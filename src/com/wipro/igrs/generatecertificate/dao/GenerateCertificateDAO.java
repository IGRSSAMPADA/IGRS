package com.wipro.igrs.generatecertificate.dao;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.generatecertificate.constant.CommonConstant;
import com.wipro.igrs.generatecertificate.sql.CommonSQL;

public class GenerateCertificateDAO {

	DBUtility dbUtil;
	private  Logger logger = 
		(Logger) Logger.getLogger(GenerateCertificateDAO.class);
	
	public ArrayList getProbationCertificate(String empID)
				throws Exception{
		ArrayList list = new ArrayList();
		 
		try {
			String[] param = new String[1];
			param[0] = empID;
			dbUtil = new DBUtility();
			
			dbUtil.createPreparedStatement(CommonSQL.EMP_DETAILS);
			//list = dbUtil.executeQuery(param);
			list.add(dbUtil.executeQuery(param));
			
			param[0] = CommonConstant.PROBATION_TEMPLATE_NAME;
			dbUtil.createPreparedStatement(CommonSQL.PROBATION_CONTENT_QUERY);
			list.add(dbUtil.executeQuery(param));
			
		}catch(Exception x) {
			logger.debug(x);
		}finally {
			dbUtil.closeConnection();
		}
		return list;
	}
	public ArrayList getConfirmEmployee() throws Exception {
		ArrayList list = new ArrayList();
		 
		try {
			 
		 
			dbUtil = new DBUtility();
			
			dbUtil.createStatement();
			
			list = dbUtil.executeQuery(CommonSQL.EMP_CONFIRM_DETAILS);
		 
			
		}catch(Exception x) {
			logger.debug(x);
		}finally {
			dbUtil.closeConnection();
		}
		return list;
	 
	}
	
	public ArrayList getConfirmCertificate() throws Exception {
		ArrayList list = new ArrayList();
		 
		try {
			String[] param = new String[1];
		 
		 
			dbUtil = new DBUtility();
			param[0] = CommonConstant.CONFIRMATION_TEMPLATE_NAME;
			dbUtil.createPreparedStatement(CommonSQL.PROBATION_CONTENT_QUERY);
			list = dbUtil.executeQuery(param);
			
		}catch(Exception x) {
			logger.debug(x);
		}finally {
			dbUtil.closeConnection();
		}
		return list;
	}
	public String getEmpCheck(String empID) throws Exception {
		String check = "";
		String sql = CommonSQL.EMP_CHECK;
		if(empID !="" || !"".equals(empID)) {
			sql = sql +"AND UPPER(IGRS_EMP_MASTER.EMP_ID)=UPPER('"+empID+"')";
		}
		logger.debug("SQL:-"+sql);
		
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			check = dbUtil.executeQry(sql);
			logger.debug("check :-"+check);
		}catch(Exception x) {
			logger.debug(x);
		}
		return check;
	}
}
