package fr.dauphine.lamsade.hib.biblio.modele;

import java.util.Date;

public class Commentaire {
	
	private Integer id;
	private String commentaire;
	private Date date_commentaire;
	private Utilisateur utilisateur;
	private BibliographieDTO biblio;
	
	public Commentaire() {
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
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	public BibliographieDTO getBiblio() {
		return biblio;
	}
	public void setBiblio(BibliographieDTO biblio) {
		this.biblio = biblio;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}
