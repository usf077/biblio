package fr.dauphine.lamsade.hib.biblio.entitiy;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * Author : Mohamed Youssef Errayhani 
 * Date : 29/03/2016
 */

/**
 * The persistent class for the bibliographie database table.
 * 
 */
@Entity
@NamedQuery(name="Bibliographie.findAll", query="SELECT b FROM Bibliographie b")
public class Bibliographie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_biblio")
	private Integer idBiblio;

	private String libelle;

	private String source;

	//bi-directional many-to-one association to TypeBibliographie
	@ManyToOne
	@JoinColumn(name="id_type_biblio")
	private TypeBibliographie typeBibliographie;

	//bi-directional many-to-one association to Commentaire
	@OneToMany(mappedBy="bibliographie")
	private List<Commentaire> commentaires;


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Bibliographie() {
	}

	public Integer getIdBiblio() {
		return this.idBiblio;
	}

	public void setIdBiblio(Integer idBiblio) {
		this.idBiblio = idBiblio;
	}

	public String getLibelle() {
		return this.libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public TypeBibliographie getTypeBibliographie() {
		return this.typeBibliographie;
	}

	public void setTypeBibliographie(TypeBibliographie typeBibliographie) {
		this.typeBibliographie = typeBibliographie;
	}

	public List<Commentaire> getCommentaires() {
		return this.commentaires;
	}

	public void setCommentaires(List<Commentaire> commentaires) {
		this.commentaires = commentaires;
	}

	public Commentaire addCommentaire(Commentaire commentaire) {
		getCommentaires().add(commentaire);
		commentaire.setBibliographie(this);

		return commentaire;
	}

	public Commentaire removeCommentaire(Commentaire commentaire) {
		getCommentaires().remove(commentaire);
		commentaire.setBibliographie(null);

		return commentaire;
	}

}