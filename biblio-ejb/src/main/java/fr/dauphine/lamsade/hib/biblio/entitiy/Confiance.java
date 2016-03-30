package fr.dauphine.lamsade.hib.biblio.entitiy;

import java.io.Serializable;
import javax.persistence.*;


/**
 * Author : Mohamed Youssef Errayhani 
 * Date : 29/03/2016
 */


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

	//bi-directional many-to-one association to Utilisateur
	@ManyToOne
	@JoinColumn(name="id_utilisateur_destination")
	private Utilisateur utilisateur1;

	//bi-directional many-to-one association to Utilisateur
	@ManyToOne
	@JoinColumn(name="id_utilisateur_source")
	private Utilisateur utilisateur2;

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

	public Utilisateur getUtilisateur1() {
		return this.utilisateur1;
	}

	public void setUtilisateur1(Utilisateur utilisateur1) {
		this.utilisateur1 = utilisateur1;
	}

	public Utilisateur getUtilisateur2() {
		return this.utilisateur2;
	}

	public void setUtilisateur2(Utilisateur utilisateur2) {
		this.utilisateur2 = utilisateur2;
	}

}