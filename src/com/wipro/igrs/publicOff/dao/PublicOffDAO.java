package com.wipro.igrs.publicOff.dao;
/**
 * ===========================================================================
 * File           :   PublicOffDAO.java
 * Description    :   Represents the DAO Class

 * Author         :   Pavani param
 * Created Date   :   Aug 23, 2008

 * ===========================================================================
 */
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.publicOff.sql.CommonSQL;
import java.util.ArrayList;

import org.apache.log4j.Logger;

public class PublicOffDAO 

{
	private Logger logger = 
		(Logger) Logger.getLogger(PublicOffDAO.class);
	DBUtility dbUtil;
    ArrayList judList;
    ArrayList nonJudList;
    ArrayList list  =  null;     
    boolean insert;
    String SQL;
//    CommonSQL commonSQL = new CommonSQL();

	 /**
     * getting  User Details.
     * @param param
     * @return
     * @throws Exception
     */
    public ArrayList getUserDet(String userId) 
    {
		  String SQL = CommonSQL.PO_USER_DET_QUERY+"'"+userId+"'";
		  ArrayList list=new ArrayList();
	     try{
	       dbUtil = new DBUtility(); 
	       dbUtil.createStatement();
	      list = dbUtil.executeQuery(SQL);
	     
	      } catch (Exception e) {
	          logger.debug("PublicOffDAO --  Exception in getUserDet():" + e);
	      }
	      logger.info("PublicOffDAO --   in getDeptList():"+list);
	      return list;
    	}


    /**
     * getting  User Details.
     * @param param
     * @return
     * @throws Exception
     */
    public ArrayList getDeptList() 
    {
		  String SQL = CommonSQL.PO_DEPT_DET_QUERY;
		  ArrayList list=new ArrayList();
	     try{
	    	dbUtil = new DBUtility(); 
	       dbUtil.createStatement();
	      list = dbUtil.executeQuery(SQL);
	     
	      } catch (Exception e) {
	          logger.debug("PublicOffDAO --  Exception in getDeptList():" + e);
	      }
	      logger.info("PublicOffDAO --  in getDeptList():"+list);
	      return list;
    	}
    

	/**
	 * saving the Public officer details.
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public boolean poDetInsert(String param[]) 
    {
   	 boolean boo  =  false;	
   	 try
   	 { 
   		    dbUtil = new DBUtility();
            dbUtil.createPreparedStatement(CommonSQL.PO_DET_INSERT);
            boo  =  dbUtil.executeUpdate(param);
            logger.info("PublicOffDAO --  poDetInsert  boo== :- " + boo);
            if (boo) {
            	dbUtil.commit();}
        	}
    	catch (Exception x) {
    		 logger.debug("PublicOffDAO -- Exception in poDetInsert() :- " + x);
             try {
				dbUtil.rollback();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    	finally {
            try {
           	  if(!boo)
           	  {
           		  dbUtil.rollback();
           	  }
               dbUtil.closeConnection();
            } catch (Exception ex) {
                logger.debug("PublicOffDAO -- Exception in poDetInsert() :-" + ex);
            }
        }
        return boo;
    }
    // --END--APPLICANT DETAILS INSERT


	public ArrayList getpublicOffDet(String userId) {
		 String SQL = CommonSQL.PO_OTHER_DET_QUERY+"'"+userId+"'";
		  ArrayList list=new ArrayList();
	     try{
	    	dbUtil = new DBUtility(); 
	       dbUtil.createStatement();
	      list = dbUtil.executeQuery(SQL);
	     
	      } catch (Exception e) {
	          logger.debug("PublicOffDAO Exception in getpublicOffDet():" + e);
	      }
	      logger.info("PublicOffDAO  in getpublicOffDet():"+list);
	      return list;
   	}


	public boolean updateActDet(String[] actDet) 
	{
		boolean boo  =  false;	
	   	 try
	   	 {
	   		    dbUtil = new DBUtility();
	            dbUtil.createPreparedStatement(CommonSQL.ACT_DET_UPDATE);
	            boo  =  dbUtil.executeUpdate(actDet);
	            logger.info("PublicOffDAO--updateActDet  boo== :- " + boo);
	            if (boo) {
	            	dbUtil.commit();}
	        	}
	    	catch (Exception x) {
	    		 logger.debug("PublicOffDAO --Exception in updateActDet() :- " + x);
	             try {
					dbUtil.rollback();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	    	finally {
	            try {
	           	  if(!boo)
	           	  {
	           		  dbUtil.rollback();
	           	  }
	               dbUtil.closeConnection();
	            } catch (Exception ex) {
	                logger.debug(" PublicOffDAO -- Exception in updateActDet() :-" + ex);
	            }
	        }
	        return boo;
	    }
}




