package com.wipro.igrs.municipalbodymaster.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.municipalbodymaster.bd.MunicipalBodyBD;
import com.wipro.igrs.municipalbodymaster.form.DeleteMunicipalForm;

public class DeleteMunicipalBodyAction extends BaseAction {

	private MunicipalBodyBD municipalBodyBD = new MunicipalBodyBD();
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		DeleteMunicipalForm bean = (DeleteMunicipalForm) form;
		String[] selectedMunicipalsIds = bean.getSelectedMunicipalIds();
		municipalBodyBD.deleteMunicipalBodies(selectedMunicipalsIds);
		return mapping.findForward("deleteMunicipalSuccess");
	}

}
