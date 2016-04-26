package fr.dauphine.lamsade.hib.biblio.service.impl;

import java.util.List;

import javax.ejb.Stateless;

import fr.dauphine.lamsade.hib.biblio.modele.Utilisateur;
import fr.dauphine.lamsade.hib.biblio.service.inter.UtilisateurServiceRemote;

/*
 * @Author :  Mehdi HOSNA 
 */
@Stateless
public class UtilisateurServiceImpl extends ServiceImpl<Utilisateur> implements UtilisateurServiceRemote{

	
	public UtilisateurServiceImpl() {
		super(Utilisateur.class);
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public Utilisateur getUserByUserName(String Email) {
		
		List<Utilisateur> utilisateurList = em.createNamedQuery("Utilisateur.findByEmail").setParameter("mail", Email).getResultList();
		Utilisateur u =  utilisateurList.isEmpty() ? null : utilisateurList.get(0);
		return u;
		}

}
