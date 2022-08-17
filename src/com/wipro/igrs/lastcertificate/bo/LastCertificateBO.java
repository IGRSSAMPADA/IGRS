package com.wipro.igrs.lastcertificate.bo;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.lastcertificate.bd.LastCertificateBD;
import com.wipro.igrs.lastcertificate.dto.LastCertificateDTO;
import com.wipro.igrs.util.CommonUtil;



public class LastCertificateBO {
	private  Logger logger = 
		(Logger) Logger.getLogger(LastCertificateBO.class);
	
	public LastCertificateDTO getEmpSalary(String  empID) {
		LastCertificateDTO dtoReturn = new LastCertificateDTO();
		ArrayList dtoList = new ArrayList();
		ArrayList listRecov = new ArrayList();
		ArrayList listDed = new ArrayList();
		String empName = "";
		String pfNo = "";
		String pfAuthority = "";
		String currentDate = "";
		try {
			String[] param = new String[1];
			param[0] = empID;
			LastCertificateDTO dtoListReturn 
				= new LastCertificateBD().getEmpSalary(param);
			ArrayList list = dtoListReturn.getEmpDetails();
			ArrayList listRecovery = dtoListReturn.getRecoveryDetails();
			ArrayList listDeduction = dtoListReturn.getMonthlyDeduction();
			CommonUtil util = new CommonUtil();
			if(list!=null) {
				for(int i=0;i<list.size();i++) {
					ArrayList listReturn = (ArrayList) list.get(i);
					LastCertificateDTO dto = new LastCertificateDTO();
					empName =(String)listReturn.get(0);
					pfNo = (String)listReturn.get(3);
					pfAuthority = (String)listReturn.get(4);
					currentDate = (String)listReturn.get(5);
					dto.setEmpName(empName);
					dto.setSalaryHead((String)listReturn.get(1));
					dto.setSalaryRate(
							util.getCurrencyFormat((String)listReturn.get(2)));
					dto.setEmpPFNo(pfNo);
					dto.setPfAuthority(pfAuthority);
					dto.setCurrentDate(currentDate);
					dtoList.add(dto);
				}
			}
			
			if(listDeduction!=null) {
				for(int i=0;i<listDeduction.size();i++) {
					ArrayList listReturn = (ArrayList) listDeduction.get(i);
					LastCertificateDTO dto = new LastCertificateDTO();
					 
					dto.setRecoveryName((String)listReturn.get(0));
					dto.setRecoveryAmt(
							util.getCurrencyFormat((String)listReturn.get(1)));
					dto.setRecoveryDuration((String)listReturn.get(2));
					listDed.add(dto);
				}
			}
			if(listDeduction!=null) {
				for(int i=0;i<listDeduction.size();i++) {
					ArrayList listReturn = (ArrayList) listDeduction.get(i);
					LastCertificateDTO dto = new LastCertificateDTO();
					 
					dto.setDeductionDate((String)listReturn.get(0));
					dto.setSalary(
							util.getCurrencyFormat((String)listReturn.get(1)));
					dto.setComponentAmount(
							util.getCurrencyFormat((String)listReturn.get(3)));
					dto.setComponentName((String)listReturn.get(4));
					listDed.add(dto);
				}
			}
			logger.debug("empName:-"+empName+":"+pfNo+":"+pfAuthority+":"+currentDate);
			dtoReturn.setEmpName(empName);
			dtoReturn.setEmpPFNo(pfNo);
			dtoReturn.setPfAuthority(pfAuthority);
			dtoReturn.setCurrentDate(currentDate);
			dtoReturn.setSalaryHeadList(dtoList);
			dtoReturn.setRecoveryDetails(listRecov);
			dtoReturn.setMonthlyDeduction(listDed);
		}catch(Exception x) {
			logger.debug(x);
		}
		return dtoReturn;
	}
}
