package com.wipro.igrs.spotInsp.form;


/**
 * ===========================================================================
 * File           :   SpotInspForm.java
 * Description    :   Represents the Bean Class

 * Author         :   Pavani Param
 * Created Date   :   Apr 08,2008.

 * ===========================================================================
 */

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import com.wipro.igrs.spotInsp.dto.SpotInspNewDTO;

public class SpotInspNewForm extends ActionForm {
  //--- START---SpotInspecion --View-- Details.
   
	String selected;
	String notSelected;
	String actionName;
	String group;
    String  flag;
    String  insertflag;
    private String language;
	SpotInspNewDTO spotDTO=new SpotInspNewDTO();
	ArrayList deedInstrumentMasterList=new ArrayList();
	
	ArrayList activityList=new ArrayList();
	
	ArrayList spotAdminList =new ArrayList();
	
	ArrayList selectedActivityList =new ArrayList();
	
	
	
	public String getInsertflag() {
		return insertflag;
	}
	public void setInsertflag(String insertflag) {
		this.insertflag = insertflag;
	}
	public String getSelected() {
		return selected;
	}
	public void setSelected(String selected) {
		this.selected = selected;
	}
	public String getNotSelected() {
		return notSelected;
	}
	public void setNotSelected(String notSelected) {
		this.notSelected = notSelected;
	}
	
	
	public ArrayList getDeedInstrumentMasterList() {
		return deedInstrumentMasterList;
	}
	public void setDeedInstrumentMasterList(ArrayList deedInstrumentMasterList) {
		this.deedInstrumentMasterList = deedInstrumentMasterList;
	}
	public SpotInspNewDTO getSpotDTO() {
		return spotDTO;
	}
	public void setSpotDTO(SpotInspNewDTO spotDTO) {
		this.spotDTO = spotDTO;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public ArrayList getSpotAdminList() {
		return spotAdminList;
	}
	public void setSpotAdminList(ArrayList spotAdminList) {
		this.spotAdminList = spotAdminList;
	}
	public ArrayList getSelectedActivityList() {
		return selectedActivityList;
	}
	public void setSelectedActivityList(ArrayList selectedActivityList) {
		this.selectedActivityList = selectedActivityList;
	}
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public ArrayList getActivityList() {
		return activityList;
	}
	public void setActivityList(ArrayList activityList) {
		this.activityList = activityList;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getLanguage() {
		return language;
	}
	
	
} 
