package com.wipro.igrs.regcompletion.sql;

public class CommonSQL {
	
	
	public static final String IGRS_COMPLETION_CHECK_FLAG = 
							" call IGRS_REG_COMP_CHECK_PROC(?,?,?,?,?,?,?)";
    public static final String PINCREATE =
    	"INSERT INTO IGRS_REG_PIN_DETAILS(REGISTRATION_ID, "
    	+" PIN_NUMBER,ACTIVE_FLAG,USER_ID,DELIVERY_STATUS) "
    	+ " VALUES(?,?,?,?,'N')";

}
