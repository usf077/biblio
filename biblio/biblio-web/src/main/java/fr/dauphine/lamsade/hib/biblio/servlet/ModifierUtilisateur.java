package fr.dauphine.lamsade.hib.biblio.servlet;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import fr.dauphine.lamsade.hib.biblio.modele.Bibliographie;
//import fr.dauphine.lamsade.hib.biblio.modele.TypeBibliographie;
import fr.dauphine.lamsade.hib.biblio.modele.Utilisateur;
//import fr.dauphine.lamsade.hib.biblio.service.inter.BibliographieServiceRemote;
import fr.dauphine.lamsade.hib.biblio.service.inter.UtilisateurServiceRemote;

/**
 * Servlet implementation class ModifierUtilisateur
 */
@WebServlet("/ModifierUtilisateur")
public class ModifierUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtilisateurServiceRemote utilisateurService;
	private static Logger logger = Logger.getLogger(ModifierUtilisateur.class.getCanonicalName());
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifierUtilisateur() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Utilisateur u = new Utilisateur();
	
		try {
			response.setContentType("text/html");
			request.setCharacterEncoding("UTF-8");
			u.setAdresse("20 rue d alsace");
			utilisateurService.ModifierUtilisateur(u);
			response.getWriter().append("l utilisateur a bien �t� Modifi�");
		} catch (Exception e) {
			logger.log(Level.SEVERE,"Erreur d'appel EJB service",e);
		} 
	
	}


}
