package fr.dauphine.lamsade.hib.biblio.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import fr.dauphine.lamsade.hib.biblio.modele.Utilisateur;
import fr.dauphine.lamsade.hib.biblio.service.inter.UtilisateurServiceRemote;
import fr.dauphine.lamsade.hib.biblio.util.DBConnexion;

public class UtilisateurServiceImpl implements UtilisateurServiceRemote {

	private static Logger logger = Logger.getLogger(UtilisateurServiceImpl.class.getCanonicalName());
	
	public UtilisateurServiceImpl(){
		
	}
	
	
	
	@Override
	public int AjouterUtilisateur(Utilisateur u) throws SQLException, ClassNotFoundException {
		Connection conn=null;
		PreparedStatement ajoutStatement = null;
		int returnValue = 0;
		try {
			conn = DBConnexion.getConnexion();
			
			String ajoutSQL = "INSERT INTO utilisateur" + "( nom, prenom, telephone, "
					+ "adresse, mail, mot_de_passe) VALUES"
					+ "(?,?,?,?,?)";
			conn.setAutoCommit(false);
			ajoutStatement = conn.prepareStatement(ajoutSQL,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ajoutStatement.setString(1, u.getNom());
			ajoutStatement.setString(2, u.getPrenom());
			ajoutStatement.setString(3,  u.getTelephone());
			ajoutStatement.setString(4, u.getAdresse());
			ajoutStatement.setString(5,u.getMail() );
			ajoutStatement.setString(6,u.getMot_de_passe());
			ajoutStatement.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Erreur EJB Ajout Utilisateur", e);
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
	public int ModifierUtilisateur(Utilisateur u) throws SQLException {
		Connection conn=null;
		PreparedStatement ajoutStatement = null;
		int returnValue = 0;
		try {
			conn = DBConnexion.getConnexion();
			
			String ajoutSQL = "UPDATE utilisateur" + " SET  nom = ? , prenom = ? , telephone = ? , adresse = ? , mail = ? , mot_de_passe = ?  WHERE"
					+ "id_utilisateur = ?";
			conn.setAutoCommit(false);
			ajoutStatement = conn.prepareStatement(ajoutSQL,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ajoutStatement.setString(1, u.getNom());
			ajoutStatement.setString(2, u.getPrenom());
			ajoutStatement.setString(3, u.getTelephone());
			ajoutStatement.setString(4, u.getAdresse());
			ajoutStatement.setString(5, u.getMail());
			ajoutStatement.setString(6, u.getMot_de_passe());
			ajoutStatement.setInt(7, u.getId_utilisateur());

			ajoutStatement.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Erreur EJB Modification Utilisateur", e);
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
	public List<Utilisateur> ListUtilisateur() throws SQLException {
		Connection conn=null;
		PreparedStatement ajoutStatement = null;
		List<Utilisateur> utilisateurList = new ArrayList<>();
		try {
			conn = DBConnexion.getConnexion();
			
			String ajoutSQL = "SELECT * From utilisateur";
			ajoutStatement = conn.prepareStatement(ajoutSQL);
			ResultSet rs = ajoutStatement.executeQuery();
			while (rs.next()) {
				Utilisateur u = new Utilisateur();
				u.setId_utilisateur(rs.getInt("id_utilisateur"));
				u.setNom(rs.getString("nom"));
				u.setPrenom(rs.getString("prenom"));
				u.setTelephone(rs.getString("telephone"));
				u.setAdresse(rs.getString("adresse"));
				u.setMail(rs.getString("mail"));
				u.setMot_de_passe(rs.getString("mot_de_passe"));
				utilisateurList.add(u);
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Erreur EJB Select Biblio", e);
		}finally {

			if (ajoutStatement != null) {
				ajoutStatement.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return utilisateurList;
	}

	@Override
	public Utilisateur getUtilisateur(int id) throws SQLException {
		Connection conn=null;
		PreparedStatement ajoutStatement = null;
		Utilisateur u = null;
		try {
			conn = DBConnexion.getConnexion();
			
			String ajoutSQL = "SELECT * FROM utilisateur WHERE id_utilisateur = ?";
			ajoutStatement = conn.prepareStatement(ajoutSQL);
			ajoutStatement.setInt(1, id);
			ResultSet rs = ajoutStatement.executeQuery();
			while (rs.next()) {
				u = new Utilisateur();
				u.setId_utilisateur(rs.getInt("id_utilisateur"));
				u.setNom(rs.getString("nom"));
				u.setPrenom(rs.getString("prenom"));
				u.setTelephone(rs.getString("telephone"));
				u.setAdresse(rs.getString("adresse"));
				u.setMail(rs.getString("mail"));
				u.setMot_de_passe(rs.getString("mot_de_passe"));
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Erreur EJB Select Utilisateur", e);
		}finally {

			if (ajoutStatement != null) {
				ajoutStatement.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return u;
	}

	@Override
	public int SupprimerUtilisateur(int id) throws SQLException {
		Connection conn=null;
		PreparedStatement ajoutStatement = null;
		int returnValue = 0;
		try {
			conn = DBConnexion.getConnexion();
			
			String ajoutSQL = "DELETE from utilisateur WHERE id_utilisateur = ?";
			conn.setAutoCommit(false);
			ajoutStatement = conn.prepareStatement(ajoutSQL,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ajoutStatement.setInt(1,id);
			ajoutStatement.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Erreur EJB Suppression Utilisateur", e);
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
