/*
* Copyright Notice
*==============================================================================
* This file contains proprietary information of IGRS Bhopal Madhya Pradesh .
* Copying or reproduction without prior written approval is prohibited.
* Copyright (c) 2007-08
*==============================================================================
*
* File Name   :  DutyCalculationAction.java 
 * Author      :  Madan Mohan 
 * Description :   14 jan 2008
 * ----------------------------------------------------------------------------
* Version         Modified By     Modified On           Modification
* ----------------------------------------------------------------------------
* 1.0             Lavi Tripathi        05 jun 2013       changes as per new SRS requirements       
 * --------------------------------------------------------------------------------
*/


package com.wipro.igrs.bankingestamp.dao;


import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;

import com.wipro.igrs.RegCompMaker.sql.RegCommonMkrSQL;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.estamping.dto.DashBoardDTO;
import com.wipro.igrs.estamping.dto.EstampDTO;
import com.wipro.igrs.estamping.form.DutyCalculationForm;
import com.wipro.igrs.estamping.sql.CommonSQL;
import com.wipro.igrs.newreginit.bd.RegCommonBD;
import com.wipro.igrs.newreginit.bo.RegCommonBO;
import com.wipro.igrs.newreginit.constant.RegInitConstant;
import com.wipro.igrs.newreginit.dto.RegCommonDTO;
import com.wipro.igrs.newreginit.form.RegCommonForm;
import com.wipro.igrs.newreginit.sql.RegCommonSQL;
import com.wipro.igrs.propertyvaluation.dto.DutyCalculationDTO;


/**
 * @since 14 jan 2008 File Name: DutyCalculationDAO.java
 * @author Madan Mohan
 * @version 1.0 Created on 14 jan 2008
 * 
 */
public class DutyCalculationDAO {

	DBUtility dbUtility = null;
	Connection connTest;
	String sql = null;
	DutyCalculationDTO dto = null;
	ArrayList mainList = null;
	//IGRSCommon igrsCommon = null;
	PreparedStatement pst = null;
	private  Logger logger = 
		(Logger) Logger.getLogger(DBUtility.class);
	private CallableStatement clstmt;
	public DutyCalculationDAO() {
		
		try {
			//igrsCommon = new IGRSCommon();
		} catch (Exception x) {
			logger.debug(x);
		}
	}
	
	
	
	
	
	/******************************************************************  
	  *   Method Name  :   insertTxn()
	  *   Arguments    :   Form 
	  *   Return       :   Boolean
	  *   Throws 	  :   NullPointer  or SQLException or Exception
 *******************************************************************/  
 
 public  boolean insertTxn (DutyCalculationForm eform, String lang)throws NullPointerException,
 SQLException,Exception{
	 
  boolean transactionflag = false;
  String estmTxnId = null;
  String txntable[] = new String[9];
  String dutyTbl [] = new String[12];
 // String docTbl[] =   new String[6];
  String appdtl[] =   new String[27];
  String partydtl[] = new String[27];
  String exempdtl[] = new String[4];
  
  
  
  String aptype =     eform.getAppType();
  String apfname =    eform.getAppFirsName();
  String apmdname=    eform.getAppMiddleName();
  String aplname=     eform.getAppLastName();
  String apgender =   eform.getAppGender();
  String appdy =      eform.getAppDay();
  String apmnth =     eform.getAppMonth();
  String apyr =       eform.getAppYear();
  String apfthrname = eform.getAppFatherName();
  String apmthrname=  eform.getAppMotherName();
  String apadrs =     eform.getAppAddress();
  String apcntry=     eform.getAppCountry();
  String apstate =    eform.getAppState();
  String apdstrct =   eform.getAppDistrict();
  String appostlcd =  eform.getAppPostalCode();
  String apmob =      eform.getAppMobno();
  String apphn =      eform.getAppPhno();
  String apemail =    eform.getAppEmailID();
  String apphotoId =  eform.getAppPhotoId();
  String apphotoidno= eform.getAppPhotoIdno();
  String appersns=    eform.getAppPersons();
  String aporgname=   eform.getAppOrgName();
  String apauthfname= eform.getAppAuthFirstName();
  String apauthmdname = eform.getAppAuthMiddleName();
  String apauthlname= eform.getAppAuthLastName();
  String apbnknme =   eform.getAppBankName();
  String apbnmadrs =  eform.getAppBankAddress();
  String deedDuratn = eform.getDeedDuration();
  
  
  String prtytype =     eform.getPartyType();
  String prtyfname =    eform.getPartyFirsName();
  String prtymdname=    eform.getPartyMiddleName();
  String prtylname=     eform.getPartyLastName();
  String prtygender =   eform.getPartyGender();
  String prtydy =       eform.getPartyDay();
  String prtymnth =     eform.getPartyMonth();
  String prtyyr =       eform.getPartyYear();
  String prtyfthrname = eform.getPartyFatherName();
  String prtymthrname=  eform.getPartyMotherName();
  String prtyadrs =     eform.getPartyAddress();
  String prtycntry=     eform.getPartyCountry();
  String prtystate =    eform.getPartyState();
  String prtydstrct =   eform.getPartyDistrict();
  String prtypostlcd =  eform.getPartyPostalCode();
  String prtymob =      eform.getPartyMobno();
  String prtyphn =      eform.getPartyPhno();
  String prtyemail =    eform.getPartyEmailID();
  String prtyphotoId =  eform.getPartyPhotoId();
  String prtyphotoidno= eform.getPartyPhotoIdno();
  String prtypersns=    eform.getPartyPersons();
  String prtyorgname=   eform.getPartyOrgName();
  String prtyauthfname= eform.getPartyAuthFirstName();
  String prtyauthmdname = eform.getPartyAuthMiddleName();
  String prtyauthlname= eform.getPartyAuthLastName();
  String prtybnknme =   eform.getPartyBankName();
  String prtybnmadrs =  eform.getPartyBankAddress();
  
  
  String atype =     "2";
  String afname =    eform.getAdoptFirstName();
  String amdname=    eform.getAdoptMiddleName();
  String alname=     eform.getAdoptLastName();
  String agender =   eform.getAdoptGender();
  String ady =       eform.getAdoptDay();
  String amnth =     eform.getAdoptMonth();
  String ayr =       eform.getAdoptYear();
  String afthrname = eform.getAdoptFatherName();
  String amthrname=  eform.getAdoptMotherName();
  String aadrs =     eform.getAdoptAddress();
  String acntry=     eform.getAdoptCountry();
  String astate =    eform.getAdoptState();
  String adstrct =   eform.getAdoptDistrict();
  String apostlcd =  eform.getAdoptPostalcode();
  String amob =      eform.getAdoptMoblie();
  String aphn =      eform.getAdoptPhone();
  String aemail =    eform.getAdoptEmail();
  String aphotoId =  eform.getAdoptPhotoId();
  String aphotoidno= eform.getAdoptPhotoIdNumber();
  String apersns=    eform.getAdoptNoPerson();
  String aorgname=  	 "";
  String aauthfname=	"";
  String aauthmdname = "";
  String aauthlname= "";
  String abnknme =   "";
  String abnmadrs =  "";
  
  
  
  if(apgender.equalsIgnoreCase("M"))
  {
  	if(lang.equalsIgnoreCase("en"))
  		eform.setAppGenderDisp("Male");
  	else
  		eform.setAppGenderDisp("पुस्र्ष");
  }
  else
  {
  	if(lang.equalsIgnoreCase("en"))
  		eform.setAppGenderDisp("Female");
  	else
  		eform.setAppGenderDisp("महिला");
  }


  if(prtygender.equalsIgnoreCase("M"))
  {
  	if(lang.equalsIgnoreCase("en"))
  		eform.setPartyGenderDisp("Male");
  	else
  		eform.setPartyGenderDisp("पुस्र्ष");
  }
  else
  {
  	if(lang.equalsIgnoreCase("en"))
  		eform.setPartyGenderDisp("Female");
  	else
  		eform.setPartyGenderDisp("महिला");
  }


  //***********END**********************
   
  String estmpurpose = eform.getEstampPurpose();
  String uid = eform.getUid();
  //String docname=eform.getDoc();
  //String docPath = eform.getDocPath();
  //docname = eform.getDocname();
  

  int deed = eform.getDutyCalculationDTO().getDeedId();
  int inst = eform.getInstDTO().getInstId();
  String exemp = eform.getExempDTO().getHdnExAmount();
  
  Date date = new Date();
  Format yearformat  = new SimpleDateFormat("yy");
  Format monthformat = new SimpleDateFormat("MM");
  Format dateformat  = new SimpleDateFormat("dd");
  String dfmt = dateformat.format(date);
  String yfmt = yearformat.format(date);
  String mfmt = monthformat.format(date);
  DBUtility transmgtUtil = null;
  try{
  transmgtUtil = new DBUtility();
  transmgtUtil.setAutoCommit(false);
  
 //for  txn table and estamp_txn_id
  /*String SQL1 = "SELECT COUNT(ESTAMP_TXN_ID) FROM IGRS_ESTAMP_TXN_DETLS WHERE TRUNC(CREATED_DATE)= TRUNC(SYSDATE)";

 // select count1
*/  //logger.debug(SQL1);
  transmgtUtil.createStatement();
  String number1 = transmgtUtil.executeQry(CommonSQL.SELECT_COUNT1);
  if (number1.equalsIgnoreCase("0")){
		logger.debug("in if clause");
		/*String drpqry = "DROP SEQUENCE IGRS_ESTAMP_TXN_ID_SEQ";	

		//dropsqnc estamp
*/		transmgtUtil.createStatement();
		transmgtUtil.executeUpdate(CommonSQL.DROP_SQNC1);
		//transmgtUtil.executeQry(drpqry);
		/*String crtqry = "CREATE SEQUENCE IGRS_ESTAMP_TXN_ID_SEQ START WITH 1 INCREMENT BY 1 MAXVALUE 999999 MINVALUE 1 NOCYCLE  NOCACHE ORDER";

		//createsqnc1
*/		transmgtUtil.createStatement();
		transmgtUtil.executeUpdate(CommonSQL.CREATE_STMNT1);
	}
	 /* String SQL2 = "select IGRS_ESTAMP_TXN_ID_SEQ.nextval from dual";

	  //selectsqnce1stampid
*/	  transmgtUtil.createStatement();
	  String number2 = transmgtUtil.executeQry(CommonSQL.SELECT_SEQUENCE1);
	  if(number2.length()==1){
		  number2="00000"+number2;  
		  }else
		  if(number2.length()==2){
			  number2="0000"+number2;  
		  }else
		  if(number2.length()==3){
			  number2="000"+number2;  
		  }else
		  if(number2.length()==4){
			  number2="00"+number2;  
		  }else
		  if(number2.length()==5){
			  number2="0"+number2;  
		  }
	  estmTxnId = dfmt+mfmt+yfmt+number2;
	  txntable[0] = estmTxnId;
	  txntable[1] = "1";
	  txntable[2] = "1";
	  txntable[3] = Integer.toString(deed);
	  txntable[4] = Integer.toString(inst);
	  txntable[5] = uid;
	  txntable[6] = estmpurpose;
	  txntable[7] = eform.getDutyCalculationDTO().getMainDutyTxnId();
	  txntable[8] = eform.getDeedDraftId();
	 
	  if(deed==0 || inst==0){
		  txntable[6]="DEED ID-INST ID 0";
		  transmgtUtil.createPreparedStatement(CommonSQL.TXN_TABLE_INSERT_LOG);
		  transmgtUtil.executeUpdate(txntable);
		  transmgtUtil.commit();
		  throw new Exception("10001");
	  }
	  if(apcntry==null||apstate==null){
		  txntable[6]="APP COUNTRY-STATE NULL";
		  transmgtUtil.createPreparedStatement(CommonSQL.TXN_TABLE_INSERT_LOG);
		  transmgtUtil.executeUpdate(txntable);
		  transmgtUtil.commit();
		  throw new Exception("10001");
	  }
	  if(prtycntry==null || prtystate==null){
		  txntable[6]="PARTY COUNTRY-STATE NULL";
		  transmgtUtil.createPreparedStatement(CommonSQL.TXN_TABLE_INSERT_LOG);
		  transmgtUtil.executeUpdate(txntable);
		  transmgtUtil.commit();
		  throw new Exception("10001");
	  }
	  if(eform.getDeedDraftId()==null){
		  txntable[6]="DEED DRAFT ID NULL";
		  transmgtUtil.createPreparedStatement(CommonSQL.TXN_TABLE_INSERT_LOG);
		  transmgtUtil.executeUpdate(txntable);
		  transmgtUtil.commit();
		  throw new Exception("10001");
	  }
	  //for duty table
	 /* String SQL3 = "select IGRS_ESTAMP_DUTY_ID_SEQ.nextval from dual";

	  //selectduty seqnc2
*/	  transmgtUtil.createStatement();
	  String number3 = transmgtUtil.executeQry(CommonSQL.SELECT_SEQUENCE2);
	  dutyTbl[0]= number3;
	  dutyTbl[1]= estmTxnId;
	  dutyTbl[2]= Double.toString(eform.getDutyCalculationDTO().getStampDuty());
	  dutyTbl[3]= Double.toString(eform.getDutyCalculationDTO().getPanchayatDuty());
	  dutyTbl[4]= Double.toString(eform.getDutyCalculationDTO().getNagarPalikaDuty());
	  dutyTbl[5]= Double.toString(eform.getDutyCalculationDTO().getUpkarDuty());
	  dutyTbl[6]= Double.toString(eform.getDutyCalculationDTO().getTotal());
	  dutyTbl[7]= eform.getDutyCalculationDTO().getBaseValue(); // modified by SIMRAN
	  dutyTbl[8]= Double.toString(eform.getDutyCalculationDTO().getDutyAlreadyPaid());
	  dutyTbl[9]= Double.toString(eform.getDutyCalculationDTO().getRegPaid());
	  dutyTbl[10]= Double.toString(eform.getDutyCalculationDTO().getAnnualRent());
	  dutyTbl[11]= uid;
	  
	 /* //for document table
	  String SQL4 = "select IGRS_ESTAMP_DOC_ID_SEQ.nextval from dual";
	  transmgtUtil.createStatement();
	  String number4 = transmgtUtil.executeQry(SQL4);
	  docTbl[0]=number4;
	  docTbl[1]=estmTxnId;
	  docTbl[2]=docname;
	  docTbl[3]="A";
	  docTbl[4]=uid;
	  docTbl[5]=docPath;*/
	  
	  //for txn_party table-applicant
	 /* String SQL5 = "select IGRS_ESTAMP_PARTY_ID_SEQ.nextval from dual";

	  //selectpartyid3
*/	  transmgtUtil.createStatement();
	  String number5 = transmgtUtil.executeQry(CommonSQL.SELECT_SEQUENCE3);
	  
	  appdtl[0]= number5;
	  appdtl[1]= estmTxnId;
	  appdtl[2]= aptype;
	  appdtl[3]= "Y";
	  if(aptype.equalsIgnoreCase("1")){
	  appdtl[4]= apauthfname;
	  appdtl[5]= apauthmdname;
	  appdtl[6]= apauthlname;
	  appdtl[7]= "";
	  appdtl[8]= "";
	  }else {
		  appdtl[4]= apfname;
		  appdtl[5]= apmdname;
		  appdtl[6]= aplname;
		  appdtl[8]= appdy +"-"+apmnth+"-"+apyr ;
		  appdtl[7]= apgender;
		  //SIMRAN
		  //if(lang.equalsIgnoreCase("hi"))
		  //{
			  String mnthDisp = "";
			  if(apmnth.equalsIgnoreCase("JAN"))
				  mnthDisp = "01";
			  else if(apmnth.equalsIgnoreCase("FEB"))
				  mnthDisp = "02";
			  else if(apmnth.equalsIgnoreCase("MAR"))
				  mnthDisp = "03";
			  else if(apmnth.equalsIgnoreCase("APR"))
				  mnthDisp = "04";
			  else if(apmnth.equalsIgnoreCase("MAY"))
				  mnthDisp = "05";
			  else if(apmnth.equalsIgnoreCase("JUN"))
				  mnthDisp = "06";
			  else if(apmnth.equalsIgnoreCase("JUL"))
				  mnthDisp = "07";
			  else if(apmnth.equalsIgnoreCase("AUG"))
				  mnthDisp = "08";
			  else if(apmnth.equalsIgnoreCase("SEP"))
				  mnthDisp = "09";
			  else if(apmnth.equalsIgnoreCase("OCT"))
				  mnthDisp = "10";
			  else if(apmnth.equalsIgnoreCase("NOV"))
				  mnthDisp = "11";
			  else if(apmnth.equalsIgnoreCase("DEC"))
				  mnthDisp = "12";
			  
			  eform.setAppAge(appdy +"/"+mnthDisp+"/"+apyr);
		  //}
		  //else
		  //{
			//  eform.setAppAge(appdtl[8]);
		  //}
		  			  
		 // eform.setAppAge(appdtl[8]);
	  }
	  
	  
	  appdtl[9]= apcntry;
	  appdtl[10]= apstate ;
	  appdtl[11]= apdstrct ;
	  appdtl[12]= apadrs ;
	  appdtl[13]= appostlcd ;
	  appdtl[14]= apphn ;
	  appdtl[15]= apmob ;
	  appdtl[16]= apemail ;
	  appdtl[17]= apphotoId ;
	  appdtl[18]= apphotoidno ;
	  appdtl[19]= apbnknme  ;
	  appdtl[20]= apbnmadrs  ;
	  appdtl[21]= aporgname ;
	  appdtl[22]= apfthrname  ;
	  appdtl[23]= apmthrname ;
	  appdtl[24]= appersns  ;
	  appdtl[25]= uid ;
	  //Added by Lavi for capturing deed duration
	  appdtl[26]= deedDuratn ;
	 
	//for txn_party table-party
	 /* String SQL6 = "select IGRS_ESTAMP_PARTY_ID_SEQ.nextval from dual";

	  //selectsqnc4
*/	  transmgtUtil.createStatement();
	  String number6 = transmgtUtil.executeQry(CommonSQL.SELECT_SEQUENCE4);
	  partydtl[0]= number6;
	  partydtl[1]= estmTxnId;
	  partydtl[2]= prtytype ;
	  partydtl[3]= "N";
	  if(prtytype.equalsIgnoreCase("1")){
		  partydtl[4]= prtyauthfname;
		  partydtl[5]= prtyauthmdname;
		  partydtl[6]= prtyauthlname;
		  partydtl[7]= "";
		  partydtl[8]= "";
		 
	  }else {
		  partydtl[4]= prtyfname;
		  partydtl[5]= prtymdname;
		  partydtl[6]= prtylname;
		  partydtl[7]= prtygender;
		  partydtl[8]= prtydy +"-"+prtymnth+"-"+prtyyr ;
		//SIMRAN
		  //if(lang.equalsIgnoreCase("hi"))
		  //{
			  String mnthDisp = "";
			  if(prtymnth.equalsIgnoreCase("JAN"))
				  mnthDisp = "01";
			  else if(prtymnth.equalsIgnoreCase("FEB"))
				  mnthDisp = "02";
			  else if(prtymnth.equalsIgnoreCase("MAR"))
				  mnthDisp = "03";
			  else if(prtymnth.equalsIgnoreCase("APR"))
				  mnthDisp = "04";
			  else if(prtymnth.equalsIgnoreCase("MAY"))
				  mnthDisp = "05";
			  else if(prtymnth.equalsIgnoreCase("JUN"))
				  mnthDisp = "06";
			  else if(prtymnth.equalsIgnoreCase("JUL"))
				  mnthDisp = "07";
			  else if(prtymnth.equalsIgnoreCase("AUG"))
				  mnthDisp = "08";
			  else if(prtymnth.equalsIgnoreCase("SEP"))
				  mnthDisp = "09";
			  else if(prtymnth.equalsIgnoreCase("OCT"))
				  mnthDisp = "10";
			  else if(prtymnth.equalsIgnoreCase("NOV"))
				  mnthDisp = "11";
			  else if(prtymnth.equalsIgnoreCase("DEC"))
				  mnthDisp = "12";
			  
		  eform.setPartyAge(prtydy +"/"+mnthDisp+"/"+prtyyr);
		//  }
		  //else
		  //{
			//  eform.setPartyAge( partydtl[8]);
		  //}
		  //eform.setPartyAge( partydtl[8]);
	  }
	 
	 
	  partydtl[9]= prtycntry;
	  partydtl[10]= prtystate ;
	  partydtl[11]= prtydstrct ;
	  partydtl[12]= prtyadrs ;
	  partydtl[13]= prtypostlcd ;
	  partydtl[14]= prtyphn ;
	  partydtl[15]= prtymob ;
	  partydtl[16]= prtyemail ;
	  partydtl[17]= prtyphotoId ;
	  partydtl[18]= prtyphotoidno ;
	  partydtl[19]= prtybnknme  ;
	  partydtl[20]= prtybnmadrs  ;
	  partydtl[21]= prtyorgname ;
	  partydtl[22]= prtyfthrname  ;
	  partydtl[23]= prtymthrname ;
	  partydtl[24]= prtypersns  ;
	  partydtl[25]= uid ;
	  partydtl[26]= "";
	  //7: DUTY, 8:DEEDDRAFT
	 if(!checkEstampValidity2(txntable[7],txntable[8])){
		 txntable[6]="MUTIPLE-ESTAMP";
		  transmgtUtil.createPreparedStatement(CommonSQL.TXN_TABLE_INSERT_LOG);
		  transmgtUtil.executeUpdate(txntable);
		  transmgtUtil.commit();
		  throw new Exception("10001");
	 }
	  transmgtUtil.createPreparedStatement(CommonSQL.TXN_TABLE_INSERT);
	  transmgtUtil.executeUpdate(txntable);
	  
	  
	  /*if(!exemp.equalsIgnoreCase("")){
		String exemptions[]=exemp.split(",");
		for(int i=0;i<exemptions.length;i++){
			 String SQL7 = "select IGRS_ESTAMP_EXEMP_ID_SEQ.nextval from dual";

			 //select exempsqnc5
			  transmgtUtil.createStatement();
			  String number7 = transmgtUtil.executeQry(CommonSQL.SELECT_SEQUENCE5);
			  exempdtl[0]= number7;
			  exempdtl[1]= estmTxnId;
			  exempdtl[2]=exemptions[i];
			  exempdtl[3]=uid;
			  transmgtUtil.createPreparedStatement(CommonSQL.EXEMP_TABLE_INSERT);
			  transmgtUtil.executeUpdate(exempdtl);
		}
		}
*/	  
	  
	 // transmgtUtil.createPreparedStatement(CommonSQL.DUTY_TABLE_INSERT);
	 // transmgtUtil.executeUpdate(dutyTbl);
      transmgtUtil.createPreparedStatement(CommonSQL.PARTY_TABLE_INSERT);
	  transmgtUtil.executeUpdate(appdtl); 
	  if (!prtytype.equalsIgnoreCase("")){
	  transmgtUtil.createPreparedStatement(CommonSQL.PARTY_TABLE_INSERT);
	  transmgtUtil.executeUpdate(partydtl); 
	  }
	  
	  
	  if(deed==1003)
	  {

		  transmgtUtil.createStatement();
		  String number9 = transmgtUtil.executeQry(CommonSQL.SELECT_SEQUENCE4);
		  partydtl[0]= number9;
		  partydtl[1]= estmTxnId;
		  partydtl[2]= atype ;
		  partydtl[3]= "A";
		  if(atype.equalsIgnoreCase("1")){
			  partydtl[4]= aauthfname;
			  partydtl[5]= aauthmdname;
			  partydtl[6]= aauthlname;
			  partydtl[7]= "";
			  partydtl[8]= "";
			 
		  }else {
			  partydtl[4]= afname;
			  partydtl[5]= amdname;
			  partydtl[6]= alname;
			  partydtl[7]= agender;
			  partydtl[8]= ady +"-"+amnth+"-"+ayr ;
			//SIMRAN
			  //if(lang.equalsIgnoreCase("hi"))
			  //{
				  String mnthDisp = "";
				  if(amnth.equalsIgnoreCase("JAN"))
					  mnthDisp = "01";
				  else if(amnth.equalsIgnoreCase("FEB"))
					  mnthDisp = "02";
				  else if(amnth.equalsIgnoreCase("MAR"))
					  mnthDisp = "03";
				  else if(amnth.equalsIgnoreCase("APR"))
					  mnthDisp = "04";
				  else if(amnth.equalsIgnoreCase("MAY"))
					  mnthDisp = "05";
				  else if(amnth.equalsIgnoreCase("JUN"))
					  mnthDisp = "06";
				  else if(amnth.equalsIgnoreCase("JUL"))
					  mnthDisp = "07";
				  else if(amnth.equalsIgnoreCase("AUG"))
					  mnthDisp = "08";
				  else if(amnth.equalsIgnoreCase("SEP"))
					  mnthDisp = "09";
				  else if(amnth.equalsIgnoreCase("OCT"))
					  mnthDisp = "10";
				  else if(amnth.equalsIgnoreCase("NOV"))
					  mnthDisp = "11";
				  else if(amnth.equalsIgnoreCase("DEC"))
					  mnthDisp = "12";
				  
			  eform.setAdoptAge(ady +"/"+mnthDisp+"/"+ayr);
			//  }
			  //else
			  //{
				//  eform.setPartyAge( partydtl[8]);
			  //}
			  //eform.setPartyAge( partydtl[8]);
		  }
		 
		 if("M".equalsIgnoreCase(agender))
		 {
			if("en".equalsIgnoreCase(lang))
			{
				eform.setAdoptGenderDisplay("Male");
			}
			else
			{
				eform.setAdoptGenderDisplay("पुस्र्ष");

			}
		 }
		 else if("F".equalsIgnoreCase(agender))
		 {
			 if("en".equalsIgnoreCase(lang))
				{
					eform.setAdoptGenderDisplay("Female");

				}
				else
				{
					eform.setAdoptGenderDisplay("महिला");

				} 
		 }
		  partydtl[9]= acntry;
		  partydtl[10]= astate ;
		  partydtl[11]= adstrct ;
		  partydtl[12]= aadrs ;
		  partydtl[13]= apostlcd ;
		  partydtl[14]= aphn ;
		  partydtl[15]= amob ;
		  partydtl[16]= aemail ;
		  partydtl[17]= aphotoId ;
		  partydtl[18]= aphotoidno ;
		  partydtl[19]= abnknme  ;
		  partydtl[20]= abnmadrs  ;
		  partydtl[21]= aorgname ;
		  partydtl[22]= afthrname  ;
		  partydtl[23]= amthrname ;
		  partydtl[24]= apersns  ;
		  partydtl[25]= uid ;
		  partydtl[26]= "";
		  if(acntry==null || astate==null){
			  throw new Exception("10002");
		  }
		  transmgtUtil.createPreparedStatement(CommonSQL.PARTY_TABLE_INSERT);
		  transmgtUtil.executeUpdate(partydtl); 
	  }
	  
	  /*if (!docname.equalsIgnoreCase("")){
	  transmgtUtil.createPreparedStatement(CommonSQL.DOCUMENT_TABLE_INSERT);
	  transmgtUtil.executeUpdate(docTbl); 
	  }*/
	  transactionflag = true;
	  transmgtUtil.commit();				     
	
 }catch (NullPointerException ne) {
	    ne.printStackTrace();
	   transmgtUtil.rollback();
	   throw ne;
		
   }
	    catch (SQLException se) {
	    	 se.printStackTrace();
	    	transmgtUtil.rollback();
	    	
		logger.error("SQL Exception at estamp  in DAO " + se); 
	 throw se;
	}
   catch(Exception e){
	   e.printStackTrace();
   	transmgtUtil.rollback();
	logger.error(" Exception at estamp in DAO " + e);
	throw e;
	}
   
   finally {
		 try{	    
			 transmgtUtil.closeConnection();
		 }
		 catch (Exception e) {
		 logger.error("Exception at estamp in DAO  " + e);
		 }		
       }
   eform.setMainTxnId(estmTxnId);
   return transactionflag;   
 }

  
	 
 
 
 
 public  boolean updateTxn (DutyCalculationForm eform, String lang)throws NullPointerException,
 SQLException,Exception{
	 
  boolean transactionflag = false;
  
  String txntable[] = new String[3];
  String docTbl[] =   new String[4];
  String appdtl[] =   new String[26];
  String partydtl[] = new String[26];
 
  
  
  String estmTxnId = eform.getMainTxnId();
  String aptype =     eform.getAppType();
  String apfname =    eform.getAppFirsName();
  String apmdname=    eform.getAppMiddleName();
  String aplname=     eform.getAppLastName();
  String apgender =   eform.getAppGender();
  String appdy =      eform.getAppDay();
  String apmnth =     eform.getAppMonth();
  String apyr =       eform.getAppYear();
  String apfthrname = eform.getAppFatherName();
  String apmthrname=  eform.getAppMotherName();
  String apadrs =     eform.getAppAddress();
  String apcntry=     eform.getAppCountry();
  String apstate =    eform.getAppState();
  String apdstrct =   eform.getAppDistrict();
  String appostlcd =  eform.getAppPostalCode();
  String apmob =      eform.getAppMobno();
  String apphn =      eform.getAppPhno();
  String apemail =    eform.getAppEmailID();
  String apphotoId =  eform.getAppPhotoId();
  String apphotoidno= eform.getAppPhotoIdno();
  String appersns=    eform.getAppPersons();
  String aporgname=   eform.getAppOrgName();
  String apauthfname= eform.getAppAuthFirstName();
  String apauthmdname = eform.getAppAuthMiddleName();
  String apauthlname= eform.getAppAuthLastName();
  String apbnknme =   eform.getAppBankName();
  String apbnmadrs =  eform.getAppBankAddress();
  String deedDuratn = eform.getDeedDuration();
  
  
  
  String prtytype =     eform.getPartyType();
  String prtyfname =    eform.getPartyFirsName();
  String prtymdname=    eform.getPartyMiddleName();
  String prtylname=     eform.getPartyLastName();
  String prtygender =   eform.getPartyGender();
  String prtydy =       eform.getPartyDay();
  String prtymnth =     eform.getPartyMonth();
  String prtyyr =       eform.getPartyYear();
  String prtyfthrname = eform.getPartyFatherName();
  String prtymthrname=  eform.getPartyMotherName();
  String prtyadrs =     eform.getPartyAddress();
  String prtycntry=     eform.getPartyCountry();
  String prtystate =    eform.getPartyState();
  String prtydstrct =   eform.getPartyDistrict();
  String prtypostlcd =  eform.getPartyPostalCode();
  String prtymob =      eform.getPartyMobno();
  String prtyphn =      eform.getPartyPhno();
  String prtyemail =    eform.getPartyEmailID();
  String prtyphotoId =  eform.getPartyPhotoId();
  String prtyphotoidno= eform.getPartyPhotoIdno();
  String prtypersns=    eform.getPartyPersons();
  String prtyorgname=   eform.getPartyOrgName();
  String prtyauthfname= eform.getPartyAuthFirstName();
  String prtyauthmdname = eform.getPartyAuthMiddleName();
  String prtyauthlname= eform.getPartyAuthLastName();
  String prtybnknme =   eform.getPartyBankName();
  String prtybnmadrs =  eform.getPartyBankAddress();
  
  if(apgender.equalsIgnoreCase("M"))
  {
  	if(lang.equalsIgnoreCase("en"))
  		eform.setAppGenderDisp("Male");
  	else
  		eform.setAppGenderDisp("पुस्र्ष");
  }
  else
  {
  	if(lang.equalsIgnoreCase("en"))
  		eform.setAppGenderDisp("Female");
  	else
  		eform.setAppGenderDisp("महिला");
  }


  if(prtygender.equalsIgnoreCase("M"))
  {
  	if(lang.equalsIgnoreCase("en"))
  		eform.setPartyGenderDisp("Male");
  	else
  		eform.setPartyGenderDisp("पुस्र्ष");
  }
  else
  {
  	if(lang.equalsIgnoreCase("en"))
  		eform.setPartyGenderDisp("Female");
  	else
  		eform.setPartyGenderDisp("महिला");
  }


  //***********END**********************

  String estmpurpose = eform.getEstampPurpose();
  String uid = eform.getUid();
  String docname=eform.getDoc();
  String docPath = eform.getDocPath();
  //String docname = eform.getDocname();
  
  
  Date date = new Date();
  Format yearformat  = new SimpleDateFormat("yy");
  Format monthformat = new SimpleDateFormat("MM");
  Format dateformat  = new SimpleDateFormat("dd");
  String dfmt = dateformat.format(date);
  String yfmt = yearformat.format(date);
  String mfmt = monthformat.format(date);
  DBUtility transmgtUtil = null;
  try{
  transmgtUtil = new DBUtility();
  transmgtUtil.setAutoCommit(false);
  
 //for  txn table and estamp_txn_id
  
	  txntable[0] = estmpurpose;
	  txntable[1] = uid;
	  txntable[2] = estmTxnId;
	  

	  //for duty table
	 
	  
	  //for document table
	  docTbl[0]=docname ;
	  docTbl[1]=uid;
	  docTbl[2]=docPath;
	  docTbl[3]=estmTxnId;
	  
	  
	  //for txn_party table-applicant
	
	  
	  appdtl[0]= aptype;
	  if(aptype.equalsIgnoreCase("1")){
	  appdtl[1]= apauthfname;
	  appdtl[2]= apauthmdname;
	  appdtl[3]= apauthlname;
	  appdtl[4]= "";
	  appdtl[5]= "";
	  }else {
		  appdtl[1]= apfname;
		  appdtl[2]= apmdname;
		  appdtl[3]= aplname;
		  appdtl[5]= appdy +"-"+apmnth+"-"+apyr ;
		  appdtl[4]= apgender;
		  String mnthDisp = "";
		  if(apmnth.equalsIgnoreCase("JAN"))
			  mnthDisp = "01";
		  else if(apmnth.equalsIgnoreCase("FEB"))
			  mnthDisp = "02";
		  else if(apmnth.equalsIgnoreCase("MAR"))
			  mnthDisp = "03";
		  else if(apmnth.equalsIgnoreCase("APR"))
			  mnthDisp = "04";
		  else if(apmnth.equalsIgnoreCase("MAY"))
			  mnthDisp = "05";
		  else if(apmnth.equalsIgnoreCase("JUN"))
			  mnthDisp = "06";
		  else if(apmnth.equalsIgnoreCase("JUL"))
			  mnthDisp = "07";
		  else if(apmnth.equalsIgnoreCase("AUG"))
			  mnthDisp = "08";
		  else if(apmnth.equalsIgnoreCase("SEP"))
			  mnthDisp = "09";
		  else if(apmnth.equalsIgnoreCase("OCT"))
			  mnthDisp = "10";
		  else if(apmnth.equalsIgnoreCase("NOV"))
			  mnthDisp = "11";
		  else if(apmnth.equalsIgnoreCase("DEC"))
			  mnthDisp = "12";
		  
		  eform.setAppAge(appdy +"/"+mnthDisp+"/"+apyr);

		 // eform.setAppAge(appdtl[5]);
	  }
	  
	  
	  appdtl[6]= apcntry;
	  appdtl[7]= apstate ;
	  appdtl[8]= apdstrct ;
	  appdtl[9]= apadrs ;
	  appdtl[10]= appostlcd ;
	  appdtl[11]= apphn ;
	  appdtl[12]= apmob ;
	  appdtl[13]= apemail ;
	  appdtl[14]= apphotoId ;
	  appdtl[15]= apphotoidno ;
	  appdtl[16]= apbnknme  ;
	  appdtl[17]= apbnmadrs  ;
	  appdtl[18]= aporgname ;
	  appdtl[19]= apfthrname  ;
	  appdtl[20]= apmthrname ;
	  appdtl[21]= appersns  ;
	  appdtl[22]= uid  ;
	  appdtl[23]= deedDuratn;
	  appdtl[24]= "Y" ;
	  appdtl[25]= estmTxnId ;
	 // appdtl[25]= deedDuratn;
	 
	//for txn_party table-party
	 
	  partydtl[0]= prtytype;
	 
	  if(prtytype.equalsIgnoreCase("1")){
		  partydtl[1]= prtyauthfname;
		  partydtl[2]= prtyauthmdname;
		  partydtl[3]= prtyauthlname;
		  partydtl[4]= "";
		  partydtl[5]= "";
		 
	  }else {
		  partydtl[1]= prtyfname;
		  partydtl[2]= prtymdname;
		  partydtl[3]= prtylname;
		  partydtl[4]= prtygender;
		//SIMRAN
		  //if(lang.equalsIgnoreCase("hi"))
		  //{
			  String mnthDisp = "";
			  if(prtymnth.equalsIgnoreCase("JAN"))
				  mnthDisp = "01";
			  else if(prtymnth.equalsIgnoreCase("FEB"))
				  mnthDisp = "02";
			  else if(prtymnth.equalsIgnoreCase("MAR"))
				  mnthDisp = "03";
			  else if(prtymnth.equalsIgnoreCase("APR"))
				  mnthDisp = "04";
			  else if(prtymnth.equalsIgnoreCase("MAY"))
				  mnthDisp = "05";
			  else if(prtymnth.equalsIgnoreCase("JUN"))
				  mnthDisp = "06";
			  else if(prtymnth.equalsIgnoreCase("JUL"))
				  mnthDisp = "07";
			  else if(prtymnth.equalsIgnoreCase("AUG"))
				  mnthDisp = "08";
			  else if(prtymnth.equalsIgnoreCase("SEP"))
				  mnthDisp = "09";
			  else if(prtymnth.equalsIgnoreCase("OCT"))
				  mnthDisp = "10";
			  else if(prtymnth.equalsIgnoreCase("NOV"))
				  mnthDisp = "11";
			  else if(prtymnth.equalsIgnoreCase("DEC"))
				  mnthDisp = "12";
			  
			  eform.setPartyAge(prtydy +"/"+mnthDisp+"/"+prtyyr);
		//  }
		  //else
		  //{
			//  eform.setPartyAge( partydtl[8]);
		  //}
		  //eform.setPartyAge( partydtl[8]);
		  partydtl[5]= prtydy +"-"+prtymnth+"-"+prtyyr ;
		//  eform.setPartyAge( partydtl[5]);
	  }
	 //
	 
	  partydtl[6]= prtycntry;
	  partydtl[7]= prtystate ;
	  partydtl[8]= prtydstrct ;
	  partydtl[9]= prtyadrs ;
	  partydtl[10]= prtypostlcd ;
	  partydtl[11]= prtyphn ;
	  partydtl[12]= prtymob ;
	  partydtl[13]= prtyemail ;
	  partydtl[14]= prtyphotoId ;
	  partydtl[15]= prtyphotoidno ;
	  partydtl[16]= prtybnknme  ;
	  partydtl[17]= prtybnmadrs  ;
	  partydtl[18]= prtyorgname ;
	  partydtl[19]= prtyfthrname  ;
	  partydtl[20]= prtymthrname ;
	  partydtl[21]= prtypersns;
	  partydtl[22]= uid ;
	  partydtl[23]= "";
	  partydtl[24]= "N" ;
	  partydtl[25]= estmTxnId ;
	//  partydtl[25]= "";
	  
	  transmgtUtil.createPreparedStatement(CommonSQL.TXN_TABLE_UPDATE);
	  transmgtUtil.executeUpdate(txntable);
	  transmgtUtil.createPreparedStatement(CommonSQL.PARTY_TABLE_UPDATE);
	  transmgtUtil.executeUpdate(appdtl); 		
	  if (!prtytype.equalsIgnoreCase("")){
	  transmgtUtil.createPreparedStatement(CommonSQL.PARTY_TABLE_UPDATE);
	  transmgtUtil.executeUpdate(partydtl); 
	  }
	  if (!docname.equalsIgnoreCase("")){
	  transmgtUtil.createPreparedStatement(CommonSQL.DOCUMENT_TABLE_UPDATE);
	  transmgtUtil.executeUpdate(docTbl); }
	  transactionflag = true;
	  transmgtUtil.commit();				     
	
 }catch (NullPointerException ne) {
	 ne.printStackTrace();
	   transmgtUtil.rollback();
	   throw ne;
		
   }
	    catch (SQLException se) {
	    	 se.printStackTrace();
	    	transmgtUtil.rollback();
	    	
		logger.error("SQL Exception at estamp  in DAO " + se); 
	 throw se;
	}
   catch(Exception e){
	   e.printStackTrace();
   	transmgtUtil.rollback();
	logger.error(" Exception at estamp in DAO " + e);
	throw e;
	}
   
   finally {
		 try{	    
			 transmgtUtil.closeConnection();
		 }
		 catch (Exception e) {
		 logger.error("Exception at estamp in DAO  " + e);
		 }		
       }
   eform.setMainTxnId(estmTxnId);
   return transactionflag;   
 }
 
 public  boolean inserteCode (DutyCalculationForm eform, DashBoardDTO dto)throws NullPointerException,
 SQLException,Exception{
	 
  boolean transactionflag = false;
  //String estmTxnId = null;
  String ecode=null;
  String ecodetable[]= new String[16];
  String paytbl[]    = new String[2];
  String updtValidityDate[] = new String[2];
 
  Date date = new Date();
  Format yearformat  = new SimpleDateFormat("yyyy");
  Format monthformat = new SimpleDateFormat("MM");
  Format dateformat  = new SimpleDateFormat("dd");
  String dfmt = dateformat.format(date);
  String yfmt = yearformat.format(date);
  String mfmt = monthformat.format(date);
  DBUtility transmgtUtil = null;
  try{
  transmgtUtil = new DBUtility();
  transmgtUtil.setAutoCommit(false);
  
 //for  txn table and estamp_txn_id
 /* String SQL1 = "SELECT COUNT(ESTAMP_CODE) FROM IGRS_ESTAMP_DETLS WHERE TRUNC(CREATED_DATE)= TRUNC(SYSDATE)";

  //selet count2
*/  //logger.debug(SQL1);
  transmgtUtil.createStatement();
  String number1 = transmgtUtil.executeQry(CommonSQL.SELECT_COUNT2);
  if (number1.equalsIgnoreCase("0")){
		logger.debug("in if clause");
		/*String drpqry = "DROP SEQUENCE IGRS_ESTAMP_CODE_SEQ";	

		//drop 2
*/		transmgtUtil.createStatement();
		transmgtUtil.executeUpdate(CommonSQL.DROP_SQNC2);
		/*String crtqry = "CREATE SEQUENCE IGRS_ESTAMP_CODE_SEQ START WITH 1 INCREMENT BY 1 MAXVALUE 999999 MINVALUE 1 NOCYCLE  NOCACHE ORDER";

		//create2
*/		transmgtUtil.createStatement();
		transmgtUtil.executeUpdate(CommonSQL.CREATE_STMNT2);
	}
	 /* String SQL2 = "select IGRS_ESTAMP_CODE_SEQ.nextval from dual";

	  //select sqnce6
*/	  transmgtUtil.createStatement();
	  String number2 = transmgtUtil.executeQry(CommonSQL.SELECT_SEQUENCE6);
	  if(number2.length()==1){
		  number2="00000"+number2;  
		  }else
		  if(number2.length()==2){
			  number2="0000"+number2;  
		  }else
		  if(number2.length()==3){
			  number2="000"+number2;  
		  }else
		  if(number2.length()==4){
			  number2="00"+number2;  
		  }else
		  if(number2.length()==5){
			  number2="0"+number2;  
		  }
	  String appdis = eform.getAppDistrict();
	  if(appdis.length()==1){
		  appdis = "0"+appdis;
	  }
	//  ecode = "MP01"+appdis+dfmt+mfmt+yfmt+number2;
	  ecode = "0101"+appdis+dfmt+mfmt+yfmt+number2;
	  eform.setEcode(ecode);
	  
     /* String SQL3 = "select IGRS_ESTAMP_TRANSACTION_ID_SEQ.nextval from dual";
      //select sqnc 7
*/	  transmgtUtil.createStatement();
	  String number3 = transmgtUtil.executeQry(CommonSQL.SELECT_SEQUENCE7);
	  
	  ecodetable[0] = number3;
	  ecodetable[1] = eform.getMainTxnId();
	  ecodetable[2] = ecode;
	  ecodetable[3] = eform.getUid();
	  ecodetable[4] = "023";
	  ecodetable[5] = Double.toString(eform.getDutyCalculationDTO().getTotal());
	  ecodetable[6] = "1";
	  ecodetable[7] = "1";
	  ecodetable[8] = null;// validity date according to the configurable param-----yet to be done
	  ecodetable[9] = "A";
	  ecodetable[10]= eform.getEstampPurpose();
	  ecodetable[11]= "E";  ///source mod code----master is yet to be made
	  ecodetable[12] = eform.getIssuedPlace();
	  ecodetable[13] = eform.getOfficeName();
	  ecodetable[14] = eform.getUserName();
	  ecodetable[15] = eform.getObjDashBoardDTO().getRole();
	  
	  paytbl[0]= eform.getUid();
	  paytbl[1]= eform.getMainTxnId();
	  
	  
	  updtValidityDate[0]=eform.getMainTxnId();
	  updtValidityDate[1]=eform.getMainTxnId();
	 

	  boolean boo=checkEstampTxn(eform.getMainTxnId(),"E");
	
	  if(boo){
	  transmgtUtil.createPreparedStatement(CommonSQL.ESTAMP_DTL_TABLE_INSERT);
	  transmgtUtil.executeUpdate(ecodetable);
	  
	  // Added by Lavi for the updation of validity date in the table
	  
	  transmgtUtil.createPreparedStatement(CommonSQL.UPDT_ECODE_VALID_DATE);
	  transmgtUtil.executeUpdate(updtValidityDate);
	  
	  // end of updation
	  
	  transmgtUtil.createPreparedStatement(CommonSQL.PAY_TABLE_UPDATE);
	  transmgtUtil.executeUpdate(paytbl);
	  
	  transmgtUtil.createPreparedStatement(CommonSQL.PAY_TABLE_UPDATE);
	  transmgtUtil.executeUpdate(paytbl);
      transactionflag = true;
	  transmgtUtil.commit();				     
	  }
 }catch (NullPointerException ne) {
	    ne.printStackTrace();
	   transmgtUtil.rollback();
	   throw ne;
		
   }
	    catch (SQLException se) {
	    	 se.printStackTrace();
	    	transmgtUtil.rollback();
	    	
		logger.error("SQL Exception at estamp  in DAO " + se); 
	 throw se;
	}
   catch(Exception e){
	   e.printStackTrace();
   	transmgtUtil.rollback();
	logger.error(" Exception at estamp in DAO " + e);
	throw e;
	}
   
   finally {
		 try{	    
			 transmgtUtil.closeConnection();
		 }
		 catch (Exception e) {
		 logger.error("Exception at estamp in DAO  " + e);
		 }		
       }
   //eform.setMainTxnId(estmTxnId);
   return transactionflag;   
 }

 

 public ArrayList getPayDtls(String txnId) throws Exception{
		
	 
	 	String[] tid = new String[1];
		tid[0]=txnId;
		
		ArrayList list = new ArrayList();
		DBUtility transmgtUtil = new DBUtility();
	try{
		transmgtUtil.createPreparedStatement(CommonSQL.GET_PAY_DTLS);
		list=transmgtUtil.executeQuery(tid);
		list.trimToSize();
	}catch(Exception e)
	{
		logger.debug(e.getMessage(),e);
	}
	finally{
		transmgtUtil.closeConnection();
	}
		return list;
	}
 
 public ArrayList getspDtls(String txnId,String language) throws Exception{
		String[] tid = new String[1];
		tid[0]=txnId;
		ArrayList list = new ArrayList();
		DBUtility transmgtUtil = new DBUtility();
		try{
		if("en".equalsIgnoreCase(language))
		{
		transmgtUtil.createPreparedStatement(CommonSQL.GET_SP_DTLS);
		}
		else
		{
			transmgtUtil.createPreparedStatement(CommonSQL.GET_SP_DTLS_HI);
		}
		list=transmgtUtil.executeQuery(tid);
		list.trimToSize();
		}catch(Exception e)
		{
			logger.debug(e.getMessage(),e);
		}
		finally{
			transmgtUtil.closeConnection();
		}
		return list;
	}
 public ArrayList getAppDtls(String txnId,String language) throws Exception{
		String[] tid = new String[1];
		tid[0]=txnId;
		ArrayList list = new ArrayList();
		DBUtility transmgtUtil = new DBUtility();
		try{
		if("en".equalsIgnoreCase(language))
		{
		transmgtUtil.createPreparedStatement(CommonSQL.GET_APP_DTLS);
		}
		else
		{

			transmgtUtil.createPreparedStatement(CommonSQL.GET_APP_DTLS_HI);
		
			
		}
		list=transmgtUtil.executeQuery(tid);
		list.trimToSize();
		}catch(Exception e)
		{
			logger.debug(e.getMessage(),e);
		}
		finally{
			transmgtUtil.closeConnection();
		}
		return list;
	}
 public ArrayList getPartyDtls(String txnId,String language) throws Exception{
		String[] tid = new String[1];
		tid[0]=txnId;
		ArrayList list = new ArrayList();
		DBUtility transmgtUtil = new DBUtility();
		try{
		if("en".equalsIgnoreCase(language))
		{
		transmgtUtil.createPreparedStatement(CommonSQL.GET_PARTY_DTLS);
		}
		else
		{
			transmgtUtil.createPreparedStatement(CommonSQL.GET_PARTY_DTLS_HI);
		}
		list=transmgtUtil.executeQuery(tid);
		list.trimToSize();
		}catch(Exception e)
		{
			logger.debug(e.getMessage(),e);
		}
		finally{
			transmgtUtil.closeConnection();
		}
		return list;
	}
 public ArrayList getPartyAdoptDtls(String txnId,String language) throws Exception{
		String[] tid = new String[1];
		tid[0]=txnId;
		ArrayList list = new ArrayList();
		DBUtility transmgtUtil = new DBUtility();
		try{
		if("en".equalsIgnoreCase(language))
		{
		transmgtUtil.createPreparedStatement(CommonSQL.GET_PARTY_DTLS_ADOPT);
		}
		else
		{
			transmgtUtil.createPreparedStatement(CommonSQL.GET_PARTY_DTLS_ADOPT_HI);
		}
		list=transmgtUtil.executeQuery(tid);
		list.trimToSize();
		}catch(Exception e)
		{
			logger.debug(e.getMessage(),e);
		}
		finally{
			transmgtUtil.closeConnection();
		}
		return list;
	}
 
 public ArrayList getEcodeDtls(String txnId,String language) throws Exception{
		String[] tid = new String[1];
		tid[0]=txnId;
		ArrayList list = new ArrayList();
		DBUtility transmgtUtil = new DBUtility();
		try{
		if("en".equalsIgnoreCase(language))
		{
		transmgtUtil.createPreparedStatement(CommonSQL.GET_ECODE_DTLS);
		}
		else
		{
			transmgtUtil.createPreparedStatement(CommonSQL.GET_ECODE_DTLS_HI);
		}
		list=transmgtUtil.executeQuery(tid);
		list.trimToSize();
		}catch(Exception e)
		{
			logger.debug(e.getMessage(),e);
		}
		finally{
			transmgtUtil.closeConnection();
		}
		return list;
	}

 public ArrayList getspbnkDtls(String txnId,String language) throws Exception{
		String[] tid = new String[1];
		tid[0]=txnId;
		ArrayList list = new ArrayList();
		DBUtility transmgtUtil = new DBUtility();
		try{
		if("en".equalsIgnoreCase(language))
		{
		transmgtUtil.createPreparedStatement(CommonSQL.GET_SP_BNK_DTLS);
		}
		else
		{
			transmgtUtil.createPreparedStatement(CommonSQL.GET_SP_BNK_DTLS_HI);
		}
		list=transmgtUtil.executeQuery(tid);
		list.trimToSize();
		}catch(Exception e)
		{
			logger.debug(e.getMessage(),e);
		}
		finally{
			transmgtUtil.closeConnection();
		}
		return list;
	}
 
 public ArrayList getDeedDtls(String txnId,String language) throws Exception{
		String[] tid = new String[1];
		tid[0]=txnId;
		ArrayList list = new ArrayList();
		DBUtility transmgtUtil = new DBUtility();
		try{
		if("en".equalsIgnoreCase(language))
		{
		transmgtUtil.createPreparedStatement(CommonSQL.GET_DEED_DTLS);
		}
		else
		{
		transmgtUtil.createPreparedStatement(CommonSQL.GET_DEED_DTLS_HI);
		}
		list=transmgtUtil.executeQuery(tid);
		list.trimToSize();
		}catch(Exception e)
		{
			logger.debug(e.getMessage(),e);
		}
		finally{
			transmgtUtil.closeConnection();
		}
		return list;
	}
	
 
 
 public String getDuty(String txnId) throws Exception{
		String[] tid = new String[1];
		tid[0]=txnId;
		String dty=null;
		DBUtility transmgtUtil = new DBUtility();
		try{
		transmgtUtil.createPreparedStatement(CommonSQL.GET_DUTY_DTLS);
		dty=transmgtUtil.executeQry(tid);
		}catch(Exception e)
		{
			logger.debug(e.getMessage(),e);
		}
		finally{
			transmgtUtil.closeConnection();
		}
		return dty;
	}
 public String getLangauge(String txnId) throws Exception{
		String[] tid = new String[1];
		tid[0]=txnId;
		String dty=null;
		
		DBUtility transmgtUtil = new DBUtility();
		try{
		transmgtUtil.createPreparedStatement(CommonSQL.GET_LANGUAGE);
		dty=transmgtUtil.executeQry(tid);
		}catch(Exception e)
		{
			logger.debug(e.getMessage(),e);
		}
		finally{
			transmgtUtil.closeConnection();
		}
		return dty;
	}
 
 public String getMainId(String txnId) throws Exception{
		String[] tid = new String[1];
		tid[0]=txnId;
		String dty=null;
		DBUtility transmgtUtil = new DBUtility();
		try{
		transmgtUtil.createPreparedStatement(CommonSQL.GET_MAIN_ID);
		dty=transmgtUtil.executeQry(tid);
		}catch(Exception e)
		{
			logger.debug(e.getMessage(),e);
		}
		finally{
			transmgtUtil.closeConnection();
		}
		return dty;
	}
 public String getspName(String txnId) throws Exception{
		String[] tid = new String[1];
		tid[0]=txnId;
		String dty=null;
		DBUtility transmgtUtil = new DBUtility();
		try{
		transmgtUtil.createPreparedStatement(CommonSQL.GET_SP_NAME_DTLS);
		dty=transmgtUtil.executeQry(tid);
		}catch(Exception e)
		{
			logger.debug(e.getMessage(),e);
		}
		finally{
			transmgtUtil.closeConnection();
		}
		return dty;
	}
 public String getspLicenseNo(String txnId) throws Exception{
		String[] tid = new String[1];
		tid[0]=txnId;
		String dty=null;
		DBUtility transmgtUtil = new DBUtility();
		try{
		transmgtUtil.createPreparedStatement(CommonSQL.GET_SP_LICENSE_DTLS);
		dty=transmgtUtil.executeQry(tid);
		transmgtUtil.closeConnection();
		}catch(Exception e)
		{
			logger.debug(e.getMessage(),e);
		}
		finally{
			transmgtUtil.closeConnection();
		}
		return dty;
	}
 
 public ArrayList getruName(String txnId,String language) throws Exception{
		String[] tid = new String[1];
		tid[0]=txnId;
		ArrayList list = new ArrayList();
		DBUtility transmgtUtil = new DBUtility();
		try{
		if("en".equalsIgnoreCase(language))
		{
		transmgtUtil.createPreparedStatement(CommonSQL.GET_RU_NAME_DTLS);
		}
		else
		{
		transmgtUtil.createPreparedStatement(CommonSQL.GET_RU_NAME_DTLS_HI);
		}
		list=transmgtUtil.executeQuery(tid);
		list.trimToSize();
		}catch(Exception e)
		{
			logger.debug(e.getMessage(),e);
		}
		finally{
			transmgtUtil.closeConnection();
		}
		return list;
	}
 
 
 
 
 /******************************************************************  
  *   Method Name  :   insertPay()
  *   Arguments    :   Form 
  *   Return       :   Boolean
  *   Throws 	  :   NullPointer  or SQLException or Exception
*******************************************************************/  

public  boolean insertPay(DutyCalculationForm eform, DashBoardDTO dto)throws NullPointerException,
SQLException,Exception{
 
boolean transactionflag = false;
//String estmTxnId = null;
String paytable[] = new String[5];
DBUtility transmgtUtil = null;
try{
transmgtUtil = new DBUtility();
transmgtUtil.setAutoCommit(false);
 
/*String SQL1 = "select IGRS_ESTAMP_PAYMENT_ID_SEQ.nextval from dual";

//select sqnc8
*/transmgtUtil.createStatement();
String number1 = transmgtUtil.executeQry(CommonSQL.SELECT_SEQUENCE8);
eform.setUniqKey(number1);

paytable[0] =number1;
paytable[1] =eform.getMainTxnId();
//paytable[2] =(eform.getDutyCalculationDTO().getDutyafterExemptionDisplay());
paytable[2]=getPayableDuty(eform.getMainTxnId());
paytable[3] ="I";
paytable[4] =eform.getUid();

  transmgtUtil.createPreparedStatement(CommonSQL.PAY_TABLE_INSERT);
  transmgtUtil.executeUpdate(paytable);
 
  transactionflag = true;
  transmgtUtil.commit();				     

}catch (NullPointerException ne) {
    ne.printStackTrace();
   transmgtUtil.rollback();
   throw ne;
	
}
    catch (SQLException se) {
    	 se.printStackTrace();
    	transmgtUtil.rollback();
    	
	logger.error("SQL Exception at PaymentTransactionFinal  in DAO " + se); 
 throw se;
}
catch(Exception e){
   e.printStackTrace();
	transmgtUtil.rollback();
logger.error(" Exception at PaymentTransactionFinal in DAO " + e);
throw e;
}

finally {
	 try{	    
		 transmgtUtil.closeConnection();
	 }
	 catch (Exception e) {
	 logger.error("Exception at PaymentTransactionFinal in DAO  " + e);
	 }		
   }
//eform.setMainTxnId(estmTxnId);
	return transactionflag;   
}

//ADDED BY LAVI FOR UPDATION OF COMPLETION STATUS IN ESTAMP TABLE


public boolean updateStatus(DutyCalculationForm form) throws Exception{
	String tid[] = new String[1];
	tid[0]=form.getMainTxnId();
	logger.info("inside dao of update status");
	logger.info("--------->"+form.getMainTxnId());
	boolean flag = false;
	DBUtility transmgtUtil = new DBUtility();
	try{
	transmgtUtil.createPreparedStatement(CommonSQL.UPDATE_ESTAMP_STATUS);
	flag=transmgtUtil.executeUpdate(tid);
	}catch(Exception e)
	{
		logger.debug(e.getMessage(),e);
	}
	finally{
		transmgtUtil.closeConnection();
	}
	return flag;
}

 // for returning ecode to other modules.

 public String getEcode (String disttId, String amnt, String func_id, String purpose, EstampDTO estampDTO, String regInitId,String modFlag,String language) throws NullPointerException,
 SQLException,Exception{
	 try
	 {
	 String ecodetable[] = new String[16];
	 String updtValidityDate[] = new String [2];
	 String txntable[] = new String [8];
	
	 String txntableParty2[] = new String [5];
	 String ecode= null;
	 String ecodeTxnId = null;
	 String[] tid = new String[1];
		tid[0]=regInitId;
		ArrayList list = new ArrayList();
		String deedId= null;
		String instId= null;
	 Date date = new Date();
	  Format yearformat  = new SimpleDateFormat("yyyy");
	  Format monthformat = new SimpleDateFormat("MM");
	  Format dateformat  = new SimpleDateFormat("dd");
	  String dfmt = dateformat.format(date);
	  String yfmt = yearformat.format(date);
	  String mfmt = monthformat.format(date);
	  DBUtility transmgtUtil = null;
	  try{
	  transmgtUtil = new DBUtility();
	  transmgtUtil.setAutoCommit(true);
	  RegCommonBO commonBO = new RegCommonBO();
		RegCommonBD commonBD = new RegCommonBD();
		RegCommonDTO mapDto =new RegCommonDTO();
		RegCommonForm form = new RegCommonForm();
		
		String OwnerRolePoA="";
		String partyId = "";
		String CommonDeedFlag = "";
	     CommonDeedFlag= getCommonFlowFlag(regInitId);
	 //for  txn table and estamp_txn_id
	 /* String SQL1 = "SELECT COUNT(ESTAMP_CODE) FROM IGRS_ESTAMP_DETLS WHERE TRUNC(CREATED_DATE)= TRUNC(SYSDATE)";
	  
	  //select count3
*/	  //logger.debug(SQL1);
	  transmgtUtil.createStatement();
	  String number1 = transmgtUtil.executeQry(CommonSQL.SELECT_COUNT3);
	  if (number1.equalsIgnoreCase("0")){
			logger.debug("in if clause");
			/*String drpqry = "DROP SEQUENCE IGRS_ESTAMP_CODE_SEQ";	
			
			//drop sqnc3
*/			transmgtUtil.createStatement();
			transmgtUtil.executeUpdate(CommonSQL.DROP_SQNC3);
			/*String crtqry = "CREATE SEQUENCE IGRS_ESTAMP_CODE_SEQ START WITH 1 INCREMENT BY 1 MAXVALUE 999999 MINVALUE 1 NOCYCLE  NOCACHE ORDER";
		
			//create sqnc3
*/			transmgtUtil.createStatement();
			transmgtUtil.executeUpdate(CommonSQL.CREATE_STMNT3);
		}
		 /* String SQL2 = "select IGRS_ESTAMP_CODE_SEQ.nextval from dual";
		 //sqnc9
		  //select
*/		  transmgtUtil.createStatement();
		  String number2 = transmgtUtil.executeQry(CommonSQL.SELECT_SEQUENCE9);
		  if(number2.length()==1){
			  number2="00000"+number2;  
			  }else
			  if(number2.length()==2){
				  number2="0000"+number2;  
			  }else
			  if(number2.length()==3){
				  number2="000"+number2;  
			  }else
			  if(number2.length()==4){
				  number2="00"+number2;  
			  }else
			  if(number2.length()==5){
				  number2="0"+number2;  
			  }
		  String appdis = disttId;
		  if(appdis.length()==1){
			  appdis = "0"+appdis;
		  }
		  ecode = "0101"+appdis+dfmt+mfmt+yfmt+number2;
		  
		 /* String SQL3 = "select IGRS_ESTAMP_TRANSACTION_ID_SEQ.nextval from dual";
		  //
		  //selct sqnc 10
*/		  transmgtUtil.createStatement();
		  String number3 = transmgtUtil.executeQry(CommonSQL.SELECT_SEQUENCE10);
		  
		  
		  
		  /*String SQL4 = "SELECT COUNT(ESTAMP_TXN_ID) FROM IGRS_ESTAMP_TXN_DETLS WHERE TRUNC(CREATED_DATE)= TRUNC(SYSDATE)";
		
		  //select count4
*/		  //logger.debug(SQL4);
		  transmgtUtil.createStatement();
		  String number4 = transmgtUtil.executeQry(CommonSQL.SELECT_COUNT4);
		  if (number4.equalsIgnoreCase("0")){
				logger.debug("in if clause");
				/*String drpqry1 = "DROP SEQUENCE IGRS_ESTAMP_TXN_ID_SEQ";	
				
				//drop seqnce 4
*/				transmgtUtil.createStatement();
				transmgtUtil.executeUpdate(CommonSQL.DROP_SQNC4);
				//transmgtUtil.executeQry(drpqry1);
				/*String crtqry1 = "CREATE SEQUENCE IGRS_ESTAMP_TXN_ID_SEQ START WITH 1 INCREMENT BY 1 MAXVALUE 999999 MINVALUE 1 NOCYCLE  NOCACHE ORDER";
		
				//create sqnce 4
*/				transmgtUtil.createStatement();
				transmgtUtil.executeUpdate(CommonSQL.CREATE_STMNT4);
			}
			 /* String SQL5 = "select IGRS_ESTAMP_TXN_ID_SEQ.nextval from dual";
	
			  //select sqnc 11
*/			  transmgtUtil.createStatement();
			  String number5 = transmgtUtil.executeQry(CommonSQL.SELECT_SEQUENCE11);
			  if(number5.length()==1){
				  number5="00000"+number5;  
				  }else
				  if(number5.length()==2){
					  number5="0000"+number5;  
				  }else
				  if(number5.length()==3){
					  number5="000"+number5;  
				  }else
				  if(number5.length()==4){
					  number5="00"+number5;  
				  }else
				  if(number5.length()==5){
					  number5="0"+number5;  
				  }
			 String estmTxnId = dfmt+mfmt+yfmt+number5;
			 
			ecodeTxnId = ecode+"~"+estmTxnId;
		  
		  ecodetable[0] = number3;
		  ecodetable[1] = estmTxnId;
		  ecodetable[2] = ecode;
		  ecodetable[3] = estampDTO.getUid();
		  ecodetable[4] = func_id;
		  ecodetable[5] = amnt;
		  ecodetable[6] = "1";
		  ecodetable[7] = "1";
		  ecodetable[8] = null;// validity date according to the configurable param-----yet to be done
		  ecodetable[9] = "A";
		  ecodetable[10]= purpose;
		  ecodetable[11]= modFlag;  ///source mod code----master is yet to be made
		  ecodetable[12] = estampDTO.getIssuedPlace();
		  ecodetable[13] = estampDTO.getOfficeName();
		  ecodetable[14] = estampDTO.getUserName();
		  ecodetable[15] = regInitId;
		  
		  transmgtUtil.createPreparedStatement(CommonSQL.GET_DEED_DETLS);
		  list=transmgtUtil.executeQuery(tid);
		  logger.info("list>>>>"+list);
		  
		  if(list!=null && list.size()>0){
				
				ArrayList rowList;
				for (int i = 0; i < list.size(); i++) {
					
					rowList = (ArrayList)list.get(i);
					
					
						 deedId = (String)rowList.get(0);
						 instId = (String)rowList.get(1);
				}
		  }
						
		  updtValidityDate[0]=estmTxnId;
		  updtValidityDate[1]=estmTxnId;
		 
		  txntable[0] = estmTxnId;
		  txntable[1] = "1";
		  txntable[2] = "2";
		  txntable[3] = deedId;
		  txntable[4] = instId;
		  txntable[5] = estampDTO.getUid();
		  txntable[6] = purpose;
		  txntable[7] = "C";
		  
		  
		  transmgtUtil.createPreparedStatement(CommonSQL.ESTAMP_TXN_DTL_INSERT_REG);
		  transmgtUtil.executeUpdate(txntable);
		
		  
		  transmgtUtil.createPreparedStatement(CommonSQL.ESTAMP_DTL_TABLE_INSERT_REG);
		  transmgtUtil.executeUpdate(ecodetable);
		  
		  
		  
		  // Added by Lavi for the updation of validity date in the table
		  
		  transmgtUtil.createPreparedStatement(CommonSQL.UPDT_ECODE_VALID_DATE);
		  transmgtUtil.executeUpdate(updtValidityDate);
		  
		  // end of updation
		  
		  // added by Lavi for inserting the party details from registration module
		  transmgtUtil.createStatement();
		  String number6 = transmgtUtil.executeQry(CommonSQL.SELECT_SEQUENCE3);
		  String txntableParty1[] = new String [28];
		  txntableParty1[0] = number6;
		  txntableParty1[1] = estmTxnId;
		 
		  
		  transmgtUtil.createPreparedStatement(CommonSQL.GET_COMMON_FLAG);
		  String commonFlag=transmgtUtil.executeQry(new String[]{instId});
		  if("Y".equalsIgnoreCase(commonFlag))
		  {
			  ArrayList partyList1=null;
			  ArrayList partyList2=null;
			//  transmgtUtil.createPreparedStatement(CommonSQL.GET_PARTY_COMMON_EXE);
			  transmgtUtil.createPreparedStatement(CommonSQL.GET_PARTY_COMMON_EXE_updated);
			  
			  partyList1=transmgtUtil.executeQuery(new String[]{regInitId});
			  //transmgtUtil.createPreparedStatement(CommonSQL.GET_PARTY_COMMON_CLA);
			  transmgtUtil.createPreparedStatement(CommonSQL.GET_PARTY_COMMON_CLA_updated);
			  partyList2=transmgtUtil.executeQuery(new String[]{regInitId});
			  if(partyList1==null||partyList1.size()==0)
			  {
				  if(partyList2!=null&&partyList2.size()>0)
				  {
					  
					  ArrayList subList=(ArrayList) partyList2.get(0);
					  txntableParty1[2]=(String) subList.get(0);
					  txntableParty1[3]="Y";
					  txntableParty1[4]=(String) subList.get(2);
					  txntableParty1[5]=(String) subList.get(3);
					  txntableParty1[6]=(String) subList.get(4);
					  txntableParty1[7]=(String) subList.get(5);
					  txntableParty1[8]=(String) subList.get(6);
					  txntableParty1[9]=(String) subList.get(7);
					  txntableParty1[10]=(String) subList.get(8);
					  txntableParty1[11]=(String) subList.get(9);
					  txntableParty1[12]=(String) subList.get(10);
					  txntableParty1[13]=(String) subList.get(11);
					  txntableParty1[14]=(String) subList.get(12);
					  txntableParty1[15]=(String) subList.get(13);
					  txntableParty1[16]=(String) subList.get(14);
					  txntableParty1[17]=(String) subList.get(15);
					  txntableParty1[18]=(String) subList.get(16);
					  txntableParty1[19]=(String) subList.get(17);
					  txntableParty1[20]=(String) subList.get(18);
					  txntableParty1[21]=(String) subList.get(19);
					  txntableParty1[22]=(String) subList.get(20);
					  //txntableParty1[23]=(String) subList.get(21);
					  txntableParty1[23]=(String) subList.get(22);
					  txntableParty1[24]="";
					  txntableParty1[25]=(String) subList.get(24);
					  txntableParty1[26]=(String) subList.get(25);
					  txntableParty1[27]=String.valueOf(partyList2.size());
					  transmgtUtil.createPreparedStatement(CommonSQL.INSERT_ESTAMP_TXN_PARTY_DETLS_PARTY1);
					  estampDTO.setAppPersons(String.valueOf(partyList2.size()));
					  
					  if(subList.get(31)!=null)
						{
							partyId = subList.get(31).toString();
						}
					  
					  int roleId = Integer.parseInt((String)subList.get(30));
					  String[] claimantArr=commonBO.getClaimantFlag(Integer.toString(roleId));
					  mapDto.setClaimantFlag(Integer.parseInt(claimantArr[0].trim()));
					  
					  int ExecutantClaimantfromDB=0;
					  if (subList.get(29)!=null && !subList.get(29).toString().equalsIgnoreCase(""))
				   	  {
				        	 ExecutantClaimantfromDB = Integer.parseInt((String)subList.get(29));
				   	  }	
					  if(subList.get(0).toString().equalsIgnoreCase("1"))
					  {
						  
						 
						  if(CommonDeedFlag!=null && "Y".equalsIgnoreCase(CommonDeedFlag)
								  && ExecutantClaimantfromDB==RegInitConstant.CLAIMANT_FLAG_2 ||
							    	ExecutantClaimantfromDB ==RegInitConstant.CLAIMANT_FLAG_4 
							    	||ExecutantClaimantfromDB ==RegInitConstant.CLAIMANT_FLAG_5 ||
							    	ExecutantClaimantfromDB ==RegInitConstant.CLAIMANT_FLAG_6
							    	)
			                		
			               {
								//poAOwnerListForOrg = commonBD.getOwnerPoaDescDisplayForOrg(lang, regNumber);
								mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
								// int ExecutantClaimantfromDB = Integer.parseInt((String)row_list.get(14));
								 System.out.println("ExecutantClaimant" + ExecutantClaimantfromDB );
								form.setExecutantClaimantName(commonBO.getExecutantClaimantNameForPOA(Integer.toString(ExecutantClaimantfromDB)));
			            		 String RoleName = form.getExecutantClaimantName();
			                	 System.out.println("Role Name " +RoleName); 
			                	 String PoaName = null;
			                	// to check hindi and english role 
			                	 String HindiEnglishRole[] = RoleName.split(",");
			                	 String hindesc = HindiEnglishRole[0];
			                	 String EngDesc =HindiEnglishRole[1];
			                	
			                	 if (EngDesc.contains("Authenticated")){
			                		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
			                    		   PoaName =" By(Authenticated PoA Holder)";
		                    		  }
		                    		  else {
		                    			  PoaName =" द्वारा(प्रमाणीकृत  मुख्‍त्‍यार)";
		                    		  }}    
			                	 else {
			                		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
			                			 PoaName =" By(PoA Holder)";
			                		  }
			                		  else {
			                			  PoaName =" द्वारा(मुख्‍त्‍यार आम)";
			                		  }
			                	 }
			                	 
			                    	 String OwnerDetail =null;
			                    	 String   poAOwnerListNew = commonBD.getOwnerDetails(regInitId,partyId,language);
			                      	String poaNameDetails = commonBD.getOwnerPOAnameDetails(partyId);
			                      	OwnerRolePoA=poAOwnerListNew+PoaName+poaNameDetails;
			                      	estampDTO.setAppFirsName(OwnerRolePoA);
			                	    
			                	      
			               }
						
						 else  if(Integer.parseInt(claimantArr[1].trim())==RegInitConstant.POA_HOLDER_FLAG )
					     {
							// poAOwnerListForOrg = commonBD.getOwnerPoaDescDisplayForOrg(lang, regNumber);
					     	 mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
					     	 String  RoleName= null;
					     	// sDTO.setRoleName(sealDAO.getPartyRoleDetails((String)subList.get(3),lang));
					     	    mapDto.setRoleName(commonBO.getRoleNameForPOA((String) subList.get(30)));
					     	    RoleName = mapDto.getRoleName();
					     	   System.out.println("Role Name " +RoleName); 
			                 	
					     	  // to check hindi and english role 
		                  	 String HindiEnglishRole[] = RoleName.split(",");
		                  	 String hindesc = HindiEnglishRole[0];
		                  	 String EngDesc =HindiEnglishRole[1];
		                  	 
		                  	 
		                  	 String PoaName = null;
		                  	 if (EngDesc.contains("Authenticated")){
		                		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
		                    		   PoaName =" By(Authenticated PoA Holder)";
	                    		  }
	                    		  else {
	                    			  PoaName =" द्वारा(प्रमाणीकृत  मुख्‍त्‍यार)";
	                    		  }}    
		                  	 else {
		                  		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
		                  			 PoaName =" By(PoA Holder)";
		                		  }
		                		  else {
		                			  PoaName =" द्वारा(मुख्‍त्‍यार आम)";
		                		  }
						}


					     	   
			                 	 System.out.println("POA NAMe is "+PoaName);
			           	         System.out.println("POA holder flag is "+mapDto.getPoaHolderFlag());
			           	        //System.out.println("PoAOwnerlistdisplay"+poAOwnerListForOrg.get(i));
			        	         
			         	        String OwnerDetail =null;
			         	       String   poAOwnerListNew = commonBD.getOwnerDetails(regInitId,partyId,language);
			                 	String poaNameDetails = commonBD.getOwnerPOAnameDetails(partyId);
			                 	OwnerRolePoA=poAOwnerListNew+PoaName+poaNameDetails;
			                 	estampDTO.setAppFirsName(OwnerRolePoA);
			          	       
					    }
						else{
							 estampDTO.setAppFirsName((String) subList.get(17));
							  
								}
						  
						  
						  estampDTO.setApplicantTypeFlag("1");
						 
						  estampDTO.setAppAddress((String) subList.get(10));
					  }
					  else if(subList.get(0).toString().equalsIgnoreCase("2"))
					  {
						  if(CommonDeedFlag!=null && "Y".equalsIgnoreCase(CommonDeedFlag)
								  && ExecutantClaimantfromDB==RegInitConstant.CLAIMANT_FLAG_2 ||
							    	ExecutantClaimantfromDB ==RegInitConstant.CLAIMANT_FLAG_4 ||
							    	ExecutantClaimantfromDB ==RegInitConstant.CLAIMANT_FLAG_5||
							    	
							    	
							    	ExecutantClaimantfromDB ==RegInitConstant.CLAIMANT_FLAG_6)
							    	
						  {
							  mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
								 //int ExecutantClaimantfromDB = Integer.parseInt((String)subList.get(15));
								 System.out.println("ExecutantClaimant" +ExecutantClaimantfromDB);
								form.setExecutantClaimantName(commonBO.getExecutantClaimantNameForPOA(Integer.toString(ExecutantClaimantfromDB)));
			         		 String RoleName = form.getExecutantClaimantName();
			             	 System.out.println("Role Name " +RoleName); 
			             	// to check hindi and english role 
			            	 String HindiEnglishRole[] = RoleName.split(",");
			            	 String hindesc = HindiEnglishRole[0];
			            	 String EngDesc =HindiEnglishRole[1];
			            	 
			            	 
			            	 String PoaName = null;
			            	 if (EngDesc.contains("Authenticated")){
		                		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
		                    		   PoaName =" By(Authenticated PoA Holder)";
	                    		  }
	                    		  else {
	                    			  PoaName =" द्वारा(प्रमाणीकृत  मुख्‍त्‍यार)";
	                    		  }}    
			            	 else {
			            		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
			            			 PoaName =" By(PoA Holder)";
			            		  }
			            		  else {
			            			  PoaName =" द्वारा(मुख्‍त्‍यार आम)";
			            		  }
			            	 }
			                 	 String OwnerDetail =null;
			                 	 
			                 	String   poAOwnerListNew = commonBD.getOwnerDetails(regInitId,partyId,language);
			                 	String poaNameDetails = commonBD.getOwnerPOAnameDetails(partyId);
			                 	OwnerRolePoA=poAOwnerListNew+PoaName+poaNameDetails;


			                 
			                 	 estampDTO.setAppFirsName(OwnerRolePoA);
				          	       
			             	        
						  }
						  // will deed Authenticaed POA finish - Rahul 
						  else  if(Integer.parseInt(claimantArr[1].trim())==RegInitConstant.POA_HOLDER_FLAG )
						     {
						    	 
						     	 mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
						     	 String  RoleName= null;
						     	// sDTO.setRoleName(sealDAO.getPartyRoleDetails((String)subList.get(3),lang));
						     	  mapDto.setRoleName(commonBO.getRoleNameForPOA((String) subList.get(30)));
						     	    RoleName = mapDto.getRoleName();
						     	   System.out.println("Role Name " +RoleName); 
				                 	
						     	  // to check hindi and english role 
			                  	 String HindiEnglishRole[] = RoleName.split(",");
			                  	 String hindesc = HindiEnglishRole[0];
			                  	 String EngDesc =HindiEnglishRole[1];
			                  	 
			                  	 
			                  	 String PoaName = null;
			                  	 if (EngDesc.contains("Authenticated")){
			                		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
			                    		   PoaName =" By(Authenticated PoA Holder)";
		                    		  }
		                    		  else {
		                    			  PoaName =" द्वारा(प्रमाणीकृत  मुख्‍त्‍यार)";
		                    		  }}    
			                  	 else {
			                  		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
			                  			 PoaName =" By(PoA Holder)";
			                		  }
			                		  else {
			                			  PoaName =" द्वारा(मुख्‍त्‍यार आम )";
			                		  }
							}


						     	   
				                
				        	         
				         	        String OwnerDetail =null;
				         	       String   poAOwnerListNew = commonBD.getOwnerDetails(regInitId,partyId,language);
				                 	String poaNameDetails = commonBD.getOwnerPOAnameDetails(partyId);
				                 	OwnerRolePoA=poAOwnerListNew+PoaName+poaNameDetails;
				                 	
				                 	 estampDTO.setAppFirsName(OwnerRolePoA);
				          	       
						    } 
					  else{
						 String name="";
						 
						 String relations = getRelationshipName((subList.get(27).toString()), language);
						 int age = Integer.parseInt(subList.get(26).toString());
						 if(age>=18){
						 if(subList.get(4)==null||subList.get(4).toString().equalsIgnoreCase(""))
						 {
							name=(String) subList.get(3)+" "+(String) subList.get(5);
						 }
						 else
						 {
							 name=(String) subList.get(3)+" "+(String) subList.get(4) + " "+(String) subList.get(5);
						 }
						 }
						 
						 else{
							 
							 if(language.equalsIgnoreCase("en")){
								 
								 if(subList.get(4)==null||subList.get(4).toString().equalsIgnoreCase(""))
								 {
									name= "(Minor)"+(String) subList.get(3)+" "+(String) subList.get(5)+" "+relations+" "+(String) subList.get(18)+ " By "+(String) subList.get(28);
								 }
								 else
								 {
									 name="(Minor)"+(String) subList.get(3)+" "+(String) subList.get(4) + " "+(String) subList.get(5)+" "+relations+" "+(String) subList.get(18)+ " By "+(String) subList.get(28);
								 } 
								 
							 }
							 else{
								 if(subList.get(4)==null||subList.get(4).toString().equalsIgnoreCase(""))
								 {
									name= "(अवयस्‍क )"+(String) subList.get(3)+" "+(String) subList.get(5)+" "+relations+" "+(String) subList.get(18)+ " द्वारा  "+(String) subList.get(28);
								 }
								 else
								 {
									 name="(अवयस्‍क )"+(String) subList.get(3)+" "+(String) subList.get(4) + " "+(String) subList.get(5)+" "+relations+" "+(String) subList.get(18)+ " द्वारा  "+(String) subList.get(28);
								 } 
								 
								 
							 }
							 
							 
						 }
						  estampDTO.setApplicantTypeFlag("2");
						  estampDTO.setAppFirsName(name);
						  estampDTO.setAppAddress((String) subList.get(10));
					  } 
						  estampDTO.setApplicantTypeFlag("2");
						//  estampDTO.setAppFirsName(name);
						  estampDTO.setAppAddress((String) subList.get(10));  
					  }
					  else
					  {
						 if(subList.get(24)==null||subList.get(24).toString().equalsIgnoreCase(""))
						 {
							 estampDTO.setAppFirsName("--");
						 }
						 else
						 {
							 estampDTO.setAppFirsName(subList.get(24).toString());
						 }
						 estampDTO.setAppAddress((String) subList.get(25));
						  estampDTO.setApplicantTypeFlag("2");
						  
					  }
					  transmgtUtil.executeUpdate(txntableParty1);
				  }
			  }
			  else if(partyList2==null||partyList2.size()==0)
			  {
				  if(partyList1!=null&&partyList1.size()>0)
				  {
					  ArrayList subList=(ArrayList) partyList1.get(0);
					  txntableParty1[2]=(String) subList.get(0);
					  txntableParty1[3]="Y";
					  txntableParty1[4]=(String) subList.get(2);
					  txntableParty1[5]=(String) subList.get(3);
					  txntableParty1[6]=(String) subList.get(4);
					  txntableParty1[7]=(String) subList.get(5);
					  txntableParty1[8]=(String) subList.get(6);
					  txntableParty1[9]=(String) subList.get(7);
					  txntableParty1[10]=(String) subList.get(8);
					  txntableParty1[11]=(String) subList.get(9);
					  txntableParty1[12]=(String) subList.get(10);
					  txntableParty1[13]=(String) subList.get(11);
					  txntableParty1[14]=(String) subList.get(12);
					  txntableParty1[15]=(String) subList.get(13);
					  txntableParty1[16]=(String) subList.get(14);
					  txntableParty1[17]=(String) subList.get(15);
					  txntableParty1[18]=(String) subList.get(16);
					  txntableParty1[19]=(String) subList.get(17);
					  txntableParty1[20]=(String) subList.get(18);
					  txntableParty1[21]=(String) subList.get(19);
					  txntableParty1[22]=(String) subList.get(20);
					  //txntableParty1[23]=(String) subList.get(21);
					  txntableParty1[23]=(String) subList.get(22);
					  txntableParty1[24]="";
					  txntableParty1[25]=(String) subList.get(24);
					  txntableParty1[26]=(String) subList.get(25);
					  txntableParty1[27]=String.valueOf(partyList1.size());
					  transmgtUtil.createPreparedStatement(CommonSQL.INSERT_ESTAMP_TXN_PARTY_DETLS_PARTY1);
					  transmgtUtil.executeUpdate(txntableParty1);
					  estampDTO.setAppPersons(String.valueOf(partyList1.size()));
					  
					  if(subList.get(31)!=null)
						{
							partyId = subList.get(31).toString();
						}
					  
					  int roleId = Integer.parseInt((String)subList.get(30));
					  String[] claimantArr=commonBO.getClaimantFlag(Integer.toString(roleId));
					  mapDto.setClaimantFlag(Integer.parseInt(claimantArr[0].trim()));
					  
					  int ExecutantClaimantfromDB=0;
					  if (subList.get(29)!=null && !subList.get(29).toString().equalsIgnoreCase(""))
				   	  {
				        	 ExecutantClaimantfromDB = Integer.parseInt((String)subList.get(29));
				   	  }	
					  if(subList.get(0).toString().equalsIgnoreCase("1"))
					  {
						  

						  
							 
						  if(CommonDeedFlag!=null && "Y".equalsIgnoreCase(CommonDeedFlag)
								  && ExecutantClaimantfromDB==RegInitConstant.CLAIMANT_FLAG_2 ||
							    	ExecutantClaimantfromDB ==RegInitConstant.CLAIMANT_FLAG_4 
							    	||ExecutantClaimantfromDB ==RegInitConstant.CLAIMANT_FLAG_5 ||
							    	ExecutantClaimantfromDB ==RegInitConstant.CLAIMANT_FLAG_6
							    	)
			                		
			               {
								//poAOwnerListForOrg = commonBD.getOwnerPoaDescDisplayForOrg(lang, regNumber);
								mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
								// int ExecutantClaimantfromDB = Integer.parseInt((String)row_list.get(14));
								 System.out.println("ExecutantClaimant" + ExecutantClaimantfromDB );
								form.setExecutantClaimantName(commonBO.getExecutantClaimantNameForPOA(Integer.toString(ExecutantClaimantfromDB)));
			            		 String RoleName = form.getExecutantClaimantName();
			                	 System.out.println("Role Name " +RoleName); 
			                	 String PoaName = null;
			                	// to check hindi and english role 
			                	 String HindiEnglishRole[] = RoleName.split(",");
			                	 String hindesc = HindiEnglishRole[0];
			                	 String EngDesc =HindiEnglishRole[1];
			                	
			                	 if (EngDesc.contains("Authenticated")){
			                		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
			                    		   PoaName =" By(Authenticated PoA Holder)";
		                    		  }
		                    		  else {
		                    			  PoaName =" द्वारा(प्रमाणीकृत  मुख्‍त्‍यार)";
		                    		  }}    
			                	 else {
			                		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
			                			 PoaName =" By(PoA Holder)";
			                		  }
			                		  else {
			                			  PoaName =" द्वारा(मुख्‍त्‍यार आम)";
			                		  }
			                	 }
			                	 
			                    	 String OwnerDetail =null;
			                    	 String   poAOwnerListNew = commonBD.getOwnerDetails(regInitId,partyId,language);
			                      	String poaNameDetails = commonBD.getOwnerPOAnameDetails(partyId);
			                      	OwnerRolePoA=poAOwnerListNew+PoaName+poaNameDetails;
			                      	estampDTO.setAppFirsName(OwnerRolePoA);
			                	    
			                	      
			               }
						
						 else  if(Integer.parseInt(claimantArr[1].trim())==RegInitConstant.POA_HOLDER_FLAG )
					     {
							// poAOwnerListForOrg = commonBD.getOwnerPoaDescDisplayForOrg(lang, regNumber);
					     	 mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
					     	 String  RoleName= null;
					     	// sDTO.setRoleName(sealDAO.getPartyRoleDetails((String)subList.get(3),lang));
					     	    mapDto.setRoleName(commonBO.getRoleNameForPOA((String) subList.get(30)));
					     	    RoleName = mapDto.getRoleName();
					     	   System.out.println("Role Name " +RoleName); 
			                 	
					     	  // to check hindi and english role 
		                  	 String HindiEnglishRole[] = RoleName.split(",");
		                  	 String hindesc = HindiEnglishRole[0];
		                  	 String EngDesc =HindiEnglishRole[1];
		                  	 
		                  	 
		                  	 String PoaName = null;
		                  	 if (EngDesc.contains("Authenticated")){
		                		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
		                    		   PoaName =" By(Authenticated PoA Holder)";
	                    		  }
	                    		  else {
	                    			  PoaName =" द्वारा(प्रमाणीकृत  मुख्‍त्‍यार)";
	                    		  }}    
		                  	 else {
		                  		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
		                  			 PoaName =" By(PoA Holder)";
		                		  }
		                		  else {
		                			  PoaName =" द्वारा(मुख्‍त्‍यार आम)";
		                		  }
						}


					     	   
			                 	 System.out.println("POA NAMe is "+PoaName);
			           	         System.out.println("POA holder flag is "+mapDto.getPoaHolderFlag());
			           	        //System.out.println("PoAOwnerlistdisplay"+poAOwnerListForOrg.get(i));
			        	         
			         	        String OwnerDetail =null;
			         	       String   poAOwnerListNew = commonBD.getOwnerDetails(regInitId,partyId,language);
			                 	String poaNameDetails = commonBD.getOwnerPOAnameDetails(partyId);
			                 	OwnerRolePoA=poAOwnerListNew+PoaName+poaNameDetails;
			                 	estampDTO.setAppFirsName(OwnerRolePoA);
			          	       
					    }
						else{
							 estampDTO.setAppFirsName((String) subList.get(17));
							  
								}
						  
						  
						  estampDTO.setApplicantTypeFlag("1");
						 
						  estampDTO.setAppAddress((String) subList.get(10));
					  }
					  else if(subList.get(0).toString().equalsIgnoreCase("2"))
					  {
						  if(CommonDeedFlag!=null && "Y".equalsIgnoreCase(CommonDeedFlag)
								  && ExecutantClaimantfromDB==RegInitConstant.CLAIMANT_FLAG_2 ||
							    	ExecutantClaimantfromDB ==RegInitConstant.CLAIMANT_FLAG_4 ||
							    	ExecutantClaimantfromDB ==RegInitConstant.CLAIMANT_FLAG_5
							    	||
							    	ExecutantClaimantfromDB ==RegInitConstant.CLAIMANT_FLAG_6)
							    	
						  {
							  mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
								 //int ExecutantClaimantfromDB = Integer.parseInt((String)subList.get(15));
								 System.out.println("ExecutantClaimant" +ExecutantClaimantfromDB);
								form.setExecutantClaimantName(commonBO.getExecutantClaimantNameForPOA(Integer.toString(ExecutantClaimantfromDB)));
			         		 String RoleName = form.getExecutantClaimantName();
			             	 System.out.println("Role Name " +RoleName); 
			             	// to check hindi and english role 
			            	 String HindiEnglishRole[] = RoleName.split(",");
			            	 String hindesc = HindiEnglishRole[0];
			            	 String EngDesc =HindiEnglishRole[1];
			            	 
			            	 
			            	 String PoaName = null;
			            	 if (EngDesc.contains("Authenticated")){
		                		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
		                    		   PoaName =" By(Authenticated PoA Holder)";
	                    		  }
	                    		  else {
	                    			  PoaName =" द्वारा(प्रमाणीकृत  मुख्‍त्‍यार)";
	                    		  }}    
			            	 else {
			            		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
			            			 PoaName =" By(PoA Holder) ";
			            		  }
			            		  else {
			            			  PoaName =" द्वारा(मुख्‍त्‍यार आम)";
			            		  }
			            	 }
			                 	 String OwnerDetail =null;
			                 	 
			                 	String   poAOwnerListNew = commonBD.getOwnerDetails(regInitId,partyId,language);
			                 	String poaNameDetails = commonBD.getOwnerPOAnameDetails(partyId);
			                 	OwnerRolePoA=poAOwnerListNew+PoaName+poaNameDetails;


			                 
			                 	 estampDTO.setAppFirsName(OwnerRolePoA);
				          	       
			             	        
						  }
						  // will deed Authenticaed POA finish - Rahul 
						  else  if(Integer.parseInt(claimantArr[1].trim())==RegInitConstant.POA_HOLDER_FLAG )
						     {
						    	 
						     	 mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
						     	 String  RoleName= null;
						     	// sDTO.setRoleName(sealDAO.getPartyRoleDetails((String)subList.get(3),lang));
						     	  mapDto.setRoleName(commonBO.getRoleNameForPOA((String) subList.get(30)));
						     	    RoleName = mapDto.getRoleName();
						     	   System.out.println("Role Name " +RoleName); 
				                 	
						     	  // to check hindi and english role 
			                  	 String HindiEnglishRole[] = RoleName.split(",");
			                  	 String hindesc = HindiEnglishRole[0];
			                  	 String EngDesc =HindiEnglishRole[1];
			                  	 
			                  	 
			                  	 String PoaName = null;
			                  	 if (EngDesc.contains("Authenticated")){
			                		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
			                    		   PoaName =" By(Authenticated PoA Holder)";
		                    		  }
		                    		  else {
		                    			  PoaName =" द्वारा(प्रमाणीकृत  मुख्‍त्‍यार)";
		                    		  }}    
			                  	 else {
			                  		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
			                  			 PoaName =" By(PoA Holder)";
			                		  }
			                		  else {
			                			  PoaName =" द्वारा(मुख्‍त्‍यार आम)";
			                		  }
							}


						     	   
				                
				        	         
				         	        String OwnerDetail =null;
				         	       String   poAOwnerListNew = commonBD.getOwnerDetails(regInitId,partyId,language);
				                 	String poaNameDetails = commonBD.getOwnerPOAnameDetails(partyId);
				                 	OwnerRolePoA=poAOwnerListNew+PoaName+poaNameDetails;
				                 	
				                 	 estampDTO.setAppFirsName(OwnerRolePoA);
				          	       
						    }
						  
					  else{
						  String name="";
						 
						 String relations = getRelationshipName((subList.get(27).toString()), language);
						 int age = Integer.parseInt(subList.get(26).toString());
						 
						 if(age>=18){
						 if(subList.get(4)==null||subList.get(4).toString().equalsIgnoreCase(""))
						 {
							name=(String) subList.get(3)+" "+(String) subList.get(5);
						 }
						 else
						 {
							 name=(String) subList.get(3)+" "+(String) subList.get(4) + " "+(String) subList.get(5);
							 
				 
						 }}
						 
						 else{

							 
							 if(language.equalsIgnoreCase("en")){
								 
								 if(subList.get(4)==null||subList.get(4).toString().equalsIgnoreCase(""))
								 {
									name= "(Minor)"+(String) subList.get(3)+" "+(String) subList.get(5)+" "+relations+" "+(String) subList.get(18)+ " By "+(String) subList.get(28);
								 }
								 else
								 {
									 name="(Minor)"+(String) subList.get(3)+" "+(String) subList.get(4) + " "+(String) subList.get(5)+" "+relations+" "+(String) subList.get(18)+ " By "+(String) subList.get(28);
								 } 
								 
							 }
							 else{
								 if(subList.get(4)==null||subList.get(4).toString().equalsIgnoreCase(""))
								 {
									name= "(अवयस्‍क )"+(String) subList.get(3)+" "+(String) subList.get(5)+" "+relations+" "+(String) subList.get(18)+ " By "+(String) subList.get(28);
								 }
								 else
								 {
									 name="(अवयस्‍क )"+(String) subList.get(3)+" "+(String) subList.get(4) + " "+(String) subList.get(5)+" "+relations+" "+(String) subList.get(18)+ " By "+(String) subList.get(28);
								 } 
								 
								 
							 }
							 
							 
					
							 
						 }
						  estampDTO.setApplicantTypeFlag("2");
						  estampDTO.setAppFirsName(name);
						  estampDTO.setAppAddress((String) subList.get(10));
					  }
						  
						  estampDTO.setApplicantTypeFlag("2");
						//  estampDTO.setAppFirsName(name);
						  estampDTO.setAppAddress((String) subList.get(10));
					  }
					  else
					  {
						 if(subList.get(24)==null||subList.get(24).toString().equalsIgnoreCase(""))
						 {
							 estampDTO.setAppFirsName("--");
						 }
						 else
						 {
							 estampDTO.setAppFirsName(subList.get(24).toString());
						 }
						 estampDTO.setAppAddress((String) subList.get(25));
						  estampDTO.setApplicantTypeFlag("2");
						  
					  }
				  }
			  }
			  else
			  {
				  if(partyList1!=null&&partyList1.size()>0)
				  {
					  ArrayList subList=(ArrayList) partyList1.get(0);
					  txntableParty1[2]=(String) subList.get(0);
					  txntableParty1[3]="Y";
					  txntableParty1[4]=(String) subList.get(2);
					  txntableParty1[5]=(String) subList.get(3);
					  txntableParty1[6]=(String) subList.get(4);
					  txntableParty1[7]=(String) subList.get(5);
					  txntableParty1[8]=(String) subList.get(6);
					  txntableParty1[9]=(String) subList.get(7);
					  txntableParty1[10]=(String) subList.get(8);
					  txntableParty1[11]=(String) subList.get(9);
					  txntableParty1[12]=(String) subList.get(10);
					  txntableParty1[13]=(String) subList.get(11);
					  txntableParty1[14]=(String) subList.get(12);
					  txntableParty1[15]=(String) subList.get(13);
					  txntableParty1[16]=(String) subList.get(14);
					  txntableParty1[17]=(String) subList.get(15);
					  txntableParty1[18]=(String) subList.get(16);
					  txntableParty1[19]=(String) subList.get(17);
					  txntableParty1[20]=(String) subList.get(18);
					  txntableParty1[21]=(String) subList.get(19);
					  txntableParty1[22]=(String) subList.get(20);
					  //txntableParty1[23]=(String) subList.get(21);
					  txntableParty1[23]=(String) subList.get(22);
					  txntableParty1[24]="";
					  txntableParty1[25]=(String) subList.get(24);
					  txntableParty1[26]=(String) subList.get(25);
					  txntableParty1[27]=String.valueOf(partyList1.size());
					  transmgtUtil.createPreparedStatement(CommonSQL.INSERT_ESTAMP_TXN_PARTY_DETLS_PARTY1);
					  transmgtUtil.executeUpdate(txntableParty1);
					  estampDTO.setAppPersons(String.valueOf(partyList1.size()));
					  if(subList.get(31)!=null)
						{
							partyId = subList.get(31).toString();
						}
					  
					  int roleId = Integer.parseInt((String)subList.get(30));
					  String[] claimantArr=commonBO.getClaimantFlag(Integer.toString(roleId));
					  mapDto.setClaimantFlag(Integer.parseInt(claimantArr[0].trim()));
					  
					  int ExecutantClaimantfromDB=0;
					  if (subList.get(29)!=null && !subList.get(29).toString().equalsIgnoreCase(""))
				   	  {
				        	 ExecutantClaimantfromDB = Integer.parseInt((String)subList.get(29));
				   	  }	
					  
					  if(subList.get(0).toString().equalsIgnoreCase("1"))
					  {

						  
							 
						  if(CommonDeedFlag!=null && "Y".equalsIgnoreCase(CommonDeedFlag)
								  && ExecutantClaimantfromDB==RegInitConstant.CLAIMANT_FLAG_2 ||
							    	ExecutantClaimantfromDB ==RegInitConstant.CLAIMANT_FLAG_4 
							    	||ExecutantClaimantfromDB ==RegInitConstant.CLAIMANT_FLAG_5 ||
							    	ExecutantClaimantfromDB ==RegInitConstant.CLAIMANT_FLAG_6
							    	)
			                		
			               {
								//poAOwnerListForOrg = commonBD.getOwnerPoaDescDisplayForOrg(lang, regNumber);
								mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
								// int ExecutantClaimantfromDB = Integer.parseInt((String)row_list.get(14));
								 System.out.println("ExecutantClaimant" + ExecutantClaimantfromDB );
								form.setExecutantClaimantName(commonBO.getExecutantClaimantNameForPOA(Integer.toString(ExecutantClaimantfromDB)));
			            		 String RoleName = form.getExecutantClaimantName();
			                	 System.out.println("Role Name " +RoleName); 
			                	 String PoaName = null;
			                	// to check hindi and english role 
			                	 String HindiEnglishRole[] = RoleName.split(",");
			                	 String hindesc = HindiEnglishRole[0];
			                	 String EngDesc =HindiEnglishRole[1];
			                	
			                	 if (EngDesc.contains("Authenticated")){
			                		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
			                    		   PoaName =" By(Authenticated PoA Holder)";
		                    		  }
		                    		  else {
		                    			  PoaName =" द्वारा(प्रमाणीकृत  मुख्‍त्‍यार)";
		                    		  }}    
			                	 else {
			                		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
			                			 PoaName =" By(PoA Holder)";
			                		  }
			                		  else {
			                			  PoaName =" द्वारा(मुख्‍त्‍यार आम)";
			                		  }
			                	 }
			                	 
			                    	 String OwnerDetail =null;
			                    	 String   poAOwnerListNew = commonBD.getOwnerDetails(regInitId,partyId,language);
			                      	String poaNameDetails = commonBD.getOwnerPOAnameDetails(partyId);
			                      	OwnerRolePoA=poAOwnerListNew+PoaName+poaNameDetails;
			                      	estampDTO.setAppFirsName(OwnerRolePoA);
			                	    
			                	      
			               }
						
						 else  if(Integer.parseInt(claimantArr[1].trim())==RegInitConstant.POA_HOLDER_FLAG )
					     {
							// poAOwnerListForOrg = commonBD.getOwnerPoaDescDisplayForOrg(lang, regNumber);
					     	 mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
					     	 String  RoleName= null;
					     	// sDTO.setRoleName(sealDAO.getPartyRoleDetails((String)subList.get(3),lang));
					     	    mapDto.setRoleName(commonBO.getRoleNameForPOA((String) subList.get(30)));
					     	    RoleName = mapDto.getRoleName();
					     	   System.out.println("Role Name " +RoleName); 
			                 	
					     	  // to check hindi and english role 
		                  	 String HindiEnglishRole[] = RoleName.split(",");
		                  	 String hindesc = HindiEnglishRole[0];
		                  	 String EngDesc =HindiEnglishRole[1];
		                  	 
		                  	 
		                  	 String PoaName = null;
		                  	 if (EngDesc.contains("Authenticated")){
		                		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
		                    		   PoaName =" By(Authenticated PoA Holder)";
	                    		  }
	                    		  else {
	                    			  PoaName =" द्वारा(प्रमाणीकृत  मुख्‍त्‍यार)";
	                    		  }}    
		                  	 else {
		                  		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
		                  			 PoaName =" By(PoA Holder) ";
		                		  }
		                		  else {
		                			  PoaName =" द्वारा(मुख्‍त्‍यार आम)";
		                		  }
						}


					     	   
			                 	 System.out.println("POA NAMe is "+PoaName);
			           	         System.out.println("POA holder flag is "+mapDto.getPoaHolderFlag());
			           	        //System.out.println("PoAOwnerlistdisplay"+poAOwnerListForOrg.get(i));
			        	         
			         	        String OwnerDetail =null;
			         	       String   poAOwnerListNew = commonBD.getOwnerDetails(regInitId,partyId,language);
			                 	String poaNameDetails = commonBD.getOwnerPOAnameDetails(partyId);
			                 	OwnerRolePoA=poAOwnerListNew+PoaName+poaNameDetails;
			                 	estampDTO.setAppFirsName(OwnerRolePoA);
			          	       
					    }
						else{
							 estampDTO.setAppFirsName((String) subList.get(17));
							  
								}
						  
						  
						  estampDTO.setApplicantTypeFlag("1");
						 
						  estampDTO.setAppAddress((String) subList.get(10));
					  }
					  else if(subList.get(0).toString().equalsIgnoreCase("2"))
					  {
						  if(CommonDeedFlag!=null && "Y".equalsIgnoreCase(CommonDeedFlag)
								  && ExecutantClaimantfromDB==RegInitConstant.CLAIMANT_FLAG_2 ||
							    	ExecutantClaimantfromDB ==RegInitConstant.CLAIMANT_FLAG_4 ||
							    	ExecutantClaimantfromDB ==RegInitConstant.CLAIMANT_FLAG_5
							    	||
							    	ExecutantClaimantfromDB ==RegInitConstant.CLAIMANT_FLAG_6)
							    	
						  {
							  mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
								 //int ExecutantClaimantfromDB = Integer.parseInt((String)subList.get(15));
								 System.out.println("ExecutantClaimant" +ExecutantClaimantfromDB);
								form.setExecutantClaimantName(commonBO.getExecutantClaimantNameForPOA(Integer.toString(ExecutantClaimantfromDB)));
			         		 String RoleName = form.getExecutantClaimantName();
			             	 System.out.println("Role Name " +RoleName); 
			             	// to check hindi and english role 
			            	 String HindiEnglishRole[] = RoleName.split(",");
			            	 String hindesc = HindiEnglishRole[0];
			            	 String EngDesc =HindiEnglishRole[1];
			            	 
			            	 
			            	 String PoaName = null;
			            	 if (EngDesc.contains("Authenticated")){
		                		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
		                    		   PoaName =" By(Authenticated PoA Holder)";
	                    		  }
	                    		  else {
	                    			  PoaName =" द्वारा(प्रमाणीकृत  मुख्‍त्‍यार)";
	                    		  }}    
			            	 else {
			            		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
			            			 PoaName =" By(PoA Holder)";
			            		  }
			            		  else {
			            			  PoaName =" द्वारा(मुख्‍त्‍यार आम)";
			            		  }
			            	 }
			                 	 String OwnerDetail =null;
			                 	 
			                 	String   poAOwnerListNew = commonBD.getOwnerDetails(regInitId,partyId,language);
			                 	String poaNameDetails = commonBD.getOwnerPOAnameDetails(partyId);
			                 	OwnerRolePoA=poAOwnerListNew+PoaName+poaNameDetails;


			                 
			                 	 estampDTO.setAppFirsName(OwnerRolePoA);
				          	       
			             	        
						  }
						  // will deed Authenticaed POA finish - Rahul 
						  else  if(Integer.parseInt(claimantArr[1].trim())==RegInitConstant.POA_HOLDER_FLAG )
						     {
						    	 
						     	 mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
						     	 String  RoleName= null;
						     	// sDTO.setRoleName(sealDAO.getPartyRoleDetails((String)subList.get(3),lang));
						     	  mapDto.setRoleName(commonBO.getRoleNameForPOA((String) subList.get(30)));
						     	    RoleName = mapDto.getRoleName();
						     	   System.out.println("Role Name " +RoleName); 
				                 	
						     	  // to check hindi and english role 
			                  	 String HindiEnglishRole[] = RoleName.split(",");
			                  	 String hindesc = HindiEnglishRole[0];
			                  	 String EngDesc =HindiEnglishRole[1];
			                  	 
			                  	 
			                  	 String PoaName = null;
			                  	 if (EngDesc.contains("Authenticated")){
			                		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
			                    		   PoaName =" By(Authenticated PoA Holder)";
		                    		  }
		                    		  else {
		                    			  PoaName =" द्वारा(प्रमाणीकृत  मुख्‍त्‍यार)";
		                    		  }}    
			                  	 else {
			                  		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
			                  			 PoaName =" By(PoA Holder)";
			                		  }
			                		  else {
			                			  PoaName =" द्वारा(मुख्‍त्‍यार आम)";
			                		  }
							}


						     	   
				                
				        	         
				         	        String OwnerDetail =null;
				         	       String   poAOwnerListNew = commonBD.getOwnerDetails(regInitId,partyId,language);
				                 	String poaNameDetails = commonBD.getOwnerPOAnameDetails(partyId);
				                 	OwnerRolePoA=poAOwnerListNew+PoaName+poaNameDetails;
				                 	
				                 	 estampDTO.setAppFirsName(OwnerRolePoA);
				          	       
						    }
					  else{
						  String name="";
						 
						 String relations = getRelationshipName((subList.get(27).toString()), language);
						 int age = Integer.parseInt(subList.get(26).toString());
						 
						 if(age>=18){
						 if(subList.get(4)==null||subList.get(4).toString().equalsIgnoreCase(""))
						 {
							name=(String) subList.get(3)+" "+(String) subList.get(5);
						 }
						 else
						 {
							 name=(String) subList.get(3)+" "+(String) subList.get(4) + " "+(String) subList.get(5);
						 }
						 }
						 
						 else{
							 


							 
							 if(language.equalsIgnoreCase("en")){
								 
								 if(subList.get(4)==null||subList.get(4).toString().equalsIgnoreCase(""))
								 {
									name= "(Minor)"+(String) subList.get(3)+" "+(String) subList.get(5)+" "+relations+" "+(String) subList.get(18)+ " By "+(String) subList.get(28);
								 }
								 else
								 {
									 name="(Minor)"+(String) subList.get(3)+" "+(String) subList.get(4) + " "+(String) subList.get(5)+" "+relations+" "+(String) subList.get(18)+ " By "+(String) subList.get(28);
								 } 
								 
							 }
							 else{
								 if(subList.get(4)==null||subList.get(4).toString().equalsIgnoreCase(""))
								 {
									name= "(अवयस्‍क)"+(String) subList.get(3)+" "+(String) subList.get(5)+" "+relations+" "+(String) subList.get(18)+ " द्वारा  "+(String) subList.get(28);
								 }
								 else
								 {
									 name="(अवयस्‍क)"+(String) subList.get(3)+" "+(String) subList.get(4) + " "+(String) subList.get(5)+" "+relations+" "+(String) subList.get(18)+ " द्वारा  "+(String) subList.get(28);
								 } 
								 
								 
							 }
							 
							 
						 
							 
						 	 
						 }
						  estampDTO.setApplicantTypeFlag("2");
						  estampDTO.setAppFirsName(name);
						  estampDTO.setAppAddress((String) subList.get(10));
					  } 
						  
						  estampDTO.setApplicantTypeFlag("2");
						 // estampDTO.setAppFirsName(name);
						  estampDTO.setAppAddress((String) subList.get(10));
					  }
					  else
					  {
						 if(subList.get(24)== null||subList.get(24).toString().equalsIgnoreCase(""))
						 {
							 estampDTO.setAppFirsName("--");
						 }
						 else
						 {
							 estampDTO.setAppFirsName(subList.get(24).toString());
						 }
						 estampDTO.setAppAddress((String) subList.get(25));
						  estampDTO.setApplicantTypeFlag("2");
						  
					  }
				  }
				  if(partyList2!=null&&partyList2.size()>0)
				  {
					  transmgtUtil.createStatement();
					  String number50 = transmgtUtil.executeQry(CommonSQL.SELECT_SEQUENCE3);
					  txntableParty1[0]=number50;
					  ArrayList subList=(ArrayList) partyList2.get(0);
					  txntableParty1[2]=(String) subList.get(0);
					  txntableParty1[3]="N";
					  txntableParty1[4]=(String) subList.get(2);
					  txntableParty1[5]=(String) subList.get(3);
					  txntableParty1[6]=(String) subList.get(4);
					  txntableParty1[7]=(String) subList.get(5);
					  txntableParty1[8]=(String) subList.get(6);
					  txntableParty1[9]=(String) subList.get(7);
					  txntableParty1[10]=(String) subList.get(8);
					  txntableParty1[11]=(String) subList.get(9);
					  txntableParty1[12]=(String) subList.get(10);
					  txntableParty1[13]=(String) subList.get(11);
					  txntableParty1[14]=(String) subList.get(12);
					  txntableParty1[15]=(String) subList.get(13);
					  txntableParty1[16]=(String) subList.get(14);
					  txntableParty1[17]=(String) subList.get(15);
					  txntableParty1[18]=(String) subList.get(16);
					  txntableParty1[19]=(String) subList.get(17);
					  txntableParty1[20]=(String) subList.get(18);
					  txntableParty1[21]=(String) subList.get(19);
					  txntableParty1[22]=(String) subList.get(20);
					  //txntableParty1[23]=(String) subList.get(21);
					  txntableParty1[23]=(String) subList.get(22);
					  txntableParty1[24]="";
					  txntableParty1[25]=(String) subList.get(24);
					  txntableParty1[26]=(String) subList.get(25);
					  txntableParty1[27]=String.valueOf(partyList2.size());
					  transmgtUtil.createPreparedStatement(CommonSQL.INSERT_ESTAMP_TXN_PARTY_DETLS_PARTY1);
					  transmgtUtil.executeUpdate(txntableParty1);
					  estampDTO.setPartyPersons(String.valueOf(partyList2.size()));
					  
					  if(subList.get(31)!=null)
						{
							partyId = subList.get(31).toString();
						}
					  
					  int roleId = Integer.parseInt((String)subList.get(30));
					  String[] claimantArr=commonBO.getClaimantFlag(Integer.toString(roleId));
					  mapDto.setClaimantFlag(Integer.parseInt(claimantArr[0].trim()));
					  
					  int ExecutantClaimantfromDB=0;
					  if (subList.get(29)!=null && !subList.get(29).toString().equalsIgnoreCase(""))
				   	  {
				        	 ExecutantClaimantfromDB = Integer.parseInt((String)subList.get(29));
				   	  }	
					  if(subList.get(0).toString().equalsIgnoreCase("1"))
					  {

						  
							 
						  if(CommonDeedFlag!=null && "Y".equalsIgnoreCase(CommonDeedFlag)
								  && ExecutantClaimantfromDB==RegInitConstant.CLAIMANT_FLAG_2 ||
							    	ExecutantClaimantfromDB ==RegInitConstant.CLAIMANT_FLAG_4 
							    	||ExecutantClaimantfromDB ==RegInitConstant.CLAIMANT_FLAG_5 ||
							    	ExecutantClaimantfromDB ==RegInitConstant.CLAIMANT_FLAG_6
							    	)
			                		
			               {
								//poAOwnerListForOrg = commonBD.getOwnerPoaDescDisplayForOrg(lang, regNumber);
								mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
								// int ExecutantClaimantfromDB = Integer.parseInt((String)row_list.get(14));
								 System.out.println("ExecutantClaimant" + ExecutantClaimantfromDB );
								form.setExecutantClaimantName(commonBO.getExecutantClaimantNameForPOA(Integer.toString(ExecutantClaimantfromDB)));
			            		 String RoleName = form.getExecutantClaimantName();
			                	 System.out.println("Role Name " +RoleName); 
			                	 String PoaName = null;
			                	// to check hindi and english role 
			                	 String HindiEnglishRole[] = RoleName.split(",");
			                	 String hindesc = HindiEnglishRole[0];
			                	 String EngDesc =HindiEnglishRole[1];
			                	
			                	 if (EngDesc.contains("Authenticated")){
			                		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
			                    		   PoaName =" By(Authenticated PoA Holder)";
		                    		  }
		                    		  else {
		                    			  PoaName =" द्वारा(प्रमाणीकृत  मुख्‍त्‍यार  )";
		                    		  }}    
			                	 else {
			                		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
			                			 PoaName =" By(PoA Holder)";
			                		  }
			                		  else {
			                			  PoaName =" द्वारा(मुख्‍त्‍यार आम)";
			                		  }
			                	 }
			                	 
			                    	 String OwnerDetail =null;
			                    	 String   poAOwnerListNew = commonBD.getOwnerDetails(regInitId,partyId,language);
			                      	String poaNameDetails = commonBD.getOwnerPOAnameDetails(partyId);
			                      	OwnerRolePoA=poAOwnerListNew+PoaName+poaNameDetails;
			                      	estampDTO.setPartyFirsName(OwnerRolePoA);
			                	    
			                	      
			               }
						
						 else  if(Integer.parseInt(claimantArr[1].trim())==RegInitConstant.POA_HOLDER_FLAG )
					     {
							// poAOwnerListForOrg = commonBD.getOwnerPoaDescDisplayForOrg(lang, regNumber);
					     	 mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
					     	 String  RoleName= null;
					     	// sDTO.setRoleName(sealDAO.getPartyRoleDetails((String)subList.get(3),lang));
					     	    mapDto.setRoleName(commonBO.getRoleNameForPOA((String) subList.get(30)));
					     	    RoleName = mapDto.getRoleName();
					     	   System.out.println("Role Name " +RoleName); 
			                 	
					     	  // to check hindi and english role 
		                  	 String HindiEnglishRole[] = RoleName.split(",");
		                  	 String hindesc = HindiEnglishRole[0];
		                  	 String EngDesc =HindiEnglishRole[1];
		                  	 
		                  	 
		                  	 String PoaName = null;
		                  	 if (EngDesc.contains("Authenticated")){
		                		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
		                    		   PoaName =" By(Authenticated PoA Holder)";
	                    		  }
	                    		  else {
	                    			  PoaName =" द्वारा(प्रमाणीकृत  मुख्‍त्‍यार)";
	                    		  }}    
		                  	 else {
		                  		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
		                  			 PoaName =" By(PoA Holder)";
		                		  }
		                		  else {
		                			  PoaName =" द्वारा(मुख्‍त्‍यार आम)";
		                		  }
						}


					     	   
			                 	 System.out.println("POA NAMe is "+PoaName);
			           	         System.out.println("POA holder flag is "+mapDto.getPoaHolderFlag());
			           	        //System.out.println("PoAOwnerlistdisplay"+poAOwnerListForOrg.get(i));
			        	         
			         	        String OwnerDetail =null;
			         	       String   poAOwnerListNew = commonBD.getOwnerDetails(regInitId,partyId,language);
			                 	String poaNameDetails = commonBD.getOwnerPOAnameDetails(partyId);
			                 	OwnerRolePoA=poAOwnerListNew+PoaName+poaNameDetails;
			                 	estampDTO.setPartyFirsName(OwnerRolePoA);
			          	       
					    }
						else{
							estampDTO.setPartyFirsName((String) subList.get(17));
							  
								}
						  
						  
						  estampDTO.setPartyTypeFlag("1");
						 
						  estampDTO.setPartyAddress((String) subList.get(10));
					  
						  
						
					  }
					  else if(subList.get(0).toString().equalsIgnoreCase("2"))
					  {
						  if(CommonDeedFlag!=null && "Y".equalsIgnoreCase(CommonDeedFlag)
								  && ExecutantClaimantfromDB==RegInitConstant.CLAIMANT_FLAG_2 ||
							    	ExecutantClaimantfromDB ==RegInitConstant.CLAIMANT_FLAG_4 ||
							    	ExecutantClaimantfromDB ==RegInitConstant.CLAIMANT_FLAG_5
							    	||
							    	ExecutantClaimantfromDB ==RegInitConstant.CLAIMANT_FLAG_6)
							    	
						  {
							  mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
								 //int ExecutantClaimantfromDB = Integer.parseInt((String)subList.get(15));
								 System.out.println("ExecutantClaimant" +ExecutantClaimantfromDB);
								form.setExecutantClaimantName(commonBO.getExecutantClaimantNameForPOA(Integer.toString(ExecutantClaimantfromDB)));
			         		 String RoleName = form.getExecutantClaimantName();
			             	 System.out.println("Role Name " +RoleName); 
			             	// to check hindi and english role 
			            	 String HindiEnglishRole[] = RoleName.split(",");
			            	 String hindesc = HindiEnglishRole[0];
			            	 String EngDesc =HindiEnglishRole[1];
			            	 
			            	 
			            	 String PoaName = null;
			            	 if (EngDesc.contains("Authenticated")){
		                		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
		                    		   PoaName =" By(Authenticated PoA Holder)";
	                    		  }
	                    		  else {
	                    			  PoaName =" द्वारा(प्रमाणीकृत  मुख्‍त्‍यार)";
	                    		  }}    
			            	 else {
			            		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
			            			 PoaName =" By(PoA Holder)";
			            		  }
			            		  else {
			            			  PoaName =" द्वारा(मुख्‍त्‍यार आम)";
			            		  }
			            	 }
			                 	 String OwnerDetail =null;
			                 	 
			                 	String   poAOwnerListNew = commonBD.getOwnerDetails(regInitId,partyId,language);
			                 	String poaNameDetails = commonBD.getOwnerPOAnameDetails(partyId);
			                 	OwnerRolePoA=poAOwnerListNew+PoaName+poaNameDetails;


			                 
			                 	estampDTO.setPartyFirsName(OwnerRolePoA);
				          	       
			             	        
						  }
						  // will deed Authenticaed POA finish - Rahul 
						  else  if(Integer.parseInt(claimantArr[1].trim())==RegInitConstant.POA_HOLDER_FLAG )
						     {
						    	 
						     	 mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
						     	 String  RoleName= null;
						     	// sDTO.setRoleName(sealDAO.getPartyRoleDetails((String)subList.get(3),lang));
						     	  mapDto.setRoleName(commonBO.getRoleNameForPOA((String) subList.get(30)));
						     	    RoleName = mapDto.getRoleName();
						     	   System.out.println("Role Name " +RoleName); 
				                 	
						     	  // to check hindi and english role 
			                  	 String HindiEnglishRole[] = RoleName.split(",");
			                  	 String hindesc = HindiEnglishRole[0];
			                  	 String EngDesc =HindiEnglishRole[1];
			                  	 
			                  	 
			                  	 String PoaName = null;
			                  	 if (EngDesc.contains("Authenticated")){ 
			                  		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
		                    		   PoaName =" By(Authenticated PoA Holder) ";
	                    		  }
	                    		  else {
	                    			  PoaName =" द्वारा(प्रमाणीकृत  मुख्‍त्‍यार)";
	                    		  }}    
			                  	 else {
			                  		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
			                  			 PoaName =" By(PoA Holder)";
			                		  }
			                		  else {
			                			  PoaName =" द्वारा(मुख्‍त्‍यार आम )";
			                		  }
							}


						     	   
				                
				        	         
				         	        String OwnerDetail =null;
				         	       String   poAOwnerListNew = commonBD.getOwnerDetails(regInitId,partyId,language);
				                 	String poaNameDetails = commonBD.getOwnerPOAnameDetails(partyId);
				                 	OwnerRolePoA=poAOwnerListNew+PoaName+poaNameDetails;
				                 	
				                 	estampDTO.setPartyFirsName(OwnerRolePoA);
				          	       
						    } 
					  else{
						 String name="";
						 
						 String relations = getRelationshipName((subList.get(27).toString()), language);
						 int age = Integer.parseInt(subList.get(26).toString());
						 
						 if(age>=18){
						 if(subList.get(4)==null||subList.get(4).toString().equalsIgnoreCase(""))
						 {
							name=(String) subList.get(3)+" "+(String) subList.get(5);
						 }
						 else
						 {
							 name=(String) subList.get(3)+" "+(String) subList.get(4) + " "+(String) subList.get(5);
						 }}
						 
						 else{


							 
							 if(language.equalsIgnoreCase("en")){
								 
								 if(subList.get(4)==null||subList.get(4).toString().equalsIgnoreCase(""))
								 {
									name= "(Minor)"+(String) subList.get(3)+" "+(String) subList.get(5)+" "+relations+" "+(String) subList.get(18)+ " By "+(String) subList.get(28);
								 }
								 else
								 {
									 name="(Minor)"+(String) subList.get(3)+" "+(String) subList.get(4) + " "+(String) subList.get(5)+" "+relations+" "+(String) subList.get(18)+ " By "+(String) subList.get(28);
								 } 
								 
							 }
							 else{
								 if(subList.get(4)==null||subList.get(4).toString().equalsIgnoreCase(""))
								 {
									name= "(अवयस्‍क )"+(String) subList.get(3)+" "+(String) subList.get(5)+" "+relations+" "+(String) subList.get(18)+ " द्वारा  "+(String) subList.get(28);
								 }
								 else
								 {
									 name="(अवयस्‍क)"+(String) subList.get(3)+" "+(String) subList.get(4) + " "+(String) subList.get(5)+" "+relations+" "+(String) subList.get(18)+ " द्वारा  "+(String) subList.get(28);
								 } 
								 
								 
							 }
							 
							 
						 
							 
						  
							 
						 }
						  estampDTO.setPartyTypeFlag("2");
						  estampDTO.setPartyFirsName(name);
						  estampDTO.setPartyAddress((String) subList.get(10));
					  }
						  
						  estampDTO.setPartyTypeFlag("2");
						  //estampDTO.setPartyFirsName(name);
						  estampDTO.setPartyAddress((String) subList.get(10));
					  }
					  else
					  {
						 if(subList.get(24)==null||subList.get(24).toString().equalsIgnoreCase(""))
						 {
							 estampDTO.setPartyFirsName("--");
						 }
						 else
						 {
							 estampDTO.setPartyFirsName(subList.get(24).toString());
						 }
						 estampDTO.setPartyAddress((String) subList.get(25));
						  estampDTO.setPartyTypeFlag("2");
						  
					  }
				  }
			  }
		  }
		  else
		  {		  
		 transmgtUtil.createPreparedStatement(CommonSQL.GET_PARTY_DETAILS_updated);
		String partyArray[]=new String[4]; 
		partyArray[0]=regInitId;
		partyArray[1]="1";	
		partyArray[2]=regInitId;
		partyArray[3]="1";
		 ArrayList partyList1=transmgtUtil.executeQuery(partyArray);
		  
		  if(partyList1!=null&&partyList1.size()>0)
		  {
			  ArrayList subList=(ArrayList) partyList1.get(0);
			  txntableParty1[2]=(String) subList.get(0);
			  txntableParty1[3]="Y";
			  txntableParty1[4]=(String) subList.get(2);
			  txntableParty1[5]=(String) subList.get(3);
			  txntableParty1[6]=(String) subList.get(4);
			  txntableParty1[7]=(String) subList.get(5);
			  txntableParty1[8]=(String) subList.get(6);
			  txntableParty1[9]=(String) subList.get(7);
			  txntableParty1[10]=(String) subList.get(8);
			  txntableParty1[11]=(String) subList.get(9);
			  txntableParty1[12]=(String) subList.get(10);
			  txntableParty1[13]=(String) subList.get(11);
			  txntableParty1[14]=(String) subList.get(12);
			  txntableParty1[15]=(String) subList.get(13);
			  txntableParty1[16]=(String) subList.get(14);
			  txntableParty1[17]=(String) subList.get(15);
			  txntableParty1[18]=(String) subList.get(16);
			  txntableParty1[19]=(String) subList.get(17);
			  txntableParty1[20]=(String) subList.get(18);
			  txntableParty1[21]=(String) subList.get(19);
			  txntableParty1[22]=(String) subList.get(20);
			 // txntableParty1[23]=(String) subList.get(21);
			  txntableParty1[23]=(String) subList.get(22);
			  txntableParty1[24]="";
			  txntableParty1[25]=(String) subList.get(24);
			  txntableParty1[26]=(String) subList.get(25);
			  txntableParty1[27]=String.valueOf(partyList1.size());
			  transmgtUtil.createPreparedStatement(CommonSQL.INSERT_ESTAMP_TXN_PARTY_DETLS_PARTY1);
			  transmgtUtil.executeUpdate(txntableParty1);
			  estampDTO.setAppPersons(String.valueOf(partyList1.size()));
			  
			  if(subList.get(0).equals("2")){  
			  estampDTO.setAge(Integer.parseInt((String)subList.get(26)));
			  estampDTO.setRelation(getRelationshipName((subList.get(27).toString()), language));
			  if((String)subList.get(28)!=null){
			  estampDTO.setGuardian((String)subList.get(28));}}
			
			  else{
				  estampDTO.setAge(0);
				  estampDTO.setRelation("");
				  estampDTO.setGuardian("");
				  
			  }
			  
				
				 if(subList.get(31)!=null)
					{
						partyId = subList.get(31).toString();
					}
				  
				  int roleId = Integer.parseInt((String)subList.get(30));
				  String[] claimantArr=commonBO.getClaimantFlag(Integer.toString(roleId));
				  mapDto.setClaimantFlag(Integer.parseInt(claimantArr[0].trim()));
				  
				  int ExecutantClaimantfromDB=0;
				  if (subList.get(29)!=null && !subList.get(29).toString().equalsIgnoreCase(""))
			   	  {
			        	 ExecutantClaimantfromDB = Integer.parseInt((String)subList.get(29));
			   	  }	
			  if(subList.get(0).toString().equalsIgnoreCase("1"))
			  {

				  

				  
					 
				  if(CommonDeedFlag!=null && "Y".equalsIgnoreCase(CommonDeedFlag)
						  && ExecutantClaimantfromDB==RegInitConstant.CLAIMANT_FLAG_2 ||
					    	ExecutantClaimantfromDB ==RegInitConstant.CLAIMANT_FLAG_4 
					    	||ExecutantClaimantfromDB ==RegInitConstant.CLAIMANT_FLAG_5 ||
					    	ExecutantClaimantfromDB ==RegInitConstant.CLAIMANT_FLAG_6
					    	)
	                		
	               {
						//poAOwnerListForOrg = commonBD.getOwnerPoaDescDisplayForOrg(lang, regNumber);
						mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
						// int ExecutantClaimantfromDB = Integer.parseInt((String)row_list.get(14));
						 System.out.println("ExecutantClaimant" + ExecutantClaimantfromDB );
						form.setExecutantClaimantName(commonBO.getExecutantClaimantNameForPOA(Integer.toString(ExecutantClaimantfromDB)));
	            		 String RoleName = form.getExecutantClaimantName();
	                	 System.out.println("Role Name " +RoleName); 
	                	 String PoaName = null;
	                	// to check hindi and english role 
	                	 String HindiEnglishRole[] = RoleName.split(",");
	                	 String hindesc = HindiEnglishRole[0];
	                	 String EngDesc =HindiEnglishRole[1];
	                	
	                	 if (EngDesc.contains("Authenticated")){ if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
                  		   PoaName =" By(Authenticated PoA Holder)";
               		  }
               		  else {
               			  PoaName =" द्वारा(प्रमाणीकृत  मुख्‍त्‍यार)";
               		  }}    
	                	 else {
	                		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
	                			 PoaName =" By(PoA Holder)";
	                		  }
	                		  else {
	                			  PoaName =" द्वारा(मुख्‍त्‍यार आम)";
	                		  }
	                	 }
	                	 
	                    	 String OwnerDetail =null;
	                    	 String   poAOwnerListNew = commonBD.getOwnerDetails(regInitId,partyId,language);
	                      	String poaNameDetails = commonBD.getOwnerPOAnameDetails(partyId);
	                      	OwnerRolePoA=poAOwnerListNew+PoaName+poaNameDetails;
	                      	estampDTO.setAppFirsName(OwnerRolePoA);
	                	    
	                	      
	               }
				
				 else  if(Integer.parseInt(claimantArr[1].trim())==RegInitConstant.POA_HOLDER_FLAG )
			     {
					// poAOwnerListForOrg = commonBD.getOwnerPoaDescDisplayForOrg(lang, regNumber);
			     	 mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
			     	 String  RoleName= null;
			     	// sDTO.setRoleName(sealDAO.getPartyRoleDetails((String)subList.get(3),lang));
			     	    mapDto.setRoleName(commonBO.getRoleNameForPOA((String) subList.get(30)));
			     	    RoleName = mapDto.getRoleName();
			     	   System.out.println("Role Name " +RoleName); 
	                 	
			     	  // to check hindi and english role 
                  	 String HindiEnglishRole[] = RoleName.split(",");
                  	 String hindesc = HindiEnglishRole[0];
                  	 String EngDesc =HindiEnglishRole[1];
                  	 
                  	 
                  	 String PoaName = null;
                  	 if (EngDesc.contains("Authenticated")){ 
                  		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
              		   PoaName =" By(Authenticated PoA Holder)";
           		  }
           		  else {
           			  PoaName =" द्वारा(प्रमाणीकृत  मुख्‍त्‍यार)";
           		  }}    
                  	 else {
                  		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
                  			 PoaName =" By(PoA Holder)";
                		  }
                		  else {
                			  PoaName =" द्वारा(मुख्‍त्‍यार आम)";
                		  }
				}


			     	   
	                 	 System.out.println("POA NAMe is "+PoaName);
	           	         System.out.println("POA holder flag is "+mapDto.getPoaHolderFlag());
	           	        //System.out.println("PoAOwnerlistdisplay"+poAOwnerListForOrg.get(i));
	        	         
	         	        String OwnerDetail =null;
	         	       String   poAOwnerListNew = commonBD.getOwnerDetails(regInitId,partyId,language);
	                 	String poaNameDetails = commonBD.getOwnerPOAnameDetails(partyId);
	                 	OwnerRolePoA=poAOwnerListNew+PoaName+poaNameDetails;
	                 	estampDTO.setAppFirsName(OwnerRolePoA);
	          	       
			    }
				else{
					 estampDTO.setAppFirsName((String) subList.get(17));
					  
						}
				  
				  
				  estampDTO.setApplicantTypeFlag("1");
				 
				  estampDTO.setAppAddress((String) subList.get(10));
			  
			  
			  }
			  else if(subList.get(0).toString().equalsIgnoreCase("2"))
			  {
				  if(CommonDeedFlag!=null && "Y".equalsIgnoreCase(CommonDeedFlag)
						  && ExecutantClaimantfromDB==RegInitConstant.CLAIMANT_FLAG_2 ||
					    	ExecutantClaimantfromDB ==RegInitConstant.CLAIMANT_FLAG_4 ||
					    	ExecutantClaimantfromDB ==RegInitConstant.CLAIMANT_FLAG_5
					    	||
					    	ExecutantClaimantfromDB ==RegInitConstant.CLAIMANT_FLAG_6)
					    	
				  {
					  mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
						 //int ExecutantClaimantfromDB = Integer.parseInt((String)subList.get(15));
						 System.out.println("ExecutantClaimant" +ExecutantClaimantfromDB);
						form.setExecutantClaimantName(commonBO.getExecutantClaimantNameForPOA(Integer.toString(ExecutantClaimantfromDB)));
	         		 String RoleName = form.getExecutantClaimantName();
	             	 System.out.println("Role Name " +RoleName); 
	             	// to check hindi and english role 
	            	 String HindiEnglishRole[] = RoleName.split(",");
	            	 String hindesc = HindiEnglishRole[0];
	            	 String EngDesc =HindiEnglishRole[1];
	            	 
	            	 
	            	 String PoaName = null;
	            	 if (EngDesc.contains("Authenticated")){ if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
              		   PoaName =" By(Authenticated PoA Holder)";
           		  }
           		  else {
           			  PoaName =" द्वारा(प्रमाणीकृत  मुख्‍त्‍यार)";
           		  }}    
	            	 else {
	            		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
	            			 PoaName =" By(PoA Holder)";
	            		  }
	            		  else {
	            			  PoaName =" द्वारा(मुख्‍त्‍यार आम)";
	            		  }
	            	 }
	                 	 String OwnerDetail =null;
	                 	 
	                 	String   poAOwnerListNew = commonBD.getOwnerDetails(regInitId,partyId,language);
	                 	String poaNameDetails = commonBD.getOwnerPOAnameDetails(partyId);
	                 	OwnerRolePoA=poAOwnerListNew+PoaName+poaNameDetails;


	                 
	                 	estampDTO.setAppFirsName(OwnerRolePoA);
		          	       
	             	        
				  }
				  // will deed Authenticaed POA finish - Rahul 
				  else  if(Integer.parseInt(claimantArr[1].trim())==RegInitConstant.POA_HOLDER_FLAG )
				     {
				    	 
				     	 mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
				     	 String  RoleName= null;
				     	// sDTO.setRoleName(sealDAO.getPartyRoleDetails((String)subList.get(3),lang));
				     	  mapDto.setRoleName(commonBO.getRoleNameForPOA((String) subList.get(30)));
				     	    RoleName = mapDto.getRoleName();
				     	   System.out.println("Role Name " +RoleName); 
		                 	
				     	  // to check hindi and english role 
	                  	 String HindiEnglishRole[] = RoleName.split(",");
	                  	 String hindesc = HindiEnglishRole[0];
	                  	 String EngDesc =HindiEnglishRole[1];
	                  	 
	                  	 
	                  	 String PoaName = null;
	                  	 if (EngDesc.contains("Authenticated")){ if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
                  		   PoaName =" By(Authenticated PoA Holder)";
               		  }
               		  else {
               			  PoaName =" द्वारा(प्रमाणीकृत  मुख्‍त्‍यार)";
               		  }}    
	                  	 else {
	                  		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
	                  			 PoaName =" By(PoA Holder)";
	                		  }
	                		  else {
	                			  PoaName =" द्वारा(मुख्‍त्‍यार आम )";
	                		  }
					}


				     	   
		                
		        	         
		         	        String OwnerDetail =null;
		         	       String   poAOwnerListNew = commonBD.getOwnerDetails(regInitId,partyId,language);
		                 	String poaNameDetails = commonBD.getOwnerPOAnameDetails(partyId);
		                 	OwnerRolePoA=poAOwnerListNew+PoaName+poaNameDetails;
		                 	
		                 	estampDTO.setAppFirsName(OwnerRolePoA);
		          	       
				    }   
			  else{
				 String name="";
				 if(subList.get(4)==null||subList.get(4).toString().equalsIgnoreCase(""))
				 {
					 if(estampDTO.getAge()>=18){
					 
					name=(String) subList.get(3)+" "+(String) subList.get(5);
					 }
					 
					 else{
						 if(language.equalsIgnoreCase("en")){
						 name="(Minor)" +(String) subList.get(3)+" "+(String) subList.get(5)+" "+estampDTO.getRelation()+" "+(String) subList.get(18)+" by "+estampDTO.getGuardian(); 
						 }
						 else{
							 
							 name="(अवयस्‍क)" +(String) subList.get(3)+" "+(String) subList.get(5)+" "+estampDTO.getRelation()+" "+(String) subList.get(18)+" द्वारा  "+estampDTO.getGuardian(); 
	 
						 }
						// name =  "(Minor) "+name+" "+relations+" "+ regForm.getFatherNameTrns()+" by "+regForm.getGuardianTrans();
					 }
				 }
				 else
				 {
					 if(estampDTO.getAge()<18){
						 if(language.equalsIgnoreCase("en")){
						 name="(Minor)" +(String) subList.get(3)+" "+(String) subList.get(4) + " "+(String) subList.get(5)+" "+estampDTO.getRelation()+" "+(String) subList.get(18)+" by "+estampDTO.getGuardian(); 
						 }
						 
						 else{
							 name="(अवयस्‍क)" +(String) subList.get(3)+" "+(String) subList.get(4) + " "+(String) subList.get(5)+" "+estampDTO.getRelation()+" "+(String) subList.get(18)+" द्वारा "+estampDTO.getGuardian(); 
	 
							 
						 }
						 
						 }
					 
					 else{
					 name=(String) subList.get(3)+" "+(String) subList.get(4) + " "+(String) subList.get(5);}
				 }
				  estampDTO.setApplicantTypeFlag("2");
				  estampDTO.setAppFirsName(name);
				  estampDTO.setAppAddress((String) subList.get(10));
			  } 
				  
				  estampDTO.setApplicantTypeFlag("2");
				  estampDTO.setAppAddress((String) subList.get(10));
			  
			  }
			  else
			  {
				 if(subList.get(24)==null||subList.get(24).toString().equalsIgnoreCase(""))
				 {
					 estampDTO.setAppFirsName("--");
				 }
				 else
				 {
					 estampDTO.setAppFirsName(subList.get(24).toString());
				 }
				 estampDTO.setAppAddress((String) subList.get(25));
				  estampDTO.setApplicantTypeFlag("2");
				  
			  }
			  
		  }
		  
		 
		  if(deedId.equalsIgnoreCase("1003"))
		  {
			 String numbr9=transmgtUtil.executeQry(CommonSQL.SELECT_SEQUENCE3);
			 	
				partyArray[0]=regInitId;
				partyArray[1]="2";	
				partyArray[2]=regInitId;
				partyArray[3]="2";
				txntableParty1[0] = numbr9;
				  txntableParty1[1] = estmTxnId;
				  transmgtUtil.createPreparedStatement(CommonSQL.GET_PARTY_DETAILS_updated);
				 partyList1=transmgtUtil.executeQuery(partyArray);
				  
				  if(partyList1!=null&&partyList1.size()>0)
				  {
					  ArrayList subList=(ArrayList) partyList1.get(0);
					  txntableParty1[2]=(String) subList.get(0);
					  txntableParty1[3]="A";
					  txntableParty1[4]=(String) subList.get(2);
					  txntableParty1[5]=(String) subList.get(3);
					  txntableParty1[6]=(String) subList.get(4);
					  txntableParty1[7]=(String) subList.get(5);
					  txntableParty1[8]=(String) subList.get(6);
					  txntableParty1[9]=(String) subList.get(7);
					  txntableParty1[10]=(String) subList.get(8);
					  txntableParty1[11]=(String) subList.get(9);
					  txntableParty1[12]=(String) subList.get(10);
					  txntableParty1[13]=(String) subList.get(11);
					  txntableParty1[14]=(String) subList.get(12);
					  txntableParty1[15]=(String) subList.get(13);
					  txntableParty1[16]=(String) subList.get(14);
					  txntableParty1[17]=(String) subList.get(15);
					  txntableParty1[18]=(String) subList.get(16);
					  txntableParty1[19]=(String) subList.get(17);
					  txntableParty1[20]=(String) subList.get(18);
					  txntableParty1[21]=(String) subList.get(19);
					  txntableParty1[22]=(String) subList.get(20);
					  //txntableParty1[23]=(String) subList.get(21);
					  txntableParty1[23]=(String) subList.get(22);
					  txntableParty1[24]="";
					  txntableParty1[25]=(String) subList.get(24);
					  txntableParty1[26]=(String) subList.get(25);
					  txntableParty1[27]=String.valueOf(partyList1.size());
					  estampDTO.setAdoptNoPerson(String.valueOf(partyList1.size()));
					  if(subList.get(0).equals("2")){  
						  estampDTO.setAge(Integer.parseInt((String)subList.get(26)));
						  estampDTO.setRelation(getRelationshipName((subList.get(27).toString()), language));
						  if((String)subList.get(28)!=null){
						  estampDTO.setGuardian((String)subList.get(28));}}
						  
						  else{
							  estampDTO.setAge(0);
							  estampDTO.setRelation("");
							  estampDTO.setGuardian("");
							  
						  }
					  
						
						 if(subList.get(31)!=null)
							{
								partyId = subList.get(31).toString();
							}
						  
						  int roleId = Integer.parseInt((String)subList.get(30));
						  String[] claimantArr=commonBO.getClaimantFlag(Integer.toString(roleId));
						  mapDto.setClaimantFlag(Integer.parseInt(claimantArr[0].trim()));
						  
						  int ExecutantClaimantfromDB=0;
						  if (subList.get(29)!=null && !subList.get(29).toString().equalsIgnoreCase(""))
					   	  {
					        	 ExecutantClaimantfromDB = Integer.parseInt((String)subList.get(29));
					   	  }	
					  transmgtUtil.createPreparedStatement(CommonSQL.INSERT_ESTAMP_TXN_PARTY_DETLS_PARTY1);
					  transmgtUtil.executeUpdate(txntableParty1);
					  estampDTO.setAdoptNoPerson(String.valueOf(partyList1.size()));
					  if(subList.get(0).toString().equalsIgnoreCase("1"))
					  {

						  

						  
							 
						  if(CommonDeedFlag!=null && "Y".equalsIgnoreCase(CommonDeedFlag)
								  && ExecutantClaimantfromDB==RegInitConstant.CLAIMANT_FLAG_2 ||
							    	ExecutantClaimantfromDB ==RegInitConstant.CLAIMANT_FLAG_4 
							    	||ExecutantClaimantfromDB ==RegInitConstant.CLAIMANT_FLAG_5 ||
							    	ExecutantClaimantfromDB ==RegInitConstant.CLAIMANT_FLAG_6
							    	)
			                		
			               {
								//poAOwnerListForOrg = commonBD.getOwnerPoaDescDisplayForOrg(lang, regNumber);
								mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
								// int ExecutantClaimantfromDB = Integer.parseInt((String)row_list.get(14));
								 System.out.println("ExecutantClaimant" + ExecutantClaimantfromDB );
								form.setExecutantClaimantName(commonBO.getExecutantClaimantNameForPOA(Integer.toString(ExecutantClaimantfromDB)));
			            		 String RoleName = form.getExecutantClaimantName();
			                	 System.out.println("Role Name " +RoleName); 
			                	 String PoaName = null;
			                	// to check hindi and english role 
			                	 String HindiEnglishRole[] = RoleName.split(",");
			                	 String hindesc = HindiEnglishRole[0];
			                	 String EngDesc =HindiEnglishRole[1];
			                	
			                	 if (EngDesc.contains("Authenticated")){
			                		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
		                    		   PoaName =" By(Authenticated PoA Holder)";
	                    		  }
	                    		  else {
	                    			  PoaName =" द्वारा(प्रमाणीकृत  मुख्‍त्‍यार)";
	                    		  }}    
			                	 else {
			                		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
			                			 PoaName =" By(PoA Holder)";
			                		  }
			                		  else {
			                			  PoaName =" द्वारा(मुख्‍त्‍यार आम)";
			                		  }
			                	 }
			                	 
			                    	 String OwnerDetail =null;
			                    	 String   poAOwnerListNew = commonBD.getOwnerDetails(regInitId,partyId,language);
			                      	String poaNameDetails = commonBD.getOwnerPOAnameDetails(partyId);
			                      	OwnerRolePoA=poAOwnerListNew+PoaName+poaNameDetails;
			                      	estampDTO.setAdoptFirstName(OwnerRolePoA);
			                	    
			                	      
			               }
						
						 else  if(Integer.parseInt(claimantArr[1].trim())==RegInitConstant.POA_HOLDER_FLAG )
					     {
							// poAOwnerListForOrg = commonBD.getOwnerPoaDescDisplayForOrg(lang, regNumber);
					     	 mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
					     	 String  RoleName= null;
					     	// sDTO.setRoleName(sealDAO.getPartyRoleDetails((String)subList.get(3),lang));
					     	    mapDto.setRoleName(commonBO.getRoleNameForPOA((String) subList.get(30)));
					     	    RoleName = mapDto.getRoleName();
					     	   System.out.println("Role Name " +RoleName); 
			                 	
					     	  // to check hindi and english role 
		                  	 String HindiEnglishRole[] = RoleName.split(",");
		                  	 String hindesc = HindiEnglishRole[0];
		                  	 String EngDesc =HindiEnglishRole[1];
		                  	 
		                  	 
		                  	 String PoaName = null;
		                  	 if (EngDesc.contains("Authenticated")){
		                		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
		                    		   PoaName =" By(Authenticated PoA Holder)";
	                    		  }
	                    		  else {
	                    			  PoaName =" द्वारा(प्रमाणीकृत  मुख्‍त्‍यार)";
	                    		  }}    
		                  	 else {
		                  		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
		                  			 PoaName =" By(PoA Holder)";
		                		  }
		                		  else {
		                			  PoaName =" द्वारा(मुख्‍त्‍यार आम)";
		                		  }
						}


					     	   
			                 	 System.out.println("POA NAMe is "+PoaName);
			           	         System.out.println("POA holder flag is "+mapDto.getPoaHolderFlag());
			           	        //System.out.println("PoAOwnerlistdisplay"+poAOwnerListForOrg.get(i));
			        	         
			         	        String OwnerDetail =null;
			         	       String   poAOwnerListNew = commonBD.getOwnerDetails(regInitId,partyId,language);
			                 	String poaNameDetails = commonBD.getOwnerPOAnameDetails(partyId);
			                 	OwnerRolePoA=poAOwnerListNew+PoaName+poaNameDetails;
			                 	estampDTO.setAdoptFirstName(OwnerRolePoA);
			          	       
					    }
						else{
							estampDTO.setAdoptFirstName((String) subList.get(17));
							  
								}
						  
						  
						  estampDTO.setAdoptionTypeFlag("1");
						 
						  estampDTO.setAdoptAddress((String) subList.get(10));
					  
					  
						  
						  
						
					  }
					  else if(subList.get(0).toString().equalsIgnoreCase("2"))
					  {
						
						  if(CommonDeedFlag!=null && "Y".equalsIgnoreCase(CommonDeedFlag)
								  && ExecutantClaimantfromDB==RegInitConstant.CLAIMANT_FLAG_2 ||
							    	ExecutantClaimantfromDB ==RegInitConstant.CLAIMANT_FLAG_4 ||
							    	ExecutantClaimantfromDB ==RegInitConstant.CLAIMANT_FLAG_5
							    	||
							    	ExecutantClaimantfromDB ==RegInitConstant.CLAIMANT_FLAG_6)
							    	
						  {
							  mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
								 //int ExecutantClaimantfromDB = Integer.parseInt((String)subList.get(15));
								 System.out.println("ExecutantClaimant" +ExecutantClaimantfromDB);
								form.setExecutantClaimantName(commonBO.getExecutantClaimantNameForPOA(Integer.toString(ExecutantClaimantfromDB)));
			         		 String RoleName = form.getExecutantClaimantName();
			             	 System.out.println("Role Name " +RoleName); 
			             	// to check hindi and english role 
			            	 String HindiEnglishRole[] = RoleName.split(",");
			            	 String hindesc = HindiEnglishRole[0];
			            	 String EngDesc =HindiEnglishRole[1];
			            	 
			            	 
			            	 String PoaName = null;
			            	 if (EngDesc.contains("Authenticated")){
		                		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
		                    		   PoaName =" By(Authenticated PoA Holder)";
	                    		  }
	                    		  else {
	                    			  PoaName =" द्वारा(प्रमाणीकृत  मुख्‍त्‍यार)";
	                    		  }}    
			            	 else {
			            		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
			            			 PoaName =" By(PoA Holder) ";
			            		  }
			            		  else {
			            			  PoaName =" द्वारा(मुख्‍त्‍यार आम)";
			            		  }
			            	 }
			                 	 String OwnerDetail =null;
			                 	 
			                 	String   poAOwnerListNew = commonBD.getOwnerDetails(regInitId,partyId,language);
			                 	String poaNameDetails = commonBD.getOwnerPOAnameDetails(partyId);
			                 	OwnerRolePoA=poAOwnerListNew+PoaName+poaNameDetails;


			                 
			                 	estampDTO.setAdoptFirstName(OwnerRolePoA);
				          	       
			             	        
						  }
						  // will deed Authenticaed POA finish - Rahul 
						  else  if(Integer.parseInt(claimantArr[1].trim())==RegInitConstant.POA_HOLDER_FLAG )
						     {
						    	 
						     	 mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
						     	 String  RoleName= null;
						     	// sDTO.setRoleName(sealDAO.getPartyRoleDetails((String)subList.get(3),lang));
						     	  mapDto.setRoleName(commonBO.getRoleNameForPOA((String) subList.get(30)));
						     	    RoleName = mapDto.getRoleName();
						     	   System.out.println("Role Name " +RoleName); 
				                 	
						     	  // to check hindi and english role 
			                  	 String HindiEnglishRole[] = RoleName.split(",");
			                  	 String hindesc = HindiEnglishRole[0];
			                  	 String EngDesc =HindiEnglishRole[1];
			                  	 
			                  	 
			                  	 String PoaName = null;
			                  	 if (EngDesc.contains("Authenticated")){
			                		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
			                    		   PoaName =" By(Authenticated PoA Holder)";
		                    		  }
		                    		  else {
		                    			  PoaName =" द्वारा(प्रमाणीकृत  मुख्‍त्‍यार)";
		                    		  }}    
			                  	 else {
			                  		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
			                  			 PoaName =" By(PoA Holder)";
			                		  }
			                		  else {
			                			  PoaName =" द्वारा(मुख्‍त्‍यार आम)";
			                		  }
							}


						     	   
				                
				        	         
				         	        String OwnerDetail =null;
				         	       String   poAOwnerListNew = commonBD.getOwnerDetails(regInitId,partyId,language);
				                 	String poaNameDetails = commonBD.getOwnerPOAnameDetails(partyId);
				                 	OwnerRolePoA=poAOwnerListNew+PoaName+poaNameDetails;
				                 	
				                 	estampDTO.setAdoptFirstName(OwnerRolePoA);
				          	       
						    }   
						  
					  else{
						 String name="";
						 if(subList.get(4)==null||subList.get(4).toString().equalsIgnoreCase(""))
						 {
							 
							 if(estampDTO.getAge()>=18){
							name=(String) subList.get(3)+" "+(String) subList.get(5);
							 }
							 
							 else{
								 if(language.equalsIgnoreCase("en")){
									 name="(Minor)" +(String) subList.get(3)+" "+(String) subList.get(5)+" "+estampDTO.getRelation()+" "+(String) subList.get(18)+" by "+estampDTO.getGuardian(); 
									 }
									 else{
										 
										 name="(अवयस्‍क)" +(String) subList.get(3)+" "+(String) subList.get(5)+" "+estampDTO.getRelation()+" "+(String) subList.get(18)+" द्वारा  "+estampDTO.getGuardian(); 
				 
									 } 
								 
							 }
						 }
						 else
						 {if(estampDTO.getAge()>=18){
							 name=(String) subList.get(3)+" "+(String) subList.get(4) + " "+(String) subList.get(5);}
						 
						 else{
							 
							 if(language.equalsIgnoreCase("en")){
								 name="(Minor)" +(String) subList.get(3)+" "+(String) subList.get(4) +" "+(String) subList.get(5)+" "+estampDTO.getRelation()+" "+(String) subList.get(18)+" by "+estampDTO.getGuardian(); 
								 }
								 else{
									 
									 name="(अवयस्‍क)" +(String) subList.get(3)+" "+(String) subList.get(4) +" "+(String) subList.get(5)+" "+estampDTO.getRelation()+" "+(String) subList.get(18)+" द्वारा  "+estampDTO.getGuardian(); 
			 
								 }  
							 
						 }
						 }
						  estampDTO.setAdoptionTypeFlag("2");
						  estampDTO.setAdoptFirstName(name);
						  estampDTO.setAdoptAddress((String) subList.get(10));
					  } 
						  
						  estampDTO.setAdoptionTypeFlag("2");
						//  estampDTO.setAdoptFirstName(name);
						  estampDTO.setAdoptAddress((String) subList.get(10));
					  }
					  else
					  {
						 if(subList.get(24)==null||subList.get(24).toString().equalsIgnoreCase(""))
						 {
							 estampDTO.setAdoptFirstName("--");
						 }
						 else
						 {
							 estampDTO.setAdoptFirstName(subList.get(24).toString());
						 }
						 estampDTO.setAdoptAddress((String) subList.get(25));
						  estampDTO.setAdoptionTypeFlag("2");
						  
					  }
				  }
		  }
		  transmgtUtil.createStatement();
		  String number7 = transmgtUtil.executeQry(CommonSQL.SELECT_SEQUENCE3);
		  
		  	txntableParty1[0] = number7;
		  	txntableParty1[1] = estmTxnId;
		  	partyArray[0]=regInitId;
		  	partyArray[2]=regInitId;
		  	if(deedId.equalsIgnoreCase("1003"))
		  	{
		  		partyArray[1]="0";	
			  	partyArray[3]="0";
			  	estampDTO.setIsAdoption("Y");
		  	}
		  	else
		  	{	
		  		partyArray[1]="2";	
		  		partyArray[3]="2";
		  		estampDTO.setIsAdoption("N");
		  	} 
		  	transmgtUtil.createPreparedStatement(CommonSQL.GET_PARTY_DETAILS_updated);
		  	partyList1=transmgtUtil.executeQuery(partyArray);
			  
			  if(partyList1!=null&&partyList1.size()>0)
			  {
				  ArrayList subList=(ArrayList) partyList1.get(0);
				  txntableParty1[2]=(String) subList.get(0);
				  txntableParty1[3]="N";
				  txntableParty1[4]=(String) subList.get(2);
				  txntableParty1[5]=(String) subList.get(3);
				  txntableParty1[6]=(String) subList.get(4);
				  txntableParty1[7]=(String) subList.get(5);
				  txntableParty1[8]=(String) subList.get(6);
				  txntableParty1[9]=(String) subList.get(7);
				  txntableParty1[10]=(String) subList.get(8);
				  txntableParty1[11]=(String) subList.get(9);
				  txntableParty1[12]=(String) subList.get(10);
				  txntableParty1[13]=(String) subList.get(11);
				  txntableParty1[14]=(String) subList.get(12);
				  txntableParty1[15]=(String) subList.get(13);
				  txntableParty1[16]=(String) subList.get(14);
				  txntableParty1[17]=(String) subList.get(15);
				  txntableParty1[18]=(String) subList.get(16);
				  txntableParty1[19]=(String) subList.get(17);
				  
				  txntableParty1[20]=(String) subList.get(18);
				  txntableParty1[21]=(String) subList.get(19);
				  txntableParty1[22]=(String) subList.get(20);
				//  txntableParty1[23]=(String) subList.get(21);
				  txntableParty1[23]=(String) subList.get(22);
				  txntableParty1[24]="";
				  txntableParty1[25]=(String) subList.get(24);
				  txntableParty1[26]=(String) subList.get(25);
				  txntableParty1[27]=String.valueOf(partyList1.size());
				  transmgtUtil.createPreparedStatement(CommonSQL.INSERT_ESTAMP_TXN_PARTY_DETLS_PARTY1);
				  estampDTO.setPartyPersons(String.valueOf(partyList1.size()));
				  transmgtUtil.executeUpdate(txntableParty1);
				  if(subList.get(0).equals("2")){  
					  estampDTO.setAge(Integer.parseInt((String)subList.get(26)));
					  estampDTO.setRelation(getRelationshipName((subList.get(27).toString()), language));
					  if((String)subList.get(28)!=null){
					  estampDTO.setGuardian((String)subList.get(28));}}
				
				 else{
					  estampDTO.setAge(0);
					  estampDTO.setRelation("");
					  estampDTO.setGuardian("");
					  
				  }
				
				 if(subList.get(31)!=null)
					{
						partyId = subList.get(31).toString();
					}
				  
				  int roleId = Integer.parseInt((String)subList.get(30));
				  String[] claimantArr=commonBO.getClaimantFlag(Integer.toString(roleId));
				  mapDto.setClaimantFlag(Integer.parseInt(claimantArr[0].trim()));
				  
				  int ExecutantClaimantfromDB=0;
				  if (subList.get(29)!=null && !subList.get(29).toString().equalsIgnoreCase(""))
			   	  {
			        	 ExecutantClaimantfromDB = Integer.parseInt((String)subList.get(29));
			   	  }	
				  if(subList.get(0).toString().equalsIgnoreCase("1"))
				  {
					  


					  

					  
						 
					  if(CommonDeedFlag!=null && "Y".equalsIgnoreCase(CommonDeedFlag)
							  && ExecutantClaimantfromDB==RegInitConstant.CLAIMANT_FLAG_2 ||
						    	ExecutantClaimantfromDB ==RegInitConstant.CLAIMANT_FLAG_4 
						    	||ExecutantClaimantfromDB ==RegInitConstant.CLAIMANT_FLAG_5 ||
						    	ExecutantClaimantfromDB ==RegInitConstant.CLAIMANT_FLAG_6
						    	)
		                		
		               {
							//poAOwnerListForOrg = commonBD.getOwnerPoaDescDisplayForOrg(lang, regNumber);
							mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
							// int ExecutantClaimantfromDB = Integer.parseInt((String)row_list.get(14));
							 System.out.println("ExecutantClaimant" + ExecutantClaimantfromDB );
							form.setExecutantClaimantName(commonBO.getExecutantClaimantNameForPOA(Integer.toString(ExecutantClaimantfromDB)));
		            		 String RoleName = form.getExecutantClaimantName();
		                	 System.out.println("Role Name " +RoleName); 
		                	 String PoaName = null;
		                	// to check hindi and english role 
		                	 String HindiEnglishRole[] = RoleName.split(",");
		                	 String hindesc = HindiEnglishRole[0];
		                	 String EngDesc =HindiEnglishRole[1];
		                	
		                	 if (EngDesc.contains("Authenticated")){
		                		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
		                    		   PoaName =" By(Authenticated PoA Holder)";
	                    		  }
	                    		  else {
	                    			  PoaName =" द्वारा(प्रमाणीकृत  मुख्‍त्‍यार)";
	                    		  }}    
		                	 else {
		                		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
		                			 PoaName =" By(PoA Holder)";
		                		  }
		                		  else {
		                			  PoaName =" द्वारा(मुख्‍त्‍यार आम)";
		                		  }
		                	 }
		                	 
		                    	 String OwnerDetail =null;
		                    	 String   poAOwnerListNew = commonBD.getOwnerDetails(regInitId,partyId,language);
		                      	String poaNameDetails = commonBD.getOwnerPOAnameDetails(partyId);
		                      	OwnerRolePoA=poAOwnerListNew+PoaName+poaNameDetails;
		                      	estampDTO.setPartyFirsName(OwnerRolePoA);
		                	    
		                	      
		               }
					
					 else  if(Integer.parseInt(claimantArr[1].trim())==RegInitConstant.POA_HOLDER_FLAG )
				     {
						// poAOwnerListForOrg = commonBD.getOwnerPoaDescDisplayForOrg(lang, regNumber);
				     	 mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
				     	 String  RoleName= null;
				     	// sDTO.setRoleName(sealDAO.getPartyRoleDetails((String)subList.get(3),lang));
				     	    mapDto.setRoleName(commonBO.getRoleNameForPOA((String) subList.get(30)));
				     	    RoleName = mapDto.getRoleName();
				     	   System.out.println("Role Name " +RoleName); 
		                 	
				     	  // to check hindi and english role 
	                  	 String HindiEnglishRole[] = RoleName.split(",");
	                  	 String hindesc = HindiEnglishRole[0];
	                  	 String EngDesc =HindiEnglishRole[1];
	                  	 
	                  	 
	                  	 String PoaName = null;
	                  	 if (EngDesc.contains("Authenticated")){
	                		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
	                    		   PoaName =" By(Authenticated PoA Holder)";
                  		  }
                  		  else {
                  			  PoaName =" द्वारा(प्रमाणीकृत  मुख्‍त्‍यार)";
                  		  }}    
	                  	 else {
	                  		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
	                  			 PoaName =" By(PoA Holder)";
	                		  }
	                		  else {
	                			  PoaName =" द्वारा(मुख्‍त्‍यार आम)";
	                		  }
					}


				     	   
		                 	 System.out.println("POA NAMe is "+PoaName);
		           	         System.out.println("POA holder flag is "+mapDto.getPoaHolderFlag());
		           	        //System.out.println("PoAOwnerlistdisplay"+poAOwnerListForOrg.get(i));
		        	         
		         	        String OwnerDetail =null;
		         	       String   poAOwnerListNew = commonBD.getOwnerDetails(regInitId,partyId,language);
		                 	String poaNameDetails = commonBD.getOwnerPOAnameDetails(partyId);
		                 	OwnerRolePoA=poAOwnerListNew+PoaName+poaNameDetails;
		                 	estampDTO.setPartyFirsName(OwnerRolePoA);
		          	       
				    }
					else{
						 estampDTO.setPartyFirsName((String) subList.get(17));
						  
							}
					  
					  
					  estampDTO.setPartyTypeFlag("1");

					 
					  estampDTO.setPartyAddress((String) subList.get(10));
				  
				 
				  }
				  else if(subList.get(0).toString().equalsIgnoreCase("2"))
				  {
					  
					  if(CommonDeedFlag!=null && "Y".equalsIgnoreCase(CommonDeedFlag)
							  && ExecutantClaimantfromDB==RegInitConstant.CLAIMANT_FLAG_2 ||
						    	ExecutantClaimantfromDB ==RegInitConstant.CLAIMANT_FLAG_4 ||
						    	ExecutantClaimantfromDB ==RegInitConstant.CLAIMANT_FLAG_5||
						    	ExecutantClaimantfromDB ==RegInitConstant.CLAIMANT_FLAG_6)
						    	
					  {
						  mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
							 //int ExecutantClaimantfromDB = Integer.parseInt((String)subList.get(15));
							 System.out.println("ExecutantClaimant" +ExecutantClaimantfromDB);
							form.setExecutantClaimantName(commonBO.getExecutantClaimantNameForPOA(Integer.toString(ExecutantClaimantfromDB)));
		         		 String RoleName = form.getExecutantClaimantName();
		             	 System.out.println("Role Name " +RoleName); 
		             	// to check hindi and english role 
		            	 String HindiEnglishRole[] = RoleName.split(",");
		            	 String hindesc = HindiEnglishRole[0];
		            	 String EngDesc =HindiEnglishRole[1];
		            	 
		            	 
		            	 String PoaName = null;
		            	 if (EngDesc.contains("Authenticated")){
	                		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
	                    		   PoaName =" By(Authenticated PoA Holder)";
                  		  }
                  		  else {
                  			  PoaName =" द्वारा(प्रमाणीकृत  मुख्‍त्‍यार)";
                  		  }}    
		            	 else {
		            		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
		            			 PoaName =" By(PoA Holder)";
		            		  }
		            		  else {
		            			  PoaName =" द्वारा(मुख्‍त्‍यार आम)";
		            		  }
		            	 }
		                 	 String OwnerDetail =null;
		                 	 
		                 	String   poAOwnerListNew = commonBD.getOwnerDetails(regInitId,partyId,language);
		                 	String poaNameDetails = commonBD.getOwnerPOAnameDetails(partyId);
		                 	OwnerRolePoA=poAOwnerListNew+PoaName+poaNameDetails;


		                 
		                 	estampDTO.setPartyFirsName(OwnerRolePoA);
			          	       
		             	        
					  }
					  // will deed Authenticaed POA finish - Rahul 
					  else  if(Integer.parseInt(claimantArr[1].trim())==RegInitConstant.POA_HOLDER_FLAG )
					     {
					    	 
					     	 mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
					     	 String  RoleName= null;
					     	// sDTO.setRoleName(sealDAO.getPartyRoleDetails((String)subList.get(3),lang));
					     	  mapDto.setRoleName(commonBO.getRoleNameForPOA((String) subList.get(30)));
					     	    RoleName = mapDto.getRoleName();
					     	   System.out.println("Role Name " +RoleName); 
			                 	
					     	  // to check hindi and english role 
		                  	 String HindiEnglishRole[] = RoleName.split(",");
		                  	 String hindesc = HindiEnglishRole[0];
		                  	 String EngDesc =HindiEnglishRole[1];
		                  	 
		                  	 
		                  	 String PoaName = null;
		                  	 if (EngDesc.contains("Authenticated")){
		                		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
		                    		   PoaName =" By(Authenticated PoA Holder)";
	                    		  }
	                    		  else {
	                    			  PoaName =" द्वारा(प्रमाणीकृत  मुख्‍त्‍यार ) ";
	                    		  }}    
		                  	 else {
		                  		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
		                  			 PoaName =" By(PoA Holder) ";
		                		  }
		                		  else {
		                			  PoaName =" द्वारा(मुख्‍त्‍यार आम)";
		                		  }
						}


					     	   
			                
			        	         
			         	        String OwnerDetail =null;
			         	       String   poAOwnerListNew = commonBD.getOwnerDetails(regInitId,partyId,language);
			                 	String poaNameDetails = commonBD.getOwnerPOAnameDetails(partyId);
			                 	OwnerRolePoA=poAOwnerListNew+PoaName+poaNameDetails;
			                 	
			                 	estampDTO.setPartyFirsName(OwnerRolePoA);
			          	       
					    }   
					  
				  else{
					 String name="";
					 if(subList.get(4)==null||subList.get(4).toString().equalsIgnoreCase(""))
					 {
						 if(estampDTO.getAge()>=18){
							 
								name=(String) subList.get(3)+" "+(String) subList.get(5);
								 }
								 
								 else{
									 
									 if(language.equalsIgnoreCase("en")){
									 
									 name="(Minor)" +(String) subList.get(3)+" "+(String) subList.get(5)+" "+estampDTO.getRelation()+" "+(String) subList.get(18)+" by "+estampDTO.getGuardian(); 
									 }
									 
									 
									 else{
										 name="(अवयस्‍क)" +(String) subList.get(3)+" "+(String) subList.get(5)+" "+estampDTO.getRelation()+" "+(String) subList.get(18)+" द्वारा  "+estampDTO.getGuardian(); 
			 
										 
									 }
									// name =  "(Minor) "+name+" "+relations+" "+ regForm.getFatherNameTrns()+" by "+regForm.getGuardianTrans();
								 }
						 
						 
						
					 }
					 else
					 {
						
						 if(estampDTO.getAge()<18){
							 
							 
							 if(language.equalsIgnoreCase("en")){
							 
							 name="(Minor)" +(String) subList.get(3)+" "+(String) subList.get(4) + " "+(String) subList.get(5)+" "+estampDTO.getRelation()+" "+(String) subList.get(18)+" by "+estampDTO.getGuardian(); 
							 }
							 
							 else{
								 
								 name="(अवयस्‍क)" +(String) subList.get(3)+" "+(String) subList.get(4) + " "+(String) subList.get(5)+" "+estampDTO.getRelation()+" "+(String) subList.get(18)+" द्वारा "+estampDTO.getGuardian(); 
	 
							 }
							 
							 
							 
							 }
						 
						 else{
						 name=(String) subList.get(3)+" "+(String) subList.get(4) + " "+(String) subList.get(5);}
					 }
						// name=(String) subList.get(3)+" "+(String) subList.get(4) + " "+(String) subList.get(5);
					 
					  estampDTO.setPartyTypeFlag("2");
					  estampDTO.setPartyFirsName(name);
					  estampDTO.setPartyAddress((String) subList.get(10));
				  }
					  
					  estampDTO.setPartyTypeFlag("2");
					  //estampDTO.setPartyFirsName(name);
					  estampDTO.setPartyAddress((String) subList.get(10));
				  }
				  else
				  {
					 if(subList.get(24)==null||subList.get(24).toString().equalsIgnoreCase(""))
					 {
						 estampDTO.setPartyFirsName("--");
					 }
					 else
					 {
						 estampDTO.setPartyFirsName(subList.get(24).toString());
					 }
					 estampDTO.setPartyAddress((String) subList.get(25));
					  estampDTO.setPartyTypeFlag("2");
					  
				  }
			  }
			  
		  }
		  
		  
		// end of addition by Lavi for inserting the party details from registration module
		  
 }		  catch (Exception e){
		  e.printStackTrace();
		  transmgtUtil.rollback();
		  logger.error(" Exception at estamp in DAO " + e);
			throw new Exception();
		  
			}
		   
		   finally {
				 try{	    
					 transmgtUtil.commit();
					 transmgtUtil.closeConnection();
				 }
				 catch (Exception e) {
				 logger.error("Exception at estamp in DAO  " + e);
				 }		
		       }
		   return ecodeTxnId;
 } 
	 catch(Exception e)
	 {
		
		 logger.debug("error in estamp");
		 e.printStackTrace();
		
	 }
	 return "";
}
 
 public String getEcode2 (String disttId, String amnt, String func_id, String purpose, EstampDTO estampDTO, String regInitId,String modFlag) throws NullPointerException,
 SQLException,Exception{
	 try
	 {
	 
	 String ecode= null;
	 String ecodeTxnId = null;
	 String[] tid = new String[1];
		tid[0]=regInitId;
		ArrayList list = new ArrayList();
		String deedId= null;
		String instId= null;
	 
	  DBUtility transmgtUtil = null;
	  try{
	  transmgtUtil = new DBUtility();
	  transmgtUtil.setAutoCommit(true);
	  
	 
		  ecode = "01014227042015000091";       //e-code
		  String estmTxnId = "27042015000091";  //estamp transaction id
		 
			 
			 
			ecodeTxnId = ecode+"~"+estmTxnId;
		  
		  
		  
		  transmgtUtil.createPreparedStatement(CommonSQL.GET_DEED_DETLS);
		  list=transmgtUtil.executeQuery(tid);
		  
		  if(list!=null && list.size()>0){
				
				ArrayList rowList;
				for (int i = 0; i < list.size(); i++) {
					
					rowList = (ArrayList)list.get(i);
					
					
						 deedId = (String)rowList.get(0);
						 instId = (String)rowList.get(1);
				}
		  }
						
		 
		  transmgtUtil.createPreparedStatement(CommonSQL.GET_COMMON_FLAG);
		  String commonFlag=transmgtUtil.executeQry(new String[]{instId});
		  if("Y".equalsIgnoreCase(commonFlag))
		  {
			  ArrayList partyList1=null;
			  ArrayList partyList2=null;
			  transmgtUtil.createPreparedStatement(CommonSQL.GET_PARTY_COMMON_EXE);
			  partyList1=transmgtUtil.executeQuery(new String[]{regInitId});
			  transmgtUtil.createPreparedStatement(CommonSQL.GET_PARTY_COMMON_CLA);
			  partyList2=transmgtUtil.executeQuery(new String[]{regInitId});
			  if(partyList1==null||partyList1.size()==0)
			  {
				  if(partyList2!=null&&partyList2.size()>0)
				  {
					  
					  ArrayList subList=(ArrayList) partyList2.get(0);
					 
					  estampDTO.setAppPersons(String.valueOf(partyList2.size()));
					  if(subList.get(0).toString().equalsIgnoreCase("1"))
					  {
						  estampDTO.setApplicantTypeFlag("1");
						  estampDTO.setAppFirsName((String) subList.get(17));
						  estampDTO.setAppAddress((String) subList.get(10));
					  }
					  else if(subList.get(0).toString().equalsIgnoreCase("2"))
					  {
						 String name="";
						 if(subList.get(4)==null||subList.get(4).toString().equalsIgnoreCase(""))
						 {
							name=(String) subList.get(3)+" "+(String) subList.get(5);
						 }
						 else
						 {
							 name=(String) subList.get(3)+" "+(String) subList.get(4) + " "+(String) subList.get(5);
						 }
						  estampDTO.setApplicantTypeFlag("2");
						  estampDTO.setAppFirsName(name);
						  estampDTO.setAppAddress((String) subList.get(10));
					  }
					  else
					  {
						 if(subList.get(24)==null||subList.get(24).toString().equalsIgnoreCase(""))
						 {
							 estampDTO.setAppFirsName("--");
						 }
						 else
						 {
							 estampDTO.setAppFirsName(subList.get(24).toString());
						 }
						 estampDTO.setAppAddress((String) subList.get(25));
						  estampDTO.setApplicantTypeFlag("2");
						  
					  }
					
				  }
			  }
			  else if(partyList2==null||partyList2.size()==0)
			  {
				  if(partyList1!=null&&partyList1.size()>0)
				  {
					  ArrayList subList=(ArrayList) partyList1.get(0);
					 
					  estampDTO.setAppPersons(String.valueOf(partyList1.size()));
					  if(subList.get(0).toString().equalsIgnoreCase("1"))
					  {
						  estampDTO.setApplicantTypeFlag("1");
						  estampDTO.setAppFirsName((String) subList.get(17));
						  estampDTO.setAppAddress((String) subList.get(10));
					  }
					  else if(subList.get(0).toString().equalsIgnoreCase("2"))
					  {
						 String name="";
						 if(subList.get(4)==null||subList.get(4).toString().equalsIgnoreCase(""))
						 {
							name=(String) subList.get(3)+" "+(String) subList.get(5);
						 }
						 else
						 {
							 name=(String) subList.get(3)+" "+(String) subList.get(4) + " "+(String) subList.get(5);
						 }
						  estampDTO.setApplicantTypeFlag("2");
						  estampDTO.setAppFirsName(name);
						  estampDTO.setAppAddress((String) subList.get(10));
					  }
					  else
					  {
						 if(subList.get(24)==null||subList.get(24).toString().equalsIgnoreCase(""))
						 {
							 estampDTO.setAppFirsName("--");
						 }
						 else
						 {
							 estampDTO.setAppFirsName(subList.get(24).toString());
						 }
						 estampDTO.setAppAddress((String) subList.get(25));
						  estampDTO.setApplicantTypeFlag("2");
						  
					  }
				  }
			  }
			  else
			  {
				  if(partyList1!=null&&partyList1.size()>0)
				  {
					  ArrayList subList=(ArrayList) partyList1.get(0);
					
					  estampDTO.setAppPersons(String.valueOf(partyList1.size()));
					  if(subList.get(0).toString().equalsIgnoreCase("1"))
					  {
						  estampDTO.setApplicantTypeFlag("1");
						  estampDTO.setAppFirsName((String) subList.get(17));
						  estampDTO.setAppAddress((String) subList.get(10));
					  }
					  else if(subList.get(0).toString().equalsIgnoreCase("2"))
					  {
						 String name="";
						 if(subList.get(4)==null||subList.get(4).toString().equalsIgnoreCase(""))
						 {
							name=(String) subList.get(3)+" "+(String) subList.get(5);
						 }
						 else
						 {
							 name=(String) subList.get(3)+" "+(String) subList.get(4) + " "+(String) subList.get(5);
						 }
						  estampDTO.setApplicantTypeFlag("2");
						  estampDTO.setAppFirsName(name);
						  estampDTO.setAppAddress((String) subList.get(10));
					  }
					  else
					  {
						 if(subList.get(24)== null||subList.get(24).toString().equalsIgnoreCase(""))
						 {
							 estampDTO.setAppFirsName("--");
						 }
						 else
						 {
							 estampDTO.setAppFirsName(subList.get(24).toString());
						 }
						 estampDTO.setAppAddress((String) subList.get(25));
						  estampDTO.setApplicantTypeFlag("2");
						  
					  }
				  }
				  if(partyList2!=null&&partyList2.size()>0)
				  {
					 
					  ArrayList subList=(ArrayList) partyList2.get(0);
					  
					  estampDTO.setPartyPersons(String.valueOf(partyList2.size()));
					  if(subList.get(0).toString().equalsIgnoreCase("1"))
					  {
						  estampDTO.setPartyTypeFlag("1");
						  estampDTO.setPartyFirsName((String) subList.get(17));
						  estampDTO.setPartyAddress((String) subList.get(10));
					  }
					  else if(subList.get(0).toString().equalsIgnoreCase("2"))
					  {
						 String name="";
						 if(subList.get(4)==null||subList.get(4).toString().equalsIgnoreCase(""))
						 {
							name=(String) subList.get(3)+" "+(String) subList.get(5);
						 }
						 else
						 {
							 name=(String) subList.get(3)+" "+(String) subList.get(4) + " "+(String) subList.get(5);
						 }
						  estampDTO.setPartyTypeFlag("2");
						  estampDTO.setPartyFirsName(name);
						  estampDTO.setPartyAddress((String) subList.get(10));
					  }
					  else
					  {
						 if(subList.get(24)==null||subList.get(24).toString().equalsIgnoreCase(""))
						 {
							 estampDTO.setPartyFirsName("--");
						 }
						 else
						 {
							 estampDTO.setPartyFirsName(subList.get(24).toString());
						 }
						 estampDTO.setPartyAddress((String) subList.get(25));
						  estampDTO.setPartyTypeFlag("2");
						  
					  }
				  }
			  }
		  }
		  else
		  {		  
		 transmgtUtil.createPreparedStatement(CommonSQL.GET_PARTY_DETAILS);
		String partyArray[]=new String[4]; 
		partyArray[0]=regInitId;
		partyArray[1]="1";	
		partyArray[2]=regInitId;
		partyArray[3]="1";
		 ArrayList partyList1=transmgtUtil.executeQuery(partyArray);
		  
		  if(partyList1!=null&&partyList1.size()>0)
		  {
			  ArrayList subList=(ArrayList) partyList1.get(0);
			 
			  estampDTO.setAppPersons(String.valueOf(partyList1.size()));
			  if(subList.get(0).toString().equalsIgnoreCase("1"))
			  {
				  estampDTO.setApplicantTypeFlag("1");
				  estampDTO.setAppFirsName((String) subList.get(17));
				  estampDTO.setAppAddress((String) subList.get(10));
			  }
			  else if(subList.get(0).toString().equalsIgnoreCase("2"))
			  {
				 String name="";
				 if(subList.get(4)==null||subList.get(4).toString().equalsIgnoreCase(""))
				 {
					name=(String) subList.get(3)+" "+(String) subList.get(5);
				 }
				 else
				 {
					 name=(String) subList.get(3)+" "+(String) subList.get(4) + " "+(String) subList.get(5);
				 }
				  estampDTO.setApplicantTypeFlag("2");
				  estampDTO.setAppFirsName(name);
				  estampDTO.setAppAddress((String) subList.get(10));
			  }
			  else
			  {
				 if(subList.get(24)==null||subList.get(24).toString().equalsIgnoreCase(""))
				 {
					 estampDTO.setAppFirsName("--");
				 }
				 else
				 {
					 estampDTO.setAppFirsName(subList.get(24).toString());
				 }
				 estampDTO.setAppAddress((String) subList.get(25));
				  estampDTO.setApplicantTypeFlag("2");
				  
			  }
			  
		  }
		  
		 
		  if(deedId.equalsIgnoreCase("1003"))
		  {
			 	
				partyArray[0]=regInitId;
				partyArray[1]="2";	
				partyArray[2]=regInitId;
				partyArray[3]="2";
				  transmgtUtil.createPreparedStatement(CommonSQL.GET_PARTY_DETAILS);
				 partyList1=transmgtUtil.executeQuery(partyArray);
				  
				  if(partyList1!=null&&partyList1.size()>0)
				  {
					  ArrayList subList=(ArrayList) partyList1.get(0);
					 
					  estampDTO.setAdoptNoPerson(String.valueOf(partyList1.size()));
					  if(subList.get(0).toString().equalsIgnoreCase("1"))
					  {
						  estampDTO.setAdoptionTypeFlag("1");
						  estampDTO.setAdoptFirstName((String) subList.get(17));
						  estampDTO.setAdoptAddress((String) subList.get(10));
					  }
					  else if(subList.get(0).toString().equalsIgnoreCase("2"))
					  {
						 String name="";
						 if(subList.get(4)==null||subList.get(4).toString().equalsIgnoreCase(""))
						 {
							name=(String) subList.get(3)+" "+(String) subList.get(5);
						 }
						 else
						 {
							 name=(String) subList.get(3)+" "+(String) subList.get(4) + " "+(String) subList.get(5);
						 }
						  estampDTO.setAdoptionTypeFlag("2");
						  estampDTO.setAdoptFirstName(name);
						  estampDTO.setAdoptAddress((String) subList.get(10));
					  }
					  else
					  {
						 if(subList.get(24)==null||subList.get(24).toString().equalsIgnoreCase(""))
						 {
							 estampDTO.setAdoptFirstName("--");
						 }
						 else
						 {
							 estampDTO.setAdoptFirstName(subList.get(24).toString());
						 }
						 estampDTO.setAdoptAddress((String) subList.get(25));
						  estampDTO.setAdoptionTypeFlag("2");
						  
					  }
				  }
		  }
		
		  	partyArray[0]=regInitId;
		  	partyArray[2]=regInitId;
		  	if(deedId.equalsIgnoreCase("1003"))
		  	{
		  		partyArray[1]="0";	
			  	partyArray[3]="0";
			  	estampDTO.setIsAdoption("Y");
		  	}
		  	else
		  	{	
		  		partyArray[1]="2";	
		  		partyArray[3]="2";
		  		estampDTO.setIsAdoption("N");
		  	} 
		  	transmgtUtil.createPreparedStatement(CommonSQL.GET_PARTY_DETAILS);
		  	partyList1=transmgtUtil.executeQuery(partyArray);
			  
			  if(partyList1!=null&&partyList1.size()>0)
			  {
				  ArrayList subList=(ArrayList) partyList1.get(0);
				  
				  estampDTO.setPartyPersons(String.valueOf(partyList1.size()));
				  if(subList.get(0).toString().equalsIgnoreCase("1"))
				  {
					  estampDTO.setPartyTypeFlag("1");
					  estampDTO.setPartyFirsName((String) subList.get(17));
					  estampDTO.setPartyAddress((String) subList.get(10));
				  }
				  else if(subList.get(0).toString().equalsIgnoreCase("2"))
				  {
					 String name="";
					 if(subList.get(4)==null||subList.get(4).toString().equalsIgnoreCase(""))
					 {
						name=(String) subList.get(3)+" "+(String) subList.get(5);
					 }
					 else
					 {
						 name=(String) subList.get(3)+" "+(String) subList.get(4) + " "+(String) subList.get(5);
					 }
					  estampDTO.setPartyTypeFlag("2");
					  estampDTO.setPartyFirsName(name);
					  estampDTO.setPartyAddress((String) subList.get(10));
				  }
				  else
				  {
					 if(subList.get(24)==null||subList.get(24).toString().equalsIgnoreCase(""))
					 {
						 estampDTO.setPartyFirsName("--");
					 }
					 else
					 {
						 estampDTO.setPartyFirsName(subList.get(24).toString());
					 }
					 estampDTO.setPartyAddress((String) subList.get(25));
					  estampDTO.setPartyTypeFlag("2");
					  
				  }
			  }
			  
		  }
		  
		  
	
		  
 }		  catch (Exception e){
		  e.printStackTrace();
		  transmgtUtil.rollback();
		  logger.error(" Exception at estamp in DAO " + e);
			throw new Exception();
		  
			}
		   
		   finally {
				 try{	    
					 transmgtUtil.commit();
					 transmgtUtil.closeConnection();
				 }
				 catch (Exception e) {
				 logger.error("Exception at estamp in DAO  " + e);
				 }		
		       }
		   return ecodeTxnId;
 } 
	 catch(Exception e)
	 {
		
		 logger.debug("error in estamp");
		 e.printStackTrace();
		
	 }
	 return "";
}
 
// public String getEcodeRegComp (String disttId, String amnt, String func_id, String purpose, EstampDTO estampDTO, String regInitId) throws NullPointerException, SQLException,Exception{
/*
	 String ecodetable[] = new String[16];
	 String updtValidityDate[] = new String [2];
	 String txntable[] = new String [8];
	// String txntableParty1[] = new String [4];
	 String txntableParty2[] = new String [5];
	 String ecode= null;
	 String ecodeTxnId = null;
	 String[] tid = new String[1];
		tid[0]=regInitId;
		ArrayList list = new ArrayList();
		String deedId= null;
		String instId= null;
	 Date date = new Date();
	  Format yearformat  = new SimpleDateFormat("yyyy");
	  Format monthformat = new SimpleDateFormat("MM");
	  Format dateformat  = new SimpleDateFormat("dd");
	  String dfmt = dateformat.format(date);
	  String yfmt = yearformat.format(date);
	  String mfmt = monthformat.format(date);
	  DBUtility transmgtUtil = null;
	  try{
	  transmgtUtil = new DBUtility();
	  transmgtUtil.setAutoCommit(true);
	  //for  txn table and estamp_txn_id
	  String SQL1 = "SELECT COUNT(ESTAMP_CODE) FROM IGRS_ESTAMP_DETLS WHERE TRUNC(CREATED_DATE)= TRUNC(SYSDATE)";

	  //selct count 5
	 // logger.debug(SQL1);
	  transmgtUtil.createStatement();
	  String number1 = transmgtUtil.executeQry(CommonSQL.SELECT_COUNT5);
	  if (number1.equalsIgnoreCase("0")){
			logger.debug("in if clause");
			String drpqry = "DROP SEQUENCE IGRS_ESTAMP_CODE_SEQ";	

			//drop sqnc 5
			transmgtUtil.createStatement();
			transmgtUtil.executeUpdate(CommonSQL.DROP_SQNC5);
			String crtqry = "CREATE SEQUENCE IGRS_ESTAMP_CODE_SEQ START WITH 1 INCREMENT BY 1 MAXVALUE 999999 MINVALUE 1 NOCYCLE  NOCACHE ORDER";

			//create sqnc 5
			transmgtUtil.createStatement();
			transmgtUtil.executeUpdate(CommonSQL.CREATE_STMNT5);
		}
		  String SQL2 = "select IGRS_ESTAMP_CODE_SEQ.nextval from dual";
		 //select sqnc 12
		  transmgtUtil.createStatement();
		  String number2 = transmgtUtil.executeQry(CommonSQL.SELECT_SEQUENCE12);
		  if(number2.length()==1){
			  number2="00000"+number2;  
			  }else
			  if(number2.length()==2){
				  number2="0000"+number2;  
			  }else
			  if(number2.length()==3){
				  number2="000"+number2;  
			  }else
			  if(number2.length()==4){
				  number2="00"+number2;  
			  }else
			  if(number2.length()==5){
				  number2="0"+number2;  
			  }
		  String appdis = disttId;
		  if(appdis.length()==1){
			  appdis = "0"+appdis;
		  }
		  ecode = "0101"+appdis+dfmt+mfmt+yfmt+number2;
		  
		  String SQL3 = "select IGRS_ESTAMP_TRANSACTION_ID_SEQ.nextval from dual";

		  //select sqnc13
		  transmgtUtil.createStatement();
		  String number3 = transmgtUtil.executeQry(CommonSQL.SELECT_SEQUENCE13);
		  
		  
		  
		  String SQL4 = "SELECT COUNT(ESTAMP_TXN_ID) FROM IGRS_ESTAMP_TXN_DETLS WHERE TRUNC(CREATED_DATE)= TRUNC(SYSDATE)";

		  //select count 6
		  //logger.debug(SQL4);
		  transmgtUtil.createStatement();
		  String number4 = transmgtUtil.executeQry(CommonSQL.SELECT_COUNT6);
		  if (number4.equalsIgnoreCase("0")){
				logger.debug("in if clause");
				String drpqry1 = "DROP SEQUENCE IGRS_ESTAMP_TXN_ID_SEQ";			

				//drop sqnc 6
				transmgtUtil.createStatement();
				transmgtUtil.executeUpdate(CommonSQL.DROP_SQNC6);
				//transmgtUtil.executeQry(drpqry1);
				String crtqry1 = "CREATE SEQUENCE IGRS_ESTAMP_TXN_ID_SEQ START WITH 1 INCREMENT BY 1 MAXVALUE 999999 MINVALUE 1 NOCYCLE  NOCACHE ORDER";

				//create sqnc 6
				transmgtUtil.createStatement();
				transmgtUtil.executeUpdate(CommonSQL.CREATE_STMNT6);
			}
			  String SQL5 = "select IGRS_ESTAMP_TXN_ID_SEQ.nextval from dual";

			  //select sqnc 14
			  transmgtUtil.createStatement();
			  String number5 = transmgtUtil.executeQry(CommonSQL.SELECT_SEQUENCE14);
			  if(number5.length()==1){
				  number5="00000"+number5;  
				  }else
				  if(number5.length()==2){
					  number2="0000"+number2;  
				  }else
				  if(number5.length()==3){
					  number5="000"+number2;  
				  }else
				  if(number5.length()==4){
					  number5="00"+number2;  
				  }else
				  if(number5.length()==5){
					  number5="0"+number2;  
				  }
			 String estmTxnId = dfmt+mfmt+yfmt+number5;
			 
			ecodeTxnId = ecode+"~"+estmTxnId;
		  
		  ecodetable[0] = number3;
		  ecodetable[1] = estmTxnId;
		  ecodetable[2] = ecode;
		  ecodetable[3] = estampDTO.getUid();
		  ecodetable[4] = func_id;
		  ecodetable[5] = amnt;
		  ecodetable[6] = "1";
		  ecodetable[7] = "1";
		  ecodetable[8] = null;// validity date according to the configurable param-----yet to be done
		  ecodetable[9] = "A";
		  ecodetable[10]= purpose;
		  ecodetable[11]= "C";  ///source mod code----master is yet to be made
		  ecodetable[12] = estampDTO.getIssuedPlace();
		  ecodetable[13] = estampDTO.getOfficeName();
		  ecodetable[14] = estampDTO.getUserName();
		  ecodetable[15] = regInitId;
		  
		  transmgtUtil.createPreparedStatement(CommonSQL.GET_DEED_DETLS);
		  list=transmgtUtil.executeQuery(tid);
		  logger.info("list>>>>"+list);
		  
		  if(list!=null && list.size()>0){
				
				ArrayList rowList;
				for (int i = 0; i < list.size(); i++) {
					
					rowList = (ArrayList)list.get(i);
					
					
						 deedId = (String)rowList.get(0);
						 instId = (String)rowList.get(1);
				}
		  }
						
		  updtValidityDate[0]=estmTxnId;
		  updtValidityDate[1]=estmTxnId;
		 
		  txntable[0] = estmTxnId;
		  txntable[1] = "1";
		  txntable[2] = "2";
		  txntable[3] = deedId;
		  txntable[4] = instId;
		  txntable[5] = estampDTO.getUid();
		  txntable[6] = purpose;
		  txntable[7] = "C";
		  
		  
		  transmgtUtil.createPreparedStatement(CommonSQL.ESTAMP_TXN_DTL_INSERT_REG);
		  transmgtUtil.executeUpdate(txntable);
		
		  
		  transmgtUtil.createPreparedStatement(CommonSQL.ESTAMP_DTL_TABLE_INSERT_REG);
		  transmgtUtil.executeUpdate(ecodetable);
		  
		  
		  
		  // Added by Lavi for the updation of validity date in the table
		  
		  transmgtUtil.createPreparedStatement(CommonSQL.UPDT_ECODE_VALID_DATE);
		  transmgtUtil.executeUpdate(updtValidityDate);
		  
		  // end of updation
		  
		// added by Lavi for inserting the party details from registration module
		  transmgtUtil.createStatement();
		  String number6 = transmgtUtil.executeQry(CommonSQL.SELECT_SEQUENCE3);

		  txntableParty1[0] = number6;
		  txntableParty1[1] = estmTxnId;
		  txntableParty1[2] = regInitId;
		  txntableParty1[3] = regInitId;
		  
		  
		  
		  transmgtUtil.createPreparedStatement(CommonSQL.INSERT_ESTAMP_TXN_PARTY_DETLS_PARTY1);
		  transmgtUtil.executeUpdate(txntableParty1);
		  if(deedId.equalsIgnoreCase("1003"))
		  {
			 String numbr9=transmgtUtil.executeQry(CommonSQL.SELECT_SEQUENCE3);
		  txntableParty1[0] = numbr9;
		  txntableParty1[1] = estmTxnId;
		  txntableParty1[2] = regInitId;
		  txntableParty1[3] = regInitId;
		  transmgtUtil.createPreparedStatement(CommonSQL.INSERT_ESTAMP_TXN_PARTY_DETLS_PARTY1_ADOPTION);
		  transmgtUtil.executeUpdate(txntableParty1);
		  }
		  
		  transmgtUtil.createStatement();
		  String number7 = transmgtUtil.executeQry(CommonSQL.SELECT_SEQUENCE3);
		  
		  txntableParty2[0] = number7;
		  txntableParty2[1] = estmTxnId;
		  txntableParty2[2] = regInitId;
		  txntableParty2[3] = regInitId;
		  txntableParty2[4] = regInitId;
		  
		  transmgtUtil.createPreparedStatement(CommonSQL.INSERT_ESTAMP_TXN_PARTY_DETLS_PARTY2);
		  transmgtUtil.executeUpdate(txntableParty2);
		  
		
		  String number6 = transmgtUtil.executeQry(CommonSQL.SELECT_SEQUENCE3);
		  String txntableParty1[] = new String [28];
		  txntableParty1[0] = number6;
		  txntableParty1[1] = estmTxnId;
		 
		  transmgtUtil.createPreparedStatement(CommonSQL.GET_COMMON_FLAG);
		  String commonFlag=transmgtUtil.executeQry(new String[]{instId});
		  if("Y".equalsIgnoreCase(commonFlag))
		  {
			  ArrayList partyList1=null;
			  ArrayList partyList2=null;
			  transmgtUtil.createPreparedStatement(CommonSQL.GET_PARTY_COMMON_EXE);
			  partyList1=transmgtUtil.executeQuery(new String[]{regInitId});
			  transmgtUtil.createPreparedStatement(CommonSQL.GET_PARTY_COMMON_CLA);
			  partyList2=transmgtUtil.executeQuery(new String[]{regInitId});
			  if(partyList1==null||partyList1.size()==0)
			  {
				  if(partyList2!=null&&partyList2.size()>0)
				  {
					  ArrayList subList=(ArrayList) partyList2.get(0);
					  txntableParty1[2]=(String) subList.get(0);
					  txntableParty1[3]="Y";
					  txntableParty1[4]=(String) subList.get(2);
					  txntableParty1[5]=(String) subList.get(3);
					  txntableParty1[6]=(String) subList.get(4);
					  txntableParty1[7]=(String) subList.get(5);
					  txntableParty1[8]=(String) subList.get(6);
					  txntableParty1[9]=(String) subList.get(7);
					  txntableParty1[10]=(String) subList.get(8);
					  txntableParty1[11]=(String) subList.get(9);
					  txntableParty1[12]=(String) subList.get(10);
					  txntableParty1[13]=(String) subList.get(11);
					  txntableParty1[14]=(String) subList.get(12);
					  txntableParty1[15]=(String) subList.get(13);
					  txntableParty1[16]=(String) subList.get(14);
					  txntableParty1[17]=(String) subList.get(15);
					  txntableParty1[18]=(String) subList.get(16);
					  txntableParty1[19]=(String) subList.get(17);
					  txntableParty1[20]=(String) subList.get(18);
					  txntableParty1[21]=(String) subList.get(19);
					  txntableParty1[22]=(String) subList.get(20);
					  //txntableParty1[23]=(String) subList.get(21);
					  txntableParty1[23]=(String) subList.get(22);
					  txntableParty1[24]="";
					  txntableParty1[25]=(String) subList.get(24);
					  txntableParty1[26]=(String) subList.get(25);
					  txntableParty1[27]=String.valueOf(partyList2.size());
					  transmgtUtil.createPreparedStatement(CommonSQL.INSERT_ESTAMP_TXN_PARTY_DETLS_PARTY1);
					  transmgtUtil.executeUpdate(txntableParty1);
				  }
			  }
			  else if(partyList2==null||partyList2.size()==0)
			  {
				  if(partyList1!=null&&partyList1.size()>0)
				  {
					  ArrayList subList=(ArrayList) partyList1.get(0);
					  txntableParty1[2]=(String) subList.get(0);
					  txntableParty1[3]="Y";
					  txntableParty1[4]=(String) subList.get(2);
					  txntableParty1[5]=(String) subList.get(3);
					  txntableParty1[6]=(String) subList.get(4);
					  txntableParty1[7]=(String) subList.get(5);
					  txntableParty1[8]=(String) subList.get(6);
					  txntableParty1[9]=(String) subList.get(7);
					  txntableParty1[10]=(String) subList.get(8);
					  txntableParty1[11]=(String) subList.get(9);
					  txntableParty1[12]=(String) subList.get(10);
					  txntableParty1[13]=(String) subList.get(11);
					  txntableParty1[14]=(String) subList.get(12);
					  txntableParty1[15]=(String) subList.get(13);
					  txntableParty1[16]=(String) subList.get(14);
					  txntableParty1[17]=(String) subList.get(15);
					  txntableParty1[18]=(String) subList.get(16);
					  txntableParty1[19]=(String) subList.get(17);
					  txntableParty1[20]=(String) subList.get(18);
					  txntableParty1[21]=(String) subList.get(19);
					  txntableParty1[22]=(String) subList.get(20);
					  //txntableParty1[23]=(String) subList.get(21);
					  txntableParty1[23]=(String) subList.get(22);
					  txntableParty1[24]="";
					  txntableParty1[25]=(String) subList.get(24);
					  txntableParty1[26]=(String) subList.get(25);
					  txntableParty1[27]=String.valueOf(partyList1.size());
					  transmgtUtil.createPreparedStatement(CommonSQL.INSERT_ESTAMP_TXN_PARTY_DETLS_PARTY1);
					  transmgtUtil.executeUpdate(txntableParty1);
				  }
			  }
			  else
			  {
				  if(partyList1!=null&&partyList1.size()>0)
				  {
					  ArrayList subList=(ArrayList) partyList1.get(0);
					  txntableParty1[2]=(String) subList.get(0);
					  txntableParty1[3]="Y";
					  txntableParty1[4]=(String) subList.get(2);
					  txntableParty1[5]=(String) subList.get(3);
					  txntableParty1[6]=(String) subList.get(4);
					  txntableParty1[7]=(String) subList.get(5);
					  txntableParty1[8]=(String) subList.get(6);
					  txntableParty1[9]=(String) subList.get(7);
					  txntableParty1[10]=(String) subList.get(8);
					  txntableParty1[11]=(String) subList.get(9);
					  txntableParty1[12]=(String) subList.get(10);
					  txntableParty1[13]=(String) subList.get(11);
					  txntableParty1[14]=(String) subList.get(12);
					  txntableParty1[15]=(String) subList.get(13);
					  txntableParty1[16]=(String) subList.get(14);
					  txntableParty1[17]=(String) subList.get(15);
					  txntableParty1[18]=(String) subList.get(16);
					  txntableParty1[19]=(String) subList.get(17);
					  txntableParty1[20]=(String) subList.get(18);
					  txntableParty1[21]=(String) subList.get(19);
					  txntableParty1[22]=(String) subList.get(20);
					  //txntableParty1[23]=(String) subList.get(21);
					  txntableParty1[23]=(String) subList.get(22);
					  txntableParty1[24]="";
					  txntableParty1[25]=(String) subList.get(24);
					  txntableParty1[26]=(String) subList.get(25);
					  txntableParty1[27]=String.valueOf(partyList1.size());
					  transmgtUtil.createPreparedStatement(CommonSQL.INSERT_ESTAMP_TXN_PARTY_DETLS_PARTY1);
					  transmgtUtil.executeUpdate(txntableParty1);
				  }
				  if(partyList2!=null&&partyList2.size()>0)
				  {
					  transmgtUtil.createStatement();
					  String number50 = transmgtUtil.executeQry(CommonSQL.SELECT_SEQUENCE3);
					  txntableParty1[0]=number50;
					  ArrayList subList=(ArrayList) partyList2.get(0);
					  txntableParty1[2]=(String) subList.get(0);
					  txntableParty1[3]="N";
					  txntableParty1[4]=(String) subList.get(2);
					  txntableParty1[5]=(String) subList.get(3);
					  txntableParty1[6]=(String) subList.get(4);
					  txntableParty1[7]=(String) subList.get(5);
					  txntableParty1[8]=(String) subList.get(6);
					  txntableParty1[9]=(String) subList.get(7);
					  txntableParty1[10]=(String) subList.get(8);
					  txntableParty1[11]=(String) subList.get(9);
					  txntableParty1[12]=(String) subList.get(10);
					  txntableParty1[13]=(String) subList.get(11);
					  txntableParty1[14]=(String) subList.get(12);
					  txntableParty1[15]=(String) subList.get(13);
					  txntableParty1[16]=(String) subList.get(14);
					  txntableParty1[17]=(String) subList.get(15);
					  txntableParty1[18]=(String) subList.get(16);
					  txntableParty1[19]=(String) subList.get(17);
					  txntableParty1[20]=(String) subList.get(18);
					  txntableParty1[21]=(String) subList.get(19);
					  txntableParty1[22]=(String) subList.get(20);
					  //txntableParty1[23]=(String) subList.get(21);
					  txntableParty1[23]=(String) subList.get(22);
					  txntableParty1[24]="";
					  txntableParty1[25]=(String) subList.get(24);
					  txntableParty1[26]=(String) subList.get(25);
					  txntableParty1[27]=String.valueOf(partyList2.size());
					  transmgtUtil.createPreparedStatement(CommonSQL.INSERT_ESTAMP_TXN_PARTY_DETLS_PARTY1);
					  transmgtUtil.executeUpdate(txntableParty1);
				  }
			  }
		  }
		  else
		  {		 
		  
		 transmgtUtil.createPreparedStatement(CommonSQL.GET_PARTY_DETAILS);
		String partyArray[]=new String[4]; 
		partyArray[0]=regInitId;
		partyArray[1]="1";	
		partyArray[2]=regInitId;
		partyArray[3]="1";
		 ArrayList partyList1=transmgtUtil.executeQuery(partyArray);
		  
		  if(partyList1!=null&&partyList1.size()>0)
		  {
			  ArrayList subList=(ArrayList) partyList1.get(0);
			  txntableParty1[2]=(String) subList.get(0);
			  txntableParty1[3]="Y";
			  txntableParty1[4]=(String) subList.get(2);
			  txntableParty1[5]=(String) subList.get(3);
			  txntableParty1[6]=(String) subList.get(4);
			  txntableParty1[7]=(String) subList.get(5);
			  txntableParty1[8]=(String) subList.get(6);
			  txntableParty1[9]=(String) subList.get(7);
			  txntableParty1[10]=(String) subList.get(8);
			  txntableParty1[11]=(String) subList.get(9);
			  txntableParty1[12]=(String) subList.get(10);
			  txntableParty1[13]=(String) subList.get(11);
			  txntableParty1[14]=(String) subList.get(12);
			  txntableParty1[15]=(String) subList.get(13);
			  txntableParty1[16]=(String) subList.get(14);
			  txntableParty1[17]=(String) subList.get(15);
			  txntableParty1[18]=(String) subList.get(16);
			  txntableParty1[19]=(String) subList.get(17);
			  txntableParty1[20]=(String) subList.get(18);
			  txntableParty1[21]=(String) subList.get(19);
			  txntableParty1[22]=(String) subList.get(20);
			 // txntableParty1[23]=(String) subList.get(21);
			  txntableParty1[23]=(String) subList.get(22);
			  txntableParty1[24]=(String) "";
			  txntableParty1[25]=(String) subList.get(24);
			  txntableParty1[26]=(String) subList.get(25);
			  txntableParty1[27]=String.valueOf(partyList1.size());
			  transmgtUtil.createPreparedStatement(CommonSQL.INSERT_ESTAMP_TXN_PARTY_DETLS_PARTY1);
			  transmgtUtil.executeUpdate(txntableParty1);
		  }
		  
		 
		  if(deedId.equalsIgnoreCase("1003"))
		  {
			 String numbr9=transmgtUtil.executeQry(CommonSQL.SELECT_SEQUENCE3);
			 	
				partyArray[0]=regInitId;
				partyArray[1]="2";	
				partyArray[2]=regInitId;
				partyArray[3]="2";
				txntableParty1[0] = numbr9;
				  txntableParty1[1] = estmTxnId;
				  transmgtUtil.createPreparedStatement(CommonSQL.GET_PARTY_DETAILS);
				 partyList1=transmgtUtil.executeQuery(partyArray);
				  
				  if(partyList1!=null&&partyList1.size()>0)
				  {
					  ArrayList subList=(ArrayList) partyList1.get(0);
					  txntableParty1[2]=(String) subList.get(0);
					  txntableParty1[3]="A";
					  txntableParty1[4]=(String) subList.get(2);
					  txntableParty1[5]=(String) subList.get(3);
					  txntableParty1[6]=(String) subList.get(4);
					  txntableParty1[7]=(String) subList.get(5);
					  txntableParty1[8]=(String) subList.get(6);
					  txntableParty1[9]=(String) subList.get(7);
					  txntableParty1[10]=(String) subList.get(8);
					  txntableParty1[11]=(String) subList.get(9);
					  txntableParty1[12]=(String) subList.get(10);
					  txntableParty1[13]=(String) subList.get(11);
					  txntableParty1[14]=(String) subList.get(12);
					  txntableParty1[15]=(String) subList.get(13);
					  txntableParty1[16]=(String) subList.get(14);
					  txntableParty1[17]=(String) subList.get(15);
					  txntableParty1[18]=(String) subList.get(16);
					  txntableParty1[19]=(String) subList.get(17);
					  txntableParty1[20]=(String) subList.get(18);
					  txntableParty1[21]=(String) subList.get(19);
					  txntableParty1[22]=(String) subList.get(20);
					  //txntableParty1[23]=(String) subList.get(21);
					  txntableParty1[23]=(String) subList.get(22);
					  txntableParty1[24]="";
					  txntableParty1[25]=(String) subList.get(24);
					  txntableParty1[26]=(String) subList.get(25);
					  txntableParty1[27]=String.valueOf(partyList1.size());
					  transmgtUtil.createPreparedStatement(CommonSQL.INSERT_ESTAMP_TXN_PARTY_DETLS_PARTY1);
					  transmgtUtil.executeUpdate(txntableParty1);
				  }
		  }
		  transmgtUtil.createStatement();
		  String number7 = transmgtUtil.executeQry(CommonSQL.SELECT_SEQUENCE3);
		  
		  	txntableParty1[0] = number7;
		  	txntableParty1[1] = estmTxnId;
		  	partyArray[0]=regInitId;
		  	partyArray[2]=regInitId;
		  	if(deedId.equalsIgnoreCase("1003"))
		  	{
		  		partyArray[1]="0";	
			  	partyArray[3]="0";
		  	}
		  	else
		  	{	
		  		partyArray[1]="2";	
		  		partyArray[3]="2";
		  	} 
		  	transmgtUtil.createPreparedStatement(CommonSQL.GET_PARTY_DETAILS);
		  	partyList1=transmgtUtil.executeQuery(partyArray);
			  
			  if(partyList1!=null&&partyList1.size()>0)
			  {
				  ArrayList subList=(ArrayList) partyList1.get(0);
				  txntableParty1[2]=(String) subList.get(0);
				  txntableParty1[3]="N";
				  txntableParty1[4]=(String) subList.get(2);
				  txntableParty1[5]=(String) subList.get(3);
				  txntableParty1[6]=(String) subList.get(4);
				  txntableParty1[7]=(String) subList.get(5);
				  txntableParty1[8]=(String) subList.get(6);
				  txntableParty1[9]=(String) subList.get(7);
				  txntableParty1[10]=(String) subList.get(8);
				  txntableParty1[11]=(String) subList.get(9);
				  txntableParty1[12]=(String) subList.get(10);
				  txntableParty1[13]=(String) subList.get(11);
				  txntableParty1[14]=(String) subList.get(12);
				  txntableParty1[15]=(String) subList.get(13);
				  txntableParty1[16]=(String) subList.get(14);
				  txntableParty1[17]=(String) subList.get(15);
				  txntableParty1[18]=(String) subList.get(16);
				  txntableParty1[19]=(String) subList.get(17);
				  
				  txntableParty1[20]=(String) subList.get(18);
				  txntableParty1[21]=(String) subList.get(19);
				  txntableParty1[22]=(String) subList.get(20);
				//  txntableParty1[23]=(String) subList.get(21);
				  txntableParty1[23]=(String) subList.get(22);
				  txntableParty1[24]="";
				  txntableParty1[25]=(String) subList.get(24);
				  txntableParty1[26]=(String) subList.get(25);
				  txntableParty1[27]=String.valueOf(partyList1.size());
				  transmgtUtil.createPreparedStatement(CommonSQL.INSERT_ESTAMP_TXN_PARTY_DETLS_PARTY1);
				  transmgtUtil.executeUpdate(txntableParty1);
			  }
		  // end of addition by Lavi for inserting the party details from registration module
		  }	  
 }		  catch (Exception e){
		  e.printStackTrace();
		  transmgtUtil.rollback();
			logger.error(" Exception at estampCompletion in DAO " + e);
			throw new Exception();
			}
		   
		   finally {
				 try{	    
					 transmgtUtil.commit();
					 transmgtUtil.closeConnection();
				 }
				 catch (Exception e) {
				 logger.error("Exception at estampCompletion in DAO  " + e);
				 }		
		       }
		   return ecodeTxnId;
	  *///}
 
 public  boolean insertConsume (DutyCalculationForm eform, DashBoardDTO dto)throws NullPointerException,
 SQLException,Exception{
	 
  boolean transactionflag = false;
  String ecode = dto.getEcode();
  String ecodeTable[] = new String[2];
 
 String status = eform.getObjDashBoardDTO().getRemarks();
 
  DBUtility transmgtUtil = null;
  try{
  transmgtUtil = new DBUtility();
  transmgtUtil.setAutoCommit(false);
	  
  ecodeTable[0]= status;
  ecodeTable[1]= ecode;
  
  transmgtUtil.createPreparedStatement(CommonSQL.CONSUMPTION_RMRKS_UPDATE);
  transmgtUtil.executeUpdate(ecodeTable);	
  
  transactionflag = true;
  transmgtUtil.commit();
	
 }catch (NullPointerException ne) {
	    ne.printStackTrace();
	   transmgtUtil.rollback();
	   throw ne;
		
   }
   
   finally {
		 try{	    
			 transmgtUtil.closeConnection();
		 }
		 catch (Exception e) {
		 logger.error("Exception at estamp in DAO  " + e);
		 }		
       }
   return transactionflag;   
 }
 public  boolean insertConsumeExp (DutyCalculationForm eform, DashBoardDTO dto)throws NullPointerException,
 SQLException,Exception{
	 
  boolean transactionflag = false;
  String ecode = dto.getEcode();
  String ecodeTable[] = new String[2];
  String ecodeTable1[] = new String[1];
 //Vinay
 String status = "Estamp code has been deactivated as user has applied for refund";
 
  DBUtility transmgtUtil = null;
  try{
  transmgtUtil = new DBUtility();
  transmgtUtil.setAutoCommit(false);
	  
  ecodeTable[0]= status;
  ecodeTable[1]= ecode;
  
  transmgtUtil.createPreparedStatement(CommonSQL.CONSUMPTION_RMRKS_UPDATE);
  transmgtUtil.executeUpdate(ecodeTable);
  
  transactionflag = true;
  transmgtUtil.commit();
  
  ecodeTable1[0]= eform.getMainTxnId();
  
  transmgtUtil.createPreparedStatement(CommonSQL.CONSUMPTION_STATUS_CHANGE);
  transmgtUtil.executeUpdate(ecodeTable1);
  
  transactionflag = true;
  transmgtUtil.commit();
	
 }catch (NullPointerException ne) {
	    ne.printStackTrace();
	   transmgtUtil.rollback();
	   throw ne;
		
   }
   
   finally {
		 try{	    
			 transmgtUtil.closeConnection();
		 }
		 catch (Exception e) {
		 logger.error("Exception at estamp in DAO  " + e);
		 }		
       }
   return transactionflag;   
 }





public ArrayList getdetails(String userId,
		DashBoardDTO objDashBoardDTO1) {
	ArrayList details = new ArrayList();
	
	DBUtility dbUtil = null;
	try
	{
	    dbUtil = new DBUtility();
		String param[] = new String[1];
		
		param[0] = userId;
		
		String sql = CommonSQL.USER_DETAILS;
		
		dbUtil = new DBUtility();
		dbUtil.createPreparedStatement(sql);
		logger.debug("Wipro in IGRSCommon - getState() "
				+ "after creating preparedstatement");
		details = dbUtil.executeQuery(param);
		
	}
	
	 catch (Exception x) {
			logger.debug(x);
		} finally{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	 

	return details;
}





public ArrayList getrudetails(String userId,
		DashBoardDTO objDashBoardDTO1,
		String txnId,String language) {
	ArrayList details = new ArrayList();
	DBUtility dbUtil = null;
	try
	{
		dbUtil = new DBUtility();
		String param[] = new String[2];
		String sql="";
		param[0] = txnId;
		param[1] = userId;
		if("en".equalsIgnoreCase(language))
		{
		 sql = CommonSQL.USER_RU_DETAILS;
		}
		else
		{
		 sql = CommonSQL.USER_RU_DETAILS_HI;
		}
		dbUtil = new DBUtility();
		dbUtil.createPreparedStatement(sql);
		logger.debug("Wipro in IGRSCommon - getState() "
				+ "after creating preparedstatement");
		details = dbUtil.executeQuery(param);
	}
	
	 catch (Exception x) {
			logger.debug(x);
		} finally{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	return details;
}





public ArrayList getiudetails(
		DashBoardDTO objDashBoardDTO1,
		String offcId,String language) {
	ArrayList details = new ArrayList();
	DBUtility dbUtil = null;
	try
	{
		dbUtil = new DBUtility();
		String param[] = new String[1];
		
		param[0] = offcId;
	String sql="";
		if("en".equalsIgnoreCase(language))
		{
		 sql = CommonSQL.USER_IU_DETAILS;
		}
		else
		{
		sql = CommonSQL.USER_IU_DETAILS_HI;
		}
		dbUtil = new DBUtility();
		dbUtil.createPreparedStatement(sql);
		logger.debug("Wipro in IGRSCommon - getState() "
				+ "after creating preparedstatement");
		details = dbUtil.executeQuery(param);
	}
	
	 catch (Exception x) {
			logger.debug(x);
		}finally{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 

	return details;
}


/******************************************************************  
 *   Method Name  :   insertDocDetls()
 *   Arguments    :   Form 
 *   Return       :   Boolean
 *   Throws 	  :   NullPointer  or SQLException or Exception
*******************************************************************/  

public  boolean insertDocDetls(DutyCalculationForm eform)throws NullPointerException,
SQLException,Exception{

boolean transactionflag = false;

String docTbl[] =   new String[6];

String uid = eform.getUid();
String docname=eform.getDoc();
String docPath = eform.getDocPath();
//docname = eform.getDocname();


DBUtility transmgtUtil = null;
try{
transmgtUtil = new DBUtility();
transmgtUtil.setAutoCommit(false);

 
 //for document table
 /*String SQL4 = "select IGRS_ESTAMP_DOC_ID_SEQ.nextval from dual";

 //select sqnce 15
*/ transmgtUtil.createStatement();
 String number4 = transmgtUtil.executeQry(CommonSQL.SELECT_SEQUENCE15);
 docTbl[0]=number4;
 docTbl[1]=eform.getMainTxnId();
 docTbl[2]=docname;
 docTbl[3]="A";
 docTbl[4]=uid;
 docTbl[5]=docPath;
 
 
 
 if (!docname.equalsIgnoreCase("")){
 transmgtUtil.createPreparedStatement(CommonSQL.DOCUMENT_TABLE_INSERT);
 transmgtUtil.executeUpdate(docTbl); 
 }
 transactionflag = true;
 transmgtUtil.commit();				     

}catch (NullPointerException ne) {
   ne.printStackTrace();
  transmgtUtil.rollback();
  throw ne;
	
}
   catch (SQLException se) {
   	 se.printStackTrace();
   	transmgtUtil.rollback();
   	
	logger.error("SQL Exception at estamp  in DAO " + se); 
throw se;
}
catch(Exception e){
  e.printStackTrace();
	transmgtUtil.rollback();
logger.error(" Exception at estamp in DAO " + e);
throw e;
}

finally {
	 try{	    
		 transmgtUtil.closeConnection();
	 }
	 catch (Exception e) {
	 logger.error("Exception at estamp in DAO  " + e);
	 }		
  }
return transactionflag;   
}





public ArrayList getiuDtls(String userId, String officeId,String language) throws NullPointerException,
SQLException,Exception {
	String[] tid = new String[3];
	tid[0]=officeId;
	tid[1]=userId;
	tid[2]=officeId;
	ArrayList list = new ArrayList();
	DBUtility transmgtUtil = new DBUtility();
	try{
	if("en".equalsIgnoreCase(language))
	{
	transmgtUtil.createPreparedStatement(CommonSQL.GET_IU_DTLS);
	}
	else
	{
		transmgtUtil.createPreparedStatement(CommonSQL.GET_IU_DTLS_HI);
	}

	list=transmgtUtil.executeQuery(tid);
	list.trimToSize();
	}catch(Exception e)
	{
		throw new Exception();
	}finally{
		transmgtUtil.closeConnection();
	}
	return list;
}

//added by simran for pdf generation in case of Estamp Generation from Registration Modules

public String getEstampTxnId(String estampCode)
{
	String estampTxnId = "";
	try {
		dbUtility = new DBUtility();
		
		String query=CommonSQL.GET_ESTAMP_TXN_ID;
		dbUtility.createPreparedStatement(query);
		estampTxnId = dbUtility.executeQry(new String[]{estampCode});
		
	} catch (Exception exception) {
		logger.debug("Exception in getInstId" + exception);
	}
	finally
	{
		try
		{
			dbUtility.closeConnection();
		}
		catch(Exception e)
		{
			logger.error("error in close connection getInstId"+e.getStackTrace());
		}
		
	}
	return estampTxnId;
}
public String getEstampCode(String estampCode)
{
	String estampTxnId = "";
	try {
		dbUtility = new DBUtility();
		
		String query=CommonSQL.GET_ESTAMP_CODE;
		dbUtility.createPreparedStatement(query);
		estampTxnId = dbUtility.executeQry(new String[]{estampCode,estampCode});
		
	} catch (Exception exception) {
		logger.debug("Exception in getInstId" + exception);
	}
	finally
	{
		try
		{
			dbUtility.closeConnection();
		}
		catch(Exception e)
		{
			logger.error("error in close connection getInstId"+e.getStackTrace());
		}
		
	}
	return estampTxnId;
}
public String getDeedContent(String id,String moduleId)
{
	String estampTxnId = "";
	Clob clob=null;
	try {
		dbUtility = new DBUtility("");
		Connection conn=dbUtility.getDBConnection();
		String query="";
		if("R".equalsIgnoreCase(moduleId))
		{	
		query=CommonSQL.deedContentReg;
		}
		else
		{
			query=CommonSQL.deedContentEstamp;
		}
		PreparedStatement pst= conn.prepareStatement(query);
		pst.setString(1,id );
		ResultSet rset=pst.executeQuery();
		while(rset.next())
		{
			clob=(rset.getClob("DEED_CONTENT"));
			
		}
	//	dbUtility.createPreparedStatement(query);
		//estampTxnId = dbUtility.executeQry(new String[]{id});
		if(clob!=null)
		{
			
	             long length=clob.length();
	             estampTxnId=clob.getSubString(1, (int)length);
	         
		}
	} catch (Exception exception) {
		logger.debug("Exception in getInstId" + exception);
	}
	finally
	{
		try
		{
			dbUtility.closeConnection();
		}
		catch(Exception e)
		{
			logger.error("error in close connection getInstId"+e.getStackTrace());
		}
		
	}
	return estampTxnId;
}
public  ArrayList getDeedType() throws Exception {
	if (logger.isDebugEnabled()) {
		System.out.println("getDeedType(String) - start");
	}
	String SQL =null;
	
	SQL = CommonSQL.DEED_QUERY;
		
	ArrayList returnArrayList = new ArrayList();
	try {

		dbUtility = new DBUtility();
		dbUtility.createStatement();
		returnArrayList = dbUtility.executeQuery(SQL);
	} catch (Exception x) {
		System.out.println(x);
	} finally {
		if (dbUtility != null) {
			dbUtility.closeConnection();
		}
	}

	if (logger.isDebugEnabled()) {
		System.out.println("getDeedType(String) - end");
	}
	return returnArrayList;

}





public ArrayList ivrsEsatmpStatus(int stampNo) {
	ArrayList estampTxnId=null;
try {
		dbUtility = new DBUtility();
		
		String query=CommonSQL.GET_IVRS_STATUS;
		dbUtility.createPreparedStatement(query);
		estampTxnId = dbUtility.executeQuery(new String[]{String.valueOf(stampNo)});
		
	} catch (Exception exception) {
		logger.debug("Exception in getInstId" + exception);
	}
	finally
	{
		try
		{
			dbUtility.closeConnection();
		}
		catch(Exception e)
		{
			logger.error("error in close connection getInstId"+e.getStackTrace());
		}
		
	}
	return estampTxnId;
}





public ArrayList ivrsEstampPurchaser(int stampNo) {
	ArrayList estampTxnId=null;
	try {
			dbUtility = new DBUtility();
			
			String query=CommonSQL.GET_IVRS_PURCHASER;
			dbUtility.createPreparedStatement(query);
			estampTxnId = dbUtility.executeQuery(new String[]{String.valueOf(stampNo)});
			
		} catch (Exception exception) {
			logger.debug("Exception in getInstId" + exception);
		}
		finally
		{
			try
			{
				dbUtility.closeConnection();
			}
			catch(Exception e)
			{
				logger.error("error in close connection getInstId"+e.getStackTrace());
			}
			
		}
		return estampTxnId;
}





public String ivrsEstampPurchaseDate(int stampNo) {
	String estampTxnId=null;
	try {
			dbUtility = new DBUtility();
			
			String query=CommonSQL.GET_IVRS_PURCHASEDATE;
			dbUtility.createPreparedStatement(query);
			estampTxnId = dbUtility.executeQry(new String[]{String.valueOf(stampNo)});
			
		} catch (Exception exception) {
			logger.debug("Exception in getInstId" + exception);
		}
		finally
		{
			try
			{
				dbUtility.closeConnection();
			}
			catch(Exception e)
			{
				logger.error("error in close connection getInstId"+e.getStackTrace());
			}
			
		}
		return estampTxnId;
}

public String getBreifDocument(String id) {
	String estampTxnId=null;
	try {
			dbUtility = new DBUtility();
			
			String query=CommonSQL.GET_BREIF_DOCUMENT;
			dbUtility.createPreparedStatement(query);
			estampTxnId = dbUtility.executeQry(new String[]{String.valueOf(id)});
			
		} catch (Exception exception) {
			logger.debug("Exception in getInstId" + exception);
		}
		finally
		{
			try
			{
				dbUtility.closeConnection();
			}
			catch(Exception e)
			{
				logger.error("error in close connection getInstId"+e.getStackTrace());
			}
			
		}
		return estampTxnId;
}




public String ivrsEstampPupose(int stampNo) {
	String estampTxnId=null;
	try {
			dbUtility = new DBUtility();
			
			String query=CommonSQL.GET_IVRS_PURPOSE;
			dbUtility.createPreparedStatement(query);
			estampTxnId = dbUtility.executeQry(new String[]{String.valueOf(stampNo)});
			
		} catch (Exception exception) {
			logger.debug("Exception in getInstId" + exception);
		}
		finally
		{
			try
			{
				dbUtility.closeConnection();
			}
			catch(Exception e)
			{
				logger.error("error in close connection getInstId"+e.getStackTrace());
			}
			
		}
		return estampTxnId;
}





public ArrayList getDutyDetails(String txnId) throws Exception{

	String[] tid = new String[2];
	tid[0]=txnId;
	tid[1]=txnId;
	ArrayList dty=null;
	DBUtility transmgtUtil = new DBUtility();
	try{
	transmgtUtil.createPreparedStatement(CommonSQL.GET_DUTY_DTLS_COMP);
	dty=transmgtUtil.executeQuery(tid);
	}catch(Exception e)
	{
		throw new Exception();
	}finally{
		transmgtUtil.closeConnection();
	}
	return dty;

}
public ArrayList getDutyDetailsInitiation(String txnId,String module) throws Exception{

	String[] tid = new String[1];
	tid[0]=txnId;

	ArrayList dty;
	DBUtility transmgtUtil = new DBUtility();
	try{
	if("I".equalsIgnoreCase(module))
	{	
	transmgtUtil.createPreparedStatement(CommonSQL.GET_REG_INIT_DUTY_DETS_FOR_CONFIRMATION);
	}
	else if("C".equalsIgnoreCase(module))
	{
	transmgtUtil.createPreparedStatement(CommonSQL.GET_REG_INIT_DUTY_DETS_FOR_CONFIRMATION_COMP);	
	}
	dty=transmgtUtil.executeQuery(tid);
	}catch(Exception e)
	{
		throw new Exception();
	}finally{
		transmgtUtil.closeConnection();
	}
	return dty;

}





public String moduleFlag(String estampCode) {

	String estampTxnId=null;
	try {
			dbUtility = new DBUtility();
			
			String query=CommonSQL.GET_MODULE_FLAG;
			dbUtility.createPreparedStatement(query);
			estampTxnId = dbUtility.executeQry(new String[]{estampCode});
			
		} catch (Exception exception) {
			logger.debug("Exception in getInstId" + exception);
		}
		finally
		{
			try
			{
				dbUtility.closeConnection();
			}
			catch(Exception e)
			{
				logger.error("error in close connection getInstId"+e.getStackTrace());
			}
			
		}
		return estampTxnId;
}
public String getRegId(String estampCode) {

	String estampTxnId=null;
	try {
			dbUtility = new DBUtility();
			
			String query=CommonSQL.GET_REG_ID;
			dbUtility.createPreparedStatement(query);
			estampTxnId = dbUtility.executeQry(new String[]{estampCode});
			
		} catch (Exception exception) {
			logger.debug("Exception in getInstId" + exception);
		}
		finally
		{
			try
			{
				dbUtility.closeConnection();
			}
			catch(Exception e)
			{
				logger.error("error in close connection getInstId"+e.getStackTrace());
			}
			
		}
		return estampTxnId;
}
public boolean updateInitationLanguage (String regNo,String lang)throws NullPointerException,
SQLException,Exception{
	boolean falg=false; 
	try {
		dbUtility = new DBUtility();
		
		String query=CommonSQL.UPDATE_LANGUAGE;
		dbUtility.createPreparedStatement(query);
		String arr[]= new String[2];
		arr[1]=regNo;
		if("en".equalsIgnoreCase(lang))
		{
			arr[0]="English";
		}
		else
		{
			arr[0]="Hindi";
		}
		falg = dbUtility.executeUpdate(arr);
		
	} catch (Exception exception) {
		logger.debug("Exception in getInstId" + exception);
	}
	finally
	{
		try
		{
			dbUtility.closeConnection();
		}
		catch(Exception e)
		{
			logger.error("error in close connection getInstId"+e.getStackTrace());
		}
		
	}
	return falg; 
}
public boolean updateEstampFlag(String regInitId)
{
	boolean falg=false; 
	try {
		dbUtility = new DBUtility();
		
		String query=CommonSQL.UPDATE_ESTAMP_FLAG;
		dbUtility.createPreparedStatement(query);
		String arr[]= new String[2];
		arr[0]="Y";
		arr[1]=regInitId;
		falg = dbUtility.executeUpdate(arr);
		
	} catch (Exception exception) {
		logger.debug("Exception in getInstId" + exception);
	}
	finally
	{
		try
		{
			dbUtility.closeConnection();
		}
		catch(Exception e)
		{
			logger.error("error in close connection getInstId"+e.getStackTrace());
		}
		
	}
	return falg; 
}
public boolean checkEstamp(String regTxnID,String mod_flag)
{
	boolean flag=false; 
	try {
		dbUtility = new DBUtility();
		
		String query=CommonSQL.GET_ESTAMP_CHECK;
		dbUtility.createPreparedStatement(query);
		String arr[]= new String[2];
		arr[0]=mod_flag;
		arr[1]=regTxnID;
		String noOfEstamp= dbUtility.executeQry(arr);
		if(Integer.parseInt(noOfEstamp)==0)
			flag=true;
		else
			flag=false;
	} catch (Exception exception) {
		logger.debug("Exception in checkEstamp" + exception);
	}
	finally
	{
		try
		{
			dbUtility.closeConnection();
		}
		catch(Exception e)
		{
			logger.error("error in close connection checkEstamp"+e.getStackTrace());
		}
		
	}
	return flag; 
}
public String getPayableDuty(String txnID) {

	String amt=null;
	try {
			dbUtility = new DBUtility();
			
			String query=CommonSQL.GET_PAYABLE_AMOUNT;
			dbUtility.createPreparedStatement(query);
			amt = dbUtility.executeQry(new String[]{txnID});
			
		} catch (Exception exception) {
			logger.debug("Exception in getInstId" + exception);
		}
		finally
		{
			try
			{
				dbUtility.closeConnection();
			}
			catch(Exception e)
			{
				logger.error("error in close connection getPayableDuty"+e.getStackTrace());
			}
			
		}
		return amt;
}





public ArrayList getReprintStatus(String estampCodeId) {
	ArrayList list=new ArrayList();
	//String param[]=new String[32];
	try {
		dbUtility = new DBUtility();
String ecode=estampCodeId;

dbUtility.createPreparedStatement(CommonSQL.ECODE_DETAILS);
	list = dbUtility.executeQuery(new String[]{ecode});
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	finally{
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	return list;
}





public Boolean insertdata(String estampCodeId, String userId, 
		String remarks) {
	
	ArrayList list=new ArrayList();
	
	Boolean insertdao = false;
	try {
		dbUtility = new DBUtility();
String ecode=estampCodeId;
String userid=userId;
String rmrks= remarks;


	dbUtility.createPreparedStatement(CommonSQL.ECODE_INSERT_DETAILS);
	dbUtility.executeUpdate(new String[]{ecode,rmrks,userid});
	dbUtility.commit();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	finally{
		insertdao=true;
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	return insertdao;
}





public Boolean updatedata(String estampCodeId) {
ArrayList list=new ArrayList();
	
	Boolean updatedao = false;
	try {
		dbUtility = new DBUtility();
String ecode=estampCodeId;



	dbUtility.createPreparedStatement(CommonSQL.ECODE_UPDATE_DETAILS);
	dbUtility.executeUpdate(new String[]{ecode});
	dbUtility.commit();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	finally{
		updatedao=true;
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	return updatedao;
}

public String getRelationshipName(String id, String languageLocale)
throws Exception {
String name = "";
try {
dbUtility = new DBUtility();
String[] param = { id };
if (languageLocale
		.equalsIgnoreCase("en")) {
	sql = CommonSQL.GET_RELATIONSHIP_NAME;
} else {
	sql = CommonSQL.GET_RELATIONSHIP_NAME_HINDI;
}
dbUtility.createPreparedStatement(sql);
name = dbUtility.executeQry(param);
} catch (Exception exception) {
System.out.println("Exception in getRelationshipName-duycalulationdao"
		+ exception);
} finally {
try {
	dbUtility.closeConnection();
} catch (Exception e) {
	logger.error("duycalulation dao in dao start" + e.getStackTrace());
}
}
return name;
}

public String getCommonFlowFlag(String txnId) throws Exception{
	String[] tid = new String[1];
	tid[0]=txnId;
	String sqlOne="";
	DBUtility dbUtility = null;

		 dbUtility = new DBUtility();
	try{
		dbUtility.createPreparedStatement( CommonSQL.GET_COMMON_FLOW_FLAG);
	sqlOne=dbUtility.executeQry(tid);
	}
	catch(Exception e)
	{
		logger.debug("error in check common flow flag " +e);
		throw e;
		
	}
	finally
	{	
		dbUtility.closeConnection();
	}
	return sqlOne;
}

public boolean checkEstampTxn(String estampTxnID,String mod_flag)
{
	boolean flag=false; 
	try {
		dbUtility = new DBUtility();
		
		String query=CommonSQL.GET_ESTAMP_CHECK_ESTAMP_MOD;
		dbUtility.createPreparedStatement(query);
		String arr[]= new String[2];
		arr[0]=mod_flag;
		arr[1]=estampTxnID;
		String noOfEstamp= dbUtility.executeQry(arr);
		if(Integer.parseInt(noOfEstamp)==0)
			flag=true;
		else
			flag=false;
	} catch (Exception exception) {
		logger.debug("Exception in checkEstamp" + exception);
	}
	finally
	{
		try
		{
			dbUtility.closeConnection();
		}
		catch(Exception e)
		{
			logger.error("error in close connection checkEstamp"+e.getStackTrace());
		}
		
	}
	return flag; 
}
public boolean checkEstampValidity(String _txnID,String deedDraft)
{
	boolean flag=false; 
	try {
		dbUtility = new DBUtility();
		String validateEntry="";
		String query=CommonSQL.DEED_DOC_CHECK;
		dbUtility.createPreparedStatement(query);
		String arr[]= new String[1];
		String arr1[]= new String[2];
		//arr[0]=deedDraft;
		arr[0]=_txnID;
		validateEntry= dbUtility.executeQry(arr);
		if(Integer.parseInt(validateEntry)==1)
			flag=true;
		else
			flag=false;
		
		if(flag){
			arr1[0]=_txnID;
			arr1[1]=_txnID;
			String check2sql=CommonSQL.MULTI_ESTAMP_CHECK;
			dbUtility.createPreparedStatement(check2sql);
			String estampCount= dbUtility.executeQry(arr1);

			if(Integer.parseInt(estampCount)>1)
				flag=false;
			else
				flag=true;
				}
	} catch (Exception exception) {
		logger.debug("Exception in checkEstampValidity" + exception);
	}
	finally
	{
		try
		{
			dbUtility.closeConnection();
		}
		catch(Exception e)
		{
			logger.error("error in close connection checkEstampValidity"+e.getStackTrace());
		}
		
	}
	return flag; 
}
public boolean checkEstampValidity2(String dutyTxnID,String deedDraftID)
{
	boolean flag=false; 
	try {
		dbUtility = new DBUtility();

		String arr[]= new String[2];
		//arr[0]=deedDraft;
		arr[0]=deedDraftID;
		arr[1]=dutyTxnID;
			String check2sql=CommonSQL.MULTI_ESTAMP_CHECK_1;
			dbUtility.createPreparedStatement(check2sql);
			String estampCount= dbUtility.executeQry(arr);

			if(Integer.parseInt(estampCount)>0)
				flag=false;
			else
				flag=true;
				
	} catch (Exception exception) {
		logger.debug("Exception in checkEstampValidity2" + exception);
	}
	finally
	{
		try
		{
			dbUtility.closeConnection();
		}
		catch(Exception e)
		{
			logger.error("error in close connection checkEstampValidity2"+e.getStackTrace());
		}
		
	}
	return flag; 
}
}
