/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Egypt.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   AddDepartmentMasterAction.java
 * Author      :   Sayed Taha  
 * Description :   Represents the Action Class for Add Department.
 *Created Date :   12 aug 2008 
 **/
package com.wipro.igrs.officedepartmentmaster.actions;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.officedepartmentmaster.bd.OfficeDepartmentBD;
import com.wipro.igrs.officedepartmentmaster.departmentexception.DepartmentNameAlreadyExists;
import com.wipro.igrs.officedepartmentmaster.dto.DeptDTO;
import com.wipro.igrs.officedepartmentmaster.form.DepartmentMasterForm;


public class AddDepartmentMasterAction extends BaseAction{
	 /**This is the main action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     */
	private OfficeDepartmentBD bd=new OfficeDepartmentBD();
    private Logger logger = (Logger) Logger.getLogger(AddDepartmentMasterAction.class);
    
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		ActionErrors error = new ActionErrors();
		logger.info("Department Action");
		String target = "success";
		String loginUserID=(String)session.getAttribute("UserId");
		DepartmentMasterForm deptForm = (DepartmentMasterForm) form;
		DeptDTO newDept=new DeptDTO();
		newDept.setDeptName(deptForm.getDeptName());
		newDept.setDeptDesc(deptForm.getDescription());
		newDept.setCreatedBy(loginUserID);
		newDept.setUpdatedBy(loginUserID);
		try{
			bd.addDepartment(newDept);
		}catch(DepartmentNameAlreadyExists e){
				error.add("deptnameerror", new ActionError("errors.namealreadyexists"));
				saveErrors(request, error);
				return mapping.findForward("createdepartment");
			
		}
		return mapping.findForward("success");
	}

}
