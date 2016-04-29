package fr.dauphine.lamsade.hib.biblio.service.inter;

import java.util.List;

import javax.ejb.Remote;

import fr.dauphine.lamsade.hib.biblio.modele.Commentaire;

/**
 * 
 * Date : 31/03/2016
 */

@Remote
public interface CommentaireServiceRemote extends ICommonService<Commentaire>{
	
	int count(int idBiblio);

	List<Commentaire> findByIdBiblio(int idBiblio,int startPosition, int nbElements);
	

}
