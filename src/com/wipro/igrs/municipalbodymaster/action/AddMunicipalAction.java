package com.wipro.igrs.municipalbodymaster.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.municipalbodymaster.bd.MunicipalBodyBD;
import com.wipro.igrs.municipalbodymaster.dto.MunicipalBodyDTO;
import com.wipro.igrs.municipalbodymaster.exception.MunicipalNameAlreadyExistException;
import com.wipro.igrs.municipalbodymaster.form.MunicipalBodyForm;

public class AddMunicipalAction extends BaseAction {
	
	private MunicipalBodyBD municipalBodyBD = new MunicipalBodyBD();
	
	public AddMunicipalAction() {
		
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		MunicipalBodyForm bean = (MunicipalBodyForm) form;
		String userId = (String) session.getAttribute("UserId") ;
		
		MunicipalBodyDTO municipalDTO = new MunicipalBodyDTO();
		municipalDTO.setMunicipalBodyName(bean.getMunicipalBodyName());
		municipalDTO.setMunicipalBodyDesc(bean.getMunicipalBodyDesc());
		municipalDTO.setMunicipalBodyStatus(bean.getMunicipalBodyStatus());
		municipalDTO.setMunicipalBodyCreatedById(userId);
		municipalDTO.setMunicipalBodyModifiedById(userId);
		
		try {
			municipalBodyBD.addMunicipalBody(municipalDTO);
		} catch (MunicipalNameAlreadyExistException e) {
			ActionErrors errors = new ActionErrors();
			errors.add("municipalNameAlreadyExist", new ActionError("errors.municipalNameAlreadyExist"));
		    this.saveErrors(request, errors);
			return mapping.findForward("addMunicipalFailed");
		}
		
		return mapping.findForward("addMunicipalSuccess");
	}

}
