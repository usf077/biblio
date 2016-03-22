package fr.dauphine.lamsade.hib.biblio.modele;

import java.io.Serializable;

public class TypeBibliographie  implements Serializable{

	private int identifiant;
	private String libelle ;
	private String Description;
	
	/**
	 * Les getters et les setters 
	 * 
	 */
	public int getIdentifiant() {
		return identifiant;
	}
	public void setIdentifiant(int identifiant) {
		this.identifiant = identifiant;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	
	
}
