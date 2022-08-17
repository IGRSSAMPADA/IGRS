package com.wipro.igrs.guideline.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;

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
import com.wipro.igrs.guideline.bd.GuidelineReportBD;
import com.wipro.igrs.guideline.constant.CommonConstant;
import com.wipro.igrs.guideline.dto.GuidelineDTO;
import com.wipro.igrs.guideline.form.GuidelinePreparationForm;

public class GuidelineReportAction extends BaseAction {
	
	String forwardJsp = "";
	private Logger  logger = Logger.getLogger(GuidelineReportAction.class);
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response,HttpSession session) 
			throws Exception {
		
		String page=request.getParameter("page");
		logger.debug("page"+page);
		ActionMessages messages = new ActionMessages();
		
		String roleId = (String)session.getAttribute("role");
		String funId = (String)session.getAttribute("functionId");
		String userId = (String)session.getAttribute("UserId");
		String actId = (String)request.getParameter("actId");
		String language = (String)session.getAttribute("languageLocale");
		Date createdDate = new Date();
		SimpleDateFormat sdfrmt = new SimpleDateFormat("dd/MM/yyyy");
		String cdate = sdfrmt.format(createdDate);
		
		if(form != null)
		{
			GuidelinePreparationForm eForm = (GuidelinePreparationForm)form;
			GuidelineDTO formDTO = eForm.getGuideDTO();
			GuidelineReportBD bd = new GuidelineReportBD();
			formDTO.setLanguage(language);
			if(page != null)
			{
				eForm.setFinancialYearList(bd.getFinancialYear());
				eForm.setDistrictList(bd.getDistrict(formDTO.getLanguage()));
				eForm.setGuideDTO(new GuidelineDTO());
				eForm.setAvailableDraftList(new ArrayList());
				eForm.setGuidelineDataMapPraroop1(new LinkedHashMap());
				eForm.setGuidelineDataMapPraroop2(new LinkedHashMap());
				eForm.setGuidelineDataMapPraroop3(new LinkedHashMap());
				//************for log*****************//
				IGRSCommon save=null;

                try {

                        save = new IGRSCommon();

                        save.saveactivitylog(userId, actId);

                } catch (Exception e) {

                       

                        e.printStackTrace();

                }

                //****************************//
				forwardJsp = CommonConstant.GUIDELINE_SELECTION;
			}
			String actionName = formDTO.getActionName();
			
			if(CommonConstant.SHOW_AVIALABLE_GUDELINES.equalsIgnoreCase(actionName))
			{
				logger.debug("SHOW AVAILABLE GUIDELINE");
				logger.debug("******financial Year*******"+formDTO.getFinancialYear());
				logger.debug("******Distt*******"+formDTO.getDistrictID());
				ArrayList avialableGuidelines = bd.getAvialableGuidelines(formDTO.getFinancialYear(), formDTO.getDistrictID());
				if(avialableGuidelines.size() == 0)
				{
					messages.add("ERRORMSG", new ActionMessage(
					"no.avialable.draft.gudeline"));
					saveMessages(request, messages);
					formDTO.setCheck("Y");
				}
				else
				{
					eForm.setAvailableDraftList(avialableGuidelines);
				}
				
				forwardJsp = CommonConstant.GUIDELINE_SELECTION;
			}
			
			if(CommonConstant.SHOW_FINAL_GUIDELINE.equalsIgnoreCase(actionName))
			{
				logger.debug("********FINAL GUIDELINE****");
				formDTO.setFinalGuidelineDetls(bd.getFinalGuidelineDetls(formDTO.getDistrictID(),formDTO));
			}
			
			if(CommonConstant.NEXT_TO_COMPARISON.equalsIgnoreCase(actionName))
			{
				logger.debug("NEXT TO COMPARISON");
				eForm.setGuidelineDataMapPraroop1(new LinkedHashMap());
				eForm.setGuidelineDataMapPraroop2(new LinkedHashMap());
				eForm.setGuidelineDataMapPraroop3(new LinkedHashMap());
				logger.debug("***********"+formDTO.getRadioValue());
				if(formDTO.getFinalGuidelineDetls().equals("-"))
				{
					messages.add("ERRORMSG", new ActionMessage(
					"no.prevalent.guideline.available"));
					saveMessages(request, messages);
					formDTO.setCheck("P");
					forwardJsp = CommonConstant.GUIDELINE_SELECTION;
				}
				else
				{
					if(formDTO.getPraroopTypeId().equals("P1"))
					{
						eForm.setGuidelineDataMapPraroop1(bd.getGuidelineDataPraroop1(formDTO, eForm,formDTO.getLanguage()));
					}
					else if(formDTO.getPraroopTypeId().equals("P2"))
					{
						eForm.setGuidelineDataMapPraroop2(bd.getGuidelineDataPraroop2(formDTO, eForm, formDTO.getLanguage()));
					}
					else if(formDTO.getPraroopTypeId().equals("P3"))
					{
						eForm.setGuidelineDataMapPraroop3(bd.getGuidelineDataPraroop3(formDTO, eForm,formDTO.getLanguage()));
					}
					forwardJsp = CommonConstant.GUIDELINE_COMPARISON;
				}
				
			}
			
			if(CommonConstant.CLOSE_ACTION.equalsIgnoreCase(actionName))
			{
				logger.debug("*****Close******");
				forwardJsp = CommonConstant.GUIDELINE_SELECTION;
			}
			if(CommonConstant.PRINT_ACTION.equalsIgnoreCase(actionName))
			{ 
				String projPath=getServlet().getServletContext().getRealPath("");
				logger.debug("*****PRINT TO PDF******");
				if(formDTO.getPraroopTypeId().equals("P1"))
				{
					eForm = bd.printToPdfPraroop1(eForm, request, response,language,projPath);
				}
				else if(formDTO.getPraroopTypeId().equals("P2"))
				{ 
					
					eForm = bd.printToPdfPraroop2(eForm, request, response,language,projPath);
				}
				else if(formDTO.getPraroopTypeId().equals("P3"))
				{
					eForm = bd.printToPdfPraroop3(eForm, request, response,language,projPath);
				}
				forwardJsp = CommonConstant.GUIDELINE_COMPARISON;
				
			}
			
			if(CommonConstant.RESET_ACTION.equalsIgnoreCase(actionName))
			{
				logger.debug("**********reset action**********");
				formDTO.setDistrictID("");
				formDTO.setFinancialYear("");
				formDTO.setPraroopTypeId("");
				eForm.setAvailableDraftList(new ArrayList());
				formDTO.setFinalGuidelineDetls("");
			}
			
		}
		return mapping.findForward(forwardJsp);
	}

}
