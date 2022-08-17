package com.wipro.igrs.ACL.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class OfficeDTO implements Serializable {

	private static final long serialVersionUID = 1740851901246871026L;
	private String officeID;
	private String officeName;

	ArrayList officeList = new ArrayList();
	ArrayList activityList = new ArrayList();
	
	/**
	 * @return the officeID
	 */
	public String getOfficeID() {
		return officeID;
	}

	/**
	 * @param officeID
	 *            the officeID to set
	 */
	public void setOfficeID(String officeID) {
		this.officeID = officeID;
	}

	/**
	 * @return the officeName
	 */
	public String getOfficeName() {
		return officeName;
	}

	/**
	 * @param officeName
	 *            the officeName to set
	 */
	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public ArrayList getOfficeList() {
		return officeList;
	}

	public void setOfficeList(ArrayList officeList) {
		this.officeList = officeList;
	}

	public ArrayList getActivityList() {
		return activityList;
	}

	public void setActivityList(ArrayList activityList) {
		this.activityList = activityList;
	}
	
	
}
