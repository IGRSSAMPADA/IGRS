

package com.wipro.igrs.hrpayroll.hrp.dto;


import java.util.ArrayList;


public class SalaryDTO {
	
	private String		gradeId;
	
	private String		gradeName;
	
	private String		minSalSlab;
	
	private String		maxSalSlab;
	
	private String		increeAmount;
	
	private ArrayList	gradeList;
	
	private String		cadreId;
	
	private String		cadreName;
	
	private ArrayList	cadreList;
	
	private String		componentId;
	
	private String		componentName;
	
	private String		componentValue;
	
	private String		componentType;
	
	private String		componentStatus;
	
	private String		selectedComponentId;
	
	private String		componentIdArr[];
	
	private String		componentNameArr[];
	
	private String		componentValueArr[];
	
	private String		typeId;
	
	private String		empId;
	
	/**
	 * 
	 */
	public SalaryDTO() {
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
	 * @return the cadreList
	 */
	public ArrayList getCadreList() {
		return cadreList;
	}
	
	/**
	 * @param cadreList
	 *            the cadreList to set
	 */
	public void setCadreList(ArrayList cadreList) {
		this.cadreList = cadreList;
	}
	
	/**
	 * @return the componentId
	 */
	public String getComponentId() {
		return componentId;
	}
	
	/**
	 * @param componentId
	 *            the componentId to set
	 */
	public void setComponentId(String componentId) {
		this.componentId = componentId;
	}
	
	/**
	 * @return the componentName
	 */
	public String getComponentName() {
		return componentName;
	}
	
	/**
	 * @param componentName
	 *            the componentName to set
	 */
	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}
	
	/**
	 * @return the componentValue
	 */
	public String getComponentValue() {
		return componentValue;
	}
	
	/**
	 * @param componentValue
	 *            the componentValue to set
	 */
	public void setComponentValue(String componentValue) {
		this.componentValue = componentValue;
	}
	
	/**
	 * @return the componentType
	 */
	public String getComponentType() {
		return componentType;
	}
	
	/**
	 * @param componentType
	 *            the componentType to set
	 */
	public void setComponentType(String componentType) {
		this.componentType = componentType;
	}
	
	/**
	 * @return the componentStatus
	 */
	public String getComponentStatus() {
		return componentStatus;
	}
	
	/**
	 * @param componentStatus
	 *            the componentStatus to set
	 */
	public void setComponentStatus(String componentStatus) {
		this.componentStatus = componentStatus;
	}
	
	/**
	 * @return the selectedComponentId
	 */
	public String getSelectedComponentId() {
		return selectedComponentId;
	}
	
	/**
	 * @param selectedComponentId
	 *            the selectedComponentId to set
	 */
	public void setSelectedComponentId(String selectedComponentId) {
		this.selectedComponentId = selectedComponentId;
	}
	
	/**
	 * @return the componentIdArr
	 */
	public String[] getComponentIdArr() {
		return componentIdArr;
	}
	
	/**
	 * @param componentIdArr
	 *            the componentIdArr to set
	 */
	public void setComponentIdArr(String[] componentIdArr) {
		this.componentIdArr = componentIdArr;
	}
	
	/**
	 * @return the componentNameArr
	 */
	public String[] getComponentNameArr() {
		return componentNameArr;
	}
	
	/**
	 * @param componentNameArr
	 *            the componentNameArr to set
	 */
	public void setComponentNameArr(String[] componentNameArr) {
		this.componentNameArr = componentNameArr;
	}
	
	/**
	 * @return the componentValueArr
	 */
	public String[] getComponentValueArr() {
		return componentValueArr;
	}
	
	/**
	 * @param componentValueArr
	 *            the componentValueArr to set
	 */
	public void setComponentValueArr(String[] componentValueArr) {
		this.componentValueArr = componentValueArr;
	}
	
	/**
	 * @return the typeId
	 */
	public String getTypeId() {
		return typeId;
	}
	
	/**
	 * @param typeId
	 *            the typeId to set
	 */
	public void setTypeId(String typeId) {
		this.typeId = typeId;
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
	
}