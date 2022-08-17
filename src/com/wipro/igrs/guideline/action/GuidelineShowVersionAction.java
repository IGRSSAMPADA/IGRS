package com.wipro.igrs.guideline.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.guideline.bd.GuidelinePreparationBD;
import com.wipro.igrs.guideline.constant.CommonConstant;
import com.wipro.igrs.guideline.dto.GuidelineDTO;
import com.wipro.igrs.guideline.form.GuidelinePreparationForm;

public class GuidelineShowVersionAction extends BaseAction{
	String forwardJsp = new String(CommonConstant.MAKER2);
	private Logger logger = Logger.getLogger(GuidelineShowVersionAction.class);
	
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
		String guideID = null;
		String fromDate = null;
		String toDate = null;
		int derivedFrom = 0;
		
		GuidelinePreparationForm eForm;
		GuidelineDTO formDTO;
		GuidelinePreparationBD bd;
		logger.debug("<...."+page);
	//	HttpSession session = request.getSession();
		logger.debug("Inside Action.");
		 
	      String roleId = (String)session.getAttribute("role");
		  String funId = (String)session.getAttribute("functionId");
		  String userId = (String)session.getAttribute("UserId");
		  String language = (String)session.getAttribute("languageLocale");
		
		if (form != null) {
				eForm = (GuidelinePreparationForm) form;
				formDTO=eForm.getGuideDTO();
				formDTO.setLanguage(language);
				bd = new GuidelinePreparationBD();
			
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
			formDTO.setDerivedFrom(derivedFrom);
			logger.debug("@@@@@@@@@@"+guideID);
			logger.debug("@@@@@@@@@@"+fromDate);
			logger.debug("@@@@@@@@@@"+toDate);
			//System.out.println("@@@@@@@@@@"+derivedFrom);
			
			
			/*
			System.out.println(formDTO.getFinancialYear());
			String Financial[] = formDTO.getFinancialYear().split("-");
			String finanDistt = Financial[0].concat(Financial[1]).concat(distId);
			System.out.println(finanDistt);
			System.out.println("GuidelineID"+guidelineID);*/
			
			String srstatus = bd.getSRPublishStatus(formDTO);
			 
			if("3".equalsIgnoreCase(srstatus))
				
			{
				String printstatus = bd.getPrintStatus(formDTO);
				
				if("Y".equalsIgnoreCase(printstatus))
					
				{
					formDTO.setDownlaodChkId("Y");
					forwardJsp = CommonConstant.PUBLISH_MAIN;
				}
				
				else if("C".equalsIgnoreCase(printstatus))
				{
					formDTO.setDownlaodChkId("C");
					forwardJsp = CommonConstant.PUBLISH_MAIN;
				}
				else
				{
					formDTO.setDownlaodChkId("N");
					forwardJsp = CommonConstant.PUBLISH_MAIN;
				}
				
			}
			
			else
			{
			tehsilList = bd.getTehsilListMaker(distId,Long.parseLong(guideID), formDTO);
			eForm.setTehsilList(tehsilList);
			
			if(eForm.getTehsilList()==null ||eForm.getTehsilList().size()==0 )
		
			{
				formDTO.setDownlaodChkId("N");
				forwardJsp = CommonConstant.PUBLISH_MAIN;
			}
			
			else
			{
			formDTO.setAreaFlag("1");
			ArrayList areaTypes = bd.getAreasTypeList(formDTO);
			eForm.setAreaTypeList(areaTypes);
			forwardJsp =  CommonConstant.MAKER2;
			}

			}
			
			if(page!=null){
				if("create".equals(page)){
					eForm.setTehsilList(new ArrayList());
					eForm.setWardList(new ArrayList());
					eForm.setMohallaList(new ArrayList());
					eForm.setPatwariList(new ArrayList());
					eForm.setVillageList(new ArrayList());
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
					eForm.setGuideDTO(formDTO);
					session.removeAttribute(CommonConstant.MOHALLA_LIST);
					session.removeAttribute(CommonConstant.PATWARI_LIST);

					forwardJsp = CommonConstant.MAKER2;
				//	logger.debug("Page is forwarded to :-    "+forwardJsp);
				}
			}
			

			
				
				
				
		}	
	//	logger.debug("forwardJsp:-"+forwardJsp);
		return mapping.findForward(forwardJsp);
		}		
			
}
