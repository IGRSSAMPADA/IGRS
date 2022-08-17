package com.wipro.igrs.guideline.action;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.guideline.bd.GuidelinePreparationBD;
import com.wipro.igrs.guideline.constant.CommonConstant;
import com.wipro.igrs.guideline.dto.GuidelineDTO;
import com.wipro.igrs.guideline.form.GuidelinePreparationForm;
import com.wipro.igrs.guideline.rule.GuideLineRule;

public class GuidelineDeriveDraftAction extends BaseAction{
	
	String forwardJsp = new String(CommonConstant.MAKER_DERIVE_DRAFT);
	private Logger logger = Logger.getLogger(GuidelineDeriveDraftAction.class);
	
	public ActionForward execute(
			ActionMapping mapping, 
			ActionForm form, 
			HttpServletRequest request,
			HttpServletResponse response,HttpSession session) 
	throws Exception {
		
		String page=request.getParameter("page");
		String districtName;
		long guidelineID;
		logger.debug("<....IN DERIVE DRAFT"+page);
	//	HttpSession session = request.getSession();
		logger.debug("Inside Action.");
		ActionMessages messages = new ActionMessages();
	      String roleId = (String)session.getAttribute("role");
		  String funId = (String)session.getAttribute("functionId");
		  String userId = (String)session.getAttribute("UserId");
		  ArrayList officeList = new ArrayList();
		  String actId = (String)request.getParameter("actId");
		  String officeId = (String)session.getAttribute("loggedToOffice");
		  String language = (String)session.getAttribute("languageLocale");
		  //ArrayList officeList = new ArrayList();
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
		/*  if(session.getAttribute("officeIdList")!= null)
		  {
			  logger.debug("<------notnull");
			  officeList = (ArrayList)session.getAttribute("officeIdList");
		  }*/
		  
		
		if (form != null) {
			GuidelinePreparationForm eForm = (GuidelinePreparationForm) form;
			GuidelinePreparationBD bd = new GuidelinePreparationBD();

			
			
			ArrayList financialYearList =bd.getFinancialYearList();
			eForm.setFinancialYearList(financialYearList);

			
			GuidelineDTO formDTO=eForm.getGuideDTO();
			formDTO.setLanguage(language);
			if(page!=null){
				if("derive".equals(page)){
					//ArrayList districtList = bd.getDistrictList(officeDetails,actId);
					ArrayList districtList = bd.getDistrict(officeId,formDTO);
					eForm.setDistrictList(districtList);
					
					eForm.setTehsilList(new ArrayList());
					eForm.setWardList(new ArrayList());
					eForm.setMohallaList(new ArrayList());
					eForm.setPatwariList(new ArrayList());
					eForm.setVillageList(new ArrayList());
					eForm.setVersionList(new ArrayList());
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
					forwardJsp = CommonConstant.MAKER_DERIVE_DRAFT;
					logger.debug("Page is forwarded to :-    "+forwardJsp);
				}
			}
			logger.debug("<--------------------"+formDTO.getActionName());
			
			if(CommonConstant.GUIDELINE_DERIVE.equalsIgnoreCase(eForm.getGuideDTO().getGuidelinePreparationForm()))
			{
				if("deriveGuideline".equalsIgnoreCase(formDTO.getActionName()))
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
					//ArrayList tehsilList = new ArrayList();
					
					//System.out.println(formDTO.getFinancialYear());
					String fYear = formDTO.getFinancialYear();
					String Financial[] = formDTO.getFinancialYear().split("-");
					String finanDistt = Financial[0].concat(Financial[1]).concat(distId);
					
					
					String fromDate = formDTO.getFromDepositeDate();
					String toDate = formDTO.getToDepositeDate();
				
					//String[] fYear1 = new String[] { fYear};
				
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
				

				eForm = guideRule.checkDurationDates(fromDate, toDate, fYear, start, end, eForm);

				if (guideRule.isError()) {
					logger.debug("is error");
					eForm.setGuideDTO(formDTO);

					request.setAttribute(CommonConstant.DURATION_DATE_INVALID, "true");
					//request.setAttribute(CommonConstant.DURATION_DATES_ERRORS, errorList);
					logger.debug("Action: Inside Error");
				}

				else{
					//boolean draftData = bd.draftData(distId);
					//if(draftData)
					//{
					guidelineID = bd.getGuidelineID(finanDistt, distId);
					
					//guidelineID = bd.getGuidelineID(finanDistt, distId);  TODO ##### check for colonies #####
					if(guidelineID == 0)
					{
						guidelineID = Long.parseLong(finanDistt.concat("100"));
					}
					else
					{
						guidelineID = guidelineID+1;
					}
					long versionNo = guidelineID;
					formDTO.setGuideID(guidelineID);
					formDTO.setDistrictID(distId);
					formDTO.setFromDepositeDate(fromDate);
					formDTO.setToDepositeDate(toDate);
					formDTO.setFinancialYear(fYear);
					formDTO.setLoggedOfficeId(officeId);
					//logger.debug("guideID"+guidelineID);
					boolean valueInserted = bd.insertAllCombinations(String.valueOf(guidelineID),distId);
					//String guideID = Long.toString(guidelineID);
					//String ver = Long.toString(versionNo);
					
					
					//logger.debug("GuidelineID from which data is to be copied"+formDTO.getGuideLineID());
					
						boolean dataCopied = bd.copyDraftData(formDTO, userId);
						logger.debug("IN ACTION LAST"+dataCopied);
					//request.setAttribute("guideID", guideID);
					//request.setAttribute("ver", ver);
					
					//tehsilList = bd.getTehsilListMaker(distId, guidelineID);
					//eForm.setTehsilList(tehsilList);
					
					//ArrayList areaTypes = bd.getAreasTypeList();
					//eForm.setAreaTypeList(areaTypes);
						
						
						if(dataCopied)
						{
							
							boolean valueDeleted = bd.deleteDeacCombinations(String.valueOf(guidelineID),distId);
							boolean newDataInserted = bd.insertNewCombinations(String.valueOf(guidelineID),distId);
							
							
							if(valueDeleted)
							{
								logger.debug("deactivated combinations deleted successfully...");
							}
							if(newDataInserted)
							{
								logger.debug("new combinations inserted successfully...");
							}
							
							
							formDTO.setGuideLineID(String.valueOf(guidelineID));
							messages.add("SUCCESS_MSG", new ActionMessage(
							"conf_msg_guideline_derive"));
							saveMessages(request, messages);
							formDTO.setConfirmationChk("2");
							formDTO.setDownlaodChkId("D");
							request.setAttribute(CommonConstant.SUCCESS_MSG,"Guideline successfully derived from DRAFT guideline");
							logger.debug("data copied successfully...");
							forwardJsp = CommonConstant.PUBLISH_MAIN;
						}
						else
						{
							messages.add("FAILURE_MSG", new ActionMessage(
							"conf_msg_guideline_derive_failed"));
							saveMessages(request, messages);
							formDTO.setConfirmationChk("2");
							formDTO.setDownlaodChkId("F");
							request.setAttribute(CommonConstant.FAILURE_MSG,"Guideline derivation failed");
							logger.debug("data not copied");
							forwardJsp = CommonConstant.PUBLISH_MAIN;
						}
						
					//}
					//else
					//{	
						//request.setAttribute(CommonConstant.SUCCESS_MSG,"Guideline Draft data not available to copy");
						//logger.debug("data not copied");
						//forwardJsp = CommonConstant.DERIVE_CONFIRMATION;
						
					//}
				}
				
				}
				if("resetPageAction".equalsIgnoreCase(formDTO.getActionName())){

					
					formDTO.setFinancialYear("");
					eForm.setErrorMsg("");
					formDTO.setDistrict("");
					formDTO.setDistrictID("");
					formDTO.setFromDepositeDate("");
					formDTO.setToDepositeDate("");
					eForm.setVersionList(new ArrayList());
					forwardJsp = CommonConstant.MAKER_DERIVE_DRAFT;
					
					//eForm.setGuideDTO(null);
				}
				
				if("showVersion".equalsIgnoreCase(formDTO.getActionName()))
				{
					eForm.setErrorMsg("");
					String finan = formDTO.getFinancialYear();
					String district = formDTO.getDistrictID();
					formDTO.setLoggedOfficeId(officeId);
					int derivedFrom = 2;
					String i = eForm.getGuideDTO().getGuideTypeChk();
					ArrayList versionList = bd.showVersionFinalMaker(finan, district, derivedFrom, language,formDTO);
					formDTO.setCheck("");
					formDTO.setPreviousPage("draft");
					//request.setAttribute("distt", district);
					//request.setAttribute("finan", finan);
					if(versionList.size() > 0)
					{
						eForm.setVersionList(versionList);
						request.setAttribute("versionList", versionList);
						
					}
					else
					{
						formDTO.setCheck("Y");
						messages.add("MSG", new ActionMessage(
						"no_data_found"));
						saveMessages(request, messages);
					}
					forwardJsp = CommonConstant.MAKER_DERIVE_DRAFT;
					
				}
				
				if("availableDraftVersions".equals(formDTO.getActionName()))
				{
					eForm.setErrorMsg("");
					formDTO.setLoggedOfficeId(officeId);
					ArrayList availableDraftList = bd.availableDraftVersions(formDTO);
					int size = (availableDraftList == null?0:availableDraftList.size());
					formDTO.setCheck("");
					if(size == 0)
					{
						logger.debug("NO DRAFT VERSION AVAILABLE");
						formDTO.setCheck("Y");
						messages.add("MSG", new ActionMessage(
						"no_data_found"));
						saveMessages(request, messages);
					}
					else
					{
						eForm.setAvailableDraftList(availableDraftList);
						request.setAttribute("draftVersions", availableDraftList);
						logger.debug("VERSIONS DRAFT");
						
					}
					forwardJsp = CommonConstant.MAKER_DERIVE_DRAFT;
					
				}
			}
			
	
	}
		logger.debug("forwardJsp:-"+forwardJsp);
		return mapping.findForward(forwardJsp);
	}

}
