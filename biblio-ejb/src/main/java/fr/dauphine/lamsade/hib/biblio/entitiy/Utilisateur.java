package fr.dauphine.lamsade.hib.biblio.entitiy;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * Author : Mohamed Youssef Errayhani 
 * Date : 29/03/2016
 */

/**
 * The persistent class for the utilisateur database table.
 * 
 */
@Entity
@NamedQuery(name="Utilisateur.findAll", query="SELECT u FROM Utilisateur u")
public class Utilisateur implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_utilisateur")
	private Integer idUtilisateur;

	private String adresse;

	private String mail;

	@Column(name="mot_de_passe")
	private String motDePasse;

	private String nom;

	private String prenom;

	private String telephone;

	//bi-directional many-to-one association to Commentaire
	@OneToMany(mappedBy="utilisateur")
	private List<Commentaire> commentaires;

	//bi-directional many-to-one association to Confiance
	@OneToMany(mappedBy="utilisateur1")
	private List<Confiance> confiances1;

	//bi-directional many-to-one association to Confiance
	@OneToMany(mappedBy="utilisateur2")
	private List<Confiance> confiances2;

	//bi-directional many-to-one association to Evaluation
	@OneToMany(mappedBy="utilisateur")
	private List<Evaluation> evaluations;
	
	public Utilisateur() {
	}

	public Integer getIdUtilisateur() {
		return this.idUtilisateur;
	}

	public void setIdUtilisateur(Integer idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public String getAdresse() {
		return this.adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getMotDePasse() {
		return this.motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public List<Commentaire> getCommentaires() {
		return this.commentaires;
	}

	public void setCommentaires(List<Commentaire> commentaires) {
		this.commentaires = commentaires;
	}

	public Commentaire addCommentaire(Commentaire commentaire) {
		getCommentaires().add(commentaire);
		commentaire.setUtilisateur(this);

		return commentaire;
	}

	public Commentaire removeCommentaire(Commentaire commentaire) {
		getCommentaires().remove(commentaire);
		commentaire.setUtilisateur(null);

		return commentaire;
	}

	public List<Confiance> getConfiances1() {
		return this.confiances1;
	}

	public void setConfiances1(List<Confiance> confiances1) {
		this.confiances1 = confiances1;
	}

	public Confiance addConfiances1(Confiance confiances1) {
		getConfiances1().add(confiances1);
		confiances1.setUtilisateur1(this);

		return confiances1;
	}

	public Confiance removeConfiances1(Confiance confiances1) {
		getConfiances1().remove(confiances1);
		confiances1.setUtilisateur1(null);

		return confiances1;
	}

	public List<Confiance> getConfiances2() {
		return this.confiances2;
	}

	public void setConfiances2(List<Confiance> confiances2) {
		this.confiances2 = confiances2;
	}

	public Confiance addConfiances2(Confiance confiances2) {
		getConfiances2().add(confiances2);
		confiances2.setUtilisateur2(this);

		return confiances2;
	}

	public Confiance removeConfiances2(Confiance confiances2) {
		getConfiances2().remove(confiances2);
		confiances2.setUtilisateur2(null);

		return confiances2;
	}

	public List<Evaluation> getEvaluations() {
		return this.evaluations;
	}

	public void setEvaluations(List<Evaluation> evaluations) {
		this.evaluations = evaluations;
	}

	public Evaluation addEvaluation(Evaluation evaluation) {
		getEvaluations().add(evaluation);
		evaluation.setUtilisateur(this);

		return evaluation;
	}

	public Evaluation removeEvaluation(Evaluation evaluation) {
		getEvaluations().remove(evaluation);
		evaluation.setUtilisateur(null);

		return evaluation;
	}

}