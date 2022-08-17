/**
 * Copyright (c) 2009-10 WIPRO INFOTECH. All Rights Reserved.
 *
 *  This software is the confidential and proprietary information of WIPRO
 *  ("Confidential Information"). You shall not
 *  disclose such Confidential Information and shall use it only in
 *  accordance with the terms of the license agreement you entered into
 *  with WIPRO.
 *
 * WIPRO MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE
 * SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, OR NON-INFRINGEMENT. WIPRO SHALL NOT BE
 * LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF
 * USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES.
 *
 * Customer specific copyright notice - NA
 */
package com.wipro.igrs.empmgmt.dto;


import java.io.Serializable;
import java.util.ArrayList;

import org.apache.struts.upload.FormFile;



/**
* 
* OfficalInfoDTO.java <br>
* OfficalInfoDTO <br>
* 
* @version 1.0
* 
* @author <b>WIPRO JAVA TEAM</b> <br>
*         Created on 12-Apr-2008 <br>
*         Last Modified on 28-Mar-2013
*/
@SuppressWarnings({ "rawtypes" })
public class OfficalInfoDTO implements Serializable {

	private static final long serialVersionUID = -7608284383511983731L;

	private String officiating;
	
	private String officiatingText;

	private transient FormFile appointmentLetter;

	private transient FormFile joiningLetter;

	private transient FormFile reasonForSaperation;

	private transient FormFile employeeUndertaking;

	private transient FormFile stmtOfFixation;

	private transient FormFile firstFinger;

	private transient FormFile secondFinger;

	private transient FormFile thirdFinger;

	private transient FormFile fourthFinger;

	private transient FormFile thumb;

	private transient FormFile digitalSign;

	private String substantive;

	private String class1;
	
	private String class1Text;

	private String designation;

	private String designationText;
	
	private String desiOffic;
	
	private String desiOffictText;

	private String designationid;

	private String officalInfoForm;

	private String supervisorDesignation;

	private String personalDetailsForm;

	private String dateOfSaperation;

	private String compEmplRefrence;

	private String employeeStatus;

	private String supervisorName;

	private String supervisorID;

	private String supervisor;

	private String gradeid;

	private String dateOfJoining;

	private String supervisor1;

	private String cadreid;

	private String documentid;

	private String documenttype;

	private String officiatingid;

	private ArrayList _serviceList;
	
	private ArrayList repoteelist;
	
	private ArrayList<DropDownDTO> offSubList;
	
	private ArrayList<DropDownDTO> gradeMasterList;
	
	private ArrayList<DropDownDTO> cadreMapList;
	
	private ArrayList<DropDownDTO> empStatusMasterList;
	
	private ArrayList<DropDownDTO> cadreMasterList;
	
	private String empid;
	
	private String firstName;
	private String middleName;
	private String lastName;
	private String documentName;
	private transient byte[] documentContents;
	private String updateFlag;
	
	
	
	public String getUpdateFlag() {
		return updateFlag;
	}

	public void setUpdateFlag(String updateFlag) {
		this.updateFlag = updateFlag;
	}

	private String dateFirstGovtService;
	/**
	 * @return the documentName
	 */
	public String getDocumentName() {
		return documentName;
	}

	/**
	 * @param documentName the documentName to set
	 */
	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	/**
	 * @return the officiating
	 */
	public String getOfficiating() {
		return officiating;
	}

	/**
	 * @param officiating the officiating to set
	 */
	public void setOfficiating(String officiating) {
		this.officiating = officiating;
	}

	/**
	 * @return the appointmentLetter
	 */
	public FormFile getAppointmentLetter() {
		return appointmentLetter;
	}

	/**
	 * @param appointmentLetter the appointmentLetter to set
	 */
	public void setAppointmentLetter(FormFile appointmentLetter) {
		this.appointmentLetter = appointmentLetter;
	}

	/**
	 * @return the joiningLetter
	 */
	public FormFile getJoiningLetter() {
		return joiningLetter;
	}

	/**
	 * @param joiningLetter the joiningLetter to set
	 */
	public void setJoiningLetter(FormFile joiningLetter) {
		this.joiningLetter = joiningLetter;
	}

	/**
	 * @return the reasonForSaperation
	 */
	public FormFile getReasonForSaperation() {
		return reasonForSaperation;
	}

	/**
	 * @param reasonForSaperation the reasonForSaperation to set
	 */
	public void setReasonForSaperation(FormFile reasonForSaperation) {
		this.reasonForSaperation = reasonForSaperation;
	}

	/**
	 * @return the employeeUndertaking
	 */
	public FormFile getEmployeeUndertaking() {
		return employeeUndertaking;
	}

	/**
	 * @param employeeUndertaking the employeeUndertaking to set
	 */
	public void setEmployeeUndertaking(FormFile employeeUndertaking) {
		this.employeeUndertaking = employeeUndertaking;
	}

	/**
	 * @return the stmtOfFixation
	 */
	public FormFile getStmtOfFixation() {
		return stmtOfFixation;
	}

	/**
	 * @param stmtOfFixation the stmtOfFixation to set
	 */
	public void setStmtOfFixation(FormFile stmtOfFixation) {
		this.stmtOfFixation = stmtOfFixation;
	}

	/**
	 * @return the firstFinger
	 */
	public FormFile getFirstFinger() {
		return firstFinger;
	}

	/**
	 * @param firstFinger the firstFinger to set
	 */
	public void setFirstFinger(FormFile firstFinger) {
		this.firstFinger = firstFinger;
	}

	/**
	 * @return the secondFinger
	 */
	public FormFile getSecondFinger() {
		return secondFinger;
	}

	/**
	 * @param secondFinger the secondFinger to set
	 */
	public void setSecondFinger(FormFile secondFinger) {
		this.secondFinger = secondFinger;
	}

	/**
	 * @return the thirdFinger
	 */
	public FormFile getThirdFinger() {
		return thirdFinger;
	}

	/**
	 * @param thirdFinger the thirdFinger to set
	 */
	public void setThirdFinger(FormFile thirdFinger) {
		this.thirdFinger = thirdFinger;
	}

	/**
	 * @return the fourthFinger
	 */
	public FormFile getFourthFinger() {
		return fourthFinger;
	}

	/**
	 * @param fourthFinger the fourthFinger to set
	 */
	public void setFourthFinger(FormFile fourthFinger) {
		this.fourthFinger = fourthFinger;
	}

	/**
	 * @return the thumb
	 */
	public FormFile getThumb() {
		return thumb;
	}

	/**
	 * @param thumb the thumb to set
	 */
	public void setThumb(FormFile thumb) {
		this.thumb = thumb;
	}

	/**
	 * @return the digitalSign
	 */
	public FormFile getDigitalSign() {
		return digitalSign;
	}

	/**
	 * @param digitalSign the digitalSign to set
	 */
	public void setDigitalSign(FormFile digitalSign) {
		this.digitalSign = digitalSign;
	}

	/**
	 * @return the substantive
	 */
	public String getSubstantive() {
		return substantive;
	}

	/**
	 * @param substantive the substantive to set
	 */
	public void setSubstantive(String substantive) {
		this.substantive = substantive;
	}

	/**
	 * @return the class1
	 */
	public String getClass1() {
		return class1;
	}

	/**
	 * @param class1 the class1 to set
	 */
	public void setClass1(String class1) {
		this.class1 = class1;
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
	 * @return the desiOffic
	 */
	public String getDesiOffic() {
		return desiOffic;
	}

	/**
	 * @param desiOffic the desiOffic to set
	 */
	public void setDesiOffic(String desiOffic) {
		this.desiOffic = desiOffic;
	}

	/**
	 * @return the designationid
	 */
	public String getDesignationid() {
		return designationid;
	}

	/**
	 * @param designationid the designationid to set
	 */
	public void setDesignationid(String designationid) {
		this.designationid = designationid;
	}

	/**
	 * @return the officalInfoForm
	 */
	public String getOfficalInfoForm() {
		return officalInfoForm;
	}

	/**
	 * @param officalInfoForm the officalInfoForm to set
	 */
	public void setOfficalInfoForm(String officalInfoForm) {
		this.officalInfoForm = officalInfoForm;
	}

	/**
	 * @return the supervisorDesignation
	 */
	public String getSupervisorDesignation() {
		return supervisorDesignation;
	}

	/**
	 * @param supervisorDesignation the supervisorDesignation to set
	 */
	public void setSupervisorDesignation(String supervisorDesignation) {
		this.supervisorDesignation = supervisorDesignation;
	}

	/**
	 * @return the personalDetailsForm
	 */
	public String getPersonalDetailsForm() {
		return personalDetailsForm;
	}

	/**
	 * @param personalDetailsForm the personalDetailsForm to set
	 */
	public void setPersonalDetailsForm(String personalDetailsForm) {
		this.personalDetailsForm = personalDetailsForm;
	}

	/**
	 * @return the dateOfSaperation
	 */
	public String getDateOfSaperation() {
		return dateOfSaperation;
	}

	/**
	 * @param dateOfSaperation the dateOfSaperation to set
	 */
	public void setDateOfSaperation(String dateOfSaperation) {
		this.dateOfSaperation = dateOfSaperation;
	}

	/**
	 * @return the compEmplRefrence
	 */
	public String getCompEmplRefrence() {
		return compEmplRefrence;
	}

	/**
	 * @param compEmplRefrence the compEmplRefrence to set
	 */
	public void setCompEmplRefrence(String compEmplRefrence) {
		this.compEmplRefrence = compEmplRefrence;
	}

	/**
	 * @return the employeeStatus
	 */
	public String getEmployeeStatus() {
		return employeeStatus;
	}

	/**
	 * @param employeeStatus the employeeStatus to set
	 */
	public void setEmployeeStatus(String employeeStatus) {
		this.employeeStatus = employeeStatus;
	}

	/**
	 * @return the supervisorName
	 */
	public String getSupervisorName() {
		return supervisorName;
	}

	/**
	 * @param supervisorName the supervisorName to set
	 */
	public void setSupervisorName(String supervisorName) {
		this.supervisorName = supervisorName;
	}

	/**
	 * @return the supervisorID
	 */
	public String getSupervisorID() {
		return supervisorID;
	}

	/**
	 * @param supervisorID the supervisorID to set
	 */
	public void setSupervisorID(String supervisorID) {
		this.supervisorID = supervisorID;
	}

	/**
	 * @return the supervisor
	 */
	public String getSupervisor() {
		return supervisor;
	}

	/**
	 * @param supervisor the supervisor to set
	 */
	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}

	/**
	 * @return the gradeid
	 */
	public String getGradeid() {
		return gradeid;
	}

	/**
	 * @param gradeid the gradeid to set
	 */
	public void setGradeid(String gradeid) {
		this.gradeid = gradeid;
	}

	/**
	 * @return the dateOfJoining
	 */
	public String getDateOfJoining() {
		return dateOfJoining;
	}

	/**
	 * @param dateOfJoining the dateOfJoining to set
	 */
	public void setDateOfJoining(String dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	/**
	 * @return the supervisor1
	 */
	public String getSupervisor1() {
		return supervisor1;
	}

	/**
	 * @param supervisor1 the supervisor1 to set
	 */
	public void setSupervisor1(String supervisor1) {
		this.supervisor1 = supervisor1;
	}

	/**
	 * @return the cadreid
	 */
	public String getCadreid() {
		return cadreid;
	}

	/**
	 * @param cadreid the cadreid to set
	 */
	public void setCadreid(String cadreid) {
		this.cadreid = cadreid;
	}

	/**
	 * @return the documentid
	 */
	public String getDocumentid() {
		return documentid;
	}

	/**
	 * @param documentid the documentid to set
	 */
	public void setDocumentid(String documentid) {
		this.documentid = documentid;
	}

	/**
	 * @return the documenttype
	 */
	public String getDocumenttype() {
		return documenttype;
	}

	/**
	 * @param documenttype the documenttype to set
	 */
	public void setDocumenttype(String documenttype) {
		this.documenttype = documenttype;
	}

	/**
	 * @return the officiatingid
	 */
	public String getOfficiatingid() {
		return officiatingid;
	}

	/**
	 * @param officiatingid the officiatingid to set
	 */
	public void setOfficiatingid(String officiatingid) {
		this.officiatingid = officiatingid;
	}

	/**
	 * @return the _serviceList
	 */
	public ArrayList get_serviceList() {
		return _serviceList;
	}

	/**
	 * @param list the _serviceList to set
	 */
	public void set_serviceList(ArrayList list) {
		_serviceList = list;
	}

	/**
	 * @return the empid
	 */
	public String getEmpid() {
		return empid;
	}

	/**
	 * @param empid the empid to set
	 */
	public void setEmpid(String empid) {
		this.empid = empid;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * @param middleName the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the repoteelist
	 */
	public ArrayList getRepoteelist() {
		return repoteelist;
	}

	/**
	 * @param repoteelist the repoteelist to set
	 */
	public void setRepoteelist(ArrayList repoteelist) {
		this.repoteelist = repoteelist;
	}

	/**
	 * @return the officiatingText
	 */
	public String getOfficiatingText() {
		return officiatingText;
	}

	/**
	 * @param officiatingText the officiatingText to set
	 */
	public void setOfficiatingText(String officiatingText) {
		this.officiatingText = officiatingText;
	}

	/**
	 * @return the class1Text
	 */
	public String getClass1Text() {
		return class1Text;
	}

	/**
	 * @param class1Text the class1Text to set
	 */
	public void setClass1Text(String class1Text) {
		this.class1Text = class1Text;
	}

	/**
	 * @return the designationText
	 */
	public String getDesignationText() {
		return designationText;
	}

	/**
	 * @param designationText the designationText to set
	 */
	public void setDesignationText(String designationText) {
		this.designationText = designationText;
	}

	/**
	 * @return the desiOffictText
	 */
	public String getDesiOffictText() {
		return desiOffictText;
	}

	/**
	 * @param desiOffictText the desiOffictText to set
	 */
	public void setDesiOffictText(String desiOffictText) {
		this.desiOffictText = desiOffictText;
	}

	/**
	 * @return the documentContents
	 */
	public byte[] getDocumentContents() {
		return documentContents;
	}

	/**
	 * @param documentContents the documentContents to set
	 */
	public void setDocumentContents(byte[] documentContents) {
		this.documentContents = documentContents;
	}

	/**
	 * @return the gradeMasterList
	 */
	public ArrayList<DropDownDTO> getGradeMasterList() {
		return gradeMasterList;
	}

	/**
	 * @param gradeMasterList the gradeMasterList to set
	 */
	public void setGradeMasterList(ArrayList<DropDownDTO> gradeMasterList) {
		this.gradeMasterList = gradeMasterList;
	}

	/**
	 * @return the offSubList
	 */
	public ArrayList<DropDownDTO> getOffSubList() {
		return offSubList;
	}

	/**
	 * @param offSubList the offSubList to set
	 */
	public void setOffSubList(ArrayList<DropDownDTO> offSubList) {
		this.offSubList = offSubList;
	}

	/**
	 * @return the cadreMapList
	 */
	public ArrayList<DropDownDTO> getCadreMapList() {
		return cadreMapList;
	}

	/**
	 * @param cadreMapList the cadreMapList to set
	 */
	public void setCadreMapList(ArrayList<DropDownDTO> cadreMapList) {
		this.cadreMapList = cadreMapList;
	}

	/**
	 * @return the empStatusMasterList
	 */
	public ArrayList<DropDownDTO> getEmpStatusMasterList() {
		return empStatusMasterList;
	}

	/**
	 * @param empStatusMasterList the empStatusMasterList to set
	 */
	public void setEmpStatusMasterList(ArrayList<DropDownDTO> empStatusMasterList) {
		this.empStatusMasterList = empStatusMasterList;
	}

	/**
	 * @return the cadreMasterList
	 */
	public ArrayList<DropDownDTO> getCadreMasterList() {
		return cadreMasterList;
	}

	/**
	 * @param cadreMasterList the cadreMasterList to set
	 */
	public void setCadreMasterList(ArrayList<DropDownDTO> cadreMasterList) {
		this.cadreMasterList = cadreMasterList;
	}

	/**
	 * @return the dateFirstGovtService
	 */
	public String getDateFirstGovtService() {
		return dateFirstGovtService;
	}

	/**
	 * @param dateFirstGovtService the dateFirstGovtService to set
	 */
	public void setDateFirstGovtService(String dateFirstGovtService) {
		this.dateFirstGovtService = dateFirstGovtService;
	}



}
