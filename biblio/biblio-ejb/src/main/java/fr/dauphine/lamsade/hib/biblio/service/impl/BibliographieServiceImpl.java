package fr.dauphine.lamsade.hib.biblio.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import fr.dauphine.lamsade.hib.biblio.modele.Bibliographie;
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
	public int AjouterBibliographie(Bibliographie b) throws SQLException {
		Connection conn=null;
		PreparedStatement ajoutStetement = null;
		int returnValue = 0;
		try {
			conn = DBConnexion.getConnexion();
			
			String ajoutSQL = "INSERT INTO bibliographie" + "( source, description, id_type_biblio) VALUES"
					+ "(?,?,?)";
			conn.setAutoCommit(false);
			ajoutStetement = conn.prepareStatement(ajoutSQL,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ajoutStetement.setString(0, b.getSource());
			ajoutStetement.setString(1, b.getDescription());
			ajoutStetement.setInt(2,  b.getTypeBibliographie().getIdentifiant());	
			conn.commit();
		} catch (SQLException e) {
			conn.rollback();
			logger.log(Level.SEVERE, e, null);
			returnValue = -1;
		}finally {

			if (ajoutStetement != null) {
				ajoutStetement.close();
			}
			if (conn != null) {
				conn.close();
			}
		}

		return returnValue;
	}

	@Override
	public int ModifierBibliographie(Bibliographie b) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Bibliographie> ListBibliographie() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bibliographie getBibliographie(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int SupprimerBibliographie(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
