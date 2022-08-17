/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	UserProfileDTO.java
 * Author		:	Rafiq Rahiman.T 
 * Date			:   14/03/2008
 */
package com.wipro.igrs.useracctmgmt.dto;


import java.util.ArrayList;

import org.apache.struts.upload.FormFile;

public class UserProfileDTO{
	
	private String Userdetails;
	private String userId="";
	private String name="";
	private String value="";
	private String firstName="";
	private String middleName="";
	private String lastName="";
	private String gender="";
	private String occupation="";
	private String motherName="";
	private String fatherName="";
	private String spouseName="";
	private String test;
	//private String address;
	//private String country;
	private String state="";
	private String city="";
	private String postalCode="";
	private String phoneNumber="";
	private String mobileNumber="";
	private String primaryEmail="";
	private String alternateEmail="";
	private String photoProof="";
	private String idNo="";
	private String bankName="";
	private String bankAddr="";
	private String regNo="";
	private String day="";
	private String month="";
	private String year="";
	private String dob="";
	private String hintQuestion="";
	private String hintAnswer="";
	private String termsCond="";
	private String address="";
	private String country="";
	private String stateName="";
	private String districtName="";
	private String userCityID="";
	private String userCountryID="";
	private String userStateID="";
	
	private String oldpassword;
	private String newpassword;                 //----added by satbir kumar for change password activity-----
	private String confirmpassword;
	
	private String notmatch;
	private String type;
	
//--added for rights activity------
	private String splicensestatus;
	private String sptype;
	private String option;
	private String spuserid;
	private String licenseno;
	private String spbankname;
	private String authPersName;
	private String designation;
	private String education;
	private String langKnown;
	private String addressSp;
	private String district;
	private String tehsil;
	private String ward;
	private String mohalla;
	private String radioSelectExp;
	private String radioSelectCh;
	private String amount;
	
	private String isSpBank;
	private String isSpInd;
	private String isSpAllType;
	
	
	private String hiderights;
	
	private String spuploadlicense;
	
	private FormFile spupload=null;
	
	private String documentName;
	private String splicensefrom;
	private String splicenseto;
	private byte[] docContents;
	private String sprepeatrights;
	
	private String search;
	
	//Added By Praveen for Tehsildar Rights: Start
	private String age;
	private String category;
	private String minority;
	private String fromDate;
	private String toDate;
	private String rightsFromDate;
	private String rightsToDate;
	private ArrayList tehsilDetails =new ArrayList();//Added by Praveen
	private ArrayList tehsilNameList;
	private String tehsilName;
	private String DocumentSize;
	private String tehsilId;
	private String FilePath;
	
	
	//End
private String UploadDocument;
	

	
	public String getUploadDocument() {
	return UploadDocument;
}

public void setUploadDocument(String uploadDocument) {
	UploadDocument = uploadDocument;
}

	public String getFilePath() {
		return FilePath;
	}

	public void setFilePath(String filePath) {
		FilePath = filePath;
	}

	public String getTehsilId() {
		return tehsilId;
	}

	public void setTehsilId(String tehsilId) {
		this.tehsilId = tehsilId;
	}

	public String getDocumentSize() {
		return DocumentSize;
	}

	public void setDocumentSize(String documentSize) {
		DocumentSize = documentSize;
	}

	private String pouserid;
	private String fileSize;
	
	private String hidefield;
	
	
	private String pofirstname;
	private String polastname;
	private String podob;
	private String poOccupation;
	private String poAddress;
	private String poDistrict;
	private String poMobilenumber;
	private String pofromdate;
	private String potodate;
	private String poemailid;
	private String pophoneno;
	private String porightstodate;
	private String porightsfromdate;
	private String podurationoption;
		
	
	private String poupdate;
	
	private ArrayList userCountryList = new ArrayList();
	private ArrayList userStateList = new ArrayList();
	private ArrayList userCityList = new ArrayList();
	private ArrayList userHintQuestion = new ArrayList();
	
	private ArrayList countryList=  new ArrayList();
	private ArrayList stateList=  new ArrayList();
	private ArrayList districtList=  new ArrayList();
	private ArrayList photoproofList= new ArrayList();
	private ArrayList hintList=  new ArrayList();
	

	

	private String uniqueId;
	private String signPath;
	private String Signature;
    private String forwardName;
    private String forwardPath;
    private String saveUrl;
    private String checkUrl;
	private String onLoadChk;
	
	
	public ArrayList getTehsilNameList() {
		return tehsilNameList;
	}

	public void setTehsilNameList(ArrayList tehsilNameList) {
		this.tehsilNameList = tehsilNameList;
	}

	public String getTehsilName() {
		return tehsilName;
	}

	public void setTehsilName(String tehsilName) {
		this.tehsilName = tehsilName;
	}
	
	
	public String getStateName() {
		return stateName;
	}
	
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	
	
	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	
	public String getRightsFromDate() {
		return rightsFromDate;
	}

	public void setRightsFromDate(String rightsFromDate) {
		this.rightsFromDate = rightsFromDate;
	}

	public String getRightsToDate() {
		return rightsToDate;
	}

	public void setRightsToDate(String rightsToDate) {
		this.rightsToDate = rightsToDate;
	}
	public String getCategory() {
		return category;
	}
	public String getMinority() {
		return minority;
	}

	public void setMinority(String minority) {
		this.minority = minority;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getUserCityID() {
		return userCityID;
	}
	public void setUserCityID(String userCityID) {
		this.userCityID = userCityID;
	}
	public String getUserCountryID() {
		return userCountryID;
	}
	public void setUserCountryID(String userCountryID) {
		this.userCountryID = userCountryID;
	}
	public String getUserStateID() {
		return userStateID;
	}
	public void setUserStateID(String userStateID) {
		this.userStateID = userStateID;
	}
	public ArrayList getUserCountryList() {
		return userCountryList;
	}
	public void setUserCountryList(ArrayList userCountryList) {
		this.userCountryList = userCountryList;
	}
	public ArrayList getUserStateList() {
		return userStateList;
	}
	public void setUserStateList(ArrayList userStateList) {
		this.userStateList = userStateList;
	}
	public ArrayList getUserCityList() {
		return userCityList;
	}
	public void setUserCityList(ArrayList userCityList) {
		this.userCityList = userCityList;
	}
	public ArrayList getUserHintQuestion() {
		return userHintQuestion;
	}
	public void setUserHintQuestion(ArrayList userHintQuestion) {
		this.userHintQuestion = userHintQuestion;
	}
	
	
	
	public String getBankName()
	{
		return bankName;
	}
	public void setBankName(String bankName)
	{
		this.bankName = bankName;
	}
	public String getBankAddr()
	{
		return bankAddr;
	}
	public void setBankAddr(String bankAddr)
	{
		this.bankAddr = bankAddr;
	}
	public ArrayList getCountryList() {
		return countryList;
	}
	public void setCountryList(ArrayList countryList) {
		this.countryList = countryList;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getMotherName() {
		return motherName;
	}
	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public String getSpouseName() {
		return spouseName;
	}
	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getPrimaryEmail() {
		return primaryEmail;
	}
	public void setPrimaryEmail(String primaryEmail) {
		this.primaryEmail = primaryEmail;
	}
	public String getAlternateEmail() {
		return alternateEmail;
	}
	public void setAlternateEmail(String alternateEmail) {
		this.alternateEmail = alternateEmail;
	}
	public String getPhotoProof() {
		return photoProof;
	}
	public void setPhotoProof(String photoProof) {
		this.photoProof = photoProof;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public String getRegNo() {
		return regNo;
	}
	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public ArrayList getStateList() {
		return stateList;
	}
	public void setStateList(ArrayList stateList) {
		this.stateList = stateList;
	}
	public ArrayList getDistrictList() {
		return districtList;
	}
	public void setDistrictList(ArrayList districtList) {
		this.districtList = districtList;
	}
	public ArrayList getPhotoproofList() {
		return photoproofList;
	}
	public void setPhotoproofList(ArrayList photoproofList) {
		this.photoproofList = photoproofList;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserdetails()
	{
		return Userdetails;
	}
	public void setUserdetails(String userdetails)
	{
		Userdetails = userdetails;
	}
	public String getDob()
	{
		return dob;
	}
	public void setDob(String dob)
	{
		this.dob = dob;
	}
	public String getTermsCond()
	{
		return termsCond;
	}
	public void setTermsCond(String termsCond)
	{
		this.termsCond = termsCond;
	}
	public String getHintQuestion()
	{
		return hintQuestion;
	}
	public void setHintQuestion(String hintQuestion)
	{
		this.hintQuestion = hintQuestion;
	}
	public ArrayList getHintList()
	{
		return hintList;
	}
	public void setHintList(ArrayList hintList)
	{
		this.hintList = hintList;
	}
	public String getHintAnswer()
	{
		return hintAnswer;
	}
	public void setHintAnswer(String hintAnswer)
	{
		this.hintAnswer = hintAnswer;
	}
	public String getOldpassword() {
		return oldpassword;
	}
	public void setOldpassword(String oldpassword) {
		this.oldpassword = oldpassword;
	}
	public String getNewpassword() {
		return newpassword;
	}
	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}
	public String getConfirmpassword() {
		return confirmpassword;
	}
	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}
	public String getNotmatch() {
		return notmatch;
	}
	public void setNotmatch(String notmatch) {
		this.notmatch = notmatch;
	}
	public String getOption() {
		return option;
	}
	public void setOption(String option) {
		this.option = option;
	}
	
	public String getLicenseno() {
		return licenseno;
	}
	public void setLicenseno(String licenseno) {
		this.licenseno = licenseno;
	}

	public String getAuthPersName() {
		return authPersName;
	}
	public void setAuthPersName(String authPersName) {
		this.authPersName = authPersName;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getLangKnown() {
		return langKnown;
	}
	public void setLangKnown(String langKnown) {
		this.langKnown = langKnown;
	}
	public String getAddressSp() {
		return addressSp;
	}
	public void setAddressSp(String addressSp) {
		this.addressSp = addressSp;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getTehsil() {
		return tehsil;
	}
	public void setTehsil(String tehsil) {
		this.tehsil = tehsil;
	}
	public String getWard() {
		return ward;
	}
	public void setWard(String ward) {
		this.ward = ward;
	}
	public String getMohalla() {
		return mohalla;
	}
	public void setMohalla(String mohalla) {
		this.mohalla = mohalla;
	}
	public String getRadioSelectExp() {
		return radioSelectExp;
	}
	public void setRadioSelectExp(String radioSelectExp) {
		this.radioSelectExp = radioSelectExp;
	}
	public String getRadioSelectCh() {
		return radioSelectCh;
	}
	public void setRadioSelectCh(String radioSelectCh) {
		this.radioSelectCh = radioSelectCh;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getIsSpBank() {
		return isSpBank;
	}
	public void setIsSpBank(String isSpBank) {
		this.isSpBank = isSpBank;
	}
	public String getIsSpInd() {
		return isSpInd;
	}
	public void setIsSpInd(String isSpInd) {
		this.isSpInd = isSpInd;
	}
	public String getIsSpAllType() {
		return isSpAllType;
	}
	public void setIsSpAllType(String isSpAllType) {
		this.isSpAllType = isSpAllType;
	}
	public String getSpuserid() {
		return spuserid;
	}
	public void setSpuserid(String spuserid) {
		this.spuserid = spuserid;
	}
	public String getSpbankname() {
		return spbankname;
	}
	public void setSpbankname(String spbankname) {
		this.spbankname = spbankname;
	}
	public String getSptype() {
		return sptype;
	}
	public void setSptype(String sptype) {
		this.sptype = sptype;
	}
	public String getSplicensestatus() {
		return splicensestatus;
	}
	public void setSplicensestatus(String splicensestatus) {
		this.splicensestatus = splicensestatus;
	}
	public String getHiderights() {
		return hiderights;
	}
	public void setHiderights(String hiderights) {
		this.hiderights = hiderights;
	}
	public String getSpuploadlicense() {
		return spuploadlicense;
	}
	public void setSpuploadlicense(String spuploadlicense) {
		this.spuploadlicense = spuploadlicense;
	}
	public String getSplicensefrom() {
		return splicensefrom;
	}
	public void setSplicensefrom(String splicensefrom) {
		this.splicensefrom = splicensefrom;
	}
	public String getSplicenseto() {
		return splicenseto;
	}
	public void setSplicenseto(String splicenseto) {
		this.splicenseto = splicenseto;
	}
	public String getSprepeatrights() {
		return sprepeatrights;
	}
	public void setSprepeatrights(String sprepeatrights) {
		this.sprepeatrights = sprepeatrights;
	}
	public FormFile getSpupload() {
		return spupload;
	}
	public void setSpupload(FormFile spupload) {
		this.spupload = spupload;
	}
	public String getDocumentName() {
		return documentName;
	}
	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}
	public byte[] getDocContents() {
		return docContents;
	}
	public void setDocContents(byte[] docContents) {
		this.docContents = docContents;
	}
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	public String getHidefield() {
		return hidefield;
	}
	public void setHidefield(String hidefield) {
		this.hidefield = hidefield;
	}
	public String getPouserid() {
		return pouserid;
	}
	public void setPouserid(String pouserid) {
		this.pouserid = pouserid;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public String getPofirstname() {
		return pofirstname;
	}
	public void setPofirstname(String pofirstname) {
		this.pofirstname = pofirstname;
	}
	public String getPolastname() {
		return polastname;
	}
	public void setPolastname(String polastname) {
		this.polastname = polastname;
	}
	public String getPodob() {
		return podob;
	}
	public void setPodob(String podob) {
		this.podob = podob;
	}
	public String getPoOccupation() {
		return poOccupation;
	}
	public void setPoOccupation(String poOccupation) {
		this.poOccupation = poOccupation;
	}
	public String getPoAddress() {
		return poAddress;
	}
	public void setPoAddress(String poAddress) {
		this.poAddress = poAddress;
	}
	public String getPoDistrict() {
		return poDistrict;
	}
	public void setPoDistrict(String poDistrict) {
		this.poDistrict = poDistrict;
	}
	public String getPoMobilenumber() {
		return poMobilenumber;
	}
	public void setPoMobilenumber(String poMobilenumber) {
		this.poMobilenumber = poMobilenumber;
	}
	public String getPofromdate() {
		return pofromdate;
	}
	public void setPofromdate(String pofromdate) {
		this.pofromdate = pofromdate;
	}
	public String getPotodate() {
		return potodate;
	}
	public void setPotodate(String potodate) {
		this.potodate = potodate;
	}
	public String getPoemailid() {
		return poemailid;
	}
	public void setPoemailid(String poemailid) {
		this.poemailid = poemailid;
	}
	public String getPophoneno() {
		return pophoneno;
	}
	public void setPophoneno(String pophoneno) {
		this.pophoneno = pophoneno;
	}
	public String getPorightstodate() {
		return porightstodate;
	}
	public void setPorightstodate(String porightstodate) {
		this.porightstodate = porightstodate;
	}
	public String getPorightsfromdate() {
		return porightsfromdate;
	}
	public void setPorightsfromdate(String porightsfromdate) {
		this.porightsfromdate = porightsfromdate;
	}
	public String getPodurationoption() {
		return podurationoption;
	}
	public void setPodurationoption(String podurationoption) {
		this.podurationoption = podurationoption;
	}
	public String getPoupdate() {
		return poupdate;
	}
	public void setPoupdate(String poupdate) {
		this.poupdate = poupdate;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getType() {
		return type;
	}
	public void setTest(String test) {
		this.test = test;
	}
	public String getTest() {
		return test;
	}
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	public String getUniqueId() {
		return uniqueId;
	}
	public void setSignPath(String signPath) {
		this.signPath = signPath;
	}
	public String getSignPath() {
		return signPath;
	}
	public void setSignature(String signature) {
		Signature = signature;
	}
	public String getSignature() {
		return Signature;
	}
	public void setForwardName(String forwardName) {
		this.forwardName = forwardName;
	}
	public String getForwardName() {
		return forwardName;
	}
	public void setForwardPath(String forwardPath) {
		this.forwardPath = forwardPath;
	}
	public String getForwardPath() {
		return forwardPath;
	}
	/*public void setMainCheck(String mainCheck) {
		this.mainCheck = mainCheck;
	}
	public String getMainCheck() {
		return mainCheck;
	}*/
	public void setSaveUrl(String saveUrl) {
		this.saveUrl = saveUrl;
	}
	public String getSaveUrl() {
		return saveUrl;
	}
	public void setCheckUrl(String checkUrl) {
		this.checkUrl = checkUrl;
	}
	public String getCheckUrl() {
		return checkUrl;
	}
	public void setOnLoadChk(String onLoadChk) {
		this.onLoadChk = onLoadChk;
	}
	public String getOnLoadChk() {
		return onLoadChk;
	}
	public ArrayList getTehsilDetails() {
		return tehsilDetails;
	}

	public void setTehsilDetails(ArrayList tehsilDetails) {
		this.tehsilDetails = tehsilDetails;
	}
}
