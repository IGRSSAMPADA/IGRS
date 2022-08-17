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

import com.wipro.igrs.auditinspection.dto.DROEmpMapDTO;
import com.wipro.igrs.auditinspection.dto.DROPendingTaskDTO;
import com.wipro.igrs.auditinspection.dto.DROReasonMapDTO;
import com.wipro.igrs.auditinspection.dto.DROReportDetailsDTO;
import com.wipro.igrs.baseaction.form.BaseForm;

/**
 * MyEclipse Struts Creation date: 06-13-2008
 * 
 * XDoclet definition:
 * 
 * @struts.form name="droInspectionForm"
 */
public class DROInspectionForm extends BaseForm {
	/*
	 * Generated Methods
	 */

	DROReportDetailsDTO droReport = new DROReportDetailsDTO();
	private String droId;
	private String userid;
	private String empName;
	private String designation;
	private String joinDate;
	private String seperationDate;
	private ArrayList employeeList = new ArrayList();
	private ArrayList pendinglist = new ArrayList();
	private ArrayList reasonlist = new ArrayList();
	private String modifyFlag;
	
	
	public String getModifyFlag() {
		return modifyFlag;
	}

	public void setModifyFlag(String modifyFlag) {
		this.modifyFlag = modifyFlag;
	}

	//added by shruti
	private String language;
	
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * Method validate
	 * 
	 * @param mapping
	 * @param request
	 * @return ActionErrors
	 */
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Method reset
	 * 
	 * @param mapping
	 * @param request
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// TODO Auto-generated method stub
	}

	public DROReportDetailsDTO getDroReport() {
		return droReport;
	}

	public void setDroReport(DROReportDetailsDTO droReport) {
		this.droReport = droReport;
	}

	public String getDroId() {
		return droId;
	}

	public void setDroId(String droId) {
		this.droId = droId;
	}

	/**
	 * @return the empName
	 */
	public String getEmpName() {
		return empName;
	}

	/**
	 * @param empName
	 *            the empName to set
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
	 * @param designation
	 *            the designation to set
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
	 * @param joinDate
	 *            the joinDate to set
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
	 * @param seperationDate
	 *            the seperationDate to set
	 */
	public void setSeperationDate(String seperationDate) {
		this.seperationDate = seperationDate;
	}

	/**
	 * @return the userid
	 */
	public String getUserid() {
		return userid;
	}

	/**
	 * @param userid
	 *            the userid to set
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}

	public void setEmployeeDTO(int index, DROEmpMapDTO value) {

		for (; index >= employeeList.size(); employeeList
				.add(new DROEmpMapDTO()))
			;
		employeeList.add(index, value);
	}

	public DROEmpMapDTO getEmployeeDTO(int index) {
		for (; index >= employeeList.size(); employeeList
				.add(new DROEmpMapDTO()))
			;
		return (DROEmpMapDTO) employeeList.get(index);
	}

	/**
	 * @return the employeeList
	 */
	public ArrayList getEmployeeList() {
		return employeeList;
	}

	/**
	 * @param employeeList
	 *            the employeeList to set
	 */
	public void setEmployeeList(ArrayList employeeList) {
		this.employeeList = employeeList;
	}

	public DROPendingTaskDTO getDropendingDTO(int index) {
		for (; index >= pendinglist.size(); pendinglist
				.add(new DROPendingTaskDTO()))
			;
		return (DROPendingTaskDTO) pendinglist.get(index);
	}

	/**
	 * @param sropendingDTO
	 *            the sropendingDTO to set
	 */
	public void setDropendingDTO(int index, DROPendingTaskDTO dropendingDTO) {
		for (; index >= pendinglist.size(); pendinglist
				.add(new DROPendingTaskDTO()))
			;
		pendinglist.add(index, dropendingDTO);
	}

	/**
	 * @return the pendinglist
	 */
	public ArrayList getPendinglist() {
		return pendinglist;
	}

	/**
	 * @param pendinglist
	 *            the pendinglist to set
	 */
	public void setPendinglist(ArrayList pendinglist) {
		this.pendinglist = pendinglist;
	}

	/**
	 * @return the reasonlist
	 */
	public ArrayList getReasonlist() {
		return reasonlist;
	}

	/**
	 * @param reasonlist
	 *            the reasonlist to set
	 */
	public void setReasonlist(ArrayList reasonlist) {
		this.reasonlist = reasonlist;
	}

	public void setReasonDTO(int index, DROReasonMapDTO value) {

		for (; index >= reasonlist.size(); reasonlist
				.add(new DROReasonMapDTO()))
			;
		reasonlist.add(index, value);
	}

	public DROReasonMapDTO getReasonDTO(int index) {
		for (; index >= reasonlist.size(); reasonlist
				.add(new DROReasonMapDTO()))
			;
		return (DROReasonMapDTO) reasonlist.get(index);
	}

}