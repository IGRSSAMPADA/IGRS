package com.wipro.igrs.generatecertificate.bd;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.generatecertificate.constant.CommonConstant;
import com.wipro.igrs.generatecertificate.dao.GenerateCertificateDAO;
import com.wipro.igrs.generatecertificate.sql.CommonSQL;

public class GenerateCertificateBD {
	
	
	private  Logger logger = 
		(Logger) Logger.getLogger(GenerateCertificateBD.class);
	
	public ArrayList getProbationCertificate(String empID) {
		ArrayList list = new ArrayList();
		try {
			GenerateCertificateDAO dao = new GenerateCertificateDAO();
			list = 
				dao.getProbationCertificate(empID);
		}catch(Exception x) {
			logger.debug(x);
		}
		return list;
	}
	public ArrayList getConfirmEmployee()   {
		ArrayList list = new ArrayList();
		 
		try {
			GenerateCertificateDAO dao = new GenerateCertificateDAO();
			list = dao.getConfirmEmployee();
		}catch(Exception x) {
			logger.debug(x);
		}
		return list;
	 
	}
	public ArrayList getConfirmCertificate() {
		ArrayList list = new ArrayList();
		 
		try {
			GenerateCertificateDAO dao = new GenerateCertificateDAO();
			list = dao.getConfirmCertificate();
		}catch(Exception x) {
			logger.debug(x);
		}
		return list;
	}

}
