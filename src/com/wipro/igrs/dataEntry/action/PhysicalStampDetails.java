package com.wipro.igrs.dataEntry.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.dataEntry.bd.DataEntryBd;
import com.wipro.igrs.dataEntry.dto.DataEntryDto;
import com.wipro.igrs.dataEntry.form.DataEntryForm;

public class PhysicalStampDetails extends Action

{
    public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request,HttpServletResponse response)
    {
	DataEntryForm entryForm =(DataEntryForm) form;
	       DataEntryDto dto = entryForm.getDto();
	       DataEntryBd bd = null;
	       boolean flag = false;
	      
	       String date = entryForm.getRegDate();
	       String dist = entryForm.getDistrict();
	    
	       ArrayList list = null;
	       String temp = entryForm.getTemp();
	     
	      
	       try
	       {
		  bd = new DataEntryBd();
		   dto.setStampList(bd.getPhysicalStamp(temp));
		   
		// flag = bd.getAdoptionDetails(dto,date,dist);
		   
	       }
	       catch(Exception ex)
	       {
		   ex.printStackTrace();
	       }
	       HttpSession session = request.getSession();
	       session.setAttribute("stamps", dto);
	
	
	
	
	return mapping.findForward("success");
    }
    

}
