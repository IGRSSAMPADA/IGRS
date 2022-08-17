package com.wipro.igrs.titlecertificate.dao;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.titlecertificate.sql.CommonSQL;

public class TitleCertificateDAO {

	DBUtility dbUtil;
	private  Logger logger = 
		(Logger) Logger.getLogger(TitleCertificateDAO.class);
	
	public ArrayList getTitleCertificatePropertyDetails(String regID)
				throws Exception{
		ArrayList list = new ArrayList();
		try {
			String[] param = new String[1];
			param[0] = regID;
			dbUtil = new DBUtility();
			
			dbUtil.createPreparedStatement(CommonSQL.TITLE_DETAILS_QUERY);
			list = dbUtil.executeQuery(param);
		}catch(Exception x) {
			logger.debug(x);
		}finally {
			dbUtil.closeConnection();
		}
		return list;
	}
	
	public ArrayList getTitleCertificateOwnerDetails(String regID)
										throws Exception{
		ArrayList list = new ArrayList();
		try {
			String[] param = new String[1];
			param[0] = regID;
			dbUtil = new DBUtility();
			
			dbUtil.createPreparedStatement(CommonSQL.PROPRIETOR_NAME_QUERY);
			list = dbUtil.executeQuery(param);
		}catch(Exception x) {
			logger.debug(x);
		}finally {
			dbUtil.closeConnection();
		}
		return list;
	}
}
