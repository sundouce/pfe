package com.esprit.ss.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.esprit.ss.domain.CurriculumVitae;

@Stateless
@LocalBean
public class CurriculumVitaeService {

	@PersistenceContext(unitName="StaffingServicesBusiness")
	EntityManager em;
	
	public void addCurriculumVitae(CurriculumVitae curriculumVitae){
		em.persist(curriculumVitae);
	}
	
	public List<CurriculumVitae> getAll() {
		return em.createQuery("FROM CurriculumVitae", CurriculumVitae.class).getResultList();
	}
	
	public void editCurriculumVitae(CurriculumVitae curriculumVitae){
		em.merge(curriculumVitae);
	}
	
	public void deleteCurriculumVitae(CurriculumVitae curriculumVitae){
		em.remove(em.merge(curriculumVitae));
	}
	
	public boolean deleteItems(CurriculumVitae[] items) {
        for (CurriculumVitae item : items) 
            em.remove(em.merge(item));
        return true;
    }
	
}
