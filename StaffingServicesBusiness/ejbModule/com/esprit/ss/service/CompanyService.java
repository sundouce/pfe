package com.esprit.ss.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.esprit.ss.domain.Company;

@Stateless
@LocalBean
public class CompanyService {

	@PersistenceContext(unitName="StaffingServicesBusiness")
	EntityManager em;
	
	public void addCompany(Company company){
		em.persist(company);
	}
	
	public List<Company> getAll() {
		return em.createQuery("FROM Company", Company.class).getResultList();
	}
	
	public void editCompany(Company company){
		em.merge(company);
	}
	
	public void deleteCompany(Company company){
		em.remove(em.merge(company));
	}
	
	public boolean deleteItems(Company[] items) {
        for (Company item : items) 
            em.remove(em.merge(item));
        return true;
    }
	
}
