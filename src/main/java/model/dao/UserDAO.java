package model.dao;

import javax.persistence.TypedQuery;

import model.entities.User;

public class UserDAO extends GenericDAO<User> {

	public UserDAO() {
		super(User.class);
	}
	
	public User findUser(User user) {
		
		String jpql = "SELECT u FROM User u WHERE u.login = :login AND u.password = :password";
		
		TypedQuery<User> query = entity.createQuery(jpql, clasS)
					.setParameter("login", user.getLogin())
					.setParameter("password", user.getPassword());
		User userFound = query.getSingleResult();
		super.closeEntity();
		return userFound;
	}
}
