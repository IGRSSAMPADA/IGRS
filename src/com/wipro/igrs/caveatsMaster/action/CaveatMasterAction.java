package com.wipro.igrs.caveatsMaster.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.caveatsMaster.bd.CaveatsDelegate;
import com.wipro.igrs.caveatsMaster.form.CaveatsForm;

public class CaveatMasterAction extends BaseAction {
    


    
    public CaveatMasterAction() {
    }
    
    public ActionForward execute(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response,HttpSession session) throws Exception {
        
    	CaveatsDelegate bdRef=new CaveatsDelegate();
    	((CaveatsForm)form).setCaveatsData(bdRef.listCaveats());
    	//request.setAttribute("data", bdRef.listCaveats());
    	return mapping.findForward("caveats_Master");
    }

}