package com.wipro.igrs.slotbooking.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class CommonDropDownDTO implements Serializable{
private String countryId;
private String countryName;
private String stateId;
private String stateName;
private String distId;
private String distName;
private String talukaId;
private String talukaName;
private String mohallaId;
private String mohallaName;
private String testbox;
private String selectcntryid;
private String selectstatid;
private String selectdistid;
private String selectmohid;
private ArrayList countryList;
private ArrayList stateList;
private ArrayList distList;
private ArrayList talukaList;
private ArrayList mohallaList;

public String getCountryId() {
	return countryId;
}
public void setCountryId(String countryId) {
	this.countryId = countryId;
}
public String getCountryName() {
	return countryName;
}
public void setCountryName(String countryName) {
	this.countryName = countryName;
}
public String getStateId() {
	return stateId;
}
public void setStateId(String stateId) {
	this.stateId = stateId;
}
public String getStateName() {
	return stateName;
}
public void setStateName(String stateName) {
	this.stateName = stateName;
}
public String getDistId() {
	return distId;
}
public void setDistId(String distId) {
	this.distId = distId;
}
public String getDistName() {
	return distName;
}
public void setDistName(String distName) {
	this.distName = distName;
}
public String getTalukaId() {
	return talukaId;
}
public void setTalukaId(String talukaId) {
	this.talukaId = talukaId;
}
public String getTalukaName() {
	return talukaName;
}
public void setTalukaName(String talukaName) {
	this.talukaName = talukaName;
}
public String getMohallaId() {
	return mohallaId;
}
public void setMohallaId(String mohallaId) {
	this.mohallaId = mohallaId;
}
public String getMohallaName() {
	return mohallaName;
}
public void setMohallaName(String mohallaName) {
	this.mohallaName = mohallaName;
}
public ArrayList getCountryList() {
	return countryList;
}
public void setCountryList(ArrayList countryList) {
	this.countryList = countryList;
}
public ArrayList getStateList() {
	return stateList;
}
public void setStateList(ArrayList stateList) {
	this.stateList = stateList;
}
public ArrayList getDistList() {
	return distList;
}
public void setDistList(ArrayList distList) {
	this.distList = distList;
}
public ArrayList getTalukaList() {
	return talukaList;
}
public void setTalukaList(ArrayList talukaList) {
	this.talukaList = talukaList;
}
public ArrayList getMohallaList() {
	return mohallaList;
}
public void setMohallaList(ArrayList mohallaList) {
	this.mohallaList = mohallaList;
}
public String getTestbox() {
	return testbox;
}
public void setTestbox(String testbox) {
	this.testbox = testbox;
}
public String getSelectcntryid() {
	return selectcntryid;
}
public void setSelectcntryid(String selectcntryid) {
	this.selectcntryid = selectcntryid;
}
public String getSelectstatid() {
	return selectstatid;
}
public void setSelectstatid(String selectstatid) {
	this.selectstatid = selectstatid;
}
public String getSelectdistid() {
	return selectdistid;
}
public void setSelectdistid(String selectdistid) {
	this.selectdistid = selectdistid;
}
public String getSelectmohid() {
	return selectmohid;
}
public void setSelectmohid(String selectmohid) {
	this.selectmohid = selectmohid;
}

}
