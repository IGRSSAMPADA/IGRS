package com.wipro.igrs.empmgmt.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.empmgmt.bd.EmpmgmtViewBD;
import com.wipro.igrs.empmgmt.dto.DeptExamDTO;
import com.wipro.igrs.empmgmt.dto.DeptTrainingDTO;
import com.wipro.igrs.empmgmt.form.DepartmentForm;

public class DepartmentViewAction extends BaseAction{ 

	private Logger logger = Logger.getLogger(DepartmentViewAction.class);
	/*
	 * Generated Methods
	 */

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,HttpSession session) throws Exception {
		String  FORWARD_PAGE=null;
		logger.debug("in dept view action");
		DepartmentForm deptForm =(DepartmentForm) form;
		DeptTrainingDTO deptTrainDTO = null;
		DeptExamDTO  deptExamDTO = null;
		 EmpmgmtViewBD employeeViewBD = null;
		 employeeViewBD = new EmpmgmtViewBD();
		 logger.debug("after bd object creation");
		 ArrayList trainingList = null;
		 ArrayList examList = null;
		// HttpSession session=request.getSession(true);
		 deptTrainDTO = deptForm.getDeptTrainingDTO();
		 deptExamDTO = deptForm.getDeptExamDTO();
		
		 if(request.getParameter("id")!=null)
		{
			 logger.debug("in if block");
			 trainingList = employeeViewBD.getTraingDetails(request.getParameter("id"));
			 examList =  employeeViewBD.getExamDetails(request.getParameter("id"));
			 logger.debug("inside getparameter "+trainingList.size());
			 logger.debug("inside employeeViewBD "+examList.size());

		}
		 
		 
		 if(trainingList!=null){
			 session.setAttribute("trainingList",trainingList);
		 }
		     
		 if(examList!=null){
			 session.setAttribute("examList",examList);
		 }
	        
			 
	        FORWARD_PAGE="ViewDepartmentDetails";
	        logger.debug("forwarding to page name "+FORWARD_PAGE);
	 	   return mapping.findForward(FORWARD_PAGE);
	
}
}
