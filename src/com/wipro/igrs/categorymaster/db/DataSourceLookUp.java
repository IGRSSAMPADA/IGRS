package com.wipro.igrs.categorymaster.db;




//import oracle.jdbc.pool.OracleConnectionCacheImpl;


import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import oracle.jdbc.pool.OracleConnectionCacheImpl;
import oracle.jdbc.pool.OracleConnectionPoolDataSource;
import oracle.jdbc.pool.OracleDataSource;

import org.apache.log4j.Logger;

import com.wipro.igrs.categorymaster.util.PropertiesFileReader;


/**
 * 
 * @author Madan Mohan
 * File           :   DataSourceLookup.java
 * Description    :   Represents the Connection pool Class
 * Created Date   :   Nov 26, 2007
 *
 */
public class DataSourceLookUp {

    private static DataSource ds = null;
    /*private static Logger logger = null;
    private static OracleConnectionCacheImpl connCacheImpl;
    */
	
	/**
	 * @author Madan Mohan
	 */
	private static Logger logger = 
		(Logger) Logger.getLogger(DataSourceLookUp.class);
	private static OracleConnectionCacheImpl connCacheImpl;
    private static OracleConnectionPoolDataSource connPoolDataSrc;
    
	    
	/**
	 * @author Madan Mohan
	 */
	
	//private static OracleConnectionPoolDataSource connPoolDataSrc;
	
	/**
	 * 
	 * @throws Exception
	 */
    private DataSourceLookUp() throws Exception {
    }
    /**
     * 
     * @return OracleDataSource
     * @throws Exception
     */
    public static synchronized OracleDataSource getConnectionPool() throws Exception {
    	
    	OracleDataSource ds = new OracleDataSource();
	    try
	    {
	    	PropertiesFileReader pr =PropertiesFileReader.getInstance("resources.igrs");
            ds.setDriverType(pr.getValue("DRIVER_TYPE"));
            ds.setServerName(pr.getValue("SERVER_NAME"));
            ds.setDatabaseName(pr.getValue("DB_NAME"));
            ds.setUser( pr.getValue("DB_USER_ID"));
            ds.setPassword(pr.getValue("DB_USER_PASSWORD"));
            logger.debug("Server Name:-"+pr.getValue("SERVER_NAME")); 
      
            //ds.setNetworkProtocol("tcp");
            
            ds.setPortNumber(Integer.parseInt(pr.getValue("DB_PORT")));
           System.out.println(">>>>>>>>"+ds.getDatabaseName());

	    }
	    catch( Exception sqlEx) {
	    	ds = null;
	        throw sqlEx;
	    }
	    return ds;
    }
 
    public static synchronized Connection getConnectionPoolDataSource() throws Exception {
    	
    	//OracleDataSource ds = new OracleDataSource();
    	Connection conn;
	    try
	    {
	    	PropertiesFileReader pr = 
	    		PropertiesFileReader.getInstance("resources.igrs");
            
            logger.debug("Server Name:-"+pr.getValue("SERVER_NAME")); 
            
	    	 connPoolDataSrc = new OracleConnectionPoolDataSource();
	         connPoolDataSrc.setURL( pr.getValue("SERVER_NAME") );
	         connPoolDataSrc.setUser(  pr.getValue("DB_USER_ID") );
	         connPoolDataSrc.setPassword( pr.getValue("DB_USER_PASSWORD"));
	         connPoolDataSrc.setPortNumber(Integer.parseInt(pr.getValue("DB_PORT")));
	         connCacheImpl = new OracleConnectionCacheImpl( connPoolDataSrc );
	         connCacheImpl.setMaxLimit( Integer.parseInt(pr.getValue("MAX_DB_CONNECTION")) );
	         connCacheImpl.setMinLimit(Integer.parseInt(pr.getValue("MIN_DB_CONNECTION")) );
	    	
	         if( connCacheImpl == null )
	             throw new SQLException("No Connection Cache");
	             
	         conn = connCacheImpl.getConnection();
	         conn.setAutoCommit(true);
            //ds.setNetworkProtocol("tcp");
            
            //ds.setPortNumber(Integer.parseInt(pr.getValue("DB_PORT")));
           
            
	    }
	    catch( Exception sqlEx) {
	    	conn = null;
	        throw sqlEx;
	    }
	    return conn;
    }
   /*public static void main(String[] a) {
	   try
	    {
	    	PropertiesFileReader pr = 
	    		PropertiesFileReader.getInstance("com.wipro.igrs.igrs");
	    	logger.debug("Server Name:-"+pr.getValue("SERVER_NAME")); 
	    }catch( Exception sqlEx) {
		        logger.debug(sqlEx);
    	}
   }*/
    
    
    /**
     * stopConnectionPool
     * This method stops the connection pool 
     * @param none
     * @return void
     */
    
    /*
    public static void destroy() 
    {
        if( connCacheImpl != null ) 
        {
            try 
            {
                connCacheImpl.close();
            } 
            catch (SQLException e) 
            {
                e.printStackTrace();
            }
            connCacheImpl = null;
        }
    }
    */
    /**
     * Method 		: getDataSource
     * Description	: obtain a datasource object through jndi lookup
     * @return ds	: DataSource
     * @throws 		: Exception
     */
    
   /* public static DataSource getDataSource() throws Exception {
        try {
            //logger = (Logger)Logger.getInstance(DataSourceLookUp.class);
            System.out.println("in getdatasource method");
            if (ds == null) {
                System.out.println("in getdatasource method when ds is null");
                PropertiesFileReader pr = 
                PropertiesFileReader.getInstance("com.wipro.igrs.igrs");
                String contextLookup = pr.getValue("CONTEXT_LOOKUP");
                String jdbcLookup=pr.getValue("JDBC_JNDI");
                InitialContext initContext = new InitialContext();
                InitialContext envContext = 
                (InitialContext)initContext.lookup(contextLookup);
                DataSource ds = (DataSource)envContext.lookup(jdbcLookup);
                //InitialContext ic =  new InitialContext(); 
                 * // JNDI initial context
                //ds = (DataSource)ic.lookup("jdbc/IGRSDB"); 
                 * // JNDI lookup
                System.out.println("Came here in DataSourceLookUp.
                getDataSource()with DataSource = " + ds);
            }
        } catch (NamingException e) {
        }
        return ds;
    }*/
    public static DataSource getDataSourcePool() throws Exception {
        try {
            //logger = (Logger)Logger.getInstance(DataSourceLookUp.class);
            logger.debug("in getdatasource method");
           // if (ds == null) {
                logger.debug("in getdatasource method when ds is null");
                
                PropertiesFileReader pr = 
    	    		PropertiesFileReader.getInstance("resources.igrs");
                InitialContext initContext = new InitialContext();
                
               // context = new InitialContext();    
                ds = (DataSource) initContext.lookup(pr.getValue("JNDI_LOOKUP"));     
                
                logger.debug("Came here in DataSourceLookUp.getDataSource()with DataSource = " + ds);
           // }
        } catch (NamingException e) {
        	logger.debug(e);
        }
        return ds;
    }

}
