/******************************************************************************
 * File Name     : PreCreateDeedMaster.java
 * Author        : Sayed Taha
 * Date          : 2008/08/27
 * Description   : Represents the Pre Create Deed Master Screen
 *
 * Modification Log:
 *  Ver.No.  Date        Author                      Modification
 *  0.0a     2008/08/27  Sayed Taha                  
 *****************************************************************************/
package com.wipro.igrs.deedparammaster.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.deedparammaster.bd.DeedParamMasterBD;
import com.wipro.igrs.deedparammaster.dto.DeedParamMasterDTO;
import com.wipro.igrs.deedparammaster.form.DeedParamMasterForm;

public class ViewAllDeedParamMasterAction extends BaseAction{


	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		DeedParamMasterForm deedForm=(DeedParamMasterForm)form;
		DeedParamMasterBD deedparambd = new DeedParamMasterBD();
		/* viewALL Deed Param's master */
		deedForm.setDeedparamList(deedparambd.getAllDeedParamDetails());
		deedForm.setSelectedDeedParam(null);
		request.setAttribute("List", deedForm.getDeedparamList());
		return mapping.findForward("view");	
	}
}
