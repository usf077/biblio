package fr.dauphine.lamsade.hib.biblio.modele;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the bibliographie database table.
 * 
 */
@Entity
@NamedQueries(value={
		@NamedQuery(name="Bibliographie.findAll", query="SELECT b FROM Bibliographie b"),
		@NamedQuery(name="Bibliographie.findByIdBiblio", query="SELECT b FROM Bibliographie b  LEFT JOIN b.auteurs aut WHERE b.idBiblio = :idBiblio")
})

public class Bibliographie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_biblio")
	private Integer idBiblio;

	private String libelle;

	private String source;
	
	private String description;

	@Version
	private Integer version;
	
	@Transient
	private String auteursName;

	//bi-directional many-to-one association to TypeBibliographie
	@ManyToOne 
	@JoinColumn(name="id_type_biblio")
	private TypeBibliographie typeBibliographie;

	//bi-directional many-to-one association to Utilisateur
	@ManyToOne 
	@JoinColumn(name="id_utilisateur")
	private Utilisateur utilisateur;

	//bi-directional many-to-many association to Auteur
	@ManyToMany(cascade={CascadeType.PERSIST,CascadeType.REMOVE})
	@JoinTable(
		name="bibliographoe_auteur"
		, joinColumns={
			@JoinColumn(name="id_biblio")
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_auteur")
			}
		)
	private List<Auteur> auteurs;

	//bi-directional many-to-one association to mentaire
	@OneToMany(mappedBy="bibliographie" ,cascade=CascadeType.REMOVE)
	private List<Commentaire> commentaires;

	public Bibliographie() {
	}
	
	@Transient
	private boolean isEditable;

	public boolean isEditable() {
		return isEditable;
	}

	public void setEditable(boolean isEditable) {
		this.isEditable = isEditable;
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

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	

	public String getAuteursName() {
		if(auteursName==null){
			auteursName="";
			for (Auteur auteur : auteurs) {
				auteursName += auteur.getNom() + " " + auteur.getPrenom() +" & ";
			}
			if(!auteursName.isEmpty() && auteursName.length()>2) {
				auteursName = auteursName.substring(0, auteursName.length()-2);
			}
			
			if(auteursName.length()>40 ){
				auteursName =	auteursName.substring(0, 40) + "...";
			}
		}
		return auteursName;
	}

	public TypeBibliographie getTypeBibliographie() {
		return this.typeBibliographie;
	}

	public void setTypeBibliographie(TypeBibliographie typeBibliographie) {
		this.typeBibliographie = typeBibliographie;
	}

	public Utilisateur getUtilisateur() {
		return this.utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public List<Auteur> getAuteurs() {
		return this.auteurs;
	}

	public void setAuteurs(List<Auteur> auteurs) {
		this.auteurs = auteurs;
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