package com.wipro.igrs.slotbooking.action;

import java.io.IOException;
import java.util.ArrayList;

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
import com.wipro.igrs.slotbooking.form.SlotBookActionForm;
 
public class SlotAmtPaymentAction extends BaseAction {
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
    	Logger logger = 
    		(Logger) Logger.getLogger(SlotAmtPaymentAction.class);
    	
    	try {
    	        SlotBookActionForm sbform = (SlotBookActionForm)form;
    	        SlotBookBD bd=new SlotBookBD();  
    	        //HttpSession session = request.getSession();
    	        /*request.getSession().setAttribute("user", "SRO");
    	        request.getSession().setAttribute("amount", "300");*/
    	        session.setAttribute("forwardPath", "./slotbooking2.do");
   	       
    	        session.setAttribute("SRONAME",sbform.getBookdto().getSelctsroname());
    	        session.setAttribute("SLOTTIME",sbform.getBookdto().getSelctslottime()); 
    	        
    	        String availSro = sbform.getBookdto().getAvailSro();

    	       //for select sroName
    	       String _sroId = sbform.getBookdto().getSroId();
    	       ArrayList sroNameList = new ArrayList();
    	       sroNameList = bd.getSrNameList(_sroId);
    	       for(int i=0;i<sroNameList.size();i++){
    	    	   SlotBookDTO dto = (SlotBookDTO)sroNameList.get(i);
    	    	   if(availSro.equalsIgnoreCase(dto.getSrId())){
    	    		   sbform.getBookdto().setAvailSroId(dto.getSrName());   	    		   
    	    	   }    	    	   
    	       }

    	       //for select districtId
    	       String distId = sbform.getBookdto().getDistId();

    	       ArrayList distList = bd.getDistrictName();
    	       for(int i=0;i<distList.size();i++){
    	    	   SlotBookDTO dto = (SlotBookDTO)distList.get(i);
    	    	   if(distId.equalsIgnoreCase(dto.getDistId())){
    	    		   sbform.getBookdto().setDistName(dto.getDistName());   	    		   
    	    	   }    	    	  
    	       }

    	       //for select sroId
    	       String sroId = sbform.getBookdto().getSroId();

    	       ArrayList sroList = bd.getSroName(distId, lang);
    	       for(int i=0;i<sroList.size();i++){
    	    	   SlotBookDTO dto = (SlotBookDTO)sroList.get(i);
    	    	   if(sroId.equalsIgnoreCase(dto.getSroId())){
    	    		   sbform.getBookdto().setSroName(dto.getSroName());   	    		   
    	    	   }    	    	  
    	       }
    	      
    	       session.setAttribute("XYZ", sbform.getBookdto());
    	        
   		 }catch (Exception e) {
             e.printStackTrace();

         }
   		
    	return mapping.findForward("slotAmtPayment");
    }
}
