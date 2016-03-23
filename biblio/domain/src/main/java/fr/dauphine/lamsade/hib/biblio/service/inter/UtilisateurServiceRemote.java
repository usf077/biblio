package fr.dauphine.lamsade.hib.biblio.service.inter;

import java.sql.SQLException;
import java.util.List;

import fr.dauphine.lamsade.hib.biblio.modele.Utilisateur;

public interface UtilisateurServiceRemote {

	
	
public int AjouterUtilisateur(Utilisateur b) throws SQLException, ClassNotFoundException ;
	
	public int ModifierUtilisateur(Utilisateur b) throws SQLException;
	public List<Utilisateur> ListUtilisateur() throws SQLException;
	public Utilisateur getUtilisateur(int id) throws SQLException;
	public int SupprimerUtilisateur(int id) throws SQLException;

}
