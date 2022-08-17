package com.wipro.igrs.municipalbodymaster.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.municipalbodymaster.bd.MunicipalBodyBD;
import com.wipro.igrs.municipalbodymaster.dto.MunicipalBodyDTO;
import com.wipro.igrs.municipalbodymaster.form.MunicipalBodyForm;

public class ShowEditMunicipalPageAction extends BaseAction {

	private MunicipalBodyBD municipalBodyBD = new MunicipalBodyBD();
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		MunicipalBodyForm bean = (MunicipalBodyForm) form;
		String municipalId = bean.getMunicipalBodyId();
		MunicipalBodyDTO municipalDTO = new MunicipalBodyDTO();
		municipalDTO.setMunicipalBodyId(municipalId);
		MunicipalBodyDTO editedMunicipal = municipalBodyBD.getMunicipalBodyById(municipalDTO);
		//bean.setMunicipalBodyId(municipalBodyId);
		bean.setMunicipalBodyName(editedMunicipal.getMunicipalBodyName());
		bean.setMunicipalBodyDesc(editedMunicipal.getMunicipalBodyDesc());
		bean.setMunicipalBodyStatus(editedMunicipal.getMunicipalBodyStatus());
		return mapping.findForward("editMunicipalPage");
	}

}
