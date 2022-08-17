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

public class AddNewMappingAction extends BaseAction{

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {

		PropTypeMapBD propBD=new PropTypeMapBD();
		PropTypeMapForm addForm=(PropTypeMapForm)form;
		MappingDataDTO dataDTO=new MappingDataDTO();
		
		dataDTO.setPropertyTypeID(addForm.getPropertyTypeName());
		dataDTO.setPropertyTypeL1ID(addForm.getPropertyTypeL1Name());
		dataDTO.setPropertyTypeL2ID(addForm.getPropertyTypeL2Name());
		dataDTO.setUomID(addForm.getUomName());
		
		if(propBD.addNewMapping(dataDTO))
		{
			return mapping.findForward("successcreate");
		}
		else
		{
			ActionErrors errors=new ActionErrors();
			ActionError error = new ActionError("errors.uomMap.namefound");
			errors.add("existmapping",error);
			saveErrors(request, errors);
			return mapping.findForward("mappingpage");
		}
		
	}

}
