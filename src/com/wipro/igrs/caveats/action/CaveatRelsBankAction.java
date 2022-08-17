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
* CaveatRelsBankAction.java <br>
* CaveatRelsBankAction <br>
* 
* @version 1.0
* 
* @author <b>WIPRO JAVA TEAM</b> <br>
*         Created on 12-Apr-2008 <br>
*         Last Modified on 28-Mar-2013
*/
@SuppressWarnings({ "rawtypes", "unchecked" })
public class CaveatRelsBankAction extends BaseAction {

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
    	logger.debug("WE ARE IN CaveatReleaseAction Debug");
		logger.info("WE ARE IN  CaveatReleaseAction INFO");
        try{
            CaveatsForm fm = (CaveatsForm) form;
            CaveatsDTO dtoRelease = fm.getCaveatsDTO();
            CaveatsDelegate cavBD = new CaveatsDelegate();
            ArrayList releaseSearchList=new ArrayList();
            String FORWAD_SUCCESS="failure";
            String refID=request.getParameter("referenceID");
            if(refID!=null)
            	dtoRelease.setReferenceID(refID);
            refID=dtoRelease.getReferenceID();
            if(!("".equalsIgnoreCase(refID)||refID==null))
            {
              //  logger.info("Inside REFID");
                dtoRelease=cavBD.releaseCvtBankByRefId(refID);
                if(dtoRelease==null)
                {
                	dtoRelease=fm.getCaveatsDTO();
                	dtoRelease.setErrorMsg("Error: Refernce ID is not found/त्रुटि: संदर्भ आईडी नहीं मिला है");
                	releaseSearchList.add(dtoRelease);
                	dtoRelease.setRecordsBuffer(releaseSearchList);
                	request.setAttribute("releaseByBankList",fm);
                    FORWAD_SUCCESS="regNo";
                }
                else
                {
                    fm.setCaveatsDTO(dtoRelease);
                    FORWAD_SUCCESS="refId";
                }
            }
            else if(true)
            {
            	 logger.info("Inside Global Search");
            	 releaseSearchList=cavBD.relsCavtBankByAll(dtoRelease.getReferenceID(),dtoRelease.getRegistrationNumber(), dtoRelease.getFromDate(),dtoRelease.getToDate());
            	 fm.getCaveatsDTO().setErrorMsg(((CaveatsDTO)releaseSearchList.get(0)).getErrorMsg());
            	 fm.getCaveatsDTO().setRecordsBuffer(releaseSearchList);
                 request.setAttribute("releaseByBankList",fm);
                 FORWAD_SUCCESS="regNo";
            }
            
            return mapping.findForward(FORWAD_SUCCESS); 
        }catch(Exception e)
        {
           // logger.error(e);
            return mapping.findForward("failure");
        }
    }
}