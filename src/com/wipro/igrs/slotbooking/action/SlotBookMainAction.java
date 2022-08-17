package com.wipro.igrs.slotbooking.action;
/**
 * ===========================================================================
 * File           :   SlotBookMainAction.java
 * Description    :   Represents the Business Class

 * Author         :   Hari Krishna GV
 * Created Date   :   Nov 28, 2007

 * ===========================================================================
 */
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
import com.wipro.igrs.slotbooking.bd.SlotBookBD;
import com.wipro.igrs.slotbooking.dto.SroSlotSheduleDTO;
import com.wipro.igrs.slotbooking.form.SroSlotSheduleForm;

public class SlotBookMainAction extends BaseAction {
    /**This is the main action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form, 
                                 HttpServletRequest request, 
                                 HttpServletResponse response,HttpSession session) throws IOException, 
                                                                      ServletException {
    	
    	
    	Logger logger = 
    		(Logger) Logger.getLogger(SlotBookMainAction.class);
    	
        String page="";
       // HttpSession session = request.getSession();
        String sessionSROId=(String)session.getAttribute("role");
		String sessionUserId=(String)session.getAttribute("UserId");   
    	try {
    		SlotBookBD bd=new  SlotBookBD(); 
    		sessionSROId = bd.getOffId(sessionUserId);
    		 String param[] = new String[2];
			   param[0] = sessionUserId;
			   param[1] = sessionSROId;
    		SroSlotSheduleForm  srform=(SroSlotSheduleForm)form;      
    		SroSlotSheduleDTO srodto=new SroSlotSheduleDTO();
    		srodto.setSno(sessionSROId);
    		srodto.setSelectDate(srform.getSelectDate());
    		String checkboxval = srform.getSheduledto().getCheckboxvalues();
    		String checkremarks = srform.getSheduledto().getCheckRemarks();
    		    			   
    		if(checkboxval!=null&&checkboxval!=""){
    		srodto.setCheckboxvalues(checkboxval);
    		        
    		if(checkremarks!=null){
    		 
    		srodto.setCheckRemarks(checkremarks);
    		}
    		 srodto.setSelectDate(srform.getSelectDate());
    		  
    		   String actiontaken=srform.getSheduledto().getActiontaken();
    		   if(actiontaken==null){
    			   actiontaken="";
    			   
    		   }
    		   if(actiontaken.equals("store")){
    			   bd.storeSroData(srodto,param);
    		   }  
    		  
    		 	
    		
    		}
    		          
            request.setAttribute("sheduleslotformdisp",srform);
    		page="success";
           
    		return mapping.findForward(page);
    		
    		}catch (Exception e) {
    		  logger.error(e);
              e.printStackTrace();
              return mapping.findForward("failure");
          }          
         
      }
}