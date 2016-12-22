package ejb.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class QuestionReponse implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id@GeneratedValue
	private int id;
	private String question;
	private String reponse;
	private String bonneReponse;
	
	@ManyToOne
	private Chapitre qcmChapitre;

	public Chapitre getQcmChapitre() {
		return qcmChapitre;
	}

	public void setQcmChapitre(Chapitre qcmChapitre) {
		this.qcmChapitre = qcmChapitre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getReponse() {
		return reponse;
	}

	public void setReponse(String reponse) {
		this.reponse = reponse;
	}

	public String getBonneReponse() {
		return bonneReponse;
	}

	public void setBonneReponse(String bonneReponse) {
		this.bonneReponse = bonneReponse;
	}
}
