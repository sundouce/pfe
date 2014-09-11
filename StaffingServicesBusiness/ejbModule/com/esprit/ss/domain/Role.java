package com.esprit.ss.domain;

public enum Role {
	
	COMPANY("Company"),
	JOBSEEKER("JobSeeker"),
	ADMINISTRATOR("Administrator");

	private String label;
	
	private Role(String label) {
        this.label = label;
	}
	
	public String getLabel() {
        return label;
    }


}
