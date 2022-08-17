package com.wipro.igrs.rti.form;

//import com.wipro.igrs.rti.dto.RTIStatusDTO;

//import com.wipro.igrs.rti.dto.RTIStatusDTO;

import com.wipro.igrs.rti.dto.RTIRequestDTO;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;


public class RTIStatusForm extends ActionForm {
    private String durationfrom;
    private String durationto;
    private RTIRequestDTO rtiDTO = new RTIRequestDTO();

    public ActionErrors validate(ActionMapping mapping, 
                                 HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();

        /*   if (rtiDTO.getRtiID() == null ||
            rtiDTO.getRtiID().length() == 0) {
            errors.add("refernceid",
                       new ActionError("errors.referenceid.required"));

        } */
        /*   if (durationfrom == null || durationfrom.length() == 0) {
            errors.add("durationfrom",
                       new ActionError("errors.fromdate.required"));

        }
        if (durationto == null || durationto.length() == 0) {
            errors.add("durationto",
                       new ActionError("errors.todate.required"));

        }
        if (rtistatus.getPurposeofloan().equalsIgnoreCase("Select")) {

            errors.add("status", new ActionError("errors.status.required"));

        } */
        return errors;
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

    public void setRtistatus(RTIRequestDTO rtistatus) {
        this.rtiDTO = rtistatus;
    }

    public RTIRequestDTO getRtistatus() {
        return rtiDTO;
    }

    public void setRtiDTO(RTIRequestDTO rtiDTO) {
        this.rtiDTO = rtiDTO;
    }

    public RTIRequestDTO getRtiDTO() {
        return rtiDTO;
    }
}
