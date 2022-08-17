
/* Copyright (c) 2007-08 WIPRO INFOTECH. All Rights Reserved.
 * 
 *  This software is the confidential and proprietary information of WIPRO
 *  ("Confidential Information"). You shall not
 *  disclose such Confidential Information and shall use it only in
 *  accordance with the terms of the license agreement you entered into
 *  with WIPRO.
 *  
 * WIPRO MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE 
 * SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF 
 * MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, OR NON-INFRINGEMENT. WIPRO SHALL NOT BE 
 * LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF 
 * USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS 
 * DERIVATIVES.
 */ 

/* 
 * FILE NAME: GuidelinePreparationAction.java
 * @author NIHAR R MISHRA
 * @version 1.0 
 * INITIAL CODING: 4th MARCH 2008
 * MODIFIED ON:    5th MAY 2008 
 * @DESCRIPTION:   THIS CLASS REPRESENTS THE ACTION FOR GUIDELINE PREPARATION 
 */


package com.wipro.igrs.guideline.action;

import in.cdac.ilcg.jasperreports.pdfexporter.JRPdfExporter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.guideline.bd.GuidelinePreparationBD;
import com.wipro.igrs.guideline.bd.GuidelineViewBD;
import com.wipro.igrs.guideline.constant.CommonConstant;
import com.wipro.igrs.guideline.dao.GuidelineApprovalDAO;
import com.wipro.igrs.guideline.dto.GuidelineDTO;
import com.wipro.igrs.guideline.form.GuidelinePreparationForm;
import com.wipro.igrs.guideline.rule.GuideLineRule;
import com.wipro.igrs.jasperBean.JasperBean;
import com.wipro.igrs.util.PropertiesFileReader;

/**
 * @author NIHAR M.
 *
 */
public class GuidelinePreparationAction extends BaseAction{

	String forwardJsp = new String(CommonConstant.MAKER1);
	private Logger logger = Logger.getLogger(GuidelinePreparationAction.class);
	
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @return NONE
	 * @throws Exception
	 */
	/* (non-Javadoc)
	 * @see com.wipro.igrs.baseaction.action.BaseAction#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.http.HttpSession)
	 */
	public ActionForward execute(
			ActionMapping mapping, 
			ActionForm form, 
			HttpServletRequest request,
			HttpServletResponse response,HttpSession session) 
	throws Exception {
		
		String page=request.getParameter("page");
		String districtName;
		long guidelineID;
	
		ActionMessages messages = new ActionMessages();
		logger.debug("Inside Action.");
		 
	      String roleId = (String)session.getAttribute("role");
		  String funId = (String)session.getAttribute("functionId");
		  String userId = (String)session.getAttribute("UserId");
		  String actId = (String)request.getParameter("actId");
		  
		  String officeId = (String)session.getAttribute("loggedToOffice");
		  String language = (String)session.getAttribute("languageLocale");

		  if (form != null) {
			GuidelinePreparationForm eForm = (GuidelinePreparationForm) form;
			GuidelinePreparationBD bd = new GuidelinePreparationBD();

			
			
			ArrayList financialYearList =bd.getFinancialYearList();
			eForm.setFinancialYearList(financialYearList);

			
			
			GuidelineDTO formDTO=eForm.getGuideDTO();
			formDTO.setLanguage(language);
			if(page!=null){
				if("create".equals(page)){ 
				
					//ArrayList districtList = bd.getDistrictList(officeDetails,actId);
				
					ArrayList districtList = bd.getDistrict(officeId,formDTO);
					eForm.setDistrictList(districtList);
					eForm.setTehsilList(new ArrayList());
					eForm.setWardList(new ArrayList());
					eForm.setMohallaList(new ArrayList());
					eForm.setPatwariList(new ArrayList());
					eForm.setVillageList(new ArrayList());
					eForm.setVersionList(new ArrayList());
					eForm.setNewMohallas(new ArrayList());
					 	
					formDTO.setFinancialYear("");
					formDTO.setFromDepositeDate("");
					formDTO.setToDepositeDate("");

					formDTO.setBasePeriodFrom("");
					formDTO.setBasePeriodTo("");
					formDTO.setAreaTypeID("");
					formDTO.setWardPatwari("");
					formDTO.setMohalla("");
					formDTO.setWard("");
					formDTO.setPatwari("");
					formDTO.setActionName("");
					eForm.setGuideDTO(new GuidelineDTO());
					session.removeAttribute(CommonConstant.MOHALLA_LIST);
					session.removeAttribute(CommonConstant.PATWARI_LIST);
					session.removeAttribute(CommonConstant.DTO);
					//************for log*****************//
					IGRSCommon save=null;

	                try {

	                        save = new IGRSCommon();

	                        save.saveactivitylog(userId, actId);

	                } catch (Exception e) {

	                       

	                        e.printStackTrace();

	                }

	                //****************************//
					forwardJsp = CommonConstant.MAKER1;
					logger.debug("Page is forwarded to :-    "+forwardJsp);
				}
			}
			
			if("PrintToPDF".equalsIgnoreCase(formDTO.getActionName()))
			{
				
				//TODO save in guideline print table
				String guideId=formDTO.getGuideLineID();
				boolean printdatainsert = bd.getinsertDataForPrint(guideId,formDTO);
				
				if(printdatainsert)
				{
					logger.debug("print data inserted successfully");
				}
				else
				{
					logger.debug("print data insertion failed");
				}
				String path = "";
				
				PropertiesFileReader proper = PropertiesFileReader.getInstance("resources.igrs");
				
			      String downloadPath=proper.getValue("GuidelineSavePath");
			 	downloadPath=downloadPath.replace("-", File.separator);

			 	formDTO.setLoggedOfficeId(officeId);
			 	String usertype=bd.getUserType(formDTO);
				
			 	if("en".equalsIgnoreCase(language))
			 	{
			 		path=downloadPath+File.separator+guideId+File.separator+"ENGLISH"+File.separator+"GuidelineFull.pdf";
			 	}
			 	else
			 	{
			 		path=downloadPath+File.separator+guideId+File.separator+"HINDI"+File.separator+"GuidelineFull.pdf";
			 	}
			 	File Fr1 = new File(path);
			 	
			 	if(Fr1.exists())
			 	{
			 		response.setContentType("application/download");

		  			  
		 			   response.setHeader("Content-Disposition", "attachment; filename="
		 						+ URLEncoder.encode(path,"UTF-8"));
		 			   
		 			   File fileToDownload = new File(path);
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
			 	}
			 	else
			 	{
			 		
			 		logger.debug("Guideline Published");
					messages.add("SUCCESS_MSG_1", new ActionMessage(
					"conf_msg_guideline_publish"));
					formDTO.setConfirmationChk("6");
					saveMessages(request, messages);
					/*for(int i=0;i<=1;i++){
						if(i==0)language="hi";
						else language="en";
					generatePDF(formDTO,eForm,language,response);//for generating the pdf
					}*/
					GuidelineViewBD gBD = new GuidelineViewBD();
					gBD.generateGuidePDF(formDTO, eForm, guideId, response, language);
					GuidelineApprovalDAO appdAO=new GuidelineApprovalDAO();
					appdAO.updateStatus(guideId);
					request.setAttribute(CommonConstant.SUCCESS_MSG_1, "Guideline has been published successfuly");
					forwardJsp= CommonConstant.PUBLISH_MAIN;
					logger.debug(forwardJsp);
				if("DRO".equalsIgnoreCase(usertype))
				{
					bd.getUpdatePrintStatus(formDTO);
					formDTO.setDownlaodChkId("C1");
					forwardJsp = CommonConstant.PUBLISH_MAIN;
				}
				else
				{
					bd.getUpdatePrintStatus(formDTO);
					formDTO.setDownlaodChkId("C");
					forwardJsp = CommonConstant.PUBLISH_MAIN;
				}
			 		
			 	}
				
			
					
				
			}
			
			if("SubmittoDR".equalsIgnoreCase(formDTO.getActionName()))
			{
				
				
				bd.getUpdateSubmitStatus(formDTO);
				formDTO.setDownlaodChkId("T");
				forwardJsp = CommonConstant.PUBLISH_MAIN;
				
			}
			if("DownloadPDF".equalsIgnoreCase(formDTO.getActionName()))
			{
				
				String guideId=formDTO.getGuideLineID();
				String path = "";
				
				PropertiesFileReader proper = PropertiesFileReader.getInstance("resources.igrs");
				
			      String downloadPath=proper.getValue("GuidelineSavePath");
			 	downloadPath=downloadPath.replace("-", File.separator);

			
				
			 	if("en".equalsIgnoreCase(language))
			 	{
			 		path=downloadPath+File.separator+guideId+File.separator+"ENGLISH"+File.separator+"GuidelineFull.pdf";
			 	}
			 	else
			 	{
			 		path=downloadPath+File.separator+guideId+File.separator+"HINDI"+File.separator+"GuidelineFull.pdf";
			 	}
			 	File Fr1 = new File(path);
			 	
			 	if(Fr1.exists())
			 	{
			 		response.setContentType("application/download");

		  			  
		 			   response.setHeader("Content-Disposition", "attachment; filename="
		 						+ URLEncoder.encode(path,"UTF-8"));
		 			   
		 			   File fileToDownload = new File(path);
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
			 	}
			 	else
			 	{
			 		formDTO.setDownlaodChkId("E");
			 		forwardJsp = CommonConstant.PUBLISH_MAIN;
			 	}
				
			}
			
			
			if(CommonConstant.GUIDELINE_PREPARATION.equalsIgnoreCase(eForm
					.getGuideDTO().getGuidelinePreparationForm()))
			{
				logger.debug("actionName:-"+formDTO.getActionName());
				
				/*if("district".equalsIgnoreCase(formDTO.getActionName()))
				{
					logger.debug("Inside DISTRICT");
					districtName = formDTO.getDistrict();
					logger.debug("<----"+districtName);
					
				}*/
				if("checkVersion".equalsIgnoreCase(formDTO.getActionName()))
				{
					
					String finan = formDTO.getFinancialYear();
					String district = formDTO.getDistrictID();
					eForm.setErrorMsg("");
					ArrayList versionList = bd.showStatusMaker(finan, district, language);
					if(versionList.size()>0)
					{
						eForm.setVersionList(versionList);
						request.setAttribute("versionList", versionList);
						request.setAttribute("distt", district);
						request.setAttribute("finan", finan);
					}
					else
					{
						formDTO.setCheck("Y");
						messages.add("MSG", new ActionMessage(
						"no_data_found"));
						saveMessages(request, messages);
					}
					
					forwardJsp = CommonConstant.MAKER1;
					
				}
				
				
				if("makeGuideline".equalsIgnoreCase(formDTO.getActionName()))
				{
					
					
					String distId = formDTO.getDistrictID();
					boolean flag=bd.insertTemplateData(distId, formDTO);
					
					if(flag)
					{
						logger.debug("template data updated successfully");
					}
					
					else
					{
						logger.debug("template data update failed");
					}
					
					GuideLineRule guideRule = new GuideLineRule();
					ArrayList tehsilList = new ArrayList();
					
					eForm.setVersionList(new ArrayList());
					String fYear = formDTO.getFinancialYear();
					String Financial[] = formDTO.getFinancialYear().split("-");
					String finanDistt = Financial[0].concat(Financial[1]).concat(distId);
					
					
					String fromDate = formDTO.getFromDepositeDate();
					String toDate = formDTO.getToDepositeDate();
					
					formDTO.setLoggedOfficeId(officeId);
					String count =bd.getGuidelineparentList(formDTO,fYear,fromDate,toDate);
					
					
					
					if("0".equalsIgnoreCase(count))
					{
						formDTO.setDownlaodChkId("");
					
					
					
				
					ArrayList start_end = bd.getStartEndDate( fYear);
					logger.debug("<-----After Calling---------->");
					String start= null;
					String end= null;
					if(start_end != null && start_end.size()==2)
					{
					 start =(String)start_end.get(0);
					 //logger.debug("<----START DATE--->"+start);
					 
					end =(String)start_end.get(1);
					//logger.debug("<-----"+start);
					
					}
				

				eForm = guideRule.checkDurationDates
				(fromDate, toDate, fYear, start, end, eForm);

				if (guideRule.isError()) {
					logger.debug("is error");
					eForm.setGuideDTO(formDTO);

					request.setAttribute(CommonConstant.DURATION_DATE_INVALID, "true");
					//request.setAttribute(CommonConstant.DURATION_DATES_ERRORS, errorList);
					logger.debug("Action: Inside Error");
				}

				else{
					
					guidelineID = bd.getGuidelineID(finanDistt,distId);
					if(guidelineID == 0)
					{
						guidelineID = Long.parseLong(finanDistt.concat("100"));
					}
					else
					{
						guidelineID = guidelineID+1;
					}
					long versionNo = guidelineID;
					logger.debug("guideline id"+guidelineID);
					//boolean valueInserted = bd.insertAllCombinations(guidelineID,distId);
					//logger.debug("insertion"+valueInserted);
					String guideID = Long.toString(guidelineID);
					String ver = Long.toString(versionNo);
					formDTO.setGuideLineID(guideID);
					request.setAttribute("guideID", guideID);
					request.setAttribute("ver", ver);
					
					tehsilList = bd.getTehsilListMakerCreation(distId, guidelineID, formDTO);
					eForm.setTehsilList(tehsilList);
					formDTO.setAreaFlag("1");
					ArrayList areaTypes = bd.getAreasTypeList(formDTO);
					eForm.setAreaTypeList(areaTypes);
					formDTO.setFirstCheck("Y");
					forwardJsp = CommonConstant.MAKER2;
				}
					}
					else
					{
						formDTO.setDownlaodChkId("DISABLE");
						forwardJsp = CommonConstant.PUBLISH_MAIN;
					}
					
			}
			
			if("resetPageAction".equalsIgnoreCase(formDTO.getActionName())){

				
					formDTO.setFinancialYear("");
					eForm.setErrorMsg("");
					formDTO.setDistrict("");
					formDTO.setDistrictID("");
					formDTO.setFromDepositeDate("");
					formDTO.setToDepositeDate("");
					forwardJsp = CommonConstant.MAKER1;
					
				}
			
			
				
			}
			
			
			if(CommonConstant.GUIDELINE_CREATION.equalsIgnoreCase(eForm
					.getGuideDTO().getGuidelineCreationForm()))
			{
				
				if("tehsilAction".equalsIgnoreCase(formDTO.getActionName()))
				{
					logger.debug("Inside Tehsil");
					logger.debug(formDTO.getTehsil());
					String tehID = formDTO.getTehsilID();
					logger.debug(formDTO.getTehsilID());
					
					long guideID = Long.parseLong(formDTO.getGuideLineID());
					
					ArrayList wardList = bd.getWardListMaker(tehID,guideID,formDTO );
					ArrayList patwariList = bd.getPatwariListMaker(tehID, guideID, formDTO);
					eForm.setWardList(wardList);
					eForm.setPatwariList(patwariList);

					session.removeAttribute(CommonConstant.MOHALLA_LIST);
					session.removeAttribute(CommonConstant.PATWARI_LIST);
					forwardJsp = CommonConstant.MAKER2;
				}
				
				/*if("dropDownWardPatwariAction".equalsIgnoreCase(formDTO.getActionName())){

					eForm.getGuideDTO().setWardPatwari(eForm.getGuideDTO().getWardPatwari());

					if(eForm.getGuideDTO().getWardPatwari().equalsIgnoreCase("P")){
						session.removeAttribute(CommonConstant.MOHALLA_LIST);
						eForm.getGuideDTO().setWard("");
					}
					if(eForm.getGuideDTO().getWardPatwari().equalsIgnoreCase("W")){
						session.removeAttribute(CommonConstant.PATWARI_LIST);
						eForm.getGuideDTO().setPatwari("");
					}

				}
				
				if("wardAction".equalsIgnoreCase(formDTO.getActionName()))
				{
					String[] wardAry=null;
					String type=formDTO.getWardPatwari();
					if(type != null && type.length() > 0){
						if(type.equalsIgnoreCase("W")){
							wardAry=formDTO.getWard().split("~");
						}
						else if(type.equalsIgnoreCase("P")){
							wardAry=formDTO.getPatwari().split("~");
						}
					}
					
					String fromDate = formDTO.getFromDepositeDate();
					String toDate = formDTO.getToDepositeDate();
					
					String durationFrom=formDTO.getDurationFrom();
					
					formDTO.setGuideLineID(formDTO.getGuideLineID());
					session.setAttribute(CommonConstant.DTO,formDTO);
					
					long guideID = Long.parseLong(formDTO.getGuideLineID());

					if(wardAry.length == 2)
					{
						ArrayList mohallalist = bd.getMohListMaker(wardAry[0],guideID,formDTO );
						formDTO.setMohList(mohallalist);
						eForm.setGuideDTO(formDTO);
						session.setAttribute(CommonConstant.MOHALLA_LIST, eForm);
						session.removeAttribute("patwarilist");
					}
				}*/
				
				if("previousAction".equalsIgnoreCase(eForm.getGuideDTO().getActionName()))
				{
					/*GuidelineDTO dtoForm = (GuidelineDTO)eForm.getGuideDTO();
					//logger.debug("IN PREVIOUS ACTION---->"+dtoForm.getDistrictID());
					//logger.debug("IN PREVIOUS ACTION---->"+dtoForm.getTehsilID());
					//logger.debug("IN PREVIOUS ACTION---->"+dtoForm.getWardID());
					//logger.debug("IN PREVIOUS ACTION---->"+dtoForm.getFromDepositeDate());
					GuidelineDTO dtoFrom = new  GuidelineDTO();
					String fromDate = dtoForm.getFromDepositeDate();
					String toDate = dtoForm.getToDepositeDate();
					String baseFrom = dtoForm.getBasePeriodFrom();
					String baseTo   = dtoForm.getBasePeriodTo();
					String areaType = dtoForm.getAreaName();

					dtoFrom.setDistrict(dtoForm.getDistrict());
					dtoFrom.setTehsil("");
					dtoFrom.setWard("");
					dtoFrom.setPatwari("");
					dtoFrom.setWardPatwari("");
					dtoFrom.setFinancialYear(dtoForm.getFinancialYear());
					dtoFrom.setFromDepositeDate(fromDate);
					dtoFrom.setToDepositeDate(toDate);
					dtoFrom.setBasePeriodFrom(baseFrom);
					dtoFrom.setBasePeriodTo(baseTo);
					dtoFrom.setAreaName(areaType);
					dtoFrom.setGuideLineID(formDTO.getGuideLineID());
					dtoFrom.setDistrictID(dtoForm.getDistrictID());
					dtoFrom.setTehsilID("");
					dtoFrom.setWardID("");
					dtoForm.setPatwariID("");
					dtoForm.setMohallaID("");
					
					eForm.setGuideDTO(dtoFrom);*/
					forwardJsp = CommonConstant.MAKER2;
				}
   
				if("mohallaAction".equalsIgnoreCase(formDTO.getActionName()))    
				{
					
					/*GuideLineRule guideRule = new GuideLineRule();
					GuidelineDTO dtoForm = eForm.getGuideDTO();
					String fYear = dtoForm.getFinancialYear();
					String fromDate = dtoForm.getFromDepositeDate();
					String toDate = dtoForm.getToDepositeDate();
					String baseFrom = dtoForm.getBasePeriodFrom();
					String baseTo   = dtoForm.getBasePeriodTo();
					String tehsil  = dtoForm.getTehsil();
					String[] wardAry = dtoForm.getWard().split("~");
					String districtAry = dtoForm.getDistrict();
					String mohallaId = dtoForm.getMohallaID();
					String areaType = dtoForm.getAreaTypeID() ;
					String[] patAry = dtoForm.getPatwari().split("~");


					GuidelineDTO dtoFrom = new  GuidelineDTO();
					dtoFrom.setDistrict(dtoForm.getDistrict());
					dtoFrom.setTehsil(dtoForm.getTehsil());
					dtoFrom.setWard(dtoForm.getWard());
					dtoFrom.setPatwari(dtoForm.getPatwari());
					dtoFrom.setWardPatwari(dtoForm.getWardPatwari());
					dtoFrom.setFinancialYear(dtoForm.getFinancialYear());
					dtoFrom.setFromDepositeDate(fromDate);
					dtoFrom.setToDepositeDate(toDate);
					dtoFrom.setBasePeriodFrom(baseFrom);
					dtoFrom.setBasePeriodTo(baseTo);
					dtoFrom.setAreaTypeID(areaType);
					dtoForm.setGuideLineID(dtoForm.getGuideLineID());
				

					
					

					if(wardAry!=null & wardAry.length==2) {
						dtoForm.setWardID(wardAry[0]);
						dtoForm.setWard(wardAry[1]);
					}
					//Added by simran
					if(patAry!=null & patAry.length==2) {
						dtoForm.setPatwariID(patAry[0]);
						dtoForm.setPatwari(patAry[1]);
					}
					// end 
					
					String[] areaArray = dtoForm.getAreaTypeID().split("~");

					if(areaArray!=null && areaArray.length == 2)
					{
						dtoForm.setAreaTypeID(areaArray[0]);
						dtoForm.setAreaName(areaArray[1]);
					}
					String[] mohArray = mohallaId.split("=");
					if(mohArray!=null && mohArray.length ==2) {
						dtoForm.setMohallaID(mohArray[0]);
						dtoForm.setMohalla(mohArray[1]);
					}*/
					
					//Added by simran
					
					/*String fYear = formDTO.getFinancialYear();
					String[] fYear1 = new String[] { fYear};
					
					ArrayList start_end = bd.getStartEndDate( fYear1[0]);
					logger.debug("<-----After Calling---------->");
					String start= null;
					String end= null;
					if(start_end != null && start_end.size()==2)
					{
						 start =(String)start_end.get(0);
						 //logger.debug("<----START DATE--->"+start);
						 
						end =(String)start_end.get(1);
						//logger.debug("<-----"+start);
						
					}*/
					
					
					String id = formDTO.getMohallaID();
					if(id==null)
					{
						formDTO.setMohallaChkId("5");
					}
					else
					{	
					id=id.split("~")[0];
					formDTO.setMohallaChkId(id);
					}
					
					String appliclause= bd.getMohallaAppliclause(formDTO);
					
					ArrayList constructRateList = bd.getconstructRateList(appliclause);
					ArrayList list1 = new ArrayList();
					if(constructRateList.size()!=0)
					{
						
						 for (int i = 0; i < constructRateList.size(); i++) {
								list1 = (ArrayList)constructRateList.get(i);
					
						formDTO.setRccVal(Double.parseDouble((String)list1.get(0)));
						request.setAttribute("RCCCOST",String.valueOf(formDTO.getRccVal()));
						formDTO.setRbcVal(Double.parseDouble((String)list1.get(1)));
						request.setAttribute("RBCCOST",String.valueOf(formDTO.getRbcVal()));
						formDTO.setTinVal(Double.parseDouble((String)list1.get(2)));
						request.setAttribute("TINCOST",String.valueOf(formDTO.getTinVal()));
						formDTO.setKabeluVal(Double.parseDouble((String)list1.get(3)));
						request.setAttribute("KABELUCOST",String.valueOf(formDTO.getKabeluVal()));
						
						
						 }
					}
					else
					{
						
						formDTO.setRccVal(0.0);
						request.setAttribute("RCCCOST",String.valueOf(formDTO.getRccVal()));
						formDTO.setRbcVal(0.0);
						request.setAttribute("RBCCOST",String.valueOf(formDTO.getRbcVal()));
						formDTO.setTinVal(0.0);
						request.setAttribute("TINCOST",String.valueOf(formDTO.getTinVal()));
						formDTO.setKabeluVal(0.0);
						request.setAttribute("KABELUCOST",String.valueOf(formDTO.getKabeluVal()));
						
					}
					
					
						ArrayList individualMohallaAttributes2 = bd.getIndividualMohallaDetails2(formDTO);
							eForm.setMohallaAllDetails(individualMohallaAttributes2);
						
						
						forwardJsp = CommonConstant.MOHALLA_DETAILS_SUCCESS;
					
				}
			/*
			if(CommonConstant.RESET_PAGE.equalsIgnoreCase(eForm
					.getGuideDTO().getMohalaDisplayForm()))
			{
				if("previousAction".equalsIgnoreCase(eForm.getGuideDTO().getActionName()))
				{
					GuidelineDTO dtoForm = (GuidelineDTO)session.getAttribute(CommonConstant.DTO);
					GuidelineDTO dtoFrom = new  GuidelineDTO();
					String fromDate = dtoForm.getFromDepositeDate();
					String toDate = dtoForm.getToDepositeDate();
					String baseFrom = dtoForm.getBasePeriodFrom();
					String baseTo   = dtoForm.getBasePeriodTo();
					String areaType = dtoForm.getAreaName();

					dtoFrom.setDistrict(dtoForm.getDistrict());
					dtoFrom.setTehsil(dtoForm.getTehsil());
					dtoFrom.setWard(dtoForm.getWard());
					dtoFrom.setPatwari(dtoForm.getPatwari());
					dtoFrom.setWardPatwari(dtoForm.getWardPatwari());
					dtoFrom.setFinancialYear(dtoForm.getFinancialYear());
					dtoFrom.setFromDepositeDate(fromDate);
					dtoFrom.setToDepositeDate(toDate);
					dtoFrom.setBasePeriodFrom(baseFrom);
					dtoFrom.setBasePeriodTo(baseTo);
					dtoFrom.setAreaName(areaType);

					eForm.setGuideDTO(dtoFrom);
					forwardJsp = CommonConstant.CREATE_GUIDELINE;
				}*/
				if(CommonConstant.NEXT_PAGE_ACTION.equalsIgnoreCase(eForm.getGuideDTO().getActionName())){
					logger.debug("GOING TO READ ONLY PAGE");
					forwardJsp = CommonConstant.NEXT_PAGE;
				}
				
				if("resetPageMaker2".equalsIgnoreCase(eForm.getGuideDTO().getActionName()))
				{
					logger.debug("reset action");
					formDTO.setTehsilID("");
					formDTO.setTehsil("");
					formDTO.setAreaTypeID("");
					formDTO.setAreaName("");
					formDTO.setWardID("");
					formDTO.setWard("");
					formDTO.setPatwari("");
					formDTO.setMohalla("");
					formDTO.setMohallaID("");
					formDTO.setSubAreaId("");
					formDTO.setSubAreaName("");
					forwardJsp = CommonConstant.MAKER2;
					
				}
				if("previousPageMaker".equalsIgnoreCase(formDTO.getActionName()))
				{
					logger.debug("previous page");
					formDTO.setTehsilID("");
					formDTO.setTehsil("");
					formDTO.setAreaTypeID("");
					formDTO.setAreaName("");
					formDTO.setWardID("");
					formDTO.setWard("");
					formDTO.setPatwari("");
					formDTO.setMohalla("");
					formDTO.setMohallaID("");
					formDTO.setSubAreaId("");
					formDTO.setSubAreaName("");
					if(eForm.getVersionList().size()>0)
					{
						request.setAttribute("versionList", eForm.getVersionList());
					}
					forwardJsp = CommonConstant.MAKER1;
				}
				
				if("getSubAreaList".equalsIgnoreCase(formDTO.getActionName()))
				{
					String urbanFlag = "N";
					formDTO.setUrbanFlag(urbanFlag);
					if(formDTO.getAreaTypeID().equals("2"))
						urbanFlag = "Y";
					formDTO.setUrbanFlag(urbanFlag);
					if(formDTO.getFirstCheck() != null)
						{
							if(formDTO.getFirstCheck().equals("Y"))
								eForm.setSubAreaList(bd.getSubAreaFirst(language, formDTO.getAreaTypeID(),formDTO.getTehsilID(), urbanFlag));
							else
								eForm.setSubAreaList(bd.getSubArea(language, formDTO.getAreaTypeID(),formDTO.getTehsilID(), urbanFlag,formDTO.getGuideLineID()));
						}
					else
					{
						eForm.setSubAreaList(bd.getSubArea(language, formDTO.getAreaTypeID(),formDTO.getTehsilID(), urbanFlag,formDTO.getGuideLineID()));
					}
					eForm.setWardList(new ArrayList());
					formDTO.setWardID("");
					eForm.setMohallaList(new ArrayList());
					formDTO.setMohallaID("");
					
					forwardJsp = CommonConstant.MAKER2;
				}
				else if("getWardPatwariList".equalsIgnoreCase(formDTO.getActionName()))
				{
					if(formDTO.getFirstCheck() != null)
					{
						if(formDTO.getFirstCheck().equals("Y"))
							 eForm.setWardList(bd.getWardPatwariFirst(language,formDTO.getSubAreaId(),formDTO.getTehsilID()));
						else
							 eForm.setWardList(bd.getWardPatwari(language,formDTO.getSubAreaId(),formDTO.getTehsilID(),formDTO.getGuideLineID()));
					}
					else
					{
						ArrayList wardcheck = new ArrayList();
						 //wardcheck = bd.getWardPatwaricheck(language,formDTO.getSubAreaId(),formDTO.getTehsilID(),formDTO.getGuideLineID());
						
						 
						eForm.setWardList(bd.getWardPatwari(language,formDTO.getSubAreaId(),formDTO.getTehsilID(),formDTO.getGuideLineID()));
						 
					}
					eForm.setMohallaList(new ArrayList());
					formDTO.setMohallaID("");
					 forwardJsp = CommonConstant.MAKER2;
				}
				else if("getMohallaColony".equalsIgnoreCase(formDTO.getActionName()))
				{
					if(formDTO.getFirstCheck() != null)
					{
						if(formDTO.getFirstCheck().equals("Y"))
							eForm.setMohallaList(bd.getColonyNameFirst(language, formDTO.getWardID()));
						else
							eForm.setMohallaList(bd.getColonyName(language, formDTO.getWardID(),formDTO.getGuideLineID()));
					}
					else
					{
						eForm.setMohallaList(bd.getColonyName(language, formDTO.getWardID(),formDTO.getGuideLineID()));
					}
					//logger.debug("size of colony "+eForm.getMohallaList().size());
					forwardJsp = CommonConstant.MAKER2;
				}
				
			}

			if(CommonConstant.MOHALLA_DETAILS_READABLE.equalsIgnoreCase(eForm.getGuideDTO().
					getMohallaDetailsReadableMode())){				
				if("mohallaSaveAction".equalsIgnoreCase(eForm.getGuideDTO().getActionName())){

					ArrayList mohallaDetails = eForm.getMohallaAllDetails();
					boolean insertMohallaDetails=false;

					int size = mohallaDetails != null?mohallaDetails.size():0;
					ArrayList gda=new ArrayList();

					GuidelineDTO gDTO = eForm.getGuideDTO();
					

					request.setAttribute("gDTO", gDTO);
					for(int i = 0; i < size;i++ ){
						GuidelineDTO gd = (GuidelineDTO)mohallaDetails.get(i);
						gda.add(gd);
					}
				

					request.getAttribute("gDTO");

					String dFrom = formDTO.getFromDepositeDate();
					String dTo   = formDTO.getToDepositeDate();
					//logger.debug("!!!!!!!!!!!!!in save Action"+formDTO.getGuideLineID());
					//logger.debug("!!!!!!!!!!!!!in save Action"+formDTO.getDistrictID());

					//boolean insertMohallaDetails = bd.insertIndividualMohallaDetails(gda, gDTO, dFrom, dTo,roleId,funId,userId);
					///ArrayList guidelineList = bd.deriveGuidelineDetails(gda); 
					boolean flag =bd.checkForAlreadySubmitted(formDTO.getGuideLineID(),gDTO);
					if(flag)
					{
						insertMohallaDetails=false;
					}
					else
					{
						 insertMohallaDetails = bd.insertPendingGuidelineValues(gda, gDTO, dFrom, dTo,roleId,funId,userId,"C");
					}
				
					
					
					if(insertMohallaDetails)
					{
						int i=0;
						logger.debug("Mohalla Details inserted successfully...");
						messages.add("SUCCESS_MSG", new ActionMessage(
						"conf_msg_guideline_creation"));
						saveMessages(request, messages);
						formDTO.setConfirmationChk("1");
						formDTO.setUrbanFlag("Y");
						eForm.setMohallaList(bd.getColonyName(language, formDTO.getWardID(),formDTO.getGuideLineID()));
						if(eForm.getMohallaList()==null || eForm.getMohallaList().size()==0){
							eForm.setWardList(bd.getWardPatwari(language,formDTO.getSubAreaId(),formDTO.getTehsilID(),formDTO.getGuideLineID()));
						}
						
						if(eForm.getWardList()==null || eForm.getWardList().size()==0)
						{
							eForm.setSubAreaList(bd.getSubArea(language, formDTO.getAreaTypeID(),formDTO.getTehsilID(),formDTO.getUrbanFlag(),formDTO.getGuideLineID()));
						}
						
						if (eForm.getSubAreaList()==null || eForm.getSubAreaList().size()==0)
						{
							formDTO.setAreaFlag("2");
							
							ArrayList areaTypes = bd.getAreasTypeList(formDTO);
							eForm.setAreaTypeList(areaTypes);
							
						}
						
						
						if(eForm.getAreaTypeList()==null || eForm.getAreaTypeList().size()==0)
						{
							if(("Y").equalsIgnoreCase(formDTO.getGuidFlag()))
							{
								i=1;
								eForm.setAreaTypeList(new ArrayList());
								
								formDTO.setDownlaodChkId("N");
								forwardJsp = CommonConstant.PUBLISH_MAIN;
							}
							else
							{ 
							formDTO.setAreaFlag("1");
							formDTO.setAreaTypeID("");
							ArrayList areaTypes = bd.getAreasTypeList(formDTO);
							eForm.setAreaTypeList(areaTypes);
							}
							Long guideID = Long.parseLong(formDTO.getGuideLineID());
							eForm.setTehsilList(bd.getTehsilListMaker(formDTO.getDistrictID(),guideID, formDTO));
						}
						
						if(i==0)
						{
						request.setAttribute(CommonConstant.SUCCESS_MSG,"Guideline successfully created for ");
						
						forwardJsp = CommonConstant.MAKER2;
						}
				}
					
					else
					{
						logger.debug("Mohalla Details not inserted ...");
						if(flag)
						{
							
							
							int i=0;
							
							formDTO.setUrbanFlag("Y");
							eForm.setMohallaList(bd.getColonyName(language, formDTO.getWardID(),formDTO.getGuideLineID()));
							if(eForm.getMohallaList()==null || eForm.getMohallaList().size()==0){
								eForm.setWardList(bd.getWardPatwari(language,formDTO.getSubAreaId(),formDTO.getTehsilID(),formDTO.getGuideLineID()));
							}
							
							if(eForm.getWardList()==null || eForm.getWardList().size()==0)
							{
								eForm.setSubAreaList(bd.getSubArea(language, formDTO.getAreaTypeID(),formDTO.getTehsilID(),formDTO.getUrbanFlag(),formDTO.getGuideLineID()));
							}
							
							if (eForm.getSubAreaList()==null || eForm.getSubAreaList().size()==0)
							{
								formDTO.setAreaFlag("2");
								
								ArrayList areaTypes = bd.getAreasTypeList(formDTO);
								eForm.setAreaTypeList(areaTypes);
								
							}
							
							
							if(eForm.getAreaTypeList()==null || eForm.getAreaTypeList().size()==0)
							{
								if(("Y").equalsIgnoreCase(formDTO.getGuidFlag()))
								{
									i=1;
									eForm.setAreaTypeList(new ArrayList());
									
									formDTO.setDownlaodChkId("N");
									forwardJsp = CommonConstant.PUBLISH_MAIN;
								}
								else
								{ 
								formDTO.setAreaFlag("1");
								formDTO.setAreaTypeID("");
								ArrayList areaTypes = bd.getAreasTypeList(formDTO);
								eForm.setAreaTypeList(areaTypes);
								}
								Long guideID = Long.parseLong(formDTO.getGuideLineID());
								eForm.setTehsilList(bd.getTehsilListMaker(formDTO.getDistrictID(),guideID, formDTO));
							}
							
							if(i==0)
							{
								formDTO.setConfirmationChk("25");
								request.setAttribute(CommonConstant.FAILURE_MSG,"Guideline creation failed ");
							
							forwardJsp = CommonConstant.MAKER2;
							}
							

							
						}
						else
						{
							messages.add("FAILURE_MSG", new ActionMessage(
							"conf_msg_guideline_creation_failed"));
							saveMessages(request, messages);
							formDTO.setConfirmationChk("1");
							request.setAttribute(CommonConstant.FAILURE_MSG,"Guideline creation failed ");
						}
						
						//forwardJsp = CommonConstant.PUBLISH_MAIN;
						forwardJsp = CommonConstant.MAKER2;
					}
				}

				if("modifyMohallaDetails".equalsIgnoreCase(eForm.getGuideDTO().getActionName())){
					String id = formDTO.getMohallaID();
					
					if(id==null)
					{
						formDTO.setMohallaChkId("5");
					}
					else
					{	
					id=id.split("~")[0];
					formDTO.setMohallaChkId(id);
					}
					
					String appliclause= bd.getMohallaAppliclause(formDTO);
					
					ArrayList constructRateList = bd.getconstructRateList(appliclause);
					ArrayList list1 = new ArrayList();
					if(constructRateList.size()!=0)
					{
						
						 for (int i = 0; i < constructRateList.size(); i++) {
								list1 = (ArrayList)constructRateList.get(i);
					
						formDTO.setRccVal(Double.parseDouble((String)list1.get(0)));
						request.setAttribute("RCCCOST",String.valueOf(formDTO.getRccVal()));
						formDTO.setRbcVal(Double.parseDouble((String)list1.get(1)));
						request.setAttribute("RBCCOST",String.valueOf(formDTO.getRbcVal()));
						formDTO.setTinVal(Double.parseDouble((String)list1.get(2)));
						request.setAttribute("TINCOST",String.valueOf(formDTO.getTinVal()));
						formDTO.setKabeluVal(Double.parseDouble((String)list1.get(3)));
						request.setAttribute("KABELUCOST",String.valueOf(formDTO.getKabeluVal()));
						
						
						 }
					}
					else
					{
						
						formDTO.setRccVal(0.0);
						request.setAttribute("RCCCOST",String.valueOf(formDTO.getRccVal()));
						formDTO.setRbcVal(0.0);
						request.setAttribute("RBCCOST",String.valueOf(formDTO.getRbcVal()));
						formDTO.setTinVal(0.0);
						request.setAttribute("TINCOST",String.valueOf(formDTO.getTinVal()));
						formDTO.setKabeluVal(0.0);
						request.setAttribute("KABELUCOST",String.valueOf(formDTO.getKabeluVal()));
						
					}					
					logger.debug("Hidden values are matched for:-    modifyMohallaDetails");
					forwardJsp = CommonConstant.MOHALLA_DETAILS_SUCCESS;
					logger.debug("Forwarded to:-  "+forwardJsp);
				}
			}
			
			
				
			}
			
		logger.debug("forwardJsp:-"+forwardJsp);
		return mapping.findForward(forwardJsp);
	}
	
	
	
	public void generatePDF(GuidelineDTO formDTO,GuidelinePreparationForm eForm,String lang,HttpServletResponse response) throws Exception{
		formDTO.setGuideType("");
		GuidelineViewBD gBD = new GuidelineViewBD();
		HashMap praroop1Map = new HashMap();
		HashMap praroop2Map = new HashMap();
		HashMap praroop3Map = new HashMap();
		 ArrayList jasper=new ArrayList();
		 ArrayList jasperPlot=new ArrayList();
		 ArrayList jasperBuild=new ArrayList();
		 ArrayList jasperAgri=new ArrayList();
		 Boolean b=true;//pallavi
		
		eForm.setGuidelineDataMapPraroop1(gBD.getGuidelineDataPraroop1(formDTO,eForm,lang));
	//	eForm.setGuidelineDataMapPraroop2(gBD.getGuidelineDataPraroop2(formDTO,eForm,lang));
		//eForm.setGuidelineDataMapPraroop3(gBD.getGuidelineDataPraroop3(formDTO,eForm,lang));
		//for Praroop 1
		 praroop1Map = eForm.getGuidelineDataMapPraroop1();
		   
		    Set mapSet = (Set)praroop1Map.entrySet();
			Iterator mapIterator = mapSet.iterator();
			while(mapIterator.hasNext())
			{
				 JasperBean jb=new JasperBean();
				Map.Entry mapEntry = (Map.Entry)mapIterator.next();
				String key[] = ((String)mapEntry.getKey()).split("~");
				String tehsil = key[1];
				String ward = key[4];
				String mohalla = key[5];
				String areaName = key[2];
				String subAreaName = key[3];
				jb.setPlotTehsil(tehsil);
				jb.setPlotAreaName(areaName);
				jb.setPlotSubAreaName(subAreaName);
				jb.setPlotWard(ward);
				jb.setPlotMohallaColony(mohalla);
				jb.setPlotDistrict(eForm.getGuideDTO().getDistrict());
				jb.setPlotFinancialYear(eForm.getGuideDTO().getFinancialYear());
					
			    
			    ArrayList list = (ArrayList)mapEntry.getValue();
			    //for plot
			    GuidelineDTO gDTO1 = (GuidelineDTO)list.get(0);
				jb.setPlotResi(gDTO1.getGuideLineValue());
				GuidelineDTO gDTO2 = (GuidelineDTO)list.get(1);
				jb.setPlotComm(gDTO2.getGuideLineValue());
				GuidelineDTO gDTO3 = (GuidelineDTO)list.get(2);
				jb.setPlotIndus(gDTO3.getGuideLineValue());
			    //for building
				GuidelineDTO gDTO4 = (GuidelineDTO)list.get(3);
				jb.setBuildingRcc(gDTO4.getGuideLineValue());
				GuidelineDTO gDTO5 = (GuidelineDTO)list.get(4);
				jb.setBuildingRbc(gDTO5.getGuideLineValue());
				GuidelineDTO gDTO6 = (GuidelineDTO)list.get(5);
				jb.setBuildingtinShade(gDTO6.getGuideLineValue());
				GuidelineDTO gDTO7 = (GuidelineDTO)list.get(6);
				jb.setBuildingKachaKab(gDTO7.getGuideLineValue());
				GuidelineDTO gDTO8 = (GuidelineDTO)list.get(7);
				jb.setBuildingShop(gDTO8.getGuideLineValue());
				GuidelineDTO gDTO9 = (GuidelineDTO)list.get(8);
				jb.setBuildingOffice(gDTO9.getGuideLineValue());
				GuidelineDTO gDTO10 = (GuidelineDTO)list.get(9);
				jb.setBuildingGodown(gDTO10.getGuideLineValue());
				GuidelineDTO gDTO11 = (GuidelineDTO)list.get(10);
				jb.setBuildingResi(gDTO11.getGuideLineValue());
				GuidelineDTO gDTO12 = (GuidelineDTO)list.get(11);
				jb.setBuildingComm(gDTO12.getGuideLineValue());
				//for agriculture
				GuidelineDTO gDTO13 = (GuidelineDTO)list.get(12);
				jb.setAgriIrri(gDTO13.getGuideLineValue());
				GuidelineDTO gDTO14 = (GuidelineDTO)list.get(13);
				jb.setAgriUnIrri(gDTO14.getGuideLineValue());
				GuidelineDTO gDTO15 = (GuidelineDTO)list.get(14);
				jb.setAgriLandResi(gDTO15.getGuideLineValue());
				GuidelineDTO gDTO16 = (GuidelineDTO)list.get(15);
				jb.setAgriLandComm(gDTO16.getGuideLineValue());
			    jasper.add(jb);
				   jb.setJasperList(jasper);
				   
				   jasperPlot.add(jb);
				   jb.setJasperListPlot(jasperPlot);
			} 
			/*//for Praroop 2
		    praroop2Map = eForm.getGuidelineDataMapPraroop2();
			    Set mapSet2 = (Set)praroop2Map.entrySet();
				Iterator mapIterator2 = mapSet2.iterator();
				while(mapIterator2.hasNext())
				{  JasperBean jb=new JasperBean();
					Map.Entry mapEntry2 = (Map.Entry)mapIterator2.next();
					String key2[] = ((String)mapEntry2.getKey()).split("~");
					String tehsil2 = key2[1];
					String ward2 = key2[4];
					String mohalla2 = key2[5];
					String Area2 = key2[2];
					String subArea2 = key2[3];
					
					jb.setBuildingTehsil(tehsil2);
					jb.setBuildingAreaName(Area2);
					jb.setBuildingSubAreaName(subArea2);
					jb.setBuildingWard(ward2);
					jb.setBuildingMohallaColony(mohalla2);
					jb.setPlotFinancialYear(eForm.getGuideDTO().getFinancialYear());
					jb.setPlotDistrict(eForm.getGuideDTO().getDistrict());
					
				    ArrayList list2 = (ArrayList)mapEntry2.getValue();
					GuidelineDTO gDTO1 = (GuidelineDTO)list2.get(0);
					jb.setBuildingRcc(gDTO1.getGuideLineValue());
					GuidelineDTO gDTO2 = (GuidelineDTO)list2.get(1);
					jb.setBuildingRbc(gDTO2.getGuideLineValue());
					GuidelineDTO gDTO3 = (GuidelineDTO)list2.get(2);
					jb.setBuildingtinShade(gDTO3.getGuideLineValue());
					GuidelineDTO gDTO4 = (GuidelineDTO)list2.get(3);
					jb.setBuildingKachaKab(gDTO4.getGuideLineValue());
					GuidelineDTO gDTO5 = (GuidelineDTO)list2.get(4);
					jb.setBuildingShop(gDTO5.getGuideLineValue());
					GuidelineDTO gDTO6 = (GuidelineDTO)list2.get(5);
					jb.setBuildingOffice(gDTO6.getGuideLineValue());
					GuidelineDTO gDTO7 = (GuidelineDTO)list2.get(6);
					jb.setBuildingGodown(gDTO7.getGuideLineValue());
					GuidelineDTO gDTO8 = (GuidelineDTO)list2.get(7);
					jb.setBuildingResi(gDTO8.getGuideLineValue());
					GuidelineDTO gDTO9 = (GuidelineDTO)list2.get(8);
					jb.setBuildingComm(gDTO9.getGuideLineValue());
				  
				    jasper.add(jb);
				    
					   jb.setJasperList(jasper);
					   
					   jasperBuild.add(jb);
					   jb.setJasperListBuild(jasperBuild);
					   
			   }
				
				//for praroop 3
				 praroop3Map = eForm.getGuidelineDataMapPraroop3();
				    
				    Set mapSet3 = (Set)praroop3Map.entrySet();
					Iterator mapIterator3 = mapSet3.iterator();
					while(mapIterator3.hasNext())
					{
						JasperBean jb=new JasperBean();
						Map.Entry mapEntry = (Map.Entry)mapIterator3.next();
						String key[] = ((String)mapEntry.getKey()).split("~");
						String tehsil = key[1];
						String area = key[4];
						String subArea = key[5];
						String ward = key[2];
						String mohalla = key[3];
						
						
						jb.setAgriTehsil(tehsil);
						jb.setAgriAreaName(area);
						jb.setAgriSubAreaName(subArea);
						jb.setAgriWard(ward);
						jb.setAgriMohallaColony(mohalla);
						jb.setPlotFinancialYear(eForm.getGuideDTO().getFinancialYear());
						jb.setPlotDistrict(eForm.getGuideDTO().getDistrict());
						
					
					    ArrayList list = (ArrayList)mapEntry.getValue();
					    GuidelineDTO gDTO1 = (GuidelineDTO)list.get(0);
						jb.setAgriIrri(gDTO1.getGuideLineValue());
						GuidelineDTO gDTO2 = (GuidelineDTO)list.get(1);
						jb.setAgriUnIrri(gDTO2.getGuideLineValue());
						GuidelineDTO gDTO3 = (GuidelineDTO)list.get(2);
						jb.setAgriLandResi(gDTO3.getGuideLineValue());
						GuidelineDTO gDTO4 = (GuidelineDTO)list.get(3);
						jb.setAgriLandComm(gDTO4.getGuideLineValue());
					  
					    jasper.add(jb);
						   jb.setJasperList(jasper);
						   
						   jasperAgri.add(jb);
						   jb.setJasperListAgri(jasperAgri);
						   
					} */
					
					//code commented for all PDF
					/*String masterReportFileName="";
					String subReportPlotFileName="";
					String subReportBuildFileName="";
					String subReportAgriFileName="";
					String destFileName="";*/
					String plotMasterReportFileName ="";
					String plotSubReportFileName ="";
					String plotDestFileName ="";
					/*String buidlingMasterReportFileName ="";
					String buidlingSubReportFileName ="";
					String buidlingDestFileName ="";
					String agriMasterReportFileName ="";
					String agriSubReportFileName ="";
					String agriDestFileName ="";*/
					
					 PropertiesFileReader property = PropertiesFileReader.getInstance("resources.igrs");
					 	//JRXML all report
					 if(lang.equalsIgnoreCase("hi")){
						/*masterReportFileName =property.getValue("pdf.location").concat("allMasterView.jasper");
						subReportPlotFileName =property.getValue("pdf.location").concat("allPlotSubView.jasper");
						subReportBuildFileName = property.getValue("pdf.location").concat("allBuildSubView.jasper");
					    subReportAgriFileName = property.getValue("pdf.location").concat("allAgriSubView.jasper");
						destFileName =property.getValue("pdf.location").concat("allMasterView.JRprint");*/
						plotMasterReportFileName =property.getValue("pdf.location").concat("masterReports.jasper");
						plotSubReportFileName =property.getValue("pdf.location").concat("guideline.jasper");
						plotDestFileName =property.getValue("pdf.location").concat("masterReports.JRprint");
						/*buidlingMasterReportFileName =property.getValue("pdf.location").concat("buildingMasterView.jasper");
						buidlingSubReportFileName =property.getValue("pdf.location").concat("buildingSubView.jasper");
						buidlingDestFileName =property.getValue("pdf.location").concat("buildingDestinationView.JRprint"); 
						agriMasterReportFileName =property.getValue("pdf.location").concat("agriMasterView.jasper");
						agriSubReportFileName =property.getValue("pdf.location").concat("agriSubView.jasper");
						agriDestFileName =property.getValue("pdf.location").concat("agriDestinationView.JRprint");*/
						
					 }else{
						/*masterReportFileName =property.getValue("pdf.location").concat("allMasterViewEng.jasper");
						subReportPlotFileName =property.getValue("pdf.location").concat("allPlotSubViewEng.jasper");
						subReportBuildFileName = property.getValue("pdf.location").concat("allBuildSubViewEng.jasper");
					    subReportAgriFileName = property.getValue("pdf.location").concat("allAgriSubViewEng.jasper");
					    destFileName =property.getValue("pdf.location").concat("allMasterViewEng.JRprint");*/
					    plotMasterReportFileName =property.getValue("pdf.location").concat("masterReports.jasper");
						plotSubReportFileName =property.getValue("pdf.location").concat("guideline.jasper");
						plotDestFileName =property.getValue("pdf.location").concat("masterReports.JRprint"); 
						/*buidlingMasterReportFileName =property.getValue("pdf.location").concat("buildingMasterViewEng.jasper");
						buidlingSubReportFileName =property.getValue("pdf.location").concat("buildingSubViewEng.jasper");
						buidlingDestFileName =property.getValue("pdf.location").concat("buildingDestinationViewEng.JRprint"); 
						agriMasterReportFileName =property.getValue("pdf.location").concat("agriMasterViewEng.jasper");
						agriSubReportFileName =property.getValue("pdf.location").concat("agriSubViewEng.jasper");
						agriDestFileName =property.getValue("pdf.location").concat("agriDestinationViewEng.JRprint");*/
					 }
					 
					
						
						
						String path=property.getValue("pdf.location");
					    
					    
								 JRBeanCollectionDataSource beanColDataSource =
							            new JRBeanCollectionDataSource(jasper);
								 JRBeanCollectionDataSource plotBeanColDataSource =
							            new JRBeanCollectionDataSource(jasperPlot);
								 JRBeanCollectionDataSource buildBeanColDataSource =
							            new JRBeanCollectionDataSource(jasperBuild);
								 JRBeanCollectionDataSource agriBeanColDataSource =
							            new JRBeanCollectionDataSource(jasperAgri);
								 try{
									 
									 
									  String uploadDir="";
								         PropertiesFileReader prObj;
								 		try {
								 			prObj = PropertiesFileReader.getInstance("resources.igrs");
								 			uploadDir=prObj.getValue("GuidelineSavePath");
								 			System.out.println(uploadDir);
								 		} catch (Exception e1) {
								 			// TODO Auto-generated catch block
								 			e1.printStackTrace();
								 		}
								 		
								 		//String filePath = null; 
										//String folName = "D:\\" +"Upload\\";
								 		String filePath=uploadDir.replace("//", File.separator);
									/*	String folNameSplit[]=uploadDir.split("-");
										String fol="";
										int k=0;
										for(int i=0;i<folNameSplit.length;i++){
											k=i+1;
											if(folNameSplit[i]!=null || folNameSplit[i]!=""){
											fol=fol.concat(File.separator).concat(folNameSplit[i]);
											File folder = new File(fol);
											filePath=fol;
											 if (!folder.exists()) 
											 {
									             folder.mkdirs();
									           
										     }else{
										     continue;
										     }
											}
										}*/
								 		 filePath=filePath+File.separator+eForm.getGuideDTO().getGuideLineID();
										File folder = new File(filePath);
										 if (!folder.exists()) 
										 {
											 System.out.println(folder.toString());
											
								              folder.mkdirs();
								            
									     }	
										
										 if(lang.equalsIgnoreCase("en")){
											 File folderH = new File(filePath.concat(File.separator+"ENGLISH"));
											 if (!folderH.exists()) 
											 {
												 System.out.println(folderH.toString());
												
												 folderH.mkdirs();
									            
										     }	 
											 filePath=filePath.concat(File.separator+"ENGLISH");
										 }else{
											 File folderH = new File(filePath.concat(File.separator+"HINDI"));
											 if (!folderH.exists()) 
											 {
												 System.out.println(folderH.toString());
												
												 folderH.mkdirs();
									            
										     }
											 filePath=filePath.concat(File.separator+"HINDI");
										 }
										 logger.debug("FOLDER STRUCTURE CREATED ");
										 
										 //Now Starting to create the PDF
										 //Praroop All Commented
									/* JasperReport jasperMasterReport = JasperCompileManager
							            .compileReport(masterReportFileName);*/
									
							      /*   JasperReport jasperPlotSubReport = JasperCompileManager
							            .compileReport(subReportPlotFileName);
							         JasperReport jasperBuildSubReport = JasperCompileManager
							            .compileReport(subReportBuildFileName);
							         JasperReport jasperAgriSubReport = JasperCompileManager
							            .compileReport(subReportAgriFileName);*/
							        // File file = new File(destFileName);
							    

							   /*     logger.debug("ALL REPORTS COMPILED");
							         Map parameters = new HashMap<String, Object>();
							         parameters.put("subreportParameter", subReportPlotFileName);
							         parameters.put("subreportParameter1", subReportBuildFileName);
							         parameters.put("subreportParameter2", subReportAgriFileName);
							         parameters.put("path", path);
							         //ServletOutputStream servletOutputStream = response.getOutputStream();  
							         System.gc();
							         
							         getJasperPdfviewerFromImg(destFileName,parameters,"all","allPDF",
							        		 jasperMasterReport,beanColDataSource);
							        System.gc();*/
/*
							         JasperFillManager.fillReportToFile(jasperMasterReport,
							            destFileName, parameters, beanColDataSource);
							         JasperPrint jasperPrint = (JasperPrint) JRLoader.loadObject(file);
							         jasperPrint.setOrientation(OrientationEnum.LANDSCAPE);*/
							        // logger.debug("ALL JASPER GENERATED COMPILED");
							    //     JasperViewer.viewReport(jasperPrint);
//for Plot
							       //JRXML Plot report
									      
							        /* JasperReport plotJasperMasterReport = JasperCompileManager
							            .compileReport(plotMasterReportFileName);*/
							         /*	 JasperReport plotJasperSubReport = JasperCompileManager
							            .compileReport(plotSubReportFileName);
							        */
										 
										 
										 
										 //Praroop 1 PDF generation Begins
										 logger.debug("Praroop 1 PDF generation Begins");
								         File plotFile = new File(plotMasterReportFileName);
								         File plotFilePrint = new File(plotDestFileName);
								         JasperReport plotJasperMasterReport = (JasperReport)JRLoader.loadObject(plotFile);
								         logger.debug("PLOT REPORTS COMPILED");
								         Map<String, Object> plotParameters = new HashMap<String, Object>();
								         plotParameters.put("subreportParameter", plotSubReportFileName);
								         plotParameters.put("path", path);
								         plotParameters.put("lang", lang);
								       // getJasperPdfviewerFromImg(plotDestFileName,plotParameters,CommonConstant.PDF_NAME_PRAROOP1,
								        	//	plotJasperMasterReport,plotBeanColDataSource,filePath);
								         JasperFillManager.fillReportToFile(plotJasperMasterReport,
								        		 plotDestFileName, plotParameters, plotBeanColDataSource);
								       // System.gc();
								        logger.debug("PLOT PDF GENERATED ");
								        ByteArrayOutputStream  pdfReportPlot = new ByteArrayOutputStream();
								        //JRXlsExporter exporterPlot = new JRXlsExporter();
								        JRPdfExporter exporterPlot = new JRPdfExporter();
								        JasperPrint plotJasperPrint = (JasperPrint) JRLoader.loadObject(plotFilePrint);
								        plotJasperPrint.setPageHeight(612);
										  plotJasperPrint.setPageWidth(1008);
										  exporterPlot.setParameter(JRExporterParameter.JASPER_PRINT, plotJasperPrint);
										  exporterPlot.setParameter(JRPdfExporterParameter.CHARACTER_ENCODING, "UTF-8");
										  exporterPlot.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
										  exporterPlot.setParameter(JRExporterParameter.OUTPUT_STREAM,pdfReportPlot);											
										  exporterPlot.exportReport();
										//  JasperViewer.viewReport(plotJasperPrint);
										 
											byte bytePlot[]=pdfReportPlot.toByteArray();
											
											
											logger.debug("byte[] array ");
										
																								
												//File newFilePlot = new File(filePath.concat("\\PraroopPlot.pdf"));
												File newFilePlot = new File(filePath.concat(File.separator+"GuidelineFull.pdf"));
												FileOutputStream fosp = new FileOutputStream(newFilePlot);
												fosp.write(bytePlot);
												fosp.close();
												logger.debug("GuidelineFull.pdf saved ");
								
							     /*    JasperFillManager.fillReportToFile(plotJasperMasterReport,
							        		 plotDestFileName, plotParameters, plotBeanColDataSource);
							   
							         JasperPrint plotJasperPrint = (JasperPrint) JRLoader.loadObject(plotFile);
							         plotJasperPrint.setOrientation(OrientationEnum.LANDSCAPE);*/
							        
							//         JasperViewer.viewReport(plotJasperPrint);
							        
							         
							         
							  	//JRXML Building report
									   
							     
									 /*JasperReport buildingJasperMasterReport = JasperCompileManager
							            .compileReport(buidlingMasterReportFileName);*/
							       /*  JasperReport buildingJasperSubReport = JasperCompileManager
							            .compileReport(buidlingSubReportFileName);
							    */
								        
								        
								      //Praroop 2 PDF generation Begins
								       /* logger.debug("Praroop 2 PDF generation Begins");
										     File buildFile = new File(buidlingMasterReportFileName);
										     JasperReport buildingJasperMasterReport = (JasperReport)JRLoader.loadObject(buildFile);
									        
									         
									         Map<String, Object> buildParameters = new HashMap<String, Object>();
									         buildParameters.put("subreportParameter", buidlingSubReportFileName);
									         buildParameters.put("path", path);
		
									         getJasperPdfviewerFromImg(buidlingDestFileName,buildParameters,CommonConstant.PDF_NAME_PRAROOP2,
									        		 buildingJasperMasterReport,buildBeanColDataSource,filePath);
									         System.gc();
									         logger.debug("BUILDING PDF GENERATED ");
									         */
									         
									         
							      /*   JasperFillManager.fillReportToFile(buildingJasperMasterReport,
							        		 buidlingDestFileName, buildParameters, buildBeanColDataSource);
							         JasperPrint builJasperPrint = (JasperPrint) JRLoader.loadObject(buildFile);
							         builJasperPrint.setOrientation(OrientationEnum.LANDSCAPE);*/
							         
							    //     JasperViewer.viewReport(builJasperPrint);
										
								//JRXML Agri report
								
								
								
								/*JasperReport agriJasperMasterReport = JasperCompileManager
					            .compileReport(agriMasterReportFileName);*/
					       /*  JasperReport agriJasperSubReport = JasperCompileManager
					            .compileReport(agriSubReportFileName);
					         */
									         
									         
									         //Praroop 3 PDF generation Begins 
									       /*  logger.debug("Praroop 3 PDF generation Begins");
									         File agriFile = new File(agriMasterReportFileName);
									         JasperReport agriJasperMasterReport = (JasperReport)JRLoader.loadObject(agriFile);
									         Map<String, Object> agriParameters = new HashMap<String, Object>();
									        
									         agriParameters.put("subreportParameter", agriSubReportFileName);
									         agriParameters.put("path", path);
									         
									         getJasperPdfviewerFromImg(agriDestFileName,agriParameters,CommonConstant.PDF_NAME_PRAROOP3,
									        		 					agriJasperMasterReport,agriBeanColDataSource,filePath);
									         System.gc();
									         logger.debug("AGRI PDF GENERATED ");*/
									         
									         //Praroop All PDF generation Begins 
									         
									         //logger.debug("ALL PDF generation Begins");
									        // generateAllPDF(filePath);
									         
									        // logger.debug("ALL PDF GENERATED");
									         logger.debug(">>>>>>>>>>>>>>>>>>>>>>GUIDELINE PDF SAVED SUCCESSFULLY<<<<<<<<<<<<<<<<<< ");
									         logger.debug(">>>>>>>>>>>>>>>>>>>>>>GUIDELINE PUBLISHED SUCCESSFULLY<<<<<<<<<<<<<<<<<< ");
									         
					         //ServletOutputStream servletOutputStream = response.getOutputStream();  

					      /*   JasperFillManager.fillReportToFile(agriJasperMasterReport,
					        		 agriDestFileName, agriParameters, agriBeanColDataSource);
					         JasperPrint agriJasperPrint = (JasperPrint) JRLoader.loadObject(agriFile);
					         agriJasperPrint.setOrientation(OrientationEnum.LANDSCAPE);*/
					         
					    //     JasperViewer.viewReport(agriJasperPrint);
							         
					         
					       
							     /*    ByteArrayOutputStream  pdfReport = new ByteArrayOutputStream();
							         ByteArrayOutputStream  pdfReportPlot = new ByteArrayOutputStream();
							         ByteArrayOutputStream  pdfReportBuild = new ByteArrayOutputStream();
							         ByteArrayOutputStream  pdfReportAgri = new ByteArrayOutputStream();
							         
							         logger.debug("BYTE ARRAY INITIALIZED ");*/
							      
										/*JRPdfExporter exporter = new JRPdfExporter();
							         //JRXlsExporter exporter=new JRXlsExporter();
										System.out.println("OPENOFFICE_PATH = "+System.getenv("OPENOFFICE_PATH"));
										exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
										exporter.setParameter(JRPdfExporterParameter.CHARACTER_ENCODING, "UTF-8");
										exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
										exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,pdfReport);
										//pallavi-start
//										exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
//										exporter.setParameter(JRXlsAbstractExporterParameter.IS_ONE_PAGE_PER_SHEET,b);
//										exporter.setParameter(JRXlsAbstractExporterParameter.IS_DETECT_CELL_TYPE,b);
//										exporter.setParameter(JRXlsAbstractExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,b);
//										exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, pdfReport);
										//pallavi-end
										exporter.exportReport();
										
										  logger.debug("JRPdfExported All ");
										  JRPdfExporter exporterPlot = new JRPdfExporter();
										
										  exporterPlot.setParameter(JRExporterParameter.JASPER_PRINT, plotJasperPrint);
										  exporterPlot.setParameter(JRPdfExporterParameter.CHARACTER_ENCODING, "UTF-8");
										  exporterPlot.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
										  exporterPlot.setParameter(JRExporterParameter.OUTPUT_STREAM,pdfReportPlot);											
										  exporterPlot.exportReport();
										//pallavi-start
											  JRXlsExporter exporterPlot=new JRXlsExporter();
											 *   exporterPlot.setParameter(JRXlsExporterParameter.JASPER_PRINT, plotJasperPrint);
											  exporterPlot.setParameter(JRXlsAbstractExporterParameter.IS_ONE_PAGE_PER_SHEET,b);
											  exporterPlot.setParameter(JRXlsAbstractExporterParameter.IS_DETECT_CELL_TYPE,b);
											  exporterPlot.setParameter(JRXlsAbstractExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,b);
											  exporterPlot.setParameter(JRExporterParameter.OUTPUT_STREAM, pdfReportPlot);
											   exporterPlot.exportReport();
												//pallavi-end
										 
											  logger.debug("JRPdfExported Plot ");*/
											
											/*JRPdfExporter exporterBuild = new JRPdfExporter();
											exporterBuild.setParameter(JRExporterParameter.JASPER_PRINT, builJasperPrint);
											exporterBuild.setParameter(JRPdfExporterParameter.CHARACTER_ENCODING, "UTF-8");
											exporterBuild.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
											exporterBuild.setParameter(JRExporterParameter.OUTPUT_STREAM,pdfReportBuild);											//exporterBuild.exportReport();
											exporterBuild.exportReport();
											logger.debug("JRPdfExported Building ");
											
											JRPdfExporter exporterAgri = new JRPdfExporter();
											exporterAgri.setParameter(JRExporterParameter.JASPER_PRINT, agriJasperPrint);
											exporterAgri.setParameter(JRPdfExporterParameter.CHARACTER_ENCODING, "UTF-8");
											exporterAgri.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
											exporterAgri.setParameter(JRExporterParameter.OUTPUT_STREAM,pdfReportAgri);											exporterAgri.exportReport();
											exporterAgri.exportReport();
											logger.debug("JRPdfExported Agri ");*/
							/*
										byte byteAll[]=pdfReport.toByteArray();
										byte bytePlot[]=pdfReportPlot.toByteArray();
										byte byteBuild[]=pdfReportBuild.toByteArray();
										byte byteAgri[]=pdfReportAgri.toByteArray();
										
										logger.debug("byte[] array ");
										try {
											File newFile = new File(filePath.concat("\\PraroopAll.pdf"));
											//File newFile = new File(filePath.concat("\\PraroopAll.xls"));
											FileOutputStream fos = new FileOutputStream(newFile);
											fos.write(byteAll);
											fos.close();
											logger.debug("PraroopAll.pdf saved ");
											
											//File newFilePlot = new File(filePath.concat("\\PraroopPlot.pdf"));
											File newFilePlot = new File(filePath.concat("\\PraroopPlot.pdf"));
											FileOutputStream fosp = new FileOutputStream(newFilePlot);
											fosp.write(bytePlot);
											fosp.close();
											logger.debug("PraroopPlot.pdf saved ");
											
											File newFileBuild = new File(filePath.concat("\\PraroopBuild.pdf"));
											FileOutputStream fosb = new FileOutputStream(newFileBuild);
											fosb.write(byteBuild);
											fosb.close();
											logger.debug("PraroopBuild.pdf saved ");
											
											File newFileAgri= new File(filePath.concat("\\PraroopAgri.pdf"));
											FileOutputStream fosa = new FileOutputStream(newFileAgri);
											fosa.write(byteAgri);
											fosa.close();
											logger.debug("PraroopAgri.pdf saved ");*/
											
										} catch (Exception ex) {
											logger.debug(ex);
										}finally{
											  // Get current size of heap in bytes
									         long heapSize = Runtime.getRuntime().totalMemory(); 

									         // Get maximum size of heap in bytes. The heap cannot grow beyond this size.// Any attempt will result in an OutOfMemoryException.
									         long heapMaxSize = Runtime.getRuntime().maxMemory();

									          // Get amount of free memory within the heap in bytes. This size will increase // after garbage collection and decrease as new objects are created.
									         long heapFreeSize = Runtime.getRuntime().freeMemory(); 
									         logger.debug("-------------->CHECKING THE HEAP MEMORY STATUS");
									         logger.debug("HEAP SIZE::::::::::"+heapSize);
									         logger.debug("HEAP MAX SIZE::::::::::"+heapMaxSize);
									         logger.debug("HEAP FREE SIZE::::::::::"+heapFreeSize);
										}
									
							            
								 }
}