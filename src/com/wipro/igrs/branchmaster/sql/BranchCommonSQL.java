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
package com.wipro.igrs.branchmaster.sql;
public class BranchCommonSQL {
	//Select Query
	public static final String SELECT_BRANCH_MASTER = "SELECT BRANCH_ID,BRANCH_NAME,BRANCH_ADDRESS,BRANCH_PHONE,BRANCH_EMAIL,CREATED_BY,CREATED_DATE,UPDATE_BY,UPDATE_DATE,STATUS FROM IGRS_BANK_BRANCH_MASTER WHERE STATUS NOT LIKE 'R' AND BANK_ID=?";
	public static final String SELECT_BRANCH_MASTER_ID = "SELECT a.BRANCH_ID,a.BRANCH_NAME,a.BRANCH_ADDRESS,a.BRANCH_PHONE,a.BRANCH_EMAIL,b.BANK_NAME,a.BANK_ID,a.CREATED_BY,a.CREATED_DATE,a.UPDATE_BY,a.UPDATE_DATE,a.STATUS FROM IGRS_BANK_BRANCH_MASTER a, IGRS_BANK_MASTER b WHERE a.BANK_ID = b.BANK_ID AND BRANCH_ID=?";
	public static final String SELECT_BRANCH_MASTER_BY_NAME = "SELECT BRANCH_ID,BRANCH_NAME,BRANCH_ADDRESS,BRANCH_PHONE,BRANCH_EMAIL,CREATED_BY,CREATED_DATE,UPDATE_BY,UPDATE_DATE,STATUS FROM IGRS_BANK_BRANCH_MASTER WHERE BRANCH_NAME LIKE ?";
	//Update Query
	public static final String UPDATE_BRANCH_MASTER = "UPDATE IGRS_BANK_BRANCH_MASTER SET BRANCH_NAME=?,BRANCH_ADDRESS=?,BRANCH_PHONE=?,BRANCH_EMAIL=?,STATUS=?,BANK_ID=?,UPDATE_BY=?,UPDATE_DATE=sysdate WHERE BRANCH_ID=?";
	//Insert Query
	public static final String INSERT_BRANCH_MASTER = "INSERT INTO IGRS_BANK_BRANCH_MASTER(BRANCH_ID,BRANCH_NAME,BRANCH_ADDRESS,BRANCH_PHONE,BRANCH_EMAIL,BANK_ID,CREATED_BY,CREATED_DATE,UPDATE_BY,UPDATE_DATE,STATUS) VALUES('BRANCH'||IGRS_BRANCH_MASTER_SEQ.nextval,?,?,?,?,?,?,sysdate,?,sysdate,'A')";       
	//Delete Query
	public static final String DELETE_BRANCH_MASTER = "UPDATE IGRS_BANK_BRANCH_MASTER SET STATUS='R' WHERE BRANCH_ID=?";
 
}
