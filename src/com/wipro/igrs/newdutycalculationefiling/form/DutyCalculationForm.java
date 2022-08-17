
package com.wipro.igrs.newdutycalculationefiling.form;


import java.util.ArrayList;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import com.wipro.igrs.newreginit.dto.CommonDTO;
import com.wipro.igrs.propertyvaluationefiling.dto.DutyCalculationDTO;
import com.wipro.igrs.propertyvaluationefiling.dto.ExemptionDTO;
import com.wipro.igrs.propertyvaluationefiling.dto.InstrumentsDTO;


/**
 * @since 14 jan 2008
 * File Name: DutyCalculationForm.java
 * @author Madan Mohan
 * @version 1.0
 * Created on 14 jan 2008
 *
 */
public class DutyCalculationForm extends ActionForm {

	/**
	 * @serial
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @author Madan Mohan
	 * 
	 * 
	 * 
	 */
	
	//bleow list is used to display grid data on duty page in efile
	
	private ArrayList<DutyCalculationDTO> listDto = new ArrayList<DutyCalculationDTO>();
	//end
	private String propertyoutMP;
	private String propertyoutMPFlag;
	private String exedate;
	private String efileId;
	private String flag;
	private String efileflag;
	private String flagmain;
	private String buttonflag;
	private String radioEFlag;
	private String radioPFlag;
	private String radioPhFlag;
	private String flagdisplay;
	private String physicalEstampErrorFlag;
	
	// Added by Gulrej
	private ArrayList<DutyCalculationDTO> physicalStampList = new ArrayList<DutyCalculationDTO>();
	private String estampValidated = "NO";
	private String physicalStampFlag = "NO";
	
		
	public String getPhysicalStampFlag() {
		return physicalStampFlag;
	}

	public void setPhysicalStampFlag(String physicalStampFlag) {
		this.physicalStampFlag = physicalStampFlag;
	}

	public String getEstampValidated() {
		return estampValidated;
	}

	public void setEstampValidated(String estampValidated) {
		this.estampValidated = estampValidated;
	}

	public String getPhysicalEstampErrorFlag() {
		return physicalEstampErrorFlag;
	}

	public void setPhysicalEstampErrorFlag(String physicalEstampErrorFlag) {
		this.physicalEstampErrorFlag = physicalEstampErrorFlag;
	}

	public String getRadioEFlag() {
		return radioEFlag;
	}

	public void setRadioEFlag(String radioEFlag) {
		this.radioEFlag = radioEFlag;
	}

	public String getRadioPFlag() {
		return radioPFlag;
	}

	public void setRadioPFlag(String radioPFlag) {
		this.radioPFlag = radioPFlag;
	}

	public String getButtonflag() {
		return buttonflag;
	}

	public void setButtonflag(String buttonflag) {
		this.buttonflag = buttonflag;
	}

	public String getFlagmain() {
		return flagmain;
	}

	public void setFlagmain(String flagmain) {
		this.flagmain = flagmain;
	}

	private String SRFlag;
	private String efileGenerateFlag;
	private String hiddenFlag;
	private String addFlag;
	private String hiddenFlagFirst;
	//added for add more physical stamp code
	private String stampVendorMore;
	private String codeStampMore;
	private String seriesNumberMore;
	private String dateOfStampMore;
	public String getStampVendorMore() {
		return stampVendorMore;
	}

	public void setStampVendorMore(String stampVendorMore) {
		this.stampVendorMore = stampVendorMore;
	}

	public String getCodeStampMore() {
		return codeStampMore;
	}

	public void setCodeStampMore(String codeStampMore) {
		this.codeStampMore = codeStampMore;
	}

	public String getSeriesNumberMore() {
		return seriesNumberMore;
	}

	public void setSeriesNumberMore(String seriesNumberMore) {
		this.seriesNumberMore = seriesNumberMore;
	}

	public String getDateOfStampMore() {
		return dateOfStampMore;
	}

	public void setDateOfStampMore(String dateOfStampMore) {
		this.dateOfStampMore = dateOfStampMore;
	}

	//end
	private String actionName;
	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	private String radioResiComm;
	private int deedID;
	//added for bank page
	private String availExemptionFlagEfile;
	private String exemptionFlagEfile;
 private String hidnRegTxnId;
	private String BankName;
	private String BankAddress;
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

	private String Country;
	private String State;
	private String District;
	private String Pin;
	private String PhoneNo;
	private String BankAName;
	private String BankDesg;
	private String BankPhn;
	private String BankEmail;
	
	
	
	private  String constantLabelValue  ;
	
	private  String moduleName  ;
	
	private String stampdate;
	
	private String hdnAmount;
	private String slotdate;
	private String todate;
	private FormFile propImage1;
	private String propImage1DocumentName;
	private String emptyFile;
	
	private FormFile propImage2;
	private String propImage2DocumentName;
	private String fileError;
	private String fileSizeError;
	private String fileSizeUploadError;
	private String uploadFileError;
	
	
	public String getPropImage2DocumentName() {
		return propImage2DocumentName;
	}

	public void setPropImage2DocumentName(String propImage2DocumentName) {
		this.propImage2DocumentName = propImage2DocumentName;
	}

	public FormFile getPropImage2() {
		return propImage2;
	}

	public void setPropImage2(FormFile propImage2) {
		this.propImage2 = propImage2;
	}

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

	//added by Shreeraj
	private int fromAdjudication=0;
	
	
	public int getFromAdjudication() {
		return fromAdjudication;
	}

	public void setFromAdjudication(int fromAdjudication) {
		this.fromAdjudication = fromAdjudication;
	}
	
	private DutyCalculationDTO dutyCalculationDTO =
		new DutyCalculationDTO();
	/**
	 * @author Madan Mohan
	 */
	private ArrayList instrumentDTOList = 
		new ArrayList();
	/**
	 * @author Madan Mohan
	 */
	private ArrayList exemptionDTOList = 
		new ArrayList();
	/**
	 * @author Madan Mohan
	 */
	private ArrayList selectedExemptionList = 
		new ArrayList();
	/**
	 * @author Madan Mohan
	 */
	private ArrayList dutycalculationDTOList =
		new ArrayList();
	/**
	 * @author Madan Mohan
	 */
	private InstrumentsDTO instDTO = 
		new InstrumentsDTO();
	/**
	 * @author Madan Mohan
	 */
	private ExemptionDTO exempDTO = 
		new ExemptionDTO();
	/**
	 * @author Madan Mohan
	 */
	private String baseValue;
	public ArrayList getDeedCategoryDTOList() {
		return deedCategoryDTOList;
	}

	public void setDeedCategoryDTOList(ArrayList deedCategoryDTOList) {
		this.deedCategoryDTOList = deedCategoryDTOList;
	}

	/* Added by Vinay */
	private ArrayList deedCategoryDTOList =
		new ArrayList();
	
	/* Adiition end*/
	public String getBaseValue() {
		return baseValue;
	}
	
	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public void setBaseValue(String baseValue) {
		this.baseValue = baseValue;
	}
	/**
	 * 
	 * @return InstrumentsDTO
	 */
	public InstrumentsDTO getInstDTO() {
		return instDTO;
	}
	/**
	 * 
	 * @param instDTO
	 */
	public void setInstDTO(InstrumentsDTO instDTO) {
		this.instDTO = instDTO;
	}
	/**
	 * 
	 * @return DutyCalculationDTO
	 */
	public DutyCalculationDTO getDutyCalculationDTO() {
		return dutyCalculationDTO;
	}
	/**
	 * 
	 * @param dutyCalculationDTO
	 */
	public void setDutyCalculationDTO(DutyCalculationDTO dutyCalculationDTO) {
		this.dutyCalculationDTO = dutyCalculationDTO;
	}
	/**
	 * 
	 * @return ArrayList
	 */
	public ArrayList getInstrumentDTOList() {
		return instrumentDTOList;
	}
	/**
	 * 
	 * @param instrumentDTOList
	 */
	public void setInstrumentDTOList(
			ArrayList instrumentDTOList) {
		this.instrumentDTOList = instrumentDTOList;
	}
	/**
	 * 
	 * @return ArrayList
	 */ 
	public ArrayList getDutycalculationDTOList() {
		return dutycalculationDTOList;
	}
	/**
	 * 
	 * @param dutycalculationDTOList
	 */
	public void setDutycalculationDTOList(
			ArrayList dutycalculationDTOList) {
		this.dutycalculationDTOList = dutycalculationDTOList;
	}
	/**
	 * 
	 * @return ExemptionDTO
	 */
	public ExemptionDTO getExempDTO() {
		return exempDTO;
	}
	/**
	 * 
	 * @param exempDTO
	 */
	public void setExempDTO(ExemptionDTO exempDTO) {
		this.exempDTO = exempDTO;
	}
	
	public String getHdnAmount() {
		return hdnAmount;
	}
	public void setHdnAmount(String hdnAmount) {
		this.hdnAmount = hdnAmount;
	}
	public String getConstantLabelValue() {
		return constantLabelValue;
	}
	public void setConstantLabelValue(String constantLabelValue) {
		this.constantLabelValue = "Y";
	}
	/**
	 * 
	 * @return ArrayList
	 */
	public ArrayList getExemptionDTOList() {
		return exemptionDTOList;
	}
	/**
	 * @param exemptionDTOList
	 */
	public void setExemptionDTOList(ArrayList exemptionDTOList) {
		this.exemptionDTOList = exemptionDTOList;
	}
	/**
	 * @return ArrayList
	 */
	public ArrayList getSelectedExemptionList() {
		return selectedExemptionList;
	}
	/**
	 * @param selectedExemptionList
	 */
	public void setSelectedExemptionList(
			ArrayList selectedExemptionList) {
		this.selectedExemptionList = selectedExemptionList;
	}

	public void setPropImage1(FormFile propImage1) {
		this.propImage1 = propImage1;
	}

	public FormFile getPropImage1() {
		return propImage1;
	}

	public void setBankName(String bankName) {
		BankName = bankName;
	}

	public String getBankName() {
		return BankName;
	}

	public void setPropImage1DocumentName(String propImage1DocumentName) {
		this.propImage1DocumentName = propImage1DocumentName;
	}

	public String getPropImage1DocumentName() {
		return propImage1DocumentName;
	}

	public void setHidnRegTxnId(String hidnRegTxnId) {
		this.hidnRegTxnId = hidnRegTxnId;
	}

	public String getHidnRegTxnId() {
		return hidnRegTxnId;
	}

	public void setStampdate(String stampdate) {
		this.stampdate = stampdate;
	}

	public String getStampdate() {
		return stampdate;
	}

	public void setAvailExemptionFlagEfile(String availExemptionFlagEfile) {
		this.availExemptionFlagEfile = availExemptionFlagEfile;
	}

	public String getAvailExemptionFlagEfile() {
		return availExemptionFlagEfile;
	}

	public void setDeedID(int deedID) {
		this.deedID = deedID;
	}

	public int getDeedID() {
		return deedID;
	}

	public void setRadioResiComm(String radioResiComm) {
		this.radioResiComm = radioResiComm;
	}

	public String getRadioResiComm() {
		return radioResiComm;
	}

	public void setHiddenFlag(String hiddenFlag) {
		this.hiddenFlag = hiddenFlag;
	}

	public String getHiddenFlag() {
		return hiddenFlag;
	}

	public void setFileError(String fileError) {
		this.fileError = fileError;
	}

	public String getFileError() {
		return fileError;
	}

	public void setUploadFileError(String uploadFileError) {
		this.uploadFileError = uploadFileError;
	}

	public String getUploadFileError() {
		return uploadFileError;
	}

	public void setAddFlag(String addFlag) {
		this.addFlag = addFlag;
	}

	public String getAddFlag() {
		return addFlag;
	}

	public void setHiddenFlagFirst(String hiddenFlagFirst) {
		this.hiddenFlagFirst = hiddenFlagFirst;
	}

	public String getHiddenFlagFirst() {
		return hiddenFlagFirst;
	}

	public void setSRFlag(String sRFlag) {
		SRFlag = sRFlag;
	}

	public String getSRFlag() {
		return SRFlag;
	}

	public void setEfileGenerateFlag(String efileGenerateFlag) {
		this.efileGenerateFlag = efileGenerateFlag;
	}

	public String getEfileGenerateFlag() {
		return efileGenerateFlag;
	}

	public void setFileSizeError(String fileSizeError) {
		this.fileSizeError = fileSizeError;
	}

	public String getFileSizeError() {
		return fileSizeError;
	}

	public void setFileSizeUploadError(String fileSizeUploadError) {
		this.fileSizeUploadError = fileSizeUploadError;
	}

	public String getFileSizeUploadError() {
		return fileSizeUploadError;
	}

	public void setListDto(ArrayList<DutyCalculationDTO> listDto) {
		this.listDto = listDto;
	}

	public ArrayList<DutyCalculationDTO> getListDto() {
		return listDto;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getFlag() {
		return flag;
	}

	public void setExedate(String exedate) {
		this.exedate = exedate;
	}

	public String getExedate() {
		return exedate;
	}

	public void setEfileId(String efileId) {
		this.efileId = efileId;
	}

	public String getEfileId() {
		return efileId;
	}

	public void setEmptyFile(String emptyFile) {
		this.emptyFile = emptyFile;
	}

	public String getEmptyFile() {
		return emptyFile;
	}

	public void setExemptionFlagEfile(String exemptionFlagEfile) {
		this.exemptionFlagEfile = exemptionFlagEfile;
	}

	public String getExemptionFlagEfile() {
		return exemptionFlagEfile;
	}

	public void setEfileflag(String efileflag) {
		this.efileflag = efileflag;
	}

	public String getEfileflag() {
		return efileflag;
	}

	public void setPropertyoutMP(String propertyoutMP) {
		this.propertyoutMP = propertyoutMP;
	}

	public String getPropertyoutMP() {
		return propertyoutMP;
	}

	public void setRadioPhFlag(String radioPhFlag) {
		this.radioPhFlag = radioPhFlag;
	}

	public String getRadioPhFlag() {
		return radioPhFlag;
	}

	public void setFlagdisplay(String flagdisplay) {
		this.flagdisplay = flagdisplay;
	}

	public String getFlagdisplay() {
		return flagdisplay;
	}

	public void setPropertyoutMPFlag(String propertyoutMPFlag) {
		this.propertyoutMPFlag = propertyoutMPFlag;
	}

	public String getPropertyoutMPFlag() {
		return propertyoutMPFlag;
	}

//Added by Gulrej
	
	public ArrayList<DutyCalculationDTO> getPhysicalStampList() {
		return physicalStampList;
	}
	public void setPhysicalStampList(ArrayList<DutyCalculationDTO> physicalStampList) {
		this.physicalStampList = physicalStampList;
	}

	public String paymentFlag;


	public String getPaymentFlag() {
		return paymentFlag;
	}

	public void setPaymentFlag(String paymentFlag) {
		this.paymentFlag = paymentFlag;
	}
	
	
}
