package com.wipro.igrs.partytypedeedmap.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.partytypedeedmap.bd.PartyTypeDeedMapBD;
import com.wipro.igrs.partytypedeedmap.dto.PartyTypeDeedMapDTO;
import com.wipro.igrs.partytypedeedmap.form.PartyTypeDeedMapForm;

public class PreCreatePartyTypeDeedMapAction extends BaseAction{

	PartyTypeDeedMapBD bd=PartyTypeDeedMapBD.getInstance();
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		((PartyTypeDeedMapForm)form).setDeedTypes(bd.getDeedTypes());
		((PartyTypeDeedMapForm)form).setPartyTypes(bd.getPartyTypes());
		((PartyTypeDeedMapForm)form).setDeedTypeID("");
		((PartyTypeDeedMapForm)form).setPartyTypeID("");
		return mapping.findForward("createpartydeedmap");
	}

}
