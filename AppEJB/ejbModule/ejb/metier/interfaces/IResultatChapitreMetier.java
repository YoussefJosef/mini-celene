package ejb.metier.interfaces;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;

import ejb.dao.interfaces.IResultatChapitreDao;
import ejb.entities.Reponse;

@Local
public interface IResultatChapitreMetier extends IResultatChapitreDao {

	public void addResultatChapitre(String login,int idChapitre,int score,String datevalidation);

	public int getNoteQcm(List<String> reponseEtudiant, List<String> listQuestion);
	
	
}
