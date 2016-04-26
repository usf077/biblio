package fr.dauphine.lamsade.hib.biblio.service.impl;

import javax.ejb.Stateless;


import fr.dauphine.lamsade.hib.biblio.modele.Commentaire;
import fr.dauphine.lamsade.hib.biblio.service.inter.CommentaireServiceRemote;

/**
 * @author Sara BAHJAJI
 */
@Stateless
public class CommentaireServiceImpl extends ServiceImpl<Commentaire>
		implements CommentaireServiceRemote {
	public CommentaireServiceImpl() {
		super(Commentaire.class);
	}

	
}
