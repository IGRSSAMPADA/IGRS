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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.UserRegistration.action.CryptoLibrary;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.caveats.bd.CaveatsDelegate;
import com.wipro.igrs.caveats.constant.CaveatsConstant;
import com.wipro.igrs.caveats.dto.CaveatsDTO;
import com.wipro.igrs.caveats.form.CaveatsForm;
import com.wipro.igrs.common.IGRSCommon;

/**
* 
* CaveatsOttAction.java <br>
* CaveatsOttAction <br>
* 
* @version 1.0
* 
* @author <b>WIPRO JAVA TEAM</b> <br>
*         Created on 12-Apr-2008 <br>
*         Last Modified on 28-Mar-2013
*/
@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
public class CaveatsOttAction extends BaseAction {
	
	private Logger logger = (Logger) Logger.getLogger(CaveatsOttAction.class);
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
		logger.debug("WE ARE IN CaveatsOttAction Debug");
		logger.info("WE ARE IN  CaveatsOttAction INFO");
		String FWD_PATH = "failure";
		String isMenuClick = request.getParameter("isMenuClick");
		String page = request.getParameter("page");
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
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			String sysDateDisplay = dateFormat.format(new Date());
			request.setAttribute("sysDateDisplay", sysDateDisplay);
			CaveatsForm caveatfrm = (CaveatsForm) form;
			CaveatsDTO cDTO = caveatfrm.getCaveatsDTO();
			CaveatsDelegate cavBD = new CaveatsDelegate();
			if("yes".equals(isMenuClick)) {
				cDTO.setActionName("init");
			}
			if(page!=null && page.equalsIgnoreCase("init"))
			{
				cDTO.setActionName("init");
			}
		//	logger.debug("cDTO actionName is : " + cDTO.getActionName());
			
			if("searchPIN".equals(cDTO.getActionName())) {
				caveatfrm.setSearchResultList(null);
				caveatfrm.setSelectedList(null);
				int stayOrderCount = 0;
				stayOrderCount = cavBD.getStayOrderLoggedCount(cDTO.getRegistrationNumber());
				
				if (stayOrderCount <= 0) {
					ArrayList result = cavBD.getPropertyTxnIDList(cDTO
							.getRegistrationNumber());
					if (result != null && result.size() > 0) {
						caveatfrm.setSearchResultList(result);
					} else {
						ArrayList errorList = new ArrayList();
						errorList
								.add("No records available please check Registration Number/रिकॉर्ड उपलब्ध नहीं कृपया पंजीकरण संख्या जाँच करें ");
						request.setAttribute("errorList", errorList);
					}
				} else {
					ArrayList errorList = new ArrayList();
					errorList
							.add("The given Registration Number already has a stay order logged against on of its property/properties; hence the same cannot be used for OTT Generation//दिए पंजीकरण संख्या पर पहले से ही अपनी संपत्ति / संपत्तियों  के खिलाफ  स्थगन आदेश लॉग है, इसलिए एक ही ओ टी टी पीढ़ी के लिए इस्तेमाल नहीं किया जा सकता");
					request.setAttribute("errorList", errorList);
				}
				session.setAttribute("caveatfrm", caveatfrm);
				FWD_PATH = "search";
			} else if("confirm".equals(cDTO.getActionName())) {
				Object attr = request.getParameterValues("pinNumber");
				String[] arr = (String[]) attr;
				//logger.debug(arr);
				try {
					ArrayList selectList = caveatfrm.getSelectedList();
					for (int iLoop = 0; iLoop < arr.length; iLoop++) {
						CaveatsDTO dto = (CaveatsDTO) selectList.get(iLoop);
						dto.setPinNumber(arr[iLoop]);
					}
				} catch (Exception e) {
				}
				ArrayList errorList = new ArrayList();
				boolean isValid = validateForm(caveatfrm.getSelectedList(), errorList, caveatfrm, cavBD);
				
				if(isValid == false) {
					request.setAttribute("errorMessage", "yes");
					request.setAttribute("errorList", errorList);
					FWD_PATH = "search";
				} else {
					caveatfrm.getCaveatsDTO().setLoggedIn((String) session.getAttribute("UserId"));
					cavBD.generateOTTNumbers(caveatfrm);
					FWD_PATH = "confirm";
				}
				session.setAttribute("caveatfrm", caveatfrm);
			} else if("choose".equals(cDTO.getActionName())) {
				caveatfrm.setSelectedList(null);
				Object attr = request.getParameterValues("chkProperty");
				String[] arr = (String[]) attr;
				try {
					if (arr != null) {
						ArrayList selectList = new ArrayList();
						ArrayList resultList = caveatfrm.getSearchResultList();
						for (String idx : arr) {
							int index = Integer.parseInt(idx);
							selectList.add(resultList.get(index));
						}
						caveatfrm.setSelectedList(selectList);
						caveatfrm.setSearchResultList(null);
					}
				} catch (Exception e) {
				}
				session.setAttribute("caveatfrm", caveatfrm);
				FWD_PATH = "search";
			} else if("init".equals(cDTO.getActionName())) {
				caveatfrm.setSearchResultList(null);
				caveatfrm.setSelectedList(null);
				cDTO.setRegistrationNumber("");
				String validity = cavBD.getOTTValidity(CaveatsConstant.OTT_VALIDITY_ATTRIB_NAME);
				caveatfrm.setValidityOTT(validity);
				if("NA".equals(validity)) {
					ArrayList errorList = new ArrayList();
					errorList.add("Please contact System Administrator. OTT Validity Parameter is either not available or deactivated/सिस्टम प्रशासक से संपर्क करें. ओ टी टी वैधता पैरामीटर या तो उपलब्ध नहीं है या निष्क्रिय नहीं है");
					request.setAttribute("errorMessage", "yes");
					request.setAttribute("errorList", errorList);
				}
				session.setAttribute("caveatfrm", caveatfrm);
//				ArrayList result = cavBD.getAvailablePINList(cDTO.getRegistrationNumber());
//				if(result != null && result.size()>0) {
//					fm.setSearchResultList(result);
//				}
				FWD_PATH = "init";
			}
			
			else if ("page".equals(cDTO.getActionName()))
			{
				session.setAttribute("caveatfrm", caveatfrm);
				FWD_PATH = "init";
				
			}
////			String FORWAD_SUCCESS = "failure";
////
////			ArrayList list = new ArrayList();
////			list = cavBD.searchForPin(cDTO.getRegistrationNumber(), cDTO
////					.getPinNumber());
////			if (list == null || list.isEmpty()) {
////				logger.info("List is Empty");
////			} else {
////				logger.info("List is NOT Empty");
////				cDTO = cavBD.insertott(cDTO.getRegistrationNumber(), cDTO
////						.getPinNumber());
////			}
////			if (cDTO != null) {
////				fm.setCaveatsDTO(cDTO);
////				FORWAD_SUCCESS = "success";
////			}
//			session.setAttribute("result", (Object) list);
		} catch (Exception e) {
			//logger.error(e);
			FWD_PATH = "search";
		}
		return mapping.findForward(FWD_PATH);
	}

	/**
	 * 
	 * @param selectedList
	 * @param errorList
	 * @param caveatfrm
	 * @param cavBD
	 * @return
	 */
	private boolean validateForm(ArrayList selectedList, ArrayList errorList, CaveatsForm caveatfrm, CaveatsDelegate cavBD) {
		boolean retVal = false;
		try {
			String validity = cavBD.getOTTValidity(CaveatsConstant.OTT_VALIDITY_ATTRIB_NAME);
			CryptoLibrary crypt = new CryptoLibrary();     
			caveatfrm.setValidityOTT(validity);
			if("NA".equals(validity)) {
				errorList.add("Please contact System Administrator. OTT Validity Parameter is either not available or deactivated/सिस्टम प्रशासक से संपर्क करें. ओ टी टी वैधता पैरामीटर या तो उपलब्ध नहीं है या निष्क्रिय नहीं है");
			}
			ArrayList<String> activePropertyList = cavBD.getActiveOTTPropertyMapping(selectedList);
			if(activePropertyList.isEmpty() == false) {
				for (String propID : activePropertyList) {
					errorList.add("The Property ID cannot be used as an OTT is already active against the same :/संपत्ति आईडी इस्तेमाल नहीं किया जा सकता एक ओ टी टी उसी के खिलाफ पहले से ही सक्रिय है  : " + propID);
				}
			}
			for (Object item : selectedList) {
				CaveatsDTO dto = (CaveatsDTO) item;
				String enteredPIN =  dto.getPinNumber().trim();
				String encrytedPIN = enteredPIN;// crypt.SHAencryption(enteredPIN);
				//added by ashima
				String pinFlag= dto.getPinFlag();
				if(pinFlag=="D")
				{
					errorList.add(" PIN has been deactivated : /पिन निष्क्रिय कर दिया गया है " +  dto.getPinFlag());
				}
				//
				// wipro@123 = 607e476d8a74c68f4f8e6c6b949c6b373f7e76a7c6e8f3aed31079564f42e42af1f273f19cc829b34d5da6a3649547a6fb0ff553eeb8bec208bfd24bdbe9f8e8
				if(encrytedPIN.equals(dto.getPinNumberInDB()) == false || pinFlag=="D") {
					errorList.add("Please check PIN for Property ID : /संपत्ति आईडी के लिए पिन की जाँच करें:" +  dto.getPropertyTxnId());
				} else {
					logger.debug("PIN Number matched for Property ID /पिन संख्या संपत्ति आईडी के लिए मिल रहा है  " +  dto.getPropertyTxnId());
				}
			}
			if(errorList.isEmpty()) {
				retVal = true;
			}
		} catch (Exception e) {
		}
		return retVal;
	}
}
