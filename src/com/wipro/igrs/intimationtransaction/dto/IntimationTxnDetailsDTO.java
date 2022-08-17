/**
 * ===========================================================================
 * File           :   IntimationTxnDetailsDTO.java
 * Description    :   Represents the DTO Class

 * Author         :   Nithya
 * Created Date   :   Jan 8, 2008

 * ===========================================================================
 */

package com.wipro.igrs.intimationtransaction.dto;

import java.util.ArrayList;

public class IntimationTxnDetailsDTO {

    //TransactionIntimation Request Parameters
	private String language;
	private String txnId;
    private String userId;
    private String sNo;
    private String status;
    private String[] intimationReqType;
    private String firstName;
    private String middleName;
    private String lastName;
    private String gender;
    private String fatherName;
    private String motherName; 
    private String spouseName;
    private String address;
    private String city;
    private String country;
    private String state;
    private String postalCode;
    private String phone;
    private String mobile;
    private String email;
    private String personIdentityNo;
    private String age;
    private String age1;
    private String postalCode1;
    private String officeId;
    private String intId;
    private String dateOfRequest;
    private String intimationStatus;
    
	public String getOfficeId() {
		return officeId;
	}

	public String getIntId() {
		return intId;
	}

	public void setIntId(String intId) {
		this.intId = intId;
	}

	public String getDateOfRequest() {
		return dateOfRequest;
	}

	public void setDateOfRequest(String dateOfRequest) {
		this.dateOfRequest = dateOfRequest;
	}

	public String getIntimationStatus() {
		return intimationStatus;
	}

	public void setIntimationStatus(String intimationStatus) {
		this.intimationStatus = intimationStatus;
	}

	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}

	public String getAge1() {
		return age1;
	}

	public void setAge1(String age1) {
		this.age1 = age1;
	}

	public String getPostalCode1() {
		return postalCode1;
	}

	public void setPostalCode1(String postalCode1) {
		this.postalCode1 = postalCode1;
	}




	private String photoIdType;
	private String photoIdTypeName;
    public String getPhotoIdTypeName() {
		return photoIdTypeName;
	}

	public void setPhotoIdTypeName(String photoIdTypeName) {
		this.photoIdTypeName = photoIdTypeName;
	}




	private String photoIdNo;
    private String docRegNo;
    private String volumeNo;
    private String bookNo;
    private String number;
    private String intimationType;
    private String[] intimationTypes;
    private String[] intimationValues ;
    private String intimationTypeId;
    private String fromDate;
    private String toDate;
    private String docId;
    private String paymentMode;
    private String applnStatus;
    private String createdDate;
    private String paidAmount;
    private Double paidAmnt;
    private String intimationSelect;
    private String intimationCheckbox1;
    private String intimationCheckbox2;
    private String intimationCheckbox3;
    
    public String getIntimationCheckbox2() {
		return intimationCheckbox2;
	}

	public void setIntimationCheckbox2(String intimationCheckbox2) {
		this.intimationCheckbox2 = intimationCheckbox2;
	}

	public String getIntimationCheckbox3() {
		return intimationCheckbox3;
	}

	public void setIntimationCheckbox3(String intimationCheckbox3) {
		this.intimationCheckbox3 = intimationCheckbox3;
	}




	private String smsValue;
    private String emailValue;
    private String postValue;


    public String getPostValue() {
		return postValue;
	}

	public void setPostValue(String postValue) {
		this.postValue = postValue;
	}

	public String getEmailValue() {
		return emailValue;
	}

	public void setEmailValue(String emailValue) {
		this.emailValue = emailValue;
	}

	public String getSmsValue() {
		return smsValue;
	}

	public void setSmsValue(String smsValue) {
		this.smsValue = smsValue;
	}

	public String getIntimationCheckbox1() {
		return intimationCheckbox1;
	}

	public void setIntimationCheckbox1(String intimationCheckbox1) {
		this.intimationCheckbox1 = intimationCheckbox1;
	}

	public String getIntimationSelect() {
		return intimationSelect;
	}

	public void setIntimationSelect(String intimationSelect) {
		this.intimationSelect = intimationSelect;
	}

	public Double getPaidAmnt() {
		return paidAmnt;
	}

	public void setPaidAmnt(Double paidAmnt) {
		this.paidAmnt = paidAmnt;
	}




	private ArrayList pendingApps = new ArrayList();
   
	public ArrayList getPendingApps() {
		return pendingApps;
	}

	public void setPendingApps(ArrayList pendingApps) {
		this.pendingApps = pendingApps;
	}

	public String getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(String paidAmount) {
		this.paidAmount = paidAmount;
	}

	public String getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(String balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	public String getLastTransactionDate() {
		return lastTransactionDate;
	}

	public void setLastTransactionDate(String lastTransactionDate) {
		this.lastTransactionDate = lastTransactionDate;
	}




	private Double balance;
	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}




	private String balanceAmount;
    private String lastTransactionDate;
    
    private String parentModName;
    public String getParentModName() {
		return parentModName;
	}

	public void setParentModName(String parentModName) {
		this.parentModName = parentModName;
	}

	public String getParentFunName() {
		return parentFunName;
	}

	public void setParentFunName(String parentFunName) {
		this.parentFunName = parentFunName;
	}





	private String parentFunName;
   

    private ArrayList countryList;
    private ArrayList stateList;
    private String countryId;
    //private String counrtyName;
    private ArrayList districtList;
    private String stateId;
    private String cityId;
    public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}





	private String cityName;
    public String getStateId() {
		return stateId;
	}

	public void setStateId(String stateId) {
		this.stateId = stateId;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}





	private String stateName;
    
    private String bankName;
    private String bankAddr;

    //Fee Details Params		
    private String fee;
    private String otherFee;
    private String totalFee;

    //Intimation Transaction Request Action params
    private String intimationTxnActionForm;
    private String intimationTxnSubmitAction;
    private String intimationTxnModifyAction;
    private String intimationProceedPaymentAction;
    private String intimationModifyPaymentAction;
    private String intimationPreviousPaymentAction;
    private String intimationTxnSuccessAction;
    private String intimationViewSubmitAction;
    private String intimationViewIdDetailsAction;
    private String intimationOkButtonAction;
    private String intimationTxnPrintAction;
    //following variables added by roopam for removing jsp error. IntimationLog.jsp
    private String searchSNo;
    private ArrayList detailsList;
    private String notFound;
    
   
	

	public String getNotFound() {
		return notFound;
	}

	public void setNotFound(String notFound) {
		this.notFound = notFound;
	}

	public String getPersonIdentityNo() {
		return personIdentityNo;
	}

	public void setPersonIdentityNo(String personIdentityNo) {
		this.personIdentityNo = personIdentityNo;
	}

	public String getSearchSNo() {
		return searchSNo;
	}

	public void setSearchSNo(String searchSNo) {
		this.searchSNo = searchSNo;
	}

	public String getPropertyRegNo() {
		return propertyRegNo;
	}

	public void setPropertyRegNo(String propertyRegNo) {
		this.propertyRegNo = propertyRegNo;
	}

	public String getSearchTypeOfTxn() {
		return searchTypeOfTxn;
	}

	public void setSearchTypeOfTxn(String searchTypeOfTxn) {
		this.searchTypeOfTxn = searchTypeOfTxn;
	}

	public String getSearchDate() {
		return searchDate;
	}

	public void setSearchDate(String searchDate) {
		this.searchDate = searchDate;
	}

	public String getSearchTime() {
		return searchTime;
	}

	public void setSearchTime(String searchTime) {
		this.searchTime = searchTime;
	}

	public String getSearchUser() {
		return searchUser;
	}

	public void setSearchUser(String searchUser) {
		this.searchUser = searchUser;
	}
	
	
	
	
	
	
	public void setCountryList(ArrayList countryList) {
		this.countryList = countryList;
	}
	
	public String getCountryId() {
		return countryId;
	}
	public void setStateList(ArrayList stateList) {
		this.stateList = stateList;
	}
	public void setDistrictList(ArrayList districtList) {
		this.districtList = districtList;
	}
	
	
	private String filePhoto;
	private String fileThumb;
	private String fileSignature;
	private String TransactionID;
	private String tranID;

	public String getTranID() {
		return tranID;
	}

	public void setTranID(String tranID) {
		this.tranID = tranID;
	}

	public String getTransactionID() {
		return TransactionID;
	}

	public void setTransactionID(String transactionID) {
		TransactionID = transactionID;
	}

	public String getFilePhoto() {
		return filePhoto;
	}

	public void setFilePhoto(String filePhoto) {
		this.filePhoto = filePhoto;
	}

	public String getFileThumb() {
		return fileThumb;
	}

	public void setFileThumb(String fileThumb) {
		this.fileThumb = fileThumb;
	}

	public String getFileSignature() {
		return fileSignature;
	}

	public void setFileSignature(String fileSignature) {
		this.fileSignature = fileSignature;
	}





	private String propertyRegNo;
    private String searchTypeOfTxn;
    private String searchDate;
    private String searchTime;
    private String searchUser;
    /**
     * @author Imran Shaik
     * added for Type Of Intimation
     */
    private ArrayList typeOfIntimation;
	private String countryName;
    
    
    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }

    public String getTxnId() {
        return txnId;
    } 

    public void setSNo(String sNo) {
        this.sNo = sNo;
    }

    public String getSNo() {
        return sNo;
    } 

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    } 

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    } 

    public void setIntimationReqType(String[] intimationReqType) {
        this.intimationReqType = intimationReqType;
    }

    public String[] getIntimationReqType() {
        return intimationReqType;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAge() {
        return age;
    }    

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setSpouseName(String spouseName) {
        this.spouseName = spouseName;
    }

    public String getSpouseName() {
        return spouseName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile() {
        return mobile;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPhotoIdType(String photoIdType) {
        this.photoIdType = photoIdType;
    }

    public String getPhotoIdType() {
        return photoIdType;
    }   

    public void setPhotoIdNo(String photoIdNo) {
        this.photoIdNo = photoIdNo;
    }

    public String getPhotoIdNo() {
        return photoIdNo;
    }



    public void setDocRegNo(String docRegNo) {
        this.docRegNo = docRegNo;
    }

    public String getDocRegNo() {
        return docRegNo;
    }   

    public void setVolumeNo(String volumeNo) {
        this.volumeNo = volumeNo;
    }

    public String getVolumeNo() {
        return volumeNo;
    }   

    public void setBookNo(String bookNo) {
        this.bookNo = bookNo;
    }

    public String getBookNo() {
        return bookNo;
    }  

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }  

    public void setIntimationType(String intimationType) {
        this.intimationType = intimationType;
    }

    public String getIntimationType() {
        return intimationType;
    }  
    public void setIntimationTypes(String[] intimationTypes) {
        this.intimationTypes = intimationTypes;
    }

    public String[] getIntimationTypes() {
        return intimationTypes;
    }  

    public void setIntimationValues(String[] intimationValues) {
        this.intimationValues = intimationValues;
    }

    public String[] getIntimationValues() {
        return intimationValues;
    }
    public void setIntimationTypeId(String intimationTypeId) {
        this.intimationTypeId = intimationTypeId;
    }

    public String getIntimationTypeId() {
        return intimationTypeId;
    } 

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getPaymentMode() {
        return paymentMode;
    } 

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getApplnStatus() {
        return applnStatus;
    } 

    public void setApplnStatus(String applnStatus) {
        this.applnStatus = applnStatus;
    }

    public String getDocId() {
        return docId;
    } 

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getFromDate() {
        return fromDate;
    } 

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankAddr(String bankAddr) {
        this.bankAddr = bankAddr;
    }

    public String getBankAddr() {
        return bankAddr;
    }                        

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getFee() {
        return fee;
    }

    public void setOtherFee(String otherFee) {
        this.otherFee = otherFee;
    }

    public String getOtherFee() {
        return otherFee;
    }

    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee;
    }

    public String getTotalFee() {
        return totalFee;
    }

    public void setIntimationTxnActionForm(String intimationTxnActionForm) {
        this.intimationTxnActionForm = intimationTxnActionForm;
    }

    public String getIntimationTxnActionForm() {
        return intimationTxnActionForm;
    }

    public void setIntimationTxnSubmitAction(String intimationTxnSubmitAction) {
        this.intimationTxnSubmitAction = intimationTxnSubmitAction;
    }

    public String getIntimationTxnSubmitAction() {
        return intimationTxnSubmitAction;
    }

    public void setIntimationTxnModifyAction(String intimationTxnModifyAction) {
        this.intimationTxnModifyAction = intimationTxnModifyAction;
    }

    public String getIntimationTxnModifyAction() {
        return intimationTxnModifyAction;
    }

    public void setIntimationProceedPaymentAction(String intimationProceedPaymentAction) {
        this.intimationProceedPaymentAction = intimationProceedPaymentAction;
    }

    public String getIntimationProceedPaymentAction() {
        return intimationProceedPaymentAction;
    }

    public void setIntimationModifyPaymentAction(String intimationModifyPaymentAction) {
        this.intimationModifyPaymentAction = intimationModifyPaymentAction;
    }

    public String getIntimationModifyPaymentAction() {
        return intimationModifyPaymentAction;
    }

    public void setIntimationPreviousPaymentAction(String intimationPreviousPaymentAction) {
        this.intimationPreviousPaymentAction = intimationPreviousPaymentAction;
    }

    public String getIntimationPreviousPaymentAction() {
        return intimationPreviousPaymentAction;
    }

    public void setIntimationTxnSuccessAction(String intimationTxnSuccessAction) {
        this.intimationTxnSuccessAction = intimationTxnSuccessAction;
    }

    public String getIntimationTxnSuccessAction() {
        return intimationTxnSuccessAction;
    }

    public void setIntimationViewSubmitAction(String intimationViewSubmitAction) {
        this.intimationViewSubmitAction = intimationViewSubmitAction;
    }

    public String getIntimationViewSubmitAction() {
        return intimationViewSubmitAction;
    }

    public void setIntimationViewIdDetailsAction(String intimationViewIdDetailsAction) {
        this.intimationViewIdDetailsAction = intimationViewIdDetailsAction;
    }

    public String getIntimationViewIdDetailsAction() {
        return intimationViewIdDetailsAction;
    }

    public void setIntimationOkButtonAction(String intimationOkButtonAction) {
        this.intimationOkButtonAction = intimationOkButtonAction;
    }

    public String getIntimationOkButtonAction() {
        return intimationOkButtonAction;
    }

    public void setIntimationTxnPrintAction(String intimationTxnPrintAction) {
        this.intimationTxnPrintAction = intimationTxnPrintAction;
    }

    public String getIntimationTxnPrintAction() {
        return intimationTxnPrintAction;
    }

    /**
     * @return the typeOfIntimation
     */
    public ArrayList getTypeOfIntimation() {
        return typeOfIntimation;
    }

    /**
     * @param typeOfIntimation the typeOfIntimation to set
     */
    public void setTypeOfIntimation(ArrayList typeOfIntimation) {
        this.typeOfIntimation = typeOfIntimation;
    }

	public void setCountryId(String countryId) {
		this.countryId=countryId;
		
	}

	public void setCountryName(String countryName) {
	   this.countryName=countryName;
		
	}

	public String getsNo() {
		return sNo;
	}

	public void setsNo(String sNo) {
		this.sNo = sNo;
	}

	public ArrayList getCountryList() {
		return countryList;
	}

	public ArrayList getStateList() {
		return stateList;
	}

	public ArrayList getDistrictList() {
		return districtList;
	}

	public String getCountryName() {
		return countryName;
	}

	public ArrayList getDetailsList() {
		return detailsList;
	}

	public void setDetailsList(ArrayList detailsList) {
		this.detailsList = detailsList;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getLanguage() {
		return language;
	}

	

}


