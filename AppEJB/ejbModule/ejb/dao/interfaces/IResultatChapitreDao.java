package ejb.dao.interfaces;

import java.util.List;

import javax.ejb.Local;

import ejb.entities.ResultatChapitre;

@Local
public interface IResultatChapitreDao {
	
	public void addResultatChapitre(ResultatChapitre rc);
	public ResultatChapitre getResultatChapitre(String login, int idChapitre);
	public List<ResultatChapitre> listResultatChapitre();
	public void editResultatChapitre(ResultatChapitre rc);
	public void deleteResultatChapitre(String login, int idChapitre);
	public List<ResultatChapitre> getListResultatChapitreByChapitre(int idChapitre);
	public List<ResultatChapitre> getListResultatChapitreByUser(String login);
	public List<ResultatChapitre> getListResultatChapitre(String login, int idChapitre);
}
