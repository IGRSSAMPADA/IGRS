
package com.wipro.igrs.spotInsp.dto;
/**
 * ===========================================================================
 * File           :   VisitBookingDTO.java
 * Description    :   Represents the DTO Class

 * Author         :   Pavani param
 * Created Date   :   Apr 08, 2008.

 * ===========================================================================
 */
import java.io.Serializable;
import java.util.ArrayList;

public class SpotInspNewDTO implements Serializable  {
	 
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

public String getSelectedId() {
	return selectedId;
}

public void setSelectedId(String selectedId) {
	this.selectedId = selectedId;
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

public String getSubClauseName() {
	return subClauseName;
}


public String getSelectFlag() {
	return selectFlag;
}

public void setSelectFlag(String selectFlag) {
	this.selectFlag = selectFlag;
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



   
   
}