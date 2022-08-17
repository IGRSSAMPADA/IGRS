/**
 * 
 */
package com.wipro.igrs.officearea.sql;

/**
 * @author HMOHAM
 *
 */
public interface OfficeAreaMappingSQL {

	String SELECT_OFFICES = "SELECT OFFICE_ID, OFFICE_NAME" +
			" FROM IGRS_OFFICE_MASTER " +
			" WHERE OFFICE_STATUS <> 'R'";
	
	String SELECT_DISTRICTS = "SELECT DISTRICT_ID, DISTRICT_NAME " +
			" FROM IGRS_DISTRICT_MASTER " +
			" WHERE DISTRICT_STATUS <> 'R'";
	
	String SELECT_TEHSILS = "SELECT TEHSIL_ID, TEHSIL_NAME " +
			" FROM IGRS_TEHSIL_MASTER " +
			" WHERE TEHSIL_STATUS <> 'R' and TEHSIL_STATUS='A' " +
			" AND DISTRICT_ID = ?";
	
	String SELECT_WARD_PATWARIS = "SELECT WARD_PATWARI_ID, WARD_PATWARI_NAME " +
			" FROM IGRS_WARD_PATWARI_MASTER " +
			" WHERE WARD_PATWARI_ID <> 'R' AND WARD_PATWARI_STATUS = 'A' " +
			" AND TEHSIL_ID = ?";
	
	String SELECT_MOHALLA_VILLIGES = "SELECT MOHALLA_VILLAGE_ID, MOHALLA_VILLAGE_NAME " +
			" FROM IGRS_MOHALLA_VILLAGE_MASTER " +
			" WHERE MOHALLA_VILLAGE_STATUS <> 'R' " +
			" AND WARD_PATWARI_ID = ?";
	
	String SELECT_OFFICE_MAPPING_BY_ID = "SELECT mapping.OFFICE_ID, mapping.DISTRICT_ID," +
			" mapping.TEHSIL_ID, mapping.WARD_PATWARI_ID, mapping.MOHALLA_VILLAGE_ID, " +
			" OFFICE_NAME, DISTRICT_NAME, TEHSIL_NAME, WARD_PATWARI_NAME, MOHALLA_VILLAGE_NAME" +
			" FROM IGRS_OFFICE_AREA_JURISDICTION mapping, " +
			" IGRS_OFFICE_MASTER office, " + 
		    " IGRS_TEHSIL_MASTER tehsil, " +
		    " IGRS_DISTRICT_MASTER district, "+
		    " IGRS_WARD_PATWARI_MASTER wardPatwari, " +
		    " IGRS_MOHALLA_VILLAGE_MASTER mohalla " +
			" WHERE office.OFFICE_ID = ? " +
			" AND mapping.office_id = office.office_id" +
			" AND mapping.district_id = district.district_Id " +
			" AND mapping.tehsil_id = tehsil.tehsil_id" +
			" AND mapping.ward_patwari_id = wardPatwari.ward_patwari_id" +
			" AND mapping.mohalla_village_id = mohalla.mohalla_village_id AND WARD_PATWARI_STATUS = 'A' AND TEHSIL_STATUS='A'";
	
	String SELECT_ALL_OFFICE_MAPPING = "SELECT mapping.OFFICE_ID, mapping.DISTRICT_ID," +
			" mapping.TEHSIL_ID, mapping.WARD_PATWARI_ID, mapping.MOHALLA_VILLAGE_ID, " +
			" OFFICE_NAME, DISTRICT_NAME, TEHSIL_NAME, WARD_PATWARI_NAME, MOHALLA_VILLAGE_NAME" +
			" FROM IGRS_OFFICE_AREA_JURISDICTION mapping, " +
			" IGRS_OFFICE_MASTER office, " + 
		    " IGRS_TEHSIL_MASTER tehsil, " +
		    " IGRS_DISTRICT_MASTER district, "+
		    " IGRS_WARD_PATWARI_MASTER wardPatwari, " +
		    " IGRS_MOHALLA_VILLAGE_MASTER mohalla " +
			" WHERE mapping.office_id = office.office_id" +
			" AND mapping.district_id = district.district_Id " +
			" AND mapping.tehsil_id = tehsil.tehsil_id " +
			" AND mapping.ward_patwari_id = wardPatwari.ward_patwari_id" +
			" AND mapping.mohalla_village_id = mohalla.mohalla_village_id AND WARD_PATWARI_STATUS = 'A' AND TEHSIL_STATUS='A'";
	
	String INSERT_OFFICE_MAPPING = "INSERT INTO IGRS_OFFICE_AREA_JURISDICTION" +
			" ( OFFICE_ID, DISTRICT_ID," +
			" TEHSIL_ID, WARD_PATWARI_ID, MOHALLA_VILLAGE_ID)" +
			" VALUES (?, ?, ?, ?, ?)";
	
	String UPDATE_OFFICE_MAPPING = "UPDATE IGRS_OFFICE_AREA_JURISDICTION " +
			" SET DISTRICT_ID = ?, TEHSIL_ID = ?, " +
			" WARD_PATWARI_ID = ?, MOHALLA_VILLAGE_ID = ? " +
			" WHERE OFFICE_ID = ?";
	
	String DELETE_OFFICE_MAPPINGS = "DELETE FROM IGRS_OFFICE_AREA_JURISDICTION " +
			" WHERE OFFICE_ID = ? ";
	
	String SELECT_BY_AREA_MAPPING = "SELECT mapping.OFFICE_ID, mapping.DISTRICT_ID," +
			" mapping.TEHSIL_ID, mapping.WARD_PATWARI_ID, mapping.MOHALLA_VILLAGE_ID, " +
			" OFFICE_NAME, DISTRICT_NAME, TEHSIL_NAME, WARD_PATWARI_NAME, MOHALLA_VILLAGE_NAME" +
			" FROM IGRS_OFFICE_AREA_JURISDICTION mapping, " +
			" IGRS_OFFICE_MASTER office, " + 
		    " IGRS_TEHSIL_MASTER tehsil, " +
		    " IGRS_DISTRICT_MASTER district, "+
		    " IGRS_WARD_PATWARI_MASTER wardPatwari, " +
		    " IGRS_MOHALLA_VILLAGE_MASTER mohalla " +
			" WHERE mapping.district_id = ? " +
			" AND mapping.tehsil_id = ? " +
			" AND mapping.ward_patwari_id = ? " +
			" AND mapping.mohalla_village_id = ? " +
			" AND mapping.office_id = office.office_id" +
			" AND mapping.district_id = district.district_Id " +
			" AND mapping.tehsil_id = tehsil.tehsil_id " +
			" AND mapping.ward_patwari_id = wardPatwari.ward_patwari_id" +
			" AND mapping.mohalla_village_id = mohalla.mohalla_village_id AND WARD_PATWARI_STATUS = 'A' AND TEHSIL_STATUS='A'";
}
