package fr.dauphine.lamsade.hib.biblio.entitiy;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * @author ERRAYHANI Mohamed Youssef
 * Date : 29/03/2016
 */

/**
 * The persistent class for the commentaire database table.
 * 
 */
@Entity
@NamedQuery(name="Commentaire.findAll", query="SELECT c FROM Commentaire c")
public class Commentaire implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_commentaire")
	private Integer idCommentaire;

	private String commentaire;

	@Temporal(TemporalType.DATE)
	@Column(name="date_commentaire")
	private Date dateCommentaire;

	//bi-directional many-to-one association to Bibliographie
	@ManyToOne
	@JoinColumn(name="id_biblio")
	private Bibliographie bibliographie;

	//bi-directional many-to-one association to Utilisateur
	@ManyToOne
	@JoinColumn(name="id_utilisateur")
	private Utilisateur utilisateur;

	//bi-directional many-to-one association to Evaluation
	@OneToMany(mappedBy="commentaire")
	private List<Evaluation> evaluations;
	

	public Commentaire() {
	}

	public Integer getIdCommentaire() {
		return this.idCommentaire;
	}

	public void setIdCommentaire(Integer idCommentaire) {
		this.idCommentaire = idCommentaire;
	}

	public String getCommentaire() {
		return this.commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public Date getDateCommentaire() {
		return this.dateCommentaire;
	}

	public void setDateCommentaire(Date dateCommentaire) {
		this.dateCommentaire = dateCommentaire;
	}

	public Bibliographie getBibliographie() {
		return this.bibliographie;
	}

	public void setBibliographie(Bibliographie bibliographie) {
		this.bibliographie = bibliographie;
	}

	public Utilisateur getUtilisateur() {
		return this.utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public List<Evaluation> getEvaluations() {
		return this.evaluations;
	}

	public void setEvaluations(List<Evaluation> evaluations) {
		this.evaluations = evaluations;
	}

	public Evaluation addEvaluation(Evaluation evaluation) {
		getEvaluations().add(evaluation);
		evaluation.setCommentaire(this);

		return evaluation;
	}

	public Evaluation removeEvaluation(Evaluation evaluation) {
		getEvaluations().remove(evaluation);
		evaluation.setCommentaire(null);

		return evaluation;
	}

}