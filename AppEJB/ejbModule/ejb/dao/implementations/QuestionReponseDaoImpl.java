package ejb.dao.implementations;

import java.util.List;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ejb.dao.interfaces.IQuestionReponseDao;
import ejb.entities.QuestionReponse;
@Stateless
public class QuestionReponseDaoImpl implements IQuestionReponseDao {
	
	@PersistenceContext(unitName="monUnite")
	private EntityManager em;

	@Override
	public QuestionReponse addQuestionReponse(QuestionReponse qr) {
		em.persist(qr);
		return qr;
	}

	@Override
	public QuestionReponse getQuestionReponse(int id) {
		QuestionReponse qr =em.find(QuestionReponse.class, id);
		return qr;
	}

	@Override
	public List<QuestionReponse> listQuestionReponse() {
		Query req = em.createQuery("select qr from QuestionReponse qr");
		return req.getResultList();
	}

	@Override
	public void editQuestionReponse(QuestionReponse qr) {
		em.merge(qr);

	}

	@Override
	public void deleteQuestionReponse(int id) {
		em.remove(this.getQuestionReponse(id));

	}

}
