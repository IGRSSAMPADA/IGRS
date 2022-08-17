package com.wipro.igrs.db;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class DBUtilityTest {
	
	static Connection conn = null;
	  
	static private Logger logger = (Logger) Logger.getLogger(DBUtilityTest.class);

		public DBUtilityTest() throws Exception {
		}
		
		public static Connection openConnection() throws Exception {
		    conn = ConnectionPool.getInstance().getConnection();
		    return conn;
		}
		
		public static void closeConnection() throws Exception {
	            if (conn != null) {
	            	ConnectionPool.getInstance().closeConnection(conn);
	            }
	    }
		
		public void commit() throws Exception {
	        logger.debug("DB - commit()");
	        try {
	        	conn.commit();
	        } catch (SQLException sqle) {
	            logger.debug("DB - commit(): " + sqle.getMessage());
	            throw sqle;
	        }
	    }
		
		public void rollback() throws Exception {
	    	logger.debug("DB - rollback()");
	        try {
	        	conn.rollback();
	        } catch (SQLException sqle) {
	            logger.debug("DB - rollback(): " + sqle.getMessage());
	            throw sqle;
	        }
	    }
		
		@Override
		protected void finalize() throws Throwable {
			if(conn != null && (!conn.isClosed())) {
				try {
					ConnectionPool.getInstance().closeConnection(conn);
					logger.debug("Connection is closed***");
				} catch (Exception e) {
					logger.error("Error in finalize method :- "+e.getMessage());
				}finally {
					super.finalize();
				}
			}
		}

}
