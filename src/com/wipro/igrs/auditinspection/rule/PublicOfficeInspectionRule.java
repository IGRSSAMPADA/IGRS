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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;

import com.wipro.igrs.auditinspection.dto.PublicDTO;
import com.wipro.igrs.auditinspection.form.PublicForm;
import com.wipro.igrs.util.CommonUtil;
import com.wipro.igrs.util.PropertiesFileReader;

public class PublicOfficeInspectionRule {

	PropertiesFileReader pr = null;

	boolean flag = false;

	private boolean error;

	ArrayList errors = new ArrayList();
	private Logger logger = (Logger) Logger
			.getLogger(PublicOfficeInspectionRule.class);

	public PublicOfficeInspectionRule() {
		try {
			pr = PropertiesFileReader.getInstance("resources.igrs");
		} catch (Exception e) {
			logger.error("Not Able to Initilize the property File" + e);
		}
	}

	/**
	 * @return the flag
	 */
	public boolean getFlag() {
		return flag;
	}

	/**
	 * @param flag
	 *            the flag to set
	 */
	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	/**
	 * @return the error
	 */
	public boolean isError() {
		return error;
	}

	/**
	 * @param error
	 *            the error to set
	 */
	public void setError(boolean error) {
		this.error = error;
	}

	/**
	 * @param publicForm
	 * @return
	 */
	public ArrayList validateExistingReport(PublicForm publicForm) {
		try {
			if (publicForm.getPublicInspectionDTO().getReportType() == null
					|| publicForm.getPublicInspectionDTO().getReportType()
							.trim().length() == 0) {

				errors.add(pr.getValue("error.poi.reportType"));
				flag = true;
				setError(flag);

			} else {
				if (publicForm.getPublicInspectionDTO().getReportType()
						.equalsIgnoreCase("existing")) {

					if (publicForm.getPublicInspectionDTO().getFromDate() == null
							|| publicForm.getPublicInspectionDTO()
									.getFromDate().trim().length() < 1) {
						errors.add(pr.getValue("error.poi.fromDate"));
						flag = true;
						setError(flag);
					}
					if (publicForm.getPublicInspectionDTO().getToDate() == null
							|| publicForm.getPublicInspectionDTO().getToDate()
									.trim().length() < 1) {
						errors.add(pr.getValue("error.poi.toDate"));
						flag = true;
						setError(flag);
					}

					String fromDate = publicForm.getPublicInspectionDTO()
							.getFromDate();
					String toDate = publicForm.getPublicInspectionDTO()
							.getToDate();

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
				}
			}
			/*if (publicForm.getPublicInspectionDTO().getOfficeId() == null
					|| publicForm.getPublicInspectionDTO().getOfficeId().trim()
							.length() == 0) {
				errors.add(pr.getValue("error.poi.officeName"));
				flag = true;
				setError(flag);
			}*/
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return errors;
	}

	public ArrayList validateNewReport(PublicForm publicForm) {
		try {
			if (publicForm.getPublicInspectionDTO().getAddress() == null
					|| publicForm.getPublicInspectionDTO().getAddress().trim()
							.length() < 1) {

				errors.add(pr.getValue("error.poi.officeAddress"));
				flag = true;
				setError(flag);

			}

			if (publicForm.getPublicInspectionDTO().getDistrictId() == null
					|| publicForm.getPublicInspectionDTO().getDistrictId()
							.trim().length() < 1) {

				errors.add(pr.getValue("error.poi.district"));
				flag = true;
				setError(flag);

			}
			if (publicForm.getPublicInspectionDTO().getInsDate() == null
					|| publicForm.getPublicInspectionDTO().getInsDate().trim()
							.length() < 1) {

				errors.add(pr.getValue("error.poi.inspectionDate"));
				flag = true;
				setError(flag);

			}
			/*
			 * if (publicForm.getPublicInspectionDTO().getInsDate().length() >
			 * 0) { Date d1 = new Date(); Date d2 = new
			 * Date(publicForm.getPublicInspectionDTO().getInsDate().trim()); if
			 * (d1.compareTo(d2) < 0) {
			 * errors.add(pr.getValue("error.poi.inspectionGreater")); flag =
			 * true; setError(flag); } }
			 */
			if (publicForm.getPublicInspectionDTO().getFromDate() == null
					|| publicForm.getPublicInspectionDTO().getFromDate().trim()
							.length() < 1) {

				errors.add(pr.getValue("error.poi.fromDate"));
				flag = true;
				setError(flag);

			}
			/*
			 * if (publicForm.getPublicInspectionDTO().getFromDate().length() >
			 * 0) { Date d1 = new Date(); Date d2 = new
			 * Date(publicForm.getPublicInspectionDTO().getFromDate().trim());
			 * if (d1.compareTo(d2) < 0) {
			 * errors.add(pr.getValue("error.poi.fromDateGreater")); flag =
			 * true; setError(flag); } }
			 */
			if (publicForm.getPublicInspectionDTO().getToDate() == null
					|| publicForm.getPublicInspectionDTO().getToDate().trim()
							.length() < 1) {

				errors.add(pr.getValue("error.poi.toDate"));
				flag = true;
				setError(flag);

			}
			/*
			 * if (publicForm.getPublicInspectionDTO().getToDate().length() > 0) {
			 * Date d1 = new Date(); Date d2 = new
			 * Date(publicForm.getPublicInspectionDTO().getToDate().trim()); if
			 * (d1.compareTo(d2) < 0) {
			 * errors.add(pr.getValue("error.poi.toDateGreater")); flag = true;
			 * setError(flag); } } if
			 * (publicForm.getPublicInspectionDTO().getFromDate().length() > 0 &&
			 * publicForm.getPublicInspectionDTO().getToDate().length() > 0) {
			 * Date d1 = new
			 * Date(publicForm.getPublicInspectionDTO().getFromDate().trim());
			 * Date d2 = new
			 * Date(publicForm.getPublicInspectionDTO().getToDate().trim()); if
			 * (d2.compareTo(d1) < 0) {
			 * errors.add(pr.getValue("error.poi.dateDiff")); flag = true;
			 * setError(flag); } }
			 */
			if (publicForm.getPublicInspectionDTO().getComments() == null
					|| publicForm.getPublicInspectionDTO().getComments().trim()
							.length() < 1) {

				errors.add(pr.getValue("error.poi.comments"));
				flag = true;
				setError(flag);

			}
			if (publicForm.getPublicInspectionDTO().getComments().trim()
					.length() > 200) {
				errors.add(pr.getValue("error.poi.commentsLength"));
				flag = true;
				setError(flag);

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return errors;
	}

	public ArrayList validatePara(PublicForm publicForm) {
		try {
			if (publicForm.getPublicInspectionDTO().getParaType() == null
					|| publicForm.getPublicInspectionDTO().getParaType().trim()
							.length() < 1) {
				errors.add(pr.getValue("error.poi.paraType"));
				flag = true;
				setError(flag);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return errors;
	}

	public ArrayList validateParaDetails(PublicForm publicForm) {
		PublicDTO myPublicDTO = publicForm.getPublicInspectionDTO();
		try {
			if (myPublicDTO.getParaName() == null
					|| myPublicDTO.getParaName().trim().length() < 1) {
				errors.add(pr.getValue("error.poi.paraName"));
				flag = true;
				setError(flag);
			} else if (myPublicDTO.getParaName().trim().length() > 50) {
				errors.add(pr.getValue("error.poi.paraNameLength"));
				flag = true;
				setError(flag);
			}
			if (myPublicDTO.getParaComments() == null
					|| myPublicDTO.getParaComments().trim().length() < 1) {
				errors.add(pr.getValue("error.poi.paraComment"));
				flag = true;
				setError(flag);
			} else if (myPublicDTO.getParaName().trim().length() > 200) {
				errors.add(pr.getValue("error.poi.paraCommentLength"));
				flag = true;
				setError(flag);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return errors;
	}

	public ArrayList validateObjectedDocuments(PublicForm publicForm) {
		PublicDTO myPublicDTO = publicForm.getPublicInspectionDTO();
		try {
			if (myPublicDTO.getTxtDocID() == null
					|| myPublicDTO.getTxtDocID().trim().length() < 1) {
				errors.add(pr.getValue("error.poi.docId"));
				flag = true;
				setError(flag);
			}
			if (myPublicDTO.getTempValAgmp() == null
					|| myPublicDTO.getTempValAgmp().trim().length() < 1) {
				errors.add(pr.getValue("error.poi.propValByAGMP"));
				flag = true;
				setError(flag);
			}
			if (myPublicDTO.getTxtStampDuty() == null
					|| myPublicDTO.getTxtStampDuty().trim().length() < 1) {
				errors.add(pr.getValue("error.poi.deficientStampDuty"));
				flag = true;
				setError(flag);
			}
			if (myPublicDTO.getTxtRegFee() == null
					|| myPublicDTO.getTxtRegFee().trim().length() < 1) {
				errors.add(pr.getValue("error.poi.deficientRegFees"));
				flag = true;
				setError(flag);
			}
			if (myPublicDTO.getTxtObjDetails() == null
					|| myPublicDTO.getTxtObjDetails().trim().length() < 1) {
				errors.add(pr.getValue("error.poi.objectionDetails"));
				flag = true;
				setError(flag);
			} else if (myPublicDTO.getTxtObjDetails().trim().length() > 200) {
				errors.add(pr.getValue("error.poi.objectionDetailsLength"));
				flag = true;
				setError(flag);
			}
			if (myPublicDTO.getTxtAgmpComm() == null
					|| myPublicDTO.getTxtAgmpComm().trim().length() < 1) {
				errors.add(pr.getValue("error.poi.agmpComment"));
				flag = true;
				setError(flag);
			} else if (myPublicDTO.getTxtAgmpComm().trim().length() > 200) {
				errors.add(pr.getValue("error.poi.agmpCommentLength"));
				flag = true;
				setError(flag);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return errors;
	}

	public static String getConvertedDate(String dFromDate) {
		String inputDate = dFromDate;
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String finalDate = "";
		Date newDate = new Date();
		try {
			newDate = formatter.parse(inputDate);
			SimpleDateFormat format = new SimpleDateFormat("dd/MMM/yyyy");
			finalDate = format.format(newDate);
		} catch (ParseException e) {
			System.out.print(e);
		}
		return finalDate;
	}

}
