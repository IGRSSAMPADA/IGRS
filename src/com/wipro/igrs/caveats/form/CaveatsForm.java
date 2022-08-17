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
package com.wipro.igrs.caveats.form;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.struts.action.ActionForm;

import com.wipro.igrs.caveats.dto.CaveatsDTO;
import com.wipro.igrs.caveats.dto.OTTDetailDTO;
import com.wipro.igrs.estamping.dto.EstampDetailsDTO;
/**
* 
* CaveatsForm.java <br>
* CaveatsForm <br>
* 
* @version 1.0
* 
* @author <b>WIPRO JAVA TEAM</b> <br>
*         Created on 12-Apr-2008 <br>
*         Last Modified on 28-Mar-2013
*/
@SuppressWarnings({ "rawtypes" })
public class CaveatsForm extends ActionForm {
    /**
	 * 
	 */
	
	private static final long serialVersionUID = -6612848304669349049L;

	private CaveatsDTO caveatsDTO=new CaveatsDTO();
  private String actionName;
  private String csrfToken;
    private String  typeOfCaveat;
    private String  bankCourtName;
    private String  bankCourtRepName;
    private String  bankCourtAddress;
    private String  cityDistrict;
    private String  bankCourtCountry;
    private String  bankCourtStateName;
    private String bankCourtPostalCode;
    private String  bankCourtPhoneNumber;
    private String  caveatDetails;
    private String  stayOrderNo;
    private String  stayOrderDetails;
    private String  stayOrderYesNo;
    private String  remarks;
    private String language;
    private String  registrationNumber;
    private String  docUrl;
    private String flag;
    private String caveatType;
    private String caveatId;
    private int referenceID;
    private String funID;
    private String fromDate;
    private String toDate;

    private String stayOrderStartDate;
    private String stayOrderUptoDate;
    private String loanDueDate;
    private ArrayList searchResultList;
    private ArrayList selectedList;
    private String errorMessage;
    private String protestRelId;
    private String paymentType;
    private String paymentSeqId;
    
	public String getPaymentSeqId() {
		return paymentSeqId;
	}
	public void setPaymentSeqId(String paymentSeqId) {
		this.paymentSeqId = paymentSeqId;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public String getProtestRelId() {
		return protestRelId;
	}
	public void setProtestRelId(String protestRelId) {
		this.protestRelId = protestRelId;
	}
	public String getFunID() {
		return funID;
	}
	public void setFunID(String funID) {
		this.funID = funID;
	}
	public HashMap getMapPropertyTransPartyDisp() {
		return mapPropertyTransPartyDisp;
	}
	public void setMapPropertyTransPartyDisp(HashMap mapPropertyTransPartyDisp) {
		this.mapPropertyTransPartyDisp = mapPropertyTransPartyDisp;
	}
	private HashMap mapPropertyTransPartyDisp = new HashMap();
  
    private String validityOTT;
    private String bankRoleName;

    private ArrayList<OTTDetailDTO> ottSearchResultList;
    
    public void setTypeOfCaveat(String typeOfCaveat) {
        this.typeOfCaveat = typeOfCaveat;
    }
    public String getTypeOfCaveat() {
        return typeOfCaveat;
    }
    public void setBankCourtName(String bankCourtName) {
        this.bankCourtName = bankCourtName;
    }
    public String getBankCourtName() {
        return bankCourtName;
    }
    public void setBankCourtRepName(String bankCourtRepName) {
        this.bankCourtRepName = bankCourtRepName;
    }
    public String getBankCourtRepName() {
        return bankCourtRepName;
    }
   
    public void setBankCourtAddress(String bankCourtAddress) {
        this.bankCourtAddress = bankCourtAddress;
    }
    public String getBankCourtAddress() {
        return bankCourtAddress;
    }
    public void setCityDistrict(String cityDistrict) {
        this.cityDistrict = cityDistrict;
    }
    public String getCityDistrict() {
        return cityDistrict;
    }
    public void setBankCourtCountry(String bankCourtCountry) {
        this.bankCourtCountry = bankCourtCountry;
    }
    public String getBankCourtCountry() {
        return bankCourtCountry;
    }
    public void setBankCourtStateName(String bankCourtStateName) {
        this.bankCourtStateName = bankCourtStateName;
    }
    public String getBankCourtStateName() {
        return bankCourtStateName;
    }

    public void setBankCourtPostalCode(String bankCourtPostalCode) {
        this.bankCourtPostalCode = bankCourtPostalCode;
    }

    public String getBankCourtPostalCode() {
        return bankCourtPostalCode;
    }

    public void setBankCourtPhoneNumber(String bankCourtPhoneNumber) {
        this.bankCourtPhoneNumber = bankCourtPhoneNumber;
    }

    public String getBankCourtPhoneNumber() {
        return bankCourtPhoneNumber;
    }

    public void setCaveatDetails(String caveatDetails) {
        this.caveatDetails = caveatDetails;
    }

    public String getCaveatDetails() {
        return caveatDetails;
    }

    public void setStayOrderNo(String stayOrderNo) {
        this.stayOrderNo = stayOrderNo;
    }

    public String getStayOrderNo() {
        return stayOrderNo;
    }

    public void setStayOrderDetails(String stayOrderDetails) {
        this.stayOrderDetails = stayOrderDetails;
    }

    public String getStayOrderDetails() {
        return stayOrderDetails;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getRemarks() {
        return remarks;
    }
  
    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setDocUrl(String docUrl) {
        this.docUrl = docUrl;
    }

    public String getDocUrl() {
        return docUrl;
    }
    
     public void setStayOrderYesNo(String stayOrderYesNo) {
         this.stayOrderYesNo = stayOrderYesNo;
     }

     public String getStayOrderYesNo() {
         return stayOrderYesNo;
     }

     public void setFlag(String flag) {
         this.flag = flag;
     }

     public String getFlag() {
         return flag;
     }
     public void setCaveatsDTO(CaveatsDTO caveatsDTO) {
         this.caveatsDTO = caveatsDTO;
     }

     public CaveatsDTO getCaveatsDTO() {
    	 
         return caveatsDTO;
     }

    public void setReferenceID(int referenceID) {
        this.referenceID = referenceID;
    }

    public int getReferenceID() {
        return referenceID;
    }

    public void setCaveatType(String caveatType) {
        this.caveatType = caveatType;
    }

    public String getCaveatType() {
        return caveatType;
    }

    public void setCaveatId(String caveatId) {
        this.caveatId = caveatId;
    }

    public String getCaveatId() {
        return caveatId;
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
	/**
	 * @return the searchResultList
	 */
	public ArrayList getSearchResultList() {
		return searchResultList;
	}
	/**
	 * @param searchResultList the searchResultList to set
	 */
	public void setSearchResultList(ArrayList searchResultList) {
		this.searchResultList = searchResultList;
	}
	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}
	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	/**
	 * @return the stayOrderStartDate
	 */
	public String getStayOrderStartDate() {
		return stayOrderStartDate;
	}
	/**
	 * @param stayOrderStartDate the stayOrderStartDate to set
	 */
	public void setStayOrderStartDate(String stayOrderStartDate) {
		this.stayOrderStartDate = stayOrderStartDate;
	}
	/**
	 * @return the stayOrderUptoDate
	 */
	public String getStayOrderUptoDate() {
		return stayOrderUptoDate;
	}
	/**
	 * @param stayOrderUptoDate the stayOrderUptoDate to set
	 */
	public void setStayOrderUptoDate(String stayOrderUptoDate) {
		this.stayOrderUptoDate = stayOrderUptoDate;
	}
	/**
	 * @return the selectedList
	 */
	public ArrayList getSelectedList() {
		return selectedList;
	}
	/**
	 * @param selectedList the selectedList to set
	 */
	public void setSelectedList(ArrayList selectedList) {
		this.selectedList = selectedList;
	}
	/**
	 * @return the validityOTT
	 */
	public String getValidityOTT() {
		return validityOTT;
	}
	/**
	 * @param validityOTT the validityOTT to set
	 */
	public void setValidityOTT(String validityOTT) {
		this.validityOTT = validityOTT;
	}
	/**
	 * @return the loanDueDate
	 */
	public String getLoanDueDate() {
		return loanDueDate;
	}
	/**
	 * @param loanDueDate the loanDueDate to set
	 */
	public void setLoanDueDate(String loanDueDate) {
		this.loanDueDate = loanDueDate;
	}
	/**
	 * @return the bankRoleName
	 */
	public String getBankRoleName() {
		return bankRoleName;
	}
	/**
	 * @param bankRoleName the bankRoleName to set
	 */
	public void setBankRoleName(String bankRoleName) {
		this.bankRoleName = bankRoleName;
	}
	/**
	 * @return the ottSearchResultList
	 */
	public ArrayList<OTTDetailDTO> getOttSearchResultList() {
		return ottSearchResultList;
	}
	/**
	 * @param ottSearchResultList the ottSearchResultList to set
	 */
	public void setOttSearchResultList(ArrayList<OTTDetailDTO> ottSearchResultList) {
		this.ottSearchResultList = ottSearchResultList;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getLanguage() {
		return language;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public String getActionName() {
		return actionName;
	}
	public void setCsrfToken(String csrfToken) {
		this.csrfToken = csrfToken;
	}
	public String getCsrfToken() {
		return csrfToken;
	}
	
	
	
}
