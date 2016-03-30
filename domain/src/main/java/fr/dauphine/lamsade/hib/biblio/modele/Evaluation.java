package fr.dauphine.lamsade.hib.biblio.modele;

import java.sql.Date;

public class Evaluation {

	private int id_evaluation;
	private Utilisateur utilisateur;
	private Commentaire commentaire;
	private int evaluation;
	private Date date_evaluation;
	
	
	public Evaluation() {
		super();
	}

	public Evaluation(int id_evaluation, Utilisateur utilisateur, Commentaire commentaire, int evaluation,
			Date date_evaluation) {
		super();
		this.id_evaluation = id_evaluation;
		this.utilisateur = utilisateur;
		this.commentaire = commentaire;
		this.evaluation = evaluation;
		this.date_evaluation = date_evaluation;
	}
	public int getId_evaluation() {
		return id_evaluation;
	}
	public void setId_evaluation(int id_evaluation) {
		this.id_evaluation = id_evaluation;
	}
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	public Commentaire getCommentaire() {
		return commentaire;
	}
	public void setCommentaire(Commentaire commentaire) {
		this.commentaire = commentaire;
	}
	public int getEvaluation() {
		return evaluation;
	}
	public void setEvaluation(int evaluation) {
		this.evaluation = evaluation;
	}
	public Date getDate_evaluation() {
		return date_evaluation;
	}
	public void setDate_evaluation(Date date_evaluation) {
		this.date_evaluation = date_evaluation;
	}
}
