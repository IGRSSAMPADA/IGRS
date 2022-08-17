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
package com.wipro.igrs.adminConfig.form;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

import com.wipro.igrs.adminConfig.dto.AdminAttrDTO;
import com.wipro.igrs.adminConfig.dto.AttrStatusDTO;
import com.wipro.igrs.empmgmt.dto.DropDownDTO;

/**
* 
* AdminAttrForm.java <br>
* AdminAttrForm <br>
* 
* @version 1.0
* 
* @author <b>WIPRO JAVA TEAM</b> <br>
*         Created on 07-Apr-2008 <br>
*         Last Modified on 28-Mar-2013
*/
public class AdminAttrForm extends ActionForm {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1692308422588808161L;
	private String formName;
	private String actionValue;

	// variables related to adminmoduleSelection
	private String subModuleID;

	// variables related to display page
	private String attributeName;
	private String attributeValue;
	private String remarks;
	private String attributeId;

	private String delAttribID;
	// intializing obj to dto
	private AdminAttrDTO admindto = new AdminAttrDTO();
	private String statusVal;
	private String statusLabel;
	private ArrayList<AttrStatusDTO> statusList;
	
	private ArrayList<DropDownDTO> moduleList;
	
	private ArrayList<DropDownDTO> functionList;
	
	private String moduleID;
	private String moduleName;
	
	private String functionID;
	private String functionName;
	
	private String updateDate;
	
	/**
	 * 
	 */
	public AdminAttrForm() {
		if(statusList == null) {
			statusList = new ArrayList<AttrStatusDTO>();
		}
		if(statusList.isEmpty()) {
			AttrStatusDTO tmp = null;
			tmp = new AttrStatusDTO();
			tmp.setValue("A");
			tmp.setLabel("Activated");
			statusList.add(tmp);
			tmp = new AttrStatusDTO();
			tmp.setValue("D");
			tmp.setLabel("Deactivated");
			statusList.add(tmp);
			
		}
	}
	

	/**
	 * @return the delAttribID
	 */
	public String getDelAttribID() {
		return delAttribID;
	}

	/**
	 * @param delAttribID the delAttribID to set
	 */
	public void setDelAttribID(String delAttribID) {
		this.delAttribID = delAttribID;
	}
	/**
	 * @return the statusVal
	 */
	public String getStatusVal() {
		return statusVal;
	}
	/**
	 * @param statusVal the statusVal to set
	 */
	public void setStatusVal(String statusVal) {
		this.statusVal = statusVal;
	}
	/**
	 * @return the statusLabel
	 */
	public String getStatusLabel() {
		return statusLabel;
	}
	/**
	 * @param statusLabel the statusLabel to set
	 */
	public void setStatusLabel(String statusLabel) {
		this.statusLabel = statusLabel;
	}
	/**
	 * @return the statusList
	 */
	public ArrayList<AttrStatusDTO> getStatusList() {
		return statusList;
	}
	/**
	 * @param statusList the statusList to set
	 */
	public void setStatusList(ArrayList<AttrStatusDTO> statusList) {
		this.statusList = statusList;
	}
	/**
	 * @return the moduleList
	 */
	public ArrayList<DropDownDTO> getModuleList() {
		return moduleList;
	}
	/**
	 * @param moduleList the moduleList to set
	 */
	public void setModuleList(ArrayList<DropDownDTO> moduleList) {
		this.moduleList = moduleList;
	}
	/**
	 * @return the functionList
	 */
	public ArrayList<DropDownDTO> getFunctionList() {
		return functionList;
	}
	/**
	 * @param functionList the functionList to set
	 */
	public void setFunctionList(ArrayList<DropDownDTO> functionList) {
		this.functionList = functionList;
	}
	/**
	 * @return the moduleName
	 */
	public String getModuleName() {
		return moduleName;
	}
	/**
	 * @param moduleName the moduleName to set
	 */
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	/**
	 * @return the functionName
	 */
	public String getFunctionName() {
		return functionName;
	}
	/**
	 * @param functionName the functionName to set
	 */
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	/**
	 * @return the updateDate
	 */
	public String getUpdateDate() {
		return updateDate;
	}
	/**
	 * @param updateDate the updateDate to set
	 */
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}


	/**
	 * @return the formName
	 */
	public String getFormName() {
		return formName;
	}


	/**
	 * @param formName the formName to set
	 */
	public void setFormName(String formName) {
		this.formName = formName;
	}


	/**
	 * @return the actionValue
	 */
	public String getActionValue() {
		return actionValue;
	}


	/**
	 * @param actionValue the actionValue to set
	 */
	public void setActionValue(String actionValue) {
		this.actionValue = actionValue;
	}


	/**
	 * @return the subModuleID
	 */
	public String getSubModuleID() {
		return subModuleID;
	}


	/**
	 * @param subModuleID the subModuleID to set
	 */
	public void setSubModuleID(String subModuleID) {
		this.subModuleID = subModuleID;
	}


	/**
	 * @return the attributeName
	 */
	public String getAttributeName() {
		return attributeName;
	}


	/**
	 * @param attributeName the attributeName to set
	 */
	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}


	/**
	 * @return the attributeValue
	 */
	public String getAttributeValue() {
		return attributeValue;
	}


	/**
	 * @param attributeValue the attributeValue to set
	 */
	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}


	/**
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}


	/**
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


	/**
	 * @return the attributeId
	 */
	public String getAttributeId() {
		return attributeId;
	}


	/**
	 * @param attributeId the attributeId to set
	 */
	public void setAttributeId(String attributeId) {
		this.attributeId = attributeId;
	}


	/**
	 * @return the admindto
	 */
	public AdminAttrDTO getAdmindto() {
		return admindto;
	}


	/**
	 * @param admindto the admindto to set
	 */
	public void setAdmindto(AdminAttrDTO admindto) {
		this.admindto = admindto;
	}


	/**
	 * @return the moduleID
	 */
	public String getModuleID() {
		return moduleID;
	}


	/**
	 * @param moduleID the moduleID to set
	 */
	public void setModuleID(String moduleID) {
		this.moduleID = moduleID;
	}


	/**
	 * @return the functionID
	 */
	public String getFunctionID() {
		return functionID;
	}


	/**
	 * @param functionID the functionID to set
	 */
	public void setFunctionID(String functionID) {
		this.functionID = functionID;
	}

	

}
