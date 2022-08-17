package com.wipro.igrs.db;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import oracle.jdbc.pool.OracleDataSource;

import org.apache.log4j.Logger;

import com.wipro.igrs.util.PropertiesFileReader;

public final class ConnectionPool {
	
	private static ConnectionPool connectionPoolinstance;
	
	private static String INACTIVE_TIME = "connection.pool.inactive.time.inseconds";
	
	private static String INTERVAL_TIME = "connection.pool.interval.time.inseconds";
		
	private static DataSource ds;
    
    private static PropertiesFileReader pr;
    
    private static ResourceBundle prDatabase;
    
    private static String environment;
	
	private static List<ConnectionVO> connectionPool;
	
	private static long connInactiveTime;
	
	private static long time;
	
	private static long countTotal;
	
	private static long intervalTime;
	
	private static boolean isCleaning = false;
	
	private static Logger logger = Logger.getLogger(ConnectionPool.class);
	
	/*
	 * Run only once....
	 */
	static {
		try {
			logger.debug("Start ConnectionPool static block"); 
			time = System.currentTimeMillis();
			connectionPool = Collections.synchronizedList(new LinkedList<ConnectionVO>());
			pr = PropertiesFileReader.getInstance("resources.igrs");
			prDatabase = ResourceBundle.getBundle("resources.database");
			environment = pr.getValue("ENVIRONMENT");
			connInactiveTime = Long.parseLong(pr.getValue(INACTIVE_TIME));
			intervalTime = Long.parseLong(pr.getValue(INTERVAL_TIME));
			logger.debug("End ConnectionPool static block"); 
		} catch (Exception e) {
			connInactiveTime = 30;
			intervalTime = 45;
			logger.error("Error :- "+e.getMessage());
			e.printStackTrace();
		}
		
		if(connInactiveTime >= 45){
			connInactiveTime = 30;
		}
		
		intervalTime *= 1000;
		connInactiveTime *= 1000;
		
		logger.debug("Initalized DB connection pool "+INACTIVE_TIME+" : "+connInactiveTime);
	}
	
	/*
	 * Restrict instance creation.
	 */
	private ConnectionPool() throws Exception{
		logger.debug("Start ConnectionPool Constructor"); 
		initalizeDataSource();
		logger.debug("End ConnectionPool Constructor"); 
	}
	
	/**
	 * @throws Exception
	 */
	private void initalizeDataSource() throws Exception{
		logger.debug("Start ConnectionPool initalizeDataSource"); 
		
		if(environment.equalsIgnoreCase("UAT")){
			String dataSourceName = pr.getValue("DATA_SOURCE");
			
			logger.debug("Start InitialContext initalize..."); 
	    	Context ctx = new InitialContext();
	    	logger.debug("End InitialContext initalize..."); 
	    	
	    	logger.debug("Start DataSource initalize...dataSourceName :- "+dataSourceName); 
	    	ds = (javax.sql.DataSource)ctx.lookup(dataSourceName);
	    	logger.debug("End DataSource initalize...dataSourceName :- "+dataSourceName); 
	    	
		} else if(environment.equalsIgnoreCase("DEVELOPMENT")){
			OracleDataSource oracleDS = new OracleDataSource();
			oracleDS.setDriverType(prDatabase.getString("DRIVER_TYPE"));
			oracleDS.setServerName(prDatabase.getString("SERVER_NAME"));
			oracleDS.setDatabaseName(prDatabase.getString("DB_NAME"));
			oracleDS.setUser( prDatabase.getString("DB_USER_ID"));
			oracleDS.setPassword(prDatabase.getString("DB_USER_PASSWORD"));
			oracleDS.setPortNumber(Integer.parseInt(prDatabase.getString("DB_PORT")));
            logger.debug("Server Name:-"+prDatabase.getString("SERVER_NAME")); 
            logger.debug("DB Name:-"+prDatabase.getString("DB_NAME")); 
            logger.debug("User ID:-"+prDatabase.getString("DB_USER_ID"));
            ds = oracleDS;
        }
		
		logger.debug("End ConnectionPool initalizeDataSource"); 
	}
	
	/**
	 * @return Instance of this class.
	 * @throws Exception 
	 */
	public static ConnectionPool getInstance() throws Exception{
		if(connectionPoolinstance == null)
			connectionPoolinstance = new ConnectionPool();	
		return connectionPoolinstance;
	}
	
	/**
	 * @return
	 * @throws Exception
	 */
	public Connection getConnection() throws Exception{
		logger.debug("Start in getConnection method");
		try {
			cleanConnectionPool();
		} catch (Exception e) {
			logger.debug("Error in cleanConnectionPool :-" + e.getMessage());
		}
		
		Connection conn = null;
		try{
			conn = ds.getConnection();
			if(conn != null){
				connectionPool.add(new ConnectionVO(conn));
			}
			logger.debug("Number of open connection :- "+ noOfCurrentOpenConn() +" Number of connecton in pool :- "+connectionPool.size()+" conn :- "+conn); 
		}catch (Exception e) {
			logger.debug("Error in getting conection :- "+e.getMessage());
			e.printStackTrace();
		}
		logger.debug("End in getConnection method :- "+ conn);
		return conn;
	}
	
	/**
	 * @param conn
	 * @throws Exception
	 */
	public void closeConnection(Connection conn) throws Exception{
		logger.debug("Start in closeConnection() method");
		if ( conn != null){
			if(!conn.isClosed()){
				synchronized (conn) {
					conn.close();
				}
			}
			connectionPool.remove(new ConnectionVO(conn));
		}
		logger.debug("End in closeConnection() method");
	}
	
	/**
	 * This method should be called each time to clean connection's in pool.
	 */
	private void cleanConnectionPool() {
		logger.debug("Start in cleanConnectionPool() method");
		if(System.currentTimeMillis() - time >= intervalTime && !isCleaning){
			isCleaning = true;
			logger.debug("cleanning ConnectionPool......");
			long time1 = System.currentTimeMillis();
			List<ConnectionVO> connectionPoolTemp = new LinkedList<ConnectionVO>();
			synchronized (ConnectionPool.connectionPool){
				connectionPoolTemp.addAll(ConnectionPool.connectionPool);
			}
			long numConn = connectionPoolTemp.size();
			int cleanedConn = 0;
			ArrayList<ConnectionVO> tempPool = new ArrayList<ConnectionVO>(10);
			
			boolean isFullCleanReq = false;
			for (ConnectionVO connectionVO : connectionPoolTemp) {
				if(connectionVO != null && connectionVO.getConnection() != null){
					try {
						Connection conn = connectionVO.getConnection();
						if (conn.isClosed()) {
							tempPool.add(connectionVO);
						} else if ((System.currentTimeMillis() - connectionVO.getTimeStamp()) >= connInactiveTime) {
							synchronized (conn) {
								conn.close();
								tempPool.add(connectionVO);
								cleanedConn++;
							}
						}
					} catch (Exception e) {
						logger.error("Error in connection :- " + e.getMessage());
						e.getStackTrace();
					}
				} else if(connectionVO != null && connectionVO.getConnection() == null){
					isFullCleanReq = true;
				}
			}
			
			if((countTotal += cleanedConn) < 0){
				countTotal = cleanedConn;
			}
			
			if(!tempPool.isEmpty()){
				connectionPoolTemp.removeAll(tempPool);
				ConnectionPool.connectionPool.removeAll(tempPool);
			}
			
			if(isFullCleanReq){
				ArrayList<ConnectionVO> tempPool2 = new ArrayList<ConnectionVO>(10);
				synchronized (ConnectionPool.connectionPool){
					for (ConnectionVO connVo : ConnectionPool.connectionPool) {
						if(connVo != null && connVo.getConnection() != null){
							tempPool2.add(connVo);
						}
					}
					ConnectionPool.connectionPool.clear();
					ConnectionPool.connectionPool.addAll(tempPool2);
					tempPool2.clear();
				}
				logger.debug("###### Full clean done for Connection Pool. current size is :- "+connectionPoolTemp.size());
			}
			
			time = System.currentTimeMillis();
			logger.debug("##### @@@@ Millis :-"+(time - time1)+"  "+cleanedConn+" : "+(tempPool.size()- cleanedConn)+" : "+
					countTotal+" ConnPolStart :- "+numConn+" ConnPolEnd :- "+connectionPoolTemp.size());
			
			connectionPoolTemp.clear();
			tempPool.clear();
			isCleaning = false;
		}
		logger.debug("End in cleanConnectionPool() method");
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return this;
	}
	
	private int noOfCurrentOpenConn() {
		int num = 0;
		synchronized (connectionPool) {
			for (ConnectionVO connectionVO : connectionPool) {
				if (connectionVO != null && connectionVO.getConnection() != null) {
					try {
						if (!connectionVO.getConnection().isClosed()) {
							num++;
						}
					} catch (Exception e) {
						logger.error("Error in counting connection :- "+ e.getMessage());
					}
				}
			}
		}
		
		return num;
	}
	
	public DataSource getDs(){
		return ds;
	}
	
}
