package fr.dauphine.lamsade.hib.biblio.modele;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the confiance database table.
 * 
 */
@Entity
@NamedQuery(name="Confiance.findAll", query="SELECT c FROM Confiance c")
public class Confiance implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_confirmation")
	private Integer idConfirmation;

	private Integer confiance;

	@Version
	private Integer version;

	//bi-directional many-to-one association to Utilisateur
	@ManyToOne
	@JoinColumn(name="id_utilisateur_destination")
	private Utilisateur utilisateurDestination;

	//bi-directional many-to-one association to Utilisateur
	@ManyToOne
	@JoinColumn(name="id_utilisateur_source")
	private Utilisateur utilisateurSource;

	public Confiance() {
	}

	public Integer getIdConfirmation() {
		return this.idConfirmation;
	}

	public void setIdConfirmation(Integer idConfirmation) {
		this.idConfirmation = idConfirmation;
	}

	public Integer getConfiance() {
		return this.confiance;
	}

	public void setConfiance(Integer confiance) {
		this.confiance = confiance;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Utilisateur getUtilisateurDestination() {
		return this.utilisateurDestination;
	}

	public void setUtilisateurDestination(Utilisateur utilisateurDestination) {
		this.utilisateurDestination = utilisateurDestination;
	}

	public Utilisateur getUtilisateurSource() {
		return this.utilisateurSource;
	}

	public void setUtilisateurSource(Utilisateur utilisateurSource) {
		this.utilisateurSource = utilisateurSource;
	}

}