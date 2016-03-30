package fr.dauphine.lamsade.hib.biblio.service.inter;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Remote;


/**
 * Author : Mohamed Youssef Errayhani 
 * Date : 29/03/2016
 */

/**
 * Contrat que doit remplir un service par défaut
 * Le type générique T désigne une Entité, et le type U désigne une classe DTO 
 */
@Remote
public interface ICommonService<T,U>{
    
    
    /**
     * Return the domain class serviced.
     * 
     * @return class
     *      The domain class type serviced.
     */
    public Class<T> getType();
    
    
    /**
     * Generic method used to get a all objects of a particular type. 
     * @return List of populated objects
     */
    public List<U> fetch() ;
    
    /**
     * Generic method used to get total count.  Should be used in sequence
     * with findAll.
     *  
     * @return int number of object found
     */
    public int count();

    
    /**
     * Generic method to get an object based on an identifier. 
     * 
     * @param id the identifier (primary key) of the class
     * @return a populated object 
     */
    public U findById(Serializable _id);

    /**
     * Generic method to save an object.
     * @param o the object to save
     * @return the object created
     */
    public U add(U _o) ;
    
    /**
     * Generic method to update an object.
     * @param o the object to save
     *  @return the object updated
     */
    public U update(U _o) ;


    /**
     * Generic method to delete an object based on id
     * @param id the identifier
     */
    public void remove(Serializable _id) ;
    
    /**
     * Return a restricted list from startPosition to startPosition+nbElements and ordered by orderBy
     * 
     * restriction : only one orderby is allowed
     * 
     * @param startPosition
     * @param nbElements
     * @param orderBy
     * @param orderSens
     * @return a restricted list of results
     */
    public List<U> findRestrictedList (int startPosition, int nbElements, String orderBy, String orderSens );
    
    public T MapFrom(U u);
    public U MapTo(T t);
    
}