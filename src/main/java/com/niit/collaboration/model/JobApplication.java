package com.niit.collaboration.model;

import java.util.Date;

import javax.persistence.Id;

public class JobApplication {

	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String userId;
	private int jobId;
    private Date appliedDate;
    private String remarks;
    private char status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getJobId() {
		return jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	public Date getDateApplied() {
		return appliedDate;
	}
	public void setDateApplied(Date appliedDate) {
		this.appliedDate = appliedDate;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}

}
