package servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ejb.metier.interfaces.IQuestionReponseMetier;

@WebServlet("/QuestionReponseServlet")
public class QuestionReponseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	private IQuestionReponseMetier metier ;
   
    public QuestionReponseServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//INITIALISATION DE PARAMETRES
				String action = request.getParameter("action");
				String page = request.getParameter("page");
				
				String idChapitreStr = request.getParameter("idChapitre");
				int idChapitre= 0;
				if(idChapitreStr!=null && !idChapitreStr.equals(""))
					idChapitre = Integer.parseInt(idChapitreStr);
				
				String idQuestionReponseStr = request.getParameter("idQuestionReponse");
				int idQuestionReponse= 0;
				if(idQuestionReponseStr!=null && !idQuestionReponseStr.equals(""))
					idQuestionReponse = Integer.parseInt(idQuestionReponseStr);
				
				String question= request.getParameter("question");
				String reponse= request.getParameter("reponse");
				String bonneReponse= request.getParameter("bonneReponse");
		
				String idChapitreSessionStr =""+request.getSession().getAttribute("idC");
				if(idChapitre == 0){
					if(idChapitreSessionStr!=null && !idChapitreSessionStr.equals(""))
						idChapitre= Integer.parseInt(idChapitreSessionStr);
				}
				
				//FIN
				
			if(action != null ){
				switch(action){
				case "Add" :
					metier.addQuestionReponse(idChapitre, question, reponse, bonneReponse);
					break;
				case "Edit" : 
					break;
				case "Delete" :
					metier.deleteQuestionReponse(idQuestionReponse);
					break;
				default :
					request.getSession().setAttribute("idC", idChapitre);
					request.setAttribute("allQuestionReponses", metier.getQuestionReponseById(idChapitre));
					request.getRequestDispatcher("enseignant/questionReponse.jsp").forward(request, response);
					
					break;
				}
				
			}
			request.setAttribute("allQuestionReponses", metier.getQuestionReponseById(idChapitre));
			request.getRequestDispatcher("enseignant/questionReponse.jsp").forward(request, response);		
			
				
		
		
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
