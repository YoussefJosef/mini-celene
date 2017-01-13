package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ejb.dto.QuestionReponseDto;
import ejb.entities.QuestionReponse;
import ejb.metier.interfaces.IQuestionReponseMetier;
import ejb.metier.interfaces.IReponseMetier;
import ejb.metier.interfaces.IUserMetier;

@WebServlet("/QuestionReponseServlet")
public class QuestionReponseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	private IQuestionReponseMetier metier ;
	
	@EJB
	private IReponseMetier metierReponse ;
	
	@EJB
	private IUserMetier metierU ;
   
    public QuestionReponseServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				allowOrDenyAcces(request,response,2);
		//INITIALISATION DE PARAMETRES
				String action = request.getParameter("action");
				String idChapitreStr = request.getParameter("idChapitre");
				int idChapitre= 0;
				if(idChapitreStr!=null && !idChapitreStr.equals(""))
					idChapitre = Integer.parseInt(idChapitreStr);
				
				String idQuestionReponseStr = request.getParameter("idQuestionReponse");
				int idQuestionReponse = 0;
				if(idQuestionReponseStr!=null && !idQuestionReponseStr.equals(""))
					idQuestionReponse = Integer.parseInt(idQuestionReponseStr);
				
				String question= request.getParameter("question");
				String indication= request.getParameter("indication");
				String nbReponseStr = request.getParameter("numReponse");
				int nbReponse= 0;
				if(nbReponseStr!=null && !nbReponseStr.equals(""))
					nbReponse = Integer.parseInt(nbReponseStr);
				
				String scoreStr = request.getParameter("score");
				int score= 0;
				if(scoreStr!=null){
					if(!scoreStr.equals("")){
						score = Integer.parseInt(scoreStr);
					}
					else{
						request.setAttribute("attentionEntier", "Nombre entier requis");
					}
				}
		
				String idChapitreSessionStr =""+request.getSession().getAttribute("idC");
				if(idChapitre == 0){
					if(idChapitreSessionStr!=null && !idChapitreSessionStr.equals(""))
						idChapitre= Integer.parseInt(idChapitreSessionStr);
				}
				
				//FIN
				
			if(action != null ){
				switch(action){
				case "Add" :
					if(idChapitre != 0){
						if(!question.equals("") && nbReponse>0 && !scoreStr.equals("")){
							boolean testReponse = true;
							for(int i=0; i<nbReponse; i++){
								if(request.getParameter("reponse"+i).equals("")){
									testReponse = false;
								}
							}
							if(testReponse){
								int idQCM = metier.addQuestionReponse(idChapitre, question, nbReponse,score,indication);
								for(int i=0; i<nbReponse; i++){
									metierReponse.addReponse(idQCM, request.getParameter("reponse"+i), request.getParameter("bonneReponse"+i) != null);
								}
							}
							else {
								request.setAttribute("attention", "Tout les champs n'ont pas été correctement remplis");
							}
							
						}
						else {
							request.setAttribute("attention", "Tout les champs n'ont pas été correctement remplis");
						}
					}
					break;
				case "Edit" : 
					break;
				case "Delete" :
					metier.deleteQuestionReponse(idQuestionReponse);
					break;
				case "chapitre" :
					if(idChapitre !=0) {
						request.getSession().setAttribute("idC",idChapitre);
						List<QuestionReponseDto> dto = new ArrayList<QuestionReponseDto>();
						for(QuestionReponse qr : metier.getListQuestionReponse(idChapitre)){
							dto.add(new QuestionReponseDto(qr));
						}
						request.setAttribute("allQuestionReponses", dto);
						request.getRequestDispatcher("enseignant/questionReponse.jsp").forward(request, response);
					}
					break;
				}
				
			}
			List<QuestionReponseDto> dto = new ArrayList<QuestionReponseDto>();
			for(QuestionReponse qr : metier.getListQuestionReponse(idChapitre)){
				dto.add(new QuestionReponseDto(qr));
			}
			request.setAttribute("allQuestionReponses", dto);
			request.getRequestDispatcher("enseignant/questionReponse.jsp").forward(request, response);		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
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