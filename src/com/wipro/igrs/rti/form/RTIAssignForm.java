package com.wipro.igrs.rti.form;

//import com.wipro.igrs.rti.dto.RTIAssignDTO;

import com.wipro.igrs.rti.dto.RTIRequestDTO;

import org.apache.struts.action.ActionForm;

public class RTIAssignForm extends ActionForm {
    public RTIAssignForm() {
    }
    RTIRequestDTO rtiDTO = new RTIRequestDTO();


    public void setRtiDTO(RTIRequestDTO rtiDTO) {
        this.rtiDTO = rtiDTO;
    }

    public RTIRequestDTO getRtiDTO() {
        return rtiDTO;
    }
}
