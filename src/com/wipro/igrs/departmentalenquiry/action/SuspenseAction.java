/*
 * SuspenseAction.java
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
import com.wipro.igrs.departmentalenquiry.dto.PreliminaryEnquiryDTO;
import com.wipro.igrs.departmentalenquiry.dto.SuspensionOrderDTO;
import com.wipro.igrs.departmentalenquiry.form.SuspensionForm;
import com.wipro.igrs.departmentalenquiry.rule.DepartmentalEnquiryRule;

/**
 * @author oneapps
 * 
 */

public class SuspenseAction extends BaseAction {

	DepartmentalEnquiryBD deBD = new DepartmentalEnquiryBD();



	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {

		SuspensionForm sForm = (SuspensionForm) form;
		String formName = sForm.getFormName();
		SuspensionOrderDTO sDTO = sForm.getSuspenseDTO();
		DepartmentalEnquiryRule rule = null;
		
			// should be removed
		//session = request.getSession(true);
		
		String filePath = getServlet().getServletContext().getRealPath("")
				+ "/temp/";
		   //create the upload folder if not exists
	      File folder = new File(filePath);
	      if(!folder.exists()){
	        folder.mkdir();
	      }
		String FORWARD = null;
		String start = request.getParameter("start");

		 System.out.println("**************************************************");
		System.out.println("start :"+start);
		System.out.println("formName :"+formName);
		System.out.println("**************************************************");

		if ("suspension".equalsIgnoreCase(start)
				|| "suspension".equalsIgnoreCase(formName)) {
			FORWARD = "startSuspension";
			return mapping.findForward(FORWARD);
		} else if ("revoke".equalsIgnoreCase(start)
				|| "revoke".equalsIgnoreCase(formName)) {
			FORWARD = "startRevoke";
			return mapping.findForward(FORWARD);
		} else if (formName.equalsIgnoreCase("searchComplaintID")) {
			System.out.println("searchComplaintID->in if(cond)");
			String complaintId = sDTO.getStrComplaintId();
			// System.out.println("complaintID:"+complaintId);
			rule = new DepartmentalEnquiryRule();
			ArrayList errorList = rule.validateComplaintId(complaintId);
			// System.out.println("ErrorList :"+errorList);
			if (rule.isError()) {
				// System.out.println("Error Found");
				request.setAttribute("errorsList", errorList);
				// return mapping.findForward("searchComplaintPopup");
			} else {
				ArrayList complaintList = deBD
						.getComplaintListForSuspensionOrder(complaintId);
				// System.out.println("Search List :"+complaintList.size());
				request.setAttribute("ComplaintDetails", complaintList);
				FORWARD = "searchComplaintPopup";
			}
		} else if (formName.equalsIgnoreCase("complaintRadioButtonAction")) {
			String radValue = request.getParameter("radioGroupUserId");
			 System.out.println("radioGroupUserId:"+radValue);
			session.setAttribute("complaintId", radValue);
			FORWARD = "complaintSearchComplete";
		} else if (formName.equalsIgnoreCase("showComplaintDetails")) {
			 System.out.println(">>>in showComplaintDetails<<<<<");
			String complaintNo = sDTO.getStrComplaintId();
			// System.out.println("1");
			CriminalCaseEnquiryDTO depDTO = deBD
					.getDeputedPEDetails(complaintNo); /*
														 * Fetches comlainee,
														 * complainer, deputed
														 * details
														 */
			String status = deBD.checkComplaintStatus(complaintNo);
			if (status.equalsIgnoreCase("Initiated")) {
				 System.out.println("-------------------------->Initiated..");
				session.setAttribute("showDeputedPEDetails", depDTO);
				FORWARD = "suspenseOrderInitiated";
			}
			if (status.equalsIgnoreCase("Suspension")) {
				System.out.println("-------------------------->Accepted..");
				PreliminaryEnquiryDTO preDTO = deBD
						.getSuspenionPEDetails(complaintNo);
				EmployeeAcceptChanrgesDTO acceptDTO = deBD
						.getSuspensionAcceptedDetails(complaintNo);
				session.setAttribute("showDeputedPEDetails", depDTO); // complainee+complainer+deputed
				// details
				session.setAttribute("showPreEnqDetails", preDTO); // Pre. Enq.
				// Details
				session.setAttribute("showAcceptedDetails", acceptDTO); // Pre
				// Enq.
				// Accepted
				// Details
				FORWARD = "suspenseOrderAccepted";
			}
		} else if (formName.equalsIgnoreCase("submitSuspensionOrderInitiated")) {
			System.out.println(">>>in submitSuspensionOrderInitiated<<<");
			rule = new DepartmentalEnquiryRule();
			ArrayList errors = rule.validateSuspensionOrderInitiated(sForm);
			// System.out.println("Errors Size : "+errors.size());
			// System.out.println("Error :"+rule.isError());
			if (rule.isError()) {
				request.setAttribute("errorsList", errors);
				FORWARD = "suspenseOrderInitiated";
			} else {
				try {
					// Upload the File into the Temporary Directory of Server

					FormFile strFile1 = sForm.getTheFile1();
					File newFile1 = new File(filePath + strFile1.getFileName());
					FileOutputStream fos1 = new FileOutputStream(newFile1);
					fos1.write(strFile1.getFileData());
					fos1.close();
					// System.out.println("File "+strFile1.getFileName()+"
					// Uploaded to Temporary Directory :"+filePath);

					FormFile strFile2 = sForm.getTheFile2();
					File newFile2 = new File(filePath + strFile2.getFileName());
					FileOutputStream fos2 = new FileOutputStream(newFile2);
					fos2.write(strFile2.getFileData());
					fos2.close();
					// System.out.println("File "+strFile2.getFileName()+"
					// Uploaded to Temporary Directory :"+filePath);
				} catch (Exception e) {
					e.printStackTrace();
				}
				String userId = (String) session.getAttribute("UserId");
				boolean b = deBD.submitSuspenseOrderInitiated(sForm, filePath,
						userId);
				if (b) {
					FormFile strFile1 = sForm.getTheFile1();
					File newFile1 = new File(filePath + strFile1.getFileName());
					newFile1.delete();

					FormFile strFile2 = sForm.getTheFile2();
					File newFile2 = new File(filePath + strFile2.getFileName());
					newFile2.delete();
					// System.out.println("File Successfully Deleted From temp
					// Dir..");

					request.setAttribute("complaintNo", sForm.getSuspenseDTO()
							.getStrComplaintId());
					session.removeAttribute("complaintId");
					session.removeAttribute("showDeputedPEDetails");
					session.removeAttribute("showPreEnqDetails"); // ,
					// preDTO);
					// //Pre.
					// Enq.
					// Details
					session.removeAttribute("showAcceptedDetails"); // ,
					// acceptDTO);
					// //Pre
					// Enq.
					// Accepted
					// Details

					FORWARD = "suspensionOrderConfirmation";

				} else
				{
					session.removeAttribute("complaintId");
					session.removeAttribute("showDeputedPEDetails");
					session.removeAttribute("showPreEnqDetails"); 
					FORWARD = "failure";
				}
			}
		} else if (formName.equalsIgnoreCase("submitSuspensionOrderAccepted")) {
			System.out.println(">>>in submitSuspensionOrderAccepted<<<");
			rule = new DepartmentalEnquiryRule();
			ArrayList errors = rule.validateSuspensionOrderAccepted(sForm);
			System.out.println("Errors :"+errors.size());
			if (rule.isError()) {
				request.setAttribute("errorsList", errors);
				FORWARD = "suspenseOrderAccepted";
			} else {
				try {
					// Upload the File into the Temporary Directory of Server
					FormFile strFile2 = sForm.getTheFile2();
					File newFile2 = new File(filePath + strFile2.getFileName());
					FileOutputStream fos2 = new FileOutputStream(newFile2);
					fos2.write(strFile2.getFileData());
					fos2.close();
					// System.out.println("File "+strFile2.getFileName()+"
					// Uploaded to Temporary Directory :"+filePath);
				} catch (Exception e) {
					e.printStackTrace();
				}
				String userId = (String) session.getAttribute("UserId");
				boolean b = deBD.submitSuspenseOrderAccepted(sForm, filePath,
						userId);
				if (b) {
					FormFile strFile2 = sForm.getTheFile2();
					File newFile2 = new File(filePath + strFile2.getFileName());
					newFile2.delete();
					// System.out.println("File Successfully Deleted From temp
					// Dir..");

					request.setAttribute("complaintNo", sForm.getSuspenseDTO()
							.getStrComplaintId());
					session.removeAttribute("complaintId");
					session.removeAttribute("showDeputedPEDetails");
					session.removeAttribute("showPreEnqDetails"); // ,
					// preDTO);
					// //Pre.
					// Enq.
					// Details
					session.removeAttribute("showAcceptedDetails"); // ,
					// acceptDTO);
					// //Pre
					// Enq.
					// Accepted
					// Details

					FORWARD = "suspensionOrderConfirmation";

				} else{
					session.removeAttribute("complaintId");
				session.removeAttribute("showDeputedPEDetails");
				session.removeAttribute("showPreEnqDetails"); 
					FORWARD = "failure";
				}
			}
		} else if (request.getParameter("formName").equalsIgnoreCase(
				"suspensionOrderPrint")
				&& request.getParameter("complaintNo") != null) {
			 System.out.println(">>>>>suspensionOrderPrint<<<<<");
			String complaintId = request.getParameter("complaintNo");
			ArrayList list = deBD.getSuspensionOrderRecord(complaintId);
			CriminalCaseEnquiryDTO cceDTO = (CriminalCaseEnquiryDTO) list
					.get(0);
			PreliminaryEnquiryDTO preDTO = (PreliminaryEnquiryDTO) list.get(1);
			SuspensionOrderDTO susDTO = (SuspensionOrderDTO) list.get(2);
			ArrayList documentList = (ArrayList) list.get(3);
			session.removeAttribute("complaintId");
			request.setAttribute("complaintId", complaintId);
			request.setAttribute("Criminal-Revoke", cceDTO);
			request.setAttribute("PreEnq-Revoke", preDTO);
			request.setAttribute("Suspension-Revoke", susDTO);
			request.setAttribute("documentList", documentList);
			FORWARD = "suspensionOrderPrint"; 
			// go and popup the print page
			// "/jsp/departmentalenquiry/suspensionOrderPrint.jsp"
		}
		// REVOKE OPERATION
		else if (formName.equalsIgnoreCase("showSuspensionDetails")) {
			 System.out.println("showSuspensionDetails->in if(cond)");
			String complaintId = sDTO.getStrComplaintId();
			// System.out.println("complaintID:"+complaintId);
			rule = new DepartmentalEnquiryRule();
			ArrayList errorList = rule.validateComplaintId(complaintId);
			// System.out.println("ErrorList :"+errorList);
			if (rule.isError()) {
				// System.out.println("Error Found");
				request.setAttribute("errorsList", errorList);
				// return mapping.findForward("searchComplaintPopup");
			} else {
				ArrayList complaintList = deBD
						.getSuspensionComplaintsList(complaintId);
				// System.out.println("Search List :"+complaintList.size());
				request.setAttribute("key", complaintId);
				request.setAttribute("SOrderList", complaintList);
				FORWARD = "searchSuspendedPopup";
			}
		} else if (formName.equalsIgnoreCase("sOrderRadioButtonAction")) {
			System.out.println(">>>>in sOrderRadioButtonAction<<<<<");
			String radValue = request.getParameter("radioGroupUserId");
			 System.out.println("radioGroupUserId:"+radValue);
			session.setAttribute("complaintId", radValue);
			FORWARD = "complaintSearchComplete";
		} else if (formName.equalsIgnoreCase("showDetailsForRevoke")) {
			System.out.println(">>>>>showDetailsForRevoke<<<<<");
			String complaintId = sDTO.getStrComplaintId();
			ArrayList list = deBD.getSuspensionOrderRecord(complaintId);
			CriminalCaseEnquiryDTO cceDTO = (CriminalCaseEnquiryDTO) list
					.get(0);
			PreliminaryEnquiryDTO preDTO = (PreliminaryEnquiryDTO) list.get(1);
			SuspensionOrderDTO susDTO = (SuspensionOrderDTO) list.get(2);
			ArrayList documentList = (ArrayList) list.get(3);
			session.removeAttribute("complaintId");
			request.setAttribute("complaintId", complaintId);
			request.setAttribute("Criminal-Revoke", cceDTO);
			request.setAttribute("PreEnq-Revoke", preDTO);
			request.setAttribute("Suspension-Revoke", susDTO);
			request.setAttribute("documentList", documentList);
			FORWARD = "showRevokePage";
		} else if (formName.equalsIgnoreCase("doRevokeAction")) {
			 System.out.println(">>>>in doRevokeAction<<<<");
			String complaintId = sDTO.getStrComplaintId();
			String userId = (String) session.getAttribute("UserId");
			boolean b = deBD.updateSuspensionToRevoke(complaintId, userId);
			if (b) {
				// System.out.println("Complaint Id : "+complaintId+" status
				// changed from 'Suspension' to 'Revoke' Succesfully!!");
				request.setAttribute("complaintNo", complaintId);
				FORWARD = "showRevokeConfirm";
			} else {
				ArrayList errors = new ArrayList();
				errors.add("Error occured in DB");
				request.setAttribute("errorsList", errors);
				FORWARD = "showRevokePage";
			}
		} else if (formName.equalsIgnoreCase("doRevokeCancel")) {
			FORWARD = "startRevoke";
		}
		// System.out.println("Forwarding to :------>"+FORWARD);
		return mapping.findForward(FORWARD);
	}
}