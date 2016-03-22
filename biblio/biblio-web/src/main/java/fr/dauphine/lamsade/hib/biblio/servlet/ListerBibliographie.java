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
 * Servlet implementation class ListerBibliographie
 */
@WebServlet("/ListerBibliographie")
public class ListerBibliographie extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private BibliographieServiceRemote bibliographieService;
	private static Logger logger = Logger.getLogger(ListerBibliographie.class.getCanonicalName());

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListerBibliographie() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			response.setContentType("text/html");
			request.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<h1> Liste des bibliographies</h1>");
			out.println(
					"<table border='1px'> <tr> <td> identifiant </td><td> libelle </td> <td> source</td>  <td> type </td> </tr>");
			List<Bibliographie> lst = bibliographieService.ListBibliographie();
			for (Bibliographie bibliographie : lst) {
				out.append("<tr>");
				out.append("<td>" + bibliographie.getIdentifiant() + "</td>");
				out.append("<td>" + bibliographie.getLibelle() + "</td>");
				out.append("<td>" + bibliographie.getSource() + "</td>");
				out.append("<td>" + bibliographie.getTypeBibliographie().getLibelle() + "</td>");
				out.append("</tr>");
			}
			out.append("</table>");
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Erreur d'appel EJB service", e);
		}

	}

}
