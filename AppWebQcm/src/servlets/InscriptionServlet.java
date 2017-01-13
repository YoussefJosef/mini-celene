package servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ejb.entities.Chapitre;
import ejb.metier.interfaces.IAccesChapterMetier;
import ejb.metier.interfaces.IChapitreMetier;
import ejb.metier.interfaces.IInscriptionMetier;
import ejb.metier.interfaces.IModuleMetier;
import ejb.metier.interfaces.IUserMetier;

@WebServlet("/InscriptionServlet")
public class InscriptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private IInscriptionMetier metierInscription ;

	@EJB
	private IModuleMetier metierModule;
	
	@EJB
	private IAccesChapterMetier metierAccesChapter;
	
	@EJB
	private IUserMetier metierUser;
	@EJB
	private IChapitreMetier metierC;
	
    public InscriptionServlet() {
        super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		allowOrDenyAcces(request,response,3);
		//INITIALISATION DES PARAMETRES
		
		String action = request.getParameter("action");
		String login = (String) request.getSession().getAttribute("login");
		

		String idModuleStr = request.getParameter("idModule");
		
		int idModule= 0;
		if(idModuleStr!=null && !idModuleStr.equals(""))
			idModule = Integer.parseInt(idModuleStr);
		
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date today = Calendar.getInstance().getTime();        
		String dateInscription = df.format(today);
		
		List<Chapitre> listC = metierC.getListChapitre(idModule) ;
		
		if(action != null ){
			switch(action){
			case "home" :
				request.setAttribute("allModules", metierInscription.getOtherModules(login));
				request.getRequestDispatcher("etudiant/inscription.jsp").forward(request, response);	
				//break;
			case "Add" :
				metierInscription.addInscription(login, idModule, dateInscription);
				if(  !(listC.isEmpty())  )
				metierAccesChapter.addAccesChapter(login, listC.get(0).getId());
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
	protected void allowOrDenyAcces(HttpServletRequest request, HttpServletResponse response,int role) throws ServletException, IOException{
		
		String currentLogin =  (String) request.getSession().getAttribute("login");
		
		if(currentLogin!=null){
			if(!currentLogin.equals("")){
				if(metierUser.getRole(currentLogin) == role ){
					return;
				}	
			}
		}
		request.getRequestDispatcher("/AuthServlet").forward(request, response);
	}

}
