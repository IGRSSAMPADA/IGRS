package com.wipro.igrs.publicOff.action;
/**
 * ===========================================================================
 * File           :   PublicOffAction.java
 * Description    :   Represents the Action Class

 * Author         :   Pavani Param
 * Created Date   :   Aug 23, 2008

 * ===========================================================================
 */
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.publicOff.bd.PublicOffBD;
import com.wipro.igrs.publicOff.form.PublicOffForm;
public class PublicOffAction extends BaseAction {
    
	private Logger logger = 
		(Logger) Logger.getLogger(PublicOffAction.class);
    public ActionForward execute(ActionMapping mapping, ActionForm form, 
                                 HttpServletRequest request, 
                                 HttpServletResponse response,HttpSession session) throws IOException, 
                                                                      ServletException {
        
        logger.info("  In action for getting form object--> " + form);
        String FORWARD_JSP = "pubcre";
        PublicOffForm pubForm  =  (PublicOffForm)form;
        PublicOffBD objBd = new PublicOffBD();
        ArrayList deptList = null;
        boolean boo = false;
        String regId = (String)request.getParameter("regId");
        String forwardTitle = (String)request.getParameter("param");
        logger.info("Action Class....regId=="+regId);
        try
        {
           if(regId != null)
           {
           deptList = objBd.getDeptList();
           pubForm.setDeptList(deptList);
     	   pubForm = objBd.getUserDet(pubForm);
     	   FORWARD_JSP  =  "pubcre";
           }
       if(forwardTitle != null)
       if(forwardTitle.equalsIgnoreCase("view"))
       {        
    	   boo = objBd.updateActDet(pubForm.getActStatus(),pubForm.getDrRemarks(),pubForm.getRegId());
    	   logger.info("Action Class..........boo=="+boo);
    	   FORWARD_JSP  =  "pubView";
        }else  if(forwardTitle.equalsIgnoreCase("conf"))
        {
        	logger.info("Action Class..........pubForm.getDrStatus()=="+pubForm.getDrStatus());
        	if(pubForm.getDrStatus().equalsIgnoreCase("A"))
        	{
        		boo = objBd.savePoDet(pubForm);
        	}
        	FORWARD_JSP  =  "pubConf";
        	
        }       
      
     logger.info("Action Class..........FORWARD_JSP=="+FORWARD_JSP);
     logger.info("Action Class..........pageTitle==="+pubForm.getPageTitle());
     session.setAttribute("pubForm",pubForm);
        }
       catch(Exception e)
        {
    	   logger.debug("Exception in  PublicOffAction Class..........==="+e);
        }
         return mapping.findForward(FORWARD_JSP);
    }
}   
