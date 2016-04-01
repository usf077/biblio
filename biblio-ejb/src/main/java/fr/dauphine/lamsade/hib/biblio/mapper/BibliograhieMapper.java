package fr.dauphine.lamsade.hib.biblio.mapper;

import javax.inject.Inject;

import fr.dauphine.lamsade.hib.biblio.entitiy.Bibliographie;
import fr.dauphine.lamsade.hib.biblio.modele.BibliographieDTO;
import fr.dauphine.lamsade.hib.biblio.service.inter.BibliographieServiceRemote;

public class BibliograhieMapper implements IMapper<Bibliographie, BibliographieDTO> {

	@Inject 
	private BibliographieServiceRemote<Bibliographie> bibliographieService ;

	@Override
	public Bibliographie MapFrom(BibliographieDTO d) {
		return bibliographieService.MapFrom(d);
	}

	@Override
	public BibliographieDTO MapTo(Bibliographie u) {
		return  bibliographieService.MapTo(u);
	}

}
