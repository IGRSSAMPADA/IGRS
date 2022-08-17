package com.wipro.igrs.empmgmt.form;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

import com.wipro.igrs.baseaction.form.BaseForm;
import com.wipro.igrs.empmgmt.dto.DeptExamDTO;
import com.wipro.igrs.empmgmt.dto.DeptTrainingDTO;

public class DepartmentForm extends BaseForm{

	
	
	private DeptTrainingDTO deptTrainingDTO=new DeptTrainingDTO();
	private DeptExamDTO deptExamDTO=new DeptExamDTO();
	private ArrayList trainingList=new ArrayList();
	private ArrayList examList=new ArrayList();
	private String formName;
	private String actionName;
	
	private String employeeId;
	
	public DeptExamDTO getDeptExamDTO() {
		return deptExamDTO;
	}

	public void setDeptExamDTO(DeptExamDTO deptExamDTO) {
		this.deptExamDTO = deptExamDTO;
	}

	public DeptTrainingDTO getDeptTrainingDTO() {
		return deptTrainingDTO;
	}

	public void setDeptTrainingDTO(DeptTrainingDTO deptTrainingDTO) {
		this.deptTrainingDTO = deptTrainingDTO;
	}

	public ArrayList getExamList() {
		return examList;
	}

	public void setExamList(ArrayList examList) {
		this.examList = examList;
	}

	public ArrayList getTrainingList() {
		return trainingList;
	}

	public void setTrainingList(ArrayList trainingList) {
		this.trainingList = trainingList;
	}

	
	public void setTrainingArrDTO(int index ,DeptTrainingDTO value){
		for(;index>=trainingList.size();trainingList.add(new DeptTrainingDTO()));
		
		trainingList.add(index,value);
		
	}
	public DeptTrainingDTO getTrainingArrDTO(int index){
		for(;index>=trainingList.size();trainingList.add(new DeptTrainingDTO()));
		return (DeptTrainingDTO)trainingList.get(index);
	}
	
	public void setExamArrDTO(int index ,DeptExamDTO value){
		for(;index>=examList.size();examList.add(new DeptExamDTO()));
		
		examList.add(index,value);
		
	}
	public DeptExamDTO getExamArrDTO(int index){
		for(;index>=examList.size();examList.add(new DeptExamDTO()));
		return (DeptExamDTO)examList.get(index);
	}

	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	/**
	 * @return the employeeId
	 */
	public String getEmployeeId() {
		return employeeId;
	}

	/**
	 * @param employeeId the employeeId to set
	 */
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	
}
