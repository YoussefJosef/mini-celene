package ejb.metier.interfaces;

import javax.ejb.Local;

import ejb.dao.interfaces.IQuestionReponseDao;

@Local
public interface IQuestionReponseMetier extends IQuestionReponseDao {
	
	public int addQuestionReponse(int idChapitre,String question,int nbReponse);
}
