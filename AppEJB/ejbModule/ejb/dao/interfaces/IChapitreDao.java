package ejb.dao.interfaces;

import java.util.List;

import javax.ejb.Local;

import ejb.entities.Chapitre;

@Local
public interface IChapitreDao {
	
	public void addChapitre(Chapitre c);
	public Chapitre getChapitre(int id);
	public List<Chapitre> listChapitre();
	public void editChapitre(Chapitre c);
	public void deleteChapitre(int id);
	public List<Chapitre> getListChapitre(int idModule);
	
}
