package fr.dauphine.lamsade.hib.biblio.servlet;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import fr.dauphine.lamsade.hib.biblio.service.inter.BibliographieServiceRemote;
import fr.dauphine.lamsade.hib.biblio.service.inter.UtilisateurServiceRemote;

/**
 * Servlet implementation class SupprimerUtilisateur
 */
@WebServlet("/SupprimerUtilisateur")
public class SupprimerUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private UtilisateurServiceRemote UtilisateurService;
	private static Logger logger = Logger.getLogger(SupprimerUtilisateur.class.getCanonicalName());
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupprimerUtilisateur() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			response.setContentType("text/html");
			request.setCharacterEncoding("UTF-8");
			UtilisateurService.SupprimerUtilisateur(1);
			response.getWriter().append("l utilisateur avec l'identifiant 1 a bien �t� supprim�");
		} catch (Exception e) {
			logger.log(Level.SEVERE,"Erreur d'appel EJB service",e);
		} 
		}

	

}
