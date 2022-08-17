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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.wipro.igrs.auditinspection.bd.AGMPReportDetailsBD;
import com.wipro.igrs.auditinspection.bd.SROReportDetailsBD;
import com.wipro.igrs.auditinspection.constant.CommonConstant;
import com.wipro.igrs.auditinspection.dto.AGMPReportDetailsDTO;
import com.wipro.igrs.auditinspection.dto.SROCheckMapDTO;
import com.wipro.igrs.auditinspection.dto.SROCommentsDTO;
import com.wipro.igrs.auditinspection.dto.SROEmpDTO;
import com.wipro.igrs.auditinspection.dto.SROPendingItemsDTO;
import com.wipro.igrs.auditinspection.dto.SROReportDetailsDTO;
import com.wipro.igrs.auditinspection.dto.UploadFileDTO;
import com.wipro.igrs.auditinspection.form.SROInspectionForm;
import com.wipro.igrs.auditinspection.rule.AuditInspectionRule;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.caseMonitoring.dto.CaseMonDTO;
import com.wipro.igrs.common.IGRSCommon;
/**
 * @author admin
 * 
 */
public class SROReportDetailsAction extends BaseAction {

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
		
		SROInspectionForm sroInspectionForm = (SROInspectionForm) form;
		SROReportDetailsBD sroReportBd = new SROReportDetailsBD();
		AGMPReportDetailsBD agmpbd = new AGMPReportDetailsBD();
		AGMPReportDetailsDTO agmpReport = new AGMPReportDetailsDTO();
		CommonConstant auditConstant=new CommonConstant();
		AuditInspectionRule rule = null;
		ArrayList errorList = null;
		String strForwadPage = "";
		ArrayList employeelist = null;
		String userId = (String) session.getAttribute("UserId");
		String officeId=(String)session.getAttribute("loggedToOffice");
		String activityid = request.getParameter("ACTID");
		//String userId = session.getAttribute("UserId").toString();
		String language=(String)session.getAttribute("languageLocale");
		sroInspectionForm.setLanguage(language);
		
		//ACTIVITY LOG
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
		//role check
		if(auditConstant.DIG_OFFICE_TYPE_ID.equalsIgnoreCase(sroReportBd.getOfficeTypeId(officeId)))
		{
			if("ACT_14".equalsIgnoreCase(activityid)||"ACT_16".equalsIgnoreCase(activityid)||"ACT_17".equalsIgnoreCase(activityid))
			{strForwadPage = "authchk";
			return mapping.findForward(strForwadPage);
			}
		}
		else if(auditConstant.IG_OFFICE_TYPE_ID.equalsIgnoreCase(sroReportBd.getOfficeTypeId(officeId)))
		{
			if("ACT_14".equalsIgnoreCase(activityid)||"ACT_16".equalsIgnoreCase(activityid)||"ACT_17".equalsIgnoreCase(activityid))
			{strForwadPage = "authchk";
			return mapping.findForward(strForwadPage);
			}
		}
		else if(auditConstant.SR_OFFICE_TYPE_ID.equalsIgnoreCase(sroReportBd.getOfficeTypeId(officeId)))
		{
			if("ACT_17".equalsIgnoreCase(activityid))
			{strForwadPage = "authchk";
			return mapping.findForward(strForwadPage);
			}	
		}
		//END
		}
		
		
		
		ArrayList allemployeeList = new ArrayList();
		ArrayList allpendinglist = new ArrayList();
	
		if(request.getParameter("action")!=null)
		{
			if("Reset".equalsIgnoreCase(request.getParameter("action")))
			{
			session.removeAttribute("listempdetails");
			session.removeAttribute("sroDetailsDto");
			session.removeAttribute("attachment1");
			session.removeAttribute("checkotheritems");
			session.removeAttribute("remarks");
			session.removeAttribute("chkitems");
			session.removeAttribute("pendingItems");
			session.removeAttribute("SroCommentsDto");
			session.removeAttribute("allemployeeList");
			session.removeAttribute("allpendinglist");
			//session.removeAttribute("UserId");
			//session.removeAttribute("loggedToOffice");
			session.removeAttribute("pending");
			SROReportDetailsDTO sroReportDetails=new SROReportDetailsDTO();
			sroInspectionForm.setSroReport(sroReportDetails);
			sroInspectionForm.setActionType("");
			//ADDED BY SHRUTI
			sroInspectionForm.setSrcomments("");
			SROPendingItemsDTO sropendingDTO=new SROPendingItemsDTO();
			sroInspectionForm.setSropendingDTO(0, sropendingDTO);
			
			strForwadPage = "newinspection";
			}
			//added by shruti---18 april 2014
			if("getSelectedItems".equalsIgnoreCase(request.getParameter("action")))
			{
				String selectedItems=request.getParameter("items");
				String[] reqitems=selectedItems.split(",");
				getUpdatedCheckListItems(request, agmpReport,sroReportBd, session,reqitems);
				//sroInspectionForm
			}
			//end
			
			//added by ShreeraJ
			if(request.getParameter("action").equalsIgnoreCase("ViewAttachment"))
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
  			   response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(filename,"UTF-8"));
  			   
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
				
			strForwadPage = "newinspection";
			}
			else if("confirm".equalsIgnoreCase(request.getParameter("page")))
			{
				strForwadPage="inspectionModify";
			}
			return mapping.findForward(strForwadPage);
			}
		}
		
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		
		}
		
		if (request.getParameter("employeeId") != null) {
			String strEmployeeId = request.getParameter("employeeId");

			SROEmpDTO sroEmpDto = sroReportBd.getEmpDetails(strEmployeeId);

			StringBuffer sbEmployee = new StringBuffer();
			if (sroEmpDto != null) {

				if (sroEmpDto.getEmployeename() != null) {
					sbEmployee.append(sroEmpDto.getEmployeename() + "|");
				} else {
					sbEmployee.append(" " + "|");
				}
				if (sroEmpDto.getDesignation() != null) {
					sbEmployee.append(sroEmpDto.getDesignation() + "|");
				} else {
					sbEmployee.append(" " + "|");
				}
				if (sroEmpDto.getJoinDate() != null) {
					sbEmployee.append(sroEmpDto.getJoinDate() + "|");
				} else {
					sbEmployee.append(" " + "|");
				}
				if (sroEmpDto.getSeperarionDate() != null) {
					sbEmployee.append(sroEmpDto.getSeperarionDate() + "|");
				} else {
					sbEmployee.append(" " + "|");
				}
			}
			
			out.print(sbEmployee.toString());
			return null;
		} 
		 else if (request.getParameter("start") != null) {
			try {
				session.removeAttribute("listempdetails");
				session.removeAttribute("sroDetailsDto");
				session.removeAttribute("attachment1");
				session.removeAttribute("checkotheritems");
				session.removeAttribute("remarks");
				session.removeAttribute("chkitems");
				session.removeAttribute("pendingItems");
				session.removeAttribute("SroCommentsDto");
				session.removeAttribute("allemployeeList");
				session.removeAttribute("allpendinglist");
				//added by shruti
				sroInspectionForm.setModifyFlag("");
				sroInspectionForm.setCounter(0);
				sroInspectionForm.setEmpId("");
				//end
				String strForwardPage = getSRONames(request, agmpReport,sroReportBd, session);
				String strFwdPage = getDistrictNmaes(request, agmpReport,sroReportBd, session);
				boolean flag = getCheckListItems(request, agmpReport,sroReportBd, session);
				boolean flagOther = getCkeckListOtherItems(request, agmpReport,sroReportBd, session);

			} catch (RuntimeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				return mapping.findForward("ErrorPage");
			}

			// return mapping.findForward("newinspection");
			strForwadPage = "newinspection";
		} 
		else if (sroInspectionForm.getActionType() != null
				&& sroInspectionForm.getActionType().equalsIgnoreCase("returnattachment")) 
		{
			session.removeAttribute("checkotheritems");
			session.removeAttribute("remarks");
			SROReportDetailsDTO sroReportDetails = sroInspectionForm.getSroReport();
			employeelist = sroInspectionForm.getEmployeeList();
			for (int i = 0; i < employeelist.size(); i++) 
			{
				SROEmpDTO empdto = (SROEmpDTO) employeelist.get(i);
				if ((empdto != null && empdto.getEmployeename() == null)
						|| (empdto != null && empdto.getEmployeename().equals("")))
				{
				} 
				else
				{
					allemployeeList.add(empdto);
				}
			}
			ArrayList pendinglist = sroInspectionForm.getPendinglist();

			for (int i = 0; i < pendinglist.size(); i++)
			{
				SROPendingItemsDTO itemsDTO = (SROPendingItemsDTO) pendinglist.get(i);
				if ((itemsDTO != null && itemsDTO.getPendingItem() == null)
						|| (itemsDTO != null && itemsDTO.getPendingItem().equals("")))
				{
				}
				else 
				{
					allpendinglist.add(itemsDTO);
				}
			}
			session.setAttribute("allemployeeList", allemployeeList);
			session.setAttribute("allpendinglist", allpendinglist);
			sroReportDetails.setListSROEmp(allemployeeList);

			// Get the Upload details list to form the Object with the file
			// names
			ArrayList listFileNames = (ArrayList) session.getAttribute("attachment1");
			String filePath = (String) session.getAttribute("FilePath");

			// Get SroComments from the UI
			ArrayList listComents = new ArrayList();
			String sroComments = request.getParameter("srcomments");
			SROCommentsDTO sroCommentsDto = new SROCommentsDTO();
			sroCommentsDto.setSroComments(sroComments);
			String userid = (String) session.getAttribute("user");
			sroCommentsDto.setUserId(userid);
			listComents.add(sroCommentsDto);
			sroReportDetails.setListSROComments(listComents);
			session.setAttribute("SroCommentsDto", sroCommentsDto);
			// Get the Pending task to form the Object
			sroReportDetails.setListSROPendingItem(allpendinglist);

			
			//////////////////////modified by shruti-----29 april 2014
			// Get the check list information to form the Object START
			ArrayList listCheckdDto = new ArrayList();
			SROCheckMapDTO sroCheckDto = null;
			String chkItem[] = request.getParameterValues("checkItem");
			ArrayList checklistnames=sroReportBd.getChkListNames(chkItem,language);
			ArrayList finalchklistnames=new ArrayList();
			if (checklistnames != null)
			{
				for (int i = 0; i < checklistnames.size(); i++) 
				{
					SROCheckMapDTO listDto = (SROCheckMapDTO) checklistnames.get(i);
					finalchklistnames.add(listDto.getItemId());
				}
			}
			String[] newchkItem = new String[finalchklistnames.size()];
			int index = 0;
			for (Object value : finalchklistnames) {
				newchkItem[index] = (String) value;
			  index++;
			}		
			session.setAttribute("chkitems", newchkItem);
			
			//if(chkItem!=null)
			if(newchkItem!=null)
			{
			for (int i = 0; i < chkItem.length; i++)
			{
				sroCheckDto = new SROCheckMapDTO();
				sroCheckDto.setItemId(chkItem[i]);
				sroCheckDto.setItemRemarks("");
				sroCheckDto.setChkVal("yes");
				listCheckdDto.add(sroCheckDto);
			}
			}
			sroReportDetails.setListSROCheckMap(listCheckdDto);

			String chkOtherItem[] = request.getParameterValues("checkOther");
			String chkOtherRemarks[] = request.getParameterValues("remarks");
			if (chkOtherItem != null && chkOtherRemarks != null) {
				try {
					for (int i = 0; i < chkOtherItem.length; i++) {
						sroCheckDto = new SROCheckMapDTO();
						sroCheckDto.setItemId(chkOtherItem[i]);
						sroCheckDto.setItemRemarks(chkOtherRemarks[i]);
						sroCheckDto.setChkVal("");
						listCheckdDto.add(sroCheckDto);
					}
				} catch (Exception e) {
					e.printStackTrace();

				}
				if (chkOtherRemarks.length > 0) {
					session.setAttribute("checkotheritems", chkOtherItem);
					session.setAttribute("remarks", chkOtherRemarks);
					sroReportDetails.setListSROCheckMap(listCheckdDto);
				}
			}

			sroReportDetails = sroInspectionForm.getSroReport();
			session.setAttribute("sroDetailsDto", sroReportDetails);
			strForwadPage = "newinspection";
		} else if (sroInspectionForm.getActionType() != null
				&& sroInspectionForm.getActionType().equalsIgnoreCase(
						"newinspection")) {
		
			session.removeAttribute("checkotheritems");
			session.removeAttribute("remarks");
		

			// First get the Report Details,Income Status and RTI details will
			// be formed in the Object
			SROReportDetailsDTO sroReportDetails = sroInspectionForm.getSroReport();

			employeelist = sroInspectionForm.getEmployeeList();
			
			for (int i = 0; i < employeelist.size(); i++) {
				SROEmpDTO empdto = (SROEmpDTO) employeelist.get(i);
				if ((empdto != null && empdto.getEmployeename() == null)
						|| (empdto != null && empdto.getEmployeename().equals(
								""))) {
				} else {

					allemployeeList.add(empdto);
				}
			}
			System.out.println(">>>>>>>>>>>>>>"+session.getAttribute("allpendinglist"));
			ArrayList pendinglist = sroInspectionForm.getPendinglist();

			for (int i = 0; i < pendinglist.size(); i++) 
			{
				SROPendingItemsDTO itemsDTO = (SROPendingItemsDTO) pendinglist.get(i);
				if ((itemsDTO != null && itemsDTO.getPendingItem() == null)
						|| (itemsDTO != null && itemsDTO.getPendingItem().equals(""))) 
				{
				} 
				else 
				{        
					allpendinglist.add(itemsDTO);
				}
			}

			session.setAttribute("allemployeeList", allemployeeList);
			session.setAttribute("allpendinglist", allpendinglist);

			sroReportDetails.setListSROEmp(allemployeeList);

			// Get the Upload details list to form the Object with the file
			// names
			ArrayList listFileNames = (ArrayList) session.getAttribute("attachment1");
			String filePath = (String) session.getAttribute("FilePath");

			// Get SroComments from the UI
			ArrayList listComents = new ArrayList();
			String sroComments = request.getParameter("srcomments");
			SROCommentsDTO sroCommentsDto = new SROCommentsDTO();
			sroCommentsDto.setSroComments(sroComments);
			String userid = (String) session.getAttribute("user");
			sroCommentsDto.setUserId(userid);
			listComents.add(sroCommentsDto);
			sroReportDetails.setListSROComments(listComents);
			session.setAttribute("SroCommentsDto", sroCommentsDto);
			// Get the Pending task to form the Object
			sroReportDetails.setListSROPendingItem(allpendinglist);
			// Get the check list information to form the Object START
			ArrayList listCheckdDto = new ArrayList();
			SROCheckMapDTO sroCheckDto = null;
			
			String chkItem[] = request.getParameterValues("checkItem");
			ArrayList checklistnames=new ArrayList();
			checklistnames=sroReportBd.getChkListNames(chkItem,language);
			ArrayList finalchklistnames=new ArrayList();
			if (checklistnames != null)
			{
				for (int i = 0; i < checklistnames.size(); i++) 
				{
					SROCheckMapDTO listDto = (SROCheckMapDTO) checklistnames.get(i);
					finalchklistnames.add(listDto.getItemId());
				}
			}
			String[] newchkItem = new String[finalchklistnames.size()];
			int index = 0;
			for (Object value : finalchklistnames) {
				newchkItem[index] = (String) value;
			  index++;
			}		
			session.setAttribute("chkitems", newchkItem);
			//if(chkItem!=null){
			if(newchkItem!=null){
			for (int i = 0; i < chkItem.length; i++) {
				// System.out.println("ade changes here to test!!!!!! :" + chkItem[i]);
				sroCheckDto = new SROCheckMapDTO();
				sroCheckDto.setItemId(chkItem[i]);
				sroCheckDto.setItemRemarks("");
				//ADDED BY SHRUTI---23 APRIL 2014
				if("en".equalsIgnoreCase(language))
				{
					sroCheckDto.setChkVal("yes");
				}
				else
				{
					sroCheckDto.setChkVal("हां");
				}
				
				listCheckdDto.add(sroCheckDto);
			}}
			sroReportDetails.setListSROCheckMap(listCheckdDto);

			String chkOtherItem[] = request.getParameterValues("checkOther");
			String chkOtherRemarks[] = request.getParameterValues("remarks");
			if (chkOtherItem != null && chkOtherRemarks != null) {
				try {
					for (int i = 0; i < chkOtherItem.length; i++) {
						sroCheckDto = new SROCheckMapDTO();
						sroCheckDto.setItemId(chkOtherItem[i]);
						sroCheckDto.setItemRemarks(chkOtherRemarks[i]);
						sroCheckDto.setChkVal("");
						listCheckdDto.add(sroCheckDto);
						// System.out.println("Valiating in Action i" + i);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (chkOtherRemarks.length > 0) {
					session.setAttribute("checkotheritems", chkOtherItem);
					session.setAttribute("remarks", chkOtherRemarks);
					sroReportDetails.setListSROCheckMap(listCheckdDto);
				}
			}

			rule = new AuditInspectionRule();
			sroReportDetails = sroInspectionForm.getSroReport();
			//COMMENTED BY SHRUTI---- 9TH DEC 2013
			errorList = rule.validateNewInspection(sroReportDetails);
			//END
			//System.out.println("Valiating in Action ");

			if (rule.isError()) {
				session.setAttribute("sroDetailsDto", sroReportDetails);
				request.setAttribute("errorsList", errorList);
				
				strForwadPage = "newinspection";
			} else if (session.getAttribute("attachment1") == null) {
				
				session.setAttribute("sroDetailsDto", sroReportDetails);
				//errorList.add("Please Attach Documents/दस्तावेज संलग्न करें");
				errorList
				.add("<li><font color="
						+ "red"
						+ ">Please Attach Documents./दस्तावेज संलग्न करें</font></li>");
				request.setAttribute("errorsList", errorList);
				strForwadPage = "newinspection";
			} else {
				// Calling BD Method
				sroReportDetails.setInspectedBy((String) session.getAttribute("UserId"));
				// setting SRO Name and Didtrict Name to SROReportDetailsDTO
				//AGMPReportDetailsDTO agmpDto = (AGMPReportDetailsDTO) session.getAttribute("SRODetails1");
				//ArrayList listSroName = agmpDto.getSroList();
				//AGMPReportDetailsDTO agmpDto1 = (AGMPReportDetailsDTO) listSroName.get(0);
				//sroReportDetails.setSroName(agmpDto1.getSroName());
				SROReportDetailsDTO sroReportDistName = (SROReportDetailsDTO) session.getAttribute("districtNameList");
				ArrayList listDistrictName = sroReportDistName.getListDistrict();
				SROReportDetailsDTO sroReportDistName1 = (SROReportDetailsDTO) listDistrictName.get(0);
				sroReportDetails.setDistrictName(sroReportDistName1.getDistrictName());

				if(sroReportDetails.getCashDepositBank().equalsIgnoreCase("Y"))
				{
					sroReportDetails.setCashDepositBankYesNo("Yes");
				}
				else
				{
					sroReportDetails.setCashDepositBankYesNo("No");
				}
				if(language.equalsIgnoreCase("hi"))
				{
					sroReportDetails.setCfyRevenueFromDate(hindiMonth(sroReportDetails.getCfyRevenueFromDate()));
					sroReportDetails.setCfyRevenueToDate(hindiMonth(sroReportDetails.getCfyRevenueToDate()));
					sroReportDetails.setCfyRevenueColFromDate(hindiMonth(sroReportDetails.getCfyRevenueColFromDate()));
					sroReportDetails.setCfyRevenueColToDate(hindiMonth(sroReportDetails.getCfyRevenueColToDate()));
					sroReportDetails.setPfyRevenueFromDate(hindiMonth(sroReportDetails.getPfyRevenueFromDate()));
					sroReportDetails.setPfyRevenueToDate(hindiMonth(sroReportDetails.getPfyRevenueToDate()));

				}
				
				session.setAttribute("sroDetailsDto", sroReportDetails);
				
				strForwadPage = "inspectionModify";
			}
		} 
		else if (sroInspectionForm.getActionType() != null
				&& sroInspectionForm.getActionType().equalsIgnoreCase("modify"))
		{
			SROReportDetailsDTO sroReportDto = (SROReportDetailsDTO) session.getAttribute("sroDetailsDto");
			ArrayList commentList=sroReportDto.getListSROComments();
			for(int i=0;i<commentList.size();i++)
			{
				SROCommentsDTO sroCommentsDto1=new SROCommentsDTO();
				sroCommentsDto1=(SROCommentsDTO)commentList.get(i);

			sroInspectionForm.setSrcomments(sroCommentsDto1.getSroComments());
			}
			
			sroInspectionForm.setSroReport(sroReportDto);
			
			//ADDED BY SHRUTI---26 FEB 2014
			if(session.getAttribute("allemployeeList")!=null){
				ArrayList emplist=(ArrayList)session.getAttribute("allemployeeList");
				System.out.println(">>>>>"+emplist.toString());
				SROEmpDTO empdto=new SROEmpDTO();
				for (int i = 0; i < emplist.size(); i++) 
				{
					 empdto = (SROEmpDTO) emplist.get(i);
					if (empdto.getUserId() != null)
					{
						String strEmployeeId = empdto.getUserId();
						SROEmpDTO sroEmpDto = sroReportBd.getEmpDetails(strEmployeeId);
						request.setAttribute("employeeId", strEmployeeId);
					} 
				} 
				allemployeeList.add(empdto);
				}
			
			session.setAttribute("allemployeeList", allemployeeList);
			sroInspectionForm.setModifyFlag("Y");
			sroInspectionForm.setEmpId((String)request.getAttribute("employeeId"));
			sroInspectionForm.setCounter(allemployeeList.size());
			
			//ADDED BY SHRUTI----24 APRIL 2014
			if(session.getAttribute("allpendinglist")!=null)
			{
			ArrayList pendinglist = (ArrayList)session.getAttribute("allpendinglist");

			for (int i = 0; i < pendinglist.size(); i++)
			{
				SROPendingItemsDTO itemsDTO = (SROPendingItemsDTO) pendinglist.get(i);
				if ((itemsDTO != null && itemsDTO.getPendingItem() == null)
						|| (itemsDTO != null && itemsDTO.getPendingItem().equals("")))
				{
				}
				else 
				{
					allpendinglist.add(itemsDTO);
				}
			}
			}
			//END
			strForwadPage = "newinspection";
			
		}
		
	else if (sroInspectionForm.getActionType() != null
				&& sroInspectionForm.getActionType().equalsIgnoreCase("submit"))
		{
			SROReportDetailsDTO sroReportDto = (SROReportDetailsDTO) session.getAttribute("sroDetailsDto");
			ArrayList listFileNames = (ArrayList) session.getAttribute("attachment1");
		
			//String filePath="C://Users/vi836878/Downloads/";
			String filePath = (String) session.getAttribute("FilePath"); 
			//String filePath="D://upload/10/";
			/*File folder=new File(filePath);
			if(!folder.exists())
			{
				folder.mkdir();
			}
*/			sroReportDto.setUserId(userId);
			sroReportDto.setLoggedInOffice(officeId);
			String reportId = sroReportBd.saveSroInspectionDetails(sroReportDto, listFileNames, filePath);

			
			if (reportId != null) 
			{
				for(int i=0;i<listFileNames.size();i++){
					UploadFileDTO uDTO = (UploadFileDTO) listFileNames.get(i);
					String filePathLen=uDTO.getFilePath();
					String docFilePath=uDTO.getFilePath().substring(0, filePathLen.length()-(uDTO.getFileName().length()+2)).concat(reportId.concat("\\"+uDTO.getFileName()));
					FormFile file = uDTO.getTheFile();
					 File folder = new File(uDTO.getFilePath().substring(0, filePathLen.length()-(uDTO.getFileName().length()+2)).concat(reportId));
					 if (!folder.exists()) 
					 {
			              folder.mkdirs();
					 }
					try {
						File newFile = new File(docFilePath);
						
						FileOutputStream fos = new FileOutputStream(newFile);
						fos.write(file.getFileData());
						fos.close();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					
					/*DMSUtility dms=new DMSUtility();
					byte b[] = dms.getDocumentBytes(docFilePath);
					dms.downloadDocument(response, docFilePath, b);*/
				}
				if(listFileNames.size()>=1){
					
					UploadFileDTO uDTO1 = (UploadFileDTO) listFileNames.get(0);
					String filePathLen=uDTO1.getFilePath();
					 File folder1 = new File(uDTO1.getFilePath().substring(0, filePathLen.length()-(uDTO1.getFileName().length())));
					 System.out.println(folder1.delete());
					 if(folder1.list()!=null){
					 String arr[]= folder1.list();
					 for(String s:arr)
					 {
						 File f=new File(folder1.getPath(),s);
						 f.delete();
						 
					 }
					 folder1.delete();
					 }
					}
				
				request.setAttribute("reportid", reportId);
				strForwadPage = "inspectionconfirm";
				
			} 
			else
			{
				strForwadPage = "errorpage";
			}
		} else if (sroInspectionForm.getActionType() != null && sroInspectionForm.getActionType().equalsIgnoreCase("cancel")) {

			session.removeAttribute("listempdetails");
			session.removeAttribute("sroDetailsDto");
			session.removeAttribute("attachment1");

			session.removeAttribute("checkotheritems");
			session.removeAttribute("remarks");
			session.removeAttribute("chkitems");
			session.removeAttribute("pendingItems");
			session.removeAttribute("SroCommentsDto");

			sroInspectionForm = null;
			strForwadPage = "srhome";
		} else if (sroInspectionForm.getActionType() != null && sroInspectionForm.getActionType().equalsIgnoreCase("reset")) {
			session.removeAttribute("listempdetails");
			session.removeAttribute("sroDetailsDto");
			session.removeAttribute("attachment1");
			session.removeAttribute("checkotheritems");
			session.removeAttribute("remarks");
			session.removeAttribute("chkitems");
			session.removeAttribute("pendingItems");
			session.removeAttribute("SroCommentsDto");
			session.removeAttribute("allemployeeList");
			session.removeAttribute("allpendinglist");
			session.removeAttribute("UserId");
			//session.removeAttribute("loggedToOffice");
			/*StringBuffer sbEmployee = new StringBuffer();
			sbEmployee.delete(0,sbEmployee.length());*/
			//request.removeAttribute("employeeId");
			//allemployeeList=null;
			
			//String strEmployeeId = request.getParameter("employeeId");
			
		/*	SROEmpDTO sroEmpDto=sroReportBd.getEmpDetails(strEmployeeId);; 
			sroEmpDto.setEmployeename("");
			sroEmpDto.setDesignation("");
			sroEmpDto.setJoinDate("");
			sroEmpDto.setSeperarionDate("");
			
*/
			session.removeAttribute("pending");
			
			strForwadPage = "newinspection";
		}
		//System.out.println("Whic is the forward page at least " + strForwadPage);

		return mapping.findForward(strForwadPage);
	}

	/**
	 * @param request
	 * @param agmpreportdetailsdto
	 * @param sroDetailsBd
	 * @param session
	 * @return String
	 */
	private String getSRONames(HttpServletRequest request,
			AGMPReportDetailsDTO agmpreportdetailsdto,
			SROReportDetailsBD sroDetailsBd, HttpSession session) {
		String strForward = "newinspection";
		ArrayList sroList = null;
		// String offTypeId = "OT1"; //Here we have to fetch The user Logged in
		// (DR/SR)
		String userId = (String) session.getAttribute("UserId");
		String OffId=(String)session.getAttribute("loggedToOffice");
		String language=(String)session.getAttribute("languageLocale");
		try {
			//moidified by shruti-18th oct 2013
			//sroList = sroDetailsBd.getSroName(userId);
			sroList = sroDetailsBd.getJrsdctnSroName(OffId,language);
			agmpreportdetailsdto.setSroList(sroList);
		} catch (Exception sroEx) {
			sroEx.printStackTrace();
		}
		session.setAttribute("SRODetails1", agmpreportdetailsdto);
		return strForward;
	}

	/**
	 * @param request
	 * @param agmpreportdetailsdto
	 * @param sroDetailsBd
	 * @param session
	 * @return String
	 */
	private String getDistrictNmaes(HttpServletRequest request,
			AGMPReportDetailsDTO agmpreportdetailsdto,
			SROReportDetailsBD sroDetailsBd, HttpSession session) {
		String strForward = "newinspection";
		String userId = (String) session.getAttribute("UserId");
		String OffId=(String)session.getAttribute("loggedToOffice");
		String language=(String)session.getAttribute("languageLocale");
		//modified by shruti-----18th oct 2013
		ArrayList sroList = null;
		String officeId="";
		sroList=sroDetailsBd.getJrsdctnSroName(OffId,language);
		//sroList = sroDetailsBd.getSroName(userId);
		if(sroList.size()>0){
			AGMPReportDetailsDTO agmpReport = (AGMPReportDetailsDTO) sroList.get(0);
			officeId = agmpReport.getSroId();
		}
		
		ArrayList listDistrictName = sroDetailsBd.getDistrict(officeId,language);
		SROReportDetailsDTO sroReportDto = new SROReportDetailsDTO();
		sroReportDto.setListDistrict(listDistrictName);
		session.setAttribute("districtNameList", sroReportDto);
		return strForward;
	}

	/**
	 * @param request
	 * @param agmpreportdetailsdto
	 * @param sroDetailsBd
	 * @param session
	 * @return
	 */
	private boolean getCheckListItems(HttpServletRequest request,
			AGMPReportDetailsDTO agmpreportdetailsdto,
			SROReportDetailsBD sroDetailsBd, HttpSession session) {
		boolean flag = true;
		String language=(String)session.getAttribute("languageLocale");
		ArrayList listCheckList = sroDetailsBd.getChkListItems(language);
		session.setAttribute("checklistitems", listCheckList);
		return flag;

	}

	//addded by shruti----18 april 2014
	private boolean getUpdatedCheckListItems(HttpServletRequest request,
			AGMPReportDetailsDTO agmpreportdetailsdto,
			SROReportDetailsBD sroDetailsBd, HttpSession session,String[] itemnames) {
		boolean flag = true;
		String language=(String)session.getAttribute("languageLocale");
		ArrayList listCheckList = sroDetailsBd.getUpdatedChkListItems(itemnames,language);
		session.setAttribute("checklistitems", listCheckList);
		return flag;

	}
	//end
	/**
	 * @param request
	 * @param agmpreportdetailsdto
	 * @param sroDetailsBd
	 * @param session
	 * @return
	 */
	private boolean getCkeckListOtherItems(HttpServletRequest request,
			AGMPReportDetailsDTO agmpreportdetailsdto,
			SROReportDetailsBD sroDetailsBd, HttpSession session) {
		boolean flag = true;
		String language=(String)session.getAttribute("languageLocale");
		ArrayList listCheckListOther = sroDetailsBd.getChkDetailsOthers(language);
		session.setAttribute("checklistothers", listCheckListOther);
		return flag;
	}

	private String hindiMonth(String enmon)
	{
		String hinmon="";
		if(enmon.equalsIgnoreCase("January"))
		{
			hinmon="जनवरी";
		}
		else if(enmon.equalsIgnoreCase("February"))
		{
			hinmon="फरवरी";
		}
		else if(enmon.equalsIgnoreCase("March"))
		{
			hinmon="मार्च";
		}
		else if(enmon.equalsIgnoreCase("April"))
		{
			hinmon="अप्रैल";
		}
		else if(enmon.equalsIgnoreCase("May"))
		{
			hinmon="मई";
		}
		else if(enmon.equalsIgnoreCase("June"))
		{
			hinmon="जून";
		}
		else if(enmon.equalsIgnoreCase("July"))
		{
			hinmon="जुलाई";
		}
		else if(enmon.equalsIgnoreCase("August"))
		{
			hinmon="अगस्त";
		}
		else if(enmon.equalsIgnoreCase("September"))
		{
			hinmon="सितंबर";
		}
		else if(enmon.equalsIgnoreCase("October"))
		{
			hinmon="अक्टूबर";
		}
		else if(enmon.equalsIgnoreCase("November"))
		{
			hinmon="नवंबर";
		}
		else if(enmon.equalsIgnoreCase("December"))
		{
			hinmon="दिसंबर";
		}
		return hinmon;
	}
}