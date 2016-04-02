package fr.dauphine.lamsade.hib.biblio.service.inter;

import javax.ejb.Remote;
import fr.dauphine.lamsade.hib.biblio.modele.CommentaireDTO;

/**
 * 
 * Date : 31/03/2016
 */

@Remote
public interface CommentaireServiceRemote<T> extends ICommonService<T, CommentaireDTO>{
    
}
