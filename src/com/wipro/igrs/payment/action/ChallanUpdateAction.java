package com.wipro.igrs.payment.action;

import java.util.ArrayList;

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
import com.wipro.igrs.payment.bd.ChallanUpdateBD;
import com.wipro.igrs.payment.dto.ChallanGenDTO;
import com.wipro.igrs.payment.form.ChallanGenForm;

/**
 * ===========================================================================
 * File           :   ChallanUpdateAction.java
 * Description    :   Represents the Challan generation update Action Class
 * Author         :   vengamamba P
 * Created Date   :    Mar 17, 2008

 * ===========================================================================
 */

public class ChallanUpdateAction extends BaseAction {
    /**This is the main action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     */
	 private static final Logger logger = Logger
	.getLogger(ChallanUpdateAction.class);
    public ActionForward execute(ActionMapping mapping, ActionForm form, 
                                 HttpServletRequest request, 
                                 HttpServletResponse response,HttpSession session) throws Exception {    
    	String FORWARD_JSP="Update";
    	ActionMessages messages = new ActionMessages();
    	if (form != null) {
    		ChallanGenForm chform = (ChallanGenForm)form;
			ChallanUpdateBD chBD = new ChallanUpdateBD();
			
			ArrayList  bankidList = chBD.getBankidList();//getting list of bankids
			ArrayList uids=chBD.getUseridList();			
			ChallanGenDTO dto= new ChallanGenDTO();
			//setting list of values to bankids,userids.
			dto.setBankidList(bankidList);
           	dto.setUseridList(uids);
			chform.setChgendto(dto);
			logger.debug("Action  ");
			String actionValue =chform.getActionValue();
			
			if (request.getParameter("axn")!=null && request.getParameter("axn").equalsIgnoreCase("updateMain"))
			{   
				FORWARD_JSP="Update";
			}
				//action belong to submit button
			else if (actionValue!=null && "View".equalsIgnoreCase(actionValue))
			{	
				String status=chBD.getChallanStatus(chform);
				//getting delivery status of challan
				if (status.equals("")){
					logger.debug("no txnid in table");
					FORWARD_JSP="Update";
					messages.add("msg",new ActionMessage("errors.no.txnid.available"));
					saveMessages(request, messages);
					request.setAttribute("error1",chform);
				}
				else{
					if (status.equals("Y")){
						logger.debug("already updated");
						FORWARD_JSP="Update";
						messages.add("msg2",new ActionMessage("errors.already.updated"));
						saveMessages(request, messages);
						request.setAttribute("error2",chform);
					}
					else{
						boolean updateflag=chBD.updateChallan(chform);
						if (updateflag)				
							logger.debug("updated successfully");
						else
						{
							logger.debug("unsuccessfully updated");
							FORWARD_JSP="Update";
							messages.add("msg3",new ActionMessage("errors.unsuccess.updation.challan"));
							saveMessages(request, messages);
							request.setAttribute("error3",chform);
						}
						//if updation over then setting data to display
						if (updateflag){
							
							ArrayList chList;
							chList=chBD.getChallanData(chform);
							ArrayList tmp_arr;
							int i;
							for( i=0;i<chList.size();i++){
								tmp_arr=(ArrayList)chList.get(i);
								logger.debug("temparray="+tmp_arr);
								logger.debug("temparray size="+tmp_arr.size());
								if (tmp_arr.size()>1){
									logger.debug("temp array value setting=");
			    					chform.setTxnid((tmp_arr.get(0).toString()));
			    					chform.setOfficeName((tmp_arr.get(1).toString()));
			    					chform.setCreatedby((tmp_arr.get(2).toString()));
			    					chform.setChdate(tmp_arr.get(3).toString());
			    					chform.setAmt((tmp_arr.get(4).toString()));
			    					chform.setScrollno((tmp_arr.get(5).toString()));
			    					
			    					chform.setDepositdate(tmp_arr.get(6).toString());
			    					chform.setDepositedby((tmp_arr.get(7).toString()));
			    					chform.setBankid((tmp_arr.get(8).toString()));
								}
			    			
							}
						
							request.setAttribute("challanlist",chform);
							FORWARD_JSP="View";		
						}//end if updateflag true
						
					}// end of else if it not already updated
				}//end of else if data not null
				
			}//end of  challan update action
			
			//action belong to cancel button
			else if (actionValue!=null && "Cancel".equalsIgnoreCase(actionValue))
		    {
		    	FORWARD_JSP="Cancel";
		    	
		    }
			
			}//end of challan update form
			
			
    	
       	return mapping.findForward(FORWARD_JSP);
    	
    } //end of execute
    
}//end of class