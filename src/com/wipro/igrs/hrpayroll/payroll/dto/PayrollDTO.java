/**
 * PayrollDTO.java
 */
package com.wipro.igrs.hrpayroll.payroll.dto;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author pranalk
 *
 */
public class PayrollDTO implements Serializable {

	private String employeeid;
	private Integer totalsal;
	private Integer taxamount;
	private Integer professiontax;
	private ArrayList componentlist;
	private String componentid;
	private String coponentname;
	private String componenttype;
	private String salaryamount;
	private String errormsg;
	private String errorcode;
	private String month;
	
	//------------------ code is added for  payroll Formula
	private String name;
	private String value;
	private ArrayList compIdList;
	private ArrayList oprIdList;

	

	/**
	 * @return the compIdList
	 */
	public ArrayList getCompIdList() {
	    return compIdList;
	}

	/**
	 * @param compIdList the compIdList to set
	 */
	public void setCompIdList(ArrayList compIdList) {
	    this.compIdList = compIdList;
	}

	public String getEmployeeid() {
		return employeeid;
	}

	public void setEmployeeid(String employeeid) {
		this.employeeid = employeeid;
	}

	
	
	/**
	 * @return the componentid
	 */
	public String getComponentid() {
		return componentid;
	}

	/**
	 * @param componentid the componentid to set
	 */
	public void setComponentid(String componentid) {
		this.componentid = componentid;
	}

	/**
	 * @return the coponentname
	 */
	public String getCoponentname() {
		return coponentname;
	}

	/**
	 * @param coponentname the coponentname to set
	 */
	public void setCoponentname(String coponentname) {
		this.coponentname = coponentname;
	}

	/**
	 * @return the componenttype
	 */
	public String getComponenttype() {
		return componenttype;
	}

	/**
	 * @param componenttype the componenttype to set
	 */
	public void setComponenttype(String componenttype) {
		this.componenttype = componenttype;
	}

	/**
	 * @return the salaryamount
	 */
	public String getSalaryamount() {
		return salaryamount;
	}

	/**
	 * @param salaryamount the salaryamount to set
	 */
	public void setSalaryamount(String salaryamount) {
		this.salaryamount = salaryamount;
	}

	/**
	 * @return the errormsg
	 */
	public String getErrormsg() {
		return errormsg;
	}

	/**
	 * @param errormsg the errormsg to set
	 */
	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}

	/**
	 * @return the errorcode
	 */
	public String getErrorcode() {
		return errorcode;
	}

	/**
	 * @param errorcode the errorcode to set
	 */
	public void setErrorcode(String errorcode) {
		this.errorcode = errorcode;
	}

	/**
	 * @return the componentlist
	 */
	public ArrayList getComponentlist() {
		return componentlist;
	}

	/**
	 * @param componentlist the componentlist to set
	 */
	public void setComponentlist(ArrayList componentlist) {
		this.componentlist = componentlist;
	}

	/**
	 * @return the totalsal
	 */
	public Integer getTotalsal() {
		return totalsal;
	}

	/**
	 * @param totalsal the totalsal to set
	 */
	public void setTotalsal(Integer totalsal) {
		this.totalsal = totalsal;
	}

	/**
	 * @return the taxamount
	 */
	public Integer getTaxamount() {
		return taxamount;
	}

	/**
	 * @param taxamount the taxamount to set
	 */
	public void setTaxamount(Integer taxamount) {
		this.taxamount = taxamount;
	}

	/**
	 * @return the professiontax
	 */
	public Integer getProfessiontax() {
		return professiontax;
	}

	/**
	 * @param professiontax the professiontax to set
	 */
	public void setProfessiontax(Integer professiontax) {
		this.professiontax = professiontax;
	}

	/**
	 * @return the name
	 */
	public String getName() {
	    return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
	    this.name = name;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
	    return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
	    this.value = value;
	}

	/**
	 * @return the oprIdList
	 */
	public ArrayList getOprIdList() {
	    return oprIdList;
	}

	/**
	 * @param oprIdList the oprIdList to set
	 */
	public void setOprIdList(ArrayList oprIdList) {
	    this.oprIdList = oprIdList;
	}

	/**
	 * @return the month
	 */
	public String getMonth() {
	    return month;
	}

	/**
	 * @param month the month to set
	 */
	public void setMonth(String month) {
	    this.month = month;
	}


}
