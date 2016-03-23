package fr.dauphine.lamsade.hib.biblio.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.dauphine.lamsade.hib.biblio.modele.Bibliographie;
import fr.dauphine.lamsade.hib.biblio.service.inter.BibliographieServiceRemote;

/**
 * Servlet implementation class LireBibliographie
 */
@WebServlet("/LireBibliographie")
public class LireBibliographie extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private BibliographieServiceRemote bibliographieService;
	private static Logger logger = Logger.getLogger(ModifierBibliographie.class.getCanonicalName());

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LireBibliographie() {
        super();   
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			response.setContentType("text/html");
			request.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<h1> Lire la bibliographie dont lideintifiant = 1</h1>");
			
			Bibliographie b = bibliographieService.getBibliographie(1);
			out.println("<h3>Identifiant: " + b.getIdentifiant() + "</h3>" );
			out.println("<h3>Libelle: "+ b.getLibelle() + "</h3>"  );
			out.println("<h3>Source: "+ b.getSource()  + "</h3>"  );
			out.println("<h3>Type: " + b.getTypeBibliographie().getLibelle()  + "</h3>" );
			
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Erreur d'appel EJB service", e);
		}
	}

	
}
