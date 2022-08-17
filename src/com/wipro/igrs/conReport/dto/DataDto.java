package com.wipro.igrs.conReport.dto;


/**
 * ===========================================================================
 * File           :   DataDTO.java
 * Description    :   Represents the Data DTO Class ECR
 * @author        :   Imran Shaik
 * Created Date   :   September 08, 2008
 * ===========================================================================
 */

import java.io.Serializable;

public class DataDto implements Serializable{
	private String id;
	private String name;
	private String fiYear;
	private String repStatus;
	
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the fiYear
	 */
	public String getFiYear() {
		return fiYear;
	}
	/**
	 * @param fiYear the fiYear to set
	 */
	public void setFiYear(String fiYear) {
		this.fiYear = fiYear;
	}
	/**
	 * @return the repStatus
	 */
	public String getRepStatus() {
		return repStatus;
	}
	/**
	 * @param repStatus the repStatus to set
	 */
	public void setRepStatus(String repStatus) {
		this.repStatus = repStatus;
	}
}