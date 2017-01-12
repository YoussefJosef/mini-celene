package servlets;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ejb.entities.Inscription;
import ejb.entities.Module;
import ejb.metier.interfaces.IInscriptionMetier;
import ejb.metier.interfaces.IModuleMetier;
import ejb.metier.interfaces.IUserMetier;

@WebServlet("/StatistiqueServlet")
public class StatistiqueServlet extends HttpServlet  {

	private static final long serialVersionUID = 1L;
	
	@EJB
	private IUserMetier metierUser;
	
	@EJB
	private IModuleMetier metierModule;
	
	@EJB
	private IInscriptionMetier metierInscrit;

	public StatistiqueServlet() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String login = (String) request.getSession().getAttribute("login");
		
		if(action!= null){
			switch (action) {
			
			}
		}
		List<Module> listM = metierModule.getListModule(login);
		List l = new LinkedList<>();
		
		for(Module M : listM){
			List<Inscription> listI = metierInscrit.getListInscriptionByModule(M.getId());
			l.add(listI);
		}

		request.setAttribute("listmodule", listM);
		request.setAttribute("listdelistModule", l);
		
		request.getRequestDispatcher("enseignant/stat.jsp").forward(request, response);	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
}
