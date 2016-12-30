package ejb.entities;

import java.io.Serializable;
import java.util.ArrayList;

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
	private ArrayList<String> reponse;
	private ArrayList<Integer> bonneReponse;
	private Integer numReponse;
	
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

	public ArrayList<String> getReponse() {
		return reponse;
	}

	public void setReponse(ArrayList<String> reponse) {
		this.reponse = reponse;
	}

	public ArrayList<Integer> getBonneReponse() {
		return bonneReponse;
	}

	public void setBonneReponse(ArrayList<Integer> bonneReponse) {
		this.bonneReponse = bonneReponse;
	}
	
	public Integer getNumReponse() {
		return numReponse;
	}

	public void setNumReponse(Integer numReponse) {
		this.numReponse = numReponse;
	}
}
