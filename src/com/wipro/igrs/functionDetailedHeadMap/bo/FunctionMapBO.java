package com.wipro.igrs.functionDetailedHeadMap.bo;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.functionDetailedHeadMap.dao.FunctionMapDAO;
import com.wipro.igrs.functionDetailedHeadMap.exception.FunctionMapException;

public class FunctionMapBO {
	private Logger logger = 
		(Logger) Logger.getLogger(FunctionMapBO.class);
	private 	FunctionMapDAO daoRef;
	public  FunctionMapBO ()
	{
		 daoRef=new FunctionMapDAO();
	}

   public ArrayList retrieveAllFunctionMap()throws Exception
   {
	   logger.debug("WE ARE IN BD Debug");
		
	   ArrayList functionMapArr = null;
	  
		try {
			logger.info("Inside (retrieveAllFunctionMap) Method");
			functionMapArr=daoRef.retrieveAllFunctionMap();
		
			return functionMapArr;
			
			
		} catch (Exception e) {
			logger.error(e);
			return functionMapArr;
		}
	   
   }
   public ArrayList retrieveAllHeads()throws Exception{
	   logger.debug("WE ARE IN BD Debug");
		
	   ArrayList HeadArr = null;
	   
		try {
			logger.info("Inside (retrieveAllHeads) Method");
			HeadArr=daoRef.retrieveAllHeads();
			
			return HeadArr;
			
			
		} catch (Exception e) {
			logger.error(e);
			return HeadArr;
		}
	   
   }
   public ArrayList retrieveAllFunctions()throws Exception{
	   logger.debug("WE ARE IN BD Debug");
		
	   ArrayList functionArr = null;
	 
		try {
			logger.info("Inside (retrieveAllFunctions) Method");
			functionArr=daoRef.retrieveAllFunctions();
			
			return functionArr;
			
			
		} catch (Exception e) {
			logger.error(e);
			return functionArr;
		}
	   
   }
   public boolean deleteFunctionMap(String id)throws Exception
   {
	   logger.debug("WE ARE IN BD Debug");
	
		boolean result=false;
		try {
			logger.info("Inside (deleteFunctionMap) Method");
			result=daoRef.deleteFunctionMap(id);
			
			return result;
			
			
		} catch (Exception e) {
			logger.error(e);
			return result;
		}
	   
   }
   public boolean insertFunctionMap(String functionId,String headId)throws Exception,FunctionMapException
   {
	   logger.debug("WE ARE IN BD Debug");
		
		boolean result=false;

			logger.info("Inside (insertFunctionMap) Method");
		    String [] params=new String[2];
	         params[0]=functionId;
	         params[1]=headId;
	         String chk=daoRef.checkFunctionHeadMapRecordExist(functionId, headId);
	         if(chk==null )
				{
	        	 	result=daoRef.insertFunctionMap(params);
				}
	         else
				{
	        	 	
					throw new FunctionMapException();
				
				}
		
			return result;
			
			

   }
   public boolean updateFunctionMap(String functionId ,String headId ,String mapId)throws Exception
   {
	   logger.debug("WE ARE IN BD Debug");
	
		boolean result=false;
	
			String[] param=new String [3];
    		param[0]=functionId;
    		param[1]=headId;
    		param[2]=mapId;
    		String chk=daoRef.checkFunctionHeadMapRecordExist(functionId, headId);
    		
			logger.info("Inside (updateFunctionMap) Method");
			if(chk==null || chk.equals(mapId))
			{
			result=daoRef.updateFunctionMap(param);
			}
			else
			{
				
				throw new FunctionMapException();
				
			}
			return result;
			
			

   }
   public ArrayList getHeadAndFunctionForCertainMapId(String mapId)throws Exception{
	   logger.debug("WE ARE IN BD Debug");
	
		ArrayList result=null;
		try {
			logger.info("Inside (getHeadAndFunctionForCertainMapId) Method");
			result=daoRef.getHeadAndFunctionForCertainMapId(mapId);
		
			return result;
			
			
		} catch (Exception e) {
			logger.error(e);
			return result;
		}
	   
   }

}
