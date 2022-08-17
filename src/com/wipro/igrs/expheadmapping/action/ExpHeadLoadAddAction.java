/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   ExpHeadLoadAddAction.java
 * Author      :   Hend M. ismail
 * Description :   Represents the pre-Add Action Class for Exp. Head Mapping Master.
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
import com.wipro.igrs.expheadmapping.form.ExpHeadMappingActionForm;

public class ExpHeadLoadAddAction extends BaseAction {

	private Logger logger = (Logger) Logger.getLogger(ExpHeadLoadAddAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		
		logger.info("we are in ExpHeadLoadAddAction");

		String target = "expheadmapping";
		
		ExpHeadMappingBD expHeadbd = new ExpHeadMappingBD();
		ExpHeadMappingActionForm expHeadForm=(ExpHeadMappingActionForm)form;
		
		ArrayList majorOptions=expHeadbd.getMajorHeadList();
		expHeadForm.setMajorOptions(majorOptions);
		expHeadForm.setMajorHeadId("0");
		
		ArrayList subMajorOptions=expHeadbd.getSubMajorHeadList();
		expHeadForm.setSubMajorOptions(subMajorOptions);
		expHeadForm.setSubMajorHeadId("0");
		
		ArrayList minorOptions=expHeadbd.getMinorHeadList();
		expHeadForm.setMinorOptions(minorOptions);
		expHeadForm.setMinorHeadId("0");
		
		ArrayList segmentOptions= expHeadbd.getSegmentList();
		expHeadForm.setSegmentOptions(segmentOptions);
		expHeadForm.setSegmentId("0");
		
		
		ArrayList schemeOptions= expHeadbd.getSchemeList();
		expHeadForm.setSchemeOptions(schemeOptions);
		expHeadForm.setSchemeId("0");
		
		ArrayList objectOptions=expHeadbd.getObjectList();
		expHeadForm.setObjectOptions(objectOptions);
		expHeadForm.setObjectId("0");
		
		ArrayList detailedHeadOptions=expHeadbd.getDetailedHeadList();
		expHeadForm.setDetailedHeadOptions(detailedHeadOptions);
		expHeadForm.setDetailedHeadId("0");
	

		return mapping.findForward(target);
	}
}
