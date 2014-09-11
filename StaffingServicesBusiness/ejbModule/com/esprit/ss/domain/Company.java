package com.esprit.ss.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;


@Entity
public class Company extends User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String website;
	private String description;
	
	private List<JobAd> jobAds;
	
	public Company() {
		super();
		setRole(Role.COMPANY);
	}
	
	public Company(User u) {
		super(u);
		setRole(Role.COMPANY);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@OneToMany(mappedBy="company")
	public List<JobAd> getJobAds() {
		return jobAds;
	}
	public void setJobAds(List<JobAd> jobAds) {
		this.jobAds = jobAds;
	}
	
	

}
