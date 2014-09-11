package com.esprit.ss.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.esprit.ss.domain.JobSeeker;

@Stateless
@LocalBean
public class JobSeekerService {

	@PersistenceContext(unitName="StaffingServicesBusiness")
	EntityManager em;
	
	public void addJobSeeker(JobSeeker jobSeeker){
		em.persist(jobSeeker);
	}
	
	public List<JobSeeker> getAll() {
		return em.createQuery("FROM JobSeeker", JobSeeker.class).getResultList();
	}
	
	public void editJobSeeker(JobSeeker jobSeeker){
		em.merge(jobSeeker);
	}
	
	public void deleteJobSeeker(JobSeeker jobSeeker){
		em.remove(em.merge(jobSeeker));
	}
	
	public boolean deleteItems(JobSeeker[] items) {
        for (JobSeeker item : items) 
            em.remove(em.merge(item));
        return true;
    }
	
}
