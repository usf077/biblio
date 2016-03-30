package fr.dauphine.lamsade.hib.biblio.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import fr.dauphine.lamsade.hib.biblio.modele.Commentaire;
import fr.dauphine.lamsade.hib.biblio.modele.Evaluation;
import fr.dauphine.lamsade.hib.biblio.modele.Utilisateur;
import fr.dauphine.lamsade.hib.biblio.service.inter.EvaluationServiceRemote;
import fr.dauphine.lamsade.hib.biblio.util.DBConnexion;

public class EvaluationServiceImpl implements EvaluationServiceRemote{

	private static Logger logger = Logger.getLogger(EvaluationServiceImpl.class.getCanonicalName());
	 
	
	public EvaluationServiceImpl() {
	}
	@Override
	public int AjouterEvaluation(Evaluation ev) throws SQLException, ClassNotFoundException {
		Connection conn=null;
		PreparedStatement ajoutStatement = null;
		int returnValue = 0;
		try {
			conn = DBConnexion.getConnexion();
			
			String ajoutSQL = "INSERT INTO evaluation" + "( id_utilisateur, id_commentaire, evaluation, date_evaluation) VALUES"
					+ "(?,?,?,?)";
			conn.setAutoCommit(false);
			ajoutStatement = conn.prepareStatement(ajoutSQL,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ajoutStatement.setInt(1, ev.getUtilisateur().getId_utilisateur());
			ajoutStatement.setInt(2, ev.getCommentaire().getId());
			ajoutStatement.setInt(3, ev.getEvaluation());	
			ajoutStatement.setDate(4, ev.getDate_evaluation());	
			
			ajoutStatement.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Erreur EJB Ajout Evaluation", e);
			conn.rollback();
			returnValue = -1;
		}finally {

			if (ajoutStatement != null) {
				ajoutStatement.close();
			}
			if (conn != null) {
				conn.close();
			}
		}

		return returnValue;
	}	
	
	@Override
	public int ModifierEvaluation(Evaluation ev) throws SQLException {
		Connection conn=null;
		PreparedStatement ajoutStatement = null;
		int returnValue = 0;
		try {
			conn = DBConnexion.getConnexion();
			
			String ajoutSQL = "UPDATE evaluation" + " SET id_utilisateur = ? , "
					+ "id_commentaire = ?, evaluation = ? WHERE"
					+ "id_evaluation = ?";
			conn.setAutoCommit(false);
			ajoutStatement = conn.prepareStatement(ajoutSQL,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ajoutStatement.setInt(1, ev.getUtilisateur().getId_utilisateur());
			ajoutStatement.setInt(2, ev.getCommentaire().getId());
			ajoutStatement.setInt(3, ev.getEvaluation());	
			ajoutStatement.setDate(4, ev.getDate_evaluation());	
			ajoutStatement.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Erreur EJB Modification Evaluation", e);
			conn.rollback();
			returnValue = -1;
		}finally {

			if (ajoutStatement != null) {
				ajoutStatement.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return returnValue;
	}

	@Override
	public List<Evaluation> ListEvaluation() throws SQLException {
		Connection conn=null;
		PreparedStatement ajoutStatement = null;
		List<Evaluation> listEvaluation = new ArrayList<>();
		try {
			conn = DBConnexion.getConnexion();
			
			String ajoutSQL = "SELECT e.id_evaluation,e.evaluation,e.date_evaluation, u.*, c.* FROM  evaluation e, utilisateur u, commentaire c "
					+ "where e.id_utilisateur = u.id_utilisateur and e.id_commentaire = c.id_commentaire";
			
			ajoutStatement = conn.prepareStatement(ajoutSQL);
			ResultSet rs = ajoutStatement.executeQuery();
			while (rs.next()) {
//---------------------A modifier----------------------//
				Evaluation ev = new Evaluation();
				Utilisateur u = new Utilisateur();
				Commentaire c = new Commentaire();
				
				u.setId_utilisateur(rs.getInt("id_utilisateur"));
				u.setNom(rs.getString("nom"));
				u.setPrenom(rs.getString("prenom"));
				u.setTelephone(rs.getString("telephone"));
				u.setAdresse(rs.getString("adresse"));
				u.setMail(rs.getString("mail"));
				u.setMot_de_passe(rs.getString("mot_de_passe"));
				
				
				c.setBiblio(null);
				c.setDate_commentaire(rs.getDate("date_commentaire"));
				c.setId(rs.getInt("id_commentaire"));
				c.setUtilisateur(u);
				c.setCommentaire(rs.getString("commentaire"));
				
				ev.setId_evaluation(rs.getInt("id_evaluation"));
				ev.setEvaluation(rs.getInt("evaluation"));
				ev.setDate_evaluation(rs.getDate("date_evaluation"));
				
				ev.setUtilisateur(u);
				ev.setCommentaire(null);
//----------------------------------------------------
				listEvaluation.add(ev);
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Erreur EJB Select Evaluation", e);
		}finally {

			if (ajoutStatement != null) {
				ajoutStatement.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return listEvaluation;
	}

	@Override
	public Evaluation getEvaluation(int id) throws SQLException {
		Connection conn=null;
		PreparedStatement ajoutStatement = null;
		Evaluation ev=null;
		try {
			conn = DBConnexion.getConnexion();
			
			String ajoutSQL = "SELECT * FROM evaluation WHERE id_evaluation = ?";
			
			ajoutStatement = conn.prepareStatement(ajoutSQL);
			ResultSet rs = ajoutStatement.executeQuery();
			while (rs.next()) {
				ev = new Evaluation();
				Utilisateur u = new Utilisateur();
				
				ev.setId_evaluation(rs.getInt("id_evaluation"));
				ev.setUtilisateur(u);
				ev.setCommentaire(null);
				ev.setEvaluation(rs.getInt("evaluation"));
				ev.setDate_evaluation(rs.getDate("date_evaluation"));
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Erreur EJB Select Evaluation", e);
		}finally {

			if (ajoutStatement != null) {
				ajoutStatement.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return ev;
	}

	@Override
	public int SupprimerEvaluation(int id) throws SQLException {
		Connection conn=null;
		PreparedStatement ajoutStatement = null;
		int returnValue = 0;
		try {
			conn = DBConnexion.getConnexion();
			
			String ajoutSQL = "DELETE from evaluation WHERE id_biblio = ?";
			conn.setAutoCommit(false);
			ajoutStatement = conn.prepareStatement(ajoutSQL,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ajoutStatement.setInt(1,id);
			ajoutStatement.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Erreur EJB Suppression Biblio", e);
			conn.rollback();
			returnValue = -1;
		}finally {

			if (ajoutStatement != null) {
				ajoutStatement.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return returnValue;
	}

	

	
	
	
	
	
}
