package com.wipro.igrs.faqonlinequery.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class FaqOnlineQueryDTO implements Serializable{
private String question;
private String answer;
private String name;
private String email;
private String address;
private String contactNumber;
private String mobileNumber;
private String place;
private String query;
private String countryName;
private ArrayList countryList;
private String stateName;
private ArrayList stateList;
private ArrayList districtList;
private String districtName;
private String countryId;
private String stateId;
private String districtId;
private String queryTxnid;
private String status;
private String reply;
private String createdDate;
private String toDate;
private String fromDate;
private String userID;
private String language;
public String getLanguage() {
	return language;
}
public void setLanguage(String language) {
	this.language = language;
}
public String getUserID() {
	return userID;
}
public void setUserID(String userID) {
	this.userID = userID;
}
public String getToDate() {
	return toDate;
}
public void setToDate(String toDate) {
	this.toDate = toDate;
}
public String getFromDate() {
	return fromDate;
}
public void setFromDate(String fromDate) {
	this.fromDate = fromDate;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public String getReply() {
	return reply;
}
public void setReply(String reply) {
	this.reply = reply;
}
public String getCreatedDate() {
	return createdDate;
}
public void setCreatedDate(String createdDate) {
	this.createdDate = createdDate;
}
public String getQueryTxnid() {
	return queryTxnid;
}
public void setQueryTxnid(String queryTxnid) {
	this.queryTxnid = queryTxnid;
}
public String getCountryName() {
	return countryName;
}
public void setCountryName(String countryName) {
	this.countryName = countryName;
}
public ArrayList getCountryList() {
	return countryList;
}
public void setCountryList(ArrayList countryList) {
	this.countryList = countryList;
}
public String getStateName() {
	return stateName;
}
public void setStateName(String stateName) {
	this.stateName = stateName;
}
public ArrayList getStateList() {
	return stateList;
}
public void setStateList(ArrayList stateList) {
	this.stateList = stateList;
}
public ArrayList getDistrictList() {
	return districtList;
}
public void setDistrictList(ArrayList districtList) {
	this.districtList = districtList;
}
public String getDistrictName() {
	return districtName;
}
public void setDistrictName(String districtName) {
	this.districtName = districtName;
}
public String getCountryId() {
	return countryId;
}
public void setCountryId(String countryId) {
	this.countryId = countryId;
}
public String getStateId() {
	return stateId;
}
public void setStateId(String stateId) {
	this.stateId = stateId;
}
public String getDistrictId() {
	return districtId;
}
public void setDistrictId(String districtId) {
	this.districtId = districtId;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getContactNumber() {
	return contactNumber;
}
public void setContactNumber(String contactNumber) {
	this.contactNumber = contactNumber;
}
public String getMobileNumber() {
	return mobileNumber;
}
public void setMobileNumber(String mobileNumber) {
	this.mobileNumber = mobileNumber;
}
public String getPlace() {
	return place;
}
public void setPlace(String place) {
	this.place = place;
}
public String getQuery() {
	return query;
}
public void setQuery(String query) {
	this.query = query;
}
public String getQuestion() {
	return question;
}
public void setQuestion(String question) {
	this.question = question;
}
public String getAnswer() {
	return answer;
}
public void setAnswer(String answer) {
	this.answer = answer;
}
}
