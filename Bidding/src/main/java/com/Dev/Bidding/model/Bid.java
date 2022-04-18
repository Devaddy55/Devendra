package com.Dev.Bidding.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Bid {
	@Id
	@GeneratedValue
	private int bidId;
	private String companyName;
	private String metalName;
	private int bidPrice;
	private String bidTime;
	private String bidDate;
	private int status;
	private int phase;
	
	
	public void setBidDate(String bidDate) {
		this.bidDate = bidDate;
	}
	public int getBidId() {
		return bidId;
	}
	public void setBidId(int bidId) {
		this.bidId = bidId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getMetalName() {
		return metalName;
	}
	public void setMetalName(String metalName) {
		this.metalName = metalName;
	}
	public int getBidPrice() {
		return bidPrice;
	}
	public void setBidPrice(int bidPrice) {
		this.bidPrice = bidPrice;
	}
	
	public String getBidTime() {
		return bidTime;
	}
	public void setBidTime(String bidTime) {
		this.bidTime = bidTime;
	}
	public String getBidDate() {
		return bidDate;
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	public int getPhase() {
		return phase;
	}
	public void setPhase(int phase) {
		this.phase = phase;
	}
	@Override
	public String toString() {
		return "[ " + companyName + ", " + metalName + ", "	+ bidPrice + "]";
	}
	

}
