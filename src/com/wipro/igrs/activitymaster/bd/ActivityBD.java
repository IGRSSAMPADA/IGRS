/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   ActivityBD.java
 * Author      :   Mallikarjun.M 
 * Description :   Represents the BD Class for Activity Master.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Mallikarjun.M  17th March, 2008	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.activitymaster.bd;

import java.util.ArrayList;

import com.wipro.igrs.activitymaster.dao.ActivityDAO;
import com.wipro.igrs.activitymaster.dto.ActivityDTO;
import com.wipro.igrs.activitymaster.form.ActivityForm;

public class ActivityBD {

	public ActivityBD() {
	}

	ActivityDAO activitydao = new ActivityDAO();

	/**
	 * Add the list of activitymaster
	 * 
	 * @throws Exception
	 */

	public void addActivitymaster(ActivityForm activityForm) throws Exception {
		activitydao.addActivitymaster(activityForm);
	}

	/**
	 * Gets the list of ActivityList
	 * 
	 * @param ArrayList
	 *            of activityList
	 * @return ArrayList of ActivityDTO
	 * @throws Exception
	 */
	public ArrayList getActivityList() throws Exception {
		return activitydao.getActivityList();
	}

	/**
	 * Update the list of activitymaster
	 * 
	 * @throws Exception
	 */
	public void updateActivitymaster(ActivityForm activityForm)
			throws Exception {
		activitydao.updateActivitymaster(activityForm);
	}

	/**
	 * Gets the list of getActivityId
	 * 
	 * @param RoleDTO
	 *            of getActivityId
	 * @return ActivityDTO
	 * @throws Exception
	 */
	public ActivityDTO getActivityId(String activeid) throws Exception {
		return activitydao.getActivityId(activeid);
	}
}
