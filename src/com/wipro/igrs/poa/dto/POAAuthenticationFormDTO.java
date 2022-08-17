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
* 1.0             SHRUTI           xxxxx              
 * --------------------------------------------------------------------------------
*/
package com.wipro.igrs.poa.dto;

import java.util.ArrayList; 
import java.io.Serializable;

import org.apache.struts.upload.FormFile;


public class POAAuthenticationFormDTO implements Serializable{
	
	private String currentYear;
	
	
	private String firstName;
	private String middleName;
	private String lastName;
	private String gender;
	private String dob;
	private String fatherName;
	private String motherName;
	private String spouseName;
	private String address;
	private String phno;
	private String mobno;
	private String email;
	private String poaType;
	private String poaRegNo;
	private String awrdrcountryId;
	private String awrdrcountryName;
	private String awrdrcountryList;
	private String awrdrstateId;
	private String awrdrstateName;
	private String awrdrstateList;
	private String awrdrdistrictId;
	private String awrdrdistrictName;
	private String awrdrdistrictList;
	private String awrdrpostalCode;
	private String poaToDate;
	private String poaFromDate;
	private String sr_UserId;
	private String manualStampchk;
	private String poaDur;
	private String stampduty;
	private String stampCode;
	private String doe;
	private String doa;
	private String sroName;
	private String sroId;
	private String srname;
	private String dobDay;
	private String dobMonth;
	private String dobYear;
	private String poaTypeName;
	private String poaTypeId;
	private ArrayList poaTypeList;
	private ArrayList ecodePoaTypeList;
	private String ecodePoaTypeId;
	private String ecodePoaTypeName;
	private String estampAppNo;
	private String awrdrFirstName;
	private String awrdrMiddleName;
	private String awrdrLastName;
	private String awrdrGender;
	private String awrdrDob;
	private String awrdrDobDay;
	private String awrdrDobMonth;
	private String awrdrDobYear;
	private String awrdrSpouseName;
	private String awrdrFatherName;
	private String awrdrMotherName;
	private String awrdrAddress;
	private String awrdrPhno;
	private String awrdrMobno;
	private String awrdrEmail;
	private FormFile awrdrSignature;
	private FormFile awrdrPhoto;
	private FormFile awrdrThumb;
	private FormFile acptrSignature;
	private FormFile acptrPhoto;
	private FormFile acptrThumb;
	
	private String PoaId;
	private String PoaCreateDate;
	
	
	private String awrdrSignatureName;
	private String awrdrPhotoName;
	private String awrdrThumbName;
	private String acptrSignatureName;
	private String acptrPhotoName;
	private String acptrThumbName;
	
	ArrayList PoaList=new ArrayList();
	
	public ArrayList getPoaList() {
		return PoaList;
	}
	public void setPoaList(ArrayList poaList) {
		PoaList = poaList;
	}
	public String getPoaId() {
		return PoaId;
	}
	public void setPoaId(String poaId) {
		PoaId = poaId;
	}
	public String getPoaCreateDate() {
		return PoaCreateDate;
	}
	public void setPoaCreateDate(String poaCreateDate) {
		PoaCreateDate = poaCreateDate;
	}
	public String getAwrdrSignatureName() {
		return awrdrSignatureName;
	}
	public void setAwrdrSignatureName(String awrdrSignatureName) {
		this.awrdrSignatureName = awrdrSignatureName;
	}
	public String getAwrdrPhotoName() {
		return awrdrPhotoName;
	}
	public void setAwrdrPhotoName(String awrdrPhotoName) {
		this.awrdrPhotoName = awrdrPhotoName;
	}
	public String getAwrdrThumbName() {
		return awrdrThumbName;
	}
	public void setAwrdrThumbName(String awrdrThumbName) {
		this.awrdrThumbName = awrdrThumbName;
	}
	public String getAcptrSignatureName() {
		return acptrSignatureName;
	}
	public void setAcptrSignatureName(String acptrSignatureName) {
		this.acptrSignatureName = acptrSignatureName;
	}
	public String getAcptrPhotoName() {
		return acptrPhotoName;
	}
	public void setAcptrPhotoName(String acptrPhotoName) {
		this.acptrPhotoName = acptrPhotoName;
	}
	public String getAcptrThumbName() {
		return acptrThumbName;
	}
	public void setAcptrThumbName(String acptrThumbName) {
		this.acptrThumbName = acptrThumbName;
	}
	
	public FormFile getAwrdrSignature() {
		return awrdrSignature;
	}
	public void setAwrdrSignature(FormFile awrdrSignature) {
		this.awrdrSignature = awrdrSignature;
	}
	public FormFile getAwrdrPhoto() {
		return awrdrPhoto;
	}
	public void setAwrdrPhoto(FormFile awrdrPhoto) {
		this.awrdrPhoto = awrdrPhoto;
	}
	public FormFile getAwrdrThumb() {
		return awrdrThumb;
	}
	public void setAwrdrThumb(FormFile awrdrThumb) {
		this.awrdrThumb = awrdrThumb;
	}
	public FormFile getAcptrSignature() {
		return acptrSignature;
	}
	public void setAcptrSignature(FormFile acptrSignature) {
		this.acptrSignature = acptrSignature;
	}
	public FormFile getAcptrPhoto() {
		return acptrPhoto;
	}
	public void setAcptrPhoto(FormFile acptrPhoto) {
		this.acptrPhoto = acptrPhoto;
	}
	public FormFile getAcptrThumb() {
		return acptrThumb;
	}
	public void setAcptrThumb(FormFile acptrThumb) {
		this.acptrThumb = acptrThumb;
	}
	private String acptrcountryId;
	private String acptrcountryName;
	private String acptrcountryList;
	private String acptrstateId;
	private String acptrstateName;
	private String acptrstateList;
	private String acptrdistrictId;
	private String acptrdistrictName;
	private String acptrdistrictList;
	private String acptrpostalCode;
	private String acptrFirstName;
	private String acptrMiddleName;
	private String acptrLastName;
	private String acptrGender;
	private String acptrDob;
	private String acptrDobDay;
	private String acptrDobMonth;
	private String acptrDobYear;
	private String acptrSpouseName;
	private String acptrFatherName;
	private String acptrMotherName;
	private String acptrAddress;
	private String acptrPhno;
	private String acptrMobno;
	private String acptrEmail;
	private String docname;
	
	private String poaAuthNo;
	
	
	public String getPoaAuthNo() {
		return poaAuthNo;
	}
	public void setPoaAuthNo(String poaAuthNo) {
		this.poaAuthNo = poaAuthNo;
	}
	public String getSrname() {
		return srname;
	}
	public void setSrname(String srname) {
		this.srname = srname;
	}
	public String getCurrentYear() {
		return currentYear;
	}
	public void setCurrentYear(String currentYear) {
		this.currentYear = currentYear;
	}
	public String getSroId() {
		return sroId;
	}
	public void setSroId(String sroId) {
		this.sroId = sroId;
	}
	public String getManualStampchk() {
		return manualStampchk;
	}
	public void setManualStampchk(String manualStampchk) {
		this.manualStampchk = manualStampchk;
	}
	public String getSr_UserId() {
		return sr_UserId;
	}
	public void setSr_UserId(String sr_UserId) {
		this.sr_UserId = sr_UserId;
	}
	public String getPoaToDate() {
		return poaToDate;
	}
	public void setPoaToDate(String poaToDate) {
		this.poaToDate = poaToDate;
	}
	public String getPoaFromDate() {
		return poaFromDate;
	}
	public void setPoaFromDate(String poaFromDate) {
		this.poaFromDate = poaFromDate;
	}
	public String getAwrdrcountryId() {
		return awrdrcountryId;
	}
	public void setAwrdrcountryId(String awrdrcountryId) {
		this.awrdrcountryId = awrdrcountryId;
	}
	public String getAwrdrcountryName() {
		return awrdrcountryName;
	}
	public void setAwrdrcountryName(String awrdrcountryName) {
		this.awrdrcountryName = awrdrcountryName;
	}
	public String getAwrdrcountryList() {
		return awrdrcountryList;
	}
	public void setAwrdrcountryList(String awrdrcountryList) {
		this.awrdrcountryList = awrdrcountryList;
	}
	public String getAwrdrstateId() {
		return awrdrstateId;
	}
	public void setAwrdrstateId(String awrdrstateId) {
		this.awrdrstateId = awrdrstateId;
	}
	public String getAwrdrstateName() {
		return awrdrstateName;
	}
	public void setAwrdrstateName(String awrdrstateName) {
		this.awrdrstateName = awrdrstateName;
	}
	public String getAwrdrstateList() {
		return awrdrstateList;
	}
	public void setAwrdrstateList(String awrdrstateList) {
		this.awrdrstateList = awrdrstateList;
	}
	public String getAwrdrdistrictId() {
		return awrdrdistrictId;
	}
	public void setAwrdrdistrictId(String awrdrdistrictId) {
		this.awrdrdistrictId = awrdrdistrictId;
	}
	public String getAwrdrdistrictName() {
		return awrdrdistrictName;
	}
	public void setAwrdrdistrictName(String awrdrdistrictName) {
		this.awrdrdistrictName = awrdrdistrictName;
	}
	public String getAwrdrdistrictList() {
		return awrdrdistrictList;
	}
	public void setAwrdrdistrictList(String awrdrdistrictList) {
		this.awrdrdistrictList = awrdrdistrictList;
	}
	public String getAwrdrpostalCode() {
		return awrdrpostalCode;
	}
	public void setAwrdrpostalCode(String awrdrpostalCode) {
		this.awrdrpostalCode = awrdrpostalCode;
	}
	public String getAcptrcountryId() {
		return acptrcountryId;
	}
	public void setAcptrcountryId(String acptrcountryId) {
		this.acptrcountryId = acptrcountryId;
	}
	public String getAcptrcountryName() {
		return acptrcountryName;
	}
	public void setAcptrcountryName(String acptrcountryName) {
		this.acptrcountryName = acptrcountryName;
	}
	public String getAcptrcountryList() {
		return acptrcountryList;
	}
	public void setAcptrcountryList(String acptrcountryList) {
		this.acptrcountryList = acptrcountryList;
	}
	public String getAcptrstateId() {
		return acptrstateId;
	}
	public void setAcptrstateId(String acptrstateId) {
		this.acptrstateId = acptrstateId;
	}
	public String getAcptrstateName() {
		return acptrstateName;
	}
	public void setAcptrstateName(String acptrstateName) {
		this.acptrstateName = acptrstateName;
	}
	public String getAcptrstateList() {
		return acptrstateList;
	}
	public void setAcptrstateList(String acptrstateList) {
		this.acptrstateList = acptrstateList;
	}
	public String getAcptrdistrictId() {
		return acptrdistrictId;
	}
	public void setAcptrdistrictId(String acptrdistrictId) {
		this.acptrdistrictId = acptrdistrictId;
	}
	public String getAcptrdistrictName() {
		return acptrdistrictName;
	}
	public void setAcptrdistrictName(String acptrdistrictName) {
		this.acptrdistrictName = acptrdistrictName;
	}
	public String getAcptrdistrictList() {
		return acptrdistrictList;
	}
	public void setAcptrdistrictList(String acptrdistrictList) {
		this.acptrdistrictList = acptrdistrictList;
	}
	public String getAcptrpostalCode() {
		return acptrpostalCode;
	}
	public void setAcptrpostalCode(String acptrpostalCode) {
		this.acptrpostalCode = acptrpostalCode;
	}
	
	public String getDocname() {
		return docname;
	}
	public void setDocname(String docname) {
		this.docname = docname;
	}
	public String getEstampAppNo() {
		return estampAppNo;
	}
	public void setEstampAppNo(String estampAppNo) {
		this.estampAppNo = estampAppNo;
	}
	public ArrayList getEcodePoaTypeList() {
		return ecodePoaTypeList;
	}
	public void setEcodePoaTypeList(ArrayList ecodePoaTypeList) {
		this.ecodePoaTypeList = ecodePoaTypeList;
	}
	public String getEcodePoaTypeId() {
		return ecodePoaTypeId;
	}
	public void setEcodePoaTypeId(String ecodePoaTypeId) {
		this.ecodePoaTypeId = ecodePoaTypeId;
	}
	public String getEcodePoaTypeName() {
		return ecodePoaTypeName;
	}
	public void setEcodePoaTypeName(String ecodePoaTypeName) {
		this.ecodePoaTypeName = ecodePoaTypeName;
	}
	

	public String getAcptrFirstName() {
		return acptrFirstName;
	}
	public void setAcptrFirstName(String acptrFirstName) {
		this.acptrFirstName = acptrFirstName;
	}
	public String getAcptrMiddleName() {
		return acptrMiddleName;
	}
	public void setAcptrMiddleName(String acptrMiddleName) {
		this.acptrMiddleName = acptrMiddleName;
	}
	public String getAcptrLastName() {
		return acptrLastName;
	}
	public void setAcptrLastName(String acptrLastName) {
		this.acptrLastName = acptrLastName;
	}
	public String getAcptrGender() {
		return acptrGender;
	}
	public void setAcptrGender(String acptrGender) {
		this.acptrGender = acptrGender;
	}
	public String getAcptrDob() {
		return acptrDob;
	}
	public void setAcptrDob(String acptrDob) {
		this.acptrDob = acptrDob;
	}
	public String getAcptrDobDay() {
		return acptrDobDay;
	}
	public void setAcptrDobDay(String acptrDobDay) {
		this.acptrDobDay = acptrDobDay;
	}
	public String getAcptrDobMonth() {
		return acptrDobMonth;
	}
	public void setAcptrDobMonth(String acptrDobMonth) {
		this.acptrDobMonth = acptrDobMonth;
	}
	public String getAcptrDobYear() {
		return acptrDobYear;
	}
	public void setAcptrDobYear(String acptrDobYear) {
		this.acptrDobYear = acptrDobYear;
	}
	public String getAcptrSpouseName() {
		return acptrSpouseName;
	}
	public void setAcptrSpouseName(String acptrSpouseName) {
		this.acptrSpouseName = acptrSpouseName;
	}
	public String getAcptrFatherName() {
		return acptrFatherName;
	}
	public void setAcptrFatherName(String acptrFatherName) {
		this.acptrFatherName = acptrFatherName;
	}
	public String getAcptrMotherName() {
		return acptrMotherName;
	}
	public void setAcptrMotherName(String acptrMotherName) {
		this.acptrMotherName = acptrMotherName;
	}
	public String getAcptrAddress() {
		return acptrAddress;
	}
	public void setAcptrAddress(String acptrAddress) {
		this.acptrAddress = acptrAddress;
	}
	public String getAcptrPhno() {
		return acptrPhno;
	}
	public void setAcptrPhno(String acptrPhno) {
		this.acptrPhno = acptrPhno;
	}
	public String getAcptrMobno() {
		return acptrMobno;
	}
	public void setAcptrMobno(String acptrMobno) {
		this.acptrMobno = acptrMobno;
	}
	public String getAcptrEmail() {
		return acptrEmail;
	}
	public void setAcptrEmail(String acptrEmail) {
		this.acptrEmail = acptrEmail;
	}
	public String getAwrdrFirstName() {
		return awrdrFirstName;
	}
	public void setAwrdrFirstName(String awrdrFirstName) {
		this.awrdrFirstName = awrdrFirstName;
	}
	public String getAwrdrMiddleName() {
		return awrdrMiddleName;
	}
	public void setAwrdrMiddleName(String awrdrMiddleName) {
		this.awrdrMiddleName = awrdrMiddleName;
	}
	
	public String getAwrdrLastName() {
		return awrdrLastName;
	}
	public void setAwrdrLastName(String awrdrLastName) {
		this.awrdrLastName = awrdrLastName;
	}
	public String getAwrdrGender() {
		return awrdrGender;
	}
	public void setAwrdrGender(String awrdrGender) {
		this.awrdrGender = awrdrGender;
	}
	public String getAwrdrDob() {
		return awrdrDob;
	}
	public void setAwrdrDob(String awrdrDob) {
		this.awrdrDob = awrdrDob;
	}
	public String getAwrdrDobDay() {
		return awrdrDobDay;
	}
	public void setAwrdrDobDay(String awrdrDobDay) {
		this.awrdrDobDay = awrdrDobDay;
	}
	public String getAwrdrDobMonth() {
		return awrdrDobMonth;
	}
	public void setAwrdrDobMonth(String awrdrDobMonth) {
		this.awrdrDobMonth = awrdrDobMonth;
	}
	public String getAwrdrDobYear() {
		return awrdrDobYear;
	}
	public void setAwrdrDobYear(String awrdrDobYear) {
		this.awrdrDobYear = awrdrDobYear;
	}
	public String getAwrdrSpouseName() {
		return awrdrSpouseName;
	}
	public void setAwrdrSpouseName(String awrdrSpouseName) {
		this.awrdrSpouseName = awrdrSpouseName;
	}
	public String getAwrdrFatherName() {
		return awrdrFatherName;
	}
	public void setAwrdrFatherName(String awrdrFatherName) {
		this.awrdrFatherName = awrdrFatherName;
	}
	public String getAwrdrMotherName() {
		return awrdrMotherName;
	}
	public void setAwrdrMotherName(String awrdrMotherName) {
		this.awrdrMotherName = awrdrMotherName;
	}
	public String getAwrdrAddress() {
		return awrdrAddress;
	}
	public void setAwrdrAddress(String awrdrAddress) {
		this.awrdrAddress = awrdrAddress;
	}
	public String getAwrdrPhno() {
		return awrdrPhno;
	}
	public void setAwrdrPhno(String awrdrPhno) {
		this.awrdrPhno = awrdrPhno;
	}
	public String getAwrdrMobno() {
		return awrdrMobno;
	}
	public void setAwrdrMobno(String awrdrMobno) {
		this.awrdrMobno = awrdrMobno;
	}
	public String getAwrdrEmail() {
		return awrdrEmail;
	}
	public void setAwrdrEmail(String awrdrEmail) {
		this.awrdrEmail = awrdrEmail;
	}
	
	public String getPoaTypeName() {
		return poaTypeName;
	}
	public void setPoaTypeName(String poaTypeName) {
		this.poaTypeName = poaTypeName;
	}
	public String getPoaTypeId() {
		return poaTypeId;
	}
	public void setPoaTypeId(String poaTypeId) {
		this.poaTypeId = poaTypeId;
	}
	public ArrayList getPoaTypeList() {
		return poaTypeList;
	}
	public void setPoaTypeList(ArrayList poaTypeList) {
		this.poaTypeList = poaTypeList;
	}
	public String getDobDay() {
		return dobDay;
	}
	public void setDobDay(String dobDay) {
		this.dobDay = dobDay;
	}
	public String getDobMonth() {
		return dobMonth;
	}
	public void setDobMonth(String dobMonth) {
		this.dobMonth = dobMonth;
	}
	public String getDobYear() {
		return dobYear;
	}
	public void setDobYear(String dobYear) {
		this.dobYear = dobYear;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public String getMotherName() {
		return motherName;
	}
	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}
	public String getSpouseName() {
		return spouseName;
	}
	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhno() {
		return phno;
	}
	public void setPhno(String phno) {
		this.phno = phno;
	}
	public String getMobno() {
		return mobno;
	}
	public void setMobno(String mobno) {
		this.mobno = mobno;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPoaType() {
		return poaType;
	}
	public void setPoaType(String poaType) {
		this.poaType = poaType;
	}
	public String getPoaRegNo() {
		return poaRegNo;
	}
	public void setPoaRegNo(String poaRegNo) {
		this.poaRegNo = poaRegNo;
	}
	public String getPoaDur() {
		return poaDur;
	}
	public void setPoaDur(String poaDur) {
		this.poaDur = poaDur;
	}
	public String getStampduty() {
		return stampduty;
	}
	public void setStampduty(String stampduty) {
		this.stampduty = stampduty;
	}
	public String getStampCode() {
		return stampCode;
	}
	public void setStampCode(String stampCode) {
		this.stampCode = stampCode;
	}
	public String getDoe() {
		return doe;
	}
	public void setDoe(String doe) {
		this.doe = doe;
	}
	public String getDoa() {
		return doa;
	}
	public void setDoa(String doa) {
		this.doa = doa;
	}
	public String getSroName() {
		return sroName;
	}
	public void setSroName(String sroName) {
		this.sroName = sroName;
	}

}