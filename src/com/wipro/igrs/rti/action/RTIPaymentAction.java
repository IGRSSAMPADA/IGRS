/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   RTIRequestAction.java
 * Author      :   Samuel Prabhakaran
 * 
 * ----------------------------------------------------------------------------
 * Version         Last Modified By     Last Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Samuel Prabhakaran       17th March, 2008	 		
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.rti.action;

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
import com.wipro.igrs.common.CommonConstant;
import com.wipro.igrs.rti.bd.RTIBD;
import com.wipro.igrs.rti.form.RTIRequestForm;

public class RTIPaymentAction extends BaseAction {

	public RTIPaymentAction() {
	}
	private  Logger logger = 
		(Logger) Logger.getLogger(RTIPaymentAction.class);
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,HttpSession session)
			throws ServletException, IOException {
		RTIRequestForm rtiform = (RTIRequestForm) form;
		ArrayList list2=new ArrayList();
		ArrayList list1=new ArrayList();
		ArrayList cashlist=new ArrayList();
		RTIBD rbd = new RTIBD();

		
		//HttpSession session=request.getSession();
		list2=(ArrayList)session.getAttribute("cashList");
		if(list2!=null && list2.size()>0){
			//list1=(ArrayList)list2.get(0);
			rtiform.getRTIRequestDTO().setCashReceiptID((String)list2.get(0));//paymentForm.getPaymentDTO().getReceiptID());
			rtiform.getRTIRequestDTO().setCashReceiptDate((String)list2.get(2));//Double.toString(paymentForm.getPaymentDTO().getAmount()));
			rtiform.getRTIRequestDTO().setAmount((String)list2.get(1));//paymentForm.getCashDate());
			
			cashlist.add(rtiform.getRTIRequestDTO());
			session.setAttribute("cashlist", cashlist);
		}
		String test = (String)session.getAttribute("paymentinteg");
		logger.debug("in rti="+test);
		if ((CommonConstant.RTI_REQUEST_RU_05).equalsIgnoreCase((String)session.getAttribute("paymentinteg"))) {
			//	if ((CommonConstant.RTI_REQUEST_RU_NEXT).equalsIgnoreCase((String)session.getAttribute("paymentinteg")))
						 {
							 session.removeAttribute("paymentinteg");
					ArrayList list3,createdate;
					rbd = new RTIBD();
					String userId =(String)session.getAttribute("userId");
					String paymentTxnId =(String)session.getAttribute("status");
					 rtiform.getRTIRequestDTO().setUserId(userId);
					 rtiform.getRTIRequestDTO().setPaymentTxnId(paymentTxnId);
					ArrayList list=rbd.rtiRequest( rtiform.getRTIRequestDTO());
					if(list!=null){
						list3=(ArrayList)list.get(0);
						String requestDate =(String)list3.get(0);
						rtiform.getRTIRequestDTO().setRequestDate(requestDate);
						createdate =new ArrayList();
						createdate.add(rtiform);
						session.setAttribute("createdate",createdate);
					}else{
						session.setAttribute("error", "Data is not inserted properly. please contact system admin");
						return mapping.findForward("rtirequest");

					}
					return mapping.findForward("rtirequestru6");
				}
				/*if ((CommonConstant.RTI_REQUEST_RU_PREVIOUS)
						.equalsIgnoreCase(rtiform.getRTIRequestDTO()
								.getRtiRequestFiveAction())) {
					return mapping.findForward("rtirequestru4");
				}*/
			}
		
		
		
		
		
		/**
		 * This if statement is update the RTI request.
		 */
		
		
		
		
		if ((CommonConstant.RTI_REQUEST_UD_06).equalsIgnoreCase((String)session.getAttribute("paymentinteg"))) {
				
			//if ((CommonConstant.RTI_REQUEST_RU_NEXT).equalsIgnoreCase((String)session.getAttribute("paymentinteg"))) {
				session.removeAttribute("paymentinteg");
				rbd = new RTIBD();
				ArrayList list3,updatedate;
//				 session = request.getSession();
				ArrayList alist = (ArrayList) session
						.getAttribute("requestinfo1");

				RTIRequestForm frm = (RTIRequestForm) alist.get(0);
			    ArrayList list=rbd.closeRTIStatus(frm.getRTIRequestDTO());
				if(list!=null){
					list3=(ArrayList)list.get(0);
					String updateDate =(String)list3.get(0);
					frm.getRTIRequestDTO().setUpdateDate(updateDate);
					updatedate =new ArrayList();
					updatedate.add(frm);
					session.setAttribute("updatedate",updatedate);
											
				}else{
					session.setAttribute("error", "Data is not updated properly. please contact system admin");
					return mapping.findForward("rtiupdate");
				}
				return mapping.findForward("rtiupdate07");
			//}
			
		/*	if ((CommonConstant.RTI_REQUEST_RU_PREVIOUS)
					.equalsIgnoreCase(rtiform.getRTIRequestDTO()
							.getRtiStatusTwoAction())) {
				return mapping.findForward("rtiupdate05");
			}*/
		}
   return null;
		
		
	}
}