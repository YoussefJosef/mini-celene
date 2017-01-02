package ejb.metier.implementations;

import java.util.ArrayList;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ejb.dao.interfaces.IChapitreDao;
import ejb.dao.interfaces.IQuestionReponseDao;
import ejb.entities.QuestionReponse;
import ejb.metier.interfaces.IQuestionReponseMetier;

@Stateless
public class QuestionReponseMetierImpl implements IQuestionReponseMetier {
	
	@PersistenceContext(unitName="monUnite")
	private EntityManager em;
	
	@EJB
	IChapitreDao daoChapitre ;
	@EJB
	IQuestionReponseDao daoQuestionReponse ;

	
	@Override
	public QuestionReponse addQuestionReponse(int idChapitre, String question, int nbReponse) {
		QuestionReponse qr = new QuestionReponse();
		qr.setQuestion(question);
		qr.setNbReponse(nbReponse);
		qr.setQcmChapitre(daoChapitre.getChapitre(idChapitre));
		em.persist(qr);
		return qr ;
		
	}

	@Override
	public List<QuestionReponse> getQuestionReponseById(int idChapitre) {
		
		List<QuestionReponse> list = daoQuestionReponse.listQuestionReponse();
		List<QuestionReponse> result = new ArrayList<>();
		
		for(QuestionReponse qr : list){
			if(qr.getQcmChapitre().getId() == idChapitre){
				result.add(qr);
			}
		}
		return result;
	}

	@Override
	public QuestionReponse addQuestionReponse(QuestionReponse qr) {
		return daoQuestionReponse.addQuestionReponse(qr);
	}

	@Override
	public QuestionReponse getQuestionReponse(int id) {
		return daoQuestionReponse.getQuestionReponse(id);
	}

	@Override
	public List<QuestionReponse> listQuestionReponse() {
		return daoQuestionReponse.listQuestionReponse();
	}

	@Override
	public void editQuestionReponse(QuestionReponse qr) {
		daoQuestionReponse.editQuestionReponse(qr);
		
	}

	@Override
	public void deleteQuestionReponse(int id) {
		daoQuestionReponse.deleteQuestionReponse(id);

	}

	
}
