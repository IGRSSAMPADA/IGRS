package com.wipro.igrs.willdeposit.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.willdeposit.bd.PaymentBD;
import com.wipro.igrs.willdeposit.bd.WillRetrievalBD;
import com.wipro.igrs.willdeposit.dto.ChallanDTO;
import com.wipro.igrs.willdeposit.dto.WillDepositDTO;
import com.wipro.igrs.willdeposit.form.PaymentForm;

/**
 * <b>Note</b>: This class is no longer in use <br/>
 * instead refer to {@link WillDepositePaymentAction}
 * @author WIPRO Java Team
 *
 */
public class WillDepositPaymentAction extends BaseAction {

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
	private Logger logger = (Logger) Logger
			.getLogger(WillDepositPaymentAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,HttpSession session)
			throws NullPointerException, IOException {
		PaymentForm paymentForm = (PaymentForm) form;
		PaymentBD paymentBD = new PaymentBD();
		ChallanDTO challanDTO;
		String checkFlag = null;
		String getStatus = null;
		int flag = 0;
		double amt = 2000;
		double diffAmt = 0.0;
		String button = null;

		String returnFlag = "";
		try {

			ArrayList challanList = new ArrayList();
			challanList = paymentForm.getPaymentList();
			session.setAttribute("challanList", challanList);

			session.setAttribute("amt", "" + amt);
			int rowsize = paymentForm.getPaymentList().size();

			if ("".equalsIgnoreCase(paymentForm.getChallancheck())) {
				checkFlag = "true";
			}
			session.setAttribute("checkFlag", checkFlag);
			String rowSize = String.valueOf(rowsize);
			session.setAttribute("rowsize", rowSize);
			if ("process".equalsIgnoreCase(paymentForm.getPaymentDTO()
					.getSelectButton())
					&& "payConfirmation".equalsIgnoreCase(paymentForm
							.getPaymentDTO().getPage())) {
				session.setAttribute("status", getStatus);
				paymentForm.getPaymentDTO().setSelectButton("");
				return mapping.findForward("srologin");
			}
			if ("paypayment".equalsIgnoreCase(paymentForm.getPaymentDTO()
					.getSelectButton())
					&& "payFail".equalsIgnoreCase(paymentForm.getPaymentDTO()
							.getPage())) {
				session.setAttribute("status", getStatus);
				paymentForm.getPaymentDTO().setSelectButton("");
				String failflag = "fail";
				session.setAttribute("failflag", failflag);
				return mapping.findForward("srologin");
			}
			if ("cancel".equalsIgnoreCase(paymentForm.getPaymentDTO()
					.getSelectButton())
					&& "payFail".equalsIgnoreCase(paymentForm.getPaymentDTO()
							.getPage())) {
				session.setAttribute("status", getStatus);
				paymentForm.getPaymentDTO().setSelectButton("");
				return mapping.findForward("srologin");
			}

			if ("cashbutton".equalsIgnoreCase(paymentForm.getPaymentDTO()
					.getCheckButton())) {
				System.out.println("your selected Button is "
						+ paymentForm.getPaymentDTO().getCheckButton());
				getStatus = paymentBD.getCashDetails(paymentForm);
				paymentForm.getPaymentDTO().setCheckButton("");
				paymentForm.getPaymentDTO().setReceiptID(
						paymentForm.getPaymentDTO().getReceiptID());
				paymentForm.setCashDate(paymentForm.getCashDate());
				paymentForm.getPaymentDTO().setAmount(
						paymentForm.getPaymentDTO().getAmount());
				session.setAttribute("status", getStatus);
				return mapping.findForward("srologin");
			}

			if ("User".equalsIgnoreCase(paymentForm.getPaymentDTO().getUser())
					&& (paymentForm.getPaymentList().size() > 0)) {
				for (int i = 0; i < paymentForm.getPaymentList().size(); i++) {
					String sbutton = String.valueOf(i);
					if (sbutton.equalsIgnoreCase(paymentForm.getPaymentDTO()
							.getCheckButton())) {
						getStatus = paymentBD.getChallanNoDetails(paymentForm,
								i);
						paymentForm.getPaymentDTO().setCheckButton("");
						paymentForm.getPaymentDTO().setReceiptID(
								paymentForm.getPaymentDTO().getReceiptID());
						paymentForm.setCashDate(paymentForm.getCashDate());
						paymentForm.getPaymentDTO().setAmount(
								paymentForm.getPaymentDTO().getAmount());
						session.setAttribute("status", getStatus);
						return mapping.findForward("userlogin");
					}
				}
			}

			// Fetch the transaction ID upon successful transaction.
			if (paymentForm.getPaymentList().size() > 0) {
				for (int i = 0; i < paymentForm.getPaymentList().size(); i++) {
					String sbutton = String.valueOf(i);
					logger.debug("sbutton:-      " + sbutton);
					if (sbutton.equalsIgnoreCase(paymentForm.getPaymentDTO()
							.getCheckButton())) {
						getStatus = paymentBD.getChallanNoDetails(paymentForm,
								i);
						logger
								.debug("CALLED:ACTION:    paymentBD.getChallanNoDetails");
						logger.debug("getStatus:-   " + getStatus);
						paymentForm.getPaymentDTO().setCheckButton("");
						paymentForm.getPaymentDTO().setReceiptID(
								paymentForm.getPaymentDTO().getReceiptID());
						paymentForm.setCashDate(paymentForm.getCashDate());
						paymentForm.getPaymentDTO().setAmount(
								paymentForm.getPaymentDTO().getAmount());
						session.setAttribute("status", getStatus);
						return mapping.findForward("proceedPayment");
					}
				}
			}

			if ("cash".equalsIgnoreCase(paymentForm.getPaymentDTO()
					.getSelectType())) {
				getStatus = paymentBD.getCashDetails(paymentForm);

				if (getStatus.equalsIgnoreCase("fail")) {
					session.setAttribute("status", getStatus);
					return mapping.findForward("checkPayment");
				} else {
					System.out.println("the Status value is Action "
							+ getStatus);
					session.setAttribute("status", getStatus);
					return mapping.findForward("processPayment");

				}
			}

			// AVAILABILITY STATUS CHECK FOR THE CHALLAN PAYMENT.
			if ("challan".equalsIgnoreCase(paymentForm.getPaymentDTO()
					.getSelectType())) {

				logger.debug("Action Value Matched for:-    "
						+ paymentForm.getPaymentDTO().getSelectType());
				getStatus = paymentBD.getChallanDetails(paymentForm);
				logger.debug("Action the status value is " + getStatus);
				if (getStatus.equalsIgnoreCase("fail")) {
					session.setAttribute("status", getStatus);
					return mapping.findForward("checkPayment");
				} else {
					session.setAttribute("status", getStatus);
					double totamt = paymentForm.getTotAmt();
					String finalamt = String.valueOf(totamt);
					session.setAttribute("totamt", finalamt);

//					HttpSession sesion = request.getSession(true);
					WillDepositDTO formDTO = (WillDepositDTO) session
							.getAttribute("formDTO");
					logger.debug("formDTO().getRetrieverType():-    "
							+ formDTO.getRetrieverType());

					WillRetrievalBD retBD = new WillRetrievalBD();
					boolean citizenUpdated = retBD
							.updateCitizenDetails(formDTO, formDTO.getRetWillId(), finalamt);

					if (citizenUpdated) {
						logger
								.debug("................Citizen details got updated....................");
					}

					return mapping.findForward("paymentSuccess");
				}
			}

			if ("User".equalsIgnoreCase(paymentForm.getPaymentDTO().getUser())) {
				getStatus = paymentBD.getChallanDetails(paymentForm);
				if (getStatus.equalsIgnoreCase("fail")) {
					session.setAttribute("status", getStatus);
					return mapping.findForward("checkPayment");
				} else {
					session.setAttribute("status", getStatus);
					return mapping.findForward("processPayment");
				}
			}
		} catch (NullPointerException ne) {
			logger.error("NullPoinetr Exception at ACTION  " + ne.getMessage());
		} catch (Exception e) {
			logger.error(" Exception at ACTION  " + e.getMessage());
		}

		return mapping.findForward("srologin");
	}
}
