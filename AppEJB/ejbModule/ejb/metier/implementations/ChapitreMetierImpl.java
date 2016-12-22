package ejb.metier.implementations;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ejb.dao.interfaces.IChapitreDao;
import ejb.dao.interfaces.IModuleDao;
import ejb.entities.Chapitre;
import ejb.entities.Module;
import ejb.metier.interfaces.IChapitreMetier;

@Stateless
public class ChapitreMetierImpl implements IChapitreMetier {
	
	@PersistenceContext(unitName="monUnite")
	private EntityManager em;
	
	@EJB
	IModuleDao daoModule ;
	@EJB
	IChapitreDao daoChapitre ;

	@Override
	public Chapitre addChapitre(Chapitre c) {
		return daoChapitre.addChapitre(c);
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
	public void deleteChapitre(int id) {
		daoChapitre.deleteChapitre(id);
	
	}
	
	@Override
	public void editChapitreById(int id, String titre, String texte,int scoreMin) {
		daoChapitre.editChapitreById(id, titre, texte, scoreMin);
		
	}
	
	public void addChapitre(int idModule, String titre, String texte, int niveau, int scoreMin) {
	Module module = daoModule.getModule(idModule);
	Chapitre chapitre = new Chapitre(module, titre, texte, scoreMin, niveau);
	em.persist(chapitre);
	
	}

	
	public List<Chapitre> getChapitres(int idModule) {
		List<Chapitre> list = daoChapitre.listChapitre();
		List<Chapitre> results = new ArrayList<>();
		
		for(Chapitre c : list){
			boolean myChapter = c.getModule().getId()== idModule ? true :false ;
			System.out.println(myChapter+"looool");
			if(myChapter) results.add(c);
			
		}
		return results;
	}

	

}
