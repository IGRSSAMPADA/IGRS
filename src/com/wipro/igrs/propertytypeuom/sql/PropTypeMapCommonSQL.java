package com.wipro.igrs.propertytypeuom.sql;

public class PropTypeMapCommonSQL {
	
	//constants
	
	public static final String SELECT_ALL_TYPE_MAPPING ="select IGRS_PROPERTY_TYPE_UOM_MAP_ID,property_type_name,property_type_l1_name,property_type_l2_name,uom_name from IGRS_PROPERTY_TYPE_UOM_MAP mapp,IGRS_PROPERTY_TYPE_MASTER t,IGRS_PROP_TYPE_L1_MASTER l1,IGRS_PROP_TYPE_L2_MASTER l2,IGRS_UOM_MASTER uom where t.PROPERTY_TYPE_ID=mapp.PROPERTY_TYPE_ID and l1.PROPERTY_TYPE_L1_ID=mapp.PROPERTY_L1_ID and l2.PROPERTY_TYPE_L2_ID=mapp.PROPERTY_L2_ID and uom.UOM_ID=mapp.UOM_ID";
	public static final String DELETE_MAPPING_BY_ID="delete from IGRS_PROPERTY_TYPE_UOM_MAP where IGRS_PROPERTY_TYPE_UOM_MAP_ID=?";
	public static final String GET_ALL_PROPERTY_TYPE_MASTER="select property_type_id,property_type_name from IGRS_PROPERTY_TYPE_MASTER";
	public static final String GET_ALL_L1_MASTER="select property_type_l1_id,property_type_l1_name from IGRS_PROP_TYPE_L1_MASTER";
	public static final String GET_ALL_L2_MASTER="select property_type_l2_id,property_type_l2_name from IGRS_PROP_TYPE_L2_MASTER";
	public static final String GET_ALL_UOM_MASTER="select uom_id,uom_name from IGRS_UOM_MASTER";
	public static final String IS_EXIST="select * from IGRS_PROPERTY_TYPE_UOM_MAP where PROPERTY_TYPE_ID=? and PROPERTY_L1_ID=? and PROPERTY_L2_ID=? and UOM_ID=?";
	public static final String ADD_NEW_MAPPING="insert into IGRS_PROPERTY_TYPE_UOM_MAP(PROPERTY_TYPE_ID,PROPERTY_L1_ID,PROPERTY_L2_ID,UOM_ID,IGRS_PROPERTY_TYPE_UOM_MAP_ID) values(?,?,?,?,?||IGRS_PROPERTY_TYPE_UOM_MAP_SEQ.nextval)";
	public static final String GET_MAPPING_BY_ID="select * from IGRS_PROPERTY_TYPE_UOM_MAP where IGRS_PROPERTY_TYPE_UOM_MAP_ID=?";
	public static final String UPDATE_MAPPING_DATA="UPDATE IGRS_PROPERTY_TYPE_UOM_MAP set PROPERTY_TYPE_ID=?,PROPERTY_L1_ID=?,PROPERTY_L2_ID=?,UOM_ID=? where IGRS_PROPERTY_TYPE_UOM_MAP_ID=?";
	public static final String GET_RELATED_L2_DATA="select property_type_l2_id,property_type_l2_name from IGRS_PROP_TYPE_L2_MASTER where PROPERTY_TYPE_L1_ID=?";
	public static final String GET_RELATED_L1_DATA="select property_type_l1_id,property_type_l1_name from IGRS_PROP_TYPE_L1_MASTER where PROPERTY_TYPE_ID=?";

	
	
	
}
