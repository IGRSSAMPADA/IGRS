/*
* Copyright Notice
*==============================================================================
* This file contains proprietary information of IGRS Bhopal Madhya Pradesh .
* Copying or reproduction without prior written approval is prohibited.
* Copyright (c) 2007-08
*==============================================================================
*
* File Name   :   
 * Author      :   
 * Description :   
 * ----------------------------------------------------------------------------
* Version         Modified By     Modified On           Modification
* ----------------------------------------------------------------------------
* 1.0             SHRUTI          xxxxx              
 * --------------------------------------------------------------------------------
*/

package com.wipro.igrs.auditinspection.rule;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.wipro.igrs.auditinspection.dto.AGMPReportDetailsDTO;
import com.wipro.igrs.auditinspection.dto.PublicDTO;
import com.wipro.igrs.auditinspection.dto.SROCommentsDTO;
import com.wipro.igrs.auditinspection.dto.SROReportDetailsDTO;
import com.wipro.igrs.util.CommonUtil;
import com.wipro.igrs.util.PropertiesFileReader;

public class AuditInspectionRule {
	PropertiesFileReader pr = null;

	boolean flag = false;
	public static final String DEFAULT_DATE_FORMAT = "dd/MM/yyyy";
	public static DateFormat DATE_FORMATTER = new SimpleDateFormat(
			DEFAULT_DATE_FORMAT);

	private boolean error;

	ArrayList errors = new ArrayList();

	public AuditInspectionRule() {

		try {
			pr = PropertiesFileReader.getInstance("resources.igrs");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param agmpReport
	 * @return
	 */
	public ArrayList validateAudit(AGMPReportDetailsDTO agmpReport) {
	

		String auditType = agmpReport.getAuditType();
		if (agmpReport.getFormName().equalsIgnoreCase("NewRcptForm")) {
			try {
				if (auditType.length() == 0) {
					errors.add(pr.getValue("error.audit.auditType"));
					flag = true;
					setError(flag);
				}
				if (agmpReport.getReport().length() == 0) {
					errors.add(pr.getValue("error.audit.report"));
					flag = true;
					setError(flag);
				}
				if (agmpReport.getAuditBody().length() == 0) {
					errors.add(pr.getValue("error.audit.auditBody"));
					flag = true;
					setError(flag);
				}
				if (agmpReport.getReport().equalsIgnoreCase("existing")) {
					String fromDate = agmpReport.getFromDate();
					String toDate = agmpReport.getToDate();
					if (fromDate.length() == 0) {
						errors.add(pr.getValue("error.audit.fromDate"));
						flag = true;
						setError(flag);
					}
					if (toDate.length() == 0) {
						errors.add(pr.getValue("error.audit.toDate"));
						flag = true;
						setError(flag);
					}

					// Date comprision START
					if (fromDate.length() > 0 && toDate.length() > 0) {

						if (CommonUtil.isGreater(fromDate, toDate)) {
							errors.add(pr.getValue("error.audit.dateDiff"));
							flag = true;
							setError(flag);

						}
						java.util.Date sysUtilDate = new java.util.Date();
						Date dateFrom = CommonUtil.getJavaUtilDate(fromDate);
						Date dateTo = CommonUtil.getJavaUtilDate(toDate);

						if (dateFrom.after(sysUtilDate)) {
							errors.add(pr.getValue("error.audit.toDateSys"));
							flag = true;
							setError(flag);
						}

						if (dateTo.after(sysUtilDate)) {
							errors.add(pr.getValue("error.audit.fromDateSys"));
							flag = true;
							setError(flag);
						}

					}
					// Date comprision END

					/*
					 * if (fromDate.length() > 0 && toDate.length() > 0) { Date
					 * d1 = new Date(fromDate.trim()); Date d2 = new
					 * Date(toDate.trim()); if (d2.compareTo(d1) < 0) {
					 * errors.add(pr.getValue("error.audit.dateDiff")); flag =
					 * true; setError(flag); } }
					 */
					if (agmpReport.getSroId().length() == 0) {
						errors.add(pr.getValue("error.audit.officeName"));
						flag = true;
						setError(flag);
					}
				}

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		if (agmpReport.getFormName().equalsIgnoreCase("addDocAgmp")) {
			errors = validateAddDocForm(agmpReport);
			// agmpReport.setAddMore("addMore");
		}
		if (agmpReport.getFormName().equalsIgnoreCase("NewAgmpExpForm")) {
			//validateReport(agmpReport);
		}
		if (agmpReport.getFormName().equalsIgnoreCase("NewAgmpRcptForm")) {
			
			validateReport(agmpReport);
		}
		if (agmpReport.getFormName().equalsIgnoreCase("NewAuditRepotForm")) {
			validateParaType(agmpReport);
		}
		/*
		 * if(agmpReport.getFormName().equalsIgnoreCase("AddAuditParaForm")) {
		 * validateAuditPara(agmpReport); }
		 */
		if (agmpReport.getFormName().equalsIgnoreCase("NewInternalRcptForm")) {
			validateReport(agmpReport);
		}

		if (agmpReport.getFormName().equalsIgnoreCase("AddIntParaForm")) {
			validateAuditPara(agmpReport);
		}
		if (agmpReport.getFormName().equalsIgnoreCase("NewInternalForm")) {
			validateParaType(agmpReport);
		}
		if (agmpReport.getFormName().equalsIgnoreCase("NewExpAuditReportForm")) {
			//modfied by shruti---14 june 2014
			//validateParaType(agmpReport);
		}
		if (agmpReport.getFormName().equalsIgnoreCase("AddAuditParaForm")) {
			validateAuditPara(agmpReport);
		}
		if (agmpReport.getFormName().equalsIgnoreCase("NewIntExpForm")) {
			//validateReport(agmpReport);
		}
		if (agmpReport.getFormName().equalsIgnoreCase("AddIntAuditParaForm")) {
			validateAuditPara(agmpReport);
		}
		if (agmpReport.getFormName().equalsIgnoreCase("NewInternalExpForm")) {
			//validateParaType(agmpReport);
		}
		if (agmpReport.getFormName().equalsIgnoreCase("ExistingAuditPara")) {
			validateParaType(agmpReport);
		}
		if (agmpReport.getFormName().equalsIgnoreCase("ExistAddAuditPara")) {
			validateAuditPara(agmpReport);
		}
		if (agmpReport.getFormName().equalsIgnoreCase("ExistingExpAuditPara")) {
			//validateParaType(agmpReport);
		}
		if (agmpReport.getFormName().equalsIgnoreCase("ExistExpAddAuditPara")) {
			//validateAuditPara(agmpReport);
		}
		if (agmpReport.getFormName().equalsIgnoreCase("ExistingExpIntAuditPara")) {
			//validateParaType(agmpReport);
		}
		if (agmpReport.getFormName().equalsIgnoreCase("ExistExpIntAddAuditPara")) {
			//validateAuditPara(agmpReport);
		}
		return errors;
	}

	/**
	 * @param agmpReport
	 * @return
	 */
	public ArrayList validateAddDocForm(AGMPReportDetailsDTO agmpReport) {
		try {
			if (agmpReport.getTxtDocID().length() == 0) {

				errors.add(pr.getValue("error.docid"));
				flag = true;
				setError(flag);
			}
			if (agmpReport.getTempValAgmp().length() == 0) {
				errors.add(pr.getValue("error.valagmp"));
				flag = true;
				setError(flag);
			}
			if ((agmpReport.getTempValAgmp().length() != 0)
					&& isInteger(agmpReport.getTempValAgmp()) == false) {
				errors.add(pr.getValue("error.valagmp.number"));
				flag = true;
				setError(flag);
			}
			if (agmpReport.getTxtStampDuty().length() == 0) {
				errors.add(pr.getValue("error.stampduty"));
				flag = true;
				setError(flag);
			}
			if ((agmpReport.getTxtStampDuty().length() != 0)
					&& isInteger(agmpReport.getTxtStampDuty()) == false) {
				errors.add(pr.getValue("error.stampduty.number"));
				flag = true;
				setError(flag);
			}
			if (agmpReport.getTxtRegFee().length() == 0) {
				errors.add(pr.getValue("error.regfee"));
				flag = true;
				setError(flag);
			}
			if ((agmpReport.getTxtRegFee().length() != 0)
					&& isInteger(agmpReport.getTxtRegFee()) == false) {
				errors.add(pr.getValue("error.registrationfee.number"));
				flag = true;
				setError(flag);
			}
			if (agmpReport.getTxtObjDetails().length() == 0) {
				errors.add(pr.getValue("error.objdetails"));
				flag = true;
				setError(flag);
			}
			if (agmpReport.getTxtAgmpComm().length() == 0) {
				errors.add(pr.getValue("error.agmpcomments"));
				flag = true;
				setError(flag);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return errors;
	}

	/**
	 * @param agmpReport
	 */
	public void validateReport(AGMPReportDetailsDTO agmpReport) {
		// ArrayList listReportError = new ArrayList();
		try {
			if (agmpReport.getTxtAuditorName().length() == 0) {
				errors.add(pr.getValue("error.audit.auditorName"));
				flag = true;
				setError(flag);
			}
			if (agmpReport.getTxtAuditLocation().length() == 0) {
				errors.add(pr.getValue("error.audit.auditorLocation"));
				flag = true;
				setError(flag);
			}
			if (agmpReport.getTxtFromDate().length() == 0) {
				errors.add(pr.getValue("error.audit.fromDate"));
				flag = true;
				setError(flag);
			}
			if (agmpReport.getTxtToDate().length() == 0) {
				errors.add(pr.getValue("error.audit.toDate"));
				flag = true;
				setError(flag);
			}
			if (agmpReport.getTxtFromDate().length() > 0
					&& agmpReport.getTxtToDate().length() > 0) {
				Date d1 = new Date(agmpReport.getTxtFromDate().trim());
				Date d2 = new Date(agmpReport.getTxtToDate().trim());
				if (d2.compareTo(d1) < 0) {
					errors.add(pr.getValue("error.audit.dateDiff"));
					flag = true;
					setError(flag);
				}
			}
			if (agmpReport.getTxtAuditDate().length() == 0) {
				errors.add(pr.getValue("error.audit.auditDate"));
				flag = true;
				setError(flag);
			}
			if (agmpReport.getTxtDispatchDate().length() == 0) {
				errors.add(pr.getValue("error.audit.dispatchDate"));
				flag = true;
				setError(flag);
			}
			if (agmpReport.getTxtEntryDate().length() == 0) {
				errors.add(pr.getValue("error.audit.entryDate"));
				flag = true;
				setError(flag);
			}
			/*
			 * if (agmpReport.getTxtAuditDate().length() > 0 &&
			 * agmpReport.getTxtEntryDate().length() > 0) { Date d1 = new
			 * Date(agmpReport.getTxtAuditDate().trim()); Date d2 = new
			 * Date(agmpReport.getTxtEntryDate().trim()); if (d2.compareTo(d1) <
			 * 0) { errors.add(pr.getValue("error.audit.entryDateDiff")); flag =
			 * true; setError(flag); } }
			 */
			if (agmpReport.getTxtPhysicalId().length() == 0) {
				errors.add(pr.getValue("error.audit.physicalId"));
				flag = true;
				setError(flag);
			}
			if (agmpReport.getTxtComments().length() == 0) {
				errors.add(pr.getValue("error.audit.comment"));
				flag = true;
				setError(flag);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// return listReportError;
	}

	/**
	 * @param iNumber
	 * @return
	 */
	boolean isInteger(String iNumber) {

		int i;
		boolean flag = true;
		for (i = 0; i < iNumber.length(); i++) {
			char c = iNumber.charAt(i);

			if (((c >= '0') && (c <= '9'))) {
				flag = false;
			}
		}
		return flag;
	}

	/**
	 * @param error
	 */
	public void setError(boolean error) {
		this.error = error;
	}

	/**
	 * @return
	 */
	public boolean isError() {
		return error;
	}

	/**
	 * @param agmpReport
	 */
	public void validateAuditPara(AGMPReportDetailsDTO agmpReport) {
		try {
			if (agmpReport.getTextParaName().length() == 0) {
				errors.add(pr.getValue("error.audit.paraname"));
				flag = true;
				setError(flag);
			}
			if (agmpReport.getTextParaComments().length() == 0) {
				errors.add(pr.getValue("error.audit.paraComments"));
				flag = true;
				setError(flag);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * @param agmpReport
	 */
	public void validateParaType(AGMPReportDetailsDTO agmpReport) {
		try {
			if (agmpReport.getListParaType().length() == 0) {
				errors.add(pr.getValue("error.audit.paraType"));
				flag = true;
				setError(flag);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * @param fileExt
	 * @return
	 */
	public ArrayList validateFileType(String fileExt) {
		String[] arrFileExt = {"jpg"};
		boolean flagFile = false;
		try {
			for (int i = 0; i < arrFileExt.length; i++) {
				if (fileExt.equalsIgnoreCase(arrFileExt[i])) {
					flagFile = true;
					break;
				}
			}
			if (!flagFile) {
				errors.add(pr.getValue("error.audit.fileType"));
				flag = true;
				setError(flag);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return errors;
	}
	
	//Added by Mohit For POT
	public ArrayList validateFileTypePOT(String fileExt) {
		String[] arrFileExt = {"jpg"};
		boolean flagFile = false;
		try {
			for (int i = 0; i < arrFileExt.length; i++) {
				if (fileExt.equalsIgnoreCase(arrFileExt[i])) {
					flagFile = true;
					break;
				}
			}
			if (!flagFile) {
				errors.add(pr.getValue("error.audit.fileType"));
				flag = true;
				setError(flag);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return errors;
	}
	
	//added by shruti for acceptable file types in SP Module
	public ArrayList validateFileTypeSP(String fileExt) {
		String[] arrFileExt = { "tif","pdf", "jpg","tiff","gif","jpeg"};
		boolean flagFile = false;
		try {
			for (int i = 0; i < arrFileExt.length; i++) {
				if (fileExt.equalsIgnoreCase(arrFileExt[i])) {
					flagFile = true;
					break;
				}
			}
			if (!flagFile) {
				errors.add(pr.getValue("error.audit.fileType"));
				flag = true;
				setError(flag);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return errors;
	}
	public ArrayList validateFileTypeSPPhoto(String fileExt) {
		String[] arrFileExt = { "jpg","jpeg"};
		boolean flagFile = false;
		try {
			for (int i = 0; i < arrFileExt.length; i++) {
				if (fileExt.equalsIgnoreCase(arrFileExt[i])) {
					flagFile = true;
					break;
				}
			}
			if (!flagFile) {
				errors.add(pr.getValue("error.audit.fileType"));
				flag = true;
				setError(flag);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return errors;
	}
	//end
	
	
	public ArrayList validateFileTypeEstamp(String fileExt) {
		String[] arrFileExt = {"pdf"};
		boolean flagFile = false;
		try {
			for (int i = 0; i < arrFileExt.length; i++) {
				if (fileExt.equalsIgnoreCase(arrFileExt[i])) {
					flagFile = true;
					break;
				}
			}
			if (!flagFile) {
				errors.add(pr.getValue("error.audit.fileType"));
				flag = true;
				setError(flag);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return errors;
	}

	// Inspection Details validations
	/**
	 * @param sroReportDto
	 * @return
	 */
	public ArrayList validateNewInspection(SROReportDetailsDTO sroReportDto) {
		try {
		
			String name = sroReportDto.getInspectionAuthorityName();
			if (name.length() == 0) {
				errors.add(pr.getValue("error.inspection.authorityname"));
				setError(true);
			}
			if (sroReportDto.getInspectionStartDate().length() == 0) {
				errors.add(pr.getValue("error.inspection.startdate"));
				setError(true);
			}
			if (sroReportDto.getInspectionEndDate().length() == 0) {
				errors.add(pr.getValue("error.inspection.enddate"));
				setError(true);
			}
			if (sroReportDto.getInspectionDate().length() == 0) {
				errors.add(pr.getValue("error.inspection.inspectiondate"));
				setError(true);
			}
			if (sroReportDto.getLfyAnnualReceipts().length() == 0) {
				errors.add(pr.getValue("error.inspection.lfyannualreceipt"));
				setError(true);
			}
			if (sroReportDto.getLfyAnnualTargets().length() == 0) {
				errors.add(pr.getValue("error.inspection.lfyannualtarget"));
				setError(true);
			}
			if (sroReportDto.getCfyAnnualReceipt().length() == 0) {
				errors.add(pr.getValue("error.inspection.cfyannualreceipt"));
				setError(true);
			}
			if (sroReportDto.getCfyAnnualIncome().length() == 0) {
				errors.add(pr.getValue("error.inspection.cfyannualincome"));
				setError(true);
			}
			if (sroReportDto.getLfyPctComp().length() == 0) {
				errors.add(pr.getValue("error.inspection.lfypctcomp"));
				setError(true);
			}
			if (sroReportDto.getIncomReasonCfy().length() == 0) {
				errors.add(pr.getValue("error.inspection.cfyincomereason"));
				setError(true);
			}
			if (sroReportDto.getIncomeReasonLfy().length() == 0) {
				errors.add(pr.getValue("error.inspection.lfyincomereason"));
				setError(true);
			}
			ArrayList listComents = sroReportDto.getListSROComments();
			SROCommentsDTO sroComments = (SROCommentsDTO) listComents.get(0);
			if (sroComments.getSroComments().length() == 0) {
				errors.add(pr.getValue("error.inspection.srocomments"));
				setError(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return errors;
	}

	public ArrayList validateNewPublicAudit(PublicDTO publicDTO) {
		try {
			

			if (publicDTO.getOfficeId().trim().length() == 0) {
				errors.add(pr
						.getValue("error.public.inspection.publicOfficeName"));
				setError(true);
			}
			if (publicDTO.getFromDate().trim().length() > 0
					&& publicDTO.getToDate().trim().length() > 0) {
				Date d1 = new Date(publicDTO.getFromDate().trim());
				Date d2 = new Date(publicDTO.getToDate().trim());
				if ((d1.compareTo(d2)) > 0) {
					errors.add(pr.getValue("error.public.inspection.date"));
					flag = true;
					setError(flag);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return errors;
	}

	/*
	 * public ArrayList validateNewPublicAudit(publicDTO poireportDTO) { try {
	 * System.out.println("In AuditInspection Rule-Inspection ");
	 * 
	 * if (poireportDTO.getAddress().trim().length()==0) {
	 * errors.add(pr.getValue("error.public.inspection.address"));
	 * setError(true); } if (poireportDTO.getDistrict().trim().length()==0) {
	 * errors.add(pr.getValue("error.public.inspection.district"));
	 * setError(true); } if (poireportDTO.getComments().trim().length()==0) {
	 * errors.add(pr.getValue("error.public.inspection.comments"));
	 * setError(true); } if(poireportDTO.getFromDate().trim().length()>0 &&
	 * poireportDTO.getToDate().trim().length()>0){ Date d1=new
	 * Date(poireportDTO.getFromDate().trim()); Date d2=new
	 * Date(poireportDTO.getToDate().trim()); if((d1.compareTo(d2))>0){
	 * errors.add(pr.getValue("error.public.inspection.date")); setError(true); } }
	 * 
	 *  } catch (Exception e) { e.printStackTrace(); } return errors; }
	 */
}
