
package com.wipro.igrs.serviceProvider.form;



import org.apache.struts.action.ActionForm;

import com.wipro.igrs.serviceProvider.dto.ServiceProviderDTO;


/**
 * @since 22 July 2013
 * File Name: ServiceProviderForm.java
 * @author Lavi Tripathi
 * @version 1.0
 * Created on 22 July 2013
 *
 */
public class ServiceProviderForm extends ActionForm {

	/**
	 * @serial
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * @author Lavi Tripathi
	 */
	
	ServiceProviderDTO objServiceProviderDTO = new ServiceProviderDTO();
	
	//ADDED BY SHRUTI
	private String lang;
	private String panCardFlag;
	private String form60Flag;
	private String errorMessageOTP;
	//added by shruti---29 april 2014
	private String UserId;
	
	
	public String getUserId() {
		return UserId;
	}

	public void setUserId(String userId) {
		UserId = userId;
	}

	public String getPanCardFlag() {
		return panCardFlag;
	}

	public void setPanCardFlag(String panCardFlag) {
		this.panCardFlag = panCardFlag;
	}

	public String getForm60Flag() {
		return form60Flag;
	}

	public void setForm60Flag(String form60Flag) {
		this.form60Flag = form60Flag;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	

	public ServiceProviderDTO getObjServiceProviderDTO() {
		return objServiceProviderDTO;
	}

	public void setObjServiceProviderDTO(ServiceProviderDTO objServiceProviderDTO) {
		this.objServiceProviderDTO = objServiceProviderDTO;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public void setErrorMessageOTP(String errorMessageOTP) {
		this.errorMessageOTP = errorMessageOTP;
	}

	public String getErrorMessageOTP() {
		return errorMessageOTP;
	}
	
	
	
	
	
	
	
}
