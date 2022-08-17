/* Copyright (c) 2007-08 WIPRO INFOTECH. All Rights Reserved.
 * 
 *  This software is the confidential and proprietary information of WIPRO
 *  ("Confidential Information"). You shall not
 *  disclose such Confidential Information and shall use it only in
 *  accordance with the terms of the license agreement you entered into
 *  with WIPRO.
 *  
 * WIPRO MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE 
 * SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF 
 * MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, OR NON-INFRINGEMENT. WIPRO SHALL NOT BE 
 * LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF 
 * USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS 
 * DERIVATIVES.
 * 
 */

/* 
 * FILE NAME: WillViewDAO.java
 * @author NIHAR R MISHRA
 * @version 1.0 
 * INITIAL CODING: 12th December 2007 
 * MODIFIED ON:	   12th April 2008
 * @DESCRIPTION:   THIS CLASS REPRESENTS THE BUSINESS DELEGATE CLASS
 */

package com.wipro.igrs.willdeposit.dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.willdeposit.bd.WillCommonBD;
import com.wipro.igrs.willdeposit.dto.WillDepositDTO;
import com.wipro.igrs.willdeposit.sql.CommonSQL;

/**
 * @author NIHAR M.
 * 
 */
public class WillViewDAO {

	//DBUtility dbUtil;
	private Logger logger = (Logger) Logger.getLogger(WillViewDAO.class);

	/**
	 * @throws Exception
	 */
	public WillViewDAO() throws Exception {

		//dbUtil = new DBUtility();
	}

	/**
	 * @param willParamId
	 * @return ArrayList
	 */
	public WillDepositDTO getwillIdDetails(String willParamId, String lang) { // VIEW 2nd
		WillCommonBD com = new WillCommonBD();
		String countryName="";
		String stateName="";
		String districtName="";
		String idProofType="";
		PreparedStatement prepStmt=null;
		ResultSet rset=null;
		WillDepositDTO dto = new WillDepositDTO();
		Blob doc;		
		String path;
		String sqlQuery = "";
        int rowCount;
        Connection con=null;
        DBUtility dbUtil=null;
		String sql = CommonSQL.WILL_ID_VIEW_QUERY;
		logger.debug("SQL:   " + sql);
		try {dbUtil=new DBUtility("");
			if (con == null || con.isClosed()) {
				con = dbUtil.getDBConnection();
			}
			prepStmt = con.prepareStatement(sql);
			prepStmt.setString(1, willParamId);
			rset = prepStmt.executeQuery();
			while (rset.next()){
				String agent = rset.getString(2);
				
				if (agent.equalsIgnoreCase("TESTATOR")) {
					
	                
	                dto.setWillId(rset.getString(1)==null ?"":rset.getString(1).toString());
					dto.setTotalFee(rset.getString(3)==null ?"":rset.getString(3).toString());
					dto.setDespositDate(rset.getString(4)==null ?"":rset.getString(4).toString());
					dto.setDrID(rset.getString(5)==null ?"":rset.getString(5).toString());
					dto.setDistrict(rset.getString(6)==null ?"":rset.getString(6).toString());
					dto.setFirstName(rset.getString(7)==null ?"":rset.getString(7).toString());
					dto.setMidName(rset.getString(8)==null ?"":rset.getString(8).toString());
					dto.setLastName(rset.getString(9)==null ?"":rset.getString(9).toString());
					if (rset.getString(10).equalsIgnoreCase("M")){
						if("en".equalsIgnoreCase(lang)){
							dto.setGender("Male");	
						}
						else{
							dto.setGender("पुस्र्ष");
						}
					}
					else
						if("en".equalsIgnoreCase(lang)){
							dto.setGender("Female");
						}
						else{
							dto.setGender("स्त्री");
						}
					if (rset.getString(11) != null)
						dto.setAge(rset.getString(11));
					else
						dto.setAge("");
					dto.setFatherName(rset.getString(12)==null ?"":rset.getString(12).toString());
					dto.setMotherName(rset.getString(13)==null ?"":rset.getString(13).toString());
					dto.setSpouseName(rset.getString(14)==null ?"":rset.getString(14).toString());
					dto.setAddress(rset.getString(15)==null ?"":rset.getString(15).toString());
					countryName=com.countryName(rset.getString(16).toString(), lang);
					dto.setCountry(countryName);
					//dto.setCountry(rset.getString(17)==null ?"":rset.getString(17).toString());
					stateName=com.stateName(rset.getString(18).toString(), rset.getString(16).toString(), lang);
					dto.setState(stateName);
					//dto.setState(rset.getString(19)==null ?"":rset.getString(19).toString());
					dto.setDistrictId(rset.getString(20)==null ?"":rset.getString(20).toString());
					//dto.setCity(rset.getString(21)==null ?"":rset.getString(21).toString());
					districtName=com.districtName(rset.getString(18).toString(), rset.getString(20).toString(), lang);
					dto.setCity(districtName);
					if (rset.getString(22) != null)
						dto.setPin(rset.getString(22));
					else
						dto.setPin("");

					dto.setPhone(rset.getString(23)==null ?"":rset.getString(23).toString());
					dto.setMphone(rset.getString(24)==null ?"":rset.getString(24).toString());
					dto.setEmail(rset.getString(25)==null ?"":rset.getString(25).toString());
					//dto.setIdProof(rset.getString(26)==null ?"":rset.getString(26).toString());
					idProofType=com.getIdType((String)rset.getString(26), lang);
					dto.setIdProof(idProofType);
					dto.setIdProofNo(rset.getString(27)==null ?"":rset.getString(27).toString());
					/*doc = rset.getBlob(28);
					if(doc!=null){
				    dto.setDocContents(doc.getBytes(1,(int)doc.length()));
					}*/
					
					
					path=(String)rset.getString(28);
					if(path!=null){
					dto.setPhotoPath(rset.getString(28).toString());
					}
					
				    /*doc = rset.getBlob(29);
				    if(doc!=null){
					    dto.setThumbContents(doc.getBytes(1,(int)doc.length()));
						}*/
					
					path = (String)rset.getString(29);
				    if(path!=null){
				    	dto.setThumbPath(rset.getString(29).toString());
				    }
					
					/*
				    doc = rset.getBlob(30);
				    if(doc!=null){
					    dto.setSignatureContents(doc.getBytes(1,(int)doc.length()));
						}
				    */
				    path = (String)rset.getString(30);
				    if(path!=null){
				    	dto.setSignPath(rset.getString(30).toString());
				    }
					
					
				    
				    
				    dto.setRemarks(rset.getString(32));
					dto.setWillStatus(rset.getString(33)==null ?"":rset.getString(33).toString());
					
					if(dto.getWillStatus().equalsIgnoreCase("Pending Retrieve")||dto.getWillStatus().equalsIgnoreCase("Retrieved")||dto.getWillStatus().equalsIgnoreCase("RI")){
						
						dto.setWithdrawlDueDate(rset.getString(41));
					}
					else {
						dto.setWithdrawlDueDate(rset.getString(31));
					}
					
					if(dto.getDocumentName()==null||dto.getDocumentName()==""){
						dto.setDocumentName(rset.getString(34)==null ?"":rset.getString(34).toString());
					}
					if(dto.getThunmbName()==null||dto.getThunmbName()==""){
	                dto.setThunmbName(rset.getString(35)==null ?"":rset.getString(35).toString());
					}
					if(dto.getSignatureName()==null||dto.getSignatureName()==""){
	                dto.setSignatureName(rset.getString(36)==null ?"":rset.getString(36).toString());
					}
					dto.setDepositRemarks(rset.getString(37)==null ?"":rset.getString(37).toString());
					String treasuryId = (String)rset.getString(38);
					dto.setTreasuryId(treasuryId);
					 dto.setBankName(rset.getString(46)==null ?"":rset.getString(46).toString());
					 dto.setBankAddress(rset.getString(47)==null ?"":rset.getString(47).toString());
					
				}
				if (agent.equalsIgnoreCase("AGENT")) {
					
					dto.setAgent(rset.getString(2));
					dto.setAgentFirstName(rset.getString(7));
					dto.setAgentMidName(rset.getString(8));
					dto.setAgentLastName(rset.getString(9));
//					dto.setGender(rset.getString(10));

					if (rset.getString(10).equalsIgnoreCase("M")){
						if("en".equalsIgnoreCase(lang)){
						dto.setAgentGender("Male");
						}
						else{
							dto.setAgentGender("पुस्र्ष");
						}
					}
					else
						if("en".equalsIgnoreCase(lang)){
						dto.setAgentGender("Female");
						}
						else{
							dto.setAgentGender("स्त्री");
						}
					if (rset.getString(11) != null)
						dto.setAgentAge(rset.getString(11));
					else
						dto.setAgentAge("");

					dto.setAgentFatherName(rset.getString(12));
					dto.setAgentMotherName(rset.getString(13));
					dto.setAgentSpouseName(rset.getString(14));
					dto.setAgentAddress(rset.getString(15));
					//dto.setAgentCountry(rset.getString(17));
					countryName=com.countryName(rset.getString(16).toString(), lang);
					dto.setAgentCountry(countryName);
					//dto.setAgentState(rset.getString(19));
					stateName=com.stateName(rset.getString(18).toString(), rset.getString(16).toString(), lang);
					dto.setAgentState(stateName);
					dto.setAgentDistrictId(rset.getString(20));
					//dto.setAgentCity(rset.getString(21));
					districtName=com.districtName(rset.getString(18).toString(), rset.getString(20).toString(), lang);
					dto.setAgentCity(districtName);
					dto.setAgentPin(rset.getString(22)==null ?"":rset.getString(22).toString());
					dto.setAgentPhone(rset.getString(23)==null ?"":rset.getString(23).toString());
					dto.setAgentMPhone(rset.getString(24)==null ?"":rset.getString(24).toString());
					dto.setAgentEmail(rset.getString(25));
					//dto.setAgentIdProof(rset.getString(26));
					idProofType=com.getIdType((String)rset.getString(26), lang);
					dto.setAgentIdProof(idProofType);
					dto.setAgentIdProofNo(rset.getString(27));
					
/*					doc = rset.getBlob(28);
					if(doc!=null){
						dto.setDocContents(doc.getBytes(1,(int)doc.length()));
						}
				    
				    doc = rset.getBlob(29);
				    if(doc!=null){
				    	dto.setThumbContents(doc.getBytes(1,(int)doc.length()));
						}
				    
				    doc = rset.getBlob(30);
				    if(doc!=null){
				    	dto.setSignatureContents(doc.getBytes(1,(int)doc.length()));
						}*/
					

//anuj
					path=(String)rset.getString(28);
					if(path!=null){
						dto.setPhotoPath(rset.getString(28).toString());
					}
					
							   
					path = (String)rset.getString(29);
					if(path!=null){
						dto.setThumbPath(rset.getString(29).toString());
					}
								
								
					path = (String)rset.getString(30);
					if(path!=null){
						dto.setSignPath(rset.getString(30).toString());
					}
//anuj								
				    dto.setWillStatus(rset.getString(33));
				    if(dto.getWillStatus().equalsIgnoreCase("Pending Retrieve")||dto.getWillStatus().equalsIgnoreCase("Retrieved")||dto.getWillStatus().equalsIgnoreCase("RI")){
						
						dto.setWithdrawlDueDate(rset.getString(41));
					}
					else {
						dto.setWithdrawlDueDate(rset.getString(31));
					}
					dto.setRemarks(rset.getString(32));
					
					dto.setDocumentName(rset.getString(34)==null ?"":rset.getString(34).toString());
	                dto.setThunmbName(rset.getString(35)==null ?"":rset.getString(35).toString());
	                dto.setSignatureName(rset.getString(36)==null ?"":rset.getString(36).toString());
	                dto.setDepositRemarks(rset.getString(37)==null ?"":rset.getString(37).toString());
	                dto.setTreasuryId(rset.getString(38)==null ?"":rset.getString(38).toString());
	                
	                /*doc = rset.getBlob(39);
	                if(doc!=null){
	                	dto.setAgentProofContents(doc.getBytes(1,(int)doc.length()));
						}*/
	                //anuj
	                path=(String)rset.getString(39);
	                if(path!=null){
	                	dto.setAgentProofPath(rset.getString(39).toString());
	                }
	                //anuj
	                
				    dto.setAgentProofName(rset.getString(40)==null ?"":rset.getString(40).toString());
				    dto.setAgentBankName(rset.getString(46)==null ?"":rset.getString(46).toString());
					 dto.setAgentBankAddress(rset.getString(47)==null ?"":rset.getString(47).toString());
					
				}
				
				
					if (agent.equalsIgnoreCase("Citizen")) {
					
					dto.setAgentCategory(rset.getString(2));
					dto.setRetrieverFirstName(rset.getString(7));
					dto.setRetrieverMidName(rset.getString(8));
					dto.setRetrieverLastName(rset.getString(9));

					if (rset.getString(10).equalsIgnoreCase("M")){
						if("en".equalsIgnoreCase(lang)){
						dto.setRetrieverGender("Male");
						}
						else{
							dto.setRetrieverGender("पुस्र्ष");
						}
					}
					else{
						if("en".equalsIgnoreCase(lang)){
						dto.setRetrieverGender("Female");
						}else{
							dto.setRetrieverGender("स्त्री");
						}
					}
					if (rset.getString(11) != null)
						dto.setRetrieverAge(rset.getString(11));
					else
						dto.setRetrieverAge("");

					dto.setRetrieverFatherName(rset.getString(12));
					dto.setRetrieverMotherName(rset.getString(13));
					dto.setRetrieverSpouseName(rset.getString(14));
					dto.setRetrieverAddress(rset.getString(15));
					System.out.println((String)rset.getString(16)+"--"+(String)rset.getString(18)+";;;"+(String)rset.getString(20));
					//dto.setRetrieverCountry(rset.getString(17));
					countryName=com.countryName(rset.getString(16).toString(), lang);
					dto.setRetrieverCountry(countryName);
					//dto.setRetrieverState(rset.getString(19));
					stateName=com.stateName((String)rset.getString(18), (String)rset.getString(16), lang);
					dto.setRetrieverState(stateName);
					//dto.setAgentDistrictId(rset.getString(20));
					//dto.setRetrieverCity(rset.getString(21));
					districtName=com.districtName((String)rset.getString(18), (String)rset.getString(20), lang);
					dto.setRetrieverCity(districtName);
					dto.setRetrieverPin(rset.getString(22)==null ?"":rset.getString(22).toString());
					dto.setRetrieverPhone(rset.getString(23)==null ?"":rset.getString(23).toString());
					dto.setRetrieverMPhone(rset.getString(24)==null ?"":rset.getString(24).toString());
					dto.setRetrieverEMail(rset.getString(25));
					//dto.setRetrieverIdProof(rset.getString(26));
					idProofType=com.getIdType(rset.getString(26).toString(), lang);
					dto.setRetrieverIdProof(idProofType);
					
					dto.setRetrieverIdProofNo(rset.getString(27));
					
					/*doc = rset.getBlob(28);
					if(doc!=null){
						dto.setRetDocContents(doc.getBytes(1,(int)doc.length()));
						}
				    
				    doc = rset.getBlob(29);
				    if(doc!=null){
				    	dto.setRetThumbContents(doc.getBytes(1,(int)doc.length()));
						}
				    
				    doc = rset.getBlob(30);
				    if(doc!=null){
				    	dto.setRetSignatureContents(doc.getBytes(1,(int)doc.length()));
						}
				    doc = rset.getBlob(44);
				    if(doc!=null){
				    	dto.setDeathCertDocContents(doc.getBytes(1,(int)doc.length()));
						}*/
					
					
					path=(String)rset.getString(28);
					if(path!=null){
						dto.setRetPhotoPath(rset.getString(28).toString());
					}
					
					path=(String)rset.getString(29);
					if(path!=null){
						dto.setRetThumbPath(rset.getString(29).toString());
					}
					
					path=(String)rset.getString(30);
					if(path!=null){
						dto.setRetSignPath(rset.getString(30).toString());
					}
					
					path=(String)rset.getString(44);
					if(path!=null){
						dto.setRetDeathCertiPath(rset.getString(44).toString());
					}
					path=(String)rset.getString(49);
					if(path!=null){
						dto.setCompScanPath(rset.getString(49).toString());
					}
					dto.setScanName(rset.getString(48).toString());
					String reltnsp=(String)rset.getString(50);
					if(reltnsp!=null){
						dto.setTestatorRelationship(rset.getString(50).toString());
					}
					
					
				    dto.setWillStatus(rset.getString(33));
				    if(dto.getWillStatus().equalsIgnoreCase("Pending Retrieve")||dto.getWillStatus().equalsIgnoreCase("Retrieved")||dto.getWillStatus().equalsIgnoreCase("RI")){
						
						dto.setWithdrawlDueDate(rset.getString(41)==null ?"":rset.getString(41).toString());
					}
					else {
						dto.setWithdrawlDueDate(rset.getString(31)==null ?"":rset.getString(31).toString());
					}
					dto.setRemarks(rset.getString(32));
					
					dto.setRetDocumentName(rset.getString(34)==null ?"":rset.getString(34).toString());
	                dto.setRetThunmbName(rset.getString(35)==null ?"":rset.getString(35).toString());
	                dto.setRetSignatureName(rset.getString(36)==null ?"":rset.getString(36).toString());
	                dto.setCertificateName(rset.getString(45)==null ?"":rset.getString(45).toString());
	                dto.setDepositRemarks(rset.getString(37)==null ?"":rset.getString(37).toString());
	                dto.setTreasuryId(rset.getString(38)==null ?"":rset.getString(38).toString());
	                dto.setRetrieverBankName(rset.getString(46)==null ?"":rset.getString(46).toString());
					 dto.setRetrieverBankAddress(rset.getString(47)==null ?"":rset.getString(47).toString());
	               
					
				}
					if(agent.equalsIgnoreCase("WithAgent")){

						
						dto.setAgentCategory(rset.getString(2));
						dto.setRetrieverFirstName(rset.getString(7));
						dto.setRetrieverMidName(rset.getString(8));
						dto.setRetrieverLastName(rset.getString(9));

						if (rset.getString(10).equalsIgnoreCase("M")){
							if("en".equalsIgnoreCase(lang)){
							dto.setRetrieverGender("Male");
							}
							else{
								dto.setRetrieverGender("स्त्री");
							}
						}
						else{
							if("en".equalsIgnoreCase(lang)){
							dto.setRetrieverGender("Female");
							}
							else{
								dto.setRetrieverGender("स्त्री");
							}
						}
						if (rset.getString(11) != null)
							dto.setRetrieverAge(rset.getString(11));
						else
							dto.setRetrieverAge("");

						dto.setRetrieverFatherName(rset.getString(12));
						dto.setRetrieverMotherName(rset.getString(13));
						dto.setRetrieverSpouseName(rset.getString(14));
						dto.setRetrieverAddress(rset.getString(15));
						//dto.setRetrieverCountry(rset.getString(17));
						countryName=com.countryName(rset.getString(16).toString(), lang);
						dto.setRetrieverCountry(countryName);
						//dto.setRetrieverState(rset.getString(19));
						stateName=com.stateName(rset.getString(18).toString(), rset.getString(16).toString(), lang);
						dto.setRetrieverState(stateName);
						//dto.setAgentDistrictId(rset.getString(20));
						//dto.setRetrieverCity(rset.getString(21));
						districtName=com.districtName(rset.getString(18).toString(), rset.getString(20).toString(), lang);
						dto.setRetrieverCity(districtName);
						dto.setRetrieverPin(rset.getString(22)==null ?"":rset.getString(22).toString());
						dto.setRetrieverPhone(rset.getString(23)==null ?"":rset.getString(23).toString());
						dto.setRetrieverMPhone(rset.getString(24)==null ?"":rset.getString(24).toString());
						dto.setRetrieverEMail(rset.getString(25));
						//dto.setRetrieverIdProof(rset.getString(26));
						idProofType=com.getIdType((String)rset.getString(26), lang);
						dto.setRetrieverIdProof(idProofType);
						dto.setRetrieverIdProofNo(rset.getString(27));
						
						/*doc = rset.getBlob(28);
						if(doc!=null){
							dto.setRetDocContents(doc.getBytes(1,(int)doc.length()));
							}
					    
					    doc = rset.getBlob(29);
					    if(doc!=null){
					    	dto.setRetThumbContents(doc.getBytes(1,(int)doc.length()));
							}
					    
					    doc = rset.getBlob(30);
					    if(doc!=null){
					    	dto.setRetSignatureContents(doc.getBytes(1,(int)doc.length()));
							}
					    doc = rset.getBlob(44);
					    if(doc!=null){
					    	dto.setDeathCertDocContents(doc.getBytes(1,(int)doc.length()));
							}*/
						
						
						path=(String)rset.getString(28);
						if(path!=null){
							dto.setRetPhotoPath(rset.getString(28).toString());
						}
						
						path=(String)rset.getString(29);
						if(path!=null){
							dto.setRetThumbPath(rset.getString(29).toString());
						}
						
						path=(String)rset.getString(30);
						if(path!=null){
							dto.setRetSignPath(rset.getString(30).toString());
						}
						
						path=(String)rset.getString(44);
						if(path!=null){
							dto.setRetDeathCertiPath(rset.getString(44).toString());
						}
								
						
						
					    dto.setWillStatus(rset.getString(33));
					    if(dto.getWillStatus().equalsIgnoreCase("Pending Retrieve")||dto.getWillStatus().equalsIgnoreCase("Retrieved")||dto.getWillStatus().equalsIgnoreCase("RI")){
							
							dto.setWithdrawlDueDate(rset.getString(41)==null ?"":rset.getString(41).toString());
						}
						else {
							dto.setWithdrawlDueDate(rset.getString(31)==null ?"":rset.getString(31).toString());
						}
						dto.setRemarks(rset.getString(32));
						
						dto.setRetDocumentName(rset.getString(34)==null ?"":rset.getString(34).toString());
		                dto.setRetThunmbName(rset.getString(35)==null ?"":rset.getString(35).toString());
		                dto.setRetSignatureName(rset.getString(36)==null ?"":rset.getString(36).toString());
		                dto.setCertificateName(rset.getString(45)==null ?"":rset.getString(45).toString());
		                dto.setDepositRemarks(rset.getString(37)==null ?"":rset.getString(37).toString());
		                dto.setTreasuryId(rset.getString(38)==null ?"":rset.getString(38).toString());
		                dto.setRetrieverBankName(rset.getString(46)==null ?"":rset.getString(46).toString());
						 dto.setRetrieverBankAddress(rset.getString(47)==null ?"":rset.getString(47).toString());
		               
						
					
					}
					if(agent.equalsIgnoreCase("WithTest")){


						
						dto.setAgentCategory(rset.getString(2));
						dto.setRetrieverFirstName(rset.getString(7));
						dto.setRetrieverMidName(rset.getString(8));
						dto.setRetrieverLastName(rset.getString(9));

						if (rset.getString(10).equalsIgnoreCase("M")){
							if("en".equalsIgnoreCase(lang)){
							dto.setRetrieverGender("Male");
							}
							else{
								dto.setRetrieverGender("पुस्र्ष");
							}
						}
						else{
							if("en".equalsIgnoreCase(lang)){
							dto.setRetrieverGender("Female");
							}
							else{
								dto.setRetrieverGender("स्त्री");
							}
						}
						if (rset.getString(11) != null)
							dto.setRetrieverAge(rset.getString(11));
						else
							dto.setRetrieverAge("");

						dto.setRetrieverFatherName(rset.getString(12));
						dto.setRetrieverMotherName(rset.getString(13));
						dto.setRetrieverSpouseName(rset.getString(14));
						dto.setRetrieverAddress(rset.getString(15));
						//dto.setRetrieverCountry(rset.getString(17));
						countryName = com.countryName(rset.getString(16).toString(), lang);
						dto.setRetrieverCountry(countryName);
						//dto.setRetrieverState(rset.getString(19));
						stateName=com.stateName(rset.getString(19), rset.getString(16).toString(), lang);
						dto.setRetrieverState(stateName);
						//dto.setAgentDistrictId(rset.getString(20));
						//dto.setRetrieverCity(rset.getString(21));
						districtName=com.districtName(rset.getString(19).toString(), rset.getString(21).toString(), lang);
						dto.setRetrieverCity(districtName);
						dto.setRetrieverPin(rset.getString(22)==null ?"":rset.getString(22).toString());
						dto.setRetrieverPhone(rset.getString(23)==null ?"":rset.getString(23).toString());
						dto.setRetrieverMPhone(rset.getString(24)==null ?"":rset.getString(24).toString());
						dto.setRetrieverEMail(rset.getString(25));
						dto.setRetrieverIdProof(rset.getString(26));
						dto.setRetrieverIdProofNo(rset.getString(27));
						
						/*doc = rset.getBlob(28);
						if(doc!=null){
							dto.setRetDocContents(doc.getBytes(1,(int)doc.length()));
							}
					    
					    doc = rset.getBlob(29);
					    if(doc!=null){
					    	dto.setRetThumbContents(doc.getBytes(1,(int)doc.length()));
							}
					    
					    doc = rset.getBlob(30);
					    if(doc!=null){
					    	dto.setRetSignatureContents(doc.getBytes(1,(int)doc.length()));
							}
					    doc = rset.getBlob(44);
					    if(doc!=null){
					    	dto.setDeathCertDocContents(doc.getBytes(1,(int)doc.length()));
							}*/
						
						
						path=(String)rset.getString(28);
						if(path!=null){
							dto.setRetPhotoPath(rset.getString(28).toString());
						}
						
						path=(String)rset.getString(29);
						if(path!=null){
							dto.setRetThumbPath(rset.getString(29).toString());
						}
						
						path=(String)rset.getString(30);
						if(path!=null){
							dto.setRetSignPath(rset.getString(30).toString());
						}
						
						path=(String)rset.getString(44);
						if(path!=null){
							dto.setRetDeathCertiPath(rset.getString(44).toString());
						}
								
						
						
					    dto.setWillStatus(rset.getString(33));
					    if(dto.getWillStatus().equalsIgnoreCase("Pending Retrieve")||dto.getWillStatus().equalsIgnoreCase("Retrieved")||dto.getWillStatus().equalsIgnoreCase("RI")){
							
							dto.setWithdrawlDueDate(rset.getString(41)==null ?"":rset.getString(41).toString());
						}
						else {
							dto.setWithdrawlDueDate(rset.getString(31)==null ?"":rset.getString(31).toString());
						}
						dto.setRemarks(rset.getString(32));
						
						dto.setRetDocumentName(rset.getString(34)==null ?"":rset.getString(34).toString());
		                dto.setRetThunmbName(rset.getString(35)==null ?"":rset.getString(35).toString());
		                dto.setRetSignatureName(rset.getString(36)==null ?"":rset.getString(36).toString());
		                dto.setCertificateName(rset.getString(45)==null ?"":rset.getString(45).toString());
		                dto.setDepositRemarks(rset.getString(37)==null ?"":rset.getString(37).toString());
		                dto.setTreasuryId(rset.getString(38)==null ?"":rset.getString(38).toString());
		                dto.setRetrieverBankName(rset.getString(46)==null ?"":rset.getString(46).toString());
						 dto.setRetrieverBankAddress(rset.getString(47)==null ?"":rset.getString(47).toString());
		               
						
					
					
					}
					
					if (agent.equalsIgnoreCase("Court")) {
						
						dto.setAgentCategory(rset.getString(2));
						dto.setRepFirstName(rset.getString(7)==null ?"":rset.getString(7).toString());
						dto.setRepMiddleName(rset.getString(8)==null ?"":rset.getString(8).toString());
						dto.setRepLastName(rset.getString(9)==null ?"":rset.getString(9).toString());
						dto.setCourtAddress(rset.getString(15));
						dto.setCourtCountry(rset.getString(17));
						dto.setCourtState(rset.getString(19));
						//dto.setAgentDistrictId(rset.getString(20));
						dto.setCourtCity(rset.getString(21));
						dto.setCourtPin(rset.getString(22)==null ?"":rset.getString(22).toString());
						dto.setCourtPhone(rset.getString(23)==null ?"":rset.getString(23).toString());
						//dto.setMphone(rset.getString(24)==null ?"":rset.getString(24).toString());
						//dto.setEmail(rset.getString(25));
						//dto.setIdProof(rset.getString(26));
						//dto.setIdProofNo(rset.getString(27));
						
						/*doc = rset.getBlob(28);
						if(doc!=null){
							dto.setRetDocContents(doc.getBytes(1,(int)doc.length()));
							}
					    
					    doc = rset.getBlob(29);
					    if(doc!=null){
					    	dto.setRetThumbContents(doc.getBytes(1,(int)doc.length()));
							}
					    
					    doc = rset.getBlob(30);
					    if(doc!=null){
					    	dto.setRetSignatureContents(doc.getBytes(1,(int)doc.length()));
							}
					    doc = rset.getBlob(44);
					    if(doc!=null){
					    	dto.setDeathCertDocContents(doc.getBytes(1,(int)doc.length()));
							}*/
						
						path=(String)rset.getString(28);
						if(path!=null){
							dto.setRetPhotoPath(rset.getString(28).toString());
						}
						
						path=(String)rset.getString(29);
						if(path!=null){
							dto.setRetThumbPath(rset.getString(29).toString());
						}
						
						path=(String)rset.getString(30);
						if(path!=null){
							dto.setRetSignPath(rset.getString(30).toString());
						}
						
						path=(String)rset.getString(44);
						if(path!=null){
							dto.setRetDeathCertiPath(rset.getString(44).toString());
						}
						
						path=(String)rset.getString(49);
						if(path!=null){
							dto.setCompScanPath(rset.getString(49).toString());
						}
						dto.setScanName(rset.getString(48).toString());
						
						
						
					    dto.setWillStatus(rset.getString(33));
					    if(dto.getWillStatus().equalsIgnoreCase("Pending Retrieve")||dto.getWillStatus().equalsIgnoreCase("Retrieved")||dto.getWillStatus().equalsIgnoreCase("RI")){
							
							dto.setWithdrawlDueDate(rset.getString(41)==null ?"":rset.getString(41).toString());
						}
						else {
							dto.setWithdrawlDueDate(rset.getString(31)==null ?"":rset.getString(31).toString());
						}
						dto.setRemarks(rset.getString(32));
						
						dto.setRetDocumentName(rset.getString(34)==null ?"":rset.getString(34).toString());
		                dto.setRetThunmbName(rset.getString(35)==null ?"":rset.getString(35).toString());
		                dto.setRetSignatureName(rset.getString(36)==null ?"":rset.getString(36).toString());
		                dto.setCertificateName(rset.getString(45)==null ?"":rset.getString(45).toString());
		                dto.setDepositRemarks(rset.getString(37)==null ?"":rset.getString(37).toString());
		                dto.setTreasuryId(rset.getString(38)==null ?"":rset.getString(38).toString());
		                dto.setCourtName(rset.getString(42)==null ?"":rset.getString(42).toString());
		                dto.setRepDesg(rset.getString(43)==null ?"":rset.getString(43).toString());
						
					}
					
					
				
			}
			
			if(!dto.getWillStatus().equalsIgnoreCase("Deposited")){
			
			String reasonSql = CommonSQL.GET_REASON_TREASURY;
			prepStmt = con.prepareStatement(reasonSql);
			prepStmt.setString(1, willParamId);
			rset = prepStmt.executeQuery();
			while (rset.next()){
			dto.setDepositRemarks(rset.getString(1)==null ?"":rset.getString(1).toString());
			logger.debug("deposit Remarks:    " + dto.getDepositRemarks());
			dto.setTreasuryId(rset.getString(2)==null ?"":rset.getString(2).toString());
			logger.debug("deposit Treasury Id:    " + dto.getTreasuryId());
			}
			}

//		ArrayList list = null;
//		try {
//
//			dbUtil = new DBUtility();
//			dbUtil.createPreparedStatement(sql);
//			logger.debug("SQL:   " + sql);
//
//			String[] param = new String[1];
//			param[0] = willParamId;
//			list = dbUtil.executeQuery(param);
//
		} catch (Exception x) {
			logger.debug("Exception in getWillDeposit() :- " + x);
			x.printStackTrace();
			
		} finally {
			try {
				
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.error("Exception while closing connection() :-" + ex);
			}
		}
		return dto;
	}

	/**
	 * @param willId
	 * @param fromDate
	 * @param toDate
	 * @param status
	 * @return ArrayList
	 */
	public ArrayList getWillViewDetails(String willId, String fromDate,
			String toDate, String status, String districtId) { // VIEW 1
		//TO DO line 412 inserted igrs_will_txn.created_by='"+userId+"
	String sql="SELECT igrs_will_txn.will_txn_id, igrs_will_txn_party.first_name,igrs_will_txn.created_by, igrs_will_txn_party.middle_name, " +
			"igrs_will_txn_party.last_name, igrs_will_txn_party.address, to_char(igrs_will_txn.created_date,'dd/mm/yyyy'), igrs_will_txn.will_status, igrs_will_txn.DEPENDANT_WILL_TXN_ID " +
			"FROM igrs_will_txn LEFT OUTER JOIN igrs_will_txn_party ON (igrs_will_txn.will_txn_id = igrs_will_txn_party.will_txn_id ) " +
			"WHERE(igrs_will_txn.will_status = '"+status+"' AND igrs_will_txn_party.PARTY_TYPE_ID='TESTATOR') AND " +
			"((igrs_will_txn.will_txn_id NOT IN  (SELECT igrs_will_txn.dependant_will_txn_id  FROM igrs_will_txn WHERE " +
			"igrs_will_txn.dependant_will_txn_id IS NOT NULL))) AND decode(igrs_will_txn.will_txn_id,  '"+willId+"', '"+willId+"',   'NA') = nvl('"+willId+"',   'NA')  AND " +
			"to_date(decode(to_char(igrs_will_txn.created_date,   'DD-MM-YY'),   '"+fromDate+"',  '"+fromDate+"',   to_char(igrs_will_txn.created_date,   'DD-MM-YY'),   " +
			"decode('"+fromDate+"',   NULL,   '1/JAN/2010',   to_char(igrs_will_txn.created_date,   'DD-MM-YY'))),   'DD-MM-YY') >= " +
			"to_date(nvl(to_char(to_date('"+fromDate+"',  'DD-MM-YY'),   'DD-MM-YY'),   '1/JAN/2010'),   'DD-MM-YY')  AND " +
			"to_date(decode(to_char(igrs_will_txn.created_date,   'DD-MM-YY'), '"+toDate+"', '"+toDate+"',   to_char(igrs_will_txn.created_date,   'DD-MM-YY'),  " +
			" decode('"+toDate+"',   NULL,   '1/JAN/2010',   to_char(igrs_will_txn.created_date,   'DD-MM-YY'))),   'DD-MM-YY') <= " +
			"to_date(nvl(to_char(to_date('"+toDate+"',   'DD-MM-YY'),   'DD-MM-YY'),   '1/JAN/2010'),   'DD-MM-YY') and substr(igrs_will_txn.will_txn_id,3,2) = '"+districtId+"' order by igrs_will_txn.created_date desc" ;
		logger.debug("Sql for will deposit..." + sql);
		DBUtility dbUtil=null;
		ArrayList list = null;
		try {dbUtil = new DBUtility();
			dbUtil.createStatement();
			list = dbUtil.executeQuery(sql);
			logger.debug("WillViewDAO:: getWillViewDetails():: AFTER" + sql);

		} catch (Exception x) {
			x.getCause();
		}finally{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public ArrayList getUpdateWillViewDetails(String willId, String fromDate,
			String toDate, String status, String districtId) { // VIEW 1
		
	String sql="SELECT igrs_will_txn.will_txn_id,igrs_will_txn.created_by, igrs_will_txn_party.first_name, igrs_will_txn_party.middle_name, " +
			"igrs_will_txn_party.last_name, igrs_will_txn_party.address, to_char(igrs_will_txn.created_date,'dd/mm/yyyy'), igrs_will_txn.will_status " +
			"FROM igrs_will_txn LEFT OUTER JOIN igrs_will_txn_party ON (igrs_will_txn.will_txn_id = igrs_will_txn_party.will_txn_id) " +
			"WHERE(igrs_will_txn.will_status = '"+status+"' AND igrs_will_txn_party.PARTY_TYPE_ID='TESTATOR') AND decode(igrs_will_txn.will_txn_id,  '"+willId+"', '"+willId+"',   'NA') = nvl('"+willId+"',   'NA')  AND " +
			"to_date(decode(to_char(igrs_will_txn.created_date,   'DD-MM-YY'),   '"+fromDate+"',  '"+fromDate+"',   to_char(igrs_will_txn.created_date,   'DD-MM-YY'),   " +
			"decode('"+fromDate+"',   NULL,   '1/JAN/2010',   to_char(igrs_will_txn.created_date,   'DD-MM-YY'))),   'DD-MM-YY') >= " +
			"to_date(nvl(to_char(to_date('"+fromDate+"',  'DD-MM-YY'),   'DD-MM-YY'),   '1/JAN/2010'),   'DD-MM-YY')  AND " +
			"to_date(decode(to_char(igrs_will_txn.created_date,   'DD-MM-YY'), '"+toDate+"', '"+toDate+"',   to_char(igrs_will_txn.created_date,   'DD-MM-YY'),  " +
			" decode('"+toDate+"',   NULL,   '1/JAN/2010',   to_char(igrs_will_txn.created_date,   'DD-MM-YY'))),   'DD-MM-YY') <= " +
			"to_date(nvl(to_char(to_date('"+toDate+"',   'DD-MM-YY'),   'DD-MM-YY'),   '1/JAN/2010'),   'DD-MM-YY')and substr(igrs_will_txn.will_txn_id,3,2) = '"+districtId+"' order by igrs_will_txn.created_date desc" ;
		logger.debug("Sql for will deposit..." + sql);
		DBUtility dbUtil=null;
		ArrayList list = null;
		try {dbUtil = new DBUtility();
			dbUtil.createStatement();
			list = dbUtil.executeQuery(sql);
			logger.debug("WillViewDAO:: getWillViewDetails():: AFTER" + sql);

		} catch (Exception x) {
			x.getCause();
		}finally{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	public String getDistrictId(String officeId){
		ArrayList alist = new ArrayList();
		String districtId="";DBUtility dbUtil=null;
		String sql="SELECT DISTRICT_ID FROM IGRS_OFFICE_MASTER WHERE OFFICE_ID='"+officeId+"'";
		try{dbUtil = new DBUtility();
			dbUtil.createStatement();
			alist=dbUtil.executeQuery(sql);
			
			ArrayList rowList= (ArrayList)alist.get(0);
			
			districtId=(String)rowList.get(0);
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return districtId;
	}
	
	public String getName(String userId){
		String name="";
		ArrayList alist= new ArrayList();
		String param[]=new String[1];
		param[0]=userId;
		String sql=CommonSQL.GET_NAME;DBUtility dbUtil=null;
		try{dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(sql);
			alist=dbUtil.executeQuery(param);
			
			ArrayList rowList= (ArrayList)alist.get(0);
			
			name=(String)rowList.get(0)+" "+(String)rowList.get(1);
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return name;
	}
	

	//to get country name based on language, start
	public ArrayList getCountry(String countryId, String language) throws Exception {
		WillDepositDAO dao = new WillDepositDAO();
		ArrayList ret = dao.getCountry();
		ArrayList list = new ArrayList();
String countryName="";
		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				
				if("hi".equalsIgnoreCase(language)){
					countryName=(String) lst.get(2);
				}
				else{
					countryName=(String) lst.get(1);
				}
				//dto.setCountry((String) lst.get(1));
				
			}
		}
		return list;
	}
	//to get country name based on language, end
	
	//to get state name based on the language, start
	public String getState(String stateId, String lang) throws Exception {
		WillDepositDAO dao = new WillDepositDAO();
		ArrayList ret = dao.getState(stateId);
		ArrayList list = new ArrayList();
		String stateName="";

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				
				
				if("hi".equalsIgnoreCase(lang)){
					stateName=(String) lst.get(2);
				}
				else{
					stateName=(String) lst.get(1);
				}
				//dto.setState((String) lst.get(1));
				
			}
		}
		return stateName;
	}
//to get state name based on the language, end




}



