package ejb.metier.implementations;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ejb.dao.interfaces.IChapitreDao;
import ejb.dao.interfaces.IModuleDao;
import ejb.dao.interfaces.IQuestionReponseDao;
import ejb.dao.interfaces.IReponseDao;
import ejb.dao.interfaces.IResultatChapitreDao;
import ejb.entities.Chapitre;
import ejb.entities.Module;
import ejb.entities.QuestionReponse;
import ejb.entities.Reponse;
import ejb.entities.ResultatChapitre;
import ejb.metier.interfaces.IChapitreMetier;

@Stateless
public class ChapitreMetierImpl implements IChapitreMetier {
	
	@PersistenceContext(unitName="monUnite")
	private EntityManager em;
	
	@EJB
	IModuleDao daoModule ;
	@EJB
	IChapitreDao daoChapitre ;
	@EJB
	IQuestionReponseDao daoQuestionReponse;
	@EJB
	IResultatChapitreDao daoResultatChapitre;
	@EJB
	IReponseDao daoReponse;

	@Override
	public void addChapitre(Chapitre c) {
		daoChapitre.addChapitre(c);
	}

	@Override
	public Chapitre getChapitre(int id) {
		return daoChapitre.getChapitre(id);
	}

	@Override
	public List<Chapitre> listChapitre() {
		return daoChapitre.listChapitre();
	}

	@Override
	public void editChapitre(Chapitre c) {
		daoChapitre.editChapitre(c);
	}

	@Override
	public void deleteChapitre(int idChapitre) {
//		List<QuestionReponse> listQR = daoQuestionReponse.getListQuestionReponse(idChapitre);
//		
//		for(QuestionReponse qr : listQR){
//			List<Reponse> list = daoReponse.getListReponses(qr.getId());
//			for(Reponse r : list){
//				daoReponse.deleteReponse(r.getId());
//				System.out.println("R");
//			}
//			daoQuestionReponse.deleteQuestionReponse(qr.getId());
//			System.out.println("QR");
//		}
//		List<ResultatChapitre> listRC = daoResultatChapitre.getListResultatChapitreByChapitre(idChapitre);
//		for(ResultatChapitre rc : listRC){
//			em.remove(rc);
//		}
//		System.out.println("RC");
//		
//		if(em.find(Chapitre.class, idChapitre) != null){
//			daoChapitre.deleteChapitre(idChapitre);	
//			System.out.println("in if");
//		}
//		else
//		System.out.println("out if");
		daoChapitre.deleteChapitre(idChapitre);
	}
	
	@Override
	public List<Chapitre> getListChapitre(int idModule) {
		return daoChapitre.getListChapitre(idModule);
	}
	

	public void addChapitre(int idModule, String titre, String texte, int scoreMin,boolean printAnswers) {

		Module module = daoModule.getModule(idModule);
		Chapitre c = new Chapitre();
		c.setModule(module);
		c.setTitre(titre);
		c.setTexte(texte);
		c.setScoreMin(scoreMin);	
		c.setPrintAnswers(printAnswers);
		daoChapitre.addChapitre(c);
	}
	
	@Override
	public void editChapitreById(int idChapitre, String titre, String texte,int scoreMin,boolean printAnswers) {


		Chapitre c = daoChapitre.getChapitre(idChapitre);
		c.setTitre(titre);
		c.setTexte(texte);
		c.setScoreMin(scoreMin);
		c.setPrintAnswers(printAnswers);
		daoChapitre.editChapitre(c);
	}	
}
