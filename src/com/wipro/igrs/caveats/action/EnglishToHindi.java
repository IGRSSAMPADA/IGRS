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

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
/**
* 
* EnglishToHindi.java <br>
* EnglishToHindi <br>
* 
* @version 1.0
* 
* @author <b>WIPRO JAVA TEAM</b> <br>
*         Created on 12-Apr-2008 <br>
*         Last Modified on 28-Mar-2013
*/
@SuppressWarnings("unused")
public class EnglishToHindi extends BaseAction { 
	
	private Logger logger = 
		(Logger) Logger.getLogger(EnglishToHindi.class);

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
	public ActionForward execute (ActionMapping mapping,ActionForm form,
									  HttpServletRequest request,
									  HttpServletResponse response,HttpSession session)throws Exception {
		//logger.debug("Called...................................");
		String FORWAD_SUCCESS="success";
			try{
				Locale locale=null;
				if ("hi".equals(request.getLocale()))
				{
					locale = new Locale("hi");
				}
				if ("en".equals(request.getLocale())) 
				{
					locale = new Locale("en");
				}
	
				String language = (String)request.getParameter("Language");
				String country = (String)request.getParameter("Country");
				Locale newLocale = new Locale(language, country);
				setLocale(request, newLocale);
				request.getSession().setAttribute(Globals.LOCALE_KEY, newLocale);
				return mapping.findForward(FORWAD_SUCCESS);
			}
            catch(Exception e)
            {
               // logger.error(e);
                return mapping.findForward("faliure");
            }
	}
	

}