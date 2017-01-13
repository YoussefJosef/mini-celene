package ejb.metier.interfaces;

import java.util.List;

import ejb.dao.interfaces.IInscriptionDao;
import ejb.entities.Module;

public interface IInscriptionMetier extends IInscriptionDao {
	
	public List<Module> getModules(String login) ;
	public List<Module> getOtherModules(String login);
	void addInscription(String login, int idModule, String dateInscription);
	void editInscription(String login, int idModule, String dateInscription);
	void updateProgression(String login, int idModule);
}
