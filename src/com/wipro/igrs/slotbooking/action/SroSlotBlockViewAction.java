package com.wipro.igrs.slotbooking.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.slotbooking.bd.SlotBookBD;
import com.wipro.igrs.slotbooking.dto.SlotBookDTO;
import com.wipro.igrs.slotbooking.dto.SroSlotSheduleDTO;
import com.wipro.igrs.slotbooking.form.SroSlotSheduleForm;

public class SroSlotBlockViewAction extends BaseAction {   
	/**This is the main action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.    
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     */  
    public ActionForward execute(ActionMapping mapping, ActionForm form, 
                                 HttpServletRequest request, 
                                 HttpServletResponse response,HttpSession session) throws IOException, 
                                                                      ServletException,                                                                      
                                                                    Exception {
    	String lang=(String)session.getAttribute("languageLocale");
    	Logger logger 				= (Logger) Logger.getLogger(SroSlotBlockViewAction.class);
    	SroSlotSheduleForm sroForm 	= (SroSlotSheduleForm)form;
    	SlotBookBD bd				= new SlotBookBD();
    	SlotBookDTO bookdto 		= sroForm.getBookdto();
    	String roleId			 	= (String)session.getAttribute("role");
		String funId 				= (String)session.getAttribute("functionId");
		String userId 				= (String)session.getAttribute("UserId");
		String actId 				= (String)request.getParameter("actId");
		
     	HashMap officeDetails 		= new HashMap();
		String roleID 				= "";
		String returnPage 			= "";
   		String sessionUserId 		= userId;
		
		if(session.getAttribute("officeactivitydata")!= null)
		{
		officeDetails				= (HashMap)session.getAttribute("officeactivitydata");
		}
				
		if(request.getParameter("label")!=null)
		{
			if((request.getParameter("label")).equalsIgnoreCase("sroOfiice"))
				{
				sroForm.setDistrictList(new ArrayList());
				sroForm.setSroNameList(new ArrayList());
				sroForm.setBookdto(new SlotBookDTO());
				sroForm.setSheduledto(new SroSlotSheduleDTO());
				sroForm.setActId(actId);
				sroForm.setSelectDate("");
				sroForm.getSheduledto().setSlotshedule(new ArrayList());
				
				ArrayList districtList = bd.getDistrictList(officeDetails,sroForm.getActId(), lang);
				sroForm.setDistrictList(districtList);
				bookdto.setUserId(userId);
				returnPage="success";
				}
			
		}
		
		if(sroForm!=null)
    	{		
			String districtId="";
			String sroid="";
			String srid="";
						
    	}
		
		
		
		if(request.getParameter("myoffice")!=null)
		{			
			String districtId = bookdto.getSlctDistrict();
			ArrayList  sroNameList = bd.getSroName(districtId, lang);
        	sroForm.setSroNameList(sroNameList);
        	SroSlotSheduleDTO srodto=new SroSlotSheduleDTO();
        	srodto.setSlotshedule(new ArrayList());
            sroForm.setSheduledto(srodto);
            sroForm.setSrNameList(new ArrayList());
            request.removeAttribute("SrNameList");
            //request.setAttribute("SrNameList"
            		
        	returnPage="success";
		}
		
		if(request.getParameter("sroName")!=null){
          	String sroID = bookdto.getSlctSrOffice();
          	ArrayList srNameList = bd.getSrNameList(sroID);
          	sroForm.setSrNameList(srNameList);
          	SroSlotSheduleDTO srodto=new SroSlotSheduleDTO();
          	srodto.setSlotshedule(new ArrayList());
            sroForm.setSheduledto(srodto);
            
          	returnPage="success";
         }
		if(request.getParameter("sroNameNew")!=null){
          	String sroID = bookdto.getSlctSrOffice();
          	ArrayList srNameList = bd.getSrNameList(sroID);
          	sroForm.setSrNameList(srNameList);
          	SroSlotSheduleDTO srodto=new SroSlotSheduleDTO();
          	srodto.setSlotshedule(new ArrayList());
            sroForm.setSheduledto(srodto);
            
          	returnPage="success";
         }
		
		if(request.getParameter("param") !=null && request.getParameter("param").equalsIgnoreCase("showslot"))
		{
            if(sroForm.getSelectDate()!=null && sroForm.getSelectDate()!="Select"){
            	
            	SroSlotSheduleDTO srodto=new SroSlotSheduleDTO();
               	srodto.setUserId(bookdto.getSlctSrId());
           		srodto.setCreatedBy(bookdto.getSlctSrId());
        		srodto.setSno(bookdto.getSlctSrOffice());
        		srodto.setSelectDate(sroForm.getSelectDate());
        		
                ArrayList srolist= bd.getSroSlotSheduleDetails(srodto, lang);
                
                srodto.setSlotshedule(srolist);
                sroForm.setSheduledto(srodto);                
        	
                request.setAttribute("SrNameList", sroForm);
                //sroForm.setSelectDate("");
            	returnPage="success";
            }
          }
       
		
		if(request.getParameter("label")!=null)
		{
			if((request.getParameter("label")).equalsIgnoreCase("srView"))
				{

				sroForm.setDistrictList(new ArrayList());
				sroForm.setSroNameList(new ArrayList());
				sroForm.setBookdto(new SlotBookDTO());
				sroForm.setSheduledto(new SroSlotSheduleDTO());
				sroForm.setActId(actId);
				sroForm.setSelectSrDate("");
				
				ArrayList OfficeList = bd.getOfficeName(officeDetails,sroForm.getActId(), lang);
				sroForm.setSroNameList(OfficeList);
				bookdto.setUserId(userId);
				returnPage="srViewSuccess";
				}
			
		}
		
		
		
		if(request.getParameter("param") !=null && request.getParameter("param").equalsIgnoreCase("showsrslot"))
		{
		if(bookdto.getSlctOffice()!=null && bookdto.getSlctOffice()!="")
		{
		if(sroForm.getSelectSrDate()!=null && sroForm.getSelectSrDate()!= ""){
        	SroSlotSheduleDTO srodto=new SroSlotSheduleDTO();
        	
    		srodto.setUserId(sessionUserId);
    		srodto.setCreatedBy(sessionUserId);
    		srodto.setSno(bookdto.getSlctOffice());
    		srodto.setSelectDate(sroForm.getSelectSrDate());
            ArrayList srolist= bd.getSroSlotSheduleDetails(srodto, lang);
            srodto.setSlotshedule(srolist);
            sroForm.setSheduledto(srodto);                   
    	    session.setAttribute("sheduleslotform", sroForm);
    	    returnPage="srViewSuccess";
        }
		}
		}
		if(request.getParameter("param") !=null && request.getParameter("param").equalsIgnoreCase("showsrslotNew"))
		{
		if(bookdto.getSlctOffice()!=null && bookdto.getSlctOffice()!="")
		{
		//if(sroForm.getSelectSrDate()!=null && sroForm.getSelectSrDate()!= ""){
        	SroSlotSheduleDTO srodto=new SroSlotSheduleDTO();
        	
    		srodto.setUserId(sessionUserId);
    		srodto.setCreatedBy(sessionUserId);
    		srodto.setSno(bookdto.getSlctOffice());
    		srodto.setSelectDate(sroForm.getSelectSrDate());
            
            srodto.setSlotshedule(new ArrayList());
            sroForm.setSheduledto(srodto);                   
    	    session.setAttribute("sheduleslotform", sroForm);
    	    returnPage="srViewSuccess";
       // }
		}
		}
    	
		
    	
		
	
    	return mapping.findForward(returnPage);
    }
}
