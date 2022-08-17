package com.wipro.igrs.slotbooking.action;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.wipro.igrs.DMSConnection.DMSUtility;
import com.wipro.igrs.auditinspection.rule.AuditInspectionRule;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.common.AuditInspectionConstant;
import com.wipro.igrs.common.PropertiesFileReader;
import com.wipro.igrs.common.mime.MIMECheck;
import com.wipro.igrs.newreginit.constant.RegInitConstant;
import com.wipro.igrs.newreginit.rule.RegInitRule;
import com.wipro.igrs.slotbooking.bd.SlotBookBD;
import com.wipro.igrs.slotbooking.bo.SlotReportBO;
import com.wipro.igrs.slotbooking.dto.SlotBookingUrgentDTO;
import com.wipro.igrs.slotbooking.form.SlotBookingUrgentForm;

enum ActionNameCalled {
	searchApplication, reschedule, payment, paymentCashLess, searchOffice, finalSubmit, fileUpload, downloadFile, slotReport, nocall, slotOfficeTime, downloadFileForReport, fetchReport, paymentCommonReceipt;
}
public class SlotBookingUrgent extends BaseAction {

	String forwardPage = "";

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {

		PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
		String lang = (String) session.getAttribute("languageLocale");
		Logger logger = (Logger) Logger.getLogger(SlotBookingUrgent.class);
		SlotBookingUrgentForm sForm = (SlotBookingUrgentForm) form;
		String moduleName = (String) request.getParameter("modName");
		String loggedInOffice = (String) session.getAttribute("loggedToOffice");
		logger.debug("!!!!From Slot booking urgent, logged in office id -- " + loggedInOffice + "!!!!");
		ActionNameCalled actionName = ActionNameCalled.valueOf((sForm.getActionName() == null) ? "nocall" : ("".equals(sForm.getActionName()) ? "nocall" : sForm.getActionName()));
		String sessionActionName = ((String) request.getParameter("actionname") == null) ? "" : (String) request.getParameter("actionname");
		if (null != sessionActionName) {
			if (!("".equals(sessionActionName))) {
				actionName = ActionNameCalled.valueOf(sessionActionName);
			}
		}
		SlotReportBO sbo = new SlotReportBO();
		SlotBookBD sbd = new SlotBookBD();
		logger.debug("in slot urgent booking");
		String officeId = (String) session.getAttribute("loggedToOffice");
		String userId = (null == session.getAttribute("UserId")) ? "" : (String) (session.getAttribute("UserId").toString());
		String officeTypeID = sbd.getOfficeType(officeId);
		officeTypeID = officeTypeID.split("-")[0];
		if (!("1".equals(officeTypeID) || "2".equals(officeTypeID))) {
			sForm.setIfError("unauthorized");
			forwardPage = "failureslot";
			return mapping.findForward(forwardPage);
		}
		// if(officeTypeID.)
		if (null != moduleName) {
			if (moduleName.equals("slotReschedule")) {
				sForm.setRegTxnId("");
				sForm.setReasonId("");
				sForm.setReasonDesc("");
				sForm.setActionName("");
				sForm.setIfError("");
				sForm.setFinalUploadFilePath("");
				sForm.setFileName("");
				sForm.setUpdateDate("");
				sForm.setReasonList(new ArrayList<SlotBookingUrgentDTO>());
				forwardPage = "searchApplication";
			} else if ("slotOfficeTimeCheck".equals(moduleName)) {
				sForm.setIfError("0");
				sForm.setActionName("");
				// actionName = "";
				SlotBookingUrgentDTO sdto = new SlotBookingUrgentDTO();
				sdto.setSelectedOfficeId("");
				sForm.setSelectedDistrictId("");
				sForm.setTimeInMinute("");
				sForm.setSelectedYesNo("");
				HashMap<String, String> districtList = sbd.getDistrictDetail(lang, loggedInOffice);
				districtList.put("ALL", "ALL");
				HashMap<String, String> officeList = sbd.getOfficeDetail("3", lang, sForm.getSelectedDistrictId(), loggedInOffice);
				sdto.setOfficeList(officeList);
				ArrayList<String> yesNoList = new ArrayList<String>();
				yesNoList.add("Yes");
				yesNoList.add("No");
				sForm.setYesNoList(yesNoList);
				sdto.setDistrictList(districtList);
				sForm.setSlotBookDto(sdto);
				actionName = ActionNameCalled.valueOf("slotOfficeTime");
				forwardPage = "slotStatusMapping";
			} else if ("slotReport".equals(moduleName)) {
				sForm.setIfError("1");
				sForm.setToDate("");
				sForm.setFrmDate("");
				HashMap<String, String> districtList = sbd.getDistrictDetail(lang, loggedInOffice);
				sForm.setSelectedDistrictId("");
				// sForm.setReportData(sbd.getReport(sForm, lang));
				sForm.setDistrictList(districtList);
				forwardPage = "slotRescheduleReport";
			} else if ("paymentDetails".equals(moduleName)) {

				String refID = request.getParameter("referenceID");
				String funcID = request.getParameter("payFuncID");
				sForm.setPaymentData(new ArrayList<SlotBookingUrgentDTO>());
				ArrayList payList = sbd.getPaymentRecord(refID, funcID);
				for (int i = 0; i < payList.size(); i++) {
					ArrayList tmp = (ArrayList) payList.get(i);
					SlotBookingUrgentDTO sdto = new SlotBookingUrgentDTO();
					if ("en".equals(lang)) {
						sdto.setPaymentType((String) (tmp.get(0)));
					} else {
						sdto.setPaymentType((String) (tmp.get(5)));
					}
					sdto.setTransactionID((String) tmp.get(3));
					sdto.setAmountPaid((String) tmp.get(1));
					sdto.setReferenceID((String) tmp.get(4));
					sdto.setPaymentDate((String) tmp.get(2));
					sdto.setPaymentBy((String) tmp.get(6));
					sForm.getPaymentData().add(sdto);
				}
				forwardPage = "paymentData";
			}
			return mapping.findForward(forwardPage);
		}
		switch (actionName) {
			case searchApplication :
				sForm.setChangedDate("");
				sForm.setChangeDate("");
				sForm.setUpdateDate("");
				sForm.setActionName("");
				sForm.setSlotBookTxnId("");
				sForm.setCashLessEnable("");
				if (sForm.getRegTxnId().startsWith("0")) {
					sForm.setRegTxnId(sForm.getRegTxnId().substring(1, (sForm.getRegTxnId().length())));
				}
				ArrayList slotDetail = sbo.getSlotDetail(sForm.getRegTxnId());
				int slotSize = slotDetail.size();

				switch (slotSize) {
					case 0 :
						String ifPresent = sbo.getIfPresent(sForm.getRegTxnId());
						if ("0".equalsIgnoreCase(ifPresent)) {
							sForm.setIfError("No Data");
						} else {
							String ifRescheduled = sbo.getIfRescheduled(sForm.getRegTxnId());
							if ("0".equalsIgnoreCase(ifRescheduled)) {
								sForm.setIfError("0");
							} else {
								sForm.setIfError("Already Rescheduled");
							}
						}
						logger.debug("SLOT URGENT BOOKING FAILED For application number " + sForm.getRegTxnId() + " !!!! Response Code -- " + sForm.getIfError());
						logger.debug("Reason --- Either Slot not booked for today or slot still has to be booked...");
						forwardPage = "searchApplication";
						break;

					case 1 :
						sForm.setIfError("1");
						sForm.setFileName("");
						sForm.setPaymentDone("");
						slotDetail = (ArrayList) slotDetail.get(0);
						boolean isWllDeed = false;
						if ("2225".equals(slotDetail.get(slotDetail.size() - 1))) {
							isWllDeed = true;
							sForm.setIfError("will deed");
							forwardPage = "searchApplication";
						}
						String ifRescheduled = sbo.getIfRescheduled(sForm.getRegTxnId());
						if (!isWllDeed) {
							if ("0".equalsIgnoreCase(ifRescheduled)) {
								sForm.setIfError("0");
							} else {
								if (null == slotDetail.get(3) || ("").equalsIgnoreCase((String) slotDetail.get(3))) {
									sForm.setIfError("Already Rescheduled");
								} else {
									ifRescheduled = "0";
									sForm.setIfError("0");
								}
							}
						}
						if (!isWllDeed) {

							if ("0".equalsIgnoreCase(ifRescheduled)) {
								sForm.setReasonList(new ArrayList<SlotBookingUrgentDTO>());
								String officeType = sbd.getOfficeType(officeId);
								String drDistId = officeType.split("-")[1];
								officeType = officeType.split("-")[0];

								if (!(drDistId.equalsIgnoreCase((String) slotDetail.get(8)))) {
									if ("1".equalsIgnoreCase(officeType)) {
										sForm.setReasonDesc("");
										sForm.setDateSelected("N");
										sbd.setSlotDetail(sForm, slotDetail, officeType, lang);
										forwardPage = "searchApplication";
									} else {
										sForm.setIfError("-5");
										forwardPage = "searchApplication";
									}
								} else {
									sForm.setReasonDesc("");
									sForm.setDateSelected("N");
									sbd.setSlotDetail(sForm, slotDetail, officeType, lang);
									forwardPage = "searchApplication";
								}
								if (!("".equalsIgnoreCase(sForm.getPayableAmount()))) {
									String chkPaymentStatus = sbd.getPaymentDetail(sForm.getRegTxnId(), sForm.getPayableAmount());
									if (chkPaymentStatus.equals(sForm.getPayableAmount())) {
										sForm.setPaymentDone("DONE");
										sForm.setPaymentFlag("");
									}
								}
							}
						}
						break;
				}
				break;

			case reschedule :
				String pattern = "dd/MM/yyyy";
				Date date1 = new Date();

				
				if ("Y".equalsIgnoreCase(sForm.getUpdateDate())) {
					sForm.setToBeBookedDate(sForm.getChangedDate());
				}
				
				String d1 = new SimpleDateFormat(pattern).format(date1);
				date1 = new SimpleDateFormat(pattern).parse(d1);
				Date date2 = new SimpleDateFormat(pattern).parse(sForm.getToBeBookedDate());

				if (date2.compareTo(date1) < 0) {
					sForm.setIfError("previousDateError");
					forwardPage = "searchApplication";
					return mapping.findForward(forwardPage);
				}

				sForm.setActionName("");
				logger.debug("!!!!!Proceeding for slot re-schedule option!!!!!");
				String officeType = sbd.getOfficeType(officeId);
				String drDistId = officeType.split("-")[1];
				officeType = officeType.split("-")[0];
				int officeTypeInNumber = Integer.parseInt(officeType);
				String chkPaymentStatus = sbd.getPaymentDetail(sForm.getRegTxnId(), sForm.getPayableAmount());
				String updateStatus = "";
				sForm.setCashLessEnable("");
				// sForm.setPaymentFlag("");
				String refId = "SLTTXNID" + (new Date()).getHours() + (new Date()).getMinutes() + (new Date()).getSeconds() + sForm.getRegTxnId();
				if (("").equals(sForm.getSlotBookTxnId())) {
					sForm.setSlotBookTxnId(refId);
				}
				boolean insertStatus = false;
				sForm.setCashLessEnable("");
				if (chkPaymentStatus.equals(sForm.getPayableAmount())) {
					sForm.setPaymentDone("Y");
					insertStatus = true;
				} else if (chkPaymentStatus.startsWith("SLTTXNID")) {
					// sForm.setPaymentFlag("Y");
					insertStatus = true;
				} else {
					boolean date = sbd.chkholidaydat(sForm.getToBeBookedDate());
					if (date) {
						sForm.setIfError("dateError");
						forwardPage = "searchApplication";
						return mapping.findForward(forwardPage);
					}
					insertStatus = sbd.insertSltRefDtl(refId, sForm.getPayableAmount(), sForm.getRegTxnId(), sForm.getFinalUploadFilePath(), sForm.getReasonId(), userId, sForm.getToBeBookedDate());
					if (insertStatus) {
						if ("Y".equals(sForm.getPaymentRequired())) {
							sForm.setIfError("1");
							sForm.setCashLessEnable("Y");
							sForm.setPaymentFlag("Y");
							forwardPage = "searchApplication";
							return mapping.findForward(forwardPage);
						}
					}

				}
				if (insertStatus) {
					switch (officeTypeInNumber) {
						case 1 :
							logger.debug("slot urgent booking executing by admin user...");
							updateStatus = sbd.updateSlotTime(sForm, userId, officeType, loggedInOffice);

							if ("1".equals(updateStatus)) {
								logger.debug("Slot has been successfully rescheduled...");
								sForm.setIfError("100");
								forwardPage = "searchApplication";
							} else {
								sForm.setIfError(updateStatus);
								forwardPage = "searchApplication";
							}
							break;
						case 2 :
							logger.debug("slot urgent booking executing by DR user Id...");
							updateStatus = sbd.updateSlotTime(sForm, userId, officeType, loggedInOffice);
							if ("1".equals(updateStatus)) {
								logger.debug("Slot has been successfully rescheduled...");
								sForm.setIfError("100");
								forwardPage = "searchApplication";
							} else {
								sForm.setIfError(updateStatus);
								forwardPage = "searchApplication";
							}
							break;
						case 3 :
							break;
					}
				}
				break;

			case payment :
				sForm.setActionName("");
				sForm.setPaymentFlag("N");
				String reasonId = sForm.getReasonId();
				ArrayList<SlotBookingUrgentDTO> reasonList = sForm.getReasonList();
				for (int i = 0; i < reasonList.size(); i++) {
					SlotBookingUrgentDTO tempDto = reasonList.get(i);
					if (tempDto.getReasonId().equals(reasonId)) {
						sForm.setPaymentRequired(tempDto.getPaymentRequired());
						sForm.setPayableAmount(tempDto.getPayableAmount());
						sForm.setPaymentFlag(tempDto.getPaymentRequired());
					}
				}
				String chkPaymentStatus1 = "";
				if ("Y".equals(sForm.getPaymentRequired())) {
					sForm.setPaymentFlag("Y");
					chkPaymentStatus1 = sbd.getPaymentStatus(sForm.getRegTxnId());
					if ("0".equals(chkPaymentStatus1)) {
						forwardPage = "searchApplication";
					}
				}
				forwardPage = "searchApplication";
				break;
			case paymentCashLess :
				sForm.setActionName("");
				request.setAttribute("referenceID", String.valueOf(sForm.getRegTxnId()));
				request.setAttribute("payableAmt", String.valueOf(sForm.getPayableAmount()));
				request.setAttribute("payFuncID", String.valueOf("FUN_077"));
				forwardPage = "paymentSltBooking";
				break;

			case searchOffice :
				sForm.setActionName("");
				SlotBookingUrgentDTO sdto = sForm.getSlotBookDto();
				HashMap<String, String> officeList = new HashMap<String, String>();
				if (!("ALL".equalsIgnoreCase(sForm.getSelectedDistrictId()))) {
					officeList = sbd.getOfficeDetail("3", lang, sForm.getSelectedDistrictId(), "");
				}
				officeList.put("ALL", "ALL");
				sdto.setOfficeList(officeList);
				sForm.setTimeInMinute("");
				sForm.setSelectedYesNo("");
				forwardPage = "slotStatusMapping";
				break;

			case finalSubmit :
				sForm.setActionName("");
				String responseCode = sbd.updateOfficeSlotData(sForm);
				sForm.setIfError(responseCode);
				forwardPage = "slotStatusMapping";
				break;
			case fileUpload :
				sForm.setActionName("");
				ArrayList errorList = new ArrayList();
				InputStream filecontent = null;
				// File file = new File(sForm.getFinalUploadFilePath());
				FormFile uploadedFile = sForm.getUploadFilePath();
				double fileSizeInBytes = uploadedFile.getFileSize();
				double fileSizeInKb = fileSizeInBytes / 1024;
				boolean allCheckPassed = true;
				if (fileSizeInKb > 150) {
					logger.debug("Upload file size is more than the prescribed one");
					sForm.setIfError("fileSizeError");
					allCheckPassed = false;
				}
				MIMECheck mimeCheck = new MIMECheck();
				String fileExt = mimeCheck.getFileExtension(uploadedFile.getFileName());
				sForm.setFileName(uploadedFile.getFileName());
				if ("".equals(fileExt)) {
					sForm.setIfError("fileTypeError");
					allCheckPassed = false;
				}
				if (allCheckPassed) {
					byte[] b1 = uploadedFile.getFileData();
					Boolean mime = mimeCheck.validateMIMEAndJPGAndPDFFileType(uploadedFile);
					if (!mime) {
						sForm.setIfError("fileTypeError");
						allCheckPassed = false;
					}
					if (allCheckPassed) {
						String path = pr.getValue("slt_booking_upload_path") + File.separator + sForm.getRegTxnId();
						/*
						 * File folder = new File(path); if (!folder.exists()) { folder.mkdirs(); }
						 */
						path = path + File.separator;
						String fileName = sForm.getReasonId() + "_" + (new Date().getDate()) + (new Date().getMinutes()) + (new Date().getSeconds() + "." + fileExt);
						/*
						 * File newFile = new File(path); if (!newFile.exists()) { logger.info("NEW FILE NAME:-" + newFile); FileOutputStream fos = new FileOutputStream(newFile); fos.write(b1); fos.close(); sForm.setFinalUploadFilePath(path); sForm.setUploadStatus("Y"); } else { FileOutputStream fos = new FileOutputStream(newFile); fos.write(b1); fos.close(); sForm.setFinalUploadFilePath(path); sForm.setUploadStatus("Y"); // logger.debug("file written:" + (filePath + // fileName)); }
						 */

						try {
							File folder = new File(path);
							if (!folder.exists()) {
								folder.mkdirs();
							}

							File newFile = new File(path, fileName);
							logger.info("NEW FILE NAME:-" + newFile);
							FileOutputStream fos = new FileOutputStream(newFile);
							fos.write(uploadedFile.getFileData());
							fos.close();
							sForm.setUploadStatus("Y");
							sForm.setFinalUploadFilePath(path + fileName);

						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
				}

				forwardPage = "searchApplication";
				break;
			case downloadFile :
				// String templatePath1=pr.getValue("clr.map.location");

				String getMapPath = sForm.getFinalUploadFilePath();
				String kId = request.getParameter("key");
				// getMapPath=getMapPath+kId+"/";
				// getMapPath=getMapPath+pr.getValue("clr_map_pdf");
				System.out.println("MPATH: " + getMapPath);
				byte[] templateContents = DMSUtility.getDocumentBytes(getMapPath);
				// DMSUtility.downloadDocument(respons,
				// eForm.getRegcompletDto().getMapPdf(),
				// eForm.getRegcompletDto().getMapPdfContents());
				DMSUtility.downloadDocument(response, getMapPath, templateContents);
				forwardPage = "searchApplication";

				break;
			case downloadFileForReport :

				String getUploadFilePath = (String) request.getParameter("filePath");
				String fileName = request.getParameter("key");
				System.out.println("MPATH: " + getUploadFilePath);
				byte[] content = DMSUtility.getDocumentBytes(getUploadFilePath);
				DMSUtility.downloadDocument(response, getUploadFilePath, content);
				forwardPage = "slotRescheduleReport";

				break;
			case slotOfficeTime :
				forwardPage = "slotStatusMapping";
				break;

			case fetchReport :
				sForm.setIfError("100");
				sForm.setReportData(sbd.getReport(sForm, lang));
				forwardPage = "slotRescheduleReport";
				break;
			case nocall :
				sForm.setIfError("urlerror");
				forwardPage = "failureslot";
				break;

			default :
				sForm.setIfError("urlerror");
				forwardPage = "failureslot";
				break;
		}
		// forwardPage = "searchApplication";
		return mapping.findForward(forwardPage);
	}
	public String getFileExtension(String fileName) {
		try {
			int pos = fileName.lastIndexOf(".");
			String strExt = fileName.substring(pos + 1, fileName.length());
			if (strExt != null && !" ".equalsIgnoreCase(strExt)) {
				strExt = strExt.toLowerCase();
			}
			return strExt;
		} catch (Exception e) {
		}
		return "";
	}
}
