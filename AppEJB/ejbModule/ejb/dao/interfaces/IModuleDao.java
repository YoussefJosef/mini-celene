package ejb.dao.interfaces;

import java.util.List;

import javax.ejb.Local;

import ejb.entities.Module;

@Local
public interface IModuleDao {
	
	public void addModule(Module m);
	public Module getModule(int id);
	public List<Module> listModule();
	public void editModule(Module m);
	public void deleteModule(int id);
	public List<Module> getListModule(String login);
}
