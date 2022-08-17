/*
 * RevokeAction.java
 */

package com.wipro.igrs.departmentalenquiry.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.departmentalenquiry.bd.DepartmentalEnquiryBD;
import com.wipro.igrs.departmentalenquiry.dto.SuspensionOrderDTO;
import com.wipro.igrs.departmentalenquiry.form.SuspensionForm;
import com.wipro.igrs.departmentalenquiry.rule.DepartmentalEnquiryRule;

/**
 * @author oneapps
 * 
 */

public class RevokeAction extends BaseAction {

	DepartmentalEnquiryBD deBD = new DepartmentalEnquiryBD();



	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		
		
		SuspensionForm sForm = (SuspensionForm) form;
		String formName = sForm.getFormName();
		SuspensionOrderDTO sDTO = sForm.getSuspenseDTO();
		DepartmentalEnquiryRule rule = null;
		
			// 	should be removed
		//session = request.getSession(true);
		
		String filePath = getServlet().getServletContext().getRealPath("")
				+ "/temp/";
		String FORWARD = null;

		// System.out.println("formName :"+formName);

		/*
		 * else if(formName.equalsIgnoreCase("sOrderRadioButtonAction")) {
		 * String radValue= request.getParameter("radioGroupUserId");
		 * System.out.println("radioGroupUserId:"+radValue);
		 * session.setAttribute("complaintId", radValue); FORWARD =
		 * "complaintSearchComplete"; } else
		 * if(formName.equalsIgnoreCase("showComplaintDetails")) {
		 * System.out.println(">>>in showComplaintDetails<<<<<"); String
		 * complaintNo = sDTO.getStrComplaintId(); //System.out.println("1");
		 * CriminalCaseEnquiryDTO depDTO =
		 * deBD.getDeputedPEDetails(complaintNo); //Fetches comlainee,
		 * complainer, deputed details String status =
		 * deBD.checkComplaintStatus(complaintNo);
		 * if(status.equalsIgnoreCase("Initiated")) {
		 * System.out.println("-------------------------->Initiated..");
		 * session.setAttribute("showDeputedPEDetails", depDTO); FORWARD =
		 * "suspenseOrderInitiated"; } if(status.equalsIgnoreCase("Accepted")) {
		 * System.out.println("-------------------------->Accepted..");
		 * PreliminaryEnquiryDTO preDTO =
		 * deBD.getSuspenionPEDetails(complaintNo); EmployeeAcceptChanrgesDTO
		 * acceptDTO = deBD.getSuspensionAcceptedDetails(complaintNo);
		 * session.setAttribute("showDeputedPEDetails", depDTO);
		 * //complainee+complainer+deputed details
		 * session.setAttribute("showPreEnqDetails", preDTO); //Pre. Enq.
		 * Details session.setAttribute("showAcceptedDetails", acceptDTO); //Pre
		 * Enq. Accepted Details FORWARD = "suspenseOrderAccepted"; } } else
		 * if(formName.equalsIgnoreCase("submitSuspensionOrderInitiated")) {
		 * System.out.println(">>>in submitSuspensionOrderInitiated<<<");
		 * rule = new DepartmentalEnquiryRule(); ArrayList errors =
		 * rule.validateSuspensionOrderInitiated(sForm);
		 * System.out.println("Errors Size : "+errors.size());
		 * System.out.println("Error :"+rule.isError()); if(rule.isError()) {
		 * request.setAttribute("errorsList", errors); FORWARD =
		 * "suspenseOrderInitiated"; } else { try { //Upload the File into the
		 * Temporary Directory of Server
		 * 
		 * FormFile strFile1 = sForm.getTheFile1(); File newFile1 = new
		 * File(filePath + strFile1.getFileName()); FileOutputStream fos1 = new
		 * FileOutputStream(newFile1); fos1.write(strFile1.getFileData());
		 * fos1.close(); System.out.println("File "+strFile1.getFileName()+"
		 * Uploaded to Temporary Directory :"+filePath);
		 * 
		 * FormFile strFile2 = sForm.getTheFile2(); File newFile2 = new
		 * File(filePath + strFile2.getFileName()); FileOutputStream fos2 = new
		 * FileOutputStream(newFile2); fos2.write(strFile2.getFileData());
		 * fos2.close(); System.out.println("File "+strFile2.getFileName()+"
		 * Uploaded to Temporary Directory :"+filePath); } catch(Exception
		 * e){e.printStackTrace();} String userId =
		 * (String)session.getAttribute("UserId"); boolean b =
		 * deBD.submitSuspenseOrderInitiated(sForm, filePath, userId); if(b) {
		 * FormFile strFile1 = sForm.getTheFile1(); File newFile1 = new
		 * File(filePath+strFile1.getFileName()); newFile1.delete();
		 * 
		 * FormFile strFile2 = sForm.getTheFile2(); File newFile2 = new
		 * File(filePath+strFile2.getFileName()); newFile2.delete();
		 * System.out.println("File Successfully Deleted From temp Dir..");
		 * 
		 * request.setAttribute("complaintNo",
		 * sForm.getSuspenseDTO().getStrComplaintId());
		 * session.removeAttribute("complaintId");
		 * session.removeAttribute("showDeputedPEDetails");
		 * session.removeAttribute("showPreEnqDetails"); //, preDTO); //Pre.
		 * Enq. Details session.removeAttribute("showAcceptedDetails"); //,
		 * acceptDTO); //Pre Enq. Accepted Details
		 * 
		 * FORWARD = "suspensionOrderConfirmation";
		 *  } else FORWARD = "failure"; } }
		 * 
		 * else if(formName.equalsIgnoreCase("submitSuspensionOrderAccepted")) {
		 * System.out.println(">>>in submitSuspensionOrderAccepted<<<"); rule =
		 * new DepartmentalEnquiryRule(); ArrayList errors =
		 * rule.validateSuspensionOrderAccepted(sForm);
		 * System.out.println("Errors :"+errors.size()); if(rule.isError()) {
		 * request.setAttribute("errorsList", errors); FORWARD =
		 * "suspenseOrderAccepted"; } else { try { //Upload the File into the
		 * Temporary Directory of Server FormFile strFile2 =
		 * sForm.getTheFile2(); File newFile2 = new File(filePath +
		 * strFile2.getFileName()); FileOutputStream fos2 = new
		 * FileOutputStream(newFile2); fos2.write(strFile2.getFileData());
		 * fos2.close(); System.out.println("File "+strFile2.getFileName()+"
		 * Uploaded to Temporary Directory :"+filePath); } catch(Exception
		 * e){e.printStackTrace();} String userId =
		 * (String)session.getAttribute("UserId"); boolean b =
		 * deBD.submitSuspensionOrderAccepted(sForm, filePath, userId); if(b) {
		 * FormFile strFile2 = sForm.getTheFile2(); File newFile2 = new
		 * File(filePath+strFile2.getFileName()); newFile2.delete();
		 * System.out.println("File Successfully Deleted From temp Dir..");
		 * 
		 * request.setAttribute("complaintNo",
		 * sForm.getSuspenseDTO().getStrComplaintId());
		 * session.removeAttribute("complaintId");
		 * session.removeAttribute("showDeputedPEDetails");
		 * session.removeAttribute("showPreEnqDetails"); //, preDTO); //Pre.
		 * Enq. Details session.removeAttribute("showAcceptedDetails"); //,
		 * acceptDTO); //Pre Enq. Accepted Details
		 * 
		 * FORWARD = "suspensionOrderConfirmation";
		 *  } else FORWARD = "failure"; } }
		 */
		// System.out.println("Forwarding to :------>"+FORWARD);
		return mapping.findForward(FORWARD);
	}
}