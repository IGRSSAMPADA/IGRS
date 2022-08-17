/**
 * LoanadvanceAction.java
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
import com.wipro.igrs.loanadvance.bd.LoanadvanceBD;
import com.wipro.igrs.loanadvance.dto.LoanadvanceDTO;
import com.wipro.igrs.loanadvance.form.LoanadvanceForm;
import com.wipro.igrs.loanadvance.rule.ValidationRule;


/**
 * @author jagadish
 *Jun 26, 2008
 * 
 */
public class LoanadvanceAction extends BaseAction {

	LoanadvanceDTO	loanadvanceDTO	= null;

	LoanadvanceBD	loanadvanceBD	= null;

	ValidationRule	rule			= null;
	private Logger logger = (Logger) Logger.getLogger(LoanadvanceAction.class);
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
		LoanadvanceForm loanadvanceForm = (LoanadvanceForm) form;
		String pageName = loanadvanceForm.getPageName();
		String pageAction = loanadvanceForm.getPageAction();
		loanadvanceDTO = loanadvanceForm.getLoanadvanceDTO();
		String[] empClass={};
		
		 if (request.getParameterValues("applicableClass") != null)    
		 {   
		  empClass = (String[]) request.getParameterValues("applicableClass");    
			   /*if (empClass.length > 0)    
			   {           
			      for (int i=0 ; i<empClass.length ; i++)       
			 	{        
				 String currentItem = empClass[i];        
			     System.out.println("currentItem = "+currentItem);
				}    
			   }  */  
		 }
		 
		 loanadvanceDTO.setApplicableClass(empClass);
	//	HttpSession session = request.getSession(true);
		ArrayList errorsList = null;
		String FORWARD_PAGE = null;

		try {

			if (request.getParameter("page") != null
					&& request.getParameter("page").equalsIgnoreCase(
							"advanceview")) {
				String advancetypeid = request.getParameter("adid");

				String advanceApprovalResultStr = advanceApprovalResult(
						mapping, loanadvanceForm, request, response, session);
				return mapping.findForward(advanceApprovalResultStr);

			}
			if (request.getParameter("page") != null
					&& request.getParameter("page").equalsIgnoreCase(
							"advancecomponentview")) {
				String componentId = request.getParameter("componentid");
				String retrieveAdvanceMasterComponentsStr = retrieveAdvanceMasterComponents(
						mapping, loanadvanceForm, request, response, session);
				return mapping.findForward(retrieveAdvanceMasterComponentsStr);
			}

			if (pageName != null && pageName.equalsIgnoreCase("AdvanceMaster")) {
				if (pageAction != null
						&& pageAction.equalsIgnoreCase("AdvanceMasterDisplay")) {
					String advanceMasterDisplayStr = advanceMasterDisplay(
							loanadvanceForm, request, response, loanadvanceBD,
							session);
					return mapping.findForward(advanceMasterDisplayStr);
				}
				if (pageAction != null
						&& pageAction.equalsIgnoreCase("AdvanceMasterAddPage")) {
					String advanceMasterAddStr = advanceMasterAdd(mapping,
							loanadvanceForm, request, response, session);
					return mapping.findForward(advanceMasterAddStr);
				}

				if (pageAction != null
						&& pageAction.equalsIgnoreCase("AdvanceMasterAdd")) {
					String advanceMasterAddStr = advanceMasterInsert(mapping,
							loanadvanceForm, request, response, session);
					return mapping.findForward(advanceMasterAddStr);
				}
				if (pageAction != null
						&& pageAction.equalsIgnoreCase("AdvanceMasterView")) {
					String advanceMasterViewStr = advanceMasterView(mapping,
							loanadvanceForm, request, response, session);
					return mapping.findForward(advanceMasterViewStr);
				}
				if (pageAction != null
						&& pageAction.equalsIgnoreCase("AdvanceMasterEdit")) {
					String advanceMasterEditStr = advanceMasterEdit(mapping,
							loanadvanceForm, request, response, session);
					return mapping.findForward(advanceMasterEditStr);
				}
				if (pageAction != null
						&& pageAction.equalsIgnoreCase("AdvavceMasterSubmit")) {
					String advanceMasterSubmitStr = advanceMasterSubmit(
							mapping, loanadvanceForm, request, response,
							session);
					return mapping.findForward(advanceMasterSubmitStr);
				}

				if (pageAction != null
						&& pageAction.equalsIgnoreCase("appliedAdvanceInsert")) {
					String executeApplyAdvanceStr = executeApplyAdvance(
							mapping, loanadvanceForm, request, response,
							session);
					return mapping.findForward(executeApplyAdvanceStr);
				}
				if (pageAction != null
						&& pageAction.equalsIgnoreCase("appliedAdvanceView")) {
					String viewApplyAdvanceStr = viewApplyAdvance(mapping,
							loanadvanceForm, request, response, session);
					return mapping.findForward(viewApplyAdvanceStr);
				}
				if (pageAction != null
						&& pageAction.equalsIgnoreCase("appliedAdvanceEdit")) {
					String editApplyAdvanceStr = editApplyAdvance(mapping,
							loanadvanceForm, request, response, session);
					return mapping.findForward(editApplyAdvanceStr);
				}
				if (pageAction != null
						&& pageAction.equalsIgnoreCase("appliedAdvanceSubmit")) {
					String submitApplyAdvance = submitApplyAdvance(mapping,
							loanadvanceForm, request, response, session);
					return mapping.findForward(submitApplyAdvance);
				}

				if (pageAction != null
						&& pageAction.equalsIgnoreCase("advanceApprovalSearch")) {
					String searchEmpApproveAdvanceStr = searchEmpApproveAdvance(
							mapping, loanadvanceForm, request, response,
							session);
					return mapping.findForward(searchEmpApproveAdvanceStr);
				}

				if (pageAction != null
						&& pageAction
								.equalsIgnoreCase("appliedAdvanceApproveSubmit")) {
					String submitAdvanceApprovalStr = submitAdvanceApproval(
							mapping, loanadvanceForm, request, response,
							session);
					session.setAttribute("Status", loanadvanceDTO.getAdvancestatus());
					session.setAttribute("Remarks", loanadvanceDTO.getAdvancedescription());
					return mapping.findForward(submitAdvanceApprovalStr);
				}

				if (pageAction != null
						&& pageAction
								.equalsIgnoreCase("advanceMasterComponentsEdit")) {
					String editAdvanceMasterComponentsStr = editAdvanceMasterComponents(
							mapping, loanadvanceForm, request, response,
							session);
					return mapping.findForward(editAdvanceMasterComponentsStr);
				}
				if (pageAction != null
						&& pageAction
								.equalsIgnoreCase("advanceMasterComponentsAfterEdit")) {
					String afterEditAddComponentStr = afterEditAddComponent(
							mapping, loanadvanceForm, request, response,
							session);
					return mapping.findForward(afterEditAddComponentStr);
				}
				if (pageAction != null
						&& pageAction
								.equalsIgnoreCase("advanceComponentDetailsView")) {
					String retrieveAdvanceMasterComponentsStr = retrieveAdvanceMasterComponents(
							mapping, loanadvanceForm, request, response,
							session);
					return mapping
							.findForward(retrieveAdvanceMasterComponentsStr);
				}
				if (pageAction != null
						&& pageAction
								.equalsIgnoreCase("advanceComponentDetailsSubmit")) {
					String submitAdvanceMasterComponentsStr = submitAdvanceMasterComponents(
							mapping, loanadvanceForm, request, response,
							session);
					return mapping
							.findForward(submitAdvanceMasterComponentsStr);
				}

				if (pageAction != null
						&& pageAction.equalsIgnoreCase("advanceStatusPage")) {
					String searchAdvanceStatusStr = searchAdvanceStatus(
							mapping, loanadvanceForm, request, response,
							session);
					return mapping.findForward(searchAdvanceStatusStr);
				}

				if (pageAction != null
						&& pageAction.equalsIgnoreCase("fetchAdvanceAmount")) {

					String fetchAdvanceAmountStr = fetchAdvanceAmount(mapping,
							loanadvanceForm, request, response, session);
					return mapping.findForward(fetchAdvanceAmountStr);
				}

				if (pageAction != null
						&& pageAction
								.equalsIgnoreCase("checkAppliedAdvanceDetails")) {

					String checkAppliedAdvanceDetails = checkAppliedAdvanceDetails(
							mapping, loanadvanceForm, request, response,
							session);
					return mapping.findForward(checkAppliedAdvanceDetails);
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
	 * @param loanadvanceForm
	 * @param request
	 * @param response
	 * @param loanadvanceBD
	 * @param session
	 * @return String
	 */
	private String advanceMasterDisplay(LoanadvanceForm loanadvanceForm,
			HttpServletRequest request, HttpServletResponse response,
			LoanadvanceBD loanadvanceBD, HttpSession session) {
		String FORWARD_PAGE = null;
		try {

			session.removeAttribute("LoanAdvance");
			session.removeAttribute("advanceMasterList");

			loanadvanceBD = new LoanadvanceBD();
			String pageAction = loanadvanceForm.getPageAction();
			ArrayList advanceMasterList = loanadvanceBD
					.retrieveAdvanceMasterDetails();

			session.setAttribute("advanceMasterList", advanceMasterList);

			FORWARD_PAGE = "AdvanceMasterDisplay";

		} catch (Exception e) {
			e.printStackTrace();
		}

		return FORWARD_PAGE;

	}

	/**
	 * @param mapping
	 * @param loanadvanceForm
	 * @param request
	 * @param response
	 * @param session
	 * @return String
	 */
	private String advanceMasterAdd(ActionMapping mapping,
			LoanadvanceForm loanadvanceForm, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		String FORWARD_PAGE = null;

		try {
			loanadvanceDTO = loanadvanceForm.getLoanadvanceDTO();
			session.setAttribute("LoanAdvance", loanadvanceDTO);
			FORWARD_PAGE = "AdvanceMasterAdd";

		} catch (Exception e) {
			e.printStackTrace();
		}
		return FORWARD_PAGE;

	}

	/**
	 * Method name :advanceMasterInsert
	 * 
	 * @param mapping
	 * @param loanadvanceForm
	 * @param request
	 * @param response
	 * @param session
	 * @return String
	 * 
	 */

	private String advanceMasterInsert(ActionMapping mapping,
			LoanadvanceForm loanadvanceForm, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		String FORWARD_PAGE = null;

		try {
			rule = new ValidationRule();
			ArrayList errorsList = rule.validateAdvanceMaster(loanadvanceForm);
			loanadvanceDTO = loanadvanceForm.getLoanadvanceDTO();
			if (rule.getError()) {
				request.setAttribute(LoanadvanceConstant.ERRORS, errorsList);
				request.setAttribute("LoanAdvanceDTO", loanadvanceDTO);

				FORWARD_PAGE = "AdvanceMasterAdd";
			} else {
				session.setAttribute("LoanAdvance", loanadvanceDTO);
				FORWARD_PAGE = "AdvanceMasterView";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return FORWARD_PAGE;

	}

	/**
	 * Method name :advanceMasterView
	 * 
	 * @param mapping
	 * @param loanadvanceForm
	 * @param request
	 * @param response
	 * @param session
	 * @return String
	 * 
	 */
	private String advanceMasterView(ActionMapping mapping,
			LoanadvanceForm loanadvanceForm, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		String FORWARD_PAGE = null;
		try {

			if (session.getAttribute("LoanAdvance") != null) {

				loanadvanceDTO = loanadvanceForm.getLoanadvanceDTO();
				loanadvanceForm.setLoanadvanceDTO(loanadvanceDTO);
				session.setAttribute("LoanAdvance", loanadvanceDTO);
			}

			FORWARD_PAGE = "AdvanceMasterView";
		} catch (Exception e) {
			e.printStackTrace();

		}
		return FORWARD_PAGE;
	}

	/**
	 * Method name :advanceMasterEdit
	 * 
	 * @param mapping
	 * @param loanadvanceForm
	 * @param request
	 * @param response
	 * @param session
	 * @return String
	 * 
	 */
	private String advanceMasterEdit(ActionMapping mapping,
			LoanadvanceForm loanadvanceForm, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		String FORWARD_PAGE = null;

		try {

			if (session.getAttribute("LoanAdvance") != null) {

				LoanadvanceDTO loanadvanceDTO = (LoanadvanceDTO) session
						.getAttribute("LoanAdvance");
				session.setAttribute("LoanAdvance", loanadvanceDTO);
				FORWARD_PAGE = "AdvanceMasterEdit";

			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return FORWARD_PAGE;
	}

	/**
	 * Method name :advanceMasterSubmit
	 * 
	 * @param mapping
	 * @param loanadvanceForm
	 * @param request
	 * @param response
	 * @param session
	 * @return String
	 * 
	 */
	private String advanceMasterSubmit(ActionMapping mapping,
			LoanadvanceForm loanadvanceForm, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		String FORWARD_PAGE = null;
		try {
			loanadvanceBD = new LoanadvanceBD();
			if (session.getAttribute("LoanAdvance") != null) {
				loanadvanceDTO = (LoanadvanceDTO) session
						.getAttribute("LoanAdvance");
				session.setAttribute("LoanAdvance", loanadvanceDTO);
			}

			boolean b = loanadvanceBD.insertAdvanceMasterDetails(
					loanadvanceDTO, session.getAttribute("UserId").toString());

			FORWARD_PAGE = "AdvavceMasterSubmit";

		} catch (Exception e) {
			e.printStackTrace();

		}

		return FORWARD_PAGE;

	}

	/**
	 * @param mapping
	 * @param loanadvanceForm
	 * @param request
	 * @param response
	 * @param session
	 * @return String
	 * @throws Exception
	 */
	private String executeApplyAdvance(ActionMapping mapping,
			LoanadvanceForm loanadvanceForm, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		String FORWARD_PAGE = null;
		try {
			session.removeAttribute("AmountList");

			loanadvanceBD = new LoanadvanceBD();
			ArrayList advancetypeList = loanadvanceBD.getAdvanceTypeList();
			loanadvanceDTO = new LoanadvanceDTO();
			loanadvanceDTO = loanadvanceForm.getLoanadvanceDTO();

			for (int i = 0; i < advancetypeList.size(); i++) {
				LoanadvanceDTO ldto = (LoanadvanceDTO) advancetypeList.get(i);

			}
			session.setAttribute("AdvanceApply", loanadvanceDTO);
			session.setAttribute("AdvanceList", advancetypeList);
			FORWARD_PAGE = "appliedAdvanceInsert";

		} catch (Exception e) {
			e.printStackTrace();

		}

		return FORWARD_PAGE;
	}

	/**
	 * @param mapping
	 * @param loanadvanceForm
	 * @param request
	 * @param response
	 * @param session
	 * @return String
	 */
	private String fetchAdvanceAmount(ActionMapping mapping,
			LoanadvanceForm loanadvanceForm, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		String FORWARD_PAGE = null;
		ArrayList AmountList = null;
		try {

			loanadvanceDTO = loanadvanceForm.getLoanadvanceDTO();
			loanadvanceBD = new LoanadvanceBD();
			AmountList = loanadvanceBD.fetchAdvanceAmount(loanadvanceDTO
					.getAdvancetypename());
			if (AmountList != null) {

				session.setAttribute("AdvanceApply", loanadvanceDTO);
				session.setAttribute("AmountList", AmountList);

			}
			FORWARD_PAGE = "appliedAdvanceInsert";

		} catch (Exception e) {
			e.printStackTrace();
		}
		return FORWARD_PAGE;
	}

	/**
	 * @param mapping
	 * @param loanadvanceForm
	 * @param request
	 * @param response
	 * @param session
	 * @return String
	 */
	private String checkAppliedAdvanceDetails(ActionMapping mapping,
			LoanadvanceForm loanadvanceForm, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		String FORWARD_PAGE = null;
		ArrayList AmountList = null;
		try {

			loanadvanceDTO = loanadvanceForm.getLoanadvanceDTO();
			loanadvanceBD = new LoanadvanceBD();
			rule = new ValidationRule();
			ArrayList errorsList = rule
					.validateApplyAdvanceComponent(loanadvanceForm);
			loanadvanceDTO = loanadvanceForm.getLoanadvanceDTO();
			if (rule.getError()) {
				request.setAttribute(LoanadvanceConstant.ERRORS, errorsList);
				request.setAttribute("AdvanceApply", loanadvanceDTO);
				FORWARD_PAGE = "appliedAdvanceInsert";
			} else {
				if (session.getAttribute("AdvanceApply") != null) {

					loanadvanceDTO = loanadvanceForm.getLoanadvanceDTO();

					session.setAttribute("AdvanceApply", loanadvanceDTO);
					FORWARD_PAGE = "appliedAdvanceView";
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return FORWARD_PAGE;
	}

	/**
	 * @param mapping
	 * @param loanadvanceForm
	 * @param request
	 * @param response
	 * @param session
	 * @return String
	 * @throws Exception
	 */
	private String viewApplyAdvance(ActionMapping mapping,
			LoanadvanceForm loanadvanceForm, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		String FORWARD_PAGE = null;
		try {

			if (session.getAttribute("AdvanceApply") != null) {

				loanadvanceDTO = (LoanadvanceDTO) session
						.getAttribute("AdvanceApply");

				session.setAttribute("AdvanceApply", loanadvanceDTO);
			}
			FORWARD_PAGE = "appliedAdvanceView";

		} catch (Exception e) {
			e.printStackTrace();

		}
		return FORWARD_PAGE;

	}

	/**
	 * @param mapping
	 * @param loanadvanceForm
	 * @param request
	 * @param response
	 * @param session
	 * @return String
	 * @throws Exception
	 */
	private String editApplyAdvance(ActionMapping mapping,
			LoanadvanceForm loanadvanceForm, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		String FORWARD_PAGE = null;
		// ArrayList advancetypeList =null;
		try {

			if (session.getAttribute("AdvanceApply") != null) {
				loanadvanceDTO = (LoanadvanceDTO) session
						.getAttribute("AdvanceApply");

				session.setAttribute("AdvanceApply", loanadvanceDTO);

			}

			FORWARD_PAGE = "appliedAdvanceInsert";

		} catch (Exception e) {
			e.printStackTrace();

		}

		return FORWARD_PAGE;
	}

	/**
	 * @param mapping
	 * @param loanadvanceForm
	 * @param request
	 * @param response
	 * @param session
	 * @return String
	 * @throws Exception
	 */
	private String submitApplyAdvance(ActionMapping mapping,
			LoanadvanceForm loanadvanceForm, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		String FORWARD_PAGE = null;

		try {
			loanadvanceBD = new LoanadvanceBD();
			String advanceid = loanadvanceDTO.getAdvancetypeid();

			if (session.getAttribute("AdvanceApply") != null) {
				loanadvanceDTO = (LoanadvanceDTO) session
						.getAttribute("AdvanceApply");

				session.setAttribute("AdvanceApply", loanadvanceDTO);
			}
			String strTXNId = loanadvanceBD.insertAppliedAdvance(
					loanadvanceDTO, session.getAttribute("UserId").toString());
			if (!strTXNId.equals("")) {

				request.setAttribute("txnId", strTXNId);
				FORWARD_PAGE = "appliedAdvanceSubmit";
			} else {

				FORWARD_PAGE = "appliedAdvanceInsert";
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return FORWARD_PAGE;
	}

	/**
	 * @param mapping
	 * @param loanadvanceForm
	 * @param request
	 * @param response
	 * @param session
	 * @return String
	 * @throws Exception
	 */
	private String searchEmpApproveAdvance(ActionMapping mapping,
			LoanadvanceForm loanadvanceForm, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		String FORWARD_PAGE = null;
		ArrayList getPendingAdvanceList = null;
		try {

			loanadvanceBD = new LoanadvanceBD();
			rule = new ValidationRule();
			ArrayList errorsList = rule.chkempId(loanadvanceForm);
			loanadvanceDTO = loanadvanceForm.getLoanadvanceDTO();
			if (rule.getError())
					 {
				request.setAttribute(LoanadvanceConstant.ERRORS, errorsList);
				if (loanadvanceDTO.getEmpid() != null) {
					getPendingAdvanceList = loanadvanceBD
							.getPendingAdvanceList(loanadvanceDTO.getEmpid());

				}
				if (getPendingAdvanceList != null)
					request.setAttribute("AdvancePendingList",
							getPendingAdvanceList);
				FORWARD_PAGE = "advanceApprovalSearch";
			} else {
				if (loanadvanceDTO.getEmpid() != null) {
					getPendingAdvanceList = loanadvanceBD
							.getPendingAdvanceList(loanadvanceDTO.getEmpid());

				}
				if (getPendingAdvanceList != null)
					request.setAttribute("AdvancePendingList",
							getPendingAdvanceList);
				FORWARD_PAGE = "advanceApprovalSearch";

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return FORWARD_PAGE;
	}

	/**
	 * @param mapping
	 * @param loanadvanceForm
	 * @param request
	 * @param response
	 * @param session
	 * @return String
	 * @throws Exception
	 */
	private String advanceApprovalResult(ActionMapping mapping,
			LoanadvanceForm loanadvanceForm, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		String FORWARD_PAGE = null;
		ArrayList getAdvanceTxnDetailsList = null;
		ArrayList advanceList = null;

		try {

			loanadvanceDTO = loanadvanceForm.getLoanadvanceDTO();
			loanadvanceBD = new LoanadvanceBD();

			if (request.getParameter("adid") != null) {
				String advancetypeid = request.getParameter("adid");
				getAdvanceTxnDetailsList = loanadvanceBD
						.getAdvanceTxnDetails(advancetypeid);
				advanceList = loanadvanceBD.getAdvanceTypeList(advancetypeid);
			}
			if (advanceList != null)
				session.setAttribute("advanceList", advanceList);

			if (getAdvanceTxnDetailsList != null)
				session.setAttribute("AdvanceListForApprove",
						getAdvanceTxnDetailsList);
			FORWARD_PAGE = "advanceApprovalSearchView";

		} catch (Exception e) {
			e.printStackTrace();
		}
		return FORWARD_PAGE;
	}

	/**
	 * @param mapping
	 * @param loanadvanceForm
	 * @param request
	 * @param response
	 * @param session
	 * @return String
	 * @throws Exception
	 */
	private String submitAdvanceApproval(ActionMapping mapping,
			LoanadvanceForm loanadvanceForm, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		String FORWARD_PAGE = null;
		try {

			ArrayList lsAdvanceTxnList = (ArrayList) session
					.getAttribute("AdvanceListForApprove");
			loanadvanceBD = new LoanadvanceBD();
			loanadvanceDTO = loanadvanceForm.getLoanadvanceDTO();
			boolean b = loanadvanceBD.getAdvanceApproved(lsAdvanceTxnList,
					loanadvanceDTO, session.getAttribute("UserId").toString());

			request.setAttribute("SUCCESS_MSG", "success");
			FORWARD_PAGE = "advanceApprovalSubmitPage";

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return FORWARD_PAGE;

	}

	/**
	 * @param mapping
	 * @param loanadvanceForm
	 * @param request
	 * @param response
	 * @param session
	 * @return String
	 */
	private String retrieveAdvanceMasterComponents(ActionMapping mapping,
			LoanadvanceForm loanadvanceForm, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		String FORWARD_PAGE = null;
		ArrayList AdvanceComponentsDetailsList = null;

		try {

			loanadvanceBD = new LoanadvanceBD();

			if (request.getParameter("componentid") != null) {
				String componentId = request.getParameter("componentid");
				AdvanceComponentsDetailsList = loanadvanceBD
						.getAdvanceMasterComponentsList(componentId);

			}
			if (AdvanceComponentsDetailsList != null)
				session.setAttribute("AdvanceComponentsDetailsList",
						AdvanceComponentsDetailsList);
			FORWARD_PAGE = "advanceComponentDetailsView";

		} catch (Exception e) {
			e.printStackTrace();

		}
		return FORWARD_PAGE;
	}

	/**
	 * @param mapping
	 * @param loanadvanceForm
	 * @param request
	 * @param response
	 * @param session
	 * @return String
	 * @throws Exception
	 */
	private String editAdvanceMasterComponents(ActionMapping mapping,
			LoanadvanceForm loanadvanceForm, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		String FORWARD_PAGE = null;
		ArrayList advanceComponentList = null;
		try {

			if (session.getAttribute("AdvanceComponentsDetailsList") != null) {
				advanceComponentList = (ArrayList) session
						.getAttribute("AdvanceComponentsDetailsList");

			}
			if (advanceComponentList != null) {

				session.setAttribute("ComponentList", advanceComponentList);

				FORWARD_PAGE = "advanceMasterComponentsEdit";

			}

		} catch (Exception e) {
			e.printStackTrace();

		}

		return FORWARD_PAGE;
	}

	/**
	 * @param mapping
	 * @param loanadvanceForm
	 * @param request
	 * @param response
	 * @param session
	 * @return String
	 */
	private String afterEditAddComponent(ActionMapping mapping,
			LoanadvanceForm loanadvanceForm, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		String FORWARD_PAGE = null;
		try {
			rule = new ValidationRule();
			ArrayList errorsList = rule
					.validateAdvanceComponent(loanadvanceForm);
			if (rule.getError())
					{
				request.setAttribute(LoanadvanceConstant.ERRORS, errorsList);
				FORWARD_PAGE = "advanceMasterComponentsEdit";
			} else {
				loanadvanceDTO = loanadvanceForm.getLoanadvanceDTO();
				session.setAttribute("list", loanadvanceDTO);
				FORWARD_PAGE = "advanceComponentDetailsSubmit";

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return FORWARD_PAGE;
	}

	/**
	 * @param mapping
	 * @param loanadvanceForm
	 * @param request
	 * @param response
	 * @param session
	 * @return String
	 */
	private String submitAdvanceMasterComponents(ActionMapping mapping,
			LoanadvanceForm loanadvanceForm, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		String FORWARD_PAGE = null;
		ArrayList list = null;
		boolean update = false;
		try {

			if (session.getAttribute("AdvanceComponentsDetailsList") != null) {

				loanadvanceBD = new LoanadvanceBD();
				list = (ArrayList) session
						.getAttribute("AdvanceComponentsDetailsList");

				loanadvanceDTO = loanadvanceForm.getLoanadvanceDTO();
				update = loanadvanceBD.updateAdvanceMasterDetails(
						loanadvanceDTO, list, session.getAttribute("UserId")
								.toString());

			}
			if (update) {
				FORWARD_PAGE = "advanceComponentDetailsSubmit";
			} else {
				FORWARD_PAGE = "AdvanceMasterDisplay";
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return FORWARD_PAGE;
	}

	/**
	 * @param mapping
	 * @param loanadvanceForm
	 * @param request
	 * @param response
	 * @param session
	 * @return String
	 * @throws Exception
	 */
	private String searchAdvanceStatus(ActionMapping mapping,
			LoanadvanceForm loanadvanceForm, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		String FORWARD_PAGE = null;
		ArrayList amountList = null;
		ArrayList advanceAmountList = null;
		ArrayList advanceType = null;
		session.removeAttribute("advanceAmountList");
		session.removeAttribute("advanceType");
		session.removeAttribute("amountList");
		try {

			loanadvanceDTO = loanadvanceForm.getLoanadvanceDTO();
			loanadvanceBD = new LoanadvanceBD();
			rule = new ValidationRule();
			ArrayList errorsList = rule.referenceId(loanadvanceForm);

			if (rule.getError())
					 {
				request.setAttribute(LoanadvanceConstant.ERRORS, errorsList);
				if (loanadvanceDTO.getAdvancetxnid() != null) {
					advanceType = loanadvanceBD
							.getAdvanceTypeList(loanadvanceDTO
									.getAdvancetxnid());
					//this code is changed by praveenkumar k
					if(advanceType.size()==0){
						errorsList.add("<font color='red'>. Please Enter Valid Reference Number");
					}
					else {
						advanceAmountList = loanadvanceBD
						.getAdvanceDetails(loanadvanceDTO.getAdvancetxnid());
						amountList = loanadvanceBD.callingProcedure(loanadvanceDTO
						.getAdvancetxnid());
					}

				}
				if (advanceAmountList != null)
					session
							.setAttribute("advanceAmountList",
									advanceAmountList);
				if (advanceType != null)
					session.setAttribute("advanceType", advanceType);
				if (amountList != null)
					session.setAttribute("amountList", amountList);

				FORWARD_PAGE = "advanceStatusPage";
			} else {
				if (loanadvanceDTO.getAdvancetxnid() != null) {
					advanceType = loanadvanceBD
							.getAdvanceTypeList(loanadvanceDTO
									.getAdvancetxnid());
					advanceAmountList = loanadvanceBD
							.getAdvanceDetails(loanadvanceDTO.getAdvancetxnid());
					amountList = loanadvanceBD.callingProcedure(loanadvanceDTO
							.getAdvancetxnid());

				}
				if (advanceAmountList != null)
					session
							.setAttribute("advanceAmountList",
									advanceAmountList);
				if (advanceType != null)
					session.setAttribute("advanceType", advanceType);
				if (amountList != null)
					session.setAttribute("amountList", amountList);
				FORWARD_PAGE = "advanceStatusPage";

			}
		} catch (Exception e) {
			e.printStackTrace();

		}

		return FORWARD_PAGE;
	}

}
