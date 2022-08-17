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
package com.wipro.igrs.caveats.action;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.wipro.igrs.auditinspection.rule.AuditInspectionRule;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.caveats.bd.CaveatsDelegate;
import com.wipro.igrs.caveats.bo.CaveatsBO;
import com.wipro.igrs.caveats.dto.CaveatsDTO;
import com.wipro.igrs.caveats.form.CaveatsForm;
import com.wipro.igrs.common.AuditInspectionConstant;
import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.reginit.bo.RegCommonBO;
/**
* 
* CaveatLogBankAction.java <br>
* CaveatLogBankAction <br>
* 
* @version 1.0
* 
* @author <b>WIPRO JAVA TEAM</b> <br>
*         Created on 12-Apr-2008 <br>
*         Last Modified on 28-Mar-2013
*/
@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
public class CaveatLogBankAction extends BaseAction {
    
	private Logger logger = 
		(Logger) Logger.getLogger(CaveatActionConfirm.class);

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
                                 HttpServletRequest request, 
                                 HttpServletResponse response,HttpSession session) throws IOException, 
                                                                      ServletException,Exception {
		String FORWAD_SUCCESS = "failure";
		try {
			String userID = (String)session.getAttribute("UserId");
			CaveatsForm fm = (CaveatsForm) form;
			CaveatsDTO dto = fm.getCaveatsDTO();
			CaveatsDelegate cavBD = new CaveatsDelegate();
			String isMenuClick = request.getParameter("isMenuClick");
			String actionName = dto.getActionName(); 
			String language=(String)session.getAttribute("languageLocale");
	    	dto.setLanguage(language);
	    	
	    	String userId = (String) session.getAttribute("UserId");
	    	String activityid = request.getParameter("ACTID");
			
			if(activityid!=null)
			{
			IGRSCommon save=null;
			try {
				save = new IGRSCommon();
				save.saveactivitylog(userId, activityid);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			}
			
	    	
			ArrayList errorList = new ArrayList();
			if(dto.getStayOrderStartDate() != null) {
				fm.setStayOrderStartDate(dto.getStayOrderStartDate());
			}
			if(dto.getStayOrderUptoDate() != null) {
				fm.setStayOrderUptoDate(dto.getStayOrderUptoDate());
			}
			if(dto.getLoanDueDate() != null) {
				fm.setLoanDueDate(dto.getLoanDueDate());
			}
			
			if ("yes".equals(isMenuClick)) {
				FORWAD_SUCCESS = "init";
				session.setAttribute("MAX_FILE_SIZE", AuditInspectionConstant.MAX_FILE_SIZE);
				dto = new CaveatsDTO();
				dto.setCaveatTypeList(cavBD.getCaveatTypeList(language));
				actionName = dto.getActionName();
				fm.setCaveatsDTO(dto);
//				String bankRoleName = checkBankRoleForUser(userID, cavBD);
//				fm.setBankRoleName(bankRoleName);
//				if ("".equals(bankRoleName)) {
//					errorList.add("Please contact System Administrator. Bank Role Parameter is either not available or deactivated OR User does not have associated Bank Role");
//					request.setAttribute("errorMessage", "yes");
//					request.setAttribute("errorList", errorList);
//
//				}
			} else {
				if("search".equals(actionName)) {
					
					dto.setLoggedIn(userID);
					dto.setRegDate(new Date().toString());
					boolean isSuccess = cavBD.searchForOtt(dto);
					if (isSuccess == false) {
						errorList
								.add("No records available please check Registration Number and OTT Number/रिकॉर्ड उपलब्ध नहीं है कृपया पंजीकरण नंबर और ओ टी टी संख्या जाँच करें ");
						request.setAttribute("errorMessage", "yes");
						request.setAttribute("errorList", errorList);
						FORWAD_SUCCESS = "init";
					} else {
						FORWAD_SUCCESS = "success";
					}
				} else if ("setUploadFile".equals(actionName)) {
					try {
						FormFile uploadedFile = dto.getAttachedDoc();
						if ("".equals(uploadedFile.getFileName())) {
							errorList
									.add("Invalid file selection. Please try again./अवैध फ़ाइल चयन. पुन: प्रयास करें.");
						}

						String fileExt = getFileExtension(uploadedFile
								.getFileName());
						AuditInspectionRule rule = new AuditInspectionRule();
						rule.validateFileType(fileExt);
						int size = uploadedFile.getFileSize();
						if (rule.isError()) {
							errorList
									.add("Invalid file type. Please select another file./अमान्य फ़ाइल प्रकार. एक और फ़ाइल का चयन करें.");
							request.setAttribute("errorsList", errorList);
						} else {
							if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
								errorList
										.add("File size is Greater than 10 MB. Please select another file./फ़ाइल का आकार 10 एमबी से अधिक है. एक और फ़ाइल का चयन करें.");
								request.setAttribute("errorsList", errorList);
							} else {
								dto.setDocumentName(uploadedFile.getFileName());
								dto.setDocContents(uploadedFile.getFileData());
								dto.setDocFileSize(getFileSize(uploadedFile
										.getFileData().length));
							}
						}
					} catch (Exception e) {
						errorList
								.add("Unable to upload file. Please try again./फ़ाइल अपलोड करने में असमर्थ. पुन: प्रयास करें.");
						request.setAttribute("errorsList", errorList);
					}
					FORWAD_SUCCESS = "success";
					
				}
				else if ("getProperty".equals(actionName)) {
					try {
						CaveatsBO objCaveatBO = new CaveatsBO();
						RegCommonBO commonBo = new RegCommonBO();
						String propertyId=request.getParameter("propertyTxnID");
						String regId=request.getParameter("registrationNo");
						if(propertyId.length()==15){
							propertyId="0"+propertyId;
						}
						String reginitId="";
						if(regId!=null)
						reginitId=objCaveatBO.getReginitId(regId);
						RegCommonBO bo = new  RegCommonBO();
						dto.setMapPropertyTransPartyDisp(bo.getPropertyDetsForViewConfirm(reginitId, propertyId,language));
						FORWAD_SUCCESS = "propertyView";
						
						
						
				}catch (Exception e) {
					// TODO: handle exception
				}
				}
				else if("confirm".equals(actionName)) {
					boolean isSuccess = validateData(dto, errorList, cavBD, (Date)session.getAttribute("currSysDate"));
					if (isSuccess == false) {
						request.setAttribute("errorMessage", "yes");
						request.setAttribute("errorList", errorList);
						FORWAD_SUCCESS = "success";
					} else {
						ArrayList typeList = dto.getCaveatTypeList();
						for (Object item : typeList) {
							CaveatsDTO tmpDTO = (CaveatsDTO) item;
							if(dto.getCaveatType().equals(tmpDTO.getCaveatId())) {
								dto.setCaveatLabel(tmpDTO.getCaveatType());
								break;
							}
						}
						FORWAD_SUCCESS = "confirm";
						//boolean flag = cavBD.logBankCaveat(dto);
					}
				}
			}
			session.setAttribute("caveatfrm", fm);
			
		} catch (Exception e) {
		//	logger.error(e);
		}
		return mapping.findForward(FORWAD_SUCCESS);
    }
    /**
     * 
     * @param userID
     * @param cavBD
     * @return
     */
    private String checkBankRoleForUser(String userID, CaveatsDelegate cavBD) {
		String retVal;
		retVal = cavBD.checkBankRoleForUser(userID);
		return retVal;
	}
    /**
     * 
     * @param dto
     * @param errorList
     * @param cavBD
     * @param currSysDate
     * @return
     */
	private boolean validateData(CaveatsDTO dto, ArrayList errorList,
			CaveatsDelegate cavBD, Date currSysDate) {
		boolean retVal = false;
		try {
			String patternNumberDecimal = "^(([^0]{1})([0-9])*|(0{1}))(\\.\\d{2})?$";
			String patterEmail = "([\\w-\\.]+)@((?:[\\w]+\\.)+)([a-zA-Z]{2,4})";
			String patternPhone = "[0-9\\+\\-\\(\\)]*";
			String patternFullName = "[a-zA-Z\\s]*";
			String patternAddress = "[(a-zA-Z)(\\s)(0-9)(\\,)(\\.)(\\-)(\\/)(\\\\)]*";
			String patternBankName = "[(a-zA-Z)(\\s)(\\.)(\\-)]*";
			String patternBankNameNew="!@#$%^&*()+=[]\\\';,/{}|\":<>?0123456789";
			String patternBankNameNew1="[(a-zA-Z)(\\s)(\\,)(\\.)(\\-)(\\/)(\\\\)]*";
			errorList.clear();
			String tmpVal;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Calendar sysCal = Calendar.getInstance();
			sysCal.setTime(currSysDate);
			sysCal.set(Calendar.HOUR_OF_DAY, 0);
			sysCal.set(Calendar.MINUTE, 0);
			sysCal.set(Calendar.SECOND, 0);
			sysCal.set(Calendar.MILLISECOND, 0);
			currSysDate = sysCal.getTime();
			sdf.setLenient(false);

//			if (dto.getDocContents() == null
//					|| dto.getDocContents().length == 0) {
//				errorList.add("Please upload and attach a supporting document");
//			}

			if (dto.getRemarks() != null) {
				tmpVal = dto.getRemarks().trim();
				dto.setRemarks(tmpVal);
				if (("".equals(tmpVal)) == false) {
					if (tmpVal.length() > 200) {
						errorList
								.add("Remarks can contain upto 200 characters/रिमार्क्स 200 अक्षर तक शामिल कर सकते हैं");
					}
				}
			}

//			if (dto.getCaveatDetails() == null
//					|| "".equals(dto.getCaveatDetails().trim())) {
//				errorList.add("Please specify Caveat Details");
//			} else {
//				tmpVal = dto.getCaveatDetails().trim();
//				dto.setCaveatDetails(tmpVal);
//				if (tmpVal.length() > 200) {
//					errorList
//							.add("Caveat Details can contain upto 200 characters");
//				}
//			}

			boolean flagStartDate = false;
			boolean flagEndDate = false;
			Date startDate = null;
			Date endDate = null;
			if (dto.getStayOrderStartDate() != null) {
				tmpVal = dto.getStayOrderStartDate().trim();
				dto.setStayOrderStartDate(tmpVal);
				if ("".equals(tmpVal) != true) {
					try {
						startDate = sdf.parse(tmpVal);
						flagStartDate = true;
					} catch (ParseException e) {
						errorList
								.add("Please enter proper Stay Order Start Date/उचित स्टे ऑर्डर प्रारंभ दिनांक दर्ज करें");
					}
				}
			}

			if (dto.getStayOrderUptoDate() != null) {
				tmpVal = dto.getStayOrderUptoDate().trim();
				dto.setStayOrderUptoDate(tmpVal);
				if ("".equals(tmpVal) != true) {
					try {
						endDate = sdf.parse(tmpVal);
						flagEndDate = true;
					} catch (ParseException e) {
						errorList
								.add("Please enter proper Stay Order Upto Date/ उचित स्टे ऑर्डर तिथि तक  दर्ज करें");
					}
				}
			}
			if (flagEndDate) {
				if (endDate.before(currSysDate)) {
					errorList
							.add("Please enter proper Stay Order Upto Date (Past dates are not allowed)/ उचित स्टे ऑर्डर (विगत दिनांक अनुमति नहीं है)तिथि तक दर्ज करें");
				}
			}
			if (flagStartDate != true && flagEndDate) {
				errorList.add("Please enter Stay Order Start Date/स्टे  आर्डर प्रारंभ तिथि  दर्ज करें");
			}

			if (flagStartDate && flagEndDate) {

				if (endDate.before(startDate)) {
					errorList
							.add("Please enter proper Stay Order Start Date and Stay Order Upto Date/उचित  स्टे  आर्डर प्रारंभ तिथि  और तिथि तक दर्ज करें ");
				}

			}

			if (dto.getStayOrderDetails() != null) {
				tmpVal = dto.getStayOrderDetails().trim();
				dto.setStayOrderDetails(tmpVal);
				if (("".equals(tmpVal)) == false) {
					if (tmpVal.length() > 200) {
						errorList
								.add("Stay Order Details can contain upto 200 characters/स्टे ऑर्डर विवरण 200 अक्षरों तक शामिल कर सकते हैं");
					}
				}
			}

			if (dto.getBankCourtRepName() != null) {
				tmpVal = dto.getBankCourtRepName().trim();
				dto.setBankCourtRepName(tmpVal);
				if (("".equals(tmpVal)) == false) {
					if (tmpVal.trim().length() > 30) {
						errorList
								.add("Representative Name can contain upto 30 characters/प्रतिनिधि का नाम 30 अक्षरों तक शामिल कर सकते हैं");
					} else if (tmpVal.matches("[a-zA-Z\\s]*") != true) {
						errorList
								.add("Please enter proper Representative Name/उचित प्रतिनिधि का नाम दर्ज करें");
					}
				}
			}

			if (dto.getStayOrderNo() != null) {
				tmpVal = dto.getStayOrderNo().trim();
				dto.setStayOrderNo(tmpVal);
				if (("".equals(tmpVal)) == false) {
					if (tmpVal.length() > 30) {
						errorList
								.add("Stay Order Number can contain upto 30 characters/स्टे ऑर्डर संख्या 30 अक्षरों तक शामिल कर सकते हैं");
					}
				}
			}

			boolean flagLoanDate = false;
			if (dto.getLoanDueDate() != null) {
				tmpVal = dto.getLoanDueDate().trim();
				dto.setLoanDueDate(tmpVal);
				if ("".equals(tmpVal) != true) {
					try {
						sdf.parse(tmpVal);
						flagLoanDate = true;
					} catch (ParseException e) {
						errorList
								.add("Please enter proper Due Date for completion of loan amount/ऋण राशि का पूरा करने के लिए उचित लंबित दिनांक दर्ज करें");
					}
				}
			}
			if (flagLoanDate != true) {
				errorList
						.add("Please enter Due Date for completion of loan amount/ऋण राशि का पूरा करने के लिए लंबित दिनांक दर्ज करें");
			}
			

			if (dto.getNoOfInstallments() == null
					|| "".equals(dto.getNoOfInstallments().trim())) {
				errorList.add("Please specify Number of Installments/किश्तों की संख्या निर्दिष्ट करें");
			} else {
				tmpVal = dto.getNoOfInstallments().trim();
				dto.setNoOfInstallments(tmpVal);
				if (tmpVal.length() > 3) {
					errorList
							.add("Number of Installments can contain upto 3 digits/किश्तों की संख्या 3 अंक तक  हो सकते हैं");
				}
				if (tmpVal.matches(patternNumberDecimal) == false) {
					errorList
							.add("Please specify a proper value for Number of Installments/किश्तों की संख्या के लिए एक उचित मूल्य निर्दिष्ट करें");
				}
			}

			if (dto.getAmountOfInstall() == null
					|| "".equals(dto.getAmountOfInstall().trim())) {
				errorList.add("Please specify Installment Amount/किस्त राशि निर्दिष्ट करें");
			} else {
				tmpVal = dto.getAmountOfInstall().trim();
				dto.setAmountOfInstall(tmpVal);
				if (tmpVal.length() > 10) {
					errorList
							.add("Installment Amount can contain upto 10 digits/किस्त राशि 10 अंक  तक हो सकते हैं");
				}
				if (tmpVal.matches(patternNumberDecimal) == false) {
					errorList
							.add("Please specify a proper value for Installment Amount/किस्त की रकम के लिए एक उचित मूल्य निर्दिष्ट करें");
				}
			}

			if (dto.getSecuredAmount() == null
					|| "".equals(dto.getSecuredAmount().trim())) {
				errorList.add("Please specify Secured Amount/सुरक्षित राशि निर्दिष्ट करें");
			} else {
				tmpVal = dto.getSecuredAmount().trim();
				dto.setSecuredAmount(tmpVal);
				if (tmpVal.length() > 10) {
					errorList.add("Secured Amount can contain upto 10 digits/सुरक्षित राशि 10 अंक तक  हो सकते हैं");
				}
				if (tmpVal.matches(patternNumberDecimal) == false) {
					errorList
							.add("Please specify a proper value for Secured Amount/सुरक्षित राशि के लिए एक उचित मूल्य निर्दिष्ट क ");
				}
			}
			
			if (dto.getStampPaid() == null
					|| "".equals(dto.getStampPaid().trim())) {
				errorList.add("Please specify Stamp Duty Paid/स्टाम्प ड्यूटी निर्दिष्ट करें");
			} else {
				tmpVal = dto.getStampPaid().trim();
				dto.setStampPaid(tmpVal);
				
				if (tmpVal.matches(patternNumberDecimal) == false) {
					errorList
							.add("Please specify a proper value for Stamp Duty Paid/स्टाम्प ड्यूटी के लिए एक उचित मूल्य निर्दिष्ट क ");
				}
			}
			
			if (dto.getMortgageType() == null
					|| "".equals(dto.getMortgageType().trim())) {
				errorList.add("Please specify Types Of Mortgage/बंधक के प्रकार निर्दिष्ट करें");
			} 
			if (dto.getLoanPurpose() == null
					|| "".equals(dto.getLoanPurpose().trim())) {
				errorList.add("Please specify Purpose Of Loan/ऋण का उद्देश्य निर्दिष्ट करें");
			} 
			
			if (dto.getRegDate() == null
					|| "".equals(dto.getRegDate().trim())) {
				errorList.add("Please specify Date Of Registration/Filing/ पंजीकरण /दाखिल करने की तारीख निर्दिष्ट करें");
			} 
			
			if (dto.getSdocuNumber() == null
					|| "".equals(dto.getSdocuNumber().trim())) {
				errorList.add("Please specify Supplementary Document/E-Registration Number/ अनुपूरक दस्तावेज / ई पंजीकरण संख्या निर्दिष्ट करें");
			}

			if (dto.getLoanAmount() == null
					|| "".equals(dto.getLoanAmount().trim())) {
				errorList.add("Please specify Loan Amount/ऋण की राशि निर्दिष्ट करें");
			} else {
				tmpVal = dto.getLoanAmount().trim();
				dto.setLoanAmount(tmpVal);
				if (tmpVal.length() > 10) {
					errorList.add("Loan Amount can contain upto 10 digits");
				}
				if (tmpVal.matches(patternNumberDecimal) == false) {
					errorList
							.add("Please specify a proper value for Loan Amount/ऋण की राशि के लिए एक उचित मूल्य निर्दिष्ट करें");
				}
			}

			if (dto.getLoanAccountNumber() == null
					|| "".equals(dto.getLoanAccountNumber().trim())) {
				errorList.add("Please specify Loan Account Number/ऋण खाता संख्या निर्दिष्ट करें");
			} else {
				tmpVal = dto.getLoanAccountNumber().trim();
				dto.setLoanAccountNumber(tmpVal);
				if (tmpVal.length() > 20) {
					errorList
							.add("Loan Account Number can contain upto 20 characters/numbers //ऋण खाता संख्या 20 वर्ण / संख्या तक शामिल कर सकते हैं");
				}
				if (tmpVal.matches(patternNumberDecimal) == false) {
//					errorList
//							.add("Please specify a proper value for Loan Account Number");
				}
			}

			if (dto.getEmailOfBankPerson() != null) {
				tmpVal = dto.getEmailOfBankPerson().trim();
				dto.setEmailOfBankPerson(tmpVal);
				if (("".equals(tmpVal)) == false) {
					if (tmpVal.length() > 30) {
						errorList.add("Email can contain upto 30 characters/ईमेल 30 अक्षरों तक शामिल कर सकते हैं");
					}
					if (tmpVal.matches(patterEmail) == false) {
						errorList.add("Please specify a proper Email ID/एक उचित ईमेल आईडी निर्दिष्ट करें");
					}
				}
			}

			if (dto.getMobOfBankPerson() != null) {
				tmpVal = dto.getMobOfBankPerson().trim();
				dto.setMobOfBankPerson(tmpVal);
				if (("".equals(tmpVal)) == false) {
					if (tmpVal.trim().length() > 15) {
						errorList
								.add("Phone/Mobile Number can contain upto 15 characters//फोन / मोबाइल नंबर  15 वर्ण तक हो सकते हैं");
					} else if (tmpVal.matches(patternPhone) != true) {
						errorList
								.add("Please enter proper Phone/Mobile Number//उचित फोन / मोबाइल नंबर दर्ज करें");
					}
				}
			}

			if (dto.getNameOfBankPerson() == null
					|| "".equals(dto.getNameOfBankPerson())) {
				errorList.add("Please enter Name of Person/व्यक्ति का नाम दर्ज करें");
			} else {
				tmpVal = dto.getNameOfBankPerson().trim();
				dto.setNameOfBankPerson(tmpVal);
				if (("".equals(tmpVal)) == false) {
					if (tmpVal.trim().length() > 30) {
						errorList
								.add("Name of Person can contain upto 30 characters/व्यक्ति का नाम 30 अक्षरों तक शामिल कर सकते हैं");
					}
					/*else if (tmpVal.matches(patternFullName) != true) {
						errorList.add("Please enter proper Name of Person/व्यक्ति का उचित नाम दर्ज करें");
					}*/
				}
			}

			if (dto.getAddressOfInsti() == null
					|| "".equals(dto.getAddressOfInsti())) {
				errorList.add("Please enter Address/पता दाखिल करें");
			} else {
				tmpVal = dto.getAddressOfInsti().trim();
				dto.setAddressOfInsti(tmpVal);
				if (("".equals(tmpVal)) == false) {
					if (tmpVal.trim().length() > 200) {
						errorList
								.add("Address can contain upto 200 characters/पता  200 वर्ण तक हो सकते हैं");
					} else if (tmpVal.matches(patternAddress) != true) {
//						errorList.add("Please enter proper Address");
					}
				}
			}

			if (dto.getNameOfInsti() == null || "".equals(dto.getNameOfInsti())) {
				errorList.add("Please enter Bank/Institute Name//बैंक / संस्थान का नाम दर्ज करें");
			} else {
				tmpVal = dto.getNameOfInsti().trim();
				dto.setNameOfInsti(tmpVal);
				if (("".equals(tmpVal)) == false) {
					if (tmpVal.trim().length() > 200) {
						errorList
								.add("Bank/Institute Name can contain upto 200 characters//बैंक / संस्थान का नाम  200 तक वर्ण हो सकते हैं");
					} /*else if (tmpVal.matches(patternBankName) !=true) {
						errorList
								.add("Please enter proper Bank/Institute Name//उचित बैंक / संस्थान का नाम दर्ज करें");
					}*/
				}
			}

//			if (dto.getCaveatType() == null || "".equals(dto.getCaveatType())) {
//				errorList.add("Please specify Type Of Caveat");
//			}
			
			//logger.debug("Number of error messages : " + errorList.size());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		} finally {
			Collections.reverse(errorList);
			retVal = errorList.isEmpty();
		}
		return retVal;
	}
	/**
	 * 
	 * @param fileName
	 * @return
	 */
	private String getFileExtension(String fileName) {
		try {
			int pos = fileName.lastIndexOf(".");
			String strExt = fileName.substring(pos + 1, fileName.length());
			return strExt;
		} catch (Exception e) {
		}
		return "";
	}
	
	private String getFileSize(int length) {
		double fileSizeInBytes = length;
		// Convert the bytes to Kilobytes (1 KB = 1024 Bytes)
		double fileSizeInKB = fileSizeInBytes / 1024.0;
		// Convert the KB to MegaBytes (1 MB = 1024 KBytes)
		double fileSizeInMB = fileSizeInKB / 1024.0;

		//System.out.println("fileSizeInBytes = "+fileSizeInBytes+" fileSizeInKB = "+fileSizeInKB+" fileSizeInMB = "+fileSizeInMB);
		DecimalFormat decim = new DecimalFormat("#.##");
		Double fileSize = Double.parseDouble(decim.format(fileSizeInMB));
		String photoSize="("+fileSize+" MB)";
		return photoSize;
	}
}
