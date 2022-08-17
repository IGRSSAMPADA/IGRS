
/* Copyright (c) 2007-08 WIPRO INFOTECH. All Rights Reserved.
 * 
 *  This software is the confidential and proprietary information of WIPRO
 *  ("Confidential Information"). You shall not
 *  disclose such Confidential Information and shall use it only in
 *  accordance with the terms of the license agreement you entered into
 *  with WIPRO.
 *  
 * WIPRO MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE 
 * SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF 
 * MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, OR NON-INFRINGEMENT. WIPRO SHALL NOT BE 
 * LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF 
 * USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS 
 * DERIVATIVES.
 */ 

/* 
 * FILE NAME: CreateHolidayACTION.java
 * @author NIHAR R MISHRA
 * @version 1.0 
 * INITIAL CODING: 8th AUGUST 2008
 * MODIFIED ON:	   None
 * @DESCRIPTION:   THIS CLASS REPRESENTS THE ACTION FOR HOLIDAY CREATION.
 */

package com.wipro.igrs.holidaylist.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.holidaylist.bd.CreateHolidayBD;
import com.wipro.igrs.holidaylist.constant.HolidayListCONSTANT;
import com.wipro.igrs.holidaylist.dto.HolidayListDTO;
import com.wipro.igrs.holidaylist.form.HolidayListFORM;
import com.wipro.igrs.util.PropertiesFileReader;

/**
 * @author NIHAR M.
 *
 */
public class CreateHolidayACTION extends BaseAction{

	String forwardJsp = new String("createHoliday");
	private Logger logger = (Logger) Logger.getLogger(CreateHolidayACTION.class);
	private PropertiesFileReader pr;

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward execute(
			ActionMapping mapping, 
			ActionForm form, 
			HttpServletRequest request,
			HttpServletResponse response, HttpSession session) 
	throws Exception {
		String page=request.getParameter("page");
		String roleId = (String) session.getAttribute("roleId");
		String funId = (String) session.getAttribute("functionId");
		String userId = (String) session.getAttribute("UserId");
		HolidayListFORM eForm = (HolidayListFORM) form;
		if(page!=null){
			if("createHoliday".equals(page)){
				HolidayListDTO holidayListDTO = new HolidayListDTO();
				eForm.setHolidayDTO(holidayListDTO);
				forwardJsp = HolidayListCONSTANT.CREATE_HOLIDAY_LIST;
				logger.debug("Page is forwarded to :-    "+forwardJsp);
				form=null;
			}
		}

		pr = PropertiesFileReader.getInstance("com.wipro.igrs.igrs");
		if (form != null) {
			logger.debug("Form:-    "+form);
			
			CreateHolidayBD holidayBD = new CreateHolidayBD();

			String formName = eForm.getHolidayDTO().getHolidayCreateForm();
			if(HolidayListCONSTANT.HOLIDAY_CREATE.equalsIgnoreCase(formName))
			{
				logger.debug("CreateHolidayACTION:: FORM VALUE MATCHED FOR:-       "+formName);
				HolidayListDTO formDTO = eForm.getHolidayDTO();
				logger.debug("CreateHolidayACTION:: ACTION MATCHED FOR:-       "+formDTO.getActionName());
				if("createHolidayAction".equals(formDTO.getActionName()))
				{
					String fyear = eForm.getHolidayDTO().getFinancialYear();
					String fDate = eForm.getHolidayDTO().getDate();
					String[] splitDate = fDate.split("/");
					String csDate = splitDate[2];

					if(csDate.equals(fyear)){
						boolean newHolidayCreated = holidayBD.createNewHoliday(formDTO,userId);
						if(newHolidayCreated){
							logger.debug("New Holiday got created successfully");
							forwardJsp = HolidayListCONSTANT.HOLIDAY_CREATED;
						}
						else{
							request.setAttribute(HolidayListCONSTANT.HOLIDAY_CREATE_FAILURE,pr.getValue("msg.createfailure"));
							logger.debug("Error creating a new Holiday");
						}
					}
					else{
						request.setAttribute(HolidayListCONSTANT.HOLIDAY_CREATE_FAILURE,pr.getValue("msg.checkDates"));

					}


					logger.debug("CreateHolidayACTION:: FORWARDED TO:-       "+forwardJsp);
				}
				formDTO.setActionName("");
			}
		}
		return mapping.findForward(forwardJsp);
	}
}