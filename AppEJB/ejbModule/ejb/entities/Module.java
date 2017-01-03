package ejb.entities;

import java.io.Serializable;


import java.util.List;

import javax.persistence.*;


@Entity
public class Module implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)

	private int id;
	private String nom;

	@OneToMany(mappedBy="module", fetch=FetchType.EAGER)
	private List<Chapitre> listChapitres;
	
	@ManyToMany
	@JoinTable(name="Inscription",
	 joinColumns=@JoinColumn(name="MODULE_ID", referencedColumnName="ID"),
     inverseJoinColumns=@JoinColumn(name="USER_LOGIN", referencedColumnName="LOGIN"))
	private List<User> etudiants;
	
	@ManyToOne
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Chapitre> getListChapitres() {
		return listChapitres;
	}

	public void setListChapitres(List<Chapitre> listChapitres) {
		this.listChapitres = listChapitres;
	}

	public Module() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
}
