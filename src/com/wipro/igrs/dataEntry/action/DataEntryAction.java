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

public class DataEntryAction extends Action
{
   public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
    {
       DataEntryForm entryForm =(DataEntryForm) form;
       DataEntryDto dto = entryForm.getDto();
       String deedId = request.getParameter("deed");
       DataEntryBd bd = null;
       HttpSession session = request.getSession();
       DataEntryDto dtoSet = null;
       String forward = "success";
       try
       {
	  
	   dtoSet = new DataEntryDto();
	   bd = new DataEntryBd();
	   ArrayList list = bd.getDeedList();
	   dtoSet.setDeedList(list);
	   session.setAttribute("deedListKey", dtoSet);
	   
	   ArrayList distList = bd.getDistrictNames();
	   dtoSet.setDistrictList(distList);
	   session.setAttribute("distListKey", dtoSet);
	   
	   String deedType = request.getParameter("deedType");
	 
	 
       }
       catch(Exception ex)
       {
	   ex.printStackTrace();
       }
     
       return mapping.findForward(forward);
   }

}
