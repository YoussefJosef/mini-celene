package ejb.metier.interfaces;

import javax.ejb.Local;

import ejb.dao.interfaces.IModuleDao;

@Local
public interface IModuleMetier extends IModuleDao {
	public void addModule(String nom,String login);
	void editModule(int idModule, String nom);
}
