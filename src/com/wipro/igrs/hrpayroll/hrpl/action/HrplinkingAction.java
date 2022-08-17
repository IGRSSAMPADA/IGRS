/**
 * HrplinkingAction.java
 * 
 */

package com.wipro.igrs.hrpayroll.hrpl.action;

import java.util.ArrayList;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.common.HrpayrollConstant;
import com.wipro.igrs.hrpayroll.hrpl.bd.HrplinkingBD;
import com.wipro.igrs.hrpayroll.hrpl.dto.ArrearsDTO;
import com.wipro.igrs.hrpayroll.hrpl.dto.AttendanceReportDTO;
import com.wipro.igrs.hrpayroll.hrpl.dto.LeaveDTO;
import com.wipro.igrs.hrpayroll.hrpl.dto.MappingDTO;
import com.wipro.igrs.hrpayroll.hrpl.dto.PenalityDTO;
import com.wipro.igrs.hrpayroll.hrpl.form.HrplinkingForm;
import com.wipro.igrs.hrpayroll.hrpl.rule.HrplinkingRule;

/**
 * @author admin
 * 
 */
public class HrplinkingAction extends BaseAction {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,HttpSession session)
			throws Exception {
		HrplinkingForm hrForm = null;
		hrForm = (HrplinkingForm) form;
	//	HttpSession session = request.getSession(true);
		String actionPage = request.getParameter("name");

		String FORWARD_PAGE = "";

		LeaveDTO leaveDTO = null;
		LeaveDTO leaveTypeDTO = null;
		ArrayList leavelist = null;
		ArrayList leaveTypelist = null;
		ArrayList attendancelist = null;
		ArrearsDTO arrearsDTOsession = null;
		PenalityDTO penalityDTOsession = null;
		LeaveDTO _leaveDTO = null;
		AttendanceReportDTO attendanceDto = null;
		HrplinkingRule rule = null;
		ArrayList attendance_list = null;
		ArrayList errorList = null;
		MappingDTO mappingDTO = null;
		ArrayList leavegradecadrelist = null;
		ArrayList gradelist = null;
		ArrayList cadrelist = null;
		ArrayList leaveDetailList = null;
		HrplinkingBD payrollBD = null;

		try {
			payrollBD = new HrplinkingBD();

			/**
			 * Attendance -search attendance details and forward to edit page
			 */

			if (actionPage != null
					&& actionPage.equalsIgnoreCase("attendanceedit")) {
				String attendancePayrollLinkingStr = attendancePayrollLinkingAction(
						form, request, session, rule, payrollBD, errorList,
						attendancelist, attendanceDto);
				return mapping.findForward(attendancePayrollLinkingStr);

			}
			/**
			 * Attendance -edit attendance and submit
			 */

			if (HrpayrollConstant.HR_PAYROLL_ATTENDANCE_02
					.equalsIgnoreCase(hrForm.getAttendanceReportDTO()
							.getAttendanceForm())) {
				String AttendanceListStr = AttendanceListAction(form, rule,
						request, session, payrollBD, attendance_list, errorList);
				return mapping.findForward(AttendanceListStr);

			}
			/**
			 * Arrears-Searching Employee Id
			 */
			try {
				if (HrpayrollConstant.HR_PAYROLL_ARREARS
						.equalsIgnoreCase(hrForm.getArrearsDTO()
								.getArrearForm())) {

					FORWARD_PAGE = arrearsAction(mapping, form, request,
							response, payrollBD, errorList);
				}
			} catch (Exception e) {
				request
						.setAttribute(HrpayrollConstant.HR_PAYROLL_ERROR_FLAG,
								e);
				FORWARD_PAGE = "Error";
			}

			/**
			 * Arrears-Editing Arrears Details
			 */

			if (HrpayrollConstant.HR_PAYROLL_ARREARS_EDIT
					.equalsIgnoreCase(hrForm.getArrearsDTO().getArrearForm())) {
				String arrearsEditStr = arrearsEditAction(form, request, rule,
						session, errorList, arrearsDTOsession);
				return mapping.findForward(arrearsEditStr);

			}

			/**
			 * Arrears-saving Arrears Details
			 */

			if (HrpayrollConstant.HR_PAYROLL_ARREARS_SAVE
					.equalsIgnoreCase(hrForm.getArrearsDTO().getArrearForm())) {
				String arrearsSaveStr = arrearsSaveAction(request, session,
						rule, payrollBD, errorList);
				return mapping.findForward(arrearsSaveStr);

			}
			/**
			 * Penalty-Searching Employee Id
			 */

			if (HrpayrollConstant.HR_PAYROLL_PENALITY.equalsIgnoreCase(hrForm
					.getPenalityDTO().getPenalityForm())) {
				String penaltyEmpLinkingStr = penaltyEmpLinkingAction(form,
						request, rule, payrollBD, errorList);
				return mapping.findForward(penaltyEmpLinkingStr);
			}
			/**
			 * Penalty-Editing penalty Details
			 */

			if (HrpayrollConstant.HR_PAYROLL_PENALITY_EDIT
					.equalsIgnoreCase(hrForm.getPenalityDTO().getPenalityForm())) {
				String penaltyEditStr = penaltyEditAction(form, request,
						session, rule, errorList, penalityDTOsession);
				return mapping.findForward(penaltyEditStr);
			}

			/**
			 * Penalty-saving penalty details
			 */
			if (HrpayrollConstant.HR_PAYROLL_PENALITY_SAVE
					.equalsIgnoreCase(hrForm.getPenalityDTO().getPenalityForm())) {
				String penaltySaveStr = penaltySaveAction(request, session,
						payrollBD, errorList, rule);
				return mapping.findForward(penaltySaveStr);

			}
			/**
			 * HR Pay Roll Linking Leave Approval
			 * 
			 */

			/**
			 * View The leaves based on status-Pending leaves
			 */

			if (actionPage != null
					&& actionPage.equalsIgnoreCase("view_applied_leave")) {
				String viewAppliedLeaveStr = viewAppliedLeaveAction(request,
						session, rule, payrollBD, errorList, leavelist);
				return mapping.findForward(viewAppliedLeaveStr);
			}

			/**
			 * Leave Approval -view leaves detalis on transaction id -forwarding
			 * to approval page
			 */

			if (HrpayrollConstant.HR_PAYROLL_LEAVE.equalsIgnoreCase(actionPage)) {
				String linkingLeaveApprovalStr = linkingLeaveApprovalAction(
						form, request, session, rule, payrollBD, leaveDTO,
						leaveTypelist, leavelist, errorList);
				return mapping.findForward(linkingLeaveApprovalStr);
			}

			/**
			 * Leave Approval- forwading to the edit page -view the leave
			 * datails
			 */

			if (actionPage != null
					&& actionPage.equalsIgnoreCase("saveleavestatus")) {
				String saveLeaveStr = saveLeaveAction(form, request, session,
						rule, _leaveDTO, errorList, leaveTypelist);
				return mapping.findForward(saveLeaveStr);
			}
			/**
			 * Leave Approval -Submit Leave Details-upadte the status to the
			 * database
			 * 
			 */

			if (actionPage != null && actionPage.equalsIgnoreCase("confirm")) {
				String leaveConfirmStr = leaveConfirmAction(request, session,
						rule, payrollBD, leaveDTO, _leaveDTO, errorList);
				return mapping.findForward(leaveConfirmStr);
			}

			/**
			 * View the available and Balanaced leave based on employee Id
			 */

			if (actionPage != null
					&& actionPage.equalsIgnoreCase("Leavestatus")) {
				String leaveStatusForEmpStr = leaveStatusForEmpAction(form,
						request, payrollBD, leaveDTO);
				return mapping.findForward(leaveStatusForEmpStr);
			}

			/**
			 * * Leave Application -Applying for leave
			 * 
			 */

			/**
			 * Leave Application -Start Here -Loading leave applicable to the
			 * employee
			 */

			if (actionPage != null
					&& actionPage
							.equalsIgnoreCase(HrpayrollConstant.HR_PAYROLL_LEAVE_DETAILS_LIST)) {

				String leaveApplicationStr = leaveDetailsAction(request,
						session, rule, payrollBD, errorList, leavelist);
				return mapping.findForward(leaveApplicationStr);

			}
			/**
			 * Leave Application -forwading to the edit page -view the leave
			 * status
			 */

			if (actionPage != null
					&& actionPage
							.equalsIgnoreCase(HrpayrollConstant.HR_PAYROLL_LEAVE_DETAILS)) {

				String leaveStatusStr = leaveStatusAction(form, request,
						session, rule, errorList, leaveDTO);
				return mapping.findForward(leaveStatusStr);
			}

			/**
			 * Leave Application -forwading to the edit page -applyleave page
			 */
			if (actionPage != null
					&& actionPage
							.equals(HrpayrollConstant.HR_PAYROLL_LEAVE_APPLICATION_EDIT)) {

				String leaveApplicationEditStr = leaveApplicationEditAction(
						request, session);
				return mapping.findForward(leaveApplicationEditStr);

			}

			/**
			 * Leave Application -submit the leave to the Data Base
			 */

			if (actionPage != null
					&& actionPage
							.equals(HrpayrollConstant.HR_PAYROLL_LEAVE_APPLICATION_SUBMIT)) {

				String leaveApplicationSubmitStr = leaveApplicationSubmitAction(
						form, request, session, payrollBD, leaveDTO,leaveDTO,leavelist);
				return mapping.findForward(leaveApplicationSubmitStr);
			}

			/**
			 * Avaliable Leaves
			 */

			if (actionPage != null && actionPage.equals("leaveavail")) {
				String availableLeaveStr = availableLeaveAction(form, request,
						session, payrollBD, _leaveDTO, leaveDTO);
				return mapping.findForward(availableLeaveStr);
			}

			/**
			 * Leave Grade Cadre Mapping-Getting Grade
			 */

			if (actionPage != null
					&& actionPage
							.equalsIgnoreCase(HrpayrollConstant.HR_PAYROLL_LEAVE_EMP_MAP)) {
				String gradeCadreMappingStr = gradeCadreMappingAction(request,
						session, rule, payrollBD, errorList, gradelist);
				return mapping.findForward(gradeCadreMappingStr);
			}

			/**
			 * Leave Grade Cadre Mapping-Cadre Lov -getting cadre based on grade
			 * id
			 */

			if (actionPage != null && actionPage.equals("CadreLOV")) {
				String cadreLovStr = cadreLovAction(form, request, session,
						payrollBD, mappingDTO, cadrelist);
				return mapping.findForward(cadreLovStr);

			}

			/**
			 * Leave Grade Cadre Mapping-retriving leaves for that gradeid and
			 * cadreid form mapping
			 */
			if (HrpayrollConstant.HR_PAYROLL_MAPPING_LEAVE
					.equalsIgnoreCase(hrForm.getMappingDTO().getMappingform())) {
				if (actionPage != null
						&& actionPage
								.equalsIgnoreCase(HrpayrollConstant.HR_PAYROLL_MAPPING_LEAVE1)) {
					String mappingLeaveLovStr = mappingLeaveLovAction(form,
							request, session, rule, payrollBD, mappingDTO,
							leavegradecadrelist, errorList);
					return mapping.findForward(mappingLeaveLovStr);
				}
			}

			/**
			 * Leave Grade Cadre Mapping-retriving leave type
			 */
			if (HrpayrollConstant.HR_PAYROLL_MAPPING_LEAVE
					.equalsIgnoreCase(hrForm.getMappingDTO().getMappingform())) {
				if (actionPage != null
						&& actionPage
								.equalsIgnoreCase(HrpayrollConstant.HR_PAYROLL_LEAVE_EMP_MAP01)) {
					String mappingLeaveEmpMapStr = mappingLeaveEmpMapAction(
							form, request, session, payrollBD, mappingDTO,
							leavegradecadrelist, leavelist);
					return mapping.findForward(mappingLeaveEmpMapStr);
				}
			}

			/**
			 * Leave Grade Cadre Mapping-Updating leave grade cadre mapping
			 */

			if (actionPage != null
					&& actionPage.equalsIgnoreCase("updatemapping")) {
				String updateMappingStr = updateMappingAction(form, leavelist,
						rule, mappingDTO, errorList, payrollBD, session,
						request, leavegradecadrelist);
				return mapping.findForward(updateMappingStr);
			}
			/**
			 * Leave Grade Cadre Mapping-submiting leave grade cadre
			 */
			if (actionPage != null && actionPage.equals("save")) {
				String leaveSaveStr = leaveSaveAction(form, request, payrollBD,
						mappingDTO, leavelist);
				return mapping.findForward(leaveSaveStr);
			}

			if (actionPage != null && actionPage.equals("loadleave")) {
				session.removeAttribute("leavegradecadrelist");
				FORWARD_PAGE = "leave_grade_cadre";
			}

		}

		catch (Exception e) {
			FORWARD_PAGE = "error";
		}

		//System.out.println("FORWARD_PAGE"+FORWARD_PAGE);
		return mapping.findForward(FORWARD_PAGE);
	}

	// ==================Private Method ===============

	// ===================Attendance Payroll Linking================
	/**
	 * @param form
	 * @param request
	 * @param session
	 * @param rule
	 * @param payrollBD
	 * @param errorList
	 * @param attendancelist
	 * @param attendanceDto
	 * @return
	 * @throws Exception
	 */
	private String attendancePayrollLinkingAction(ActionForm form,
			HttpServletRequest request, HttpSession session,
			HrplinkingRule rule, HrplinkingBD payrollBD, ArrayList errorList,
			ArrayList attendancelist, AttendanceReportDTO attendanceDto)
			throws Exception {

		HrplinkingForm hrForm = null;
		hrForm = (HrplinkingForm) form;
		String FORWARD_PAGE = null;
		
		if (hrForm != null) {
		
			attendanceDto = hrForm.getAttendanceReportDTO();
			rule = new HrplinkingRule();
			errorList = rule.validateAttendanceRule(attendanceDto);

			if (rule.isError()) {

				request.setAttribute(HrpayrollConstant.HR_PAYROLL_ERROR_FLAG,
						"true");
				request.setAttribute(HrpayrollConstant.HR_PAYROLL_ERROR_LIST,
						errorList);
				FORWARD_PAGE = "attendance_form";
			} else {

				attendancelist = payrollBD
						.submitAttendanceReport(attendanceDto);


				if (attendancelist!=null && attendancelist.size() > 0) {

					request.setAttribute(
							HrpayrollConstant.HR_PAYROLL_ERROR_FLAG, "false");
					request.setAttribute(
							HrpayrollConstant.HR_PAYROLL_ERROR_LIST, null);
					session.setAttribute("attendanceDisplay", attendancelist);
					session.setAttribute("attendanceDto", attendanceDto);
					FORWARD_PAGE = "attendance_report";
				} else {

					session.removeAttribute("attendanceDisplay");
					session.removeAttribute("attendanceDto");
					request
							.setAttribute("noResultsToDisplay",
									"No Records Found");

					FORWARD_PAGE = "attendance_form";
				}
			}
		}
		return FORWARD_PAGE;
	}

	// =====================Attendance List==============
	/**
	 * @param form
	 * @param rule
	 * @param request
	 * @param session
	 * @param payrollBD
	 * @param attendance_list
	 * @param errorList
	 * @return
	 * @throws Exception
	 */
	private String AttendanceListAction(ActionForm form, HrplinkingRule rule,
			HttpServletRequest request, HttpSession session,
			HrplinkingBD payrollBD, ArrayList attendance_list,
			ArrayList errorList) throws Exception {

		String FORWARD_PAGE = null;
		HrplinkingForm hrForm = null;

		hrForm = (HrplinkingForm) form;
		rule = new HrplinkingRule();
		attendance_list = hrForm.getAttendanceList();

		// AttendanceReportDTO attendanceDto = null;
		// rule.validateAttendanceRule(attendanceDto);
		String strUserId = (String) session.getAttribute("UserId");
		boolean result = payrollBD.updateAttendanceReport(attendance_list,
				strUserId);

		if (result == true) {
			request.setAttribute(HrpayrollConstant.HR_PAYROLL_ERROR_FLAG,
					"false");
			request.setAttribute(HrpayrollConstant.HR_PAYROLL_ERROR_LIST, null);
			session.removeAttribute("attendanceDisplay");
			session.removeAttribute("attendanceDto");
			FORWARD_PAGE = "submit_attendance";
		} else {
			errorList = rule.validate(result); // errorList.add("Nothing to
			// Update");

			request.setAttribute(HrpayrollConstant.HR_PAYROLL_ERROR_FLAG,
					"true");
			request.setAttribute(HrpayrollConstant.HR_PAYROLL_ERROR_LIST,
					errorList);
			FORWARD_PAGE = "attendance_form";
		}

		return FORWARD_PAGE;
	}

	// ===============Penalty In Hr Employee Linking=============
	/**
	 * @param form
	 * @param request
	 * @param rule
	 * @param payrollBD
	 * @param errorList
	 * @return
	 */
	private String penaltyEmpLinkingAction(ActionForm form,
			HttpServletRequest request, HrplinkingRule rule,
			HrplinkingBD payrollBD, ArrayList errorList) {
		HrplinkingForm hrForm = null;
		hrForm = (HrplinkingForm) form;
		String FORWARD = "";
		try {
			if (hrForm != null) {
				rule = new HrplinkingRule();
				PenalityDTO penalityDTO = hrForm.getPenalityDTO();
				errorList = rule.validatePenalityDTORule(penalityDTO);
				if (rule.isError()) {
					request.setAttribute(
							HrpayrollConstant.HR_PAYROLL_ERROR_FLAG, "true");
					request.setAttribute(
							HrpayrollConstant.HR_PAYROLL_ERROR_LIST, errorList);
					FORWARD = "penality_error";
				} else {
					boolean result = payrollBD.getPenality_Empid(penalityDTO);
					if (result) {
						request.setAttribute(
								HrpayrollConstant.HR_PAYROLL_ERROR_FLAG,
								"false");
						request.setAttribute(
								HrpayrollConstant.HR_PAYROLL_ERROR_LIST, null);
						FORWARD = "penality_edit";
					} else {
						errorList = rule.validateEmpID(result);
						request
								.setAttribute(
										HrpayrollConstant.HR_PAYROLL_ERROR_FLAG,
										"true");
						request.setAttribute(
								HrpayrollConstant.HR_PAYROLL_ERROR_LIST,
								errorList);

						FORWARD = "penality_error";
					}
				}
			}
		}

		catch (Exception e) {
			request.setAttribute(HrpayrollConstant.HR_PAYROLL_ERROR_FLAG, e);
			FORWARD = "Error";
		}
		return FORWARD;
	}

	// ==================Penalty Edit================
	/**
	 * @param form
	 * @param request
	 * @param session
	 * @param rule
	 * @param errorList
	 * @param penalityDTOsession
	 * @return
	 */
	private String penaltyEditAction(ActionForm form,
			HttpServletRequest request, HttpSession session,
			HrplinkingRule rule, ArrayList errorList,
			PenalityDTO penalityDTOsession) {
		String FORWARD_PAGE = null;
		HrplinkingForm hrForm = null;
		hrForm = (HrplinkingForm) form;
		if (hrForm != null) {
			try {
				rule = new HrplinkingRule();
				penalityDTOsession = hrForm.getPenalityDTO();
				errorList = rule
						.validatePenalityDTOSessionRule(penalityDTOsession);
				if (rule.isError()) {
					request.setAttribute(
							HrpayrollConstant.HR_PAYROLL_ERROR_FLAG, "true");
					request.setAttribute(
							HrpayrollConstant.HR_PAYROLL_ERROR_LIST, errorList);

					FORWARD_PAGE = "penality_edit";
				} else {
					request.setAttribute(
							HrpayrollConstant.HR_PAYROLL_ERROR_FLAG, "false");
					request.setAttribute(
							HrpayrollConstant.HR_PAYROLL_ERROR_LIST, null);
					session.setAttribute("penalitydto", penalityDTOsession);
					FORWARD_PAGE = "penality_display";
				}
			} catch (Exception e) {
				request
						.setAttribute(HrpayrollConstant.HR_PAYROLL_ERROR_FLAG,
								e);
				FORWARD_PAGE = "Error";
			}
		}
		return FORWARD_PAGE;

	}

	// ===================Penalty Save================
	/**
	 * @param request
	 * @param session
	 * @param payrollBD
	 * @param errorList
	 * @param rule
	 * @return
	 */
	private String penaltySaveAction(HttpServletRequest request,
			HttpSession session, HrplinkingBD payrollBD, ArrayList errorList,
			HrplinkingRule rule) {
		String FORWARD_PAGE = null;
		try {

			PenalityDTO dto = (PenalityDTO) session.getAttribute("penalitydto");
			String strUserId = (String) session.getAttribute("UserId");
			boolean result = payrollBD.submitPenality(dto, strUserId);
			if (result == true) {
				request.setAttribute(HrpayrollConstant.HR_PAYROLL_ERROR_FLAG,
						"false");
				request.setAttribute(HrpayrollConstant.HR_PAYROLL_ERROR_LIST,
						null);
				session.removeAttribute("penalitydto");
				FORWARD_PAGE = "submit_penality";
			} else {
				errorList = rule.validate(result);
				request.setAttribute(HrpayrollConstant.HR_PAYROLL_ERROR_FLAG,
						"true");
				request.setAttribute(HrpayrollConstant.HR_PAYROLL_ERROR_LIST,
						errorList);

				FORWARD_PAGE = "penality_edit";
			}
		} catch (Exception e) {
			request.setAttribute(HrpayrollConstant.HR_PAYROLL_ERROR_FLAG, e);
			FORWARD_PAGE = "Error";
		}
		return FORWARD_PAGE;
	}

	// ========================Hr Payroll Linking Leave
	// Approval====================
	/**
	 * @param form
	 * @param request
	 * @param session
	 * @param rule
	 * @param payrollBD
	 * @param leaveDTO
	 * @param leaveTypelist
	 * @param leavelist
	 * @param errorList
	 * @return
	 * @throws Exception
	 */
	private String linkingLeaveApprovalAction(ActionForm form,
			HttpServletRequest request, HttpSession session,
			HrplinkingRule rule, HrplinkingBD payrollBD, LeaveDTO leaveDTO,
			ArrayList leaveTypelist, ArrayList leavelist, ArrayList errorList)
			throws Exception {

		rule = new HrplinkingRule();
		String FORWARD_PAGE = null;
		String transactionid = request.getParameter("tran");
		session.removeAttribute("leaveDisplay");

		if (transactionid != null) {
			leavelist = payrollBD.getPendingLeavesonId(transactionid);

			if (leavelist.size() > 0) {

				leaveDTO = (LeaveDTO) leavelist.get(0);
				leaveTypelist = payrollBD.getLeavesType(leaveDTO);
				session.setAttribute("leaveDisplay", leavelist);
				session.setAttribute("leaveDTO", leaveDTO);

				if (leaveTypelist.size() > 0) {

					session.setAttribute("leavetypeLOV", leaveTypelist);
				}
				FORWARD_PAGE = "approval";
			} else {
				errorList = rule.validateLeave(leavelist);
				request.setAttribute(HrpayrollConstant.HR_PAYROLL_ERROR_FLAG,
						"true");
				request.setAttribute(HrpayrollConstant.HR_PAYROLL_ERROR_LIST,
						errorList);
				FORWARD_PAGE = "approval";

			}
		}
		return FORWARD_PAGE;
	}

	// =======================View Applied Leave========================
	/**
	 * @param request
	 * @param session
	 * @param rule
	 * @param payrollBD
	 * @param errorList
	 * @param leavelist
	 * @return
	 * @throws Exception
	 */
	private String viewAppliedLeaveAction(HttpServletRequest request,
			HttpSession session, HrplinkingRule rule, HrplinkingBD payrollBD,
			ArrayList errorList, ArrayList leavelist) throws Exception {

		String FORWARD_PAGE = null;
		rule = new HrplinkingRule();
		leavelist = payrollBD.displayLeaves();

		if (leavelist != null) {

			errorList = rule.validateLeave(leavelist);

			request.setAttribute(HrpayrollConstant.HR_PAYROLL_ERROR_FLAG,
					"false");
			request.setAttribute(HrpayrollConstant.HR_PAYROLL_ERROR_LIST, null);
			session.setAttribute("leaveDisplay", leavelist);
			FORWARD_PAGE = "LeaveApproval";
		} else {
			errorList = rule.validateLeave(leavelist);
			request.setAttribute(HrpayrollConstant.HR_PAYROLL_ERROR_FLAG,
					"true");
			request.setAttribute(HrpayrollConstant.HR_PAYROLL_ERROR_LIST,
					errorList);
			FORWARD_PAGE = "LeaveApproval";
		}
		return FORWARD_PAGE;
	}

	// =============================Save Leave Status========================
	/**
	 * @param form
	 * @param request
	 * @param session
	 * @param rule
	 * @param _leaveDTO
	 * @param errorList
	 * @param leaveTypelist
	 * @return
	 */
	private String saveLeaveAction(ActionForm form, HttpServletRequest request,
			HttpSession session, HrplinkingRule rule, LeaveDTO _leaveDTO,
			ArrayList errorList, ArrayList leaveTypelist) {

		String FORWARD_PAGE = null;
		HrplinkingForm hrForm = null;
		hrForm = (HrplinkingForm) form;
		if (hrForm != null) {
			rule = new HrplinkingRule();
			// leaveDTO = (LeaveDTO)session.getAttribute("leaveDTO");
			_leaveDTO = hrForm.getLeaveDTO();
			errorList = rule.validateLeaveDTO(_leaveDTO);
			if (rule.isError()) {
				request.setAttribute(HrpayrollConstant.HR_PAYROLL_ERROR_FLAG,
						"false");
				request.setAttribute(HrpayrollConstant.HR_PAYROLL_ERROR_LIST,
						null);
				session.setAttribute("leavedtolist", _leaveDTO);
				session.setAttribute("leavetypeLOV", leaveTypelist);
				FORWARD_PAGE = "leave_approval_save";
			} else {
				session.removeAttribute("leaveDTO");
				request.setAttribute(HrpayrollConstant.HR_PAYROLL_ERROR_FLAG,
						"true");
				request.setAttribute(HrpayrollConstant.HR_PAYROLL_ERROR_LIST,
						errorList);

				FORWARD_PAGE = "leave_approval_save";
			}

		}
		return FORWARD_PAGE;
	}

	// ===================Leave Confirm==================

	/**
	 * @param request
	 * @param session
	 * @param rule
	 * @param payrollBD
	 * @param leaveDTO
	 * @param _leaveDTO
	 * @param errorList
	 * @return
	 * @throws Exception
	 */
	private String leaveConfirmAction(HttpServletRequest request,
			HttpSession session, HrplinkingRule rule, HrplinkingBD payrollBD,
			LeaveDTO leaveDTO, LeaveDTO _leaveDTO, ArrayList errorList)
			throws Exception {

		boolean result = false;
		String FORWARD_PAGE = null;
		String strUserId = (String) session.getAttribute("UserId");
		leaveDTO = (LeaveDTO) session.getAttribute("leaveDTO");
		_leaveDTO = (LeaveDTO) session.getAttribute("leavedtolist");
		rule = new HrplinkingRule();
		if (leaveDTO != null && _leaveDTO != null) {
			result = payrollBD
					.updateLeaveStatus(_leaveDTO, leaveDTO, strUserId);
		}
		if (result == true) {
			request.setAttribute(HrpayrollConstant.HR_PAYROLL_ERROR_FLAG,
					"false");
			request.setAttribute(HrpayrollConstant.HR_PAYROLL_ERROR_LIST, null);
			FORWARD_PAGE = "leave_confirm";
		} else {
			errorList = rule.validate(result);
			request.setAttribute(HrpayrollConstant.HR_PAYROLL_ERROR_FLAG,
					"true");
			request.setAttribute(HrpayrollConstant.HR_PAYROLL_ERROR_LIST,
					errorList);
			FORWARD_PAGE = "leave_approval_save";
		}
		return FORWARD_PAGE;
	}

	// ================Leave Status=====================
	/**
	 * @param form
	 * @param request
	 * @param payrollBD
	 * @param leaveDTO
	 * @return
	 * @throws Exception
	 */
	private String leaveStatusForEmpAction(ActionForm form,
			HttpServletRequest request, HrplinkingBD payrollBD,
			LeaveDTO leaveDTO) throws Exception {

		String FORWARD_PAGE = null;
		HrplinkingForm hrForm = null;
		hrForm = (HrplinkingForm) form;
		leaveDTO = hrForm.getLeaveDTO();
		String empid = request.getParameter("empid");

		if (empid != null) {
			leaveDTO.setEmpid(empid);
		}
		// ArrayList empleavelist=payrollBD.displayLeaveEmpStatus(leaveDTO);

		ArrayList empleaveReport = payrollBD
				.displayLeaveEmpLeaveReport(leaveDTO);

		// ArrayList leaveFinancialYear=payrollBD.getFinancialYear();
		int availableleave = 0;
		int usedleave = 0;
		for (int i = 0; i < empleaveReport.size(); i++) {
			leaveDTO = (LeaveDTO) empleaveReport.get(i);
			String avail = leaveDTO.getLeaveAvail();
			String used = leaveDTO.getLeaveUsed();
			availableleave += Integer.parseInt(avail);
			usedleave += Integer.parseInt(used);
		}
		if (availableleave > 0 && usedleave > 0) {
			request.setAttribute("available", new Integer(availableleave));
			request.setAttribute("used", new Integer(usedleave));
		}
		// request.setAttribute("EmpLeavelist", empleavelist);
		request.setAttribute("EmpLeaveReport", empleaveReport);
		request.setAttribute("leaveDTO", leaveDTO);
		// request.setAttribute("leaveFinancialyear",leaveFinancialYear);
		FORWARD_PAGE = "leave_emp_status";

		return FORWARD_PAGE;
	}

	// =======================Leave Type Grade Cadre Mapping==================
	/**
	 * @param request
	 * @param session
	 * @param rule
	 * @param payrollBD
	 * @param errorList
	 * @param gradelist
	 * @return
	 */
	private String gradeCadreMappingAction(HttpServletRequest request,
			HttpSession session, HrplinkingRule rule, HrplinkingBD payrollBD,
			ArrayList errorList, ArrayList gradelist) {
		session.removeAttribute("gradeList");
		session.removeAttribute("leavegradecadrelist");
		session.removeAttribute("cadrelist");
		session.removeAttribute("mappingDto");
		session.removeAttribute("leaveDisplay");
		String FORWARD_PAGE = null;
		try {
			gradelist = payrollBD.getAllGrades();
			rule = new HrplinkingRule();
			if (gradelist != null) {
				request.setAttribute(HrpayrollConstant.HR_PAYROLL_ERROR_FLAG,
						"false");
				request.setAttribute(HrpayrollConstant.HR_PAYROLL_ERROR_LIST,
						null);
				session.setAttribute("gradeList", gradelist);
				session.removeAttribute("leavegradecadrelist");
				FORWARD_PAGE = "leave_grade_cadre";
			} else {
				errorList = rule.validateList(gradelist);
				if (rule.isError()) {
					request.setAttribute(
							HrpayrollConstant.HR_PAYROLL_ERROR_FLAG, "true");
					request.setAttribute(
							HrpayrollConstant.HR_PAYROLL_ERROR_LIST, errorList);
				}
				FORWARD_PAGE = "leave_grade_cadre";
			}
		}

		catch (Exception e) {
			request.setAttribute(HrpayrollConstant.HR_PAYROLL_ERROR_FLAG, e);
			FORWARD_PAGE = "Error";
		}
		return FORWARD_PAGE;
	}

	// =======================Cadre LOV=================

	/**
	 * @param form
	 * @param request
	 * @param session
	 * @param payrollBD
	 * @param mappingDTO
	 * @param cadrelist
	 * @return
	 */
	private String cadreLovAction(ActionForm form, HttpServletRequest request,
			HttpSession session, HrplinkingBD payrollBD, MappingDTO mappingDTO,
			ArrayList cadrelist) {

		String FORWARD_PAGE = null;
		try {
			HrplinkingForm hrForm = null;
			hrForm = (HrplinkingForm) form;
			HrplinkingRule rule = null;
			rule = new HrplinkingRule();
			mappingDTO = hrForm.getMappingDTO();
			String gradeId = mappingDTO.getGradetypeid();
			session.removeAttribute("leavegradecadrelist");
			// gradelist=(ArrayList)session.getAttribute("gradeList");
			cadrelist = payrollBD.getAllCadres(gradeId);
			ArrayList errorList = null;
			if (cadrelist != null) {
				session.setAttribute("mappingDto", mappingDTO);
				session.setAttribute("cadrelist", cadrelist);
				errorList = rule.validateLeaveGradeCadre(cadrelist);
			//	System.out.println("=======errorList========" + errorList);
				if (rule.isError()) {
					request.setAttribute(
							HrpayrollConstant.HR_PAYROLL_ERROR_FLAG, "true");
					request.setAttribute(
							HrpayrollConstant.HR_PAYROLL_ERROR_LIST, errorList);
					FORWARD_PAGE = "leave_grade_cadre";
				} else {
					request.setAttribute(
							HrpayrollConstant.HR_PAYROLL_ERROR_FLAG, "false");
					request.setAttribute(
							HrpayrollConstant.HR_PAYROLL_ERROR_LIST, null);
					FORWARD_PAGE = "leave_grade_cadre";
				}
				FORWARD_PAGE = "leave_grade_cadre";
			}
		}

		catch (Exception e) {
			request.setAttribute(HrpayrollConstant.HR_PAYROLL_ERROR_FLAG, e);
			FORWARD_PAGE = "Error";
		}
		return FORWARD_PAGE;
	}

	// ===============Hr Payroll Mapping Leave1================
	/**
	 * @param form
	 * @param request
	 * @param session
	 * @param rule
	 * @param payrollBD
	 * @param mappingDTO
	 * @param leavegradecadrelist
	 * @param errorList
	 * @return
	 */
	private String mappingLeaveLovAction(ActionForm form,
			HttpServletRequest request, HttpSession session,
			HrplinkingRule rule, HrplinkingBD payrollBD, MappingDTO mappingDTO,
			ArrayList leavegradecadrelist, ArrayList errorList) {

		String FORWARD_PAGE = null;
		try {
			// session.removeAttribute("gradeList");
			// session.removeAttribute("leaveDisplay");
			// session.removeAttribute("mappingDto");

			HrplinkingForm hrForm = null;
			hrForm = (HrplinkingForm) form;
			rule = new HrplinkingRule();
			mappingDTO = hrForm.getMappingDTO();
			rule.vadidateMappingDTO(mappingDTO);
			//System.out.println("leave lov outside");
			if (rule.isError()) {
				//System.out.println("leave lov if condition");
				request.setAttribute(HrpayrollConstant.HR_PAYROLL_ERROR_FLAG,
						"true");
				request.setAttribute(HrpayrollConstant.HR_PAYROLL_ERROR_LIST,
						errorList);
				session.removeAttribute("leavegradecadrelist");
				FORWARD_PAGE = "leave_grade_cadre";
			} else {
				//System.out.println("leave lov in else condition");
				leavegradecadrelist = payrollBD.getLeaveGradeCadre(mappingDTO);

				request.setAttribute(HrpayrollConstant.HR_PAYROLL_ERROR_FLAG,
						"false");
				request.setAttribute(HrpayrollConstant.HR_PAYROLL_ERROR_LIST,
						null);

				if (leavegradecadrelist.size() > 0) {
					session.setAttribute("leavegradecadrelist",
							leavegradecadrelist);
				}
				session.setAttribute("mappingDto", mappingDTO);
				FORWARD_PAGE = "leave_grade_cadre";
			}
		}

		catch (Exception e) {
			request.setAttribute(HrpayrollConstant.HR_PAYROLL_ERROR_FLAG, e);
			FORWARD_PAGE = "Error";
		}
		return FORWARD_PAGE;
	}

	// ===================Hr Payroll Mapping Leave Emp_Map===============

	/**
	 * @param form
	 * @param request
	 * @param session
	 * @param payrollBD
	 * @param mappingDTO
	 * @param leavegradecadrelist
	 * @param leavelist
	 * @return
	 */
	private String mappingLeaveEmpMapAction(ActionForm form,
			HttpServletRequest request, HttpSession session,
			HrplinkingBD payrollBD, MappingDTO mappingDTO,
			ArrayList leavegradecadrelist, ArrayList leavelist) {
		String FORWARD_PAGE = null;
		try {
			// session.removeAttribute("gradeList");
			session.removeAttribute("leaveDisplay");
			// session.removeAttribute("mappingDto");
			// session.removeAttribute("leavegradecadrelist");
			HrplinkingForm hrForm = null;
			hrForm = (HrplinkingForm) form;
			mappingDTO = hrForm.getMappingDTO();
			// gradelist=(ArrayList)session.getAttribute("gradeList");
			// cadrelist=(ArrayList)session.getAttribute("cadrelist");
			leavegradecadrelist = payrollBD.getLeaveGradeCadre(mappingDTO);
			leavelist = payrollBD.getAllLeavesCadreGrade(mappingDTO);
			if (leavegradecadrelist != null && leavelist != null) {
				request.setAttribute(HrpayrollConstant.HR_PAYROLL_ERROR_FLAG,
						"false");
				request.setAttribute(HrpayrollConstant.HR_PAYROLL_ERROR_LIST,
						null);
				if (leavegradecadrelist.size() > 0) {
					session.setAttribute("leavegradecadrelist",
							leavegradecadrelist);
				}
				if (leavelist.size() > 0) {
					session.setAttribute("leaveDisplay", leavelist);
				}
				session.setAttribute("mappingDto", mappingDTO);
				// session.removeAttribute("leavegradecadrelist");

				FORWARD_PAGE = "leave_grade_cadre_EDIT";
			}
		}

		catch (Exception e) {
			request.setAttribute(HrpayrollConstant.HR_PAYROLL_ERROR_FLAG, e);
			FORWARD_PAGE = "Error";
		}
		return FORWARD_PAGE;
	}

	// ==========================Update Mapping====================
	/**
	 * @param form
	 * @param leavelist
	 * @param rule
	 * @param mappingDTO
	 * @param errorList
	 * @param payrollBD
	 * @param session
	 * @param request
	 * @param leavegradecadrelist
	 * @return
	 * @throws Exception
	 */
	private String updateMappingAction(ActionForm form, ArrayList leavelist,
			HrplinkingRule rule, MappingDTO mappingDTO, ArrayList errorList,
			HrplinkingBD payrollBD, HttpSession session,
			HttpServletRequest request, ArrayList leavegradecadrelist)
			throws Exception {
		String FORWARD_PAGE = null;
		HrplinkingForm hrForm = null;
		hrForm = (HrplinkingForm) form;
		errorList = new ArrayList();
		rule = new HrplinkingRule();
		mappingDTO = hrForm.getMappingDTO();
		leavegradecadrelist = (ArrayList) session
				.getAttribute("leavegradecadrelist");

		boolean result = payrollBD.updateLeaveGradeCadreMapping(mappingDTO,
				leavegradecadrelist);
		if (result) {
			leavegradecadrelist = payrollBD.getLeaveGradeCadre(mappingDTO);
			leavelist = payrollBD.getAllLeavesCadreGrade(mappingDTO);
			session.setAttribute("leavegradecadrelist", leavegradecadrelist);
			session.setAttribute("leaveDisplay", leavelist);

			FORWARD_PAGE = "leave_grade_cadre";
		}
		if (result) {
			errorList = rule.validate(result);
			request.setAttribute(HrpayrollConstant.HR_PAYROLL_ERROR_FLAG,
					"true");
			request.setAttribute(HrpayrollConstant.HR_PAYROLL_ERROR_LIST,
					errorList);

			FORWARD_PAGE = "leave_grade_cadre";
		}
		return FORWARD_PAGE;
	}

	// =================================Save=======================
	/**
	 * @param form
	 * @param request
	 * @param payrollBD
	 * @param mappingDTO
	 * @param leavelist
	 * @return
	 */
	private String leaveSaveAction(ActionForm form, HttpServletRequest request,
			HrplinkingBD payrollBD, MappingDTO mappingDTO, ArrayList leavelist) {
		String FORWARD_PAGE = null;
		ServletRequest session = null;
		String strUserId = (String) session.getAttribute("UserId");
		try {
			HrplinkingForm hrForm = null;
			hrForm = (HrplinkingForm) form;
			mappingDTO = hrForm.getMappingDTO();

			boolean result = payrollBD.submitLeaveGradeCadre(mappingDTO,
					strUserId);
			if (result) {
				leavelist = payrollBD.getAllLeaves();
				FORWARD_PAGE = "leave_grade_cadre";
				session.removeAttribute("leaveDisplay");
			} else {
				FORWARD_PAGE = "leave_grade_cadre";
			}
		}

		catch (Exception e) {
			request.setAttribute(HrpayrollConstant.HR_PAYROLL_ERROR_FLAG, e);
			FORWARD_PAGE = "Error";
		}
		return FORWARD_PAGE;
	}

	// ===============Leave Details List ==================

	/**
	 * @param request
	 * @param session
	 * @param rule
	 * @param payrollBD
	 * @param leavelist
	 * @param errorList
	 * @return
	 */
	private String leaveDetailsAction(HttpServletRequest request,
			HttpSession session, HrplinkingRule rule, HrplinkingBD payrollBD,
			ArrayList leavelist, ArrayList errorList) {
		String FORWARD_PAGE = null;

		try {
			session.removeAttribute("leaveDisplay");
			session.removeAttribute("leaveDTO");
			session.removeAttribute("leavedtolist");
			session.removeAttribute("leaveavaillist");

			String empid = (String) session.getAttribute("UserId");// Session
			// EmployeeId
			leavelist = payrollBD.getAllLeavesonID(empid);

			rule = new HrplinkingRule();
			errorList = rule.validateList(leavelist);
			if (rule.isError()) {
				request.setAttribute(HrpayrollConstant.HR_PAYROLL_ERROR_FLAG,
						"true");
				request.setAttribute(HrpayrollConstant.HR_PAYROLL_ERROR_LIST,
						errorList);
				FORWARD_PAGE = "leave_application_form";
				session.removeAttribute("leaveDisplay");
			} else {
				request.setAttribute(HrpayrollConstant.HR_PAYROLL_ERROR_FLAG,
						"false");
				request.setAttribute(HrpayrollConstant.HR_PAYROLL_ERROR_LIST,
						null);
				session.setAttribute("leaveDisplay", leavelist);
				FORWARD_PAGE = "leave_application_form";
			}

		}

		catch (Exception e) {
			request.setAttribute(HrpayrollConstant.HR_PAYROLL_ERROR_FLAG, e);
			FORWARD_PAGE = "Error";
		}
		return FORWARD_PAGE;
	}

	// ============= Leave Details=============

	/**
	 * @param form
	 * @param request
	 * @param session
	 * @param rule
	 * @param errorList
	 * @param leaveDTO
	 * @return
	 */
	private String leaveStatusAction(ActionForm form,
			HttpServletRequest request, HttpSession session,
			HrplinkingRule rule, ArrayList errorList, LeaveDTO leaveDTO) {
		String FORWARD_PAGE = null;

		try {
			session.removeAttribute("leaveDTO");
			HrplinkingForm hrForm = null;
			hrForm = (HrplinkingForm) form;
			rule = new HrplinkingRule();
			leaveDTO = hrForm.getLeaveDTO();
			ArrayList list=(ArrayList)session.getAttribute("leaveavaillist");
			errorList = rule.validateLeaveDetails(leaveDTO,list);
			
			if (rule.isError()) {
				session.removeAttribute("leaveDTO");
				request.setAttribute(HrpayrollConstant.HR_PAYROLL_ERROR_FLAG,
						"true");
				request.setAttribute(HrpayrollConstant.HR_PAYROLL_ERROR_LIST,
						errorList);
				FORWARD_PAGE = "leave_application_form";
			} else {
				errorList = rule.validateLeaveBalance(leaveDTO,
						(LeaveDTO) session.getAttribute("leavedtolist"));

				if (rule.isError()) {
					session.removeAttribute("leaveDTO");
					session.removeAttribute("leavedtolist");
					request.setAttribute(
							HrpayrollConstant.HR_PAYROLL_ERROR_FLAG, "true");
					request.setAttribute(
							HrpayrollConstant.HR_PAYROLL_ERROR_LIST, errorList);
					FORWARD_PAGE = "leave_application_form";
				} else {
					request.setAttribute(
							HrpayrollConstant.HR_PAYROLL_ERROR_FLAG, "false");
					request.setAttribute(
							HrpayrollConstant.HR_PAYROLL_ERROR_LIST, null);
					session.setAttribute("leaveDTO", leaveDTO);
					FORWARD_PAGE = "leave_application_form_save";
				}
			}
		}

		catch (Exception e) {
			request.setAttribute(HrpayrollConstant.HR_PAYROLL_ERROR_FLAG, e);
			FORWARD_PAGE = "Error";
		}
		return FORWARD_PAGE;
	}

	// ===================Leave Applications Edit================

	/**
	 * @param request
	 * @param session
	 * @return
	 */
	private String leaveApplicationEditAction(HttpServletRequest request,
			HttpSession session) {
		String FORWARD_PAGE = null;

		try {
			if (session.getAttribute("leaveDTO") != null) {
				FORWARD_PAGE = "leave_application_form";
			}

		}

		catch (Exception e) {
			request.setAttribute(HrpayrollConstant.HR_PAYROLL_ERROR_FLAG, e);
			FORWARD_PAGE = "Error";
		}
		return FORWARD_PAGE;
	}

	// ====================Leave Applications Submit=======================
	/**
	 * @param form
	 * @param request
	 * @param session
	 * @param payrollBD
	 * @param leaveDTO
	 * @param leavelist
	 * @return
	 */
	private String leaveApplicationSubmitAction(ActionForm form,
			HttpServletRequest request, HttpSession session,
			HrplinkingBD payrollBD, LeaveDTO leaveDTO,LeaveDTO avilableDTO, ArrayList leavelist) {
		String FORWARD_PAGE = null;
		try {

			HrplinkingForm hrForm = null;
			hrForm = (HrplinkingForm) form;
			leaveDTO = (LeaveDTO) session.getAttribute("leaveDTO");
			avilableDTO = (LeaveDTO) session.getAttribute("leavedtolist");
			leaveDTO.setLeaveAvail(avilableDTO.getLeaveAvail());
			leaveDTO.setLeavetypeID(hrForm.getLeaveDTO().getLeavetypeID());
			leaveDTO.setTransactionid(payrollBD.getTransactionId());
			leavelist = (ArrayList) session.getAttribute("leaveDisplay");
			if (leaveDTO != null) {
				String strUserId = (String) session.getAttribute("UserId");
				boolean result = payrollBD.submitLeaveApplication(leaveDTO,
						strUserId);
				if (result) {
					session.removeAttribute("leaveDisplay");
					session.removeAttribute("leaveDTO");
					session.removeAttribute("leavedtolist");
					FORWARD_PAGE = "leave_application_success";
				} else {
					session.removeAttribute("leaveDisplay");
					session.removeAttribute("leaveDTO");
					session.removeAttribute("leavedtolist");
					FORWARD_PAGE = "leave_application_form";
				}
			}
		}

		catch (Exception e) {
			request.setAttribute(HrpayrollConstant.HR_PAYROLL_ERROR_FLAG, e);
			FORWARD_PAGE = "Error";
		}

		return FORWARD_PAGE;
	}

	// ==============Available Leave==================

	/**
	 * @param form
	 * @param request
	 * @param session
	 * @param payrollBD
	 * @param _leaveDTO
	 * @param leaveDTO
	 * @return
	 */
	private String availableLeaveAction(ActionForm form,
			HttpServletRequest request, HttpSession session,
			HrplinkingBD payrollBD, LeaveDTO _leaveDTO, LeaveDTO leaveDTO) {
		String FORWARD_PAGE = null;
		try {
			session.removeAttribute("leavedtolist");
			HrplinkingForm hrForm = null;
			hrForm = (HrplinkingForm) form;
			String leavetype = request.getParameter("leavetype");
			String empid = (String) session.getAttribute("UserId");
			leaveDTO = hrForm.getLeaveDTO();
			ArrayList leaveavaillist = payrollBD.getLeaveAvailable(empid,
					leavetype);
			HrplinkingRule rule = new HrplinkingRule();	
			ArrayList errorList=new ArrayList();
			if(leavetype.length()>0 && (leaveavaillist==null || (leaveavaillist!=null && leaveavaillist.size()==0))){
							
				errorList = rule.validateLeaveBalanceDetails(leaveavaillist);
			}
			if (rule.isError()) {
				session.removeAttribute("leaveDTO");
				request.setAttribute(HrpayrollConstant.HR_PAYROLL_ERROR_FLAG,
						"true");
				request.setAttribute(HrpayrollConstant.HR_PAYROLL_ERROR_LIST,
						errorList);
				FORWARD_PAGE = "leave_application_form";
			} else{
			for (int i = 0; i < leaveavaillist.size(); i++) {
				_leaveDTO = (LeaveDTO) leaveavaillist.get(i);
				session.setAttribute("leavedtolist", _leaveDTO);

			}
			session.setAttribute("leaveDTO", leaveDTO);
			session.setAttribute("leaveavaillist", leaveavaillist);
			FORWARD_PAGE = "leave_application_form";
			}
		}

		catch (Exception e) {
			request.setAttribute(HrpayrollConstant.HR_PAYROLL_ERROR_FLAG, e);
			FORWARD_PAGE = "Error";
		}

		return FORWARD_PAGE;
	}

	// ============================Arrears Form=======================

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @param payrollBD
	 * @param errorList
	 * @return
	 */
	private String arrearsAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HrplinkingBD payrollBD, ArrayList errorList) {

		String FORWARD_PAGE = "";
		HrplinkingForm hrForm = null;
		hrForm = (HrplinkingForm) form;
		HrplinkingRule rule = new HrplinkingRule();
		ArrearsDTO arrearsDTO = hrForm.getArrearsDTO();
		errorList = rule.validateArrearsDTORule(arrearsDTO);
		if (rule.isError()) {
			request.setAttribute(HrpayrollConstant.HR_PAYROLL_ERROR_FLAG,
					"true");
			request.setAttribute(HrpayrollConstant.HR_PAYROLL_ERROR_LIST,
					errorList);
			FORWARD_PAGE = "arrears_form";
		} else {
			try {

				boolean result = payrollBD.getArrears_Empid(arrearsDTO);
				if (result) {
					// session.setAttribute("arrearsdto", arrearsDTOsession);
					request.setAttribute(
							HrpayrollConstant.HR_PAYROLL_ERROR_FLAG, "false");
					request.setAttribute(
							HrpayrollConstant.HR_PAYROLL_ERROR_LIST, null);

					FORWARD_PAGE = "arrears_edit";
				} else {
					errorList = rule.validateEmpID(result);
					request.setAttribute(
							HrpayrollConstant.HR_PAYROLL_ERROR_FLAG, "true");
					request.setAttribute(
							HrpayrollConstant.HR_PAYROLL_ERROR_LIST, errorList);
					FORWARD_PAGE = "arrears_form";
				}

			} catch (Exception e) {
				e.printStackTrace();
				//System.out.println("Exception " + e);
			}
		}

		return FORWARD_PAGE;
	}

	// ==================Arrears Edit==============
	/**
	 * @param form
	 * @param request
	 * @param rule
	 * @param session
	 * @param errorList
	 * @param arrearsDTOsession
	 * @return
	 */
	private String arrearsEditAction(ActionForm form,
			HttpServletRequest request, HrplinkingRule rule,
			HttpSession session, ArrayList errorList,
			ArrearsDTO arrearsDTOsession) {
		String FORWARD_PAGE = null;
		try {
			HrplinkingForm hrForm = null;
			hrForm = (HrplinkingForm) form;
			rule = new HrplinkingRule();
			arrearsDTOsession = hrForm.getArrearsDTO();

			errorList = rule.validateArrearsDTORule(arrearsDTOsession);
			if (rule.isError()) {
				request.setAttribute(HrpayrollConstant.HR_PAYROLL_ERROR_FLAG,
						"true");
				request.setAttribute(HrpayrollConstant.HR_PAYROLL_ERROR_LIST,
						errorList);

				FORWARD_PAGE = "arrears_edit";
			} else {
				request.setAttribute(HrpayrollConstant.HR_PAYROLL_ERROR_FLAG,
						"false");
				request.setAttribute(HrpayrollConstant.HR_PAYROLL_ERROR_LIST,
						null);
				session.setAttribute("arrearsdto", arrearsDTOsession);
				FORWARD_PAGE = "arrears_display";
			}

		}

		catch (Exception e) {
			request.setAttribute(HrpayrollConstant.HR_PAYROLL_ERROR_FLAG, e);
			FORWARD_PAGE = "Error";
		}
		return FORWARD_PAGE;
	}

	// ===============Arrears Save===============

	/**
	 * @param request
	 * @param session
	 * @param rule
	 * @param payrollBD
	 * @param errorList
	 * @return
	 */
	private String arrearsSaveAction(HttpServletRequest request,
			HttpSession session, HrplinkingRule rule, HrplinkingBD payrollBD,
			ArrayList errorList) {
		String FORWARD_PAGE = null;
		try {
			ArrearsDTO dto = (ArrearsDTO) session.getAttribute("arrearsdto");
			dto.setArrearStatus("A");
			String strUserId = (String) session.getAttribute("UserId");
			boolean result = payrollBD.submitArrears(dto, strUserId);
			if (result == true) {
				request.setAttribute(HrpayrollConstant.HR_PAYROLL_ERROR_FLAG,
						"false");
				request.setAttribute(HrpayrollConstant.HR_PAYROLL_ERROR_LIST,
						null);
				session.removeAttribute("arrearsdto");
				FORWARD_PAGE = "submit_arrears";
			} else {
				errorList = rule.validate(result);
				request.setAttribute(HrpayrollConstant.HR_PAYROLL_ERROR_FLAG,
						"true");
				request.setAttribute(HrpayrollConstant.HR_PAYROLL_ERROR_LIST,
						errorList);
				FORWARD_PAGE = "arrears_edit";
			}
		}

		catch (Exception e) {
			request.setAttribute(HrpayrollConstant.HR_PAYROLL_ERROR_FLAG, e);
			FORWARD_PAGE = "Error";
		}
		return FORWARD_PAGE;
	}

}