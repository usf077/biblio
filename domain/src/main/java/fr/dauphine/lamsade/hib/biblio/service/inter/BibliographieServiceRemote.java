package fr.dauphine.lamsade.hib.biblio.service.inter;


import java.util.List;

import javax.ejb.Remote;

import fr.dauphine.lamsade.hib.biblio.modele.Bibliographie;



/**
 * @author ERRAYHANI Mohamed Youssef
 * Date : 29/03/2016
 */

@Remote
public interface BibliographieServiceRemote extends ICommonService<Bibliographie>{
	
	public int count(String txt, int idTypeBiblio);
	public List<Bibliographie>  findSearchRestrictedList(String txt, int idTypeBiblio,int startPosition, int nbElements, String orderBy, String orderSens);
	public Bibliographie findByIdAndFetchAuteurs(int id);
	public int count(int idUser);
	public List<Bibliographie>  findByUserIdRestrictedList( int idUser,int startPosition, int nbElements, String orderBy, String orderSens);
	
	
}
