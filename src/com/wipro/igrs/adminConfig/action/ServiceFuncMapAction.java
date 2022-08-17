/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	ServiceFuncMapAction.java
 * Author		:	vengamamba
 * Date			: 	21/06/2008
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

import com.wipro.igrs.adminConfig.bd.ServiceFuncMapBD;
import com.wipro.igrs.adminConfig.dto.ServiceFuncMapDTO;
import com.wipro.igrs.adminConfig.form.ServiceFuncMapForm;
import com.wipro.igrs.baseaction.action.BaseAction;

public class ServiceFuncMapAction extends BaseAction {
	private Logger logger = (Logger) Logger
			.getLogger(ServiceFuncMapAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,HttpSession session)
			throws Exception {
		logger.info("inside ServiceFuncMapAction class...");
		String forward = "serviceFuncMapScreen";

		ServiceFuncMapForm mapForm = (ServiceFuncMapForm) form;
		ServiceFuncMapBD mapbd = new ServiceFuncMapBD();
		ServiceFuncMapDTO dto = new ServiceFuncMapDTO();
		//HttpSession session = request.getSession();
	    String roleId = (String)session.getAttribute("role");
		String funId = (String)session.getAttribute("functionId");
		String userId = (String)session.getAttribute("UserId");
		ArrayList funcList = mapbd.getFuncIdList();
		ArrayList serviceList = mapbd.getServiceIdList();
		dto.setFuncIdList(funcList);
		dto.setServiceIdList(serviceList);
		mapForm.setMapdto(dto);

		if (mapForm.getFuncMapPageName() != null) {
			if (mapForm.getFuncMapPageName().equalsIgnoreCase("FuncMapCreate")) {
	
					if (mapForm.getActionValue().equalsIgnoreCase("Submit")) {

					String params[] = new String[2];
					params[0] = mapForm.getFuncid();
					params[1] = mapForm.getServiceid();

					boolean flag = mapbd.addFuncMapping(params,roleId,funId,userId);
					if (flag)
						forward = "success";
					else
						forward = "fail";
				} else
					forward = "start";
			}

		}

		return mapping.findForward(forward);
	}
}
