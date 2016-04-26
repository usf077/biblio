package fr.dauphine.lamsade.hib.biblio.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import fr.dauphine.lamsade.hib.biblio.modele.Auteur;
import fr.dauphine.lamsade.hib.biblio.modele.Bibliographie;
import fr.dauphine.lamsade.hib.biblio.modele.TypeBibliographie;
import fr.dauphine.lamsade.hib.biblio.service.inter.BibliographieServiceRemote;
import fr.dauphine.lamsade.hib.biblio.service.inter.TypeBibliographieServiceRemote;
import fr.dauphine.lamsade.hib.biblio.util.PaginationHelper;

@ManagedBean(name = "bibiographieBean")
@SessionScoped
public class BibliographieBean {
	
	@EJB
	private BibliographieServiceRemote bibliographieService;
	@EJB
	private TypeBibliographieServiceRemote typebibliographieService;
	private static Logger logger = Logger.getLogger(BibliographieBean.class.getCanonicalName());
	private PaginationHelper pagination;
	private DataModel dtmdl = null;
	private int searchSelectectedType =0;
	private String searchTxt;
	public List<TypeBibliographie> typeBibliographies;
	private Bibliographie biblio ;
	//private Auteur currentAuteur;
	private String currentAuteurFirstName;
	private String currentAuteurLastName;
	

	public void getListeBibliographie()
	{
		
	}
	
	
	 public DataModel getDtmdl() {
		return dtmdl;
	}


	public void setDtmdl(DataModel dtmdl) {
		this.dtmdl = dtmdl;
	}


	public int getSearchSelectectedType() {
		return searchSelectectedType;
	}


	public void setSearchSelectectedType(int searchSelectectedType) {
		this.searchSelectectedType = searchSelectectedType;
	}



	public String getSearchTxt() {
		return searchTxt;
	}



	public void setSearchTxt(String searchTxt) {
		this.searchTxt = searchTxt;
	}



	public List<TypeBibliographie> getTypeBibliographies() {
		typeBibliographies = typebibliographieService.fetch();
		return typeBibliographies;
	}



	public void setTypeBibliographies(List<TypeBibliographie> typeBibliographies) {
		this.typeBibliographies = typeBibliographies;
	}



	public void setPagination(PaginationHelper pagination) {
		this.pagination = pagination;
	}



	public Bibliographie getBiblio() {
		return biblio;
	}


	public void setBiblio(Bibliographie biblio) {
		this.biblio = biblio;
	}


	public String getCurrentAuteurFirstName() {
		return currentAuteurFirstName;
	}


	public void setCurrentAuteurFirstName(String currentAuteurFirstName) {
		this.currentAuteurFirstName = currentAuteurFirstName;
	}


	public String getCurrentAuteurLastName() {
		return currentAuteurLastName;
	}


	public void setCurrentAuteurLastName(String currentAuteurLastName) {
		this.currentAuteurLastName = currentAuteurLastName;
	}


	public PaginationHelper getPagination() {
         FacesContext facesContext = FacesContext.getCurrentInstance();
         ExternalContext externalContext = facesContext.getExternalContext();        
         String referrer = externalContext.getRequestHeaderMap().get("referer"); 

        if (pagination == null) {

            pagination = new PaginationHelper(10) {
                @Override
                public int getItemsCount() {
                    return bibliographieService.count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(bibliographieService.findSearchRestrictedList(searchTxt,searchSelectectedType,getPageFirstItem(), getPageSize(), "idBiblio", "ASC") );
                }
            };
        }
        return pagination;
    }

	 public String Search()
	    {
	        dtmdl = getPagination().createPageDataModel();
	        return "app-main";
	    }
	 
	 public void onCreateLoad() {
	        if (!FacesContext.getCurrentInstance().isPostback()) {
	           biblio = new Bibliographie();
	        }
	    }

	    public void onCreateStep2Load()
	    {
	        if (!FacesContext.getCurrentInstance().isPostback()) {
	           biblio.setAuteurs(new ArrayList<>()); 
	        }
	    }
	    
	 public String AddAuteur(){	
		 Auteur auteur = new Auteur();
		 auteur.setNom(currentAuteurLastName);
		 auteur.setPrenom(currentAuteurFirstName);
		 biblio.getAuteurs().add(auteur);
		 currentAuteurFirstName=null;
		 currentAuteurLastName=null;
		 return null;
	 }
	 
	 public String RemoveAuteur(int item){
		 System.out.println("######## " + item);
		 biblio.getAuteurs().remove(item);
		 return null;
	 }
	 public String CreateBiblio(){
		 return "createStep2";
	 }
	 
	 
    public DataModel getdtmdl() {
        if (dtmdl == null) {
            dtmdl = getPagination().createPageDataModel();
        }
        return dtmdl;
    }


    private void recreateModel() {
        dtmdl = null;
    }
    private void recreatePagination() {
        pagination = null;
    }
    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "home";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "home";
    }
    public String goToPage(int page) {
        getPagination().setPage(page - 1);
        recreateModel();
        return "home";
    }
    
    public String init(){
        
        searchTxt=null;
        searchSelectectedType=0;
        dtmdl=null;
        return "app-main";
    }
    
   

}
