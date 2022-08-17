package com.wipro.igrs.auditinspection.bd;

import java.util.ArrayList;

import com.wipro.igrs.auditinspection.dao.AddAuditComplianceDAO;
import com.wipro.igrs.auditinspection.dto.AGMPReportDetailsDTO;
import com.wipro.igrs.auditinspection.dto.PublicDTO;


public class AddAuditComplianceBD {

	AddAuditComplianceDAO dao=new AddAuditComplianceDAO();
	public String getRole(String officeid)
	{
		return dao.getRole(officeid);
	}
	
	public ArrayList searchReport(AGMPReportDetailsDTO reportDetailsDTO,String ofcId)
	{
		return dao.searchReport(reportDetailsDTO,ofcId);
	}

	public boolean updateStatus(AGMPReportDetailsDTO agmpDTO) {
		return dao.updateStatus(agmpDTO);
	}
	
}
