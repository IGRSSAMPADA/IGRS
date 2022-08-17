/**
 * 
 */
package com.wipro.igrs.departmentalenquiry.dao;

import java.util.List;

import com.wipro.igrs.departmentalenquiry.dto.DepartmentalCriteriaDTO;

/**
 * @author HMOHAM
 *
 */
public interface IDepartmentalEnquirySearchDAO {

	 List search(DepartmentalCriteriaDTO dto);
	 
	 List getOfficesByType(String officeTypeId);
	 
	 List getComplaintStatusList();
}
