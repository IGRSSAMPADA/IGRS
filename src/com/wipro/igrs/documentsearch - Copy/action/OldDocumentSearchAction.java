/**
 * Action class for Old Document Search
 */
package com.wipro.igrs.documentsearch.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import com.wipro.igrs.common.AuditInspectionConstant;
import com.wipro.igrs.common.CommonConstant;
import com.wipro.igrs.common.PropertiesFileReader;
import com.wipro.igrs.documentsearch.bd.OldDocumentSearchBD;
import com.wipro.igrs.documentsearch.bo.DocumentSearchBO;
import com.wipro.igrs.documentsearch.dao.OldDocumentSearchDAO;
import com.wipro.igrs.documentsearch.dto.OldRegistrationMap;
import com.wipro.igrs.documentsearch.form.OldDocumentSearchForm;

/**
 * @author Aakash Agarwal
 * @email aakash.agarwal1@wipro.com
 */
public class OldDocumentSearchAction extends BaseAction {
	private final Logger logger = (Logger) Logger.getLogger(OldDocumentSearchAction.class);
	String forwardPage = "";
	String userid = "";
	String functionId = "";
	String language = "";
	String totalFee = "";
	String officeId = "";
	String pageName = "";
	String userTypeId = "";
	String searchType = "";

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.wipro.igrs.baseaction.action.BaseAction#execute(org.apache.struts
	 * .action.ActionMapping, org.apache.struts.action.ActionForm,
	 * javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse, javax.servlet.http.HttpSession)
	 */
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws IOException, ServletException, Exception {
		userid = (String) session.getAttribute("UserId");
		functionId = (String) session.getAttribute("functionId");
		language = (String) session.getAttribute("languageLocale");
		pageName = (String) request.getParameter("pageName");
		officeId = (String) session.getAttribute("loggedToOffice");
		searchType = (String) request.getParameter("searchType");
		if (pageName == null) {
			pageName = "";
		}
		if (searchType == null) {
			searchType = "";
		}
		OldDocumentSearchForm oldDocSrchForm = (OldDocumentSearchForm) form;
		OldDocumentSearchBD oldDocumentSearchBD = new OldDocumentSearchBD();
		DocumentSearchBO documentSearchBO = new DocumentSearchBO();
		try {
			PropertiesFileReader properties = PropertiesFileReader.getInstance("resources.igrs");
			String directory = properties.getValue("igrs_upload_path");
			oldDocSrchForm.setLanguage(language);
			oldDocSrchForm.setIsOld("Y");
			oldDocSrchForm.setFunctionId(functionId);
			oldDocSrchForm.setServiceId("");
			userTypeId = documentSearchBO.chkUser(userid);
			oldDocSrchForm.setUserTypeId(userTypeId);
			if (oldDocSrchForm.getUserTypeId().equalsIgnoreCase("I")) {
				ArrayList dtlList = (ArrayList) documentSearchBO.getInternalUserDtls(officeId);
				oldDocSrchForm.setParentOfficeId(officeId);
				for (int i = 0; i < dtlList.size(); i++) {
					ArrayList list = (ArrayList) dtlList.get(0);
					oldDocSrchForm.setParentOfficeName(list.get(0).toString());
					oldDocSrchForm.setParentDistrictId(list.get(1).toString());
					oldDocSrchForm.setParentDistrictName(list.get(2).toString());
				}
			} else if (oldDocSrchForm.getUserTypeId().equalsIgnoreCase("2")) {
				oldDocSrchForm.setParentDistrictName("NA");
				oldDocSrchForm.setParentDistrictId("NA");
				oldDocSrchForm.setParentOfficeId("NA");
				oldDocSrchForm.setParentOfficeName("NA");
			} else {
				ArrayList dtlList = documentSearchBO.getExternalUserDtls(userid);
				for (int i = 0; i < dtlList.size(); i++) {
					ArrayList list = (ArrayList) dtlList.get(0);
					oldDocSrchForm.setParentDistrictName(list.get(0).toString());
					oldDocSrchForm.setParentDistrictId(list.get(1).toString());
				}
				oldDocSrchForm.setParentOfficeId("NA");
				oldDocSrchForm.setParentOfficeName("NA");
			}
			if (pageName.equals("initialization")) {
				onload(oldDocSrchForm);
				totalFee = oldDocumentSearchBD.getOthersFeeDuty(oldDocSrchForm.getFunctionId(),
						oldDocSrchForm.getServiceId(), oldDocSrchForm.getUserTypeId());
				if (totalFee == "" || totalFee == null) {
					totalFee = "0";
				}
				oldDocSrchForm.setTotalFee(totalFee);
				session.setAttribute("Total_Fee", oldDocSrchForm.getTotalFee());
				if (searchType.equals("A"))
					forwardPage = "olddocumentsearchpagea";
				if (searchType.equals("B"))
					forwardPage = "olddocumentsearchpageb";
			}
			if (pageName.equals("registrationdashboard")) {
				List<OldRegistrationMap> registrationMap = getRegistrationMapDetails(oldDocSrchForm, userid,
						oldDocSrchForm.getFunctionId(), searchType);
				oldDocSrchForm.setRegistrationMap(registrationMap);
				session.setAttribute("registrationMapDetails", oldDocSrchForm.getRegistrationMap());
				if (registrationMap == null || registrationMap.isEmpty()) {
					oldDocSrchForm.setErrorName("No such record found \n\n ऐसा कोई रिकॉर्ड नहीं मिला ");
				}
				forwardPage = "olddocumentregistrationdashboard";
			}

			if (pageName.equals("dopayment")) {
				Map<String, String> deedRegistrationParamMap = oldDocumentSearchBD
						.getDeedRegistrationParamMap(oldDocSrchForm);
				String refId = saveTransaction(oldDocSrchForm, userid, oldDocSrchForm.getFunctionId(),
						deedRegistrationParamMap);
				oldDocSrchForm.setReferenceId(refId);
				if (userTypeId.equals("I")) {
					FormFile formFile = oldDocSrchForm.getFile();
					if (formFile != null) {
						String referenceId = oldDocSrchForm.getReferenceId();
						String filePath = directory + "//OLDDOCSEARCH//" + referenceId + "//";
						String fileName = oldDocSrchForm.getUploadDocumentStack();
						uploadFile(formFile, fileName, filePath);
					}
					updateOldSearchATxnDetails(oldDocSrchForm);
				}
				if (oldDocSrchForm.getActionName() != null && oldDocSrchForm.getActionName().equals("paymentModule")) {
					oldDocSrchForm.setActionName("");
					doPayment(request, session, oldDocSrchForm, oldDocumentSearchBD, language);
				}
			}
			if (pageName.equals("olddocumentsearchresultpagewithoutpayment")) {
				Map<String, String> deedRegistrationParamMap = oldDocumentSearchBD
						.getDeedRegistrationParamMap(oldDocSrchForm);
				String refId = saveTransaction(oldDocSrchForm, userid, oldDocSrchForm.getFunctionId(),
						deedRegistrationParamMap);
				oldDocSrchForm.setReferenceId(refId);
				if (userTypeId.equals("I")) {
					FormFile formFile = oldDocSrchForm.getFile();
					if (formFile != null) {
						String referenceId = oldDocSrchForm.getReferenceId();
						String filePath = directory + "//OLDDOCSEARCH//" + referenceId + "//";
						String fileName = oldDocSrchForm.getUploadDocumentStack();
						oldDocSrchForm.setFilePath(filePath);
						uploadFile(formFile, fileName, filePath);
					}
					updateOldSearchATxnDetails(oldDocSrchForm);
				}
				oldDocumentSearchBD.getDeedDetails(oldDocSrchForm, deedRegistrationParamMap);
				forwardPage = "olddocumentsearchresultpage";
			}
			if (pageName.equals("olddocumentsearchresultpagewithpayment")) {
				String referenceNumber = request.getParameter("referenceNumber");
				onload(oldDocSrchForm);
				if (referenceNumber != "" || referenceNumber != null) {
					oldDocSrchForm.setReferenceId(referenceNumber);
					oldDocumentSearchBD.getOldSearchATxnDetails(oldDocSrchForm, referenceNumber, userid);
					List<OldRegistrationMap> registrationMap = getRegistrationMapDetails(oldDocSrchForm, userid,
							oldDocSrchForm.getFunctionId(), searchType);
					oldDocSrchForm.setRegistrationMap(registrationMap);
					oldDocSrchForm.setRadioButton(registrationMap.get(0).toString());
				}
				Map<String, String> deedRegistrationParamMap = oldDocumentSearchBD
						.getDeedRegistrationParamMap(oldDocSrchForm);
				oldDocumentSearchBD.getDeedDetails(oldDocSrchForm, deedRegistrationParamMap);
				forwardPage = "olddocumentsearchresultpage";
			}
			if (pageName.equals("uploadFile")) {
				List<String> errorList = new ArrayList<String>();
				FormFile file = oldDocSrchForm.getFile();
				byte content[] = file.getFileData();
				oldDocSrchForm.setContent(content);
				if ("".equals(file.getFileName())) {
					errorList.add("Invalid file selection. Please try again.\n\n अवैध फ़ाइल चयन |पुन: प्रयास करें |");
				}
				String fileExtension = getFileExtension(file.getFileName());
				AuditInspectionRule rule = new AuditInspectionRule();
				rule.validateFileType(fileExtension);
				int size = file.getFileSize();
				double fileSizeInBytes = size;
				double fileSizeInKB = fileSizeInBytes / 1024.0;
				double fileSizeInMB = fileSizeInKB / 1024.0;
				DecimalFormat decim = new DecimalFormat("#.##");
				Double fileSize = Double.parseDouble(decim.format(fileSizeInMB));
				if (rule.isError()) {
					errorList.add(
							"Invalid file type. Please select another file.\n\n अमान्य फ़ाइल प्रकार |अन्य फाइल चुनें |");
					request.setAttribute("errorList", errorList);
				} else {
					if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
						errorList.add(
								"File size is Greater than 10 MB. Please select another file.\n\n फ़ाइल का आकार 10 MB से अधिक है| अन्य फाइल चुनें|");
						request.setAttribute("errorList", errorList);
					} else {
						oldDocSrchForm.setUploadDocumentStack(file.getFileName());
					}
				}
				if (searchType.equals("A"))
					forwardPage = "olddocumentsearchpagea";
				if (searchType.equals("B"))
					forwardPage = "olddocumentsearchpageb";

			}
			if (pageName.equals("downLoadFile")) {
				byte content[] = oldDocSrchForm.getContent();
				String fileName = oldDocSrchForm.getUploadDocumentStack();
				downloadFile(response, content, fileName);
			}
			if (pageName.equals("removeFile")) {
				FormFile file = (FormFile) oldDocSrchForm.getFile();
				if (file != null) {
					String fileName = oldDocSrchForm.getUploadDocumentStack();
					String path = "";
					removeFile(fileName, path);
					oldDocSrchForm.setUploadDocumentStack("");
				}
				if (searchType.equals("A"))
					forwardPage = "olddocumentsearchpagea";
				if (searchType.equals("B"))
					forwardPage = "olddocumentsearchpageb";
			}

		} catch (Exception e) {
			logger.error(e);
			mapping.findForward("failure");
		}
		return mapping.findForward(forwardPage);
	}

	/**
	 * @param form
	 * @throws Exception
	 */
	public void onload(OldDocumentSearchForm oldDocSrchForm) throws Exception {
		oldDocSrchForm.setBook_number("");
		oldDocSrchForm.setDate_of_registration("");
		oldDocSrchForm.setDistrict("");
		oldDocSrchForm.setVolume_number("");
		oldDocSrchForm.setDocument_number("");
		oldDocSrchForm.setSro_name("");
		oldDocSrchForm.setName_of_sr("");
		oldDocSrchForm.setDeedCertificateOfSaleBuyerSellerDetails(null);
		oldDocSrchForm.setDeedCertificateOfSaleFloorDetails(null);
		oldDocSrchForm.setDeedCertificateOfSaleKhasraDetails(null);
		oldDocSrchForm.setDeedCertificateOfSalePropertyDetails(null);
		oldDocSrchForm.setDeedCertificateOfSaleStampDetails(null);
		oldDocSrchForm.setActionName("");
		oldDocSrchForm.setDistrictList(null);
		oldDocSrchForm.setErrorName("");
		oldDocSrchForm.setSroNameList(null);
		oldDocSrchForm.setTotalFee("");
		oldDocSrchForm.setRegistrationMap(null);
		oldDocSrchForm.setReferenceId("");
		oldDocSrchForm.setToDate("");
		oldDocSrchForm.setFromDate("");
		oldDocSrchForm.setWardNumber("");
		oldDocSrchForm.setOrganisationName("");
		oldDocSrchForm.setPartyName("");
		oldDocSrchForm.setRadioButton("");
		oldDocSrchForm.setWardNumber("");
		oldDocSrchForm.setRegistration_number("");
		oldDocSrchForm.setFeeString("");
		oldDocSrchForm.setKhasraNumber("");
		oldDocSrchForm.setDistrictList(null);
		oldDocSrchForm.setSroNameList(null);
		oldDocSrchForm.setReason("");
		oldDocSrchForm.setFile(null);
		oldDocSrchForm.setContent(null);
		oldDocSrchForm.setUploadDocumentStack("");
		oldDocSrchForm.setUploadFile("");
		oldDocSrchForm.setFilePath("");
		OldDocumentSearchBD oldDocumentSearchBD = new OldDocumentSearchBD();
		oldDocSrchForm.setDistrictList(oldDocumentSearchBD.getDistrictList());
		oldDocSrchForm.setSroNameList(oldDocumentSearchBD.getSroNameList());
	}

	/**
	 * @param form
	 * @param userId
	 * @param functionId
	 * @return
	 * @throws Exception
	 */
	public List<OldRegistrationMap> getRegistrationMapDetails(OldDocumentSearchForm form, String userId,
			String functionId, String searchType) throws Exception {
		List<OldRegistrationMap> registrationMap = null;
		OldDocumentSearchBD oldDocumentSearchBD = new OldDocumentSearchBD();
		Map<String, String> params = oldDocumentSearchBD.getParams(form);
		registrationMap = oldDocumentSearchBD.getDeedRegistrationMap(form, params, searchType);
		return registrationMap;
	}

	/**
	 * @param form
	 * @param userId
	 * @param functionId
	 * @return
	 * @throws Exception
	 */
	public String saveTransaction(OldDocumentSearchForm form, String userId, String functionId,
			Map<String, String> deedRegistrationParamMap) throws Exception {
		String transactionId = "";
		OldDocumentSearchDAO oldDocumentSearchDAO = new OldDocumentSearchDAO();
		transactionId = oldDocumentSearchDAO.storeOldSearchATxnDetails(form, userId, functionId,
				deedRegistrationParamMap);
		return transactionId;
	}

	/**
	 * @param request
	 * @param session
	 * @param form
	 * @param oldDocumentSearchBD
	 * @param language
	 * @return
	 * @throws Exception
	 */
	public String doPayment(HttpServletRequest request, HttpSession session, OldDocumentSearchForm form,
			OldDocumentSearchBD oldDocumentSearchBD, String language) throws Exception {
		String PTID = null;
		form.setActionName(null);
		try {
			String functionId = form.getFunctionId();
			String referenceId = form.getReferenceId();
			String amountTobePaid = form.getTotalFee();
			PTID = oldDocumentSearchBD.storePaymentTxnDetails(CommonConstant.PAYMENT_FLAG, form, userid, functionId,
					referenceId);
			request.setAttribute("forwardPath", "./olddocsearch.do?pageName=olddocumentsearchresultpage");
			request.setAttribute("parentTable", "IGRS_SEARCH_PAYMENT_DETAILS");
			request.setAttribute("parentAmount", amountTobePaid);
			request.setAttribute("parentFunId", functionId);
			request.setAttribute("parentKey", PTID);
			request.setAttribute("parentModName", session.getAttribute("modName"));
			request.setAttribute("parentFunName", functionId);
			request.setAttribute("parentReferenceId", referenceId);
			request.setAttribute("formName", "oldDocumentSearchForm");
			if ("en".equalsIgnoreCase(language)) {
				session.setAttribute("modName", CommonConstant.DOC_SRCH_MODNAME_ENGLISH);
			} else {
				session.setAttribute("modName", CommonConstant.DOC_SRCH_MODNAME_HINDI);
			}
			request.setAttribute("parentColumnName", "TRANSACTION_ID");
			request.setAttribute("parentOfficeId", form.getParentOfficeId());
			request.setAttribute("parentOfficeName", form.getParentOfficeName());
			request.setAttribute("parentDistrictId", form.getParentDistrictId());
			request.setAttribute("parentDistrictName", form.getParentDistrictName());
			session.setAttribute("Fee", form.getServiceFee());
			session.setAttribute("Other_Fee", form.getOtherFee());
			session.setAttribute("Total_Fee", form.getTotalFee());
			forwardPage = "forwardPath";
		} catch (Exception e) {
			logger.error(e);
		}
		return PTID;
	}

	/**
	 * @param response
	 * @param content
	 * @param fileName
	 */
	public void downloadFile(HttpServletResponse response, byte[] content, String fileName) {
		try {
			OutputStream os = response.getOutputStream();
			response.setContentType("application/download");
			response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
			os.write(content);
			os.flush();
			os.close();
		} catch (Exception e) {
			logger.error(e);
		}
	}

	/**
	 * @param fileName
	 * @return
	 */
	public String getFileExtension(String fileName) {
		try {
			int pos = fileName.lastIndexOf(".");
			String strExt = fileName.substring(pos + 1, fileName.length());
			return strExt;
		} catch (Exception e) {
			logger.error(e);
		}
		return "";
	}

	/**
	 * @param fileName
	 * @param filePath
	 * @return
	 */
	public boolean removeFile(String fileName, String filePath) {
		try {
			File file = new File(filePath + fileName);
			if (file.exists()) {
				file.delete();
				return true;
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return false;
	}

	/**
	 * @param file
	 * @param fileName
	 * @param filePath
	 * @return
	 */
	public boolean uploadFile(FormFile file, String fileName, String filePath) {
		try {
			File folder = new File(filePath);
			if (!folder.exists()) {
				folder.mkdirs();
			}
			File newFile = new File(filePath, fileName);
			FileOutputStream fos = new FileOutputStream(newFile);
			fos.write(file.getFileData());
			fos.close();
			return true;
		} catch (Exception e) {
			logger.error(e);
		}
		return false;
	}

	/**
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public boolean updateOldSearchATxnDetails(OldDocumentSearchForm form) throws Exception {
		OldDocumentSearchBD oldDocumentSearchBD = new OldDocumentSearchBD();
		return oldDocumentSearchBD.updateOldSearchATxnDetails(form);
	}
}
