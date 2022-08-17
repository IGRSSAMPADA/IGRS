package com.wipro.igrs.suspensionenquiry.bd;

import java.util.Collection;

import com.wipro.igrs.suspensionenquiry.dto.SuspensionCriteriaDTO;

import com.wipro.igrs.suspensionenquiry.bao.*;
public class SuspensionEnquiryBD {

	
SuspensionEnquiryBAO susBAO = new SuspensionEnquiryBAO();
	
	public Collection search(SuspensionCriteriaDTO dto)
	{
		return susBAO.search(dto);
	}
	 
	public Collection getOfficesByType(String officeTypeId)
	{
		return susBAO.getOfficesByType(officeTypeId);
	}
	
}
