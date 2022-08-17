/**
 * ServiceProviderDTO
 */


package com.wipro.igrs.sp.dto;


import java.io.Serializable;

import org.apache.struts.upload.FormFile;


/**
 * @author root
 * 
 */
public class ServiceProviderDTO implements Serializable {
	
	private String spusertypename;
	private String spusertypeid;
	private String hdnspusertypename;
	private String selectspusertype;
	private String spusername;
	private String licencett;
	private String spuserid;
	private String spFirstName;
	private String spMiddleName;
	private String spLastName;
	private String spGender;
	private String spOccupation;
	private String spFatherName;
	private String spMotherName;
	private String spSpouseName;
	private String spCountry;
	private String spState;
	private String spDistrict;
	private String spPhoneNumber;
	private String spMobileNumber;
	private String spPrimaryEmail;
	private String spAlternateEmail;
	private String spPhotoIdProof;
	private String spIdNo;                                         
	private String spaddress;
	private String spdistrctid;
	private String spdistrictname;
	private String sptehsilid;
	private String sptehsilname;
	private String splicencenumber;
	private String splfromdate;
	private String spltodate;
	private String drocomments;
	private String spbankid;
	private String spbankname;
	private String spbankaddress;
	private String spbankphonenumber;
	private String spDOB;
	private String spVolumeNumber;
	private String spbookNumber;
	private String spRegistrationNumber;
	private String fileName;
	private int fileId;
	private int fileSize;
    private String documenttype;
    private String documentname;
    private String spDurationFrom;
    private String spDurationTo;
    private String spDateOfInsurance;
    private String spOtherInformation;
    private String sppostalcode;
    private String spRefrenceId;
    private String spPermAddress;
    private String spLicenceTxnId;
    private String spUserlicenceNo;
    private String spOfficeDistrict;
    private String spOfficeAddress;
    private String spPhotoIdNumber;
    private String spPhotoIdName;
    private String bankAuthPerson;
	private String bankPersonDesignation;
    private String bankContactInfo;
    private String renewFromDate;
    private String actionName;
    private FormFile uploadLicense;
    
    public FormFile getUploadLicense() {
		return uploadLicense;
	}

	public void setUploadLicense(FormFile uploadLicense) {
		this.uploadLicense = uploadLicense;
	}

	public String getRenewFromDate() {
		return renewFromDate;
	}

	public void setRenewFromDate(String renewFromDate) {
		this.renewFromDate = renewFromDate;
	}

	public String getBankAuthPerson() {
		return bankAuthPerson;
	}

	public void setBankAuthPerson(String bankAuthPerson) {
		this.bankAuthPerson = bankAuthPerson;
	}

	public String getBankPersonDesignation() {
		return bankPersonDesignation;
	}

	public void setBankPersonDesignation(String bankPersonDesignation) {
		this.bankPersonDesignation = bankPersonDesignation;
	}

	public String getBankContactInfo() {
		return bankContactInfo;
	}

	public void setBankContactInfo(String bankContactInfo) {
		this.bankContactInfo = bankContactInfo;
	}

    public String getSpPhotoIdName() {
		return spPhotoIdName;
	}

	public void setSpPhotoIdName(String spPhotoIdName) {
		this.spPhotoIdName = spPhotoIdName;
	}

    
    
	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getSpusertypename() {
		return spusertypename;
	}

	public void setSpusertypename(String spusertypename) {
		this.spusertypename = spusertypename;
	}

	public String getSpusertypeid() {
		return spusertypeid;
	}

	public void setSpusertypeid(String spusertypeid) {
		this.spusertypeid = spusertypeid;
	}

	/**
	 * @return the spOfficeAddress
	 */
	public String getSpOfficeAddress() {
		return spOfficeAddress;
	}

	/**
	 * @param spOfficeAddress the spOfficeAddress to set
	 */
	public void setSpOfficeAddress(String spOfficeAddress) {
		this.spOfficeAddress = spOfficeAddress;
	}

	/**
	 * @return the spUserlicenceNo
	 */
	public String getSpUserlicenceNo() {
		return spUserlicenceNo;
	}

	/**
	 * @param spUserlicenceNo the spUserlicenceNo to set
	 */
	public void setSpUserlicenceNo(String spUserlicenceNo) {
		this.spUserlicenceNo = spUserlicenceNo;
	}

	/**
	 * @return the spLicenceTxnId
	 */
	public String getSpLicenceTxnId() {
		return spLicenceTxnId;
	}

	/**
	 * @param spLicenceTxnId the spLicenceTxnId to set
	 */
	public void setSpLicenceTxnId(String spLicenceTxnId) {
		this.spLicenceTxnId = spLicenceTxnId;
	}

	/**
	 * @return the spPermAddress
	 */
	public String getSpPermAddress() {
		return spPermAddress;
	}

	/**
	 * @param spPermAddress the spPermAddress to set
	 */
	public void setSpPermAddress(String spPermAddress) {
		this.spPermAddress = spPermAddress;
	}

	/**
	 * @return the spRefrenceId
	 */
	public String getSpRefrenceId() {
		return spRefrenceId;
	}

	/**
	 * @param spRefrenceId the spRefrenceId to set
	 */
	public void setSpRefrenceId(String spRefrenceId) {
		this.spRefrenceId = spRefrenceId;
	}

	/**
	 * @return the spOtherInformation
	 */
	public String getSpOtherInformation() {
		return spOtherInformation;
	}

	/**
	 * @param spOtherInformation the spOtherInformation to set
	 */
	public void setSpOtherInformation(String spOtherInformation) {
		this.spOtherInformation = spOtherInformation;
	}

	/**
	 * @return the spDateOfInsurance
	 */
	public String getSpDateOfInsurance() {
		return spDateOfInsurance;
	}

	/**
	 * @param spDateOfInsurance the spDateOfInsurance to set
	 */
	public void setSpDateOfInsurance(String spDateOfInsurance) {
		this.spDateOfInsurance = spDateOfInsurance;
	}

	/**
	 * @return the spDurationTo
	 */
	public String getSpDurationTo() {
		return spDurationTo;
	}

	/**
	 * @param spDurationTo the spDurationTo to set
	 */
	public void setSpDurationTo(String spDurationTo) {
		this.spDurationTo = spDurationTo;
	}

	/**
	 * @return the spDurationFrom
	 */
	public String getSpDurationFrom() {
		return spDurationFrom;
	}

	/**
	 * @param spDurationFrom the spDurationFrom to set
	 */
	public void setSpDurationFrom(String spDurationFrom) {
		this.spDurationFrom = spDurationFrom;
	}

	/**
	 * @return the spDOB
	 */
	public String getSpDOB() {
		return spDOB;
	}

	/**
	 * @param spDOB the spDOB to set
	 */
	public void setSpDOB(String spDOB) {
		
		
		
		this.spDOB = spDOB.substring(0, 10);
		
	}

	/**
	 * @return the drocomments
	 */
	public String getDrocomments() {
		return drocomments;
	}

	/**
	 * @param drocomments
	 *            the drocomments to set
	 */
	public void setDrocomments(String drocomments) {
		this.drocomments = drocomments;
	}

	/**
	 * @return the splicencenumber
	 */
	public String getSplicencenumber() {
		return splicencenumber;
	}

	/**
	 * @param splicencenumber
	 *            the splicencenumber to set
	 */
	public void setSplicencenumber(String splicencenumber) {
		this.splicencenumber = splicencenumber;
	}

	/**
	 * @return the spuserid
	 */
	

	/**
	 * @return the spusername
	 */
	public String getSpusername() {
		return spusername;
	}

	/**
	 * @param spusername
	 *            the spusername to set
	 */
	public void setSpusername(String spusername) {
		this.spusername = spusername;
	}

	/**
	 * @return the spaddress
	 */
	public String getSpaddress() {
		return spaddress;
	}

	/**
	 * @param spaddress
	 *            the spaddress to set
	 */
	public void setSpaddress(String spaddress) {
		this.spaddress = spaddress;
	}

	/**
	 * @return the sppostalcode
	 */
	public String getSppostalcode() {
		return sppostalcode;
	}

	/**
	 * @param sppostalcode
	 *            the sppostalcode to set
	 */
	public void setSppostalcode(String sppostalcode) {
		this.sppostalcode = sppostalcode;
	}

	/**
	 * @return the spdistrctid
	 */
	public String getSpdistrctid() {
		return spdistrctid;
	}

	/**
	 * @param spdistrctid
	 *            the spdistrctid to set
	 */
	public void setSpdistrctid(String spdistrctid) {
		this.spdistrctid = spdistrctid;
	}

	/**
	 * @return the spdistrictname
	 */
	public String getSpdistrictname() {
		return spdistrictname;
	}

	/**
	 * @param spdistrictname
	 *            the spdistrictname to set
	 */
	public void setSpdistrictname(String spdistrictname) {
		this.spdistrictname = spdistrictname;
	}

	/**
	 * @return the sptehsilid
	 */
	public String getSptehsilid() {
		return sptehsilid;
	}

	/**
	 * @param sptehsilid
	 *            the sptehsilid to set
	 */
	public void setSptehsilid(String sptehsilid) {
		this.sptehsilid = sptehsilid;
	}

	/**
	 * @return the sptehsilname
	 */
	public String getSptehsilname() {
		return sptehsilname;
	}

	/**
	 * @param sptehsilname
	 *            the sptehsilname to set
	 */
	public void setSptehsilname(String sptehsilname) {
		this.sptehsilname = sptehsilname;
	}

	/**
	 * @return the splfromdate
	 */
	public String getSplfromdate() {
		return splfromdate;
	}

	/**
	 * @param splfromdate
	 *            the splfromdate to set
	 */
	public void setSplfromdate(String splfromdate) {
		this.splfromdate = splfromdate;
	}

	/**
	 * @return the spltodate
	 */
	public String getSpltodate() {
		return spltodate;
	}

	/**
	 * @param spltodate
	 *            the spltodate to set
	 */
	public void setSpltodate(String spltodate) {
		this.spltodate = spltodate;
	}

	/**
	 * @return the spbankid
	 */
	public String getSpbankid() {
		return spbankid;
	}

	/**
	 * @param spbankid
	 *            the spbankid to set
	 */
	public void setSpbankid(String spbankid) {
		this.spbankid = spbankid;
	}

	/**
	 * @return the spbankname
	 */
	public String getSpbankname() {
		return spbankname;
	}

	/**
	 * @param spbankname
	 *            the spbankname to set
	 */
	public void setSpbankname(String spbankname) {
		this.spbankname = spbankname;
	}

	/**
	 * @return the spbankaddress
	 */
	public String getSpbankaddress() {
		return spbankaddress;
	}

	/**
	 * @param spbankaddress
	 *            the spbankaddress to set
	 */
	public void setSpbankaddress(String spbankaddress) {
		this.spbankaddress = spbankaddress;
	}

	/**
	 * @return the spbankphonenumber
	 */
	public String getSpbankphonenumber() {
		return spbankphonenumber;
	}

	/**
	 * @param spbankphonenumber
	 *            the spbankphonenumber to set
	 */
	public void setSpbankphonenumber(String spbankphonenumber) {
		this.spbankphonenumber = spbankphonenumber;
	}

	/**
	 * @return the spFirstName
	 */
	public String getSpFirstName() {
		return spFirstName;
	}

	/**
	 * @param spFirstName the spFirstName to set
	 */
	public void setSpFirstName(String spFirstName) {
		this.spFirstName = spFirstName;
	}

	/**
	 * @return the spMiddleName
	 */
	public String getSpMiddleName() {
		return spMiddleName;
	}

	/**
	 * @param spMiddleName the spMiddleName to set
	 */
	public void setSpMiddleName(String spMiddleName) {
		this.spMiddleName = spMiddleName;
	}

	/**
	 * @return the spLastName
	 */
	public String getSpLastName() {
		return spLastName;
	}

	/**
	 * @param spLastName the spLastName to set
	 */
	public void setSpLastName(String spLastName) {
		this.spLastName = spLastName;
	}

	/**
	 * @return the spGender
	 */
	public String getSpGender() {
		return spGender;
	}

	/**
	 * @param spGender the spGender to set
	 */
	public void setSpGender(String spGender) {
		this.spGender = spGender;
	}

	/**
	 * @return the spOccupation
	 */
	public String getSpOccupation() {
		return spOccupation;
	}

	/**
	 * @param spOccupation the spOccupation to set
	 */
	public void setSpOccupation(String spOccupation) {
		this.spOccupation = spOccupation;
	}

	/**
	 * @return the spFatherName
	 */
	public String getSpFatherName() {
		return spFatherName;
	}

	/**
	 * @param spFatherName the spFatherName to set
	 */
	public void setSpFatherName(String spFatherName) {
		this.spFatherName = spFatherName;
	}

	/**
	 * @return the spMotherName
	 */
	public String getSpMotherName() {
		return spMotherName;
	}

	/**
	 * @param spMotherName the spMotherName to set
	 */
	public void setSpMotherName(String spMotherName) {
		this.spMotherName = spMotherName;
	}

	/**
	 * @return the spSpouseName
	 */
	public String getSpSpouseName() {
		return spSpouseName;
	}

	/**
	 * @param spSpouseName the spSpouseName to set
	 */
	public void setSpSpouseName(String spSpouseName) {
		this.spSpouseName = spSpouseName;
	}

	

	/**
	 * @return the spCountry
	 */
	public String getSpCountry() {
		return spCountry;
	}

	/**
	 * @param spCountry the spCountry to set
	 */
	public void setSpCountry(String spCountry) {
		this.spCountry = spCountry;
	}

	/**
	 * @return the spState
	 */
	public String getSpState() {
		return spState;
	}

	/**
	 * @param spState the spState to set
	 */
	public void setSpState(String spState) {
		this.spState = spState;
	}

	/**
	 * @return the spDistrict
	 */
	public String getSpDistrict() {
		return spDistrict;
	}

	/**
	 * @param spDistrict the spDistrict to set
	 */
	public void setSpDistrict(String spDistrict) {
		this.spDistrict = spDistrict;
	}



	/**
	 * @return the spPhoneNumber
	 */
	public String getSpPhoneNumber() {
		return spPhoneNumber;
	}

	/**
	 * @param spPhoneNumber the spPhoneNumber to set
	 */
	public void setSpPhoneNumber(String spPhoneNumber) {
		this.spPhoneNumber = spPhoneNumber;
	}

	/**
	 * @return the spMobileNumber
	 */
	public String getSpMobileNumber() {
		return spMobileNumber;
	}

	/**
	 * @param spMobileNumber the spMobileNumber to set
	 */
	public void setSpMobileNumber(String spMobileNumber) {
		this.spMobileNumber = spMobileNumber;
	}

	/**
	 * @return the spPrimaryEmail
	 */
	public String getSpPrimaryEmail() {
		return spPrimaryEmail;
	}

	/**
	 * @param spPrimaryEmail the spPrimaryEmail to set
	 */
	public void setSpPrimaryEmail(String spPrimaryEmail) {
		this.spPrimaryEmail = spPrimaryEmail;
	}

	/**
	 * @return the spAlternateEmail
	 */
	public String getSpAlternateEmail() {
		return spAlternateEmail;
	}

	/**
	 * @param spAlternateEmail the spAlternateEmail to set
	 */
	public void setSpAlternateEmail(String spAlternateEmail) {
		this.spAlternateEmail = spAlternateEmail;
	}

	/**
	 * @return the spPhotoIdProof
	 */
	public String getSpPhotoIdProof() {
		return spPhotoIdProof;
	}

	/**
	 * @param spPhotoIdProof the spPhotoIdProof to set
	 */
	public void setSpPhotoIdProof(String spPhotoIdProof) {
		this.spPhotoIdProof = spPhotoIdProof;
	}

	/**
	 * @return the spIdNo
	 */
	public String getSpIdNo() {
		return spIdNo;
	}

	/**
	 * @param spIdNo the spIdNo to set
	 */
	public void setSpIdNo(String spIdNo) {
		this.spIdNo = spIdNo;
	}

	/**
	 * @return the spVolumeNumber
	 */
	public String getSpVolumeNumber() {
		return spVolumeNumber;
	}

	/**
	 * @param spVolumeNumber the spVolumeNumber to set
	 */
	public void setSpVolumeNumber(String spVolumeNumber) {
		this.spVolumeNumber = spVolumeNumber;
	}

	/**
	 * @return the spbookNumber
	 */
	public String getSpbookNumber() {
		return spbookNumber;
	}

	/**
	 * @param spbookNumber the spbookNumber to set
	 */
	public void setSpbookNumber(String spbookNumber) {
		this.spbookNumber = spbookNumber;
	}

	/**
	 * @return the spRegistrationNumber
	 */
	public String getSpRegistrationNumber() {
		return spRegistrationNumber;
	}

	/**
	 * @param spRegistrationNumber the spRegistrationNumber to set
	 */
	public void setSpRegistrationNumber(String spRegistrationNumber) {
		this.spRegistrationNumber = spRegistrationNumber;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the fileId
	 */
	public int getFileId() {
		return fileId;
	}

	/**
	 * @param fileId the fileId to set
	 */
	public void setFileId(int fileId) {
		this.fileId = fileId;
	}

	/**
	 * @return the fileSize
	 */
	public int getFileSize() {
		return fileSize;
	}

	/**
	 * @param fileSize the fileSize to set
	 */
	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}

	/**
	 * @return the documenttype
	 */
	public String getDocumenttype() {
		return documenttype;
	}

	/**
	 * @param documenttype the documenttype to set
	 */
	public void setDocumenttype(String documenttype) {
		this.documenttype = documenttype;
	}

	/**
	 * @return the documentname
	 */
	public String getDocumentname() {
		return documentname;
	}

	/**
	 * @param documentname the documentname to set
	 */
	public void setDocumentname(String documentname) {
		this.documentname = documentname;
	}

	/**
	 * @return the spOfficeDistrict
	 */
	public String getSpOfficeDistrict() {
		return spOfficeDistrict;
	}

	/**
	 * @param spOfficeDistrict the spOfficeDistrict to set
	 */
	public void setSpOfficeDistrict(String spOfficeDistrict) {
		this.spOfficeDistrict = spOfficeDistrict;
	}

	/**
	 * @return the spPhotoIdNumber
	 */
	public String getSpPhotoIdNumber() {
		return spPhotoIdNumber;
	}

	/**
	 * @param spPhotoIdNumber the spPhotoIdNumber to set
	 */
	public void setSpPhotoIdNumber(String spPhotoIdNumber) {
		this.spPhotoIdNumber = spPhotoIdNumber;
	}

	/**
	 * @return the dateOfBirth
	 */
	//modified
	private String districts;


	public String getDistricts() {
		return districts;
	}

	public void setDistricts(String districts) {
		this.districts = districts;
	}
	
	private String tehsils;


	public String getTehsils() {
		return tehsils;
	}

	public void setTehsils(String tehsils) {
		this.tehsils = tehsils;
	}
	

	private String spbanks;


	public String getSpbanks() {
		return spbanks;
	}

	public void setSpbanks(String spbanks) {
		this.spbanks = spbanks;
	}

	public String getHdnspusertypename() {
		return hdnspusertypename;
	}

	public void setHdnspusertypename(String hdnspusertypename) {
		this.hdnspusertypename = hdnspusertypename;
	}

	public String getSpuserid() {
		return spuserid;
	}

	public void setSpuserid(String spuserid) {
		this.spuserid = spuserid;
	}
	public String getLicencett() {
		return licencett;
	}

	public void setLicencett(String licencett) {
		this.licencett = licencett;
	}

	public String getSelectspusertype() {
		return selectspusertype;
	}

	public void setSelectspusertype(String selectspusertype) {
		this.selectspusertype = selectspusertype;
	}
}

