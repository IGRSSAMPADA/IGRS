

package com.wipro.igrs.hrpayroll.hrp.dto;


import java.util.ArrayList;


public class GradeCadreDTO {
	String		gradeId;
	
	String		gradeName;
	
	ArrayList	gradeList;
	
	String		cadreId;
	
	String		cadreName;
	
	String		cadreIds[];
	
	/**
	 * 
	 */
	public GradeCadreDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @return the gradeId
	 */
	public String getGradeId() {
		return gradeId;
	}
	
	/**
	 * @param gradeId
	 *            the gradeId to set
	 */
	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}
	
	/**
	 * @return the gradeName
	 */
	public String getGradeName() {
		return gradeName;
	}
	
	/**
	 * @param gradeName
	 *            the gradeName to set
	 */
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	
	/**
	 * @return the gradeList
	 */
	public ArrayList getGradeList() {
		return gradeList;
	}
	
	/**
	 * @param gradeList
	 *            the gradeList to set
	 */
	public void setGradeList(ArrayList gradeList) {
		this.gradeList = gradeList;
	}
	
	/**
	 * @return the cadreId
	 */
	public String getCadreId() {
		return cadreId;
	}
	
	/**
	 * @param cadreId
	 *            the cadreId to set
	 */
	public void setCadreId(String cadreId) {
		this.cadreId = cadreId;
	}
	
	/**
	 * @return the cadreName
	 */
	public String getCadreName() {
		return cadreName;
	}
	
	/**
	 * @param cadreName
	 *            the cadreName to set
	 */
	public void setCadreName(String cadreName) {
		this.cadreName = cadreName;
	}
	
	/**
	 * @return the cadreIds
	 */
	public String[] getCadreIds() {
		return cadreIds;
	}
	
	/**
	 * @param cadreIds
	 *            the cadreIds to set
	 */
	public void setCadreIds(String[] cadreIds) {
		this.cadreIds = cadreIds;
	}
	
}