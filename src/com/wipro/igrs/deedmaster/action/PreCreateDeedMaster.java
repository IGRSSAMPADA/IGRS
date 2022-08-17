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
package com.wipro.igrs.deedmaster.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.deedmaster.form.ModuleMasterForm;

public class PreCreateDeedMaster extends BaseAction {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		request.getSession().setAttribute("UserId", new String("ADMIN"));
		// reseting the form bean values

		ModuleMasterForm deedForm = (ModuleMasterForm) form;
		deedForm.setCreatedDate("");
		deedForm.setDeedCategory("");
		deedForm.setName("");
		deedForm.setDeedPeriority("");
		deedForm.setDeedStatus("");
		deedForm.setDescription("");

		deedForm.setDeedlinkprop(false);
		deedForm.setDeedreq(false);
		deedForm.setDeedRor(false);
		deedForm.setPropertyValuerequired(false);
		deedForm.setDutyCelRequired(false);

		return mapping.findForward("deedTypeMaster");
	}
}
