package com.wipro.igrs.departmentalenquiry.bd;

import java.util.Collection;
import java.util.List;

import com.wipro.igrs.departmentalenquiry.bao.DepartmentalEnquirySearchBAO;
import com.wipro.igrs.departmentalenquiry.dto.DepartmentalCriteriaDTO;

public class DepartmentalEnquirySearchBD {

	DepartmentalEnquirySearchBAO deptBAO = new DepartmentalEnquirySearchBAO();
	
	public Collection search(DepartmentalCriteriaDTO dto)
	 {
		 return deptBAO.search(dto);
	 }
	 
	 public Collection getOfficesByType(String officeTypeId)
	 {
		 return deptBAO.getOfficesByType(officeTypeId);
	 }

	/**
	 * @return
	 * @see com.wipro.igrs.departmentalenquiry.bao.DepartmentalEnquirySearchBAO#getComplaintStatusList()
	 */
	public List getComplaintStatusList() {
		return deptBAO.getComplaintStatusList();
	}
	
	 
}
