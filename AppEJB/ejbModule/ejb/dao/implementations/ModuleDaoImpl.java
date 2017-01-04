package ejb.dao.implementations;

import java.util.ArrayList;
import java.util.List;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ejb.dao.interfaces.IModuleDao;
import ejb.entities.Module;
@Stateless
public class ModuleDaoImpl implements IModuleDao {
	
	@PersistenceContext(unitName="monUnite")
	private EntityManager em;

	@Override
	public void addModule(Module m) {
		em.persist(m);
	}

	@Override
	public Module getModule(int id) {
		Module m=em.find(Module.class, id);
		return m;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Module> listModule() {
		Query req = em.createQuery("select m from Module m");
		return req.getResultList();

	}

	@Override
	public void editModule(Module m) {
		em.merge(m);
	}

	@Override
	public void deleteModule(int id) {
		em.remove(this.getModule(id));
	}

	@Override
	public List<Module> getListModule(String login) {
		List<Module> list = listModule();
		List<Module> results = new ArrayList<>();
		
		for(Module m : list){
			boolean bool = m.getUser().getLogin().equals(login) ? true :false ;
			if(bool) results.add(m);
		}
		return results;
	}
}
