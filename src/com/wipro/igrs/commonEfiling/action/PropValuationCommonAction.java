package com.wipro.igrs.commonEfiling.action;


import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.commonEfiling.bo.CommonBO;
import com.wipro.igrs.commonEfiling.dto.CommonDTO;
import com.wipro.igrs.commonEfiling.form.CommonForm;


public class PropValuationCommonAction extends BaseAction {
	private static Logger logger = org.apache.log4j.Logger.getLogger(PropValuationCommonAction.class);
	 String FORWARD_JSP = "";
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		
		CommonForm commonForm=(CommonForm)form;
		CommonBO commonBO=new CommonBO();
		CommonDTO commonDTO=commonForm.getCommonDTO();
		String language = (String)session.getAttribute("languageLocale");
		String type=request.getParameter("type");
		String id=request.getParameter("valID");
		//String subId=request.getParameter("subvalID");
		// added by vinay Sharma
		String districtID=request.getParameter("districtID");
		
		commonDTO.setSubClauseChkd("");
		String userId = (String) session.getAttribute("UserId");
		
		String propTypeId;
		if(id.startsWith("a"))
		{
			propTypeId="2";
		}
		else
		{
			propTypeId=commonBO.getpropTypeId(language,id);
		}
		
		if("1".equalsIgnoreCase(propTypeId))
		{
			logger.debug("PropValuationCommonAction :: if(1.equalsIgnoreCase(propTypeId)) ");
			CommonDTO cdto=commonBO.getplotPropertyDetails(language,id);
			commonForm.setCommonDTO(cdto.getCmDTO());
			
			commonForm.getCommonDTO().setSubClauseList(commonBO.getallSubClauseList(language,id,commonDTO));
			
			
			if(commonBO.getallSubClauseList(language,id,commonDTO).size()!=0 && commonBO.getallSubClauseList(language,id,commonDTO)!=null)
			{
				logger.debug("PropValuationCommonAction :: case 1");
				commonForm.getCommonDTO().setSubClauseChkd("Y");
				FORWARD_JSP="plotView";
			}
			
			else
			{
				logger.debug("PropValuationCommonAction :: case 2");
				commonForm.getCommonDTO().setSubClauseChkd("N");
				FORWARD_JSP="plotView";
			}
			
			FORWARD_JSP="plotView";
		}
		
		
		else if("3".equalsIgnoreCase(propTypeId))
		{
			
			commonForm.getCommonDTO().setAgriAreaDetailsList(commonBO.getagriAreaDetailsList(language,id,commonForm));
			
			commonForm.getCommonDTO().setAgriPropDetailsList(commonBO.getagriPropDetailsList(language,id,commonDTO));
			
			commonForm.getCommonDTO().setSubClauseList(commonBO.getallSubClauseList(language,id,commonDTO));
			
			commonForm.getCommonDTO().setAgriTreeDetailsList(commonBO.getAgriTreeDetailsList(language,id,commonDTO));
		
			if(commonBO.getAgriTreeDetailsList(language,id,commonDTO).size()!=0 && commonBO.getAgriTreeDetailsList(language,id,commonDTO)!=null)
			{
				
				commonForm.getCommonDTO().setAgriTreeChkd("Y");
				
			}
			
			else
			{
				commonForm.getCommonDTO().setAgriTreeChkd("N");
				
			}
			
			
			if(commonBO.getallSubClauseList(language,id,commonDTO).size()!=0 && commonBO.getallSubClauseList(language,id,commonDTO)!=null)
			{
				
				commonForm.getCommonDTO().setSubClauseChkd("Y");
				
			}
			
			else
			{
				commonForm.getCommonDTO().setSubClauseChkd("N");
				
			}
			
			FORWARD_JSP="agriView";
		
		}
		else if("2".equalsIgnoreCase(propTypeId))
		{
			CommonDTO cdto=commonBO.getbuildingView(id, language);
			commonForm.setCommonDTO(cdto);


			

			ArrayList subclaueList;
			if(id.startsWith("a"))
			{
				subclaueList=commonBO.getallSubClauseListBuildAgri(language,id.substring(1),commonDTO);
	
			}
			else
			{
				subclaueList=commonBO.getallSubClauseList(language,id,commonDTO);

			}

			if(subclaueList!=null && subclaueList.size()!=0)

			{
				
				commonForm.getCommonDTO().setSubClauseChkd("Y");
				commonForm.getCommonDTO().setSubClauseList(subclaueList);
			}
			
			else
			{
				commonForm.getCommonDTO().setSubClauseChkd("N");
				
			}
			FORWARD_JSP="buildingView";	
			
		}
		
		return mapping.findForward(FORWARD_JSP);
	}
	
	
	
}
	