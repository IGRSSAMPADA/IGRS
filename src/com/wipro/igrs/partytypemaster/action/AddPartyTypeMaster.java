package com.wipro.igrs.partytypemaster.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.partytypemaster.bd.PartyTypeMasterBD;
import com.wipro.igrs.partytypemaster.form.PartyTypeMasterForm;

public class AddPartyTypeMaster extends BaseAction {
    
	PartyTypeMasterBD partyTypeMasterBD = new PartyTypeMasterBD();

    
    public AddPartyTypeMaster() {
    }
    
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        // TODO: Write method body
    	
    	PartyTypeMasterForm bean = (PartyTypeMasterForm) form;
    	
    	bean.setName(null);
    	bean.setDesc(null);
    	bean.setStatus(null);
    	bean.setFuncVal(null);
    	bean.setDependVal(null);
    	
    	List allFunctions = partyTypeMasterBD.getAllFunctions();
    	List allDependences = partyTypeMasterBD.getAllDependences();
    	bean.setAllFunctions(allFunctions);
    	bean.setAllDependences(allDependences);
    	
        return mapping.findForward("addPartyTypeMasterPage");
    }

}