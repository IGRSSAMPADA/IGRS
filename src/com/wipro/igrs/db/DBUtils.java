package com.wipro.igrs.db;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.servlet.http.HttpServletResponse;

import oracle.jdbc.driver.OracleCallableStatement;
import oracle.jdbc.driver.OracleTypes;
import oracle.jdbc.pool.OracleDataSource;

import org.apache.log4j.Logger;

import com.wipro.igrs.hrpayroll.payroll.dto.PayrollDTO;
import com.wipro.igrs.loanadvance.dto.LoanDTO;
import com.wipro.igrs.loanadvance.dto.LoanadvanceDTO;
import com.wipro.igrs.loanadvance.sql.CommonSQL;
import com.wipro.igrs.util.PropertiesFileReader;

/**
 * 
 * @author WIPRO Java Team <br/>
 *         File : DBUtils.java <br/>
 *         Description : Class to be used by various modules to perform database
 *         operations<br/>
 *         Created Date : Oct 15, 2012
 * 
 */
public class DBUtils {

	private static DBUtils instance;
	private static Logger logger = (Logger) Logger.getLogger(DBUtils.class);
	private IGRSDataSource dataSource;

	/**
	 * 
	 */
	private DBUtils() {
		try {
			this.dataSource = IGRSDataSource.getInstance();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * Get a singleton instance of DBUtils
	 * 
	 * @return
	 */
	/*public static DBUtils getInstance() {
		if (instance == null) {
			synchronized (DBUtils.class) {
				if (instance == null) {
					instance = new DBUtils();
				}
			}
		}
		return instance;
	}*/

	/**
	 * 
	 * @author WIPRO Java Team <br/>
	 *         File : DBUtils.java (Moved from IGRSDataSource.java) <br/>
	 *         Description : Represents the Connection pool Class <br/>
	 *         Created Date : Oct 19, 2012 <br/>
	 *         <b>Note</b>: Moved to an inner class as <br/>
	 *         implementation of actual datasource need not <br/>
	 *         be concerned by individual modules of IGRS
	 * 
	 */
	private static class IGRSDataSource extends OracleDataSource {

		private static PropertiesFileReader propReader;
		private static IGRSDataSource instance;
		private static Logger logger = (Logger) Logger
				.getLogger(IGRSDataSource.class);
		private static DatabaseMetaData metaData;
		/**
		 * 
		 */
		private static final long serialVersionUID = 8213533442926347573L;

		/**
		 * Restricted constructer used in order to implement Singleton Pattern
		 * 
		 * @throws SQLException
		 */
		private IGRSDataSource() throws SQLException {
			super();
		}

		/**
		 * Gets a Singleton instance of IGRSDataSource
		 * 
		 * @return An instance of IGRSDataSource
		 */
		public static IGRSDataSource getInstance() {
			if (instance == null) {
				synchronized (IGRSDataSource.class) {
					if (instance == null) {
						try {
							instance = new IGRSDataSource();
							buildProperties(instance);
						} catch (SQLException e) {
							logger.error(e.getMessage(), e);
						}
					}
				}
			}
			return instance;
		}

		/**
		 * This method builds various properties of the datasource, this method
		 * also binds the datasource to a JNDI resource
		 * 
		 * @param instance
		 */
		private static void buildProperties(IGRSDataSource instance) {
			boolean isJNDILookup = false;
			String serverType, jndiName;
			try {
				InitialContext initContext = new InitialContext();
				propReader = PropertiesFileReader
						.getInstance("com.wipro.igrs.igrs");
				serverType = propReader.getValue("SERVER_TYPE");
				jndiName = propReader.getValue("JNDI_LOOKUP");
				if ("TOMCAT".equals(serverType)) {
					logger.debug("Server type is TOMCAT generating datasource instance from properties");
					generateDataSource(instance);
					bindJNDIDataSource(instance, jndiName, initContext);
					isJNDILookup = false;
				} else {
					logger.debug("Server type is not TOMCAT generating datasource from JNDI");
					instance = lookUpDataSource();
					if (instance != null) {
						isJNDILookup = true;
					} else {
						logger.warn("Could not generate datasource from JNDI falling back to properties");
						generateDataSource(instance);
					}

				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			} finally {
				if (isJNDILookup) {
					logger.debug("Datasource built using JNDI Lookup");
				} else {
					logger.debug("Datasource built without using JNDI Lookup");
				}

			}
		}

		/**
		 * @param instance
		 * @param jndiName
		 * @param initContext
		 */
		private static void bindJNDIDataSource(IGRSDataSource instance,
				String jndiName, InitialContext initContext) {
			try {
				initContext.bind(jndiName, instance);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}

		/**
		 * @param instance
		 */
		private static void generateDataSource(IGRSDataSource instance) {
			try {
				instance.setDriverType(propReader.getValue("DRIVER_TYPE"));
				instance.setServerName(propReader.getValue("SERVER_NAME"));
				instance.setDatabaseName(propReader.getValue("DB_NAME"));
				instance.setUser(propReader.getValue("DB_USER_ID"));
				instance.setPassword(propReader.getValue("DB_USER_PASSWORD"));
				instance.setPortNumber(Integer.parseInt(propReader
						.getValue("DB_PORT")));
				Properties propsConnCache = new Properties();
				propsConnCache.put("InitialLimit",
						propReader.getValue("MIN_DB_CONNECTION"));
				propsConnCache.put("MaxLimit",
						propReader.getValue("MAX_DB_CONNECTION"));
				propsConnCache.put("MinLimit",
						propReader.getValue("MIN_DB_CONNECTION"));
				propsConnCache.put("AbandonedConnectionTimeout", "30");
				// TODO Add propety AbandonedConnectionTimeout to be set on
				// seconds
				/*instance.setConnectionCachingEnabled(true);
				instance.setConnectionCacheProperties(propsConnCache);*/
				logger.debug("Attempting to retrieve database metadata");
				metaData = instance.getConnection().getMetaData();
				logger.debug("Database metadata retrieved : "
						+ metaData.toString());
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}

		/**
		 * Performs a JNDI Lookup to obtain a reference to the datasource
		 * 
		 * @return An instance of {@link IGRSDataSource}
		 */
		public static IGRSDataSource lookUpDataSource() {
			try {
				InitialContext initContext = new InitialContext();
				Object temp;
				temp = initContext.lookup(propReader.getValue("JNDI_LOOKUP"));
				if (temp != null) {
					if (temp instanceof IGRSDataSource) {
						logger.debug("JNDI lookup returned a valid IGRSDataSource object");
						instance = (IGRSDataSource) temp;
					}
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
			return instance;
		}

		/**
		 * Gets a connection instance from the datasource.<br/>
		 * <b>Note</b>: {@link Connection#setAutoCommit(boolean)} will be set to
		 * true
		 * 
		 * @return A {@link Connection} instance
		 */
		@SuppressWarnings("unused")
		public Connection getConnectionFromDataSource() {
			return getConnectionFromDataSource(true);
		}

		/**
		 * Gets a connection instance from the datasource
		 * 
		 * @param autoCommit
		 *            Sets the connection's auto-commit mode to the given state. <br/>
		 *            See {@link Connection#setAutoCommit(boolean)}
		 * @return A {@link Connection} instance
		 */
		public Connection getConnectionFromDataSource(boolean autoCommit) {
			Connection retVal = null;
			try {
				retVal = this.getConnection();
				retVal.setAutoCommit(autoCommit);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
			return retVal;
		}
	}

	/**
	 * Get a {@link Connection} object from Datasource with specified option
	 * 
	 * @param autoCommit
	 * @return
	 */
	private Connection retrieveConnection(boolean autoCommit) {
		Connection conn = null;
		try {
			conn = this.dataSource.getConnectionFromDataSource(autoCommit);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return conn;

	}

	/**
	 * Get a {@link Connection} object from Datasource
	 * 
	 * @return
	 */
	private Connection retrieveConnection() {
		return this.retrieveConnection(true);
	}

	/**
	 * Creates a {@link Statement} object with autoCommit = true, resultSetType=
	 * {@link ResultSet#TYPE_SCROLL_INSENSITIVE} and resultSetConcurrency=
	 * {@link ResultSet#CONCUR_READ_ONLY}
	 * 
	 * @return
	 * 
	 * @throws SQLException
	 */
	public Statement createStatement() throws SQLException {
		return createStatement(true, ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
	}

	/**
	 * Creates a {@link Statement} object with options specified
	 * 
	 * @param autoCommit
	 * @param resultSetType
	 * @param resultSetConcurrency
	 * @return
	 * @throws SQLException
	 */
	public Statement createStatement(boolean autoCommit, int resultSetType,
			int resultSetConcurrency) throws SQLException {
		Connection conn = null;
		Statement simpStmt = null;
		try {
			conn = this.retrieveConnection(autoCommit);
			simpStmt = conn
					.createStatement(resultSetType, resultSetConcurrency);
		} catch (SQLException sqle) {
			logger.error(sqle.getMessage(), sqle);
			throw sqle;
		}
		return simpStmt;
	}

	/**
	 * Creates a {@link PreparedStatement} object with options specified
	 * 
	 * @param sqlQuery
	 * @param resultSetType
	 * @param resultSetConcurrency
	 * @param autoCommit
	 * @return
	 * @throws SQLException
	 */
	public PreparedStatement createPreparedStatement(String sqlQuery,
			int resultSetType, int resultSetConcurrency, boolean autoCommit)
			throws SQLException {
		Connection conn = null;
		PreparedStatement prepStmt = null;
		try {
			conn = this.retrieveConnection();
			prepStmt = conn.prepareStatement(sqlQuery, resultSetType,
					resultSetConcurrency);
		} catch (SQLException sqle) {
			logger.error(sqle.getMessage(), sqle);
			throw sqle;
		}
		return prepStmt;
	}

	/**
	 * Creates a {@link PreparedStatement} object with autoCommit = true,
	 * resultSetType= {@link ResultSet#TYPE_SCROLL_INSENSITIVE} and
	 * resultSetConcurrency= {@link ResultSet#CONCUR_READ_ONLY}
	 * 
	 * @param sqlQuery
	 * @return
	 * @throws SQLException
	 */
	public PreparedStatement createPreparedStatement(String sqlQuery)
			throws SQLException {
		Connection conn = null;
		PreparedStatement prepStmt = null;
		try {
			conn = this.retrieveConnection();
			prepStmt = conn.prepareStatement(sqlQuery);
		} catch (SQLException sqle) {
			logger.error(sqle.getMessage(), sqle);
			throw sqle;
		}
		return prepStmt;
	}

	/**
	 * Creates a {@link CallableStatement} object with the specified option
	 * 
	 * @param procedure
	 * @param autoCommit
	 * @return
	 * @throws SQLException
	 */
	public CallableStatement createCallableStatement(String procedure,
			boolean autoCommit) throws SQLException {
		Connection conn = null;
		CallableStatement callStmt = null;
		try {
			conn = this.retrieveConnection(autoCommit);
			callStmt = conn.prepareCall(procedure);
		} catch (SQLException sqle) {
			logger.error(sqle.getMessage(), sqle);
			throw sqle;
		}
		return callStmt;
	}

	/**
	 * Creates a {@link CallableStatement} object with autoCommit = true
	 * 
	 * @param procedure
	 * @return
	 * @throws SQLException
	 */
	public CallableStatement createCallableStatement(String procedure)
			throws SQLException {
		return createCallableStatement(procedure, true);
	}

	/**
	 * 
	 * @param procedure
	 * @param autoCommit
	 * @return
	 * @throws SQLException
	 */
	public OracleCallableStatement createOracleCallableStatement(
			String procedure, boolean autoCommit) throws SQLException {
		Connection conn = null;
		OracleCallableStatement oraCallStmt = null;
		Object tempStmt = null;
		try {
			conn = this.retrieveConnection(autoCommit);
			tempStmt = conn.prepareCall(procedure);
			if (tempStmt != null) {
				if (tempStmt instanceof OracleCallableStatement) {
					oraCallStmt = (OracleCallableStatement) tempStmt;
				}
			}
		} catch (SQLException sqle) {
			logger.error(sqle.getMessage(), sqle);
			throw sqle;
		}
		return oraCallStmt;
	}

	/**
	 * @param dataSet
	 * @param rstData
	 * @return
	 * @throws SQLException
	 */
	private ArrayList<ArrayList<Object>> populateDataSet(
			ArrayList<ArrayList<Object>> dataSet, ResultSet rstData)
			throws SQLException {
		ArrayList<Object> row;
		ResultSetMetaData metaData = rstData.getMetaData();
		int colCount = metaData.getColumnCount();
		while (rstData.next()) {
			row = new ArrayList<Object>(colCount);
			for (int iLoop = 1; iLoop <= colCount; iLoop++) {
				row.add(rstData.getObject(iLoop));
			}
			dataSet.add(row);
		}
		dataSet.trimToSize();
		return dataSet;
	}

	public ArrayList<ArrayList<Object>> executeQuery(String sqlQuery)
			throws Exception {
		ArrayList<ArrayList<Object>> dataSet = new ArrayList<ArrayList<Object>>();
		try {
			Statement stmt = createStatement();
			ResultSet rstData = stmt.executeQuery(sqlQuery);
			dataSet = populateDataSet(dataSet, rstData);
			stmt.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		return dataSet;
	}

	public ArrayList<ArrayList<Object>> executeQuery(Object[] params,
			PreparedStatement prepStmt) throws Exception {
		ArrayList<ArrayList<Object>> dataSet = new ArrayList<ArrayList<Object>>();
		try {
			for (int iLoop = 0; iLoop < params.length; iLoop++) {
				prepStmt.setObject(iLoop + 1, params[iLoop]);
			}
			ResultSet rstData = prepStmt.executeQuery();
			dataSet = populateDataSet(dataSet, rstData);
			prepStmt.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		dataSet.trimToSize();
		return dataSet;
	}

	public String[] authenticateUser(String userId, String pwd,
			String noattempt, String roleID, CallableStatement callStmt)
			throws Exception {
		String[] status = new String[3];
		try {
			callStmt.setString(1, userId);
			callStmt.setString(2, pwd);

			callStmt.setInt(3, Integer.parseInt(noattempt));

			callStmt.registerOutParameter(4, OracleTypes.VARCHAR);
			callStmt.registerOutParameter(5, OracleTypes.VARCHAR);
			callStmt.registerOutParameter(6, OracleTypes.VARCHAR);
			callStmt.registerOutParameter(7, OracleTypes.VARCHAR);

			callStmt.execute();
			status[0] = callStmt.getString(4);
			status[1] = callStmt.getString(5);
			status[2] = callStmt.getString(6);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		return status;
	}

	public boolean executeUpdate(Object[] params, PreparedStatement prepStmt)
			throws Exception {
		boolean retVal = false;
		int rowCount = 0;
		try {
			for (int iLoop = 0; iLoop < params.length; iLoop++) {
				prepStmt.setObject(iLoop + 1, params[iLoop]);
			}
			rowCount = prepStmt.executeUpdate();
			prepStmt.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		} finally {
			if (rowCount == 0) {
				retVal = false;
			} else {
				retVal = true;
			}
		}
		return retVal;

	}

	public boolean executeUpdate(String sqlQuery) throws Exception {
		return executeUpdate(null, createPreparedStatement(sqlQuery));
	}

	public Object executeQry(Object[] params, String sqlQuery) throws Exception {
		Object retVal = null;

		try {
			ResultSet rstData;
			PreparedStatement prepStmt = createPreparedStatement(sqlQuery,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY, true);
			if (params != null) {
				for (int iLoop = 0; iLoop < params.length; iLoop++) {
					prepStmt.setObject(iLoop + 1, params[iLoop]);
				}
			}
			rstData = prepStmt.executeQuery();
			if (rstData.last()) {
				retVal = rstData.getObject(1);
			}
			prepStmt.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		} finally {
			if (retVal == null) {
				retVal = new Object();
			}
		}
		return retVal;
	}

	public Object executeQry(String sqlQuery) throws Exception {
		return executeQry(null, sqlQuery);
	}

	public boolean executeBatchStatments(ArrayList<String> batchQueries)
			throws Exception {
		boolean retVal = false;
		boolean qryFlag = false;
		String sqlQry;
		Statement stmt;
		try {
			Connection conn = dataSource.getConnection();
			stmt = conn.createStatement();
			try {
				conn.setAutoCommit(false);
				for (int iLoop = 0; iLoop < batchQueries.size(); iLoop++) {
					sqlQry = batchQueries.get(iLoop);
					stmt.addBatch(sqlQry);
				}
				stmt.executeBatch();
				qryFlag = true;
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				retVal = false;
				throw e;
			} finally {
				if (qryFlag) {
					logger.info("Commiting Batch of Queries");
					conn.commit();
					retVal = true;
				} else {
					logger.info("Rolling Back set of Queries");
					conn.rollback();
				}
				stmt.close();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		return retVal;
	}

	/**
	 * 
	 * @param procedure
	 * @param deedTypeID
	 * @param instrumentID
	 * @param exemptionID
	 * @return
	 * @throws Exception
	 */
	public String getStampDutyValue(String procedure, String deedTypeID,
			String instrumentID, String exemptionID[]) throws Exception {
		String status = "dd";
		CallableStatement callStmt = null;
		try {
			callStmt = createCallableStatement(procedure);
			callStmt.setString(1, deedTypeID);
			callStmt.setString(2, instrumentID);
			callStmt.setString(3, null);
			callStmt.registerOutParameter(4, OracleTypes.NUMBER);
			if (callStmt.execute()) {
				// TODO decide on below
				// status = "" + callStmt.getInt(4);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		return status;
	}

	/**
	 * 
	 * @param _refDeedTypeId
	 * @param _refInstrId
	 * @param _refExemId
	 * @return
	 */
	public String getNonJudStampDuty(String _refDeedTypeId, String _refInstrId,
			String _refExemId) {
		CallableStatement callStmt = null;
		String stampDuty = null;
		try {
			// TODO move procedure
			callStmt = createCallableStatement("call IGRS_STAMP_DUTY.IGRS_STAMP_DUTY_CALC3(?,?,?,?,?)");
			callStmt.setString(1, _refDeedTypeId);
			callStmt.setString(2, _refInstrId);
			callStmt.setString(3, _refExemId);
			callStmt.setString(4, null);
			callStmt.registerOutParameter(5, OracleTypes.NUMBER);
			if (!callStmt.execute()) {
				stampDuty = "" + callStmt.getInt(5);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return stampDuty;
	}

	/**
	 * 
	 * @param _refcatId
	 * @param _refBaseVal
	 * @param _refUnits
	 * @return
	 */
	public String getJudStampDuty(String _refcatId, String _refBaseVal,
			String _refUnits) {
		CallableStatement callStmt = null;
		String stampDuty = null;
		try {
			// TODO move procedure
			callStmt = createCallableStatement("call IGRS_STAMP_DUTY_JUDICIAL_CALC.IGRS_JUDICIAL_SDUTY_CALC(?,?,?,?)");
			callStmt.setString(1, _refcatId);
			callStmt.setString(2, _refBaseVal);
			callStmt.setString(3, _refUnits);
			callStmt.registerOutParameter(4, OracleTypes.NUMBER);
			if (!callStmt.execute()) {
				stampDuty = "" + callStmt.getInt(4);
			}
		} catch (Exception e) {
			logger.debug("Error " + e);
		}
		return stampDuty;
	}

	/**
	 * 
	 * @param sqlQuery
	 * @return
	 * @throws Exception
	 */
	public int executeUpdt(String sqlQuery) throws Exception {
		int result = 0;
		try {
			Statement stmt = createStatement();
			result = stmt.executeUpdate(sqlQuery);
		} catch (Exception e) {
			logger.debug("Error " + e);
		}
		return result;
	}

	/**
	 * 
	 * @param deed
	 * @param instrument
	 * @param exemption
	 * @return
	 */
	public String getStampDuty(String deed, String instrument, String exemption) {
		CallableStatement callStmt = null;
		String stampDuty = null;
		try {
			// TODO move procedure
			callStmt = createCallableStatement("call IGRS_STAMP_DUTY.IGRS_STAMP_DUTY_CALC3(?,?,?,?,?)");
			callStmt.setString(1, deed);
			callStmt.setString(2, instrument);
			callStmt.setString(3, exemption);
			callStmt.setString(4, null);
			callStmt.registerOutParameter(5, OracleTypes.NUMBER);
			if (!callStmt.execute()) {
				stampDuty = "" + callStmt.getInt(5);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return stampDuty;
	}

	/**
	 * 
	 * @param function_id
	 * @return
	 */
	public int getOthersDuty(String function_id) {
		CallableStatement callStmt = null;
		int otherFee = 0;
		try {
			// TODO move procedure
			callStmt = createCallableStatement("call IGRS_SERVICE_FEE_PKG.IGRS_SERVICE_FEE_PROC(?,?)");
			callStmt.setString(1, function_id);
			callStmt.registerOutParameter(2, OracleTypes.NUMBER);
			if (!callStmt.execute()) {
				otherFee = callStmt.getInt(2);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return otherFee;
	}

	/**
	 * 
	 * @param _refFunId
	 * @return
	 */
	public String getWillOtherFeeValue(String _refFunId) {
		CallableStatement callStmt = null;
		String othersFeeDuty = null;
		try {
			// TODO move procedure
			callStmt = createCallableStatement("call IGRS_SERVICE_FEE_PKG.IGRS_SERVICE_FEE_PROC(?,?,?,?,?,?)");
			callStmt.setString(1, _refFunId);
			callStmt.setString(2, null);
			callStmt.setString(3, null);
			callStmt.registerOutParameter(4, OracleTypes.NUMBER);
			callStmt.registerOutParameter(5, OracleTypes.CLOB);
			callStmt.registerOutParameter(6, OracleTypes.VARCHAR);
			if (!callStmt.execute()) {
				othersFeeDuty = "" + callStmt.getInt(4);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return othersFeeDuty;
	}

	/**
	 * 
	 * @param regid
	 * @param sqlQuery
	 * @return
	 * @throws Exception
	 */
	public ArrayList<ArrayList<Object>> executeQueryLikeSearch(String regid,
			String sqlQuery) throws Exception {
		ArrayList<ArrayList<Object>> dataSet = new ArrayList<ArrayList<Object>>();
		PreparedStatement prepStmt = null;
		ResultSet rstData = null;
		try {
			prepStmt = createPreparedStatement(sqlQuery);
			prepStmt.setString(1, "%" + regid + "%");
			rstData = prepStmt.executeQuery();
			populateDataSet(dataSet, rstData);
			prepStmt.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		return dataSet;
	}

	/**
	 * 
	 * @param docId
	 * @param filePath
	 * @param fileName
	 * @param otherDetails
	 * @param functionName
	 * @return
	 * @throws Exception
	 */
	public boolean attachAGMPAuditReport(String docId, String filePath,
			String fileName, String[] otherDetails, String functionName)
			throws Exception {
		boolean retVal = false;
		PreparedStatement prepStmt = null;
		String sqlQuery = null;
		FileInputStream fileStream = null;
		try {
			// TODO move sqls
			if ("audit".equalsIgnoreCase(functionName)) {
				sqlQuery = "INSERT INTO IGRS_RAUDIT_DOCUMENT_DETAILS (DOCUMENT_TXN_ID, DOCUMENT_FILE_NAME, DOCUMENT_FILE, DOCUMENT_STATUS, CREATED_BY, CREATED_DATE, UPDATE_BY, UPDATE_DATE) VALUES (?,?,?,?,?,SYSDATE,?,SYSDATE)";
			} else if ("inspection".equalsIgnoreCase(functionName)) {
				sqlQuery = "INSERT INTO IGRS_POI_DOC_DTLS (DOC_TXN_ID, DOC_NAME, DOC_OBJECT, DOC_STATUS, CREATED_BY, CREATED_DATE, UPDATE_BY, UPDATE_DATE) VALUES (?,?,?,?,?,SYSDATE,?,SYSDATE)";
			}
			fileStream = new FileInputStream(filePath);
			prepStmt = createPreparedStatement(sqlQuery);
			prepStmt.setString(1, docId);
			prepStmt.setString(2, fileName);
			prepStmt.setBinaryStream(3, fileStream, fileStream.available());
			prepStmt.setString(4, otherDetails[0]);
			prepStmt.setString(5, otherDetails[1]);
			prepStmt.setString(7, otherDetails[2]);
			retVal = prepStmt.executeUpdate() == 1 ? true : false;
			prepStmt.close();
			fileStream.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		return retVal;
	}

	/**
	 * 
	 * @param inspectionId
	 * @param docTxnId
	 * @param fileName
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	public boolean saveSroDocuments(String inspectionId, String docTxnId,
			String fileName, String filePath) throws Exception {
		boolean retVal = false;
		PreparedStatement prepStmt = null;
		String sqlQuery = null;
		FileInputStream fileStream = null;
		File file;
		try {
			// TODO move SQL
			sqlQuery = "INSERT INTO IGRS_SRO_INSP_DOC_MAP (INSP_TXN_ID, DOCUMENT_TXN_ID, DOCUMENT_NAME, DOCUMENT_OBJ) VALUES (?,?,?,?)";
			file = new File(filePath + fileName);
			fileStream = new FileInputStream(file);
			prepStmt = createPreparedStatement(sqlQuery);
			prepStmt.setString(1, inspectionId);
			prepStmt.setString(2, docTxnId);
			prepStmt.setString(3, fileName);
			prepStmt.setBinaryStream(4, fileStream, fileStream.available());
			retVal = prepStmt.executeUpdate() == 1 ? true : false;
			prepStmt.close();
			fileStream.close();
			file.delete();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		return retVal;
	}

	/**
	 * 
	 * @param updateQuery
	 * @param params
	 * @param procedure
	 * @param loanadvanceDTO
	 * @return
	 * @throws Exception
	 */
	public String insertAdvanceEMI(String updateQuery, Object[] params,
			String procedure, LoanadvanceDTO loanadvanceDTO) throws Exception {
		boolean flag;
		Connection conn;
		PreparedStatement prepStmt;
		CallableStatement callStmt;
		String status = "";
		String errorCode = "";
		try {
			conn = retrieveConnection(false);
			prepStmt = conn.prepareStatement(updateQuery);
			flag = executeUpdate(params, prepStmt);
			callStmt = conn.prepareCall(procedure);
			callStmt.setString(1, loanadvanceDTO.getAdvancetxnid().toString());
			callStmt.setString(2, loanadvanceDTO.getEmpid().toString());
			callStmt.setString(3, loanadvanceDTO.getAdvancetypeid().toString());
			callStmt.setString(4, loanadvanceDTO.getAdvanceamount().toString());
			callStmt.registerOutParameter(5, OracleTypes.INTEGER);
			callStmt.registerOutParameter(6, OracleTypes.VARCHAR);
			callStmt.execute();
			status = callStmt.getString(6);
			errorCode = callStmt.getString(5);
			if (flag == true && "0".equals(status)) {
				conn.commit();
			} else {
				conn.rollback();
			}
			prepStmt.close();
			callStmt.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		return errorCode;
	}

	/**
	 * 
	 * @param userId
	 * @param doctype
	 * @param fileName
	 * @param filePath
	 * @param loginId
	 * @return
	 * @throws Exception
	 */
	public boolean attachOfficalInfo(String userId, String doctype,
			String fileName, String filePath, String loginId) throws Exception {
		boolean retVal = false;
		PreparedStatement prepStmt = null;
		String sqlQuery = null;
		FileInputStream fileStream = null;
		File file;
		try {
			// TODO move sql
			sqlQuery = "INSERT INTO IGRS_EMP_DOCUMENT_DETAILS(DOCUMENT_NAME, EMP_ID, EMP_DOC_TYPE_ID, EMP_DOC_VALUE, CREATED_BY, EMP_DOC_STATUS,  DOCUMENT_ID, CREATED_DATE) VALUES (?,?,?,?,?,?,IGRS_EMP_DOCUMENT_DETAILS_SEQ.NEXTVAL, SYSDATE)";
			file = new File(filePath + fileName);
			fileStream = new FileInputStream(file);
			prepStmt = createPreparedStatement(sqlQuery);
			prepStmt.setString(1, fileName);
			prepStmt.setString(2, userId);
			prepStmt.setString(3, doctype);
			prepStmt.setBinaryStream(4, fileStream, fileStream.available());
			prepStmt.setString(5, loginId);
			prepStmt.setString(6, "A");
			retVal = prepStmt.executeUpdate() == 1 ? true : false;
			prepStmt.close();
			fileStream.close();
			file.delete();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		return retVal;
	}

	/**
	 * 
	 * @param filePath
	 * @param fileName
	 * @param licenceTxnId
	 * @return
	 * @throws Exception
	 */
	public boolean attachSpLicence(String filePath, String fileName,
			String licenceTxnId) throws Exception {

		boolean retVal = false;
		PreparedStatement prepStmt = null;
		String sqlQuery = null;
		FileInputStream fileStream = null;
		File file;
		try {
			// TODO move sql
			sqlQuery = "UDPATE IGRS_SP_USER_LICENSE_DETAILS SET DOCUMENT_OBJ = ? WHERE LICENSE_TXN_ID = ?";
			file = new File(filePath + fileName);
			fileStream = new FileInputStream(file);
			prepStmt = createPreparedStatement(sqlQuery);
			prepStmt.setBinaryStream(1, fileStream, fileStream.available());
			prepStmt.setString(2, licenceTxnId);
			retVal = prepStmt.executeUpdate() == 1 ? true : false;
			prepStmt.close();
			fileStream.close();
			file.delete();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		return retVal;
	}

	/**
	 * 
	 * @param advanceTxnId
	 * @return
	 */
	public ArrayList<String> calculateAdvanceEMIForApprovedOne(
			String advanceTxnId) {
		String status = "";
		int errorCode;
		ArrayList<String> list = new ArrayList<String>();
		CallableStatement callStmt;
		try {
			// TODO move procedure
			callStmt = createCallableStatement("call IGRS_ADVANCE_REPAYDTL_PROC(?,?,?,?,?,?,?)");
			callStmt.setString(1, advanceTxnId);
			callStmt.registerOutParameter(2, OracleTypes.INTEGER);
			callStmt.registerOutParameter(3, OracleTypes.INTEGER);
			callStmt.registerOutParameter(4, OracleTypes.INTEGER);
			callStmt.registerOutParameter(5, OracleTypes.INTEGER);
			callStmt.registerOutParameter(6, OracleTypes.INTEGER);
			callStmt.registerOutParameter(7, OracleTypes.VARCHAR);

			callStmt.execute();
			status = callStmt.getString(7);
			errorCode = callStmt.getInt(6);
			logger.debug("Status from procedure call : " + status);
			if (errorCode == 0) {
				list = new ArrayList<String>();
				// TODO Check is getString works
				// else replace with Integer.toString()
				list.add(callStmt.getString(2));
				list.add(callStmt.getString(3));
				list.add(callStmt.getString(4));
				list.add(callStmt.getString(5));
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return list;
	}

	public synchronized String savePreEnquiryDoc(String[] details,
			String filePath) throws Exception {
		String retVal = null, sequenceVal = "";
		boolean insertCheck = false;
		PreparedStatement prepStmt = null;
		String sqlQuery = null;
		FileInputStream fileStream = null;
		File file;
		try {
			// TODO move sql
			sqlQuery = "INSERT INTO IGRS_EMP_COMPLAINT_DOC_MAP (COMPLAINT_NO,DOCUMENT_NAME,DOCUMENT_OBJECT,DOCUMENT_TYPE,DOCUMENT_UPLOAD_DATE,DOCUMENT_ID) "
					+ " VALUES (?,?,?,?,?,IGRS_EMP_COM_DOC_MAP_SEQ.NEXTVAL)";

			file = new File(filePath + details[1]);
			fileStream = new FileInputStream(file);
			prepStmt = createPreparedStatement(sqlQuery);
			prepStmt.setString(1, details[0]);
			prepStmt.setString(2, details[1]);
			prepStmt.setBinaryStream(3, fileStream, fileStream.available());
			prepStmt.setString(4, details[2]);
			prepStmt.setString(5, details[3]);
			insertCheck = prepStmt.executeUpdate() == 1 ? true : false;
			prepStmt.close();
			// TODO move sql
			sqlQuery = "SELECT IGRS_EMP_COM_DOC_MAP_SEQ.CURRVAL AS SEQ FROM DUAL";
			sequenceVal = executeQry(sqlQuery).toString();
			fileStream.close();
			file.delete();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		} finally {
			if (insertCheck) {
				retVal = sequenceVal;
			} else {
				retVal = "false";
			}
		}
		return retVal;
	}

	public String insertIntoDetailsHistiory(ArrayList<LoanDTO> list,
			String userId, LoanDTO loanDTO) throws Exception {
		boolean flag = false;
		String status = "";
		String errorCode = "";
		String sqlQuery = null;
		String params[] = null;
		PreparedStatement prepStmt = null;
		CallableStatement callStmt = null;
		Connection conn = null;
		try {
			// TODO move
			sqlQuery = CommonSQL.UPDATE_LOAN_DETAILS_TRANSACTION_MASTER;
			LoanDTO loanamountDTO = (LoanDTO) list.get(0);
			params = new String[6];
			params[0] = loanDTO.getLoanstatus();
			params[1] = loanDTO.getComments();
			params[2] = userId;
			params[3] = userId;
			params[4] = loanamountDTO.getLoantxnid();
			params[5] = loanamountDTO.getEmpid();
			conn = dataSource.getConnectionFromDataSource(false);
			prepStmt = conn.prepareStatement(sqlQuery);
			flag = executeUpdate(params, prepStmt);
			// TODO move procedure
			sqlQuery = "call IGRS_LOAN_TXN_PROC(?,?,?,?,?,?,?)";

			callStmt = conn.prepareCall(sqlQuery);
			callStmt.setString(1, loanDTO.getLoantxnid().toString());
			callStmt.setString(2, loanDTO.getEmpid().toString());
			callStmt.setString(3, loanDTO.getLoanid().toString());
			callStmt.setString(4, loanDTO.getLoanamount().toString());
			callStmt.setString(5, loanDTO.getUserinstallment().toString());
			callStmt.registerOutParameter(6, OracleTypes.INTEGER);
			callStmt.registerOutParameter(7, OracleTypes.VARCHAR);

			callStmt.execute();
			status = callStmt.getString(7);
			errorCode = callStmt.getString(6);
			if (flag == true && status.equals("0")) {
				conn.commit();
			} else {
				conn.rollback();
			}
			prepStmt.close();
			callStmt.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		return errorCode;
	}

	public ArrayList<String> calculateLoanEMIForApprovedOne(String loanTxnId) {
		String status = "";
		int errorCode;
		// TODO Move procedure call
		String procedure = "call IGRS_LOAN_REPAYDTL_PROC(?,?,?,?,?,?,?)";
		ArrayList<String> list = null;
		CallableStatement callStmt = null;
		try {
			callStmt = createCallableStatement(procedure);
			callStmt.setString(1, loanTxnId);
			callStmt.registerOutParameter(2, OracleTypes.INTEGER);
			callStmt.registerOutParameter(3, OracleTypes.INTEGER);
			callStmt.registerOutParameter(4, OracleTypes.INTEGER);
			callStmt.registerOutParameter(5, OracleTypes.INTEGER);
			callStmt.registerOutParameter(6, OracleTypes.INTEGER);
			callStmt.registerOutParameter(7, OracleTypes.VARCHAR);
			callStmt.execute();
			status = callStmt.getString(7);
			errorCode = callStmt.getInt(6);
			logger.debug("Status from procedure call : " + status);
			if (errorCode == 0) {
				list = new ArrayList<String>();
				// TODO Check is getString works
				// else replace with Integer.toString()
				list.add(callStmt.getString(2));
				list.add(callStmt.getString(3));
				list.add(callStmt.getString(4));
				list.add(callStmt.getString(5));
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return list;
	}

	/**
	 * @param resp
	 * @param contentName
	 */
	private void setResponseContentHeader(HttpServletResponse resp,
			String contentName) {
		String ext;
		if (contentName != null) {
			ext = contentName.substring(contentName.length() - 3,
					contentName.length());
			logger.debug("File Extension is " + ext);
			if (ext != null) {
				if (ext.equalsIgnoreCase("doc")) {
					resp.setContentType("application/download");
				} else if (ext.equalsIgnoreCase("ppt")) {
					resp.setContentType("application/download");
				} else if (ext.equalsIgnoreCase("xls")) {
					resp.setContentType("application/download");
				} else if (ext.equalsIgnoreCase("htm")) {
					resp.setContentType("text/html");
				} else if (ext.equalsIgnoreCase("tml")) {
					resp.setContentType("text/html");
				} else if (ext.equalsIgnoreCase("txt")) {
					resp.setContentType("text/plain");
				} else if (ext.equalsIgnoreCase("pdf")) {
					resp.setContentType("application/pdf");
				} else if (ext.equalsIgnoreCase("bmp")) {
					resp.setContentType("image/x-bmp");
				} else if (ext.equalsIgnoreCase("gif")) {
					resp.setContentType("image/gif");
				} else if (ext.equalsIgnoreCase("jpg")) {
					resp.setContentType("image/jpeg");
				} else if (ext.equalsIgnoreCase("peg")) {
					resp.setContentType("image/jpeg");
				} else {
					resp.setContentType("application/download");
				}
			}
		}
		resp.setHeader("Content-Disposition", "attachment; filename="
				+ contentName);
	}

	/**
	 * @param resp
	 * @param buffer
	 * @param index
	 * @param fileBlob
	 * @throws IOException
	 * @throws SQLException
	 */
	private void writeBlobToResponse(HttpServletResponse resp, Blob fileBlob)
			throws IOException, SQLException {
		int buffer = 10000;
		long index = 1;
		OutputStream outStream;
		outStream = resp.getOutputStream();
		while (index < fileBlob.length()) {
			outStream.write(fileBlob.getBytes(index, buffer));
			outStream.flush();
			index += buffer;
		}
		outStream.close();
	}

	public String getDownload(HttpServletResponse resp, String contId) {
		String ext = null;
		String contentName = null;
		boolean value;
		String errorCode = null;
		String errorMsg = null;
		Blob fileBlob = null;
		CallableStatement callStmt = null;
		try {
			// TODO Move procedure call
			// Also check on stored proc availability
			callStmt = createCallableStatement("call downloadDepartmentEnqFileProc(?,?,?,?,?,?)");
			callStmt.setString(1, contId);
			callStmt.registerOutParameter(2, OracleTypes.BLOB);
			callStmt.registerOutParameter(3, OracleTypes.VARCHAR);
			callStmt.registerOutParameter(4, OracleTypes.VARCHAR);
			callStmt.registerOutParameter(5, OracleTypes.VARCHAR);
			callStmt.registerOutParameter(6, OracleTypes.VARCHAR);
			value = callStmt.execute();
			logger.debug("Stored procedure execute returned :" + value);
			contentName = callStmt.getString(3);
			ext = callStmt.getString(4);
			errorCode = callStmt.getString(5);
			errorMsg = callStmt.getString(6);
			fileBlob = callStmt.getBlob(2);
			logger.debug("Content Name : " + contentName);
			logger.debug("Ext : " + ext);
			logger.debug("Error Message : " + errorMsg);
			logger.debug("ErrorCode :" + errorCode);
			logger.debug("File length : " + fileBlob.length());

			setResponseContentHeader(resp, contentName);
			writeBlobToResponse(resp, fileBlob);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {

		}
		return errorCode + errorMsg;
	}

	public String getStampDuty(String deed, String instrument,
			String exemption, String marketValue) {
		CallableStatement callStmt = null;
		String stampDuty = null;
		boolean value;
		try {
			// TODO Move procedure call
			callStmt = createCallableStatement("call IGRS_STAMP_DUTY_PKG.IGRS_STAMP_DUTY_PROC(?,?,?,?,?,?,?,?)");
			callStmt.setString(1, deed);
			callStmt.setString(2, instrument);
			callStmt.setString(3, exemption);
			callStmt.setString(4, marketValue);
			callStmt.setString(5, null);
			callStmt.registerOutParameter(6, OracleTypes.NUMBER);
			callStmt.registerOutParameter(7, OracleTypes.VARCHAR);
			callStmt.registerOutParameter(8, OracleTypes.VARCHAR);
			value = callStmt.execute();
			if (!value) {
				stampDuty = callStmt.getString(6);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return stampDuty;
	}

	public int getOthersDuty(Object args[]) {
		CallableStatement callStmt = null;
		boolean value;
		int otherFee = 0;
		try {
			// TODO Move procedure call
			callStmt = createCallableStatement("call IGRS_SERVICE_FEE_PKG.IGRS_SERVICE_FEE_PROC(?,?,?,?,?,?)");
			callStmt.setObject(1, args[0]);
			callStmt.setObject(2, args[1]);
			callStmt.setObject(3, args[2]);
			callStmt.registerOutParameter(4, OracleTypes.NUMBER);
			callStmt.registerOutParameter(5, OracleTypes.CLOB);
			callStmt.registerOutParameter(6, OracleTypes.VARCHAR);
			value = callStmt.execute();
			if (!value) {
				otherFee = callStmt.getInt(4);
				logger.debug("otherFee : " + otherFee);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return otherFee;
	}

	public void readBLOBToFileGet(HttpServletResponse resp, String strDocId,
			String strFunctionName) throws Exception {
		String sqlQuery = null;
		String contentName = null;
		Blob fileBlob = null;
		PreparedStatement prepStmt = null;
		Object[] params = { strDocId };
		ArrayList<ArrayList<Object>> dataSet = null;
		ArrayList<Object> row = null;
		try {
			// TODO Move SQLs
			if ("EMPMANG".equals(strFunctionName)) {
				sqlQuery = "SELECT EMP_DOC_VALUE, DOCUMENT_NAME FROM IGRS_EMP_DOCUMENT_DETAILS WHERE DOCUMENT_ID = ? FOR UPDATE";
			} else if ("DEPTENQ".equals(strFunctionName)) {
				sqlQuery = "SELECT DOCUMENT_OBJECT, DOCUMENT_NAME FROM IGRS_EMP_COMPLAINT_DOC_MAP WHERE DOCUMENT_ID = ? FOR UPDATE";
			} else if ("INSPECTION".equals(strFunctionName)) {
				sqlQuery = "SELECT DOCUMENT_OBJECT, DOCUMENT_NAME FROM IGRS_SRO_INSP_DOC_MAP WHERE DOCUMENT_TXN_ID = ? FOR UPDATE";
			} else if ("POIINSPECTION".equals(strFunctionName)) {
				sqlQuery = "SELECT DOC_OBJECT, DOC_NAME FROM IGRS_POI_DOC_DTLS WHERE DOC_TXN_ID = ? FOR UPDATE";
			} else if ("SP".equals(strFunctionName)) {
				sqlQuery = "SELECT DOCUMENT_OBJ, DOCUMENT_NAME FROM IGRS_SP_USER_LICENSE_DETAILS WHERE LICENSE_TXN_ID = ? FOR UPDATE";
			}

			if (sqlQuery != null) {
				prepStmt = createPreparedStatement(sqlQuery,
						ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_UPDATABLE, false);
				dataSet = executeQuery(params, prepStmt);
				if (dataSet.size() > 0) {
					row = dataSet.get(0);
					fileBlob = (Blob) row.get(0);
					contentName = (String) row.get(1);
				}
			}
			logger.debug("Query returned contentName " + contentName);
			logger.debug("Query returned fileBlob " + fileBlob);
			if (contentName != null && fileBlob != null) {
				setResponseContentHeader(resp, contentName);
				writeBlobToResponse(resp, fileBlob);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}

	public boolean saveDRODocuments(String inspId, String docTxnId,
			String fileName, String filePath, String docTypeId) {
		String sqlQuery;
		int rowCount = 0;
		boolean retVal;
		PreparedStatement prepStmt = null;
		Object[] params;
		String docTypeIDInsert;
		File file;
		FileInputStream fileStream;
		try {
			// TODO Move SQLs
			sqlQuery = "SELECT DOCUMENT_TYPE_ID	FROM IGRS_DOCUMENT_TYPE_MASTER WHERE DOCUMENT_NAME=?";
			params = new Object[] { docTypeId };
			file = new File(filePath + fileName);
			fileStream = new FileInputStream(file);
			docTypeIDInsert = executeQry(params, sqlQuery).toString();
			sqlQuery = "INSERT INTO IGRS_DRO_INSP_DOC_MAP (DOCUMENT_TXN_ID, INSP_TXN_ID, DOCUMENT_NAME, DOC_TYPE_ID, DOCUMENT_OBJ) VALUES (?,?,?,?,?)";
			prepStmt = createPreparedStatement(sqlQuery);
			prepStmt.setString(1, docTxnId);
			prepStmt.setString(2, inspId);
			prepStmt.setString(3, fileName);
			prepStmt.setString(4, docTypeIDInsert);
			prepStmt.setBinaryStream(5, fileStream, fileStream.available());
			rowCount = prepStmt.executeUpdate();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (rowCount > 0) {
				retVal = true;
			} else {
				retVal = false;
			}
		}
		return retVal;
	}

	public HashMap<String, Object> calculatePayroll(String employeeid,
			String month) {
		HashMap<String, Object> allComponents = new HashMap<String, Object>();
		OracleCallableStatement oraCallStmt = null;
		String errorCode = null, errormsg = null;
		ResultSet cursorSet = null;
		boolean execFlag;
		int totalSal;
		try {
			// TODO move procedure call
			oraCallStmt = createOracleCallableStatement(
					"call get_emp_monthly_salary(?,?,?,?,?,?)", true);
			oraCallStmt.setString(1, employeeid);
			oraCallStmt.setString(2, month);
			oraCallStmt.registerOutParameter(3, OracleTypes.NUMBER);
			oraCallStmt.registerOutParameter(4, OracleTypes.CURSOR);
			oraCallStmt.registerOutParameter(5, OracleTypes.VARCHAR);
			oraCallStmt.registerOutParameter(6, OracleTypes.VARCHAR);
			execFlag = oraCallStmt.execute();
			if (!execFlag) {

				totalSal = oraCallStmt.getInt(3);
				cursorSet = oraCallStmt.getCursor(4);
				errorCode = oraCallStmt.getString(5);
				errormsg = oraCallStmt.getString(6);

				ArrayList<PayrollDTO> componentslist = new ArrayList<PayrollDTO>();
				PayrollDTO payrollDTO = null;

				while (cursorSet.next()) {
					payrollDTO = new PayrollDTO();
					payrollDTO.setComponentid(cursorSet
							.getString("COMPONENT_ID"));
					payrollDTO.setCoponentname(cursorSet
							.getString("COMPONENT_NAME"));
					payrollDTO.setSalaryamount(cursorSet
							.getString("COMPONENT_VALUE"));
					payrollDTO.setComponenttype(cursorSet
							.getString("COMPONENT_TYPE"));
					componentslist.add(payrollDTO);
				}
				allComponents.put("TOTALSAL", Integer.toString(totalSal));
				allComponents.put("COMPONENTS", componentslist);
				allComponents.put("ERRORCODE", errorCode);
				allComponents.put("ERRORMSG", errormsg);
			}
		} catch (Exception e) {
			logger.debug("Error " + e.getMessage());
		}
		return allComponents;
	}

	// TODO implement remaining functions

}