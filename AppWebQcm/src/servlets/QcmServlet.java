package servlets;

import java.awt.List;
import java.io.IOException;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ejb.dao.interfaces.IResultatChapitreDao;
import ejb.entities.Reponse;
import ejb.entities.ResultatChapitre;
import ejb.metier.interfaces.IModuleMetier;
import ejb.metier.interfaces.IQuestionReponseMetier;
import ejb.metier.interfaces.IReponseMetier;
import ejb.metier.interfaces.IResultatChapitreMetier;

/**
 * Servlet implementation class QcmServlet
 */
@WebServlet("/QcmServlet")
public class QcmServlet extends HttpServlet  {
	private static final long serialVersionUID = 1L;
	@EJB
	private IQuestionReponseMetier metierQR;
	
	@EJB
	private IReponseMetier metierR;
	
	@EJB
	private IResultatChapitreMetier metierRC;
	
	public QcmServlet() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String action =  request.getParameter("action");
		String login = (String) request.getSession().getAttribute("login");
		String repQuestion = request.getParameter("repQuestion");
		int idChapitre = 2 ;
		
		String datevalidation = "date" ;
		int idQuestionReponse = 1 ;
		
		ArrayList<String> listReponses =  new ArrayList<String>();
		
		if(action!= null){
			switch (action) {
			case "verify":
				
				ArrayList<String> listReponseEtudiant = new ArrayList<String>();
				ArrayList<String> listQuestion = new ArrayList<String>();
				int j = 1 ;
				while (request.getParameter(""+j) != null) {
					listReponseEtudiant.add(request.getParameter(""+j));
					listQuestion.add(request.getParameter("q+"+j));
					j++;
				}
				Reponse reponse = metierQR.getReponseByIdQrAndStringReponse(2, "repo");
				boolean bobo = reponse.isBonneRep();
				System.out.println("popia"+bobo);
				System.out.println("grrr"+reponse);
				/* Etape 1 :je donne a une fct en param idQR et reponse etudiant et elle me retourne
				 * le valeur boolean de sa reponse, donc reste a faire un getScore de la QuestionReponse
				 * dans le cas ou la reponse a une valeur true, et puis faire le somme des score
				 * pour obtenir le scoreFinal.
				 * Etape 2 : avant de passer au chapitre suivant verifier si scorefinal est sup ou egale
				 * 	au score min pour passer .... 
				 */
				
			//	int score = metierRC.getNoteQcm(listReponseEtudiant,listQuestion);
			//	metierRC.addResultatChapitre(login,idChapitre,score,datevalidation);
				
				System.out.println("youyou"+listQuestion);
				
				
			//	request.setAttribute("score", metierRC.getResultatChapitre(login, idChapitre).getScore());
				request.getRequestDispatcher("etudiant/resultat.jsp").forward(request, response);
				break;
			}
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
}

