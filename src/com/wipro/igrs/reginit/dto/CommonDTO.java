package com.wipro.igrs.reginit.dto;

import java.io.Serializable;


public class CommonDTO implements Serializable{
	private String name;
	private String id;
	private String middleName;
	private String lastName;
	private String checked="";
	//follwoing for confirmation screen
	private String role="";
	private String roleId="";
	//following for khasra details
	private String khasraNum="";
	private String khasraArea="";
	private String lagaan="";
	private String rinPustika="";
	
	//FOLLOWING FOR EXCHANGE DEED
	private String valuationId;
	private String propertyId="";
	private String marketValue;
	private String partyType="";
	private String areaTypeId="";
	private String govBodyId="";
	private String propTypeId="";
	private String l1TypeId="";
	private String l2TypeId="";
	private String areaUnitId="";
	private String area="";
	private String distId="";
	private String tehsilId="";
	private String wardId="";
	private String mohalaId="";
	
	private String regTxnId="";
	private String userId="";
	private String partyTxnId="";
	
	private String sysMv="";              //for system generated market value
	
	private String particularDesc="";
	
	
	
	
	public String getParticularDesc() {
		return particularDesc;
	}
	public void setParticularDesc(String particularDesc) {
		this.particularDesc = particularDesc;
	}
	public String getSysMv() {
		return sysMv;
	}
	public void setSysMv(String sysMv) {
		this.sysMv = sysMv;
	}
	public String getAreaTypeId() {
		return areaTypeId;
	}
	public void setAreaTypeId(String areaTypeId) {
		this.areaTypeId = areaTypeId;
	}
	public String getGovBodyId() {
		return govBodyId;
	}
	public void setGovBodyId(String govBodyId) {
		this.govBodyId = govBodyId;
	}
	public String getPropTypeId() {
		return propTypeId;
	}
	public void setPropTypeId(String propTypeId) {
		this.propTypeId = propTypeId;
	}
	public String getL1TypeId() {
		return l1TypeId;
	}
	public void setL1TypeId(String typeId) {
		l1TypeId = typeId;
	}
	public String getL2TypeId() {
		return l2TypeId;
	}
	public void setL2TypeId(String typeId) {
		l2TypeId = typeId;
	}
	public String getAreaUnitId() {
		return areaUnitId;
	}
	public void setAreaUnitId(String areaUnitId) {
		this.areaUnitId = areaUnitId;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getDistId() {
		return distId;
	}
	public void setDistId(String distId) {
		this.distId = distId;
	}
	public String getTehsilId() {
		return tehsilId;
	}
	public void setTehsilId(String tehsilId) {
		this.tehsilId = tehsilId;
	}
	public String getWardId() {
		return wardId;
	}
	public void setWardId(String wardId) {
		this.wardId = wardId;
	}
	public String getMohalaId() {
		return mohalaId;
	}
	public void setMohalaId(String mohalaId) {
		this.mohalaId = mohalaId;
	}
	public String getRegTxnId() {
		return regTxnId;
	}
	public void setRegTxnId(String regTxnId) {
		this.regTxnId = regTxnId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPartyTxnId() {
		return partyTxnId;
	}
	public void setPartyTxnId(String partyTxnId) {
		this.partyTxnId = partyTxnId;
	}
	public String getValuationId() {
		return valuationId;
	}
	public void setValuationId(String valuationId) {
		this.valuationId = valuationId;
	}
	public String getPropertyId() {
		return propertyId;
	}
	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}
	public String getMarketValue() {
		return marketValue;
	}
	public void setMarketValue(String marketValue) {
		this.marketValue = marketValue;
	}
	public String getPartyType() {
		return partyType;
	}
	public void setPartyType(String partyType) {
		this.partyType = partyType;
	}
	public String getKhasraNum() {
		return khasraNum;
	}
	public void setKhasraNum(String khasraNum) {
		this.khasraNum = khasraNum;
	}
	public String getKhasraArea() {
		return khasraArea;
	}
	public void setKhasraArea(String khasraArea) {
		this.khasraArea = khasraArea;
	}
	public String getLagaan() {
		return lagaan;
	}
	public void setLagaan(String lagaan) {
		this.lagaan = lagaan;
	}
	public String getRinPustika() {
		return rinPustika;
	}
	public void setRinPustika(String rinPustika) {
		this.rinPustika = rinPustika;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}
	/**
	 * @param middleName the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the checked
	 */
	public String getChecked() {
		return checked;
	}
	/**
	 * @param checked the checked to set
	 */
	public void setChecked(String checked) {
		this.checked = checked;
	}
	
	
}