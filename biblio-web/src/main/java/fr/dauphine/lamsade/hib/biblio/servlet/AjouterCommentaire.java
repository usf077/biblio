package fr.dauphine.lamsade.hib.biblio.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.dauphine.lamsade.hib.biblio.modele.BibliographieDTO;
import fr.dauphine.lamsade.hib.biblio.modele.Commentaire;
import fr.dauphine.lamsade.hib.biblio.modele.TypeBibliographieDTO;
import fr.dauphine.lamsade.hib.biblio.modele.Utilisateur;
import fr.dauphine.lamsade.hib.biblio.service.inter.BibliographieServiceRemote;
import fr.dauphine.lamsade.hib.biblio.service.inter.CommentaireServiceRemote;

/**
 * Servlet implementation class AjouterCommentaire
 */
@WebServlet("/AjouterCommentaire")
public class AjouterCommentaire extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private CommentaireServiceRemote commentaireService ;
	private static Logger logger = Logger.getLogger(AjouterCommentaire.class.getCanonicalName());
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjouterCommentaire() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Commentaire c = new Commentaire();
		Utilisateur u = new Utilisateur();
		BibliographieDTO b = new BibliographieDTO();
		try {
			response.setContentType("text/html");
			request.setCharacterEncoding("UTF-8");
			c.setCommentaire("Commenatire sur biblio");
			c.setDate_commentaire(new Date());
			u.setId_utilisateur(1);
			b.setIdentifiant(1);
			c.setUtilisateur(u);
			c.setBiblio(b);
			commentaireService.AjouterCommentaire(c);
			response.getWriter().append("Bibliographie a bien été ajouté");
		} catch (Exception e) {
			logger.log(Level.SEVERE,"Erreur d'appel EJB service",e);
		} 
	
	}

}
