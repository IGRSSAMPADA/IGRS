/**
 * DeInitateAction.java
 */

package com.wipro.igrs.departmentalenquiry.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.departmentalenquiry.bd.DepartmentalEnquiryBD;
import com.wipro.igrs.departmentalenquiry.dto.CriminalCaseEnquiryDTO;
import com.wipro.igrs.departmentalenquiry.form.DeInitiateForm;
import com.wipro.igrs.departmentalenquiry.rule.DepartmentalEnquiryRule;
import com.wipro.igrs.login.action.LoginAction;

/**
 * @author oneapps
 * 
 */

public class DeInitiateAction extends BaseAction {

	private DepartmentalEnquiryBD deBD = new DepartmentalEnquiryBD();
	private Logger logger = Logger.getLogger(LoginAction.class);



	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {

		
		
		DeInitiateForm deInitiateForm = (DeInitiateForm) form;		
		String formName = deInitiateForm.getFormName();
		CriminalCaseEnquiryDTO criminalCaseDTO = deInitiateForm
				.getCriminalCaseDTO();
		
		//should be removed
		//session = request.getSession(true);
		
		DepartmentalEnquiryRule rule = null;
		ArrayList errorList = null;
		String forwardName = null;

		// System.out.println("Form Name :" + deInitiateForm.getFormName());

		if (request.getParameter("start") != null) {
			//session.removeAttribute("ComplaintId");
			forwardName = "deinitiate";

		}

		else if (deInitiateForm.getFormName() != null
				&& deInitiateForm.getFormName().equalsIgnoreCase(
						"complaintSearch")
				&& request.getParameter("submit").equalsIgnoreCase("Search")) {

			if (deInitiateForm.getComplaintNo() == null
					|| deInitiateForm.getComplaintNo().trim().length() == 0) {
				errorList = new ArrayList();
				errorList.add("<li><font color=" + "red >"
						+ "Please Specify a Complaint Number</font></li>");
				request.setAttribute("errorsList", errorList);
				forwardName = "complaintDetails";
			} else {
				String ComplaintId = deInitiateForm.getComplaintNo();
				// System.out.println(" Complaint ID" + ComplaintId);
				ArrayList listComplaint = deBD.getComplaintList(ComplaintId);
				request.setAttribute("ComplaintDetails", listComplaint);
				// session.setAttribute("Flag", true);
				forwardName = "complaintDetails";
			}

		} else if (deInitiateForm.getFormName() != null
				&& deInitiateForm.getFormName().equalsIgnoreCase(
						"complaintSearch")
				&& request.getParameter("radioGroupUserId") != null) {
			// System.out.println("I am In 2nd one");

			if (request.getParameter("radioGroupUserId") == null) {
				errorList = new ArrayList();
				errorList.add("<li><font color=" + "red >"
						+ "Please Specify a Complaint Number</font></li>");
				request.setAttribute("errorsList", errorList);
				forwardName = "complaintDetails";
			} else {
				String complaintId = request.getParameter("radioGroupUserId");
				session.setAttribute("ComplaintId", complaintId);
				forwardName = "searchcomplete";
			}

		} 
		
		else if (deInitiateForm.getFormName() != null 
				&& deInitiateForm.getFormName().equalsIgnoreCase("revoke")) {
			forwardName = "startRevoke";
		}
		else if (deInitiateForm.getFormName() != null
				&& deInitiateForm.getFormName().equalsIgnoreCase("putDetails")) {

			rule = new DepartmentalEnquiryRule();
			errorList = rule.validateComplaintId(deInitiateForm);
			if (rule.isError()) {
				request.setAttribute("errorsList", errorList);
				forwardName = "deinitiate";
			} else {
				String complaintId = (String) session
						.getAttribute("ComplaintId");
				// System.out.println(" Complaint ID" + complaintId);
				CriminalCaseEnquiryDTO criminalCaseDTO1 = deBD
						.getComplaintDetails(complaintId);
				session.setAttribute("Details", criminalCaseDTO1);
				forwardName = "details";
			}

		}

		else if (deInitiateForm.getFormName() != null
				&& deInitiateForm.getFormName().equalsIgnoreCase(
						"deputedPerson")
				&& request.getParameter("radioGroupUserId") == null) {
			rule = new DepartmentalEnquiryRule();
			errorList = rule.validateDP(deInitiateForm);
			if (rule.isError()) {
				request.setAttribute("errorsList", errorList);
				forwardName = "returnDP";
			} else {

				String userId = criminalCaseDTO.getTxtSearchId();
				// System.out.println(" UserId ID" + userId);
				ArrayList list = deBD.getUserDetails(userId);
				session.setAttribute("DeputedPerson", list);
				forwardName = "searchList";
			}
		} else if (deInitiateForm.getFormName() != null
				&& deInitiateForm.getFormName().equalsIgnoreCase(
						"deputedPerson")
				&& request.getParameter("radioGroupUserId") != null) {
			// System.out.println("I am in Deputed Check");

			if (request.getParameter("radioGroupUserId") == null) {

				errorList = new ArrayList();
				errorList.add("<li><font color=" + "red >"
						+ "Please Specify a Complaint Number</font></li>");
				request.setAttribute("errorsList", errorList);
				forwardName = "returnDP";
			} else {

				CriminalCaseEnquiryDTO dto1 = null;
				String deputedPersonId = request
						.getParameter("radioGroupUserId");
				ArrayList list = (ArrayList) session
						.getAttribute("DeputedPerson");
				for (int i = 0; i < list.size(); i++) {
					CriminalCaseEnquiryDTO dto = (CriminalCaseEnquiryDTO) list
							.get(i);
					if (dto.getUserId().equalsIgnoreCase(deputedPersonId)) {
						dto1 = new CriminalCaseEnquiryDTO();
						dto1.setUserId(dto.getUserId());
						dto1.setName(dto.getName());
						dto1.setAddress(dto.getAddress());
						dto1.setContactNumber(dto.getContactNumber());
						dto1.setContactNumber(dto.getContactNumber());
						dto1.setEmail(dto.getEmail());
						break;
					}
				}

				session.setAttribute("DeputedDetails", dto1);
				forwardName = "searchcomplete";
			}
		}

		else if (deInitiateForm.getFormName() != null
				&& deInitiateForm.getFormName().equalsIgnoreCase("submitForm")) {

			rule = new DepartmentalEnquiryRule();
			errorList = rule.validateDeSubmit(deInitiateForm);
			if (rule.isError()) {

				request.setAttribute("errorsList", errorList);
				forwardName = "details";
			} else {
				// System.out.println("I am in Submit");
				String userId = (String) session.getAttribute("UserId");
				String complaintId = (String) session
						.getAttribute("ComplaintId");
				// System.out.println("Complaint ID :" + complaintId);
				CriminalCaseEnquiryDTO dto = (CriminalCaseEnquiryDTO) session
						.getAttribute("DeputedDetails");
				boolean flag = deBD.submitDeInitiateDetails(dto, userId,
						complaintId);

				if (flag) {
					session.removeAttribute("ComplaintId");
					session.removeAttribute("Details");
					session.removeAttribute("DeputedPerson");
					session.removeAttribute("DeputedDetails");
					request.setAttribute("complaintNo", complaintId);
					forwardName = "decomplete";
				} else {
					forwardName = "failure";
				}
			}
		}

		else if (formName == null
				&& request.getParameter("complaintId") != null) {
			// System.out.println("HI");
			// System.out.println("Request Parameter Comp ID
			// :"+request.getParameter("complaintId"));
			String compID = request.getParameter("complaintId");
			CriminalCaseEnquiryDTO myDTO = deBD.getPreEnquiryDetails(compID);
			request.setAttribute("DeDetails", myDTO);
			forwardName = "printDetails";

		}
		// System.out.println("Forwar Name >>>>>" + forwardName);
		return mapping.findForward(forwardName);
	}

}
