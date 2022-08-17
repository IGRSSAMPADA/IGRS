package com.wipro.igrs.rti.dto;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

public class RTIRequestDTO {
	private String countryId;
	private String country;
	private String stateName;
	private ArrayList CountryList;
	private ArrayList StateList;
	
	private ArrayList districtList = new ArrayList();
	private String rtiRequest_Form;
	
	private String userId;
	private String paymentTxnId;
	// intimation fee
	private String totalAppealFee;
	private String totalFixedFee;
	private String totalInformationFee;
	private String fee;
	private String others;
	private String numberOfPages;
	private String numberOfHour;
	private String emailBaseValue;
	private String emailcentralSalesTax;
	private String emaileducationcess;
	private String emailvaladdtax;
	private String emailfeetotal;

	private String smsBaseValue;
	private String smscentralSalesTax;
	private String smseducationcess;
	private String smsvaladdtax;
	private String smsfeetotal;

	private String postBaseValue;
	private String postcentralSalesTax;
	private String posteducationcess;
	private String postvaladdtax;
	private String postfeetotal;
	private String totalfee;
	
	private String reqfee;
	private String reqothers;
	private String reqtotal;

	private String intimationTypeId;
	private String intimationTypeValue;

	private String rtiRequestThreeAction;
	private String rtiRequestFiveAction;
	private String rtiRequestFourAction;
	private String rtiRequestTwoAction;
	private String rtiRequestOneAction;
	private String emailId;
	private String textEmailId;
	private String sms;
	private String textSMS;
	private String post;
	private String postaddress;
	private String empID;
	private String empName;
	private String cashReceiptID;
	private String cashReceiptDate;
	private String challanNo;
	private String radiobuttonPayment1;
	private String durationFrom;
	private String durationTo;
	private String droName;
	//private String drName;
	private String resolutionDate;
	private String resolutionRemark;
	private String resolutionInformation;
	private String rtiStatus;
	private String requestDate;
	private String updateDate;
	private String rtiID;
	private String firstName;
	private String middleName;
	private String lastName;
	private String gender;
	private String age;
	private String emailID;
	private String fatherName;
	private String motherName;
	private String spouseName;
	private String address;
	private String city;
	private String district;
	private String state;
	private String postelCode;
	private String phoneNumber;
	private String mobileNumber;
	private String listID;

	private String requestInformation;
	private String paymentmode1;
	private String chkChallanDel;
	private String challan;
	private String challanDate;
	private String bankName;
	private String amount;
	private String idProofNumber;

	private String commonID;
	private String idName;
	private String searchReportType;
	private String searchAnualType;

	private String total;
	private String received;
	private String closed;
	private String openOnStartDate;
	private String openOnEndDate;
	private String openUptoOneMonth;
	private String openOneToThreeMonth;
	private String beyondThreeMonth;
	private String totalOpen;

	// RTI STATUS TWO
	private String rtiStatusTwoForm;
	private String rtiStatusTwoAction;
	private String rtiStatusTwoAction1;

	private String rtiAssignAction;
	private String replycomments;
	private String replyStatus;
	private String dueDate;

	private String rtiReportForm;
	private String rtiReportAction;

	private String hoAssignForm;
	private String hoAssignAction;

	private String emailIntimationFee;
	private String emailIntimationOthers;
	private String emailIntimationTotal;
	private String smsIntimationFee;
	private String smsIntimationOthers;
	private String smsIntimationTotal;
	private String postIntimationFee;
	private String postIntimationOthers;
	private String postIntimationTotal;
	private String pageIntimationFee;
	private String pageIntimationOthers;
	private String pageIntimationTotal;
	private String hourIntimationFee;
	private String hourIntimationOthers;
	private String hourIntimationTotal;
	private String appealIntimationFee;
	private String appealIntimationOthers;
	private String appealIntimationTotal;

	

	private ArrayList drnamelist = new ArrayList();

	private ArrayList idlist = new ArrayList();

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.firstName = "";
		this.middleName = "";
		this.lastName = "";
	}

	public void setGender(String _gender) {
		this.gender = _gender;
	}

	public String getGender() {
		return gender;
	}

	public void setAddress(String _address) {
		this.address = _address;
	}

	public String getAddress() {
		return address;
	}

	public void setCity(String _city) {
		this.city = _city;
	}

	public String getCity() {
		return city;
	}

	public void setCountry(String _country) {
		this.country = _country;
	}

	public String getCountry() {
		return country;
	}

	public void setState(String _state) {
		this.state = _state;
	}

	public String getState() {
		return state;
	}

	public void setListID(String _listID) {
		this.listID = _listID;
	}

	public String getListID() {
		return listID;
	}

	public void setPaymentmode1(String _paymentmode1) {
		this.paymentmode1 = _paymentmode1;
	}

	public String getPaymentmode1() {
		return paymentmode1;
	}

	public void setChkChallanDel(String _chkChallanDel) {
		this.chkChallanDel = _chkChallanDel;
	}

	public String getChkChallanDel() {
		return chkChallanDel;
	}

	public void setAge(String _age) {
		this.age = _age;
	}

	public String getAge() {
		return age;
	}

	public void setFirstName(String _firstName) {
		this.firstName = _firstName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setMiddleName(String _middleName) {
		this.middleName = _middleName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setLastName(String _lastName) {
		this.lastName = _lastName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setFatherName(String _fatherName) {
		this.fatherName = _fatherName;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setMotherName(String _motherName) {
		this.motherName = _motherName;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setSpouseName(String _spouseName) {
		this.spouseName = _spouseName;
	}

	public String getSpouseName() {
		return spouseName;
	}

	public void setPhoneNumber(String _phoneNumber) {
		this.phoneNumber = _phoneNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setMobileNumber(String _mobileNumber) {
		this.mobileNumber = _mobileNumber;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setRequestInformation(String _requestInformation) {
		this.requestInformation = _requestInformation;
	}

	public String getRequestInformation() {
		return requestInformation;
	}

	public void setChallan(String _challan) {
		this.challan = _challan;
	}

	public String getChallan() {
		return challan;
	}

	public void setAmount(String _amount) {
		this.amount = _amount;
	}

	public String getAmount() {
		return amount;
	}

	public void setBankName(String _bankName) {
		this.bankName = _bankName;
	}

	public String getBankName() {
		return bankName;
	}

	public void setRtiID(String rtiID) {
		this.rtiID = rtiID;
	}

	public String getRtiID() {
		return rtiID;
	}

	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}

	public String getRequestDate() {
		return requestDate;
	}

	public void setRtiStatus(String rtiStatus) {
		this.rtiStatus = rtiStatus;
	}

	public String getRtiStatus() {
		return rtiStatus;
	}

	public void setResolutionInformation(String resolutionInformation) {
		this.resolutionInformation = resolutionInformation;
	}

	public String getResolutionInformation() {
		return resolutionInformation;
	}

	public void setDroName(String droName) {
		this.droName = droName;
	}

	public String getDroName() {
		return droName;
	}

	

	public void setResolutionRemark(String resolutionRemark) {
		this.resolutionRemark = resolutionRemark;
	}

	public String getResolutionRemark() {
		return resolutionRemark;
	}

	public void setResolutionDate(String resolutionDate) {
		this.resolutionDate = resolutionDate;
	}

	public String getResolutionDate() {
		return resolutionDate;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	public String getEmailID() {
		return emailID;
	}

	public void setPostelCode(String postelCode) {
		this.postelCode = postelCode;
	}

	public String getPostelCode() {
		return postelCode;
	}

	public void setChallanDate(String challanDate) {
		this.challanDate = challanDate;
	}

	public String getChallanDate() {
		return challanDate;
	}

	public void setDurationFrom(String durationFrom) {
		this.durationFrom = durationFrom;
	}

	public String getDurationFrom() {
		return durationFrom;
	}

	public void setDurationTo(String durationTo) {
		this.durationTo = durationTo;
	}

	public String getDurationTo() {
		return durationTo;
	}

	public void setRadiobuttonPayment1(String radiobuttonPayment1) {
		this.radiobuttonPayment1 = radiobuttonPayment1;
	}

	public String getRadiobuttonPayment1() {
		return radiobuttonPayment1;
	}

	public void setChallanNo(String challanNo) {
		this.challanNo = challanNo;
	}

	public String getChallanNo() {
		return challanNo;
	}

	public void setCashReceiptID(String cashReceiptID) {
		this.cashReceiptID = cashReceiptID;
	}

	public String getCashReceiptID() {
		return cashReceiptID;
	}

	public void setRtiStatusTwoForm(String rtiStatusTwoForm) {
		this.rtiStatusTwoForm = rtiStatusTwoForm;
	}

	public String getRtiStatusTwoForm() {
		return rtiStatusTwoForm;
	}

	public void setRtiStatusTwoAction(String rtiStatusTwoAction) {
		this.rtiStatusTwoAction = rtiStatusTwoAction;
	}

	public String getRtiStatusTwoAction() {
		return rtiStatusTwoAction;
	}

	public void setRtiReportForm(String rtiReportForm) {
		this.rtiReportForm = rtiReportForm;
	}

	public String getRtiReportForm() {
		return rtiReportForm;
	}

	public void setRtiReportAction(String rtiReportAction) {
		this.rtiReportAction = rtiReportAction;
	}

	public String getRtiReportAction() {
		return rtiReportAction;
	}

	public void setHoAssignForm(String hoAssignForm) {
		this.hoAssignForm = hoAssignForm;
	}

	public String getHoAssignForm() {
		return hoAssignForm;
	}

	public void setHoAssignAction(String hoAssignAction) {
		this.hoAssignAction = hoAssignAction;
	}

	public String getHoAssignAction() {
		return hoAssignAction;
	}

	public void setEmpID(String empID) {
		this.empID = empID;
	}

	public String getEmpID() {
		return empID;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpName() {
		return empName;
	}

	public void setDrnamelist(ArrayList drnamelist) {
		this.drnamelist = drnamelist;
	}

	public ArrayList getDrnamelist() {
		return drnamelist;
	}

	public void setIdProofNumber(String idProofNumber) {
	    this.idProofNumber = idProofNumber;
	}

	public String getIdProofNumber() {
	    		
		return idProofNumber;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	public String getCountryId() {
		return countryId;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getStateName() {
		return stateName;
	}

	public void setCountryList(ArrayList countryList) {
		this.CountryList = countryList;
	}

	public ArrayList getCountryList() {
		return CountryList;
	}

	public void setStateList(ArrayList stateList) {
		this.StateList = stateList;
	}

	public ArrayList getStateList() {
		return StateList;
	}

	public void setSearchReportType(String searchReportType) {
		this.searchReportType = searchReportType;
	}

	public String getSearchReportType() {
		return searchReportType;
	}

	public void setSearchAnualType(String searchAnualType) {
		this.searchAnualType = searchAnualType;
	}

	public String getSearchAnualType() {
		return searchAnualType;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getTotal() {
		return total;
	}

	public void setReceived(String received) {
		this.received = received;
	}

	public String getReceived() {
		return received;
	}

	public void setClosed(String closed) {
		this.closed = closed;
	}

	public String getClosed() {
		return closed;
	}

	public void setOpenOnStartDate(String openOnStartDate) {
		this.openOnStartDate = openOnStartDate;
	}

	public String getOpenOnStartDate() {
		return openOnStartDate;
	}

	public void setOpenOnEndDate(String openOnEndDate) {
		this.openOnEndDate = openOnEndDate;
	}

	public String getOpenOnEndDate() {
		return openOnEndDate;
	}

	public void setOpenUptoOneMonth(String openUptoOneMonth) {
		this.openUptoOneMonth = openUptoOneMonth;
	}

	public String getOpenUptoOneMonth() {
		return openUptoOneMonth;
	}

	public void setOpenOneToThreeMonth(String openOneToThreeMonth) {
		this.openOneToThreeMonth = openOneToThreeMonth;
	}

	public String getOpenOneToThreeMonth() {
		return openOneToThreeMonth;
	}

	public void setBeyondThreeMonth(String beyondThreeMonth) {
		this.beyondThreeMonth = beyondThreeMonth;
	}

	public String getBeyondThreeMonth() {
		return beyondThreeMonth;
	}

	public void setTotalOpen(String totalOpen) {
		this.totalOpen = totalOpen;
	}

	public String getTotalOpen() {
		return totalOpen;
	}

	public void setCommonID(String commonID) {
		this.commonID = commonID;
	}

	public String getCommonID() {
		return commonID;
	}

	public void setIdName(String idName) {
		this.idName = idName;
	}

	public String getIdName() {
		return idName;
	}

	public void setIdlist(ArrayList idlist) {
		this.idlist = idlist;
	}

	public ArrayList getIdlist() {
		return idlist;
	}

	public void setEmailId(String emailId) {
		
		this.emailId = emailId;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setTextEmailId(String textEmailId) {
		this.textEmailId = textEmailId;
	}

	public String getTextEmailId() {
		return textEmailId;
	}

	public void setSms(String sms) {
		
		this.sms = sms;
	}

	public String getSms() {
		return sms;
	}

	public void setTextSMS(String textSMS) {
		this.textSMS = textSMS;
	}

	public String getTextSMS() {
		return textSMS;
	}

	public void setPost(String post) {
		
		this.post = post;
	}

	public String getPost() {
		return post;
	}

	public void setRtiRequestTwoAction(String rtiRequestTwoAction) {
		this.rtiRequestTwoAction = rtiRequestTwoAction;
	}

	public String getRtiRequestTwoAction() {
		return rtiRequestTwoAction;
	}

	public void setRtiRequest_Form(String rtiRequest_Form) {
		this.rtiRequest_Form = rtiRequest_Form;
	}

	public String getRtiRequest_Form() {
		return rtiRequest_Form;
	}

	public void setRtiRequestFourAction(String rtiRequestFourAction) {
		this.rtiRequestFourAction = rtiRequestFourAction;
	}

	public String getRtiRequestFourAction() {
		return rtiRequestFourAction;
	}

	public void setRtiRequestFiveAction(String rtiRequestFiveAction) {
		this.rtiRequestFiveAction = rtiRequestFiveAction;
	}

	public String getRtiRequestFiveAction() {
		return rtiRequestFiveAction;
	}

	public void setRtiRequestOneAction(String rtiRequestOneAction) {
		this.rtiRequestOneAction = rtiRequestOneAction;
	}

	public String getRtiRequestOneAction() {
		return rtiRequestOneAction;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrictList(ArrayList districtList) {
		this.districtList = districtList;
	}

	public ArrayList getDistrictList() {
		return districtList;
	}

	

	public void setEmailcentralSalesTax(String emailcentralSalesTax) {
		this.emailcentralSalesTax = emailcentralSalesTax;
	}

	public String getEmailcentralSalesTax() {
		return emailcentralSalesTax;
	}

	public void setEmaileducationcess(String emaileducationcess) {
		this.emaileducationcess = emaileducationcess;
	}

	public String getEmaileducationcess() {
		return emaileducationcess;
	}

	public void setEmailvaladdtax(String emailvaladdtax) {
		this.emailvaladdtax = emailvaladdtax;
	}

	public String getEmailvaladdtax() {
		return emailvaladdtax;
	}

	public void setEmailfeetotal(String emailfeetotal) {
		this.emailfeetotal = emailfeetotal;
	}

	public String getEmailfeetotal() {
		return emailfeetotal;
	}

	public void setSmscentralSalesTax(String smscentralSalesTax) {
		this.smscentralSalesTax = smscentralSalesTax;
	}

	public String getSmscentralSalesTax() {
		return smscentralSalesTax;
	}

	public void setSmseducationcess(String smseducationcess) {
		this.smseducationcess = smseducationcess;
	}

	public String getSmseducationcess() {
		return smseducationcess;
	}

	public void setSmsvaladdtax(String smsvaladdtax) {
		this.smsvaladdtax = smsvaladdtax;
	}

	public String getSmsvaladdtax() {
		return smsvaladdtax;
	}

	public void setSmsfeetotal(String smsfeetotal) {
		this.smsfeetotal = smsfeetotal;
	}

	public String getSmsfeetotal() {
		return smsfeetotal;
	}

	public void setSmsBaseValue(String smsBaseValue) {
		this.smsBaseValue = smsBaseValue;
	}

	public String getSmsBaseValue() {
		return smsBaseValue;
	}

	public void setPostBaseValue(String postBaseValue) {
		this.postBaseValue = postBaseValue;
	}

	public String getPostBaseValue() {
		return postBaseValue;
	}

	public void setPostcentralSalesTax(String postcentralSalesTax) {
		this.postcentralSalesTax = postcentralSalesTax;
	}

	public String getPostcentralSalesTax() {
		return postcentralSalesTax;
	}

	public void setPosteducationcess(String posteducationcess) {
		this.posteducationcess = posteducationcess;
	}

	public String getPosteducationcess() {
		return posteducationcess;
	}

	public void setPostvaladdtax(String postvaladdtax) {
		this.postvaladdtax = postvaladdtax;
	}

	public String getPostvaladdtax() {
		return postvaladdtax;
	}

	public void setPostfeetotal(String postfeetotal) {
		this.postfeetotal = postfeetotal;
	}

	public String getPostfeetotal() {
		return postfeetotal;
	}

	public void setTotalfee(String totalfee) {
		this.totalfee = totalfee;
	}

	public String getTotalfee() {
		return totalfee;
	}

	public String getPostaddress() {
		return postaddress;
	}

	public void setPostaddress(String postaddress) {
		this.postaddress = postaddress;
	}

	public String getRtiStatusTwoAction1() {
		return rtiStatusTwoAction1;
	}

	public void setRtiStatusTwoAction1(String rtiStatusTwoAction1) {
		this.rtiStatusTwoAction1 = rtiStatusTwoAction1;
	}

	public String getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(String numberOfPages) {
		this.numberOfPages = numberOfPages;
	}

	public String getNumberOfHour() {
		return numberOfHour;
	}

	public void setNumberOfHour(String numberOfHour) {
		this.numberOfHour = numberOfHour;
	}

	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

	public String getOthers() {
		return others;
	}

	public void setOthers(String others) {
		this.others = others;
	}

	public String getIntimationTypeId() {
		return intimationTypeId;
	}

	public void setIntimationTypeId(String intimationTypeId) {
		this.intimationTypeId = intimationTypeId;
	}

	public String getIntimationTypeValue() {
		return intimationTypeValue;
	}

	public void setIntimationTypeValue(String intimationTypeValue) {
		this.intimationTypeValue = intimationTypeValue;
	}

	public String getRtiAssignAction() {
		return rtiAssignAction;
	}

	public void setRtiAssignAction(String rtiAssignAction) {
		this.rtiAssignAction = rtiAssignAction;
	}

	public String getReplycomments() {
		return replycomments;
	}

	public void setReplycomments(String replycomments) {
		this.replycomments = replycomments;
	}

	public String getReplyStatus() {
		return replyStatus;
	}

	public void setReplyStatus(String replyStatus) {
		this.replyStatus = replyStatus;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public String getRtiRequestThreeAction() {
		return rtiRequestThreeAction;
	}

	public void setRtiRequestThreeAction(String rtiRequestThreeAction) {
		this.rtiRequestThreeAction = rtiRequestThreeAction;
	}

	public String getEmailIntimationFee() {
		return emailIntimationFee;
	}

	public void setEmailIntimationFee(String emailIntimationFee) {
		this.emailIntimationFee = emailIntimationFee;
	}

	public String getSmsIntimationFee() {
		return smsIntimationFee;
	}

	public void setSmsIntimationFee(String smsIntimationFee) {
		this.smsIntimationFee = smsIntimationFee;
	}

	public String getPostIntimationFee() {
		return postIntimationFee;
	}

	public void setPostIntimationFee(String postIntimationFee) {
		this.postIntimationFee = postIntimationFee;
	}

	public String getPageIntimationFee() {
		return pageIntimationFee;
	}

	public void setPageIntimationFee(String pageIntimationFee) {
		this.pageIntimationFee = pageIntimationFee;
	}

	public String getHourIntimationFee() {
		return hourIntimationFee;
	}

	public void setHourIntimationFee(String hourIntimationFee) {
		this.hourIntimationFee = hourIntimationFee;
	}

	public String getEmailBaseValue() {
		return emailBaseValue;
	}

	public void setEmailBaseValue(String emailBaseValue) {
		this.emailBaseValue = emailBaseValue;
	}
	
	/**
	 * This method is used to return totalFixedFee
	 * @return
	 */
	public String getTotalFixedFee() {
		return totalFixedFee;
	}
	/**
	 * This method is used to set totalFixedFee
	 * @param totalFixedFee
	 */
	public void setTotalFixedFee(String totalFixedFee) {
		this.totalFixedFee = totalFixedFee;
	}
	/**
	 * This method is used to return totalInformationFee
	 * @return
	 */
	public String getTotalInformationFee() {
		return totalInformationFee;
	}
	/**
	 * This method is used to set totalInformationFee
	 * @param totalInformationFee
	 */
	public void setTotalInformationFee(String totalInformationFee) {
		this.totalInformationFee = totalInformationFee;
	}
	/**
	 * This method is used to return totalAppealFee
	 * @return
	 */
	public String getTotalAppealFee() {
		return totalAppealFee;
	}
	/**
	 * This method is used to set totalAppealFee
	 * @param totalAppealFee
	 */
	public void setTotalAppealFee(String totalAppealFee) {
		this.totalAppealFee = totalAppealFee;
	}
	
	/**
	 * This method is used to return cashReceiptDate
	 * @return
	 */
	public String getCashReceiptDate() {
		return cashReceiptDate;
	}
	/**
	 * This method is used to set cashReceiptDate
	 * @param cashReceiptDate
	 */
	public void setCashReceiptDate(String cashReceiptDate) {
		this.cashReceiptDate = cashReceiptDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	

	public String getReqfee() {
		return reqfee;
	}

	public void setReqfee(String reqfee) {
		this.reqfee = reqfee;
	}

	public String getReqothers() {
		return reqothers;
	}

	public void setReqothers(String reqothers) {
		this.reqothers = reqothers;
	}

	public String getReqtotal() {
		return reqtotal;
	}

	public void setReqtotal(String reqtotal) {
		this.reqtotal = reqtotal;
	}

	public String getEmailIntimationOthers() {
		return emailIntimationOthers;
	}

	public void setEmailIntimationOthers(String emailIntimationOthers) {
		this.emailIntimationOthers = emailIntimationOthers;
	}

	

	public String getSmsIntimationOthers() {
		return smsIntimationOthers;
	}

	public void setSmsIntimationOthers(String smsIntimationOthers) {
		this.smsIntimationOthers = smsIntimationOthers;
	}

	
	public String getPostIntimationOthers() {
		return postIntimationOthers;
	}

	public void setPostIntimationOthers(String postIntimationOthers) {
		this.postIntimationOthers = postIntimationOthers;
	}

	
	public String getPageIntimationOthers() {
		return pageIntimationOthers;
	}

	public void setPageIntimationOthers(String pageIntimationOthers) {
		this.pageIntimationOthers = pageIntimationOthers;
	}
	

	public String getHourIntimationOthers() {
		return hourIntimationOthers;
	}

	public void setHourIntimationOthers(String hourIntimationOthers) {
		this.hourIntimationOthers = hourIntimationOthers;
	}
	
	public String getEmailIntimationTotal() {
		return emailIntimationTotal;
	}

	public void setEmailIntimationTotal(String emailIntimationTotal) {
		this.emailIntimationTotal = emailIntimationTotal;
	}

	public String getSmsIntimationTotal() {
		return smsIntimationTotal;
	}

	public void setSmsIntimationTotal(String smsIntimationTotal) {
		this.smsIntimationTotal = smsIntimationTotal;
	}

	public String getPostIntimationTotal() {
		return postIntimationTotal;
	}

	public void setPostIntimationTotal(String postIntimationTotal) {
		this.postIntimationTotal = postIntimationTotal;
	}

	public String getPageIntimationTotal() {
		return pageIntimationTotal;
	}

	public void setPageIntimationTotal(String pageIntimationTotal) {
		this.pageIntimationTotal = pageIntimationTotal;
	}

	public String getHourIntimationTotal() {
		return hourIntimationTotal;
	}

	public void setHourIntimationTotal(String hourIntimationTotal) {
		this.hourIntimationTotal = hourIntimationTotal;
	}

	public String getAppealIntimationFee() {
		return appealIntimationFee;
	}

	public void setAppealIntimationFee(String appealIntimationFee) {
		this.appealIntimationFee = appealIntimationFee;
	}

	public String getAppealIntimationOthers() {
		return appealIntimationOthers;
	}

	public void setAppealIntimationOthers(String appealIntimationOthers) {
		this.appealIntimationOthers = appealIntimationOthers;
	}

	public String getAppealIntimationTotal() {
		return appealIntimationTotal;
	}

	public void setAppealIntimationTotal(String appealIntimationTotal) {
		this.appealIntimationTotal = appealIntimationTotal;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPaymentTxnId() {
		return paymentTxnId;
	}

	public void setPaymentTxnId(String paymentTxnId) {
		this.paymentTxnId = paymentTxnId;
	}


}
