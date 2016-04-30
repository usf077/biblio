/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.dauphine.lamsade.hib.biblio.beans;

import fr.dauphine.lamsade.hib.biblio.modele.Bibliographie;
import fr.dauphine.lamsade.hib.biblio.modele.Commentaire;
import fr.dauphine.lamsade.hib.biblio.modele.TypeBibliographie;
import fr.dauphine.lamsade.hib.biblio.modele.Utilisateur;
import fr.dauphine.lamsade.hib.biblio.service.inter.BibliographieServiceRemote;
import fr.dauphine.lamsade.hib.biblio.service.inter.CommentaireServiceRemote;
import fr.dauphine.lamsade.hib.biblio.util.PaginationHelper;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author ERRAYHANI Mohamed Youssef
 */
@ManagedBean(name = "CommentaireBean")
@SessionScoped
public class CommentaireBean {

    @EJB
    private CommentaireServiceRemote commentaireService;
    
    @EJB
    private BibliographieServiceRemote bibliographieService;

    private static Logger logger = Logger.getLogger(CommentaireBean.class.getCanonicalName());
    private PaginationHelper pagination;
    private DataModel dtmdl = null;
    
    private int idCurrentBiblio;

    private Bibliographie CurrentBibliographie;
    
    private String commentaire;


	/**
     * Creates a new instance of AdministrationBean
     */
    public CommentaireBean() {

    }
    
    public void init() {
    	if (!FacesContext.getCurrentInstance().isPostback()) {
		commentaire = null;
		dtmdl = null;	
    	}
	}
       
    public Bibliographie getCurrentBibliographie() {
    	 FacesContext context = FacesContext.getCurrentInstance();
    	try {
    		CurrentBibliographie = bibliographieService.findByIdAndFetchAuteurs(idCurrentBiblio);
		}  catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error loading bibliographie!",
                    "Unexpected error when loadin the biblio.  Please contact the system Administrator");
            context.addMessage(null, message);
            logger.log(Level.SEVERE,
                    "Unable to load the bibliographie",
                    e);
        }
    	
  		return CurrentBibliographie;
  	}

  	public void setCurrentBibliographie(Bibliographie currentBibliographie) {
  		CurrentBibliographie = currentBibliographie;
  	}
  	
    public int getIdCurrentBiblio() {
		return idCurrentBiblio;
	}

	public void setIdCurrentBiblio(int idCurrentBiblio) {
		this.idCurrentBiblio = idCurrentBiblio;
	}
	
	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {
                @Override
                public int getItemsCount() {
                    return commentaireService.count(idCurrentBiblio);
                }
                @Override
                public DataModel createPageDataModel() {

                    return new ListDataModel(commentaireService.findByIdBiblio(idCurrentBiblio, getPageFirstItem(), getPageSize()));
                }
            };
        }
        return pagination;
    }

    private void recreateModel() {
        dtmdl = null;
    }

    public String AddNewComment(){
    	FacesContext context = FacesContext.getCurrentInstance();
		try {
			Commentaire comm = new Commentaire();
			Utilisateur user = (Utilisateur) context.getExternalContext().getSessionMap()
					.get(UsersBean.USER_SESSION_KEY);
			comm.setCommentaire(commentaire);
			comm.setDateCommentaire(new Date());
			comm.setUtilisateur(user);
			comm.setBibliographie(CurrentBibliographie);
			
			commentaireService.add(comm);
			recreateModel();
			commentaire = null;

		} catch (Exception e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error creating the commentaire!",
					"Unexpected error when creating the biblio.  Please contact the system Administrator");
			context.addMessage(null, message);
			logger.log(Level.SEVERE, "Unable to create the new commentaire", e);
		}
		return null;
    }
    public DataModel getdtmdl() {
        if (dtmdl == null) {
            dtmdl = getPagination().createPageDataModel();
        }
        return dtmdl;
    }

    public String Remove(int idComm){
    	FacesContext context = FacesContext.getCurrentInstance();
		try {
			commentaireService.remove(idComm);
    	recreateModel();
		} catch(Exception e) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error removing the commentaire!",
						"Unexpected error when removing the commentaire.  Please contact the system Administrator");
				context.addMessage(null, message);
				logger.log(Level.SEVERE, "Unable to remove the commentaire", e);
		}
    	recreateModel();
    	return null;
    }
    
    public String UpdateComment(int idComm){
    	FacesContext context = FacesContext.getCurrentInstance();
		try {
		Commentaire c=	commentaireService.findById(idComm);
		c.setCommentaire(commentaire);
		commentaireService.update(c);
    	recreateModel();
		} catch(Exception e) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error updating the commentaire!",
						"Unexpected error when updating the commentaire.  Please contact the system Administrator");
				context.addMessage(null, message);
				logger.log(Level.SEVERE, "Unable to update the commentaire", e);
		}
		commentaire=null;
		recreateModel();
		return null;
    }
    public String Edit(){
    	((Commentaire)this.dtmdl.getRowData()).setEditable(true); 
    	commentaire = ((Commentaire)this.dtmdl.getRowData()).getCommentaire();
    	return null;
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
        //  getPagination().nextPage();        
        recreateModel();
        return "home";
    }
    public String Detail(int identifiant) {
       // commande = commandeService.getCommandeById(identifiant);
        return "detail3";
    }
    public String Comment(int idBiblio) {
		idCurrentBiblio = idBiblio;
		return "comment";
	}
  
   
}
