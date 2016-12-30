package ejb.metier.interfaces;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;

import ejb.dao.interfaces.IQuestionReponseDao;
import ejb.entities.QuestionReponse;

@Local
public interface IQuestionReponseMetier extends IQuestionReponseDao {
	
	public void addQuestionReponse(int idChapitre,String question,ArrayList<String> reponse,ArrayList<Integer> bonneReponse, int numReponse);
	public List<QuestionReponse> getQuestionReponseById(int idChapitre);
	


}
