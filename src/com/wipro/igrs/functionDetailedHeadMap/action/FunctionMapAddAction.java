package com.wipro.igrs.functionDetailedHeadMap.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.functionDetailedHeadMap.bd.FunctionMapBD;
import com.wipro.igrs.functionDetailedHeadMap.exception.FunctionMapException;
import com.wipro.igrs.functionDetailedHeadMap.form.FunctionMapAdd;

public class FunctionMapAddAction extends BaseAction  {
    


    
    public FunctionMapAddAction() {
    }
    
	public ActionForward execute(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response,HttpSession session)throws Exception{
    	ActionForward actionForward=mapping.findForward("functionMapPrepareAddPageAction");
    	 ActionErrors errors =new ActionErrors();
    	if(isCancelled(request))
  	   {
    		actionForward=  mapping.findForward("functionMapListAll");
  	   }
    	else
    	{
    	FunctionMapAdd functionMapAdd=(FunctionMapAdd)form;
         FunctionMapBD refBd=new FunctionMapBD();
         try
         {
        String functionId=functionMapAdd.getSelectedFunc();
        String headId=functionMapAdd.getSelectedHead();
       
        if(!(headId.equals("0")) && !(functionId.equals("0")))
        {
        refBd.insertFunctionMap(functionId,headId);
        
         actionForward=mapping.findForward("successAdd");
        }
         }
         catch(FunctionMapException ex)
         {	
        	
     		
			 errors.add("recordExist",new ActionError("error.functionMap.recordExist"));
			 saveErrors(request, errors);
             
         }

    	}
    	return actionForward;
    }

}