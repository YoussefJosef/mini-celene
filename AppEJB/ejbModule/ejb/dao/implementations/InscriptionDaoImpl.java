package ejb.dao.implementations;

import java.util.List;




import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ejb.dao.interfaces.IInscriptionDao;
import ejb.entities.Inscription;
import ejb.entities.User;
import ejb.entities.Module;
@Stateless
public class InscriptionDaoImpl implements IInscriptionDao {
	
	@PersistenceContext(unitName="monUnite")
	private EntityManager em;

	@Override
	public Inscription addInscription(String login,int idModule) {
		User user = em.find(User.class,login);
		Module module = em.find(Module.class, idModule);
		
		Inscription inscription = new Inscription();
		inscription.setUser(user);
		inscription.setModule(module);
		em.persist(inscription);
		
		return inscription;
	}

	@Override
	public Inscription getInscription(String login, int idModule){
		User user = em.find(User.class,login);
		Module module = em.find(Module.class, idModule);
		
		Query q = em.createQuery("select i from Inscription i where i.user  = :log and i.module= :mod", Inscription.class)
				.setParameter("log", user).setParameter("mod",module);
		return  (Inscription) q.getSingleResult();
	}

	@Override
	public List<Inscription> listInscription() {
		Query req = em.createQuery("select i from Inscription i");
		return req.getResultList();
	}

	@Override
	public void editInscription(String login, int idModule) {
		em.merge(this.getInscription(login, idModule));
	}

	@Override
	public void deleteInscription(String login, int idModule) {
		em.remove(this.getInscription(login,idModule));
	}

}
