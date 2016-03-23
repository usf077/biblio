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

import fr.dauphine.lamsade.hib.biblio.service.inter.EvaluationServiceRemote;


@WebServlet("/SupprimerEvaluation")
public class SupprimerEvaluation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private EvaluationServiceRemote evaluationService;
	
	private static Logger logger = Logger.getLogger(AjouterEvaluation.class.getCanonicalName());
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupprimerEvaluation() {
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
			evaluationService.SupprimerEvaluation(1);
			response.getWriter().append("L'Evaluation a été bien suppriméé");
		} catch (Exception e) {
			logger.log(Level.SEVERE,"Erreur d'appel EJB service",e);
		} 
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
