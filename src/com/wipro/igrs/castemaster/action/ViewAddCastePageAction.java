package com.wipro.igrs.castemaster.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.castemaster.bd.CasteBD;
import com.wipro.igrs.castemaster.dto.PersonCategoryDTO;

public class ViewAddCastePageAction extends BaseAction {
    

	CasteBD casteBD;
	
    
    public ViewAddCastePageAction() {
    	try {
    		casteBD = new CasteBD();
    	} catch (Exception e) {
			e.printStackTrace();
		}
    }


	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		
		List allCategories = casteBD.getAllCategories();
        
        request.setAttribute("allCategories", allCategories);
        
        return mapping.findForward("addCastePage");
	}

}