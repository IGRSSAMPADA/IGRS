/*
/*
* Copyright Notice
*==============================================================================
* This file contains proprietary information of IGRS Bhopal Madhya Pradesh .
* Copying or reproduction without prior written approval is prohibited.
* Copyright (c) 2007-08
*==============================================================================
*
* File Name   :   
 * Author      :   
 * Description :   
 * ----------------------------------------------------------------------------
* Version         Modified By     Modified On           Modification
* ----------------------------------------------------------------------------
* 1.0             SHRUTI          xxxxx              
 * --------------------------------------------------------------------------------
*/



package com.wipro.igrs.auditinspection.bd;


import com.wipro.igrs.auditinspection.dao.PoiTxnDAO;
import com.wipro.igrs.auditinspection.dto.PoiTxnDTO;

public class PoiTxnBD {
	
	
	
	PoiTxnDAO poiDAO=new PoiTxnDAO();
	
	public PoiTxnDTO getTxnDtlsByID(String txnID)
	{
		return poiDAO.getTxnDtlsByID(txnID);
	}
	
	public boolean updateTxnDtls(PoiTxnDTO poiDTO)
	{
		return poiDAO.updateTxnDtls(poiDTO);
	}

}
