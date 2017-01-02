package ejb.dao.interfaces;

import javax.ejb.Local;

import ejb.entities.Reponse;

@Local
public interface IReponseDao {

	public Reponse addReponse(Reponse r);
}