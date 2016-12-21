package com.paul.demo.common.domain;

import java.util.Date;

import javax.persistence.MappedSuperclass;

import com.paul.demo.common.domain.user.User;

@MappedSuperclass
public class Auditor extends Identity{
	private Date createdOn;
	private Long createdBy;
	private Date updatedOn;
	private Long updatedBy;
	
	public void init(User user){
		createdBy = user.getId();
		updatedBy = user.getId();
		createdOn = new Date(); 
		updatedOn = new Date(); 
	}
	
	public void update(User user){
		updatedOn = new Date();
		updatedBy = user.getId(); 
	}
	
	public Date getCreatedOn() {
		return createdOn;
	}
	
	public void setCreatedOn(Date createdOn) { 
		this.createdOn = createdOn;
	}
	
	public Date getUpdatedOn() {
		return updatedOn;
	}
	
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	/**
	 * @return the createdBy
	 */
	public Long getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the updatedBy
	 */
	public Long getUpdatedBy() {
		return updatedBy;
	}

	/**
	 * @param updatedBy the updatedBy to set
	 */
	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	
	
}
