/**
 * PayrollForm.java
 */

package com.wipro.igrs.hrpayroll.payroll.form;

import java.util.ArrayList;

import com.wipro.igrs.baseaction.form.BaseForm;
import com.wipro.igrs.hrpayroll.payroll.dto.PayrollDTO;

/**
 * @author pranalk
 *
 */


public class PayrollForm extends BaseForm {
	private String actionType;
	
	//------------------ code is added for  payroll Formula
	
	private String compId;
	private String compName;
	private String compType;
	private String operId;
	private String compParentId;
	private String funOprId;
	private String pageTitle;
	private String chkCompId;
	
	private ArrayList empSalViewRes;
	
	
	
	PayrollDTO payrollDTO=new PayrollDTO();

	/**
	 * @return the payrollDTO
	 */
	public PayrollDTO getPayrollDTO() {
		return payrollDTO;
	}

	/**
	 * @param payrollDTO the payrollDTO to set
	 */
	public void setPayrollDTO(PayrollDTO payrollDTO) {
		this.payrollDTO = payrollDTO;
	}

	/**
	 * @return the actionType
	 */
	public String getActionType() {
		return actionType;
	}

	/**
	 * @param actionType the actionType to set
	 */
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getCompId() {
	    return compId;
	}

	public void setCompId(String compId) {
	    this.compId = compId;
	}

	public String getCompType() {
	    return compType;
	}

	public void setCompType(String compType) {
	    this.compType = compType;
	}

	public String getOperId() {
	    return operId;
	}

	public void setOperId(String operId) {
	    this.operId = operId;
	}

	public String getCompParentId() {
	    return compParentId;
	}

	public void setCompParentId(String compParentId) {
	    this.compParentId = compParentId;
	}

	public String getFunOprId() {
	    return funOprId;
	}

	public void setFunOprId(String funOprId) {
	    this.funOprId = funOprId;
	}

	/**
	 * @return the pageTitle
	 */
	public String getPageTitle() {
	    return pageTitle;
	}

	/**
	 * @param pageTitle the pageTitle to set
	 */
	public void setPageTitle(String pageTitle) {
	    this.pageTitle = pageTitle;
	}

	/**
	 * @return the empSalViewRes
	 */
	public ArrayList getEmpSalViewRes() {
	    return empSalViewRes;
	}

	/**
	 * @param empSalViewRes the empSalViewRes to set
	 */
	public void setEmpSalViewRes(ArrayList empSalViewRes) {
	    this.empSalViewRes = empSalViewRes;
	}

	public String getChkCompId() {
	    return chkCompId;
	}

	public void setChkCompId(String chkCompId) {
	    this.chkCompId = chkCompId;
	}

	public String getCompName() {
	    return compName;
	}

	public void setCompName(String compName) {
	    this.compName = compName;
	}


	
	
}