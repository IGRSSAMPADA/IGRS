package com.wipro.igrs.suspensionenquiry.bao;


import java.util.*;

import com.wipro.igrs.suspensionenquiry.dao.SuspensionEnquiryDAO;
import com.wipro.igrs.suspensionenquiry.dto.SuspensionCriteriaDTO;

public class SuspensionEnquiryBAO {
	
	
	SuspensionEnquiryDAO susDAO = new SuspensionEnquiryDAO();
	
	public Collection search(SuspensionCriteriaDTO dto)
	{
		return susDAO.search(dto);
	}
	 
	public Collection getOfficesByType(String officeTypeId)
	{
		return susDAO.getOfficesByType(officeTypeId);
	}

}
