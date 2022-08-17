/**
 * PreliminaryEnquiryAction.java
 */

package com.wipro.igrs.departmentalenquiry.action;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.departmentalenquiry.bd.DepartmentalEnquiryBD;
import com.wipro.igrs.departmentalenquiry.dto.CriminalCaseEnquiryDTO;
import com.wipro.igrs.departmentalenquiry.dto.EmployeeAcceptChanrgesDTO;
import com.wipro.igrs.departmentalenquiry.dto.EmployeeReleaseChanrgesDTO;
import com.wipro.igrs.departmentalenquiry.dto.PreliminaryEnquiryDTO;
import com.wipro.igrs.departmentalenquiry.dto.SuspensionOrderDTO;
import com.wipro.igrs.departmentalenquiry.form.PreliminaryEnquiryForm;
import com.wipro.igrs.departmentalenquiry.rule.DepartmentalEnquiryRule;

/**
 * @author admin
 * 
 */
public class PreliminaryEnquiryAction extends BaseAction {

	DepartmentalEnquiryBD deBD = new DepartmentalEnquiryBD();


	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {

		
			//should be removed
		//session = request.getSession(true);
		
		PreliminaryEnquiryForm myForm = (PreliminaryEnquiryForm) form;
		PreliminaryEnquiryDTO preDTO = myForm.getPreEnquiryDTO();
		DepartmentalEnquiryRule rule = null;
		ArrayList errorList = null;
		String forwardName = null;
		String filePath = getServlet().getServletContext().getRealPath("")
				+ "/temp/";
		// System.out.println("I am in PreEnquiry Action :");
		// System.out.println(" Form Name :" + myForm.getActionType());
		// System.out
		// .println("Param :" + request.getParameter("radioGroupUserId"));

		if (request.getParameter("start") != null) {
			//session.removeAttribute("ComplaintId");
			forwardName = "preEnquiryStart";

		} else if (myForm.getActionType().equalsIgnoreCase("complaintSearch")
				&& request.getParameter("radioGroupUserId") == null) {
			// System.out.println("I am here in First Block");
			if (myForm.getStrComplaintId() == null
					|| myForm.getStrComplaintId().length() < 1) {
				errorList = new ArrayList();
				errorList.add("<li><font color=" + "red >"
						+ "Please Specify a Complaint Number</font></li>");
				request.setAttribute("errorsList", errorList);
				forwardName = "complaintDetails";
			} else {
				String ComplaintId = myForm.getStrComplaintId();
				System.out.println(" Complaint ID" + ComplaintId);
				ArrayList listComplaint = deBD
						.getPreEnquiryComplaintList(ComplaintId);
				request.setAttribute("ComplaintDetails", listComplaint);
				forwardName = "complaintDetails";
			}
		} else if (myForm.getActionType().equalsIgnoreCase("complaintSearch")
				&& request.getParameter("radioGroupUserId") != null) {
			//System.out.println("I am in block 2 Checking request Parameter");
			String complaintId = request.getParameter("radioGroupUserId");
			session.setAttribute("ComplaintId", complaintId);
			forwardName = "searchcomplete";
		} else if (myForm.getActionType().equalsIgnoreCase("submitComplaint")) {
			if (myForm.getStrComplaintId() == null
					|| myForm.getStrComplaintId().length() < 1) {
				errorList = new ArrayList();
				errorList.add("<li><font color=" + "red >"
						+ "Please Specify a Complaint Number</font></li>");
				request.setAttribute("errorsList", errorList);
				forwardName = "preEnquiryStart";
			} else {
				String complaintId = myForm.getStrComplaintId();
				CriminalCaseEnquiryDTO myDTO = deBD
						.getPreEnquiryDetails(complaintId);
				System.out.println("I am now in Action after getting details"
						+ myDTO.getComplaintNo());
				myForm.setStrComplaintId(complaintId);
				session.setAttribute("PreEnquiryDetails", myDTO);
				forwardName = "enquiryDetails";
			}

		} else if (myForm.getActionType().equalsIgnoreCase("preEnquiryAction")) {
			// System.out.println("I am in PreEnquiry Action");
			rule = new DepartmentalEnquiryRule();
			errorList = rule.validatePreEnquiryAction(myForm);
			// System.out.println("Size of Error List" + errorList.size());
			if (rule.isError()) {
				request.setAttribute("errorsList", errorList);
				forwardName = "enquiryDetails";
			} else {
				String preDetails = myForm.getStrPriliminaryDetails();
				String preComment = myForm.getStrPriliminaryComments();
				String preEnqDate = myForm.getStrPreliminaryDate();
				String comlaintNo = myForm.getStrComplaintId();
				FormFile strFile = myForm.getTheFile();
				// System.out.println("Complaiont ID in Action :" + comlaintNo);
				// System.out.println("FIle Size :" + strFile.getFileSize());
				try {
					File newFile = new File(filePath + strFile.getFileName());
					FileOutputStream fos = new FileOutputStream(newFile);
					fos.write(strFile.getFileData());
					fos.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				preDTO.setStrComplaintId(comlaintNo);
				preDTO.setStrPriliminaryDetails(preDetails);
				preDTO.setStrPriliminaryComments(preComment);
				preDTO.setStrPreliminaryDate(preEnqDate);
				preDTO.setStrSuuportingDoc(strFile.getFileName());
				session.setAttribute("PreEnquiryAction", preDTO);
				forwardName = "preEnquiryAction";

			}
		} else if (myForm.getActionType().equalsIgnoreCase("takeAction")) {
			String action = request.getParameter("radioProcess");
			if (action == null || action.length() == 0) {
				errorList = new ArrayList();
				errorList.add("<li><font color=" + "red >"
						+ "Please Select a choice for Proceeding</font></li>");
				request.setAttribute("errorsList", errorList);
				forwardName = "preEnquiryAction";
			} else {
				if (action.equalsIgnoreCase("ACCEPT")) {
					forwardName = "empAcceptCharges";
				} else if (action.equalsIgnoreCase("RELEASE")) {
					forwardName = "empReleaseCharges";
				} else {

					forwardName = "suspenssion";
				}
			}
		} else if (myForm.getActionType().equalsIgnoreCase(
				"submitEmpAcceptCharges")) {
			rule = new DepartmentalEnquiryRule();
			errorList = rule.validateEmployeeAcceptCharges(myForm);
			if (rule.isError()) {
				request.setAttribute("errorsList", errorList);
				forwardName = "empAcceptCharges";
			} else {
				// System.out.println("I have passed the rule now in submit");
				String userId = (String) session.getAttribute("UserId");
				PreliminaryEnquiryDTO preEnquiryDTO = (PreliminaryEnquiryDTO) session
						.getAttribute("PreEnquiryAction");
				EmployeeAcceptChanrgesDTO empDTO = myForm
						.getEmpAcceptChargesDTO();
				try {
					boolean flagAcceptChargesResult = deBD
							.submitEmpAcceptCharges(preEnquiryDTO, empDTO,
									filePath, userId);
					if (flagAcceptChargesResult) {
						File newFile = new File(filePath
								+ preEnquiryDTO.getStrSuuportingDoc());
						newFile.delete();
						// System.out.println("File Successfully Deleted From
						// temp :"+preEnquiryDTO.getStrSuuportingDoc());

						String complaintNo = preEnquiryDTO.getStrComplaintId();
						request.setAttribute("complaintNo", complaintNo);

						session.removeAttribute("ComplaintId");
						session.removeAttribute("PreEnquiryDetails");
						session.removeAttribute("PreEnquiryAction");
						forwardName = "empAcceptChargesConfirmation";
					} else {
						forwardName = "failure";
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		} else if (myForm.getActionType().equalsIgnoreCase(
				"employeeReleaseChargesSubmit")) {
			rule = new DepartmentalEnquiryRule();
			errorList = rule.validateEmployeeReleaseCharges(myForm);
			if (rule.isError()) {
				request.setAttribute("errorsList", errorList);
				forwardName = "empReleaseCharges";
			} else {
				String userId = (String) session.getAttribute("UserId");
				PreliminaryEnquiryDTO preEnquiryDTO = (PreliminaryEnquiryDTO) session
						.getAttribute("PreEnquiryAction");
				EmployeeReleaseChanrgesDTO empRelDTO = myForm
						.getEmpReleaseDTO();
				boolean flagReleaseChargesResult = deBD
						.submitEmpReleaseCharges(preEnquiryDTO, empRelDTO,
								filePath, userId);
				if (flagReleaseChargesResult) {
					File newFile = new File(filePath
							+ preEnquiryDTO.getStrSuuportingDoc());
					newFile.delete();
					// System.out.println("File Successfully Deleted From temp
					// :"+preEnquiryDTO.getStrSuuportingDoc());

					String complaintNo = preEnquiryDTO.getStrComplaintId();
					request.setAttribute("complaintNo", complaintNo);

					session.removeAttribute("ComplaintId");
					session.removeAttribute("PreEnquiryDetails");
					session.removeAttribute("PreEnquiryAction");
					forwardName = "empReleaseChargesConfirmation";
				} else {
					forwardName = "failure";
				}
			}
		} else if (myForm.getActionType().equalsIgnoreCase(
				"preEnquirySuspenssion")) {
			rule = new DepartmentalEnquiryRule();
			errorList = rule.validatePreEnquirySuspension(myForm);
			if (rule.isError()) {
				request.setAttribute("errorsList", errorList);
				forwardName = "suspenssion";
			} else {
				String userId = (String) session.getAttribute("UserId");
				PreliminaryEnquiryDTO preEnquiryDTO = (PreliminaryEnquiryDTO) session
						.getAttribute("PreEnquiryAction");
				SuspensionOrderDTO suspensionDTO = myForm.getSuspensionDTO();
				FormFile strFile = myForm.getStrSignature();
				suspensionDTO.setStrSignatureFileName(strFile.getFileName());

				try {
					File newFile = new File(filePath + strFile.getFileName());
					FileOutputStream fos = new FileOutputStream(newFile);
					fos.write(strFile.getFileData());
					fos.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}

				boolean flagSuspensionResult = deBD.submitPreSuspension(
						preEnquiryDTO, suspensionDTO, filePath, userId);

				if (flagSuspensionResult) {
					File newFile = new File(filePath + strFile.getFileName());
					newFile.delete();
					// System.out.println("File Successfully Deleted From temp
					// :"+preEnquiryDTO.getStrSuuportingDoc());

					String complaintNo = preEnquiryDTO.getStrComplaintId();
					request.setAttribute("complaintNo", complaintNo);

					session.removeAttribute("ComplaintId");
					session.removeAttribute("PreEnquiryDetails");
					session.removeAttribute("PreEnquiryAction");
					forwardName = "suspensionConfirmation";
				} else {
					forwardName = "failure";
				}
			}
		} else if (myForm.getActionType() != null
				&& myForm.getActionType().equalsIgnoreCase("cancel")) {
			/* session.removeAttribute("ComplaintId"); */
			session.removeAttribute("PreEnquiryDetails");
			session.removeAttribute("PreEnquiryAction");
			forwardName = "preEnquiryStart";
		}

		// System.out.println("Forward Page :" + forwardName);
		return mapping.findForward(forwardName);
	}
}
