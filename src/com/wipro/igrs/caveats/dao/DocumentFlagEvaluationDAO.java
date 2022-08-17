package com.wipro.igrs.caveats.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.newgen.burning.Party;
import com.wipro.igrs.Seals.sql.SealsSQL;
import com.wipro.igrs.caveats.form.DocumentFlagEvaluationForm;
import com.wipro.igrs.caveats.sql.CommonSQL;
import com.wipro.igrs.caveats.sql.DocumentFlagEvaluationSQL;
import com.wipro.igrs.db.DBUtility;

public class DocumentFlagEvaluationDAO {
	
	private Logger logger = Logger.getLogger(DocumentFlagEvaluationDAO.class);
	String sql = null;
	
	 public String getLangauge(String txnId) {

		 	String[] tid = new String[1];
			tid[0]=txnId;
			String dty="";
			try{
			DBUtility transmgtUtil = new DBUtility();
			transmgtUtil.createPreparedStatement(DocumentFlagEvaluationSQL.GET_LANGUAGE);
			dty=transmgtUtil.executeQry(tid);
			transmgtUtil.closeConnection();
			}
			catch(Exception e)
			{
				logger.debug(e.getStackTrace());
			}
			return dty;
		}
	 
	 public String getRegistartionDateInit(String regInitId)
	 {
		 
		 
		 String peanlity="";
		 DBUtility dbUtility=null;
		 try{
			 	dbUtility=new DBUtility();
			 	String query=DocumentFlagEvaluationSQL.GET_REG_DATE_INIT;
			 	dbUtility.createPreparedStatement(query);
			 	peanlity=dbUtility.executeQry(new String[]{regInitId.trim()});
			 	dbUtility.closeConnection();
		 	} catch(Exception e)
		 	{
		 		logger.debug(e.getMessage(),e);
		 	}
		 	finally
			{
				try
				{
					dbUtility.closeConnection();
				}catch(Exception e)
				{
					e.printStackTrace();
					logger.debug("error in close connection getInstd of ragVompMkrDAO"+e.getStackTrace());
					
				}
			}
		 return peanlity;	 
	 }
	 
	 public String getConsiderAmount(String regInitId)
	 {
		 String considerAmount="--";
		 DBUtility dbUtility=null;
		 try
			{
				dbUtility = new DBUtility();
				String regArr[] = {regInitId};
				sql = DocumentFlagEvaluationSQL.GET_CONSIDER_AMOUNT;
				dbUtility.createPreparedStatement(sql);
				//logger.debug(sql);
				considerAmount = dbUtility.executeQry(regArr);
				if(considerAmount==null||"".equalsIgnoreCase(considerAmount))
				{
					considerAmount="NA";
				}
				else
				{
					if(considerAmount.contains("."))
					{
					if((considerAmount.substring(considerAmount.indexOf("."))).length()>2)
					{	
						considerAmount=considerAmount.substring(0,(considerAmount.indexOf(".")+3));	
					}
					}
					
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
				logger.debug("error in get considerAmt of DocumentFlagEvaluationDAO "+e.getStackTrace());
			}
			finally
			{
				try
				{
					dbUtility.closeConnection();
				}catch(Exception e)
				{
					e.printStackTrace();
					logger.debug("error in close connection getconsiderAmt of DocumentFlagEvaluationDAO "+e.getStackTrace());
					
				}
			}
			return considerAmount;
	 }
	 
	 public List getRegDetails(String regNo) throws Exception{
			String status, regId = null;
			DBUtility dbUtility = null;
			ArrayList list= null;
			try {
				dbUtility = new DBUtility();
				/*dbUtility.createStatement();
				String query=RegCompCheckerSQL.CHECK_STATUS+"'"+regInitId+"'";
				status = dbUtility.executeQry(query);*/
				String query=DocumentFlagEvaluationSQL.CHECK_STATUS;
				dbUtility.createPreparedStatement(query);
				//status = dbUtility.executeQry(new String[]{regInitId});
				list=dbUtility.executeQuery(new String[]{regNo});
				
			} catch (Exception exception) {
				logger.debug("Exception in getSeqNum" + exception);
			}
			finally
			{
				try
				{
					dbUtility.closeConnection();
				}
				catch(Exception e)
				{
					logger.error("error in close connection updtStatus"+e.getStackTrace());
				}
				
			}
			
			return list;
			
		}
	 
	 public String  getCompletionNumber(String regInitId) throws Exception{
			String compNumber = null;
			DBUtility dbUtility = null;
			try {
				dbUtility = new DBUtility();
				/*dbUtility.createStatement();
				String query=RegCompCheckerSQL.GET_CMPLETION_NUMBER+"'"+regInitId+"'";
				compNumber = dbUtility.executeQry(query);*/
				String query=DocumentFlagEvaluationSQL.GET_CMPLETION_NUMBER;
				dbUtility.createPreparedStatement(query);
				compNumber = dbUtility.executeQry(new String[]{regInitId});
			} catch (Exception exception) {
				logger.debug("Exception in getSeqNum" + exception);
			}
			finally
			{
				try
				{
					dbUtility.closeConnection();
				}
				catch(Exception e)
				{
					logger.error("error in close connection updtStatus"+e.getStackTrace());
				}
				
			}
			
			return compNumber;
		}
	 
	 public String checkFinalDocGenFlag(String regInitId) throws Exception{
			String status = null;
			DBUtility dbUtility=null;
			try {
				dbUtility = new DBUtility();
				/*dbUtility.createStatement();
				String query=RegCompCheckerSQL.CHECK_STATUS+"'"+regInitId+"'";
				status = dbUtility.executeQry(query);*/
				String query=DocumentFlagEvaluationSQL.CHECK_FINAL_DOC_GEN_FLAG;
				dbUtility.createPreparedStatement(query);
				status = dbUtility.executeQry(new String[]{regInitId});
				
			} catch (Exception exception) {
				logger.debug("Exception in checkFinalDocGenFlag" + exception);
			}
			finally
			{
				try
				{
					dbUtility.closeConnection();
				}
				catch(Exception e)
				{
					logger.error("error in close connection checkFinalDocGenFlag"+e.getStackTrace());
				}
				
			}
			
			return status;
			
		}
	 
	 public String getUsrId(String regNo)
	 {
	 	String districtId = null;
	 	DBUtility dbUtility = null;
	 	try {
	 		dbUtility = new DBUtility();
	 		/*dbUtility.createStatement();
	 		String query=RegCompCheckerSQL.GET_DISTRICT_ID+"'"+officeId+"'";
	 		districtId = dbUtility.executeQry(query);*/
	 		String query=DocumentFlagEvaluationSQL.GET_USER_COMP;
	 		dbUtility.createPreparedStatement(query);
	 		districtId = dbUtility.executeQry(new String[]{regNo});
	 		
	 	} catch (Exception exception) {
	 		logger.debug("Exception in getDistrictCodeChecker" + exception);
	 	}
	 	finally
	 	{
	 		try
	 		{
	 			dbUtility.closeConnection();
	 		}
	 		catch(Exception e)
	 		{
	 			logger.error("error in close connection updtStatus"+e.getStackTrace());
	 		}
	 		
	 	}
	 	
	 	return districtId;
	 }
	 
	 public String checkNullAndVoid(String regId)
	 {
	 	String nullVoidFlag= "";
	 	DBUtility dbUtility=null;
	 	try {
	 		dbUtility = new DBUtility();
	 		String query=DocumentFlagEvaluationSQL.GET_NULL_AND_VOID_FLAG;
	 		dbUtility.createPreparedStatement(query);
	 		String arr[] = {regId};
	 		logger.debug("qry"+query);
	 		nullVoidFlag = dbUtility.executeQry(arr);
	 	} catch (Exception exception) {
	 		logger.debug("Exception in pinDetails" + exception);
	 	}
	 	finally
	 	{
	 		try
	 		{
	 			dbUtility.closeConnection();
	 		}
	 		catch(Exception e)
	 		{
	 			logger.error("error in close connection pinDetails"+e.getStackTrace());
	 		}
	 		
	 	}
	 	if("T".equalsIgnoreCase(nullVoidFlag))
	 	{
	 		return "true";
	 	}
	 	else
	 	{
	 		return "false";
	 	}
	 }
	 
	 public boolean checkRegNumber(String regNumber)
	 {
	 	String dregNumber = "";
	 	DBUtility dbUtility=null;
	 	try {
	 		dbUtility = new DBUtility();
	 		String query=DocumentFlagEvaluationSQL.CHECK_REG_NUMBER;
	 		dbUtility.createPreparedStatement(query);
	 		String arr[] = {regNumber};
	 		logger.debug("qry"+query);
	 		dregNumber = dbUtility.executeQry(arr);
	 	} catch (Exception exception) {
	 		logger.debug("Exception in pinDetails" + exception);
	 	}
	 	finally
	 	{
	 		try
	 		{
	 			dbUtility.closeConnection();
	 		}
	 		catch(Exception e)
	 		{
	 			logger.error("error in close connection pinDetails"+e.getStackTrace());
	 		}
	 		
	 	}
	 	if(regNumber.equalsIgnoreCase(dregNumber))
	 	{
	 		return true;
	 	}
	 	else
	 	{
	 		return false;
	 	}
	 }
	 
	 public String getOfcId(String regNo)
	 {
	 	String districtId = null;
	 	DBUtility dbUtility = null;
	 	try {
	 		dbUtility = new DBUtility();
	 		/*dbUtility.createStatement();
	 		String query=RegCompCheckerSQL.GET_DISTRICT_ID+"'"+officeId+"'";
	 		districtId = dbUtility.executeQry(query);*/
	 		String query=DocumentFlagEvaluationSQL.GET_OFFICE_COMP;
	 		dbUtility.createPreparedStatement(query);
	 		districtId = dbUtility.executeQry(new String[]{regNo});
	 		
	 	} catch (Exception exception) {
	 		logger.debug("Exception in getDistrictCodeChecker" + exception);
	 	}
	 	finally
	 	{
	 		try
	 		{
	 			dbUtility.closeConnection();
	 		}
	 		catch(Exception e)
	 		{
	 			logger.error("error in close connection updtStatus"+e.getStackTrace());
	 		}
	 		
	 	}
	 	
	 	return districtId;
	 }
	 
	 public ArrayList getPartyDetailsRegCertificate(String regInitNo,String langauge )
	 {
		 ArrayList returnList=new ArrayList();
		 DBUtility dbUtility=null;
		 try
			{
				dbUtility = new DBUtility();
				sql = DocumentFlagEvaluationSQL.GET_COMMON_FLAG;
				dbUtility.createPreparedStatement(sql);
				String arr[]=new String[]{regInitNo};
				String commonFlag = dbUtility.executeQry(arr);
				if("en".equalsIgnoreCase(langauge))
				{	
					if("Y".equalsIgnoreCase(langauge))
					{
						sql=DocumentFlagEvaluationSQL.GET_PARTY_DETAILS_CER_COMMOMN;
					}
					else
					{
						sql=DocumentFlagEvaluationSQL.GET_PARTY_DETAILS_CER_OTHER;
					}
				}
				else
				{
					if("Y".equalsIgnoreCase(langauge))
					{
						sql=DocumentFlagEvaluationSQL.GET_PARTY_DETAILS_CER_COMMOMN_HI;
					}
					else
					{
						sql=DocumentFlagEvaluationSQL.GET_PARTY_DETAILS_CER_OTHER_HI;

					}
				}
				dbUtility.createPreparedStatement(sql);
				 System.out.println(sql);
				ArrayList list=dbUtility.executeQuery(arr);
				
				if(list!=null&&list.size()>0)
				{
					for(int i=0;i<list.size();i++)
					{
						ArrayList subList=(ArrayList) list.get(i);
						Party party=new Party();
						String appId=(String) subList.get(0);
						if("1".equalsIgnoreCase(appId))
						{
							String name=(String) subList.get(5);
							String fanme=(String) subList.get(6);
							String addr=(String) subList.get(7);
							String role=(String) subList.get(10);
							party.setType("Organization");
							party.setName(name);
							party.setFather_representedBy(fanme);
							party.setAddress(addr);
							party.setHeading(role);
						}
						else if("2".equalsIgnoreCase(appId))
						{
							String name="";
							if( subList.get(2)!=null&&! subList.get(2).toString().equalsIgnoreCase(""))
							{
								name=(String) subList.get(1)+" "+(String) subList.get(2)+" "+(String) subList.get(3);
							}
							else
							{
								name=(String) subList.get(1)+" "+(String) subList.get(3);
							}
							
							String fanme=(String) subList.get(4);
							String addr=(String) subList.get(7);
							String role=(String) subList.get(10);
							party.setType("Person");
							party.setName(name);
							party.setFather_representedBy(fanme);
							party.setAddress(addr);
							party.setHeading(role);
						}
						else if("3".equalsIgnoreCase(appId))
						{
							String name="--";
							if( subList.get(8)!=null&&! subList.get(8).toString().equalsIgnoreCase(""))
							{
								name=(String) subList.get(8);;
							}
							String fanme="--";
							String addr=(String) subList.get(9);
							String role=(String) subList.get(10);
							party.setType("Person");
							party.setName(name);
							party.setFather_representedBy(fanme);
							party.setAddress(addr);
							party.setHeading(role);
						}
						returnList.add(party);
						if(subList.get(12)!=null&&! subList.get(12).toString().equalsIgnoreCase(""))
						{
							String ownerId=subList.get(12).toString();
							String arr1[]=new String[]{ownerId};
							if("en".equalsIgnoreCase(langauge))
							{	
							sql=DocumentFlagEvaluationSQL.GET_PARTY_DETAILS_CER_OWNER;
							}
							else
							{
								sql=DocumentFlagEvaluationSQL.GET_PARTY_DETAILS_CER_OWNER;	
							}
							dbUtility.createPreparedStatement(sql);
							 
							ArrayList list1=dbUtility.executeQuery(arr1);
							if(list1!=null&&list1.size()>0)
							{
								for(int j=0;j<list1.size();j++)
								{
									ArrayList subList1=(ArrayList) list1.get(j);
									Party party1=new Party();
									String appId1=(String) subList1.get(0);
									if("1".equalsIgnoreCase(appId1))
									{
										String name=(String) subList1.get(5);
										String fanme=(String) subList1.get(6);
										String addr=(String) subList1.get(7);
										String role=(String) subList.get(10);
										party1.setType("Organization");
										party1.setName(name);
										party1.setFather_representedBy(fanme);
										party1.setAddress(addr);
										party1.setHeading(role);
									}
									else if("2".equalsIgnoreCase(appId1))
									{
										String name=(String) subList1.get(1);
										
										String fanme=(String) subList1.get(4);
										String addr=(String) subList1.get(7);
										String role=(String) subList.get(10);
										party1.setType("Owner");
										party1.setName(name);
										party1.setFather_representedBy("--");
										party1.setAddress(addr);
										party1.setHeading(role);
									}
									else if("3".equalsIgnoreCase(appId1))
									{
										String name="--";
										if( subList1.get(8)!=null&&! subList1.get(8).toString().equalsIgnoreCase(""))
										{
											name=(String) subList1.get(8);;
										}
											
										String fanme="--";
										String addr=(String) subList1.get(9);
										String role=(String) subList.get(10);
										party1.setType("Owner");
										party1.setName(name);
										party1.setFather_representedBy(fanme);
										party1.setAddress(addr);
										party1.setHeading(role);
									}
								returnList.add(party1);
								}
							}
						}
						
					}
				}
				
				if("en".equalsIgnoreCase(langauge))
				{	
				sql=DocumentFlagEvaluationSQL.GET_CONSENTER_DETAILS_REG;
				}
				else
				{
					sql=DocumentFlagEvaluationSQL.GET_CONSENTER_DETAILS_REG_HI;	
				}
				dbUtility.createPreparedStatement(sql);
				 
				 list=dbUtility.executeQuery(new String[]{regInitNo});
				 if(list!=null&&list.size()>0)
				 {
					 for(int k=0;k<list.size();k++)
					 {
						Party party= new Party();
						 ArrayList subList =(ArrayList) list.get(k);
						 	String name=(String) subList.get(0);
							String fanme=(String) subList.get(1);
							String addr=(String) subList.get(2);
							String role="";
							if("en".equalsIgnoreCase(langauge))
							{
								role="Consenter";
							}
							else
							{
								role="सहमतिकर्ता";
							}
							party.setType("Person");
							party.setName(name);
							party.setFather_representedBy(fanme);
							party.setAddress(addr);
							party.setHeading(role);
							returnList.add(party);
					 }
				 }
					
			} catch(Exception e)
			{
				logger.debug("Exception in save Party Details in DocumentFlagEvaluationDAO"+e);
				e.printStackTrace();
				logger.debug(e.getMessage(),e);
				
	  }finally{
				try {
					dbUtility.closeConnection();
				} catch (Exception e) {
					logger.error("DocumentFlagEvaluationDAO " + e.getStackTrace());
				}
			}
	  return returnList;
	 }
	 
	 public String getSrDetails(String userId) throws Exception{
			String srName = "";
			DBUtility dbUtility=null;
			try {
				dbUtility = new DBUtility();
				String SQL=SealsSQL.GET_SR_DETAILS;
				dbUtility.createPreparedStatement(SQL);
				String arr[] = {userId};
				srName = dbUtility.executeQry(arr);
			} catch (Exception exception) {
				logger.debug("Exception in DocumentFlagEvaluationDAO" + exception);
			}
			finally{
				try {
					dbUtility.closeConnection();
				} catch (Exception e) {
					logger.error("DocumentFlagEvaluationDAO in dao start" + e.getStackTrace());
				}
			}
			return srName;
			
		}
	 
	 public ArrayList getRegStampDuty(String regNumber) throws Exception{
			ArrayList dutyDetls = new ArrayList();
			DBUtility dbUtility=null;
			try {
				dbUtility = new DBUtility();
				String SQL=DocumentFlagEvaluationSQL.GET_REG_STAMP_DETLS;
				dbUtility.createPreparedStatement(SQL);
				String arr[] = {regNumber};
				dutyDetls = dbUtility.executeQuery(arr);
			} catch (Exception exception) {
				logger.debug("Exception in getofficeName" + exception);
			}
			finally{
				try {
					dbUtility.closeConnection();
				} catch (Exception e) {
					logger.error("DocumentFlagEvaluationDAO in dao start" + e.getStackTrace());
				}
			}
			return dutyDetls;
			
		}
	 
	 public String getSystemDate()
	 {
	 	String sysDate = "";
	 	DBUtility dbUtility=null;
	 	try {
	 		dbUtility = new DBUtility(); 
	 		String sql = SealsSQL.GET_SYS_DATE;
	 		dbUtility.createStatement();
	 		sysDate = dbUtility.executeQry(sql);
	 	}catch (Exception exception) {
	 		logger.debug("Exception in getSystemDate" + exception);
	 	}
	 	finally{
	 		try {
	 			dbUtility.closeConnection();
	 		} catch (Exception e) {
	 			logger.error("DocumentFlagEvaluationDAO in dao start" + e.getStackTrace());
	 		}
	 	}
	 	return sysDate;
	 }
	 
	 public String getDeedDocPath(String regNumber) throws Exception{
			String deedDocPath = "";
			DBUtility dbUtility=null;
			try {
				dbUtility = new DBUtility();
				String SQL=DocumentFlagEvaluationSQL.GET_DEED_DOC_PATH;
				dbUtility.createPreparedStatement(SQL);
				String arr[] = {regNumber};
				deedDocPath = dbUtility.executeQry(arr);
			} catch (Exception exception) {
				logger.debug("Exception in getofficeName" + exception);
			}
			finally{
				try {
					dbUtility.closeConnection();
				} catch (Exception e) {
					logger.error("DocumentFlagEvaluationDAO in dao start" + e.getStackTrace());
				}
			}
			return deedDocPath;
			
		}
	 
	 public ArrayList getEstampForRegistration(String regInitId) throws Exception
	 {
	 	ArrayList list = new ArrayList();
	 	DBUtility dbUtility=null;
	 	try {
	 		dbUtility = new DBUtility();
	 		
	 		String query=DocumentFlagEvaluationSQL.GET_ESTAMP_DETLS;
	 		dbUtility.createPreparedStatement(query);
	 		list = dbUtility.executeQuery(new String[]{regInitId});
	 	} catch (Exception exception) {
	 		logger.debug("Exception in getEstampForRegistration" + exception);
	 	}
	 	finally
	 	{
	 		try
	 		{
	 			dbUtility.closeConnection();
	 		}
	 		catch(Exception e)
	 		{
	 			logger.error("error in close connection getEstampForRegistration"+e.getStackTrace());
	 		}
	 		
	 	}
	 	return list;
	 }
	 
	 public String getMarketValue(String regInitId)
	 {
		 String marketValue="--";
		 DBUtility dbUtility=null;
		 try
			{
				dbUtility = new DBUtility();
				String regArr[] = {regInitId};
				sql = DocumentFlagEvaluationSQL.GET_MARKET_VALUE;
				dbUtility.createPreparedStatement(sql);
				//logger.debug(sql);
				marketValue = dbUtility.executeQry(regArr);
				if(marketValue==null||"".equalsIgnoreCase(marketValue))
				{
					marketValue="--";
				}
				else
				{
					if(marketValue.contains("."))
					{
					if((marketValue.substring(marketValue.indexOf("."))).length()>2)
					{	
						marketValue=marketValue.substring(0,(marketValue.indexOf(".")+3));	
					}
					}
					
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
				logger.debug("error in getInstd of DocumentFlagEvaluationDAO"+e.getStackTrace());
			}
			finally
			{
				try
				{
					dbUtility.closeConnection();
				}catch(Exception e)
				{
					e.printStackTrace();
					logger.debug("error in close connection getInstd of DocumentFlagEvaluationDAO"+e.getStackTrace());
					
				}
			}
			return marketValue;
	 }
	 
	 public ArrayList getEstampCodeDetails(String regNum) throws Exception
	 {
	 	ArrayList list = new ArrayList();
	 	DBUtility dbUtility=null;
	 	try
	 	{
	 		dbUtility = new DBUtility();
	 		String regArr[] = {regNum};
	 		sql = DocumentFlagEvaluationSQL.GET_ESTAMP_CODE_DETLS;
	 		dbUtility.createPreparedStatement(sql);
	 		list = dbUtility.executeQuery(regArr);
	 		
	 	}
	 	catch(Exception e)
	 	{
	 		e.printStackTrace();
	 		logger.debug("error in getInstd of DocumentFlagEvaluationDAO"+e.getStackTrace());
	 	}
	 	finally
	 	{
	 		try
	 		{
	 			dbUtility.closeConnection();
	 		}catch(Exception e)
	 		{
	 			e.printStackTrace();
	 			logger.debug("error in close connection getInstd of DocumentFlagEvaluationDAO"+e.getStackTrace());
	 			
	 		}
	 	}
	 	return list;
	 }
	 
	 public String getofficeName(String officeId, String lang) throws Exception{
			String offcName = "";
			DBUtility dbUtility=null;
			try {
				dbUtility = new DBUtility();
				String SQL ="";
				if(lang.equalsIgnoreCase("en"))
					SQL=SealsSQL.GET_OFFICE_NAME;
				else
					SQL=SealsSQL.GET_OFFICE_NAME_HI;
				dbUtility.createPreparedStatement(SQL);
				String arr[] = {officeId};
				offcName = dbUtility.executeQry(arr);
			} catch (Exception exception) {
				logger.debug("Exception in getofficeName" + exception);
			}
			finally{
				try {
					dbUtility.closeConnection();
				} catch (Exception e) {
					logger.error("SealsDAO in dao start" + e.getStackTrace());
				}
			}
			return offcName;
			
		}

	public boolean declareNullAndVoid(DocumentFlagEvaluationForm docForm, String userId) {
		boolean flag=false;
		String []arr=new String[4];
		DBUtility dbUtility=null;
		
		try {
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(DocumentFlagEvaluationSQL.DECLARE_NULL_VOID);
			String dateTime = new SimpleDateFormat("dd-MMM-yyyy hh.mm.ss a").format(new Date());
			arr[3]=docForm.getRegNumber();
			arr[0]=dateTime;
			arr[1]="T";
			arr[2]=userId;
			 flag=dbUtility.executeUpdate(arr);
			 
			 boolean historyFlag= addNullVoidDtls(docForm.getRegNumber(),docForm.getRemarks(),docForm.getFinalDocPath(), dateTime, userId,docForm.getCourtOrderNo(),docForm.getCourtName(),docForm.getCourtOrderDate());
			 
			 if(flag && historyFlag)
				{
					dbUtility.commit();
					flag = true;
				}
				else
				{
					dbUtility.rollback();
					flag = false;
				}
			 
			 } catch (Exception e) {
			e.printStackTrace();
		}finally {
            try {
                dbUtility.closeConnection();
          } catch (Exception e) {
                logger.error("DocumentFlagEvaluationDAO:: " + e.getStackTrace());
          }
       }
		
		return flag;
	}
	
	
	public boolean revertNullAndVoid(DocumentFlagEvaluationForm docForm, String userId,boolean revertFlag) {
		boolean flag=false;
		String []arr=new String[4];
		DBUtility dbUtility=null;
		String courtName = null;
		String courtOrderNo = null;
		String courtOrderDate = null;
		
		if(revertFlag){
			courtName = docForm.getRevertCourtName();
			courtOrderNo = docForm.getRevertCourtOrderNo();
			courtOrderDate = docForm.getRevertCourtOrderDate();
		}else{
			courtName = docForm.getCourtName();
			courtOrderNo=docForm.getCourtOrderNo();
			courtOrderDate=docForm.getCourtOrderDate();
		}
		
		try {
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(DocumentFlagEvaluationSQL.DECLARE_NULL_VOID);
			String dateTime = new SimpleDateFormat("dd-MMM-yyyy hh.mm.ss a").format(new Date());
			arr[3]=docForm.getRegNumber1();
			arr[0]=dateTime;
			arr[1]="F";
			arr[2]=userId;
			 flag=dbUtility.executeUpdate(arr);
			 
			 boolean historyFlag= addNullVoidDtls(docForm.getRegNumber1(),docForm.getRemarks1(),docForm.getFinalDocPath1(), dateTime, userId,courtOrderNo,courtName,courtOrderDate);
			 
			 if(flag && historyFlag)
				{
					dbUtility.commit();
					flag = true;
				}
				else
				{
					dbUtility.rollback();
					flag = false;
				}
			 
			 } catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	public boolean addNullVoidDtls(String regNo, String remarks, String path, String dateTime, String userId,String courtOrderNo,String courtName,String courtOrderDate){
		String []arr=new String[9];
		boolean flag=false;
		DBUtility dbUtility=null;
		
	        try{
	        	dbUtility = new DBUtility();
	        	String 	txnId=getSequenceID(DocumentFlagEvaluationSQL.GET_TXN_SEQ);
	        	
	        	arr[0] =txnId;
	        	arr[1] =regNo;
	        	arr[2] =remarks;
	        	arr[3] =userId;
	        	arr[4] = dateTime;
	        	arr[5] =path+"//AdditionalUpload.Pdf";
	        	arr[6] = courtOrderNo;
	        	arr[7] = courtName;
	        	arr[8] = courtOrderDate;
				dbUtility.createPreparedStatement(DocumentFlagEvaluationSQL.INSERT_NULL_VOID_DETAILS);
				flag=dbUtility.executeUpdate(arr);
	             
	             
	        }
	        catch (Exception exception){
	              System.out.println("Exception in DocumentFlagEvaluationDAO :: addNullVoidDtls" + exception);
	        }
	        /*finally {
	              try {
	                    dbUtility.closeConnection();
	              } catch (Exception e) {
	                    logger.error("DocumentFlagEvaluationDAO:: " + e.getStackTrace());
	              }
	        }*/
	        return flag;
	        
	  }
	
	public String getSequenceID(String sql)
	{
		String id=null;
		DBUtility dbUtility=null;
		 try {
	        	
	        	dbUtility = new DBUtility();
	        		
	        	dbUtility.createStatement();
	        	
	        	id = dbUtility.executeQry(sql);
	        	

	        } catch (Exception exception) {

	                System.out.println("Exception in DocumentFlagEvaluationDAO :: getSequenceID" + exception);

	        }finally{
				try {
					dbUtility.closeConnection();
				} catch (Exception e) {
					logger.error("DocumentFlagEvaluationDAO ::" + e.getStackTrace());
				}
			}
	        
		return id;
	}

	public List getLastRemarksAndUploads(String regNumber) {
		ArrayList list = new ArrayList();
		ArrayList list1 = new ArrayList();
	 	DBUtility dbUtility=null;
	 	try
	 	{
	 		dbUtility = new DBUtility();
	 		String regArr[] = {regNumber};
	 		sql = DocumentFlagEvaluationSQL.GET_LAST_UPDATION_DETAILS;
	 		dbUtility.createPreparedStatement(sql);
	 		list = dbUtility.executeQuery(regArr);
	 		
	 		String updationDate = null, updatedBy = null;
			if(list!=null&&list.size()>0)
			{
				for(int i=0;i<list.size();i++)
				{
					ArrayList subList=(ArrayList) list.get(i);
					updationDate=(String) subList.get(0);
					updatedBy = (String) subList.get(1);
				}
			}
			
			sql = DocumentFlagEvaluationSQL.GET_LAST_REMARKS_UPLOADS;
	 		dbUtility.createPreparedStatement(sql);
	 		String regArr1[] = {regNumber,updatedBy,updationDate };
	 		list1 = dbUtility.executeQuery(regArr1);
	 		
	 	}
	 	catch(Exception e)
	 	{
	 		e.printStackTrace();
	 		logger.debug("error in getInstd of DocumentFlagEvaluationDAO"+e.getStackTrace());
	 	}
	 	finally
	 	{
	 		try
	 		{
	 			dbUtility.closeConnection();
	 		}catch(Exception e)
	 		{
	 			e.printStackTrace();
	 			logger.debug("error in close connection getInstd of DocumentFlagEvaluationDAO"+e.getStackTrace());
	 			
	 		}
	 	}
	 	return list1;
	}
	
	//By Mohit
	public String getReginitId(String regComId) {
        String reginitId="";
        String[]ary=new String[1];
        ary[0]=regComId;
    	DBUtility dbUtil = null;
        try
        {
        	dbUtil = new DBUtility();	
        	dbUtil.createPreparedStatement(DocumentFlagEvaluationSQL.GET_REGINIT_ID);
        	reginitId =dbUtil.executeQry(ary);
          
        } catch (Exception e)
        {
            logger.debug("Exception in getReginitId :- " + e);
        }
        finally {
        	 try{	    
				 if (dbUtil != null) {
					 dbUtil.closeConnection();
			 }
				 }
			 catch (Exception e) {
			 logger.error("Exception at closing connection at getReginitId  " + e);
			 }	
		}
       
        return reginitId;
    }


}
