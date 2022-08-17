package com.wipro.igrs.categorymaster.db;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import oracle.jdbc.OracleResultSet;
import oracle.jdbc.driver.OracleTypes;
import oracle.jdbc.pool.OracleDataSource;
import oracle.sql.BLOB;

import org.apache.log4j.Logger;

//import com.wipro.igrs.loanadvance.dto.LoanDTO;
//import com.wipro.igrs.loanadvance.dto.LoanadvanceDTO;

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
public class DBUtility {
    Connection con = null;
    Statement st = null;
    PreparedStatement pst = null;
    ResultSet rst = null;
    CallableStatement clstmt = null;
     
	private CallableStatement callstmt  ;
	private Logger logger = (Logger) Logger.getLogger(DBUtility.class);
    //MessagePropertiesReader msgReader = null;

    /**
     * Constructor 	: DBUtility
     * Description	: Default Constructor for DBUtility Class
     * @throws 		: TKMLException
     */
    public DBUtility() throws Exception {
       logger = (Logger) Logger.getInstance(this.getClass());
       logger.debug("DBUtility() constructor");
        openConnection();
    }

    private void openConnection() throws Exception {
    	logger.info("DBUtility - openConnection()");
        try {
            //DataSource ds = DataSourceLookUp.getDataSource();
        	OracleDataSource ds = DataSourceLookUp.getConnectionPool();
            if (ds == null) {
            	logger.info("DBUtility - openConnection(): NullPointerException - ds");
                throw new NullPointerException();

            }
            con = ds.getConnection();
        } catch (SQLException sqle) {
           logger.debug(sqle);
            throw sqle;
        }
    }

    /**
     * Method 		: createStatement
     * Description	: Method to Statement Object
     * @throws 		:  Exception
     */
    public synchronized void createStatement() throws Exception {
    	logger.debug("DBUtility - createStatement()");
        try {
            //This makes the resultset scrollable
            st =con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException sqle) {
            logger.debug(sqle);
            throw sqle;
        }
    }

    /**
     * Method 		: createPreparedStatement
     * Description	: Creates a PreparedStatement object
     * @param query : String
     * @throws 		: Exception
     */
    public synchronized void createPreparedStatement(String query) throws Exception {
        try {
        	logger.debug("DBUtility - createPreparedStatement(String query) = " + query);
            pst = con.prepareStatement(query);
        } catch (SQLException sqle) {
            logger.debug(sqle);
            throw sqle;
        }
    }

    /**
     * Method 		: returnPreparedStatement
     * Description	: Creates a PreparedStatement object for Doucment UpLoad
     * @param query : String
     * @throws 		: Exception
     */

    public synchronized PreparedStatement returnPreparedStatement(String SQL) throws Exception {
		   try {
		   		pst = con.prepareStatement(SQL);
		   } catch (SQLException sqle) {
			   logger.debug(sqle);
		       throw sqle;
		   }
		   return pst;
   }

    /**
     * Method 		: createPreparedStatement
     * Description	: Creates a PreparedStatement object
     * @param query : String
     * @throws 		: Exception
     */
    public synchronized void createCallableStatement(String procedure) throws Exception {
        try {
        	logger.debug("DBUtility - createCallableStatement(String procedure) = " + procedure);
            clstmt = con.prepareCall(procedure);
        } catch (SQLException sqle) {
            logger.debug(sqle);
            throw sqle;
        }
    }

    /**
     * Method 		: executeQuery
     * Description	: Method to execute simple select queries
     * @param query : String
     * @return list : ArrayList
     * @throws 		: Exception
     */
    public synchronized ArrayList executeQuery(String query) throws Exception {
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
            logger.debug("DBUtility - executeQuery(String): " +
                               sqle.getMessage());
            throw sqle;
        } catch (NumberFormatException nfe) {
           logger.debug("DBUtility - executeQuery(String): " +
                               nfe.getMessage());
            throw nfe;
        } catch (IllegalArgumentException iae) {
            logger.debug("DBUtility - executeQuery(String): " +
                               iae.getMessage());
            throw iae;
        }
        list.trimToSize();
        return list;
    }

    /**
     * Method 		: executeQuery
     * Description	: Execute a query with PreparedStatement
     * @param arr   : String []
     * @return list : ArrayList
     * @throws 		: Exception
     */
    public synchronized ArrayList executeQuery(String[] arr) throws Exception {
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
                    row_list.add(rst.getString(i));
                } //for
                list.add(row_list);
            } //while
            pst.clearParameters();
        } catch (SQLException sqle) {
            logger.debug("DBUtility - executeQuery(String[]): " +
                               sqle.getMessage());
            throw sqle;
        } catch (NumberFormatException nfe) {
            logger.debug("DBUtility - executeQuery(String[]): " +
                               nfe.getMessage());
            throw nfe;
        } catch (IllegalArgumentException iae) {
            logger.debug("DBUtility - executeQuery(String[]): " +
                               iae.getMessage());
            throw iae;
        }
        list.trimToSize();
        return list;
    }


    /**
     * Method 		: executeCallableQuery
     * Description	: Execute a query with CallableStatement
     * @param arr   : String []
     * @return list : ArrayList
     * @throws 		: Exception
     */
    public synchronized String authenticateUser(String userId, String pwd,
                                   String noattempt) throws Exception {
    	logger.debug("DBUtility - authenticateUser()");
        String status = "dd";
        try {
            clstmt.setString(1, userId);
            clstmt.setString(2, pwd);
            clstmt.registerOutParameter(4, Types.VARCHAR);
            //clstmt.registerOutParameter(4, Types.INTEGER);
            clstmt.setInt(3, Integer.parseInt(noattempt));
            clstmt.execute();
            status = clstmt.getString(4);
            logger.debug("Type:-" + status);
        } catch (SQLException sqle) {
            logger.debug("DBUtility - authenticateUser(String[]): " +
                               sqle.getMessage());
            throw sqle;
        } catch (NumberFormatException nfe) {
            logger.debug("DBUtility - authenticateUser(String[]): " +
                               nfe.getMessage());
            throw nfe;
        } catch (IllegalArgumentException iae) {
            logger.debug("DBUtility - authenticateUser(String[]): " +
                               iae.getMessage());
            throw iae;
        }

        return status;
    }

    /**
     * Method 		: executeUpdate
     * Description	: execute update,insert,delete  query using prepared statement
     * @param arr	: String[]
     * @return boolean
     * @throws 		: Exception
     */
    public synchronized boolean executeUpdate(String[] arr) throws Exception {

        int j = 0;
        try {
            for (int i = 0; i < arr.length; i++) {
            	logger.debug("Wipro : arr[" + i + "] = " + arr[i]);
                pst.setString(i + 1, arr[i]);
            }
            logger.debug("Wipro : before execute update");
            try {
                j = pst.executeUpdate();
            } catch (StringIndexOutOfBoundsException e) {
                logger.debug("Wipro in executeUpdate StringIndexOutOfBoundsException = " +  e.getMessage());
            }
            logger.debug("Wipro : after execute update");
            pst.clearParameters();
        } catch (SQLException sqle) {
            logger.debug("DBUtility - executeUpdate(String[]): " +
                               sqle.getMessage());
           
            throw sqle;
        } catch (NumberFormatException nfe) {
            logger.debug("DBUtility - executeUpdate(String[]): " +
                               nfe.getMessage());
            throw nfe;
        } catch (IllegalArgumentException iae) {
            logger.debug("DBUtility - executeUpdate(String[]): " +
                               iae.getMessage());
            throw iae;
        } catch (StringIndexOutOfBoundsException sioobe) {
            logger.debug("DBUtility - executeUpdate(String[]): " +
                               sioobe.getMessage());
            throw sioobe;
        }
        if (j == 0)
            return false;
        return true;
    }

    public synchronized boolean executeUpdateDouble(double d, String str) throws Exception {
        int j = 0;
        try {
            pst.setDouble(1, d);
            pst.setString(2, str);
            j = pst.executeUpdate();
            pst.clearParameters();
        } catch (SQLException sqle) {
            logger.debug("DBUtility - executeUpdate(String[]): " +
                               sqle.getMessage());
            throw sqle;
        }
        if (j == 0)
            return false;
        return true;
    }

    /**
     * Method 		: executeQry
     * Description	: Execute a query with PreparedStatement
     * @param arr   : String []
     * @return record : String
     * @throws 		: Exception
     */
    public synchronized String executeQry(String[] arr) throws Exception {
    	logger.debug("DBUtility - executeQry(String arr[])");
        String record = "";
        try {
            for (int i = 0; i < arr.length; i++) {
                pst.setString(i + 1, arr[i]);
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
            logger.debug("DBUtility - executeQry(String[]): " +
                               sqle.getMessage());
            throw sqle;
        } catch (NumberFormatException nfe) {
            logger.debug("DBUtility - executeQry(String[]): " +
                               nfe.getMessage());
            throw nfe;
        } catch (IllegalArgumentException iae) {
            logger.debug("DBUtility - executeQry(String[]): " +
                               iae.getMessage());
            throw iae;
        }
        return record;
    }

    /**
     * Method 		: executeQry
     * Description	: Execute a query with Statement
     * @param arr   : String
     * @return record : String
     * @throws 		: Exception
     */
    public synchronized String executeQry(String query) throws Exception {
    	logger.debug("DBUtility - executeQry(String) query = " + query);
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
            logger.debug("DBUtility - executeQry(String): " +
                               sqle.getMessage());
            throw sqle;
        } catch (NumberFormatException nfe) {
            logger.debug("DBUtility - executeQry(String): " +
                               nfe.getMessage());
            throw nfe;
        } catch (IllegalArgumentException iae) {
            logger.debug("DBUtility - executeQry(String): " +
                               iae.getMessage());
            throw iae;
        }
        return record;
    }

    /**
     * Method 		: setAutoCommit
     * Description	: method to enable or disable autocommit mode of connection oblect
     * @param flag	: boolen
     * @throws 		: Exception
     */
    public synchronized void setAutoCommit(boolean flag) throws Exception {
        try {
            con.setAutoCommit(flag);
        } catch (SQLException sqle) {
            logger.debug("DBUtility - setAutoCommit(boolean): " +
                               sqle.getMessage());
            throw sqle;
        }
    }

    /**
     * Method 		: commit
     * Description	: Commits a Transaction
     * @throws 		: Exception
     */
    public synchronized void commit() throws Exception {
        logger.debug("DBUtility - commit()");
        try {
            con.commit();
        } catch (SQLException sqle) {
            logger.debug("DBUtility - commit(): " + sqle.getMessage());
            throw sqle;
        }
    }

    /**
     * Method 		: rollback
     * Description	: Rollback a Transaction
     * @throws 		: Exception
     */
    public synchronized void rollback() throws Exception {
    	logger.debug("DBUtility - rollback()");
        try {
            con.rollback();
        } catch (SQLException sqle) {
            logger.debug("DBUtility - rollback(): " + sqle.getMessage());
            throw sqle;
        }
    }

    /**
     * Method 		: closeConnection
     * Description	: Closes a Connection
     * @throws 		: Exception
     */
    public synchronized   void closeConnection() throws Exception {
    	logger.debug("DBUtility - closeConnection()");
        try {
            if (con != null) {
            	logger.debug("DBUtility - closeConnection() - con is not null");
                //st.close();
                //pst.close();
                //rst.close();
                con.close();
            }
        } catch (SQLException sqle) {
            logger.debug("DBUtility - closeConnection(): " +
                               sqle.getMessage());
            throw sqle;
        }
    }

    /**
     * Method 		: isClosed
     * Description	: Check the status of connection
     * @return flag : boolean
     * @throws 		: Exception
     */
    public synchronized boolean isClosed() throws Exception {
    	logger.debug("DBUtility - isClosed()" );
        boolean flag = true;
        try {
            flag = con.isClosed();
        } catch (SQLException sqle) {
            logger.debug("DBUtility - isClosed(): " + sqle.getMessage());
            throw sqle;
        }
        return flag;
    }

    /*public static void main(String[] arg) {

           try {
                 DBUtility dbUtil=new DBUtility();
                 dbUtil.createCallableStatement("call IGRS_VALID_USER(?,?,?,?)");
         		 String status=dbUtil.authenticateUser("igrsdrs","igrsdrs" , "3");
         		 logger.debug("Status:-"+status);

                 dbUtil.createPreparedStatement("INSERT INTO IGRS_REGISTERED_USER_EXTERNAL(USER_ID,BIRTH_DATE) VALUES(?,?)");
                 String[] param=new String[2];
                 param[0]="qq";
                 param[1]="12-02-2007";
                 dbUtil.executeUpdate(param);
                 dbUtil.commit();
           }catch(Exception x){
                logger.debug(x);
           }
    }
*/

    /**
     * Method 		: executeCallableQuery
     * Description	: Execute a query with CallableStatement
     * @param arr   : String []
     * @return list : ArrayList
     * @throws 		: Exception
     */
    public synchronized String getStampDutyValue(String deedTypeID, String instrumentID,String exemptionID[]) throws Exception {
        logger.debug("DBUtility - getStampDutyValue()");

        String status="dd";
        int counter=4;
        Object [][] 		totRecordObjArray ;
        totRecordObjArray = new Object[counter][1];
        DBUtility dbUtil=new DBUtility();
        ArrayList exempList=new ArrayList();
        exempList.add(0,"1");
        exempList.add(1,"2");
        exempList.add(2,"3");


        try {
        	clstmt.setString(1,deedTypeID);
        	clstmt.setString(2, instrumentID);
        	String temp[] = {"1","2"};

        	clstmt.registerOutParameter(4, OracleTypes.NUMBER);
        	clstmt.execute();

        } catch (Exception e) {
            logger.debug("in method:");
            e.printStackTrace();
        }

        return status;
    }
    /**
     * for calculating stamp duty
     * @param deed
     * @param instrument
     * @param exemptionIDs
     * @return Stamp Duty
     */

  public synchronized String getNonJudStampDuty(String _refDeedTypeId, String  _refInstrId,String  _refExemId){
  CallableStatement cst = null;
  String stampDuty = null;
  logger.debug("DBUtility - getNonJudStampDuty()");
    try {
          DBUtility db = new DBUtility();
          db.createCallableStatement("call IGRS_STAMP_DUTY.IGRS_STAMP_DUTY_CALC3(?,?,?,?,?)");
          cst = db.clstmt;
          //_refExemId="1,2";
          logger.debug("DBUtility - getNonJudStampDuty()"+_refDeedTypeId);
          logger.debug("DBUtility - getNonJudStampDuty()"+_refInstrId);
          logger.debug("DBUtility - getNonJudStampDuty()"+_refExemId);
          cst.setString(1,_refDeedTypeId);
          cst.setString(2,_refInstrId);
          cst.setString(3, _refExemId);
          cst.setString(4, null);
          cst.registerOutParameter(5, OracleTypes.NUMBER);

          if(!cst.execute()){
                int temp = cst.getInt(5);
                stampDuty = String.valueOf(temp);
          }
          } catch (Exception e) {
          e.printStackTrace();
    }
          logger.debug("DBUtility - getNonJudStampDuty() stamp Duty"+stampDuty);
    return stampDuty;
  }
  /**
   * for calculating stamp duty for Judicial
   * @param catId
   * @param basevalue
   * @param units
   * @return Stamp Duty
   */

public synchronized String getJudStampDuty(String _refcatId,String _refBaseVal,String _refUnits){
	  CallableStatement cst = null;
	  String stampDuty = null;
	  logger.debug("DBUtility - getJudStampDuty() stamp id"+_refcatId);

	    try {
	          DBUtility db = new DBUtility();
	          db.createCallableStatement("call IGRS_STAMP_DUTY_JUDICIAL_CALC.IGRS_JUDICIAL_SDUTY_CALC(?,?,?,?)");
	          cst = db.clstmt;

	          cst.setString(1,_refcatId);
	          cst.setString(2,_refBaseVal);
	          cst.setString(3, _refUnits);
	          cst.registerOutParameter(4, OracleTypes.NUMBER);

	          if(!cst.execute()){
	                int temp = cst.getInt(4);
	                stampDuty = String.valueOf(temp);
	          }
	          } catch (Exception e) {
	          e.printStackTrace();
	    }
	          logger.debug("DBUtility - getJudStampDuty() stamp Duty"+stampDuty);
	    return stampDuty;
	  }
/**
 * for calculating  othersFeeDuty
 * @param _refFunId
 * @return othersFeeDuty
 */
public synchronized String getOthersFeeDuty(String _refFunId){
	  CallableStatement cst = null;
	  String othersFeeDuty = null;
	  logger.debug("DBUtility - getOthersFeeDuty()");

	    try {
	          DBUtility db = new DBUtility();
	          db.createCallableStatement("call IGRS_SERVICE_FEE_PKG.IGRS_SERVICE_FEE_PROC(?,?)");
	          cst = db.clstmt;

	          cst.setString(1,_refFunId);
	          cst.registerOutParameter(2, OracleTypes.NUMBER);

	          if(!cst.execute()){
	                int temp = cst.getInt(2);
	                othersFeeDuty = String.valueOf(temp);
	          }
	          } catch (Exception e) {
	          e.printStackTrace();
	    }
	          logger.debug("DBUtility - getOthersFeeDuty() othersFeeDuty"+othersFeeDuty);
	    return othersFeeDuty;
	  }
/**
 * Method 		: executeQry
 * Description	: Execute a query with Statement
 * @param arr   : String
 * @return record : String
 * @throws 		: Exception
 */
public synchronized int executeUpdt(String query) throws Exception {
    logger.debug("DBUtility - executeUpdt(String) query = " + query);
    int result = 0;
    try {
    	logger.debug("before run"+st);
    	result = st.executeUpdate(query);
    	logger.debug("after run"+result);

    } catch (SQLException sqle) {
        logger.debug("DBUtility - executeQry(String): " +
                           sqle.getMessage());
        throw sqle;
    } catch (NumberFormatException nfe) {
        logger.debug("DBUtility - executeQry(String): " +
                           nfe.getMessage());
        throw nfe;
    } catch (IllegalArgumentException iae) {
        logger.debug("DBUtility - executeQry(String): " +
                           iae.getMessage());
        throw iae;
    }
    return result;
}
/**
 * for calculating stamp duty
 * @param deed
 * @param instrument
 * @param exemptionIDs
 * @return Stamp Duty
 */
public synchronized String getStampDuty(String deed,String instrument,String exemption){
CallableStatement cst = null;
String stampDuty = null;
try {
	DBUtility db = new DBUtility();
	db.createCallableStatement("call IGRS_STAMP_DUTY.IGRS_STAMP_DUTY_CALC3(?,?,?,?,?)");
	cst = db.clstmt;

	cst.setString(1,deed);
	cst.setString(2,instrument);
	cst.setString(3,exemption);
	cst.setString(4, null);
	cst.registerOutParameter(5, OracleTypes.NUMBER);

	if(!cst.execute()){
		int temp = cst.getInt(5);
		stampDuty = String.valueOf(temp);
	}
	} catch (Exception e) {
	e.printStackTrace();
}
return stampDuty;
}

/**
 * for calculating Other Fee
 * @param function_id
 * @return otherFee
 */
public synchronized int getOthersDuty(String function_id){
CallableStatement cst = null;
int otherFee = 0;
try {
	DBUtility db = new DBUtility();
	db.createCallableStatement("call IGRS_SERVICE_FEE_PKG.IGRS_SERVICE_FEE_PROC(?,?)");
	cst = db.clstmt;
	cst.setString(1,function_id);
	cst.registerOutParameter(2, OracleTypes.NUMBER);
	if(!cst.execute()){
		otherFee = cst.getInt(2);
	}
	} catch (Exception e) {
	e.printStackTrace();
}
return otherFee;
}

/**
 * for calculating  othersFeeDuty
 * @param _refFunId
 * @return othersFeeDuty
 */
public synchronized String getWillOtherFeeValue(String _refFunId){
	CallableStatement cst = null;
	String othersFeeDuty = null;
	try {
		DBUtility db = new DBUtility();
		db.createCallableStatement("call IGRS_SERVICE_FEE_PKG.IGRS_SERVICE_FEE_PROC(?,?)");
		cst = db.clstmt;
		cst.setString(1,_refFunId);
		cst.registerOutParameter(2, OracleTypes.NUMBER);
		if(!cst.execute()){
			int temp = cst.getInt(2);
			othersFeeDuty = String.valueOf(temp);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	logger.debug("DBUtility:: getWillOtherFeeValue():: othersFeeDuty:-      "+othersFeeDuty);
	return othersFeeDuty;
}

/**
 * Method 		: createPreparedStatement
 * Description	: Creates a PreparedStatement object
 * @param query : String
 * @throws 		: Exception
 */
public synchronized CallableStatement returnCallableStatement(String procedure) throws
     Exception {
    try {
    	callstmt = con.prepareCall(procedure);
    } catch (SQLException sqle) {
        //logger.debug(sqle);
        throw sqle;
    }
    return callstmt;
}

/**
 * Method 		: executeQuery1
 * Description	: Execute a query with PreparedStatement
 * purpose      : To Deal with 'LIKE' search
 * @param arr   : String []
 * @return list : ArrayList
 * @throws 		: Exception
 */
public synchronized ArrayList executeQueryLikeSearch(String regid) throws Exception{
   logger.debug("DBUtility - executeQuery()");
   ArrayList list = new ArrayList();
   try {
     /*  for (int i = 0; i < arr.length; i++) {
           logger.debug("Wipro : arr[" + i + "] = " + arr[i]);
           pst.setString(i+1,"%"+ arr[i]+"%");
       }*/
	   pst.setString(1, "%"+regid+"%");
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
       logger.debug("DBUtility - executeQuery(String[]): " +
                          sqle.getMessage());
       throw sqle;
   } catch (NumberFormatException nfe) {
       logger.debug("DBUtility - executeQuery(String[]): " +
                          nfe.getMessage());
       throw nfe;
   } catch (IllegalArgumentException iae) {
       logger.debug("DBUtility - executeQuery(String[]): " +
                          iae.getMessage());
       throw iae;
   }
   list.trimToSize();
   return list;
}
public synchronized boolean attachAGMPAuditReport(String docId,String filePath,String fileName,String[] OthDetails,String functionName) throws Exception
{
	boolean check     = true;
	String  returnVal = null;
	String  sqlText   = null;
	BLOB    image     = null;
	int     chunkSize;
	byte[]  binaryBuffer;
	long                position;
	int                 bytesRead               = 0;
	int                 bytesWritten            = 0;
	int                 totbytesRead            = 0;
	int                 totbytesWritten         = 0;
	
	//String strSeq = null;
	
   try
	  {
	    int j=0;
	    File file = new File(filePath+fileName);
	    InputStream ioFile = new FileInputStream(file);
	    
	    //FileInputStream is = new FileInputStream(filePath+fileName);
	    if(functionName.equalsIgnoreCase("audit")){
	    sqlText= "INSERT INTO IGRS_RAUDIT_DOCUMENT_DETAILS"+
         "(DOCUMENT_TXN_ID, DOCUMENT_FILE_NAME, DOCUMENT_FILE, DOCUMENT_STATUS, CREATED_BY, CREATED_DATE, UPDATE_BY, UPDATE_DATE) "+ 
         "VALUES('"+docId+"','"+fileName+"',EMPTY_BLOB(),'"+OthDetails[0]+"','"+OthDetails[1]+"',SYSDATE,'"+OthDetails[2]+"',SYSDATE)";  
	    }
	    else if(functionName.equalsIgnoreCase("inspection")){
	    	sqlText= "INSERT INTO IGRS_POI_DOC_DTLS"+
	         "(DOC_TXN_ID, DOC_NAME, DOC_OBJECT, DOC_STATUS, CREATED_BY, CREATED_DATE, UPDATE_BY, UPDATE_DATE) "+ 
	         "VALUES('"+docId+"','"+fileName+"',EMPTY_BLOB(),'"+OthDetails[0]+"','"+OthDetails[1]+"',SYSDATE,'"+OthDetails[2]+"',SYSDATE)";  
	    }
	   //CommonSQL.insert_IGRS_AUDIT_DOC_MAPPING;
	     
	    st = con.createStatement();
	    st.executeUpdate(sqlText);
        
	    logger.debug("After insert ");
	    if(functionName.equalsIgnoreCase("audit")){
	    sqlText = 
            "SELECT DOCUMENT_FILE " +
            "FROM   IGRS_RAUDIT_DOCUMENT_DETAILS " +
            "WHERE  DOCUMENT_TXN_ID ='"+docId+"' FOR UPDATE";
	    }
	    else if(functionName.equalsIgnoreCase("inspection")){
	    	sqlText = 
	            "SELECT DOC_OBJECT " +
	            "FROM   IGRS_POI_DOC_DTLS " +
	            "WHERE  DOC_TXN_ID ='"+docId+"' FOR UPDATE";
	    }
	    
        rst = st.executeQuery(sqlText);
        rst.next();
        if(functionName.equalsIgnoreCase("audit")){
         image = ((OracleResultSet) rst).getBLOB("DOCUMENT_FILE");
        }
        else if(functionName.equalsIgnoreCase("inspection")){
        	image = ((OracleResultSet) rst).getBLOB("DOC_OBJECT");
        }
	    
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

    	  } catch (IOException e) {
    	       logger.debug("Caught I/O Exception: (Write BLOB value - Put Method).");
    	       e.printStackTrace();
    	       check=false;
    	       //throw e;
    	   } catch (SQLException e) {
    	       logger.debug("Caught SQL Exception: (Write BLOB value - Put Method).");
    	       logger.debug("SQL:\n" + sqlText);
    	       e.printStackTrace();
    	       check=false;
    	       //throw e;
    	   }
    	  
    	   	if(check){
    	   		con.commit();
    	   	}
    	   	else{
    	   		con.rollback();
    	   	}
      
        return check;
      }

public synchronized boolean saveSroDocuments(String docTxnId,String inspectionId,String filename,String filesPath)
{
	boolean flag = true;
	int i = 0;
	   String              sqlText                 = null;
	   BLOB                image                   = null;
	   int                 chunkSize;
	   byte[]              binaryBuffer;
	   long                position;
	   int                 bytesRead               = 0;
	   int                 bytesWritten            = 0;
	   int                 totbytesRead            = 0;
	   int                 totbytesWritten         = 0;
	   File file=null;
	try {
		logger.debug("File path :"+filesPath+filename);
	    file = new File(filesPath+filename);
	    InputStream ioFile = new FileInputStream(file);
	    st = con.createStatement();
	   
	   
	    //BLOB reading as well as writting in to the Database START
	    //before that we need to get the sequence number
        sqlText ="INSERT INTO IGRS_SRO_INSP_DOC_MAP"
    		+"(INSP_TXN_ID,DOCUMENT_TXN_ID,DOCUMENT_NAME,DOCUMENT_OBJ) "
    		+" VALUES('"+inspectionId+"','"+docTxnId+"', '"+filename+"',EMPTY_BLOB())";
    	
        logger.debug("before insert query :"+sqlText);
        st.executeUpdate(sqlText);
        logger.debug("After insert ");
        sqlText = 
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
		
	} catch (FileNotFoundException e) {
		try {
			con.rollback();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		e.printStackTrace();
		file.delete();
		flag=false;
	} catch (SQLException e) {
		e.printStackTrace();
		try {
			con.rollback();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		file.delete();
		flag=false;
	} catch (IOException e) {
		e.printStackTrace();
		try {
			con.rollback();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		file.delete();
		flag=false;
	}
	return flag;
}

/**
 * @param loanadvanceDTO
 * @return
 * @throws Exception
 */
/*public synchronized String insertAdvanceEMI(LoanadvanceDTO loanadvanceDTO) throws Exception {
	logger.debug("DBUtility - insertAdvanceEMI()");
	String status = "";
	String errorCode="";
	try {
		logger.debug(" loanadvanceDTO.getAdvanceamount().toString() "+loanadvanceDTO.getAdvanceamount().toString());
		logger.debug(" loanadvanceDTO.getAdvancetypeid().toString() "+loanadvanceDTO.getAdvancetypeid().toString());
		logger.debug(" loanadvanceDTO.getEmpid().toString() "+loanadvanceDTO.getEmpid().toString());

		clstmt.setString(1, loanadvanceDTO.getAdvancetxnid().toString());
    	clstmt.setString(2, loanadvanceDTO.getEmpid().toString());
        clstmt.setString(3, loanadvanceDTO.getAdvancetypeid().toString());
        clstmt.setString(4, loanadvanceDTO.getAdvanceamount().toString());
        clstmt.registerOutParameter(5, Types.INTEGER);
        clstmt.registerOutParameter(6, Types.VARCHAR);

        clstmt.execute();
        status = clstmt.getString(6);
        errorCode = clstmt.getString(5);
        logger.debug("Status from the store procedure "+status);
        logger.debug("Print the ErrorCode "+clstmt.getInt(5));

	logger.debug("Type:-" + status);
	} catch (SQLException sqle) {
	logger.debug("DBUtility - insertAdvanceEMI(String[]): " +
	    sqle.getMessage());
	throw sqle;
	} catch (NumberFormatException nfe) {
	logger.debug("DBUtility - insertAdvanceEMI(String[]): " +
	    nfe.getMessage());
	throw nfe;
	} catch (IllegalArgumentException iae) {
	logger.debug("DBUtility - insertAdvanceEMI(String[]): " +
	    iae.getMessage());
	throw iae;
	}

	return errorCode;
}
*/
public synchronized boolean attachOfficalInfo(String userid,String doctype,String fileName,String strFilePath,String strLoginId) throws Exception
{
   boolean check = true;
   String              sqlText                 = null;
   BLOB                image                   = null;
   int                 chunkSize;
   byte[]              binaryBuffer;
   long                position;
   int                 bytesRead               = 0;
   int                 bytesWritten            = 0;
   int                 totbytesRead            = 0;
   int                 totbytesWritten         = 0;
   String strSeq = null;
   File file=null;
   try
	  {
	    logger.debug(" doctype "+doctype+" fileName   "+fileName+" strFilePath "+strFilePath);
	    
	    strSeq = "SELECT IGRS_EMP_DOCUMENT_DETAILS_SEQ.NEXTVAL as SEQ FROM DUAL";
	    
	    st = con.createStatement();
	    logger.debug("First query : "+strSeq);
	    rst = st.executeQuery(strSeq);
	    while(rst.next()){
	    	strSeq = rst.getString("SEQ");
	    	
	    }
	    sqlText="INSERT INTO IGRS_EMP_DOCUMENT_DETAILS(DOCUMENT_ID,DOCUMENT_NAME,EMP_ID,EMP_DOC_ID,EMP_DOC_VALUE,EMP_DOC_STATUS," +
	    		"CREATED_BY,CREATED_DATE)" +
	    		" VALUES("+strSeq+",'"+fileName+"','"+userid+"','"+doctype+"',EMPTY_BLOB(),'A','"+strLoginId+"',sysdate)";
	    logger.debug("sqlText  "+sqlText);
	    logger.debug("File path :"+strFilePath+fileName);
	    
	    file = new File(strFilePath+fileName);
	    InputStream ioFile = new FileInputStream(file);
	    
        logger.debug("before insert query :"+sqlText);
        st.executeUpdate(sqlText);
        logger.debug("After insert ");
        sqlText = 
            "SELECT EMP_DOC_VALUE " +
            "FROM   IGRS_EMP_DOCUMENT_DETAILS " +
            "WHERE  DOCUMENT_ID ="+strSeq+ " FOR UPDATE";
        
        logger.debug("Before update start"+sqlText);
        rst = st.executeQuery(sqlText);
        rst.next();
        image = ((OracleResultSet) rst).getBLOB("EMP_DOC_VALUE");
        
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
        file.delete();

	  } catch (IOException e) {
	       logger.debug("Caught I/O Exception: (Write BLOB value - Put Method).");
	       e.printStackTrace();
	       check=false;
	       con.rollback();
	        file.delete();
	       //throw e;
	   } catch (SQLException e) {
	       logger.debug("Caught SQL Exception: (Write BLOB value - Put Method).");
	       logger.debug("SQL:\n" + sqlText);
	       e.printStackTrace();
	       con.rollback();
	       check=false;
	        file.delete();
	       //throw e;
	   }
	
  
    return check;
  }

public synchronized boolean attachSpLicence(
		 String strFilePath,String fileName,String licenceTxnId) throws Exception
{

	   boolean check = true;
	   String              sqlText                 = null;
	   BLOB                image                   = null;
	   int                 chunkSize;
	   byte[]              binaryBuffer;
	   long                position;
	   int                 bytesRead               = 0;
	   int                 bytesWritten            = 0;
	   int                 totbytesRead            = 0;
	   int                 totbytesWritten         = 0;
	
	   File file=null;
	   try
		  {
		    file = new File(strFilePath+fileName);
		    InputStream ioFile = new FileInputStream(file);
		    
		    
		    sqlText = 
	            "SELECT DOCUMENT_TYPE " +
	            "FROM   IGRS_SP_USER_LICENSE_DETAILS " +
	            "WHERE  LICENSE_TXN_ID ='"+licenceTxnId+ "' FOR UPDATE";
	        
	        logger.debug("Before update start"+sqlText);
	        rst = st.executeQuery(sqlText);
	        rst.next();
	        image = ((OracleResultSet) rst).getBLOB("DOCUMENT_TYPE");
	        
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
	        file.delete();

		  } catch (IOException e) {
		       logger.debug("Caught I/O Exception: (Write BLOB value - Put Method).");
		       e.printStackTrace();
		       check=false;
		       con.rollback();
		        file.delete();
		       //throw e;
		   } catch (SQLException e) {
		       logger.debug("Caught SQL Exception: (Write BLOB value - Put Method).");
		       logger.debug("SQL:\n" + sqlText);
		       e.printStackTrace();
		       con.rollback();
		       check=false;
		        file.delete();
		       //throw e;
		   }
		
	  
	    return check;
	  
}


/**
 * @param advanceTxnId
 * @return
 */
public synchronized ArrayList calculateAdvanceEMIForApprovedOne(String advanceTxnId) {
	logger.debug("DBUtility - calculateAdvanceEMI()");
	String status = "";
	String errorCode="";
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
        logger.debug("Status from the store procedure "+status);
        logger.debug("Print the ErrorCode "+clstmt.getInt(6));
        logger.debug("Print paid inst no  :"+clstmt.getInt(2));
        logger.debug("Print paid amount  :"+clstmt.getInt(3));
        logger.debug("Unpaid inst no   :"+clstmt.getInt(4));
        logger.debug("Unpaid amount   :"+clstmt.getInt(5));
if(clstmt.getInt(6) == 0)
{
	 list = new ArrayList();
	list.add((clstmt.getInt(2))+"");
	list.add((clstmt.getInt(3))+"");
	list.add((clstmt.getInt(4))+"");
	list.add((clstmt.getInt(5))+"");
	logger.debug(" list   "+list);

}

	logger.debug("Type:-" + status);
	} catch (SQLException sqle) {
	logger.debug("DBUtility - insertAdvanceEMI(String[]): " +
	    sqle.getMessage());

	} catch (NumberFormatException nfe) {
	logger.debug("DBUtility - insertAdvanceEMI(String[]): " +
	    nfe.getMessage());

	} catch (IllegalArgumentException iae) {
	logger.debug("DBUtility - insertAdvanceEMI(String[]): " +
	    iae.getMessage());

	}

	return list;
}

// Method Changed by OneApps
public synchronized String savePreEnquiryDoc(String[] details, String filePath) throws Exception
{
   boolean check = true;
   String returnVal=null;
   String              sqlText                 = null;
   BLOB                image                   = null;
   int                 chunkSize;
   byte[]              binaryBuffer;
   long                position;
   int                 bytesRead               = 0;
   int                 bytesWritten            = 0;
   int                 totbytesRead            = 0;
   int                 totbytesWritten         = 0;
   String strSeq = null;
   try
	  {
	    File file = new File(filePath+details[1]);
	    InputStream ioFile = new FileInputStream(file);
	    
	    st = con.createStatement();
	   
	    //BLOB reading as well as writting in to the Database START
	    //before that we need to get the sequence number
	    //String strSeq =null;
	    rst = st.executeQuery("SELECT IGRS_EMP_COM_DOC_MAP_SEQ.NEXTVAL as seq FROM DUAL");
	    
	    rst.next();
	    strSeq = ((OracleResultSet) rst).getString("seq");
	    
	   // logger.debug("Sequint number "+strSeq);
        sqlText ="INSERT INTO IGRS_EMP_COMPLAINT_DOC_MAP"
    		+"(DOCUMENT_ID,COMPLAINT_NO,DOCUMENT_NAME,DOCUMENT_OBJECT,DOCUMENT_TYPE,DOCUMENT_UPLOAD_DATE) "
    		+" VALUES("+strSeq+",'"+details[0]+"', '"+details[1]+"',EMPTY_BLOB(),'"+details[2]+"', '"+details[3]+"')";
    	
       
        
      //  logger.debug("before insert query :"+sqlText);
        st.executeUpdate(sqlText);
        logger.debug("After insert ");
        sqlText = 
            "SELECT DOCUMENT_OBJECT " +
            "FROM   IGRS_EMP_COMPLAINT_DOC_MAP " +
            "WHERE  DOCUMENT_ID ="+strSeq+
            " FOR UPDATE";
       // logger.debug("Before update start"+sqlText);
        rst = st.executeQuery(sqlText);
        rst.next();
        image = ((OracleResultSet) rst).getBLOB("DOCUMENT_OBJECT");
        
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
	    
	  }  catch (IOException e) {
       logger.debug("Caught I/O Exception: (Write BLOB value - Put Method).");
       e.printStackTrace();
       check=false;
       //throw e;
   } catch (SQLException e) {
       logger.debug("Caught SQL Exception: (Write BLOB value - Put Method).");
       logger.debug("SQL:\n" + sqlText);
       e.printStackTrace();
       check=false;
       //throw e;
   }
  
   	if(check){
   		con.commit();
   	}
   	else{
   		
   		con.rollback();
   	}
    if(check){
    	returnVal = strSeq;
    }
    else{
    	returnVal = "false";
    }
   	return returnVal;
  }
/*
public synchronized String insertIntoDetailsHistiory(ArrayList list) throws Exception {
	logger.debug("DBUtility - insertAdvanceEMI()");
	String status = "";
	String errorCode="";
	try {
		LoanDTO loanDTO=(LoanDTO)list.get(0);
		logger.debug(" loanDTO.getLoantxnid().toString() "+loanDTO.getLoantxnid().toString());
		logger.debug(" loanDTO.getEmpid().toString() "+loanDTO.getEmpid().toString());
		logger.debug(" loanDTO.getLoanid().toString() "+loanDTO.getLoanid().toString());
		logger.debug(" loanDTO.getLoanamount().toString()"+loanDTO.getLoanamount().toString());
		logger.debug(" loanDTO.getUserinstallment().toString() "+loanDTO.getUserinstallment().toString());



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
        logger.debug("Status from the store procedure "+status);
        logger.debug("Print the ErrorCode "+clstmt.getInt(6));

	logger.debug("Type:-" + status);
	} catch (SQLException sqle) {
	logger.debug("DBUtility - insertIntoDetailsHistiory(String[]): " +
	    sqle.getMessage());
	throw sqle;
	} catch (NumberFormatException nfe) {
	logger.debug("DBUtility - insertIntoDetailsHistiory(String[]): " +
	    nfe.getMessage());
	throw nfe;
	} catch (IllegalArgumentException iae) {
	logger.debug("DBUtility - insertIntoDetailsHistiory(String[]): " +
	    iae.getMessage());
	throw iae;
	}

	return errorCode;
}
*/

public synchronized ArrayList calculateLoanEMIForApprovedOne(String loanTxnId) {
	logger.debug("DBUtility - calculateLoanEMIForApprovedOne()");
	String status = "";
	String errorCode="";
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
        logger.debug("Status from the store procedure "+status);
        logger.debug("Print the ErrorCode "+clstmt.getInt(6));
        logger.debug("Print paid inst no  :"+clstmt.getInt(2));
        logger.debug("Print paid amount  :"+clstmt.getInt(3));
        logger.debug("Unpaid inst no   :"+clstmt.getInt(4));
        logger.debug("Unpaid amount   :"+clstmt.getInt(5));
if(clstmt.getInt(6) == 0)
{
	 list = new ArrayList();
	list.add((clstmt.getInt(2))+"");
	list.add((clstmt.getInt(3))+"");
	list.add((clstmt.getInt(4))+"");
	list.add((clstmt.getInt(5))+"");
	logger.debug(" list   "+list);

}

	logger.debug("Type:-" + status);
	} catch (SQLException sqle) {
	logger.debug("DBUtility - insertLoanEMI(String[]): " +
	    sqle.getMessage());

	} catch (NumberFormatException nfe) {
	logger.debug("DBUtility - insertLoanEMI(String[]): " +
	    nfe.getMessage());

	} catch (IllegalArgumentException iae) {
	logger.debug("DBUtility - insertLoanEMI(String[]): " +
	    iae.getMessage());

	}

	return list;
}

public synchronized String getDownload(HttpServletResponse res,String contId) {
	Connection connection = null;
	CallableStatement cstmnt = null;
	String preferenceString = null;
	Statement stmt = null;
	ResultSet rs = null;
	OutputStream os = null;
    String ext = null;
    String contentName;

    String errorCode=null;
    String errorMsg=null;

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
		 logger.debug("Print in Util value "+contId);
		 cstmnt.setString(1, contId);

		 cstmnt.registerOutParameter(2, OracleTypes.BLOB);
		 cstmnt.registerOutParameter(3, OracleTypes.VARCHAR);
		 cstmnt.registerOutParameter(4, OracleTypes.VARCHAR);
		 cstmnt.registerOutParameter(5, OracleTypes.VARCHAR);
		 cstmnt.registerOutParameter(6, OracleTypes.VARCHAR);

		 boolean value = cstmnt.execute();
		 logger.debug("Print somethig after execution"+value);

		//Added to get file name and type from database
		contentName = cstmnt.getString(3);
		logger.debug("Getting the value "+contentName);
		ext = cstmnt.getString(4);

		//contentName += "." + ext;
		 errorCode = cstmnt.getString(5);
		errorMsg = cstmnt.getString(6);
		logger.debug(" errorCode "+errorCode+" errorMsg   "+errorMsg+"  contentName  "+contentName);


		logger.info("errorCode :" + errorCode);
		   int i =0;
			oracle.sql.BLOB fileBlob = (oracle.sql.BLOB) cstmnt.getBlob(2);
			logger.debug(" fileBlob "+fileBlob.length());
			os = res.getOutputStream();
			long index = 1;
			if(contentName!=null)
					{
						ext = contentName.substring(contentName.length()-3, contentName.length());
						logger.debug(" ext "+ext);
						if(ext!=null){
								if (ext.equalsIgnoreCase("doc")){
									res.setContentType("application/download");
								}
								else if (ext.equalsIgnoreCase("ppt")){
									res.setContentType("application/download");
								}
								else if (ext.equalsIgnoreCase("xls")){
									res.setContentType("application/download");
								}
								else if (ext.equalsIgnoreCase("htm")){
									res.setContentType("text/html");
								}
								else if (ext.equalsIgnoreCase("tml")){
									res.setContentType("text/html");
								}
								else if (ext.equalsIgnoreCase("txt")){
									res.setContentType("text/plain");
								}
								else if (ext.equalsIgnoreCase("pdf")){
									res.setContentType("application/pdf");
								}
								else if (ext.equalsIgnoreCase("bmp")){
									res.setContentType("image/x-bmp");
								}
								else if (ext.equalsIgnoreCase("gif")){
									res.setContentType("image/gif");
								}
								else if (ext.equalsIgnoreCase("jpg")){
									res.setContentType("image/jpeg");
								}
								else if (ext.equalsIgnoreCase("peg")){
									res.setContentType("image/jpeg");
								}
								else
								{
									res.setContentType("application/download");
								}
						}
					}

		res.setHeader ("Content-Disposition", "attachment; filename="+contentName);

			while (index < fileBlob.length()) {
				os.write(fileBlob.getBytes(index, 10000));
				os.flush();
				index += 10000;
			}
	}
	catch (Exception excp) {
		logger.error(
			"ContentDAO:downloadContent:SQLException:" + excp.toString());

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
			logger.error(
				" ContentDAO:downloadContent:finally :General Exception Clause"
					+ excp.toString());

		}
	}
	return errorCode+errorMsg;

}

/**added by Imran Shaik
	 * for calculating stamp duty
	 * @param deed
	 * @param instrument
     * @param marketValue
	 * @param exemptionIDs
	 * @return Stamp Duty
	 */
    public synchronized String getStampDuty(String deed,String instrument,String exemption, String marketValue){
    CallableStatement cst = null;
    String stampDuty = null;
	try {
		DBUtility db = new DBUtility();
		db.createCallableStatement("call IGRS_STAMP_DUTY_PKG.IGRS_STAMP_DUTY_PROC(?,?,?,?,?,?,?,?)");

		cst = db.clstmt;

		cst.setString(1,deed);
		cst.setString(2,instrument);
		cst.setString(3,exemption);
		cst.setString(4, marketValue);
		cst.setString(5, null);
		cst.registerOutParameter(6, OracleTypes.NUMBER);
		cst.registerOutParameter(7, OracleTypes.VARCHAR);
		cst.registerOutParameter(8, OracleTypes.VARCHAR);

		if(!cst.execute()){
			int temp = cst.getInt(6);
			stampDuty = String.valueOf(temp);
		}
		} catch (Exception e) {
		e.printStackTrace();
	}
	return stampDuty;
    }

    /**added by Imran Shaik
	 * for calculating Other Fee
	 * @param function_id
	 * @return otherFee
	 */
    public int getOthersDuty(String args[]){
    CallableStatement cst = null;
    int otherFee = 0;
	try {
		DBUtility db = new DBUtility();
		db.createCallableStatement("call IGRS_SERVICE_FEE_PKG.IGRS_SERVICE_FEE_PROC(?,?,?,?,?,?)");
		cst = db.clstmt;
		cst.setString(1,args[0]);
		cst.setString(2,args[1]);
		cst.setString(3,args[2]);
		cst.registerOutParameter(4, OracleTypes.NUMBER);
		cst.registerOutParameter(5, OracleTypes.CLOB);
		cst.registerOutParameter(6, OracleTypes.VARCHAR);
		if(!cst.execute()){
			otherFee = cst.getInt(4);
			logger.debug("otherFee="+otherFee);
		}
		} catch (Exception e) {
		e.printStackTrace();
	}

	return otherFee;
    }

  //This method is used for reading the BLOB containet and making file to show int the UI START
    /**
     * Method used to write the contents (data) from an Oracle BLOB column to
     * an O/S file. This method uses one of two ways to get data from the BLOB
     * column - namely the getBytes() method. The other way to read data from an
     * Oracle BLOB column is to use Streams.
     * 
     * @throws java.io.IOException
     * @throws java.sql.SQLException
     */
    public void readBLOBToFileGet(HttpServletResponse res,String strDocId,String strFunctionName)
            throws IOException, SQLException {
    	
        String              sqlText                     = null;
        BLOB                image                       = null;
        String contentName=null;
        OutputStream os = null;
        String ext = null;
        
        try {
            st = con.createStatement();
            String docObject= "document_object";
            String docName  = "document_name";
            if(strFunctionName.equals("EMPMANG")){
            	sqlText =  "SELECT EMP_DOC_VALUE," +docName+
                " FROM   IGRS_EMP_DOCUMENT_DETAILS " +
                "WHERE  DOCUMENT_ID =" +strDocId+
                " FOR UPDATE";
            	docObject ="EMP_DOC_VALUE";
            }else if(strFunctionName.equalsIgnoreCase("DEPTENQ")){
            	  sqlText = 
                      "SELECT document_object," +docName+
                      " FROM   IGRS_EMP_COMPLAINT_DOC_MAP " +
                      "WHERE  DOCUMENT_ID =" +strDocId+
                      " FOR UPDATE";
            }else if(strFunctionName.equalsIgnoreCase("INSPECTION")){
          	  sqlText = 
                  "SELECT document_obj," +docName+
                  " FROM   IGRS_SRO_INSP_DOC_MAP " +
                  "WHERE  DOCUMENT_TXN_ID =" +strDocId+
                  " FOR UPDATE";
          	      docObject ="DOCUMENT_OBJ";
            }
            else if(strFunctionName.equalsIgnoreCase("POIINSPECTION")){
            	docName="DOC_NAME";
            	sqlText = 
                    "SELECT DOC_OBJECT," +docName+
                    " FROM   IGRS_POI_DOC_DTLS " +
                    "WHERE  DOC_TXN_ID ='" +strDocId+"'"+
                    " FOR UPDATE";
            	      docObject ="DOC_OBJECT";
            }else if(strFunctionName.equalsIgnoreCase("SP")){
            	docName="DOCUMENT_NAME";
            	docObject ="DOCUMENT_TYPE";
            	sqlText="SELECT "+docName+","+docObject+" FROM IGRS_SP_USER_LICENSE_DETAILS"
            			+" WHERE LICENSE_TXN_ID='"+strDocId+"'" 
            			+" FOR UPDATE";	
            	
            }
            
          
            System.out.println("before excuting :"+sqlText);
            rst = st.executeQuery(sqlText);
        //    System.out.println("After eceutingn ");
            //rst.next();
            //image = ((OracleResultSet) rst).getBLOB("document_object");
            
           while(rst.next()){
    	        image = ((OracleResultSet) rst).getBLOB(docObject);
    	        contentName = ((OracleResultSet) rst).getString(docName);
            }
           
        	//System.out.println("Content Name :"+contentName);
            os = res.getOutputStream();
    		long index = 1;
    		if(contentName!=null)
    				{
    			//System.out.println(" Before exception ");
    					ext = contentName.substring(contentName.length()-3, contentName.length());
    					//System.out.println(" ext "+ext);
    					if(ext!=null){
    							if (ext.equalsIgnoreCase("doc")){
    								res.setContentType("application/download");
    							}
    							else if (ext.equalsIgnoreCase("ppt")){
    								res.setContentType("application/download");
    							}
    							else if (ext.equalsIgnoreCase("xls")){
    								res.setContentType("application/download");
    							}
    							else if (ext.equalsIgnoreCase("htm")){
    								res.setContentType("text/html");
    							}
    							else if (ext.equalsIgnoreCase("tml")){
    								res.setContentType("text/html");
    							}
    							else if (ext.equalsIgnoreCase("txt")){
    								res.setContentType("text/plain");
    							}
    							else if (ext.equalsIgnoreCase("pdf")){
    								res.setContentType("application/pdf");
    							}
    							else if (ext.equalsIgnoreCase("bmp")){
    								res.setContentType("image/x-bmp");
    							}
    							else if (ext.equalsIgnoreCase("gif")){
    								res.setContentType("image/gif");
    							}
    							else if (ext.equalsIgnoreCase("jpg")){
    								res.setContentType("image/jpeg");
    							}
    							else if (ext.equalsIgnoreCase("peg")){
    								res.setContentType("image/jpeg");
    							}
    							else
    							{
    								res.setContentType("application/download");
    							}
    					}
    				}

    	    res.setHeader ("Content-Disposition", "attachment; filename="+contentName);

            
            // Loop through while reading a data from the BLOB
            // column using the getBytes() method. This data will be stored
            // in a BLOB column and writting in OutPutStream.
    		while (index < image.length()) {
    			os.write(image.getBytes(index, 10000));
    			os.flush();
    			index += 10000;
    		}

        } catch (IOException e) {
            System.out.println("Caught I/O Exception: (Write BLOB value to file - Get Method).");
            e.printStackTrace();
            throw e;
        } catch (SQLException e) {
            System.out.println("Caught SQL Exception: (Write BLOB value to file - Get Method).");
            System.out.println("SQL:\n" + sqlText);
            e.printStackTrace();
            logger.error(
    				" DBUtility:downloadContent:finally :General Exception Clause"
    					+ e.toString());
        }

    }

    //BLOB Regading END


//This method is used for reading the BLOB containet and making file to show int the UI START
    /**
     * Method used to write the contents (data) from an Oracle BLOB column to
     * an O/S file. This method uses one of two ways to get data from the BLOB
     * column - namely the getBytes() method. The other way to read data from an
     * Oracle BLOB column is to use Streams.
     * 
     * @throws java.io.IOException
     * @throws java.sql.SQLException
     */


public ResultSet readBLOBToFileGet(String strDocId)
    throws IOException, SQLException {

String              sqlText                     = null;
BLOB                image                       = null;
String contentName=null;
OutputStream os = null;
String ext = null;

try {
    st = con.createStatement();
    String docObject= "DOCUMENT_OBJ";
    String docExtn  = "DOCUMENT_EXTN";
    String docName="DEATH_CERTIFICATE";
  
    	sqlText =  "SELECT DOCUMENT_OBJ,DOCUMENT_EXTN FROM IGRS_REG_PIN_DOC_DETAILS WHERE  PIN_REQ_TXN_ID ='" +strDocId+ "' AND DOCUMENT_NAME='" +docName+ "' FOR UPDATE";
           	     
  
    	logger.debug("before excuting :"+sqlText);
    rst = st.executeQuery(sqlText);
    logger.debug("after excuting :"+sqlText);
//    System.out.println("After eceutingn ");
    //rst.next();
    //image = ((OracleResultSet) rst).getBLOB("document_object");
    return rst;
    
  

} catch (SQLException e) {
	logger.error("Caught SQL Exception: (Write BLOB value to file - Get Method).");
	logger.error("SQL:\n" + sqlText);
    e.printStackTrace();
    logger.error(
			" DBUtility:downloadContent:finally :General Exception Clause"
				+ e.toString());
}finally{
	con.close();
}
return null;
}




//This method is used for reading the BLOB containet and making file to show int the UI START
    /**
     * Method used to write the contents (data) from an Oracle BLOB column to
     * an O/S file. This method uses one of two ways to get data from the BLOB
     * column - namely the getBytes() method. The other way to read data from an
     * Oracle BLOB column is to use Streams.
     * 
     * @throws java.io.IOException
     * @throws java.sql.SQLException
     */
    
    
public ResultSet readBLOBToFileGet1(String strDocId)
throws IOException, SQLException {

String              sqlText                     = null;
BLOB                image                       = null;
String contentName=null;
OutputStream os = null;
String ext = null;

try {
st = con.createStatement();
String docObject= "DOCUMENT_OBJ";
String docName  = "MUTATION_FILE";
String docExtn="DOCUMENT_EXTN";

	sqlText =  "SELECT DOCUMENT_OBJ,DOCUMENT_EXTN FROM IGRS_REG_PIN_DOC_DETAILS WHERE  PIN_REQ_TXN_ID ='" +strDocId+ "' AND DOCUMENT_NAME='" +docName+ "' FOR UPDATE";
   
	//docObject ="DEATH_CERTIFICATE";



logger.debug("before excuting :"+sqlText);
rst = st.executeQuery(sqlText);
logger.debug("after excuting :"+sqlText);
//System.out.println("After eceutingn ");
//rst.next();
//image = ((OracleResultSet) rst).getBLOB("document_object");
return rst;


}  catch (SQLException e) {
logger.error("Caught SQL Exception: (Write BLOB value to file - Get Method).");
logger.error("SQL:\n" + sqlText);
e.printStackTrace();
logger.error(
			" DBUtility:downloadContent:finally :General Exception Clause"
				+ e.toString());
}finally{
	con.close();
}
return null;
}   
   
    




}
