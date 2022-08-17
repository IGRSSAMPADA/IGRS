package com.wipro.igrs.caveatsMaster.action;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.caveatsMaster.dto.CaveatsDTO;
import com.wipro.igrs.caveatsMaster.form.CaveatsForm;

public class CaveatActionConfirm extends BaseAction {
    /**This is the main action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     */
	private Logger logger = 
		(Logger) Logger.getLogger(CaveatActionConfirm.class);

    public ActionForward execute(ActionMapping mapping, ActionForm form, 
                                 HttpServletRequest request, 
                                 HttpServletResponse response,HttpSession session) throws IOException, 
                                                                      ServletException,Exception {
    	logger.debug("WE ARE IN CaveatActionConfirm Debug");
    	logger.info("WE ARE IN  CaveatActionConfirm INFO");
    	String FORWAD_SUCCESS="";
        try{
        	CaveatsForm fm=(CaveatsForm)form;
        	CaveatsDTO cDTO=new CaveatsDTO();
        	cDTO=fm.getCaveatsDTO();
        	if("create".equalsIgnoreCase(request.getParameter("pageName")))
        	{
        		String str="";
        		session.setAttribute("suppleDetails",cDTO);
         	    if(session.getAttribute("frwdedByEdit")!=null)
         	    {
         	    	str=(String)session.getAttribute("frwdedByEdit");
         	    }
         	    if(str.equalsIgnoreCase("yes"))
         	    {
         	    	FORWAD_SUCCESS="ConfirmScreen";
         	    }
         	    else
         	    	FORWAD_SUCCESS="propertyForwardPath";
        	}
            else if("dispCaveat".equalsIgnoreCase(request.getParameter("pageName")))
        	{
        		logger.info("Inside confirmDetails Action");
        		cDTO=(CaveatsDTO)session.getAttribute("suppleDetails");
        		fm.setCaveatsDTO(cDTO);
        		FORWAD_SUCCESS="ConfirmScreen";
        	}

            }catch(Exception e)
            {
                logger.error(e);
                FORWAD_SUCCESS="failure";
            }    
            return mapping.findForward(FORWAD_SUCCESS);
    }
}
