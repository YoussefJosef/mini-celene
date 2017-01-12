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
				
				String scoreStr = request.getParameter("score");
				int score= 0;
				if(scoreStr!=null && !scoreStr.equals(""))
					score = Integer.parseInt(scoreStr);
		
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
						int idQCM = metier.addQuestionReponse(idChapitre, question, nbReponse,score);
						
						//on cr�er toutes les r�ponses directement.
						for(int i=0; i<nbReponse; i++){
							metierReponse.addReponse(idQCM, request.getParameter("reponse"+i), request.getParameter("bonneReponse"+i) != null);
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
						List<QuestionReponseDto> dto = new ArrayList();
						for(QuestionReponse qr : metier.getListQuestionReponse(idChapitre)){
							dto.add(new QuestionReponseDto(qr));
						}
						request.setAttribute("allQuestionReponses", dto);
						request.getRequestDispatcher("enseignant/questionReponse.jsp").forward(request, response);
					}
					break;
				}
				
			}
			List<QuestionReponseDto> dto = new ArrayList();
			for(QuestionReponse qr : metier.getListQuestionReponse(idChapitre)){
				dto.add(new QuestionReponseDto(qr));
			}
			request.setAttribute("allQuestionReponses", dto);
			request.getRequestDispatcher("enseignant/questionReponse.jsp").forward(request, response);		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}