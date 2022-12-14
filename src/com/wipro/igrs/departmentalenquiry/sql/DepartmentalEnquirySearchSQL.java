package com.wipro.igrs.departmentalenquiry.sql;

public class DepartmentalEnquirySearchSQL {

	private static final String SELECTED_FIELDS = "SELECT FIRST_NAME ||' '|| MIDDLE_NAME ||' ' ||LAST_NAME,"+
	"DESIG_NAME,IGRS_OFFICE_MASTER.OFFICE_NAME,IGRS_EMP_COMPLAINT_DETAILS.CREATED_DATE,IGRS_EMP_COMPLAINT_DETAILS.COMPLAINT_NO,CompetentName.FIRST_NAME ||' '|| CompetentName.MIDDLE_NAME,CompetentDesignation.ROLE_NAME,DATE_OF_ISSUANCE,COMPLAINT_NO,"+
	"OfficerName.FIRST_NAME ||' '|| OfficerName.MIDDLE_NAME,OfficerDesignation.ROLE_NAME FROM IGRS_EMP_COMPLAINT_DETAILS join IGRS_EMP_MASTER on IGRS_EMP_COMPLAINT_DETAILS.COMPLAINEE_USER_ID = IGRS_EMP_MASTER.EMP_ID "+
	"join IGRS_EMP_OFFICE_MAPPING on IGRS_EMP_MASTER.EMP_ID = IGRS_EMP_OFFICE_MAPPING.EMP_ID join IGRS_OFFICE_MASTER on IGRS_EMP_OFFICE_MAPPING.OFFICE_ID = IGRS_OFFICE_MASTER.OFFICE_ID join IGRS_OFFICE_TYPE_MASTER on IGRS_OFFICE_MASTER.OFFICE_TYPE_ID = IGRS_OFFICE_TYPE_MASTER.OFFICE_TYPE_ID "+
	"join IGRS_EMP_OFFICIAL_DETAILS on IGRS_EMP_MASTER.EMP_ID = IGRS_EMP_OFFICIAL_DETAILS.EMP_ID join IGRS_EMP_DESIGNATION_MASTER on IGRS_EMP_OFFICIAL_DETAILS.EMP_DESIGNATION_ID = IGRS_EMP_DESIGNATION_MASTER.DESIG_ID "+
	"join IGRS_EMP_COMPLAINT_ACTION_DTLS on IGRS_EMP_COMPLAINT_DETAILS.COMPLAINT_NO = IGRS_EMP_COMPLAINT_ACTION_DTLS.COMPLAINT_NO "+
	"join IGRS_EMP_MASTER CompetentName on IGRS_EMP_COMPLAINT_ACTION_DTLS.ISSUED_AUTHORITY = CompetentName.EMP_ID "+
	"join IGRS_EMP_OFFICIAL_DETAILS CompetentOfficial on CompetentOfficial.EMP_ID = CompetentName.EMP_ID join IGRS_ROLE_MASTER CompetentDesignation on IGRS_EMP_OFFICIAL_DETAILS.EMP_DESIGNATION_ID = CompetentDesignation.ROLE_ID "+
	"join IGRS_EMP_MASTER OfficerName on IGRS_EMP_COMPLAINT_ACTION_DTLS.ISSUED_AUTHORITY = OfficerName.EMP_ID "+
	"join IGRS_EMP_OFFICIAL_DETAILS OfficerOfficial on OfficerOfficial.EMP_ID = OfficerName.EMP_ID   join IGRS_ROLE_MASTER OfficerDesignation on OfficerOfficial.EMP_DESIGNATION_ID = OfficerDesignation.ROLE_ID "+
	"join IGRS_EMP_COMPLAINT_STATUS_DTLS on IGRS_EMP_COMPLAINT_DETAILS.COMPLAINT_NO = IGRS_EMP_COMPLAINT_STATUS_DTLS.COMPLAINT_NO "+
	"WHERE ";
	
	public static final String BY_EXACT_DATE_ONLY = SELECTED_FIELDS+"IGRS_EMP_COMPLAINT_ACTION_DTLS.DATE_OF_ISSUANCE = ?";
	public static final String BY_STATUS_ONLY = SELECTED_FIELDS+"IGRS_EMP_COMPLAINT_STATUS_DTLS.COMPLAINT_NO = ?";
	public static final String BY_LOCATION_ONLY = SELECTED_FIELDS+"IGRS_OFFICE_TYPE_MASTER.OFFICE_TYPE_ID = ?";
	public static final String BY_OFFICE_ONLY = SELECTED_FIELDS+"IGRS_EMP_OFFICE_MAPPING.OFFICE_ID = ?";
	public static final String BY_RANGE_DATE_ONLY = SELECTED_FIELDS+"IGRS_EMP_COMPLAINT_ACTION_DTLS.DATE_OF_ISSUANCE BETWEEN ? AND ?";
	
	public static final String BY_EXACT_DATE_STATUS = SELECTED_FIELDS+"IGRS_EMP_COMPLAINT_ACTION_DTLS.DATE_OF_ISSUANCE = ? and IGRS_EMP_COMPLAINT_STATUS_DTLS.COMPLAINT_NO = ?";
	public static final String BY_EXACT_DATE_LOCATION = SELECTED_FIELDS+"IGRS_EMP_COMPLAINT_ACTION_DTLS.DATE_OF_ISSUANCE = ? and IGRS_OFFICE_TYPE_MASTER.OFFICE_TYPE_ID = ?";
	public static final String BY_EXACT_DATE_OFFICE = SELECTED_FIELDS+"IGRS_EMP_COMPLAINT_ACTION_DTLS.DATE_OF_ISSUANCE = ? and IGRS_EMP_OFFICE_MAPPING.OFFICE_ID = ?";
	
	public static final String BY_RANGE_DATE_STATUS = SELECTED_FIELDS+"IGRS_EMP_COMPLAINT_ACTION_DTLS.DATE_OF_ISSUANCE BETWEEN ? AND ? and IGRS_EMP_COMPLAINT_STATUS_DTLS.COMPLAINT_NO = ?";
	public static final String BY_RANGE_DATE_LOCATION = SELECTED_FIELDS+"IGRS_EMP_COMPLAINT_ACTION_DTLS.DATE_OF_ISSUANCE BETWEEN ? AND ? and IGRS_OFFICE_TYPE_MASTER.OFFICE_TYPE_ID = ?";
	public static final String BY_RANGE_DATE_OFFICE = SELECTED_FIELDS+"IGRS_EMP_COMPLAINT_ACTION_DTLS.DATE_OF_ISSUANCE BETWEEN ? AND ? and IGRS_EMP_OFFICE_MAPPING.OFFICE_ID = ?";
	
	public static final String BY_EXACT_DATE_STATUS_LOCATION = SELECTED_FIELDS+"IGRS_EMP_COMPLAINT_ACTION_DTLS.DATE_OF_ISSUANCE = ? and IGRS_EMP_COMPLAINT_STATUS_DTLS.COMPLAINT_NO = ? and IGRS_OFFICE_TYPE_MASTER.OFFICE_TYPE_ID = ?";
	public static final String BY_RANGE_DATE_STATUS_LOCATION = SELECTED_FIELDS+"IGRS_EMP_COMPLAINT_ACTION_DTLS.DATE_OF_ISSUANCE BETWEEN ? AND ? and IGRS_EMP_COMPLAINT_STATUS_DTLS.COMPLAINT_NO = ? and IGRS_OFFICE_TYPE_MASTER.OFFICE_TYPE_ID = ?";
	
	public static final String BY_EXACT_DATE_STATUS_OFFICE = SELECTED_FIELDS+"IGRS_EMP_COMPLAINT_ACTION_DTLS.DATE_OF_ISSUANCE = ? and IGRS_EMP_COMPLAINT_STATUS_DTLS.COMPLAINT_NO = ? and IGRS_EMP_OFFICE_MAPPING.OFFICE_ID = ?";
	public static final String BY_RANGE_DATE_STATUS_OFFICE = SELECTED_FIELDS+"IGRS_EMP_COMPLAINT_ACTION_DTLS.DATE_OF_ISSUANCE BETWEEN ? AND ? and IGRS_EMP_COMPLAINT_STATUS_DTLS.COMPLAINT_NO = ? and IGRS_EMP_OFFICE_MAPPING.OFFICE_ID = ?";
	
	public static final String BY_STATUS_LOCATION = SELECTED_FIELDS+"IGRS_EMP_COMPLAINT_STATUS_DTLS.COMPLAINT_NO = ? and IGRS_OFFICE_TYPE_MASTER.OFFICE_TYPE_ID = ?";
	public static final String BY_STATUS_OFFICE = SELECTED_FIELDS+"IGRS_EMP_COMPLAINT_STATUS_DTLS.COMPLAINT_NO = ? and IGRS_EMP_OFFICE_MAPPING.OFFICE_ID = ?";
	
	public static final String GET_ALL_COMPLAINT_STATUS = "SELECT COMPLAINT_NO, COMPLAINT_STATUS " +
			"FROM IGRS_EMP_COMPLAINT_STATUS_DTLS"; 
	
}
