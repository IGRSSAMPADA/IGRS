/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   RTIAssignAction.java
 * Author      :   Samuel Prabhakaran
 * Description :   Represents the AssignAction Class for HO Assign DRO.
 * ----------------------------------------------------------------------------
 * Version         Last Modified By     Last Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Samuel Prabhakaran       28th Dec, 2007	 		
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.rti.action;



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
import com.wipro.igrs.rti.bd.RTIBD;


public class RTIAssignAction extends BaseAction {
    public RTIAssignAction() {
    }

    public ActionForward execute(ActionMapping mapping, ActionForm form, 
                                 HttpServletRequest request, 
                                 HttpServletResponse response,HttpSession session) throws ServletException, 
                                                                      IOException {
    	
    	
	//HttpSession session=request.getSession();
	//user role,function id,user id
	String paramIds[] = new String[3];
		paramIds[0] = (String)session.getAttribute("roleId");
		paramIds[1] = (String)session.getAttribute("functionId");
		paramIds[2] = (String)session.getAttribute("UserId");
    	RTIBD bd=new RTIBD();
    	String id=request.getParameter("rtiID");
    	ArrayList feelist=bd.getReqFee(id,paramIds);
    	session.setAttribute("feelist",feelist);	
     
        return mapping.findForward("rtiassign02");
        //return mapping.findForward("failure");
    }
}


