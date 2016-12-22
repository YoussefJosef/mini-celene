package ejb.dao.interfaces;

import java.util.List;

import javax.ejb.Local;

import ejb.entities.Chapitre;

@Local
public interface IChapitreDao {
	
	public Chapitre addChapitre(Chapitre c);
	public Chapitre getChapitre(int id);
	public List<Chapitre> listChapitre();
	public void editChapitre(Chapitre c);
	public void deleteChapitre(int id);
	public void editChapitreById(int id,String titre, String texte,int scoreMin);
	
}
