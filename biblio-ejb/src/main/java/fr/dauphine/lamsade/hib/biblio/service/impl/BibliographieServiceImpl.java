package fr.dauphine.lamsade.hib.biblio.service.impl;


import javax.ejb.Stateless;
import fr.dauphine.lamsade.hib.biblio.entitiy.Bibliographie;
import fr.dauphine.lamsade.hib.biblio.entitiy.TypeBibliographie;
import fr.dauphine.lamsade.hib.biblio.modele.BibliographieDTO;
import fr.dauphine.lamsade.hib.biblio.modele.TypeBibliographieDTO;
import fr.dauphine.lamsade.hib.biblio.service.inter.BibliographieServiceRemote;

/**
 * @author Mohamed Youssef Errayhani
 * Date : 29/03/2016
 */
/**
 * Session Bean implementation class Bibliotheque
 */
@Stateless
public class BibliographieServiceImpl extends ServiceImpl<Bibliographie,BibliographieDTO> implements BibliographieServiceRemote<Bibliographie> {


	public BibliographieServiceImpl() {
		super(Bibliographie.class);
	}
   
	@Override
	public Bibliographie MapFrom(BibliographieDTO u) {
		
		if(u.getIdentifiant()!=0){
			Bibliographie b =em.find(Bibliographie.class, u.getIdentifiant());
			if (u.getTypeBibliographie()!=null){
				if(b.getTypeBibliographie()!=null &&b.getTypeBibliographie().getIdTypeBiblio()!=u.getTypeBibliographie().getIdentifiant() ){
					TypeBibliographie tb = em.find(TypeBibliographie.class, u.getTypeBibliographie().getIdentifiant());
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
			TypeBibliographie tb = em.find(TypeBibliographie.class, u.getTypeBibliographie().getIdentifiant());
			tb.setIdTypeBiblio(u.getTypeBibliographie().getIdentifiant());
			b.setLibelle(u.getLibelle());
			b.setTypeBibliographie(tb);
			b.setSource(u.getSource());
			return b;			
		}

	}

	@Override
	public BibliographieDTO MapTo(Bibliographie b) {
		BibliographieDTO bDto = new BibliographieDTO();
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
