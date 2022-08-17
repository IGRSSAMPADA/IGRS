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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.wipro.igrs.DMSConnection.DMSUtility;
import com.wipro.igrs.auditinspection.bd.DROInspectionBD;
import com.wipro.igrs.auditinspection.bd.SROReportDetailsBD;
import com.wipro.igrs.auditinspection.constant.CommonConstant;
import com.wipro.igrs.auditinspection.dto.DROEmpMapDTO;
import com.wipro.igrs.auditinspection.dto.DROInspItemMap;
import com.wipro.igrs.auditinspection.dto.DROPendingTaskDTO;
import com.wipro.igrs.auditinspection.dto.DROReasonMapDTO;
import com.wipro.igrs.auditinspection.dto.DROReportDetailsDTO;
import com.wipro.igrs.auditinspection.dto.UploadFileDTO;
import com.wipro.igrs.auditinspection.form.DROInspectionForm;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.common.IGRSCommon;

public class DROInspectionAction extends BaseAction {
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
	
	Logger logger = (Logger) Logger.getLogger(DROInspectionAction.class);
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,HttpSession session) {

	//	HttpSession session = request.getSession(true);
		DROInspectionForm droInspectionForm = (DROInspectionForm) form;
		DROReportDetailsDTO droReport = new DROReportDetailsDTO();
		CommonConstant auditConstant=new CommonConstant();
		SROReportDetailsBD sroReportBd = new SROReportDetailsBD();
		DROInspectionBD droReportBD = new DROInspectionBD();
		String strForwadPage = "";
		ArrayList allemployeeList = new ArrayList();
		ArrayList allpendinglist = new ArrayList();
		ArrayList allreasonList=new ArrayList();
		PrintWriter out = null;
		String officeId=(String)session.getAttribute("loggedToOffice");
		String language=(String)session.getAttribute("languageLocale");
		DMSUtility dms = new DMSUtility();
		//added by shruti
        droInspectionForm.setLanguage(language);
       
        droInspectionForm.getDroReport().setEmpCount("0");
        //end
        
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
		//role check
		if(auditConstant.DIG_OFFICE_TYPE_ID.equalsIgnoreCase(sroReportBd.getOfficeTypeId(officeId)))
		{
			if("ACT_16".equalsIgnoreCase(activityid)||"ACT_14".equalsIgnoreCase(activityid))
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
		else if(auditConstant.DR_OFFICE_TYPE_ID.equalsIgnoreCase(sroReportBd.getOfficeTypeId(officeId)))
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
		if(request.getParameter("action")!=null)
		{
		if("ViewAttachment".equalsIgnoreCase(request.getParameter("action")))
		{
			String filename = request.getParameter("path").toString();
			   response.setContentType("application/download");
			   try
			   {
			   response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(filename,"UTF-8"));
			   
			   File fileToDownload = new File(filename);
			   FileInputStream fileInputStream = new FileInputStream(fileToDownload);
			   OutputStream outstream = response.getOutputStream();
			   byte[] buf = new byte[2048];

			   int readNum;
			   while ((readNum=fileInputStream.read(buf))!=-1)
			   {
				   outstream.write(buf,0,readNum);
			   }
			   fileInputStream.close();
			   out.close();
			   }catch( Exception e)
			   {
				   e.printStackTrace();
			   }
			
		if("create".equalsIgnoreCase(request.getParameter("page")))
		{	
		strForwadPage = "newdroinsp";
		}
		else if("confirm".equalsIgnoreCase(request.getParameter("page")))
		{
			strForwadPage="droInspConfirm";
		}
		return mapping.findForward(strForwadPage);
		}
		}
		
		///////------------- writer COMMENTED BY SHRUTI----28 FEB 2014
		try {
			out = response.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
//---------------------------END
		
		//added by shruti---2 marc 2014
		if(request.getParameter("action")!=null)
		{
			if(request.getParameter("action").equalsIgnoreCase("Reset"))
			{
			removeSessionAttributes(session);
			session.removeAttribute("listempdetails");
			session.removeAttribute("droDetailsDto");
			session.removeAttribute("alldroemployeeList");
			session.removeAttribute("allreasonslist");
			//ADDED BY SHRUTI
			session.removeAttribute("droDetailsDto");
			droInspectionForm.getDroReport().setPendingDetails("");
			droInspectionForm.getDroReport().setPastdetails("");
			droInspectionForm.getDroReport().setPensionDetails("");
			droInspectionForm.getDroReport().setDeDetails("");
			droInspectionForm.getDroReport().setRrcDetails("");
			droInspectionForm.getDroReport().setStampDetails("");
			droInspectionForm.getDroReport().setInspDate("");
			droInspectionForm.getDroReport().setInspDueDate("");
			droInspectionForm.getDroReport().setInspStartDate("");
			droInspectionForm.getDroReport().setInspToDate("");
			droInspectionForm.getDroReport().setInspEntryDate("");
			droInspectionForm.getDroReport().setObjEntryDate("");
			droInspectionForm.getDroReport().setAuthorityName("");
			droInspectionForm.getDroReport().setIgComment("");
			droInspectionForm.setActionType("");
			droInspectionForm.getReasonDTO(0).setReason("");
			String strForwardPage1 = getDRONames(request, droReport,droReportBD, session,language);
			strForwadPage = getItemNames(request, droReport, droReportBD,session);
			strForwadPage = strForwardPage1;			
			}
		}
		//end
		
		//added by Shreeraj
		if(request.getParameter("DROEmpiID") != null && session.getAttribute("alldroemployeeList")!=null)
		{	
		String empID=(String)request.getParameter("DROEmpiID");
		droInspectionForm.getDroReport().setEmpMod("");
		String eID[]=empID.split("~");
		
		ArrayList emplist = (ArrayList) session.getAttribute("alldroemployeeList");
		ArrayList subList=new ArrayList();
		//subList=(ArrayList) session.getAttribute("alldroemployeeList");
		for(int i=0;i<emplist.size();i++){
			
			DROEmpMapDTO empdto=(DROEmpMapDTO)emplist.get(i);
			for(int j=0;j<eID.length;j++){
				
				if(empdto.getEmpId()!=null){
					
				
				if(empdto.getEmpId().equalsIgnoreCase(eID[j])){
					empdto.setFlagEmp("Y");
					break;
				}else{
					empdto.setFlagEmp("N");
				}
				}
			}
			subList.add(empdto);
		}
		
		ArrayList newList=new ArrayList();
		
		
		
		for(int k=0;k<subList.size();k++){
			DROEmpMapDTO dto=(DROEmpMapDTO)subList.get(k);
		if(dto.getFlagEmp()!=null){
			if(dto.getFlagEmp().equalsIgnoreCase("N")){
				newList.add(dto);
			}
		}
		}
		droInspectionForm.setEmployeeList(newList);
		session.setAttribute("alldroemployeeList", newList);
		 droInspectionForm.setActionType("");
		strForwadPage = "newdroinsp";
		}
		if (request.getParameter("employeeId") != null) {
			DROReportDetailsDTO droReportDetailsDto = droInspectionForm.getDroReport();
			String strEmployeeId = request.getParameter("employeeId");
			DROEmpMapDTO droEmpDto = droReportBD.getEmpDetails(strEmployeeId);
			StringBuffer sbEmployee = new StringBuffer();
			if (droEmpDto != null) {
				if (droEmpDto.getEmpName() != null) {
					sbEmployee.append(droEmpDto.getEmpName() + "|");
				} else {
					sbEmployee.append(" " + "|");
				}
				if (droEmpDto.getDesignation() != null) {
					sbEmployee.append(droEmpDto.getDesignation() + "|");
				} else {
					sbEmployee.append(" " + "|");
				}
				if (droEmpDto.getJoiningDate() != null) {
					sbEmployee.append(droEmpDto.getJoiningDate() + "|");
				} else {
					sbEmployee.append(" " + "|");
				}
				if (droEmpDto.getSeperationDate() != null) {
					sbEmployee.append(droEmpDto.getSeperationDate() + "|");
				} else {
					sbEmployee.append(" " + "|");
				}
			}
			out.print(sbEmployee.toString());
			return null;
		} 
		
		else if(request.getParameter("droname")!=null){
			DROReportDetailsDTO droReportDetailsDto = droInspectionForm.getDroReport();
			String strdroname = request.getParameter("droname");
			String districtname = droReportBD.getDistrictName(strdroname,language);			
			out.print(districtname);
			return null;
		}
			else if (request.getParameter("start") != null) {
				//COMMENTED BY SHRUTI FOR CLEARING ALL PARAMETERS IN FORM ON PAGE LOAD--10 FEB 2014
			//DROReportDetailsDTO droReportDetailsDto = droInspectionForm.getDroReport();
				 droInspectionForm.setModifyFlag("N");
				 droInspectionForm.getDroReport().setEmpMod("");
			removeSessionAttributes(session);
			session.removeAttribute("alldroemployeeList");
			session.removeAttribute("allreasonslist");
			//ADDED BY SHRUTI
			session.removeAttribute("droDetailsDto");
			droInspectionForm.getDroReport().setPendingDetails("");
			droInspectionForm.getDroReport().setPastdetails("");
			droInspectionForm.getDroReport().setPensionDetails("");
			droInspectionForm.getDroReport().setDeDetails("");
			droInspectionForm.getDroReport().setRrcDetails("");
			droInspectionForm.getDroReport().setStampDetails("");
			droInspectionForm.getDroReport().setInspDate("");
			droInspectionForm.getDroReport().setInspDueDate("");
			droInspectionForm.getDroReport().setInspStartDate("");
			droInspectionForm.getDroReport().setInspToDate("");
			droInspectionForm.getDroReport().setInspEntryDate("");
			droInspectionForm.getDroReport().setObjEntryDate("");
			droInspectionForm.getDroReport().setAuthorityName("");
			droInspectionForm.getDroReport().setIgComment("");
			droInspectionForm.getReasonDTO(0).setReason("");
			//END
			String strForwardPage1 = getDRONames(request, droReport,droReportBD, session,language);
			strForwadPage = getItemNames(request, droReport, droReportBD,session);
			strForwadPage = strForwardPage1;			
		} else if (droInspectionForm.getActionType() != null
				&& droInspectionForm.getActionType().equalsIgnoreCase(
						"newinspection")) {
			DROReportDetailsDTO droReportDetailsDto = droInspectionForm.getDroReport();
			session.setAttribute("droDetailsDto",droReportDetailsDto);
			/* 
			 * This blocks load the multiple insertion of employee to the inspection			 * 
			 * 
			 */
			ArrayList employeelist =droInspectionForm.getEmployeeList();
			allemployeeList=getAllEmployeeList(employeelist);
			session.setAttribute("alldroemployeeList", allemployeeList);
			/* 
			 * This blocks load the multiple insertion of pending task to the dro			 * 
			 * 
			 */
			
			
			ArrayList pendinglist =droInspectionForm.getPendinglist();
			allpendinglist=getAllPendingList(pendinglist);			
			session.setAttribute("alldropendinglist", allpendinglist);
			/* 
			 * This blocks load the multiple reason of pending task to the dro			 * 
			 * 
			 */
			
			ArrayList reasonList = droInspectionForm.getReasonlist();
			allreasonList=getAllreasonList(reasonList);			
			session.setAttribute("allreasonslist",allreasonList);
			
			
			ArrayList listAllItemMapDto = new ArrayList();
			/* Fetching Pending task Details from Request start*/
			/*
			 * Pending Check Items
			 * 
			 */
			
			ArrayList listPenChkNew = new ArrayList();
			String[] penChk =droReportDetailsDto.getPenChkItem();
			DROInspItemMap droinspitemmap =null;
			if(penChk!=null){
			for (int i = 0; i < penChk.length; i++) {
				droinspitemmap= new DROInspItemMap();
				droinspitemmap.setItemName(penChk[i]);
				droinspitemmap.setItemStatus("Yes");
				droinspitemmap.setItemRemarks("");
				droinspitemmap.setItemValue(1);
				listPenChkNew.add(droinspitemmap);
				listAllItemMapDto.add(droinspitemmap);
			}
			}
			session.setAttribute("listPenChkNew", listPenChkNew);
			/*
			 * Pending Transaction Items
			 * 
			 */
			
			
			ArrayList listPenTxt = (ArrayList) session.getAttribute("pendingtxtitems");
			ArrayList listPenTxtNew = new ArrayList();
			DROInspItemMap droItemMapNew=null;
			String[] penText = droReportDetailsDto.getPenTxtItem();
			if(penText!=null && listPenTxt!=null){
			for (int i = 0; i < listPenTxt.size(); i++) {
				droinspitemmap = (DROInspItemMap) listPenTxt.get(i);
				droItemMapNew= new DROInspItemMap();
				droItemMapNew.setItemName(droinspitemmap.getItemName());
				int val=0;
				try{
					val = Integer.parseInt(penText[i]);
				}catch (Exception e) {
				val=0;
				e.printStackTrace();	
				}				
				droItemMapNew.setItemValue(val);
				droItemMapNew.setItemRemarks("");
				listPenTxtNew.add(droItemMapNew);
				listAllItemMapDto.add(droItemMapNew);
			}
			}
			session.setAttribute("listpentxtnew", listPenTxtNew);
			/*
			 * Pending Remarks
			 * 
			 * 
			 */
			ArrayList listpenRemarks = (ArrayList) session.getAttribute("pendingremarks");
			String[] penRemarks =droReportDetailsDto.getPenRemarks();
			ArrayList listpenRemarksNew = new ArrayList();
			if(penRemarks!=null && listpenRemarks!=null){
			for (int i = 0; i < listpenRemarks.size(); i++) {
				droinspitemmap = (DROInspItemMap) listpenRemarks.get(i);
				droItemMapNew = new DROInspItemMap();
				droItemMapNew.setItemName(droinspitemmap.getItemName());
				droItemMapNew.setItemRemarks(penRemarks[i]);
				droItemMapNew.setItemValue(0);
				listpenRemarksNew.add(droItemMapNew);
				listAllItemMapDto.add(droItemMapNew);
			}
			}
			session.setAttribute("listpenremarksnew", listpenRemarksNew);
			/* Fetching Pending task Details from Request End */
			/*
			 * Mud drank Values
			 * 
			 */
			/* Fetching Mudrank Details From Request Start */
			ArrayList listMudTxtItem = (ArrayList) session.getAttribute("listmudtxtitems");
			ArrayList listMudTxt = new ArrayList();
			
			String[] mudTxtVal = droReportDetailsDto.getMudrankTxt();
			if(mudTxtVal!=null && listMudTxtItem !=null){
			for (int i = 0; i < listMudTxtItem.size(); i++) {
				droinspitemmap = (DROInspItemMap) listMudTxtItem.get(i);
				droItemMapNew = new DROInspItemMap();
				droItemMapNew.setItemName(droinspitemmap.getItemName());
				int val = Integer.parseInt(mudTxtVal[i]);
				droItemMapNew.setItemValue(val);
				droItemMapNew.setItemRemarks("");
				listMudTxt.add(droItemMapNew);
				listAllItemMapDto.add(droItemMapNew);
			}
			}
			session.setAttribute("listMudTxtValNew", listMudTxt);
			/*
			 * Mud Check items and Remarks
			 * 
			 * 
			 */
			
			ArrayList listMudRemarks = new ArrayList();
			String[] itemNames =droReportDetailsDto.getMudChkItemName();				
			String[] itemRemaks = droReportDetailsDto.getMudrankRemarks();
			if(itemNames!=null && itemRemaks!=null){
			for (int i = 0; i < itemNames.length; i++) {
				droinspitemmap= new DROInspItemMap();
				droinspitemmap.setItemName(itemNames[i]);
				droinspitemmap.setItemRemarks(itemRemaks[i]);
				droinspitemmap.setItemValue(0);
				droinspitemmap.setItemStatus("Yes");
				listMudRemarks.add(droinspitemmap);
				listAllItemMapDto.add(droinspitemmap);
			}
			}
			session.setAttribute("mudItemNames", itemNames);
			session.setAttribute("mudRemarks", itemRemaks);
			session.setAttribute("listMudRamrksNew", listMudRemarks);
			/*
			 * 
			 * 0
			 */
			ArrayList listMudTxtItems = (ArrayList) session
			.getAttribute("listmudtxtitems");
			ArrayList listMudTxte = new ArrayList();
			String[] mudfrommonth=droReportDetailsDto.getMudFromMonth();
			String[] mudtomonth=droReportDetailsDto.getMudToMonth();
			String[] mudfromyear=droReportDetailsDto.getMudFromYear();
			String [] mudtoyear=droReportDetailsDto.getMudToYear();
			
		//	String[] mudTxtVal = droReportDetailsDto.getMudrankTxt();
			if(mudfrommonth!=null && mudtomonth !=null && mudfromyear!=null && mudtoyear!=null){
			for (int i = 0; i < mudfrommonth.length; i++) {
				droinspitemmap = (DROInspItemMap) listMudTxtItems.get(i);
				droItemMapNew = new DROInspItemMap();
				if(droinspitemmap.getItemName().equalsIgnoreCase("No. of Registerd cases")|| droinspitemmap.getItemName().equalsIgnoreCase("No of Solved cases") ){
					
				droItemMapNew.setItemName(droinspitemmap.getItemName()+" From");
				//int val = Integer.parseInt(mudTxtVal[i]);
				droItemMapNew.setItemValue(0);
				droItemMapNew.setItemRemarks(mudfrommonth[i]+"-"+mudfromyear[i]);
				listMudTxte.add(droItemMapNew);
				
				droItemMapNew.setItemName(droinspitemmap.getItemName()+" To");
				//int val = Integer.parseInt(mudTxtVal[i]);
				droItemMapNew.setItemValue(0);
				droItemMapNew.setItemRemarks(mudtomonth[i]+"-"+mudtoyear[i]);
				listMudTxte.add(droItemMapNew);				
				listAllItemMapDto.add(droItemMapNew);
				}
			}
			}
			session.setAttribute("listMudfromtoNew", listMudTxte);
			/* Fetching Mudrank Details From Request End */
			/* Fetching R.R.C Details From Request Start */
					
			ArrayList listRrcTxt = (ArrayList) session.getAttribute("listTxtItemsRRC");
			ArrayList listRrcTxtNew = new ArrayList();
			String[] rrcText =droReportDetailsDto.getRrcTxtValue();
			if(rrcText!=null && listRrcTxt!=null ){
			for (int i = 0; i < listRrcTxt.size(); i++) {
				droinspitemmap = (DROInspItemMap) listRrcTxt.get(i);
				 droItemMapNew = new DROInspItemMap();
				droItemMapNew.setItemName(droinspitemmap.getItemName());
				droItemMapNew.setItemValue(Integer.parseInt(rrcText[i]));
				droItemMapNew.setItemRemarks("");
				listRrcTxtNew.add(droItemMapNew);
				listAllItemMapDto.add(droItemMapNew);
			}}
			session.setAttribute("listRrcTxtNew", listRrcTxtNew);
			/*
			 * RRC Check Items
			 * 
			 */
			
			String[] rrcChkItems = droReportDetailsDto.getChkRrcItem();
			ArrayList listRrcChk = new ArrayList();
			if(rrcChkItems!=null){
			for (int i = 0; i < rrcChkItems.length; i++) {
				droinspitemmap = new DROInspItemMap();
				droinspitemmap.setItemName(rrcChkItems[i]);
				droinspitemmap.setItemRemarks("");
				droinspitemmap.setItemValue(0);
				droinspitemmap.setItemStatus("Yes");
				listRrcChk.add(droinspitemmap);
				listAllItemMapDto.add(droinspitemmap);
			}
			}
			session.setAttribute("listChkRrcNew", listRrcChk);
			/*
			 * Radio Button As Parameter Values;
			 * 
			 */
			
			ArrayList listRadioRrc = (ArrayList) session.getAttribute("listChkRadioItemsRRC");
			ArrayList listRadioRrcNew = new ArrayList();
		
			session.setAttribute("listRrcRadio", listRadioRrcNew);
			/* Fetching R.R.C Details From Request End */
			
			/* Fetching SRO Details From Request start */
			ArrayList listSroTxt = (ArrayList) session.getAttribute("listTxtItemSro");
			ArrayList listSroTxtNew = new ArrayList();
		
			session.setAttribute("listSroTxtNew", listSroTxtNew);
			/*
			 * SRO CHECK ITEMS
			 * 
			 */
			
			String[] sroChk = droReportDetailsDto.getSroChkItem();
			ArrayList listChkSroNew = new ArrayList();
		
			session.setAttribute("listSroChkNew", listChkSroNew);
			/* Fetching SRO Details From Request End */
						
			/* Fetching ROS Details From Request Start*/
			ArrayList listTxtRos = (ArrayList) session.getAttribute("listTxtItemsRos");
			ArrayList listTxtRosNew = new ArrayList();
			session.setAttribute("listRosTxtNew", listTxtRosNew);
			/*
			 * ROS Radio Items Radio Button As Parameter Values;
			 * 
			 */
			ArrayList listRadioRos = (ArrayList) session.getAttribute("listChkRadioItemsRos");
			ArrayList listRadioRosNew = new ArrayList();

			session.setAttribute("listRadioRosNew", listRadioRosNew);
			/*
			 * Check RoS 
			 * 
			 * 
			 */			
			String[] chkRos = droReportDetailsDto.getChkRosItems();
			ArrayList listChkRosNew = new ArrayList();

			session.setAttribute("listChkRosNew", listChkRosNew);
			/* Fetching ROS Details From Request End */
			
			/* Fetching CASE Details From Request Start*/
			
			ArrayList listCasTxt = (ArrayList) session.getAttribute("listTxtItemCas");
			ArrayList listCasTxtNew = new ArrayList();
			String[] txtCas = droReportDetailsDto.getCasTxtVal();
		
			session.setAttribute("listCasTxtNew", listCasTxtNew);
			/* Fetching CAS Details From Request End */
			
			/* Fetching STAMP Details From Request Start */
			
			String[] staChk = droReportDetailsDto.getChkStaItems();
			ArrayList listStaChk = new ArrayList();
			
			session.setAttribute("listChkStaNew", listStaChk);
			
			
			ArrayList listStaRadio = (ArrayList) session.getAttribute("listChkRadioSta");
			ArrayList listStaRadioNew = new ArrayList();
		
			session.setAttribute("listStaRadioNew", listStaRadioNew);
			/*
			 * Stamp Remarks
			 * 
			 */
			
			ArrayList listStaRemaks = (ArrayList) session.getAttribute("listRemarksSta");
			ArrayList listStaRemarksNew = new ArrayList();
		
			session.setAttribute("listStaRemarksNew", listStaRemarksNew);
			/* Fetching STA Details From Request End  */
			/* Fetching MAH Details From Request Start */
			ArrayList listMahTxtItems = (ArrayList) session.getAttribute("listTxtItemsLek");
			ArrayList listMahTxtNew = new ArrayList();
			String[] itemName = droReportDetailsDto.getLekItemYear();
		
			session.setAttribute("LekhaSelect", listMahTxtNew);

			ArrayList listLekTxt = (ArrayList) session.getAttribute("listTxtLekha");
			ArrayList listLekTxtNew = new ArrayList();
			
			session.setAttribute("listTxtLekNew", listLekTxtNew);
			
			
			session.setAttribute("listMahTxtNew", listMahTxtNew);
			ArrayList listMahRemarkItems = (ArrayList) session.getAttribute("listRemarksLek");
			ArrayList listMahRemarksNew = new ArrayList();
		
			session.setAttribute("listMahRemarksNew", listMahRemarksNew);
			/* Fetching MAH Details From Request End */
			
			ArrayList listTxtLekha = (ArrayList) session.getAttribute("listTxtLekha");
			
			session.setAttribute("listYearLekhainternal", listMahRemarksNew);
			
			ArrayList listTxt = (ArrayList) session.getAttribute("lekintertxtval");
		
			session.setAttribute("listDateLekhainternal", listMahRemarksNew);
			
			
			/* Fetching TES Details From Request Start*/
			ArrayList listTesTxt = (ArrayList) session.getAttribute("listTextItemsTes");
			ArrayList listTesTxtNew = new ArrayList();
			
			session.setAttribute("listTesTxtNew", listTesTxtNew);
			
			ArrayList listTesRemarks = (ArrayList) session.getAttribute("listRemarkItemsTes");
			ArrayList listTesRemarksNew = new ArrayList();
		
			session.setAttribute("listTesRemarksNew", listTesRemarksNew);
			/* Fetching TES Details From Request End */
			
			
			/* Fetching REP Details From Request Start
			 * annual confidential charitravali report
			 * */
			ArrayList listRepRadio = (ArrayList) session.getAttribute("listRadioRep");
			ArrayList listRepRadioNew = new ArrayList();
		
			
			session.setAttribute("listRadiRepNew", listRepRadioNew);		
			
			
			ArrayList listTxtRep = (ArrayList) session.getAttribute("listTxtRep");
			ArrayList listTxtRepNew = new ArrayList();
		
			session.setAttribute("listRepTxtNew", listTxtRepNew);
			/* Fetching REP Details From Request End */		
			
			/* Fetching FRU Details From Request Start*/
			ArrayList listRadioFru = (ArrayList) session.getAttribute("listChkRadioFru");
			ArrayList listRadioFruNew = new ArrayList();
			
			session.setAttribute("listRadioFruNew", listRadioFruNew);
			/* Fetching FRU Details From Request End  */
			
			/* Fetching DES Details From Request Start*/
			ArrayList listDesTxt = (ArrayList) session.getAttribute("listTextItemDes");
			//String[] desTxt = droReportDetailsDto.getTxtItemsDes();
			ArrayList listDesTextNew = new ArrayList();
			
			session.setAttribute("listDesTextNew", listDesTextNew);
			
			
			ArrayList listDesRadioItems = (ArrayList) session.getAttribute("listChkRadioItemsDes");
			ArrayList listDesRadioNew = new ArrayList();

			session.setAttribute("listRadioDesNew", listDesRadioNew);
			/* Fetching DES Details From Request End */
			
			/* Fetching LEK Details From Request Start */
		
			/* Fetching LEK Details From Request End */
			droReportDetailsDto.setListDroItemMap(listAllItemMapDto);
			
			//droReportDetailsDto.setListDroPendingTask((ArrayList)session.getAttribute("alldropendinglist"));
			droReportDetailsDto.setListEmpMapDto((ArrayList)session.getAttribute("alldroemployeeList"));
			droReportDetailsDto.setListDROReasonMap((ArrayList)session.getAttribute("allreasonslist"));
			droReportDetailsDto.setListDROReasonMap(droInspectionForm.getReasonlist());
		//	System.out.println("DIstrict Name :"+request.getParameter("district"));
			//droReportDetailsDto.setDistrictName(request.getParameter("district"));
			if(language.equalsIgnoreCase("hi"))
			{
				droReportDetailsDto.setFrommonth(hindiMonth(droReportDetailsDto.getFrommonth()));
				droReportDetailsDto.setTomonth(hindiMonth(droReportDetailsDto.getTomonth()));
				droReportDetailsDto.setRevenuefrommonth(hindiMonth(droReportDetailsDto.getRevenuefrommonth()));
				droReportDetailsDto.setRevenuetomonth(hindiMonth(droReportDetailsDto.getRevenuetomonth()));
				droReportDetailsDto.setPfyfrommonth(hindiMonth(droReportDetailsDto.getPfyfrommonth()));
				droReportDetailsDto.setPfytomonth(hindiMonth(droReportDetailsDto.getPfytomonth()));
			}
			droReportDetailsDto.setDroName(droReportBD.getOfficeName(droReportDetailsDto.getDroId(),language));
			session.setAttribute("droDetailsDto",droReportDetailsDto);
			//System.out.println("Session Created");
			strForwadPage = "droInspConfirm";
		}
		else if(droInspectionForm.getActionType() != null && droInspectionForm.getActionType().equalsIgnoreCase("returnattachmentpendingtask")){
			//System.out.println("DROINSPECTION FORM IN PENDING FORM"+droInspectionForm.getActionType());
			
			
			
			
			
			DROReportDetailsDTO reportDetailsDTO =droInspectionForm.getDroReport();
			session.setAttribute("droDetailsDto",reportDetailsDTO);
			/* 
			 * This blocks load the multiple insertion of employee to the inspection			 * 
			 * 
			 */
			ArrayList employeelist =droInspectionForm.getEmployeeList();
			allemployeeList=getAllEmployeeList(employeelist);
			session.setAttribute("alldroemployeeList", allemployeeList);
			/* 
			 * This blocks load the multiple insertion of pending task to the dro			 * 
			 * 
			 */
			
			ArrayList pendinglist =droInspectionForm.getPendinglist();
			allpendinglist=getAllPendingList(pendinglist);			
			session.setAttribute("alldropendinglist", allpendinglist);
			/* 
			 * This blocks load the multiple reason of pending task to the dro			 * 
			 * 
			 */
			
			ArrayList reasonList = droInspectionForm.getReasonlist();
			allreasonList=getAllreasonList(reasonList);			
			//ArrayList listPendingFileNames = (ArrayList) session.getAttribute("pendingfiles");
		//	session.setAttribute("pendingfiles", listPendingFileNames);
			//session.removeAttribute("attachment1");
			/*
			 * Details of Pending Tasks 
			 */
			
			
			String[] penRemarks = request.getParameterValues("penRemarks");
			/*
			 * Fetching Mudrank Details
			 */
			String[] mudTxtVal = request.getParameterValues("mudrankTxt");
			String[] itemNames = request.getParameterValues("mudChkItemName");
			String[] itemRemaks = request.getParameterValues("mudrankRemarks");
			/* Fetching Mudrank Details From Request End */
			
			/* Fetching R.R.C Details From Request Start */
			String[] rrcText = request.getParameterValues("rrcTxtValue");
			String[] rrcChkItems = request.getParameterValues("chkRrcItem");
			/* Fetching R.R.C Details From Request End */
			/*
			 *  
			 *  Fetching SRO Details From Request start
			 *   */
			String[] txtVal = request.getParameterValues("sroTextItem");
			String[] sroChk = request.getParameterValues("sroChkItem");
			/* Fetching SRO Details From Request End
			 *  */
			/* Fetching ROS Details From Request Start*/
			String[] txtRos = request.getParameterValues("rosTxtItems");
			String radio =request.getParameter("radioRos");
			String[] chkRos = request.getParameterValues("chkRosItems");
			/* Fetching ROS Details From Request End */
			/* Fetching CAS Details From Request Start*/
			String[] txtCas = request.getParameterValues("casTxtVal");
			/* Fetching CAS Details From Request End */
			/* Fetching STA Details From Request Start */
			String[] staChk = request.getParameterValues("chkStaItems");
			String staRadioItem=request.getParameter("staRadioItem");
			String[] staRemarks = request.getParameterValues("staRemarks");
			
			/* Fetching STA Details From Request End  */
			/* Fetching MAH Details From Request Start */
			String[] itemName = request.getParameterValues("lekItemYear");
			String lekItemYear = request.getParameter("lekItemYear");
			//DROInspItemMap droItemNew = new DROInspItemMap();
			String[] mahRemars = request.getParameterValues("mahRemarks");
			/* Fetching MAH Details From Request End */
			/* Fetching REP Details From Request Start*/
			String repRadio=request.getParameter("repRadio");
			String[] txtRep = request.getParameterValues("repTxtItem");
			/* Fetching REP Details From Request End */
			/* Fetching TES Details From Request Start*/
			String[] tesItems = request.getParameterValues("txtTesItems");
			String[] tesRemarks = request.getParameterValues("tesRemarks");
			/* Fetching TES Details From Request End */
			/* Fetching DES Details From Request Start*/
			String[] desTxt = request.getParameterValues("txtItemsDes");
			ArrayList listDesRadioNew = new ArrayList();
			request.getParameter("radioDes");
			/* Fetching DES Details From Request End */
			/* Fetching FRU Details From Request Start*/
			String radioFru=request.getParameter("radioFru");
			/* Fetching FRU Details From Request End  */
			/* Fetching LEK Details From Request Start */
			
			strForwadPage = "newdroinsp";
		}
		else if(droInspectionForm.getActionType() != null && droInspectionForm.getActionType().equalsIgnoreCase("returnattachmentaudit")){
			//System.out.println("DROINSPECTION FORM IN AUDIT FORM"+droInspectionForm.getActionType());
			
			DROReportDetailsDTO reportDetailsDTO =droInspectionForm.getDroReport();
			session.setAttribute("droDetailsDto",reportDetailsDTO);
			/* 
			 * This blocks load the multiple insertion of employee to the inspection			 * 
			 * 
			 */
			ArrayList employeelist =droInspectionForm.getEmployeeList();
			allemployeeList=getAllEmployeeList(employeelist);
			session.setAttribute("alldroemployeeList", allemployeeList);
			/* 
			 * This blocks load the multiple insertion of pending task to the dro			 * 
			 * 
			 */
			
			ArrayList pendinglist =droInspectionForm.getPendinglist();
			allpendinglist=getAllPendingList(pendinglist);			
			session.setAttribute("alldropendinglist", allpendinglist);
			/* 
			 * This blocks load the multiple reason of pending task to the dro			 * 
			 * 
			 */
			
			ArrayList reasonList = droInspectionForm.getReasonlist();
			allreasonList=getAllreasonList(reasonList);			
			
			strForwadPage = "newdroinsp";
		}
		else if(droInspectionForm.getActionType() != null && droInspectionForm.getActionType().equalsIgnoreCase("returnattachmentinternalaudit")){
			//System.out.println("DROINSPECTION FORM IN INTERNAL FORM"+droInspectionForm.getActionType());
			
			DROReportDetailsDTO reportDetailsDTO =droInspectionForm.getDroReport();
			session.setAttribute("droDetailsDto",reportDetailsDTO);
			/* 
			 * This blocks load the multiple insertion of employee to the inspection			 * 
			 * 
			 */
			ArrayList employeelist =droInspectionForm.getEmployeeList();
			allemployeeList=getAllEmployeeList(employeelist);
			session.setAttribute("alldroemployeeList", allemployeeList);
			/* 
			 * This blocks load the multiple insertion of pending task to the dro			 * 
			 * 
			 */
			
			ArrayList pendinglist =droInspectionForm.getPendinglist();
			allpendinglist=getAllPendingList(pendinglist);			
			session.setAttribute("alldropendinglist", allpendinglist);
			/* 
			 * This blocks load the multiple reason of pending task to the dro			 * 
			 * 
			 */
			
			ArrayList reasonList = droInspectionForm.getReasonlist();
			allreasonList=getAllreasonList(reasonList);			
	
			strForwadPage = "newdroinsp";
		}
		else if(droInspectionForm.getActionType() != null && droInspectionForm.getActionType().equalsIgnoreCase("returnattachmentstamp")){
			
			DROReportDetailsDTO reportDetailsDTO =droInspectionForm.getDroReport();
			session.setAttribute("droDetailsDto",reportDetailsDTO);
			/* 
			 * This blocks load the multiple insertion of employee to the inspection			 * 
			 * 
			 */
			ArrayList employeelist =droInspectionForm.getEmployeeList();
			allemployeeList=getAllEmployeeList(employeelist);
			session.setAttribute("alldroemployeeList", allemployeeList);
			/* 
			 * This blocks load the multiple insertion of pending task to the dro			 * 
			 * 
			 */
			
			ArrayList pendinglist =droInspectionForm.getPendinglist();
			allpendinglist=getAllPendingList(pendinglist);			
			session.setAttribute("alldropendinglist", allpendinglist);
			/* 
			 * This blocks load the multiple reason of pending task to the dro			 * 
			 * 
			 */
			
			ArrayList reasonList = droInspectionForm.getReasonlist();
			allreasonList=getAllreasonList(reasonList);			

			strForwadPage = "newdroinsp";
		}
		
		else if(droInspectionForm.getActionType() != null && droInspectionForm.getActionType().equalsIgnoreCase("submit")){
			DROReportDetailsDTO listAllItemsDto  =  (DROReportDetailsDTO)session.getAttribute("droDetailsDto");
			ArrayList pendingfiles = (ArrayList)session.getAttribute("pendingfiles");
			ArrayList auditfiles=(ArrayList)session.getAttribute("auditfiles");
			ArrayList internalauditfiles=(ArrayList)session.getAttribute("internalauditfiles");
			ArrayList stampfiles=(ArrayList)session.getAttribute("stampfiles");			
			String filePath = (String) session.getAttribute("FilePath");
			
			listAllItemsDto.setInspectedBy((String) session.getAttribute("UserId"));
			String droReportId  = droReportBD.saveDROInspectionDetails(listAllItemsDto,pendingfiles,auditfiles,internalauditfiles,stampfiles, filePath,officeId);
			
			if(droReportId==null)
			{
				strForwadPage = "";
			}
			else
			{ 
				if(pendingfiles!=null){
					
				
				for(int i=0;i<pendingfiles.size();i++){
					UploadFileDTO uDTO = (UploadFileDTO) pendingfiles.get(i);
					String filePathLen=uDTO.getFilePath();
					String docFilePath=uDTO.getFilePath().substring(0, filePathLen.length()-(uDTO.getFileName().length()+2)).concat(droReportId.concat("\\"+uDTO.getFileName()));
					FormFile file = uDTO.getTheFile();
					 File folder = new File(uDTO.getFilePath().substring(0, filePathLen.length()-(uDTO.getFileName().length()+2)).concat(droReportId));
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
				if(pendingfiles.size()>=1){
					
					UploadFileDTO uDTO1 = (UploadFileDTO) pendingfiles.get(0);
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
			}
				strForwadPage = "inspFinal";
				request.setAttribute("droInspTxnId",droReportId);
			}
		}
		else if(droInspectionForm.getActionType() != null && droInspectionForm.getActionType().equalsIgnoreCase("modify")){
			String month1=droInspectionForm.getDroReport().getFrommonth();
			String month2=droInspectionForm.getDroReport().getPfyfrommonth();
			String month3=droInspectionForm.getDroReport().getPfytomonth();
			String month4=droInspectionForm.getDroReport().getRevenuefrommonth();
			
			String month5=droInspectionForm.getDroReport().getRevenuetomonth();
			String month6=droInspectionForm.getDroReport().getTomonth();
			if(language.equalsIgnoreCase("hi")){
				if(month1=="जनवरी"){
					droInspectionForm.getDroReport().setFrommonth("January");
				}
				if(month1=="फरवरी"){
					droInspectionForm.getDroReport().setFrommonth("February");
				}
				if(month1=="मार्च"){
					droInspectionForm.getDroReport().setFrommonth("March");
				}
				if(month1=="अप्रैल"){
					droInspectionForm.getDroReport().setFrommonth("April");
				}
				if(month1=="मई"){
					droInspectionForm.getDroReport().setFrommonth("May");
				}
				if(month1=="जून"){
					droInspectionForm.getDroReport().setFrommonth("June");
				}
				if(month1=="जुलाई"){
					droInspectionForm.getDroReport().setFrommonth("July");
				}
				if(month1=="अगस्त"){
					droInspectionForm.getDroReport().setFrommonth("August");
				}
				if(month1=="सितंबर"){
					droInspectionForm.getDroReport().setFrommonth("September");
				}
				if(month1=="अक्टूबर"){
					droInspectionForm.getDroReport().setFrommonth("October");
				}
				if(month1=="नवंबर"){
					droInspectionForm.getDroReport().setFrommonth("November");
				}
				if(month1=="दिसंबर"){
					droInspectionForm.getDroReport().setFrommonth("December");
				}
				//FOR MONTH2
				if(month2=="जनवरी"){
					droInspectionForm.getDroReport().setPfyfrommonth("January");
				}
				if(month2=="फरवरी"){
					droInspectionForm.getDroReport().setPfyfrommonth("February");
				}
				if(month2=="मार्च"){
					droInspectionForm.getDroReport().setPfyfrommonth("March");
				}
				if(month2=="अप्रैल"){
					droInspectionForm.getDroReport().setPfyfrommonth("April");
				}
				if(month2=="मई"){
					droInspectionForm.getDroReport().setPfyfrommonth("May");
				}
				if(month2=="जून"){
					droInspectionForm.getDroReport().setPfyfrommonth("June");
				}
				if(month2=="जुलाई"){
					droInspectionForm.getDroReport().setPfyfrommonth("July");
				}
				if(month2=="अगस्त"){
					droInspectionForm.getDroReport().setPfyfrommonth("August");
				}
				if(month2=="सितंबर"){
					droInspectionForm.getDroReport().setPfyfrommonth("September");
				}
				if(month2=="अक्टूबर"){
					droInspectionForm.getDroReport().setPfyfrommonth("October");
				}
				if(month2=="नवंबर"){
					droInspectionForm.getDroReport().setPfyfrommonth("November");
				}
				if(month2=="दिसंबर"){
					droInspectionForm.getDroReport().setPfyfrommonth("December");
				}
				//FOR MONTH3
				if(month3=="जनवरी"){
					droInspectionForm.getDroReport().setPfytomonth("January");
				}
				if(month3=="फरवरी"){
					droInspectionForm.getDroReport().setPfytomonth("February");
				}
				if(month3=="मार्च"){
					droInspectionForm.getDroReport().setPfytomonth("March");
				}
				if(month3=="अप्रैल"){
					droInspectionForm.getDroReport().setPfytomonth("April");
				}
				if(month3=="मई"){
					droInspectionForm.getDroReport().setPfytomonth("May");
				}
				if(month3=="जून"){
					droInspectionForm.getDroReport().setPfytomonth("June");
				}
				if(month3=="जुलाई"){
					droInspectionForm.getDroReport().setPfytomonth("July");
				}
				if(month3=="अगस्त"){
					droInspectionForm.getDroReport().setPfytomonth("August");
				}
				if(month3=="सितंबर"){
					droInspectionForm.getDroReport().setPfytomonth("September");
				}
				if(month3=="अक्टूबर"){
					droInspectionForm.getDroReport().setPfytomonth("October");
				}
				if(month3=="नवंबर"){
					droInspectionForm.getDroReport().setPfytomonth("November");
				}
				if(month3=="दिसंबर"){
					droInspectionForm.getDroReport().setPfytomonth("December");
				}
				//FOR MONTH 4
				if(month4=="जनवरी"){
					droInspectionForm.getDroReport().setRevenuefrommonth("January");
				}
				if(month4=="फरवरी"){
					droInspectionForm.getDroReport().setRevenuefrommonth("February");
				}
				if(month4=="मार्च"){
					droInspectionForm.getDroReport().setRevenuefrommonth("March");
				}
				if(month4=="अप्रैल"){
					droInspectionForm.getDroReport().setRevenuefrommonth("April");
				}
				if(month4=="मई"){
					droInspectionForm.getDroReport().setRevenuefrommonth("May");
				}
				if(month4=="जून"){
					droInspectionForm.getDroReport().setRevenuefrommonth("June");
				}
				if(month4=="जुलाई"){
					droInspectionForm.getDroReport().setRevenuefrommonth("July");
				}
				if(month4=="अगस्त"){
					droInspectionForm.getDroReport().setRevenuefrommonth("August");
				}
				if(month4=="सितंबर"){
					droInspectionForm.getDroReport().setRevenuefrommonth("September");
				}
				if(month4=="अक्टूबर"){
					droInspectionForm.getDroReport().setRevenuefrommonth("October");
				}
				if(month4=="नवंबर"){
					droInspectionForm.getDroReport().setRevenuefrommonth("November");
				}
				if(month4=="दिसंबर"){
					droInspectionForm.getDroReport().setRevenuefrommonth("December");
				}
				//FOR MONTH5
				if(month5=="जनवरी"){
					droInspectionForm.getDroReport().setRevenuetomonth("January");
				}
				if(month5=="फरवरी"){
					droInspectionForm.getDroReport().setRevenuetomonth("February");
				}
				if(month5=="मार्च"){
					droInspectionForm.getDroReport().setRevenuetomonth("March");
				}
				if(month5=="अप्रैल"){
					droInspectionForm.getDroReport().setRevenuetomonth("April");
				}
				if(month5=="मई"){
					droInspectionForm.getDroReport().setRevenuetomonth("May");
				}
				if(month5=="जून"){
					droInspectionForm.getDroReport().setRevenuetomonth("June");
				}
				if(month5=="जुलाई"){
					droInspectionForm.getDroReport().setRevenuetomonth("July");
				}
				if(month5=="अगस्त"){
					droInspectionForm.getDroReport().setRevenuetomonth("August");
				}
				if(month5=="सितंबर"){
					droInspectionForm.getDroReport().setRevenuetomonth("September");
				}
				if(month5=="अक्टूबर"){
					droInspectionForm.getDroReport().setRevenuetomonth("October");
				}
				if(month5=="नवंबर"){
					droInspectionForm.getDroReport().setRevenuetomonth("November");
				}
				if(month5=="दिसंबर"){
					droInspectionForm.getDroReport().setRevenuetomonth("December");
				}
				//FOR MONTH6
				if(month6=="जनवरी"){
					droInspectionForm.getDroReport().setTomonth("January");
				}
				if(month6=="फरवरी"){
					droInspectionForm.getDroReport().setTomonth("February");
				}
				if(month6=="मार्च"){
					droInspectionForm.getDroReport().setTomonth("March");
				}
				if(month6=="अप्रैल"){
					droInspectionForm.getDroReport().setTomonth("April");
				}
				if(month6=="मई"){
					droInspectionForm.getDroReport().setTomonth("May");
				}
				if(month6=="जून"){
					droInspectionForm.getDroReport().setTomonth("June");
				}
				if(month6=="जुलाई"){
					droInspectionForm.getDroReport().setTomonth("July");
				}
				if(month6=="अगस्त"){
					droInspectionForm.getDroReport().setTomonth("August");
				}
				if(month6=="सितंबर"){
					droInspectionForm.getDroReport().setTomonth("September");
				}
				if(month6=="अक्टूबर"){
					droInspectionForm.getDroReport().setTomonth("October");
				}
				if(month6=="नवंबर"){
					droInspectionForm.getDroReport().setTomonth("November");
				}
				if(month6=="दिसंबर"){
					droInspectionForm.getDroReport().setTomonth("December");
				}
				
			}
			droInspectionForm.getDroReport().setEmpMod("");
			droInspectionForm.setModifyFlag("Y");
			strForwadPage = "newdroinsp";
		}
		else if(droInspectionForm.getActionType()!= null && droInspectionForm.getActionType().equalsIgnoreCase("cancel")){
			removeSessionAttributes(session);
			strForwadPage = "srhome";
		}
			

		logger.info("Forward Page :" + strForwadPage);
		return mapping.findForward(strForwadPage);
	}

	/**
	 * @param reasonList
	 * @return
	 */
	private ArrayList getAllreasonList(ArrayList reasonList) {
		ArrayList allreasonList=new ArrayList();
		if(reasonList!=null){
		for(int i=0;i<reasonList.size();i++)
		{
			DROReasonMapDTO  droReasonDto  = (DROReasonMapDTO)reasonList.get(i);
			if(droReasonDto != null || droReasonDto.getReason().equals("")){
			}
			else{
				
				allreasonList.add(droReasonDto);
			}
		}
		}
		return allreasonList;
	}

	/**
	 * @param pendinglist
	 * @return
	 */
	private ArrayList getAllPendingList(ArrayList pendinglist) {
		ArrayList allpendinglist=new ArrayList();
		if(pendinglist!=null){
		for (int i = 0; i < pendinglist.size(); i++) {
			DROPendingTaskDTO  itemsDTO= (DROPendingTaskDTO) pendinglist.get(i);
			if ((itemsDTO != null && itemsDTO.getPendingTask() == null)
					|| (itemsDTO != null && itemsDTO.getPendingTask().equals(
							""))) {
			} else {

				allpendinglist.add(itemsDTO);
			}
		}
		}
		return allpendinglist;
	}

	/**
	 * @return
	 */
	private ArrayList getAllEmployeeList(ArrayList employeelist) {
		ArrayList allemployeeList=new ArrayList();
		if(employeelist!=null){
		for (int i = 0; i < employeelist.size(); i++) {
			DROEmpMapDTO empdto = (DROEmpMapDTO) employeelist.get(i);
			if ((empdto != null && empdto.getEmpName() == null)
					|| (empdto != null && empdto.getEmpName().equals(
							""))) {
			} else {

				allemployeeList.add(empdto);
			}
		}	
		}
		return allemployeeList;
	}

	/**
	 * @param request
	 * @param agmpreportdetailsdto
	 * @param sroDetailsBd
	 * @param session
	 * @return
	 */

	private String getDRONames(HttpServletRequest request,
			DROReportDetailsDTO myDTO,DROInspectionBD droDetailsBd,
			HttpSession session,String langauge) {

		String officeId=(String)session.getAttribute("loggedToOffice");
		ArrayList droList = droDetailsBd.getDRONames(langauge,officeId);
		myDTO.setDroList(droList);
		session.setAttribute("DRONames", myDTO);
		return "newdroinsp";
	}

	/*************************************************************************************
	 get the Item Names 
	 *************************************************************************************/
	private String getItemNames(HttpServletRequest request,
			DROReportDetailsDTO myDTO, DROInspectionBD droDetailsBd,
			HttpSession session) {

		ArrayList listpendingItem = droDetailsBd.getPendingItems();
		DROInspItemMap droItemMap = droDetailsBd.getItemName();

		session.setAttribute("listpenchkitem", listpendingItem);
		session.setAttribute("pendingitemdto", droItemMap);

		ArrayList listpendingText = droDetailsBd.getPendingTextItems();
		session.setAttribute("pendingtxtitems", listpendingText);

		ArrayList listpendingRemarks = droDetailsBd.getPendingRemarkItems();
		session.setAttribute("pendingremarks", listpendingRemarks);

		DROInspItemMap droItemMapMud = droDetailsBd.getItemNameMud();
		session.setAttribute("dromuditemdto", droItemMapMud);

		ArrayList listMudTextItems = droDetailsBd.getMudTextItems();
		session.setAttribute("listmudtxtitems", listMudTextItems);

		ArrayList listMudRemarksItems = droDetailsBd.getMudRemarksItems();
		session.setAttribute("listmudremarks", listMudRemarksItems);

		DROInspItemMap droItemMapRrc = droDetailsBd.getItemNameRrc();
		session.setAttribute("droinsprrcdto", droItemMapRrc);

		ArrayList listRrcTextItems = droDetailsBd.getRrcTextItems();
		session.setAttribute("listTxtItemsRRC", listRrcTextItems);

		ArrayList listRrcChkItems = droDetailsBd.getRrcChkItems();
		session.setAttribute("listChkItemsRRC", listRrcChkItems);

		ArrayList listRrcChkRadioItems = droDetailsBd.getRrcChkRadioItems();
		session.setAttribute("listChkRadioItemsRRC", listRrcChkRadioItems);

		DROInspItemMap droItemMapSro = droDetailsBd.getItemNameSro();
		ArrayList listSroTextItems = droDetailsBd.getSroTxtItems();
		ArrayList listSroCheckItems = droDetailsBd.getSroCheckItems();

		session.setAttribute("sroItemNameDto", droItemMapSro);
		session.setAttribute("listTxtItemSro", listSroTextItems);
		session.setAttribute("listChkItemSro", listSroCheckItems);

		DROInspItemMap droItemMapRos = droDetailsBd.getItemNameRos();
		ArrayList listRosTxtItems = droDetailsBd.getRosTxtItems();
		ArrayList listRosChkRadioItems = droDetailsBd.getRosChkRadioItems();
		ArrayList listRosChkItems = droDetailsBd.getRosChkItems();

		session.setAttribute("rosItemNameDto", droItemMapRos);
		session.setAttribute("listTxtItemsRos", listRosTxtItems);
		session.setAttribute("listChkRadioItemsRos", listRosChkRadioItems);
		session.setAttribute("listChkItemsRos", listRosChkItems);

		DROInspItemMap droItemMapCas = droDetailsBd.getItemNameCas();
		ArrayList listTxtItemsCas = droDetailsBd.getCasTxtItems();

		session.setAttribute("casItemNameDto", droItemMapCas);
		session.setAttribute("listTxtItemCas", listTxtItemsCas);

		DROInspItemMap droItemMapSta = droDetailsBd.getItemNameSta();
		ArrayList listChkRadioSta = droDetailsBd.getStaChkRadioItems();
		ArrayList listChkItemsSta = droDetailsBd.getStaChkItems();
		ArrayList listRemarksSta = droDetailsBd.getStaRemarksItems();

		session.setAttribute("staItemNameDto", droItemMapSta);
		session.setAttribute("listChkItemsSta", listChkItemsSta);
		session.setAttribute("listChkRadioSta", listChkRadioSta);
		session.setAttribute("listRemarksSta", listRemarksSta);

		DROInspItemMap droItemMapTes = droDetailsBd.getItemNameTes();
		ArrayList listTextItemsTes = droDetailsBd.getTesTextItems();
		ArrayList listRemarkItemsTes = droDetailsBd.getTesRemarksItems();

		session.setAttribute("itemNameTesDto", droItemMapTes);
		session.setAttribute("listTextItemsTes", listTextItemsTes);
		session.setAttribute("listRemarkItemsTes", listRemarkItemsTes);

		DROInspItemMap droItemMapDes = droDetailsBd.getItemNameDes();
		ArrayList listTextItemsDes = droDetailsBd.getDesTextItems();
		ArrayList listChkRadioDes = droDetailsBd.getChkRadioDes();

		session.setAttribute("droItemNameDes", droItemMapDes);
		session.setAttribute("listTextItemDes", listTextItemsDes);
		session.setAttribute("listChkRadioItemsDes", listChkRadioDes);

		DROInspItemMap droItemMapFru = droDetailsBd.getItemNameFru();
		ArrayList listChkRadioFru = droDetailsBd.getChkRadioFru();
		ArrayList listDocItemsFru = droDetailsBd.getDocItemsFru();

		session.setAttribute("droItemNameFru", droItemMapFru);
		session.setAttribute("listChkRadioFru", listChkRadioFru);
		session.setAttribute("listDocItemsFru", listDocItemsFru);

		DROInspItemMap droIteMapLek = droDetailsBd.getItemNameLek();
		ArrayList listTxtItemsLek = droDetailsBd.getLekTxtItems();
		ArrayList listDocItemsLek = droDetailsBd.getLekDocItems();
		ArrayList listRemarksItemLek = droDetailsBd.getLekRemarkItems();

		session.setAttribute("lekItemNameDto", droIteMapLek);
		session.setAttribute("listTxtItemsLek", listTxtItemsLek);
		session.setAttribute("listDocItemsLek", listDocItemsLek);
		session.setAttribute("listRemarksLek", listRemarksItemLek);

		DROInspItemMap droIteMapLekha = droDetailsBd.getItemNameLekha();
		ArrayList listTxtLekha = droDetailsBd.getTxtItemsLekha();
		ArrayList listDocLekha = droDetailsBd.getDocItemsLekha();

		session.setAttribute("lekhaItemDto", droIteMapLekha);
		session.setAttribute("listTxtLekha", listTxtLekha);
		session.setAttribute("listDocLekha", listDocLekha);

		DROInspItemMap droItemMapRep = droDetailsBd.getItemNameRep();
		ArrayList listTxtItemsRep = droDetailsBd.getTxtItemsRep();
		ArrayList listRadioIteRep = droDetailsBd.getRadioItemsRep();

		session.setAttribute("repItemNameDto", droItemMapRep);
		session.setAttribute("listTxtRep", listTxtItemsRep);
		session.setAttribute("listRadioRep", listRadioIteRep);

		return "newdroinsp";
    }
	private void removeSessionAttributes(HttpSession session)
	{
		session.removeAttribute("pendingfiles");
		session.removeAttribute("auditfiles");
		session.removeAttribute("internalauditfiles");
		session.removeAttribute("stampfiles");
		
		session.removeAttribute("droDetailsDto");
		session.removeAttribute("alldropendinglist");
		
		session.removeAttribute("listpenchkitem");
		session.removeAttribute("pendingitemdto");
        session.removeAttribute("pendingtxtitems");
        session.removeAttribute("pendingremarks");
        session.removeAttribute("dromuditemdto");
        session.removeAttribute("listmudtxtitems");
        session.removeAttribute("listmudremarks");
        session.removeAttribute("droinsprrcdto");
        session.removeAttribute("listTxtItemsRRC");
        session.removeAttribute("listChkItemsRRC");
        session.removeAttribute("listChkRadioItemsRRC");
        session.removeAttribute("sroItemNameDto");
		session.removeAttribute("listTxtItemSro");
		session.removeAttribute("listChkItemSro");
        session.removeAttribute("rosItemNameDto");
		session.removeAttribute("listTxtItemsRos");
		session.removeAttribute("listChkRadioItemsRos");
		session.removeAttribute("listChkItemsRos");
        session.removeAttribute("casItemNameDto");
		session.removeAttribute("listTxtItemCas");
        session.removeAttribute("staItemNameDto");
		session.removeAttribute("listChkItemsSta");
		session.removeAttribute("listChkRadioSta");
		session.removeAttribute("listRemarksSta");
        session.removeAttribute("itemNameTesDto");
		session.removeAttribute("listTextItemsTes");
		session.removeAttribute("listRemarkItemsTes");
        session.removeAttribute("droItemNameDes");
		session.removeAttribute("listTextItemDes");
		session.removeAttribute("listChkRadioItemsDes");
        session.removeAttribute("droItemNameFru");
		session.removeAttribute("listChkRadioFru");
		session.removeAttribute("listDocItemsFru");
        session.removeAttribute("lekItemNameDto");
		session.removeAttribute("listTxtItemsLek");
		session.removeAttribute("listDocItemsLek");
		session.removeAttribute("listRemarksLek");
        session.removeAttribute("lekhaItemDto");
		session.removeAttribute("listTxtLekha");
		session.removeAttribute("listDocLekha");
        session.removeAttribute("repItemNameDto");
		session.removeAttribute("listTxtRep");
		session.removeAttribute("listRadioRep");
	}

			private String getFileExtension(String fileName) {
				int pos = fileName.lastIndexOf(".");
				String strExt = fileName.substring(pos + 1, fileName.length());
				return strExt;
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