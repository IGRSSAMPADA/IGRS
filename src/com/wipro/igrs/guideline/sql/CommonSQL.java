
/* Copyright (c) 2007-08 WIPRO INFOTECH. All Rights Reserved.
 * 
 *  This software is the confidential and proprietary information of WIPRO
 *  ("Confidential Information"). You shall not
 *  disclose such Confidential Information and shall use it only in
 *  accordance with the terms of the license agreement you entered into
 *  with WIPRO.
 *  
 * WIPRO MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE 
 * SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF 
 * MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, OR NON-INFRINGEMENT. WIPRO SHALL NOT BE 
 * LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF 
 * USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS 
 * DERIVATIVES.
 */ 

/* 
 * FILE NAME: GuideLine Process - CommonSQL.java
 * @author NIHAR R MISHRA
 * @version 1.0 
 * INITIAL CODING: 4th MARCH 2008
 * MODIFIED ON:    6th MAY 2008 
 * MODIFIED ON:	   None
 * @DESCRIPTION:   THIS CLASS REPRESENTS THE COMMON SQL CLASS FOR GUIDELINE PREPARATION.
 */


package com.wipro.igrs.guideline.sql;

public class CommonSQL {
	
	/**
	 * Financial YEAR for GUIDELINE CREATION
	 */
	
	public static final String FINANCIAL = "SELECT FISCAL_YEAR FROM IGRS_FISCAL_YEAR WHERE GUIDELINE_YEAR_STATUS='A' ORDER BY FISCAL_YEAR";

	
	/**
	 * FINANCIAL YEAR FOR GUIDELINE VIEW
	 */
	
	public static final String FINANCIAL_VIEW = "SELECT FISCAL_YEAR FROM IGRS_FISCAL_YEAR WHERE FISCAL_YEAR_STATUS = 'A'";
	
	/**
	 * FINANCIAL YEAR FOR GUIDELINE VIEW FOR CITIZEN
	 */
	public static final String ACTIVE_FINANCIAL_VIEW = "SELECT FISCAL_YEAR FROM IGRS_FISCAL_YEAR WHERE GUIDELINE_YEAR_STATUS='A' ORDER BY FISCAL_YEAR ";
	
	/**
	 * START AND END DATE FOR FINANCIAL YEAR
	 */
	
	public static final String START_END ="SELECT START_DATE,END_DATE FROM IGRS_FISCAL_YEAR WHERE FISCAL_YEAR= ?";
	
	
	/**
	 * DISTRICT
	 */
	public static final String DISTRICT = "SELECT DISTINCT DISTRICT_ID,DISTRICT_NAME,H_DISTRICT_NAME FROM"
		+ " IGRS_DISTRICT_MASTER WHERE DISTRICT_STATUS = 'A' AND STATE_ID = '1' ORDER BY DISTRICT_NAME ";
	
	public static final String DISTRICT_HI = "SELECT DISTINCT DISTRICT_ID,DISTRICT_NAME,H_DISTRICT_NAME FROM"
		+ " IGRS_DISTRICT_MASTER WHERE DISTRICT_STATUS = 'A' AND STATE_ID = '1' ORDER BY H_DISTRICT_NAME ";

	/**
	 * TEHSIL
	 */
	public static final String TEHSIL = "SELECT TEHSIL_ID, TEHSIL_NAME,H_TEHSIL_NAME FROM"
		+ " IGRS_TEHSIL_MASTER WHERE DISTRICT_ID = ? and tehsil_status = 'A'";

	/**
	 * WARD
	 */
	public static final String WARD = "SELECT WARD_PATWARI_ID, WARD_PATWARI_NAME FROM"
		+ " IGRS_WARD_PATWARI_MASTER WHERE TEHSIL_ID = ? AND WARD_OR_PATWARI = 1 and ward_patwari_status= 'A'";

	/**
	 * PATWARI
	 */
	public static final String PATWARI = "SELECT WARD_PATWARI_ID, WARD_PATWARI_NAME FROM"
		+ " IGRS_WARD_PATWARI_MASTER WHERE TEHSIL_ID = ? AND WARD_OR_PATWARI = 2 and ward_patwari_status = 'A'";



	/**
	 * MOHALLA_VIEW
	 */
	/*public static final String MOHALLA_VIEW = "select property_type_name, property_type_l1_name, " +
			"property_type_l2_name, guideline_value from igrs_guideline_data_temp where financial year = ?";

	"SELECT"+" "+ 
	"igrs_mohalla_village_master.mohalla_village_id ,"+
	"igrs_mohalla_village_master.mohalla_village_name ,"+
	"igrs_guideline_data_temp.mohalla_village_id"+" "+ 
	"FROM igrs_mohalla_village_master LEFT OUTER JOIN"+" "+
	"igrs_guideline_data_temp ON(igrs_mohalla_village_master.mohalla_village_id=igrs_guideline_data_temp.mohalla_village_id)"+
	"WHERE igrs_guideline_data_temp.ward_patwari_id = ?"; 
	 */




	/**
	 * This query fetches the Mohalla/ Village List by taking the parameters as
	 * ward id, duration from date and duration to date.
	 * Applicable to both approval and creation process.
	 * 
	 * MOHALLA
	 */
	/*public static final String MOHALLA = "SELECT"+" "+ 
	"to_char(igrs_guideline_data_temp.base_period_from),"+
	"to_char(igrs_guideline_data_temp.base_period_to),"+
	"igrs_mohalla_village_master.mohalla_village_id ,"+
	"igrs_mohalla_village_master.mohalla_village_name ,"+
	"igrs_guideline_data_temp.STATUS_FLAG ,"+
	"igrs_guideline_data_temp.mohalla_village_id"+" "+ 
	"FROM igrs_mohalla_village_master LEFT OUTER JOIN"+" "+
	"igrs_guideline_data_temp ON(igrs_mohalla_village_master.mohalla_village_id=igrs_guideline_data_temp.mohalla_village_id)"+" "+
	"WHERE igrs_guideline_data_temp.ward_patwari_id = ? AND TRUNC(igrs_guideline_data_temp.CREATED_DATE) BETWEEN TO_CHAR(TO_DATE(?,'dd/mm/yy'))"+" "+ 
	"AND TO_CHAR(TO_DATE(? ,'dd/mm/yy'))"; 
	 */

	/*public static final String MOHALLA_1 = "SELECT distinct to_char(igrs_guideline_data_temp.base_period_from)" + '"'+"Base Period From"+'"'+ ","+
	"to_char(igrs_guideline_data_temp.base_period_to)"+'"'+"BasePeriodTo"+'"'+ ","+
	"igrs_mohalla_village_master.mohalla_village_id , igrs_mohalla_village_master.mohalla_village_name ,"+
	"igrs_guideline_data_temp.STATUS_FLAG, igrs_guideline_data_temp.mohalla_village_id FROM"+" "+
	"igrs_mohalla_village_master"+" "+ 
	"LEFT OUTER JOIN igrs_guideline_data_temp"+" "+
	"ON(igrs_mohalla_village_master.mohalla_village_id=igrs_guideline_data_temp.mohalla_village_id)"+ 
	"WHERE igrs_guideline_data_temp.ward_patwari_id = ?"+" "+ 
	"AND TRUNC(igrs_guideline_data_temp.CREATED_DATE)"+ 
	"BETWEEN TO_CHAR(TO_DATE(?, 'dd/mm/yy')) "+" "+
	"AND TO_CHAR(TO_DATE(?, 'dd/mm/yy'))"+" "+
	"UNION SELECT null,null, igrs_mohalla_village_master.mohalla_village_id ,"+
	"igrs_mohalla_village_master.mohalla_village_name ,"+
	"null, null FROM igrs_mohalla_village_master"+" "+ 
	"WHERE igrs_mohalla_village_master.mohalla_village_id not in"+ 
	"( select mohalla_village_id from igrs_guideline_data_temp"+ 
	" "+"where status_flag='P')"; */

	public static final String MOHALLA = "SELECT distinct to_char(igrs_guideline_data_temp.base_period_from)"+'"'+"Base Period From"+'"'+","+
	"to_char(igrs_guideline_data_temp.base_period_to)"+'"'+"BasePeriodTo"+'"'+","+"igrs_mohalla_village_master.mohalla_village_id , " +
			"igrs_mohalla_village_master.mohalla_village_name ,igrs_guideline_data_temp.STATUS_FLAG, " +
			"igrs_guideline_data_temp.mohalla_village_id FROM igrs_mohalla_village_master," +
			"igrs_guideline_data_temp where igrs_guideline_data_temp.mohalla_village_id in " +
			"(select mohalla_village_id from igrs_guideline_data_temp INTERSECT select mohalla_village_id from " +
			"igrs_mohalla_village_master) AND igrs_guideline_data_temp.ward_patwari_id =? AND " +
			"TRUNC(igrs_guideline_data_temp.CREATED_DATE) BETWEEN TO_CHAR(TO_DATE(?, 'dd/mm/yy'))  " +
			"AND TO_CHAR(TO_DATE(?, 'dd/mm/yy'))  AND igrs_guideline_data_temp.status_flag='P'"; 

	/**
	 * This query fetches the Mohalla/ Village List by taking the parameters as
	 * ward id, duration from date and duration to date.
	 * Applicable to both approval and creation process.
	 * 
	 * MOHALLA
	 */
	/*public static final String MOHALLA_DRAFT_VIEW = "SELECT distinct igrs_property_type_master.property_type_name,"+
	"igrs_prop_type_l1_master.property_type_l1_name,  igrs_prop_type_l2_master.property_type_l2_name,"+
	"igrs_guideline_data_temp.guideline_value,  igrs_guideline_data_temp.guideline_avg_value,"+
	"igrs_guideline_data_temp.pct_increment," +
	"igrs_mohalla_village_master.mohalla_village_id,"+
	"igrs_mohalla_village_master.mohalla_village_name FROM igrs_guideline_data_temp,"+
	"igrs_property_type_master,  igrs_prop_type_l1_master,"+
	"igrs_prop_type_l2_master, igrs_mohalla_village_master  WHERE igrs_guideline_data_temp.mohalla_village_id = ? AND ("+    
	"(igrs_prop_type_l1_master.property_type_l1_id = igrs_prop_type_l2_master.property_type_l1_id )"+" "+
	"AND (igrs_property_type_master.property_type_id = igrs_guideline_data_temp.property_type_id)"+" "+
	"AND (igrs_prop_type_l1_master.property_type_l1_id =  igrs_guideline_data_temp.property_type_l1_id)"+" "+
	"AND (igrs_prop_type_l2_master.property_type_l2_id =  igrs_guideline_data_temp.property_type_l2_id)"+" "+
	"AND (igrs_mohalla_village_master.mohalla_village_id =igrs_guideline_data_temp.mohalla_village_id)"+" "+	
	"AND igrs_guideline_data_temp.FINANCIAL_YEAR = ?)";*/
	//"AND igrs_guideline_data_temp.STATUS_FLAG = 'D'"+
	
	/*public static final String MOHALLA_DRAFT_VIEW = "SELECT distinct igrs_property_type_master.property_type_name," +
			"igrs_prop_type_l1_master.property_type_l1_name, igrs_prop_type_l2_master.property_type_l2_name, " +
			"igrs_guideline_data_temp.guideline_value, igrs_guideline_data_temp.guideline_avg_value,igrs_guideline_data_temp.pct_increment, " +
			"igrs_mohalla_village_master.mohalla_village_id,igrs_mohalla_village_master.mohalla_village_name FROM " +
			"igrs_guideline_data_temp,igrs_property_type_master,  igrs_prop_type_l1_master,igrs_prop_type_l2_master, " +
			"igrs_mohalla_village_master  WHERE igrs_guideline_data_temp.mohalla_village_id = ? AND " +
			"igrs_guideline_data_temp.FINANCIAL_YEAR = ? AND " +
			"igrs_guideline_data_temp.STATUS_FLAG = ? AND "+
			"igrs_guideline_data_temp.mohalla_village_id =igrs_mohalla_village_master.mohalla_village_id AND " +
			"igrs_guideline_data_temp.property_type_id = igrs_property_type_master.property_type_id AND " +
			"igrs_guideline_data_temp.property_type_l1_id =  igrs_prop_type_l1_master.property_type_l1_id (+) AND " +
			"igrs_guideline_data_temp.property_type_l2_id =  igrs_prop_type_l2_master.property_type_l2_id(+)";*/
	
	/*public static final String MOHALLA_DRAFT_VIEW = "SELECT distinct igrs_property_type_master.property_type_name," +
			"igrs_prop_type_l1_master.property_type_l1_name, igrs_prop_type_l2_master.property_type_l2_name," +
			" igrs_guideline_data_temp.guideline_value, igrs_guideline_data_temp.guideline_avg_value," +
			"igrs_guideline_data_temp.pct_increment, igrs_mohalla_village_master.mohalla_village_id," +
			"igrs_mohalla_village_master.mohalla_village_name, igrs_uom_master.uom_name FROM  " +
			"((igrs_guideline_data_temp left outer join igrs_mohalla_village_master on" +
			"(igrs_guideline_data_temp.mohalla_village_id =igrs_mohalla_village_master.mohalla_village_id)) " +
			"left outer join igrs_property_type_master on (igrs_guideline_data_temp.property_type_id = igrs_property_type_master.property_type_id )" +
			"left outer join igrs_prop_type_l1_master on" +
			"( igrs_guideline_data_temp.property_type_l1_id =  igrs_prop_type_l1_master.property_type_l1_id )" +
			" left outer join igrs_prop_type_l2_master on" +
			"( igrs_guideline_data_temp.property_type_l2_id =  igrs_prop_type_l2_master.property_type_l2_id) )" +
			"left outer join igrs_uom_master on" +
			"( igrs_prop_type_l1_master.UOM_ID = igrs_uom_master.uom_id) " +
			"WHERE igrs_guideline_data_temp.mohalla_village_id = ? AND" +
			" igrs_guideline_data_temp.FINANCIAL_YEAR = ? AND " +
			"igrs_guideline_data_temp.STATUS_FLAG = ?";*/
	public static final String MOHALLA_DRAFT_VIEW = "SELECT distinct p.property_type_name,p1.property_type_l1_name, " +
			"p2.property_type_l2_name,c2.guideline_value,m.mohalla_village_id," +
			"m.mohalla_village_name, c1.area_type_id,c2.property_type_id," +
			"c2.property_type_l1_id, c2.property_type_l2_id ,u.uom_name, " +
			"u.uom_id FROM " +
			"IGRS_PROPERTY_TYPE_MASTER p ,IGRS_PROP_TYPE_L1_MASTER p1," +
			"IGRS_PROP_TYPE_L2_MASTER p2,igrs_guideline_child2_temp c2," +
			"igrs_guideline_child1_temp c1,igrs_guideline_parent_temp c," +
			"igrs_mohalla_village_master m, igrs_uom_master u where " +
			"c.guideline_id = (select distinct a.guideline_id from igrs_guideline_parent_temp a," +
			" igrs_guideline_child1_temp b where a.district_id = ? " +
			"and a.financial_year= ? and b.status_flag = ?  and" +
			" a.guideline_id  = b.guideline_id and a.duration_from = " +
			"(select max(duration_from) from igrs_guideline_parent_temp a," +
			" igrs_guideline_child1_temp b where a.district_id = ? and " +
			"a.financial_year= ? and b.status_flag = ? and " +
			"a.guideline_id = b.guideline_id ))and c1.mohalla_village_id = ? " +
			"and c.guideline_id = c1.guideline_id and " +
			"c1.guideline_child1_id = c2.guideline_child1_id and" +
			" c1.mohalla_village_id  = m.mohalla_village_id and " +
			"c2.property_type_id = p.property_type_id and " +
			"c2.property_type_l1_id = p1.property_type_l1_id(+) " +
			"and c2.property_type_l2_id = p2.property_type_l2_id(+) " +
			"and c2.unit_of_measure_id = u.uom_id";
	
	


	/*public static final String MOHALLA_PENDING_VIEW = "SELECT distinct igrs_property_type_master.property_type_name," +
	"igrs_prop_type_l1_master.property_type_l1_name, igrs_prop_type_l2_master.property_type_l2_name, " +
	"igrs_guideline_data_temp.guideline_value, igrs_guideline_data_temp.guideline_avg_value,igrs_guideline_data_temp.pct_increment, " +
	"igrs_mohalla_village_master.mohalla_village_id,igrs_mohalla_village_master.mohalla_village_name FROM " +
	"igrs_guideline_data_temp,igrs_property_type_master,  igrs_prop_type_l1_master,igrs_prop_type_l2_master, " +
	"igrs_mohalla_village_master  WHERE igrs_guideline_data_temp.mohalla_village_id = ? AND " +
	"igrs_guideline_data_temp.FINANCIAL_YEAR = ? AND " +
	"igrs_guideline_data_temp.STATUS_FLAG = ? AND "+
	"igrs_guideline_data_temp.mohalla_village_id =igrs_mohalla_village_master.mohalla_village_id AND " +
	"igrs_guideline_data_temp.property_type_id = igrs_property_type_master.property_type_id AND " +
	"igrs_guideline_data_temp.property_type_l1_id =  igrs_prop_type_l1_master.property_type_l1_id (+) AND " +
	"igrs_guideline_data_temp.property_type_l2_id =  igrs_prop_type_l2_master.property_type_l2_id(+)";*/
	
	public static final String MOHALLA_PENDING_VIEW = "SELECT distinct igrs_property_type_master.property_type_name," +
			"igrs_prop_type_l1_master.property_type_l1_name, igrs_prop_type_l2_master.property_type_l2_name," +
			" igrs_guideline_data_temp.guideline_value, igrs_guideline_data_temp.guideline_avg_value," +
			"igrs_guideline_data_temp.pct_increment, igrs_mohalla_village_master.mohalla_village_id," +
			"igrs_mohalla_village_master.mohalla_village_name, igrs_uom_master.uom_name FROM  " +
			"((igrs_guideline_data_temp left outer join igrs_mohalla_village_master on" +
			"(igrs_guideline_data_temp.mohalla_village_id =igrs_mohalla_village_master.mohalla_village_id)) " +
			"left outer join igrs_property_type_master on (igrs_guideline_data_temp.property_type_id = igrs_property_type_master.property_type_id )" +
			"left outer join igrs_prop_type_l1_master on" +
			"( igrs_guideline_data_temp.property_type_l1_id =  igrs_prop_type_l1_master.property_type_l1_id )" +
			" left outer join igrs_prop_type_l2_master on" +
			"( igrs_guideline_data_temp.property_type_l2_id =  igrs_prop_type_l2_master.property_type_l2_id) )" +
			"left outer join igrs_uom_master on" +
			"( igrs_prop_type_l1_master.UOM_ID = igrs_uom_master.uom_id) " +
			"WHERE igrs_guideline_data_temp.mohalla_village_id = ? AND" +
			" igrs_guideline_data_temp.FINANCIAL_YEAR = ? AND " +
			"igrs_guideline_data_temp.STATUS_FLAG = ? ";

	
	/**
	 * This query fetches the Mohalla/ Village List by taking the parameters as
	 * ward id, duration from date and duration to date.
	 * Applicable to both approval and creation process.
	 * 
	 * MOHALLA
	 */
	/*public static final String MOHALLA_FINAL_VIEW = "SELECT distinct igrs_property_type_master.property_type_name,"+
	"igrs_prop_type_l1_master.property_type_l1_name,  igrs_prop_type_l2_master.property_type_l2_name,"+
	"igrs_master_guideline_data.guideline_value,  igrs_master_guideline_data.guideline_avg_value,"+
	"igrs_master_guideline_data.pct_increment," +
	"igrs_mohalla_village_master.mohalla_village_id,"+
	"igrs_mohalla_village_master.mohalla_village_name FROM igrs_master_guideline_data,"+
	"igrs_property_type_master,  igrs_prop_type_l1_master,"+
	"igrs_prop_type_l2_master, igrs_mohalla_village_master  WHERE igrs_master_guideline_data.mohalla_village_id = ? AND ("+    
	"(igrs_prop_type_l1_master.property_type_l1_id = igrs_prop_type_l2_master.property_type_l1_id )"+" "+
	"AND (igrs_property_type_master.property_type_id = igrs_master_guideline_data.property_type_id)"+" "+
	"AND (igrs_prop_type_l1_master.property_type_l1_id =  igrs_master_guideline_data.property_type_l1_id)"+" "+
	"AND (igrs_prop_type_l2_master.property_type_l2_id =  igrs_master_guideline_data.property_type_l2_id)"+" "+
	"AND (igrs_mohalla_village_master.mohalla_village_id =igrs_master_guideline_data.mohalla_village_id)"+" "+
	"AND igrs_master_guideline_data.STATUS_FLAG = 'F'"+
	"AND igrs_master_guideline_data.FINANCIAL_YEAR = ?)";*/
	
	
	/*public static final String MOHALLA_FINAL_VIEW = "SELECT distinct igrs_property_type_master.property_type_name," +
	"igrs_prop_type_l1_master.property_type_l1_name, igrs_prop_type_l2_master.property_type_l2_name, " +
	"igrs_master_guideline_data.guideline_value, igrs_master_guideline_data.guideline_avg_value,igrs_master_guideline_data.pct_increment, " +
	"igrs_mohalla_village_master.mohalla_village_id,igrs_mohalla_village_master.mohalla_village_name FROM " +
	"igrs_master_guideline_data,igrs_property_type_master,  igrs_prop_type_l1_master,igrs_prop_type_l2_master, " +
	"igrs_mohalla_village_master  WHERE igrs_master_guideline_data.mohalla_village_id = ? AND " +
	"igrs_master_guideline_data.FINANCIAL_YEAR = ? AND " +
	"igrs_master_guideline_data.STATUS_FLAG = ? AND "+
	"igrs_master_guideline_data.mohalla_village_id =igrs_mohalla_village_master.mohalla_village_id AND " +
	"igrs_master_guideline_data.property_type_id = igrs_property_type_master.property_type_id AND " +
	"igrs_master_guideline_data.property_type_l1_id =  igrs_prop_type_l1_master.property_type_l1_id (+) AND " +
	"igrs_master_guideline_data.property_type_l2_id =  igrs_prop_type_l2_master.property_type_l2_id(+)";*/
	
	/*public static final String MOHALLA_FINAL_VIEW = "SELECT distinct igrs_property_type_master.property_type_name," +
			"igrs_prop_type_l1_master.property_type_l1_name, igrs_prop_type_l2_master.property_type_l2_name, " +
			"igrs_master_guideline_data.guideline_value, igrs_master_guideline_data.guideline_avg_value,igrs_master_guideline_data.pct_increment," +
			" igrs_mohalla_village_master.mohalla_village_id,igrs_mohalla_village_master.mohalla_village_name ,igrs_uom_master.uom_name FROM " +
			" ((igrs_master_guideline_data left outer join igrs_mohalla_village_master on" +
			"( igrs_master_guideline_data.mohalla_village_id =igrs_mohalla_village_master.mohalla_village_id))" +
			" left outer join igrs_property_type_master on " +
			"(igrs_master_guideline_data.property_type_id = igrs_property_type_master.property_type_id )" +
			"left outer join igrs_prop_type_l1_master on" +
			"( igrs_master_guideline_data.property_type_l1_id =  igrs_prop_type_l1_master.property_type_l1_id ) " +
			"left outer join igrs_prop_type_l2_master on" +
			"( igrs_master_guideline_data.property_type_l2_id =  igrs_prop_type_l2_master.property_type_l2_id) )" +
			"left outer join igrs_uom_master on" +
			"( igrs_prop_type_l1_master.UOM_ID = igrs_uom_master.uom_id) " +
			"WHERE igrs_master_guideline_data.mohalla_village_id = ? AND " +
			"igrs_master_guideline_data.FINANCIAL_YEAR = ? AND" +
			" igrs_master_guideline_data.STATUS_FLAG = ?";*/

	public static final String MOHALLA_FINAL_VIEW = "SELECT distinct p.property_type_name,p1.property_type_l1_name," +
			"p2.property_type_l2_name,c2.guideline_value,m.mohalla_village_id,m.mohalla_village_name," +
			"c1.area_type_id,c2.property_type_id,c2.property_type_l1_id, c2.property_type_l2_id ," +
			" u.uom_name, u.uom_id FROM IGRS_PROPERTY_TYPE_MASTER p ,IGRS_PROP_TYPE_L1_MASTER p1," +
			" IGRS_PROP_TYPE_L2_MASTER p2,igrs_guideline_master_child2 c2,igrs_guideline_master_child1 c1," +
			"igrs_guideline_master_parent c,IGRS_MOHALLA_VILLAGE_MASTER m, igrs_uom_master u " +
			"where c.guideline_id = (select guideline_id from igrs_guideline_master_parent where district_id = ?)" +
			" and c1.mohalla_village_id = ? and c.guideline_id = c1.guideline_id and " +
			"c1.guideline_child1_id = c2.guideline_child1_id and " +
			"c1.mohalla_village_id  = m.mohalla_village_id and " +
			"c2.property_type_id = p.property_type_id and " +
			"c2.property_type_l1_id = p1.property_type_l1_id(+) and " +
			"c2.property_type_l2_id = p2.property_type_l2_id(+) and " +
			"c2.unit_of_measure_id = u.uom_id";




	/**
	 * This query fetches the Mohalla/ Village List by taking the parameters as
	 * ward id, duration from date and duration to date.
	 * Applicable to both approval and creation process.
	 * 
	 * MOHALLA
	 */
	public static final String VILLAGE_DRAFT_VIEW = "SELECT distinct igrs_property_type_master.property_type_name,"+
	"igrs_prop_type_l1_master.property_type_l1_name,  igrs_prop_type_l2_master.property_type_l2_name,"+
	"igrs_guideline_data_temp.guideline_value,  igrs_guideline_data_temp.guideline_avg_value,"+
	"igrs_guideline_data_temp.pct_increment," +
	"igrs_mohalla_village_master.mohalla_village_id,"+
	"igrs_mohalla_village_master.mohalla_village_name FROM igrs_guideline_data_temp,"+
	"igrs_property_type_master,  igrs_prop_type_l1_master,"+
	"igrs_prop_type_l2_master, igrs_mohalla_village_master  WHERE igrs_guideline_data_temp.mohalla_village_id = ? AND ("+    
	"(igrs_prop_type_l1_master.property_type_l1_id = igrs_prop_type_l2_master.property_type_l1_id )"+" "+
	"AND (igrs_property_type_master.property_type_id = igrs_guideline_data_temp.property_type_id)"+" "+
	"AND (igrs_prop_type_l1_master.property_type_l1_id =  igrs_guideline_data_temp.property_type_l1_id)"+" "+
	"AND (igrs_prop_type_l2_master.property_type_l2_id =  igrs_guideline_data_temp.property_type_l2_id)"+" "+
	"AND (igrs_mohalla_village_master.mohalla_village_id =igrs_guideline_data_temp.mohalla_village_id)"+" "+
	"AND igrs_guideline_data_temp.FINANCIAL_YEAR = ?" +
	"AND igrs_guideline_data_temp.STATUS_FLAG = 'D')";


	/**
	 * This query fetches the Mohalla/ Village List by taking the parameters as
	 * ward id, duration from date and duration to date.
	 * Applicable to both approval and creation process.
	 * 
	 * MOHALLA
	 */
	public static final String VILLAGE_FINAL_VIEW = "SELECT distinct igrs_property_type_master.property_type_name,"+
	"igrs_prop_type_l1_master.property_type_l1_name,  igrs_prop_type_l2_master.property_type_l2_name,"+
	"igrs_master_guideline_data.guideline_value,  igrs_master_guideline_data.guideline_avg_value,"+
	"igrs_master_guideline_data.pct_increment," +
	"igrs_mohalla_village_master.mohalla_village_id,"+
	"igrs_mohalla_village_master.mohalla_village_name FROM igrs_master_guideline_data,"+
	"igrs_property_type_master,  igrs_prop_type_l1_master,"+
	"igrs_prop_type_l2_master, igrs_mohalla_village_master  WHERE igrs_master_guideline_data.mohalla_village_id = ? AND ("+    
	"(igrs_prop_type_l1_master.property_type_l1_id = igrs_prop_type_l2_master.property_type_l1_id )"+" "+
	"AND (igrs_property_type_master.property_type_id = igrs_master_guideline_data.property_type_id)"+" "+
	"AND (igrs_prop_type_l1_master.property_type_l1_id =  igrs_master_guideline_data.property_type_l1_id)"+" "+
	"AND (igrs_prop_type_l2_master.property_type_l2_id =  igrs_master_guideline_data.property_type_l2_id)"+" "+
	"AND (igrs_mohalla_village_master.mohalla_village_id =igrs_master_guideline_data.mohalla_village_id)"+" "+
	"AND igrs_master_guideline_data.FINANCIAL_YEAR = ?)"+
	"AND igrs_master_guideline_data.STATUS_FLAG = 'F')";



	/**
	 * 
	 */
	public static final String MOHALLA_NOVALUE = "SELECT"+" "+ 
	"igrs_mohalla_village_master.mohalla_village_id ,"+
	"igrs_mohalla_village_master.mohalla_village_name"+" "+ 
	"FROM igrs_mohalla_village_master WHERE igrs_mohalla_village_master.ward_patwari_id = ? ";


	/**
	 * VILLAGE
	 */
	/*public static final String VILLAGE = "SELECT distinct to_char(igrs_guideline_data_temp.base_period_from)" + '"'+"Base Period From"+'"'+ ","+
	"to_char(igrs_guideline_data_temp.base_period_to)"+'"'+"BasePeriodTo"+'"'+ ","+
	"igrs_mohalla_village_master.mohalla_village_id , igrs_mohalla_village_master.mohalla_village_name ,"+
	"igrs_guideline_data_temp.STATUS_FLAG, igrs_guideline_data_temp.mohalla_village_id FROM"+" "+
	"igrs_mohalla_village_master"+" "+ 
	"LEFT OUTER JOIN igrs_guideline_data_temp"+" "+
	"ON(igrs_mohalla_village_master.mohalla_village_id=igrs_guideline_data_temp.mohalla_village_id)"+ 
	"WHERE igrs_guideline_data_temp.ward_patwari_id = ?"+" "+ 
	"AND TRUNC(igrs_guideline_data_temp.CREATED_DATE)"+ 
	"BETWEEN TO_CHAR(TO_DATE(?, 'dd/mm/yy')) "+" "+
	"AND TO_CHAR(TO_DATE(?, 'dd/mm/yy'))"+" "+
	"UNION SELECT null,null, igrs_mohalla_village_master.mohalla_village_id ,"+
	"igrs_mohalla_village_master.mohalla_village_name ,"+
	"null, null FROM igrs_mohalla_village_master"+" "+ 
	"WHERE igrs_mohalla_village_master.mohalla_village_id not in"+ 
	"( select mohalla_village_id from igrs_guideline_data_temp"+ 
	" "+"where status_flag= 1)"; */



	/**
	 * INDIVIDUAL_MOHALLA_DETAILS_NOVALUE
	 */
	/*public static final String INDIVIDUAL_MOHALLA_DETAILS_NOVALUE = "select IGRS_PROPERTY_TYPE_MASTER.property_type_id,"+
	"IGRS_PROPERTY_TYPE_MASTER.property_type_name, IGRS_PROP_TYPE_L1_MASTER.property_type_l1_id,"+
	"IGRS_PROP_TYPE_L1_MASTER.property_type_l1_name, IGRS_PROP_TYPE_L2_MASTER.property_type_l2_id,"+
	"IGRS_PROP_TYPE_L2_MASTER.property_type_l2_name, igrs_uom_master.uom_id, igrs_uom_master.uom_name from((IGRS_PROPERTY_TYPE_MASTER left outer join IGRS_PROP_TYPE_L1_MASTER"+" "+ 
	"on(IGRS_PROP_TYPE_L1_MASTER.property_type_id=IGRS_PROPERTY_TYPE_MASTER.property_type_id))"+
	"left outer join"+" "+ 
	"IGRS_PROP_TYPE_L2_MASTER on(IGRS_PROP_TYPE_L2_MASTER.property_type_l1_id=IGRS_PROP_TYPE_L1_MASTER.property_type_l1_id))" +
	"left outer join igrs_uom_master on(IGRS_PROP_TYPE_L1_MASTER.uom_id = igrs_uom_master.uom_id)" +
	" "+"ORDER BY IGRS_PROP_TYPE_L1_MASTER.property_type_l1_id," +
	"IGRS_PROP_TYPE_L2_MASTER.property_type_l2_id";*/
	
	public static final String INDIVIDUAL_MOHALLA_DETAILS_NOVALUE = "SELECT IGRS_PROPERTY_TYPE_MASTER.property_type_id,IGRS_PROPERTY_TYPE_MASTER.property_type_name," +
			"IGRS_PROPERTY_TYPE_MASTER.H_PROPERTY_TYPE_NAME ,IGRS_PROP_TYPE_L1_MASTER_DUMMY.prop_type_l1_id," +
			"IGRS_PROP_TYPE_L1_MASTER_DUMMY.prop_type_l1_name,IGRS_PROP_TYPE_L1_MASTER_DUMMY.H_PROP_TYPE_L1_NAME, IGRS_PROP_TYPE_L2_MASTER_DUMMY.property_type_l2_id," +
			"IGRS_PROP_TYPE_L2_MASTER_DUMMY.property_type_l2_name,IGRS_PROP_TYPE_L2_MASTER_DUMMY.H_PROPERTY_TYPE_L2_NAME, igrs_uom_master.uom_id, " +
			"igrs_uom_master.uom_name,igrs_uom_master.H_UOM_NAME FROM ((IGRS_PROPERTY_TYPE_MASTER LEFT OUTER JOIN IGRS_PROP_TYPE_L1_MASTER_DUMMY ON " +
			"(IGRS_PROP_TYPE_L1_MASTER_DUMMY.property_type_id=IGRS_PROPERTY_TYPE_MASTER.property_type_id AND IGRS_PROP_TYPE_L1_MASTER_DUMMY.DISPLAY_FLAG_GUIDLN = 'Y' )) " +
			"LEFT OUTER JOIN IGRS_PROP_TYPE_L2_MASTER_DUMMY ON (IGRS_PROP_TYPE_L2_MASTER_DUMMY.property_type_l1_id=IGRS_PROP_TYPE_L1_MASTER_DUMMY.PROP_TYPE_L1_ID AND" +
			" (IGRS_PROP_TYPE_L2_MASTER_DUMMY.DISPLAY_FLAG = 'G' OR IGRS_PROP_TYPE_L2_MASTER_DUMMY.DISPLAY_FLAG = 'B'))) LEFT OUTER JOIN" +
			" igrs_uom_master ON (IGRS_PROP_TYPE_L1_MASTER_DUMMY.uom_id = igrs_uom_master.uom_id) WHERE " +
			"IGRS_PROPERTY_TYPE_MASTER.PROPERTY_TYPE_STATUS = 'A' AND IGRS_PROP_TYPE_L1_MASTER_DUMMY.PROP_TYPE_L1_STATUS = 'A' AND " +
			"IGRS_PROP_TYPE_L2_MASTER_DUMMY.PROPERTY_TYPE_L2_STATUS ='A' OR IGRS_PROP_TYPE_L2_MASTER_DUMMY.PROPERTY_TYPE_L2_STATUS IS NULL ORDER BY " +
			"IGRS_PROPERTY_TYPE_MASTER.PROPERTY_TYPE_ID,IGRS_PROP_TYPE_L1_MASTER_DUMMY.PROP_TYPE_L1_ID,IGRS_PROP_TYPE_L2_MASTER_DUMMY.PROPERTY_TYPE_L2_ID";



	/**
	 * This query is used to fetch the individual mohalla or village list from the temp table
	 * Duration From must be greater than the base period from and the Duration To
	 * must be less than the base period to.
	 * Applicable to Approval Process as well as Creation Process.
	 * 
	 * INDIVIDUAL_MOHALLA_DETAILS
	 */
	/*public static final String INDIVIDUAL_MOHALLA_DETAILS = "SELECT distinct igrs_guideline_data_temp.guideline_id,"+
	"igrs_guideline_data_temp.district_id,igrs_guideline_data_temp.tehsil_id,"+
	"igrs_guideline_data_temp.ward_patwari_id,igrs_guideline_data_temp.mohalla_village_id,"+
	"igrs_guideline_data_temp.area_type_id,igrs_guideline_data_temp.property_type_id property_id,"+
	"IGRS_GUIDELINE_AVERAGE_FUNC(igrs_guideline_data_temp.district_id,"+
	"igrs_guideline_data_temp.tehsil_id,igrs_guideline_data_temp.ward_patwari_id,"+
	"igrs_guideline_data_temp.mohalla_village_id,igrs_guideline_data_temp.area_type_id,"+
	"igrs_guideline_data_temp.property_type_id,igrs_guideline_data_temp.property_type_l1_id,"+
	"igrs_guideline_data_temp.property_type_l2_id) avg_val,(SELECT property_type_name"+ 
	" "+"FROM igrs_property_type_master WHERE igrs_property_type_master.property_type_id="+
	"igrs_guideline_data_temp.property_type_id) property_name,"+
	"igrs_guideline_data_temp.property_type_l1_id l1_id,(SELECT property_type_l1_name FROM igrs_prop_type_l1_master"+" "+
	"WHERE igrs_prop_type_l1_master.property_type_l1_id=igrs_guideline_data_temp.property_type_l1_id) l1_name,"+
	"igrs_guideline_data_temp.property_type_l2_id l2_id,(SELECT property_type_l2_name FROM igrs_prop_type_l2_master"+" "+ 

	"WHERE igrs_prop_type_l2_master.property_type_l2_id=igrs_guideline_data_temp.property_type_l2_id) l2_name,"+
	"igrs_guideline_data_temp.unit_of_measure_id, igrs_guideline_data_temp.guideline_value,"+
	"igrs_guideline_data_temp.duration_from, igrs_guideline_data_temp.duration_to,"+
	"igrs_guideline_data_temp.financial_year, igrs_guideline_data_temp.pct_increment,"+
	"igrs_guideline_data_temp.status_flag, igrs_guideline_data_temp.created_by,"+
	"to_char(igrs_guideline_data_temp.created_date, 'dd/mm/yy'),igrs_guideline_data_temp.update_by,igrs_guideline_data_temp.update_date,"+
	"igrs_guideline_data_temp.base_period_from,igrs_guideline_data_temp.base_period_to"+" "+ 
	"FROM igrs_guideline_data_temp,igrs_master_guideline_data_h WHERE "+" "+
	"igrs_master_guideline_data_h.MOHALLA_VILLAGE_ID=igrs_guideline_data_temp.MOHALLA_VILLAGE_ID"+ 
	" "+"and igrs_master_guideline_data_h.MOHALLA_VILLAGE_ID = ?"+" "+ 
	"AND( to_char(igrs_master_guideline_data_h.duration_from,'dd/mm/yy') >= ?"+" "+ 
	"AND to_char(igrs_master_guideline_data_h.duration_to,'dd/mm/yy')  <= ? )"+

	"GROUP BY igrs_guideline_data_temp.guideline_id,igrs_guideline_data_temp.district_id,"+
	"igrs_guideline_data_temp.tehsil_id, igrs_guideline_data_temp.ward_patwari_id,"+
	"igrs_guideline_data_temp.mohalla_village_id, igrs_guideline_data_temp.area_type_id,"+
	"igrs_guideline_data_temp.PROPERTY_TYPE_ID, igrs_guideline_data_temp.property_type_l1_id,"+
	"igrs_guideline_data_temp.property_type_l2_id, igrs_guideline_data_temp.unit_of_measure_id,"+
	"igrs_guideline_data_temp.guideline_value, igrs_guideline_data_temp.duration_from,"+
	"igrs_guideline_data_temp.duration_to, igrs_guideline_data_temp.financial_year,"+
	"igrs_guideline_data_temp.pct_increment,igrs_guideline_data_temp.status_flag,igrs_guideline_data_temp.created_by,"+ 
	"igrs_guideline_data_temp.created_date,igrs_guideline_data_temp.update_by,"+
	"igrs_guideline_data_temp.update_date,igrs_guideline_data_temp.base_period_from,"+
	"igrs_guideline_data_temp.base_period_to order by igrs_guideline_data_temp.PROPERTY_TYPE_L1_ID," +
	"igrs_guideline_data_temp.PROPERTY_TYPE_L2_ID";
	*/
	/*public static final String INDIVIDUAL_MOHALLA_DETAILS = "SELECT distinct igrs_property_type_master.property_type_name,"+
	"igrs_prop_type_l1_master.property_type_l1_name,  igrs_prop_type_l2_master.property_type_l2_name,"+
	"igrs_guideline_data_temp.guideline_value,  igrs_guideline_data_temp.guideline_avg_value,"+
	"igrs_guideline_data_temp.pct_increment," +
	"igrs_mohalla_village_master.mohalla_village_id,"+
	"igrs_mohalla_village_master.mohalla_village_name,igrs_guideline_data_temp.guideline_id, " +
	"igrs_guideline_data_temp.area_type_id,igrs_guideline_data_temp.property_type_id property_id,"+
	"igrs_guideline_data_temp.property_type_l1_id l1_id,igrs_guideline_data_temp.property_type_l2_id l2_id "+
	"FROM igrs_guideline_data_temp, "+
	"igrs_property_type_master,  igrs_prop_type_l1_master,"+
	"igrs_prop_type_l2_master, igrs_mohalla_village_master  WHERE igrs_guideline_data_temp.mohalla_village_id = ? AND ("+    
	"(igrs_prop_type_l1_master.property_type_l1_id = igrs_prop_type_l2_master.property_type_l1_id )"+" "+
	"AND (igrs_property_type_master.property_type_id = igrs_guideline_data_temp.property_type_id)"+" "+
	"AND (igrs_prop_type_l1_master.property_type_l1_id =  igrs_guideline_data_temp.property_type_l1_id)"+" "+
	"AND (igrs_prop_type_l2_master.property_type_l2_id =  igrs_guideline_data_temp.property_type_l2_id)"+" "+
	"AND (igrs_mohalla_village_master.mohalla_village_id =igrs_guideline_data_temp.mohalla_village_id)"+" "+
	"AND igrs_guideline_data_temp.STATUS_FLAG = 'P'"+" "+
	"AND igrs_guideline_data_temp.FINANCIAL_YEAR = ?)";*/
	
	public static final String INDIVIDUAL_MOHALLA_DETAILS = "SELECT distinct igrs_property_type_master.property_type_name,"+
	"igrs_prop_type_l1_master.property_type_l1_name,  igrs_prop_type_l2_master.property_type_l2_name,"+
	"igrs_guideline_data_temp.guideline_value,  igrs_guideline_data_temp.guideline_avg_value,"+
	"igrs_guideline_data_temp.pct_increment," +
	"igrs_mohalla_village_master.mohalla_village_id,"+
	"igrs_mohalla_village_master.mohalla_village_name,igrs_guideline_data_temp.guideline_id, " +
	"igrs_guideline_data_temp.area_type_id,igrs_guideline_data_temp.property_type_id property_id,"+
	"igrs_guideline_data_temp.property_type_l1_id l1_id,igrs_guideline_data_temp.property_type_l2_id l2_id FROM " +
	"igrs_guideline_data_temp,igrs_property_type_master,  igrs_prop_type_l1_master,igrs_prop_type_l2_master, " +
	"igrs_mohalla_village_master  WHERE igrs_guideline_data_temp.mohalla_village_id = ? AND " +
	"igrs_guideline_data_temp.FINANCIAL_YEAR = ? AND " +
	"igrs_guideline_data_temp.STATUS_FLAG = 1"+" AND "+
	"igrs_guideline_data_temp.mohalla_village_id =igrs_mohalla_village_master.mohalla_village_id AND " +
	"igrs_guideline_data_temp.property_type_id = igrs_property_type_master.property_type_id AND " +
	"igrs_guideline_data_temp.property_type_l1_id =  igrs_prop_type_l1_master.property_type_l1_id (+) AND " +
	"igrs_guideline_data_temp.property_type_l2_id =  igrs_prop_type_l2_master.property_type_l2_id(+)";
	

	//"AND igrs_guideline_data_temp.STATUS_FLAG = 'D'"+";


	//GuideLine View - Individual Mohalla Details
	/**
	 * INDIVIDUAL_MOHALLA_VIEW_DETAILS
	 */
	public static final String INDIVIDUAL_MOHALLA_VIEW_DETAILS = "SELECT igrs_guideline_data_temp.mohalla_village_id,"+
	"igrs_guideline_data_temp.PROPERTY_TYPE_ID,"+
	"(SELECT property_type_name FROM igrs_property_type_master"+ 
	" "+"WHERE igrs_property_type_master.property_type_id=igrs_guideline_data_temp.property_type_id)property_name,"+
	"(SELECT property_type_l1_name FROM igrs_prop_type_l1_master" +
	" "+"WHERE igrs_prop_type_l1_master.property_type_l1_id=igrs_guideline_data_temp.property_type_l1_id) l1_name,"+
	"(SELECT property_type_l2_name FROM igrs_prop_type_l2_master WHERE igrs_prop_type_l2_master.property_type_l2_id=igrs_guideline_data_temp.property_type_l2_id) l2_name,"+
	"igrs_guideline_data_temp.guideline_value FROM igrs_guideline_data_temp, igrs_master_guideline_data_h WHERE"+" "+
	"igrs_master_guideline_data_h.MOHALLA_VILLAGE_ID=igrs_guideline_data_temp.MOHALLA_VILLAGE_ID"+" "+
	"and igrs_master_guideline_data_h.MOHALLA_VILLAGE_ID = ?"+" "+ 
	"AND igrs_master_guideline_data_h.property_type_id=igrs_guideline_data_temp.property_type_id"+" "+ 
	"AND igrs_master_guideline_data_h.property_type_l1_id=igrs_guideline_data_temp.property_type_l1_id"+" "+ 
	"AND( igrs_master_guideline_data_h.property_type_l2_id=igrs_guideline_data_temp.property_type_l2_id"+" "+ 
	"OR IGRS_MASTER_GUIDELINE_DATA_H.property_type_l2_id is null)"+" "+
	"AND( to_char(igrs_master_guideline_data_h.duration_from,'dd/mm/yy') >= ?"+" "+ 
	"AND to_char(igrs_master_guideline_data_h.duration_to,'dd/mm/yy')  <= ?)"+" "+ 
	"GROUP BY igrs_guideline_data_temp.mohalla_village_id,igrs_guideline_data_temp.PROPERTY_TYPE_ID,"+
	"igrs_guideline_data_temp.property_type_l1_id"+
	",igrs_guideline_data_temp.property_type_l2_id,"+
	"igrs_guideline_data_temp.guideline_value,igrs_guideline_data_temp.duration_from,"+
	"igrs_guideline_data_temp.duration_to,igrs_guideline_data_temp.status_flag"+" "+ 
	"order by igrs_guideline_data_temp.PROPERTY_TYPE_ID,igrs_guideline_data_temp.PROPERTY_TYPE_L1_ID,igrs_guideline_data_temp.PROPERTY_TYPE_L2_ID";

	/**
	 * AREA_TYPE
	 */
	public static final String AREA_TYPE = "SELECT AREA_TYPE_ID, AREA_TYPE_NAME,H_AREA_TYPE_NAME FROM " +
	"IGRS_AREA_TYPE_MASTER where area_type_status = 'A' ORDER BY AREA_TYPE_NAME";


	/**
	 * GET_MOHALLA_NAME
	 */
	public static final String GET_MOHALLA_NAME = "SELECT MOHALLA_VILLAGE_NAME FROM" +
	" IGRS_MOHALLA_VILLAGE_MASTER WHERE MOHALLA_VILLAGE_ID = ? and mohalla_village_status = 'A'";


	/**
	 * MOHALLA_DETAILS_UPDATE
	 */
	public static final String MOHALLA_DETAILS_UPDATE = "UPDATE igrs_guideline_data_temp set " +
	"district_id = ?, tehsil_id = ?, WARD_PATWARI_ID = ?, MOHALLA_VILLAGE_ID = ?, area_type_id = ?, " +
	"unit_of_measure_id = ?, guideline_value = ?, duration_from = to_date(?, 'dd/mm/yy'), duration_to = to_date(?, 'dd/mm/yy'), " +
	"status_flag = ?, created_by = ?, created_date = to_date(?, 'dd/mm/yy'), update_by = ?, update_date = to_date(?, 'dd/mm/yy'), " +
	"base_period_from = to_date(?, 'dd/mm/yy'), base_period_to = to_date(?, 'dd/mm/yy'), financial_year = ?, GUIDELINE_AVG_VALUE = ?, PCT_INCREMENT = ?, INCREMENT_ON = ?" +
	" "+" where MOHALLA_VILLAGE_ID = 'MOH_001' AND PROPERTY_TYPE_ID = ? AND PROPERTY_TYPE_L1_ID=?" +
	"AND (property_type_l2_id = ? or property_type_l2_id is null) AND GUIDELINE_ID = ? ";



	/**
	 * INDIVIDUAL_MOHALLA_INSERT_CREATE   //create add guideline id here
	 */
	public static final String INDIVIDUAL_MOHALLA_INSERT_CREATE ="INSERT"+" "+ 
	"INTO igrs_guideline_data_temp(GUIDELINE_ID, DISTRICT_ID,	TEHSIL_ID,	WARD_PATWARI_ID ,MOHALLA_VILLAGE_ID,"+ 
	"AREA_TYPE_ID, PROPERTY_TYPE_ID, PROPERTY_TYPE_L1_ID,PROPERTY_TYPE_L2_ID,UNIT_OF_MEASURE_ID, GUIDELINE_VALUE, DURATION_FROM, DURATION_TO," +
	" STATUS_FLAG,"+ 
	"CREATED_BY, CREATED_DATE,"+
	"BASE_PERIOD_FROM,BASE_PERIOD_TO, FINANCIAL_YEAR,"+
	"GUIDELINE_AVG_VALUE,PCT_INCREMENT, INCREMENT_ON)"+" "+
	"VALUES(IGRS_GUIDELINE_DATA_SEQ.nextval,?, ?, ? ,?, ?,"+ 
	"?, ?, ?, ?, ?, to_date(?, 'dd/mm/yy'), to_date(?, 'dd/mm/yy'),"+
	"?, ?, SYSDATE, to_date(?, 'dd/mm/yy'), to_date(?, 'dd/mm/yy'),"+
	"?, ?, ?, ?)";




	/**
	 * MOHALLA_MASTER_DETAILS_INSERT
	 */
	public static final String MOHALLA_MASTER_DETAILS_INSERT = "INSERT ALL"+" "+ 
	"INTO IGRS_MASTER_GUIDELINE_DATA(GUIDELINE_ID, DISTRICT_ID,	TEHSIL_ID,	WARD_PATWARI_ID ,MOHALLA_VILLAGE_ID,"+ 
	"AREA_TYPE_ID, PROPERTY_TYPE_ID, PROPERTY_TYPE_L1_ID,PROPERTY_TYPE_L2_ID,UNIT_OF_MEASURE_ID, GUIDELINE_VALUE, DURATION_FROM, DURATION_TO," +
	" STATUS_FLAG,"+ 
	"CREATED_BY, CREATED_DATE, UPDATE_BY, UPDATE_DATE, VERSION_NO,"+
	"BASE_PERIOD_FROM,BASE_PERIOD_TO, FINANCIAL_YEAR,"+
	"GUIDELINE_AVG_VALUE,PCT_INCREMENT, INCREMENT_ON)"+
	"VALUES(GUIDELINE_ID,DISTRICT_ID, TEHSIL_ID,	WARD_PATWARI_ID ,MOHALLA_VILLAGE_ID,AREA_TYPE_ID, PROPERTY_TYPE_ID, PROPERTY_TYPE_L1_ID,"+ 
	"PROPERTY_TYPE_L2_ID, UNIT_OF_MEASURE_ID, GUIDELINE_VALUE, DURATION_FROM, DURATION_TO, STATUS_FLAG, CREATED_BY,"+
	"CREATED_DATE, UPDATE_BY, UPDATE_DATE, igrs_guideline_version_func, BASE_PERIOD_FROM, BASE_PERIOD_TO,"+
	"FINANCIAL_YEAR,GUIDELINE_AVG_VALUE,	PCT_INCREMENT,	INCREMENT_ON )"+

	"SELECT GUIDELINE_ID,"+ 
	"DISTRICT_ID,TEHSIL_ID,WARD_PATWARI_ID ,MOHALLA_VILLAGE_ID,	AREA_TYPE_ID,	PROPERTY_TYPE_ID,"+ 
	"PROPERTY_TYPE_L1_ID,	PROPERTY_TYPE_L2_ID,	UNIT_OF_MEASURE_ID, GUIDELINE_VALUE,	DURATION_FROM,	DURATION_TO,"+ 
	"STATUS_FLAG, CREATED_BY, CREATED_DATE, UPDATE_BY, UPDATE_DATE, BASE_PERIOD_FROM, BASE_PERIOD_TO,"+
	"FINANCIAL_YEAR,	GUIDELINE_AVG_VALUE,"+
	"PCT_INCREMENT,	INCREMENT_ON FROM IGRS_GUIDELINE_DATA_TEMP	WHERE status_flag = ?";



	/**
	 * MOHALLA_DETAILS_INSERT
	 */
	public static final String MOHALLA_DETAILS_UPDATE_APPROVAL = "update igrs_guideline_data_temp set"+" "+
	"district_id = ?, tehsil_id = ?, WARD_PATWARI_ID = ?, MOHALLA_VILLAGE_ID = ?, area_type_id = ?, " +
	"PROPERTY_TYPE_ID = nvl(?, null), PROPERTY_TYPE_L1_ID = nvl(?, null),PROPERTY_TYPE_L2_ID = nvl(?, null), unit_of_measure_id = ?, " +
	"guideline_value = ?, duration_from = to_date(?, 'dd/mm/yy'), duration_to = to_date(?, 'dd/mm/yy'), status_flag = ?, " +
	"created_by = ?, created_date = to_date(?, 'dd/mm/yy'), update_by = ?, update_date = SYSDATE, " +
	"base_period_from = to_date(?, 'dd/mm/yy'), base_period_to = to_date(?, 'dd/mm/yy'), FINANCIAL_YEAR = ?,"+
	"GUIDELINE_AVG_VALUE = ?, PCT_INCREMENT = ?, INCREMENT_ON = ? where GUIDELINE_ID = ?";

	public static final String MOHALLA_DETAILS_UPDATE_APPROVAL_NEW = "update igrs_guideline_parent_temp set"+" "+
	"district_id = ?, tehsil_id = ?, WARD_PATWARI_ID = ?, MOHALLA_VILLAGE_ID = ?, " +
	"PROPERTY_TYPE_ID = nvl(?, null), PROPERTY_TYPE_L1_ID = nvl(?, null),PROPERTY_TYPE_L2_ID = nvl(?, null), unit_of_measure_id = ?, " +
	"guideline_value = ?, duration_from = to_date(?, 'dd/mm/yy'), duration_to = to_date(?, 'dd/mm/yy'), status_flag = ?, " +
	"created_by = ?, created_date = to_date(?, 'dd/mm/yy'), update_by = ?, update_date = SYSDATE, " +
	"base_period_from = to_date(?, 'dd/mm/yy'), base_period_to = to_date(?, 'dd/mm/yy'), FINANCIAL_YEAR = ?,"+
	"GUIDELINE_AVG_VALUE = ?, PCT_INCREMENT = ?, INCREMENT_ON = ? where GUIDELINE_ID = ?";


	/**
	 * GET_MOHALLAS_DRAFT_VIEW
	 */
	public static final String GET_MOHALLAS_DRAFT_VIEW_1 ="SELECT distinct igrs_guideline_data_temp.mohalla_village_id,"+
	"igrs_mohalla_village_master.mohalla_village_name,  igrs_guideline_data_temp.financial_year"+" "+       
	"FROM igrs_mohalla_village_master, igrs_guideline_data_temp WHERE ((igrs_mohalla_village_master.mohalla_village_id ="+
	"igrs_guideline_data_temp.mohalla_village_id) AND igrs_guideline_data_temp.WARD_PATWARI_ID = ? "+" "+ 
	"AND igrs_guideline_data_temp.financial_year = ?)";

	public static final String GET_MOHALLAS_DRAFT_VIEW ="SELECT distinct m.mohalla_village_id,"+
	"m.mohalla_village_name,m.h_mohalla_village_name FROM IGRS_MOHALLA_VILLAGE_MASTER m WHERE m.WARD_PATWARI_ID=? and m.mohalla_village_status = 'A'";
	
	public static final String GET_VILLAGE_VIEW ="SELECT distinct m.mohalla_village_id,"+
	"m.mohalla_village_name	FROM IGRS_MOHALLA_VILLAGE_MASTER m WHERE m.WARD_PATWARI_ID=? and m.mohalla_village_status = 'A'";


	/**
	 * MOHALLA_DETAILS_FINAL_INSERT_APPROVAL
	 */

	/*
	public static final String MOHALLA_DETAILS_FINAL_INSERT_APPROVAL = "insert into igrs_master_guideline_data"+
	"(district_id, tehsil_id, WARD_PATWARI_ID, MOHALLA_VILLAGE_ID, area_type_id, " +
	"PROPERTY_TYPE_ID, PROPERTY_TYPE_L1_ID,PROPERTY_TYPE_L2_ID, unit_of_measure_id, " +
	"guideline_value, duration_from, duration_to, status_flag, " +
	"created_by, created_date, update_by, update_date, " +
	"base_period_from, base_period_to, FINANCIAL_YEAR,"+
	"GUIDELINE_AVG_VALUE, PCT_INCREMENT, INCREMENT_ON, GUIDELINE_ID) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, to_date(?, 'dd/mm/yy')," +
	"to_date(?, 'dd/mm/yy'), ?, ?, to_date(?, 'dd/mm/yy'), ?, SYSDATE, to_date(?, 'dd/mm/yy'), to_date(?, 'dd/mm/yy'), ?, ?, ?, ?, ?)";
	 */
	
	public static final String INDIVIDUAL_MOHALLA_DETAILS_BASE = "SELECT distinct igrs_property_type_master.property_type_name,"+
	"igrs_prop_type_l1_master.property_type_l1_name,  igrs_prop_type_l2_master.property_type_l2_name,"+
	"igrs_guideline_data_temp.guideline_value,  igrs_guideline_data_temp.guideline_avg_value,"+
	"igrs_guideline_data_temp.pct_increment," +
	"igrs_mohalla_village_master.mohalla_village_id,"+
	"igrs_mohalla_village_master.mohalla_village_name,igrs_guideline_data_temp.guideline_id, " +
	"igrs_guideline_data_temp.area_type_id,igrs_guideline_data_temp.property_type_id property_id,"+
	"igrs_guideline_data_temp.property_type_l1_id l1_id,igrs_guideline_data_temp.property_type_l2_id l2_id FROM " +
	"igrs_guideline_data_temp,igrs_property_type_master,  igrs_prop_type_l1_master,igrs_prop_type_l2_master, " +
	"igrs_mohalla_village_master  WHERE igrs_guideline_data_temp.mohalla_village_id = ? AND " +
	"igrs_guideline_data_temp.duration_from = ? AND " +
	"igrs_guideline_data_temp.duration_to = ? " +
	"igrs_guideline_data_temp.STATUS_FLAG = 1"+" AND "+
	"igrs_guideline_data_temp.mohalla_village_id =igrs_mohalla_village_master.mohalla_village_id AND " +
	"igrs_guideline_data_temp.property_type_id = igrs_property_type_master.property_type_id AND " +
	"igrs_guideline_data_temp.property_type_l1_id =  igrs_prop_type_l1_master.property_type_l1_id (+) AND " +
	"igrs_guideline_data_temp.property_type_l2_id =  igrs_prop_type_l2_master.property_type_l2_id(+)";
	
	// ADDED by SIMRAN
	
	public static final String GUIDELINE_ID = "SELECT MAX(GUIDELINE_ID) FROM IGRS_GUIDELINE_STATUS WHERE DISTRICT_ID = ? AND GUIDELINE_ID LIKE ?";
	
	
	public static final String COMBINATION_INSERTION = "INSERT ALL INTO IGRS_GUIDELINE_STATUS(GUIDELINE_ID,TEHSIL_ID,WARD_PATWARI_ID,COLONY_ID,MAKER_STATUS,CHECKER_STATUS, DISTRICT_ID," +
			"SUBAREA_WARD_MAPPING_ID,COLONY_WARD_MAPPING_ID, SUB_AREA_ID) VALUES" +
			" (?,TEHSIL_ID, WARD_PATWARI_ID,COLONY_ID,1,1,DISTRICT_ID,SUBAREA_WARD_MAPPING_ID,COLONY_WARD_MAPPING_ID,SUB_AREA_ID) " +
			"SELECT TEHSIL_ID, WARD_PATWARI_ID,COLONY_ID,DISTRICT_ID,SUBAREA_WARD_MAPPING_ID,COLONY_WARD_MAPPING_ID,SUB_AREA_ID  FROM " +
			"IGRS_GUIDELINE_TEMPLATE WHERE DISTRICT_ID = ?";
	
	public static final String TEHSIL_MAKER = "SELECT  DISTINCT IGRS_GUIDELINE_STATUS.TEHSIL_ID, IGRS_TEHSIL_MASTER.TEHSIL_NAME,IGRS_TEHSIL_MASTER.H_TEHSIL_NAME  FROM IGRS_TEHSIL_MASTER ," +
			" IGRS_GUIDELINE_STATUS WHERE IGRS_TEHSIL_MASTER.TEHSIL_ID = IGRS_GUIDELINE_STATUS.TEHSIL_ID AND IGRS_GUIDELINE_STATUS.DISTRICT_ID = ? AND IGRS_GUIDELINE_STATUS.GUIDELINE_ID = ? AND IGRS_GUIDELINE_STATUS.MAKER_STATUS IN(1,3) and IGRS_TEHSIL_MASTER.TEHSIL_STATUS  = 'A' ORDER BY IGRS_TEHSIL_MASTER.TEHSIL_NAME";
	
	public static final String TEHSIL_MAKER_FIRST_TIME = "SELECT  DISTINCT IGRS_TEHSIL_MASTER.TEHSIL_ID, IGRS_TEHSIL_MASTER.TEHSIL_NAME,IGRS_TEHSIL_MASTER.H_TEHSIL_NAME  " +
			"FROM IGRS_TEHSIL_MASTER WHERE " +
			" IGRS_TEHSIL_MASTER.DISTRICT_ID = ? AND IGRS_TEHSIL_MASTER.TEHSIL_STATUS  = 'A' ORDER BY IGRS_TEHSIL_MASTER.TEHSIL_NAME";
	
	
	/*public static final String TEHSIL_MAKER = "SELECT  DISTINCT IGRS_GUIDELINE_STATUS.TEHSIL_ID, IGRS_TEHSIL_MASTER.TEHSIL_NAME ,IGRS_GUIDELINE_PARENT_TEMP.DURATION_FROM,IGRS_GUIDELINE_PARENT_TEMP.DURATION_TO,  FROM IGRS_TEHSIL_MASTER ," +
			"IGRS_GUIDELINE_STATUS, IGRS_GUIDELINE_PARENT_TEMP WHERE IGRS_TEHSIL_MASTER.TEHSIL_ID = IGRS_GUIDELINE_STATUS.TEHSIL_ID AND IGRS_GUIDELINE_STATUS.DISTRICT_ID = ? AND IGRS_GUIDELINE_STATUS.GUIDELINE_ID = ? " +
			"AND IGRS_GUIDELINE_PARENT_TEMP.GUIDELINE_ID = ? AND IGRS_GUIDELINE_STATUS.MAKER_STATUS is null";*/
	
	public static final String TEHSIL_VIEW_ALL = "SELECT  DISTINCT IGRS_GUIDELINE_MASTER_CHILD1.TEHSIL_ID, IGRS_TEHSIL_MASTER.TEHSIL_NAME,IGRS_TEHSIL_MASTER.H_TEHSIL_NAME  FROM IGRS_TEHSIL_MASTER ," +
	" IGRS_GUIDELINE_MASTER_CHILD1 WHERE IGRS_TEHSIL_MASTER.TEHSIL_ID = IGRS_GUIDELINE_MASTER_CHILD1.TEHSIL_ID  AND IGRS_GUIDELINE_MASTER_CHILD1.GUIDELINE_ID = ?  and IGRS_TEHSIL_MASTER.TEHSIL_STATUS  = 'A' ORDER BY IGRS_TEHSIL_MASTER.TEHSIL_NAME";

	
	public static final String TEHSIL_VIEW_TEMP_ALL = "SELECT  DISTINCT IGRS_GUIDELINE_CHILD1_TEMP.TEHSIL_ID, IGRS_TEHSIL_MASTER.TEHSIL_NAME,IGRS_TEHSIL_MASTER.H_TEHSIL_NAME  FROM IGRS_TEHSIL_MASTER ," +
	" IGRS_GUIDELINE_CHILD1_TEMP WHERE IGRS_TEHSIL_MASTER.TEHSIL_ID = IGRS_GUIDELINE_CHILD1_TEMP.TEHSIL_ID  AND IGRS_GUIDELINE_CHILD1_TEMP.GUIDELINE_ID = ?  and IGRS_TEHSIL_MASTER.TEHSIL_STATUS  = 'A' ORDER BY IGRS_TEHSIL_MASTER.TEHSIL_NAME";

	
	public static final String SUBAREA_VIEW_ALL = "SELECT  DISTINCT IGRS_GUIDELINE_MASTER_CHILD1.SUB_AREA_TYPE_ID, IGRS_SUB_AREA_TYPE_MASTER.SUB_AREA_TYPE_NAME,IGRS_SUB_AREA_TYPE_MASTER.H_SUB_AREA_TYPE_NAME  FROM IGRS_SUB_AREA_TYPE_MASTER ," +
	" IGRS_GUIDELINE_MASTER_CHILD1 WHERE IGRS_SUB_AREA_TYPE_MASTER.SUB_AREA_TYPE_ID = IGRS_GUIDELINE_MASTER_CHILD1.SUB_AREA_TYPE_ID  AND IGRS_GUIDELINE_MASTER_CHILD1.GUIDELINE_ID = ? AND IGRS_GUIDELINE_MASTER_CHILD1.TEHSIL_ID = ? AND IGRS_GUIDELINE_MASTER_CHILD1.AREA_TYPE_ID = ?  and IGRS_SUB_AREA_TYPE_MASTER.SUB_AREA_TYPE_STATUS  = 'A' ORDER BY IGRS_SUB_AREA_TYPE_MASTER.SUB_AREA_TYPE_NAME";

	public static final String SUBAREA_VIEW_TEMP_ALL = "SELECT  DISTINCT IGRS_GUIDELINE_CHILD1_TEMP.SUB_AREA_TYPE_ID, IGRS_SUB_AREA_TYPE_MASTER.SUB_AREA_TYPE_NAME,IGRS_SUB_AREA_TYPE_MASTER.H_SUB_AREA_TYPE_NAME  FROM IGRS_SUB_AREA_TYPE_MASTER ," +
	" IGRS_GUIDELINE_CHILD1_TEMP WHERE IGRS_SUB_AREA_TYPE_MASTER.SUB_AREA_TYPE_ID = IGRS_GUIDELINE_CHILD1_TEMP.SUB_AREA_TYPE_ID  AND IGRS_GUIDELINE_CHILD1_TEMP.GUIDELINE_ID = ? AND IGRS_GUIDELINE_CHILD1_TEMP.TEHSIL_ID = ? AND IGRS_GUIDELINE_CHILD1_TEMP.AREA_TYPE_ID = ?  and IGRS_SUB_AREA_TYPE_MASTER.SUB_AREA_TYPE_STATUS  = 'A' ORDER BY IGRS_SUB_AREA_TYPE_MASTER.SUB_AREA_TYPE_NAME";

	
	public static final String WARD_VIEW_ALL = "SELECT  DISTINCT IGRS_GUIDELINE_MASTER_CHILD1.WARD_PATWARI_ID, IGRS_WARD_PATWARI_MASTER.WARD_PATWARI_NAME,IGRS_WARD_PATWARI_MASTER.H_WARD_PATWARI_NAME  FROM IGRS_WARD_PATWARI_MASTER ," +
	" IGRS_GUIDELINE_MASTER_CHILD1 WHERE IGRS_WARD_PATWARI_MASTER.WARD_PATWARI_ID = IGRS_GUIDELINE_MASTER_CHILD1.WARD_PATWARI_ID  AND IGRS_GUIDELINE_MASTER_CHILD1.GUIDELINE_ID = ? AND IGRS_GUIDELINE_MASTER_CHILD1.TEHSIL_ID = ? AND IGRS_GUIDELINE_MASTER_CHILD1.AREA_TYPE_ID = ? AND IGRS_GUIDELINE_MASTER_CHILD1.SUB_AREA_TYPE_ID = ?  and IGRS_WARD_PATWARI_MASTER.WARD_PATWARI_STATUS  = 'A' ORDER BY IGRS_WARD_PATWARI_MASTER.WARD_PATWARI_NAME";

	public static final String WARD_VIEW_TEMP_ALL = "SELECT  DISTINCT IGRS_GUIDELINE_CHILD1_TEMP.WARD_PATWARI_ID, IGRS_WARD_PATWARI_MASTER.WARD_PATWARI_NAME,IGRS_WARD_PATWARI_MASTER.H_WARD_PATWARI_NAME  FROM IGRS_WARD_PATWARI_MASTER ," +
	" IGRS_GUIDELINE_CHILD1_TEMP WHERE IGRS_WARD_PATWARI_MASTER.WARD_PATWARI_ID = IGRS_GUIDELINE_CHILD1_TEMP.WARD_PATWARI_ID  AND IGRS_GUIDELINE_CHILD1_TEMP.GUIDELINE_ID = ? AND IGRS_GUIDELINE_CHILD1_TEMP.TEHSIL_ID = ? AND IGRS_GUIDELINE_CHILD1_TEMP.AREA_TYPE_ID = ? AND IGRS_GUIDELINE_CHILD1_TEMP.SUB_AREA_TYPE_ID = ?  and IGRS_WARD_PATWARI_MASTER.WARD_PATWARI_STATUS  = 'A' ORDER BY IGRS_WARD_PATWARI_MASTER.WARD_PATWARI_NAME";

	
	public static final String COLONY_VIEW_ALL = "SELECT  DISTINCT IGRS_GUIDELINE_MASTER_CHILD1.COLONY_ID, IGRS_COLONY_MASTER.COLONY_NAME,IGRS_COLONY_MASTER.H_COLONY_NAME  FROM IGRS_COLONY_MASTER ," +
	" IGRS_GUIDELINE_MASTER_CHILD1 WHERE IGRS_COLONY_MASTER.COLONY_ID = IGRS_GUIDELINE_MASTER_CHILD1.COLONY_ID  AND IGRS_GUIDELINE_MASTER_CHILD1.GUIDELINE_ID = ? AND IGRS_GUIDELINE_MASTER_CHILD1.TEHSIL_ID = ? AND IGRS_GUIDELINE_MASTER_CHILD1.AREA_TYPE_ID = ? AND IGRS_GUIDELINE_MASTER_CHILD1.SUB_AREA_TYPE_ID = ? AND IGRS_GUIDELINE_MASTER_CHILD1.WARD_PATWARI_ID = ?  and IGRS_COLONY_MASTER.COLONY_STATUS  = 'A' ORDER BY IGRS_COLONY_MASTER.COLONY_NAME";

	public static final String COLONY_VIEW_TEMP_ALL = "SELECT  DISTINCT IGRS_GUIDELINE_CHILD1_TEMP.COLONY_ID, IGRS_COLONY_MASTER.COLONY_NAME,IGRS_COLONY_MASTER.H_COLONY_NAME  FROM IGRS_COLONY_MASTER ," +
	" IGRS_GUIDELINE_CHILD1_TEMP WHERE IGRS_COLONY_MASTER.COLONY_ID = IGRS_GUIDELINE_CHILD1_TEMP.COLONY_ID  AND IGRS_GUIDELINE_CHILD1_TEMP.GUIDELINE_ID = ? AND IGRS_GUIDELINE_CHILD1_TEMP.TEHSIL_ID = ? AND IGRS_GUIDELINE_CHILD1_TEMP.AREA_TYPE_ID = ? AND IGRS_GUIDELINE_CHILD1_TEMP.SUB_AREA_TYPE_ID = ? AND IGRS_GUIDELINE_CHILD1_TEMP.WARD_PATWARI_ID = ?  and IGRS_COLONY_MASTER.COLONY_STATUS  = 'A' ORDER BY IGRS_COLONY_MASTER.COLONY_NAME";

		
		public static final String WARD_MAKER = "SELECT  DISTINCT IGRS_GUIDELINE_STATUS.WARD_PATWARI_ID, IGRS_WARD_PATWARI_MASTER.WARD_PATWARI_NAME,IGRS_WARD_PATWARI_MASTER.H_WARD_PATWARI_NAME  FROM IGRS_WARD_PATWARI_MASTER ,IGRS_GUIDELINE_STATUS WHERE " +
			"IGRS_WARD_PATWARI_MASTER.WARD_PATWARI_ID = IGRS_GUIDELINE_STATUS.WARD_PATWARI_ID AND IGRS_GUIDELINE_STATUS.TEHSIL_ID = ? AND IGRS_GUIDELINE_STATUS.GUIDELINE_ID = ?  AND " +
			"IGRS_WARD_PATWARI_MASTER.WARD_OR_PATWARI = 1 AND IGRS_GUIDELINE_STATUS.MAKER_STATUS IN(1,3)  AND IGRS_WARD_PATWARI_MASTER.WARD_PATWARI_STATUS = 'A' ORDER BY IGRS_WARD_PATWARI_MASTER.WARD_PATWARI_NAME";

	public static final String PATWARI_MAKER = "SELECT  DISTINCT IGRS_GUIDELINE_STATUS.WARD_PATWARI_ID, IGRS_WARD_PATWARI_MASTER.WARD_PATWARI_NAME,IGRS_WARD_PATWARI_MASTER.H_WARD_PATWARI_NAME  FROM IGRS_WARD_PATWARI_MASTER ,IGRS_GUIDELINE_STATUS WHERE " +
	"IGRS_WARD_PATWARI_MASTER.WARD_PATWARI_ID = IGRS_GUIDELINE_STATUS.WARD_PATWARI_ID AND IGRS_GUIDELINE_STATUS.TEHSIL_ID = ? AND IGRS_GUIDELINE_STATUS.GUIDELINE_ID = ?  AND IGRS_WARD_PATWARI_MASTER.WARD_OR_PATWARI = 2 AND IGRS_GUIDELINE_STATUS.MAKER_STATUS IN(1,3)AND IGRS_WARD_PATWARI_MASTER.WARD_PATWARI_STATUS = 'A' ORDER BY IGRS_WARD_PATWARI_MASTER.WARD_PATWARI_NAME ";

	public static final String MOHALLA_MAKER = "SELECT  DISTINCT IGRS_GUIDELINE_STATUS.MOHALLA_VILLAGE_ID, IGRS_MOHALLA_VILLAGE_MASTER.MOHALLA_VILLAGE_NAME,IGRS_MOHALLA_VILLAGE_MASTER.H_MOHALLA_VILLAGE_NAME  FROM IGRS_MOHALLA_VILLAGE_MASTER ,IGRS_GUIDELINE_STATUS WHERE " +
	"IGRS_MOHALLA_VILLAGE_MASTER.MOHALLA_VILLAGE_ID = IGRS_GUIDELINE_STATUS.MOHALLA_VILLAGE_ID AND IGRS_GUIDELINE_STATUS.WARD_PATWARI_ID = ? AND IGRS_GUIDELINE_STATUS.GUIDELINE_ID = ?  AND IGRS_GUIDELINE_STATUS.MAKER_STATUS IN(1,3) AND IGRS_MOHALLA_VILLAGE_MASTER.MOHALLA_VILLAGE_STATUS = 'A' ORDER BY IGRS_MOHALLA_VILLAGE_MASTER.MOHALLA_VILLAGE_NAME";
	
	public static final String INSERT_TEMP_PARENT = "insert into IGRS_GUIDELINE_PARENT_TEMP(GUIDELINE_ID,FINANCIAL_YEAR,DISTRICT_ID, DURATION_FROM, DURATION_TO, VERSION_NO,CREATED_BY,CREATED_DATE,DERIVED_FROM,DERIVED_FROM_GUIDELINE_ID,DERVIED_BY_STATUS) " +
			"values(?,?,?,to_date(?,'dd/mm/yy'),to_date(?,'dd/mm/yy'),?,?, SYSDATE,?,?,'')";
	public static final String INSERT_TEMP_PARENT_DRIVE = "insert into IGRS_GUIDELINE_PARENT_TEMP(GUIDELINE_ID,FINANCIAL_YEAR,DISTRICT_ID, DURATION_FROM, DURATION_TO, VERSION_NO,CREATED_BY,CREATED_DATE,DERIVED_FROM,DERIVED_FROM_GUIDELINE_ID,DERVIED_BY_STATUS) " +
	"values(?,?,?,to_date(?,'dd/mm/yy'),to_date(?,'dd/mm/yy'),?,?, SYSDATE,?,?,'SR')";
	
	public static final String INSERT_TEMP_PARENT_DERIVED_FINAL = "insert into IGRS_GUIDELINE_PARENT_TEMP(GUIDELINE_ID,FINANCIAL_YEAR,DISTRICT_ID, DURATION_FROM, DURATION_TO, VERSION_NO,CREATED_BY,CREATED_DATE,DERIVED_FROM,DERIVED_FROM_GUIDELINE_ID,DERVIED_BY_STATUS) " +
	"values(?,?,?,to_date(?,'dd/mm/yy'),to_date(?,'dd/mm/yy'),?,?, SYSDATE,?,?,'SR')";

	//public static final String INSERT_TEMP_CHILD1 = "insert into IGRS_GUIDELINE_CHILD1_TEMP(GUIDELINE_CHILD1_ID, GUIDELINE_ID, TEHSIL_ID, AREA_TYPE_ID, WARD_PATWARI_ID, MOHALLA_VILLAGE_ID, STATUS_FLAG, BASE_PERIOD_FROM, BASE_PERIOD_TO, CREATED_BY, CREATED_DATE, VALIDATE_STATUS) " +
			//"VALUES(?,?,?,?,?,?,?,to_date(?,'dd/mm/yy'), to_date(?,'dd/mm/yy'),?,SYSDATE,1)";
	
	public static final String INSERT_TEMP_CHILD1 = "INSERT INTO IGRS_GUIDELINE_CHILD1_TEMP " +
			"(GUIDELINE_CHILD1_ID, GUIDELINE_ID, TEHSIL_ID, AREA_TYPE_ID, WARD_PATWARI_ID, COLONY_ID, STATUS_FLAG, CREATED_BY, CREATED_DATE," +
			" VALIDATE_STATUS, SUB_AREA_TYPE_ID, SUBAREA_WARD_MAPPING_ID, COLONY_WARD_MAPPING_ID) VALUES ( ?,? ,? ,?,? ,? ,?,? ,SYSDATE,1 ,?,? ,? )";

	public static final String INSERT_TEMP_CHILD2 = "insert into IGRS_GUIDELINE_CHILD2_TEMP(ID,GUIDELINE_CHILD1_ID,PROPERTY_TYPE_ID,PROPERTY_TYPE_L1_ID,PROPERTY_TYPE_L2_ID, UNIT_OF_MEASURE_ID,GUIDELINE_VALUE,GUIDELINE_AVG_VALUE, PCT_INCREMENT,INCREMENT_ON, CREATED_BY, CREATED_DATE)" +
			"VALUES (IGRS_GUIDELINE_CHILD2_SEQ.nextval,?,?,?,?,?,?,?,?,?,?,SYSDATE)";
	
	public static final String SELECT_SEQ = "select IGRS_GUIDELINE_CHILD1_SEQ.nextval from dual"; 
	
	public static final String UPDATE_GUIDELINE_STATUS = "UPDATE IGRS_GUIDELINE_STATUS SET MAKER_STATUS = ? WHERE COLONY_ID = ? AND GUIDELINE_ID = ? AND COLONY_WARD_MAPPING_ID = ?";
	
	/*public static final String SHOW_VERSION_MAKER = "Select distinct VERSION_NO, DURATION_FROM,DURATION_TO " +
			
			"FROM IGRS_GUIDELINE_PARENT_TEMP WHERE" +
			" FINANCIAL_YEAR = ? AND DISTRICT_ID = ?  " ;
	*/
	/*public static final String SHOW_VERSION_MAKER = "SELECT VERSION_NO, DURATION_FROM, DURATION_TO," +
			"case when d.GUIDELINE_ID>0 then 'Pending' else 'Complete' end ," +
			"case when b.GUIDELINE_ID>0 then 'Draft' when c.GUIDELINE_ID > 0 then 'Final' else 'Pending' end" +
			" FROM IGRS_GUIDELINE_PARENT_TEMP a left join " +
			"(select GUIDELINE_ID from IGRS_GUIDELINE_STATUS where checker_status = 2 group by GUIDELINE_ID) b " +
			"on  a.GUIDELINE_ID  = b.GUIDELINE_ID left join" +
			"(select GUIDELINE_ID from IGRS_GUIDELINE_STATUS where checker_status = 3 group by GUIDELINE_ID) c " +
			"on a.GUIDELINE_ID = c.GUIDELINE_ID left join " +
			"(select GUIDELINE_ID from IGRS_GUIDELINE_STATUS where maker_status = 1 group by GUIDELINE_ID) d " +
			"on  a.GUIDELINE_ID  = d.GUIDELINE_ID where a.financial_year = ? AND a.district_id = ? AND a.derived_from = 1 and a.publish_status is null order by version_no desc";
	
	public static final String SHOW_VERSION_FINAL_MAKER = "SELECT VERSION_NO, DURATION_FROM, DURATION_TO ," +
	"case when d.GUIDELINE_ID>0 then 'Pending' else 'Complete' end ," +
	"case when b.GUIDELINE_ID>0 then 'Draft' when c.GUIDELINE_ID > 0 then 'Final' else 'Pending' end" +
	" FROM IGRS_GUIDELINE_PARENT_TEMP a left join " +
	"(select GUIDELINE_ID from IGRS_GUIDELINE_STATUS where checker_status = 2 group by GUIDELINE_ID) b " +
	"on  a.GUIDELINE_ID  = b.GUIDELINE_ID left join" +
	"(select GUIDELINE_ID from IGRS_GUIDELINE_STATUS where checker_status = 3 group by GUIDELINE_ID) c " +
	"on a.GUIDELINE_ID = c.GUIDELINE_ID left join " +
	"(select GUIDELINE_ID from IGRS_GUIDELINE_STATUS where maker_status = 1 group by GUIDELINE_ID) d " +
	"on  a.GUIDELINE_ID  = d.GUIDELINE_ID where a.financial_year = ? AND a.district_id = ? AND a.derived_from = ? and a.PUBLISH_STATUS is null order by VERSION_NO desc";*/
	
	public static final String SHOW_VERSION_MAKER = "SELECT VERSION_NO, DURATION_FROM, DURATION_TO," +
	"case when d.GUIDELINE_ID>0 then 'Pending' else 'Complete' end ," +
	"case when b.GUIDELINE_ID>0 then 'Draft' when c.GUIDELINE_ID > 0 then 'Final' else 'Pending' end" +
	" FROM IGRS_GUIDELINE_PARENT_TEMP a left join " +
	"(select GUIDELINE_ID from IGRS_GUIDELINE_STATUS where checker_status = 2 group by GUIDELINE_ID) b " +
	"on  a.GUIDELINE_ID  = b.GUIDELINE_ID left join" +
	"(select GUIDELINE_ID from IGRS_GUIDELINE_STATUS where checker_status = 3 group by GUIDELINE_ID) c " +
	"on a.GUIDELINE_ID = c.GUIDELINE_ID left join " +
	"(select GUIDELINE_ID from IGRS_GUIDELINE_STATUS where maker_status = 1 group by GUIDELINE_ID) d " +
	"on  a.GUIDELINE_ID  = d.GUIDELINE_ID where a.financial_year = ? AND a.district_id = ? and DERVIED_BY_STATUS is null AND a.derived_from = 1 and a.DR_PUBLISH_STATUS is null and (a.sr_publish_status is null or a.sr_publish_status=3)  order by version_no desc";
	public static final String SHOW_VERSION_MAKER_FINAL = "SELECT a.VERSION_NO, a.DURATION_FROM, a.DURATION_TO, " +
	" 'Complete', " +
	" 'Final'" +
	" FROM IGRS_GUIDELINE_MASTER_PARENT a " +
	" where a.financial_year = ? AND a.district_id = ? order by a.version_no desc ";

public static final String SHOW_VERSION_FINAL_MAKER = "SELECT VERSION_NO, DURATION_FROM, DURATION_TO ," +
"case when d.GUIDELINE_ID>0 then 'Pending' else 'Complete' end ," +
"case when b.GUIDELINE_ID>0 then 'Draft' when c.GUIDELINE_ID > 0 then 'Final' else 'Pending' end" +
" FROM IGRS_GUIDELINE_PARENT_TEMP a left join " +
"(select GUIDELINE_ID from IGRS_GUIDELINE_STATUS where checker_status = 2 group by GUIDELINE_ID) b " +
"on  a.GUIDELINE_ID  = b.GUIDELINE_ID left join" +
"(select GUIDELINE_ID from IGRS_GUIDELINE_STATUS where checker_status = 3 group by GUIDELINE_ID) c " +
"on a.GUIDELINE_ID = c.GUIDELINE_ID left join " +
"(select GUIDELINE_ID from IGRS_GUIDELINE_STATUS where maker_status = 1 group by GUIDELINE_ID) d " +
"on  a.GUIDELINE_ID  = d.GUIDELINE_ID where a.financial_year = ? AND a.district_id = ?  and a.DERVIED_BY_STATUS='SR' and (a.sr_publish_status is null or a.sr_publish_status=3) and a.DR_PUBLISH_STATUS is null and a.PUBLISH_STATUS is null order by VERSION_NO desc";
	
public static final String SHOW_VERSION_FINAL_MAKER_DR = "SELECT VERSION_NO, DURATION_FROM, DURATION_TO ," +
"case when d.GUIDELINE_ID>0 then 'Pending' else 'Complete' end ," +
"case when b.GUIDELINE_ID>0 then 'Draft' when c.GUIDELINE_ID > 0 then 'Final' else 'Pending' end" +
" FROM IGRS_GUIDELINE_PARENT_TEMP a left join " +
"(select GUIDELINE_ID from IGRS_GUIDELINE_STATUS where checker_status = 2 group by GUIDELINE_ID) b " +
"on  a.GUIDELINE_ID  = b.GUIDELINE_ID left join" +
"(select GUIDELINE_ID from IGRS_GUIDELINE_STATUS where checker_status = 3 group by GUIDELINE_ID) c " +
"on a.GUIDELINE_ID = c.GUIDELINE_ID left join " +
"(select GUIDELINE_ID from IGRS_GUIDELINE_STATUS where maker_status = 1 group by GUIDELINE_ID) d " +
"on  a.GUIDELINE_ID  = d.GUIDELINE_ID where a.financial_year = ? AND a.district_id = ?  and a.DERVIED_BY_STATUS='DR' and (a.sr_publish_status is null or a.sr_publish_status=3) and a.DR_PUBLISH_STATUS is null and a.PUBLISH_STATUS is null order by VERSION_NO desc";




	public static final String CHECK = "SELECT DISTRICT_ID FROM IGRS_GUIDELINE_PARENT_TEMP WHERE GUIDELINE_ID = ?";
	
	
	
	/**
	 * CHECKER for GUIDELINE
	 */
	
	public static final String CHECK_CHECKER = "SELECT DISTRICT_ID FROM IGRS_GUIDELINE_MASTER_PARENT WHERE GUIDELINE_ID = ?";
	
	public static final String CHECK_CHECKER2 = "SELECT distinct Tehsil_id FROM IGRS_GUIDELINE_MASTER_CHILD1 WHERE GUIDELINE_ID = ? and tehsil_id = ? and ward_patwari_id = ? and mohalla_village_id  =?";
	
	/*public static final String SHOW_VERSION_CHECKER = "SELECT distinct VERSION_NO, DURATION_FROM, DURATION_TO ," +
			"case when b.GUIDELINE_ID>0 then 'Pending' when c.GUIDELINE_ID > 0 then 'Complete' end," +
			"case when e.GUIDELINE_ID>0 then 'Draft' when f.GUIDELINE_ID > 0 then 'Final' else 'Pending' end," +
			"case when g.guideline_id > 0 then 'New' when h.guideline_id > 0 then 'Draft' else 'Final' end " +
			"FROM IGRS_GUIDELINE_PARENT_TEMP a left join " +
			"(select GUIDELINE_ID from IGRS_GUIDELINE_STATUS where checker_status =1 group by GUIDELINE_ID) b on  " +
			"a.GUIDELINE_ID  = b.GUIDELINE_ID left join" +
			"(select GUIDELINE_ID from IGRS_GUIDELINE_STATUS where checker_status in(2,3) group by GUIDELINE_ID) c on" +
			" a.GUIDELINE_ID = c.GUIDELINE_ID left join " +
			"(select GUIDELINE_ID from IGRS_GUIDELINE_STATUS where checker_status = 2 group by GUIDELINE_ID) e on" +
			"  a.GUIDELINE_ID  = e.GUIDELINE_ID left join" +
			"(select GUIDELINE_ID from IGRS_GUIDELINE_STATUS where checker_status = 3  group by GUIDELINE_ID) f on" +
			" a.GUIDELINE_ID = f.GUIDELINE_ID left join " +
			"(select guideline_id from IGRS_GUIDELINE_PARENT_TEMP where derived_from = 1 group by guideline_id) g on" +
			" a.guideline_id = g.guideline_id left join " +
			"(select guideline_id from IGRS_GUIDELINE_PARENT_TEMP where derived_from = 2 group by guideline_id) h on " +
			"a.guideline_id = h.guideline_id where a.financial_year =? AND a.district_id = ? order by version_no";
	*/
	
	public static final String SHOW_VERSION_CHECKER = "SELECT distinct VERSION_NO, DURATION_FROM, DURATION_TO , " +
			"case when b.GUIDELINE_ID>0 then 'Pending'when j.guideline_id > 0 then 'Pending' when c.GUIDELINE_ID > 0 then 'Complete' when i.guideline_id > 0 then 'Complete' end, " +
			"case when e.GUIDELINE_ID>0 then 'Draft' when f.GUIDELINE_ID > 0 then 'Final' else 'Pending' end, " +
			"case when g.guideline_id > 0 then 'New' when h.guideline_id > 0 then 'Draft' else 'Final' end,DR_PUBLISH_STATUS FROM " +
			"IGRS_GUIDELINE_PARENT_TEMP a left join (select GUIDELINE_ID from IGRS_GUIDELINE_STATUS where " +
			"checker_status =1 group by GUIDELINE_ID) b on a.GUIDELINE_ID  = b.GUIDELINE_ID left join " +
			"(select GUIDELINE_ID from IGRS_GUIDELINE_STATUS where maker_status = 4 and checker_status in (1,4) group by GUIDELINE_ID) j " +
			"on a.GUIDELINE_ID  = j.GUIDELINE_ID left join " +
			"(select GUIDELINE_ID from IGRS_GUIDELINE_STATUS where checker_status in(2,3) group by GUIDELINE_ID) c on" +
			" a.GUIDELINE_ID = c.GUIDELINE_ID left join" +
			"(select GUIDELINE_ID from IGRS_GUIDELINE_STATUS where maker_status = 3 and checker_status = 4 group by GUIDELINE_ID) i on" +
			" a.GUIDELINE_ID = i.GUIDELINE_ID left join" +
			"(select GUIDELINE_ID from IGRS_GUIDELINE_STATUS where checker_status = 2 group by GUIDELINE_ID) e on " +
			"a.GUIDELINE_ID  = e.GUIDELINE_ID left join " +
			"(select GUIDELINE_ID from IGRS_GUIDELINE_STATUS where checker_status = 3  group by GUIDELINE_ID) f on " +
			"a.GUIDELINE_ID = f.GUIDELINE_ID left join " +
			"(select guideline_id from IGRS_GUIDELINE_PARENT_TEMP where derived_from = 1 group by guideline_id) g on" +
			" a.guideline_id = g.guideline_id left join" +
			"(select guideline_id from IGRS_GUIDELINE_PARENT_TEMP where derived_from = 2 group by guideline_id) h on" +
			" a.guideline_id = h.guideline_id where a.financial_year =? AND a.district_id = ? and a.PUBLISH_STATUS is null and a.PRINT_STATUS='C' and( a.DR_PUBLISH_STATUS='3' or a.DERVIED_BY_STATUS='DR') order by version_no desc";
	
	public static final String PENDING_TEHSIL_CHECKER ="SELECT  DISTINCT IGRS_GUIDELINE_STATUS.TEHSIL_ID, IGRS_TEHSIL_MASTER.TEHSIL_NAME,IGRS_TEHSIL_MASTER.H_TEHSIL_NAME  FROM IGRS_TEHSIL_MASTER ," +
			" IGRS_GUIDELINE_STATUS WHERE IGRS_TEHSIL_MASTER.TEHSIL_ID = IGRS_GUIDELINE_STATUS.TEHSIL_ID AND IGRS_GUIDELINE_STATUS.DISTRICT_ID = ? AND IGRS_GUIDELINE_STATUS.GUIDELINE_ID = ? AND IGRS_GUIDELINE_STATUS.CHECKER_STATUS in(1,4) AND IGRS_GUIDELINE_STATUS.MAKER_STATUS IN(1,2,4) AND IGRS_TEHSIL_MASTER.TEHSIL_STATUS = 'A' ORDER BY IGRS_TEHSIL_MASTER.TEHSIL_NAME";
		
	public static final String PENDING_TEHSIL_CHECKER_HI ="SELECT  DISTINCT IGRS_GUIDELINE_STATUS.TEHSIL_ID, IGRS_TEHSIL_MASTER.TEHSIL_NAME,IGRS_TEHSIL_MASTER.H_TEHSIL_NAME  FROM IGRS_TEHSIL_MASTER ," +
	" IGRS_GUIDELINE_STATUS WHERE IGRS_TEHSIL_MASTER.TEHSIL_ID = IGRS_GUIDELINE_STATUS.TEHSIL_ID AND IGRS_GUIDELINE_STATUS.DISTRICT_ID = ? AND IGRS_GUIDELINE_STATUS.GUIDELINE_ID = ? AND IGRS_GUIDELINE_STATUS.CHECKER_STATUS in(1,4) AND IGRS_GUIDELINE_STATUS.MAKER_STATUS IN(1,2,4) AND IGRS_TEHSIL_MASTER.TEHSIL_STATUS = 'A' ORDER BY IGRS_TEHSIL_MASTER.H_TEHSIL_NAME";

	public static final String COMPLETE_TEHSIL_CHECKER ="select distinct a.tehsil_id, tehsil_name,h_tehsil_name from IGRS_GUIDELINE_STATUS a ," +
			" IGRS_TEHSIL_MASTER b where a.district_id =? and a.guideline_id =?" +
			" and a.tehsil_id = b.tehsil_id and b.TEHSIL_STATUS = 'A' and " +
			"a.tehsil_id not in (select distinct  tehsil_id from IGRS_GUIDELINE_STATUS where district_id =? " +
			"and guideline_id =? and checker_status in(1,4) and maker_status in(1,2,4)) ORDER BY tehsil_name";
	
	public static final String COMPLETE_TEHSIL_CHECKER_HI ="select distinct a.tehsil_id, tehsil_name,h_tehsil_name from IGRS_GUIDELINE_STATUS a ," +
	" IGRS_TEHSIL_MASTER b where a.district_id =? and a.guideline_id =?" +
	" and a.tehsil_id = b.tehsil_id and b.TEHSIL_STATUS = 'A' and " +
	"a.tehsil_id not in (select distinct  tehsil_id from IGRS_GUIDELINE_STATUS where district_id =? " +
	"and guideline_id =? and checker_status in(1,4) and maker_status in(1,2,4)) ORDER BY h_tehsil_name";
	
	public static final String WARD_CHECKER = "SELECT  DISTINCT IGRS_GUIDELINE_STATUS.WARD_PATWARI_ID, IGRS_WARD_PATWARI_MASTER.WARD_PATWARI_NAME,IGRS_WARD_PATWARI_MASTER.H_WARD_PATWARI_NAME,IGRS_GUIDELINE_STATUS.SUBAREA_WARD_MAPPING_ID  FROM IGRS_WARD_PATWARI_MASTER ,IGRS_GUIDELINE_STATUS WHERE " +
	"IGRS_WARD_PATWARI_MASTER.WARD_PATWARI_ID = IGRS_GUIDELINE_STATUS.WARD_PATWARI_ID AND IGRS_GUIDELINE_STATUS.TEHSIL_ID = ? AND IGRS_GUIDELINE_STATUS.GUIDELINE_ID = ?  AND " +
	"IGRS_WARD_PATWARI_MASTER.WARD_OR_PATWARI = '1' AND IGRS_GUIDELINE_STATUS.CHECKER_STATUS in(1,4)  AND IGRS_GUIDELINE_STATUS.MAKER_STATUS in (1,2,4) and IGRS_WARD_PATWARI_MASTER.ward_patwari_status = 'A' ORDER BY IGRS_WARD_PATWARI_MASTER.WARD_PATWARI_NAME";

	public static final String WARD_CHECKER_HI = "SELECT  DISTINCT IGRS_GUIDELINE_STATUS.WARD_PATWARI_ID, IGRS_WARD_PATWARI_MASTER.WARD_PATWARI_NAME,IGRS_WARD_PATWARI_MASTER.H_WARD_PATWARI_NAME,IGRS_GUIDELINE_STATUS.SUBAREA_WARD_MAPPING_ID  FROM IGRS_WARD_PATWARI_MASTER ,IGRS_GUIDELINE_STATUS WHERE " +
	"IGRS_WARD_PATWARI_MASTER.WARD_PATWARI_ID = IGRS_GUIDELINE_STATUS.WARD_PATWARI_ID AND IGRS_GUIDELINE_STATUS.TEHSIL_ID = ? AND IGRS_GUIDELINE_STATUS.GUIDELINE_ID = ?  AND " +
	"IGRS_WARD_PATWARI_MASTER.WARD_OR_PATWARI = '1' AND IGRS_GUIDELINE_STATUS.CHECKER_STATUS in(1,4)  AND IGRS_GUIDELINE_STATUS.MAKER_STATUS in (1,2,4) and IGRS_WARD_PATWARI_MASTER.ward_patwari_status = 'A' ORDER BY IGRS_WARD_PATWARI_MASTER.H_WARD_PATWARI_NAME";

public static final String PATWARI_CHECKER = "SELECT  DISTINCT IGRS_GUIDELINE_STATUS.WARD_PATWARI_ID, IGRS_WARD_PATWARI_MASTER.WARD_PATWARI_NAME,IGRS_WARD_PATWARI_MASTER.H_WARD_PATWARI_NAME,IGRS_GUIDELINE_STATUS.SUBAREA_WARD_MAPPING_ID  FROM IGRS_WARD_PATWARI_MASTER ,IGRS_GUIDELINE_STATUS WHERE IGRS_WARD_PATWARI_MASTER.WARD_PATWARI_ID = IGRS_GUIDELINE_STATUS.WARD_PATWARI_ID AND IGRS_GUIDELINE_STATUS.TEHSIL_ID = ? AND IGRS_GUIDELINE_STATUS.GUIDELINE_ID = ?  AND IGRS_WARD_PATWARI_MASTER.WARD_OR_PATWARI = '2' AND IGRS_GUIDELINE_STATUS.CHECKER_STATUS in(1,4) AND IGRS_GUIDELINE_STATUS.MAKER_STATUS in(1,2,4) and IGRS_WARD_PATWARI_MASTER.ward_patwari_status = 'A' ORDER BY IGRS_WARD_PATWARI_MASTER.WARD_PATWARI_NAME";

public static final String PATWARI_CHECKER_HI = "SELECT  DISTINCT IGRS_GUIDELINE_STATUS.WARD_PATWARI_ID, IGRS_WARD_PATWARI_MASTER.WARD_PATWARI_NAME,IGRS_WARD_PATWARI_MASTER.H_WARD_PATWARI_NAME,IGRS_GUIDELINE_STATUS.SUBAREA_WARD_MAPPING_ID  FROM IGRS_WARD_PATWARI_MASTER ,IGRS_GUIDELINE_STATUS WHERE IGRS_WARD_PATWARI_MASTER.WARD_PATWARI_ID = IGRS_GUIDELINE_STATUS.WARD_PATWARI_ID AND IGRS_GUIDELINE_STATUS.TEHSIL_ID = ? AND IGRS_GUIDELINE_STATUS.GUIDELINE_ID = ?  AND IGRS_WARD_PATWARI_MASTER.WARD_OR_PATWARI = '2' AND IGRS_GUIDELINE_STATUS.CHECKER_STATUS in(1,4) AND IGRS_GUIDELINE_STATUS.MAKER_STATUS in(1,2,4) and IGRS_WARD_PATWARI_MASTER.ward_patwari_status = 'A' ORDER BY IGRS_WARD_PATWARI_MASTER.H_WARD_PATWARI_NAME";

public static final String COMPLETE_WARD_CHECKER ="select distinct a.WARD_PATWARI_ID, WARD_PATWARI_NAME,H_WARD_PATWARI_NAME,a.SUBAREA_WARD_MAPPING_ID from IGRS_GUIDELINE_STATUS a ," +
" IGRS_WARD_PATWARI_MASTER b where a.tehsil_id =? and a.guideline_id =?" +
" and a.ward_patwari_id = b.ward_patwari_id and " +
"b.ward_or_patwari = '1' and b.ward_patwari_status = 'A' and "+
"a.ward_patwari_id not in (SELECT  DISTINCT IGRS_GUIDELINE_STATUS.WARD_PATWARI_ID FROM IGRS_WARD_PATWARI_MASTER ,IGRS_GUIDELINE_STATUS WHERE " +
	"IGRS_WARD_PATWARI_MASTER.WARD_PATWARI_ID = IGRS_GUIDELINE_STATUS.WARD_PATWARI_ID AND IGRS_GUIDELINE_STATUS.TEHSIL_ID = ? AND IGRS_GUIDELINE_STATUS.GUIDELINE_ID = ?  AND " +
	"IGRS_WARD_PATWARI_MASTER.WARD_OR_PATWARI = '1' AND IGRS_GUIDELINE_STATUS.CHECKER_STATUS in(1,4) AND IGRS_GUIDELINE_STATUS.MAKER_STATUS in(1,2,4) and IGRS_WARD_PATWARI_MASTER.ward_patwari_status = 'A') ORDER BY WARD_PATWARI_NAME";

public static final String COMPLETE_WARD_CHECKER_HI ="select distinct a.WARD_PATWARI_ID, WARD_PATWARI_NAME,H_WARD_PATWARI_NAME,a.SUBAREA_WARD_MAPPING_ID from IGRS_GUIDELINE_STATUS a ," +
" IGRS_WARD_PATWARI_MASTER b where a.tehsil_id =? and a.guideline_id =?" +
" and a.ward_patwari_id = b.ward_patwari_id and " +
"b.ward_or_patwari = '1' and b.ward_patwari_status = 'A' and "+
"a.ward_patwari_id not in (SELECT  DISTINCT IGRS_GUIDELINE_STATUS.WARD_PATWARI_ID FROM IGRS_WARD_PATWARI_MASTER ,IGRS_GUIDELINE_STATUS WHERE " +
	"IGRS_WARD_PATWARI_MASTER.WARD_PATWARI_ID = IGRS_GUIDELINE_STATUS.WARD_PATWARI_ID AND IGRS_GUIDELINE_STATUS.TEHSIL_ID = ? AND IGRS_GUIDELINE_STATUS.GUIDELINE_ID = ?  AND " +
	"IGRS_WARD_PATWARI_MASTER.WARD_OR_PATWARI = '1' AND IGRS_GUIDELINE_STATUS.CHECKER_STATUS in(1,4) AND IGRS_GUIDELINE_STATUS.MAKER_STATUS in(1,2,4) and IGRS_WARD_PATWARI_MASTER.ward_patwari_status = 'A') ORDER BY H_WARD_PATWARI_NAME";

public static final String COMPLETE_PATWARI_CHECKER ="select distinct a.WARD_PATWARI_ID, WARD_PATWARI_NAME,h_WARD_PATWARI_NAME,a.SUBAREA_WARD_MAPPING_ID from " +
		"IGRS_GUIDELINE_STATUS a ,IGRS_WARD_PATWARI_MASTER b where a.tehsil_id =? " +
		"and a.guideline_id =? and a.ward_patwari_id = b.ward_patwari_id " +
		"and b.ward_or_patwari = '2' and b.ward_patwari_status = 'A' and a.ward_patwari_id not in" +
		" (SELECT  DISTINCT IGRS_GUIDELINE_STATUS.WARD_PATWARI_ID FROM " +
		"IGRS_WARD_PATWARI_MASTER ,IGRS_GUIDELINE_STATUS WHERE " +
		"IGRS_WARD_PATWARI_MASTER.WARD_PATWARI_ID = IGRS_GUIDELINE_STATUS.WARD_PATWARI_ID AND" +
		" IGRS_GUIDELINE_STATUS.TEHSIL_ID = ? AND IGRS_GUIDELINE_STATUS.GUIDELINE_ID = ? AND" +
		" IGRS_WARD_PATWARI_MASTER.WARD_OR_PATWARI = '2' AND IGRS_GUIDELINE_STATUS.CHECKER_STATUS in(1,4) AND IGRS_GUIDELINE_STATUS.MAKER_STATUS in(1,2,4)  " +
		"and IGRS_WARD_PATWARI_MASTER.ward_patwari_status = 'A') ORDER BY WARD_PATWARI_NAME";

public static final String COMPLETE_PATWARI_CHECKER_HI ="select distinct a.WARD_PATWARI_ID, WARD_PATWARI_NAME,h_WARD_PATWARI_NAME,a.SUBAREA_WARD_MAPPING_ID from " +
"IGRS_GUIDELINE_STATUS a ,IGRS_WARD_PATWARI_MASTER b where a.tehsil_id =? " +
"and a.guideline_id =? and a.ward_patwari_id = b.ward_patwari_id " +
"and b.ward_or_patwari = '2' and b.ward_patwari_status = 'A' and a.ward_patwari_id not in" +
" (SELECT  DISTINCT IGRS_GUIDELINE_STATUS.WARD_PATWARI_ID FROM " +
"IGRS_WARD_PATWARI_MASTER ,IGRS_GUIDELINE_STATUS WHERE " +
"IGRS_WARD_PATWARI_MASTER.WARD_PATWARI_ID = IGRS_GUIDELINE_STATUS.WARD_PATWARI_ID AND" +
" IGRS_GUIDELINE_STATUS.TEHSIL_ID = ? AND IGRS_GUIDELINE_STATUS.GUIDELINE_ID = ? AND" +
" IGRS_WARD_PATWARI_MASTER.WARD_OR_PATWARI = '2' AND IGRS_GUIDELINE_STATUS.CHECKER_STATUS in(1,4) AND IGRS_GUIDELINE_STATUS.MAKER_STATUS in(1,2,4)  " +
"and IGRS_WARD_PATWARI_MASTER.ward_patwari_status = 'A') ORDER BY h_WARD_PATWARI_NAME";

//public static final String MOHALLA_CHECKER = "SELECT  DISTINCT IGRS_GUIDELINE_STATUS.MOHALLA_VILLAGE_ID, IGRS_MOHALLA_VILLAGE_MASTER.MOHALLA_VILLAGE_NAME,IGRS_MOHALLA_VILLAGE_MASTER.H_MOHALLA_VILLAGE_NAME FROM IGRS_MOHALLA_VILLAGE_MASTER ,IGRS_GUIDELINE_STATUS WHERE " +
//"IGRS_MOHALLA_VILLAGE_MASTER.MOHALLA_VILLAGE_ID = IGRS_GUIDELINE_STATUS.MOHALLA_VILLAGE_ID AND IGRS_GUIDELINE_STATUS.WARD_PATWARI_ID = ? AND IGRS_GUIDELINE_STATUS.GUIDELINE_ID = ?  AND IGRS_GUIDELINE_STATUS.CHECKER_STATUS in(1,4) AND IGRS_GUIDELINE_STATUS.MAKER_STATUS in(1,2,4) and IGRS_MOHALLA_VILLAGE_MASTER.mohalla_village_status = 'A' ";

public static final String MOHALLA_CHECKER = "SELECT  DISTINCT IGRS_GUIDELINE_STATUS.COLONY_ID, IGRS_COLONY_MASTER.COLONY_NAME,IGRS_COLONY_MASTER.H_COLONY_NAME,IGRS_GUIDELINE_STATUS.COLONY_WARD_MAPPING_ID FROM " +
		"IGRS_COLONY_MASTER ,IGRS_GUIDELINE_STATUS WHERE IGRS_COLONY_MASTER.COLONY_ID = IGRS_GUIDELINE_STATUS.COLONY_ID AND " +
		"IGRS_GUIDELINE_STATUS.WARD_PATWARI_ID = ? AND IGRS_GUIDELINE_STATUS.GUIDELINE_ID = ?  AND " +
		"IGRS_GUIDELINE_STATUS.CHECKER_STATUS IN(1,4) AND IGRS_GUIDELINE_STATUS.MAKER_STATUS IN(1,2,4) AND IGRS_COLONY_MASTER.COLONY_STATUS = 'A' ORDER BY IGRS_COLONY_MASTER.COLONY_NAME";

public static final String MOHALLA_CHECKER_HI = "SELECT  DISTINCT IGRS_GUIDELINE_STATUS.COLONY_ID, IGRS_COLONY_MASTER.COLONY_NAME,IGRS_COLONY_MASTER.H_COLONY_NAME,IGRS_GUIDELINE_STATUS.COLONY_WARD_MAPPING_ID FROM " +
"IGRS_COLONY_MASTER ,IGRS_GUIDELINE_STATUS WHERE IGRS_COLONY_MASTER.COLONY_ID = IGRS_GUIDELINE_STATUS.COLONY_ID AND " +
"IGRS_GUIDELINE_STATUS.WARD_PATWARI_ID = ? AND IGRS_GUIDELINE_STATUS.GUIDELINE_ID = ?  AND " +
"IGRS_GUIDELINE_STATUS.CHECKER_STATUS IN(1,4) AND IGRS_GUIDELINE_STATUS.MAKER_STATUS IN(1,2,4) AND IGRS_COLONY_MASTER.COLONY_STATUS = 'A' ORDER BY IGRS_COLONY_MASTER.H_COLONY_NAME";

/*public static final String COMPLETE_MOHALLA_CHECKER ="select distinct a.MOHALLA_VILLAGE_ID, MOHALLA_VILLAGE_NAME,H_MOHALLA_VILLAGE_NAME " +
		"from IGRS_GUIDELINE_STATUS a ,IGRS_MOHALLA_VILLAGE_MASTER b " +
		"where a.WARD_PATWARI_ID =? and a.guideline_id =? and " +
		"a.MOHALLA_VILLAGE_ID = b.MOHALLA_VILLAGE_ID and b.mohalla_village_status = 'A' " +
		"and a.MOHALLA_VILLAGE_ID not in (SELECT  DISTINCT IGRS_GUIDELINE_STATUS.MOHALLA_VILLAGE_ID " +
		"FROM IGRS_MOHALLA_VILLAGE_MASTER ,IGRS_GUIDELINE_STATUS WHERE " +
		"IGRS_MOHALLA_VILLAGE_MASTER.MOHALLA_VILLAGE_ID = IGRS_GUIDELINE_STATUS.MOHALLA_VILLAGE_ID" +
		" AND IGRS_GUIDELINE_STATUS.WARD_PATWARI_ID = ? AND " +
		"IGRS_GUIDELINE_STATUS.GUIDELINE_ID = ?  AND " +
		"IGRS_GUIDELINE_STATUS.CHECKER_STATUS in(1,4) and " +
		"IGRS_GUIDELINE_STATUS.MAKER_STATUS in(1,2,4) and " +
		"IGRS_MOHALLA_VILLAGE_MASTER.mohalla_village_status = 'A') ";*/

public static final String COMPLETE_MOHALLA_CHECKER = "SELECT DISTINCT a.COLONY_ID, COLONY_NAME,H_COLONY_NAME,a.COLONY_WARD_MAPPING_ID FROM IGRS_GUIDELINE_STATUS a ,IGRS_COLONY_MASTER b WHERE " +
		"a.WARD_PATWARI_ID =? AND a.guideline_id =? AND a.COLONY_ID = b.COLONY_ID AND b.COLONY_STATUS = 'A' AND a.COLONY_ID NOT IN" +
		" (SELECT  DISTINCT IGRS_GUIDELINE_STATUS.COLONY_ID FROM IGRS_COLONY_MASTER ,IGRS_GUIDELINE_STATUS WHERE " +
		"IGRS_COLONY_MASTER.COLONY_ID = IGRS_GUIDELINE_STATUS.COLONY_ID AND IGRS_GUIDELINE_STATUS.WARD_PATWARI_ID = ? AND " +
		"IGRS_GUIDELINE_STATUS.GUIDELINE_ID = ?  AND IGRS_GUIDELINE_STATUS.CHECKER_STATUS IN(1,4) AND IGRS_GUIDELINE_STATUS.MAKER_STATUS IN(1,2,4) " +
		"AND IGRS_COLONY_MASTER.COLONY_STATUS = 'A') ORDER BY COLONY_NAME";

public static final String COMPLETE_MOHALLA_CHECKER_HI = "SELECT DISTINCT a.COLONY_ID, COLONY_NAME,H_COLONY_NAME,a.COLONY_WARD_MAPPING_ID FROM IGRS_GUIDELINE_STATUS a ,IGRS_COLONY_MASTER b WHERE " +
"a.WARD_PATWARI_ID =? AND a.guideline_id =? AND a.COLONY_ID = b.COLONY_ID AND b.COLONY_STATUS = 'A' AND a.COLONY_ID NOT IN" +
" (SELECT  DISTINCT IGRS_GUIDELINE_STATUS.COLONY_ID FROM IGRS_COLONY_MASTER ,IGRS_GUIDELINE_STATUS WHERE " +
"IGRS_COLONY_MASTER.COLONY_ID = IGRS_GUIDELINE_STATUS.COLONY_ID AND IGRS_GUIDELINE_STATUS.WARD_PATWARI_ID = ? AND " +
"IGRS_GUIDELINE_STATUS.GUIDELINE_ID = ?  AND IGRS_GUIDELINE_STATUS.CHECKER_STATUS IN(1,4) AND IGRS_GUIDELINE_STATUS.MAKER_STATUS IN(1,2,4) " +
"AND IGRS_COLONY_MASTER.COLONY_STATUS = 'A') ORDER BY H_COLONY_NAME";

//public static final String GUIDELINE_VIEW_APPROVAL = "SELECT DISTINCT  p.property_type_name,  p1.property_type_l1_name, p2.property_type_l2_name, g2.guideline_value,g2.guideline_avg_value, g2.pct_increment,m.colony_name,g2.property_type_id,g2.property_type_l1_id, g2.property_type_l2_id ,u.uom_name,u.uom_id,p.h_property_type_name,  p1.h_property_type_l1_name,p2.h_property_type_l2_name,m.h_colony_name,u.h_uom_name FROM IGRS_GUIDELINE_PARENT_TEMP g LEFT OUTER JOIN IGRS_GUIDELINE_CHILD1_TEMP g1 ON g.guideline_id = g1.guideline_id LEFT OUTER JOIN IGRS_GUIDElINE_CHILD2_TEMP g2 ON g1.guideline_child1_id = g2.guideline_child1_id LEFT OUTER JOIN IGRS_COLONY_MASTER m ON g1.colony_id = m.colony_id LEFT OUTER JOIN IGRS_PROPERTY_TYPE_MASTER p ON g2.property_type_id = p.property_type_id LEFT OUTER JOIN IGRS_PROP_TYPE_L1_MASTER p1 ON p1.property_type_l1_id = g2.property_type_l1_id LEFT OUTER JOIN IGRS_PROP_TYPE_L2_MASTER p2 ON p2.property_type_l2_id = g2.property_type_l2_id LEFT OUTER JOIN IGRS_UOM_MASTER u ON g2.Unit_of_measure_id = u.uom_id WHERE g.guideline_id = ? AND g1.colony_id = ? AND g1.status_flag = 1 ORDER BY g2.PROPERTY_TYPE_ID,g2.PROPERTY_TYPE_L1_ID";

public static final String GUIDELINE_VIEW_APPROVAL =" SELECT DISTINCT p.property_type_name,p1.prop_type_l1_name,p2.property_type_l2_name, c2.guideline_value,m.COLONY_ID,m.COLONY_NAME, c1.area_type_id,c2.property_type_id,c2.property_type_l1_id, c2.property_type_l2_id , u.uom_name, u.uom_id, p.h_property_type_name, p1.h_prop_type_l1_name,p2.h_property_type_l2_name, m.H_COLONY_NAME,u.h_uom_name FROM IGRS_PROPERTY_TYPE_MASTER p ,IGRS_PROP_TYPE_L1_MASTER_DUMMY p1,IGRS_PROP_TYPE_L2_MASTER_DUMMY p2,IGRS_GUIDELINE_CHILD2_TEMP c2,IGRS_GUIDELINE_CHILD1_TEMP c1,IGRS_GUIDELINE_PARENT_TEMP c,IGRS_COLONY_MASTER m, IGRS_UOM_MASTER u WHERE c.guideline_id = ? AND p1.DISPLAY_FLAG_GUIDLN='Y' AND (p2.DISPLAY_FLAG IS NULL OR (p2.DISPLAY_FLAG IN('B','G')))AND c1.COLONY_WARD_MAPPING_ID = ?  AND c1.COLONY_ID = ? AND c.guideline_id = c1.guideline_id AND c1.guideline_child1_id = c2.guideline_child1_id AND c1.COLONY_ID  = m.COLONY_ID AND c2.property_type_id = p.property_type_id AND c2.property_type_l1_id = p1.prop_type_l1_id(+) AND  c2.property_type_l2_id = p2.property_type_l2_id(+) AND p1.UOM_ID = u.uom_id ORDER BY c2.property_type_id,c2.property_type_l1_id, c2.property_type_l2_id";

//public static final String GET_GUIDELINE_VIEW_FINAL_DETAILS = "select distinct  p.property_type_name,  p1.property_type_l1_name, p2.property_type_l2_name, g2.guideline_value,g2.guideline_avg_value,g2.pct_increment,m.colony_name,g2.property_type_id, g2.property_type_l1_id, g2.property_type_l2_id ,u.uom_name,u.uom_id FROM IGRS_GUIDELINE_MASTER_PARENT g left outer join IGRS_GUIDELINE_MASTER_CHILD1 g1 on g.guideline_id = g1.guideline_id left outer join IGRS_GUIDELINE_MASTER_CHILD2 g2 on g1.guideline_child1_id = g2.guideline_child1_id left outer join IGRS_COLONY_MASTER m on g1.colony_id = m.colony_id left outer join IGRS_PROPERTY_TYPE_MASTER p on g2.property_type_id = p.property_type_id left outer join IGRS_PROP_TYPE_L1_MASTER p1 on p1.property_type_l1_id = g2.property_type_l1_id left outer join IGRS_PROP_TYPE_L2_MASTER p2 on p2.property_type_l2_id = g2.property_type_l2_id left outer join IGRS_UOM_MASTER u on g2.Unit_of_measure_id = u.uom_id where g.guideline_id = ? and g1.colony_id = ? and g1.status_flag = 3 order by g2.PROPERTY_TYPE_ID,g2.PROPERTY_TYPE_L1_ID";

public static final String GET_GUIDELINE_VIEW_FINAL_DETAILS = "SELECT DISTINCT p.property_type_name,p1.prop_type_l1_name,p2.property_type_l2_name, c2.guideline_value,m.COLONY_ID,m.COLONY_NAME, c1.area_type_id,c2.property_type_id,c2.property_type_l1_id, c2.property_type_l2_id , u.uom_name, u.uom_id, p.h_property_type_name, p1.h_prop_type_l1_name,p2.h_property_type_l2_name, m.H_COLONY_NAME,u.h_uom_name FROM IGRS_PROPERTY_TYPE_MASTER p ,IGRS_PROP_TYPE_L1_MASTER_DUMMY p1,IGRS_PROP_TYPE_L2_MASTER_DUMMY p2,IGRS_GUIDELINE_MASTER_CHILD2 c2,IGRS_GUIDELINE_MASTER_CHILD1 c1,IGRS_GUIDELINE_MASTER_PARENT c,IGRS_COLONY_MASTER m, IGRS_UOM_MASTER u WHERE c.guideline_id = ? AND p1.DISPLAY_FLAG_GUIDLN='Y' AND (p2.DISPLAY_FLAG IS NULL OR (p2.DISPLAY_FLAG IN('B','G')))AND c1.COLONY_WARD_MAPPING_ID = ?  AND c1.COLONY_ID = ? AND c.guideline_id = c1.guideline_id AND c1.guideline_child1_id = c2.guideline_child1_id AND c1.COLONY_ID  = m.COLONY_ID AND c2.property_type_id = p.property_type_id AND c2.property_type_l1_id = p1.prop_type_l1_id(+) AND  c2.property_type_l2_id = p2.property_type_l2_id(+) AND p1.UOM_ID = u.uom_id ORDER BY c2.property_type_id,c2.property_type_l1_id, c2.property_type_l2_id";

//public static final String GUIDELINE_VIEW_APPROVAL_DRAFT = "select distinct  p.property_type_name,  p1.property_type_l1_name, p2.property_type_l2_name, g2.guideline_value,g2.guideline_avg_value, g2.pct_increment,m.colony_name,g2.property_type_id, g2.property_type_l1_id, g2.property_type_l2_id ,u.uom_name,u.uom_id,p.h_property_type_name,  p1.h_property_type_l1_name,p2.h_property_type_l2_name,m.h_colony_name,u.h_uom_name FROM IGRS_GUIDELINE_PARENT_TEMP g left outer join IGRS_GUIDELINE_CHILD1_TEMP g1 on g.guideline_id = g1.guideline_id left outer join IGRS_GUIDElINE_CHILD2_TEMP g2 on g1.guideline_child1_id = g2.guideline_child1_id left outer join IGRS_COLONY_MASTER m on g1.colony_id = m.colony_id left outer join IGRS_PROPERTY_TYPE_MASTER p on g2.property_type_id = p.property_type_id left outer join IGRS_PROP_TYPE_L1_MASTER p1 on p1.property_type_l1_id = g2.property_type_l1_id left outer join IGRS_PROP_TYPE_L2_MASTER p2 on p2.property_type_l2_id = g2.property_type_l2_id left outer join IGRS_UOM_MASTER u on g2.Unit_of_measure_id = u.uom_id where g.guideline_id = ? and g1.colony_id = ? order by g2.PROPERTY_TYPE_ID,g2.PROPERTY_TYPE_L1_ID";

public static final String GUIDELINE_VIEW_APPROVAL_DRAFT = "SELECT DISTINCT p.property_type_name,p1.prop_type_l1_name,p2.property_type_l2_name, c2.guideline_value,m.COLONY_ID,m.COLONY_NAME, c1.area_type_id,c2.property_type_id,c2.property_type_l1_id, c2.property_type_l2_id , u.uom_name, u.uom_id, p.h_property_type_name, p1.h_prop_type_l1_name,p2.h_property_type_l2_name, m.H_COLONY_NAME,u.h_uom_name FROM IGRS_PROPERTY_TYPE_MASTER p ,IGRS_PROP_TYPE_L1_MASTER_DUMMY p1,IGRS_PROP_TYPE_L2_MASTER_DUMMY p2,IGRS_GUIDELINE_CHILD2_TEMP c2,IGRS_GUIDELINE_CHILD1_TEMP c1,IGRS_GUIDELINE_PARENT_TEMP c,IGRS_COLONY_MASTER m, IGRS_UOM_MASTER u WHERE c.guideline_id = ? AND p1.DISPLAY_FLAG_GUIDLN='Y' AND (p2.DISPLAY_FLAG IS NULL OR (p2.DISPLAY_FLAG IN('B','G')))AND c1.COLONY_WARD_MAPPING_ID = ?  AND c1.COLONY_ID = ? AND c.guideline_id = c1.guideline_id AND c1.guideline_child1_id = c2.guideline_child1_id AND c1.COLONY_ID  = m.COLONY_ID AND c2.property_type_id = p.property_type_id AND c2.property_type_l1_id = p1.prop_type_l1_id(+) AND  c2.property_type_l2_id = p2.property_type_l2_id(+) AND p1.UOM_ID = u.uom_id ORDER BY c2.property_type_id,c2.property_type_l1_id";
		
public static final String GUIDELINE_VIEW_APPROVAL_FINAL = "select distinct  p.property_type_name,  p1.property_type_l1_name, p2.property_type_l2_name, g2.guideline_value,g2.guideline_avg_value, g2.pct_increment,m.colony_name,g2.property_type_id, g2.property_type_l1_id, g2.property_type_l2_id ,u.uom_name,u.uom_id FROM IGRS_GUIDELINE_MASTER_PARENT g left outer join IGRS_GUIDELINE_MASTER_CHILD1 g1 on g.guideline_id = g1.guideline_id left outer join IGRS_GUIDELINE_MASTER_CHILD2 g2 on g1.guideline_child1_id = g2.guideline_child1_id left outer join IGRS_COLONY_MASTER m on g1.colony_id = m.colony_id left outer join IGRS_PROPERTY_TYPE_MASTER p on g2.property_type_id = p.property_type_id left outer join IGRS_PROP_TYPE_L1_MASTER p1 on p1.property_type_l1_id = g2.property_type_l1_id left outer join IGRS_PROP_TYPE_L2_MASTER p2 on p2.property_type_l2_id = g2.property_type_l2_id left outer join IGRS_UOM_MASTER u on g2.Unit_of_measure_id = u.uom_id where g.guideline_id = ? and g1.colony_id = ?";


public static final String SHOW_DRAFT_VERSION_CHECKER = "select distinct a.version_no, a.duration_from,a.duration_to ,b.checker_status from IGRS_GUIDELINE_PARENT_TEMP a, IGRS_GUIDELINE_STATUS b where a.financial_year=? and a.district_id = ? and  b.checker_status = 'D' and a.guideline_id = b.guideline_id ";

public static final String VALIDATE_DRAFT_PROCEDURE = "CALL "
	+ "igrs_validate_draft_guideline(?,?,?,?,?,to_date(?,'dd/mm/yy'),to_date(?,'dd/mm/yy'),?,?)";

public static final String UPDATE_DRAFT_PROCEDURE = "CALL "
	+ "igrs_update_temp_guideline(?,?,to_date(?,'dd/mm/yy'),to_date(?,'dd/mm/yy'),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,to_date(?,'dd/mm/yy'),?,?,?,?)";

public static final String INSERT_FINAL_PROCEDURE = "CALL "
	+ "igrs_insert_final_guideline(?,?,?,?,to_date(?,'dd/mm/yy'),to_date(?,'dd/mm/yy'),?,?,?,?,to_date(?,'dd/mm/yy'),to_date(?,'dd/mm/yy'),?,?,?,?,?,?,?,?,?,to_date(?,'dd/mm/yy'),?,?,?)";

public static final String UPDATE_GUIDELINE_STATUS_CHECKER = "update IGRS_GUIDELINE_STATUS set CHECKER_STATUS =? WHERE COLONY_ID = ? AND GUIDELINE_ID = ?";

public static final String UPDATE_GUIDELINE_STATUS_CHECKER_ALL = "update IGRS_GUIDELINE_STATUS set CHECKER_STATUS =1 WHERE GUIDELINE_ID = ? and maker_status = 4";

public static final String UPDATE_GUIDELINE_STATUS_CHECKER_FINAL = "update IGRS_GUIDELINE_STATUS set CHECKER_STATUS = 'F' WHERE MOHALLA_VILLAGE_ID = ? AND GUIDELINE_ID = ?";

public static final String VALIDATE_FINAL_PROCEDURE = "Call "
	+"igrs_validate_final_guideline(?,?,?,?,?,to_date(?,'dd/mm/yy'),to_date(?,'dd/mm/yy'),?,?)";


public static final String UPDATE_FINAL_PROCEDURE = "CALL "
	+"igrs_update_final_guideline(?,?,to_date(?,'dd/mm/yy'),to_date(?,'dd/mm/yy'),?,?,?,?,to_date(?,'dd/mm/yy'),to_date(?,'dd/mm/yy'),?,?,?,?,?,?,?,?,?,to_date(?,'dd/mm/yy'),?,?,?,?)";


public static final String COPY_MASTER_DATA = "CALL "
	+"igrs_copy_master_data(?,?,?,?,?)";

public static final String COPY_DRAFT_DATA = "CALL "
	+"igrs_copy_draft_data(?,?,?,?,?)";


//public static final String COPY_DRAFT_DATA = "CALL"
	//+"igrs_copy_draft_data(?,?,?,?)";

/*public static final String MOHALLA_DETAILS_MODIFY = "SELECT distinct p.property_type_name,p1.property_type_l1_name,p2.property_type_l2_name," +
		"c2.guideline_value,m.mohalla_village_id,m.mohalla_village_name, " +
		"c1.area_type_id,c2.property_type_id,c2.property_type_l1_id, c2.property_type_l2_id , u.uom_name, u.uom_id, " +
		" p.h_property_type_name,p1.h_property_type_l1_name,p2.h_property_type_l2_name, " +
		" m.h_mohalla_village_name,u.h_uom_name " +
		"FROM IGRS_PROPERTY_TYPE_MASTER p ,IGRS_PROP_TYPE_L1_MASTER p1, " +
		"IGRS_PROP_TYPE_L2_MASTER p2,igrs_guideline_child2_temp c2,igrs_guideline_child1_temp c1, " +
		"igrs_guideline_parent_temp c,IGRS_MOHALLA_VILLAGE_MASTER m, igrs_uom_master u " +
		"where c.guideline_id = ? and c1.mohalla_village_id = ? " +
		"and c.guideline_id = c1.guideline_id and c1.guideline_child1_id = c2.guideline_child1_id " +
		"and c1.mohalla_village_id  = m.mohalla_village_id and c2.property_type_id = p.property_type_id " +
		"and c2.property_type_l1_id = p1.property_type_l1_id(+) " +
		"and c2.property_type_l2_id = p2.property_type_l2_id(+)" +
		"and c2.unit_of_measure_id = u.uom_id order by c2.property_type_id,c2.property_type_l1_id";*/

public static final String MOHALLA_DETAILS_MODIFY = "SELECT DISTINCT p.property_type_name,p1.prop_type_l1_name,p2.property_type_l2_name, c2.guideline_value,m.COLONY_ID,m.COLONY_NAME," +
		"  c1.area_type_id,c2.property_type_id,c2.property_type_l1_id, c2.property_type_l2_id , u.uom_name, u.uom_id, p.h_property_type_name," +
		"	p1.h_prop_type_l1_name,p2.h_property_type_l2_name, m.H_COLONY_NAME,u.h_uom_name FROM IGRS_PROPERTY_TYPE_MASTER p ,IGRS_PROP_TYPE_L1_MASTER_DUMMY p1," +
		"	IGRS_PROP_TYPE_L2_MASTER_DUMMY p2,igrs_guideline_child2_temp c2,igrs_guideline_child1_temp c1,igrs_guideline_parent_temp c,IGRS_COLONY_MASTER m," +
		" 	IGRS_UOM_MASTER u WHERE c.guideline_id = ? AND p1.DISPLAY_FLAG_GUIDLN='Y' AND (p2.DISPLAY_FLAG IS NULL OR (p2.DISPLAY_FLAG IN('B','G')))AND" +
		" 	c1.COLONY_WARD_MAPPING_ID = ?  AND c1.COLONY_ID = ? AND c.guideline_id = c1.guideline_id AND c1.guideline_child1_id = c2.guideline_child1_id AND" +
		" 	c1.COLONY_ID  = m.COLONY_ID AND c2.property_type_id = p.property_type_id AND c2.property_type_l1_id = p1.prop_type_l1_id(+) AND " +
		"	c2.property_type_l2_id = p2.property_type_l2_id(+) AND p1.UOM_ID = u.uom_id ORDER BY c2.property_type_id,c2.property_type_l1_id,c2.property_type_l2_id";

public static final String MOHALLA_DETAILS_FINAL = "SELECT distinct p.property_type_name,p1.property_type_l1_name," +
		"p2.property_type_l2_name, c2.guideline_value,m.mohalla_village_id,m.mohalla_village_name," +
		" c1.area_type_id,c2.property_type_id,c2.property_type_l1_id, c2.property_type_l2_id , " +
		"u.uom_name, u.uom_id FROM " +
		"IGRS_PROPERTY_TYPE_MASTER p ,IGRS_PROP_TYPE_L1_MASTER p1,IGRS_PROP_TYPE_L2_MASTER p2," +
		"IGRS_GUIDELINE_MASTER_CHILD2 c2,IGRS_GUIDELINE_MASTER_CHILD1 c1, IGRS_GUIDELINE_MASTER_PARENT c," +
		"IGRS_MOHALLA_VILLAGE_MASTER m, igrs_uom_master u where c.guideline_id = ?" +
		" and c1.mohalla_village_id = ? " +
		"and c.guideline_id = c1.guideline_id and c1.guideline_child1_id = c2.guideline_child1_id and " +
		"c1.mohalla_village_id  = m.mohalla_village_id and c2.property_type_id = p.property_type_id and" +
		" c2.property_type_l1_id = p1.property_type_l1_id(+)and " +
		"c2.property_type_l2_id = p2.property_type_l2_id(+) and " +
		"c2.unit_of_measure_id = u.uom_id order by c2.property_type_id,c2.property_type_l1_id,c2.property_type_l2_id";

public static final String UPDATE_END_DATE_MASTER = "UPDATE IGRS_GUIDELINE_MASTER_PARENT SET DURATION_TO = TO_DATE(?,'dd/mm/yy') WHERE GUIDELINE_ID = ?";

public static final String UPDATE_END_DATE = "UPDATE IGRS_GUIDELINE_PARENT_TEMP SET DURATION_TO = TO_DATE(?,'dd/mm/yy') WHERE GUIDELINE_ID = ?";

public static final String NOT_VALIDATED_DATA = "SELECT TEHSIL_NAME, WARD_PATWARI_NAME,COLONY_NAME FROM IGRS_TEHSIL_MASTER T, IGRS_WARD_PATWARI_MASTER W, IGRS_COLONY_MASTER M ," +
		"IGRS_GUIDELINE_CHILD1_TEMP G WHERE G.GUIDELINE_ID =? AND G.VALIDATE_STATUS = 1 AND T.TEHSIL_ID = G.TEHSIL_ID AND" +
		" W.WARD_PATWARI_ID = G.WARD_PATWARI_ID AND M.COLONY_ID = G.COLONY_ID AND W.WARD_PATWARI_STATUS='A' AND M.COLONY_STATUS='A' AND T.TEHSIL_STATUS='A'";

public static final String NOT_ENTERED_DATA = "SELECT TEHSIL_NAME, WARD_PATWARI_NAME,COLONY_NAME FROM IGRS_TEHSIL_MASTER T, IGRS_WARD_PATWARI_MASTER W,IGRS_COLONY_MASTER M, IGRS_GUIDELINE_STATUS G WHERE G.GUIDELINE_ID = ? AND G.MAKER_STATUS = 1 " +
		"AND T.TEHSIL_ID = G.TEHSIL_ID AND W.WARD_PATWARI_ID = G.WARD_PATWARI_ID AND M.COLONY_ID = G.COLONY_ID AND W.WARD_PATWARI_STATUS='A' AND M.COLONY_STATUS='A' AND T.TEHSIL_STATUS='A'";

public static final String VIEW_VERSION_TEMP_DRAFT = "SELECT  DISTINCT p.GUIDELINE_ID, p.DURATION_FROM, p.DURATION_TO FROM IGRS_GUIDELINE_PARENT_TEMP p, IGRS_GUIDELINE_CHILD1_TEMP c " +
		"WHERE p.FINANCIAL_YEAR = ? AND p.DISTRICT_ID = ? AND " +
		"p.PUBLISH_STATUS = ? AND c.STATUS_FLAG = ? AND" +
		" p.GUIDELINE_ID = c.GUIDELINE_ID ORDER BY GUIDELINE_ID desc";

public static final String VIEW_VERSION_FINAL = "SELECT  DISTINCT p.GUIDELINE_ID, p.DURATION_FROM, p.DURATION_TO FROM IGRS_GUIDELINE_MASTER_PARENT p, " +
		"IGRS_GUIDELINE_MASTER_CHILD1 c WHERE " +
		"p.FINANCIAL_YEAR = ? AND p.DISTRICT_ID = ? AND " +
		"c.STATUS_FLAG = ? AND p.GUIDELINE_ID = c.GUIDELINE_ID ORDER BY GUIDELINE_ID desc";


/*
 * 
 *VIEW
 */

public static final String GUIDELINE_VIEW_TEMP= "SELECT distinct p.property_type_id,p.property_type_name,p1.property_type_l1_id,p1.property_type_l1_name," +
		" p2.property_type_l2_id,p2.property_type_l2_name,c2.guideline_value,m.mohalla_village_id,m.mohalla_village_name, " +
		"u.uom_name,u.uom_id, c2.PROPERTY_TYPE_ID, c2.PROPERTY_TYPE_L1_ID, c2.PROPERTY_TYPE_L2_ID FROM " +
		"IGRS_PROPERTY_TYPE_MASTER p ,IGRS_PROP_TYPE_L1_MASTER p1,IGRS_PROP_TYPE_L2_MASTER p2," +
		"igrs_guideline_child2_temp c2,igrs_guideline_child1_temp c1,igrs_guideline_parent_temp c," +
		"igrs_mohalla_village_master m, igrs_uom_master u where c.guideline_id = ? " +
		"and c1.TEHSIL_ID = ? and c1.WARD_PATWARI_ID = ? and c1.mohalla_village_id = ?" +
		" and c.guideline_id = c1.guideline_id and " +
		"c1.guideline_child1_id = c2.guideline_child1_id and" +
		" c1.mohalla_village_id  = m.mohalla_village_id and " +
		"c2.property_type_id = p.property_type_id and" +
		" c2.property_type_l1_id = p1.property_type_l1_id(+) and " +
		"c2.property_type_l2_id = p2.property_type_l2_id(+) and " +
		"c2.unit_of_measure_id = u.uom_id order by c2.property_type_id, c2.property_type_l1_id";

public static final String GUIDELINE_VIEW_FINAL= "SELECT distinct p.property_type_id,p.property_type_name,p1.property_type_l1_id,p1.property_type_l1_name," +
" p2.property_type_l2_id, p2.property_type_l2_name,c2.guideline_value,m.mohalla_village_id,m.mohalla_village_name, " +
"u.uom_name,u.uom_id, c2.PROPERTY_TYPE_ID, c2.PROPERTY_TYPE_L1_ID, c2.PROPERTY_TYPE_L2_ID FROM " +
"IGRS_PROPERTY_TYPE_MASTER p ,IGRS_PROP_TYPE_L1_MASTER p1,IGRS_PROP_TYPE_L2_MASTER p2," +
"igrs_guideline_master_child2 c2,igrs_guideline_master_child1 c1,igrs_guideline_master_parent c," +
"igrs_mohalla_village_master m, igrs_uom_master u where c.guideline_id = ? " +
"and c1.TEHSIL_ID = ? and c1.WARD_PATWARI_ID = ? and c1.mohalla_village_id = ?" +
" and c.guideline_id = c1.guideline_id and " +
"c1.guideline_child1_id = c2.guideline_child1_id and" +
" c1.mohalla_village_id  = m.mohalla_village_id and " +
"c2.property_type_id = p.property_type_id and" +
" c2.property_type_l1_id = p1.property_type_l1_id(+) and " +
"c2.property_type_l2_id = p2.property_type_l2_id(+) and " +
"c2.unit_of_measure_id = u.uom_id order by c2.property_type_id, c2.property_type_l1_id";


/*
 * SUBCLAUSES
 */

public static final String MAP_SUBCLAUSES = "SELECT a.SUB_CLAUSE_ID, a.SUB_CLAUSE_NAME FROM IGRS_SUB_CLAUSE_MASTER a,IGRS_SUBCLAUSE_PROP_MAPPING b, " +
		"IGRS_SUBCLAUSE_DISTRCT_MAPPING c WHERE b.PROPERTY_TYPE_ID = ? AND " +
		"b.PROPERTY_TYPE_L1_ID = ? AND nvl(b.PROPERTY_TYPE_L2_ID,null) = nvl(?,null) AND " +
		"c.DISTRICT_ID =? AND c.TEHSIL_ID = ? AND c.WARD_PATWARI_ID = ? AND " +
		"c.MOHALLA_VILLAGE_ID = ? AND a.SUB_CLAUSE_ID = b.SUB_CLAUSE_ID AND " +
		"b.SUB_CLAUSE_ID = c.SUB_CLAUSE_ID";

public static final String UNMAP_SUBCLAUSES = "SELECT a.SUB_CLAUSE_ID, a.SUB_CLAUSE_NAME FROM IGRS_SUB_CLAUSE_MASTER a,IGRS_SUBCLAUSE_PROP_MAPPING b" +
		" WHERE b.PROPERTY_TYPE_ID = ? AND b.PROPERTY_TYPE_L1_ID = ? AND" +
		" nvl(b.PROPERTY_TYPE_L2_ID,null) = nvl(?,null) AND" +
		" a.SUB_CLAUSE_ID = b.SUB_CLAUSE_ID AND" +
		" a.SUB_CLAUSE_ID NOT IN(SELECT SUB_CLAUSE_ID FROM IGRS_SUBCLAUSE_DISTRCT_MAPPING WHERE DISTRICT_ID = ?)"; 


public static final String MAP_TEST = "SELECT DISTINCT a.PROPERTY_TYPE_ID, a.PROPERTY_TYPE_L1_ID,a.PROPERTY_TYPE_L2_ID, " +
		"b.SUB_CLAUSE_ID, b.SUB_CLAUSE_NAME FROM IGRS_SUBCLAUSE_PROP_MAPPING a, IGRS_SUB_CLAUSE_MASTER b," +
		" IGRS_SUBCLAUSE_DISTRCT_MAPPING c WHERE c.DISTRICT_ID = ? AND c.TEHSIL_ID = ? AND c.WARD_PATWARI_ID = ? AND " +
		"c.MOHALLA_VILLAGE_ID = ? AND a.STATUS = 'A' AND a.SUB_CLAUSE_ID = b.SUB_CLAUSE_ID AND" +
		" b.SUB_CLAUSE_ID = c.SUB_CLAUSE_ID ORDER BY a.PROPERTY_TYPE_ID,a.PROPERTY_TYPE_L1_ID,b.SUB_CLAUSE_ID";

public static final String UNMAP_TEST = "SELECT DISTINCT a.PROPERTY_TYPE_ID, a.PROPERTY_TYPE_L1_ID,a.PROPERTY_TYPE_L2_ID, " +
		"b.SUB_CLAUSE_ID, b.SUB_CLAUSE_NAME FROM IGRS_SUBCLAUSE_PROP_MAPPING a, " +
		"IGRS_SUB_CLAUSE_MASTER b WHERE  a.STATUS = 'A' AND a.SUB_CLAUSE_ID not in" +
		"(SELECT SUB_CLAUSE_ID FROM IGRS_SUBCLAUSE_DISTRCT_MAPPING c WHERE c.DISTRICT_ID = ? AND c.TEHSIL_ID = ? AND c.WARD_PATWARI_ID = ? AND c.MOHALLA_VILLAGE_ID = ? )" +
		"AND a.SUB_CLAUSE_ID = b.SUB_CLAUSE_ID ORDER BY a.PROPERTY_TYPE_ID,a.PROPERTY_TYPE_L1_ID,b.SUB_CLAUSE_ID";

public static final String SUBCLAUSE = "SELECT a.SUB_CLAUSE_ID,a.SUB_CLAUSE_NAME, a.SUB_CLAUSE_DESC, a.DEFINE_FOR_ALL_DISTRICT FROM IGRS_SUB_CLAUSE_MASTER a,IGRS_SUBCLAUSE_PROP_MAPPING b " +
		"WHERE b.PROPERTY_TYPE_ID = ? AND b.PROPERTY_TYPE_L1_ID = ? AND " +
		"nvl(b.PROPERTY_TYPE_L2_ID,null) = nvl(?,null)AND a.SUB_CLAUSE_STATUS = 'A' and " +
		"a.SUB_CLAUSE_ID = b.SUB_CLAUSE_ID order by a.SUB_CLAUSE_ID";

public static final String SUBCLAUSE_NULL = "SELECT a.SUB_CLAUSE_ID, a.SUB_CLAUSE_NAME, a.SUB_CLAUSE_DESC, a.DEFINE_FOR_ALL_DISTRICT FROM IGRS_SUB_CLAUSE_MASTER a,IGRS_SUBCLAUSE_PROP_MAPPING b " +
"WHERE b.PROPERTY_TYPE_ID = ? AND b.PROPERTY_TYPE_L1_ID = ? AND " +
"b.PROPERTY_TYPE_L2_ID is null AND a.SUB_CLAUSE_STATUS = 'A' and " +
"a.SUB_CLAUSE_ID = b.SUB_CLAUSE_ID order by a.SUB_CLAUSE_ID";


public static final String SUBCLAUSE_NOT_CLICKED = "SELECT a.SUB_CLAUSE_ID FROM IGRS_SUB_CLAUSE_MASTER a,IGRS_SUBCLAUSE_PROP_MAPPING b " +
"WHERE b.PROPERTY_TYPE_ID = ? AND b.PROPERTY_TYPE_L1_ID = ? AND " +
"nvl(b.PROPERTY_TYPE_L2_ID,null) = nvl(?,null)AND a.SUB_CLAUSE_STATUS = 'A' and a.define_for_all_district = 'Y' and " +
"a.SUB_CLAUSE_ID = b.SUB_CLAUSE_ID order by a.SUB_CLAUSE_ID";

public static final String SUBCLAUSE_NOT_CLICKED_NULL = "SELECT a.SUB_CLAUSE_ID FROM IGRS_SUB_CLAUSE_MASTER a,IGRS_SUBCLAUSE_PROP_MAPPING b " +
"WHERE b.PROPERTY_TYPE_ID = ? AND b.PROPERTY_TYPE_L1_ID = ? AND " +
"b.PROPERTY_TYPE_L2_ID is null AND a.SUB_CLAUSE_STATUS = 'A' and a.define_for_all_district = 'Y' and " +
"a.SUB_CLAUSE_ID = b.SUB_CLAUSE_ID order by a.SUB_CLAUSE_ID";

public static final String SUBCLAUSE_TEST = "SELECT DISTINCT a.PROPERTY_TYPE_ID, a.PROPERTY_TYPE_L1_ID,a.PROPERTY_TYPE_L2_ID," +
		" b.SUB_CLAUSE_ID, b.SUB_CLAUSE_DESC ,b.DEFINE_FOR_ALL_DISTRICT FROM " +
		"IGRS_SUBCLAUSE_PROP_MAPPING a, IGRS_SUB_CLAUSE_MASTER b WHERE a.STATUS = 'A' AND" +
		" a.SUB_CLAUSE_ID = b.SUB_CLAUSE_ID ORDER BY a.PROPERTY_TYPE_ID,a.PROPERTY_TYPE_L1_ID,b.SUB_CLAUSE_ID";

public static final String SUB_PROP = "SELECT DISTINCT a.PROPERTY_TYPE_ID, a.PROPERTY_TYPE_L1_ID,a.PROPERTY_TYPE_L2_ID FROM " +
		"IGRS_SUBCLAUSE_PROP_MAPPING a ORDER BY " +
		"a.PROPERTY_TYPE_ID,a.PROPERTY_TYPE_L1_ID";


public static final String SUBCLAUSE_APPROVED = "INSERT INTO IGRS_SUBCLAUSE_MASTER_APPROVED(ID, DISTRICT_ID, TEHSIL_ID, WARD_PATWARI_ID, MOHALLA_VILLAGE_ID, " +
		"PROPERTY_TYPE_ID, PROPERTY_TYPE_L1_ID, PROPERTY_TYPE_L2_ID, SUBCLAUSE_ID, STATUS, GUIDELINE_ID) VALUES" +
		"(IGRS_SUBCLAUSE_APP_SEQ.nextval,?,?,?,?,?,?,?,?,?,?)";


/*public static final String SUBCLAUSE_DERIVED1 = "INSERT ALL INTO IGRS_SUBCLAUSE_DERIVED_MASTER (ID, DISTRICT_ID, TEHSIL_ID, WARD_PATWARI_ID, MOHALLA_VILLAGE_ID, PROPERTY_TYPE_ID, PROPERTY_TYPE_L1_ID, PROPERTY_TYPE_L2_ID, STATUS, GUIDELINE_ID, MAPPED)" +
		"VALUES" +
		"(IGRS_SUB_DERIVE_SEQ.nextval, DISTRICT_ID, TEHSIL_ID, WARD_PATWARI_ID, MOHALLA_VILLAGE_ID," +
		" PROPERTY_TYPE_ID, PROPERTY_TYPE_L1_ID, PROPERTY_TYPE_L2_ID, STATUS, ?, 'Y') " +
		"SELECT f.DISTRICT_ID,f.TEHSIL_ID, f.WARD_PATWARI_ID, f.MOHALLA_VILLAGE_ID, f.PROPERTY_TYPE_ID, f.PROPERTY_TYPE_L1_ID, f.PROPERTY_TYPE_L2_ID, f.SUB_CLAUSE_ID,f.STATUS FROM IGRS_SUB_CLAUSE_MASTER_FINAL f, IGRS_SUB_CLAUSE_MASTER m " +
		"WHERE f.DISTRICT_ID = ? AND m.SUB_CLAUSE_STATUS = 'A' AND f.SUB_CLAUSE_ID = m.SUB_CLAUSE_ID";

public static final String SUBCLAUSE_DERIVED2 = "INSERT ALL INTO IGRS_SUBCLAUSE_DERIVED_MASTER (ID, DISTRICT_ID, TEHSIL_ID, WARD_PATWARI_ID, MOHALLA_VILLAGE_ID, PROPERTY_TYPE_ID, PROPERTY_TYPE_L1_ID, PROPERTY_TYPE_L2_ID, SUBCLAUSE_ID, STATUS, GUIDELINE_ID, MAPPED)VALUES" +
		"(IGRS_SUB_DERIVE_SEQ.nextval, DISTRICT_ID, TEHSIL_ID, WARD_PATWARI_ID, MOHALLA_VILLAGE_ID,PROPERTY_TYPE_ID, PROPERTY_TYPE_L1_ID, PROPERTY_TYPE_L2_ID,SUB_CLAUSE_ID, SUB_CLAUSE_STATUS, ?, DEFINE_FOR_ALL_DISTRICT)" +
		"SELECT  distinct c.district_id, c.tehsil_id, c.ward_patwari_id, c.mohalla_village_id ,b.PROPERTY_TYPE_ID, b.PROPERTY_TYPE_L1_ID, b.PROPERTY_TYPE_L2_ID, a.SUB_CLAUSE_ID,a.SUB_CLAUSE_STATUS,a.DEFINE_FOR_ALL_DISTRICT FROM " +
		"IGRS_SUB_CLAUSE_MASTER a,IGRS_SUBCLAUSE_PROP_MAPPING b, IGRS_GUIDELINE_TEMPLATE c WHERE A.SUB_CLAUSE_ID NOT IN" +
		"(SELECT DISTINCT SUB_CLAUSE_ID FROM IGRS_SUB_CLAUSE_MASTER_FINAL WHERE DISTRICT_ID  = ?) and a.SUB_CLAUSE_ID = b.SUB_CLAUSE_ID and c.DISTRICT_ID = ?";*/

public static final String VIEW_DERIVE = "SELECT DISTINCT a.SUBCLAUSE_ID,b.SUB_CLAUSE_NAME, b.SUB_CLAUSE_DESC, MAPPED FROM IGRS_SUBCLAUSE_DERIVED_MASTER a, IGRS_SUB_CLAUSE_MASTER b WHERE " +
		"a.GUIDELINE_ID = ? AND a.TEHSIL_ID = ? AND a.WARD_PATWARI_ID = ? AND a.MOHALLA_VILLAGE_ID = ? AND" +
		" a.PROPERTY_TYPE_ID= ? AND a.PROPERTY_TYPE_L1_ID =? AND a.PROPERTY_TYPE_L2_ID = ? AND " +
		"a.SUBCLAUSE_ID = b.SUB_CLAUSE_ID";

//latest
//public static final String VIEW_DERIVE = "SELECT DISTINCT a.SUBCLAUSE_ID,b.SUB_CLAUSE_NAME, b.SUB_CLAUSE_DESC, MAPPED FROM IGRS_SUBCLAUSE_DERIVED_MASTER a, IGRS_SUB_CLAUSE_MASTER b WHERE  a.GUIDELINE_ID = '' AND a.TEHSIL_ID = '' AND a.WARD_PATWARI_ID = '' AND a.COLONY_ID = '' AND a.COLONY_WARD_MAPPING_ID = '' AND a.PROPERTY_TYPE_ID= '' AND a.SUB_PROPERTY_TYPE = '' AND a.SUBCLAUSE_ID = b.SUB_CLAUSE_ID";

public static final String VIEW_DERIVE_NULL = "SELECT DISTINCT a.SUBCLAUSE_ID, b.SUB_CLAUSE_NAME,b.SUB_CLAUSE_DESC, MAPPED FROM IGRS_SUBCLAUSE_DERIVED_MASTER a, IGRS_SUB_CLAUSE_MASTER b WHERE " +
"a.GUIDELINE_ID = ? AND a.TEHSIL_ID = ? AND a.WARD_PATWARI_ID = ? AND a.MOHALLA_VILLAGE_ID = ? AND" +
" a.PROPERTY_TYPE_ID= ? AND a.PROPERTY_TYPE_L1_ID =? AND a.PROPERTY_TYPE_L2_ID is null AND " +
"a.SUBCLAUSE_ID = b.SUB_CLAUSE_ID";


public static final String DERIVE_SUB_NOTCLICKED = "SELECT SUBCLAUSE_ID FROM IGRS_SUBCLAUSE_DERIVED_MASTER WHERE " +
		"GUIDELINE_ID = ? AND TEHSIL_ID = ? AND WARD_PATWARI_ID = ? AND MOHALLA_VILLAGE_ID = ? AND" +
		" PROPERTY_TYPE_ID = ? AND PROPERTY_TYPE_L1_ID = ? AND PROPERTY_TYPE_L2_ID = ? AND STATUS = 'A' and MAPPED = 'Y' order by SUBCLAUSE_ID";


public static final String DERIVE_UPDATE = "UPDATE IGRS_SUBCLAUSE_DERIVED_MASTER SET MAPPED = ?,STATUS = 'A' " +
		"WHERE GUIDELINE_ID = ? AND DISTRICT_ID= ? AND " +
		"TEHSIL_ID = ? AND WARD_PATWARI_ID = ? AND MOHALLA_VILLAGE_ID = ? AND " +
		"PROPERTY_TYPE_ID = ? AND PROPERTY_TYPE_L1_ID = ? AND PROPERTY_TYPE_L2_ID = ? AND SUBCLAUSE_ID = ?";


public static final String DERIVE_TO_APPROVED = "INSERT ALL INTO IGRS_SUBCLAUSE_MASTER_APPROVED(ID, DISTRICT_ID, TEHSIL_ID, WARD_PATWARI_ID, MOHALLA_VILLAGE_ID, PROPERTY_TYPE_ID, PROPERTY_TYPE_L1_ID, " +
		"PROPERTY_TYPE_L2_ID, STATUS, SUBCLAUSE_ID,GUIDELINE_ID)VALUES" +
		"(IGRS_SUBCLAUSE_APP_SEQ.NEXTVAL, DISTRICT_ID, TEHSIL_ID, WARD_PATWARI_ID, " +
		"MOHALLA_VILLAGE_ID, PROPERTY_TYPE_ID, PROPERTY_TYPE_L1_ID, PROPERTY_TYPE_L2_ID, STATUS," +
		" SUBCLAUSE_ID,GUIDELINE_ID) SELECT DISTRICT_ID, TEHSIL_ID, WARD_PATWARI_ID, MOHALLA_VILLAGE_ID, " +
		"PROPERTY_TYPE_ID , PROPERTY_TYPE_L1_ID, PROPERTY_TYPE_L2_ID, STATUS, SUBCLAUSE_ID, GUIDELINE_ID " +
		"FROM IGRS_SUBCLAUSE_DERIVED_MASTER WHERE GUIDELINE_ID = ? AND MOHALLA_VILLAGE_ID = ? AND MAPPED= 'Y'";

public static final String APP_DATA = "SELECT DISTINCT SUBCLAUSE_ID FROM IGRS_SUBCLAUSE_DERIVED_MASTER " +
		"WHERE GUIDELINE_ID = ? AND TEHSIL_ID = ? AND WARD_PATWARI_ID = ? AND MOHALLA_VILLAGE_ID = ? AND " +
		"PROPERTY_TYPE_ID = ? AND PROPERTY_TYPE_L1_ID = ? AND PROPERTY_TYPE_L2_ID = ? AND STATUS = 'A' ";

public static final String APP_COMPLETE = "SELECT SUB_CLAUSE_ID,SUB_CLAUSE_NAME,SUB_CLAUSE_DESC FROM IGRS_SUB_CLAUSE_MASTER a, IGRS_SUBCLAUSE_MASTER_APPROVED b" +
		" WHERE b.GUIDELINE_ID = ? AND b.TEHSIL_ID = ? AND b.WARD_PATWARI_ID = ? AND " +
		"b.MOHALLA_VILLAGE_ID =? AND b.PROPERTY_TYPE_ID = ? " +
		"AND b.PROPERTY_TYPE_L1_ID =? AND b.PROPERTY_TYPE_L2_ID =? AND b.SUBCLAUSE_ID = a.SUB_CLAUSE_ID AND b.STATUS = 'A'";

public static final String APP_COMPLETE_DERIVED = "SELECT SUB_CLAUSE_ID,SUB_CLAUSE_NAME, SUB_CLAUSE_DESC, MAPPED FROM IGRS_SUB_CLAUSE_MASTER a, IGRS_SUBCLAUSE_MASTER_APPROVED b" +
" WHERE b.GUIDELINE_ID = ? AND b.TEHSIL_ID = ? AND b.WARD_PATWARI_ID = ? AND " +
"b.MOHALLA_VILLAGE_ID =? AND b.PROPERTY_TYPE_ID = ? " +
"AND b.PROPERTY_TYPE_L1_ID =? AND b.PROPERTY_TYPE_L2_ID =? AND b.SUBCLAUSE_ID = a.SUB_CLAUSE_ID AND b.MAPPED = 'Y' AND b.STATUS = 'A'";
 
public static final String SUB_NEW_MOHALLA = "INSERT INTO IGRS_SUBCLAUSE_DERIVED_MASTER(ID, DISTRICT_ID, TEHSIL_ID, WARD_PATWARI_ID, MOHALLA_VILLAGE_ID, " +
		"PROPERTY_TYPE_ID, PROPERTY_TYPE_L1_ID, PROPERTY_TYPE_L2_ID, SUBCLAUSE_ID, STATUS, GUIDELINE_ID, MAPPED) VALUES" +
		"(IGRS_SUBCLAUSE_APP_SEQ.nextval,?,?,?,?,?,?,?,?,?,?,'Y')"; 

public static final String SUB_VIEW_APP = "SELECT a.SUBCLAUSE_ID,b.SUB_CLAUSE_NAME, b.SUB_CLAUSE_DESC, a.STATUS FROM " +
		"IGRS_SUBCLAUSE_MASTER_APPROVED a, IGRS_SUB_CLAUSE_MASTER b WHERE " +
		"a.GUIDELINE_ID = ? AND a.TEHSIL_ID = ? AND a.WARD_PATWARI_ID = ? AND " +
		"a.MOHALLA_VILLAGE_ID = ? AND a.PROPERTY_TYPE_ID = ? AND " +
		"a.PROPERTY_TYPE_L1_ID = ? AND a.PROPERTY_TYPE_L2_ID = ? AND " +
		"a.SUBCLAUSE_ID = b.SUB_CLAUSE_ID and a.STATUS = 'A' AND b.SUB_CLAUSE_STATUS = 'A'";

public static final String SUB_VIEW_FINAL = "SELECT a.SUB_CLAUSE_ID,b.SUB_CLAUSE_NAME, b.SUB_CLAUSE_DESC, a.STATUS FROM " +
		"IGRS_SUB_CLAUSE_MASTER_FINAL a, IGRS_SUB_CLAUSE_MASTER b WHERE " +
		"a.DISTRICT_ID = ? AND a.TEHSIL_ID = ? AND a.WARD_PATWARI_ID = ? AND a.MOHALLA_VILLAGE_ID = ? AND " +
		"a.PROPERTY_TYPE_ID = ? AND a.PROPERTY_TYPE_L1_ID = ? AND a.PROPERTY_TYPE_L2_ID = ? AND" +
		" a.SUB_CLAUSE_ID = b.SUB_CLAUSE_ID and a.STATUS = 'A' AND b.SUB_CLAUSE_STATUS = 'A'";


public static final String VIEW_DRAFT_AVAILABLE = "SELECT DISTINCT a.GUIDELINE_ID , a.DURATION_FROM, a.DURATION_TO, a.FINANCIAL_YEAR FROM IGRS_GUIDELINE_PARENT_TEMP a, IGRS_GUIDELINE_CHILD1_TEMP b " +
		"WHERE a.GUIDELINE_ID = b.GUIDELINE_ID AND a.PUBLISH_STATUS = '2' AND a.DISTRICT_ID = ? AND " +
		" a.FINANCIAL_YEAR IN(?) order by a.guideline_id desc ";

public static final String SR_VIEW_DRAFT_AVAILABLE = "SELECT DISTINCT a.GUIDELINE_ID , a.DURATION_FROM, a.DURATION_TO, a.FINANCIAL_YEAR FROM IGRS_GUIDELINE_PARENT_TEMP a, IGRS_GUIDELINE_CHILD1_TEMP b " +
"WHERE a.GUIDELINE_ID = b.GUIDELINE_ID AND a.SR_PUBLISH_STATUS = '3' and a.PRINT_STATUS = 'C' and a.DR_PUBLISH_STATUS is null and (a.DERVIED_BY_STATUS is null or DERVIED_BY_STATUS='SR') AND a.DISTRICT_ID = ? AND " +
" a.FINANCIAL_YEAR IN(?) order by a.guideline_id desc ";

public static final String DR_VIEW_DRAFT_AVAILABLE = "SELECT DISTINCT a.GUIDELINE_ID , a.DURATION_FROM, a.DURATION_TO, a.FINANCIAL_YEAR FROM IGRS_GUIDELINE_PARENT_TEMP a, IGRS_GUIDELINE_CHILD1_TEMP b " +
"WHERE a.GUIDELINE_ID = b.GUIDELINE_ID AND a.PRINT_STATUS='C' and a.SR_PUBLISH_STATUS = '3' and ( a.DERVIED_BY_STATUS='DR' OR a.DR_PUBLISH_STATUS=3) and(a.PUBLISH_STATUS = '2') AND a.DISTRICT_ID = ? AND " +
" a.FINANCIAL_YEAR IN(?) order by a.guideline_id desc ";


/**
 * BELOW QUERIES FOR DISTRICT WISE VIEW OF GUIDELINE
 */

public static final String GET_TEHSILS = "SELECT TEHSIL_ID, TEHSIL_NAME FROM IGRS_TEHSIL_MASTER WHERE TEHSIL_STATUS = 'A' AND DISTRICT_ID =";
public static final String GET_WARD_PATWARI = "SELECT WARD_PATWARI_ID, WARD_PATWARI_NAME,H_WARD_PATWARI_NAME FROM IGRS_WARD_PATWARI_MASTER WHERE WARD_PATWARI_STATUS = 'A' AND TEHSIL_ID = ?";
public static final String GET_MOHALLA_VILLAGE = "SELECT MOHALLA_VILLAGE_ID, MOHALLA_VILLAGE_NAME FROM IGRS_MOHALLA_VILLAGE_MASTER WHERE " +
		"MOHALLA_VILLAGE_STATUS = 'A'  AND WARD_PATWARI_ID=";


/**
 * BELOW QUERY IS TO INSERT DATA OF THE FEEDBACK OF DRAFT OF GUIDELINE
 * Author: Lavi
 */

public static final String CALL_IGRS_EMAIL_PROC= "Call IGRS_INSERT_EMAIL_DATA(?,?,?,?)";

/**
 * BELOW QUERY ADDED For GETTING DISTRICT ON THE BASIS OF OFFICE
 */
public static final String GET_DISTRICT = "SELECT a.DISTRICT_ID FROM IGRS_OFFICE_MASTER a WHERE  a.OFFICE_ID= ?";

public static final String GET_DISRICT_NAME = "SELECT DISTRICT_ID,DISTRICT_NAME FROM IGRS_DISTRICT_MASTER WHERE DISTRICT_ID = ";

public static final String GET_DERIVED_GUIDELINE_DETLS = "SELECT DERIVED_FROM, DERIVED_FROM_GUIDELINE_ID FROM IGRS_GUIDELINE_PARENT_TEMP WHERE GUIDELINE_ID = ?";

/**
 * added on 1 aug
 */

/*public static final String GUIDELINE_VIEW_FINAL_PRAROOP= "SELECT distinct p.property_type_id,p.property_type_name,p1.property_type_l1_id,p1.property_type_l1_name," +
" p2.property_type_l2_id, p2.property_type_l2_name,c2.guideline_value,m.mohalla_village_id,m.mohalla_village_name, " +
"u.uom_name,u.uom_id, c2.PROPERTY_TYPE_ID, c2.PROPERTY_TYPE_L1_ID, c2.PROPERTY_TYPE_L2_ID FROM " +
"IGRS_PROPERTY_TYPE_MASTER p ,IGRS_PROP_TYPE_L1_MASTER p1,IGRS_PROP_TYPE_L2_MASTER p2," +
"igrs_guideline_master_child2 c2,igrs_guideline_master_child1 c1,igrs_guideline_master_parent c," +
"igrs_mohalla_village_master m, igrs_uom_master u where c.guideline_id = ? " +
"and c1.TEHSIL_ID = ? and c1.WARD_PATWARI_ID = ? and c1.mohalla_village_id = ?" +
" and c.guideline_id = c1.guideline_id and " +
"c1.guideline_child1_id = c2.guideline_child1_id and" +
" c1.mohalla_village_id  = m.mohalla_village_id and " +
"c2.property_type_id = p.property_type_id and" +
" c2.property_type_l1_id = p1.property_type_l1_id(+) and " +
"c2.property_type_l2_id = p2.property_type_l2_id(+) and " +
"c2.unit_of_measure_id = u.uom_id and p.property_type_id = ? order by c2.property_type_id, c2.property_type_l1_id,c2.property_type_l2_id";
*/
public static String AREA_NAME_EN="SELECT AREA_TYPE_ID,AREA_TYPE_NAME FROM IGRS_AREA_TYPE_MASTER WHERE  AREA_TYPE_STATUS='A'";

public static String AREA_NAME_HI="SELECT AREA_TYPE_ID,H_AREA_TYPE_NAME FROM IGRS_AREA_TYPE_MASTER WHERE AREA_TYPE_STATUS='A'";

public static String SUB_AREA_NAME_EN="SELECT SUB_AREA_TYPE_ID,SUB_AREA_TYPE_NAME FROM IGRS_SUB_AREA_TYPE_MASTER WHERE AREA_TYPE_ID=? AND SUB_AREA_TYPE_STATUS='A'";

public static String SUB_AREA_NAME_HI="SELECT SUB_AREA_TYPE_ID,H_SUB_AREA_TYPE_NAME FROM IGRS_SUB_AREA_TYPE_MASTER WHERE AREA_TYPE_ID=? AND SUB_AREA_TYPE_STATUS='A'";

public static String SUB_AREA_NAME_EN_UR="SELECT SUB_AREA_TYPE_ID,SUB_AREA_TYPE_NAME FROM IGRS_SUB_AREA_TYPE_MASTER WHERE AREA_TYPE_ID=? AND SUB_AREA_TYPE_STATUS='A' AND TEHSIL_ID=?";

public static String SUB_AREA_NAME_HI_UR="SELECT SUB_AREA_TYPE_ID,H_SUB_AREA_TYPE_NAME FROM IGRS_SUB_AREA_TYPE_MASTER WHERE AREA_TYPE_ID=? AND SUB_AREA_TYPE_STATUS='A' AND TEHSIL_ID=?";

public static String WARD_PATWARI_NAME_EN="SELECT WPM.WARD_PATWARI_ID,WPM.WARD_PATWARI_NAME,WM.SUBAREA_WARD_MAPPING_ID FROM IGRS_WARD_PATWARI_MASTER WPM,IGRS_SUBAREA_WARD_MAPPING WM WHERE WM.WARD_PATWARI_ID=WPM.WARD_PATWARI_ID AND WM.SUB_AREA_TYPE_ID=? AND TEHSIL_ID =? AND WARD_PATWARI_STATUS='A'";

public static String WARD_PATWARI_NAME_HI="SELECT WPM.WARD_PATWARI_ID ,WPM.H_WARD_PATWARI_NAME,WM.SUBAREA_WARD_MAPPING_ID FROM IGRS_WARD_PATWARI_MASTER WPM,IGRS_SUBAREA_WARD_MAPPING WM WHERE WM.WARD_PATWARI_ID=WPM.WARD_PATWARI_ID AND WM.SUB_AREA_TYPE_ID=? AND TEHSIL_ID =? AND WARD_PATWARI_STATUS='A'";

public static String COLONY_NAME_EN="SELECT C.COLONY_ID, C.COLONY_NAME,M.COLONY_WARD_MAPPING_ID,C.APPLICABLE_SUBCLAUSE_ID FROM IGRS_COLONY_MASTER C ,IGRS_COLONY_WARD_MAPPING M WHERE M.COLONY_ID=C.COLONY_ID AND C.COLONY_STATUS='A' AND M.SUBAREA_WARD_MAPPING_ID=?";

public static String COLONY_NAME_HI="SELECT C.COLONY_ID, C.H_COLONY_NAME,M.COLONY_WARD_MAPPING_ID,C.APPLICABLE_SUBCLAUSE_ID FROM IGRS_COLONY_MASTER C ,IGRS_COLONY_WARD_MAPPING M WHERE M.COLONY_ID=C.COLONY_ID AND C.COLONY_STATUS='A' AND M.SUBAREA_WARD_MAPPING_ID=?";


/*public static final String GUIDELINE_VIEW_FINAL_PRAROOP= "SELECT DISTINCT p.property_type_id,p.property_type_name,p1.prop_type_l1_id,p1.prop_type_l1_name, "+
														"p2.property_type_l2_id, p2.property_type_l2_name,c2.guideline_value,m.COLONY_ID,m.COLONY_NAME,  "+
														"u.uom_name,u.uom_id, c2.PROPERTY_TYPE_ID, c2.PROPERTY_TYPE_L1_ID, c2.PROPERTY_TYPE_L2_ID "+
														"FROM IGRS_PROPERTY_TYPE_MASTER p ,IGRS_PROP_TYPE_L1_MASTER_dummy p1,IGRS_PROP_TYPE_L2_MASTER_dummy p2, "+
														"igrs_guideline_master_child2 c2,igrs_guideline_master_child1 c1,igrs_guideline_master_parent c, "+
														"IGRS_COLONY_MASTER m, igrs_uom_master u, IGRS_AREA_TYPE_MASTER atm,IGRS_SUB_AREA_TYPE_MASTER satm "+
														"WHERE c.guideline_id = ? AND c1.AREA_TYPE_ID=?  "+
														" AND c1.TEHSIL_ID = ? AND c1.WARD_PATWARI_ID = ? AND c1.COLONY_WARD_MAPPING_ID=?  and "+
														" AND c1.COLONY_ID=? AND p.property_type_id =?  AND c.guideline_id = c1.guideline_id AND "+
														"p1.DISPLAY_FLAG_GUIDLN='Y' AND (p2.DISPLAY_FLAG is null OR (p2.DISPLAY_FLAG in('B','G'))) AND "+
														"c1.guideline_child1_id = c2.guideline_child1_id AND c2.property_type_id = p.property_type_id AND "+
														"c2.property_type_l1_id = p1.prop_type_l1_id(+) AND c2.property_type_l2_id = p2.property_type_l2_id(+)  AND "+
														"c1.COLONY_ID=m.COLONY_ID AND c1.AREA_TYPE_ID = atm.AREA_TYPE_ID AND c1.SUB_AREA_TYPE_ID=satm.SUB_AREA_TYPE_ID "+
														"AND c2.unit_of_measure_id = u.uom_id ORDER BY c2.property_type_id, c2.property_type_l1_id,c2.property_type_l2_id";*/

public static final String GUIDELINE_VIEW_FINAL_PRAROOP= "SELECT distinct p.property_type_id,p.property_type_name,p1.PROP_TYPE_L1_ID,p1.PROP_TYPE_L1_NAME, "+
"p2.property_type_l2_id,p2.property_type_l2_name,c2.guideline_value,m.COLONY_ID,m.COLONY_NAME, "+
"u.uom_name,u.uom_id, c2.PROPERTY_TYPE_ID, c2.PROPERTY_TYPE_L1_ID, c2.PROPERTY_TYPE_L2_ID "+
"FROM IGRS_PROPERTY_TYPE_MASTER p ,IGRS_PROP_TYPE_L1_MASTER_dummy p1,IGRS_PROP_TYPE_L2_MASTER_dummy p2, "+
"igrs_guideline_master_child2 c2,igrs_guideline_master_child1 c1,igrs_guideline_master_parent c, "+
"IGRS_COLONY_MASTER m, igrs_uom_master u where c.guideline_id = ? "+
"AND c1.AREA_TYPE_ID=? and c1.TEHSIL_ID = ? "+
"and c1.WARD_PATWARI_ID = ? AND c1.COLONY_WARD_MAPPING_ID=? and c1.COLONY_ID=? "+
" and c.guideline_id = c1.guideline_id and c1.guideline_child1_id = c2.guideline_child1_id and "+
"p1.DISPLAY_FLAG_GUIDLN='Y' AND (p2.DISPLAY_FLAG is null OR (p2.DISPLAY_FLAG in('B','G'))) AND "+
" c1.COLONY_ID  = m.COLONY_ID and c2.property_type_id = p.property_type_id and "+
" c2.property_type_l1_id = p1.PROP_TYPE_L1_ID(+) and c2.property_type_l2_id = p2.property_type_l2_id(+) and "+
" p1.UOM_ID = u.uom_id and p.property_type_id in(1,2,3) order by c2.property_type_id, "+
"c2.property_type_l1_id,c2.property_type_l2_id";

/*public static final String GUIDELINE_VIEW_TEMP_PRAROOP= "SELECT distinct p.property_type_id,p.property_type_name,p1.property_type_l1_id,p1.property_type_l1_name," +
" p2.property_type_l2_id,p2.property_type_l2_name,c2.guideline_value,m.mohalla_village_id,m.mohalla_village_name, " +
"u.uom_name,u.uom_id, c2.PROPERTY_TYPE_ID, c2.PROPERTY_TYPE_L1_ID, c2.PROPERTY_TYPE_L2_ID FROM " +
"IGRS_PROPERTY_TYPE_MASTER p ,IGRS_PROP_TYPE_L1_MASTER p1,IGRS_PROP_TYPE_L2_MASTER p2," +
"igrs_guideline_child2_temp c2,igrs_guideline_child1_temp c1,igrs_guideline_parent_temp c," +
"igrs_mohalla_village_master m, igrs_uom_master u where c.guideline_id = ? " +
"and c1.TEHSIL_ID = ? and c1.WARD_PATWARI_ID = ? and c1.mohalla_village_id = ?" +
" and c.guideline_id = c1.guideline_id and " +
"c1.guideline_child1_id = c2.guideline_child1_id and" +
" c1.mohalla_village_id  = m.mohalla_village_id and " +
"c2.property_type_id = p.property_type_id and" +
" c2.property_type_l1_id = p1.property_type_l1_id(+) and " +
"c2.property_type_l2_id = p2.property_type_l2_id(+) and " +
"c2.unit_of_measure_id = u.uom_id and p.property_type_id = ? order by c2.property_type_id, c2.property_type_l1_id,c2.property_type_l2_id";
*/



//added by SHREERAJ

public static final String GUIDELINE_VIEW_TEMP_PRAROOP= "SELECT distinct p.property_type_id,p.property_type_name,p1.PROP_TYPE_L1_ID "+
",p1.PROP_TYPE_L1_NAME, p2.property_type_l2_id,p2.property_type_l2_name, "+
"c2.guideline_value,m.COLONY_ID,m.COLONY_NAME, u.uom_name,u.uom_id, "+
"c2.PROPERTY_TYPE_ID, c2.PROPERTY_TYPE_L1_ID, c2.PROPERTY_TYPE_L2_ID "+
"FROM IGRS_PROPERTY_TYPE_MASTER p ,IGRS_PROP_TYPE_L1_MASTER_dummy p1, "+
"IGRS_PROP_TYPE_L2_MASTER_dummy p2, igrs_guideline_child2_temp c2, "+
" igrs_guideline_child1_temp c1,igrs_guideline_parent_temp c, "+
"IGRS_COLONY_MASTER m, igrs_uom_master u ,IGRS_SUB_AREA_TYPE_MASTER s "+
"where c.guideline_id = ? AND c1.AREA_TYPE_ID=?  "+
" and c1.TEHSIL_ID = ? and c1.WARD_PATWARI_ID = ? AND "+
" c1.COLONY_WARD_MAPPING_ID=? and c1.colony_id = ? "+
"and c.guideline_id = c1.guideline_id and c1.guideline_child1_id = c2.guideline_child1_id "+
"and p1.DISPLAY_FLAG_GUIDLN='Y' AND (p2.DISPLAY_FLAG is null OR "+
"(p2.DISPLAY_FLAG in('B','G'))) AND  c1.COLONY_ID  = m.COLONY_ID and "+
"c2.property_type_id = p.property_type_id and  c2.property_type_l1_id = p1.PROP_TYPE_L1_ID(+) "+
"and c2.property_type_l2_id = p2.property_type_l2_id(+) and p1.UOM_ID = u.uom_id and "+
"p.property_type_id in (1,2,3) order by c2.property_type_id, c2.property_type_l1_id,c2.property_type_l2_id ";

public static final String GUIDELINE_REPORT_TEMP_PRAROOP= "SELECT distinct p.property_type_id,p.property_type_name,p1.PROP_TYPE_L1_ID "+
",p1.PROP_TYPE_L1_NAME, p2.property_type_l2_id,p2.property_type_l2_name, "+
"c2.guideline_value,m.COLONY_ID,m.COLONY_NAME, u.uom_name,u.uom_id, "+
"c2.PROPERTY_TYPE_ID, c2.PROPERTY_TYPE_L1_ID, c2.PROPERTY_TYPE_L2_ID "+
"FROM IGRS_PROPERTY_TYPE_MASTER p ,IGRS_PROP_TYPE_L1_MASTER_dummy p1, "+
"IGRS_PROP_TYPE_L2_MASTER_dummy p2, igrs_guideline_child2_temp c2, "+
" igrs_guideline_child1_temp c1,igrs_guideline_parent_temp c, "+
"IGRS_COLONY_MASTER m, igrs_uom_master u ,IGRS_SUB_AREA_TYPE_MASTER s "+
"where c.guideline_id = ? AND c1.AREA_TYPE_ID=?  "+
" and c1.TEHSIL_ID = ? and c1.WARD_PATWARI_ID = ? AND "+
" c1.COLONY_WARD_MAPPING_ID=? and c1.colony_id = ? "+
"and c.guideline_id = c1.guideline_id and c1.guideline_child1_id = c2.guideline_child1_id "+
"and p1.DISPLAY_FLAG_GUIDLN='Y' AND (p2.DISPLAY_FLAG is null OR "+
"(p2.DISPLAY_FLAG in('B','G'))) AND  c1.COLONY_ID  = m.COLONY_ID and "+
"c2.property_type_id = p.property_type_id and  c2.property_type_l1_id = p1.PROP_TYPE_L1_ID(+) "+
"and c2.property_type_l2_id = p2.property_type_l2_id(+) and p1.UOM_ID = u.uom_id and "+
"p.property_type_id in (?) order by c2.property_type_id, c2.property_type_l1_id,c2.property_type_l2_id ";

/*public static final String GUIDELINE_VIEW_TEMP_PRAROOP= "SELECT distinct p.property_type_id,p.property_type_name,p1.PROP_TYPE_L1_ID,p1.PROP_TYPE_L1_NAME, "+
														"p2.property_type_l2_id,p2.property_type_l2_name,c2.guideline_value,m.COLONY_ID,m.COLONY_NAME, "+
														"u.uom_name,u.uom_id, c2.PROPERTY_TYPE_ID, c2.PROPERTY_TYPE_L1_ID, c2.PROPERTY_TYPE_L2_ID "+
														"FROM IGRS_PROPERTY_TYPE_MASTER p ,IGRS_PROP_TYPE_L1_MASTER_dummy p1,IGRS_PROP_TYPE_L2_MASTER_dummy p2, "+
														"igrs_guideline_child2_temp c2,igrs_guideline_child1_temp c1,igrs_guideline_parent_temp c, "+
														"IGRS_COLONY_MASTER m, igrs_uom_master u where c.guideline_id = ? "+
														"AND c1.AREA_TYPE_ID=? and c1.TEHSIL_ID = ? "+
														"and c1.WARD_PATWARI_ID = ? AND c1.COLONY_WARD_MAPPING_ID=? and c1.COLONY_ID=? "+
														" and c.guideline_id = c1.guideline_id and c1.guideline_child1_id = c2.guideline_child1_id and "+
														"p1.DISPLAY_FLAG_GUIDLN='Y' AND (p2.DISPLAY_FLAG is null OR (p2.DISPLAY_FLAG in('B','G'))) AND "+
														" c1.COLONY_ID  = m.COLONY_ID and c2.property_type_id = p.property_type_id and "+
														" c2.property_type_l1_id = p1.PROP_TYPE_L1_ID(+) and c2.property_type_l2_id = p2.property_type_l2_id(+) and "+
														" p1.UOM_ID = u.uom_id and p.property_type_id = ? order by c2.property_type_id, "+
														"c2.property_type_l1_id,c2.property_type_l2_id";*/

public static final String GET_AVIALBALE_DRAFT_GUIDELINE = "SELECT DISTINCT a.GUIDELINE_ID , to_char(a.DURATION_FROM,'dd/mm/yyyy'),to_char(a.DURATION_TO,'dd/mm/yyyy')," +
		" a.FINANCIAL_YEAR FROM IGRS_GUIDELINE_PARENT_TEMP a, IGRS_GUIDELINE_CHILD1_TEMP b WHERE " +
		"a.GUIDELINE_ID = b.GUIDELINE_ID AND b.STATUS_FLAG = 2  AND " +
		"a.PUBLISH_STATUS = '2' AND a.DISTRICT_ID = ? AND a.FINANCIAL_YEAR = ?";

public static final String GET_PROPERTY_TYPES = "SELECT DISTINCT PROP_TYPE_L1_NAME, PROPERTY_TYPE_L2_NAME,A.PROPERTY_TYPE_L1_ID," +
		"A.PROPERTY_TYPE_L2_ID FROM IGRS_GUIDELINE_MASTER_CHILD2 A,IGRS_GUIDELINE_MASTER_CHILD1 B , " +
		"IGRS_PROP_TYPE_L1_MASTER_DUMMY C,IGRS_PROP_TYPE_L2_MASTER_DUMMY D WHERE GUIDELINE_ID =? " +
		"AND A.PROPERTY_TYPE_ID = ? AND A.GUIDELINE_CHILD1_ID = B.GUIDELINE_CHILD1_ID AND" +
		" A.PROPERTY_TYPE_L1_ID = C.PROP_TYPE_L1_ID(+) AND A.PROPERTY_TYPE_L2_ID = D.PROPERTY_TYPE_L2_ID(+) " +
		"ORDER BY A.PROPERTY_TYPE_L1_ID,A.PROPERTY_TYPE_L2_ID";

public static final String GET_FINAL_GUIDELINE_DETLS = "SELECT GUIDELINE_ID, FINANCIAL_YEAR FROM " +
		"IGRS_GUIDELINE_MASTER_PARENT WHERE DISTRICT_ID = ?";

public static final String GET_PRVALENT_GUIDELINE_DETLS_NULL = "SELECT distinct c2.guideline_value FROM " +
		"IGRS_PROPERTY_TYPE_MASTER p ,IGRS_PROP_TYPE_L1_MASTER_dummy p1,IGRS_PROP_TYPE_L2_MASTER_dummy p2," +
		"IGRS_GUIDELINE_MASTER_CHILD2 c2,IGRS_GUIDELINE_MASTER_CHILD1 c1,IGRS_GUIDELINE_MASTER_PARENT c," +
		"IGRS_COLONY_MASTER m, igrs_uom_master u where " +
		"c.guideline_id = ?  "+
		"and c1.TEHSIL_ID = ? and c1.WARD_PATWARI_ID = ? AND c1.COLONY_ID=? and "+
		"  c1.COLONY_WARD_MAPPING_ID=?  " +
		" and c2.PROPERTY_TYPE_ID = ? and c2.PROPERTY_TYPE_L1_ID = ?" +
		" and c2.PROPERTY_TYPE_L2_ID is null and c.guideline_id = c1.guideline_id and " +
		"p1.DISPLAY_FLAG_GUIDLN='Y' AND (p2.DISPLAY_FLAG is null OR (p2.DISPLAY_FLAG in('B','G'))) AND "+
		"c1.guideline_child1_id = c2.guideline_child1_id and " +
		"c1.COLONY_ID  = m.COLONY_ID and " +
		"c2.property_type_id = p.property_type_id and " +
		"c2.property_type_l1_id = p1.PROP_TYPE_L1_ID(+) and " +
		"c2.property_type_l2_id = p2.property_type_l2_id(+) and " +
		" p1.UOM_ID = u.uom_id ";

public static final String GET_PRVALENT_GUIDELINE_DETLS = "SELECT distinct c2.guideline_value FROM " +
"IGRS_PROPERTY_TYPE_MASTER p ,IGRS_PROP_TYPE_L1_MASTER_dummy p1,IGRS_PROP_TYPE_L2_MASTER_dummy p2," +
"IGRS_GUIDELINE_MASTER_CHILD2 c2,IGRS_GUIDELINE_MASTER_CHILD1 c1,IGRS_GUIDELINE_MASTER_PARENT c," +
"IGRS_COLONY_MASTER m, igrs_uom_master u where " +
"c.guideline_id = ?  "+
"and c1.TEHSIL_ID = ? and c1.WARD_PATWARI_ID = ? and " +
"c1.COLONY_ID = ?  AND c1.COLONY_WARD_MAPPING_ID=? "+
"and c2.PROPERTY_TYPE_ID = ? and c2.PROPERTY_TYPE_L1_ID = ?" +
" and c2.PROPERTY_TYPE_L2_ID = ? and c.guideline_id = c1.guideline_id and " +
"p1.DISPLAY_FLAG_GUIDLN='Y' AND (p2.DISPLAY_FLAG is null OR (p2.DISPLAY_FLAG in('B','G'))) AND "+
"c1.guideline_child1_id = c2.guideline_child1_id and " +
"c1.COLONY_ID  = m.COLONY_ID and " +
"c2.property_type_id = p.property_type_id and " +
"c2.property_type_l1_id = p1.PROP_TYPE_L1_ID(+) and " +
"c2.property_type_l2_id = p2.property_type_l2_id(+) and " +
"p1.UOM_ID = u.uom_id ";

public static final String GET_SUBCLUASE_ID = "SELECT DISTINCT SUBCLAUSE_ID FROM IGRS_SUBCLAUSE_DERIVED_MASTER WHERE " +
		"GUIDELINE_ID = ? AND TEHSIL_ID = ? AND WARD_PATWARI_ID = ? AND" +
		" MOHALLA_VILLAGE_ID = ? AND PROPERTY_TYPE_ID = ? AND " +
		"PROPERTY_TYPE_L1_ID = ? AND PROPERTY_TYPE_L2_ID = ? AND STATUS = 'A'";

public static final String GET_SUBCLAUSE_DETLS = "SELECT SUB_CLAUSE_ID,SUB_CLAUSE_NAME, " +
		"SUB_CLAUSE_DESC FROM IGRS_SUB_CLAUSE_MASTER WHERE SUB_CLAUSE_ID = ?";

public static final String DERIVE_SUBCLAUSE_NOT_CLICKED_NEW_MOHALLA_NULL  = "SELECT a.SUB_CLAUSE_ID FROM IGRS_SUB_CLAUSE_MASTER a," +
		"IGRS_SUBCLAUSE_PROP_MAPPING b WHERE b.PROPERTY_TYPE_ID = ?" +
		" AND b.PROPERTY_TYPE_L1_ID = ? AND  b.PROPERTY_TYPE_L2_ID is null" +
		" AND a.SUB_CLAUSE_STATUS = 'A' and a.SUB_CLAUSE_ID = b.SUB_CLAUSE_ID  " +
		"AND a.DEFINE_FOR_ALL_DISTRICT = 'Y' order by a.SUB_CLAUSE_ID";

public static final String DERIVE_SUBCLAUSE_NOT_CLICKED_NEW_MOHALLA  = "SELECT a.SUB_CLAUSE_ID FROM IGRS_SUB_CLAUSE_MASTER a," +
"IGRS_SUBCLAUSE_PROP_MAPPING b WHERE b.PROPERTY_TYPE_ID = ?" +
" AND b.PROPERTY_TYPE_L1_ID = ? AND  b.PROPERTY_TYPE_L2_ID = ?" +
" AND a.SUB_CLAUSE_STATUS = 'A' and a.SUB_CLAUSE_ID = b.SUB_CLAUSE_ID  " +
"AND a.DEFINE_FOR_ALL_DISTRICT = 'Y' order by a.SUB_CLAUSE_ID";

// ADDED BY VINAY
public static final String GET_CHECKER_STATUS="select checker_status from igrs_guideline_status where guideline_id= ? and maker_status = 4";

public static final String UPDATE_CHILD_TEMP="update igrs_guideline_child1_temp set validate_Status = 2 where guideline_id = ? and tehsil_id = ? and colony_id = ?" ;

public static final String UPDATE_PARENT_TEMP="update igrs_guideline_parent_temp set publish_status = ? where guideline_id = ?" ;

public static final String UPDATE_PARENT_TEMP_NEW="update igrs_guideline_parent_temp set SR_PUBLISH_STATUS = 3,PRINT_STATUS='C' where guideline_id = ?" ;

public static final String UPDATE_CHILD_TEMP1="update igrs_guideline_child1_temp set status_flag =? where guideline_id = ?" ;

public static final String GET_MOHALLA_MINUS="SELECT DISTINCT MOHALLA_VILLAGE_ID FROM IGRS_SUBCLAUSE_DERIVED_MASTER WHERE GUIDELINE_ID = ? minus SELECT DISTINCT MOHALLA_VILLAGE_ID FROM IGRS_SUBCLAUSE_MASTER_APPROVED WHERE GUIDELINE_ID = ?";

public static final String GET_GUIDELINE_ID="select guideline_id from igrs_guideline_master_parent where district_id = ?";

public static final String UPDATE_CHILD_TEMP2="update igrs_guideline_child1_temp set validate_Status = 1 where guideline_id = ? and tehsil_id = ? and colony_id = ?" ;

public static final String GET_DISTRICT_LIST = "SELECT om.DISTRICT_ID, dm.DISTRICT_NAME,dm.H_DISTRICT_NAME FROM IGRS_OFFICE_MASTER om, IGRS_DISTRICT_MASTER dm WHERE" +
		" om.DISTRICT_ID = dm.DISTRICT_ID AND OFFICE_ID = ? order by dm.DISTRICT_NAME";

public static final String GET_DISTRICT_LIST_HI = "SELECT om.DISTRICT_ID, dm.DISTRICT_NAME,dm.H_DISTRICT_NAME FROM IGRS_OFFICE_MASTER om, IGRS_DISTRICT_MASTER dm WHERE" +
" om.DISTRICT_ID = dm.DISTRICT_ID AND OFFICE_ID = ? order by dm.H_DISTRICT_NAME";

public static final String GET_FINAL_GUIDELINE_ID = "SELECT GUIDELINE_ID FROM " +
"IGRS_GUIDELINE_MASTER_PARENT WHERE DISTRICT_ID = ?";

//####---added by satbir kumar------


public static final String GET_GUIDELINE_IDS ="select to_char(guideline_id) as id,guideline_id || ' ' || '['|| to_char(duration_from, 'dd/mm/yyyy') || ' - ' || to_char(duration_to,'dd/mm/yyyy')||']' as " +
		" idyear from igrs_guideline_parent_temp where publish_status=2 and district_id=? and FINANCIAL_YEAR=? ";

public static final String DEL_TEMP_TABLE ="DELETE FROM IGRS_GUIDELINE_PRINT WHERE GUIDELINE_ID=?" ;

public static final String CALL_PRC_TEMP_GUID ="call igrs_print_guideline_proc(?,?,?)" ;

public static final String GET_FINAL_VIEW_RATES_ALL ="SELECT DISTINCT p.property_type_name,p1.prop_type_l1_name,nvl(p2.property_type_l2_name,'-'), c2.guideline_value,m.COLONY_ID,m.COLONY_NAME, c1.area_type_id,c2.property_type_id,c2.property_type_l1_id, c2.property_type_l2_id , u.uom_name, u.uom_id, p.h_property_type_name, p1.h_prop_type_l1_name,p2.h_property_type_l2_name, m.H_COLONY_NAME,u.h_uom_name FROM IGRS_PROPERTY_TYPE_MASTER p ,IGRS_PROP_TYPE_L1_MASTER_DUMMY p1,IGRS_PROP_TYPE_L2_MASTER_DUMMY p2,IGRS_GUIDELINE_MASTER_CHILD2 c2,IGRS_GUIDELINE_MASTER_CHILD1 c1,IGRS_GUIDELINE_MASTER_PARENT c,IGRS_COLONY_MASTER m, IGRS_UOM_MASTER u WHERE c.guideline_id = ? AND c1.COLONY_ID = ? AND c.guideline_id = c1.guideline_id AND p1.DISPLAY_FLAG_GUIDLN='Y' AND (p2.DISPLAY_FLAG IS NULL OR (p2.DISPLAY_FLAG IN('B','G'))) AND c1.guideline_child1_id = c2.guideline_child1_id AND c1.COLONY_ID  = m.COLONY_ID AND c2.property_type_id = p.property_type_id AND c2.property_type_l1_id = p1.prop_type_l1_id(+) AND  c2.property_type_l2_id = p2.property_type_l2_id(+) AND p1.UOM_ID = u.uom_id ORDER BY c2.property_type_id,c2.property_type_l1_id, c2.property_type_l2_id";

public static final String GET_TEMP_VIEW_RATES_ALL ="SELECT DISTINCT p.property_type_name,p1.prop_type_l1_name,nvl(p2.property_type_l2_name,'-'), c2.guideline_value,m.COLONY_ID,m.COLONY_NAME, c1.area_type_id,c2.property_type_id,c2.property_type_l1_id, c2.property_type_l2_id , u.uom_name, u.uom_id, p.h_property_type_name, p1.h_prop_type_l1_name,p2.h_property_type_l2_name, m.H_COLONY_NAME,u.h_uom_name FROM IGRS_PROPERTY_TYPE_MASTER p ,IGRS_PROP_TYPE_L1_MASTER_DUMMY p1,IGRS_PROP_TYPE_L2_MASTER_DUMMY p2,IGRS_GUIDELINE_CHILD2_TEMP c2,IGRS_GUIDELINE_CHILD1_TEMP c1,IGRS_GUIDELINE_PARENT_TEMP c,IGRS_COLONY_MASTER m, IGRS_UOM_MASTER u WHERE c.guideline_id = ? AND c1.COLONY_ID = ? AND c.guideline_id = c1.guideline_id AND p1.DISPLAY_FLAG_GUIDLN='Y' AND (p2.DISPLAY_FLAG IS NULL OR (p2.DISPLAY_FLAG IN('B','G'))) AND c1.guideline_child1_id = c2.guideline_child1_id AND c1.COLONY_ID  = m.COLONY_ID AND c2.property_type_id = p.property_type_id AND c2.property_type_l1_id = p1.prop_type_l1_id(+) AND  c2.property_type_l2_id = p2.property_type_l2_id(+) AND p1.UOM_ID = u.uom_id ORDER BY c2.property_type_id,c2.property_type_l1_id, c2.property_type_l2_id";


public static final String GET_FIYEAR_DETLS="select FINANCIAL_YEAR,to_char(DURATION_FROM,'dd/mm/yyy'),to_char(DURATION_TO,'dd/mm/yyy') FROM IGRS_GUIDELINE_MASTER_PARENT WHERE GUIDELINE_ID=?";

public static final String AREA_TYPE_RU = "SELECT AREA_TYPE_ID, AREA_TYPE_NAME,H_AREA_TYPE_NAME FROM " +
"IGRS_AREA_TYPE_MASTER where AREA_TYPE_ID =1 and area_type_status = 'A' ORDER BY AREA_TYPE_NAME";

public static final String AREA_TYPE_UR = "SELECT AREA_TYPE_ID, AREA_TYPE_NAME,H_AREA_TYPE_NAME FROM " +
"IGRS_AREA_TYPE_MASTER where AREA_TYPE_ID =2 and  area_type_status = 'A' ORDER BY AREA_TYPE_NAME";

public static final String COL_SUBCLAUSE_CHK = "SELECT DISTINCT APPLICABLE_SUBCLAUSE_ID  FROM " +
"IGRS_COLONY_MASTER where COLONY_ID =? AND COLONY_STATUS='A' ";

public static String UPDATE_PRINT_STATUS="update IGRS_GUIDELINE_PARENT_TEMP set PRINT_STATUS='C' where GUIDELINE_ID=?";

public static String UPDATE_SR_PUBLISH_STATUS="update IGRS_GUIDELINE_PARENT_TEMP set SR_PUBLISH_STATUS=3 where GUIDELINE_ID=?";

public static String CHECK_SR_PUBLISH_STATUS="SELECT SR_PUBLISH_STATUS FROM IGRS_GUIDELINE_PARENT_TEMP where GUIDELINE_ID=?";

public static String CHECK_PRINT_STATUS="SELECT PRINT_STATUS FROM IGRS_GUIDELINE_PARENT_TEMP where GUIDELINE_ID=?";

public static String UPDATE_SUBMIT_TO_DR="update IGRS_GUIDELINE_PARENT_TEMP set DR_PUBLISH_STATUS=3 where GUIDELINE_ID=?";

public static String CHECK_OFFICE_TYPE="SELECT DISTINCT S.OFFICE_TYPE_NAME FROM IGRS_OFFICE_TYPE_MASTER S,IGRS_OFFICE_MASTER M WHERE M.OFFICE_TYPE_ID=S.OFFICE_TYPE_ID AND M.OFFICE_ID=?";

public static String UPDATE_OLD_GUIDELINE_SR_STATUS="update IGRS_GUIDELINE_PARENT_TEMP set SR_PUBLISH_STATUS=4 where GUIDELINE_ID=?";

public static String UPDATE_AUTO_APPROVE_TO_DR="UPDATE IGRS_GUIDELINE_STATUS SET MAKER_STATUS=3,CHECKER_STATUS=2 WHERE GUIDELINE_ID=?";

public static String UPDATE_AUTO_APPROVE_MODIFY_TO_SR="UPDATE IGRS_GUIDELINE_STATUS SET MAKER_STATUS=4 WHERE GUIDELINE_ID=?";

public static String UPDATE_DERIVE_SR_STATUS="update IGRS_GUIDELINE_PARENT_TEMP set DERVIED_BY_STATUS='SR' where GUIDELINE_ID=?";

public static String UPDATE_DERIVE_DR_STATUS="update IGRS_GUIDELINE_PARENT_TEMP set DERVIED_BY_STATUS='DR' where GUIDELINE_ID=?";

public static String UPDATE_AUTO_VALIDATE_TO_DR="UPDATE IGRS_GUIDELINE_CHILD1_TEMP SET VALIDATE_STATUS='2' WHERE GUIDELINE_ID=?";

public static String UPDATE_COMPLETION_STATUS="UPDATE IGRS_GUIDELINE_STATUS SET CHECKER_STATUS='2' WHERE GUIDELINE_ID=?";

public static String UPDATE_PUBLISH_STATUS="update IGRS_GUIDELINE_PARENT_TEMP set PUBLISH_STATUS=2 where GUIDELINE_ID=?";

public static String UPDATE_PUBLISH_STATUS_FINAL="update IGRS_GUIDELINE_PARENT_TEMP set PUBLISH_STATUS=3 where GUIDELINE_ID=?";

public static String UPDATE_CURRENT_PUBLISH_STATUS="update IGRS_GUIDELINE_PARENT_TEMP set PUBLISH_STATUS=10 where GUIDELINE_ID NOT IN (?) AND FINANCIAL_YEAR=? AND DISTRICT_ID=? AND PUBLISH_STATUS=2 ";



public static String UPDATE_VIEW_STATUS="update IGRS_GUIDELINE_CHILD1_TEMP set STATUS_FLAG=2 where GUIDELINE_ID=?";
public static String UPDATE_VIEW_STATUS_FINAL="update IGRS_GUIDELINE_CHILD1_TEMP set STATUS_FLAG=3 where GUIDELINE_ID=?";

public static final String GET_ID_PARENT_DETLS="SELECT COUNT(*) FROM IGRS_GUIDELINE_PARENT_TEMP WHERE DISTRICT_ID=? AND FINANCIAL_YEAR=? AND DURATION_FROM=to_date(?,'dd-mm-yy') AND DURATION_TO=to_date(?,'dd-mm-yy')";


public static final String GET_DISTRICT_DETLS="SELECT DISTRICT_ID  FROM IGRS_OFFICE_MASTER WHERE OFFICE_ID=?";


//---end of addition


public static final String CHILD1_DELETE_DEAC_COMBINATIONS="delete from igrs_guideline_child1_temp where guideline_child1_id in ( "+
															" select t.guideline_child1_id from igrs_colony_master c,igrs_guideline_child1_temp t where c.colony_status='D' "+
															" and t.colony_id = c.COLONY_ID and t.guideline_id=?)";

public static final String CHILD2_DELETE_DEAC_COMBINATIONS="delete from igrs_guideline_child2_temp where guideline_child1_id in ( "+
															"select t.guideline_child1_id from igrs_colony_master c,igrs_guideline_child1_temp t where c.colony_status='D' "+
															"and t.colony_id = c.COLONY_ID and t.guideline_id=?)";

public static final String STATUS_DELETE_DEAC_COMBINATIONS="delete from igrs_guideline_status where colony_id in ( "+
" select t.colony_id from igrs_colony_master c,igrs_guideline_status t where c.colony_status='D' "+
" and t.colony_id = c.COLONY_ID and t.guideline_id=?)";


public static final String INSERT_NEW_COMBINATIONS = "CALL IGRS_INSERT_NEW_COMBINATIONS(?,?)";

public static final String GET_NEW_COMBINATIONS = "select t.tehsil_name,t.h_tehsil_name,a.area_type_name,a.h_area_type_name, "+
" sb.SUB_AREA_TYPE_NAME,sb.h_SUB_AREA_TYPE_NAME,w.WARD_PATWARI_NAME,w.h_WARD_PATWARI_NAME,c.COLONY_NAME,c.H_COLONY_NAME,c.colony_id from igrs_guideline_child1_temp c1,  "+
 " IGRS_COLONY_MASTER c , igrs_guideline_child2_temp c2 ,igrs_tehsil_master t,  "+
	" igrs_area_type_master a, igrs_sub_area_type_master sb, igrs_ward_patwari_master w where  "+
	" c1.GUIDELINE_ID= ? "+
	" and t.TEHSIL_STATUS='A'  "+
	" and a.AREA_TYPE_STATUS='A'  "+
	" and sb.SUB_AREA_TYPE_STATUS='A'  "+
	" and w.WARD_PATWARI_STATUS='A'  "+
	" and c.COLONY_STATUS='A'  "+
	" and c2.guideline_value=0  "+
 " and c2.PROPERTY_TYPE_ID=1  "+
	" and c2.PROPERTY_TYPE_L1_ID=1  "+
	" and t.TEHSIL_ID=c1.TEHSIL_ID  "+
	" and a.AREA_TYPE_ID=c1.AREA_TYPE_ID  "+
	" and sb.SUB_AREA_TYPE_ID=c1.SUB_AREA_TYPE_ID  "+
	" and w.WARD_PATWARI_ID=c1.WARD_PATWARI_ID  "+
	" and c.COLONY_ID=c1.COLONY_ID  "+
	" and c1.GUIDELINE_CHILD1_ID=c2.GUIDELINE_CHILD1_ID";

public static final String DELETE_OLD_COMBINATION ="DELETE FROM IGRS_GUIDELINE_TEMPLATE WHERE DISTRICT_ID=? ";
public static final String INSERT_NEW_TEMPLATE_COMBINATIONS ="INSERT INTO IGRS_GUIDELINE_TEMPLATE(DISTRICT_ID, TEHSIL_ID, WARD_PATWARI_ID, COLONY_ID,SUBAREA_WARD_MAPPING_ID,COLONY_WARD_MAPPING_ID,SUB_AREA_ID) " +
		" SELECT distt.DISTRICT_ID, teh.TEHSIL_ID, ward.WARD_PATWARI_ID, "+
		" col.COLONY_ID, subWard.SUBAREA_WARD_MAPPING_ID , colward.COLONY_WARD_MAPPING_ID, subAr.SUB_AREA_TYPE_ID FROM IGRS_DISTRICT_MASTER distt, IGRS_TEHSIL_MASTER teh, IGRS_WARD_PATWARI_MASTER ward, IGRS_COLONY_MASTER col, "+
		" IGRS_SUBAREA_WARD_MAPPING subWard, IGRS_COLONY_WARD_MAPPING colward, IGRS_SUB_AREA_TYPE_MASTER subAr "+
		" WHERE distt.DISTRICT_ID = teh.DISTRICT_ID AND distt.DISTRICT_ID=? AND teh.TEHSIL_ID = ward.TEHSIL_ID AND ward.WARD_PATWARI_ID = subWard.WARD_PATWARI_ID AND "+
		" subWard.SUBAREA_WARD_MAPPING_ID = colward.SUBAREA_WARD_MAPPING_ID AND colward.COLONY_ID = col.COLONY_ID AND  "+
		" subAr.SUB_AREA_TYPE_ID = subWard.SUB_AREA_TYPE_ID AND  distt.district_status = 'A' "+
		" AND teh.tehsil_status = 'A' AND ward.WARD_PATWARI_STATUS = 'A' AND col.COLONY_STATUS = 'A'";
public static final String CHECK_OLD_COMBINATION ="SELECT COUNT(*) FROM IGRS_GUIDELINE_TEMPLATE WHERE DISTRICT_ID=?";

public static final String CHECK_OLD_PRINT_COMBINATION="SELECT COUNT(*) FROM IGRS_GUIDELINE_PRINT WHERE GUIDELINE_ID=? ";
public static final String DELETE_OLD_PRINT_COMBINATION="DELETE FROM IGRS_GUIDELINE_PRINT WHERE GUIDELINE_ID=? ";
public static final String INSERT_NEW_PRINT_COMBINATIONS="call igrs_print_guideline_proc(?,?,?)";
public static final String UPDATE_GUIDELINE_PRINT_STATUS="UPDATE IGRS_GUIDELINE_PARENT_TEMP SET PRINT_STATUS='C',SR_PUBLISH_STATUS=3 WHERE GUIDELINE_ID=? ";
public static final String CHECK_ALREADY_SUBMMITTED_COLONY = "SELECT COUNT(*) FROM IGRS_GUIDELINE_STATUS WHERE GUIDELINE_ID=? AND COLONY_ID=? AND MAKER_STATUS=2";
public static final String GET_CONSTRUCTION_RATES="SELECT RCC_RATE,RBC_RATE,TINSHADE_RATE,KABELU_RATE FROM IGRS_CONST_RATES_MASTER WHERE APPLICABLE_SUBCLAUSE_ID=? AND STATUS='A' "; 
}

