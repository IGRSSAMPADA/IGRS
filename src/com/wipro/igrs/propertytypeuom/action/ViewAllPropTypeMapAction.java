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

public class ViewAllPropTypeMapAction extends BaseAction{

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {

		PropTypeMapBD propBD=new PropTypeMapBD();
		
		ArrayList alldata=propBD.getAllMappedData();
		request.setAttribute("alldata",alldata);
		
		return mapping.findForward("propertytypeuommaster");
		
		
	}
	

}
