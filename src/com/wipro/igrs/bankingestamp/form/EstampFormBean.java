/**
 * ===========================================================================
 * File           :   EstampFormBean.java
 * Description    :   Represents the Bean Class

 * Author         :   Pavani Param
 * Created Date   :   Dec 08, 2007

 * ===========================================================================
 */
package com.wipro.igrs.bankingestamp.form;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;

import com.lowagie.text.pdf.ArabicLigaturizer;
import com.wipro.igrs.estamping.action.EStampAction;
import com.wipro.igrs.estamping.dto.EstampDetailsDTO;
import com.wipro.igrs.estamping.dto.DashBoardDTO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;
import org.apache.struts.upload.FormFile;




public class EstampFormBean extends ActionForm {
	/**
     * 
     */
    private static final long serialVersionUID = 1L;
    
	  //--- START---Applicant Details

	public EstampFormBean() {
	   getInstance();
	    // TODO Auto-generated constructor stub
	}

	/*private Date sdate;

	public Date getSdate() {
		return sdate;
	}

	public void setSdate(Date sdate) {
		this.sdate = sdate;
		logger.info("date="+sdate);
	}*/
	
	private String sdate;

	public String getSdate() {
		return sdate;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}

	/*public static long getSerialVersionUID() {
		return serialVersionUID;
	}*/
	private String DisttName;
	private String courtType;
	private String alias;
	private String actionValue;
    private String type;
    private HashMap mapForm = new HashMap();
    private String judicialType;
    private String nonJudicialType;
    //private String value;
    private String exmpIds;
    private String Select;
    private String transID;
    private String firstName;
    private String middleName;
    private String lastName;
    private String gender = "M";
    private String age ;
    private String fatherName; 
    private String motherName;
    private String spouseName;
    private String address;
    private String city;
    private String postalCode;
    private String valFrom;
    private String country;
    private String state;
    private String PhoneNumber;
    private String mobileNumber;
    
   private String emailID;
   private String idNo;
   private String bankAddr;
   private String pageTitle;
  
    private String typeDocument;
    private String purposeDoc;
    private String validityFrom;
    private String validityTo;
    private String deedValFrom;
    private String deedValTo;
    private String applPartyTypeId;//-- to display org details in print jsp
    private String txn1PartyTypeId;
    private String txn2PartyTypeId;
    
    

    private String estampValFrom;
    private String estampValTo;
    private ArrayList estampSResult;
    private ArrayList retRegInitList;
    private String pmtTxnId;
    private String deactTxnId;
    private ArrayList mstampList = new ArrayList();
    
    
    //---Manual Stamp Details
    private String typeOfStamp;
    private String venderName;
    private String venderId;
    private String dateOfIssue;
    private String mdistrict;
    private String sroName ;
    private String treasuryName ;
    private String stampType;
    private String mstampCode ;
    private String denomination ;
    private String mtotalAmt;
    private String chkIndval ;
    private String chkStampDel;
    private String stampCodeFrom;
    private String stampCodeTo;
    private String sellPlace;
    private ArrayList mstResList = new ArrayList();
    
    // -- Deed Details Dynamically
    
    private ArrayList formList = new ArrayList(); 
    private ArrayList instList = new ArrayList(); 
    //---E-Stamp Deactivation 
    
    private String deacRemarks;
    private String applNo;
    private String modeOfPay;
    private String estampCode;
    private String amtStamp;
    private String apllNo;
    private String dateOfEstamp;
    private String temp;
    private String signedPath;
    private String userId; 
    private FormFile upLoadDoc = null;
   
    
    DashBoardDTO objDashBoard = new DashBoardDTO();
    
    // ADDED BY LAVI FOR JUDICIAL E-STAMPS
    EstampDetailsDTO objEstampDet = new EstampDetailsDTO();
    
    
    //----START--Requisite Duties 
    
    private double stampDuty;
  
    private double total;
    //---START--Challan Payment Information 
    private  String challanNumber;
    private String challanDate;
    private String challanAmount;
    private String bankName;
  //  private String upLoadDoc;
   // private String upLoadDeed;
    private Object upLoadDeed;
    //--upload Document
    private FormFile docFile = null;
    //private byte[] fileData;
    
    //--START-- deed attribute details mapping    
    private  HashMap values;
    
    //---START--- dashboard
    private DashBoardDTO objDashBoardDTO = new DashBoardDTO();
    private String printCheck ;
    
    
    public String getPrintCheck() {
		return printCheck;
	}

	public void setPrintCheck(String printCheck) {
		this.printCheck = printCheck;
	}

	public String getSignedPath() {
		return signedPath;
	}

	public void setSignedPath(String signedPath) {
		this.signedPath = signedPath;
	}

	public Map getInstance()
    {
	if (values == null)
	values = new HashMap();
	return values;
    }
    
    public void setValue(String key, Object value) {
	
        values.put(key, value);
    
        
    }
    

    public Object getValue(String key) {
	
        return values.get(key);
    }
    
    //--END-- deed attribute details mapping
    
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

    /**
     * @param eMailID
     */
   
 //---END-- APPLICANT DETAILS --  
 
  //--START--Transacting --Party Details--
  
  
 
 //--START--VALIDATION-APPLICANT DETAILS--
 
    /**
	 * @param index
	 * @param vo
	 */
	public void setPaymentItems(int index, EstampDetailsDTO vo){
		for (; index >= mstampList.size(); mstampList.add(new EstampDetailsDTO()));
		mstampList.set(index, vo);
	}

	/**
	 * @param index 
	 * @return PaymentDTO
	 */

	public EstampDetailsDTO getPaymentItems(int index){
		for(; index >= mstampList.size(); mstampList.add(new EstampDetailsDTO()));
		return (EstampDetailsDTO) mstampList.get(index);
	}
 
  public void reset(ActionMapping mapping, HttpServletRequest request) {
	  getObjEstampDet().setPhotoId(null);
	  getObjEstampDet().setCountry(null);
	  
             super.reset( mapping, request);
         }
  
  //--To check Alphanucmeric characters
  /**
   * Check  AlphNumeric values. 
   * @return boolean
   * @throws Exception
   */ 
  	public boolean checkAlphaN(String strValue){
  		boolean blnFlag = false;
  		for (int  i = 1; i < strValue.length(); i++)
  		{
  			char ch = strValue.charAt(i);
  		  if((ch >= 48 && ch <= 57) || (ch >= 65 && ch <= 90) || (ch >= 97 && ch <= 122))
  			{
  			blnFlag=true;
  			}
  		 else
	        {
	            blnFlag = false;
	            break;
	        }
  		}
    return blnFlag;
  		}
  	//--To check Numbers.
  	
  	public boolean checkNum(String strValue){
  		boolean blnFlag = false;
  		try
  	{
  	int i = Integer.parseInt(strValue);
  	}
  	catch(NumberFormatException e)
  	{
  	blnFlag = true;
 	}
  	  blnFlag  =  false;
  	  return blnFlag;
   }

  	/**
     * Validate the Form values
     * @return boolean
     * @throws Exception
     */ 
 /* 	
public ActionErrors  validate(ActionMapping map,HttpServletRequest req)
    {
    logger.info("validate method" );
    

       super.validate(map,req);
    ActionErrors errors = new ActionErrors();
    //logger.info("pageTitle value  = "+pageTitle);
  if(pageTitle.equalsIgnoreCase("NonJudicial"))
   {		//--NOn Judicial form validation

//*******************------START--VALIDATION--APPLICANT DETAILS.
    
  if(getObjEstampDet().getApplPartyType().equals("Select") || getObjEstampDet().getApplPartyType().equals(""))
  {
	 logger.info("type validationdkfnsdkf the estampType "+getObjEstampDet().getApplPartyType());
	 errors.add("ApplicantType",new ActionError("App_Party_Type.require"));
  }else if(getObjEstampDet().getApplPartyType().equals("02")){  //--APPLICANT----INDIVIDUAL
	  
	  logger.info("Individul rohini");
	//--START--VALIDATION--APPLICANT ---FIRST NAME.
		
      if(firstName == null || firstName.equals(""))    {   
    	  logger.info("first Name value = "+firstName);
  	      errors.add("firstName",new ActionError("App_Ind_FirstName.require"));
      }else if((firstName.charAt(0)< 'a' ||firstName.charAt(0)< 'z' ) &&(firstName.charAt(0)< 'A' ||firstName.charAt(0)< 'Z' ))
	      {
		     logger.info("first Name value inm alpha = "+firstName);
		  	 errors.add("firstName",new ActionError("App_Ind_FirstName.firstAlpha"));
      }else if(!checkAlphaN(firstName)){	    	
			     logger.info("first Name value inm alpha Numeric = "+firstName);
			  	 errors.add("firstName",new ActionError("App_Ind_FirstName.alphaNum"));  
    	    }
    //--END --VALIDATION--APPLICANT ---FIRST NAME.
		
 //--START--VALIDATION--APPLICANT ---MIDDLE NAME.
      logger.info("Middle Name value inm alpha sri  = "+middleName);
	
    if(middleName != ""){
  	if((middleName.charAt(0)< 'a' ||middleName.charAt(0)< 'z' ) &&(middleName.charAt(0)< 'A' ||middleName.charAt(0)< 'Z' ))
    {
  	  logger.info("first Name value inm alpha = "+middleName);
	 errors.add("MiddleName",new ActionError("App_Ind_MiddleName.firstAlpha"));
    }else if(!checkAlphaN(middleName)){	    	
  	  logger.info("first Name value inm alpha Numeric = "+firstName);
	 errors.add("MiddleName",new ActionError("App_Ind_MiddleName.alphaNum"));  
  	  
    }
    }
//--END--VALIDATION--APPLICANT ---MIDDLE NAME. 
      
//--START--VALIDATION--APPLICANT ---LAST NAME.
	
  if(lastName  == null || lastName.equals(""))
  {
	  logger.info("first Name value = "+lastName);
	  errors.add("lastName",new ActionError("App_Ind_LastName.require"));
  }

  else if((lastName.charAt(0)< 'a' ||lastName.charAt(0)< 'z' ) &&(lastName.charAt(0)< 'A' ||lastName.charAt(0)< 'Z' ))
  {
	  logger.info("first Name value inm alpha = "+lastName);
	 errors.add("LastName",new ActionError("App_Ind_LastName.firstAlpha"));
  }else if(!checkAlphaN(lastName)){	    	
	  logger.info("first Name value inm alpha Numeric = "+lastName);
	 errors.add("LastName",new ActionError("App_Ind_LastName.alphaNum"));  
	  
  }
//--END--VALIDATION--APPLICANT ---LAST NAME.   
  
//--START--VALIDATION--APPLICANT ---GENDER.
	
  	if(gender == null ||gender.equals("") )
		{
			  logger.info("GENDER S value="+gender);
		  errors.add("gender",new ActionError("App_Ind_Gender.require"));
		}
//--END--VALIDATION--APPLICANT ---GENDER. 
  	
  	
//--START--VALIDATION--APPLICANT ---AGE.
	
  	if(age == null ||age.equals("") )
		{
			  logger.info("GENDER S value = "+gender);
		  errors.add("age",new ActionError("App_Ind_Age.require"));
		}
//--END--VALIDATION--APPLICANT ---AGE. 


//--START--VALIDATION--APPLICANT ---FATHER NAME.
	if(fatherName == null || fatherName.equals(""))
	{
		  logger.info("first Name value = "+fatherName);
	      errors.add("FatherName",new ActionError("App_Ind_FatherName.require"));
	}

	else if((fatherName.charAt(0)< 'a' ||fatherName.charAt(0)< 'z' ) &&(fatherName.charAt(0)< 'A' ||fatherName.charAt(0)< 'Z' ))
	{
		  logger.info("first Name value inm alpha = "+fatherName);
	      errors.add("FatherName",new ActionError("App_Ind_FatherName.firstAlpha"));
	}else if(!checkAlphaN(fatherName)){	    	
		  logger.info("first Name value inm alpha Numeric = "+firstName);
	     errors.add("FatherName",new ActionError("App_Ind_FatherName.alphaNum"));  
		  
	}
//--END--VALIDATION--APPLICANT ---FATHER NAME. 

//--START--VALIDATION--APPLICANT ---MOTHER NAME.
	
	if(motherName == null || motherName.equals(""))
	{
		  logger.info("first Name value = "+motherName);
	      errors.add("MotherName",new ActionError("App_Ind_MotherName.require"));
	}

	else if((motherName.charAt(0)< 'a' ||motherName.charAt(0)< 'z' ) &&(motherName.charAt(0)< 'A' ||motherName.charAt(0)< 'Z' ))
	{
		  logger.info("first Name value inm alpha = "+motherName);
	      errors.add("MotherName",new ActionError("App_Ind_MotherName.firstAlpha"));
	}else if(!checkAlphaN(motherName)){	    	
		  logger.info("first Name value inm alpha Numeric = "+motherName);
	      errors.add("MotherName",new ActionError("App_Ind_MotherName.alphaNum"));  
		  
	}
//--END--VALIDATION--APPLICANT ---MOTHER NAME. 


//--START--VALIDATION--APPLICANT ---ADDRESS.	
		if(address == null || address.equals(""))
		{
			  logger.info("first Name value = "+address);
		      errors.add("Address",new ActionError("App_Ind_Address.require"));
		}   
//--END--VALIDATION--APPLICANT --ADDRESS.
//		logger.info("type validationdkfnsdkf the estampType "+getObjEstampDet().getCountry());
//		logger.info("type validationdkfnsdkf the estampType "+getObjEstampDet().getState());
//		logger.info("type validationdkfnsdkf the estampType "+getObjEstampDet().getDistrict());
//--START--VALIDATION--APPLICANT --COUNTRY.		
		if(getObjEstampDet().getCountry().equals("Select") || getObjEstampDet().getCountry().equals(""))
		  {
			 logger.info("type validationdkfnsdkf the estampType "+getObjEstampDet().getCountry());
			 errors.add("AppCountry",new ActionError("App_Ind_Contry.require"));
		  }
 
//--END--VALIDATION--APPLICANT --COUNTRY.	
		
//--START--VALIDATION--APPLICANT --STATE.
		if(getObjEstampDet().getCountry().equals("INDIA"))
		{
		if(getObjEstampDet().getState().equals("Select") || getObjEstampDet().getState().equals(""))
		  {
			 logger.info("type validationdkfnsdkf the estampType "+getObjEstampDet().getState());
			 errors.add("AppState",new ActionError("App_Ind_State.require"));
		  }
		}	
//--END--VALIDATION--APPLICANT --STATE.	

//--START--VALIDATION--APPLICANT --DISTRICT.	
		if(getObjEstampDet().getState().equals("IGR-01"))
		if(getObjEstampDet().getDistrict().equals("Select") || getObjEstampDet().getDistrict().equals(""))
		  {
			 logger.info("type  getDistrict the estampType "+getObjEstampDet().getDistrict());
			 errors.add("AppDistrict",new ActionError("App_Ind_District.require"));
		  }
		
//--END--VALIDATION--APPLICANT --DISTRICT.	
		 logger.info("Type validationd the getPhotoId "+getObjEstampDet().getPhotoId());
		
//--START--VALIDATION--APPLICANT --PHOTO ID PROOF.		
		if(getObjEstampDet().getPhotoId().equals("Select") || getObjEstampDet().getPhotoId()== null)
		  {
			 logger.info("type validationdkfnsdkf the estampType     "+getObjEstampDet().getPhotoId());
			 errors.add("AppPhotoId",new ActionError("App_Ind_PhotoId.require"));
		  }
		  else if(getObjEstampDet().getPhotoId().equals("IGR-29")){//--it shoule be IGR-29
			  logger.info("Bank Name rohini");
			  if(bankName== null ||bankName.equals(""))
		       {
			    	   logger.info("BANK name not  selected"+ bankName);
			      errors.add("AppBankName",new ActionError("App_Ind_BankName.require"));
			
			   }
			  if(bankAddr== null ||bankAddr.equals(""))
		       {
			    	   logger.info("BANK name not  selected"+ bankAddr);
			      errors.add("AppbankAddr",new ActionError("App_Ind_BankAddr.require"));
			
			   }
		  }
 
//--END--VALIDATION--APPLICANT --PHOTO ID PROOF.	
		
		
		
//--START--VALIDATION--APPLICANT ---PHOTO ID NO.	
		if(idNo == null || idNo.equals(""))
		{
			  logger.info("first Name value="+address);
		      errors.add("PhotoIdNo",new ActionError("App_Ind_PhotoIdNo.require"));
		}   
//--END--VALIDATION--APPLICANT --PHOTO ID NO.	
		
		
  }else if(getObjEstampDet().getApplPartyType().equals("01")){  //--APPLICANT----ORGANISATION
	  
	  //logger.info("Organisation rohini");
	  if(getObjOrgDetailsDTO().getOrgName() == null || getObjOrgDetailsDTO().getOrgName().equals(""))
      {
    	  logger.info("OrgName  value = "+getObjOrgDetailsDTO().getOrgName());
  	  errors.add("APPOrgName",new ActionError("App_Org_OrgName.require"));
      }else if((getObjOrgDetailsDTO().getOrgName().charAt(0)< 'a' ||getObjOrgDetailsDTO().getOrgName().charAt(0)< 'z' ) &&(getObjOrgDetailsDTO().getOrgName().charAt(0)< 'A' ||getObjOrgDetailsDTO().getOrgName().charAt(0)< 'Z' ))
      {
		     logger.info("first Name value inm alpha = "+getObjOrgDetailsDTO().getOrgName());
		  	 errors.add("APPOrgName",new ActionError("App_Org_OrgName.firstAlpha"));
   }else if(!checkAlphaN(getObjOrgDetailsDTO().getOrgName())){	    	
		     logger.info("first Name value inm alpha Numeric = "+getObjOrgDetailsDTO().getOrgName());
		  	 errors.add("APPOrgName",new ActionError("App_Org_OrgName.alphaNum"));  
 	    }
  }
  logger.info("type validationdkfnsdkf the estamp Type out of If     "+getObjEstampDet().getApplPartyType());
   //      logger.info("first Name value out of if pavani  = "+checkAlphaN(lastName));

//--END--VALIDATION--APPLICANT DETAILS
	       
//--START--VALIDATION--TXN PARTY-I DETAILS  
  
//--START--VALIDATION--XN PARTY-I--INDIVIDUAL ---FIRST NAME.
  
  if(getObjEstampDet().getTxn1PartyType().equals("Select") || getObjEstampDet().getTxn1PartyType().equals(""))
  {
	 logger.info("type validationdkfnsdkf the estampType     "+getObjEstampDet().getTxn1PartyType());
	 errors.add("Estamp",new ActionError("Txn1_Party_Type.require"));
  }else if(getObjEstampDet().getTxn1PartyType().equals("02")){  //--TXN PARTY-I----INDIVIDUAL
	  
       logger.info("getObjTransDet().getFirstName1()"+getObjTransDet().getFirstName1());
	       if(getObjTransDet().getFirstName1() == null || getObjTransDet().getFirstName1().equals("")){
	    	   errors.add("Txn1FirstName",new ActionError("Txn1_Ind_FirstName.require"));
	       	} else if((getObjTransDet().getFirstName1().charAt(0)< 'a' ||getObjTransDet().getFirstName1().charAt(0)< 'z' ) &&(getObjTransDet().getFirstName1().charAt(0)< 'A' ||getObjTransDet().getFirstName1().charAt(0)< 'Z' ))
		     {
	 	   	  logger.info("first Name value inm alpha = "+getObjTransDet().getFirstName1());
	 	 	 errors.add("Txn1FirstName",new ActionError("Txn1_Ind_FirstName.firstAlpha"));
	 	     }else if(!checkAlphaN(getObjTransDet().getFirstName1())){	    	
	 	   	  logger.info("first Name value inm alpha Numeric = "+getObjTransDet().getFirstName1());
	 	 	 errors.add("Txn1FirstName",new ActionError("Txn1_Ind_FirstName.alphaNum"));  
	 	   	 }
//--START--VALIDATION--XN PARTY-I--INDIVIDUAL ---FIRST NAME.
	       
	       
//--START--VALIDATION--XN PARTY-I--INDIVIDUAL ---MIDDLE NAME.
	       logger.info("Middle Name value inm alpha sri ="+middleName);
	 	
	     if(getObjTransDet().getMiddleName1() != ""){
	   	if((getObjTransDet().getMiddleName1().charAt(0)< 'a' ||getObjTransDet().getMiddleName1().charAt(0)< 'z' ) &&(getObjTransDet().getMiddleName1().charAt(0)< 'A' ||getObjTransDet().getMiddleName1().charAt(0)< 'Z' ))
	     {
	   	  logger.info("first Name value inm alpha="+getObjTransDet().getMiddleName1());
	 	 errors.add("Txn1MiddleName",new ActionError("Txn1_Ind_MiddleName.firstAlpha"));
	     }else if(!checkAlphaN(getObjTransDet().getMiddleName1())){	    	
	   	  logger.info("first Name value inm alpha Numeric="+getObjTransDet().getMiddleName1());
	 	 errors.add("Txn1MiddleName",new ActionError("Txn1_Ind_MiddleName.alphaNum"));  
	   	  
	     }
	     }
//--START--VALIDATION--XN PARTY-I--INDIVIDUAL ---MIDDLE NAME. 
	     
//--START--VALIDATION--XN PARTY-I--INDIVIDUAL ---LAST NAME. 	     
	         
	       
	       
	 	   if(getObjTransDet().getLastName1() == null || getObjTransDet().getLastName1().equals("")){
	 	       errors.add("Txn1LastName",new ActionError("Txn1_Ind_LastName.require"));
	 	   }else if((getObjTransDet().getLastName1().charAt(0)< 'a' ||getObjTransDet().getLastName1().charAt(0)< 'z' ) &&(getObjTransDet().getLastName1().charAt(0)< 'A' ||getObjTransDet().getLastName1().charAt(0)< 'Z' ))
		     {
	 	   	  logger.info("first Name value inm alpha="+getObjTransDet().getLastName1());
	 	 	 errors.add("Txn1LastName",new ActionError("Txn1_Ind_LastName.firstAlpha"));
	 	     }else if(!checkAlphaN(getObjTransDet().getLastName1())){	    	
	 	   	  logger.info("first Name value inm alpha Numeric="+getObjTransDet().getLastName1());
	 	 	 errors.add("Txn1LastName",new ActionError("Txn1_Ind_LastName.alphaNum"));  
	 	   	 }
	 	 	 	   
//--END--VALIDATION--TXN PARTY-I--INDIVIDUAL ---LAST NAME. 
	 	   
//--END--VALIDATION--TXN PARTY-I--INDIVIDUAL ---GENDER .	 	   
	 	   
	 	       if(getObjTransDet().getGender1() ==null ||getObjTransDet().getGender1().equals("") )
	 	       {
	 	       errors.add("Txn1Gender",new ActionError("Txn1_Ind_Gender.require")); 
	 	     }
//--END--VALIDATION--TXN PARTY-I--INDIVIDUAL ---GENDER.	
	 	       
   
	 	       
//--END--VALIDATION--XN PARTY-I--INDIVIDUAL ---AGE.	 	   
		 	   
	 	       if(getObjTransDet().getAge1() ==null ||getObjTransDet().getAge1().equals("") )
	 	       {
	 	       errors.add("Txn1Age",new ActionError("Txn1_Ind_Age.require")); 
	 	     }
//--END--VALIDATION--TXN PARTY-I--INDIVIDUAL ---AGE.	
	 	       

 //--START--VALIDATION--TXN PARTY-I--INDIVIDUAL ---FATHER NAME.	 	       
  	  
	 	       if(getObjTransDet().getFatherName1() == null || getObjTransDet().getFatherName1().equals("")){
	 	          errors.add("Txn1FatherName",new ActionError("Txn1_Ind_FatherName.require"));
	 	       }else if((getObjTransDet().getFatherName1().charAt(0)< 'a' ||getObjTransDet().getFatherName1().charAt(0)< 'z' ) &&(getObjTransDet().getFatherName1().charAt(0)< 'A' ||getObjTransDet().getFatherName1().charAt(0)< 'Z' ))
			     {
		 	 	   	 logger.info("first Name value inm alpha="+getObjTransDet().getFatherName1());
		 	 	 	 errors.add("Txn1FatherName",new ActionError("Txn1_Ind_FatherName.firstAlpha"));
	 	 	     }else if(!checkAlphaN(getObjTransDet().getFatherName1())){	    	
		 	 	   	 logger.info("first Name value inm alpha Numeric="+getObjTransDet().getFatherName1());
		 	 	 	 errors.add("Txn1FatherName",new ActionError("Txn1_Ind_FatherName.alphaNum"));  
	 	 	   	 }
//--END--VALIDATION--XN PARTY-I--INDIVIDUAL ---FATHER NAME.	 	       

 //--END--VALIDATION--XN PARTY-I--INDIVIDUAL ---MOTHER NAME.		 	       
	 	       if(getObjTransDet().getMotherName1()== null || getObjTransDet().getMotherName1().equals("")){
	 	        errors.add("Txn1MotherName",new ActionError("Txn1_Ind_MotherName.require"));
	 	       }else if((getObjTransDet().getMotherName1().charAt(0)< 'a' ||getObjTransDet().getMotherName1().charAt(0)< 'z' ) &&(getObjTransDet().getMotherName1().charAt(0)< 'A' ||getObjTransDet().getMotherName1().charAt(0)< 'Z' ))
			     {
		 	 	   	 logger.info("first Name value inm alpha="+getObjTransDet().getMotherName1());
		 	 	 	 errors.add("Txn1MotherName",new ActionError("Txn1_Ind_MotherName.firstAlpha"));
	 	 	     }else if(!checkAlphaN(getObjTransDet().getMotherName1())){	    	
		 	 	   	 logger.info("first Name value inm alpha Numeric="+getObjTransDet().getMotherName1());
		 	 	 	 errors.add("Txn1MotherName",new ActionError("Txn1_Ind_MotherName.alphaNum"));  
	 	 	   	 }
 //--END--VALIDATION--TXN PARTY-I--INDIVIDUAL ---MOTHER NAME.
	 	       
	 	       
	 	       

  } else if(getObjEstampDet().getTxn1PartyType().equals("01")){  //--TXN PARTY-I----ORGANISATION--start
	  
//--START--VALIDATION--XN PARTY-I--ORGANISATION --- NAME.
		  
		       if(getObjOrgDetailsDTO().getOrgNameTxn1() == null || getObjOrgDetailsDTO().getOrgNameTxn1().equals("")){
		    	   errors.add("Txn1OrgName",new ActionError("Txn1_Org_OrgName.require"));
		       	} else if((getObjOrgDetailsDTO().getOrgNameTxn1().charAt(0)< 'a' ||getObjOrgDetailsDTO().getOrgNameTxn1().charAt(0)< 'z' ) &&(getObjOrgDetailsDTO().getOrgNameTxn1().charAt(0)< 'A' ||getObjOrgDetailsDTO().getOrgNameTxn1().charAt(0)< 'Z' ))
			     {
		 	   	  logger.info("first Name value inm alpha="+getObjOrgDetailsDTO().getOrgNameTxn1());
		 	 	 errors.add("Txn1OrgName",new ActionError("Txn1_Org_OrgName.firstAlpha"));
		 	     }else if(!checkAlphaN(getObjOrgDetailsDTO().getOrgNameTxn1())){	    	
		 	   	  logger.info("first Name value inm alpha Numeric="+getObjOrgDetailsDTO().getOrgNameTxn1());
		 	 	 errors.add("Txn1OrgName",new ActionError("Txn1_Org_OrgName.alphaNum"));  
		 	   	 }
		       
//--END--VALIDATION--TXN PARTY-I--ORGANISATION --- NAME.
		       
		   	
		     //--START--VALIDATION--XN PARTY-I--ORGANISATION ----AUTHORIZED--- FIRST NAME.
		     	      
		     	       if(getObjOrgDetailsDTO().getAuthFirstNameTxn1() == null || getObjOrgDetailsDTO().getAuthFirstNameTxn1().equals("")){
		     	    	   errors.add("Txn1AuthFName",new ActionError("Txn1_Org_AuthFName.require"));
		     	       	} else if((getObjOrgDetailsDTO().getAuthFirstNameTxn1().charAt(0)< 'a' ||getObjOrgDetailsDTO().getAuthFirstNameTxn1().charAt(0)< 'z' ) &&(getObjOrgDetailsDTO().getAuthFirstNameTxn1().charAt(0)< 'A' ||getObjOrgDetailsDTO().getAuthFirstNameTxn1().charAt(0)< 'Z' ))
		     		     {
		     	 	   	  logger.info("first Name value inm alpha="+getObjOrgDetailsDTO().getAuthFirstNameTxn1());
		     	 	 	 errors.add("Txn1AuthFName",new ActionError("Txn1_Org_AuthFName.firstAlpha"));
		     	 	     }else if(!checkAlphaN(getObjOrgDetailsDTO().getAuthFirstNameTxn1())){	    	
		     	 	   	  logger.info("first Name value inm alpha Numeric="+getObjOrgDetailsDTO().getAuthFirstNameTxn1());
		     	 	 	 errors.add("Txn1AuthFName",new ActionError("Txn1_Org_AuthFName.alphaNum"));  
		     	 	   	 }
		     	       
		     //--START--VALIDATION--XN PARTY-I--ORGANISATION ----AUTHORIZED--- FIRST NAME.
		     	       
		     //--START--VALIDATION--XN PARTY-I--ORGANISATION ----AUTHORIZED--- MIDDLE NAME.
		     	 	
		     	     if(getObjOrgDetailsDTO().getAuthMiddleNameTxn1() != ""){
		     	   	if((getObjOrgDetailsDTO().getAuthMiddleNameTxn1().charAt(0)< 'a' ||getObjOrgDetailsDTO().getAuthMiddleNameTxn1().charAt(0)< 'z' ) &&(getObjOrgDetailsDTO().getAuthMiddleNameTxn1().charAt(0)< 'A' ||getObjOrgDetailsDTO().getAuthMiddleNameTxn1().charAt(0)< 'Z' ))
		     	     {
		     	   	  logger.info("first Name value inm alpha="+getObjOrgDetailsDTO().getAuthMiddleNameTxn1());
		     	 	 errors.add("Txn1AuthMidName",new ActionError("Txn1_Org_AuthMidName.firstAlpha"));
		     	     }else if(!checkAlphaN(getObjOrgDetailsDTO().getAuthMiddleNameTxn1())){	    	
		     	   	  logger.info("first Name value inm alpha Numeric="+getObjOrgDetailsDTO().getAuthMiddleNameTxn1());
		     	 	 errors.add("Txn1AuthMidName",new ActionError("Txn1_Org_AuthMidName.alphaNum"));  
		     	   	  
		     	     }
		     	     }
		     //--START--VALIDATION--XN PARTY-I--ORGANISATION ----AUTHORIZED--- MIDDLE NAME. 
		     	     
		     //--START--VALIDATION--XN PARTY-I--ORGANISATION ----AUTHORIZED--- LAST NAME. 	     
		     	         
		     	       
		     	       
		     	     if(getObjOrgDetailsDTO().getAuthLastNameTxn1() == null || getObjOrgDetailsDTO().getAuthLastNameTxn1().equals("")){
		     	    	   errors.add("Txn1AuthFName",new ActionError("Txn1_Org_AuthLName.require"));
		     	       	} else if((getObjOrgDetailsDTO().getAuthLastNameTxn1().charAt(0)< 'a' ||getObjOrgDetailsDTO().getAuthLastNameTxn1().charAt(0)< 'z' ) &&(getObjOrgDetailsDTO().getAuthLastNameTxn1().charAt(0)< 'A' ||getObjOrgDetailsDTO().getAuthLastNameTxn1().charAt(0)< 'Z' ))
		     		     {
		     	 	   	  logger.info("first Name value inm alpha="+getObjOrgDetailsDTO().getAuthLastNameTxn1());
		     	 	 	 errors.add("Txn1AuthFName",new ActionError("Txn1_Org_AuthLName.firstAlpha"));
		     	 	     }else if(!checkAlphaN(getObjOrgDetailsDTO().getAuthLastNameTxn1())){	    	
		     	 	   	  logger.info("first Name value inm alpha Numeric="+getObjOrgDetailsDTO().getAuthLastNameTxn1());
		     	 	 	 errors.add("Txn1AuthFName",new ActionError("Txn1_Org_AuthLName.alphaNum"));  
		     	 	   	 }
		     	 	   
		       } //--end -I oraganisation
		     //--END--VALIDATION--XN PARTY-I--ORGANISATION ----AUTHORIZED--- LAST NAME.//---END--ORGANISATION 		       
  
  
  //---END-- TXN PARTY -I INDIVIDUAl
  
//**************************************************--END--VALIDATION--TXN PARTY - I DETAILS ***************************
  
//**************************************************--START--VALIDATION--TXN PARTY - II DETAILS ***************************
//--START--VALIDATION--TXN PARTY-II DETAILS  
  
//--START--VALIDATION--XN PARTY-II--INDIVIDUAL ---FIRST NAME.
  
  if(getObjEstampDet().getTxn2PartyType().equals("Select") || getObjEstampDet().getTxn2PartyType().equals(""))
  {
	 logger.info("type validationdkfnsdkf the estampType     "+getObjEstampDet().getTxn2PartyType());
	 errors.add("Estamp",new ActionError("Txn2_Party_Type.require"));
  }else if(getObjEstampDet().getTxn2PartyType().equals("02")){  //--TXN PARTY-II----INDIVIDUAL--start
	  
       logger.info("getObjTransDet().getFirstName2()"+getObjTransDet().getFirstName2());
	       if(getObjTransDet().getFirstName2() == null || getObjTransDet().getFirstName2().equals("")){
	    	   errors.add("Txn2FirstName",new ActionError("Txn2_Ind_FirstName.require"));
	       	} else if((getObjTransDet().getFirstName2().charAt(0)< 'a' ||getObjTransDet().getFirstName2().charAt(0)< 'z' ) &&(getObjTransDet().getFirstName2().charAt(0)< 'A' ||getObjTransDet().getFirstName2().charAt(0)< 'Z' ))
		     {
	 	   	  logger.info("first Name value inm alpha="+getObjTransDet().getFirstName2());
	 	 	 errors.add("Txn2FirstName",new ActionError("Txn2_Ind_FirstName.firstAlpha"));
	 	     }else if(!checkAlphaN(getObjTransDet().getFirstName2())){	    	
	 	   	  logger.info("first Name value inm alpha Numeric="+getObjTransDet().getFirstName2());
	 	 	 errors.add("Txn2FirstName",new ActionError("Txn2_Ind_FirstName.alphaNum"));  
	 	   	 }
//--START--VALIDATION--XN PARTY-II--INDIVIDUAL ---FIRST NAME.
	       
	       
//--START--VALIDATION--XN PARTY-II--INDIVIDUAL ---MIDDLE NAME.
	       logger.info("Middle Name value inm alpha sri ="+middleName);
	 	
	     if(getObjTransDet().getMiddleName2() != ""){
	   	if((getObjTransDet().getMiddleName2().charAt(0)< 'a' ||getObjTransDet().getMiddleName2().charAt(0)< 'z' ) &&(getObjTransDet().getMiddleName2().charAt(0)< 'A' ||getObjTransDet().getMiddleName2().charAt(0)< 'Z' ))
	     {
	   	  logger.info("first Name value inm alpha="+getObjTransDet().getMiddleName2());
	 	 errors.add("Txn2MiddleName",new ActionError("Txn2_Ind_MiddleName.firstAlpha"));
	     }else if(!checkAlphaN(getObjTransDet().getMiddleName2())){	    	
	   	  logger.info("first Name value inm alpha Numeric="+getObjTransDet().getMiddleName2());
	 	 errors.add("Txn2MiddleName",new ActionError("Txn2_Ind_MiddleName.alphaNum"));  
	   	  
	     }
	     }
//--START--VALIDATION--XN PARTY-II--INDIVIDUAL ---MIDDLE NAME. 
	     
//--START--VALIDATION--XN PARTY-II--INDIVIDUAL ---LAST NAME. 	     
	         
	       
	       
	 	   if(getObjTransDet().getLastName2() == null || getObjTransDet().getLastName2().equals("")){
	 	       errors.add("Txn2LastName",new ActionError("Txn2_Ind_LastName.require"));
	 	   }else if((getObjTransDet().getLastName2().charAt(0)< 'a' ||getObjTransDet().getLastName2().charAt(0)< 'z' ) &&(getObjTransDet().getLastName2().charAt(0)< 'A' ||getObjTransDet().getLastName2().charAt(0)< 'Z' ))
		     {
	 	   	  logger.info("first Name value inm alpha="+getObjTransDet().getLastName2());
	 	 	 errors.add("Txn2LastName",new ActionError("Txn2_Ind_LastName.firstAlpha"));
	 	     }else if(!checkAlphaN(getObjTransDet().getLastName2())){	    	
	 	   	  logger.info("first Name value inm alpha Numeric="+getObjTransDet().getLastName2());
	 	 	 errors.add("Txn2LastName",new ActionError("Txn2_Ind_LastName.alphaNum"));  
	 	   	 }
	 	   
	 	   
//--END--VALIDATION--XN PARTY-II--INDIVIDUAL ---LAST NAME. 
	 	   
//--END--VALIDATION--XN PARTY-II--INDIVIDUAL ---GENDER NAME.	 	   
	 	   
	 	       if(getObjTransDet().getGender2() ==null ||getObjTransDet().getGender2().equals("") )
	 	       {
	 	     logger.info("gender not selcted");
	 	       errors.add("Txn2Gender",new ActionError("Txn2_Ind_Gender.require")); 
	 	     }
//--END--VALIDATION--XN PARTY-II--INDIVIDUAL ---GENDER NAME.	
	 	       
//--START--VALIDATION--TXN PARTY-I--INDIVIDUAL ---AGE .	 	   
		 	   
	 	       if(getObjTransDet().getAge2() ==null ||getObjTransDet().getAge2().equals("") )
	 	       {
	 	       errors.add("Txn2Age",new ActionError("Txn2_Ind_Age.require")); 
	 	     }
//--END--VALIDATION--TXN PARTY-I--INDIVIDUAL ---AGE.		 	       
	 	       

 //--START--VALIDATION--XN PARTY-II--INDIVIDUAL ---FATHER NAME.	 	       
  	  
	 	       if(getObjTransDet().getFatherName2() == null || getObjTransDet().getFatherName2().equals("")){
	 	          errors.add("Txn2FatherName",new ActionError("Txn2_Ind_FatherName.require"));
	 	       }else if((getObjTransDet().getFatherName2().charAt(0)< 'a' ||getObjTransDet().getFatherName2().charAt(0)< 'z' ) &&(getObjTransDet().getFatherName2().charAt(0)< 'A' ||getObjTransDet().getFatherName2().charAt(0)< 'Z' ))
			     {
		 	 	   	 logger.info("first Name value inm alpha="+getObjTransDet().getFatherName2());
		 	 	 	 errors.add("Txn2FatherName",new ActionError("Txn2_Ind_FatherName.firstAlpha"));
	 	 	     }else if(!checkAlphaN(getObjTransDet().getFatherName2())){	    	
		 	 	   	 logger.info("first Name value inm alpha Numeric="+getObjTransDet().getFatherName2());
		 	 	 	 errors.add("Txn2FatherName",new ActionError("Txn2_Ind_FatherName.alphaNum"));  
	 	 	   	 }
//--END--VALIDATION--XN PARTY-II--INDIVIDUAL ---FATHER NAME.	 	       

 //--END--VALIDATION--XN PARTY-II--INDIVIDUAL ---MOTHER NAME.		 	       
	 	       if(getObjTransDet().getMotherName2()== null || getObjTransDet().getMotherName2().equals("")){
	 	        errors.add("Txn2MotherName",new ActionError("Txn2_Ind_MotherName.require"));
	 	       }else if((getObjTransDet().getMotherName2().charAt(0)< 'a' ||getObjTransDet().getMotherName2().charAt(0)< 'z' ) &&(getObjTransDet().getMotherName2().charAt(0)< 'A' ||getObjTransDet().getMotherName2().charAt(0)< 'Z' ))
			     {
		 	 	   	 logger.info("first Name value inm alpha="+getObjTransDet().getMotherName2());
		 	 	 	 errors.add("Txn2MotherName",new ActionError("Txn2_Ind_MotherName.firstAlpha"));
	 	 	     }else if(!checkAlphaN(getObjTransDet().getMotherName2())){	    	
		 	 	   	 logger.info("first Name value inm alpha Numeric="+getObjTransDet().getMotherName2());
		 	 	 	 errors.add("Txn2MotherName",new ActionError("Txn2_Ind_MotherName.alphaNum"));  
	 	 	   	 }
 //--END--VALIDATION--XN PARTY-II--INDIVIDUAL ---MOTHER NAME.		 	       

  }else if(getObjEstampDet().getTxn2PartyType().equals("01")){  //--TXN PARTY-II----ORGANISATION--start
	  
//--START--VALIDATION--XN PARTY-II--ORGANISATION --- NAME.
	  
	       if(getObjOrgDetailsDTO().getOrgNameTxn2() == null || getObjOrgDetailsDTO().getOrgNameTxn2().equals("")){
	    	   errors.add("Txn2OrgName",new ActionError("Txn2_Org_OrgName.require"));
	       	} else if((getObjOrgDetailsDTO().getOrgNameTxn2().charAt(0)< 'a' ||getObjOrgDetailsDTO().getOrgNameTxn2().charAt(0)< 'z' ) &&(getObjOrgDetailsDTO().getOrgNameTxn2().charAt(0)< 'A' ||getObjOrgDetailsDTO().getOrgNameTxn2().charAt(0)< 'Z' ))
		     {
	 	   	  logger.info("first Name value inm alpha="+getObjOrgDetailsDTO().getOrgNameTxn2());
	 	 	 errors.add("Txn2OrgName",new ActionError("Txn2_Org_OrgName.firstAlpha"));
	 	     }else if(!checkAlphaN(getObjOrgDetailsDTO().getOrgNameTxn2())){	    	
	 	   	  logger.info("first Name value inm alpha Numeric="+getObjOrgDetailsDTO().getOrgNameTxn2());
	 	 	 errors.add("Txn2OrgName",new ActionError("Txn2_Org_OrgName.alphaNum"));  
	 	   	 }
	       
//--START--VALIDATION--TXN PARTY-II--ORGANISATION --- NAME.
	       
	
//--START--VALIDATION--XN PARTY-II--ORGANISATION ----AUTHORIZED--- FIRST NAME.
	      
	       if(getObjOrgDetailsDTO().getAuthFirstNameTxn2() == null || getObjOrgDetailsDTO().getAuthFirstNameTxn2().equals("")){
	    	   errors.add("Txn2AuthFName",new ActionError("Txn2_Org_AuthFName.require"));
	       	} else if((getObjOrgDetailsDTO().getAuthFirstNameTxn2().charAt(0)< 'a' ||getObjOrgDetailsDTO().getAuthFirstNameTxn2().charAt(0)< 'z' ) &&(getObjOrgDetailsDTO().getAuthFirstNameTxn2().charAt(0)< 'A' ||getObjOrgDetailsDTO().getAuthFirstNameTxn2().charAt(0)< 'Z' ))
		     {
	 	   	  logger.info("first Name value inm alpha="+getObjOrgDetailsDTO().getAuthFirstNameTxn2());
	 	 	 errors.add("Txn2AuthFName",new ActionError("Txn2_Org_AuthFName.firstAlpha"));
	 	     }else if(!checkAlphaN(getObjOrgDetailsDTO().getAuthFirstNameTxn2())){	    	
	 	   	  logger.info("first Name value inm alpha Numeric="+getObjOrgDetailsDTO().getAuthFirstNameTxn2());
	 	 	 errors.add("Txn2AuthFName",new ActionError("Txn2_Org_AuthFName.alphaNum"));  
	 	   	 }
	       
//--START--VALIDATION--XN PARTY-II--ORGANISATION ----AUTHORIZED--- FIRST NAME.
	       
//--START--VALIDATION--XN PARTY-II--ORGANISATION ----AUTHORIZED--- MIDDLE NAME.
	 	
	     if(getObjOrgDetailsDTO().getAuthMiddleNameTxn2() != ""){
	   	if((getObjOrgDetailsDTO().getAuthMiddleNameTxn2().charAt(0)< 'a' ||getObjOrgDetailsDTO().getAuthMiddleNameTxn2().charAt(0)< 'z' ) &&(getObjOrgDetailsDTO().getAuthMiddleNameTxn2().charAt(0)< 'A' ||getObjOrgDetailsDTO().getAuthMiddleNameTxn2().charAt(0)< 'Z' ))
	     {
	   	  logger.info("first Name value inm alpha="+getObjOrgDetailsDTO().getAuthMiddleNameTxn2());
	 	 errors.add("Txn2AuthMidName",new ActionError("Txn2_Org_AuthMidName.firstAlpha"));
	     }else if(!checkAlphaN(getObjOrgDetailsDTO().getAuthMiddleNameTxn2())){	    	
	   	  logger.info("first Name value inm alpha Numeric="+getObjOrgDetailsDTO().getAuthMiddleNameTxn2());
	 	 errors.add("Txn2AuthMidName",new ActionError("Txn2_Org_AuthMidName.alphaNum"));  
	   	  
	     }
	     }
//--START--VALIDATION--XN PARTY-II--ORGANISATION ----AUTHORIZED--- MIDDLE NAME. 
	     
//--START--VALIDATION--XN PARTY-II--ORGANISATION ----AUTHORIZED--- LAST NAME. 	     
	         
	       
	       
	     if(getObjOrgDetailsDTO().getAuthLastNameTxn2() == null || getObjOrgDetailsDTO().getAuthLastNameTxn2().equals("")){
	    	   errors.add("Txn2AuthFName",new ActionError("Txn2_Org_AuthLName.require"));
	       	} else if((getObjOrgDetailsDTO().getAuthLastNameTxn2().charAt(0)< 'a' ||getObjOrgDetailsDTO().getAuthLastNameTxn2().charAt(0)< 'z' ) &&(getObjOrgDetailsDTO().getAuthLastNameTxn2().charAt(0)< 'A' ||getObjOrgDetailsDTO().getAuthLastNameTxn2().charAt(0)< 'Z' ))
		     {
	 	   	  logger.info("first Name value inm alpha="+getObjOrgDetailsDTO().getAuthLastNameTxn2());
	 	 	 errors.add("Txn2AuthFName",new ActionError("Txn2_Org_AuthLName.firstAlpha"));
	 	     }else if(!checkAlphaN(getObjOrgDetailsDTO().getAuthLastNameTxn2())){	    	
	 	   	  logger.info("first Name value inm alpha Numeric="+getObjOrgDetailsDTO().getAuthLastNameTxn2());
	 	 	 errors.add("Txn2AuthFName",new ActionError("Txn2_Org_AuthLName.alphaNum"));  
	 	   	 }
	 	   
  } //--end -II oraganisation
//--END--VALIDATION--XN PARTY-II--ORGANISATION ----AUTHORIZED--- LAST NAME.//---END--ORGANISATION 
  
//--END--VALIDATION--TXN PARTY - II   -- ORGANISATION DETAILS 
//**************************************************--START--VALIDATION--TXN PARTY - II DETAILS ***************************  
  
  
//**************************************************--START--DEED--- DETAILS *************************** 
//--START--DEED DETAILS	
  
  // ---DATE --COMPARISON.
  SimpleDateFormat sd1 = new SimpleDateFormat("dd/MM/yyyy");
//  SimpleDateFormat sd2 = new SimpleDateFormat(deedValTo);
  Date date1 = null;
  Date date2 = null;       
	 	     
  if(getObjEstampDet().getDeedType().equals("Select") || getObjEstampDet().getDeedType().equals(""))		 	      	 
  	 errors.add("DeedType",new ActionError("Deed_Type.require"));
  if(getObjDocDetails().getPurposeDeed() == null || getObjDocDetails().getPurposeDeed().equals(""))
   	errors.add("PurposeDeed",new ActionError("Purpose_Deed.require"));
  if(deedValFrom == null || deedValFrom.equals(""))
     errors.add("validityFrom",new ActionError("validityFrom.require"));
  if(deedValTo == null || deedValTo.equals("")) 
  	errors.add("validityTo",new ActionError("validityTo.require"));
  if(deedValTo != null  &&  deedValFrom != null ){
	  
	  try {
			date1 = sd1.parse(deedValFrom);
			date2 = sd1.parse(deedValTo);
			if(date2.compareTo(date1)<0)
		  {		logger.info("Validity To Min");
				errors.add("validityTo",new ActionError("validityTo.Max"));
		  }	
		} catch (ParseException e) {
			e.printStackTrace();
		}
  }

////--END--DEED DETAILS  
   
//**************************************************--START--DEED--- DETAILS ***************************      
}
  
  //           
//        
//        
////    try
////    {
////    int i=Integer.parseInt(firstName);
////    }
////    catch(NumberFormatException e)
////    {
////        errors.add("firstName",new ActionError("firstName.notNumber"));
////    }
//    
//  
//  
       
logger.info("END OF VALIDATE METHOD");      
  return errors;
 
 }*/
//   
   //--END--APPLICANTDETAILS---

  //--START--  Document Details 
//    public void setTypeDocument(String typeDocument) {
//        this.typeDocument = typeDocument;
//    }
//
//    public String getTypeDocument() {
//        return typeDocument;
//    }

//    public void setValidityFrom(String validityFrom) {
//        this.validityFrom = validityFrom;
//    }
//
//    public String getValidityFrom() {
//        return validityFrom;
//    }
//
//    public void setValidityTo(String validityTo) {
//        this.validityTo = validityTo;
//    }
//
//    public String getValidityTo() {
//        return validityTo;
//    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public String getEmailID() {
        return emailID;
    }

//    public void setPurposeDoc(String purposeDoc) {
//        this.purposeDoc = purposeDoc;
//    }
//
//    public String getPurposeDoc() {
//        return purposeDoc;
//    }

    public void setStampDuty(double stampDuty) {
        this.stampDuty = stampDuty;
       // logger.info("stampDutysetting"+this.stampDuty);
    }
//---REQUISITES DETAILS----
    public double getStampDuty() {
        //logger.info("stampDutysetting"+stampDuty);
        return stampDuty;
    }

   

    

    public void setTotal(double total) {
        this.total = total;
    }

    public double getTotal() {
        return total;
    }

    public void setChallanNumber(String challanNumber) {
        this.challanNumber = challanNumber;
    }

    public String getChallanNumber() {
        return challanNumber;
    }

    

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setChallanDate(String challanDate) {
        this.challanDate = challanDate;
    }

    public String getChallanDate() {
        return challanDate;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setChallanAmount(String challanAmount) {
        this.challanAmount = challanAmount;
    }
   
   
    public void setObjEstampDet(EstampDetailsDTO objEstampDet) {
        this.objEstampDet = objEstampDet;
    }

    public EstampDetailsDTO getObjEstampDet() {
        return objEstampDet;
    }

    
    public void setTransID(String transID) {
        this.transID = transID;
    }

    public String getTransID() {
        return transID;
    }

//    public void setTypeDocument(String typeDocument) {
//        this.typeDocument = typeDocument;
//    }
//
//    public String getTypeDocument() {
//        return typeDocument;
//    }

    public void setValFrom(String valFrom) {
        this.valFrom = valFrom;
    }

    public String getValFrom() {
        return valFrom;
    }

    public void setJudicialType(String judicialType) {
        this.judicialType = judicialType;
    }

    public String getJudicialType() {
        return judicialType;
    }

    public void setNonJudicialType(String nonJudicialType) {
        this.nonJudicialType = nonJudicialType;
    }

    public String getNonJudicialType() {
        return nonJudicialType;
    }

//    public void setJudicialTypeList(ArrayList judicialTypeList) {
//        this.judicialTypeList = judicialTypeList;
//    }
//
//    public ArrayList getJudicialTypeList() {
//        return judicialTypeList;
//    }
//
//    public void setNonJudicialTypeList(ArrayList nonJudicialTypeList) {
//        this.nonJudicialTypeList = nonJudicialTypeList;
//    }
//
//    public ArrayList getNonJudicialTypeList() {
//        return nonJudicialTypeList;
//    }

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

	public String getChallanAmount() {
		return challanAmount;
	}

//	public ChallanPaymentDetDTO getObjChallanPaymentDet() {
//		return objChallanPaymentDet;
//	}
//
//	public void setObjChallanPaymentDet(ChallanPaymentDetDTO objChallanPaymentDet) {
//		this.objChallanPaymentDet = objChallanPaymentDet;
//	}

	public DashBoardDTO getObjDashBoard() {
		return objDashBoard;
	}

	public void setObjDashBoard(DashBoardDTO objDashBoard) {
		this.objDashBoard = objDashBoard;
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

	public String getDeedValFrom() {
		return deedValFrom;
	}

	public void setDeedValFrom(String deedValFrom) {
		this.deedValFrom = deedValFrom;
	}

	public String getDeedValTo() {
		return deedValTo;
	}

	public void setDeedValTo(String deedValTo) {
		this.deedValTo = deedValTo;
	}

	public String getApplPartyTypeId() {
		return applPartyTypeId;
	}

	public void setApplPartyTypeId(String applPartyTypeId) {
		this.applPartyTypeId = applPartyTypeId;
	}

	public String getTxn1PartyTypeId() {
		return txn1PartyTypeId;
	}

	public void setTxn1PartyTypeId(String txn1PartyTypeId) {
		this.txn1PartyTypeId = txn1PartyTypeId;
	}

	public String getTxn2PartyTypeId() {
		return txn2PartyTypeId;
	}

	public void setTxn2PartyTypeId(String txn2PartyTypeId) {
		this.txn2PartyTypeId = txn2PartyTypeId;
	}

	public String getTypeDocument() {
		return typeDocument;
	}

	public void setTypeDocument(String typeDocument) {
		this.typeDocument = typeDocument;
	}

	public String getPurposeDoc() {
		return purposeDoc;
	}

	public void setPurposeDoc(String purposeDoc) {
		this.purposeDoc = purposeDoc;
	}

	public String getValidityFrom() {
		return validityFrom;
	}

	public void setValidityFrom(String validityFrom) {
		this.validityFrom = validityFrom;
	}

	public String getValidityTo() {
		return validityTo;
	}

	public void setValidityTo(String validityTo) {
		this.validityTo = validityTo;
	}

	public String getEstampValFrom() {
		return estampValFrom;
	}

	public void setEstampValFrom(String estampValFrom) {
		this.estampValFrom = estampValFrom;
	}

	public String getEstampValTo() {
		return estampValTo;
	}

	public void setEstampValTo(String estampValTo) {
		this.estampValTo = estampValTo;
	}

	public ArrayList getEstampSResult() {
		return estampSResult;
	}

	public void setEstampSResult(ArrayList estampSResult) {
		this.estampSResult = estampSResult;
	}

	/**
	 * @return the exmpIds
	 */
	public String getExmpIds() {
		return exmpIds;
	}

	/**
	 * @param exmpIds the exmpIds to set
	 */
	public void setExmpIds(String exmpIds) {
		this.exmpIds = exmpIds;
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

	/**
	 * @return the pmtTxnId
	 */
	public String getPmtTxnId() {
		return pmtTxnId;
	}

	/**
	 * @param pmtTxnId the pmtTxnId to set
	 */
	public void setPmtTxnId(String pmtTxnId) {
		this.pmtTxnId = pmtTxnId;
	}

	
	/**
	 * @return the docFile
	 */
	public FormFile getDocFile() {
		return docFile;
	}

	
	public void setDocFile(FormFile docFile) {
		this.docFile = docFile;
	}

	/**
	 * @return the uploadDoc
	 */

	/**
	 * @return the upLoadDeed
	 */
	public Object getUpLoadDeed() {
		return upLoadDeed;
	}

	/**
	 * @param deacRemarks the deacRemarks to set
	 */
	public void setDeacRemarks(String deacRemarks) {
		this.deacRemarks = deacRemarks;
	}
	

	public String getDeacRemarks() {
		return deacRemarks;
	}

	/**
	 * @return the applNo
	 */
	public String getApplNo() {
		return applNo;
	}

	/**
	 * @param applNo the applNo to set
	 */
	public void setApplNo(String applNo) {
		this.applNo = applNo;
	}

	/**
	 * @return the modeOfPay
	 */
	public String getModeOfPay() {
		return modeOfPay;
	}

	/**
	 * @param modeOfPay the modeOfPay to set
	 */
	public void setModeOfPay(String modeOfPay) {
		this.modeOfPay = modeOfPay;
	}

	/**
	 * @return the amtStamp
	 */
	public String getAmtStamp() {
		return amtStamp;
	}

	/**
	 * @param amtStamp the amtStamp to set
	 */
	public void setAmtStamp(String amtStamp) {
		this.amtStamp = amtStamp;
	}

	/**
	 * @return the apllNo
	 */
	public String getApllNo() {
		return apllNo;
	}

	/**
	 * @param apllNo the apllNo to set
	 */
	public void setApllNo(String apllNo) {
		this.apllNo = apllNo;
	}

	/**
	 * @return the dateOfEstamp
	 */
	public String getDateOfEstamp() {
		return dateOfEstamp;
	}

	/**
	 * @param dateOfEstamp the dateOfEstamp to set
	 */
	public void setDateOfEstamp(String dateOfEstamp) {
		this.dateOfEstamp = dateOfEstamp;
	}

	/**
	 * @return the estampCode
	 */
	public String getEstampCode() {
		return estampCode;
	}

	/**
	 * @param estampCode the estampCode to set
	 */
	public void setEstampCode(String estampCode) {
		this.estampCode = estampCode;
	}

	/**
	 * @return the deactTxnId
	 */
	public String getDeactTxnId() {
		return deactTxnId;
	}

	/**
	 * @param deactTxnId the deactTxnId to set
	 */
	public void setDeactTxnId(String deactTxnId) {
		this.deactTxnId = deactTxnId;
	}

	/**
	 * @return the retRegInitList
	 */
	public ArrayList getRetRegInitList() {
		return retRegInitList;
	}

	/**
	 * @param retRegInitList the retRegInitList to set
	 */
	public void setRetRegInitList(ArrayList retRegInitList) {
		this.retRegInitList = retRegInitList;
	}

	/**
	 * @return the typeOfStamp
	 */
	public String getTypeOfStamp() {
		return typeOfStamp;
	}

	/**
	 * @param typeOfStamp the typeOfStamp to set
	 */
	public void setTypeOfStamp(String typeOfStamp) {
		this.typeOfStamp = typeOfStamp;
	}

	/**
	 * @return the venderName
	 */
	public String getVenderName() {
		return venderName;
	}

	/**
	 * @param venderName the venderName to set
	 */
	public void setVenderName(String venderName) {
		this.venderName = venderName;
	}

	/**
	 * @return the venderId
	 */
	public String getVenderId() {
		return venderId;
	}

	/**
	 * @param venderId the venderId to set
	 */
	public void setVenderId(String venderId) {
		this.venderId = venderId;
	}

	/**
	 * @return the dateOfIssue
	 */
	public String getDateOfIssue() {
		return dateOfIssue;
	}

	/**
	 * @param dateOfIssue the dateOfIssue to set
	 */
	public void setDateOfIssue(String dateOfIssue) {
		this.dateOfIssue = dateOfIssue;
	}

	/**
	 * @return the sroName
	 */
	public String getSroName() {
		return sroName;
	}

	/**
	 * @param sroName the sroName to set
	 */
	public void setSroName(String sroName) {
		this.sroName = sroName;
	}

	/**
	 * @return the treasuryName
	 */
	public String getTreasuryName() {
		return treasuryName;
	}

	/**
	 * @param treasuryName the treasuryName to set
	 */
	public void setTreasuryName(String treasuryName) {
		this.treasuryName = treasuryName;
	}

	/**
	 * @return the stampType
	 */
	public String getStampType() {
		return stampType;
	}

	/**
	 * @param stampType the stampType to set
	 */
	public void setStampType(String stampType) {
		this.stampType = stampType;
	}

	/**
	 * @return the mstampCode
	 */
	public String getMstampCode() {
		return mstampCode;
	}

	/**
	 * @param mstampCode the mstampCode to set
	 */
	public void setMstampCode(String mstampCode) {
		this.mstampCode = mstampCode;
	}

	/**
	 * @return the denomination
	 */
	public String getDenomination() {
		return denomination;
	}

	/**
	 * @param denomination the denomination to set
	 */
	public void setDenomination(String denomination) {
		this.denomination = denomination;
	}

	/**
	 * @return the mtotalAmt
	 */
	public String getMtotalAmt() {
		return mtotalAmt;
	}

	/**
	 * @param mtotalAmt the mtotalAmt to set
	 */
	public void setMtotalAmt(String mtotalAmt) {
		this.mtotalAmt = mtotalAmt;
	}

	/**
	 * @return the chkIndval
	 */
	public String getChkIndval() {
		return chkIndval;
	}

	/**
	 * @param chkIndval the chkIndval to set
	 */
	public void setChkIndval(String chkIndval) {
		this.chkIndval = chkIndval;
	}

	/**
	 * @return the stampCodeFrom
	 */
	public String getStampCodeFrom() {
		return stampCodeFrom;
	}

	/**
	 * @param stampCodeFrom the stampCodeFrom to set
	 */
	public void setStampCodeFrom(String stampCodeFrom) {
		this.stampCodeFrom = stampCodeFrom;
	}

	/**
	 * @return the stampCodeTo
	 */
	public String getStampCodeTo() {
		return stampCodeTo;
	}

	/**
	 * @param stampCodeTo the stampCodeTo to set
	 */
	public void setStampCodeTo(String stampCodeTo) {
		this.stampCodeTo = stampCodeTo;
	}

	/**
	 * @return the chkStampDel
	 */
	public String getChkStampDel() {
		return chkStampDel;
	}

	/**
	 * @param chkStampDel the chkStampDel to set
	 */
	public void setChkStampDel(String chkStampDel) {
		this.chkStampDel = chkStampDel;
	}

	/**
	 * @return the mstampList
	 */
	public ArrayList getMstampList() {
		return mstampList;
	}

	/**
	 * @param mstampList the mstampList to set
	 */
	public void setMstampList(ArrayList mstampList) {
		this.mstampList = mstampList;
	}

	/**
	 * @return the temp
	 */
	public String getTemp() {
		return temp;
	}

	/**
	 * @param temp the temp to set
	 */
	public void setTemp(String temp) {
		this.temp = temp;
	}

	/**
	 * @return the mdistrict
	 */
	public String getMdistrict() {
		return mdistrict;
	}

	/**
	 * @param mdistrict the mdistrict to set
	 */
	public void setMdistrict(String mdistrict) {
		this.mdistrict = mdistrict;
	}

	/**
	 * @return the sellPlace
	 */
	public String getSellPlace() {
		return sellPlace;
	}

	/**
	 * @param sellPlace the sellPlace to set
	 */
	public void setSellPlace(String sellPlace) {
		this.sellPlace = sellPlace;
	}

	/**
	 * @return the mstResList
	 */
	public ArrayList getMstResList() {
		return mstResList;
	}

	/**
	 * @param mstResList the mstResList to set
	 */
	public void setMstResList(ArrayList mstResList) {
		this.mstResList = mstResList;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the upLoadDoc
	 */
	public FormFile getUpLoadDoc() {
		return upLoadDoc;
	}

	/**
	 * @param upLoadDoc the upLoadDoc to set
	 */
	public void setUpLoadDoc(FormFile upLoadDoc) {
		this.upLoadDoc = upLoadDoc;
	}

	/**
	 * @return the logger
	 */
	

	/**
	 * @return the formList
	 */
	public ArrayList getFormList() {
	    return formList;
	}

	/**
	 * @param formList the formList to set
	 */
	public void setFormList(ArrayList formList) {
	    this.formList = formList;
	}

	/**
	 * @return the instList
	 */
	public ArrayList getInstList() {
	    return instList;
	}

	/**
	 * @param instList the instList to set
	 */
	public void setInstList(ArrayList instList) {
	    this.instList = instList;
	}


	/**
	 * @return the mapForm
	 */
	public HashMap getMapForm() {
	    return mapForm;
	}


	/**
	 * @param mapForm the mapForm to set
	 */
	public void setMapForm(HashMap mapForm) {
	    this.mapForm = mapForm;
	}

	/**
	 * @return the values
	 */
	public HashMap getValues() {
	    return values;
	}

	/**
	 * @param values the values to set
	 */
	public void setValues(HashMap values) {
	    this.values = values;
	}

	public void setUpLoadDeed(FormFile upLoadDeed) {
		this.upLoadDeed = upLoadDeed;
	}

	public void setUpLoadDeed(Object upLoadDeed) {
		this.upLoadDeed = upLoadDeed;
	}

	public DashBoardDTO getObjDashBoardDTO() {
		return objDashBoardDTO;
	}

	public void setObjDashBoardDTO(DashBoardDTO objDashBoardDTO) {
		this.objDashBoardDTO = objDashBoardDTO;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getAlias() {
		return alias;
	}

	public void setCourtType(String courtType) {
		this.courtType = courtType;
	}

	public String getCourtType() {
		return courtType;
	}

	public String getDisttName() {
		return DisttName;
	}

	public void setDisttName(String disttName) {
		DisttName = disttName;
	}
	
	
	
	/**
	 * @return the value
	 */
	/*public String getValue() {
	    return value;
	}*/

	
	/**
	 * @param value the value to set
	 */
	/*public void setValue(String value) {
	    this.value = value;
	}*/

	/**
	 * @return the values
	 */
	/*public Map getValues() {
	    return values;
	}*/

	
	/*public byte[] getFileData() {
		return fileData;
	}

	
	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}*/

	/**
	 * @return the uploadDoc
	 */
	
	
	
	
	
	
	
	
	
}


