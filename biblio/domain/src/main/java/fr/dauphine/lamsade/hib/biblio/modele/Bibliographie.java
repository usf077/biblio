package fr.dauphine.lamsade.hib.biblio.modele;

import java.io.Serializable;

public class Bibliographie  implements Serializable {

	private int identifiant; 
	private String libelle;
	private String source;
	private TypeBibliographie typeBibliographie;
	
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
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public TypeBibliographie getTypeBibliographie() {
		return typeBibliographie;
	}
	public void setTypeBibliographie(TypeBibliographie typeBibliographie) {
		this.typeBibliographie = typeBibliographie;
	}
	
	
}
