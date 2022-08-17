package com.wipro.igrs.slotbooking.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.log4j.Logger;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.slotbooking.bd.SlotBookBD;
import com.wipro.igrs.slotbooking.dto.SlotBookDTO;
import com.wipro.igrs.slotbooking.dto.SroSlotSheduleDTO;
import com.wipro.igrs.slotbooking.form.SroSlotSheduleForm;

/**
 * @author venshis
 *
 */
public class SroSlotSheduleAction extends BaseAction {   
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
    	Logger logger 				= 	(Logger) Logger.getLogger(SroSlotSheduleAction.class);
    	SroSlotSheduleForm  srform	=	(SroSlotSheduleForm)form;   
    	SlotBookBD bd				=	new  SlotBookBD(); 
    	SlotBookDTO bookdto 		= 	srform.getBookdto();
    	
    	
    	String sessionSROId			=(String)session.getAttribute("role");
    	String sessionUserId		=(String)session.getAttribute("UserId"); 
    	String funId 				= (String)session.getAttribute("functionId");
		String actId 				= (String)request.getParameter("actId");
    
		
		HashMap officeDetails 		= new HashMap();
		
		if(session.getAttribute("officeactivitydata")!= null)
		{
		officeDetails				= (HashMap)session.getAttribute("officeactivitydata");
		}
		
		if(request.getParameter("label")!=null)
		{
		if((request.getParameter("label")).equalsIgnoreCase("srdashboard"))
		{
		srform.setScheduleOfficeList(new ArrayList());
		srform.setActId(actId);
		srform.setSelectDate("");
		srform.setSelectScheduleDate("");
		srform.setSelectSrDate("");
		ArrayList OfficeList = bd.getOfficeName(officeDetails,srform.getActId(), lang);
		srform.setScheduleOfficeList(OfficeList);
		}
		}
				    	
        String page="";
        
    	try {	

       	sessionSROId = bookdto.getSlctScheduleOffice();
    	logger.debug(sessionSROId);
    	String param[] = new String[2];
		param[0] = sessionUserId;
		param[1] = sessionSROId;
    		   
    		
    	SroSlotSheduleDTO srodto=new SroSlotSheduleDTO();
    	srodto.setUserId(sessionUserId);
    	srodto.setCreatedBy(sessionUserId);
    	srodto.setSno(sessionSROId);
    	srodto.setSelectScheduleDate(srform.getSelectScheduleDate());
    	String checkboxval = srform.getSheduledto().getCheckboxvalues();
    	String cancelvalue = srform.getSheduledto().getActiontaken();  
    	String checkremarks = srform.getSheduledto().getCheckRemarks(); 
    		
    	if(cancelvalue==null)
    	{
    	cancelvalue="";
    	}
    		
    	if(cancelvalue.equals("reset"))
    	
    	{
    	    	srform.setSelectScheduleDate(null);
    			page="successone";
    			srform.getSheduledto().setActiontaken(null);
    			return mapping.findForward(page);
    			
    		}
    		if(cancelvalue.equals("cancelsrobookedslot")){
    			
    			if(srform.getSelectScheduleDate()!=null)	 
        		{
        		srodto.setSelectScheduleDate(srform.getSelectScheduleDate());
        		ArrayList srolist= bd.getSrobookedSlotDetails(srodto,param);//gives the list of sro slots that 
        													//are booked for the particular date and id
        		//System.out.println(srolist.size());
        		srodto.setSlotshedule(srolist);
        	
        		System.out.println(srodto.getSlotshedule().size());
                srform.setSheduledto(srodto);
                srform.setListsize(srodto.getSlotshedule().size());
        		
                srform.setSelectScheduleDate(srform.getSelectScheduleDate());           
                request.setAttribute("sheduleslotformdisp",srform);
        		}      				 
    			
    			page="cancel";
    			
    		}else    		
    		if(!cancelvalue.equals("cancelsrobookedslot") ||cancelvalue!=""){
    			   
    		if(checkboxval!=null ){
    		
    		srodto.setCheckboxvalues(checkboxval);
    		       
    		if(checkremarks!=null){
    		 
    		srodto.setCheckRemarks(checkremarks);
    		}
    		 srodto.setSelectDate(srform.getSelectScheduleDate());
    		int i=0;
    		   String actiontaken=srform.getSheduledto().getActiontaken();
    		   if(actiontaken==null){
    			   actiontaken="";
    		   }
    		   if(actiontaken.equals("store")){
    			  boolean boo = bd.storeSroData(srodto,param);
    			if (boo){
				
    				request.removeAttribute("sheduleslotformdisp");
    				srform.getSheduledto().setActiontaken(null);
			  }
		   }else if(actiontaken.equals("updatesr"))
		       	i = bd.updatebookedbysr(srodto,param);
		   		if (i>0){
		   			  			
		   		    
		   		    request.removeAttribute("sheduleslotformdisp");
		   		    srform.getSheduledto().setActiontaken(null);
		   		}
		}
    		
    		
    		
    		
    		if(srform.getSelectScheduleDate()!=null & srform.getSelectScheduleDate()!="")	 
    		{	    			
    		srodto.setSelectDate(srform.getSelectScheduleDate());
    		//Start:====for checking weekend
    		int i=bd.checkweekend(srodto);
			if(i>0)
			{
				logger.debug("inside if");
				page="failureHoliday";
				srform.getSheduledto().setActiontaken("reset");
				return mapping.findForward(page);
			}
			//End:====for checking weekend
    		ArrayList srolist= bd.getSroSlotSheduleDetails(srodto, lang);
            srodto.setSlotshedule(srolist);
            srform.setSheduledto(srodto);
            srform.setSelectDate(srform.getSelectScheduleDate());           
            request.setAttribute("sheduleslotformdisp",srform);
            page="success";
            srform.getSheduledto().setActiontaken("reset");
            return mapping.findForward(page);
    		}
    		
            page="successone";
           
    		}		 
    			return mapping.findForward(page);
    		
    		}catch (Exception e) {
    			e.printStackTrace();
              logger.error(e);
              return mapping.findForward("failure");
          }          
         
      }
}
