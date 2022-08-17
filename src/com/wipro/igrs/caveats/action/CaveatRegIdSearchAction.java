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
import com.wipro.igrs.caveats.bd.CaveatsDelegate;
import com.wipro.igrs.caveats.dto.CaveatsDTO;
import com.wipro.igrs.caveats.form.CaveatsForm;

/**
* 
* CaveatRegIdSearchAction.java <br>
* CaveatRegIdSearchAction <br>
* 
* @version 1.0
* 
* @author <b>WIPRO JAVA TEAM</b> <br>
*         Created on 12-Apr-2008 <br>
*         Last Modified on 28-Mar-2013
*/
@SuppressWarnings({ "rawtypes", "unchecked" })
public class CaveatRegIdSearchAction extends BaseAction {
    
	private Logger logger = 
		(Logger) Logger.getLogger(CaveatActionConfirm.class);

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
                                 HttpServletRequest request, 
                                 HttpServletResponse response,HttpSession session) throws IOException, 
                                                                      ServletException,Exception {
    	logger.debug("WE ARE IN CaveatRegIdSearchAction Debug");
		logger.info("WE ARE IN  CaveatRegIdSearchAction INFO");
        try{
            CaveatsForm cForm = (CaveatsForm) form;
            CaveatsDTO caveatDto = cForm.getCaveatsDTO();
            CaveatsDelegate cavBD = new CaveatsDelegate();
    		String language = (String)session.getAttribute("languageLocale");
            String FORWAD_SUCCESS="success";
            String RegNo=caveatDto.getRegistrationNumber();
            String actionType = request.getParameter("actionType");
            caveatDto.setCloneSelectedItems(null);
            if("setSelectedPropIDs".equals(actionType)) {
            	
            	ArrayList searchResultList = cForm.getSearchResultList();
            	String[] selectedIndexes = request.getParameterValues("chkProperty");
            	ArrayList selectedItems = null;
            	if(selectedIndexes != null && selectedIndexes.length>0) {
            		selectedItems = new ArrayList();
					for (String selectedIndex : selectedIndexes) {
						int index = Integer.parseInt(selectedIndex);
						selectedItems.add(searchResultList.get(index-1));
					}
					cForm.setSearchResultList(null);
					caveatDto.setCloneSelectedItems(selectedItems);
            		//caveatDto.setSelectedItems(selectedItems);
            		session.setAttribute("cvtSearchUpdate","yes");
            	} else {
            		request.setAttribute("error", "Please select at least one Property/कम से कम एक संपत्ति का चयन करें...");
            	}
            	
            	caveatDto.setErrorMsg("FLAG");
				FORWAD_SUCCESS = "success";
            } else {
	            cForm.setSearchResultList(null);
				ArrayList searchResultList = cavBD.searchRegId(RegNo,language);
				if (searchResultList.size() > 0) {
					cForm.setSearchResultList(searchResultList);
					caveatDto.setErrorMsg("FLAG");
					FORWAD_SUCCESS = "success";
				} else {
					request.setAttribute("error", "This Registration Number doesn't not exist or it does not have associated Property/Properties./यह पंजीकरण नंबर मौजूद नहीं नहीं  है या  संबंधित संपत्ति /प्रॉपर्टीज नहीं है");
					caveatDto
							.setErrorMsg("This Registration Number doesn't not exist or it does not have associated Property/Properties/यह पंजीकरण नंबर मौजूद नहीं नहीं  है या  संबंधित संपत्ति /प्रॉपर्टीज नहीं है");
					FORWAD_SUCCESS = "failure";
				}
            }
            session.setAttribute("caveatfrm", cForm);
            return mapping.findForward(FORWAD_SUCCESS); 
        }catch(Exception e)
        {
          //  logger.error(e);
            return mapping.findForward("failure");
        }
    }
}
