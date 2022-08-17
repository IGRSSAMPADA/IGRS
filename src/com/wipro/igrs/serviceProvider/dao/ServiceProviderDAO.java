package com.wipro.igrs.serviceProvider.dao;
/**
 * ===========================================================================
 * File           :   ServiceProviderDAO.java
 * Description    :   Represents the DAO Class

 * Author         :   Lavi Tripathi
 * Created Date   :   July 22, 2013

 * ===========================================================================
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.newPropvaluation.sql.PropertyValuationSQL;
import com.wipro.igrs.serviceProvider.constant.ServiceProviderConstant;
import com.wipro.igrs.serviceProvider.dto.ServiceProviderDTO;
import com.wipro.igrs.serviceProvider.form.ServiceProviderForm;
import com.wipro.igrs.serviceProvider.sql.ServiceProviderSQL;
import com.wipro.igrs.baseaction.constant.Constants;

public class ServiceProviderDAO 
{

	DBUtility dbUtil;
	
    ArrayList list  =  null;
    PreparedStatement pst = null;
    Connection con = null;
    private DBUtility dbUtility = null;
    private Logger logger = 
		(Logger) Logger.getLogger(ServiceProviderDAO.class);

    ServiceProviderConstant CommonConstant=new ServiceProviderConstant();
    public  boolean insertCriteria (ServiceProviderForm objServiceProviderForm) throws Exception{
		 
  	  boolean transactionflag = false;
  	  String addCriteria[]= new String[5];

  	  DBUtility dbUtil = null;
  	  try{
  		  dbUtil = new DBUtility();
  		  dbUtil.setAutoCommit(false);
  		  

  	      //String SQL = "select IGRS_SP_ADD_CRITERIA_SEQ.nextval from dual";
  	      dbUtil.createStatement();
  		  String number = dbUtil.executeQry(ServiceProviderSQL.GET_SEQ_CRITERIA);
  		  
  		addCriteria[0] = number;
  		addCriteria[1] = (String) objServiceProviderForm.getObjServiceProviderDTO().getCriteria();
  		addCriteria[2] = (String) objServiceProviderForm.getObjServiceProviderDTO().getRadioSelect();
  		addCriteria[3] = "A";
  		addCriteria[4] = (String) objServiceProviderForm.getObjServiceProviderDTO().getUid();
  		  
  		  dbUtil.createPreparedStatement(ServiceProviderSQL.SP_CRITERIA_INSERT);
  		  dbUtil.executeUpdate(addCriteria);
  	      transactionflag = true;
  	      dbUtil.commit();				     
  		
  	 }
  	   catch(Exception e){
  		   e.printStackTrace();
  		   dbUtil.rollback();
  		logger.error(" Exception at serviceProviderDAO in DAO " + e);
  		throw e;
  		}
  	   
  	   finally {
  			 try{	    
  				 dbUtil.closeConnection();
  			 }
  			 catch (Exception e) {
  			 logger.error("Exception at serviceProviderDAO in DAO  " + e);
  			 }		
  	       }
  	   return transactionflag;   
  	 }
    
    public ArrayList getCriterias(String value)
	{
		ArrayList criteriaList = new ArrayList();
		
		
		
		String[] param={value};
		
		try {
			dbUtil = new DBUtility();
			
					String SQL = ServiceProviderSQL.GET_SP_CRITERIA;
					dbUtil.createPreparedStatement(SQL);
				
					
					try
					{	
						
						criteriaList=dbUtil.executeQuery(param);
				            logger.debug("-----> in ServiceProviderDAO - getCriterias() after dbUtil.executeQuery(SQL)");
				           
				            criteriaList.trimToSize();
			
		} catch (Exception x) {
			logger.debug(x);
			x.printStackTrace();
		} 
		
		finally {
			
			try {
				
				dbUtil.closeConnection();
			} 
			catch (Exception ex) {
				logger.debug(ex);
				ex.printStackTrace();
			}
		}
		
	}
		catch(Exception e){
			logger.debug(e);
		}
		return criteriaList;
		}
    
    public boolean deleteCriteria(String id)
	{
		boolean delCriteria = false;
		
		
		
		String[] param={id};
		
		try {
			dbUtil = new DBUtility();
			
					String SQL = ServiceProviderSQL.DELETE_SP_CRITERIA;
					dbUtil.createPreparedStatement(SQL);
				
					
					try
					{	
						
						delCriteria=dbUtil.executeUpdate(param);
				            logger.debug("-----> in ServiceProviderDAO - deleteCriteria() after dbUtil.executeUpdate(param)");
			
		} catch (Exception x) {
			logger.debug(x);
			x.printStackTrace();
		} 
		
		finally {
			
			try {
				
				dbUtil.closeConnection();
			} 
			catch (Exception ex) {
				logger.debug(ex);
				ex.printStackTrace();
			}
		}
		
	}
		catch(Exception e){
			logger.debug(e);
		}
		return delCriteria;
		}
    
    public boolean editCriteria(ServiceProviderForm objServiceProviderForm) throws Exception
	{
		 
    	  boolean transactionflag = false;
    	  String edtCriteria[]= new String[3];

    	  DBUtility dbUtil = null;
    	  try{
    		  dbUtil = new DBUtility();
    		  dbUtil.setAutoCommit(false);
    		  
    		  
    		  edtCriteria[0] = (String) objServiceProviderForm.getObjServiceProviderDTO().getCriteria();
    		  edtCriteria[1] = (String) objServiceProviderForm.getObjServiceProviderDTO().getUid();
    		  edtCriteria[2] = (String) objServiceProviderForm.getObjServiceProviderDTO().getCriteriaId();
    		  
    		  dbUtil.createPreparedStatement(ServiceProviderSQL.UPDATE_SP_CRITERIA);
    		  dbUtil.executeUpdate(edtCriteria);
    	      transactionflag = true;
    	      dbUtil.commit();				     
    		
    	 }
    	   catch(Exception e){
    		   e.printStackTrace();
    		   dbUtil.rollback();
    		logger.error(" Exception at serviceProviderDAO in DAO " + e);
    		throw e;
    		}
    	   
    	   finally {
    			 try{	    
    				 dbUtil.closeConnection();
    			 }
    			 catch (Exception e) {
    			 logger.error("Exception at serviceProviderDAO in DAO  " + e);
    			 }		
    	       }
    	   return transactionflag;   
    	 }
    
    public  boolean insertTandC (ServiceProviderForm objServiceProviderForm) throws Exception{
		 
    	  boolean transactionflag = false;
    	  String addTandC[]= new String[5];

    	  DBUtility dbUtil = null;
    	  try{
    		  dbUtil = new DBUtility();
    		  dbUtil.setAutoCommit(false);
    		  
    	      //String SQL = "select IGRS_SP_ADD_TERMS_CONDTNS_SEQ.nextval from dual";
    	      dbUtil.createStatement();
    		  String number = dbUtil.executeQry(ServiceProviderSQL.GET_SEQ_TERMS_COND);
    		  
    		  addTandC[0] = number;
    		  addTandC[1] = (String) objServiceProviderForm.getObjServiceProviderDTO().getCriteria();
    		  addTandC[2] = (String) objServiceProviderForm.getObjServiceProviderDTO().getRadioSelect();
    		  addTandC[3] = "A";
    		  addTandC[4] = (String) objServiceProviderForm.getObjServiceProviderDTO().getUid();
    		  
    		  dbUtil.createPreparedStatement(ServiceProviderSQL.SP_TERMS_CONDTNS_INSERT);
    		  dbUtil.executeUpdate(addTandC);
    	      transactionflag = true;
    	      dbUtil.commit();				     
    		
    	 }
    	   catch(Exception e){
    		   e.printStackTrace();
    		   dbUtil.rollback();
    		logger.error(" Exception at serviceProviderDAO in DAO " + e);
    		throw e;
    		}
    	   
    	   finally {
    			 try{	    
    				 dbUtil.closeConnection();
    			 }
    			 catch (Exception e) {
    			 logger.error("Exception at serviceProviderDAO in DAO  " + e);
    			 }		
    	       }
    	   return transactionflag;   
    	 }
    
    public ArrayList getTC(String value)
	{
		ArrayList tClist = new ArrayList();
		
		
		
		String[] param={value};
		
		try {
			dbUtil = new DBUtility();
			
					String SQL = ServiceProviderSQL.GET_SP_TC;
					dbUtil.createPreparedStatement(SQL);
				
					
					try
					{	
						
						tClist=dbUtil.executeQuery(param);
				            logger.debug("-----> in ServiceProviderDAO - getTC() after dbUtil.executeQuery(SQL)");
				           
				            tClist.trimToSize();
			
		} catch (Exception x) {
			logger.debug(x);
			x.printStackTrace();
		} 
		
		finally {
			
			try {
				
				dbUtil.closeConnection();
			} 
			catch (Exception ex) {
				logger.debug(ex);
				ex.printStackTrace();
			}
		}
		
	}
		catch(Exception e){
			logger.debug(e);
		}
		return tClist;
		}
    
    public boolean deleteTC(String id)
	{
		boolean delCriteria = false;
		
		
		
		String[] param={id};
		
		try {
			dbUtil = new DBUtility();
			
					String SQL = ServiceProviderSQL.DELETE_SP_TC;
					dbUtil.createPreparedStatement(SQL);
				
					
					try
					{	
						
						delCriteria=dbUtil.executeUpdate(param);
				            logger.debug("-----> in ServiceProviderDAO - deleteCriteria() after dbUtil.executeUpdate(param)");
			
		} catch (Exception x) {
			logger.debug(x);
			x.printStackTrace();
		} 
		
		finally {
			
			try {
				
				dbUtil.closeConnection();
			} 
			catch (Exception ex) {
				logger.debug(ex);
				ex.printStackTrace();
			}
		}
		
	}
		catch(Exception e){
			logger.debug(e);
		}
		return delCriteria;
		}
    
    public boolean editTC(ServiceProviderForm objServiceProviderForm) throws Exception
	{
		 
    	  boolean transactionflag = false;
    	  String edtTC[]= new String[3];

    	  DBUtility dbUtil = null;
    	  try{
    		  dbUtil = new DBUtility();
    		  dbUtil.setAutoCommit(false);
    		  
    		  
    		  edtTC[0] = (String) objServiceProviderForm.getObjServiceProviderDTO().getCriteria();
    		  edtTC[1] = (String) objServiceProviderForm.getObjServiceProviderDTO().getUid();
    		  edtTC[2] = (String) objServiceProviderForm.getObjServiceProviderDTO().getTcId();
    		  
    		  dbUtil.createPreparedStatement(ServiceProviderSQL.UPDATE_SP_TC);
    		  dbUtil.executeUpdate(edtTC);
    	      transactionflag = true;
    	      dbUtil.commit();				     
    		
    	 }
    	   catch(Exception e){
    		   e.printStackTrace();
    		   dbUtil.rollback();
    		logger.error(" Exception at serviceProviderDAO in DAO " + e);
    		throw e;
    		}
    	   
    	   finally {
    			 try{	    
    				 dbUtil.closeConnection();
    			 }
    			 catch (Exception e) {
    			 logger.error("Exception at serviceProviderDAO in DAO  " + e);
    			 }		
    	       }
    	   return transactionflag;   
    	 }
    
    public ArrayList getSPDetails(String userID,String lang) throws Exception
    {

		ArrayList spDetailsList = new ArrayList();
		
		
		
		String[] param={userID};
		String SQL ="";
		try {
			dbUtil = new DBUtility();
			if("en".equalsIgnoreCase(lang))
			{
					SQL = ServiceProviderSQL.GET_SP_DETLS_RU;
			}
			else
			{
				SQL = ServiceProviderSQL.GET_SP_DETLS_RU_H;
			}
					dbUtil.createPreparedStatement(SQL);
				
					try
					{	
						
						spDetailsList=dbUtil.executeQuery(param);
				            logger.debug("-----> in ServiceProviderDAO - getSPDetails() after dbUtil.executeQuery(SQL)");
				           
				            spDetailsList.trimToSize();
			
		} catch (Exception x) {
			logger.debug(x);
			x.printStackTrace();
		} 
		
		finally {
			
			try {
				
				dbUtil.closeConnection();
			} 
			catch (Exception ex) {
				logger.debug(ex);
				ex.printStackTrace();
			}
		}
		
	}
		catch(Exception e){
			logger.debug(e);
		}
		return spDetailsList;
		
    	
    }
    
    public ArrayList getSpType(String lang) throws Exception
    {
    	ArrayList spTypeLst = new ArrayList();
    	String SQL = "";
    	try {
    		dbUtil = new DBUtility();
    		
    		if("en".equalsIgnoreCase(lang))
    		{
    		SQL = ServiceProviderSQL.GET_SP_TYPE_LIST;
    		}
    		else
    		{
    		SQL = ServiceProviderSQL.GET_SP_TYPE_LIST_H;
    		}
    		
    		
    		dbUtil.createStatement();
    			
    			try
    			{
    				spTypeLst=dbUtil.executeQuery(SQL);
    				logger.debug("-----> in ServiceProviderDAO - getSPDetails() after dbUtil.executeQuery(SQL)");
    				
    				spTypeLst.trimToSize();
    			}
    			catch (Exception x) {
    				logger.debug(x);
    				x.printStackTrace();
    			} 
    			
   finally {
	   
	   		try {
	   			dbUtil.closeConnection();
	   			}
	   				catch (Exception x) {
	   				logger.debug(x);
	   				x.printStackTrace();
	   				} 
   			}
    		
    	}
    	
    	catch (Exception e) {
    		logger.debug(e);
		} 
    	return spTypeLst;
    }
    
    public ArrayList getDistrict(String lang) throws Exception
    {
    	ArrayList districtList = new ArrayList();
    	String SQL="";
    	try {
    		dbUtil = new DBUtility();
    		if("en".equalsIgnoreCase(lang))
    		{
    		SQL = ServiceProviderSQL.GET_DISTRICT_LIST;
    		}
    		else
    		{
    			SQL = ServiceProviderSQL.GET_DISTRICT_LIST_H;	
    		}
    		dbUtil.createStatement();
    			
    			try
    			{
    				districtList=dbUtil.executeQuery(SQL);
    				logger.debug("-----> in ServiceProviderDAO - getDistrict() after dbUtil.executeQuery(SQL)");
    				
    				districtList.trimToSize();
    			}
    			catch (Exception x) {
    				logger.debug(x);
    				x.printStackTrace();
    			} 
    			
   finally {
	   
	   		try {
	   			dbUtil.closeConnection();
	   			}
	   				catch (Exception x) {
	   				logger.debug(x);
	   				x.printStackTrace();
	   				} 
   			}
    		
    	}
    	
    	catch (Exception e) {
    		logger.debug(e);
		} 
    	return districtList;
    }
    
    public ArrayList getTehsil(String disttId,String lang) throws Exception
    {
    	ArrayList tehsilList = new ArrayList();
    	String[] param = {disttId};
    	String SQL="";
    	try {
    		dbUtil = new DBUtility();
    		if("en".equalsIgnoreCase(lang))
    		{
    		SQL = ServiceProviderSQL.GET_TEHSIL_LIST;
    		}
    		else
    		{
    			SQL = ServiceProviderSQL.GET_TEHSIL_LIST_H;
    		}
    		dbUtil.createPreparedStatement(SQL);
    			
    			try
    			{
    				tehsilList=dbUtil.executeQuery(param);
    				logger.debug("-----> in ServiceProviderDAO - getTehsil() after dbUtil.executeQuery(SQL)");
    				
    				tehsilList.trimToSize();
    			}
    			catch (Exception x) {
    				logger.debug(x);
    				x.printStackTrace();
    			} 
    			
   finally {
	   
	   		try {
	   			dbUtil.closeConnection();
	   			}
	   				catch (Exception x) {
	   				logger.debug(x);
	   				x.printStackTrace();
	   				} 
   			}
    		
    	}
    	
    	catch (Exception e) {
    		logger.debug(e);
		} 
    	return tehsilList;
    }
    
    public ArrayList getWardPatwari(String tehsilId,String lang) throws Exception
    {
    	ArrayList wardPatwariList = new ArrayList();
    	String[] param = {tehsilId};
    	String SQL="";
    	try {
    		dbUtil = new DBUtility();
    		if("en".equalsIgnoreCase(lang))
    		{
    		SQL = ServiceProviderSQL.GET_WARD_PATWARI_LIST;
    		}
    		else
    		{
    			SQL = ServiceProviderSQL.GET_WARD_PATWARI_LIST_H;	
    		}
    		dbUtil.createPreparedStatement(SQL);
    			
    			try
    			{
    				wardPatwariList=dbUtil.executeQuery(param);
    				logger.debug("-----> in ServiceProviderDAO - getWardPatwari() after dbUtil.executeQuery(SQL)");
    				
    				wardPatwariList.trimToSize();
    			}
    			catch (Exception x) {
    				logger.debug(x);
    				x.printStackTrace();
    			} 
    			
   finally {
	   
	   		try {
	   			dbUtil.closeConnection();
	   			}
	   				catch (Exception x) {
	   				logger.debug(x);
	   				x.printStackTrace();
	   				} 
   			}
    		
    	}
    	
    	catch (Exception e) {
    		logger.debug(e);
		} 
    	return wardPatwariList;
    }
    
    public ArrayList getMohallaVillage(String wardId,String lang) throws Exception
    {
    	ArrayList mohallaVillageList = new ArrayList();
    	String[] param = {wardId};
    	String SQL="";
    	try {
    		dbUtil = new DBUtility();
    		if("en".equalsIgnoreCase(lang))
    		{
    		SQL = ServiceProviderSQL.GET_MOHALLA_VILLAGE_LIST;
    		}
    		else
    		{
    			SQL = ServiceProviderSQL.GET_MOHALLA_VILLAGE_LIST_H;
    		}
    		dbUtil.createPreparedStatement(SQL);
    			
    			try
    			{
    				mohallaVillageList=dbUtil.executeQuery(param);
    				logger.debug("-----> in ServiceProviderDAO - getMohallaVillage() after dbUtil.executeQuery(SQL)");
    				
    				mohallaVillageList.trimToSize();
    			}
    			catch (Exception x) {
    				logger.debug(x);
    				x.printStackTrace();
    			} 
    			
   finally {
	   
	   		try {
	   			dbUtil.closeConnection();
	   			}
	   				catch (Exception x) {
	   				logger.debug(x);
	   				x.printStackTrace();
	   				} 
   			}
    		
    	}
    	
    	catch (Exception e) {
    		logger.debug(e);
		} 
    	return mohallaVillageList;
    }
    
    
    public  boolean insertSpDetls (ServiceProviderForm objServiceProviderForm) throws Exception{
		 
    	  boolean transactionflag = false;
    	  //String insertSpDetls[]= new String[18];
    	  String insertSpDetls[]= new String[22];
    	  String insertPayDetls[] = new String[6];
    	  
    	  Date date = new Date();
    	  Format yearformat  = new SimpleDateFormat("yyyy");
    	  Format monthformat = new SimpleDateFormat("MM");
    	  Format dateformat  = new SimpleDateFormat("dd");
    	  String dfmt = dateformat.format(date);
    	  String yfmt = yearformat.format(date);
    	  String mfmt = monthformat.format(date);

    	  DBUtility dbUtil = null;
    	  try{
    		  dbUtil = new DBUtility();
    		  dbUtil.setAutoCommit(false);
    		  
    		//for  dropping the sequence monthly for IGRS_SP_DETLS table
    		  
    		  //String SQL1 = "SELECT TO_CHAR(SYSDATE, 'DD/MM/YYYY') FROM DUAL";
    		  dbUtil.createStatement();
    		  logger.debug("******* Statement created");
    		  
    		  String number2 = dbUtil.executeQry(ServiceProviderSQL.GET_TODAYS_DATE);
    		  
    		  logger.debug("*******" +number2);
    		  String subDate = number2.substring(0, 2);
    		  
    		  logger.debug("******* Substring obtained" +subDate);
    		  
    		  if (subDate.equalsIgnoreCase("01"))
    		  {
    			  //String sql= "SELECT COUNT(REQUEST_NUMBER) FROM IGRS_SP_DETLS WHERE TRUNC(CREATED_DATE)= TRUNC(SYSDATE)";
    			  
    			  dbUtil.createStatement();
    			  String countRequestNo = dbUtil.executeQry(ServiceProviderSQL.GET_COUNT_OF_SEQ_SP_DETLS);
    			  
    			  if (countRequestNo.equalsIgnoreCase("0"))
    			  {
    				logger.debug("in if clause");
    				//String drop_query = "DROP SEQUENCE IGRS_SP_REQUEST_NUMBER_SEQ";		
    		
    				dbUtil.createStatement();
    				dbUtil.executeUpdate(ServiceProviderSQL.DROP_QRY_FOR_SEQ_REQ_NMBR);
    				//String create_query = "CREATE SEQUENCE IGRS_SP_REQUEST_NUMBER_SEQ START WITH 1 INCREMENT BY 1 MAXVALUE 9999999999 MINVALUE 1 NOCYCLE  NOCACHE ORDER";
    				
    				dbUtil.createStatement();
    				dbUtil.executeUpdate(ServiceProviderSQL.CREATE_SEQ_FOR_SEQ_REQ_NMBR);
    			  }

    		  }
    		  
    	      //String SQL = "select IGRS_SP_REQUEST_NUMBER_SEQ.nextval from dual";
    	      dbUtil.createStatement();
    		  String number = dbUtil.executeQry(ServiceProviderSQL.GET_SEQ_REQUEST_NUMBER);
    		  
    		  if (number.length()==1)
    		  {
    			  number="000"+number;
    		  }
    		  else if (number.length()==2)
    		  {
    			  number="00"+number;
    		  }
    		  else if (number.length()==3)
    		  {
    			  number="0"+number;
    		  }
    		  
    		  String distt = objServiceProviderForm.getObjServiceProviderDTO().getDistrict();
    		  if(distt.length()==1){
    			  distt = "0"+distt;
    		  }
    		  
    		  String spType = objServiceProviderForm.getObjServiceProviderDTO().getSpType();
    		  if(spType.length()==1){
    			  spType = "0"+spType;
    		  }
    		  
    		  String number1=spType+distt+dfmt+mfmt+yfmt+number;
    		  objServiceProviderForm.getObjServiceProviderDTO().setReqNo(number1);
    		  
    		  insertSpDetls[0] = number1;
    		  insertSpDetls[1] = spType;
    		   
    		  
    		  if (spType.equalsIgnoreCase("02") || spType.equalsIgnoreCase("03") || spType.equalsIgnoreCase("04"))
    		  {
    			  insertSpDetls[2] = (String) objServiceProviderForm.getObjServiceProviderDTO().getBankName();
        		  insertSpDetls[3] = (String) objServiceProviderForm.getObjServiceProviderDTO().getAuthPersName();
        		  insertSpDetls[4] = (String) objServiceProviderForm.getObjServiceProviderDTO().getDesignation();
        		  insertSpDetls[5] = (String) objServiceProviderForm.getObjServiceProviderDTO().getAddressSp();
        		  insertSpDetls[6] = (String) objServiceProviderForm.getObjServiceProviderDTO().getDistrict();
        		  insertSpDetls[7] = (String) objServiceProviderForm.getObjServiceProviderDTO().getTehsil();
        		  /*insertSpDetls[8] = (String) objServiceProviderForm.getObjServiceProviderDTO().getWard();
        		  insertSpDetls[9] = (String) objServiceProviderForm.getObjServiceProviderDTO().getMohalla();*/
        		  insertSpDetls[8] = (String) objServiceProviderForm.getObjServiceProviderDTO().getWardPatwariId().split("~")[0];
        		  insertSpDetls[9] = (String) objServiceProviderForm.getObjServiceProviderDTO().getColonyId().split("~")[0];
        		  insertSpDetls[10] = null;
        		  insertSpDetls[11] = null;
        		  insertSpDetls[12] = (String) objServiceProviderForm.getObjServiceProviderDTO().getRadioSelectExp();
        		  insertSpDetls[13] = (String) objServiceProviderForm.getObjServiceProviderDTO().getRadioSelectCh();
        		  insertSpDetls[14] = (String) objServiceProviderForm.getObjServiceProviderDTO().getAmount();
    		  }
    		  
    		  if (spType.equalsIgnoreCase("01"))
    		  {
    			  insertSpDetls[2] = null;
        		  insertSpDetls[3] = null;
        		  insertSpDetls[4] = null;
    			  insertSpDetls[5] = (String) objServiceProviderForm.getObjServiceProviderDTO().getAddressSp();
        		  insertSpDetls[6] = (String) objServiceProviderForm.getObjServiceProviderDTO().getDistrict();
        		  insertSpDetls[7] = (String) objServiceProviderForm.getObjServiceProviderDTO().getTehsil();
        		  /*insertSpDetls[8] = (String) objServiceProviderForm.getObjServiceProviderDTO().getWard();
        		  insertSpDetls[9] = (String) objServiceProviderForm.getObjServiceProviderDTO().getMohalla();*/
        		  insertSpDetls[8] = (String) objServiceProviderForm.getObjServiceProviderDTO().getWardPatwariId().split("~")[0];
        		  insertSpDetls[9] = (String) objServiceProviderForm.getObjServiceProviderDTO().getColonyId().split("~")[0];
        		  insertSpDetls[10] = (String) objServiceProviderForm.getObjServiceProviderDTO().getEducation();
        		  insertSpDetls[11] = (String) objServiceProviderForm.getObjServiceProviderDTO().getLangKnown();
        		  insertSpDetls[12] = (String) objServiceProviderForm.getObjServiceProviderDTO().getRadioSelectExp();
        		  insertSpDetls[13] = (String) objServiceProviderForm.getObjServiceProviderDTO().getRadioSelectCh();
        		  insertSpDetls[14] = (String) objServiceProviderForm.getObjServiceProviderDTO().getAmount();  
    		  }
    		  
    		  insertSpDetls[15] = (String) objServiceProviderForm.getObjServiceProviderDTO().getUid();
    		  insertSpDetls[16] = "A";	
    		  insertSpDetls[17] = (String) objServiceProviderForm.getObjServiceProviderDTO().getNewOrRenewalFlag();
    		  //added by shruti---1 st april 2014
    		  insertSpDetls[18] = (String) objServiceProviderForm.getObjServiceProviderDTO().getPanCard();
    		  insertSpDetls[19] = (String) objServiceProviderForm.getObjServiceProviderDTO().getForm60();
    		  insertSpDetls[20] = (String) objServiceProviderForm.getObjServiceProviderDTO().getAreaId();
    		  insertSpDetls[21] = (String) objServiceProviderForm.getObjServiceProviderDTO().getSubAreaId();
    		  
    		  
    		  
    		  ///end
    		  //String SQL2 = "select IGRS_SP_PAYMENT_ID_SEQ.nextval from dual";
    	      dbUtil.createStatement();
    		  String number3 = dbUtil.executeQry(ServiceProviderSQL.GET_SEQ_PAYMENT_ID);
    		  
    		  insertPayDetls[0]= number3;
    		  insertPayDetls[1]= number1;
    		  insertPayDetls[2]= "0";
    		  insertPayDetls[3]= "I";
    		  insertPayDetls[4]= (String) objServiceProviderForm.getObjServiceProviderDTO().getUid();
    		  insertPayDetls[5]= "0";
    		  
    		  
    		  dbUtil.createPreparedStatement(ServiceProviderSQL.INSERT_SP_DETLS);
    		  dbUtil.executeUpdate(insertSpDetls);
    	      transactionflag = true;
    	      dbUtil.commit();	
    	      
    	      dbUtil.createPreparedStatement(ServiceProviderSQL.INSERT_SP_PAYMENT_DETLS);
    		  dbUtil.executeUpdate(insertPayDetls);
    	      transactionflag = true;
    	      dbUtil.commit();	
    	    //added by shruti---6 march 2014
    	      if(objServiceProviderForm.getObjServiceProviderDTO().getNewOrRenewalFlag()!=null && "New".equalsIgnoreCase((String)objServiceProviderForm.getObjServiceProviderDTO().getNewOrRenewalFlag()))
    	      {
    	    	  //email integration
    	      String[] uid={(String) objServiceProviderForm.getObjServiceProviderDTO().getUid()};
    	      dbUtil.createPreparedStatement(ServiceProviderSQL.GETUSERNAME);
    	      String userName=dbUtil.executeQry(uid);
    	      
    	      String status = new String();
    	      String userId=(String) objServiceProviderForm.getObjServiceProviderDTO().getUid();
    	      String subject=CommonConstant.EMAILSUB_LICENSEAPP;
    	      String content="This is in reference of request Id "+number1+ ".Service Provider " +userName+" has requested for New License";
    	        try {
    	        	
    		           ServiceProviderDAO dao=new ServiceProviderDAO();
    		           status  = dao.insertEmailData(userId, subject, content);

    	             } catch (Exception x) {
    		               logger.error(x);
    	               }
    	      dbUtil.commit();
    	      //sms integration
    	      dbUtil.createPreparedStatement(ServiceProviderSQL.GETMOBILENUMBER);
    	      String mobileNo=dbUtil.executeQry(uid);
    	        try {
    	        	
    		           ServiceProviderDAO dao=new ServiceProviderDAO();
    		           status  = dao.insertSMSData(userId, mobileNo, content);

    	             } catch (Exception x) {
    		               logger.error(x);
    	               }
    	      dbUtil.commit();
    	      //end
    	      
    	      }
    	      else if(objServiceProviderForm.getObjServiceProviderDTO().getNewOrRenewalFlag()!=null && "Renewed".equalsIgnoreCase((String)objServiceProviderForm.getObjServiceProviderDTO().getNewOrRenewalFlag()))
    	      {
    	      String[] uid={(String) objServiceProviderForm.getObjServiceProviderDTO().getUid()};
    	      dbUtil.createPreparedStatement(ServiceProviderSQL.GETUSERNAME);
    	      String userName=dbUtil.executeQry(uid);
    	      
    	      String status = new String();
    	      String userId=(String) objServiceProviderForm.getObjServiceProviderDTO().getUid();
    	      String subject=CommonConstant.EMAILSUB_LICRENEWAL;
    	      String content="This is in reference of request Id "+number1+ ".Service Provider " +userName+" has requested for renewal of License";
    	        try {
    	        	
    		           ServiceProviderDAO dao=new ServiceProviderDAO();
    		           status  = dao.insertEmailData(userId, subject, content);

    	             } catch (Exception x) {
    		               logger.error(x);
    	               }
    	      dbUtil.commit();
    	      //sms integration
    	      dbUtil.createPreparedStatement(ServiceProviderSQL.GETMOBILENUMBER);
    	      String mobileNo=dbUtil.executeQry(uid);
    	        try {
    	        	
    		           ServiceProviderDAO dao=new ServiceProviderDAO();
    		           status  = dao.insertSMSData(userId, mobileNo, content);

    	             } catch (Exception x) {
    		               logger.error(x);
    	               }
    	      dbUtil.commit();
    	      }
    	      //end
    		
    	 }
    	   catch(Exception e){
    		   e.printStackTrace();
    		   dbUtil.rollback();
    		logger.error(" Exception at serviceProviderDAO in DAO " + e);
    		throw e;
    		}
    	   
    	   finally {
    			 try{	    
    				 dbUtil.closeConnection();
    			 }
    			 catch (Exception e) {
    			 logger.error("Exception at serviceProviderDAO in DAO  " + e);
    			 }		
    	       }
    	   return transactionflag;   
    	 }
    
    public  boolean insertSpDocDetls1 (ServiceProviderForm objServiceProviderForm) throws Exception{
		 
  	  boolean transactionflag = false;
  	  String docTbl[]= new String[6];

  	  DBUtility dbUtil = null;
     
  	  try{
  		  dbUtil = new DBUtility();
  		  dbUtil.setAutoCommit(false);
  		     
		      dbUtil.createStatement();
			  String number2 = dbUtil.executeQry(ServiceProviderSQL.GET_SEQ_DOCUMENT_UPLOAD_ID);
			  docTbl[0]=number2;
			  docTbl[1]=objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
			  docTbl[2]=objServiceProviderForm.getObjServiceProviderDTO().getDocName();
			  docTbl[3]=(String)objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
			  docTbl[4]="A";
			  docTbl[5]=(String)objServiceProviderForm.getObjServiceProviderDTO().getUid();
			  
			  dbUtil.createPreparedStatement(ServiceProviderSQL.INSERT_DOCUMENT_DETLS);
			  dbUtil.executeUpdate(docTbl);
		      transactionflag = true;
		      dbUtil.commit();
		 
  	 }
  	   catch(Exception e){
  		   e.printStackTrace();
  		   dbUtil.rollback();
  		logger.error(" Exception at serviceProviderDAO in DAO " + e);
  		throw e;
  		}
  	   
  	   finally {
  			 try{	    
  				 dbUtil.closeConnection();
  			 }
  			 catch (Exception e) {
  			 logger.error("Exception at serviceProviderDAO in DAO  " + e);
  			 }		
  	       }
  	   return transactionflag;   
  	 }
    
    public ArrayList getDroDetails(String distt,String lang) throws Exception
    {

		ArrayList droDetailsList = new ArrayList();
		
		
		
		String[] param={distt};
		
		try {
			dbUtil = new DBUtility();
			String SQL="";
			if("en".equalsIgnoreCase(lang))
			{
			SQL = ServiceProviderSQL.GET_DRO_DETAILS;
			}
			else
			{
			SQL = ServiceProviderSQL.GET_DRO_DETAILS_H;
			}
					
					dbUtil.createPreparedStatement(SQL);
				
					
					try
					{	
						
						droDetailsList=dbUtil.executeQuery(param);
				            logger.debug("-----> in ServiceProviderDAO - getDroDetails() after dbUtil.executeQuery(SQL)");
				           
				            droDetailsList.trimToSize();
			
		} catch (Exception x) {
			logger.debug(x);
			x.printStackTrace();
		} 
		
		finally {
			
			try {
				
				dbUtil.closeConnection();
			} 
			catch (Exception ex) {
				logger.debug(ex);
				ex.printStackTrace();
			}
		}
		
	}
		catch(Exception e){
			logger.debug(e);
		}
		return droDetailsList;
		
    	
    }
    
    public String getdisttId(String ofcId)
	{
		String disttId = null;
		
		
		
		String[] param={ofcId};
		
		try {
			dbUtil = new DBUtility();
			
					String SQL = ServiceProviderSQL.GET_DISTT_ID_DR;
					dbUtil.createPreparedStatement(SQL);
				
					
					try
					{	
						
							disttId=dbUtil.executeQry(param);
				            logger.debug("-----> in ServiceProviderDAO - getdisttId() after dbUtil.executeUpdate(param)");
			
		} catch (Exception x) {
			logger.debug(x);
			x.printStackTrace();
		} 
		
		finally {
			
			try {
				
				dbUtil.closeConnection();
			} 
			catch (Exception ex) {
				logger.debug(ex);
				ex.printStackTrace();
			}
		}
		
	}
		catch(Exception e){
			logger.debug(e);
		}
		return disttId;
		}
    
    public ArrayList getPendingApps(String distt,String lang)
	{
		ArrayList pendingAppList = new ArrayList();
		
		String[] param={distt};
		String SQL ="";
		
		try {
			dbUtil = new DBUtility();
			
			if("en".equalsIgnoreCase(lang))
			{
					SQL = ServiceProviderSQL.GET_APPLICATIONS_DETLS;
			}
			else
			{
				SQL = ServiceProviderSQL.GET_APPLICATIONS_DETLS_H;
			}
					dbUtil.createPreparedStatement(SQL);
				
					
					try
					{	
						
						pendingAppList=dbUtil.executeQuery(param);
				            logger.debug("Wipro in ServiceProviderDAO - getPendingApps() after dbUtil.executeQuery(SQL)");
				           
				            pendingAppList.trimToSize();
			
		} catch (Exception x) {
			logger.debug(x);
			x.printStackTrace();
		} finally {
			try {
				
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				ex.printStackTrace();
			}
		}
		
	}
		catch(Exception e){
			logger.debug(e);
		}
		return pendingAppList;
		}
    
    
    public ArrayList getPendingAppsSearch1(String distt, String fromDate,String toDate,String lang)
	{
		ArrayList pendingAppList = new ArrayList();
		
		String[] param={distt, fromDate,toDate};
		
		try {
			dbUtil = new DBUtility();
			String SQL="";
			if("en".equalsIgnoreCase(lang))
			{
				SQL = ServiceProviderSQL.GET_APPLICATIONS_DETLS_SEARCH1;
			}
			else
			{
				SQL = ServiceProviderSQL.GET_APPLICATIONS_DETLS_SEARCH1_H;
			}
					dbUtil.createPreparedStatement(SQL);
				
					
					try
					{	
						
						pendingAppList=dbUtil.executeQuery(param);
				            logger.debug("Wipro in ServiceProviderDAO - getPendingAppsSearch1() after dbUtil.executeQuery(SQL)");
				           
				            pendingAppList.trimToSize();
			
		} catch (Exception x) {
			logger.debug(x);
			x.printStackTrace();
		} finally {
			try {
				
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				ex.printStackTrace();
			}
		}
		
	}
		catch(Exception e){
			logger.debug(e);
		}
		return pendingAppList;
		}
    
    public ArrayList getPendingAppsSearch2(String distt, String applctnNumber,String language)
	{
		ArrayList pendingAppList = new ArrayList();
		
		String[] param={distt, applctnNumber};
		
		try {
			dbUtil = new DBUtility();
			String SQL="";
			if("en".equalsIgnoreCase(language))
			{
				SQL = ServiceProviderSQL.GET_APPLICATIONS_DETLS_SEARCH2;
			}
			else
			{
				SQL = ServiceProviderSQL.GET_APPLICATIONS_DETLS_SEARCH2_H;
			}
					dbUtil.createPreparedStatement(SQL);
				
					
					try
					{	
						
						pendingAppList=dbUtil.executeQuery(param);
				            logger.debug("Wipro in ServiceProviderDAO - getPendingAppsSearch2() after dbUtil.executeQuery(SQL)");
				           
				            pendingAppList.trimToSize();
			
		} catch (Exception x) {
			logger.debug(x);
			x.printStackTrace();
		} finally {
			try {
				
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				ex.printStackTrace();
			}
		}
		
	}
		catch(Exception e){
			logger.debug(e);
		}
		return pendingAppList;
		}
    
    public ArrayList getPendingAppsSearch3(String distt, String status,String lang)
	{
		ArrayList pendingAppList = new ArrayList();
		
		String[] param={distt, status};
		String SQL="";
		try {
			dbUtil = new DBUtility();
			if("en".equalsIgnoreCase(lang))
			{
					SQL = ServiceProviderSQL.GET_APPLICATIONS_DETLS_SEARCH3;
			}
			else
			{
				SQL = ServiceProviderSQL.GET_APPLICATIONS_DETLS_SEARCH3_H;
			}
					
					dbUtil.createPreparedStatement(SQL);
					try
					{	
						
						pendingAppList=dbUtil.executeQuery(param);
				            logger.debug("Wipro in ServiceProviderDAO - getPendingAppsSearch3() after dbUtil.executeQuery(SQL)");
				           
				            pendingAppList.trimToSize();
			
		} catch (Exception x) {
			logger.debug(x);
			x.printStackTrace();
		} finally {
			try {
				
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				ex.printStackTrace();
			}
		}
		
	}
		catch(Exception e){
			logger.debug(e);
		}
		return pendingAppList;
		}
    
    public ArrayList getPendingAppsSearch4(String distt, String fromDate, String toDate, String applctnNumber,String lang)
	{
		ArrayList pendingAppList = new ArrayList();
		
		String[] param={distt, fromDate, toDate, applctnNumber};
		String SQL ="";
		try {
			dbUtil = new DBUtility();
			
			if("en".equalsIgnoreCase(lang))
					{
						SQL = ServiceProviderSQL.GET_APPLICATIONS_DETLS_SEARCH4;
					}
			else
			{
				SQL = ServiceProviderSQL.GET_APPLICATIONS_DETLS_SEARCH4_H;
			}
					dbUtil.createPreparedStatement(SQL);
				
					
					try
					{	
						
						pendingAppList=dbUtil.executeQuery(param);
				            logger.debug("Wipro in ServiceProviderDAO - getPendingAppsSearch4() after dbUtil.executeQuery(SQL)");
				           
				            pendingAppList.trimToSize();
			
		} catch (Exception x) {
			logger.debug(x);
			x.printStackTrace();
		} finally {
			try {
				
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				ex.printStackTrace();
			}
		}
		
	}
		catch(Exception e){
			logger.debug(e);
		}
		return pendingAppList;
		}
    
    public ArrayList getPendingAppsSearch5(String distt, String fromDate, String toDate, String status,String lang)
	{
		ArrayList pendingAppList = new ArrayList();
		
		String[] param={distt, fromDate, toDate, status};
		String SQL="";
		try {
			dbUtil = new DBUtility();
			if("en".equalsIgnoreCase(lang))
			{
					SQL = ServiceProviderSQL.GET_APPLICATIONS_DETLS_SEARCH5;
			}
			else
			{
				SQL = ServiceProviderSQL.GET_APPLICATIONS_DETLS_SEARCH5_H;
			}
					dbUtil.createPreparedStatement(SQL);
				
					
					try
					{	
						
						pendingAppList=dbUtil.executeQuery(param);
				            logger.debug("Wipro in ServiceProviderDAO - getPendingAppsSearch5() after dbUtil.executeQuery(SQL)");
				           
				            pendingAppList.trimToSize();
			
		} catch (Exception x) {
			logger.debug(x);
			x.printStackTrace();
		} finally {
			try {
				
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				ex.printStackTrace();
			}
		}
		
	}
		catch(Exception e){
			logger.debug(e);
		}
		return pendingAppList;
		}
    
    public ArrayList getPendingAppsSearch6(String distt, String applctnNumber, String status,String lang)
	{
		ArrayList pendingAppList = new ArrayList();
		
		String[] param={distt, applctnNumber, status};

		String SQL ="";
		try {
			dbUtil = new DBUtility();
			if("en".equalsIgnoreCase(lang))
			{
					SQL = ServiceProviderSQL.GET_APPLICATIONS_DETLS_SEARCH6;
			}
			else
			{
				SQL = ServiceProviderSQL.GET_APPLICATIONS_DETLS_SEARCH6_H;
			}
					dbUtil.createPreparedStatement(SQL);
				
					
					try
					{	
						
						pendingAppList=dbUtil.executeQuery(param);
				            logger.debug("Wipro in ServiceProviderDAO - getPendingAppsSearch6() after dbUtil.executeQuery(SQL)");
				           
				            pendingAppList.trimToSize();
			
		} catch (Exception x) {
			logger.debug(x);
			x.printStackTrace();
		} finally {
			try {
				
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				ex.printStackTrace();
			}
		}
		
	}
		catch(Exception e){
			logger.debug(e);
		}
		return pendingAppList;
		}
    
    public ArrayList getPendingAppsSearch7(String distt, String fromDate, String toDate,String applctnNumber, String status)
	{
		ArrayList pendingAppList = new ArrayList();
		
		String[] param={distt, fromDate, toDate, applctnNumber, status};
		
		try {
			dbUtil = new DBUtility();
			
					String SQL = ServiceProviderSQL.GET_APPLICATIONS_DETLS_SEARCH7;
					dbUtil.createPreparedStatement(SQL);
				
					
					try
					{	
						
						pendingAppList=dbUtil.executeQuery(param);
				            logger.debug("Wipro in ServiceProviderDAO - getPendingAppsSearch7() after dbUtil.executeQuery(SQL)");
				           
				            pendingAppList.trimToSize();
			
		} catch (Exception x) {
			logger.debug(x);
			x.printStackTrace();
		} finally {
			try {
				
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				ex.printStackTrace();
			}
		}
		
	}
		catch(Exception e){
			logger.debug(e);
		}
		return pendingAppList;
		}
    
    
    public ArrayList getPendingAppsSP(String userId,String lang)
	{
		ArrayList pendingAppList = new ArrayList();
		
		String[] param={userId};
		String SQL="";
		try {
			dbUtil = new DBUtility();
			if("en".equalsIgnoreCase(lang))
			{
			SQL = ServiceProviderSQL.GET_APPLICATIONS_DETLS_SP;
			}
			else
			{
				SQL = ServiceProviderSQL.GET_APPLICATIONS_DETLS_SP_H;	
			}
					dbUtil.createPreparedStatement(SQL);
				
					
					try
					{	
						
						pendingAppList=dbUtil.executeQuery(param);
				            logger.debug("Wipro in ServiceProviderDAO - getPendingAppsSP() after dbUtil.executeQuery(SQL)");
				           
				            pendingAppList.trimToSize();
			
		} catch (Exception x) {
			logger.debug(x);
			x.printStackTrace();
		} finally {
			try {
				
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				ex.printStackTrace();
			}
		}
		
	}
		catch(Exception e){
			logger.debug(e);
		}
		return pendingAppList;
		}
    
    public ArrayList getApplictnStatus(String lang) throws Exception
    {
    	ArrayList aplctnStatusList = new ArrayList();
    	String SQL="";
    	try {
    		dbUtil = new DBUtility();
    		if("en".equalsIgnoreCase(lang))
    		{
    		SQL = ServiceProviderSQL.GET_APPLICATN_STAUS_LIST;
    		}
    		else
    		{
    		SQL = ServiceProviderSQL.GET_APPLICATN_STAUS_LIST_H;	
    		}
    		dbUtil.createStatement();
    			
    			try
    			{
    				aplctnStatusList=dbUtil.executeQuery(SQL);
    				logger.debug("-----> in ServiceProviderDAO - getApplictnStatus() after dbUtil.executeQuery(SQL)");
    				
    				aplctnStatusList.trimToSize();
    			}
    			catch (Exception x) {
    				logger.debug(x);
    				x.printStackTrace();
    			} 
    			
   finally {
	   
	   		try {
	   			dbUtil.closeConnection();
	   			}
	   				catch (Exception x) {
	   				logger.debug(x);
	   				x.printStackTrace();
	   				} 
   			}
    		
    	}
    	
    	catch (Exception e) {
    		logger.debug(e);
		} 
    	return aplctnStatusList;
    }
    
    public ArrayList getSPDetails(ServiceProviderForm objServiceProviderForm,String lang) throws Exception
    {

		ArrayList spDetailsList = new ArrayList();
		
		String requestNo= (String)objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
		
		
		
		String[] param={requestNo};
		String SQL="";
		try {
			dbUtil = new DBUtility();
			if("en".equalsIgnoreCase(lang))
			{
				SQL = ServiceProviderSQL.GET_SP_LOGIN_DETLS_FOR_DR;
			}
			else
			{
				SQL = ServiceProviderSQL.GET_SP_LOGIN_DETLS_FOR_DR_H;
			}
					dbUtil.createPreparedStatement(SQL);
				
					
					try
					{	
						
						spDetailsList=dbUtil.executeQuery(param);
				            logger.debug("-----> in ServiceProviderDAO - getSPDetails() after dbUtil.executeQuery(SQL)");
				           
				            spDetailsList.trimToSize();
			
		} catch (Exception x) {
			logger.debug(x);
			x.printStackTrace();
		} 
		
		finally {
			
			try {
				
				dbUtil.closeConnection();
			} 
			catch (Exception ex) {
				logger.debug(ex);
				ex.printStackTrace();
			}
		}
		
	}
		catch(Exception e){
			logger.debug(e);
		}
		return spDetailsList;
		
    	
    }
    
    public String getspTypeId(String requestNumber)
	{
		String spTypeId = null;
		
		
		
		String[] param={requestNumber};
		
		try {
			dbUtil = new DBUtility();
			
					String SQL = ServiceProviderSQL.GET_SP_TYPE_ID;
					dbUtil.createPreparedStatement(SQL);
				
					
					try
					{	
						
							spTypeId=dbUtil.executeQry(param);
				            logger.debug("-----> in ServiceProviderDAO - getspTypeId() after dbUtil.executeUpdate(param)");
			
		} catch (Exception x) {
			logger.debug(x);
			x.printStackTrace();
		} 
		
		finally {
			
			try {
				
				dbUtil.closeConnection();
			} 
			catch (Exception ex) {
				logger.debug(ex);
				ex.printStackTrace();
			}
		}
		
	}
		catch(Exception e){
			logger.debug(e);
		}
		return spTypeId;
		}
    
    public ArrayList getspDetls(ServiceProviderForm objServiceProviderForm,String lang) throws Exception
    {

		ArrayList details = new ArrayList();
		
		String requestNo= (String)objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
		
		
		
		String[] param={requestNo};
		String SQL="";
		try {
			dbUtil = new DBUtility();
			if("en".equalsIgnoreCase(lang))
			{
					SQL = ServiceProviderSQL.GET_SP_DETLS_FOR_DR;
			}
			else
				
			{
				SQL = ServiceProviderSQL.GET_SP_DETLS_FOR_DR_H;	
			}
					dbUtil.createPreparedStatement(SQL);
				
					
					try
					{	
						
							details=dbUtil.executeQuery(param);
				            logger.debug("-----> in ServiceProviderDAO - getSPDetails() after dbUtil.executeQuery(SQL)");
				           
				            details.trimToSize();
			
		} catch (Exception x) {
			logger.debug(x);
			x.printStackTrace();
		} 
		
		finally {
			
			try {
				
				dbUtil.closeConnection();
			} 
			catch (Exception ex) {
				logger.debug(ex);
				ex.printStackTrace();
			}
		}
		
	}
		catch(Exception e){
			logger.debug(e);
		}
		return details;
		
    	
    }
    
    public ArrayList getspDocDetls(String requestNumber) throws Exception
    {

		ArrayList spDocDetails = new ArrayList();
		
		
		
		String[] param={requestNumber};
		
		try {
			dbUtil = new DBUtility();
			
					String SQL = ServiceProviderSQL.GET_SP_DOC_DETLS_FOR_DR;
					dbUtil.createPreparedStatement(SQL);
				
					
					try
					{	
						
						spDocDetails=dbUtil.executeQuery(param);
				            logger.debug("-----> in ServiceProviderDAO - getspDocDetls() after dbUtil.executeQuery(SQL)");
				           
				            spDocDetails.trimToSize();
			
		} catch (Exception x) {
			logger.debug(x);
			x.printStackTrace();
		} 
		
		finally {
			
			try {
				
				dbUtil.closeConnection();
			} 
			catch (Exception ex) {
				logger.debug(ex);
				ex.printStackTrace();
			}
		}
		
	}
		catch(Exception e){
			logger.debug(e);
		}
		return spDocDetails;
		
    	
    }
    
    public ArrayList getspDocDetlsDR(ServiceProviderForm objServiceProviderForm) throws Exception
    {

		ArrayList spDocDetails = new ArrayList();
		String requestNumber = (String)objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
		
		String[] param={requestNumber};
		
		try {
			dbUtil = new DBUtility();
			
					String SQL = ServiceProviderSQL.GET_SP_DOC_DETLS_FOR_DR;
					dbUtil.createPreparedStatement(SQL);
				
					try
					{	
						
						spDocDetails=dbUtil.executeQuery(param);
				            logger.debug("-----> in ServiceProviderDAO - getspDocDetls() after dbUtil.executeQuery(SQL)");
				           
				            spDocDetails.trimToSize();
			
		} catch (Exception x) {
			logger.debug(x);
			x.printStackTrace();
		} 
		
		finally {
			
			try {
				
				dbUtil.closeConnection();
			} 
			catch (Exception ex) {
				logger.debug(ex);
				ex.printStackTrace();
			}
		}
		
	}
		catch(Exception e){
			logger.debug(e);
		}
		return spDocDetails;
		
    	
    }
    
    public String getFees()
	{
		String fees = null;
		
		try {
			dbUtil = new DBUtility();
			
					String SQL = ServiceProviderSQL.GET_LICENSE_FEE;
					dbUtil.createStatement();
					
				
					
					try
					{	
						
						    fees=dbUtil.executeQry(SQL);
				            logger.debug("-----> in ServiceProviderDAO - getFees() after dbUtil.executeUpdate(param)");
			
		} catch (Exception x) {
			logger.debug(x);
			x.printStackTrace();
		} 
		
		finally {
			
			try {
				
				dbUtil.closeConnection();
			} 
			catch (Exception ex) {
				logger.debug(ex);
				ex.printStackTrace();
			}
		}
		
	}
		catch(Exception e){
			logger.debug(e);
		}
		return fees;
		}
    
    public  boolean insertDRApprovalComments (ServiceProviderForm objServiceProviderForm) throws Exception{
		 
    	boolean transactionflag = false;
  	  	String insertDRAppDetls[]= new String[7];
  	  	String insertDRAppComments[]= new String[5];
  	  	String updatePaymentDetls[]= new String[3];
  	  	

  	  DBUtility dbUtil = null;
  	  try{
  		  dbUtil = new DBUtility();
  		  dbUtil.setAutoCommit(false);
  		  
  	      //String SQL = "select IGRS_SP_COMMENTS_ID_SEQ.nextval from dual";
  	  
  	      dbUtil.createStatement();
  		  String number = dbUtil.executeQry(ServiceProviderSQL.GET_SEQ_COMMENTS_ID);

  		  insertDRAppDetls[0] = objServiceProviderForm.getObjServiceProviderDTO().getLicenseFromDate();
  		  insertDRAppDetls[1] =  objServiceProviderForm.getObjServiceProviderDTO().getLicenseToDate();
  		  insertDRAppDetls[2] =  objServiceProviderForm.getObjServiceProviderDTO().getFees();
  		  insertDRAppDetls[3] = "5";
  		  insertDRAppDetls[4] = (String) objServiceProviderForm.getObjServiceProviderDTO().getOfficeId();
  		  insertDRAppDetls[5] = (String) objServiceProviderForm.getObjServiceProviderDTO().getUid();
  		  insertDRAppDetls[6] =  (String)objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
  		  
  		  insertDRAppComments[0] =  number;
  		  insertDRAppComments[1] =  (String)objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
  		  insertDRAppComments[2] =  objServiceProviderForm.getObjServiceProviderDTO().getComments();
  		  insertDRAppComments[3] =  "5";
  		  insertDRAppComments[4] = (String) objServiceProviderForm.getObjServiceProviderDTO().getUid();
  		  
  		  updatePaymentDetls[0]= objServiceProviderForm.getObjServiceProviderDTO().getFees();
  		  updatePaymentDetls[1]= (String)objServiceProviderForm.getObjServiceProviderDTO().getUid();
  		  updatePaymentDetls[2]= (String)objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
  		  
  		
  		  
  		  dbUtil.createPreparedStatement(ServiceProviderSQL.INSERT_DR_APPROVED_LICENSE_DATES);
  		  dbUtil.executeUpdate(insertDRAppDetls);
  		  
  		  dbUtil.createPreparedStatement(ServiceProviderSQL.INSERT_DR_COMMENTS);
		  dbUtil.executeUpdate(insertDRAppComments);
		  
		  dbUtil.createPreparedStatement(ServiceProviderSQL.UPDATE_PAYMENT_DETAILS);
		  dbUtil.executeUpdate(updatePaymentDetls);
		  
  	      transactionflag = true;
  	      dbUtil.commit();	
  	      
  	  //added by shruti---6 march 2014
	      /*String[] uid={(String) objServiceProviderForm.getObjServiceProviderDTO().getUid()};
	      dbUtil.createPreparedStatement(ServiceProviderSQL.GETUSERNAME);
	      String userName=dbUtil.executeQry(uid);*/
  	      String[] param={
  	    		  		(String)objServiceProviderForm.getObjServiceProviderDTO().getRequestNo(),
  	    		  		(String) objServiceProviderForm.getObjServiceProviderDTO().getOfficeId(),
  	      };
  	      dbUtil.createPreparedStatement(ServiceProviderSQL.GETDATAPVEMAIL);
  	      ArrayList dtls=new ArrayList();
  	      ArrayList list=new ArrayList();
  	      dtls=dbUtil.executeQuery(param);
  	      for(int i=0;i<dtls.size();i++)
  	      {
  	    	  list=(ArrayList)dtls.get(i);  
  	      }
	      String status = new String();
	      String subject=CommonConstant.EMAILSUB_APPREJ;
	      String content="This is in reference of your request Id "+(String)objServiceProviderForm.getObjServiceProviderDTO().getRequestNo()+ ".Your request for Service Provider License is accepted.";
	        try {
	        	
		           ServiceProviderDAO dao=new ServiceProviderDAO();
		           status  = dao.insertEmailData(list.get(3).toString(), subject, content);

	             } catch (Exception x) {
		               logger.error(x);
	               }
	      dbUtil.commit();
	      //sms integration
	      dbUtil.createPreparedStatement(ServiceProviderSQL.GETMOBILENUMBER);
	      String[] userId={list.get(3).toString()};
	      String mobileNo=dbUtil.executeQry(userId);
	        try {
	        	
		           ServiceProviderDAO dao=new ServiceProviderDAO();
		           status  = dao.insertSMSData(list.get(3).toString(), mobileNo, content);

	             } catch (Exception x) {
		               logger.error(x);
	               }
	      dbUtil.commit();
  		
  		
  	 }
  	   catch(Exception e){
  		   e.printStackTrace();
  		   dbUtil.rollback();
  		logger.error(" Exception at serviceProviderDAO in DAO " + e);
  		throw e;
  		}
  	   
  	   finally {
  			 try{	    
  				 dbUtil.closeConnection();
  			 }
  			 catch (Exception e) {
  			 logger.error("Exception at serviceProviderDAO in DAO  " + e);
  			 }		
  	       }
  	   return transactionflag;   
  	 }
    
    public  boolean updateSpDocDetls (ServiceProviderForm objServiceProviderForm) throws Exception{
		 
    	  boolean transactionflag = false;
    	  String docTbl[]= new String[5];

    	  DBUtility dbUtil = null;
    	  try{
    		  dbUtil = new DBUtility();
    		  dbUtil.setAutoCommit(false);
    		  
          if (objServiceProviderForm.getObjServiceProviderDTO().getAffidavitCriminal()!=null && objServiceProviderForm.getObjServiceProviderDTO().getDocName().startsWith("Criminal_Activities_Affidavit"))
    	  {
    			  docTbl[0]=objServiceProviderForm.getObjServiceProviderDTO().getDocName();
        		  docTbl[1]=(String)objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
        		  docTbl[2]=(String)objServiceProviderForm.getObjServiceProviderDTO().getUid();
        		  docTbl[3]=(String)objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
        		  docTbl[4]="Criminal_Activities_Affidavit%";
        		  
        		  dbUtil.createPreparedStatement(ServiceProviderSQL.UPDATE_DOCUMENT_DETLS);
        		  dbUtil.executeUpdate(docTbl);
        	      transactionflag = true;
        	      dbUtil.commit();
		  }
		  
		  if (objServiceProviderForm.getObjServiceProviderDTO().getCharacterCertiGaz()!=null && objServiceProviderForm.getObjServiceProviderDTO().getDocName().startsWith("Character_Ceertificate_Gazette"))
		  {
			  
			  docTbl[0]=objServiceProviderForm.getObjServiceProviderDTO().getDocName();
    		  docTbl[1]=(String)objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
    		  docTbl[2]=(String)objServiceProviderForm.getObjServiceProviderDTO().getUid();
    		  docTbl[3]=(String)objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
    		  docTbl[4]="Character_Ceertificate_Gazette%";
    		  
    		  dbUtil.createPreparedStatement(ServiceProviderSQL.UPDATE_DOCUMENT_DETLS);
    		  dbUtil.executeUpdate(docTbl);
    	      transactionflag = true;
    	      dbUtil.commit();
			    
		  }
		  
		  if (objServiceProviderForm.getObjServiceProviderDTO().getDobCerti()!=null && objServiceProviderForm.getObjServiceProviderDTO().getDocName().startsWith("DOB_Certificate"))
		  {
			  
			  docTbl[0]=objServiceProviderForm.getObjServiceProviderDTO().getDocName();
    		  docTbl[1]=(String)objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
    		  docTbl[2]=(String)objServiceProviderForm.getObjServiceProviderDTO().getUid();
    		  docTbl[3]=(String)objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
    		  docTbl[4]="DOB_Certificate%";
    		  
    		  dbUtil.createPreparedStatement(ServiceProviderSQL.UPDATE_DOCUMENT_DETLS);
    		  dbUtil.executeUpdate(docTbl);
    	      transactionflag = true;
    	      dbUtil.commit();
			   
		  }
		  
		  if (objServiceProviderForm.getObjServiceProviderDTO().getRentDetail()!=null && objServiceProviderForm.getObjServiceProviderDTO().getDocName().startsWith("Rent_Details"))
		  {
			  
			  docTbl[0]=objServiceProviderForm.getObjServiceProviderDTO().getDocName();
    		  docTbl[1]=(String)objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
    		  docTbl[2]=(String)objServiceProviderForm.getObjServiceProviderDTO().getUid();
    		  docTbl[3]=(String)objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
    		  docTbl[4]="Rent_Details%";
    		  
    		  dbUtil.createPreparedStatement(ServiceProviderSQL.UPDATE_DOCUMENT_DETLS);
    		  dbUtil.executeUpdate(docTbl);
    	      transactionflag = true;
    	      dbUtil.commit();
		  }
		  
		  if (objServiceProviderForm.getObjServiceProviderDTO().getHigherSecCerti()!=null && objServiceProviderForm.getObjServiceProviderDTO().getDocName().startsWith("Higher_Secondary_Certificate"))
		  {
			  
			  docTbl[0]=objServiceProviderForm.getObjServiceProviderDTO().getDocName();
    		  docTbl[1]=(String)objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
    		  docTbl[2]=(String)objServiceProviderForm.getObjServiceProviderDTO().getUid();
    		  docTbl[3]=(String)objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
    		  docTbl[4]="Higher_Secondary_Certificate%";
    		  
    		  dbUtil.createPreparedStatement(ServiceProviderSQL.UPDATE_DOCUMENT_DETLS);
    		  dbUtil.executeUpdate(docTbl);
    	      transactionflag = true;
    	      dbUtil.commit();
			   
		  }
		  
		  if (objServiceProviderForm.getObjServiceProviderDTO().getPhotoImg()!=null && objServiceProviderForm.getObjServiceProviderDTO().getDocName().startsWith("Photo"))
		  {
			  
			  docTbl[0]=objServiceProviderForm.getObjServiceProviderDTO().getDocName();
    		  docTbl[1]=(String)objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
    		  docTbl[2]=(String)objServiceProviderForm.getObjServiceProviderDTO().getUid();
    		  docTbl[3]=(String)objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
    		  docTbl[4]="Photo%";
    		  
    		  dbUtil.createPreparedStatement(ServiceProviderSQL.UPDATE_DOCUMENT_DETLS);
    		  dbUtil.executeUpdate(docTbl);
    	      transactionflag = true;
    	      dbUtil.commit();
			    
		  }
		  
		  if (objServiceProviderForm.getObjServiceProviderDTO().getCharacterCerti()!=null && objServiceProviderForm.getObjServiceProviderDTO().getDocName().startsWith("Character_Certificate"))
		  {
			  
			  docTbl[0]=objServiceProviderForm.getObjServiceProviderDTO().getDocName();
    		  docTbl[1]=(String)objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
    		  docTbl[2]=(String)objServiceProviderForm.getObjServiceProviderDTO().getUid();
    		  docTbl[3]=(String)objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
    		  docTbl[4]="Character_Certificate%";
    		  
    		  dbUtil.createPreparedStatement(ServiceProviderSQL.UPDATE_DOCUMENT_DETLS);
    		  dbUtil.executeUpdate(docTbl);
    	      transactionflag = true;
    	      dbUtil.commit();
			  
		  }
		  
		  if (objServiceProviderForm.getObjServiceProviderDTO().getAgeProof()!=null && objServiceProviderForm.getObjServiceProviderDTO().getDocName().startsWith("Age_Proof"))
		  {
			  
			  docTbl[0]=objServiceProviderForm.getObjServiceProviderDTO().getDocName();
    		  docTbl[1]=(String)objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
    		  docTbl[2]=(String)objServiceProviderForm.getObjServiceProviderDTO().getUid();
    		  docTbl[3]=(String)objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
    		  docTbl[4]="Age_Proof%";
    		  
    		  dbUtil.createPreparedStatement(ServiceProviderSQL.UPDATE_DOCUMENT_DETLS);
    		  dbUtil.executeUpdate(docTbl);
    	      transactionflag = true;
    	      dbUtil.commit();
			  
		  }
		  
		  if (objServiceProviderForm.getObjServiceProviderDTO().getSolvencyCerti()!=null && objServiceProviderForm.getObjServiceProviderDTO().getDocName().startsWith("Solvency_Certificate"))
		  {
			  
			  docTbl[0]=objServiceProviderForm.getObjServiceProviderDTO().getDocName();
    		  docTbl[1]=(String)objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
    		  docTbl[2]=(String)objServiceProviderForm.getObjServiceProviderDTO().getUid();
    		  docTbl[3]=(String)objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
    		  docTbl[4]="Solvency_Certificate%";
    		  
    		  dbUtil.createPreparedStatement(ServiceProviderSQL.UPDATE_DOCUMENT_DETLS);
    		  dbUtil.executeUpdate(docTbl);
    	      transactionflag = true;
    	      dbUtil.commit();
			  
		  }
		  
		  if (objServiceProviderForm.getObjServiceProviderDTO().getPoliceCaseCerti()!=null && objServiceProviderForm.getObjServiceProviderDTO().getDocName().startsWith("Affidavit_Police_Case"))
		  {
			  
			  docTbl[0]=objServiceProviderForm.getObjServiceProviderDTO().getDocName();
    		  docTbl[1]=(String)objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
    		  docTbl[2]=(String)objServiceProviderForm.getObjServiceProviderDTO().getUid();
    		  docTbl[3]=(String)objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
    		  docTbl[4]="Affidavit_Police_Case%";
    		  
    		  dbUtil.createPreparedStatement(ServiceProviderSQL.UPDATE_DOCUMENT_DETLS);
    		  dbUtil.executeUpdate(docTbl);
    	      transactionflag = true;
    	      dbUtil.commit();
			    
		  }
		  
		  if (objServiceProviderForm.getObjServiceProviderDTO().getPossessionBusinessDoc()!=null && objServiceProviderForm.getObjServiceProviderDTO().getDocName().startsWith("Business_Premises"))
		  {
			  
			  docTbl[0]=objServiceProviderForm.getObjServiceProviderDTO().getDocName();
    		  docTbl[1]=(String)objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
    		  docTbl[2]=(String)objServiceProviderForm.getObjServiceProviderDTO().getUid();
    		  docTbl[3]=(String)objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
    		  docTbl[4]="Business_Premises%";
    		  
    		  dbUtil.createPreparedStatement(ServiceProviderSQL.UPDATE_DOCUMENT_DETLS);
    		  dbUtil.executeUpdate(docTbl);
    	      transactionflag = true;
    	      dbUtil.commit();
			   
		  }
		  
		  if (objServiceProviderForm.getObjServiceProviderDTO().getHigherEduCerti()!=null && objServiceProviderForm.getObjServiceProviderDTO().getDocName().startsWith("Education_Qualification"))
		  {
			  
			  docTbl[0]=objServiceProviderForm.getObjServiceProviderDTO().getDocName();
    		  docTbl[1]=(String)objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
    		  docTbl[2]=(String)objServiceProviderForm.getObjServiceProviderDTO().getUid();
    		  docTbl[3]=(String)objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
    		  docTbl[4]="Education_Qualification%";
    		  
    		  dbUtil.createPreparedStatement(ServiceProviderSQL.UPDATE_DOCUMENT_DETLS);
    		  dbUtil.executeUpdate(docTbl);
    	      transactionflag = true;
    	      dbUtil.commit();
			  
		  }
		  
		  if (objServiceProviderForm.getObjServiceProviderDTO().getAffidavitHardware()!=null && objServiceProviderForm.getObjServiceProviderDTO().getDocName().startsWith("Hardware_Available"))
		  {
			  
			  docTbl[0]=objServiceProviderForm.getObjServiceProviderDTO().getDocName();
    		  docTbl[1]=(String)objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
    		  docTbl[2]=(String)objServiceProviderForm.getObjServiceProviderDTO().getUid();
    		  docTbl[3]=(String)objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
    		  docTbl[4]="Hardware_Available%";
    		  
    		  dbUtil.createPreparedStatement(ServiceProviderSQL.UPDATE_DOCUMENT_DETLS);
    		  dbUtil.executeUpdate(docTbl);
    	      transactionflag = true;
    	      dbUtil.commit();
			  
		  }	
		  //added by shruti---30 july 2014
		  if (objServiceProviderForm.getObjServiceProviderDTO().getPancardUpload()!=null && objServiceProviderForm.getObjServiceProviderDTO().getDocName().startsWith("PanCard"))
		  {
			  
			  docTbl[0]=objServiceProviderForm.getObjServiceProviderDTO().getDocName();
    		  docTbl[1]=(String)objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
    		  docTbl[2]=(String)objServiceProviderForm.getObjServiceProviderDTO().getUid();
    		  docTbl[3]=(String)objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
    		  docTbl[4]="PanCard%";
    		  
    		  dbUtil.createPreparedStatement(ServiceProviderSQL.UPDATE_DOCUMENT_DETLS);
    		  dbUtil.executeUpdate(docTbl);
    	      transactionflag = true;
    	      dbUtil.commit();
			  
		  }	
		  if (objServiceProviderForm.getObjServiceProviderDTO().getForm60Upload()!=null && objServiceProviderForm.getObjServiceProviderDTO().getDocName().startsWith("Form60_61"))
		  {
			  
			  docTbl[0]=objServiceProviderForm.getObjServiceProviderDTO().getDocName();
    		  docTbl[1]=(String)objServiceProviderForm.getObjServiceProviderDTO().getDocPath();
    		  docTbl[2]=(String)objServiceProviderForm.getObjServiceProviderDTO().getUid();
    		  docTbl[3]=(String)objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
    		  docTbl[4]="Form60_61%";
    		  
    		  dbUtil.createPreparedStatement(ServiceProviderSQL.UPDATE_DOCUMENT_DETLS);
    		  dbUtil.executeUpdate(docTbl);
    	      transactionflag = true;
    	      dbUtil.commit();
			  
		  }	
		  //end
    		
    	 }
    	   catch(Exception e){
    		   e.printStackTrace();
    		   dbUtil.rollback();
    		logger.error(" Exception at serviceProviderDAO in DAO " + e);
    		throw e;
    		}
    	   
    	   finally {
    			 try{	    
    				 dbUtil.closeConnection();
    			 }
    			 catch (Exception e) {
    			 logger.error("Exception at serviceProviderDAO in DAO  " + e);
    			 }		
    	       }
    	   return transactionflag;   
    	 }
    
    public  boolean insertDRCallForPVComments (ServiceProviderForm objServiceProviderForm) throws Exception{
		 
    	boolean transactionflag = false;
  	  	String updateAppStatus[]= new String[4];
  	  	String insertDRAppComments[]= new String[5];

  	  DBUtility dbUtil = null;
  	  try{
  		  dbUtil = new DBUtility();
  		  dbUtil.setAutoCommit(false);
  		  
  	      //String SQL = "select IGRS_SP_COMMENTS_ID_SEQ.nextval from dual";
  	      dbUtil.createStatement();
  		  String number = dbUtil.executeQry(ServiceProviderSQL.GET_SEQ_COMMENTS_ID);

  		  updateAppStatus[0] = "3";
  		  updateAppStatus[1] = (String) objServiceProviderForm.getObjServiceProviderDTO().getOfficeId();
  		  updateAppStatus[2] = (String) objServiceProviderForm.getObjServiceProviderDTO().getUid();
  		  updateAppStatus[3] =  (String)objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
  		  
  		  insertDRAppComments[0] =  number;
  		  insertDRAppComments[1] =  (String)objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
  		  insertDRAppComments[2] =  objServiceProviderForm.getObjServiceProviderDTO().getRmrksCallForPV();
  		  insertDRAppComments[3] =  "3";
  		  insertDRAppComments[4] = (String) objServiceProviderForm.getObjServiceProviderDTO().getUid();
  		  
  		
  		  
  		  dbUtil.createPreparedStatement(ServiceProviderSQL.UPDATE_APPLCTN_STATUS);
  		  dbUtil.executeUpdate(updateAppStatus);
  		  
  		  dbUtil.createPreparedStatement(ServiceProviderSQL.INSERT_DR_COMMENTS);
		  dbUtil.executeUpdate(insertDRAppComments);
  	      transactionflag = true;
  	      dbUtil.commit();			
  	    //added by shruti---6 march 2014
	      /*String[] uid={(String) objServiceProviderForm.getObjServiceProviderDTO().getUid()};
	      dbUtil.createPreparedStatement(ServiceProviderSQL.GETUSERNAME);
	      String userName=dbUtil.executeQry(uid);*/
  	      String[] param={
  	    		  		(String)objServiceProviderForm.getObjServiceProviderDTO().getRequestNo(),
  	    		  		(String) objServiceProviderForm.getObjServiceProviderDTO().getOfficeId(),
  	      };
  	      dbUtil.createPreparedStatement(ServiceProviderSQL.GETDATAPVEMAIL);
  	      ArrayList dtls=new ArrayList();
  	      ArrayList list=new ArrayList();
  	      dtls=dbUtil.executeQuery(param);
  	      for(int i=0;i<dtls.size();i++)
  	      {
  	    	  list=(ArrayList)dtls.get(i);  
  	      }
	      String status = new String();
	      //String userId=dtls.get(3).toString();
	      String subject=CommonConstant.EMAILSUB_PHYVERIFICATION;
	      String content="This is in reference of your request Id "+(String)objServiceProviderForm.getObjServiceProviderDTO().getRequestNo()+ ".You " +list.get(0).toString()+" are requested to come for Physical Verification at "+ list.get(1).toString()+","+ list.get(2).toString();
	        try {
	        	
		           ServiceProviderDAO dao=new ServiceProviderDAO();
		           status  = dao.insertEmailData(list.get(3).toString(), subject, content);

	             } catch (Exception x) {
		               logger.error(x);
	               }
	      dbUtil.commit();
	      //sms integration
	    //sms integration
	      String[] userId={list.get(3).toString()};
	      dbUtil.createPreparedStatement(ServiceProviderSQL.GETMOBILENUMBER);
	      String mobileNo=dbUtil.executeQry(userId);
	        try {
	        	
		           ServiceProviderDAO dao=new ServiceProviderDAO();
		           status  = dao.insertSMSData(list.get(3).toString(), mobileNo, content);

	             } catch (Exception x) {
		               logger.error(x);
	               }
	      dbUtil.commit();
  		
  	 }
  	   catch(Exception e){
  		   e.printStackTrace();
  		   dbUtil.rollback();
  		logger.error(" Exception at serviceProviderDAO in DAO " + e);
  		throw e;
  		}
  	   
  	   finally {
  			 try{	    
  				 dbUtil.closeConnection();
  			 }
  			 catch (Exception e) {
  			 logger.error("Exception at serviceProviderDAO in DAO  " + e);
  			 }		
  	       }
  	   return transactionflag;   
  	 }
    
    public  boolean insertDRCallForAddInfoComments (ServiceProviderForm objServiceProviderForm) throws Exception{
		 
    	boolean transactionflag = false;
  	  	String updateAppStatus[]= new String[4];
  	  	String insertDRAppComments[]= new String[5];

  	  DBUtility dbUtil = null;
  	  try{
  		  dbUtil = new DBUtility();
  		  dbUtil.setAutoCommit(false);
  		  
  	      String SQL = "select IGRS_SP_COMMENTS_ID_SEQ.nextval from dual";
  	      dbUtil.createStatement();
  		  String number = dbUtil.executeQry(SQL);

  		  updateAppStatus[0] = "4";
  		  updateAppStatus[1] = (String) objServiceProviderForm.getObjServiceProviderDTO().getOfficeId();
  		  updateAppStatus[2] = (String) objServiceProviderForm.getObjServiceProviderDTO().getUid();
  		  updateAppStatus[3] =  (String)objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
  		  
  		  insertDRAppComments[0] =  number;
  		  insertDRAppComments[1] =  (String)objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
  		  insertDRAppComments[2] =  objServiceProviderForm.getObjServiceProviderDTO().getRmrksCallForAddInfo();
  		  insertDRAppComments[3] =  "4";
  		  insertDRAppComments[4] = (String) objServiceProviderForm.getObjServiceProviderDTO().getUid();
  		  
  		
  		  
  		  dbUtil.createPreparedStatement(ServiceProviderSQL.UPDATE_APPLCTN_STATUS);
  		  dbUtil.executeUpdate(updateAppStatus);
  		  
  		  dbUtil.createPreparedStatement(ServiceProviderSQL.INSERT_DR_COMMENTS);
		  dbUtil.executeUpdate(insertDRAppComments);
  	      transactionflag = true;
  	      dbUtil.commit();
  	  //added by shruti---6 march 2014
	      /*String[] uid={(String) objServiceProviderForm.getObjServiceProviderDTO().getUid()};
	      dbUtil.createPreparedStatement(ServiceProviderSQL.GETUSERNAME);
	      String userName=dbUtil.executeQry(uid);*/
  	      String[] param={
  	    		  		(String)objServiceProviderForm.getObjServiceProviderDTO().getRequestNo(),
  	    		  		(String) objServiceProviderForm.getObjServiceProviderDTO().getOfficeId(),
  	      };
  	      dbUtil.createPreparedStatement(ServiceProviderSQL.GETDATAPVEMAIL);
  	      ArrayList dtls=new ArrayList();
  	      ArrayList list=new ArrayList();
  	      dtls=dbUtil.executeQuery(param);
  	      for(int i=0;i<dtls.size();i++)
  	      {
  	    	  list=(ArrayList)dtls.get(i);  
  	      }
	      String status = new String();
	      //String userId=dtls.get(3).toString();
	      String subject=CommonConstant.EMAILSUB_ADDINFO;
	      String content="This is in reference of your request Id "+(String)objServiceProviderForm.getObjServiceProviderDTO().getRequestNo()+ ".You " +list.get(0).toString()+" are requested to give Additional Information for getting Service Provider License.";
	        try {
	        	
		           ServiceProviderDAO dao=new ServiceProviderDAO();
		           status  = dao.insertEmailData(list.get(3).toString(), subject, content);

	             } catch (Exception x) {
		               logger.error(x);
	               }
	      dbUtil.commit();
	      //sms integration
	    //sms integration
	      String[] userId={list.get(3).toString()};
	      dbUtil.createPreparedStatement(ServiceProviderSQL.GETMOBILENUMBER);
	      dbUtil.executeQry(userId);
	      String mobileNo=dbUtil.executeQry(userId);
	        try {
	        	
		           ServiceProviderDAO dao=new ServiceProviderDAO();
		           status  = dao.insertSMSData(list.get(3).toString(), mobileNo, content);

	             } catch (Exception x) {
		               logger.error(x);
	               }
	      dbUtil.commit();
	      //end
  	      
  		
  	 }
  	   catch(Exception e){
  		   e.printStackTrace();
  		   dbUtil.rollback();
  		logger.error(" Exception at serviceProviderDAO in DAO " + e);
  		throw e;
  		}
  	   
  	   finally {
  			 try{	    
  				 dbUtil.closeConnection();
  			 }
  			 catch (Exception e) {
  			 logger.error("Exception at serviceProviderDAO in DAO  " + e);
  			 }		
  	       }
  	   return transactionflag;   
  	 }
    
    public  boolean insertDrRejectionComments (ServiceProviderForm objServiceProviderForm) throws Exception{
		 
    	boolean transactionflag = false;
  	  	String updateAppStatus[]= new String[4];
  	  	String insertDRAppComments[]= new String[5];

  	  DBUtility dbUtil = null;
  	  try{
  		  dbUtil = new DBUtility();
  		  dbUtil.setAutoCommit(false);
  		  
  	      //String SQL = "select IGRS_SP_COMMENTS_ID_SEQ.nextval from dual";
  	      dbUtil.createStatement();
  		  String number = dbUtil.executeQry(ServiceProviderSQL.GET_SEQ_COMMENTS_ID);

  		  updateAppStatus[0] = "6";
  		  updateAppStatus[1] = (String) objServiceProviderForm.getObjServiceProviderDTO().getOfficeId();
  		  updateAppStatus[2] = (String) objServiceProviderForm.getObjServiceProviderDTO().getUid();
  		  updateAppStatus[3] =  (String)objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
  		  
  		  insertDRAppComments[0] =  number;
  		  insertDRAppComments[1] =  (String)objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
  		  insertDRAppComments[2] =  objServiceProviderForm.getObjServiceProviderDTO().getRmrksForRejection();
  		  insertDRAppComments[3] =  "6";
  		  insertDRAppComments[4] = (String) objServiceProviderForm.getObjServiceProviderDTO().getUid();
  		  
  		
  		  
  		  dbUtil.createPreparedStatement(ServiceProviderSQL.UPDATE_APPLCTN_STATUS);
  		  dbUtil.executeUpdate(updateAppStatus);
  		  
  		  dbUtil.createPreparedStatement(ServiceProviderSQL.INSERT_DR_COMMENTS);
		  dbUtil.executeUpdate(insertDRAppComments);
  	      transactionflag = true;
  	      dbUtil.commit();				
  	      
  	    //added by shruti---6 march 2014
	      /*String[] uid={(String) objServiceProviderForm.getObjServiceProviderDTO().getUid()};
	      dbUtil.createPreparedStatement(ServiceProviderSQL.GETUSERNAME);
	      String userName=dbUtil.executeQry(uid);*/
  	      String[] param={
  	    		  		(String)objServiceProviderForm.getObjServiceProviderDTO().getRequestNo(),
  	    		  		(String) objServiceProviderForm.getObjServiceProviderDTO().getOfficeId(),
  	      };
  	      dbUtil.createPreparedStatement(ServiceProviderSQL.GETDATAPVEMAIL);
  	      ArrayList dtls=new ArrayList();
  	      ArrayList list=new ArrayList();
  	      dtls=dbUtil.executeQuery(param);
  	      for(int i=0;i<dtls.size();i++)
  	      {
  	    	  list=(ArrayList)dtls.get(i);  
  	      }
	      String status = new String();
	      String subject=CommonConstant.EMAILSUB_APPREJ;
	      String content="This is in reference of your request Id "+(String)objServiceProviderForm.getObjServiceProviderDTO().getRequestNo()+ ".Your request for Service Provider License is rejected.";
	        try {
	        	
		           ServiceProviderDAO dao=new ServiceProviderDAO();
		           status  = dao.insertEmailData(list.get(3).toString(), subject, content);

	             } catch (Exception x) {
		               logger.error(x);
	               }
	      dbUtil.commit();
	      //sms integration
	    //sms integration
	      String[] userId={list.get(3).toString()};
	      dbUtil.createPreparedStatement(ServiceProviderSQL.GETMOBILENUMBER);
	      String mobileNo=dbUtil.executeQry(userId);
	        try {
	        	
		           ServiceProviderDAO dao=new ServiceProviderDAO();
		           status  = dao.insertSMSData(list.get(3).toString(), mobileNo, content);

	             } catch (Exception x) {
		               logger.error(x);
	               }
	      dbUtil.commit();
  	 }
  	   catch(Exception e){
  		   e.printStackTrace();
  		   dbUtil.rollback();
  		logger.error(" Exception at serviceProviderDAO in DAO " + e);
  		throw e;
  		}
  	   
  	   finally {
  			 try{	    
  				 dbUtil.closeConnection();
  			 }
  			 catch (Exception e) {
  			 logger.error("Exception at serviceProviderDAO in DAO  " + e);
  			 }		
  	       }
  	   return transactionflag;   
  	 }
    
    public ArrayList getPendingAppsDIG(String ofcId,String lang)
	{
		ArrayList pendingAppListDIG = new ArrayList();
		
		String[] param={ofcId};
		
		try {
			dbUtil = new DBUtility();
			
			String SQL ="";
			if("en".equalsIgnoreCase(lang))
			{
				SQL = ServiceProviderSQL.GET_PENDING_APPS_DIG;
			}
			else
			{
				SQL = ServiceProviderSQL.GET_PENDING_APPS_DIG_H;
			}
					dbUtil.createPreparedStatement(SQL);
				
					
					try
					{	
						
						pendingAppListDIG=dbUtil.executeQuery(param);
				            logger.debug("Wipro in ServiceProviderDAO - getPendingApps() after dbUtil.executeQuery(SQL)");
				           
				            pendingAppListDIG.trimToSize();
			
		} catch (Exception x) {
			logger.debug(x);
			x.printStackTrace();
		} finally {
			try {
				
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				ex.printStackTrace();
			}
		}
		
	}
		catch(Exception e){
			logger.debug(e);
		}
		return pendingAppListDIG;
		}
    
    public ArrayList getPendingAppsSearchDIG1(String ofcId, String fromDate,String toDate)
	{
		ArrayList pendingAppList = new ArrayList();
		
		String[] param={ofcId, fromDate,toDate};
		
		try {
			dbUtil = new DBUtility();
			
					String SQL = ServiceProviderSQL.GET_APPLICATIONS_DETLS_SEARCH_DIG1;
					dbUtil.createPreparedStatement(SQL);
				
					
					try
					{	
						
						pendingAppList=dbUtil.executeQuery(param);
				            logger.debug("Wipro in ServiceProviderDAO - getPendingAppsSearchDIG1() after dbUtil.executeQuery(SQL)");
				           
				            pendingAppList.trimToSize();
			
		} catch (Exception x) {
			logger.debug(x);
			x.printStackTrace();
		} finally {
			try {
				
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				ex.printStackTrace();
			}
		}
		
	}
		catch(Exception e){
			logger.debug(e);
		}
		return pendingAppList;
		}
    
    public ArrayList getPendingAppsSearchDIG2(String ofcId, String applctnNumber)
	{
		ArrayList pendingAppList = new ArrayList();
		
		String[] param={ofcId, applctnNumber};
		
		try {
			dbUtil = new DBUtility();
			
					String SQL = ServiceProviderSQL.GET_APPLICATIONS_DETLS_SEARCH_DIG2;
					dbUtil.createPreparedStatement(SQL);
				
					
					try
					{	
						
						pendingAppList=dbUtil.executeQuery(param);
				            logger.debug("Wipro in ServiceProviderDAO - getPendingAppsSearchDIG2() after dbUtil.executeQuery(SQL)");
				           
				            pendingAppList.trimToSize();
			
		} catch (Exception x) {
			logger.debug(x);
			x.printStackTrace();
		} finally {
			try {
				
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				ex.printStackTrace();
			}
		}
		
	}
		catch(Exception e){
			logger.debug(e);
		}
		return pendingAppList;
		}
    
    public ArrayList getPendingAppsSearchDIG3(String ofcId, String fromDate, String toDate, String applctnNumber)
	{
		ArrayList pendingAppList = new ArrayList();
		
		String[] param={ofcId, fromDate, toDate, applctnNumber};
		
		try {
			dbUtil = new DBUtility();
			
					String SQL = ServiceProviderSQL.GET_APPLICATIONS_DETLS_SEARCH_DIG3;
					dbUtil.createPreparedStatement(SQL);
				
					
					try
					{	
						
						pendingAppList=dbUtil.executeQuery(param);
				            logger.debug("Wipro in ServiceProviderDAO - getPendingAppsSearchDIG3() after dbUtil.executeQuery(SQL)");
				           
				            pendingAppList.trimToSize();
			
		} catch (Exception x) {
			logger.debug(x);
			x.printStackTrace();
		} finally {
			try {
				
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex);
				ex.printStackTrace();
			}
		}
		
	}
		catch(Exception e){
			logger.debug(e);
		}
		return pendingAppList;
		}
    
    public  boolean updateStatusOfApplicatnDIG (ServiceProviderForm objServiceProviderForm) throws Exception{
		 
    	boolean transactionflag = false;
  	  	String updateAppStatus[]= new String[4];
  	  	
  	  	DBUtility dbUtil = null;
  	  	
  	  try{
  		  dbUtil = new DBUtility();
  		  dbUtil.setAutoCommit(false);

  		  updateAppStatus[0] = "1";
  		  updateAppStatus[1] = (String) objServiceProviderForm.getObjServiceProviderDTO().getOfficeId();
  		  updateAppStatus[2] = (String) objServiceProviderForm.getObjServiceProviderDTO().getUid();
  		  updateAppStatus[3] =  (String)objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();		  
  		
  		  
  		  dbUtil.createPreparedStatement(ServiceProviderSQL.UPDATE_APPLCTN_STATUS);
  		  dbUtil.executeUpdate(updateAppStatus);
  		  
  	      transactionflag = true;
  	      dbUtil.commit();				     
  		
  	 }
  	   catch(Exception e){
  		   e.printStackTrace();
  		   dbUtil.rollback();
  		logger.error(" Exception at serviceProviderDAO in DAO " + e);
  		throw e;
  		}
  	   
  	   finally {
  			 try{	    
  				 dbUtil.closeConnection();
  			 }
  			 catch (Exception e) {
  			 logger.error("Exception at serviceProviderDAO in DAO  " + e);
  			 }		
  	       }
  	   return transactionflag;   
  	 }
    
    public  boolean updateStatusOfApplicatnDR (ServiceProviderForm objServiceProviderForm) throws Exception{
		 
    	boolean transactionflag = false;
  	  	String updateAppStatus[]= new String[4];
  	  	
  	  	DBUtility dbUtil = null;
  	  	
  	  try{
  		  dbUtil = new DBUtility();
  		  dbUtil.setAutoCommit(false);

  		  updateAppStatus[0] = "9";
  		  updateAppStatus[1] = (String) objServiceProviderForm.getObjServiceProviderDTO().getOfficeId();
  		  updateAppStatus[2] = (String) objServiceProviderForm.getObjServiceProviderDTO().getUid();
  		  updateAppStatus[3] =  (String)objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();		  
  		
  		  
  		  dbUtil.createPreparedStatement(ServiceProviderSQL.UPDATE_APPLCTN_STATUS);
  		  dbUtil.executeUpdate(updateAppStatus);
  		  
  	      transactionflag = true;
  	      dbUtil.commit();				     
  		
  	 }
  	   catch(Exception e){
  		   e.printStackTrace();
  		   dbUtil.rollback();
  		logger.error(" Exception at serviceProviderDAO in DAO " + e);
  		throw e;
  		}
  	   
  	   finally {
  			 try{	    
  				 dbUtil.closeConnection();
  			 }
  			 catch (Exception e) {
  			 logger.error("Exception at serviceProviderDAO in DAO  " + e);
  			 }		
  	       }
  	   return transactionflag;   
  	 }
    
    public  boolean updateSpDetls (ServiceProviderForm objServiceProviderForm) throws Exception{
		 
  	  boolean transactionflag = false;
  	  String updateSpDetls[]= new String[15];

  	  DBUtility dbUtil = null;
  	  try{
  		  dbUtil = new DBUtility();
  		  dbUtil.setAutoCommit(false);
  		  
  		  String distt = objServiceProviderForm.getObjServiceProviderDTO().getDistrict();
  		  if(distt.length()==1){
  			  distt = "0"+distt;
  		  }
  		  
  		  String spType = objServiceProviderForm.getObjServiceProviderDTO().getSpType();
  		  if(spType.length()==1){
  			  spType = "0"+spType;
  		  }
  		  
  		  if (spType.equalsIgnoreCase("02") || spType.equalsIgnoreCase("03") || spType.equalsIgnoreCase("04"))
  		  {
  			updateSpDetls[0] = (String) objServiceProviderForm.getObjServiceProviderDTO().getBankName();
  			updateSpDetls[1] = (String) objServiceProviderForm.getObjServiceProviderDTO().getAuthPersName();
  			updateSpDetls[2] = (String) objServiceProviderForm.getObjServiceProviderDTO().getDesignation();
  			updateSpDetls[3] = (String) objServiceProviderForm.getObjServiceProviderDTO().getAddressSp();
  			updateSpDetls[4] = (String) objServiceProviderForm.getObjServiceProviderDTO().getDistrict();
  			updateSpDetls[5] = (String) objServiceProviderForm.getObjServiceProviderDTO().getTehsil();
/*  			updateSpDetls[6] = (String) objServiceProviderForm.getObjServiceProviderDTO().getWard();
  			updateSpDetls[7] = (String) objServiceProviderForm.getObjServiceProviderDTO().getMohalla();*/
  			updateSpDetls[6] = (String) objServiceProviderForm.getObjServiceProviderDTO().getWardPatwariId().split("~")[0];
  			updateSpDetls[7] = (String) objServiceProviderForm.getObjServiceProviderDTO().getColonyId().split("~")[0];
  			updateSpDetls[8] = null;
  			updateSpDetls[9] = null;
  			updateSpDetls[10] = (String) objServiceProviderForm.getObjServiceProviderDTO().getRadioSelectExp();
  			updateSpDetls[11] = (String) objServiceProviderForm.getObjServiceProviderDTO().getRadioSelectCh();
  			updateSpDetls[12] = (String) objServiceProviderForm.getObjServiceProviderDTO().getAmount();
  		  }
  		  
  		  if (spType.equalsIgnoreCase("01"))
  		  {
  			updateSpDetls[0] = null;
  			updateSpDetls[1] = null;
  			updateSpDetls[2] = null;
  			updateSpDetls[3] = (String) objServiceProviderForm.getObjServiceProviderDTO().getAddressSp();
  			updateSpDetls[4] = (String) objServiceProviderForm.getObjServiceProviderDTO().getDistrict();
  			updateSpDetls[5] = (String) objServiceProviderForm.getObjServiceProviderDTO().getTehsil();
/*  			updateSpDetls[6] = (String) objServiceProviderForm.getObjServiceProviderDTO().getWard();
  			updateSpDetls[7] = (String) objServiceProviderForm.getObjServiceProviderDTO().getMohalla();*/
  			updateSpDetls[6] = (String) objServiceProviderForm.getObjServiceProviderDTO().getWardPatwariId().split("~")[0];
  			updateSpDetls[7] = (String) objServiceProviderForm.getObjServiceProviderDTO().getColonyId().split("~")[0];
  			updateSpDetls[8] = (String) objServiceProviderForm.getObjServiceProviderDTO().getEducation();
  			updateSpDetls[9] = (String) objServiceProviderForm.getObjServiceProviderDTO().getLangKnown();
  			updateSpDetls[10] = (String) objServiceProviderForm.getObjServiceProviderDTO().getRadioSelectExp();
  			updateSpDetls[11] = (String) objServiceProviderForm.getObjServiceProviderDTO().getRadioSelectCh();
  			updateSpDetls[12] = (String) objServiceProviderForm.getObjServiceProviderDTO().getAmount();  
  		  }
  		  
  		  updateSpDetls[13] = (String) objServiceProviderForm.getObjServiceProviderDTO().getUid();
  		  updateSpDetls[14] = (String)objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
  		  
  		  dbUtil.createPreparedStatement(ServiceProviderSQL.UPDATE_APPLCANT_DETLS);
  		  dbUtil.executeUpdate(updateSpDetls);
  	      transactionflag = true;
  	      dbUtil.commit();				     
  		
  	 }
  	   catch(Exception e){
  		   e.printStackTrace();
  		   dbUtil.rollback();
  		logger.error(" Exception at serviceProviderDAO in DAO " + e);
  		throw e;
  		}
  	   
  	   finally {
  			 try{	    
  				 dbUtil.closeConnection();
  			 }
  			 catch (Exception e) {
  			 logger.error("Exception at serviceProviderDAO in DAO  " + e);
  			 }		
  	       }
  	   return transactionflag;   
  	 }
    
    public String getdisttId(ServiceProviderForm objServiceProviderForm)
	{
		String reqNo = (String)objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
		String disttId=null;
		
		
		
		String[] param={reqNo};
		
		try {
			dbUtil = new DBUtility();
			
					String SQL = ServiceProviderSQL.GET_DISTT_ID_SP;
					dbUtil.createPreparedStatement(SQL);
				
					
					try
					{	
						
							disttId=dbUtil.executeQry(param);
				            logger.debug("-----> in ServiceProviderDAO - getdisttId() after dbUtil.executeUpdate(param)");
			
		} catch (Exception x) {
			logger.debug(x);
			x.printStackTrace();
		} 
		
		finally {
			
			try {
				
				dbUtil.closeConnection();
			} 
			catch (Exception ex) {
				logger.debug(ex);
				ex.printStackTrace();
			}
		}
		
	}
		catch(Exception e){
			logger.debug(e);
		}
		return disttId;
		}
    
    
    public ArrayList getDroDetailsIfAlreadyFiled(ServiceProviderForm objServiceProviderForm,String language) throws Exception
    {

		ArrayList droDetailsList = new ArrayList();
		
		String uid = (String)objServiceProviderForm.getObjServiceProviderDTO().getUid();
		
		String[] param={uid};
		String SQL ="";
		try {
			dbUtil = new DBUtility();
			
			if("en".equalsIgnoreCase(language))
			{
			SQL = ServiceProviderSQL.GET_DRO_DETAILS_IF_ALREADY_FILED;
			}
			else
			{
			SQL = ServiceProviderSQL.GET_DRO_DETAILS_IF_ALREADY_FILED_H;
			}
					dbUtil.createPreparedStatement(SQL);
				
					
					try
					{	
						
						droDetailsList=dbUtil.executeQuery(param);
				            logger.debug("-----> in ServiceProviderDAO - getDroDetailsIfAlreadyFiled() after dbUtil.executeQuery(SQL)");
				           
				            droDetailsList.trimToSize();
			
		} catch (Exception x) {
			logger.debug(x);
			x.printStackTrace();
		} 
		
		finally {
			
			try {
				
				dbUtil.closeConnection();
			} 
			catch (Exception ex) {
				logger.debug(ex);
				ex.printStackTrace();
			}
		}
		
	}
		catch(Exception e){
			logger.debug(e);
		}
		return droDetailsList;
		
    	
    }
    
    public ArrayList getPaymentDetls(ServiceProviderForm objServiceProviderForm,String language) throws Exception
    {

		ArrayList details = new ArrayList();
		
		String requestNo= (String)objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
		
		
		
		String[] param={requestNo,requestNo};
		
		try {
			dbUtil = new DBUtility();
			String SQL="";
			if("en".equalsIgnoreCase(language))
			{
				SQL = ServiceProviderSQL.GET_SP_PAYMENT_DETLS;
			}
			else
			{
				SQL = ServiceProviderSQL.GET_SP_PAYMENT_DETLS_H;
			}
			
				
					dbUtil.createPreparedStatement(SQL);
				
					
					try
					{	
						
							details=dbUtil.executeQuery(param);
				            logger.debug("-----> in ServiceProviderDAO - getPaymentDetls() after dbUtil.executeQuery(SQL)");
				           
				            details.trimToSize();
			
		} catch (Exception x) {
			logger.debug(x);
			x.printStackTrace();
		} 
		
		finally {
			
			try {
				
				dbUtil.closeConnection();
			} 
			catch (Exception ex) {
				logger.debug(ex);
				ex.printStackTrace();
			}
		}
		
	}
		catch(Exception e){
			logger.debug(e);
		}
		return details;
		
    	
    }
    
    public  boolean insertPaymentDetls (ServiceProviderForm objServiceProviderForm) throws Exception{
		 
  	  boolean transactionflag = false;
  	  String insertPayDetls[] = new String[6];

  	  DBUtility dbUtil = null;
  	  try{
  		  dbUtil = new DBUtility();
  		  dbUtil.setAutoCommit(false);
  		  
  		  //String SQL = "select IGRS_SP_PAYMENT_ID_SEQ.nextval from dual";
  	      dbUtil.createStatement();
  		  String number = dbUtil.executeQry(ServiceProviderSQL.GET_SEQ_PAYMENT_ID);
  		  
  		  insertPayDetls[0]= number;
  		  insertPayDetls[1]= (String)objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
  		  insertPayDetls[2]= "0";
  		  insertPayDetls[3]= "I";
  		  insertPayDetls[4]= (String) objServiceProviderForm.getObjServiceProviderDTO().getUid();
  		  insertPayDetls[5]= (Double.toString(objServiceProviderForm.getObjServiceProviderDTO().getAmountRemaining()));
  	      
  	      dbUtil.createPreparedStatement(ServiceProviderSQL.INSERT_SP_PAYMENT_DETLS);
  		  dbUtil.executeUpdate(insertPayDetls);
  	      transactionflag = true;
  	      dbUtil.commit();	
  		
  	 }
  	   catch(Exception e){
  		   e.printStackTrace();
  		   dbUtil.rollback();
  		logger.error(" Exception at serviceProviderDAO in DAO " + e);
  		throw e;
  		}
  	   
  	   finally {
  			 try{	    
  				 dbUtil.closeConnection();
  			 }
  			 catch (Exception e) {
  			 logger.error("Exception at serviceProviderDAO in DAO  " + e);
  			 }		
  	       }
  	   return transactionflag;   
  	 }
    
    public  boolean updateStatus (ServiceProviderForm objServiceProviderForm) throws Exception{
		 
    	  boolean transactionflag = false;
    	  String updateAppStatus[] = new String[3];
    	  String updatePaymntStatus[] = new String[3];

    	  DBUtility dbUtil = null;
    	  try{
    		  dbUtil = new DBUtility();
    		  dbUtil.setAutoCommit(false);
    		  
    		  updateAppStatus[0] = "7";
      		  updateAppStatus[1] = (String) objServiceProviderForm.getObjServiceProviderDTO().getUid();
      		  updateAppStatus[2] =  (String)objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
      		  
      		  
      		  updatePaymntStatus[0] =  "C";
      		  updatePaymntStatus[1] = (String) objServiceProviderForm.getObjServiceProviderDTO().getUid();
      		  updatePaymntStatus[2] =  (String)objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
    	      
    	      dbUtil.createPreparedStatement(ServiceProviderSQL.UPDATE_APLCTN_STATUS_AFTER_PAYMENT);
    		  dbUtil.executeUpdate(updateAppStatus);
    		  
    		  dbUtil.createPreparedStatement(ServiceProviderSQL.UPDATE_PAYMNT_STATUS_AFTER_PAYMENT);
    		  dbUtil.executeUpdate(updatePaymntStatus);
    		  
    	      transactionflag = true;
    	      dbUtil.commit();	
    	      
    	      //added by shruti---6 march 2014
    	      String[] uid={(String) objServiceProviderForm.getObjServiceProviderDTO().getUid()};
    	      dbUtil.createPreparedStatement(ServiceProviderSQL.GETUSERNAME);
    	      String userName=dbUtil.executeQry(uid);
    	      
    	      String status = new String();
    	      String userId=(String) objServiceProviderForm.getObjServiceProviderDTO().getUid();
    	      String subject=CommonConstant.EMAILSUB_FEEPAID;
    	      String content="This is in reference of your request Id "+(String)objServiceProviderForm.getObjServiceProviderDTO().getRequestNo()+ ".You have successfully paid the License fee.";
    	        try {
    	        	
    		           ServiceProviderDAO dao=new ServiceProviderDAO();
    		           status  = dao.insertEmailData(userId, subject, content);

    	             } catch (Exception x) {
    		               logger.error(x);
    	               }
    	      dbUtil.commit();
    		
    	 }
    	   catch(Exception e){
    		   e.printStackTrace();
    		   dbUtil.rollback();
    		logger.error(" Exception at serviceProviderDAO in DAO " + e);
    		throw e;
    		}
    	   
    	   finally {
    			 try{	    
    				 dbUtil.closeConnection();
    			 }
    			 catch (Exception e) {
    			 logger.error("Exception at serviceProviderDAO in DAO  " + e);
    			 }		
    	       }
    	   return transactionflag;   
    	 }
    
    public ArrayList getPreviousLicenseDetailsEstamp(ServiceProviderForm objServiceProviderForm) throws Exception
    {

		ArrayList details = new ArrayList();
		
		String licenseNumber= (String)objServiceProviderForm.getObjServiceProviderDTO().getLicenseNumber();
		
		
		
		String[] param={licenseNumber,licenseNumber,licenseNumber,licenseNumber};
		
		try {
			dbUtil = new DBUtility();
			
					String SQL = ServiceProviderSQL.GET_SP_PREVIOUS_LICENSE_ESTAMP_AMOUNT_DETLS;
					dbUtil.createPreparedStatement(SQL);
				
					
					try
					{	
						
							details=dbUtil.executeQuery(param);
				            logger.debug("-----> in ServiceProviderDAO - getPaymentDetls() after dbUtil.executeQuery(SQL)");
				           
				            details.trimToSize();
			
		} catch (Exception x) {
			logger.debug(x);
			x.printStackTrace();
		} 
		
		finally {
			
			try {
				
				dbUtil.closeConnection();
			} 
			catch (Exception ex) {
				logger.debug(ex);
				ex.printStackTrace();
			}
		}
		
	}
		catch(Exception e){
			logger.debug(e);
		}
		return details;
		
    	
    }
    
    public ArrayList getPreviousLicenseDetailsOthers(ServiceProviderForm objServiceProviderForm) throws Exception
    {

		ArrayList details = new ArrayList();
		
		String licenseNumber= (String)objServiceProviderForm.getObjServiceProviderDTO().getLicenseNumber();
		
		
		
		String[] param={licenseNumber,licenseNumber,licenseNumber,licenseNumber};
		
		try {
			dbUtil = new DBUtility();
			
					String SQL = ServiceProviderSQL.GET_SP_PREVIOUS_LICENSE_OTHERS_AMOUNT_DETLS;
					dbUtil.createPreparedStatement(SQL);
				
					
					try
					{	
						
							details=dbUtil.executeQuery(param);
				            logger.debug("-----> in ServiceProviderDAO - getPaymentDetls() after dbUtil.executeQuery(SQL)");
				           
				            details.trimToSize();
			
		} catch (Exception x) {
			logger.debug(x);
			x.printStackTrace();
		} 
		
		finally {
			
			try {
				
				dbUtil.closeConnection();
			} 
			catch (Exception ex) {
				logger.debug(ex);
				ex.printStackTrace();
			}
		}
		
	}
		catch(Exception e){
			logger.debug(e);
		}
		return details;
		
    	
    }
    
    public ArrayList getFirstTimeLicenseDetails(ServiceProviderForm objServiceProviderForm) throws Exception
    {

		ArrayList details = new ArrayList();
		
		String requestNo= (String)objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
		
		
		
		String[] param={requestNo};
		
		try {
			dbUtil = new DBUtility();
			
					String SQL = ServiceProviderSQL.GET_SP_FIRST_TIME_LICENSE_DETLS;
					dbUtil.createPreparedStatement(SQL);
				
					
					try
					{	
						
							details=dbUtil.executeQuery(param);
				            logger.debug("-----> in ServiceProviderDAO - getPaymentDetls() after dbUtil.executeQuery(SQL)");
				           
				            details.trimToSize();
			
		} catch (Exception x) {
			logger.debug(x);
			x.printStackTrace();
		} 
		
		finally {
			
			try {
				
				dbUtil.closeConnection();
			} 
			catch (Exception ex) {
				logger.debug(ex);
				ex.printStackTrace();
			}
		}
		
	}
		catch(Exception e){
			logger.debug(e);
		}
		return details;
		
    	
    }
    
    public  boolean insertLicenseNumber (ServiceProviderForm objServiceProviderForm) throws Exception{
		 
    	boolean transactionflag = false;
    	  //String estmTxnId = null;
    	  String licenseNumber[]= new String[3];
    	 
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
    	  
    	// for dropping license number sequence on monthly rollover basis.
    	  
    	  String SQL = ServiceProviderSQL.GET_TODAYS_DATE;
    	  transmgtUtil.createStatement();
    	  String number3 = transmgtUtil.executeQry(SQL);
    	  
    	  logger.debug("*******" +number3);
    	  String subDate = number3.substring(0, 2);
    	  logger.debug("******* Substring obtained" +subDate);
    	  
    	  if (subDate.equalsIgnoreCase("01"))
    	  {
    		  //String SQL1 = "SELECT COUNT(LICENSE_NUMBER) FROM IGRS_SP_DETLS WHERE TRUNC(CREATED_DATE)= TRUNC(SYSDATE)";
        	  //logger.debug(SQL1);
        	  transmgtUtil.createStatement();
        	  String number1 = transmgtUtil.executeQry(ServiceProviderSQL.GET_COUNT_OF_SEQ_LICENSE_NMBR);
        	  
        	  if (number1.equalsIgnoreCase("0"))
        	  {
        			logger.debug("in if clause");
        			//String drpqry = "DROP SEQUENCE IGRS_SP_LICENSE_NUMBER_SEQ";		
        			transmgtUtil.createStatement();
        			transmgtUtil.executeUpdate(ServiceProviderSQL.DROP_QRY_FOR_SEQ_LICENSE_NMBR);
        			//String crtqry = "CREATE SEQUENCE IGRS_SP_LICENSE_NUMBER_SEQ START WITH 1 INCREMENT BY 1 MAXVALUE 999999 MINVALUE 1 NOCYCLE  NOCACHE ORDER";

        			
        			transmgtUtil.createStatement();
        			transmgtUtil.executeUpdate(ServiceProviderSQL.CREATE_SEQ_FOR_SEQ_LICENSE_NMBR);
        		}

    	  }
    		 // String SQL2 = "select IGRS_SP_LICENSE_NUMBER_SEQ.nextval from dual";

    		  transmgtUtil.createStatement();
    		  String number2 = transmgtUtil.executeQry(ServiceProviderSQL.GET_SEQ_LICENSE_NUMBER);
    		  if(number2.length()==1){
    			  number2="0000"+number2;  
    			  }else
    			  if(number2.length()==2){
    				  number2="000"+number2;  
    			  }else
    			  if(number2.length()==3){
    				  number2="00"+number2;  
    			  }else
    			  if(number2.length()==4){
    				  number2="0"+number2;  
    			  }
    		  
    		  String drDistrict = (String)objServiceProviderForm.getObjServiceProviderDTO().getDrDistrict();
    		  if(drDistrict.length()==1){
    			  drDistrict = "0"+drDistrict;
    		  }
    		  
    		  String spType = objServiceProviderForm.getObjServiceProviderDTO().getSpType();
    		  if(spType.length()==1){
    			  spType = "0"+spType;
    		  }
    		  
    		  String ofcId = (String) objServiceProviderForm.getObjServiceProviderDTO().getOfficeId();
    		  logger.debug("The office id is: " +ofcId);
    		  
    		  if (ofcId.length()== 4)
    		  {
    			  ofcId = "0"+"0"+ objServiceProviderForm.getObjServiceProviderDTO().getOfficeId().toString().substring(3);
    		  }
    		  else if (ofcId.length()== 5)
    		  {
    			  ofcId = "0"+((String) objServiceProviderForm.getObjServiceProviderDTO().getOfficeId()).substring(3,5);
    			  logger.debug("sub string of office id is: "+ofcId);
    		  }
    		  else if (ofcId.length()== 6)
    		  {
    			  ofcId = (String) objServiceProviderForm.getObjServiceProviderDTO().getOfficeId().toString().substring(3,6);
    		  }
    		  
    		  
    		  
    		  String licenseNo = "SP"+spType+drDistrict+ofcId+mfmt+yfmt+number2;
    		  
    		 
      		  
      		 		String check = ServiceProviderSQL.CHECK_UNIQUE_LICENCE_NUMBER;
      		 		transmgtUtil.createStatement();
      		 		 transmgtUtil.createPreparedStatement(check);
      		 		String checkarr[] = new String[1];
      		 		checkarr[0]=licenseNo;
      		 		String count = transmgtUtil.executeQry(checkarr);
      		 		if(Integer.parseInt(count)==0)
      		 		{
    		  
      		 		licenseNumber[0] = licenseNo;
      		 		licenseNumber[1] = (String)objServiceProviderForm.getObjServiceProviderDTO().getUid();
      		 		licenseNumber[2] = (String)objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();

      		 		transmgtUtil.createPreparedStatement(ServiceProviderSQL.UPDATE_LICENSE_NUMBER);
      		 		transmgtUtil.executeUpdate(licenseNumber);
      		 		transactionflag = true;
      		 		transmgtUtil.commit();
      		 		}
      		 		else{
      		 			throw new Exception(Constants.PDFEXCEPTION);
      		 		}
    		
    	 }
    	   catch(Exception e){
    		   e.printStackTrace();
    		   transmgtUtil.rollback();
    		logger.error(" Exception at serviceProviderDAO in DAO " + e);
    		throw e;
    		}
    	   
    	   finally {
    			 try{	    
    				 transmgtUtil.closeConnection();
    			 }
    			 catch (Exception e) {
    			 logger.error("Exception at serviceProviderDAO in DAO  " + e);
    			 }		
    	       }
    	   return transactionflag;   
    	 }
    
    public ArrayList getDRComments(ServiceProviderForm objServiceProviderForm) throws Exception
    {

		ArrayList details = new ArrayList();
		
		String requestNo= (String)objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
		String applctnStatus = (String)objServiceProviderForm.getObjServiceProviderDTO().getApplicantStatus();
		
		
		
		String[] param={requestNo,applctnStatus,requestNo,applctnStatus};
		
		try {
			dbUtil = new DBUtility();
			
					String SQL = ServiceProviderSQL.GET_DR_COMMENTS;
					dbUtil.createPreparedStatement(SQL);
				
					
					try
					{	
						
							details=dbUtil.executeQuery(param);
				            logger.debug("-----> in ServiceProviderDAO - getDRComments() after dbUtil.executeQuery(SQL)");
				           
				            details.trimToSize();
			
		} catch (Exception x) {
			logger.debug(x);
			x.printStackTrace();
		} 
		
		finally {
			
			try {
				
				dbUtil.closeConnection();
			} 
			catch (Exception ex) {
				logger.debug(ex);
				ex.printStackTrace();
			}
		}
		
	}
		catch(Exception e){
			logger.debug(e);
		}
		return details;
		
    	
    }
    
    public ArrayList getLicenseNumber(ServiceProviderForm objServiceProviderForm) throws Exception
    {

		ArrayList details = new ArrayList();
		
		String requestNo= (String)objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
		
		
		
		String[] param={requestNo};
		
		try {
			dbUtil = new DBUtility();
			
					String SQL = ServiceProviderSQL.GET_LICENSE_DETLS;
					dbUtil.createPreparedStatement(SQL);
				
					
					try
					{	
						
							details=dbUtil.executeQuery(param);
				            logger.debug("-----> in ServiceProviderDAO - getLicenseNumber() after dbUtil.executeQuery(SQL)");
				           
				            details.trimToSize();
			
		} catch (Exception x) {
			logger.debug(x);
			x.printStackTrace();
		} 
		
		finally {
			
			try {
				
				dbUtil.closeConnection();
			} 
			catch (Exception ex) {
				logger.debug(ex);
				ex.printStackTrace();
			}
		}
		
	}
		catch(Exception e){
			logger.debug(e);
		}
		return details;
		
    	
    }
    public ArrayList cancelLicenseDashboard(ServiceProviderDTO spDTO,String lang)
	{
    	//changes regarding date duration-done by shruti
		String sqlQuery="";
		String sqlQuery1="";
		String sqlQuery2="";
		ArrayList list=null;
		DBUtility dbUtil = null;
		
		String[] param = {spDTO.getOfficeId().toString(),spDTO.getLicenseId()};
		
			try {
				dbUtil = new DBUtility();
				dbUtil.createStatement();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if(spDTO.getSearchType().equalsIgnoreCase("L"))
			{
			//sqlQuery="SELECT SP.LICENSE_NUMBER,(SELECT SP_TYPE_NAME FROM IGRS_SP_TYPE_MASTER WHERE SP_TYPE_ID=SP.SP_TYPE_ID),SP.BANK_NAME,(SELECT FIRST_NAME||' '||MIDDLE_NAME||''||LAST_NAME FROM IGRS_USER_REG_DETAILS WHERE User_id=SP.CREATED_BY),TO_CHAR(UPDATE_DATE,'DD/MM/YYYY'),(SELECT SP_STATUS_NAME FROM IGRS_SP_STATUS_MASTER WHERE SP_STATUS_ID=SP.APPLCTN_STATUS) FROM IGRS_SP_DETLS SP WHERE SP.STATUS='A' AND SP.APPLCTN_STATUS='8' AND SP.LICENSE_NUMBER='";
			try {
				//list =dbUtil.executeQuery(sqlQuery+spDTO.getLicenseId()+"'");
				if("en".equalsIgnoreCase(lang))
				{
				sqlQuery = ServiceProviderSQL.GET_SP_LICENSE_DETLS_FOR_CANCELLATION;
				}
				else
				{
					sqlQuery = ServiceProviderSQL.GET_SP_LICENSE_DETLS_FOR_CANCELLATION_H;
				}
				
				dbUtil.createPreparedStatement(sqlQuery);
				
				list=dbUtil.executeQuery(param);
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}finally {
   			 try{	    
				 dbUtil.closeConnection();
			 }
			 catch (Exception e) {
			 logger.error("Exception at serviceProviderDAO in DAO  " + e);
			 }	
			}
			
			}
			else if(spDTO.getSearchType().equalsIgnoreCase("N"))
			{
				//MODIFIED BY SHRUTI
				if("en".equalsIgnoreCase(lang))
				{
					sqlQuery="SELECT DISTINCT SP.LICENSE_NUMBER,(SELECT SP_TYPE_NAME FROM IGRS_SP_TYPE_MASTER WHERE SP_TYPE_ID=SP.SP_TYPE_ID),SP.BANK_NAME,(SELECT FIRST_NAME||' '||MIDDLE_NAME||''||LAST_NAME FROM IGRS_USER_REG_DETAILS WHERE User_id=SP.CREATED_BY),TO_CHAR(UPDATE_DATE,'DD/MM/YYYY'),(SELECT SP_STATUS_NAME FROM IGRS_SP_STATUS_MASTER WHERE SP_STATUS_ID=SP.APPLCTN_STATUS) FROM IGRS_SP_DETLS SP WHERE SP.STATUS='A' AND SP.APPLCTN_STATUS='8' AND SP.DISTRICT_ID=(SELECT DISTRICT_ID FROM IGRS_OFFICE_MASTER WHERE OFFICE_ID='";
					sqlQuery2="') AND (UPPER(SP.BANK_NAME) LIKE '%";
				
				}
				else
				{
					sqlQuery="SELECT DISTINCT SP.LICENSE_NUMBER,(SELECT H_SP_TYPE_NAME FROM IGRS_SP_TYPE_MASTER WHERE SP_TYPE_ID=SP.SP_TYPE_ID),SP.BANK_NAME,(SELECT FIRST_NAME||' '||MIDDLE_NAME||''||LAST_NAME FROM IGRS_USER_REG_DETAILS WHERE User_id=SP.CREATED_BY),TO_CHAR(UPDATE_DATE,'DD/MM/YYYY'),(SELECT H_SP_STATUS_NAME FROM IGRS_SP_STATUS_MASTER WHERE SP_STATUS_ID=SP.APPLCTN_STATUS) FROM IGRS_SP_DETLS SP WHERE SP.STATUS='A' AND SP.APPLCTN_STATUS='8' AND SP.DISTRICT_ID=(SELECT DISTRICT_ID FROM IGRS_OFFICE_MASTER WHERE OFFICE_ID='";
					sqlQuery2="') AND (UPPER(SP.BANK_NAME) LIKE '%";	
				}
				//end
			sqlQuery1="%' OR UPPER((SELECT FIRST_NAME FROM IGRS_USER_REG_DETAILS WHERE User_id=SP.CREATED_BY)) LIKE '%";


			try {
				list =dbUtil.executeQuery(sqlQuery+spDTO.getOfficeId().toString()+sqlQuery2+spDTO.getSearchName().toUpperCase()+sqlQuery1+spDTO.getSearchName().toUpperCase()+"%')");
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}finally {
	   			 try{	    
					 dbUtil.closeConnection();
				 }
				 catch (Exception e) {
				 logger.error("Exception at serviceProviderDAO in DAO  " + e);
				 }	
				}
			}
			else if(spDTO.getSearchType().equalsIgnoreCase("D"))
			{
				
			//sqlQuery="SELECT SP.LICENSE_NUMBER,(SELECT SP_TYPE_NAME FROM IGRS_SP_TYPE_MASTER WHERE SP_TYPE_ID=SP.SP_TYPE_ID),SP.BANK_NAME,(SELECT FIRST_NAME||' '||MIDDLE_NAME||''||LAST_NAME FROM IGRS_USER_REG_DETAILS WHERE User_id=SP.CREATED_BY),TO_CHAR(UPDATE_DATE,'DD/MM/YYYY'),(SELECT SP_STATUS_NAME FROM IGRS_SP_STATUS_MASTER WHERE SP_STATUS_ID=SP.APPLCTN_STATUS) FROM IGRS_SP_DETLS SP WHERE SP.STATUS='A' AND SP.APPLCTN_STATUS='8' AND to_char(SP.UPDATE_DATE,'dd/mm/yyyy') BETWEEN '";
			//sqlQuery1="' AND '";
			//String sqlQuery2="'";
			try 
			{
				//list=dbUtil.executeQuery(sqlQuery+spDTO.getFromCreatedDate()+sqlQuery1+spDTO.getToCreatedDate()+sqlQuery2);
				if("en".equalsIgnoreCase(lang))
				{
				sqlQuery = ServiceProviderSQL.GET_SEARCH_DETLS_CANCELLATION;
				}
				else
				{
				sqlQuery = ServiceProviderSQL.GET_SEARCH_DETLS_CANCELLATION_H;
				}
				
				dbUtil.createPreparedStatement(sqlQuery);
				
				list=dbUtil.executeQuery(new String[]{spDTO.getOfficeId().toString(),(String) spDTO.getFromCreatedDate(),(String) spDTO.getToCreatedDate()});
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			finally {
	   			 try{	    
					 dbUtil.closeConnection();
				 }
				 catch (Exception e) {
				 logger.error("Exception at serviceProviderDAO in DAO  " + e);
				 }	
				}
			}
    	return list;
	}

    public  boolean updateStatusToLicenseIssued (ServiceProviderForm objServiceProviderForm) throws Exception{
		 
    	boolean transactionflag = false;
  	  	String updateAppStatus[]= new String[4];
  	  	String assignRights[] = new String[1];
  	  	String spTypeId="";
  	  	String[] param={(String)objServiceProviderForm.getObjServiceProviderDTO().getRequestNo()};
  	  	
  	  	
  	  	
  	  	DBUtility dbUtil = null;
  	  	
  	  try{
  		  dbUtil = new DBUtility();
  		  dbUtil.setAutoCommit(false);

  		  updateAppStatus[0] = "8";
  		  updateAppStatus[1] = (String) objServiceProviderForm.getObjServiceProviderDTO().getOfficeId();
  		  updateAppStatus[2] = (String) objServiceProviderForm.getObjServiceProviderDTO().getUid();
  		  updateAppStatus[3] =  (String)objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();	
  		  
  		  assignRights[0] = (String)objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
  		
  		  
  		  dbUtil.createPreparedStatement(ServiceProviderSQL.UPDATE_APPLCTN_STATUS);
  		  dbUtil.executeUpdate(updateAppStatus);
  		  
  		  
  		  String SQL = ServiceProviderSQL.GET_SP_TYPE_ID;
  		  dbUtil.createPreparedStatement(SQL);
  		  
  	  	  spTypeId=dbUtil.executeQry(param);
  	  	  
  	  	  if((spTypeId).equalsIgnoreCase("1"))
  	  	  {
  	  	  dbUtil.createPreparedStatement(ServiceProviderSQL.ASSIGN_RIGHTS_FOR_SP_INDIVIDUAL);
  		  dbUtil.executeUpdate(assignRights);
  		  
  		  dbUtil.createPreparedStatement(ServiceProviderSQL.ASSIGN_USER_TYPE_SP_INDIVIDUAL);
  		  dbUtil.executeUpdate(assignRights); 
  	  	  }
  	  	  
  	  	  else if ((spTypeId).equalsIgnoreCase("2"))
  	  	  {
  	  	  dbUtil.createPreparedStatement(ServiceProviderSQL.ASSIGN_RIGHTS_FOR_SP_BANK);
  		  dbUtil.executeUpdate(assignRights);
  		  
  		  dbUtil.createPreparedStatement(ServiceProviderSQL.ASSIGN_USER_TYPE_SP_BANK);
  		  dbUtil.executeUpdate(assignRights);  
  	  	  }
  	  	  
  	  	  else if ((spTypeId).equalsIgnoreCase("3"))
	  	  {
  	  	  dbUtil.createPreparedStatement(ServiceProviderSQL.ASSIGN_RIGHTS_FOR_FI);
  		  dbUtil.executeUpdate(assignRights);
  		  
  		  dbUtil.createPreparedStatement(ServiceProviderSQL.ASSIGN_USER_TYPE_FI);
  		  dbUtil.executeUpdate(assignRights);  
	  	  }
  	  	  
  	  	  else if ((spTypeId).equalsIgnoreCase("4"))
	  	  {
  	  	  dbUtil.createPreparedStatement(ServiceProviderSQL.ASSIGN_RIGHTS_FOR_PO);
  		  dbUtil.executeUpdate(assignRights);
  		  
  		  dbUtil.createPreparedStatement(ServiceProviderSQL.ASSIGN_USER_TYPE_PO);
  		  dbUtil.executeUpdate(assignRights);  
	  	  }
		  
  		  
  		  
  	      transactionflag = true;
  	      dbUtil.commit();				     
  		
  	 }
  	   catch(Exception e){
  		   e.printStackTrace();
  		   dbUtil.rollback();
  		logger.error(" Exception at serviceProviderDAO in DAO " + e);
  		throw e;
  		}
  	   
  	   finally {
  			 try{	    
  				 dbUtil.closeConnection();
  			 }
  			 catch (Exception e) {
  			 logger.error("Exception at serviceProviderDAO in DAO  " + e);
  			 }		
  	       }
  	   return transactionflag;   
  	 }

    public String getRequestNumber(String licenseId)
	{
		String requestNumber="";
		String[] param = {licenseId};
    	
    	try {
			dbUtil = new DBUtility();
			
			dbUtil.createStatement();
			
			String SQL = ServiceProviderSQL.GET_REQ_NO_FOR_CANCELLATION;
	  		dbUtil.createPreparedStatement(SQL);
	  		  
	  		requestNumber=dbUtil.executeQry(param);
	  		
			//requestNumber=dbUtil.executeQry("SELECT REQUEST_NUMBER FROM IGRS_SP_DETLS WHERE LICENSE_NUMBER='"+licenseId+"'");
	  		
    		} catch (Exception e) {
			e.printStackTrace();
    		}finally {
  			 try{	    
				 dbUtil.closeConnection();
			 }
			 catch (Exception e) {
			 logger.error("Exception at serviceProviderDAO in DAO  " + e);
			 }	
			}
		
    	return requestNumber;
	}

    public  boolean updateLicenseDate(ServiceProviderForm objServiceProviderForm) throws Exception{
		 
    	boolean transactionflag = false;
  	  	String updateLicenseDate[]= new String[5];
  	  	
  	  	DBUtility dbUtil = null;
  	  	
  	  try{
  		  dbUtil = new DBUtility();
  		  dbUtil.setAutoCommit(false);

  		  updateLicenseDate[0] = (String) objServiceProviderForm.getObjServiceProviderDTO().getDurationFrom();
  		  updateLicenseDate[1] = (String) objServiceProviderForm.getObjServiceProviderDTO().getDurationTo();
  		  updateLicenseDate[2] = (String) objServiceProviderForm.getObjServiceProviderDTO().getOfficeId();
  		  updateLicenseDate[3] = (String) objServiceProviderForm.getObjServiceProviderDTO().getUid();
  		  updateLicenseDate[4] =  (String)objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();		  
  		
  		  
  		  dbUtil.createPreparedStatement(ServiceProviderSQL.UPDATE_LICENSE_DATE);
  		  dbUtil.executeUpdate(updateLicenseDate);
  		  
  	      transactionflag = true;
  	      dbUtil.commit();				     
  		
  	 }
  	   catch(Exception e){
  		   e.printStackTrace();
  		   dbUtil.rollback();
  		logger.error(" Exception at serviceProviderDAO in DAO " + e);
  		throw e;
  		}
  	   
  	   finally {
  			 try{	    
  				 dbUtil.closeConnection();
  			 }
  			 catch (Exception e) {
  			 logger.error("Exception at serviceProviderDAO in DAO  " + e);
  			 }		
  	       }
  	   return transactionflag;   
  	 }

    public  boolean insertDRCommentsCancel (ServiceProviderForm objServiceProviderForm) throws Exception{
		 
    	boolean transactionflag = false;
  	  	String updateAppStatus[]= new String[4];
  	  	String insertDRAppComments[]= new String[5];
  	  	String updateRightsAssigned[] = new String[1];
  	  	String revokeRights="";

  	  DBUtility dbUtil = null;
  	  try{
  		  dbUtil = new DBUtility();
  		  dbUtil.setAutoCommit(false);
  		  
  	      //String SQL = "select IGRS_SP_COMMENTS_ID_SEQ.nextval from dual";
  	      dbUtil.createStatement();
  		  String number = dbUtil.executeQry(ServiceProviderSQL.GET_SEQ_COMMENTS_ID);

  		  updateAppStatus[0] = "11";
  		  updateAppStatus[1] = (String) objServiceProviderForm.getObjServiceProviderDTO().getOfficeId();
  		  updateAppStatus[2] = (String) objServiceProviderForm.getObjServiceProviderDTO().getUid();
  		  updateAppStatus[3] =  (String)objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
  		  
  		  insertDRAppComments[0] =  number;
  		  insertDRAppComments[1] =  (String)objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
  		  insertDRAppComments[2] =  objServiceProviderForm.getObjServiceProviderDTO().getGistOrder();
  		  insertDRAppComments[3] =  "11";
  		  insertDRAppComments[4] = (String) objServiceProviderForm.getObjServiceProviderDTO().getUid();
  		  
  		  updateRightsAssigned[0] = (String)objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
  		  
  		
  		  
  		  dbUtil.createPreparedStatement(ServiceProviderSQL.UPDATE_APPLCTN_STATUS);
  		  dbUtil.executeUpdate(updateAppStatus);
  		  
  		  dbUtil.createPreparedStatement(ServiceProviderSQL.INSERT_DR_COMMENTS);
		  dbUtil.executeUpdate(insertDRAppComments);
		  
		  dbUtil.createPreparedStatement(ServiceProviderSQL.REVOKE_RIGHTS_ON_CANCELLATION);
		  dbUtil.executeUpdate(updateRightsAssigned);
		  
		  dbUtil.createPreparedStatement(ServiceProviderSQL.REVOKE_USER_TYPE_ON_CANCELLATION);
		  dbUtil.executeUpdate(updateRightsAssigned);
		  
		  
		  
  	      transactionflag = true;
  	      dbUtil.commit();				     
  		
  	 }
  	   catch(Exception e){
  		   e.printStackTrace();
  		   dbUtil.rollback();
  		logger.error(" Exception at serviceProviderDAO in DAO " + e);
  		throw e;
  		}
  	   
  	   finally {
  			 try{	    
  				 dbUtil.closeConnection();
  			 }
  			 catch (Exception e) {
  			 logger.error("Exception at serviceProviderDAO in DAO  " + e);
  			 }		
  	       }
  	   return transactionflag;   
  	 }

    public ArrayList getPdfDetlsForLicense(ServiceProviderForm objServiceProviderForm,String language) throws Exception
    {

		ArrayList details = new ArrayList();
		
		String requestNo= (String)objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
		String officialsId=(String)objServiceProviderForm.getUserId();
		String SQL="";
		//MODIFIED BY SHRUTI----18 MARCH 2014----DMS INTEGRATION
		String[] param={requestNo,requestNo,officialsId,officialsId,requestNo,requestNo};
		
		try {
			dbUtil = new DBUtility();
			
			if("en".equalsIgnoreCase(language))
			{
					SQL = ServiceProviderSQL.GET_DETLS_FOR_PDF_OF_LICENSE;
			}
			else
			{		
					SQL = ServiceProviderSQL.GET_DETLS_FOR_PDF_OF_LICENSE_H;
			}
					dbUtil.createPreparedStatement(SQL);
				
					
					try
					{	
						
							details=dbUtil.executeQuery(param);
				            logger.debug("-----> in ServiceProviderDAO - getPdfDetlsForLicense() after dbUtil.executeQuery(SQL)");
				           
				            details.trimToSize();
			
		} catch (Exception x) {
			logger.debug(x);
			x.printStackTrace();
		} 
		
		finally {
			
			try {
				
				dbUtil.closeConnection();
			} 
			catch (Exception ex) {
				logger.debug(ex);
				ex.printStackTrace();
			}
		}
		
	}
		catch(Exception e){
			logger.debug(e);
		}
		return details;
		
    	
    }
    
    public String getTodaysDate() throws Exception
    {

    	String number="";
		
		try {
			dbUtil = new DBUtility();
			
			String SQL = ServiceProviderSQL.GET_TODAYS_DATE;
			dbUtil.createStatement();
	    	  
				
					
					try
					{	
						
							number = dbUtil.executeQry(SQL);
				            logger.debug("-----> in ServiceProviderDAO - getTodaysDate() after dbUtil.executeQry(SQL)");
			
		} catch (Exception x) {
			logger.debug(x);
			x.printStackTrace();
		} 
		
		finally {
			
			try {
				
				dbUtil.closeConnection();
			} 
			catch (Exception ex) {
				logger.debug(ex);
				ex.printStackTrace();
			}
		}
		
	}
		catch(Exception e){
			logger.debug(e);
		}
		return number;
		
    	
    }
    
    public ArrayList getDroDetailsIfAlreadyFiledRenewal(ServiceProviderForm objServiceProviderForm,String lang) throws Exception
    {

		ArrayList droDetailsList = new ArrayList();
		
		String uid = (String)objServiceProviderForm.getObjServiceProviderDTO().getUid();
		
		String[] param={uid};
		
		try {
			dbUtil = new DBUtility();
			
					String SQL ="";
					if("en".equalsIgnoreCase(lang))
					{
					SQL=ServiceProviderSQL.GET_DRO_DETAILS_IF_ALREADY_FILED_RENEWAL;
					}
					else
					{
						SQL=ServiceProviderSQL.GET_DRO_DETAILS_IF_ALREADY_FILED_RENEWAL_H;	
					}
					dbUtil.createPreparedStatement(SQL);
				
					
					try
					{	
						
						droDetailsList=dbUtil.executeQuery(param);
				            logger.debug("-----> in ServiceProviderDAO - getDroDetailsIfAlreadyFiledRenewal() after dbUtil.executeQuery(SQL)");
				           
				            droDetailsList.trimToSize();
			
		} catch (Exception x) {
			logger.debug(x);
			x.printStackTrace();
		} 
		
		finally {
			
			try {
				
				dbUtil.closeConnection();
			} 
			catch (Exception ex) {
				logger.debug(ex);
				ex.printStackTrace();
			}
		}
		
	}
		catch(Exception e){
			logger.debug(e);
		}
		return droDetailsList;
		
    	
    }
    
    public ArrayList getSpTypeRenewal(ServiceProviderForm objServiceProviderForm,String lang) throws Exception
    {

		ArrayList spType = new ArrayList();
		
		String uid = (String)objServiceProviderForm.getObjServiceProviderDTO().getUid();
		
		String[] param={uid};
		String SQL="";
		try {
			dbUtil = new DBUtility();
			if("en".equalsIgnoreCase(lang))
			{
					SQL = ServiceProviderSQL.GET_SP_TYPE_RENEWAL;
			}
			else
			{
				SQL = ServiceProviderSQL.GET_SP_TYPE_RENEWAL_H;	
			}
					dbUtil.createPreparedStatement(SQL);
				
					
					try
					{	
						
						spType=dbUtil.executeQuery(param);
				            logger.debug("-----> in ServiceProviderDAO - getspTypeRenewal() after dbUtil.executeQuery(SQL)");
				           
				            spType.trimToSize();
			
		} catch (Exception x) {
			logger.debug(x);
			x.printStackTrace();
		} 
		
		finally {
			
			try {
				
				dbUtil.closeConnection();
			} 
			catch (Exception ex) {
				logger.debug(ex);
				ex.printStackTrace();
			}
		}
		
	}
		catch(Exception e){
			logger.debug(e);
		}
		return spType;
		
    	
    }
    
    public ArrayList getspDetlsForRenewal(ServiceProviderForm objServiceProviderForm) throws Exception
    {

		ArrayList details = new ArrayList();
		
		String userId= (String)objServiceProviderForm.getObjServiceProviderDTO().getUid();
		
		
		
		String[] param={userId};
		
		try {
			dbUtil = new DBUtility();
			
					String SQL = ServiceProviderSQL.GET_SP_DETLS_FOR_RENEWAL;
					dbUtil.createPreparedStatement(SQL);
				
					
					try
					{	
						
							details=dbUtil.executeQuery(param);
				            logger.debug("-----> in ServiceProviderDAO - getspDetlsForRenewal() after dbUtil.executeQuery(SQL)");
				           
				            details.trimToSize();
			
		} catch (Exception x) {
			logger.debug(x);
			x.printStackTrace();
		} 
		
		finally {
			
			try {
				
				dbUtil.closeConnection();
			} 
			catch (Exception ex) {
				logger.debug(ex);
				ex.printStackTrace();
			}
		}
		
	}
		catch(Exception e){
			logger.debug(e);
		}
		return details;
		
    	
    }
    
    public ArrayList getLicenseDetails(ServiceProviderForm objServiceProviderForm) throws Exception
    {

		ArrayList spType = new ArrayList();
		
		String requestNo = (String)objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
		
		String[] param={requestNo};
		
		try {
			dbUtil = new DBUtility();
			//modified by shruti--20 feb 2014
			String SQL ="";
			SQL = ServiceProviderSQL.GET_ACTIVE_LICENSE_NUMBER;
			//MODIFIED ---13 MARCH 2014
			/*if(objServiceProviderForm.getObjServiceProviderDTO().getApplicantStatus().equals("8"))
			{
					SQL = ServiceProviderSQL.GET_ACTIVE_LICENSE_NUMBER;
			}
			else if(objServiceProviderForm.getObjServiceProviderDTO().getApplicantStatus().equals("7"))
			{
				SQL = ServiceProviderSQL.GET_ACTIVE_LICENSE_NUMBER_AFTER_FEE_PAID;
			}*/
					dbUtil.createPreparedStatement(SQL);
					try
					{	
						
						spType=dbUtil.executeQuery(param);
				            logger.debug("-----> in ServiceProviderDAO - getLicenseDetails() after dbUtil.executeQuery(SQL)");
				           
				            spType.trimToSize();
			
		} catch (Exception x) {
			logger.debug(x);
			x.printStackTrace();
		} 
		
		finally {
			
			try {
				
				dbUtil.closeConnection();
			} 
			catch (Exception ex) {
				logger.debug(ex);
				ex.printStackTrace();
			}
		}
		
	}
		catch(Exception e){
			logger.debug(e);
		}
		return spType;
		
    	
    }
    
    public boolean renewLicenseNumber(ServiceProviderForm objServiceProviderForm) throws Exception
	{
		 
    	  boolean transactionflag = false;
    	  String renewLicense[]= new String[3];

    	  DBUtility dbUtil = null;
    	  try{
    		  dbUtil = new DBUtility();
    		  dbUtil.setAutoCommit(false);
    		  
    		  
    		  renewLicense[0] = (String) objServiceProviderForm.getObjServiceProviderDTO().getLicenseNumber();
    		  renewLicense[1] = (String) objServiceProviderForm.getObjServiceProviderDTO().getUid();
    		  renewLicense[2] = (String) objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
    		  
    		  String check = ServiceProviderSQL.CHECK_RENEW_LICENCE_NUMBER;
    		  dbUtil.createStatement();
    		  dbUtil.createPreparedStatement(check);
		 		String checkarr[] = new String[1];
		 		checkarr[0]=(String) objServiceProviderForm.getObjServiceProviderDTO().getLicenseNumber();;
		 		//checkarr[1]=(String) objServiceProviderForm.getObjServiceProviderDTO().getUid();
		 		String count = dbUtil.executeQry(checkarr);
		 		if(Integer.parseInt(count)==1)
		 		{
		 			dbUtil.createPreparedStatement(ServiceProviderSQL.UPDATE_LICENSE_NUMBER);
		 			dbUtil.executeUpdate(renewLicense);
		 			transactionflag = true;
		 			dbUtil.commit();				     
    		
		 		}
		 		else{
		 			throw new Exception(Constants.PDFEXCEPTION);
		 			}
		 		}
    	   catch(Exception e){
    		   e.printStackTrace();
    		   dbUtil.rollback();
    		logger.error(" Exception at serviceProviderDAO in DAO " + e);
    		throw e;
    		}
    	   
    	   finally {
    			 try{	    
    				 dbUtil.closeConnection();
    			 }
    			 catch (Exception e) {
    			 logger.error("Exception at serviceProviderDAO in DAO  " + e);
    			 }		
    	       }
    	   return transactionflag;   
    	 }
    
    public boolean updatePreviousStatusOfLicenseIssued(ServiceProviderForm objServiceProviderForm) throws Exception
	{
		 
    	  boolean transactionflag = false;
    	  String renewLicense[]= new String[3];

    	  DBUtility dbUtil = null;
    	  try{
    		  dbUtil = new DBUtility();
    		  dbUtil.setAutoCommit(false);
    		  
    		  
    		  renewLicense[0] = "D";
    		  renewLicense[1] = (String) objServiceProviderForm.getObjServiceProviderDTO().getUid();
    		  renewLicense[2] = (String) objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
    		  
    		  dbUtil.createPreparedStatement(ServiceProviderSQL.DEACTIVATE_PREVIOUS_ISSUED_LICENSE);
    		  dbUtil.executeUpdate(renewLicense);
    	      transactionflag = true;
    	      dbUtil.commit();				     
    		
    	 }
    	   catch(Exception e){
    		   e.printStackTrace();
    		   dbUtil.rollback();
    		logger.error(" Exception at serviceProviderDAO in DAO " + e);
    		throw e;
    		}
    	   
    	   finally {
    			 try{	    
    				 dbUtil.closeConnection();
    			 }
    			 catch (Exception e) {
    			 logger.error("Exception at serviceProviderDAO in DAO  " + e);
    			 }		
    	       }
    	   return transactionflag;   
    	 }
    
    public ArrayList getPdfDetlsForLicenseReprint(ServiceProviderForm objServiceProviderForm,String lang) throws Exception
    {

		ArrayList details = new ArrayList();
		
		String requestNo= (String)objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
		String officialsId=(String)objServiceProviderForm.getUserId();
		
		
		String[] param={requestNo,requestNo,officialsId,officialsId,requestNo,requestNo};
		
		try {
			dbUtil = new DBUtility();
			
					String SQL ="";
					if("en".equalsIgnoreCase(lang))
					{
						SQL=ServiceProviderSQL.GET_DETLS_FOR_PDF_OF_LICENSE_REPRINT;
					}
					else
					{
						SQL=ServiceProviderSQL.GET_DETLS_FOR_PDF_OF_LICENSE_REPRINT_H;
					}
					dbUtil.createPreparedStatement(SQL);
				
					
					try
					{	
						
							details=dbUtil.executeQuery(param);
				            logger.debug("-----> in ServiceProviderDAO - getPdfDetlsForLicenseReprint() after dbUtil.executeQuery(SQL)");
				           
				            details.trimToSize();
			
		} catch (Exception x) {
			logger.debug(x);
			x.printStackTrace();
		} 
		
		finally {
			
			try {
				
				dbUtil.closeConnection();
			} 
			catch (Exception ex) {
				logger.debug(ex);
				ex.printStackTrace();
			}
		}
		
	}
		catch(Exception e){
			logger.debug(e);
		}
		return details;
		
    	
    }
    
    public boolean insertAmount(ServiceProviderForm objServiceProviderForm) throws Exception
	{
		 
    	  boolean transactionflag = false;
    	  String insertAmountEstamp[]= new String[12];
    	  String insertAmountOthers[]= new String[12];
    	  String insertAmountEstampJudicial[]=new String[12]; // added by siddhartha
    	  String debtAmountOthers[] = new String[12];
    	  String debtAmountEstamp[] = new String[12];
    	  String debtAmountEstampJudicial[]=new String[12];  // added by siddhartha
    	  String srNo = "";
    	  String srNoOthers = "";
    	  String srNoJudicial="";   // added by siddhartha
    	  String srNoDebt = "";
    	  String srNoOthersDebt = "";
    	  String srNoJudicialDebt="";  // added by siddhartha
    	  String sequenceIdOthers = "";
    	  String sequenceIdOthers1="";
    	  String sequenceIdEstamp = "";
    	  String sequenceIdEstamp1="";
    	  String sequenceIdEstampJudicial=""; // added by siddhartha
    	  String sequenceIdEstampJudicial1="";
    	  String pymntIdOthers = "";
    	  String pymntIdOthers1="";
    	  String pymntIdEstamp = "";
    	  String pymntIdEstamp1 = "";
    	  String pymntIdEstampJudicial = ""; // added by siddhartha
    	  String pymntIdEstampJudicial1 = "";
    	  String moduleCnstnt = "SPTX";
    	  Date date = new Date();
   	   	  Format yearformat = new SimpleDateFormat("yyyy");
   	   	  Format monthformat = new SimpleDateFormat("MM");
   	   	  Format dateformat = new SimpleDateFormat("dd");
   	   	  String dfmt = dateformat.format(date);
   	   	  String yfmt = yearformat.format(date);
   	   	  String mfmt = monthformat.format(date);

    	  DBUtility dbUtil = null;
    	  try{
    		  dbUtil = new DBUtility();
    		  dbUtil.setAutoCommit(false);
    		  
    		  String bal[] = objServiceProviderForm.getObjServiceProviderDTO().getSelectedValuesOfCreditLimit().split(",");
    		  if ("No".equalsIgnoreCase(bal[1]) !=true)
    		  {
    			  	  dbUtil.createStatement();
    			  	  srNoOthers = dbUtil.executeQry(ServiceProviderSQL.SELECT_CRDT_LIMIT_TXN_SEQ);
    			  	  
    			  	  dbUtil.createStatement();
  			  	  	  sequenceIdOthers = dbUtil.executeQry(ServiceProviderSQL.SELECT_CRDT_PYMNT_OTHRS_SEQ);

  			  	  	  if(sequenceIdOthers.length()==1){
  			  	  		 sequenceIdOthers="0000"+sequenceIdOthers;  
  					  }else
  					  if(sequenceIdOthers.length()==2){
  						sequenceIdOthers="000"+sequenceIdOthers;  
  					  }else
  					  if(sequenceIdOthers.length()==3){
  						sequenceIdOthers="00"+sequenceIdOthers;  
  					  }else
  					  if(sequenceIdOthers.length()==4){
  						sequenceIdOthers="0"+sequenceIdOthers;  
  					  }
  			  	  	  
  			  	  	  //added by siddhartha
  			  	  	  
  			  	     dbUtil.createStatement();
			  	  	  sequenceIdOthers1 = dbUtil.executeQry(ServiceProviderSQL.SELECT_CRDT_PYMNT_OTHRS_SEQ);
  			  	  	  
			  	  	if(sequenceIdOthers1.length()==1){
			  	  	sequenceIdOthers1="0000"+sequenceIdOthers1;  
 					  }else
 					  if(sequenceIdOthers1.length()==2){
 						 sequenceIdOthers1="000"+sequenceIdOthers1;  
 					  }else
 					  if(sequenceIdOthers.length()==3){
 						 sequenceIdOthers1="00"+sequenceIdOthers1;  
 					  }else
 					  if(sequenceIdOthers1.length()==4){
 						 sequenceIdOthers1="0"+sequenceIdOthers1;  
 					  }
  			  	  	  
  			  	  	  
  			  	  	  
  			  	  	  
  			  	  	  pymntIdOthers = moduleCnstnt+dfmt+mfmt+yfmt+sequenceIdOthers;
  			  	      pymntIdOthers1 = moduleCnstnt+dfmt+mfmt+yfmt+sequenceIdOthers1;
  			  	  	  
    	    		  insertAmountOthers[0] = srNoOthers;
    	    		  insertAmountOthers[1] = (String) objServiceProviderForm.getObjServiceProviderDTO().getLicenseNumber();
    	    		  insertAmountOthers[2] = "5";
    	    		  insertAmountOthers[3] = "C";
    	    		  insertAmountOthers[4] = pymntIdOthers;
    	    		  insertAmountOthers[5] = (String) objServiceProviderForm.getObjServiceProviderDTO().getOthersBalanceCreditLimit();
    	    		  insertAmountOthers[6] = (String) objServiceProviderForm.getObjServiceProviderDTO().getOthersBalanceCreditLimit();
    	    		  insertAmountOthers[7] = (String) objServiceProviderForm.getObjServiceProviderDTO().getOthersBalanceCreditLimit();
    	    		  insertAmountOthers[8] = (String) objServiceProviderForm.getObjServiceProviderDTO().getUid();
    	    		  insertAmountOthers[9] = "C";
    	    		  insertAmountOthers[10] = "FUN_300";
    	    		  insertAmountOthers[11] = (String) objServiceProviderForm.getObjServiceProviderDTO().getUid();
    	    		  
    	    		  dbUtil.createPreparedStatement(ServiceProviderSQL.INSERT_TRANSFER_CREDIT_SP_ACNT_OTHRS);
    	    		  dbUtil.executeUpdate(insertAmountOthers);
    	    		  
    	    		  dbUtil.createStatement();
    			  	  srNoOthersDebt = dbUtil.executeQry(ServiceProviderSQL.SELECT_CRDT_LIMIT_TXN_SEQ);
    	    		  
    			  	  debtAmountOthers[0] = srNoOthersDebt;
    			  	  debtAmountOthers[1] = (String) objServiceProviderForm.getObjServiceProviderDTO().getPreviousLicenseNoOthers();
    			  	  debtAmountOthers[2] = "5";
    			  	  debtAmountOthers[3] = "D";
    			  	  debtAmountOthers[4] = pymntIdOthers1;
    			  	  debtAmountOthers[5] = (String) objServiceProviderForm.getObjServiceProviderDTO().getOthersBalanceCreditLimit();
    			  	  debtAmountOthers[6] = "0.00";
    			  	  debtAmountOthers[7] = (String) objServiceProviderForm.getObjServiceProviderDTO().getOthersBalanceCreditLimit();
    			  	  debtAmountOthers[8] = (String) objServiceProviderForm.getObjServiceProviderDTO().getUid();
    			  	  debtAmountOthers[9] = "C";
    			  	  debtAmountOthers[10] = "FUN_227";
    			  	  debtAmountOthers[11] = (String) objServiceProviderForm.getObjServiceProviderDTO().getUid();
    	    		  
    	    		  dbUtil.createPreparedStatement(ServiceProviderSQL.INSERT_TRANSFER_DEBT_SP_ACNT_OTHRS);
    	    		  dbUtil.executeUpdate(debtAmountOthers);
    	    	      transactionflag = true;
    	    	      dbUtil.commit();
    		  }
    		  
    		  if ("No".equalsIgnoreCase(bal[0]) !=true)
    		  {
    			  	  dbUtil.createStatement();
    	    		  srNo = dbUtil.executeQry(ServiceProviderSQL.CRDT_LIMIT_TXN_SEQ_ESTAMP);
    	    		  
    	    		  dbUtil.createStatement();
    	    		  sequenceIdEstamp = dbUtil.executeQry(ServiceProviderSQL.SELECT_CRDT_PYMNT_ESTAMP_SEQ);
    	    		  
    	    		  if(sequenceIdEstamp.length()==1){
    	    			  sequenceIdEstamp="0000"+sequenceIdEstamp;  
   					  }else
   					  if(sequenceIdEstamp.length()==2){
   						sequenceIdEstamp="000"+sequenceIdEstamp;  
   					  }else
   					  if(sequenceIdEstamp.length()==3){
   						sequenceIdEstamp="00"+sequenceIdEstamp;  
   					  }else
   					  if(sequenceIdEstamp.length()==4){
   						sequenceIdEstamp="0"+sequenceIdEstamp;  
   					  }
    	    		  
    	    		  
    	    		  dbUtil.createStatement();
    	    		  sequenceIdEstamp1 = dbUtil.executeQry(ServiceProviderSQL.SELECT_CRDT_PYMNT_ESTAMP_SEQ);
    	    		  
    	    		  if(sequenceIdEstamp1.length()==1){
    	    			  sequenceIdEstamp1="0000"+sequenceIdEstamp1;  
   					  }else
   					  if(sequenceIdEstamp1.length()==2){
   						sequenceIdEstamp1="000"+sequenceIdEstamp1;  
   					  }else
   					  if(sequenceIdEstamp1.length()==3){
   						sequenceIdEstamp1="00"+sequenceIdEstamp1;  
   					  }else
   					  if(sequenceIdEstamp1.length()==4){
   						sequenceIdEstamp1="0"+sequenceIdEstamp1;  
   					  }
    	    		  
    	    		  
    	    		  
  			  	  	  
  			  	  	  pymntIdEstamp = moduleCnstnt+dfmt+mfmt+yfmt+sequenceIdEstamp;
  			  	      pymntIdEstamp1 = moduleCnstnt+dfmt+mfmt+yfmt+sequenceIdEstamp1;
  			  	  	  
    	    		  insertAmountEstamp[0] = srNo;
    	    		  insertAmountEstamp[1] = (String) objServiceProviderForm.getObjServiceProviderDTO().getLicenseNumber();
    	    		  insertAmountEstamp[2] = "5";
    	    		  insertAmountEstamp[3] = "C";
    	    		  insertAmountEstamp[4] = pymntIdEstamp;
    	    		  insertAmountEstamp[5] = (String) objServiceProviderForm.getObjServiceProviderDTO().getEstampBalanceCreditLimit();
    	    		  insertAmountEstamp[6] = (String) objServiceProviderForm.getObjServiceProviderDTO().getEstampBalanceCreditLimit();
    	    		  insertAmountEstamp[7] = (String) objServiceProviderForm.getObjServiceProviderDTO().getEstampBalanceCreditLimit();
    	    		  insertAmountEstamp[8] = (String) objServiceProviderForm.getObjServiceProviderDTO().getUid();
    	    		  insertAmountEstamp[9] = "C";
    	    		  insertAmountEstamp[10] = "FUN_200";
    	    		  insertAmountEstamp[11] = (String) objServiceProviderForm.getObjServiceProviderDTO().getUid();
    	    		  
    	    		  dbUtil.createPreparedStatement(ServiceProviderSQL.INSERT_TRANSFER_CREDIT_SP_ACNT_ESTAMP);
    	    		  dbUtil.executeUpdate(insertAmountEstamp);
    	    		  
    	    		  dbUtil.createStatement();
    	    		  srNoDebt = dbUtil.executeQry(ServiceProviderSQL.CRDT_LIMIT_TXN_SEQ_ESTAMP);
    	    		  
    	    		  debtAmountEstamp[0] = srNoDebt;
    	    		  debtAmountEstamp[1] = (String) objServiceProviderForm.getObjServiceProviderDTO().getPreviousLicenseNoEstamp();
    	    		  debtAmountEstamp[2] = "5";
    	    		  debtAmountEstamp[3] = "D";
    	    		  debtAmountEstamp[4] = pymntIdEstamp1;
    	    		  debtAmountEstamp[5] = (String) objServiceProviderForm.getObjServiceProviderDTO().getEstampBalanceCreditLimit();
    	    		  debtAmountEstamp[6] = "0.00";
    	    		  debtAmountEstamp[7] = (String) objServiceProviderForm.getObjServiceProviderDTO().getEstampBalanceCreditLimit();
    	    		  debtAmountEstamp[8] = (String) objServiceProviderForm.getObjServiceProviderDTO().getUid();
    	    		  debtAmountEstamp[9] = "C";
    	    		  debtAmountEstamp[10] = "FUN_227";
    	    		  debtAmountEstamp[11] = (String) objServiceProviderForm.getObjServiceProviderDTO().getUid();
    	    		  
    	    		  dbUtil.createPreparedStatement(ServiceProviderSQL.INSERT_TRANSFER_DEBT_SP_ACNT_ESTAMP);
    	    		  dbUtil.executeUpdate(debtAmountEstamp);
    	    		  
    	    	      transactionflag = true;
    	    	      dbUtil.commit();
    	    	      
    	    	      
    		  }
    		//added by siddhartha  
    		  if("No".equalsIgnoreCase(bal[2])!=true){

			  	  dbUtil.createStatement();
			  	srNoJudicial = dbUtil.executeQry(ServiceProviderSQL.CRDT_LIMIT_TXN_SEQ_ESTAMP_JUDICIAL);
	    		  
	    		 dbUtil.createStatement();
	    		 
	    		 sequenceIdEstampJudicial = dbUtil.executeQry((ServiceProviderSQL.SELECT_CRDT_PYMNT_ESTAMP_SEQ_JUDICIAL));
	    		  
	    		  if(sequenceIdEstampJudicial.length()==1){
	    			  sequenceIdEstampJudicial="0000"+sequenceIdEstampJudicial;  
					  }else
					  if(sequenceIdEstampJudicial.length()==2){
						  sequenceIdEstampJudicial="000"+sequenceIdEstampJudicial;  
					  }else
					  if(sequenceIdEstampJudicial.length()==3){
						  sequenceIdEstampJudicial="00"+sequenceIdEstampJudicial;  
					  }else
					  if(sequenceIdEstampJudicial.length()==4){
						  sequenceIdEstampJudicial="0"+sequenceIdEstampJudicial;  
					  }
			  	  	  
	    		  
	    		  dbUtil.createStatement();
	    		  sequenceIdEstampJudicial1 = dbUtil.executeQry(ServiceProviderSQL.SELECT_CRDT_PYMNT_ESTAMP_SEQ_JUDICIAL);
	    		  
	    		  if(sequenceIdEstampJudicial1.length()==1){
	    			  sequenceIdEstampJudicial1="0000"+sequenceIdEstampJudicial1;  
					  }else
					  if(sequenceIdEstampJudicial1.length()==2){
						  sequenceIdEstampJudicial1="000"+sequenceIdEstampJudicial1;  
					  }else
					  if(sequenceIdEstampJudicial1.length()==3){
						  sequenceIdEstampJudicial1="00"+sequenceIdEstampJudicial1;  
					  }else
					  if(sequenceIdEstampJudicial1.length()==4){
						  sequenceIdEstampJudicial1="0"+sequenceIdEstampJudicial1;  
					  }
			  	  	  
	    		  
	    		  
			  	  	  pymntIdEstampJudicial = moduleCnstnt+dfmt+mfmt+yfmt+sequenceIdEstampJudicial;
			  	  	pymntIdEstampJudicial1 = moduleCnstnt+dfmt+mfmt+yfmt+sequenceIdEstampJudicial1;
			  	  	  
			  	insertAmountEstampJudicial[0] = srNoJudicial;
			  	insertAmountEstampJudicial[1] = (String) objServiceProviderForm.getObjServiceProviderDTO().getLicenseNumber();
			  	insertAmountEstampJudicial[2] = "5";
			  	insertAmountEstampJudicial[3] = "C";
			  	insertAmountEstampJudicial[4] = pymntIdEstampJudicial;
			  	insertAmountEstampJudicial[5] = (String) objServiceProviderForm.getObjServiceProviderDTO().getBalAmountCreditEstampJudicial();
			  	insertAmountEstampJudicial[6] = (String) objServiceProviderForm.getObjServiceProviderDTO().getBalAmountCreditEstampJudicial();
			  	insertAmountEstampJudicial[7] = (String) objServiceProviderForm.getObjServiceProviderDTO().getBalAmountCreditEstampJudicial();
	    		  insertAmountEstampJudicial[8] = (String) objServiceProviderForm.getObjServiceProviderDTO().getUid();
	    		  insertAmountEstampJudicial[9] = "C";
	    		  insertAmountEstampJudicial[10] = "FUN_200";
	    		  insertAmountEstampJudicial[11] = (String) objServiceProviderForm.getObjServiceProviderDTO().getUid();
	    		  
	    		  dbUtil.createPreparedStatement(ServiceProviderSQL.INSERT_TRANSFER_CREDIT_SP_ACNT_ESTAMP_JUDICIAL);
	    		  dbUtil.executeUpdate(insertAmountEstampJudicial);
	    		  
	    		  dbUtil.createStatement();
	    		  srNoJudicialDebt = dbUtil.executeQry(ServiceProviderSQL.CRDT_LIMIT_TXN_SEQ_ESTAMP_JUDICIAL);
	    		  
	    		  debtAmountEstampJudicial[0] = srNoJudicialDebt;
	    		  debtAmountEstampJudicial[1] = (String) objServiceProviderForm.getObjServiceProviderDTO().getPreviousLicenseNoEstamp();
	    		  debtAmountEstampJudicial[2] = "5";
	    		  debtAmountEstampJudicial[3] = "D";
	    		  debtAmountEstampJudicial[4] = pymntIdEstampJudicial1;
	    		  debtAmountEstampJudicial[5] = (String) objServiceProviderForm.getObjServiceProviderDTO().getBalAmountCreditEstampJudicial();
	    		  debtAmountEstampJudicial[6] = "0.00";
	    		  debtAmountEstampJudicial[7] = (String) objServiceProviderForm.getObjServiceProviderDTO().getBalAmountCreditEstampJudicial();
	    		  debtAmountEstampJudicial[8] = (String) objServiceProviderForm.getObjServiceProviderDTO().getUid();
	    		  debtAmountEstampJudicial[9] = "C";
	    		  debtAmountEstampJudicial[10] = "FUN_227";
	    		  debtAmountEstampJudicial[11] = (String) objServiceProviderForm.getObjServiceProviderDTO().getUid();
	    		  
	    		  dbUtil.createPreparedStatement(ServiceProviderSQL.INSERT_TRANSFER_DEBT_SP_ACNT_ESTAMP_JUDICIAL);
	    		  dbUtil.executeUpdate(debtAmountEstampJudicial);
	    		  
	    	      transactionflag = true;
	    	      dbUtil.commit();
	    	      
	    	      
		  
    			  
    			  
    			  
    			  
    		  }
    		  
    		  
    	 }
    	   catch(Exception e){
    		   e.printStackTrace();
    		   dbUtil.rollback();
    		logger.error(" Exception at serviceProviderDAO in DAO " + e);
    		throw e;
    		}
    	   
    	   finally {
    			 try{	    
    				 dbUtil.closeConnection();
    			 }
    			 catch (Exception e) {
    			 logger.error("Exception at serviceProviderDAO in DAO  " + e);
    			 }		
    	       }
    	   return transactionflag;   
    	 }
    
    public ArrayList getDetlsWhetherPreviousLicenseExprd(ServiceProviderForm objServiceProviderForm) throws Exception
    {

		ArrayList details = new ArrayList();
		
		String requestNo= (String)objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
		
		
		
		String[] param={requestNo};
		
		try {
			dbUtil = new DBUtility();
			
					String SQL = ServiceProviderSQL.CHK_DURING_RENEWAL_WHETHER_PRVS_LICENSE_EXPRD;
					dbUtil.createPreparedStatement(SQL);
				
					
					try
					{	
						
							details=dbUtil.executeQuery(param);
				            logger.debug("-----> in ServiceProviderDAO - getDetlsWhetherPreviousLicenseExprd() after dbUtil.executeQuery(SQL)");
				           
				            details.trimToSize();
			
		} catch (Exception x) {
			logger.debug(x);
			x.printStackTrace();
		} 
		
		finally {
			
			try {
				
				dbUtil.closeConnection();
			} 
			catch (Exception ex) {
				logger.debug(ex);
				ex.printStackTrace();
			}
		}
		
	}
		catch(Exception e){
			logger.debug(e);
		}
		return details;
		
    	
    }
    
    public  boolean updateExprdStatus (ServiceProviderForm objServiceProviderForm) throws Exception{
		 
    	boolean transactionflag = false;
  	  	String updateAppStatus[]= new String[4];
  	  	String updateRightsAssigned[] = new String[1];

  	  DBUtility dbUtil = null;
  	  try{
  		  dbUtil = new DBUtility();
  		  dbUtil.setAutoCommit(false);

  		  updateAppStatus[0] = "12";
  		  updateAppStatus[1] = (String) objServiceProviderForm.getObjServiceProviderDTO().getOfficeId();
  		  updateAppStatus[2] = (String) objServiceProviderForm.getObjServiceProviderDTO().getUid();
  		  updateAppStatus[3] =  (String)objServiceProviderForm.getObjServiceProviderDTO().getLicenseNumber(); 		  
  		
  		  
  		  updateRightsAssigned[0] = (String)objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
  		 
  		  dbUtil.createPreparedStatement(ServiceProviderSQL.UPDATE_APPLCTN_STATUS_EXPRD_CASE);
  		  dbUtil.executeUpdate(updateAppStatus);
  		  
  		  dbUtil.createPreparedStatement(ServiceProviderSQL.REVOKE_RIGHTS_ON_CANCELLATION);
		  dbUtil.executeUpdate(updateRightsAssigned);
		  
		  dbUtil.createPreparedStatement(ServiceProviderSQL.REVOKE_USER_TYPE_ON_CANCELLATION);
		  dbUtil.executeUpdate(updateRightsAssigned);
  		  
  	      transactionflag = true;
  	      dbUtil.commit();				     
  		
  	 }
  	   catch(Exception e){
  		   e.printStackTrace();
  		   dbUtil.rollback();
  		logger.error(" Exception at serviceProviderDAO in DAO " + e);
  		throw e;
  		}
  	   
  	   finally {
  			 try{	    
  				 dbUtil.closeConnection();
  			 }
  			 catch (Exception e) {
  			 logger.error("Exception at serviceProviderDAO in DAO  " + e);
  			 }		
  	       }
  	   return transactionflag;   
  	 }
    
    
    
    public ArrayList getPlaceDetls(ServiceProviderForm objServiceProviderForm) throws Exception
    {

		ArrayList details = new ArrayList();
		
		String requestNo= (String)objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
		
		
		
		String[] param={requestNo};
		
		try {
			dbUtil = new DBUtility();
			
					String SQL = ServiceProviderSQL.GET_PLACE_ID_DETLS;
					dbUtil.createPreparedStatement(SQL);
				
					
					try
					{	
						
							details=dbUtil.executeQuery(param);
				            logger.debug("-----> in ServiceProviderDAO - getPlaceDetls() after dbUtil.executeQuery(SQL)");
				           
				            details.trimToSize();
			
		} catch (Exception x) {
			logger.debug(x);
			x.printStackTrace();
		} 
		
		finally {
			
			try {
				
				dbUtil.closeConnection();
			} 
			catch (Exception ex) {
				logger.debug(ex);
				ex.printStackTrace();
			}
		}
		
	}
		catch(Exception e){
			logger.debug(e);
		}
		return details;
		
    	
    }
    
    public ArrayList getPreviousLicenseDetailsEstamp1(ServiceProviderForm objServiceProviderForm) throws Exception
    {

		ArrayList details = new ArrayList();
		
		String licenseNumber= (String)objServiceProviderForm.getObjServiceProviderDTO().getLicenseNumber();
		
		
		
		String[] param={licenseNumber,licenseNumber,licenseNumber,licenseNumber};
		
		try {
			dbUtil = new DBUtility();
			
					String SQL = ServiceProviderSQL.GET_SP_PREVIOUS_LICENSE_ESTAMP_AMOUNT_DETLS1;
					dbUtil.createPreparedStatement(SQL);
				
					
					try
					{	
						
							details=dbUtil.executeQuery(param);
				            logger.debug("-----> in ServiceProviderDAO - getPaymentDetls() after dbUtil.executeQuery(SQL)");
				           
				            details.trimToSize();
			
		} catch (Exception x) {
			logger.debug(x);
			x.printStackTrace();
		} 
		
		finally {
			
			try {
				
				dbUtil.closeConnection();
			} 
			catch (Exception ex) {
				logger.debug(ex);
				ex.printStackTrace();
			}
		}
		
	}
		catch(Exception e){
			logger.debug(e);
		}
		return details;
		
    	
    }
    
    public ArrayList getPreviousLicenseDetailsOthers1(ServiceProviderForm objServiceProviderForm) throws Exception
    {

		ArrayList details = new ArrayList();
		
		String licenseNumber= (String)objServiceProviderForm.getObjServiceProviderDTO().getLicenseNumber();
		
		
		
		String[] param={licenseNumber,licenseNumber,licenseNumber,licenseNumber};
		
		try {
			dbUtil = new DBUtility();
			
					String SQL = ServiceProviderSQL.GET_SP_PREVIOUS_LICENSE_OTHERS_AMOUNT_DETLS1;
					dbUtil.createPreparedStatement(SQL);
				
					
					try
					{	
						
							details=dbUtil.executeQuery(param);
				            logger.debug("-----> in ServiceProviderDAO - getPaymentDetls() after dbUtil.executeQuery(SQL)");
				           
				            details.trimToSize();
			
		} catch (Exception x) {
			logger.debug(x);
			x.printStackTrace();
		} 
		
		finally {
			
			try {
				
				dbUtil.closeConnection();
			} 
			catch (Exception ex) {
				logger.debug(ex);
				ex.printStackTrace();
			}
		}
		
	}
		catch(Exception e){
			logger.debug(e);
		}
		return details;
		
    	
    }
    
    
    //added by shruti------ 5 march 2014
    public String insertEmailData(String userId, String subject, String content)
	throws Exception {
    	
    String status = new String();
     try {
    	 dbUtility = new DBUtility();
    	 dbUtility.createCallableStatement(ServiceProviderSQL.CALL_IGRS_INSERT_EMAIL_DATA);
	     status = dbUtility.insertEmailData(userId, subject, content);
	     System.out.println("STATUS --->");
	     System.out.println("STATUS --->" + status);
     } catch (Exception x) {
	logger.debug(x);
	x.printStackTrace();
     } finally {
    	 dbUtility.closeConnection();
     }
    return status;
}
    
    //added by shruti--30 july 2014
    public String insertSMSData(String userId, String mobileno, String content)
	throws Exception {
    	
    String status = new String();
    String[] param={mobileno,userId,content};
     try {
    	 dbUtility = new DBUtility();
    	 dbUtility.createPreparedStatement(ServiceProviderSQL.INSERT_SMS_CONTENT);
    	 dbUtility.executeUpdate(param);
    	 //dbUtility.executeQuery(param);
	   
     } catch (Exception x) {
	logger.debug(x);
	x.printStackTrace();
     } finally {
    	 dbUtility.closeConnection();
     }
    return status;
}
    //end
    
    public String getTehsilId(ServiceProviderForm objServiceProviderForm)
	{
		String reqNo = (String)objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
		String tehsilId=null;
		
		
		
		String[] param={reqNo};
		
		try {
			dbUtil = new DBUtility();
			
					String SQL = ServiceProviderSQL.GET_TEHSIL_ID_SP;
					dbUtil.createPreparedStatement(SQL);
				
					
					try
					{	
						
						tehsilId=dbUtil.executeQry(param);
				            logger.debug("-----> in ServiceProviderDAO - getTehsilId() after dbUtil.executeUpdate(param)");
			
		} catch (Exception x) {
			logger.debug(x);
			x.printStackTrace();
		} 
		
		finally {
			
			try {
				
				dbUtil.closeConnection();
			} 
			catch (Exception ex) {
				logger.debug(ex);
				ex.printStackTrace();
			}
		}
		
	}
		catch(Exception e){
			logger.debug(e);
		}
		return tehsilId;
		}  
    
    public String getWardId(ServiceProviderForm objServiceProviderForm,String subareaId,String tehsilId)
	{
		String reqNo = (String)objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
		//String subareaId=(String)objServiceProviderForm.getObjServiceProviderDTO().getSubAreaId();
		//String tehsilId=(String)objServiceProviderForm.getObjServiceProviderDTO().getTehsil();
		String wardId=null;
		
		String[] param={subareaId,tehsilId,reqNo};
		try {
					dbUtil = new DBUtility();
					String SQL = ServiceProviderSQL.GET_WARD_ID_SP;
					dbUtil.createPreparedStatement(SQL);
					
					try
					{	
							wardId=dbUtil.executeQry(param);
				            logger.debug("-----> in ServiceProviderDAO - getwardId() after dbUtil.executeUpdate(param)");
			
		} catch (Exception x) {
			logger.debug(x);
			x.printStackTrace();
		} 
		
		finally {
			
			try {
				
				dbUtil.closeConnection();
			} 
			catch (Exception ex) {
				logger.debug(ex);
				ex.printStackTrace();
			}
		}
		
	}
		catch(Exception e){
			logger.debug(e);
		}
		return wardId;
		}  
    public String getMohallaId(ServiceProviderForm objServiceProviderForm,String mappingId)
	{
		String reqNo = (String)objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
		
		String mohallaId=null;

		String[] param={mappingId,reqNo};
		
		try {
					dbUtil = new DBUtility();
					String SQL = ServiceProviderSQL.GET_MOHALLA_ID_SP;
					dbUtil.createPreparedStatement(SQL);
					try
					{	
						
							mohallaId=dbUtil.executeQry(param);
				            logger.debug("-----> in ServiceProviderDAO - getMohallaId() after dbUtil.executeUpdate(param)");
			
		} catch (Exception x) {
			logger.debug(x);
			x.printStackTrace();
		} 
		
		finally {
			
			try {
				
				dbUtil.closeConnection();
			} 
			catch (Exception ex) {
				logger.debug(ex);
				ex.printStackTrace();
			}
		}
		
	}
		catch(Exception e){
			logger.debug(e);
		}
		return mohallaId;
		}  
    
    public String getAreaId(ServiceProviderForm objServiceProviderForm)
	{
		String reqNo = (String)objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
		String mohallaId=null;
		
		
		
		String[] param={reqNo};
		
		try {
			dbUtil = new DBUtility();
			
					String SQL = ServiceProviderSQL.GET_AREA_ID_SP;
					dbUtil.createPreparedStatement(SQL);
				
					
					try
					{	
						
						mohallaId=dbUtil.executeQry(param);
				            logger.debug("-----> in ServiceProviderDAO - getMohallaId() after dbUtil.executeUpdate(param)");
			
		} catch (Exception x) {
			logger.debug(x);
			x.printStackTrace();
		} 
		
		finally {
			
			try {
				
				dbUtil.closeConnection();
			} 
			catch (Exception ex) {
				logger.debug(ex);
				ex.printStackTrace();
			}
		}
		
	}
		catch(Exception e){
			logger.debug(e);
		}
		return mohallaId;
		}  
    
    public String getSubAreaId(ServiceProviderForm objServiceProviderForm)
	{
		String reqNo = (String)objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
		String mohallaId=null;
		
		
		
		String[] param={reqNo};
		
		try {
			dbUtil = new DBUtility();
			
					String SQL = ServiceProviderSQL.GET_SUB_AREA_ID_SP;
					dbUtil.createPreparedStatement(SQL);
				
					
					try
					{	
						
						mohallaId=dbUtil.executeQry(param);
				            logger.debug("-----> in ServiceProviderDAO - getMohallaId() after dbUtil.executeUpdate(param)");
			
		} catch (Exception x) {
			logger.debug(x);
			x.printStackTrace();
		} 
		
		finally {
			
			try {
				
				dbUtil.closeConnection();
			} 
			catch (Exception ex) {
				logger.debug(ex);
				ex.printStackTrace();
			}
		}
		
	}
		catch(Exception e){
			logger.debug(e);
		}
		return mohallaId;
		}  
    
    
    //added by shruti---21 july 2014
    public ArrayList getArea(String language) {
		ArrayList areaList=null;
		try {
			dbUtil= new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			dbUtil.createStatement();
			if(language.equalsIgnoreCase("en"))
			{	
				areaList=dbUtil.executeQuery(ServiceProviderSQL.AREA_NAME_EN);
			}
			else
			{
				areaList=dbUtil.executeQuery(ServiceProviderSQL.AREA_NAME_HI);
			}
			
			return areaList;
			
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
		finally
		{
			try
			{
				dbUtil.closeConnection();
			}
			catch(Exception e)
			{
				logger.error(e);

			}
			
		}
	}
    //end
    
    public ArrayList getSubArea(String language, String areaId, String tehsilId, String urbanFlag) {
		ArrayList subAreaList=null;
		try {
			dbUtil= new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	try {
		if(language.equalsIgnoreCase("en"))
		{	
			if("Y".equalsIgnoreCase(urbanFlag))
			{	
			dbUtil.createPreparedStatement(ServiceProviderSQL.SUB_AREA_NAME_EN_UR);
			subAreaList=dbUtil.executeQuery(new String[]{areaId,tehsilId});

			}
			else
			{
				dbUtil.createPreparedStatement(ServiceProviderSQL.SUB_AREA_NAME_EN);	
				subAreaList=dbUtil.executeQuery(new String[]{areaId});
			}
		}
		else
		{
			if("Y".equalsIgnoreCase(urbanFlag))
			{	
			dbUtil.createPreparedStatement(ServiceProviderSQL.SUB_AREA_NAME_HI_UR);
			subAreaList=dbUtil.executeQuery(new String[]{areaId,tehsilId});
			}
			else
			{
				dbUtil.createPreparedStatement(ServiceProviderSQL.SUB_AREA_NAME_HI);	
				subAreaList=dbUtil.executeQuery(new String[]{areaId});

			}
		}
		
		return subAreaList;
		
	} catch (Exception e) {
		logger.error(e);
		return null;
	}
	finally
	{
		try
		{
			dbUtil.closeConnection();
		}
		catch(Exception e)
		{
			logger.error(e);

		}
		
	}
	
}
    
    public ArrayList getWardPatwari(String language, String subAreaId,
			String tehsilId) {
			ArrayList wardPatwariList=null;
			try {
				dbUtil= new DBUtility();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		try {
			if(language.equalsIgnoreCase("en"))
			{	
				dbUtil.createPreparedStatement(ServiceProviderSQL.WARD_PATWARI_NAME_EN);
			}
			else
			{
				dbUtil.createPreparedStatement(ServiceProviderSQL.WARD_PATWARI_NAME_HI);	
			}
			wardPatwariList=dbUtil.executeQuery(new String[]{subAreaId,tehsilId});
			return wardPatwariList;
			
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
		finally
		{
			try
			{
				dbUtil.closeConnection();
			}
			catch(Exception e)
			{
				logger.error(e);

			}
			
		}
	}
    
    
    
    public ArrayList getColonyName(String language, String wardPatwariId) {
		ArrayList l2NameList=null;
		try {
			dbUtil= new DBUtility();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			if(language.equalsIgnoreCase("en"))
			{	
				dbUtil.createPreparedStatement(ServiceProviderSQL.COLONY_NAME_EN);
			}
			else
			{
				dbUtil.createPreparedStatement(ServiceProviderSQL.COLONY_NAME_HI);	
			}
			l2NameList=dbUtil.executeQuery(new String[]{wardPatwariId.split("~")[1]});
			
			/*if(wardPatwariId.split("~").length>1)
			{	
			l2NameList=dbUtil.executeQuery(new String[]{wardPatwariId.split("~")[1]});
			}
			else
			{
				l2NameList=dbUtil.executeQuery(new String[]{wardPatwariId.split("~")[0]});
			}*/
			return l2NameList;
			
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
		finally
		{
			try
			{
				dbUtil.closeConnection();
			}
			catch(Exception e)
			{
				logger.error(e);

			}
			
		}
	}

	public ArrayList getPreviousLicenseDetailsEstampJudicial1(
			ServiceProviderForm objServiceProviderForm) throws Exception{

		ArrayList details = new ArrayList();
		
		String licenseNumber= (String)objServiceProviderForm.getObjServiceProviderDTO().getLicenseNumber();
		
		
		
		String[] param={licenseNumber,licenseNumber,licenseNumber,licenseNumber};
		
		try {
			dbUtil = new DBUtility();
			
					String SQL = ServiceProviderSQL.GET_SP_PREVIOUS_LICENSE_ESTAMP_JUDICIAL_AMOUNT_DETLS1;
					dbUtil.createPreparedStatement(SQL);
				
					
					try
					{	
						
							details=dbUtil.executeQuery(param);
				            logger.debug("-----> in ServiceProviderDAO - getPaymentDetls() after dbUtil.executeQuery(SQL)");
				           
				            details.trimToSize();
			
		} catch (Exception x) {
			logger.debug(x);
			x.printStackTrace();
		} 
		
		finally {
			
			try {
				
				dbUtil.closeConnection();
			} 
			catch (Exception ex) {
				logger.debug(ex);
				ex.printStackTrace();
			}
		}
		
	}
		catch(Exception e){
			logger.debug(e);
		}
		return details;
		
    	
    }

	public boolean getRequestNumber(ServiceProviderForm objServiceProviderForm) {
		// TODO Auto-generated method stub
		
		String reqNo = (String)objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
		boolean flag=false;
		String check="";
		String[] param={reqNo};
		try {
			dbUtil = new DBUtility();
			String SQL = ServiceProviderSQL.GET_REQUEST_NO;
			dbUtil.createPreparedStatement(SQL);
			check=dbUtil.executeQry(param);
			if(!check.equalsIgnoreCase("")){
				flag=true;
			}	
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}

	public String getNewLicenseNumber(ServiceProviderForm objServiceProviderForm) {
		// TODO Auto-generated method stub
		
		
		String reqNo = (String)objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
		String newLicenseNo=null;
		String[] param={reqNo};
		try{
			dbUtil = new DBUtility();
			String SQL=ServiceProviderSQL.GET_NEW_LICENSE_NO;
			dbUtil.createPreparedStatement(SQL);
			newLicenseNo=dbUtil.executeQry(param);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return newLicenseNo;
	}
	
	 public  boolean insertSpOldDocDetls (ServiceProviderForm objServiceProviderForm) throws Exception{
		boolean transactionflag = false;
		String spNewRequestNo = (String) objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
		 String spOldRequestNo = (String) objServiceProviderForm.getObjServiceProviderDTO().getRequestNo();
	  	  String spOldRequestNo1 [] = {spOldRequestNo};
	  	  String spNewRequestNo1 [] = {spNewRequestNo};
	  	  String docTbl[]= new String[6];
	  	  ArrayList oldReqList = null;
	  	  ArrayList oldReqList1 = null;
	  	  ArrayList newReqList = null;
	  	  ArrayList newReqList1 = null;

	  	  DBUtility dbUtil = null;
	     
	  	  try{
	  		  dbUtil = new DBUtility();
	  		  dbUtil.setAutoCommit(false);
	  		  
	  		  //changes to show the old documents on DR side by santosh
	  		   String SQL = ServiceProviderSQL.GET_SP_DOC_DETLS_FOR_DR;
			   dbUtil.createPreparedStatement(SQL);
			   newReqList = dbUtil.executeQuery(spNewRequestNo1);
			   oldReqList = dbUtil.executeQuery(spOldRequestNo1);
			   
			   if(newReqList.size()>0 ){
				   String newDocName = null;
				   String oldDocName = null;
				   for(int i=0; i<newReqList.size(); i++){
					   newReqList1 = (ArrayList) newReqList.get(i);
					    newDocName = (String) newReqList1.get(0);
					 if(oldReqList.size()>0){
					  for(int j=0; j<oldReqList.size(); j++){
						 oldReqList1 = (ArrayList)oldReqList.get(j);
						 oldDocName = (String)oldReqList1.get(0);
						 if(newDocName.equalsIgnoreCase(oldDocName)){
							 oldReqList.remove(j);
						 }
						 if(oldDocName.contains("PanCard") || oldDocName.contains("Form60_61") 
								 || oldDocName.contains("Signature_Licensing_Authority")){
							 oldReqList.remove(j);
						 }
					 }   
				   }  
			   }
			   }
			   if(oldReqList.size()!=0 && oldReqList.size()>0){
				   
			    for(int i=0; i<oldReqList.size(); i++){
	  		  //String SQL1 = "select IGRS_SP_DOC_ID_SEQ.nextval from dual";
			      oldReqList1 = (ArrayList) oldReqList.get(i);
		  		  dbUtil.createStatement();
		  		  String number3 = dbUtil.executeQry(ServiceProviderSQL.GET_SEQ_DOCUMENT_UPLOAD_ID);
		  		  docTbl[0]=number3;
		  		  docTbl[1]=objServiceProviderForm.getObjServiceProviderDTO().getReqNo();
		  		  docTbl[2]=(String) oldReqList1.get(0);
		  		  docTbl[3]=(String) oldReqList1.get(1);
		  		  docTbl[4]="A";
		  		  docTbl[5]=(String)objServiceProviderForm.getObjServiceProviderDTO().getUid();
		  		  
		  		  dbUtil.createPreparedStatement(ServiceProviderSQL.INSERT_DOCUMENT_DETLS);
		  		  transactionflag = dbUtil.executeUpdate(docTbl);
		  	  
	  	          dbUtil.commit();	
				  
			   }
			   }
			 
	  	 }
	  	   catch(Exception e){
	  		   e.printStackTrace();
	  		   dbUtil.rollback();
	  		logger.error(" Exception in fun insertSpOldDocDetls serviceProviderDAO in DAO " + e);
	  		//throw e;
	  		}
	  	   
	  	   finally {
	  			 try{	    
	  				 dbUtil.closeConnection();
	  			 }
	  			 catch (Exception e) {
	  			 logger.error("Exception in fun insertSpOldDocDetls serviceProviderDAO in DAO  " + e);
	  			 }		
	  	       }
	  	   return transactionflag;   
	  	 }
	 
}



