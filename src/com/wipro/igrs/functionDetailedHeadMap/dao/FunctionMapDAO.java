package com.wipro.igrs.functionDetailedHeadMap.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;





import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.functionDetailedHeadMap.dto.FunctionDetailedHeadMapDTO;
import com.wipro.igrs.functionDetailedHeadMap.sql.CommonSQL;

public class FunctionMapDAO {
	
	  private Logger logger = 
			(Logger) Logger.getLogger(FunctionMapDAO.class);
	   public ArrayList retrieveAllFunctionMap()throws Exception{
	    	logger.debug("IN DAO retrieveAllFunctionMap");
	        ArrayList ar1=new ArrayList();
	        DBUtility dbUtil=null;
	      
	        try {
	            ArrayList typeList=new ArrayList();
	            ArrayList typeTemp=new ArrayList();
	           
	            dbUtil=new DBUtility();
	            String str=CommonSQL.IGRS_FUNCTION_MAP_LIST_ALL;
	          
	            dbUtil.createStatement();
	            typeList=dbUtil.executeQuery(str);
	            if(typeList!=null){
	            	
	                for(int i=0;i<typeList.size();i++) {
	                    typeTemp=new ArrayList();
	                    typeTemp=(ArrayList)typeList.get(i);
	                    if(typeTemp.size()>0){
	                    	FunctionDetailedHeadMapDTO type = new FunctionDetailedHeadMapDTO();
	                        
	                        type.setFunctionName((String)typeTemp.get(0));
	                        type.setHeadName((String)typeTemp.get(1));
	                        type.setFunctionId((String)typeTemp.get(2));
	                        type.setHeadId((String)typeTemp.get(3));
	                        type.setMapId((String)typeTemp.get(4));
	                
	                       
	                   
	                      
	                        
	                        ar1.add(type);
	                    }
	                }
	            }
	        }catch(IOException ie){
	        
	        	logger.error(ie);
	        }
	        catch(SQLException se){
	   
	        	logger.error(se);
	        }
	        catch(Exception e){
	        
	            logger.error(e);
	        }
	        finally {
	    		logger.debug("using FINALLY Connection is closed");
	            dbUtil.closeConnection(); 
	        }
	        return ar1;
	    }
	   public ArrayList retrieveAllFunctions()throws Exception{
			logger.debug("IN DAO retrieveAllFunctionMap");
	        ArrayList ar1=new ArrayList();
	        DBUtility dbUtil=null;
	      
	        try {
	            ArrayList typeList=new ArrayList();
	            ArrayList typeTemp=new ArrayList();
	           
	            dbUtil=new DBUtility();
	            String str=CommonSQL.IGRS_FUNCTION_MAP_PREPARE_FUNCTION_ADD_EDIT_PAGE;
	       
	            dbUtil.createStatement();
	            typeList=dbUtil.executeQuery(str);
	            if(typeList!=null){
	            	
	                for(int i=0;i<typeList.size();i++) {
	                    typeTemp=new ArrayList();
	                    typeTemp=(ArrayList)typeList.get(i);
	                    if(typeTemp.size()>0){
	                    	FunctionDetailedHeadMapDTO type = new FunctionDetailedHeadMapDTO();
	                        
	                        type.setFunctionName((String)typeTemp.get(0));
	                        type.setFunctionId((String)typeTemp.get(1));
	                    
	                        
	                        ar1.add(type);
	                    }
	                }
	            }
	        }catch(IOException ie){
	        
	        	logger.error(ie);
	        }
	        catch(SQLException se){
	   
	        	logger.error(se);
	        }
	        catch(Exception e){
	        
	            logger.error(e);
	        }
	        finally {
	    		logger.debug("using FINALLY Connection is closed");
	            dbUtil.closeConnection(); 
	        }
	        return ar1;
		   
	   }
	   public ArrayList retrieveAllHeads()throws Exception{
			logger.debug("IN DAO retrieveAllFunctionMap");
	        ArrayList ar1=new ArrayList();
	        DBUtility dbUtil=null;
	      
	        try {
	            ArrayList typeList=new ArrayList();
	            ArrayList typeTemp=new ArrayList();
	           
	            dbUtil=new DBUtility();
	            String str=CommonSQL.IGRS_FUNCTION_MAP_PREPARE_HEAD_ADD_EDIT_PAGE;
	           
	            dbUtil.createStatement();
	            typeList=dbUtil.executeQuery(str);
	            if(typeList!=null){
	            	
	                for(int i=0;i<typeList.size();i++) {
	                    typeTemp=new ArrayList();
	                    typeTemp=(ArrayList)typeList.get(i);
	                    if(typeTemp.size()>0){
	                    	FunctionDetailedHeadMapDTO type = new FunctionDetailedHeadMapDTO();
	                        
	                        
	                        type.setHeadName((String)typeTemp.get(0));
	                        type.setHeadId((String)typeTemp.get(1));
	                        
	                        ar1.add(type);
	                    }
	                }
	            }
	        }catch(IOException ie){
	        
	        	logger.error(ie);
	        }
	        catch(SQLException se){
	   
	        	logger.error(se);
	        }
	        catch(Exception e){
	        
	            logger.error(e);
	        }
	        finally {
	    		logger.debug("using FINALLY Connection is closed");
	            dbUtil.closeConnection(); 
	        }
	        return ar1;
		   
	   }
	    public boolean deleteFunctionMap(String id)throws Exception
	    {
	    	logger.debug("IN deleteFunctionMap");
	    	DBUtility dbUtil=null;
	    	boolean result=false;
	    	String []param=new String [1];
	    	param[0]=id;
	        try{
	            String query=CommonSQL.IGRS_FUNCTION_MAP_DELETE_RECORD;
	            dbUtil = new DBUtility();
	            dbUtil.createPreparedStatement(query);
	            result = dbUtil.executeUpdate(param);
	            return result;
	        }catch(IOException ie){
		    	logger.error(ie);
		    }
		    catch(SQLException se){
		    	se.printStackTrace();
		    	logger.error(se);
		    }
		    catch(Exception e){
		        logger.error(e);
		    }
		    finally {
				logger.debug("using FINALLY Connection is closed");
		        dbUtil.closeConnection(); 
		    }
		    return result;
	    }
	    public boolean insertFunctionMap(String[] param)throws Exception
	    {
	    	logger.debug("IN DAO insertFunctionMap");
	    	DBUtility dbUtil=null;
	    	boolean result=false;
	        try{
	            String query=CommonSQL.IGRS_FUNCTION_MAP_INSERT_RECORD;
	            dbUtil = new DBUtility();
	            dbUtil.createPreparedStatement(query);
	            result = dbUtil.executeUpdate(param);
	            return result;
	        }catch(IOException ie){
		    	logger.error(ie);
		    }
		    catch(SQLException se){
		    	logger.error(se);
		    }
		    catch(Exception e){
		        logger.error(e);
		    }
		    finally {
				logger.debug("using FINALLY Connection is closed");
		        dbUtil.closeConnection(); 
		    }
		    return result;
	    }
	    public boolean updateFunctionMap(String[] param)throws Exception
	    {
	    	logger.debug("IN DAO updateFunctionMap");
	    	DBUtility dbUtil=null;
	    	boolean result=false;
	        try{
	            String query=CommonSQL.IGRS_FUNCTION_MAP_UPDATE_RECORD;
	            dbUtil = new DBUtility();
	            dbUtil.createPreparedStatement(query);
	            result = dbUtil.executeUpdate(param);
	            return result;
	        }catch(IOException ie){
		    	logger.error(ie);
		    	
		    }
		    catch(SQLException se){
		    	logger.error(se);
		    	
		    }
		    catch(Exception e){
		        logger.error(e);
		       
		    }
		    finally {
				logger.debug("using FINALLY Connection is closed");
		        dbUtil.closeConnection(); 
		    }
		    return result;
	    }


	    public String checkFunctionHeadMapRecordExist(String functionId ,String headId)throws Exception{
    	logger.debug("IN DAO checkFunctionHeadMapRecordExist");
        ArrayList ar1=new ArrayList();
        DBUtility dbUtil=null;
       
        String result=null;
        String [] param=new String [2];
        param[0]=functionId;
        param[1]=headId;
        
        try {
            ArrayList typeList=new ArrayList();
            ArrayList typeTemp=new ArrayList();
           
            dbUtil=new DBUtility();
            String str=CommonSQL.IGRS_FUNCTION_MAP_IS_EXIST;
            dbUtil.createPreparedStatement(str);
            typeList=dbUtil.executeQuery(param);
     
           
            if(!typeList.isEmpty()){
            	result=(String)(((ArrayList)(typeList.get(0))).get(0));
            
            	
                }
      
            
        }catch(IOException ie){
        	
        	logger.error(ie);
        	
        }
        catch(SQLException se){
        	
        	logger.error(se);
        	
        }
        catch(Exception e){
       
        	e.printStackTrace();
            logger.error(e);
            
        }
        finally {
    		logger.debug("using FINALLY Connection is closed");
            dbUtil.closeConnection(); 
        }
        
        return result;
}
	    public ArrayList getHeadAndFunctionForCertainMapId(String mapId)throws Exception{
	    	logger.debug("IN DAO getHeadAndFunctionForCertainMapId");
	        ArrayList ar1=new ArrayList();
	        DBUtility dbUtil=null;
	       
	        String result=null;
	        String [] param=new String [1];
	        param[0]=mapId;
	        
	        
	        try {
	            ArrayList typeList=new ArrayList();
	            ArrayList typeTemp=new ArrayList();
	           
	            dbUtil=new DBUtility();
	            String str=CommonSQL.IGRS_FUNCTION_MAP_RETRIEVE_RECORD;
	            dbUtil.createPreparedStatement(str);
	            typeList=dbUtil.executeQuery(param);
	     
	           
	            if(typeList!=null){
	                for(int i=0;i<typeList.size();i++) {
	                    typeTemp=new ArrayList();
	                    typeTemp=(ArrayList)typeList.get(i);
	                    if(typeTemp.size()>0){
	                        FunctionDetailedHeadMapDTO type = new FunctionDetailedHeadMapDTO();
	                        
	                        type.setFunctionId((String)typeTemp.get(0));
	                        type.setHeadId((String)typeTemp.get(1));
	                     
	                      
	                        
	                        ar1.add(type);
	                    }
	                }
	            }
	      
	            
	        }catch(IOException ie){
	        	
	        	logger.error(ie);
	        	
	        }
	        catch(SQLException se){
	        	
	        	logger.error(se);
	        	
	        }
	        catch(Exception e){
	       
	        	e.printStackTrace();
	            logger.error(e);
	            
	        }
	        finally {
	    		logger.debug("using FINALLY Connection is closed");
	            dbUtil.closeConnection(); 
	        }
	        
	        return ar1;
	}
		    
    

}
