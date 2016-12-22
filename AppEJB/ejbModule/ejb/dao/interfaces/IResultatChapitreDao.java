package ejb.dao.interfaces;

import java.util.List;

import javax.ejb.Local;

import ejb.entities.ResultatChapitre;

@Local
public interface IResultatChapitreDao {
	
	public ResultatChapitre addResultatChapitre(ResultatChapitre rc);
	public ResultatChapitre getResultatChapitre(int id);
	public List<ResultatChapitre> listResultatChapitre();
	public void editResultatChapitre(ResultatChapitre rc);
	public void deleteResultatChapitre(int id);

}
