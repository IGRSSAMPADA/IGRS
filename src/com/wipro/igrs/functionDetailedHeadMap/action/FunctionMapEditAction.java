package com.wipro.igrs.functionDetailedHeadMap.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.functionDetailedHeadMap.bd.FunctionMapBD;
import com.wipro.igrs.functionDetailedHeadMap.exception.FunctionMapException;
import com.wipro.igrs.functionDetailedHeadMap.form.FunctionMapEdit;
import com.wipro.igrs.baseaction.action.BaseAction;

public class FunctionMapEditAction extends  BaseAction{
    


    
    public FunctionMapEditAction() {
    }
    
    public ActionForward execute(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response,HttpSession session)throws Exception{
    	ActionForward actionForward=new ActionForward();
    	 ActionErrors errors =new ActionErrors();
    	if(isCancelled(request))
  	   {
    		actionForward=  mapping.findForward("functionMapListAll");
  	   }
    	else
    	{
    		
    		FunctionMapEdit functionMapEditForm=(FunctionMapEdit)form;

    		
    		FunctionMapBD refBd=new FunctionMapBD();
    		try
    		{
    		boolean editResult=refBd.updateFunctionMap(functionMapEditForm.getSelectedFunc(),functionMapEditForm.getSelectedHead(),(String)session.getAttribute("mapId"));
    		actionForward=  mapping.findForward("successEdit");
    		}
    		catch(FunctionMapException ex)
    		{
    			
    			 errors.add("recordExist",new ActionError("error.functionMap.recordExist"));
                saveErrors(request, errors);
    			actionForward=  mapping.findForward("functionMapPrepareEditPageAction");
    		}
    	}
    	return actionForward; 
    }

}