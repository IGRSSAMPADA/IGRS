/**
 * DepartmentalExamsDTO.java
 */

package com.wipro.igrs.empmgmt.dto;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author jagadish Jun 4, 2008
 * 
 */
public class DepartmentalExamsDTO implements Serializable {

	private String deptid;

	private String deptexamname;

	private String dateofexam;

	private String orgauthority;

	private String orgbody;

	private String placeofexam;

	private String dateofresult;

	private String examdetails;

	private String deptexamtxnid;

	private ArrayList deptExamResult;

	/**
	 * @return the deptid
	 */
	public String getDeptid() {
		return deptid;
	}

	/**
	 * @param deptid
	 *            the deptid to set
	 */
	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

	/**
	 * @return the deptexamname
	 */
	public String getDeptexamname() {
		return deptexamname;
	}

	/**
	 * @param deptexamname
	 *            the deptexamname to set
	 */
	public void setDeptexamname(String deptexamname) {
		this.deptexamname = deptexamname;
	}

	/**
	 * @return the dateofexam
	 */
	public String getDateofexam() {
		return dateofexam;
	}

	/**
	 * @param dateofexam
	 *            the dateofexam to set
	 */
	public void setDateofexam(String dateofexam) {
		this.dateofexam = dateofexam;
	}

	/**
	 * @return the orgauthority
	 */
	public String getOrgauthority() {
		return orgauthority;
	}

	/**
	 * @param orgauthority
	 *            the orgauthority to set
	 */
	public void setOrgauthority(String orgauthority) {
		this.orgauthority = orgauthority;
	}

	/**
	 * @return the orgbody
	 */
	public String getOrgbody() {
		return orgbody;
	}

	/**
	 * @param orgbody
	 *            the orgbody to set
	 */
	public void setOrgbody(String orgbody) {
		this.orgbody = orgbody;
	}

	/**
	 * @return the placeofexam
	 */
	public String getPlaceofexam() {
		return placeofexam;
	}

	/**
	 * @param placeofexam
	 *            the placeofexam to set
	 */
	public void setPlaceofexam(String placeofexam) {
		this.placeofexam = placeofexam;
	}

	/**
	 * @return the dateofresult
	 */
	public String getDateofresult() {
		return dateofresult;
	}

	/**
	 * @param dateofresult
	 *            the dateofresult to set
	 */
	public void setDateofresult(String dateofresult) {
		this.dateofresult = dateofresult;
	}

	/**
	 * @return the examdetails
	 */
	public String getExamdetails() {
		return examdetails;
	}

	/**
	 * @param examdetails
	 *            the examdetails to set
	 */
	public void setExamdetails(String examdetails) {
		this.examdetails = examdetails;
	}

	/**
	 * @return the deptexamtxnid
	 */
	public String getDeptexamtxnid() {
		return deptexamtxnid;
	}

	/**
	 * @param deptexamtxnid
	 *            the deptexamtxnid to set
	 */
	public void setDeptexamtxnid(String deptexamtxnid) {
		this.deptexamtxnid = deptexamtxnid;
	}

	/**
	 * @return the deptExamResult
	 */
	public ArrayList getDeptExamResult() {
		return deptExamResult;
	}

	/**
	 * @param deptExamResult
	 *            the deptExamResult to set
	 */
	public void setDeptExamResult(ArrayList deptExamResult) {
		this.deptExamResult = deptExamResult;
	}
}
