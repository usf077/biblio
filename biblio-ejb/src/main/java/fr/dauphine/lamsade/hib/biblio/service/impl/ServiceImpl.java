
package fr.dauphine.lamsade.hib.biblio.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


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
@Stateless
public abstract class ServiceImpl<T,U> implements ICommonService<T,U> {
    
	protected Class<T> _type;
	private static final Logger logger = Logger.getLogger(ServiceImpl.class.toString());

	@PersistenceContext
	protected EntityManager em;

    public ServiceImpl() {
	
	}
    
    public ServiceImpl(Class<T> type) {
    	this._type = type;
	}


    public U findById(Serializable _id){
        return MapTo( em.find(_type, _id));
    }
    

    public List<U> fetch(){
    	logger.log(Level.INFO, "find all of class " +_type.getName() );
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(_type);
        javax.persistence.Query q = em.createQuery(cq);
        List<T> resultEntites = q.getResultList();
    	List<U> resultsDto = new ArrayList<U>();
    	for (T item : resultEntites) {
    		resultsDto.add(MapTo(item));
		}
        return resultsDto;
    }

    public int count() {
     	CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<T> rt = cq.from(_type);
        cq.select(em.getCriteriaBuilder().countDistinct(rt));
        javax.persistence.Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    

    public void remove(Serializable _id) {
    	 logger.log(Level.INFO, "delete object of class : " +_type.getName() + " with id : " + _id);
         em.remove(em.find(_type,_id));
    }
    

    public U add(U _o)  {
    	 logger.log(Level.INFO, "add object of class " +_type.getName() + " : " + _o);
    	 T t = MapFrom(_o);
         em.persist(t);
         em.flush();
        return MapTo(t);
    }
 

    public U update(U _o)  {
    	logger.log(Level.INFO, "update object of class " +_type.getName() + " : " + _o);
    	T t = MapFrom(_o);
    	em.merge( t);
        em.flush();
        return MapTo(t);
    }

	public Class<T> getType() {
		return _type;
	}

    public List<U> findRestrictedList(int startPosition, int nbElements, String orderBy, String orderSens)
    {
    	CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
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
        List<T> resultsEntites = q.getResultList();

    	List<U> lstDao = new ArrayList<U>();
    	for (T item :  resultsEntites) {
    		lstDao.add(MapTo(item));
		}
        return lstDao;
    }



	public abstract T MapFrom(U u) ;

	public abstract U MapTo(T t) ;
}
