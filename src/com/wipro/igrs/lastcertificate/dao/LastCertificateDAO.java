package com.wipro.igrs.lastcertificate.dao;


import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.lastcertificate.dto.LastCertificateDTO;
import com.wipro.igrs.lastcertificate.sql.CommonSQL;

public class LastCertificateDAO {

	DBUtility dbUtil;
	private  Logger logger = 
		(Logger) Logger.getLogger(LastCertificateDAO.class);
	
	
	public LastCertificateDTO  getEmpLastPay(String[] param) throws Exception {
		ArrayList list = new ArrayList();
		ArrayList listRecovery = new ArrayList();
		LastCertificateDTO dto = new LastCertificateDTO();
		try{
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.EMP_SALARY_DETAILS);
			
			list = dbUtil.executeQuery(param);
			dto.setEmpDetails(list);
			
			dbUtil.createPreparedStatement(CommonSQL.EMP_RECOVERY_DETAILS);
			listRecovery = dbUtil.executeQuery(param);
			dto.setRecoveryDetails(listRecovery);
			
			
			dbUtil.createPreparedStatement(
					CommonSQL.EMP_MONTHLY_DEDUCTION_DETAILS);
			 
			dto.setMonthlyDeduction(dbUtil.executeQuery(param));
			
		}catch(Exception x) {
			logger.debug(x);
		}finally {
			dbUtil.closeConnection();
		}
		return dto;
	}
	
	public String[] getEmpCount(String[] param) throws Exception {
		String[] list = new String[2];
		try{
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.EMP_SALARY_COUNT_DETAILS);
			list[0] = dbUtil.executeQry(param);
			dbUtil.createPreparedStatement(CommonSQL.EMP_FUND_DETAILS);
			list[1] = dbUtil.executeQry(param);
			
		}catch(Exception x) {
			logger.debug(x);
		}finally {
			dbUtil.closeConnection();
		}
		return list;
	}
	
}
