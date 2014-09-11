package com.esprit.ss.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.esprit.ss.domain.JobApplication;

@Stateless
@LocalBean
public class JobApplicationService {

	@PersistenceContext(unitName="StaffingServicesBusiness")
	EntityManager em;
	
	public void addJobApplication(JobApplication jobApplication){
		em.persist(jobApplication);
	}
	
	public List<JobApplication> getAll() {
		return em.createQuery("FROM JobApplication", JobApplication.class).getResultList();
	}
	
	public void editJobApplication(JobApplication jobApplication){
		em.merge(jobApplication);
	}
	
	public void deleteJobApplication(JobApplication jobApplication){
		em.remove(em.merge(jobApplication));
	}
	
	public boolean deleteItems(JobApplication[] items) {
        for (JobApplication item : items) 
            em.remove(em.merge(item));
        return true;
    }
	
}
