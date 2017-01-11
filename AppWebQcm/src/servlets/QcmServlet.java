package servlets;

import java.awt.List;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ejb.dao.interfaces.IResultatChapitreDao;
import ejb.entities.Chapitre;
import ejb.entities.Reponse;
import ejb.entities.ResultatChapitre;
import ejb.entities.User;
import ejb.metier.interfaces.IAccesChapterMetier;
import ejb.metier.interfaces.IChapitreMetier;
import ejb.metier.interfaces.IModuleMetier;
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
	public QcmServlet() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String action =  request.getParameter("action");
		String login = (String) request.getSession().getAttribute("login");
		String repQuestion = request.getParameter("repQuestion");
		String idChapitreSessionStr =""+request.getSession().getAttribute("idChap");
		final boolean TRUE=true;
		final boolean FALSE=false;
		int idChapitre = 0 ;
		if(idChapitre == 0){
			if(idChapitreSessionStr!=null && !idChapitreSessionStr.equals(""))
				idChapitre= Integer.parseInt(idChapitreSessionStr);
		}
		
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date today = Calendar.getInstance().getTime();        
		String datevalidation = df.format(today);
	
		
		ArrayList<String> listReponseEtudiant = new ArrayList<String>();
		ArrayList<String> listQuestion = new ArrayList<String>();
		ArrayList<String> listReponses =  new ArrayList<String>();
		
		Reponse reponse = new Reponse();
		int question;
		String reponseE;
		int score = 0;
		int scoreMin =0;
		
		String messageInformation = "";
		
		
		if(action!= null){
			switch (action) {
			case "verify":
				
				int j = 1 ;
				while (request.getParameter(""+j) != null) {
					listReponseEtudiant.add(request.getParameter(""+j));
					listQuestion.add(request.getParameter("q+"+j));
					j++;
				}
				
				for(int i =0 ; i<listQuestion.size();++i){
					question = Integer.parseInt(listQuestion.get(i));
					reponseE = listReponseEtudiant.get(i);
					reponse = metierQR.getReponseByIdQrAndStringReponse(question,reponseE);
					if(reponse.isBonneRep()){
						score +=metierQR.getQuestionReponse(question).getScore();
					}
				}
				scoreMin =	metierC.getChapitre(idChapitre).getScoreMin();
				
				
				/*Traitement des etats */
				if(  !( metierRC.checkIfUserModuleExist(login, idChapitre) )  ){
					if(score>=scoreMin){
						
						metierRC.addResultatChapitre(login, idChapitre, score, datevalidation,TRUE);
						// fct to add access CHApter here
						
					    metierA.updateAccesChapterList(login,idChapitre);
						messageInformation = "Bravo ! vous avez reussi a passer le QCM et valider le chapitre,"
								+ "vous avez acces au chapitre suivant !";
						//bloké qcm
			
						request.setAttribute("messageInformation", messageInformation);
						request.setAttribute("chapitre", metierC.getChapitre(idChapitre).getTitre());
						
						request.setAttribute("resultatChapitre", metierRC.getResultatChapitre(login, idChapitre));
						request.getRequestDispatcher("etudiant/resultat.jsp").forward(request, response);
						
					}
					else{
						metierRC.addResultatChapitre(login, idChapitre, score,"-----",FALSE);
						messageInformation = "Vous n'avez pas reussi a valider le QCM, "
								+ "Votre score="+score+" est inferieur au Score Min="+scoreMin+", Veuillez réessayer !";
						request.setAttribute("messageInformation", messageInformation);
						request.setAttribute("chapitre", metierC.getChapitre(idChapitre).getTitre());
						request.setAttribute("resultatChapitre", metierRC.getResultatChapitre(login, idChapitre));
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
							metierA.updateAccesChapterList(login,idChapitre);
		
							request.setAttribute("messageInformation", messageInformation);
							request.setAttribute("chapitre", metierC.getChapitre(idChapitre).getTitre());
							request.setAttribute("resultatChapitre", metierRC.getResultatChapitre(login, idChapitre));
							request.getRequestDispatcher("etudiant/resultat.jsp").forward(request, response);
						
							//bloké qcm
						}
						else{
							metierRC.editResultatChapitreWithDate(login, idChapitre, score, "-----",FALSE);
							messageInformation = "Vous n'avez pas reussi a valider le QCM, "
									+ "Votre score="+score+" est inferieur au Score Min="+scoreMin+", Veuillez réessayer !";
							request.setAttribute("messageInformation", messageInformation);
							request.setAttribute("chapitre", metierC.getChapitre(idChapitre).getTitre());
							request.setAttribute("resultatChapitre", metierRC.getResultatChapitre(login, idChapitre));
							request.getRequestDispatcher("etudiant/resultat.jsp").forward(request, response);
							
							//nv 
							//peut refaire
						}
					}
					else{
						messageInformation = "vous avez deja validé ce QCM, impossible de refaire";
						request.setAttribute("messageInformation", messageInformation);
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
	

}

