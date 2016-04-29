package fr.dauphine.lamsade.hib.biblio.modele;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the utilisateur database table.
 * 
 */
@Entity
@NamedQueries(value = {@NamedQuery(name="Utilisateur.findAll", query="SELECT u FROM Utilisateur u"),
		@NamedQuery(name="Utilisateur.findByEmail", query="SELECT u FROM Utilisateur u WHERE u.mail = :mail")})
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

	@Column(name="photo_path")
	private String photoPath;

	private String prenom;

	private String telephone;

	@Version 
	private Integer version;
	
	@Temporal(TemporalType.DATE)
	@Column(name="date_inscription")
	private Date dateInscription;

	@Temporal(TemporalType.DATE)
	@Column(name="date_naissance")
	private Date dateNaissance;
	
	@Transient
	private int age;
	

	@Transient
	private boolean isEditable;

	public boolean isEditable() {
		return isEditable;
	}

	public void setEditable(boolean isEditable) {
		this.isEditable = isEditable;
	}

	public int getAge(){	
		  Calendar birthDay = Calendar.getInstance();
	      birthDay.setTimeInMillis( this.dateNaissance.getTime());
	      long currentTime = System.currentTimeMillis();
	      Calendar now = Calendar.getInstance();
	      now.setTimeInMillis(currentTime);
	      this.age = now.get(Calendar.YEAR) - birthDay.get(Calendar.YEAR);
		return age;
	}
	
	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public Date getDateInscription() {
		return dateInscription;
	}

	public void setDateInscription(Date dateInscription) {
		this.dateInscription = dateInscription;
	}
	
	//bi-directional many-to-one association to Bibliographie
	@OneToMany(mappedBy="utilisateur")
	private List<Bibliographie> bibliographies;

	//bi-directional many-to-one association to Commentaire
	@OneToMany(mappedBy="utilisateur")
	private List<Commentaire> commentaires;

	//bi-directional many-to-one association to Confiance
	@OneToMany(mappedBy="utilisateurDestination")
	private List<Confiance> confiancesDestination;

	//bi-directional many-to-one association to Confiance
	@OneToMany(mappedBy="utilisateurSource")
	private List<Confiance> confiancesSource;

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

	public String getPhotoPath() {
		return this.photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
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

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public List<Bibliographie> getBibliographies() {
		return this.bibliographies;
	}

	public void setBibliographies(List<Bibliographie> bibliographies) {
		this.bibliographies = bibliographies;
	}

	public Bibliographie addBibliography(Bibliographie bibliography) {
		getBibliographies().add(bibliography);
		bibliography.setUtilisateur(this);

		return bibliography;
	}

	public Bibliographie removeBibliography(Bibliographie bibliography) {
		getBibliographies().remove(bibliography);
		bibliography.setUtilisateur(null);

		return bibliography;
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

	public List<Confiance> getConfiancesDestination() {
		return this.confiancesDestination;
	}

	public void setConfiances1(List<Confiance> confiancesDestination) {
		this.confiancesDestination = confiancesDestination;
	}

	public Confiance addConfiancesDestination(Confiance confiancesDestination) {
		getConfiancesDestination().add(confiancesDestination);
		confiancesDestination.setUtilisateurDestination(this);

		return confiancesDestination;
	}

	public Confiance removeConfiancesDestination(Confiance confiancesDestination) {
		getConfiancesDestination().remove(confiancesDestination);
		confiancesDestination.setUtilisateurDestination(null);

		return confiancesDestination;
	}

	public List<Confiance> getConfiancesSource() {
		return this.confiancesSource;
	}

	public void setConfiances2(List<Confiance> confiancesSource) {
		this.confiancesSource = confiancesSource;
	}

	public Confiance addConfiances2(Confiance confiancesSource) {
		getConfiancesSource().add(confiancesSource);
		confiancesSource.setUtilisateurSource(this);

		return confiancesSource;
	}

	public Confiance removeConfiancesSource(Confiance confiancesSource) {
		getConfiancesSource().remove(confiancesSource);
		confiancesSource.setUtilisateurSource(null);

		return confiancesSource;
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
	@Override
	public	String toString(){
	return this.nom + " " + this.prenom;
	}

}