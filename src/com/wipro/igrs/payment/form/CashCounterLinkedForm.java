package com.wipro.igrs.payment.form;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.struts.action.ActionForm;

import com.wipro.igrs.payment.dto.CashCounterDTO;
import com.wipro.igrs.payment.dto.CashCounterLinkedDTO;
import com.wipro.igrs.payment.dto.PhysicalChallanDTO;
/**
 * ===========================================================================
 * File : CashCounterLinkedForm.java 
 * Description : Represents the Cash Counter FormBean
 *  Author : vengamamba P 
 * Created Date : Feb 07, 2008
 * 
 * ===========================================================================
 */

public class CashCounterLinkedForm extends ActionForm {

	private CashCounterLinkedDTO cashdto = new CashCounterLinkedDTO();
	
	//below 4 for view
	private String minDate;
	private String transId;
	private String chdate;
	private String language;

	// variables related to applicant Details
	private String firstName;
	private String middleName;
	private String lastName;
	private String gender="M";
	private String age;
	private String fatherName;
	private String listID;
	private String idName;
	private String idNo;
	private String bankName;
	private String bankAddr;
	private String offName;
	private String offId;
	private String distId;
	private String distName;
	private String officeName;
	private String revMjrHd;
	private String revMnrHd;
	private String revSubMjrHd;
	private String posRefNo;
	private String oldNewReceipt;
	
	//variables related to Application details
	private String listService;
	private String funName;
	private HashMap serviceMap =new HashMap();
	private HashMap serviceMapDetails =new HashMap();
	private String formkeyId;
	private String deleteService;
	private String delArry;
   private String existingCash ;
	
	

	//variables related to cash information
	private String cashAmt;
	private String servFee;
	private String drpFee;
	private String diff;
	private String combId;
	//akansha
	private String transactionId;
	private String checkerRegInitId;
	
	//variables related to challan
	private String checkboxAssociateChallan;
	private String chkChallanDel;
	//private String scrollNumber;
	private String remarks;
	//private String bankId;
	private String challanDate;
	//private String amount;
	
	//variables related to hidden action values
	 private String checkButton = "";
	 private String ccheck;
	 private String delrow;
	 private String actDelRow;
	 private String selectType;
	private String cashForm;
	private String actionValue;
	private String actionName;
	private String actionNext;
	private int iscalculated = 0;
		
	//variable related to challanpay list
	private String temp = null;
	private String chList;
   
	private PhysicalChallanDTO challanDTO = new PhysicalChallanDTO();
   
	private String rowIndex;
    //variables related to total amount,arraylist for challan
	private double totAmt;
	private ArrayList paymentList = new ArrayList();
	private ArrayList displayList = new ArrayList();
	
	private String empName;
	
	
    public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getMinDate() {
		return minDate;
	}

	public void setMinDate(String minDate) {
		this.minDate = minDate;
	}

	public String getTransId() {
		return transId;
	}

	public void setTransId(String transId) {
		this.transId = transId;
	}

	public String getChdate() {
		return chdate;
	}

	public void setChdate(String chdate) {
		this.chdate = chdate;
	}

	public String getOldNewReceipt() {
		return oldNewReceipt;
	}

	public void setOldNewReceipt(String oldNewReceipt) {
		this.oldNewReceipt = oldNewReceipt;
	}

	public String getPosRefNo() {
		return posRefNo;
	}

	public void setPosRefNo(String posRefNo) {
		this.posRefNo = posRefNo;
	}

	public String getDistName() {
		return distName;
	}

	public void setDistName(String distName) {
		this.distName = distName;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public int getIscalculated() {
		return iscalculated;
	}

	public void setIscalculated(int iscalculated) {
		this.iscalculated = iscalculated;
	}

	public ArrayList getDisplayList() {
		return displayList;
	}

	public void setDisplayList(ArrayList displayList) {
		this.displayList = displayList;
	}

	public String getCashForm() {
		return cashForm;
	}

	public void setCashForm(String cashForm) {
		this.cashForm = cashForm;
	}

	public String getActionValue() {
		return actionValue;
	}

	public void setActionValue(String actionValue) {
		this.actionValue = actionValue;
	}
	public String getActionNext() {
		return actionNext;
	}

	public void setActionNext(String actionNext) {
		this.actionNext = actionNext;
	}
	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
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

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String name) {
		fatherName = name;
	}

	public String getListID() {
		return listID;
	}

	public void setListID(String listID) {
		this.listID = listID;
	}
	public String getOffId() {
		return offId;
	}

	public void setOffId(String offId) {
		this.offId = offId;
	}

	public String getDistId() {
		return distId;
	}

	public void setDistId(String distId) {
		this.distId = distId;
	}

	
	public String getIdName() {
		return idName;
	}

	public void setIdName(String idName) {
		this.idName = idName;
	}
	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public String getFunName() {
		return funName;
	}

	public void setFunName(String funName) {
		this.funName = funName;
	}
	public HashMap getServiceMap() {
		return serviceMap;
	}

	public void setServiceMap(HashMap serviceMap) {
		this.serviceMap = serviceMap;
	}
	
	public HashMap getServiceMapDetails() {
		return serviceMapDetails;
	}

	public void setServiceMapDetails(HashMap serviceMapDetails) {
		this.serviceMapDetails = serviceMapDetails;
	}
	public String getFormkeyId() {
		return formkeyId;
	}

	public void setFormkeyId(String formkeyId) {
		this.formkeyId = formkeyId;
	}
	public String getDeleteService() {
		return deleteService;
	}

	public void setDeleteService(String deleteService) {
		this.deleteService = deleteService;
	}
	public String getDelArry() {
		return delArry;
	}

	public void setDelArry(String delArry) {
		this.delArry = delArry;
	}
	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankAddr() {
		return bankAddr;
	}

	public void setBankAddr(String bankAddr) {
		this.bankAddr = bankAddr;
	}
	public String getOffName() {
		return offName;
	}

	public void setOffName(String offName) {
		this.offName = offName;
	}
	public String getDrpFee() {
		return drpFee;
	}

	public void setDrpFee(String drpFee) {
		this.drpFee = drpFee;
	}

	public String getDiff() {
		return diff;
	}

	public void setDiff(String diff) {
		this.diff = diff;
	}
	public String getListService() {
		return listService;
	}

	public void setListService(String listService) {
		this.listService = listService;
	}

	public String getCashAmt() {
		return cashAmt;
	}

	public void setCashAmt(String cashAmt) {
		this.cashAmt = cashAmt;
	}
	public String getServFee() {
		return servFee;
	}

	public void setServFee(String servFee) {
		this.servFee = servFee;
	}

	public String getCombId() {
		return combId;
	}

	public void setCombId(String combId) {
		this.combId = combId;
	}

	public String getCheckboxAssociateChallan() {
		return checkboxAssociateChallan;
	}

	public void setCheckboxAssociateChallan(String checkboxAssociateChallan) {
		this.checkboxAssociateChallan = checkboxAssociateChallan;
	}

	public String getChkChallanDel() {
		return chkChallanDel;
	}

	public void setChkChallanDel(String chkChallanDel) {
		this.chkChallanDel = chkChallanDel;
	}
	

	//public String getScrollNumber() {
	//	return scrollNumber;
	//}

	//public void setScrollNumber(String scrollNO) {
	//	this.scrollNumber = scrollNO;
	//}

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

	public String getRevSubMjrHd() {
		return revSubMjrHd;
	}

	public void setRevSubMjrHd(String revSubMjrHd) {
		this.revSubMjrHd = revSubMjrHd;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public CashCounterLinkedDTO getCashdto() {
		return cashdto;
	}

	public void setCashdto(CashCounterLinkedDTO cashdto) {
		this.cashdto = cashdto;
	}
	public String getTemp()
	{
		return temp;
	}

	public void setTemp(String temp)
	{
		this.temp = temp;
	}

	public PhysicalChallanDTO getChallanDTO() {
		return challanDTO;
	}



	public void setChallanDTO(PhysicalChallanDTO challanDTO) {
		this.challanDTO = challanDTO;
	}



	public String getRowIndex() {
		return rowIndex;
	}



	public void setRowIndex(String rowIndex) {
		this.rowIndex = rowIndex;
	}

	public String getChList() {
		return chList;
	}

	public void setChList(String chList) {
		this.chList = chList;
	}

	//public String getBankId() {
//		return bankId;
///	}

	//public void setBankId(String bankId) {
	//	this.bankId = bankId;
	//}

	public String getChallanDate() {
		return challanDate;
	}

	public void setChallanDate(String challanDate) {
		this.challanDate = challanDate;
	}

//	public String getAmount() {
//		return amount;
//	}

//	public void setAmount(String amount) {
//		this.amount = amount;
//	}
	
	/**
	 * @param index
	 * @param vo
	 */
	public void setPaymentItems(int index, PhysicalChallanDTO vo){
		for (; index >= paymentList.size(); paymentList.add(new PhysicalChallanDTO()));
		System.out.println("in setter -->"+vo.getChallanDate());
		System.out.println(vo.getAmount());
		System.out.println(vo.getScrollNumber());
		paymentList.set(index, vo);
	}

	/**
	 * @param index 
	 * @return PaymentDTO
	 */

	public PhysicalChallanDTO getPaymentItems(int index){
		for(; index >= paymentList.size(); paymentList.add(new PhysicalChallanDTO()));
		return (PhysicalChallanDTO) paymentList.get(index);
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

	public String getCheckButton() {
		return checkButton;
	}

	public void setCheckButton(String checkButton) {
		this.checkButton = checkButton;
	}

	public String getCcheck() {
		return ccheck;
	}

	public void setCcheck(String ccheck) {
		this.ccheck = ccheck;
	}

	public String getDelrow() {
		return delrow;
	}

	public void setDelrow(String delrow) {
		this.delrow = delrow;
	}

	public String getActDelRow() {
		return actDelRow;
	}

	public void setActDelRow(String actDelRow) {
		this.actDelRow = actDelRow;
	}

	public String getSelectType() {
		return selectType;
	}

	public void setSelectType(String selectType) {
		this.selectType = selectType;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setCheckerRegInitId(String checkerRegInitId) {
		this.checkerRegInitId = checkerRegInitId;
	}

	public String getCheckerRegInitId() {
		return checkerRegInitId;
	}

	public void setExistingCash(String existingCash) {
		this.existingCash = existingCash;
	}

	public String getExistingCash() {
		return existingCash;
	}

}
