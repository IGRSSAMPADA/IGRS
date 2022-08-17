package com.wipro.igrs.salarygrademaster.form;

import java.util.ArrayList;

public class SalaryGradeForm extends com.wipro.igrs.baseaction.form.BaseForm{

	private String salCompGradeId;
	private String componentId;
	private String gradeId;
	private String gradeName;
	private ArrayList allComponents;
	private ArrayList allGrades;
	private String[] selected;
	private String selectedGrade;
	private String selectedComponent;
	private String oldComponentId;
	private String oldGradeId;
	
	public String getOldComponentId() {
		return oldComponentId;
	}
	public void setOldComponentId(String oldComponentId) {
		this.oldComponentId = oldComponentId;
	}
	public String getOldGradeId() {
		return oldGradeId;
	}
	public void setOldGradeId(String oldGradeId) {
		this.oldGradeId = oldGradeId;
	}
	public String getSelectedGrade() {
		return selectedGrade;
	}
	public void setSelectedGrade(String selectedGrade) {
		this.selectedGrade = selectedGrade;
	}
	public String getSelectedComponent() {
		return selectedComponent;
	}
	public void setSelectedComponent(String selectedComponent) {
		this.selectedComponent = selectedComponent;
	}
	public String[] getSelected() {
		return selected;
	}
	public void setSelected(String[] selected) {
		this.selected = selected;
	}
	public String getComponentId() {
		return componentId;
	}
	public void setComponentId(String componentId) {
		this.componentId = componentId;
	}
	public String getGradeId() {
		return gradeId;
	}
	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	public ArrayList getAllComponents() {
		return allComponents;
	}
	public void setAllComponents(ArrayList allComponents) {
		this.allComponents = allComponents;
	}
	public ArrayList getAllGrades() {
		return allGrades;
	}
	public void setAllGrades(ArrayList allGrades) {
		this.allGrades = allGrades;
	}
	public String getSalCompGradeId() {
		return salCompGradeId;
	}
	public void setSalCompGradeId(String salCompGradeId) {
		this.salCompGradeId = salCompGradeId;
	}
	
}
