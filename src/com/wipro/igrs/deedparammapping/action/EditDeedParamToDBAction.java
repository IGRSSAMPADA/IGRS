package com.wipro.igrs.deedparammapping.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.deedparammapping.bd.DeedParamBD;
import com.wipro.igrs.deedparammapping.dao.DeedParamDAO;
import com.wipro.igrs.deedparammapping.dto.DeedParamDTO;
import com.wipro.igrs.deedparammapping.form.DeedParamBean;

public class EditDeedParamToDBAction extends BaseAction {
    

	DeedParamBD deedParamBD = new DeedParamBD(); 
    
    public EditDeedParamToDBAction() {
    }
    
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
       

    	if(isCancelled(request)){
    		return mapping.findForward("deedParamAction");
    	}
    	ActionErrors errors = new ActionErrors();
    	
    	String id = (String)session.getAttribute("DeedParamId");
    	//System.out.println("hiiiiiiiii edit" + id);
    	DeedParamBean bean = (DeedParamBean)form;
    	
    	DeedParamDTO deedParamDTO = new DeedParamDTO();
    	
    	deedParamDTO.setDeedTypeId(bean.getDeedType());
    	deedParamDTO.setExeptionId(bean.getDeedExemption());
    	deedParamDTO.setInstrumentId(bean.getDeedInstrument());
    	deedParamDTO.setDeedParamMasterId(bean.getDeedParamMaster());
    	deedParamDTO.setStatus("A");
    	deedParamDTO.setId(id);
    	
    	//System.out.println(bean.getDeedType());
    	//System.out.println(bean.getDeedExemption());
    	//System.out.println(bean.getDeedInstrument());
    	//System.out.println(bean.getDeedParamMaster());
    	
    	if(deedParamBD.isExist(deedParamDTO)){
    		errors.add("deedParamAlreadyExist", new ActionError("error.deedParamAlreadyExist"));
    		saveErrors(request, errors);
    		return mapping.findForward("editDeedParamAction");
    	}
    	boolean b = deedParamBD.updateMapTablesToDeepParam(deedParamDTO);
    	
    	//System.out.println(b);
    	
    	if(b)
    		 return mapping.findForward("editMapSuccess");
    	else 
    		return mapping.findForward("errorPage");
    	
       
    }

}