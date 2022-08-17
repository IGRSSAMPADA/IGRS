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

public class MapFunToRoleAction extends BaseAction {
    
	private Logger logger = (Logger) Logger.getLogger(MapFunToRoleAction.class);
	
	private FunToRoleMappingBD funToRoleMappingBD;
	private FunToRoleMappingDto dto; 
	private FunToRoleMappingForm funToRoleMappingForm;
	private ArrayList funToRoleMappingList=null;
	
	public MapFunToRoleAction() 
    {
    	
    }
    
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception 
			
	{
		
		
		funToRoleMappingForm = (FunToRoleMappingForm)form;
		logger.info("we are in empReportMgrAction");
		String target = "success";
		ActionErrors errors;
		if(isCancelled(request))
		{
			return mapping.findForward(target);
		}
		
		for (int i = 0; i < funToRoleMappingForm.getSelected().length; i++) {
			 System.out.println(funToRoleMappingForm.getSelected()[i]);
		}
		
		
		funToRoleMappingBD = new FunToRoleMappingBD();
		if(funToRoleMappingForm.getActivityId().equalsIgnoreCase("null"))
		{
			funToRoleMappingBD.deleteFunToRoleMapping(funToRoleMappingForm.getFunToRoleMappingid());
			System.out.println("deleting ....");
		}
		
		
		for (int i = 0; i < funToRoleMappingForm.getSelected().length; i++) {
			System.out.println("creating .... "+i);
			dto = new FunToRoleMappingDto(funToRoleMappingForm.getFunctionId(),funToRoleMappingForm.getRoleId(),
					funToRoleMappingForm.getSelected()[i],funToRoleMappingForm.getModuleId(),
					funToRoleMappingForm.getSubModuleId());
			funToRoleMappingBD.addFunToRoleMapping(dto);
		}
		setList(request);
		System.out.println("combo boxes .. loaded");
		
		return mapping.findForward(target);
	}
	
	private void setList(HttpServletRequest request)
	{
		try {
			funToRoleMappingList = funToRoleMappingBD.getFunToRoleMappingDetailed();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		request.setAttribute("funToRoleMappingList", funToRoleMappingList);
	}
	
}