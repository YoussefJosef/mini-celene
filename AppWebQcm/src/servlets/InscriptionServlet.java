package servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ejb.metier.interfaces.IInscriptionMetier;
import ejb.metier.interfaces.IModuleMetier;

@WebServlet("/InscriptionServlet")
public class InscriptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private IInscriptionMetier metierInscription ;

	@EJB
	private IModuleMetier metierModule;
	
    public InscriptionServlet() {
        super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		String login = (String) request.getSession().getAttribute("login");
		

		String idModuleStr = request.getParameter("idModule");
		
		int idModule= 0;
		if(idModuleStr!=null && !idModuleStr.equals(""))
			idModule = Integer.parseInt(idModuleStr);
		
		String idInscriptionStr = request.getParameter("idInscription");
		int idInscription= 0;
		if(idInscriptionStr!=null && !idInscriptionStr.equals(""))
			idInscription = Integer.parseInt(idInscriptionStr);
		
		if(action != null ){
			switch(action){
			case "home" :
				request.setAttribute("allModules", metierInscription.getOtherModules(login));
				request.getRequestDispatcher("etudiant/inscription.jsp").forward(request, response);	
				//break;
			case "Add" :
				metierInscription.addInscription(login, idModule);
				break;
			case "Delete" :
				metierInscription.deleteInscription(login,idModule);
				break;
			}
		}
		request.setAttribute("myModules", metierInscription.getModules(login));
		request.getRequestDispatcher("etudiant/mesModules.jsp").forward(request, response);		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
