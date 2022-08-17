package com.wipro.igrs.complaintdetails.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

public class ComplaintDetailsForm extends org.apache.struts.action.ActionForm {

	private String paymentDateFrom;
	private String paymentDateTo;
	private String empCode;
	private String empFirstName;
	private String empMidName;
	private String empLastName;
	private String location;
	private String officeId;
	private String status;
	
    public String getOfficeId() {
		return officeId;
	}

	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public ComplaintDetailsForm () {
    }

    public void reset(ActionMapping actionMapping, HttpServletRequest request) {
        // TODO: Write method body
        //throw new UnsupportedOperationException("Method is not implemented");
    }

    public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest request) {
        // TODO: Write method body
        //throw new UnsupportedOperationException("Method is not implemented");
    	return null;
    }

	public String getPaymentDateFrom() {
		return paymentDateFrom;
	}

	public void setPaymentDateFrom(String paymentDateFrom) {
		this.paymentDateFrom = paymentDateFrom;
	}

	public String getPaymentDateTo() {
		return paymentDateTo;
	}

	public void setPaymentDateTo(String paymentDateTo) {
		this.paymentDateTo = paymentDateTo;
	}

	public String getEmpFirstName() {
		return empFirstName;
	}

	public void setEmpFirstName(String empFirstName) {
		this.empFirstName = empFirstName;
	}

	public String getEmpMidName() {
		return empMidName;
	}

	public void setEmpMidName(String empMidName) {
		this.empMidName = empMidName;
	}

	public String getEmpLastName() {
		return empLastName;
	}

	public void setEmpLastName(String empLastName) {
		this.empLastName = empLastName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}


}