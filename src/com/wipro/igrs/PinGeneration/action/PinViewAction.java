package com.wipro.igrs.PinGeneration.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.PinGeneration.bd.PinGenerationBD;
import com.wipro.igrs.PinGeneration.form.PinGenerationForm;
import com.wipro.igrs.baseaction.action.BaseAction;


public class PinViewAction extends BaseAction {
    public PinViewAction() {
    }

    public ActionForward execute(ActionMapping mapping, ActionForm form, 
                                 HttpServletRequest request, 
                                 HttpServletResponse response,HttpSession session) throws ServletException, 
                                                                      IOException {
    	PinGenerationBD bd =new PinGenerationBD();
    	String pinReqNo =request.getParameter("pinReqTxnNo");
    	
    	ArrayList olist =bd.getOfficeId(pinReqNo);
    	
    	if(olist!=null){
    		ArrayList officeid =new ArrayList();
    		ArrayList list2=(ArrayList)olist.get(0);
    		String officeID =(String)list2.get(0);
    		PinGenerationForm pform =new PinGenerationForm();
    		pform.getPinGenerationDTO().setOfficeID(officeID);
    		officeid.add(pform);
    	//	HttpSession session=request.getSession();
    		session.setAttribute("officeid", officeid);
    		
    	}
    	
    	ArrayList list= bd.getUpdateDate(pinReqNo);
    	if(list!=null){
    		ArrayList updatedate =new ArrayList();
    		ArrayList list2=(ArrayList)list.get(0);
    		String updateDate =(String)list2.get(0);
    		PinGenerationForm pform =new PinGenerationForm();
    		pform.getPinGenerationDTO().setLastUpdateDate(updateDate);
    		updatedate.add(pform);
    	//	HttpSession session=request.getSession();
    		session.setAttribute("updatedate", updatedate);
    		
    	}
    	
   
		//bd.readBLOBToFileGet(response,pinReqNo );
        return mapping.findForward("pinview2");
        //return mapping.findForward("failure");
    }
}