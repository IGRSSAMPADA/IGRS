/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   RTIRequestAction.java
 * Author      :   Samuel Prabhakaran
 * 
 * ----------------------------------------------------------------------------
 * Version         Last Modified By     Last Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Samuel Prabhakaran       17th March, 2008	 		
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.rti.action;

import java.io.IOException;
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
import com.wipro.igrs.common.CommonConstant;
import com.wipro.igrs.rti.bd.RTIBD;
import com.wipro.igrs.rti.dto.IGRSCountryDTO;
import com.wipro.igrs.rti.dto.RTIRequestDTO;
import com.wipro.igrs.rti.form.RTIRequestForm;

public class RTIRequestAction extends BaseAction {

    private  Logger logger = 
	(Logger) Logger.getLogger(RTIRequestAction.class);
	public RTIRequestAction() {
	}
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,HttpSession session)
			throws ServletException, IOException {
		RTIRequestForm rtiform = (RTIRequestForm) form;
		
		//HttpSession session=request.getSession();
		RTIBD rbd = new RTIBD();

		IGRSCountryDTO countryDTO = rtiform.getIGRSCountryDTO();
		//user role,function id,user id
		String paramIds[] = new String[3];
			paramIds[0] = (String)session.getAttribute("roleId");
			paramIds[1] = (String)session.getAttribute("functionId");
			paramIds[2] = (String)session.getAttribute("UserId");
		
		String label=request.getParameter("label");
				
		if(label!=null){
			
			
			if(label.equalsIgnoreCase("request")){
				
				rbd = new RTIBD();
				ArrayList countrylist = rbd.getCountryList();
				ArrayList statelist = rbd.getStateList();
				ArrayList idlist = rbd.getphotoIdList();

				RTIRequestDTO RTIRequestDTO = new RTIRequestDTO();
				countryDTO.setCountryList(countrylist);
				countryDTO.setStateList(statelist);

				RTIRequestDTO.setIdlist(idlist);
				rtiform.setRTIRequestDTO(RTIRequestDTO);

				rtiform.setIGRSCountryDTO(countryDTO);
//				 session = request.getSession();

				session.setAttribute("IGRSCountryForm", rtiform);
				ArrayList intimationdto = rbd.getRTIIntimationFee(rtiform.getRTIRequestDTO(),paramIds);
				RTIRequestDTO dto1=(RTIRequestDTO)intimationdto.get(0);
				rtiform.setRTIRequestDTO(dto1);
				ArrayList intimation1=new ArrayList();
				intimation1.add(rtiform);
//				session = request.getSession();
				session.setAttribute("intimation1", intimation1);
				
				
				return mapping.findForward("rtirequest");
			}
		
		if(label.equalsIgnoreCase("view")){
			return mapping.findForward("rtiview");
		}
		if(label.equalsIgnoreCase("update")){
			return mapping.findForward("rtiupdate");
		}
		if(label.equalsIgnoreCase("assign")){
			return mapping.findForward("rtiassign");
		}
		if(label.equalsIgnoreCase("report")){
			return mapping.findForward("rtireport");
		}
		}
		/**
		 * Description : This if Statement is used to get district when state is
		 * selected
		 */

		if ((CommonConstant.RTI_REQUEST_DISTRICT).equalsIgnoreCase(rtiform
				.getRTIRequestDTO().getRtiRequestOneAction())) {
			String state = request.getParameter("RTIRef");
			String country = rtiform.getRTIRequestDTO().getCountry();
//			 session = request.getSession();
			session.setAttribute("country", country);
			ArrayList districtlist = rbd.getDistrictList(state);
			countryDTO.setDistrictList(districtlist);
			rtiform.setIGRSCountryDTO(countryDTO);
//			HttpSession session1 = request.getSession();

			// session.setAttribute("requestForm",requestForm);
			session.setAttribute("IGRSCountryForm", rtiform);
			return mapping.findForward("success");
		}
		/**
		 * Description : This if statement is used to get country and state
		 */
		else if ((CommonConstant.RTI_REQUEST_COUNTRY).equalsIgnoreCase(rtiform
				.getRTIRequestDTO().getRtiRequestOneAction())) {
			rbd = new RTIBD();
			ArrayList countrylist = rbd.getCountryList();
			ArrayList statelist = rbd.getStateList();
			ArrayList idlist = rbd.getphotoIdList();

			RTIRequestDTO RTIRequestDTO = new RTIRequestDTO();
			countryDTO.setCountryList(countrylist);
			countryDTO.setStateList(statelist);

			RTIRequestDTO.setIdlist(idlist);
			rtiform.setRTIRequestDTO(RTIRequestDTO);

			rtiform.setIGRSCountryDTO(countryDTO);
//			 session = request.getSession();

			session.setAttribute("IGRSCountryForm", rtiform);

			ArrayList intimationdto = rbd.getRTIIntimationFee(rtiform.getRTIRequestDTO(),paramIds);
			RTIRequestDTO dto1=(RTIRequestDTO)intimationdto.get(0);
			rtiform.setRTIRequestDTO(dto1);
			ArrayList intimation1=new ArrayList();
			intimation1.add(rtiform);
//			session = request.getSession();
			session.setAttribute("intimation1", intimation1);

			return mapping.findForward("success");
		}
		
		
		
		
		
		
		
		/**
		 * Description :This if statement contanis the RTI request information, address,postal code,etc
		 */
		
		
		
		
		if ((CommonConstant.RTI_REQUEST_RU_01).equalsIgnoreCase(rtiform
				.getRTIRequestDTO().getRtiRequest_Form())
				|| (CommonConstant.RTI_REQUEST_RU_03).equalsIgnoreCase(rtiform
						.getRTIRequestDTO().getRtiRequest_Form())) {

			if ((CommonConstant.RTI_INTIMATION).equalsIgnoreCase(rtiform
					.getRTIRequestDTO().getRtiRequestOneAction())
					|| (CommonConstant.RTI_INTIMATION).equalsIgnoreCase(rtiform
							.getRTIRequestDTO().getRtiRequestThreeAction())) {
				
				rbd = new RTIBD();
				rtiform.getRTIRequestDTO().setEmailId(
						request.getParameter("email"));
				rtiform.getRTIRequestDTO().setSms(request.getParameter("sms"));
				rtiform.getRTIRequestDTO()
						.setPost(request.getParameter("post"));
				ArrayList intimationdto = rbd.getRTIIntimationFee(rtiform.getRTIRequestDTO(),paramIds);
				RTIRequestDTO dto1=(RTIRequestDTO)intimationdto.get(0);
				rtiform.setRTIRequestDTO(dto1);
				ArrayList intimation1=new ArrayList();
				intimation1.add(rtiform);
//				 session = request.getSession();
				session.setAttribute("intimation1", intimation1);
				
				return mapping.findForward("rtirequestru1");
			}
		}

		/**
		 * Description :This statement contains the status information and number of pager and number of hour
		 */

		if ((CommonConstant.RTIRequest_UD_02).equalsIgnoreCase(rtiform
				.getRTIRequestDTO().getRtiRequest_Form())) {

			if ((CommonConstant.RTI_INTIMATION).equalsIgnoreCase(rtiform
					.getRTIRequestDTO().getRtiStatusTwoAction())) {

				ArrayList list = new ArrayList();
				RTIRequestDTO dto = rtiform.getRTIRequestDTO();

//				 session = request.getSession();

				ArrayList alist = (ArrayList) session
						.getAttribute("requestinfo");

				for (int i = 0; i < alist.size(); i++) {
					RTIRequestForm frm = (RTIRequestForm) alist.get(i);

					if (((String) session.getAttribute("id")).equals(frm
							.getRTIRequestDTO().getRtiID())) {
						frm.getRTIRequestDTO().setResolutionInformation(
								dto.getResolutionInformation());
						frm.getRTIRequestDTO().setNumberOfPages(
								dto.getNumberOfPages());
						frm.getRTIRequestDTO().setNumberOfHour(
								dto.getNumberOfHour());
						frm.getRTIRequestDTO().setFee(dto.getFee());
						frm.getRTIRequestDTO().setOthers(dto.getOthers());
						frm.getRTIRequestDTO().setTotalfee(dto.getTotalfee());

						list.add(frm);
					}
				}
				session.setAttribute("requestinfo1", list);

				
				rbd = new RTIBD();

				ArrayList intimationdto = rbd.getRTIWorkFee(rtiform.getRTIRequestDTO());
				RTIRequestDTO dto1=(RTIRequestDTO)intimationdto.get(0);
				rtiform.setRTIRequestDTO(dto1);
				ArrayList intimation1=new ArrayList();
				intimation1.add(rtiform);
//				session = request.getSession();
				session.setAttribute("intimation1", intimation1);

				return mapping.findForward("rtiupdate04");
			}
		}

		/**
		 * 
		 *Description : This if statement contains the payment information.
		 * 
		 * 
		 */
		if ((CommonConstant.RTI_REQUEST_RU_01).equalsIgnoreCase(rtiform
				.getRTIRequestDTO().getRtiRequest_Form())) {

			ArrayList list = new ArrayList();
			RTIRequestDTO dto = rtiform.getRTIRequestDTO();
			list.add(rtiform);

//			 session = request.getSession();
			session.setAttribute("requestinfo", list);
			session.setAttribute("paymentinteg","RTIRequest_RU_05");
			session.setAttribute("amount", dto.getTotalfee());
			session.setAttribute("user", paramIds[0]);
			session.setAttribute("forwardPath", "./rtipaymentlookup.do");
			
			//logger.debug("samcool");
			//return mapping.findForward("./payment.do");
			return mapping.findForward("rtirequestru2");
		}
		/**
		 * Description :This if statment contains the RTI Request info.
		 * Modify is true then goes to then goes to request page.
		 * proceed is true then goes to payment module
		 */
		if ((CommonConstant.RTI_REQUEST_RU_02).equalsIgnoreCase(rtiform
				.getRTIRequestDTO().getRtiRequest_Form())) {
		    String temp="";
			if ((CommonConstant.RTI_REQUEST_RU_02_MODIFY)
					.equalsIgnoreCase(rtiform.getRTIRequestDTO()
							.getRtiRequestTwoAction())) {

				ArrayList list = new ArrayList();
				RTIRequestDTO dto = rtiform.getRTIRequestDTO();
				list.add(rtiform);
//				 session = request.getSession();
				session.setAttribute("requestinfo", list);
				temp = "rtirequestru3";
				//return mapping.findForward("rtirequestru3");
			}
			if ((CommonConstant.RTI_REQUEST_RU_02_PROCEED)
					.equalsIgnoreCase(rtiform.getRTIRequestDTO()
							.getRtiRequestTwoAction())) {
				rbd = new RTIBD();
				ArrayList intimationdto = rbd.getRTIIntimationFee(rtiform.getRTIRequestDTO(),paramIds);
				RTIRequestDTO dto1=(RTIRequestDTO)intimationdto.get(0);
				rtiform.setRTIRequestDTO(dto1);
				ArrayList intimation=new ArrayList();
				intimation.add(rtiform);
//				 session = request.getSession();
				session.setAttribute("intimation", intimation);
				//temp data
				session.setAttribute("amount", dto1.getTotalfee());
				session.setAttribute("user", paramIds[0]);
				session.setAttribute("forwardPath", "./rtipaymentlookup.do");
				//logger.debug("imran");
				
				temp="topayment";
			}
			logger.debug("imran temp="+temp);
			return mapping.findForward(temp);
		}
		/**
		 *Description :This if statement goes to modify page  
		 */
		if ((CommonConstant.RTI_REQUEST_RU_03).equalsIgnoreCase(rtiform
				.getRTIRequestDTO().getRtiRequest_Form())) {
			
			return mapping.findForward("rtirequestru2");
		}
		/**
		 * This if statement contains RTI request challan information
		 */

		if ((CommonConstant.RTI_REQUEST_RU_04).equalsIgnoreCase(rtiform
				.getRTIRequestDTO().getRtiRequest_Form())) {
//			 session = request.getSession();
			session.removeAttribute("challanlist");
			String cha[] = request.getParameterValues("challanNo");
			String amount[] = request.getParameterValues("amount");
			String bname[] = request.getParameterValues("bankName");
			String cdate[] = request.getParameterValues("challanDate0");
			ArrayList challanlist = new ArrayList();

			if (cha.length > 0 && cha[0].length() > 0) {

				for (int i = 0; i < cha.length; i++) {

					String s = "challanDate" + i + "";

					RTIRequestDTO dto = new RTIRequestDTO();
					dto.setChallanNo(cha[i]);
					dto.setAmount(amount[i]);
					dto.setBankName(bname[i]);
					dto.setChallanDate(request.getParameter(s));
					challanlist.add(dto);

				}

				session.setAttribute("challanlist", challanlist);
			}
			
			if ((CommonConstant.RTI_REQUEST_RU_NEXT).equalsIgnoreCase(rtiform
					.getRTIRequestDTO().getRtiRequestFourAction())) {
				return mapping.findForward("rtirequestru5");
			}
			
			if ((CommonConstant.RTI_REQUEST_RU_PREVIOUS)
					.equalsIgnoreCase(rtiform.getRTIRequestDTO()
							.getRtiRequestFourAction())) {
				return mapping.findForward("rtirequestru2");
			}
		}
		
		/**
		 * This if statement contains RTI Update information
		 */
			

		if ((CommonConstant.RTIRequest_UD_03).equalsIgnoreCase(rtiform
				.getRTIRequestDTO().getRtiRequest_Form())) {

			String actionForm = rtiform.getRTIRequestDTO()
					.getRtiStatusTwoAction();

			if (CommonConstant.MODIFY.equals(actionForm)) {
				
				return mapping.findForward("rtiupdate04");
			}
			if (CommonConstant.PROCEED.equals(actionForm)) {
				
				return mapping.findForward("rtiupdate05");
			}
		}
		
		/**
		 * if RTIRequest_UD_00 is true then goes to RTI Update page.
		 * if RTIRequest_VI_00 is true then goes to RTI View page.
		 * if RTIRequest_AS_00 is true then goes to RTI Assign page.
		 * 
		 * 
		 * 
		 * 
		 */
		if ((CommonConstant.RTIRequest_UD_00).equalsIgnoreCase(rtiform
				.getRTIRequestDTO().getRtiRequest_Form())
				|| (CommonConstant.RTIRequest_VI_00).equalsIgnoreCase(rtiform
						.getRTIRequestDTO().getRtiRequest_Form())
				|| (CommonConstant.RTIRequest_AS_00).equalsIgnoreCase(rtiform
						.getRTIRequestDTO().getRtiRequest_Form())) {
			rbd = new RTIBD();
			if ((CommonConstant.RTIRequest_UD_00).equalsIgnoreCase(rtiform
					.getRTIRequestDTO().getRtiRequest_Form()))
			{
				ArrayList intimationdto = rbd.getRTIWorkFee(rtiform.getRTIRequestDTO());
				RTIRequestDTO dto1=(RTIRequestDTO)intimationdto.get(0);
				rtiform.setRTIRequestDTO(dto1);
				ArrayList intimation1=new ArrayList();
				intimation1.add(rtiform);
//				session = request.getSession();
				session.setAttribute("intimation1", intimation1);
			}
			rtiform.getRTIRequestDTO().setDurationFrom(
					rtiform.getDurationfrom());
			rtiform.getRTIRequestDTO().setDurationTo(rtiform.getDurationto());
			String id = rtiform.getRTIRequestDTO().getRtiID();
			String fromDate = rtiform.getDurationfrom();
			String toDate = rtiform.getDurationto();
			String status = rtiform.getRTIRequestDTO().getRtiStatus();
			//View part.........
			ArrayList list = rbd.statusInfo(id, fromDate, toDate, status);
			ArrayList list2 = rbd.getIntimation(id);
			ArrayList list1 = rbd.getComments(id);
			status = "open";
			if ((CommonConstant.RTIRequest_UD_00).equalsIgnoreCase(rtiform
					.getRTIRequestDTO().getRtiRequest_Form())
					|| (CommonConstant.RTIRequest_AS_00)
							.equalsIgnoreCase(rtiform.getRTIRequestDTO()
									.getRtiRequest_Form()))
				list = rbd.statusInfo(id, fromDate, toDate, status);
//			session = request.getSession();
			session.setAttribute("intimation", list2);
			session.setAttribute("requestinfo", list);
			session.setAttribute("comments", list1);

			request.setAttribute("list", list);

			if ((CommonConstant.RTIRequest_VI_00).equalsIgnoreCase(rtiform
					.getRTIRequestDTO().getRtiRequest_Form())) {
				if (list.size() == 0) {
					session.setAttribute("error", "No data found.");
					return mapping.findForward("rtiview");
				}

				return mapping.findForward("rtiview01");
			}

			if ((CommonConstant.RTIRequest_AS_00).equalsIgnoreCase(rtiform
					.getRTIRequestDTO().getRtiRequest_Form())) {
				ArrayList drolist = rbd.getDroName();

				rtiform.getRTIRequestDTO().setDrnamelist(drolist);
				session.setAttribute("RTIAssign", rtiform);
				if (list.size() == 0) {
					session.setAttribute("error", "No data found.");

					return mapping.findForward("rtiassign");
				}

				return mapping.findForward("rtiassign01");
			}

			if (list.size() == 0) {
				session.setAttribute("error", "No data found.");

				return mapping.findForward("rtiupdate");
			}
			return mapping.findForward("rtiupdate01");
		}
		/**
		 * This if statement contains the RTI update information
		 * 
		 */

		if ((CommonConstant.RTIRequest_UD_02).equalsIgnoreCase(rtiform
				.getRTIRequestDTO().getRtiRequest_Form())) {

			String actionForm = rtiform.getRTIRequestDTO()
					.getRtiStatusTwoAction();

			ArrayList list = new ArrayList();
			RTIRequestDTO dto = rtiform.getRTIRequestDTO();

//			 session = request.getSession();

			ArrayList alist = (ArrayList) session.getAttribute("requestinfo");

			for (int i = 0; i < alist.size(); i++) {
				RTIRequestForm frm = (RTIRequestForm) alist.get(i);

				if (((String) session.getAttribute("id")).equals(frm
						.getRTIRequestDTO().getRtiID())) {
					frm.getRTIRequestDTO().setResolutionInformation(
							dto.getResolutionInformation());
					frm.getRTIRequestDTO().setNumberOfPages(
							dto.getNumberOfPages());
					frm.getRTIRequestDTO().setNumberOfHour(
							dto.getNumberOfHour());
					frm.getRTIRequestDTO().setFee(dto.getFee());
					session.setAttribute("paymentinteg","RTI_Request_UD_06");
					session.setAttribute("amount", dto.getTotalfee());
				//	session.setAttribute("user", "sro");
					session.setAttribute("forwardPath","./rtipaymentlookup.do");
					frm.getRTIRequestDTO().setOthers(dto.getOthers());
					frm.getRTIRequestDTO().setTotalfee(dto.getTotalfee());

					list.add(frm);
				}
			}
			session.setAttribute("requestinfo1", list);

			if (CommonConstant.NEXT.equals(actionForm)) {

				return mapping.findForward("rtiupdate03");
			} else if (CommonConstant.PREVIOUS.equals(actionForm)) {

				return mapping.findForward("rtiupdate01");
			}

		}
		/**
		 * This if statement contains the update challan information
		 */
		if ((CommonConstant.RTI_REQUEST_UD_05).equalsIgnoreCase(rtiform
				.getRTIRequestDTO().getRtiRequest_Form())) {
			
			
//			 session = request.getSession();
			session.removeAttribute("challanlist");
			String cha[] = request.getParameterValues("challanNo");
			String amount[] = request.getParameterValues("amount");
			String bname[] = request.getParameterValues("bankName");
			String cdate[] = request.getParameterValues("challanDate");
			String chdate[]=request.getParameterValues("chdate");
			//int date = Integer.parseInt(rtiform.getDatefield());

			
			ArrayList challanlist = new ArrayList();
			
			if (cha.length > 0 && cha[0].length() > 0) {

				for (int i = 0; i < cha.length; i++) {
					String s;
					if ((i == 0)||(i<cdate.length)) {
						

						s = "challanDate";
					} else {
						
						s = "challanDate" + chdate[i] + "";
						
					}
					
					
					RTIRequestDTO dto = new RTIRequestDTO();
					dto.setChallanNo(cha[i]);
					dto.setAmount(amount[i]);
					dto.setBankName(bname[i]);
					dto.setChallanDate(request.getParameter(s));
					challanlist.add(dto);

				}
				
				session.setAttribute("challanlist", challanlist);
			}
			
			if ((CommonConstant.RTI_REQUEST_RU_NEXT).equalsIgnoreCase(rtiform
					.getRTIRequestDTO().getRtiStatusTwoAction())) {
				
				return mapping.findForward("rtiupdate06");
			}
			
			if ((CommonConstant.RTI_REQUEST_RU_PREVIOUS)
					.equalsIgnoreCase(rtiform.getRTIRequestDTO()
							.getRtiStatusTwoAction())) {
				return mapping.findForward("rtiupdate03");
			}
		}
		/**
		 * This if statement contains RTI update information
		 * this page comes after enter payment information
		 */
		
		if ((CommonConstant.RTIRequest_UD_07).equalsIgnoreCase(rtiform
				.getRTIRequestDTO().getRtiRequest_Form())) {

			return mapping.findForward("rtiupdate08");
		}
		/**
		 * This if statement contains  RTI request information 
		 * this page comes after enter payment information
		 */

		if ((CommonConstant.RTI_REQUEST_RU_06).equalsIgnoreCase(rtiform
				.getRTIRequestDTO().getRtiRequest_Form())) {

			return mapping.findForward("rtirequestru7");
		}
		/**
		 * This if statement contains RTI Assign information
		 */
		if ((CommonConstant.RTIRequest_AS_02).equalsIgnoreCase(rtiform
				.getRTIRequestDTO().getRtiRequest_Form())) {

			String actionForm = rtiform.getRTIRequestDTO().getRtiAssignAction();

			ArrayList list = new ArrayList();
			RTIRequestDTO dto = rtiform.getRTIRequestDTO();

//			 session = request.getSession();

			ArrayList alist = (ArrayList) session.getAttribute("requestinfo");

			for (int i = 0; i < alist.size(); i++) {
				RTIRequestForm frm = (RTIRequestForm) alist.get(i);

				if (((String) session.getAttribute("id")).equals(frm
						.getRTIRequestDTO().getRtiID())) {

					frm.getRTIRequestDTO().setReplycomments(
							dto.getReplycomments());
					frm.getRTIRequestDTO().setDueDate(dto.getDueDate());
					frm.setDueDate(rtiform.getDueDate());
					frm.getRTIRequestDTO().setReplyStatus(dto.getReplyStatus());
					frm.getRTIRequestDTO().setDroName(dto.getDroName());

					list.add(frm);
				}
			}
			session.setAttribute("requestinfo1", list);

			if (CommonConstant.NEXT.equals(actionForm)) {

				return mapping.findForward("rtiassign03");
			} else if (CommonConstant.PREVIOUS.equals(actionForm)) {

				return mapping.findForward("rtiassign01");
			}

		}
		/**
		 * This if statement rti assign info.
		 * modify is true then goes to modify page
		 */

		if ((CommonConstant.RTIRequest_AS_03).equalsIgnoreCase(rtiform
				.getRTIRequestDTO().getRtiRequest_Form())) {

			String actionForm = rtiform.getRTIRequestDTO().getRtiAssignAction();

			if (CommonConstant.MODIFY.equals(actionForm)) {

				return mapping.findForward("rtiassign04");
			}
			if (CommonConstant.PROCEED.equals(actionForm)) {

				rbd = new RTIBD();
//				 session = request.getSession();
				ArrayList alist = (ArrayList) session
						.getAttribute("requestinfo1");

				RTIRequestForm frm = (RTIRequestForm) alist.get(0);
				rbd.assignDRO(frm.getRTIRequestDTO());

				return mapping.findForward("rtiassign05");
			}
		}
		return mapping.findForward("success");

	}
}
