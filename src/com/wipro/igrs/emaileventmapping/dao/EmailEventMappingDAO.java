/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Egypt.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   EmailEventDAO.java
 * Author      :   Sayed Taha
 * Description :   Represents the DAO Class for Department Master.
 * Created Date   :   Aug 18, 2008         	 		 
 * --------------------------------------------------------------------------------
 */
	
package com.wipro.igrs.emaileventmapping.dao;

import java.util.ArrayList;
import java.util.Date;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.emaileventmapping.dto.EmailEventMappingDTO;
import com.wipro.igrs.emaileventmapping.sql.EmailEventMappingSQL;
public class EmailEventMappingDAO {
	//Singleton Design patterns
	private static EmailEventMappingDAO emailEventMappingDAO = new EmailEventMappingDAO();
	public static EmailEventMappingDAO getInstance(){
		return emailEventMappingDAO;
	}
	private EmailEventMappingDAO(){
	}
	/**
	 * 
	 * @return
	 */
	public ArrayList getAllEmailEvents() {
		ArrayList result = new ArrayList();
		ArrayList subList = null;
		EmailEventMappingDTO dto = null;
		DBUtility dbUtility = null;
		try {
			String sql = EmailEventMappingSQL.SELECT_ALL_EVENTS_MAPPING;
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			ArrayList eventsList = dbUtility.executeQuery(sql);
			for (int i = 0; i < eventsList.size(); i++) {
				subList = (ArrayList) eventsList.get(i);
				dto = new EmailEventMappingDTO();
				
				dto.setEmailUserLookupDtlsID(subList.get(0).toString());
				dto.setEmailLookupName(subList.get(1).toString());
				dto.setEmailLookupTxnID(subList.get(2).toString());
				dto.setToEmailUserID(subList.get(3).toString());
				dto.setCcEmailUserID(subList.get(4).toString());
				dto.setFromEmailUserID(subList.get(5).toString());
				String date=subList.get(6).toString();
				if(!date.equals("")){
					dto.setCreationDate(new java.util.Date(date));
				}
				result.add(dto);
			}
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				e.getStackTrace();
			}
		}
		
		return result;
	}
	/**
	 * 
	 * @return
	 */
	public ArrayList getAllEmailUsers() {
		ArrayList result = new ArrayList();
		ArrayList subList = null;
		DBUtility dbUtility = null;
		try {
			String sql = EmailEventMappingSQL.SELECT_EMAIL_USER_ID;
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			ArrayList eventsList = dbUtility.executeQuery(sql);
			result.add(new String("Select User >>>"));
			for (int i = 0; i < eventsList.size(); i++) {
				subList = (ArrayList) eventsList.get(i);
				result.add(subList.get(0).toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				e.getStackTrace();
			}
		}
		result.add(new String("-non-"));
		return result;
	}
	/**
	 * 
	 * @param dto
	 */
	public void addEmailEventMapping(EmailEventMappingDTO dto) {
		String sql = EmailEventMappingSQL.INSERT_EMAIL_EVENT_MAPPING;
		DBUtility dbUtility = null;
		String param[] = new String[4];
		param[0] = dto.getEmailLookupTxnID();
		param[1] = dto.getToEmailUserID();
		param[2] = dto.getCcEmailUserID();
		param[3] = dto.getFromEmailUserID();
		
		
		try {
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			
			boolean succeed = dbUtility.executeUpdate(param);
			if (succeed) {
				dbUtility.commit();
			} else {
				dbUtility.rollback();
			}
				 
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				dbUtility.closeConnection();
			}catch(Exception e) {
				e.getStackTrace();
			}
		}
	}
	/**
	 * 
	 * @param deptID
	 */
	
	public void deleteEmailEventMapping(String deptID) {
		String sql = EmailEventMappingSQL.DELETE_EMAIL_EVENT_MAPPING;
		String param[] = new String[1];
		param[0]=deptID;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			boolean succeed = dbUtility.executeUpdate(param);
			if (succeed) {
				dbUtility.commit();
			} else {
				dbUtility.rollback();
			}
				 
		} catch (Exception e) {
			e.getStackTrace();
		}finally {
			try {
				dbUtility.closeConnection();
			}catch(Exception e) {
				e.getStackTrace();
			}
		}
		
	}
	/**
	 * 
	 * @return
	 */
	public ArrayList getEmailEventName() {
		ArrayList result = new ArrayList();
		ArrayList subList = null;
		EmailEventMappingDTO dto = null;
		DBUtility dbUtility = null;
		
		try {
			String sql = EmailEventMappingSQL.SELECT_EMAIL_EVENT_NAME_LIST;
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			ArrayList eventsList = dbUtility.executeQuery(sql);
			dto = new EmailEventMappingDTO();
			dto.setEmailLookupTxnID("Select Event>>>");
			dto.setEmailLookupName("Select Event>>>");
			result.add(dto);
			for (int i = 0; i < eventsList.size(); i++) {
				subList = (ArrayList) eventsList.get(i);
				dto = new EmailEventMappingDTO();
				
				dto.setEmailLookupTxnID(subList.get(0).toString());
				dto.setEmailLookupName(subList.get(1).toString());
				
				result.add(dto);
			}
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				e.getStackTrace();
			}
		}
		return result;
	}
	/**
	 * To get email event Object By ID 
	 * @return ArrayList all of result
	 */
	public EmailEventMappingDTO getEmailEventByID(String evenyID){
		EmailEventMappingDTO dto = null;
		DBUtility dbUtility=null;
		String [] param=new String[1];
		param[0]=evenyID;
		try {
			String sql = EmailEventMappingSQL.SELECT_EMAIL_EVENT_BYID;
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			
			ArrayList municipalList = dbUtility.executeQuery(param);
			ArrayList subList = (ArrayList) municipalList.get(0);
			
			dto = new EmailEventMappingDTO();
			
			dto.setEmailUserLookupDtlsID(subList.get(0).toString());
			dto.setEmailLookupName(subList.get(1).toString());
			dto.setEmailLookupTxnID(subList.get(2).toString());
			dto.setToEmailUserID(subList.get(3).toString());
			if(subList.get(4)!=null){
				dto.setCcEmailUserID(subList.get(4).toString());
			}
			dto.setFromEmailUserID(subList.get(5).toString());	
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				e.getStackTrace();
			}
		}
		return dto;
	}
   /**
    * 
    * @param dto
    */
	public void updateEmailEventMapping(EmailEventMappingDTO dto){
    	String sql = EmailEventMappingSQL.UPDATE_EMAIL_EVENT_MAPPING;
		DBUtility dbUtility = null;
		String param[] = new String[5];
		param[0] = dto.getEmailLookupTxnID();
		param[1] = dto.getToEmailUserID();
		param[2] = dto.getCcEmailUserID();
		param[3] = dto.getFromEmailUserID();
		param[4] = dto.getEmailUserLookupDtlsID();
		
		try {
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			boolean succeed = dbUtility.executeUpdate(param);
			if (succeed) {
				dbUtility.commit();
			} else {
				dbUtility.rollback();
			}
				 
		} catch (Exception e) {
			e.getStackTrace();
		}finally {
			try {
				dbUtility.closeConnection();
			}catch(Exception e) {
				e.getStackTrace();
			}
		}	
    }
	
}