package ejb.metier.implementations;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ejb.dao.interfaces.IUserDao;
import ejb.entities.User;
import ejb.metier.interfaces.IUserMetier;

@Stateless
public class UserMetierImpl implements IUserMetier {

	@PersistenceContext(unitName="monUnite")
	private EntityManager em;
	@EJB
	IUserDao dao ;
	
	@Override
	public int getRole(String login) {
		return em.createQuery("select u.role from User u where u.login  = :log ", Integer.class)
				.setParameter("log", login).getSingleResult();
	}

	@Override
	public boolean checkCredentials(String login, String mdp) {
		User user = dao.getUser(login);		
		return ((user != null) && (user.getPassword().equals(mdp)));
	}

	@Override
	public User addUser(User u) {
		return dao.addUser(u);
	}

	@Override
	public User getUser(String login) {
		return dao.getUser(login);
	}

	@Override
	public List<User> listUser() {
		return dao.listUser();
	}

	@Override
	public void editUser(User u) {
		dao.editUser(u);
		
	}

	@Override
	public void deleteUser(String login) {
		dao.deleteUser(login);
		
	}
}
