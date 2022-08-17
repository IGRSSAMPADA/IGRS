package com.wipro.igrs.deedDraft.dao;

import java.io.Writer;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import oracle.sql.CLOB;

import org.apache.log4j.Logger;

import com.wipro.igrs.common.PropertiesFileReader;
import com.wipro.igrs.common.sql.CommonSQL;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.deedDraft.dto.DeedDraftDTO;
import com.wipro.igrs.deedDraft.sql.DeedDraftSQL;
import com.wipro.igrs.newreginit.constant.RegInitConstant;

public class DeedDraftViewEditDAO {
	private Logger logger = Logger.getLogger(DeedDraftViewEditDAO.class);

	public Clob getCLOB(String innStr, Connection conn) throws Exception {
		logger.debug("BEGINNING OF GETCLOB METHOD PARAM STRING::::::" + innStr);
		PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
		String pathClob = pr.getValue("reg.deed_draft_clob");
		Clob tempClob = null;
		// If the temporary CLOB has not yet been created, create new
		logger.debug("CONNECTION BEFORE CASTING" + conn.getClass().getName());
		//Connection orConn = (oracle.jdbc.OracleConnection)conn;
		//	logger.debug("CONNECTION AFTER CASTING"+orConn.getClass().getName());
		//tempClob = CLOB.createTemporary(orConn, true, CLOB.DURATION_SESSION);
		if ("SIT".equalsIgnoreCase(pathClob)) {
			Connection orConn = (oracle.jdbc.OracleConnection) conn;
			tempClob = CLOB.createTemporary(orConn, true, CLOB.DURATION_SESSION);
		} else {
			tempClob = conn.createClob();
		}
		logger.debug("CONNECTION ENVIRONEMT TYPE:::::::::::::" + pathClob);
		logger.debug("CLOB CREATED");
		// Open the temporary CLOB in readwrite mode to enable writing
		//tempClob.open(CLOB.MODE_READWRITE);
		// Get the output stream to write
		Writer tempClobWriter = tempClob.setCharacterStream(1);
		// Write the data into the temporary CLOB
		tempClobWriter.write(innStr);
		// Flush and close the stream
		tempClobWriter.flush();
		tempClobWriter.close();
		logger.debug("ENDING OF GETCLOB METHOD RETURN CLOB:::::::");
		return tempClob;
	}

	/**
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public ArrayList getDraftDeedDetailsForDashboard(String userId) throws Exception {
		ArrayList list = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String sql = DeedDraftSQL.GET_DEED_DRAFT_VIEW_DETAILS;
			dbUtility.createPreparedStatement(sql);
			String userArr[] = { userId };
			list = dbUtility.executeQuery(userArr);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtility.closeConnection();
		}
		logger.debug("size of list in dao-->" + list.size());
		return list;
	}

	public ArrayList getDraftDeedDeleteDetailsForDashboard(String userId) throws Exception {
		ArrayList list = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String sql = DeedDraftSQL.GET_DEED_Delete_VIEW_DETAILS;
			dbUtility.createPreparedStatement(sql);
			String userArr[] = { userId, userId };
			list = dbUtility.executeQuery(userArr);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtility.closeConnection();
		}
		logger.debug("size of list in dao-->" + list.size());
		return list;
	}

	public boolean updateDeedDraftStatus(String deedDraftId) {
		boolean ret = false;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(DeedDraftSQL.UPDATE_DEED_TXN_DTLS_STATUS);
			ret = dbUtility.executeUpdate(new String[] { deedDraftId });
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ret;
	}

	//saurav
	public String getRegTxnId(String deedAppId) throws Exception {
		String regTxnId = "";
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String sql = DeedDraftSQL.GET_DEED_APP_ID;
			String[] arr = { deedAppId };
			dbUtility.createPreparedStatement(sql);
			regTxnId = dbUtility.executeQry(arr);
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
		} finally {
			dbUtility.closeConnection();
		}
		return regTxnId;
	}

	public ArrayList checkValidRegID(String appID) throws Exception {
		ArrayList list = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String sql = DeedDraftSQL.GET_APPLICATION_DETAILS;
			//String [] params = {appID};
			dbUtility.createPreparedStatement(sql);
			list = dbUtility.executeQuerySearch(appID);
			if (!(list == null || list.isEmpty())) {
				list = (ArrayList) list.get(0);
			}
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
		} finally {
			dbUtility.closeConnection();
		}
		return list;
	}

	public String getAppType(String deedAppId) throws Exception {
		String appType = "";
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String sql = DeedDraftSQL.GET_DEED_APP_TYPE;
			String[] arr = { deedAppId };
			dbUtility.createPreparedStatement(sql);
			appType = dbUtility.executeQry(arr);
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
		} finally {
			dbUtility.closeConnection();
		}
		return appType;
	}

	public String getMasterCheck(String deedAppId) throws Exception {
		String result = "";
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String sql = DeedDraftSQL.GET_MASTER_CHECK;
			String[] param = { deedAppId };
			dbUtility.createPreparedStatement(sql);
			result = dbUtility.executeQry(param);
			if ("".equals(result)) {
				result = "Y";
			}
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
		} finally {
			dbUtility.closeConnection();
		}
		return result;
	}

	public String getDeedStatus(String deedAppId) throws Exception {
		String result = "";
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String sql = DeedDraftSQL.GET_DEED_STATUS;
			String[] param = { deedAppId };
			dbUtility.createPreparedStatement(sql);
			result = dbUtility.executeQry(param);
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
		} finally {
			dbUtility.closeConnection();
		}
		return result;
	}

	public ArrayList getPropdetails(String appId) throws Exception {
		ArrayList list = null;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			list = new ArrayList();
			String sql = DeedDraftSQL.GET_PROPERTY_DETAILS;
			dbUtility.createPreparedStatement(sql);
			list = dbUtility.executeQuerySearch(appId);
			//list = (ArrayList) list.get(0);
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
		} finally {
			dbUtility.closeConnection();
		}
		return list;
	}

	public ArrayList getDutyDetails(String appId) throws Exception {
		ArrayList list = null;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			list = new ArrayList();
			String sql = DeedDraftSQL.GET_DUTY_DETAILS;
			dbUtility.createPreparedStatement(sql);
			list = dbUtility.executeQuerySearch(appId);
			//list = (ArrayList) list.get(0);
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
		} finally {
			dbUtility.closeConnection();
		}
		return list;
	}

	public ArrayList getConsenterDetails(String regTxnId, String userId) throws Exception {
		ArrayList consenterDetails = new ArrayList();
		DBUtility db = null;
		try {
			db = new DBUtility();
			String sql = DeedDraftSQL.GET_CONSENTER_DETLS;
			String[] arr = { userId, regTxnId };
			db.createPreparedStatement(sql);
			consenterDetails = db.executeQuery(arr);
		} catch (Exception e) {
			logger.debug(e);
			e.printStackTrace();
		} finally {
			db.closeConnection();
		}
		return consenterDetails;
	}

	public ArrayList getAppDetails(String appId) throws Exception {
		ArrayList list = null;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String sql = DeedDraftSQL.GET_APP_DETAILS;
			dbUtility.createPreparedStatement(sql);
			list = dbUtility.executeQuerySearch(appId);
			list = (ArrayList) list.get(0);
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
		} finally {
			dbUtility.closeConnection();
		}
		return list;
	}

	public String getPropRelated(String deedId, String instId) throws Exception {
		String propCheck = "N";
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String sql = "select prop_req_flag from igrs_deed_instrument_master where deed_type_id=? and instrument_id=?";
			dbUtility.createPreparedStatement(sql);
			String[] arr = { deedId, instId };
			propCheck = dbUtility.executeQry(arr);
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
		} finally {
			dbUtility.closeConnection();
		}
		return propCheck;
	}

	//for property optional flag
	public String getPropOptionalCheck(String deedId, String instId) throws Exception {
		String propOptionalCheck = "N";
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String sql = "select prop_optional_flag from igrs_deed_instrument_master where deed_type_id=? and instrument_id=?";
			dbUtility.createPreparedStatement(sql);
			String[] arr = { deedId, instId };
			propOptionalCheck = dbUtility.executeQry(arr);
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
		} finally {
			dbUtility.closeConnection();
		}
		return propOptionalCheck;
	}

	public String getPropValCheck(String deedId, String instId) throws Exception {
		String isVal = "N";
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String sql = "select PROP_VAL_REQ_FLAG from igrs_deed_instrument_master where deed_type_id=? and instrument_id=?";
			dbUtility.createPreparedStatement(sql);
			String[] arr = { deedId, instId };
			isVal = dbUtility.executeQry(arr);
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
		} finally {
			dbUtility.closeConnection();
		}
		return isVal;
	}

	public ArrayList getExemption(String appId, String type) throws Exception {
		ArrayList<String> list = null;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String sql = null;
			list = new ArrayList<String>();
			sql = DeedDraftSQL.GET_EXEMPTION_DETAILS;
			dbUtility.createPreparedStatement(sql);
			String[] arr = { type, appId };
			list = dbUtility.executeQuery(arr);
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
		} finally {
			dbUtility.closeConnection();
		}
		return list;
	}

	public ArrayList getValuationDetails(String params, String language) throws Exception {
		ArrayList list = null;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String sql = null;
			list = new ArrayList();
			ArrayList lst = null;
			sql = ("en").equalsIgnoreCase(language) ? DeedDraftSQL.GET_VAL_DETAILS_E : DeedDraftSQL.GET_VAL_DETAILS_H;
			String[] arr = { params };
			dbUtility.createPreparedStatement(sql);
			list = dbUtility.executeQuery(arr);
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
		} finally {
			dbUtility.closeConnection();
		}
		return list;
	}

	public String getPropType(String id) throws Exception {
		String type = "";
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String sql = DeedDraftSQL.GET_PROP_TYPE;
			dbUtility.createPreparedStatement(sql);
			String[] arr = { id };
			type = dbUtility.executeQry(arr);
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
		} finally {
			dbUtility.closeConnection();
		}
		return type;
	}

	public ArrayList getbuildingView(String id, String language) {
		DBUtility dbUtility = null;
		ArrayList details = new ArrayList();
		try {
			dbUtility = new DBUtility();
			try {
				String arr[] = new String[1];
				arr[0] = id; //TODO
				if (language.equalsIgnoreCase("en")) {
					dbUtility.createPreparedStatement(DeedDraftSQL.GET_BUILDING_VIEW_DETAILS);
				} else {
					dbUtility.createPreparedStatement(DeedDraftSQL.GET_BUILDING_VIEW_DETAILS_HINDI);
				}
				details = dbUtility.executeQuery(arr);
			} catch (Exception x) {
				logger.debug(x);
				x.printStackTrace();
			} finally {
				try {
					dbUtility.closeConnection();
				} catch (Exception ex) {
					logger.debug(ex);
					ex.printStackTrace();
				}
			}
		} catch (Exception e) {
			logger.debug(e);
		}
		return details;
	}
	//construction slab changes by saurav
	public String getConstructionSlabDesc(String constSlabId, String language) throws Exception {
		String constSlabDesc = "";
		DBUtility db = null;
		try{
			db = new DBUtility();
			String sql = "";
			if(language.equalsIgnoreCase("en")){
			sql = DeedDraftSQL.GET_CONST_SLAB_DESC_E;
			}else {
			sql = DeedDraftSQL.GET_CONST_SLAB_DESC_H;
			}
			String arr[] = {constSlabId};
			db.createPreparedStatement(sql);
			constSlabDesc = db.executeQry(arr);
		}catch(Exception e){
			logger.debug(e);
			e.printStackTrace();
		}finally{
			db.closeConnection();
		}
		return constSlabDesc;
	}
	
	public ArrayList getconstSlabDetail(String id, String language) throws Exception {
		ArrayList list = new ArrayList();
		DBUtility db = null;
		try{
			db = new DBUtility();
			String sql = DeedDraftSQL.GET_AGRI_CONSTRUCTION_CONST_SLAB;
			String[] arr = {id};
			db.createPreparedStatement(sql);
			list = db.executeQuery(arr);
		}catch (Exception e) {
			logger.debug(e);
			e.printStackTrace();
		} finally {
			db.closeConnection();
		}
		return list;
	}
	//construction slab changes end
	public ArrayList getClauseListAgriConst(String language, String agriTxnId) throws Exception {
		ArrayList list = null;
		DBUtility db = null;
		try {
			db = new DBUtility();
			String[] arr = { agriTxnId };
			if (language.equalsIgnoreCase("en")) {
				db.createPreparedStatement(DeedDraftSQL.GET_EN_SUB_AGRI_CLAUSES_LIST);
			} else {
				db.createPreparedStatement(DeedDraftSQL.GET_EN_SUB_AGRI_CLAUSES_LIST);
			}
			list = db.executeQuery(arr);
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
		} finally {
			db.closeConnection();
		}
		return list;
	}

	public ArrayList getBuildingDetailsAgri(String id, String language) {
		ArrayList details = new ArrayList();
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			try {
				String arr[] = new String[1];
				arr[0] = id; //TODO
				if (language.equalsIgnoreCase("en")) {
					dbUtil.createPreparedStatement(CommonSQL.GET_BUILDING_VIEW_DETAILS_AGRI);
				} else {
					dbUtil.createPreparedStatement(CommonSQL.GET_BUILDING_VIEW_DETAILS_AGRI_HINDI);
				}
				details = dbUtil.executeQuery(arr);
			} catch (Exception x) {
				logger.debug(x);
				x.printStackTrace();
			} finally {
				try {
					dbUtil.closeConnection();
				} catch (Exception ex) {
					logger.debug(ex);
					ex.printStackTrace();
				}
			}
		} catch (Exception e) {
			logger.debug(e);
		}
		return details;
	}

	public ArrayList getFloorList(String buildingTxnId, String language) throws Exception {
		ArrayList details = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			try {
				String arr[] = new String[1];
				arr[0] = buildingTxnId; //TODO
				if ("en".endsWith(language)) {
					dbUtility.createPreparedStatement(DeedDraftSQL.GET_BUILDING_FLOOR_DETAILS);
				} else {
					dbUtility.createPreparedStatement(DeedDraftSQL.GET_BUILDING_FLOOR_DETAILS_HINDI);
				}
				details = dbUtility.executeQuery(arr);
			} catch (Exception x) {
				logger.debug(x);
				x.printStackTrace();
			} finally {
				try {
					dbUtility.closeConnection();
				} catch (Exception ex) {
					logger.debug(ex);
					ex.printStackTrace();
				}
			}
		} catch (Exception e) {
			logger.debug(e);
		}
		return details;
	}

	public ArrayList getAgriDetails(String valId, String language) {
		ArrayList details = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			try {
				String arr[] = new String[1];
				arr[0] = valId; //TODO
				if (language.equalsIgnoreCase("en")) {
					dbUtility.createPreparedStatement(DeedDraftSQL.GET_AGRI_EN_VIEW_AREA_DETAILS);
				} else {
					dbUtility.createPreparedStatement(DeedDraftSQL.GET_AGRI_HI_VIEW_AREA_DETAILS);
				}
				details = dbUtility.executeQuery(arr);
			} catch (Exception x) {
				logger.debug(x);
				x.printStackTrace();
			} finally {
				try {
					dbUtility.closeConnection();
				} catch (Exception ex) {
					logger.debug(ex);
					ex.printStackTrace();
				}
			}
		} catch (Exception e) {
			logger.debug(e);
		}
		return details;
	}

	public ArrayList getagriPropDetailsList(String valId, String language) throws Exception {
		ArrayList details = new ArrayList();
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			try {
				String arr[] = new String[1];
				arr[0] = valId; //TODO
				if (language.equalsIgnoreCase("en")) {
					dbUtil.createPreparedStatement(DeedDraftSQL.GET_AGRI_EN_VIEW_PROP_DETAILS);
				} else {
					dbUtil.createPreparedStatement(DeedDraftSQL.GET_AGRI_HI_VIEW_PROP_DETAILS);
				}
				details = dbUtil.executeQuery(arr);
			} catch (Exception x) {
				logger.debug(x);
				x.printStackTrace();
			}
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				ex.printStackTrace();
			}
		}
		return details;
	}

	public ArrayList getAgriTreeDetailsList(String valId, String language) throws Exception {
		ArrayList treedetails = new ArrayList();
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			try {
				String arr[] = new String[1];
				arr[0] = valId; //TODO
				if (language.equalsIgnoreCase("en")) {
					dbUtil.createPreparedStatement(DeedDraftSQL.GET_EN_AGRI_TREE_DETAILS);
				} else {
					dbUtil.createPreparedStatement(DeedDraftSQL.GET_HI_AGRI_TREE_DETAILS);
				}
				treedetails = dbUtil.executeQuery(arr);
			} catch (Exception x) {
				logger.debug(x);
				x.printStackTrace();
			} finally {
				try {
					dbUtil.closeConnection();
				} catch (Exception ex) {
					logger.debug(ex);
					ex.printStackTrace();
				}
			}
		} catch (Exception e) {
			logger.debug(e);
		}
		return treedetails;
	}

	public ArrayList getClauseList(String language, String valId) throws Exception {
		ArrayList list = null;
		DBUtility db = null;
		try {
			db = new DBUtility();
			String[] arr = { valId };
			if (language.equalsIgnoreCase("en")) {
				db.createPreparedStatement(CommonSQL.GET_EN_SUB_CLAUSES_LIST);
			} else {
				db.createPreparedStatement(CommonSQL.GET_HI_SUB_CLAUSES_LIST);
			}
			list = db.executeQuery(arr);
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
		} finally {
			db.closeConnection();
		}
		return list;
	}

	public ArrayList getUserEnterableValue(String dutyTxnId, String propDutyId, String language) throws Exception {
		ArrayList list = null;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String query = "";
			if (language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
				query = DeedDraftSQL.GET_PROP_WISE_USER_ENTERABLE;
			} else {
				query = DeedDraftSQL.GET_PROP_WISE_USER_ENTERABLE_HI;
			}
			if (("".equals(propDutyId))) {
				query = query + DeedDraftSQL.PROP_DUTY_NOT_AVAILABLE;
				String param[] = { dutyTxnId };
				dbUtility.createPreparedStatement(query);
				list = dbUtility.executeQuery(param);
			} else {
				String param1[] = { dutyTxnId, propDutyId };
				query = query + DeedDraftSQL.PROP_DUTY_AVAILABLE;
				dbUtility.createPreparedStatement(query);
				list = dbUtility.executeQuery(param1);
			}
		} catch (Exception exception) {
			logger.debug("Exception in getPropWiseUserEnterables" + exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("RegCommonDAO in dao start" + e.getStackTrace());
			}
		}
		return list;
	}

	public ArrayList getKhasraDetails(String propertyId) throws Exception {
		ArrayList list = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String sql = DeedDraftSQL.GET_KHASHRA_DETAILS;
			dbUtility.createPreparedStatement(sql);
			list = dbUtility.executeQuerySearch(propertyId);
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
		} finally {
			dbUtility.closeConnection();
		}
		return list;
	}

	public ArrayList getPropDutyDetails(String dutyTxnId, String regTxnId, String propertyId) throws Exception {
		ArrayList list = null;
		DBUtility dbUtility = null;
		try {
			list = new ArrayList();
			dbUtility = new DBUtility();
			String sql = DeedDraftSQL.GET_PROP_DUTY_DETAILS;
			String[] arr = { dutyTxnId, regTxnId, propertyId };
			dbUtility.createPreparedStatement(sql);
			list = dbUtility.executeQuery(arr);
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
		} finally {
			dbUtility.closeConnection();
		}
		return list;
	}

	public ArrayList getDeedContent(String deedDraftId, String pageId) throws Exception {
		Clob clob = null;
		String deedContent = "", deed = "", deedStatus = "", createdBy = "";
		ArrayList list = new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility("");
			String sql = DeedDraftSQL.GET_DEED_NAME_CONTENT;
			//	//dbUtility.createPreparedStatement(sql);
			//String deedArr[] = {deedDraftId,
			//		pageId};
			//list = dbUtility.executeQuery(deedArr);
			Connection conn = dbUtility.getDBConnection();
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, deedDraftId);
			pst.setString(2, pageId);
			ResultSet rset = pst.executeQuery();
			while (rset.next()) {
				deed = (rset.getString("DEED_NAME"));
				clob = (rset.getClob("DEED_CONTENT"));
				deedStatus = (rset.getString("DEED_DRAFT_STATUS"));
				createdBy = (rset.getString("CREATED_BY"));
			}
			if (clob != null) {
				long length = clob.length();
				deedContent = clob.getSubString(1, (int) length);
			}
			list.add(deed);
			list.add(deedContent);
			list.add(deedStatus);
			list.add(createdBy);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtility.closeConnection();
		}
		return list;
	}

	public String getMaxPageNumber(String deedDraftId) throws Exception {
		String maxPageId = "";
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String sql = DeedDraftSQL.GET_MAX_PAGE_NUMBER;
			dbUtility.createPreparedStatement(sql);
			String deedArr[] = { deedDraftId };
			maxPageId = dbUtility.executeQry(deedArr);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtility.closeConnection();
		}
		return maxPageId;
	}

	public String getAdjudicationFlag(String applicationId) throws Exception {
		String adjuFlag = "";
		DBUtility db = null;
		try {
			db = new DBUtility();
			String sql = DeedDraftSQL.GET_ADJU_FLAG;
			String[] arr = { applicationId };
			db.createPreparedStatement(sql);
			adjuFlag = db.executeQry(arr);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.closeConnection();
		}
		return adjuFlag;
	}

	public boolean saveDeedDraftDetails(DeedDraftDTO ddto, String userID, String appType, String applicationId) throws Exception {
		logger.debug("BEGINNING OF saveDeedDraftDetails METHOD PARAM STRING user::::::" + userID);
		boolean flag = false;
		int i = 0;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			if (ddto.getFirstPageChk().equalsIgnoreCase("Y")) {
				if (checkDeedRecord(ddto.getDeedId(), ddto.getPageId())) {
					dbUtility.setAutoCommit(false);
					String sql = DeedDraftSQL.UPDATE_DEED_TXN_DTLS;
					String deedDtls[] = { ddto.getDeedName(), userID, appType, applicationId, ddto.getDeedId() };
					dbUtility.createPreparedStatement(sql);
					flag = dbUtility.executeUpdate(deedDtls);
					if (flag) {
						dbUtility.commit();
						sql = DeedDraftSQL.UPADTE_DEED_CONTENT_DTLS;
						/*String deedContentDtls[] = {
								
								ddto.getDeedContent(),
								userID,
								ddto.getDeedId(),
								ddto.getPageId()
						};*/
						/*dbUtility.createPreparedStatement(sql);
						flag = dbUtility.executeUpdate(deedContentDtls);*/
						Connection conn = dbUtility.getDBConnection();
						//CLOB objClob = conn.createClob();
						//objClob.setString(1, ddto.getDeedContent());
						// CLOB objClob = stringtoClob(ddto.getDeedContent(),conn );
						Clob objClob = getCLOB(ddto.getDeedContent(), conn);
						PreparedStatement pst = conn.prepareStatement(sql);
						logger.debug(sql);
						//  Clob cl=orclConn.createClob();
						//cl=setClob(ddto.getDeedContent() );
						pst.setClob(1, objClob);
						pst.setString(2, userID);
						pst.setString(3, ddto.getDeedId());
						pst.setString(4, ddto.getPageId());
						//flag = pst.execute();
						i = pst.executeUpdate();
						if (i > 0) {
							logger.debug("CLOB UPDATED SUCCESSFULLY");
							flag = true;
						} else {
							logger.debug("CLOB UPDATE FAILED");
							flag = false;
						}
					}
				} else {
					if (ddto.getDeedId() != "" || ddto.getDeedId() != null) {
						String sql = DeedDraftSQL.UPDATE_DEED_TXN_DTLS;
						String deedDtls[] = { ddto.getDeedName(), userID, appType, applicationId, ddto.getDeedId() };
						dbUtility.createPreparedStatement(sql);
						flag = dbUtility.executeUpdate(deedDtls);
						sql = DeedDraftSQL.INSERT_DEED_CONTENT_DTLS;
						String deedContentDtls[] = { ddto.getPageId(), ddto.getDeedId(),
						//ddto.getDeedContent(),
						userID };
						dbUtility.createPreparedStatement(sql);
						flag = dbUtility.executeUpdate(deedContentDtls);
						if (flag) {
							dbUtility.commit();
							Connection conn = dbUtility.getDBConnection();
							sql = DeedDraftSQL.UPDATE_DEED_CONTENT_CLOB;
							logger.debug(sql);
							// CLOB objClob = stringtoClob(ddto.getDeedContent(),conn );
							Clob objClob = getCLOB(ddto.getDeedContent(), conn);
							PreparedStatement pstClob = conn.prepareStatement(sql);
							pstClob.setClob(1, objClob);
							pstClob.setString(2, ddto.getPageId());
							pstClob.setString(3, ddto.getDeedId());
							i = pstClob.executeUpdate();
							logger.debug("CLOB STATUS STRING IS::::::" + objClob.getSubString(1, (int) objClob.length()));
							if (i > 0) {
								logger.debug("CLOB UPDATED SUCCESSFULLY");
								flag = true;
							} else {
								logger.debug("CLOB UPDATE FAILED");
								flag = false;
							}
						}
					} else {
						String sql = DeedDraftSQL.GET_DEED_DRAFT_ID;
						dbUtility.createStatement();
						String deedDraftId = dbUtility.executeQry(sql);
						ddto.setDeedId(deedDraftId);
						dbUtility.setAutoCommit(false);
						sql = DeedDraftSQL.INSERT_DEED_TXN_DTLS;
						String deedDtls[] = { deedDraftId, ddto.getDeedName(), appType, applicationId, userID };
						dbUtility.createPreparedStatement(sql);
						flag = dbUtility.executeUpdate(deedDtls);
						if (flag) {
							dbUtility.commit();
							sql = DeedDraftSQL.INSERT_DEED_CONTENT_DTLS;
							String deedContentDtls[] = { ddto.getPageId(), deedDraftId,
							//ddto.getDeedContent(),
							userID };
							dbUtility.createPreparedStatement(sql);
							flag = dbUtility.executeUpdate(deedContentDtls);
							if (flag) {
								dbUtility.commit();
								Connection conn = dbUtility.getDBConnection();
								sql = DeedDraftSQL.UPDATE_DEED_CONTENT_CLOB;
								logger.debug(sql);
								// CLOB objClob = stringtoClob(ddto.getDeedContent(),conn );
								Clob objClob = getCLOB(ddto.getDeedContent(), conn);
								PreparedStatement pstClob = conn.prepareStatement(sql);
								pstClob.setClob(1, objClob);
								pstClob.setString(2, ddto.getPageId());
								pstClob.setString(3, ddto.getDeedId());
								i = pstClob.executeUpdate();
								logger.debug("CLOB STATUS STRING IS::::::" + objClob.getSubString(1, (int) objClob.length()));
								if (i > 0) {
									logger.debug("CLOB UPDATED SUCCESSFULLY");
									flag = true;
								} else {
									logger.debug("CLOB UPDATE FAILED");
									flag = false;
								}
							}
						}
					}
				}
			} else {
				if (checkDeedRecord(ddto.getDeedId(), ddto.getPageId())) {
					//for deed draft changes saurav
					String sql = DeedDraftSQL.UPDATE_DEED_TXN_DTLS;
					String deedDtls[] = { ddto.getDeedName(), userID, appType, applicationId, ddto.getDeedId() };
					dbUtility.createPreparedStatement(sql);
					flag = dbUtility.executeUpdate(deedDtls);
					if (flag) {
						sql = DeedDraftSQL.UPADTE_DEED_CONTENT_DTLS;
						/*	String deedContentDtls[] = {
									ddto.getDeedContent(),
									userID,
									ddto.getDeedId(),
									ddto.getPageId()
							};
							dbUtility.createPreparedStatement(sql);
							flag = dbUtility.executeUpdate(deedContentDtls);*/
						Connection conn = dbUtility.getDBConnection();
						//CLOB objClob = conn.createClob();
						//objClob.setString(1, ddto.getDeedContent());
						// CLOB objClob = stringtoClob(ddto.getDeedContent(),conn );
						Clob objClob = getCLOB(ddto.getDeedContent(), conn);
						PreparedStatement pst = conn.prepareStatement(sql);
						//  Clob cl=orclConn.createClob();
						//cl=setClob(ddto.getDeedContent() );
						pst.setClob(1, objClob);
						pst.setString(2, userID);
						pst.setString(3, ddto.getDeedId());
						pst.setString(4, ddto.getPageId());
						//flag = pst.execute();
						i = pst.executeUpdate();
						if (i > 0) {
							logger.debug("CLOB UPDATED SUCCESSFULLY");
							flag = true;
						} else {
							logger.debug("CLOB UPDATE FAILED");
							flag = false;
						}
					}
				} else {
					String sql = DeedDraftSQL.UPDATE_DEED_TXN_DTLS;
					String deedDtls[] = { ddto.getDeedName(), userID, appType, applicationId, ddto.getDeedId() };
					dbUtility.createPreparedStatement(sql);
					flag = dbUtility.executeUpdate(deedDtls);
					if (flag) {
						sql = DeedDraftSQL.INSERT_DEED_CONTENT_DTLS;
						String deedContentDtls[] = { ddto.getPageId(), ddto.getDeedId(), userID };
						dbUtility.createPreparedStatement(sql);
						flag = dbUtility.executeUpdate(deedContentDtls);
						if (flag) {
							dbUtility.commit();
							Connection conn = dbUtility.getDBConnection();
							sql = DeedDraftSQL.UPDATE_DEED_CONTENT_CLOB;
							logger.debug(sql);
							// CLOB objClob = stringtoClob(ddto.getDeedContent(),conn );
							Clob objClob = getCLOB(ddto.getDeedContent(), conn);
							PreparedStatement pstClob = conn.prepareStatement(sql);
							pstClob.setClob(1, objClob);
							pstClob.setString(2, ddto.getPageId());
							pstClob.setString(3, ddto.getDeedId());
							i = pstClob.executeUpdate();
							logger.debug("CLOB STATUS STRING IS::::::" + objClob.getSubString(1, (int) objClob.length()));
							if (i > 0) {
								logger.debug("CLOB UPDATED SUCCESSFULLY");
								flag = true;
							} else {
								logger.debug("CLOB UPDATE FAILED");
								flag = false;
							}
						}
					}
				}
				if (flag) {
					logger.debug("INSERTION SUCCESSFULL");
					dbUtility.commit();
				} else {
					logger.debug("INSERTION FAILURE");
					dbUtility.rollback();
				}
			}
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
		} finally {
			dbUtility.closeConnection();
			logger.debug("ENDING OF saveDeedDraftDetails METHOD FLAG STATUS::::::" + flag);
		}
		return flag;
	}

	public boolean checkDeedRecord(String deedDraftId, String pageId) throws Exception {
		String deedContent = "";
		boolean flag = true;
		DBUtility dbUtility = null;
		try {
			String sql = DeedDraftSQL.GET_DEED_RECORD;
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			String deedArr[] = { deedDraftId, pageId };
			deedContent = dbUtility.executeQry(deedArr);
			if (deedContent.equals("0"))
				flag = false;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtility.closeConnection();
		}
		return flag;
	}

	public boolean checkDeedRecordOnNext(String deedDraftId, String pageId) throws Exception {
		String deedContent = "";
		boolean flag = true;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String sql = DeedDraftSQL.GET_DEED_RECORD;
			dbUtility.createPreparedStatement(sql);
			String deedArr[] = { deedDraftId, pageId };
			deedContent = dbUtility.executeQry(deedArr);
			if (deedContent.equals("0"))
				flag = false;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtility.closeConnection();
		}
		return flag;
	}

	public boolean changeStatus(String deedDraftId) throws Exception {
		String deedContent = "";
		boolean flag = true;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement("UPDATE IGRS_DEED_DRAFT_TXN_DTLS SET DEED_DRAFT_STATUS='I' WHERE DEED_DRAFT_ID=?");
			String deedArr[] = { deedDraftId };
			flag = dbUtility.executeUpdate(deedArr);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtility.closeConnection();
		}
		return flag;
	}

	public boolean saveDeedDraftPath(DeedDraftDTO ddto) {
		boolean ret = false;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement("UPDATE IGRS_DEED_DRAFT_TXN_DTLS SET UPLOAD_PATH=?, DEED_DRAFT_STATUS='A' WHERE DEED_DRAFT_ID = ?");
			ret = dbUtility.executeUpdate(new String[] { "D:/Upload/16/" + ddto.getDeedId() + "/deedDraft.pdf", ddto.getDeedId() });
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ret;
	}

	public boolean deleteDeed(String[] deedId) {
		DBUtility dbUtility = null;
		boolean flag = false;
		try {
			try {
				dbUtility = new DBUtility();
				dbUtility.setAutoCommit(false);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String qstnMarks = "(";
			for (int i = 0; i < deedId.length; i++) {
				qstnMarks += "?";
				if (i != deedId.length - 1) {
					qstnMarks += ",";
				}
			}
			qstnMarks += ")";
			String sql = DeedDraftSQL.DELETE_DEED + qstnMarks;
			try {
				dbUtility.createPreparedStatement(sql);
				flag = dbUtility.executeUpdate(deedId);
				if (flag) {
					dbUtility.commit();
				} else {
					dbUtility.rollback();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag;
	}
}
