package ejb.dao.implementations;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ejb.dao.interfaces.IReponseDao;
import ejb.entities.Reponse;

@Stateless
public class ReponseDaoImpl implements IReponseDao {

	@PersistenceContext(unitName="monUnite")
	private EntityManager em;
	
	@Override
	public Reponse addReponse(Reponse r) {
		em.persist(r);
		return r;
	}

	
}