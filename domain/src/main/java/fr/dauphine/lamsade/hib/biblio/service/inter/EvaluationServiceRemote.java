package fr.dauphine.lamsade.hib.biblio.service.inter;

import java.sql.SQLException;
import java.util.List;

import fr.dauphine.lamsade.hib.biblio.modele.Evaluation;

public interface EvaluationServiceRemote {

	public int AjouterEvaluation(Evaluation e) throws SQLException, ClassNotFoundException ;
	
	public int ModifierEvaluation(Evaluation e) throws SQLException;
	public List<Evaluation> ListEvaluation() throws SQLException;
	public Evaluation getEvaluation(int id) throws SQLException;
	public int SupprimerEvaluation(int id) throws SQLException;
}

