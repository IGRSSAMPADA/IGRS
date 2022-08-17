package com.wipro.igrs.payment.action;


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.payment.bd.ChallanGenBD;
import com.wipro.igrs.payment.dto.ChallanGenDTO;
import com.wipro.igrs.payment.form.ChallanGenForm;

/**
 * ===========================================================================
 * File           :   ChallanGenAction.java
 * Description    :   Represents the Challan generation view Action Class
 * Author         :   vengamamba P
 * Created Date   :    Mar 14, 2008

 * ===========================================================================
 */

public class ChallanGenAction extends BaseAction {
    /**This is the main action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     */

	 private static final Logger logger = Logger
	.getLogger(ChallanGenAction.class);
    public ActionForward execute(ActionMapping mapping, ActionForm form, 
                                 HttpServletRequest request, 
                                 HttpServletResponse response,HttpSession session) throws Exception {    
    	String FORWARD_JSP="Challan";
    	if (form != null) {
    		ChallanGenForm chform = (ChallanGenForm)form;
			ChallanGenBD chBD = new ChallanGenBD();
			
			ArrayList  officeidList = chBD.getOfficeNameList();//getting list officeids
						
			ChallanGenDTO dto= new ChallanGenDTO();
			//setting list of values to officeids,services.
			dto.setOfficeNameList(officeidList);
           	
			chform.setChgendto(dto);
			logger.debug("Action  ");
			String actionValue =chform.getActionValue();
			//if ("challanViewForm".equalsIgnoreCase(chform.getChallanForm()))
			if(request.getParameter("axn")!=null && request.getParameter("axn").equalsIgnoreCase("viewMain"))
			{   
				
				//action belong to submit button
				
			}//end of challan view form
			else if ("ChallanView".equalsIgnoreCase(actionValue))
			{	
				String officeid=chform.getOfficeid();
				String chdate=chform.getChdate();
				ArrayList slist=null;
				//RETRIVING DELEVERY STATUS OF CHALLAN
				slist=chBD.getStatus(officeid,chdate);
				if (!slist.isEmpty())
				{
					ArrayList tmp;
					for(int i=0;i<slist.size();i++){
						tmp=(ArrayList)slist.get(i);
						logger.debug("temparray="+tmp);
						chform.setTxnid((tmp.get(0).toString()));
						chform.setDelivaryStatus((tmp.get(1).toString()));
					}
					logger.debug("Action  "+chform.getDelivaryStatus());
					//retrieving challan data of undelivered to bank
					if (chform.getDelivaryStatus().equals("N")){	
						ArrayList chList;
						chList=chBD.getChallanGenData(officeid,chdate);
						ArrayList tmp_arr;
						int i;
						for( i=0;i<chList.size();i++){
							tmp_arr=(ArrayList)chList.get(i);
							logger.debug("temparray="+tmp_arr);
							logger.debug("temparray size="+tmp_arr.size());
							if (tmp_arr.size()>1){
								logger.debug("temp array value setting=");
								chform.setTxnid((tmp_arr.get(0).toString()));
								chform.setOfficeName((tmp_arr.get(5).toString()));
								chform.setCreatedby((tmp_arr.get(2).toString()));
								chform.setChdate(tmp_arr.get(3).toString());
								chform.setAmt((tmp_arr.get(4).toString()));
	    					}
	    			
						}
														
						request.setAttribute("challanlist",chform);
						FORWARD_JSP="ChallanView";		
										
					}//end of if undelivered
					//retriving data if already delivered
					else{
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
					}//end if of delivered
				}//end if of data not null
				//if data does not match 
				else
				{
					FORWARD_JSP="Challan";
					request.setAttribute("error",chform);
				}
			}//end of  challan view action
			//action belong to cancel button
			else if ("Cancel".equalsIgnoreCase(actionValue))
		    {
		    	FORWARD_JSP="Cancel";
		    	
		    }
	    }
    	return mapping.findForward(FORWARD_JSP);
    	
    } //end of execute
    
}//end of class