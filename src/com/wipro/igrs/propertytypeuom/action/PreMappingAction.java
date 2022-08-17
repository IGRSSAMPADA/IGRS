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
import com.wipro.igrs.propertytypeuom.form.PropTypeMapForm;

public class PreMappingAction extends BaseAction{

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		
		PropTypeMapBD propBD=new PropTypeMapBD();
		ArrayList returned =propBD.getAllDataToMap(0);
		((PropTypeMapForm)form).setProperty((ArrayList)returned.get(0));
		((PropTypeMapForm)form).setL1((ArrayList)returned.get(1));
		((PropTypeMapForm)form).setL2((ArrayList)returned.get(2));
		//((PropTypeMapForm)form).setL1(new ArrayList());
		//((PropTypeMapForm)form).setL2(new ArrayList());
		((PropTypeMapForm)form).setUom((ArrayList)returned.get(3));
		
		
		((PropTypeMapForm)form).setPropertyTypeName("");
		((PropTypeMapForm)form).setUomName("");
		((PropTypeMapForm)form).setPropertyTypeL1Name("");
		((PropTypeMapForm)form).setPropertyTypeL2Name("");
		
		return mapping.findForward("mappingpage");
	}

}
