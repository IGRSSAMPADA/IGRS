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
import com.wipro.igrs.castemaster.dto.CasteDTO;
import com.wipro.igrs.castemaster.form.CasteForm;
import com.wipro.igrs.exception.IGRSException;

public class ViewEditCastePageAction extends BaseAction {
    

	CasteBD casteBD;

    
    public ViewEditCastePageAction() {
    	try {
			casteBD = new CasteBD();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		CasteForm bean = (CasteForm)form;
    	
    	CasteDTO casteDTO = casteBD.getCasteById(bean.getCasteId());
    	
    	if(casteDTO == null)
    		throw new IGRSException("no Caste found with the specified id : " + bean.getCasteId());
    	
    	bean.setCasteName(casteDTO.getName());
    	bean.setStatus(casteDTO.getStatus());
    	bean.setCategoryId(casteDTO.getCategory_id());
    	
    	
    	
    	List allCategories = casteBD.getAllCategories();
        
        request.setAttribute("allCategories", allCategories);
        
        return mapping.findForward("editCastePage");
	}

}