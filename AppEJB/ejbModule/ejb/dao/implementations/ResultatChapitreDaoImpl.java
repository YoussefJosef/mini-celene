package ejb.dao.implementations;

import java.util.ArrayList;
import java.util.List;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ejb.dao.interfaces.IResultatChapitreDao;
import ejb.entities.AccesChapter;
import ejb.entities.Chapitre;
import ejb.entities.ResultatChapitre;
import ejb.entities.User;
@Stateless
public class ResultatChapitreDaoImpl implements IResultatChapitreDao {
	
	@PersistenceContext(unitName="monUnite")
	private EntityManager em;

	@Override
	public void addResultatChapitre(ResultatChapitre rc) {
		em.persist(rc);
	}

	@Override
	public ResultatChapitre getResultatChapitre(String login, int idChapitre) {
		User user = em.find(User.class,login);
		Chapitre chapitre = em.find(Chapitre.class, idChapitre);
		
		Query q = em.createQuery("select rc from ResultatChapitre rc where rc.user  = :log and rc.chapitre= :chap", ResultatChapitre.class)
				.setParameter("log", user).setParameter("chap",chapitre);
		List<ResultatChapitre> l = q.getResultList();
		if(l.isEmpty()) return null;
		return  (ResultatChapitre) q.getResultList().get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ResultatChapitre> listResultatChapitre() {
		Query req = em.createQuery("select rc from ResultatChapitre rc");
		return req.getResultList();
	}

	@Override
	public void editResultatChapitre(ResultatChapitre rc) {
		em.merge(rc);
	}

	@Override
	public void deleteResultatChapitre(String login, int idChapitre) {
		em.remove(this.getResultatChapitre(login, idChapitre));
	}

	@Override
	public List<ResultatChapitre> getListResultatChapitreByChapitre(int idChapitre) {
		List<ResultatChapitre> list = listResultatChapitre();
		List<ResultatChapitre> results = new ArrayList<>();
		
		for(ResultatChapitre rc : list){
			boolean bool = rc.getChapitre().getId()== idChapitre ? true :false ;
			if(bool) results.add(rc);
		}
		return results;
	}

	@Override
	public List<ResultatChapitre> getListResultatChapitreByUser(String login) {
		List<ResultatChapitre> list = listResultatChapitre();
		List<ResultatChapitre> results = new ArrayList<>();
		
		for(ResultatChapitre rc : list){
			boolean bool = rc.getUser().getLogin().equals(login) ? true :false ;
			if(bool) results.add(rc);
		}
		return results;
	}

	@Override
	public List<ResultatChapitre> getListResultatChapitre(String login, int idChapitre) {
		List<ResultatChapitre> list = getListResultatChapitreByUser(login);
		List<ResultatChapitre> results = new ArrayList<>();
		
		for(ResultatChapitre rc : list){
			boolean bool = rc.getChapitre().getId()== idChapitre ? true :false ;
			if(bool) results.add(rc);
		}
		return results;
	}

}
