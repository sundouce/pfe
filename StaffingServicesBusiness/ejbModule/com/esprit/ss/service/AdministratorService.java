package com.esprit.ss.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.esprit.ss.domain.Administrator;

@Stateless
@LocalBean
public class AdministratorService {

	@PersistenceContext(unitName="StaffingServicesBusiness")
	EntityManager em;
	
	public void addAdministrator(Administrator administrator){
		em.persist(administrator);
	}
	
	public List<Administrator> getAll() {
		return em.createQuery("FROM Administrator", Administrator.class).getResultList();
	}
	
	public void editAdministrator(Administrator administrator){
		em.merge(administrator);
	}
	
	public void deleteAdministrator(Administrator administrator){
		em.remove(em.merge(administrator));
	}
	
	public boolean deleteItems(Administrator[] items) {
        for (Administrator item : items) 
            em.remove(em.merge(item));
        return true;
    }
	
}
