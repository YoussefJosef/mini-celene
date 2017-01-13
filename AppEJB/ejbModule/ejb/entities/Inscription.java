package ejb.entities;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class Inscription implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id @ManyToOne
	private User user;
	@Id @ManyToOne
	private Module module;
	
	private int progression;
	private String dateInscription;

	public int getProgression() {
		return progression;
	}
	public void setProgression(int progression) {
		this.progression = progression;
	}
	public String getDateInscription() {
		return dateInscription;
	}
	public void setDateInscription(String dateInscription) {
		this.dateInscription = dateInscription;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Module getModule() {
		return module;
	}
	public void setModule(Module module) {
		this.module = module;
	}
	
}