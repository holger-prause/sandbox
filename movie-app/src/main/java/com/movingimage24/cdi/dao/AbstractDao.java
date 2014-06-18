package com.movingimage24.cdi.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public abstract class AbstractDao<T> {
    @Inject
    EntityManager entityManager;
		
	Class<T> entityType;
	
    @PostConstruct
    public void doInit() 
    {
    	//will be called multiple times
    	try 
    	{
    		entityType = getEntityType();
    	}

    	catch(Exception ignored)
    	{}
    }
	
	@SuppressWarnings("unchecked")
	public Class<T> getEntityType()
	{
		Type genericSuperclass = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) genericSuperclass;
		return (Class<T>) pt.getActualTypeArguments()[0];
	}	
    
	public void persist(T entity)
	{
		entityManager.persist(entity);
	}
	
	public List<T> list()
	{		
		//tell which type the query will return
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityType);	
		
		//specifiy what to select - in this case just select everything
		Root<T> rootCriteria = criteriaQuery.from(entityType);
		criteriaQuery.select(rootCriteria);
		
		TypedQuery<T> typedQuery 
			= entityManager.createQuery(criteriaQuery);
		return typedQuery.getResultList();
	}
}
