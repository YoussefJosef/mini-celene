package ejb.metier.implementations;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ejb.dao.interfaces.IChapitreDao;
import ejb.dao.interfaces.IQuestionReponseDao;
import ejb.dao.interfaces.IReponseDao;
import ejb.entities.QuestionReponse;
import ejb.entities.Reponse;
import ejb.metier.interfaces.IQuestionReponseMetier;

@Stateless
public class QuestionReponseMetierImpl implements IQuestionReponseMetier {
	
	@PersistenceContext(unitName="monUnite")
	private EntityManager em;
	
	@EJB
	IChapitreDao daoChapitre;
	@EJB
	IQuestionReponseDao daoQuestionReponse;
	@EJB
	IReponseDao daoReponse;

	@Override
	public void addQuestionReponse(QuestionReponse qr) {
		daoQuestionReponse.addQuestionReponse(qr);
	}

	@Override
	public QuestionReponse getQuestionReponse(int idQuestionReponse) {
		return daoQuestionReponse.getQuestionReponse(idQuestionReponse);
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
	public void deleteQuestionReponse(int idQuestionReponse) {
//		List<Reponse> list = daoReponse.getListReponses(idQuestionReponse);
//		for(Reponse r : list){
//			daoReponse.deleteReponse(r.getId());
//		}
		daoQuestionReponse.deleteQuestionReponse(idQuestionReponse);
	}
	
	@Override
	public List<QuestionReponse> getListQuestionReponse(int idChapitre) {
		return daoQuestionReponse.getListQuestionReponse(idChapitre);
	}

	@Override
	public int addQuestionReponse(int idChapitre, String question, int nbReponse, int score, String indication) {
		QuestionReponse qr = new QuestionReponse();
		qr.setQuestion(question);
		qr.setNbReponse(nbReponse);
		qr.setScore(score);
		qr.setIndication(indication);
		qr.setQcmChapitre(daoChapitre.getChapitre(idChapitre));
		em.persist(qr);	
		return qr.getId();
	}

	@Override
	public Reponse getReponseByIdQrAndStringReponse(int idQuestionReponse, String reponseEtudiant) {
	
		QuestionReponse questionReponse = em.find(QuestionReponse.class,idQuestionReponse);

		Query q = em.createQuery("select r from Reponse r where r.QCM  = :QR and r.rep= :rep",
				Reponse.class)
				.setParameter("QR",questionReponse).setParameter("rep",reponseEtudiant);
		
		return  (Reponse) q.getResultList().get(0);
		
	}	

}
