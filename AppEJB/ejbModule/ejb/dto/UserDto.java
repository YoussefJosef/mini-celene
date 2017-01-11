package ejb.dto;

import ejb.entities.User;

public class UserDto {

	private String login;
	private String password;
	private String role;
	private String nom;
	private String prenom;
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public UserDto(){
		super();
	}
	
	public UserDto(User u){
		this.login = u.getLogin();
		this.password = u.getPassword();
		switch(u.getRole()){
		case 1:
			this.role = "Administrateur";
			break;
		case 2:
			this.role = "Enseignant";
			break;
		case 3:
			this.role = "Etudiant";
			break;
		default:
			this.role = "inconnu";
		}
		this.nom = u.getNom();
		this.prenom = u.getPrenom();
	}
}
