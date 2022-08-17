package com.wipro.igrs.titlecertificate.bd;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.titlecertificate.dao.TitleCertificateDAO;

public class TitleCertificateBD {
	
	
	private  Logger logger = 
		(Logger) Logger.getLogger(TitleCertificateBD.class);
	
	public ArrayList getTitleCertificatePropertyDetails(String regID) {
		ArrayList list = new ArrayList();
		try {
			TitleCertificateDAO dao = new TitleCertificateDAO();
			list = 
				dao.getTitleCertificatePropertyDetails(regID);
		}catch(Exception x) {
			logger.debug(x);
		}
		return list;
	}
	public ArrayList getTitleCertificateOwnerDetails(String regID) {
		ArrayList list = new ArrayList();
		try {
			TitleCertificateDAO dao = new TitleCertificateDAO();
			logger.debug("getTitleCertificateOwnerDetails");
			list = 
				dao.getTitleCertificateOwnerDetails(regID);
		}catch(Exception x) {
			logger.debug(x);
		}
		return list;
	}	

}
