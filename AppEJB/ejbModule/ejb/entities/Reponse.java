package ejb.entities;

import javax.persistence.ManyToOne;

public class Reponse {

	private String rep;
	private boolean bonneRep;
	
	@ManyToOne
	private QuestionReponse QCM;

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
	
	
}
