package com.wipro.igrs.officedeptmapping.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.officedeptmapping.bd.OfficeDeptMappingBD;
import com.wipro.igrs.officedeptmapping.dto.OfficeDeptDTO;
import com.wipro.igrs.officedeptmapping.form.OfficeDeptMapForm;

public class EditOfficeDeptAction extends BaseAction {

	OfficeDeptMappingBD officeDeptBD = new OfficeDeptMappingBD();

	public EditOfficeDeptAction() {
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {

		OfficeDeptDTO officeDeptDTO = ((OfficeDeptMapForm) form)
				.getOfficeDeptDTO();

		officeDeptBD.editOfficeDeptMap(officeDeptDTO);

		return mapping.findForward("success");
	}

}