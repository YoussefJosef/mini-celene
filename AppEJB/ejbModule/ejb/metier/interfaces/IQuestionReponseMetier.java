package ejb.metier.interfaces;

import java.util.List;

import javax.ejb.Local;

import ejb.dao.interfaces.IQuestionReponseDao;
import ejb.entities.QuestionReponse;

@Local
public interface IQuestionReponseMetier extends IQuestionReponseDao {
	
	public int addQuestionReponse(int idChapitre,String question,int nbReponse);
	public List<QuestionReponse> getQuestionsReponses(int idChapitre);

}
