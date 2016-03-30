package fr.dauphine.lamsade.hib.biblio.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;

import fr.dauphine.lamsade.hib.biblio.modele.Commentaire;
import fr.dauphine.lamsade.hib.biblio.service.inter.CommentaireServiceRemote;
import fr.dauphine.lamsade.hib.biblio.util.DBConnexion;

/**
 * Session Bean implementation class Commentaire
 */
@Stateless
public class CommentaireServiceImpl implements CommentaireServiceRemote {

	private static Logger logger = Logger.getLogger(CommentaireServiceImpl.class.getCanonicalName());
	
	/**
	 * Default constructor.
	 */
	public CommentaireServiceImpl() {

	}
	
	@Override
	public void AjouterCommentaire(Commentaire c) throws SQLException {
		Connection conn=null;
		PreparedStatement ajoutStatement = null;
		try {
			conn = DBConnexion.getConnexion();
			
			String ajoutSQL = "INSERT INTO commentaire" + "( id_commentaire, id_utilisateur, id_biblio, commentaire, date_commentaire) VALUES"
					+ "(?,?,?,?,?)";
			conn.setAutoCommit(false);
			ajoutStatement = conn.prepareStatement(ajoutSQL,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ajoutStatement.setInt(1, c.getId());
			ajoutStatement.setInt(2, c.getUtilisateur().getId_utilisateur());
			ajoutStatement.setInt(3, c.getBiblio().getIdentifiant());
			ajoutStatement.setString(4, c.getCommentaire());
			ajoutStatement.setString(5, c.getDate_commentaire().toString());
			ajoutStatement.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Erreur EJB Ajout Commentaire", e);
			conn.rollback();
		}finally {

			if (ajoutStatement != null) {
				ajoutStatement.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		
	}

}
