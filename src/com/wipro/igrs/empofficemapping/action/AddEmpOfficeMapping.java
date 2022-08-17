package com.wipro.igrs.empofficemapping.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.empofficemapping.bd.EmpOfficeMappingBD;
import com.wipro.igrs.empofficemapping.dto.EmpOfficeMappingDTO;
import com.wipro.igrs.empofficemapping.form.EmpOfficeMappingForm;

public class AddEmpOfficeMapping extends BaseAction{
	
	private EmpOfficeMappingBD empOfficeMappingBD = new EmpOfficeMappingBD();
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		// TODO Auto-generated method stub
		EmpOfficeMappingForm bean = (EmpOfficeMappingForm) form;
		if (isCancelled(request)) {
			return mapping.findForward("addEmpMappingCanceld");
		}
		
		EmpOfficeMappingDTO dto = new EmpOfficeMappingDTO();
		dto.setEmpId(bean.getEmpId());
//		dto.setDeptId(bean.getDeptId());
//		dto.setRoleId(bean.getRoleId());
		dto.setOfficeId(bean.getOfficeId());
		empOfficeMappingBD.addEmpOfficeMapping(dto);
		return mapping.findForward("addEmpMappingSuccess");
	}

}
