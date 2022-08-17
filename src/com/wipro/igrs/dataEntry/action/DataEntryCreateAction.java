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

public class DataEntryCreateAction extends Action 
{
    public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request,HttpServletResponse response)
    {
	DataEntryForm entryForm =(DataEntryForm) form;
	       DataEntryDto dto = entryForm.getDto();
	       DataEntryBd bd = null;
	       boolean flag = false;
	      HttpSession session = request.getSession();
	      String tempVal = entryForm.getTemp();  
	     
	       String date = entryForm.getRegDate();
	       String dist = entryForm.getDistrict();
	       String forward = null;
	       try
	       {
		   bd = new DataEntryBd();
		//   flag = bd.adoptionDeedDetails(dto,deed);
		   flag = bd.getAdoptionDetails(dto,date,dist);
	       }
	       catch(Exception ex)
	       {
		   ex.printStackTrace();
	       }
		
	
   
    if(flag)
	{
	forward = "success";
	
	}
    		return mapping.findForward(forward);
	
    }

}
