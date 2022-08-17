/******************************************************************************
 * File Name     : TestPreEdit.java
 * Author        : Sayed Taha
 * Date          : 2008/08/27
 * Description   : Represents the Test Pre Edit
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

public class TestPreEdit extends BaseAction {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {

		((ModuleMasterForm) form).setId("82");
		request.setAttribute("editDeed", new String("arg1"));
		request.getSession().setAttribute("UserId", "Sico");
		return mapping.findForward("masterModule");
	}

}
