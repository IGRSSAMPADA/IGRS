/**
 * Copyright (c) 2009-10 WIPRO INFOTECH. All Rights Reserved.
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
 *
 * Customer specific copyright notice - NA
 */
package com.wipro.igrs.caveats.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.caveats.bd.CaveatsDelegate;
import com.wipro.igrs.caveats.dto.CaveatsDTO;
import com.wipro.igrs.caveats.dto.OTTDetailDTO;
import com.wipro.igrs.caveats.form.CaveatsForm;
/**
* 
* OTTBankSearchAction.java <br>
* OTTBankSearchAction <br>
* 
* @version 1.0
* 
* @author <b>WIPRO JAVA TEAM</b> <br>
*         Created on 12-Apr-2008 <br>
*         Last Modified on 28-Mar-2013
*/
public class OTTBankSearchAction extends BaseAction {

	private static Logger logger = (Logger) Logger
			.getLogger(OTTBankSearchAction.class);

	/**
	 * This is the main action called from the Struts framework.
	 * 
	 * @param mapping
	 *            The ActionMapping used to select this instance.
	 * @param form
	 *            The optional ActionForm bean for this request.
	 * @param request
	 *            The HTTP Request we are processing.
	 * @param response
	 *            The HTTP Response we are processing.
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		logger.debug("Entering method");
		String forward = "init";
		// init
		// searchresult
		// searchempty
		try {
			String isMenuClick;
			String actionName;
			String userID = (String) session.getAttribute("UserId");
			CaveatsForm caveatForm = (CaveatsForm) form;
			String language=(String)session.getAttribute("languageLocale");
			CaveatsDTO cDTO = caveatForm.getCaveatsDTO();
			cDTO.setLanguage(language);
			ArrayList<OTTDetailDTO> ottList;
			ArrayList<String> errorList;
			isMenuClick = request.getParameter("isMenuClick");
			if ("yes".equals(isMenuClick)) {
				caveatForm.setCaveatsDTO(new CaveatsDTO());
				ottList = caveatForm.getOttSearchResultList();
				if (ottList != null) {
					ottList.clear();
				} else {
					ottList = new ArrayList<OTTDetailDTO>();
				}
				caveatForm.setOttSearchResultList(ottList);
			} else {
				actionName = request.getParameter("actionName");
				 cDTO = caveatForm.getCaveatsDTO();
				caveatForm.setFromDate(cDTO.getFromDate());
				caveatForm.setToDate(cDTO.getToDate());
				
				CaveatsDelegate cavBD = new CaveatsDelegate();
				if ("search".equals(actionName)) {
					errorList = validate(cDTO, session);
					if (errorList.isEmpty()) {
						ottList = cavBD.getOTTListing(cDTO, userID,language);
						caveatForm.setOttSearchResultList(ottList);
						if (ottList.isEmpty()) {
							errorList = new ArrayList<String>();
							errorList
									.add("No record(s) found based on search criteria/कोई रिकॉर्ड (ओं) खोज मानदंडों के आधार पर नहीं मिला");
							request.setAttribute("errorList", errorList);
							forward = "searchempty";
						} else {
							forward = "searchresult";
						}
					} else {
						request.setAttribute("errorList", errorList);
						forward = "searchempty";
					}
				}
			}
			session.setAttribute("caveatfrm", caveatForm);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		//logger.debug("Exiting method with forward : " + forward);
		return mapping.findForward(forward);
	}
	/**
	 * 
	 * @param cDTO
	 * @param session
	 * @return
	 */
	private ArrayList<String> validate(CaveatsDTO cDTO, HttpSession session) {
		ArrayList<String> errorList = new ArrayList<String>();
		try {
			Calendar sysCal = Calendar.getInstance();
			Date currSysDate = (Date) session.getAttribute("currSysDate");
			Date fromDate, toDate;
			boolean isFromDate, isToDate;
			SimpleDateFormat inputDateFormat = new SimpleDateFormat(
					"dd/MM/yyyy");
			inputDateFormat.setLenient(false);
			sysCal.setTime(currSysDate);
			sysCal.set(Calendar.HOUR_OF_DAY, 0);
			sysCal.set(Calendar.MINUTE, 0);
			sysCal.set(Calendar.SECOND, 0);
			sysCal.set(Calendar.MILLISECOND, 0);
			currSysDate = sysCal.getTime();
			fromDate = new Date(currSysDate.getTime());
			toDate = new Date(currSysDate.getTime());
			String fromDateString = cDTO.getFromDate();
			String toDateString = cDTO.getToDate();

			try {
				fromDate = inputDateFormat.parse(fromDateString);
				isFromDate = true;
			} catch (ParseException e) {
				isFromDate = false;
			}
			try {
				toDate = inputDateFormat.parse(toDateString);
				isToDate = true;
			} catch (ParseException e) {
				isToDate = false;
			}
			if (isFromDate) {
				if (currSysDate.before(fromDate)) {
					errorList
							.add("Duration From cannot be greater than current date/ अवधि से आज की तारीख से अधिक नहीं हो सकता");
				}
			}
			if (isToDate) {
				if (currSysDate.before(toDate)) {
					errorList
							.add("Duration To cannot be greater than current date/ अवधि तक आज की तारीख से अधिक नहीं हो सकता");
				}
			}
			if (isFromDate && isToDate) {
				if (fromDate.after(toDate)) {
					errorList
							.add("Duration From cannot be greater than Duration To/ अवधि से,अवधि तक से अधिक नहीं हो सकता ");
				}
			}
		} catch (Exception e) {
		}
		return errorList;
	}
}
