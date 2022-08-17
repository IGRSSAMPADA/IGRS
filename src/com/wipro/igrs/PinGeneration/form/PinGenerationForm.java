/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   PinGenerationForm.java
 * Author      :   Neesha 
 * Description :   Represents the FormClass for UserRegistration
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0            Neesha 15th Jan, 2007	 		 
 * --------------------------------------------------------------------------------
 */


package com.wipro.igrs.PinGeneration.form;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.PinGeneration.dto.PinGenerationDTO;
import com.wipro.igrs.PinGeneration.dto.IGRSCountryDTO;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;


/**
 * @author neegaga
 *
 */

public class PinGenerationForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	PinGenerationDTO pinGenerationDTO = new PinGenerationDTO();
	IGRSCountryDTO IGRSCountryDTO = new IGRSCountryDTO();
	private String strPinNo;
	private String durationFrom;
	private String durationTo;
	private ArrayList searchResultList=new ArrayList();

//	private ArrayList storedPINsList;
	
	
	
	
public ArrayList getSearchResultList() {
		return searchResultList;
	}

	public void setSearchResultList(ArrayList searchResultList) {
		this.searchResultList = searchResultList;
	}

/*public ActionErrors validate(ActionMapping mapping, 
            HttpServletRequest request) {
		String s="";
		ActionErrors errors = new ActionErrors();
		
		
if(("pinGen1").equalsIgnoreCase(pinGenerationDTO.getAction())) {
			
	
		
        if (pinGenerationDTO.getFirstName() == null ||
        		pinGenerationDTO.getFirstName().length() == 0) {
        	
            errors.add("firstName",
                       new ActionError("errors.firstname.required"));
        }
        if (pinGenerationDTO.getFirstName() != null) {
            s =pinGenerationDTO.getFirstName();
            
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
        if (pinGenerationDTO.getLastName() == null ||
        		pinGenerationDTO.getLastName().length() == 0) {
            errors.add("lastName",
                       new ActionError("errors.lastname.required"));
        }
        if (pinGenerationDTO.getLastName() != null) {
            s = pinGenerationDTO.getLastName();
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
        if (pinGenerationDTO.getMiddleName() != null) {
            s = pinGenerationDTO.getMiddleName();
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

        if (pinGenerationDTO.getGender() == null ||
            pinGenerationDTO.getGender().length() == 0) {
            errors.add("gender", new ActionError("errors.gender.required"));
        }
        
        try{
            if (pinGenerationDTO.getAge().length() <= 0) {
            } else if (pinGenerationDTO.getAge().length() >0) {
            try {
                int age = Integer.parseInt(pinGenerationDTO.getAge());
                if (age < -1 || age > 100) {
                    errors.add("age", new ActionError("errors.age.range"));
                }
            } catch (Exception e) {
                errors.add("age", new ActionError("errors.age.integer"));
            }
        }
        }catch(Exception e){ }
        if (pinGenerationDTO.getFatherName() == null ||
            pinGenerationDTO.getFatherName().length() == 0) {
            errors.add("fatherName",
                       new ActionError("errors.fathername.required"));
        }
        if (pinGenerationDTO.getFatherName() != null) {
            s = pinGenerationDTO.getFatherName();
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
        if (pinGenerationDTO.getMotherName() == null ||
            pinGenerationDTO.getMotherName().length() == 0) {
            errors.add("motherName",
                       new ActionError("errors.mothername.required"));
        }
        if (pinGenerationDTO.getMotherName() != null) {
            s = pinGenerationDTO.getMotherName();
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
        
        if (pinGenerationDTO.getAddress() == null ||
            pinGenerationDTO.getAddress().length() == 0) {
            errors.add("address", new ActionError("errors.address.required"));
        }
        if (("[Select]").equalsIgnoreCase(pinGenerationDTO.getDistrict())||pinGenerationDTO.getDistrict() == null || pinGenerationDTO.getDistrict().length() == 0) {
            errors.add("city", new ActionError("errors.city.required"));
        }

        if (("[Select]").equalsIgnoreCase(pinGenerationDTO.getCountry())||pinGenerationDTO.getCountry() == null ||
            pinGenerationDTO.getCountry().length() == 0) {
            errors.add("country", new ActionError("errors.country.required"));
        }
        if (("[Select]").equalsIgnoreCase(pinGenerationDTO.getState())||pinGenerationDTO.getState() == null ||
            pinGenerationDTO.getState().length() == 0) {
            errors.add("state", new ActionError("errors.state.required"));
        }
        
        try{
        if (pinGenerationDTO.getMobileNumber().length() > 0) {
            try {
                Long.parseLong(pinGenerationDTO.getMobileNumber());
            } catch (Exception e) {
                errors.add("mobileNumber",
                           new ActionError("errors.mobilenumber.integer"));
            }
        }
        if (pinGenerationDTO.getPhoneNumber().length() > 0) {
            try {
                Long.parseLong(pinGenerationDTO.getPhoneNumber());
            } catch (Exception e) {
                errors.add("phoneNumber",
                           new ActionError("errors.phonenumber.integer"));
            }
        }
        }
        catch(Exception e){} 

        
    }

		//String s="";
		return errors;
	}*/

	/**
	 * @return the storedPINsList
	 */
//	public ArrayList getStoredPINsList() {
//		return storedPINsList;
//	}

	/**
	 * @param storedPINsList the storedPINsList to set
	 */
//	public void setStoredPINsList(ArrayList storedPINsList) {
//		this.storedPINsList = storedPINsList;
//	}

	

	/**
	 * @return the PinGenerationDTO
	 */
	public PinGenerationDTO getPinGenerationDTO() {
		return pinGenerationDTO;
	}

	/**
	 * @param PinGenerationDTO the PinGenerationDTO to set
	 */
	public void setPinGenerationDTO(PinGenerationDTO pinGenerationDTO) {
		this.pinGenerationDTO = pinGenerationDTO;
	}

	/**
	 * @return the strPinNo
	 */
	public String getStrPinNo() {
		return strPinNo;
	}

	/**
	 * @param strPinNo the strPinNo to set
	 */
	public void setStrPinNo(String strPinNo) {
		this.strPinNo = strPinNo;
	}

	public IGRSCountryDTO getIGRSCountryDTO() {
		return IGRSCountryDTO;
	}

	public void setIGRSCountryDTO(IGRSCountryDTO scountryDTO) {
		IGRSCountryDTO = scountryDTO;
	}

	public String getDurationFrom() {
		return durationFrom;
	}

	public void setDurationFrom(String durationFrom) {
		this.durationFrom = durationFrom;
	}

	public String getDurationTo() {
		return durationTo;
	}

	public void setDurationTo(String durationTo) {
		this.durationTo = durationTo;
	}

	

	
	
}
