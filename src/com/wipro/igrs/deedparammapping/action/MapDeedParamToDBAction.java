package com.wipro.igrs.deedparammapping.action;

import java.util.List;

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
import com.wipro.igrs.deedparammapping.dto.DeedParamDTO;
import com.wipro.igrs.deedparammapping.form.DeedParamBean;

public class MapDeedParamToDBAction extends BaseAction {
    

	DeedParamBD deedParamBD = new DeedParamBD();
    
    public MapDeedParamToDBAction() {
    }
    
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
    	
    	if(isCancelled(request)){
    		return mapping.findForward("deedParamAction");
    	}
    	ActionErrors errors = new ActionErrors();
    	
    	DeedParamBean bean = (DeedParamBean)form;
    	DeedParamDTO deedParamDTO = new DeedParamDTO();
    	deedParamDTO.setDeedTypeId(bean.getDeedType());
    	deedParamDTO.setExeptionId(bean.getDeedExemption());
    	deedParamDTO.setInstrumentId(bean.getDeedInstrument());
    	deedParamDTO.setStatus("A");
    	deedParamDTO.setDeedParamMasterId(bean.getDeedParamMaster());
    	//System.out.println(bean.getDeedType());
    	//System.out.println(bean.getDeedExemption());
    	//System.out.println(bean.getDeedInstrument());
    	//System.out.println(bean.getDeedParamMaster());
    	
    	if(deedParamBD.isExist(deedParamDTO)){
    		errors.add("deedParamAlreadyExist", new ActionError("error.deedParamAlreadyExist"));
    		saveErrors(request, errors);
    		return mapping.findForward("mapDeedParam");
    	}
    	boolean b = deedParamBD.mapTablesToDeepParam(deedParamDTO);
    	
    	if(b)
    		return mapping.findForward("success");
    	else 
    		return mapping.findForward("errorPage");
    }

}