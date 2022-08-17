package com.wipro.igrs.castemaster.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.castemaster.bd.CasteBD;

public class ViewCastMasterAction extends BaseAction {
    

	private CasteBD casteBD;
    
    public ViewCastMasterAction() {
    	try {
    		casteBD = new CasteBD();
    	}
    	catch (Exception e) {
			e.printStackTrace();
		}
    }


	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		
		List allCastes = casteBD.getAllCastes();
        request.setAttribute("casteList", allCastes);
        
        return mapping.findForward("casteMasterPage");
	}

}