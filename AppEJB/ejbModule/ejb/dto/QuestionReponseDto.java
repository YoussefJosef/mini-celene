package ejb.dto;

import java.util.ArrayList;
import java.util.List;

import ejb.entities.QuestionReponse;
import ejb.entities.Reponse;

public class QuestionReponseDto {

	private int id;
	private String Question;
	private String Indication;
	private List<String> reponse;
	private int Score;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getQuestion() {
		return Question;
	}
	public void setQuestion(String question) {
		Question = question;
	}
	public List<String> getReponse() {
		return reponse;
	}
	public void setReponse(List<String> reponse) {
		this.reponse = reponse;
	}
	public int getScore() {
		return Score;
	}
	public void setScore(int score) {
		Score = score;
	}
	
	public QuestionReponseDto(){
		super();
	}
	public String getIndication() {
		return Indication;
	}
	public void setIndication(String indication) {
		Indication = indication;
	}
	
	public String getIndication() {
		return Indication;
	}
	public void setIndication(String indication) {
		Indication = indication;
	}
	public QuestionReponseDto(QuestionReponse QR){
		this.id = QR.getId();
		this.Question = QR.getQuestion();
		this.Indication= QR.getIndication();
		this.Score = QR.getScore();
		List<Reponse> R = QR.getReponse();
		List<String> reponse = new ArrayList<String>();
		for(Reponse r : R){
			reponse.add(r.getRep());
		}
		this.reponse = reponse;
	}

	public QuestionReponseDto getTrueAnswers(QuestionReponse QR){
		QuestionReponseDto qr = new QuestionReponseDto();
		qr.setId(QR.getId());
		qr.setQuestion(QR.getQuestion());
		qr.setIndication( QR.getIndication());
		qr.setScore(QR.getScore() );
		List<Reponse> listR = QR.getReponse();
		List<String> reponse = new ArrayList<String>();
		for(Reponse r : listR){
			if(r.isBonneRep())
			reponse.add(r.getRep());		
		}
		qr.setReponse(reponse);
		return qr;
	}

}
