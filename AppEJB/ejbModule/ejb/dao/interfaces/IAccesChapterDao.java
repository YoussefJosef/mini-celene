package ejb.dao.interfaces;

import java.util.List;

import javax.ejb.Local;

import ejb.entities.AccesChapter;

@Local
public interface IAccesChapterDao {

	public void addAccesChapter(AccesChapter m);
	public AccesChapter getAccesChapter(int id);
	public List<AccesChapter> listAccesChapter();
	public void editAccesChapter(AccesChapter m);
	public void deleteAccesChapter(int id);
	public List<AccesChapter> getListAccesChapter(String login);
}
