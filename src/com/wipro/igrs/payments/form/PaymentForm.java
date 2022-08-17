package com.wipro.igrs.payments.form;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts.action.ActionForm;
import org.apache.struts.validator.ValidatorForm;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;

import com.wipro.igrs.payment.dto.OnlinePaymentDto;
import com.wipro.igrs.payments.dto.ChallanDTO;
import com.wipro.igrs.payments.dto.OnlineDTO;
import com.wipro.igrs.payments.dto.PaymentDTO;

/**
 * ===========================================================================
 * File           :  PaymentForm.java
 * Description    :   Represents the Payment Form Bean Class
 * Author         :   Karteek P
 * Created Date   :   Feb 18, 2008

 * ===========================================================================
 */

public class PaymentForm extends ActionForm {
 
	private PaymentDTO paymentDTO = new PaymentDTO();
    private ChallanDTO payDTO = new ChallanDTO();
    private OnlineDTO onDTO = new OnlineDTO();
    //Added by Ajit For Double Verification
    private OnlinePaymentDto dto=new OnlinePaymentDto();
	private String onlineIp;
    
    //Added By Mohit Soni
    
    private ArrayList treasuryList;
    //Added by Ajit
	//Ajit
	private String statusTrs;
	private String statusDesc;
	private String urn;
    
    private String treasuryId;
    private String emailAddress;
    private String revSchemeHead;
    private String crnNumber;
    private String challanNumber;
    private String cin;
    private String brn;
    private String scrollNumber;
    private String scrollDate;
    private String trasactionDate;
	private String formName1;
    //added by SHreeraj
	
	private String amountbytrsry;
	private String challandatetrsry;
	private String checksum;
	private String enableCash;
	
	//added by sanjeev jha
	private String igrsadd1;
	private String igrsadd2;
	private String igrsadd3;
	private String igrsadd4;
	private String igrsadd5;
	private String state;
	private String pin;
	private String mobile;
	private String tin;
	private String remarks;
	private String ifsc;
	private String account_number;
	private String account_name;
	private String new1;
	private String new2;
	private String new3;
	
	
	
	public OnlinePaymentDto getDto() {
		return dto;
	}

	public void setDto(OnlinePaymentDto dto) {
		this.dto = dto;
	}

	public String getStatusTrs() {
		return statusTrs;
	}

	public void setStatusTrs(String statusTrs) {
		this.statusTrs = statusTrs;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public String getUrn() {
		return urn;
	}

	public void setUrn(String urn) {
		this.urn = urn;
	}

	public String getTin() {
		return tin;
	}

	public void setTin(String tin) {
		this.tin = tin;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getIfsc() {
		return ifsc;
	}

	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}

	public String getAccount_number() {
		return account_number;
	}

	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}

	public String getAccount_name() {
		return account_name;
	}

	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}

	public String getNew1() {
		return new1;
	}

	public void setNew1(String new1) {
		this.new1 = new1;
	}

	public String getNew2() {
		return new2;
	}

	public void setNew2(String new2) {
		this.new2 = new2;
	}

	public String getNew3() {
		return new3;
	}

	public void setNew3(String new3) {
		this.new3 = new3;
	}

	public String getIgrsadd1() {
		return igrsadd1;
	}

	public void setIgrsadd1(String igrsadd1) {
		this.igrsadd1 = igrsadd1;
	}

	public String getIgrsadd2() {
		return igrsadd2;
	}

	public void setIgrsadd2(String igrsadd2) {
		this.igrsadd2 = igrsadd2;
	}

	public String getIgrsadd3() {
		return igrsadd3;
	}

	public void setIgrsadd3(String igrsadd3) {
		this.igrsadd3 = igrsadd3;
	}

	public String getIgrsadd4() {
		return igrsadd4;
	}

	public void setIgrsadd4(String igrsadd4) {
		this.igrsadd4 = igrsadd4;
	}

	public String getIgrsadd5() {
		return igrsadd5;
	}

	public void setIgrsadd5(String igrsadd5) {
		this.igrsadd5 = igrsadd5;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEnableCash() {
		return enableCash;
	}

	public void setEnableCash(String enableCash) {
		this.enableCash = enableCash;
	}

	public String getChallandatetrsry() {
		return challandatetrsry;
	}

	public void setChallandatetrsry(String challandatetrsry) {
		this.challandatetrsry = challandatetrsry;
	}

	public String getAmountbytrsry() {
		return amountbytrsry;
	}

	public void setAmountbytrsry(String amountbytrsry) {
		this.amountbytrsry = amountbytrsry;
	}

	

	public String getChecksum() {
		return checksum;
	}

	public void setChecksum(String checksum) {
		this.checksum = checksum;
	}

	public String getChallanNumber() {
		return challanNumber;
	}

	public void setChallanNumber(String challanNumber) {
		this.challanNumber = challanNumber;
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public String getBrn() {
		return brn;
	}

	public void setBrn(String brn) {
		this.brn = brn;
	}

	public String getScrollNumber() {
		return scrollNumber;
	}

	public void setScrollNumber(String scrollNumber) {
		this.scrollNumber = scrollNumber;
	}

	public String getScrollDate() {
		return scrollDate;
	}

	public void setScrollDate(String scrollDate) {
		this.scrollDate = scrollDate;
	}

	public String getTrasactionDate() {
		return trasactionDate;
	}

	public void setTrasactionDate(String trasactionDate) {
		this.trasactionDate = trasactionDate;
	}

	public Map getValues() {
		return values;
	}

	private String challanNO;
	private String challanRefNo;
	private String challanDate;
	private String challancheck;
	private String challanentrAmt;
	private String challanBankId;
	private String challanBankName;
	private String challansearchRsptID;
	private String radioSelect;
	private String durationFrom;
	private String durationTo;
	private String cashDate;
	private double totAmt;
	private String receiptID;
	private String formName;
	private String actionName;
	private String amount;
	private String searchRsptID;
	private String entrAmt;
	private String crEntrAmt;
	private String payMode;
	private String funcId;
	private String loggedUser;
	private String forwardPath;
	private String parentAmount;
	private String parentTable;
	private String parentKey;
    private String transId;
    private String parentColumnName;
    private String spFlag;
    private String funName;
    private String tranDate;
    private String deposName;
    private String issuName;
    private String spLicenceNo;
    private String spAmt;
    private String spComsn;
    private double spamtd;
    private String spActBal;
    private String serviceId;
    private ArrayList paymentList = new ArrayList();
  //  private ArrayList delrow = new ArrayList();
    private String delrow;
    private final  Map values = new HashMap();
    private String chkChallan ;
    private int checkAvlblty = 0;
    
    private String parentOffId;
    private String parentOffName;
    private String parentDistId;
    private String parentDistName;
    private String parentRefId;
    private String revMjrHd;
   
	private String revMnrHd;
    private String revSbMjrHd;
    
    private String dwnldChallanAmt;
    private String dwnldChallanFirstName;
    private String dwnldChallanMiddleName;
    private String dwnldChallanLastName;
    private String dwnldChallanAddress;
    private String dwnldChallanUniqueId;
    private ArrayList dwnldChallanOfficeList=new ArrayList();
    private String dwnldChallanOfficeId;
    private String dwnldChallanOfficeName;
    private ArrayList dwnldChallanDistrictList=new ArrayList();
    private String dwnldChallanDistrictId;
    private String dwnldChallanDistrictName;
    
    private String onlineRefNo;
    private String onlineCinNo;
    private String onlineEntrAmt;
    private String onlineSearchRsptID;
    
    
    private int isBothNA=0;
    private int isNoneNA=0;
    private int isONA=0;
   
  
    //below created by Roopam
    
    private String dateParam;
    private String treasuryUrl;
    
    
    private String ePNR;
    
    
    
    
    
    
    
    private String hoa;
    
    
    
    
    
    public String getEPNR() {
		return ePNR;
	}

	public void setEPNR(String epnr) {
		ePNR = epnr;
	}

	public String getTreasuryUrl() {
		return treasuryUrl;
	}

	public void setTreasuryUrl(String treasuryUrl) {
		this.treasuryUrl = treasuryUrl;
	}

	public String getDateParam() {
		return dateParam;
	}

	public void setDateParam(String dateParam) {
		this.dateParam = dateParam;
	}

	public String getOnlineRefNo() {
		return onlineRefNo;
	}

	public void setOnlineRefNo(String onlineRefNo) {
		this.onlineRefNo = onlineRefNo;
	}

	public String getOnlineCinNo() {
		return onlineCinNo;
	}

	public void setOnlineCinNo(String onlineCinNo) {
		this.onlineCinNo = onlineCinNo;
	}

	public String getOnlineEntrAmt() {
		return onlineEntrAmt;
	}

	public void setOnlineEntrAmt(String onlineEntrAmt) {
		this.onlineEntrAmt = onlineEntrAmt;
	}

	public String getOnlineSearchRsptID() {
		return onlineSearchRsptID;
	}

	public void setOnlineSearchRsptID(String onlineSearchRsptID) {
		this.onlineSearchRsptID = onlineSearchRsptID;
	}

    
    public String getChallanRefNo() {
		return challanRefNo;
	}

	public void setChallanRefNo(String challanRefNo) {
		this.challanRefNo = challanRefNo;
	}

	public String getDwnldChallanUniqueId() {
		return dwnldChallanUniqueId;
	}

	public void setDwnldChallanUniqueId(String dwnldChallanUniqueId) {
		this.dwnldChallanUniqueId = dwnldChallanUniqueId;
	}

	public String getDwnldChallanAddress() {
		return dwnldChallanAddress;
	}

	public void setDwnldChallanAddress(String dwnldChallanAddress) {
		this.dwnldChallanAddress = dwnldChallanAddress;
	}

	public ArrayList getDwnldChallanOfficeList() {
		return dwnldChallanOfficeList;
	}

	public void setDwnldChallanOfficeList(ArrayList dwnldChallanOfficeList) {
		this.dwnldChallanOfficeList = dwnldChallanOfficeList;
	}

	public String getDwnldChallanOfficeId() {
		return dwnldChallanOfficeId;
	}

	public void setDwnldChallanOfficeId(String dwnldChallanOfficeId) {
		this.dwnldChallanOfficeId = dwnldChallanOfficeId;
	}

	public String getDwnldChallanOfficeName() {
		return dwnldChallanOfficeName;
	}

	public void setDwnldChallanOfficeName(String dwnldChallanOfficeName) {
		this.dwnldChallanOfficeName = dwnldChallanOfficeName;
	}

	public ArrayList getDwnldChallanDistrictList() {
		return dwnldChallanDistrictList;
	}

	public void setDwnldChallanDistrictList(ArrayList dwnldChallanDistrictList) {
		this.dwnldChallanDistrictList = dwnldChallanDistrictList;
	}

	public String getDwnldChallanDistrictId() {
		return dwnldChallanDistrictId;
	}

	public void setDwnldChallanDistrictId(String dwnldChallanDistrictId) {
		this.dwnldChallanDistrictId = dwnldChallanDistrictId;
	}

	public String getDwnldChallanDistrictName() {
		return dwnldChallanDistrictName;
	}

	public void setDwnldChallanDistrictName(String dwnldChallanDistrictName) {
		this.dwnldChallanDistrictName = dwnldChallanDistrictName;
	}

	public int getIsBothNA() {
		return isBothNA;
	}

	public void setIsBothNA(int isBothNA) {
		this.isBothNA = isBothNA;
	}

	public int getIsNoneNA() {
		return isNoneNA;
	}

	public void setIsNoneNA(int isNoneNA) {
		this.isNoneNA = isNoneNA;
	}

	public int getIsONA() {
		return isONA;
	}

	public void setIsONA(int isONA) {
		this.isONA = isONA;
	}

	public String getDwnldChallanFirstName() {
		return dwnldChallanFirstName;
	}

	public void setDwnldChallanFirstName(String dwnldChallanFirstName) {
		this.dwnldChallanFirstName = dwnldChallanFirstName;
	}

	public String getDwnldChallanMiddleName() {
		return dwnldChallanMiddleName;
	}

	public void setDwnldChallanMiddleName(String dwnldChallanMiddleName) {
		this.dwnldChallanMiddleName = dwnldChallanMiddleName;
	}

	public String getDwnldChallanLastName() {
		return dwnldChallanLastName;
	}

	public void setDwnldChallanLastName(String dwnldChallanLastName) {
		this.dwnldChallanLastName = dwnldChallanLastName;
	}

	public String getDwnldChallanAmt() {
		return dwnldChallanAmt;
	}

	public void setDwnldChallanAmt(String dwnldChallanAmt) {
		this.dwnldChallanAmt = dwnldChallanAmt;
	}

	public String getRevMjrHd() {
		return revMjrHd;
	}

	public void setRevMjrHd(String revMjrHd) {
		this.revMjrHd = revMjrHd;
	}

	public String getRevMnrHd() {
		return revMnrHd;
	}

	public void setRevMnrHd(String revMnrHd) {
		this.revMnrHd = revMnrHd;
	}

	public String getRevSbMjrHd() {
		return revSbMjrHd;
	}

	public void setRevSbMjrHd(String revSbMjrHd) {
		this.revSbMjrHd = revSbMjrHd;
	}

	public int getCheckAvlblty() {
		return checkAvlblty;
	}

	public void setCheckAvlblty(int checkAvlblty) {
		this.checkAvlblty = checkAvlblty;
	}

public String getChkChallan() {
    return chkChallan;
}

public void setChkChallan(String chkChallan) {
    this.chkChallan = chkChallan;
}

public void setValue(String key, Object value) {
       values.put(key, value);
   }

   public Object getValue(String key) {
       return values.get(key);
   }
   
    
	

	/**
	 * @return the challanNO
	 */
	public String getChallanNO() {
		return challanNO;
	}

	/**
	 * @param challanNO the challanNO to set
	 */
	public void setChallanNO(String challanNO) {
		this.challanNO = challanNO;
	}

	/**
	 * @return the challancheck
	 */
	public String getChallancheck() {
		return challancheck;
	}

	/**
	 * @param challancheck the challancheck to set
	 */
	public void setChallancheck(String challancheck) {
		this.challancheck = challancheck;
	}

	/**
	 * @return the challanDate
	 */
	public String getChallanDate() {
		return challanDate;
	}

	/**
	 * @param challanDate the challanDate to set
	 */
	public void setChallanDate(String challanDate) {
		this.challanDate = challanDate;
	}
	public String getChallanentrAmt() {
		return challanentrAmt;
	}

	public void setChallanentrAmt(String challanentrAmt) {
		this.challanentrAmt = challanentrAmt;
	}

	public String getChallanBankId() {
		return challanBankId;
	}

	public void setChallanBankId(String challanBankId) {
		this.challanBankId = challanBankId;
	}
	public String getChallansearchRsptID() {
		return challansearchRsptID;
	}

	public void setChallansearchRsptID(String challansearchRsptID) {
		this.challansearchRsptID = challansearchRsptID;
	}

	public String getChallanBankName() {
		return challanBankName;
	}

	public void setChallanBankName(String challanBankName) {
		this.challanBankName = challanBankName;
	}

	

	

	/**
	 * @return the durationFrom
	 */
	public String getDurationFrom() {
		return durationFrom;
	}

	/**
	 * @param durationFrom the durationFrom to set
	 */
	public void setDurationFrom(String durationFrom) {
		this.durationFrom = durationFrom;
	}

	/**
	 * @return the durationTo
	 */
	public String getDurationTo() {
		return durationTo;
	}

	/**
	 * @param durationTo the durationTo to set
	 */
	public void setDurationTo(String durationTo) {
		this.durationTo = durationTo;
	}

	

	/**
	 * @return the radioSelect
	 */
	public String getRadioSelect() {
		return radioSelect;
	}

	/**
	 * @param radioSelect the radioSelect to set
	 */
	public void setRadioSelect(String radioSelect) {
		this.radioSelect = radioSelect;
	}

	/**
	 * @return the paymentDTO
	 */
	public PaymentDTO getPaymentDTO() {
		return paymentDTO;
	}

	/**
	 * @param paymentDTO the paymentDTO to set
	 */
	public void setPaymentDTO(PaymentDTO paymentDTO) {
		this.paymentDTO = paymentDTO;
	}

	/**
	 * @return the cashDate
	 */
	public String getCashDate() {
		return cashDate;
	}

	/**
	 * @param cashDate the cashDate to set
	 */
	public void setCashDate(String cashDate) {
		this.cashDate = cashDate;
	}
	
	/**
	 * @param index
	 * @param vo
	 */
	public void setPaymentItems(int index, ChallanDTO vo){
		for (; index >= paymentList.size(); paymentList.add(new ChallanDTO()));
		/*System.out.println("in setter -->"+vo.getChallanDate());
		System.out.println(vo.getAmount());
		System.out.println(vo.getChallanNo());*/
		paymentList.set(index, vo);
	}

	/**
	 * @param index 
	 * @return PaymentDTO
	 */

	public ChallanDTO getPaymentItems(int index){
		for(; index >= paymentList.size(); paymentList.add(new ChallanDTO()));
		return (ChallanDTO) paymentList.get(index);
	}

	/**
	 * @return the paymentList
	 */
	public ArrayList getPaymentList() {
		return paymentList;
	}

	/**
	 * @param paymentList the paymentList to set
	 */
	public void setPaymentList(ArrayList paymentList) {
		this.paymentList = paymentList;
	}

	/**
	 * @return the payDTO
	 */
	public ChallanDTO getPayDTO() {
		return payDTO;
	}

	/**
	 * @param payDTO the payDTO to set
	 */
	public void setPayDTO(ChallanDTO payDTO) {
		this.payDTO = payDTO;
	}
/*
	 public void reset(ActionMapping mapping, HttpServletRequest request) {
		    this.radioSelect = null;
			this.durationFrom = null;
			this.durationTo = null;
			this.cashDate = null;
			this.challanDate = null;
			this.challancheck = null;
			this.getPaymentDTO().setReceiptID("");
			this.getPaymentDTO().setAmount(0.0);
			this.getPayDTO().setChallanNo("");
			this.getPayDTO().setAmount("");
			this.getPayDTO().setChallanDate("");
		    }
*/
	/**
	 * @return the totAmt
	 */
	public double getTotAmt() {
		return totAmt;
	}

	/**
	 * @param totAmt the totAmt to set
	 */
	public void setTotAmt(double totAmt) {
		this.totAmt = totAmt;
	}

	/**
	 * @return the delrow
	 */
	public String getDelrow() {
		return delrow;
	}

	/**
	 * @param delrow the delrow to set
	 */
	public void setDelrow(String delrow) {
		this.delrow = delrow;
	}

	public String getAmount() {
	    return amount;
	}

	public void setAmount(String amount) {
	    this.amount = amount;
	}
	 public String getTransId() {
			return transId;
		}

		public void setTransId(String transId) {
			this.transId = transId;
		}
		 public String getSpFlag() {
				return spFlag;
			}

			public void setSpFlag(String spFlag) {
				this.spFlag = spFlag;
			}

			


	public String getReceiptID() {
	    return receiptID;
	}

	public void setReceiptID(String receiptID) {
	    this.receiptID = receiptID;
	}

	public String getSearchRsptID() {
	    return searchRsptID;
	}

	public void setSearchRsptID(String searchRsptID) {
	    this.searchRsptID = searchRsptID;
	}
	
	public String getEntrAmt() {
		return entrAmt;
	}

	public void setEntrAmt(String entrAmt) {
		this.entrAmt = entrAmt;
	}

	public String getCrEntrAmt() {
		return crEntrAmt;
	}

	public void setCrEntrAmt(String crEntrAmt) {
		this.crEntrAmt = crEntrAmt;
	}
	
	 public String getPayMode() {
		return payMode;
	}

	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}

	public String getFormName() {
	    return formName;
	}

	public void setFormName(String formName) {
	    this.formName = formName;
	}
	 public String getFuncId() {
			return funcId;
		}

	public void setFuncId(String funcId) {
			this.funcId = funcId;
		}
	public String getSpAmt() {
		return spAmt;
	}

	public void setSpAmt(String spAmt) {
		this.spAmt = spAmt;
	}

	public String getSpComsn() {
		return spComsn;
	}

	public void setSpComsn(String spComsn) {
		this.spComsn = spComsn;
	}

	public double getSpamtd() {
		return spamtd;
	}

	public void setSpamtd(double spamtd) {
		this.spamtd = spamtd;
	}
	public String getSpActBal() {
			return spActBal;
		}

	public void setSpActBal(String spActBal) {
			this.spActBal = spActBal;
		}




		 public String getParentColumnName() {
				return parentColumnName;
			}

			public void setParentColumnName(String parentColumnName) {
				this.parentColumnName = parentColumnName;
			}

			
		 public String getLoggedUser() {
				return loggedUser;
			}

			public void setLoggedUser(String loggedUser) {
				this.loggedUser = loggedUser;
			}
			public String getForwardPath() {
				return forwardPath;
			}

			public void setForwardPath(String forwardPath) {
				this.forwardPath = forwardPath;
			}

			public String getParentAmount() {
				return parentAmount;
			}

			public void setParentAmount(String parentAmount) {
				this.parentAmount = parentAmount;
			}

			public String getParentTable() {
				return parentTable;
			}

			public void setParentTable(String parentTable) {
				this.parentTable = parentTable;
			}

			public String getParentKey() {
				return parentKey;
			}

			public void setParentKey(String parentKey) {
				this.parentKey = parentKey;
			}
		    public String getFunName() {
				return funName;
			}

			public void setFunName(String funName) {
				this.funName = funName;
			}

			public String getTranDate() {
				return tranDate;
			}

			public void setTranDate(String tranDate) {
				this.tranDate = tranDate;
			}

			public String getDeposName() {
				return deposName;
			}

			public void setDeposName(String deposName) {
				this.deposName = deposName;
			}

			public String getIssuName() {
				return issuName;
			}

			public void setIssuName(String issuName) {
				this.issuName = issuName;
			}
			 public String getSpLicenceNo() {
					return spLicenceNo;
				}

			public void setSpLicenceNo(String spLicenceNo) {
					this.spLicenceNo = spLicenceNo;
				}

	public String getActionName() {
	    return actionName;
	}

	public void setActionName(String actionName) {
	    this.actionName = actionName;
	}

	public String getParentOffId() {
		return parentOffId;
	}

	public void setParentOffId(String parentOffId) {
		this.parentOffId = parentOffId;
	}

	public String getParentOffName() {
		return parentOffName;
	}

	public void setParentOffName(String parentOffName) {
		this.parentOffName = parentOffName;
	}

	public String getParentDistId() {
		return parentDistId;
	}

	public void setParentDistId(String parentDistId) {
		this.parentDistId = parentDistId;
	}

	public String getParentDistName() {
		return parentDistName;
	}

	public void setParentDistName(String parentDistName) {
		this.parentDistName = parentDistName;
	}

	public String getParentRefId() {
		return parentRefId;
	}

	public void setParentRefId(String parentRefId) {
		this.parentRefId = parentRefId;
	}

	public OnlineDTO getOnDTO() {
		return onDTO;
	}

	public void setOnDTO(OnlineDTO onDTO) {
		this.onDTO = onDTO;
	}

	public void setTreasuryList(ArrayList treasuryList) {
		this.treasuryList = treasuryList;
	}

	public ArrayList getTreasuryList() {
		return treasuryList;
	}

	public void setTreasuryId(String treasuryId) {
		this.treasuryId = treasuryId;
	}

	public String getTreasuryId() {
		return treasuryId;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setRevSchemeHead(String revSchemeHead) {
		this.revSchemeHead = revSchemeHead;
	}

	public String getRevSchemeHead() {
		return revSchemeHead;
	}

	public void setCrnNumber(String crnNumber) {
		this.crnNumber = crnNumber;
	}

	public String getCrnNumber() {
		return crnNumber;
	}

	public void setFormName1(String formName1) {
		this.formName1 = formName1;
	}

	public String getFormName1() {
		return formName1;
	}

	public void setHoa(String hoa) {
		this.hoa = hoa;
	}

	public String getHoa() {
		return hoa;
	}

	public void setOnlineIp(String onlineIp) {
		this.onlineIp = onlineIp;
	}

	public String getOnlineIp() {
		return onlineIp;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	
	
	
	

}