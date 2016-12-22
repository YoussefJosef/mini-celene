package ejb.metier.implementations;

import java.util.ArrayList;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ejb.dao.interfaces.IInscriptionDao;
import ejb.dao.interfaces.IModuleDao;
import ejb.dao.interfaces.IUserDao;
import ejb.entities.Inscription;
import ejb.entities.Module;
import ejb.entities.User;
import ejb.metier.interfaces.IInscriptionMetier;

@Stateless
public class InscriptionMetierImpl implements IInscriptionMetier{

	@PersistenceContext(unitName="monUnite")
	private EntityManager em;
	
	@EJB
	IModuleDao daoModule ;
	@EJB
	IUserDao daoUser;
	@EJB
	IInscriptionDao daoInscription;
	
	@Override
	public Inscription addInscription(String login, int idModule) {
		return daoInscription.addInscription(login, idModule);
	}

	@Override
	public Inscription getInscription(String login, int idModule) {
		
		return daoInscription.getInscription(login, idModule);
	}

	@Override
	public List<Inscription> listInscription() {
		return daoInscription.listInscription();
	}

	@Override
	public void editInscription(String login, int idModule) {
		daoInscription.editInscription(login, idModule);
	}

	@Override
	public void deleteInscription(String login, int idModule){
		daoInscription.deleteInscription(login, idModule);
		
	}

/*	public List<Inscription> getModules(String login) {

		List<Inscription> list =daoInscription.listInscription();
		List<Inscription> results = new ArrayList<Inscription>();
		
		for(Inscription m : list){
			boolean myModule=m.getUser().getLogin().equals(login);
			if(myModule) results.add(m);
		}
		return results;
	}
	*/
	public List<Module> getModules(String login){
		User user = daoUser.getUser(login);
		List<Module> listModules = new ArrayList<>();
		listModules= user.getListModulesEtudiant();
		return listModules;
	}
	
	public List<Module> getOtherModules(String login){
		List<Module> allModules = daoModule.listModule();
		List<Module> studentsModules = this.getModules(login);
	
		allModules.removeAll(studentsModules);
		return allModules;
	}
	
}
