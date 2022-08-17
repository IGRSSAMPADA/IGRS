package com.wipro.igrs.expform76b.sql;

public class ExpForm76BCommonSQL {
	
	
	public static final String GET_NEXT_VALUE_OF_SEQ="select IGRS_EXP_MPTC_76B_SEQ.nextval from dual"; 
	public static final String GET_ALL_DISTRICTS="select district_name,district_id from igrs_district_master";
	public static final String GET_ALL_FISCAL_YEARS="select fiscal_year,fiscal_year_id from igrs_fiscal_year";
	public static final String GET_ALL_FISCAL_MONTHS="select fiscal_month_name,fiscal_month_id from igrs_fiscal_month_master";
	public static final String GET_ALL_ACCOUNT_HEADS="select detailed_head_name_e,detailed_head_id from igrs_exp_detailed_head_master";
	public static final String GET_ALL_MAJOR_HEADS="select major_head_name_e,major_head_id from igrs_exp_major_head_master";
	public static final String GET_ALL_SUB_MAJOR_HEADS="select sub_major_head_name_e,sub_major_head_id from igrs_exp_sub_major_head_master";
	public static final String GET_ALL_MINOR_HEADS_RELATED_TO_SUB_MAJOR="select minor_head_name_e,minor_head_id from igrs_exp_minor_head_master where sub_major_head_id=?";
	public static final String IS_EMPLOYEE_HAS_STATUSE="select EMP_OFFICIAL_STATUS from IGRS_EMP_OFFICIAL_DETAILS where EMP_ID=?";
	public static final String GET_JOP_AND_PAY="select EMP_OFFICIAL_STATUS,component_value from IGRS_EMP_OFFICIAL_DETAILS,igrs_emp_salary_basic_dtls,igrs_emp_master where IGRS_EMP_OFFICIAL_DETAILS.EMP_ID=igrs_emp_master.EMP_ID and  igrs_emp_salary_basic_dtls.EMP_ID=igrs_emp_master.EMP_ID and igrs_emp_master.EMP_ID=?";
	public static final String SAVE_NEW_RECORD="insert into IGRS_EXP_MPTC_76B_DTLS(EXP_BILL_NO,DISTRICT_ID,FISCAL_YEAR_ID,FISCAL_MONTH_ID,ACCOUNT_HEAD_ID,MAJOR_HEAD_ID,SUB_MAJOR_HEAD_ID,MINOR_HEAD_ID,GRANT_NO,EMP_ID,AMOUNT_REQUIRED,SURETY_TAKEN,CREATED_BY,CREATED_DATE) values(?,?,?,?,?,?,?,?,?,?,?,?,?,sysdate)";
	
}
