package fr.dauphine.lamsade.hib.biblio.servlet;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.dauphine.lamsade.hib.biblio.modele.Commentaire;
import fr.dauphine.lamsade.hib.biblio.modele.Evaluation;
import fr.dauphine.lamsade.hib.biblio.modele.Utilisateur;
import fr.dauphine.lamsade.hib.biblio.service.inter.EvaluationServiceRemote;

/**
 * Servlet implementation class AjouterEvaluation
 */
@WebServlet("/AjouterEvaluation")
public class AjouterEvaluation extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@EJB
	private EvaluationServiceRemote evaluationService;
	
	private static Logger logger = Logger.getLogger(AjouterEvaluation.class.getCanonicalName());
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjouterEvaluation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
		Evaluation ev = new Evaluation();
		Utilisateur u = new Utilisateur();
		Commentaire c = new Commentaire();
		try {
			response.setContentType("text/html");
			request.setCharacterEncoding("UTF-8");
			ev.setUtilisateur(u);
			ev.setCommentaire(c);
			ev.setEvaluation(1);
			ev.setDate_evaluation(Date.valueOf(LocalDate.now()));
			ev.setCommentaire(c);
			
		} catch (Exception e) {
			logger.log(Level.SEVERE,"Erreur d'appel EJB service",e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
