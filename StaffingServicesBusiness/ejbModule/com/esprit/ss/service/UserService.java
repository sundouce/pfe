package com.esprit.ss.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.esprit.ss.domain.User;

@Stateless
@LocalBean
public class UserService {

	@PersistenceContext(unitName="StaffingServicesBusiness")
	EntityManager em;
	
	public void addUser(User user){
		em.persist(user);
	}
	
	public List<User> getAll() {
		return em.createQuery("FROM User", User.class).getResultList();
	}
	
	public void editUser(User user){
		em.merge(user);
	}
	
	public void deleteUser(User user){
		em.remove(em.merge(user));
	}
	
	public boolean deleteItems(User[] items) {
        for (User item : items) 
            em.remove(em.merge(item));
        return true;
    }
	
	public int getCount() {
	    return em.createQuery(
	    		"SELECT COUNT(u.mail) FROM User u", Number.class)
	    		.getSingleResult().intValue();
	}
	
	public User getByMail(String mail) {
	    return em.createQuery(
	        "SELECT u FROM User u WHERE u.mail = :mail", User.class)
	        .setParameter("mail", mail).getSingleResult();
	}
	
	public boolean mailExists(String mail) {
		return em.createQuery(
	    		"SELECT COUNT(u.mail) FROM User u WHERE u.mail = :mail", Number.class)
	    		.setParameter("mail", mail).getSingleResult().intValue() == 1;
	}
	
}
