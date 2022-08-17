package com.wipro.igrs.UserRegistration.dao;



import java.sql.CallableStatement;
import java.util.ArrayList;

import oracle.jdbc.driver.OracleTypes;

import org.apache.log4j.Logger;

import com.wipro.igrs.UserRegistration.sql.CommonSQL;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.newuser.dao.UserForgetPasswordDAO;
import com.wipro.igrs.newuser.sql.UserForgetPasswordCommonSQL;


public class UserResetPasswordDAO {
	
	
    private DBUtility dbUtility = null;
    private String sql = null;
    
    
    private Logger logger = 
		(Logger) Logger.getLogger(UserForgetPasswordDAO.class);
    private CallableStatement clstmnt = null; 
    /*DAO constructor*/
    public UserResetPasswordDAO() {
 
    }
    
    
       /* UPDATE USER PASSWORD */
    
    public boolean UpdateUserPaswrd(String userName, String newPwrd, String srtPhotoIdProof) throws Exception {
    	boolean updateStatus=false;
    	String param= new String();
        try {
           	sql = CommonSQL.CALL_UPDATE_USER_PASSWORD ;
        	dbUtility=new DBUtility();
            dbUtility.createPreparedStatement(sql);
            clstmnt = dbUtility.returnCallableStatement(sql);
            clstmnt.setString(1, userName);
          	 clstmnt.setString(2, newPwrd);
          	 clstmnt.setString(3, srtPhotoIdProof);
          	 
          	clstmnt.registerOutParameter(4, OracleTypes.VARCHAR);
          	if (!clstmnt.execute()) {
  				 
   				param = clstmnt.getString(4);
   				if(param.equalsIgnoreCase("y"))
   					updateStatus=true;
   				else
   					logger.debug("pppppppppppppppp-->>Could not update password");
   				
   			}
           
            	
         } catch (Exception e) {
              e.getStackTrace();
         }finally {
			if (dbUtility != null) {
				dbUtility.closeConnection();
			}
		}
        return updateStatus;
    }
    
    public ArrayList getUserId(String key) throws Exception {
    	String userId="";
    	ArrayList list=null;
    	logger.debug("INSIDE DAO1--------"+userId);
    	try{
    		logger.debug("before getting user validation ");
    		dbUtility = new DBUtility();
    		dbUtility.createPreparedStatement(CommonSQL.USER_ID_RESET_KEY);
			logger.debug("SQL:" + CommonSQL.USER_ID_RESET_KEY);
			String param[] = new String[1];
			param[0] = key;
			list = dbUtility.executeQuery(param);
		    
		    logger.debug("INSIDE DAO4--------"+list);
		if(list!=null && list.size()==1){
    		//userId=list.toString();
    		logger.debug("UserId found corresponding to given key--------");
    	}
    	
      	}catch (Exception e) {
            e.getStackTrace();
        }finally {
			if (dbUtility != null) {
				dbUtility.closeConnection();
			}
		}
        logger.debug("INSIDE DAO6--------"+userId);
    	return list;
    }
    
    

}
