/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	ServiceParamMapAction.java
 * Author		:	vengamamba
 * Date			: 	23/06/2008
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

import com.wipro.igrs.adminConfig.bd.ServiceParamMapBD;
import com.wipro.igrs.adminConfig.dto.ServiceParamMapDTO;
import com.wipro.igrs.adminConfig.form.ServiceParamMapForm;
import com.wipro.igrs.baseaction.action.BaseAction;

public class ServiceParamMapAction extends BaseAction {
	private Logger logger = (Logger) Logger
			.getLogger(ServiceParamMapAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,HttpSession session)
			throws Exception {
		logger.info("inside ServiceParamMapAction class...");
		String forward = "serviceParamMapScreen";
		//  HttpSession session = request.getSession();
	      String roleId = (String)session.getAttribute("role");
		  String funId = (String)session.getAttribute("functionId");
		  String userId = (String)session.getAttribute("UserId");
		  
		ServiceParamMapForm mapForm = (ServiceParamMapForm) form;
		ServiceParamMapBD mapbd = new ServiceParamMapBD();
		ServiceParamMapDTO dto = new ServiceParamMapDTO();

		ArrayList funcList = mapbd.getFuncIdList();
		ArrayList serviceList = mapbd.getServiceIdList();
		ArrayList paramList = mapbd.getServiceParamIdList();
		ArrayList operatorList = mapbd.getOperatorIdList();

		dto.setFuncIdList(funcList);
		dto.setServiceIdList(serviceList);
		dto.setParamIdList(paramList);
		dto.setOperatorIdList(operatorList);

		mapForm.setMapdto(dto);

		if (mapForm.getParamMapPageName() != null) {
			if (mapForm.getParamMapPageName()
					.equalsIgnoreCase("ParamMapCreate")) {
				if (mapForm.getActionValue().equalsIgnoreCase("Submit")) {

					String params[] = new String[9];
					params[0] = mapForm.getFuncid();
					params[8] = mapForm.getServiceid();
					params[1] = mapForm.getParamId();
					params[2] = mapForm.getParamValue();
					params[3] = mapForm.getParamType();
					params[4] = mapForm.getParentParamId();
					params[5] = mapForm.getOperatorId();
					params[6] = mapForm.getFuncOperatorId();
					params[7] = mapForm.getPriority();

					boolean flag = mapbd.addParamMapping(params,roleId,funId,userId);
					if (flag)
						forward = "success";
					else
						forward = "fail";
				} else
					forward = "start";
			}

		}

		mapForm.setParamMapPageName(null);
		return mapping.findForward(forward);
	}
}
