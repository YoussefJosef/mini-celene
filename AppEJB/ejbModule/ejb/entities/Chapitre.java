package ejb.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
	
	private boolean printAnswers;


	@ManyToOne
	private Module module;
	
	@OneToMany(mappedBy="qcmChapitre", fetch=FetchType.EAGER)
	private List<QuestionReponse> Qcm;
	
	@OneToMany(mappedBy="chapitre", fetch=FetchType.EAGER)
	private List<ResultatChapitre> listResultatChapitres;
	
	@ManyToMany
	@JoinTable(name="ResultatChapitre",
	 joinColumns=@JoinColumn(name="CHAPITRE_ID", referencedColumnName="ID"),
     inverseJoinColumns=@JoinColumn(name="USER_LOGIN", referencedColumnName="LOGIN"))
	private List<User> etudiantsQcm;
	
	
	public Module getModule() {
		return module;
	}

	public List<ResultatChapitre> getListResultatChapitres() {
		return listResultatChapitres;
	}

	public void setListResultatChapitres(List<ResultatChapitre> listResultatChapitres) {
		this.listResultatChapitres = listResultatChapitres;
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

	public List<User> getEtudiantsQcm() {
		return etudiantsQcm;
	}

	public void setEtudiantsQcm(List<User> etudiantsQcm) {
		this.etudiantsQcm = etudiantsQcm;
	}

	public boolean isPrintAnswers() {
		return printAnswers;
	}

	public void setPrintAnswers(boolean printAnswers) {
		this.printAnswers = printAnswers;
	}
	
}
