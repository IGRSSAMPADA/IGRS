/**
 * ===========================================================================
 * File           :   PublicOffForm.java
 * Description    :   Represents the Bean Class

 * Author         :   Pavani Param
 * Created Date   :   Aug 23, 2008

 * ===========================================================================
 */
package com.wipro.igrs.publicOff.form;
import com.wipro.igrs.publicOff.dto.PublicOfficerDTO;
import java.util.ArrayList;
import org.apache.struts.action.ActionForm;



public class PublicOffForm extends ActionForm {
	
  //--- START---Applicant Details

	private String actionValue;
    private String type;
    private String judicialType;
    private String nonJudicialType;
    private String exmpIds;
    private String Select;
    private String transID;
    private String firstName;
    private String middleName;
    private String lastName;
    private String gender;
    private String age ;
    private String fatherName; 
    private String motherName;
    private String spouseName;
    private String address;
    private String city;
    private String postalCode;
    private String country;
    private String state;
    private String district;
    private String PhoneNumber;
    private String mobileNumber;
    private String emailID;
    private String idNo;
    private String bankAddr;
    private String bankName;
    private String pageTitle;
    private String photoId;
     
    //---START-----PUBLIC OFFICER DETAILS--------------------
   
   private String accFrom;
   private String accTo;
   private String designation;
   private String dept;
   private String suFName;
   private String suMName;
   private String suLName;
   private String regId;
   private String drRemarks;
   private String drStatus;
   private String actStatus;
   private String statMsg;
   private ArrayList deptList = new ArrayList();
   
	

  
   
 
   PublicOfficerDTO objDto = new PublicOfficerDTO();
    
    
    
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
        System.out.println("setAddress"+this.address );
    }

    public String getAddress() {
    System.out.println("getAddress"+address);
        return address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

   
    public void setPhoneNumber(String phoneNumber) {
        this.PhoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

  	
    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getSelect() {
		return Select;
	}

	public void setSelect(String select) {
		Select = select;
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

	
	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getBankAddr() {
		return bankAddr;
	}

	public void setBankAddr(String bankAddr) {
		this.bankAddr = bankAddr;
	}

	

	public String getPageTitle() {
		return pageTitle;
	}

	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}

	
	/**
	 * @return the actionValue
	 */
	public String getActionValue() {
		return actionValue;
	}

	/**
	 * @param actionValue the actionValue to set
	 */
	public void setActionValue(String actionValue) {
		this.actionValue = actionValue;
	}

	public String getJudicialType() {
		return judicialType;
	}

	public void setJudicialType(String judicialType) {
		this.judicialType = judicialType;
	}

	public String getNonJudicialType() {
		return nonJudicialType;
	}

	public void setNonJudicialType(String nonJudicialType) {
		this.nonJudicialType = nonJudicialType;
	}

	public String getExmpIds() {
		return exmpIds;
	}

	public void setExmpIds(String exmpIds) {
		this.exmpIds = exmpIds;
	}

	public String getTransID() {
		return transID;
	}

	public void setTransID(String transID) {
		this.transID = transID;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmailID() {
		return emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	public String getAccFrom() {
		return accFrom;
	}

	public void setAccFrom(String accFrom) {
		this.accFrom = accFrom;
	}

	public String getAccTo() {
		return accTo;
	}

	public void setAccTo(String accTo) {
		this.accTo = accTo;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	

	public PublicOfficerDTO getObjDto() {
		return objDto;
	}

	public void setObjDto(PublicOfficerDTO objDto) {
		this.objDto = objDto;
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
	 * @return the regId
	 */
	public String getRegId() {
		return regId;
	}

	/**
	 * @param regId the regId to set
	 */
	public void setRegId(String regId) {
		this.regId = regId;
	}

	/**
	 * @return the deptList
	 */
	public ArrayList getDeptList() {
		return deptList;
	}

	/**
	 * @param deptList the deptList to set
	 */
	public void setDeptList(ArrayList deptList) {
		this.deptList = deptList;
	}

	/**
	 * @return the district
	 */
	public String getDistrict() {
		return district;
	}

	/**
	 * @param district the district to set
	 */
	public void setDistrict(String district) {
		this.district = district;
	}

	/**
	 * @return the photoId
	 */
	public String getPhotoId() {
		return photoId;
	}

	/**
	 * @param photoId the photoId to set
	 */
	public void setPhotoId(String photoId) {
		this.photoId = photoId;
	}

	
	/**
	 * @return the suFName
	 */
	public String getSuFName() {
		return suFName;
	}

	/**
	 * @param suFName the suFName to set
	 */
	public void setSuFName(String suFName) {
		this.suFName = suFName;
	}

	/**
	 * @return the suMName
	 */
	public String getSuMName() {
		return suMName;
	}

	/**
	 * @param suMName the suMName to set
	 */
	public void setSuMName(String suMName) {
		this.suMName = suMName;
	}

	/**
	 * @return the suLName
	 */
	public String getSuLName() {
		return suLName;
	}

	/**
	 * @param suLName the suLName to set
	 */
	public void setSuLName(String suLName) {
		this.suLName = suLName;
	}

	/**
	 * @return the drRemarks
	 */
	public String getDrRemarks() {
		return drRemarks;
	}

	/**
	 * @param drRemarks the drRemarks to set
	 */
	public void setDrRemarks(String drRemarks) {
		this.drRemarks = drRemarks;
	}

	/**
	 * @return the drStatus
	 */
	public String getDrStatus() {
		return drStatus;
	}

	/**
	 * @param drStatus the drStatus to set
	 */
	public void setDrStatus(String drStatus) {
		this.drStatus = drStatus;
	}

	/**
	 * @return the actStatus
	 */
	public String getActStatus() {
		return actStatus;
	}

	/**
	 * @param actStatus the actStatus to set
	 */
	public void setActStatus(String actStatus) {
		this.actStatus = actStatus;
	}

	/**
	 * @return the statMsg
	 */
	public String getStatMsg() {
		return statMsg;
	}

	/**
	 * @param statMsg the statMsg to set
	 */
	public void setStatMsg(String statMsg) {
		this.statMsg = statMsg;
	}
}


