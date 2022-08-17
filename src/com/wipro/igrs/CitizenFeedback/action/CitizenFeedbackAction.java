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
 * FILE NAME: CitizenFeedbackAction.java
 * @author SATBIR KUMAR
 * @version 1.0 
 * INITIAL CODING: 23th June 2008
 * @DESCRIPTION:   THIS CLASS REPRESENTS THE ACTION FOR CITIZEN FEEDBACK ACTION.
 */

package com.wipro.igrs.CitizenFeedback.action;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.octo.captcha.service.CaptchaServiceException;
import com.wipro.igrs.CitizenFeedback.bd.CitizenFeebackBD;
import com.wipro.igrs.CitizenFeedback.constant.CitizenFeedbackCommonConstant;
import com.wipro.igrs.CitizenFeedback.dto.CitizenFeedbackDTO;
import com.wipro.igrs.CitizenFeedback.form.CitizenFeedbackForm;
import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.common.PropertiesFileReader;

/**
 * @author NIHAR M.
 * 
 */
public class CitizenFeedbackAction extends Action {

	private Logger logger = (Logger) Logger
			.getLogger(CitizenFeedbackAction.class);
	String forwardJsp = new String("createCitizenFeedback");
	
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String page = request.getParameter("page");
		System.out.println(page);
		HttpSession session = request.getSession(true);
		session.setAttribute("langModule", "feedback");
		String language=(String)session.getAttribute("languageLocale");
		
		// POC for XSS 
		ActionForward forward = null;
		if(IGRSCommon.checkXSSData(form)){
			forward = new ActionForward("/jsp/FailureXSS.jsp");
			request.setAttribute("xssData", IGRSCommon.xssFieldName);
			logger.debug("Invalid XSS Data Token.. login..");
			session.invalidate();
			return forward;
		}
		

		HttpSession requestSession = request.getSession(true);
		requestSession.setAttribute("langModule", "feedback");
		CitizenFeedbackForm eForm = (CitizenFeedbackForm) form;

		if (page != null) {
			if ("createCitizenFeedback".equals(page)) {
				logger.debug("page:-  " + page);
				eForm.setActionName("");
				eForm.getCitizenDTO().setOption("");
				eForm.getCitizenDTO().setEmailID("");
				eForm.getCitizenDTO().setFirstName("");
				eForm.getCitizenDTO().setFeedback("");
				eForm.setFeedbacks(null);
				eForm.setWillDistrict(null);
				eForm.getCitizenDTO().setCityDistrictId("");
				eForm.getCitizenDTO().setFeedbackFunctionID("");
				eForm.setErroMessage("");
				saveToken(request);
				ArrayList errorList = new ArrayList();
				CitizenFeebackBD citizenBD = new CitizenFeebackBD();
				
				//ArrayList listCountry = citizenBD.getCountry();
				
				
				//ArrayList listState = citizenBD
						//.getState(CitizenFeedbackCommonConstant.INDIA);
				
				
				
				ArrayList listDistrict = citizenBD.getDistrict(language);
				
				
				//ArrayList listProof = citizenBD
						//.getIDProof(CitizenFeedbackCommonConstant.IGRS_ID_TYPE);
				
				ArrayList listID = citizenBD.getFeedBackIDs(language);

				
				
				//eForm.setProofs(listProof);
				//eForm.setWillCountry(listCountry);
				//eForm.setWillState(listState);
				eForm.setWillDistrict(listDistrict);
				eForm.setFeedbacks(listID);

				forwardJsp = CitizenFeedbackCommonConstant.CREATE_CITIZEN_FEEDBACK;
				logger.debug("Page is forwarded to :-    " + forwardJsp);
				return mapping.findForward(forwardJsp);
			}
		}

		if (form != null) {
			logger.debug("form != null");
			
			
			String formName = eForm.getFormName();
			logger.debug("formName:-  " + formName);

			if (CitizenFeedbackCommonConstant.CITIZEN_FEEDBACK_FORM
					.equalsIgnoreCase(formName)) {
				logger.debug("Form Values are matched for:-   "
						+ CitizenFeedbackCommonConstant.CITIZEN_FEEDBACK_FORM);
				if ("SUBMIT_ACTION".equalsIgnoreCase(eForm
						.getActionName())) {
					CitizenFeedbackDTO objDTO = eForm.getCitizenDTO();
					logger.debug("Action matched for:-    "
							+ eForm.getActionName());
					if(isTokenValid(request))
					{
						resetToken(request);
					//boolean isError = validateForm(eForm, errorList, request);
					
					 Boolean isResponseCorrect =Boolean.FALSE;
			           String captchaId = (String) request.getSession().getAttribute("QARFAD");
			           String responses = request.getParameter("j_captcha_response");
			           try {
			               isResponseCorrect = IGRSCommon.validateCaptcha(captchaId,responses); 
			           } catch (CaptchaServiceException e) {
			                logger.debug("Failed to get Captcha",e);
			           }
			           
			           if(isResponseCorrect)
			           {
			        	   CitizenFeebackBD citizenBD = new CitizenFeebackBD();
					String refId="";                                              //---modified by Satbir Kumar---                                         
					refId = citizenBD.submitFeedbackDetails(objDTO,language);
					//request.setAttribute("a", eForm.getCitizenDTO().getFirstName());
					if (refId!="") {
						
						
						boolean mailsent = citizenBD.mailtouser(refId,objDTO);
						
						if(mailsent == true)
						{
							logger.debug("mail to user sent");
							
						}
						
						else
						{
							logger.debug("sending mail to user failed!!");
						}
						
						
						//ArrayList detail = new ArrayList();
						//detail=citizenBD.getDetails(refId);
						
						request.setAttribute("name", eForm.getCitizenDTO().getFirstName());
						request.setAttribute("emailid", eForm.getCitizenDTO().getEmailID());
						request.setAttribute("refno", refId);
						
						eForm.setActionName("");
						eForm.getCitizenDTO().setOption("");
						eForm.getCitizenDTO().setEmailID("");
						eForm.getCitizenDTO().setFirstName("");
						eForm.getCitizenDTO().setFeedback("");
						eForm.setFeedbacks(null);
						eForm.setWillDistrict(null);
						eForm.getCitizenDTO().setCityDistrictId("");
						eForm.getCitizenDTO().setFeedbackFunctionID("");
						eForm.setErroMessage("captcha is correct .");
						logger.debug("Inserted Successfully");
						forwardJsp = "feedbackGiven";
						eForm.setFormName(null);
				           }
				           else
				           {
				        	   if(language.equals("en"))
				        	   eForm.setErroMessage("Invalid Captcha is entered. Please Enter the Captcha Again");
				        	   else
				        	   eForm.setErroMessage("अवैध कैप्चा दर्ज किया गया है। फिर से कैप्चा दर्ज करें");   
				        	   forwardJsp = "createCitizenFeedback";    //message is not displayed in jsp
				        	   saveToken(request);
				           }
					} else {
						logger.debug("Failed!");
						 if(language.equals("en"))
				        	   eForm.setErroMessage("Invalid Captcha is entered. Please Enter the Captcha Again");
				        	   else
				        	   eForm.setErroMessage("अवैध कैप्चा दर्ज किया गया है। फिर से कैप्चा दर्ज करें");   
				        	   forwardJsp = "createCitizenFeedback";
								saveToken(request);

					}
			          // resetToken(request);         
			           eForm = null;
					}
					else
					{
						forwardJsp="createCitizenFeedback"; 
						
						logger.debug("Failed");
					}
				}
		}
		}
		
		return mapping.findForward(forwardJsp);
	}
	
}