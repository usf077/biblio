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

import fr.dauphine.lamsade.hib.biblio.modele.Bibliographie;
import fr.dauphine.lamsade.hib.biblio.modele.TypeBibliographie;
import fr.dauphine.lamsade.hib.biblio.service.inter.BibliographieServiceRemote;

/**
 * Servlet implementation class ModifierBibliographie
 */
@WebServlet("/ModifierBibliographie")
public class ModifierBibliographie extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private BibliographieServiceRemote bibliographieService;
	private static Logger logger = Logger.getLogger(ModifierBibliographie.class.getCanonicalName());

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifierBibliographie() {
        super();
     }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Bibliographie b = new Bibliographie();
		TypeBibliographie tb = new TypeBibliographie();
		try {
			response.setContentType("text/html");
			request.setCharacterEncoding("UTF-8");
			b.setSource("www.biblioModifier.fr");
			b.setLibelle("Biblio webographie Modifier");
			tb.setIdentifiant(1);
			b.setTypeBibliographie(tb);
			bibliographieService.ModifierBibliographie(b);
			response.getWriter().append("Bibliographie a bien �t� Modifi�");
		} catch (Exception e) {
			logger.log(Level.SEVERE,"Erreur d'appel EJB service",e);
		} 
	
	}

}
