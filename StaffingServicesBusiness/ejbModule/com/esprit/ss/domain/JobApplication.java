package com.esprit.ss.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class JobApplication implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private Date applicationDate;
	private String topic;
	
	private CoverLetter coverLetter;
	private CurriculumVitae curriculumVitae;
	private JobSeeker jobSeeker;
	private Response response;
	private JobAd jobAd;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getApplicationDate() {
		return applicationDate;
	}
	public void setApplicationDate(Date applicationDate) {
		this.applicationDate = applicationDate;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String object) {
		this.topic = object;
	}
	
	@OneToOne
	public CoverLetter getCoverLetter() {
		return coverLetter;
	}
	public void setCoverLetter(CoverLetter coverLetter) {
		this.coverLetter = coverLetter;
	}
	
	@OneToOne
	public CurriculumVitae getCurriculumVitae() {
		return curriculumVitae;
	}
	public void setCurriculumVitae(CurriculumVitae curriculumVitae) {
		this.curriculumVitae = curriculumVitae;
	}
	
	@ManyToOne
	public JobSeeker getJobSeeker() {
		return jobSeeker;
	}
	public void setJobSeeker(JobSeeker jobSeeker) {
		this.jobSeeker = jobSeeker;
	}
	
	@OneToOne
	public Response getResponse() {
		return response;
	}
	public void setResponse(Response response) {
		this.response = response;
	}
	
	@ManyToOne
	public JobAd getJobAd() {
		return jobAd;
	}
	public void setJobAd(JobAd jobAd) {
		this.jobAd = jobAd;
	}

}
