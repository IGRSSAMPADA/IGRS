/**
 * 
 */
package com.wipro.igrs.suspensionenquiry.dao;

import java.util.List;

import com.wipro.igrs.departmentalenquiry.dto.DepartmentalCriteriaDTO;
import com.wipro.igrs.suspensionenquiry.dto.SuspensionCriteriaDTO;

/**
 * @author HMOHAM
 *
 */
public interface ISuspensionEnquiryDAO {

	public List search(SuspensionCriteriaDTO dto);
	 
	public List getOfficesByType(String officeTypeId);
}
