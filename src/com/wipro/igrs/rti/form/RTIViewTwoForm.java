package com.wipro.igrs.rti.form;

//import com.wipro.igrs.rti.dto.RTIStatusTwoDTO;

import com.wipro.igrs.rti.dto.RTIRequestDTO;

import org.apache.struts.action.ActionForm;


public class RTIViewTwoForm extends ActionForm {
    public RTIViewTwoForm() {
    }

    RTIRequestDTO RTIDTO = new RTIRequestDTO();


    public void setRTIDTO(RTIRequestDTO rTIDTO) {
        this.RTIDTO = rTIDTO;
    }

    public RTIRequestDTO getRTIDTO() {
        return RTIDTO;
    }
}
