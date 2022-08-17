/*
* Copyright Notice
*==============================================================================
* This file contains proprietary information of IGRS Bhopal Madhya Pradesh .
* Copying or reproduction without prior written approval is prohibited.
* Copyright (c) 2007-08
*==============================================================================
*
* File Name   :   
 * Author      :   
 * Description :   
 * ----------------------------------------------------------------------------
* Version         Modified By     Modified On           Modification
* ----------------------------------------------------------------------------
* 1.0             SHRUTI          xxxxx              
 * --------------------------------------------------------------------------------
*/


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

import com.wipro.igrs.auditinspection.bd.SROReportDetailsBD;
import com.wipro.igrs.auditinspection.constant.CommonConstant;
import com.wipro.igrs.auditinspection.dto.AGMPReportDetailsDTO;
import com.wipro.igrs.auditinspection.dto.SROCommentsDTO;
import com.wipro.igrs.auditinspection.dto.SROReportDetailsDTO;
import com.wipro.igrs.auditinspection.form.SROInspectionViewForm;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.common.IGRSCommon;

/**
 * @author admin
 * 
 */
public class SROInspectionViewAction extends BaseAction {

	/**
	 * Method execute
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,HttpSession session) {

		AGMPReportDetailsDTO agmpReport = new AGMPReportDetailsDTO();
		SROReportDetailsBD sroReportBd = new SROReportDetailsBD();
	//	HttpSession session = request.getSession(true);
		CommonConstant auditConstant=new CommonConstant();
		SROInspectionViewForm sroInspectionForm = (SROInspectionViewForm) form;
		String forwardAction = "";
		String strActionType = sroInspectionForm.getActionType();
		String language=(String)session.getAttribute("languageLocale");
		sroInspectionForm.setLanguage(language);
		ArrayList errorList = null;
		String activityid = request.getParameter("ACTID");
		String userId = session.getAttribute("UserId").toString();
		if(activityid!=null)
		{
		IGRSCommon save=null;
		try {
			save = new IGRSCommon();
			save.saveactivitylog(userId, activityid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		if (request.getParameter("viewsearch") != null) {
			session.removeAttribute("SRODetails1");
			ArrayList sroList = new ArrayList();
			//String userId = (String) session.getAttribute("UserId");
			//ADDED BY SHRUTI
			String officeId=(String)session.getAttribute("loggedToOffice");
			try {
				//MODIFIED BY SHRUTI----18TH OCT 2013
				//sroList = sroReportBd.getSroName(userId);

				//role check
				if(auditConstant.DIG_OFFICE_TYPE_ID.equalsIgnoreCase(sroReportBd.getOfficeTypeId(officeId)))
				{
					sroList=sroReportBd.getSROForDIG(officeId, language);
					
				}
				else if(auditConstant.IG_OFFICE_TYPE_ID.equalsIgnoreCase(sroReportBd.getOfficeTypeId(officeId)))
				{
					sroList=sroReportBd.getAllSroName(language);
				}
				else if(auditConstant.SR_OFFICE_TYPE_ID.equalsIgnoreCase(sroReportBd.getOfficeTypeId(officeId)))
				{
					sroList=sroReportBd.getLoggedInSroName(officeId, language);
				}
				else//DR Role
				sroList=sroReportBd.getJrsdctnSroName(officeId,language);
				//end
				agmpReport.setSroList(sroList);
			} catch (Exception sroEx) {
				sroEx.printStackTrace();
			}
			sroInspectionForm.setReportId("");
			sroInspectionForm.setInspectionStatus("");
			sroInspectionForm.setSroId("");
			sroInspectionForm.setToDate("");
			sroInspectionForm.setFromDate("");
			session.setAttribute("SRODetails1", agmpReport);
			session.removeAttribute("inspectionidlist");
			sroInspectionForm.setActionType("viewPage");
			forwardAction = "viewandsearch";
			
		} 
		else if("ViewAttachment".equalsIgnoreCase(request.getParameter("action")))
		{

			String filename = request.getParameter("path").toString();
			
			   // set the http content type to "APPLICATION/OCTET-STREAM
			   response.setContentType("application/download");

			   // initialize the http content-disposition header to
			   // indicate a file attachment with the default filename
			   // "myFile.txt"
			  // String fileName = (String)formDTO.getCompThumbPath();
			   //Filename=\"myFile.txt\"";
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
			/*DMSUtility dms = new DMSUtility();
			String selectFile=request.getParameter("path");
			ArrayList listFileNames = (ArrayList) session.getAttribute("attachment1");	
			for(int i=0;i<listFileNames.size();i++){
				UploadFileDTO uDTO=(UploadFileDTO)listFileNames.get(i);
				String fileName=uDTO.getFileName();
				String filePath = (String) session.getAttribute("FilePath"); 
				if(selectFile.equalsIgnoreCase(fileName)){
					agmpReport.setDocContents(DMSUtility.getDocumentBytes(filePath.concat(fileName)));
					DMSUtility.downloadDocument(response, filePath.concat(fileName), agmpReport.getDocContents());
					byte b[] = dms.getDocumentBytes(filePath.concat(fileName));
					dms.downloadDocument(response, filePath.concat(fileName), b);
					
				}
				
				
			}
		*/
		if("create".equalsIgnoreCase(request.getParameter("page")))
		{	
			forwardAction = "searchresult";
		}
		else if("confirm".equalsIgnoreCase(request.getParameter("page")))
		{
			forwardAction="searchresult";
		}
		return mapping.findForward(forwardAction);
		
		}
		
		else if (request.getParameter("addcomments") != null) {
			ArrayList sroList = new ArrayList();
			//String userId = (String) session.getAttribute("UserId");
			//ADDED BY SHRUTI
			String officeId=(String)session.getAttribute("loggedToOffice");
			try {
				//MODIFIED BY SHRUTI----18TH OCT 2013
				//sroList = sroReportBd.getSroName(userId);
				//sroList=sroReportBd.getJrsdctnSroName(officeId,language);
				//role check
				if(auditConstant.DIG_OFFICE_TYPE_ID.equalsIgnoreCase(sroReportBd.getOfficeTypeId(officeId)))
				{
					sroList=sroReportBd.getSROForDIG(officeId, language);
					
				}
				else if(auditConstant.IG_OFFICE_TYPE_ID.equalsIgnoreCase(sroReportBd.getOfficeTypeId(officeId)))
				{
					sroList=sroReportBd.getAllSroName(language);
				}
				else if(auditConstant.SR_OFFICE_TYPE_ID.equalsIgnoreCase(sroReportBd.getOfficeTypeId(officeId)))
				{
					sroList=sroReportBd.getLoggedInSroName(officeId, language);
				}
				else//DR Role
				sroList=sroReportBd.getJrsdctnSroName(officeId,language);
				//end
				//end
				//sroList = sroReportBd.getSroName(userId);
				agmpReport.setSroList(sroList);
			} catch (Exception sroEx) {
				sroEx.printStackTrace();
			}
			session.removeAttribute("inspectionidlist");				
			session.setAttribute("SRODetails1", agmpReport);
			forwardAction = "inspidsforcomm";
		} else if (request.getParameter("inspid") != null) {
			String inspectionid = request.getParameter("inspid");

			SROReportDetailsDTO sroReportDetailDto = sroInspectionForm.getSroReport();
			sroReportDetailDto.setInspectionId(inspectionid);
			SROReportDetailsDTO newSroDetailsDto = new SROReportDetailsDTO();
			ArrayList listChkDto = sroReportBd.getCheckMapDetails(sroReportDetailDto,language);
			ArrayList listChkOther = sroReportBd.getCheckMapOtherDetails(sroReportDetailDto,language);
			ArrayList lisPendingItem = sroReportBd.getPendingItems(sroReportDetailDto);
			ArrayList lisEmpDetails = sroReportBd.getEmpDetailsList(sroReportDetailDto);
			newSroDetailsDto = sroReportBd.getSroReportDetails(sroReportDetailDto,language);
			ArrayList listSroComm = sroReportBd.getSroComments(sroReportDetailDto);
			ArrayList listDocName = sroReportBd.getDocNames(sroReportDetailDto);
			session.setAttribute("listempdetails", lisEmpDetails);
			session.setAttribute("chkmapdetails", listChkDto);
			session.setAttribute("pendingItems", lisPendingItem);
			session.setAttribute("chkmapother", listChkOther);
			session.setAttribute("sroDetailsDto", newSroDetailsDto);
			session.setAttribute("sroComments", listSroComm);
			session.setAttribute("listDocNames", listDocName);
			forwardAction = "searchresult";
			} else if (request.getParameter("inspidforcomm") != null) {
			String inspectionid = request.getParameter("inspidforcomm");
			session.setAttribute("inspid", inspectionid);

			SROReportDetailsDTO sroReportDetailDto = sroInspectionForm.getSroReport();
			sroReportDetailDto.setInspectionId(inspectionid);
			SROReportDetailsDTO newSroDetailsDto = new SROReportDetailsDTO();
			ArrayList listChkDto = sroReportBd.getCheckMapDetails(sroReportDetailDto,language);
			ArrayList listChkOther = sroReportBd.getCheckMapOtherDetails(sroReportDetailDto,language);
			ArrayList lisPendingItem = sroReportBd.getPendingItems(sroReportDetailDto);
			ArrayList lisEmpDetails = sroReportBd.getEmpDetailsList(sroReportDetailDto);
			newSroDetailsDto = sroReportBd.getSroReportDetails(sroReportDetailDto,language);
			ArrayList listSroComm = sroReportBd.getSroComments(sroReportDetailDto);
			ArrayList listDocName = sroReportBd.getDocNames(sroReportDetailDto);
			session.setAttribute("listempdetails", lisEmpDetails);
			session.setAttribute("chkmapdetails", listChkDto);
			session.setAttribute("pendingItems", lisPendingItem);
			session.setAttribute("chkmapother", listChkOther);
			session.setAttribute("sroDetailsDto", newSroDetailsDto);
			session.setAttribute("sroComments", listSroComm);
			session.setAttribute("listDocNames", listDocName);
			
			forwardAction = "inspaddcomm";
		} 
		//added by shruti-- 3 march 2014
			else if (sroInspectionForm.getActionType().equalsIgnoreCase(
			"reset")){
				SROReportDetailsDTO sroDetailsDto = sroInspectionForm.getSroReport();
		sroDetailsDto.setInspectionId("");
		sroDetailsDto.setInspectionStartDate("");
		sroDetailsDto.setInspectionEndDate("");
		sroDetailsDto.setSroName("");
		sroDetailsDto.setInspectionStatus("");
		sroInspectionForm.setReportId("");
		sroInspectionForm.setInspectionStatus("");
		sroInspectionForm.setSroId("");
		sroInspectionForm.setToDate("");
		sroInspectionForm.setFromDate("");
		forwardAction = "viewandsearch";
			}
		//end
			else if (sroInspectionForm.getActionType().equalsIgnoreCase(
				"searchinspectionid")) {

			ArrayList listSroDto = new ArrayList();
			SROReportDetailsDTO sroDetailsDto = sroInspectionForm.getSroReport();
				sroDetailsDto.setInspectionId(sroInspectionForm.getReportId());
				sroDetailsDto.setInspectionStartDate(sroInspectionForm.getFromDate());
				sroDetailsDto.setInspectionEndDate(sroInspectionForm.getToDate());
				sroDetailsDto.setSroName(sroInspectionForm.getSroId());
				sroDetailsDto.setInspectionStatus(sroInspectionForm.getInspectionStatus());
			listSroDto = sroReportBd.getInspectionIds(sroDetailsDto);
			sroDetailsDto.setInspectionId("");
			sroDetailsDto.setInspectionStartDate("");
			sroDetailsDto.setInspectionEndDate("");
			sroDetailsDto.setSroName("");
			sroDetailsDto.setInspectionStatus("");
			sroInspectionForm.setReportId("");
			sroInspectionForm.setInspectionStatus("");
			sroInspectionForm.setSroId("");
			sroInspectionForm.setToDate("");
			sroInspectionForm.setFromDate("");
			if (listSroDto.size() > 0) {
				request.setAttribute("inspectionidlist", listSroDto);
			} else {
				errorList = new ArrayList();
				errorList.add("<li><font color=" + "red"
						+ ">No Records Found.</font></li>");
				request.setAttribute("errorsList", errorList);
			}
			forwardAction = "viewandsearch";
			// return mapping.findForward("viewandsearch");
		} else if (sroInspectionForm.getActionType().equalsIgnoreCase("back")) {

			forwardAction = "searchresult";
		} else if (sroInspectionForm.getActionType().equalsIgnoreCase(
				"searchidsforcomments")) {
			ArrayList listSroDto = new ArrayList();
			SROReportDetailsDTO sroDetailsDto = sroInspectionForm
					.getSroReport();
			/*
			 * sroDetailsDto.setInspectionId(sroInspectionForm.getReportId());
			 * sroDetailsDto.setInspectionStartDate(sroInspectionForm
			 * .getFromDate());
			 * sroDetailsDto.setInspectionEndDate(sroInspectionForm.getToDate());
			 * sroDetailsDto.setSroName(sroInspectionForm.getSroId());
			 */
			if (sroInspectionForm.getReportId() != null
					&& sroInspectionForm.getReportId().trim().length() > 0) {
				sroDetailsDto.setInspectionId(sroInspectionForm.getReportId());
			} else {
				sroDetailsDto.setInspectionStartDate(sroInspectionForm.getFromDate());
				sroDetailsDto.setInspectionEndDate(sroInspectionForm.getToDate());
				sroDetailsDto.setSroName(sroInspectionForm.getSroId());
			}

			listSroDto = sroReportBd.getInspectionIds(sroDetailsDto);

			if (listSroDto.size() > 0) {
				session.setAttribute("inspectionidlist", listSroDto);
			} else {
				errorList = new ArrayList();
				errorList.add("<li><font color=" + "red"
						+ ">No Records Found</font></li>");
				request.setAttribute("errorsList", errorList);
			}
			forwardAction = "inspidsforcomm";
		} else if (sroInspectionForm.getActionType().equalsIgnoreCase(
				"savecomments")) {
			String user = (String) session.getAttribute("UserId");
			String inspId = (String) session.getAttribute("inspid");
			SROCommentsDTO sroCommentsDto = new SROCommentsDTO();
			sroCommentsDto.setUserId(user);
			sroCommentsDto.setInspectionId(inspId);
			sroCommentsDto.setSroComments(request.getParameter("srcomments"));
			sroReportBd.addSroComments(sroCommentsDto);
			request.setAttribute("inspectionId", inspId);
			// return mapping.findForward("addcommfinal");
			forwardAction = "addcommfinal";
		} else if (sroInspectionForm.getActionType().equalsIgnoreCase(
				"backtoaddcomm")) {
			forwardAction = "inspidsforcomm";
		} else if (sroInspectionForm.getActionType().equalsIgnoreCase("cancel")) {
			session.removeAttribute("inspectionidlist");
			session.removeAttribute("listempdetails");
			session.removeAttribute("chkmapdetails");
			session.removeAttribute("pendingItems");
			session.removeAttribute("chkmapother");
			session.removeAttribute("sroDetailsDto");
			session.removeAttribute("sroComments");
			session.removeAttribute("listDocNames");
			session.removeAttribute("SRODetails1");
			forwardAction = "srhome";
		}

		//System.out.println("for which page i am forwadring " + forwardAction);
		return mapping.findForward(forwardAction);
	}

}