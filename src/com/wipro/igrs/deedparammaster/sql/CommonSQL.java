/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   CommonSQL.java
 * Author      :   Imran Shaik
 * Description :   Represents the SQL Class for Registration Initiation.
 * ----------------------------------------------------------------------------
 * Version         Last Modified By     Last Modified On           Modification
 * ----------------------------------------------------------------------------
 * 	0.0             Imran Shaik		     18th Mar, 2008	 
 *  1.0         Sayed Taha(Wipro-egypt)     27th Aug, 2008 		 
 * ----------------------------------------------------------------------------
 */


package com.wipro.igrs.deedparammaster.sql;


public class CommonSQL {
    /*done by basem */public static final String INSERT_DEEDPARAM_MASTER = "INSERT INTO IGRS_DEED_PARAM_MASTER M (M.PARAM_ID,M.PARAM_NAME,M.PARAM_DESC,M.PARAM_STATUS,M.CREATED_BY,M.CREATED_DATE) VALUES ('DP'||IGRS_DEED_PARAM_MASTER_SEQ.NEXTVAL,?,?,'A',?,SYSDATE)";
    /*done by basem*/ public static final String IS_DEEP_PARAM_EXIST="SELECT * FROM IGRS_DEED_PARAM_MASTER WHERE PARAM_NAME=? AND PARAM_STATUS='A'";
    /*done by basem*/public static final String SELECT_ALL_DEEDPARAM_MASTER = "SELECT * FROM IGRS_DEED_PARAM_MASTER where PARAM_STATUS='A' order by PARAM_ID";
    /*done by basem*/public static final String UPDATE_DEEDPARAM_MASTER = "UPDATE IGRS_DEED_PARAM_MASTER M SET M.PARAM_NAME=?,M.PARAM_DESC=?,M.PARAM_STATUS=?,M.UPDATE_BY=?,M.UPDATE_DATE=SYSDATE WHERE M.PARAM_ID=?";
   /*done by basem*/public static final String DELETE_DEEDPARAM = "UPDATE IGRS_DEED_PARAM_MASTER E SET E.PARAM_STATUS = 'D' WHERE E.PARAM_ID = ?";
   /*done by basem*/public static final String SELECT_DEEDPARAM_BYID = "SELECT * FROM IGRS_DEED_PARAM_MASTER  where PARAM_STATUS='A' and  PARAM_ID = ?";
}
