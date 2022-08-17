package com.wipro.igrs.willdeposit.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.willdeposit.bd.WillDepositBD;
import com.wipro.igrs.willdeposit.dto.ChallanDTO;
import com.wipro.igrs.willdeposit.dto.WillDepositDTO;
import com.wipro.igrs.willdeposit.form.PaymentForm;

@SuppressWarnings("all")
public class WillDepositePaymentAction extends BaseAction {

	private Logger logger = (Logger) Logger
			.getLogger(WillDepositePaymentAction.class);

	/**
	 * This is the main action called from the Struts framework.
	 * 
	 * @param mapping
	 *            The ActionMapping used to select this instance.
	 * @param form
	 *            The optional ActionForm bean for this request.
	 * @param request
	 *            The HTTP Request we are processing.
	 * @param response
	 *            The HTTP Response we are processing.
	 */

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws NullPointerException, IOException {
		PaymentForm paymentForm = (PaymentForm) form;
		// PaymentBD paymentBD = new PaymentBD();
		ChallanDTO challanDTO;
		String checkFlag = null;
		String getStatus = null;
		int flag = 0;
		double amt = 2000;
		double diffAmt = 0.0;
		String button = null;

		String roleId = (String) session.getAttribute("loggedInRole");
		String funId = (String) session.getAttribute("functionId");
		String userId = (String) session.getAttribute("UserId");
		String nextAction = request.getParameter("paymentStatus"); //added on 18Feb13
		String hidNextMode = paymentForm.getHidNextMode();
		String returnFlag = "";
		try {
			if("gotoPaymentReadOnly".equals(hidNextMode)) {
				return mapping.findForward("proceedPayment");
			}
			if("cancelToHome".equals(hidNextMode)) {
				return mapping.findForward("proceedPayment");
			}
			if("proceedPayment".equals(nextAction)) {
				return mapping.findForward("proceedPayment");
			}
			if (nextAction != null	&& nextAction.equalsIgnoreCase("P")) {
			getStatus = (String) session.getAttribute("status");			
			WillDepositDTO formDTO = (WillDepositDTO) session.getAttribute("formDTO");
			request.setAttribute("depWillID", formDTO.getWillId());
			String willId=(String)request.getAttribute("depWillID");
			WillDepositBD bd= new WillDepositBD();
			//method area
			//request.setAttribute("PGTYP", "willRetUpdate");
			
			String status=bd.checkStatus(willId, funId);
			return mapping.findForward(status);
			
			}
		} catch (NullPointerException ne) {
			logger.error("NullPoinetr Exception at ACTION  " + ne.getMessage());
		} catch (Exception e) {
			logger.error(" Exception at ACTION  " + e.getMessage());
		}

		return mapping.findForward("srologin");
	}
}
