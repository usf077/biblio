package fr.dauphine.lamsade.hib.biblio.service.impl;

import javax.ejb.Stateless;

import fr.dauphine.lamsade.hib.biblio.entitiy.Bibliographie;
import fr.dauphine.lamsade.hib.biblio.entitiy.Commentaire;
import fr.dauphine.lamsade.hib.biblio.entitiy.Utilisateur;
import fr.dauphine.lamsade.hib.biblio.modele.BibliographieDTO;
import fr.dauphine.lamsade.hib.biblio.modele.CommentaireDTO;
import fr.dauphine.lamsade.hib.biblio.modele.UtilisateurDTO;
import fr.dauphine.lamsade.hib.biblio.modele.TypeBibliographieDTO;

import fr.dauphine.lamsade.hib.biblio.service.inter.CommentaireServiceRemote;

/**
 * @author Sara BAHJAJI
 */
@Stateless
public class CommentaireServiceImpl extends ServiceImpl<Commentaire, CommentaireDTO>
		implements CommentaireServiceRemote<Commentaire> {
	public CommentaireServiceImpl() {
		super(Commentaire.class);
	}

	@Override
	public Commentaire MapFrom(CommentaireDTO cDto) {

		if (cDto.getIdentifiant() != 0) {
			Commentaire c = em.find(Commentaire.class, cDto.getIdentifiant());
			c.setCommentaire(cDto.getCommentaire());
			return c;
		} else {
			Commentaire c = new Commentaire();
			Bibliographie b = em.find(Bibliographie.class, cDto.getBiblio().getIdentifiant());
			Utilisateur u = em.find(Utilisateur.class, cDto.getUtilisateur().getIdentifiant());
			c.setUtilisateur(u);
			c.setCommentaire(cDto.getCommentaire());
			c.setDateCommentaire(cDto.getDate_commentaire());
			c.setBibliographie(b);

			return c;
		}

	}

	@Override
	public CommentaireDTO MapTo(Commentaire c) {
		CommentaireDTO cDto = new CommentaireDTO();
		cDto.setIdentifiant(c.getIdCommentaire());
		cDto.setCommentaire(c.getCommentaire());
		cDto.setDate_commentaire(c.getDateCommentaire());

		// bibliographie
		Bibliographie b = c.getBibliographie();
		BibliographieDTO bDto = new BibliographieDTO();

		bDto.setLibelle(b.getLibelle());
		bDto.setSource(b.getSource());
		if (b.getTypeBibliographie() != null) {
			TypeBibliographieDTO tbDTO = new TypeBibliographieDTO();
			tbDTO.setIdentifiant(b.getTypeBibliographie().getIdTypeBiblio());
			tbDTO.setLibelle(b.getTypeBibliographie().getLibelle());
			bDto.setTypeBibliographie(tbDTO);
		}

		cDto.setBiblio(bDto);
	

		// Utilisateur
		Utilisateur u = c.getUtilisateur();
		UtilisateurDTO uDto = new UtilisateurDTO();

		uDto.setIdentifiant(u.getIdUtilisateur());
		uDto.setAdresse(u.getAdresse());
		uDto.setMail(u.getMail());
		uDto.setMot_de_passe(u.getMotDePasse());
		uDto.setNom(u.getNom());
		uDto.setPrenom(u.getPrenom());
		uDto.setTelephone(u.getTelephone());

		cDto.setUtilisateur(uDto);
		return cDto;

	}

}
