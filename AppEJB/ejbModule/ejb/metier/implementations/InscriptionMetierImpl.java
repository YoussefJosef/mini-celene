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
	public void addInscription(Inscription i) {
		daoInscription.addInscription(i);
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
	public void editInscription(Inscription i) {
		daoInscription.editInscription(i);
	}

	@Override
	public void deleteInscription(String login, int idModule) {
		daoInscription.deleteInscription(login, idModule);
	
	}
	
	@Override
	public List<Inscription> getListInscription(String login, int idModule) {
		return daoInscription.getListInscription(login, idModule);
	}
	
	@Override
	public List<Inscription> getListInscriptionByUser(String login) {
		return daoInscription.getListInscriptionByUser(login);
	}
	
	@Override
	public List<Inscription> getListInscriptionByModule(int idModule) {
		return daoInscription.getListInscriptionByModule(idModule);
	}
	
	@Override
	public void addInscription(String login, int idModule, int progression) {
		User user = daoUser.getUser(login);
		Module module = daoModule.getModule(idModule);
		Inscription i = new Inscription();
		i.setUser(user);
		i.setModule(module);
		i.setProgression(progression);
		daoInscription.addInscription(i);
	}

	@Override
	public void editInscription(String login, int idModule, int progression) {
		Inscription i = daoInscription.getInscription(login, idModule);
		i.setProgression(progression);
		daoInscription.editInscription(i);
	}

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
