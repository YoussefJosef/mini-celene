package ejb.entities;


import java.io.Serializable;


import java.lang.String;
import java.util.List;

import javax.persistence.*;


@Entity

public class User implements Serializable {

	   
	@Id 
	private String login;
	private String password; 
	private int role;
	private String nom;
	private String prenom;

	@ManyToMany(mappedBy="etudiants",cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private List<Module> listModulesEtudiant;
	
	@OneToMany(mappedBy="user", fetch=FetchType.EAGER)
	private List<Module> listModules;
	
	@OneToMany(mappedBy="user", fetch=FetchType.EAGER)
	private List<ResultatChapitre> listResultats;
	
	
	private static final long serialVersionUID = 1L;

	public User() {
		super();
	}   
  
	public User(String login, String password, int role, String nom, String prenom) {
		super();
		this.login = login;
		this.password = password;
		this.role = role;
		this.nom = nom;
		this.prenom = prenom;
	}

    public User(String login, String password, int role) {
		super();
		this.login = login;
		this.password = password;
		this.role = role;
	}
	public int getRole() {
		return this.role;
	}

	public void setRole(int role) {
		this.role = role;
	}   
	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}   
	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
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

	public List<Module> getListModulesEtudiant() {
		return listModulesEtudiant;
	}

	public void setListModulesEtudiant(List<Module> listModulesEtudiant) {
		this.listModulesEtudiant = listModulesEtudiant;
	}

	public List<Module> getListModules() {
		return listModules;
	}

	public void setListModules(List<Module> listModules) {
		this.listModules = listModules;
	}

	public List<ResultatChapitre> getListResultats() {
		return listResultats;
	}

	public void setListResultats(List<ResultatChapitre> listResultats) {
		this.listResultats = listResultats;
	}
   
}
