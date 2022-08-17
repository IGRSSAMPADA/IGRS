package com.wipro.igrs.officedeptmapping.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.officedeptmapping.bd.OfficeDeptMappingBD;
import com.wipro.igrs.officedeptmapping.form.OfficeDeptMapMasterForm;

public class DeleteOfficeDeptMap extends BaseAction {

	OfficeDeptMappingBD officeDeptBD = new OfficeDeptMappingBD();

	public DeleteOfficeDeptMap() {
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {

		OfficeDeptMapMasterForm theForm = (OfficeDeptMapMasterForm) form;

		officeDeptBD.deleteOfficeDeptMaps(theForm.getOfficeDeptIds());

		return mapping.findForward("success");
	}

}