package ejb.metier.interfaces;

import javax.ejb.Local;

import ejb.dao.interfaces.IReponseDao;
import ejb.entities.Reponse;

@Local
public interface IReponseMetier extends IReponseDao {

	public void addReponse(int idQuestionReponse, String reponse, boolean bonneReponse);

	public Reponse getReponseByRep(String reponse);
	
}