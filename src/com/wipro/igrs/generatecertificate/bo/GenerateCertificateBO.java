package com.wipro.igrs.generatecertificate.bo;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.generatecertificate.bd.GenerateCertificateBD;
import com.wipro.igrs.generatecertificate.dto.GenerateCertificateDTO;

public class GenerateCertificateBO {
	private  Logger logger = 
		(Logger) Logger.getLogger(GenerateCertificateBO.class);
	
	public GenerateCertificateDTO getProbationCertificate(String empID) {
		GenerateCertificateDTO dto = new GenerateCertificateDTO(); 
		try {
			GenerateCertificateBD bd = new GenerateCertificateBD();
			ArrayList retlist = 
				bd.getProbationCertificate(empID);
			String empName ="";
			String systemDate = "";
			String content = "";
			String footer = "";
			if(retlist!=null) {
				ArrayList listEmp = (ArrayList) retlist.get(0);
				ArrayList listContent = (ArrayList)retlist.get(1);
				if(listEmp != null) {
					for(int i =0;i<listEmp.size();i++) {
						ArrayList listRet = (ArrayList)listEmp.get(i);
						empName = (String)listRet.get(0);
						systemDate = (String)listRet.get(1);
						logger.debug("empName:-"+empName+":"+systemDate);
						dto.setEmpName(empName+" ("+empID+")");
					}
				}
				if(listContent != null) {
					for(int i =0;i<listContent.size();i++) {
						ArrayList listRet = (ArrayList)listContent.get(i);
						dto.setHeader((String)listRet.get(0));
						footer = (String)listRet.get(1);
						footer = footer.replaceAll("#SYSDATE#", systemDate);
						String footer1 = footer.replaceAll("#EMPNAME#",
											empName);
						
						dto.setFooter(footer1);
						content = (String)listRet.get(2);
						content = content.replaceAll("#EMPNAME#", empName);
						
						
						logger.debug("content:-"+content);
						
						dto.setContent(content);
					}
				}
			}
			
		}catch(Exception x) {
			logger.debug(x);
		}
		return dto;
	}
	public ArrayList getEmployeeDetails() {
		ArrayList list = new ArrayList() ;
		 
		try {
			GenerateCertificateBD bd = new GenerateCertificateBD();
			ArrayList retList = bd.getConfirmEmployee();
			if(retList!=null) {
				for(int i = 0; i<retList.size(); i++) {
					GenerateCertificateDTO dto = new GenerateCertificateDTO();
					ArrayList listEmp  = (ArrayList)retList.get(i);
					dto.setConfirmEmpID((String)listEmp.get(1)
									+"~"+(String)listEmp.get(0)
									+"~"+(String)listEmp.get(2));
					dto.setConfirmEmpName((String)listEmp.get(0));
					
					list.add(dto);
				}
			}
			
		}catch(Exception x) {
			logger.debug(x);
		}
		return list;
	}
	public ArrayList getEmployeeName(String empDetail) {
		ArrayList list = new ArrayList();
		ArrayList returnList = new ArrayList();
		String sysdate ="";
		String empFullName="";
		logger.debug("***"+empDetail);
		if(empDetail != null) {
			String[] empAry = empDetail.split(",");
			if(empAry!=null) {
				for(int i = 0;i<empAry.length;i++) {
					String[] empName = empAry[i].split("~");
					GenerateCertificateDTO dto = new GenerateCertificateDTO();
					if(empName!=null) {
						if(empName[0] !=null && empName[1] !=null) {
							dto.setConfirmEmpID(empName[0]);
							dto.setConfirmEmpName(empName[1]);
							empFullName=empName[1];
							sysdate = empName[2];
							dto.setConfirmDate(sysdate);
						}
					}
					returnList.add(dto) ;
				}
			}
		}
		list.add(getConfirmationCertificate(sysdate,empFullName));
		list.add(returnList);
		return list;
	}
	private GenerateCertificateDTO getConfirmationCertificate(String sysdate,String empFullName) {
		GenerateCertificateDTO dto = new GenerateCertificateDTO();
		try {
			GenerateCertificateBD bd = new GenerateCertificateBD();
			ArrayList listContent = bd.getConfirmCertificate();
			String header = "";
			String content = "";
			String footer = "";
			if(listContent != null) {
				for(int i =0;i<listContent.size();i++) {
					ArrayList listRet = (ArrayList)listContent.get(i);
					header = (String)listRet.get(0);
					
					header = header.replaceAll("#SYSDATE#", sysdate);
					
					dto.setHeader(header);
					footer = (String)listRet.get(1);
					
					footer = footer.replaceAll("#SYSDATE#", sysdate);
					footer = footer.replaceAll("#EMPNAME#",
							empFullName);
					dto.setFooter(footer);
					content = (String)listRet.get(2);
					content = content.replaceAll("#SYSDATE#", sysdate);
					
					
					logger.debug("content:-"+content);
					
					dto.setContent(content);
				}
			}
		}catch(Exception x) {
			logger.debug(x);
		}
		return dto ;
	}
}
