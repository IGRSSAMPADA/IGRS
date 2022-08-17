package com.wipro.igrs.deedparammapping.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.deedparammapping.bd.DeedParamBD;
import com.wipro.igrs.deedparammapping.form.DeedParamBean;

public class DeedParamAction extends BaseAction {
    

	DeedParamBD deedParamBD = new DeedParamBD();
    
    public DeedParamAction() {
    }
    
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {

    	DeedParamBean bean = (DeedParamBean) form;
    	List allDeedParamMapping = deedParamBD.getAllDeedParamMapping();
    	
    	request.setAttribute("allDeedParamMapping", allDeedParamMapping);
    	
    	
        return mapping.findForward("viewDeedParamMapping");
    }

}