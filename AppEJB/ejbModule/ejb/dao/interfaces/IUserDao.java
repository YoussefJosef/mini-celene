package ejb.dao.interfaces;

import java.util.List;

import javax.ejb.Local;

import ejb.entities.User;

@Local
public interface IUserDao {

	public void addUser(User u);
	public User getUser(String login);
	public List<User> listUser();
	public void editUser(User u);
	public void deleteUser(String login);
}
