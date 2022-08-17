package com.wipro.igrs.functiontorolemapping.action;


import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.activitymaster.bd.ActivityBD;
import com.wipro.igrs.activitymaster.dto.ActivityDTO;
import com.wipro.igrs.activitymaster.form.ActivityForm;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.empreportmgr.bd.EmpReportMgrBD;
import com.wipro.igrs.empreportmgr.dto.empReportMgrDto;
import com.wipro.igrs.empreportmgr.form.EmpReportMgrForm;
import com.wipro.igrs.functiontorolemapping.bd.FunToRoleMappingBD;
import com.wipro.igrs.functiontorolemapping.dto.FunToRoleMappingDto;
import com.wipro.igrs.functiontorolemapping.form.FunToRoleMappingForm;
import com.wipro.igrs.treasuryMaster.bd.TreasureBD;
import com.wipro.igrs.treasuryMaster.dto.TreasureDTO;
import com.wipro.igrs.treasuryMaster.exception.IllegalTreasuryException;
import com.wipro.igrs.treasuryMaster.form.TreasuryForm;
import com.wipro.igrs.useracctmgmt.dto.UserProfileDTO;

public class PreAddNewMappingAction extends BaseAction {
    
	private Logger logger = (Logger) Logger.getLogger(PreAddNewMappingAction.class);
	
	private FunToRoleMappingBD funToRoleMappingBD;
	private FunToRoleMappingDto dto; 
	private FunToRoleMappingForm funToRoleMappingForm;
	private ArrayList funToRoleMappingList=null;
	
	public PreAddNewMappingAction() 
    {
    	
    }
    
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception 
			
	{
		funToRoleMappingForm = (FunToRoleMappingForm)form;
		logger.info("we are in empReportMgrAction");
		String target = "addFunToRoleMapping";
		ActionErrors errors;
		
		
		funToRoleMappingBD = new FunToRoleMappingBD();
		
		funToRoleMappingForm.setRoleId("null");
		funToRoleMappingForm.setModuleId("null");
	    funToRoleMappingForm.setActivityId("notnull");
		funToRoleMappingForm.setRoles(funToRoleMappingBD.getAllRoles());
		funToRoleMappingForm.setModules(funToRoleMappingBD.getAllModules());
		funToRoleMappingForm.setSubModules(new ArrayList());
		funToRoleMappingForm.setFunctions(new ArrayList());
		//commented by shruti in order to avoid compilation issue
		/*funToRoleMappingForm.setActivities(funToRoleMappingBD.getAllActivities());*/
		//end
		System.out.println("combo boxes .. loaded");

		return mapping.findForward(target);
	}
	
}