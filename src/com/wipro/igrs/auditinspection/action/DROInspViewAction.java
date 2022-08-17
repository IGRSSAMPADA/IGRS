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

import com.wipro.igrs.auditinspection.bd.DROInspectionBD;
import com.wipro.igrs.auditinspection.bd.SROReportDetailsBD;
import com.wipro.igrs.auditinspection.constant.CommonConstant;
import com.wipro.igrs.auditinspection.dto.DROReportDetailsDTO;
import com.wipro.igrs.auditinspection.form.DROInspViewForm;
import com.wipro.igrs.auditinspection.rule.AuditInspectionRule;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.common.IGRSCommon;


/** 
 * MyEclipse Struts
 * Creation date: 07-03-2008
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class DROInspViewAction extends BaseAction {
	/*
	 * Generated Methods
	 */

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		
	//	HttpSession session   =  request.getSession();
		DROInspViewForm droViewForm    =  (DROInspViewForm)form;
		DROReportDetailsDTO  droReportDto  = droViewForm.getDroReportDto();
		DROInspectionBD   droReportBD      =  new DROInspectionBD();
		AuditInspectionRule rule = null;
		ArrayList errorList = null;
		String strForward  = null;
		strForward = "droinspsearch";
		String language=(String)session.getAttribute("languageLocale");
		droViewForm.setLanguage(language);
		String activityid = request.getParameter("ACTID");
		String userId = session.getAttribute("UserId").toString();
		String officeId=(String)session.getAttribute("loggedToOffice");
		CommonConstant auditConstant=new CommonConstant();
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

		if(request.getParameter("start") != null){
			session.removeAttribute("inspectionidlist");
			strForward = getDRONames(request, droReportDto,droReportBD, session,language);
		}else if(request.getParameter("inspid") != null){
			String inspId = request.getParameter("inspid");
			DROReportDetailsDTO droDetailsDto  = droReportBD.getDroReportDetails(inspId,language);
			ArrayList listEmpDetails    =  droReportBD.getDROEmpDetails(inspId);
			ArrayList listReasonDetails =  droReportBD.getDROReasonDetails(inspId);
			//ArrayList listPendingDetails = droReportBD.getPendingDetails(inspId);
			ArrayList listPenDocDetails  = droReportBD.getDocumentNames(inspId);
			
			request.setAttribute("listDocNamesPen",listPenDocDetails);
			//request.setAttribute("pendingDto",listPendingDetails);
			request.setAttribute("reasonMap",listReasonDetails);
			request.setAttribute("droEmpDetails",listEmpDetails);
			request.setAttribute("droDetailsDto",droDetailsDto);
			//System.out.println("Search DRO insp id");
			strForward = "drosearchresult";
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
			   strForward="drosearchresult";
		return mapping.findForward(strForward);
		
		}
		
		else if(droViewForm.getActionType() != null && droViewForm.getActionType().equalsIgnoreCase("droReportSearch"))
		{
			
			ArrayList listInspIds  =  droReportBD.getDroInspIds(droReportDto);
			if(listInspIds.size() > 0){
			   session.setAttribute("inspectionidlist", listInspIds);
			   
			}
			if(listInspIds.size() == 0){
				session.removeAttribute("inspectionidlist");
				errorList = new ArrayList();
				if("en".equalsIgnoreCase(language))
						{
				errorList.add("<li><font color=" + "red"+ ">No Records Present.</font></li>");
				request.setAttribute("errorsList", errorList);
						}
				else
				{
					errorList.add("<li><font color=" + "red"+ ">कोई रिकार्ड उपलबध नहीं |</font></li>");
					request.setAttribute("errorsList", errorList);	
				}
			}
			
			strForward = "droinspsearch";
		}
		//added by shruti----3 march 2014
		else if(droViewForm.getActionType() != null && droViewForm.getActionType().equalsIgnoreCase("reset"))
		{
			
			droReportDto.setInspStartDate("");
			droReportDto.setInspToDate("");
			droReportDto.setDroInspId("");
			droReportDto.setDroId("");
			session.removeAttribute("inspectionidlist");
			strForward = getDRONames(request, droReportDto,droReportBD, session,language);
			strForward = "droinspsearch";
		}
		//end
		
		
		return mapping.findForward(strForward);
	}
	
	private String getDRONames(HttpServletRequest request,
			DROReportDetailsDTO myDTO,DROInspectionBD droDetailsBd,
			HttpSession session,String language) {
		
		String officeId=(String)session.getAttribute("loggedToOffice");
		CommonConstant auditConstant=new CommonConstant();
		SROReportDetailsBD sroReportBd = new SROReportDetailsBD();
		//ArrayList droList = droDetailsBd.getViewDRONames(language);
		ArrayList droList =new ArrayList();
		//role check
		if(auditConstant.DIG_OFFICE_TYPE_ID.equalsIgnoreCase(sroReportBd.getOfficeTypeId(officeId)))
		{
			droList=droDetailsBd.getDRONames(language, officeId);
			myDTO.setDroList(droList);
			session.setAttribute("DRONames", myDTO);
			return "droinspsearch";
		}
		else if(auditConstant.IG_OFFICE_TYPE_ID.equalsIgnoreCase(sroReportBd.getOfficeTypeId(officeId)))
		{
			droList=droDetailsBd.getViewDRONames(language);
			myDTO.setDroList(droList);
			session.setAttribute("DRONames", myDTO);
			return "droinspsearch";
		}
		else if(auditConstant.SR_OFFICE_TYPE_ID.equalsIgnoreCase(sroReportBd.getOfficeTypeId(officeId)))
		{
			return "viewChk";
		}
		else
		{//DR Role
			droList=droDetailsBd.getLoggedInDRONames(language, officeId);
		myDTO.setDroList(droList);
		session.setAttribute("DRONames", myDTO);
		return "droinspsearch";
		}
		//end
		
		/*myDTO.setDroList(droList);
		session.setAttribute("DRONames", myDTO);
		return "droinspsearch";*/
	}
}