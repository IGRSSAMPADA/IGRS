package com.wipro.igrs.auditinspection.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.auditinspection.bd.AddComplianceBD;
import com.wipro.igrs.auditinspection.dto.DROReportDetailsDTO;
import com.wipro.igrs.auditinspection.dto.PublicDTO;
import com.wipro.igrs.auditinspection.dto.SROReportDetailsDTO;
import com.wipro.igrs.auditinspection.form.PublicForm;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.common.IGRSCommon;

public class AddComplianceAction extends BaseAction {

	
	String forward_Jsp;
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
	 	 PublicForm publicForm= (PublicForm)form;
	
		 PublicDTO publicDTO = publicForm.getPublicInspectionDTO();
		 String funId = (String)session.getAttribute("functionId");
         String userId = (String)session.getAttribute("UserId");
         String officeId=(String)session.getAttribute("loggedToOffice");
         String txnId=request.getParameter("txnId");
         String language=(String)session.getAttribute("languageLocale");
         publicForm.setLanguage(language);
         String activityid = request.getParameter("ACTID");
         
 		if(activityid!=null)
 		{
         IGRSCommon save=null;
 		try {
 			save = new IGRSCommon();
 			save.saveactivitylog(userId, activityid);
 		} catch (Exception e) {
 			e.printStackTrace();
 		}
 	}
         AddComplianceBD bd= new AddComplianceBD();
         
         //ADDED BY SHRUTI---17 APRIL 2014
         if("reset".equalsIgnoreCase(request.getParameter("action")))
         {
        	 publicDTO.setReportId("");
        	 publicDTO.setFromDate("");
        	 publicDTO.setToDate("");
        	 publicDTO.setReportType("");
        	 request.setAttribute("srList", null);
        	 request.setAttribute("drList", null);
         }
         //END
         
         
        if(txnId!=null)
        {
        	//if(txnId.substring(0,1).equalsIgnoreCase("1"))
        		if(txnId.substring(0,1).equalsIgnoreCase("2"))
        	{
        		SROReportDetailsDTO sroReportDetailDto = new SROReportDetailsDTO();
        		SROReportDetailsDTO newSroDetailsDto = new SROReportDetailsDTO();
        		sroReportDetailDto.setInspectionId(txnId);
        		ArrayList listChkDto = bd.getCheckMapDetails(sroReportDetailDto,language);
    			ArrayList listChkOther = bd.getCheckMapOtherDetails(sroReportDetailDto,language);
    			ArrayList lisPendingItem = bd.getPendingItems(sroReportDetailDto);
    			ArrayList lisEmpDetails = bd.getEmpDetailsList(sroReportDetailDto);
    			newSroDetailsDto = bd.getSroReportDetails(sroReportDetailDto,language);
    			ArrayList listSroComm = bd.getSroComments(sroReportDetailDto);
    			ArrayList listDocName = bd.getDocNames(sroReportDetailDto);
    			request.setAttribute("listempdetails", lisEmpDetails);
    			request.setAttribute("chkmapdetails", listChkDto);
    			request.setAttribute("pendingItems", lisPendingItem);
    			request.setAttribute("chkmapother", listChkOther);
    			request.setAttribute("sroDetailsDto", newSroDetailsDto);
    			request.setAttribute("sroComments", listSroComm);
    			request.setAttribute("listDocNames", listDocName);
    			publicDTO.setTxnId(txnId);
    			publicDTO.setComplianceFlag(newSroDetailsDto.getComplianceFlag());
    			publicForm.setPublicInspectionDTO(publicDTO);
    			forward_Jsp = "SearchResultSR";
        	}
        	
        	//else if(txnId.substring(0,1).equalsIgnoreCase("2"))
        		else if(txnId.substring(0,1).equalsIgnoreCase("1"))
        	{
        		DROReportDetailsDTO droDetailsDto  = bd.getDroReportDetails(txnId,language);
    			ArrayList listEmpDetails    =  bd.getDROEmpDetails(txnId);
    			ArrayList listReasonDetails =  bd.getDROReasonDetails(txnId);
    			ArrayList listPenDocDetails  = bd.getDocumentNames(txnId);
    			request.setAttribute("listDocNamesPen",listPenDocDetails);
    			request.setAttribute("reasonMap",listReasonDetails);
    			request.setAttribute("droEmpDetails",listEmpDetails);
    			request.setAttribute("droDetailsDto",droDetailsDto);
    			publicDTO.setTxnId(txnId);
    			publicDTO.setComplianceFlag(droDetailsDto.getComplianceFlag());
    			publicForm.setPublicInspectionDTO(publicDTO);
    			forward_Jsp = "SearchresultDR";
        	}
        }
        else if("ViewAttachment".equalsIgnoreCase(request.getParameter("action")))
		{

			   String filename = request.getParameter("path").toString();
			   response.setContentType("application/download");

			   try
			   {
			   response.setHeader("Content-Disposition", "attachment; filename="
						+ URLEncoder.encode(filename,"UTF-8"));
			   
			   File fileToDownload = new File(filename);
			   FileInputStream fileInputStream = new FileInputStream(fileToDownload);
			   OutputStream out = response.getOutputStream();
			   byte[] buf = new byte[2048];

			   int readNum;
			   while ((readNum=fileInputStream.read(buf))!=-1)
			   {
			      out.write(buf,0,readNum);
			   }
			   fileInputStream.close();
			   out.close();
			   }catch( Exception e)
			   {
				   e.printStackTrace();
			   }
		if("sro".equalsIgnoreCase(request.getParameter("page")))
		{	
			return mapping.findForward("SearchResultSR");
		}
		else if("dro".equalsIgnoreCase(request.getParameter("page")))
		{
			return mapping.findForward( "SearchresultDR");
		}
		
		
		}
        else if("ok".equalsIgnoreCase(request.getParameter("clicksrSearch")))
    	{
    		if(publicDTO.getReportType()!=null && !publicDTO.getReportType().equalsIgnoreCase(""))
    		{

    			String role=bd.getRole(officeId);
    			if(role!=null&&role.equalsIgnoreCase("DRO"))
            	{
    				if(publicDTO.getReportType().equalsIgnoreCase("SR"))
    				{
    					request.removeAttribute("srList");
    	    			ArrayList srdashlist=bd.getSroDashboardSearchDR(officeId,publicDTO,language);
    	    			request.setAttribute("srList", srdashlist);
    	    			forward_Jsp = "searchResult";
    	    			publicForm.setActionType("");
    				}
    				else if(publicDTO.getReportType().equalsIgnoreCase("DR"))
    				{
    					request.removeAttribute("srList");
    	    			ArrayList srdashlist=bd.getDroDashboardSearchDR(officeId,publicDTO,language);
    	    			request.setAttribute("srList", srdashlist);
    	    			forward_Jsp = "searchResult";
    	    			publicForm.setActionType("");
    				}
    					
            	}
    			if(role!=null&&role.equalsIgnoreCase("DIGO"))
            	{
    				if(publicDTO.getReportType().equalsIgnoreCase("SR"))
    				{
    					request.removeAttribute("srList");
    	    			ArrayList srdashlist=bd.getSroDashboardSearchDIG(officeId,publicDTO,language);
    	    			request.setAttribute("srList", srdashlist);
    	    			forward_Jsp = "searchResult";
    	    			publicForm.setActionType("");
    				}
    				else if(publicDTO.getReportType().equalsIgnoreCase("DR"))
    				{
    					request.removeAttribute("srList");
    	    			ArrayList srdashlist=bd.getDroDashboardSearchDIG(officeId,publicDTO,language);
    	    			request.setAttribute("srList", srdashlist);
    	    			forward_Jsp = "searchResult";
    	    			publicForm.setActionType("");
    				}
            	}
    			if(role!=null&&role.equalsIgnoreCase("IGO"))
            	{
    				if(publicDTO.getReportType().equalsIgnoreCase("SR"))
    				{
    					request.removeAttribute("srList");
    	    			ArrayList srdashlist=bd.getSroDashboardSearchIG(officeId,publicDTO,language);
    	    			request.setAttribute("srList", srdashlist);
    	    			forward_Jsp = "searchResult";
    	    			publicForm.setActionType("");
    				}
    				else if(publicDTO.getReportType().equalsIgnoreCase("DR"))
    				{
    					request.removeAttribute("srList");
    	    			ArrayList srdashlist=bd.getDroDashboardSearchIG(officeId,publicDTO,language);
    	    			request.setAttribute("srList", srdashlist);
    	    			forward_Jsp = "searchResult";
    	    			publicForm.setActionType("");
    				}
            	}
    		}
    		else
    		{	
    		ArrayList srdashlist=bd.getSrDashboardSearch(officeId,publicDTO,language);
			request.setAttribute("srList", srdashlist);
			forward_Jsp = "searchResult";
    		}
			publicForm.setActionType("");
			return mapping.findForward(forward_Jsp);
    	}
        else if("submitSR".equalsIgnoreCase(publicForm.getActionType()))
    	{
    		if(bd.updateSROComliance(publicDTO));
    		{
    			forward_Jsp = "compSuccess";
    			publicForm.setActionType("");
    		}
    	}
    	else if("submitDR".equalsIgnoreCase(publicForm.getActionType()))
    	{
    		if(bd.updateDROComliance(publicDTO));
    		{
    			forward_Jsp = "compSuccess";
    			publicForm.setActionType("");
    		}
    	}
    	else if("submitsearchSR".equalsIgnoreCase(publicForm.getActionType()))
    	{
    			request.removeAttribute("srList");
    			ArrayList srdashlist=bd.getSrDashboardSearch(officeId,publicDTO,language);
    			request.setAttribute("srList", srdashlist);
    			forward_Jsp = "searchResult";
    			publicForm.setActionType("");
    		
    	}
    	else if("submitsearchDR".equalsIgnoreCase(publicForm.getActionType()))
    	{
    			String role=bd.getRole(officeId);
    			if(role!=null&&role.equalsIgnoreCase("DRO"))
            	{
    				if(publicDTO.getReportType().equalsIgnoreCase("SR"))
    				{
    					request.removeAttribute("srList");
    	    			ArrayList srdashlist=bd.getSroDashboardSearchDR(officeId,publicDTO,language);
    	    			request.setAttribute("srList", srdashlist);
    	    			forward_Jsp = "searchResult";
    	    			publicForm.setActionType("");
    				}
    				else if(publicDTO.getReportType().equalsIgnoreCase("DR"))
    				{
    					request.removeAttribute("srList");
    	    			ArrayList srdashlist=bd.getDroDashboardSearchDR(officeId,publicDTO,language);
    	    			request.setAttribute("srList", srdashlist);
    	    			forward_Jsp = "searchResult";
    	    			publicForm.setActionType("");
    				}
    					
            	}
    			if(role!=null&&role.equalsIgnoreCase("DIGO"))
            	{
    				if(publicDTO.getReportType().equalsIgnoreCase("SR"))
    				{
    					request.removeAttribute("srList");
    	    			ArrayList srdashlist=bd.getSroDashboardSearchDIG(officeId,publicDTO,language);
    	    			request.setAttribute("srList", srdashlist);
    	    			forward_Jsp = "searchResult";
    	    			publicForm.setActionType("");
    				}
    				else if(publicDTO.getReportType().equalsIgnoreCase("DR"))
    				{
    					request.removeAttribute("srList");
    	    			ArrayList srdashlist=bd.getDroDashboardSearchDIG(officeId,publicDTO,language);
    	    			request.setAttribute("srList", srdashlist);
    	    			forward_Jsp = "searchResult";
    	    			publicForm.setActionType("");
    				}
            	}
    			if(role!=null&&role.equalsIgnoreCase("IGO"))
            	{
    				if(publicDTO.getReportType().equalsIgnoreCase("SR"))
    				{
    					request.removeAttribute("srList");
    	    			ArrayList srdashlist=bd.getSroDashboardSearchIG(officeId,publicDTO,language);
    	    			request.setAttribute("srList", srdashlist);
    	    			forward_Jsp = "searchResult";
    	    			publicForm.setActionType("");
    				}
    				else if(publicDTO.getReportType().equalsIgnoreCase("DR"))
    				{
    					request.removeAttribute("srList");
    	    			ArrayList srdashlist=bd.getDroDashboardSearchIG(officeId,publicDTO,language);
    	    			request.setAttribute("srList", srdashlist);
    	    			forward_Jsp = "searchResult";
    	    			publicForm.setActionType("");
    				}
            	}
    			
    		
    	}
        else if("Add Compliance".equalsIgnoreCase(request.getParameter("fnName"))&&"Audit And  Inspection".equalsIgnoreCase(request.getParameter("modName")))
		{
			String role=bd.getRole(officeId);
        	if(role!=null&&role.equalsIgnoreCase("SRO"))
        	{
        		request.removeAttribute("srList");
    			request.removeAttribute("drList");
    			publicDTO.setReportId("");
    			publicDTO.setFromDate("");
    			publicDTO.setToDate("");
    			publicDTO.setReportType("");
    			ArrayList srdashlist=bd.getSrDashboard(officeId,language);
    			request.setAttribute("srList", srdashlist);
    			forward_Jsp="SearchPageSR";
        	}
        	else
        	{
        		if(role!=null&&role.equalsIgnoreCase("DRO"))
            	{
        			request.removeAttribute("srList");
        			request.removeAttribute("drList");
        			
        			ArrayList srdashlist=bd.getSroDashboardDR(officeId,language);
        			ArrayList drdashlist=bd.getDroDashboardDR(officeId,language);
        			request.setAttribute("srList", srdashlist);
        			request.setAttribute("drList", drdashlist);
            	}
        		else if(role!=null&&role.equalsIgnoreCase("DIGO"))
            	{
        			request.removeAttribute("srList");
        			request.removeAttribute("drList");
        			ArrayList srdashlist=bd.getSroDashboardDIG(officeId,language);
        			ArrayList drdashlist=bd.getDroDashboardDIG(officeId,language);
        			request.setAttribute("srList", srdashlist);
        			request.setAttribute("drList", drdashlist);
            	}
        		else if(role!=null&&role.equalsIgnoreCase("IGO"))
            	{
        			request.removeAttribute("srList");
        			request.removeAttribute("drList");
        			ArrayList srdashlist=bd.getSroDashboardIG(officeId,language);
        			ArrayList drdashlist=bd.getDroDashboardIG(officeId,language);
        			request.setAttribute("srList", srdashlist);
        			request.setAttribute("drList", drdashlist);
            	}
        		publicDTO.setReportId("");
    			publicDTO.setFromDate("");
    			publicDTO.setToDate("");
    			String report=request.getParameter("report");
    			if(report!=null)
    			{
    				publicDTO.setReportType(report);
    			}
    			else 
    				publicDTO.setReportType("");
    			publicForm.setPublicInspectionDTO(publicDTO);
    			forward_Jsp="SearchPage";
    			
        	}
        }
		return mapping.findForward(forward_Jsp);
	}

}
