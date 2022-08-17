package com.wipro.igrs.faqonlinequery.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.octo.captcha.service.CaptchaServiceException;
import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.faqonlinequery.bd.FaqOnlineQueryBD;
import com.wipro.igrs.faqonlinequery.dto.FaqOnlineQueryDTO;
import com.wipro.igrs.faqonlinequery.form.FaqOnlineQueryForm;

public class FaqOnlineQueryAction extends Action {
	  private static Logger log = org.apache.log4j.Logger.getLogger(FaqOnlineQueryAction.class);
	  
	    String FORWARD_JSP = "";
	    FaqOnlineQueryBD faqBD= new FaqOnlineQueryBD();
	    
	    
	    @Override
	    public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
	    	HttpSession Session = request.getSession(true);
	    	// POC for XSS 
			ActionForward forward = null;
			if(IGRSCommon.checkXSSData(form)){
				forward = new ActionForward("/jsp/FailureXSS.jsp");
				request.setAttribute("xssData", IGRSCommon.xssFieldName);
				log.debug("Invalid XSS Data Token.. login..");
				Session.invalidate();
				return forward;
			}
	    	
	    	
	    	Session.setAttribute("langModule", "faqOnline");
	    	FaqOnlineQueryForm faqForm = (FaqOnlineQueryForm) form;
	    	FaqOnlineQueryDTO faqDTO=faqForm.getFaqDTO();
	    	String pageName=request.getParameter("pageName");
	    	String frwdName=request.getParameter("fwdName");
	    	String queryId=request.getParameter("queryTxnid");
	    	String userId = (String)Session.getAttribute("UserId");
	    	String language = (String)Session.getAttribute("languageLocale");
	    	faqDTO.setLanguage(language);
	    	faqDTO.setUserID(userId);
		if("FAQDETAILS".equalsIgnoreCase(pageName))
		{
			ArrayList <FaqOnlineQueryDTO> faqList=faqBD.getFAQDetails(language);
			request.setAttribute("faqList", faqList);
			Session.setAttribute("langModule", "faqOnline");
			FORWARD_JSP="FAQDETAILS";
		}
		else if(queryId!=null)
		{
			queryId=queryId.trim();
			faqDTO=faqBD.replyDetails(queryId,language);
			faqDTO.setQueryTxnid(queryId);
			faqForm.setFaqDTO(faqDTO);
			FORWARD_JSP="REPLYPAGE";
		}
		else if("submitReply".equalsIgnoreCase(faqForm.getActionName()))
		{
			if(faqBD.updateQueryDetails(faqDTO))
			{
				FORWARD_JSP="REPLYSUCCESS";
				faqForm.setActionName("");
			}
		}
		else if("REPLYSEARCH".equalsIgnoreCase(pageName))
		{
			ArrayList <FaqOnlineQueryDTO> dashList=faqBD.replyDashBoard(language);
			request.setAttribute("dashList", dashList);
			faqDTO.setToDate("");
			faqDTO.setQueryTxnid("");
			faqDTO.setFromDate("");
			faqDTO.setStatus("");
			faqForm.setActionName("");
			faqForm.setFaqDTO(faqDTO);
			FORWARD_JSP="REPLYSEARCHPAGE";
		}
		else if("REPLYSEARCHDASHBOARD".equalsIgnoreCase(pageName))
		{
			ArrayList <FaqOnlineQueryDTO> dashList=faqBD.searchDashBoard(faqDTO,language);
			request.setAttribute("dashListSearch", dashList);
			FORWARD_JSP="REPLYSEARCHDASHBOARD";
		}
		else if("searchSubmit".equalsIgnoreCase(faqForm.getActionName()))
		{
			ArrayList <FaqOnlineQueryDTO> dashList=faqBD.searchDashBoard(faqDTO,language);
			request.setAttribute("dashListSearch", dashList);
			faqForm.setActionName("");
			FORWARD_JSP="REPLYSEARCHDASHBOARD";
			
		}
		else if("ONLINEQUERY".equalsIgnoreCase(pageName))
		{
			faqDTO.setCountryList(null);
			faqDTO.setAddress("");
			faqDTO.setName("");
			faqDTO.setEmail("");
			faqDTO.setAddress("");
			faqDTO.setContactNumber("");
			faqDTO.setCountryName("");
			faqDTO.setCountryId("");
			faqDTO.setMobileNumber("");
			faqDTO.setPlace("");
			faqDTO.setDistrictList(null);
			faqDTO.setStateList(null);
			faqDTO.setDistrictId("");
			faqDTO.setDistrictName("");
			faqDTO.setStateId("");
			faqDTO.setStateName("");
			faqDTO.setQuery("");
			faqDTO.setCountryList(faqBD.countryBD(language));
			faqForm.setFaqDTO(faqDTO);
			faqForm.setErroMessage("");
			Session.setAttribute("langModule", "faqOnlineQuery");
			FORWARD_JSP="ONLINEQUERY";
		}
		 else if(frwdName!=null&&(frwdName.equalsIgnoreCase("State")))
	 	 {
			 ArrayList state=new ArrayList();
			 String countryId=request.getParameter("countryId");		 
			 faqDTO.setStateList(faqBD.stateBD(faqDTO.getCountryId(),language)); 		
			 faqDTO.setDistrictList(null);
			 Session.setAttribute("langModule", "faqOnlineQuery");
	 		 FORWARD_JSP = new String("ONLINEQUERY"); 		
	 	 }
		 else if(frwdName!=null&&(frwdName.equalsIgnoreCase("District")))
		 	{
			 	 ArrayList district=new ArrayList();
				 String sateId=request.getParameter("countryId");			
				 faqDTO.setDistrictList(faqBD.districtBD(faqDTO.getStateId(),language));
				 Session.setAttribute("langModule", "faqOnlineQuery");
				FORWARD_JSP = new String("ONLINEQUERY");	 		
		 	}
		else if("submit".equalsIgnoreCase(faqForm.getActionName()))
		{
			String id =faqBD.submitQueryDetails(faqDTO);
			 Boolean isResponseCorrect =Boolean.FALSE;
	           String captchaId = (String) request.getSession().getAttribute("QARFAD");
	           String responses = request.getParameter("j_captcha_response");
	           try {
	               isResponseCorrect = IGRSCommon.validateCaptcha(captchaId,responses); 
	           } catch (CaptchaServiceException e) {
	                e.printStackTrace();
	           }
	           if(isResponseCorrect)
	           {
			if(!id.equalsIgnoreCase(""))
			{
				faqDTO.setQueryTxnid(id);
				faqForm.setFaqDTO(faqDTO);
				request.setAttribute("name", faqDTO.getName());
				request.setAttribute("emailid", faqDTO.getEmail());
				request.setAttribute("refno", id);
				faqForm.setActionName("");
				FORWARD_JSP="SUBMITQUERYSUCCESS";
			}
	           }
	           else
	           {
	        	   if(language.equals("en"))
	        		   faqForm.setErroMessage("Invalid Captcha is entered. Please Enter the Captcha Again");
		        	   else
		        		   faqForm.setErroMessage("अवैध कैप्चा दर्ज किया गया है। फिर से कैप्चा दर्ज करें");   
	        	   FORWARD_JSP="ONLINEQUERY";
	           }
		}
		return mapping.findForward(FORWARD_JSP);
	}

}
