/* 
 * <Copyright information>
 *
 * <Customer specific copyright notice (if any) >
 *
 * ==============================================================================
 * This file contains proprietary information of Wipro Infotech Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007
 * ==============================================================================
 * 
 * File Name	   		: RevenueMgmtAction.java
 *
 * Description	   		: Struts Action to view Revenue Management Details
 *
 * Version Number  		: 1.0 
 *
 * Created Date	   		: 24 12 2007  
 *
 * Modification History	: Created
 */


package com.wipro.igrs.revenuemgmt.action;


import java.util.ArrayList;

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
import com.wipro.igrs.revenuemgmt.bd.RevenueMgmtBD;
import com.wipro.igrs.revenuemgmt.common.RevenueMgmtConstants;
import com.wipro.igrs.revenuemgmt.form.RevenueMgmtForm;

public class RevenueMgmtAction extends BaseAction {
    /**
 * This is the main action called from the Struts framework.
 * @param mapping The ActionMapping used to select this instance.
 * @param form The optional ActionForm bean for this request.
 * @param request The HTTP Request we are processing.
 * @param response The HTTP Response we are processing.
 * @throws Exception
 * @return ActionForward
 */
	private Logger logger = (Logger) Logger.getLogger(RevenueMgmtAction.class);
public ActionForward execute(ActionMapping mapping, ActionForm form, 
                             HttpServletRequest request, 
                             HttpServletResponse response,HttpSession session) throws Exception {
	logger.debug("WELCOME TO IGRS REVENUE MGMT ACTION");
    RevenueMgmtForm revenueMgmtForm = (RevenueMgmtForm)form;
    RevenueMgmtBD revenueReportsBD = new RevenueMgmtBD(); 
    ActionMessages messages = new ActionMessages();
    /** page documentation comment */
    String page = revenueMgmtForm.getPage();
    /** actionID documentation comment */
    String actionID = revenueMgmtForm.getActionID(); 
    /** returnPage documentation comment */
    String returnPage = "";
    logger.debug("page:-"+page+" and actionID:-"+actionID);
   // HttpSession session = request.getSession(); 
    String roleId = (String)session.getAttribute("roleId");
	String funId = (String)session.getAttribute("functionId");
	String userId = (String)session.getAttribute("UserId");
	System.out.println("page:-"+page+" and actionID:-"+actionID);
    if (form != null) {
    	// for Revenue Collect Reports       			
    	if(page.equalsIgnoreCase(
    			RevenueMgmtConstants.LBL_REVENUE_COLLECT_RPT_PAGE)){        		
    		if(actionID.equalsIgnoreCase(RevenueMgmtConstants.
    				LBL_REVENUE_COLLECT_RPT_ACTIONID)){
    			logger.debug("in Revenue Collection Report action");    
    			String districtId = revenueMgmtForm.getRevenueMgmtDTO().getDistrictId();
    			logger.debug("districtId===="+districtId);
    			session.setAttribute("DistrictId", districtId);
    			logger.debug("session.getAttribute(DistrictId)**"+session.getAttribute("DistrictId"));
            	ArrayList reportList = revenueReportsBD.
            				getRevenueDetails(revenueMgmtForm);
            	logger.debug("the SIZE of revenueRprtList:-"
            			+reportList.size());
            	if(reportList.size()==0){
            		messages.add("msg1",
            				new ActionMessage("no.reports.available"));
            		messages.add("message",new ActionMessage("continue"));
            		saveMessages(request, messages);
                	request.setAttribute("revenueList",reportList);
            		ArrayList list = 
            			revenueReportsBD.getDistrictDetails(revenueMgmtForm);
            		logger.debug("officeNameList-----"+list); 
    				revenueMgmtForm.setDistrictList(list);
    				request.setAttribute("districtlst",revenueMgmtForm);
    				
    				ArrayList officeTypeLst = 
    					revenueReportsBD.getOfficeTypeDetails(revenueMgmtForm);
    				logger.debug("officeNameList-----"+officeTypeLst); 
    		        revenueMgmtForm.setOfficeTypeLst(officeTypeLst);
    		        request.setAttribute("officetypelst",revenueMgmtForm);
    		        
    		        ArrayList officeNameList = 
    		        	revenueReportsBD.getOfficeNameDetails(revenueMgmtForm);
    	        	logger.debug("officeNameList-----"+officeNameList);        	
    	        	revenueMgmtForm.setOfficeNameList(officeNameList);
    	        	request.setAttribute("officenamelst",revenueMgmtForm);
    	        	returnPage = RevenueMgmtConstants.REV_CLT_RPT_SUCCESS;
            	}
            	else{
					revenueMgmtForm.setReportList(reportList);
                	session.setAttribute("revenueReport1",reportList);
                	returnPage = 
                		RevenueMgmtConstants.LBL_REVENUE_COLLECT_RPT_SEARCH_PAGE;
            	}
    		}
    		if(actionID.equalsIgnoreCase(RevenueMgmtConstants.LBL_SIGNOUT)){
				returnPage = RevenueMgmtConstants.LBL_SIGNOUT;
    		}
    		if(actionID.equalsIgnoreCase(RevenueMgmtConstants.LBL_CANCEL)){
				logger.debug("your are in cancel action");
				returnPage = RevenueMgmtConstants.LBL_CANCEL;
			}
    		if(actionID.equalsIgnoreCase(RevenueMgmtConstants.LBL_RESET)){
    			ArrayList list = 
        			revenueReportsBD.getDistrictDetails(revenueMgmtForm);
        		logger.debug("officeNameList-----"+list); 
				revenueMgmtForm.setDistrictList(list);
				request.setAttribute("districtlst",revenueMgmtForm);
				
				ArrayList officeTypeLst = 
					revenueReportsBD.getOfficeTypeDetails(revenueMgmtForm);
				logger.debug("officeNameList-----"+officeTypeLst); 
		        revenueMgmtForm.setOfficeTypeLst(officeTypeLst);
		        request.setAttribute("officetypelst",revenueMgmtForm);
		        
		        ArrayList officeNameList = 
		        	revenueReportsBD.getOfficeNameDetails(revenueMgmtForm);
	        	logger.debug("officeNameList-----"+officeNameList);        	
	        	revenueMgmtForm.setOfficeNameList(officeNameList);
	        	request.setAttribute("officenamelst",revenueMgmtForm);
    			revenueMgmtForm.getRevenueMgmtDTO().setDistrictId("Select");
    			revenueMgmtForm.getRevenueMgmtDTO().setOfficeId("Select");
    			revenueMgmtForm.getRevenueMgmtDTO().setOfficeTypeId("Select");
    			returnPage = RevenueMgmtConstants.REV_CLT_RPT_SUCCESS;
    		}
    	}
    	if(page.equalsIgnoreCase(
    			RevenueMgmtConstants.LBL_REVENUE_COLLECT_RPT_SEARCH_PAGE)){
    		if(actionID.equalsIgnoreCase(RevenueMgmtConstants.LBL_BACK)){
    			if(request.getAttribute("districtlst")!= null){
    				logger.debug("******************88");
    			}else{
    				ArrayList list = 
            			revenueReportsBD.getDistrictDetails(revenueMgmtForm);
            		logger.debug("officeNameList-----"+list); 
    				revenueMgmtForm.setDistrictList(list);
    				request.setAttribute("districtlst",revenueMgmtForm);
    				
    				ArrayList officeTypeLst = 
    					revenueReportsBD.getOfficeTypeDetails(revenueMgmtForm);
    				logger.debug("officeNameList-----"+officeTypeLst); 
    		        revenueMgmtForm.setOfficeTypeLst(officeTypeLst);
    		        request.setAttribute("officetypelst",revenueMgmtForm);
    		        
    		        ArrayList officeNameList = 
    		        	revenueReportsBD.getOfficeNameDetails(revenueMgmtForm);
    	        	logger.debug("officeNameList-----"+officeNameList);        	
    	        	revenueMgmtForm.setOfficeNameList(officeNameList);
    	        	request.setAttribute("officenamelst",revenueMgmtForm);
    			}        			
    			returnPage = RevenueMgmtConstants.REV_CLT_RPT_SUCCESS;
    		}
    		if(actionID.equalsIgnoreCase(RevenueMgmtConstants.LBL_PRINT)){
    			logger.debug("in rev clt search print page========");
    			logger.debug("session.getAttribute(DistrictId)**"+session.getAttribute("DistrictId"));
    			revenueMgmtForm.getRevenueMgmtDTO().setDistrictId((String)session.getAttribute("DistrictId"));
    			logger.debug("districtId in search print===="+revenueMgmtForm.getRevenueMgmtDTO().getDistrictId());
    			ArrayList reportList = 
    				revenueReportsBD.getRevenueDetails(revenueMgmtForm);
            	logger.debug("the SIZE of reportList:- " + reportList.size());
            	revenueMgmtForm.setReportList(reportList);
            	request.setAttribute("revenueReport1", revenueMgmtForm);
    			returnPage = 
    				RevenueMgmtConstants.LBL_REVENUE_COLLECT_RPT_PRINT_PAGE;
    		}
    		if(actionID.equalsIgnoreCase(RevenueMgmtConstants.LBL_SIGNOUT)){
    			logger.debug("fromDate value===="+revenueMgmtForm.getFromDate());
    			session.removeAttribute("FromDate");
				session.invalidate();
				logger.debug("after invalidate fromDate value===="+revenueMgmtForm.getFromDate());
				returnPage = RevenueMgmtConstants.LBL_SIGNOUT;
    		}
    	}
    	if(page.equalsIgnoreCase(
    			RevenueMgmtConstants.LBL_REVENUE_COLLECT_RPT_PRINT_PAGE)){
    		if(actionID.equalsIgnoreCase(RevenueMgmtConstants.LBL_BACK)){
    			logger.debug("session.getAttribute(DistrictId)**"
    					+session.getAttribute("DistrictId"));
    			revenueMgmtForm.getRevenueMgmtDTO().setDistrictId(
    					(String)session.getAttribute("DistrictId"));
    			logger.debug("districtId in revrecon search print===="
    					+revenueMgmtForm.getRevenueMgmtDTO().getDistrictId());
    			ArrayList reportList = 
    				revenueReportsBD.getRevenueDetails(revenueMgmtForm);
            	logger.debug("the SIZE of reportList:- " + reportList.size());
            	revenueMgmtForm.setReportList(reportList);
            	request.setAttribute("revenueReport1",revenueMgmtForm);
            	returnPage = 
            		RevenueMgmtConstants.LBL_REVENUE_COLLECT_RPT_SEARCH_PAGE;
    		}
    	}
    	// for Cash Payments Report
    	if(page.equalsIgnoreCase(RevenueMgmtConstants.LBL_CASH_PYMNTS_PAGE)){
    		if(actionID.equalsIgnoreCase(
    				RevenueMgmtConstants.LBL_CASH_PYMNTS_ACTIONID)){
    			logger.debug("in Cash Pymnts action");
    			String districtId = revenueMgmtForm.getRevenueMgmtDTO().getDistrictId();
    			logger.debug("districtId===="+districtId);
    			session.setAttribute("DistrictId", districtId);
    			logger.debug("session.getAttribute(DistrictId)**"+session.getAttribute("DistrictId"));
    			ArrayList reportList = 
    				revenueReportsBD.getCashPaymentDetails(revenueMgmtForm);       
    			if(reportList.size()==0){
            		messages.add("msg1",new ActionMessage("no.reports.available"));
            		messages.add("message",new ActionMessage("continue"));
            		saveMessages(request, messages);
                	request.setAttribute("revenueList", reportList);
                	ArrayList list = 
            			revenueReportsBD.getDistrictDetails(revenueMgmtForm);
            		logger.debug("officeNameList-----"+list); 
    				revenueMgmtForm.setDistrictList(list);
    				request.setAttribute("districtlst",revenueMgmtForm);
    				
    				ArrayList officeTypeLst = 
    					revenueReportsBD.getOfficeTypeDetails(revenueMgmtForm);
    				logger.debug("officeNameList-----"+officeTypeLst); 
    		        revenueMgmtForm.setOfficeTypeLst(officeTypeLst);
    		        request.setAttribute("officetypelst",revenueMgmtForm);
    		        
    		        ArrayList officeNameList = 
    		        	revenueReportsBD.getOfficeNameDetails(revenueMgmtForm);
    	        	logger.debug("officeNameList------"+officeNameList);        	
    	        	revenueMgmtForm.setOfficeNameList(officeNameList);
    	        	request.setAttribute("officenamelst",revenueMgmtForm);
    	        	returnPage = RevenueMgmtConstants.CASH_PYMNT_SUCCESS;
            	}
            	else{
            		revenueMgmtForm.setReportList(reportList);
                	session.setAttribute("revenueReport1", reportList);	        
        	        returnPage = 
        	        	RevenueMgmtConstants.LBL_CASH_PAYMENTS_SEARCH_PAGE;
            	}
    		}		 
			if(actionID.equalsIgnoreCase(RevenueMgmtConstants.LBL_BACK)){        			 
			  returnPage = RevenueMgmtConstants.LBL_BACK;
			}
			logger.debug("hai");
			if(actionID.equalsIgnoreCase(RevenueMgmtConstants.LBL_CANCEL)){
				logger.debug("your are in cancel action");
				returnPage = RevenueMgmtConstants.LBL_CANCEL;
			}
			if(actionID.equalsIgnoreCase(RevenueMgmtConstants.LBL_SIGNOUT)){
			  returnPage = RevenueMgmtConstants.LBL_SIGNOUT;
			}
			if(actionID.equalsIgnoreCase(RevenueMgmtConstants.LBL_RESET)){
    			ArrayList list = 
        			revenueReportsBD.getDistrictDetails(revenueMgmtForm);
        		logger.debug("officeNameList-----"+list); 
				revenueMgmtForm.setDistrictList(list);
				request.setAttribute("districtlst",revenueMgmtForm);
				
				ArrayList officeTypeLst = 
					revenueReportsBD.getOfficeTypeDetails(revenueMgmtForm);
				logger.debug("officeNameList-----"+officeTypeLst); 
		        revenueMgmtForm.setOfficeTypeLst(officeTypeLst);
		        request.setAttribute("officetypelst",revenueMgmtForm);
		        
		        ArrayList officeNameList = 
		        	revenueReportsBD.getOfficeNameDetails(revenueMgmtForm);
	        	logger.debug("officeNameList-----"+officeNameList);        	
	        	revenueMgmtForm.setOfficeNameList(officeNameList);
	        	request.setAttribute("officenamelst",revenueMgmtForm);
    			revenueMgmtForm.getRevenueMgmtDTO().setDistrictId("Select");
    			revenueMgmtForm.getRevenueMgmtDTO().setOfficeId("Select");
    			revenueMgmtForm.getRevenueMgmtDTO().setOfficeTypeId("Select");
    			returnPage = RevenueMgmtConstants.CASH_PYMNT_SUCCESS;
    		}
    	}
    	if(page.equalsIgnoreCase(RevenueMgmtConstants.LBL_CASH_PAYMENTS_SEARCH_PAGE)){
    		if(actionID.equalsIgnoreCase(RevenueMgmtConstants.LBL_BACK)){
    			if(request.getAttribute("districtlst")!= null){
    				logger.debug("******************88");
    			}else{
    				ArrayList list = 
            			revenueReportsBD.getDistrictDetails(revenueMgmtForm);
            		logger.debug("officeNameList-----"+list); 
    				revenueMgmtForm.setDistrictList(list);
    				request.setAttribute("districtlst",revenueMgmtForm);
    				
    				ArrayList officeTypeLst = 
    					revenueReportsBD.getOfficeTypeDetails(revenueMgmtForm);
    				logger.debug("officeNameList-----"+officeTypeLst); 
    		        revenueMgmtForm.setOfficeTypeLst(officeTypeLst);
    		        request.setAttribute("officetypelst",revenueMgmtForm);
    		        
    		        ArrayList officeNameList = 
    		        	revenueReportsBD.getOfficeNameDetails(revenueMgmtForm);
    	        	logger.debug("officeNameList------"+officeNameList);        	
    	        	revenueMgmtForm.setOfficeNameList(officeNameList);
    	        	request.setAttribute("officenamelst",revenueMgmtForm);
    			}
    			returnPage = RevenueMgmtConstants.CASH_PYMNT_SUCCESS;
    		}
    		if(actionID.equalsIgnoreCase(RevenueMgmtConstants.LBL_PRINT)){
    			logger.debug("in print page========");
    			logger.debug("session.getAttribute(DistrictId)**"
    					+session.getAttribute("DistrictId"));
    			revenueMgmtForm.getRevenueMgmtDTO().setDistrictId(
    					(String)session.getAttribute("DistrictId"));
    			logger.debug("districtId in cash search print===="
    					+revenueMgmtForm.getRevenueMgmtDTO().getDistrictId());
    			ArrayList reportList = 
    				revenueReportsBD.getCashPaymentDetails(revenueMgmtForm);       
    			revenueMgmtForm.setReportList(reportList);
    	        // request.setAttribute("dailyCashPayments",dailyCashPaymentsForm);	        
    	        returnPage = 
    	        	RevenueMgmtConstants.LBL_CASH_PAYMENTS_RPT_PRINT_PAGE;       			
    		}
    		if(actionID.equalsIgnoreCase(RevenueMgmtConstants.LBL_SIGNOUT)){
				returnPage = RevenueMgmtConstants.LBL_SIGNOUT;
    		}
    	}
    	if(page.equalsIgnoreCase(
    			RevenueMgmtConstants.LBL_CASH_PAYMENTS_RPT_PRINT_PAGE)){
    		if(actionID.equalsIgnoreCase(RevenueMgmtConstants.LBL_BACK)){
    			logger.debug("session.getAttribute(DistrictId)**"
    					+session.getAttribute("DistrictId"));
    			revenueMgmtForm.getRevenueMgmtDTO().setDistrictId(
    					(String)session.getAttribute("DistrictId"));
    			logger.debug("districtId in revrecon search print===="
    					+revenueMgmtForm.getRevenueMgmtDTO().getDistrictId());
    			ArrayList reportList = 
    				revenueReportsBD.getCashPaymentDetails(revenueMgmtForm);       
    			revenueMgmtForm.setReportList(reportList);
    	        // request.setAttribute("dailyCashPayments",dailyCashPaymentsForm);
            	returnPage = RevenueMgmtConstants.LBL_CASH_PAYMENTS_SEARCH_PAGE;
    		}
    	}
    	// for Challan Payment Reports
    	if(page.equalsIgnoreCase(RevenueMgmtConstants.LBL_CHALLAN_PYMNTS_PAGE)){
    		if(actionID.equalsIgnoreCase(
    				RevenueMgmtConstants.LBL_CHALLAN_PYMNTS_ACTIONID)){
    			logger.debug("in Challan Pymnts report action");
    			String districtId = revenueMgmtForm.getRevenueMgmtDTO().getDistrictId();
    			logger.debug("districtId===="+districtId);
    			session.setAttribute("DistrictId", districtId);
    			logger.debug("session.getAttribute(DistrictId)**"+session.getAttribute("DistrictId"));
    			ArrayList reportList = 
    				revenueReportsBD.getChallanPaymentDetails(revenueMgmtForm);       
    			if(reportList.size()==0){
            		messages.add("msg1",new ActionMessage("no.reports.available"));
            		messages.add("message",new ActionMessage("continue"));
            		saveMessages(request, messages);
                	request.setAttribute("revenueList", reportList);
                	ArrayList list = 
            			revenueReportsBD.getDistrictDetails(revenueMgmtForm);
            		logger.debug("officeNameList-----"+list); 
    				revenueMgmtForm.setDistrictList(list);
    				request.setAttribute("districtlst",revenueMgmtForm);
    				
    				ArrayList officeTypeLst = 
    					revenueReportsBD.getOfficeTypeDetails(revenueMgmtForm);
    				logger.debug("officeNameList-----"+officeTypeLst); 
    		        revenueMgmtForm.setOfficeTypeLst(officeTypeLst);
    		        request.setAttribute("officetypelst",revenueMgmtForm);
    		        
    		        ArrayList officeNameList = 
    		        	revenueReportsBD.getOfficeNameDetails(revenueMgmtForm);
    	        	logger.debug("officeNameList-----"+officeNameList);        	
    	        	revenueMgmtForm.setOfficeNameList(officeNameList);
    	        	request.setAttribute("officenamelst",revenueMgmtForm);
    	        	returnPage = RevenueMgmtConstants.CHALLAN_PYMNT_SUCCESS;
            	}
            	else{
            		revenueMgmtForm.setReportList(reportList);
                	session.setAttribute("revenueReport1", reportList);	        
                	returnPage = 
                		RevenueMgmtConstants.LBL_CHALLAN_PAYMENTS_SEARCH_PAGE;                		
            	}
    			
        	}
    		if(actionID.equalsIgnoreCase(RevenueMgmtConstants.LBL_CANCEL)){
    			  logger.debug("your are in cancel action");
    			  returnPage = RevenueMgmtConstants.LBL_CANCEL;
    		}
    		if(actionID.equalsIgnoreCase(RevenueMgmtConstants.LBL_SIGNOUT)){
    			  returnPage = RevenueMgmtConstants.LBL_SIGNOUT;
    		}	
    		if(actionID.equalsIgnoreCase(RevenueMgmtConstants.LBL_RESET)){
    			ArrayList list = 
        			revenueReportsBD.getDistrictDetails(revenueMgmtForm);
        		logger.debug("officeNameList-----"+list); 
				revenueMgmtForm.setDistrictList(list);
				request.setAttribute("districtlst",revenueMgmtForm);
				
				ArrayList officeTypeLst = 
					revenueReportsBD.getOfficeTypeDetails(revenueMgmtForm);
				logger.debug("officeNameList-----"+officeTypeLst); 
		        revenueMgmtForm.setOfficeTypeLst(officeTypeLst);
		        request.setAttribute("officetypelst",revenueMgmtForm);
		        
		        ArrayList officeNameList = 
		        	revenueReportsBD.getOfficeNameDetails(revenueMgmtForm);
	        	logger.debug("officeNameList-----"+officeNameList);        	
	        	revenueMgmtForm.setOfficeNameList(officeNameList);
	        	request.setAttribute("officenamelst",revenueMgmtForm);
    			revenueMgmtForm.getRevenueMgmtDTO().setDistrictId("Select");
    			revenueMgmtForm.getRevenueMgmtDTO().setOfficeId("Select");
    			revenueMgmtForm.getRevenueMgmtDTO().setOfficeTypeId("Select");
    			returnPage = RevenueMgmtConstants.CHALLAN_PYMNT_SUCCESS;
    		}
    	}
    	if(page.equalsIgnoreCase(
    			RevenueMgmtConstants.LBL_CHALLAN_PAYMENTS_SEARCH_PAGE)){
    		if(actionID.equalsIgnoreCase(RevenueMgmtConstants.LBL_BACK)){
    			if(request.getAttribute("districtlst")!= null){
    				logger.debug("******************88");
    			}else{
    				ArrayList list = 
            			revenueReportsBD.getDistrictDetails(revenueMgmtForm);
            		logger.debug("officeNameList-----"+list); 
    				revenueMgmtForm.setDistrictList(list);
    				request.setAttribute("districtlst",revenueMgmtForm);
    				
    				ArrayList officeTypeLst = 
    					revenueReportsBD.getOfficeTypeDetails(revenueMgmtForm);
    				logger.debug("officeNameList-----"+officeTypeLst); 
    		        revenueMgmtForm.setOfficeTypeLst(officeTypeLst);
    		        request.setAttribute("officetypelst",revenueMgmtForm);
    		        
    		        ArrayList officeNameList = 
    		        	revenueReportsBD.getOfficeNameDetails(revenueMgmtForm);
    	        	logger.debug("officeNameList-----"+officeNameList);        	
    	        	revenueMgmtForm.setOfficeNameList(officeNameList);
    	        	request.setAttribute("officenamelst",revenueMgmtForm); 
    			}
    			returnPage = RevenueMgmtConstants.CHALLAN_PYMNT_SUCCESS;
    		}
    		if(actionID.equalsIgnoreCase(RevenueMgmtConstants.LBL_PRINT)){
    			logger.debug("in challan print page========");
    			logger.debug("session.getAttribute(DistrictId)**"
    					+session.getAttribute("DistrictId"));
    			revenueMgmtForm.getRevenueMgmtDTO().setDistrictId(
    					(String)session.getAttribute("DistrictId"));
    			logger.debug("districtId in challan search print===="
    					+revenueMgmtForm.getRevenueMgmtDTO().getDistrictId());
    			ArrayList reportList = 
    				revenueReportsBD.getChallanPaymentDetails(revenueMgmtForm);       
    			revenueMgmtForm.setReportList(reportList);
    	        //request.setAttribute("dailyCashPayments",dailyCashPaymentsForm);	        
    	        returnPage = 
    	        	RevenueMgmtConstants.LBL_CHALLAN_PAYMENTS_RPT_PRINT_PAGE;       			
    		}
    		if(actionID.equalsIgnoreCase(RevenueMgmtConstants.LBL_SIGNOUT)){
				returnPage = RevenueMgmtConstants.LBL_SIGNOUT;
    		}
    	}
    	if(page.equalsIgnoreCase(RevenueMgmtConstants.
    			LBL_CHALLAN_PAYMENTS_RPT_PRINT_PAGE)){
    		if(actionID.equalsIgnoreCase(RevenueMgmtConstants.LBL_BACK)){
    			logger.debug("session.getAttribute(DistrictId)**"
    					+session.getAttribute("DistrictId"));
    			revenueMgmtForm.getRevenueMgmtDTO().setDistrictId(
    					(String)session.getAttribute("DistrictId"));
    			logger.debug("districtId in revrecon search print===="
    					+revenueMgmtForm.getRevenueMgmtDTO().getDistrictId());
    			ArrayList reportList = 
    				revenueReportsBD.getChallanPaymentDetails(revenueMgmtForm);       
    			revenueMgmtForm.setReportList(reportList);
    	        //request.setAttribute("dailyCashPayments",dailyCashPaymentsForm);
            	returnPage = 
            		RevenueMgmtConstants.LBL_CHALLAN_PAYMENTS_SEARCH_PAGE;
    		}
    	}
    	
    	// for Online Payments
    	if(page.equalsIgnoreCase(RevenueMgmtConstants.LBL_ONLINE_PYMNTS_PAGE)){
    		if(actionID.equalsIgnoreCase(
    				RevenueMgmtConstants.LBL_ONLINE_PYMNTS_ACTIONID)){
    			logger.debug("in Daily Online Pymnts action");
    			String districtId = revenueMgmtForm.getRevenueMgmtDTO().getDistrictId();
    			logger.debug("districtId===="+districtId);
    			session.setAttribute("DistrictId", districtId);
    			logger.debug("session.getAttribute(DistrictId)**"+session.getAttribute("DistrictId"));
    			ArrayList reportList = 
    				revenueReportsBD.getOnlinePaymentDetails(revenueMgmtForm);       
    			if(reportList.size()==0){
            		messages.add("msg1",new ActionMessage("no.reports.available"));
            		messages.add("message",new ActionMessage("continue"));
            		saveMessages(request, messages);
                	request.setAttribute("revenueList", reportList);
                	ArrayList list = 
            			revenueReportsBD.getDistrictDetails(revenueMgmtForm);
            		logger.debug("officeNameList-----"+list); 
    				revenueMgmtForm.setDistrictList(list);
    				request.setAttribute("districtlst",revenueMgmtForm);
    				
    				ArrayList officeTypeLst = 
    					revenueReportsBD.getOfficeTypeDetails(revenueMgmtForm);
    				logger.debug("officeNameList-----"+officeTypeLst); 
    		        revenueMgmtForm.setOfficeTypeLst(officeTypeLst);
    		        request.setAttribute("officetypelst",revenueMgmtForm);
    		        
    		        ArrayList officeNameList = 
    		        	revenueReportsBD.getOfficeNameDetails(revenueMgmtForm);
    	        	logger.debug("officeNameList-----"+officeNameList);        	
    	        	revenueMgmtForm.setOfficeNameList(officeNameList);
    	        	request.setAttribute("officenamelst",revenueMgmtForm);
    	        	returnPage = RevenueMgmtConstants.ONLINE_PYMNT_SUCCESS;
            	}
            	else{
            		revenueMgmtForm.setReportList(reportList);
                	session.setAttribute("revenueReport1", reportList);	        
        	        returnPage = 
        	        	RevenueMgmtConstants.LBL_ONLINE_PAYMENTS_SEARCH_PAGE;
            	}
    		}		 
    		logger.debug("actionID value:-"+actionID);		 
    		if(actionID.equalsIgnoreCase(RevenueMgmtConstants.LBL_BACK)){
			  returnPage = RevenueMgmtConstants.LBL_BACK;
    		}
		 	logger.debug("hai");
		 	if(actionID.equalsIgnoreCase(RevenueMgmtConstants.LBL_CANCEL)){
		 		logger.debug("your are in cancel action");
		 		returnPage = RevenueMgmtConstants.LBL_CANCEL;
		 	}
		 	if(actionID.equalsIgnoreCase(RevenueMgmtConstants.LBL_SIGNOUT)){
			  returnPage = RevenueMgmtConstants.LBL_SIGNOUT;
		 	}
		 	if(actionID.equalsIgnoreCase(RevenueMgmtConstants.LBL_RESET)){
    			ArrayList list = 
        			revenueReportsBD.getDistrictDetails(revenueMgmtForm);
        		logger.debug("officeNameList-----"+list); 
				revenueMgmtForm.setDistrictList(list);
				request.setAttribute("districtlst",revenueMgmtForm);
				
				ArrayList officeTypeLst = 
					revenueReportsBD.getOfficeTypeDetails(revenueMgmtForm);
				logger.debug("officeNameList-----"+officeTypeLst); 
		        revenueMgmtForm.setOfficeTypeLst(officeTypeLst);
		        request.setAttribute("officetypelst",revenueMgmtForm);
		        
		        ArrayList officeNameList = 
		        	revenueReportsBD.getOfficeNameDetails(revenueMgmtForm);
	        	logger.debug("officeNameList-----"+officeNameList);        	
	        	revenueMgmtForm.setOfficeNameList(officeNameList);
	        	request.setAttribute("officenamelst",revenueMgmtForm);
    			revenueMgmtForm.getRevenueMgmtDTO().setDistrictId("Select");
    			revenueMgmtForm.getRevenueMgmtDTO().setOfficeId("Select");
    			revenueMgmtForm.getRevenueMgmtDTO().setOfficeTypeId("Select");
    			returnPage = RevenueMgmtConstants.ONLINE_PYMNT_SUCCESS;
    		}
    	}
    	if(page.equalsIgnoreCase(RevenueMgmtConstants.LBL_ONLINE_PAYMENTS_SEARCH_PAGE)){
    		if(actionID.equalsIgnoreCase(RevenueMgmtConstants.LBL_BACK)){
    			if(request.getAttribute("districtlst")!= null){
    				logger.debug("******************88");
    			}else{
    				ArrayList list = 
            			revenueReportsBD.getDistrictDetails(revenueMgmtForm);
            		logger.debug("officeNameList-----"+list); 
    				revenueMgmtForm.setDistrictList(list);
    				request.setAttribute("districtlst",revenueMgmtForm);
    				
    				ArrayList officeTypeLst = 
    					revenueReportsBD.getOfficeTypeDetails(revenueMgmtForm);
    				logger.debug("officeNameList-----"+officeTypeLst); 
    		        revenueMgmtForm.setOfficeTypeLst(officeTypeLst);
    		        request.setAttribute("officetypelst",revenueMgmtForm);
    		        
    		        ArrayList officeNameList = 
    		        	revenueReportsBD.getOfficeNameDetails(revenueMgmtForm);
    	        	logger.debug("officeNameList-----"+officeNameList);        	
    	        	revenueMgmtForm.setOfficeNameList(officeNameList);
    	        	request.setAttribute("officenamelst",revenueMgmtForm);
    	        	request.setAttribute("officenamelst", revenueMgmtForm); 
    			}
    			returnPage = RevenueMgmtConstants.ONLINE_PYMNT_SUCCESS;
    		}
    		if(actionID.equalsIgnoreCase(RevenueMgmtConstants.LBL_PRINT)){
    			logger.debug("in online print page========");
    			logger.debug("session.getAttribute(DistrictId)**"
    					+session.getAttribute("DistrictId"));
    			revenueMgmtForm.getRevenueMgmtDTO().setDistrictId(
    					(String)session.getAttribute("DistrictId"));
    			logger.debug("districtId in online search print===="
    					+revenueMgmtForm.getRevenueMgmtDTO().getDistrictId());
    			/*modified by Imran Shaik 
    			 * calling wrong method instead of Online calling Challan payment details 
    			 * ArrayList reportList = 
    				revenueReportsBD.getChallanPaymentDetails(revenueMgmtForm);*/
    			ArrayList reportList = 
    				revenueReportsBD.getOnlinePaymentDetails(revenueMgmtForm);
    			revenueMgmtForm.setReportList(reportList);
    	        // request.setAttribute("dailyCashPayments",dailyCashPaymentsForm);	        
    	        returnPage = RevenueMgmtConstants.LBL_ONLINE_PAYMENTS_RPT_PRINT_PAGE;       			
    		}
    		if(actionID.equalsIgnoreCase(RevenueMgmtConstants.LBL_SIGNOUT)){
				returnPage = RevenueMgmtConstants.LBL_SIGNOUT;
    		}
    	}
    	if(page.equalsIgnoreCase(
    			RevenueMgmtConstants.LBL_ONLINE_PAYMENTS_RPT_PRINT_PAGE)){
    		if(actionID.equalsIgnoreCase(RevenueMgmtConstants.LBL_BACK)){
    			logger.debug("session.getAttribute(DistrictId)**"
    					+session.getAttribute("DistrictId"));
    			revenueMgmtForm.getRevenueMgmtDTO().setDistrictId(
    					(String)session.getAttribute("DistrictId"));
    			logger.debug("districtId in revrecon search print===="
    					+revenueMgmtForm.getRevenueMgmtDTO().getDistrictId());
    			ArrayList reportList = 
    				revenueReportsBD.getChallanPaymentDetails(revenueMgmtForm);       
    			revenueMgmtForm.setReportList(reportList);
    	        //request.setAttribute("dailyCashPayments",dailyCashPaymentsForm);
            	returnPage = 
            		RevenueMgmtConstants.LBL_ONLINE_PAYMENTS_SEARCH_PAGE;
    		}
    	}
    	// for Revenue Reconciliation Reports
    	if(page.equalsIgnoreCase(RevenueMgmtConstants.LBL_REV_RECON_RPT_PAGE)){
    		if(actionID.equalsIgnoreCase(RevenueMgmtConstants.LBL_REV_RECON_RPT_ACTIONID)){
    			logger.debug("Before BD calling---> ");
    			String districtId = revenueMgmtForm.getRevenueMgmtDTO().getDistrictId();
    			logger.debug("districtId===="+districtId);
    			session.setAttribute("DistrictId", districtId);
    			logger.debug("session.getAttribute(DistrictId)**"+session.getAttribute("DistrictId"));
    			ArrayList reportList =  revenueReportsBD.getRevReconDetails(revenueMgmtForm);
    			logger.debug("after BD Calling --->");
    			logger.debug("size:-"+reportList.size());
    			//RevenueMgmtDTO dto = (RevenueMgmtDTO)reportList.get(0);
                //logger.debug("list DRO value:-"+dto.getCashAtDRO());  
    			if(reportList.size()==0){
    				logger.debug("in revrecon reportlist if loop$$$$$$$$$$$$$$$");
    				messages.add("reconMsg", new ActionMessage("no.reports.available"));
            		saveMessages(request, messages);
                	request.setAttribute("revenueList", reportList);
                	ArrayList list = 
            			revenueReportsBD.getDistrictDetails(revenueMgmtForm);
            		logger.debug("officeNameList-----"+list); 
    				revenueMgmtForm.setDistrictList(list);
    				request.setAttribute("districtlst",revenueMgmtForm);
    				
    				ArrayList officeTypeLst = 
    					revenueReportsBD.getOfficeTypeDetails(revenueMgmtForm);
    				logger.debug("officeNameList-----"+officeTypeLst); 
    		        revenueMgmtForm.setOfficeTypeLst(officeTypeLst);
    		        request.setAttribute("officetypelst",revenueMgmtForm);
    		        
    		        ArrayList officeNameList = 
    		        	revenueReportsBD.getOfficeNameDetails(revenueMgmtForm);
    	        	logger.debug("officeNameList-----"+officeNameList);        	
    	        	revenueMgmtForm.setOfficeNameList(officeNameList);
    	        	request.setAttribute("officenamelst",revenueMgmtForm);
    	        	returnPage = RevenueMgmtConstants.REV_RECON_RPT_SUCCESS;
    			}
    			else{
	                revenueMgmtForm.setReportList(reportList);	                
	                request.setAttribute("revenueReport1",revenueMgmtForm); 
	                returnPage = RevenueMgmtConstants.LBL_REV_RECON_RPT_SEARCH_PAGE;        				
    			}                
    		} 
    		if(actionID.equalsIgnoreCase(RevenueMgmtConstants.LBL_CANCEL)){
    			logger.debug("your are in cancel action");
    			returnPage = RevenueMgmtConstants.LBL_CANCEL;
    		}
    		if(actionID.equalsIgnoreCase(RevenueMgmtConstants.LBL_SIGNOUT)){
    			returnPage = RevenueMgmtConstants.LBL_SIGNOUT;
    		}
    		if(actionID.equalsIgnoreCase(RevenueMgmtConstants.LBL_RESET)){
    			ArrayList list = 
        			revenueReportsBD.getDistrictDetails(revenueMgmtForm);
        		logger.debug("officeNameList-----"+list); 
				revenueMgmtForm.setDistrictList(list);
				request.setAttribute("districtlst",revenueMgmtForm);
				
				ArrayList officeTypeLst = 
					revenueReportsBD.getOfficeTypeDetails(revenueMgmtForm);
				logger.debug("officeNameList-----"+officeTypeLst); 
		        revenueMgmtForm.setOfficeTypeLst(officeTypeLst);
		        request.setAttribute("officetypelst",revenueMgmtForm);
		        
		        ArrayList officeNameList = 
		        	revenueReportsBD.getOfficeNameDetails(revenueMgmtForm);
	        	logger.debug("officeNameList-----"+officeNameList);        	
	        	revenueMgmtForm.setOfficeNameList(officeNameList);
	        	request.setAttribute("officenamelst",revenueMgmtForm);
    			revenueMgmtForm.getRevenueMgmtDTO().setDistrictId("Select");
    			revenueMgmtForm.getRevenueMgmtDTO().setOfficeId("Select");
    			revenueMgmtForm.getRevenueMgmtDTO().setOfficeTypeId("Select");
    			returnPage = RevenueMgmtConstants.REV_RECON_RPT_SUCCESS;
    		}
    	}
    	if(page.equalsIgnoreCase(RevenueMgmtConstants.LBL_REV_RECON_RPT_SEARCH_PAGE)){
    		if(actionID.equalsIgnoreCase(RevenueMgmtConstants.LBL_BACK)){
    			if(request.getAttribute("districtlst")!= null){
    				logger.debug("******************88");
    			}else{
    				ArrayList list = 
            			revenueReportsBD.getDistrictDetails(revenueMgmtForm);
            		logger.debug("officeNameList-----"+list); 
    				revenueMgmtForm.setDistrictList(list);
    				request.setAttribute("districtlst",revenueMgmtForm);
    				
    				ArrayList officeTypeLst = 
    					revenueReportsBD.getOfficeTypeDetails(revenueMgmtForm);
    				logger.debug("officeNameList-----"+officeTypeLst); 
    		        revenueMgmtForm.setOfficeTypeLst(officeTypeLst);
    		        request.setAttribute("officetypelst",revenueMgmtForm);
    		        
    		        ArrayList officeNameList = 
    		        	revenueReportsBD.getOfficeNameDetails(revenueMgmtForm);
    	        	logger.debug("officeNameList-----"+officeNameList);        	
    	        	revenueMgmtForm.setOfficeNameList(officeNameList);
    	        	request.setAttribute("officenamelst",revenueMgmtForm); 
    			}
    			returnPage = RevenueMgmtConstants.REV_RECON_RPT_SUCCESS;
    		}
    		if(actionID.equalsIgnoreCase(RevenueMgmtConstants.LBL_PRINT)){
    			logger.debug("in rev Recon print page========");
    			logger.debug("session.getAttribute(DistrictId)**"
    					+session.getAttribute("DistrictId"));
    			revenueMgmtForm.getRevenueMgmtDTO().setDistrictId(
    					(String)session.getAttribute("DistrictId"));
    			logger.debug("districtId in revrecon search print===="
    					+revenueMgmtForm.getRevenueMgmtDTO().getDistrictId());
    			ArrayList reportList =  
    				revenueReportsBD.getRevReconDetails(revenueMgmtForm);       
    			revenueMgmtForm.setReportList(reportList);	                
                request.setAttribute("revenueReport1",revenueMgmtForm);         
    	        returnPage = RevenueMgmtConstants.LBL_REV_RECON_RPT_PRINT_PAGE;       			
    		}
    		if(actionID.equalsIgnoreCase(RevenueMgmtConstants.LBL_SIGNOUT)){
				returnPage = RevenueMgmtConstants.LBL_SIGNOUT;
    		}
    	}
    	if(page.equalsIgnoreCase(RevenueMgmtConstants.LBL_REV_RECON_RPT_PRINT_PAGE)){
    		if(actionID.equalsIgnoreCase(RevenueMgmtConstants.LBL_BACK)){
    			logger.debug("session.getAttribute(DistrictId)**"
    					+session.getAttribute("DistrictId"));
    			revenueMgmtForm.getRevenueMgmtDTO().setDistrictId(
    					(String)session.getAttribute("DistrictId"));
    			logger.debug("districtId in revrecon search print===="
    					+revenueMgmtForm.getRevenueMgmtDTO().getDistrictId());
    			ArrayList reportList =  
    				revenueReportsBD.getRevReconDetails(revenueMgmtForm);       
    			revenueMgmtForm.setReportList(reportList);	                
                request.setAttribute("revenueReport1",revenueMgmtForm);   
            	returnPage = RevenueMgmtConstants.LBL_REV_RECON_RPT_SEARCH_PAGE;
    		}
    	}
    	// for Advance Payments
    	if(page.equalsIgnoreCase(RevenueMgmtConstants.LBL_ADVANCE_PYMNTS_PAGE)){
    		if(actionID.equalsIgnoreCase(
    				RevenueMgmtConstants.LBL_ADVANCE_PYMNTS_ACTIONID)){
    			logger.debug("in Advance Pymnts action");
    			logger.debug(
    					"advancePaymentsForm.getDto().getOffiId()****"
    					+revenueMgmtForm.getRevenueMgmtDTO().getOffiId());
    			ArrayList advancePaymentsCreditList = 
    				revenueReportsBD.getAdvancePaymentsCreditDetails(revenueMgmtForm);     
    		ArrayList advancePaymentsDebitList = 
    				revenueReportsBD.getAdvancePaymentsDebitDetails(revenueMgmtForm);         
    			revenueMgmtForm.setReportList(advancePaymentsCreditList);
    			revenueMgmtForm.setReportDebitList(advancePaymentsDebitList);
    	        request.setAttribute("advancePaymentList",revenueMgmtForm);
    	      //  request.setAttribute("advancePaymentList",revenueMgmtForm);
    	        returnPage = RevenueMgmtConstants.ADVANCE_PYMNTS_SUCCESS;
    		}
    		if(actionID.equalsIgnoreCase(RevenueMgmtConstants.LBL_RESET)){
    			ArrayList list = 
        			revenueReportsBD.getDistrictDetails(revenueMgmtForm);
        		logger.debug("officeNameList-----"+list); 
				revenueMgmtForm.setDistrictList(list);
				request.setAttribute("districtlst",revenueMgmtForm);
				
				ArrayList officeTypeLst = 
					revenueReportsBD.getOfficeTypeDetails(revenueMgmtForm);
				logger.debug("officeNameList-----"+officeTypeLst); 
		        revenueMgmtForm.setOfficeTypeLst(officeTypeLst);
		        request.setAttribute("officetypelst",revenueMgmtForm);
		        
		        ArrayList officeNameList = 
		        	revenueReportsBD.getOfficeNameDetails(revenueMgmtForm);
	        	logger.debug("officeNameList-----"+officeNameList);        	
	        	revenueMgmtForm.setOfficeNameList(officeNameList);
	        	request.setAttribute("officenamelst",revenueMgmtForm);
    			revenueMgmtForm.getRevenueMgmtDTO().setDistrictId("Select");
    			revenueMgmtForm.getRevenueMgmtDTO().setOfficeId("Select");
    			revenueMgmtForm.getRevenueMgmtDTO().setOfficeTypeId("Select");
    			returnPage = RevenueMgmtConstants.ADVANCE_PYMNTS_SUCCESS;
    		}
    	} 
    	// for Service Fee Matrix
    	if(page.equalsIgnoreCase(
    			RevenueMgmtConstants.LBL_SERVICE_FEE_MATRIX_CREATE_PAGE)){
    		if(actionID.equalsIgnoreCase(
    				RevenueMgmtConstants.LBL_SERVICE_FEE_MATRIX_CREATE_ACTIONID)){
    			logger.debug("in service fee create revenuemgmt action");
    			String districtId = revenueMgmtForm.getRevenueMgmtDTO().getDistrictId();
    			logger.debug("districtId===="+districtId);
    			session.setAttribute("DistrictId", districtId);
    			logger.debug("session.getAttribute(DistrictId)**"+session.getAttribute("DistrictId"));
    			
    			boolean flag = 	revenueReportsBD.insertServiceFeeDetails(revenueMgmtForm,roleId,funId,userId);
    			if(flag == false){
    				messages.add("paramName", 
    						new ActionMessage("paramName.already.database"));
    				saveMessages(request, messages);
    				request.setAttribute("paramNameDetails",revenueReportsBD);
    				returnPage = RevenueMgmtConstants.SERVICE_FEE_CREATE;
    			} 
    			else{
    				messages.add("createMsg", 
    						new ActionMessage("paramid.generated.successfully",
    								revenueMgmtForm.getRevenueMgmtDTO().getParamId()));
    				saveMessages(request, messages);
        			request.setAttribute("CreateDetails",revenueReportsBD);
        			returnPage = RevenueMgmtConstants.REVENUE_MANAGEMENT_SUCCESS;
    			}
    		}
    		if(actionID.equalsIgnoreCase(RevenueMgmtConstants.LBL_SIGNOUT)){
				returnPage = RevenueMgmtConstants.LBL_SIGNOUT;
    		}
    		if(actionID.equalsIgnoreCase(RevenueMgmtConstants.LBL_CANCEL)){
				logger.debug("your are in cancel action");
				returnPage = RevenueMgmtConstants.LBL_CANCEL;
			}
    		if(actionID.equalsIgnoreCase(RevenueMgmtConstants.LBL_RESET)){
    			ArrayList list = 
        			revenueReportsBD.getDistrictDetails(revenueMgmtForm);
        		logger.debug("officeNameList-----"+list); 
				revenueMgmtForm.setDistrictList(list);
				request.setAttribute("districtlst",revenueMgmtForm);
				
				ArrayList officeTypeLst = 
					revenueReportsBD.getOfficeTypeDetails(revenueMgmtForm);
				logger.debug("officeNameList-----"+officeTypeLst); 
		        revenueMgmtForm.setOfficeTypeLst(officeTypeLst);
		        request.setAttribute("officetypelst",revenueMgmtForm);
		        
		        ArrayList officeNameList = 
		        	revenueReportsBD.getOfficeNameDetails(revenueMgmtForm);
	        	logger.debug("officeNameList-----"+officeNameList);        	
	        	revenueMgmtForm.setOfficeNameList(officeNameList);
	        	request.setAttribute("officenamelst",revenueMgmtForm);
    			revenueMgmtForm.getRevenueMgmtDTO().setDistrictId("Select");
    			revenueMgmtForm.getRevenueMgmtDTO().setOfficeId("Select");
    			revenueMgmtForm.getRevenueMgmtDTO().setOfficeTypeId("Select");
    			returnPage = RevenueMgmtConstants.SERVICE_FEE_CREATE;
    		}
    	}
    	if(page.equalsIgnoreCase(
    			RevenueMgmtConstants.LBL_SERVICE_FEE_CREATE_RESULT_PAGE)){
    		if(actionID.equalsIgnoreCase(
    				RevenueMgmtConstants.LBL_SERVICE_FEE_CREATE_RESULT_ACTIONID)){
    			returnPage = RevenueMgmtConstants.SERVICE_FEE_CREATE;
    		}
    		if(actionID.equalsIgnoreCase(RevenueMgmtConstants.LBL_SIGNOUT)){
				returnPage = RevenueMgmtConstants.LBL_SIGNOUT;
    		}
    	}
    	if(page.equalsIgnoreCase(
    			RevenueMgmtConstants.LBL_SERVICE_FEE_DETAILS_PAGE)){
    		if(actionID.equalsIgnoreCase(
    				RevenueMgmtConstants.LBL_SERVICE_FEE_DETAILS_ACTIONID)){
    			logger.debug("in service fee Details revenuemgmt action");
    			String districtId = revenueMgmtForm.getRevenueMgmtDTO().getDistrictId();
    			logger.debug("districtId===="+districtId);
    			session.setAttribute("DistrictId", districtId);
    			logger.debug("session.getAttribute(DistrictId)**"+session.getAttribute("DistrictId"));
    			ArrayList serviceFeeList = 
    				revenueReportsBD.getServiceFeeDetails(revenueMgmtForm);
    			logger.debug("serviceFeeList size===="+serviceFeeList.size()); 
    			if(serviceFeeList.size()==0){
    				logger.debug(" in if loop%%%%%%%%%%%%%%%5");
    				messages.add("message", 
    						new ActionMessage("no.paramId.found"));
    				saveMessages(request, messages);
    				request.setAttribute("ServiceFeeList",serviceFeeList);
    				returnPage = RevenueMgmtConstants.SERVICE_FEE_SUCCESS;
    			}
    			else{
        			revenueMgmtForm.setServiceFeeList(serviceFeeList);
        			session.setAttribute("viewServiceFeeList",serviceFeeList);
        	        returnPage = RevenueMgmtConstants.LBL_SERVICE_FEE_VIEW_DETAILS;        				
    			}
    		}
    		if(actionID.equalsIgnoreCase(RevenueMgmtConstants.LBL_CANCEL)){
    			logger.debug("your are in cancel service fee details action");
    			returnPage = RevenueMgmtConstants.LBL_CANCEL;
    		}
    		if(actionID.equalsIgnoreCase(RevenueMgmtConstants.LBL_SIGNOUT)){
				returnPage = RevenueMgmtConstants.LBL_SIGNOUT;
    		}
    		if(actionID.equalsIgnoreCase(RevenueMgmtConstants.LBL_RESET)){
    			ArrayList list = 
        			revenueReportsBD.getDistrictDetails(revenueMgmtForm);
        		logger.debug("officeNameList-----"+list); 
				revenueMgmtForm.setDistrictList(list);
				request.setAttribute("districtlst",revenueMgmtForm);
				
				ArrayList officeTypeLst = 
					revenueReportsBD.getOfficeTypeDetails(revenueMgmtForm);
				logger.debug("officeNameList-----"+officeTypeLst); 
		        revenueMgmtForm.setOfficeTypeLst(officeTypeLst);
		        request.setAttribute("officetypelst",revenueMgmtForm);
		        
		        ArrayList officeNameList = 
		        	revenueReportsBD.getOfficeNameDetails(revenueMgmtForm);
	        	logger.debug("officeNameList-----"+officeNameList);        	
	        	revenueMgmtForm.setOfficeNameList(officeNameList);
	        	request.setAttribute("officenamelst",revenueMgmtForm);
    			revenueMgmtForm.getRevenueMgmtDTO().setDistrictId("Select");
    			revenueMgmtForm.getRevenueMgmtDTO().setOfficeId("Select");
    			revenueMgmtForm.getRevenueMgmtDTO().setOfficeTypeId("Select");
    			returnPage = RevenueMgmtConstants.SERVICE_FEE_SUCCESS;
    		}
    	}
    	if(page.equalsIgnoreCase(
    			RevenueMgmtConstants.LBL_SERVICE_FEE_VIEW_DETAILS)){
    		if(actionID.equalsIgnoreCase(RevenueMgmtConstants.LBL_BACK)){
    			returnPage = RevenueMgmtConstants.SERVICE_FEE_SUCCESS;        			
    		}
    		if(actionID.equalsIgnoreCase(RevenueMgmtConstants.LBL_PRINT)){
    			logger.debug("session.getAttribute(DistrictId)**"
    					+session.getAttribute("DistrictId"));
    			revenueMgmtForm.getRevenueMgmtDTO().setDistrictId(
    					(String)session.getAttribute("DistrictId"));
    			logger.debug("districtId in service view search print===="
    					+revenueMgmtForm.getRevenueMgmtDTO().getDistrictId());
    			ArrayList serviceFeeList = 
    				revenueReportsBD.getServiceFeeDetails(revenueMgmtForm);
    			logger.debug("serviceFeeList size=="+serviceFeeList.size());
    			revenueMgmtForm.setServiceFeeList(serviceFeeList);
    			session.setAttribute("viewServiceFeeList",serviceFeeList);
    			returnPage = 
    				RevenueMgmtConstants.LBL_SERVICE_FEE_PRINT_DETAILS; 
    		}     		       		
    		if(actionID.equalsIgnoreCase(RevenueMgmtConstants.LBL_SIGNOUT)){
				returnPage = RevenueMgmtConstants.LBL_SIGNOUT;
    		}
    	}
    	if(page.equalsIgnoreCase(
    			RevenueMgmtConstants.LBL_SERVICE_FEE_PRINT_DETAILS)){
    		if(actionID.equalsIgnoreCase(
    				RevenueMgmtConstants.LBL_BACK)){
    			logger.debug("session.getAttribute(DistrictId)**"
    					+session.getAttribute("DistrictId"));
    			revenueMgmtForm.getRevenueMgmtDTO().setDistrictId(
    					(String)session.getAttribute("DistrictId"));
    			logger.debug("districtId in revrecon search print===="
    					+revenueMgmtForm.getRevenueMgmtDTO().getDistrictId());
    			ArrayList serviceFeeList = 
    				revenueReportsBD.getServiceFeeDetails(revenueMgmtForm);         
    			revenueMgmtForm.setServiceFeeList(serviceFeeList); 
    			session.setAttribute("viewServiceFeeList",serviceFeeList);
    			returnPage = 
    				RevenueMgmtConstants.LBL_SERVICE_FEE_VIEW_DETAILS;      			
    		}
    		if(actionID.equalsIgnoreCase(RevenueMgmtConstants.LBL_SIGNOUT)){
				returnPage = RevenueMgmtConstants.LBL_SIGNOUT;
    		}
    	}        	
    	if(page.equalsIgnoreCase(
    			RevenueMgmtConstants.LBL_SERVICE_FEE_UPDATE_PAGE)){
    		if(actionID.equalsIgnoreCase(
    				RevenueMgmtConstants.LBL_SERVICE_FEE_UPDATE_ACTIONID)){
    			logger.debug(
    					"before MODIFY service fee Details revenuemgmt action");
    			boolean flag = 
    				revenueReportsBD.updateServiceFeeDetails(revenueMgmtForm,roleId,funId,userId);
    			logger.debug(
    					"after MODIFY service fee Details, flag value="+flag);
    			if(flag == true){
    				messages.add("updateMsg", 
    						new ActionMessage("date.updated.successfully"));
    				saveMessages(request, messages);
    				request.setAttribute("UpdateDetails",revenueReportsBD);
    				logger.debug(
    						"before returnpage");
    				returnPage = 
    					RevenueMgmtConstants.LBL_SERVICE_FEE_UPDATE_RESULT_PAGE;
    				logger.debug("in if loop");
    			}else{
    				logger.debug("in else loop");
    				messages.add("msg", new ActionMessage("no.record.updated"));
    				saveMessages(request, messages);
    				request.setAttribute("UpdateDetails",revenueReportsBD);
    				returnPage = 
    					RevenueMgmtConstants.SERVICE_FEE_PARAM_ID_DETAILS;
    			}
    		}        		     			
    		if(actionID.equalsIgnoreCase(RevenueMgmtConstants.LBL_SIGNOUT)){
				returnPage = RevenueMgmtConstants.LBL_SIGNOUT;
    		}
    	}
    	if(page.equalsIgnoreCase(
    			RevenueMgmtConstants.LBL_SERVICE_FEE_UPDATE_RESULT_PAGE)){
    		if(actionID.equalsIgnoreCase(
    				RevenueMgmtConstants.LBL_SERVICE_FEE_UPDATE_RESULT_ACTIONID)){
    			returnPage = RevenueMgmtConstants.SERVICE_FEE_SUCCESS;
    		}
    		if(actionID.equalsIgnoreCase(RevenueMgmtConstants.LBL_SIGNOUT)){
				returnPage = RevenueMgmtConstants.LBL_SIGNOUT;
    		}
    	}
    	
    	if(request.getParameter("paramIdValue")!=null){
    		String paramId = request.getParameter("paramIdValue");
    		logger.debug("paramId in param Id value===="+paramId);
			ArrayList paramIdList = 
				revenueReportsBD.getParamIdDetails(paramId);
			logger.debug("paramIdList in revmgmt action======="+paramIdList);
			revenueMgmtForm.setServiceFeeList(paramIdList);
			returnPage = RevenueMgmtConstants.SERVICE_FEE_PARAM_ID_DETAILS;
		}
    } 
    logger.debug("you are in action class if part end");
    return mapping.findForward(returnPage);
  }
}
