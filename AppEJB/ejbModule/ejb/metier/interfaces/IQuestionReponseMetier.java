package ejb.metier.interfaces;

import java.util.List;

import javax.ejb.Local;

import ejb.dao.interfaces.IQuestionReponseDao;
import ejb.entities.QuestionReponse;
import ejb.entities.Reponse;

@Local
public interface IQuestionReponseMetier extends IQuestionReponseDao {
	
	public QuestionReponse addQuestionReponse(int idChapitre,String question,int nbReponse);
	public List<QuestionReponse> getQuestionReponseById(int idChapitre);
	


}
