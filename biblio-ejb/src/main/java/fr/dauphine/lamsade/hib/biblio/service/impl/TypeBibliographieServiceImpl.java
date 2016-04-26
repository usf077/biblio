package fr.dauphine.lamsade.hib.biblio.service.impl;


import javax.ejb.Stateless;
import fr.dauphine.lamsade.hib.biblio.modele.*;
import fr.dauphine.lamsade.hib.biblio.service.inter.TypeBibliographieServiceRemote;

/**
 * @author Mohamed Youssef Errayhani
 * Date : 29/03/2016
 */
/**
 * Session Bean implementation class Bibliotheque
 */
@Stateless
public class TypeBibliographieServiceImpl extends ServiceImpl<TypeBibliographie> implements TypeBibliographieServiceRemote {

	public TypeBibliographieServiceImpl() {
		super(TypeBibliographie.class);
	}
   

}
