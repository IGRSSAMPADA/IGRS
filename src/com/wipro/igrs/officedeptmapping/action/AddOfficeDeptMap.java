package com.wipro.igrs.officedeptmapping.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.officedeptmapping.bd.OfficeDeptMappingBD;
import com.wipro.igrs.officedeptmapping.exception.officeDeptMappingFoundException;
import com.wipro.igrs.officedeptmapping.form.OfficeDeptMapForm;

public class AddOfficeDeptMap extends BaseAction {

	OfficeDeptMappingBD officeDeptBD = new OfficeDeptMappingBD();
	ActionErrors errors = null;

	public AddOfficeDeptMap() {
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {

		try {
			OfficeDeptMapForm theForm = (OfficeDeptMapForm) form;

			if (theForm.getOfficeDeptDTO().getDeptId() == ""
					|| theForm.getOfficeDeptDTO().getOfficeId() == "") {
				errors = new ActionErrors();
				errors.add("officeDeptRequiredError", new ActionError(
						"error.officeDeptRequired"));
				saveErrors(request, errors);
				return mapping.findForward("officedeptmap");
			} else
				officeDeptBD.addOfficeDeptMap(theForm.getOfficeDeptDTO());
		} catch (officeDeptMappingFoundException e) {
			errors = new ActionErrors();
			errors.add("officeDeptError", new ActionError("error.officeDept"));
			saveErrors(request, errors);
			return mapping.findForward("officedeptmap");
		}

		return mapping.findForward("success");
	}

}