package fr.dauphine.lamsade.hib.biblio.modele;

import java.util.Date;
// 
public class CommentaireDTO {
	
	private int identifiant;
	private String commentaire;
	private Date date_commentaire;
	private UtilisateurDTO utilisateur;
	private BibliographieDTO biblio;
	
	public CommentaireDTO() {
		super();
	}
	public String getCommentaire() {
		return commentaire;
	}
	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}
	public Date getDate_commentaire() {
		return date_commentaire;
	}
	public void setDate_commentaire(Date date_commentaire) {
		this.date_commentaire = date_commentaire;
	}
	public UtilisateurDTO getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(UtilisateurDTO utilisateur) {
		this.utilisateur = utilisateur;
	}
	public BibliographieDTO getBiblio() {
		return biblio;
	}
	public void setBiblio(BibliographieDTO biblio) {
		this.biblio = biblio;
	}
	public int getIdentifiant() {
		return identifiant;
	}
	public void setIdentifiant(int id) {
		this.identifiant = id;
	}
}
