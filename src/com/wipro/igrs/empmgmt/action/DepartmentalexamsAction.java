/*
 * DepartmentalexamsAction.java
 * 
 */

package com.wipro.igrs.empmgmt.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.common.EmpmgmtConstant;
import com.wipro.igrs.empmgmt.bd.DepartmentalExamsBD;
import com.wipro.igrs.empmgmt.dto.DepartmentalExamsDTO;
import com.wipro.igrs.empmgmt.dto.DepartmentalExamsResultDTO;
import com.wipro.igrs.empmgmt.form.DepartmentalexamsForm;
import com.wipro.igrs.empmgmt.rule.EmpMgmtRule;

/**
 * MyEclipse Struts Creation date: 06-04-2008
 * 
 * XDoclet definition:
 * 
 * @struts.action path="/departmentalexams" name="departmentalexamsForm"
 *                input="/form/departmentalexams.jsp" scope="request"
 *                validate="true"
 */
/**
 * @author jagadish Jun 4, 2008
 * 
 */

public class DepartmentalexamsAction extends BaseAction {
	/*
	 * Generated Methods
	 */
	private DepartmentalExamsDTO deptExamDTO = null;

	private DepartmentalExamsResultDTO examResultDTO = null;

	private DepartmentalExamsBD deptExamBD;

	private EmpMgmtRule empMgmtRule = null;

	private ArrayList errorList = null;

	/**
	 * Method execute
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,HttpSession session) {
	//	HttpSession session = request.getSession(true);
		DepartmentalexamsForm departmentalexamsForm = (DepartmentalexamsForm) form;
		String pageName = departmentalexamsForm.getPageName();
		String pageAction = departmentalexamsForm.getPageAction();

		String FORWARD_PAGE = "";

		if (pageName != null && pageName.equalsIgnoreCase("DepartmentalExams")) {
			if (pageAction != null
					&& pageAction.equalsIgnoreCase("entryDepartmentalExams")) {
				String entryDepartmentalExamStr = entryDepartmentalExam(
						mapping, departmentalexamsForm, request, response,
						session);

				FORWARD_PAGE = entryDepartmentalExamStr;
			} else if (pageAction != null
					&& pageAction.equalsIgnoreCase("displayDepartmentalExams")) {
				String viewDepartmentalExamStr = viewDepartmentalExam(mapping,
						departmentalexamsForm, request, response, session);

				FORWARD_PAGE = viewDepartmentalExamStr;

			} else if (pageAction != null
					&& pageAction.equalsIgnoreCase("submitDepartmentalExams")) {
				String submitDepartmentalExamsStr = submitDepartmentalExam(
						mapping, departmentalexamsForm, request, response,
						session);

				FORWARD_PAGE = submitDepartmentalExamsStr;

			} else if (pageAction != null
					&& pageAction.equalsIgnoreCase("editDepartmentalExams")) {
				String editDepartmentalExamStr = editDepartmentalExam(mapping,
						departmentalexamsForm, request, response, session);

				FORWARD_PAGE = editDepartmentalExamStr;

			}
		}

		return mapping.findForward(FORWARD_PAGE);
	}

	/**
	 * @param mapping
	 * @param departmentalexamsForm
	 * @param request
	 * @param response
	 * @param session
	 * @return String
	 */
	private String entryDepartmentalExam(ActionMapping mapping,
			DepartmentalexamsForm departmentalexamsForm,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		String FORWARD_PAGE = null;

		try {

			deptExamDTO = new DepartmentalExamsDTO();
			deptExamDTO = departmentalexamsForm.getDeptExamDTO();
			examResultDTO = new DepartmentalExamsResultDTO();
			session.setAttribute("deptExam", deptExamDTO);
			FORWARD_PAGE = "entryDepartmentalExams";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return FORWARD_PAGE;
	}

	/**
	 * @param mapping
	 * @param departmentalexamsForm
	 * @param request
	 * @param response
	 * @param session
	 * @return String
	 */
	private String editDepartmentalExam(ActionMapping mapping,
			DepartmentalexamsForm departmentalexamsForm,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		String FORWARD_PAGE = null;
		ArrayList examResultList = null;
		try {
			if (session.getAttribute("deptExam") != null) {
				deptExamDTO = (DepartmentalExamsDTO) session
						.getAttribute("deptExam");
				session.setAttribute("deptExam", deptExamDTO);
			}
			if (session.getAttribute("examResultList") != null) {
				examResultList = (ArrayList) session
						.getAttribute("examResultList");
				session.setAttribute("examResultList", examResultList);
			}
			departmentalexamsForm.setDeptexamList(examResultList);
			FORWARD_PAGE = "entryDepartmentalExams";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return FORWARD_PAGE;
	}

	/**
	 * @param mapping
	 * @param departmentalexamsForm
	 * @param request
	 * @param response
	 * @param session
	 * @return String
	 */
	private String viewDepartmentalExam(ActionMapping mapping,
			DepartmentalexamsForm departmentalexamsForm,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		String FORWARD_PAGE = null;

		ArrayList examResultList = null;
		try {

			deptExamBD = new DepartmentalExamsBD();
			empMgmtRule = new EmpMgmtRule();
			deptExamDTO = departmentalexamsForm.getDeptExamDTO();
			examResultList = departmentalexamsForm.getDeptexamList();
			errorList = empMgmtRule.validateDepartmentalExam(deptExamDTO,
					examResultList);
			if (empMgmtRule.isError()) {
				request.setAttribute(EmpmgmtConstant.EMP_MGMT_ERROR_FLAG,
						Boolean.TRUE);
				request.setAttribute(EmpmgmtConstant.EMP_MGMT_ERROR_LIST,
						errorList);
				request.setAttribute("deptExam", deptExamDTO);
				session.setAttribute("examResultList", examResultList);
				deptExamDTO = departmentalexamsForm.getDeptExamDTO();
				FORWARD_PAGE = "entryDepartmentalExams";
			} else {
				session.setAttribute("deptExam", deptExamDTO);
				DepartmentalExamsResultDTO resultDTO = null;
				int iSize = examResultList.size();
				ArrayList lsNew = new ArrayList();

				for (int i = 0; i < iSize; i++) {
					resultDTO = (DepartmentalExamsResultDTO) examResultList
							.get(i);

					if (resultDTO.getEmpid() == null
							|| resultDTO.getEmpid().equals("")) {

					} else {
						lsNew.add(resultDTO);
					}

				}

				session.setAttribute("examResultList", lsNew);

				FORWARD_PAGE = "displayDepartmentalExams";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return FORWARD_PAGE;

	}

	/**
	 * @param mapping
	 * @param departmentalexamsForm
	 * @param request
	 * @param response
	 * @param session
	 * @return String
	 */
	private String submitDepartmentalExam(ActionMapping mapping,
			DepartmentalexamsForm departmentalexamsForm,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		String FORWARD_PAGE = null;
		boolean insert = false;
		ArrayList examList = null;
		try {

			deptExamBD = new DepartmentalExamsBD();

			if (session.getAttribute("deptExam") != null) {
				deptExamDTO = (DepartmentalExamsDTO) session
						.getAttribute("deptExam");
			}

			if (session.getAttribute("examResultList") != null) {
				examList = (ArrayList) session.getAttribute("examResultList");

				insert = deptExamBD.insertDepartmentalExamDetails(deptExamDTO,
						examList, session.getAttribute("UserId").toString());
			}

			FORWARD_PAGE = "submitDepartmentalExams";

		} catch (Exception e) {
			e.printStackTrace();
		}
		return FORWARD_PAGE;
	}

}