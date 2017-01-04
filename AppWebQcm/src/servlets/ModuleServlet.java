package servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ejb.metier.interfaces.IModuleMetier;

@WebServlet("/ModuleServlet")
public class ModuleServlet  extends HttpServlet  {
	private static final long serialVersionUID = 1L;
	@EJB
	private IModuleMetier metier;
	
	public ModuleServlet() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String nom = request.getParameter("nom");
		String idStr = request.getParameter("id");
		int id = 0;
		if(idStr!=null && !idStr.equals(""))
				id = Integer.parseInt(idStr);
		String login = (String) request.getSession().getAttribute("login");
		String page = request.getParameter("page");
		
		
		if(action!= null && nom!= null){
			switch (action) {
			case "Add":
				metier.addModule(nom, login);
				break;
			case "Edit":
				if(page.equals("module")){
					request.setAttribute("currentModule", metier.getModule(id));
					request.getRequestDispatcher("enseignant/editModule.jsp").forward(request, response);
				} 
				else if(page.equals("edit") && id !=0){
					metier.editModule(id,nom);
					request.setAttribute("allModules", metier.listModule());
					request.getRequestDispatcher("enseignant/module.jsp").forward(request, response);
				}
				else
					request.getRequestDispatcher("index.jsp").forward(request, response);
				
				break;
			case "Delete":
				metier.deleteModule(id);
				break;
			case "Search":
				metier.getModule(id);
				break;
			}
			
		}
		request.setAttribute("allModules", metier.getListModule(login));
		request.getRequestDispatcher("enseignant/module.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	
}
