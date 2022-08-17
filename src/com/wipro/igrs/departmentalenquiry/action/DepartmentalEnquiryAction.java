/**
 * DepartmentalEnquiryAction.java
 * 
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
import com.wipro.igrs.departmentalenquiry.form.DepartmentalEnquiryForm;
import com.wipro.igrs.departmentalenquiry.rule.DepartmentalEnquiryRule;
import com.wipro.igrs.login.action.LoginAction;

/**
 * @author root May 31, 2008
 */
public class DepartmentalEnquiryAction extends BaseAction {

	private DepartmentalEnquiryBD deBD = new DepartmentalEnquiryBD();

	private Logger logger = Logger.getLogger(LoginAction.class);

	/* ############################################################################################################################ */

	/**
	 * @param session
	 * @param criminalCaseDTO
	 * @return
	 */
	private String searchComplainee(HttpSession session,
			CriminalCaseEnquiryDTO criminalCaseDTO) {
		String userTypeId = criminalCaseDTO.getUserIdType();
		String returnVal = null;
		ArrayList listUserDetails = null;
		// System.out.println("User Type Id :" + userTypeId);

		if (userTypeId.equalsIgnoreCase("regUserId")) {
			// Search The Registered User Id
			String userId = criminalCaseDTO.getTxtSearchId();
			// System.out.println("User Id :" + userId);
			listUserDetails = deBD.getUserDetails(userId);
			session.setAttribute("RegUserDetails", listUserDetails);
			returnVal = "searchedDetails";
		} else {
			// Search the Employee
			String userId = criminalCaseDTO.getTxtSearchId();
			// System.out.println("User Id :" + userId);
			//listUserDetails = deBD.getUserDetails(userId);
			listUserDetails = deBD.getEmpDetails(userId);
			session.setAttribute("RegUserDetails", listUserDetails);
			returnVal = "searchedDetails";
		}
		return returnVal;
	}

	/* ############################################################################################################################## */

	/**
	 * @param session
	 * @param criminalCaseDTO
	 * @return
	 */
	private String searchComplainer(HttpSession session,
			CriminalCaseEnquiryDTO criminalCaseDTO) {
		String userTypeId = criminalCaseDTO.getCompUserIdType();

		String returnVal = null;
		ArrayList listUserDetails = null;
		// System.out.println("User Type Id :" + userTypeId);

		if (userTypeId.equalsIgnoreCase("regUserId")) {
			// Search The Registered User Id
			String userId = criminalCaseDTO.getTxtCompSearchId();
			// System.out.println("User Id :" + userId);
			listUserDetails = deBD.getComplainerUserDetails(userId);
			session.setAttribute("ComplainerRegUser", listUserDetails);
			returnVal = "ComplainerUserSearchedDetails";
		} else {
			// Search the Employee
			String userId = criminalCaseDTO.getTxtCompSearchId();
			// System.out.println("User Id :" + userId);
			//listUserDetails = deBD.getComplainerUserDetails(userId);
			 listUserDetails = deBD.getComplainerEmpDetails(userId);
			session.setAttribute("ComplainerRegUser", listUserDetails);
			returnVal = "ComplainerUserSearchedDetails";
		}
		return returnVal;
	}

	/* ############################################################################################################################## */

	/**
	 * @param request
	 * @param session
	 * @param userTypeId
	 * @return
	 */
	private String putSearchDetails(HttpServletRequest request,
			HttpSession session, String userTypeId) {

		CriminalCaseEnquiryDTO dto1 = null;
		String returnVal = null;
		String str = request.getParameter("radioGroupUserId");
		// System.out.println("String :" + str);
		ArrayList list = null;
		if (userTypeId.equalsIgnoreCase("regUserId")) {
			list = (ArrayList) session.getAttribute("RegUserDetails");
			// System.out.println("List Size :" + list.size());
			for (int i = 0; i < list.size(); i++) {
				// System.out.println("Hi :" + i);

				CriminalCaseEnquiryDTO dto = (CriminalCaseEnquiryDTO) list
						.get(i);

				// System.out.println("SESSION :" + dto.getUserId());
				if (dto.getUserId().equalsIgnoreCase(str)) {
					dto1 = new CriminalCaseEnquiryDTO();
					dto1.setUserId(dto.getUserId());
					dto1.setName(dto.getName());
					dto1.setPlaceOfPosting(dto.getPlaceOfPosting());
					dto1.setAddress(dto.getAddress());
					dto1.setContactNumber(dto.getContactNumber());
					dto1.setEmail(dto.getEmail());
					dto1.setDesignation(dto.getDesignation());

					break;
				}
			}
			// System.out.println("New Dto :" + dto1.getUserId());
			session.setAttribute("ComplaineeDetails", dto1);
			request.setAttribute("selectedVal", "regUserId");

		} else {
			list = (ArrayList) session.getAttribute("RegUserDetails");
			// System.out.println("List Size :" + list.size());
			for (int i = 0; i < list.size(); i++) {
				// System.out.println("Hi :" + i);

				CriminalCaseEnquiryDTO dto = (CriminalCaseEnquiryDTO) list
						.get(i);

				// System.out.println("SESSION :" + dto.getUserId());
				if (dto.getUserId().equalsIgnoreCase(str)) {
					dto1 = new CriminalCaseEnquiryDTO();
					dto1.setUserId(dto.getUserId());
					dto1.setName(dto.getName());
					dto1.setPlaceOfPosting(dto.getPlaceOfPosting());
					dto1.setAddress(dto.getAddress());
					dto1.setContactNumber(dto.getContactNumber());
					dto1.setEmail(dto.getEmail());
					dto1.setDesignation(dto.getDesignation());

					break;
				}
			}
			// System.out.println("New Dto :" + dto1.getUserId());
			session.setAttribute("ComplaineeDetails", dto1);
			request.setAttribute("selectedVal", "emp");
		}
		return "searchConfirmation";
	}

	/* ######################################################################################################################### */

	/**
	 * @param request
	 * @param session
	 * @param userTypeId
	 * @return
	 */
	private String putComplainerSearchDetails(HttpServletRequest request,
			HttpSession session, String userTypeId) {

		CriminalCaseEnquiryDTO dto1 = null;
		String str = request.getParameter("radioGroupUserId");
		// System.out.println("String :" + str);
		ArrayList list = null;
		if (userTypeId.equalsIgnoreCase("regUserId")) {
			list = (ArrayList) session.getAttribute("ComplainerRegUser");
			// System.out.println("List Size :" + list.size());
			for (int i = 0; i < list.size(); i++) {
				// System.out.println("Hi :" + i);

				CriminalCaseEnquiryDTO dto = (CriminalCaseEnquiryDTO) list
						.get(i);

				// System.out.println("SESSION :" + dto.getComplainerUserId());
				if (dto.getComplainerUserId().equalsIgnoreCase(str)) {
					dto1 = new CriminalCaseEnquiryDTO();
					dto1.setComplainerUserId(dto.getComplainerUserId());
					dto1.setComplainerName(dto.getComplainerName());
					dto1.setComplainerDesignation(dto
							.getComplainerDesignation());
					dto1.setComplainerAddress(dto.getComplainerAddress());
					dto1.setComplainerContactNumber(dto
							.getComplainerContactNumber());
					dto1.setComplainerEmail(dto.getComplainerEmail());
					dto1.setComplainerDesignation(dto
							.getComplainerDesignation());
					dto1.setComplainerDepartment(dto.getComplainerDepartment());
					break;
				}
			}
			// System.out.println("New Dto :" + dto1.getComplainerUserId());
			session.setAttribute("ComplainerDetails", dto1);
		} else {
			list = (ArrayList) session.getAttribute("ComplainerRegUser");
			// System.out.println("List Size :" + list.size());
			for (int i = 0; i < list.size(); i++) {
				// System.out.println("Hi :" + i);

				CriminalCaseEnquiryDTO dto = (CriminalCaseEnquiryDTO) list
						.get(i);

				// System.out.println("SESSION :" + dto.getComplainerUserId());
				if (dto.getComplainerUserId().equalsIgnoreCase(str)) {
					dto1 = new CriminalCaseEnquiryDTO();
					dto1.setComplainerUserId(dto.getComplainerUserId());
					dto1.setComplainerName(dto.getComplainerName());
					dto1.setComplainerDesignation(dto
							.getComplainerDesignation());
					dto1.setComplainerAddress(dto.getComplainerAddress());
					dto1.setComplainerContactNumber(dto
							.getComplainerContactNumber());
					dto1.setComplainerEmail(dto.getComplainerEmail());
					dto1.setComplainerDesignation(dto
							.getComplainerDesignation());
					dto1.setComplainerDepartment(dto.getComplainerDepartment());
					break;
				}
			}
			// System.out.println("New Dto :" + dto1.getComplainerUserId());
			session.setAttribute("ComplainerDetails", dto1);
		}
		return "searchConfirmation";
	}


	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {

		DepartmentalEnquiryForm departmentalEnquiryForm = (DepartmentalEnquiryForm) form;
		CriminalCaseEnquiryDTO criminalCaseDTO = departmentalEnquiryForm
				.getCriminalCaseEnquiryDTO();
		DepartmentalEnquiryRule rule = null;
		ArrayList errorList = null;

		//should be removed
		//session = request.getSession(true);
		
		String forwardName = null;

		// System.out.println("Inside Departmental Enquiry Action");

		// System.out.println("FORM NAME :"
		// + departmentalEnquiryForm.getFormName());
		// System.out.println("REquest :"+request.getParameter("complaintId"));

		if (request.getParameter("start") != null) {
			String userID = (String)session.getAttribute("UserId");
			//CriminalCaseEnquiryDTO myDTO = deBD.getComplaintDetails(userID);
			return mapping.findForward("criminalCaseEnquiry");

		}

		else if (departmentalEnquiryForm.getFormName() != null
				&& departmentalEnquiryForm.getFormName().equals("searchEmp")) {

			// String forwardName = null;
			if (criminalCaseDTO.getSearchFor().equalsIgnoreCase("complainee")) {
				rule = new DepartmentalEnquiryRule();
				errorList = rule.validateSearch(departmentalEnquiryForm);
				if (rule.isError()) {
					// System.out.println("Error Found");
					request.setAttribute("errorsList", errorList);
					forwardName = "searchComplainee";
				}
				forwardName = searchComplainee(session, criminalCaseDTO);
			} else {
				rule = new DepartmentalEnquiryRule();
				errorList = rule.validateSearch(departmentalEnquiryForm);
				if (rule.isError()) {
					// System.out.println("Error Found");
					request.setAttribute("errorsList", errorList);
					forwardName = "searchComplainer";
				}
				forwardName = searchComplainer(session, criminalCaseDTO);
			}

		}

		else if (departmentalEnquiryForm.getFormName() != null
				&& departmentalEnquiryForm.getFormName().equals("putEmpDetail")) {

			// String returnVal = null;
			// System.out.println("I am Here :" +
			// criminalCaseDTO.getSearchFor());
			if (criminalCaseDTO.getSearchFor().equalsIgnoreCase("complainee")) {
				String str = request.getParameter("radioGroupUserId");
				// System.out.println("Cheked User ID :"+str);
				if (str == null) {
					errorList = new ArrayList();
					errorList.add("<li><font color=" + "red"
							+ ">Please Select User Id</font></li>");
					request.setAttribute("errorsList", errorList);
					if (criminalCaseDTO.getUserIdType().equalsIgnoreCase(
							"regUserId")) {
						forwardName = "searchedDetails";
					} else {
						forwardName = "searchedEmpDetails";
					}
					// System.out.println("Return Value = :" + forwardName
					// + "Size :" + errorList.size());
				} else {
					String userIdType = criminalCaseDTO.getUserIdType();
					// System.out.println("User Type Id :" + userIdType);
					forwardName = putSearchDetails(request, session, userIdType);
				}

			} else {

				String str = request.getParameter("radioGroupUserId");
				// System.out.println("Cheked User ID :" + str);
				if (str == null) {
					errorList = new ArrayList();
					errorList.add("<li><font color=" + "red"
							+ ">Please Select User Id</font></li>");
					request.setAttribute("errorsList", errorList);
					if (criminalCaseDTO.getCompUserIdType().equalsIgnoreCase(
							"compUserTypeId")) {
						forwardName = "ComplainerUserSearchedDetails";
					} else {
						forwardName = "ComplainerUserSearchedDetails";
					}
				} else {
					String userIdType = criminalCaseDTO.getCompUserIdType();
					// System.out.println("User Type Id :" + userIdType);
					forwardName = putComplainerSearchDetails(request, session,
							userIdType);
				}

			}
		}
		if (departmentalEnquiryForm.getFormName() != null
				&& departmentalEnquiryForm.getFormName().equals(
						"searchComplete")) {
			forwardName = "complaineeDetails";
		}

		else if (departmentalEnquiryForm.getFormName() != null
				&& departmentalEnquiryForm.getFormName().equalsIgnoreCase(
						"submitCriminalCase")) {
			// System.out.println("I am in Submitt");
			String returnVal = null;

			rule = new DepartmentalEnquiryRule();
			errorList = rule.validateCriminalCase(departmentalEnquiryForm);
			if (rule.isError()) {
				request.setAttribute("errorsList", errorList);
				forwardName = "criminalCaseEnquiry";
			} else {
				String userId = (String) session.getAttribute("UserId");
				String criminalTxnId = deBD.submitCriminalCase(criminalCaseDTO,
						userId);
				if (!criminalTxnId.equalsIgnoreCase("false")) {
					criminalCaseDTO.setCriminalCaseId(criminalTxnId);
					request.setAttribute("complainId", criminalTxnId);
					forwardName = "caseLodged";
				} else {
					forwardName = "caseFailure";
				}
				session.removeAttribute("RegUserDetails");
				session.removeAttribute("EmpDetails");
				session.removeAttribute("ComplainerRegUser");
				session.removeAttribute("ComplainerEmp");
				session.removeAttribute("ComplaineeDetails");
				session.removeAttribute("ComplainerDetails");
			}
			// System.out.println(returnVal);

		} else if (departmentalEnquiryForm.getFormName() == null
				&& request.getParameter("complaintId") != null) {
			// System.out.println("HI");
			// System.out.println("Request Parameter Comp ID
			// :"+request.getParameter("complaintId"));
			String compID = request.getParameter("complaintId");
			CriminalCaseEnquiryDTO myDTO = deBD.getComplaintDetails(compID);
			request.setAttribute("criminalCaseDetails", myDTO);
			forwardName = "printDetails";

		}

		/* ############################################################################################################################# */
		// System.out.println("Forward Page :"+forwardName);
		return mapping.findForward(forwardName);
	}
}