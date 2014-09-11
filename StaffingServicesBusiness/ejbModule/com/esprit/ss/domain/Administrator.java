package com.esprit.ss.domain;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class Administrator extends User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Administrator() {
		super();
		setRole(Role.ADMINISTRATOR);
	}
	
	public Administrator(User u) {
		super(u);
		setRole(Role.ADMINISTRATOR);
	}

}
