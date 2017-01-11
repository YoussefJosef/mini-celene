package ejb.metier.interfaces;

import javax.ejb.Local;

import ejb.dao.interfaces.IQuestionReponseDao;
import ejb.entities.Reponse;

@Local
public interface IQuestionReponseMetier extends IQuestionReponseDao {
	
	public int addQuestionReponse(int idChapitre,String question,int nbReponse,int score,String indication);
	public Reponse getReponseByIdQrAndStringReponse ( int idQuestionReponse, String reponse);
}
