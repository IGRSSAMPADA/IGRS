package com.wipro.igrs.guideline.action;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.wipro.igrs.DMSConnection.DMSUtility;
import com.wipro.igrs.UserRegistration.util.PropertiesFileReader;
import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.guideline.bd.GuidelinePreparationBD;
import com.wipro.igrs.guideline.bd.GuidelineViewBD;
import com.wipro.igrs.guideline.constant.CommonConstant;
import com.wipro.igrs.guideline.dto.GuidelineDTO;
import com.wipro.igrs.guideline.form.GuidelinePreparationForm;

public class GuideLineViewCitizenAction extends Action{
	
String forwardJsp = new String("view1");
	
	private Logger logger = Logger.getLogger(GuideLineViewCitizenAction.class);

	
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @return NONE
	 * @throws Exception
	 */
	@Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession(true);
		String page=request.getParameter("page");
		logger.debug("<----------------INSIDE CITIZEN ACTION----->");
		String userId = (String)session.getAttribute("UserId");
		String language = (String)session.getAttribute("languageLocale");
		String actId = (String)request.getParameter("actId");
		String fwd = (String)request.getParameter("fwd");
		ActionMessages messages = new ActionMessages();		
		String districtName = null;
		String target = null;
		String actionName = null;
		if (form != null) {
			GuidelinePreparationForm eForm = (GuidelinePreparationForm) form;
			GuidelineViewBD viewBD = new GuidelineViewBD();
			GuidelineDTO formDTO=eForm.getGuideDTO();
			
			formDTO.setLanguage(language);
			//ArrayList areaTypes = viewBD.getAreasTypeList();
			//eForm.setAreaTypeList(areaTypes);
			
			
			/**
			 * 
			 * Getting Status for the guideline from COMMONCONSTANTS
			 */
			
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
			
			
		
		if(page!=null){
			if("view".equals(page)){
				ArrayList districtList = viewBD.getDistrictList(language);
				eForm.setDistrictList(districtList);
				
				ArrayList financialYearList = viewBD.getActiveFinancialYearList();
				eForm.setFinancialYearList(financialYearList);
				eForm.setTehsilList(new ArrayList());
				eForm.setWardList(new ArrayList());
				eForm.setMohallaList(new ArrayList());
				eForm.setPatwariList(new ArrayList());
				
				eForm.setGuideDTO(new GuidelineDTO());
				
				//--####added by satbir kumar---###
				
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
				
				//###----end of adittion-------#####
				
				
				formDTO.setActionName("");
				formDTO.setDistrict("");
				formDTO.setDistrictID("");
				formDTO.setFinancialYear("");
				formDTO.setWard("");
				formDTO.setStatus("");
				formDTO.setGuideType("F");
				formDTO.setHdnAppType("F");
				eForm.getGuideDTO().setGuideType("F");
				eForm.getGuideDTO().setHdnAppType("F");
				formDTO.setGuidelineStatus("");
				session.removeAttribute("mohallalist");
				//************for log*****************//
				IGRSCommon save=null;

                try {

                        save = new IGRSCommon();

                        save.saveactivitylog(userId, actId);

                } catch (Exception e) {

                       

                        e.printStackTrace();

                }
              /* forwardJsp = CommonConstant.MAKER1;
				logger.debug("Page is forwarded to :-    "+forwardJsp);
               */ //****************************//
				forwardJsp = CommonConstant.VIEW1 ;
				logger.debug("GUIDELINEVIEW ACTION::  FORWARDED TO :-     "+forwardJsp);
			}
		}
		if(page!=null){
			if("viewDraft".equals(page)){
				//************for log*****************//
				IGRSCommon save=null;

                try {

                        save = new IGRSCommon();

                        save.saveactivitylog(userId, actId);

                } catch (Exception e) {

                       

                        e.printStackTrace();

                }

                //****************************//
				if(userId == null)
				{
					session.setAttribute("langModule", "guidelineDraft");
				}
				else
				{
					session.removeAttribute("langModule");
				}
				ArrayList districtList = viewBD.getDistrictList(language);
				eForm.setDistrictList(districtList);
				
				ArrayList financialYearList = viewBD.getActiveFinancialYearList();
				eForm.setFinancialYearList(financialYearList);
				eForm.setTehsilList(new ArrayList());
				eForm.setWardList(new ArrayList());
				eForm.setMohallaList(new ArrayList());
				eForm.setPatwariList(new ArrayList());
				
				
				eForm.setGuideDTO(new GuidelineDTO());
				formDTO.setActionName("");
				formDTO.setDistrict("");
				formDTO.setDistrictID("");
				formDTO.setFinancialYear("");
				formDTO.setWard("");
				formDTO.setStatus("");
				formDTO.setGuidelineStatus("");
				session.removeAttribute("mohallalist");
				forwardJsp = CommonConstant.VIEW_DRAFT ;
				logger.debug("GUIDELINEVIEW ACTION::  FORWARDED TO :-     "+forwardJsp);
			}
		}
		if(page!=null){
			
			if("viewFinal".equals(page)){
				//************for log*****************//
				IGRSCommon save=null;

                try {

                        save = new IGRSCommon();

                        save.saveactivitylog(userId, actId);

                } catch (Exception e) {

                       

                        e.printStackTrace();

                }

                //****************************//
				if(userId == null)
				{
					session.setAttribute("langModule", "guideline");
				}
				else
				{
					session.removeAttribute("langModule");
				}
				ArrayList districtList = viewBD.getDistrictList(language);
				eForm.setDistrictList(districtList);
				
				ArrayList financialYearList = viewBD.getActiveFinancialYearList();
				eForm.setFinancialYearList(financialYearList);
				eForm.setTehsilList(new ArrayList());
				eForm.setWardList(new ArrayList());
				eForm.setMohallaList(new ArrayList());
				eForm.setPatwariList(new ArrayList());
				
				
				eForm.setGuideDTO(new GuidelineDTO());
				formDTO.setActionName("");
				formDTO.setDistrict("");
				formDTO.setDistrictID("");
				formDTO.setFinancialYear("");
				formDTO.setWard("");
				formDTO.setStatus("");
				formDTO.setGuidelineStatus("");
				session.removeAttribute("mohallalist");
				forwardJsp = CommonConstant.VIEW_FINAL ;
				logger.debug("GUIDELINEVIEW ACTION::  FORWARDED TO :-     "+forwardJsp);
			}
		}
		
		logger.debug("###################"+formDTO.getActionName());

			if(CommonConstant.GUIDELINE_VIEW_FORM_CITIZEN.equalsIgnoreCase(eForm
					.getGuideDTO().getGuidelineViewForm()))
			{
				
				logger.debug("###################"+formDTO.getActionName());
				
				
				
				if(request.getParameter("actionName")!= null)
				{
					actionName = request.getParameter("actionName");
				}
				else
				{
					actionName = formDTO.getActionName();
				}
				
				if(request.getParameter("Prnt")!= null)
				{
					logger.debug("<-----------INSIDE PRINT");
					String par = request.getParameter("Prnt");
					
					if("Y".equalsIgnoreCase(par)){
					formDTO.setActionName("");	
					
                	forwardJsp= CommonConstant.PRINT_GUIDELINE_PAGE; 
                	
					}

				}
				
				if("district".equalsIgnoreCase(actionName))
				{
					System.out.println("Inside DISTRICT");
					districtName = formDTO.getDistrict();
					System.out.println("<----"+districtName);
				}
				if("dropDownGuideLineStatus".equalsIgnoreCase(actionName)){
					
					//eForm.getGuideDTO().setMoh(eForm.getGuideDTO().getMoh());
					eForm.getGuideDTO().setGuideStatus(eForm.getGuideDTO().getGuideStatus());
					//logger.debug("inside status"+eForm.getGuideDTO().getGuideStatus());
					if(eForm.getGuideDTO().getGuideStatus() == 3)
					{
						logger.debug("FINAL");
					}
					formDTO.setGuideType("");
					forwardJsp = CommonConstant.VIEW1; 
				}
				
				if("showFinal".equalsIgnoreCase(actionName))
				{
					eForm.setTehsilList(new ArrayList());
					eForm.setWardList(new ArrayList());
					eForm.setMohallaList(new ArrayList());
					eForm.setPatwariList(new ArrayList());
					formDTO.setMailContent("");
					
					eForm.setGuideDTO(new GuidelineDTO());
					formDTO.setActionName("");
					formDTO.setDistrict("");
					formDTO.setDistrictID("");
					formDTO.setFinancialYear("");
					formDTO.setWard("");
					formDTO.setStatus("");
					formDTO.setGuidelineStatus("");
					session.removeAttribute("mohallalist");
					forwardJsp = CommonConstant.VIEW_FINAL ;
					logger.debug("GUIDELINEVIEW ACTION::  FORWARDED TO :-     "+forwardJsp);
				}
				
				
				
				if("showVersion".equalsIgnoreCase(actionName))
				{//TODO
					ArrayList versionList  = new ArrayList();
					formDTO.setMailContent("");
					formDTO.setCheck("");
					String dataType = null;
					logger.debug("<--------INSIDE SHOW VERSION--------->");
					//logger.debug("<--------INSIDE SHOW VERSIONT--------->"+formDTO.getPraroopTypeId());
					//logger.debug("DISTT"+formDTO.getDistrictID());
					//logger.debug(formDTO.getFinancialYear());
					//logger.debug("Status"+formDTO.getGuideStatus());
					//logger.debug("APP TYPE"+formDTO.getGuideType());
					
					if(formDTO.getGuideStatus() == 	3 && formDTO.getGuideType().equalsIgnoreCase("F"))
					{
						dataType = "final";
					}
					else
					{
						dataType = "temp";
					}
					versionList =  viewBD.viewVersionsTemp(formDTO, dataType);
					logger.debug("SIZE"+versionList.size());
					int size = (versionList == null?0:versionList.size());
					if(size == 0)
					{
						logger.debug("No DATA FOUND");
						
						formDTO.setCheck("Y");
						messages.add("MSG", new ActionMessage(
						"no_data_found"));
						saveMessages(request, messages);
						//request.setAttribute(CommonConstant.SUCCESS_MSG,"No guideline data available");
					}
					else
					{
					eForm.setVersionList(versionList);
					request.setAttribute("versionList",versionList);
					}
					formDTO.setPage(CommonConstant.VIEW1);
					forwardJsp = CommonConstant.VIEW1; 
					
				}
				
				if("showVersionDraft".equalsIgnoreCase(actionName))
				{
					ArrayList versionList  = new ArrayList();
					formDTO.setCheck("");
					formDTO.setMailContent("");
					String dataType = null;
					logger.debug("<--------INSIDE SHOW VERSION DRAFT--------->");
					//logger.debug("<--------INSIDE SHOW VERSION DRAFT--------->"+formDTO.getPraroopTypeId());
					//logger.debug("DISTT"+formDTO.getDistrictID());
					//logger.debug("DISTRICT"+formDTO.getDistrict());
					//logger.debug(formDTO.getFinancialYear());
					//logger.debug("Status"+formDTO.getGuideStatus());
					formDTO.setGuideType("D");
					//logger.debug("APP TYPE"+formDTO.getGuideType());
					
					//if(formDTO.getGuideStatus() == 3 && formDTO.getGuideType().equalsIgnoreCase("F"))
					//{
					//	dataType = "final";
					//}
					//else
					//{
						dataType = "temp";
					//}
					versionList =  viewBD.viewVersionsTemp(formDTO, dataType);
					logger.debug("SIZE"+versionList.size());
					
					
					int size = (versionList == null?0:versionList.size());
					if(size == 0)
					{
						logger.debug("No DATA FOUND");
						formDTO.setCheck("Y");
						messages.add("MSG", new ActionMessage(
						"no_data_found"));
						saveMessages(request, messages);
						//request.setAttribute(CommonConstant.SUCCESS_MSG,"No guideline data available");
					}
					else
					{
					eForm.setVersionList(versionList);
					request.setAttribute("versionList",versionList);
					}
					formDTO.setPage(CommonConstant.VIEW_DRAFT);
					forwardJsp = CommonConstant.VIEW_DRAFT; 
					
				}
				if("showVersionFinal".equalsIgnoreCase(actionName))
				{
					ArrayList versionList  = new ArrayList();
					String dataType = null;
					formDTO.setCheck("");
					logger.debug("<--------INSIDE SHOW VERSION FINAL--------->");
					//logger.debug("DISTT"+formDTO.getDistrictID());
					//logger.debug("DISTRICT"+formDTO.getDistrict());
					//logger.debug(formDTO.getFinancialYear());
					String guideLineId = null;
					String dFrom = null;
					String dTo = null;
					Date sys = new Date();
					int f1,f2;
					//logger.debug("System date"+sys);
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					String s1 = sdf.format(sys);
					Date curr = sdf.parse(s1);
					//logger.debug("date"+s1);
					SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy");
					SimpleDateFormat sdf2 = new SimpleDateFormat("MM");
					String currYear = sdf1.format(curr);
					int curYear = Integer.parseInt(currYear);
					String currMonth = sdf2.format(curr);
					//logger.debug("Current Year"+curYear);
					//logger.debug("Current Month"+currMonth);
					if(currMonth.equals("01")||currMonth.equals("02")||currMonth.equals("03"))
					{
						f1 = curYear-1;
						f2 = curYear;
					}
					else
					{
						f1= curYear;
						f2 = curYear+1;
					}
					currYear = ((Integer)f1).toString()+"-"+((Integer)f2).toString();
					//logger.debug("YEAR IN SHOW FINAL"+currYear);
					formDTO.setFinancialYear(currYear);
					
					//logger.debug("Status"+formDTO.getGuideStatus());
					formDTO.setGuideType("F");
					//logger.debug("APP TYPE"+formDTO.getGuideType());
					
					//if(formDTO.getGuideStatus() == 3 && formDTO.getGuideType().equalsIgnoreCase("F"))
					//{
					//	dataType = "final";
					//}
					//else
					//{
						dataType = "final";
					//}
					versionList =  viewBD.viewVersionsTemp(formDTO, dataType);
					//logger.debug("SIZE"+versionList.size());
					int size = (versionList == null?0:versionList.size());
					if(size == 0)
					{
						logger.debug("No DATA FOUND");
						request.setAttribute(CommonConstant.SUCCESS_MSG,"No guideline data available");
					}
					else
					{
						Iterator itr = versionList.iterator();
						while(itr.hasNext())
						{
							GuidelineDTO gDTO = (GuidelineDTO)itr.next();
							guideLineId = gDTO.getGuideLineID();
							dFrom = gDTO.getFromDepositeDate();
							dTo = gDTO.getToDepositeDate();
							
							
						}
						formDTO.setGuideLineID(guideLineId);
						logger.debug(guideLineId);
						formDTO.setFromDepositeDate(dFrom);
						formDTO.setToDepositeDate(dTo);
						
						
						ArrayList tehsilList = viewBD.getTehsilList(formDTO.getDistrictID(),formDTO.getLanguage());
						eForm.setTehsilList(tehsilList);
						
						ArrayList areaTypes = viewBD.getAreasTypeList();
						eForm.setAreaTypeList(areaTypes);
						
						
					}
					forwardJsp = CommonConstant.VIEW2;
					
				}
				////////////////////////ADDED---30/4/2013-----///////////////////////////
				if("districtWiseView".equalsIgnoreCase(actionName))
				{
					ArrayList versionList  = new ArrayList();
					eForm.setGuidelineDataMapPraroop1(new LinkedHashMap());
					eForm.setGuidelineDataMapPraroop2(new LinkedHashMap());
					eForm.setGuidelineDataMapPraroop3(new LinkedHashMap());
					String dataType = null;
					//logger.debug("<--------INSIDE SHOW VERSION FINAL--------->");
					//logger.debug("<--------INSIDE SHOW VERSION FINAL--------->"+formDTO.getPraroopTypeId());
					//logger.debug("DISTT"+formDTO.getDistrictID());
					//logger.debug("DISTRICT"+formDTO.getDistrict());
					//logger.debug(formDTO.getFinancialYear());
					String guideLineId = null;
					String dFrom = null;
					String dTo = null;
					Date sys = new Date();
					int f1,f2;
					//logger.debug("System date"+sys);
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					String s1 = sdf.format(sys);
					Date curr = sdf.parse(s1);
					//logger.debug("date"+s1);
					SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy");
					SimpleDateFormat sdf2 = new SimpleDateFormat("MM");
					String currYear = sdf1.format(curr);
					int curYear = Integer.parseInt(currYear);
					String currMonth = sdf2.format(curr);
					//logger.debug("Current Year"+curYear);
					//logger.debug("Current Month"+currMonth);
					if(currMonth.equals("01")||currMonth.equals("02")||currMonth.equals("03"))
					{
						f1 = curYear-1;
						f2 = curYear;
					}
					else
					{
						f1= curYear;
						f2 = curYear+1;
					}
					currYear = ((Integer)f1).toString()+"-"+((Integer)f2).toString();
					//logger.debug("YEAR IN SHOW FINAL"+currYear);
					formDTO.setFinancialYear(currYear);
					
					//logger.debug("Status"+formDTO.getGuideStatus());
					formDTO.setGuideType("F");
					//logger.debug("APP TYPE"+formDTO.getGuideType());
					
					//if(formDTO.getGuideStatus() == 3 && formDTO.getGuideType().equalsIgnoreCase("F"))
					//{
					//	dataType = "final";
					//}
					//else
					//{
						dataType = "final";
					//}
					versionList =  viewBD.viewVersionsTemp(formDTO, dataType);
					//logger.debug("SIZE"+versionList.size());
					int size = (versionList == null?0:versionList.size());
					if(size == 0)
					{
						logger.debug("No DATA FOUND");
						request.setAttribute(CommonConstant.SUCCESS_MSG,"No guideline data available");
					}
					else
					{
						Iterator itr = versionList.iterator();
						while(itr.hasNext())
						{
							GuidelineDTO gDTO = (GuidelineDTO)itr.next();
							guideLineId = gDTO.getGuideLineID();
							dFrom = gDTO.getFromDepositeDate();
							dTo = gDTO.getToDepositeDate();
							
							
						}
						formDTO.setGuideLineID(guideLineId);
						eForm.setGuideLineID(guideLineId);
						//logger.debug(guideLineId);
						formDTO.setFromDepositeDate(dFrom);
						formDTO.setToDepositeDate(dTo);
						//String guideID=eForm.getGuideLineID().split("~")[0];
						//logger.debug("guide path"+guideID);
						/*if(formDTO.getPraroopTypeId().equals("P1"))
						{
							logger.debug("*****************new Method in action*****");
							LinkedHashMap guidelineData = viewBD.getGuidelineDataPraroop1(formDTO,eForm,formDTO.getLanguage());
							eForm.setGuidelineDataMapPraroop1(guidelineData);
						}
						else if(formDTO.getPraroopTypeId().equals("P2"))
						{
							LinkedHashMap guidelineData = viewBD.getGuidelineDataPraroop2(formDTO,eForm,formDTO.getLanguage());
							eForm.setGuidelineDataMapPraroop2(guidelineData);
						}
						else if(formDTO.getPraroopTypeId().equals("P3"))
						{
							LinkedHashMap guidelineData = viewBD.getGuidelineDataPraroop3(formDTO,eForm,formDTO.getLanguage());
							eForm.setGuidelineDataMapPraroop3(guidelineData);
						}
						else
						{
							LinkedHashMap guidelineData = viewBD.getGuidelineDataPraroop(formDTO,eForm,formDTO.getLanguage());
							eForm.setGuidelineDataMapPraroop3(guidelineData);
						}
						//logger.debug("SIZE OF MAP 1"+eForm.getGuidelineDataMapPraroop1().size());
						//logger.debug("SIZE OF MAP 2"+eForm.getGuidelineDataMapPraroop2().size());
						//logger.debug("SIZE OF MAP 3"+eForm.getGuidelineDataMapPraroop3().size());
						
					}
					formDTO.setPage(CommonConstant.VIEW_FINAL);
					forwardJsp = CommonConstant.GUIDELINE_VIEW_PRAROOP;*/
					}	
					String projPath=getServlet().getServletContext().getRealPath("");
					logger.debug("pdf action");
					DMSUtility dms=new DMSUtility();
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
			 		String folNameSplit[]=uploadDir.split("-");
					String fol="";
					int k=0;
					for(int i=0;i<folNameSplit.length;i++){
						k=i+1;
						if(folNameSplit[i]!=null || folNameSplit[i]!=""){
						fol=fol.concat(File.separator).concat(folNameSplit[i]);
						}
					}
					String filePath="";
					String guideID=eForm.getGuideLineID().split("~")[0];
					if(guideID!=null){
					filePath=fol.concat(File.separator+guideID);
					}
					if(language.equalsIgnoreCase("en")){
						filePath=filePath.concat(File.separator+"ENGLISH");
						
					}else{
						filePath=filePath.concat(File.separator+"HINDI");
						
					}
					if(formDTO.getPraroopTypeId().equals("P1"))
					{
						byte b[]=dms.getDocumentBytes(filePath.concat(File.separator+CommonConstant.PDF_NAME_PRAROOP1_EXT));
						dms.downloadDocument(response, filePath.concat(File.separator+CommonConstant.PDF_NAME_PRAROOP1_EXT), b);
						//eForm = viewBD.printToPdfPraroop1(eForm, request, response,language);
					}
					else if(formDTO.getPraroopTypeId().equals("P2"))
					{
						byte b[]=dms.getDocumentBytes(filePath.concat(File.separator+CommonConstant.PDF_NAME_PRAROOP2_EXT));
						dms.downloadDocument(response, filePath.concat(File.separator+CommonConstant.PDF_NAME_PRAROOP2_EXT), b);
						//eForm = viewBD.printToPdfPraroop2(eForm, request, response,language,projPath);
					}
					else if(formDTO.getPraroopTypeId().equals("P3"))
					{
						byte b[]=dms.getDocumentBytes(filePath.concat(File.separator+CommonConstant.PDF_NAME_PRAROOP3_EXT));
						dms.downloadDocument(response, filePath.concat(File.separator+CommonConstant.PDF_NAME_PRAROOP3_EXT), b);
						//eForm = viewBD.printToPdfPraroop3(eForm, request, response,language,projPath);
					}
					else
					{
						byte b[]=dms.getDocumentBytes(filePath.concat(File.separator+CommonConstant.PDF_NAME_PRAROOPALL_EXT));
						dms.downloadDocument(response, filePath.concat(File.separator+CommonConstant.PDF_NAME_PRAROOPALL_EXT), b);
						//eForm = viewBD.printToPdf(eForm, request, response,language,projPath);
					}
					
				}
				if(CommonConstant.PDF_GENERATION_ACTION.equalsIgnoreCase(actionName))
				{
					String projPath=getServlet().getServletContext().getRealPath("");
					logger.debug("pdf action");
					DMSUtility dms=new DMSUtility();
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
			 		String folNameSplit[]=uploadDir.split("-");
					String fol="";
					int k=0;
					for(int i=0;i<folNameSplit.length;i++){
						k=i+1;
						if(folNameSplit[i]!=null || folNameSplit[i]!=""){
						fol=fol.concat(File.separator).concat(folNameSplit[i]);
						}
					}
					String filePath="";
					if(eForm.getGuideDTO().getGuideLineID()!=null){
					filePath=fol.concat(File.separator+eForm.getGuideDTO().getGuideLineID());
					}
					if(formDTO.getPraroopTypeId().equals("P1"))
					{
						byte b[]=dms.getDocumentBytes(filePath.concat(File.separator+CommonConstant.PDF_NAME_PRAROOP1_EXT));
						dms.downloadDocument(response, filePath.concat(File.separator+CommonConstant.PDF_NAME_PRAROOP1_EXT), b);
						//eForm = viewBD.printToPdfPraroop1(eForm, request, response,language);
					}
					else if(formDTO.getPraroopTypeId().equals("P2"))
					{
						byte b[]=dms.getDocumentBytes(filePath.concat(File.separator+CommonConstant.PDF_NAME_PRAROOP2_EXT));
						dms.downloadDocument(response, filePath.concat(File.separator+CommonConstant.PDF_NAME_PRAROOP2_EXT), b);
						//eForm = viewBD.printToPdfPraroop2(eForm, request, response,language,projPath);
					}
					else if(formDTO.getPraroopTypeId().equals("P3"))
					{
						byte b[]=dms.getDocumentBytes(filePath.concat(File.separator+CommonConstant.PDF_NAME_PRAROOP3_EXT));
						dms.downloadDocument(response, filePath.concat(File.separator+CommonConstant.PDF_NAME_PRAROOP3_EXT), b);
						//eForm = viewBD.printToPdfPraroop3(eForm, request, response,language,projPath);
					}
					else
					{
						byte b[]=dms.getDocumentBytes(filePath.concat(File.separator+CommonConstant.PDF_NAME_PRAROOPALL_EXT));
						dms.downloadDocument(response, filePath.concat(File.separator+CommonConstant.PDF_NAME_PRAROOPALL_EXT), b);
						//eForm = viewBD.printToPdf(eForm, request, response,language,projPath);
					}
					
					forwardJsp = CommonConstant.GUIDELINE_VIEW_PRAROOP;
				}
				
				
			//////////////////////////////////////////////////////////////////////////////////////////////	
					
				if("previousPageClick".equalsIgnoreCase(actionName))
				{
					long guidelineID;
					
					String fromDate = null;
					String toDate = null;
					int derivedFrom = 0;
					
					GuidelinePreparationBD	bd = new GuidelinePreparationBD();

			
					String guideID=eForm.getGuideLineID().split("~")[0];
				
				ArrayList tehsilList = new ArrayList();
				
				String distId = formDTO.getDistrictID();
				logger.debug("@@@@@@@@@@@@@"+distId);
				
				
				/*String version = request.getParameter("guideLineID");
				
				String guideDuration[]= version.split("~");
				if(guideDuration.length == 3)
				{
					guideID= guideDuration[0];
					fromDate = guideDuration[1];
					toDate = guideDuration[2];
					//derivedFrom = Integer.parseInt(guideDuration[3]);
				}*/
				
				formDTO.setGuideLineID(guideID);
				//formDTO.setFromDepositeDate(fromDate);
				//formDTO.setToDepositeDate(toDate);
				logger.debug("@@@@@@@@@@"+guideID);
				//logger.debug("@@@@@@@@@@"+fromDate);
				//logger.debug("@@@@@@@@@@"+toDate);
				
				
				
				/*
				System.out.println(formDTO.getFinancialYear());
				String Financial[] = formDTO.getFinancialYear().split("-");
				String finanDistt = Financial[0].concat(Financial[1]).concat(distId);
				System.out.println(finanDistt);
				System.out.println("GuidelineID"+guidelineID);*/
				
				tehsilList = bd.getTehsilListMaker(distId,Long.parseLong(guideID), formDTO);
				tehsilList = bd.getTehsilListViewall(distId,Long.parseLong(guideID), formDTO);
				eForm.setTehsilList(tehsilList);
			
				
				
				

				
				
				
				
						//eForm.setTehsilList(new ArrayList());
						eForm.setAreaTypeList(new ArrayList());
						eForm.setWardList(new ArrayList());
						eForm.setMohallaList(new ArrayList());
						eForm.setPatwariList(new ArrayList());
						eForm.setVillageList(new ArrayList());
						eForm.setFinancialYearList(new ArrayList());
						eForm.setMohallaAllDetails(new ArrayList());
						eForm.setSubAreaList(new ArrayList());
						formDTO.setSubAreaList(new ArrayList());
						formDTO.setTehsil("");
						formDTO.setTehsilID("");
						/*formDTO.setFinancialYear("");
						formDTO.setFromDepositeDate("");
						formDTO.setToDepositeDate("");

						formDTO.setBasePeriodFrom("");
						formDTO.setBasePeriodTo("");
						formDTO.setAreaTypeID("");
						formDTO.setWardPatwari("");
						formDTO.setMohalla("");
						formDTO.setWard("");
						formDTO.setPatwari("");*/
						eForm.setGuideDTO(formDTO);
						session.removeAttribute(CommonConstant.MOHALLA_LIST);
						session.removeAttribute(CommonConstant.PATWARI_LIST);

						forwardJsp = CommonConstant.VIEW_PROP_DETLS;
						logger.debug("Page is forwarded to :-    "+forwardJsp);
					
				}
				
				if("versionClick".equalsIgnoreCase(actionName))
					{
						
						
						
						long guidelineID;
						
						String fromDate = null;
						String toDate = null;
						int derivedFrom = 0;
						
						GuidelinePreparationBD	bd = new GuidelinePreparationBD();

				
						String guideID=eForm.getGuideLineID().split("~")[0];
					
					ArrayList tehsilList = new ArrayList();
					
					String distId = formDTO.getDistrictID();
					logger.debug("@@@@@@@@@@@@@"+distId);
					
					
					String version = request.getParameter("guideLineID");
					
					String guideDuration[]= version.split("~");
					if(guideDuration.length == 3)
					{
						guideID= guideDuration[0];
						fromDate = guideDuration[1];
						toDate = guideDuration[2];
						//derivedFrom = Integer.parseInt(guideDuration[3]);
					}
					
					formDTO.setGuideLineID(guideID);
					formDTO.setFromDepositeDate(fromDate);
					formDTO.setToDepositeDate(toDate);
					logger.debug("@@@@@@@@@@"+guideID);
					logger.debug("@@@@@@@@@@"+fromDate);
					logger.debug("@@@@@@@@@@"+toDate);
					
					
					
					/*
					System.out.println(formDTO.getFinancialYear());
					String Financial[] = formDTO.getFinancialYear().split("-");
					String finanDistt = Financial[0].concat(Financial[1]).concat(distId);
					System.out.println(finanDistt);
					System.out.println("GuidelineID"+guidelineID);*/
					
					//tehsilList = bd.getTehsilListMaker(distId,Long.parseLong(guideID), formDTO);
					tehsilList = bd.getTehsilListViewall(distId,Long.parseLong(guideID), formDTO);
					eForm.setTehsilList(tehsilList);
				
					
					
					

					
					
					
					
							//eForm.setTehsilList(new ArrayList());
							eForm.setAreaTypeList(new ArrayList());
							eForm.setWardList(new ArrayList());
							eForm.setMohallaList(new ArrayList());
							eForm.setPatwariList(new ArrayList());
							eForm.setVillageList(new ArrayList());
							eForm.setFinancialYearList(new ArrayList());
							eForm.setMohallaAllDetails(new ArrayList());
							/*formDTO.setFinancialYear("");
							formDTO.setFromDepositeDate("");
							formDTO.setToDepositeDate("");

							formDTO.setBasePeriodFrom("");
							formDTO.setBasePeriodTo("");
							formDTO.setAreaTypeID("");
							formDTO.setWardPatwari("");
							formDTO.setMohalla("");
							formDTO.setWard("");
							formDTO.setPatwari("");*/
							eForm.setGuideDTO(formDTO);
							session.removeAttribute(CommonConstant.MOHALLA_LIST);
							session.removeAttribute(CommonConstant.PATWARI_LIST);

							forwardJsp = CommonConstant.VIEW_PROP_DETLS;
							logger.debug("Page is forwarded to :-    "+forwardJsp);
					

					
						
						
						
				
						
						
						
						
						
						
						
						
						
						
						/*
						//String projPath=getServlet().getServletContext().getRealPath("");
						logger.debug("pdf action");
						DMSUtility dms=new DMSUtility();
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
				 		String folNameSplit[]=uploadDir.split("-");
						String fol="";
						int k=0;
						for(int i=0;i<folNameSplit.length;i++){
							k=i+1;
							if(folNameSplit[i]!=null || folNameSplit[i]!=""){
							fol=fol.concat(File.separator).concat(folNameSplit[i]);
							}
						}
						String filePath="";
						String guideID=eForm.getGuideLineID().split("~")[0];
						if(guideID!=null){
						filePath=fol.concat(File.separator+guideID);
						}
						if(language.equalsIgnoreCase("en")){
							filePath=filePath.concat(File.separator+"ENGLISH");
							
						}else{
							filePath=filePath.concat(File.separator+"HINDI");
							
						}
						if(formDTO.getPraroopTypeId().equals("P1"))
						{
							byte b[]=dms.getDocumentBytes(filePath.concat(File.separator+CommonConstant.PDF_NAME_PRAROOP1_EXT));
							dms.downloadDocument(response, filePath.concat(File.separator+CommonConstant.PDF_NAME_PRAROOP1_EXT), b);
							//eForm = viewBD.printToPdfPraroop1(eForm, request, response,language);
						}
						else if(formDTO.getPraroopTypeId().equals("P2"))
						{
							byte b[]=dms.getDocumentBytes(filePath.concat(File.separator+CommonConstant.PDF_NAME_PRAROOP2_EXT));
							dms.downloadDocument(response, filePath.concat(File.separator+CommonConstant.PDF_NAME_PRAROOP2_EXT), b);
							//eForm = viewBD.printToPdfPraroop2(eForm, request, response,language,projPath);
						}
						else if(formDTO.getPraroopTypeId().equals("P3"))
						{
							byte b[]=dms.getDocumentBytes(filePath.concat(File.separator+CommonConstant.PDF_NAME_PRAROOP3_EXT));
							dms.downloadDocument(response, filePath.concat(File.separator+CommonConstant.PDF_NAME_PRAROOP3_EXT), b);
							//eForm = viewBD.printToPdfPraroop3(eForm, request, response,language,projPath);
						}
						else
						{
							byte b[]=dms.getDocumentBytes(filePath.concat(File.separator+CommonConstant.PDF_NAME_PRAROOPALL_EXT));
							dms.downloadDocument(response, filePath.concat(File.separator+CommonConstant.PDF_NAME_PRAROOPALL_EXT), b);
							//eForm = viewBD.printToPdf(eForm, request, response,language,projPath);
						}
						
					//	forwardJsp = CommonConstant.GUIDELINE_VIEW_PRAROOP;
					
						
						
						//logger.debug("Praroop type"+formDTO.getPraroopTypeId());
						eForm.setGuidelineDataMapPraroop1(new LinkedHashMap());
						eForm.setGuidelineDataMapPraroop2(new LinkedHashMap());
						eForm.setGuidelineDataMapPraroop3(new LinkedHashMap());
						if(request.getParameter("guideLineID") != null)
						{
							logger.debug("IN VERSION CLICK");
							String guide  = null;
							String dFrom = null;
							String dTo = null;
							String guideDuration = request.getParameter("guideLineID");
							String guideArr[] = guideDuration.split("~");
							if(guideArr.length == 3)
							{
								guide = guideArr[0];
								dFrom = guideArr[1];
								dTo = guideArr[2];
								
							}
							
							formDTO.setGuideLineID(guide);
							//logger.debug(guide);
							formDTO.setFromDepositeDate(dFrom);
							formDTO.setToDepositeDate(dTo);
							
							//ArrayList tehsilList = viewBD.getTehsilList(formDTO.getDistrictID());
							//eForm.setTehsilList(tehsilList);
							
							//ArrayList areaTypes = viewBD.getAreasTypeList();
							//eForm.setAreaTypeList(areaTypes);
							
							if(formDTO.getPraroopTypeId().equals("P1"))
							{
								LinkedHashMap guidelineData = viewBD.getGuidelineDataPraroop1(formDTO,eForm,formDTO.getLanguage());
								eForm.setGuidelineDataMapPraroop1(guidelineData);
							}
							else if(formDTO.getPraroopTypeId().equals("P2"))
							{
								LinkedHashMap guidelineData = viewBD.getGuidelineDataPraroop2(formDTO,eForm,formDTO.getLanguage());
								eForm.setGuidelineDataMapPraroop2(guidelineData);
							}
							else if(formDTO.getPraroopTypeId().equals("P3"))
							{
								LinkedHashMap guidelineData = viewBD.getGuidelineDataPraroop3(formDTO,eForm,formDTO.getLanguage());
								eForm.setGuidelineDataMapPraroop3(guidelineData);
							}
							else
							{
								LinkedHashMap guidelineData = viewBD.getGuidelineDataPraroop(formDTO,eForm,formDTO.getLanguage());
								eForm.setGuidelineDataMapPraroop3(guidelineData);
							}
							
							//logger.debug("SIZE OF MAP 1"+eForm.getGuidelineDataMapPraroop1().size());
							//logger.debug("SIZE OF MAP 2"+eForm.getGuidelineDataMapPraroop2().size());
							//logger.debug("SIZE OF MAP 3"+eForm.getGuidelineDataMapPraroop3().size());
							formDTO.setCheckDraft("");
							//logger.debug("<------"+formDTO.getCheckDraft());
							//Added by Lavi
							if (eForm.getGuideDTO().getGuideStatus()==2)
							{
								logger.debug("DRAFT");
								formDTO.setCheckDraft("DRAFT");
								
							}
							//logger.debug("<------"+formDTO.getCheckDraft());
							forwardJsp = CommonConstant.GUIDELINE_VIEW_PRAROOP;
							
						}
					*/}
					
					
					
					
					if("showguidelineRates".equalsIgnoreCase(actionName))
					{
						long guidelineID;
						
						String fromDate = null;
						String toDate = null;
						int derivedFrom = 0;
						
						GuidelinePreparationBD	bd = new GuidelinePreparationBD();
						//GuidelineApprovalBD bd1 = new GuidelineApprovalBD();
				
						String guideID=eForm.getGuideLineID().split("~")[0];
				
					
					String distId = formDTO.getDistrictID();
					logger.debug("@@@@@@@@@@@@@"+distId);
					
					
					//String version = request.getParameter("guideLineID");
					 
					/*String guideDuration[]= version.split("~");
					if(guideDuration.length == 3)
					{
						guideID= guideDuration[0];
						fromDate = guideDuration[1];
						toDate = guideDuration[2];
						//derivedFrom = Integer.parseInt(guideDuration[3]);
					}
					*/
					formDTO.setGuideLineID(guideID);
					//formDTO.setFromDepositeDate(fromDate);
					//formDTO.setToDepositeDate(toDate);
					logger.debug("@@@@@@@@@@"+guideID);
					//logger.debug("@@@@@@@@@@"+fromDate);
					//logger.debug("@@@@@@@@@@"+toDate);
					
					
					//formDTO=bd.getFinancialDuration(formDTO,guideID);
					
					
					ArrayList guidelineRates = new ArrayList();
					guidelineRates = bd.getguidelineratesList(formDTO,guideID);
					eForm.setMohallaAllDetails(guidelineRates);
					
					
					
					
					//ArrayList individualMohallaAttributes = bd1.guideLineViewCheckerComplete(Long.parseLong(guideID), formDTO.getMoh(), language);
					
					
					/*ArrayList individualMohallaAttributes2 = bd.getIndividualMohallaDetails2(formDTO);
					eForm.setMohallaAllDetails(individualMohallaAttributes2);
					*/
					
					/*formDTO.setFinancialYear("");
					formDTO.setFromDepositeDate("");
					formDTO.setToDepositeDate("");

					formDTO.setBasePeriodFrom("");
					formDTO.setBasePeriodTo("");
					formDTO.setAreaTypeID("");
					formDTO.setWardPatwari("");
					formDTO.setMohalla("");
					formDTO.setWard("");
					formDTO.setPatwari("");*/
					formDTO.setActionName("");
					forwardJsp = CommonConstant.VIEW_GUIDELINE_DETLS;
					logger.debug("Page is forwarded to :-    "+forwardJsp);
			

					}
					
					
					
					
					
					
					
					
					
					if("areatypelist".equalsIgnoreCase(actionName))
					{
						long guidelineID;
						
						String fromDate = null;
						String toDate = null;
						int derivedFrom = 0;
						
						GuidelinePreparationBD	bd = new GuidelinePreparationBD();

				
						String guideID=eForm.getGuideLineID().split("~")[0];
				
					
					String distId = formDTO.getDistrictID();
					logger.debug("@@@@@@@@@@@@@"+distId);
					
					
					//String version = request.getParameter("guideLineID");
					 
					/*String guideDuration[]= version.split("~");
					if(guideDuration.length == 3)
					{
						guideID= guideDuration[0];
						fromDate = guideDuration[1];
						toDate = guideDuration[2];
						//derivedFrom = Integer.parseInt(guideDuration[3]);
					}
					*/
					formDTO.setGuideLineID(guideID);
					//formDTO.setFromDepositeDate(fromDate);
					//formDTO.setToDepositeDate(toDate);
					logger.debug("@@@@@@@@@@"+guideID);
					//logger.debug("@@@@@@@@@@"+fromDate);
					//logger.debug("@@@@@@@@@@"+toDate);
					formDTO.setAreaFlag("1");
					ArrayList areaTypes = new ArrayList();
					areaTypes = bd.getAreasTypeList(formDTO);
					eForm.setAreaTypeList(areaTypes);
					
					eForm.setSubAreaList(new ArrayList());
					
					/*formDTO.setFinancialYear("");
					formDTO.setFromDepositeDate("");
					formDTO.setToDepositeDate("");

					formDTO.setBasePeriodFrom("");
					formDTO.setBasePeriodTo("");
					formDTO.setAreaTypeID("");
					formDTO.setWardPatwari("");
					formDTO.setMohalla("");
					formDTO.setWard("");
					formDTO.setPatwari("");*/
					formDTO.setActionName("");
					forwardJsp = CommonConstant.VIEW_PROP_DETLS;
					logger.debug("Page is forwarded to :-    "+forwardJsp);
			

					}
					
					
					if("mohallavillagelist".equalsIgnoreCase(actionName))
					{
						long guidelineID;
						
						String fromDate = null;
						String toDate = null;
						int derivedFrom = 0;
						
						GuidelinePreparationBD	bd = new GuidelinePreparationBD();

				
						String guideID=eForm.getGuideLineID().split("~")[0];
				
					
					String distId = formDTO.getDistrictID();
					logger.debug("@@@@@@@@@@@@@"+distId);
					
					
					//String version = request.getParameter("guideLineID");
					 
					/*String guideDuration[]= version.split("~");
					if(guideDuration.length == 3)
					{
						guideID= guideDuration[0];
						fromDate = guideDuration[1];
						toDate = guideDuration[2];
						//derivedFrom = Integer.parseInt(guideDuration[3]);
					}
					*/
					formDTO.setGuideLineID(guideID);
					//formDTO.setFromDepositeDate(fromDate);
					//formDTO.setToDepositeDate(toDate);
					logger.debug("@@@@@@@@@@"+guideID);
					//logger.debug("@@@@@@@@@@"+fromDate);
					//logger.debug("@@@@@@@@@@"+toDate);
					
					ArrayList mohallalist = new ArrayList();
					mohallalist = bd.getMohallaListViewall(formDTO,guideID);
					eForm.setMohallaList(mohallalist);
					
					
					/*formDTO.setFinancialYear("");
					formDTO.setFromDepositeDate("");
					formDTO.setToDepositeDate("");

					formDTO.setBasePeriodFrom("");
					formDTO.setBasePeriodTo("");
					formDTO.setAreaTypeID("");
					formDTO.setWardPatwari("");
					formDTO.setMohalla("");
					formDTO.setWard("");
					formDTO.setPatwari("");*/
					formDTO.setActionName("");
					forwardJsp = CommonConstant.VIEW_PROP_DETLS;
					logger.debug("Page is forwarded to :-    "+forwardJsp);
			

					}
					
					
					
			
					
					if("wardpatwarilist".equalsIgnoreCase(actionName))
					{
						long guidelineID;
						
						String fromDate = null;
						String toDate = null;
						int derivedFrom = 0;
						
						GuidelinePreparationBD	bd = new GuidelinePreparationBD();

				
						String guideID=eForm.getGuideLineID().split("~")[0];
					
				
					
					String distId = formDTO.getDistrictID();
					logger.debug("@@@@@@@@@@@@@"+distId);
					
					
					//String version = request.getParameter("guideLineID");
					 
					/*String guideDuration[]= version.split("~");
					if(guideDuration.length == 3)
					{
						guideID= guideDuration[0];
						fromDate = guideDuration[1];
						toDate = guideDuration[2];
						//derivedFrom = Integer.parseInt(guideDuration[3]);
					}
					*/
					formDTO.setGuideLineID(guideID);
					//formDTO.setFromDepositeDate(fromDate);
					//formDTO.setToDepositeDate(toDate);
					logger.debug("@@@@@@@@@@"+guideID);
					//logger.debug("@@@@@@@@@@"+fromDate);
					//logger.debug("@@@@@@@@@@"+toDate);
					
					ArrayList wardList = new ArrayList();
					wardList = bd.getWardListViewall(formDTO,guideID);
					eForm.setWardList(wardList);
					eForm.setMohallaList(new ArrayList());
					formDTO.setActionName("");
					forwardJsp = CommonConstant.VIEW_PROP_DETLS;
					logger.debug("Page is forwarded to :-    "+forwardJsp);
			

					}
					
					
			
					
					if("subarealist".equalsIgnoreCase(actionName))
					{
						long guidelineID;
						
						String fromDate = null;
						String toDate = null;
						int derivedFrom = 0;
						
						GuidelinePreparationBD	bd = new GuidelinePreparationBD();

				
						String guideID=eForm.getGuideLineID().split("~")[0];
					
					
					
					String distId = formDTO.getDistrictID();
					logger.debug("@@@@@@@@@@@@@"+distId);
					
					
					//String version = request.getParameter("guideLineID");
					 
					/*String guideDuration[]= version.split("~");
					if(guideDuration.length == 3)
					{
						guideID= guideDuration[0];
						fromDate = guideDuration[1];
						toDate = guideDuration[2];
						//derivedFrom = Integer.parseInt(guideDuration[3]);
					}
					*/
					formDTO.setGuideLineID(guideID);
					//formDTO.setFromDepositeDate(fromDate);
					//formDTO.setToDepositeDate(toDate);
					logger.debug("@@@@@@@@@@"+guideID);
					//logger.debug("@@@@@@@@@@"+fromDate);
					//logger.debug("@@@@@@@@@@"+toDate);
					
						ArrayList subareaList = new ArrayList();
						eForm.getGuideDTO().setSubAreaList(null);
					subareaList = bd.getSubAreaListViewall(formDTO,guideID);
					
					formDTO.setSubAreaId("");
					formDTO.setSubAreaID("");
					formDTO.setSubAreaName("");
					
					formDTO.setSubAreaList(null);
					formDTO.setSubAreaList(bd.getSubAreaListViewall(formDTO,guideID));
					eForm.getGuideDTO().setSubAreaID("");
					eForm.getGuideDTO().setSubAreaID("");
					eForm.getGuideDTO().setSubAreaName("");
					/*formDTO.setFinancialYear("");
					formDTO.setFromDepositeDate("");
					formDTO.setToDepositeDate("");

					formDTO.setBasePeriodFrom("");
					formDTO.setBasePeriodTo("");
					//formDTO.setAreaTypeID("");
					formDTO.setWardPatwari("");
					formDTO.setMohalla("");
					formDTO.setWard("");
					formDTO.setPatwari("");
					formDTO.setSubAreaID("");
					;*/
					//eForm.setGuideDTO(formDTO);
					session.removeAttribute(CommonConstant.MOHALLA_LIST);
					session.removeAttribute(CommonConstant.PATWARI_LIST);
					eForm.setSubAreaList(subareaList);
					formDTO.setActionName("");
					formDTO.setSubAreaList(subareaList);
					eForm.setGuideDTO(formDTO);
				
					forwardJsp = CommonConstant.VIEW_PROP_DETLS;
					logger.debug("Page is forwarded to :-    "+forwardJsp);
			

					}
					
					
					
					if("tehsilAction".equalsIgnoreCase(actionName))
					{
						System.out.println("Inside Tehsil");
						formDTO.setWardID("");
						formDTO.setPatwariID("");
						System.out.println(formDTO.getTehsil());
						String tehID = formDTO.getTehsilID();
						System.out.println(formDTO.getTehsilID());
						//System.out.println(formDTO.getGuideLineID());
						long guideID = Long.parseLong(formDTO.getGuideLineID());
						
						ArrayList wardList = viewBD.getWardList(tehID);
						ArrayList patwariList = viewBD.getPatwariList(tehID);
						eForm.setWardList(wardList);
						eForm.setPatwariList(patwariList)
						;

						session.removeAttribute(CommonConstant.MOHALLA_LIST);
						session.removeAttribute(CommonConstant.PATWARI_LIST);
						forwardJsp = CommonConstant.VIEW2;
					}
					

					if("dropDownWardPatwariAction".equalsIgnoreCase(actionName)){

						eForm.getGuideDTO().setWardPatwari(eForm.getGuideDTO().getWardPatwari());
						formDTO.setWardID("");
						formDTO.setPatwariID("");
						request.removeAttribute("mohallId");
						formDTO.setMohallaID("");
						formDTO.setMohalla("");
						//logger.debug("<-----AREA TYPE------->"+eForm.getGuideDTO().getAreaTypeID());
						//logger.debug("AREA TYPE NAME----->"+eForm.getGuideDTO().getAreaName());
						
						if(eForm.getGuideDTO().getWardPatwari().equalsIgnoreCase("P")){
							session.removeAttribute(CommonConstant.MOHALLA_LIST);
							eForm.getGuideDTO().setWard("");
						}
						if(eForm.getGuideDTO().getWardPatwari().equalsIgnoreCase("W")){
							session.removeAttribute(CommonConstant.PATWARI_LIST);
							eForm.getGuideDTO().setPatwari("");
						}
						forwardJsp = CommonConstant.VIEW2;

					}
					
					if("wardAction".equalsIgnoreCase(actionName))
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
						
						
						

						if(wardAry.length == 2)
						{
							ArrayList mohallalist = viewBD.getMohViewList(wardAry[0],formDTO.getLanguage());
							formDTO.setMohList(mohallalist);
							eForm.setGuideDTO(formDTO);
							session.setAttribute(CommonConstant.MOHALLA_LIST, eForm);
							session.removeAttribute("patwarilist");
						}
						
						forwardJsp = CommonConstant.VIEW2;
					}
				
				
				/*if("guideLinePrepAction".equals(formDTO.getActionName()))
				{
					String disID = formDTO.getDistrict();

					if (disID != null ) {
						String[] districtAry=formDTO.getDistrict().split("~");
						if(districtAry.length == 2 ) {
							ArrayList tehsilList = viewBD.getTehsilList(districtAry[0]);
							eForm.setTehsilList(tehsilList);
							forwardJsp = CommonConstant.VIEW_GUIDELINE_CITIZEN; 
							
						}
					}
				}

				
				if("tehsilAction".equalsIgnoreCase(formDTO.getActionName()))
				{
					String[] thesilAry=formDTO.getTehsil().split("~");
					if(thesilAry.length == 2)
					{	
						System.out.println(thesilAry[0]);
						ArrayList wardList = viewBD.getWardList(thesilAry[0]);
						ArrayList patwariList = viewBD.getPatwariList(thesilAry[0]);
						eForm.setWardList(wardList);
						eForm.setPatwariList(patwariList);
						forwardJsp = CommonConstant.VIEW_GUIDELINE_CITIZEN; 
					}
				}
				
				if("dropDownWardPatwariAction".equalsIgnoreCase(formDTO.getActionName())){

					eForm.getGuideDTO().setWardPatwari(eForm.getGuideDTO().getWardPatwari());

					if(eForm.getGuideDTO().getWardPatwari().equalsIgnoreCase("P")){
						session.removeAttribute(CommonConstant.MOHALLA_LIST);
						eForm.getGuideDTO().setWard("");
					}
					if(eForm.getGuideDTO().getWardPatwari().equalsIgnoreCase("W")){
						session.removeAttribute(CommonConstant.PATWARI_LIST);
						eForm.getGuideDTO().setPatwari("");
						forwardJsp = CommonConstant.VIEW_GUIDELINE_CITIZEN; 
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
					String fyear= formDTO.getFinancialYear();
					
					String durationFrom=formDTO.getDurationFrom();
					

					session.setAttribute(CommonConstant.DTO,formDTO);

					if(wardAry.length == 2)
					{
						ArrayList mohallalist = viewBD.getMohViewList(wardAry[0]);
						formDTO.setMohList(mohallalist);
						eForm.setGuideDTO(formDTO);
						session.setAttribute(CommonConstant.MOHALLA_LIST, eForm);
						session.removeAttribute("patwarilist");
					}
					forwardJsp = CommonConstant.VIEW_GUIDELINE_CITIZEN;
				}
				if("dropDownMohalla".equalsIgnoreCase(formDTO.getActionName())){
					
					eForm.getGuideDTO().setMoh(eForm.getGuideDTO().getMoh());
					System.out.println(eForm.getGuideDTO().getMoh());
					forwardJsp = CommonConstant.VIEW_GUIDELINE_CITIZEN; 
				}
*/
				
				if("resetPageAction".equalsIgnoreCase(actionName)){

					formDTO.setFinancialYear("");
					formDTO.setDistrict("");
					formDTO.setDistrictID("");
					formDTO.setGuidelineStatus("");
					formDTO.setGuideStatus(0);
					formDTO.setGuideType("");
					eForm.setVersionList(new ArrayList());
					formDTO.setPraroopTypeId("");
					eForm.setGuidelineDataMapPraroop1(new LinkedHashMap());
					eForm.setGuidelineDataMapPraroop2(new LinkedHashMap());
					eForm.setGuidelineDataMapPraroop3(new LinkedHashMap());
					//eForm.setFinancialYearList(new ArrayList());
					//eForm.setDistrictList(new ArrayList());
					
					
					forwardJsp = CommonConstant.VIEW1;
					
					//eForm.setGuideDTO(null);
				}
				
				if("resetPageActionDraft".equalsIgnoreCase(actionName)){

					formDTO.setFinancialYear("");
					formDTO.setDistrict("");
					formDTO.setDistrictID("");
					formDTO.setGuidelineStatus("");
					formDTO.setGuideStatus(0);
					formDTO.setGuideType("");
					eForm.setVersionList(new ArrayList());
					formDTO.setPraroopTypeId("");
					eForm.setGuidelineDataMapPraroop1(new LinkedHashMap());
					eForm.setGuidelineDataMapPraroop2(new LinkedHashMap());
					eForm.setGuidelineDataMapPraroop3(new LinkedHashMap());
					//eForm.setFinancialYearList(new ArrayList());
					//eForm.setDistrictList(new ArrayList());
					
					
					forwardJsp = CommonConstant.VIEW_DRAFT;
					
					//eForm.setGuideDTO(null);
				}
				if("resetPageActionFinal".equalsIgnoreCase(actionName)){

					formDTO.setFinancialYear("");
					formDTO.setDistrict("");
					formDTO.setDistrictID("");
					formDTO.setGuidelineStatus("");
					formDTO.setGuideStatus(0);
					formDTO.setGuideType("");
					eForm.setVersionList(new ArrayList());
					formDTO.setPraroopTypeId("");
					eForm.setGuidelineDataMapPraroop1(new LinkedHashMap());
					eForm.setGuidelineDataMapPraroop2(new LinkedHashMap());
					eForm.setGuidelineDataMapPraroop3(new LinkedHashMap());
					//eForm.setFinancialYearList(new ArrayList());
					//eForm.setDistrictList(new ArrayList());
					
					
					forwardJsp = CommonConstant.VIEW_FINAL;
					
					//eForm.setGuideDTO(null);
				}
				
				if("previousAction".equalsIgnoreCase(actionName))
				{
					/*GuidelineDTO dtoForm = (GuidelineDTO)session.getAttribute(CommonConstant.DTO);
					GuidelineDTO dtoFrom = new  GuidelineDTO();
					String fromDate = dtoForm.getFromDepositeDate();
					String toDate = dtoForm.getToDepositeDate();
					String baseFrom = dtoForm.getBasePeriodFrom();
					String baseTo   = dtoForm.getBasePeriodTo();
					String areaType = dtoForm.getAreaName();*/
					
					GuidelineDTO dtoForm = eForm.getGuideDTO();
					dtoForm.setDistrict(dtoForm.getDistrict());
					dtoForm.setFinancialYear(dtoForm.getFinancialYear());
					dtoForm.setGuideStatus(dtoForm.getGuideStatus());
					dtoForm.setGuideType(dtoForm.getGuideType());
					
					eForm.getGuideDTO().setTehsil("");
					eForm.getGuideDTO().setWardPatwari("");
					eForm.getGuideDTO().setWard("");
					eForm.getGuideDTO().setPatwari("");
					eForm.getGuideDTO().setMohalla("");
					eForm.getGuideDTO().setTehsilID("");
					eForm.getGuideDTO().setWardID("");
					eForm.getGuideDTO().setPatwariID("");
					eForm.getGuideDTO().setAreaTypeID("");
					request.removeAttribute("mohallId");
					eForm.getGuideDTO().setMoh("");
					eForm.setVersionList(eForm.getVersionList());
					request.setAttribute("versionList", eForm.getVersionList());
					eForm.setGuideDTO(dtoForm);
					forwardJsp = CommonConstant.VIEW1;
				}
				
				

				/*
				 *  GuideLine View Part.
				 *  Shows individual mohalla details pop up
				 *  bd.getIndividualMohallaDraftView(mohallaAry[0], finYear):- Mohalla ID and Financial Year.
				 */
				if("mohallaAction".equalsIgnoreCase((String)request.getParameter("actionID")))    
				{
					logger.debug("<-------inside view Action---s->");
					GuidelineDTO formDTO1 = eForm.getGuideDTO();
					String tehsil = formDTO.getTehsil();
					String[] wardAry = formDTO.getWard().split("~");
					String[] patAry = formDTO.getPatwari().split("~");
					String[] areaAry = eForm.getGuideDTO().getAreaTypeID().split("~");
					//String[] districtAry = formDTO.getDistrict().split("~");
					
					//int guideStatus = Integer.parseInt(formDTO.getGuidelineStatus());
					
					//logger.debug("<----GuidelineStatus--->"+guideStatus);
					
					
					//if(thesilAry != null && thesilAry.length==2) {
					//	formDTO.setTehsilID(thesilAry[0]);
						//formDTO.setTehsil(thesilAry[1]);
					//}

					String[]  mohallaAry = request.getParameter("mohallId").split("=");

					formDTO.setFinancialYear(formDTO.getFinancialYear());
					//formDTO.setAreaName(formDTO.getAreaName());

					if(wardAry!=null & wardAry.length==2) {
						formDTO.setWardID(wardAry[0]);
						formDTO.setWard(wardAry[1]);
					}

					if(patAry!=null & patAry.length==2) {
						formDTO.setPatwariID(patAry[0]);
						formDTO.setPatwari(patAry[1]);
					}
					
					if(areaAry != null & areaAry.length == 2){
						formDTO.setAreaTypeID(areaAry[0]);
						formDTO.setAreaName(areaAry[1]);
					}
					/*if(districtAry !=null && districtAry.length==2) {
						formDTO.setDistrictID(districtAry[0]);																						
						formDTO.setDistrict(districtAry[1]); 
					}*/

					if(mohallaAry !=null && mohallaAry.length==2) {
						formDTO.setMohallaID(mohallaAry[0]);																						
						formDTO.setMohalla(mohallaAry[1]); 
					}

					String finYear = formDTO.getFinancialYear();
					//logger.debug(tehsil);
					//logger.debug(formDTO.getDistrictID());
					//logger.debug(formDTO.getTehsilID());
					//logger.debug(formDTO.getWardID());
					//logger.debug(formDTO.getPatwariID());
					//logger.debug(formDTO.getMohallaID());
					//logger.debug("Status"+formDTO.getGuideStatus());
					//logger.debug("APP TYPE"+formDTO.getGuideType());
					
					
					ArrayList individualMohallaAttributes = viewBD.getIndividualMohallaViewNew(formDTO);
					
					int size = (individualMohallaAttributes == null?0:individualMohallaAttributes.size());
					if(size == 0)
					{
						logger.debug("No DATA FOUND");
						request.setAttribute(CommonConstant.SUCCESS_MSG,"No guideline data available");
						
					}
					else
					{
						eForm.setMohallaAllDetails(individualMohallaAttributes);

						
					}
					forwardJsp = CommonConstant.MOHALLA_DETAILS_VIEW_MAKER_SUCCESS;
				/*
				 * Added by Simran to view According to status	
				 */
					//Fetches details - Property TYpe, L1, L2 and GuideLine Value.
					
						
						/* if(guideStatus ==  CommonConstant.GUIDELINESTATUS_DRAFT){
							
							logger.debug("Status is Draft");
							ArrayList individualMohallaAttributes = viewBD.getIndividualMohallaDraftView(mohallaAry[0], formDTO.getDistrictID(),finYear, guideStatus);
							eForm.setMohallaAllDetails(individualMohallaAttributes);

							forwardJsp = CommonConstant.MOHALLA_DETAILS_VIEW_MAKER_SUCCESS;
							//session.removeAttribute("mohallalist");
							//session.removeAttribute("patwarilist");
						 }
							
							else if(guideStatus == CommonConstant.GUIDELINESTATUS_FINAL){
								
								logger.debug("status is FINAL;");
								ArrayList individualMohallaAttributes = viewBD.getIndividualMohallaFinalView(mohallaAry[0],formDTO.getDistrictID());
								eForm.setMohallaAllDetails(individualMohallaAttributes);

								forwardJsp = CommonConstant.MOHALLA_DETAILS_VIEW_MAKER_SUCCESS;
								//session.removeAttribute("mohallalist");
								//session.removeAttribute("patwarilist");
							
							}
							
					*/	}
				
				if("subClauseClickReadOnly".equalsIgnoreCase(request.getParameter("actionName")))
				{
					
					String propID = null;
					String propL1 = null;
					String propL2 = null;
					String index = null;
					String value = null;
					ArrayList subClauseList = null;
					String propDetails = null;
				
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
					
					subClauseList = viewBD.subClauseReadOnly(formDTO);
					
				
					//logger.debug("SIZE of SUB CLAUSE LIST"+subClauseList.size());
					eForm.setSubClauseList(subClauseList);
					request.setAttribute("subClauseList",subClauseList);
					
					
					forwardJsp = CommonConstant.SUB_CLAUSE_READ_ONLY;
					
				}
			
			
				if ("saveEmailContent".equalsIgnoreCase(actionName))
				{
					String emailContent = eForm.getGuideDTO().getMailContent();
					//logger.debug("---------->"+emailContent);
					String status=null;
					
					if (emailContent == "")
					{
						//logger.debug("----->"+emailContent);
						forwardJsp = CommonConstant.VIEW1;
					}
					else
					{
						status = viewBD.getEmailContent(formDTO);
					}
					
					if (status!= null)
					{
						
						//messages.add("SUCCESS_MSG_2", new ActionMessage(
						//"conf_feedback"));
						//saveMessages(request, messages);
						messages.add("SUCCESS_MSG_2", new ActionMessage(
						"conf_feedback"));
						saveMessages(request, messages);
						request.setAttribute(CommonConstant.SUCCESS_MSG_2,"Your feedback has been sent successfully");
						//forwardJsp = CommonConstant.VIEW1;
						forwardJsp = CommonConstant.PUBLISH_MAIN;
						
					}
					eForm.getGuideDTO().setMailContent("");
					messages.add("SUCCESS_MSG_2", new ActionMessage(
					"conf_feedback"));
					saveMessages(request, messages);
					request.setAttribute(CommonConstant.SUCCESS_MSG_2,"Your feedback has been sent successfully");
					forwardJsp = CommonConstant.PUBLISH_MAIN;
				}
					
					
					
				}
			}

	
		return mapping.findForward(forwardJsp);

}}



