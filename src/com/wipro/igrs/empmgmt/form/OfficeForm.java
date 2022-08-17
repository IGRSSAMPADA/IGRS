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
package com.wipro.igrs.empmgmt.form;


import java.util.ArrayList;

import com.wipro.igrs.baseaction.form.BaseForm;
import com.wipro.igrs.empmgmt.dto.OfficalInfoDTO;
import com.wipro.igrs.empmgmt.dto.ServiceVerificationDTO;

/**
* 
* OfficeForm.java <br>
* OfficeForm <br>
* 
* @version 1.0
* 
* @author <b>WIPRO JAVA TEAM</b> <br>
*         Created on 12-Apr-2008 <br>
*         Last Modified on 28-Mar-2013
*/
@SuppressWarnings({ "rawtypes", "unchecked" })
public class OfficeForm extends BaseForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6725142972193547544L;

	private OfficalInfoDTO officalInfoDTO = new OfficalInfoDTO();

	private ArrayList serviceVerificationList = new ArrayList();

	private ArrayList documentList = new ArrayList();

	private String formName;

	private String actionName;
	
	private String verifyName;
	
	private String verifyDate;
	
	private String verifyComments;
	
	private String delServiceIndex;

	/**
	 * @param index
	 * @param value
	 */
	public void setServiceVerificationDTO(int index,
			ServiceVerificationDTO value) {
		for (; index >= serviceVerificationList.size(); serviceVerificationList
		.add(new ServiceVerificationDTO()))
			;

		serviceVerificationList.add(index, value);

	}

	/**
	 * @param index
	 * @return
	 */
	public ServiceVerificationDTO getServiceVerificationDTO(int index) {
		for (; index >= serviceVerificationList.size(); serviceVerificationList
		.add(new ServiceVerificationDTO()))
			;
		return (ServiceVerificationDTO) serviceVerificationList.get(index);
	}

	/**
	 * @param index
	 * @param value
	 */
	public void setServiceVerifyDocumentDTO(int index,
			ServiceVerificationDTO value) {
		for (; index >= documentList.size(); documentList
		.add(new ServiceVerificationDTO()))
			;

		documentList.add(index, value);

	}

	/**
	 * @param index
	 * @return
	 */
	public ServiceVerificationDTO getServiceVerifyDocumentDTO(int index) {
		for (; index >= documentList.size(); documentList
		.add(new ServiceVerificationDTO()))
			;
		return (ServiceVerificationDTO) documentList.get(index);
	}

	/**
	 * @return
	 */
	public ArrayList getServiceVerificationList() {
		return serviceVerificationList;
	}

	/**
	 * @param serviceVerificationList
	 */
	public void setServiceVerificationList(ArrayList serviceVerificationList) {
		this.serviceVerificationList = serviceVerificationList;
	}

	/**
	 * @return
	 */
	public ArrayList getDocumentList() {
		return documentList;
	}

	/**
	 * @param documentList
	 */
	public void setDocumentList(ArrayList documentList) {
		this.documentList = documentList;
	}

	/**
	 * @return the officalInfoDTO
	 */
	public OfficalInfoDTO getOfficalInfoDTO() {
		return officalInfoDTO;
	}

	/**
	 * @param officalInfoDTO
	 *            the officalInfoDTO to set
	 */
	public void setOfficalInfoDTO(OfficalInfoDTO officalInfoDTO) {
		this.officalInfoDTO = officalInfoDTO;
	}

	/**
	 * @return the formName
	 */
	public String getFormName() {
		return formName;
	}

	/**
	 * @param formName
	 *            the formName to set
	 */
	public void setFormName(String formName) {
		this.formName = formName;
	}

	/**
	 * @return the actionName
	 */
	public String getActionName() {
		return actionName;
	}

	/**
	 * @param actionName
	 *            the actionName to set
	 */
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	/**
	 * @return the verifyName
	 */
	public String getVerifyName() {
		return verifyName;
	}

	/**
	 * @param verifyName the verifyName to set
	 */
	public void setVerifyName(String verifyName) {
		this.verifyName = verifyName;
	}

	/**
	 * @return the verifyDate
	 */
	public String getVerifyDate() {
		return verifyDate;
	}

	/**
	 * @param verifyDate the verifyDate to set
	 */
	public void setVerifyDate(String verifyDate) {
		this.verifyDate = verifyDate;
	}

	/**
	 * @return the verifyComments
	 */
	public String getVerifyComments() {
		return verifyComments;
	}

	/**
	 * @param verifyComments the verifyComments to set
	 */
	public void setVerifyComments(String verifyComments) {
		this.verifyComments = verifyComments;
	}

	/**
	 * @return the delServiceIndex
	 */
	public String getDelServiceIndex() {
		return delServiceIndex;
	}

	/**
	 * @param delServiceIndex the delServiceIndex to set
	 */
	public void setDelServiceIndex(String delServiceIndex) {
		this.delServiceIndex = delServiceIndex;
	}

}