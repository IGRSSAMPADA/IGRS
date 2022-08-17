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
* AdminAttributeAction.java <br>
* AdminAttributeAction <br>
* 
* @version 1.0
* 
* @author <b>WIPRO JAVA TEAM</b> <br>
*         Created on 12-Apr-2008 <br>
*         Last Modified on 28-Mar-2013
*/
@SuppressWarnings({ "unused" })
public class CaveatActionConfirm extends BaseAction {
    
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
    	logger.debug("WE ARE IN CaveatActionConfirm Debug");
    	logger.info("WE ARE IN  CaveatActionConfirm INFO");
    	String FORWAD_SUCCESS="";
    	String transid=null;
    	transid=request.getParameter("transactionID");
        try{
        	CaveatsForm fm=(CaveatsForm)form;
        	CaveatsDTO cDTO=new CaveatsDTO();
        	cDTO=fm.getCaveatsDTO();
        	CaveatsDelegate cavBD = new CaveatsDelegate();
            
        	if(cDTO.getRegistrationNumber()!=null || ("".equals(cDTO.getRegistrationNumber())!= true)) {
        		session.setAttribute("sessionRegTxnId", cDTO.getRegistrationNumber());
//	ArrayList propTxnIdList=cavBD.getPropertyTxnIDList(cDTO.getRegistrationNumber());
//      		cDTO.setPropertyTxnIdList(propTxnIdList);
        		session.setAttribute("propTxnIdList", cDTO.getPropertyTxnId());
        	}
        	session.setAttribute("caveatfrm", fm);
        	if("create".equalsIgnoreCase(request.getParameter("pageName")))
        	{
        		String str="";
        	//	session.setAttribute("suppleDetails",cDTO);
        		request.setAttribute("suppleDetails",cDTO);
        		FORWAD_SUCCESS="ConfirmScreen";
//         	    if(session.getAttribute("frwdedByEdit")!=null)
//         	    {
//         	    	str=(String)session.getAttribute("frwdedByEdit");
//         	    }
//         	    if(str.equalsIgnoreCase("yes"))
//         	    {
//         	    	FORWAD_SUCCESS="ConfirmScreen";
//         	    }
//         	    else
//         	    	FORWAD_SUCCESS="propertyForwardPath";
        	}
        	/*if(transid!=null )
				
			{
				
				
                	  CaveatsForm fm1 = (CaveatsForm) form;
                	  CaveatsDelegate objTrans= new CaveatsDelegate();
          			

                 CaveatsDTO dto=objTrans.getTransactionidDetails(transid);

                  
                  request.setAttribute("refId",transid);

        

          fm1.setCaveatsDTO(dto);

                  request.setAttribute("fm1",fm1);

                  request.setAttribute("PartialPayment","true");

          return mapping.findForward("TransidScreen");

       }
 
                	    

                       */       

                              
            else if("dispCaveat".equalsIgnoreCase(request.getParameter("pageName")))
        	{
        		logger.info("Inside confirmDetails Action");
        		//cDTO=(CaveatsDTO)session.getAttribute("suppleDetails");
        		cDTO=(CaveatsDTO)request.getAttribute("suppleDetails");
        		fm.setCaveatsDTO(cDTO);
        		FORWAD_SUCCESS="ConfirmScreen";
        	}

            }catch(Exception e)
            {
               // logger.error(e);
                FORWAD_SUCCESS="failure";
            }    
            return mapping.findForward(FORWAD_SUCCESS);
    }
}
