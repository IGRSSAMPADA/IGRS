package com.wipro.igrs.propertytypeuom.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.propertytypeuom.bd.PropTypeMapBD;
import com.wipro.igrs.propertytypeuom.dto.MappingDataDTO;
import com.wipro.igrs.propertytypeuom.form.PropTypeMapForm;

public class PreUpdateAction extends BaseAction{

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		
		
		PropTypeMapBD propBD=new PropTypeMapBD();
		ArrayList returned =propBD.getAllDataToMap(1);
		((PropTypeMapForm)form).setProperty((ArrayList)returned.get(0));
		((PropTypeMapForm)form).setL1((ArrayList)returned.get(1));
		((PropTypeMapForm)form).setL2((ArrayList)returned.get(2));
		((PropTypeMapForm)form).setUom((ArrayList)returned.get(3));
		
		MappingDataDTO dataDTO=propBD.getMappingByID(request.getParameter("id"));
		
		((PropTypeMapForm)form).setId(request.getParameter("id"));
		((PropTypeMapForm)form).setPropertyTypeName(dataDTO.getPropertyTypeID());
		((PropTypeMapForm)form).setUomName(dataDTO.getUomID());
		((PropTypeMapForm)form).setPropertyTypeL1Name(dataDTO.getPropertyTypeL1ID());
		((PropTypeMapForm)form).setPropertyTypeL2Name(dataDTO.getPropertyTypeL2ID());
		
		return mapping.findForward("updatemappingpage");
	}

}
