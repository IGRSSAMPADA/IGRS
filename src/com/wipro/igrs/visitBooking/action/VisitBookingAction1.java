package com.wipro.igrs.visitBooking.action;

/**
 * ===========================================================================
 * File           :   VisitBookingAction1.java
 * Description    :   Represents the Action Class to getting the Drop Down values

 * Author         :   pavani Param
 * Created Date   :   Apr 08, 2008.

 * ===========================================================================
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.visitBooking.bd.VisitBookingBD;
import com.wipro.igrs.visitBooking.dto.VisitBookingDTO;
import com.wipro.igrs.visitBooking.form.VisitBookingForm;
public class VisitBookingAction1 extends BaseAction {
	private Logger logger = (Logger) Logger.getLogger(VisitBookingAction1.class);
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,HttpSession session)
			throws IOException, ServletException {

		
		//HttpSession session = request.getSession(false);
		String functionId = null;
		String userType = null;
		String serviceId = null;
		functionId = (String)request.getParameter("functionId");
		if(functionId ==  null)
		   {
			   functionId = "FUN_030";
		   }
		userType = (String)session.getAttribute("role");
	   if(userType ==  null)
		   {
		   userType = "SRO";
		   }
		ArrayList otherFeeVal = null;
		float total = 0;
		String refId;
		
		VisitBookingForm visitForm;
		VisitBookingDTO objVisDTo;
		ArrayList stateList = new ArrayList();
		ArrayList districtList = new ArrayList();
		VisitBookingBD objBD = new VisitBookingBD();
		String regNo=request.getParameter("reqApplNo");
		
		if(regNo != null && regNo.length() > 0){
			PrintWriter out = response.getWriter();
			String reqApplNo=request.getParameter("reqApplNo");
			//check Application No
			 String sbEmployee =objBD.checkAppNo(reqApplNo);
			logger.debug("Just to check the value "+sbEmployee);
			out.print(sbEmployee);
			return null;
		}
		
		if (session.getAttribute("visitFormSess") != null) {
			visitForm = (VisitBookingForm) session.getAttribute("visitFormSess");
			objVisDTo = visitForm.getObjVBDto();
		}else{
		visitForm = (VisitBookingForm) form;
		objVisDTo = new VisitBookingDTO();
		}
		try {

			
			ArrayList countryList = objBD.getCountry();
			String strCountryID = request.getParameter("countryID");
			if (strCountryID != null)
				stateList = objBD.getState(strCountryID);
			String strStateID = request.getParameter("stateID");
			if (strStateID != null) {
				districtList = objBD.getDistrict(strStateID);
			}
			ArrayList photoIdList = objBD.getphotoIdList();
			objVisDTo.setCountryList(countryList);
			objVisDTo.setStateList(stateList);
			objVisDTo.setDistrictList(districtList);
			objVisDTo.setPhotoIdList(photoIdList);
			objVisDTo.setVstDistList(districtList);
			
			refId = request.getParameter("referenceID");
			logger.debug("VisitBookingAction1--  referenceID = " + refId);
			if (refId != null) {
				logger.debug("VisitBookingAction1--in side if  referenceID = "+ refId);
				visitForm.setReferenceID(refId);
				visitForm = objBD.getVisitBookViewDet(visitForm);
				logger.debug("VisitBookingAction1--in side if  getRemarksList() = "+ visitForm.getRemarksList());
			}
			try{
				
				String feeVal = objBD.getVisitBookFeeVal(functionId);
				logger.debug("VisitBookingAction1 -- feeVal ="+feeVal);
				if(feeVal != null)
					if(!feeVal.equalsIgnoreCase("")){
						float temp = Float.parseFloat(feeVal);
						visitForm.setFee(String.valueOf(temp));			
					}
				otherFeeVal = objBD.getOthersFeeDuty(functionId, serviceId, userType);
				logger.debug("VisitBookingAction1 -- otherFeeVal ="+otherFeeVal);
				if(otherFeeVal != null)
				{
				visitForm.setOthers((String)otherFeeVal.get(2));
				total = Float.parseFloat(visitForm.getFee())+ Float.parseFloat(visitForm.getOthers());
				visitForm.setTotal(String.valueOf(total));
				logger.debug("VisitBookingAction1 -- total ="+total);
				}
				}catch (Exception e) {
					e.printStackTrace();
					visitForm.setFee("0.0");
					visitForm.setTotal("0.0");
				}				
			visitForm.setObjVBDto(objVisDTo);
			session.setAttribute("visitFormSess", visitForm);
		} catch (Exception e) {
			e.printStackTrace();

		}

		return mapping.findForward("success");
	}

}
