package com.wipro.igrs.officedeptmapping.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.officedepartmentmaster.bd.OfficeDepartmentBD;
import com.wipro.igrs.officedeptmapping.bd.OfficeDeptMappingBD;
import com.wipro.igrs.officedeptmapping.dto.OfficeDeptDTO;
import com.wipro.igrs.officedeptmapping.form.OfficeDeptMapForm;
import com.wipro.igrs.officemaster.bd.OfficeBD;

public class PreEditOfficeDept extends BaseAction {

	OfficeBD officeBD = new OfficeBD();
	OfficeDepartmentBD officeDeptBD = new OfficeDepartmentBD();
	OfficeDeptMappingBD officeDeptMappingBD = new OfficeDeptMappingBD();

	public PreEditOfficeDept() {
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {

		OfficeDeptDTO officeDeptDTO = officeDeptMappingBD
				.getOfficeDeptByID(request.getParameter("officeDeptId"));

		((OfficeDeptMapForm) form).setOfficeDeptDTO(officeDeptDTO);
		((OfficeDeptMapForm) form).setOffices((ArrayList) officeBD
				.getOfficeList());
		((OfficeDeptMapForm) form).setDepartments(officeDeptBD
				.getAllDepartments());

		return mapping.findForward("updateofficedeptmap");
	}

}