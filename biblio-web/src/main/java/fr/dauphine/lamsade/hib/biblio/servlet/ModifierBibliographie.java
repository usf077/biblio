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

import fr.dauphine.lamsade.hib.biblio.modele.BibliographieDTO;
import fr.dauphine.lamsade.hib.biblio.modele.TypeBibliographieDTO;
import fr.dauphine.lamsade.hib.biblio.service.inter.BibliographieServiceRemote;


/**
 * Author : Mohamed Youssef Errayhani 
 * Date : 29/03/2016
 */

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
		BibliographieDTO b = new BibliographieDTO();
		TypeBibliographieDTO tb = new TypeBibliographieDTO();
		try {
			String libelle =  (String) request.getParameter("Libelle") ;
		    String source = (String)  request.getParameter("Source");
		    int typeIdentifiant =   Integer.parseInt((String) request.getParameter("TypeIdentifiant"));
		    int identifiant =  Integer.parseInt((String) request.getParameter("Identifiant"));
			b.setIdentifiant(identifiant);
		    b.setSource(source);
			b.setLibelle(libelle);
			tb.setIdentifiant(typeIdentifiant);
			b.setTypeBibliographie(tb);
			bibliographieService.update(b);
			 response.sendRedirect("/biblio-web/ListerBibliographie");
		} catch (Exception e) {
			logger.log(Level.SEVERE,"Erreur d'appel EJB service",e);
		} 
	
	}

}
