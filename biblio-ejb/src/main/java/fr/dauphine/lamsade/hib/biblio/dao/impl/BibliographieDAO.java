package fr.dauphine.lamsade.hib.biblio.dao.impl;

import fr.dauphine.lamsade.hib.biblio.dao.IBibliographieDAO;
import fr.dauphine.lamsade.hib.biblio.entitiy.Bibliographie;



public class BibliographieDAO extends BaseDao<Bibliographie> implements IBibliographieDAO {

	public BibliographieDAO(Class<Bibliographie> _type) {
		super(_type);
	}
	

	public BibliographieDAO() {
		super();
	}
	
}