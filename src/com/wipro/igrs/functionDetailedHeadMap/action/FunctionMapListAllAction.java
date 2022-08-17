package com.wipro.igrs.functionDetailedHeadMap.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.functionDetailedHeadMap.bd.FunctionMapBD;
import com.wipro.igrs.functionDetailedHeadMap.form.FunctionMapListAll;

public class FunctionMapListAllAction extends BaseAction {

	public FunctionMapListAllAction() {
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		FunctionMapListAll listAllForm = (FunctionMapListAll) form;
		FunctionMapBD functionMapBD = new FunctionMapBD();
		ArrayList functions = functionMapBD.retrieveAllFunctionMap();

		listAllForm.setFunctions(functionMapBD.retrieveAllFunctionMap());
		return mapping.findForward("functionMap_master");
	}

}