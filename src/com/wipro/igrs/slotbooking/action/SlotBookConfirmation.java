package com.wipro.igrs.slotbooking.action;
import java.util.ArrayList;
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
/**
 * @author venshis
 *
 */
public class SlotBookConfirmation extends BaseAction {
    /**This is the main action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form, 
                                 HttpServletRequest request, 
                                 HttpServletResponse response,HttpSession session) throws  
                                                                      Exception {
    	Logger logger = 
    		(Logger) Logger.getLogger(SlotBookConfirmation.class);
       //	HttpSession session =request.getSession();
        String funId = (String)session.getAttribute("functionId");
		//String userId = (String)session.getAttribute("UserId");
        try {           	
        SlotBookActionForm sbform = (SlotBookActionForm)form;   
        SlotBookBD userBd=new SlotBookBD(); 
        //Start Added by Sreelatha
        SlotBookActionForm sbForm = 
        	(SlotBookActionForm) session.getAttribute("sbform"); //getting the sbform attribute from slotbookaction.java
        SlotBookDTO dto=sbForm.getBookdto();
        SlotBookDTO dto1=sbform.getBookdto();
        logger.debug("sroname"+dto1.getSelctsroname());
        String userId=dto1.getSelctsroname();
        logger.debug("user id"+userId);
        String slotId= dto1.getSelctslottime();
        //String slotId=(String)session.getAttribute("SLOTTIME");
        //Start:====== added on 9-11-2012
        String selctsroname=dto1.getSelctsroname();
        String sroid=dto1.getAvailSroId();
        logger.debug("sroname is"+selctsroname);
        logger.debug("sroid is"+sroid);
      //End:====== added on 9-11-2012
       System.out.println("the value of slotid"+slotId);
       //Start:=====Added By Ankita
       if(slotId!=null )
       {
    	   String slottime=userBd.getslottime(slotId);
    	   dto.setSelctslottimedisp(slottime);
       }
       
       
       
     //End:=====Added By Ankita
       	dto.setUserId(userId);
       	logger.debug("userid is"+dto.getUserId());
        dto.setTimeSlotId(slotId);
        dto.setSelctslottime(slotId);
        logger.debug("the value of slotid"+dto.getSelctslottime());
        dto.setSlotdate(sbForm.getSlotdate());
        System.out.println("selctslottimedisp"+dto.getTimesSlot());
       // dto.setSelctslottime(sbform.getBookdto().getTimeSlotId());   //problem area
       // System.out.println("sbform.getBookdto().getTimeSlotId()----->"+sbform.getBookdto().getTimeSlotId());        
        // added on 16-10-2012
        //dto.setSelctslottime(dto1.getTimeSlotId());
       //added by ankita 12-10-2012
      // logger.debug((String)session.getAttribute("SLOTTIME"));
       // dto.setSelctsroname((String)session.getAttribute("SRONAME"));
      // dto.getSelctslottime();
      // logger.debug("hello"+dto.getSelctslottime());
        //End Added by Sreelatha
        
        //String refRegId=(String)session.getAttribute("regId");
        
        String refRegId=(String)session.getAttribute("App_No");
        //added by ankita
       logger.debug("Check regid - " + (String)session.getAttribute("regId"));
       logger.debug("Check refRegid - " + (String)session.getAttribute("refRegId"));
        if(refRegId==null ||refRegId=="" ){
        	
        	refRegId="MP120320081001";
        	
        }
        //added by ankita 15-10-2012 for user data storing
       // String actiontaken=sbform.getBookdto().getActiontaken();
      //if(actiontaken.equals("store")){
    	  logger.debug("inside confirmation if");
    	 
    	   //}
       
       //end here 15-10-2012
       
    	   
    	 //start:===commented on 1.11.2012  
    	  ArrayList list= null;
        if(refRegId!=null){
        	dto.setUserId(userId);   //problem area
        	dto.setRegId(refRegId);
        	//Added by ankita 14-12-2012 
        	dto.setCreatedBy((String)session.getAttribute("UserId"));
        	//Added by ankita 14-12-2012 		
        	list= userBd.userStoreData(dto);
       
        SlotBookDTO ss= (SlotBookDTO) list.get(0);
 
        dto.setSlotRefId(ss.getSlotRefId());
        dto.setBookList(list);
        sbform.setBookdto(dto);
        
        //Start:==Added By SreeLatha for inserting paymentTxn_in and Slot_Id  into IGRS_REG_SLOT_PAYMENT_DTLS
        String payTxnId = (String)session.getAttribute("status");
        String slotTxnId = dto.getSlotRefId();
        boolean flag = userBd.insertPayTxnSLotTxnId(payTxnId,slotTxnId);
        //End:==Added By SreeLatha for inserting paymentTxn_in and Slot_Id  into IGRS_REG_SLOT_PAYMENT_DTLS
        session.setAttribute("slotbookactionform", sbform);   
        }
    	   
    	    //end:===commented on 1.11.2012 
        sbform=null;
        session.setAttribute("App_No","");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            return mapping.findForward("failure");
        }
        
        return mapping.findForward("success");
        }
    }
    