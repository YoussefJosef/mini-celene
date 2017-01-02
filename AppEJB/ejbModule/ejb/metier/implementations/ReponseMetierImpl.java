package ejb.metier.implementations;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
	public void addReponse(int idQuestionReponse, String reponse, boolean bonneReponse) {
		Reponse r = new Reponse();
		r.setRep(reponse);
		r.setBonneRep(bonneReponse);
		r.setQCM(daoQuestionReponse.getQuestionReponse(idQuestionReponse));
		em.persist(r);		
	}

	@Override
	public Reponse addReponse(Reponse r) {
		return daoReponse.addReponse(r);
	}
	
	
}