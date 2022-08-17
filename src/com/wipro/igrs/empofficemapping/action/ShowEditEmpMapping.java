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

public class ShowEditEmpMapping extends BaseAction {

	private EmpOfficeMappingBD empOfficeMappingBD = new EmpOfficeMappingBD();
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		// TODO Auto-generated method stub
		EmpOfficeMappingForm bean = (EmpOfficeMappingForm) form;
		String empId = request.getParameter("mappingEmpId");
		String empName = empOfficeMappingBD.getEmpName(empId);
		
		
		EmpOfficeMappingDTO dto = new EmpOfficeMappingDTO();
		dto.setEmpId(empId);
		EmpOfficeMappingDTO mappingDto = empOfficeMappingBD.getEmpMappingById(dto);
		
		
		bean.setEmpId(empId);
		bean.setEmpName(empName);
		bean.setOfficeId(mappingDto.getOfficeId());
		bean.setDeptId(mappingDto.getDeptId());
		bean.setRoleId(mappingDto.getRoleId());
		
		bean.setAllDepts(empOfficeMappingBD.getAllDepts());
		bean.setAllOffices(empOfficeMappingBD.getAllOffices());
		bean.setAllRoles(empOfficeMappingBD.getAllRoles());
		
		return mapping.findForward("editEmpOfficeMapping");
	}

}
