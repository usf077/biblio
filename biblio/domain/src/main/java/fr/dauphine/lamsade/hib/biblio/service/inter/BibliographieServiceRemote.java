package fr.dauphine.lamsade.hib.biblio.service.inter;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Remote;

import fr.dauphine.lamsade.hib.biblio.modele.Bibliographie;

@Remote
public interface BibliographieServiceRemote {
    /**
     * Ajouter une bibliographie à la base
     * @param b
     * @return état de l'ajout
     * @throws SQLException 
     */
	public int AjouterBibliographie(Bibliographie b) throws SQLException ;
	
	/**
	 * 
	 * @param b
	 * @return état de la modification
	 */
	public int ModifierBibliographie(Bibliographie b) throws SQLException;
	
	/**
	 * Lister les bibliogrpahie depuis la base 
	 * @return list des bibliographie dans la base
	 */
	public List<Bibliographie> ListBibliographie() throws SQLException;
	
	/**
	 * Lire une bibliographie de la base
	 * @param id
	 * @return Une bibliographie par identifiant
	 */
	public Bibliographie getBibliographie(int id) throws SQLException;
	
	/**
	 * supprimer une bibliographie de la base par idnetifiant
	 * @param id
	 * @return Etat de la suppresion
	 */
	public int SupprimerBibliographie(int id) throws SQLException;

}
