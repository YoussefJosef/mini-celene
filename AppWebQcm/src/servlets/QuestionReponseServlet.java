package servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ejb.metier.interfaces.IQuestionReponseMetier;
import ejb.metier.interfaces.IReponseMetier;

@WebServlet("/QuestionReponseServlet")
public class QuestionReponseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	private IQuestionReponseMetier metier ;
	
	@EJB
	private IReponseMetier metierReponse ;
   
    public QuestionReponseServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
				
				String nbReponseStr = request.getParameter("numReponse");
				int nbReponse= 0;
				if(nbReponseStr!=null && !nbReponseStr.equals(""))
					nbReponse = Integer.parseInt(nbReponseStr);
				
				// String reponse= request.getParameter("reponse");
				// String bonneReponse= request.getParameter("bonneReponse");
				
		
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
						int idQCM = metier.addQuestionReponse(idChapitre, question, nbReponse);
						
						//on créer toutes les réponses directement.
						for(int i=0; i<nbReponse; i++){
							metierReponse.addReponse(idQCM, request.getParameter("reponse"+i), request.getParameter("bonneReponse"+i)=="vrai");
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
						request.setAttribute("allQuestionReponses", metier.getQuestionsReponses(idChapitre));
						request.getRequestDispatcher("enseignant/questionReponse.jsp").forward(request, response);
					}
					break;
				}
				
			}
			request.setAttribute("allQuestionReponses", metier.getQuestionsReponses(idChapitre));
			request.getRequestDispatcher("enseignant/questionReponse.jsp").forward(request, response);		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}