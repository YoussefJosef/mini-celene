package ejb.metier.interfaces;

import java.util.List;

import javax.ejb.Local;

import ejb.dao.interfaces.IAccesChapterDao;
import ejb.entities.AccesChapter;
import ejb.entities.Chapitre;
import ejb.entities.ResultatChapitre;

@Local
public interface IAccesChapterMetier extends IAccesChapterDao {
		public void addAccesChapter(String login,int idChapitre);

		List<Chapitre> getAuthorizedChapters(String login, int idModule);

		List<Chapitre> getAccesListChapter(List<AccesChapter> listA, List<Chapitre> listC);

		List<Chapitre> getChaptersList(List<ResultatChapitre> listRC, List<Chapitre> listC);

		void updateAccesChapterList(String login, int idModule, int idChapitre);
}
