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


package com.wipro.igrs.auditinspection.sql;

public class PoiTxnCreateCommonSql {
	
	public static final String GET_TXN_DTLS_BY_ID="select * from IGRS_POI_TXN_DTLS where POI_TXN_ID=?";
	public static final String UPDATE_TXN_TDLS="UPDATE IGRS_POI_TXN_DTLS SET INSP_DATE=?,DISPATCH_NO=? where POI_TXN_ID=?";

	
}
