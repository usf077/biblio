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

import fr.dauphine.lamsade.hib.biblio.modele.CommentaireDTO;
import fr.dauphine.lamsade.hib.biblio.service.inter.CommentaireServiceRemote;

/** 
 @author Sara BAHJAJI
 **/
//Servlet pour l'implementation de la classe ModifierCommentaire


@WebServlet("/ModifierCommentaire")
public class ModifierCommentaire extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private CommentaireServiceRemote commentaireService;
	private static Logger logger = Logger.getLogger(ModifierBibliographie.class.getCanonicalName());
     
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifierCommentaire() {
        super();
     }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CommentaireDTO c = new CommentaireDTO();
	
		try {
			String comm =  (String) request.getParameter("Commentaire") ; 
			
		    int identifiant =  Integer.parseInt((String) request.getParameter("Identifiant"));
			c.setCommentaire(comm);
			c.setIdentifiant(identifiant);
			commentaireService.update(c);
			 response.sendRedirect("/biblio-web/ListerCommentaire");
		} catch (Exception e) {
			logger.log(Level.SEVERE,"Erreur d'appel EJB service",e);
		} 
	
	}

}
