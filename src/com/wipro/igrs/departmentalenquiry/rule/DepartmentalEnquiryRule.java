/**
 * DepartmentalEnquiryRule.java
 */

package com.wipro.igrs.departmentalenquiry.rule;

import java.util.ArrayList;

import org.apache.struts.upload.FormFile;

import com.wipro.igrs.departmentalenquiry.dto.ChargeSheetReleaseDTO;
import com.wipro.igrs.departmentalenquiry.dto.CriminalCaseEnquiryDTO;
import com.wipro.igrs.departmentalenquiry.dto.EmployeeAcceptChanrgesDTO;
import com.wipro.igrs.departmentalenquiry.dto.EmployeeReleaseChanrgesDTO;
import com.wipro.igrs.departmentalenquiry.dto.PreliminaryEnquiryDTO;
import com.wipro.igrs.departmentalenquiry.dto.SuspensionOrderDTO;
import com.wipro.igrs.departmentalenquiry.form.ChargeSheetForm;
import com.wipro.igrs.departmentalenquiry.form.DeInitiateForm;
import com.wipro.igrs.departmentalenquiry.form.DepartmentalEnquiryForm;
import com.wipro.igrs.departmentalenquiry.form.PreliminaryEnquiryForm;
import com.wipro.igrs.departmentalenquiry.form.SuspensionForm;
import com.wipro.igrs.departmentalenquiry.form.UploadWitnessForm;
import com.wipro.igrs.util.PropertiesFileReader;

/**
 * @author oneapps
 * 
 */
public class DepartmentalEnquiryRule {
	PropertiesFileReader pr = null;

	boolean flag = false;

	private boolean error;

	ArrayList errors = new ArrayList();

	public DepartmentalEnquiryRule() {

		try {
			pr = PropertiesFileReader.getInstance("resources.igrs");
		} catch (Exception e) {
			System.out.println("Not Able to Initilize the property File" + e);
		}
	}

	/**
	 * @param deForm
	 * @return
	 */
	public ArrayList validateSearch(DepartmentalEnquiryForm deForm) {
		System.out.println("I am in Departmental Enquiry Rule");
		CriminalCaseEnquiryDTO criminalCaseDTO = deForm
				.getCriminalCaseEnquiryDTO();
		if (deForm.getFormName().equalsIgnoreCase("searchEmp")
				&& criminalCaseDTO.getSearchFor()
						.equalsIgnoreCase("complainee")) {
			try {
				if (criminalCaseDTO.getUserIdType().length() == 0) {
					errors.add(pr.getValue("error.de.userType.required"));
					flag = true;
					setError(flag);
				}
				if (criminalCaseDTO.getTxtSearchId().length() == 0) {
					errors.add(pr.getValue("error.de.userType.required"));
					flag = true;
					setError(flag);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		if (deForm.getFormName().equalsIgnoreCase("searchEmp")
				&& criminalCaseDTO.getSearchFor()
						.equalsIgnoreCase("complainer")) {
			try {
				if (criminalCaseDTO.getCompUserIdType().length() == 0) {
					errors.add(pr.getValue("error.de.userType.required"));
					flag = true;
					setError(flag);
				}
				if (criminalCaseDTO.getTxtCompSearchId().length() == 0) {
					errors.add(pr.getValue("error.de.userType.required"));
					flag = true;
					setError(flag);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}
		return errors;
	}// END OF METHOD

	/**
	 * @param myForm
	 * @return
	 */
	public ArrayList validateCriminalCase(DepartmentalEnquiryForm myForm) {
		CriminalCaseEnquiryDTO myDTO = myForm.getCriminalCaseEnquiryDTO();
		try {
			if (myDTO.getCaseType().length() == 0) {
				errors.add(pr.getValue("error.de.caseType.required"));
				flag = true;
				setError(flag);
			}
			if (myDTO.getUserId().length() == 0) {

				errors.add(pr.getValue("error.de.complainee.userId"));
				flag = true;
				setError(flag);
			}
			if (myDTO.getComplainerUserId().length() == 0) {
				errors.add(pr.getValue("error.de.complainer.userId"));
				flag = true;
				setError(flag);
			}
			if (myDTO.getCriminalCaseAbout().length() == 0) {
				errors.add(pr.getValue("error.de.caseAbout"));
				flag = true;
				setError(flag);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return errors;
	}

	/**
	 * @param myform
	 * @return
	 */
	public ArrayList validateComplaintId(DeInitiateForm myform) {
		CriminalCaseEnquiryDTO myDTO = myform.getCriminalCaseDTO();
		try {
			if (myDTO.getComplaintNo().length() == 0) {
				errors.add(pr.getValue("error.de.complaintId"));
				flag = true;
				setError(flag);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return errors;
	}

	/**
	 * @param myForm
	 * @return
	 */
	public ArrayList validateDP(DeInitiateForm myForm) {
		CriminalCaseEnquiryDTO myDTO = myForm.getCriminalCaseDTO();
		try {
			if (myDTO.getUserIdType().length() == 0) {
				errors.add(pr.getValue("error.de.userType.required"));
				flag = true;
				setError(flag);
			}
			if (myDTO.getTxtSearchId().length() == 0) {
				errors.add(pr.getValue("error.de.searchId.required"));
				flag = true;
				setError(flag);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return errors;
	}

	/**
	 * @param myForm
	 * @return
	 */
	public ArrayList validatePreEnquiryAction(PreliminaryEnquiryForm myForm) {
		try {
			System.out.println("I am in Pre Enquiry Action Rule");
			System.out.println("Details Length :"
					+ myForm.getStrPriliminaryDetails().length());
			FormFile strFile = myForm.getTheFile();
			String fileExt = getFileExtension(strFile.getFileName());
			if (strFile == null) {
				errors.add(pr.getValue("error.de.fileRequired"));
				flag = true;
				setError(flag);
			}
			if (!validateFileType(fileExt)) {
				errors.add(pr.getValue("error.audit.fileType"));
				flag = true;
				setError(flag);
			}
			if (strFile.getFileName().length() >= 30) {
				errors.add(pr.getValue("error.de.fileNameLength"));
				flag = true;
				setError(flag);
			}
			if (strFile.getFileSize() >= 10485760) {
				errors.add(pr.getValue("error.de.fileSize"));
				flag = true;
				setError(flag);
			}
			if (myForm.getStrPriliminaryDetails().trim().length() == 0) {
				errors.add(pr.getValue("error.de.preliminaryDetails"));
				flag = true;
				setError(flag);
			}
			if (myForm.getStrPriliminaryDetails().trim().length() > 200) {
				errors.add(pr.getValue("error.de.preDetailsSize"));
				flag = true;
				setError(flag);
			}
			if (myForm.getStrPriliminaryComments().trim().length() == 0) {
				errors.add(pr.getValue("error.de.preliminaryComments"));
				flag = true;
				setError(flag);
			}
			if (myForm.getStrPriliminaryComments().trim().length() > 200) {
				errors.add(pr.getValue("error.de.preCommentSize"));
				flag = true;
				setError(flag);
			}
			if (myForm.getStrPreliminaryDate().trim().length() == 0) {
				errors.add(pr.getValue("error.de.preliminaryDate"));
				flag = true;
				setError(flag);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return errors;
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
	 * @param fileName
	 * @return
	 */
	private String getFileExtension(String fileName) {
		int pos = fileName.lastIndexOf(".");
		String strExt = fileName.substring(pos + 1, fileName.length());
		return strExt;
	}

	/**
	 * @param fileExt
	 * @return
	 */
	public boolean validateFileType(String fileExt) {
		String[] arrFileExt = { "doc", "xls", "pdf", "txt", "jpg", "tiff",
				"gif", "rtf", "zip" };
		boolean flagFile = false;
		try {
			for (int i = 0; i < arrFileExt.length; i++) {
				if (fileExt.equalsIgnoreCase(arrFileExt[i])) {
					flagFile = true;
					break;
				}
			}
			/*
			 * if(!flagFile) { errors.add(pr.getValue("error.audit.fileType"));
			 * flag = true; setError(flag); }
			 */
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return flagFile;
	}

	/**
	 * @param myForm
	 * @return
	 */
	public ArrayList validateDeSubmit(DeInitiateForm myForm) {
		CriminalCaseEnquiryDTO myDTO = myForm.getCriminalCaseDTO();
		try {
			if (myDTO.getDpUserId().length() == 0) {
				errors.add(pr.getValue("error.de.deputedPersonId"));
				flag = true;
				setError(flag);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return errors;
	}

	/**
	 * @param myForm
	 * @return
	 */
	public ArrayList validateEmployeeAcceptCharges(PreliminaryEnquiryForm myForm) {
		EmployeeAcceptChanrgesDTO myDTO = myForm.getEmpAcceptChargesDTO();
		try {
			if (myDTO.getStrFinalCharge().trim().length() == 0) {
				errors.add(pr.getValue("error.de.finalCharge"));
				flag = true;
				setError(flag);
			}
			if (myDTO.getStrNatureOfCharge() == null
					|| myDTO.getStrNatureOfCharge().trim().length() == 0) {
				errors.add(pr.getValue("error.de.NatureOfCharge"));
				flag = true;
				setError(flag);
			}
			if (myDTO.getStrCompliantStatus() == null
					|| myDTO.getStrCompliantStatus().trim().length() == 0) {
				errors.add(pr.getValue("error.de.complaintClosed"));
				flag = true;
				setError(flag);
			}
			if (myDTO.getStrComments() == null
					|| myDTO.getStrComments().trim().length() == 0) {
				errors.add(pr.getValue("error.de.commentRequired"));
				flag = true;
				setError(flag);
			}
			if (myDTO.getStrComments() == null
					|| myDTO.getStrComments().trim().length() > 200) {
				errors.add(pr.getValue("error.de.commentSize"));
				flag = true;
				setError(flag);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return errors;
	}

	/**
	 * @param myForm
	 * @return
	 */
	public ArrayList validateEmployeeReleaseCharges(
			PreliminaryEnquiryForm myForm) {
		EmployeeReleaseChanrgesDTO myDTO = myForm.getEmpReleaseDTO();
		try {
			if (myDTO.getStrCompliantStatus() == null
					|| myDTO.getStrCompliantStatus().length() == 0) {
				errors.add(pr.getValue("error.de.complaintStatus"));
				flag = true;
				setError(flag);
			}
			if (myDTO.getStrComments() == null
					|| myDTO.getStrComments().length() == 0) {
				errors.add(pr.getValue("error.de.commentRequired"));
				flag = true;
				setError(flag);
			}
			if (myDTO.getStrComments().length() > 200) {
				errors.add(pr.getValue("error.de.commentSize"));
				flag = true;
				setError(flag);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return errors;
	}

	public ArrayList validatePreEnquirySuspension(PreliminaryEnquiryForm myForm) {
		SuspensionOrderDTO myDTO = myForm.getSuspensionDTO();
		try {
			if (myDTO.getStrFinalCharge() == null
					|| myDTO.getStrFinalCharge().length() == 0) {
				errors.add(pr.getValue("error.de.finalCharge"));
				flag = true;
				setError(flag);
			}
			if (myDTO.getNatureOfCharge() == null
					|| myDTO.getStrFinalCharge().length() == 0) {
				errors.add(pr.getValue("error.de.NatureOfCharge"));
				flag = true;
				setError(flag);
			}
			if (myDTO.getStrIssuingAuthority() == null
					|| myDTO.getStrIssuingAuthority().length() == 0) {
				errors.add(pr.getValue("error.de.issuingAuthority"));
				flag = true;
				setError(flag);
			}
			if (myDTO.getStrDateOfIssue() == null
					|| myDTO.getStrDateOfIssue().length() == 0) {
				errors.add(pr.getValue("error.de.dateOfIssue"));
				flag = true;
				setError(flag);
			}
			if (myDTO.getStrPlaceOfIssue() == null
					|| myDTO.getStrPlaceOfIssue().length() == 0) {
				errors.add(pr.getValue("error.de.placeOfIssue"));
				flag = true;
				setError(flag);
			}
			if (myDTO.getStrAddress() == null
					|| myDTO.getStrAddress().length() == 0) {
				errors.add(pr.getValue("error.de.address"));
				flag = true;
				setError(flag);
			}
			if (myDTO.getStrDetails() == null
					|| myDTO.getStrDetails().length() == 0) {
				errors.add(pr.getValue("error.de.details"));
				flag = true;
				setError(flag);
			}
			if (myDTO.getStrDetails().length() > 200) {
				errors.add(pr.getValue("error.de.detailsLength"));
				flag = true;
				setError(flag);
			}
			if (myDTO.getStrComments() == null
					|| myDTO.getStrComments().length() == 0) {
				errors.add(pr.getValue("error.de.preliminaryComments"));
				flag = true;
				setError(flag);
			}
			if (myDTO.getStrComments().length() > 200) {
				errors.add(pr.getValue("error.de.preCommentSize"));
				flag = true;
				setError(flag);
			}
			FormFile strFile = myForm.getStrSignature();
			String fileExt = getFileExtension(strFile.getFileName());
			if (strFile == null) {
				errors.add(pr.getValue("error.de.fileRequired"));
				flag = true;
				setError(flag);
			}
			if (!validateImageType(fileExt)) {
				errors.add(pr.getValue("error.de.signatureFileType"));
				flag = true;
				setError(flag);
			}
			if (strFile.getFileName().length() >= 30) {
				errors.add(pr.getValue("error.de.fileNameLength"));
				flag = true;
				setError(flag);
			}
			if (strFile.getFileSize() >= 10485760) {
				errors.add(pr.getValue("error.de.fileSize"));
				flag = true;
				setError(flag);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return errors;
	}

	public boolean validateImageType(String fileExt) {
		String[] arrFileExt = { "gif", "jpg", "jpeg", "bmp" };
		boolean flagFile = false;
		try {
			for (int i = 0; i < arrFileExt.length; i++) {
				if (fileExt.equalsIgnoreCase(arrFileExt[i])) {
					flagFile = true;
					break;
				}
			}
			/*
			 * if(!flagFile) { errors.add(pr.getValue("error.audit.fileType"));
			 * flag = true; setError(flag); }
			 */
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return flagFile;
	}

	public boolean validateDocumentType(String fileExt) {
		String[] arrFileExt = { "doc", "xls", "pdf", "txt" };
		boolean flagFile = false;
		try {
			for (int i = 0; i < arrFileExt.length; i++) {
				if (fileExt.equalsIgnoreCase(arrFileExt[i])) {
					flagFile = true;
					break;
				}
			}
			/*
			 * if(!flagFile) { errors.add(pr.getValue("error.audit.fileType"));
			 * flag = true; setError(flag); }
			 */
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return flagFile;
	}

	public ArrayList validateSuspensionOrderInitiated(SuspensionForm sForm) {
		System.out.println("in RULE : validateSuspensionOrderInitiated");
		try {
			FormFile supDoc = sForm.getTheFile1();
			FormFile authSign = sForm.getTheFile2();
			// Validating Documents and Images
			this.validateDocument(supDoc);
			this.validateImage(authSign);
			// Pre Enquiry [Validation Rules]
			PreliminaryEnquiryDTO preDTO = sForm.getPrelimDTO();
			String preComments = preDTO.getStrPriliminaryComments();
			String preDetails = preDTO.getStrPriliminaryDetails();
			String preDate = preDTO.getStrPreliminaryDate();

			if (preComments == null || preComments.trim().length() == 0) {
				errors.add(pr.getValue("error.de.preliminaryComments"));
				flag = true;
				setError(flag);
			}
			if (preComments == null || preComments.trim().length() > 200) {
				errors.add(pr.getValue("error.de.preCommentSize"));
				flag = true;
				setError(flag);
			}
			if (preDetails == null || preDetails.trim().length() == 0) {
				errors.add(pr.getValue("error.de.preDetails"));
				flag = true;
				setError(flag);
			}
			if (preDetails == null || preDetails.trim().length() > 200) {
				errors.add(pr.getValue("error.de.preDetailsSize"));
				flag = true;
				setError(flag);
			}
			if (preDate == null || preDate.trim().length() == 0) {
				errors.add(pr.getValue("error.de.preDate"));
				flag = true;
				setError(flag);
			}

			// Pre Enquiry - Accept [Validation Rules]
			EmployeeAcceptChanrgesDTO acceptDTO = sForm.getPeAcceptsDTO();
			String finalCharge = acceptDTO.getStrFinalCharge();
			String natureOfCharge = acceptDTO.getStrNatureOfCharge();
			if (finalCharge == null || finalCharge.trim().length() == 0) {
				errors.add(pr.getValue("error.de.finalCharge"));
				flag = true;
				setError(flag);
			}
			if (natureOfCharge == null || natureOfCharge.trim().length() == 0) {
				errors.add(pr.getValue("error.de.NatureOfCharge"));
				flag = true;
				setError(flag);
			}

			// Suspension Order [Validation Rules]
			SuspensionOrderDTO suspenseDTO = sForm.getSuspenseDTO();
			String susAuth = suspenseDTO.getStrIssuingAuthority();
			String susDate = suspenseDTO.getStrDateOfIssue();
			String susPlace = suspenseDTO.getStrPlaceOfIssue();
			String susAddress = suspenseDTO.getStrAddress();
			String susDetails = suspenseDTO.getStrDetails();
			String susComments = suspenseDTO.getStrComments();
			if (susAuth == null || susAuth.trim().length() == 0) {
				errors.add(pr.getValue("error.de.susAuth"));
				flag = true;
				setError(flag);
			}
			if (susDate == null || susDate.trim().length() == 0) {
				errors.add(pr.getValue("error.de.susDate"));
				flag = true;
				setError(flag);
			}
			if (susPlace == null || susPlace.trim().length() == 0) {
				errors.add(pr.getValue("error.de.susPlace"));
				flag = true;
				setError(flag);
			}
			if (susAddress == null || susAddress.trim().length() == 0) {
				errors.add(pr.getValue("error.de.susAddress"));
				flag = true;
				setError(flag);
			}
			if (susDetails == null || susDetails.trim().length() == 0) {
				errors.add(pr.getValue("error.de.susDetails"));
				flag = true;
				setError(flag);
			}
			if (susComments == null || susComments.trim().length() == 0) {
				errors.add(pr.getValue("error.de.susComments"));
				flag = true;
				setError(flag);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return errors;
	}

	public ArrayList validateSuspensionOrderAccepted(SuspensionForm sForm) {
		System.out.println("in RULE : validateSuspensionOrderAccepted");
		try {
			FormFile authSign = sForm.getTheFile2();
			// Validating Documents and Images
			this.validateImage(authSign);

			// Suspension Order [Validation Rules]
			SuspensionOrderDTO suspenseDTO = sForm.getSuspenseDTO();
			String susAuth = suspenseDTO.getStrIssuingAuthority();
			String susDate = suspenseDTO.getStrDateOfIssue();
			String susPlace = suspenseDTO.getStrPlaceOfIssue();
			String susAddress = suspenseDTO.getStrAddress();
			String susDetails = suspenseDTO.getStrDetails();
			String susComments = suspenseDTO.getStrComments();
			if (susAuth == null || susAuth.trim().length() == 0) {
				errors.add(pr.getValue("error.de.susAuth"));
				flag = true;
				setError(flag);
			}
			if (susDate == null || susDate.trim().length() == 0) {
				errors.add(pr.getValue("error.de.susDate"));
				flag = true;
				setError(flag);
			}
			if (susPlace == null || susPlace.trim().length() == 0) {
				errors.add(pr.getValue("error.de.susPlace"));
				flag = true;
				setError(flag);
			}
			if (susAddress == null || susAddress.trim().length() == 0) {
				errors.add(pr.getValue("error.de.susAddress"));
				flag = true;
				setError(flag);
			}
			if (susDetails == null || susDetails.trim().length() == 0) {
				errors.add(pr.getValue("error.de.susDetails"));
				flag = true;
				setError(flag);
			}
			if (susComments == null || susComments.trim().length() == 0) {
				errors.add(pr.getValue("error.de.susComments"));
				flag = true;
				setError(flag);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return errors;
	}

	public void validateDocument(FormFile formFile) {
		try {
			if (formFile == null) {
				errors.add(pr.getValue("error.de.fileRequired"));
				flag = true;
				setError(flag);
			}
			String fileExt = getFileExtension(formFile.getFileName());
			if (!validateFileType(fileExt)) {
				errors.add(pr.getValue("error.de.docFileType"));
				flag = true;
				setError(flag);
			}
			if (formFile.getFileName().length() >= 30) {
				errors.add(pr.getValue("error.de.fileNameLength"));
				flag = true;
				setError(flag);
			}
			if (formFile.getFileSize() >= 10485760) {
				errors.add(pr.getValue("error.de.fileSize"));
				flag = true;
				setError(flag);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void validateImage(FormFile formFile) {
		try {
			if (formFile == null) {
				errors.add(pr.getValue("error.de.fileRequired"));
				flag = true;
				setError(flag);
			}
			String fileExt = getFileExtension(formFile.getFileName());
			if (!validateImageType(fileExt)) {
				errors.add(pr.getValue("error.de.imageFileType"));
				flag = true;
				setError(flag);
			}
			if (formFile.getFileName().length() >= 30) {
				errors.add(pr.getValue("error.de.fileNameLength"));
				flag = true;
				setError(flag);
			}
			if (formFile.getFileSize() >= 10485760) {
				errors.add(pr.getValue("error.de.fileSize"));
				flag = true;
				setError(flag);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param myform
	 * @return
	 */
	public ArrayList validateComplaintId(String complaintId) {
		try {
			if (complaintId.trim().length() == 0) {
				errors.add(pr.getValue("error.de.complaintId"));
				flag = true;
				setError(flag);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return errors;
	}

	public ArrayList validateChargesheetDoc(FormFile file) {
		validateDocument(file);
		return errors;
	}

	public ArrayList validateWitnessDetails(UploadWitnessForm myForm) {

		try {
			if (myForm.getStrEnqOfficerName() == null
					|| myForm.getStrEnqOfficerName().length() == 0) {
				errors.add(pr.getValue("error.de.witnessOfficerName"));
				flag = true;
				setError(flag);
			}
			validateDocument(myForm.getTheFile());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return errors;
	}

	public ArrayList validateSaveChargeSheet(ChargeSheetForm myForm) {
		ChargeSheetReleaseDTO myDTO = myForm.getChargeSheetDTO();
		try {
			if (myDTO.getStrCharge() == null
					|| myDTO.getStrCharge().length() < 1) {
				errors.add(pr.getValue("error.de.charge"));
				flag = true;
				setError(flag);
			}
			if (myDTO.getStrDateOfIssue() == null
					|| myDTO.getStrDateOfIssue().length() < 1) {
				errors.add(pr.getValue("error.de.dateOfIssue"));
				flag = true;
				setError(flag);
			}
			if (myDTO.getStrIssuingAuthority() == null
					|| myDTO.getStrIssuingAuthority().length() < 1) {
				errors.add(pr.getValue("error.de.issuingAuthority"));
				flag = true;
				setError(flag);
			}
			if (myDTO.getStrPlaceOfIssue() == null
					|| myDTO.getStrPlaceOfIssue().length() < 1) {
				errors.add(pr.getValue("error.de.placeOfIssue"));
				flag = true;
				setError(flag);
			}
			if (myDTO.getStrDetails() == null
					|| myDTO.getStrDetails().length() < 1) {
				errors.add(pr.getValue("error.de.preliminaryDetails"));
				flag = true;
				setError(flag);
			}
			if (myDTO.getStrDetails().trim().length() > 200) {
				errors.add(pr.getValue("error.de.preDetailsSize "));
				flag = true;
				setError(flag);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return errors;
	}

}
