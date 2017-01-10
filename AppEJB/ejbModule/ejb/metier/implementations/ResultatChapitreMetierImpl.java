package ejb.metier.implementations;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ejb.dao.interfaces.IChapitreDao;
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
import ejb.metier.interfaces.IChapitreMetier;
import ejb.metier.interfaces.IReponseMetier;
import ejb.metier.interfaces.IResultatChapitreMetier;

@Stateless
public class ResultatChapitreMetierImpl implements IResultatChapitreMetier  {

	@PersistenceContext(unitName="monUnite")
	private EntityManager em;
	
	@EJB
	IUserDao daoUser;
	@EJB
	IChapitreDao daoChapitre;
	@EJB
	IResultatChapitreDao daoResultatChapitre ;
	@EJB
	IQuestionReponseDao daoQuestionReponse;
	@EJB
	IReponseMetier metierReponse;
	@EJB
	IChapitreMetier metierChapitre;
	@EJB
	IReponseDao daoReponse;
	
	@Override
	public void addResultatChapitre(ResultatChapitre rc) {
		daoResultatChapitre.addResultatChapitre(rc);
		
	}

	@Override
	public ResultatChapitre getResultatChapitre(String login, int idChapitre) {
		return daoResultatChapitre.getResultatChapitre(login, idChapitre);
	}

	@Override
	public List<ResultatChapitre> listResultatChapitre() {
		return daoResultatChapitre.listResultatChapitre();
			
	}

	@Override
	public void editResultatChapitre(ResultatChapitre rc) {
		daoResultatChapitre.editResultatChapitre(rc);
		
	}

	@Override
	public void deleteResultatChapitre(String login, int idChapitre) {
		daoResultatChapitre.deleteResultatChapitre(login, idChapitre);
		
	}

	@Override
	public List<ResultatChapitre> getListResultatChapitreByChapitre(int idChapitre) {
		return daoResultatChapitre.getListResultatChapitreByChapitre(idChapitre);
		
	}

	@Override
	public List<ResultatChapitre> getListResultatChapitreByUser(String login) {
		return daoResultatChapitre.getListResultatChapitreByUser(login);
	}

	@Override
	public List<ResultatChapitre> getListResultatChapitre(String login, int idChapitre) {
		return daoResultatChapitre.getListResultatChapitre(login, idChapitre);
	}

	@Override
	public void addResultatChapitre(String login, int idChapitre, int score, String dateValidation,boolean validated) {
		User user = daoUser.getUser(login);
		Chapitre chapitre = daoChapitre.getChapitre(idChapitre);
		ResultatChapitre rc = new ResultatChapitre();
		rc.setUser(user);
		rc.setChapitre(chapitre);
		rc.setDateValidation(dateValidation);
		rc.setScore(score);
		rc.setNombreEssai(rc.getNombreEssai()+1);
		rc.setValidated(validated);
		daoResultatChapitre.addResultatChapitre(rc);
		
	}


	@Override
	public void editResultatChapitreWithDate(String login, int idChapitre, int score, String dateValidation,boolean validated) {
		ResultatChapitre rc = daoResultatChapitre.getResultatChapitre(login, idChapitre);
		rc.setScore(score);
		rc.setDateValidation(dateValidation);
		rc.setNombreEssai(rc.getNombreEssai()+1);
		rc.setValidated(validated);
		daoResultatChapitre.editResultatChapitre(rc);
		
	}

	@Override
	public void editResultatChapitreWithoutDate(String login, int idChapitre, int score,boolean validated) {
		ResultatChapitre rc = daoResultatChapitre.getResultatChapitre(login, idChapitre);
		rc.setScore(score);
		rc.setNombreEssai(rc.getNombreEssai()+1);
		rc.setValidated(validated);
		daoResultatChapitre.editResultatChapitre(rc);
		
	}
	
	@Override
	public boolean checkIfUserModuleExist(String login, int idChapitre){
		boolean exist = false ;
		
		ArrayList<ResultatChapitre> listRC = new ArrayList<ResultatChapitre>();
		listRC = (ArrayList<ResultatChapitre>) this.listResultatChapitre();
		
		if(!(listRC.isEmpty()))
		for(ResultatChapitre rc : listRC){
			System.out.println(rc.getUser().getLogin());
			if (rc.getChapitre().getId() == idChapitre && rc.getUser().getLogin().equals(login)){
				exist = true;
				break;
			}
		}
		return exist ;
	}

	@Override
	public List<ResultatChapitre> getValidatedListResultatChapitreByUser(String login) {
		List<ResultatChapitre> list = listResultatChapitre();
		List<ResultatChapitre> results = new ArrayList<>();
		
		for(ResultatChapitre rc : list){
			boolean bool = rc.getUser().getLogin().equals(login) && rc.isValidated() ? true :false ;
			if(bool) results.add(rc);
		}
		
		return results;
	}
}
