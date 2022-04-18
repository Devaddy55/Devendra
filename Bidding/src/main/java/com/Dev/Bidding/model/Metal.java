package com.Dev.Bidding.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Metal {
	
	@Id
	private int metalid;
	private String metalname;

	public String getMetalname() {
		return metalname;
	}

	public int getMetalid() {
		return metalid;
	}

	public void setMetalid(int metalid) {
		this.metalid = metalid;
	}

	public void setMetalname(String metalname) {
		this.metalname = metalname;
	}
	

}
