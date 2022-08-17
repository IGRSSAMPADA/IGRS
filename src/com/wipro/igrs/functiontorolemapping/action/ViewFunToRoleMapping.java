package com.wipro.igrs.functiontorolemapping.action;





import java.io.IOException;
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

public class ViewFunToRoleMapping extends BaseAction {
    
	private Logger logger = (Logger) Logger.getLogger(ViewFunToRoleMapping.class);
	
	private FunToRoleMappingBD funToRoleMappingBD;
	private FunToRoleMappingDto dto; 
	private FunToRoleMappingForm funToRoleMappingForm;
	private ArrayList funToRoleMappingList=null;
	ActionErrors errors;
	
    public ViewFunToRoleMapping() 
    {
    	
    }
    
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception
			
	{
		funToRoleMappingForm = (FunToRoleMappingForm)form;
		logger.info("we are in empReportMgrAction");
		String target = "viewFunToRoleMapping";
		
		dto = new FunToRoleMappingDto();

		funToRoleMappingBD = new FunToRoleMappingBD();
		System.out.println("start");
		
		try {
			funToRoleMappingList = funToRoleMappingBD.getFunToRoleMappingDetailed();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			errors = new ActionErrors();
			System.out.println("io eroor ............................");
			errors.add("funToRoleMapping",new ActionError("funToRoleMapping.errors.database"));
			saveErrors(request, errors);
			return mapping.findForward("viewFunToRoleMapping");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
		
		request.setAttribute("funToRoleMappingList", funToRoleMappingList);
		
		return mapping.findForward(target);
	}
	
}