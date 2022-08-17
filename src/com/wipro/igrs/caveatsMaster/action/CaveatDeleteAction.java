package com.wipro.igrs.caveatsMaster.action;

import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.caveatsMaster.bd.CaveatsDelegate;

public class CaveatDeleteAction extends BaseAction {

	public CaveatDeleteAction() {
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,HttpSession session)
			throws Exception {
		CaveatsDelegate caveatsDelegate = new CaveatsDelegate();
		/*
		 * String id=request.getParameter("id"); 
		 * if(caveatsDelegate.deleteCaveatsMasterBD(id)) { return
		 * mapping.findForward("caveatMasterAction"); } else { return null; }
		 */
		String deleteCaveat = request.getParameter("deleteCaveat");
		StringTokenizer st = new StringTokenizer(deleteCaveat, "*");

		while (st.hasMoreTokens()) {
			caveatsDelegate.deleteCaveatsMasterBD(st.nextToken());
		}

		return mapping.findForward("caveatMasterAction");
	}
}