package servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import ejb.entities.QuestionReponse;

import ejb.entities.Reponse;
import ejb.metier.interfaces.IAccesChapterMetier;
import ejb.metier.interfaces.IChapitreMetier;
import ejb.metier.interfaces.IInscriptionMetier;
import ejb.metier.interfaces.IQuestionReponseMetier;
import ejb.metier.interfaces.IReponseMetier;
import ejb.metier.interfaces.IResultatChapitreMetier;
import ejb.metier.interfaces.IUserMetier;

/**
 * Servlet implementation class QcmServlet
 */
@WebServlet("/QcmServlet")
public class QcmServlet extends HttpServlet  {
	private static final long serialVersionUID = 1L;
	@EJB
	private IQuestionReponseMetier metierQR;
	
	@EJB
	private IReponseMetier metierR;
	
	@EJB
	private IResultatChapitreMetier metierRC;
	
	@EJB
	private IChapitreMetier metierC;
	
	@EJB
	private IUserMetier metierU;
	@EJB
	private IAccesChapterMetier metierA;
	@EJB
	private IInscriptionMetier metierI;
	public QcmServlet() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		allowOrDenyAcces(request,response,3);
		//INITIALISATION DES PARAMETRES
		String action =  request.getParameter("action");
		String login = (String) request.getSession().getAttribute("login");
		String idChapitreSessionStr =""+request.getSession().getAttribute("idChap");
		final boolean TRUE=true;
		final boolean FALSE=false;
		boolean test = true;
		int idChapitre = 0 ;
		if(idChapitre == 0){
			if(idChapitreSessionStr!=null && !idChapitreSessionStr.equals(""))
				idChapitre= Integer.parseInt(idChapitreSessionStr);
		}
		
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date today = Calendar.getInstance().getTime();        
		String datevalidation = df.format(today);
	
		ArrayList<String> listQuestion = new ArrayList<String>();
		int question;
		int score = 0;
		int scoreMin =0;
		
		String messageInformation = "";
		
		
		if(action!= null){
			switch (action) {
			case "verify":
				
				int j = 1 ;
				while (request.getParameter("q+"+j) != null) {
					listQuestion.add(request.getParameter("q+"+j));
					j++;
				}
				
				for(int i =0 ; i<listQuestion.size();++i){
					question = Integer.parseInt(listQuestion.get(i));
					for (Reponse R : metierR.getListReponses(question)) {
						if(request.getParameter("r+"+R.getId()) != null){
							if(!R.isBonneRep()){
								test = false;
							}
						}
						else{
							if(R.isBonneRep()){
								test = false;
							}
						}
					}
					if(test){
						score +=metierQR.getQuestionReponse(question).getScore();
					}
					test = true;
				}
				scoreMin =	metierC.getChapitre(idChapitre).getScoreMin();
			    
				/*Traitement des etats */
				if(  !( metierRC.checkIfUserModuleExist(login, idChapitre) )  ){
					if(score>=scoreMin){
						
						metierRC.addResultatChapitre(login, idChapitre, score, datevalidation,TRUE);
						// fct to add access CHApter here
						
						messageInformation = "Bravo ! vous avez reussi a passer le QCM et valider le chapitre,"
								+ "vous avez acces au chapitre suivant !";
						//bloké qcm
						
						
						//envoyer bonnes reponses
						sendCorrectAnswers(request, response, idChapitre);
						
					    metierI.updateProgression(login, metierC.getChapitre(idChapitre).getModule().getId());
					    
						request.setAttribute("messageInformation", messageInformation);
						request.setAttribute("chapitre", metierC.getChapitre(idChapitre));
						request.setAttribute("reusite", FALSE);
						request.setAttribute("resultatChapitre", metierRC.getResultatChapitre(login, idChapitre));
						request.setAttribute("module", metierC.getChapitre(idChapitre).getModule());
						request.getRequestDispatcher("etudiant/resultat.jsp").forward(request, response);
						
					}
					else{
						metierRC.addResultatChapitre(login, idChapitre, score,"-----",FALSE);
						messageInformation = "Vous n'avez pas reussi a valider le QCM, "
								+ "Votre score est de "+score+". Il est inferieur au score minimum requis de "+scoreMin+", Veuillez réessayer !";
						request.setAttribute("reusite", TRUE);
						request.setAttribute("messageInformation", messageInformation);
						request.setAttribute("chapitre", metierC.getChapitre(idChapitre));
						request.setAttribute("resultatChapitre", metierRC.getResultatChapitre(login, idChapitre));
						request.setAttribute("module", metierC.getChapitre(idChapitre).getModule());
						request.getRequestDispatcher("etudiant/resultat.jsp").forward(request, response);
						//non validé peut refaire
					}
				}
				else{//user a deja passé qcm 
					boolean validated = metierRC.getResultatChapitre(login, idChapitre).isValidated();
					if(!validated){
						if(score>=scoreMin){
							messageInformation = "Bravo ! vous avez reussi a passer le QCM et valider le chapitre,"
									+ "vous avez acces au chapitre suivant !";
						
							metierRC.editResultatChapitreWithDate(login, idChapitre, score, datevalidation,TRUE);
							
							sendCorrectAnswers(request, response, idChapitre);
							
						    metierI.updateProgression(login, metierC.getChapitre(idChapitre).getModule().getId());
						    
							request.setAttribute("reusite", FALSE);
							request.setAttribute("messageInformation", messageInformation);
							request.setAttribute("chapitre", metierC.getChapitre(idChapitre));
							request.setAttribute("resultatChapitre", metierRC.getResultatChapitre(login, idChapitre));
							request.setAttribute("module", metierC.getChapitre(idChapitre).getModule());
							request.getRequestDispatcher("etudiant/resultat.jsp").forward(request, response);
						
							//bloké qcm
						}
						else{
							metierRC.editResultatChapitreWithDate(login, idChapitre, score, "-----",FALSE);
							messageInformation = "Vous n'avez pas reussi a valider le QCM, "
									+ "Votre score est de "+score+". Il est inferieur au score minimum requis de "+scoreMin+", Veuillez réessayer !";
							request.setAttribute("messageInformation", messageInformation);
							request.setAttribute("chapitre", metierC.getChapitre(idChapitre));
							request.setAttribute("reusite", TRUE);
							request.setAttribute("resultatChapitre", metierRC.getResultatChapitre(login, idChapitre));
							request.setAttribute("module", metierC.getChapitre(idChapitre).getModule());
							request.getRequestDispatcher("etudiant/resultat.jsp").forward(request, response);
							
							//nv 
							//peut refaire
						}
					}
					else{
						messageInformation = "vous avez deja validé ce QCM, impossible de refaire";
						request.setAttribute("messageInformation", messageInformation);
						request.setAttribute("reusite", FALSE);
						request.setAttribute("module", metierC.getChapitre(idChapitre).getModule());
						request.getRequestDispatcher("etudiant/resultat.jsp").forward(request, response);
					}
				}			
			}
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected void sendCorrectAnswers(HttpServletRequest request, HttpServletResponse response ,int idChapitre){
		boolean printAnswers = metierC.getChapitre(idChapitre).isPrintAnswers();
		List<QuestionReponse> listQR = metierQR.getListQuestionReponse(idChapitre);
		List<Object> l = new LinkedList<>();
		for(QuestionReponse qr : listQR){
			List<Reponse> listR = metierR.getListReponses(qr.getId()) ;
			l.add(listR);
		}
		request.setAttribute("listquestions", listQR);
		request.setAttribute("listdelistReponses", l);
		request.setAttribute("printAnswers", printAnswers);
	}
	
	protected void allowOrDenyAcces(HttpServletRequest request, HttpServletResponse response,int role) throws ServletException, IOException{
		
		String currentLogin =  (String) request.getSession().getAttribute("login");
		
		if(currentLogin!=null){
			if(!currentLogin.equals("")){
				if(metierU.getRole(currentLogin) == role ){
					return;
				}	
			}
		}
		request.getRequestDispatcher("/AuthServlet").forward(request, response);
	}
	

}

