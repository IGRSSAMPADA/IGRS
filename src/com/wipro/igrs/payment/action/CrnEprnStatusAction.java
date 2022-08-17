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
import com.wipro.igrs.payment.bo.CrnEprnStatusBO;
import com.wipro.igrs.payment.dto.CrnEprnStatusDTO;
import com.wipro.igrs.payment.form.CrnEprnStatusForm;

/**
 * ===========================================================================
 * File           :   CrnEprnStatusAction.java
 * Description    :   Represents the  Status of CRN or EPRN Number
 * Author         :   Thilak Reddy
 * Created Date   :   Feb 02, 2017

 * ===========================================================================
 */

public class CrnEprnStatusAction extends BaseAction{
	
	 /**This is the main action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     */

	
	/**
	 * @see forwardJsp is used for redirecting
	 */
	private String forwardJsp = "";
	/**
	 * @author Thilak
	 */
	private Logger logger = (Logger) Logger
	.getLogger(CrnEprnStatusAction.class);

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @exception Exception
	 * @return ActionForward
	 */

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		// TODO Auto-generated method stub
				
		if (form != null) {
			String language=(String)session.getAttribute("languageLocale");
			CrnEprnStatusForm crnEprnStatusForm = (CrnEprnStatusForm) form;
			crnEprnStatusForm.getCrnEprnStatusDTO().setCheck("");
			
			CrnEprnStatusBO crnEprnStatusBO = new CrnEprnStatusBO();
			String userId = (String) session.getAttribute("UserId");
			crnEprnStatusForm.setUserId(userId);
			CrnEprnStatusDTO crnEprnStatusDTO = new CrnEprnStatusDTO();
			forwardJsp = "search";
			System.out.println("Before action");
			String formName = crnEprnStatusDTO.getFormName();
			String actionName = "";
			if (request.getParameter("actionName") != null) {
				actionName = request.getParameter("actionName");
			}
            else
			{
               actionName = crnEprnStatusDTO.getActionName();
               crnEprnStatusForm.getCrnEprnStatusDTO().setPaymentMode("");
       		   crnEprnStatusForm.getCrnEprnStatusDTO().setCrnEprnNo("");
			}
			String modName = (String) request.getParameter("modName");
			logger.debug("modName:-" + modName);
			String fnName = (String) request.getParameter("fnName");
			logger.debug("fnName:-" + fnName);

			if (modName != null && fnName != null) {
				//crnEprnStatusForm.setPaymentModeList(crnEprnStatusBO.getCrnEprnList(language));
				session.setAttribute("modName", modName);
				session.setAttribute("fnName", fnName);
			}
			
		if("SEARCH_ACTION".equalsIgnoreCase(actionName))
		{
			String crnEprnNo = crnEprnStatusForm.getCrnEprnStatusDTO().getCrnEprnNo();
			String paymentModeId = crnEprnStatusForm.getCrnEprnStatusDTO().getPaymentMode();
			logger.debug(actionName);
			logger.debug(crnEprnNo);
			logger.debug(paymentModeId);
			String tableName=null;
			String parentKeyStr=null;
			int parentKey=0;
			String parentColumn=null;
			ArrayList rowList=null;
			ArrayList paymentDetails= crnEprnStatusBO.getPaymentDetails(paymentModeId,crnEprnNo); //obtaining payment details
			logger.debug("Payment Details Obtained"+paymentDetails);
			if (paymentDetails.size() == 0)
			{
				crnEprnStatusForm.getCrnEprnStatusDTO().setViewCrnEprnDetails(null);
				crnEprnStatusForm.getCrnEprnStatusDTO().setCheck("Y");    //setting check if there is no info about payment status
				logger
				.debug("----------------> no payment details found");
				
				logger.debug("-------------->" + forwardJsp);
			}
			else 
			{
				CrnEprnStatusDTO dto = new CrnEprnStatusDTO();
				for (int i = 0; i < paymentDetails.size(); i++) {
					dto=(CrnEprnStatusDTO)paymentDetails.get(i);
					String paymentFlag = (String)dto.getPaymentFlag();
					
					if("D".equalsIgnoreCase(paymentFlag))
					{
						crnEprnStatusForm.getCrnEprnStatusDTO().setCheck("A");    //setting check if payment is processed
						crnEprnStatusForm.getCrnEprnStatusDTO().setViewCrnEprnDetails(
								paymentDetails);
					    
					}
					else
					{
						crnEprnStatusForm.getCrnEprnStatusDTO().setCheck("N"); //setting check if payment is still in process
						logger
						.debug("----------------> payment process not completed");
						
						logger.debug("-------------->" + forwardJsp);						
					   
					}
					
					
				}
								
			}
			System.out.println("Inside action");
		
	  }
		
	}
		
		return mapping.findForward(forwardJsp);

  }
}
