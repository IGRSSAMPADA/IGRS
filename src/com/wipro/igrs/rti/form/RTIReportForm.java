package com.wipro.igrs.rti.form;

//import com.wipro.igrs.rti.dto.RTIReportDTO;

import com.wipro.igrs.rti.dto.RTIRequestDTO;

import org.apache.struts.action.ActionForm;

public class RTIReportForm extends ActionForm {
    String durationFrom;
    String durationTo;

    public RTIReportForm() {
    }
    RTIRequestDTO rTIReportDTO = new RTIRequestDTO();

    public void setRTIReportDTO(RTIRequestDTO rTIReportDTO) {
        this.rTIReportDTO = rTIReportDTO;
    }

    public RTIRequestDTO getRTIReportDTO() {


        return rTIReportDTO;
    }

    public void setDurationFrom(String durationFrom) {
        this.durationFrom = durationFrom;
    }

    public String getDurationFrom() {
        return durationFrom;
    }

    public void setDurationTo(String durationTo) {
        this.durationTo = durationTo;
    }

    public String getDurationTo() {
        return durationTo;
    }
}
