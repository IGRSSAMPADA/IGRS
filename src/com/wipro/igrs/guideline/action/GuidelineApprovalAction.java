
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
 * FILE NAME: GuidelineApprovalAction.java
 * @author NIHAR R MISHRA
 * @version 1.0 
 * INITIAL CODING: 4th MARCH 2008
 * MODIFIED ON:    6th MAY 2008 
 * MODIFIED ON:	   None
 * @DESCRIPTION:   THIS CLASS REPRESENTS THE ACTION FOR GUIDELINE APPROVAL ACTION.
 */

package com.wipro.igrs.guideline.action;

import in.cdac.ilcg.jasperreports.pdfexporter.JRPdfExporter;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRGraphics2DExporter;
import net.sf.jasperreports.engine.export.JRGraphics2DExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.log4j.Logger;
import org.apache.pdfbox.util.PDFMergerUtility;
import org.apache.poi.util.IOUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;
import com.wipro.igrs.DMSConnection.DMSUtility;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.guideline.bd.GuidelineApprovalBD;
import com.wipro.igrs.guideline.bd.GuidelinePreparationBD;
import com.wipro.igrs.guideline.bd.GuidelineViewBD;
import com.wipro.igrs.guideline.constant.CommonConstant;
import com.wipro.igrs.guideline.dao.GuidelineApprovalDAO;
import com.wipro.igrs.guideline.dto.GuidelineDTO;
import com.wipro.igrs.guideline.form.GuidelinePreparationForm;
import com.wipro.igrs.jasperBean.JasperBean;
import com.wipro.igrs.util.PropertiesFileReader;

/**
 * @author NIHAR M.
 *
 */
public class GuidelineApprovalAction extends BaseAction{

	String forwardJsp = new String(CommonConstant.CHECKER1);
	private Logger logger = Logger.getLogger(GuidelineApprovalAction.class);
	private PropertiesFileReader pr;

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward execute(
			ActionMapping mapping, 
			ActionForm form, 
			HttpServletRequest request,
			HttpServletResponse response,HttpSession session) 
	throws Exception {
		String page=request.getParameter("page");


		ActionMessages messages = new ActionMessages();		
		
		String districtName;
		long guideID = 0;
		String guidelineID = null ;
		String durationFrom = null;
		String durationTo = null;
		String statusChecker = null; 
		String derivedFrom = null;
		int approvalType = 0;
		String actionName = null;
		String propDetails = null;
		ArrayList versionList = new ArrayList();
	//	HttpSession session = request.getSession(true);
	      String roleId = (String)session.getAttribute("role");
		  String funId = (String)session.getAttribute("functionId");
		  String userId = (String)session.getAttribute("UserId");
		 // pr = PropertiesFileReader.getInstance("com.wipro.igrs.igrs");
		  String actId = (String)request.getParameter("actId");
		  String officeId = (String)session.getAttribute("loggedToOffice");
		  String language = (String)session.getAttribute("languageLocale");
		  ArrayList officeList = new ArrayList();
		  HashMap officeDetails = new HashMap();
		  String roleID = "";
		  //if(session.getAttribute("officeIdList")!= null)
		  //{
			  //logger.debug("<------notnull");
			 // officeList = (ArrayList)session.getAttribute("officeIdList");
		  //}
		  
		  if(session.getAttribute("officeactivitydata")!= null)
		  {
			 officeDetails = (HashMap)session.getAttribute("officeactivitydata");
			  
		  }
		  
		  
		 
		 /* ArrayList officeList = new ArrayList();
		  if(session.getAttribute("officeIdList")!= null)
		  {
			  logger.debug("<------notnull");
			  officeList = (ArrayList)session.getAttribute("officeIdList");
		  }*/
		
		
		if (form != null) {
			GuidelinePreparationForm eForm = (GuidelinePreparationForm) form;
			GuidelineApprovalBD bd = new GuidelineApprovalBD();
			
			
			ArrayList financialYearList =bd.getFinancialYearList();
			eForm.setFinancialYearList(financialYearList);
			
			
			String formName = eForm
			.getGuideDTO().getGuidelineApprovalForm();

			GuidelineDTO formDTO=eForm.getGuideDTO();
			formDTO.setLanguage(language);
			if(page!=null){
				if("approve".equals(page)){
					//ArrayList districtList = bd.getDistrictList(officeDetails,actId);
					ArrayList districtList = bd.getDistrict(officeId,language);
					eForm.setDistrictList(districtList);
						eForm.setTehsilList(new ArrayList());
						eForm.setWardList(new ArrayList());
						eForm.setMohallaList(new ArrayList());
						eForm.setPatwariList(new ArrayList());
						eForm.setVillageList(new ArrayList());
						eForm.setVersionList(new ArrayList());
						eForm.setCombination(new ArrayList());
						//eForm.setFinancialYearList(new ArrayList());
						 	
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
						//formDTO.setActionName("");
						//request.removeAttribute("actionID");
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
						forwardJsp = CommonConstant.CHECKER1;
						logger.debug("Page is forwarded to :-    "+forwardJsp);
					}
				}
					//GuidelinePreparationForm eForm = (GuidelinePreparationForm) form;
					//GuidelineApprovalBD bd = new GuidelineApprovalBD();
					//ArrayList districtList = bd.getDistrictList();
					//eForm.setDistrictList(districtList);
					
					//ArrayList financialYearList =bd.getFinancialYearList();
					//eForm.setFinancialYearList(financialYearList);

					//ArrayList areaTypes = bd.getAreasTypeList();
					//eForm.setAreaTypeList(areaTypes);
					
					//forwardJsp = CommonConstant.CHECKER1;
					//System.out.println("Page is forwarded to :-    "+forwardJsp);
				//}
			//}

			
			
				
				if(request.getParameter("actionName") != null)
				{
					actionName = request.getParameter("actionName");
					formDTO.setActionName("");
				}
				else
				{
					actionName = formDTO.getActionName();
				}
				
				
				
				/*if("district".equalsIgnoreCase(actionName))
				{
					System.out.println("Inside DISTRICT");
					districtName = formDTO.getDistrict();
					System.out.println("<----"+districtName);
					
				}*/
				
				logger.debug(formDTO.getActionName());
				
				if("showVersion".equalsIgnoreCase(actionName))
				{
					logger.debug("INSIDE SHOW ACTION");
					String finan = formDTO.getFinancialYear();
					String district = formDTO.getDistrictID();
					versionList = bd.showStatusChecker(finan, district,language);
					
					HashMap guidelineStatusList = new HashMap();
					
					if(formDTO.getLanguage().equalsIgnoreCase("en"))
					{
						guidelineStatusList.put(CommonConstant.GUIDELINESTATUS_DRAFT, CommonConstant.DSP_GUIDELINESTATUS_DRAFT);
						guidelineStatusList.put(CommonConstant.GUIDELINESTATUS_FINAL, CommonConstant.DSP_GUIDELINESTATUS_FINAL);
					}
					else
					{
						guidelineStatusList.put(CommonConstant.GUIDELINESTATUS_DRAFT, CommonConstant.H_DSP_GUIDELINESTATUS_DRAFT);
						guidelineStatusList.put(CommonConstant.GUIDELINESTATUS_FINAL, CommonConstant.H_DSP_GUIDELINESTATUS_FINAL);
					}
					
					eForm.setGuidelineStatusList(guidelineStatusList);
			
					
					if(versionList.size() > 0)
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
					forwardJsp = CommonConstant.CHECKER1;
					
				}
				
				if("DownloadPDF".equalsIgnoreCase(formDTO.getActionName()))
				{
					boolean boo = false;
					String[] guideDuration = formDTO.getSelectedGuideLineID().toString().split("~");
					if(guideDuration.length == 5)
					{
					guidelineID = guideDuration[0];
					durationFrom = guideDuration[1];
					durationTo = guideDuration[2];
					statusChecker = guideDuration[3]; 
					derivedFrom   = guideDuration[4];
					}
					formDTO.setGuideLineID(guidelineID);
					
					String guideId=formDTO.getGuideLineID();
					String path = "";
					GuidelinePreparationBD bd1 = new GuidelinePreparationBD();
					PropertiesFileReader proper = PropertiesFileReader.getInstance("resources.igrs");
					
				      String downloadPath=proper.getValue("GuidelineSavePath");
				 	downloadPath=downloadPath.replace("-", File.separator);

				 	formDTO.setLoggedOfficeId(officeId);
				 	String usertype=bd1.getUserType(formDTO);
					
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
						boolean printdatainsert = bd1.getinsertDataForPrint(guideId,formDTO);
						if(printdatainsert)
						{
							logger.debug("print data inserted successfully");
						}
						else
						{
							logger.debug("print data insertion failed");
						}
						/*for(int i=0;i<=1;i++){
							if(i==0)language="hi";
							else language="en";
					 boo = 	generatePDF(formDTO,eForm,language,response);//for generating the pdf
					
					
						}*/
						GuidelineViewBD gBD = new GuidelineViewBD();
						boo=gBD.generateGuidePDF(formDTO, eForm, guideId, response, language);
						
						if(boo)
						{
						request.setAttribute(CommonConstant.SUCCESS_MSG_1, "Guideline has been published successfuly");
						forwardJsp= CommonConstant.PUBLISH_MAIN;
						logger.debug(forwardJsp);
					if("DRO".equalsIgnoreCase(usertype))
					{
						bd1.getUpdatePrintStatus(formDTO);
						formDTO.setDownlaodChkId("C1");
						forwardJsp = CommonConstant.PUBLISH_MAIN;
					}
					else
					{
						bd1.getUpdatePrintStatus(formDTO);
						formDTO.setDownlaodChkId("C");
						forwardJsp = CommonConstant.PUBLISH_MAIN;
					}
				 		
				 	}
				 	
				 	else
				 	{
				 		forwardJsp = "failure";
				 	}
				}
				
				}
				if("versionNext".equalsIgnoreCase(actionName))
				{
					String[] guideDuration = formDTO.getSelectedGuideLineID().toString().split("~");
					if(guideDuration.length == 5)
					{
						guidelineID = guideDuration[0];
						durationFrom = guideDuration[1];
						durationTo = guideDuration[2];
						//approvalType = guideDuration[3]; 
					}
					//logger.debug("Duration From"+durationFrom);
					//logger.debug("Duration From"+durationTo);
					formDTO.setGuideLineID(guidelineID);
					formDTO.setFromDepositeDate(durationFrom);
					formDTO.setToDepositeDate(durationTo);
					//logger.debug("@@@@@@@@@@@IN VERSION NEXT"+guidelineID);
					guideID = Long.parseLong(guidelineID);
					String finan = formDTO.getFinancialYear();
					String district = formDTO.getDistrictID();
					versionList = bd.showStatusChecker(finan, district,language);
					
					HashMap guidelineStatusList = new HashMap();
					
					if(formDTO.getLanguage().equalsIgnoreCase("en"))
					{
						guidelineStatusList.put(CommonConstant.GUIDELINESTATUS_DRAFT, CommonConstant.DSP_GUIDELINESTATUS_DRAFT);
						guidelineStatusList.put(CommonConstant.GUIDELINESTATUS_FINAL, CommonConstant.DSP_GUIDELINESTATUS_FINAL);
					}
					else
					{
						guidelineStatusList.put(CommonConstant.GUIDELINESTATUS_DRAFT, CommonConstant.H_DSP_GUIDELINESTATUS_DRAFT);
						guidelineStatusList.put(CommonConstant.GUIDELINESTATUS_FINAL, CommonConstant.H_DSP_GUIDELINESTATUS_FINAL);
					}
					
					eForm.setGuidelineStatusList(guidelineStatusList);
					eForm.setVersionList(versionList);
					request.setAttribute("versionList", versionList);
					request.setAttribute("distt", district);
					request.setAttribute("finan", finan);
					forwardJsp = CommonConstant.CHECKER1;
				}
				
				if("finalAction".equalsIgnoreCase(actionName))
					{
							
						String[] guideDuration = formDTO.getSelectedGuideLineID().toString().split("~");
						if(guideDuration.length == 5)
						{
							guidelineID = guideDuration[0];
							durationFrom = guideDuration[1];
							durationTo = guideDuration[2];
							statusChecker = guideDuration[3]; 
							derivedFrom   = guideDuration[4];
						}
						//logger.debug("Duration From"+durationFrom);
						//logger.debug("Duration From"+durationTo);
						formDTO.setGuideLineID(guidelineID);
						formDTO.setFromDepositeDate(durationFrom);
						formDTO.setToDepositeDate(durationTo);
						formDTO.setDerived(derivedFrom);
						//logger.debug("@@@@@@@@@@@IN finalAction"+guidelineID);
						//logger.debug("@@@@@@@@@@@IN guideStatus"+formDTO.getGuidelineStatus());
						//logger.debug("@@@@@@@@@@@IN app type"+formDTO.getApprovalType());
					
 						if(statusChecker.equalsIgnoreCase("Pending"))
						{
							//approvalType = formDTO.getApprovalType();
 							if(formDTO.getApprovalType() == 0)
 							{
 								approvalType = Integer.parseInt(formDTO.getGuidelineStatus());
 							}
 							else
 							{
 								approvalType = formDTO.getApprovalType();
 							}
						}
						else if(statusChecker.equalsIgnoreCase("Draft"))
						{
							approvalType = 2;
						}
						else
						{
							approvalType = 3;
						}
					
 						//logger.debug("APPROVAL STATUS"+formDTO.getGuidelineStatus());
 						//logger.debug("Approval Type"+formDTO.getApprovalType());
 						//logger.debug("FINAL Approval Type"+approvalType);
						
						if(approvalType == 3)
						{
							if(formDTO.getLanguage().equalsIgnoreCase("en"))
							{
								formDTO.setApprovalTypeDisp("Final");
							}
							else
							{
								formDTO.setApprovalTypeDisp("à¤«à¤¾à¤‡à¤¨à¤²");
							}
							
						}
						else
						{
							if(formDTO.getLanguage().equalsIgnoreCase("en"))
							{
								formDTO.setApprovalTypeDisp("Draft");
							}
							else
							{
								formDTO.setApprovalTypeDisp("à¤ªà¥�à¤°à¤¾à¤°à¥‚à¤ªà¥�");
							}
							
						}
							ArrayList tehsilListPending = new ArrayList();
							ArrayList tehsilListComplete = new ArrayList();
							String distId = formDTO.getDistrictID();
							//logger.debug("%%%%GUIDELINE"+formDTO.getGuideLineID());
							guideID = Long.parseLong(formDTO.getGuideLineID());
							//logger.debug("@#$%^&**&^%+"+guideID);
							//logger.debug(formDTO.getFinancialYear());
							formDTO.setApprovalType(approvalType);
					
							tehsilListPending = bd.getPendingTehsilListChecker(distId, guideID,language);
							eForm.setTehsilList(tehsilListPending);
							request.setAttribute("tehsilList", tehsilListPending);
							
							tehsilListComplete = bd.getCompleteTehsilListChecker(distId, guideID,language);
							eForm.setCompleteTehsilList(tehsilListComplete);
							request.setAttribute("completeTehsilList", tehsilListComplete);
							
							formDTO.setGuideLineID(formDTO.getGuideLineID());
							formDTO.setDistrictID(distId);
							formDTO.setDistrict(formDTO.getDistrict());
							//logger.debug("-----"+formDTO.getFinancialYear());
							formDTO.setFinancialYear(formDTO.getFinancialYear());
							
					
							forwardJsp = CommonConstant.CHECKER2;
						}
				
				
				if("publish".equalsIgnoreCase(actionName))
				{
					
					
					String filename=null;
					GuidelineApprovalDAO apDAO = new GuidelineApprovalDAO();
					//String s[]=request.getParameterValues("guideDraftFinal");
					String[] guideDuration = formDTO.getSelectedGuideLineID().toString().split("~");
					
					if(guideDuration.length == 5)
					{
					guidelineID = guideDuration[0];
					durationFrom = guideDuration[1];
					durationTo = guideDuration[2];
					statusChecker = guideDuration[3]; 
					derivedFrom   = guideDuration[4];
					}
					formDTO.setGuideLineID(guidelineID);
					
					String guideId = formDTO.getGuideLineID();
					GuidelinePreparationBD bd1 = new GuidelinePreparationBD();
					
					PropertiesFileReader proper = PropertiesFileReader.getInstance("resources.igrs");
					
				      String downloadPath=proper.getValue("GuidelineSavePath");
				 	downloadPath=downloadPath.replace("-", File.separator);

				      if("en".equalsIgnoreCase(language))
					 	{
				    	  filename=downloadPath+File.separator+guideId+File.separator+"ENGLISH"+File.separator+"GuidelineFull.pdf";
					 	}
					 	else
					 	{
					 		filename=downloadPath+File.separator+guideId+File.separator+"HINDI"+File.separator+"GuidelineFull.pdf";
					 	}
					 	File Fr1 = new File(filename);
				
				
						if(Fr1.exists())
					 	{
							apDAO.updatestatus(formDTO);
							formDTO.setDownlaodChkId("O"); 
							request.setAttribute(CommonConstant.SUCCESS_MSG_1, "Guideline has been published successfuly");
							forwardJsp= CommonConstant.PUBLISH_MAIN;
							logger.debug(forwardJsp);
					 	}
						else
						{
					guideID = Long.parseLong(guidelineID);
					//logger.debug("!@#$%^&+"+statusChecker);
					if(derivedFrom.equalsIgnoreCase("final") || derivedFrom.equalsIgnoreCase("draft"))
					{
						
						ArrayList maker_entered = apDAO.checkPublishMakerStatus(guideID);
						if(maker_entered.size() == 0)
						{
							ArrayList checker_validated = apDAO.checkPublishValidateStatus(guideID);
							if(checker_validated.size() == 0)
							{
								if(formDTO.getGotTypeChk().equalsIgnoreCase("final"))
								{
									approvalType = 3;
								}
								else if(formDTO.getGotTypeChk().equalsIgnoreCase("draft"))
								{
									approvalType = 2;
								}
								else
								{
									if(formDTO.getApprovalType() == 0)
		 							{
		 								approvalType = Integer.parseInt(formDTO.getGuidelineStatus());
		 							}
		 							else
		 							{
		 								approvalType = formDTO.getApprovalType();
		 							}
								}
								//logger.debug(approvalType);
								
								//boolean subClauseDrafttoApp = bd.subClauseDeriveToApprove(guidelineID);
								//if(subClauseDrafttoApp)
								//{
									boolean updateChild1Status = apDAO.updateChild1Status(guideID,approvalType);
									if(updateChild1Status)
									{
									logger.debug("Guideline status changed");
									//request.setAttribute(CommonConstant.SUCCESS_MSG, "Guideline has been published successfuly");
									//forwardJsp= CommonConstant.PUBLISH_MAIN;
									//System.out.println(forwardJsp);
									boolean updatePublishStatus = apDAO.updatePublishStatus(guideID,approvalType);
									if(updatePublishStatus)
									{
										logger.debug("Guideline Published");
										messages.add("SUCCESS_MSG_1", new ActionMessage(
										"conf_msg_guideline_publish"));
										formDTO.setConfirmationChk("6");
										saveMessages(request, messages);
										boolean printdatainsert = bd1.getinsertDataForPrint(guideId,formDTO);
										if(printdatainsert)
										{
											logger.debug("print data inserted successfully");
										}
										else
										{
											logger.debug("print data insertion failed");
										}
										/*for(int i=0;i<=1;i++){
											if(i==0)language="hi";
											else language="en";
										generatePDF(formDTO,eForm,language,response);//for generating the pdf
										}*/
										GuidelineViewBD gBD = new GuidelineViewBD();
										gBD.generateGuidePDF(formDTO, eForm, guideId, response, language);
										
										formDTO.setDownlaodChkId("O");
										request.setAttribute(CommonConstant.SUCCESS_MSG_1, "Guideline has been published successfuly");
										forwardJsp= CommonConstant.PUBLISH_MAIN;
										logger.debug(forwardJsp);
									}
									else
									{
										messages.add("SUCCESS_MSG_1", new ActionMessage(
										"conf_msg_guideline_publish_fail"));
										saveMessages(request, messages);
										logger.debug("Guideline NOT Published");
										formDTO.setConfirmationChk("7");
										request.setAttribute(CommonConstant.SUCCESS_MSG_1, "Publishing guideline failed");
										forwardJsp= CommonConstant.PUBLISH_MAIN;
										logger.debug(forwardJsp);
									}
								}
								else
								{
									messages.add("SUCCESS_MSG_1", new ActionMessage(
									"conf_msg_guideline_publish_fail"));
									saveMessages(request, messages);
									formDTO.setConfirmationChk("7");
									logger.debug("Guideline status not changed in Child1");
									request.setAttribute(CommonConstant.SUCCESS_MSG_1, "Publishing guideline failed");
									forwardJsp= CommonConstant.PUBLISH_MAIN;
									logger.debug(forwardJsp);
								}
							//}
								/*else
								{
									messages.add("SUCCESS_MSG_1", new ActionMessage(
									"conf_msg_guideline_publish_fail"));
									saveMessages(request, messages);
									formDTO.setConfirmationChk("7");
									logger.debug("APPROVAL ACTION- DRIVE TO APP");
									request.setAttribute(CommonConstant.SUCCESS_MSG_1, "Publishing guideline failed");
									forwardJsp= CommonConstant.PUBLISH_MAIN;
									logger.debug(forwardJsp);
								}*/
								
							}
							else
							{
								logger.debug("Guideline Data Not Validated");
								messages.add("SUCCESS_MSG_1", new ActionMessage(
								"conf_msg_not_validated"));
								saveMessages(request, messages);
								formDTO.setConfirmationChk("8");
								request.setAttribute(CommonConstant.SUCCESS_MSG_1, "Selected guideline cannot be published as data has not been validated for following combinations");
								eForm.setCombination(checker_validated);
								request.setAttribute("combination", checker_validated);
								forwardJsp= CommonConstant.PUBLISH_MAIN;
								logger.debug(forwardJsp);
							
							}
							
						}
						else
						{
							logger.debug("Guideline Data Not Entered....");
							logger.debug("Guideline Data Not Validated");
							messages.add("SUCCESS_MSG_1", new ActionMessage(
							"conf_msg_data_not_entered"));
							formDTO.setConfirmationChk("9");
							saveMessages(request, messages);
							request.setAttribute(CommonConstant.SUCCESS_MSG_1," Selected guideline cannot be published as Maker has not entered data for following combinations ");
							eForm.setCombination(maker_entered);
							request.setAttribute("combination", maker_entered);
							forwardJsp= CommonConstant.PUBLISH_MAIN;
							logger.debug(forwardJsp);
						}
					}
					else
					{
						if(statusChecker.equalsIgnoreCase("final") || statusChecker.equalsIgnoreCase("draft"))
						{
							ArrayList maker_entered = apDAO.checkPublishMakerStatus(guideID);
							if(maker_entered.size() == 0)
							{
								ArrayList checker_validated = apDAO.checkPublishValidateStatus(guideID);
								if(checker_validated.size() == 0)
								{
									if(formDTO.getGotTypeChk().equalsIgnoreCase("final"))
									{
										approvalType = 3;
									}
									else
									{
										approvalType = 2;
									}
									
									boolean updatePublishStatus = apDAO.updatePublishStatus(guideID,approvalType);
									if(updatePublishStatus)
									{
										logger.debug("Guideline Published");
										
										messages.add("SUCCESS_MSG_1", new ActionMessage(
										"conf_msg_guideline_publish"));
										saveMessages(request, messages);
										boolean printdatainsert = bd1.getinsertDataForPrint(guideId,formDTO);
										if(printdatainsert)
										{
											logger.debug("print data inserted successfully");
										}
										else
										{
											logger.debug("print data insertion failed");
										}
										GuidelineViewBD gBD = new GuidelineViewBD();
										gBD.generateGuidePDF(formDTO, eForm, guideId, response, language);
										//generatePDF(formDTO,eForm,language,response);//for generating the pdf
										
										formDTO.setConfirmationChk("6");
										request.setAttribute(CommonConstant.SUCCESS_MSG_1, "Guideline has been published successfuly");
										forwardJsp=  CommonConstant.PUBLISH_MAIN;
										logger.debug(forwardJsp);
									}
									else
									{
										logger.debug("Guideline NOT Published");
										messages.add("SUCCESS_MSG_1", new ActionMessage(
										"conf_msg_guideline_publish_fail"));
										formDTO.setConfirmationChk("7");
										saveMessages(request, messages);
										request.setAttribute(CommonConstant.SUCCESS_MSG_1, "Publishing guideline failed");
										forwardJsp=  CommonConstant.PUBLISH_MAIN;
										logger.debug(forwardJsp);
									}
							}
							else
							{
								logger.debug("Guideline Data Not Validated");
								messages.add("SUCCESS_MSG_1", new ActionMessage(
								"conf_msg_not_validated"));
								saveMessages(request, messages);
								formDTO.setConfirmationChk("8");
								request.setAttribute(CommonConstant.SUCCESS_MSG_1, "Selected guideline cannot be published as data has not been validated for following combinations");
								eForm.setCombination(checker_validated);
								request.setAttribute("combination", checker_validated);
								forwardJsp= CommonConstant.PUBLISH_MAIN;
								logger.debug(forwardJsp);
							
							}
							
						}
						else
						{
							logger.debug("Guideline Data Not Entered....");
							messages.add("SUCCESS_MSG_1", new ActionMessage(
							"conf_msg_data_not_entered"));
							formDTO.setConfirmationChk("9");
							saveMessages(request, messages);
							request.setAttribute(CommonConstant.SUCCESS_MSG_1," Selected guideline cannot be published as Maker has not entered data for following combinations ");
							eForm.setCombination(maker_entered);
							request.setAttribute("combination", maker_entered);
							forwardJsp= CommonConstant.PUBLISH_MAIN;
							logger.debug(forwardJsp);
						}
						
					}
				
					else
					{
						//request.setAttribute("eForm", eForm);
						logger.debug("Guideline Not approved as Final....");
						messages.add("SUCCESS_MSG_1", new ActionMessage(
						"conf_msg_data_not_approved"));
						formDTO.setConfirmationChk("10");
						saveMessages(request, messages);
						request.setAttribute(CommonConstant.SUCCESS_MSG_1," Selected guideline cannot be published as it has not been approved as FINAL or DRAFT ");
						forwardJsp= CommonConstant.PUBLISH_MAIN;
						logger.debug(forwardJsp);
					}
				}
					
				}
				
				/*if("draftAction".equalsIgnoreCase(actionName))
				{
					ArrayList tehsilListPending = new ArrayList();
					ArrayList tehsilListComplete = new ArrayList();
					String distId = formDTO.getDistrictID();
					System.out.println("%%%%GUIDELINE"+formDTO.getGuideLineID());
					guideID = Long.parseLong(formDTO.getGuideLineID());
					System.out.println("@#$%^&**&^%+"+guideID);
					System.out.println(formDTO.getFinancialYear());
					
					formDTO.setApprovalType("DRAFT");
					tehsilListPending = bd.getPendingTehsilListChecker(distId, guideID);
					eForm.setTehsilList(tehsilListPending);
					request.setAttribute("tehsilList", tehsilListPending);
					
					tehsilListComplete = bd.getCompleteTehsilListChecker(distId, guideID);
					eForm.setCompleteTehsilList(tehsilListComplete);
					request.setAttribute("completeTehsilList", tehsilListComplete);
					
					
					formDTO.setGuideLineID(formDTO.getGuideLineID());
					
			
			forwardJsp = CommonConstant.CHECKER2;
				}*/
				if("tehsilPending".equalsIgnoreCase(actionName))
				{
						ArrayList wardListPending = new ArrayList();
						ArrayList wardListComplete =  new ArrayList();
						ArrayList patwariListPending = new ArrayList();
						ArrayList patwariListComplete = new ArrayList();
						//logger.debug("&&&&&&&&&&&&&"+formDTO.getTehID());
						String teh[]=formDTO.getTehsil().toString().split("~");
						int tehId = 0;
						if(teh.length==2)
						{
							tehId = Integer.parseInt(teh[0]);
						}
						//logger.debug("District in tehsil action"+formDTO.getDistrict());
						//logger.debug("&&&&&&&&&&&&&&&&&&GUIDELINE"+formDTO.getGuideLineID());
							
						guideID = Long.parseLong(formDTO.getGuideLineID());
							
						wardListPending = bd.getPendingWardListChecker(tehId, guideID,language);
						eForm.setWardList(wardListPending);
							
							
						wardListComplete = bd.getCompleteWardListChecker(tehId, guideID,language);
						eForm.setCompleteWardList(wardListComplete);
						
							
						patwariListPending=bd.getPendingPatwariListChecker(tehId, guideID,language);
						eForm.setPatwariList(patwariListPending);
							
							
						patwariListComplete=bd.getCompletePatwariListChecker(tehId, guideID,language);
						eForm.setCompletePatwariList(patwariListComplete);
							
							
						formDTO.setGuideLineID(formDTO.getGuideLineID());
						formDTO.setTehID(tehId);
						formDTO.setTehsil(teh[1]);
						request.setAttribute("wardList", wardListPending);
						request.setAttribute("completeWardList",wardListComplete);
						request.setAttribute("patwariList",  patwariListPending);
						request.setAttribute("completePatwariList",patwariListComplete);
						forwardJsp = CommonConstant.CHECKER3;
							
						}
			
				if("tehsilComplete".equalsIgnoreCase(actionName))
				{
					ArrayList wardListPending = new ArrayList();
					ArrayList wardListComplete =  new ArrayList();
					ArrayList patwariListPending = new ArrayList();
					ArrayList patwariListComplete = new ArrayList();
					//System.out.println("&&&&&&&&&&&&&"+formDTO.getTehID());
					//logger.debug("&&&&&&&&&&&&&&&&&&GUIDELINE"+formDTO.getGuideLineID());
					String teh[]=formDTO.getTehsil().toString().split("~");
					int tehId = 0;
					if(teh.length==2)
					{
						tehId = Integer.parseInt(teh[0]);
					}
					guideID = Long.parseLong(formDTO.getGuideLineID());
						
					wardListComplete = bd.getCompleteWardListChecker(tehId, guideID,language);
					eForm.setCompleteWardList(wardListComplete);
					//patwariListPending=bd.getPendingPatwariListChecker(tehId, guideID);
					//eForm.setPatwariList(patwariListPending);
						
					patwariListComplete=bd.getCompletePatwariListChecker(tehId, guideID,language);
					eForm.setCompletePatwariList(patwariListComplete);
						
					formDTO.setGuideLineID(formDTO.getGuideLineID());
					formDTO.setTehID(tehId);
					formDTO.setTehsil(teh[1]);
					request.setAttribute("wardList", wardListPending);
					request.setAttribute("completeWardList",wardListComplete);
					request.setAttribute("patwariList",  patwariListPending);
					request.setAttribute("completePatwariList",patwariListComplete);
					forwardJsp = CommonConstant.CHECKER3;
					
				}
				
				
				if("wardPending".equalsIgnoreCase(actionName))
				{
					//logger.debug("&&&&&&&&&&&&&IN WARD PENDING"+formDTO.getWardId());
					//logger.debug("&&&&&&&&&&&&&&&&&&GUIDELINE"+formDTO.getGuideLineID());
					
					guideID = Long.parseLong(formDTO.getGuideLineID());
					
					String warArray[] = formDTO.getWard().toString().split("~");
					int wardId = 0;
					if(warArray.length == 3)
					{
						wardId = Integer.parseInt(warArray[0]);
					}
					logger.debug("ward pending"+wardId);
					//System.out.println("IN WARD ACTION"+formDTO.getDistrict());
					ArrayList mohallaListPending = new ArrayList();
					ArrayList mohallaListComplete = new ArrayList();
					
					mohallaListPending = bd.getPendingMohallaListChecker(wardId, guideID,language);
					eForm.setMohallaList(mohallaListPending);
					
					mohallaListComplete = bd.getCompleteMohallaListChecker(wardId, guideID,language);
					eForm.setCompleteMohallaList(mohallaListComplete);
					
					request.setAttribute("mohallaList",mohallaListPending );
					request.setAttribute("completeMohallaList",mohallaListComplete );
					
					formDTO.setWard(warArray[2]);
					formDTO.setWardId(wardId);
					formDTO.setWardID(warArray[1]);
					forwardJsp = CommonConstant.CHECKER4;
					
					
				}
				
				
				if("wardComplete".equalsIgnoreCase(actionName))
				{
					//logger.debug("&&&&&&&&&&&&&IN WARD Complete"+formDTO.getWardId());
					//logger.debug("&&&&&&&&&&&&&&&&&&GUIDELINE"+formDTO.getGuideLineID());
					String warArray[] = formDTO.getWard().toString().split("~");
					int wardId = 0;
					if(warArray.length == 3)
					{
						wardId = Integer.parseInt(warArray[0]);
					}
					guideID = Long.parseLong(formDTO.getGuideLineID());
					
					ArrayList mohallaListComplete = new ArrayList();
					
					mohallaListComplete = bd.getCompleteMohallaListChecker(wardId, guideID,language);
					eForm.setCompleteMohallaList(mohallaListComplete);
					request.setAttribute("completeMohallaList",mohallaListComplete );
					formDTO.setWard(warArray[2]);
					formDTO.setWardId(wardId);
					formDTO.setWardID(warArray[1]);
					forwardJsp = CommonConstant.CHECKER4;
				}
				
				
				if("patwariPending".equalsIgnoreCase(actionName))
				{
					//logger.debug("&&&&&&&&&&&&&IN Patwari Pending"+formDTO.getPatwariId());
					//logger.debug("&&&&&&&&&&&&&&&&&&GUIDELINE"+formDTO.getGuideLineID());
					String patArray[] = formDTO.getPatwari().toString().split("~");
					int patwariId = 0;
					if(patArray.length == 3)
					{
						patwariId = Integer.parseInt(patArray[0]);
					}
					guideID = Long.parseLong(formDTO.getGuideLineID());
					
					ArrayList villageListPending = new ArrayList();
					ArrayList villageListComplete = new ArrayList();
					
					villageListPending = bd.getPendingMohallaListChecker(patwariId, guideID,language);
					eForm.setVillageList(villageListPending);
					
					villageListComplete = bd.getCompleteMohallaListChecker(patwariId, guideID,language);
					eForm.setCompleteVillageList(villageListComplete);
					
					request.setAttribute("villageList", villageListPending);
					request.setAttribute("completeVillageList", villageListComplete);
					formDTO.setPatwari(patArray[2]);
					formDTO.setPatwariId(patwariId);
					formDTO.setWardId(patwariId);
					formDTO.setWardID(patArray[1]);
					forwardJsp = CommonConstant.CHECKER4;
				}
				if("patwariComplete".equalsIgnoreCase(actionName))
				{
					//logger.debug("&&&&&&&&&&&&&IN Patwari Complete"+formDTO.getPatwariId());
					//logger.debug("&&&&&&&&&&&&&&&&&&GUIDELINE"+formDTO.getGuideLineID());
					String patArray[] = formDTO.getPatwari().toString().split("~");
					int patwariId = 0;
					if(patArray.length == 3)
					{
						patwariId = Integer.parseInt(patArray[0]);
					}
					guideID = Long.parseLong(formDTO.getGuideLineID());
					//logger.debug(patwariId);
					ArrayList villageListComplete = new ArrayList();
					
					villageListComplete = bd.getCompleteMohallaListChecker(patwariId, guideID,language);
					eForm.setCompleteVillageList(villageListComplete);
					//logger.debug(villageListComplete.size());
					request.setAttribute("completeVillageList", villageListComplete);
					formDTO.setPatwari(patArray[2]);
					formDTO.setPatwariId(patwariId);
					formDTO.setWardId(patwariId);
					formDTO.setWardID(patArray[1]);
					forwardJsp = CommonConstant.CHECKER4;
				}
				
				if("mohallaPending".equalsIgnoreCase(actionName))
				{
					/*System.out.println("IN MOHALLA PENDING");
					System.out.println("Financial"+formDTO.getFinancialYear());
					System.out.println("District"+formDTO.getDistrict());
					System.out.println("DistrictID"+formDTO.getDistrictID());
					System.out.println("Tehsil"+formDTO.getTehsil());
					System.out.println("TehsilID"+formDTO.getTehID());
					System.out.println("Ward"+formDTO.getWard());
					System.out.println("WardID"+formDTO.getWardId());*/
					//logger.debug("IN MOHALLA DURATION"+formDTO.getFromDepositeDate());
					String[] mohArray = formDTO.getMoh().toString().split("~");
					int mohId=0;
					String fYear = formDTO.getFinancialYear();
					String guideLineID = formDTO.getGuideLineID(); 
					guideID = Long.parseLong(guideLineID);
					if(mohArray.length==3)
					{
						mohId = Integer.parseInt(mohArray[0]);
					}
					//logger.debug("Mohalla"+mohArray[1]);
					//logger.debug("MohallaID"+mohId);
					//logger.debug("APPROVAL TYPE IN MOHALLA"+formDTO.getApprovalType());
					formDTO.setMohalla(mohArray[2]);
					formDTO.setMohallaId(mohId);
					
					//ArrayList individualMohallaAttributes = bd.getIndividualMohallaDetails(mohId, fYear);
					//ArrayList individualMohallaAttributes = bd.
					//eForm.setMohallaAllDetails(individualMohallaAttributes);
					ArrayList individualMohallaAttributes = bd.guideLineViewChecker(guideID, formDTO.getMoh(),language);
					if(individualMohallaAttributes.size()!= 0)
					{
						eForm.setMohallaAllDetails(individualMohallaAttributes);
						request.setAttribute("eForm", eForm);
						forwardJsp = CommonConstant.APPROVE_CHECKER5;
					}
					else
					{
						//ArrayList individualMohallaAttributes2 = bd.getIndividualMohallaDetails2();
						//System.out.println(individualMohallaAttributes2.size());
						//eForm.setMohallaAllDetails(individualMohallaAttributes2);
						//request.setAttribute("eForm", eForm);
						//logger.debug("Data not entered by MAKER...");
						messages.add("SUCCESS_MSG", new ActionMessage(
						"conf_maker_data_not_entered"));
						saveMessages(request, messages);
						formDTO.setConfirmationChk("11");
						request.setAttribute(CommonConstant.SUCCESS_MSG,"Maker has not entered data in the guideline ID shown below for ");
						forwardJsp= CommonConstant.PUBLISH_MAIN;
						logger.debug(forwardJsp);
					}
					
					
					//forwardJsp = CommonConstant.DISPLAY_APPROVAL;
					//logger.debug("Forwarded to :-   "+forwardJsp);
					
					
					
				}
				
				if("mohallaComplete".equalsIgnoreCase(actionName))
				{
					
					String[] mohArray = formDTO.getMoh().toString().split("~");
					int mohId=0;
					String guideLineID = formDTO.getGuideLineID(); 
					guideID = Long.parseLong(guideLineID);
					if(mohArray.length==3)
					{
						mohId = Integer.parseInt(mohArray[0]);
					}
					//logger.debug("Mohalla"+mohArray[1]);
					//logger.debug("MohallaID"+mohId);
					
					ArrayList individualMohallaAttributes = bd.guideLineViewCheckerComplete(guideID, formDTO.getMoh(), language);
					eForm.setMohallaAllDetails(individualMohallaAttributes);
					//logger.debug(individualMohallaAttributes.size());
					formDTO.setMohallaId(mohId);
					formDTO.setMohalla(mohArray[2]);
					request.setAttribute("eForm", eForm);
					//forwardJsp = CommonConstant.DISPLAY_APPROVAL;
					logger.debug("Forwarded to :-   "+forwardJsp);
					
					forwardJsp = CommonConstant.APPROVE_CHECKER_READ_ONLY;
				}
				
				if("villagePending".equalsIgnoreCase(actionName))
				{
					
					String[] mohArray = formDTO.getMoh().toString().split("~");
					int mohId=0;
					String fYear = formDTO.getFinancialYear();
					String guideLineID = formDTO.getGuideLineID(); 
					guideID = Long.parseLong(guideLineID);
					if(mohArray.length==3)
					{
						mohId = Integer.parseInt(mohArray[0]);
					}
					//logger.debug("Mohalla"+mohArray[1]);
					//logger.debug("MohallaID"+mohId);
					
					formDTO.setMohalla(mohArray[2]);
					formDTO.setMohallaId(mohId);
					
					ArrayList individualMohallaAttributes = bd.guideLineViewChecker(guideID, formDTO.getMoh(), language);
					if(individualMohallaAttributes.size()!= 0)
					{
						eForm.setMohallaAllDetails(individualMohallaAttributes);
						request.setAttribute("eForm", eForm);
						forwardJsp = CommonConstant.APPROVE_CHECKER5;
					}
					
					else
					{
						//ArrayList individualMohallaAttributes2 = bd.getIndividualMohallaDetails2();
						//System.out.println(individualMohallaAttributes2.size());
						//eForm.setMohallaAllDetails(individualMohallaAttributes2);
						//request.setAttribute("eForm", eForm);
						logger.debug("Data not entered by MAKER...");
						messages.add("SUCCESS_MSG", new ActionMessage(
						"conf_maker_data_not_entered"));
						formDTO.setConfirmationChk("11");
						saveMessages(request, messages);
						request.setAttribute(CommonConstant.SUCCESS_MSG,"Maker has not entered data in the guideline ID shown below for ");
						forwardJsp=CommonConstant.PUBLISH_MAIN;
						logger.debug(forwardJsp);
					}
					//forwardJsp = CommonConstant.DISPLAY_APPROVAL;
					//logger.debug("Forwarded to :-   "+forwardJsp);
					
					
				}
				
				if("villageComplete".equalsIgnoreCase(actionName))
				{
					String guideLineID = formDTO.getGuideLineID(); 
					guideID = Long.parseLong(guideLineID);
					String[] mohArray = formDTO.getMoh().toString().split("~");
					int mohId=0;
					if(mohArray.length==3)
					{
						mohId = Integer.parseInt(mohArray[0]);
					}
					//logger.debug("Mohalla"+mohArray[1]);
					//logger.debug("MohallaID"+mohId);
				
				
				ArrayList individualMohallaAttributes = bd.guideLineViewCheckerComplete(guideID, formDTO.getMoh(), language);
				eForm.setMohallaAllDetails(individualMohallaAttributes);
				
				formDTO.setMohallaId(mohId);
				formDTO.setMohalla(mohArray[2]);
				request.setAttribute("eForm", eForm);
				//forwardJsp = CommonConstant.DISPLAY_APPROVAL;
				//logger.debug("Forwarded to :-   "+forwardJsp);
				
				forwardJsp = CommonConstant.APPROVE_CHECKER_READ_ONLY;
			}
				if("OKClick".equalsIgnoreCase(actionName))
				{
					logger.debug("INSIDE OKCLICK ACTION");
					String finan = formDTO.getFinancialYear();
					String district = formDTO.getDistrictID();
					versionList = bd.showStatusChecker(finan, district,language);
					
					HashMap guidelineStatusList = new HashMap();
					if(formDTO.getLanguage().equalsIgnoreCase("en"))
					{
						guidelineStatusList.put(CommonConstant.GUIDELINESTATUS_DRAFT, CommonConstant.DSP_GUIDELINESTATUS_DRAFT);
						guidelineStatusList.put(CommonConstant.GUIDELINESTATUS_FINAL, CommonConstant.DSP_GUIDELINESTATUS_FINAL);
					}
					else
					{
						guidelineStatusList.put(CommonConstant.GUIDELINESTATUS_DRAFT, CommonConstant.H_DSP_GUIDELINESTATUS_DRAFT);
						guidelineStatusList.put(CommonConstant.GUIDELINESTATUS_FINAL, CommonConstant.H_DSP_GUIDELINESTATUS_FINAL);
					}
					eForm.setGuidelineStatusList(guidelineStatusList);
			
					eForm.setVersionList(versionList);
					request.setAttribute("versionList", versionList);
					request.setAttribute("distt", district);
					request.setAttribute("finan", finan);
					
					
					
					
					forwardJsp = CommonConstant.CHECKER1;
				}
				
				/*if("subClause".equalsIgnoreCase(actionName))
				{
					String propID = null;
					String propL1 = null;
					String propL2 = null;
					String val[] = request.getParameter("propVal").split("~");
					if(val != null && val.length == 3)
					{
						propID = val[0];
						propL1 = val[1];
						propL2 = val[2];
					}
					
					formDTO.setPropertyID(propID);
					formDTO.setL1_ID(propL1);
					formDTO.setL2_ID(propL2);
					
					ArrayList mappedSubClauses = bd.mappedSubClauses(formDTO);
					eForm.setMappedSubList(mappedSubClauses);
					
					ArrayList unmappedSubclauses = bd.unMappedSubClauses(formDTO);
					eForm.setUnMappedSubList(unmappedSubclauses);
					
					
					logger.debug("<-------value-------->"+val);
				}
*/				
				
				
				if("nextPage".equalsIgnoreCase(eForm.getGuideDTO().getActionName()))
				{
					
					/*HashMap hMap2 = formDTO.getHMap();
					int size = (hMap2 == null?0:hMap2.size());
					//logger.debug("SIXE OF HASHMAP "+size);
					if(size == 0)
					{
					
						Enumeration parameterList = request.getParameterNames();
					    HashMap hMap = new HashMap();
					    HashMap hMap1 = new HashMap();
					    ArrayList nullKeys = new ArrayList();	
					     logger.debug("GuidelineApprovalAction--ActionForward");
						  while( parameterList.hasMoreElements() )
						  {
						    String sName = parameterList.nextElement().toString();
						    if(sName.startsWith("subID_")){
							     String sVal = (request.getParameter(sName)==null?"0":request.getParameter(sName)) ;
						    	 hMap.put(sName, sVal);
							     //logger.debug("Added--->" + sName +"="+sVal);
						    }
						     // String[] sMultiple = request.getParameterValues( sName );
						      
						        //for( int i=0; i<sMultiple.length; i++ )
						          // if a paramater contains multiple values, print all of them
						          //System.out.println(sName + "[" + i + "] = " + sMultiple[i] + "" );
						   // logger.debug(sName);
						 // }
						  }
						  
						  Set mapSet = (Set)hMap.entrySet();
							
							Iterator mapIterator = mapSet.iterator();
							
							while(mapIterator.hasNext())
							{
								Map.Entry mapEntry = (Map.Entry)mapIterator.next();
								String value = (String)mapEntry.getValue();
								//logger.debug((String)mapEntry.getKey()+" val "+value);
								value = value == null?"":value.trim();
								//TODO change if logic
								if("".equals(value))
								{
									String keyValue = (String)mapEntry.getKey();
									nullKeys.add(keyValue);
								}
							}*/
							//logger.debug(nullKeys.size());
							
							/*
							 * 
							 * getting SubClause Ids for Keys having null values in HashMap1
							 */
							/*if(formDTO.getDerived().toString().equalsIgnoreCase("final") || formDTO.getDerived().toString().equalsIgnoreCase("draft"))
							{
								 hMap1 = bd.subClauseNotClickedDerived(nullKeys,formDTO);
							}
							else
							{
							 hMap1 = bd.subClauseNotClicked(nullKeys);
							}*/
							
							/*
							 * setting values in Previous HashMap i.e hMap
							 * 
							 */
							/*Set mapSet1 = (Set)hMap1.entrySet();
							
							Iterator mapIterator1 = mapSet1.iterator();
							
							while(mapIterator1.hasNext())
							{
								Map.Entry mapEntry1 = (Map.Entry)mapIterator1.next();
								String key = (String)mapEntry1.getKey();
								String value = (String)mapEntry1.getValue();
								hMap.put(key,value);
								
							}
						 // logger.debug("size of map after complete insertion of values"+hMap.size());
						  
						  formDTO.setHMap(hMap);
					}
					
					else
					{
						
						Enumeration parameterList = request.getParameterNames();
					   
					    ArrayList nullKeys = new ArrayList();	
					     logger.debug("GuidelineDeriveAction--ActionForward");
						  while( parameterList.hasMoreElements() )
						  {
						    String sName = parameterList.nextElement().toString();
						    if(sName.startsWith("subID_")){
							     String sVal = request.getParameter(sName) ;
							     //logger.debug("Value"+sVal);
							     if("".equalsIgnoreCase(sVal)!= true)
							     {
							    	 logger.debug("Inside IF --val not null");
							    	 hMap2.put(sName, sVal);
							    	// logger.debug("Added--->" + sName +"="+sVal);
							     }
						    }
						     // String[] sMultiple = request.getParameterValues( sName );
						      
						        //for( int i=0; i<sMultiple.length; i++ )
						          // if a paramater contains multiple values, print all of them
						          //System.out.println(sName + "[" + i + "] = " + sMultiple[i] + "" );
						    //logger.debug(sName);
						 // }
						  }
						  Set mapSet = (Set)hMap2.entrySet();
							
							Iterator mapIterator = mapSet.iterator();
							
							while(mapIterator.hasNext())
							{
								Map.Entry mapEntry = (Map.Entry)mapIterator.next();
								String value = (String)mapEntry.getValue();
								//logger.debug((String)mapEntry.getKey()+" val "+value);
								value = value == null?"":value.trim();
								
								if("".equals(value))
								{
									String keyValue = (String)mapEntry.getKey();
									nullKeys.add(keyValue);
								}
							}
							logger.debug(nullKeys.size());*/
							//logger.debug("GUIDELINE"+formDTO.getGuideLineID());
							//logger.debug("TEHSIL"+formDTO.getTehsilID());
							//logger.debug("WARD"+formDTO.getWardID());
							//logger.debug("mohalla"+formDTO.getMohallaID());
							
							/*
							 * 
							 * getting SubClause Ids for Keys having null values in HashMap1
							 */
							//HashMap hMap1 = bd.subClauseNotClicked(nullKeys);
							
							
							/*
							 * setting values in Previous HashMap i.e hMap
							 * 
							 */
							/*Set mapSet1 = (Set)hMap1.entrySet();
							
							Iterator mapIterator1 = mapSet1.iterator();
							
							while(mapIterator1.hasNext())
							{
								Map.Entry mapEntry1 = (Map.Entry)mapIterator1.next();
								String key = (String)mapEntry1.getKey();
								String value = (String)mapEntry1.getValue();
								hMap2.put(key,value);
								
							}
						 // logger.debug("size of map after complete insertion of values"+hMap2.size());
						  formDTO.setHMap(hMap2);
						
					}*/
					logger.debug("Going to read Only page");
					forwardJsp = CommonConstant.DISPLAY_APPROVAL;
				}
				
			//actionName = formDTO.getActionName();
				
					
		if(("saveReadOnlyPage").equalsIgnoreCase(actionName))
			{
					GuidelineApprovalDAO appDAO = new GuidelineApprovalDAO();
					/*HashMap hMap = new HashMap();
					hMap = formDTO.getHMap();
					
					
					Set mapSet1 = (Set)hMap.entrySet();
					
					Iterator mapIterator1 = mapSet1.iterator();
					
					while(mapIterator1.hasNext())
					{
						Map.Entry mapEntry1 = (Map.Entry)mapIterator1.next();
						String key = (String)mapEntry1.getKey();
						String value = (String)mapEntry1.getValue();
						//logger.debug("FINAL VALUES IN HASHMAP^^^^^^^^^^^^"+key+" val "+value);
						
					}
					
					logger.debug(hMap.size());
					ArrayList propSub = new ArrayList();
					Set mapSet2 = (Set)hMap.entrySet();
					
					Iterator mapIterator2 = mapSet2.iterator();
					
					while(mapIterator2.hasNext())
					{	GuidelineDTO gdObj = new GuidelineDTO();
						Map.Entry mapEntry1 = (Map.Entry)mapIterator2.next();
						
						String keys[] = ((String)mapEntry1.getKey()).split("_");
						gdObj.setPropId(keys[1]);
						gdObj.setPropId1(keys[2]);
						gdObj.setPropId2(keys[3]);
						if((String)mapEntry1.getValue()!=null)
						{
							String values[] = ((String)mapEntry1.getValue()).split("_");
							gdObj.setSubClauseIds(values);
							propSub.add(gdObj);
						}
						else
						{
							
							propSub.add(gdObj);
						}
					}*/
					//logger.debug(propSub.size());
					/*for(int i = 0; i < propSub.size();i++ ){
						GuidelineDTO gd = (GuidelineDTO)propSub.get(i);
						logger.debug(gd.getPropId1());
					}*/
					
					
					//boolean subInsert = appDAO.subClauseInsertion(propSub,formDTO);
					
					
					
					ArrayList mohallaDetails = eForm.getMohallaAllDetails();
					int size = mohallaDetails != null?mohallaDetails.size():0;
					//logger.debug("SIZE IN SAVE ACTION"+size);
					ArrayList gda=new ArrayList();

					for(int i = 0; i < size;i++ ){
						GuidelineDTO gd = (GuidelineDTO)mohallaDetails.get(i);
						gda.add(gd);
					}
					GuidelineDTO gDTO = eForm.getGuideDTO();
					String dFrom = null;
					String dTo = null;
					
					dFrom = formDTO.getFromDepositeDate();
					dTo = formDTO.getToDepositeDate();
					//logger.debug(dFrom);
					
				/*	String []dFromArr = formDTO.getFromDepositeDate().toString().split("-");
					if(dFromArr.length ==3)
					{
						 dFrom = dFromArr[2]+"/"+dFromArr[1]+"/"+dFromArr[0];
					}
					
					String [] dToArr = formDTO.getToDepositeDate().toString().split("-");
					if(dToArr.length ==3)
					{
						 dTo = dToArr[2]+"/"+dToArr[1]+"/"+dToArr[0];
					}
					*/
					
					int mohallaID = formDTO.getMohallaId(); 
					/*
					 * If the selected approval type will be draft then the data will be updated
					 * in IGRS_GUIDELINE_DATA_TEMP table and the status will be changed to "D".
					 */
					request.removeAttribute(CommonConstant.SUCCESS_MSG);
					request.removeAttribute(CommonConstant.FAILURE_MSG);
					//if(formDTO.getApprovalType() ==2){
						//boolean updateTempDraftDetails =  appDAO.validateTempDraft(gda, gDTO, dFrom, dTo, roleId, funId, userId);
					boolean validate = appDAO.validateGuidelineData(gda, gDTO, dFrom, dTo, roleId, funId, userId);	
					//boolean updateTempDraftDetails =  appDAO.updateTempMohallaDetailsNew(gda, gDTO, dFrom, dTo, roleId, funId, userId);
						//boolean insertTempMohallaDetails = appDAO.updateTempMohallaDetailsNew(gda, gDTO, dFrom, dTo,roleId,funId,userId);
					if(validate)
					{
						//boolean subInsert = appDAO.subClauseInsertion(propSub,formDTO);
						boolean updateTempDraftDetails =  appDAO.updateTempMohallaDetailsNew(gda, gDTO, dFrom, dTo, roleId, funId, userId);
						if(updateTempDraftDetails){
							logger.debug("Mohalla Details has been drafted successfully...");
							messages.add("SUCCESS_MSG", new ActionMessage(
							"conf_msg_guideline_approval"));
							formDTO.setConfirmationChk("12");
							saveMessages(request, messages);
							request.setAttribute(CommonConstant.SUCCESS_MSG,"Guideline data has been sucessfully Approved for ");
							forwardJsp= CommonConstant.PUBLISH_MAIN;;
							logger.debug(forwardJsp);
						}else{
							//request.setAttribute(CommonConstant.SUCCESS_MSG,pr.getValue("msg.faliure"));
							eForm.setMohallaAllDetails(gda);
							eForm.setGuideDTO(gDTO);
							messages.add("FAILURE_MSG", new ActionMessage(
							"conf_msg_data_nt_updated"));
							formDTO.setConfirmationChk("12");
							saveMessages(request, messages);
							request.setAttribute(CommonConstant.FAILURE_MSG,"Guideline data has not been updated");
							forwardJsp= CommonConstant.PUBLISH_MAIN;;
							logger.debug(forwardJsp);
							
						}
					
					}
					else
					{
						eForm.setMohallaAllDetails(gda);
						eForm.setGuideDTO(gDTO);
						forwardJsp = "warningOverlappingFinal";
						logger.debug(forwardJsp);
					}
					
					

						//forwardJsp= "draftApproval";
						//logger.debug(forwardJsp);
					//}

					/*
					 * If the selected approval type will be final then the data will be updated
					 * in IGRS_GUIDELINE_DATA_TEMP table and the same data will be inserted into
					 * IGRS_MASTER_GUIDELINE_DATA table status will be changed to "F".
					 * 
					 * Trigger is fired and if there will be new data then it will be replaced by 
					 * the old data and old data will be stored in IGRS_MASTER_GUIDELINE_DATA_H
					 * having a new version being created as 1.3(example).
					 * As well as the same data will be removed from IGRS_GUIDELINE_DATA_TEMP.
					 */
					/*else if(eForm.getGuideDTO().getApprovalType() == 3){
						logger.debug("eForm.getGuideDTO().getApprovalType():-     "+eForm.getGuideDTO().getApprovalType());
						//boolean insertMohallaDetails = appDAO.insertFinalMohallaMasterDetails(gda, gDTO, dFrom, dTo,roleId,funId,userId);
						//boolean insertMohallaDetails = appDAO.validateTempDraft(gda, gDTO, dFrom, dTo, roleId, funId, userId);
						
						boolean insertMohallaDetails = appDAO.validateMasterFinal(gda, gDTO, dFrom, dTo, roleId, funId, userId);
						//boolean insertMohallaDetails = appDAO.insertFinalMohallaMasterDetailsNew(gda, gDTO, dFrom, dTo,roleId,funId,userId);
						if(insertMohallaDetails)
						{
							logger.debug("Mohalla Details has been Finalized successfully...");
							request.setAttribute(CommonConstant.SUCCESS_MSG,"Guideline has been finalized successfully");
							forwardJsp= "finalApproval";
							logger.debug(forwardJsp);
						}else {
							//request.setAttribute(CommonConstant.SUCCESS_MSG,pr.getValue("msg.faliure"));
							eForm.setMohallaAllDetails(gda);
							eForm.setGuideDTO(gDTO);
							forwardJsp = "warningOverlappingFinal";
							logger.debug(forwardJsp);
						}
						
					}*/
				}
				
					
					if(CommonConstant.MODIFY_MOHALLA_DETAILS.equalsIgnoreCase(eForm.getGuideDTO().getActionName())){

						
						formDTO.setModify(true);
						forwardJsp = CommonConstant.APPROVE_CHECKER5;
						logger.debug("Forwarded to :   "+forwardJsp);
					}
					
					
					/*if("saveOverlappindData".equalsIgnoreCase(actionName))
					{
						
						guidelineID = formDTO.getGuideLineID();
						
						System.out.println("Guide ID"+guidelineID);
						
						ArrayList mohallaDetails = eForm.getMohallaAllDetails();
						int size = mohallaDetails != null?mohallaDetails.size():0;
						
						System.out.println("SIZE IN SAVE OVERLAPPING ACTION"+size);
						ArrayList gda=new ArrayList();

						for(int i = 0; i < size;i++ ){
							GuidelineDTO gd = (GuidelineDTO)mohallaDetails.get(i);
							gda.add(gd);
						}
						GuidelineDTO gDTO = eForm.getGuideDTO();
						String dFrom = null;
						String dTo = null;
						
						String []dFromArr = formDTO.getFromDepositeDate().toString().split("-");
						if(dFromArr.length ==3)
						{
							 dFrom = dFromArr[2]+"/"+dFromArr[1]+"/"+dFromArr[0];
						}
						
						String [] dToArr = formDTO.getToDepositeDate().toString().split("-");
						if(dToArr.length ==3)
						{
							 dTo = dToArr[2]+"/"+dToArr[1]+"/"+dToArr[0];
						}
						
						
						int mohallaID = formDTO.getMohallaId(); 
						
						request.removeAttribute(CommonConstant.SUCCESS_MSG);
						GuidelineApprovalDAO appDAO = new GuidelineApprovalDAO();
						
						if(formDTO.getApprovalType()== 2){
							//boolean updateTempDraftDetails =  appDAO.FinalMohallaMasterDetailsNew(gda, gDTO, dFrom, dTo, roleId, funId, userId);
							boolean updateTempDraftDetails = appDAO.updateTempMohallaDetailsNew(gda, gDTO, dFrom, dTo,roleId,funId,userId);
							if(updateTempDraftDetails){
								logger.debug("Mohalla Details has been drafted successfully...");
								request.setAttribute(CommonConstant.SUCCESS_MSG,pr.getValue("msg.modifiedsuccess"));
								forwardJsp= "draftApproval";
								logger.debug(forwardJsp);
							}else{
								//request.setAttribute(CommonConstant.SUCCESS_MSG,pr.getValue("msg.faliure"));
								logger.debug("Mohalla Details has been drafted successfully...");
								request.setAttribute(CommonConstant.SUCCESS_MSG,pr.getValue("msg.modifiedsuccess"));
								forwardJsp= "draftApproval";
								logger.debug(forwardJsp);
								
							}
					}
		}*/
					
				if("saveOverlappindFinalData".equalsIgnoreCase(actionName))
				{
					GuidelineApprovalDAO appDAO = new GuidelineApprovalDAO();
					/*ArrayList nullKeys = new ArrayList();	
					
					HashMap hMap = new HashMap();
					hMap = formDTO.getHMap();
					
					//logger.debug(hMap.size());
					ArrayList propSub = new ArrayList();
					Set mapSet2 = (Set)hMap.entrySet();
					
					Iterator mapIterator2 = mapSet2.iterator();
					
					while(mapIterator2.hasNext())
					{
						Map.Entry mapEntry1 = (Map.Entry)mapIterator2.next();
						GuidelineDTO gdObj = new GuidelineDTO();
						
						String keys[] = ((String)mapEntry1.getKey()).split("_");
						gdObj.setPropId(keys[1]);
						gdObj.setPropId1(keys[2]);
						gdObj.setPropId2(keys[3]);
						if((String)mapEntry1.getValue()!=null)
						{
							String values[] = ((String)mapEntry1.getValue()).split("_");
							gdObj.setSubClauseIds(values);
							propSub.add(gdObj);
						}
						else
						{
							
							propSub.add(gdObj);
						}
							
					}*/
					//logger.debug(propSub.size());
								
					ArrayList mohallaDetails = eForm.getMohallaAllDetails();
					int size = mohallaDetails != null?mohallaDetails.size():0;
								
					//logger.debug("SIZE IN SAVE OVERLAPPING ACTION"+size);
					ArrayList gda=new ArrayList();
		
					for(int i = 0; i < size;i++ ){
						GuidelineDTO gd = (GuidelineDTO)mohallaDetails.get(i);
						gda.add(gd);
					}
					GuidelineDTO gDTO = eForm.getGuideDTO();
					String dFrom = null;
					String dTo = null;
								
					dFrom = formDTO.getFromDepositeDate();
					dTo = formDTO.getToDepositeDate();
					//logger.debug(dFrom);
								
								/*String []dFromArr = formDTO.getFromDepositeDate().toString().split("-");
								if(dFromArr.length ==3)
								{
									 dFrom = dFromArr[2]+"/"+dFromArr[1]+"/"+dFromArr[0];
								}
								
								String [] dToArr = formDTO.getToDepositeDate().toString().split("-");
								if(dToArr.length ==3)
								{
									 dTo = dToArr[2]+"/"+dToArr[1]+"/"+dToArr[0];
								}*/
								
								
					int mohallaID = formDTO.getMohallaId(); 
					request.removeAttribute(CommonConstant.SUCCESS_MSG);
					appDAO = new GuidelineApprovalDAO();
								
					//if(formDTO.getApprovalType()==3){
					//boolean updateTempDraftDetails =  appDAO.FinalMohallaMasterDetailsNew(gda, gDTO, dFrom, dTo, roleId, funId, userId);
					boolean updateDate = appDAO.updateDurationPeriod(gDTO, dFrom);
					if(updateDate)
					{
						boolean updateTempDraftDetails = appDAO.updateTempMohallaDetailsNew(gda, gDTO, dFrom, dTo, roleId, funId, userId);
						if(updateTempDraftDetails){
							logger.debug("Mohalla Details has been drafted successfully...");
							messages.add("SUCCESS_MSG", new ActionMessage(
							"conf_msg_guideline_approval"));
							formDTO.setConfirmationChk("12");
							saveMessages(request, messages);
							request.setAttribute(CommonConstant.SUCCESS_MSG,"Guideline data has been sucessfully Approved for ");
							forwardJsp= CommonConstant.PUBLISH_MAIN;;
							logger.debug(forwardJsp);
							}else{
								//request.setAttribute(CommonConstant.SUCCESS_MSG,pr.getValue("msg.faliure"));
								messages.add("FAILURE_MSG", new ActionMessage(
								"conf_msg_data_nt_updated"));
								formDTO.setConfirmationChk("12");
								saveMessages(request, messages);
									request.setAttribute(CommonConstant.FAILURE_MSG,"Guideline data has not been updated");
									forwardJsp= CommonConstant.PUBLISH_MAIN;;
									logger.debug(forwardJsp);
								}
					}
			}
				
					
					
				
				/*if(request.getParameter("ver")!=null && !("SUBMIT_ACTION".equalsIgnoreCase(actionName))){
					try{
					String version = request.getParameter("ver").toString();
					System.out.println("INSIDE SUBMIT ACTION@@@@"+version);
					//session.setAttribute("instrument", instrument);
					//ArrayList list = commonBo.getExemption(instrument,"instrument");
					//for(int i=0;i<list.size();i++)
						//commonDto.getExemption().add(list.get(i));
					//regForm.setInstrument(instrument);
					//forward="deedSelect";
					}catch (Exception e) {
						e.printStackTrace();
					}
				}
				if("guideLinePrepAction".equals(actionName))
				{
					String disID = formDTO.getDistrict();

					if (disID != null ) {
						String[] districtAry=formDTO.getDistrict().split("~");
						if(districtAry.length == 2 ) {
							ArrayList tehsilList = bd.getTehsilList(districtAry[0]);
							eForm.setTehsilList(tehsilList);
						}
					}
				}

				if("tehsilAction".equalsIgnoreCase(actionName))
				{
					String[] thesilAry=formDTO.getTehsil().split("~");
					if(thesilAry.length == 2)
					{
						ArrayList wardList = bd.getWardList(thesilAry[0]);
						ArrayList patwariList = bd.getPatwariList(thesilAry[0]);
						eForm.setWardList(wardList);
						eForm.setPatwariList(patwariList);
					}
				}*/


				/*
				 * The below condition will fetch the mohalla/ village list from the database
				 * by taking the ward id, duration from date and duration to date.
				 * "mohallaList" is used for the pagination in the jsp page.  
				 */
				/*if("wardAction".equalsIgnoreCase(actionName))         
				{
					String[] wardAry=formDTO.getWard().split("~");
					String fromDate = formDTO.getFromDepositeDate();
					String toDate = formDTO.getToDepositeDate();

					String baseFrom = formDTO.getBasePeriodFrom();
					String baseTo 	= formDTO.getBasePeriodTo();

					session.setAttribute(CommonConstant.DTO,formDTO);*/


					/*
					 * Get individual Mohalla/ Village List.
					 * Parameters:-    Mohalla/VIllage ID - From Date - To Date
					 * attribute is set to mohallalist
					 */
					/*if(wardAry.length == 2)
					{
						ArrayList mohallalist = bd.getMohList(wardAry[0]);
						formDTO.setMohList(mohallalist);
						eForm.setGuideDTO(formDTO);
						session.setAttribute(CommonConstant.MOHALLA_LIST, eForm);
						session.removeAttribute("patwarilist");
						forwardJsp = "approveGuideLine";
					}
				}*/


				/*
				 * Get individual Mohalla/ Village List.
				 * Parameters:-    Mohalla/VIllage ID - From Date - To Date
				 * attribute is set to mohallalist
				 */
				/*if("patwariAction".equalsIgnoreCase(actionName))
				{
					String fromDate = formDTO.getFromDepositeDate();
					String toDate = formDTO.getToDepositeDate();
					String[] patwariAry = formDTO.getPatwari().split("~");

					session.setAttribute(CommonConstant.DTO,formDTO);

					if(patwariAry.length == 2)
					{
						ArrayList patwarilist = bd.getVillage(patwariAry[0], fromDate, toDate);
						eForm.setVillageList(patwarilist);
						session.setAttribute(CommonConstant.PATWARI_LIST, patwarilist);
						session.removeAttribute(CommonConstant.MOHALLA_LIST);
					}
				}

				if("dropDownWardPatwariAction".equalsIgnoreCase(actionName)){
					eForm.getGuideDTO().setWardPatwari(eForm.getGuideDTO().getWardPatwari());

					if(eForm.getGuideDTO().getWardPatwari().equalsIgnoreCase("P")){
						session.removeAttribute(CommonConstant.MOHALLA_LIST);
						eForm.getGuideDTO().setWard("");
					}
					if(eForm.getGuideDTO().getWardPatwari().equalsIgnoreCase("W")){
						session.removeAttribute(CommonConstant.PATWARI_LIST);
						eForm.getGuideDTO().setPatwari("");
					}

				}*/

				if("resetAction".equalsIgnoreCase(actionName))
				{
					formDTO.setDistrict("");
					formDTO.setTehsil("");
					formDTO.setFromDepositeDate("");
					formDTO.setToDepositeDate("");
					formDTO.setBasePeriodFrom("");
					formDTO.setBasePeriodTo("");
					formDTO.setAreaName("");
					formDTO.setWardPatwari("");
					formDTO.setMohalla("");
					formDTO.setWard("");
					formDTO.setPatwari("");
					formDTO.setFinancialYear("");
					session.removeAttribute(CommonConstant.MOHALLA_LIST);
					session.removeAttribute(CommonConstant.PATWARI_LIST);
					forwardJsp = CommonConstant.APPROVE_GUIDELINE;

				}

			/*	if("mohallaAction".equalsIgnoreCase(actionName))    
				{
					GuideLineRule guideRule = new GuideLineRule();

					//session.getAttribute(CommonConstant.DTO);
					GuidelineDTO dtoForm = eForm.getGuideDTO();
					GuidelineDTO dtoFrom = new  GuidelineDTO();
					String fromDate = dtoForm.getFromDepositeDate();
					String toDate = dtoForm.getToDepositeDate();
					String baseFrom = dtoForm.getBasePeriodFrom();
					String baseTo   = dtoForm.getBasePeriodTo();
					String[] thesilAry = dtoForm.getTehsil().split("~");
					String[] wardAry = dtoForm.getWard().split("~");
					String[] districtAry = dtoForm.getDistrict().split("~");
					String mohallaId = dtoForm.getMohallaID();
					String areaType = dtoForm.getAreaName();
					String fYear = dtoForm.getFinancialYear();

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
					session.setAttribute(CommonConstant.DTO, dtoFrom);

					if(thesilAry != null && thesilAry.length==2) {
						formDTO.setTehsilID(thesilAry[0]);
						formDTO.setTehsil(thesilAry[1]);
					}

					formDTO.setToDepositeDate(formDTO.getToDepositeDate());
					formDTO.setFinancialYear(formDTO.getFinancialYear());
					formDTO.setAreaName(formDTO.getAreaName());

					if(wardAry!=null & wardAry.length==2) {
						formDTO.setWardID(wardAry[0]);
						formDTO.setWard(wardAry[1]);
					}
					if(districtAry !=null && districtAry.length==2) {
						formDTO.setDistrictID(districtAry[0]);
						formDTO.setDistrict(districtAry[1]);
					}
					String[] mohArray = mohallaId.split("=");
					if(mohArray!=null && mohArray.length ==2) {
						formDTO.setMohalla(mohArray[1]);
					}
					
					
					
					
					ArrayList start_end = bd.getStartEndDate( fYear);
					logger.debug("<-----After Calling---------->");
					String start= null;
					String end= null;
					if(start_end != null && start_end.size()==2)
					{
						 start =(String)start_end.get(0);
						 
						end =(String)start_end.get(1);
						
					}
					ArrayList errorList = guideRule.checkDurationDates(fromDate, toDate, fYear, start, end, eForm);

					if (guideRule.isError()) {
						eForm.setGuideDTO(dtoFrom);

						request.setAttribute(CommonConstant.DURATION_DATE_INVALID, "true");
						request.setAttribute(CommonConstant.DURATION_DATES_ERRORS, errorList);
					} 
					else {
						ArrayList individualMohallaAttributes = bd.getIndividualMohallaDetails(mohArray[0], fYear);
						eForm.setMohallaAllDetails(individualMohallaAttributes);
						request.setAttribute("eForm", eForm);
						forwardJsp = CommonConstant.DISPLAY_APPROVAL;
						logger.debug("Forwarded to :-   "+forwardJsp);
					}
				}*/
		
				
				/*if(CommonConstant.MODIFY_APPROVAl.equalsIgnoreCase(formName))
				{		
					logger.debug("Form Values are matched for:-   "+CommonConstant.MODIFY_APPROVAl);
					if("mohallaMasterSaveAction".equalsIgnoreCase(eForm.getGuideDTO().getActionName())){

						logger.debug("Hidden values are matched for:-    mohallaMasterSaveAction");
						forwardJsp = CommonConstant.APPROVE_CHECKER5;
						logger.debug("Forwarded to:-  "+forwardJsp);
					}
				}*/

				

				/*if("goFirstPageApproval".equalsIgnoreCase(eForm.getGuideDTO().getActionName()))
				{
					GuidelineDTO dtoForm = (GuidelineDTO)session.getAttribute(CommonConstant.DTO);
					GuidelineDTO dtoFrom = new  GuidelineDTO();
					String fromDate = dtoForm.getFromDepositeDate();
					String toDate = dtoForm.getToDepositeDate();
					String baseFrom = dtoForm.getBasePeriodFrom();
					String baseTo   = dtoForm.getBasePeriodTo();
					String mohallaId = dtoForm.getMohallaID();
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
					forwardJsp = CommonConstant.APPROVE_GUIDELINE;
				}*/

				/*if(CommonConstant.MODIFY_MOHALLA_DETAILS.equalsIgnoreCase(eForm.getGuideDTO().getActionName())){

					forwardJsp = CommonConstant.GUIDELINE_APP_MODIFY;
					logger.debug("Forwarded to :   "+forwardJsp);
				}*/

				/*if(CommonConstant.SAVE_READONLY_PAGE.equalsIgnoreCase(eForm.getGuideDTO().getActionName())){

					ArrayList mohallaDetails = eForm.getMohallaAllDetails();
					int size = mohallaDetails != null?mohallaDetails.size():0;
					ArrayList gda=new ArrayList();

					for(int i = 0; i < size;i++ ){
						GuidelineDTO gd = (GuidelineDTO)mohallaDetails.get(i);
						gda.add(gd);
					}
					GuidelineDTO gDTO = eForm.getGuideDTO();
					String dFrom = formDTO.getFromDepositeDate();
					String dTo   = formDTO.getToDepositeDate();
					String mohallaId = formDTO.getMohallaID();

					String[] mohArray = mohallaId.split("=");
					if(mohArray!=null && mohArray.length ==2) {
						gDTO.setMohallaID(mohArray[0]);
					}*/
					
					/*
					 * If the selected approval type will be draft then the data will be updated
					 * in IGRS_GUIDELINE_DATA_TEMP table and the status will be changed to "D".
					 */
					/*request.removeAttribute(CommonConstant.SUCCESS_MSG);
					GuidelineApprovalDAO appDAO = new GuidelineApprovalDAO();
					if(eForm.getGuideDTO().getApprovalType().equalsIgnoreCase("DRAFT")){
						boolean insertTempMohallaDetails = appDAO.updateTempMohallaDetails(gda, gDTO, dFrom, dTo,roleId,funId,userId);
						if(insertTempMohallaDetails){
							logger.debug("Mohalla Details has been drafted successfully...");
							request.setAttribute(CommonConstant.SUCCESS_MSG,pr.getValue("msg.modifiedsuccess"));
						}else {
							request.setAttribute(CommonConstant.SUCCESS_MSG,pr.getValue("msg.faliure"));
						}

						forwardJsp= "draftApproval";
						logger.debug(forwardJsp);
					}*/

					/*
					 * If the selected approval type will be final then the data will be updated
					 * in IGRS_GUIDELINE_DATA_TEMP table and the same data will be inserted into
					 * IGRS_MASTER_GUIDELINE_DATA table status will be changed to "F".
					 * 
					 * Trigger is fired and if there will be new data then it will be replaced by 
					 * the old data and old data will be stored in IGRS_MASTER_GUIDELINE_DATA_H
					 * having a new version being created as 1.3(example).
					 * As well as the same data will be removed from IGRS_GUIDELINE_DATA_TEMP.
					 */
					/*else if(eForm.getGuideDTO().getApprovalType().equalsIgnoreCase("FINAL")){
						logger.debug("eForm.getGuideDTO().getApprovalType():-     "+eForm.getGuideDTO().getApprovalType());
						boolean insertMohallaDetails = appDAO.insertFinalMohallaMasterDetails(gda, gDTO, dFrom, dTo,roleId,funId,userId);
						if(insertMohallaDetails)
						{
							logger.debug("Mohalla Details has been approved successfully...");
							request.setAttribute(CommonConstant.SUCCESS_MSG,pr.getValue("msg.modifiedsuccess"));
							forwardJsp= "finalApproval";
							logger.debug(forwardJsp);
						}else {
							request.setAttribute(CommonConstant.SUCCESS_MSG,pr.getValue("msg.faliure"));
						}
					}
				}*/
			

			/*if(CommonConstant.MODIFY_APPROVAl.equalsIgnoreCase(formName))
			{		
				logger.debug("Form Values are matched for:-   "+CommonConstant.MODIFY_APPROVAl);
				if("mohallaMasterSaveAction".equalsIgnoreCase(eForm.getGuideDTO().getActionName())){

					logger.debug("Hidden values are matched for:-    mohallaMasterSaveAction");
					forwardJsp = CommonConstant.DISPLAY_APPROVAL;
					logger.debug("Forwarded to:-  "+forwardJsp);
				}
			}*/
				//if("subClause".equalsIgnoreCase(eForm.getGuideDTO().getGuidelineApprovalForm()))
			//{
				if(request.getParameter("actionName") != null)
				{
					if("subClauseClick".equalsIgnoreCase(request.getParameter("actionName")))
					{
						
						
							ArrayList subClauseList = new ArrayList();
							//logger.debug("DERIVEDFROM"+formDTO.getDerived());
							
							
							request.removeAttribute("mappedSubList");
							request.removeAttribute("unMappedSubList");
							String propID = null;
							String propL1 = null;
							String propL2 = null;
							String index = null;
							String value = null;
							String sub = request.getParameter("selectedSubClause");
							//logger.debug(sub);
							propDetails = request.getParameter("prop");
							//logger.debug("*********************"+propDetails);
							formDTO.setPropDetails(propDetails);
							String val[] = formDTO.getPropDetails().split("_");
							request.setAttribute("propDetails",(String)formDTO.getPropDetails());
							int k = 0;
							if(val != null && val.length == 4)
							{
								propID = val[1];
								propL1 = val[2];
								propL2 = val[3];
								//index = val[3];
							}
							
							formDTO.setPropertyID(propID);
							formDTO.setL1_ID(propL1);
							formDTO.setL2_ID(propL2);
							
							value = request.getParameter(propDetails);
							//String value1=request.getParameter.startsWith("subID");
						    //logger.debug(propDetails);
						   // logger.debug("^^^^^^^^^^^^^^^^^^^^^^^^^"+value);
						    if(formDTO.getDerived().toString().equalsIgnoreCase("final") || formDTO.getDerived().toString().equalsIgnoreCase("draft"))
							{
						    	logger.debug("DERIVED");
						    	subClauseList = bd.subclauses(formDTO);
							}
						    else
						    {
							   
						    subClauseList = bd.subclausesNew(formDTO);
	
						    }
							if(!sub.equals(""))
							{
								String selectedSubClause[] = (request.getParameter("selectedSubClause")).split("_");
								for(int i = 0;i<selectedSubClause.length; i++)
								{
									logger.debug(selectedSubClause[i]);
								}
								
							Iterator itr = subClauseList.iterator();
							while(itr.hasNext())
							{
								logger.debug("While LooP");
								GuidelineDTO gDTO = (GuidelineDTO)itr.next();
								gDTO.getSubClauseId();
								for(int i = 0;i<selectedSubClause.length; i++)
								{
									logger.debug("FOR LOOP");
									if(selectedSubClause[i] != null && selectedSubClause[i].equals(gDTO.getSubClauseId()))
											{
												gDTO.setStatus("Y");
												logger.debug("STATUS EQUALS to Y");
												break;
											}
									else
									{
										gDTO.setStatus("N");
									}
								}
							}
							logger.debug("SUB CLAUSES"+subClauseList.size());
								//eForm.setSubClauseList(subClauseList);
								//request.setAttribute("subClauseList",subClauseList);
								//eForm.getGuideDTO().setActionName("subClauseClick");
								
								//forwardJsp = CommonConstant.SUB_CLAUSE;
								}
							
							if(formDTO.isModify())
							{
								HashMap hMap = formDTO.getHMap();
								//logger.debug("SIZE OF HASH MAP IN READ ONLY CLICK"+hMap.size());
								String subIdArr[] = {};
								Set mapSet = (Set)hMap.entrySet();
								
								Iterator mapIterator = mapSet.iterator();
								Iterator itr = subClauseList.iterator();
								
								while(mapIterator.hasNext())
								{
									Map.Entry mapEntry = (Map.Entry)mapIterator.next();
									if(mapEntry.getKey().equals(propDetails))
									{
										logger.debug("Inside if condition");
										logger.debug("String Value is"+mapEntry.getValue());
										Object obj=null;
										if(mapEntry.getValue()!=null){
											subIdArr = ((String)mapEntry.getValue()).split("_");
									}
										break;
									}
									
								}
								
								while(itr.hasNext())
								{
									logger.debug("While LooP");
									GuidelineDTO gDTO = (GuidelineDTO)itr.next();
									gDTO.getSubClauseId();
									for(int i = 0;i<subIdArr.length; i++)
									{
										logger.debug("FOR LOOP");
										if(subIdArr[i] != null && subIdArr[i].equals(gDTO.getSubClauseId()))
												{
													gDTO.setStatus("Y");
													logger.debug("STATUS EQUALS to Y");
													break;
												}
										else
										{
											gDTO.setStatus("N");
										}
									}
								}
							}
					
	/////////////////////
						    logger.debug("SUB CLAUSES"+subClauseList.size());
							eForm.setSubClauseList(subClauseList);
							request.setAttribute("subClauseList",subClauseList);
							//eForm.getGuideDTO().setActionName("subClauseClick");
							
							
	
							/*else
							{
						
								propDetails = request.getParameter("prop");
								
								
								logger.debug("*********************"+propDetails);
								formDTO.setPropDetails(propDetails);
								String val[] = formDTO.getPropDetails().split("_");
								request.setAttribute("propDetails",(String)formDTO.getPropDetails());
								int k = 0;
								ArrayList subClauseList = new ArrayList();
								
								if(val != null && val.length == 4)
								{
									propID = val[1];
									propL1 = val[2];
									propL2 = val[3];
									//index = val[3];
								}
								
								formDTO.setPropertyID(propID);
								formDTO.setL1_ID(propL1);
								formDTO.setL2_ID(propL2);
								
								value = request.getParameter(propDetails);
								//String value1=request.getParameter.startsWith("subID");
							    logger.debug(propDetails);
							    logger.debug("^^^^^^^^^^^^^^^^^^^^^^^^^"+value);
								 
								   
							    subClauseList = bd.subclausesNew(formDTO);
							    logger.debug("SUB CLAUSES"+subClauseList.size());
								eForm.setSubClauseList(subClauseList);
								request.setAttribute("subClauseList",subClauseList);
								//eForm.getGuideDTO().setActionName("subClauseClick");
								
								forwardJsp = CommonConstant.SUB_CLAUSE;
						    	
							}	*/
							     
							    
							  
							 
	
							
								
						
							
							/*ArrayList mappedSubClauses = bd.mappedSubClauses(formDTO);
							logger.debug("MAPPED"+mappedSubClauses.size());
							eForm.setMappedSubList(mappedSubClauses);
							request.setAttribute("mappedSubList",mappedSubClauses);
							
							ArrayList unMappedSubclauses = bd.unMappedSubClauses(formDTO);
							logger.debug("UNMAPPED"+unMappedSubclauses.size());
							eForm.setUnMappedSubList(unMappedSubclauses);
							request.setAttribute("unMappedSubList",unMappedSubclauses);*/
						
						
						forwardJsp = CommonConstant.SUB_CLAUSE;
						
					}
					
					if("subClauseClickReadOnly".equalsIgnoreCase(request.getParameter("actionName")))
					{
						
						String propID = null;
						String propL1 = null;
						String propL2 = null;
						String index = null;
						String value = null;
						ArrayList subClauseList = null;
						propDetails = request.getParameter("prop");
						//logger.debug("*********************"+propDetails);
						HashMap hMap = formDTO.getHMap();
						//logger.debug("SIZE OF HASH MAP IN READ ONLY CLICK"+hMap.size());
						
						Set mapSet = (Set)hMap.entrySet();
						
						Iterator mapIterator = mapSet.iterator();
						
						while(mapIterator.hasNext())
						{
							Map.Entry mapEntry = (Map.Entry)mapIterator.next();
							if(mapEntry.getKey().equals(propDetails))
							{
								logger.debug("Inside if condition");
								//logger.debug("String Value is"+mapEntry.getValue());
								Object obj=null;
								if(mapEntry.getValue()!=null){
									String subIds[] = ((String)mapEntry.getValue()).split("_");
									subClauseList = bd.subClause(subIds);
									logger.debug("size of arrayList in if condition");
								}
								break;
							}
						}
						
						
						
						/*formDTO.setPropDetails(propDetails);
						String val[] = formDTO.getPropDetails().split("_");
						request.setAttribute("propDetails",(String)formDTO.getPropDetails());
						int k = 0;
						ArrayList subClauseList = new ArrayList();
						
						value = request.getParameter(propDetails);
						
						logger.debug(value);
						
						if(val != null && val.length == 3)
						{
							propID = val[0];
							propL1 = val[1];
							propL2 = val[2];
							//index = val[3];
						}
						
						formDTO.setPropertyID(propID);
						formDTO.setL1_ID(propL1);
						formDTO.setL2_ID(propL2);*/
						//String value =    
					    
					    	
						  
						 
						//logger.debug("SUB CLAUSES"+subClauseList.size());
						eForm.setSubClauseList(subClauseList);
						request.setAttribute("subClauseList",subClauseList);
						//eForm.getGuideDTO().setActionName("subClauseClick");
						
						forwardJsp = CommonConstant.SUB_CLAUSE_READ_ONLY;
						
					}
					
					
					if("subClauseClickComplete".equalsIgnoreCase(request.getParameter("actionName")))
					{
						
						String propID = null;
						String propL1 = null;
						String propL2 = null;
						String index = null;
						String value = null;
						ArrayList subClauseList = null;
						
					
						propDetails = request.getParameter("prop");
						//logger.debug("*********************"+propDetails);
						
						formDTO.setPropDetails(propDetails);
						String val[] = propDetails.split("_");
						request.setAttribute("propDetails",(String)formDTO.getPropDetails());
						int k = 0;
					
						
						if(val != null && val.length == 4)
						{
							propID = val[1];
							propL1 = val[2];
							propL2 = val[3];
							//index = val[3];
						}
						
						formDTO.setPropertyID(propID);
						formDTO.setL1_ID(propL1);
						formDTO.setL2_ID(propL2);
						
						if(formDTO.getDerived().toString().equalsIgnoreCase("final") || formDTO.getDerived().toString().equalsIgnoreCase("draft"))
						{
					    	logger.debug("DERIVED");
					    	subClauseList = bd.subClauseReadOnlyComplete(formDTO);
					    	int size = subClauseList != null?subClauseList.size():0;
					    	if(size == 0)
					    	{
					    		subClauseList = bd.subClauseReadOnlyCompleteDerived(formDTO);
					    	}
						}
						else
						{
							subClauseList = bd.subClauseReadOnlyComplete(formDTO);
						}
					
						//logger.debug("SIZE of SUB CLAUSE LIST"+subClauseList.size());
						eForm.setSubClauseList(subClauseList);
						request.setAttribute("subClauseList",subClauseList);
						
						
						forwardJsp = CommonConstant.SUB_CLAUSE_READ_ONLY;
						
					}
				}
				
				
			//}
		}
		}
				
		logger.debug("forward Page"+forwardJsp);
		return mapping.findForward(forwardJsp);
	

}
	
	/**
	 * for generatinf the pdf when the guideline is published
	 * >>>>>> JRXMLS <<<<<<<<<
	 * @param dto,form,string
	 * @return void
	 * @author SHREERAJ
	 */	
	public boolean generatePDF(GuidelineDTO formDTO,GuidelinePreparationForm eForm,String lang,HttpServletResponse response) throws Exception{
		formDTO.setGuideType("");
		boolean boo  = false;
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
											boo = true;
										} catch (Exception ex) {
											logger.debug(ex.getMessage(),ex);
											boo = false;
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
									         return boo;
										}
									
							            
								}/*catch(Exception e){
									 e.printStackTrace();
								 }*/
								
	
	/**
	 * for generating the pdf when the guideline is published through 2D-Graphics
	 * 
	 * @param String,Map,String,JasperReport,JRBeanCollectionDataSource,String
	 * @return void
	 * @author SHREERAJ
	 */	
	public void getJasperPdfviewerFromImg(String destFileName, Map parameters, String uniqeid,JasperReport jasperMasterReport,JRBeanCollectionDataSource beanColDataSource,String path) throws Exception{

		path=path+File.separator;
	      String REPORT_URL = "";
	      OutputStream outputStream = new FileOutputStream(path +uniqeid+ ".pdf");
	     // String fileTitle = "D://IGRS" + "//" + iifName + "_" + uniqeid + ".pdf";
	      String fileTitle =path +uniqeid+ ".pdf";
	    //  DBUtility dbUtil = new DBUtility("");
	      //Connection conn = dbUtil.getDBConnection();
	      try {
	    	  File file = new File(destFileName);
	    	  Document document=null;
	    	  int size=0;
	         // JasperPrint jasperPrint;
	          //jasperPrint = JasperFillManager.fillReport(path, map, conn);
/*	          JasperFillManager.fillReportToFile(destFileName,
	        		  jasperMasterReport, parameters, beanColDataSource);*/
	          JasperFillManager.fillReportToFile(jasperMasterReport, destFileName, parameters, beanColDataSource);
			     JasperPrint jasperPrint = (JasperPrint) JRLoader.loadObject(file);
	          Rectangle ngle = new Rectangle(595, 1000);
	        //  if("all".equalsIgnoreCase(iifName)||"plot".equalsIgnoreCase(iifName)){
	         //  document = new Document(PageSize.A4,50,50,50,50);//PageSize.A4);
	         //  size=75;
	         // }
	         // else{
		           document = new Document(PageSize.A4.rotate());//PageSize.A4);
		           size=85;
	        //  }
	          BufferedImage img = null;
	          Graphics2D g = null;
	          File output = null;
	        String tempDirectory = path;
	          String iifFolderName = jasperPrint.getName();
	          String iifReporytName = jasperPrint.getName();
	          for (int i = 0; i < jasperPrint.getPages().size(); i++) {
	              output = new File(tempDirectory + "//" + iifReporytName + uniqeid + i + ".jpeg");
	              img = new BufferedImage(jasperPrint.getPageWidth(), jasperPrint.getPageHeight(), BufferedImage.TYPE_INT_RGB);
	              g = (Graphics2D) img.getGraphics();
	              JRGraphics2DExporter exporter = new JRGraphics2DExporter();
	              exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
	              exporter.setParameter(JRGraphics2DExporterParameter.GRAPHICS_2D, g);
	              exporter.setParameter(JRExporterParameter.PAGE_INDEX, new Integer(i));
	              exporter.setParameter(JRExporterParameter.OFFSET_X, new Integer(0)); //lblPage border
	              exporter.setParameter(JRExporterParameter.OFFSET_Y, new Integer(0));
	              exporter.exportReport();
	              img.flush();
	              g.dispose();
	              ImageIO.write(img, "jpeg", output);
	          }
	          PdfWriter.getInstance(document, new FileOutputStream(tempDirectory + "/" + iifReporytName + uniqeid + ".pdf"));
	          document.open();
	          Image image1 = null;
	          for (int i = 0; i < jasperPrint.getPages().size(); i++) {
	              image1 = Image.getInstance(tempDirectory + "//" + iifReporytName + uniqeid + i + ".jpeg");
	              image1.scalePercent(size);
	         //     image1.scaleToFit( image1.getAbsoluteX(),  image1.getAbsoluteY());
	              document.add(image1);
	          }
	          document.addTitle(fileTitle);
	          document.close();
	          FileInputStream inputStream = null;
	          inputStream = new FileInputStream(tempDirectory + "//" + iifReporytName + uniqeid + ".pdf");
	          File file1 = new File(REPORT_URL + "//" + iifReporytName + "_" + uniqeid + ".pdf");
	         // long filesize = file1.length();
	          byte[] buffer = new byte[10024];
	          int bytesRead = 0;
	          do {
	              bytesRead = inputStream.read(buffer);
	              outputStream.write(buffer, 0, bytesRead);

	          } while (bytesRead == buffer.length);
	          outputStream.flush();
	          outputStream.close();
	          inputStream.close();

	          ////////////////////////////To Cleand Temp File After Export ////////////////////////////////////
	          File imagefiles = null;
	          for (int i = 0; i < jasperPrint.getPages().size(); i++) {
	              imagefiles = new File(tempDirectory + iifReporytName + uniqeid + i + ".jpeg");
	              boolean success = imagefiles.delete();
	          }
	          File pdffile = new File(tempDirectory + iifReporytName + uniqeid + ".pdf");
	          boolean success = pdffile.delete();
	          System.out.println("cleaning done , now opening file");
	          ////////////////////////////To Cleand Temp File After Export ////////////////////////////////////

	          /* 
	           * Opening the file
	           */
	          /*File fileToOpen = new File(path +uniqeid+ ".pdf");
	        
	           if (Desktop.isDesktopSupported()) {
	              try {

	                  Desktop.getDesktop().open(fileToOpen);
	              } catch (IOException ex) {
	                  ex.printStackTrace();
	              }
	          }*/
	      }catch (IOException ex) {
	          ex.printStackTrace();
	      }
		  
	}
	
	/**
	 * for generating the All PDF of guideline 
	 * 
	 * @param string
	 * @return void
	 * @author SHREERAJ
	 */	
	public void generateAllPDF(String path) throws Exception{
		path=path+File.separator;
		try{
			
			String fileOne=path+CommonConstant.PDF_NAME_PRAROOP1_EXT;
			String fileSecond=path+CommonConstant.PDF_NAME_PRAROOP2_EXT;
			String fileThird=path+CommonConstant.PDF_NAME_PRAROOP3_EXT;
			FileInputStream f1=new FileInputStream(fileOne);
			FileInputStream f2=new FileInputStream(fileSecond);
			FileInputStream f3=new FileInputStream(fileThird);
			PDFMergerUtility ut = new PDFMergerUtility();
			ut.addSource(f1);
			ut.addSource(f2);
			ut.addSource(f3);
			ut.setDestinationFileName(path+CommonConstant.PDF_NAME_PRAROOPALL_EXT);
			ut.mergeDocuments();
			logger.debug("merging Done--->");
			System.out.println("merging done---->");
			byte os[]=DMSUtility.getDocumentBytes(fileOne);
			FileInputStream fis = new FileInputStream(new File(path+"/"+CommonConstant.PDF_NAME_PRAROOPALL_EXT));
			byte[] abc = IOUtils.toByteArray(fis);
			fis.close();
		
	}catch(Exception e){
		
		logger.debug("EXCEPTION IN MERGING ALL THE PDF"+e);
		e.printStackTrace();	
		throw e;
		
	}
	}
		
}