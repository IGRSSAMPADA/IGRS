/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007
 *==============================================================================
 * File Name   :     AdvancePaymentsAction.java
 * Author      :     @author srenaag
 * Description :     Struts Action to load Service Provider  Names
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On   Modification
 * ----------------------------------------------------------------------------
 * 1.0            	sreelatha      Dec 24, 2007     Created.
 * ----------------------------------------------------------------------------
 */
package com.wipro.igrs.revenuemgmt.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.revenuemgmt.bd.RevenueMgmtBD;
import com.wipro.igrs.revenuemgmt.common.RevenueMgmtConstants;
import com.wipro.igrs.revenuemgmt.form.RevenueMgmtForm;

public class AdvancePaymentsAction extends BaseAction{
	 /**This is the main action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form, 
                                 HttpServletRequest request, 
                                 HttpServletResponse response,HttpSession session) throws IOException,ServletException {
    	System.out.println("you are in action class if part start");
    	RevenueMgmtForm revenueMgmtForm = (RevenueMgmtForm)form;
        //String actionID = advancePaymentsForm.getActionID();
        //System.out.println("actionID value:-"+actionID);
        String returnPage = "";
		String empty = null ;
        if(form!=null){        
        RevenueMgmtBD revenueReportsBD = new RevenueMgmtBD();         
        ArrayList spList = revenueReportsBD.getspDetails(revenueMgmtForm);
        revenueMgmtForm.setSpList(spList);
        request.setAttribute("advancepyments", revenueMgmtForm);
        returnPage = RevenueMgmtConstants.REVENUE_MANAGEMENT_SUCCESS;
        }
        //System.out.println("actionID value:-"+revenueMgmtForm.getActionID());
       /* if(actionID.equalsIgnoreCase(RevenueMgmtConstants.REVENUE_MANAGEMENT_CANCEL)){
        	System.out.println("in cancel action");
			returnPage = RevenueMgmtConstants.REVENUE_MANAGEMENT_CANCEL;
		}
        if(actionID.equalsIgnoreCase(RevenueMgmtConstants.REVENUE_MANAGEMENT_SIGNOUT)){
        	returnPage = RevenueMgmtConstants.REVENUE_MANAGEMENT_SIGNOUT;
        }	*/
        System.out.println("you are in action class if part end");
        return mapping.findForward( returnPage);
    
    }
}
