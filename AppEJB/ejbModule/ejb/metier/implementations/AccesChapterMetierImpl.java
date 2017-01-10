package ejb.metier.implementations;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ejb.dao.interfaces.IAccesChapterDao;
import ejb.dao.interfaces.IUserDao;
import ejb.entities.AccesChapter;
import ejb.entities.Chapitre;
import ejb.entities.ResultatChapitre;
import ejb.entities.User;
import ejb.metier.interfaces.IAccesChapterMetier;
import ejb.metier.interfaces.IChapitreMetier;
import ejb.metier.interfaces.IResultatChapitreMetier;

@Stateless
public class AccesChapterMetierImpl implements IAccesChapterMetier{

	@PersistenceContext(unitName="monUnite")
	private EntityManager em;
	
	@EJB
	IAccesChapterDao daoAccesChapter ;
	@EJB
	IUserDao daoUser;
	@EJB
	IChapitreMetier metierC;
	@EJB
	IResultatChapitreMetier metierRC;
	@Override
	public void addAccesChapter(AccesChapter a) {
		daoAccesChapter.addAccesChapter(a);
		
	}

	@Override
	public AccesChapter getAccesChapter(int id) {
		return daoAccesChapter.getAccesChapter(id);
	}

	@Override
	public List<AccesChapter> listAccesChapter() {
		return daoAccesChapter.listAccesChapter();
	}

	@Override
	public void editAccesChapter(AccesChapter a) {
		daoAccesChapter.editAccesChapter(a);
		
	}

	@Override
	public void deleteAccesChapter(int id) {
		daoAccesChapter.deleteAccesChapter(id);
		
	}

	@Override
	public List<AccesChapter> getListAccesChapter(String login) {
		return daoAccesChapter.getListAccesChapter(login);
	}

	@Override
	public void addAccesChapter(String login, int idChapitre) {
		User user = daoUser.getUser(login);
		AccesChapter a = new AccesChapter();
		a.setIdChapitre(idChapitre);
		a.setUser(user);
		em.persist(a);
		
	}
	@Override
	public List<Chapitre> getAuthorizedChapters(String login,int idModule){
			
			List<AccesChapter> listA = this.getListAccesChapter(login);
			List<Chapitre> listC =  metierC.getListChapitre(idModule);
			
			return getAccesListChapter(listA,listC);
		}
	
	@Override
	public List<Chapitre> getAccesListChapter(List<AccesChapter> listA,List<Chapitre> listC){
			List<Chapitre> myListC = new ArrayList<Chapitre>();
			for(Chapitre c : listC){
				for(AccesChapter a : listA){
					if( a.getIdChapitre() == c.getId() ){
						myListC.add(c);
						break;
					}
				}
			}
			return myListC;
		}
	
	@Override
	public void updateAccesChapterList(String login,int idModule,int idChapitre){
	int newIdM=	metierC.getChapitre(idChapitre).getModule().getId();
		List<Chapitre> listCm = metierC.getListChapitre(newIdM);
		//List<ResultatChapitre> listRC =metierRC.getValidatedListResultatChapitreByUser(login);
	//	List<Chapitre> listCf = this.getChaptersList(listRC, listCm);
		System.out.println("idM :"+idModule+"idChap : " +idChapitre+"newidm:"+newIdM);
		for(int j = 0 ; j < listCm.size() ;++j)
		System.out.println("idChap: "+listCm.get(j).getId());
		for(int i = 0 ; i < listCm.size()-1 ; ++i){
			if(listCm.get(i).getId() == idChapitre)
				 addAccesChapter(login,listCm.get(i+1).getId() );
		}
		
	}

	@Override
	public List<Chapitre> getChaptersList(List<ResultatChapitre> listRC,List<Chapitre> listC){
			List<Chapitre> myListC = new ArrayList<Chapitre>();
			for(Chapitre c : listC){
				for(ResultatChapitre a : listRC){
					if( a.getChapitre().getId() == c.getId() ){
						myListC.add(c);
						break;
					}
				}
			}
			return myListC;
		}
	
}
