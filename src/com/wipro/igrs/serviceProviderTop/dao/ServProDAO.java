package com.wipro.igrs.serviceProviderTop.dao;

import java.util.ArrayList;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.serviceProviderTop.dto.DTO;

public class ServProDAO {

	public ArrayList getData(DTO dto) {
ArrayList alist = new ArrayList();
String param[]=new String[2];
param[0]=dto.getDistrict();
param[1]=dto.getDistrict();
		try {
			DBUtility db = new DBUtility();

			//modified by shruti
			String sql="SELECT AUTHRZD_PERSN_NAME, " +
					"ADDRESS " +
					"FROM IGRS_SP_DETLS " +
					"WHERE DISTRICT_ID=? " +
					"AND SP_TYPE_ID!  ='1' and APPLCTN_STATUS='8' and STATUS='A'" +
					"UNION ALL " +
					"SELECT ax.FIRST_NAME " +
					"||' ' " +
					"||ax.MIDDLE_NAME " +
					"||' ' " +
					"||ax.LAST_NAME AS Name,sp.ADDRESS " +
					"FROM IGRS_SP_DETLS sp, " +
					"IGRS_USER_REG_DETAILS ax " +
					"WHERE SP.CREATED_BY=AX.USER_ID " +
					"AND sp.district_id =? " +
					"AND sp.sp_type_id  ='1' and sp.APPLCTN_STATUS='8' and sp.STATUS='A'";
			//end
			db.createPreparedStatement(sql);
			alist=db.executeQuery(param);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return alist;
	}






}
