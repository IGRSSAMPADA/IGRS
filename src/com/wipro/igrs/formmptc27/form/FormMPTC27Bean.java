package com.wipro.igrs.formmptc27.form;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

public class FormMPTC27Bean extends org.apache.struts.action.ActionForm {


	private String empId;
	private ArrayList allEmpId;
	private String[] selectEmp;
	private ArrayList selectEmpList;

	
	
	

	/**
	 * @return the selectEmp
	 */
	public String[] getSelectEmp() {
		return selectEmp;
	}

	/**
	 * @param selectEmp the selectEmp to set
	 */
	public void setSelectEmp(String[] selectEmp) {
		this.selectEmp = selectEmp;
	}

	/**
	 * @return the selectEmpList
	 */
	public ArrayList getSelectEmpList() {
		return selectEmpList;
	}

	/**
	 * @param selectEmpList the selectEmpList to set
	 */
	public void setSelectEmpList(ArrayList selectEmpList) {
		this.selectEmpList = selectEmpList;
	}

	/**
	 * @return the empId
	 */
	public String getEmpId() {
		return empId;
	}

	/**
	 * @param empId the empId to set
	 */
	public void setEmpId(String empId) {
		this.empId = empId;
	}

	
	/**
	 * @return the allEmpId
	 */
	public ArrayList getAllEmpId() {
		return allEmpId;
	}

	/**
	 * @param allEmpId the allEmpId to set
	 */
	public void setAllEmpId(ArrayList allEmpId) {
		this.allEmpId = allEmpId;
	}

	public FormMPTC27Bean () {
    }

    public void reset(ActionMapping actionMapping, HttpServletRequest request) {
        // TODO: Write method body
        
    }

    public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest request) {
        // TODO: Write method body
        return null;
    }


}