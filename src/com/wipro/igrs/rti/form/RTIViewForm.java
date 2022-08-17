package com.wipro.igrs.rti.form;


//import com.wipro.igrs.rti.dto.RTIStatusDTO;

import com.wipro.igrs.rti.dto.RTIRequestDTO;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class RTIViewForm extends ActionForm {
    private RTIRequestDTO RTIRequestDTO = new RTIRequestDTO();
    private String durationfrom;
    private String durationto;

    public ActionErrors validate(ActionMapping mapping, 
                                 HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();

        /*    if (rtiDTO.getRtiID() == null ||
            rtiDTO.getRtiID().length() == 0) {
            errors.add("refernceid",
                       new ActionError("errors.referenceid.required"));

        }*/
        /*   if (rtiDTO.getDurationFrom() == null ||
            rtiDTO.getDurationFrom().length() == 0) {
            errors.add("durationfrom",
                       new ActionError("errors.fromdate.required"));

        }
        if (rtiDTO.getDurationTo() == null ||
            rtiDTO.getDurationTo().length() == 0) {
            errors.add("durationto",
                       new ActionError("errors.todate.required"));

        }
        if (rtiDTO.getRtiStatus().equalsIgnoreCase("Select")) {

            errors.add("status", new ActionError("errors.status.required"));

        }*/
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


   


	public RTIRequestDTO getRTIRequestDTO() {
		return RTIRequestDTO;
	}


	public void setRTIRequestDTO(RTIRequestDTO requestDTO) {
		RTIRequestDTO = requestDTO;
	}
}
