package com.wipro.igrs.caveatsMaster.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.caveatsMaster.bd.CaveatsDelegate;
import com.wipro.igrs.caveatsMaster.dto.CaveatsDTO;
import com.wipro.igrs.caveatsMaster.exception.CaveatsException;
import com.wipro.igrs.caveatsMaster.form.CaveatEditForm;

public class CaveatEditAction extends BaseAction {
    

private String originalName;
private Logger logger = Logger.getLogger(CaveatEditAction.class);
    
    public CaveatEditAction() {
    }
    
    public ActionForward execute(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response,HttpSession session) throws Exception {
    	
    	    ActionErrors errors =new ActionErrors();
    	    ActionForward forward=new ActionForward();
    	 CaveatsDelegate caveatsDelegate=new CaveatsDelegate();
    	   if(isCancelled(request))
      	   {
      		 forward=  mapping.findForward("caveatMasterAction");
      	   }
    	   else
    	   {
     	if(((CaveatEditForm)form).getId()==null && !((CaveatEditForm)form).isFlag() )
     	{
     		originalName=request.getParameter("name");
    	
       CaveatsDTO dto=caveatsDelegate.retrieveCavietsByNameBD(originalName);
       ((CaveatEditForm)form).setCaveatsName(dto.getCaveatType());
       ((CaveatEditForm)form).setCaveatsDesc(dto.getCaveatDetails());
       ((CaveatEditForm)form).setCaveatsStatus(dto.getCaveatStatus());
       ((CaveatEditForm)form).setId(dto.getCaveatId());
       ((CaveatEditForm)form).setFlag(true);
       forward= mapping.findForward("editCaveats");
     	}
     	else
     	{
     		if(((CaveatEditForm)form).getCaveatsName()==null || ((CaveatEditForm)form).getCaveatsName().equals("") ||((CaveatEditForm)form).getCaveatsDesc()==null || ((CaveatEditForm)form).getCaveatsDesc().equals(""))
     		{
	     		if(((CaveatEditForm)form).getCaveatsName()==null || ((CaveatEditForm)form).getCaveatsName().equals(""))
	     		{
	     		   errors.add("nameError",new ActionError("error.caveat.required"));
	               saveErrors(request, errors);
	               forward=mapping.findForward("editCaveats");
	     		}
	     		
	     		if(((CaveatEditForm)form).getCaveatsDesc()==null || ((CaveatEditForm)form).getCaveatsDesc().equals(""))
	         	{
	     			errors.add("descError",new ActionError("error.caveat.required"));
	                saveErrors(request, errors);
	                forward=mapping.findForward("editCaveats");
	         	}
     		}
     		else
     		{
     			
     			   String[] param=new String [5];
     		       param[0]=((CaveatEditForm)form).getCaveatsName();//CAVEAT_TYPE_NAME=?
     		       param[1]=((CaveatEditForm)form).getCaveatsDesc();//CAVEAT_TYPE_DESC=?
     		       param[2]=((CaveatEditForm)form).getCaveatsStatus();//"D";//CAVEAT_TYPE_STATUS=?
     		       param[3]=(String)session.getAttribute("UserId");//UPDATE_BY=?
     		       param[4]=((CaveatEditForm)form).getId();//CAVEAT_TYPE_ID=?
     		       logger.debug(((CaveatEditForm)form).getId());
     		  
     		       try
     		       {
     		    	   caveatsDelegate.updateCaveatsMasterBD(param,originalName);
     		    	  forward= mapping.findForward("caveatMasterAction");
     		       }
     		       catch(CaveatsException ce)
     		       {
     		    	   errors.add("nameError",new ActionError("error.caveat.nameExist"));
     		           saveErrors(request, errors);
     		          forward= mapping.findForward("editCaveats");
     		       }
     		}
     			
     			
     		
    
  /*     if(caveatsDelegate.updateCaveatsMasterBD(param))
   	{
   		return  mapping.findForward("caveatMasterAction");
   		
   	}
   	else
   	{
   		 errors.add("nameError",new ActionError("error.caveat.nameExist"));
          saveErrors(request, errors);
       	return mapping.findForward("editCaveats");
   	}*/
       
       
     	}
    	   }
     	return forward;
    	
    }

}