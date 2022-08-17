/**
 * 
 */
package com.wipro.igrs.documentsearch.form;

import java.util.List;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import com.wipro.igrs.documentsearch.dto.DeedCertificateOfSaleBuyerSellerDetails;
import com.wipro.igrs.documentsearch.dto.DeedCertificateOfSaleFloorDetails;
import com.wipro.igrs.documentsearch.dto.DeedCertificateOfSaleKhasraDetails;
import com.wipro.igrs.documentsearch.dto.DeedCertificateOfSalePropertyDetails;
import com.wipro.igrs.documentsearch.dto.DeedCertificateOfSaleStampDetails;
import com.wipro.igrs.documentsearch.dto.DistrictDetailsDTO;
import com.wipro.igrs.documentsearch.dto.OldRegistrationMap;
import com.wipro.igrs.documentsearch.dto.SRONameDetailsDTO;

/**
 * @author Aakash Agarwal
 * @email aakash.agarwal1@wipro.com
 */

public class OldDocumentSearchForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7261702919066211397L;
	private List<OldRegistrationMap> registrationMap;
	private String registration_number;
	private String date_of_registration;
	private String district;
	private String volume_number;
	private String book_number;
	private String document_number;
	private String name_of_sr;
	private String sro_name;
	private String language;
	private List<DistrictDetailsDTO> districtList;
	private List<SRONameDetailsDTO> sroNameList;
	private String errorName;
	private String referenceId;
	private List<DeedCertificateOfSaleBuyerSellerDetails> deedCertificateOfSaleBuyerSellerDetails;
	private List<DeedCertificateOfSaleFloorDetails> deedCertificateOfSaleFloorDetails;
	private List<DeedCertificateOfSaleKhasraDetails> deedCertificateOfSaleKhasraDetails;
	private List<DeedCertificateOfSalePropertyDetails> deedCertificateOfSalePropertyDetails;
	private List<DeedCertificateOfSaleStampDetails> deedCertificateOfSaleStampDetails;
	private int isCompletePay = 0;
	private String feeString;
	private String totalFee;
	private String serviceFee = "0";
	private String otherFee = "0";
	private String functionId;
	private String serviceId;
	private String userTypeId;
	private String actionName;
	private String formName;
	private String parentOfficeName;
	private String parentDistrictId;
	private String parentDistrictName;
	private String parentOfficeId;
	private String isOld;
	private String wardNumber;
	private String khasraNumber;
	private String partyName;
	private String organisationName;
	private String toDate;
	private String fromDate;
	private String radioButton;
	private String reason;
	private FormFile file;
	private byte[] content;
	private String uploadDocumentStack;
	private String uploadFile;
	private String filePath;

	/**
	 * @return the date_of_registration
	 */
	public String getDate_of_registration() {
		return date_of_registration;
	}

	/**
	 * @param date_of_registration
	 *            the date_of_registration to set
	 */
	public void setDate_of_registration(String date_of_registration) {
		this.date_of_registration = date_of_registration;
	}

	/**
	 * @return the district
	 */
	public String getDistrict() {
		return district;
	}

	/**
	 * @param district
	 *            the district to set
	 */
	public void setDistrict(String district) {
		this.district = district;
	}

	/**
	 * @return the volume_number
	 */
	public String getVolume_number() {
		return volume_number;
	}

	/**
	 * @param volume_number
	 *            the volume_number to set
	 */
	public void setVolume_number(String volume_number) {
		this.volume_number = volume_number;
	}

	/**
	 * @return the book_number
	 */
	public String getBook_number() {
		return book_number;
	}

	/**
	 * @param book_number
	 *            the book_number to set
	 */
	public void setBook_number(String book_number) {
		this.book_number = book_number;
	}

	/**
	 * @return the document_number
	 */
	public String getDocument_number() {
		return document_number;
	}

	/**
	 * @param document_number
	 *            the document_number to set
	 */
	public void setDocument_number(String document_number) {
		this.document_number = document_number;
	}

	/**
	 * @return the name_of_sr
	 */
	public String getName_of_sr() {
		return name_of_sr;
	}

	/**
	 * @param name_of_sr
	 *            the name_of_sr to set
	 */
	public void setName_of_sr(String name_of_sr) {
		this.name_of_sr = name_of_sr;
	}

	/**
	 * @return the sro_name
	 */
	public String getSro_name() {
		return sro_name;
	}

	/**
	 * @param sro_name
	 *            the sro_name to set
	 */
	public void setSro_name(String sro_name) {
		this.sro_name = sro_name;
	}

	/**
	 * @return the deedCertificateOfSaleBuyerSellerDetails
	 */
	public List<DeedCertificateOfSaleBuyerSellerDetails> getDeedCertificateOfSaleBuyerSellerDetails() {
		return deedCertificateOfSaleBuyerSellerDetails;
	}

	/**
	 * @param deedCertificateOfSaleBuyerSellerDetails
	 *            the deedCertificateOfSaleBuyerSellerDetails to set
	 */
	public void setDeedCertificateOfSaleBuyerSellerDetails(
			List<DeedCertificateOfSaleBuyerSellerDetails> deedCertificateOfSaleBuyerSellerDetails) {
		this.deedCertificateOfSaleBuyerSellerDetails = deedCertificateOfSaleBuyerSellerDetails;
	}

	/**
	 * @return the deedCertificateOfSaleFloorDetails
	 */
	public List<DeedCertificateOfSaleFloorDetails> getDeedCertificateOfSaleFloorDetails() {
		return deedCertificateOfSaleFloorDetails;
	}

	/**
	 * @param deedCertificateOfSaleFloorDetails
	 *            the deedCertificateOfSaleFloorDetails to set
	 */
	public void setDeedCertificateOfSaleFloorDetails(
			List<DeedCertificateOfSaleFloorDetails> deedCertificateOfSaleFloorDetails) {
		this.deedCertificateOfSaleFloorDetails = deedCertificateOfSaleFloorDetails;
	}

	/**
	 * @return the deedCertificateOfSaleKhasraDetails
	 */
	public List<DeedCertificateOfSaleKhasraDetails> getDeedCertificateOfSaleKhasraDetails() {
		return deedCertificateOfSaleKhasraDetails;
	}

	/**
	 * @param deedCertificateOfSaleKhasraDetails
	 *            the deedCertificateOfSaleKhasraDetails to set
	 */
	public void setDeedCertificateOfSaleKhasraDetails(
			List<DeedCertificateOfSaleKhasraDetails> deedCertificateOfSaleKhasraDetails) {
		this.deedCertificateOfSaleKhasraDetails = deedCertificateOfSaleKhasraDetails;
	}

	/**
	 * @return the deedCertificateOfSalePropertyDetails
	 */
	public List<DeedCertificateOfSalePropertyDetails> getDeedCertificateOfSalePropertyDetails() {
		return deedCertificateOfSalePropertyDetails;
	}

	/**
	 * @param deedCertificateOfSalePropertyDetails
	 *            the deedCertificateOfSalePropertyDetails to set
	 */
	public void setDeedCertificateOfSalePropertyDetails(
			List<DeedCertificateOfSalePropertyDetails> deedCertificateOfSalePropertyDetails) {
		this.deedCertificateOfSalePropertyDetails = deedCertificateOfSalePropertyDetails;
	}

	/**
	 * @return the deedCertificateOfSaleStampDetails
	 */
	public List<DeedCertificateOfSaleStampDetails> getDeedCertificateOfSaleStampDetails() {
		return deedCertificateOfSaleStampDetails;
	}

	/**
	 * @param deedCertificateOfSaleStampDetails
	 *            the deedCertificateOfSaleStampDetails to set
	 */
	public void setDeedCertificateOfSaleStampDetails(
			List<DeedCertificateOfSaleStampDetails> deedCertificateOfSaleStampDetails) {
		this.deedCertificateOfSaleStampDetails = deedCertificateOfSaleStampDetails;
	}

	/**
	 * @param language
	 *            the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * @param districtList
	 *            the districtList to set
	 */
	public void setDistrictList(List<DistrictDetailsDTO> districtList) {
		this.districtList = districtList;
	}

	/**
	 * @return the districtList
	 */
	public List<DistrictDetailsDTO> getDistrictList() {
		return districtList;
	}

	/**
	 * @param sroNameList
	 *            the sroNameList to set
	 */
	public void setSroNameList(List<SRONameDetailsDTO> sroNameList) {
		this.sroNameList = sroNameList;
	}

	/**
	 * @return the sroNameList
	 */
	public List<SRONameDetailsDTO> getSroNameList() {
		return sroNameList;
	}

	/**
	 * @param errorName
	 *            the errorName to set
	 */
	public void setErrorName(String errorName) {
		this.errorName = errorName;
	}

	/**
	 * @return the errorName
	 */
	public String getErrorName() {
		return errorName;
	}

	/**
	 * @param referenceId
	 *            the referenceId to set
	 */
	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}

	/**
	 * @return the referenceId
	 */
	public String getReferenceId() {
		return referenceId;
	}

	/**
	 * @param isCompletePay
	 *            the isCompletePay to set
	 */
	public void setIsCompletePay(int isCompletePay) {
		this.isCompletePay = isCompletePay;
	}

	/**
	 * @return the isCompletePay
	 */
	public int getIsCompletePay() {
		return isCompletePay;
	}

	/**
	 * @param feeString
	 *            the feeString to set
	 */
	public void setFeeString(String feeString) {
		this.feeString = feeString;
	}

	/**
	 * @return the feeString
	 */
	public String getFeeString() {
		return feeString;
	}

	/**
	 * @param totalFee
	 *            the totalFee to set
	 */
	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}

	/**
	 * @return the totalFee
	 */
	public String getTotalFee() {
		return totalFee;
	}

	/**
	 * @param serviceFee
	 *            the serviceFee to set
	 */
	public void setServiceFee(String serviceFee) {
		this.serviceFee = serviceFee;
	}

	/**
	 * @return the serviceFee
	 */
	public String getServiceFee() {
		return serviceFee;
	}

	/**
	 * @param otherFee
	 *            the otherFee to set
	 */
	public void setOtherFee(String otherFee) {
		this.otherFee = otherFee;
	}

	/**
	 * @return the otherFee
	 */
	public String getOtherFee() {
		return otherFee;
	}

	/**
	 * @param functionId
	 *            the functionId to set
	 */
	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}

	/**
	 * @return the functionId
	 */
	public String getFunctionId() {
		return functionId;
	}

	/**
	 * @param serviceId
	 *            the serviceId to set
	 */
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	/**
	 * @return the serviceId
	 */
	public String getServiceId() {
		return serviceId;
	}

	/**
	 * @param userTypeId
	 *            the userTypeId to set
	 */
	public void setUserTypeId(String userTypeId) {
		this.userTypeId = userTypeId;
	}

	/**
	 * @return the userTypeId
	 */
	public String getUserTypeId() {
		return userTypeId;
	}

	/**
	 * @param actionName
	 *            the actionName to set
	 */
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	/**
	 * @return the actionName
	 */
	public String getActionName() {
		return actionName;
	}

	/**
	 * @param formName
	 *            the formName to set
	 */
	public void setFormName(String formName) {
		this.formName = formName;
	}

	/**
	 * @return the formName
	 */
	public String getFormName() {
		return formName;
	}

	/**
	 * @param parentOfficeName
	 *            the parentOfficeName to set
	 */
	public void setParentOfficeName(String parentOfficeName) {
		this.parentOfficeName = parentOfficeName;
	}

	/**
	 * @return the parentOfficeName
	 */
	public String getParentOfficeName() {
		return parentOfficeName;
	}

	/**
	 * @param parentDistrictId
	 *            the parentDistrictId to set
	 */
	public void setParentDistrictId(String parentDistrictId) {
		this.parentDistrictId = parentDistrictId;
	}

	/**
	 * @return the parentDistrictId
	 */
	public String getParentDistrictId() {
		return parentDistrictId;
	}

	/**
	 * @param parentDistrictName
	 *            the parentDistrictName to set
	 */
	public void setParentDistrictName(String parentDistrictName) {
		this.parentDistrictName = parentDistrictName;
	}

	/**
	 * @return the parentDistrictName
	 */
	public String getParentDistrictName() {
		return parentDistrictName;
	}

	/**
	 * @param parentOfficeId
	 *            the parentOfficeId to set
	 */
	public void setParentOfficeId(String parentOfficeId) {
		this.parentOfficeId = parentOfficeId;
	}

	/**
	 * @return the parentOfficeId
	 */
	public String getParentOfficeId() {
		return parentOfficeId;
	}

	/**
	 * @param isOld
	 *            the isOld to set
	 */
	public void setIsOld(String isOld) {
		this.isOld = isOld;
	}

	/**
	 * @return the isOld
	 */
	public String getIsOld() {
		return isOld;
	}

	public String getWardNumber() {
		return wardNumber;
	}

	public void setWardNumber(String wardNumber) {
		this.wardNumber = wardNumber;
	}

	public String getKhasraNumber() {
		return khasraNumber;
	}

	public void setKhasraNumber(String khasraNumber) {
		this.khasraNumber = khasraNumber;
	}

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public String getOrganisationName() {
		return organisationName;
	}

	public void setOrganisationName(String organisationName) {
		this.organisationName = organisationName;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	/**
	 * @return the registrationMap
	 */
	public List<OldRegistrationMap> getRegistrationMap() {
		return registrationMap;
	}

	/**
	 * @param registrationMap
	 *            the registrationMap to set
	 */
	public void setRegistrationMap(List<OldRegistrationMap> registrationMap) {
		this.registrationMap = registrationMap;
	}

	/**
	 * @return the radioButton
	 */
	public String getRadioButton() {
		return radioButton;
	}

	/**
	 * @param radioButton
	 *            the radioButton to set
	 */
	public void setRadioButton(String radioButton) {
		this.radioButton = radioButton;
	}

	/**
	 * @return the registration_number
	 */
	public String getRegistration_number() {
		return registration_number;
	}

	/**
	 * @param registration_number
	 *            the registration_number to set
	 */
	public void setRegistration_number(String registration_number) {
		this.registration_number = registration_number;
	}

	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * @param reason
	 *            the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}

	/**
	 * @return the file
	 */
	public FormFile getFile() {
		return file;
	}

	/**
	 * @param file
	 *            the file to set
	 */
	public void setFile(FormFile file) {
		this.file = file;
	}

	/**
	 * @return the content
	 */
	public byte[] getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(byte[] content) {
		this.content = content;
	}

	/**
	 * @return the uploadDocumentStack
	 */
	public String getUploadDocumentStack() {
		return uploadDocumentStack;
	}

	/**
	 * @param uploadDocumentStack
	 *            the uploadDocumentStack to set
	 */
	public void setUploadDocumentStack(String uploadDocumentStack) {
		this.uploadDocumentStack = uploadDocumentStack;
	}

	/**
	 * @return the uploadFile
	 */
	public String getUploadFile() {
		return uploadFile;
	}

	/**
	 * @param uploadFile
	 *            the uploadFile to set
	 */
	public void setUploadFile(String uploadFile) {
		this.uploadFile = uploadFile;
	}

	/**
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * @param filePath
	 *            the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

}
