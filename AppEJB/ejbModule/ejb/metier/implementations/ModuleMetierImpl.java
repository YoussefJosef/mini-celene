package ejb.metier.implementations;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ejb.dao.interfaces.IChapitreDao;
import ejb.dao.interfaces.IInscriptionDao;
import ejb.dao.interfaces.IModuleDao;
import ejb.dao.interfaces.IQuestionReponseDao;
import ejb.dao.interfaces.IReponseDao;
import ejb.dao.interfaces.IResultatChapitreDao;
import ejb.dao.interfaces.IUserDao;
import ejb.entities.Chapitre;
import ejb.entities.Inscription;
import ejb.entities.Module;
import ejb.entities.QuestionReponse;
import ejb.entities.Reponse;
import ejb.entities.ResultatChapitre;
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
	@EJB
	IChapitreDao daoChapitre;
	@EJB
	IInscriptionDao daoInscription;
	@EJB
	IReponseDao daoReponse;
	@EJB
	IQuestionReponseDao daoQuestionReponse;
	@EJB
	IResultatChapitreDao daoResultatChapitre;
	
	@Override
	public void addModule(Module m) {
		daoModule.addModule(m);
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
	public void deleteModule(int idModule) {
		List<Chapitre> listC = daoChapitre.getListChapitre(idModule);
		for(Chapitre c : listC){
			List<QuestionReponse> listQR = daoQuestionReponse.getListQuestionReponse(c.getId());
			for(QuestionReponse qr : listQR){
				List<Reponse> list = daoReponse.getListReponses(qr.getId());
				for(Reponse r : list){
					daoReponse.deleteReponse(r.getId());
				}
				daoQuestionReponse.deleteQuestionReponse(qr.getId());
			}
			List<ResultatChapitre> listRC = daoChapitre.getChapitre(c.getId()).getListResultatChapitres();
			for(ResultatChapitre rc : listRC){
				daoResultatChapitre.deleteResultatChapitre(rc.getUser().getLogin(), c.getId());
			}
			daoChapitre.deleteChapitre(c.getId());
		}
		List<Inscription> listI = daoInscription.getListInscriptionByModule(idModule);
		for(Inscription i : listI){
			daoInscription.deleteInscription(i.getUser().getLogin(), idModule);
		}
		daoModule.deleteModule(idModule);
	}
	
	@Override
	public List<Module> getListModule(String login) {
		return daoModule.getListModule(login);
	}

	@Override
	public void editModule(int idModule, String nom) {
		Module m = daoModule.getModule(idModule);
		m.setNom(nom);
		daoModule.editModule(m);
	}
	
	public void addModule(String nom,String login){
		User user = daoUser.getUser(login);
		Module m = new Module();
		m.setNom(nom);
		m.setUser(user);
		em.persist(m);
		
	}
}
