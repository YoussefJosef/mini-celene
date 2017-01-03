package ejb.metier.interfaces;

import ejb.dao.interfaces.IReponseDao;

public interface IReponseMetier extends IReponseDao {

	public void addReponse(int idQuestionReponse, String reponse, boolean bonneReponse);
}
