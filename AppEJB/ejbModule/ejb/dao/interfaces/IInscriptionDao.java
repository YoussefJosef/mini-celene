package ejb.dao.interfaces;

import java.util.List;

import javax.ejb.Local;

import ejb.entities.Inscription;

@Local
public interface IInscriptionDao {

	public Inscription addInscription(String login, int idModule);
	public Inscription getInscription(String login, int idModule);
	public List<Inscription> listInscription();
	public void editInscription(String login, int idModule);
	public void deleteInscription(String login, int idModule);
	
}
