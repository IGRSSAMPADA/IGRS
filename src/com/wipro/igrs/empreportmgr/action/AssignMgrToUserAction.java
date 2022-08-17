package com.wipro.igrs.empreportmgr.action;



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
import com.wipro.igrs.treasuryMaster.bd.TreasureBD;
import com.wipro.igrs.treasuryMaster.dto.TreasureDTO;
import com.wipro.igrs.treasuryMaster.exception.IllegalTreasuryException;
import com.wipro.igrs.treasuryMaster.form.TreasuryForm;
import com.wipro.igrs.useracctmgmt.dto.UserProfileDTO;

public class AssignMgrToUserAction extends BaseAction {
    
	private Logger logger = (Logger) Logger.getLogger(AssignMgrToUserAction.class);
	private EmpReportMgrBD empReportMgrBD;
	private empReportMgrDto dto; 
	private EmpReportMgrForm reportMgrForm;
	private ArrayList EmpReportMgrList=null;
    
	public AssignMgrToUserAction() 
    {
    	
    }
    
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception 
			
	{
		String target = "viewEmpReportManager";
		if(isCancelled(request))
		{
			return mapping.findForward(target);
		}
		
		reportMgrForm = (EmpReportMgrForm)form;
		logger.info("we are in empReportMgrAction");
		//String target = "viewEmpReportManager";
		ActionErrors errors;
		
		String user = (String) session.getAttribute("UserId");
		user = "Fabrigaz";
		dto = new empReportMgrDto();
		empReportMgrBD = new EmpReportMgrBD();
		
		
		dto.setEmpSupervisorId(reportMgrForm.getEmpSupervisorId());
		dto.setEmpId(reportMgrForm.getEmpId());
		dto.setEmpSupervisorRole(reportMgrForm.getRole());
		
		empReportMgrBD.addEmpReportManager(dto);
		
		System.out.println("after adding ............");
		
		reportMgrForm.setRoles(empReportMgrBD.getAllRoles());
		
		if(!reportMgrForm.getRole().equals(null))
		reportMgrForm.setManagers(empReportMgrBD.getUsersForThisRole(reportMgrForm.getRole()));
		
		System.out.println("roles loaded");
		reportMgrForm.setRole("null");
		//setList(request);
		return mapping.findForward(target);
	}
	
}