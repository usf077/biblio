package fr.dauphine.lamsade.hib.biblio.service.inter;

import java.sql.SQLException;

import javax.ejb.Remote;

import fr.dauphine.lamsade.hib.biblio.modele.Commentaire;

@Remote
public interface CommentaireServiceRemote {
	
    /**
     * AjouterCommentaire
     * @param c
     * @return
     * @throws SQLException
     */
	public void AjouterCommentaire(Commentaire c) throws SQLException ;
}
