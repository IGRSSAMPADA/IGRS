/**
 * 
 */
package com.wipro.igrs.departmentalenquiry.dto;

import java.io.Serializable;

/**
 * @author HMOHAM
 *
 */
public class ComplaintStatusDTO implements Serializable {

	private String statusId;
	private String statusName;
	/**
	 * @return the statusId
	 */
	public String getStatusId() {
		return statusId;
	}
	/**
	 * @param statusId the statusId to set
	 */
	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}
	/**
	 * @return the statusName
	 */
	public String getStatusName() {
		return statusName;
	}
	/**
	 * @param statusName the statusName to set
	 */
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	
	
}
