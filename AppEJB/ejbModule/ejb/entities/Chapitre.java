package ejb.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Chapitre implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue
	private int id;
	private String texte;
	private String titre;
	private int scoreMin;
	private int niveau;

	@ManyToOne
	private Module module;
	
	@OneToMany(mappedBy="qcmChapitre", fetch=FetchType.EAGER)
	private List<QuestionReponse> Qcm;
	
	@OneToMany(mappedBy="chapitre", fetch=FetchType.EAGER)
	private List<ResultatChapitre> listResultatChapitres;
	
	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}


	public List<QuestionReponse> getQcm() {
		return Qcm;
	}

	public void setQcm(List<QuestionReponse> qcm) {
		Qcm = qcm;
	}

	public int getScoreMin() {
		return scoreMin;
	}

	public void setScoreMin(int scoreMin) {
		this.scoreMin = scoreMin;
	}

	public String getTexte() {
		return texte;
	}

	public void setTexte(String texte) {
		this.texte = texte;
	}

	public int getNiveau() {
		return niveau;
	}

	public void setNiveau(int niveau) {
		this.niveau = niveau;
	}
	
}
