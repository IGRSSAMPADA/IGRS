/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	WardpatwariDTO.java
 * Author		:	Rafiq Rahiman.T 
 * Date			: 	04/03/2008
 */
package com.wipro.igrs.wardpatwarimaster.dto;

import java.util.ArrayList;

public class WardpatwariDTO implements java.io.Serializable{
	public WardpatwariDTO(){
	}
	private String wardpatwariId;
	private String wardpatwariName;
	private String wardpatwariDesc;
	private String wardpatwariStatus;
	private String wardpageName;
	private String name;
	private String value;
	private String tehsilId;
	private ArrayList tehsilidList;
	private ArrayList wardpatwariList;
	public ArrayList getWardpatwariList() {
		return wardpatwariList;
	}
	public void setWardpatwariList(ArrayList wardpatwariList) {
		this.wardpatwariList = wardpatwariList;
	}
	public String getWardpatwariId() {
		return wardpatwariId;
	}
	public void setWardpatwariId(String wardpatwariId) {
		this.wardpatwariId = wardpatwariId;
	}
	public String getWardpatwariName() {
		return wardpatwariName;
	}
	public void setWardpatwariName(String wardpatwariName) {
		this.wardpatwariName = wardpatwariName;
	}
	public String getWardpatwariDesc() {
		return wardpatwariDesc;
	}
	public void setWardpatwariDesc(String wardpatwariDesc) {
		this.wardpatwariDesc = wardpatwariDesc;
	}
	public String getWardpatwariStatus() {
		return wardpatwariStatus;
	}
	public void setWardpatwariStatus(String wardpatwariStatus) {
		this.wardpatwariStatus = wardpatwariStatus;
	}
	
	public String getWardpageName() {
		return wardpageName;
	}
	public void setWardpageName(String wardpageName) {
		this.wardpageName = wardpageName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public ArrayList getTehsilidList() {
		return tehsilidList;
	}
	public void setTehsilidList(ArrayList tehsilidList) {
		this.tehsilidList = tehsilidList;
	}
	public String getTehsilId() {
		return tehsilId;
	}
	public void setTehsilId(String tehsilId) {
		this.tehsilId = tehsilId;
	}
}
