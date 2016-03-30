package fr.dauphine.lamsade.hib.biblio.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import fr.dauphine.lamsade.hib.biblio.dao.IDao;


/**
 * Author : Mohamed Youssef Errayhani 
 * Date : 29/03/2016
 */

/**
 * This class serves as the Base class for all other Daos - namely to hold
 * common methods that they might all use. Can be used for standard CRUD
 * operations.</p>
 *
 */
@Stateless
public class BaseDao <T>  implements IDao <T> 
{
    /**
     * logger
     */
    private static final Logger logger = Logger.getLogger(BaseDao.class.toString());
    
    private Class<T> type;
    @PersistenceContext
    private EntityManager em;
    
    

    @SuppressWarnings("unchecked")
    public List<T> findRestrictedList (int startPosition, int nbElements, String orderBy, String orderSens )
    {
    	CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<T> rt = cq.from(type);
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
        List<T> results = q.getResultList();
        return results;
        
    }    

    /**
     * Constructeur avec le type d'objet interrogé par le DAO
     * @param type
     */
    
    public BaseDao(Class<T> _type) {
        this.type = _type;
    }

    public BaseDao() {
    }

    
    public T add(T _o) {
       
        logger.log(Level.INFO, "add object of class " +type.getName() + " : " + _o);
        em.persist(_o);
        em.flush();
        return _o;
    }

    
    public T update(T _o) {
    	logger.log(Level.INFO, "update object of class " +type.getName() + " : " + _o);
        em.merge(_o);
        em.flush();
        return _o;
    }

    
    @SuppressWarnings("unchecked")
    public T findById(Serializable _id) 
    {
       logger.log(Level.INFO, "find object of class " +type.getName() + " with id : " + _id);
       T o = (T) em.find(type, _id);
        return o;        
    }


    @SuppressWarnings("unchecked")
    public List<T> findAll() {
    	logger.log(Level.INFO, "find all of class " +type.getName() );
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(type);
        javax.persistence.Query q = em.createQuery(cq);
        List<T> results = q.getResultList();
        return results;
    }
    
    
    public int count() {
    	 CriteriaBuilder cb = em.getCriteriaBuilder();
         CriteriaQuery cq = cb.createQuery();
         Root<T> rt = cq.from(type);
         cq.select(em.getCriteriaBuilder().countDistinct(rt));
         javax.persistence.Query q = em.createQuery(cq);
         return ((Long) q.getSingleResult()).intValue();
    }


    public void delete(Serializable _id) {
       
        logger.log(Level.INFO, "delete object of class : " +type.getName() + " with id : " + _id);
        em.remove(findById(_id));
    }
    
	
    public Class<T> getType() {
		return type;
	}
	
	
    public void setType(Class<T> type) {
		this.type = type;
	}


	
    public void delete(T _o)
    {
    	logger.log(Level.INFO,"delete object of class : " +type.getName() + " : " + _o);
        em.remove(_o);
        em.flush();
    }
}
