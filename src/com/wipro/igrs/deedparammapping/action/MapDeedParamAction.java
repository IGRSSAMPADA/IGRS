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

public class MapDeedParamAction extends BaseAction {
    

	DeedParamBD deedParamBD = new DeedParamBD();
    
    public MapDeedParamAction() {
    }
    
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
    	
    	DeedParamBean bean = (DeedParamBean)form;
    	
    	List allDeedTypeMaster = deedParamBD.getAllDeedTypeMaster();
    	List allDeedParamMaster = deedParamBD.getAllDeedParamMaster();
    	List allDeedInstument = deedParamBD.getAllDeedInstrument();
    	List allDeedExemption = deedParamBD.getAllDeedExemption();
    	
    	bean.setDeedTypeList(allDeedTypeMaster);
    	bean.setDeedParamMasterList(allDeedParamMaster);
    	//bean.setDeedType(null);
    	//bean.setDeedParamMaster(null);deedInstrument
    	bean.setDeedInstrumentList(allDeedInstument);
    	bean.setDeedExemptionList(allDeedExemption);
    	
        return mapping.findForward("mapDeedParamPage");
    }

}