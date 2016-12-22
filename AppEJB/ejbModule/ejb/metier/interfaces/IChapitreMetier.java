package ejb.metier.interfaces;

import java.util.List;

import javax.ejb.Local;

import ejb.dao.interfaces.IChapitreDao;
import ejb.entities.Chapitre;

@Local
public interface IChapitreMetier  extends IChapitreDao{
	public void addChapitre(int idModule,String titre,String texte,int niveau, int scoreMin);
	public List<Chapitre> getChapitres(int idModule);
}
