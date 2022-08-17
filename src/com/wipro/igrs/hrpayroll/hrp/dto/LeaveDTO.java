

package com.wipro.igrs.hrpayroll.hrp.dto;

public class LeaveDTO {
	
	private String		leave_type_id;
	
	private String		leave_type_name;
	
	private String		leave_type_desc;
	
	private String		maximum_no_days;
	
	private String		gender;
	
	private String		leaveStatus;
	
	private String[]	chkLeave;
	
	private String[]	editLeaveIds;
	
	private String[]	editLeaveTypes;
	
	private String[]	editLeaveDesc;
	
	private String[]	editLeaveGender;
	
	private String[]	editLeaveDays;
	
	/**
	 * 
	 */
	public LeaveDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @return the leave_type_id
	 */
	public String getLeave_type_id() {
		return leave_type_id;
	}
	
	/**
	 * @param leave_type_id
	 *            the leave_type_id to set
	 */
	public void setLeave_type_id(String leave_type_id) {
		this.leave_type_id = leave_type_id;
	}
	
	/**
	 * @return the leave_type_name
	 */
	public String getLeave_type_name() {
		return leave_type_name;
	}
	
	/**
	 * @param leave_type_name
	 *            the leave_type_name to set
	 */
	public void setLeave_type_name(String leave_type_name) {
		this.leave_type_name = leave_type_name;
	}
	
	/**
	 * @return the leave_type_desc
	 */
	public String getLeave_type_desc() {
		return leave_type_desc;
	}
	
	/**
	 * @param leave_type_desc
	 *            the leave_type_desc to set
	 */
	public void setLeave_type_desc(String leave_type_desc) {
		this.leave_type_desc = leave_type_desc;
	}
	
	/**
	 * @return the maximum_no_days
	 */
	public String getMaximum_no_days() {
		return maximum_no_days;
	}
	
	/**
	 * @param maximum_no_days
	 *            the maximum_no_days to set
	 */
	public void setMaximum_no_days(String maximum_no_days) {
		this.maximum_no_days = maximum_no_days;
	}
	
	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}
	
	/**
	 * @param gender
	 *            the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
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
	 * @return the chkLeave
	 */
	public String[] getChkLeave() {
		return chkLeave;
	}
	
	/**
	 * @param chkLeave
	 *            the chkLeave to set
	 */
	public void setChkLeave(String[] chkLeave) {
		this.chkLeave = chkLeave;
	}
	
	/**
	 * @return the editLeaveIds
	 */
	public String[] getEditLeaveIds() {
		return editLeaveIds;
	}
	
	/**
	 * @param editLeaveIds
	 *            the editLeaveIds to set
	 */
	public void setEditLeaveIds(String[] editLeaveIds) {
		this.editLeaveIds = editLeaveIds;
	}
	
	/**
	 * @return the editLeaveTypes
	 */
	public String[] getEditLeaveTypes() {
		return editLeaveTypes;
	}
	
	/**
	 * @param editLeaveTypes
	 *            the editLeaveTypes to set
	 */
	public void setEditLeaveTypes(String[] editLeaveTypes) {
		this.editLeaveTypes = editLeaveTypes;
	}
	
	/**
	 * @return the editLeaveDesc
	 */
	public String[] getEditLeaveDesc() {
		return editLeaveDesc;
	}
	
	/**
	 * @param editLeaveDesc
	 *            the editLeaveDesc to set
	 */
	public void setEditLeaveDesc(String[] editLeaveDesc) {
		this.editLeaveDesc = editLeaveDesc;
	}
	
	/**
	 * @return the editLeaveGender
	 */
	public String[] getEditLeaveGender() {
		return editLeaveGender;
	}
	
	/**
	 * @param editLeaveGender
	 *            the editLeaveGender to set
	 */
	public void setEditLeaveGender(String[] editLeaveGender) {
		this.editLeaveGender = editLeaveGender;
	}
	
	/**
	 * @return the editLeaveDays
	 */
	public String[] getEditLeaveDays() {
		return editLeaveDays;
	}
	
	/**
	 * @param editLeaveDays the editLeaveDays to set
	 */
	public void setEditLeaveDays(String[] editLeaveDays) {
		this.editLeaveDays = editLeaveDays;
	}
	
}
