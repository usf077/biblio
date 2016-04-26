package fr.dauphine.lamsade.hib.biblio.modele;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the auteur database table.
 * 
 */
@Entity
@NamedQuery(name="Auteur.findAll", query="SELECT a FROM Auteur a")
public class Auteur implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_auteur")
	private Integer idAuteur;

	private String nom;

	private String prenom;
	
	@Version
	private Integer version;

	//bi-directional many-to-many association to Bibliographie
	@ManyToMany(mappedBy="auteurs")
	private List<Bibliographie> bibliographies;

	public Auteur() {
	}

	public Integer getIdAuteur() {
		return this.idAuteur;
	}

	public void setIdAuteur(Integer idAuteur) {
		this.idAuteur = idAuteur;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public List<Bibliographie> getBibliographies() {
		return this.bibliographies;
	}

	public void setBibliographies(List<Bibliographie> bibliographies) {
		this.bibliographies = bibliographies;
	}

	@Override
	public	String toString(){
		return this.nom + " " + this.prenom;
	}
}