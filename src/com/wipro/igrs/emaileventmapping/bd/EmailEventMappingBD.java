/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Egypt.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   EmailEventMappingBD.java
 * Author      :   Sayed Taha
 * Description :   Represents the Business delegate Class for Email Event Mapping Master.
 * Created Date   :   Aug 18, 2008         	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.emaileventmapping.bd;

import java.util.ArrayList;

import com.wipro.igrs.emaileventmapping.bao.EmailEventMappingBAO;
import com.wipro.igrs.emaileventmapping.dto.EmailEventMappingDTO;

public class EmailEventMappingBD {
	private EmailEventMappingBAO bao=EmailEventMappingBAO.getInstance();
	
	/**
	 * Singleton design pattern
	 **/
	private static EmailEventMappingBD emailEventMappingBD = new EmailEventMappingBD();
	private EmailEventMappingBD(){
	}
	/**
	 * 
	 * @return
	 */
	public static EmailEventMappingBD getInstance(){
		return emailEventMappingBD;
	}
	/**
	 * 
	 * @return
	 */
	public ArrayList getAllEmailEvents() {
		return bao.getAllEmailEvents();
	}
	/**
	 * 
	 * @param deptID
	 */
	public void deleteEmailEventMapping(String deptID){
		bao.deleteEmailEventMapping(deptID);
	}
	/**
	 * 
	 * @return
	 */
	public ArrayList getAllEmailUsers(){
		return bao.getAllEmailUsers();
	}
   /**
    * 
    * @return
    */
	public ArrayList getEmailEventName(){
		return bao.getEmailEventName();
	}
	/**
	 * 
	 * @param dto
	 */
	public void addEmailEventMapping(EmailEventMappingDTO dto) {
		bao.addEmailEventMapping(dto);
		
	}
	/**
	 * 
	 * @param eventtoedit
	 * @return
	 */
	public EmailEventMappingDTO getEmailEventByID(String eventtoedit) {
		return bao.getEmailEventByID(eventtoedit);
	}
	/**
	 * 
	 * @param dto
	 */
	public void updateEmailEventMapping(EmailEventMappingDTO dto){
		bao.updateEmailEventMapping(dto);
	}
}
