package ejb.dao.interfaces;

import java.util.List;

import javax.ejb.Local;

import ejb.entities.QuestionReponse;

@Local
public interface IQuestionReponseDao {
	
	public QuestionReponse addQuestionReponse(QuestionReponse qr);
	public QuestionReponse getQuestionReponse(int id);
	public List<QuestionReponse> listQuestionReponse();
	public void editQuestionReponse(QuestionReponse qr);
	public void deleteQuestionReponse(int id);

}
