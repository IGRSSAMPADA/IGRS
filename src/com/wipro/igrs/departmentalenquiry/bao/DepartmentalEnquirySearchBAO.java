package com.wipro.igrs.departmentalenquiry.bao;

import java.util.Collection;
import java.util.List;

import com.wipro.igrs.departmentalenquiry.dto.DepartmentalCriteriaDTO;
import com.wipro.igrs.departmentalenquiry.dao.DepartmentalEnquirySearchDAO;

public class DepartmentalEnquirySearchBAO {
	
	DepartmentalEnquirySearchDAO deptDAO = new DepartmentalEnquirySearchDAO();
		
	 public Collection search(DepartmentalCriteriaDTO dto)
	 {
		 return deptDAO.search(dto);
	 }
	 
	 public Collection getOfficesByType(String officeTypeId)
	 {
		 return deptDAO.getOfficesByType(officeTypeId);
	 }

	/**
	 * @return
	 * @see com.wipro.igrs.departmentalenquiry.dao.DepartmentalEnquirySearchDAO#getComplaintStatusList()
	 */
	public List getComplaintStatusList() {
		return deptDAO.getComplaintStatusList();
	}

	 
}
