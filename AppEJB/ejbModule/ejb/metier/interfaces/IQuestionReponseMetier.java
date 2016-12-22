package ejb.metier.interfaces;

import java.util.List;

import javax.ejb.Local;

import ejb.dao.interfaces.IQuestionReponseDao;
import ejb.entities.QuestionReponse;

@Local
public interface IQuestionReponseMetier extends IQuestionReponseDao {
	
	public void addQuestionReponse(int idChapitre,String question,String reponse,String bonneReponse);
	public List<QuestionReponse> getQuestionReponseById(int idChapitre);
	


}
