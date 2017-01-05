package ejb.dao.implementations;

import java.util.ArrayList;
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
	public void addInscription(Inscription i) {
		em.persist(i);
	}

	@Override
	public Inscription getInscription(String login, int idModule){
		User user = em.find(User.class,login);
		Module module = em.find(Module.class, idModule);
		
		Query q = em.createQuery("select i from Inscription i where i.user  = :log and i.module= :mod", Inscription.class)
				.setParameter("log", user).setParameter("mod",module);
		return  (Inscription) q.getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Inscription> listInscription() {
		Query req = em.createQuery("select i from Inscription i");
		return req.getResultList();
	}

	@Override
	public void editInscription(Inscription i) {
		em.merge(i);
	}

	@Override
	public void deleteInscription(String login, int idModule) {
		em.remove(this.getInscription(login,idModule));
	}

	@Override
	public List<Inscription> getListInscriptionByUser(String login) {
		List<Inscription> list = listInscription();
		List<Inscription> results = new ArrayList<>();
		
		for(Inscription i : list){
			boolean bool = i.getUser().getLogin().equals(login) ? true :false ;
			if(bool) results.add(i);
		}
		return results;
	}

	@Override
	public List<Inscription> getListInscriptionByModule(int idModule) {
		List<Inscription> list = listInscription();
		List<Inscription> results = new ArrayList<>();
		
		for(Inscription i : list){
			boolean bool = i.getModule().getId() == idModule ? true :false ;
			if(bool) results.add(i);
		}
		return results;
	}

	@Override
	public List<Inscription> getListInscription(String login, int idModule) {
		List<Inscription> list = getListInscriptionByUser(login);
		List<Inscription> results = new ArrayList<>();
		
		for(Inscription i : list){
			boolean bool = i.getModule().getId() == idModule ? true :false ;
			if(bool) results.add(i);
		}
		return results;
	}
	
	

}
