
package fr.dauphine.lamsade.hib.biblio.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import fr.dauphine.lamsade.hib.biblio.service.inter.ICommonService;

/**
 * @author: Mohamed Youssef Errayhani 
 * Date : 29/03/2016
 */

/**
 * Base class for Business Services - use this class for utility methods and
 * generic CRUD methods.
 * 
 */


public abstract class ServiceImpl<T> implements ICommonService<T> {
    
	protected Class<T> _type;
	private static final Logger logger = Logger.getLogger(ServiceImpl.class.toString());

	@PersistenceContext
	protected EntityManager em;

    public ServiceImpl() {
	
	}
    
    public ServiceImpl(Class<T> type) {
    	this._type = type;
	}


    public T findById(Serializable _id){
        return  em.find(_type, _id);
    }
    

    @SuppressWarnings("unchecked")
	public List<T> fetch(){
    	logger.log(Level.INFO, "find all of class " +_type.getName() );
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(_type);
        javax.persistence.Query q = em.createQuery(cq);
       
        return q.getResultList();
    }
    
    @SuppressWarnings("unchecked")
    public int count() {
     	CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = (CriteriaQuery<T>) cb.createQuery();
        Root<T> rt = cq.from(_type);
        cq.select((Selection<? extends T>) em.getCriteriaBuilder().countDistinct(rt));
        javax.persistence.Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    

    public void remove(Serializable _id) {
    	 logger.log(Level.INFO, "delete object of class : " +_type.getName() + " with id : " + _id);
         em.remove(em.find(_type,_id));
    }
    

    public T add(T _o)  {
    	 logger.log(Level.INFO, "add object of class " +_type.getName() + " : " + _o);    	
         em.persist(_o);
         em.flush();
        return _o;
    }
 

    public T update(T _o)  {
    	logger.log(Level.INFO, "update object of class " +_type.getName() + " : " + _o);
    	em.merge( _o);
        em.flush();
        return _o;
    }

	public Class<T> getType() {
		return _type;
	}

    @SuppressWarnings("unchecked")
	public List<T> findRestrictedList(int startPosition, int nbElements, String orderBy, String orderSens)
    {
    	CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = (CriteriaQuery<T>) cb.createQuery();
        Root<T> rt = cq.from(_type);
        if ( orderSens.equalsIgnoreCase("ASC") )
        {
        	cq.orderBy(cb.asc(rt.get(orderBy)));
        }
        else
        {
        	cq.orderBy(cb.desc(rt.get(orderBy)));
        }
        javax.persistence.Query q = em.createQuery(cq);
         q.setMaxResults(nbElements);
         q.setFirstResult(startPosition);
       
        return q.getResultList();
    }

}
