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

import fr.dauphine.lamsade.hib.biblio.service.inter.BibliographieServiceRemote;


/**
 * @author ERRAYHANI Mohamed Youssef
 * Date : 29/03/2016
 */

/**
 * Servlet implementation class SupprimerBibliographie
 */
@WebServlet("/SupprimerBibliographie")
public class SupprimerBibliographie extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private BibliographieServiceRemote bibliographieService;
	private static Logger logger = Logger.getLogger(SupprimerBibliographie.class.getCanonicalName());
 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupprimerBibliographie() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			int identifiant = new Integer((String) request.getParameter("Identifiant"));
			bibliographieService.remove(identifiant);
			response.sendRedirect("/biblio-web/ListerBibliographie");
		} catch (Exception e) {
			logger.log(Level.SEVERE,"Erreur d'appel EJB service",e);
		} 
	}

}
