package com.wipro.igrs.formmptc27.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.formmptc27.bd.FormMPTC27BD;
import com.wipro.igrs.formmptc27.form.FormMPTC27Bean;

public class FormMPTC27Action extends BaseAction {
    
	FormMPTC27BD formMPTC27BD = new FormMPTC27BD();

    
    public FormMPTC27Action() {
    }
    
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {

    	FormMPTC27Bean bean = (FormMPTC27Bean)form;
    	
    	ArrayList allIds = formMPTC27BD.getAllIds();
    	
    	bean.setAllEmpId(allIds);
    	bean.setEmpId(null);
    	bean.setSelectEmpList(new ArrayList());
        return mapping.findForward("formMPTC27Page");
    }

}