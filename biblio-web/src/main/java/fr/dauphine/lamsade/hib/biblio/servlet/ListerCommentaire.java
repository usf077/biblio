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

import fr.dauphine.lamsade.hib.biblio.modele.CommentaireDTO;
import fr.dauphine.lamsade.hib.biblio.service.inter.CommentaireServiceRemote;

/**
 * Servlet implementation class ListerBibliographie
 */
@WebServlet("/ListerCommentaire")
public class ListerCommentaire extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private CommentaireServiceRemote commentaireService;
	private static Logger logger = Logger.getLogger(ListerCommentaire.class.getCanonicalName());

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListerCommentaire() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			request.getSession(true).setAttribute("TableCommentaires", commentaireService.fetch());
	        response.sendRedirect("/biblio-web/Commentaire/ListeCommentaires.jsp");
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Erreur d'appel EJB service", e);
		}

	}

}