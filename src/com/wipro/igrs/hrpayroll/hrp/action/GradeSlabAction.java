package com.wipro.igrs.hrpayroll.hrp.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.common.HrpayrollConstant;
import com.wipro.igrs.hrpayroll.hrp.bd.BasicSlabBD;
import com.wipro.igrs.hrpayroll.hrp.bd.HRPayrollBD;

public class GradeSlabAction extends BaseAction {
	private Logger logger = (Logger) Logger.getLogger(GradeSlabAction.class);

	String FORWARD_PAGE = "success";
	BasicSlabBD basicSlabBD = null;
	HRPayrollBD hRPayrollBD =null;
	//BasicSlabBD basicSlabBD = null;
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,HttpSession session) throws Exception {
		hRPayrollBD = new HRPayrollBD();
		String pageAction = request.getParameter("pageAction");
		String gradeId = request.getParameter("slabId");
		String mode = request.getParameter("mode");
		if(mode != null && mode.length() > 0 && mode.equalsIgnoreCase("new")){
			session.setAttribute(HrpayrollConstant.SESSION_SALARY_GRADE_LIST,
					null);
			session.setAttribute("basicSlabList", null);
			session.setAttribute("allottedList", null);
			session.setAttribute("gradeId", null);
		}
		
		ArrayList gradeList = hRPayrollBD.fetchGrades();
		// HttpSession session = request.getSession();
		if (gradeList != null)
			session.setAttribute(HrpayrollConstant.SESSION_SALARY_GRADE_LIST,
					gradeList); // "salaryGradeList"
		logger.info("gradeList:" + gradeList);
		FORWARD_PAGE="showSlabs";
		
		if(pageAction != null && pageAction.length() > 0 && pageAction.equalsIgnoreCase("getSlabs")){
			basicSlabBD = new BasicSlabBD();
			ArrayList basicSlabList = basicSlabBD.retrieveBasicSlabList();
			logger.debug("basicSlabList---> "+basicSlabList);
			ArrayList allottedList = basicSlabBD.getAllottedSlabList(gradeId);
			
			if(basicSlabList != null)
				session.setAttribute("basicSlabList", basicSlabList);

			if(allottedList != null)
				session.setAttribute("allottedList", allottedList);
			
			session.setAttribute("gradeId", gradeId);
			
			FORWARD_PAGE="showSlabs";
		}
		if(pageAction != null && pageAction.length() > 0 && pageAction.equalsIgnoreCase("updateSlabs")){
			String newSlabIds = request.getParameter("newSlabId");
			String oldSlabIds = request.getParameter("oldSlabId");
			basicSlabBD = new BasicSlabBD();
			boolean update1 = basicSlabBD.updateOldSlabs(oldSlabIds,gradeId);
			boolean update2 = basicSlabBD.updateNewSlabs(newSlabIds,gradeId);
			
			FORWARD_PAGE = "success";
		}
		
		return mapping.findForward(FORWARD_PAGE);
	}


}
