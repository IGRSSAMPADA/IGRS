package com.wipro.igrs.empfund.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

public class EmpFundForm extends org.apache.struts.action.ActionForm {

	private String empId;
	private String fundTypeId;
	private String fundTypeName;
	private String fundAccountNo;
	private String fundLocation;
	private String componentName;
	private String fundAmount;
	
	private boolean fundAmountRanged = false;
	
	private String empName;
	private String nomneeName;
	private String nomneeRelationship;
	private String nomneeAddress;
	private String nomneeAge;
	private String nomneeSharePCT;
	
	

    public EmpFundForm () {
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
	 * @return the fundTypeId
	 */
	public String getFundTypeId() {
		return fundTypeId;
	}



	/**
	 * @param fundTypeId the fundTypeId to set
	 */
	public void setFundTypeId(String fundTypeId) {
		this.fundTypeId = fundTypeId;
	}



	/**
	 * @return the fundTypeName
	 */
	public String getFundTypeName() {
		return fundTypeName;
	}



	/**
	 * @param fundTypeName the fundTypeName to set
	 */
	public void setFundTypeName(String fundTypeName) {
		this.fundTypeName = fundTypeName;
	}



	/**
	 * @return the fundAccountNo
	 */
	public String getFundAccountNo() {
		return fundAccountNo;
	}



	/**
	 * @param fundAccountNo the fundAccountNo to set
	 */
	public void setFundAccountNo(String fundAccountNo) {
		this.fundAccountNo = fundAccountNo;
	}



	/**
	 * @return the fundLocation
	 */
	public String getFundLocation() {
		return fundLocation;
	}



	/**
	 * @param fundLocation the fundLocation to set
	 */
	public void setFundLocation(String fundLocation) {
		this.fundLocation = fundLocation;
	}


	/**
	 * @return the componentName
	 */
	public String getComponentName() {
		return componentName;
	}
	/**
	 * @param componentName the componentName to set
	 */
	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}
	/**
	 * @return the fundAmount
	 */
	public String getFundAmount() {
		return fundAmount;
	}



	/**
	 * @param fundAmount the fundAmount to set
	 */
	public void setFundAmount(String fundAmount) {
		this.fundAmount = fundAmount;
	}



	/**
	 * @return the empName
	 */
	public String getEmpName() {
		return empName;
	}



	/**
	 * @param empName the empName to set
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
	}



	/**
	 * @return the nomneeName
	 */
	public String getNomneeName() {
		return nomneeName;
	}



	/**
	 * @param nomneeName the nomneeName to set
	 */
	public void setNomneeName(String nomneeName) {
		this.nomneeName = nomneeName;
	}



	/**
	 * @return the nomneeRelationship
	 */
	public String getNomneeRelationship() {
		return nomneeRelationship;
	}



	/**
	 * @param nomneeRelationship the nomneeRelationship to set
	 */
	public void setNomneeRelationship(String nomneeRelationship) {
		this.nomneeRelationship = nomneeRelationship;
	}



	/**
	 * @return the nomneeAddress
	 */
	public String getNomneeAddress() {
		return nomneeAddress;
	}



	/**
	 * @param nomneeAddress the nomneeAddress to set
	 */
	public void setNomneeAddress(String nomneeAddress) {
		this.nomneeAddress = nomneeAddress;
	}



	/**
	 * @return the nomneeAge
	 */
	public String getNomneeAge() {
		return nomneeAge;
	}



	/**
	 * @param nomneeAge the nomneeAge to set
	 */
	public void setNomneeAge(String nomneeAge) {
		this.nomneeAge = nomneeAge;
	}



	/**
	 * @return the nomneeSharePCT
	 */
	public String getNomneeSharePCT() {
		return nomneeSharePCT;
	}



	/**
	 * @param nomneeSharePCT the nomneeSharePCT to set
	 */
	public void setNomneeSharePCT(String nomneeSharePCT) {
		this.nomneeSharePCT = nomneeSharePCT;
	}

	/**
	 * @return the fundAmountRanged
	 */
	public boolean isFundAmountRanged() {
		return fundAmountRanged;
	}
	/**
	 * @param fundAmountRanged the fundAmountRanged to set
	 */
	public void setFundAmountRanged(boolean fundAmountRanged) {
		this.fundAmountRanged = fundAmountRanged;
	}
	public void reset(ActionMapping actionMapping, HttpServletRequest request) {        
    }

    public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest request) {
        return null;
    }


}