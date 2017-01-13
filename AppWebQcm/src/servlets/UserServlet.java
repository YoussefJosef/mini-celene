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

import ejb.dto.UserDto;
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
			switch (action) {
			case "Add":
				if(!login.equals("") && !password.equals("") && role != 0 && nom.equals("") && prenom.equals("")){
					metier.addUser(login,  password,  role,  nom,  prenom);
				}
				else {
					request.setAttribute("attention", "Tout les champs n'ont pas été correctement remplis");
				}
				break;
			case "Edit":
				if(page.equals("home")){
					request.setAttribute("currentUser", metier.getUser(login));
					request.getRequestDispatcher("admin/editUser.jsp").forward(request, response);
				} 
				else if(page.equals("edit")){
					if(!login.equals("") && !password.equals("") && role != 0 && nom.equals("") && prenom.equals("")){
						metier.editUser(login,  password,  role,  nom,  prenom);
					}
					else {
						request.setAttribute("attention", "Tout les champs n'ont pas été correctement remplis");
						request.setAttribute("currentUser", metier.getUser(login));
						request.getRequestDispatcher("admin/editUser.jsp").forward(request, response);
					}
					List<UserDto> dto = new ArrayList<UserDto>();
					for(User u : metier.listUser()){
						dto.add(new UserDto(u));
					}
					request.setAttribute("allUsers", dto);
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
		List<UserDto> dto = new ArrayList<UserDto>();
		for(User u : metier.listUser()){
			dto.add(new UserDto(u));
		}
		request.setAttribute("allUsers", dto);
		request.getRequestDispatcher("admin/home.jsp").forward(request, response);
		//	response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
