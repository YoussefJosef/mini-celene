package ejb.dto;

import ejb.entities.Inscription;

public class InscriptionDto {

	private String login;
	private String module;
	private int progression;
	private String dateInscription;
	private int idModule;
	
	
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public int getIdModule() {
		return idModule;
	}
	public void setIdModule(int idModule) {
		this.idModule = idModule;
	}
	public String getDateInscription() {
		return dateInscription;
	}
	public void setDateInscription(String dateInscription) {
		this.dateInscription = dateInscription;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public int getProgression() {
		return progression;
	}
	public void setProgression(int progression) {
		this.progression = progression;
	}
	
	public InscriptionDto(){
		super();
	}
	
	public InscriptionDto(Inscription i){
		this.login = i.getUser().getLogin();
		this.module = i.getModule().getNom();
		this.progression = i.getProgression();
		this.dateInscription = i.getDateInscription();
		this.idModule = i.getModule().getId();
	}
}
