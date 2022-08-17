package com.wipro.igrs.propertytypeuom.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.propertytypeuom.bd.PropTypeMapBD;
import com.wipro.igrs.propertytypeuom.dto.MappingDataDTO;
import com.wipro.igrs.propertytypeuom.form.PropTypeMapForm;

public class UpdateMappingAction extends BaseAction{

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {

		PropTypeMapForm propForm=(PropTypeMapForm)form;
		PropTypeMapBD propBD=new PropTypeMapBD();
		MappingDataDTO dataDTO=new MappingDataDTO();
		
		
		dataDTO.setId(propForm.getId());
		dataDTO.setPropertyTypeID(propForm.getPropertyTypeName());
		dataDTO.setPropertyTypeL1ID(propForm.getPropertyTypeL1Name());
		dataDTO.setPropertyTypeL2ID(propForm.getPropertyTypeL2Name());
		dataDTO.setUomID(propForm.getUomName());
		
		
		if(propBD.updateMapping(dataDTO))
		{
			return mapping.findForward("successupdate");
		}
		else
		{
			ActionErrors errors=new ActionErrors();
			ActionError error = new ActionError("errors.uomMap.namefound");
			errors.add("existmapping",error);
			saveErrors(request, errors);
			return mapping.findForward("updatemappingpage");
		}
		
		
	}

}
