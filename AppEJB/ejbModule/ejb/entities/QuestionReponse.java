package ejb.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class QuestionReponse implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id@GeneratedValue
	private int id;
	private String question;
	private String indication;
	private int score;

	@OneToMany(mappedBy="QCM",cascade=CascadeType.REMOVE, fetch=FetchType.EAGER)
	private List<Reponse> reponse;
	
	private Integer nbReponse;
	
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


	public List<Reponse> getReponse() {
		return reponse;
	}

	public void setReponse(List<Reponse> reponse) {
		this.reponse = reponse;
	}

	public Integer getNbReponse() {
		return nbReponse;
	}

	public void setNbReponse(Integer nbReponse) {
		this.nbReponse = nbReponse;
	}
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getIndication() {
		return indication;
	}

	public void setIndication(String indication) {
		this.indication = indication;
	}
}