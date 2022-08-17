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
package com.wipro.igrs.poa.dao;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;

import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.igrsmaster.dto.ModuleMasterDTO;
import com.wipro.igrs.igrsmaster.form.ModuleMasterForm;
import com.wipro.igrs.poa.dto.POAAuthenticationFormDTO;
import com.wipro.igrs.poa.sql.CommonSQL;
import com.wipro.igrs.sp.dto.ServiceProviderDTO;
import com.wipro.igrs.util.CommonUtil;


public class POAAuthenticationFormDAO {

    DBUtility dbUtil;
	ArrayList returnList = null;
	DBUtility dbUtility = null;
	IGRSCommon igrsCommon = null;
	
	private static Logger logger = (Logger) Logger.getLogger(POAAuthenticationFormDAO.class);

	public POAAuthenticationFormDAO() {
		
	}

	/* class added by SHRUTI*/
	public static String getOracleDate(String day, String month, String year) {
		String inputDate = day + "-" + month + "-" + year;
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		logger.debug("  " + inputDate + " parses as ");
		String finalDate = "";
		Date newDate = new Date();
		try {
			newDate = formatter.parse(inputDate);
			SimpleDateFormat format = new SimpleDateFormat("dd/MMM/yyyy");
			finalDate = format.format(newDate);

			logger.debug(finalDate);
		} catch (ParseException e) {
			logger.info(e);
		}
		return  finalDate;
	}

	public String systemDate()
	{
		String currentDate="";
		try
		{
		dbUtility=new DBUtility();
		dbUtility.createStatement();
		currentDate=dbUtility.executeQry(CommonSQL.GETCURRENTDATE);
		dbUtility.commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return currentDate;
		
	}
	
	public ArrayList getpoaTypeList() 
	{
		String SQL=CommonSQL.SELECTPOATYPES;
		ArrayList list=new ArrayList();
		ArrayList list1=new ArrayList();
		ArrayList poaTypeList=new ArrayList();
		try{
			dbUtility=new DBUtility();
			dbUtility.createStatement();
			list1=dbUtility.executeQuery(SQL); 
			for(int i=0;i<list1.size();i++){
			list=(ArrayList)list1.get(i);
			POAAuthenticationFormDTO poaDTO=new POAAuthenticationFormDTO();
			poaDTO.setPoaTypeId((String)list.get(0));
			logger.info("INDEX 1-----"+poaDTO.getPoaTypeId());
			poaDTO.setPoaTypeName((String)list.get(1));
			logger.info("INDEX 2-----"+poaDTO.getPoaTypeName());
			poaTypeList.add(poaDTO);
			dbUtility.commit();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return poaTypeList;
		
	}
	public ArrayList getecodePoaTypeList() 
	{
		String SQL=CommonSQL.SELECTPOATYPES;
		ArrayList list=new ArrayList();
		ArrayList list1=new ArrayList();
		ArrayList poaTypeList=new ArrayList();
		try{
			dbUtility=new DBUtility();
			dbUtility.createStatement();
			list1=dbUtility.executeQuery(SQL); 
			for(int i=0;i<list1.size();i++){
			list=(ArrayList)list1.get(i);
			POAAuthenticationFormDTO poaDTO=new POAAuthenticationFormDTO();
			poaDTO.setEcodePoaTypeId((String)list.get(0));
			logger.info("INDEX 1-----"+poaDTO.getPoaTypeId());
			poaDTO.setEcodePoaTypeName((String)list.get(1));
			logger.info("INDEX 2-----"+poaDTO.getPoaTypeName());
			poaTypeList.add(poaDTO);
			dbUtility.commit();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return poaTypeList;
		
	}
	
	
	public ArrayList getCountry() throws NullPointerException,
	SQLException, Exception {
	String SQL=CommonSQL.SELECTCOUNTRY;
	ArrayList list=new ArrayList();
	ArrayList list1=new ArrayList();
	ArrayList awrdrcountryList=new ArrayList();
	try{
		dbUtility=new DBUtility();
		dbUtility.createStatement();
		list1=dbUtility.executeQuery(SQL);
		for(int i=0;i<list1.size();i++){
		list=(ArrayList)list1.get(i);
		POAAuthenticationFormDTO poaDTO=new POAAuthenticationFormDTO();
		poaDTO.setAwrdrcountryId((String)list.get(0));
		logger.info("INDEX 1-----"+poaDTO.getAwrdrcountryId());
		poaDTO.setAwrdrcountryName((String)list.get(1));
		logger.info("INDEX 2-----"+poaDTO.getAwrdrcountryName());
		awrdrcountryList.add(poaDTO);
		dbUtility.commit();
		}
	}
		catch (NullPointerException ne) {
	System.out
			.println("Null Pointer Exception at getting Country LIst "
					+ ne);
} catch (Exception e) {
	logger.info("Exception at getting Country List in DAO " + e);
}
return awrdrcountryList;
}

/**
* Getting State list
* 
* @return list
*/
public synchronized ArrayList getState(String _countryId)
	throws NullPointerException, SQLException, Exception {

	ArrayList list=new ArrayList();
	ArrayList list1=new ArrayList();
	ArrayList awrdrstateList=new ArrayList();

	try{
		String[] country_id={_countryId};
		dbUtility=new DBUtility();
		dbUtility.createPreparedStatement(CommonSQL.SELECTSTATE);
		list1=dbUtility.executeQuery(country_id);
		for(int i=0;i<list1.size();i++){
		list=(ArrayList)list1.get(i);
		POAAuthenticationFormDTO poaDTO=new POAAuthenticationFormDTO();
		poaDTO.setAwrdrstateId((String)list.get(0));
		logger.info("INDEX 1-----"+poaDTO.getAwrdrstateId());
		poaDTO.setAwrdrstateName((String)list.get(1));
		logger.info("INDEX 2-----"+poaDTO.getAwrdrstateName());
		awrdrstateList.add(poaDTO);
		dbUtility.commit();
		}
	
} catch (NullPointerException ne) {
	System.out
			.println("NullPointerException at getting State List in DAO "
					+ ne);
} catch (Exception e) {
	logger.info("Exception at getting State List in DAO " + e);
} finally {
	try {
		dbUtility.closeConnection();
	} catch (Exception e) {
		logger.info("Exception at getting State List in DAO "
				+ e);
	}
}
return awrdrstateList;
}

/**
* Getting District list
* 
* @return list
*/
public synchronized ArrayList getDistrict(String _stateId)
	throws NullPointerException, SQLException, Exception {
	ArrayList list=new ArrayList();
	ArrayList list1=new ArrayList();
	ArrayList awrdrdistrictList=new ArrayList();

	try{
		String[] stateid={_stateId};
		dbUtility=new DBUtility();
		dbUtility.createPreparedStatement(CommonSQL.SELECTDISTRICT);
	    list1=dbUtility.executeQuery(stateid);
		for(int i=0;i<list1.size();i++){
		list=(ArrayList)list1.get(i);
		POAAuthenticationFormDTO poaDTO=new POAAuthenticationFormDTO();
		poaDTO.setAwrdrdistrictId((String)list.get(0));
		logger.info("INDEX 1-----"+poaDTO.getAwrdrdistrictId());
		poaDTO.setAwrdrdistrictName((String)list.get(1));
		logger.info("INDEX 2-----"+poaDTO.getAwrdrdistrictName());
		awrdrdistrictList.add(poaDTO);
		dbUtility.commit();
		}
} catch (NullPointerException ne) {
	System.out
			.println("NullPointer Exception at getting District List in DAO  "
					+ ne);
} catch (Exception e) {
	System.out
			.println("Exception at getting District List in DAO " + e);
}
return awrdrdistrictList;
}
	

public ArrayList getCountry1() throws NullPointerException,
SQLException, Exception {
String SQL=CommonSQL.SELECTCOUNTRY;
ArrayList list=new ArrayList();
ArrayList list1=new ArrayList();
ArrayList acptrcountryList=new ArrayList();
try{
	dbUtility=new DBUtility();
	dbUtility.createStatement();
	list1=dbUtility.executeQuery(SQL);
	for(int i=0;i<list1.size();i++){
	list=(ArrayList)list1.get(i);
	POAAuthenticationFormDTO poaDTO=new POAAuthenticationFormDTO();
	poaDTO.setAcptrcountryId((String)list.get(0));
	logger.info("INDEX 1-----"+poaDTO.getAcptrcountryId());
	poaDTO.setAcptrcountryName((String)list.get(1));
	logger.info("INDEX 2-----"+poaDTO.getAcptrcountryName());
	acptrcountryList.add(poaDTO);
	dbUtility.commit();
	}
}
	catch (NullPointerException ne) {
logger.info("Null Pointer Exception at getting Country LIst "+ ne);
} catch (Exception e) {
logger.info("Exception at getting Country List in DAO " + e);
}
return acptrcountryList;
}

/**
* Getting State list
* 
* @return list
*/
public ArrayList getState1(String _countryId1)
throws NullPointerException, SQLException, Exception {

ArrayList list=new ArrayList();
ArrayList list1=new ArrayList();
ArrayList acptrstateList=new ArrayList();

try{
	String[] country_id={_countryId1};
	dbUtility=new DBUtility();
	dbUtility.createPreparedStatement(CommonSQL.SELECTSTATE);
	list1=dbUtility.executeQuery(country_id);
	for(int i=0;i<list1.size();i++){
	list=(ArrayList)list1.get(i);
	POAAuthenticationFormDTO poaDTO=new POAAuthenticationFormDTO();
	poaDTO.setAcptrstateId((String)list.get(0));
	logger.info("INDEX 1-----"+poaDTO.getAcptrstateId());
	poaDTO.setAcptrstateName((String)list.get(1));
	logger.info("INDEX 2-----"+poaDTO.getAcptrstateName());
	acptrstateList.add(poaDTO);
	dbUtility.commit();
	}

} catch (NullPointerException ne) {
logger.info("NullPointerException at getting State List in DAO "
				+ ne);
} catch (Exception e) {
logger.info("Exception at getting State List in DAO " + e);
} finally {
try {
	dbUtility.closeConnection();
} catch (Exception e) {
	logger.info("Exception at getting State List in DAO "+ e);
}
}
return acptrstateList;
}

/**
* Getting District list
* 
* @return list
*/
public ArrayList getDistrict1(String _stateId1)
throws NullPointerException, SQLException, Exception {
ArrayList list=new ArrayList();
ArrayList list1=new ArrayList();
ArrayList acptrdistrictList=new ArrayList();

try{
	String[] stateid={_stateId1};
	dbUtility=new DBUtility();
	dbUtility.createPreparedStatement(CommonSQL.SELECTDISTRICT);
    list1=dbUtility.executeQuery(stateid);
	for(int i=0;i<list1.size();i++){
	list=(ArrayList)list1.get(i);
	POAAuthenticationFormDTO poaDTO=new POAAuthenticationFormDTO();
	poaDTO.setAcptrdistrictId((String)list.get(0));
	logger.info("INDEX 1-----"+poaDTO.getAcptrdistrictId());
	poaDTO.setAcptrdistrictName((String)list.get(1));
	logger.info("INDEX 2-----"+poaDTO.getAcptrdistrictName());
	acptrdistrictList.add(poaDTO);
	dbUtility.commit();
	}
} catch (NullPointerException ne) {
logger.info("NullPointer Exception at getting District List in DAO  "+ ne);
} catch (Exception e) {
logger.info("Exception at getting District List in DAO " + e);
}
return acptrdistrictList;
}

public String getInsertionOfPOADetails(POAAuthenticationFormDTO poaDTO) throws Exception
{
	//igrsCommon=new IGRSCommon();
	POAAuthenticationFormDAO poaDAO=new POAAuthenticationFormDAO();
	String poano=poaDAO.POAAuthenticationNumber(poaDTO);
	boolean flag=false;
	try{
		dbUtility=new DBUtility();
		dbUtility.setAutoCommit(false);
		
		String applicantDetails[]=
								{
									poano,
									poaDTO.getFirstName(),
									poaDTO.getMiddleName(),
									poaDTO.getLastName(),
									poaDTO.getGender(),
									getOracleDate(poaDTO.getDobDay(), poaDTO.getDobMonth(), poaDTO.getDobYear()),
									poaDTO.getFatherName(),
									poaDTO.getMotherName(),
									poaDTO.getSpouseName(),
									poaDTO.getAddress(),
									poaDTO.getPhno(),
									poaDTO.getMobno(),
									poaDTO.getEmail(),
									poaDTO.getPoaTypeId(),
									poaDTO.getSrname(),
									poaDTO.getSroId(),
									poaDTO.getSr_UserId(),
									poaDTO.getSr_UserId(),
									poaDTO.getManualStampchk()
								};
		
		dbUtility.createPreparedStatement(CommonSQL.INSERTPOAAPPLICANTDETAILS);
		flag=dbUtility.executeUpdate(applicantDetails);
	
		/*if(poaDTO.getDoe()==null)
		{
			poaDTO.setDoe("00-00-0000");
		}*/
		
		
		if(poaDTO.getManualStampchk()=="Y")
			{
				String[] poaDetails={
		
							poano,
							poaDTO.getAwrdrFirstName(),
							poaDTO.getAwrdrMiddleName(),
							poaDTO.getAwrdrLastName(),
							poaDTO.getAwrdrGender(),
							getOracleDate(poaDTO.getAwrdrDobDay(), poaDTO.getAwrdrDobMonth(), poaDTO.getAwrdrDobYear()),
							poaDTO.getAwrdrFatherName(),
							poaDTO.getAwrdrMotherName(),
							poaDTO.getAwrdrSpouseName(),
							poaDTO.getAwrdrAddress(),
							poaDTO.getAwrdrPhno(),
							poaDTO.getAwrdrMobno(),
							poaDTO.getAwrdrEmail(),
							poaDTO.getPoaTypeId(),
							CommonUtil.getConvertedDate(poaDTO.getPoaFromDate()),
							CommonUtil.getConvertedDate(poaDTO.getPoaToDate()),
							poaDTO.getStampduty(),
							poaDTO.getStampCode(),
							CommonUtil.getConvertedDate(poaDTO.getDoe()),
							poaDTO.getDocname(),
							CommonUtil.getConvertedDate(poaDTO.getDoa()),
							poaDTO.getSrname(),
							poaDTO.getSroId(),
							poaDTO.getSr_UserId(),
							poaDTO.getSr_UserId(),
							"50004",
							poaDTO.getPoaRegNo(),
							poaDTO.getEstampAppNo()
							/*poaDTO.getEcodePoaTypeId()*/
							};
		
						dbUtility.createPreparedStatement(CommonSQL.INSERTPOAAWARDERDETAILS);
						flag=dbUtility.executeUpdate(poaDetails);
		
		
						String acceptorDetails[]=
											{
									poano,
									poaDTO.getAcptrFirstName(),
									poaDTO.getAcptrMiddleName(),
									poaDTO.getAcptrLastName(),
									poaDTO.getAcptrGender(),
									getOracleDate(poaDTO.getAcptrDobDay(), poaDTO.getAcptrDobMonth(), poaDTO.getAcptrDobYear()),
									poaDTO.getAcptrFatherName(),
									poaDTO.getAcptrMotherName(),
									poaDTO.getAcptrSpouseName(),
									poaDTO.getAcptrAddress(),
									poaDTO.getAcptrPhno(),
									poaDTO.getAcptrMobno(),
									poaDTO.getAcptrEmail(),
									poaDTO.getPoaTypeId(),
									CommonUtil.getConvertedDate(poaDTO.getPoaFromDate()),
									CommonUtil.getConvertedDate(poaDTO.getPoaToDate()),
									poaDTO.getStampduty(),
									poaDTO.getStampCode(),
									CommonUtil.getConvertedDate(poaDTO.getDoe()),
									poaDTO.getDocname(),
									CommonUtil.getConvertedDate(poaDTO.getDoa()),
									poaDTO.getSrname(),
									poaDTO.getSroId(),
									poaDTO.getSr_UserId(),
									poaDTO.getSr_UserId(),
									"50008",
									poaDTO.getPoaRegNo(),
									poaDTO.getEstampAppNo()
									/*poaDTO.getEcodePoaTypeId()	*/	
								};
								dbUtility.createPreparedStatement(CommonSQL.INSERTPOAACCEPTORDETAILS);
								flag=dbUtility.executeUpdate(acceptorDetails);
			}
								poaDTO.setPoaAuthNo(poano);
								
								dbUtility.commit();
								
	}
		catch (NullPointerException ne) {
			dbUtility.rollback();
			dbUtility.closeConnection();
			logger.info("NullPointer Exception at getting District List in DAO  "+ ne);
			} catch (Exception e) {
			logger.info("EXCEPTION IN POA INSERTION IN DAO " + e);
			}
	return poano;
}

public String getSRName(String userid)
{
	String srname="";
	String[] uid = {userid};
	try {
		dbUtil = new DBUtility();
		dbUtil.createStatement();
		dbUtil.createPreparedStatement(CommonSQL.GETSRNAME);
		srname=dbUtil.executeQry(uid);
		//poaSR_no= dbUtil.executeQry(CommonSQL.GETSRNAME);
		
	} catch (Exception exception) {
		logger.info("Exception in getSPusers-SPDAo" + exception);
	}
	return srname;

	
	}
public ArrayList getSROName(POAAuthenticationFormDTO poaDTO) throws Exception {
	ArrayList list=new ArrayList();
	ArrayList list1=new ArrayList();
	String srname="";
	try {
		
		dbUtil = new DBUtility();
		String arrdistid[] = { poaDTO.getSr_UserId() };
		dbUtil.createPreparedStatement(CommonSQL.SELECTSROOFFICENAME);
		list1 = dbUtil.executeQuery(arrdistid);
		for(int i=0;i<list1.size();i++)
		{
			list=(ArrayList)list1.get(i);	
			//poaDTO=new POAAuthenticationFormDTO();
			/*poaDTO.setSroName((String)list.get(0));*/
			/*sroid=(String)list.get(0);
			srname=(String)list.get(1);*/
		}
		dbUtil.commit();
	} catch (Exception exception) {
		logger.info("Exception in getSROOfficeCode method in POAAuthenticationFormDAO" + exception);
	}
	finally
	{
		dbUtil.closeConnection();
	}
	return list;
}
public ArrayList getSROOfficecode(String distid) throws Exception {
	ArrayList list=new ArrayList();
	try {
		
		dbUtil = new DBUtility();
		String arrdistid[] = { distid };
		dbUtil.createPreparedStatement(CommonSQL.SELECTSROOFFICECODE);
		list = dbUtil.executeQuery(arrdistid);
		dbUtil.commit();
	} catch (Exception exception) {
		logger.info("Exception in getSROOfficeCode method in POAAuthenticationFormDAO" + exception);
	}
	finally
	{
		dbUtil.closeConnection();
	}
	return list;
}
public ArrayList getSRDistrictCode(String userid) throws Exception {
	ArrayList list=new ArrayList();
	try {
		
		dbUtil = new DBUtility();
		String uid[] = { userid };
		dbUtil.createPreparedStatement(CommonSQL.SELECTSRDISTRICT);
		list = dbUtil.executeQuery(uid);
		dbUtil.commit();
		
	} catch (Exception exception) {
		logger.info("Exception in getSROOfficeCode method in POAAuthenticationFormDAO" + exception);
	}
	finally
	{
		dbUtil.closeConnection();
	}
	return list;
}
public String getpoaSR_NO() throws Exception {
	String poaSR_no = "";
	try {
		dbUtil = new DBUtility();
		dbUtil.createStatement();
		poaSR_no= dbUtil.executeQry(CommonSQL.SELECTPAOSRNO);
		
	} catch (Exception exception) {
		logger.info("Exception in getSPusers-SPDAo" + exception);
	}
	return poaSR_no;

}
public String getNewpoaSR_NO() throws Exception {
	String poaSR_no = "";
	try {
		dbUtil = new DBUtility();
		dbUtil.createStatement();
		dbUtil.executeUpdt(CommonSQL.RESETPOASRNOSEQ);
		dbUtil.createStatement();
		dbUtil.executeUpdt(CommonSQL.RECREATEPOASRNO);
		dbUtil.createStatement();
		poaSR_no= dbUtil.executeQry(CommonSQL.SELECTPAOSRNO);
		dbUtil.commit();
		
	} catch (Exception exception) {
		logger.info("Exception in getSPusers-SPDAo" + exception);
	}
	return poaSR_no;

}
public String getPrevPOANoYear() throws Exception {
	String poa_no = "";
	try {
		dbUtil = new DBUtility();
		dbUtil.createStatement();
		poa_no= dbUtil.executeQry(CommonSQL.SELECTYEAROFPOANO);
		
	} catch (Exception exception) {
		logger.info("Exception in getSPusers-SPDAo" + exception);
	}
	return poa_no;

}
public String getcurrentYear() throws Exception {
	String curryear = "";
	try {
		dbUtil = new DBUtility();
		dbUtil.createStatement();
		curryear= dbUtil.executeQry(CommonSQL.SELECTSYSDATEYEAR);
		
	} catch (Exception exception) {
		logger.info("Exception in getSPusers-SPDAo" + exception);
	}
	return curryear;

}

public String POAAuthenticationNumber(POAAuthenticationFormDTO poaDTO)
{
	/* constant */
	String mpConstant="MP";
	
	String districtCode="";
	String sroCode="";
	String year="";
	String sr_no="";
	String userid=poaDTO.getSr_UserId();
	String dcode="";
	String authenticationNo="";
	//logger.info("USER ID>>>>>"+userid);
	try
	{
	POAAuthenticationFormDAO poaDAO=new POAAuthenticationFormDAO();
	/* disrict code*/
	ArrayList arrayList1=poaDAO.getSRDistrictCode(userid);
	for (int i = 0; i < arrayList1.size(); i++) {
		ArrayList templist = (ArrayList) arrayList1.get(i);
		districtCode = templist.get(0).toString();
		dcode=districtCode;
		if(districtCode.length()==1){
			districtCode="0"+districtCode;
		}
		else if(districtCode.length()==2){
			districtCode=districtCode;
		}
		else
		{
			logger.info("WRONG FORMAT---master data cannot be gr8tr than 2 digit");	
		}
		logger.info("DISTRICT CODE>>>>>"+districtCode);
	}	
	/* SRO OFFICE code */
	ArrayList arrayList = poaDAO.getSROOfficecode(dcode);
	for (int i = 0; i < arrayList.size(); i++) {
		ArrayList templist = (ArrayList) arrayList.get(i);
		sroCode = templist.get(0).toString();
		if(sroCode.length()==1){
			sroCode="00"+sroCode;
		}
		else if(sroCode.length()==2){
			sroCode="0"+sroCode;
		}
		else if(sroCode.length()==3){
			sroCode=sroCode;
		}
		else
		{
			logger.info("WRONG FORMAT---master data cannot be gr8tr than 3 digit");
			}
		logger.info("SRO CODE>>>>>"+sroCode);
	}
	
	year=poaDAO.getcurrentYear();
	
	String prevPOANoYear=poaDAO.getPrevPOANoYear();
	String currentYear=poaDAO.getcurrentYear();
	if(prevPOANoYear.equalsIgnoreCase(currentYear))
	{
		sr_no=poaDAO.getpoaSR_NO();
	}
	else if(!prevPOANoYear.equalsIgnoreCase(currentYear))
	{
		sr_no=poaDAO.getNewpoaSR_NO();
	}
	
	if(sr_no.length()==1){
		sr_no="0000"+sr_no;
	}
	else if(sr_no.length()==2){
		sr_no="000"+sr_no;
	}
	else if(sr_no.length()==3){
		sr_no="00"+sr_no;
	}
	else if(sr_no.length()==4){
		sr_no="0"+sr_no;
	}
	else{
		sr_no=sr_no;
	}
	
/*	Calendar cal = new GregorianCalendar();
	int y = cal.get(Calendar.YEAR);
	year=Integer.toString(y);
	logger.info("YEAR>>>>>>"+year);*/

	
	
/*	sr_no=poaDAO.getpoaSR_NO();
	if(sr_no.length()==1){
		sr_no="0000"+sr_no;
	}
	else if(sr_no.length()==2){
		sr_no="000"+sr_no;
	}
	else if(sr_no.length()==3){
		sr_no="00"+sr_no;
	}
	else if(sr_no.length()==4){
		sr_no="0"+sr_no;
	}
	else if(sr_no.length()==4){
		sr_no=sr_no;
	}*/
		logger.info("SERIAL NUMBER>>>>"+sr_no);
		authenticationNo=mpConstant+districtCode+sroCode+year+sr_no;
		//logger.info("AUTHENTICATION NUMBER>>>>>"+authenticationNo);
	}
	catch(NullPointerException ne)
	{
		logger.info("NullPointer Exception at getting District List in DAO  "+ ne);
	} catch (Exception e) {
		logger.info("Exception at getting District List in DAO " + e);
		}
	return authenticationNo;
}
public synchronized ArrayList getPoaViewDetails(String poaId) 
{
    ArrayList poaList = new ArrayList();  
          ArrayList poaRead = new ArrayList();
          try 
          {
        	  dbUtil = new DBUtility(); 
        	  String[] param={poaId};
        	  String str=CommonSQL.GETPOAVIEWDTLS;
            //dbUtil.createStatement();   
        	  dbUtil.createPreparedStatement(str);
           // poaRead = dbUtil.executeQuery();       
        	  poaRead=dbUtil.executeQuery(param);
          }
          catch (Exception e) {
        	  logger.error("exception in calling at DAO Class at getPoaViewDetails()  " +e);
          }
          finally 
          {
             try
             {
            	 dbUtil.closeConnection();                                  
              }
             catch(Exception e){
            	 logger.error("Exception in Finally Block  "+ e);   
             } 
          } 
         
          if(poaRead!=null) 
          {
              for(int i=0;i<poaRead.size();i++)  
                {
        	  poaList.add((ArrayList)poaRead.get(i));
                }
          }         
       return poaList;

}

public synchronized ArrayList getPoaViewDetails2(String dateFrom,String dateTo) 
{
    ArrayList poaList = new ArrayList();  
          ArrayList poaRead = new ArrayList();
          try 
          {
        	  dbUtil = new DBUtility();         
        	  String[] param={dateFrom,dateTo};
        	  String str=CommonSQL.GETPOAVIEWDTLS2; 
            //dbUtil.createStatement();      
            //poaRead = dbUtil.executeQuery(
        	  dbUtil.createPreparedStatement(str);
        	  poaRead= dbUtil.executeQuery(param);
      
          }
          catch (Exception e) {
        	  logger.error("exception in calling at DAO Class at getPoaViewDetails()  " +e);
          }
          finally 
          {
             try
             {
            	 dbUtil.closeConnection();                                  
              }
             catch(Exception e){
            	 logger.error("Exception in Finally Block  "+ e);   
             } 
          } 
         
          if(poaRead!=null) 
          {
              for(int i=0;i<poaRead.size();i++)  
                {
        	  poaList.add((ArrayList)poaRead.get(i));
                }
          }         
       return poaList;
}

public synchronized ArrayList getPoaDetails(String _poaId)
{
    
    ArrayList poaList = new ArrayList();  
          ArrayList potRead = new ArrayList();
          try 
          {
         	  dbUtil = new DBUtility();            
            //dbUtil.createStatement();  
          //  sql = CommonSQL.SELECT_POTDETAILS;
         //  potRead = dbUtil.executeQuery("select FIRST_NAME,MIDDLE_NAME,LAST_NAME,DISTRICT_NAME,to_char(to_date(DATE_OF_OBJECTION,'DD-MM-RRRR'),'DD-MM-RRRR'),TRANSACTION_ID,OLD_NEW,REGISTRATION_NUMBER,OLD_REG_VOLUME_NO,OLD_REG_BOOK_NO,OLD_REG_NO,ESTAMP_CODE,STAMP_FEE_IN_DOC,STAMP_FEE_REQUIRED,DOC_IMPOUNDED,PO_COMMENTS,CASE_ELIGIBILITY,DR_COMMENTS from igrs_public_officer_tool_txn po,IGRS_DISTRICT_MASTER dm where po.DISTRICT_ID=dm.DISTRICT_ID and TRANSACTION_ID='"+potId+"'");
           
         	  String str=CommonSQL.GETPOADTLS;
         	  String[] param={_poaId};
         	  potRead = dbUtil.executeQuery(param);
               
          }
          catch (Exception e) {
        	  logger.error("exception in calling at DAO Class at getPotDetails()  " +e);
          }
          finally 
          {
             try
             {
            	 dbUtil.closeConnection();                                  
              }
             catch(Exception e){
             
             } 
          } 
        
          if(potRead!=null) 
          {
              for(int i=0;i<potRead.size();i++)  
                {
        	  poaList.add((ArrayList)potRead.get(i));
                }
          }         
       return poaList;

}

//*****************************************Added by SIMRAN*************************************************//

/**
 * 
 * @param poaAuthNo
 * @return ArrayList
 * @throws Exception
 */
public ArrayList getPOAApplicantDetlsForView(String poaAuthNo) throws Exception {
	ArrayList list=new ArrayList();
	
	try {
		
		dbUtil = new DBUtility();
		String[] param={poaAuthNo};
		//dbUtil.createStatement();
		String sql = CommonSQL.GET_POA_APPLICANT_DETAILS;
		dbUtil.createPreparedStatement(sql);
		logger.debug("sql");
		list=dbUtil.executeQuery(param);
		//list = dbUtil.executeQuery(sql);
	} catch (Exception exception) {
		logger.info("Exception in getPOAApplicantDetlsForView method in POAAuthenticationFormDAO" + exception);
	}
	finally
	{
		dbUtil.closeConnection();
	}
	return list;
}

/**
 * 
 * @param poaAuthNo
 * @return ArrayList
 * @throws Exception
 */
public ArrayList getPOAAwardeeDetlsForView(String poaAuthNo) throws Exception {
	ArrayList list=new ArrayList();
	
	try {
		
		dbUtil = new DBUtility();
		//dbUtil.createStatement();
		String[] param={poaAuthNo};
		String sql = CommonSQL.GET_POA_AWARDEE_DETLS;
		dbUtil.createPreparedStatement(sql);
		list=dbUtil.executeQuery(param);
		//list = dbUtil.executeQuery(sql);
	} catch (Exception exception) {
		logger.info("Exception in getPOAAwardeeDetlsForView method in POAAuthenticationFormDAO" + exception);
	}
	finally
	{
		dbUtil.closeConnection();
	}
	return list;
}

/**
 * 
 * @param poaAuthNo
 * @return ArrayList
 * @throws Exception
 */
public ArrayList getPOAAcceptorDetlsForView(String poaAuthNo) throws Exception {
	ArrayList list=new ArrayList();
	try {
		
		dbUtil = new DBUtility();
		String[] param={poaAuthNo};
		//dbUtil.createStatement();
		
		String sql = CommonSQL.GET_POA_ACCEPTOR_DETLS;
		dbUtil.createPreparedStatement(sql);
		//list = dbUtil.executeQuery(sql);
		list=dbUtil.executeQuery(param);
	} catch (Exception exception) {
		logger.info("Exception in getPOAAcceptorDetlsForView method in POAAuthenticationFormDAO" + exception);
	}
	finally
	{
		dbUtil.closeConnection();
	}
	return list;
}

/**
 * 
 * @param poaAuthNo
 * @return ArrayList
 * @throws Exception
 */
public ArrayList getPOACommonDetlsForView(String poaAuthNo) throws Exception {
	ArrayList list=new ArrayList();
	try {
		
		dbUtil = new DBUtility();
		String[] param={poaAuthNo};
		//dbUtil.createStatement();
		
		String sql = CommonSQL.GET_POA_COMMON_DETLS;
		dbUtil.createPreparedStatement(sql);
		//list = dbUtil.executeQuery(sql);
		list=dbUtil.executeQuery(param);
	} catch (Exception exception) {
		logger.info("Exception in getPOACommonDetlsForView method in POAAuthenticationFormDAO" + exception);
	}
	finally
	{
		dbUtil.closeConnection();
	}
	return list;
}


} // DAO CLOSE
