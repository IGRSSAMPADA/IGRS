/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Egypt.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	BankCommonSQL.java
 * Author	:	Walaa Elrahman
 * Date		: 10/08/2008
 */
package com.wipro.igrs.bankmaster.sql;
public class BankCommonSQL {
	//Select Query
	public static final String SELECT_BANK_MASTER = "SELECT BANK_ID,BANK_NAME,BANK_ADDRESS,BANK_PHONE_NUMBER,CREATED_BY,CREATED_DATE,UPDATE_BY,UPDATE_DATE,STATUS FROM IGRS_BANK_MASTER WHERE STATUS NOT LIKE 'R'";
	public static final String SELECT_BANK_MASTER_ID = "SELECT BANK_ID,BANK_NAME,BANK_ADDRESS,BANK_PHONE_NUMBER,STATUS FROM IGRS_BANK_MASTER WHERE BANK_ID=?";
	public static final String SELECT_BANK_MASTER_BY_NAME = "SELECT BANK_ID,BANK_NAME,BANK_ADDRESS,BANK_PHONE_NUMBER FROM IGRS_BANK_MASTER WHERE BANK_NAME LIKE ?";
	//Update Query
	public static final String UPDATE_BANK_MASTER = "UPDATE IGRS_BANK_MASTER SET BANK_NAME=?,BANK_ADDRESS=?,BANK_PHONE_NUMBER=?,STATUS=?,UPDATE_BY=?,UPDATE_DATE=sysdate WHERE BANK_ID=?";
	//Insert Query
	public static final String INSERT_BANK_MASTER = "INSERT INTO IGRS_BANK_MASTER(BANK_ID,BANK_NAME,BANK_ADDRESS,BANK_PHONE_NUMBER,CREATED_BY,CREATED_DATE,UPDATE_BY,UPDATE_DATE,STATUS) VALUES('BANK'||IGRS_BANK_MASTER_SEQ.nextval,?,?,?,?,sysdate,?,sysdate,'A')";       
	//Delete Query
	public static final String DELETE_BANK_MASTER = "UPDATE IGRS_BANK_MASTER SET STATUS='R' WHERE BANK_ID=?";

}
