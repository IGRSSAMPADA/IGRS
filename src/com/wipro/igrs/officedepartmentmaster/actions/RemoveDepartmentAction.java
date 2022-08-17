/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Egypt.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   RemoveDepartmentAction.java
 * Author      :   Sayed Taha  
 * Description :   Represents the Action Class for remove Department (Soft remove).
 *Created Date :   12 aug 2008 
 **/
package com.wipro.igrs.officedepartmentmaster.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.officedepartmentmaster.bd.OfficeDepartmentBD;
import com.wipro.igrs.officedepartmentmaster.form.DepartmentMasterForm;

public class RemoveDepartmentAction extends BaseAction{
	 /**This is the main action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     */
	private OfficeDepartmentBD bd=new OfficeDepartmentBD();
    private Logger logger = (Logger) Logger.getLogger(RemoveDepartmentAction.class);
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		DepartmentMasterForm deptForm = (DepartmentMasterForm) form;
		String []deleted=deptForm.getSelectedDept();
		for(int index=0;index<deleted.length;index++){
			bd.deleteDepartment(deleted[index]);
		}
		return mapping.findForward("departmentmaster");
	}

}
