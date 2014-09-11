package com.esprit.ss.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.esprit.ss.domain.JobAd;

@Stateless
@LocalBean
public class JobAdService {

	@PersistenceContext(unitName="StaffingServicesBusiness")
	EntityManager em;
	
	public void addJobAd(JobAd jobAd){
		em.persist(jobAd);
	}
	
	public List<JobAd> getAll() {
		return em.createQuery("FROM JobAd", JobAd.class).getResultList();
	}
	
	public void editJobAd(JobAd jobAd){
		em.merge(jobAd);
	}
	
	public void deleteJobAd(JobAd jobAd){
		em.remove(em.merge(jobAd));
	}
	
	public boolean deleteItems(JobAd[] items) {
        for (JobAd item : items) 
            em.remove(em.merge(item));
        return true;
    }
	
}
