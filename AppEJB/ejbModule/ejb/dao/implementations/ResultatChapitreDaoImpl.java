package ejb.dao.implementations;

import java.util.List;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ejb.dao.interfaces.IResultatChapitreDao;
import ejb.entities.ResultatChapitre;
@Stateless
public class ResultatChapitreDaoImpl implements IResultatChapitreDao {
	
	@PersistenceContext(unitName="monUnite")
	private EntityManager em;

	@Override
	public ResultatChapitre addResultatChapitre(ResultatChapitre rc) {
		em.persist(rc);
		return rc;
	}

	@Override
	public ResultatChapitre getResultatChapitre(int id) {
		ResultatChapitre rc =em.find(ResultatChapitre.class, id);
		return rc;
	}

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
	public void deleteResultatChapitre(int id) {
		em.remove(this.getResultatChapitre(id));

	}

}
