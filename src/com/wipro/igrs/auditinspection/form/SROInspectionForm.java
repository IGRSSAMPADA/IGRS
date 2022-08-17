/*
* Copyright Notice
*==============================================================================
* This file contains proprietary information of IGRS Bhopal Madhya Pradesh .
* Copying or reproduction without prior written approval is prohibited.
* Copyright (c) 2007-08
*==============================================================================
*
* File Name   :   
 * Author      :   
 * Description :   
 * ----------------------------------------------------------------------------
* Version         Modified By     Modified On           Modification
* ----------------------------------------------------------------------------
* 1.0             SHRUTI          xxxxx              
 * --------------------------------------------------------------------------------
*/

package com.wipro.igrs.auditinspection.form;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.auditinspection.dto.SROEmpDTO;
import com.wipro.igrs.auditinspection.dto.SROPendingItemsDTO;
import com.wipro.igrs.auditinspection.dto.SROReportDetailsDTO;
import com.wipro.igrs.baseaction.form.BaseForm;


/** 
 * MyEclipse Struts
 * Creation date: 05-21-2008
 * 
 * XDoclet definition:
 * @struts.form name="sROInspectionForm"
 */
public class SROInspectionForm extends BaseForm {
	/*
	 * Generated Methods
	 */

	/** 
	 * Method validate
	 * @param mapping
	 * @param request
	 * @return ActionErrors
	 */
	
	
	SROReportDetailsDTO sroReport       =  new SROReportDetailsDTO();
	private ArrayList employeeList = new ArrayList();
	
	//private SROEmpDTO employeeDTO = new SROEmpDTO();
	private SROPendingItemsDTO sropendingDTO=new SROPendingItemsDTO();
	private String formName = null;
	private String useridAction;
	
	private String pendingtype;
	private String pendingfrom;
	private String pendingno;
	private String srcomments;
	private String remarks;
	//private String userid;
	private String empName;
	private String designation;
	private String joinDate;
	private String seperationDate;
	private ArrayList pendinglist=new ArrayList();
	
	//added by shruti
	private String language;
	private String modifyFlag;
	private String empId;
	private int counter;
	
	

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getModifyFlag() {
		return modifyFlag;
	}

	public void setModifyFlag(String modifyFlag) {
		this.modifyFlag = modifyFlag;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * @return the pendinglist
	 */
	public ArrayList getPendinglist() {
		return pendinglist;
	}

	/**
	 * @param pendinglist the pendinglist to set
	 */
	public void setPendinglist(ArrayList pendinglist) {
		this.pendinglist = pendinglist;
	}

	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		
		return null;
	}

	/** 
	 * Method reset
	 * @param mapping
	 * @param request
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// TODO Auto-generated method stub
	}

	public SROReportDetailsDTO getSroReport() {
		return sroReport;
	}

	public void setSroReport(SROReportDetailsDTO sroReport) {
		this.sroReport = sroReport;
	}

	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	public String getUseridAction() {
		return useridAction;
	}

	public void setUseridAction(String useridAction) {
		this.useridAction = useridAction;
	}

	public String getPendingtype() {
    	return pendingtype;
    }

	public void setPendingtype(String pendingtype) {
    	this.pendingtype = pendingtype;
    }

	public String getPendingfrom() {
    	return pendingfrom;
    }

	public void setPendingfrom(String pendingfrom) {
    	this.pendingfrom = pendingfrom;
    }

	public String getPendingno() {
    	return pendingno;
    }

	public void setPendingno(String pendingno) {
    	this.pendingno = pendingno;
    }

	public String getSrcomments() {
    	return srcomments;
    }

	public void setSrcomments(String srcomments) {
    	this.srcomments = srcomments;
    }

	public String getRemarks() {
    	return remarks;
    }

	public void setRemarks(String remarks) {
    	this.remarks = remarks;
    }

	
	/**
     * @return the empName
     */
    public String getEmpName() {
    	return empName;
    }

	/**
     * @param empName the empName to set
     */
    public void setEmpName(String empName) {
    	this.empName = empName;
    }

	/**
     * @return the designation
     */
    public String getDesignation() {
    	return designation;
    }

	/**
     * @param designation the designation to set
     */
    public void setDesignation(String designation) {
    	this.designation = designation;
    }

	/**
     * @return the joinDate
     */
    public String getJoinDate() {
    	return joinDate;
    }

	/**
     * @param joinDate the joinDate to set
     */
    public void setJoinDate(String joinDate) {
    	this.joinDate = joinDate;
    }

	/**
     * @return the seperationDate
     */
    public String getSeperationDate() {
    	return seperationDate;
    }

	/**
     * @param seperationDate the seperationDate to set
     */
    public void setSeperationDate(String seperationDate) {
    	this.seperationDate = seperationDate;
    }

	/**
	 * @return the sropendingDTO
	 */
	public SROPendingItemsDTO getSropendingDTO(int index) {
		for(; index>=pendinglist.size();pendinglist.add(new SROPendingItemsDTO()));
		return (SROPendingItemsDTO) pendinglist.get(index);
	}

	/**
	 * @param sropendingDTO the sropendingDTO to set
	 */
	public void setSropendingDTO(int index,SROPendingItemsDTO sropendingDTO) {
		for(; index>=pendinglist.size();pendinglist.add(new SROPendingItemsDTO()));
		pendinglist.add(index,sropendingDTO);
	}


	public void setEmployeeDTO(int index, SROEmpDTO value) 
	{
		
		for(; index >= employeeList.size(); 
		employeeList.add(new SROEmpDTO()));
		employeeList.add(index,value);
	}
	
	public SROEmpDTO getEmployeeDTO(int index) 
	{
		for(; index >= employeeList.size(); 
		employeeList.add(new SROEmpDTO()));
		return (SROEmpDTO)employeeList.get(index);
	}
	public ArrayList getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(ArrayList employeeList) {
		this.employeeList = employeeList;
	}
}