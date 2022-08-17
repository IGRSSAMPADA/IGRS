package com.wipro.igrs.municipalbodymaster.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.municipalbodymaster.bd.MunicipalBodyBD;

public class ViewAllMunicipalBodiesAction extends BaseAction {
	
	private MunicipalBodyBD municipalBD = new MunicipalBodyBD();

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		ArrayList allMunicipalBodies = municipalBD.getAllMunicipalBodies();
		request.setAttribute("allMunicipalBodies", allMunicipalBodies);
		return mapping.findForward("viewAllMunicipalBodies");
	}

}
