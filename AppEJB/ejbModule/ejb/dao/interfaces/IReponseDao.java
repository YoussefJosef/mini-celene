package ejb.dao.interfaces;

import java.util.List;

import javax.ejb.Local;

import ejb.entities.Reponse;

@Local
public interface IReponseDao {

	public void addReponse(Reponse r);
	public Reponse getReponse(int id);
	public List<Reponse> listReponse();
	public void deleteReponse(int id);
	public void editReponse(Reponse r);
	public List<Reponse> getListReponses(int idQuestionReponse);
}