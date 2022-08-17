package com.wipro.igrs.lastcertificate.bd;

import org.apache.log4j.Logger;

import com.wipro.igrs.lastcertificate.dao.LastCertificateDAO;
import com.wipro.igrs.lastcertificate.dto.LastCertificateDTO;


public class LastCertificateBD {
	
	
	private  Logger logger = 
		(Logger) Logger.getLogger(LastCertificateBD.class);
	
	public LastCertificateDTO getEmpSalary(String[] param) {
		LastCertificateDTO list = new LastCertificateDTO();
		try {
			list = new LastCertificateDAO().getEmpLastPay(param);
		}catch(Exception x) {
			logger.debug(x);
		}
		return list;
	}
		

}
