/**
 * LoanAction.java
 */

package com.wipro.igrs.loanadvance.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.common.LoanadvanceConstant;
import com.wipro.igrs.loanadvance.bd.LoanBD;
import com.wipro.igrs.loanadvance.bd.LoanadvanceBD;
import com.wipro.igrs.loanadvance.dto.LoanDTO;
import com.wipro.igrs.loanadvance.form.LoanForm;
import com.wipro.igrs.loanadvance.rule.ValidationRule;

/**
 * @author jagadish
 * 
 */
public class LoanAction extends BaseAction {
	/*
	 * Generated Methods
	 */

	LoanBD loanBD = null;

	LoanDTO loanDTO = null;

	ValidationRule rule = null;
	private Logger logger = (Logger) Logger.getLogger(LoanadvanceBD.class);
	/**
	 * Method execute
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		LoanForm loanForm = (LoanForm) form;// TODO Auto-generated method stub
		String pageAction = loanForm.getPageAction();		
		String pageName = loanForm.getPageName();
		String FORWARD_PAGE = null;
		ArrayList errorsList = null;
		try {
			if (request.getParameter("page") != null
					&& request.getParameter("page").equalsIgnoreCase(
							"loancomponentview")) {
				String componentId = request.getParameter("componentid");
				String getloanMasterComponentsStr = getloanMasterComponents(
						mapping, loanForm, request, response, session);
				return mapping.findForward(getloanMasterComponentsStr);
			}
			if (request.getParameter("page") != null
					&& request.getParameter("page").equalsIgnoreCase(
							"pendingloancomponents")) {
				String componentId = request.getParameter("componentid");
				String getLoanApprovalComponentsStr = getLoanApprovalComponents(
						mapping, loanForm, request, response, session);
				return mapping.findForward(getLoanApprovalComponentsStr);
			}

			if (pageName != null && pageName.equalsIgnoreCase("LoanMaster")) {
				if (pageAction != null
						&& pageAction.equalsIgnoreCase("LoanComponentDisplay")) {
					String loanComponentListStr = loanContentDisplay(mapping,
							loanForm, request, response, session);
					return mapping.findForward(loanComponentListStr);
				}
				if (pageAction != null
						&& pageAction.equalsIgnoreCase("LoanMasterAdd")) {
					String loanMasterExecuteStr = loanMasterExecute(mapping,
							loanForm, request, response, session);
					return mapping.findForward(loanMasterExecuteStr);
				}
				if (pageAction != null
						&& pageAction.equalsIgnoreCase("LoanMasterView")) {
					String loanMasterViewStr = loanMasterView(mapping,
							loanForm, request, response, session);
					return mapping.findForward(loanMasterViewStr);
				}
				if (pageAction != null
						&& pageAction.equalsIgnoreCase("LoanMasterEdit")) {
					String loanMasterEditStr = loanMasterEdit(mapping,
							loanForm, request, response, session);
					return mapping.findForward(loanMasterEditStr);
				}
				if (pageAction != null
						&& pageAction.equalsIgnoreCase("LoanMasterSubmit")) {
					String loanMasterSubmitStr = loanMasterSubmit(mapping,
							loanForm, request, response, session);
					return mapping.findForward(loanMasterSubmitStr);

				}

				if (pageAction != null
						&& pageAction
								.equalsIgnoreCase("LoanMasterComponentEdit")) {
					String editLoanMasterComponentsStr = editLoanMasterComponents(
							mapping, loanForm, request, response, session);
					return mapping.findForward(editLoanMasterComponentsStr);

				}
				if (pageAction != null
						&& pageAction
								.equalsIgnoreCase("LoanMastercomponentSubmit")) {
					String submitLoanMasterComponentsStr = submitLoanMasterComponents(
							mapping, loanForm, request, response, session);
					return mapping.findForward(submitLoanMasterComponentsStr);

				}

				if (pageAction != null
						&& pageAction.equalsIgnoreCase("loanApplyAdd")) {
					String loanApplyAddStr = loanApplyAdd(mapping, loanForm,
							request, response, session);
					return mapping.findForward(loanApplyAddStr);
				}

				if (pageAction != null
						&& pageAction.equalsIgnoreCase("getLoanAmount")) {
					String getMaxLoanAmountStr = getMaxLoanAmount(mapping,
							loanForm, request, response, session);
					return mapping.findForward(getMaxLoanAmountStr);
				}
				if (pageAction != null
						&& pageAction.equalsIgnoreCase("loanApplyView")) {
					String viewAppliedLoanStr = viewAppliedLoan(mapping,
							loanForm, request, response, session);
					return mapping.findForward(viewAppliedLoanStr);
				}

				if (pageAction != null
						&& pageAction.equalsIgnoreCase("loanApplyEdit")) {
					String editAppliedLoanStr = editAppliedLoan(mapping,
							loanForm, request, response, session);
					return mapping.findForward(editAppliedLoanStr);
				}

				if (pageAction != null
						&& pageAction.equalsIgnoreCase("loanApplySubmit")) {
					String submitAppliedLoanStr = submitAppliedLoan(mapping,
							loanForm, request, response, session);
					return mapping.findForward(submitAppliedLoanStr);
				}

				if (pageAction != null
						&& pageAction.equalsIgnoreCase("loanApprovalSearch")) {
					String loanApprovedSearchStr = loanApprovedSearch(mapping,
							loanForm, request, response, session);
					return mapping.findForward(loanApprovedSearchStr);

				}

				if (pageAction != null
						&& pageAction.equalsIgnoreCase("loanApproveSubmit")) {
					
					String getLoanApprovedSubmitStr = getLoanApprovedSubmit(
							mapping, loanForm, request, response, session);
					String loanStatus=loanDTO.getLoanstatus();
					if(loanStatus.equalsIgnoreCase("A"))
					{
					return mapping.findForward(getLoanApprovedSubmitStr);
					}
					else{
						getLoanApprovedSubmitStr="getLoanRejected";
						return mapping.findForward(getLoanApprovedSubmitStr);						
					}

				}
				if (pageAction != null
						&& pageAction.equalsIgnoreCase("loanStatus")) {
					String getLoanStatusStr = getLoanStatus(mapping, loanForm,
							request, response, session);
					return mapping.findForward(getLoanStatusStr);

				}
			}

		} catch (Exception e) {
			logger.info("General Exception Occurred" + e);
			e.printStackTrace();
		}
		if (FORWARD_PAGE == LoanadvanceConstant.DB_ERROR) {
			logger.info("FORWARD_PAGE:" + FORWARD_PAGE);
			logger.info("SQLException occcured");
			errorsList = new ArrayList();
			errorsList
					.add("SQLException Occurred!! Check the Log file to Debug");
			logger.info("---> errorsList!=null :" + (errorsList != null));
			FORWARD_PAGE = LoanadvanceConstant.ERRORS; // "error";
			session.setAttribute(LoanadvanceConstant.ERRORS, errorsList);
			return mapping.findForward(FORWARD_PAGE);
		}
		if (FORWARD_PAGE == null || FORWARD_PAGE == LoanadvanceConstant.ERRORS) {

			FORWARD_PAGE = LoanadvanceConstant.ERRORS; // "error";
			session.setAttribute(LoanadvanceConstant.ERRORS, errorsList);
		}

		logger.info("NOW FORWARDING TO :" + FORWARD_PAGE);
		return mapping.findForward(FORWARD_PAGE);

	}

	/**
	 * @param mapping
	 * @param loanForm
	 * @param request
	 * @param response
	 * @param session
	 * @return String
	 */
	private String getLoanApprovalComponents(ActionMapping mapping,
			LoanForm loanForm, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		String FORWARD_PAGE = null;
		ArrayList approvalComponentslist = null;
		ArrayList approvalLoanTypeList = null;
		try {
			loanBD = new LoanBD();
			if (request.getParameter("componentid") != null) {
				String componentId = request.getParameter("componentid");
				approvalComponentslist = loanBD
						.getLoanApprovalComponents(componentId);
				approvalLoanTypeList = loanBD.getLoanApprovalType(componentId);

			}

			if (approvalComponentslist != null)
				session.setAttribute("approvalComponentslist",
						approvalComponentslist);
			if (approvalLoanTypeList != null)
				session.setAttribute("approvalLoanTypeList",
						approvalLoanTypeList);

			FORWARD_PAGE = "loanApprovalView";

		} catch (Exception e) {
			e.printStackTrace();
		}
		return FORWARD_PAGE;
	}

	/**
	 * @param mapping
	 * @param loanForm
	 * @param request
	 * @param response
	 * @param session
	 * @return String
	 */
	private String loanContentDisplay(ActionMapping mapping, LoanForm loanForm,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		String FORWARD_PAGE = null;
		try {

			loanBD = new LoanBD();
			ArrayList getLoanComponentsList = loanBD
					.getLoanMasterComponentsList();
			if (getLoanComponentsList != null)
				request.setAttribute("LoanComponent", getLoanComponentsList);
			FORWARD_PAGE = "LoanComponentDisplay";

		} catch (Exception e) {
			e.printStackTrace();

		}

		return FORWARD_PAGE;
	}

	/**
	 * @param mapping
	 * @param loanForm
	 * @param request
	 * @param response
	 * @param session
	 * @return String
	 */
	private String loanMasterExecute(ActionMapping mapping, LoanForm loanForm,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		String FORWARD_PAGE = null;
		try {
			loanDTO = new LoanDTO();
			loanDTO = loanForm.getLoanDTO();
			session.setAttribute("LoanComponent", loanDTO);
			FORWARD_PAGE = "LoanMasterAdd";

		} catch (Exception e) {
			e.printStackTrace();
		}
		return FORWARD_PAGE;
	}

	/**
	 * @param mapping
	 * @param loanForm
	 * @param request
	 * @param response
	 * @param session
	 * @return String
	 */
	private String loanMasterView(ActionMapping mapping, LoanForm loanForm,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		String FORWARD_PAGE = null;
		try {
			rule = new ValidationRule();
			ArrayList errorsList = rule.validateloanMaster(loanForm);

			if (rule.getError()) {
				request.setAttribute(LoanadvanceConstant.ERRORS, errorsList);
				FORWARD_PAGE = "LoanMasterAdd";
			} else {
				loanDTO = loanForm.getLoanDTO();
				session.setAttribute("LoanComponent", loanDTO);

				FORWARD_PAGE = "LoanMasterView";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return FORWARD_PAGE;
	}

	/**
	 * @param mapping
	 * @param loanForm
	 * @param request
	 * @param response
	 * @param session
	 * @return String
	 */
	private String loanMasterEdit(ActionMapping mapping, LoanForm loanForm,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		String FORWARD_PAGE = null;
		try {

			if (session.getAttribute("LoanComponent") != null) {
				loanDTO = (LoanDTO) session.getAttribute("LoanComponent");
				session.setAttribute("LoanComponent", loanDTO);
			}
			// if(loanDTO !=null)

			FORWARD_PAGE = "LoanMasterAdd";

		} catch (Exception e) {
			e.printStackTrace();
		}
		return FORWARD_PAGE;

	}

	/**
	 * @param mapping
	 * @param loanForm
	 * @param request
	 * @param response
	 * @param session
	 * @return String
	 */
	private String loanMasterSubmit(ActionMapping mapping, LoanForm loanForm,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		String FORWARD_PAGE = null;
		try {
			loanBD = new LoanBD();
			if (session.getAttribute("LoanComponent") != null) {
				loanDTO = (LoanDTO) session.getAttribute("LoanComponent");
				boolean b = loanBD.insertLoanMasterComponents(loanDTO, session
						.getAttribute("UserId").toString());

			}
			FORWARD_PAGE = "LoanMasterSubmit";

		} catch (Exception e) {
			e.printStackTrace();

		}
		return FORWARD_PAGE;
	}

	/**
	 * @param mapping
	 * @param loanForm
	 * @param request
	 * @param response
	 * @param session
	 * @return String
	 */
	private String getloanMasterComponents(ActionMapping mapping,
			LoanForm loanForm, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		String FORWARD_PAGE = null;
		ArrayList getLoanMasterComponentsList = null;
		try {
			loanBD = new LoanBD();
			if (request.getParameter("componentid") != null) {
				String componentId = request.getParameter("componentid");
				getLoanMasterComponentsList = loanBD
						.getLoanMasterComponentsListForUpdate(componentId);

			}
			if (getLoanMasterComponentsList != null) {

				session.setAttribute("LoanComponents",
						getLoanMasterComponentsList);
				FORWARD_PAGE = "LoanMasterComponentView";
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return FORWARD_PAGE;
	}

	/**
	 * @param mapping
	 * @param loanForm
	 * @param request
	 * @param response
	 * @param session
	 * @return String
	 */
	private String editLoanMasterComponents(ActionMapping mapping,
			LoanForm loanForm, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		String FORWARD_PAGE = null;
		ArrayList loanComponentsList = null;
		try {
			if (session.getAttribute("LoanComponents") != null) {
				loanComponentsList = (ArrayList) session
						.getAttribute("LoanComponents");

			}
			if (loanComponentsList != null)
				session.setAttribute("LoanComponents", loanComponentsList);
			FORWARD_PAGE = "LoanMasterComponentEdit";

		} catch (Exception e) {
			e.printStackTrace();
		}

		return FORWARD_PAGE;
	}

	/**
	 * @param mapping
	 * @param loanForm
	 * @param request
	 * @param response
	 * @param session
	 * @return String
	 */
	private String submitLoanMasterComponents(ActionMapping mapping,
			LoanForm loanForm, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		String FORWARD_PAGE = null;
		ArrayList updatedList = null;

		try {
			if (session.getAttribute("LoanComponents") != null) {
				loanDTO = loanForm.getLoanDTO();
				updatedList = (ArrayList) session
						.getAttribute("LoanComponents");
				
				loanBD = new LoanBD();
				boolean update = loanBD.updateLoanComponents(updatedList,
						loanDTO, session.getAttribute("UserId").toString());
				

			}
			FORWARD_PAGE = "LoanMasterComponentSubmit";
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return FORWARD_PAGE;
	}

	/**
	 * @param mapping
	 * @param loanForm
	 * @param request
	 * @param response
	 * @param session
	 * @return String
	 */
	private String loanApplyAdd(ActionMapping mapping, LoanForm loanForm,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		String FORWARD_PAGE = null;
		ArrayList loanNameList = null;
		try {

			loanBD = new LoanBD();
			loanDTO = new LoanDTO();

			session.removeAttribute("loanMaxAmountList");
			loanDTO = loanForm.getLoanDTO();
			loanNameList = loanBD.getLoanType();
			if (loanNameList != null) {
				session.setAttribute("loanNameList", loanNameList);

			}
			session.setAttribute("LoanComponent", loanDTO);

			FORWARD_PAGE = "loanApplyAdd";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return FORWARD_PAGE;
	}

	/**
	 * @param mapping
	 * @param loanForm
	 * @param request
	 * @param response
	 * @param session
	 * @return String
	 */
	private String getMaxLoanAmount(ActionMapping mapping, LoanForm loanForm,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		String FORWARD_PAGE = null;
		ArrayList loanMaxAmountList = null;
		try {

			loanDTO = loanForm.getLoanDTO();

			loanBD = new LoanBD();
			loanMaxAmountList = loanBD.getMaxLoanAmount(loanDTO.getLoanid());

			if (loanMaxAmountList != null) {
				session.setAttribute("LoanComponent", loanDTO);
				session.setAttribute("loanMaxAmountList", loanMaxAmountList);

			}
			FORWARD_PAGE = "loanApplyAdd";

		} catch (Exception e) {
			e.printStackTrace();
		}
		return FORWARD_PAGE;
	}

	/*
	 * private String validateAppliedLoan(ActionMapping mapping, LoanForm
	 * loanForm, HttpServletRequest request, HttpServletResponse response,
	 * HttpSession session) {
	 * 
	 * String FORWARD_PAGE = null; try { rule = new ValidationRule(); ArrayList
	 * errorsList = rule.validateAppliedloan(loanForm);
	 * 
	 * if (rule.getError() || (errorsList != null && errorsList.size() > 0)) {
	 * request.setAttribute(LoanadvanceConstant.ERRORS, errorsList);
	 * FORWARD_PAGE = "loanApplyAdd"; } else {
	 * if(session.getAttribute("LoanComponent")!=null) {
	 * loanDTO=loanForm.getLoanDTO();
	 * session.setAttribute("LoanComponent",loanDTO); }
	 * FORWARD_PAGE="loanApplyView"; } } catch (Exception e) {
	 * e.printStackTrace(); } return FORWARD_PAGE; }
	 */

	/**
	 * @param mapping
	 * @param loanForm
	 * @param request
	 * @param response
	 * @param session
	 * @return String
	 */
	private String viewAppliedLoan(ActionMapping mapping, LoanForm loanForm,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		String FORWARD_PAGE = null;

		try {

			rule = new ValidationRule();
			ArrayList errorsList = rule.validateAppliedloan(loanForm);

			if (rule.getError()) {
				request.setAttribute(LoanadvanceConstant.ERRORS, errorsList);
				if (session.getAttribute("LoanComponent") != null) {
					loanDTO = loanForm.getLoanDTO();
					session.setAttribute("LoanComponent", loanDTO);

				}
				FORWARD_PAGE = "loanApplyAdd";
			} else {

				if (session.getAttribute("LoanComponent") != null) {
					loanDTO = loanForm.getLoanDTO();
					session.setAttribute("LoanComponent", loanDTO);

				}
				FORWARD_PAGE = "loanApplyView";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return FORWARD_PAGE;

	}

	/**
	 * @param mapping
	 * @param loanForm
	 * @param request
	 * @param response
	 * @param session
	 * @return String
	 */
	private String submitAppliedLoan(ActionMapping mapping, LoanForm loanForm,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		String FORWARD_PAGE = null;
		String txnId = "";
		try {

			loanBD = new LoanBD();

			if (session.getAttribute("LoanComponent") != null) {
				loanDTO = (LoanDTO) session.getAttribute("LoanComponent");
				session.setAttribute("LoanComponent", loanDTO);

			}
			txnId = loanBD.insertAppliedLoan(loanDTO, session.getAttribute(
					"UserId").toString());
			if (!txnId.equals("")) {
				request.setAttribute("txnId", txnId);
				FORWARD_PAGE = "loanApplySubmit";

			} else {

				FORWARD_PAGE = "loanApplyAdd";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return FORWARD_PAGE;

	}

	/**
	 * @param mapping
	 * @param loanForm
	 * @param request
	 * @param response
	 * @param session
	 * @return String
	 */
	private String editAppliedLoan(ActionMapping mapping, LoanForm loanForm,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		String FORWARD_PAGE = null;

		try {

			if (session.getAttribute("LoanComponent") != null) {
				loanDTO = (LoanDTO) session.getAttribute("LoanComponent");
				session.setAttribute("LoanComponent", loanDTO);

			}
			FORWARD_PAGE = "loanApplyAdd";

		} catch (Exception e) {
			e.printStackTrace();
		}
		return FORWARD_PAGE;

	}

	/**
	 * @param mapping
	 * @param loanForm
	 * @param request
	 * @param response
	 * @param session
	 * @return String
	 */
	private String loanApprovedSearch(ActionMapping mapping, LoanForm loanForm,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		ArrayList loanList = null;
		String FORWARD_PAGE = null;
		try {
			session.removeAttribute("loanList");
			loanBD = new LoanBD();
			rule = new ValidationRule();
			ArrayList errorsList = rule.chkloanempId(loanForm);
			loanDTO = loanForm.getLoanDTO();
			if (rule.getError()) {
				request.setAttribute(LoanadvanceConstant.ERRORS, errorsList);
				if (loanDTO.getEmpid() != null) {
					loanList = loanBD.getLoanDetails(loanDTO.getEmpid());
				}

				if (loanList != null) {
					session.setAttribute("loanList", loanList);
				}
				FORWARD_PAGE = "loanApprovalSearch";
			} else

			if (loanDTO.getEmpid() != null) {
				loanList = loanBD.getLoanDetails(loanDTO.getEmpid());
			}

			if (loanList != null) {
				session.setAttribute("loanList", loanList);
			}
			FORWARD_PAGE = "loanApprovalSearch";

		} catch (Exception e) {
			e.printStackTrace();
		}
		return FORWARD_PAGE;
	}

	/**
	 * @param mapping
	 * @param loanForm
	 * @param request
	 * @param response
	 * @param session
	 * @return String
	 */
	private String getLoanApprovedSubmit(ActionMapping mapping,
			LoanForm loanForm, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		String FORWARD_PAGE = null;
		ArrayList ApproveList = null;

		try {
			loanBD = new LoanBD();
			loanDTO = loanForm.getLoanDTO();
			if (session.getAttribute("approvalComponentslist") != null) {
				ApproveList = (ArrayList) session
						.getAttribute("approvalComponentslist");
				boolean b = loanBD.getLoanApproved(ApproveList, loanDTO,
						session.getAttribute("UserId").toString());

			}
			FORWARD_PAGE = "loanApproveSubmit";

		} catch (Exception e) {
			e.printStackTrace();
		}
		return FORWARD_PAGE;
	}

	/**
	 * @param mapping
	 * @param loanForm
	 * @param request
	 * @param response
	 * @param session
	 * @return String
	 */
	private String getLoanStatus(ActionMapping mapping, LoanForm loanForm,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		String FORWARD_PAGE = null;
		ArrayList loanDetailsList = null;
		ArrayList loanPaymentDetailsList = null;
		ArrayList loantypeList = null;

		try {
			session.removeAttribute("loanDetailsList");
			session.removeAttribute("loanPaymentDetailsList");
			session.removeAttribute("loantypeList");
			loanBD = new LoanBD();
			rule = new ValidationRule();
			ArrayList errorsList = rule.loanReferenceId(loanForm);
			loanDTO = loanForm.getLoanDTO();
			if (rule.getError()) {
				request.setAttribute(LoanadvanceConstant.ERRORS, errorsList);

				if (loanDTO.getLoantxnid() != null) {
					loantypeList = loanBD.getLoanType(loanDTO.getLoantxnid());

					if (loantypeList.size() == 0) {
						errorsList
								.add("<font color='red'>. Please Enter Valid Reference Number");
					} else {
						loanDetailsList = loanBD.getLoanStatusDetails(loanDTO
								.getLoantxnid());
						loanPaymentDetailsList = loanBD
								.getLoanPaymentDetails(loanDTO.getLoantxnid());

					}
				}
				if (loanDetailsList != null)
					session.setAttribute("loanDetailsList", loanDetailsList);
				if (loanPaymentDetailsList != null)
					session.setAttribute("loanPaymentDetailsList",
							loanPaymentDetailsList);
				if (loantypeList != null)
					session.setAttribute("loantypeList", loantypeList);
				FORWARD_PAGE = "loanStatus";
			} else

			if (loanDTO.getLoantxnid() != null) {
				loantypeList = loanBD.getLoanType(loanDTO.getLoantxnid());

				loanDetailsList = loanBD.getLoanStatusDetails(loanDTO
						.getLoantxnid());
				loanPaymentDetailsList = loanBD.getLoanPaymentDetails(loanDTO
						.getLoantxnid());

			}
			if (loanDetailsList != null)
				session.setAttribute("loanDetailsList", loanDetailsList);
			if (loanPaymentDetailsList != null)
				session.setAttribute("loanPaymentDetailsList",
						loanPaymentDetailsList);
			if (loantypeList != null)
				session.setAttribute("loantypeList", loantypeList);
			FORWARD_PAGE = "loanStatus";

		} catch (Exception e) {
			e.printStackTrace();
		}
		return FORWARD_PAGE;
	}
}