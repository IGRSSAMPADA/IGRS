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
 * FILE NAME: CitizenFeedbackViewAction.java
 * @author NIHAR R MISHRA
 * @version 1.0 
 * INITIAL CODING: 23th June 2008
 * @DESCRIPTION:   THIS CLASS REPRESENTS THE ACTION FOR CITIZEN VIEW ACTION.
 */

package com.wipro.igrs.CitizenFeedback.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.CitizenFeedback.bd.CitizenFeebackViewBD;
import com.wipro.igrs.CitizenFeedback.constant.CitizenFeedbackCommonConstant;
import com.wipro.igrs.CitizenFeedback.dto.CitizenFeedbackDTO;
import com.wipro.igrs.CitizenFeedback.form.CitizenFeedbackForm;
import com.wipro.igrs.CitizenFeedback.form.FeedbackComplaintForm;
import com.wipro.igrs.CitizenFeedback.rule.CitizenFeedbackRULE;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.estamping.dto.DashBoardDTO;

/**
 * @author NIHAR M.
 * 
 */
public class CitizenFeedbackViewAction extends BaseAction {

	private Logger logger = (Logger) Logger
			.getLogger(CitizenFeedbackViewAction.class);
	String forwardJsp = new String("createCitizenFeedback");
	
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,HttpSession session)
			throws Exception {
		String language = (String)session.getAttribute("languageLocale");
		IGRSCommon save =  new IGRSCommon();
		String userId = (String) session.getAttribute("UserId");
		FeedbackComplaintForm eForm = (FeedbackComplaintForm) form;
		String page = request.getParameter("page");
		String activityid = request.getParameter("ACTID");
		
		if (page != null) {
			if ("ViewDetails".equals(page)) {
				save.saveactivitylog(userId, activityid);
				eForm.getCitizenDTO().setFromrequestdate("");
        		eForm.getCitizenDTO().setTorequestdate("");
        		eForm.getCitizenDTO().setSearchrefid("");
        		eForm.getCitizenDTO().setStatus("");
				logger.debug("page:-  " + page);
				forwardJsp = CitizenFeedbackCommonConstant.VIEW_CITIZEN_FEEDBACK;
				logger.debug("Page is forwarded to :-    " + forwardJsp);
				
			}
		}
		
		  //for creating dashboard
		//CitizenFeebackViewBD cbd = new CitizenFeebackViewBD();
        
		if (form != null) {
			logger.debug("form != null");
			
			eForm.setLanguage(language);
			//HttpSession session = request.getSession(true);
			CitizenFeebackViewBD citizenBD = new CitizenFeebackViewBD();

			String formName = eForm.getFormName();
			logger.debug("formName:-  " + formName);
			
			
			if (CitizenFeedbackCommonConstant.FEEDBACK_COMPLAINT_FORM
					.equalsIgnoreCase(formName)) {
				
				logger.debug("Form Values are matched for:-   "
						+ CitizenFeedbackCommonConstant.FEEDBACK_COMPLAINT_FORM);
				
				if ("SUBMIT_ACTION".equalsIgnoreCase(eForm
						.getActionName())) 
				{
					
					logger.debug("Action matched for:-   "
							+ eForm.getActionName());
					
					
					CitizenFeebackViewBD bd = new CitizenFeebackViewBD();
					
					boolean insert = bd.email(eForm.getCitizenDTO());
					
					eForm.getCitizenDTO().setComments("");
					
					if(insert == true){
						
						logger.debug("mail sent");
						
						
					
					System.out.println(eForm.getCitizenDTO().getReferenceId());
					String id = eForm.getCitizenDTO().getReferenceId();
					
					
					boolean update = bd.updatestatus(id,language);
					
					forwardJsp= "final";
					
					if(update==true)
					{
						logger.debug("updated");
					}
					else{
						logger.debug("not updated");
					}
					
					}
					else {
						logger.debug("Failed!");
					}
					
					
				
				}
				eForm.setFormName(null);
			
			}
			
			
			
			if(request.getParameter("page")!=null){
				if(request.getParameter("page").equalsIgnoreCase("ViewDetailsNext"))
				{
					request.setAttribute("pendingApplicationList", eForm.getPendingRegApplicationList());
					forwardJsp= "ViewDetails";
				}
				else if(request.getParameter("page").equalsIgnoreCase("ViewDetails")){
	        		
	        		String id= (String)session.getAttribute("loggedToOffice");
	        		logger.debug(id);
	        		CitizenFeebackViewBD cbd = new CitizenFeebackViewBD();
	        		ArrayList pendingApplicationList = cbd.getPendingEstampApps(id,userId, language);
	        		
	        		System.out.println("in action"+pendingApplicationList);
					/*if(pendingApplicationList.size()==0)
					{
					eform.setPendingRegApplicationList(null);
					}
					else*/
						eForm.setPendingRegApplicationList(pendingApplicationList);
						
						request.setAttribute("pendingApplicationList", eForm.getPendingRegApplicationList());
					forwardJsp= "ViewDetails";
					//return mapping.findForward(forwardJsp);
				}
			}
	        	if ("SEARCH".equalsIgnoreCase(eForm
						.getActionName())) 
				{
	        		String offid= (String)session.getAttribute("loggedToOffice");
	        		logger.debug(offid);
	        		
	        		logger.debug("Action matched for:-   "
							+ eForm.getActionName());
	        		
	        		String durationFrom = eForm.getCitizenDTO().getFromrequestdate();
	        		logger.debug(durationFrom);
	                String durationTo = eForm.getCitizenDTO().getTorequestdate();
	                logger.debug(durationTo);
	                String refId = eForm.getCitizenDTO().getSearchrefid();
	                logger.debug(refId);
	                String status = eForm.getCitizenDTO().getStatus();
	                logger.debug(status);
	        		
	                CitizenFeebackViewBD cbd = new CitizenFeebackViewBD();
	        		ArrayList pendingApplicationList = cbd.getFilteredresults(durationFrom,durationTo,refId,status,offid,userId,language);
	        		
	        		eForm.setPendingRegApplicationList(pendingApplicationList);
	        		request.setAttribute("pendingApplicationList", eForm.getPendingRegApplicationList());
	        		
	        		if(pendingApplicationList.size()!=0)
	        		{
	        			logger.debug("success");
	        		}
	        		eForm.setActionName("");
	        		forwardJsp= "ViewDetails";
	        		
				}
				
	        	

			
			if (request.getParameter("hdnReferenceId") != null) {
		
				System.out.println(request.getParameter("hdnReferenceId"));
				String txnBalBoth = request.getParameter("hdnReferenceId").toString();
				
				

				eForm.getCitizenDTO().setHdnreferenceId(txnBalBoth);
				//eForm.getObjDashBoardDTO().setBalanceAmount(txnBalBoth[1]);
				//eForm.getObjDashBoardDTO().setHidnUserId(userId);
				//objDashBoardDTO1.setFlag("Y");
				// String appStatus[] = new String[5];

					String refId = eForm.getCitizenDTO().getHdnreferenceId();
					
					eForm.getCitizenDTO().setReferenceId(refId);
					
					ArrayList list = citizenBD.getDetails(refId, language);
					
					CitizenFeedbackDTO dto= eForm.getCitizenDTO();
					
					dto= (CitizenFeedbackDTO) list.get(0);
					
					String status=dto.getStatus();
					
					logger.debug(dto.getStatus());
					if("Completed".equalsIgnoreCase(status))
					{
						dto.setLogic("1");
					}
					else
					{
						dto.setLogic("2");
					}
					//dto.getDrcomments();    // Commented to display Feedback details.
					//logger.debug(dto.getDrcomments());
					for(int i=0;i<list.size();i++)
					{
						dto= (CitizenFeedbackDTO) list.get(i);
						dto.setReferenceId(refId);
					}
					
					eForm.setDetailList(list);
					eForm.setCitizenDTO(dto);
					
					
					
					System.out.println("in action printing"+list);
					
					if(list.size() != 0)
					{
						logger.debug("Got Successfully");
						forwardJsp = "showdetails";
					} 
					
					else {
						logger.debug("Failed!");
					}
					
					
			}
				
				
			}
		
		return mapping.findForward(forwardJsp);
	}
}
