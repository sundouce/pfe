package com.esprit.ss.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.esprit.ss.domain.Response;

@Stateless
@LocalBean
public class ResponseService {

	@PersistenceContext(unitName="StaffingServicesBusiness")
	EntityManager em;
	
	public void addResponse(Response response){
		em.persist(response);
	}
	
	public List<Response> getAll() {
		return em.createQuery("FROM Response", Response.class).getResultList();
	}
	
	public void editResponse(Response response){
		em.merge(response);
	}
	
	public void deleteResponse(Response response){
		em.remove(em.merge(response));
	}
	
	public boolean deleteItems(Response[] items) {
        for (Response item : items) 
            em.remove(em.merge(item));
        return true;
    }
	
}
