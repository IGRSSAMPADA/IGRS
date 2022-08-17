package com.wipro.igrs.suspensionenquiry.sql;

public class SuspensionEnquirySQL {
	
	private static final String SELECTED_FIELDS = "SELECT FIRST_NAME ||' '|| MIDDLE_NAME ||' ' ||LAST_NAME,"+
	"DESIG_NAME,OFFICE_NAME,COMPLAINT_NO,DATE_OF_ISSUANCE,COMPLAINT_NO,DATE_OF_ISSUANCE,"+
	"ISSUED_AUTHORITY,EMP_DESIGNATION_ID FROM IGRS_EMP_COMPLAINT_DETAILS join IGRS_EMP_MASTER on IGRS_EMP_COMPLAINT_DETAILS.COMPLAINEE_USER_ID = IGRS_EMP_MASTER.EMP_ID join IGRS_EMP_COMPLAINT_ACTION_DTLS on IGRS_EMP_COMPLAINT_DETAILS.COMPLAINT_NO = IGRS_EMP_COMPLAINT_ACTION_DTLS.COMPLAINT_NO  join IGRS_EMP_OFFICIAL_DETAILS on IGRS_EMP_MASTER.EMP_ID = IGRS_EMP_OFFICIAL_DETAILS.EMP_ID join IGRS_EMP_DESIGNATION_MASTER on "+
	"IGRS_EMP_OFFICIAL_DETAILS.EMP_DESIGNATION_ID = IGRS_EMP_DESIGNATION_MASTER.DESIG_ID join IGRS_EMP_OFFICE_MAPPING on "+
	"IGRS_EMP_MASTER.EMP_ID = IGRS_EMP_OFFICE_MAPPING.EMP_ID join IGRS_OFFICE_MASTER on IGRS_EMP_OFFICE_MAPPING.OFFICE_ID = IGRS_OFFICE_MASTER.OFFICE_ID join IGRS_OFFICE_TYPE_MASTER on IGRS_OFFICE_MASTER.OFFICE_TYPE_ID = IGRS_OFFICE_TYPE_MASTER.OFFICE_TYPE_ID "+
	"WHERE ";
	
	
	public static final String SUSPENSION_EXACT_DATE_ONLY = SELECTED_FIELDS + "IGRS_EMP_COMPLAINT_ACTION_DTLS.DATE_OF_ISSUANCE = ?"; 
	
	public static final String SUSPENSION_RANGE_DATE_ONLY = SELECTED_FIELDS + " IGRS_EMP_COMPLAINT_ACTION_DTLS.DATE_OF_ISSUANCE BETWEEN ? AND ? ";
	
	public static final String REVOCATION_EXACT_DATE_ONLY = SELECTED_FIELDS + " IGRS_EMP_COMPLAINT_ACTION_DTLS.DATE_OF_ISSUANCE = ? ";
	
	
	public static final String REVOCATION_RANGE_DATE_ONLY = SELECTED_FIELDS + " IGRS_EMP_COMPLAINT_ACTION_DTLS.DATE_OF_ISSUANCE BETWEEN ? AND ? ";
	
	
	public static final String LOCATION_ONLY = SELECTED_FIELDS + " IGRS_OFFICE_TYPE_MASTER.OFFICE_TYPE_ID = ? ";
	
	public static final String BY_OFFICE_ONLY = SELECTED_FIELDS + " IGRS_EMP_OFFICE_MAPPING.OFFICE_ID = ? ";
	
	public static final String SUSPENSION_REVOCATION_EXACT = SELECTED_FIELDS + " IGRS_EMP_COMPLAINT_ACTION_DTLS.DATE_OF_ISSUANCE = ? and IGRS_EMP_COMPLAINT_ACTION_DTLS.DATE_OF_ISSUANCE = ? ";
	
	public static final String SUSPENSION_REVOCATION_RANGE = SELECTED_FIELDS + " IGRS_EMP_COMPLAINT_ACTION_DTLS.DATE_OF_ISSUANCE BETWEEN ? AND ?  and IGRS_EMP_COMPLAINT_ACTION_DTLS.DATE_OF_ISSUANCE BETWEEN ? AND ? ";
	
	public static final String SUSPENSION_REVOCATION_EXACT_LOCATION  = SELECTED_FIELDS + "  IGRS_OFFICE_TYPE_MASTER.OFFICE_TYPE_ID = ? and IGRS_EMP_COMPLAINT_ACTION_DTLS.DATE_OF_ISSUANCE = ? and IGRS_EMP_COMPLAINT_ACTION_DTLS.DATE_OF_ISSUANCE = ? ";
	
	public static final String SUSPENSION_REVOCATION_RANGE_LOCATION  = SELECTED_FIELDS + " IGRS_OFFICE_TYPE_MASTER.OFFICE_TYPE_ID = ? and IGRS_EMP_COMPLAINT_ACTION_DTLS.DATE_OF_ISSUANCE BETWEEN ? AND ? and IGRS_EMP_COMPLAINT_ACTION_DTLS.DATE_OF_ISSUANCE BETWEEN ? AND ? ";
	
	public static final String SUSPENSION_REVOCATION_EXACT_OFFICE = SELECTED_FIELDS + " IGRS_EMP_OFFICE_MAPPING.OFFICE_ID = ? and IGRS_EMP_COMPLAINT_ACTION_DTLS.DATE_OF_ISSUANCE = ? and IGRS_EMP_COMPLAINT_ACTION_DTLS.DATE_OF_ISSUANCE = ? ";
	
	public static final String SUSPENSION_REVOCATION_RANGE_OFFICE = SELECTED_FIELDS + "IGRS_EMP_OFFICE_MAPPING.OFFICE_ID = ? and IGRS_EMP_COMPLAINT_ACTION_DTLS.DATE_OF_ISSUANCE BETWEEN ? AND ? and IGRS_EMP_COMPLAINT_ACTION_DTLS.DATE_OF_ISSUANCE BETWEEN ? AND ?";
	
	public static final String SELECT_OFFICES_BY_TYPE = "SELECT OFFICE_ID,OFFICE_NAME FROM IGRS_OFFICE_MASTER WHERE OFFICE_TYPE_ID = ?";
	
	public static final String SUSPENSION_RANGE_LOCATION = SELECTED_FIELDS+" IGRS_OFFICE_TYPE_MASTER.OFFICE_TYPE_ID = ? and IGRS_EMP_COMPLAINT_ACTION_DTLS.DATE_OF_ISSUANCE BETWEEN ? AND ?";
	
	
	public static final String SUSPENSION_RANGE_OFFICE = SELECTED_FIELDS+" IGRS_EMP_OFFICE_MAPPING.OFFICE_ID = ? and IGRS_EMP_COMPLAINT_ACTION_DTLS.DATE_OF_ISSUANCE BETWEEN ? AND ?";
	
	public static final String REVOCATION_RANGE_OFFICE = SELECTED_FIELDS+" IGRS_EMP_OFFICE_MAPPING.OFFICE_ID = ? and IGRS_EMP_COMPLAINT_ACTION_DTLS.DATE_OF_ISSUANCE BETWEEN ? AND ?";
	
	public static final String REVOCATION_RANGE_LOCATION = SELECTED_FIELDS+" IGRS_OFFICE_TYPE_MASTER.OFFICE_TYPE_ID = ? and IGRS_EMP_COMPLAINT_ACTION_DTLS.DATE_OF_ISSUANCE BETWEEN ? AND ?";
	
	public static final String SUSPENSION_EXACT_LOCATION = SELECTED_FIELDS+" IGRS_OFFICE_TYPE_MASTER.OFFICE_TYPE_ID = ? and IGRS_EMP_COMPLAINT_ACTION_DTLS.DATE_OF_ISSUANCE = ?";
	
	public static final String SUSPENSION_EXACT_OFFICE = SELECTED_FIELDS+" IGRS_EMP_OFFICE_MAPPING.OFFICE_ID = ? and IGRS_EMP_COMPLAINT_ACTION_DTLS.DATE_OF_ISSUANCE = ?";
	
	public static final String REVOCATION_EXACT_LOCATION = SELECTED_FIELDS+" IGRS_OFFICE_TYPE_MASTER.OFFICE_TYPE_ID = ? and IGRS_EMP_COMPLAINT_ACTION_DTLS.DATE_OF_ISSUANCE = ? ";
	
	public static final String REVOCATION_EXACT_OFFICE = SELECTED_FIELDS+" IGRS_EMP_OFFICE_MAPPING.OFFICE_ID = ? and IGRS_EMP_COMPLAINT_ACTION_DTLS.DATE_OF_ISSUANCE = ?";
}
