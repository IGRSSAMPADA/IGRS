package com.wipro.igrs.caveats.form;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class DocumentFlagEvaluationForm extends ActionForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String regId;
	private FormFile uploadedDoc;
	private String remarks;
	private String regNumber;
	private long timeTaken;
	private String headerImg;
	private String finalDocPath;
	private String docName;
	private String fileError;
	private String fileSizeError;
	private String error;
	private String lastRemarks;
	private String lastUploadPath;
	private String courtName;
	private String courtOrderNo;
	private String courtOrderDate;
	private String revertCourtName;
	private String revertCourtOrderNo;
	private String revertCourtOrderDate;
	
	public String getRevertCourtName() {
		return revertCourtName;
	}

	public void setRevertCourtName(String revertCourtName) {
		this.revertCourtName = revertCourtName;
	}

	public String getRevertCourtOrderNo() {
		return revertCourtOrderNo;
	}

	public void setRevertCourtOrderNo(String revertCourtOrderNo) {
		this.revertCourtOrderNo = revertCourtOrderNo;
	}

	public String getRevertCourtOrderDate() {
		return revertCourtOrderDate;
	}

	public void setRevertCourtOrderDate(String revertCourtOrderDate) {
		this.revertCourtOrderDate = revertCourtOrderDate;
	}


	public String getCourtName() {
		return courtName;
	}

	public void setCourtName(String courtName) {
		this.courtName = courtName;
	}

	public String getCourtOrderNo() {
		return courtOrderNo;
	}

	public void setCourtOrderNo(String courtOrderNo) {
		this.courtOrderNo = courtOrderNo;
	}

	public String getCourtOrderDate() {
		return courtOrderDate;
	}

	public void setCourtOrderDate(String courtOrderDate) {
		this.courtOrderDate = courtOrderDate;
	}

	public String getLastRemarks() {
		return lastRemarks;
	}

	public void setLastRemarks(String lastRemarks) {
		this.lastRemarks = lastRemarks;
	}

	public String getLastUploadPath() {
		return lastUploadPath;
	}

	public void setLastUploadPath(String lastUploadPath) {
		this.lastUploadPath = lastUploadPath;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getError1() {
		return error1;
	}

	public void setError1(String error1) {
		this.error1 = error1;
	}

	// For Revert Null and Void
	private String regId1;
	private FormFile uploadedDoc1;
	private String remarks1;
	private String regNumber1;
	private long timeTaken1;
	private String headerImg1;
	private String error1;
	
	public String getRegId1() {
		return regId1;
	}

	public void setRegId1(String regId1) {
		this.regId1 = regId1;
	}

	public FormFile getUploadedDoc1() {
		return uploadedDoc1;
	}

	public void setUploadedDoc1(FormFile uploadedDoc1) {
		this.uploadedDoc1 = uploadedDoc1;
	}

	public String getRemarks1() {
		return remarks1;
	}

	public void setRemarks1(String remarks1) {
		this.remarks1 = remarks1;
	}

	public String getRegNumber1() {
		return regNumber1;
	}

	public void setRegNumber1(String regNumber1) {
		this.regNumber1 = regNumber1;
	}

	public long getTimeTaken1() {
		return timeTaken1;
	}

	public void setTimeTaken1(long timeTaken1) {
		this.timeTaken1 = timeTaken1;
	}

	public String getHeaderImg1() {
		return headerImg1;
	}

	public void setHeaderImg1(String headerImg1) {
		this.headerImg1 = headerImg1;
	}

	public String getFinalDocPath1() {
		return finalDocPath1;
	}

	public void setFinalDocPath1(String finalDocPath1) {
		this.finalDocPath1 = finalDocPath1;
	}

	public String getDocName1() {
		return docName1;
	}

	public void setDocName1(String docName1) {
		this.docName1 = docName1;
	}

	public String getFileError1() {
		return fileError1;
	}

	public void setFileError1(String fileError1) {
		this.fileError1 = fileError1;
	}

	public String getFileSizeError1() {
		return fileSizeError1;
	}

	public void setFileSizeError1(String fileSizeError1) {
		this.fileSizeError1 = fileSizeError1;
	}

	private String finalDocPath1;
	private String docName1;
	private String fileError1;
	private String fileSizeError1;

	public String getFileError() {
		return fileError;
	}

	public void setFileError(String fileError) {
		this.fileError = fileError;
	}

	public String getFileSizeError() {
		return fileSizeError;
	}

	public void setFileSizeError(String fileSizeError) {
		this.fileSizeError = fileSizeError;
	}

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public String getFinalDocPath() {
		return finalDocPath;
	}

	public void setFinalDocPath(String finalDocPath) {
		this.finalDocPath = finalDocPath;
	}

	public String getHeaderImg() {
		return headerImg;
	}

	public void setHeaderImg(String headerImg) {
		this.headerImg = headerImg;
	}

	public long getTimeTaken() {
		return timeTaken;
	}

	public void setTimeTaken(long timeTaken) {
		this.timeTaken = timeTaken;
	}

	public String getRegNumber() {
		return regNumber;
	}

	public void setRegNumber(String regNumber) {
		this.regNumber = regNumber;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public FormFile getUploadedDoc() {
		return uploadedDoc;
	}

	public void setUploadedDoc(FormFile uploadedDoc) {
		this.uploadedDoc = uploadedDoc;
	}

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

}
