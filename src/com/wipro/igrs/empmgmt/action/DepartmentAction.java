/**
 * Copyright (c) 2009-10 WIPRO INFOTECH. All Rights Reserved.
 *
 *  This software is the confidential and proprietary information of WIPRO
 *  ("Confidential Information"). You shall not
 *  disclose such Confidential Information and shall use it only in
 *  accordance with the terms of the license agreement you entered into
 *  with WIPRO.
 *
 * WIPRO MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE
 * SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, OR NON-INFRINGEMENT. WIPRO SHALL NOT BE
 * LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF
 * USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES.
 *
 * Customer specific copyright notice - NA
 */
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
import com.wipro.igrs.common.EmpmgmtConstant;
import com.wipro.igrs.empmgmt.bd.CommonBD;
import com.wipro.igrs.empmgmt.dto.DeptExamDTO;
import com.wipro.igrs.empmgmt.dto.DeptTrainingDTO;
import com.wipro.igrs.empmgmt.form.DepartmentForm;
import com.wipro.igrs.empmgmt.rule.EmpMgmtRule;

/**
* 
* DepartmentAction.java <br>
* DepartmentAction <br>
* 
* @version 1.0
* 
* @author <b>WIPRO JAVA TEAM</b> <br>
*         Created on 12-Apr-2008 <br>
*         Last Modified on 28-Mar-2013
*/
@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
public class DepartmentAction extends BaseAction {
	private Logger logger = Logger.getLogger(DepartmentAction.class);

	/**
	 * This is the main action called from the Struts framework.
	 * 
	 * @param mapping
	 *            The ActionMapping used to select this instance.
	 * @param form
	 *            The optional ActionForm bean for this request.
	 * @param request
	 *            The HTTP Request we are processing.
	 * @param response
	 *            The HTTP Response we are processing.
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,HttpSession session)
			throws Exception {
		logger.debug("department1");
		String FORWARDPAGE = null;
		String parameter = request.getParameter("name");
		String formName = request.getParameter("formName");
		String actionName = request.getParameter("actionName");
		logger.debug("actionName  " + actionName);
		String str = "Data is submitted successfully !";
	//	HttpSession session = request.getSession();
		EmpMgmtRule empMgmtRule = null;
		CommonBD commonBD = null;
		ArrayList errorList = null;
		String strUserId = session.getAttribute("UserId").toString();

		logger.debug("department2");

		// if(parameter.equalsIgnoreCase("department"))
		// {
		try {
			logger.debug("department3");
			DepartmentForm departmentForm = (DepartmentForm) form;
			commonBD = new CommonBD();
			DeptExamDTO deptExamDTO = new DeptExamDTO();
			DeptTrainingDTO deptTrainingDTO = new DeptTrainingDTO();
			// if(parameter.equalsIgnoreCase("department"))

			if (formName != null && formName.equals("department")) {
				session.removeAttribute("empid");
				logger.debug("hello");
				FORWARDPAGE = "department";
			}

			else if (parameter != null && parameter.equals("checkempid")) {

				deptExamDTO = departmentForm.getDeptExamDTO();

				String empid = commonBD.searchEmpID(deptExamDTO
						.getEmployeeId());
				if (empid!=null && empid.length()> 0) {
					session.setAttribute("empid", deptExamDTO.getEmployeeId());
					FORWARDPAGE = "department";
				} else {
					session.removeAttribute("empid");
					errorList = new ArrayList();
					errorList.add("Employee Id not Present");
					request.setAttribute(EmpmgmtConstant.EMP_MGMT_ERROR_FLAG,
							"true");
					request.setAttribute(EmpmgmtConstant.EMP_MGMT_ERROR_LIST,
							errorList);
					FORWARDPAGE = "department";
				}
			}

			else if (actionName != null
					&& parameter.equalsIgnoreCase("department")
					&& actionName.equalsIgnoreCase("InsertDepartmentDetails")) {
				logger.debug("department Insert");
				empMgmtRule = new EmpMgmtRule();
				commonBD = new CommonBD();
				errorList = new ArrayList();
				ArrayList errordepartmentdetail = new ArrayList();
				ArrayList errordepartmentexam = new ArrayList();
				ArrayList trainingList = departmentForm.getTrainingList();
				ArrayList examList = departmentForm.getExamList();
				// request.setAttribute("trainingList", trainingList);
				// request.setAttribute("examList", examList);

				DeptTrainingDTO deptTrainingDTO1 = null;
				// remove values which are comming from UI as null Property
				// START
				for (int i = 0; i < trainingList.size(); i++) {
					deptTrainingDTO1 = (DeptTrainingDTO) trainingList.get(i);

					if (deptTrainingDTO1.getTrainingName() == null
							|| deptTrainingDTO1.getTrainingName().equals("")) {
						logger.debug("how many times i am comming here "
								+ i);
						trainingList.remove(i);
					}

				}
				departmentForm.setTrainingList(trainingList);
				logger.debug("Afer removeing the list "
						+ trainingList.size());

				// ////////////////////

				DeptExamDTO deptExamDTO1 = null;
				// remove values which are comming from UI as null Property
				// START
				for (int i = 0; i < examList.size(); i++) {
					deptExamDTO1 = (DeptExamDTO) examList.get(i);

					if (deptExamDTO1.getNameOfExam() == null
							|| deptExamDTO1.getNameOfExam().equals("")) {
						logger.debug("how many times i am comming here "
								+ i);
						examList.remove(i);
					}

				}
				departmentForm.setExamList(examList);
				logger.debug("Afer removeing the list " + examList.size());

				errordepartmentdetail = empMgmtRule
						.validateDepartmentalTrainningList(trainingList);
				errordepartmentexam = empMgmtRule
						.validateDepartmentalExamList(examList);
				if (errordepartmentdetail != null) {
					for (int i = 0; i < errordepartmentdetail.size(); i++) {
						errorList.add(errordepartmentdetail.get(i));
					}
				}
				if (errordepartmentexam != null) {
					for (int i = 0; i < errordepartmentexam.size(); i++) {
						errorList.add(errordepartmentexam.get(i));
					}
				}
				if (empMgmtRule.isError()) {
					request.setAttribute(EmpmgmtConstant.EMP_MGMT_ERROR_FLAG,
							Boolean.TRUE);
					request.setAttribute(EmpmgmtConstant.EMP_MGMT_ERROR_LIST,
							errorList);
					System.out.print("data are not inserted !");
					FORWARDPAGE = "department";

				} else {

					session.setAttribute("trainingList", trainingList);
					session.setAttribute("examList", examList);

					FORWARDPAGE = "departmentview";

				}
			} else if (actionName != null
					&& parameter.equalsIgnoreCase("department")
					&& actionName.equalsIgnoreCase("InsertDepartment")) {
				ArrayList trainingList = (ArrayList) session
						.getAttribute("trainingList");
				ArrayList examList = (ArrayList) session
						.getAttribute("examList");
				// deptExamDTO=new DeptExamDTO();
				// String empid=deptExamDTO.getEmployeeId();
				// logger.debug("empid "+empid);
				String empid = session.getAttribute("empid").toString();
				logger.debug("trainingList " + trainingList);
				logger.debug("examList " + examList);
				boolean result = commonBD.addDepartment(trainingList, examList,
						strUserId, empid);
				// boolean result1 = commonBD.insertTraining(trainingList);
				// boolean result2 = commonBD.insertExam(examList);
				request.setAttribute("str", str);

				FORWARDPAGE = "departmentsubmit";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return mapping.findForward(FORWARDPAGE);
	}
}