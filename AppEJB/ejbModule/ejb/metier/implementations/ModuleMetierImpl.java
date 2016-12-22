package ejb.metier.implementations;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ejb.dao.interfaces.IModuleDao;
import ejb.dao.interfaces.IUserDao;
import ejb.entities.Module;
import ejb.entities.User;
import ejb.metier.interfaces.IModuleMetier;
;

@Stateless
public class ModuleMetierImpl implements IModuleMetier{

	@PersistenceContext(unitName="monUnite")
	private EntityManager em;
	
	@EJB
	IModuleDao daoModule ;
	@EJB
	IUserDao daoUser;
	
	@Override
	public Module addModule(Module m) {
		return daoModule.addModule(m);
	}
	
	@Override
	public Module getModule(int id) {
		return daoModule.getModule(id);
	}
	@Override
	public List<Module> listModule() {
		return daoModule.listModule();
	}
	@Override
	public void editModule(Module m) {
		daoModule.editModule(m);
		
	}
	@Override
	public void deleteModule(int id) {
		daoModule.deleteModule(id);
		
	}

	@Override
	public void editModuleNameById(int id, String nom) {
		daoModule.editModuleNameById(id, nom);
		
	}
	
	public void addModule(String nom,String login){
		User user = daoUser.getUser(login);
		Module m = new Module();
		m.setNom(nom);
		m.setUser(user);
		em.persist(m);
		
	}
	
	public List<Module> getModules(String login) {

		List<Module> list =daoModule.listModule();
		List<Module> results = new ArrayList<Module>();
		
		for(Module m : list){
			boolean myModule=m.getUser().getLogin().equals(login);
			if(myModule) results.add(m);
		}
		return results;
	}
}
