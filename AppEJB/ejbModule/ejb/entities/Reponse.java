package ejb.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
@Entity
public class Reponse implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue
	private int id;
	private String rep;
	private boolean bonneRep;
	
	@ManyToOne
	private QuestionReponse QCM;
	
	public String toString(){
		return "id : "+id+"rep:"+ rep+"bool:"+bonneRep ;
	}

	public String getRep() {
		return rep;
	}

	public void setRep(String rep) {
		this.rep = rep;
	}

	public boolean isBonneRep() {
		return bonneRep;
	}

	public void setBonneRep(boolean bonneRep) {
		this.bonneRep = bonneRep;
	}

	public QuestionReponse getQCM() {
		return QCM;
	}

	public void setQCM(QuestionReponse qCM) {
		QCM = qCM;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}