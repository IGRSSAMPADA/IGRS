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
package com.wipro.igrs.adminConfig.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.adminConfig.bd.AttributeBD;
import com.wipro.igrs.adminConfig.form.AdminAttrForm;
import com.wipro.igrs.baseaction.action.BaseAction;

/**
* 
* AttributeUpdateAction.java <br>
* AttributeUpdateAction <br>
* 
* @version 1.0
* 
* @author <b>WIPRO JAVA TEAM</b> <br>
*         Created on 12-Apr-2008 <br>
*         Last Modified on 28-Mar-2013
*/
@SuppressWarnings({ "rawtypes" })
public class AttributeUpdateAction extends BaseAction {
	
	private Logger logger = (Logger) Logger
			.getLogger(AttributeUpdateAction.class);

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
			throws Exception {

		try {
			AdminAttrForm adminForm = (AdminAttrForm) form;
			AttributeBD atBD = new AttributeBD();
			logger.debug("attrid=" + request.getParameter("attributeId"));
			if (request.getParameter("attributeId") != null) {
				String id = (String) request.getParameter("attributeId");
				ArrayList list = atBD.getDataById(id);

				if (list.size() > 0) {
					for (int i = 0; i < list.size(); i++) {
						ArrayList temp = new ArrayList();
						temp = (ArrayList) list.get(i);
						adminForm.setAttributeId((temp .get(0).toString()));
						adminForm.setAttributeName((temp.get(1).toString()));
						adminForm.setAttributeValue((temp.get(2).toString()));
						Object rem = temp.get(3);
						rem = rem == null ? "" : rem.toString();
						adminForm.setRemarks(rem.toString());
						adminForm.setStatusVal(temp.get(4).toString());
						adminForm.setStatusLabel(temp.get(5).toString());
						adminForm.setModuleID(temp.get(6).toString());
						adminForm.setFunctionID(temp.get(7).toString());
						adminForm.setModuleName(temp.get(8).toString());
						adminForm.setFunctionName(temp.get(9).toString());

					}
				}
				//	HttpSession session = request.getSession();
				session.setAttribute("AdminAttrForm", adminForm);
				session.setAttribute("adminForm", adminForm);

			}// end of if if AttributeId not null
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return mapping.findForward("success");
	}
}
