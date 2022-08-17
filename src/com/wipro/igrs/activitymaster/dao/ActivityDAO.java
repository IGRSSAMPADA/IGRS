/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   ActivityDAO.java
 * Author      :   Mallikarjun.M 
 * Description :   Represents the DAO Class for Activity Master.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Mallikarjun.M  17th March, 2008	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.activitymaster.dao;

import java.util.ArrayList;

import org.apache.log4j.Logger;

 
import com.wipro.igrs.activitymaster.dto.ActivityDTO;
import com.wipro.igrs.activitymaster.form.ActivityForm;
import com.wipro.igrs.activitymaster.sql.ActivityCommonSQL;
import com.wipro.igrs.db.DBUtility;

public class ActivityDAO {

	private ArrayList activityList = new ArrayList();
	DBUtility dbUtility = null;
	String sql = null;
	ArrayList subList = null;
	ActivityDTO dto = null;
	String activityID = null;
	private Logger logger = (Logger) Logger.getLogger(ActivityDAO.class);

	/* DAO constructor */
	public ActivityDAO() {

		 
	}

	/*
	 * public String getActivityID() { return activityID; }
	 * 
	 * private String activityIDgenerator() { String id = "ACT" + new
	 * Date().getTime(); LoggerMsg.debug("this is roleIDgenerator() & value of
	 * ID " + id); return id; }
	 */
	/* ADD ACTIVITY MASTER */

	public void addActivitymaster(ActivityForm activityForm) {
		sql = ActivityCommonSQL.INSERT_ACTIVITY_MASTER;
		String param[] = new String[2];
		param[0] = activityForm.getActivityName();
		param[1] = activityForm.getActivityDesc();

		try {
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			boolean boo = dbUtility.executeUpdate(param);
			if (boo) {
				dbUtility.commit();
			} else {
				dbUtility.rollback();
			}
				 
		} catch (Exception e) {
			e.getStackTrace();
		}finally {
			try {
				dbUtility.closeConnection();
			}catch(Exception x) {
				x.getStackTrace();
			}
		}
	}

	/* GET ACTIVITY LIST */

	public ArrayList getActivityList() throws Exception {
		try {
			sql = ActivityCommonSQL.SELECT_ACTIVITY_MASTER;
			dbUtility = new DBUtility();
			dbUtility.createStatement();

			ArrayList mainList1 = dbUtility.executeQuery(sql);
			for (int i = 0; i < mainList1.size(); i++) {
				subList = (ArrayList) mainList1.get(i);
				dto = new ActivityDTO();
				dto.setActivityId(subList.get(0).toString());
				dto.setActivityName(subList.get(1).toString());
				dto.setActivityDesc(subList.get(2).toString());
				dto.setActivityStatus(subList.get(3).toString());
				activityList.add(dto);
			}
		} catch (Exception e) {
			e.getStackTrace();
		}finally {
			dbUtility.closeConnection();
		}
		return activityList;
	}

	/* UPDATE ACTIVITY MASTER */

	public void updateActivitymaster(ActivityForm activityForm) {

		dto = new ActivityDTO();
		String param[] = new String[4];
		param[0] = activityForm.getDto().getActivityName();
		param[1] = activityForm.getDto().getActivityDesc();
		param[2] = activityForm.getDto().getActivityStatus();
		param[3] = activityForm.getDto().getActivityId();
		sql = ActivityCommonSQL.UPDATE_ACTIVITY_MASTER;

		try {
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			boolean boo = dbUtility.executeUpdate(param);
			if (boo) {
				dbUtility.commit();
			}else {
				dbUtility.rollback();
			}	
		} catch (Exception e) {
			e.getStackTrace();
		}finally {
			try {
				dbUtility.closeConnection();
			}catch(Exception x){
				x.getStackTrace();
			}
		}
	}

	/* GET ACTIVITY ID */
	public ActivityDTO getActivityId(String activeid) throws Exception {
		try {
			sql = ActivityCommonSQL.SELECT_ACTIVITY_MASTER_ID;
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			String sd[] = new String[1];
			sd[0] = activeid;
			ArrayList list = dbUtility.executeQuery(sd);
			ArrayList list1 = (ArrayList) list.get(0);
			dto = new ActivityDTO();
			dto.setActivityId(list1.get(0).toString());
			dto.setActivityName(list1.get(1).toString());
			dto.setActivityDesc(list1.get(2).toString());
			dto.setActivityStatus(list1.get(3).toString());

		} catch (Exception e) {
			e.getStackTrace();
		}finally{
			dbUtility.closeConnection();
		}
		return dto;
	}

}
