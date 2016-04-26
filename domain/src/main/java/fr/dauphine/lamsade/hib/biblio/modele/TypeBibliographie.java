package fr.dauphine.lamsade.hib.biblio.modele;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the type_bibliographie database table.
 * 
 */
@Entity
@Table(name="type_bibliographie")
@NamedQuery(name="TypeBibliographie.findAll", query="SELECT t FROM TypeBibliographie t")
public class TypeBibliographie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_type_biblio")
	private Integer idTypeBiblio;

	private String description;

	private String libelle;

	@Version
	private Integer version;

	//bi-directional many-to-one association to Bibliographie
	@OneToMany(mappedBy="typeBibliographie")
	private List<Bibliographie> bibliographies;

	public TypeBibliographie() {
	}

	public Integer getIdTypeBiblio() {
		return this.idTypeBiblio;
	}

	public void setIdTypeBiblio(Integer idTypeBiblio) {
		this.idTypeBiblio = idTypeBiblio;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLibelle() {
		return this.libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
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

	public Bibliographie addBibliography(Bibliographie bibliography) {
		getBibliographies().add(bibliography);
		bibliography.setTypeBibliographie(this);

		return bibliography;
	}

	public Bibliographie removeBibliography(Bibliographie bibliography) {
		getBibliographies().remove(bibliography);
		bibliography.setTypeBibliographie(null);

		return bibliography;
	}

}