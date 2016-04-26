package fr.dauphine.lamsade.hib.biblio.service.impl;


import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

import fr.dauphine.lamsade.hib.biblio.modele.Auteur;
import fr.dauphine.lamsade.hib.biblio.modele.Bibliographie;
import fr.dauphine.lamsade.hib.biblio.modele.Commentaire;
import fr.dauphine.lamsade.hib.biblio.modele.TypeBibliographie;
import fr.dauphine.lamsade.hib.biblio.service.inter.BibliographieServiceRemote;

/**
 * @author Mohamed Youssef Errayhani
 * Date : 29/03/2016
 */
/**
 * Session Bean implementation class TypeBibliotheque
 */

@Stateless
public class BibliographieServiceImpl extends ServiceImpl<Bibliographie> implements BibliographieServiceRemote {


	public BibliographieServiceImpl() {
		super(Bibliographie.class);
	}

	@Override
	public int count(String txt, int idTypeBiblio) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Bibliographie> findSearchRestrictedList(String txt, int idTypeBiblio, int startPosition, int nbElements,
			String orderBy, String orderSens) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		Metamodel m = em.getMetamodel();
		EntityType<Bibliographie> Bibliographie_ =  m.entity(Bibliographie.class);
		EntityType<TypeBibliographie> TypeBibliographie_ =  m.entity(TypeBibliographie.class);
        Expression e=null;
        CriteriaQuery cq = cb.createQuery();
        Root<Bibliographie> biblio = cq.from(Bibliographie.class);
        Join<Bibliographie, Auteur> auteur = biblio.join(Bibliographie_.getList("auteurs", Auteur.class));
        Join<Bibliographie, Commentaire> commentaire = biblio.join(Bibliographie_.getList("commentaires", Commentaire.class));
        Join<Bibliographie, TypeBibliographie> Typebiblio = biblio.join(Bibliographie_.getSingularAttribute("typeBibliographie", TypeBibliographie.class));
        biblio.fetch(Bibliographie_.getList("auteurs"));
        biblio.fetch(Bibliographie_.getList("commentaires"));
        if (idTypeBiblio != 0) {
            e = cb.equal(Typebiblio.get("idTypeBiblio"), idTypeBiblio);
        }
      if (txt != null && !txt.isEmpty()) {
            if (e == null) {
                e = cb.or(cb.like(cb.lower(biblio.get("libelle")), "%" + txt.toLowerCase() + "%"), cb.like(cb.lower(biblio.get("source")), "%" + txt.toLowerCase() + "%"), cb.like(cb.lower(auteur.get("nom")), "%" + txt.toLowerCase() + "%"),
                        cb.like(cb.lower(auteur.get("prenom")), "%" + txt.toLowerCase() + "%"));
            } else {
                e = cb.and(e, cb.or(cb.like(cb.lower(biblio.get("libelle")), "%" + txt.toLowerCase() + "%"), cb.like(cb.lower(biblio.get("source")), "%" + txt.toLowerCase() + "%"), cb.like(cb.lower(auteur.get("nom")), "%" + txt.toLowerCase() + "%"),
                        cb.like(cb.lower(auteur.get("prenom")), "%" + txt.toLowerCase() + "%")));
            }
        }
        if (e!=null){
            cq.where(e);
        }
        if ( orderSens.equalsIgnoreCase("ASC") )
        {
        	cq.orderBy(cb.asc(biblio.get(orderBy)));
        }
        else
        {
        	cq.orderBy(cb.desc(biblio.get(orderBy)));
        }
        cq.select(biblio).distinct(true);
        javax.persistence.Query q = em.createQuery(cq);
        q.setMaxResults(nbElements);
        q.setFirstResult(startPosition);
        return q.getResultList();
       

	}

	
   

}
