package com.wipro.igrs.rti.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.common.CommonConstant;
import com.wipro.igrs.rti.bd.RTIBD;
import com.wipro.igrs.rti.dto.IGRSCountryDTO;
import com.wipro.igrs.rti.dto.RTIRequestDTO;
import com.wipro.igrs.rti.form.IGRSCountryForm;


public class UserLoginAction extends BaseAction {
    public UserLoginAction() {
    }

    public ActionForward execute(ActionMapping mapping, ActionForm form, 
                                 HttpServletRequest request, 
                                 HttpServletResponse response,HttpSession session) throws ServletException, 
                                                                      IOException {

        RTIBD bd = new RTIBD();
        IGRSCountryForm IGRSCountryForm = (IGRSCountryForm)form;
        IGRSCountryDTO countryDTO = new IGRSCountryDTO();
        System.out.println("\n\n\ndistrict :" + 
                           IGRSCountryForm.getRTIRequestDTO().getRtiRequestOneAction());
        if ((CommonConstant.RTI_REQUEST_DISTRICT).equalsIgnoreCase(IGRSCountryForm.getRTIRequestDTO().getRtiRequestOneAction())) {
            String state = request.getParameter("RTIRef");
            String country = IGRSCountryForm.getRTIRequestDTO().getCountry();
      //      HttpSession session = request.getSession();
            session.setAttribute("country", country);
            ArrayList districtlist = bd.getDistrictList(state);
            countryDTO.setDistrictList(districtlist);
        } else {
            ArrayList countrylist = bd.getCountryList();
            ArrayList statelist = bd.getStateList();
            ArrayList idlist = bd.getphotoIdList();

            RTIRequestDTO requestDTO = new RTIRequestDTO();
            countryDTO.setCountryList(countrylist);
            countryDTO.setStateList(statelist);

            requestDTO.setIdlist(idlist);
            IGRSCountryForm.setRTIRequestDTO(requestDTO);
        }
        IGRSCountryForm.setIGRSCountryDTO(countryDTO);
        //HttpSession session = request.getSession();

        //session.setAttribute("requestForm",requestForm);
        session.setAttribute("IGRSCountryForm", IGRSCountryForm);

        return mapping.findForward("success");
        //return mapping.findForward("failure");
    }
}
