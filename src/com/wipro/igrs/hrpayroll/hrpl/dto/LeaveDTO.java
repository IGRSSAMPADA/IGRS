

package com.wipro.igrs.hrpayroll.hrpl.dto;


import java.io.Serializable;


public class LeaveDTO implements Serializable {
	private String empid;

	private String name;

	private String days;

	private String leavetype;

	private String leaveStatus;

	private String leave_fromdate;

	private String leave_todate;

	private String leave_reason;

	private String convert_leve_type;

	private String leave_approved_reason;

	private String leavetypeID;

	private String leave_requestDate;

	private String leaveform;

	private String fiscal_fromyear;

	private String fiscal_toyear;

	public String leaveAvail;

	public String leaveUsed;

	public String fiscalyearid;

	public String addressonleave;

	public String phonenumberonleave;

	private String leaveDetailForm;

	private String transactionid;
	
	private String leavetypeonselection;
	
	public String getLeavetypeonselection() {
		return leavetypeonselection;
	}

	public void setLeavetypeonselection(String leavetypeonselection) {
		this.leavetypeonselection = leavetypeonselection;
	}

	/**
	 * @return the empid
	 */
	public String getEmpid() {
		return empid;
	}

	/**
	 * @param empid
	 *            the empid to set
	 */
	public void setEmpid(String empid) {
		this.empid = empid;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the days
	 */
	public String getDays() {
		return days;
	}

	/**
	 * @param days
	 *            the days to set
	 */
	public void setDays(String days) {
		this.days = days;
	}

	/**
	 * @return the leavetype
	 */
	public String getLeavetype() {
		return leavetype;
	}

	/**
	 * @param leavetype
	 *            the leavetype to set
	 */
	public void setLeavetype(String leavetype) {
		this.leavetype = leavetype;
	}

	/**
	 * @return the leaveStatus
	 */
	public String getLeaveStatus() {
		return leaveStatus;
	}

	/**
	 * @param leaveStatus
	 *            the leaveStatus to set
	 */
	public void setLeaveStatus(String leaveStatus) {
		this.leaveStatus = leaveStatus;
	}

	/**
	 * @return the leave_fromdate
	 */
	public String getLeave_fromdate() {
		return leave_fromdate;
	}

	/**
	 * @param leave_fromdate
	 *            the leave_fromdate to set
	 */
	public void setLeave_fromdate(String leave_fromdate) {
		this.leave_fromdate = leave_fromdate;
	}

	/**
	 * @return the leave_todate
	 */
	public String getLeave_todate() {
		return leave_todate;
	}

	/**
	 * @param leave_todate
	 *            the leave_todate to set
	 */
	public void setLeave_todate(String leave_todate) {
		this.leave_todate = leave_todate;
	}

	/**
	 * @return the leave_reason
	 */
	public String getLeave_reason() {
		return leave_reason;
	}

	/**
	 * @param leave_reason
	 *            the leave_reason to set
	 */
	public void setLeave_reason(String leave_reason) {
		this.leave_reason = leave_reason;
	}

	/**
	 * @return the convert_leve_type
	 */
	public String getConvert_leve_type() {
		return convert_leve_type;
	}

	/**
	 * @param convert_leve_type
	 *            the convert_leve_type to set
	 */
	public void setConvert_leve_type(String convert_leve_type) {
		this.convert_leve_type = convert_leve_type;
	}

	/**
	 * @return the leave_approved_reason
	 */
	public String getLeave_approved_reason() {
		return leave_approved_reason;
	}

	/**
	 * @param leave_approved_reason
	 *            the leave_approved_reason to set
	 */
	public void setLeave_approved_reason(String leave_approved_reason) {
		this.leave_approved_reason = leave_approved_reason;
	}

	/**
	 * @return the leavetypeID
	 */
	public String getLeavetypeID() {
		return leavetypeID;
	}

	/**
	 * @param leavetypeID
	 *            the leavetypeID to set
	 */
	public void setLeavetypeID(String leavetypeID) {
		this.leavetypeID = leavetypeID;
	}

	/**
	 * @return the leave_requestDate
	 */
	public String getLeave_requestDate() {
		return leave_requestDate;
	}

	/**
	 * @param leave_requestDate
	 *            the leave_requestDate to set
	 */
	public void setLeave_requestDate(String leave_requestDate) {
		this.leave_requestDate = leave_requestDate;
	}

	/**
	 * @return the leaveform
	 */
	public String getLeaveform() {
		return leaveform;
	}

	/**
	 * @param leaveform
	 *            the leaveform to set
	 */
	public void setLeaveform(String leaveform) {
		this.leaveform = leaveform;
	}

	/**
	 * @return the fiscal_fromyear
	 */
	public String getFiscal_fromyear() {
		return fiscal_fromyear;
	}

	/**
	 * @param fiscal_fromyear
	 *            the fiscal_fromyear to set
	 */
	public void setFiscal_fromyear(String fiscal_fromyear) {
		this.fiscal_fromyear = fiscal_fromyear;
	}

	/**
	 * @return the fiscal_toyear
	 */
	public String getFiscal_toyear() {
		return fiscal_toyear;
	}

	/**
	 * @param fiscal_toyear
	 *            the fiscal_toyear to set
	 */
	public void setFiscal_toyear(String fiscal_toyear) {
		this.fiscal_toyear = fiscal_toyear;
	}

	/**
	 * @return the leaveAvail
	 */
	public String getLeaveAvail() {
		return leaveAvail;
	}

	/**
	 * @param leaveAvail
	 *            the leaveAvail to set
	 */
	public void setLeaveAvail(String leaveAvail) {
		this.leaveAvail = leaveAvail;
	}

	/**
	 * @return the leaveUsed
	 */
	public String getLeaveUsed() {
		return leaveUsed;
	}

	/**
	 * @param leaveUsed
	 *            the leaveUsed to set
	 */
	public void setLeaveUsed(String leaveUsed) {
		this.leaveUsed = leaveUsed;
	}

	/**
	 * @return the fiscalyearid
	 */
	public String getFiscalyearid() {
		return fiscalyearid;
	}

	/**
	 * @param fiscalyearid
	 *            the fiscalyearid to set
	 */
	public void setFiscalyearid(String fiscalyearid) {
		this.fiscalyearid = fiscalyearid;
	}

	/**
	 * @return the addressonleave
	 */
	public String getAddressonleave() {
		return addressonleave;
	}

	/**
	 * @param addressonleave
	 *            the addressonleave to set
	 */
	public void setAddressonleave(String addressonleave) {
		this.addressonleave = addressonleave;
	}

	/**
	 * @return the phonenumberonleave
	 */
	public String getPhonenumberonleave() {
		return phonenumberonleave;
	}

	/**
	 * @param phonenumberonleave
	 *            the phonenumberonleave to set
	 */
	public void setPhonenumberonleave(String phonenumberonleave) {
		this.phonenumberonleave = phonenumberonleave;
	}

	/**
	 * @return the leaveDetailForm
	 */
	public String getLeaveDetailForm() {
		return leaveDetailForm;
	}

	/**
	 * @param leaveDetailForm
	 *            the leaveDetailForm to set
	 */
	public void setLeaveDetailForm(String leaveDetailForm) {
		this.leaveDetailForm = leaveDetailForm;
	}

	/**
	 * @return the transactionid
	 */
	public String getTransactionid() {
		return transactionid;
	}

	/**
	 * @param transactionid
	 *            the transactionid to set
	 */
	public void setTransactionid(String transactionid) {
		this.transactionid = transactionid;
	}

}
