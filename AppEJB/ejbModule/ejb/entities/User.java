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
	
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private List<Module> listModules;
	
	@ManyToMany(mappedBy="etudiantsQcm",cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private List<Chapitre> listResultats;
	
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private List<AccesChapter> listAccesChapters;
	
	private static final long serialVersionUID = 1L;

	public List<Chapitre> getListResultats() {
		return listResultats;
	}

	public void setListResultats(List<Chapitre> listResultats) {
		this.listResultats = listResultats;
	}

	public List<AccesChapter> getListAccesChapters() {
		return listAccesChapters;
	}

	public void setListAccesChapters(List<AccesChapter> listAccesChapters) {
		this.listAccesChapters = listAccesChapters;
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


	
   
}
