package ejb.dao.implementations;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ejb.dao.interfaces.IReponseDao;
import ejb.entities.Reponse;

@Stateless
public class ReponseDaoImpl implements IReponseDao {

	@PersistenceContext(unitName="monUnite")
	private EntityManager em;
	
	@Override
	public void addReponse(Reponse r) {
		em.persist(r);
	}
	
	@Override
	public Reponse getReponse(int id) {
		Reponse r =em.find(Reponse.class, id);
		return r;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Reponse> listReponse() {
		Query req = em.createQuery("select r from Reponse r");
		return req.getResultList();
	}
	
	@Override
	public void editReponse(Reponse r) {
		em.merge(r);		
	}
	
	@Override
	public void deleteReponse(int id) {
		em.remove(this.getReponse(id));

	}

	@Override
	public List<Reponse> getListReponses(int idQuestionReponse) {
		List<Reponse> list = listReponse();
		List<Reponse> results = new ArrayList<>();
		
		for(Reponse r : list){
			boolean bool = r.getQCM().getId()== idQuestionReponse ? true :false ;
			if(bool) results.add(r);
			
		}
		return results;
	}

	
}