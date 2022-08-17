/*
 * ChargesheetAction.java
 */

package com.wipro.igrs.departmentalenquiry.action;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.departmentalenquiry.bd.DepartmentalEnquiryBD;
import com.wipro.igrs.departmentalenquiry.dto.ChargeSheetReleaseDTO;
import com.wipro.igrs.departmentalenquiry.dto.CriminalCaseEnquiryDTO;
import com.wipro.igrs.departmentalenquiry.dto.WitnessDTO;
import com.wipro.igrs.departmentalenquiry.form.ChargeSheetForm;
import com.wipro.igrs.departmentalenquiry.rule.DepartmentalEnquiryRule;

/**
 * MyEclipse Struts Creation date: 06-06-2008
 * 
 * XDoclet definition:
 * 
 * @struts.action path="/chargesheet" name="chargesheetForm" scope="request"
 *                validate="true"
 */
public class ChargesheetAction extends BaseAction {
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
		
		
		
		ChargeSheetForm chargesheetForm = (ChargeSheetForm) form;// TODO
		// Auto-generated
		// method
		// stub
		String forwardName = null;
		DepartmentalEnquiryRule rule = null;
		ArrayList errorList = null;
		
		//should be removed
		//session = request.getSession(true);
		
		String filePath = getServlet().getServletContext().getRealPath("")
				+ "/temp/";

		
		if (chargesheetForm.getActionType() != null
				&& chargesheetForm.getActionType().equalsIgnoreCase(
						"complaintSearch")
				&& request.getParameter("radioGroupUserId") == null) {
			System.out.println("complaintSearch");
			session.removeAttribute("ChargesheetDetails");
			session.removeAttribute("attachment1");
			 session.removeAttribute("ComplaintId");
			if (chargesheetForm.getChargeSheetDTO().getStrComplaintId() == null
					|| chargesheetForm.getChargeSheetDTO().getStrComplaintId()
							.trim().length() == 0) {
				errorList = new ArrayList();
				errorList.add("<li><font color=" + "red >"
						+ "Please Enter a Complaint Number</font></li>");
				request.setAttribute("errorsList", errorList);
				forwardName = "complaintDetails";
			} else {
				System.out.println("In else block");
				String ComplaintNo = chargesheetForm.getChargeSheetDTO()
						.getStrComplaintId();
				 System.out.println(" Complaint ID" + ComplaintNo);
				ArrayList listComplaint = deBD
						.getChargesheetComplaintList(ComplaintNo);
				System.out.println("listcomplaint"+listComplaint);
				request.setAttribute("ComplaintDetails", listComplaint);
				forwardName = "complaintDetails";
			}

		} else if (chargesheetForm.getActionType() != null
				&& chargesheetForm.getActionType().equalsIgnoreCase(
						"complaintSearch")
				&& request.getParameter("radioGroupUserId") != null) {
			// System.out.println("I am in block 2 Checking request Parameter");
			String strComplaintId = request.getParameter("radioGroupUserId");
			session.setAttribute("ComplaintId", strComplaintId);
			forwardName = "searchcomplete";
		} else if (chargesheetForm.getActionType() != null
				&& chargesheetForm.getActionType().equalsIgnoreCase(
						"submitComplaint")) {
			if (chargesheetForm.getChargeSheetDTO().getStrComplaintId() == null
					|| chargesheetForm.getChargeSheetDTO().getStrComplaintId()
							.trim().length() == 0) {
				errorList = new ArrayList();
				errorList.add("<li><font color=" + "red >"
						+ "Please Specify a Complaint Number</font></li>");
				request.setAttribute("errorsList", errorList);
				forwardName = "chargesheetStart";
			} else {
				String complaintId = chargesheetForm.getChargeSheetDTO()
						.getStrComplaintId();
				CriminalCaseEnquiryDTO myDTO = deBD
						.getChargesheetDetails(complaintId);
				// System.out.println("I am now in Action after getting
				// details"+ myDTO.getComplaintNo());
				chargesheetForm.getChargeSheetDTO().setStrComplaintId(
						complaintId);
				session.setAttribute("PreEnquiryDetails", myDTO);
				forwardName = "chargesheetDetails";
			}

		}

		else if (chargesheetForm.getActionType() == null
				&& request.getParameter("name") != null
				&& request.getParameter("name").equalsIgnoreCase(
						"attachBasicProof")) {
			// System.out.println("HI");
			ChargeSheetReleaseDTO chargesheetDTO = chargesheetForm
					.getChargeSheetDTO();
			String compID = (String) session.getAttribute("ComplaintId");
			chargesheetDTO.setStrComplaintId(compID);
			session.setAttribute("ChargesheetDetails", chargesheetDTO);
			forwardName = "attachBasicProof";

		} else if (chargesheetForm.getActionType() != null
				&& chargesheetForm.getActionType().equalsIgnoreCase(
						"basicProofAttached")) {
			FormFile file = chargesheetForm.getTheFile();
			// System.out.println("File Name :"+file.getFileName());
			rule = new DepartmentalEnquiryRule();
			errorList = rule.validateChargesheetDoc(file);
			if (rule.isError()) {
				request.setAttribute("errorsList", errorList);
				forwardName = "attachBasicProof";
			} else {

				// System.out.println("Testing : I am Writing the file
				// :"+file.getFileName()+" "+"to the Temp Dir :");

				try {
					File newFile = new File(filePath + file.getFileName());
					FileOutputStream fos = new FileOutputStream(newFile);
					fos.write(file.getFileData());
					fos.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}

				ChargeSheetReleaseDTO chargesheetDTO = (ChargeSheetReleaseDTO) session
						.getAttribute("ChargesheetDetails");

				session.removeAttribute("ChargesheetDetails");
				chargesheetDTO.setStrBasisOfCharges(file.getFileName());
				chargesheetForm.setChargeSheetDTO(chargesheetDTO);

				session.setAttribute("ChargesheetDetails", chargesheetDTO);
				forwardName = "donePage";
			}
		} else if (chargesheetForm.getActionType() != null
				&& chargesheetForm.getActionType().equalsIgnoreCase(
				"returnwitnessattachment")) {
			
			forwardName = "chargesheetDetails";
			
		}
		else if (chargesheetForm.getActionType() != null
				&& chargesheetForm.getActionType().equalsIgnoreCase(
				"returnbasicattachment")) {
			
			forwardName = "chargesheetDetails";
			
		}else if (chargesheetForm.getActionType() != null
				&& chargesheetForm.getActionType().equalsIgnoreCase(
						"saveChargesheet")) {
			rule = new DepartmentalEnquiryRule();
			errorList = rule.validateSaveChargeSheet(chargesheetForm);
			if (rule.isError()) {
				if (session.getAttribute("ChargesheetDetails") == null) {
					errorList.add("<li><font color=" + "red >"
							+ "Please Select a Basic Proof</font></li>");
				}
				if (session.getAttribute("attachment1") == null) {
					errorList.add("<li><font color=" + "red >"
							+ "Please Select a Witness</font></li>");
				}
				request.setAttribute("errorsList", errorList);
				forwardName = "chargesheetDetails";
			} else if (session.getAttribute("ChargesheetDetails") == null
					&& session.getAttribute("attachment1") == null) {
				errorList = new ArrayList();
				errorList.add("<li><font color=" + "red >"
						+ "Please Select a Basic Proof</font></li>");
				errorList.add("<li><font color=" + "red >"
						+ "Please Select a Witness</font></li>");
				request.setAttribute("errorsList", errorList);
				forwardName = "chargesheetDetails";
			} else if (session.getAttribute("ChargesheetDetails") == null
					&& session.getAttribute("attachment1") != null) {
				errorList = new ArrayList();
				errorList.add("<li><font color=" + "red >"
						+ "Please Select a Basic Proof</font></li>");
				request.setAttribute("errorsList", errorList);
				forwardName = "chargesheetDetails";
			} else if (session.getAttribute("ChargesheetDetails") != null
					&& session.getAttribute("attachment1") == null) {
				errorList = new ArrayList();
				errorList.add("<li><font color=" + "red >"
						+ "Please Select a Witness</font></li>");
				request.setAttribute("errorsList", errorList);
				forwardName = "chargesheetDetails";
			} else {
				// System.out.println("I am in Action Submit ChargeSheet");

				ChargeSheetReleaseDTO chargeSheetDTO1 = (ChargeSheetReleaseDTO) session
						.getAttribute("ChargesheetDetails");
				ChargeSheetReleaseDTO chargeSheetDTO = chargesheetForm
						.getChargeSheetDTO();
				chargeSheetDTO.setStrComplaintId((String) session
						.getAttribute("ComplaintId"));
				chargeSheetDTO.setStrBasisOfCharges(chargeSheetDTO1
						.getStrBasisOfCharges());
				// System.out.println("Complaint ID
				// :"+chargeSheetDTO.getStrComplaintId());
				ArrayList listWitnessDetails = (ArrayList) session
						.getAttribute("attachment1");
				String userId = (String) session.getAttribute("UserId");
				boolean flagChargeSheetSaveResult = deBD
						.saveChargeSheetDetails(chargeSheetDTO,
								listWitnessDetails, userId, filePath);
				if (flagChargeSheetSaveResult) {
					// System.out.println("I am here in successfull block");

					File file1 = new File(filePath
							+ chargeSheetDTO.getStrBasisOfCharges());
					file1.delete();
					// System.out.println("File Successfully Deleted From temp
					// :"+chargeSheetDTO.getStrBasisOfCharges());

					for (int i = 0; i < listWitnessDetails.size(); i++) {
						WitnessDTO dto = (WitnessDTO) listWitnessDetails.get(i);
						String strFileName = dto.getStrSupportingDocName();
						File file = new File(filePath + strFileName);
						file.delete();
						// System.out.println("File Successfully Deleted From
						// temp :"+strFileName);
					}
					session.removeAttribute("ComplaintId");
					session.removeAttribute("PreEnquiryDetails");
					session.removeAttribute("ChargesheetDetails");

					request.setAttribute("ComplaintId", chargeSheetDTO
							.getStrComplaintId());
					forwardName = "chargeSheetConfirm";
				} else{
					errorList=new ArrayList();
					errorList.add("<li><font color=" + "red >"
							+ "Transaction Failure</font></li>");
					forwardName = "chargeSheetConfirm";
				}
			}
		}
		return mapping.findForward(forwardName);
	}
}