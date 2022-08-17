package com.wipro.igrs.guideline.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.UserRegistration.util.PropertiesFileReader;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.guideline.bd.GuidelineDraftToFinalBD;
import com.wipro.igrs.guideline.constant.CommonConstant;
import com.wipro.igrs.guideline.dto.GuidelineDTO;
import com.wipro.igrs.guideline.form.GuidelinePreparationForm;

public class GuidelineDraftToFinalAction extends BaseAction  {
	
	String forwardJsp = new String(CommonConstant.DRAFT_TO_FINAl1);
	private Logger logger = Logger.getLogger(GuidelineDraftToFinalAction.class);
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
		String districtName;
		long guideID = 0;
		String guidelineID = null ;
		String durationFrom = null;
		String durationTo = null;
		ArrayList versionList = new ArrayList();
	//	HttpSession session = request.getSession(true);
	      String roleId = (String)session.getAttribute("role");
		  String funId = (String)session.getAttribute("functionId");
		  String userId = (String)session.getAttribute("UserId");
		  //pr = PropertiesFileReader.getInstance("com.wipro.igrs.igrs");
		if(page!=null){
			if("approve".equals(page)){
				GuidelinePreparationForm eForm = (GuidelinePreparationForm) form;
				GuidelineDraftToFinalBD bd = new GuidelineDraftToFinalBD();
				ArrayList districtList = bd.getDistrictList();
				eForm.setDistrictList(districtList);
				
				ArrayList financialYearList =bd.getFinancialYearList();
				eForm.setFinancialYearList(financialYearList);

				//ArrayList areaTypes = bd.getAreasTypeList();
				//eForm.setAreaTypeList(areaTypes);
				
				forwardJsp = CommonConstant.DRAFT_TO_FINAl1;
				System.out.println("Page is forwarded to :-    "+forwardJsp);
			}
		}
		
		else{
		if (form != null) {
			GuidelinePreparationForm eForm = (GuidelinePreparationForm) form;
			GuidelineDraftToFinalBD bd = new GuidelineDraftToFinalBD();
			ArrayList districtList = bd.getDistrictList();
			eForm.setDistrictList(districtList);

			
			
			String formName = eForm
			.getGuideDTO().getGuidelineApprovalForm();

			GuidelineDTO formDTO=eForm.getGuideDTO();
			
			
			if(CommonConstant.DRAFT_FINAl_FORM.equalsIgnoreCase(formName))
			{
				logger.debug("actionName:-"+formDTO.getActionName());
				
				
				
				if("showVersion".equalsIgnoreCase(formDTO.getActionName()))
				{
					System.out.println("INSIDE SHOW ACTION");
					String finan = formDTO.getFinancialYear();
					String district = formDTO.getDistrictID();
					System.out.println("INSIDE SHOW VERSION IN DRAFT TO FINAL"+district+finan);
					versionList = bd.showDraftStatusChecker(finan, district);
					eForm.setVersionList(versionList);
					request.setAttribute("versionList", versionList);
					request.setAttribute("distt", district);
					request.setAttribute("finan", finan);
					
					
					
					
					forwardJsp = CommonConstant.DRAFT_TO_FINAl1;
					
				}
				
				if("versionNext".equalsIgnoreCase(formDTO.getActionName()))
				{
					String[] guideDuration = formDTO.getSelectedGuideLineID().toString().split("~");
					if(guideDuration.length == 3)
					{
						guidelineID = guideDuration[0];
						durationFrom = guideDuration[1];
						durationTo = guideDuration[2];
					}
					System.out.println("Duration From"+durationFrom);
					System.out.println("Duration From"+durationTo);
					formDTO.setGuideLineID(guidelineID);
					formDTO.setFromDepositeDate(durationFrom);
					formDTO.setToDepositeDate(durationTo);
					System.out.println("@@@@@@@@@@@IN VERSION NEXT"+guidelineID);
					guideID = Long.parseLong(guidelineID);
					String finan = formDTO.getFinancialYear();
					String district = formDTO.getDistrictID();
					versionList = bd.showDraftStatusChecker(finan, district);
					eForm.setVersionList(versionList);
					request.setAttribute("versionList", versionList);
					request.setAttribute("distt", district);
					request.setAttribute("finan", finan);
					forwardJsp = CommonConstant.DRAFT_TO_FINAl1;
				}
				
				if("finalAction".equalsIgnoreCase(formDTO.getActionName()))
				{
					
				}
			
			

		}
		}
		
	}
		return mapping.findForward(forwardJsp);
	}
}



