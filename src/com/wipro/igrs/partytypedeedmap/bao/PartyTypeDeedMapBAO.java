/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Egypt.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   PartyTypeDeedMapBAO.java
 * Author      :   Sayed Taha
 * Description :   Represents the Business Access Objects Class for party type deed mapping Master.
 * Created Date   :   sept 3, 2008         	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.partytypedeedmap.bao;
import java.util.ArrayList;
import java.util.Date;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.partytypedeedmap.dao.PartyTypeDeedMapDAO;
import com.wipro.igrs.partytypedeedmap.dto.PartyTypeDeedMapDTO;
public class PartyTypeDeedMapBAO {
	     private PartyTypeDeedMapDAO dao;
	     /**
	      * singleton design patterns
	      **/
	     private static PartyTypeDeedMapBAO eventMappingBAO =new PartyTypeDeedMapBAO();
	     private PartyTypeDeedMapBAO(){
	    	 dao=PartyTypeDeedMapDAO.getInstance();
	     }
	     public static PartyTypeDeedMapBAO getInstance(){
	    	 return eventMappingBAO;
	     }
	     
		/**
		 * Get All Party Type Deed Mappings
		 * @return
		 */
		public ArrayList getAllPartyTypeDeedMaps(){
			return dao.getAllPartyTypeDeedMaps();
		}
		public void deletePartyTypeDeedMaps(String mapID, String roleId, String funId, String userId){
			dao.deletePartyTypeDeedMaps(mapID,roleId,funId,userId);
		}
		public ArrayList getDeedTypes() {
			return dao.getDeedTypes();
		}
		public ArrayList getPartyTypes(){
			return dao.getPartyTypes();
		}
		public void addPartyDeedMapping(PartyTypeDeedMapDTO dto, String roleId, String funId, String userId) {
			dao.addPartyDeedMapping(dto,roleId,funId,userId);
		}
		public PartyTypeDeedMapDTO getPartyDeedMapByID(String mapID){
			return dao.getPartyDeedMapByID(mapID);
		}
		public void updatePartyDeedMap(PartyTypeDeedMapDTO dto, String roleId, String funId, String userId) {
			dao.updatePartyDeedMap(dto,roleId,funId,userId);
		}
		public boolean isMapExists(PartyTypeDeedMapDTO dto) {
			return dao.isMapExists(dto);
		}
		
}
		
		

