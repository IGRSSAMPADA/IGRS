package com.wipro.igrs.functionDetailedHeadMap.bd;

import java.util.ArrayList;

import org.apache.log4j.Logger;


import com.wipro.igrs.functionDetailedHeadMap.bo.FunctionMapBO;
import com.wipro.igrs.functionDetailedHeadMap.exception.FunctionMapException;


public class FunctionMapBD {
	private Logger logger = 
		(Logger) Logger.getLogger(FunctionMapBD.class);
	private FunctionMapBO boRef;
	public FunctionMapBD ()
	{
		 boRef=new FunctionMapBO();
	}
   public ArrayList retrieveAllFunctionMap()throws Exception
   {
	logger.debug("IN BD retrieveAllFunctionMap");
	 ArrayList functionMapArr = null;
	  
	try
	{
		functionMapArr=boRef.retrieveAllFunctionMap();
		
		return functionMapArr;
		
		
	}
	 catch (Exception e) {
			logger.error(e);
			return functionMapArr;
	 }
   }
   public ArrayList retrieveAllHeads()throws Exception{
		logger.debug("IN BD retrieveAllHeads");
		 ArrayList headArr = null;
		 
		try
		{
			headArr =boRef.retrieveAllHeads();
			
			return headArr;
			
			
		}
		 catch (Exception e) {
				logger.error(e);
				return headArr;
		 }
	   
   }
   public ArrayList retrieveAllFunctions()throws Exception{
		logger.debug("IN BD retrieveAllFunctions");
		 ArrayList functionArr = null;
		 
		try
		{
			functionArr =boRef.retrieveAllFunctions();
			
			return functionArr;
			
			
		}
		 catch (Exception e) {
				logger.error(e);
				return functionArr;
		 }
   }
   public boolean deleteFunctionMap(String id)throws Exception
   {
		logger.debug("IN BD deleteFunctionMap");
		 boolean result = false;
		 
		try
		{
			result=boRef.deleteFunctionMap(id);
			
			return result;
			
			
		}
		 catch (Exception e) {
				logger.error(e);
				return result;
		 }
   }
   public boolean insertFunctionMap(String functionId,String headId)throws Exception,FunctionMapException
   {
		logger.debug("IN BD insertFunctionMap");
		 boolean result = false;

//		try
//		{
			result=boRef.insertFunctionMap(functionId,headId);
			
			return result;
			
			
	/*	}
		 catch (Exception e) {
				logger.error(e);
				return result;
		 }*/
   }
   public boolean updateFunctionMap(String functionId ,String headId ,String mapId)throws Exception
   {
		logger.debug("IN BD updateFunctionMap");
		 boolean result = false;
		
//		try
//		{
			result=boRef.updateFunctionMap( functionId , headId ,mapId);
			
			return result;
			
			
//		}
//		 catch (Exception e) {
//				logger.error(e);
//				return result;
//		 }
	   
   }
   public ArrayList getHeadAndFunctionForCertainMapId(String mapId)throws Exception{
		logger.debug("IN BD getHeadAndFunctionForCertainMapId");
		 ArrayList result = null;
		  
		try
		{
			result=boRef.getHeadAndFunctionForCertainMapId(mapId);
			
			return result;
			
			
		}
		 catch (Exception e) {
				logger.error(e);
				return result;
		 }
	   
   }

}
