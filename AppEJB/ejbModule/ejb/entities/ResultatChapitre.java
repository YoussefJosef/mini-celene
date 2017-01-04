package ejb.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class ResultatChapitre implements Serializable{
	

	private static final long serialVersionUID = 1L;

	@Id@ManyToOne
	private User user;
	
	@Id@ManyToOne
	private Chapitre chapitre;
	
	private int score;
	private int nombreEssai;
	private String dateValidation;

	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getNombreEssai() {
		return nombreEssai;
	}
	public void setNombreEssai(int nombreEssai) {
		this.nombreEssai = nombreEssai;
	}
	public String getDateValidation() {
		return dateValidation;
	}
	public void setDateValidation(String dateValidation) {
		this.dateValidation = dateValidation;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Chapitre getChapitre() {
		return chapitre;
	}
	public void setChapitre(Chapitre chapitre) {
		this.chapitre = chapitre;
	}
	

}
