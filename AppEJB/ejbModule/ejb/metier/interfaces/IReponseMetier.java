package ejb.metier.interfaces;

import javax.ejb.Local;

import ejb.dao.interfaces.IReponseDao;

@Local
public interface IReponseMetier extends IReponseDao {

	public void addReponse(int idQuestionReponse, String reponse, boolean bonneReponse);
	
}