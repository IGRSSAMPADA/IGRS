package com.wipro.igrs.spotinspslabmaster.actions;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.spotinspslabmaster.bd.InspectionBD;

public class ApproveInspection extends BaseAction {
    
    public ActionForward execute(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response,HttpSession session) throws Exception {

    	InspectionBD insBD=new InspectionBD();
    	String language=(String)session.getAttribute("languageLocale");
		//cDto.setLanguage(language);
    	Vector returned=insBD.viewAllIActiveInspections();
    	request.setAttribute("allDTOs", returned);
    	return mapping.findForward("inspectionApprovemaster");
    }

}