package com.wipro.igrs.CitizenFeedback.dao;

import java.util.ArrayList;
import org.apache.log4j.Logger;

import com.wipro.igrs.CitizenFeedback.sql.CitizenFeedbackSQL;
import com.wipro.igrs.UserRegistration.dto.UserRegistrationDTO;
import com.wipro.igrs.UserRegistration.sql.CommonSQL;
import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.reginit.dto.CommonDTO;
import com.wipro.igrs.reginit.sql.RegCommonSQL;



public class FeedbackComplaintDAO {

	public ArrayList getFServices() throws Exception {
		  
		DBUtility	 dbUtil = new DBUtility();
		ArrayList list = null;
		 
		try {
			 dbUtil.createStatement();
			 list = dbUtil.executeQuery(CitizenFeedbackSQL.SELECT_SERVICE_NAME);
		} catch (Exception e) {
			//System.out.println("Exception in getFServices():" + e);
		}finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			}catch(Exception x) {
				//System.out.println(x);
			}
		}
		return list;
	}
	






	public ArrayList getFdistrict() throws Exception {
		{				DBUtility	 dbUtil = new DBUtility();
			ArrayList list = null;
			 
			try {
				 dbUtil.createStatement();
				 list = dbUtil.executeQuery(CitizenFeedbackSQL.SELECT_DISTRICT_NAME2);
			} catch (Exception e) {
				//System.out.println("Exception in getFdistrict():" + e);
			}finally {
				try {
					if (dbUtil != null) {
						dbUtil.closeConnection();
					}
				}catch(Exception x) {
					//System.out.println(x);
				}
			}
			return list;
		}
		
	}

	
}
