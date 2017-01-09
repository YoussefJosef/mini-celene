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
	public void addResultatChapitre(String login, int idChapitre, int score, String dateValidation) {
		User user = daoUser.getUser(login);
		Chapitre chapitre = daoChapitre.getChapitre(idChapitre);
		ResultatChapitre rc = new ResultatChapitre();
		rc.setUser(user);
		rc.setChapitre(chapitre);
		rc.setDateValidation(dateValidation);
		rc.setScore(score);
		rc.setNombreEssai(rc.getNombreEssai()+1);
		daoResultatChapitre.addResultatChapitre(rc);
		
	}


	@Override
	public void editResultatChapitreWithDate(String login, int idChapitre, int score, String dateValidation) {
		ResultatChapitre resultatChapitre = daoResultatChapitre.getResultatChapitre(login, idChapitre);
		resultatChapitre.setScore(score);
		resultatChapitre.setDateValidation(dateValidation);
		daoResultatChapitre.editResultatChapitre(resultatChapitre);
		
	}

	@Override
	public void editResultatChapitreWithoutDate(String login, int idChapitre, int score) {
		ResultatChapitre resultatChapitre = daoResultatChapitre.getResultatChapitre(login, idChapitre);
		resultatChapitre.setScore(score);
		daoResultatChapitre.editResultatChapitre(resultatChapitre);
		
	}
	}
