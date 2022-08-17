/**
 * Copyright (c) 2009-10 WIPRO INFOTECH. All Rights Reserved.
 *
 *  This software is the confidential and proprietary information of WIPRO
 *  ("Confidential Information"). You shall not
 *  disclose such Confidential Information and shall use it only in
 *  accordance with the terms of the license agreement you entered into
 *  with WIPRO.
 *
 * WIPRO MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE
 * SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, OR NON-INFRINGEMENT. WIPRO SHALL NOT BE
 * LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF
 * USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES.
 *
 * Customer specific copyright notice - NA
 */
package com.wipro.igrs.empmgmt.action;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.common.EmpmgmtConstant;
import com.wipro.igrs.empmgmt.bd.CommonBD;
import com.wipro.igrs.empmgmt.bd.EmpmgmtViewBD;
import com.wipro.igrs.empmgmt.dto.BankDTO;
import com.wipro.igrs.empmgmt.dto.BankMstDTO;
import com.wipro.igrs.empmgmt.dto.FundDTO;
import com.wipro.igrs.empmgmt.dto.NomineeDTO;
import com.wipro.igrs.empmgmt.dto.PersonalDetailsDTO;
import com.wipro.igrs.empmgmt.form.BankForm;
import com.wipro.igrs.empmgmt.form.PersonalForm;
import com.wipro.igrs.empmgmt.rule.EmpMgmtRule;

/**
* 
* BankAction.java <br>
* BankAction <br>
* 
* @version 1.0
* 
* @author <b>WIPRO JAVA TEAM</b> <br>
*         Created on 12-Apr-2008 <br>
*         Last Modified on 28-Mar-2013
*/
@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
public class BankAction extends BaseAction {
	private static Logger logger = Logger.getLogger(BankAction.class);
	
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
			HttpServletRequest request, HttpServletResponse response,HttpSession session)
			throws Exception {
		BankForm bankForm = (BankForm) form;
		CommonBD commonBD = new CommonBD();
		EmpMgmtRule empMgmtRule = new EmpMgmtRule();
		String parameter = request.getParameter("name");
		BankDTO bankDTO = new BankDTO();
		ArrayList bankFundList = new ArrayList();
		ArrayList nomineeList = new ArrayList();
		ArrayList<BankMstDTO> bankMasterList = new ArrayList<BankMstDTO>();
		ArrayList errorfundlist = null;
		ArrayList errornomineelist = null;
		ArrayList fundList = null;
		ArrayList errorList = null;
		String FORWARD_PAGE = "New";
		String strEmployeeId = (String) session.getAttribute("employeeId");
		String update = request.getParameter("update");

		
		try {
			bankForm.setSelectedNominee(new NomineeDTO());
			Object tmp;
//			ArrayList fundaccno = null;
			tmp = session.getAttribute("fundaccno");
			if(tmp != null) {
//				fundaccno=(ArrayList) tmp;
			}else {
//				fundaccno=commonBD.getFundAccNo();
//				session.setAttribute("fundaccno", fundaccno);
			}
			tmp = session.getAttribute("bankMasterList");
			if(tmp != null) {
				bankMasterList = (ArrayList<BankMstDTO>) tmp;
				bankForm.setBankMasterList(bankMasterList);
			}else {
				bankMasterList = commonBD.getBankMasterList();
				bankForm.setBankMasterList(bankMasterList);
				session.setAttribute("bankMasterList", bankMasterList);
			}
			
			tmp = session.getAttribute("FundList");
			if(tmp != null) {
				fundList=(ArrayList) tmp;
				if(fundList.size()==0 ||fundList==null){
					fundList = commonBD.getFundType();
				}
			}else {
				fundList = commonBD.getFundType();
				session.setAttribute("FundList", fundList);
			}

			if (bankForm.getActionType() == null
					|| (bankForm.getActionType() != null && bankForm
							.getActionType().trim().length() == 0)) {
				bankForm.setActionType("New");
			}

			if (bankForm.getActionType() != null
					&& bankForm.getActionType().equals("New")) {
//				fundlist = commonBD.getFundType();
				
				errorList = new ArrayList();
				errorList = empMgmtRule.validateList(fundList,
						EmpmgmtConstant.FUNDTYPE);
				session.removeAttribute("bankupdate");
				if (empMgmtRule.isError()) {
					request.setAttribute(EmpmgmtConstant.EMP_MGMT_ERROR_FLAG,
							Boolean.TRUE);
					request.setAttribute(EmpmgmtConstant.EMP_MGMT_ERROR_LIST,
							errorList);
					FORWARD_PAGE = EmpmgmtConstant.BANK_DETAILS_ACTION;
				} else {
					if (update != null) {
						session.setAttribute("bankupdate", update);
					}
//					if(session.getAttribute("bankFundList") != null) {
//						bankFundList = (ArrayList) session.getAttribute("bankFundList");
//						bankForm.setBankFundList(bankFundList);
//					}
//					session.setAttribute("fundaccno", fundaccno);
					session.setAttribute("nomineeList", nomineeList);
//					session.setAttribute("bankFundList", bankFundList);
					session.setAttribute("bankForm", bankForm);
					session.setAttribute("FundList", fundList);
					request.setAttribute(EmpmgmtConstant.EMP_MGMT_ERROR_FLAG,
							Boolean.FALSE);
					request.setAttribute(EmpmgmtConstant.EMP_MGMT_ERROR_LIST,
							null);
					FORWARD_PAGE = EmpmgmtConstant.BANK_DETAILS_ACTION;
				}
			
			} else if ("addBankFundRow".equals(bankForm.getActionType())) {
				FundDTO newFundDTO = new FundDTO();
				bankFundList = bankForm.getBankFundList();
				
				if (bankFundList != null) {
					newFundDTO.setEmployee_id(strEmployeeId);
					newFundDTO.setAccountNo(bankForm.getSelectedFund().getAccountNo());
					newFundDTO.setAccountLocation(bankForm.getSelectedFund().getAccountLocation());
					newFundDTO.setType(bankForm.getSelectedFund().getType());
//					newFundDTO.setFromDB(false);
					for (Object typeItem : fundList) {
						BankDTO item = (BankDTO) typeItem;
						if(item.getFundTypeId().equalsIgnoreCase(newFundDTO.getType())) {
							newFundDTO.setFundName(item.getFundTypeName());
							break;
						}
					}
//					fundaccno.add(newFundDTO);
					bankFundList.add(newFundDTO);
				}
				bankForm.setBankFundList(bankFundList);
				bankForm.setiFundIndex(-1);
				bankForm.setSelectedFund(new FundDTO());
//				session.setAttribute("fundaccno", fundaccno);
				session.setAttribute("FundList", fundList);
				session.setAttribute("bankForm", bankForm);
				request.setAttribute(EmpmgmtConstant.EMP_MGMT_ERROR_FLAG,
						Boolean.FALSE);
				request.setAttribute(EmpmgmtConstant.EMP_MGMT_ERROR_LIST,
						null);
				FORWARD_PAGE = EmpmgmtConstant.BANK_DETAILS_ACTION;
			} else if ("delBankFundRow".equals(bankForm.getActionType())) {
				bankFundList = bankForm.getBankFundList();
				int delIndex = Integer.parseInt(request.getParameter("delIndex"));
				if(bankFundList != null) {
					try {
						FundDTO removedFund = (FundDTO) bankFundList.remove(delIndex);
						int markIndex = -1;
//						for (int iLoop = 0; iLoop < fundaccno.size(); iLoop++) {
//							FundDTO tmpFundDTO = (FundDTO) fundaccno.get(iLoop);
//							if(tmpFundDTO.isFromDB() == false) {
//								if (removedFund.getAccountNo() != null &&
//										removedFund.getAccountNo().equals(tmpFundDTO.getAccountNo())) {
//									markIndex = iLoop;
//									break;
//								}
//							}
//						}
//						if(markIndex != -1) {
//							fundaccno.remove(markIndex);
//						}
					} catch (Exception e) {
					}
					bankForm.setiFundIndex(-1);
					bankForm.setSelectedFund(new FundDTO());
					bankForm.setBankFundList(bankFundList);
				}
//				session.setAttribute("fundaccno", fundaccno);
//				session.setAttribute("bankFundList", bankFundList);
				session.setAttribute("FundList", fundList);
				session.setAttribute("bankForm", bankForm);
				request.setAttribute(EmpmgmtConstant.EMP_MGMT_ERROR_FLAG,
						Boolean.FALSE);
				request.setAttribute(EmpmgmtConstant.EMP_MGMT_ERROR_LIST,
						null);
				FORWARD_PAGE = EmpmgmtConstant.BANK_DETAILS_ACTION;
			} else if ("fundSelected".equals(bankForm.getActionType())) {
				bankFundList = bankForm.getBankFundList();
				bankForm.setSelectedFund(new FundDTO());
				int selectedIndex = Integer.parseInt(request.getParameter("fundIndex"));
				bankForm.setiFundIndex(selectedIndex);
				if(bankFundList != null) {
					try {
						FundDTO selectedFund = (FundDTO) bankFundList.get(selectedIndex);
						bankForm.setSelectedFund(selectedFund);
						int markIndex = -1;
//						for (int iLoop = 0; iLoop < fundaccno.size(); iLoop++) {
//							FundDTO tmpFundDTO = (FundDTO) fundaccno.get(iLoop);
//							if(tmpFundDTO.isFromDB() == false) {
//								if (selectedFund.getAccountNo() != null &&
//										selectedFund.getAccountNo().equals(tmpFundDTO.getAccountNo())) {
//									markIndex = iLoop;
//									break;
//								}
//							}
//						}
//						bankForm.setSelectedFund(selectedFund);
//						if(markIndex != -1) {
//							fundaccno.remove(markIndex);
//						}
					} catch (Exception e) {
					}
					
					bankForm.setBankFundList(bankFundList);
				}
//				session.setAttribute("fundaccno", fundaccno);
				session.setAttribute("FundList", fundList);
				session.setAttribute("bankForm", bankForm);
				request.setAttribute(EmpmgmtConstant.EMP_MGMT_ERROR_FLAG,
						Boolean.FALSE);
				request.setAttribute(EmpmgmtConstant.EMP_MGMT_ERROR_LIST,
						null);
				FORWARD_PAGE = EmpmgmtConstant.BANK_DETAILS_ACTION;
			} else if ("editBankFundRow".equals(bankForm.getActionType())) {
				bankFundList = bankForm.getBankFundList();
				bankForm.setSelectedFund(new FundDTO());
				int selectedIndex = Integer.parseInt(request.getParameter("iFundIndex"));
				bankForm.setiFundIndex(-1);
				if(bankFundList != null) {
					try {
						FundDTO selectedFund = (FundDTO) bankFundList.get(selectedIndex);
						//selectedFund.setType(selectedFund.getType());
						String fundName = commonBD.getFundTypeName(selectedFund.getType());
						selectedFund.setFundName(fundName);
						//selectedFund.setAccountNo(selectedFund.getAccountNo());
						//selectedFund.setAccountLocation(selectedFund.getAccountLocation());
					} catch (Exception e) {
					}
					bankForm.setBankFundList(bankFundList);
				}
//				session.setAttribute("fundaccno", fundaccno);
				session.setAttribute("FundList", fundList);
				session.setAttribute("bankForm", bankForm);
				request.setAttribute(EmpmgmtConstant.EMP_MGMT_ERROR_FLAG,
						Boolean.FALSE);
				request.setAttribute(EmpmgmtConstant.EMP_MGMT_ERROR_LIST,
						null);
				FORWARD_PAGE = EmpmgmtConstant.BANK_DETAILS_ACTION;
			} else if ("addNomineeRow".equals(bankForm.getActionType())) {
				
				bankFundList = bankForm.getBankFundList();
				if(bankFundList != null) {
					try {
						FundDTO tmpFund = null;
						/*
						 url = "bank.do?name=displaybankpage&formName=addNomineeRow"
						+ "&nomineeName=" + nomineeName 
						+ "&nomineeAddress=" + nomineeAddress 
						+ "&relationWithNominee=" + relationWithNominee 
						+ "&nomineeAge=" + nomineeAge;
						 */
						for (int iLoop = 0; iLoop < bankFundList.size(); iLoop++) {
							tmpFund = (FundDTO) bankFundList.get(iLoop);
							if(bankForm.getAccountNo().equalsIgnoreCase(tmpFund.getAccountNo())) {
								break;
							}
						}
						if(tmpFund != null) {
							NomineeDTO tmpNominee = new NomineeDTO();
							tmpNominee.setStrAccountNumber(bankForm.getAccountNo());
							tmpNominee.setFundTypeId(bankForm.getType());
							tmpNominee.setNomineeName(bankForm.getNomineeName());
							tmpNominee.setRelationWithNominee(bankForm.getRelationWithNominee());
							tmpNominee.setNomineeAge(bankForm.getNomineeAge());
							tmpNominee.setNomineeAddress(bankForm.getNomineeAddress());
							tmpNominee.setNomineePercentage(bankForm.getNomineePercentage());
							tmpNominee.setEmployee_id(strEmployeeId);
							nomineeList = tmpFund.getNomineeList();
							if(nomineeList == null) {
								nomineeList = new ArrayList();
							}
							nomineeList.add(tmpNominee);
							tmpFund.setNomineeList(nomineeList);
							bankForm.setSelectedNominees(tmpFund.getNomineeList());
							bankForm.setNomineeName("");
							bankForm.setRelationWithNominee("");
							bankForm.setNomineeAge("");
							bankForm.setNomineeAddress("");
							bankForm.setNomineePercentage("");
						}
						bankForm.setBankFundList(bankFundList);
						bankForm.setiNomineeIndex(-1);
					} catch (Exception e) {
					}
					
				}
//				session.setAttribute("fundaccno", fundaccno);
				session.setAttribute("FundList", fundList);
				session.setAttribute("bankForm", bankForm);
				request.setAttribute(EmpmgmtConstant.EMP_MGMT_ERROR_FLAG,
						Boolean.FALSE);
				request.setAttribute(EmpmgmtConstant.EMP_MGMT_ERROR_LIST,
						null);
				FORWARD_PAGE = EmpmgmtConstant.NOMINEE_PAGE_ACTION;
			} else if ("fnDeleteNominee".equals(bankForm.getActionType())) {
				String fundAccountNo = bankForm.getAccountNo();
				String paraDelNomineeIndex = request.getParameter("nomineeIndex");
				bankForm.setSelectedNominees(new ArrayList());
				bankFundList = bankForm.getBankFundList();
				FundDTO tmpFund = null;
				int markIndex = -1;
				int delNomineeIndex = Integer.parseInt(paraDelNomineeIndex);
				for (int iLoop = 0; iLoop < bankFundList.size(); iLoop++) {
					tmpFund = (FundDTO) bankFundList.get(iLoop);
					if(fundAccountNo.equalsIgnoreCase(tmpFund.getAccountNo())) {
						markIndex = iLoop;
						break;
					}
				}
				if(tmpFund != null) {
					if(markIndex >= 0) {
						try {
							tmpFund.getNomineeList().remove(delNomineeIndex);
						} catch (Exception e) {
						}
						bankForm.setiNomineeIndex(-1);
						bankForm.setSelectedNominees(tmpFund.getNomineeList());
					}
				}
				bankForm.setNomineeName("");
				bankForm.setRelationWithNominee("");
				bankForm.setNomineeAge("");
				bankForm.setNomineeAddress("");
				bankForm.setNomineePercentage("");
				bankForm.setiNomineeIndex(-1);
				session.setAttribute("bankForm", bankForm);
				request.setAttribute(EmpmgmtConstant.EMP_MGMT_ERROR_FLAG,
						Boolean.FALSE);
				request.setAttribute(EmpmgmtConstant.EMP_MGMT_ERROR_LIST,
						null);
				FORWARD_PAGE = EmpmgmtConstant.NOMINEE_PAGE_ACTION;
			} else if ("selectFundChange".equals(bankForm.getActionType())) {
				String fundAccountNo = bankForm.getAccountNo();
				bankForm.setSelectedNominees(new ArrayList());
				bankFundList = bankForm.getBankFundList();
				FundDTO tmpFund = null;
				for (int iLoop = 0; iLoop < bankFundList.size(); iLoop++) {
					tmpFund = (FundDTO) bankFundList.get(iLoop);
					if(fundAccountNo.equalsIgnoreCase(tmpFund.getAccountNo())) {
						break;
					}
				}
				if(tmpFund != null) {
					ArrayList nominees = tmpFund.getNomineeList();
					if(nominees != null) {
						for (Object item : nominees) {
							NomineeDTO nominee = (NomineeDTO) item;
						}
					}
					bankForm.setSelectedNominees(tmpFund.getNomineeList());
				}
				bankForm.setNomineeAddress("");
				bankForm.setNomineeAge("");
				bankForm.setRelationWithNominee("");
				bankForm.setNomineeName("");
				bankForm.setNomineePercentage("");
				session.setAttribute("bankForm", bankForm);
				request.setAttribute(EmpmgmtConstant.EMP_MGMT_ERROR_FLAG,
						Boolean.FALSE);
				request.setAttribute(EmpmgmtConstant.EMP_MGMT_ERROR_LIST,
						null);
				FORWARD_PAGE = EmpmgmtConstant.NOMINEE_PAGE_ACTION;
			} else if ("nomineeSelected".equals(bankForm.getActionType())) {
				String fundAccountNo = bankForm.getAccountNo();
				int nomineeIndex = Integer.parseInt(request.getParameter("nomineeIndex"));
				bankFundList = bankForm.getBankFundList();
				FundDTO tmpFund = null;
				NomineeDTO tmpNom = null;
				for (int iLoop = 0; iLoop < bankFundList.size(); iLoop++) {
					tmpFund = (FundDTO) bankFundList.get(iLoop);
					if(fundAccountNo.equalsIgnoreCase(tmpFund.getAccountNo())) {
						break;
					}				
				}
				try {
					tmpNom = (NomineeDTO) tmpFund.getNomineeList().get(
							nomineeIndex);
					tmpNom.setSelected(""+nomineeIndex);
					bankForm.setiNomineeIndex(nomineeIndex);
					bankForm.setSelectedNominee(tmpNom);
					bankForm.setNomineeName(tmpNom.getNomineeName());
					bankForm.setRelationWithNominee(tmpNom.getRelationWithNominee());
					bankForm.setNomineeAge(tmpNom.getNomineeAge());
					bankForm.setNomineeAddress(tmpNom.getNomineeAddress());
					bankForm.setNomineePercentage(tmpNom.getNomineePercentage());
				} catch (Exception e) {
				}
				session.setAttribute("bankForm", bankForm);
				request.setAttribute(EmpmgmtConstant.EMP_MGMT_ERROR_FLAG,
						Boolean.FALSE);
				request.setAttribute(EmpmgmtConstant.EMP_MGMT_ERROR_LIST,
						null);
				FORWARD_PAGE = EmpmgmtConstant.NOMINEE_PAGE_ACTION;
			} else if ("fnupdateNominee".equals(bankForm.getActionType())) {
				String fundAccountNo = bankForm.getAccountNo();
				int nomineeIndex = Integer.parseInt(request.getParameter("nomineeIndex"));
				bankFundList = bankForm.getBankFundList();
				FundDTO tmpFund = null;
				NomineeDTO tmpNom = null;
				for (int iLoop = 0; iLoop < bankFundList.size(); iLoop++) {
					tmpFund = (FundDTO) bankFundList.get(iLoop);
					if(fundAccountNo.equalsIgnoreCase(tmpFund.getAccountNo())) {
						break;
					}				
				}
				try {					
					tmpNom = (NomineeDTO) tmpFund.getNomineeList().get(
							nomineeIndex);
					tmpNom.setNomineeName(bankForm.getNomineeName());
					tmpNom.setRelationWithNominee(bankForm.getRelationWithNominee());
					tmpNom.setNomineeAge(bankForm.getNomineeAge());
					tmpNom.setNomineeAddress(bankForm.getNomineeAddress());
					tmpNom.setNomineePercentage(bankForm.getNomineePercentage());
					tmpFund.getNomineeList().set(nomineeIndex, tmpNom);
					bankForm.setNomineeName("");
					bankForm.setRelationWithNominee("");
					bankForm.setNomineeAge("");
					bankForm.setNomineeAddress("");
					bankForm.setiNomineeIndex(nomineeIndex);
					bankForm.setSelectedNominee(new NomineeDTO());
				} catch (Exception e) {
				}
				session.setAttribute("bankForm", bankForm);
				request.setAttribute(EmpmgmtConstant.EMP_MGMT_ERROR_FLAG,
						Boolean.FALSE);
				request.setAttribute(EmpmgmtConstant.EMP_MGMT_ERROR_LIST,
						null);
				FORWARD_PAGE = EmpmgmtConstant.NOMINEE_PAGE_ACTION;
			} else if ("fnNomineeSave".equals(bankForm.getActionType())) {
				commonBD = new CommonBD();
				String fundAccountNo = bankForm.getAccountNo();
				bankFundList = bankForm.getBankFundList();
				nomineeList = new ArrayList();
				ArrayList tmpNomList;
				for (int iLoop = 0; iLoop < bankFundList.size(); iLoop++) {
					FundDTO tmpFundDTO = (FundDTO) bankFundList.get(iLoop);
					tmpNomList = tmpFundDTO.getNomineeList();
					for(int jLoop =0 ; jLoop < tmpNomList.size(); jLoop++) {
						NomineeDTO nomDTO = (NomineeDTO) tmpNomList.get(jLoop);
						nomDTO.setStrAccountNumber(tmpFundDTO.getAccountNo());
						tmpNomList.set(jLoop, nomDTO);
					}
					nomineeList.addAll(tmpNomList);
				}
				empMgmtRule = new EmpMgmtRule();
				// String[] fundTypeData =
				// commonBD.getFundTypeData(fundAccountNo);
				// nomDTO.setFundTypeId(fundTypeData[0]);
				// nomDTO.setFundName(fundTypeData[1]);
				for (Object item : nomineeList) {
					NomineeDTO nomDTO = (NomineeDTO) item;
					 String[] fundTypeData =
					 commonBD.getFundTypeData(nomDTO.getStrAccountNumber());
					 nomDTO.setFundTypeId(fundTypeData[0]);
					 nomDTO.setFundName(fundTypeData[1]);
				}
				
				errornomineelist = empMgmtRule.validateNomineeList(nomineeList);
				
				errorList = new ArrayList();
				if (empMgmtRule.isError()) {
					try {
						errorList.addAll(errornomineelist);
					} catch (Exception e) {
					}
					request.setAttribute(EmpmgmtConstant.EMP_MGMT_ERROR_FLAG,
							Boolean.TRUE);
					request.setAttribute(EmpmgmtConstant.EMP_MGMT_ERROR_LIST,
							errorList);
					FORWARD_PAGE = EmpmgmtConstant.NOMINEE_PAGE_ACTION;
				}else {
					boolean flag = commonBD.insertNomineeDetails(nomineeList,
							(String) session.getAttribute("UserId"),
							strEmployeeId, fundAccountNo);
					if (flag) {
						bankForm.setActionType("");
						request.setAttribute("success",
								"<font color=green>Bank/Account and Nominee Information submitted successfully!</font>");
						FORWARD_PAGE = "viewbankpage";
					} else {
						request.setAttribute("failure",
								"<font color=red>Bank/Account Information not submitted successfully!</font>");
						FORWARD_PAGE = "NOMINEE_PAGE_ACTION";
					}
					request.setAttribute(EmpmgmtConstant.EMP_MGMT_ERROR_FLAG,
						Boolean.FALSE);
					request.setAttribute(EmpmgmtConstant.EMP_MGMT_ERROR_LIST,
							null);
					
				}
				bankForm.setActionType("");
				session.setAttribute("bankForm", bankForm);
				
			} else if (bankForm.getActionType() != null
					&& bankForm.getActionType().equals("savebank")) {
				empMgmtRule = new EmpMgmtRule();
				commonBD = new CommonBD();
				bankDTO = bankForm.getBankDTO();
				bankFundList = bankForm.getBankFundList();
//				try {
//					FundDTO prevDTO = (FundDTO) bankFundList.get(bankFundList.size()-1);
//					prevDTO.setType(bankForm.getType());
//					prevDTO.setAccountNo(bankForm.getAccountNo());
//					prevDTO.setAccountLocation(bankForm.getAccountLocation());
////					fundaccno.add(prevDTO);
//					bankForm.setType("");
//					bankForm.setAccountNo("");
//					bankForm.setAccountLocation("");
//				} catch (Exception e) {
//				}
				bankDTO.setNameAsInBank(bankForm.getNameAsInBank());
				bankDTO.setPanNo(bankForm.getPanNo());
				bankDTO.setBankAccountNo(bankForm.getBankAccountNo());
				bankDTO.setBankName(bankForm.getBankName());
				bankDTO.setBankBranch(bankForm.getBankBranch());
				bankDTO.setBankAddress(bankForm.getBankAddress());
				bankDTO.setBankIFSC(bankForm.getBankIFSC());
				bankDTO.setFundList(bankFundList);
				errorList = empMgmtRule.validateBank(bankDTO);
				errorfundlist = empMgmtRule.validateFundList(bankFundList, strEmployeeId);
//				session.setAttribute("fundaccno", fundaccno);
				session.setAttribute("FundList", fundList);
				session.setAttribute("bankForm", bankForm);

				if (empMgmtRule.isError()) {
					try {
						errorList.addAll(errorfundlist);
					} catch (Exception e) {
					}
					request.setAttribute(EmpmgmtConstant.EMP_MGMT_ERROR_FLAG,
							Boolean.TRUE);
					request.setAttribute(EmpmgmtConstant.EMP_MGMT_ERROR_LIST,
							errorList);
					FORWARD_PAGE = EmpmgmtConstant.BANK_DETAILS_ACTION;
				} else {

					boolean flag = commonBD.insertBankDetails(bankDTO,
							null, null, (String) session
									.getAttribute("UserId"), strEmployeeId);
					if (flag) {
//						bankForm.setAccountNo("");
						bankForm.setBankDetailsPresent(true);
						session.setAttribute("bankForm", bankForm);
						request.setAttribute("success",
								"<font color=green>Bank/Account Information submitted successfully!</font>");
						FORWARD_PAGE = "nominee";
					} else {
						request.setAttribute("failure",
								"<font color=red>Bank/Account Information not submitted successfully!</font>");
						FORWARD_PAGE = "New";
					}
//					if (flag && session.getAttribute("bankupdate") == null) {
//						ArrayList list = null;
//						if (session.getAttribute("tablist") != null) {
//							list = (ArrayList) session.getAttribute("tablist");
//						} else {
//							list = new ArrayList();
//						}
//						if (!list.contains("PROPERTY")) {
//							bankForm.setActionType("");
//							FORWARD_PAGE = "property";
//						} else if (!list.contains("TALENT")) {
//							bankForm.setActionType("");
//							FORWARD_PAGE = "talent";
//						} else if (!list.contains("OFFICE")) {
//							bankForm.setActionType("");
//							FORWARD_PAGE = "office";
//						} else {
//							FORWARD_PAGE = "success";
//						}
//						list.add("BANK");
//						session.setAttribute("tablist", list);
//						request
//								.setAttribute("success",
//										"<font color=red>Bank/Account Information submitted successfully!</font>");
//
//					} else if (session.getAttribute("bankupdate") != null
//							&& session.getAttribute("bankupdate").toString()
//									.equals("true")) {
//
//						FORWARD_PAGE = "viewpersonal";
//					} else {
//						request
//								.setAttribute("failure",
//										"<font color=red>Bank/Account Information not submitted successfully!</font>");
//						FORWARD_PAGE = "New";
//					}

				}

			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		//setPersonalSessionValue(strEmployeeId, session);
		return mapping.findForward(FORWARD_PAGE);
	}

	/**
	 * @param strEmployeeId
	 * @param session
	 */
	private void setPersonalSessionValue(String strEmployeeId, HttpSession session) {
		try {
			EmpmgmtViewBD employeeViewBD = new EmpmgmtViewBD();
			//added by shruti--to resolve compilation problem
			String locale="";
			Locale currentLocale=new Locale(locale);
			if(session.getAttribute("org.apache.struts.action.LOCALE")!=null){
				currentLocale=(Locale)session.getAttribute("org.apache.struts.action.LOCALE");
				locale=currentLocale.toString();
				
			}
			//end
			PersonalDetailsDTO personalDetailsDTO = employeeViewBD.getPersonalDetails(strEmployeeId,locale);
			
			ArrayList childList = employeeViewBD.getChildDetails(strEmployeeId);
			if (personalDetailsDTO != null || childList != null) {
				PersonalForm personalForm = (PersonalForm) session.getAttribute("personalForm");
				if(personalForm == null) {
					personalForm = new PersonalForm();
				}
				personalForm.setChildList(childList);
				personalForm.setPersonalDTO(personalDetailsDTO);
				session.setAttribute("personalForm", personalForm);
				session.setAttribute("employeeId", strEmployeeId);
				session.setAttribute("Personal", personalDetailsDTO);
				session.setAttribute("childList", childList);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	/**
	 * @param request
	 */
	private void printAttributes(HttpServletRequest request) {
		Enumeration<String> names = request.getAttributeNames();
		while (names.hasMoreElements()) {
			String name = (String) names.nextElement();
			Object attribute = request.getAttribute(name);
//			System.out.print("Name : " + name + "\t\t");
//			System.out.println("Attribute : " + attribute);
		}
	}
	
	/**
	 * @param request
	 */
	private void printParams(HttpServletRequest request) {
		Enumeration<String> names = request.getParameterNames();
		while (names.hasMoreElements()) {
			String name = (String) names.nextElement();
			String param = request.getParameter(name);
//			System.out.print("Name : " + name + "\t\t");
//			System.out.println("Parameter : " + param);
		}
	}

}