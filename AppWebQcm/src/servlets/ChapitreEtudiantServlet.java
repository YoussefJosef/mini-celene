package servlets;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ejb.entities.QuestionReponse;
import ejb.entities.Reponse;
import ejb.metier.interfaces.IChapitreMetier;
import ejb.metier.interfaces.IQuestionReponseMetier;
import ejb.metier.interfaces.IReponseMetier;

/**
 * Servlet implementation class ChapitreEtudiantServlet
 */
@WebServlet("/ChapitreEtudiantServlet")
public class ChapitreEtudiantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private IChapitreMetier metier ;
	@EJB
	private IQuestionReponseMetier metierQR;
	
	@EJB
	private IReponseMetier metierR;
	
	
	public ChapitreEtudiantServlet() {
		super();
		// TODO Auto-generated constructor stub
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//INITIALISATION DE PARAMETRES
		String action = request.getParameter("action");
		String page = request.getParameter("page");
		
		String idModuleStr = request.getParameter("idModule");
		
		int idModule= 0;
		if(idModuleStr!=null && !idModuleStr.equals(""))
			idModule = Integer.parseInt(idModuleStr);
		
		String idChapitreStr = request.getParameter("idChapitre");
		int idChapitre= 0;
		if(idChapitreStr!=null && !idChapitreStr.equals(""))
			idChapitre = Integer.parseInt(idChapitreStr);
		
		
	
		// FIN INITIALISATION DE PARAMETRES
		
		if(action!= null ){
			switch(action){
			case "cours":
				break;
			case "qcm":
				request.getSession().setAttribute("idChap",idChapitre);
				List<QuestionReponse> listQR = metierQR.getListQuestionReponse(idChapitre);
				List l = new LinkedList<>();
				
				for(QuestionReponse qr : listQR){
					List<Reponse> listR = metierR.getListReponses(qr.getId()) ;
					l.add(listR);
				}
			
				request.setAttribute("listquestions", listQR);
				request.setAttribute("listdelistReponses", l);
				
				request.getRequestDispatcher("etudiant/qcm.jsp").forward(request, response);		
				break;
			case "resultat":
				break;
			}
		}
		
		request.setAttribute("allChapitres", metier.getListChapitre(idModule));
		request.getRequestDispatcher("etudiant/chapitre.jsp").forward(request, response);		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
	