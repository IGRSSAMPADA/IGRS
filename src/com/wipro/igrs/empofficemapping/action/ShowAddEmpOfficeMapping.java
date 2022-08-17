package com.wipro.igrs.empofficemapping.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.empofficemapping.bd.EmpOfficeMappingBD;
import com.wipro.igrs.empofficemapping.form.EmpOfficeMappingForm;

public class ShowAddEmpOfficeMapping extends BaseAction {

	private EmpOfficeMappingBD empOfficeMappingBD = new EmpOfficeMappingBD();
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		// TODO Auto-generated method stub
		if (isCancelled(request)) {
			return mapping.findForward("addEmpMappingCanceld");
		}
		
		EmpOfficeMappingForm bean = (EmpOfficeMappingForm) form;
		String empId = bean.getEmpId();
		
		ActionErrors errors = new ActionErrors();
		if (!empOfficeMappingBD.isEmpExist(empId)) {	
//			errors.add("addEmpOfficeMappingError", new ActionError("errors.empofficemap.empnotexist"));
//		    this.saveErrors(request, errors);
			request.setAttribute("isempAvail", "false");
		    return mapping.findForward("addEmpMappingFailed");
		} 
		if (empOfficeMappingBD.isMappingExist(empId)) {
//			errors.add("addEmpOfficeMappingError", new ActionError("errors.empofficemap.mappingalreadyexist"));
//		    this.saveErrors(request, errors);
		    
		    request.setAttribute("isMappingExist", "false");
			return mapping.findForward("addEmpMappingFailed");	
		}
		
		bean.setEmpName(empOfficeMappingBD.getEmpName(empId));
		bean.setDeptId(null);
		bean.setOfficeId(null);
		bean.setRoleId(null);
		bean.setAllDepts(empOfficeMappingBD.getAllDepts());
		bean.setAllOffices(empOfficeMappingBD.getAllOffices());
		bean.setAllRoles(empOfficeMappingBD.getAllRoles());
		
		return mapping.findForward("addEmpMapping");
	}
	

}
