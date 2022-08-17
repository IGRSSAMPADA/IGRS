package com.wipro.igrs.caveats.action;


import java.io.File;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.wipro.igrs.caveats.form.CaveatsForm;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.caveats.bd.CaveatsDelegate;
import com.wipro.igrs.caveats.dao.CaveatsDAO;
import com.wipro.igrs.caveats.dto.CaveatsDTO;

public class CaveatDashboard extends BaseAction {
	private Logger logger = 
		(Logger) Logger.getLogger(CaveatAction.class);
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request, 
            HttpServletResponse response,HttpSession session) throws Exception {
		logger.info("EStampAction -- In action for getting form object--> " );
    	logger.info("EStampAction -- In action for getting form object--> " + form);
        try{
        	CaveatsForm caveatFormBean= (CaveatsForm) form;
        }catch(Exception e){
        	e.printStackTrace();
        }
        String FORWARD_JSP = "success";
                
        
        logger.debug("FORWARD_JSP===="+FORWARD_JSP);
        return mapping.findForward(FORWARD_JSP);
	}
}
