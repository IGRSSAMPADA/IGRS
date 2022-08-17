package com.wipro.igrs.generatecertificate.rule;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.generatecertificate.dao.GenerateCertificateDAO;
import com.wipro.igrs.generatecertificate.dto.GenerateCertificateDTO;
import com.wipro.igrs.generatecertificate.util.PropertiesFileReader;


public class GenerateCertificateRule {
	private boolean error;
	//private PropertiesFileReader pr;
	private Logger logger = (Logger) Logger
	.getLogger(GenerateCertificateRule.class);
	
	
	public ArrayList validateCertificate(
						String empID, PropertiesFileReader pr) {
		boolean flag = false;
		ArrayList errorList = new ArrayList();
		
		try {
			
			errorList.add(pr.getValue("error.header"));
			
		    GenerateCertificateDAO dao = new GenerateCertificateDAO();
		    String check = dao.getEmpCheck(empID);
			if("".equals(empID)) {
				errorList.add(pr.getValue("error.empid"));
				flag = true;
			}
			if(check != null && Integer.parseInt(check) == 0) {
				errorList.add(pr.getValue("error.lastpay.empid"));
				flag = true;
			}
			setError(flag);
		}catch(Exception x) {
			logger.debug(x);
		}
		
		
		
		
		return errorList;
	}
	public ArrayList validateCertificateName(
			String empNames, PropertiesFileReader pr) {
		boolean flag = false;
		ArrayList errorList = new ArrayList();
		
		try {
			
			errorList.add(pr.getValue("error.header"));
			
		    GenerateCertificateDAO dao = new GenerateCertificateDAO();
		    
		    if(empNames != null) {
				String[] empAry = empNames.split(",");
				if(empAry!=null) {
					for(int i = 0;i<empAry.length;i++) {
						String[] empName = empAry[i].split("~");
						if(empName!=null) {
							if(empName[0] !=null && empName[1] !=null) {
							 
								if("".equals(empName[0])) {
									errorList.add(pr.getValue("error.empid"));
									flag = true;
								}
								String check = dao.getEmpCheck(empName[0]);
								
								if(check != null && Integer.parseInt(check) == 0) {
									errorList.add("<li><font color=\"red\">"+
											empName[0]+" </font></li> "+
											pr.getValue("error.lastpay.empid"));
									flag = true;
								}
							}
						}
						 
					}
				}
			}
		    
			setError(flag);
		}catch(Exception x) {
			logger.debug(x);
		}
		return errorList;
	}
	public void setError(boolean error) {
		this.error = error;
	}
	public boolean isError() {
		return error;
	}
}
