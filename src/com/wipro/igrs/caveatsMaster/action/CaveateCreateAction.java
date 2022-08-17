package com.wipro.igrs.caveatsMaster.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.caveatsMaster.bd.CaveatsDelegate;
import com.wipro.igrs.caveatsMaster.exception.CaveatsException;
import com.wipro.igrs.caveatsMaster.form.CaveatAddForm;

public class CaveateCreateAction extends BaseAction {

	// private boolean flag=false;
	private Logger logger = Logger.getLogger(CaveateCreateAction.class);
	public CaveateCreateAction() {

	}

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,HttpSession session)
			throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		String flag = (String) request.getParameter("flag");
		if (isCancelled(request)) {
			forward = mapping.findForward("caveatMasterAction");
		} else {
			if (flag == null) {

				logger.debug("flag true");

				if (((CaveatAddForm) form).getCaveatsName().equals("")
						|| ((CaveatAddForm) form).getCaveatsName() == null
						|| ((CaveatAddForm) form).getCaveatsDesc().equals("")
						|| ((CaveatAddForm) form).getCaveatsDesc() == null) {
					if (((CaveatAddForm) form).getCaveatsName().equals("")
							|| ((CaveatAddForm) form).getCaveatsName() == null) {
						errors.add("nameError", new ActionError(
								"error.caveat.required"));
						saveErrors(request, errors);
						forward = mapping.findForward("createCaveats");

					}
					if (((CaveatAddForm) form).getCaveatsDesc().equals("")
							|| ((CaveatAddForm) form).getCaveatsDesc() == null) {
						errors.add("descError", new ActionError(
								"error.caveat.required"));
						saveErrors(request, errors);
						forward = mapping.findForward("createCaveats");
					}
				}

				else {
					// flag=false;
					CaveatsDelegate caveatsDelegate = new CaveatsDelegate();
					String[] param = new String[6];
					param[0] = ((CaveatAddForm) form).getCaveatsName();
					param[1] = ((CaveatAddForm) form).getCaveatsDesc();
					param[2] = new String("A");
					param[3] = (String) session.getAttribute(
							"UserId");
					param[4] = (String) session.getAttribute(
							"UserId");
					param[5] = new String("Y");
					try {
						caveatsDelegate.insertCaveatsMasterBD(param);

						forward = mapping.findForward("caveatMasterAction");
					} catch (CaveatsException ce) {
						errors.add("nameError", new ActionError(
								"error.caveat.nameExist"));
						saveErrors(request, errors);
						forward = mapping.findForward("createCaveats");
					}

				}
			} else {
				// flag=true;
				forward = mapping.findForward("createCaveats");
			}

		}
		return forward;

	}

}