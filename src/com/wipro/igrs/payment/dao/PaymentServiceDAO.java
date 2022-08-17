package com.wipro.igrs.payment.dao;

/**
 * ===========================================================================
 * File           :   PaymentServiceDAO.java
 * Description    :   Represents the  Payment DAO Class
 * Author         :   Shreeraj Khare
 * Created Date   :   Jan 12, 2017

 * ===========================================================================
 */
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import oracle.jdbc.driver.OracleTypes;

import org.apache.log4j.Logger;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.payment.dto.PaymentServiceDTO;
import com.wipro.igrs.payment.form.PaymentServiceForm;
import com.wipro.igrs.payment.sql.CommonSQL;

public class PaymentServiceDAO {
	DBUtility dbUtil = null;
	String query;
	private Logger logger = (Logger) Logger.getLogger(PaymentServiceDAO.class);
	private CallableStatement clstmt;
	/**
	 * Method : getServiceList Description : returns list of function ids whose
	 * payment flag=Y
	 * 
	 * @param query
	 *            : return Type :ArrayList
	 */
	public ArrayList getServiceList() {
		ArrayList list = null;
		try {
			dbUtil = new DBUtility();
			// query = CommonSQL.CASH_FUNCTIONID_LIST;
			query = CommonSQL.PAYMENT_SERVICE_LIST;
			logger.debug("the query is  in DAO   " + query);
			dbUtil.createStatement();
			list = new ArrayList();
			list = dbUtil.executeQuery(query);
			logger.debug("the values in list is  " + list);
		} catch (Exception x) {
			logger.error("Exception in funcidlist(): " + x);

		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.error("Exception in closing connection" + ex);

			}
		}
		return list;
	}

	public ArrayList getYearRange() {
		ArrayList list = null;
		try {
			dbUtil = new DBUtility();
			// query = CommonSQL.CASH_FUNCTIONID_LIST;
			query = CommonSQL.PAYMENT_YEAR__RANGE_LIST;
			logger.debug("the query is  in DAO   " + query);
			dbUtil.createStatement();
			list = new ArrayList();
			list = dbUtil.executeQuery(query);
			logger.debug("the values in list is  " + list);
		} catch (Exception x) {
			logger.error("Exception in funcidlist(): " + x);

		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.error("Exception in closing connection" + ex);

			}
		}
		return list;
	}

	public ArrayList validateReff(String refID, String serviceID) throws Exception {
		boolean boo = false;
		StringBuilder queryBuilder;
		String srcColumn = "";
		String payFlag;
		String parentKeyCol;
		String payableAmount = null;
		String paymentCol = null;
		String parentSeq = null;
		ArrayList recList = new ArrayList();
		try {
			dbUtil = new DBUtility();
			query = CommonSQL.PAYMENT_SERVICE_MASTER;
			logger.debug("the query is  in DAO validateReff  " + query);
			dbUtil.createPreparedStatement(query);
			ArrayList mainList = dbUtil.executeQuery(new String[]{serviceID});
			if (mainList == null || mainList.isEmpty()) {
				throw new Exception("10003");
			}
			if (mainList.size() > 0) {
				ArrayList list = (ArrayList) mainList.get(0);
				String tableName = list.get(0).toString();
				String colName = list.get(1).toString();
				parentKeyCol = list.get(4).toString();
				paymentCol = list.get(5).toString();
				parentSeq = list.get(6).toString();
				queryBuilder = new StringBuilder("SELECT COUNT(*) FROM " + tableName + " where " + colName + "='" + refID.trim() + "'");
				if (list.get(2) != null) {
					srcColumn = list.get(2).toString();
					queryBuilder.append(" and " + srcColumn);
				}
				if (list.get(3) != null) {
					payFlag = list.get(3).toString();
					queryBuilder.append(" and " + payFlag);
				}

				logger.debug("DYNAMIC QUERY IS:::::::::::" + queryBuilder.toString());
				dbUtil.createStatement();
				String count = dbUtil.executeQry(queryBuilder.toString());

				if (count != null && Integer.parseInt(count) > 0) {
					/*
					 * SELECT TXN_ID,PAYABLE_AMOUNT,PAYMENT_FLAG FROM
					 * IGRS_REG_PAYMENT_DETLS WHERE TXN_ID=( SELECT MAX(TXN_ID)
					 * FROM IGRS_REG_PAYMENT_DETLS WHERE REG_TXN_ID
					 * ='131216000008' AND SOURCE_MOD_FLAG='C' AND
					 * PAYMENT_TYPE_ID=1 GROUP BY PAYABLE_AMOUNT)
					 */
					/*
					 * SELECT PAYMENT_TXN_ID, PAID_AMOUNT, PAYABLE_AMOUNT FROM
					 * IGRS_REG_PAYMENT_DETLS WHERE CREATED_DATE= (SELECT
					 * MAX(CREATED_DATE) FROM IGRS_REG_PAYMENT_DETLS WHERE
					 * REG_TXN_ID='270117012345' ) AND REG_TXN_ID
					 * ='270117012345' AND SOURCE_MOD_FLAG='C' AND
					 * PAYMENT_TYPE_ID=1
					 */

					query = "SELECT PAYMENT_TXN_ID,nvl(PAID_AMOUNT,0),nvl(" + paymentCol + ",0), " + parentKeyCol + " FROM " + tableName + " WHERE CREATED_DATE=" + "(SELECT MAX(CREATED_DATE) FROM " + tableName + " WHERE " + colName + "='" + refID.trim() + "') " + "AND " + colName + "='" + refID.trim() + "' ";

					if (!srcColumn.isEmpty())
						query = query + " and " + srcColumn;

					//query="SELECT "+paymentCol+",max("+parentKeyCol+") FROM "+
					// tableName
					// +" where "+colName+"='"+refID+"' and "+srcColumn+
					// " group by "+paymentCol;
					logger.debug("DYNAMIC QUERY IS:::::::::::" + query);
					dbUtil.createStatement();
					// payableAmount=dbUtil.executeQry(query);
					recList = dbUtil.executeQuery(query);

				} else {
					logger.debug("No Entry FOUND for::::::::" + refID.trim());
					throw new Exception("10001");
				}

			}
		} catch (SQLException sqlE) {
			throw new Exception("10001");
		} catch (Exception e) {
			String msg = e.getMessage();
			if (msg.equalsIgnoreCase("10001")) {
				throw e;
			}
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return recList;

	}

	public String gettype(String userId) {
		DBUtility dbUtil = null;
		String type = "";
		try {
			String arry[] = new String[1];
			arry[0] = userId;
			String SQL = CommonSQL.USER_TYPE_QUERY;

			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(SQL);
			logger.debug("Wipro in IGRSCommon - gettype() " + "after creating preparedstatement");
			type = dbUtil.executeQry(arry);
			logger.debug("Wipro in IGRSCommon - " + "gettype() after excuteQuery");

			logger.debug("found type" + type);

		} catch (Exception x) {
			logger.debug(x);
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return type;
	}
	public ArrayList getdetails(String userId) {
		ArrayList details = new ArrayList();

		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			String param[] = new String[1];

			param[0] = userId;

			String sql = CommonSQL.USER_DETAILS;

			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(sql);
			logger.debug("Wipro in IGRSCommon - getdetails() " + "after creating preparedstatement");
			details = dbUtil.executeQuery(param);

		}

		catch (Exception x) {
			logger.debug(x);
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return details;
	}
	public ArrayList getrudetails(String userId, PaymentServiceDTO objDashBoardDTO1, String txnId, String language) {
		ArrayList details = new ArrayList();
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			String param[] = new String[1];
			String sql = "";
			// param[0] = txnId;
			// param[0] = userId;
			if ("en".equalsIgnoreCase(language)) {
				sql = CommonSQL.USER_RU_DETAILS;
			} else {
				sql = CommonSQL.USER_RU_DETAILS_HI;
			}
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(sql);
			logger.debug("Wipro in IGRSCommon - getrudetails() " + "after creating preparedstatement");
			details = dbUtil.executeQuery(new String[]{userId});
		}

		catch (Exception x) {
			logger.debug(x);
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return details;
	}

	public ArrayList getPaymentDetails(String serviceID) {
		ArrayList list = null;
		try {
			dbUtil = new DBUtility();
			query = CommonSQL.PAYMENT_SERVICE_MASTER;
			logger.debug("the query is  in DAO   " + query);
			dbUtil.createPreparedStatement(query);
			list = new ArrayList();
			list = dbUtil.executeQuery(new String[]{serviceID});
			logger.debug("the values in list is  " + list);
		} catch (Exception x) {
			logger.error("Exception in getPaymentDetails(): " + x);

		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.error("Exception in closing connection" + ex);

			}
		}
		return list;
	}
	/*
	 * public boolean insertPaymentDetls(PaymentServiceForm pForm,ArrayList
	 * payDetls){ boolean boo=false; StringBuilder queryBuilder; try{ dbUtil=new
	 * DBUtility(); query=CommonSQL.INSERT_PAYMENT_SERVICE; ArrayList
	 * list=(ArrayList)payDetls.get(0); String funID=list.get(2).toString();
	 * String refID=pForm.getRefID(); String payable=pForm.getPayableAmt();
	 * String seq=list.get(6).toString(); dbUtil.createPreparedStatement(query);
	 * boo=dbUtil.executeUpdate(new
	 * String[]{funID,refID,payable,pForm.getUserID(),pForm.getServiceID()});
	 * if(boo){ queryBuilder=new StringBuilder("INSERT IN");
	 * query=CommonSQL.INSERT_PAYMENT_SERVICE; boolean boo } } catch (Exception
	 * x) { logger.error("Exception in getPaymentDetails(): " + x);
	 * 
	 * } finally { try { dbUtil.closeConnection(); } catch (Exception ex) {
	 * logger.error("Exception in closing connection" + ex);
	 * 
	 * } } return boo; }
	 */
	public String insertPaymentDetls(PaymentServiceForm pForm) {
		String parentKey = null;

		try {
			dbUtil = new DBUtility();
			clstmt = dbUtil.returnCallableStatement(CommonSQL.PAYMENT_SERVICE_PROCEDURE);
			clstmt.setString(1, pForm.getServiceID());
			clstmt.setString(2, pForm.getRefID());
			clstmt.setString(3, pForm.getPayableAmt());
			clstmt.setString(4, pForm.getUserID());
			clstmt.registerOutParameter(5, OracleTypes.INTEGER);
			clstmt.registerOutParameter(6, OracleTypes.VARCHAR);
			if (!clstmt.execute()) {
				int i = clstmt.getInt(5);
				boolean boo = (i == 1) ? true : false;
				if (boo)
					parentKey = clstmt.getString(6);
			}
		} catch (Exception x) {
			logger.error("Exception in getPaymentDetails(): " + x);

		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.error("Exception in closing connection" + ex);

			}
		}
		return parentKey;
	}
	public ArrayList getPaymentRecord(String referenceID, String _funcId) {
		ArrayList list = null;
		try {
			dbUtil = new DBUtility();
			query = CommonSQL.PAYMENT_RECORD;
			logger.debug("the query is  in DAO   " + query);
			dbUtil.createPreparedStatement(query);
			list = new ArrayList();
			list = dbUtil.executeQuery(new String[]{referenceID.trim(), _funcId.trim()});
			if (list.size() <= 0) {
				list = dbUtil.executeQuery(new String[]{"0" + referenceID.trim(), _funcId.trim()});
			}
			logger.debug("the values in list is  " + list);
		} catch (Exception x) {
			logger.error("Exception in getPaymentRecord(): " + x);

		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.error("Exception in closing connection" + ex);

			}
		}
		return list;
	}
	public boolean insertPayable(String referenceID, String _funcId, String payAmt, String userID) throws Exception {
		String parentKey = null;
		boolean boo = false;
		if (payAmt.isEmpty() || "0".equalsIgnoreCase(payAmt.trim())) {
			throw new Exception("10003");
		}

		try {
			dbUtil = new DBUtility();
			String _sql = CommonSQL.SELECT_SERVICE_ID;
			dbUtil.createPreparedStatement(_sql);
			logger.debug("Wipro in IGRSCommon - gettype() " + "after creating preparedstatement");
			String serviceID = dbUtil.executeQry(new String[]{_funcId});
			if (!serviceID.isEmpty()) {
				clstmt = dbUtil.returnCallableStatement(CommonSQL.PAYMENT_SERVICE_PROCEDURE);
				clstmt.setString(1, serviceID);
				clstmt.setString(2, referenceID.trim());
				clstmt.setString(3, payAmt);
				clstmt.setString(4, userID);
				clstmt.registerOutParameter(5, OracleTypes.INTEGER);
				clstmt.registerOutParameter(6, OracleTypes.VARCHAR);

				if (!clstmt.execute()) {
					int i = clstmt.getInt(5);
					boo = (i == 1) ? true : false;

				}
			}
		} catch (Exception x) {
			logger.error("Exception in getPaymentDetails(): " + x);
			x.printStackTrace();
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.error("Exception in closing connection" + ex);

			}
		}
		return boo;
	}

	/**
	 * Method : getOfflineServiceList Description : returns list of function ids
	 * whose payment flag=Y
	 * 
	 * @param query
	 *            : return Type :ArrayList
	 */
	public ArrayList getOfflineServiceList() {
		ArrayList list = null;
		try {
			dbUtil = new DBUtility();
			// query = CommonSQL.CASH_FUNCTIONID_LIST;
			query = CommonSQL.OFFLINE_PAYMENT_SERVICE;
			logger.debug("the query is  in DAO   " + query);
			dbUtil.createStatement();
			list = new ArrayList();
			list = dbUtil.executeQuery(query);
			logger.debug("the values in list is  " + list);
		} catch (Exception x) {
			logger.error("Exception in funcidlist(): " + x);

		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.error("Exception in closing connection" + ex);

			}
		}
		return list;
	}

	public String insertOfflinePaymentDetls(PaymentServiceForm pForm, String userID) {
		String reffID = null;
		try {
			dbUtil = new DBUtility();
			// query = CommonSQL.CASH_FUNCTIONID_LIST;
			query = CommonSQL.OFFLINE_PAYMENT_SEQUENCE;
			logger.debug("the query is  in DAO   " + query);
			dbUtil.createStatement();
			StringBuilder sb = new StringBuilder();
			ArrayList list = dbUtil.executeQuery(query);
			if (list == null || list.isEmpty())
				throw new Exception("10001");
			ArrayList<String> subList = (ArrayList) list.get(0);
			String[] arr = new String[subList.size()];
			arr = subList.toArray(arr);
			// Object arr[]=list.toArray();

			sb.append("34");
			sb.append(arr[0]);
			if (arr[1].trim().length() == 1)
				sb.append("000000" + arr[1]);
			else if (arr[1].trim().length() == 2)
				sb.append("00000" + arr[1]);
			else if (arr[1].trim().length() == 3)
				sb.append("0000" + arr[1]);
			else if (arr[1].trim().length() == 4)
				sb.append("000" + arr[1]);
			else if (arr[1].trim().length() == 5)
				sb.append("00" + arr[1]);
			else if (arr[1].trim().length() == 6)
				sb.append("0" + arr[1]);
			else
				sb.append(arr[1]);
			reffID = sb.toString();

			dbUtil.createPreparedStatement(CommonSQL.OFFLINE_PAYMENT_INSERT);
			boolean boo = dbUtil.executeUpdate(new String[]{reffID, pForm.getOffServiceID(), pForm.getUserValue(), pForm.getFinalFee(), userID, pForm.getCaseNo(), pForm.getPartyName(), pForm.getfYear(), pForm.gettYear()});
			if (!boo)
				throw new Exception("10001");
			logger.debug("the values in list is  " + list);
		} catch (Exception x) {
			logger.error("Exception in funcidlist(): " + x);

		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.error("Exception in closing connection" + ex);

			}
		}
		return reffID;
	}

	public ArrayList validateReffView(String refID, String serviceID, String userID) throws Exception {
		boolean boo = false;
		StringBuilder queryBuilder;
		String srcColumn = "";
		String payFlag;
		String parentKeyCol;
		String payableAmount = null;
		String paymentCol = null;
		String parentSeq = null;
		String sql = null;
		String str = null;
		ArrayList recList = new ArrayList();
		try {
			dbUtil = new DBUtility();
			query = CommonSQL.PAYMENT_SERVICE_MASTER;
			logger.debug("the query is  in DAO validateReff  " + query);
			dbUtil.createPreparedStatement(query);
			ArrayList mainList = dbUtil.executeQuery(new String[]{serviceID});

			if (mainList.size() > 0) {
				ArrayList list = (ArrayList) mainList.get(0);
				String tableName = list.get(0).toString();
				String colName = list.get(1).toString();
				parentKeyCol = list.get(4).toString();
				paymentCol = list.get(5).toString();
				parentSeq = list.get(6).toString();
				queryBuilder = new StringBuilder("SELECT COUNT(*) FROM " + tableName + " where " + colName + "='" + refID + "'");
				if (list.get(2) != null) {
					srcColumn = list.get(2).toString();
					queryBuilder.append(" and " + srcColumn);
				}
				if (list.get(3) != null) {
					payFlag = list.get(3).toString();
					queryBuilder.append(" and " + payFlag);
				}

				logger.debug("DYNAMIC QUERY IS:::::::::::" + queryBuilder.toString());
				dbUtil.createStatement();
				String count = dbUtil.executeQry(queryBuilder.toString());
				// paymnt detls not reflecting in view recipt by santosh
				// START | changes to provide access of SP/RU payment receipt to
				// internal user by santosh
				sql = CommonSQL.GET_USER_TYPE;

				dbUtil.createPreparedStatement(sql);

				str = dbUtil.executeQry(new String[]{userID});

				if (count != null && Integer.parseInt(count) > 0 && null != str && !"".equalsIgnoreCase(str) && "I".equalsIgnoreCase(str)) {
					query = "SELECT A.PAYMENT_TXN_ID, NVL(A.PAID_AMOUNT,0), NVL( A." + paymentCol + ",0), " + " A." + parentKeyCol + ",c.first_name||' '||C.MIDDLE_NAME||' '||c.last_name,SERVICE_NAME,  SERVICE_NAME_H," + " to_char(A.UPDATE_DATE,'dd-mm-yyyy hh:mi am'),f.PAYMENT_MODE_H,f.PAYMENT_MODE " + " FROM " + tableName + " A,IGRS_PAYMENT_SERVICE_LOG B,IGRS_USER_REG_DETAILS C,IGRS_SERVICE_MASTER d,IGRS_PAYMENT_TXN_DETAILS e, " + " IGRS_PAYMENT_MODE_MASTER f " + " WHERE " + colName + "='" + refID + "' AND A.CREATED_BY=C.USER_ID and A.PAYMENT_TXN_ID=E.TRANSACTION_ID and f.PAYMENT_MODE_ID=e.PAYMENT_TYPE_ID " + " AND B.REFERENCE_ID=" + colName + " AND PARENT_KEY_COL=A." + parentKeyCol + " and d.service_id=b.service_id " + " AND A.PAYMENT_TXN_ID IS NOT NULL AND A.PAYMENT_FLAG='P' and d.service_id='" + serviceID + "'";

					logger.debug("DYNAMIC QUERY IS:::::::::::" + query);
					dbUtil.createStatement();
					recList = dbUtil.executeQuery(query);
				}
				// END | changes to provide access of SP/RU payment receipt to
				// internal user by santosh
				else if (count != null && Integer.parseInt(count) > 0 && !"I".equalsIgnoreCase(str)) {
					query = "SELECT A.PAYMENT_TXN_ID, NVL(A.PAID_AMOUNT,0), NVL( A." + paymentCol + ",0), " + " A." + parentKeyCol + ",c.first_name||' '||C.MIDDLE_NAME||' '||c.last_name,SERVICE_NAME,  SERVICE_NAME_H," + " to_char(A.UPDATE_DATE,'dd-mm-yyyy hh:mi am'),f.PAYMENT_MODE_H,f.PAYMENT_MODE " + " FROM " + tableName + " A,IGRS_PAYMENT_SERVICE_LOG B,IGRS_USER_REG_DETAILS C,IGRS_SERVICE_MASTER d,IGRS_PAYMENT_TXN_DETAILS e, " + " IGRS_PAYMENT_MODE_MASTER f " + " WHERE " + colName + "='" + refID + "' AND A.CREATED_BY=C.USER_ID and A.PAYMENT_TXN_ID=E.TRANSACTION_ID and f.PAYMENT_MODE_ID=e.PAYMENT_TYPE_ID " + " AND B.REFERENCE_ID=" + colName + " AND PARENT_KEY_COL=A." + parentKeyCol + " and d.service_id=b.service_id " + " AND A.PAYMENT_TXN_ID IS NOT NULL AND A.PAYMENT_FLAG='P' and d.service_id='" + serviceID + "' and A.CREATED_BY='" + userID + "'";

					logger.debug("DYNAMIC QUERY IS:::::::::::" + query);
					dbUtil.createStatement();
					recList = dbUtil.executeQuery(query);
					recList.add(list.get(9).toString());
				} else {
					logger.debug("No Entry FOUND for::::::::" + refID);
					throw new Exception("10001");
				}

			}
		} catch (SQLException sqlE) {
			throw new Exception("10001");
		} catch (Exception e) {
			String msg = e.getMessage();
			if (msg.equalsIgnoreCase("10001")) {
				throw e;
			}
			e.printStackTrace();
		}

		return recList;

	}

	public String getNumberOfDays() {
		String day = null;
		String durationSQL = CommonSQL.GET_REPORT_DURATION;

		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			day = dbUtil.executeQry(durationSQL);

		} catch (Exception e) {
			logger.debug("Exception");
			// TODO: handle exception
		} finally {
			try {

				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return day;
	}

	/**
	 * Method : getOfflinePaymentList Description : returns list of
	 * getOfflinePaymentList
	 * 
	 * @param query
	 *            : return Type :ArrayList
	 * @throws Exception
	 */
	public ArrayList getOfflinePaymentList(String serviceID, String fromDate, String toDate, String userID, String officeId) throws Exception {
		ArrayList list = null;

		StringBuilder sb = new StringBuilder(CommonSQL.OFFLINE_OUTSIDE_SAMPADA);
		String sql = null;
		String str = null;
		dbUtil = new DBUtility();
		// query = CommonSQL.CASH_FUNCTIONID_LIST;
		String[] arr;
		if (serviceID != null && !serviceID.isEmpty()) {
			arr = new String[2];
			sb.append(CommonSQL.OFFLINE_OUTSIDE_SAMPADA_SERVICE);
			arr[0] = serviceID;
			arr[1] = userID;
		} else {
			if (!validateReport(fromDate, toDate))
				throw new Exception("10004");
			arr = new String[3];
			sb.append(CommonSQL.OFFLINE_OUTSIDE_SAMPADA_DURATION);
			arr[0] = fromDate;
			arr[1] = toDate;
			arr[2] = userID;
		}
		try {
			// START | changes to provide access of SP/RU payment receipt to
			// internal user by santosh
			sql = CommonSQL.GET_USER_TYPE;

			dbUtil.createPreparedStatement(sql);

			str = dbUtil.executeQry(new String[]{userID});

			if (null != str && !"".equalsIgnoreCase(str) && "I".equalsIgnoreCase(str)) {

				if (serviceID != null && !serviceID.isEmpty()) {
					arr = new String[2];
					arr[0] = serviceID;
					arr[1] = officeId;
					sb.append(CommonSQL.OFFLINE_OUTSIDE_SAMPADA_ORDERBY);
				} else {
					arr = new String[3];
					arr[0] = fromDate;
					arr[1] = toDate;
					arr[2] = officeId;
					sb.append(CommonSQL.OFFLINE_OUTSIDE_SAMPADA_ORDERBY);
				}
			} else {
				sb.append(CommonSQL.OFFLINE_OUTSIDE_SAMPADA_ORDER);
			}
			// END | changes to provide access of SP/RU payment receipt to
			// internal user by santosh
			logger.debug("the query is  in DAO   " + sb.toString());
			dbUtil.createPreparedStatement(sb.toString());
			list = new ArrayList();
			list = dbUtil.executeQuery(arr);
			logger.debug("the values in list is  " + list);
		} catch (Exception x) {

			logger.error("Exception in getOfflinePaymentList(): " + x);

		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.error("Exception in closing connection" + ex);

			}
		}
		return list;
	}

	public boolean validateReport(String fromDate, String toDate) {
		boolean boo = false;
		String durationSQL = CommonSQL.GET_REPORT_DURATION;
		String dateDiffSQL = CommonSQL.REPORT_DATE_DIFF;

		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			String day = dbUtil.executeQry(durationSQL);

			dbUtil.createPreparedStatement(dateDiffSQL);
			String dayDiff = dbUtil.executeQry(new String[]{toDate, fromDate});
			if (Integer.parseInt(day) >= Integer.parseInt(dayDiff))
				boo = true;
			else
				boo = false;

		} catch (Exception e) {
			logger.debug("Exception");
			// TODO: handle exception
		} finally {
			try {

				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return boo;
	}

	public ArrayList validateConsumedReff(String refID, String serviceID) {

		ArrayList list = null;
		try {
			dbUtil = new DBUtility();
			// query = CommonSQL.CASH_FUNCTIONID_LIST;
			query = CommonSQL.OFFLINE_CONSUMPTION_VALIDATE;
			logger.debug("the query is  in DAO   " + query);
			dbUtil.createPreparedStatement(query);
			list = new ArrayList();
			list = dbUtil.executeQuery(new String[]{refID.trim(), serviceID});
			logger.debug("the values in list is  " + list);
		} catch (Exception x) {
			logger.error("Exception in funcidlist(): " + x);

		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.error("Exception in closing connection" + ex);

			}
		}
		return list;

	}

	public boolean updateConsume(String payID, String refID, String empID, String offcID) {
		boolean boo = false;
		try {
			dbUtil = new DBUtility();
			if (refID != null && "".equalsIgnoreCase(refID.trim()))
				refID = "NA";
			query = CommonSQL.UPDATE_CONSUME_REFID;
			logger.debug("the query is  in DAO   " + query);
			dbUtil.createPreparedStatement(query);
			boo = dbUtil.executeUpdate(new String[]{empID, offcID, refID.trim(), payID.trim()});
		} catch (Exception x) {
			logger.error("Exception in funcidlist(): " + x);

		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.error("Exception in closing connection" + ex);

			}
		}
		return boo;
	}

	public String validateEfilePayment(String referenceId, String serviceId, String userId) {
		String parentKey = "";
		String sql = CommonSQL.GET_PARENT_KEY_EFILE;
		try {
			dbUtil = new DBUtility();
			String[] param = {referenceId};
			dbUtil.createPreparedStatement(sql);
			parentKey = dbUtil.executeQry(param);
		} catch (Exception x) {
			logger.error("Exception in validateEfilePayment(): " + x);

		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.error("Exception in closing connection" + ex);

			}
		}
		return parentKey;
	}
	public boolean updateEfilePayment(String referenceId, String serviceId, String userId, String parentKey) {
		boolean boo = false;
		String sql = CommonSQL.EFILE_PAYMENT_CHECK;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(sql);
			String[] param = {parentKey, userId, referenceId};
			String resp = dbUtil.executeQry(param);
			if (null != resp) {
				boo = true;
			}
			if (boo) {
				sql = CommonSQL.UPDATE_PAYMENT_EFILE;
				dbUtil.createPreparedStatement(sql);
				String[] param1 = {param[2]};
				boo = dbUtil.executeUpdate(param1);
				if (boo) {
					sql = CommonSQL.CHANGE_EFILE_STATUS;
					dbUtil.createPreparedStatement(sql);
					String arr1[] = new String[3];
					arr1[0] = "COMPLETE";
					arr1[1] = "N";
					arr1[2] = referenceId;
					boo = dbUtil.executeUpdate(arr1);
				}
			}
		} catch (Exception x) {
			logger.error("Exception in updateEfilePayment(): " + x);

		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.error("Exception in closing connection" + ex);

			}
		}

		return boo;
	}
	
	public ArrayList getDistrictList() {
		ArrayList list = null;
		try {
			dbUtil = new DBUtility();
			// query = CommonSQL.CASH_FUNCTIONID_LIST;
			query = CommonSQL.SELECT_DISTRICT_LIST;
			logger.debug("the query is  in DAO   " + query);
			dbUtil.createStatement();
			list = new ArrayList();
			list = dbUtil.executeQuery(query);
			logger.debug("the values in list is  " + list);
		} catch (Exception x) {
			logger.error("Exception in funcidlist(): " + x);

		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.error("Exception in closing connection" + ex);

			}
		}
		return list;
	}
	
	
}
