package fr.dauphine.lamsade.hib.biblio.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.dauphine.lamsade.hib.biblio.dao.IDao;
import fr.dauphine.lamsade.hib.biblio.dao.impl.BibliographieDAO;
import fr.dauphine.lamsade.hib.biblio.entitiy.Bibliographie;
import fr.dauphine.lamsade.hib.biblio.entitiy.TypeBibliographie;
import fr.dauphine.lamsade.hib.biblio.modele.BibliographieDTO;
import fr.dauphine.lamsade.hib.biblio.modele.TypeBibliographieDTO;
import fr.dauphine.lamsade.hib.biblio.service.inter.BibliographieServiceRemote;
import fr.dauphine.lamsade.hib.biblio.util.DBConnexion;


/**
 * Author : Mohamed Youssef Errayhani 
 * Date : 29/03/2016
 */

/**
 * Session Bean implementation class Bibliotheque
 */
@Stateless
public class BibliographieServiceImpl extends ServiceImpl<Bibliographie,BibliographieDTO> implements BibliographieServiceRemote<Bibliographie> {

    @Inject
	IDao<TypeBibliographie> daoType;
	
	public BibliographieServiceImpl() {
		this._type =Bibliographie.class;	
	}
	
	
	@Override
	public Bibliographie MapFrom(BibliographieDTO u) {
		
		if(u.getIdentifiant()!=0){
			Bibliographie b = dao.findById(u.getIdentifiant());
			daoType.setType(TypeBibliographie.class);
			if (u.getTypeBibliographie()!=null){
				if(b.getTypeBibliographie()!=null &&b.getTypeBibliographie().getIdTypeBiblio()!=u.getTypeBibliographie().getIdentifiant() ){
					TypeBibliographie tb = daoType.findById(u.getTypeBibliographie().getIdentifiant());
					b.setTypeBibliographie(tb);
				}
			}
			else{
				b.setTypeBibliographie(null);
			}
			b.setLibelle(u.getLibelle());
			b.setSource(u.getSource());
			return b;
		}else{
			Bibliographie b = new Bibliographie();
			daoType.setType(TypeBibliographie.class);
			TypeBibliographie tb = daoType.findById(u.getTypeBibliographie().getIdentifiant());
			tb.setIdTypeBiblio(u.getTypeBibliographie().getIdentifiant());
			b.setLibelle(u.getLibelle());
			b.setTypeBibliographie(tb);
			b.setSource(u.getSource());
			return b;			
		}

	}

	@Override
	public BibliographieDTO MapTo(Bibliographie b) {
		BibliographieDTO bDto =  new BibliographieDTO();
		bDto.setIdentifiant(b.getIdBiblio());
		bDto.setLibelle(b.getLibelle());
		bDto.setSource(b.getSource());
		if(b.getTypeBibliographie()!=null){
			TypeBibliographieDTO tbDTO = new TypeBibliographieDTO();
			tbDTO.setIdentifiant(b.getTypeBibliographie().getIdTypeBiblio());
			tbDTO.setLibelle(b.getTypeBibliographie().getLibelle());
			bDto.setTypeBibliographie(tbDTO);
		}
		return bDto;
	}

}
