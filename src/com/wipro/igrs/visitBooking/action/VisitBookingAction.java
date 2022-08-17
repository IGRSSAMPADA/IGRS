package com.wipro.igrs.visitBooking.action;

/**
 * ===========================================================================
 * File           :   VisitBookingAction.java
 * Description    :   Represents the Action Class

 * Author         :   Pavani Param
 * Created Date   :   Apr 08,2008.

 * ===========================================================================
 */


import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.empmgmt.bd.TrainingBD;
import com.wipro.igrs.empmgmt.dto.EmployeeDTO;
import com.wipro.igrs.visitBooking.bd.VisitBookingBD;
import com.wipro.igrs.visitBooking.form.VisitBookingForm;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


  public class VisitBookingAction extends BaseAction 
     {
	  private Logger logger = (Logger) Logger.getLogger(VisitBookingAction.class);
		public ActionForward execute(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response,HttpSession session)
				throws IOException, ServletException
				
				{
						logger.debug(" VisitBookingAction --  In action for getting form object--> ");
						logger.debug("VisitBookingAction --  In action for getting form object--> " + form);
						String FORWARD_JSP = "success";
						String paymentTxnId=null;
				
						VisitBookingForm visitForm = (VisitBookingForm) form;
						boolean blnInsVal;
					      String roleId = (String)session.getAttribute("role");
						  String funId = (String)session.getAttribute("functionId");
						  String userId = (String)session.getAttribute("UserId");
						  VisitBookingBD objVisitBD = new VisitBookingBD();
						  
						try {
				
								
					
								// *********** START ****************************Visit Booking****************************
								
								
								if(session.getAttribute("status")!=null)
									paymentTxnId=session.getAttribute("status").toString();
								logger.debug("VisitBookingAction -- payment="+paymentTxnId);
								if (paymentTxnId !=null )
								{
										visitForm.setPmtTxnId(paymentTxnId);
										blnInsVal = objVisitBD.submitVisitInfo(visitForm,roleId,funId,userId);
										if (blnInsVal) 
											{
												logger.debug("VisitBookingAction-- visit TXN ID = "+ visitForm.getVisitTxnId());
												visitForm = objVisitBD.printVisitBookDet(visitForm);
											}
										FORWARD_JSP = "confirm";
										session.removeAttribute("visitForm");
								}
					
								if (visitForm.getPageTitle().equalsIgnoreCase("VisitBookView"))
								{
									visitForm = objVisitBD.getVisitViewResult(visitForm);
								}
								else if (visitForm.getPageTitle().equalsIgnoreCase("VisitBookUpdate"))
								{
										logger.debug("VisitBookingAction-- VisitBookUpdate  ");
										visitForm = objVisitBD.getVisitUpdateResult(visitForm);
								} 
								else if (visitForm.getPageTitle().equalsIgnoreCase("VisitBookUpdateDet"))
								{
									logger.debug("VisitBookingAction -- VisitBookUpdateRes  ");
									blnInsVal = objVisitBD.submitVisitUpdRem(visitForm,roleId,funId,userId);
								}
					
								logger.debug("VisitBookingAction -- ......FORWARD_JSP=" + FORWARD_JSP);
								logger.debug("VisitBookingAction -- ......PageTitle="+ visitForm.getPageTitle());
								
								session.setAttribute("forwardPath","./visitCreateConfirm.do");
								session.setAttribute("amount",visitForm.getTotal());
								
								session.setAttribute("visitVRList",visitForm.getVisitViewList());
								session.setAttribute("visitForm", visitForm);
							} 
						catch (Exception e) 
							{
								e.printStackTrace();
							}
						return mapping.findForward(FORWARD_JSP);
		}
}
