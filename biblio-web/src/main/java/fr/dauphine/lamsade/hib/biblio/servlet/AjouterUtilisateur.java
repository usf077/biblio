package fr.dauphine.lamsade.hib.biblio.servlet;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

//import javax.ejb.EJB;
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
 * Servlet implementation class AjouterUtilisateur
 */
@WebServlet("/AjouterUtilisateur")
public class AjouterUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	private UtilisateurServiceRemote utilisateurService ;
	private static Logger logger = Logger.getLogger(AjouterUtilisateur.class.getCanonicalName());
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjouterUtilisateur() {
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
			u.setAdresse("01 rue de paris");
			u.setId_utilisateur(1);
			u.setMail("dupont@gmail.com");
			u.setMot_de_passe("123");
			u.setNom("dupont");
			u.setPrenom("martin");
			u.setTelephone("0612345678");
			utilisateurService.AjouterUtilisateur(u);
			
			response.getWriter().append("l utilisateur a bien �t� ajout�");
		} catch (Exception e) {
			logger.log(Level.SEVERE,"Erreur d'appel EJB service",e);
		} 
		
		
		
	}

	

}
