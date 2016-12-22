package ejb.metier.interfaces;

import java.util.List;

import javax.ejb.Local;

import ejb.dao.interfaces.IModuleDao;
import ejb.entities.Module;

@Local
public interface IModuleMetier extends IModuleDao {
	public void addModule(String nom,String login);
	public List<Module> getModules(String login);
}
