package com.wipro.igrs.login.dto;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;


public class MasterListDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
   
	private String roleID;
	private String roleName;
	
	//Following added by roopam 11 April 2013
	
	ArrayList officeDistList=new ArrayList();
	private String officeID;
	public String getOfficeName() {
		return officeName;
	}
	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}
	private String officeName;
	private String distID;
	
	
	
	
	
	
	
	public ArrayList getOfficeDistList() {
		return officeDistList;
	}
	public void setOfficeDistList(ArrayList officeDistList) {
		this.officeDistList = officeDistList;
	}
	public String getOfficeID() {
		return officeID;
	}
	public void setOfficeID(String officeID) {
		this.officeID = officeID;
	}
	public String getDistID() {
		return distID;
	}
	public void setDistID(String distID) {
		this.distID = distID;
	}
	public String getRoleID() {
		return roleID;
	}
	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	/**
	 * added by simran for logged in user dtls
	 */
	
	private String userName;
	private String designation;
	private String signature;
	private String penType;
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getPenType() {
		return penType;
	}
	public void setPenType(String penType) {
		this.penType = penType;
	}
	
	
}
