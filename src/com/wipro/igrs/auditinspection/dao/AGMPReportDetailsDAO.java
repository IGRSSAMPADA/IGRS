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

import org.apache.log4j.Logger;

import com.wipro.igrs.auditinspection.dto.AGMPReportDetailsDTO;
import com.wipro.igrs.auditinspection.sql.AddComplianceSQL;
import com.wipro.igrs.auditinspection.sql.CommonSQL;
import com.wipro.igrs.db.DBUtility;

/**
 * @author oneapps
 * 
 */
public class AGMPReportDetailsDAO {
	// DBUtility dbUtil = null;

	Logger logger = 
		(Logger) Logger.getLogger(AGMPReportDetailsDAO.class);
	public AGMPReportDetailsDAO() {

		// dbUtil = new DBUtility();

	}

	/**
	 * @param OffTypeId
	 * @return
	 */
	public ArrayList getSRO(String userId) {
		ArrayList list = null;
		DBUtility dbUtil = null;
		String SQL = CommonSQL.GETSRO;
		String arry[] = new String[1];
		if (userId != null) {
			arry[0] = userId;
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

	/**
	 * @param OffTypeId
	 * @return
	 * @throws Exception
	 */
	public ArrayList getDistrictId(String OffTypeId) throws Exception {
		ArrayList list = null;
		DBUtility dbUtil = null;
		/*String SQL = CommonSQL.GETDISTRICT;*/
		String SQL = CommonSQL.GETDISTRICTID;
		String arry[] = new String[1];
		if (OffTypeId != null) {
			arry[0] = OffTypeId;
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

	//added by shruti---13 june 2014
	public String getDistId(String OffTypeId) throws Exception {
		String distId=null;
		DBUtility dbUtil = null;
		String SQL = CommonSQL.GETDISTRICTID;
		String arry[] = new String[1];
		if (OffTypeId != null) {
			arry[0] = OffTypeId;
		}
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(SQL);
			distId=dbUtil.executeQry(arry);
			if(distId.length()==1)
			{
				distId="0"+distId;
			}
			
			//list = dbUtil.executeQuery(arry);
			
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
		return distId;
	}
	public String getDist(String OffTypeId,String language) throws Exception {
		String distId=null;
		DBUtility dbUtil = null;
		String SQL="";
		if("en".equalsIgnoreCase(language))
		{
		SQL = CommonSQL.GETDISTRICTNAME;
		}
		else
		{
			SQL = CommonSQL.GETDISTRICTNAME_H;	
		}
		String arry[] = new String[1];
		if (OffTypeId != null) {
			arry[0] = OffTypeId;
		}
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(SQL);
			distId=dbUtil.executeQry(arry);
			
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
		return distId;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public ArrayList getMaxParaId() throws Exception {
		ArrayList list = null;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			list = dbUtil.executeQuery(CommonSQL.GETPARAID);
		
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

	/**
	 * @param regid
	 * @return
	 */
	public ArrayList getRegDetails(String regid[]) {
		
		ArrayList finalList=new ArrayList();
		
		ArrayList regidother = new ArrayList();
		DBUtility dbUtil = null;
		String regId="";
		try {
			//currently added by shruti-no data against other user id
			//regid[1]="7777777";
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.REG_DETAILS_RETRIEVE_QRY);
			regidother = dbUtil.executeQuery(regid);
			//added by shruti
			for (int i = 0; i < regidother.size(); i++) {
				ArrayList row_list = (ArrayList) regidother.get(i);
				{
					regId=(String)row_list.get(6);
				}
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
		return regidother;
	}

	//added by shruti 4th sep 2013
	public ArrayList getPartyDetails(String _refTranId,String language) throws Exception
	{
		ArrayList partyList =null;
		DBUtility dbUtil = null;
		try {	
			dbUtil = new DBUtility();
			String[] param={_refTranId};
			String str="";
			if("en".equalsIgnoreCase(language))
			{
			 str= CommonSQL.GETPARTYDETAILS;
			}
			else
			{
			str = CommonSQL.GETPARTYDETAILS_H;
			}
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
	//end
	
	
	public ArrayList getPropertyDetails(String _refTranId,String language) throws Exception{
		logger.debug(" DAO getPartyDetails--");
		
		ArrayList propertyList =null;
		
		DBUtility dbUtil = null;
		try {	
			dbUtil = new DBUtility();
			String[] param={_refTranId};
			String str="";
			if("en".equalsIgnoreCase(language))
			{
			str =CommonSQL.GETPROPERTYDETAILS;
			}
			else
			{
			str =CommonSQL.GETPROPERTYDETAILS;
			}
			
			
			logger.debug("in dao try for getPropertyDetails" + str);
			dbUtil.createPreparedStatement(str);
			propertyList =dbUtil.executeQuery(param) ;
			logger.debug("in dao try for getPartyDetails" + propertyList);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if(dbUtil!=null){
				dbUtil.closeConnection();
				}
				
		}				
		return propertyList;		
	}
	/**
	 * @param regid
	 * @return
	 */
	public ArrayList getRegOtherDetails(String regid[]) {
		ArrayList regidother = new ArrayList();
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.REG_OTHERS_RETRIEVE_QRY_EXCT);
			regidother = dbUtil.executeQuery(regid);
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
		return regidother;
	}

	/**
	 * @param sear
	 * @return
	 */
	public ArrayList getRegidInfo(String sear[]) {
		ArrayList search = new ArrayList();
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.REG_RETRIEVE_QUERY);
			String regid = sear[0];
			search = dbUtil.executeQueryLikeSearch(regid.toUpperCase());
			
		} catch (Exception e) {
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
		return search;
	}

	/**
	 * @param docid
	 * @return
	 */
	public ArrayList getDocIdDetails(String[] docid) {
		ArrayList docidarr = new ArrayList();
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil
					.createPreparedStatement(CommonSQL.REG_OTHERS_RETRIEVE_QRY_LIKE);
			String doc_id = docid[0];
			docidarr = dbUtil.executeQueryLikeSearch(doc_id.toUpperCase());
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
		return docidarr;
	}

	/**
	 * @param doc
	 * @return
	 */
	public boolean saveDocDetails(String doc[]) {
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil
					.createPreparedStatement(CommonSQL.INSERT_IGRS_RAUDIT_PARA_OBJ);
			dbUtil.executeUpdate(doc);
			return true;
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
		return false;
	}

	/**
	 * @param docId
	 * @return
	 */
	public ArrayList getRegDocStatus(String docId[]) {
		ArrayList docIdStatus = new ArrayList();
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil
					.createPreparedStatement(CommonSQL.RETRIEVE_REGISTERED_DOC_STATUS);
			docIdStatus = dbUtil.executeQuery(docId);
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
		return docIdStatus;
	}

	/**
	 * @param para
	 * @return
	 */
	public boolean saveParaDetails(String para[]) {
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil
					.createPreparedStatement(CommonSQL.INSERT_IGRS_RAUDIT_PARA_DETAILS);
			dbUtil.executeUpdate(para);

		} catch (Exception e) {
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
		return true;
	}

	/**
	 * @param docId
	 * @return
	 */
	public ArrayList getDocStatus(String docId[]) {
		ArrayList docIdStatus = new ArrayList();
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.RETRIEVE_DOC_STATUS);
			docIdStatus = dbUtil.executeQuery(docId);
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
		return docIdStatus;
	}

	/**
	 * @param regId
	 * @return
	 */
	public ArrayList getRegIdStatus(String[] regId) {
		ArrayList regIdStatus = new ArrayList();
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.RETRIEVE_REG_STATUS);
			regIdStatus = dbUtil.executeQuery(regId);
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
		return regIdStatus;
	}

	/**
	 * @param date
	 * @return
	 */
	public ArrayList getExistingTxn(String[] date) {
		ArrayList existing = new ArrayList();
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.RETRIEVE_EXISTING_DETAILS);
			existing = dbUtil.executeQuery(date);
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
		return existing;
	}
//added by shruti---20 june 2014
	public ArrayList getExistingAgmpRcptTxn(String[] date) {
		ArrayList existing = new ArrayList();
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.RETRIEVE_EXISTING_DETAILS_AGMP_RCPT);
			existing = dbUtil.executeQuery(date);
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
		return existing;
	}
	public ArrayList getExistingAgmpExpTxn(String[] date) {
		ArrayList existing = new ArrayList();
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.RETRIEVE_EXISTING_DETAILS_AGMP_EXP);
			existing = dbUtil.executeQuery(date);
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
		return existing;
	}
	public ArrayList getExistingInternalRcptTxn(String[] date) {
		ArrayList existing = new ArrayList();
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.RETRIEVE_EXISTING_DETAILS_INTERNAL_RCPT);
			existing = dbUtil.executeQuery(date);
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
		return existing;
	}
	public ArrayList getExistingInternalExpTxn(String[] date) {
		ArrayList existing = new ArrayList();
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.RETRIEVE_EXISTING_DETAILS_INTERNAL_EXP);
			existing = dbUtil.executeQuery(date);
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
		return existing;
	}
	//end

	/**
	 * @param audit
	 * @return
	 */
	public boolean insertAuditComments(String audit[]) {
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.INSERT_RAUDIT_COMMENTS);
			dbUtil.executeUpdate(audit);
		} catch (Exception e) {
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

		return true;
	}

	/**
	 * @return
	 */
	public ArrayList getSeqTransactionIds() {
		ArrayList seq = new ArrayList();
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			seq = dbUtil.executeQuery(CommonSQL.RETRIEVE_SEQ_TXN_IDS);
		} catch (Exception e) {
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
		return seq;
	}

	/**
	 * @param seqArray
	 * @return
	 */
	public boolean insertRauditCommentsDetails(String[] seqArray) {
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil
					.createPreparedStatement(CommonSQL.INSERT_IGRS_RAUDIT_COMMENT_MAPPING);
			dbUtil.executeUpdate(seqArray);
		} catch (Exception e) {
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
		return true;
	}

	/**
	 * @param existingId
	 * @return
	 */
	public ArrayList getExistDetails(String[] existingId,String language) {
		ArrayList list = new ArrayList();
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			if("en".equalsIgnoreCase(language))
			{
			dbUtil.createPreparedStatement(CommonSQL.RETRIEVE_EXISTING_RECORD);
			}
			else
			{
				dbUtil.createPreparedStatement(CommonSQL.RETRIEVE_EXISTING_RECORD_H);
			}
			list = dbUtil.executeQuery(existingId);
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

	/**
	 * @param listDetails
	 * @return
	 */
	public String submitAGMPReport(ArrayList listDetails) {
		String returnVal = "false";
		String[] txnDetails = (String[]) listDetails.get(0);
		String auditFilePath = (String) listDetails.get(1);
		String[] fileNames = (String[]) listDetails.get(2);
		String[] fileDetails = (String[]) listDetails.get(3);
		String[] paraDetails = (String[]) listDetails.get(4);
		String[] objection = (String[]) listDetails.get(5);
		String[] valAgmp = (String[]) listDetails.get(6);
		String[] stampDuty = (String[]) listDetails.get(7);
		String[] regFee = (String[]) listDetails.get(8);
		String[] marketVal=(String[]) listDetails.get(9);

		//modified by shruti
		String[] docId = (String[]) listDetails.get(10);
		String[] objStatus = (String[]) listDetails.get(11);
		String[] caseId = (String[]) listDetails.get(12);
		//end
		
		String[] agmpComm = (String[]) listDetails.get(13);
		String[] comDetails = (String[]) listDetails.get(14);
		//added by shruti-3rd oct 2013
		//String[] marketVal=(String[]) listDetails.get(14);
		//end


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
			boolean flagReoprtTxn = true;
			try {
				ArrayList tempReportId = getReportTxnId();
				ArrayList tempReportId2 = (ArrayList) tempReportId.get(0);
				txnDetails[14] = (String) tempReportId2.get(0);
				listReportId.add(txnDetails[14]);
				dbUtil.createPreparedStatement(CommonSQL.insert_IGRS_RCPT_AUDIT_TXN);
				flagReoprtTxn = dbUtil.executeUpdate(txnDetails);
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			// Insertion In IGRS_RAUDIT_DOCUMENT_DETAILS-COMMENTED BY SHRUTI-----3RD SEP 2013---UPLOAD WILL BE CHANGED
			boolean flagDocTxn = true;
			if(flagReoprtTxn){
			try {
				for (int i = 0; i < fileNames.length; i++) {
					ArrayList listDocId = getDocTxnId();
					ArrayList listDocId2 = (ArrayList) listDocId.get(0);
					String docId1 = (String) listDocId2.get(0);
					//modified by shruti--1st oct 2013
					/*flagDocTxn = dbUtil.attachAGMPAuditReport(docId1,auditFilePath, fileNames[i], fileDetails, "audit");*/
					flagDocTxn=dbUtil.saveAGMPAuditReport(docId1, auditFilePath,fileNames[i], fileDetails, "audit");
					listDocTxnId.add(docId1);
					
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			}
			boolean flagParaTxn = true;
			if(flagReoprtTxn && flagReoprtTxn){
			
				// Insertion In IGRS_RAUDIT_PARA_DETAILS
			try {
				dbUtil.createPreparedStatement(CommonSQL.INSERT_IGRS_RAUDIT_PARA_DETAILS);
				flagParaTxn = dbUtil.executeUpdate(paraDetails);
				listParaTxnId.add(paraDetails[0]);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			}
			// Insertion In IGRS_RAUDIT_PARA_OBJ_DETAILS
			boolean flagObjTxn = true;
			if(flagReoprtTxn && flagReoprtTxn && flagParaTxn){
			for (int i = 0; i < objection.length; i++) {
				try {
					ArrayList listObj = getMaxObjectionId();
					ArrayList listObj2 = (ArrayList) listObj.get(0);
					
					String[] objectionDetails = new String[9];
					objectionDetails[0] = (String) listObj2.get(0);
					objectionDetails[1] = objection[i];
					objectionDetails[2] = valAgmp[i];
					objectionDetails[3] = stampDuty[i];
					objectionDetails[4] = regFee[i];
					//added by shruti
					objectionDetails[5] = docId[i];
					objectionDetails[6] = caseId[i];
					//MODIFIED BY SHRUTI---3RD OCT 2013
					objectionDetails[7] ="OPEN";
					//objectionDetails[7] = objStatus[i];
					objectionDetails[8] =marketVal[i];
					//end
					dbUtil.createPreparedStatement(CommonSQL.INSERT_IGRS_RAUDIT_PARA_OBJ);
					flagObjTxn = dbUtil.executeUpdate(objectionDetails);
					listObjTxnId.add(objectionDetails[0]);
				} catch (Exception e) {
					flagObjTxn = true;					
					e.printStackTrace();
				}
			}
			}
			// Insertion In IGRS_RAUDIT_COMMENT_DETAILS
			boolean flagComTxn = true;
			if(flagReoprtTxn && flagReoprtTxn && flagParaTxn && flagObjTxn){
			for (int i = 0; i < agmpComm.length; i++) {
				try {
					ArrayList listAgmp = getMaxCommentId();
					ArrayList listAgmp2 = (ArrayList) listAgmp.get(0);
					String[] agmpComments = new String[4];
					agmpComments[0] = (String) listAgmp2.get(0);
					agmpComments[1] = agmpComm[i];
					agmpComments[2] = comDetails[0];
					agmpComments[3] = comDetails[1];
					dbUtil.createPreparedStatement(CommonSQL.INSERT_IGRS_RAUDIT_PARA_AGMP_COMMENT);
					flagComTxn = dbUtil.executeUpdate(agmpComments);
					listCommTxnId.add(agmpComments[0]);
					
				} catch (Exception e) {
					flagComTxn = true;
					
					e.printStackTrace();
				}
			}
			}

			// Insertion In IGRS_RAUDIT_COMMENTS_MAPPING
			boolean flagComMapTxn = true;
			if(flagReoprtTxn && flagReoprtTxn && flagParaTxn && flagObjTxn && flagComTxn){
			try {
				String[] commentMapping = new String[4];
				for (int i = 0; i < listCommTxnId.size(); i++) 
				{
					commentMapping[0] = (String) listReportId.get(0);
					commentMapping[1] = (String) listCommTxnId.get(i);
					commentMapping[2] = (String) listParaTxnId.get(0);
					commentMapping[3] = (String) listObjTxnId.get(i);
					dbUtil.createPreparedStatement(CommonSQL.INSERT_IGRS_RAUDIT_COMMENT_MAPPING);
					flagComMapTxn = dbUtil.executeUpdate(commentMapping);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			}
			// Insertion In IGRS_RAUDIT_DOC_MAPPING
			String[] docMapping = new String[4];
			boolean flagDocMapTxn = true;
			if(flagReoprtTxn && flagReoprtTxn && flagParaTxn && flagObjTxn && flagComTxn && flagComMapTxn){
			try {
				for (int i = 0; i < listDocTxnId.size(); i++) {
					docMapping[0] = (String) listReportId.get(0);
					docMapping[1] = (String) listDocTxnId.get(i);
					docMapping[2] = (String) listParaTxnId.get(0);
					if (listObjTxnId.size() == 1)
						docMapping[3] = (String) listObjTxnId.get(0);
					else
						docMapping[3] = (String) listObjTxnId.get(i);
					dbUtil.createPreparedStatement(CommonSQL.INSERT_IGRS_RAUDIT_DOC_MAPPING);
					flagDocMapTxn = dbUtil.executeUpdate(docMapping);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			}
			// Insertion In IGRS_RAUDIT_PARA_MAPPING
			String[] paraMapping = new String[2];
			boolean flagParaMapTxn = true;
			if(flagReoprtTxn && flagReoprtTxn && flagParaTxn && flagObjTxn && flagComTxn && flagComMapTxn && flagDocMapTxn){
			try {
				paraMapping[0] = (String) listReportId.get(0);
				paraMapping[1] = (String) listParaTxnId.get(0);
				dbUtil.createPreparedStatement(CommonSQL.INSERT_IGRS_RAUDIT_PARA_MAPPING);
				flagParaMapTxn = dbUtil.executeUpdate(paraMapping);
				
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			}
			// Insertion In IGRS_RAUDIT_PARA_OBJ_MAPPING
			String[] paraObjMapping = new String[2];
			boolean flagParaObjMapTxn = true;
			if(flagReoprtTxn && flagReoprtTxn && flagParaTxn && flagObjTxn && flagComTxn && flagComMapTxn && flagDocMapTxn && flagParaMapTxn){
			
			try {
				for (int i = 0; i < listObjTxnId.size(); i++) {
					paraObjMapping[0] = (String) listParaTxnId.get(0);
					paraObjMapping[1] = (String) listObjTxnId.get(i);
					dbUtil.createPreparedStatement(CommonSQL.INSERT_IGRS_RAUDIT_PARA_OBJ_MAPPING);
					flagParaObjMapTxn = dbUtil.executeUpdate(paraObjMapping);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			}
			
			/*if (flagReoprtTxn && flagDocTxn && flagParaTxn && flagObjTxn
					&& flagComTxn && flagComMapTxn && flagDocMapTxn
					&& flagParaMapTxn && flagParaObjMapTxn) --- MODIFIED BY SHRUTI*/
			if (flagReoprtTxn && flagParaTxn && flagObjTxn
					&& flagComTxn && flagComMapTxn && flagDocMapTxn
					&& flagParaMapTxn && flagParaObjMapTxn)
			{
				dbUtil.commit();
				returnVal = txnDetails[14];
				
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

	
	public String submitInternalReport(ArrayList listDetails) {
		String returnVal = "false";
		String[] txnDetails = (String[]) listDetails.get(0);
		String auditFilePath = (String) listDetails.get(1);
		String[] fileNames = (String[]) listDetails.get(2);
		String[] fileDetails = (String[]) listDetails.get(3);
		String[] paraDetails = (String[]) listDetails.get(4);
		String[] objection = (String[]) listDetails.get(5);
		String[] valAgmp = (String[]) listDetails.get(6);
		String[] stampDuty = (String[]) listDetails.get(7);
		String[] regFee = (String[]) listDetails.get(8);
		String[] marketVal=(String[]) listDetails.get(9);

		//modified by shruti
		String[] docId = (String[]) listDetails.get(10);
		String[] objStatus = (String[]) listDetails.get(11);
		String[] caseId = (String[]) listDetails.get(12);
		//end
		
		String[] agmpComm = (String[]) listDetails.get(13);
		String[] comDetails = (String[]) listDetails.get(14);
		//added by shruti-3rd oct 2013
		//String[] marketVal=(String[]) listDetails.get(14);
		//end


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
			boolean flagReoprtTxn = true;
			try {
				ArrayList tempReportId = getReportTxnId();
				ArrayList tempReportId2 = (ArrayList) tempReportId.get(0);
				txnDetails[14] = (String) tempReportId2.get(0);
				listReportId.add(txnDetails[14]);
				dbUtil.createPreparedStatement(CommonSQL.insert_IGRS_RCPT_AUDIT_TXN);
				flagReoprtTxn = dbUtil.executeUpdate(txnDetails);
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			// Insertion In IGRS_RAUDIT_DOCUMENT_DETAILS-COMMENTED BY SHRUTI-----3RD SEP 2013---UPLOAD WILL BE CHANGED
			boolean flagDocTxn = true;
			if(flagReoprtTxn){
			try {
				for (int i = 0; i < fileNames.length; i++) {
					ArrayList listDocId = getDocTxnId();
					ArrayList listDocId2 = (ArrayList) listDocId.get(0);
					String docId1 = (String) listDocId2.get(0);
					//modified by shruti--1st oct 2013
					/*flagDocTxn = dbUtil.attachAGMPAuditReport(docId1,auditFilePath, fileNames[i], fileDetails, "audit");*/
					flagDocTxn=dbUtil.saveAGMPAuditReport(docId1, auditFilePath,fileNames[i], fileDetails, "audit");
					listDocTxnId.add(docId1);
					
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			}
			boolean flagParaTxn = true;
			if(flagReoprtTxn && flagReoprtTxn){
			
				// Insertion In IGRS_RAUDIT_PARA_DETAILS
			try {
				dbUtil.createPreparedStatement(CommonSQL.INSERT_IGRS_RAUDIT_PARA_DETAILS);
				flagParaTxn = dbUtil.executeUpdate(paraDetails);
				listParaTxnId.add(paraDetails[0]);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			}
			// Insertion In IGRS_RAUDIT_PARA_OBJ_DETAILS
			boolean flagObjTxn = true;
			if(flagReoprtTxn && flagReoprtTxn && flagParaTxn){
			for (int i = 0; i < objection.length; i++) {
				try {
					ArrayList listObj = getMaxObjectionId();
					ArrayList listObj2 = (ArrayList) listObj.get(0);
					
					String[] objectionDetails = new String[9];
					objectionDetails[0] = (String) listObj2.get(0);
					objectionDetails[1] = objection[i];
					objectionDetails[2] = valAgmp[i];
					objectionDetails[3] = stampDuty[i];
					objectionDetails[4] = regFee[i];
					//added by shruti
					objectionDetails[5] = docId[i];
					objectionDetails[6] = caseId[i];
					//MODIFIED BY SHRUTI---3RD OCT 2013
					objectionDetails[7] ="OPEN";
					//objectionDetails[7] = objStatus[i];
					objectionDetails[8] =marketVal[i];
					//end
					dbUtil.createPreparedStatement(CommonSQL.INSERT_IGRS_RAUDIT_PARA_OBJ);
					flagObjTxn = dbUtil.executeUpdate(objectionDetails);
					listObjTxnId.add(objectionDetails[0]);
				} catch (Exception e) {
					flagObjTxn = true;					
					e.printStackTrace();
				}
			}
			}
			// Insertion In IGRS_RAUDIT_COMMENT_DETAILS
			boolean flagComTxn = true;
			if(flagReoprtTxn && flagReoprtTxn && flagParaTxn && flagObjTxn){
			for (int i = 0; i < agmpComm.length; i++) {
				try {
					ArrayList listAgmp = getMaxCommentId();
					ArrayList listAgmp2 = (ArrayList) listAgmp.get(0);
					String[] agmpComments = new String[4];
					agmpComments[0] = (String) listAgmp2.get(0);
					agmpComments[1] = agmpComm[i];
					agmpComments[2] = comDetails[0];
					agmpComments[3] = comDetails[1];
					dbUtil.createPreparedStatement(CommonSQL.INSERT_IGRS_RAUDIT_PARA_AGMP_COMMENT);
					flagComTxn = dbUtil.executeUpdate(agmpComments);
					listCommTxnId.add(agmpComments[0]);
					
				} catch (Exception e) {
					flagComTxn = true;
					
					e.printStackTrace();
				}
			}
			}

			// Insertion In IGRS_RAUDIT_COMMENTS_MAPPING
			boolean flagComMapTxn = true;
			if(flagReoprtTxn && flagReoprtTxn && flagParaTxn && flagObjTxn && flagComTxn){
			try {
				String[] commentMapping = new String[4];
				for (int i = 0; i < listCommTxnId.size(); i++) 
				{
					commentMapping[0] = (String) listReportId.get(0);
					commentMapping[1] = (String) listCommTxnId.get(i);
					commentMapping[2] = (String) listParaTxnId.get(0);
					commentMapping[3] = (String) listObjTxnId.get(i);
					dbUtil.createPreparedStatement(CommonSQL.INSERT_IGRS_RAUDIT_COMMENT_MAPPING);
					flagComMapTxn = dbUtil.executeUpdate(commentMapping);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			}
			// Insertion In IGRS_RAUDIT_DOC_MAPPING
			String[] docMapping = new String[4];
			boolean flagDocMapTxn = true;
			if(flagReoprtTxn && flagReoprtTxn && flagParaTxn && flagObjTxn && flagComTxn && flagComMapTxn){
			try {
				for (int i = 0; i < listDocTxnId.size(); i++) {
					docMapping[0] = (String) listReportId.get(0);
					docMapping[1] = (String) listDocTxnId.get(i);
					docMapping[2] = (String) listParaTxnId.get(0);
					if (listObjTxnId.size() == 1)
						docMapping[3] = (String) listObjTxnId.get(0);
					else
						docMapping[3] = (String) listObjTxnId.get(i);
					dbUtil.createPreparedStatement(CommonSQL.INSERT_IGRS_RAUDIT_DOC_MAPPING);
					flagDocMapTxn = dbUtil.executeUpdate(docMapping);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			}
			// Insertion In IGRS_RAUDIT_PARA_MAPPING
			String[] paraMapping = new String[2];
			boolean flagParaMapTxn = true;
			if(flagReoprtTxn && flagReoprtTxn && flagParaTxn && flagObjTxn && flagComTxn && flagComMapTxn && flagDocMapTxn){
			try {
				paraMapping[0] = (String) listReportId.get(0);
				paraMapping[1] = (String) listParaTxnId.get(0);
				dbUtil.createPreparedStatement(CommonSQL.INSERT_IGRS_RAUDIT_PARA_MAPPING);
				flagParaMapTxn = dbUtil.executeUpdate(paraMapping);
				
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			}
			// Insertion In IGRS_RAUDIT_PARA_OBJ_MAPPING
			String[] paraObjMapping = new String[2];
			boolean flagParaObjMapTxn = true;
			if(flagReoprtTxn && flagReoprtTxn && flagParaTxn && flagObjTxn && flagComTxn && flagComMapTxn && flagDocMapTxn && flagParaMapTxn){
			
			try {
				for (int i = 0; i < listObjTxnId.size(); i++) {
					paraObjMapping[0] = (String) listParaTxnId.get(0);
					paraObjMapping[1] = (String) listObjTxnId.get(i);
					dbUtil.createPreparedStatement(CommonSQL.INSERT_IGRS_RAUDIT_PARA_OBJ_MAPPING);
					flagParaObjMapTxn = dbUtil.executeUpdate(paraObjMapping);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			}
			
			/*if (flagReoprtTxn && flagDocTxn && flagParaTxn && flagObjTxn
					&& flagComTxn && flagComMapTxn && flagDocMapTxn
					&& flagParaMapTxn && flagParaObjMapTxn) --- MODIFIED BY SHRUTI*/
			if (flagReoprtTxn && flagParaTxn && flagObjTxn
					&& flagComTxn && flagComMapTxn && flagDocMapTxn
					&& flagParaMapTxn && flagParaObjMapTxn)
			{
				dbUtil.commit();
				returnVal = txnDetails[14];
				
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

	public String submitInternalExpReport(ArrayList listDetails) {
		String returnVal = "false";
		String[] txnDetails = (String[]) listDetails.get(0);
		String auditFilePath = (String) listDetails.get(1);
		String[] fileNames = (String[]) listDetails.get(2);
		String[] fileDetails = (String[]) listDetails.get(3);
		String[] paraDetails = (String[]) listDetails.get(4);
		String[] objection = (String[]) listDetails.get(5);
		String[] valAgmp = (String[]) listDetails.get(6);
		String[] stampDuty = (String[]) listDetails.get(7);
		String[] regFee = (String[]) listDetails.get(8);
		String[] marketVal=(String[]) listDetails.get(9);

		String[] docId = (String[]) listDetails.get(10);
		String[] objStatus = (String[]) listDetails.get(11);
		String[] caseId = (String[]) listDetails.get(12);
		
		String[] agmpComm = (String[]) listDetails.get(13);
		String[] comDetails = (String[]) listDetails.get(14);

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
			boolean flagReoprtTxn = true;
			try {
				String distId=txnDetails[10];
				ArrayList tempReportId = getInternalExpReportTxnId();
				ArrayList tempReportId2 = (ArrayList) tempReportId.get(0);
				//modified by shruti---13 june 2014
				txnDetails[14] = "IE"+distId+(String) tempReportId2.get(0);
				//end
				listReportId.add(txnDetails[14]);
				dbUtil.createPreparedStatement(CommonSQL.insert_IGRS_RCPT_AUDIT_TXN);
				flagReoprtTxn = dbUtil.executeUpdate(txnDetails);
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			// Insertion In IGRS_RAUDIT_DOCUMENT_DETAILS-COMMENTED BY SHRUTI-----3RD SEP 2013---UPLOAD WILL BE CHANGED
			boolean flagDocTxn = true;
			if(flagReoprtTxn){
			try {
				for (int i = 0; i < fileNames.length; i++) {
					ArrayList listDocId = getDocTxnId();
					ArrayList listDocId2 = (ArrayList) listDocId.get(0);
					String docId1 = (String) listDocId2.get(0);
					//modified by shruti--1st oct 2013
					/*flagDocTxn = dbUtil.attachAGMPAuditReport(docId1,auditFilePath, fileNames[i], fileDetails, "audit");*/
					flagDocTxn=dbUtil.saveAGMPAuditReport(docId1, auditFilePath,fileNames[i], fileDetails, "audit");
					listDocTxnId.add(docId1);
					
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			}
			boolean flagParaTxn = true;
			if(flagReoprtTxn && flagReoprtTxn){
			
				// Insertion In IGRS_RAUDIT_PARA_DETAILS
			try {
				dbUtil.createPreparedStatement(CommonSQL.INSERT_IGRS_RAUDIT_PARA_DETAILS);
				flagParaTxn = dbUtil.executeUpdate(paraDetails);
				listParaTxnId.add(paraDetails[0]);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			}
			// Insertion In IGRS_RAUDIT_PARA_OBJ_DETAILS
			boolean flagObjTxn = true;
			if(flagReoprtTxn && flagReoprtTxn && flagParaTxn){
			for (int i = 0; i < objection.length; i++) {
				try {
					ArrayList listObj = getMaxObjectionId();
					ArrayList listObj2 = (ArrayList) listObj.get(0);
					
					String[] objectionDetails = new String[9];
					objectionDetails[0] = (String) listObj2.get(0);
					objectionDetails[1] = objection[i];
					objectionDetails[2] = valAgmp[i];
					objectionDetails[3] = stampDuty[i];
					objectionDetails[4] = regFee[i];
					//added by shruti
					objectionDetails[5] = docId[i];
					objectionDetails[6] = caseId[i];
					//MODIFIED BY SHRUTI---3RD OCT 2013
					objectionDetails[7] ="OPEN";
					//objectionDetails[7] = objStatus[i];
					objectionDetails[8] =marketVal[i];
					//end
					dbUtil.createPreparedStatement(CommonSQL.INSERT_IGRS_RAUDIT_PARA_OBJ);
					flagObjTxn = dbUtil.executeUpdate(objectionDetails);
					listObjTxnId.add(objectionDetails[0]);
				} catch (Exception e) {
					flagObjTxn = true;					
					e.printStackTrace();
				}
			}
			}
			// Insertion In IGRS_RAUDIT_COMMENT_DETAILS
			boolean flagComTxn = true;
			if(flagReoprtTxn && flagReoprtTxn && flagParaTxn && flagObjTxn){
			for (int i = 0; i < agmpComm.length; i++) {
				try {
					ArrayList listAgmp = getMaxCommentId();
					ArrayList listAgmp2 = (ArrayList) listAgmp.get(0);
					String[] agmpComments = new String[4];
					agmpComments[0] = (String) listAgmp2.get(0);
					agmpComments[1] = agmpComm[i];
					agmpComments[2] = comDetails[0];
					agmpComments[3] = comDetails[1];
					dbUtil.createPreparedStatement(CommonSQL.INSERT_IGRS_RAUDIT_PARA_AGMP_COMMENT);
					flagComTxn = dbUtil.executeUpdate(agmpComments);
					listCommTxnId.add(agmpComments[0]);
					
				} catch (Exception e) {
					flagComTxn = true;
					
					e.printStackTrace();
				}
			}
			}

			// Insertion In IGRS_RAUDIT_COMMENTS_MAPPING
			boolean flagComMapTxn = true;
			if(flagReoprtTxn && flagReoprtTxn && flagParaTxn && flagObjTxn && flagComTxn){
			try {
				String[] commentMapping = new String[4];
				for (int i = 0; i < listCommTxnId.size(); i++) 
				{
					commentMapping[0] = (String) listReportId.get(0);
					commentMapping[1] = (String) listCommTxnId.get(i);
					commentMapping[2] = (String) listParaTxnId.get(0);
					commentMapping[3] = (String) listObjTxnId.get(i);
					dbUtil.createPreparedStatement(CommonSQL.INSERT_IGRS_RAUDIT_COMMENT_MAPPING);
					flagComMapTxn = dbUtil.executeUpdate(commentMapping);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			}
			// Insertion In IGRS_RAUDIT_DOC_MAPPING
			String[] docMapping = new String[4];
			boolean flagDocMapTxn = true;
			if(flagReoprtTxn && flagReoprtTxn && flagParaTxn && flagObjTxn && flagComTxn && flagComMapTxn){
			try {
				for (int i = 0; i < listDocTxnId.size(); i++) {
					docMapping[0] = (String) listReportId.get(0);
					docMapping[1] = (String) listDocTxnId.get(i);
					docMapping[2] = (String) listParaTxnId.get(0);
					if (listObjTxnId.size() == 1)
						docMapping[3] = (String) listObjTxnId.get(0);
					else
						docMapping[3] = (String) listObjTxnId.get(i);
					dbUtil.createPreparedStatement(CommonSQL.INSERT_IGRS_RAUDIT_DOC_MAPPING);
					flagDocMapTxn = dbUtil.executeUpdate(docMapping);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			}
			// Insertion In IGRS_RAUDIT_PARA_MAPPING
			String[] paraMapping = new String[2];
			boolean flagParaMapTxn = true;
			if(flagReoprtTxn && flagReoprtTxn && flagParaTxn && flagObjTxn && flagComTxn && flagComMapTxn && flagDocMapTxn){
			try {
				paraMapping[0] = (String) listReportId.get(0);
				paraMapping[1] = (String) listParaTxnId.get(0);
				dbUtil.createPreparedStatement(CommonSQL.INSERT_IGRS_RAUDIT_PARA_MAPPING);
				flagParaMapTxn = dbUtil.executeUpdate(paraMapping);
				
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			}
			// Insertion In IGRS_RAUDIT_PARA_OBJ_MAPPING
			String[] paraObjMapping = new String[2];
			boolean flagParaObjMapTxn = true;
			if(flagReoprtTxn && flagReoprtTxn && flagParaTxn && flagObjTxn && flagComTxn && flagComMapTxn && flagDocMapTxn && flagParaMapTxn){
			
			try {
				for (int i = 0; i < listObjTxnId.size(); i++) {
					paraObjMapping[0] = (String) listParaTxnId.get(0);
					paraObjMapping[1] = (String) listObjTxnId.get(i);
					dbUtil.createPreparedStatement(CommonSQL.INSERT_IGRS_RAUDIT_PARA_OBJ_MAPPING);
					flagParaObjMapTxn = dbUtil.executeUpdate(paraObjMapping);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			}
			
			/*if (flagReoprtTxn && flagDocTxn && flagParaTxn && flagObjTxn
					&& flagComTxn && flagComMapTxn && flagDocMapTxn
					&& flagParaMapTxn && flagParaObjMapTxn) --- MODIFIED BY SHRUTI*/
			if (flagReoprtTxn && flagParaTxn && flagObjTxn
					&& flagComTxn && flagComMapTxn && flagDocMapTxn
					&& flagParaMapTxn && flagParaObjMapTxn)
			{
				dbUtil.commit();
				returnVal = txnDetails[14];
				
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

	public String submitAGMPExpReport(ArrayList listDetails) {
		String returnVal = "false";
		String[] txnDetails = (String[]) listDetails.get(0);
		String auditFilePath = (String) listDetails.get(1);
		String[] fileNames = (String[]) listDetails.get(2);
		String[] fileDetails = (String[]) listDetails.get(3);
		String[] paraDetails = (String[]) listDetails.get(4);
		String[] objection = (String[]) listDetails.get(5);
		String[] valAgmp = (String[]) listDetails.get(6);
		String[] stampDuty = (String[]) listDetails.get(7);
		String[] regFee = (String[]) listDetails.get(8);
		String[] marketVal=(String[]) listDetails.get(9);

		String[] docId = (String[]) listDetails.get(10);
		String[] objStatus = (String[]) listDetails.get(11);
		String[] caseId = (String[]) listDetails.get(12);
		
		String[] agmpComm = (String[]) listDetails.get(13);
		String[] comDetails = (String[]) listDetails.get(14);

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
			boolean flagReoprtTxn = true;
			try {
				String distId=txnDetails[10];
				ArrayList tempReportId = getAGMPExpReportTxnId();
				ArrayList tempReportId2 = (ArrayList) tempReportId.get(0);
				//modified by shruti---13 june 2014
				txnDetails[14] = "AGE"+distId+(String) tempReportId2.get(0);
				//end
				listReportId.add(txnDetails[14]);
				dbUtil.createPreparedStatement(CommonSQL.insert_IGRS_RCPT_AUDIT_TXN);
				flagReoprtTxn = dbUtil.executeUpdate(txnDetails);
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			// Insertion In IGRS_RAUDIT_DOCUMENT_DETAILS-COMMENTED BY SHRUTI-----3RD SEP 2013---UPLOAD WILL BE CHANGED
			boolean flagDocTxn = true;
			if(flagReoprtTxn){
			try {
				for (int i = 0; i < fileNames.length; i++) {
					ArrayList listDocId = getDocTxnId();
					ArrayList listDocId2 = (ArrayList) listDocId.get(0);
					String docId1 = (String) listDocId2.get(0);
					//modified by shruti--1st oct 2013
					/*flagDocTxn = dbUtil.attachAGMPAuditReport(docId1,auditFilePath, fileNames[i], fileDetails, "audit");*/
					flagDocTxn=dbUtil.saveAGMPAuditReport(docId1, auditFilePath,fileNames[i], fileDetails, "audit");
					listDocTxnId.add(docId1);
					
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			}
			boolean flagParaTxn = true;
			if(flagReoprtTxn && flagReoprtTxn){
			
				// Insertion In IGRS_RAUDIT_PARA_DETAILS
			try {
				dbUtil.createPreparedStatement(CommonSQL.INSERT_IGRS_RAUDIT_PARA_DETAILS);
				flagParaTxn = dbUtil.executeUpdate(paraDetails);
				listParaTxnId.add(paraDetails[0]);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			}
			// Insertion In IGRS_RAUDIT_PARA_OBJ_DETAILS
			boolean flagObjTxn = true;
			if(flagReoprtTxn && flagReoprtTxn && flagParaTxn){
			for (int i = 0; i < objection.length; i++) {
				try {
					ArrayList listObj = getMaxObjectionId();
					ArrayList listObj2 = (ArrayList) listObj.get(0);
					
					String[] objectionDetails = new String[9];
					objectionDetails[0] = (String) listObj2.get(0);
					objectionDetails[1] = objection[i];
					objectionDetails[2] = valAgmp[i];
					objectionDetails[3] = stampDuty[i];
					objectionDetails[4] = regFee[i];
					//added by shruti
					objectionDetails[5] = docId[i];
					objectionDetails[6] = caseId[i];
					//MODIFIED BY SHRUTI---3RD OCT 2013
					objectionDetails[7] ="OPEN";
					//objectionDetails[7] = objStatus[i];
					objectionDetails[8] =marketVal[i];
					//end
					dbUtil.createPreparedStatement(CommonSQL.INSERT_IGRS_RAUDIT_PARA_OBJ);
					flagObjTxn = dbUtil.executeUpdate(objectionDetails);
					listObjTxnId.add(objectionDetails[0]);
				} catch (Exception e) {
					flagObjTxn = true;					
					e.printStackTrace();
				}
			}
			}
			// Insertion In IGRS_RAUDIT_COMMENT_DETAILS
			boolean flagComTxn = true;
			if(flagReoprtTxn && flagReoprtTxn && flagParaTxn && flagObjTxn){
			for (int i = 0; i < agmpComm.length; i++) {
				try {
					ArrayList listAgmp = getMaxCommentId();
					ArrayList listAgmp2 = (ArrayList) listAgmp.get(0);
					String[] agmpComments = new String[4];
					agmpComments[0] = (String) listAgmp2.get(0);
					agmpComments[1] = agmpComm[i];
					agmpComments[2] = comDetails[0];
					agmpComments[3] = comDetails[1];
					dbUtil.createPreparedStatement(CommonSQL.INSERT_IGRS_RAUDIT_PARA_AGMP_COMMENT);
					flagComTxn = dbUtil.executeUpdate(agmpComments);
					listCommTxnId.add(agmpComments[0]);
					
				} catch (Exception e) {
					flagComTxn = true;
					
					e.printStackTrace();
				}
			}
			}

			// Insertion In IGRS_RAUDIT_COMMENTS_MAPPING
			boolean flagComMapTxn = true;
			if(flagReoprtTxn && flagReoprtTxn && flagParaTxn && flagObjTxn && flagComTxn){
			try {
				String[] commentMapping = new String[4];
				for (int i = 0; i < listCommTxnId.size(); i++) 
				{
					commentMapping[0] = (String) listReportId.get(0);
					commentMapping[1] = (String) listCommTxnId.get(i);
					commentMapping[2] = (String) listParaTxnId.get(0);
					commentMapping[3] = (String) listObjTxnId.get(i);
					dbUtil.createPreparedStatement(CommonSQL.INSERT_IGRS_RAUDIT_COMMENT_MAPPING);
					flagComMapTxn = dbUtil.executeUpdate(commentMapping);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			}
			// Insertion In IGRS_RAUDIT_DOC_MAPPING
			String[] docMapping = new String[4];
			boolean flagDocMapTxn = true;
			if(flagReoprtTxn && flagReoprtTxn && flagParaTxn && flagObjTxn && flagComTxn && flagComMapTxn){
			try {
				for (int i = 0; i < listDocTxnId.size(); i++) {
					docMapping[0] = (String) listReportId.get(0);
					docMapping[1] = (String) listDocTxnId.get(i);
					docMapping[2] = (String) listParaTxnId.get(0);
					if (listObjTxnId.size() == 1)
						docMapping[3] = (String) listObjTxnId.get(0);
					else
						docMapping[3] = (String) listObjTxnId.get(i);
					dbUtil.createPreparedStatement(CommonSQL.INSERT_IGRS_RAUDIT_DOC_MAPPING);
					flagDocMapTxn = dbUtil.executeUpdate(docMapping);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			}
			// Insertion In IGRS_RAUDIT_PARA_MAPPING
			String[] paraMapping = new String[2];
			boolean flagParaMapTxn = true;
			if(flagReoprtTxn && flagReoprtTxn && flagParaTxn && flagObjTxn && flagComTxn && flagComMapTxn && flagDocMapTxn){
			try {
				paraMapping[0] = (String) listReportId.get(0);
				paraMapping[1] = (String) listParaTxnId.get(0);
				dbUtil.createPreparedStatement(CommonSQL.INSERT_IGRS_RAUDIT_PARA_MAPPING);
				flagParaMapTxn = dbUtil.executeUpdate(paraMapping);
				
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			}
			// Insertion In IGRS_RAUDIT_PARA_OBJ_MAPPING
			String[] paraObjMapping = new String[2];
			boolean flagParaObjMapTxn = true;
			if(flagReoprtTxn && flagReoprtTxn && flagParaTxn && flagObjTxn && flagComTxn && flagComMapTxn && flagDocMapTxn && flagParaMapTxn){
			
			try {
				for (int i = 0; i < listObjTxnId.size(); i++) {
					paraObjMapping[0] = (String) listParaTxnId.get(0);
					paraObjMapping[1] = (String) listObjTxnId.get(i);
					dbUtil.createPreparedStatement(CommonSQL.INSERT_IGRS_RAUDIT_PARA_OBJ_MAPPING);
					flagParaObjMapTxn = dbUtil.executeUpdate(paraObjMapping);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			}
			
			/*if (flagReoprtTxn && flagDocTxn && flagParaTxn && flagObjTxn
					&& flagComTxn && flagComMapTxn && flagDocMapTxn
					&& flagParaMapTxn && flagParaObjMapTxn) --- MODIFIED BY SHRUTI*/
			if (flagReoprtTxn && flagParaTxn && flagObjTxn
					&& flagComTxn && flagComMapTxn && flagDocMapTxn
					&& flagParaMapTxn && flagParaObjMapTxn)
			{
				dbUtil.commit();
				returnVal = txnDetails[14];
				
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

	public String submitAGMPRcptReport(ArrayList listDetails) {
		String returnVal = "false";
		String[] txnDetails = (String[]) listDetails.get(0);
		String auditFilePath = (String) listDetails.get(1);
		String[] fileNames = (String[]) listDetails.get(2);
		String[] fileDetails = (String[]) listDetails.get(3);
		String[] paraDetails = (String[]) listDetails.get(4);
		String[] objection = (String[]) listDetails.get(5);
		String[] valAgmp = (String[]) listDetails.get(6);
		String[] stampDuty = (String[]) listDetails.get(7);
		String[] regFee = (String[]) listDetails.get(8);
		String[] marketVal=(String[]) listDetails.get(9);

		String[] docId = (String[]) listDetails.get(10);
		String[] objStatus = (String[]) listDetails.get(11);
		String[] caseId = (String[]) listDetails.get(12);
		
		String[] agmpComm = (String[]) listDetails.get(13);
		String[] comDetails = (String[]) listDetails.get(14);

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
			boolean flagReoprtTxn = true;
			try {
				String distId=txnDetails[10];
				ArrayList tempReportId = getAGMPRcptReportTxnId();
				ArrayList tempReportId2 = (ArrayList) tempReportId.get(0);
				//modified by shruti---13 june 2014
				txnDetails[14] = "AGR"+distId+(String) tempReportId2.get(0);
				//end
				listReportId.add(txnDetails[14]);
				dbUtil.createPreparedStatement(CommonSQL.insert_IGRS_RCPT_AUDIT_TXN);
				flagReoprtTxn = dbUtil.executeUpdate(txnDetails);
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			// Insertion In IGRS_RAUDIT_DOCUMENT_DETAILS-COMMENTED BY SHRUTI-----3RD SEP 2013---UPLOAD WILL BE CHANGED
			boolean flagDocTxn = true;
			if(flagReoprtTxn){
			try {
				for (int i = 0; i < fileNames.length; i++) {
					ArrayList listDocId = getDocTxnId();
					ArrayList listDocId2 = (ArrayList) listDocId.get(0);
					String docId1 = (String) listDocId2.get(0);
					//modified by shruti--1st oct 2013
					/*flagDocTxn = dbUtil.attachAGMPAuditReport(docId1,auditFilePath, fileNames[i], fileDetails, "audit");*/
					flagDocTxn=dbUtil.saveAGMPAuditReport(docId1, auditFilePath,fileNames[i], fileDetails, "audit");
					listDocTxnId.add(docId1);
					
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			}
			boolean flagParaTxn = true;
			if(flagReoprtTxn && flagReoprtTxn){
			
				// Insertion In IGRS_RAUDIT_PARA_DETAILS
			try {
				dbUtil.createPreparedStatement(CommonSQL.INSERT_IGRS_RAUDIT_PARA_DETAILS);
				flagParaTxn = dbUtil.executeUpdate(paraDetails);
				listParaTxnId.add(paraDetails[0]);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			}
			// Insertion In IGRS_RAUDIT_PARA_OBJ_DETAILS
			boolean flagObjTxn = true;
			if(flagReoprtTxn && flagReoprtTxn && flagParaTxn){
			for (int i = 0; i < objection.length; i++) {
				try {
					ArrayList listObj = getMaxObjectionId();
					ArrayList listObj2 = (ArrayList) listObj.get(0);
					
					String[] objectionDetails = new String[9];
					objectionDetails[0] = (String) listObj2.get(0);
					objectionDetails[1] = objection[i];
					objectionDetails[2] = valAgmp[i];
					objectionDetails[3] = stampDuty[i];
					objectionDetails[4] = regFee[i];
					//added by shruti
					objectionDetails[5] = docId[i];
					objectionDetails[6] = caseId[i];
					//MODIFIED BY SHRUTI---3RD OCT 2013
					objectionDetails[7] ="OPEN";
					//objectionDetails[7] = objStatus[i];
					objectionDetails[8] =marketVal[i];
					//end
					dbUtil.createPreparedStatement(CommonSQL.INSERT_IGRS_RAUDIT_PARA_OBJ);
					flagObjTxn = dbUtil.executeUpdate(objectionDetails);
					listObjTxnId.add(objectionDetails[0]);
				} catch (Exception e) {
					flagObjTxn = true;					
					e.printStackTrace();
				}
			}
			}
			// Insertion In IGRS_RAUDIT_COMMENT_DETAILS
			boolean flagComTxn = true;
			if(flagReoprtTxn && flagReoprtTxn && flagParaTxn && flagObjTxn){
			for (int i = 0; i < agmpComm.length; i++) {
				try {
					ArrayList listAgmp = getMaxCommentId();
					ArrayList listAgmp2 = (ArrayList) listAgmp.get(0);
					String[] agmpComments = new String[4];
					agmpComments[0] = (String) listAgmp2.get(0);
					agmpComments[1] = agmpComm[i];
					agmpComments[2] = comDetails[0];
					agmpComments[3] = comDetails[1];
					dbUtil.createPreparedStatement(CommonSQL.INSERT_IGRS_RAUDIT_PARA_AGMP_COMMENT);
					flagComTxn = dbUtil.executeUpdate(agmpComments);
					listCommTxnId.add(agmpComments[0]);
					
				} catch (Exception e) {
					flagComTxn = true;
					
					e.printStackTrace();
				}
			}
			}

			// Insertion In IGRS_RAUDIT_COMMENTS_MAPPING
			boolean flagComMapTxn = true;
			if(flagReoprtTxn && flagReoprtTxn && flagParaTxn && flagObjTxn && flagComTxn){
			try {
				String[] commentMapping = new String[4];
				for (int i = 0; i < listCommTxnId.size(); i++) 
				{
					commentMapping[0] = (String) listReportId.get(0);
					commentMapping[1] = (String) listCommTxnId.get(i);
					commentMapping[2] = (String) listParaTxnId.get(0);
					commentMapping[3] = (String) listObjTxnId.get(i);
					dbUtil.createPreparedStatement(CommonSQL.INSERT_IGRS_RAUDIT_COMMENT_MAPPING);
					flagComMapTxn = dbUtil.executeUpdate(commentMapping);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			}
			// Insertion In IGRS_RAUDIT_DOC_MAPPING
			String[] docMapping = new String[4];
			boolean flagDocMapTxn = true;
			if(flagReoprtTxn && flagReoprtTxn && flagParaTxn && flagObjTxn && flagComTxn && flagComMapTxn){
			try {
				for (int i = 0; i < listDocTxnId.size(); i++) {
					docMapping[0] = (String) listReportId.get(0);
					docMapping[1] = (String) listDocTxnId.get(i);
					docMapping[2] = (String) listParaTxnId.get(0);
					if (listObjTxnId.size() == 1)
						docMapping[3] = (String) listObjTxnId.get(0);
					else
						docMapping[3] = (String) listObjTxnId.get(i);
					dbUtil.createPreparedStatement(CommonSQL.INSERT_IGRS_RAUDIT_DOC_MAPPING);
					flagDocMapTxn = dbUtil.executeUpdate(docMapping);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			}
			// Insertion In IGRS_RAUDIT_PARA_MAPPING
			String[] paraMapping = new String[2];
			boolean flagParaMapTxn = true;
			if(flagReoprtTxn && flagReoprtTxn && flagParaTxn && flagObjTxn && flagComTxn && flagComMapTxn && flagDocMapTxn){
			try {
				paraMapping[0] = (String) listReportId.get(0);
				paraMapping[1] = (String) listParaTxnId.get(0);
				dbUtil.createPreparedStatement(CommonSQL.INSERT_IGRS_RAUDIT_PARA_MAPPING);
				flagParaMapTxn = dbUtil.executeUpdate(paraMapping);
				
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			}
			// Insertion In IGRS_RAUDIT_PARA_OBJ_MAPPING
			String[] paraObjMapping = new String[2];
			boolean flagParaObjMapTxn = true;
			if(flagReoprtTxn && flagReoprtTxn && flagParaTxn && flagObjTxn && flagComTxn && flagComMapTxn && flagDocMapTxn && flagParaMapTxn){
			
			try {
				for (int i = 0; i < listObjTxnId.size(); i++) {
					paraObjMapping[0] = (String) listParaTxnId.get(0);
					paraObjMapping[1] = (String) listObjTxnId.get(i);
					dbUtil.createPreparedStatement(CommonSQL.INSERT_IGRS_RAUDIT_PARA_OBJ_MAPPING);
					flagParaObjMapTxn = dbUtil.executeUpdate(paraObjMapping);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			}
			
			/*if (flagReoprtTxn && flagDocTxn && flagParaTxn && flagObjTxn
					&& flagComTxn && flagComMapTxn && flagDocMapTxn
					&& flagParaMapTxn && flagParaObjMapTxn) --- MODIFIED BY SHRUTI*/
			if (flagReoprtTxn && flagParaTxn && flagObjTxn
					&& flagComTxn && flagComMapTxn && flagDocMapTxn
					&& flagParaMapTxn && flagParaObjMapTxn)
			{
				dbUtil.commit();
				returnVal = txnDetails[14];
				
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

	public String submitInternalRcptReport(ArrayList listDetails) {
		String returnVal = "false";
		String[] txnDetails = (String[]) listDetails.get(0);
		String auditFilePath = (String) listDetails.get(1);
		String[] fileNames = (String[]) listDetails.get(2);
		String[] fileDetails = (String[]) listDetails.get(3);
		String[] paraDetails = (String[]) listDetails.get(4);
		String[] objection = (String[]) listDetails.get(5);
		String[] valAgmp = (String[]) listDetails.get(6);
		String[] stampDuty = (String[]) listDetails.get(7);
		String[] regFee = (String[]) listDetails.get(8);
		String[] marketVal=(String[]) listDetails.get(9);

		String[] docId = (String[]) listDetails.get(10);
		String[] objStatus = (String[]) listDetails.get(11);
		String[] caseId = (String[]) listDetails.get(12);
		
		String[] agmpComm = (String[]) listDetails.get(13);
		String[] comDetails = (String[]) listDetails.get(14);

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
			boolean flagReoprtTxn = true;
			try {
				String distId=txnDetails[10];
				ArrayList tempReportId = getInternalRcptReportTxnId();
				ArrayList tempReportId2 = (ArrayList) tempReportId.get(0);
				//modified by shruti---13 june 2014
				txnDetails[14] = "IA"+distId+(String) tempReportId2.get(0);
				//end
				listReportId.add(txnDetails[14]);
				dbUtil.createPreparedStatement(CommonSQL.insert_IGRS_RCPT_AUDIT_TXN);
				flagReoprtTxn = dbUtil.executeUpdate(txnDetails);
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			// Insertion In IGRS_RAUDIT_DOCUMENT_DETAILS-COMMENTED BY SHRUTI-----3RD SEP 2013---UPLOAD WILL BE CHANGED
			boolean flagDocTxn = true;
			if(flagReoprtTxn){
			try {
				for (int i = 0; i < fileNames.length; i++) {
					ArrayList listDocId = getDocTxnId();
					ArrayList listDocId2 = (ArrayList) listDocId.get(0);
					String docId1 = (String) listDocId2.get(0);
					//modified by shruti--1st oct 2013
					/*flagDocTxn = dbUtil.attachAGMPAuditReport(docId1,auditFilePath, fileNames[i], fileDetails, "audit");*/
					flagDocTxn=dbUtil.saveAGMPAuditReport(docId1, auditFilePath,fileNames[i], fileDetails, "audit");
					listDocTxnId.add(docId1);
					
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			}
			boolean flagParaTxn = true;
			if(flagReoprtTxn && flagReoprtTxn){
			
				// Insertion In IGRS_RAUDIT_PARA_DETAILS
			try {
				dbUtil.createPreparedStatement(CommonSQL.INSERT_IGRS_RAUDIT_PARA_DETAILS);
				flagParaTxn = dbUtil.executeUpdate(paraDetails);
				listParaTxnId.add(paraDetails[0]);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			}
			// Insertion In IGRS_RAUDIT_PARA_OBJ_DETAILS
			boolean flagObjTxn = true;
			if(flagReoprtTxn && flagReoprtTxn && flagParaTxn){
			for (int i = 0; i < objection.length; i++) {
				try {
					ArrayList listObj = getMaxObjectionId();
					ArrayList listObj2 = (ArrayList) listObj.get(0);
					
					String[] objectionDetails = new String[9];
					objectionDetails[0] = (String) listObj2.get(0);
					objectionDetails[1] = objection[i];
					objectionDetails[2] = valAgmp[i];
					objectionDetails[3] = stampDuty[i];
					objectionDetails[4] = regFee[i];
					//added by shruti
					objectionDetails[5] = docId[i];
					objectionDetails[6] = caseId[i];
					//MODIFIED BY SHRUTI---3RD OCT 2013
					objectionDetails[7] ="OPEN";
					//objectionDetails[7] = objStatus[i];
					objectionDetails[8] =marketVal[i];
					//end
					dbUtil.createPreparedStatement(CommonSQL.INSERT_IGRS_RAUDIT_PARA_OBJ);
					flagObjTxn = dbUtil.executeUpdate(objectionDetails);
					listObjTxnId.add(objectionDetails[0]);
				} catch (Exception e) {
					flagObjTxn = true;					
					e.printStackTrace();
				}
			}
			}
			// Insertion In IGRS_RAUDIT_COMMENT_DETAILS
			boolean flagComTxn = true;
			if(flagReoprtTxn && flagReoprtTxn && flagParaTxn && flagObjTxn){
			for (int i = 0; i < agmpComm.length; i++) {
				try {
					ArrayList listAgmp = getMaxCommentId();
					ArrayList listAgmp2 = (ArrayList) listAgmp.get(0);
					String[] agmpComments = new String[4];
					agmpComments[0] = (String) listAgmp2.get(0);
					agmpComments[1] = agmpComm[i];
					agmpComments[2] = comDetails[0];
					agmpComments[3] = comDetails[1];
					dbUtil.createPreparedStatement(CommonSQL.INSERT_IGRS_RAUDIT_PARA_AGMP_COMMENT);
					flagComTxn = dbUtil.executeUpdate(agmpComments);
					listCommTxnId.add(agmpComments[0]);
					
				} catch (Exception e) {
					flagComTxn = true;
					
					e.printStackTrace();
				}
			}
			}

			// Insertion In IGRS_RAUDIT_COMMENTS_MAPPING
			boolean flagComMapTxn = true;
			if(flagReoprtTxn && flagReoprtTxn && flagParaTxn && flagObjTxn && flagComTxn){
			try {
				String[] commentMapping = new String[4];
				for (int i = 0; i < listCommTxnId.size(); i++) 
				{
					commentMapping[0] = (String) listReportId.get(0);
					commentMapping[1] = (String) listCommTxnId.get(i);
					commentMapping[2] = (String) listParaTxnId.get(0);
					commentMapping[3] = (String) listObjTxnId.get(i);
					dbUtil.createPreparedStatement(CommonSQL.INSERT_IGRS_RAUDIT_COMMENT_MAPPING);
					flagComMapTxn = dbUtil.executeUpdate(commentMapping);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			}
			// Insertion In IGRS_RAUDIT_DOC_MAPPING
			String[] docMapping = new String[4];
			boolean flagDocMapTxn = true;
			if(flagReoprtTxn && flagReoprtTxn && flagParaTxn && flagObjTxn && flagComTxn && flagComMapTxn){
			try {
				for (int i = 0; i < listDocTxnId.size(); i++) {
					docMapping[0] = (String) listReportId.get(0);
					docMapping[1] = (String) listDocTxnId.get(i);
					docMapping[2] = (String) listParaTxnId.get(0);
					if (listObjTxnId.size() == 1)
						docMapping[3] = (String) listObjTxnId.get(0);
					else
						docMapping[3] = (String) listObjTxnId.get(i);
					dbUtil.createPreparedStatement(CommonSQL.INSERT_IGRS_RAUDIT_DOC_MAPPING);
					flagDocMapTxn = dbUtil.executeUpdate(docMapping);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			}
			// Insertion In IGRS_RAUDIT_PARA_MAPPING
			String[] paraMapping = new String[2];
			boolean flagParaMapTxn = true;
			if(flagReoprtTxn && flagReoprtTxn && flagParaTxn && flagObjTxn && flagComTxn && flagComMapTxn && flagDocMapTxn){
			try {
				paraMapping[0] = (String) listReportId.get(0);
				paraMapping[1] = (String) listParaTxnId.get(0);
				dbUtil.createPreparedStatement(CommonSQL.INSERT_IGRS_RAUDIT_PARA_MAPPING);
				flagParaMapTxn = dbUtil.executeUpdate(paraMapping);
				
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			}
			// Insertion In IGRS_RAUDIT_PARA_OBJ_MAPPING
			String[] paraObjMapping = new String[2];
			boolean flagParaObjMapTxn = true;
			if(flagReoprtTxn && flagReoprtTxn && flagParaTxn && flagObjTxn && flagComTxn && flagComMapTxn && flagDocMapTxn && flagParaMapTxn){
			
			try {
				for (int i = 0; i < listObjTxnId.size(); i++) {
					paraObjMapping[0] = (String) listParaTxnId.get(0);
					paraObjMapping[1] = (String) listObjTxnId.get(i);
					dbUtil.createPreparedStatement(CommonSQL.INSERT_IGRS_RAUDIT_PARA_OBJ_MAPPING);
					flagParaObjMapTxn = dbUtil.executeUpdate(paraObjMapping);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			}
			
			/*if (flagReoprtTxn && flagDocTxn && flagParaTxn && flagObjTxn
					&& flagComTxn && flagComMapTxn && flagDocMapTxn
					&& flagParaMapTxn && flagParaObjMapTxn) --- MODIFIED BY SHRUTI*/
			if (flagReoprtTxn && flagParaTxn && flagObjTxn
					&& flagComTxn && flagComMapTxn && flagDocMapTxn
					&& flagParaMapTxn && flagParaObjMapTxn)
			{
				dbUtil.commit();
				returnVal = txnDetails[14];
				
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

	/**
	 * @param listDetails
	 * @param reportId
	 * @return
	 */
	public String submitExistingAuditReport(ArrayList listDetails,
			String reportId) {
		String returnVal = "false";
		boolean flagObj = false;
		boolean flagCmt = false;
		boolean flagPara = false;
		boolean flagCommMap = false;

		String[] paraDetails = (String[]) listDetails.get(0);
		String[] objection = (String[]) listDetails.get(1);
		String[] valAgmp = (String[]) listDetails.get(2);
		String[] stampDuty = (String[]) listDetails.get(3);
		String[] regFee = (String[]) listDetails.get(4);
		String[] marketVal=(String[]) listDetails.get(5);
		
		//added by shruti
		String[] docId = (String[]) listDetails.get(6);
		String[] objStatus = (String[]) listDetails.get(7);
		String[] caseId = (String[]) listDetails.get(8);
		//end
		//modified by shruti
		String[] agmpComm = (String[]) listDetails.get(9);
		String[] comDetails = (String[]) listDetails.get(10);

		// ArrayList listReportId = new ArrayList();
		ArrayList listParaTxnId = new ArrayList();
		ArrayList listCommTxnId = new ArrayList();
		ArrayList listObjTxnId = new ArrayList();
		ArrayList listDocTxnId = new ArrayList();

		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.setAutoCommit(false);

			dbUtil.createPreparedStatement(CommonSQL.INSERT_IGRS_RAUDIT_PARA_DETAILS);
			flagPara = dbUtil.executeUpdate(paraDetails);
			listParaTxnId.add(paraDetails[0]);

			for (int i = 0; i < objection.length; i++) {
				try {
					ArrayList listObj = getMaxObjectionId();
					ArrayList listObj2 = (ArrayList) listObj.get(0);
					String[] objectionDetails = new String[9];
					objectionDetails[0] = (String) listObj2.get(0);
					objectionDetails[1] = objection[i];
					objectionDetails[2] = valAgmp[i];
					objectionDetails[3] = stampDuty[i];
					objectionDetails[4] = regFee[i];
					objectionDetails[5] = docId[i];
					objectionDetails[6] = caseId[i];
					//objectionDetails[7] = objStatus[i];
					//edited by shruti 16th nov 2013
					objectionDetails[7] = "OPEN";
					objectionDetails[8] = marketVal[i];
					dbUtil.createPreparedStatement(CommonSQL.INSERT_IGRS_RAUDIT_PARA_OBJ);
					flagObj = dbUtil.executeUpdate(objectionDetails);
					listObjTxnId.add(objectionDetails[0]);

				} catch (Exception e) {
					flagObj = false;
					
					e.printStackTrace();
				}
			}
			// Insertion In IGRS_RAUDIT_COMMENT_DETAILS
			for (int i = 0; i < agmpComm.length; i++) {
				try {
					ArrayList listAgmp = getMaxCommentId();
					ArrayList listAgmp2 = (ArrayList) listAgmp.get(0);
					String[] agmpComments = new String[4];
					agmpComments[0] = (String) listAgmp2.get(0);
					agmpComments[1] = agmpComm[i];
					agmpComments[2] = comDetails[0];
					agmpComments[3] = comDetails[1];
					
					dbUtil.createPreparedStatement(CommonSQL.INSERT_IGRS_RAUDIT_PARA_AGMP_COMMENT);
					flagCmt = dbUtil.executeUpdate(agmpComments);
					listCommTxnId.add(agmpComments[0]);

				} catch (Exception e) {
					flagCmt = false;
					
					e.printStackTrace();
				}
			}

			// Insertion In IGRS_RAUDIT_COMMENTS_MAPPING

			String[] commentMapping = new String[4];
			for (int i = 0; i < listCommTxnId.size(); i++) {
				commentMapping[0] = reportId;
				commentMapping[1] = (String) listCommTxnId.get(i);
				commentMapping[2] = (String) listParaTxnId.get(0);
				commentMapping[3] = (String) listObjTxnId.get(i);

				dbUtil.createPreparedStatement(CommonSQL.INSERT_IGRS_RAUDIT_COMMENT_MAPPING);
				flagCommMap = dbUtil.executeUpdate(commentMapping);

			}

			// Insertion In IGRS_RAUDIT_DOC_MAPPING

			// Insertion In IGRS_RAUDIT_PARA_MAPPING
			String[] paraMapping = new String[2];
			boolean flagParaMap = false;

			paraMapping[0] = reportId;
			paraMapping[1] = (String) listParaTxnId.get(0);
			dbUtil.createPreparedStatement(CommonSQL.INSERT_IGRS_RAUDIT_PARA_MAPPING);
			flagParaMap = dbUtil.executeUpdate(paraMapping);

			// Insertion In IGRS_RAUDIT_PARA_OBJ_MAPPING
			String[] paraObjMapping = new String[2];
			boolean flagParaObjMap = false;
			for (int i = 0; i < listObjTxnId.size(); i++) {
				paraObjMapping[0] = (String) listParaTxnId.get(0);
				paraObjMapping[1] = (String) listObjTxnId.get(i);

				dbUtil.createPreparedStatement(CommonSQL.INSERT_IGRS_RAUDIT_PARA_OBJ_MAPPING);
				flagParaObjMap = dbUtil.executeUpdate(paraObjMapping);

			}

			if (flagParaObjMap && flagPara && flagObj && flagCommMap
					&& flagParaMap && flagCmt) {
				dbUtil.commit();
				returnVal = reportId;
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

	/**
	 * @return
	 */
	private ArrayList getReportTxnId() {

		ArrayList txnId = new ArrayList();
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			txnId = dbUtil
					.executeQuery(CommonSQL.RETRIEVE_SEQ_RAUDIT_TXN_DETAILS);
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

	//added by shruti---13 june 2014
	private ArrayList getInternalExpReportTxnId() {

		ArrayList txnId = new ArrayList();
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			txnId = dbUtil.executeQuery(CommonSQL.GET_INTERNAL_EXP_SEQ);
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
	private ArrayList getAGMPExpReportTxnId() {

		ArrayList txnId = new ArrayList();
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			txnId = dbUtil.executeQuery(CommonSQL.GET_AGMP_EXP_SEQ);
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
	private ArrayList getAGMPRcptReportTxnId() {

		ArrayList txnId = new ArrayList();
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			txnId = dbUtil.executeQuery(CommonSQL.GET_AGMP_RCPT_SEQ);
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
	private ArrayList getInternalRcptReportTxnId() {

		ArrayList txnId = new ArrayList();
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			txnId = dbUtil.executeQuery(CommonSQL.GET_INTERNAL_RCPT_SEQ);
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

	//end
	/**
	 * @return
	 */
	private ArrayList getDocTxnId() {
		ArrayList list = new ArrayList();
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			list = dbUtil.executeQuery(CommonSQL.GETDOCUMENTTXNID);
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

	/**
	 * @return
	 * @throws Exception
	 */
	private ArrayList getMaxObjectionId() throws Exception {
		ArrayList list = null;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			list = dbUtil.executeQuery(CommonSQL.GETOBJECTIONID);
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

	/**
	 * @return
	 * @throws Exception
	 */
	private ArrayList getMaxCommentId() throws Exception {
		ArrayList list = null;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			list = dbUtil.executeQuery(CommonSQL.GETCOMMENTID);
			
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

	public ArrayList getReceiptParaList(String language) {
		ArrayList list = null;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			if("en".equalsIgnoreCase(language))
			{
			list = dbUtil.executeQuery(CommonSQL.SELECT_PARA_DETAILS);
			}
			else
			{
			list = dbUtil.executeQuery(CommonSQL.SELECT_PARA_DETAILS_H);
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

	//added by shruti-10th sep 2013
	public ArrayList getReceiptParaDtlsList(String reportId) {
		ArrayList list = null;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			String[] param={reportId};
			dbUtil.createPreparedStatement(CommonSQL.GET_AUDIT_PARA_LIST);
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

	//end
	public ArrayList getExpenParaList() {
		ArrayList list = null;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			list = dbUtil.executeQuery(CommonSQL.SELECT_EXP_PARA_DETAILS);
			
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
	
	//added by shruti
	public String getOfficeName(String officeId,String language) {
		ArrayList list = null;
		DBUtility dbUtil = null;
		String officeName="";
		String arry[] = new String[1];
		if (officeId != null) {
			arry[0] = officeId;
		}
		try {
			dbUtil = new DBUtility();
			if("en".equalsIgnoreCase(language))
			{
			dbUtil.createPreparedStatement(CommonSQL.GETOFFICENAME);
			}
			else
			{
			dbUtil.createPreparedStatement(CommonSQL.GETOFFICENAME_H);
			}
			officeName=dbUtil.executeQry(arry);
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
		return officeName;
	}
	
	
	//added by shruti
	public ArrayList getCaseidInfo(String sear[]) {
		ArrayList search = new ArrayList();
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.CASE_RETRIEVE_QUERY);
			String caseid = sear[0];
			search = dbUtil.executeQueryLikeSearch(caseid.toUpperCase());
			
		} catch (Exception e) {
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
		return search;
	}
	
	public ArrayList getCaseDetails(String caseid[]) {
		
		ArrayList finalList=new ArrayList();
		
		ArrayList caseidother = new ArrayList();
		DBUtility dbUtil = null;
		String caseId="";
		try {
			//caseid[1]="testabc";
			String[] param={caseid[0]};
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.CASE_DETAILS_RETRIEVE_QRY);
			caseidother = dbUtil.executeQuery(param);
			for (int i = 0; i < caseidother.size(); i++) {
				ArrayList row_list = (ArrayList) caseidother.get(i);
				{
					caseId=(String)row_list.get(0);
				}
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
		return caseidother;
	}

	//added by shruti-12th sep 2013
	public ArrayList getCaseStatus(String caseId[]) {
		ArrayList caseIdStatus = new ArrayList();
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.GETCASESTATUS);
			caseIdStatus = dbUtil.executeQuery(caseId);
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
		return caseIdStatus;
	}
	public ArrayList getRRCCaseStatus(String caseId[]) {
		ArrayList caseIdStatus = new ArrayList();
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.GETRRCCASESTATUS);
			caseIdStatus = dbUtil.executeQuery(caseId);
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
		return caseIdStatus;
	}
	
	//added by ashima
	public ArrayList getDocDetails(String transid) throws Exception

    {
            ArrayList partyList =null;
            DBUtility dbUtil = null;
            try {   
                    dbUtil = new DBUtility();
                    String[] param={transid};
                    String str = CommonSQL.GET_DOC_DETAILS;
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
//end
	//added by SHRUTI
	public ArrayList getParaDetails(String transid) throws Exception

    {
            ArrayList paraList =null;
            DBUtility dbUtil = null;
            try {   
                    dbUtil = new DBUtility();
                    String[] param={transid};
                    String str = CommonSQL.GETPARADTLS;
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
	public ArrayList getParaStatusList(String lastStatus) throws Exception

    {
            ArrayList paraStatusList =null;
            DBUtility dbUtil = null;
            try {   
                    dbUtil = new DBUtility();
                    //modified by shruti----19 june 2014
                    String[] param={lastStatus};
                    if(lastStatus!=null)
                    {
                    	if("O".equalsIgnoreCase(lastStatus))
                    	{
                    		lastStatus="OPEN";
                    	}
                    	if(!"CLOSE".equalsIgnoreCase(lastStatus))
                    	{
                    	String str = CommonSQL.GETPARASTATUSLIST;
                        dbUtil.createPreparedStatement(str);
                        paraStatusList = dbUtil.executeQuery(param);
                    	}
                    	else
                    	{
                    		String str = CommonSQL.GETPARASTATUSLISTWHENCLOSE;
                    		dbUtil.createStatement();
                            //dbUtil.createPreparedStatement(str);
                            //paraStatusList = dbUtil.executeQuery(param);
                    		paraStatusList=dbUtil.executeQuery(str);
                    	}
                    }
                    //end
                    
                    
            } catch (Exception e) {
                    System.out.println(e);
            } finally {
                    if(dbUtil!=null){
                            dbUtil.closeConnection();
                            }
            }                               
            return paraStatusList;               

    }
//end
	//added by Shreeraj
		public ArrayList getOfficeType() throws Exception

	    {
	            ArrayList partyList =null;
	            DBUtility dbUtil = null;
	            try {   
	                    dbUtil = new DBUtility();
	                    
	                    String str = CommonSQL.GET_OFFICE_TYPE;
	                    dbUtil.createStatement();
	                    partyList = dbUtil.executeQuery(str);
	            } catch (Exception e) {
	                    logger.info(e);
	            } finally {
	                    if(dbUtil!=null){
	                            dbUtil.closeConnection();
	                            }
	            }                               
	            return partyList;               

	    }
		public ArrayList getParaType() throws Exception

	    {
	            ArrayList partyList =null;
	            DBUtility dbUtil = null;
	            try {   
	                    dbUtil = new DBUtility();
	                    
	                    String str = CommonSQL.GET_PARA_TYPE_LIST;
	                    dbUtil.createStatement();
	                    partyList = dbUtil.executeQuery(str);
	            } catch (Exception e) {
	                    logger.info(e);
	            } finally {
	                    if(dbUtil!=null){
	                            dbUtil.closeConnection();
	                            }
	            }                               
	            return partyList;               

	    }
		public ArrayList getParaStatus() throws Exception

	    {
	            ArrayList partyList =null;
	            DBUtility dbUtil = null;
	            try {   
	                    dbUtil = new DBUtility();
	                    
	                    String str = CommonSQL.GET_PARA_STATUS_LIST;
	                    dbUtil.createStatement();
	                    partyList = dbUtil.executeQuery(str);
	            } catch (Exception e) {
	                    logger.info(e);
	            } finally {
	                    if(dbUtil!=null){
	                            dbUtil.closeConnection();
	                            }
	            }                               
	            return partyList;               

	    }
		
		public ArrayList  getOfficeTypeList(String offId,String ofcId) throws Exception

	    {
	            String role=getRole(ofcId);
				ArrayList partyList =null;
	            DBUtility dbUtil = null;
	            
	            try {   
	                    dbUtil = new DBUtility();
	                  
	                    String str = "";
	                    if("SRO".equalsIgnoreCase(role))
	                    {
	                    	str=CommonSQL.GET_OFFICE_LIST_SRO;
	                    	  String[] param={ofcId};
	                    	  dbUtil.createPreparedStatement(str);
	  	                    partyList = dbUtil.executeQuery(param);
	                    }
	                    else if("DRO".equalsIgnoreCase(role))
	                    {
	                    	str=CommonSQL.GET_OFFICE_LIST_DRO;
	                    	  String[] param={ofcId};
	                    	  dbUtil.createPreparedStatement(str);
	  	                    partyList = dbUtil.executeQuery(param);
	                    }
	                    else if("DIGO".equalsIgnoreCase(role))
	                    {
	                    	str=CommonSQL.GET_OFFICE_LIST_DIG;
	                    	  String[] param={ofcId};
	                    	  dbUtil.createPreparedStatement(str);
	  	                    partyList = dbUtil.executeQuery(param);
	                    }
	                    else if("IGO".equalsIgnoreCase(role))
	                    {
	                    	str=CommonSQL.GET_DROSROHO_OFFICE_LIST;
	                    	  String[] param={offId};
	                    	  dbUtil.createPreparedStatement(str);
	  	                    partyList = dbUtil.executeQuery(param);
	                    }
	                   
	            } catch (Exception e) {
	                    logger.info(e);
	            } finally {
	                    if(dbUtil!=null){
	                            dbUtil.closeConnection();
	                            }
	            }                               
	            return partyList;               

	    }
		
		public ArrayList  getSearchResult(String durFrom,String durTo, AGMPReportDetailsDTO dto) throws Exception

	    {
	            ArrayList partyList =null;
	            DBUtility dbUtil = null;
	            try {   	String[] detArray=new  String[3];
	                    dbUtil = new DBUtility();
	                //    detArray[0]=dto.getParaId();
       		    		detArray[0]=dto.getOffcId();
       		        	//detArray[1]=dto.getParaStatusId();
       		    		//detArray[2]=dto.getReportStatus();
       		    		detArray[1]=durFrom;
       		    		detArray[2]=durTo;
       		    		
	                    String str = CommonSQL.GET_AGMP_REPORT_SEARCH_LIST;
	                    if("1".equalsIgnoreCase(dto.getReportType()))
	                    {
	                    	str= str+ " and S.TRANSACTION_ID like '%AGR%'";
	                    }
	                    else  if("2".equalsIgnoreCase(dto.getReportType()))
	                    {
	                    	str= str+ " and S.TRANSACTION_ID like '%AGE%'";
	                    }
	                    dbUtil.createPreparedStatement(str);
	                    partyList = dbUtil.executeQuery(detArray);
	            } catch (Exception e) {
	                    logger.info(e);
	            } finally {
	                    if(dbUtil!=null){
	                            dbUtil.closeConnection();
	                            }
	            }                               
	            return partyList;               

	    }
		
		public ArrayList  getSearchInternalResult(String durFrom,String durTo, AGMPReportDetailsDTO dto) throws Exception

	    {
	            ArrayList partyList =null;
	            DBUtility dbUtil = null;
	            try {   	String[] detArray=new  String[3];
	                    dbUtil = new DBUtility();
	                 //   detArray[0]=dto.getParaId();
       		    		detArray[0]=dto.getOffcId();
       		        	//detArray[1]=dto.getParaStatusId();
       		    		//detArray[2]=dto.getReportStatus();
       		    		detArray[1]=durFrom;
       		    		detArray[2]=durTo;
	                    String str = CommonSQL.GET_INTERNAL_REPORT_SEARCH_LIST;
	                    if("1".equalsIgnoreCase(dto.getReportType()))
	                    {
	                    	str= str+ " and S.TRANSACTION_ID like '%IA%'";
	                    }
	                    else  if("2".equalsIgnoreCase(dto.getReportType()))
	                    {
	                    	str= str+ " and S.TRANSACTION_ID like '%IE%'";
	                    }
	                    dbUtil.createPreparedStatement(str);
	                    partyList = dbUtil.executeQuery(detArray);
	            } catch (Exception e) {
	                    System.out.println(e);
	            } finally {
	                    if(dbUtil!=null){
	                            dbUtil.closeConnection();
	                            }
	            }                               
	            return partyList;               

	    }
		
		public ArrayList  fetchSearchResult(String repId) throws Exception

	    {
	            ArrayList partyList =null;
	            DBUtility dbUtil = null;
	            try {   
	                    dbUtil = new DBUtility();
	                    String[] param={repId};
	                    String str = CommonSQL.GET_AGMP_SEARCH_RESULT;
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
		
		public ArrayList  fetchParaResult(String repId) throws Exception

	    {
	            ArrayList partyList =null;
	            DBUtility dbUtil = null;
	            try {   
	                    dbUtil = new DBUtility();
	                    String[] param={repId};
	                    String str = CommonSQL.GET_AGMP_SEARCH_PARA_RESULT;
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
	//end
		//added
		public boolean  updateParaDetails(AGMPReportDetailsDTO dto) throws Exception

	    {
			boolean flg=false;
	            ArrayList partyList =null;
	            DBUtility dbUtil = null;
	            try {  
	            		String[] detArray=new  String[3];
	                    dbUtil = new DBUtility();
       		        	detArray[0]=dto.getParaStatus();
       		    		detArray[1]=dto.getParaComments();
       		    		detArray[2]=dto.getParaId();
	                    String str = CommonSQL.UPDATEPARADETAILS;
	                    dbUtil.createPreparedStatement(str);
	                    flg=dbUtil.executeUpdate(detArray);
	            } catch (Exception e) {
	                    logger.info(e);
	            } finally {
	                    if(dbUtil!=null){
	                            dbUtil.closeConnection();
	                            }
	            }                               
	            return flg;               

	    }
		//end
		//added by shruti-15th oct 2013
		public ArrayList getObjectionDetails(String transid) throws Exception

	    {
	            ArrayList partyList =null;
	            DBUtility dbUtil = null;
	            try {   
	                    dbUtil = new DBUtility();
	                    String[] param={transid};
	                    String str = CommonSQL.GETOBJECTIONDETAILS;
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
		//vinay
		public ArrayList getObjectionDetls(String transid) throws Exception

	    {
	            ArrayList partyList =null;
	            DBUtility dbUtil = null;
	            try {   
	                    dbUtil = new DBUtility();
	                    String[] param={transid};
	                    String str = CommonSQL.GETOBJECTIONDETAILS_VIEW;
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
		//added by shruti-16th oct 2013
		public ArrayList getObjectionParaDetails(String transid) throws Exception

	    {
	            ArrayList objparaList =null;
	            DBUtility dbUtil = null;
	            try {   
	                    dbUtil = new DBUtility();
	                    String[] param={transid};
	                    String str = CommonSQL.GETOBJECTIONPARADETAILS;
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

		//added by shruti-16th oct 2013
		public boolean  updateObjectionParaDetails(AGMPReportDetailsDTO dto) throws Exception

	    {
			boolean flg=false;
	            ArrayList partyList =null;
	            DBUtility dbUtil = null;
	            try {  
	            		String[] detArray=new  String[3];
	                    dbUtil = new DBUtility();
	                    detArray[0]=dto.getObjStatus();
	                    detArray[1]=dto.getFinalComments();
       		        	detArray[2]=dto.getObjId();
       		        	
       		    		//detArray[1]=dto.getParaComments();
       		    		//detArray[2]=dto.getParaId();
	                    String str = CommonSQL.UPDATEOBJPARADETAILS;
	                    dbUtil.createPreparedStatement(str);
	                    flg=dbUtil.executeUpdate(detArray);
	            } catch (Exception e) {
	                    logger.info(e);
	            } finally {
	                    if(dbUtil!=null){
	                            dbUtil.closeConnection();
	                            }
	            }                               
	            return flg;               

	    }

		//end
		
		public ArrayList getJurisdictionSRO(String officeId,String language) {
			ArrayList list = null;
			DBUtility dbUtil = null;
			String SQL ="";
			if("en".equalsIgnoreCase(language))
			{
			 SQL=CommonSQL.GETJURISDICTIONSRO;
			}
			else
			{
				 SQL=CommonSQL.GETJURISDICTIONSRO_HI;
			}
			String arry[] = new String[1];
			if (officeId != null) {
				arry[0] = officeId;
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
		public String getRole(String officeid)
		{
			String list=null;
			DBUtility dbUtil = null;
			try{
				 dbUtil=new DBUtility();
				 String[] param={officeid.toUpperCase()};
				 String str=AddComplianceSQL.GET_ROLE;
				 dbUtil.createPreparedStatement(str);
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
		
		public String getParaStatus(String auditTxnId)
		{
			ArrayList list=new ArrayList();
			String status = "CLOSE";
			DBUtility dbUtil = null;
			try{
				 dbUtil=new DBUtility();
				 String[] param={auditTxnId};
				 String str = CommonSQL.GET_PARA_STATUS;
				 dbUtil.createPreparedStatement(str);
				 list = dbUtil.executeQuery(param);
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
			
			for(int i = 0 ;i < list.size();i++)
			{
				ArrayList subList = (ArrayList)list.get(i);
				if(subList!=null && subList.size()>0)
				{
				if(("OPEN").equalsIgnoreCase(subList.get(0).toString()) || ("O".equalsIgnoreCase(subList.get(0).toString())))
				{
					status = "OPEN";
					break;
				}
				}
			}
			return status;
		}		
		
		public String getObjStatus(String paraId)
		{
			ArrayList list=new ArrayList();
			String status = "CLOSE";
			DBUtility dbUtil = null;
			try{
				 dbUtil=new DBUtility();
				 String[] param={paraId};
				 String str = CommonSQL.GET_OBJECTION_STATUS;
				 dbUtil.createPreparedStatement(str);
				 list = dbUtil.executeQuery(param);
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
			
			for(int i = 0 ;i < list.size();i++)
			{
				ArrayList subList = (ArrayList)list.get(i);
				if(subList.get(0).toString().equalsIgnoreCase("OPEN") || subList.get(0).toString().equalsIgnoreCase("O"))
				{
					status = "OPEN";
					break;
				}
			}
			return status;
		}		
		
}
