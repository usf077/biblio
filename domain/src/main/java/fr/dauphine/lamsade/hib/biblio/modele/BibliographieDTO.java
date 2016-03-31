package fr.dauphine.lamsade.hib.biblio.modele;

import java.io.Serializable;


/**
 * @author ERRAYHANI Mohamed Youssef
 * Date : 29/03/2016
 */


public class BibliographieDTO  implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int identifiant; 
	private String libelle;
	private String source;
	private TypeBibliographieDTO typeBibliographie;
	
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
	public TypeBibliographieDTO getTypeBibliographie() {
		return typeBibliographie;
	}
	public void setTypeBibliographie(TypeBibliographieDTO typeBibliographie) {
		this.typeBibliographie = typeBibliographie;
	}
	
	
}
