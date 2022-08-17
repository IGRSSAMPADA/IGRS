

package com.wipro.igrs.hrpayroll.hrp.dto;


import java.io.Serializable;


public class GradeDTO implements Serializable {
	private String	gradeId;
	
	private String	gradeName;
	
	private String	minSalSlab;
	
	private String	maxSalSlab;
	
	private String	increeAmount;
	
	private String	gradeStatus;
	
	private String	chkGrade[];				// deleting and editing
	
	private String	editGradeIds[];
	
	private String	editGradeNames[];
	
	private String	editGradeMinSalSlabs[];
	
	private String	editGradeMaxSalSlabs[];
	
	private String	editGradeIncreeAmounts[];
	
	/**
	 * 
	 */
	public GradeDTO() {
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
	 * @return the minSalSlab
	 */
	public String getMinSalSlab() {
		return minSalSlab;
	}
	
	/**
	 * @param minSalSlab
	 *            the minSalSlab to set
	 */
	public void setMinSalSlab(String minSalSlab) {
		this.minSalSlab = minSalSlab;
	}
	
	/**
	 * @return the maxSalSlab
	 */
	public String getMaxSalSlab() {
		return maxSalSlab;
	}
	
	/**
	 * @param maxSalSlab
	 *            the maxSalSlab to set
	 */
	public void setMaxSalSlab(String maxSalSlab) {
		this.maxSalSlab = maxSalSlab;
	}
	
	/**
	 * @return the increeAmount
	 */
	public String getIncreeAmount() {
		return increeAmount;
	}
	
	/**
	 * @param increeAmount
	 *            the increeAmount to set
	 */
	public void setIncreeAmount(String increeAmount) {
		this.increeAmount = increeAmount;
	}
	
	/**
	 * @return the gradeStatus
	 */
	public String getGradeStatus() {
		return gradeStatus;
	}
	
	/**
	 * @param gradeStatus
	 *            the gradeStatus to set
	 */
	public void setGradeStatus(String gradeStatus) {
		this.gradeStatus = gradeStatus;
	}
	
	/**
	 * @return the chkGrade
	 */
	public String[] getChkGrade() {
		return chkGrade;
	}
	
	/**
	 * @param chkGrade
	 *            the chkGrade to set
	 */
	public void setChkGrade(String[] chkGrade) {
		this.chkGrade = chkGrade;
	}
	
	/**
	 * @return the editGradeIds
	 */
	public String[] getEditGradeIds() {
		return editGradeIds;
	}
	
	/**
	 * @param editGradeIds
	 *            the editGradeIds to set
	 */
	public void setEditGradeIds(String[] editGradeIds) {
		this.editGradeIds = editGradeIds;
	}
	
	/**
	 * @return the editGradeNames
	 */
	public String[] getEditGradeNames() {
		return editGradeNames;
	}
	
	/**
	 * @param editGradeNames
	 *            the editGradeNames to set
	 */
	public void setEditGradeNames(String[] editGradeNames) {
		this.editGradeNames = editGradeNames;
	}
	
	/**
	 * @return the editGradeMinSalSlabs
	 */
	public String[] getEditGradeMinSalSlabs() {
		return editGradeMinSalSlabs;
	}
	
	/**
	 * @param editGradeMinSalSlabs
	 *            the editGradeMinSalSlabs to set
	 */
	public void setEditGradeMinSalSlabs(String[] editGradeMinSalSlabs) {
		this.editGradeMinSalSlabs = editGradeMinSalSlabs;
	}
	
	/**
	 * @return the editGradeMaxSalSlabs
	 */
	public String[] getEditGradeMaxSalSlabs() {
		return editGradeMaxSalSlabs;
	}
	
	/**
	 * @param editGradeMaxSalSlabs
	 *            the editGradeMaxSalSlabs to set
	 */
	public void setEditGradeMaxSalSlabs(String[] editGradeMaxSalSlabs) {
		this.editGradeMaxSalSlabs = editGradeMaxSalSlabs;
	}
	
	/**
	 * @return the editGradeIncreeAmounts
	 */
	public String[] getEditGradeIncreeAmounts() {
		return editGradeIncreeAmounts;
	}
	
	/**
	 * @param editGradeIncreeAmounts
	 *            the editGradeIncreeAmounts to set
	 */
	public void setEditGradeIncreeAmounts(String[] editGradeIncreeAmounts) {
		this.editGradeIncreeAmounts = editGradeIncreeAmounts;
	}
	
}