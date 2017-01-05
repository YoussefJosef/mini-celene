package ejb.metier.implementations;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ejb.dao.interfaces.IInscriptionDao;
import ejb.dao.interfaces.IUserDao;
import ejb.entities.Inscription;
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
