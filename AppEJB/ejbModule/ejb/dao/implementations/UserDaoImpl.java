package ejb.dao.implementations;

import java.util.List;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import ejb.dao.interfaces.IUserDao;
import ejb.entities.User;
@Stateless
public class UserDaoImpl implements IUserDao {
	
	@PersistenceContext(unitName="monUnite")
	private EntityManager em;

	@Override
	public void addUser(User u) {
		em.persist(u);
	}

	@Override
	public User getUser(String login) {
		User u=em.find(User.class, login);
		return u;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> listUser() {
		int i = 1 ;
		Query req = em.createQuery("select u from User u where u.role != :r",User.class)
				.setParameter("r",i);
		return req.getResultList();
	}

	@Override
	public void editUser(User u) {
		em.merge(u);		
	}

	@Override
	public void deleteUser(String login) {
		em.remove(this.getUser(login));
		
	}
}
