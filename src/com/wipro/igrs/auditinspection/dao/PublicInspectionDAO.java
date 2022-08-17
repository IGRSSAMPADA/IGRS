/*
* Copyright Notice
*==============================================================================
* This file contains proprietary information of IGRS Bhopal Madhya Pradesh .
* Copying or reproduction without prior written approval is prohibited.
* Copyright (c) 2007-08
*==============================================================================
*
* File Name   :   
 * Author      :   
 * Description :   
 * ----------------------------------------------------------------------------
* Version         Modified By     Modified On           Modification
* ----------------------------------------------------------------------------
* 1.0             SHRUTI          xxxxx              
 * --------------------------------------------------------------------------------
*/

package com.wipro.igrs.auditinspection.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeMap;

import org.apache.log4j.Logger;


import com.wipro.igrs.auditinspection.dto.POIAddObjectionDTO;
import com.wipro.igrs.auditinspection.dto.PublicDTO;
import com.wipro.igrs.auditinspection.sql.PublicOfficeInspectionSQL;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.empmgmt.dto.AcademicDTO;
import com.wipro.igrs.empmgmt.sql.CommonSQL;
import com.wipro.igrs.login.action.LoginAction;

public class PublicInspectionDAO {

	
		private Logger logger = 
			(Logger) Logger.getLogger(LoginAction.class);
		
		
		
		public ArrayList getOffice(String userId){
			ArrayList list = null;
			DBUtility dbUtil = null;
			String SQL = PublicOfficeInspectionSQL.GETPOOFFICE;
			String arry[] = new String[1];
			if (userId != null) {
				arry[0] = "A";
			}
			try {
				dbUtil = new DBUtility();
				dbUtil.createPreparedStatement(SQL);
				list = dbUtil.executeQuery(arry);
				
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				try {
					if (dbUtil != null) {
						dbUtil.closeConnection();
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			return list;
		}
		public String getOfficeName(String office){
			String list=null;
			DBUtility dbUtil = null;
			try{
				 dbUtil=new DBUtility();
				 String[] param={office};
				 String str=PublicOfficeInspectionSQL.GETOFFICENAME;
				 dbUtil.createPreparedStatement(str);
			    //dbUtil.createStatement();
			    list = dbUtil.executeQry(param);
			}catch(Exception ex){
				ex.printStackTrace();
				
			}
			finally{
				try{
					if(dbUtil!=null){
						dbUtil.closeConnection();
					}
				}catch(Exception exp){
					exp.printStackTrace();
				}
			}
			return list;
		}
		
		
		public ArrayList getDistrict(String office,String language) {
			ArrayList list=null;
			DBUtility dbUtil = null;
			try{
				 dbUtil=new DBUtility();
			     dbUtil.createStatement();
			     if("en".equalsIgnoreCase(language))
			     {
			     list = dbUtil.executeQuery(PublicOfficeInspectionSQL.GETDISTRICT);
			     }
			     else
			     {
			    	 list = dbUtil.executeQuery(PublicOfficeInspectionSQL.GETDISTRICT_H);
			     }
			}catch(Exception ex){
				ex.printStackTrace();
				
			}
			finally{
				try{
					if(dbUtil!=null){
						dbUtil.closeConnection();
					}
				}catch(Exception exp){
					exp.printStackTrace();
				}
			}  
			
			return list;
		}
		
		/*
		public boolean insertPublic(PublicDTO publicInspectionDTO,POIreportDTO poireportDTO,POIreportDTO1 poireportDTO1)throws Exception
		{
			System.out.println("PersonbalDetailsDAO ->insertEmpFamilyMaster()");
			boolean b1 = false, b2 = false, b3=false;
			
			b1 = addPublicDTO(publicInspectionDTO);
			
			b2 = addpoireportDTO(poireportDTO);
			
			b3 = addpoireportDTO1(poireportDTO1);

			if(b1 && b2 && b3)
				{	try {
						dbUtil.commit();
					} catch (Exception e) {
						e.printStackTrace();
					}	
				}
				else
				{
					try {
						dbUtil.rollback();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			return (b1 && b2 && b3);
		}
		
public boolean addPublicDTO(PublicDTO publicInspectionDTO)throws Exception
		{
			dbUtil.setAutoCommit(false);
			boolean flag = false;
			
					String sqlValues[] = {
							publicInspectionDTO.getPubOfficeName(),
							publicInspectionDTO.getOfficeId()
							};
					CommonSQL common = new CommonSQL();
					String sqlQuery = CommonSQL.INSERT_EMP_ACADEMIC_DETAILS;
					dbUtil.createPreparedStatement(sqlQuery);
					flag = dbUtil.executeUpdate(sqlValues);
					if (flag == false) {
						
					}
			
			return flag;	
		}
public boolean addpoireportDTO(POIreportDTO poireportDTO)throws Exception
{
	dbUtil.setAutoCommit(false);
	boolean flag = false;
	
			String sqlValues[] = {
					poireportDTO.getPubOfficeName(),
					poireportDTO.getOfficeId()
					};
			CommonSQL common = new CommonSQL();
			String sqlQuery = CommonSQL.INSERT_EMP_ACADEMIC_DETAILS;
			dbUtil.createPreparedStatement(sqlQuery);
			flag = dbUtil.executeUpdate(sqlValues);
			if (flag == false) {
				
			}
	
	return flag;	
}
*/
/**
 * @param arr
 * @return
 */
public ArrayList getExistingReports(String[] arr){
	ArrayList list=null;
	DBUtility dbUtil = null;
	try{
			dbUtil=new DBUtility();
			dbUtil.createPreparedStatement(PublicOfficeInspectionSQL.GET_EXISTING_INSPECTION_DETAILS);
			list = dbUtil.executeQuery(arr);
			}catch(Exception ex){
				ex.printStackTrace();
		
			}
			finally{
				try{
					if(dbUtil!=null){
						dbUtil.closeConnection();
					}
				}catch(Exception exp){
					exp.printStackTrace();
				}
	}  
	
	return list;	
}

/**
 * @param arr
 * @return
 */
public ArrayList getExistingPOIDetails(String[] arr){
	ArrayList list   = new ArrayList();
	DBUtility dbUtil = null;
	try{
			dbUtil=new DBUtility();
			dbUtil.createPreparedStatement(PublicOfficeInspectionSQL.GET_EXISTING_POIID_DETAILS);
			ArrayList listDetails = dbUtil.executeQuery(arr);
			}catch(Exception ex){
				ex.printStackTrace();
		
			}
			finally{
				try{
					if(dbUtil!=null){
						dbUtil.closeConnection();
					}
				}catch(Exception exp){
					exp.printStackTrace();
				}
	}  
	
	return list;	
}

public String submitInspectionReport(ArrayList listDetails) {
	String returnVal = "false";
	String[] txnDetails = (String[]) listDetails.get(0);
	String auditFilePath = (String) listDetails.get(1);
	String[] fileNames = (String[]) listDetails.get(2);
	String[] fileDetails = (String[]) listDetails.get(3);
	String[] paraDetails = (String[]) listDetails.get(4);
	ArrayList objection  = (ArrayList) listDetails.get(5);
	ArrayList agmpComm  = (ArrayList) listDetails.get(6);
	

	ArrayList listReportId = new ArrayList();
	ArrayList listParaTxnId = new ArrayList();
	ArrayList listCommTxnId = new ArrayList();
	ArrayList listObjTxnId = new ArrayList();
	ArrayList listDocTxnId = new ArrayList();
	ArrayList listDocMapping = new ArrayList();
	DBUtility dbUtil = null;
	try {
		dbUtil = new DBUtility();
		dbUtil.setAutoCommit(false);

		// Insertion In IGRS_RAUDIT_TXN_DETAILS
		boolean flagReoprtTxn = false;
		try {
			ArrayList tempReportId = getReportTxnId();
			ArrayList tempReportId2 = (ArrayList) tempReportId.get(0);
			txnDetails[12] = (String) tempReportId2.get(0);
			listReportId.add(txnDetails[12]);
			dbUtil.createPreparedStatement(PublicOfficeInspectionSQL.insert_IGRS_POI_TXN);
			flagReoprtTxn = dbUtil.executeUpdate(txnDetails);
			// listReportId=common.getReportId();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// Insertion In IGRS_RAUDIT_DOCUMENT_DETAILS

		boolean flagDocTxn = false;
		try {
			for (int i = 0; i < fileNames.length; i++) {
				ArrayList listDocId = getDocTxnId();
				ArrayList listDocId2 = (ArrayList) listDocId.get(0);
				String docId = (String) listDocId2.get(0);
				//MODIFIED BY SHRUTI---18TH OCT 2013---BLOB REMOVED
				flagDocTxn = dbUtil.saveAGMPAuditReport(docId,auditFilePath, fileNames[i], fileDetails,"inspection");
				/*flagDocTxn = dbUtil.attachAGMPAuditReport(docId,
						auditFilePath, fileNames[i], fileDetails,"inspection");*/
				listDocTxnId.add(docId);
				
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// Insertion In IGRS_POI_PARA_DETAILS
		boolean flagParaTxn = false;
		try {
			
			
			ArrayList paraTypeId = getParaTypeId(paraDetails[0]);
			ArrayList paraId = (ArrayList) paraTypeId.get(0);
			String Id = (String) paraId.get(0);
			paraDetails[0] =  Id;
			
			ArrayList para = getMaxParaId();
			ArrayList paraRow = (ArrayList) para.get(0);
			String paraTxn = (String) paraRow.get(0);
			paraDetails[4] =  paraTxn;
			dbUtil.createPreparedStatement(PublicOfficeInspectionSQL.INSERT_IGRS_POI_PARA_DETAILS);
			flagParaTxn = dbUtil.executeUpdate(paraDetails);
			listParaTxnId.add(paraDetails[4]);
			
			// listParaTxnId=common.getMaxParaId();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		// Insertion In IGRS_RAUDIT_PARA_OBJ_DETAILS
		boolean flagObjTxn = false;
		for (int i = 0; i < objection.size(); i++) {
			 String[] objectionDetails = (String[])objection.get(i);
			try {
				ArrayList listObj = getMaxObjectionId();
				ArrayList listObj2 = (ArrayList) listObj.get(0);		
				objectionDetails[7] = (String) listObj2.get(0);
				dbUtil.createPreparedStatement(PublicOfficeInspectionSQL.INSERT_IGRS_POI_PARA_OBJ);
				flagObjTxn = dbUtil.executeUpdate(objectionDetails);
				listObjTxnId.add(objectionDetails[7]);
				} catch (Exception e) {
				flagObjTxn = false;
				
				e.printStackTrace();
			}
		}
		// Insertion In IGRS_RAUDIT_COMMENT_DETAILS
		boolean flagComTxn = false;
		for (int i = 0; i < agmpComm.size(); i++) {
			//modified by shruti----14th oct 2013
			String[] agmpComments = (String[])agmpComm.get(i);
			//String[] agmpComments=new String[2];
			//agmpComments[0]=(String)agmpComm.get(i);
			try {
				ArrayList listAgmp = getMaxCommentId();
				ArrayList listAgmp2 = (ArrayList) listAgmp.get(0);
				agmpComments[2] = (String) listAgmp2.get(0);
				dbUtil.createPreparedStatement(PublicOfficeInspectionSQL.INSERT_IGRS_POI_PARA_COMMENT);
				flagComTxn = dbUtil.executeUpdate(agmpComments);
				listCommTxnId.add(agmpComments[2]);
				
			} catch (Exception e) {
				flagComTxn = false;
				
				e.printStackTrace();
			}
		}

		// Insertion In IGRS_RAUDIT_COMMENTS_MAPPING
		boolean flagComMapTxn = false;
		try {
			String[] commentMapping = new String[4];
			for (int i = 0; i < listCommTxnId.size(); i++) {
				
				commentMapping[0] = (String) listReportId.get(0);
				commentMapping[1] = (String) listCommTxnId.get(i);
				commentMapping[2] = (String) listParaTxnId.get(0);
				commentMapping[3] = (String) listObjTxnId.get(i);
				dbUtil.createPreparedStatement(PublicOfficeInspectionSQL.INSERT_IGRS_POI_COMMENT_MAPPING);
				flagComMapTxn = dbUtil.executeUpdate(commentMapping);
				

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// Insertion In IGRS_RAUDIT_DOC_MAPPING
		String[] docMapping = new String[4];
		boolean flagDocMapTxn = false;
		try {
			for (int i = 0; i < listDocTxnId.size(); i++) {
				docMapping[0] = (String) listReportId.get(0);
				docMapping[1] = (String) listDocTxnId.get(i);
				docMapping[2] = (String) listParaTxnId.get(0);
				if (listObjTxnId.size() == 1)
					docMapping[3] = (String) listObjTxnId.get(0);
				else
					docMapping[3] = (String) listObjTxnId.get(i);

				dbUtil.createPreparedStatement(PublicOfficeInspectionSQL.INSERT_IGRS_POI_DOC_MAPPING);
				flagDocMapTxn = dbUtil.executeUpdate(docMapping);

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// Insertion In IGRS_RAUDIT_PARA_MAPPING
		String[] paraMapping = new String[2];
		boolean flagParaMapTxn = false;
		try {
			paraMapping[0] = (String) listReportId.get(0);
			paraMapping[1] = (String) listParaTxnId.get(0);
			dbUtil
			.createPreparedStatement(PublicOfficeInspectionSQL.INSERT_IGRS_POI_PARA_MAPPING);
			flagParaMapTxn = dbUtil.executeUpdate(paraMapping);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// Insertion In IGRS_RAUDIT_PARA_OBJ_MAPPING
		/*String[] paraObjMapping = new String[2];
		boolean flagParaObjMapTxn = false;
		try {
			for (int i = 0; i < listObjTxnId.size(); i++) {
				paraObjMapping[0] = (String) listParaTxnId.get(0);
				paraObjMapping[1] = (String) listObjTxnId.get(i);

				dbUtil
				.createPreparedStatement(CommonSQL.INSERT_IGRS_RAUDIT_PARA_OBJ_MAPPING);
				flagParaObjMapTxn = dbUtil.executeUpdate(paraObjMapping);
				System.out.println("PARA OBJECTION FLAG :"
						+ flagParaObjMapTxn);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}*/
		
		/*System.out.println(" Para Object Mapping Flag :"
				+ flagParaObjMapTxn);*/

		if (flagReoprtTxn && flagDocTxn && flagParaTxn && flagObjTxn&& flagComTxn 
				&& flagComMapTxn && flagDocMapTxn&& flagParaMapTxn ) 
		{
			dbUtil.commit();
			returnVal = txnDetails[12];
		
		} else {
			
			dbUtil.rollback();
			returnVal = "false";
		}
	} catch (Exception e) {

		try {
			dbUtil.rollback();
			returnVal = "false";
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		e.printStackTrace();
	} finally {
		try {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	return returnVal;
}
private ArrayList getReportTxnId() {

	ArrayList txnId = new ArrayList();
	DBUtility dbUtil = null;
	try {
		dbUtil = new DBUtility();
		dbUtil.createStatement();
		txnId = dbUtil
		.executeQuery(PublicOfficeInspectionSQL.RETRIEVE_SEQ_POI_TXN_DETAILS);
	} catch (Exception ex) {
		ex.printStackTrace();
	} finally {
		try {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	return txnId;
}

private ArrayList getDocTxnId() {
	ArrayList list = new ArrayList();
	DBUtility dbUtil = null;
	try {
		dbUtil = new DBUtility();
		dbUtil.createStatement();
		list = dbUtil
		.executeQuery("SELECT 'DOC'||LPAD(IGRS_POI_DOC_DTLS_SEQ.NEXTVAL,4,0) FROM DUAL");
		
	} catch (Exception ex) {
		ex.printStackTrace();
	} finally {
		try {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	return list;
}

public ArrayList getMaxParaId() throws Exception {
	ArrayList list = null;
	DBUtility dbUtil = null;
	try {
		dbUtil = new DBUtility();
		dbUtil.createStatement();
		
		list = dbUtil
		.executeQuery("SELECT 'PTXN'||LPAD(IGRS_POI_PARA_DTLS_SEQ.NEXTVAL,4,0) FROM DUAL");
		
	} catch (Exception ex) {
		ex.printStackTrace();
	} finally {
		try {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	return list;
}

private ArrayList getParaTypeId(String paraTypeName){
	ArrayList list = null;
	DBUtility dbUtil = null;
	//MODIFIED BY SHRUTI
	//String[] arr = new String[1];
	String[] arr = new String[2];
	arr[0]=paraTypeName;
	arr[1]=paraTypeName;
	try {
		dbUtil = new DBUtility();
		dbUtil.createPreparedStatement(PublicOfficeInspectionSQL.GET_IGRS_POI_GET_PARA_TYPE_ID);
		list = dbUtil.executeQuery(arr);
	} catch (Exception ex) {
		ex.printStackTrace();
	} finally {
		try {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	return list;
}

private ArrayList getMaxObjectionId() throws Exception {
	ArrayList list = null;
	DBUtility dbUtil = null;
	try {
		dbUtil = new DBUtility();
		dbUtil.createStatement();
		list = dbUtil
		.executeQuery(PublicOfficeInspectionSQL.GETPOIOBJECTIONID);
		
	} catch (Exception ex) {
		ex.printStackTrace();
	} finally {
		try {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	return list;
}

private ArrayList getMaxCommentId() throws Exception {
	ArrayList list = null;
	DBUtility dbUtil = null;
	try {
		dbUtil = new DBUtility();
		dbUtil.createStatement();
		list = dbUtil.executeQuery(PublicOfficeInspectionSQL.GETPOICOMMENTID);
		
	} catch (Exception ex) {
		ex.printStackTrace();
	} finally {
		try {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	return list;
}

public ArrayList getExistingDetails(String[] arr){
	ArrayList list = null;
	DBUtility dbUtil = null;
	try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(PublicOfficeInspectionSQL.GET_EXISTING_POI_DETAILS);
			list = dbUtil.executeQuery(arr);
	} catch (Exception ex) {
		ex.printStackTrace();
	} finally {
		try {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	return list;
}

public boolean updateInspectionReport(ArrayList list){
	boolean returnVal   = false;
	
	String[] paraDetails = (String[]) list.get(0);
	ArrayList objection  = (ArrayList)list.get(1);
	ArrayList agmpComm   = (ArrayList)list.get(2);
	String poiTxnId      = (String) list.get(3);
		
	ArrayList listParaTxnId = new ArrayList();
	ArrayList listCommTxnId = new ArrayList();
	ArrayList listObjTxnId  = new ArrayList();
	
	DBUtility dbUtil = null;
	try {
		dbUtil = new DBUtility();
		dbUtil.setAutoCommit(false);

		//INSETION INTO IGRS_POI_PARA_DTLS
		boolean flagParaTxn = false;
		try{
			ArrayList paraTypeId = getParaTypeId(paraDetails[0]);
			ArrayList paraId = (ArrayList) paraTypeId.get(0);
			String Id = (String) paraId.get(0);
			paraDetails[0] =  Id;
		
			ArrayList para = getMaxParaId();
			ArrayList paraRow = (ArrayList) para.get(0);
			String paraTxn = (String) paraRow.get(0);
			paraDetails[4] =  paraTxn;
			dbUtil
			.createPreparedStatement(PublicOfficeInspectionSQL.INSERT_IGRS_POI_PARA_DETAILS);
			flagParaTxn = dbUtil.executeUpdate(paraDetails);
			listParaTxnId.add(paraDetails[4]);
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		

		boolean flagObjTxn = false;
		for (int i = 0; i < objection.size(); i++) {
			 String[] objectionDetails = (String[])objection.get(i);
			try {
				ArrayList listObj = getMaxObjectionId();
				ArrayList listObj2 = (ArrayList) listObj.get(0);
							
				objectionDetails[7] = (String) listObj2.get(0);
				
				dbUtil.createPreparedStatement(PublicOfficeInspectionSQL.INSERT_IGRS_POI_PARA_OBJ);
				flagObjTxn = dbUtil.executeUpdate(objectionDetails);
				listObjTxnId.add(objectionDetails[7]);
				
			} catch (Exception e) {
				flagObjTxn = false;
			
				e.printStackTrace();
			}
		}
		// Insertion In IGRS_POI_COMMENT_DETAILS
		boolean flagComTxn = false;
		for (int i = 0; i < agmpComm.size(); i++) {
			String[] agmpComments = (String[])agmpComm.get(i);
			
			try {
				ArrayList listAgmp = getMaxCommentId();
				ArrayList listAgmp2 = (ArrayList) listAgmp.get(0);
				
				agmpComments[2] = (String) listAgmp2.get(0);
				
				
				dbUtil
				.createPreparedStatement(PublicOfficeInspectionSQL.INSERT_IGRS_POI_PARA_COMMENT);
				flagComTxn = dbUtil.executeUpdate(agmpComments);
				listCommTxnId.add(agmpComments[2]);
				
			} catch (Exception e) {
				flagComTxn = false;
				
				e.printStackTrace();
			}
		}

		boolean flagComMapTxn = false;
		try {
			String[] commentMapping = new String[4];
			for (int i = 0; i < listCommTxnId.size(); i++) {
				
				commentMapping[0] = poiTxnId;
				commentMapping[1] = (String) listCommTxnId.get(i);
				commentMapping[2] = (String) listParaTxnId.get(0);
				commentMapping[3] = (String) listObjTxnId.get(i);

				dbUtil
				.createPreparedStatement(PublicOfficeInspectionSQL.INSERT_IGRS_POI_COMMENT_MAPPING);
				flagComMapTxn = dbUtil.executeUpdate(commentMapping);
				

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// Insertion In IGRS_RAUDIT_PARA_MAPPING
		String[] paraMapping = new String[2];
		boolean flagParaMapTxn = false;
		try {
			paraMapping[0] = poiTxnId;
			paraMapping[1] = (String) listParaTxnId.get(0);
			dbUtil.createPreparedStatement(PublicOfficeInspectionSQL.INSERT_IGRS_POI_PARA_MAPPING);
			flagParaMapTxn = dbUtil.executeUpdate(paraMapping);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// Insertion In IGRS_RAUDIT_PARA_OBJ_MAPPING
		/*String[] paraObjMapping = new String[2];
		boolean flagParaObjMap = false;
		for (int i = 0; i < listObjTxnId.size(); i++) {
			paraObjMapping[0] = (String) listParaTxnId.get(0);
			paraObjMapping[1] = (String) listObjTxnId.get(i);

			dbUtil
			.createPreparedStatement(CommonSQL.INSERT_IGRS_RAUDIT_PARA_OBJ_MAPPING);
			flagParaObjMap = dbUtil.executeUpdate(paraObjMapping);

		}*/

				
		if (flagParaTxn && flagObjTxn && flagComTxn
				&& flagComMapTxn && flagParaMapTxn) {
			dbUtil.commit();
			returnVal = true;
		} else {
			dbUtil.rollback();
			returnVal = false;
		}
	} catch (Exception e) {

		try {
			dbUtil.rollback();
			returnVal = false;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		e.printStackTrace();
	} finally {
		try {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	return returnVal;
}

public ArrayList getPrintDetails(String[] arr){
	ArrayList list = new ArrayList();
	DBUtility dbUtil = null;
	try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(PublicOfficeInspectionSQL.GET_POI_TXN_DETAILS_PRINT);
			ArrayList list1 = dbUtil.executeQuery(arr);
			
			dbUtil.createPreparedStatement(PublicOfficeInspectionSQL.GET_POI_TXN_DOC_DETAILS_PRINT);
			ArrayList list2 = dbUtil.executeQuery(arr);
			
			dbUtil.createPreparedStatement(PublicOfficeInspectionSQL.GET_POI_TXN_PARA_DETAILS_PRINT);
			ArrayList list3 = dbUtil.executeQuery(arr);
			
			list.add(list1);
			list.add(list2);
			list.add(list3);
			
	} catch (Exception ex) {
		ex.printStackTrace();
	} finally {
		try {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	return list;
}
public ArrayList getExpenParaList(String language) {
	ArrayList list = null;
	DBUtility dbUtil = null;
	try {
		dbUtil = new DBUtility();
		dbUtil.createStatement();
		if("en".equalsIgnoreCase(language))
		{
		list = dbUtil.executeQuery(PublicOfficeInspectionSQL.SELECT_POI_PARA_DETAILS);
		}
		else
		{
			list = dbUtil.executeQuery(PublicOfficeInspectionSQL.SELECT_POI_PARA_DETAILS_H);
		}
		
	} catch (Exception ex) {
		ex.printStackTrace();
	} finally {
		try {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	return list;
}

public ArrayList getInspectionPoiId(String []droDetails){
	
	ArrayList list = null;
	DBUtility dbUtil = null;
	try {
		dbUtil = new DBUtility();
		String sql=PublicOfficeInspectionSQL.GET_POI_ID_DETAILS;
		TreeMap<String, String> paramsMap = new TreeMap<String, String>();
		if(droDetails[3]!=null)
		{
			
			paramsMap.put("1txnID", droDetails[3]);
			sql=sql+" AND POI_TXN_ID=?";
		}
		if(droDetails[4]!=null)
		{
			paramsMap.put("2Status", droDetails[4]);
			sql=sql+" AND UPPER(INSP_REPORT_STATUS)=?";
		}
		if( droDetails[0]!=null && droDetails[1]!=null)
		{
			sql=sql+" AND INSP_DATE  BETWEEN ? AND ?";
			paramsMap.put("4fromDate", droDetails[0]);
			paramsMap.put("5toDate", droDetails[1]);
		}
		if(droDetails[2]!=null)
		{
			sql=sql+" AND DISTRICT_ID = ?";
			paramsMap.put("3officeid", droDetails[2]);
		}
		
		dbUtil.createPreparedStatement(sql);
		Collection<String> values = paramsMap.values();
		ArrayList<String> tmp = new ArrayList(values);
//		Collections.reverse(tmp);
		String[] params = tmp.toArray(new String[]{}); 
		list  =  dbUtil.executeQuery(params);
		logger.debug("size is"+list.size());
		//logger.debug("list is"+list);
		dbUtil.createPreparedStatement(sql);
		
		
	} catch (Exception ex) {
		ex.printStackTrace();
	} finally {
		try {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	return list;
}

public ArrayList getPOIDetails(String id)
{
	ArrayList list = null;
	DBUtility dbUtil = null;
	String []param ={id};
	try {
		dbUtil = new DBUtility();
		dbUtil.createPreparedStatement(PublicOfficeInspectionSQL.GET_POI_DETAILS);
		list = dbUtil.executeQuery(param);
		
	} catch (Exception ex) {
		ex.printStackTrace();
	} finally {
		try {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	return list;
}
public ArrayList getDocName(String id)
{
	ArrayList list = null;
	DBUtility dbUtil = null;
	String []param ={id};
	try {
		dbUtil = new DBUtility();
		dbUtil.createPreparedStatement(PublicOfficeInspectionSQL.GET_DOC_NAMES);
		list = dbUtil.executeQuery(param);
		
	} catch (Exception ex) {
		ex.printStackTrace();
	} finally {
		try {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	return list;
}
public ArrayList getDocPath(String id)
{
	ArrayList list = null;
	DBUtility dbUtil = null;
	String []param ={id};
	try {
		dbUtil = new DBUtility();
		dbUtil.createPreparedStatement(PublicOfficeInspectionSQL.GET_DOC_PATH);
		list = dbUtil.executeQuery(param);
		
	} catch (Exception ex) {
		ex.printStackTrace();
	} finally {
		try {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	return list;
}
public ArrayList getParaDetails(String id,String language)
{
	ArrayList list = null;
	DBUtility dbUtil = null;
	String []param ={id};
	try {
		dbUtil = new DBUtility();
		if("en".equalsIgnoreCase(language))
		{
		dbUtil.createPreparedStatement(PublicOfficeInspectionSQL.GET_PARA_DETAILS);
		}
		else
		{
			dbUtil.createPreparedStatement(PublicOfficeInspectionSQL.GET_PARA_DETAILS_H);	
		}
		list = dbUtil.executeQuery(param);
		
	} catch (Exception ex) {
		ex.printStackTrace();
	} finally {
		try {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	return list;
}

public ArrayList getObjDetails(String id)
{
	ArrayList list = null;
	DBUtility dbUtil = null;
	String []param ={id};
	try {
		dbUtil = new DBUtility();
		dbUtil.createPreparedStatement(PublicOfficeInspectionSQL.GET_OBJECTED_DOC_DETAILS);
		list = dbUtil.executeQuery(param);
		
	} catch (Exception ex) {
		ex.printStackTrace();
	} finally {
		try {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	return list;
}

public ArrayList getParaDetailsComplete(String transid) throws Exception

{
        ArrayList paraList =null;
        DBUtility dbUtil = null;
        try {   
                dbUtil = new DBUtility();
                String[] param={transid};
                String str = PublicOfficeInspectionSQL.GETPARADTLS;
                //edited by shruti-15th oct 2013
                //String str = CommonSQL.GETPARADTLS1;
                dbUtil.createPreparedStatement(str);
                paraList = dbUtil.executeQuery(param);
        } catch (Exception e) {
                logger.info(e);
        } finally {
                if(dbUtil!=null){
                        dbUtil.closeConnection();
                        }
        }                               
        return paraList;               

}
public ArrayList getObjectionDetails(String transid) throws Exception

{
        ArrayList partyList =null;
        DBUtility dbUtil = null;
        try {   
                dbUtil = new DBUtility();
                String[] param={transid};
                String str = PublicOfficeInspectionSQL.GETOBJECTIONDETAILS;
                dbUtil.createPreparedStatement(str);
                partyList = dbUtil.executeQuery(param);
        } catch (Exception e) {
                logger.info(e);
        } finally {
                if(dbUtil!=null){
                        dbUtil.closeConnection();
                        }
        }                               
        return partyList;               

}
public ArrayList getObjectionParaDetails(String transid) throws Exception

{
        ArrayList objparaList =null;
        DBUtility dbUtil = null;
        try {   
                dbUtil = new DBUtility();
                String[] param={transid};
                String str = PublicOfficeInspectionSQL.GETOBJECTIONPARADETAILS;
                //edited by shruti-15th oct 2013
                //String str = CommonSQL.GETPARADTLS1;
                dbUtil.createPreparedStatement(str);
                objparaList = dbUtil.executeQuery(param);
        } catch (Exception e) {
                logger.info(e);
        } finally {
                if(dbUtil!=null){
                        dbUtil.closeConnection();
                        }
        }                               
        return objparaList;               
}
public boolean updateObjStatus(PublicDTO pdto)
{
	 boolean flag =false;
     DBUtility dbUtil = null;
     try {   
             dbUtil = new DBUtility();
             String[] param={pdto.getObjStatus(),pdto.getObjTxnId()};
             String str = PublicOfficeInspectionSQL.UPDATEOBJECTIONPARADETAILS;
             //edited by shruti-15th oct 2013
             //String str = CommonSQL.GETPARADTLS1;
             dbUtil.createPreparedStatement(str);
             flag = dbUtil.executeUpdate(param);
     } catch (Exception e) {
             logger.info(e);
     } finally {
             if(dbUtil!=null){
                     try {
						dbUtil.closeConnection();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                     }
     }                               
     return flag;  
}
public boolean updateParaStatus(PublicDTO pdto)
{
	 boolean flag =false;
     DBUtility dbUtil = null;
     try {   
             dbUtil = new DBUtility();
             //CHECK ADDED BY SHRUTI FOR EXISITING DATA FORM IN TABLE--24 feb 2014
             if(pdto.getParaStatus()!=null)
             {
            	 String modifyStatus=pdto.getParaStatus();
            	 if("OPEN".equalsIgnoreCase(modifyStatus))
            	 {
            		 pdto.setParaStatus("Y");
            	 }
             }
             //END
             String[] param={pdto.getParaComments(),pdto.getParaStatus(),pdto.getParaTxnid()};
             String str = PublicOfficeInspectionSQL.UPDATEPARASTATUS;
             //edited by shruti-15th oct 2013
             //String str = CommonSQL.GETPARADTLS1;
             dbUtil.createPreparedStatement(str);
             flag = dbUtil.executeUpdate(param);
     } catch (Exception e) {
             logger.info(e);
     } finally {
             if(dbUtil!=null){
                     try {
						dbUtil.closeConnection();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                     }
     }                               
     return flag;  
}
}
