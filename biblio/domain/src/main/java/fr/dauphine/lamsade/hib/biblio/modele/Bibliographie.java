package fr.dauphine.lamsade.hib.biblio.modele;

import java.io.Serializable;

public class Bibliographie  implements Serializable {

	private int identifiant; 
	private String source;
	private String description;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public TypeBibliographie getTypeBibliographie() {
		return typeBibliographie;
	}
	public void setTypeBibliographie(TypeBibliographie typeBibliographie) {
		this.typeBibliographie = typeBibliographie;
	}
	
	
}
