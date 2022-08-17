package com.wipro.igrs.deedparammapping.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.deedparammapping.bd.DeedParamBD;
import com.wipro.igrs.deedparammapping.form.DeedParamBean;

public class DeleteDeedParamAction extends BaseAction {
    
	DeedParamBD deedParamBD = new DeedParamBD();

    
    public DeleteDeedParamAction() {
    }
    
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {

    	DeedParamBean bean = (DeedParamBean)form;
    	String[] ids = bean.getSelections();
    	
    	boolean b = deedParamBD.deleteMapTablesToDeepParam(ids);
    	
    	if(b)
    		return mapping.findForward("deedParamAction");
    	else
    		return mapping.findForward("errorPage");
    }

}