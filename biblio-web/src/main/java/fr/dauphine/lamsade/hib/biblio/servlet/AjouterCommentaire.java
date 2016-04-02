package fr.dauphine.lamsade.hib.biblio.servlet;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.dauphine.lamsade.hib.biblio.modele.BibliographieDTO;
import fr.dauphine.lamsade.hib.biblio.modele.CommentaireDTO;
import fr.dauphine.lamsade.hib.biblio.modele.UtilisateurDTO;
import fr.dauphine.lamsade.hib.biblio.service.inter.BibliographieServiceRemote;
import fr.dauphine.lamsade.hib.biblio.service.inter.CommentaireServiceRemote;

/**
 * Servlet implementation class AjouterCommentaire
 * @author Sara BAHJAJI
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		CommentaireDTO c = new CommentaireDTO();
		UtilisateurDTO u = new UtilisateurDTO();
		BibliographieDTO b = new BibliographieDTO();
		try {
			response.setContentType("text/html");
			request.setCharacterEncoding("UTF-8");
			
			String comm= request.getParameter("commentaire");
			c.setCommentaire(comm);
			c.setDate_commentaire(new Date());
			u.setIdentifiant(1);
			b.setIdentifiant(2);
			c.setUtilisateur(u);
			c.setBiblio(b);
			commentaireService.add(c);
			
			response.sendRedirect("/biblio-web/ListerCommentaire");
		} catch (Exception e) {
			logger.log(Level.SEVERE,"Erreur d'appel EJB service",e);
		} 
	
	}

}
