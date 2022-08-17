package com.wipro.igrs.CitizenFeedback.dto;

import java.io.Serializable;
import org.apache.struts.upload.FormFile;

public class FeedbackComplaintDTO implements Serializable{
	


	/**
	 * @author Sourav Khatri
	 */

	private String districtID;
	private String ServiceID;
	
	private String districtname;
	private String Servicename;
	private String fcAction;
	
	
	

	public String getDistrictName() {
		return districtname;
	}

	public void setDistrictName(String districtname) {
		this.districtname = districtname;
	}


	
	public String getServiceName() {
		return Servicename;
	}

	public void setServiceName(String Servicename) {
		this.Servicename = Servicename;
	}
	
	

	public String getDistrictID() {
		return districtID;
	}

	public void setDistrictID(String districtID) {
		this.districtID = districtID;
	}

	public String getServiceID() {
		return ServiceID;
	}

	public void setServiceID(String ServiceID) {
		ServiceID = ServiceID;
	}


	public void setFeedbackComplaint(String fcAction) {
		this.fcAction = fcAction;
	}
	
	public String getfeedbackComplaint() {
		return fcAction;
	}

	

	

}
