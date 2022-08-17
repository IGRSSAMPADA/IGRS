package com.wipro.igrs.copyissuance.form;

/**
  * ===========================================================================
  * File           :   CertifiedActionForm.java
  * Description    :   Represents the Form Class

  * Author         :   Dev Pradhan
  * Created Date   :   Jan 07, 2008

  * ===========================================================================
  */


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;


import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.wipro.igrs.copyissuance.dto.CertifiedCopyDetailsDTO;
import com.wipro.igrs.copyissuance.dto.PaymentDTO;
import com.wipro.igrs.estamping.dto.EstampDetailsDTO;



public class CertifiedActionForm extends ActionForm
{
    private CertifiedCopyDetailsDTO issuanceDTO = new CertifiedCopyDetailsDTO();
    private ArrayList issuanceCountry = new ArrayList();
    private ArrayList issuanceState = new ArrayList();
    private ArrayList issuanceDistrict = new ArrayList();
    private ArrayList copyStatus = new ArrayList();    
    private ArrayList issuanceProof = new ArrayList();
    private String countryName;
    private String contryId;
    private String printNumber;
    private ArrayList countryList;
    
    private String stateName;
    private String regNumberDownload;
    private String regNumberDownloadFinal;
    private String stId;
    private ArrayList stateList;
    private String radio;
    private String districtName;
    private String districtId;
    private ArrayList districtList;
    private ArrayList copReqViewList;
    private ArrayList copReqViewList1;
    
    public String getRadio() {
		return radio;
	}

	public void setRadio(String radio) {
		this.radio = radio;
	}

	public ArrayList getCopReqViewList1() {
		return copReqViewList1;
	}

	public void setCopReqViewList1(ArrayList copReqViewList1) {
		this.copReqViewList1 = copReqViewList1;
	}

	private ArrayList copyViewList;
    private ArrayList onlineCopyViewList;
    private ArrayList onlineCopyViewList1;
    private String reqCreatedAt;
    private String typeReq;
    public ArrayList getOnlineCopyViewList1() {
		return onlineCopyViewList1;
	}

	public void setOnlineCopyViewList1(ArrayList onlineCopyViewList1) {
		this.onlineCopyViewList1 = onlineCopyViewList1;
	}

	private String createdDt;
    private String regNo;
    private String certifiedId;
    private String appStatus;
    
    private String address;
	private String gender;
    private String age;
    private String fatherName;
    private String motherName;
	private String firstName;
    private String middleName;
    private String lastName;
	private String city;
    private String country;
    private String state;
    private String pin;
    private String phone;
    private String mphone;
    private String email;
	private String idProofNo;
	private String idProof;
	private String issuanceRemark;
	private String fee;
	private String postalFee;
	private String totalFee;
	private String modifiedDt; 
	private String districtName2;
	private String paymentTxnId;
	private String paymentAmount;
	private String paymentMode;
	private String docReg;
	private String searchNo;
	 private ArrayList  onlineCopyList;
	 private String dateOfReq;
	 
	 
	
	 
	 
	 CertifiedCopyDetailsDTO objcopyDet = new CertifiedCopyDetailsDTO();
	public CertifiedCopyDetailsDTO getObjcopyDet() {
		return objcopyDet;
	}

	public void setObjcopyDet(CertifiedCopyDetailsDTO objcopyDet) {
		this.objcopyDet = objcopyDet;
	}

	public String getDateOfReq() {
		return dateOfReq;
	}

	public void setDateOfReq(String dateOfReq) {
		this.dateOfReq = dateOfReq;
	}

	public ArrayList getOnlineCopyList() {
		return onlineCopyList;
	}

	public void setOnlineCopyList(ArrayList onlineCopyList) {
		this.onlineCopyList = onlineCopyList;
	}

	public String getDocReg() {
		return docReg;
	}

	public void setDocReg(String docReg) {
		this.docReg = docReg;
	}

	public String getSearchNo() {
		return searchNo;
	}

	public void setSearchNo(String searchNo) {
		this.searchNo = searchNo;
	}

	public String getPaymentTxnId() {
	return paymentTxnId;
}

public void setPaymentTxnId(String paymentTxnId) {
	this.paymentTxnId = paymentTxnId;
}

public String getPaymentAmount() {
	return paymentAmount;
}

public void setPaymentAmount(String paymentAmount) {
	this.paymentAmount = paymentAmount;
}

public String getPaymentMode() {
	return paymentMode;
}

public void setPaymentMode(String paymentMode) {
	this.paymentMode = paymentMode;
}

	public String getDistrictName2() {
		return districtName2;
	}

	public void setDistrictName2(String districtName2) {
		this.districtName2 = districtName2;
	}

public String getModifiedDt() {
		return modifiedDt;
	}

	public void setModifiedDt(String modifiedDt) {
		this.modifiedDt = modifiedDt;
	}

public ArrayList getOnlineCopyViewList() {
		return onlineCopyViewList;
	}

	public void setOnlineCopyViewList(ArrayList onlineCopyViewList) {
		this.onlineCopyViewList = onlineCopyViewList;
	}

public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

	public String getPostalFee() {
		return postalFee;
	}

	public void setPostalFee(String postalFee) {
		this.postalFee = postalFee;
	}

	public String getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}

public String getReqCreatedAt() {
	return reqCreatedAt;
}

public void setReqCreatedAt(String reqCreatedAt) {
	this.reqCreatedAt = reqCreatedAt;
}

public ArrayList getCopyViewList() {
	return copyViewList;
}

public void setCopyViewList(ArrayList copyViewList) {
	this.copyViewList = copyViewList;
}

public String getAddress() {
	return address;
}

public void setAddress(String address) {
	this.address = address;
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

public void setFatherName(String fatherName) {
	this.fatherName = fatherName;
}

public String getMotherName() {
	return motherName;
}

public void setMotherName(String motherName) {
	this.motherName = motherName;
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

public String getCity() {
	return city;
}

public void setCity(String city) {
	this.city = city;
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

public String getPin() {
	return pin;
}

public void setPin(String pin) {
	this.pin = pin;
}

public String getPhone() {
	return phone;
}

public void setPhone(String phone) {
	this.phone = phone;
}

public String getMphone() {
	return mphone;
}

public void setMphone(String mphone) {
	this.mphone = mphone;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getIdProofNo() {
	return idProofNo;
}

public void setIdProofNo(String idProofNo) {
	this.idProofNo = idProofNo;
}

public String getIdProof() {
	return idProof;
}

public void setIdProof(String idProof) {
	this.idProof = idProof;
}

public String getIssuanceRemark() {
	return issuanceRemark;
}

public void setIssuanceRemark(String issuanceRemark) {
	this.issuanceRemark = issuanceRemark;
}

public String getPurposeReq() {
	return purposeReq;
}

public void setPurposeReq(String purposeReq) {
	this.purposeReq = purposeReq;
}

private String purposeReq;


    public String getAppStatus() {
		return appStatus;
	}

	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
	}

	public String getTypeReq() {
		return typeReq;
	}

	public void setTypeReq(String typeReq) {
		this.typeReq = typeReq;
	}

	public String getCreatedDt() {
		return createdDt;
	}

	public void setCreatedDt(String createdDt) {
		this.createdDt = createdDt;
	}

	public String getRegNo() {
		return regNo;
	}

	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}

	public String getCertifiedId() {
		return certifiedId;
	}

	public void setCertifiedId(String certifiedId) {
		this.certifiedId = certifiedId;
	}


	public ArrayList getCopReqViewList() {
		return copReqViewList;
	}

	public void setCopReqViewList(ArrayList copReqViewList) {
		this.copReqViewList = copReqViewList;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getContryId() {
		return contryId;
	}

	public void setContryId(String contryId) {
		this.contryId = contryId;
	}

	public ArrayList getCountryList() {
		return countryList;
	}

	public void setCountryList(ArrayList countryList) {
		this.countryList = countryList;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getStId() {
		return stId;
	}

	public void setStId(String stId) {
		this.stId = stId;
	}

	public ArrayList getStateList() {
		return stateList;
	}

	public void setStateList(ArrayList stateList) {
		this.stateList = stateList;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	public ArrayList getDistrictList() {
		return districtList;
	}

	public void setDistrictList(ArrayList districtList) {
		this.districtList = districtList;
	}

	private FormFile filePhoto = null;
    private FormFile fileThumb = null;
    private FormFile fileSignature = null;

    //ArrayList for Challan/ Payment Detils
    private ArrayList paymentList = new ArrayList();
    private PaymentDTO payDTO = new PaymentDTO();

private String regiDate;
private String numDate;
    public String getRegiDate() {
	return regiDate;
}

public void setRegiDate(String regiDate) {
	this.regiDate = regiDate;
}

public String getNumDate() {
	return numDate;
}

public void setNumDate(String numDate) {
	this.numDate = numDate;
}

	public void setIssuanceDTO(CertifiedCopyDetailsDTO issuanceDTO) {
        this.issuanceDTO = issuanceDTO;
    }

    public CertifiedCopyDetailsDTO getIssuanceDTO() {
        return issuanceDTO;
    }

    public void setIssuanceCountry(ArrayList issuanceCountry) {
        this.issuanceCountry = issuanceCountry;
    }

    public ArrayList getIssuanceCountry() {
        return issuanceCountry;
    }

    public void setIssuanceState(ArrayList issuanceState) {
        this.issuanceState = issuanceState;
    }

    public ArrayList getIssuanceState() {
        return issuanceState;
    }

    public void setIssuanceDistrict(ArrayList issuanceDistrict) {
        this.issuanceDistrict = issuanceDistrict;
    }

    public ArrayList getIssuanceDistrict() {
        return issuanceDistrict;
    }

    public void setCopyStatus(ArrayList copyStatus) {
        this.copyStatus = copyStatus;
    }

    public ArrayList getCopyStatus() {
        return copyStatus;
    }


    public void setIssuanceProof(ArrayList issuanceProof) {
        this.issuanceProof = issuanceProof;
    }

    public ArrayList getIssuanceProof() {
        return issuanceProof;
    }


    public void setPayDTO(PaymentDTO payDTO) {
        this.payDTO = payDTO;
    }

    public PaymentDTO getPayDTO() {
        return payDTO;
    }



    public ArrayList getPaymentList() {
        return paymentList;
    }

    public void setPaymentList(ArrayList paymentList) {
        this.paymentList = paymentList;
    }

    public void setPaymentItems(int index, PaymentDTO vo)
    {
        for (; index >= paymentList.size(); paymentList.add(new PaymentDTO()));
        paymentList.set(index,vo);
    }

    public PaymentDTO getPaymentItems(int index)
    {
        for (; index >= paymentList.size(); paymentList.add(new PaymentDTO()));
        return(PaymentDTO)paymentList.get(index);
    }

    /**
     * @return the filePhoto
     */
    public FormFile getFilePhoto() {
        return filePhoto;
    }

    /**
     * @param filePhoto the filePhoto to set
     */
    public void setFilePhoto(FormFile filePhoto) {
        this.filePhoto = filePhoto;
    }

    /**
     * @return the fileThumb
     */
    public FormFile getFileThumb() {
        return fileThumb;
    }

    /**
     * @param fileThumb the fileThumb to set
     */
    public void setFileThumb(FormFile fileThumb) {
        this.fileThumb = fileThumb;
    }

    /**
     * @return the fileSignature
     */
    public FormFile getFileSignature() {
        return fileSignature;
    }

    /**
     * @param fileSignature the fileSignature to set
     */
    public void setFileSignature(FormFile fileSignature) {
        this.fileSignature = fileSignature;
    }

	public void setRegNumberDownload(String regNumberDownload) {
		this.regNumberDownload = regNumberDownload;
	}

	public String getRegNumberDownload() {
		return regNumberDownload;
	}

	public void setRegNumberDownloadFinal(String regNumberDownloadFinal) {
		this.regNumberDownloadFinal = regNumberDownloadFinal;
	}

	public String getRegNumberDownloadFinal() {
		return regNumberDownloadFinal;
	}

	public void setPrintNumber(String printNumber) {
		this.printNumber = printNumber;
	}

	public String getPrintNumber() {
		return printNumber;
	}

	

   
}
