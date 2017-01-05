package ejb.dao.implementations;

import java.util.ArrayList;
import java.util.List;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ejb.dao.interfaces.IChapitreDao;
import ejb.entities.Chapitre;
@Stateless
public class ChapitreDaoImpl implements IChapitreDao {
	
	@PersistenceContext(unitName="monUnite")
	private EntityManager em;
	
	@Override
	public void addChapitre(Chapitre c) {
		em.persist(c);
	}

	@Override
	public Chapitre getChapitre(int id) {
		Chapitre c =em.find(Chapitre.class, id);
		return c;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Chapitre> listChapitre() {
		Query req =em.createQuery("select c from Chapitre c");
		return req.getResultList();
	}

	@Override
	public void editChapitre(Chapitre c) {
		em.merge(c);
	}

	@Override
	public void deleteChapitre(int id) {
		em.remove(this.getChapitre(id));
	}

	@Override
	public List<Chapitre> getListChapitre(int idModule) {
		List<Chapitre> list = listChapitre();
		List<Chapitre> results = new ArrayList<>();
		
		for(Chapitre c : list){
			boolean bool = c.getModule().getId()== idModule ? true :false ;
			if(bool) results.add(c);
		}
		return results;
	}
}
