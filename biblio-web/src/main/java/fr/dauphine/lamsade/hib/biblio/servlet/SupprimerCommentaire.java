package fr.dauphine.lamsade.hib.biblio.servlet;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.dauphine.lamsade.hib.biblio.service.inter.CommentaireServiceRemote;


/**
@author Sara BAHJAJI
 */

/**
 * Servlet de l'implementation de la classe SupprimerCommentaire
 */
@WebServlet("/SupprimerCommentaire")
public class SupprimerCommentaire extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private CommentaireServiceRemote commentaireService;
	private static Logger logger = Logger.getLogger(SupprimerCommentaire.class.getCanonicalName());
 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupprimerCommentaire() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			int identifiant = new Integer((String) request.getParameter("Identifiant"));
            commentaireService.remove(identifiant);
			response.sendRedirect("/biblio-web/ListerCommentaire");
		} catch (Exception e) {
			logger.log(Level.SEVERE,"Erreur d'appel EJB service",e);
		} 
	}

}
