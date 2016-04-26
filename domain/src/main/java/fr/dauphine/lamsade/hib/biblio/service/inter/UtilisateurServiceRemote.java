package fr.dauphine.lamsade.hib.biblio.service.inter;


import javax.ejb.Remote;

import fr.dauphine.lamsade.hib.biblio.modele.Utilisateur;


/*
 * @Author : Mehdi HOSNA
 * 
 */

@Remote
public interface UtilisateurServiceRemote extends ICommonService<Utilisateur> {
	Utilisateur getUserByUserName(String Email);
}
