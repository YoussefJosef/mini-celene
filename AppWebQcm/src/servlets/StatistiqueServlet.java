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

import ejb.dto.InscriptionDto;
import ejb.dto.ResultatChapitreDto;
import ejb.entities.Chapitre;
import ejb.entities.Inscription;
import ejb.entities.Module;
import ejb.entities.ResultatChapitre;
import ejb.metier.interfaces.IChapitreMetier;
import ejb.metier.interfaces.IInscriptionMetier;
import ejb.metier.interfaces.IModuleMetier;
import ejb.metier.interfaces.IResultatChapitreMetier;
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
	
	@EJB
	private IResultatChapitreMetier metierResult;
	
	@EJB
	private IChapitreMetier metierChapitre;
	
	public StatistiqueServlet() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		allowOrDenyAcces(request,response,2);
		//INITIALISATION DES PARAMATRES
		String action = request.getParameter("action");
		String login = (String) request.getSession().getAttribute("login");
		
		String idModuleStr = request.getParameter("idModule");
		int idModule= 0;
		if(idModuleStr!=null && !idModuleStr.equals(""))
			idModule = Integer.parseInt(idModuleStr);
		
		String loginInscrit = request.getParameter("idInscrit");
		
		if(action!= null){
			switch (action) {
			case "detail":
				List<Chapitre> listC = metierChapitre.getListChapitre(idModule);
				List<ResultatChapitre> l = new ArrayList<>();
				if(listC.isEmpty()){
					request.getRequestDispatcher("enseignant/statStudent.jsp").forward(request, response);	
				}
				
				for(Chapitre C : listC){
					List<ResultatChapitre> listRC = metierResult.getListResultatChapitre(loginInscrit, C.getId());
					l.addAll(listRC);
				}
				if(l.isEmpty()){
					request.getRequestDispatcher("enseignant/statStudent.jsp").forward(request, response);	
				}

				List<ResultatChapitreDto> dto = new ArrayList<ResultatChapitreDto>();
				for(ResultatChapitre rc : l){
					dto.add(new ResultatChapitreDto(rc));
				}
				request.setAttribute("listResult", dto);
				request.setAttribute("login", loginInscrit);
				request.setAttribute("module", metierModule.getModule(idModule).getNom());
				request.getRequestDispatcher("enseignant/statStudent.jsp").forward(request, response);	
			}
		}
		List<Module> listM = metierModule.getListModule(login);
		List<Inscription> l = new ArrayList<>();
		if(listM.isEmpty()){
			request.getRequestDispatcher("enseignant/stat.jsp").forward(request, response);	
		}
		
		for(Module M : listM){
			List<Inscription> listI = metierInscrit.getListInscriptionByModule(M.getId());
			l.addAll(listI);
		}
		if(l.isEmpty()){
			request.getRequestDispatcher("enseignant/stat.jsp").forward(request, response);	
		}

		List<InscriptionDto> dto = new ArrayList<InscriptionDto>();
		for(Inscription i : l){
			dto.add(new InscriptionDto(i));
		}
		request.setAttribute("listInscrit", dto);
		request.getRequestDispatcher("enseignant/stat.jsp").forward(request, response);	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
