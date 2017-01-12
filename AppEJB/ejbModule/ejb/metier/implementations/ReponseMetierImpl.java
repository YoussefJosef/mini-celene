package ejb.metier.implementations;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ejb.dao.interfaces.IQuestionReponseDao;
import ejb.dao.interfaces.IReponseDao;
import ejb.entities.Reponse;
import ejb.metier.interfaces.IReponseMetier;

@Stateless
public class ReponseMetierImpl implements IReponseMetier{

	@PersistenceContext(unitName="monUnite")
	private EntityManager em;
	
	@EJB
	IReponseDao daoReponse;

	@EJB
	IQuestionReponseDao daoQuestionReponse ;
	

	@Override
	public void addReponse(Reponse r) {
		daoReponse.addReponse(r);		
	}

	@Override
	public Reponse getReponse(int idReponse) {
		return daoReponse.getReponse(idReponse);
	}

	@Override
	public List<Reponse> listReponse() {
		return daoReponse.listReponse();
	}

	@Override
	public void deleteReponse(int idReponse) {
		daoReponse.deleteReponse(idReponse);
	}

	@Override
	public void editReponse(Reponse r) {
		daoReponse.editReponse(r);
	}

	@Override
	public List<Reponse> getListReponses(int idQuestionReponse) {
		return daoReponse.getListReponses(idQuestionReponse);
	}
	
	@Override
	public void addReponse(int idQuestionReponse, String reponse, boolean bonneReponse) {
		Reponse r = new Reponse();
		r.setRep(reponse);
		r.setBonneRep(bonneReponse);
		r.setQCM(daoQuestionReponse.getQuestionReponse(idQuestionReponse));
		em.persist(r);		
	}
	
	@Override
	public Reponse getReponseByRep(String reponse){
		Query q = em.createQuery("select r from Reponse r where r.rep  = :rep , Reponse.class")
				.setParameter("rep", reponse);
		return  (Reponse) q.getSingleResult();
		
	}
	
}