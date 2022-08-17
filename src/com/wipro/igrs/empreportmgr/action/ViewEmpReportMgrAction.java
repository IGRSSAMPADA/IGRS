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

public class ViewEmpReportMgrAction extends BaseAction {
    
	private Logger logger = (Logger) Logger.getLogger(ViewEmpReportMgrAction.class);
	private EmpReportMgrBD empReportMgrBD;
	private empReportMgrDto dto; 
	private EmpReportMgrForm reportMgrForm;
	private ArrayList EmpReportMgrList=null;
    public ViewEmpReportMgrAction() 
    {
    	
    }
    
    //public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    //    // TODO: Write method body
    //    throw new UnsupportedOperationException("Method is not implemented");
   // }

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception 
			
	{
		reportMgrForm = (EmpReportMgrForm)form;
		logger.info("we are in empReportMgrAction");
		String target = "empReportManagerMaster";
		ActionErrors errors;
		dto = new empReportMgrDto();
		//dto.setTreasuryName(treasuryForm.getTreasuryName());
		//dto.setTreasuryAddress(treasuryForm.getTreasuryAddress());
		//dto.setTreasuryPhone(treasuryForm.getTreasuryPhone());
		String user = (String) session.getAttribute("UserId");

		user = "Fabrigaz";
		
		empReportMgrBD = new EmpReportMgrBD();
		reportMgrForm.setRoles(empReportMgrBD.getAllRoles());
		System.out.println("start");
		setList(request);
		//reportMgrForm.setRole("null");
		return mapping.findForward(target);
	}
	
	private void setList(HttpServletRequest request)
	{
		try {
			//if(reportMgrForm.getRole()!="")
			EmpReportMgrList = empReportMgrBD.getUsersForThisRole(reportMgrForm.getRole());
			for (int i = 0; i < EmpReportMgrList.size(); i++) {
				System.out.println( ((UserProfileDTO)EmpReportMgrList.get(i)).getFirstName() );
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		request.setAttribute("EmpReportMgrList", EmpReportMgrList);
	}
    
}