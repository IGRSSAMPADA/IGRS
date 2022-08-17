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

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
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
import com.wipro.igrs.caveats.form.CaveatsForm;
import com.wipro.igrs.common.IGRSCommon;

/**
* 
* CaveatsBankSearchAction.java <br>
* CaveatsBankSearchAction <br>
* 
* @version 1.0
* 
* @author <b>WIPRO JAVA TEAM</b> <br>
*         Created on 12-Apr-2008 <br>
*         Last Modified on 28-Mar-2013
*/
@SuppressWarnings({ "rawtypes" })
public class CaveatsBankSearchAction extends BaseAction {
	
	private Logger logger = (Logger) Logger
			.getLogger(CaveatsBankSearchAction.class);
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
			HttpServletRequest request, HttpServletResponse response,HttpSession session)
			throws IOException, ServletException, Exception {
		logger.debug("WE ARE IN CaveatsBankSearchAction Debug");
		logger.info("WE ARE IN  CaveatsBankSearchAction INFO");
		String FORWAD_SUCCESS = "failure";
		String userId = (String) session.getAttribute("UserId");
    	String activityid = request.getParameter("ACTID");
		
		if(activityid!=null)
		{
		IGRSCommon save=null;
		try {
			save = new IGRSCommon();
			save.saveactivitylog(userId, activityid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
		
		
		try {
			CaveatsForm fm = (CaveatsForm) form;
			CaveatsDTO cDTO = fm.getCaveatsDTO();
			CaveatsDelegate cBD = new CaveatsDelegate();
			ArrayList viewSearchlist = new ArrayList();
			String language=(String)session.getAttribute("languageLocale");
			cDTO.setLanguage(language);
			if ("view".equalsIgnoreCase(request.getParameter("pageName"))) {
				String referenceID = request.getParameter("referenceID");
				String download = request.getParameter("download");
				String downloadRelease = request.getParameter("downloadRelease");
				if("yes".equals(download)) {
					downloadDocument(response, cDTO.getDocContents(), cDTO.getDocumentName());
				}else if("yes".equals(downloadRelease)) {
					downloadDocument(response, cDTO.getReleaseContents(), cDTO.getReleaseDocName());
				}
				else {
					ArrayList list = fm.getSearchResultList();
					for (Object item : list) {
						CaveatsDTO tmpDTO = (CaveatsDTO) item;
						if(tmpDTO.getReferenceID().equals(referenceID)) {
							fm.setCaveatsDTO(tmpDTO);
							cDTO = tmpDTO;
							cDTO.setLoggedIn((String) session.getAttribute("UserId"));
							cBD.searchBankPropertyByRefID(cDTO);
							break;
						}
					}
				}
				FORWAD_SUCCESS = "refId";
			} else if ("search".equalsIgnoreCase(request.getParameter("pageName"))) {
				//logger.info("Inside Search by All other fields");
				ArrayList<String> errorList = validate(cDTO, session);
				if (errorList.isEmpty()) {
					String userID = (String) session.getAttribute("UserId");
					viewSearchlist = cBD.searchBankAll(
							cDTO.getReferenceIDSearch(),
							cDTO.getRegistrationNoSearch(), cDTO.getFlag(),
							cDTO.getFromDate(), cDTO.getToDate(), userID,language);
					fm.setSearchResultList(viewSearchlist);
					session.setAttribute("searchResultList", viewSearchlist);
					session.setAttribute("caveatfrm", fm);
					FORWAD_SUCCESS = "regNo";
				} else {
					request.setAttribute("errorsList", errorList);
					FORWAD_SUCCESS = "searchPage";
				}
			} else if ("yes".equalsIgnoreCase(request.getParameter("isMenuClick"))) {
				//logger.info("Inside isMenuClick = yes");
				session.removeAttribute("searchResultList");
				session.removeAttribute("caveatfrm");
				FORWAD_SUCCESS = "searchPage";
			} else {
				
			}
			return mapping.findForward(FORWAD_SUCCESS);
		} catch (Exception e) {
			//logger.error(e.getMessage(),e);
			return mapping.findForward("faliure");
		}
	}
	/**
	 * 
	 * @param res
	 * @param docContents
	 * @param fileName
	 */
	public static void downloadDocument(HttpServletResponse res, byte[] docContents, String fileName) {
		try {
			OutputStream os = res.getOutputStream();
			res.setContentType("application/download");
			res.setHeader("Content-Disposition", "attachment; filename="
					+ URLEncoder.encode(fileName,"UTF-8"));
			os.write(docContents);
			os.flush();
			os.close();
		} catch (Exception e) {
		}
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
							.add("Duration To cannot be greater than current date/  अवधि तक आज की तारीख से अधिक नहीं हो सकता");
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
