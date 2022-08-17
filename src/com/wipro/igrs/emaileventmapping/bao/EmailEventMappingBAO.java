/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Egypt.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   EmailEventMappingBAO.java
 * Author      :   Sayed Taha
 * Description :   Represents the Business Access Objects Class for Email Event mapping.
 * Created Date   :   Aug 19, 2008         	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.emaileventmapping.bao;
import java.util.ArrayList;
import java.util.Date;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.emaileventmapping.dao.EmailEventMappingDAO;
import com.wipro.igrs.emaileventmapping.dto.EmailEventMappingDTO;
public class EmailEventMappingBAO {
	     private EmailEventMappingDAO dao;
	     /**
	      * singleton design patterns
	      **/
	     private static EmailEventMappingBAO eventMappingBAO =new EmailEventMappingBAO();
	     private EmailEventMappingBAO(){
	    	 dao=EmailEventMappingDAO.getInstance();
	     }
	     public static EmailEventMappingBAO getInstance(){
	    	 return eventMappingBAO;
	     }
	     
		/**
		 * To get all Departments in the DB.
		 * @return ArrayList all of result
		 */
		public ArrayList getAllEmailEvents(){
			return dao.getAllEmailEvents();
		}
		/**
		 * 
		 * @param deptID
		 * @return
		 */
		public void deleteEmailEventMapping(String deptID) {
			dao.deleteEmailEventMapping(deptID);
			
		}
		
		public ArrayList getAllEmailUsers(){
			return dao.getAllEmailUsers();
		}
		/**
		 * 
		 * @return
		 */
		public ArrayList getEmailEventName(){
			return dao.getEmailEventName();
		}
		/**
		 * 
		 * @param dto
		 */
		public void addEmailEventMapping(EmailEventMappingDTO dto) {
			dao.addEmailEventMapping(dto);
			
		}
		/**
		 * 
		 * @param eventtoedit
		 * @return
		 */
		public EmailEventMappingDTO getEmailEventByID(String eventtoedit) {
			return dao.getEmailEventByID(eventtoedit);
		}
		/**
		 * 
		 * @param dto
		 */
		public void updateEmailEventMapping(EmailEventMappingDTO dto){
			dao.updateEmailEventMapping(dto);
		}
		
}
