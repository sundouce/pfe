package com.esprit.ss.managedBean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.esprit.ss.domain.JobAd;
import com.esprit.ss.service.JobAdService;

@ManagedBean
@SessionScoped

public class JobAdCtrl implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JobAdService jobAdDAO;
	
	private List<JobAd> lazyModel;
	private List<JobAd> filteredJobAds;

	
	public JobAdCtrl() {
		super();
		try {
			jobAdDAO = (JobAdService) new InitialContext().lookup("java:global/StaffingServicesWeb-portlet/JobAdService!com.esprit.ss.service.JobAdService");		
		} catch (NamingException e1) {
			e1.printStackTrace();
		}
		
		
	}


	public List<JobAd> getLazyModel() {
		lazyModel = jobAdDAO.getAll();
		return lazyModel;
	}


	public void setLazyModel(List<JobAd> lazyModel) {
		this.lazyModel = lazyModel;
	}


	public List<JobAd> getFilteredJobAds() {
		return filteredJobAds;
	}


	public void setFilteredJobAds(List<JobAd> filteredJobAds) {
		this.filteredJobAds = filteredJobAds;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
