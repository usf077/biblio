package fr.dauphine.lamsade.hib.biblio.service.inter;


import javax.ejb.Remote;

import fr.dauphine.lamsade.hib.biblio.modele.BibliographieDTO;


/**
 * Author : Mohamed Youssef Errayhani 
 * Date : 29/03/2016
 */

@Remote
public interface BibliographieServiceRemote<T> extends ICommonService<T, BibliographieDTO>{
    
}
