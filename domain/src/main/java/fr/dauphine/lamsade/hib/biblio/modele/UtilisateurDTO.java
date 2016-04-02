package fr.dauphine.lamsade.hib.biblio.modele;

import java.io.Serializable;

public class UtilisateurDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int identifiant;
	private String nom;
	private String prenom;
	private String telephone;
	private String adresse;
	private String mail;
	private String mot_de_passe;
	
	public UtilisateurDTO(int id_utilisateur, String nom, String prenom, String telephone, String adresse, String mail,
			String mot_de_passe) {
		super();
		this.identifiant = id_utilisateur;
		this.nom = nom;
		this.prenom = prenom;
		this.telephone = telephone;
		this.adresse = adresse;
		this.mail = mail;
		this.mot_de_passe = mot_de_passe;
	}

	public UtilisateurDTO() {
		super();
	}


	public int getIdentifiant() {
		return identifiant;
	}
	public void setIdentifiant(int id_utilisateur) {
		this.identifiant = id_utilisateur;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getMot_de_passe() {
		return mot_de_passe;
	}
	public void setMot_de_passe(String mot_de_passe) {
		this.mot_de_passe = mot_de_passe;
	}
	
	
	
}
