package model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.entities.Entities;

public class GenericDAO<T extends Entities> {

	protected static EntityManagerFactory factory;
	protected EntityManager entity;
	protected Class<T> clasS;
	
	static {
		if(factory == null) {
			factory = Persistence.createEntityManagerFactory("dbagenda");
		}
	}
	
	{
		if(entity == null) {
			entity = factory.createEntityManager();
		}
	}
	
	public GenericDAO() {
		this(null);
	}
	
	public GenericDAO(Class<T> clasS){
		this.clasS = clasS;
	}
	
	public GenericDAO<T> save(T t) {
		entity.persist(t);
		return this;
	}
	
	public T findById(Integer id){
		T t = entity.find(this.clasS, id);
		return t;
	}
	
	public List<T> findAll(){
		if(this.clasS ==  null) {
			
		}
		String jpql = "SELECT u FROM " + clasS.getName() + " u";
		TypedQuery<T> query = entity.createQuery(jpql, this.clasS);
		return query.getResultList();
	}
	
	public GenericDAO<T> update(T t) {
		try{
			entity.merge(t);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public GenericDAO<T> delete(Integer id) {
		T t = findById(id);
		if(t == null) {
			throw new UnsupportedOperationException("Null class.");
		}
		entity.remove(t);
		return this;
	}
	
	public GenericDAO<T> beginTransaction() {
		entity.getTransaction().begin();
		return this;
	}
	
	public GenericDAO<T> commitTransaction() {
		entity.getTransaction().commit();
		return this;
	}
	
	public void closeEntity() {
		entity.close();
		entity = null;
	}
}
