package servlets;

import java.io.IOException;



import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ejb.entities.User;
import ejb.metier.interfaces.IUserMetier;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @EJB
    private IUserMetier metier ;
   
    public UserServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String roleStr = request.getParameter("role");
		String page = request.getParameter("page");
		int role = 0;
		if(roleStr!=null && !roleStr.equals(""))
				role = Integer.parseInt(roleStr);
	
		
		if(action != null && login !=null) {
			User u = new User ( login,  password,  role,  nom,  prenom);
			
			switch (action) {
			case "Add":
				metier.addUser(u);
				break;
			case "Edit":
				if(page.equals("home")){
					request.setAttribute("currentUser", metier.getUser(login));
					request.getRequestDispatcher("admin/editUser.jsp").forward(request, response);
				} 
				else if(page.equals("edit")){
					metier.editUser(u);
					request.setAttribute("allUsers", metier.listUser());
					request.getRequestDispatcher("admin/home.jsp").forward(request, response);
				}
				else
					request.getRequestDispatcher("index.jsp").forward(request, response);
				
				break;
			case "Delete":
				metier.deleteUser(login);
				break;
			case "Search":
				metier.getUser(login);
				break;
			}
		}
		
		request.setAttribute("allUsers", metier.listUser());
		request.getRequestDispatcher("admin/home.jsp").forward(request, response);
		//	response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
