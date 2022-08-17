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
* AttributeDeleteAction.java <br>
* AttributeDeleteAction <br>
* 
* @version 1.0
* 
* @author <b>WIPRO JAVA TEAM</b> <br>
*         Created on 12-Apr-2008 <br>
*         Last Modified on 28-Mar-2013
*/
@SuppressWarnings({ "rawtypes", "unchecked" })
public class AttributeDeleteAction extends BaseAction {
	
	private Logger logger = (Logger) Logger
			.getLogger(AttributeDeleteAction.class);
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

		AdminAttrForm form1 = (AdminAttrForm) form;
		AttributeBD atBD = new AttributeBD();
		//  HttpSession session = request.getSession();
	      String roleId = (String)session.getAttribute("role");
		  String funId = (String)session.getAttribute("functionId");
		  String userId = (String)session.getAttribute("UserId");

		if (request.getParameter("attributeId") != null) {

			String id = (String) request.getParameter("attributeId");
			boolean flag = atBD.deleteDataById(id,roleId,funId,userId);
			if (flag) {
				ArrayList displayList = atBD.displayData(form1);
				ArrayList mylist = new ArrayList();
				int listSize = displayList.size();
				if (listSize > 0) {
					for (int i = 0; i < displayList.size(); i++) {
						ArrayList temp = new ArrayList();
						temp = (ArrayList) displayList.get(i);
						logger.debug("temparray=" + temp);
						AdminAttrForm fm = new AdminAttrForm();
						fm.setAttributeName((temp.get(0).toString()));
						fm.setAttributeValue((temp.get(1).toString()));
						String rem = "";
						if (temp.get(2) != null)
							rem = temp.get(2).toString();
						fm.setRemarks(rem);

						mylist.add(fm);
					}
				}
				 session = request.getSession();
				session.setAttribute("displayList1", mylist);
			}

		}// end of if if AttributeId not null
		return mapping.findForward("success");
	}
}
