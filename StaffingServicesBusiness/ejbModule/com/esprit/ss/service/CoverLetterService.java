package com.esprit.ss.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.esprit.ss.domain.CoverLetter;

@Stateless
@LocalBean
public class CoverLetterService {

	@PersistenceContext(unitName="StaffingServicesBusiness")
	EntityManager em;
	
	public void addCoverLetter(CoverLetter coverLetter){
		em.persist(coverLetter);
	}
	
	public List<CoverLetter> getAll() {
		return em.createQuery("FROM CoverLetter", CoverLetter.class).getResultList();
	}
	
	public void editCoverLetter(CoverLetter coverLetter){
		em.merge(coverLetter);
	}
	
	public void deleteCoverLetter(CoverLetter coverLetter){
		em.remove(em.merge(coverLetter));
	}
	
	public boolean deleteItems(CoverLetter[] items) {
        for (CoverLetter item : items) 
            em.remove(em.merge(item));
        return true;
    }
	
}
