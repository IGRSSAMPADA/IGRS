package com.wipro.igrs.slotbooking.dto;

import java.io.Serializable;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

/**
 * ===========================================================================
 * File : SlotBookDTO.java Description : Represents the Business Class
 * 
 * Author : Hari Krishna GV Created Date : Nov 28, 2007
 * 
 * ===========================================================================
 */
public class

SlotBookDTO implements Serializable{
	
	
	private String actiontaken;//added by ankita 11.10.2012
	private String remarks;
	private String slotFlag;
	private String errorMsg;
	private String language;

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	private String pageName;
	private String distId;
	private String regId;
	private String distName;
	private ArrayList bookList;
	private ArrayList sroNameList;
	private ArrayList sroSlotList;
	private ArrayList availableSro;
	private ArrayList userReportList;
	private String sroName;
	private String sroId;
	private String slotdate;
	//Start Added by Ankita
	private String label;
	//End Added by Ankita
	//Start Added by SreeLatha for slotBooking Amount
	
	private String slotAmount;
	private ArrayList srNameList;
	private String srName;
	private String srId;
	
	//End Added by SreeLatha for slotBooking Amount
	
	//Start===Added by SreeLatha for SlotBooking View
    private String refId;    
    private String status;
    private String radiodate = "refID";     
    private String actionID;
    private String durationFrom;
    private String durationTo;
    private int sno;  
    private String appNumber;
    private String visitDate;
    private String visitTime;
    private ArrayList slotBkViewList = new ArrayList();
    //End===Added by SreeLatha for SlotBooking View
    
	private String timesSlot;
	private String timeSlotId;
	private String availSro;
	private String availSroId;
	private String avlsroname;
	private String selctsroname;
	private String selctslottime;
	private String selctslottimedisp;
	private String gendar;
	private String selectdistid;
	private String selectsroid;
	private String slotRefId;
	private String gobutton;
	private String userId;
	private String createdBy;
	private String userStatus;
    private String button;
    
    
    
    
    //Added on 25-6-2013 to accomodate DB restructring
    
    private String roleGroupId;
    private String roleID;
    private String check;
      
   
	private String slctDistrict;
    private String slctSrOffice;
    private String slctSrId;
    private String slctDate;
    
    
    private String slctSrDistrict;
    private String slctOffice;
    private String slctScheduleOffice;
    
    
    
    private String usrSlctDist;
    private String usrSlctTehsil;
    private String usrSlctOffice;
    
    private String tehsilId;
    private String tehsilName;
    ArrayList tehsilList = new ArrayList();
    
    
    private String officeID;
    private String officeName;
    ArrayList officeList = new ArrayList();
    private String sroTime;
    
    private String reSchedule="New";
    private String selectedDate;
    
    private String slotDateSys;
    
    
    
    
    
    
    public String getSlotDateSys() {
		return slotDateSys;
	}

	public void setSlotDateSys(String slotDateSys) {
		this.slotDateSys = slotDateSys;
	}

	public String getSelectedDate() {
		return selectedDate;
	}

	public void setSelectedDate(String selectedDate) {
		this.selectedDate = selectedDate;
	}

	public String getReSchedule() {
		return reSchedule;
	}

	public void setReSchedule(String reSchedule) {
		this.reSchedule = reSchedule;
	}
    
    
    
    
    
    public String getSroTime() {
		return sroTime;
	}

	public void setSroTime(String sroTime) {
		this.sroTime = sroTime;
	}

	public String getCheck() {
		return check;
	}

	public void setCheck(String check) {
		this.check = check;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public ArrayList getOfficeList() {
		return officeList;
	}

	public void setOfficeList(ArrayList officeList) {
		this.officeList = officeList;
	}
    
    
    
    public ArrayList getTehsilList() {
		return tehsilList;
	}

	public void setTehsilList(ArrayList tehsilList) {
		this.tehsilList = tehsilList;
	}

	public String getTehsilId() {
		return tehsilId;
	}

	public void setTehsilId(String tehsilId) {
		this.tehsilId = tehsilId;
	}

	public String getTehsilName() {
		return tehsilName;
	}

	public void setTehsilName(String tehsilName) {
		this.tehsilName = tehsilName;
	}

	public String getUsrSlctDist() {
		return usrSlctDist;
	}

	public void setUsrSlctDist(String usrSlctDist) {
		this.usrSlctDist = usrSlctDist;
	}

	public String getUsrSlctTehsil() {
		return usrSlctTehsil;
	}

	public void setUsrSlctTehsil(String usrSlctTehsil) {
		this.usrSlctTehsil = usrSlctTehsil;
	}

	public String getUsrSlctOffice() {
		return usrSlctOffice;
	}

	public void setUsrSlctOffice(String usrSlctOffice) {
		this.usrSlctOffice = usrSlctOffice;
	}

	public String getSlctScheduleOffice() {
		return slctScheduleOffice;
	}

	public void setSlctScheduleOffice(String slctScheduleOffice) {
		this.slctScheduleOffice = slctScheduleOffice;
	}

	public String getSlctSrDistrict() {
		return slctSrDistrict;
	}

	public void setSlctSrDistrict(String slctSrDistrict) {
		this.slctSrDistrict = slctSrDistrict;
	}

	public String getSlctOffice() {
		return slctOffice;
	}

	public void setSlctOffice(String slctOffice) {
		this.slctOffice = slctOffice;
	}

	public String getSlctDate() {
		return slctDate;
	}

	public void setSlctDate(String slctDate) {
		this.slctDate = slctDate;
	}

	public String getSlctDistrict() {
		return slctDistrict;
	}

	public void setSlctDistrict(String slctDistrict) {
		this.slctDistrict = slctDistrict;
	}

	public String getSlctSrOffice() {
		return slctSrOffice;
	}

	public void setSlctSrOffice(String slctSrOffice) {
		this.slctSrOffice = slctSrOffice;
	}

	public String getSlctSrId() {
		return slctSrId;
	}

	public void setSlctSrId(String slctSrId) {
		this.slctSrId = slctSrId;
	}

	public String getRoleGroupId() {
		return roleGroupId;
	}

	public void setRoleGroupId(String roleGroupId) {
		this.roleGroupId = roleGroupId;
	}

	public String getRoleID() {
		return roleID;
	}

	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}

	public String getOfficeID() {
		return officeID;
	}

	public void setOfficeID(String officeID) {
		this.officeID = officeID;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	public String getButton() {
	return button;
}

public void setButton(String button) {
	this.button = button;
}

	/**
	 * @return
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return
	 */
	public String getSlotRefId() {
		return slotRefId;
	}

	/**
	 * @param slotRefId
	 */
	public void setSlotRefId(String slotRefId) {
		this.slotRefId = slotRefId;
	}

	/**
	 * @return
	 */
	public String getSelectsroid() {
		return selectsroid;
	}

	/**
	 * @param selectsroid
	 */
	public void setSelectsroid(String selectsroid) {
		this.selectsroid = selectsroid;
	}

	/**
	 * @return
	 */
	public String getSelectdistid() {
		return selectdistid;
	}

	/**
	 * @param selectdistid
	 */
	public void setSelectdistid(String selectdistid) {
		this.selectdistid = selectdistid;
	}

	/**
	 * @param distId
	 */
	public void setDistId(String distId) {
		this.distId = distId;
	}

	/**
	 * @return
	 */
	public String getDistId() {
		return distId;
	}

	/**
	 * @param distName
	 */
	public void setDistName(String distName) {
		this.distName = distName;
	}

	/**
	 * @return
	 */
	public String getDistName() {
		return distName;
	}

	/**
	 * @param bookList
	 */
	public void setBookList(ArrayList bookList) {
		this.bookList = bookList;
	}

	/**
	 * @return
	 */
	public ArrayList getBookList() {
		return bookList;
	}

	/**
	 * @param sroName
	 */
	public void setSroName(String sroName) {
		this.sroName = sroName;
	}

	/**
	 * @return
	 */
	public String getSroName() {
		return sroName;
	}

	/**
	 * @param sroId
	 */
	public void setSroId(String sroId) {
		this.sroId = sroId;
	}

	/**
	 * @return
	 */
	public String getSroId() {
		return sroId;
	}

	/**
	 * @param slotdate
	 */
	public void setSlotdate(String slotdate) {
		this.slotdate = slotdate;
	}

	/**
	 * @return
	 */
	public String getSlotdate() {
		return slotdate;
	}

	/**
	 * @param sroNameList
	 */
	public void setSroNameList(ArrayList sroNameList) {
		this.sroNameList = sroNameList;
	}

	/**
	 * @return
	 */
	public ArrayList getSroNameList() {
		return sroNameList;
	}

	/**
	 * @param timesSlot
	 */
	public void setTimesSlot(String timesSlot) {
		this.timesSlot = timesSlot;
	}

	/**
	 * @return
	 */
	public String getTimesSlot() {
		return timesSlot;
	}

	/**
	 * @param sroSlotList
	 */
	public void setSroSlotList(ArrayList sroSlotList) {
		this.sroSlotList = sroSlotList;
	}

	/**
	 * @return
	 */
	public ArrayList getSroSlotList() {
		return sroSlotList;
	}

	/**
	 * @param availableSro
	 */
	public void setAvailableSro(ArrayList availableSro) {
		this.availableSro = availableSro;
	}

	/**
	 * @return
	 */
	public ArrayList getAvailableSro() {
		return availableSro;
	}

	/**
	 * @param availSro
	 */
	public void setAvailSro(String availSro) {
		this.availSro = availSro;
	}

	/**
	 * @return
	 */
	public String getAvailSro() {
		return availSro;
	}

	/**
	 * @param avlsroname
	 */
	public void setAvlsroname(String avlsroname) {
		this.avlsroname = avlsroname;
	}

	/**
	 * @return
	 */
	public String getAvlsroname() {
		return avlsroname;
	}

	/**
	 * @param gendar
	 */
	public void setGendar(String gendar) {
		this.gendar = gendar;
	}

	/**
	 * @return
	 */
	public String getGendar() {
		return gendar;
	}

	/**
	 * @param selctsroname
	 */
	public void setSelctsroname(String selctsroname) {
		this.selctsroname = selctsroname;
	}

	/**
	 * @return
	 */
	public String getSelctsroname() {
		return selctsroname;
	}

	/**
	 * @param selctslottime
	 */
	public void setSelctslottime(String selctslottime) {
		this.selctslottime = selctslottime;
	}

	/**
	 * @return
	 */
	public String getSelctslottime() {
		return selctslottime;
	}

	/**
	 * @return
	 */
	public String getTimeSlotId() {
		return timeSlotId;
	}

	/**
	 * @param timeSlotId
	 */
	public void setTimeSlotId(String timeSlotId) {
		this.timeSlotId = timeSlotId;
	}

	/**
	 * @return
	 */
	public String getAvailSroId() {
		return availSroId;
	}

	/**
	 * @param availSroId
	 */
	public void setAvailSroId(String availSroId) {
		this.availSroId = availSroId;
	}

	/**
	 * @return
	 */
	public String getSelctslottimedisp() {
		return selctslottimedisp;
	}

	/**
	 * @param selctslottimedisp
	 */
	public void setSelctslottimedisp(String selctslottimedisp) {
		this.selctslottimedisp = selctslottimedisp;
	}

	/**
	 * @return
	 */
	public String getGobutton() {
		return gobutton;
	}

	/**
	 * @param gobutton
	 */
	public void setGobutton(String gobutton) {
		this.gobutton = gobutton;
	}

	/**
	 * @return
	 */
	public String getRegId() {
		return regId;
	}

	/**
	 * @param regId
	 */
	public void setRegId(String regId) {
		this.regId = regId;
	}

	/**
	 * @return
	 */
	public String getPageName() {
		return pageName;
	}

	/**
	 * @param pageName
	 */
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	/**
	 * @return
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return
	 */
	public String getUserStatus() {
		return userStatus;
	}

	/**
	 * @param userStatus
	 */
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	/**
	 * @return
	 */
	public ArrayList getUserReportList() {
		return userReportList;
	}

	/**
	 * @param userReportList
	 */
	public void setUserReportList(ArrayList userReportList) {
		this.userReportList = userReportList;
	}

	/**
	 * @return the slotAmount
	 */
	public String getSlotAmount() {
		return slotAmount;
	}

	/**
	 * @param slotAmount the slotAmount to set
	 */
	public void setSlotAmount(String slotAmount) {
		this.slotAmount = slotAmount;
	}

	/**
	 * @return the srNameList
	 */
	public ArrayList getSrNameList() {
		return srNameList;
	}

	/**
	 * @param srNameList the srNameList to set
	 */
	public void setSrNameList(ArrayList srNameList) {
		this.srNameList = srNameList;
	}

	/**
	 * @return the srName
	 */
	public String getSrName() {
		return srName;
	}

	/**
	 * @param srName the srName to set
	 */
	public void setSrName(String srName) {
		this.srName = srName;
	}

	/**
	 * @return the srId
	 */
	public String getSrId() {
		return srId;
	}

	/**
	 * @param srId the srId to set
	 */
	public void setSrId(String srId) {
		this.srId = srId;
	}

	/**
	 * @return the refId
	 */
	public String getRefId() {
		return refId;
	}

	/**
	 * @param refId the refId to set
	 */
	public void setRefId(String refId) {
		this.refId = refId;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the radiodate
	 */
	public String getRadiodate() {
		return radiodate;
	}

	/**
	 * @param radiodate the radiodate to set
	 */
	public void setRadiodate(String radiodate) {
		this.radiodate = radiodate;
	}

	/**
	 * @return the actionID
	 */
	public String getActionID() {
		return actionID;
	}

	/**
	 * @param actionID the actionID to set
	 */
	public void setActionID(String actionID) {
		this.actionID = actionID;
	}

	/**
	 * @return the durationFrom
	 */
	public String getDurationFrom() {
		return durationFrom;
	}

	/**
	 * @param durationFrom the durationFrom to set
	 */
	public void setDurationFrom(String durationFrom) {
		this.durationFrom = durationFrom;
		
		//added by ankita
		
		System.out.println(this.durationFrom);
	}

	/**
	 * @return the durationTo
	 */
	public String getDurationTo() {
		return durationTo;
	}

	/**
	 * @param durationTo the durationTo to set
	 */
	public void setDurationTo(String durationTo) {
		this.durationTo = durationTo;
		
		//added by ankita
	//	System.out.println(this.durationTo);
	}

	/**
	 * @return the sno
	 */
	public int getSno() {
		return sno;
	}

	/**
	 * @param sno the sno to set
	 */
	public void setSno(int sno) {
		this.sno = sno;
	}

	/**
	 * @return the appNumber
	 */
	public String getAppNumber() {
		return appNumber;
	}

	/**
	 * @param appNumber the appNumber to set
	 */
	public void setAppNumber(String appNumber) {
		this.appNumber = appNumber;
	}

	/**
	 * @return the visitDate
	 */
	public String getVisitDate() {
		return visitDate;
	}

	/**
	 * @param visitDate the visitDate to set
	 */
	public void setVisitDate(String visitDate) {
		this.visitDate = visitDate;
	}

	/**
	 * @return the visitTime
	 */
	public String getVisitTime() {
		return visitTime;
	}

	/**
	 * @param visitTime the visitTime to set
	 */
	public void setVisitTime(String visitTime) {
		this.visitTime = visitTime;
	}

	/**
	 * @return the slotBkViewList
	 */
	public ArrayList getSlotBkViewList() {
		return slotBkViewList;
	}

	/**
	 * @param slotBkViewList the slotBkViewList to set
	 */
	public void setSlotBkViewList(ArrayList slotBkViewList) {
		this.slotBkViewList = slotBkViewList;
	}

	//added by ankita 11.10.12
	public String getActiontaken() {
		return actiontaken;
	}
	/**
	 * @param actiontaken
	 *///added by ankita 11.10.12
	public void setActiontaken(String actiontaken) {
		this.actiontaken = actiontaken;
	}

	public void setSlotFlag(String slotFlag) {
		this.slotFlag = slotFlag;
	}

	public String getSlotFlag() {
		return slotFlag;
	}

	private String bookedCount;

	public String getBookedCount() {
		return bookedCount;
	}

	public void setBookedCount(String bookedCount) {
		this.bookedCount = bookedCount;
	}
	
}
