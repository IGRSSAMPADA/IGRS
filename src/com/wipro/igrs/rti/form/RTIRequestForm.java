/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   RTIRequestForm.java
 * Author      :   Samuel Prabhakaran
 * 
 * ----------------------------------------------------------------------------
 * Version         Last Modified By     Last Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Samuel Prabhakaran       17th March, 2008	 		
 * --------------------------------------------------------------------------------
 */



package com.wipro.igrs.rti.form;


import com.wipro.igrs.common.CommonConstant;
import com.wipro.igrs.rti.dto.IGRSCountryDTO;
import com.wipro.igrs.rti.dto.RTIRequestDTO;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;


//import com.wipro.igrs.rule.RTIRequestRule;

public class RTIRequestForm extends ActionForm {
    private String challandate;
    private RTIRequestDTO RTIRequestDTO = new RTIRequestDTO();
    private IGRSCountryDTO IGRSCountryDTO = new IGRSCountryDTO();

    private String next;
    private String resolutionInformation;
    private String durationfrom;
    private String durationto;

    private String radiobuttonPayment1;
    private String chkChallanDel;
    private String challanDate0;
    private String challanDate;
    private String cashReceiptID;
    private String challanNo;
    private String amount;
    private String bankName;
    private String datefield;


    private String firstName;
    private String middleName;
    private String lastName;
    private String gender;
    private String age;
    private String fatherName;
    private String motherName;
    private String address;
    private String postelCode;
    private String phoneNumber;
    private String requestInformation;
    
    private String dueDate;
    private String emailId;
    private String textEmailId;
    private String sms;
    private String textSMS;
    private String post;
    private String country;
    private String state;
    private String sistrict;
    private String rtiStatusTwoAction;

 
    public ActionErrors validate(ActionMapping mapping, 
                                 HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
       String s="";

	   String label = request.getParameter("label");
		if ("view".equalsIgnoreCase(label) || "update".equalsIgnoreCase(label)
				|| "report".equalsIgnoreCase(label) || "assign".equals(label)
				|| "request".equals(label)) {
			return errors;
		}	
       if("country".equalsIgnoreCase(RTIRequestDTO.getRtiRequestOneAction())||"district".equalsIgnoreCase(RTIRequestDTO.getRtiRequestOneAction())){
           return errors;
       }
       
       if((CommonConstant.RTIRequest_UD_00).equalsIgnoreCase(RTIRequestDTO.getRtiRequest_Form())||(CommonConstant.RTIRequest_VI_00).equalsIgnoreCase(RTIRequestDTO.getRtiRequest_Form())){
           return errors;
       }
       
        if((CommonConstant.RTIRequest_UD_02).equalsIgnoreCase(RTIRequestDTO.getRtiRequest_Form())||(CommonConstant.RTIRequest_VI_02).equalsIgnoreCase(RTIRequestDTO.getRtiRequest_Form())){
            return errors;
        }
        
        if((CommonConstant.RTIRequest_UD_03).equalsIgnoreCase(RTIRequestDTO.getRtiRequest_Form())||(CommonConstant.RTIRequest_VI_02).equalsIgnoreCase(RTIRequestDTO.getRtiRequest_Form())){
        	
            return errors;
        }
        
        if((CommonConstant.RTI_REQUEST_UD_05).equalsIgnoreCase(RTIRequestDTO.getRtiRequest_Form())||(CommonConstant.RTI_REQUEST_UD_06).equalsIgnoreCase(RTIRequestDTO.getRtiRequest_Form())){
            return errors;
        }
        
        
        if((CommonConstant.RTIRequest_UD_07).equalsIgnoreCase(RTIRequestDTO.getRtiRequest_Form())) {
			
			return errors;
		}
    
        if((CommonConstant.RTIRequest_AS_00).equalsIgnoreCase(RTIRequestDTO.getRtiRequest_Form())) {
			
			return errors;
		}
        
        if((CommonConstant.RTIRequest_AS_02).equalsIgnoreCase(RTIRequestDTO.getRtiRequest_Form())) {
			
			return errors;
		}
        
 if((CommonConstant.RTI_INTIMATION).equalsIgnoreCase(RTIRequestDTO.getRtiRequestOneAction())) {
			
			return errors;
		}
 if((CommonConstant.RTI_INTIMATION).equalsIgnoreCase(RTIRequestDTO.getRtiRequestThreeAction())) {
		
		return errors;
	}
        
        if((CommonConstant.RTIRequest_AS_03).equalsIgnoreCase(RTIRequestDTO.getRtiRequest_Form())) {
			
			return errors;
		}
        if (RTIRequestDTO.getFirstName() == null ||
            RTIRequestDTO.getFirstName().length() == 0) {
            errors.add("firstName",
                       new ActionError("errors.firstname.required"));
        }
        if (RTIRequestDTO.getFirstName() != null) {
            s =RTIRequestDTO.getFirstName();
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == '1' || c == '2' || c == '3' || c == '4' || c == '5' ||
                    c == '6' || c == '7' || c == '8' || c == '9' || c == '0' ||
                    c == '!' || c == '@' || c == '#' || c == '$' || c == '%' ||
                    c == '^' || c == '&' || c == '*' || c == '(' || c == ')' ||
                    c == '_' || c == '-' || c == '+' || c == '=' || c == '|' ||
                    c == '/' || c == '<' || c == '>' || c == '?' || c == ':' ||
                    c == ';' || c == '"' || c == '{' || c == '}' || c == '[' ||
                    c == ']') {
                    errors.add("firstName",
                               new ActionError("error.name.alphabet"));
                    break;
                }
            }
        }
        if (RTIRequestDTO.getLastName() == null ||
            RTIRequestDTO.getLastName().length() == 0) {
            errors.add("lastName",
                       new ActionError("errors.lastname.required"));
        }
        if (RTIRequestDTO.getLastName() != null) {
            s = RTIRequestDTO.getLastName();
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == '1' || c == '2' || c == '3' || c == '4' || c == '5' ||
                    c == '6' || c == '7' || c == '8' || c == '9' || c == '0' ||
                    c == '!' || c == '@' || c == '#' || c == '$' || c == '%' ||
                    c == '^' || c == '&' || c == '*' || c == '(' || c == ')' ||
                    c == '_' || c == '-' || c == '+' || c == '=' || c == '|' ||
                    c == '/' || c == '<' || c == '>' || c == '?' || c == ':' ||
                    c == ';' || c == '"' || c == '{' || c == '}' || c == '[' ||
                    c == ']') {
                    errors.add("firstName",
                               new ActionError("error.name.alphabet"));
                    break;
                }
            }
        }
        if (RTIRequestDTO.getMiddleName() != null) {
            s = RTIRequestDTO.getMiddleName();
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == '1' || c == '2' || c == '3' || c == '4' || c == '5' ||
                    c == '6' || c == '7' || c == '8' || c == '9' || c == '0' ||
                    c == '!' || c == '@' || c == '#' || c == '$' || c == '%' ||
                    c == '^' || c == '&' || c == '*' || c == '(' || c == ')' ||
                    c == '_' || c == '-' || c == '+' || c == '=' || c == '|' ||
                    c == '/' || c == '<' || c == '>' || c == '?' || c == ':' ||
                    c == ';' || c == '"' || c == '{' || c == '}' || c == '[' ||
                    c == ']') {
                    errors.add("firstName",
                               new ActionError("error.name.alphabet"));
                    break;
                }
            }
        }

        if (RTIRequestDTO.getGender() == null ||
            RTIRequestDTO.getGender().length() == 0) {
            errors.add("gender", new ActionError("errors.gender.required"));
        }
        
        try{
            if (RTIRequestDTO.getAge().length() <= 0) {
            } else if (RTIRequestDTO.getAge().length() >0) {
            try {
                int age = Integer.parseInt(RTIRequestDTO.getAge());
                if (age < -1 || age > 100) {
                    errors.add("age", new ActionError("errors.age.range"));
                }
            } catch (Exception e) {
                errors.add("age", new ActionError("errors.age.integer"));
            }
        }
        }catch(Exception e){ }
        if (RTIRequestDTO.getFatherName() == null ||
            RTIRequestDTO.getFatherName().length() == 0) {
            errors.add("fatherName",
                       new ActionError("errors.fathername.required"));
        }
        if (RTIRequestDTO.getFatherName() != null) {
            s = RTIRequestDTO.getFatherName();
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == '1' || c == '2' || c == '3' || c == '4' || c == '5' ||
                    c == '6' || c == '7' || c == '8' || c == '9' || c == '0' ||
                    c == '!' || c == '@' || c == '#' || c == '$' || c == '%' ||
                    c == '^' || c == '&' || c == '*' || c == '(' || c == ')' ||
                    c == '_' || c == '-' || c == '+' || c == '=' || c == '|' ||
                    c == '/' || c == '<' || c == '>' || c == '?' || c == ':' ||
                    c == ';' || c == '"' || c == '{' || c == '}' || c == '[' ||
                    c == ']') {
                    errors.add("firstName",
                               new ActionError("error.name.alphabet"));
                    break;
                }
            }
        }
       /* if (RTIRequestDTO.getMotherName() == null ||
            RTIRequestDTO.getMotherName().length() == 0) {
            errors.add("motherName",
                       new ActionError("errors.mothername.required"));
        }*/
        if (RTIRequestDTO.getMotherName() != null) {
            s = RTIRequestDTO.getMotherName();
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == '1' || c == '2' || c == '3' || c == '4' || c == '5' ||
                    c == '6' || c == '7' || c == '8' || c == '9' || c == '0' ||
                    c == '!' || c == '@' || c == '#' || c == '$' || c == '%' ||
                    c == '^' || c == '&' || c == '*' || c == '(' || c == ')' ||
                    c == '_' || c == '-' || c == '+' || c == '=' || c == '|' ||
                    c == '/' || c == '<' || c == '>' || c == '?' || c == ':' ||
                    c == ';' || c == '"' || c == '{' || c == '}' || c == '[' ||
                    c == ']') {
                    errors.add("firstName",
                               new ActionError("error.name.alphabet"));
                    break;
                }
            }
        }
        if (RTIRequestDTO.getTextEmailId() != null) {
            int i = RTIRequestDTO.getTextEmailId().indexOf("@");
            if (i == -1) {
                errors.add("emailId", new ActionError("error.email.notvalid"));
            }
        }
        if (RTIRequestDTO.getAddress() == null ||
            RTIRequestDTO.getAddress().length() == 0) {
            errors.add("address", new ActionError("errors.address.required"));
        }
        if (("[Select]").equalsIgnoreCase(RTIRequestDTO.getDistrict())||RTIRequestDTO.getDistrict() == null || RTIRequestDTO.getDistrict().length() == 0) {
            errors.add("city", new ActionError("errors.city.required"));
        }

        if (("[Select]").equalsIgnoreCase(RTIRequestDTO.getCountry())||RTIRequestDTO.getCountry() == null ||
            RTIRequestDTO.getCountry().length() == 0) {
            errors.add("country", new ActionError("errors.country.required"));
        }
        if (("[Select]").equalsIgnoreCase(RTIRequestDTO.getState())||RTIRequestDTO.getState() == null ||
            RTIRequestDTO.getState().length() == 0) {
            errors.add("state", new ActionError("errors.state.required"));
        }
        if (RTIRequestDTO.getRequestInformation() == null ||
            RTIRequestDTO.getRequestInformation().length() == 0) {
            errors.add("requestInfirmation",
                       new ActionError("errors.requestinformation.required"));

        }
        try{
        if (RTIRequestDTO.getMobileNumber().length() > 0) {
            try {
                Long.parseLong(RTIRequestDTO.getMobileNumber());
            } catch (Exception e) {
                errors.add("mobileNumber",
                           new ActionError("errors.mobilenumber.integer"));
            }
        }
        if (RTIRequestDTO.getPhoneNumber().length() > 0) {
            try {
                Long.parseLong(RTIRequestDTO.getPhoneNumber());
            } catch (Exception e) {
                errors.add("phoneNumber",
                           new ActionError("errors.phonenumber.integer"));
            }
        }
        }
        catch(Exception e){} 

        return errors;
    } 


    public void setChallandate(String challandate) {

        this.challandate = challandate;
    }

    public String getChallandate() {
        return challandate;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getNext() {
        return next;
    }


    public void setRadiobuttonPayment1(String radiobuttonPayment1) {
        RTIRequestDTO.setRadiobuttonPayment1(radiobuttonPayment1);
        this.radiobuttonPayment1 = radiobuttonPayment1;
    }

    public String getRadiobuttonPayment1() {
        return radiobuttonPayment1;
    }

    public void setChkChallanDel(String chkChallanDel) {
        this.chkChallanDel = chkChallanDel;
    }

    public String getChkChallanDel() {
        return chkChallanDel;
    }


   

    public void setCashReceiptID(String cashReceiptID) {
        RTIRequestDTO.setCashReceiptID(cashReceiptID);
        this.cashReceiptID = cashReceiptID;
    }

    public String getCashReceiptID() {
        return cashReceiptID;
    }

    public void setChallanNo(String challanNo) {
        RTIRequestDTO.setChallanNo(challanNo);
        this.challanNo = challanNo;
    }

    public String getChallanNo() {
        return challanNo;
    }

    public void setAmount(String amount) {
        RTIRequestDTO.setAmount(amount);
        this.amount = amount;
    }

    public String getAmount() {
        return amount;
    }

    public void setBankName(String bankName) {
        RTIRequestDTO.setBankName(bankName);
        this.bankName = bankName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setFirstName(String firstName) {
        RTIRequestDTO.setFirstName(firstName);
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setMiddleName(String middleName) {
        RTIRequestDTO.setMiddleName(middleName);
        this.middleName = middleName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setLastName(String lastName) {
        RTIRequestDTO.setLastName(lastName);
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setGender(String gender) {
        RTIRequestDTO.setGender(gender);
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setAge(String age) {
        RTIRequestDTO.setAge(age);
        this.age = age;
    }

    public String getAge() {
        return age;
    }

    public void setFatherName(String fatherName) {
        RTIRequestDTO.setFatherName(fatherName);
        this.fatherName = fatherName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setMotherName(String motherName) {
        RTIRequestDTO.setMotherName(motherName);
        this.motherName = motherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setAddress(String address) {
        RTIRequestDTO.setAddress(address);
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setPostelCode(String postelCode) {
        RTIRequestDTO.setPostelCode(postelCode);
        this.postelCode = postelCode;
    }

    public String getPostelCode() {
        return postelCode;
    }

    public void setPhoneNumber(String phoneNumber) {
        RTIRequestDTO.setPhoneNumber(phoneNumber);
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setRequestInformation(String requestInformation) {
        RTIRequestDTO.setRequestInformation(requestInformation);
        this.requestInformation = requestInformation;
    }

    public String getRequestInformation() {
        return requestInformation;
    }

    public void setEmailId(String emailId) {
       
        RTIRequestDTO.setEmailId(emailId);
        this.emailId = emailId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setTextEmailId(String textEmailId) {
        RTIRequestDTO.setTextEmailId(textEmailId);
        this.textEmailId = textEmailId;
    }

    public String getTextEmailId() {
        return textEmailId;
    }

    public void setSms(String sms) {
        RTIRequestDTO.setSms(sms);
        this.sms = sms;
    }

    public String getSms() {
        return sms;
    }

    public void setTextSMS(String textSMS) {
        RTIRequestDTO.setTextSMS(textSMS);
        this.textSMS = textSMS;
    }

    public String getTextSMS() {
        return textSMS;
    }

    public void setPost(String post) {
        RTIRequestDTO.setPost(post);
        this.post = post;
    }

    public String getPost() {
        return post;
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

    public void setSistrict(String sistrict) {
        this.sistrict = sistrict;
    }

    public String getSistrict() {
        return sistrict;
    }

    public void setIGRSCountryDTO(IGRSCountryDTO iGRSCountryDTO) {
        this.IGRSCountryDTO = iGRSCountryDTO;
    }

    public IGRSCountryDTO getIGRSCountryDTO() {
        return IGRSCountryDTO;
    }

    public void setChallanDate0(String challanDate0) {
        this.challanDate0 = challanDate0;
    }

    public String getChallanDate0() {
        return challanDate0;
    }

    public void setDurationfrom(String durationfrom) {
        this.durationfrom = durationfrom;
    }

    public String getDurationfrom() {
        return durationfrom;
    }

    public void setDurationto(String durationto) {
        this.durationto = durationto;
    }

    public String getDurationto() {
        return durationto;
    }

    public void setResolutionInformation(String resolutionInformation) {
        this.resolutionInformation = resolutionInformation;
    }

    public String getResolutionInformation() {
        return resolutionInformation;
    }

	public String getRtiStatusTwoAction() {
		return rtiStatusTwoAction;
	}

	public void setRtiStatusTwoAction(String rtiStatusTwoAction) {
		this.rtiStatusTwoAction = rtiStatusTwoAction;
	}
	   public void setRTIRequestDTO(RTIRequestDTO rtireqdto) {
	        this.RTIRequestDTO = rtireqdto;

	    }

	    public RTIRequestDTO getRTIRequestDTO() {
	        return RTIRequestDTO;
	    }


		public String getChallanDate() {
			return challanDate;
		}


		public void setChallanDate(String challanDate) {
			this.challanDate = challanDate;
		}


		public String getDatefield() {
			return datefield;
		}


		public void setDatefield(String datefield) {
			this.datefield = datefield;
		}


		public String getDueDate() {
			return dueDate;
		}


		public void setDueDate(String dueDate) {
			RTIRequestDTO.setDueDate(dueDate);
			this.dueDate = dueDate;
		}

}

