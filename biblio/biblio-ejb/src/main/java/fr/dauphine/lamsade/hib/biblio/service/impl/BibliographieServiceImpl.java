package fr.dauphine.lamsade.hib.biblio.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import fr.dauphine.lamsade.hib.biblio.modele.Bibliographie;
import fr.dauphine.lamsade.hib.biblio.modele.TypeBibliographie;
import fr.dauphine.lamsade.hib.biblio.service.inter.BibliographieServiceRemote;
import fr.dauphine.lamsade.hib.biblio.util.DBConnexion;

/**
 * Session Bean implementation class Bibliotheque
 */
@Stateless
public class BibliographieServiceImpl implements BibliographieServiceRemote {

	/**
	 * Default constructor.
	 */
	public BibliographieServiceImpl() {

	}

	private static Logger logger = Logger.getLogger(BibliographieServiceImpl.class.getCanonicalName());

	@Override
	public int AjouterBibliographie(Bibliographie b) throws SQLException  {
		Connection conn=null;
		PreparedStatement ajoutStatement = null;
		int returnValue = 0;
		try {
			conn = DBConnexion.getConnexion();
			
			String ajoutSQL = "INSERT INTO bibliographie" + "( source, libelle, id_type_biblio) VALUES"
					+ "(?,?,?)";
			conn.setAutoCommit(false);
			ajoutStatement = conn.prepareStatement(ajoutSQL,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ajoutStatement.setString(1, b.getSource());
			ajoutStatement.setString(2, b.getLibelle());
			ajoutStatement.setInt(3,  b.getTypeBibliographie().getIdentifiant());	
			ajoutStatement.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Erreur EJB Ajout Biblio", e);
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
	public int ModifierBibliographie(Bibliographie b) throws SQLException {
		Connection conn=null;
		PreparedStatement ajoutStatement = null;
		int returnValue = 0;
		try {
			conn = DBConnexion.getConnexion();
			
			String ajoutSQL = "UPDATE bibliographie" + " SET source = ? , libelle = ?, id_type_biblio = ? WHERE"
					+ "id_biblio = ?";
			conn.setAutoCommit(false);
			ajoutStatement = conn.prepareStatement(ajoutSQL,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ajoutStatement.setString(1, b.getSource());
			ajoutStatement.setString(2, b.getLibelle());
			ajoutStatement.setInt(3,  b.getTypeBibliographie().getIdentifiant());	
			ajoutStatement.setInt(4,  b.getIdentifiant());	
			ajoutStatement.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Erreur EJB Modification Biblio", e);
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
	public List<Bibliographie> ListBibliographie() throws SQLException {
		Connection conn=null;
		PreparedStatement ajoutStatement = null;
		List<Bibliographie> biblioLst = new ArrayList<>();
		try {
			conn = DBConnexion.getConnexion();
			
			String ajoutSQL = "SELECT b.*, tb.libelle as libelle_type FROM bibliographie b , type_bibliographie tb where b.id_type_biblio = tb.id_type_biblio";
			ajoutStatement = conn.prepareStatement(ajoutSQL);
			ResultSet rs = ajoutStatement.executeQuery();
			while (rs.next()) {
				Bibliographie b = new Bibliographie();
				TypeBibliographie tb = new TypeBibliographie();
				b.setIdentifiant(rs.getInt("id_biblio"));
				b.setSource(rs.getString("source"));
				b.setLibelle(rs.getString("libelle"));
				tb.setIdentifiant(rs.getInt("id_type_biblio"));
				tb.setLibelle(rs.getString("libelle_type"));
				b.setTypeBibliographie(tb);
				biblioLst.add(b);
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
		return biblioLst;
	}

	@Override
	public Bibliographie getBibliographie(int id) throws SQLException {
		Connection conn=null;
		PreparedStatement ajoutStatement = null;
		Bibliographie b = null;
		try {
			conn = DBConnexion.getConnexion();
			
			String ajoutSQL = "SELECT b.*, tb.libelle as libelle_type FROM bibliographie b , type_bibliographie tb WHERE b.id_type_biblio = tb.id_type_biblio and id_biblio = ?";
			ajoutStatement = conn.prepareStatement(ajoutSQL);
			ajoutStatement.setInt(1, id);
			ResultSet rs = ajoutStatement.executeQuery();
			while (rs.next()) {
				b = new Bibliographie();
				TypeBibliographie tb = new TypeBibliographie();
				b.setIdentifiant(rs.getInt("id_biblio"));
				b.setSource(rs.getString("source"));
				b.setLibelle(rs.getString("libelle"));
				tb.setIdentifiant(rs.getInt("id_type_biblio"));
				tb.setLibelle(rs.getString("libelle_type"));
				b.setTypeBibliographie(tb);
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
		return b;
	}

	@Override
	public int SupprimerBibliographie(int id) throws SQLException {
		Connection conn=null;
		PreparedStatement ajoutStatement = null;
		int returnValue = 0;
		try {
			conn = DBConnexion.getConnexion();
			
			String ajoutSQL = "DELETE from bibliographie WHERE id_biblio = ?";
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
