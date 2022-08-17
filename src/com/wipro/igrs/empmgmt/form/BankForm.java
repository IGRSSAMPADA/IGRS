/**
 * Copyright (c) 2009-10 WIPRO INFOTECH. All Rights Reserved.
 *
 *  This software is the confidential and proprietary information of WIPRO
 *  ("Confidential Information"). You shall not
 *  disclose such Confidential Information and shall use it only in
 *  accordance with the terms of the license agreement you entered into
 *  with WIPRO.
 *
 * WIPRO MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE
 * SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, OR NON-INFRINGEMENT. WIPRO SHALL NOT BE
 * LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF
 * USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES.
 *
 * Customer specific copyright notice - NA
 */
package com.wipro.igrs.empmgmt.form;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.form.BaseForm;
import com.wipro.igrs.empmgmt.dto.BankDTO;
import com.wipro.igrs.empmgmt.dto.BankMstDTO;
import com.wipro.igrs.empmgmt.dto.FundDTO;
import com.wipro.igrs.empmgmt.dto.NomineeDTO;

/**
* 
* BankForm.java <br>
* BankForm <br>
* 
* @version 1.0
* 
* @author <b>WIPRO JAVA TEAM</b> <br>
*         Created on 12-Apr-2008 <br>
*         Last Modified on 28-Mar-2013
*/
@SuppressWarnings({ "rawtypes", "unchecked" })
public class BankForm extends BaseForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7329103067990466188L;

	/*
	 * Generated Methods
	 */
	private BankDTO bankDTO = new BankDTO();

	private FundDTO fundDTO = new FundDTO();

	private NomineeDTO nomineeDTO = new NomineeDTO();

	private ArrayList bankFundList;

	private ArrayList nomineeslist = new ArrayList();

	private String formName;

	private String actionName;
	
	private String type;

	private String accountNo;

	private String accountLocation;

	private int iFundIndex = -1;
	
	private int iNomineeIndex = -1;
	
	private String nameAsInBank;

	private String panNo;

	private String bankAccountNo;

	private String bankName;

	private String bankNameLabel;

	private String bankBranch;

	private String bankAddress;
	
	private String	nomineeName;

	private String	nomineeAddress;

	private String	relationWithNominee;

	private String	nomineeAge;

	private boolean bankDetailsPresent;
	
	private ArrayList selectedNominees;
	
	private NomineeDTO selectedNominee;
	
	private FundDTO selectedFund;
	
	private ArrayList<BankMstDTO> bankMasterList;
	
	private String bankIFSC;
	
	private String	nomineePercentage;
	
	public BankForm() {
		if(bankFundList == null) {
			bankFundList = new ArrayList();
		}
		if(selectedNominee == null) {
			selectedNominee = new NomineeDTO(); 
		}
		if(selectedFund == null) {
			selectedFund = new FundDTO(); 
		}
		if(bankMasterList == null) {
			bankMasterList = new ArrayList<BankMstDTO>();
		}
	}
	/**
	 * @return the bankDTO
	 */
	public BankDTO getBankDTO() {
		return bankDTO;
	}

	/**
	 * @param bankDTO
	 *            the bankDTO to set
	 */
	public void setBankDTO(BankDTO bankDTO) {
		this.bankDTO = bankDTO;
	}

	/**
	 * @return the nomineeslist
	 */
	public ArrayList getNomineeslist() {
		return nomineeslist;
	}

	/**
	 * @param nomineeslist
	 *            the nomineeslist to set
	 */
	public void setNomineeslist(ArrayList nomineeslist) {
		this.nomineeslist = nomineeslist;
	}

	/**
	 * @return the formName
	 */
	public String getFormName() {
		return formName;
	}

	/**
	 * @param formName
	 *            the formName to set
	 */
	public void setFormName(String formName) {
		this.formName = formName;
	}

	/**
	 * @return the actionName
	 */
	public String getActionName() {
		return actionName;
	}

	/**
	 * @param actionName
	 *            the actionName to set
	 */
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	/**
	 * @param index
	 * @param value
	 */
	public void setFundArrDTO(int index, FundDTO value) {

		for (; index >= bankFundList.size(); bankFundList.add(new FundDTO()))
			;
		bankFundList.add(index, value);
	}

	/**
	 * @param index
	 * @return
	 */
	public FundDTO getFundArrDTO(int index) {
		for (; index >= bankFundList.size(); bankFundList.add(new FundDTO()))
			;
		return (FundDTO) bankFundList.get(index);
	}

	/**
	 * @param index
	 * @param value
	 */
	public void setNomineeArrDTO(int index, NomineeDTO value) {

		for (; index >= nomineeslist.size(); nomineeslist.add(new NomineeDTO()))
			;
		nomineeslist.add(index, value);
	}

	/**
	 * @param index
	 * @return
	 */
	public NomineeDTO getNomineeArrDTO(int index) {
		for (; index >= nomineeslist.size(); nomineeslist.add(new NomineeDTO()))
			;
		return (NomineeDTO) nomineeslist.get(index);
	}

	/**
	 * Method validate
	 * 
	 * @param mapping
	 * @param request
	 * @return ActionErrors
	 */
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Method reset
	 * 
	 * @param mapping
	 * @param request
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// TODO Auto-generated method stub
	}

	public FundDTO getFundDTO() {
		return fundDTO;
	}

	public void setFundDTO(FundDTO fundDTO) {
		this.fundDTO = fundDTO;
	}

	public NomineeDTO getNomineeDTO() {
		return nomineeDTO;
	}

	public void setNomineeDTO(NomineeDTO nomineeDTO) {
		this.nomineeDTO = nomineeDTO;
	}

	/**
	 * @return the bankFundList
	 */
	public ArrayList getBankFundList() {
		return bankFundList;
	}

	/**
	 * @param bankFundList the bankFundList to set
	 */
	public void setBankFundList(ArrayList bankFundList) {
		this.bankFundList = bankFundList;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the accountNo
	 */
	public String getAccountNo() {
		return accountNo;
	}

	/**
	 * @param accountNo the accountNo to set
	 */
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	/**
	 * @return the accountLocation
	 */
	public String getAccountLocation() {
		return accountLocation;
	}

	/**
	 * @param accountLocation the accountLocation to set
	 */
	public void setAccountLocation(String accountLocation) {
		this.accountLocation = accountLocation;
	}

	/**
	 * @return the iFundIndex
	 */
	public int getiFundIndex() {
		return iFundIndex;
	}

	/**
	 * @param iFundIndex the iFundIndex to set
	 */
	public void setiFundIndex(int iFundIndex) {
		this.iFundIndex = iFundIndex;
	}

	/**
	 * @return the iNomineeIndex
	 */
	public int getiNomineeIndex() {
		return iNomineeIndex;
	}

	/**
	 * @param iNomineeIndex the iNomineeIndex to set
	 */
	public void setiNomineeIndex(int iNomineeIndex) {
		this.iNomineeIndex = iNomineeIndex;
	}

	/**
	 * @return the nameAsInBank
	 */
	public String getNameAsInBank() {
		return nameAsInBank;
	}

	/**
	 * @param nameAsInBank the nameAsInBank to set
	 */
	public void setNameAsInBank(String nameAsInBank) {
		this.nameAsInBank = nameAsInBank;
	}

	/**
	 * @return the panNo
	 */
	public String getPanNo() {
		return panNo;
	}

	/**
	 * @param panNo the panNo to set
	 */
	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}

	/**
	 * @return the bankAccountNo
	 */
	public String getBankAccountNo() {
		return bankAccountNo;
	}

	/**
	 * @param bankAccountNo the bankAccountNo to set
	 */
	public void setBankAccountNo(String bankAccountNo) {
		this.bankAccountNo = bankAccountNo;
	}

	/**
	 * @return the bankName
	 */
	public String getBankName() {
		return bankName;
	}

	/**
	 * @param bankName the bankName to set
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	/**
	 * @return the bankBranch
	 */
	public String getBankBranch() {
		return bankBranch;
	}

	/**
	 * @param bankBranch the bankBranch to set
	 */
	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}

	/**
	 * @return the bankAddress
	 */
	public String getBankAddress() {
		return bankAddress;
	}

	/**
	 * @param bankAddress the bankAddress to set
	 */
	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}
	/**
	 * @return the nomineeName
	 */
	public String getNomineeName() {
		return nomineeName;
	}
	/**
	 * @param nomineeName the nomineeName to set
	 */
	public void setNomineeName(String nomineeName) {
		this.nomineeName = nomineeName;
	}
	/**
	 * @return the nomineeAddress
	 */
	public String getNomineeAddress() {
		return nomineeAddress;
	}
	/**
	 * @param nomineeAddress the nomineeAddress to set
	 */
	public void setNomineeAddress(String nomineeAddress) {
		this.nomineeAddress = nomineeAddress;
	}
	/**
	 * @return the relationWithNominee
	 */
	public String getRelationWithNominee() {
		return relationWithNominee;
	}
	/**
	 * @param relationWithNominee the relationWithNominee to set
	 */
	public void setRelationWithNominee(String relationWithNominee) {
		this.relationWithNominee = relationWithNominee;
	}
	/**
	 * @return the nomineeAge
	 */
	public String getNomineeAge() {
		return nomineeAge;
	}
	/**
	 * @param nomineeAge the nomineeAge to set
	 */
	public void setNomineeAge(String nomineeAge) {
		this.nomineeAge = nomineeAge;
	}
	/**
	 * @return the bankDetailsPresent
	 */
	public boolean getBankDetailsPresent() {
		return bankDetailsPresent;
	}
	/**
	 * @return the bankDetailsPresent
	 */
	public boolean isBankDetailsPresent() {
		return bankDetailsPresent;
	}
	/**
	 * @param bankDetailsPresent the bankDetailsPresent to set
	 */
	public void setBankDetailsPresent(boolean bankDetailsPresent) {
		this.bankDetailsPresent = bankDetailsPresent;
	}
	/**
	 * @return the selectedNominees
	 */
	public ArrayList getSelectedNominees() {
		return selectedNominees;
	}
	/**
	 * @param selectedNominees the selectedNominees to set
	 */
	public void setSelectedNominees(ArrayList selectedNominees) {
		this.selectedNominees = selectedNominees;
	}
	/**
	 * @return the selectedNominee
	 */
	public NomineeDTO getSelectedNominee() {
		return selectedNominee;
	}
	/**
	 * @param selectedNominee the selectedNominee to set
	 */
	public void setSelectedNominee(NomineeDTO selectedNominee) {
		this.selectedNominee = selectedNominee;
	}
	/**
	 * @return the selectedFund
	 */
	public FundDTO getSelectedFund() {
		return selectedFund;
	}
	/**
	 * @param selectedFund the selectedFund to set
	 */
	public void setSelectedFund(FundDTO selectedFund) {
		this.selectedFund = selectedFund;
	}
	/**
	 * @return the bankMasterList
	 */
	public ArrayList<BankMstDTO> getBankMasterList() {
		return bankMasterList;
	}
	/**
	 * @param bankMasterList the bankMasterList to set
	 */
	public void setBankMasterList(ArrayList<BankMstDTO> bankMasterList) {
		this.bankMasterList = bankMasterList;
	}
	/**
	 * @return the bankNameLabel
	 */
	public String getBankNameLabel() {
		return bankNameLabel;
	}
	/**
	 * @param bankNameLabel the bankNameLabel to set
	 */
	public void setBankNameLabel(String bankNameLabel) {
		this.bankNameLabel = bankNameLabel;
	}
	/**
	 * @return the bankIFSC
	 */
	public String getBankIFSC() {
		return bankIFSC;
	}
	/**
	 * @param bankIFSC the bankIFSC to set
	 */
	public void setBankIFSC(String bankIFSC) {
		this.bankIFSC = bankIFSC;
	}
	/**
	 * @return the nomineePercentage
	 */
	public String getNomineePercentage() {
		return nomineePercentage;
	}
	/**
	 * @param nomineePercentage the nomineePercentage to set
	 */
	public void setNomineePercentage(String nomineePercentage) {
		this.nomineePercentage = nomineePercentage;
	}
	
}