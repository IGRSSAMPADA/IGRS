package com.wipro.igrs.revenueManagement.dao;


import java.sql.CallableStatement;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import oracle.jdbc.driver.OracleTypes;
import org.apache.log4j.Logger;

import com.wipro.igrs.DeliveryOfDocuments.dao.DeliveryOfDocumentsDAO;
import com.wipro.igrs.RegCompMaker.util.PropertiesFileReader;
import com.wipro.igrs.common.CommonSQL;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.propertylock.dto.PropertyLockDTO;
import com.wipro.igrs.propertylock.sql.PlockSQL;
import com.wipro.igrs.reginit.constant.RegInitConstant;
import com.wipro.igrs.reginit.sql.RegCommonSQL;
import com.wipro.igrs.revenueManagement.dto.RevenueMgtDTO;
import com.wipro.igrs.revenueManagement.sql.RevenueMgtSQL;

public class RevenueMgtDAO {

	
	DBUtility dbUtil = null;
	String sql = null;
	ArrayList mainList = null;
	//DeliveryOfDocumentsDTO  dto = null;
	
	private Logger logger = Logger.getLogger(RevenueMgtDAO.class);
	private PropertiesFileReader pr;
	private CallableStatement clstmt;
	
	
	
	/******************************************************************  
	  *   Method Name  :   getFunction()
	  *   Arguments    :   -
	  *   Return       :   ArrayList
	  *   Throws 	  :   Exception
	 *******************************************************************/ 
	public synchronized ArrayList getFunction() throws Exception {
		ArrayList list = new ArrayList();
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			//list = dbUtil.executeQuery(RevenueMgtSQL.FUNCTION_QUERY);  //COMMENTED BY ROOPAM ON 6 NOV 2013
			//list = dbUtil.executeQuery(RevenueMgtSQL.FUNCTION_QUERY_NEW);
			list = dbUtil.executeQuery(RevenueMgtSQL.FUNCTION_QUERY_NEW_HINDI);
		    } catch (Exception e) {
			logger.debug(e);
		} finally {
			 try{	    
				 if (dbUtil != null) {
					 dbUtil.closeConnection();
			 }
				 }
			 catch (Exception e) {
			 logger.error("Exception in closing connection  " + e);
			 }	
		}
		return list;
	}
	
	/******************************************************************  
	  *   Method Name  :   getService(String funid)
	  *   Arguments    :   function id
	  *   Return       :   ArrayList
	  *   Throws 	  :   Exception
	 *******************************************************************/ 
	public synchronized ArrayList getService(String funid) throws Exception {
		ArrayList list = new ArrayList();
		String arry []=new String[1];
		if (funid != null) {
			arry[0] = funid;
		}

		try {
			
			dbUtil = new DBUtility();
			//dbUtil.createPreparedStatement(RevenueMgtSQL.SERVICE_QUERY);
			dbUtil.createPreparedStatement(RevenueMgtSQL.SERVICE_QUERY_HINDI);
			list = dbUtil.executeQuery(arry);
			} catch (Exception e) {
			logger.debug(e);
		} finally {
			 try{	    
				 if (dbUtil != null) {
					 dbUtil.closeConnection();
			 }
				 }
			 catch (Exception e) {
			 logger.error("Exception in closing connection  " + e);
			 }	
		}
		return list;
	}
	
	/******************************************************************  
	  *   Method Name  :   getMajorHead()
	  *   Arguments    :   -
	  *   Return       :   ArrayList
	  *   Throws 	  :   Exception
	 *******************************************************************/ 
	public synchronized ArrayList getMajorHead() throws Exception {
		ArrayList list = new ArrayList();
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			list = dbUtil.executeQuery(RevenueMgtSQL.MAJOR_HEAD_QUERY);
		    } catch (Exception e) {
			logger.debug(e);
		} finally {
			 try{	    
				 if (dbUtil != null) {
					 dbUtil.closeConnection();
			 }
				 }
			 catch (Exception e) {
			 logger.error("Exception in closing connection  " + e);
			 }	
		}
		return list;
	}
	
	/******************************************************************  
	  *   Method Name  :   getSubMajorHead(String mjrHdId)
	  *   Arguments    :   Major Head ID
	  *   Return       :   ArrayList
	  *   Throws 	  :   Exception
	 *******************************************************************/ 
	/*public synchronized ArrayList getSubMajorHead(String mjrHdId) throws Exception {
		ArrayList list = new ArrayList();
		String arry []=new String[1];
		if (mjrHdId != null) {
			arry[0] = mjrHdId;
		}

		try {
			
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(RevenueMgtSQL.SUB_MAJOR_HEAD_QUERY);
			list = dbUtil.executeQuery(arry);
			} catch (Exception e) {
			logger.debug(e);
		} finally {
			 try{	    
				 if (dbUtil != null) {
					 dbUtil.closeConnection();
			 }
				 }
			 catch (Exception e) {
			 logger.error("Exception in closing connection  " + e);
			 }	
		}
		return list;
	}*/
	public synchronized ArrayList getSubMajorHead() throws Exception {
		ArrayList list = new ArrayList();
	//	String arry []=new String[1];
	//	if (mjrHdId != null) {
	//		arry[0] = mjrHdId;
	//	}

		try {
			
			dbUtil = new DBUtility();
	//		dbUtil.createPreparedStatement(RevenueMgtSQL.SUB_MAJOR_HEAD_QUERY);
	//		list = dbUtil.executeQuery(arry);
			dbUtil.createStatement();
			list = dbUtil.executeQuery(RevenueMgtSQL.SUB_MAJOR_HEAD_QUERY_NEW);
			} catch (Exception e) {
			logger.debug(e);
		} finally {
			 try{	    
				 if (dbUtil != null) {
					 dbUtil.closeConnection();
			 }
				 }
			 catch (Exception e) {
			 logger.error("Exception in closing connection  " + e);
			 }	
		}
		return list;
	}
	/******************************************************************  
	  *   Method Name  :   getMinorHead(String mjrHdId)
	  *   Arguments    :   Sub Major Head ID
	  *   Return       :   ArrayList
	  *   Throws 	  :   Exception
	 *******************************************************************/ 
	/*public synchronized ArrayList getMinorHead(String mjrHdId) throws Exception {
		ArrayList list = new ArrayList();
		String arry []=new String[1];
		if (mjrHdId != null) {
			arry[0] = mjrHdId;
		}

		try {
			
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(RevenueMgtSQL.MINOR_HEAD_QUERY);
			list = dbUtil.executeQuery(arry);
			} catch (Exception e) {
			logger.debug(e);
		} finally {
			 try{	    
				 if (dbUtil != null) {
					 dbUtil.closeConnection();
			 }
				 }
			 catch (Exception e) {
			 logger.error("Exception in closing connection  " + e);
			 }	
		}
		return list;
	}*/
	
	public synchronized ArrayList getMinorHead() throws Exception {
		ArrayList list = new ArrayList();
		//String arry []=new String[1];
		//if (mjrHdId != null) {
		//	arry[0] = mjrHdId;
		//}

		try {
			
			dbUtil = new DBUtility();
			//dbUtil.createPreparedStatement(RevenueMgtSQL.MINOR_HEAD_QUERY);
			//list = dbUtil.executeQuery(arry);
			dbUtil.createStatement();
			list = dbUtil.executeQuery(RevenueMgtSQL.MINOR_HEAD_QUERY_NEW);
			} catch (Exception e) {
			logger.debug(e);
		} finally {
			 try{	    
				 if (dbUtil != null) {
					 dbUtil.closeConnection();
			 }
				 }
			 catch (Exception e) {
			 logger.error("Exception in closing connection  " + e);
			 }	
		}
		return list;
	}
	
	/******************************************************************  
	  *   Method Name  :   getDependList()
	  *   Arguments    :   -
	  *   Return       :   ArrayList
	  *   Throws 	  :   Exception
	 *******************************************************************/ 
	public synchronized ArrayList getDependList() throws Exception {
		ArrayList list = new ArrayList();
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			list = dbUtil.executeQuery(RevenueMgtSQL.DEPENDENCY_QUERY);
		    } catch (Exception e) {
			logger.debug(e);
		} finally {
			 try{	    
				 if (dbUtil != null) {
					 dbUtil.closeConnection();
			 }
				 }
			 catch (Exception e) {
			 logger.error("Exception in closing connection  " + e);
			 }	
		}
		return list;
	}
	
	/******************************************************************  
	  *   Method Name  :   getOperatorList()
	  *   Arguments    :   -
	  *   Return       :   ArrayList
	  *   Throws 	  :   Exception
	 *******************************************************************/ 
	public synchronized ArrayList getOperatorList() throws Exception {
		ArrayList list = new ArrayList();
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			list = dbUtil.executeQuery(RevenueMgtSQL.OPERATOR_QUERY);
		    } catch (Exception e) {
			logger.debug(e);
		} finally {
			 try{	    
				 if (dbUtil != null) {
					 dbUtil.closeConnection();
			 }
				 }
			 catch (Exception e) {
			 logger.error("Exception in closing connection  " + e);
			 }	
		}
		return list;
	}
	
	/******************************************************************  
	  *   Method Name  :   getOperatorList()
	  *   Arguments    :   -
	  *   Return       :   ArrayList
	  *   Throws 	  :   Exception
	 *******************************************************************/ 
	public synchronized ArrayList getYearList() throws Exception {
		ArrayList list = new ArrayList();
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			list = dbUtil.executeQuery(RevenueMgtSQL.YEAR_QUERY);
		    } catch (Exception e) {
			logger.debug(e);
		} finally {
			 try{	    
				 if (dbUtil != null) {
					 dbUtil.closeConnection();
			 }
				 }
			 catch (Exception e) {
			 logger.error("Exception in closing connection  " + e);
			 }	
		}
		return list;
	}
	
	/******************************************************************  
	  *   Method Name  :   getPriorityList()
	  *   Arguments    :   -
	  *   Return       :   ArrayList
	  *   Throws 	  :   Exception
	 *******************************************************************/ 
	public synchronized ArrayList getPriorityList() throws Exception {
		ArrayList list = new ArrayList();
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			list = dbUtil.executeQuery(RevenueMgtSQL.MAJOR_HEAD_QUERY);
		    } catch (Exception e) {
			logger.debug(e);
		} finally {
			 try{	    
				 if (dbUtil != null) {
					 dbUtil.closeConnection();
			 }
				 }
			 catch (Exception e) {
			 logger.error("Exception in closing connection  " + e);
			 }	
		}
		return list;
	}
	
	/******************************************************************  
	  *   Method Name  :   getDistrictList()
	  *   Arguments    :   -
	  *   Return       :   ArrayList
	  *   Throws 	  :   Exception
	 *******************************************************************/ 
	public synchronized ArrayList getDistrictList() throws Exception {
		ArrayList list = new ArrayList();
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			//list = dbUtil.executeQuery(RevenueMgtSQL.DISTRICT_QRY);
			list = dbUtil.executeQuery(RevenueMgtSQL.DISTRICT_QRY_HINDI);
		    } catch (Exception e) {
			logger.debug(e);
		} finally {
			 try{	    
				 if (dbUtil != null) {
					 dbUtil.closeConnection();
			 }
				 }
			 catch (Exception e) {
			 logger.error("Exception in closing connection  " + e);
			 }	
		}
		return list;
	}
	
	/******************************************************************  
	  *   Method Name  :   getOfficeTypeList()
	  *   Arguments    :   -
	  *   Return       :   ArrayList
	  *   Throws 	  :   Exception
	 * @param disId 
	 *******************************************************************/ 
	public synchronized ArrayList getOfficeTypeList(String disId) throws Exception {
		ArrayList list = new ArrayList();
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			//list = dbUtil.executeQuery(RevenueMgtSQL.OFFICE_TYPE_DETAILS);
			list = dbUtil.executeQuery(RevenueMgtSQL.OFFICE_TYPE_DETAILS_HINDI);
		    } catch (Exception e) {
			logger.debug(e);
		} finally {
			 try{	    
				 if (dbUtil != null) {
					 dbUtil.closeConnection();
			 }
				 }
			 catch (Exception e) {
			 logger.error("Exception in closing connection  " + e);
			 }	
		}
		return list;
	}
	
	/******************************************************************  
	  *   Method Name  :   getOfficeNameList()
	  *   Arguments    :   -
	  *   Return       :   ArrayList
	  *   Throws 	  :   Exception
	 * @param disId 
	 * @param offtyp 
	 *******************************************************************/ 
	public synchronized ArrayList getOfficeNameList(String disId, String offtyp) throws Exception {
		ArrayList list = new ArrayList();
		String []arr = new String[2];
		arr[0]=offtyp;
		arr[1]=disId;
		
		try {
			dbUtil = new DBUtility();
			//dbUtil.createStatement();
			//dbUtil.createPreparedStatement(RevenueMgtSQL.OFFICE_NAME_DETAILS);
			dbUtil.createPreparedStatement(RevenueMgtSQL.OFFICE_NAME_DETAILS_HINDI);
			list=dbUtil.executeQuery(arr);
		    } catch (Exception e) {
			logger.debug(e);
		} finally {
			 try{	    
				 if (dbUtil != null) {
					 dbUtil.closeConnection();
			 }
				 }
			 catch (Exception e) {
			 logger.error("Exception in closing connection  " + e);
			 }	
		}
		return list;
	}

	

	/******************************************************************  
	  *   Method Name  :   getreportDetails()
	  *   Arguments    :   -
	  *   Return       :   ArrayList
	  *   Throws 	  :   Exception
	 * @param mnth 
	 * @param toDt 
	 * @param fmDt 
	 * @param userDt 
	 * @param disId 
	 * @param offtyp 
	 *******************************************************************/ 
	public ArrayList getreportDetails(RevenueMgtDTO dto, String radioDt, String userDt, 
			String fmDt, String toDt, String mnth, String languageLocale) {
		ArrayList list = new ArrayList();
		ArrayList list2 = new ArrayList();
		//ArrayList subList = new ArrayList();
		ArrayList subList2 = new ArrayList();
		ArrayList mainList = new ArrayList();
		String payMode=dto.getRadioMode();
		
		String disId=dto.getDistrictId();
		
		/*if(disId.length()<2){
			   disId= "0"+disId;
		   }*/
	
		/*String []date = new String[4];
		date[0]=payMode;
		date[1]=userDt;
		date[2]=disId;
		date[3]=dto.getOfficeId();*/
		
		
		String []week = new String[5];
		week[0]=payMode;//because of EOD challan generation report
		week[1]=fmDt;
		week[2]=toDt;
		week[3]=disId;
		week[4]=dto.getOfficeId();
		
		/*String []month = new String[5];
		month[0]=payMode;
		month[1]="01/"+mnth+"/"+dto.getYearId();
		if(mnth.equalsIgnoreCase("02")){
		if((Integer.parseInt(dto.getYearId()) % 4 == 0 && Integer.parseInt(dto.getYearId()) % 100 != 0) || (Integer.parseInt(dto.getYearId()) % 400 == 0))	
		{
		month[2]="29/"+mnth+"/"+dto.getYearId();
		}else {
			month[2]="28/"+mnth+"/"+dto.getYearId();
		}
		}
		if(mnth.equalsIgnoreCase("01")||mnth.equalsIgnoreCase("03")||mnth.equalsIgnoreCase("05")||mnth.equalsIgnoreCase("07")||mnth.equalsIgnoreCase("08")||mnth.equalsIgnoreCase("10")||mnth.equalsIgnoreCase("12")){
			month[2]="31/"+mnth+"/"+dto.getYearId();
			}
		if(mnth.equalsIgnoreCase("04")||mnth.equalsIgnoreCase("06")||mnth.equalsIgnoreCase("09")||mnth.equalsIgnoreCase("11")){
			month[2]="30/"+mnth+"/"+dto.getYearId();
			}
		month[3]=disId;
		month[4]=dto.getOfficeId();*/
		
		try {
			dbUtil = new DBUtility();
			//dbUtil.createStatement();
			if(fmDt!=null && toDt!=null){
			if(!payMode.equalsIgnoreCase("1")){
			
		   /* if(radioDt.equalsIgnoreCase("date")){
			   dbUtil.createPreparedStatement(RevenueMgtSQL.REPORT_QRY_DATE);
			   list=dbUtil.executeQuery(date);
		    }*/
		    //if(radioDt.equalsIgnoreCase("week")){
				
				   dbUtil.createPreparedStatement(RevenueMgtSQL.REPORT_QRY_WEEK_YEAR);
				   list=dbUtil.executeQuery(week);
			   // }
		   /* if(radioDt.equalsIgnoreCase("month")){
				   dbUtil.createPreparedStatement(RevenueMgtSQL.REPORT_QRY_WEEK_YEAR);
				   list=dbUtil.executeQuery(month);
			    }*/
			}else{
				
			    /*if(radioDt.equalsIgnoreCase("date")){
			    	date[0]="1";//because of EOD challan generation report
				   dbUtil.createPreparedStatement(RevenueMgtSQL.REPORT_QRY_DATE_CASH);
				   list=dbUtil.executeQuery(date);
			    }*/
				if(disId.length()<2){
					   disId= "0"+disId;
					   week[3]=disId;
				   }
				
			    //if(radioDt.equalsIgnoreCase("week")){
				
			    	week[0]="1";//because of EOD challan generation report
					   dbUtil.createPreparedStatement(RevenueMgtSQL.REPORT_QRY_WEEK_YEAR_CASH);
					   list=dbUtil.executeQuery(week);
					   System.out.print(true);
				   // }
			    /*if(radioDt.equalsIgnoreCase("month")){
			    	month[0]="1";//because of EOD challan generation report
					   dbUtil.createPreparedStatement(RevenueMgtSQL.REPORT_QRY_WEEK_YEAR_CASH);
					   list=dbUtil.executeQuery(month);
				    }*/
				
				
			}
		  }
		    RevenueMgtDTO rdto = new RevenueMgtDTO();
		    if(list.size()>0 && list !=null)
			{
		    	for(int i=0; i<list.size(); i++){
					ArrayList subList=((ArrayList)list.get(i));
					if(subList != null && subList.size()>0){ 
							  
					              rdto = new RevenueMgtDTO();
					              
					              if(!payMode.equalsIgnoreCase("1")){
					              rdto.setTransNo((String)subList.get(0));
					              rdto.setTransDtDwnld((String)subList.get(1));
					              if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
					            	  
					              rdto.setTransPurp((String)subList.get(3));    //HINDI
					              rdto.setTransType((String)subList.get(5));    //HINDI
					              rdto.setTransLocOff((String)subList.get(7));  //HINDI
					              rdto.setTransLocDis((String)subList.get(9));  //HINDI
					              }else{
					            	  
					            	  
							          rdto.setTransPurp((String)subList.get(14));    //HINDI
							          rdto.setTransType((String)subList.get(15));    //HINDI
						              rdto.setTransLocOff((String)subList.get(16));  //HINDI
						              rdto.setTransLocDis((String)subList.get(17));  //HINDI
					            	  
					              }
					              rdto.setTransUser((String)subList.get(10));
					              rdto.setTransDtTreas((String)subList.get(11));
					              rdto.setTransAmntDwnld((String)subList.get(12));
					              //rdto.setTransAmntTreas((String)subList.get(13));
					              rdto.setTransAmntTreas((String)subList.get(12));
					              }else{
					            	  
					            	  

						              rdto.setTransNo((String)subList.get(0));
						              rdto.setTransDtDwnld((String)subList.get(1));
						              if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
						           
						            	  rdto.setTransPurp("EOD Challan"); 
						              
						              
						              rdto.setTransType((String)subList.get(3));    //HINDI
						              rdto.setTransLocOff((String)subList.get(5));  //HINDI
						              rdto.setTransLocDis((String)subList.get(7));  //HINDI
						              }else{
						            	  
						            	  
							            	  rdto.setTransPurp("ई ओ डी चालान"); 
							              
								          
								          rdto.setTransType((String)subList.get(12));    //HINDI
							              rdto.setTransLocOff((String)subList.get(13));  //HINDI
							              rdto.setTransLocDis((String)subList.get(14));  //HINDI
						            	  
						              }
						              rdto.setTransUser((String)subList.get(8));
						              rdto.setTransDtTreas((String)subList.get(9));
						              rdto.setTransAmntDwnld((String)subList.get(10));
						              //rdto.setTransAmntTreas((String)subList.get(11));
						              rdto.setTransAmntTreas((String)subList.get(10));
  
					              }
					              mainList.add(rdto);
				
					}
				}
				}
		    
		    /*int totalSum = 0;
		    
		    for(int i=0; i<list.size(); i++){
				ArrayList subList=((ArrayList)list.get(i));
				
				
			
			int amt = Integer.valueOf((String)subList.get(10));
			
			totalSum = totalSum+amt;
			
		    }
		    
		    dto.setTransTotalDwnld(totalSum+"");
			dto.setTransTotalTreas(totalSum+"");*/
		    
		    /* if(radioDt.equalsIgnoreCase("date")){
		    	 
				   dbUtil.createPreparedStatement(RevenueMgtSQL.SUM_QRY_DATE);
				   list2=dbUtil.executeQuery(date);
			    }*/
		     //if(radioDt.equalsIgnoreCase("week")){
		    if(fmDt!=null && toDt!=null){
		 
		    	   dbUtil.createPreparedStatement(RevenueMgtSQL.SUM_QRY_WEEK_YEAR);
				   list2=dbUtil.executeQuery(week);
		    }
		     //}
		   /*  if(radioDt.equalsIgnoreCase("month")){
		    	 
				   dbUtil.createPreparedStatement(RevenueMgtSQL.SUM_QRY_WEEK_YEAR);
				   list2=dbUtil.executeQuery(month);
			    }*/
		     
		     if(list2.size()>0 && list2 !=null)
				{
			    	for(int i=0; i<list2.size(); i++){
						subList2.add((ArrayList)list2.get(i));
						if(subList2 != null && subList2.size()>0){ 
								  for (int k=0; k< subList2.size(); k++){
									 ArrayList compSub = (ArrayList)subList2.get(k);  
									 dto.setTransTotalDwnld((String)compSub.get(0));
									 //dto.setTransTotalTreas((String)compSub.get(1));
									 dto.setTransTotalTreas((String)compSub.get(0));
									 }
						}
			    	}
				}
		     
		    
		    } catch (Exception e) {
			logger.debug(e);
		} finally {
			 try{	    
				 if (dbUtil != null) {
					 dbUtil.closeConnection();
			 }
				 }
			 catch (Exception e) {
			 logger.error("Exception in closing connection  " + e);
			 }	
		}
		return mainList;
	}
	/******************************************************************  
	  *   Method Name  :   updateMatrix()
	  *   Arguments    :   RevenueMgtForm
	  *   Return       :   boolean
	  *   Throws 	   :   Exception
	  *   Author       :   ROOPAM MEHTA
	  *   Date         :   11 Nov 2013   
	 *******************************************************************/
	public synchronized boolean updateMatrix(String[] param) throws Exception {
		
		boolean updation=false;
		try {
			dbUtil = new DBUtility();
			dbUtil.setAutoCommit(false);
			sql = RevenueMgtSQL.UPDATE_MATRIX;
			dbUtil.createPreparedStatement(sql);
			updation=dbUtil.executeUpdate(param);
			if(updation){
				dbUtil.commit();
			}else{
				dbUtil.rollback();
			}
		    } catch (Exception e) {
		    	dbUtil.rollback();
			logger.debug(e);
			return false;
		} finally {
			 try{	    
				 if (dbUtil != null) {
					 dbUtil.closeConnection();
			 }
				 }
			 catch (Exception e) {
			 logger.error("Exception in closing connection  " + e);
			 }	
		}
		return updation;
	}
	/******************************************************************  
	  *   Method Name  :   getMatrixView()
	  *   Arguments    :   -
	  *   Return       :   ArrayList
	  *   Throws 	   :   Exception
	  *   Author       :   ROOPAM MEHTA
	  *   Date         :   11 Nov 2013   
	 *******************************************************************/
	public synchronized ArrayList getMatrixView() throws Exception {
		ArrayList list = new ArrayList();
	
		try {
			
			dbUtil = new DBUtility();
	
			dbUtil.createStatement();
			//list = dbUtil.executeQuery(RevenueMgtSQL.GET_MATRIX_VIEW);
			list = dbUtil.executeQuery(RevenueMgtSQL.GET_MATRIX_VIEW_HINDI);
			} catch (Exception e) {
			logger.debug(e);
		} finally {
			 try{	    
				 if (dbUtil != null) {
					 dbUtil.closeConnection();
			 }
				 }
			 catch (Exception e) {
			 logger.error("Exception in closing connection  " + e);
			 }	
		}
		return list;
	}
}
