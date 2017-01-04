package ejb.dao.interfaces;

import java.util.List;

import javax.ejb.Local;

import ejb.entities.Inscription;

@Local
public interface IInscriptionDao {

	public void addInscription(Inscription i);
	public Inscription getInscription(String login, int idModule);
	public List<Inscription> listInscription();
	public void editInscription(Inscription i);
	public void deleteInscription(String login, int idModule);
	public List<Inscription> getListInscriptionByUser(String login);
	public List<Inscription> getListInscriptionByModule(int idModule);
	public List<Inscription> getListInscription(String login, int idModule);	
}
