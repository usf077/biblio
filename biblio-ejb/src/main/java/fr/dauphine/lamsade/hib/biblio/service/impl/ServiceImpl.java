
package fr.dauphine.lamsade.hib.biblio.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;

import fr.dauphine.lamsade.hib.biblio.service.inter.ICommonService;
import fr.dauphine.lamsade.hib.biblio.dao.IDao;


/**
 * @author: Mohamed Youssef Errayhani 
 * Date : 29/03/2016
 */

/**
 * Base class for Business Services - use this class for utility methods and
 * generic CRUD methods.
 * 
 */
public abstract class ServiceImpl<T,U> implements ICommonService<T,U> {
    
	@Inject
	
    protected IDao<T> dao ;
	protected Class<T> _type;

	
	public void setDao(IDao<T> _dao) {
        this.dao = _dao;
    }

    public ServiceImpl() {
	
	}
    
    public ServiceImpl(Class<T> type) {
    	this._type = type;
	}

    @PostConstruct 
    public void setDaoType(){
    	dao.setType(_type);
    }

    public IDao<T> getDao() {
        return dao;
    }    


    public U findById(Serializable _id){
//    	dao.setType(_type);
        return MapTo( dao.findById(_id));
    }
    

    public List<U> fetch(){
  //  	dao.setType(_type);
    	List<U> lst = new ArrayList<U>();
    	for (T item : dao.findAll()) {
			lst.add(MapTo(item));
		}
        return lst;
    }

    public int count() {
    //	dao.setType(_type);
        return dao.count();
    }
    

    public void remove(Serializable _id) {
    //	dao.setType(_type);
        dao.delete(_id);
    }
    

    public U add(U _o)  {
    //	dao.setType(_type);
        return MapTo(dao.add(MapFrom(_o)));
    }
 

    public U update(U _o)  {
    	//dao.setType(_type);
        return MapTo( (T)dao.update( MapFrom(_o)));
    }

	public Class<T> getType() {
		return getDao().getType();
	}

    public List<U> findRestrictedList(int startPosition, int nbElements, String orderBy, String orderSens)
    {
    //	dao.setType(_type);
    	List<U> lst = new ArrayList<U>();
    	for (T item :  dao.findRestrictedList(startPosition, nbElements, orderBy, orderSens)) {
			lst.add(MapTo(item));
		}
        return lst;
    }



	public abstract T MapFrom(U u) ;

	public abstract U MapTo(T t) ;
}
