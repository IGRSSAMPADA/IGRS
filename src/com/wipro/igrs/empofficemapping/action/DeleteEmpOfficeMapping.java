package com.wipro.igrs.empofficemapping.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.empofficemapping.bd.EmpOfficeMappingBD;
import com.wipro.igrs.empofficemapping.form.DeleteEmpOfficeMappingForm;
import com.wipro.igrs.municipalbodymaster.form.DeleteMunicipalForm;

public class DeleteEmpOfficeMapping extends BaseAction {
	
	private EmpOfficeMappingBD empOfficeMappingBD = new EmpOfficeMappingBD(); 

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		// TODO Auto-generated method stub
		DeleteEmpOfficeMappingForm bean = (DeleteEmpOfficeMappingForm) form;
		String[] selectedMunicipalsIds = bean.getSelectedMappingsIds();
		empOfficeMappingBD.deleteEmpOfficeMappings(selectedMunicipalsIds);
		return mapping.findForward("deleteEmpOfficeMappingSuccess");
	}

}
