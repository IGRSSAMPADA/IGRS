package com.wipro.igrs.functionDetailedHeadMap.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


import com.wipro.igrs.functionDetailedHeadMap.bd.FunctionMapBD;
import com.wipro.igrs.functionDetailedHeadMap.form.FunctionMapDelete;
import com.wipro.igrs.baseaction.action.BaseAction;

public class FunctionMapDeleteAction extends BaseAction  {
    


    
    public FunctionMapDeleteAction() {
    }
    
	public ActionForward execute(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response,HttpSession session)throws Exception{
     	FunctionMapBD functionMapBD=new FunctionMapBD();
     
     	    	String [] toBeDeleted=((FunctionMapDelete)form).getSelect();
     	    	
     	    	if(toBeDeleted!=null)
     	    	{
     	    	for(int i=0;i<toBeDeleted.length;i++)
     	    	{
     	    		System.out.println(toBeDeleted[i]);
     	    		functionMapBD.deleteFunctionMap(toBeDeleted[i]);
     	    	}
     	    	
     	    	}
     	    	return mapping.findForward("functionMapListAllAction");
    }

}