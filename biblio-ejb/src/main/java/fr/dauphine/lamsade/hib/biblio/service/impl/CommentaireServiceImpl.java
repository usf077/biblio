package fr.dauphine.lamsade.hib.biblio.service.impl;

import java.util.List;

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

	@Override
	public int count(int idBiblio) {
		 return ((Number)em.createNamedQuery("Commenaite.countByIdBiblio").setParameter("idBiblio", idBiblio).getSingleResult()).intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Commentaire> findByIdBiblio(int idBiblio, int startPosition, int nbElements) {
		
		 return em.createNamedQuery("Commenaite.findByIdBiblio").
				 setParameter("idBiblio", idBiblio).
				 setFirstResult(startPosition).
				 setMaxResults(nbElements).
				 getResultList();

	}}
