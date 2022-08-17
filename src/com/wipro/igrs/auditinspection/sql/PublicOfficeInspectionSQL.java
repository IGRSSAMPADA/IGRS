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

public class PublicOfficeInspectionSQL {

	 public static final String GET_EXISTING_INSPECTION_DETAILS = 
		 "SELECT POI_TXN_ID, to_char(INSP_DATE,'DD/MM/YYYY'), to_char(CREATED_DATE,'DD/MM/YYYY'), INSP_REPORT_STATUS"+
         " FROM  IGRS_POI_TXN_DTLS WHERE  (insp_from_date BETWEEN(?) AND (?))";
	 
	 
	 public static final String GET_EXISTING_POIID_DETAILS = 
		 "SELECT IPTD.POI_TXN_ID, IPTD.PO_NAME, IPTD.PO_ADDRESS, to_char(IPTD.INSP_DATE,'DD/MM/YYYY'), to_char(IPTD.CREATED_DATE,'DD/MM/YYYY') IPTD.CREATED_DATE,to_char(IPTD.INSP_FROM_DATE,'DD/MM/YYYY') "
		 +",to_char(IPTD.INSP_TO_DATE,'DD/MM/YYYY'), IPTD.FINAL_COMMENTS, IDM.DISTRICT_NAME FROM IGRS_POI_TXN_DTLS IPTD,IGRS_DISTRICT_MASTER IDM"
		 +" WHERE IPTD.DISTRICT_ID=IDM.DISTRICT_ID AND IPTD.POI_TXN_ID=?"; 
	 
	 
	 public static final String RETRIEVE_SEQ_POI_TXN_DETAILS=
	    	"SELECT 'POI'||TO_CHAR(SYSDATE,'DD')||TO_CHAR(SYSDATE,'MM')||TO_CHAR(SYSDATE,'RRRR')||"+
	         "LPAD(IGRS_POI_TXN_DTLS_SEQ.NEXTVAL,4,0)"+ 
	         "FROM DUAL";
 
	 public static final String insert_IGRS_POI_TXN="INSERT INTO IGRS_POI_TXN_DTLS \n"+
     "(PO_NAME,PO_ADDRESS,INSP_FROM_DATE,INSP_TO_DATE,INSP_DATE,\n"+
     " INSP_REPORT_STATUS,FINAL_COMMENTS,DISTRICT_ID,DRS_OFFICE_ID,DRS_USER_ID,\n" +
     " CREATED_BY,CREATED_DATE,UPDATE_BY,UPDATE_DATE,POI_TXN_ID,DISPATCH_DATE,DISPATCH_NO,INSPECTION_ENTRY_DATE)\n"+
     " VALUES(?,?,?,?,?,?,?,?,?,?,?,SYSDATE,?,SYSDATE,?,?,?,SYSDATE)";   
	 
	 public static final String INSERT_IGRS_POI_PARA_DETAILS=
	    	"INSERT INTO IGRS_POI_PARA_DTLS (PARA_TYPE_ID,PARA_NAME,PARA_COMMENTS,PARA_COMMENTS_DATE,PARA_STATUS,PARA_TXN_ID)"+
	    	" VALUES(?,?,?,SYSDATE,?,?)";

	 //MODIFIED BY SHRUTI FOR HINDI CONVERSION
	 public static final String GET_IGRS_POI_GET_PARA_TYPE_ID=
	    	"SELECT PARA_TYPE_ID FROM IGRS_AUDIT_PARA_TYPE_MASTER WHERE PARA_TYPE_NAME=? OR H_PARA_TYPE_NAME=?";
	 
	 public static final String INSERT_IGRS_POI_PARA_OBJ=
	    	"INSERT INTO IGRS_POI_PARA_OBJ_DTLS(OBJ_DETAILS, OBJ_DATE, OBJ_STATUS, REG_DOC_ID, VALUE_AT_REG_TIME, "+
	    	" VALUE_FROM_AGMP, DEFICIENT_STAMP_DUTY, DEFICIENT_REG_FEE,OBJ_TXN_ID) VALUES(?,SYSDATE,?,?,?,?,?,?,?)";
	 
	 public static final String INSERT_IGRS_POI_PARA_COMMENT=
	    	"INSERT INTO IGRS_POI_COMMENT_DTLS(DRS_USER_ID, DRS_USER_COMMENTS, COMMENTS_DATE, COMMENT_TXN_ID) VALUES(?,?,SYSDATE,?)";
	 
	 public static final String INSERT_IGRS_POI_COMMENT_MAPPING=
	    	"INSERT INTO IGRS_POI_COMMENT_MAP(POI_TXN_ID,COMMENT_TXN_ID,PARA_TXN_ID,OBJ_TXN_ID) VALUES(?,?,?,?)";
	 
	 public static final String INSERT_IGRS_POI_DOC_MAPPING=
	    	"INSERT INTO IGRS_POI_DOC_MAP(POI_TXN_ID,DOC_TXN_ID,PARA_TXN_ID,OBJ_TXN_ID) VALUES(?,?,?,?)";
	 
	 public static final String INSERT_IGRS_POI_PARA_MAPPING=
	    	"INSERT INTO IGRS_POI_PARA_MAP(POI_TXN_ID,PARA_TXN_ID) VALUES(?,?)";
	 
	 public static final String GET_EXISTING_POI_DETAILS=
	    	"select iptd.POI_TXN_ID,iptd.PO_NAME, iptd.PO_ADDRESS, idm.DISTRICT_NAME, to_char(iptd.INSP_DATE,'DD/MM/YYYY'), to_char(iptd.CREATED_DATE,'DD/MM/YYYY'),"+
	    	"to_char(iptd.INSP_FROM_DATE,'DD/MM/YYYY'), to_char(iptd.INSP_TO_DATE,'DD/MM/YYYY'),iptd.FINAL_COMMENTS from IGRS_POI_TXN_DTLS iptd,"+
	    	"IGRS_DISTRICT_MASTER idm where iptd.DISTRICT_ID=idm.DISTRICT_ID AND iptd.POI_TXN_ID=?";
	 
	 public static final String GET_POI_TXN_DETAILS_PRINT=
	    	"SELECT DISTINCT iptd.PO_NAME, iptd.PO_ADDRESS, idm.DISTRICT_NAME, to_char(iptd.INSP_DATE,'DD/MM/YYYY')," +
	    	" to_char(iptd.CREATED_DATE,'DD/MM/YYYY'), to_char(iptd.INSP_FROM_DATE,'DD/MM/YYYY'), to_char(iptd.INSP_TO_DATE,'DD/MM/YYYY')," +
	    	" iptd.FINAL_COMMENTS FROM IGRS_POI_TXN_DTLS iptd, IGRS_DISTRICT_MASTER idm"
            +" WHERE iptd.DISTRICT_ID =idm.DISTRICT_ID AND iptd.POI_TXN_ID=?";
	 
	 public static final String GET_POI_TXN_DOC_DETAILS_PRINT=
	    	"SELECT  ipdd.DOC_TXN_ID, ipdd.DOC_NAME from IGRS_POI_DOC_DTLS ipdd,IGRS_POI_DOC_MAP ipdm "
           +"WHERE ipdm.DOC_TXN_ID = ipdd.DOC_TXN_ID AND ipdm.POI_TXN_ID=?";
	 
	 public static final String GET_POI_TXN_PARA_DETAILS_PRINT=
	    	"SELECT iaptm.PARA_TYPE_NAME, ippd.PARA_NAME, ippd.PARA_COMMENTS "  
            +"from IGRS_AUDIT_PARA_TYPE_MASTER iaptm, IGRS_POI_PARA_DTLS ippd, IGRS_POI_PARA_MAP ipdm "
            +"WHERE iaptm.PARA_TYPE_ID = ippd.PARA_TYPE_ID AND ippd.PARA_TXN_ID  = ipdm.PARA_TXN_ID "
            +"AND ipdm.POI_TXN_ID =?";


	public static final String SELECT_POI_PARA_DETAILS = "select PARA_TYPE_ID,PARA_TYPE_NAME from IGRS_AUDIT_PARA_TYPE_MASTER where PARA_TYPE_STATUS='A' AND AUDIT_TYPE='I'";
	//added by shruti
	public static final String SELECT_POI_PARA_DETAILS_H = "select PARA_TYPE_ID,H_PARA_TYPE_NAME from IGRS_AUDIT_PARA_TYPE_MASTER where PARA_TYPE_STATUS='A' AND AUDIT_TYPE='I'";
	//end
	// added by vinay
	public static final String GET_POI_ID_DETAILS="SELECT POI_TXN_ID,TO_CHAR(INSP_DATE,'dd/mm/yyyy'),INSP_REPORT_STATUS FROM IGRS_POI_TXN_DTLS WHERE 1=1 ";
	public static final String GET_POI_DETAILS="SELECT POI.PO_NAME,POI.PO_ADDRESS,DIST.DISTRICT_NAME,TO_CHAR(POI.INSP_DATE,'DD/MM/YYYY'),TO_CHAR(POI.DISPATCH_DATE,'DD/MM/YYYY'),POI.DISPATCH_NO,TO_CHAR(POI.INSP_FROM_DATE,'DD/MM/YYYY'),TO_CHAR(POI.INSP_TO_DATE,'DD/MM/YYYY'),POI.FINAL_COMMENTS,TO_CHAR(INSPECTION_ENTRY_DATE,'DD/MM/YYYY'),POI.INSP_REPORT_STATUS FROM IGRS_POI_TXN_DTLS POI, IGRS_DISTRICT_MASTER DIST WHERE DIST.DISTRICT_ID=POI.DISTRICT_ID AND POI.POI_TXN_ID=?";
	public static final String GET_DOC_NAMES="SELECT DOC_NAME FROM IGRS_POI_DOC_DTLS WHERE DOC_TXN_ID IN (SELECT DOC_TXN_ID FROM IGRS_POI_DOC_MAP WHERE POI_TXN_ID=?)";
	
	public static final String GET_PARA_DETAILS="SELECT APARA.PARA_TYPE_NAME,PARA.PARA_NAME,PARA.PARA_COMMENTS,PARA.PARA_TXN_ID,PARA.PARA_STATUS FROM IGRS_POI_PARA_DTLS PARA,IGRS_AUDIT_PARA_TYPE_MASTER APARA WHERE APARA.PARA_TYPE_ID=PARA.PARA_TYPE_ID AND PARA_TXN_ID IN(SELECT PARA_TXN_ID FROM IGRS_POI_PARA_MAP WHERE POI_TXN_ID=?)";
	public static final String GET_PARA_DETAILS_H="SELECT APARA.H_PARA_TYPE_NAME,PARA.PARA_NAME,PARA.PARA_COMMENTS,PARA.PARA_TXN_ID,PARA.PARA_STATUS FROM IGRS_POI_PARA_DTLS PARA,IGRS_AUDIT_PARA_TYPE_MASTER APARA WHERE APARA.PARA_TYPE_ID=PARA.PARA_TYPE_ID AND PARA_TXN_ID IN(SELECT PARA_TXN_ID FROM IGRS_POI_PARA_MAP WHERE POI_TXN_ID=?)";
	
	
	public static final String  GET_OBJECTED_DOC_DETAILS="select  IPPOD.REG_DOC_ID,IPPOD.OBJ_DETAILS,IPCD.DRS_USER_COMMENTS,IPPOD.OBJ_STATUS FROM IGRS_POI_PARA_OBJ_DTLS IPPOD, "+
															" IGRS_POI_COMMENT_MAP IPCM,IGRS_POI_COMMENT_DTLS IPCD WHERE ipcd.COMMENT_TXN_ID=IPCM.COMMENT_TXN_ID"+ 
															" AND  ipcm.OBJ_TXN_ID=IPPOD.OBJ_TXN_ID AND"+ 
															" IPCM.POI_TXN_ID=?";
	public static final String GETPOICOMMENTID="SELECT 'CMT'||LPAD(IGRS_POI_COMMENT_DTLS_SEQ.NEXTVAL,4,0) FROM DUAL";
	public static final String GETPOIOBJECTIONID="SELECT 'POBJ'||LPAD(IGRS_POI_PARA_OBJ_DTLS_SEQ.NEXTVAL,4,0) FROM DUAL";
	public static final String GETDISTRICT="Select DISTRICT_ID,DISTRICT_NAME from IGRS_DISTRICT_MASTER where DISTRICT_STATUS='A' and STATE_ID='1'";
	//ADDED BY SHRUTI
	public static final String GETDISTRICT_H="Select DISTRICT_ID,H_DISTRICT_NAME from IGRS_DISTRICT_MASTER where DISTRICT_STATUS='A' and STATE_ID='1'";
	//END
	public static final String GETOFFICENAME="Select  OFFICE_NAME from IGRS_OFFICE_MASTER where OFFICE_ID=?";
	public static final String GETPOOFFICE="select PO_DEPT_ID,PO_DEPT_NAME from IGRS_PUBLIC_OFFICE_DEPT_MASTER where PO_DEPT_STATUS=?";
	
	public static final String GETPARADTLS="select irpd.PARA_TXN_ID,irpd.PARA_TYPE_ID,irpd.PARA_NAME,irpd.PARA_COMMENTS,irpd.PARA_STATUS from IGRS_POI_PARA_DTLS irpd where irpd.PARA_TXN_ID=?";

	public static final String GETOBJECTIONDETAILS="select IRPOB.OBJ_TXN_ID,IRPOB.REG_DOC_ID,IRPOB.OBJ_STATUS,IRPOB.CASE_TXN_ID FROM IGRS_POI_PARA_OBJ_DTLS IRPOB, IGRS_POI_COMMENT_MAP IRPOM"
			+" WHERE IRPOB.OBJ_TXN_ID=IRPOM.OBJ_TXN_ID AND IRPOM.PARA_TXN_ID=?";
	public static final String GETOBJECTIONPARADETAILS="select OBJ_DETAILS,OBJ_STATUS,REG_DOC_ID,VALUE_AT_REG_TIME,VALUE_FROM_AGMP,DEFICIENT_STAMP_DUTY,"
		+"DEFICIENT_REG_FEE,CASE_TXN_ID,CREATED_DATE from IGRS_POI_PARA_OBJ_DTLS where OBJ_TXN_ID=?";

	public static final String UPDATEOBJECTIONPARADETAILS="UPDATE IGRS_POI_PARA_OBJ_DTLS SET OBJ_STATUS=? WHERE OBJ_TXN_ID=?";
	public static final String UPDATEPARASTATUS="UPDATE IGRS_POI_PARA_DTLS SET PARA_COMMENTS=?,PARA_STATUS=? WHERE PARA_TXN_ID=?";

//added by ShreeraJ
	public static final String GET_DOC_PATH="SELECT DOC_PATH,DOC_NAME FROM IGRS_POI_DOC_DTLS WHERE DOC_TXN_ID IN (SELECT DOC_TXN_ID FROM IGRS_POI_DOC_MAP WHERE POI_TXN_ID=?)";


}