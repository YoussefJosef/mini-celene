package ejb.dto;

import ejb.entities.ResultatChapitre;

public class ResultatChapitreDto {

	private String login;
	private String nomChapitre;
	private int score;
	private int nombreEssai;
	private String dateValidation;
	private String validated;
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getNomChapitre() {
		return nomChapitre;
	}
	public void setNomChapitre(String nomChapitre) {
		this.nomChapitre = nomChapitre;
	}
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
	public String getValidated() {
		return validated;
	}
	public void setValidated(String validated) {
		this.validated = validated;
	}
	
	public ResultatChapitreDto(){
		super();
	}
	
	public ResultatChapitreDto(ResultatChapitre RC){
		this.login = RC.getUser().getLogin();
		this.nomChapitre = RC.getChapitre().getTitre();
		this.score = RC.getScore();
		this.nombreEssai = RC.getNombreEssai();
		this.dateValidation = RC.getDateValidation();
		if(RC.isValidated()){
			this.validated = "Chapitre validé";
		}
		else{
			this.validated = "en cours";
		}
	}
}
