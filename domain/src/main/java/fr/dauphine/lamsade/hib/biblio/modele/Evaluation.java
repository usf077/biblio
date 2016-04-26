package fr.dauphine.lamsade.hib.biblio.modele;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the evaluation database table.
 * 
 */
@Entity
@NamedQuery(name="Evaluation.findAll", query="SELECT e FROM Evaluation e")
public class Evaluation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_evaluation")
	private Integer idEvaluation;

	@Temporal(TemporalType.DATE)
	@Column(name="date_evaluation")
	private Date dateEvaluation;

	private Integer evaluation;

	@Version
	private Integer version;

	//bi-directional many-to-one association to Commentaire
	@ManyToOne
	@JoinColumn(name="id_commentaire")
	private Commentaire commentaire;

	//bi-directional many-to-one association to Utilisateur
	@ManyToOne
	@JoinColumn(name="id_utilisateur")
	private Utilisateur utilisateur;

	public Evaluation() {
	}

	public Integer getIdEvaluation() {
		return this.idEvaluation;
	}

	public void setIdEvaluation(Integer idEvaluation) {
		this.idEvaluation = idEvaluation;
	}

	public Date getDateEvaluation() {
		return this.dateEvaluation;
	}

	public void setDateEvaluation(Date dateEvaluation) {
		this.dateEvaluation = dateEvaluation;
	}

	public Integer getEvaluation() {
		return this.evaluation;
	}

	public void setEvaluation(Integer evaluation) {
		this.evaluation = evaluation;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Commentaire getCommentaire() {
		return this.commentaire;
	}

	public void setCommentaire(Commentaire commentaire) {
		this.commentaire = commentaire;
	}

	public Utilisateur getUtilisateur() {
		return this.utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

}