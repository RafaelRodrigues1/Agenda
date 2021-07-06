package model.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import model.entities.Contact;
import model.entities.User;

public class ContactDAO extends GenericDAO<Contact> {

	public ContactDAO() {
		super(Contact.class);
	}
	
	public List<Contact> findAll(User user){
		
		String jpql = "SELECT c FROM Contact c WHERE c.user = :user_id";
		TypedQuery<Contact> query = entity.createQuery(jpql, Contact.class)
						.setParameter("user_id", user);
		List<Contact> contactList = query.getResultList();
		super.closeEntity();
		return contactList;
	}
}
