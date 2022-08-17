
/* Copyright (c) 2007-08 WIPRO INFOTECH. All Rights Reserved.
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
 */ 

/* 
 * FILE NAME: RegistrationCompletionFORM.java
 * @author NIHAR R MISHRA
 * @version 1.0 
 * INITIAL CODING: 28th June 2008
 * @DESCRIPTION:   THIS CLASS REPRESENTS THE FORM BEAN FOR REGISTRATION COMPLETION ACTION.
 */


package com.wipro.igrs.RegistrationCompletion.form;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;

import com.wipro.igrs.CitizenFeedback.action.CitizenFeedbackAction;
import com.wipro.igrs.RegistrationCompletion.dto.RegistrationCompletionDTO;

/**
 * @author NIHAR M.
 *
 */
public class RegistrationCompletionFORM extends ActionForm {

	private Logger logger = (Logger) Logger.getLogger(CitizenFeedbackAction.class);
	String forwardJsp = new String("createCitizenFeedback");
	
	private RegistrationCompletionDTO regDTO = new RegistrationCompletionDTO();
	private String formName;
	private String actionName;
	
	private ArrayList regIdDetails = new ArrayList();
	private ArrayList statusList = new ArrayList();
	
	public RegistrationCompletionDTO getRegDTO() {
		return regDTO;
	}
	public void setRegDTO(RegistrationCompletionDTO regDTO) {
		this.regDTO = regDTO;
	}
	public String getFormName() {
		return formName;
	}
	public void setFormName(String formName) {
		this.formName = formName;
	}
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public ArrayList getRegIdDetails() {
		return regIdDetails;
	}
	public void setRegIdDetails(ArrayList regIdDetails) {
		this.regIdDetails = regIdDetails;
	}
	public ArrayList getStatusList() {
		return statusList;
	}
	public void setStatusList(ArrayList statusList) {
		this.statusList = statusList;
	}
}
