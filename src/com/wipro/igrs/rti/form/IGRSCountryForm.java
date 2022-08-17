package com.wipro.igrs.rti.form;

import com.wipro.igrs.rti.dto.IGRSCountryDTO;

//import com.wipro.igrs.rti.dto.RTIRequestDTO;

import com.wipro.igrs.rti.dto.RTIRequestDTO;

import org.apache.struts.action.ActionForm;

public class IGRSCountryForm extends ActionForm {
    private IGRSCountryDTO IGRSCountryDTO = new IGRSCountryDTO();
    RTIRequestDTO requestDTO = new RTIRequestDTO();

    public IGRSCountryForm() {
    }


    public void setRTIRequestDTO(RTIRequestDTO requestDTO) {
        this.requestDTO = requestDTO;
    }

    public RTIRequestDTO getRTIRequestDTO() {
        return requestDTO;
    }

    /* public void setCountryDTO(IGRSCountryDTO countryDTO) {
        this.countryDTO = countryDTO;
    }

    public IGRSCountryDTO getCountryDTO() {
        return countryDTO;
    }*/

    public void setIGRSCountryDTO(IGRSCountryDTO iGRSCountryDTO) {
        this.IGRSCountryDTO = iGRSCountryDTO;
    }

    public IGRSCountryDTO getIGRSCountryDTO() {
        return IGRSCountryDTO;
    }
}
