package fr.dauphine.lamsade.hib.biblio.modele;

import java.io.Serializable;


/**
 * Author : Mohamed Youssef Errayhani 
 * Date : 29/03/2016
 */

public class TypeBibliographieDTO  implements Serializable{

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
