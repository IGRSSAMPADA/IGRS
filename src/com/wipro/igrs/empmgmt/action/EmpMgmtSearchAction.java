/**
 * EmpMgmtAction.java
 */

package com.wipro.igrs.empmgmt.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.empmgmt.bd.TrainingBD;
import com.wipro.igrs.empmgmt.dto.EmployeeDTO;

/**
 * @author root Jun 6, 2008
 */
public class EmpMgmtSearchAction extends BaseAction {
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,HttpSession session)
			throws Exception {

		String employeeId = request.getParameter("employeeId");
	//	HttpSession session = request.getSession(true);
		TrainingBD trainingBD = new TrainingBD();
		EmployeeDTO employeeDTO = null;
		PrintWriter out = response.getWriter();
		
		employeeDTO = trainingBD.getEmployeeSearch(employeeId);
		StringBuffer sbEmployee = new StringBuffer();
		
		if (employeeDTO.getEmployeeNumber() != null) {
			sbEmployee.append(employeeDTO.getEmployeeNumber() + "|");
		}

		
		out.print(sbEmployee.toString());

		return mapping.findForward(null);
	}

}
