package ejb.metier.interfaces;

import javax.ejb.Local;

import ejb.dao.interfaces.IUserDao;

@Local
public interface IUserMetier extends IUserDao{

	public int getRole(String login);
	public boolean checkCredentials(String login,String mdp);
	void addUser(String login, String password, int role, String nom, String prenom);
	void editUser(String login, String password, int role, String nom, String prenom);
	
}
