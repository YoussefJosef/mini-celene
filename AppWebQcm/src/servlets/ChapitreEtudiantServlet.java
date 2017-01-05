package servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ejb.metier.interfaces.IChapitreMetier;

/**
 * Servlet implementation class ChapitreEtudiantServlet
 */
@WebServlet("/ChapitreEtudiantServlet")
public class ChapitreEtudiantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private IChapitreMetier metier ;

	
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
	