package com.wipro.igrs.auditinspection.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.auditinspection.bd.AGMPReportDetailsBD;
import com.wipro.igrs.auditinspection.bd.AddAuditComplianceBD;
import com.wipro.igrs.auditinspection.bd.AddComplianceBD;
import com.wipro.igrs.auditinspection.dto.AGMPReportDetailsDTO;
import com.wipro.igrs.auditinspection.dto.PublicDTO;
import com.wipro.igrs.auditinspection.form.AGMPReportDetailsForm;
import com.wipro.igrs.auditinspection.form.PublicForm;
import com.wipro.igrs.baseaction.action.BaseAction;

public class AddAuditComplainceAction extends BaseAction {

	
	String forward_Jsp;
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		 String funId = (String)session.getAttribute("functionId");
         String userId = (String)session.getAttribute("UserId");
         String officeId=(String)session.getAttribute("loggedToOffice");
         String language=(String)session.getAttribute("languageLocale");
		 AGMPReportDetailsForm eForm =(AGMPReportDetailsForm) form;
	 	
	 	 String actionName=eForm.getActionType();

	 	 String fnName = "";
		String modName = "";
		if(request.getParameter("fnName")!= null)
			fnName = request.getParameter("fnName");
		if(request.getParameter("fnName")!= null)
			modName = request.getParameter("modName");
		 AddAuditComplianceBD bd= new AddAuditComplianceBD();
		 if("Add Compliance".equalsIgnoreCase(request.getParameter("fnName"))&&"Audit And  Inspection".equalsIgnoreCase(request.getParameter("modName")))
			{
			 
			 String role=bd.getRole(officeId);
			 session.removeAttribute("paraSearchList");
			 eForm.getAgmpDTO().setRole(role);	
			 eForm.getAgmpDTO().setReportId("");
			 eForm.getAgmpDTO().setToDate("");
			 eForm.getAgmpDTO().setFromDate("");
			 eForm.setActionType("");
			 eForm.getAgmpDTO().setReportType("");
			 eForm.getAgmpDTO().setReportSubType("");
			 eForm.getAgmpDTO().setReportSubType1("");

			 forward_Jsp="AddComplianceSearch";
	        	
	    			
	        }
		 else if("submitSearch".equalsIgnoreCase(actionName))
	      {
				
				
			    eForm.getAgmpDTO().setAuditSearchList(bd.searchReport( eForm.getAgmpDTO(), officeId));
				request.setAttribute("searchList", eForm.getAgmpDTO().getAuditSearchList());
				eForm.setActionType("");
				forward_Jsp="AddComplianceSearchResult";
	      }
		 else if(request.getParameter("pageName")!=null && request.getParameter("pageName").equalsIgnoreCase("dashboard"))
		 {
			 eForm.getAgmpDTO().setAuditSearchList(bd.searchReport( eForm.getAgmpDTO(), officeId));
				request.setAttribute("searchList", eForm.getAgmpDTO().getAuditSearchList());
				eForm.setActionType("");
				forward_Jsp="AddComplianceSearchResult";
		 }
		 else if(request.getParameter("pageTitle")!=null)
		 {
			 String reportID = (String)request.getParameter("txnId");
			 eForm.getAgmpDTO().setReport(reportID);
			 	ArrayList paraSearchList = new ArrayList();
				AGMPReportDetailsBD bd1 = new AGMPReportDetailsBD();	
				ArrayList al=bd1.fetchSearchResult(reportID);
				ArrayList al1=bd1.fetchParaResult(reportID);
			 AGMPReportDetailsDTO dto1=new AGMPReportDetailsDTO();
			 dto1=(AGMPReportDetailsDTO)al.get(0);
			 for(int i=0;i<al1.size();i++)
			 {	  AGMPReportDetailsDTO dto2=new AGMPReportDetailsDTO();
				 
			 	dto2=(AGMPReportDetailsDTO)al1.get(i);						
				
			 	eForm.getAgmpDTO().setParaSearchList(dto2.getParaSearchList());
			 	paraSearchList.add(dto2);
			 }
				eForm.getAgmpDTO().setParaSearchList(paraSearchList);
			 
			 eForm.getAgmpDTO().setAuditDate(dto1.getAuditDate().substring(0, 10));
			 eForm.getAgmpDTO().setAudEntryDate(dto1.getAudEntryDate().substring(0, 10));
			 if(dto1.getReportStatus().equalsIgnoreCase("O")||dto1.getReportStatus().equalsIgnoreCase("Open")){
				 eForm.getAgmpDTO().setReportStatus("Open");
			 }
			 else{
				 eForm.getAgmpDTO().setReportStatus(dto1.getReportStatus());
			 }
			 
			 eForm.getAgmpDTO().setTxnId(reportID);
			 eForm.getAgmpDTO().setAuditType(dto1.getAuditType());
			 eForm.getAgmpDTO().setComplianceFlag(dto1.getComplianceFlag());
			 eForm.getAgmpDTO().setSrCompilance(dto1.getSrCompilance());
			 eForm.getAgmpDTO().setDrComplinace(dto1.getDrComplinace());
			 eForm.getAgmpDTO().setDigCompliance(dto1.getDigCompliance());
			 eForm.getAgmpDTO().setIgCompliance(dto1.getIgCompliance());
			 request.setAttribute("",eForm);
			 session.setAttribute("paraSearchList", paraSearchList);
			 eForm.setActionType("");
			 forward_Jsp = "agmpSearchViewCompliance";
		 }
		 else if(request.getParameter("paraTitle")!=null)
			{
				String paraID = (String)request.getParameter("paraTxnId");
				AGMPReportDetailsBD bd1 = new AGMPReportDetailsBD();	

				eForm.getAgmpDTO().setParaListView(bd1.getObjectionDetails(paraID));
				
				 eForm.setActionType("");
				forward_Jsp="objView";
				
			}
		 else if(actionName!=null && actionName.equalsIgnoreCase("backTrace")){

			 
			 eForm.setActionType("");
			 forward_Jsp = "agmpSearchViewCompliance";
		 }
		 else if("addComplianceSubmit".equalsIgnoreCase(actionName))
		 {
			 boolean status=bd.updateStatus(eForm.getAgmpDTO());
			if(status)
			{	
				 eForm.setActionType("");
				 forward_Jsp = "sucess";
			}
			
		 }
		 
		 return mapping.findForward(forward_Jsp);
}
	
}