package com.wipro.igrs.db;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import oracle.jdbc.OracleResultSet;
import oracle.jdbc.driver.OracleCallableStatement;
import oracle.jdbc.driver.OracleTypes;
import oracle.sql.BLOB;

import org.apache.log4j.Logger;
import org.apache.struts.upload.FormFile;

import com.wipro.igrs.CitizenFeedback.dto.CitizenFeedbackDTO;
import com.wipro.igrs.UserRegistration.sql.CommonSQL;
import com.wipro.igrs.auditinspection.dto.AGMPReportDetailsDTO;
import com.wipro.igrs.caveats.dto.CaveatsDTO;
import com.wipro.igrs.caveats.dto.OTTDetailDTO;
import com.wipro.igrs.common.CommonConstant;
import com.wipro.igrs.common.CustomArrayList;
import com.wipro.igrs.copyissuance.form.CertifiedActionForm;
import com.wipro.igrs.hrpayroll.payroll.dto.PayrollDTO;
import com.wipro.igrs.loanadvance.dto.LoanDTO;
import com.wipro.igrs.loanadvance.dto.LoanadvanceDTO;
import com.wipro.igrs.reginit.constant.RegInitConstant;
import com.wipro.igrs.revenueManagement.dto.RevMgtDTO;
import com.wipro.igrs.util.CommonUtil;

/**
 * ===========================================================================
 * File           :   DBUtility.java
 * Description    :   Represents the Database Utility Class

 * Author         :   Madan  Mohan
 * Created Date   :   Nov 26, 2007

 * ===========================================================================
 */
/**
 * ===========================================================================
 * File           :   DBUtility.java
 * Description    :   Represents the Database Utility Class

 * Author         :   Madan  Mohan
 * Created Date   :   Nov 26, 2007

 * ===========================================================================
 */
/**
 * ===========================================================================
 * File : DBUtility.java Description : Represents the Database Utility Class
 * Modified By : Roopam Mehta Modified Date : Oct 10, 2012
 * ===========================================================================
 */
public class DBUtility {
	Statement st = null;
	PreparedStatement pst = null;
	ResultSet rst = null;
	CallableStatement clstmt = null;
	OracleCallableStatement oclstmt = null;
	Connection conn = null;
	private CallableStatement callstmt;
	private static Logger logger = Logger.getLogger(DBUtility.class);

	/**
	 * Constructor : DBUtility Description : Default Constructor for DBUtility
	 * Class
	 * 
	 * @throws : TKMLException
	 */
	public DBUtility(String s) {}

	public DBUtility() throws Exception {
		conn = ConnectionPool.getInstance().getConnection();
	}

	/**
	 * Method : createStatement Description : Method to Statement Object
	 * 
	 * @throws : Exception
	 */
	@Deprecated public void createStatement() throws Exception {
		logger.debug("DBUtility - createStatement()");
		try {
			//This makes the resultset scrollable
			st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		} catch (SQLException sqle) {
			logger.error(sqle.getMessage(), sqle);
			throw sqle;
		}
	}

	/**
	 * Method : createPreparedStatement Description : Creates a
	 * PreparedStatement object
	 * 
	 * @param query : String
	 * @throws : Exception
	 */
	@Deprecated public void createPreparedStatement(String query) throws Exception {
		try {
			logger.debug("DBUtility - createPreparedStatement(String query) = " + query);
			System.out.println(query);
			pst = conn.prepareStatement(query);
		} catch (SQLException sqle) {
			logger.error(sqle.getMessage(), sqle);
			throw sqle;
		}
	}

	/**
	 * Method : returnPreparedStatement Description : Creates a
	 * PreparedStatement object for Doucment UpLoad
	 * 
	 * @param query : String
	 * @throws : Exception
	 */
	@Deprecated public PreparedStatement returnPreparedStatement(String SQL) throws Exception {
		try {
			pst = conn.prepareStatement(SQL);
		} catch (SQLException sqle) {
			logger.error(sqle.getMessage(), sqle);
			throw sqle;
		}
		return pst;
	}

	/**
	 * Method : createPreparedStatement Description : Creates a
	 * PreparedStatement object
	 * 
	 * @param query : String
	 * @throws : Exception
	 */
	@Deprecated public CallableStatement createCallableStatement(String procedure) throws Exception {
		try {
			logger.debug("DBUtility - createCallableStatement(String procedure) = " + procedure);
			clstmt = conn.prepareCall(procedure);
		} catch (SQLException sqle) {
			logger.error(sqle.getMessage(), sqle);
			throw sqle;
		}
		return clstmt;
	}

	/**
	 * Method : createPreparedStatement Description : Creates a
	 * PreparedStatement object
	 * 
	 * @param query : String
	 * @throws : Exception
	 */
	@Deprecated public void createOracleCallableStatement(String procedure) throws Exception {
		try {
			logger.debug("DBUtility - before createOracleCallableStatement(String procedure) = " + procedure);
			oclstmt = (OracleCallableStatement) conn.prepareCall(procedure);
			logger.debug("DBUtility - after createOracleCallableStatement(String procedure) = " + procedure + ":" + conn);
			logger.debug("DBUtility - after createOracleCallableStatement(String procedure) = " + oclstmt);
		} catch (SQLException sqle) {
			logger.error(sqle.getMessage(), sqle);
			throw sqle;
		}
	}

	/**
	 * Method : executeQuery Description : Method to execute simple select
	 * queries
	 * 
	 * @param query : String
	 * @return list : ArrayList
	 * @throws : Exception
	 */
	@Deprecated public ArrayList executeQuery(String query) throws Exception {
		logger.debug("DBUtility - executeQuery(String query)" + query);
		ArrayList list = new ArrayList();
		try {
			rst = st.executeQuery(query);
			ResultSetMetaData rsmd = rst.getMetaData();
			int col_count = rsmd.getColumnCount();
			while (rst.next()) {
				ArrayList row_list = new ArrayList();
				for (int i = 1; i <= col_count; i++) {
					String temp = rst.getString(i);
					if (rst.wasNull()) {
						temp = "";
					}
					row_list.add(temp);
				}
				list.add(row_list);
			}
		} catch (SQLException sqle) {
			logger.error(sqle.getMessage(), sqle);
			throw sqle;
		} catch (NumberFormatException nfe) {
			logger.error(nfe.getMessage(), nfe);
			throw nfe;
		} catch (IllegalArgumentException iae) {
			logger.error(iae.getMessage(), iae);
			throw iae;
		}
		list.trimToSize();
		return list;
	}

	/**
	 * Method : executeQuery Description : Execute a query with
	 * PreparedStatement
	 * 
	 * @param arr : String []
	 * @return list : ArrayList
	 * @throws : Exception
	 */
	@Deprecated public ArrayList executeQuery(String[] arr) throws Exception {
		logger.debug("DBUtility - executeQuery()");
		ArrayList list = new ArrayList();
		try {
			for (int i = 0; i < arr.length; i++) {
				logger.debug("Wipro : arr[" + i + "] = " + arr[i]);
				pst.setString(i + 1, arr[i]);
			}
			rst = pst.executeQuery();
			logger.debug("Wipro in DBUtility - executeQuery() after pst.executeQuery()");
			ResultSetMetaData rsmd = rst.getMetaData();
			int col_count = rsmd.getColumnCount();
			while (rst.next()) {
				ArrayList row_list = new ArrayList();
				for (int i = 1; i <= col_count; i++) {
					//logger.debug("index for reg init----> "+i);
					row_list.add(rst.getString(i));
				} //for
				list.add(row_list);
			} //while
			pst.clearParameters();
		} catch (SQLException sqle) {
			logger.error(sqle.getMessage(), sqle);
			throw sqle;
		} catch (NumberFormatException nfe) {
			logger.error(nfe.getMessage(), nfe);
			throw nfe;
		} catch (IllegalArgumentException iae) {
			logger.error(iae.getMessage(), iae);
			throw iae;
		}
		list.trimToSize();
		return list;
	}

	/**
	 * Method : executeQuery Description : Execute a query with
	 * PreparedStatement
	 * 
	 * @param arr : String []
	 * @return list : ArrayList
	 * @throws : Exception
	 */
	@Deprecated public CustomArrayList executeQueryCustomArrayList(String[] arr) throws Exception {
		logger.debug("DBUtility - executeQueryCustomArrayList()");
		CustomArrayList list = new CustomArrayList();
		try {
			for (int i = 0; i < arr.length; i++) {
				logger.debug("Wipro : arr[" + i + "] = " + arr[i]);
				pst.setString(i + 1, arr[i]);
			}
			rst = pst.executeQuery();
			logger.debug("Wipro in DBUtility - executeQuery() after pst.executeQuery()");
			ResultSetMetaData rsmd = rst.getMetaData();
			int col_count = rsmd.getColumnCount();
			int count = 0;
			while (rst.next() && count == 0) {
				CustomArrayList row_list = new CustomArrayList();
				for (int i = 1; i <= col_count; i++) {
					row_list.add(rst.getString(i));
				} //for
				list.add(row_list);
				count++;
			} //while
			pst.clearParameters();
		} catch (SQLException sqle) {
			logger.error(sqle.getMessage(), sqle);
			throw sqle;
		} catch (NumberFormatException nfe) {
			logger.error(nfe.getMessage(), nfe);
			throw nfe;
		} catch (IllegalArgumentException iae) {
			logger.error(iae.getMessage(), iae);
			throw iae;
		}
		list.trimToSize();
		return list;
	}

	/**
	 * Method : executeCallableQuery Description : Execute a query with
	 * CallableStatement
	 * 
	 * @param arr : String []
	 * @return list : ArrayList
	 * @throws : Exception
	 */
	@Deprecated public String[] authenticateUser(String userId, String pwd, String noattempt, String roleID) throws Exception {
		logger.debug("DBUtility - authenticateUser()");
		String status[] = new String[3];
		try {
			clstmt.setString(1, userId);
			clstmt.setString(2, pwd);
			clstmt.setInt(3, Integer.parseInt(noattempt));
			clstmt.registerOutParameter(4, Types.VARCHAR);
			//clstmt.setString(5, roleID);
			clstmt.registerOutParameter(5, Types.VARCHAR);
			clstmt.registerOutParameter(6, Types.VARCHAR);
			clstmt.registerOutParameter(7, Types.VARCHAR);
			clstmt.execute();
			status[0] = clstmt.getString(4);
			status[1] = clstmt.getString(5);
			status[2] = clstmt.getString(6);
			logger.debug("Type:-" + status);
		} catch (SQLException sqle) {
			logger.error(sqle.getMessage(), sqle);
			throw sqle;
		} catch (NumberFormatException nfe) {
			logger.error(nfe.getMessage(), nfe);
			throw nfe;
		} catch (IllegalArgumentException iae) {
			logger.error(iae.getMessage(), iae);
			throw iae;
		}
		return status;
	}

	/**
	 * Method : executeUpdate Description : execute update,insert,delete query
	 * using prepared statement
	 * 
	 * @param arr : String[]
	 * @return boolean
	 * @throws : Exception
	 */
	@Deprecated public boolean executeUpdate(String[] arr) throws Exception {
		int j = 0;
		try {
			for (int i = 0; i < arr.length; i++) {
				logger.debug("Wipro : arr[" + i + "] = " + arr[i]);
				System.out.println(i+" -- "+arr[i]);
				pst.setString(i + 1, arr[i]);
			}
			logger.debug("Wipro : before execute update");
			try {
				j = pst.executeUpdate();
			} catch (StringIndexOutOfBoundsException e) {
				logger.error(e.getMessage(), e);
			}
			logger.debug("after execute update row cownt is " + j);
			System.out.println("after execute update row cownt is " + j);
			pst.clearParameters();
		} catch (SQLException sqle) {
			logger.error(sqle.getMessage(), sqle);
			throw sqle;
		} catch (NumberFormatException nfe) {
			logger.error(nfe.getMessage(), nfe);
			throw nfe;
		} catch (IllegalArgumentException iae) {
			logger.error(iae.getMessage(), iae);
			throw iae;
		} catch (StringIndexOutOfBoundsException sioobe) {
			logger.error(sioobe.getMessage(), sioobe);
			throw sioobe;
		}
		if (j == 0)
			return false;
		return true;
	}

	/**
	 * Method : executeUpdate Description : execute update,insert,delete query
	 * using prepared statement
	 * 
	 * @param arr : String
	 * @return boolean
	 * @throws : Exception
	 */
	@Deprecated public boolean executeUpdate(String sql) throws Exception {
		int j = 0;
		try {
			j = st.executeUpdate(sql);
			logger.debug("Wipro : after execute update");
			//pst.clearParameters();
		} catch (SQLException sqle) {
			logger.error(sqle.getMessage(), sqle);
			throw sqle;
		} catch (NumberFormatException nfe) {
			logger.error(nfe.getMessage(), nfe);
			throw nfe;
		} catch (IllegalArgumentException iae) {
			logger.error(iae.getMessage(), iae);
			throw iae;
		} catch (StringIndexOutOfBoundsException sioobe) {
			logger.error(sioobe.getMessage(), sioobe);
			throw sioobe;
		}
		if (j == 0)
			return false;
		return true;
	}

	@Deprecated public boolean executeUpdateDouble(double d, String str) throws Exception {
		int j = 0;
		try {
			pst.setDouble(1, d);
			pst.setString(2, str);
			j = pst.executeUpdate();
			pst.clearParameters();
		} catch (SQLException sqle) {
			logger.error(sqle.getMessage(), sqle);
			throw sqle;
		}
		if (j == 0)
			return false;
		return true;
	}

	@Deprecated public boolean executeUpdateTrans(String transactionID) throws Exception {
		int j = 0;
		try {
			pst.setString(1, transactionID);
			j = pst.executeUpdate();
			pst.clearParameters();
		} catch (SQLException sqle) {
			logger.error(sqle.getMessage(), sqle);
			throw sqle;
		}
		if (j == 0)
			return false;
		return true;
	}

	@Deprecated public boolean checkStatus(String bal) throws Exception {
		int j = 0;
		try {
			pst.setString(1, bal);
			j = pst.executeUpdate();
			pst.clearParameters();
		} catch (SQLException sqle) {
			logger.error(sqle.getMessage(), sqle);
			throw sqle;
		}
		if (j == 0)
			return false;
		return true;
	}

	/**
	 * Method : executeQry Description : Execute a query with PreparedStatement
	 * 
	 * @param arr : String []
	 * @return record : String
	 * @throws : Exception
	 */
	@Deprecated public String executeQry(String[] arr) throws Exception {
		logger.debug("DBUtility - executeQry(String arr[])");
		String record = "";
		try {
			for (int i = 0; i < arr.length; i++) {
				pst.setString(i + 1, arr[i]);
				logger.debug("Wipro : arr[" + i + "] = " + arr[i]);
			}
			rst = pst.executeQuery();
			while (rst.next()) {
				String data = rst.getString(1);
				if (rst.wasNull()) {
					data = "";
				}
				record = data;
			} //while
			pst.clearParameters();
		} catch (SQLException sqle) {
			logger.error(sqle.getMessage(), sqle);
			throw sqle;
		} catch (NumberFormatException nfe) {
			logger.error(nfe.getMessage(), nfe);
			throw nfe;
		} catch (IllegalArgumentException iae) {
			logger.error(iae.getMessage(), iae);
			throw iae;
		}
		return record;
	}

	/**
	 * Method : executeQry Description : Execute a query with Statement
	 * 
	 * @param arr : String
	 * @return record : String
	 * @throws : Exception
	 */
	@Deprecated public String executeQry(String query) throws Exception {
		String record = "";
		try {
			rst = st.executeQuery(query);
			while (rst.next()) {
				String data = rst.getString(1);
				if (rst.wasNull()) {
					data = "";
				}
				record = data;
			} //while
		} catch (SQLException sqle) {
			logger.error(sqle.getMessage(), sqle);
			throw sqle;
		} catch (NumberFormatException nfe) {
			logger.error(nfe.getMessage(), nfe);
			throw nfe;
		} catch (IllegalArgumentException iae) {
			logger.error(iae.getMessage(), iae);
			throw iae;
		}
		return record;
	}

	/**
	 * Method : setAutoCommit Description : method to enable or disable
	 * autocommit mode of connection oblect
	 * 
	 * @param flag : boolen
	 * @throws : Exception
	 */
	@Deprecated public void setAutoCommit(boolean flag) throws Exception {
		try {
			conn.setAutoCommit(flag);
		} catch (SQLException sqle) {
			logger.error(sqle.getMessage(), sqle);
			throw sqle;
		}
	}

	/**
	 * Method : commit Description : Commits a Transaction
	 * 
	 * @throws : Exception
	 */
	@Deprecated public void commit() throws Exception {
		logger.debug("DBUtility - commit()");
		try {
			conn.commit();
		} catch (SQLException sqle) {
			logger.debug(sqle.getMessage(), sqle);
			throw sqle;
		}
	}

	/**
	 * Method : rollback Description : Rollback a Transaction
	 * 
	 * @throws : Exception
	 */
	@Deprecated public void rollback() throws Exception {
		logger.debug("DBUtility - rollback()");
		try {
			conn.rollback();
		} catch (SQLException sqle) {
			logger.debug(sqle.getMessage(), sqle);
			throw sqle;
		}
	}

	/**
	 * Method : closeConnection Description : Closes a Connection
	 * 
	 * @throws : Exception
	 */
	public void closeConnection() throws Exception {
		logger.debug("Start in closeConnection() method");
		closeResources();
		logger.debug("After closeResources() method" + conn);
		ConnectionPool.getInstance().closeConnection(conn);
		logger.debug("End in closeConnection() method");
	}

	private void closeResources() {
		logger.debug("Start in closeResources() method");
		try {
			if (rst != null) {
				rst.close();
			}
		} catch (Exception e) {
			logger.error("Error in rst - " + e.getMessage());
		}
		try {
			if (st != null) {
				st.close();
			}
		} catch (Exception e) {
			logger.error("Error in st - " + e.getMessage());
		}
		try {
			if (pst != null) {
				pst.close();
			}
		} catch (Exception e) {
			logger.error("Error in pst - " + e.getMessage());
		}
		try {
			if (clstmt != null) {
				clstmt.close();
			}
		} catch (Exception e) {
			logger.error("Error in clstmt - " + e.getMessage());
		}
		try {
			if (oclstmt != null) {
				oclstmt.close();
			}
		} catch (Exception e) {
			logger.error("Error in oclstmt - " + e.getMessage());
		}
		logger.debug("End in closeResources() method");
	}

	/**
	 * Method : isClosed Description : Check the status of connection
	 * 
	 * @return flag : boolean
	 * @throws : Exception
	 */
	public boolean isClosed() throws Exception {
		logger.debug("DBUtility - isClosed()");
		boolean flag = true;
		try {
			flag = conn.isClosed();
		} catch (SQLException sqle) {
			logger.debug(sqle.getMessage(), sqle);
			throw sqle;
		}
		return flag;
	}

	/**
	 * Method : executeCallableQuery Description : Execute a query with
	 * CallableStatement
	 * 
	 * @param arr : String []
	 * @return list : ArrayList
	 * @throws : Exception
	 */
	@Deprecated public String getStampDutyValue(String deedTypeID, String instrumentID, String exemptionID[]) throws Exception {
		logger.debug("DBUtility - getStampDutyValue()");
		String status = "dd";
		int counter = 4;
		Object[][] totRecordObjArray;
		totRecordObjArray = new Object[counter][1];
		DBUtility dbUtil = new DBUtility();
		ArrayList exempList = new ArrayList();
		exempList.add(0, "1");
		exempList.add(1, "2");
		exempList.add(2, "3");
		try {
			clstmt.setString(1, deedTypeID);
			clstmt.setString(2, instrumentID);
			String temp[] = { "1", "2" };
			clstmt.registerOutParameter(4, OracleTypes.NUMBER);
			clstmt.execute();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			clstmt.close();
		}
		return status;
	}

	/**
	 * for calculating stamp duty
	 * 
	 * @param deed
	 * @param instrument
	 * @param exemptionIDs
	 * @return Stamp Duty
	 */
	@Deprecated public String getNonJudStampDuty(String _refDeedTypeId, String _refInstrId, String _refExemId) {
		CallableStatement cst = null;
		String stampDuty = null;
		logger.debug("DBUtility - getNonJudStampDuty()");
		DBUtility db = null;
		try {
			db = new DBUtility();
			db.createCallableStatement("call IGRS_STAMP_DUTY.IGRS_STAMP_DUTY_CALC3(?,?,?,?,?)");
			cst = db.clstmt;
			//_refExemId="1,2";
			logger.debug("DBUtility - getNonJudStampDuty()" + _refDeedTypeId);
			logger.debug("DBUtility - getNonJudStampDuty()" + _refInstrId);
			logger.debug("DBUtility - getNonJudStampDuty()" + _refExemId);
			cst.setString(1, _refDeedTypeId);
			cst.setString(2, _refInstrId);
			cst.setString(3, _refExemId);
			cst.setString(4, null);
			cst.registerOutParameter(5, OracleTypes.NUMBER);
			if (!cst.execute()) {
				int temp = cst.getInt(5);
				stampDuty = String.valueOf(temp);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			try {
				db.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		logger.debug("DBUtility - getNonJudStampDuty() stamp Duty" + stampDuty);
		return stampDuty;
	}

	/**
	 * for calculating stamp duty for Judicial
	 * 
	 * @param catId
	 * @param basevalue
	 * @param units
	 * @return Stamp Duty
	 */
	@Deprecated public String getJudStampDuty(String _refcatId, String _refBaseVal, String _refUnits) {
		CallableStatement cst = null;
		String stampDuty = null;
		logger.debug("DBUtility - getJudStampDuty() stamp id" + _refcatId);
		DBUtility db = null;
		try {
			db = new DBUtility();
			db.createCallableStatement("call IGRS_STAMP_DUTY_JUDICIAL_CALC.IGRS_JUDICIAL_SDUTY_CALC(?,?,?,?)");
			cst = db.clstmt;
			cst.setString(1, _refcatId);
			cst.setString(2, _refBaseVal);
			cst.setString(3, _refUnits);
			cst.registerOutParameter(4, OracleTypes.NUMBER);
			if (!cst.execute()) {
				int temp = cst.getInt(4);
				stampDuty = String.valueOf(temp);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			try {
				db.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		logger.debug("DBUtility - getJudStampDuty() stamp Duty" + stampDuty);
		return stampDuty;
	}

	/**
	 * for calculating othersFeeDuty
	 * 
	 * @param _refFunId
	 * @return othersFeeDuty
	 */
	@Deprecated public String getOthersFeeDuty(String _refFunId) {
		CallableStatement cst = null;
		String othersFeeDuty = null;
		logger.debug("DBUtility - getOthersFeeDuty()");
		DBUtility db = null;
		try {
			db = new DBUtility();
			db.createCallableStatement("call IGRS_SERVICE_FEE_PKG.IGRS_SERVICE_FEE_PROC(?,?)");
			cst = db.clstmt;
			cst.setString(1, _refFunId);
			cst.registerOutParameter(2, OracleTypes.NUMBER);
			if (!cst.execute()) {
				int temp = cst.getInt(2);
				othersFeeDuty = String.valueOf(temp);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			try {
				db.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		logger.debug("DBUtility - getOthersFeeDuty() othersFeeDuty" + othersFeeDuty);
		return othersFeeDuty;
	}

	/**
	 * Method : executeQry Description : Execute a query with Statement
	 * 
	 * @param arr : String
	 * @return record : String
	 * @throws : Exception
	 */
	@Deprecated public int executeUpdt(String query) throws Exception {
		logger.debug("DBUtility - executeUpdt(String) query = " + query);
		int result = 0;
		try {
			logger.debug("before run" + st);
			result = st.executeUpdate(query);
			logger.debug("after run" + result);
		} catch (SQLException sqle) {
			logger.error(sqle.getMessage(), sqle);
			throw sqle;
		} catch (NumberFormatException nfe) {
			logger.error(nfe.getMessage(), nfe);
			throw nfe;
		} catch (IllegalArgumentException iae) {
			logger.error(iae.getMessage(), iae);
			throw iae;
		} finally {
			st.close();
		}
		return result;
	}

	/**
	 * for calculating stamp duty
	 * 
	 * @param deed
	 * @param instrument
	 * @param exemptionIDs
	 * @return Stamp Duty
	 */
	@Deprecated public String getStampDuty(String deed, String instrument, String exemption) {
		CallableStatement cst = null;
		String stampDuty = null;
		DBUtility db = null;
		try {
			db = new DBUtility();
			db.createCallableStatement("call IGRS_STAMP_DUTY.IGRS_STAMP_DUTY_CALC3(?,?,?,?,?)");
			cst = db.clstmt;
			cst.setString(1, deed);
			cst.setString(2, instrument);
			cst.setString(3, exemption);
			cst.setString(4, null);
			cst.registerOutParameter(5, OracleTypes.NUMBER);
			if (!cst.execute()) {
				int temp = cst.getInt(5);
				stampDuty = String.valueOf(temp);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			try {
				db.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return stampDuty;
	}

	/**
	 * for calculating Other Fee
	 * 
	 * @param function_id
	 * @return otherFee
	 */
	@Deprecated public int getOthersDuty(String function_id) {
		CallableStatement cst = null;
		int otherFee = 0;
		DBUtility db = null;
		try {
			db = new DBUtility();
			db.createCallableStatement("call IGRS_SERVICE_FEE_PKG.IGRS_SERVICE_FEE_PROC(?,?)");
			cst = db.clstmt;
			cst.setString(1, function_id);
			cst.registerOutParameter(2, OracleTypes.NUMBER);
			if (!cst.execute()) {
				otherFee = cst.getInt(2);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			try {
				db.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return otherFee;
	}

	/**
	 * for calculating othersFeeDuty
	 * 
	 * @param _refFunId
	 * @return othersFeeDuty
	 */
	@Deprecated public String getWillOtherFeeValue(String _refFunId) {
		CallableStatement cst = null;
		String othersFeeDuty = null;
		DBUtility db = null;
		try {
			db = new DBUtility();
			db.createCallableStatement("call IGRS_SERVICE_FEE_PKG.IGRS_SERVICE_FEE_PROC(?,?,?,?,?,?)");
			cst = db.clstmt;
			cst.setString(1, _refFunId);
			cst.setString(2, null);
			cst.setString(3, null);
			cst.registerOutParameter(4, OracleTypes.NUMBER);
			cst.registerOutParameter(5, OracleTypes.CLOB);
			cst.registerOutParameter(6, OracleTypes.VARCHAR);
			if (!cst.execute()) {
				int temp = cst.getInt(4);
				othersFeeDuty = String.valueOf(temp);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			try {
				db.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		logger.debug("DBUtility:: getWillOtherFeeValue():: othersFeeDuty:-      " + othersFeeDuty);
		return othersFeeDuty;
	}

	/**
	 * Method : createPreparedStatement Description : Creates a
	 * PreparedStatement object
	 * 
	 * @param query : String
	 * @throws : Exception
	 */
	@Deprecated public CallableStatement returnCallableStatement(String procedure) throws Exception {
		try {
			callstmt = conn.prepareCall(procedure);
		} catch (SQLException sqle) {
			logger.error(sqle.getMessage(), sqle);
			throw sqle;
		}
		return callstmt;
	}

	//added by saurav for deed draft changes
	public ArrayList executeQuerySearch(String appID) throws Exception {
		logger.debug("DBUtility - executeQuery()");
		ArrayList list = new ArrayList();
		try {
			/*  for (int i = 0; i < arr.length; i++) {
			      logger.debug("Wipro : arr[" + i + "] = " + arr[i]);
			      pst.setString(i+1,"%"+ arr[i]+"%");
			  }*/
			logger.debug("App Id " + appID);
			pst.setString(1, appID);
			rst = pst.executeQuery();
			logger.debug("Wipro in DBUtility - executeQuerySearch() after pst.executeQuerySearch()");
			ResultSetMetaData rsmd = rst.getMetaData();
			int col_count = rsmd.getColumnCount();
			while (rst.next()) {
				ArrayList row_list = new ArrayList();
				int i = 1;
				while (i <= col_count) {
					row_list.add(rst.getString(i));
					i++;
				}
				list.add(row_list);
			} //while
			pst.clearParameters();
		} catch (SQLException sqle) {
			logger.error(sqle.getMessage(), sqle);
			throw sqle;
		} catch (NumberFormatException nfe) {
			logger.error(nfe.getMessage(), nfe);
			throw nfe;
		} catch (IllegalArgumentException iae) {
			logger.error(iae.getMessage(), iae);
			throw iae;
		} finally {}
		list.trimToSize();
		return list;
	}

	/**
	 * Method : executeQuery1 Description : Execute a query with
	 * PreparedStatement purpose : To Deal with 'LIKE' search
	 * 
	 * @param arr : String []
	 * @return list : ArrayList
	 * @throws : Exception
	 */
	@Deprecated public ArrayList executeQueryLikeSearch(String regid) throws Exception {
		logger.debug("DBUtility - executeQuery()");
		ArrayList list = new ArrayList();
		try {
			/*  for (int i = 0; i < arr.length; i++) {
			      logger.debug("Wipro : arr[" + i + "] = " + arr[i]);
			      pst.setString(i+1,"%"+ arr[i]+"%");
			  }*/
			pst.setString(1, "%" + regid + "%");
			rst = pst.executeQuery();
			logger.debug("Wipro in DBUtility - executeQuery() after pst.executeQuery()");
			ResultSetMetaData rsmd = rst.getMetaData();
			int col_count = rsmd.getColumnCount();
			while (rst.next()) {
				ArrayList row_list = new ArrayList();
				for (int i = 1; i <= col_count; i++) {
					row_list.add(rst.getString(i));
				} //for
				list.add(row_list);
			} //while
			pst.clearParameters();
		} catch (SQLException sqle) {
			logger.error(sqle.getMessage(), sqle);
			throw sqle;
		} catch (NumberFormatException nfe) {
			logger.error(nfe.getMessage(), nfe);
			throw nfe;
		} catch (IllegalArgumentException iae) {
			logger.error(iae.getMessage(), iae);
			throw iae;
		} finally {}
		list.trimToSize();
		return list;
	}

	@Deprecated public boolean attachAGMPAuditReport(String docId, String filePath, String fileName, String[] OthDetails, String functionName) throws Exception {
		boolean check = true;
		String returnVal = null;
		String sqlText = null;
		BLOB image = null;
		int chunkSize;
		byte[] binaryBuffer;
		long position;
		int bytesRead = 0;
		int bytesWritten = 0;
		int totbytesRead = 0;
		int totbytesWritten = 0;
		try {
			int j = 0;
			File file = new File(filePath + fileName);
			InputStream ioFile = new FileInputStream(file);
			if (functionName.equalsIgnoreCase("audit")) {
				sqlText = "INSERT INTO IGRS_RAUDIT_DOCUMENT_DETAILS" + "(DOCUMENT_TXN_ID, DOCUMENT_FILE_NAME, DOCUMENT_FILE, DOCUMENT_STATUS, CREATED_BY, CREATED_DATE, UPDATE_BY, UPDATE_DATE) " + "VALUES('" + docId + "','" + fileName + "',EMPTY_BLOB(),'" + OthDetails[0] + "','" + OthDetails[1] + "',SYSDATE,'" + OthDetails[2] + "',SYSDATE)";
			} else if (functionName.equalsIgnoreCase("inspection")) {
				sqlText = "INSERT INTO IGRS_POI_DOC_DTLS" + "(DOC_TXN_ID, DOC_NAME, DOC_OBJECT, DOC_STATUS, CREATED_BY, CREATED_DATE, UPDATE_BY, UPDATE_DATE) " + "VALUES('" + docId + "','" + fileName + "',EMPTY_BLOB(),'" + OthDetails[0] + "','" + OthDetails[1] + "',SYSDATE,'" + OthDetails[2] + "',SYSDATE)";
			}
			st = conn.createStatement();
			st.executeUpdate(sqlText);
			logger.debug("After insert ");
			if (functionName.equalsIgnoreCase("audit")) {
				sqlText = "SELECT DOCUMENT_FILE " + "FROM   IGRS_RAUDIT_DOCUMENT_DETAILS " + "WHERE  DOCUMENT_TXN_ID ='" + docId + "' FOR UPDATE";
			} else if (functionName.equalsIgnoreCase("inspection")) {
				sqlText = "SELECT DOC_OBJECT " + "FROM   IGRS_POI_DOC_DTLS " + "WHERE  DOC_TXN_ID ='" + docId + "' FOR UPDATE";
			}
			rst = st.executeQuery(sqlText);
			rst.next();
			if (functionName.equalsIgnoreCase("audit")) {
				image = ((OracleResultSet) rst).getBLOB("DOCUMENT_FILE");
			} else if (functionName.equalsIgnoreCase("inspection")) {
				image = ((OracleResultSet) rst).getBLOB("DOC_OBJECT");
			}
			chunkSize = image.getChunkSize();
			binaryBuffer = new byte[chunkSize];
			position = 1;
			while ((bytesRead = ioFile.read(binaryBuffer)) != -1) {
				bytesWritten = image.putBytes(position, binaryBuffer, bytesRead);
				position += bytesRead;
				totbytesRead += bytesRead;
				totbytesWritten += bytesWritten;
			}
			ioFile.close();
			logger.debug("==========================================================\n" + "  PUT METHOD\n" + "==========================================================\n" + "Wrote file  to BLOB column.\n" + totbytesRead + " bytes read.\n" + totbytesWritten + " bytes written.\n");
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			check = false;
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			logger.debug("SQL:\n" + sqlText);
			check = false;
		}
		if (check) {
			conn.commit();
		} else {
			conn.rollback();
		}
		return check;
	}

	@Deprecated public boolean saveSroDocuments(String docTxnId, String inspectionId, String filename, String filesPath) {
		boolean flag = true;
		int i = 0;
		String sqlText = null;
		BLOB image = null;
		int chunkSize;
		byte[] binaryBuffer;
		long position;
		int bytesRead = 0;
		int bytesWritten = 0;
		int totbytesRead = 0;
		int totbytesWritten = 0;
		File file = null;
		try {
			logger.debug("File path :" + filesPath + filename);
			file = new File(filesPath + filename);
			InputStream ioFile = new FileInputStream(file);
			st = conn.createStatement();
			//BLOB reading as well as writting in to the Database START
			//before that we need to get the sequence number
			/* sqlText ="INSERT INTO IGRS_SRO_INSP_DOC_MAP"
				+"(INSP_TXN_ID,DOCUMENT_TXN_ID,DOCUMENT_NAME,DOCUMENT_OBJ) "
				+" VALUES('"+inspectionId+"','"+docTxnId+"', '"+filename+"',EMPTY_BLOB())";*/
			//Modified by SHreeraJ
			sqlText = "INSERT INTO IGRS_SRO_INSP_DOC_MAP" + "(INSP_TXN_ID,DOCUMENT_TXN_ID,DOCUMENT_NAME,DOCUMENT_PATH) " + " VALUES('" + inspectionId + "','" + docTxnId + "', '" + filename + "','" + filesPath + "')";
			logger.debug("before insert query :" + sqlText);
			st.executeUpdate(sqlText);
			logger.debug("After insert ");
			sqlText = "SELECT DOCUMENT_OBJ " + "FROM   IGRS_SRO_INSP_DOC_MAP " + "WHERE  DOCUMENT_TXN_ID ='" + docTxnId + "'" + " FOR UPDATE";
			logger.debug("Before update start" + sqlText);
			rst = st.executeQuery(sqlText);
			rst.next();
			image = ((OracleResultSet) rst).getBLOB("DOCUMENT_OBJ");
			chunkSize = image.getChunkSize();
			binaryBuffer = new byte[chunkSize];
			position = 1;
			while ((bytesRead = ioFile.read(binaryBuffer)) != -1) {
				bytesWritten = image.putBytes(position, binaryBuffer, bytesRead);
				position += bytesRead;
				totbytesRead += bytesRead;
				totbytesWritten += bytesWritten;
			}
			ioFile.close();
			logger.debug("==========================================================\n" + "  PUT METHOD\n" + "==========================================================\n" + "Wrote file  to BLOB column.\n" + totbytesRead + " bytes read.\n" + totbytesWritten + " bytes written.\n");
			//BLOB end 
			//Deleteing file from physcial path
			file.delete();
		} catch (FileNotFoundException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error(e1.getMessage(), e1);
			}
			logger.error(e.getMessage(), e);
			file.delete();
			flag = false;
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error(e1.getMessage(), e1);
			}
			file.delete();
			flag = false;
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error(e1.getMessage(), e1);
			}
			//file.delete();
			flag = false;
		}
		return flag;
	}

	/**
	 * @param loanadvanceDTO
	 * @return
	 * @throws Exception
	 */
	@Deprecated public String insertAdvanceEMI(LoanadvanceDTO loanadvanceDTO) throws Exception {
		logger.debug("DBUtility - insertAdvanceEMI()");
		String status = "";
		String errorCode = "";
		try {
			logger.debug(" loanadvanceDTO.getAdvanceamount().toString() " + loanadvanceDTO.getAdvanceamount().toString());
			logger.debug(" loanadvanceDTO.getAdvancetypeid().toString() " + loanadvanceDTO.getAdvancetypeid().toString());
			logger.debug(" loanadvanceDTO.getEmpid().toString() " + loanadvanceDTO.getEmpid().toString());
			clstmt.setString(1, loanadvanceDTO.getAdvancetxnid().toString());
			clstmt.setString(2, loanadvanceDTO.getEmpid().toString());
			clstmt.setString(3, loanadvanceDTO.getAdvancetypeid().toString());
			clstmt.setString(4, loanadvanceDTO.getAdvanceamount().toString());
			clstmt.registerOutParameter(5, Types.INTEGER);
			clstmt.registerOutParameter(6, Types.VARCHAR);
			clstmt.execute();
			status = clstmt.getString(6);
			errorCode = clstmt.getString(5);
			logger.debug("Status from the store procedure " + status);
			logger.debug("Print the ErrorCode " + clstmt.getInt(5));
			logger.debug("Type:-" + status);
		} catch (SQLException sqle) {
			logger.error(sqle.getMessage(), sqle);
			throw sqle;
		} catch (NumberFormatException nfe) {
			logger.error(nfe.getMessage(), nfe);
			throw nfe;
		} catch (IllegalArgumentException iae) {
			logger.error(iae.getMessage(), iae);
			throw iae;
		}
		return errorCode;
	}

	@Deprecated public boolean attachOfficalInfo(String userid, String doctype, String fileName, String strFilePath, String strLoginId) throws Exception {
		boolean check = true;
		String sqlText = null;
		BLOB image = null;
		int chunkSize;
		byte[] binaryBuffer;
		long position;
		int bytesRead = 0;
		int bytesWritten = 0;
		int totbytesRead = 0;
		int totbytesWritten = 0;
		String strSeq = null;
		File file = null;
		try {
			logger.debug(" doctype " + doctype + " fileName   " + fileName + " strFilePath " + strFilePath);
			strSeq = "SELECT IGRS_EMP_DOCUMENT_DETAILS_SEQ.NEXTVAL as SEQ FROM DUAL";
			st = conn.createStatement();
			logger.debug("First query : " + strSeq);
			rst = st.executeQuery(strSeq);
			while (rst.next()) {
				strSeq = rst.getString("SEQ");
			}
			sqlText = "INSERT INTO IGRS_EMP_DOCUMENT_DETAILS(DOCUMENT_ID,DOCUMENT_NAME,EMP_ID,EMP_DOC_TYPE_ID,EMP_DOC_VALUE,EMP_DOC_STATUS," + "CREATED_BY,CREATED_DATE)" + " VALUES(" + strSeq + ",'" + fileName + "','" + userid + "','" + doctype + "',EMPTY_BLOB(),'A','" + strLoginId + "',sysdate)";
			logger.debug("sqlText  " + sqlText);
			logger.debug("File path :" + strFilePath + fileName);
			file = new File(strFilePath + fileName);
			InputStream ioFile = new FileInputStream(file);
			logger.debug("before insert query :" + sqlText);
			st.executeUpdate(sqlText);
			logger.debug("After insert ");
			sqlText = "SELECT EMP_DOC_VALUE " + "FROM   IGRS_EMP_DOCUMENT_DETAILS " + "WHERE  DOCUMENT_ID =" + strSeq + " FOR UPDATE";
			logger.debug("Before update start" + sqlText);
			rst = st.executeQuery(sqlText);
			rst.next();
			image = ((OracleResultSet) rst).getBLOB("EMP_DOC_VALUE");
			chunkSize = image.getChunkSize();
			binaryBuffer = new byte[chunkSize];
			position = 1;
			while ((bytesRead = ioFile.read(binaryBuffer)) != -1) {
				bytesWritten = image.putBytes(position, binaryBuffer, bytesRead);
				position += bytesRead;
				totbytesRead += bytesRead;
				totbytesWritten += bytesWritten;
			}
			ioFile.close();
			logger.debug("==========================================================\n" + "  PUT METHOD\n" + "==========================================================\n" + "Wrote file  to BLOB column.\n" + totbytesRead + " bytes read.\n" + totbytesWritten + " bytes written.\n");
			file.delete();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			check = false;
			conn.rollback();
			file.delete();
			//throw e;
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			logger.debug("SQL:\n" + sqlText);
			//    e.printStackTrace();
			conn.rollback();
			check = false;
			file.delete();
			//throw e;
		}
		return check;
	}

	@Deprecated public boolean attachSpLicence(String strFilePath, String fileName, String licenceTxnId) throws Exception {
		boolean check = true;
		String sqlText = null;
		BLOB image = null;
		int chunkSize;
		byte[] binaryBuffer;
		long position;
		int bytesRead = 0;
		int bytesWritten = 0;
		int totbytesRead = 0;
		int totbytesWritten = 0;
		File file = null;
		try {
			file = new File(strFilePath + fileName);
			InputStream ioFile = new FileInputStream(file);
			sqlText = "SELECT DOCUMENT_OBJ " + "FROM   IGRS_SP_USER_LICENSE_DETAILS " + "WHERE  LICENSE_TXN_ID ='" + licenceTxnId + "' FOR UPDATE";
			logger.debug("Before update start" + sqlText);
			rst = st.executeQuery(sqlText);
			rst.next();
			image = ((OracleResultSet) rst).getBLOB("DOCUMENT_OBJ");
			chunkSize = image.getChunkSize();
			binaryBuffer = new byte[chunkSize];
			position = 1;
			while ((bytesRead = ioFile.read(binaryBuffer)) != -1) {
				bytesWritten = image.putBytes(position, binaryBuffer, bytesRead);
				position += bytesRead;
				totbytesRead += bytesRead;
				totbytesWritten += bytesWritten;
			}
			ioFile.close();
			logger.debug("==========================================================\n" + "  PUT METHOD\n" + "==========================================================\n" + "Wrote file  to BLOB column.\n" + totbytesRead + " bytes read.\n" + totbytesWritten + " bytes written.\n");
			file.delete();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			//e.printStackTrace();
			check = false;
			conn.rollback();
			file.delete();
			//throw e;
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			logger.debug("SQL:\n" + sqlText);
			//e.printStackTrace();
			conn.rollback();
			check = false;
			file.delete();
			//throw e;
		}
		return check;
	}

	//added by shruti
	@Deprecated public boolean attachSpBankLicence(String strFilePath, String fileName, String licenceTxnId) throws Exception {
		boolean check = true;
		String sqlText = null;
		BLOB image = null;
		int chunkSize;
		byte[] binaryBuffer;
		long position;
		int bytesRead = 0;
		int bytesWritten = 0;
		int totbytesRead = 0;
		int totbytesWritten = 0;
		File file = null;
		try {
			file = new File(strFilePath + fileName);
			InputStream ioFile = new FileInputStream(file);
			sqlText = "SELECT DOCUMENT_OBJ " + "FROM   IGRS_SP_BANK_LICENSE_DETAILS " + "WHERE  LICENSE_TXN_ID ='" + licenceTxnId + "' FOR UPDATE";
			logger.debug("Before update start" + sqlText);
			rst = st.executeQuery(sqlText);
			rst.next();
			image = ((OracleResultSet) rst).getBLOB("DOCUMENT_OBJ");
			chunkSize = image.getChunkSize();
			binaryBuffer = new byte[chunkSize];
			position = 1;
			while ((bytesRead = ioFile.read(binaryBuffer)) != -1) {
				bytesWritten = image.putBytes(position, binaryBuffer, bytesRead);
				position += bytesRead;
				totbytesRead += bytesRead;
				totbytesWritten += bytesWritten;
			}
			ioFile.close();
			logger.debug("==========================================================\n" + "  PUT METHOD\n" + "==========================================================\n" + "Wrote file  to BLOB column.\n" + totbytesRead + " bytes read.\n" + totbytesWritten + " bytes written.\n");
			file.delete();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			//e.printStackTrace();
			check = false;
			conn.rollback();
			file.delete();
			//throw e;
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			logger.debug("SQL:\n" + sqlText);
			//e.printStackTrace();
			conn.rollback();
			check = false;
			file.delete();
			//throw e;
		}
		return check;
	}

	//end of code
	/**
	 * @param advanceTxnId
	 * @return
	 */
	@Deprecated public ArrayList calculateAdvanceEMIForApprovedOne(String advanceTxnId) {
		logger.debug("DBUtility - calculateAdvanceEMI()");
		String status = "";
		String errorCode = "";
		ArrayList list = null;
		try {
			clstmt.setString(1, advanceTxnId);
			clstmt.registerOutParameter(2, Types.INTEGER);
			clstmt.registerOutParameter(3, Types.INTEGER);
			clstmt.registerOutParameter(4, Types.INTEGER);
			clstmt.registerOutParameter(5, Types.INTEGER);
			clstmt.registerOutParameter(6, Types.INTEGER);
			clstmt.registerOutParameter(7, Types.VARCHAR);
			clstmt.execute();
			status = clstmt.getString(7);
			errorCode = clstmt.getString(6);
			logger.debug("Status from the store procedure " + status);
			logger.debug("Print the ErrorCode " + clstmt.getInt(6));
			logger.debug("Print paid inst no  :" + clstmt.getInt(2));
			logger.debug("Print paid amount  :" + clstmt.getInt(3));
			logger.debug("Unpaid inst no   :" + clstmt.getInt(4));
			logger.debug("Unpaid amount   :" + clstmt.getInt(5));
			if (clstmt.getInt(6) == 0) {
				list = new ArrayList();
				list.add((clstmt.getInt(2)) + "");
				list.add((clstmt.getInt(3)) + "");
				list.add((clstmt.getInt(4)) + "");
				list.add((clstmt.getInt(5)) + "");
				logger.debug(" list   " + list);
			}
			logger.debug("Type:-" + status);
		} catch (SQLException sqle) {
			logger.error(sqle.getMessage(), sqle);
		} catch (NumberFormatException nfe) {
			logger.error(nfe.getMessage(), nfe);
		} catch (IllegalArgumentException iae) {
			logger.error(iae.getMessage(), iae);
		}
		return list;
	}

	// Method Changed by OneApps
	@Deprecated public String savePreEnquiryDoc(String[] details, String filePath) throws Exception {
		boolean check = true;
		String returnVal = null;
		String sqlText = null;
		BLOB image = null;
		int chunkSize;
		byte[] binaryBuffer;
		long position;
		int bytesRead = 0;
		int bytesWritten = 0;
		int totbytesRead = 0;
		int totbytesWritten = 0;
		String strSeq = null;
		try {
			File file = new File(filePath + details[1]);
			InputStream ioFile = new FileInputStream(file);
			st = conn.createStatement();
			//BLOB reading as well as writting in to the Database START
			//before that we need to get the sequence number
			//String strSeq =null;
			rst = st.executeQuery("SELECT IGRS_EMP_COM_DOC_MAP_SEQ.NEXTVAL as seq FROM DUAL");
			rst.next();
			strSeq = ((OracleResultSet) rst).getString("seq");
			// logger.debug("Sequint number "+strSeq);
			sqlText = "INSERT INTO IGRS_EMP_COMPLAINT_DOC_MAP" + "(DOCUMENT_ID,COMPLAINT_NO,DOCUMENT_NAME,DOCUMENT_OBJECT,DOCUMENT_TYPE,DOCUMENT_UPLOAD_DATE) " + " VALUES(" + strSeq + ",'" + details[0] + "', '" + details[1] + "',EMPTY_BLOB(),'" + details[2] + "', '" + details[3] + "')";
			//  logger.debug("before insert query :"+sqlText);
			st.executeUpdate(sqlText);
			logger.debug("After insert ");
			sqlText = "SELECT DOCUMENT_OBJECT " + "FROM   IGRS_EMP_COMPLAINT_DOC_MAP " + "WHERE  DOCUMENT_ID =" + strSeq + " FOR UPDATE";
			// logger.debug("Before update start"+sqlText);
			rst = st.executeQuery(sqlText);
			rst.next();
			image = ((OracleResultSet) rst).getBLOB("DOCUMENT_OBJECT");
			chunkSize = image.getChunkSize();
			binaryBuffer = new byte[chunkSize];
			position = 1;
			while ((bytesRead = ioFile.read(binaryBuffer)) != -1) {
				bytesWritten = image.putBytes(position, binaryBuffer, bytesRead);
				position += bytesRead;
				totbytesRead += bytesRead;
				totbytesWritten += bytesWritten;
			}
			ioFile.close();
			logger.debug("==========================================================\n" + "  PUT METHOD\n" + "==========================================================\n" + "Wrote file  to BLOB column.\n" + totbytesRead + " bytes read.\n" + totbytesWritten + " bytes written.\n");
			//BLOB end 
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			//e.printStackTrace();
			check = false;
			//throw e;
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			logger.debug("SQL:\n" + sqlText);
			//e.printStackTrace();
			check = false;
			//throw e;
		}
		if (check) {
			conn.commit();
		} else {
			conn.rollback();
		}
		if (check) {
			returnVal = strSeq;
		} else {
			returnVal = "false";
		}
		return returnVal;
	}

	@Deprecated public String insertIntoDetailsHistiory(ArrayList list) throws Exception {
		logger.debug("DBUtility - insertAdvanceEMI()");
		String status = "";
		String errorCode = "";
		try {
			LoanDTO loanDTO = (LoanDTO) list.get(0);
			clstmt.setString(1, loanDTO.getLoantxnid().toString());
			clstmt.setString(2, loanDTO.getEmpid().toString());
			clstmt.setString(3, loanDTO.getLoanid().toString());
			clstmt.setString(4, loanDTO.getLoanamount().toString());
			clstmt.setString(5, loanDTO.getUserinstallment().toString());
			clstmt.registerOutParameter(6, Types.INTEGER);
			clstmt.registerOutParameter(7, Types.VARCHAR);
			clstmt.execute();
			status = clstmt.getString(7);
			errorCode = clstmt.getString(6);
			logger.debug("Type:-" + status);
		} catch (SQLException sqle) {
			logger.error(sqle.getMessage(), sqle);
			throw sqle;
		} catch (NumberFormatException nfe) {
			logger.error(nfe.getMessage(), nfe);
			throw nfe;
		} catch (IllegalArgumentException iae) {
			logger.error(iae.getMessage(), iae);
			throw iae;
		}
		return errorCode;
	}

	@Deprecated public ArrayList calculateLoanEMIForApprovedOne(String loanTxnId) {
		logger.debug("DBUtility - calculateLoanEMIForApprovedOne()");
		String status = "";
		String errorCode = "";
		ArrayList list = null;
		try {
			clstmt.setString(1, loanTxnId);
			clstmt.registerOutParameter(2, Types.INTEGER);
			clstmt.registerOutParameter(3, Types.INTEGER);
			clstmt.registerOutParameter(4, Types.INTEGER);
			clstmt.registerOutParameter(5, Types.INTEGER);
			clstmt.registerOutParameter(6, Types.INTEGER);
			clstmt.registerOutParameter(7, Types.VARCHAR);
			clstmt.execute();
			status = clstmt.getString(7);
			errorCode = clstmt.getString(6);
			if (clstmt.getInt(6) == 0) {
				list = new ArrayList();
				list.add((clstmt.getInt(2)) + "");
				list.add((clstmt.getInt(3)) + "");
				list.add((clstmt.getInt(4)) + "");
				list.add((clstmt.getInt(5)) + "");
				logger.debug(" list   " + list);
			}
			logger.debug("Type:-" + status);
		} catch (SQLException sqle) {
			logger.error(sqle.getMessage(), sqle);
		} catch (NumberFormatException nfe) {
			logger.error(nfe.getMessage(), nfe);
		} catch (IllegalArgumentException iae) {
			logger.error(iae.getMessage(), iae);
		}
		return list;
	}

	public String getDownload(HttpServletResponse res, String contId) {
		Connection connection = null;
		CallableStatement cstmnt = null;
		String preferenceString = null;
		Statement stmt = null;
		ResultSet rs = null;
		OutputStream os = null;
		String ext = null;
		String contentName;
		String errorCode = null;
		String errorMsg = null;
		try {
			//connection = dataSource.getConnection();
			int att_id = 0;
			DBUtility db = new DBUtility();
			//db.createStatement();
			db.createCallableStatement("call downloadDepartmentEnqFileProc(?,?,?,?,?,?)");
			cstmnt = db.clstmt;
			/*clstmt =
				con.prepareCall(
					" call downloadDepartmentEnqFileProc(?,?,?,?,?,?)",
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);*/
			logger.debug("Print in Util value " + contId);
			cstmnt.setString(1, contId);
			cstmnt.registerOutParameter(2, OracleTypes.BLOB);
			cstmnt.registerOutParameter(3, OracleTypes.VARCHAR);
			cstmnt.registerOutParameter(4, OracleTypes.VARCHAR);
			cstmnt.registerOutParameter(5, OracleTypes.VARCHAR);
			cstmnt.registerOutParameter(6, OracleTypes.VARCHAR);
			boolean value = cstmnt.execute();
			logger.debug("Print somethig after execution" + value);
			//Added to get file name and type from database
			contentName = cstmnt.getString(3);
			logger.debug("Getting the value " + contentName);
			ext = cstmnt.getString(4);
			//contentName += "." + ext;
			errorCode = cstmnt.getString(5);
			errorMsg = cstmnt.getString(6);
			logger.debug(" errorCode " + errorCode + " errorMsg   " + errorMsg + "  contentName  " + contentName);
			logger.info("errorCode :" + errorCode);
			int i = 0;
			oracle.sql.BLOB fileBlob = (oracle.sql.BLOB) cstmnt.getBlob(2);
			logger.debug(" fileBlob " + fileBlob.length());
			os = res.getOutputStream();
			long index = 1;
			if (contentName != null) {
				ext = contentName.substring(contentName.length() - 3, contentName.length());
				logger.debug(" ext " + ext);
				if (ext != null) {
					if (ext.equalsIgnoreCase("doc")) {
						res.setContentType("application/download");
					} else if (ext.equalsIgnoreCase("ppt")) {
						res.setContentType("application/download");
					} else if (ext.equalsIgnoreCase("xls")) {
						res.setContentType("application/download");
					} else if (ext.equalsIgnoreCase("htm")) {
						res.setContentType("text/html");
					} else if (ext.equalsIgnoreCase("tml")) {
						res.setContentType("text/html");
					} else if (ext.equalsIgnoreCase("txt")) {
						res.setContentType("text/plain");
					} else if (ext.equalsIgnoreCase("pdf")) {
						res.setContentType("application/pdf");
					} else if (ext.equalsIgnoreCase("bmp")) {
						res.setContentType("image/x-bmp");
					} else if (ext.equalsIgnoreCase("gif")) {
						res.setContentType("image/gif");
					} else if (ext.equalsIgnoreCase("jpg")) {
						res.setContentType("image/jpeg");
					} else if (ext.equalsIgnoreCase("peg")) {
						res.setContentType("image/jpeg");
					} else {
						res.setContentType("application/download");
					}
				}
			}
			res.setHeader("Content-Disposition", "attachment; filename=" + contentName);
			while (index < fileBlob.length()) {
				os.write(fileBlob.getBytes(index, 10000));
				os.flush();
				index += 10000;
			}
		} catch (Exception excp) {
			logger.error(excp.getMessage(), excp);
		} finally {
			try {
				/*if (rst != null) {
					rst.close();
				}
				if (clstmt != null) {
					clstmt.close();
				}
				if (con != null) {
					con.close();
					//closeConnection(connection);
				}*/
				if (os != null) {
					os.close();
				}
			} catch (Exception excp) {
				logger.error(excp.getMessage(), excp);
			}
		}
		return errorCode + errorMsg;
	}

	/**
	 * added by Imran Shaik for calculating stamp duty
	 * 
	 * @param deed
	 * @param instrument
	 * @param marketValue
	 * @param exemptionIDs
	 * @return Stamp Duty
	 */
	@Deprecated public String getStampDuty(String deed, String instrument, String exemption, String marketValue) {
		CallableStatement cst = null;
		String stampDuty = null;
		try {
			DBUtility db = new DBUtility();
			db.createCallableStatement("call IGRS_STAMP_DUTY_PKG.IGRS_STAMP_DUTY_PROC(?,?,?,?,?,?,?,?)");
			cst = db.clstmt;
			cst.setString(1, deed);
			cst.setString(2, instrument);
			cst.setString(3, exemption);
			cst.setString(4, marketValue);
			cst.setString(5, null);
			cst.registerOutParameter(6, OracleTypes.NUMBER);
			cst.registerOutParameter(7, OracleTypes.VARCHAR);
			cst.registerOutParameter(8, OracleTypes.VARCHAR);
			if (!cst.execute()) {
				int temp = cst.getInt(6);
				stampDuty = String.valueOf(temp);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return stampDuty;
	}

	/**
	 * added by Imran Shaik for calculating Other Fee
	 * 
	 * @param function_id
	 * @return otherFee
	 */
	@Deprecated public int getOthersDuty(String args[]) {
		CallableStatement cst = null;
		int otherFee = 0;
		try {
			DBUtility db = new DBUtility();
			db.createCallableStatement("call IGRS_SERVICE_FEE_PKG.IGRS_SERVICE_FEE_PROC(?,?,?,?,?,?)");
			cst = db.clstmt;
			cst.setString(1, args[0]);
			cst.setString(2, args[1]);
			cst.setString(3, args[2]);
			cst.registerOutParameter(4, OracleTypes.NUMBER);
			cst.registerOutParameter(5, OracleTypes.CLOB);
			cst.registerOutParameter(6, OracleTypes.VARCHAR);
			if (!cst.execute()) {
				otherFee = cst.getInt(4);
				logger.debug("otherFee=" + otherFee);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return otherFee;
	}

	//This method is used for reading the BLOB containet and making file to show int the UI START
	/**
	 * Method used to write the contents (data) from an Oracle BLOB column to an
	 * O/S file. This method uses one of two ways to get data from the BLOB
	 * column - namely the getBytes() method. The other way to read data from an
	 * Oracle BLOB column is to use Streams.
	 * 
	 * @throws java.io.IOException
	 * @throws java.sql.SQLException
	 */
	@Deprecated public void readBLOBToFileGet(HttpServletResponse res, String strDocId, String strFunctionName) throws IOException, SQLException {
		String sqlText = null;
		BLOB image = null;
		String contentName = null;
		OutputStream os = null;
		String ext = null;
		try {
			st = conn.createStatement();
			String docObject = "document_object";
			String docName = "document_name";
			if (strFunctionName.equals("EMPMANG")) {
				sqlText = "SELECT EMP_DOC_VALUE," + docName + " FROM   IGRS_EMP_DOCUMENT_DETAILS " + "WHERE  DOCUMENT_ID =" + strDocId + " FOR UPDATE";
				docObject = "EMP_DOC_VALUE";
			} else if (strFunctionName.equalsIgnoreCase("DEPTENQ")) {
				sqlText = "SELECT document_object," + docName + " FROM   IGRS_EMP_COMPLAINT_DOC_MAP " + "WHERE  DOCUMENT_ID =" + strDocId + " FOR UPDATE";
			} else if (strFunctionName.equalsIgnoreCase("INSPECTION")) {
				sqlText = "SELECT document_obj," + docName + " FROM   IGRS_SRO_INSP_DOC_MAP " + "WHERE  DOCUMENT_TXN_ID =" + strDocId + " FOR UPDATE";
				docObject = "DOCUMENT_OBJ";
			} else if (strFunctionName.equalsIgnoreCase("POIINSPECTION")) {
				docName = "DOC_NAME";
				sqlText = "SELECT DOC_OBJECT," + docName + " FROM   IGRS_POI_DOC_DTLS " + "WHERE  DOC_TXN_ID ='" + strDocId + "'" + " FOR UPDATE";
				docObject = "DOC_OBJECT";
			} else if (strFunctionName.equalsIgnoreCase("SP")) {
				docName = "DOCUMENT_NAME";
				docObject = "DOCUMENT_OBJ";
				sqlText = "SELECT " + docName + "," + docObject + " FROM IGRS_SP_USER_LICENSE_DETAILS" + " WHERE LICENSE_TXN_ID='" + strDocId + "'" + " FOR UPDATE";
			}
			logger.debug("before excuting :" + sqlText);
			rst = st.executeQuery(sqlText);
			//    logger.debug("After eceutingn ");
			//rst.next();
			//image = ((OracleResultSet) rst).getBLOB("document_object");
			while (rst.next()) {
				image = ((OracleResultSet) rst).getBLOB(docObject);
				contentName = ((OracleResultSet) rst).getString(docName);
			}
			//logger.debug("Content Name :"+contentName);
			os = res.getOutputStream();
			long index = 1;
			if (contentName != null) {
				//logger.debug(" Before exception ");
				ext = contentName.substring(contentName.length() - 3, contentName.length());
				//logger.debug(" ext "+ext);
				if (ext != null) {
					if (ext.equalsIgnoreCase("doc")) {
						res.setContentType("application/download");
					} else if (ext.equalsIgnoreCase("ppt")) {
						res.setContentType("application/download");
					} else if (ext.equalsIgnoreCase("xls")) {
						res.setContentType("application/download");
					} else if (ext.equalsIgnoreCase("htm")) {
						res.setContentType("text/html");
					} else if (ext.equalsIgnoreCase("tml")) {
						res.setContentType("text/html");
					} else if (ext.equalsIgnoreCase("txt")) {
						res.setContentType("text/plain");
					} else if (ext.equalsIgnoreCase("pdf")) {
						res.setContentType("application/pdf");
					} else if (ext.equalsIgnoreCase("bmp")) {
						res.setContentType("image/x-bmp");
					} else if (ext.equalsIgnoreCase("gif")) {
						res.setContentType("image/gif");
					} else if (ext.equalsIgnoreCase("jpg")) {
						res.setContentType("image/jpeg");
					} else if (ext.equalsIgnoreCase("peg")) {
						res.setContentType("image/jpeg");
					} else {
						res.setContentType("application/download");
					}
				}
			}
			res.setHeader("Content-Disposition", "attachment; filename=" + contentName);
			// Loop through while reading a data from the BLOB
			// column using the getBytes() method. This data will be stored
			// in a BLOB column and writting in OutPutStream.
			while (index < image.length()) {
				os.write(image.getBytes(index, 10000));
				os.flush();
				index += 10000;
			}
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			//    e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	//BLOB Regading END
	//This method is used for reading the BLOB containet and making file to show int the UI START
	/**
	 * Method used to write the contents (data) from an Oracle BLOB column to an
	 * O/S file. This method uses one of two ways to get data from the BLOB
	 * column - namely the getBytes() method. The other way to read data from an
	 * Oracle BLOB column is to use Streams.
	 * 
	 * @throws java.io.IOException
	 * @throws java.sql.SQLException
	 */
	@Deprecated public ResultSet readBLOBToFileGet(String strDocId) throws IOException, SQLException {
		String sqlText = null;
		BLOB image = null;
		String contentName = null;
		OutputStream os = null;
		String ext = null;
		try {
			if (conn == null || conn.isClosed()) {
				try {
					conn = returnConnection();
				} catch (Exception e) {
					logger.error("error :- " + e.getMessage());
					e.printStackTrace();
				}
			}
			st = conn.createStatement();
			String docObject = "DOCUMENT_OBJ";
			String docExtn = "DOCUMENT_EXTN";
			String docName = "DEATH_CERTIFICATE";
			sqlText = "SELECT DOCUMENT_OBJ,DOCUMENT_EXTN FROM IGRS_REG_PIN_DOC_DETAILS WHERE  PIN_REQ_TXN_ID ='" + strDocId + "' AND DOCUMENT_NAME='" + docName + "' FOR UPDATE";
			logger.debug("before excuting :" + sqlText);
			rst = st.executeQuery(sqlText);
			logger.debug("after excuting :" + sqlText);
			//    logger.debug("After eceutingn ");
			//rst.next();
			//image = ((OracleResultSet) rst).getBLOB("document_object");
			return rst;
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			//	logger.error("SQL:\n" + sqlText);
			//  e.printStackTrace();
			//    logger.error(
			//			" DBUtility:downloadContent:finally :General Exception Clause"
			//				+ e.toString());
		} finally {
			try {
				releaseConnection(conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	//This method is used for reading the BLOB containet and making file to show int the UI START
	/**
	 * Method used to write the contents (data) from an Oracle BLOB column to an
	 * O/S file. This method uses one of two ways to get data from the BLOB
	 * column - namely the getBytes() method. The other way to read data from an
	 * Oracle BLOB column is to use Streams.
	 * 
	 * @throws java.io.IOException
	 * @throws java.sql.SQLException
	 */
	@Deprecated public ResultSet readBLOBToFileGet1(String strDocId) throws IOException, SQLException {
		String sqlText = null;
		BLOB image = null;
		String contentName = null;
		OutputStream os = null;
		String ext = null;
		try {
			if (conn == null || conn.isClosed()) {
				try {
					conn = returnConnection();
				} catch (Exception e) {
					logger.error("error :- " + e.getMessage());
					e.printStackTrace();
				}
			}
			st = conn.createStatement();
			String docObject = "DOCUMENT_OBJ";
			String docName = "MUTATION_FILE";
			String docExtn = "DOCUMENT_EXTN";
			sqlText = "SELECT DOCUMENT_OBJ,DOCUMENT_EXTN FROM IGRS_REG_PIN_DOC_DETAILS WHERE  PIN_REQ_TXN_ID ='" + strDocId + "' AND DOCUMENT_NAME='" + docName + "' FOR UPDATE";
			//docObject ="DEATH_CERTIFICATE";
			logger.debug("before excuting :" + sqlText);
			rst = st.executeQuery(sqlText);
			logger.debug("after excuting :" + sqlText);
			//logger.debug("After eceutingn ");
			//rst.next();
			//image = ((OracleResultSet) rst).getBLOB("document_object");
			return rst;
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			//logger.error("SQL:\n" + sqlText);
			////e.printStackTrace();
			//logger.error(
			//			" DBUtility:downloadContent:finally :General Exception Clause"
			//				+ e.toString());
		} finally {
			try {
				releaseConnection(conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	//BLOB Regading END
	@Deprecated public boolean saveDRODocuments(String inspId, String docTxnId, String fileName, String filePath, String docTypeId) {
		boolean check = true;
		String returnVal = null;
		String sqlText = null;
		BLOB image = null;
		int chunkSize;
		byte[] binaryBuffer;
		long position;
		int bytesRead = 0;
		int bytesWritten = 0;
		int totbytesRead = 0;
		int totbytesWritten = 0;
		String strSeq = null;
		try {
			File file = new File(filePath + fileName);
			InputStream ioFile = new FileInputStream(file);
			st = conn.createStatement();
			//BLOB reading as well as writting in to the Database START
			//before that we need to get the sequence number
			//String strSeq =null;
			///------------Added new
			rst = st.executeQuery("select DOCUMENT_TYPE_ID	from IGRS_DOCUMENT_TYPE_MASTER where DOCUMENT_NAME='" + docTypeId + "'");
			rst.next();
			String documentid = ((OracleResultSet) rst).getString("DOCUMENT_TYPE_ID");
			//---------added new
			// logger.debug("Sequint number "+strSeq);
			sqlText = "INSERT INTO IGRS_DRO_INSP_DOC_MAP" + "(DOCUMENT_TXN_ID,INSP_TXN_ID,DOCUMENT_NAME,DOCUMENT_OBJ,DOC_TYPE_ID) " + " VALUES('" + docTxnId + "','" + inspId + "', '" + fileName + "',EMPTY_BLOB(),'" + documentid + "')";
			//  logger.debug("before insert query :"+sqlText);
			st.executeUpdate(sqlText);
			logger.debug("After insert ");
			sqlText = "SELECT DOCUMENT_OBJ " + "FROM   IGRS_DRO_INSP_DOC_MAP " + "WHERE  DOCUMENT_TXN_ID ='" + docTxnId + "'" + " FOR UPDATE";
			// logger.debug("Before update start"+sqlText);
			rst = st.executeQuery(sqlText);
			rst.next();
			image = ((OracleResultSet) rst).getBLOB("DOCUMENT_OBJ");
			chunkSize = image.getChunkSize();
			binaryBuffer = new byte[chunkSize];
			position = 1;
			while ((bytesRead = ioFile.read(binaryBuffer)) != -1) {
				bytesWritten = image.putBytes(position, binaryBuffer, bytesRead);
				position += bytesRead;
				totbytesRead += bytesRead;
				totbytesWritten += bytesWritten;
			}
			ioFile.close();
			logger.debug("==========================================================\n" + "  PUT METHOD\n" + "==========================================================\n" + "Wrote file  to BLOB column.\n" + totbytesRead + " bytes read.\n" + totbytesWritten + " bytes written.\n");
			//BLOB end 
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			//	       e.printStackTrace();
			check = false;
			//throw e;
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			logger.debug("SQL:\n" + sqlText);
			//	       e.printStackTrace();
			check = false;
			//throw e;
		}
		if (check) {
			try {
				conn.commit();
			} catch (SQLException e) {
				check = false;
				logger.error(e.getMessage(), e);
			}
		} else {
			try {
				conn.rollback();
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
			}
		}
		return check;
	}

	//Method for DROInspection inserting Files END
	@Deprecated public Connection returnConnection() throws Exception {
		if (!(conn != null && !conn.isClosed())) {
			conn = ConnectionPool.getInstance().getConnection();
		}
		return conn;
	}

	//Method for DROInspection inserting Files END
	@Deprecated public HashMap calculatePayroll(String employeeid, String month) {
		HashMap allcomponent = new HashMap();
		OracleCallableStatement cst = null;
		String stampDuty = null;
		try {
			DBUtility dbUtility = new DBUtility();
			//dbUtility.createCallableStatement("call IGRS_PAYROLL_PKG1.IGRS_PAYROLL_CALC_PROC(?,?,?,?,?,?,?)");
			dbUtility.createOracleCallableStatement("call get_emp_monthly_salary(?,?,?,?,?,?)");
			//cst = dbUtility.oclstmt;
			logger.debug("before setting value");
			logger.debug("before setting value" + dbUtility.oclstmt);
			dbUtility.oclstmt.setString(1, employeeid);
			logger.debug("1");
			dbUtility.oclstmt.setString(2, month);
			logger.debug("2");
			dbUtility.oclstmt.registerOutParameter(3, OracleTypes.NUMBER);
			logger.debug("3");
			dbUtility.oclstmt.registerOutParameter(4, OracleTypes.CURSOR);
			logger.debug("4");
			dbUtility.oclstmt.registerOutParameter(5, OracleTypes.VARCHAR);
			logger.debug("5");
			dbUtility.oclstmt.registerOutParameter(6, OracleTypes.VARCHAR);
			logger.debug("6");
			boolean bol = dbUtility.oclstmt.execute();
			logger.debug("return execute():-" + bol);
			if (!bol) {
				int totalsal = dbUtility.oclstmt.getInt(3);
				//int taxamount = cst.getInt(3);
				//int professiontax = cst.getInt(4);
				ResultSet componentvalues = (ResultSet) dbUtility.oclstmt.getCursor(4);
				String errorcode = dbUtility.oclstmt.getString(5);
				String errormsg = dbUtility.oclstmt.getString(6);
				/*
				StringBuffer componentbuffer=new StringBuffer();
				if(componentvalues!=null){
					Reader reader=componentvalues.getCharacterStream();
					int value;
					while((value=reader.read())!=-1){
					char c=(char)value;
					componentbuffer.append(c);
					}
				}
				*/
				ArrayList componentslist = new ArrayList();
				ArrayList componentlist = null;
				PayrollDTO payrollDTO = null;
				/*
				StringTokenizer stringTokenizersemi=new StringTokenizer(componentbuffer.toString(),";");
				
				while(stringTokenizersemi.hasMoreTokens()){
				String components =stringTokenizersemi.nextToken();
				StringTokenizer stringTokenizer=new StringTokenizer(components,",");
				componentlist=new ArrayList();
				while(stringTokenizer.hasMoreTokens()){					
					String value=stringTokenizer.nextToken();
					logger.debug("the component value is ==== > " + value);
					componentlist.add(value);
				}
				
				componentslist.add(componentlist);
				}
				*/
				logger.debug("before entering the result set for components == > ");
				while (componentvalues.next()) {
					payrollDTO = new PayrollDTO();
					payrollDTO.setComponentid(componentvalues.getString("COMPONENT_ID"));
					payrollDTO.setCoponentname(componentvalues.getString("COMPONENT_NAME"));
					payrollDTO.setSalaryamount(componentvalues.getString("COMPONENT_VALUE"));
					payrollDTO.setComponenttype(componentvalues.getString("COMPONENT_TYPE"));
					componentslist.add(payrollDTO);
					logger.debug("the components list size is  is == > " + componentslist.size());
				}
				//logger.debug("Tax Amount:-"+taxamount);
				logger.debug("TotalSal:-" + totalsal);
				//logger.debug("professional tax:-"+professiontax);
				allcomponent.put("TOTALSAL", String.valueOf(totalsal));
				//allcomponent.put("TAXAMOUNT",String.valueOf(taxamount));
				//allcomponent.put("PROFESSIONTAX",String.valueOf(professiontax));
				allcomponent.put("COMPONENTS", componentslist);
				allcomponent.put("ERRORCODE", errorcode);
				allcomponent.put("ERRORMSG", errormsg);
				Iterator it = componentslist.listIterator();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		logger.debug("the hash map value is == > " + allcomponent.size());
		return allcomponent;
	}

	/*
	 *  Generic method to insert the documents in the database.  
	 */
	@Deprecated public boolean saveDRODocuments(ArrayList columnName, ArrayList columnValue, String tableName, String docFieldName, String txnFieldName, String docTxnId, FormFile fileName, String filePath) {
		boolean check = false;
		String returnVal = null;
		String sqlText = null;
		BLOB image = null;
		int chunkSize;
		byte[] binaryBuffer;
		long position;
		int bytesRead = 0;
		int bytesWritten = 0;
		int totbytesRead = 0;
		int totbytesWritten = 0;
		String strColumnNames = "";
		String strColumnValues = "";
		try {
			//File file = new File(filePath+fileName);
			InputStream ioFile = fileName.getInputStream();
			//new FileInputStream(file);
			st = conn.createStatement();
			for (int i = 0; i < columnName.size(); i++) {
				if (strColumnNames != null && !strColumnNames.equals("")) {
					strColumnNames = strColumnNames + ", " + (String) columnName.get(i);
				} else {
					strColumnNames = (String) columnName.get(i);
				}
			}
			strColumnNames = strColumnNames + ", " + docFieldName + "," + txnFieldName + ",CREATED_DATE";
			for (int i = 0; i < columnValue.size(); i++) {
				if (strColumnValues != null && !strColumnValues.equals("")) {
					strColumnValues = strColumnValues + ", " + "'" + (String) columnValue.get(i) + "'";
				} else {
					strColumnValues = "'" + (String) columnValue.get(i) + "'";
				}
			}
			strColumnValues = strColumnValues + ", EMPTY_BLOB(),'" + docTxnId + "',SYSDATE";
			//BLOB reading as well as writting in to the Database START
			//before that we need to get the sequence number
			//String strSeq =null;
			// rst = st.executeQuery("SELECT IGRS_EMP_COM_DOC_MAP_SEQ.NEXTVAL as seq FROM DUAL");
			//rst.next();
			//strSeq = ((OracleResultSet) rst).getString("seq");
			// logger.debug("Sequint number "+strSeq);
			/*			        sqlText ="INSERT INTO "+tableName
						    		+"(DOCUMENT_TXN_ID,INSP_TXN_ID,DOCUMENT_NAME,"+docFieldName+",DOC_TYPE_ID) "
						    		+" VALUES('"+docTxnId+"','"+inspId+"', '"+fileName+"',EMPTY_BLOB(),'"+docTypeId+"')";
			*/
			sqlText = "INSERT INTO " + tableName + "(" + strColumnNames + ") VALUES (" + strColumnValues + ")";
			logger.debug("before insert query :" + sqlText);
			logger.debug(sqlText);
			int j = st.executeUpdate(sqlText);
			if (j == 0) {
				check = false;
			} else {
				check = true;
			}
			logger.debug("After insert ");
			sqlText = "SELECT " + docFieldName + " FROM  " + tableName + " WHERE  " + txnFieldName + " ='" + docTxnId + "' FOR UPDATE";
			logger.debug("after insert query :" + sqlText);
			/*sqlText = 
			    "SELECT DOCUMENT_OBJ " +
			    "FROM   IGRS_DRO_INSP_DOC_MAP " +
			    "WHERE  DOCUMENT_TXN_ID ='"+docTxnId+"'"+
			    " FOR UPDATE";*/
			// logger.debug("Before update start"+sqlText);
			rst = st.executeQuery(sqlText);
			rst.next();
			image = ((OracleResultSet) rst).getBLOB(docFieldName);
			chunkSize = image.getChunkSize();
			binaryBuffer = new byte[chunkSize];
			position = 1;
			while ((bytesRead = ioFile.read(binaryBuffer)) != -1) {
				bytesWritten = image.putBytes(position, binaryBuffer, bytesRead);
				position += bytesRead;
				totbytesRead += bytesRead;
				totbytesWritten += bytesWritten;
			}
			ioFile.close();
			logger.debug("==========================================================\n" + "  PUT METHOD\n" + "==========================================================\n" + "Wrote file  to BLOB column.\n" + totbytesRead + " bytes read.\n" + totbytesWritten + " bytes written.\n");
			//BLOB end 
		} catch (IOException e) {
			//    logger.debug("Caught I/O Exception: (Write BLOB value - Put Method).");
			logger.error(e.getMessage(), e);
			check = false;
			//throw e;
		} catch (Exception e) {
			//logger.debug("Caught SQL Exception: (Write BLOB value - Put Method).");
			//logger.debug("SQL:\n" + sqlText);
			logger.error(e.getMessage(), e);
			check = false;
			//throw e;
		}
		logger.debug("check:-" + check);
		return check;
	}

	/*
	 *  Generic method to insert the documents in the database.  
	 */
	@Deprecated public boolean saveDRODocuments(ArrayList columnName, ArrayList columnValue, String tableName, String docFieldName, String txnFieldName, String docTxnId, String fileName, String filePath) {
		boolean check = true;
		String returnVal = null;
		String sqlText = null;
		BLOB image = null;
		int chunkSize;
		byte[] binaryBuffer;
		long position;
		int bytesRead = 0;
		int bytesWritten = 0;
		int totbytesRead = 0;
		int totbytesWritten = 0;
		String strColumnNames = "";
		String strColumnValues = "";
		try {
			File file = new File(filePath + fileName);
			InputStream ioFile = new FileInputStream(file);
			st = conn.createStatement();
			for (int i = 0; i < columnName.size(); i++) {
				if (strColumnNames != null && !strColumnNames.equals("")) {
					strColumnNames = strColumnNames + ", " + (String) columnName.get(i);
				} else {
					strColumnNames = (String) columnName.get(i);
				}
			}
			strColumnNames = strColumnNames + ", " + docFieldName;
			for (int i = 0; i < columnValue.size(); i++) {
				if (strColumnValues != null && !strColumnValues.equals("")) {
					strColumnValues = strColumnValues + ", " + "'" + (String) columnValue.get(i) + "'";
				} else {
					strColumnValues = "'" + (String) columnValue.get(i) + "'";
				}
			}
			strColumnValues = strColumnValues + ", EMPTY_BLOB()";
			//BLOB reading as well as writting in to the Database START
			//before that we need to get the sequence number
			//String strSeq =null;
			// rst = st.executeQuery("SELECT IGRS_EMP_COM_DOC_MAP_SEQ.NEXTVAL as seq FROM DUAL");
			//rst.next();
			//strSeq = ((OracleResultSet) rst).getString("seq");
			// logger.debug("Sequint number "+strSeq);
			/*			        sqlText ="INSERT INTO "+tableName
						    		+"(DOCUMENT_TXN_ID,INSP_TXN_ID,DOCUMENT_NAME,"+docFieldName+",DOC_TYPE_ID) "
						    		+" VALUES('"+docTxnId+"','"+inspId+"', '"+fileName+"',EMPTY_BLOB(),'"+docTypeId+"')";
			*/
			sqlText = "INSERT INTO " + tableName + "(" + strColumnNames + ") VALUES (" + strColumnValues + ")";
			logger.debug("before insert query :" + sqlText);
			st.executeUpdate(sqlText);
			logger.debug("After insert ");
			sqlText = "SELECT " + docFieldName + " FROM  " + tableName + " WHERE  " + txnFieldName + " ='" + docTxnId + "' FOR UPDATE";
			logger.debug("after insert query :" + sqlText);
			/*sqlText = 
			    "SELECT DOCUMENT_OBJ " +
			    "FROM   IGRS_DRO_INSP_DOC_MAP " +
			    "WHERE  DOCUMENT_TXN_ID ='"+docTxnId+"'"+
			    " FOR UPDATE";*/
			// logger.debug("Before update start"+sqlText);
			rst = st.executeQuery(sqlText);
			rst.next();
			image = ((OracleResultSet) rst).getBLOB(docFieldName);
			chunkSize = image.getChunkSize();
			binaryBuffer = new byte[chunkSize];
			position = 1;
			while ((bytesRead = ioFile.read(binaryBuffer)) != -1) {
				bytesWritten = image.putBytes(position, binaryBuffer, bytesRead);
				position += bytesRead;
				totbytesRead += bytesRead;
				totbytesWritten += bytesWritten;
			}
			ioFile.close();
			logger.debug("==========================================================\n" + "  PUT METHOD\n" + "==========================================================\n" + "Wrote file  to BLOB column.\n" + totbytesRead + " bytes read.\n" + totbytesWritten + " bytes written.\n");
			//BLOB end 
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			check = false;
			//throw e;
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			check = false;
			//throw e;
		}
		if (check) {
			try {
				conn.commit();
				File newFile1 = new File(filePath + fileName);
				newFile1.delete();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				check = false;
				logger.error(e.getMessage(), e);
			}
		} else {
			try {
				conn.rollback();
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
			}
		}
		return check;
	}

	/**
	 * Method : executeCallableQuery Description : Execute a query with
	 * CallableStatement
	 * 
	 * @param arr : String []
	 * @return list : ArrayList
	 * @throws : Exception
	 * @author ROOPAM
	 */
	@Deprecated public String insertEmailData(String userId, String subject, String content) throws Exception {
		logger.debug("DBUtility - insertEmailData()");
		String status = new String();
		try {
			clstmt.setString(1, userId);
			clstmt.setString(2, subject);
			clstmt.setString(3, content);
			clstmt.registerOutParameter(4, Types.VARCHAR);
			clstmt.execute();
			status = clstmt.getString(4);
			logger.debug("Type:-" + status);
		} catch (SQLException sqle) {
			logger.error(sqle.getMessage(), sqle);
			throw sqle;
		} catch (NumberFormatException nfe) {
			logger.error(nfe.getMessage(), nfe);
			throw nfe;
		} catch (IllegalArgumentException iae) {
			logger.error(iae.getMessage(), iae);
			throw iae;
		}
		return status;
	}

	/**
	 * Method : executeCallableQuery Description : Execute a query with
	 * CallableStatement
	 * 
	 * @param arr : Int []
	 * @return list : ArrayList
	 * @throws : Exception
	 * @author Rishab
	 */
	@Deprecated public ArrayList executeQuery(int[] arr) throws Exception {
		logger.debug("DBUtility - executeQuery()");
		ArrayList list = new ArrayList();
		try {
			for (int i = 0; i < arr.length; i++) {
				logger.debug("Wipro : arr[" + i + "] = " + arr[i]);
				//   pst.setString(i + 1, arr[i]);
				pst.setInt(i + 1, arr[i]);
			}
			rst = pst.executeQuery();
			logger.debug("Wipro in DBUtility - executeQuery() after pst.executeQuery()");
			ResultSetMetaData rsmd = rst.getMetaData();
			int col_count = rsmd.getColumnCount();
			while (rst.next()) {
				ArrayList row_list = new ArrayList();
				for (int i = 1; i <= col_count; i++) {
					row_list.add(rst.getString(i));
				} //for
				list.add(row_list);
			} //while
			pst.clearParameters();
		} catch (SQLException sqle) {
			logger.error(sqle.getMessage(), sqle);
			throw sqle;
		} catch (NumberFormatException nfe) {
			logger.error(nfe.getMessage(), nfe);
			throw nfe;
		} catch (IllegalArgumentException iae) {
			logger.error(iae.getMessage(), iae);
			throw iae;
		}
		list.trimToSize();
		return list;
	}

	//
	@Deprecated public ArrayList<Map<String, String>> getCompleteMenuDetails(String[] roles) {
		ArrayList<Map<String, String>> retVal = null;
		try {
			Map<String, String> mdlMap = new HashMap<String, String>(50, 25);
			Map<String, String> smlMap = new HashMap<String, String>(50, 25);
			Map<String, String> fncMap = new HashMap<String, String>(50, 25);
			Map<String, String> actMap = new HashMap<String, String>(50, 25);
			retVal = new ArrayList<Map<String, String>>(4);
			createStatement();
			StringBuilder stBldr = new StringBuilder();
			String sqlText = "";
			stBldr.append(CommonSQL.SEARCH_ROLE_MODULE);
			stBldr.append(" AND RFN.ROLE_ID IN ( ");
			stBldr.append(buildInClause(roles));
			stBldr.append(" ) ");
			sqlText = stBldr.toString();
			logger.info("Getting modules");
			logger.debug("SQL is : " + sqlText);
			buildMenuMap(mdlMap, sqlText);
			logger.info("Module Count " + mdlMap.keySet().size());
			stBldr.delete(0, stBldr.length());
			stBldr.append(CommonSQL.SEARCH_SUB_MODULE);
			stBldr.append(" AND RFN.MODULE_ID IN ( ");
			stBldr.append(buildInClause(mdlMap.keySet().toArray(new String[0])));
			stBldr.append(" ) ");
			stBldr.append(" AND RFN.ROLE_ID IN ( ");
			stBldr.append(buildInClause(roles));
			stBldr.append(" ) ");
			sqlText = stBldr.toString();
			logger.info("Getting sub modules");
			logger.debug("SQL is : " + sqlText);
			buildMenuMap(smlMap, sqlText);
			logger.info("Sub Module Count " + smlMap.keySet().size());
			stBldr.delete(0, stBldr.length());
			stBldr.append(CommonSQL.SEARCH_FUNCTION);
			stBldr.append(" AND RFN.SUB_MODULE_ID IN ( ");
			stBldr.append(buildInClause(smlMap.keySet().toArray(new String[0])));
			stBldr.append(" ) ");
			stBldr.append(" AND RFN.MODULE_ID IN ( ");
			stBldr.append(buildInClause(mdlMap.keySet().toArray(new String[0])));
			stBldr.append(" ) ");
			stBldr.append(" AND RFN.ROLE_ID IN ( ");
			stBldr.append(buildInClause(roles));
			stBldr.append(" ) ");
			sqlText = stBldr.toString();
			logger.info("Getting Function");
			logger.debug("SQL is : " + sqlText);
			buildMenuMap(fncMap, sqlText);
			logger.info("Function Count " + fncMap.keySet().size());
			stBldr.delete(0, stBldr.length());
			stBldr.append(CommonSQL.SEARCH_ACTIVITIES);
			stBldr.append(" AND RFN.FUNCTION_ID IN ( ");
			stBldr.append(buildInClause(fncMap.keySet().toArray(new String[0])));
			stBldr.append(" ) ");
			stBldr.append(" AND RFN.SUB_MODULE_ID IN ( ");
			stBldr.append(buildInClause(smlMap.keySet().toArray(new String[0])));
			stBldr.append(" ) ");
			stBldr.append(" AND RFN.MODULE_ID IN ( ");
			stBldr.append(buildInClause(mdlMap.keySet().toArray(new String[0])));
			stBldr.append(" ) ");
			stBldr.append(" AND RFN.ROLE_ID IN ( ");
			stBldr.append(buildInClause(roles));
			stBldr.append(" ) ");
			sqlText = stBldr.toString();
			logger.info("Getting Activities");
			logger.debug("SQL is : " + sqlText);
			buildMenuMap(actMap, sqlText);
			logger.info("Activity Count " + actMap.keySet().size());
			// MDL.MODULE_ID, MDL.MODULE_NAME,
			// SML.SUB_MODULE_ID, SML.SUB_MODULE_NAME,
			// FNC.FUNCTION_ID, FNC.FUNCTION_NAME,
			// ACT.ACTIVITY_ID, ACT.ACTIVITY_NAME
			retVal.add(mdlMap);
			retVal.add(smlMap);
			retVal.add(fncMap);
			retVal.add(actMap);
			closeConnection();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return retVal;
	}

	/**
	 * @param genMap
	 * @param sqlText
	 * @throws SQLException
	 */
	@Deprecated private void buildMenuMap(Map<String, String> genMap, String sqlText) {
		try {
			try {
				rst = st.executeQuery(sqlText);
				while (rst.next()) {
					genMap.put(rst.getString(1), rst.getString(2));
				}
				rst.close();
			} catch (Exception e) {
				logger.error(e.getMessage());
			} finally {
				rst.close();
				//				st.close();
			}
		} catch (Exception e) {
			logger.error("Error occured while cleaning ResultSet " + e.getMessage());
		}
	}

	@Deprecated private String buildInClause(String[] params) {
		StringBuilder stBldr = new StringBuilder();
		for (int iLoop = 0; iLoop < params.length; iLoop++) {
			stBldr.append("\'");
			stBldr.append(params[iLoop]);
			stBldr.append("\'");
			if (iLoop < (params.length - 1)) {
				stBldr.append(", ");
			}
		}
		return stBldr.toString();
	}

	//This method is used for reading the BLOB containet and making file to show int the UI START
	/**
	 * Method used to write the contents (data) from an Oracle BLOB column to an
	 * O/S file. This method uses one of two ways to get data from the BLOB
	 * column - namely the getBytes() method. The other way to read data from an
	 * Oracle BLOB column is to use Streams.
	 * 
	 * @throws java.io.IOException
	 * @throws java.sql.SQLException
	 */
	@Deprecated public void readBLOBToFileGetForCopyIssuance(HttpServletResponse res, String strDocId, String strFunctionName) throws IOException, SQLException {
		String sqlText = null;
		BLOB image = null;
		String contentName = null;
		OutputStream os = null;
		String ext = null;
		try {
			st = conn.createStatement();
			String docObject = "document_object";
			String docName = "document_name";
			if (strFunctionName.equalsIgnoreCase("REGCOPYREQ")) {
				//docName="DOCUMENT_NAME";
				docObject = "PARTY_THUMB_IMPRESSION";
				sqlText = "SELECT " + docObject + " FROM IGRS_CERCOPY_TXN_PARTY_DETAILS" + " WHERE CER_COPY_TXN_ID='" + strDocId + "'" + " FOR UPDATE";
			}
			logger.debug("before excuting :" + sqlText);
			rst = st.executeQuery(sqlText);
			//    logger.debug("After eceutingn ");
			//rst.next();
			//image = ((OracleResultSet) rst).getBLOB("document_object");
			while (rst.next()) {
				image = ((OracleResultSet) rst).getBLOB(docObject);
				// contentName = ((OracleResultSet) rst).getString(docName);
				contentName = "Feather-Pen-vector.jpg";
			}
			//logger.debug("Content Name :"+contentName);
			os = res.getOutputStream();
			long index = 1;
			if (contentName != null) {
				//logger.debug(" Before exception ");
				ext = contentName.substring(contentName.length() - 3, contentName.length());
				//logger.debug(" ext "+ext);
				if (ext != null) {
					if (ext.equalsIgnoreCase("doc")) {
						res.setContentType("application/download");
					} else if (ext.equalsIgnoreCase("ppt")) {
						res.setContentType("application/download");
					} else if (ext.equalsIgnoreCase("xls")) {
						res.setContentType("application/download");
					} else if (ext.equalsIgnoreCase("htm")) {
						res.setContentType("text/html");
					} else if (ext.equalsIgnoreCase("tml")) {
						res.setContentType("text/html");
					} else if (ext.equalsIgnoreCase("txt")) {
						res.setContentType("text/plain");
					} else if (ext.equalsIgnoreCase("pdf")) {
						res.setContentType("application/pdf");
					} else if (ext.equalsIgnoreCase("bmp")) {
						res.setContentType("image/x-bmp");
					} else if (ext.equalsIgnoreCase("gif")) {
						res.setContentType("image/gif");
					} else if (ext.equalsIgnoreCase("jpg")) {
						res.setContentType("image/jpeg");
					} else if (ext.equalsIgnoreCase("peg")) {
						res.setContentType("image/jpeg");
					} else {
						res.setContentType("application/download");
					}
				}
			}
			res.setHeader("Content-Disposition", "attachment; filename=" + contentName);
			// Loop through while reading a data from the BLOB
			// column using the getBytes() method. This data will be stored
			// in a BLOB column and writting in OutPutStream.
			while (index < image.length()) {
				os.write(image.getBytes(index, 30000));
				os.flush();
				index += 10000;
			}
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	//BLOB Regading END
	public byte[] getByteArrayForBLOB(String query, Object docID) throws Exception {
		byte[] retVal = null;
		try {
			PreparedStatement prepStmt = returnPreparedStatement(query);
			prepStmt.setObject(1, docID);
			ResultSet rset = prepStmt.executeQuery();
			rset.next();
			Blob bl = rset.getBlob(1);
			retVal = bl.getBytes(1, (int) bl.length());
			rset.close();
			prepStmt.close();
		} catch (Exception e) {
			throw e;
		}
		return retVal;
	}

	@Deprecated public void downloadByteArrayAsFile(HttpServletResponse res, byte[] content, String fileName) {
		try {
			OutputStream os = res.getOutputStream();
			long index = 1;
			res.setContentType("application/download");
			res.setHeader("Content-Disposition", "attachment; filename=" + fileName);
			os.write(content);
			os.flush();
			os.close();
		} catch (Exception e) {}
	}

	@Deprecated public boolean attachEmpOfficialDocs(String empID, String doctype, String fileName, byte[] docContents, String userId) throws Exception {
		boolean check = false;
		try {
			String sqlQuery = "INSERT INTO IGRS_EMP_DOCUMENT_DETAILS " + "( EMP_ID, EMP_DOC_TYPE_ID, EMP_DOC_STATUS, CREATED_BY, " + " UPDATE_BY, DOCUMENT_NAME, EMP_DOC_VALUE, CREATED_DATE, UPDATE_DATE, DOCUMENT_ID ) " + " VALUES ( ?, ?, ?, ?, ?, ?, ?, SYSDATE, SYSDATE, IGRS_EMP_DOCUMENT_DETAILS_SEQ.NEXTVAL )";
			PreparedStatement prepStmt = conn.prepareStatement(sqlQuery);
			prepStmt.setString(1, empID); // EMP_ID
			prepStmt.setString(2, doctype);// EMP_DOC_TYPE_ID
			prepStmt.setString(3, "A");// EMP_DOC_STATUS
			prepStmt.setString(4, userId);// CREATED_BY
			prepStmt.setString(5, userId);// UPDATE_BY
			prepStmt.setString(6, fileName);// DOCUMENT_NAME
			prepStmt.setBinaryStream(7, new ByteArrayInputStream(docContents), docContents.length);// EMP_DOC_VALUE
			int rowCount = prepStmt.executeUpdate();
			logger.info("Rowcount : " + rowCount);
			prepStmt.close();
			check = true;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return check;
	}

	@Deprecated public boolean insertCaveatData(CaveatsDTO dto, String userId, String functionId, String payAmount) {
		boolean retVal;
		int rowCount;
		try {
			PreparedStatement prepStmt;
			String caveatTxnID = null;
			Statement stmt;
			ResultSet rset;
			DBUtility dbUtil;
			String sqlQuery = "";
			String payQuery = "";
			String payid;
			///////
			String[] insrArray = new String[5];
			////
			try {
				dbUtil = new DBUtility();
				dbUtil.setAutoCommit(false);
				String val = dto.getCaveatType();
				String str = val.substring(val.length() - 2);
				String str1 = "";
				String SQL2 = "select IGRS_CAVEAT_MASTER_SEQ.nextval from dual";
				dbUtil.createStatement();
				String number2 = dbUtil.executeQry(SQL2);
				if (number2.length() == 1) {
					number2 = "00000" + number2;
				} else if (number2.length() == 2) {
					number2 = "0000" + number2;
				} else if (number2.length() == 3) {
					number2 = "000" + number2;
				} else if (number2.length() == 4) {
					number2 = "00" + number2;
				} else if (number2.length() == 5) {
					number2 = "0" + number2;
				}
				/*    if(str.equals("01"))
				       {
				    	   str1="C" ;
				       }
				        
				       else if(str.equals("02"))
				       {
					    	   str1="B" ;
				       }
				       else if(str.equals("03"))
				       {
				    	    str1="O";
				       }
				       */
				Date date = new Date();
				Format yearformat = new SimpleDateFormat("yy");
				Format monthformat = new SimpleDateFormat("MM");
				Format dateformat = new SimpleDateFormat("dd");
				String dfmt = dateformat.format(date);
				String yfmt = yearformat.format(date);
				String mfmt = monthformat.format(date);
				caveatTxnID = "CTD" + str + dfmt + mfmt + yfmt + number2;
				System.out.println(caveatTxnID);
				String SQL3 = "SELECT IGRS_CAVEAT_PAYMENT_SEQ.NEXTVAL FROM DUAL";
				dbUtil.createStatement();
				String reqNo = dbUtil.executeQry(SQL3);
				////
				/*	try {
							if(con == null || con.isClosed()) {
								con = returnConnection();
							}
							con.setAutoCommit(false);
							sqlQuery = com.wipro.igrs.caveats.sql.CommonSQL.GET_CAVEATS_TXN_ID;
							stmt = con.createStatement();
							rset = stmt.executeQuery(sqlQuery);

							logger.debug("Execute query " + sqlQuery);
							
							if(rset.next()) {
								caveatTxnID = rset.getString(1);
							}
							stmt.close();
					*/
				dto.setReferenceID(caveatTxnID);
				dto.setReqNum(reqNo);
				//dto.setReferenceID(caveatID);
				logger.debug("Caveat Transaction Id" + caveatTxnID);
				//payQuery = com.wipro.igrs.caveats.sql.CommonSQL.CAVEATS_PAYMENT_TXN_ID;
				//payid = executeQry(payQuery);
				logger.debug("Caveat -- Payment  Transaction Id" + caveatTxnID);
				insrArray[0] = reqNo;
				insrArray[1] = caveatTxnID;
				insrArray[2] = "I";
				insrArray[3] = functionId;
				insrArray[4] = userId;
				//insrArray[4]= "50";
				logger.debug("functionId " + functionId);
				logger.debug("userId " + userId);
				try {
					sqlQuery = com.wipro.igrs.caveats.sql.CommonSQL.CAVETAS_PAYTXN_INSERT;
					logger.debug("Execute query " + sqlQuery);
					createPreparedStatement(sqlQuery);
					executeUpdate(insrArray);
					//logger.debug("Caveat -- Payment  query executed " + 	executeUpdate(insrArray));
				} catch (Exception e) {
					logger.debug(e);
				}
				sqlQuery = com.wipro.igrs.caveats.sql.CommonSQL.LOG_CAVEATS_NONBANK;
				prepStmt = conn.prepareStatement(sqlQuery);
				prepStmt.setString(1, dto.getReferenceID());
				prepStmt.setString(2, dto.getBankCourtName());
				prepStmt.setString(3, dto.getBankCourtAddress());
				prepStmt.setString(4, dto.getBankCourtRepName());
				prepStmt.setString(5, dto.getCountryId());
				prepStmt.setString(6, dto.getStateId());
				prepStmt.setString(7, dto.getDistrictId());
				prepStmt.setString(8, dto.getBankCourtPostalCode());
				prepStmt.setString(9, dto.getBankCourtPhoneNumber());
				prepStmt.setString(10, dto.getCaseNum());
				if (dto.getCourtOrderDate() != null) {
					prepStmt.setString(11, CommonUtil.getConvertedDate(dto.getCourtOrderDate()));
				} else {
					prepStmt.setString(11, null);
				}
				prepStmt.setString(12, dto.getCaveatId());
				prepStmt.setString(13, dto.getStayOrderNo());
				prepStmt.setString(14, dto.getStayOrderDetails());
				prepStmt.setString(15, dto.getCaveatDetails());
				prepStmt.setString(16, dto.getDocumentName());
				prepStmt.setBinaryStream(17, new ByteArrayInputStream(dto.getDocContents()), dto.getDocContents().length);
				prepStmt.setString(18, dto.getRemarks());
				prepStmt.setString(19, dto.getRegistrationNumber());
				prepStmt.setString(20, "INITIATED");
				prepStmt.setString(21, dto.getLoggedIn());
				if (dto.getStayOrderStartDate() != null) {
					prepStmt.setString(22, CommonUtil.getConvertedDate(dto.getStayOrderStartDate()));
				} else {
					prepStmt.setString(22, null);
				}
				if (dto.getStayOrderUptoDate() != null) {
					prepStmt.setString(23, CommonUtil.getConvertedDate(dto.getStayOrderUptoDate()));
				} else {
					prepStmt.setString(23, null);
				}
				prepStmt.setString(24, dto.getPropertyTxnLock());
				prepStmt.setInt(25, Integer.parseInt(payAmount));
				rowCount = prepStmt.executeUpdate();
				logger.debug("Statement " + prepStmt);
				logger.debug("Logged Caveat : RowCount " + rowCount);
				prepStmt.close();
				sqlQuery = com.wipro.igrs.caveats.sql.CommonSQL.LOG_CAVEATS_PROPERTY_MAP;
				logger.debug("Caveat Property Map total count " + dto.getSelectedItems().size());
				prepStmt = conn.prepareStatement(sqlQuery);
				for (Object selectedItem : dto.getSelectedItems()) {
					CaveatsDTO selectedProp = (CaveatsDTO) selectedItem;
					selectedProp.setReferenceID(dto.getReferenceID());
					prepStmt.setString(1, selectedProp.getReferenceID());
					prepStmt.setString(2, selectedProp.getPropertyTxnId());
					rowCount = prepStmt.executeUpdate();
					logger.debug("Logged Caveat Property Map: RowCount " + rowCount);
				}
				prepStmt.close();
				conn.commit();
				retVal = true;
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				retVal = false;
				conn.rollback();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			retVal = false;
		}
		return retVal;
	}

	//ADDED BY ASHIMA  
	@Deprecated public String convertDOB2(Object date) throws Exception {
		//String sysdate=commonBd.getCurrDateTime();
		//String transDate1=paymentBD.gettranDate(eForm);
		SimpleDateFormat date1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat date2 = new SimpleDateFormat("dd/MM/yyyy");
		Date d1 = date1.parse(date.toString());
		String formatDate = date2.format(d1);
		System.out.println("formatted date=----->" + formatDate);
		return formatDate;
	}

	//
	@Deprecated public ArrayList getCaveatDetails(String query, String[] params, String language) {
		ArrayList retVal = null;
		PreparedStatement prepStmt;
		ResultSet rset;
		CaveatsDTO dto;
		Blob doc;
		try {
			try {
				if (conn == null || conn.isClosed()) {
					conn = returnConnection();
				}
				prepStmt = conn.prepareStatement(query);
				for (int iLoop = 0; iLoop < params.length; iLoop++) {
					prepStmt.setString((iLoop + 1), params[iLoop]);
				}
				rset = prepStmt.executeQuery();
				retVal = new ArrayList();
				while (rset.next()) {
					dto = new CaveatsDTO();
					dto.setReferenceID(rset.getString(1));
					dto.setBankCourtName(rset.getString(2));
					dto.setBankCourtAddress(rset.getString(3));
					dto.setBankCourtRepName(rset.getString(4));
					dto.setCountryId(rset.getString(5));
					dto.setStateId(rset.getString(6));
					dto.setDistrictId(rset.getString(7));
					dto.setBankCourtPostalCode(rset.getString(8));
					dto.setBankCourtPhoneNumber(rset.getString(9));
					dto.setCaveatId(rset.getString(10));
					dto.setCaveatType(rset.getString(10));
					dto.setStayOrderNo(rset.getString(11));
					dto.setStayOrderDetails(rset.getString(12));
					dto.setCaveatDetails(rset.getString(13));
					dto.setDocumentName(rset.getString(14));
					dto.setUploaded_doc_path(rset.getString(15));
					//doc = rset.getBlob(15);
					//	if(doc != null) {
					//	dto.setDocContents(doc.getBytes(1, (int)doc.length()));
					//}
					dto.setRemarks(rset.getString(16));
					dto.setRegistrationNumber(rset.getString(17));
					if ("en".equalsIgnoreCase(language)) {
						if (rset.getString(18).equalsIgnoreCase("INITIATED")) {
							dto.setFlag("INITIATED");
							// dto.setFlag(rset.getString(18).equalsIgnoreCase("INITIATED")?"INITIATED":"LOGGED");
						} else {
							dto.setFlag(rset.getString(18).equalsIgnoreCase("LOGGED") ? "LOGGED" : "RELEASED");
						}
					} else {
						if (rset.getString(18).equalsIgnoreCase("INITIATED")) {
							dto.setFlag("शुरू किया गया है ");
							// dto.setFlag(rset.getString(18).equalsIgnoreCase("INITIATED")?"INITIATED":"LOGGED");
						} else {
							dto.setFlag(rset.getString(18).equalsIgnoreCase("LOGGED") ? "लॉग किया गया है " : "रिलीज किया गया है");
						}
					}
					//dto.setLoggedDate(rset.getString(19));
					//modified by ashima
					dto.setLoggedDate(convertDOB2(rset.getString(19)));
					//
					dto.setReleaseDate(rset.getString(20));
					dto.setReasonForRelease(rset.getString(21));
					dto.setRemarksForRelease(rset.getString(22));
					//					dto.setReasonForRelease(rset.getString(23));
					//					dto.setReasonForRelease(rset.getString(24));
					//					dto.setReasonForRelease(rset.getString(25));
					//					dto.setReasonForRelease(rset.getString(26));
					dto.setCaveatLabel(rset.getString(27));
					dto.setBankCourtCountry(rset.getString(28));
					dto.setBankCourtStateName(rset.getString(29));
					dto.setCityDistrict(rset.getString(30));
					dto.setStayOrderStartDate(rset.getString(31));
					dto.setStayOrderUptoDate(rset.getString(32));
					doc = rset.getBlob(33);
					if (doc != null) {
						dto.setReleaseContents(doc.getBytes(1, (int) doc.length()));
					}
					dto.setPropertyTxnLock(rset.getString(34));
					dto.setReleaseDocName(rset.getString(35));
					dto.setSerialNo(retVal.size() + 1);
					retVal.add(dto);
				}
				rset.close();
				prepStmt.close();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return retVal;
	}

	@Deprecated public ArrayList getSearchDetails(String query, String[] params, String language) {
		ArrayList retVal = null;
		PreparedStatement prepStmt;
		ResultSet rset;
		CitizenFeedbackDTO dto;
		try {
			try {
				if (conn == null || conn.isClosed()) {
					conn = returnConnection();
				}
				prepStmt = conn.prepareStatement(query);
				for (int iLoop = 0; iLoop < params.length; iLoop++) {
					prepStmt.setString((iLoop + 1), params[iLoop]);
				} //---added by satbir kumar----
				rset = prepStmt.executeQuery();
				retVal = new ArrayList();
				while (rset.next()) {
					dto = new CitizenFeedbackDTO();
					dto.setReferenceId(rset.getString(1));
					dto.setHdnreferenceId(rset.getString(1));
					dto.setFirstName(rset.getString(2));
					dto.setEmailID(rset.getString(3));
					dto.setCreatedDate(rset.getString(4));
					if ("en".equalsIgnoreCase(language)) {
						dto.setType(rset.getString(5));
					} else if ("hi".equalsIgnoreCase(language)) {
						dto.setType(rset.getString(5).equalsIgnoreCase("FEEDBACK") ? "प्रतिक्रिया " : "शिकायत ");
					}
					if ("en".equalsIgnoreCase(language)) {
						dto.setFeedbackFunctionName(rset.getString(6));
					} else if ("hi".equalsIgnoreCase(language)) {
						dto.setFeedbackFunctionName(rset.getString(8));
					}
					if ("en".equalsIgnoreCase(language)) {
						dto.setStatus(rset.getString(7));
					} else if ("hi".equalsIgnoreCase(language)) {
						dto.setStatus(rset.getString(7).equalsIgnoreCase("Pending") ? "लंबित" : "संपन्न");
					}
					retVal.add(dto);
				}
				rset.close();
				prepStmt.close();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return retVal;
	}

	// for cash/ challan/online
	@Deprecated public ArrayList getSearchDetails1(String query, String[] params, String languageLocale) {
		String paymentTypeName = "";
		if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
			if ((params[0]).equals("1")) {
				paymentTypeName = "Cash";
			}
			if ((params[0]).equals("2")) {
				paymentTypeName = "Challan";
			}
			if ((params[0]).equals("4")) {
				paymentTypeName = "Credit Limit";
			}
			if ((params[0]).equals("3")) {
				paymentTypeName = "Online";
			}
		} else {
			if ((params[0]).equals("1")) {
				paymentTypeName = CommonConstant.CASH_HINDI;
			}
			if ((params[0]).equals("2")) {
				paymentTypeName = CommonConstant.CHALLAN_HINDI;
			}
			if ((params[0]).equals("4")) {
				paymentTypeName = CommonConstant.CREDIT_LIMIT_HINDI;
			}
			if ((params[0]).equals("3")) {
				paymentTypeName = CommonConstant.ONLINE_HINDI;
			}
		}
		ArrayList retVal = null;
		PreparedStatement prepStmt;
		ResultSet rset;
		RevMgtDTO dto;
		try {
			try {
				if (conn == null || conn.isClosed()) {
					conn = returnConnection();
				}
				prepStmt = conn.prepareStatement(query);
				for (int iLoop = 0; iLoop < params.length; iLoop++) {
					prepStmt.setString((iLoop + 1), params[iLoop]);
				} //---added by Rachita----
				rset = prepStmt.executeQuery();
				retVal = new ArrayList();
				while (rset.next()) {
					dto = new RevMgtDTO();
					dto.setTxnId(rset.getString(1));
					//dto.setTxnAmt(Double.parseDouble((String)(rset.getString(2))));
					dto.setTxnAmt(rset.getString(2));
					dto.setTxnDate(rset.getString(3));
					dto.setUserId(rset.getString(5));
					dto.setReceiptId(rset.getString(11));
					Double txnAmt = (Double.parseDouble((String) rset.getString(2)));
					if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
						dto.setTxnType(rset.getString(4));
						dto.setTxnPurpose(rset.getString(8));
						dto.setOfficeName(rset.getString(9));
						dto.setDistrictName(rset.getString(10));
						dto.setViewComb(rset.getString(1) + "~" + txnAmt + "~" + rset.getString(3) + "~" + paymentTypeName + "~" + rset.getString(5) + "~" + rset.getString(8) + "~" + rset.getString(9) + "~" + rset.getString(10) + "~" + rset.getString(11));
					} else {
						dto.setTxnType(rset.getString(4));
						dto.setTxnPurpose(rset.getString(12));
						dto.setOfficeName(rset.getString(13));
						dto.setDistrictName(rset.getString(14));
						dto.setViewComb(rset.getString(1) + "~" + txnAmt + "~" + rset.getString(3) + "~" + paymentTypeName + "~" + rset.getString(5) + "~" + rset.getString(12) + "~" + rset.getString(13) + "~" + rset.getString(14) + "~" + rset.getString(11));
					}
					retVal.add(dto);
				}
				rset.close();
				prepStmt.close();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return retVal;
	}

	// for cash/ challan/online
	@Deprecated public ArrayList getSearchDetails2(String query, String[] params, String languageLocale) {
		String paymentTypeName = "";
		if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
			if ((params[0]).equals("1")) {
				paymentTypeName = "Cash";
			}
			if ((params[0]).equals("2")) {
				paymentTypeName = "Challan";
			}
			if ((params[0]).equals("4")) {
				paymentTypeName = "Credit Limit";
			}
			if ((params[0]).equals("3")) {
				paymentTypeName = "Online";
			}
		} else {
			if ((params[0]).equals("1")) {
				paymentTypeName = CommonConstant.CASH_HINDI;
			}
			if ((params[0]).equals("2")) {
				paymentTypeName = CommonConstant.CHALLAN_HINDI;
			}
			if ((params[0]).equals("4")) {
				paymentTypeName = CommonConstant.CREDIT_LIMIT_HINDI;
			}
			if ((params[0]).equals("3")) {
				paymentTypeName = CommonConstant.ONLINE_HINDI;
			}
		}
		ArrayList retVal = null;
		PreparedStatement prepStmt;
		ResultSet rset;
		RevMgtDTO dto;
		try {
			try {
				if (conn == null || conn.isClosed()) {
					conn = returnConnection();
				}
				prepStmt = conn.prepareStatement(query);
				for (int iLoop = 0; iLoop < params.length; iLoop++) {
					prepStmt.setString((iLoop + 1), params[iLoop]);
				} //---added by Rachita----
				rset = prepStmt.executeQuery();
				retVal = new ArrayList();
				String receiptId = "-";
				while (rset.next()) {
					dto = new RevMgtDTO();
					dto.setTxnId(rset.getString(1));
					//dto.setTxnAmt(Double.parseDouble((String)(rset.getString(2))));
					dto.setTxnAmt(rset.getString(2));
					dto.setTxnDate(rset.getString(3));
					dto.setUserId(rset.getString(5));
					dto.setReceiptId("-");
					Double txnAmt = (Double.parseDouble((String) rset.getString(2)));
					if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
						dto.setTxnType(rset.getString(4));
						dto.setTxnPurpose(rset.getString(8));
						dto.setOfficeName(rset.getString(9));
						dto.setDistrictName(rset.getString(10));
						dto.setViewComb(rset.getString(1) + "~" + txnAmt + "~" + rset.getString(3) + "~" + paymentTypeName + "~" + rset.getString(5) + "~" + rset.getString(8) + "~" + rset.getString(9) + "~" + rset.getString(10) + "~" + receiptId);
					} else {
						dto.setTxnType(rset.getString(4));
						dto.setTxnPurpose(rset.getString(11));
						dto.setOfficeName(rset.getString(12));
						dto.setDistrictName(rset.getString(13));
						dto.setViewComb(rset.getString(1) + "~" + txnAmt + "~" + rset.getString(3) + "~" + paymentTypeName + "~" + rset.getString(5) + "~" + rset.getString(11) + "~" + rset.getString(12) + "~" + rset.getString(13) + "~" + receiptId);
					}
					retVal.add(dto);
				}
				rset.close();
				prepStmt.close();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return retVal;
	}

	@Deprecated public boolean releaseCaveat(String query, CaveatsDTO caveatDTO) throws Exception {
		if (conn == null || conn.isClosed()) {
			logger.debug("Getting connection");
			conn = returnConnection();
		}
		PreparedStatement prepStmt = conn.prepareStatement(query);
		prepStmt.setString(1, caveatDTO.getReasonForRelease());
		prepStmt.setString(2, caveatDTO.getRemarksForRelease());
		prepStmt.setString(3, caveatDTO.getLoggedIn());
		if (caveatDTO.getReleaseContents() != null) {
			prepStmt.setString(4, caveatDTO.getReleaseDocName());
			prepStmt.setBinaryStream(5, new ByteArrayInputStream(caveatDTO.getReleaseContents()), caveatDTO.getReleaseContents().length);
		} else {
			prepStmt.setString(4, "");
			prepStmt.setObject(5, null);
		}
		prepStmt.setString(6, caveatDTO.getReferenceID());
		int rowcount = prepStmt.executeUpdate();
		prepStmt.close();
		logger.debug("Rowcount : " + rowcount);
		return (rowcount == 1);
	}

	@Deprecated public boolean flagUpdate(String query, CaveatsDTO caveatDTO) throws Exception {
		if (conn == null || conn.isClosed()) {
			logger.debug("Getting connection");
			conn = returnConnection();
		}
		PreparedStatement prepStmt = conn.prepareStatement(query);
		prepStmt.setString(1, caveatDTO.getReasonForRelease());
		prepStmt.setString(2, caveatDTO.getRemarksForRelease());
		prepStmt.setString(3, caveatDTO.getLoggedIn());
		if (caveatDTO.getReleaseContents() != null) {
			prepStmt.setString(4, caveatDTO.getReleaseDocName());
			prepStmt.setBinaryStream(5, new ByteArrayInputStream(caveatDTO.getReleaseContents()), caveatDTO.getReleaseContents().length);
		} else {
			prepStmt.setString(4, "");
			prepStmt.setObject(5, null);
		}
		prepStmt.setString(6, caveatDTO.getReferenceID());
		int rowcount = prepStmt.executeUpdate();
		prepStmt.close();
		logger.debug("Rowcount : " + rowcount);
		return (rowcount == 1);
	}

	@Deprecated public boolean insertBankCaveatData(CaveatsDTO dto) {
		boolean retVal;
		int rowCount;
		try {
			PreparedStatement prepStmt;
			String caveatTxnID = null;
			Statement stmt;
			ResultSet rset;
			String sqlQuery = "";
			try {
				if (conn == null || conn.isClosed()) {
					conn = returnConnection();
				}
				conn.setAutoCommit(false);
				sqlQuery = com.wipro.igrs.caveats.sql.CommonSQL.GET_BANKCAVEATS_TXN_ID;
				stmt = conn.createStatement();
				rset = stmt.executeQuery(sqlQuery);
				if (rset.next()) {
					caveatTxnID = rset.getString(1);
				}
				stmt.close();
				dto.setReferenceID(caveatTxnID);
				sqlQuery = com.wipro.igrs.caveats.sql.CommonSQL.LOG_CAVEATS_BANK;//26 params
				prepStmt = conn.prepareStatement(sqlQuery);
				prepStmt.setString(1, dto.getReferenceID());
				prepStmt.setString(2, dto.getOttNumber());
				//				prepStmt.setString(3, dto.getCaveatType());
				prepStmt.setString(3, null);
				prepStmt.setString(4, dto.getNameOfInsti());
				prepStmt.setString(5, dto.getAddressOfInsti());
				prepStmt.setString(6, dto.getNameOfBankPerson());
				prepStmt.setString(7, dto.getMobOfBankPerson());
				prepStmt.setString(8, dto.getEmailOfBankPerson());
				prepStmt.setString(9, dto.getLoanAccountNumber());
				prepStmt.setString(10, dto.getLoanAmount());
				prepStmt.setString(11, dto.getSecuredAmount());
				prepStmt.setString(12, dto.getAmountOfInstall());
				prepStmt.setString(13, dto.getNoOfInstallments());
				prepStmt.setString(14, dto.getLoanDueDate());
				prepStmt.setString(15, dto.getRegistrationNumber());
				prepStmt.setString(16, dto.getLoggedIn());
				prepStmt.setString(17, dto.getStampPaid());
				prepStmt.setString(18, dto.getLoanPurpose());
				prepStmt.setString(19, dto.getMortgageType());
				prepStmt.setString(20, dto.getRegDate());
				prepStmt.setString(21, dto.getSdocuNumber());
				//				prepStmt.setString(15, dto.getStayOrderNo());
				//				prepStmt.setString(16, dto.getBankCourtRepName());
				//				prepStmt.setString(17, dto.getStayOrderDetails());
				//				prepStmt.setString(18, dto.getStayOrderStartDate());
				//				prepStmt.setString(19, dto.getStayOrderUptoDate());
				//				prepStmt.setString(20, dto.getCaveatDetails());
				//				prepStmt.setString(21, dto.getRemarks());
				//				try {
				//					if (dto.getDocContents() != null && dto.getDocContents().length>0) {
				//						prepStmt.setBinaryStream(22, new ByteArrayInputStream(
				//								dto.getDocContents()),
				//								dto.getDocContents().length);
				//					} else {
				//						prepStmt.setObject(22, null);
				//					}
				//				} catch (Exception e) {
				//					// TODO: handle exception
				//				}
				//				prepStmt.setString(23, dto.getDocumentName());
				//				prepStmt.setString(24, dto.getPropertyTxnLock());
				//				prepStmt.setString(25, dto.getRegistrationNumber());
				//				prepStmt.setString(26, dto.getLoggedIn());
				//				prepStmt.setString(14, dto.getDocumentName());
				//				prepStmt.setBinaryStream(15, new ByteArrayInputStream(dto.getDocContents()), dto.getDocContents().length);
				//				prepStmt.setString(16, dto.getRemarks());
				//				prepStmt.setString(17, dto.getRegistrationNumber());
				//				prepStmt.setString(18, "LOGGED");
				//				prepStmt.setString(19, dto.getLoggedIn());
				//				if(dto.getStayOrderStartDate() != null) {
				//					prepStmt.setString(20, CommonUtil.getConvertedDate(dto.getStayOrderStartDate()));
				//				}
				//				else {
				//					prepStmt.setString(20, null);
				//				}
				//				if(dto.getStayOrderUptoDate() != null) {
				//					prepStmt.setString(21, CommonUtil.getConvertedDate(dto.getStayOrderUptoDate()));
				//				}
				//				else {
				//					prepStmt.setString(21, null);
				//				}
				//				prepStmt.setString(22, dto.getPropertyTxnLock());
				rowCount = prepStmt.executeUpdate();
				logger.debug("Logged Caveat : RowCount " + rowCount);
				sqlQuery = com.wipro.igrs.caveats.sql.CommonSQL.UPDATE_OTT_STATUS;
				prepStmt = conn.prepareStatement(sqlQuery);
				prepStmt.setString(1, dto.getLoggedIn());
				prepStmt.setString(2, dto.getOttNumber());
				rowCount = prepStmt.executeUpdate();
				logger.debug("OTT Deactivated : RowCount " + rowCount);
				prepStmt.close();
				//				sqlQuery = com.wipro.igrs.caveats.sql.CommonSQL.LOG_CAVEATS_PROPERTY_MAP;
				//				logger.debug("Caveat Property Map total count " + dto.getSelectedItems().size());
				//				prepStmt = con.prepareStatement(sqlQuery);
				//				for (Object selectedItem : dto.getSelectedItems()) {
				//					CaveatsDTO selectedProp = (CaveatsDTO) selectedItem;
				//					selectedProp.setReferenceID(dto.getReferenceID());
				//					prepStmt.setString(1, selectedProp.getReferenceID());
				//					prepStmt.setString(2, selectedProp.getPropertyTxnId());
				//					rowCount = prepStmt.executeUpdate();
				//					logger.debug("Logged Caveat Property Map: RowCount " + rowCount);
				//				}
				//				prepStmt.close();
				conn.commit();
				retVal = true;
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				retVal = false;
				conn.rollback();
			}
			releaseConnection(conn);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			retVal = false;
		}
		return retVal;
	}

	@Deprecated public ArrayList getBankCaveatDetails(String query, String[] params, String language) {
		ArrayList retVal = null;
		PreparedStatement prepStmt;
		ResultSet rset;
		CaveatsDTO dto;
		Blob doc;
		try {
			try {
				if (conn == null || conn.isClosed()) {
					conn = returnConnection();
				}
				prepStmt = conn.prepareStatement(query);
				for (int iLoop = 0; iLoop < params.length; iLoop++) {
					prepStmt.setString((iLoop + 1), params[iLoop]);
				}
				rset = prepStmt.executeQuery();
				retVal = new ArrayList();
				// CTX.CAVEAT_BANK_TXN_ID,
				// CTX.OTT_ID,
				// CTX.LOAN_ACCOUNT_NO,
				// LOAN_END_DATE,
				// CTX.LOAN_AMOUNT,
				// CTX.LOAN_SECURED_AMOUNT,
				// CTX.NO_OF_INSTALLMENTS,
				// CTX.BANK_NAME,
				// CTX.BANK_ADDRESS,
				// CTX.BANK_REP_NAME,
				// CTX.REGISTRATION_ID,
				// CAVEAT_LOGGED_DATE,
				// CTX.BANK_PERSON_CONTACT_NO,
				// CTX.BANK_PERSON_EMAIL_ID,
				// CTX.INSTALLMENT_AMOUNT,
				// CTX.CAVEAT_STATUS,
				// CAVEAT_RELEASE_DATE,
				// CTX.REMARKS_FOR_RELEASE,
				// CTX.REASON_FOR_RELEASE
				while (rset.next()) {
					dto = new CaveatsDTO();
					dto.setReferenceID(rset.getString(1));
					dto.setOttNumber(rset.getString(2));
					dto.setLoanAccountNumber(rset.getString(3));
					dto.setLoanDueDate(rset.getString(4));
					dto.setLoanAmount(rset.getString(5));
					dto.setSecuredAmount(rset.getString(6));
					dto.setNoOfInstallments(rset.getString(7));
					dto.setNameOfInsti(rset.getString(8));
					dto.setAddressOfInsti(rset.getString(9));
					dto.setNameOfBankPerson(rset.getString(10));
					dto.setRegistrationNumber(rset.getString(11));
					dto.setLoggedDate(rset.getString(12));
					dto.setMobOfBankPerson(rset.getString(13));
					dto.setEmailOfBankPerson(rset.getString(14));
					dto.setAmountOfInstall(rset.getString(15));
					if ("en".equalsIgnoreCase(language)) {
						dto.setFlag(rset.getString(16).equalsIgnoreCase("LOGGED") ? "LOGGED" : "RELEASED");
					} else {
						dto.setFlag(rset.getString(16).equalsIgnoreCase("LOGGED") ? "लॉग किया गया है" : "रिलीज़ किया गया है");
					}
					dto.setReleaseDate(rset.getString(17));
					dto.setRemarksForRelease(rset.getString(18));
					dto.setReasonForRelease(rset.getString(19));
					dto.setSerialNo(retVal.size() + 1);
					dto.setStampPaid(rset.getString(20));
					dto.setRegDate(rset.getString(23));
					dto.setLoanPurpose(rset.getString(21));
					dto.setMortgageType(rset.getString(22));
					dto.setSdocuNumber(rset.getString(24));
					retVal.add(dto);
				}
				rset.close();
				prepStmt.close();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return retVal;
	}

	@Deprecated public ArrayList<OTTDetailDTO> getBankOTTDetails(String query, String[] params) {
		ArrayList<OTTDetailDTO> retVal = null;
		PreparedStatement prepStmt;
		ResultSet rset;
		OTTDetailDTO dto;
		Blob doc;
		try {
			try {
				if (conn == null || conn.isClosed()) {
					conn = returnConnection();
				}
				prepStmt = conn.prepareStatement(query);
				for (int iLoop = 0; iLoop < params.length; iLoop++) {
					prepStmt.setString((iLoop + 1), params[iLoop]);
				}
				rset = prepStmt.executeQuery();
				retVal = new ArrayList<OTTDetailDTO>();
				while (rset.next()) {
					dto = new OTTDetailDTO();
					dto.setOttNumber(rset.getString(1));
					dto.setRegistrationNo(rset.getString(2));
					dto.setStatus(rset.getString(3));
					dto.setGeneratedDate(rset.getString(4));
					dto.setExpirationDate(rset.getString(5));
					dto.setSerialNo("" + (retVal.size() + 1));
					retVal.add(dto);
				}
				rset.close();
				prepStmt.close();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return retVal;
	}

	//added by shruti-3rd July 2013
	/*
	 *  Generic method to insert the documents in the database.  
	 */
	@Deprecated public boolean saveCaseDocuments(ArrayList columnName, ArrayList columnValue, String tableName) {
		boolean check = false;
		String returnVal = null;
		String sqlText = null;
		BLOB image = null;
		int chunkSize;
		byte[] binaryBuffer;
		long position;
		int bytesRead = 0;
		int bytesWritten = 0;
		int totbytesRead = 0;
		int totbytesWritten = 0;
		String strColumnNames = "";
		String strColumnValues = "";
		try {
			//InputStream ioFile = fileName.getInputStream();
			st = conn.createStatement();
			for (int i = 0; i < columnName.size(); i++) {
				if (strColumnNames != null && !strColumnNames.equals("")) {
					strColumnNames = strColumnNames + ", " + (String) columnName.get(i);
				} else {
					strColumnNames = (String) columnName.get(i);
				}
			}
			strColumnNames = strColumnNames + ", " + "CREATED_DATE";
			for (int i = 0; i < columnValue.size(); i++) {
				if (strColumnValues != null && !strColumnValues.equals("")) {
					strColumnValues = strColumnValues + ", " + "'" + (String) columnValue.get(i) + "'";
				} else {
					strColumnValues = "'" + (String) columnValue.get(i) + "'";
				}
			}
			strColumnValues = strColumnValues + "," + "SYSDATE";
			sqlText = "INSERT INTO " + tableName + "(" + strColumnNames + ") VALUES (" + strColumnValues + ")";
			logger.debug("before insert query :" + sqlText);
			logger.debug(sqlText);
			int j = st.executeUpdate(sqlText);
			if (j == 0) {
				check = false;
			} else {
				check = true;
			}
			logger.debug("After insert ");
			/* sqlText = "SELECT "+docFieldName+" FROM  "+tableName
			 +" WHERE  "+txnFieldName+" ='"+docTxnId+"' FOR UPDATE";
			 
			 logger.debug("after insert query :"+sqlText);			        
			logger.debug("Before update start"+sqlText);
			 rst = st.executeQuery(sqlText);
			 rst.next();
			 image = ((OracleResultSet) rst).getBLOB(docFieldName);
			 
			 chunkSize = image.getChunkSize();
			 binaryBuffer = new byte[chunkSize];
			 
			 position = 1;
			 while ((bytesRead = ioFile.read(binaryBuffer)) != -1) {
			     bytesWritten = image.putBytes(position, binaryBuffer, bytesRead);
			     position        += bytesRead;
			     totbytesRead    += bytesRead;
			     totbytesWritten += bytesWritten;
			 }*/
			//ioFile.close();
			/* logger.debug(
			     "==========================================================\n" +
			     "  PUT METHOD\n" +
			     "==========================================================\n" +
			     "Wrote file  to BLOB column.\n" +
			     totbytesRead + " bytes read.\n" +
			     totbytesWritten + " bytes written.\n"
			 );*/
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			check = false;
		}
		logger.debug("check:-" + check);
		return check;
	}

	@Deprecated public ArrayList getCopyDetails(String query, String[] params, CertifiedActionForm form, String language) {
		ArrayList retVal = null;
		PreparedStatement prepStmt;
		ResultSet rset;
		CaveatsDTO dto;
		Blob doc;
		try {
			try {
				if (conn == null || conn.isClosed()) {
					conn = returnConnection();
				}
				prepStmt = conn.prepareStatement(query);
				for (int iLoop = 0; iLoop < params.length; iLoop++) {
					prepStmt.setString((iLoop + 1), params[iLoop]);
				}
				rset = prepStmt.executeQuery();
				retVal = new ArrayList();
				while (rset.next()) {
					form = new CertifiedActionForm();
					//ArrayList retList1=(ArrayList)list.get(i);    
					if ("en".equalsIgnoreCase(language))
						form.setTypeReq(rset.getString(1));
					else
						form.setTypeReq("प्रमाणित प्रति ");
					form.setCreatedDt(rset.getString(2));
					form.setRegNo(rset.getString(3));
					form.setCertifiedId(rset.getString(4));
					String status = rset.getString(5);
					if ("U".equalsIgnoreCase(status)) {
						if ("en".equalsIgnoreCase(language)) {
							status = "Un-delivered";
						} else {
							status = "अप्राप्य";
						}
					}
					if ("P".equalsIgnoreCase(status)) {
						if ("en".equalsIgnoreCase(language)) {
							status = "Pending at SR";
						} else {
							status = "एसआर से लंबित";
						}
					}
					if ("C".equalsIgnoreCase(status)) {
						if ("en".equalsIgnoreCase(language)) {
							status = "Completed Not Delivered";
						} else {
							status = "पूरे किए लेकिन वितरित नहीं";
						}
					}
					if ("D".equalsIgnoreCase(status)) {
						if ("en".equalsIgnoreCase(language)) {
							status = "Dispatched";
						} else {
							status = "भेजा";
						}
					}
					form.setAppStatus(status);
					form.setReqCreatedAt("at SRO");
					//dataList.add(form);   
					retVal.add(form);
				}
				rset.close();
				prepStmt.close();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return retVal;
	}

	public ArrayList getSearchAuditDetails(String query, String[] params, AGMPReportDetailsDTO reportDetailsDTO) {
		ArrayList retVal = null;
		PreparedStatement prepStmt;
		ResultSet rset;
		CaveatsDTO dto;
		Blob doc;
		try {
			try {
				if (conn == null || conn.isClosed()) {
					conn = returnConnection();
				}
				logger.debug(query);
				prepStmt = conn.prepareStatement(query);
				for (int iLoop = 0; iLoop < params.length; iLoop++) {
					prepStmt.setString((iLoop + 1), params[iLoop]);
					logger.debug("Array:" + params[iLoop]);
				}
				rset = prepStmt.executeQuery();
				retVal = new ArrayList();
				while (rset.next()) {
					reportDetailsDTO = new AGMPReportDetailsDTO();
					//ArrayList retList1=(ArrayList)list.get(i);    
					reportDetailsDTO.setTxnId(rset.getString(1));
					reportDetailsDTO.setAuditDate(rset.getString(2));
					reportDetailsDTO.setCreatedDate(rset.getString(3));
					String status = rset.getString(4);
					if ("O".equalsIgnoreCase(status) || "open".equalsIgnoreCase(status)) {
						status = "Open";
					}
					reportDetailsDTO.setAuditStatus(status);
					//dataList.add(form);   
					retVal.add(reportDetailsDTO);
				}
				rset.close();
				prepStmt.close();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return retVal;
	}

	//added by shruti
	@Deprecated public boolean saveAGMPAuditReport(String docId, String filePath, String fileName, String[] OthDetails, String functionName) throws Exception {
		boolean check = true;
		String sqlText = null;
		try {
			if (functionName.equalsIgnoreCase("audit")) {
				/* sqlText= "INSERT INTO IGRS_RAUDIT_DOCUMENT_DETAILS"+
				  "(DOCUMENT_TXN_ID, DOCUMENT_FILE_NAME, DOCUMENT_FILE, DOCUMENT_STATUS, CREATED_BY, CREATED_DATE, UPDATE_BY, UPDATE_DATE) "+ 
				  "VALUES('"+docId+"','"+fileName+"',EMPTY_BLOB(),'"+OthDetails[0]+"','"+OthDetails[1]+"',SYSDATE,'"+OthDetails[2]+"',SYSDATE)";  */
				sqlText = "INSERT INTO IGRS_RAUDIT_DOCUMENT_DETAILS" + "(DOCUMENT_TXN_ID, DOCUMENT_FILE_NAME, DOCUMENT_STATUS, CREATED_BY, CREATED_DATE, UPDATE_BY, UPDATE_DATE) " + "VALUES('" + docId + "','" + fileName + "','" + OthDetails[0] + "','" + OthDetails[1] + "',SYSDATE,'" + OthDetails[2] + "',SYSDATE)";
			} else if (functionName.equalsIgnoreCase("inspection")) {
				/*sqlText= "INSERT INTO IGRS_POI_DOC_DTLS"+
				 "(DOC_TXN_ID, DOC_NAME, DOC_OBJECT, DOC_STATUS, CREATED_BY, CREATED_DATE, UPDATE_BY, UPDATE_DATE) "+ 
				 "VALUES('"+docId+"','"+fileName+"',EMPTY_BLOB(),'"+OthDetails[0]+"','"+OthDetails[1]+"',SYSDATE,'"+OthDetails[2]+"',SYSDATE)";  
				*/
				sqlText = "INSERT INTO IGRS_POI_DOC_DTLS" + "(DOC_TXN_ID, DOC_NAME, DOC_PATH , DOC_STATUS, CREATED_BY, CREATED_DATE, UPDATE_BY, UPDATE_DATE) " + "VALUES('" + docId + "','" + fileName + "','" + filePath.concat(fileName) + "','" + OthDetails[0] + "','" + OthDetails[1] + "',SYSDATE,'" + OthDetails[2] + "',SYSDATE)";
			}
			st = conn.createStatement();
			st.executeUpdate(sqlText);
			logger.debug("After insert ");
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			logger.debug("SQL:\n" + sqlText);
		}
		if (check) {
			conn.commit();
		} else {
			conn.rollback();
		}
		return check;
	}

	//added by shruti
	//BLOB Regading END
	@Deprecated public boolean saveDROInspDocuments(String inspId, String docTxnId, String fileName, String filePath, String docTypeId) {
		boolean check = true;
		String returnVal = null;
		String sqlText = null;
		String strSeq = null;
		try {
			// rst = st.executeQuery("select DOCUMENT_TYPE_ID	from IGRS_DOCUMENT_TYPE_MASTER where DOCUMENT_NAME='"+docTypeId+"'");
			//rst.next();
			//String documentid=((OracleResultSet) rst).getString("DOCUMENT_TYPE_ID");
			sqlText = "INSERT INTO IGRS_DRO_INSP_DOC_MAP" + "(DOCUMENT_TXN_ID,INSP_TXN_ID,DOCUMENT_NAME,DOCUMENT_PATH) " + " VALUES('" + docTxnId + "','" + inspId + "', '" + fileName + "', '" + filePath + "')";
			st.executeUpdate(sqlText);
			logger.debug("After insert ");
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			// logger.debug("SQL:\n" + sqlText);
			//check=false;
		}
		if (check) {
			try {
				conn.commit();
			} catch (SQLException e) {
				check = false;
				logger.error(e.getMessage(), e);
			}
		} else {
			try {
				conn.rollback();
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
			}
		}
		return check;
	}

	//added by shruti----29 th nov 2013
	@Deprecated public boolean saveSroDocumentsNew(String docTxnId, String inspectionId, String filename, String filesPath) {
		boolean flag = true;
		int i = 0;
		String sqlText = null;
		BLOB image = null;
		int chunkSize;
		byte[] binaryBuffer;
		long position;
		int bytesRead = 0;
		int bytesWritten = 0;
		int totbytesRead = 0;
		int totbytesWritten = 0;
		//File file=null;
		try {
			logger.debug("File path :" + filesPath + filename);
			// file = new File(filesPath+filename);
			// InputStream ioFile = new FileInputStream(file);
			st = conn.createStatement();
			//BLOB reading as well as writting in to the Database START
			//before that we need to get the sequence number
			/* sqlText ="INSERT INTO IGRS_SRO_INSP_DOC_MAP"
				+"(INSP_TXN_ID,DOCUMENT_TXN_ID,DOCUMENT_NAME,DOCUMENT_OBJ) "
				+" VALUES('"+inspectionId+"','"+docTxnId+"', '"+filename+"',EMPTY_BLOB())";*/
			//Modified by SHreeraJ
			sqlText = "INSERT INTO IGRS_SRO_INSP_DOC_MAP" + "(INSP_TXN_ID,DOCUMENT_TXN_ID,DOCUMENT_NAME,DOCUMENT_PATH) " + " VALUES('" + inspectionId + "','" + docTxnId + "', '" + filename + "','" + filesPath + "')";
			logger.debug("before insert query :" + sqlText);
			st.executeUpdate(sqlText);
			logger.debug("After insert ");
			/* sqlText = 
			     "SELECT DOCUMENT_OBJ " +
			     "FROM   IGRS_SRO_INSP_DOC_MAP " +
			     "WHERE  DOCUMENT_TXN_ID ='"+docTxnId+"'"+
			     " FOR UPDATE";
			 logger.debug("Before update start"+sqlText);
			 rst = st.executeQuery(sqlText);
			 rst.next();
			 image = ((OracleResultSet) rst).getBLOB("DOCUMENT_OBJ");
			 
			 chunkSize = image.getChunkSize();
			 binaryBuffer = new byte[chunkSize];
			 
			 position = 1;
			 while ((bytesRead = ioFile.read(binaryBuffer)) != -1) {
			     bytesWritten = image.putBytes(position, binaryBuffer, bytesRead);
			     position        += bytesRead;
			     totbytesRead    += bytesRead;
			     totbytesWritten += bytesWritten;
			 }
			 
			 ioFile.close();
			 
			 logger.debug(
			     "==========================================================\n" +
			     "  PUT METHOD\n" +
			     "==========================================================\n" +
			     "Wrote file  to BLOB column.\n" +
			     totbytesRead + " bytes read.\n" +
			     totbytesWritten + " bytes written.\n"
			 );
			 //BLOB end 
			 
			 //Deleteing file from physcial path
			 file.delete();
			*/
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error(e1.getMessage(), e1);
			}
			//	file.delete();
			flag = false;
		}
		return flag;
	}

	public void releaseConnection(Connection con) throws Exception {
		ConnectionPool.getInstance().closeConnection(con);
	}

	public Connection getDBConnection() throws Exception {
		if (!(conn != null && !conn.isClosed())) {
			conn = ConnectionPool.getInstance().getConnection();
		}
		return conn;
	}

	@Override protected void finalize() throws Throwable {
		if (conn != null && (!conn.isClosed())) {
			try {
				ConnectionPool.getInstance().closeConnection(conn);
				logger.debug("Connection is closed...");
			} catch (Exception e) {
				logger.error("Error in finalize method :- " + e.getMessage());
			} finally {
				super.finalize();
			}
		}
	}

	/**
	 * Method : executeQuery Description : Execute a query with
	 * PreparedStatement
	 * 
	 * @param arr : List
	 * @return list : ArrayList
	 * @throws : Exception
	 */
	//Added by Aakash
	public ArrayList executeQuery(List<String> arr) throws Exception {
		logger.debug("DBUtility - executeQuery()");
		ArrayList list = new ArrayList();
		try {
			for (int i = 0; i < arr.size(); i++) {
				pst.setString(i + 1, arr.get(i));
			}
			rst = pst.executeQuery();
			logger.debug("Wipro in DBUtility - executeQuery() after pst.executeQuery()");
			ResultSetMetaData rsmd = rst.getMetaData();
			int col_count = rsmd.getColumnCount();
			while (rst.next()) {
				ArrayList row_list = new ArrayList();
				for (int i = 1; i <= col_count; i++) {
					//logger.debug("index for reg init----> "+i);
					row_list.add(rst.getString(i));
				} //for
				list.add(row_list);
			} //while
			pst.clearParameters();
		} catch (SQLException sqle) {
			logger.error(sqle.getMessage(), sqle);
			throw sqle;
		} catch (NumberFormatException nfe) {
			logger.error(nfe.getMessage(), nfe);
			throw nfe;
		} catch (IllegalArgumentException iae) {
			logger.error(iae.getMessage(), iae);
			throw iae;
		}
		list.trimToSize();
		return list;
	}
}
