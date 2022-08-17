/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, egypt.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   PartyTypeDeedMapBD.java
 * Author      :   Sayed Taha
 * Description :   Represents the BD
 * Created Date   :   sept 3, 2008         	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.partytypedeedmap.bd;

import java.util.ArrayList;

import com.wipro.igrs.partytypedeedmap.bao.PartyTypeDeedMapBAO;
import com.wipro.igrs.partytypedeedmap.dto.PartyTypeDeedMapDTO;

public class PartyTypeDeedMapBD {
	private PartyTypeDeedMapBAO bao=PartyTypeDeedMapBAO.getInstance();
	
	/**
	 * Singleton design pattern
	 **/
	private static PartyTypeDeedMapBD emailEventMappingBD = new PartyTypeDeedMapBD();
	private PartyTypeDeedMapBD(){
	}
	public static PartyTypeDeedMapBD getInstance(){
		return emailEventMappingBD;
	}
	public ArrayList getAllPartyTypeDeedMaps() {
		return bao.getAllPartyTypeDeedMaps();
	}
	public void deletePartyTypeDeedMaps(String mapID, String roleId, String funId, String userId){
		bao.deletePartyTypeDeedMaps(mapID,roleId,funId,userId);
	}
	public ArrayList getDeedTypes() {
		return bao.getDeedTypes();
	}
	public ArrayList getPartyTypes(){
		return bao.getPartyTypes();
	}
	public void addPartyDeedMapping(PartyTypeDeedMapDTO dto, String roleId, String funId, String userId) {
		bao.addPartyDeedMapping(dto,roleId,funId,userId);
	}
	public PartyTypeDeedMapDTO getPartyDeedMapByID(String mapID){
		return bao.getPartyDeedMapByID(mapID);
	}
	public void updatePartyDeedMap(PartyTypeDeedMapDTO dto, String roleId, String funId, String userId){
		bao.updatePartyDeedMap(dto,roleId,funId,userId);
	}
	public boolean isMapExists(PartyTypeDeedMapDTO dto) {
		return bao.isMapExists(dto);
	}
}
