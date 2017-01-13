package servlets;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ejb.metier.interfaces.IChapitreMetier;
import ejb.metier.interfaces.IResultatChapitreMetier;
import ejb.metier.interfaces.IUserMetier;

/**
 * Servlet implementation class ChapitreServlet
 */
@WebServlet("/ChapitreServlet")
public class ChapitreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private IChapitreMetier metier ;
	@EJB
	private IResultatChapitreMetier metierRC ;
	@EJB
	private IUserMetier metierU ;
	
    public ChapitreServlet() {
        super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		allowOrDenyAcces(request,response,2);
		//INITIALISATION DE PARAMETRES
		String action = request.getParameter("action");
		String page = request.getParameter("page");
		
		
		String idChapitreStr = request.getParameter("idChapitre");
		int idChapitre= 0;
		if(idChapitreStr!=null && !idChapitreStr.equals(""))
			idChapitre = Integer.parseInt(idChapitreStr);
		
		String titre = request.getParameter("titre");
		String texte = request.getParameter("texte");
		boolean printAnswers = (request.getParameter("printAnswers") != null);
		
		String scoreMinStr = request.getParameter("scoreMin");
		int scoreMin = 0;
		if(scoreMinStr!=null){
			if(!scoreMinStr.equals("")){
				scoreMin = Integer.parseInt(scoreMinStr);
			}
			else{
				request.setAttribute("attentionEntier", "Nombre entier requis");
			}
		}
			
		
		String idModuleStr = request.getParameter("idModule");
		int idModule= 0;
		if(idModuleStr!=null && !idModuleStr.equals(""))
			idModule = Integer.parseInt(idModuleStr);
		
		String idModuleSessionStr =""+request.getSession().getAttribute("idMS");
		if(idModule == 0){
			if(idModuleSessionStr!=null && !idModuleSessionStr.equals(""))
				idModule= Integer.parseInt(idModuleSessionStr);
			
		}
		// FIN INITIALISATION DE PARAMETRES
		
		
		if(action!= null ){
			switch(action){
			case "module" :
				if(idModule !=0) {
					request.getSession().setAttribute("idMS",idModule);
					request.setAttribute("allChapitres", metier.getListChapitre(idModule));
					request.getRequestDispatcher("enseignant/chapitre.jsp").forward(request, response);
				}
				break;
			case "Add":
				if(idModule != 0){
					if(!titre.equals("") && !texte.equals("") && !scoreMinStr.equals("")){
						metier.addChapitre(idModule, titre, texte, scoreMin,printAnswers);
					}
					else {
						request.setAttribute("attention", "Tout les champs n'ont pas été correctement remplis");
					}
				}
				break;
			case "Edit":
				if(page.equals("chapitre")){
					request.setAttribute("attentionEntier", "");
					request.setAttribute("currentChapitre", metier.getChapitre(idChapitre));
					request.getRequestDispatcher("enseignant/editChapitre.jsp").forward(request, response);
				} 
				else if(page.equals("edit") && idChapitre !=0){
					if(!titre.equals("") && !texte.equals("") && !scoreMinStr.equals("")){
						metier.editChapitreById(idChapitre, titre, texte, scoreMin,printAnswers);
					}
					else {
						request.setAttribute("attention", "Tout les champs n'ont pas été correctement remplis");
						request.setAttribute("currentChapitre", metier.getChapitre(idChapitre));
						request.getRequestDispatcher("enseignant/editChapitre.jsp").forward(request, response);
					}
					request.setAttribute("allChapitres", metier.getListChapitre(idModule));
					request.getRequestDispatcher("enseignant/chapitre.jsp").forward(request, response);
				}
				else
					request.getRequestDispatcher("index.jsp").forward(request, response);
				break;
			case "Delete":
				metier.deleteChapitre(idChapitre);
				break;
			case "Search":
				metier.getChapitre(idChapitre);
				break;
			}
		}
		
		request.setAttribute("allChapitres", metier.getListChapitre(idModule));
		request.getRequestDispatcher("enseignant/chapitre.jsp").forward(request, response);		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
	