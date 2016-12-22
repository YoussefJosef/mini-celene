package ejb.dao.implementations;

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
	public Chapitre addChapitre(Chapitre c) {
		em.persist(c);
		return c;
	}

	@Override
	public Chapitre getChapitre(int id) {
		Chapitre c =em.find(Chapitre.class, id);
		return c;
	}

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
	public void editChapitreById(int id, String titre, String texte, int scoreMin) {
		Chapitre chapitre = getChapitre(id);
		chapitre.setTitre(titre);
		chapitre.setTexte(texte);
		chapitre.setScoreMin(scoreMin);
		
	}

}
