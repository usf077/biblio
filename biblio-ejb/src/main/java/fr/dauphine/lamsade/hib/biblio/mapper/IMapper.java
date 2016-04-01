package fr.dauphine.lamsade.hib.biblio.mapper;


public interface IMapper<E, D> {
	public E MapFrom(D d);
	public D MapTo(E u);
}
