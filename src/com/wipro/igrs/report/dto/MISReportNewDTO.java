package com.wipro.igrs.report.dto;

/**
 * ===========================================================================
 * File           :   MISReportNewDTO.java
 * Description    :   Represents the DTO Class

 * Author         :   Siddhartha Chakrabarti
 * Created Date   :   Apr 22, 2016.

 * ===========================================================================
 */




import java.io.Serializable;
import java.util.ArrayList;

public class MISReportNewDTO implements Serializable {

	
	   String selectedName;
	   String  actionName;
	   String activityName;
	   String activityId;
	   String selectedId;
	   String subClauseName;
	   String propertyName;
	   String selectFlag;


	
	
    public String getSelectedName() {
		return selectedName;
	}
	public void setSelectedName(String selectedName) {
		this.selectedName = selectedName;
	}
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public String getActivityId() {
		return activityId;
	}
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	public String getSelectedId() {
		return selectedId;
	}
	public void setSelectedId(String selectedId) {
		this.selectedId = selectedId;
	}
	public String getSubClauseName() {
		return subClauseName;
	}
	public void setSubClauseName(String subClauseName) {
		this.subClauseName = subClauseName;
	}
	public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	public String getSelectFlag() {
		return selectFlag;
	}
	public void setSelectFlag(String selectFlag) {
		this.selectFlag = selectFlag;
	}
		
	
}
