package ejb.dao.implementations;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ejb.dao.interfaces.IAccesChapterDao;
import ejb.entities.AccesChapter;

@Stateless
public class AccesChapterDaoImpl implements IAccesChapterDao {

	@PersistenceContext(unitName="monUnite")
	private EntityManager em;

	@Override
	public void addAccesChapter(AccesChapter m) {
		em.persist(m);
	}

	@Override
	public AccesChapter getAccesChapter(int id) {
		AccesChapter m=em.find(AccesChapter.class, id);
		return m;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AccesChapter> listAccesChapter() {
		Query req = em.createQuery("select a from AccesChapter a");
		return req.getResultList();

	}

	@Override
	public void editAccesChapter(AccesChapter m) {
		em.merge(m);
	}

	@Override
	public void deleteAccesChapter(int id) {
		em.remove(this.getAccesChapter(id));
	}

	@Override
	public List<AccesChapter> getListAccesChapter(String login) {
		List<AccesChapter> list = listAccesChapter();
		List<AccesChapter> results = new ArrayList<>();
		
		for(AccesChapter a : list){
			boolean bool = a.getUser().getLogin().equals(login) ? true :false ;
			if(bool) results.add(a);
		}
		return results;
	}
}
