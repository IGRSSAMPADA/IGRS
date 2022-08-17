package com.wipro.igrs.device.applet;

import java.io.Serializable;
import java.net.URL;

public class ETokenDTO implements Serializable,Cloneable {
	
	private static final long serialVersionUID = 1L;
	private String registrationId;
	private String noOfPersons;
	private String typeOfPerson;
	private String counterType;
	private String etokenNo;
	private String createdDate;
	private String counterNo;
	private String counterName;
	private String Status;
	private String timeFrom;
	private String timeTo;
	private String slotDate;
	private String appStatus;
	private String assigned="N";
	private String officeId;
	private String ActiveDeactive;
	private int timeRemaining = -1;
	private String language;
	private String waitTime;
	private String counterNoChecker;
	private String waitCounterMaker;
	private String collectCounter;
	
	private URL urls;
	public Object clone(){  
	    try{  
	        return super.clone();  
	    }catch(Exception e){ 
	        return null; 
	    }
	}
	
	public String getTimeFrom() {
		return timeFrom;
	}
	public void setTimeFrom(String timeFrom) {
		this.timeFrom = timeFrom;
	}
	public String getTimeTo() {
		return timeTo;
	}
	public void setTimeTo(String timeTo) {
		this.timeTo = timeTo;
	}
	public String getCounterNo() {
		return counterNo;
	}
	public void setCounterNo(String counterNo) {
		this.counterNo = counterNo;
	}
	public String getCounterName() {
		return counterName;
	}
	public void setCounterName(String counterName) {
		this.counterName = counterName;
	}
	public String getRegistrationId() {
		return registrationId;
	} 
	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}
	public String getNoOfPersons() {
		return noOfPersons;
	}
	public void setNoOfPersons(String noOfPersons) {
		this.noOfPersons = noOfPersons;
	}
	public String getTypeOfPerson() {
		return typeOfPerson;
	}
	public void setTypeOfPerson(String typeOfPerson) {
		this.typeOfPerson = typeOfPerson;
	}
	public String getCounterType() {
		return counterType;
	}
	public void setCounterType(String counterType) {
		this.counterType = counterType;
	}
	public void setEtokenNo(String etokenNo) {
		this.etokenNo = etokenNo;
	}
	public String getEtokenNo() {
		return etokenNo;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getStatus() {
		return Status;
	}
	public void setSlotDate(String slotDate) {
		this.slotDate = slotDate;
	}
	public String getSlotDate() {
		return slotDate;
	}
	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
	}
	public String getAppStatus() {
		return appStatus;
	}
	public void setAssigned(String assigned) {
		this.assigned = assigned;
	}
	public String getAssigned() {
		return assigned;
	}
	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}
	public String getOfficeId() {
		return officeId;
	}
	public void setTimeRemaining(int timeRemaining) {
		this.timeRemaining = timeRemaining;
	}
	public int getTimeRemaining() {
		return timeRemaining;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getLanguage() {
		return language;
	}

	public void setActiveDeactive(String activeDeactive) {
		ActiveDeactive = activeDeactive;
	}

	public String getWaitCounterMaker() {
		return waitCounterMaker;
	}

	public void setWaitCounterMaker(String waitCounterMaker) {
		this.waitCounterMaker = waitCounterMaker;
	}

	public String getCollectCounter() {
		return collectCounter;
	}

	public void setCollectCounter(String collectCounter) {
		this.collectCounter = collectCounter;
	}

	public String getActiveDeactive() {
		return ActiveDeactive;
	}

	public void setWaitTime(String waitTime) {
		this.waitTime = waitTime;
	}

	public String getWaitTime() {
		return waitTime;
	}

	public void setCounterNoChecker(String counterNoChecker) {
		this.counterNoChecker = counterNoChecker;
	}

	public String getCounterNoChecker() {
		return counterNoChecker;
	}

	public void setUrls(URL urls) {
		this.urls = urls;
	}

	public URL getUrls() {
		return urls;
	}
	
	 
}
