/*
* Copyright Notice
*==============================================================================
* This file contains proprietary information of IGRS Bhopal Madhya Pradesh .
* Copying or reproduction without prior written approval is prohibited.
* Copyright (c) 2007-08
*==============================================================================
*
* File Name   :   
 * Author      :   
 * Description :   
 * ----------------------------------------------------------------------------
* Version         Modified By     Modified On           Modification
* ----------------------------------------------------------------------------
* 1.0             SHRUTI          xxxxx              
 * --------------------------------------------------------------------------------
*/


package com.wipro.igrs.auditinspection.dto;


import java.io.Serializable;
import java.util.ArrayList;


public class PropertyDetailsDTO implements Serializable {

	private String propType;
	private String useType;
	private String distName;
	private String tehsilName;
	private String wardName;
	private String mohallaName;
	private String areaTypeName;
	private String govMunicplBody;
	private String vikasKhandName;
	private String riCircle;
	private String layoutDet;
	private String nazoolStreetNo;
	private String address;
	private String eastBoundary;
	private String westBoundary;
	private String northBoundary;
	private String southBoundary;
	private String total;
	ArrayList propertyList=new ArrayList();
	
	public ArrayList getPropertyList() {
		return propertyList;
	}
	public void setPropertyList(ArrayList propertyList) {
		this.propertyList = propertyList;
	}
	public String getPropType() {
		return propType;
	}
	public void setPropType(String propType) {
		this.propType = propType;
	}
	public String getUseType() {
		return useType;
	}
	public void setUseType(String useType) {
		this.useType = useType;
	}
	public String getDistName() {
		return distName;
	}
	public void setDistName(String distName) {
		this.distName = distName;
	}
	public String getTehsilName() {
		return tehsilName;
	}
	public void setTehsilName(String tehsilName) {
		this.tehsilName = tehsilName;
	}
	public String getWardName() {
		return wardName;
	}
	public void setWardName(String wardName) {
		this.wardName = wardName;
	}
	public String getMohallaName() {
		return mohallaName;
	}
	public void setMohallaName(String mohallaName) {
		this.mohallaName = mohallaName;
	}
	public String getAreaTypeName() {
		return areaTypeName;
	}
	public void setAreaTypeName(String areaTypeName) {
		this.areaTypeName = areaTypeName;
	}
	public String getGovMunicplBody() {
		return govMunicplBody;
	}
	public void setGovMunicplBody(String govMunicplBody) {
		this.govMunicplBody = govMunicplBody;
	}
	public String getVikasKhandName() {
		return vikasKhandName;
	}
	public void setVikasKhandName(String vikasKhandName) {
		this.vikasKhandName = vikasKhandName;
	}
	public String getRiCircle() {
		return riCircle;
	}
	public void setRiCircle(String riCircle) {
		this.riCircle = riCircle;
	}
	public String getLayoutDet() {
		return layoutDet;
	}
	public void setLayoutDet(String layoutDet) {
		this.layoutDet = layoutDet;
	}
	public String getNazoolStreetNo() {
		return nazoolStreetNo;
	}
	public void setNazoolStreetNo(String nazoolStreetNo) {
		this.nazoolStreetNo = nazoolStreetNo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEastBoundary() {
		return eastBoundary;
	}
	public void setEastBoundary(String eastBoundary) {
		this.eastBoundary = eastBoundary;
	}
	public String getWestBoundary() {
		return westBoundary;
	}
	public void setWestBoundary(String westBoundary) {
		this.westBoundary = westBoundary;
	}
	public String getNorthBoundary() {
		return northBoundary;
	}
	public void setNorthBoundary(String northBoundary) {
		this.northBoundary = northBoundary;
	}
	public String getSouthBoundary() {
		return southBoundary;
	}
	public void setSouthBoundary(String southBoundary) {
		this.southBoundary = southBoundary;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}

	
	
	
}
