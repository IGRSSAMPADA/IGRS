/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   ExpHeadLoadEditAction.java
 * Author      :   Hend M. ismail
 * Description :   Represents the pre-Edit Action Class for Exp. Head Mapping Master.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0              Hend M. ismail  18th Augest, 2008	 		 
 * --------------------------------------------------------------------------------
 */

package com.wipro.igrs.expheadmapping.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.expheadmapping.bd.ExpHeadMappingBD;
import com.wipro.igrs.expheadmapping.dto.ExpHeadMappingDTO;
import com.wipro.igrs.expheadmapping.form.ExpHeadMappingActionForm;

public class ExpHeadLoadEditAction extends BaseAction {

	private Logger logger = (Logger) Logger.getLogger(ExpHeadLoadEditAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		logger.info("we are in UserTypeLoadEditAction");
		String target = "editexpheadmapping";

			

		ExpHeadMappingBD expHeadbd = new ExpHeadMappingBD();
		ExpHeadMappingActionForm expHeadForm=(ExpHeadMappingActionForm)form;
		
		
		String majorheadid=(String)request.getParameter("majorheadid");
		ExpHeadMappingDTO dto = expHeadbd.getExpHeadMappingId(majorheadid);
		expHeadForm.setMajorHeadId(dto.getMajorHeadId());
		expHeadForm.setSubMajorHeadId(dto.getSubMajorHeadId());
		expHeadForm.setMinorHeadId(dto.getMinorHeadId());
		expHeadForm.setSchemeId(dto.getSchemeId());
		expHeadForm.setSegmentId(dto.getSegmentId());
		expHeadForm.setObjectId(dto.getObjectId());
		expHeadForm.setDetailedHeadId(dto.getDetailedHeadId());
		expHeadForm.setDroId(dto.getDroId());
		

		
		ArrayList majorOptions=expHeadbd.getExpHeadList();
		expHeadForm.setMajorOptions(majorOptions);
		
		ArrayList subMajorOptions=expHeadbd.getSubMajorHeadList();
		expHeadForm.setSubMajorOptions(subMajorOptions);
		
		ArrayList minorOptions=expHeadbd.getMinorHeadList();
		expHeadForm.setMinorOptions(minorOptions);
		
		ArrayList segmentOptions= expHeadbd.getSegmentList();
		expHeadForm.setSegmentOptions(segmentOptions);
		
		
		ArrayList schemeOptions= expHeadbd.getSchemeList();
		expHeadForm.setSchemeOptions(schemeOptions);
		
		ArrayList objectOptions=expHeadbd.getObjectList();
		expHeadForm.setObjectOptions(objectOptions);
		
		ArrayList detailedHeadOptions=expHeadbd.getDetailedHeadList();
		expHeadForm.setDetailedHeadOptions(detailedHeadOptions);
		
		


		return mapping.findForward(target);
	}


}
