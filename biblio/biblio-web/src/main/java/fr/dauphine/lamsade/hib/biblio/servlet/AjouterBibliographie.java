package fr.dauphine.lamsade.hib.biblio.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.dauphine.lamsade.hib.biblio.modele.Bibliographie;
import fr.dauphine.lamsade.hib.biblio.modele.TypeBibliographie;
import fr.dauphine.lamsade.hib.biblio.service.inter.BibliographieServiceRemote;

/**
 * Servlet implementation class AjouterBibliographie
 */
@WebServlet("/AjouterBibliographie")
public class AjouterBibliographie extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private BibliographieServiceRemote bibliographieService ;
	private static Logger logger = Logger.getLogger(AjouterBibliographie.class.getCanonicalName());
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjouterBibliographie() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {

		Bibliographie b = new Bibliographie();
		TypeBibliographie tb = new TypeBibliographie();
		try {
			b.setSource("www.biblio.fr");
			b.setLibelle("Biblio webographie");
			tb.setIdentifiant(1);
			b.setTypeBibliographie(tb);
			bibliographieService.AjouterBibliographie(b);
			response.getWriter().append("Bibliographie a bien été ajouté");
		} catch (Exception e) {
			logger.log(Level.SEVERE,"Erreur d'appel EJB service",e);
		} 
		
	}

}
