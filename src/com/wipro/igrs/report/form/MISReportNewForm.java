package com.wipro.igrs.report.form;

/**
 * ===========================================================================
 * File           :   MISReportNewForm.java
 * Description    :   Represents the Bean class

 * Author         :   Siddhartha Chakrabarti
 * Created Date   :   Apr 22, 2016.

 * ===========================================================================
 */





import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import com.wipro.igrs.report.dto.MISReportNewDTO;



public class MISReportNewForm extends ActionForm{

	
	String selected;
	String notSelected;
	String actionName;
	String group;
    String  flag;
    String  insertflag;
    private String language;

    MISReportNewDTO dto=new MISReportNewDTO();
	
  ArrayList deedInstrumentMasterList=new ArrayList();
	
	ArrayList activityList=new ArrayList();
	
	ArrayList spotAdminList =new ArrayList();
	
	ArrayList selectedActivityList =new ArrayList();

	
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

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getInsertflag() {
		return insertflag;
	}

	public void setInsertflag(String insertflag) {
		this.insertflag = insertflag;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public MISReportNewDTO getDto() {
		return dto;
	}

	public void setDto(MISReportNewDTO dto) {
		this.dto = dto;
	}

	public ArrayList getDeedInstrumentMasterList() {
		return deedInstrumentMasterList;
	}

	public void setDeedInstrumentMasterList(ArrayList deedInstrumentMasterList) {
		this.deedInstrumentMasterList = deedInstrumentMasterList;
	}

	public ArrayList getActivityList() {
		return activityList;
	}

	public void setActivityList(ArrayList activityList) {
		this.activityList = activityList;
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

		
}
