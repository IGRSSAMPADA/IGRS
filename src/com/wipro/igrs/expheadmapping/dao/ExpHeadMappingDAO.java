/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   ExpHeadMappingDAO.java
 * Author      :   Hend M. ismail
 * Description :   Represents DAO Class for Exp. Head Mapping Master.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0              Hend M. ismail  18th Augest, 2008	 		 
 * --------------------------------------------------------------------------------
 */

package com.wipro.igrs.expheadmapping.dao;

import java.util.ArrayList;

import org.apache.log4j.Logger;

 
import com.wipro.igrs.expheadmapping.dto.ExpHeadMappingDTO;
import com.wipro.igrs.expheadmapping.sql.ExpHeadMappingCommonSQL;
import com.wipro.igrs.db.DBUtility;

public class ExpHeadMappingDAO {

	private ArrayList expHeadList = new ArrayList();
	private ArrayList majorHeadList = new ArrayList();
	private ArrayList subMajorHeadList = new ArrayList();
	private ArrayList minorHeadList = new ArrayList();
	private ArrayList objectList = new ArrayList();
	private ArrayList schemeList = new ArrayList();
	private ArrayList segmentList = new ArrayList();
	private ArrayList detailedList = new ArrayList();
	
    DBUtility dbUtility = null;
    String sql = null;
    ArrayList subList = null;
    
    ExpHeadMappingDTO expHeadDTO=null;


    

	private Logger logger = (Logger) Logger.getLogger(ExpHeadMappingDAO.class);

	/* DAO constructor */
	public ExpHeadMappingDAO() {

		 
	}
	
	/* GET MINOR HEAD BY SUB-MID */
	
	
	public  ArrayList getMinorBySubMajor(String subMajorid) throws Exception {
		try {
			sql = ExpHeadMappingCommonSQL.SELECT_EXP_MINOR_HEAD_MASTER_SUB_MAJOR_ID;
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			String sd[] = new String[1];
			sd[0] = subMajorid;
	        ArrayList mainList1= dbUtility.executeQuery(sd);
	        for (int i=0;i<mainList1.size();i++) 
	         {
	         subList = (ArrayList)mainList1.get(i);
	         expHeadDTO = new ExpHeadMappingDTO();
	         expHeadDTO.setMinorHeadId(subList.get(0).toString());
	         expHeadDTO.setMinorHeadName(subList.get(1).toString());
	         minorHeadList.add(expHeadDTO);
	         //dbUtility.commit();
	         }
	        } catch (Exception e) {
	          e.getStackTrace();
	        }finally {
	        	dbUtility.closeConnection();
	        }
	    return minorHeadList;
	}
	/*GET EXP HEAD MAPPING ID*/
	public ExpHeadMappingDTO getExpHeadMappingId(String majorid) throws Exception {
		try {
			sql = ExpHeadMappingCommonSQL.SELECT_EXP_HEAD_MAPPING_MAJOR_ID;
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			String sd[] = new String[1];
			sd[0] = majorid;
			ArrayList list = dbUtility.executeQuery(sd);
			ArrayList subList = (ArrayList) list.get(0);
			expHeadDTO = new ExpHeadMappingDTO();
			expHeadDTO.setMajorHeadId(subList.get(0).toString());
		    expHeadDTO.setMajorHeadName(subList.get(1).toString());
		    expHeadDTO.setSubMajorHeadId(subList.get(2).toString());
		    expHeadDTO.setSubMajorHeadName(subList.get(3).toString());
		    expHeadDTO.setMinorHeadId(subList.get(4).toString());
		    expHeadDTO.setMinorHeadName(subList.get(5).toString());
		    expHeadDTO.setSchemeId(subList.get(6).toString());
		    expHeadDTO.setSchemeName(subList.get(7).toString());
		    expHeadDTO.setSegmentId(subList.get(8).toString());
		    expHeadDTO.setSegmentName(subList.get(9).toString());
		    expHeadDTO.setObjectId(subList.get(10).toString());
		    expHeadDTO.setObjectName(subList.get(11).toString());
		    expHeadDTO.setDetailedHeadId(subList.get(12).toString());
		    expHeadDTO.setDetailedHeadName(subList.get(13).toString());
		 //   expHeadDTO.setDroId(subList.get(14).toString());


		} catch (Exception e) {
		//	e.getStackTrace();
			e.printStackTrace();
		}finally{
			dbUtility.closeConnection();
		}
		return expHeadDTO;
	}
	/* GET EXP HEAD MAPPING LIST  */
	public ArrayList getExpHeadList()throws Exception
	{
		try {
			dbUtility = new DBUtility();
	        sql = ExpHeadMappingCommonSQL.SELECT_EXP_HEAD_MAPPING_JOIN;
	        dbUtility.createStatement();
	       
	        ArrayList mainList1= dbUtility.executeQuery(sql);
	        for (int i=0;i<mainList1.size();i++) 
	         {
	         subList = (ArrayList)mainList1.get(i);
	         expHeadDTO = new ExpHeadMappingDTO();
	         expHeadDTO.setMajorHeadId(subList.get(0).toString());
	         expHeadDTO.setMajorHeadName(subList.get(1).toString());
	         expHeadDTO.setSubMajorHeadId(subList.get(2).toString());
	         expHeadDTO.setSubMajorHeadName(subList.get(3).toString());
	         expHeadDTO.setMinorHeadId(subList.get(4).toString());
	         expHeadDTO.setMinorHeadName(subList.get(5).toString());
	         expHeadDTO.setSchemeId(subList.get(6).toString());
	         expHeadDTO.setSchemeName(subList.get(7).toString());
	         expHeadDTO.setSegmentId(subList.get(8).toString());
	         expHeadDTO.setSegmentName(subList.get(9).toString());
	         expHeadDTO.setObjectId(subList.get(10).toString());
	         expHeadDTO.setObjectName(subList.get(11).toString());
	         expHeadDTO.setDetailedHeadId(subList.get(12).toString());
	         expHeadDTO.setDetailedHeadName(subList.get(13).toString());
	         expHeadDTO.setDroId(subList.get(14).toString());
	         
	         expHeadList.add(expHeadDTO);
	         //dbUtility.commit();
	         }
	        } catch (Exception e) {
	          e.getStackTrace();
	        }finally {
	        	dbUtility.closeConnection();
	        }
	    return expHeadList;
		
	}

	/* GET MAJOR HEAD LIST  */
	public ArrayList getMajorHeadList()throws Exception
	{
		try {
	        sql = ExpHeadMappingCommonSQL.SELECT_EXP_MAJOR_HEAD_MASTER;
	        dbUtility = new DBUtility();
	        dbUtility.createStatement();
	       
	        ArrayList mainList1= dbUtility.executeQuery(sql);
	        for (int i=0;i<mainList1.size();i++) 
	         {
	         subList = (ArrayList)mainList1.get(i);
	         expHeadDTO = new ExpHeadMappingDTO();
	         expHeadDTO.setMajorHeadId(subList.get(0).toString());
	         expHeadDTO.setMajorHeadName(subList.get(1).toString());
	         majorHeadList.add(expHeadDTO);
	         //dbUtility.commit();
	         }
	        } catch (Exception e) {
	          e.getStackTrace();
	        }finally {
	        	dbUtility.closeConnection();
	        }
	    return majorHeadList;
		
	}
	/* GET SUB MAJOR HEAD LIST  */
	public ArrayList getSubMajorHeadList()throws Exception
	{
		try {
			dbUtility = new DBUtility();
	        sql = ExpHeadMappingCommonSQL.SELECT_EXP_SUB_MAJOR_HEAD_MASTER;
	        dbUtility.createStatement();
	       
	        ArrayList mainList1= dbUtility.executeQuery(sql);
	        for (int i=0;i<mainList1.size();i++) 
	         {
	         subList = (ArrayList)mainList1.get(i);
	         expHeadDTO = new ExpHeadMappingDTO();
	         expHeadDTO.setSubMajorHeadId(subList.get(0).toString());
	         expHeadDTO.setSubMajorHeadName(subList.get(1).toString());
	         subMajorHeadList.add(expHeadDTO);
	         //dbUtility.commit();
	         }
	        } catch (Exception e) {
	          e.getStackTrace();
	        }finally {
	        	dbUtility.closeConnection();
	        }
	    return subMajorHeadList;
		
	}
	
	/* GET MINOR HEAD LIST  */
	public ArrayList getMinorHeadList()throws Exception
	{
		try {
			dbUtility = new DBUtility();
	        sql = ExpHeadMappingCommonSQL.SELECT_EXP_MINOR_HEAD_MASTER;
	        dbUtility.createStatement();
	       
	        ArrayList mainList1= dbUtility.executeQuery(sql);
	        for (int i=0;i<mainList1.size();i++) 
	         {
	         subList = (ArrayList)mainList1.get(i);
	         expHeadDTO = new ExpHeadMappingDTO();
	         expHeadDTO.setMinorHeadId(subList.get(0).toString());
	         expHeadDTO.setMinorHeadName(subList.get(1).toString());
	         minorHeadList.add(expHeadDTO);
	         //dbUtility.commit();
	         }
	        } catch (Exception e) {
	          e.getStackTrace();
	        }finally {
	        	dbUtility.closeConnection();
	        }
	    return minorHeadList;
		
	}
	/* GET OBJECT LIST  */
	public ArrayList getObjectList()throws Exception
	{
		try {
			dbUtility = new DBUtility();
	        sql = ExpHeadMappingCommonSQL.SELECT_EXP_OBJECT_MASTER;
	        dbUtility.createStatement();
	       
	        ArrayList mainList1= dbUtility.executeQuery(sql);
	        for (int i=0;i<mainList1.size();i++) 
	         {
	         subList = (ArrayList)mainList1.get(i);
	         expHeadDTO = new ExpHeadMappingDTO();
	         expHeadDTO.setObjectId(subList.get(0).toString());
	         expHeadDTO.setObjectName(subList.get(1).toString());
	         objectList.add(expHeadDTO);
	         //dbUtility.commit();
	         }
	        } catch (Exception e) {
	          e.getStackTrace();
	        }finally {
	        	dbUtility.closeConnection();
	        }
	    return objectList;
		
	}
	/* GET SEGMENT LIST  */
	public ArrayList getSegmentList()throws Exception
	{
		try {
			dbUtility = new DBUtility();
	        sql = ExpHeadMappingCommonSQL.SELECT_EXP_SEGMENT_MASTER;
	        dbUtility.createStatement();
	       
	        ArrayList mainList1= dbUtility.executeQuery(sql);
	        for (int i=0;i<mainList1.size();i++) 
	         {
	         subList = (ArrayList)mainList1.get(i);
	         expHeadDTO = new ExpHeadMappingDTO();
	         expHeadDTO.setSegmentId(subList.get(0).toString());
	         expHeadDTO.setSegmentName(subList.get(1).toString());
	         segmentList.add(expHeadDTO);
	         //dbUtility.commit();
	         }
	        } catch (Exception e) {
	          e.getStackTrace();

	        }finally {
	        	dbUtility.closeConnection();
	        }
	    return segmentList;
		
	}
	
	/* GET SCHEME LIST  */
	public ArrayList getSchemeList()throws Exception
	{
		try {
			dbUtility = new DBUtility();
	        sql = ExpHeadMappingCommonSQL.SELECT_EXP_SCHEME_MASTER;
	        dbUtility.createStatement();
	       
	        ArrayList mainList1= dbUtility.executeQuery(sql);
	        for (int i=0;i<mainList1.size();i++) 
	         {
	         subList = (ArrayList)mainList1.get(i);
	         expHeadDTO = new ExpHeadMappingDTO();
	         expHeadDTO.setSchemeId(subList.get(0).toString());
	         expHeadDTO.setSchemeName(subList.get(1).toString());
	         schemeList.add(expHeadDTO);
	         //dbUtility.commit();
	         }
	        } catch (Exception e) {
	          e.getStackTrace();
	        }finally {
	        	dbUtility.closeConnection();
	        }
	    return schemeList;
		
	}
	
	/* INSERT EXP HEAD MAPPING MASTER */
	public void insertExpHeadMappingMster(ExpHeadMappingDTO dto) {
		
		String param[] = new String[8];
		param[0] = dto.getMajorHeadId();
		param[1] = dto.getSubMajorHeadId();
		param[2] = dto.getMinorHeadId();
		param[3] = dto.getSchemeId();
		param[4] = dto.getSegmentId();
		param[5] = dto.getObjectId();
		param[6] = dto.getDetailedHeadId();
		param[7] = dto.getDroId();
		
		
		sql = ExpHeadMappingCommonSQL.INSERT_EXP_HEAD_MAPPING_MASTER;

		try {
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			boolean boo = dbUtility.executeUpdate(param);
			if (boo) {
				dbUtility.commit();
			}else {
				dbUtility.rollback();
			}	
		} catch (Exception e) {
			//e.getStackTrace();
			e.printStackTrace();
		}finally {
			try {
				dbUtility.closeConnection();
			}catch(Exception x){
				x.getStackTrace();
			}
		}
	}
	
	/* GET DETAILED HEAD LIST  */
	public ArrayList getDetailedHeadList()throws Exception
	{
		try {
			dbUtility = new DBUtility();
	        sql = ExpHeadMappingCommonSQL.SELECT_EXP_DETAILED_HEAD_MASTER;
	        dbUtility.createStatement();
	       
	        ArrayList mainList1= dbUtility.executeQuery(sql);
	        for (int i=0;i<mainList1.size();i++) 
	         {
	         subList = (ArrayList)mainList1.get(i);
	         expHeadDTO = new ExpHeadMappingDTO();
	         expHeadDTO.setDetailedHeadId(subList.get(0).toString());
	         expHeadDTO.setDetailedHeadName(subList.get(1).toString());
	         detailedList.add(expHeadDTO);
	         //dbUtility.commit();
	         }
	        } catch (Exception e) {
	          e.getStackTrace();
	        }finally {
	        	dbUtility.closeConnection();
	        }
	    return detailedList;
		
	}

	/* UPDATE EXP HEAD MAPPING MASTER */

	public void updateExpHeadMappingMaster(ExpHeadMappingDTO dto) {
		
		String param[] = new String[8];
		param[0] = dto.getSubMajorHeadId();
		param[1] = dto.getMinorHeadId();
		param[2] = dto.getSchemeId();
		param[3] = dto.getSegmentId();
		param[4] = dto.getObjectId();
		param[5] = dto.getDetailedHeadId();
		param[6] = dto.getDroId();
		param[7] = dto.getMajorHeadId();
		
		sql = ExpHeadMappingCommonSQL.UPDATE_EXP_HEAD_MASTER;

		try {
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			boolean boo = dbUtility.executeUpdate(param);
			if (boo) {
				dbUtility.commit();
			}else {
				dbUtility.rollback();
			}	
		} catch (Exception e) {
			//e.getStackTrace();
			e.printStackTrace();
		}finally {
			try {
				dbUtility.closeConnection();
			}catch(Exception x){
				x.getStackTrace();
			}
		}
	}
	/* DELETE EXP HEAD MAPPING MASTER */
	
	public void deleteExpHeadMaster(String id){
		
		String param[] = new String[1];
		param[0] = id;

		sql = ExpHeadMappingCommonSQL.DELETE_EXP_HEAD_MASTER;

		try {
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			boolean boo = dbUtility.executeUpdate(param);
			if (boo) {
				dbUtility.commit();
			}else {
				dbUtility.rollback();
			}	
		} catch (Exception e) {
			e.getStackTrace();

		}finally {
			try {
				dbUtility.closeConnection();
			}catch(Exception x){
				x.getStackTrace();
			}
		}
}
}
