/*
 * ChargesheetCloseAction.java
 */

package com.wipro.igrs.departmentalenquiry.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.departmentalenquiry.bd.DepartmentalEnquiryBD;
import com.wipro.igrs.departmentalenquiry.dto.ChargeSheetReleaseDTO;
import com.wipro.igrs.departmentalenquiry.dto.CriminalCaseEnquiryDTO;
import com.wipro.igrs.departmentalenquiry.form.ChargesheetCloseForm;
import com.wipro.igrs.departmentalenquiry.rule.DepartmentalEnquiryRule;

/**
 * MyEclipse Struts Creation date: 06-12-2008
 * 
 * XDoclet definition:
 * 
 * @struts.action validate="true"
 */
public class ChargesheetCloseAction extends BaseAction {
	/*
	 * Generated Methods
	 */

	/**
	 * Method execute
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	private DepartmentalEnquiryBD deBD = new DepartmentalEnquiryBD();


	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {

		
		
		ChargesheetCloseForm chargeSheetCloseForm = (ChargesheetCloseForm) form;

		//should be removed
		//session = request.getSession(true);
		
		String forwardName = null;
		DepartmentalEnquiryRule rule = null;
		ArrayList errorList = null;

		// System.out.println("I am in Chargesheet Close Action");

		if (chargeSheetCloseForm.getActionType() == null
				&& request.getParameter("start").equalsIgnoreCase(
						"chargesheetCloseStart")) {
			//session.removeAttribute("ComplaintId");
			forwardName = "startChargesheetClose";
		}

		else if (chargeSheetCloseForm.getActionType() != null
				&& chargeSheetCloseForm.getActionType().equalsIgnoreCase(
						"complaintSearch")
				&& request.getParameter("radioGroupUserId") == null) {
			if (chargeSheetCloseForm.getChargeSheetDTO().getStrComplaintId() == null
					|| chargeSheetCloseForm.getChargeSheetDTO()
							.getStrComplaintId().trim().length() == 0) {
				errorList = new ArrayList();
				errorList.add("<li><font color=" + "red >"
						+ "Please Enter a Complaint Number</font></li>");
				request.setAttribute("errorsList", errorList);
				forwardName = "complaintDetails";
			} else {
				String ComplaintNo = chargeSheetCloseForm.getChargeSheetDTO()
						.getStrComplaintId();
				// System.out.println(" Complaint ID" + ComplaintNo);
				ArrayList listComplaint = deBD
						.getChargesheetCloseComplaintList(ComplaintNo);
				request.setAttribute("ComplaintDetails", listComplaint);
				forwardName = "complaintDetails";
			}

		} else if (chargeSheetCloseForm.getActionType() != null
				&& chargeSheetCloseForm.getActionType().equalsIgnoreCase(
						"complaintSearch")
				&& request.getParameter("radioGroupUserId") != null) {
			// System.out.println("I am in block 2 Checking request Parameter");
			String strComplaintId = request.getParameter("radioGroupUserId");
			System.out.println("strComplaintId in java ----------->>> "+strComplaintId);
			session.setAttribute("ComplaintId", strComplaintId);
			forwardName = "searchcomplete";
		} else if (chargeSheetCloseForm.getActionType() != null
				&& chargeSheetCloseForm.getActionType().equalsIgnoreCase(
						"submitComplaint")) {
			if (chargeSheetCloseForm.getChargeSheetDTO().getStrComplaintId() == null
					|| chargeSheetCloseForm.getChargeSheetDTO()
							.getStrComplaintId().trim().length() == 0) {
				errorList = new ArrayList();
				errorList.add("<li><font color=" + "red >"
						+ "Please Specify a Complaint Number</font></li>");
				request.setAttribute("errorsList", errorList);
				forwardName = "chargesheetStart";
			} else {
				String complaintId = chargeSheetCloseForm.getChargeSheetDTO()
						.getStrComplaintId();
				ArrayList list = deBD.getChargesheetCloseDetails(complaintId);
				System.out.println("Inside action ----------------->>>>  "+list);
				CriminalCaseEnquiryDTO criminalDTO = (CriminalCaseEnquiryDTO) list
						.get(0);
				ChargeSheetReleaseDTO chargesheetDTO = (ChargeSheetReleaseDTO) list
						.get(1);
				System.out.println("After in action *****************");
				ArrayList wintnessList = (ArrayList) list.get(2);
				System.out.println("wintnessList------------------>>>>  "+wintnessList);
				session.setAttribute("ComplaineeDetails", criminalDTO);
				session.setAttribute("ChargeSheetDetails", chargesheetDTO);
				session.setAttribute("WitnessDetails", wintnessList);
				forwardName = "chargesheetCloseDetails";
			}

		} else if (chargeSheetCloseForm.getActionType() != null
				&& chargeSheetCloseForm.getActionType().equalsIgnoreCase(
						"closeChargesheet")) {
			if (chargeSheetCloseForm.getStrComment() == null
					|| chargeSheetCloseForm.getStrComment().trim().length() < 1) {
				errorList = new ArrayList();
				errorList.add("<li><font color=" + "red >"
						+ "Please Enter Comment</font></li>");
				request.setAttribute("errorsList", errorList);
				forwardName = "chargesheetCloseDetails";
			} else {
				String userId = (String) session.getAttribute("UserId");
				CriminalCaseEnquiryDTO myDTO = (CriminalCaseEnquiryDTO) session
						.getAttribute("ComplaineeDetails");
				String complaintNo = myDTO.getComplaintNo();
				boolean flag = deBD.saveChargeSheetCloseDetails(
						chargeSheetCloseForm.getStrComment(), complaintNo,
						userId);
				if (flag) {
					request.setAttribute("complainId", complaintNo);
					forwardName = "confirmation";
				} else {
					forwardName = "failure";
				}
			}

		}

		// System.out.println("Forward Name :"+forwardName);
		return mapping.findForward(forwardName);
	}
}