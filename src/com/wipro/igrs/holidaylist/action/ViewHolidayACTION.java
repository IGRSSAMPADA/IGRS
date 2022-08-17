
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
 * FILE NAME: ViewHolidayACTION.java
 * @author NIHAR R MISHRA
 * @version 1.0 
 * INITIAL CODING: 8th AUGUST 2008
 * MODIFIED ON:	   None
 * @DESCRIPTION:   THIS CLASS REPRESENTS THE ACTION FOR HOLIDAY VIEW ACTION.
 */

package com.wipro.igrs.holidaylist.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.holidaylist.bd.ViewHolidayBD;
import com.wipro.igrs.holidaylist.constant.HolidayListCONSTANT;
import com.wipro.igrs.holidaylist.dto.HolidayListDTO;
import com.wipro.igrs.holidaylist.form.HolidayListFORM;
import com.wipro.igrs.util.PropertiesFileReader;

/**
 * @author NIHAR M.
 *
 */
public class ViewHolidayACTION extends BaseAction{

	String forwardJsp = new String("viewHoliday");
	private Logger logger = (Logger) Logger.getLogger(ViewHolidayACTION.class);
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
			HttpServletResponse response,HttpSession session)
	throws Exception {
		String page=request.getParameter("page");
		String roleId = (String) session.getAttribute("roleId");
		String funId = (String) session.getAttribute("functionId");
		String userId = (String) session.getAttribute("UserId");
		HolidayListFORM eForm = (HolidayListFORM) form;
		if(page!=null){
			if("viewHoliday".equals(page)){
				forwardJsp = HolidayListCONSTANT.HOLIDAY_VIEW;
				HolidayListDTO holidayListDTO = new HolidayListDTO();
				eForm.setHolidayDTO(holidayListDTO);
				logger.debug("Page is forwarded to :-    "+forwardJsp);
			}
		}

		pr = PropertiesFileReader.getInstance("com.wipro.igrs.igrs");
		if (form != null) {
			logger.debug("Form:-    "+form);
			
			ViewHolidayBD holidayBD = new ViewHolidayBD();

			String formName = eForm.getHolidayDTO().getHolidayViewForm();
			logger.debug("formName:-  "+formName);
			HashMap holidayList = holidayBD.getAllHolidayList();
			eForm.setHashHolidayList(holidayList);

			HolidayListDTO formDTO = eForm.getHolidayDTO();
			if(HolidayListCONSTANT.HOLIDAY_VIEW_FORM.equalsIgnoreCase(formName))
			{
				logger.debug("CreateHolidayACTION:: FORM VALUE MATCHED FOR:-       "+formName);
				logger.debug("CreateHolidayACTION:: ACTION MATCHED FOR:-       "+formDTO.getActionName());

				if("viewHolidayAction".equals(formDTO.getActionName()))
				{
					boolean removed = false;
					HolidayListDTO dto = eForm.getHolidayDTO();
					String[] holidayID= dto.getHdnholiName().split(",");

					if(holidayID != null){
						logger.debug(holidayID+"  is removed...");
						removed = holidayBD.removeHoliday(holidayID);

					}

					if(removed){
						eForm.setHashHolidayList(holidayBD.getAllHolidayList());
						logger.debug("********************************************************");
						logger.debug("Record removed successfully");
						forwardJsp =  HolidayListCONSTANT.HOLIDAY_VIEW;
						logger.debug("CreateHolidayACTION:: FORWARDED TO:-       "+forwardJsp);
					}
					else{
						request.setAttribute(HolidayListCONSTANT.HOLIDAY_VIEW_FAILURE,pr.getValue("msg.removalFailure"));
						logger.debug("Unsuccessful");
					}
				}
			}

			if("indHolidayDetails".equals(formDTO.getActionName())){

				logger.debug(formDTO.getActionName());
				System.out.println(formDTO.getActionName());

				HolidayListDTO hDTO = new HolidayListDTO();
				String hID = request.getParameter("refId");
				hDTO = holidayBD.getIndHolidayDetails(hID);
				System.out.println("hDTO:-   "+hDTO);
				eForm.setHolidayDTO(hDTO);

				forwardJsp = HolidayListCONSTANT.VIEW_IND_HOLIDAY;
				logger.debug("Forwarded to:-     "+forwardJsp);
			}
			
			if(HolidayListCONSTANT.HOLIDAY_UPDATE_FORM.equalsIgnoreCase(formName))
			{
				logger.debug("CreateHolidayACTION:: FORM VALUE MATCHED FOR:-       "+formName);
				System.out.println(formName);
				if("updateHolidayAction".equals(formDTO.getActionName()))
				{
					System.out.println(formDTO.getActionName());
					boolean removed = false;
					HolidayListDTO dto = eForm.getHolidayDTO();

					removed = holidayBD.updateHolidayDetails(dto,userId);

					if(removed){
						eForm.setHashHolidayList(holidayBD.getAllHolidayList());
						logger.debug("********************************************************");
						logger.debug("Record UPDATED successfully");
						forwardJsp =  "viewIndividualHolidayUpdated";
						logger.debug("CreateViewACTION:: FORWARDED TO:-       "+forwardJsp);
					}
					else{
						request.setAttribute(HolidayListCONSTANT.HOLIDAY_VIEW_FAILURE,pr.getValue("msg.updateFailure"));
						logger.debug("Unsuccessful");
					}
				}
			}

		}
		return mapping.findForward(forwardJsp);
	}
}