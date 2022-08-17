/**
 * 
 */
package com.wipro.igrs.revenueManagement.form;

/**
 * @author SHREERAJ KHARE
 *
 */
import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

import com.wipro.igrs.revenueManagement.dto.RevMgtAdvDTO;
import com.wipro.igrs.revenueManagement.dto.RevMgtDTO;
public class RevMgtAdvForm extends ActionForm {
	
	private static long serialVersionUID = 1L;
	private String formName;
	private String actionName;
	private String uId;
	RevMgtAdvDTO rmapDto = new RevMgtAdvDTO();
	private String toDate;
	private String fromDate;

	

	/**
	 * @return the toDate
	 */
	public String getToDate() {
		return toDate;
	}
	/**
	 * @param toDate the toDate to set
	 */
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	/**
	 * @return the fromDate
	 */
	public String getFromDate() {
		return fromDate;
	}
	/**
	 * @param fromDate the fromDate to set
	 */
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	/**
	 * @return the serialVersionUID
	 */
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	/**
	 * @param serialVersionUID the serialVersionUID to set
	 */
	public static void setSerialVersionUID(long serialVersionUID) {
		RevMgtAdvForm.serialVersionUID = serialVersionUID;
	}

	/**
	 * @return the rmapDto
	 */
	public RevMgtAdvDTO getRmapDto() {
		return rmapDto;
	}
	/**
	 * @param rmapDto the rmapDto to set
	 */
	public void setRmapDto(RevMgtAdvDTO rmapDto) {
		this.rmapDto = rmapDto;
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
	 * @return the actionName
	 */
	public String getActionName() {
		return actionName;
	}
	/**
	 * @param actionName the actionName to set
	 */
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	/**
	 * @return the uId
	 */
	public String getuId() {
		return uId;
	}
	/**
	 * @param uId the uId to set
	 */
	public void setuId(String uId) {
		this.uId = uId;
	}
	
}
