package com.wipro.igrs.clr.services;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.db.DBUtility;

public class CLRServiceDAO {

	private static Logger logger = (Logger) Logger.getLogger(CLRServiceDAO.class);
	public static  ArrayList<String> getValAsArrayListAsString(String sql, String[] param, String methodName) throws Exception{
		ArrayList<String> list = null;
		DBUtility db = null;
		ArrayList temp = new ArrayList();
		try{
			db = new DBUtility();
			db.createPreparedStatement(sql);
			temp = db.executeQuery(param);
		}catch(Exception e){
			logger.debug("Some error occured in method "+methodName);
			e.printStackTrace();
		}finally{
			db.closeConnection();
		}
		list = new ArrayList<String>();
		for(int i = 0;i<temp.size();i++){
			ArrayList<String> tempList = (ArrayList<String>) temp.get(i);
			list.add(tempList.get(0));
		}
		return list;
	}
	
	public static  ArrayList getValAsArrayList(String sql, String[] param, String methodName) throws Exception{
		ArrayList<String> list = null;
		DBUtility db = null;
		try{
			list = new ArrayList();
			db = new DBUtility();
			db.createPreparedStatement(sql);
			list = db.executeQuery(param);
		}catch(Exception e){
			logger.debug("Some error occured in method "+methodName);
			e.printStackTrace();
		}finally{
			db.closeConnection();
		}
		return list;
	}
	public static boolean insertData(String sql, String[] param, String methodName) throws Exception{
		boolean result=false;
		DBUtility db = null;
		try{
			db = new DBUtility();
			db.createPreparedStatement(sql);
			result = db.executeUpdate(param);
		}catch(Exception e){
			logger.debug("Some error occured in method "+methodName);
			e.printStackTrace();
		}finally{
			db.closeConnection();
		}
		return result;
	}
	public static String checkifAvailable(String sql, String[] param, String methodName) throws Exception{
		String response="N";
		DBUtility db = null;
		try{
			db = new DBUtility();
			db.createPreparedStatement(sql);
			String count = db.executeQry(param);
			if(count.equalsIgnoreCase("0")){
				response="Y";
			}
		}catch(Exception e){
			logger.debug("Some error occured in method "+methodName);
			e.printStackTrace();
		}finally{
			db.closeConnection();
		}
		return response;
	}
	public static String checkCount(String sql, String[] param, String methodName) throws Exception{
		String response="0";
		DBUtility db = null;
		try{
			db = new DBUtility();
			db.createPreparedStatement(sql);
			String count = db.executeQry(param);
			response=count;
		}catch(Exception e){
			logger.debug("Some error occured in method "+methodName);
			e.printStackTrace();
		}finally{
			db.closeConnection();
		}
		return response;
	}
	public static boolean deleteData(String sql, String[] param, String methodName) throws Exception{
		boolean result=false;
		DBUtility db = null;
		try{
			db = new DBUtility();
			db.createPreparedStatement(sql);
			result = db.executeUpdate(param);
		}catch(Exception e){
			logger.debug("Some error occured in method "+methodName);
			e.printStackTrace();
		}finally{
			db.closeConnection();
		}
		return result;
	}
	public static boolean updateData(String sql, String[] param, String methodName) throws Exception{
		boolean result=false;
		DBUtility db = null;
		try{
			db = new DBUtility();
			db.createPreparedStatement(sql);
			result = db.executeUpdate(param);
		}catch(Exception e){
			logger.debug("Some error occured in method "+methodName);
			e.printStackTrace();
		}finally{
			db.closeConnection();
		}
		return result;
	}
	public static String formPath(String regTxnId, String formType) throws Exception {
		String path = "";
		DBUtility db = null;
		String sql = "";//CLRServicesSQL.GET_FORMA1_PATH;
		if("A1".equalsIgnoreCase(formType)){
			sql=CLRServicesSQL.GET_FORMA1_PATH;
		}else if("A2".equalsIgnoreCase(formType)){
			sql=CLRServicesSQL.GET_FORMA2_PATH;
		}
		try{
			db = new DBUtility();
			db.createPreparedStatement(sql);
			String [] param = {regTxnId};
			path = db.executeQry(param);
		}catch(Exception e){
			logger.debug("Some error occured in method +formPath");
			e.printStackTrace();
		}finally{
			db.closeConnection();
		}
		return path;
	}
}
