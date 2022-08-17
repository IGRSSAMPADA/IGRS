package com.wipro.igrs.functionDetailedHeadMap.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.functionDetailedHeadMap.bd.FunctionMapBD;
import com.wipro.igrs.functionDetailedHeadMap.form.FunctionMapAdd;
import com.wipro.igrs.baseaction.action.BaseAction;

public class FunctionMapPrepareAddPageAction extends BaseAction {
    


    
    public FunctionMapPrepareAddPageAction() {
    }
    
    public ActionForward execute(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response,HttpSession session) throws Exception{
       FunctionMapAdd functionMapAdd=(FunctionMapAdd)form;
       FunctionMapBD refBd=new FunctionMapBD();
       ArrayList functionData=refBd.retrieveAllFunctions();
       ArrayList headData=refBd.retrieveAllHeads();
     
       functionMapAdd.setFunctions(functionData);
       functionMapAdd.setHeads(headData);
       return mapping.findForward("functionMap_add");
       
    }

}