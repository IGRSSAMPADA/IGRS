package com.wipro.igrs.propertyvaluationefiling.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import com.wipro.igrs.newPropvaluationefiling.dto.PropertyValuationDTO;


/**
 * @since 14 jan 2008
 * File Name: DutyCalculationAction.java
 * @author Madan Mohan
 * @version 1.0
 * Created on 14 jan 2008
 *
 */
public class DutyCalculationDTO implements Serializable {
	private String propertyoutMP;
	//bleow variable used for display data in grid form
	private String displayStampVendorName;
	private String displayStampVendorSeries;
	private String displayCodeStamp;
	private String displayDateStamp;
	private String flag;
	//end 
	
	private String efileFeeBeforeExemp;
	private String eileFeeDisplay;
	private String efileFeeAfterExemp;
	
	private String efileFeeAfterExempDisplay;
	
	private String nameOfOfficial;
	private String nameOfOfficial1;
	private String nameOfOfficial2;
	private String nameOfOfficial3;
	private String nameOfOfficial4;
	private String nameOfOfficial5;
	private String nameOfOfficial6;
	private String nameOfOfficial7;
	private String nameOfOfficial8;
	
	private String addressGovtOffclOutMp;
	
	
	
	
	
	
	
	
	private String stampvendor;
	private String codeOfStamps;
	private String seriesNumber;
      private String dateOfStamps;
	
	//end of bank variable
	
	public String getStampvendor() {
		return stampvendor;
	}
	public void setStampvendor(String stampvendor) {
		this.stampvendor = stampvendor;
	}
	public String getCodeOfStamps() {
		return codeOfStamps;
	}
	public void setCodeOfStamps(String codeOfStamps) {
		this.codeOfStamps = codeOfStamps;
	}
	public String getSeriesNumber() {
		return seriesNumber;
	}
	public void setSeriesNumber(String seriesNumber) {
		this.seriesNumber = seriesNumber;
	}
	public String getDateOfStamps() {
		return dateOfStamps;
	}
	public void setDateOfStamps(String dateOfStamps) {
		this.dateOfStamps = dateOfStamps;
	}
	private String addressOfOfficial;
	
	private String desgOfOfficial;
	//added for bank page
	private String BankName;
	private String BankAddress;
	private String Country;
	private String State;
	private String District;
	private String Pin;
	private String PhoneNo;
	private String BankAName;
	private String BankDesg;
	private String BankPhn;
	private String BankEmail;
	
	public String getBankName() {
		return BankName;
	}
	public void setBankName(String bankName) {
		BankName = bankName;
	}
	public String getBankAddress() {
		return BankAddress;
	}
	public void setBankAddress(String bankAddress) {
		BankAddress = bankAddress;
	}
	public String getCountry() {
		return Country;
	}
	public void setCountry(String country) {
		Country = country;
	}
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	public String getDistrict() {
		return District;
	}
	public void setDistrict(String district) {
		District = district;
	}
	public String getPin() {
		return Pin;
	}
	public void setPin(String pin) {
		Pin = pin;
	}
	public String getPhoneNo() {
		return PhoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		PhoneNo = phoneNo;
	}
	public String getBankAName() {
		return BankAName;
	}
	public void setBankAName(String bankAName) {
		BankAName = bankAName;
	}
	public String getBankDesg() {
		return BankDesg;
	}
	public void setBankDesg(String bankDesg) {
		BankDesg = bankDesg;
	}
	public String getBankPhn() {
		return BankPhn;
	}
	public void setBankPhn(String bankPhn) {
		BankPhn = bankPhn;
	}
	public String getBankEmail() {
		return BankEmail;
	}
	public void setBankEmail(String bankEmail) {
		BankEmail = bankEmail;
	}
	private String  inddistrict;
	
	private ArrayList distList;
	private ArrayList officeList;
	
	private String districtId="";
	private String districtName="";
	private String officeName="";
	private String officeId="";
	
	// private FormFile propImage1;
	
	
	
	public String getNameOfOfficial() {
		return nameOfOfficial;
	}
	public void setNameOfOfficial(String nameOfOfficial) {
		this.nameOfOfficial = nameOfOfficial;
	}
	public String getDesgOfOfficial() {
		return desgOfOfficial;
	}
	public void setDesgOfOfficial(String desgOfOfficial) {
		this.desgOfOfficial = desgOfOfficial;
	}
	public String getAddressGovtOffclOutMp() {
		return addressGovtOffclOutMp;
	}
	public void setAddressGovtOffclOutMp(String addressGovtOffclOutMp) {
		this.addressGovtOffclOutMp = addressGovtOffclOutMp;
	}
	public String getAddressOfOfficial() {
		return addressOfOfficial;
	}
	public void setAddressOfOfficial(String addressOfOfficial) {
		this.addressOfOfficial = addressOfOfficial;
	}
	private String slotdate;
	private String hidnRegTxnId;
	private String todate;
	public String getSlotdate() {
		return slotdate;
	}
	public void setSlotdate(String slotdate) {
		this.slotdate = slotdate;
	}
	public String getTodate() {
		return todate;
	}
	public void setTodate(String todate) {
		this.todate = todate;
	}
	private String estampFlag;
	
	public String getEstampFlag() {
		return estampFlag;
	}
	public void setEstampFlag(String estampFlag) {
		this.estampFlag = estampFlag;
	}
	
	private String radioResiComm3;
	private String radioResiComm;
	public String getRadioResiComm() {
		return radioResiComm;
	}
	public void setRadioResiComm(String radioResiComm) {
		this.radioResiComm = radioResiComm;
	}
	
	private String radioResiComm1;
	public String getRadioResiComm1() {
		return radioResiComm1;
	}
	public void setRadioResiComm1(String radioResiComm1) {
		this.radioResiComm1 = radioResiComm1;
	}
	public String getEstampCode() {
		return estampCode;
	}
	public void setEstampCode(String estampCode) {
		this.estampCode = estampCode;
	}
	private String estampCode;
	private String purposeLoan;
	
	public String getPurposeLoan() {
		return purposeLoan;
	}
	public void setPurposeLoan(String purposeLoan) {
		this.purposeLoan = purposeLoan;
	}
	/**
	 * @author Madan Mohan
	 */
	//private double baseValue;
	
	
	private String hidEfileTxnId;
	private String officeNameSR;
	private String SRName;
	
	
	public String getHidEfileTxnId() {
		return hidEfileTxnId;
	}
	public void setHidEfileTxnId(String hidEfileTxnId) {
		this.hidEfileTxnId = hidEfileTxnId;
	}
	private String agriApplicableSubclauseId;//added by roopam-14 april 2015
	private String ewsAppliedFlag;
	
	
	
	
	public String getEwsAppliedFlag() {
		return ewsAppliedFlag;
	}
	public void setEwsAppliedFlag(String ewsAppliedFlag) {
		this.ewsAppliedFlag = ewsAppliedFlag;
	}
	public String getAgriApplicableSubclauseId() {
		return agriApplicableSubclauseId;
	}
	public void setAgriApplicableSubclauseId(String agriApplicableSubclauseId) {
		this.agriApplicableSubclauseId = agriApplicableSubclauseId;
	}
	private String baseValue;
	
	private String baseValueDisplay;
	/**
	 * @author Madan Mohan
	 */
	/**
	 * @author Madan Mohan
	 */
	
	private String regFeeDisplay;
	private double regFee;
	int stampId;
	private String userId;
	private String payableRegFeeDisplay;
	
	private String valTxnId;
	PropertyValuationDTO propDTO;
	
	
	
	
	public PropertyValuationDTO getPropDTO() {
		return propDTO;
	}
	public void setPropDTO(PropertyValuationDTO propDTO) {
		this.propDTO = propDTO;
	}
	public String getValTxnId() {
		return valTxnId;
	}
	public void setValTxnId(String valTxnId) {
		this.valTxnId = valTxnId;
	}
	public String getBaseValue() {
		return baseValue;
	}
	public void setBaseValue(String baseValue) {
		this.baseValue = baseValue;
	}
	private String firstName;
	/**
	 * @author Madan Mohan
	 */
	private String midName;
	/**
	 * @author Madan Mohan
	 */
	private String lastName;
	/**
	 * @author Madan Mohan
	 */
	private String sex;
	/**
	 * @author Madan Mohan
	 */
	private int age;
	
	
	private String dob;
	private String dobDay;
	private String dobMonth;
	private String dobYear;
	private String currentYear;
	private String typeOfTransaction; 
	public String getTypeOfTransaction() {
		return typeOfTransaction;
	}
	public void setTypeOfTransaction(String typeOfTransaction) {
		this.typeOfTransaction = typeOfTransaction;
	}
	/**
	 * @author Madan Mohan
	 */
	private String deedType;
	/**
	 * @author Madan Mohan
	 */
	private String hdnDeedType;
	/**
	 * @author Madan Mohan
	 */
	private String instrumentType;
	/**
	 * @author Madan Mohan
	 */
	private String exemptionType;
	/**
	 * @author Rishab
	 */
	private String firstPartName;
	/**
	 * @author Rishab
	 */
	private String secondPartName;
	/**
	 * @author Rishab
	 */
	private double stampDuty;
	
	private double  stampDutyEWS;
	private double  nagarPalikaDutyEWS;	
	private double  panchayatDutyEWS;	
	private double  upkarDutyEWS;
	private double  totalEWS;
	
	
	
	public double getStampDutyEWS() {
		return stampDutyEWS;
	}
	public void setStampDutyEWS(double stampDutyEWS) {
		this.stampDutyEWS = stampDutyEWS;
	}
	public double getNagarPalikaDutyEWS() {
		return nagarPalikaDutyEWS;
	}
	public void setNagarPalikaDutyEWS(double nagarPalikaDutyEWS) {
		this.nagarPalikaDutyEWS = nagarPalikaDutyEWS;
	}
	public double getPanchayatDutyEWS() {
		return panchayatDutyEWS;
	}
	public void setPanchayatDutyEWS(double panchayatDutyEWS) {
		this.panchayatDutyEWS = panchayatDutyEWS;
	}
	public double getUpkarDutyEWS() {
		return upkarDutyEWS;
	}
	public void setUpkarDutyEWS(double upkarDutyEWS) {
		this.upkarDutyEWS = upkarDutyEWS;
	}
	public double getTotalEWS() {
		return totalEWS;
	}
	public void setTotalEWS(double totalEWS) {
		this.totalEWS = totalEWS;
	}
	private String stampDutyDisplay;
	/**
	 * @author Rishab
	 */
	private double dutyAlreadyPaid;
	
	private String dutyAlreadyPaidDisplay;
	/**
	 * @author Rishab
	 */
	private String dutyAlreadyPaidFlag;
	/**
	 * @author Rishab
	 */
	
	private double regPaid;
	
	private String regPaidDisplay;
	
	/**
	 * @author Rishab
	 */
	
	private String exchangeFlag;
	
	private double shareValue;
	

	
	
	private double annualRent;
	
	private String annualRentDisplay;
	
	private String dutyPaidDisplay;
	
	private double dutyPaid;
	
	private double duty;
	
	
	private double  nagarPalikaDuty;
	
	private double  panchayatDuty;
	
	private double  upkarDuty;
	
   private String  nagarPalikaDutyDisplay;
	
	private String  panchayatDutyDisplay;
	
	private String  upkarDutyDisplay;
	
	/**
	 * @author Lavi
	 */
	private int fromReg=0;
	
	
	public String getFirstPartName() {
		return firstPartName;
	}
	public void setFirstPartName(String firstPartName) {
		this.firstPartName = firstPartName;
	}
	public String getSecondPartName() {
		return secondPartName;
	}
	public void setSecondPartName(String secondPartName) {
		this.secondPartName = secondPartName;
	}
	public double getNagarPalikaDuty() {
		return nagarPalikaDuty;
	}
	public void setNagarPalikaDuty(double nagarPalikaDuty) {
		this.nagarPalikaDuty = nagarPalikaDuty;
	}
	public double getPanchayatDuty() {
		return panchayatDuty;
	}
	public void setPanchayatDuty(double panchayatDuty) {
		this.panchayatDuty = panchayatDuty;
	}
	public double getUpkarDuty() {
		return upkarDuty;
	}
	public void setUpkarDuty(double upkarDuty) {
		this.upkarDuty = upkarDuty;
	}
	public double getAnnualRent() {
		return annualRent;
	}
	public void setAnnualRent(double annualRent) {
		this.annualRent = annualRent;
	}
	public double getDutyPaid() {
		return dutyPaid;
	}
	public void setDutyPaid(double dutyPaid) {
		this.dutyPaid = dutyPaid;
	}
	public double getDuty() {
		return duty;
	}
	public void setDuty(double duty) {
		this.duty = duty;
	}
	public double getRegPaid() {
		return regPaid;
	}
	public void setRegPaid(double regPaid) {
		this.regPaid = regPaid;
	}
	
	
	public double getShareValue() {
		return shareValue;
	}
	public void setShareValue(double shareValue) {
		this.shareValue = shareValue;
	}
	public String getExchangeFlag() {
		return exchangeFlag;
	}
	public void setExchangeFlag(String exchangeFlag) {
		this.exchangeFlag = exchangeFlag;
	}
	public String getDutyAlreadyPaidFlag() {
		return dutyAlreadyPaidFlag;
	}
	public void setDutyAlreadyPaidFlag(String dutyAlreadyPaidFlag) {
		this.dutyAlreadyPaidFlag = dutyAlreadyPaidFlag;
	}
	public double getDutyAlreadyPaid() {
		return dutyAlreadyPaid;
	}
	public void setDutyAlreadyPaid(double dutyAlreadyPaid) {
		this.dutyAlreadyPaid = dutyAlreadyPaid;
	}
	public double getStampDuty() {
		return stampDuty;
	}
	public void setStampDuty(double stampDuty) {
		this.stampDuty = stampDuty;
	}
	/**
	 * @author Madan Mohan
	 */
	private String other;
	/**
	 * @author Madan Mohan
	 */
	private String dateCalculation;
	/**
	 * @author Madan Mohan
	 */
	private int deedId;

	private int instrumentId;
	
	public int getInstrumentId() {
		return instrumentId;
	}
	public void setInstrumentId(int instrumentId) {
		this.instrumentId = instrumentId;
	}
	public int getDeedId() {
		return deedId;
	}
	public void setDeedId(int deedId) {
		this.deedId = deedId;
	}
	/**
	 * @author Madan Mohan
	 */
	private String formName;
	/**
	 * @author Madan Mohan
	 */
	private String actionName;
	/**
	 * @author Madan Mohan
	 */
	private 	Double total;
	
	private String totalDisplay;
	
	/**
	 * @return String
	 */
	public Double getTotal() {
		return total;
	}
	/**
	 * @param total
	 */
	public void setTotal(Double total) {
		this.total = total;
	}
	/**
	 * @return String
	 */
	public String getFormName() {
		return formName;
	}
	/**
	 * @param formName
	 */
	public void setFormName(String formName) {
		this.formName = formName;
	}
	/**
	 * @return String
	 */
	public String getActionName() {
		return actionName;
	}
	/**
	 * @param actionName
	 */
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	/**
	 * @return String
	 */

	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return String
	 */
	public String getMidName() {
		return midName;
	}
	/**
	 * @param midName
	 */
	public void setMidName(String midName) {
		this.midName = midName;
	}
	/**
	 * @return
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return String 
	 */
	public String getSex() {
		return sex;
	}
	/**
	 * @param sex
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	/**
	 * @return int
	 */
	public int getAge() {
		return age;
	}
	/**
	 * @param age
	 */
	public void setAge(int age) {
		this.age = age;
	}
	/**
	 * @return String
	 */
	public String getDeedType() {
		return deedType;
	}
	/**
	 * @param deedType
	 */
	public void setDeedType(String deedType) {
		this.deedType = deedType;
	}
	/**
	 * @return String
	 */
	public String getInstrumentType() {
		return instrumentType;
	}
	/**
	 * @param instrumentType
	 */
	public void setInstrumentType(String instrumentType) {
		this.instrumentType = instrumentType;
	}
	/**
	 * @return String
	 */
	public String getExemptionType() {
		return exemptionType;
	}
	/**
	 * @param exemptionType
	 */
	public void setExemptionType(String exemptionType) {
		this.exemptionType = exemptionType;
	}
	/**
	 * @return String
	 */

	/**
	 * @return String
	 */
	public String getOther() {
		return other;
	}
	public double getRegFee() {
		return regFee;
	}
	public void setRegFee(double regFee) {
		this.regFee = regFee;
	}
	/**
	 * @param other
	 */
	public void setOther(String other) {
		this.other = other;
	}
	/**
	 * @return String
	 */
	public String getDateCalculation() {
		return dateCalculation;
	}
	/**
	 * @param dateCalculation
	 */
	public void setDateCalculation(String dateCalculation) {
		this.dateCalculation = dateCalculation;
	}
	/**
	 * @return String
	 */
	public String getHdnDeedType() {
		return hdnDeedType;
	}
	/**
	 * @param hdnDeedType
	 */
	public void setHdnDeedType(String hdnDeedType) {
		this.hdnDeedType = hdnDeedType;
	}
	/**
	 * @return double
	 */
	/*public double getBaseValue() {
		return baseValue;
	}*/
	/**
	 * @param baseValue
	 */
	
	/*public void setBaseValue(double baseValue) {
		this.baseValue = baseValue;
	}*/
	public String getBaseValueDisplay() {
		return baseValueDisplay;
	}
	public void setBaseValueDisplay(String baseValueDisplay) {
		this.baseValueDisplay = baseValueDisplay;
	}
	public String getRegFeeDisplay() {
		return regFeeDisplay;
	}
	public void setRegFeeDisplay(String regFeeDisplay) {
		this.regFeeDisplay = regFeeDisplay;
	}
	public String getStampDutyDisplay() {
		return stampDutyDisplay;
	}
	public void setStampDutyDisplay(String stampDutyDisplay) {
		this.stampDutyDisplay = stampDutyDisplay;
	}
	public String getDutyAlreadyPaidDisplay() {
		return dutyAlreadyPaidDisplay;
	}
	public void setDutyAlreadyPaidDisplay(String dutyAlreadyPaidDisplay) {
		this.dutyAlreadyPaidDisplay = dutyAlreadyPaidDisplay;
	}
	public String getRegPaidDisplay() {
		return regPaidDisplay;
	}
	public void setRegPaidDisplay(String regPaidDisplay) {
		this.regPaidDisplay = regPaidDisplay;
	}
	public String getAnnualRentDisplay() {
		return annualRentDisplay;
	}
	public void setAnnualRentDisplay(String annualRentDisplay) {
		this.annualRentDisplay = annualRentDisplay;
	}
	public String getDutyPaidDisplay() {
		return dutyPaidDisplay;
	}
	public void setDutyPaidDisplay(String dutyPaidDisplay) {
		this.dutyPaidDisplay = dutyPaidDisplay;
	}
	public String getNagarPalikaDutyDisplay() {
		return nagarPalikaDutyDisplay;
	}
	public void setNagarPalikaDutyDisplay(String nagarPalikaDutyDisplay) {
		this.nagarPalikaDutyDisplay = nagarPalikaDutyDisplay;
	}
	public String getPanchayatDutyDisplay() {
		return panchayatDutyDisplay;
	}
	public void setPanchayatDutyDisplay(String panchayatDutyDisplay) {
		this.panchayatDutyDisplay = panchayatDutyDisplay;
	}
	public String getUpkarDutyDisplay() {
		return upkarDutyDisplay;
	}
	public void setUpkarDutyDisplay(String upkarDutyDisplay) {
		this.upkarDutyDisplay = upkarDutyDisplay;
	}
	public String getTotalDisplay() {
		return totalDisplay;
	}
	public void setTotalDisplay(String totalDisplay) {
		this.totalDisplay = totalDisplay;
	}
	public int getStampId() {
		return stampId;
	}
	public void setStampId(int stampId) {
		this.stampId = stampId;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getDobDay() {
		return dobDay;
	}
	public void setDobDay(String dobDay) {
		this.dobDay = dobDay;
	}
	public String getDobMonth() {
		return dobMonth;
	}
	public void setDobMonth(String dobMonth) {
		this.dobMonth = dobMonth;
	}
	public String getDobYear() {
		return dobYear;
	}
	public void setDobYear(String dobYear) {
		this.dobYear = dobYear;
	}
	public String getCurrentYear() {
		return currentYear;
	}
	public void setCurrentYear(String currentYear) {
		this.currentYear = currentYear;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getFromReg() {
		return fromReg;
	}
	public void setFromReg(int fromReg) {
		this.fromReg = fromReg;
	}
	
	
/*Added by Vinay*/
	private String deedCategoryId;

	private String deedCategoryName;
	
	private String propertyValuationRequired;

	private String valuationIdValidate;
	
	private String hvValId;

	private String valuationId;
	
	private String propertyName;
	
	private String marketValue;
	
	private String hvValIdFlag;
	
	private String addPropertyFlag;
	
	private String deleteValuationId;
	
	private String radioButton1;
	private String radioButton2;

	private String alreadyPaidRegFee;
	
	private String alreadyPaidStampDuty;
	
	private double payableRegFee;
	
	private double payableStampDuty;
	
	private double totalStampDuty;
	private double totalregFee;

	private double totalUpkar;

	private double totalNagarPalika;

	private double totalPanchyat;

	private double totalPayableStamp;

	private double totalPayableReg;

	private double totalPaidStamp;

	private double totalPaidReg;
	
	private double entireTotal;

 
	public double getTotalStampDuty() {
		return totalStampDuty;
	}
	public void setTotalStampDuty(double totalStampDuty) {
		this.totalStampDuty = totalStampDuty;
	}
	public double getTotalregFee() {
		return totalregFee;
	}
	public void setTotalregFee(double totalregFee) {
		this.totalregFee = totalregFee;
	}
	public double getTotalUpkar() {
		return totalUpkar;
	}
	public void setTotalUpkar(double totalUpkar) {
		this.totalUpkar = totalUpkar;
	}
	public double getTotalNagarPalika() {
		return totalNagarPalika;
	}
	public void setTotalNagarPalika(double totalNagarPalika) {
		this.totalNagarPalika = totalNagarPalika;
	}
	public double getTotalPanchyat() {
		return totalPanchyat;
	}
	public void setTotalPanchyat(double totalPanchyat) {
		this.totalPanchyat = totalPanchyat;
	}
	public double getTotalPayableStamp() {
		return totalPayableStamp;
	}
	public void setTotalPayableStamp(double totalPayableStamp) {
		this.totalPayableStamp = totalPayableStamp;
	}
	public double getTotalPayableReg() {
		return totalPayableReg;
	}
	public void setTotalPayableReg(double totalPayableReg) {
		this.totalPayableReg = totalPayableReg;
	}
	public double getTotalPaidStamp() {
		return totalPaidStamp;
	}
	public void setTotalPaidStamp(double totalPaidStamp) {
		this.totalPaidStamp = totalPaidStamp;
	}
	public double getTotalPaidReg() {
		return totalPaidReg;
	}
	public void setTotalPaidReg(double totalPaidReg) {
		this.totalPaidReg = totalPaidReg;
	}
	private ArrayList userValueList= new ArrayList();
	private HashMap<String,String> userValueMap=new HashMap<String,String>();
	private ArrayList propertyDetailsList=new ArrayList();
	private ArrayList valuationIdList=new ArrayList();
	
	public String getDeedCategoryId() {
		return deedCategoryId;
	}
	public void setDeedCategoryId(String deedCategoryId) {
		this.deedCategoryId = deedCategoryId;
	}
	public String getDeedCategoryName() {
		return deedCategoryName;
	}
	public void setDeedCategoryName(String deedCategoryName) {
		this.deedCategoryName = deedCategoryName;
	}
	public void setPropertyValuationRequired(String propertyValuationRequired) {
		this.propertyValuationRequired = propertyValuationRequired;
	}
	public String getPropertyValuationRequired() {
		return propertyValuationRequired;
	}
	public void setValuationIdValidate(String valuationIdValidate) {
		this.valuationIdValidate = valuationIdValidate;
	}
	public String getValuationIdValidate() {
		return valuationIdValidate;
	}
	public void setHvValId(String hvValId) {
		this.hvValId = hvValId;
	}
	public String getHvValId() {
		return hvValId;
	}
	public void setValuationId(String valuationId) {
		this.valuationId = valuationId;
	}
	public String getValuationId() {
		return valuationId;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	public String getPropertyName() {
		return propertyName;
	}
	public void setMarketValue(String marketValue) {
		this.marketValue = marketValue;
	}
	public String getMarketValue() {
		return marketValue;
	}
	public void setValuationIdList(ArrayList valuationIdList) {
		this.valuationIdList = valuationIdList;
	}
	public ArrayList getValuationIdList() {
		return valuationIdList;
	}
	public void setHvValIdFlag(String hvValIdFlag) {
		this.hvValIdFlag = hvValIdFlag;
	}
	public String getHvValIdFlag() {
		return hvValIdFlag;
	}
	public void setPropertyDetailsList(ArrayList propertyDetailsList) {
		this.propertyDetailsList = propertyDetailsList;
	}
	public ArrayList getPropertyDetailsList() {
		return propertyDetailsList;
	}
	public void setAddPropertyFlag(String addPropertyFlag) {
		this.addPropertyFlag = addPropertyFlag;
	}
	public String getAddPropertyFlag() {
		return addPropertyFlag;
	}
	public void setDeleteValuationId(String deleteValuationId) {
		this.deleteValuationId = deleteValuationId;
	}
	public String getDeleteValuationId() {
		return deleteValuationId;
	}
	public void setRadioButton1(String radioButton1) {
		this.radioButton1 = radioButton1;
	}
	public String getRadioButton1() {
		return radioButton1;
	}
	public void setUserValueMap(HashMap<String,String> userValueMap) {
		this.userValueMap = userValueMap;
	}
	public HashMap<String,String> getUserValueMap() {
		return userValueMap;
	}
	public void setUserValueList(ArrayList userValueList) {
		this.userValueList = userValueList;
	}
	public ArrayList getUserValueList() {
		return userValueList;
	}
	public void setAlreadyPaidRegFee(String alreadyPaidRegFee) {
		this.alreadyPaidRegFee = alreadyPaidRegFee;
	}
	public String getAlreadyPaidRegFee() {
		return alreadyPaidRegFee;
	}
	public void setAlreadyPaidStampDuty(String alreadyPaidStampDuty) {
		this.alreadyPaidStampDuty = alreadyPaidStampDuty;
	}
	public String getAlreadyPaidStampDuty() {
		return alreadyPaidStampDuty;
	}
	public void setPayableRegFee(double payableRegFee) {
		this.payableRegFee = payableRegFee;
	}
	public double getPayableRegFee() {
		return payableRegFee;
	}
	public void setPayableStampDuty(double payableStampDuty) {
		this.payableStampDuty = payableStampDuty;
	}
	public double getPayableStampDuty() {
		return payableStampDuty;
	}
	public void setEntireTotal(double entireTotal) {
		this.entireTotal = entireTotal;
	}
	public double getEntireTotal() {
		return entireTotal;
	}
	private double exchangeMV1;
	private double exchangeMV2;
	private String exchangeParty;
	public double getExchangeMV1() {
		return exchangeMV1;
	}
	public void setExchangeMV1(double exchangeMV1) {
		this.exchangeMV1 = exchangeMV1;
	}
	public double getExchangeMV2() {
		return exchangeMV2;
	}
	public void setExchangeMV2(double exchangeMV2) {
		this.exchangeMV2 = exchangeMV2;
	}
	public ArrayList getExchangeList1() {
		return exchangeList1;
	}
	public void setExchangeList1(ArrayList exchangeList1) {
		this.exchangeList1 = exchangeList1;
	}
	public ArrayList getExchangeList2() {
		return exchangeList2;
	}
	public void setExchangeList2(ArrayList exchangeList2) {
		this.exchangeList2 = exchangeList2;
	}
	public void setExchangeParty(String exchangeParty) {
		this.exchangeParty = exchangeParty;
	}
	public String getExchangeParty() {
		return exchangeParty;
	}
	public void setRadioButton2(String radioButton2) {
		this.radioButton2 = radioButton2;
	}
	public String getRadioButton2() {
		return radioButton2;
	}
	public void setNoOfSperatedPart(String noOfSperatedPart) {
		this.noOfSperatedPart = noOfSperatedPart;
	}
	public String getNoOfSperatedPart() {
		return noOfSperatedPart;
	}
	public void setPartitionMV(double partitionMV) {
		this.partitionMV = partitionMV;
	}
	public double getPartitionMV() {
		return partitionMV;
	}
	public void setMaxPartitionMv(double maxPartitionMv) {
		this.maxPartitionMv = maxPartitionMv;
	}
	public double getMaxPartitionMv() {
		return maxPartitionMv;
	}
	public void setAvgLandRevenue(String avgLandRevenue) {
		this.avgLandRevenue = avgLandRevenue;
	}
	public String getAvgLandRevenue() {
		return avgLandRevenue;
	}
	public void setTotalLandRevenue(double totalLandRevenue) {
		this.totalLandRevenue = totalLandRevenue;
	}
	public double getTotalLandRevenue() {
		return totalLandRevenue;
	}
	public void setMaxLandRevenue(double maxLandRevenue) {
		this.maxLandRevenue = maxLandRevenue;
	}
	public double getMaxLandRevenue() {
		return maxLandRevenue;
	}
	public void setLandRevenueList(ArrayList landRevenueList) {
		this.landRevenueList = landRevenueList;
	}
	public ArrayList getLandRevenueList() {
		return landRevenueList;
	}
	public void setLandRevenueId(int landRevenueId) {
		this.landRevenueId = landRevenueId;
	}
	public int getLandRevenueId() {
		return landRevenueId;
	}
	public void setLandRevenueIdString(String landRevenueIdString) {
		this.landRevenueIdString = landRevenueIdString;
	}
	public String getLandRevenueIdString() {
		return landRevenueIdString;
	}
	public void setRinPustikaVisible(String rinPustikaVisible) {
		this.rinPustikaVisible = rinPustikaVisible;
	}
	public String getRinPustikaVisible() {
		return rinPustikaVisible;
	}
	public void setRinPustikaCheck(String rinPustikaCheck) {
		this.rinPustikaCheck = rinPustikaCheck;
	}
	public String getRinPustikaCheck() {
		return rinPustikaCheck;
	}
	public void setRinPustikaCheckFlag(String rinPustikaCheckFlag) {
		this.rinPustikaCheckFlag = rinPustikaCheckFlag;
	}
	public String getRinPustikaCheckFlag() {
		return rinPustikaCheckFlag;
	}
	public void setMunicipalVisible(String municipalVisible) {
		this.municipalVisible = municipalVisible;
	}
	public String getMunicipalVisible() {
		return municipalVisible;
	}
	public void setMunicipalCheck(String municipalCheck) {
		this.municipalCheck = municipalCheck;
	}
	public String getMunicipalCheck() {
		return municipalCheck;
	}
	public void setMunicipalCheckFlag(String municipalCheckFlag) {
		this.municipalCheckFlag = municipalCheckFlag;
	}
	public String getMunicipalCheckFlag() {
		return municipalCheckFlag;
	}
	public void setMainDutyTxnId(String mainDutyTxnId) {
		this.mainDutyTxnId = mainDutyTxnId;
	}
	public String getMainDutyTxnId() {
		return mainDutyTxnId;
	}
	public void setDeedDescription(String deedDescription) {
		this.deedDescription = deedDescription;
	}
	public String getDeedDescription() {
		return deedDescription;
	}
	public void setInstDescription(String instDescription) {
		this.instDescription = instDescription;
	}
	public String getInstDescription() {
		return instDescription;
	}
	public void setExempDescription(String exempDescription) {
		this.exempDescription = exempDescription;
	}
	public String getExempDescription() {
		return exempDescription;
	}
	public void setExempId(String exempId) {
		this.exempId = exempId;
	}
	public String getExempId() {
		return exempId;
	}
	public void setExemptionDescpList(ArrayList exemptionDescpList) {
		this.exemptionDescpList = exemptionDescpList;
	}
	public ArrayList getExemptionDescpList() {
		return exemptionDescpList;
	}
	public void setExmepApplicable(String exmepApplicable) {
		this.exmepApplicable = exmepApplicable;
	}
	public String getExmepApplicable() {
		return exmepApplicable;
	}
	public void setExemppercent(String exemppercent) {
		this.exemppercent = exemppercent;
	}
	public String getExemppercent() {
		return exemppercent;
	}
	public void setExempName(String exempName) {
		this.exempName = exempName;
	}
	public String getExempName() {
		return exempName;
	}
	public void setExempNameId(String exempNameId) {
		this.exempNameId = exempNameId;
	}
	public String getExempNameId() {
		return exempNameId;
	}
	public void setExemptionList(ArrayList exemptionList) {
		this.exemptionList = exemptionList;
	}
	public ArrayList getExemptionList() {
		return exemptionList;
	}
	public void setExempCheckBox(String exempCheckBox) {
		this.exempCheckBox = exempCheckBox;
	}
	public String getExempCheckBox() {
		return exempCheckBox;
	}
	public void setSelectedExempId(String selectedExempId) {
		this.selectedExempId = selectedExempId;
	}
	public String getSelectedExempId() {
		return selectedExempId;
	}
	public void setDutyafterExemption(double dutyafterExemption) {
		this.dutyafterExemption = dutyafterExemption;
	}
	public double getDutyafterExemption() {
		return dutyafterExemption;
	}
	public void setDutyafterExemptionTotal(double dutyafterExemptionTotal) {
		this.dutyafterExemptionTotal = dutyafterExemptionTotal;
	}
	public double getDutyafterExemptionTotal() {
		return dutyafterExemptionTotal;
	}
	private ArrayList exchangeList1=new ArrayList();
	private ArrayList exchangeList2=new ArrayList();
	private ArrayList landRevenueList=new ArrayList();
	private String noOfSperatedPart;
	private int landRevenueId;
	private String landRevenueIdString;

	private double partitionMV;
	private double maxPartitionMv;
	private String avgLandRevenue;
	private double totalLandRevenue;
	private double maxLandRevenue;
	private String rinPustikaVisible;
	private String rinPustikaVisible1;
	private String rinPustikaVisible2;
	private String propertyId;
	
	private String  rinPustikaCheck;
	private String rinPustikaCheckFlag;
	private String municipalVisible;
	private String  municipalCheck;
	private String municipalCheckFlag;	
	private String familyVisible;
	private String  familyCheck;
	private String familyCheckFlag;
	private String  govCheck;
	private String govCheckFlag;
	private String govValue;
	public String getGovValue() {
		return govValue;
	}
	public void setGovValue(String govValue) {
		this.govValue = govValue;
	}
	public String getGovCheck() {
		return govCheck;
	}
	public void setGovCheck(String govCheck) {
		this.govCheck = govCheck;
	}
	public String getGovCheckFlag() {
		return govCheckFlag;
	}
	public void setGovCheckFlag(String govCheckFlag) {
		this.govCheckFlag = govCheckFlag;
	}
	public String getFamilyVisible() {
		return familyVisible;
	}
	public void setFamilyVisible(String familyVisible) {
		this.familyVisible = familyVisible;
	}
	public String getFamilyCheck() {
		return familyCheck;
	}
	public void setFamilyCheck(String familyCheck) {
		this.familyCheck = familyCheck;
	}
	public String getFamilyCheckFlag() {
		return familyCheckFlag;
	}
	public void setFamilyCheckFlag(String familyCheckFlag) {
		this.familyCheckFlag = familyCheckFlag;
	}
	private String mainDutyTxnId;
	private String deedDescription;
	private String instDescription;
	private String exempDescription;
	private String exempId;
	private String selectedExempId;
	private String exmepApplicable;
	private String exemppercent;
	private String exempName;
	private String exempNameId;
	private ArrayList exemptionList=new ArrayList();
	private String exempCheckBox;
	public ArrayList getRegFeeExemptionList() {
		return regFeeExemptionList;
	}
	public void setRegFeeExemptionList(ArrayList regFeeExemptionList) {
		this.regFeeExemptionList = regFeeExemptionList;
	}
	public ArrayList getRegFeeExemptionDiscriptionList() {
		return regFeeExemptionDiscriptionList;
	}
	public void setRegFeeExemptionDiscriptionList(
			ArrayList regFeeExemptionDiscriptionList) {
		this.regFeeExemptionDiscriptionList = regFeeExemptionDiscriptionList;
	}
	private ArrayList exemptionDescpList=new ArrayList();;   
	private double dutyafterExemption;
	private double dutyafterExemptionTotal;
	private String totalStampDutyDisplay;
	private String totalregFeeDisplay;
	private ArrayList regFeeExemptionList=new ArrayList();
	private ArrayList regFeeExemptionDiscriptionList=new ArrayList(); 
	private String totalUpkarDisplay;
	private double totalYear;
	private double totalMonth;
	public double getTotalYear() {
		return totalYear;
	}
	public void setTotalYear(double totalYear) {
		this.totalYear = totalYear;
	}
	public double getTotalMonth() {
		return totalMonth;
	}
	public void setTotalMonth(double totalMonth) {
		this.totalMonth = totalMonth;
	}
	private String totalNagarPalikaDisplay;

	private String dutyafterExemptionDisplay;
	private String partyAdded; 
	public String getTotalStampDutyDisplay() {
		return totalStampDutyDisplay;
	}
	public void setTotalStampDutyDisplay(String totalStampDutyDisplay) {
		this.totalStampDutyDisplay = totalStampDutyDisplay;
	}
	public String getTotalregFeeDisplay() {
		return totalregFeeDisplay;
	}
	public void setTotalregFeeDisplay(String totalregFeeDisplay) {
		this.totalregFeeDisplay = totalregFeeDisplay;
	}
	public String getTotalUpkarDisplay() {
		return totalUpkarDisplay;
	}
	public void setTotalUpkarDisplay(String totalUpkarDisplay) {
		this.totalUpkarDisplay = totalUpkarDisplay;
	}
	public String getTotalNagarPalikaDisplay() {
		return totalNagarPalikaDisplay;
	}
	public void setTotalNagarPalikaDisplay(String totalNagarPalikaDisplay) {
		this.totalNagarPalikaDisplay = totalNagarPalikaDisplay;
	}
	public String getTotalPanchyatDisplay() {
		return totalPanchyatDisplay;
	}
	public void setTotalPanchyatDisplay(String totalPanchyatDisplay) {
		this.totalPanchyatDisplay = totalPanchyatDisplay;
	}
	public String getTotalPayableStampDisplay() {
		return totalPayableStampDisplay;
	}
	public void setTotalPayableStampDisplay(String totalPayableStampDisplay) {
		this.totalPayableStampDisplay = totalPayableStampDisplay;
	}
	public String getTotalPayableRegDisplay() {
		return totalPayableRegDisplay;
	}
	public void setTotalPayableRegDisplay(String totalPayableRegDisplay) {
		this.totalPayableRegDisplay = totalPayableRegDisplay;
	}
	public String getTotalPaidStampDisplay() {
		return totalPaidStampDisplay;
	}
	public void setTotalPaidStampDisplay(String totalPaidStampDisplay) {
		this.totalPaidStampDisplay = totalPaidStampDisplay;
	}
	public String getTotalPaidRegDisplay() {
		return totalPaidRegDisplay;
	}
	public void setTotalPaidRegDisplay(String totalPaidRegDisplay) {
		this.totalPaidRegDisplay = totalPaidRegDisplay;
	}
	public String getEntireTotalDisplay() {
		return entireTotalDisplay;
	}
	public void setEntireTotalDisplay(String entireTotalDisplay) {
		this.entireTotalDisplay = entireTotalDisplay;
	}
	public void setDutyafterExemptionDisplay(String dutyafterExemptionDisplay) {
		this.dutyafterExemptionDisplay = dutyafterExemptionDisplay;
	}
	public String getDutyafterExemptionDisplay() {
		return dutyafterExemptionDisplay;
	}
	public void setPartyAdded(String partyAdded) {
		this.partyAdded = partyAdded;
	}
	public String getPartyAdded() {
		return partyAdded;
	}
	public void setRinPustikaVisible1(String rinPustikaVisible1) {
		this.rinPustikaVisible1 = rinPustikaVisible1;
	}
	public String getRinPustikaVisible1() {
		return rinPustikaVisible1;
	}
	public void setRinPustikaVisible2(String rinPustikaVisible2) {
		this.rinPustikaVisible2 = rinPustikaVisible2;
	}
	public String getRinPustikaVisible2() {
		return rinPustikaVisible2;
	}
	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}
	public String getPropertyId() {
		return propertyId;
	}
	public void setDutyafterExemptionDisplayTotal(
			String dutyafterExemptionDisplayTotal) {
		this.dutyafterExemptionDisplayTotal = dutyafterExemptionDisplayTotal;
	}
	public String getDutyafterExemptionDisplayTotal() {
		return dutyafterExemptionDisplayTotal;
	}
	public void setPropArea(double propArea) {
		this.propArea = propArea;
	}
	public double getPropArea() {
		return propArea;
	}
	private String totalPanchyatDisplay;

	private String totalPayableStampDisplay;

	private String totalPayableRegDisplay;

	private String totalPaidStampDisplay;

	private String totalPaidRegDisplay;
	
	private String entireTotalDisplay;
	
	private String dutyafterExemptionDisplayTotal;
	private double propArea; 
	
	public ArrayList getCancellationCategoryList() {
		return cancellationCategoryList;
	}
	public void setCancellationCategoryList(ArrayList cancellationCategoryList) {
		this.cancellationCategoryList = cancellationCategoryList;
	}
	public ArrayList getCancellationDeedList() {
		return cancellationDeedList;
	}
	public void setCancellationDeedList(ArrayList cancellationDeedList) {
		this.cancellationDeedList = cancellationDeedList;
	}
	public String getCancellationInstrument() {
		return cancellationInstrument;
	}
	public void setCancellationInstrument(String cancellationInstrument) {
		this.cancellationInstrument = cancellationInstrument;
	}
	private ArrayList cancellationCategoryList=new ArrayList();
	private ArrayList cancellationDeedList=new ArrayList();
	
	private String cancellationInstrument;
	private String cancellationDeedId;
	private String cancellationDeedName;
	private String cancellationCategoryName;
	private String cancellationCategoryId;
	private String cancellationInstrumentId;

	private String cancellationFlag;
	private String cancellationDeedDiscprtion;
	private String cancellationInstDiscrition;
	private String cancellationRadio;

	public String getCancellationDeedDiscprtion() {
		return cancellationDeedDiscprtion;
	}
	public void setCancellationDeedDiscprtion(String cancellationDeedDiscprtion) {
		this.cancellationDeedDiscprtion = cancellationDeedDiscprtion;
	}
	public String getCancellationInstDiscrition() {
		return cancellationInstDiscrition;
	}
	public void setCancellationInstDiscrition(String cancellationInstDiscrition) {
		this.cancellationInstDiscrition = cancellationInstDiscrition;
	}
	public String getCancellationRadio() {
		return cancellationRadio;
	}
	public void setCancellationRadio(String cancellationRadio) {
		this.cancellationRadio = cancellationRadio;
	}
	public String getCancellationDeedId() {
		return cancellationDeedId;
	}
	public void setCancellationDeedId(String cancellationDeedId) {
		this.cancellationDeedId = cancellationDeedId;
	}
	public String getCancellationDeedName() {
		return cancellationDeedName;
	}
	public void setCancellationDeedName(String cancellationDeedName) {
		this.cancellationDeedName = cancellationDeedName;
	}
	public String getCancellationCategoryName() {
		return cancellationCategoryName;
	}
	public void setCancellationCategoryName(String cancellationCategoryName) {
		this.cancellationCategoryName = cancellationCategoryName;
	}
	public String getCancellationCategoryId() {
		return cancellationCategoryId;
	}
	public void setCancellationCategoryId(String cancellationCategoryId) {
		this.cancellationCategoryId = cancellationCategoryId;
	}
	public String getCancellationInstrumentId() {
		return cancellationInstrumentId;
	}
	public void setCancellationInstrumentId(String cancellationInstrumentId) {
		this.cancellationInstrumentId = cancellationInstrumentId;
	}
	public void setCancellationFlag(String cancellationFlag) {
		this.cancellationFlag = cancellationFlag;
	}
	public String getCancellationFlag() {
		return cancellationFlag;
	}
	
	private String premium;
	
	private String additionalPremium;
	
	private String developmentCharges;
	
	private String otherCharges;
	private double regFeeafterExemp;
	private double regFeeafterExempTotal;
	private String landId;
	private String regFeeafterExempDisplay;
	private String regFeeafterExempTotalDisplay;
	private String mothlyRent;
	private String maintence;
	private String periodMonth;
	private String availExemption;
	private String availExemptionFlag;
	private String exemptionVisible;

	
	
	public ArrayList getRentArrayList() {
		return rentArrayList;
	}
	public void setRentArrayList(ArrayList rentArrayList) {
		this.rentArrayList = rentArrayList;
	}
	private String periodYear;
	private ArrayList rentArrayList=new ArrayList();

	public String getMothlyRent() {
		return mothlyRent;
	}
	public void setMothlyRent(String mothlyRent) {
		this.mothlyRent = mothlyRent;
	}
	public String getMaintence() {
		return maintence;
	}
	public void setMaintence(String maintence) {
		this.maintence = maintence;
	}
	public String getPeriodMonth() {
		return periodMonth;
	}
	public void setPeriodMonth(String periodMonth) {
		this.periodMonth = periodMonth;
	}
	public String getPeriodYear() {
		return periodYear;
	}
	public void setPeriodYear(String periodYear) {
		this.periodYear = periodYear;
	}
	public String getRegFeeafterExempDisplay() {
		return regFeeafterExempDisplay;
	}
	public void setRegFeeafterExempDisplay(String regFeeafterExempDisplay) {
		this.regFeeafterExempDisplay = regFeeafterExempDisplay;
	}
	public String getRegFeeafterExempTotalDisplay() {
		return regFeeafterExempTotalDisplay;
	}
	public void setRegFeeafterExempTotalDisplay(String regFeeafterExempTotalDisplay) {
		this.regFeeafterExempTotalDisplay = regFeeafterExempTotalDisplay;
	}
	public String getPremium() {
		return premium;
	}
	public void setPremium(String premium) {
		this.premium = premium;
	}
	public String getAdditionalPremium() {
		return additionalPremium;
	}
	public void setAdditionalPremium(String additionalPremium) {
		this.additionalPremium = additionalPremium;
	}
	public String getDevelopmentCharges() {
		return developmentCharges;
	}
	public void setDevelopmentCharges(String developmentCharges) {
		this.developmentCharges = developmentCharges;
	}
	public String getOtherCharges() {
		return otherCharges;
	}
	public void setOtherCharges(String otherCharges) {
		this.otherCharges = otherCharges;
	}
	public void setRegFeeafterExemp(double regFeeafterExemp) {
		this.regFeeafterExemp = regFeeafterExemp;
	}
	public double getRegFeeafterExemp() {
		return regFeeafterExemp;
	}
	public void setRegFeeafterExempTotal(double regFeeafterExempTotal) {
		this.regFeeafterExempTotal = regFeeafterExempTotal;
	}
	public double getRegFeeafterExempTotal() {
		return regFeeafterExempTotal;
	}
	public void setLandId(String landId) {
		this.landId = landId;
	}
	public String getLandId() {
		return landId;
	}
	public void setAvailExemption(String availExemption) {
		this.availExemption = availExemption;
	}
	public String getAvailExemption() {
		return availExemption;
	}
	public void setAvailExemptionFlag(String availExemptionFlag) {
		this.availExemptionFlag = availExemptionFlag;
	}
	public String getAvailExemptionFlag() {
		return availExemptionFlag;
	}
	public void setExemptionVisible(String exemptionVisible) {
		this.exemptionVisible = exemptionVisible;
	}
	public String getExemptionVisible() {
		return exemptionVisible;
	}

	public void setFromModule(String fromModule) {
		this.fromModule = fromModule;
	}
	public String getFromModule() {
		return fromModule;
	}
	private String fromModule;
	public String getDashSatmp() {
		return dashSatmp;
	}
	public void setDashSatmp(String dashSatmp) {
		this.dashSatmp = dashSatmp;
	}
	public String getDashUpkar() {
		return dashUpkar;
	}
	public void setDashUpkar(String dashUpkar) {
		this.dashUpkar = dashUpkar;
	}
	public String getDashMunicipal() {
		return dashMunicipal;
	}
	public void setDashMunicipal(String dashMunicipal) {
		this.dashMunicipal = dashMunicipal;
	}
	public String getDashJanpad() {
		return dashJanpad;
	}
	public void setDashJanpad(String dashJanpad) {
		this.dashJanpad = dashJanpad;
	}
	public String getDashTotal() {
		return dashTotal;
	}
	public void setDashTotal(String dashTotal) {
		this.dashTotal = dashTotal;
	}
	public String getDashReg() {
		return dashReg;
	}
	public void setDashReg(String dashReg) {
		this.dashReg = dashReg;
	}
	public String getDashPayReg() {
		return dashPayReg;
	}
	public void setDashPayReg(String dashPayReg) {
		this.dashPayReg = dashPayReg;
	}
	public String getDashPayTotal() {
		return dashPayTotal;
	}
	public void setDashPayTotal(String dashPayTotal) {
		this.dashPayTotal = dashPayTotal;
	}
	public void setPayableRegFeeDisplay(String payableRegFeeDisplay) {
		this.payableRegFeeDisplay = payableRegFeeDisplay;
	}
	public String getPayableRegFeeDisplay() {
		return payableRegFeeDisplay;
	}
	public String dashSatmp;
	public String dashUpkar;
	public String dashMunicipal;
	public String dashJanpad;
	public String dashTotal;
	public String dashReg;
	public String dashPayReg;
	public String dashPayTotal;
	public double paidStamp;
	public double paidReg;
	public double minusExempStamp;
	public double minusExempStampTotal;
	public double minusExempReg;
	public double minusExempRegTotal;

	public String minRegDisclaimerFlag;
	public String minStampDisclaimerFlag;
	public String minStamp;
	public String minRegFee;
	
	private String hvShare;
	private String hvShareFlag;
	
	public String getHvShare() {
		return hvShare;
	}
	public void setHvShare(String hvShare) {
		this.hvShare = hvShare;
	}
	public String getHvShareFlag() {
		return hvShareFlag;
	}
	public void setHvShareFlag(String hvShareFlag) {
		this.hvShareFlag = hvShareFlag;
	}
	public String getMinStamp() {
		return minStamp;
	}
	public void setMinStamp(String minStamp) {
		this.minStamp = minStamp;
	}
	public String getMinRegFee() {
		return minRegFee;
	}
	public void setMinRegFee(String minRegFee) {
		this.minRegFee = minRegFee;
	}
	public String getMinRegDisclaimerFlag() {
		return minRegDisclaimerFlag;
	}
	public void setMinRegDisclaimerFlag(String minRegDisclaimerFlag) {
		this.minRegDisclaimerFlag = minRegDisclaimerFlag;
	}
	public String getMinStampDisclaimerFlag() {
		return minStampDisclaimerFlag;
	}
	public void setMinStampDisclaimerFlag(String minStampDisclaimerFlag) {
		this.minStampDisclaimerFlag = minStampDisclaimerFlag;
	}
	public double getMinusExempStamp() {
		return minusExempStamp;
	}
	public void setMinusExempStamp(double minusExempStamp) {
		this.minusExempStamp = minusExempStamp;
	}
	public double getMinusExempStampTotal() {
		return minusExempStampTotal;
	}
	public void setMinusExempStampTotal(double minusExempStampTotal) {
		this.minusExempStampTotal = minusExempStampTotal;
	}
	public double getMinusExempReg() {
		return minusExempReg;
	}
	public void setMinusExempReg(double minusExempReg) {
		this.minusExempReg = minusExempReg;
	}
	public double getMinusExempRegTotal() {
		return minusExempRegTotal;
	}
	public void setMinusExempRegTotal(double minusExempRegTotal) {
		this.minusExempRegTotal = minusExempRegTotal;
	}
	public double getPaidStamp() {
		return paidStamp;
	}
	public void setPaidStamp(double paidStamp) {
		this.paidStamp = paidStamp;
	}
	public double getPaidReg() {
		return paidReg;
	}
	public void setPaidReg(double paidReg) {
		this.paidReg = paidReg;
	}

	public void setPayableDutyDisplay(String payableDutyDisplay) {
		this.payableDutyDisplay = payableDutyDisplay;
	}
	public String getPayableDutyDisplay() {
		return payableDutyDisplay;
	}
	private String payableDutyDisplay;
	
	private double totalGovValue;

	private String openPopUpRent;
	
	private String openPopUpPremium; 

	public double getTotalGovValue() {
		return totalGovValue;
	}
	public void setTotalGovValue(double totalGovValue) {
		this.totalGovValue = totalGovValue;
	}
	public String getOpenPopUpRent() {
		return openPopUpRent;
	}
	public void setOpenPopUpRent(String openPopUpRent) {
		this.openPopUpRent = openPopUpRent;
	}
	public String getOpenPopUpPremium() {
		return openPopUpPremium;
	}
	public void setOpenPopUpPremium(String openPopUpPremium) {
		this.openPopUpPremium = openPopUpPremium;
	}
	private String marketValueDisplay;




	public String getMarketValueDisplay() {
		return marketValueDisplay;
	}
	public void setMarketValueDisplay(String marketValueDisplay) {
		this.marketValueDisplay = marketValueDisplay;
	}
	public void setInddistrict(String inddistrict) {
		this.inddistrict = inddistrict;
	}
	public String getInddistrict() {
		return inddistrict;
	}
	public void setDistList(ArrayList distList) {
		this.distList = distList;
	}
	public ArrayList getDistList() {
		return distList;
	}
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}
	public String getDistrictId() {
		return districtId;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getDistrictName() {
		return districtName;
	}
	/*public void setPropImage1(FormFile propImage1) {
		this.propImage1 = propImage1;
	}
	public FormFile getPropImage1() {
		return propImage1;
	}*/
	public void setNameOfOfficial1(String nameOfOfficial1) {
		this.nameOfOfficial1 = nameOfOfficial1;
	}
	public String getNameOfOfficial1() {
		return nameOfOfficial1;
	}
	public void setNameOfOfficial2(String nameOfOfficial2) {
		this.nameOfOfficial2 = nameOfOfficial2;
	}
	public String getNameOfOfficial2() {
		return nameOfOfficial2;
	}
	public void setNameOfOfficial3(String nameOfOfficial3) {
		this.nameOfOfficial3 = nameOfOfficial3;
	}
	public String getNameOfOfficial3() {
		return nameOfOfficial3;
	}
	public void setNameOfOfficial4(String nameOfOfficial4) {
		this.nameOfOfficial4 = nameOfOfficial4;
	}
	public String getNameOfOfficial4() {
		return nameOfOfficial4;
	}
	public void setNameOfOfficial5(String nameOfOfficial5) {
		this.nameOfOfficial5 = nameOfOfficial5;
	}
	public String getNameOfOfficial5() {
		return nameOfOfficial5;
	}
	public void setNameOfOfficial6(String nameOfOfficial6) {
		this.nameOfOfficial6 = nameOfOfficial6;
	}
	public String getNameOfOfficial6() {
		return nameOfOfficial6;
	}
	public void setNameOfOfficial7(String nameOfOfficial7) {
		this.nameOfOfficial7 = nameOfOfficial7;
	}
	public String getNameOfOfficial7() {
		return nameOfOfficial7;
	}
	public void setNameOfOfficial8(String nameOfOfficial8) {
		this.nameOfOfficial8 = nameOfOfficial8;
	}
	public String getNameOfOfficial8() {
		return nameOfOfficial8;
	}
	public void setEfileFeeBeforeExemp(String efileFeeBeforeExemp) {
		this.efileFeeBeforeExemp = efileFeeBeforeExemp;
	}
	public String getEfileFeeBeforeExemp() {
		return efileFeeBeforeExemp;
	}
	public void setEfileFeeAfterExemp(String efileFeeAfterExemp) {
		this.efileFeeAfterExemp = efileFeeAfterExemp;
	}
	public String getEfileFeeAfterExemp() {
		return efileFeeAfterExemp;
	}
	public void setRadioResiComm3(String radioResiComm3) {
		this.radioResiComm3 = radioResiComm3;
	}
	public String getRadioResiComm3() {
		return radioResiComm3;
	}
	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}
	public String getOfficeName() {
		return officeName;
	}
	public void setOfficeList(ArrayList officeList) {
		this.officeList = officeList;
	}
	public ArrayList getOfficeList() {
		return officeList;
	}
	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}
	public String getOfficeId() {
		return officeId;
	}
	public void setOfficeNameSR(String officeNameSR) {
		this.officeNameSR = officeNameSR;
	}
	public String getOfficeNameSR() {
		return officeNameSR;
	}
	public void setSRName(String sRName) {
		SRName = sRName;
	}
	public String getSRName() {
		return SRName;
	}
	public void setDisplayStampVendorName(String displayStampVendorName) {
		this.displayStampVendorName = displayStampVendorName;
	}
	public String getDisplayStampVendorName() {
		return displayStampVendorName;
	}
	public void setDisplayStampVendorSeries(String displayStampVendorSeries) {
		this.displayStampVendorSeries = displayStampVendorSeries;
	}
	public String getDisplayStampVendorSeries() {
		return displayStampVendorSeries;
	}
	public void setDisplayCodeStamp(String displayCodeStamp) {
		this.displayCodeStamp = displayCodeStamp;
	}
	public String getDisplayCodeStamp() {
		return displayCodeStamp;
	}
	public void setDisplayDateStamp(String displayDateStamp) {
		this.displayDateStamp = displayDateStamp;
	}
	public String getDisplayDateStamp() {
		return displayDateStamp;
	}
	public void setEfileFeeAfterExempDisplay(String efileFeeAfterExempDisplay) {
		this.efileFeeAfterExempDisplay = efileFeeAfterExempDisplay;
	}
	public String getEfileFeeAfterExempDisplay() {
		return efileFeeAfterExempDisplay;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getFlag() {
		return flag;
	}
	public void setEileFeeDisplay(String eileFeeDisplay) {
		this.eileFeeDisplay = eileFeeDisplay;
	}
	public String getEileFeeDisplay() {
		return eileFeeDisplay;
	}
	public void setHidnRegTxnId(String hidnRegTxnId) {
		this.hidnRegTxnId = hidnRegTxnId;
	}
	public String getHidnRegTxnId() {
		return hidnRegTxnId;
	}
	public void setPropertyoutMP(String propertyoutMP) {
		this.propertyoutMP = propertyoutMP;
	}
	public String getPropertyoutMP() {
		return propertyoutMP;
	}
	

}
