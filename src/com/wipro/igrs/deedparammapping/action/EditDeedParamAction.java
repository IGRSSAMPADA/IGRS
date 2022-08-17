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
import com.wipro.igrs.deedparammapping.dto.DeedParamDTO;
import com.wipro.igrs.deedparammapping.form.DeedParamBean;

public class EditDeedParamAction extends BaseAction {
    
	DeedParamBD deedParamBD = new DeedParamBD();

    
    public EditDeedParamAction() {
    }
    
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {

    	DeedParamBean bean = (DeedParamBean)form;
    	
    	String id = request.getParameter("id");
    	 
    	if(id == null){
    		id = (String)session.getAttribute("DeedParamId");
    	}
    	else{
    		session.setAttribute("DeedParamId", id);
    	}
    	
    	DeedParamDTO deedParamMappingById = deedParamBD.getDeedParamMappingById(id);
    	
    	List allDeedTypeMaster = deedParamBD.getAllDeedTypeMaster();
    	List allDeedParamMaster = deedParamBD.getAllDeedParamMaster();
    	
    	List allInstruments = deedParamBD.getDeedInstrumentByDeedTypeId(deedParamMappingById.getDeedTypeId());
    	List allExemptions = deedParamBD.getDeedExemptionByDeedTypeId(deedParamMappingById.getDeedTypeId());
    	if(allExemptions.isEmpty()){
    		allExemptions = deedParamBD.getExemptionsByInstrumentId(deedParamMappingById.getInstrumentId());
    	}
    	
    	bean.setDeedTypeList(allDeedTypeMaster);
    	bean.setDeedParamMasterList(allDeedParamMaster);
    	bean.setDeedExemptionList(allExemptions);
    	bean.setDeedInstrumentList(allInstruments);
    	//System.out.println("hiiiiiiiiiiiiiiiiiiiiiiiiiii" + deedParamMappingById.getExeptionId());
    	bean.setDeedExemption(deedParamMappingById.getExeptionId());
    	bean.setDeedInstrument(deedParamMappingById.getInstrumentId());
    	bean.setDeedParamMaster(deedParamMappingById.getDeedParamMasterId());
    	bean.setDeedType(deedParamMappingById.getDeedTypeId());
    	
    	return mapping.findForward("editParamParamPage");
    }

}