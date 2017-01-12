package ejb.metier.interfaces;

import javax.ejb.Local;

import ejb.dao.interfaces.IChapitreDao;

@Local
public interface IChapitreMetier  extends IChapitreDao{

	public void addChapitre(int idModule,String titre,String texte, int scoreMin,boolean printAnswers);
	void editChapitreById(int idChapitre, String titre, String texte, int scoreMin,boolean printAnswers);

}
