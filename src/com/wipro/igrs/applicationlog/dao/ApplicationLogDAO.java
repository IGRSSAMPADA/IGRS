package com.wipro.igrs.applicationlog.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.wipro.igrs.CitizenFeedback.dao.CitizenFeedbackDAO;
import com.wipro.igrs.applicationlog.dto.ApplicationLogDTO;
import com.wipro.igrs.applicationlog.sql.ApplicationLogSQL;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.rolemaster.dto.RoleDTO;
import com.wipro.igrs.rolemaster.sql.RoleCommonSQL;



public class ApplicationLogDAO {
	
	Logger logger = (Logger) Logger.getLogger(ApplicationLogDAO.class);
	
	DBUtility dbUtility = null;
	String sql = null;
	ApplicationLogDTO appDTO = null;
	List activityList = null;
	ArrayList subList = null;
	
	String param[] = null;

	public List searchByDate(String durationfrom,String durationto,String userId, String language) {
		
		ArrayList typeList = new ArrayList();
		String[] param = new String[3];
		param[0] = durationfrom;
		param[1]= durationto;
		param[2] = userId;
		logger.debug(param[0]);
		logger.debug(param[1]);
		logger.debug(param[2]);
		
		try {
			
			
			String[] param1 = new String[1];
			param1[0] = userId;
			logger.debug(param1[0]);
			sql = ApplicationLogSQL.GET_ROLE_GROUP;
			logger.debug(sql);
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			String rolegroup = dbUtility.executeQry(param1);
			logger.debug(rolegroup);
			
			if(rolegroup.equalsIgnoreCase("IGRSAdminRoleGroup"))
			{
				String[] param2 = new String[2];
				param2[0] = durationfrom;
				param2[1]= durationto;
				logger.debug("rolegroup IGRSAdminRoleGroup found condition true");
				logger.debug(param2[0]);
				logger.debug(param2[1]);
				sql = ApplicationLogSQL.SELECT_APP_LOG_FOR_ADMIN;
				logger.debug(sql);
				dbUtility = new DBUtility();
				dbUtility.createPreparedStatement(sql);
				ArrayList mainList1 = dbUtility.executeQuery(param2);
				logger.debug(mainList1);
				for (int i = 0; i < mainList1.size(); i++) {
					appDTO = new ApplicationLogDTO();
					subList = (ArrayList) mainList1.get(i);
					logger.debug(subList);
					//appDTO.setTableName(subList.get(0).toString());
					//appDTO.setOperationType(subList.get(1).toString());
					if("en".equalsIgnoreCase(language))
					{
						appDTO.setOperatedFuncID(subList.get(1)==null?"":subList.get(1).toString());
					}
					else if("hi".equalsIgnoreCase(language))
					{
						appDTO.setOperatedFuncID(subList.get(5)==null?"":subList.get(5).toString());
					}
					if("en".equalsIgnoreCase(language))
					{
					appDTO.setOperationStatus(subList.get(0)==null?"":subList.get(0).toString());
					}
					else if("hi".equalsIgnoreCase(language))
					{
						appDTO.setOperationStatus(subList.get(4)==null?"":subList.get(4).toString());
					}
					appDTO.setOperationDate(subList.get(2).toString());
					appDTO.setOperatedBy(subList.get(3).toString());
					
					
	                typeList.add(appDTO);
			}
			}
			else
			{
				logger.debug("rolegroup IGRSAdminRoleGroup found condition false :normal user");
			sql = ApplicationLogSQL.SELECT_APP_LOG;
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			logger.debug(sql);
			ArrayList mainList1 = dbUtility.executeQuery(param);
			logger.debug(mainList1);
			for (int i = 0; i < mainList1.size(); i++) {
				appDTO = new ApplicationLogDTO();
				subList = (ArrayList) mainList1.get(i);
				logger.debug(subList);
				//appDTO.setTableName(subList.get(0).toString());
				//appDTO.setOperationType(subList.get(1).toString());
				if("en".equalsIgnoreCase(language))
				{
					appDTO.setOperatedFuncID(subList.get(1)==null?"":subList.get(1).toString());
				}
				else if("hi".equalsIgnoreCase(language))
				{
					appDTO.setOperatedFuncID(subList.get(5)==null?"":subList.get(5).toString());
				}
				if("en".equalsIgnoreCase(language))
				{
					appDTO.setOperationStatus(subList.get(0)==null?"":subList.get(0).toString());
				}
				else if("hi".equalsIgnoreCase(language))
				{
					appDTO.setOperationStatus(subList.get(4)==null?"":subList.get(4).toString());
				}
				appDTO.setOperationDate(subList.get(2).toString());
				appDTO.setOperatedBy(subList.get(3).toString());
				
				
                typeList.add(appDTO);
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return typeList;
	}
	
	
	public ArrayList transSearchByDate(String durationfrom,String durationto, String userId, String language) {
		
		ArrayList transTypeList = new ArrayList();
		
	
		
		try {    
			
			
			String[] param1 = new String[1];
			param1[0] = userId;
			sql = ApplicationLogSQL.GET_ROLE_GROUP;
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			String rolegroup = dbUtility.executeQry(param1);
			
			if(rolegroup.equalsIgnoreCase("IGRSAdminRoleGroup"))
			{
				
				String[] param2 = new String[2];
				param2[0] = durationfrom;
				param2[1]= durationto;
				
				sql = ApplicationLogSQL.SELECT_ADMIN_TRANSACTION_LOG_DETAILS;
				dbUtility = new DBUtility();
				dbUtility.createPreparedStatement(sql);
				
				ArrayList mainList1 = dbUtility.executeQuery(param2);
				
				for (int i = 0; i < mainList1.size(); i++) {
					subList = (ArrayList) mainList1.get(i);
					System.out.println("sublist value..." + subList);
					appDTO = new ApplicationLogDTO();
					
					
					if("en".equalsIgnoreCase(language))
					{
						appDTO.setOperatedActivityId(subList.get(0)==null?"":subList.get(0).toString());
					}
					else if("hi".equalsIgnoreCase(language))
					{
						appDTO.setOperatedActivityId(subList.get(4)==null?"":subList.get(4).toString());
					}
					
					
					if("en".equalsIgnoreCase(language))
					{
						appDTO.setOperatedFuncID(subList.get(1)==null?"":subList.get(1).toString());
					}
					else if("hi".equalsIgnoreCase(language))
					{
						appDTO.setOperatedFuncID(subList.get(5)==null?"":subList.get(5).toString());
					}
					
					
					appDTO.setOperationDate(subList.get(2).toString()==null?"":subList.get(2).toString());
					
					appDTO.setOperatedBy(subList.get(3).toString()==null?"":subList.get(3).toString());
					
					appDTO.setPaymentStatus(subList.get(6).toString()==null?"":subList.get(6).toString());
					
					if("P".equalsIgnoreCase(subList.get(6).toString()))
					{
						
						if("en".equalsIgnoreCase(language))
						{
							appDTO.setPaymentStatus("SUCCESS");
						}
						
						else
						{
							appDTO.setPaymentStatus("सफ़ल");
						}
					}
					else if("I".equalsIgnoreCase(subList.get(6).toString()))
					{
						if("en".equalsIgnoreCase(language))
						{
							appDTO.setPaymentStatus("INITIATED");
						}
						else
						{
							appDTO.setPaymentStatus("शुरुआत");
						}
					}
					
					
					
					appDTO.setPaymentTxnID(subList.get(7).toString()==null?"":subList.get(7).toString());
					appDTO.setTxnAmount(subList.get(8).toString()==null?"":subList.get(8).toString());
				
						
					transTypeList.add(appDTO);
				}
			}
			
			else
			{
				String[] param = new String[3];
				param[0] = durationfrom;
				param[1] = durationto;
				param[2] = userId;
				
				sql = ApplicationLogSQL.SELECT_TRANSACTION_LOG_DETAILS;
				dbUtility = new DBUtility();
				dbUtility.createPreparedStatement(sql);
				ArrayList mainList1 = dbUtility.executeQuery(param);
				for (int i = 0; i < mainList1.size(); i++) {
					subList = (ArrayList) mainList1.get(i);
					System.out.println("sublist value..." + subList);
					appDTO = new ApplicationLogDTO();
					appDTO.setOpertdFuncID(subList.get(0).toString());
					appDTO.setGrossAmount(subList.get(1).toString());
					appDTO.setService(subList.get(5).toString());
					if("P".equalsIgnoreCase(subList.get(2).toString())){
						appDTO.setStatus("SUCCESS");
					}
					else if("I".equalsIgnoreCase(subList.get(2).toString()))
					{
					appDTO.setStatus("INITIATED");
					}
					
					if(subList.get(3)==null){
						appDTO.setCreatedBy("");
					}
					else {
						appDTO.setCreatedBy(subList.get(3).toString());
					}

					if(subList.get(4)==null){
						appDTO.setCreatedDate("");
					}
					else {
						appDTO.setCreatedDate(subList.get(4).toString());
					}
					
					transTypeList.add(appDTO);
				}
			}
			
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return transTypeList;
	}


	public boolean deleteactivitylog(String durationfrom, String durationto,String userId) {
		boolean flag = false;
		String[] param = new String[3];
		param[0] = durationfrom;
		param[1] = durationto;
		param[2] = userId;
		try {
			
			String[] param1 = new String[1];
			param1[0] = userId;
			sql = ApplicationLogSQL.GET_ROLE_GROUP;
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			String rolegroup = dbUtility.executeQry(param1);
			
			if(rolegroup.equalsIgnoreCase("IGRSAdminRoleGroup"))
			{
				String[] param2 = new String[2];
				param2[0] = durationfrom;
				param2[1]= durationto;
				
				sql = ApplicationLogSQL.DELETE_ADMIN_ACTIVITY_LOG;
				dbUtility = new DBUtility();
				dbUtility.createPreparedStatement(sql);
				flag = dbUtility.executeUpdate(param2);
			}
			
			else
			{
			
			
			sql = ApplicationLogSQL.DELETE_ACTIVITY_LOG;
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			flag = dbUtility.executeUpdate(param);
			
			}
			
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag;
	}


	public boolean deletetxnlog(String durationfrom, String durationto,
			String userId) {
		boolean flag = false;
		String[] param = new String[3];
		param[0] = durationfrom;
		param[1] = durationto;
		param[2] = userId;
		try {
			
			String[] param1 = new String[1];
			param1[0] = userId;
			sql = ApplicationLogSQL.GET_ROLE_GROUP;
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			String rolegroup = dbUtility.executeQry(param1);
			
			if(rolegroup.equalsIgnoreCase("IGRSAdminRoleGroup"))
			{
				String[] param2 = new String[2];
				param2[0] = durationfrom;
				param2[1]= durationto;
				
				sql = ApplicationLogSQL.DELETE_ADMIN_TXN_LOGS;
				dbUtility = new DBUtility();
				dbUtility.createPreparedStatement(sql);
				flag = dbUtility.executeUpdate(param2);
			}
			
			else
			{
			
			
			sql = ApplicationLogSQL.DELETE_TXN_LOGS;
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			flag = dbUtility.executeUpdate(param);
			
			}
			
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag;
	}


	public String getrolegroup(String userId)throws Exception {
		
			String rolegroup = "";
		try {
			
			String[] param1 = new String[1];
			param1[0] = userId;
			sql = ApplicationLogSQL.GET_ROLE_GROUP;
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			rolegroup = dbUtility.executeQry(param1);
		
		
		
	}
		catch (Exception e) {
			e.getStackTrace();
		} 
		finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rolegroup;
		
}
}
