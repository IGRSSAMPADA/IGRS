/* 
 * <Copyright information>
 *
 * <Customer specific copyright notice (if any) >
 *
 * ==============================================================================
 * This file contains proprietary information of Wipro Infotech Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007
 * ==============================================================================
 * 
 * File Name	   		: RevenueMgmtDAO.java
 *
 * Description	   		: Struts Action Form for RevenueMgmt
 *
 * Version Number  		: 1.0 
 *
 * Created Date	   		: 28 11 2007  
 *
 * Modification History	: Created
 */


package com.wipro.igrs.revenuemgmt.form;


import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import com.wipro.igrs.revenuemgmt.dto.RevenueMgmtDTO;


/**
 * This class applies the basic coding guidelines mentioned in this document.
 * A quick view to this class will let one know how the code should look like.
 *
 * @author  Wipro Infotech, Sreelatha M
 * @version 1.0, 24/12/2007
 * @since   1.0
 */

public class RevenueMgmtForm extends ActionForm {
	private RevenueMgmtDTO revenueMgmtDTO = new RevenueMgmtDTO() ;
	
	 private ArrayList reportList = new ArrayList();
	/** the value of reportList association */
    private ArrayList reportCreditList = new ArrayList();
    
    private ArrayList reportDebitList = new ArrayList();
    
    /** the value of userTypeList association */
    private ArrayList userTypeList = new ArrayList();
    
    /** the value of spList association*/
    private ArrayList spList = new ArrayList(); 
    
    /** the value of districtList association */
    private ArrayList districtList = new ArrayList(); 
    
    /** the value of officeTypeLst association */
    private ArrayList officeTypeLst = new ArrayList();
    
    /** the value of officeNameList association */
    private ArrayList officeNameList = new ArrayList();
    
    /** the value of serviceFeeList association */
    private ArrayList serviceFeeList = new ArrayList();
    
    /** the value of actionID association */
    private String actionID;
    
    /** the value of page association */
    private String page;
    
    /** the value of fromDate association */
    private String fromDate;
    
    /** the value of toDate association */
    private String toDate;
    
    /** the value of userDate association */
    private String userDate;
    
    /** the value of month association */
    private String month;
    
    /** the value of userType association */
    private String userType;
    
    /** the value of radiodate association INITIALISED to date value */
    private String radiodate = "date";
    
    /** the value of serviceProviderName association */
    private String serviceProviderName = "Select";
    
    /**Reset all properties to their default values.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     */
    public void reset(ActionMapping mapping, HttpServletRequest request) {
    	this.serviceProviderName="Select";
    	this.getRevenueMgmtDTO().setDistrictId("Select");
    	this.getRevenueMgmtDTO().setOfficeTypeId("Select");
    	System.out.println("in reset method:"+getServiceProviderName());
    	System.out.println("this.getRevenueMgmtDTO().getDistrictId()======="
    			+this.getRevenueMgmtDTO().getDistrictId());
    	System.out.println("this.getRevenueMgmtDTO().getOfficeTypeId()======="
    			+this.getRevenueMgmtDTO().getOfficeTypeId());
        super.reset( mapping, request);
    }

    /**Validate all properties to their default values.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return ActionErrors A list of all errors found.
     public ActionErrors validate(ActionMapping mapping, 
                                 HttpServletRequest request) {
    	ActionErrors errors = new ActionErrors();
    	System.out.println("getServiceProviderName() value:-"+getServiceProviderName());
        boolean spName = getServiceProviderName().equalsIgnoreCase("Select");
        System.out.println("boolean spName value:-"+spName);        
        if(spName){
        	 System.out.println("in Advance Payments Form Validation");
             errors.add("serviceProviderName",new ActionError ("error.spName.required"));
        }
        //return super.validate( mapping, request);
        return errors;
        
    }*/
    
    /**
	* Return the revenueMgmtDTO instance that identifies this object.
	* @return RevenueMgmtDTO
	*/    
	public RevenueMgmtDTO getRevenueMgmtDTO() {
		return revenueMgmtDTO;
	}
	
	/**
	* Set the revenueMgmtDTO instance that identifies this object.
	* @param revenueMgmtDTO
	*/
	public void setRevenueMgmtDTO(RevenueMgmtDTO revenueMgmtDTO) {
		this.revenueMgmtDTO = revenueMgmtDTO;
	}
	
	/**
	* Return the value of the fromDate column.
	* @return java.lang.String
	*/
	public String getFromDate() {
		return fromDate;
	}
	
	/**
	* Set the value of the fromDate column.
	* @param fromDate
	*/
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	
	/**
	* Return the value of the toDate column.
	* @return java.lang.String
	*/
	public String getToDate() {
		return toDate;
	}
	
	/**
	* Set the value of the toDate column.
	* @param toDate
	*/
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	
	/**
	* @return java.util.ArrayList
	*/
	public ArrayList getReportList() {
		return reportList;
	}
	
	/**
	* @param reportList
	*/
	public void setReportList(ArrayList reportList) {
		this.reportList = reportList;
	}
	
	/**
	* @return java.lang.String
	*/
	public String getActionID() {
		return actionID;
	}
	
	/**
	* @param actionID
	*/
	public void setActionID(String actionID) {
		this.actionID = actionID;
	}
	
	/**
	* @return java.lang.String
	*/
	public String getPage() {
		return page;
	}
	
	/**
	* @param page
	*/
	public void setPage(String page) {
		this.page = page;
	}
	
	/**
	* @return java.util.Date
	*/
	public String getUserDate() {
		return userDate;
	}
	
	/**
	 * @param userDate
	*/
	public void setUserDate(String userDate) {
		this.userDate = userDate;
	}
	
	/**
	* @return java.lang.String
	*/
	public String getMonth() {
		return month;
	}
	
	/**
	* @param month
	*/
	public void setMonth(String month) {
		this.month = month;
	}
	
	/**
	* @return java.lang.String
	*/
	public String getUserType() {
		return userType;
	}
	
	/**
	* @param userType
	*/
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	/**
	* @return java.util.ArrayList
	*/
	public ArrayList getUserTypeList() {
		return userTypeList;
	}
	
	/**
	* @param userTypeList
	*/
	public void setUserTypeList(ArrayList userTypeList) {
		this.userTypeList = userTypeList;
	}
	
	/**
	* @return java.util.ArrayList
	*/
	public ArrayList getSpList() {
		return spList;
	}
	
	/**
	* @param spList
	*/
	public void setSpList(ArrayList spList) {
		this.spList = spList;
	}
	
	/**
	* @return java.lang.String
	*/
	public String getServiceProviderName() {
		return serviceProviderName;
	}
	
	/**
	* @param serviceProviderName
	*/
	public void setServiceProviderName(String serviceProviderName) {
		this.serviceProviderName = serviceProviderName;
	}

	/**
	* @return java.util.ArrayList
	*/
	public ArrayList getDistrictList() {
		return districtList;
	}

	/**
	* @param districtList
	*/
	public void setDistrictList(ArrayList districtList) {
		this.districtList = districtList;
	}

	/**
	* @return java.util.ArrayList
	*/
	public ArrayList getOfficeTypeLst() {
		return officeTypeLst;
	}
	
	/**
	* @param officeTypeLst
	*/
	public void setOfficeTypeLst(ArrayList officeTypeLst) {
		this.officeTypeLst = officeTypeLst;
	}

	/**
	* @return java.util.ArrayList
	*/
	public ArrayList getOfficeNameList() {
		return officeNameList;
	}

	/**
	* @param officeNameList
	*/
	public void setOfficeNameList(ArrayList officeNameList) {
		this.officeNameList = officeNameList;
	}

	/**
	* @return java.lang.String
	*/
	public String getRadiodate() {
		return radiodate;
	}

	/**
	* @param radiodate
	*/
	public void setRadiodate(String radiodate) {
		this.radiodate = radiodate;
	}

	/**
	* @return java.util.ArrayList
	*/
	public ArrayList getServiceFeeList() {
		return serviceFeeList;
	}

	/**
	* @param serviceFeeList
	*/
	public void setServiceFeeList(ArrayList serviceFeeList) {
		this.serviceFeeList = serviceFeeList;
	}

	/**
	 * @return the reportCreditList
	 */
	public ArrayList getReportCreditList() {
		return reportCreditList;
	}

	/**
	 * @param reportCreditList the reportCreditList to set
	 */
	public void setReportCreditList(ArrayList reportCreditList) {
		this.reportCreditList = reportCreditList;
	}

	/**
	 * @return the reportDebitList
	 */
	public ArrayList getReportDebitList() {
		return reportDebitList;
	}

	/**
	 * @param reportDebitList the reportDebitList to set
	 */
	public void setReportDebitList(ArrayList reportDebitList) {
		this.reportDebitList = reportDebitList;
	}
}
