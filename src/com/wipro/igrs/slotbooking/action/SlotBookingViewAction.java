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
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.slotbooking.bd.SlotBookBD;
import com.wipro.igrs.slotbooking.dto.SlotBookDTO;
import com.wipro.igrs.slotbooking.form.SlotBookActionForm;

public class SlotBookingViewAction  extends BaseAction {
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
    		(Logger) Logger.getLogger(SlotBookingViewAction.class);
    		
        SlotBookActionForm sbform = (SlotBookActionForm)form;
        String lang=(String)session.getAttribute("languageLocale");
		//HttpSession session = request.getSession();
		SlotBookBD bd = new SlotBookBD(); 
		SlotBookDTO dto = sbform.getBookdto();
		String page = sbform.getBookdto().getPageName();
		
		String actionID = sbform.getBookdto().getActionID();
		
		String returnPage = "";
		ActionMessages messages = new ActionMessages();
		String durationFrom = sbform.getDurationFrom();
		
		
		//added by ankita
		
		System.out.println(durationFrom);
		String durationTo = sbform.getDurationTo();
				
		
		//added by ankita
		
		System.out.println(durationTo);
		
		
		dto.setDurationFrom(durationFrom);
		
		dto.setDurationTo(durationTo);
				
    	try { 
    		if(sbform!=null){
    			returnPage = "success";
    			if(page!=null){
    				if(page.equalsIgnoreCase("slotBookingView")){
            			if(actionID.equalsIgnoreCase("submit")){            				
            				ArrayList slotBkViewList = bd.getSlotBkViewDetails(dto, lang);
            				if(slotBkViewList.size()!=0){
            					SlotBookDTO sbdto = (SlotBookDTO)slotBkViewList.get(0);

                				sbform.getBookdto().setSlotBkViewList(slotBkViewList);
                				session.setAttribute("SltBkViewLst",slotBkViewList);
                				sbform.getBookdto().setActionID(null);
                				returnPage = "slotBkViewSearch";
                				
                				
            				}else{
            					messages.add("MSG", new ActionMessage("no.records.found"));
            					saveMessages(request, messages);
            					request.setAttribute("SltBkList", slotBkViewList);
            					returnPage = "success";
            				}
            			}else if(actionID.equalsIgnoreCase("cancel")){
            				sbform.getBookdto().setActionID(null);
            				returnPage = "main";
            			}else if(actionID.equalsIgnoreCase("reset")){
            				sbform.setDurationFrom("");
            				sbform.setDurationTo("");
            				
            				returnPage = "success";
            			}
            		}else if(page.equalsIgnoreCase("slotBookingSearch")){
            			if(actionID.equalsIgnoreCase("back")){
            				returnPage = "success";
            			}else if(actionID.equalsIgnoreCase("cancel")){
            				returnPage = "main";
            			}
            		}else if(page.equalsIgnoreCase("slotBookingUpdate")){
            			if(actionID.equalsIgnoreCase("back")){
            				ArrayList slotBkViewList = bd.getSlotBkViewDetails(dto, lang);
	        				if(slotBkViewList.size()!=0){
	        					SlotBookDTO sbdto = (SlotBookDTO)slotBkViewList.get(0);

	            				sbform.getBookdto().setSlotBkViewList(slotBkViewList);
	            				session.setAttribute("SltBkViewLst",slotBkViewList);
	            				returnPage = "slotBkViewSearch";
	        				}
            			}else if(actionID.equalsIgnoreCase("cancel")){
            				returnPage = "main";
            			}
            		}
    				//Start:====for SlotBooking View Get Data for particularId
    				if(request.getParameter("refIdValue")!=null){
    		    		String referenceId = request.getParameter("refIdValue");
    		    		
    					ArrayList referenceIdList = 
    						bd.getRefIdDetails(referenceId);
    					SlotBookDTO sbdto = (SlotBookDTO)referenceIdList.get(0);
    					sbform.getBookdto().setSlotBkViewList(referenceIdList);
    					session.setAttribute("SltBkViewLst",sbform);
    					returnPage = "refIdSuccess";
    				}
    				//End:====for SlotBooking View Get Data for particularId
    			}        		
    		}
        }catch (Exception e) {
        	logger.error("in slotbk view action====="+e);
		}
		return mapping.findForward(returnPage);
    }
        
}
