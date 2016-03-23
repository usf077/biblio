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

import fr.dauphine.lamsade.hib.biblio.modele.Evaluation;
import fr.dauphine.lamsade.hib.biblio.service.inter.EvaluationServiceRemote;

@WebServlet("/ListerEvaluation")
public class ListerEvaluation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	private EvaluationServiceRemote evaluationService;
	
	private static Logger logger = Logger.getLogger(AjouterEvaluation.class.getCanonicalName());
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListerEvaluation() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	
		try {
			response.setContentType("text/html");
			request.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<h1> Liste des Evaluation</h1>");
			out.println(
					"<table border='1px'> <tr> <td> identifiant </td><td> libelle </td> <td> source</td>  <td> type </td> </tr>");
			List<Evaluation> listEvaluation = evaluationService.ListEvaluation();
				for (Evaluation ev : listEvaluation) {
				out.append("<tr>");
				out.println("<h3>Identifiant: " + ev.getId_evaluation() + "</h3>" );
				out.println("<h3>Utilisateur: "+ ev.getUtilisateur().getNom() + " "+ev.getUtilisateur().getPrenom()+ "</h3>"  );
				out.println("<h3>Commentaire: "+ ev.getCommentaire().getCommentaire()  + "</h3>"  );
				out.println("<h3>Evaluation: " + ev.getEvaluation()  + "</h3>" );
				out.append("</tr>");
			}
			out.append("</table>");
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Erreur d'appel EJB service", e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
