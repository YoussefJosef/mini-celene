package ejb.metier.implementations;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ejb.dao.interfaces.IAccesChapterDao;
import ejb.dao.interfaces.IChapitreDao;
import ejb.dao.interfaces.IInscriptionDao;
import ejb.dao.interfaces.IModuleDao;
import ejb.dao.interfaces.IQuestionReponseDao;
import ejb.dao.interfaces.IReponseDao;
import ejb.dao.interfaces.IResultatChapitreDao;
import ejb.dao.interfaces.IUserDao;
import ejb.entities.AccesChapter;
import ejb.entities.Chapitre;
import ejb.entities.Inscription;
import ejb.entities.Module;
import ejb.entities.QuestionReponse;
import ejb.entities.Reponse;
import ejb.entities.ResultatChapitre;
import ejb.entities.User;
import ejb.metier.interfaces.IUserMetier;

@Stateless
public class UserMetierImpl implements IUserMetier {

	@PersistenceContext(unitName="monUnite")
	private EntityManager em;
	
	@EJB
	IUserDao daoUser ;
	@EJB
	IInscriptionDao daoInscription;
	@EJB
	IModuleDao daoModule ;
	@EJB
	IChapitreDao daoChapitre;
	@EJB
	IReponseDao daoReponse;
	@EJB
	IQuestionReponseDao daoQuestionReponse;
	@EJB
	IResultatChapitreDao daoResultatChapitre;
	@EJB
	IAccesChapterDao daoAccesChapter;
	
	@Override
	public void addUser(User u) {
		daoUser.addUser(u);
	}
	
	@Override
	public User getUser(String login) {
		return daoUser.getUser(login);
	}
	
	@Override
	public List<User> listUser() {
		return daoUser.listUser();
	}
	
	@Override
	public void deleteUser(String login) {
		
		List<Inscription> listI = daoInscription.getListInscriptionByUser(login);
		for(Inscription i : listI){
			daoInscription.deleteInscription(login, i.getModule().getId());
		}
		
		List<ResultatChapitre> listRC = daoResultatChapitre.getListResultatChapitreByUser(login);
		for(ResultatChapitre rc : listRC){
			daoResultatChapitre.deleteResultatChapitre(login, rc.getChapitre().getId());
		}
		
		List<AccesChapter> listAC = daoAccesChapter.getListAccesChapter(login);
		for(AccesChapter ac : listAC){
			daoAccesChapter.deleteAccesChapter(ac.getId());
		}
		
		List<Module> listM = daoModule.getListModule(login);
		for(Module m : listM){
			List<Chapitre> listC = daoChapitre.getListChapitre(m.getId());
			for(Chapitre c : listC){
				List<QuestionReponse> listQR = daoQuestionReponse.getListQuestionReponse(c.getId());
				for(QuestionReponse qr : listQR){
					List<Reponse> list = daoReponse.getListReponses(qr.getId());
					for(Reponse r : list){
						daoReponse.deleteReponse(r.getId());
					}
					daoQuestionReponse.deleteQuestionReponse(qr.getId());
				}
				List<ResultatChapitre> listRCC = daoChapitre.getChapitre(c.getId()).getListResultatChapitres();
				for(ResultatChapitre rc : listRCC){
					daoResultatChapitre.deleteResultatChapitre(rc.getUser().getLogin(), c.getId());
				}
				daoChapitre.deleteChapitre(c.getId());
			}
			List<Inscription> listIm = daoInscription.getListInscriptionByModule(m.getId());
			for(Inscription i : listIm){
				daoInscription.deleteInscription(i.getUser().getLogin(), m.getId());
			}
			daoModule.deleteModule(m.getId());
		}
		daoUser.deleteUser(login);
	}
	
	@Override
	public void editUser(User u) {
		daoUser.editUser(u);
	}
	
	@Override
	public int getRole(String login) {
		return em.createQuery("select u.role from User u where u.login  = :log ", Integer.class)
				.setParameter("log", login).getSingleResult();
	}

	@Override
	public boolean checkCredentials(String login, String mdp) {
		User user = daoUser.getUser(login);		
		return ((user != null) && (user.getPassword().equals(mdp)));
	}

	@Override
	public void addUser(String login, String password, int role, String nom, String prenom) {
		User u = new User();
		u.setLogin(login);
		u.setPassword(password);
		u.setRole(role);
		u.setNom(nom);
		u.setPrenom(prenom);
		daoUser.addUser(u);
	}

	@Override
	public void editUser(String login, String password, int role, String nom, String prenom) {
		User u = daoUser.getUser(login);
		u.setPassword(password);
		u.setRole(role);
		u.setNom(nom);
		u.setPrenom(prenom);
		daoUser.editUser(u);
	}

	

	

	
}
