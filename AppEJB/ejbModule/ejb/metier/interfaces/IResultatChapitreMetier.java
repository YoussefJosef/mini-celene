package ejb.metier.interfaces;

import java.util.List;

import javax.ejb.Local;

import ejb.dao.interfaces.IResultatChapitreDao;
import ejb.entities.ResultatChapitre;

@Local
public interface IResultatChapitreMetier extends IResultatChapitreDao {

	public void addResultatChapitre(String login,int idChapitre,int score,String datevalidation,boolean validated);
	
	public void editResultatChapitreWithDate(String login,int idChapitre,int score,String dateValidation, boolean validated);
	
	public void editResultatChapitreWithoutDate(String login,int idChapitre,int score, boolean validated);

	public boolean checkIfUserModuleExist(String login, int idChapitre);
	

	public List<ResultatChapitre> getValidatedListResultatChapitreByUser(String login);
}
