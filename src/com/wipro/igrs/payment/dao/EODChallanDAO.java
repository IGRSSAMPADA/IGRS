package com.wipro.igrs.payment.dao;
import org.apache.log4j.Logger;
import com.wipro.igrs.common.IGRSCommon;

import com.wipro.igrs.db.DBUtility;

import com.wipro.igrs.payment.sql.CommonSQL;

import com.wipro.igrs.payment.constant.CommonConstant;
import com.wipro.igrs.payment.form.EODChallanForm;
import com.wipro.igrs.propertylock.sql.PlockSQL;
import com.wipro.igrs.reginit.constant.RegInitConstant;
import com.wipro.igrs.revenueManagement.sql.RevenueMgtSQL;

import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;
/**
 * ===========================================================================
 * File           :   EODChallanDAO.java
 * Description    :   Represents the EOD Challan Generation DAO Class
 * Author         :   Aakriti
 * Created Date   :   Dec 21, 2012

 * ===========================================================================
 */
public class EODChallanDAO {
	ArrayList list = null;
	String str = null;
	String query;
	DBUtility dbUtil=null; // obj for dbutility
	private Logger logger = (Logger) Logger.getLogger(EODChallanDAO.class);
	public EODChallanDAO() throws Exception {
		//dbUtil = new DBUtility(); // creating connection to db

	}

	/**
     * ===========================================================================
     * Method         :   getOfficeNameList()
     * Description    :   Returns list  of OfficeName and its id values. 
     * Arguments      :   
     * return type    :   Arraylist
     
     * ===========================================================================
     */  
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
			list = dbUtil.executeQuery(CommonSQL.MINOR_HEAD_QUERY_NEW);
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
			list = dbUtil.executeQuery(CommonSQL.SUB_MAJOR_HEAD_QUERY_NEW);
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
	public synchronized ArrayList getMajorHead() throws Exception {
		ArrayList list = new ArrayList();
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			list = dbUtil.executeQuery(CommonSQL.MAJOR_HEAD_QUERY);
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
	
	
	public ArrayList getOfficeNameList() {

		try {
			dbUtil = new DBUtility();
			query = CommonSQL.OFFICE_NAME_LIST;
			logger.debug("the query is  in DAO   " + query);
			dbUtil.createStatement();
			list = new ArrayList();
			list = dbUtil.executeQuery(query);
			logger.debug("the values in list is  " + list);
		} catch (Exception x) {
			logger.error("Exception in getOfficeNameList(): " + x);
			
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.error("Exception in closing connection" + ex);
				
			}
		}
		return list;
	}
	
	 /**
     * Method 		: getOracleDate
     * Description	: getting date in oracle date format
     * @param query : string ,
     * @throws 		: 
     * return Type  :string
     */
 public  String getOracleDate(String day,String month,String year) {

	 
	 String inputDate = day + "-" + month + "-" + year;
	 SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	 logger.debug(" " + inputDate + " parses as ");
	 String finalDate = "";
	 Date newDate ;
	 try {
		 newDate = formatter.parse(inputDate);
		 SimpleDateFormat format = new SimpleDateFormat("dd/MMM/yyyy");
		 finalDate = format.format(newDate);
		 logger.debug(finalDate);

	 	}
	 catch (Exception e) {
		 logger.error(e);
	 }

	 return finalDate;

}
	
	/**
     * ===========================================================================
     * Method         :   getOfficeName()
     * Description    :   Returns String of paymode name. 
     * Arguments      :   officeid
     * return type    :   String
     * ===========================================================================
     */  
	public String getOfficeName(String fid,String languageLocale){
		String name=null;
		
		try {
			dbUtil = new DBUtility();
			String[] param={fid};                // createStatement converted to createPreparedStatement by Roopam: 7 Oct 2013.
			if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
			query = CommonSQL.Office_NAME;
			}else{
				query = CommonSQL.OFFICE_NAME_HINDI;
			}
			//query=query+" '"+fid+"'";
			logger.debug("the query is  in DAO   " + query);
			//System.out.println("the querry is in dao...."+query);
			dbUtil.createPreparedStatement(query);
			name = dbUtil.executeQry(param);
			logger.debug("the value in getOfficeName is " + name);
		} catch (Exception x) {
			logger.error("Exception in getOfficeName(): " + x);
			
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.error("Exception in closing connection" + ex);
				
			}
		}
		return name;
	}
	
	
	/**
     * ===========================================================================
     * Method         :   getDistrictId()
     * Description    :   Returns String of paymode name. 
     * Arguments      :   officeid
     * return type    :   String
     * ===========================================================================
     */  
	public String getDistrictId(String fid){
		String name=null;
		
		try {
			dbUtil = new DBUtility();
			String[] param={fid};                // createStatement converted to createPreparedStatement by Roopam: 7 Oct 2013.
			query = CommonSQL.CASH_DISTRICT_ID;
			//query=query+" '"+fid+"'";
			logger.debug("the query is  in DAO   " + query);
			//System.out.println("the querry is in dao...."+query);
			dbUtil.createPreparedStatement(query);
			name = dbUtil.executeQry(param);
			logger.debug("the value in getOfficeName is " + name);
		} catch (Exception x) {
			logger.error("Exception in getOfficeName(): " + x);
			
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.error("Exception in closing connection" + ex);
				
			}
		}
		return name;
	}
	
	/**
     * Method 		: getChallanGendata
     * Description	: returns list of CHALANGEN DETAILS 
     * @param query :  string 
     * return Type  :ArrayList
     */
	
	public ArrayList getChallanGendata(String[] p) {

		try {
			dbUtil = new DBUtility();
			//getting challangen details from igrs_challan_gen_details
			query = CommonSQL.CHALLAN_GEN_LIST;
			logger.debug("the query is  in DAO   " + query);
			dbUtil.createPreparedStatement(query);
			list = new ArrayList();
			list = dbUtil.executeQuery(p);
			logger.debug("the values in list is  " + list);
		} catch (Exception x) {
			logger.error("Exception in getChallanGenData(): " + x);
			
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.debug("Exception in closing connection :-" + ex);
			}
		}
		return list;
	}
	
	/**
     * Method 		: getChallanGendata
     * Description	: returns list of CHALANGEN DETAILS 
     * @param query :  string 
     * return Type  :ArrayList
     */
	
	public ArrayList getlatestChlnDtls(String p) {

		try {
			dbUtil = new DBUtility();
			//getting challangen details from igrs_challan_gen_details
			query = CommonSQL.LATEST_EOD_DTL;
			logger.debug("the query is  in DAO   " + query);
			dbUtil.createPreparedStatement(query);
			list = new ArrayList();
			String []p1 = new String[2];
			p1[0]= p;
			p1[1]= p;
			list = dbUtil.executeQuery(p1);
			logger.debug("the values in list is  " + list);
		} catch (Exception x) {
			logger.error("Exception in getChallanGenData(): " + x);
			
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.debug("Exception in closing connection :-" + ex);
			}
		}
		return list;
	}
	/**
     * Method 		: getTotalAmount
     * Description	: returns total amount collected through cash 
     * @param query :  string array
     * return Type  : String
     */
	
	public ArrayList getTotalAmount(String[] p) {
		ArrayList str1= new ArrayList();		
		try {
			dbUtil = new DBUtility();
			//getting total cash collected from igrs_payment_mode_details
			query = CommonSQL.CASH_AMOUNT;
			logger.debug("the query is  in DAO   " + query);
			
			dbUtil.createPreparedStatement(query);
			str1 = dbUtil.executeQuery(p);
			//System.out.println("the value in the String is>>>>>>>>"+str);
			logger.debug("the values in list is  " + list);
		} catch (Exception x) {
			logger.error("Exception in getChallanGenData(): " + x);
			
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.debug("Exception in closing connection :-" + ex);
			}
		}
		return str1;
	}
	/**
     * Method 		: getChallandata
     * Description	: returns list of CHALANGEN DETAILS 
     * @param query :  string 
     * return Type  :ArrayList
     */
	
	public ArrayList getChallandata(String[] p) {

		try {
			dbUtil = new DBUtility();
			//getting challangen details from igrs_challan_gen_details
			query = CommonSQL.CHALLAN_GEN_UPDATE_LIST;
			logger.debug("the query is  in DAO   " + query);
			dbUtil.createPreparedStatement(query);
			list = new ArrayList();
			list = dbUtil.executeQuery(p);
			logger.debug("the values in list is  " + list);
		} catch (Exception x) {
			logger.error("Exception in getChallanGenData(): " + x);
			
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.debug("Exception in closing connection :-" + ex);
			}
		}
		return list;
	}
	
	public ArrayList getEmpNameDesignation(String[] param) {

		try {
			dbUtil = new DBUtility();
			query = CommonSQL.GET_EMP_NAME_DESIGNATION;
			logger.debug("the query is  in DAO   " + query);
			dbUtil.createPreparedStatement(query);
			list = new ArrayList();
			list = dbUtil.executeQuery(param);
			logger.debug("the values in list is  " + list);
		} catch (Exception x) {
			logger.error("Exception in getEmpNameDesignation(): " + x);
			
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.debug("Exception in closing connection :-" + ex);
			}
		}
		return list;
	}
	
	/**
     * Method 		: addEODDetails
     * Description	: returns boolean gives success of insert or not
     * @param query :  array of string ,
     * return Type  :boolean
     */
	 public boolean addEODDetails(String[] param)throws Exception{
	    	
	    	boolean insertflag = false;	
	        
	    	try {
	    		dbUtil = new DBUtility();
	    		logger.debug("before inserting cash data");
	    		//System.out.println("inside eodchallan DAO..before inserting cash data");
	    		//DBUtility db = new DBUtility();
	    		dbUtil.createPreparedStatement(CommonSQL.EOD_ID_UPDATE);
	            //System.out.println("the query inside the dao class is...."+CommonSQL.EOD_ID_UPDATE);
	            insertflag = dbUtil.executeUpdate(param);
	                   	             
	            logger.debug("after inserting ");
	            //System.out.println(" inside eodchallan DAO..After Inserting");

	        	}
	    	catch (Exception x) {
	    		logger.error("Exception in addEODDetails(): " + x);
	    	   		
	        }
	    	finally {
	            try {
	              		dbUtil.closeConnection();
	            } catch (Exception ex) {
	            	logger.error("Exception in addEODDetails(): " + ex);
	            }
	        }
	        return insertflag;
	 }
	 
	 
	 
	 
	 
	 
	 
	 
	 /**
	     * Method 		: addFinalEODDetails
	     * Description	: returns boolean gives success of transaction
	 * @param uid2 
	     * @param query :  EODChallanForm form ,
	     * return Type  :boolean
	     */
		 public boolean addFinalEODDetails(EODChallanForm form, String uid2)throws NullPointerException,
		                                                            SQLException,Exception{
		    	
		    	 boolean insertflag = false;	
		    	 String[] param1 	= new String[5];
		    	 String[] param2 = new String[9];
				 String day,month,year;
				 String ddate	  =	form.getChdate();
				 String txnid	  =	form.getTxnid();
				 String officeId = form.getOfficeid();
				 String uid = form.getCreatedby();
				 String cashCollected = form.getAmt();
				 String mjrHd=form.getRevMjrHeadId().split(",")[0].trim();
					 String sbmjrHd=form.getRevSubMjrHeadId().split(",")[0].trim();
						 String mnrHd=form.getRevMnrHeadId().split(",")[0].trim();
						 String oldNewReceipt = form.getOldNewReceipt();
				 //param1[0]	= txnid; 
				 //param1[1]	= ddate;
				 param1[1]	= officeId;
				
				 //param2[0]  = txnid;
				 param2[1]  = officeId;
				 param2[2]  = uid;
				 param2[3]  = cashCollected;
				 param2[4]  = form.getMinDate();
				 
				 param2[5]  = mjrHd;
				 param2[6]  = sbmjrHd;
				 param2[7]  = mnrHd;
				 param2[8]  = oldNewReceipt;
				 
				   String refChallanID="";
				   String disId=form.getDisId();
				   String offId=form.getOfficeid();
				   Date date = new Date();
				   Format yearformat = new SimpleDateFormat("yyyy");
				   Format monthformat = new SimpleDateFormat("MM");
				   Format dateformat = new SimpleDateFormat("dd");
				   String dfmt = dateformat.format(date);
				   String yfmt = yearformat.format(date);
				   String mfmt = monthformat.format(date);
				   if(disId.length()<2){
					   disId= "0"+disId;
				   }
				   if(offId.length()==1){
					   offId= "00"+offId;
				   }
				   if(offId.length()==2){
					   offId= "0"+offId;
				   }
				   offId="010";//to be removed post creation of office master
				 
				 
				//DBUtility db = null;
				try {
					dbUtil = new DBUtility();
		    		logger.debug("before inserting cash data");
		    		//System.out.println("inside eodchallan DAO..before inserting eod data");
		    		//db = new DBUtility();
		    		dbUtil.setAutoCommit(false);
		    		String SQL1 = CommonSQL.SELECT_TRANSACTION_ID;    //query moved to CommonSQL by Roopam: 7 Oct 2013.
		    		logger.debug(SQL1);
		    		dbUtil.createStatement();
			   	      String number1 = dbUtil.executeQry(SQL1);
		    		
		    		String SQL5 = "SELECT COUNT(CHALLAN_SERIAL_NUMBER) FROM IGRS_PAYMENT_ECHALLAN_DETAILS WHERE TRUNC(CREATED_DATE)= TRUNC(SYSDATE)";
		    		logger.debug(SQL5);
		    		dbUtil.createStatement();
			   	      String number5 = dbUtil.executeQry(SQL5);
		    		
		   	        
		   	      if (number1.equalsIgnoreCase("0") && number5.equalsIgnoreCase("0")){
		   	                 
		   	         String drpqry = CommonSQL.DROP_PAYMENT_EOD_ID_SEQ;           //query moved to CommonSQL by Roopam: 7 Oct 2013.               
		   	      dbUtil.createStatement();
		   	   dbUtil.executeUpdate(drpqry);
		   	         String crtqry = CommonSQL.CREATE_PAYMENT_EOD_ID_SEQ;        //query moved to CommonSQL by Roopam: 7 Oct 2013.
		   	      dbUtil.createStatement();
		   	   dbUtil.executeUpdate(crtqry);
		   	           }
		   	             String SQL2 = CommonSQL.GET_PAYMENT_EOD_ID_SEQ_NEXTVAL;   //query moved to CommonSQL by Roopam: 7 Oct 2013.
		   	          dbUtil.createStatement();
		   	             String number2 = dbUtil.executeQry(SQL2);
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
		   	             
		   	refChallanID="1"+disId+offId+dfmt+mfmt+yfmt+number2; 
		   	param1[0]=	refChallanID;
		   	param1[2]=	sbmjrHd;
		   	param1[3]=	mnrHd;
			param1[4]=	oldNewReceipt;
		   	param2[0]=	refChallanID;
		    		
		    		
		    		
		    		
		    		
		   	dbUtil.createPreparedStatement(CommonSQL.EOD_INSERT);
		    		insertflag=dbUtil.executeUpdate(param2);
		    		if(insertflag){
		    			dbUtil.createPreparedStatement(CommonSQL.EOD_ID_UPDATE);
		            insertflag=dbUtil.executeUpdate(param1);
		            if(insertflag){
		            	String arry[]=new String[11];
		            	/*SQL1 = "SELECT COUNT(CHALLAN_SERIAL_NUMBER) FROM IGRS_PAYMENT_ECHALLAN_DETAILS WHERE TRUNC(CREATED_DATE)= TRUNC(SYSDATE)";
		       	     logger.debug(SQL1);
		       	  db.createStatement();
		       	     number1 = db.executeQry(SQL1);
		       	     if (number1.equalsIgnoreCase("0")){
		       	                 
		       	                   String drpqry = "DROP SEQUENCE IGRS_PAYMENT_ECHALLAN_ID_SEQ";                         
		       	                db.createStatement();
		       	             db.executeUpdate(drpqry);
		       	                   String crtqry = "CREATE SEQUENCE IGRS_PAYMENT_ECHALLAN_ID_SEQ START WITH 1 INCREMENT BY 1 MAXVALUE 999999 MINVALUE 1 NOCYCLE  NOCACHE ORDER";
		       	                db.createStatement();
		       	             db.executeUpdate(crtqry);
		       	           }*/
		       	           /*  SQL2 = "select IGRS_PAYMENT_ECHALLAN_ID_SEQ.nextval from dual";
		       	          db.createStatement();
		       	             number2 = db.executeQry(SQL2);
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
		       	                     }*/
		       	             
		       	 //refChallanID="1"+disId+offId+dfmt+mfmt+yfmt+number2; 
		       	 arry[0] = refChallanID;
		       	arry[1] = cashCollected;
		       	arry[2] = cashCollected;
		       	arry[3] = disId;
		       	arry[4] = officeId;
		       	arry[5] = uid2;
		       	
		       	arry[6]  = mjrHd;
		       	arry[7]  = sbmjrHd;
		       	arry[8]  = mnrHd;
		       	
		       	int epnrNo=10000000;
		   	 String ePNR = "select EPRN_CHALLAN.nextval from dual";
		        dbUtil.createStatement();
		        String ePNR2 = dbUtil.executeQry(ePNR);
		        if(ePNR2!=null && !ePNR2.equalsIgnoreCase("")){
		        epnrNo=epnrNo+Integer.parseInt(ePNR2);
		        arry[9]= Integer.toString(epnrNo);
		        }else{
		        	throw new Exception();
		        }
		        if(form.getOldNewReceipt().equalsIgnoreCase("Y")){
		             arry[10]= "FUN_404";
		        }else{
		        	 arry[10]= "FUN_405";
		        }
		        //arry[9]= Integer.toString(epnrNo);
		   	 form.setEprn(arry[9]);
		       	dbUtil.createPreparedStatement("INSERT INTO IGRS_PAYMENT_ECHALLAN_DETAILS (CHALLAN_SERIAL_NUMBER, PAYABLE_AMOUNT, PAID_AMOUNT, DISTRICT_ID, OFFICE_ID, PAYMENT_MODE_ID, CREATED_BY, CREATED_DATE,REVENUE_MAJOR_HEAD,REVENUE_SUB_MAJOR_HEAD,REVENUE_MINOR_HEAD,EPNR_NO,FUNCTION_ID)VALUES(?,?,?,?,?,'1',?,SYSDATE,?,?,?,?,?)");
		       	 boolean up=dbUtil.executeUpdate(arry);
		            	
		            	if(up){
		            		dbUtil.commit();
				            form.setTxnid(refChallanID);
				           }else{
				        	   dbUtil.rollback();
				           }
		            }
		            
		           }
				}
		    	catch (NullPointerException ne) {
		    		dbUtil.rollback();
		 		   throw ne;
		 			
		 	    }
		    	    catch (SQLException se) {
		    	    	dbUtil.rollback();
		    	    	
		    		logger.error("SQL Exception at addFinalEODDetails  in DAO " + se); 
		    	 throw se;
		 		}
		 	    catch(Exception e){
		 	    	dbUtil.rollback();
		 		logger.error(" Exception at addFinalEODDetails in DAO " + e);
		 		throw e;
		 	    }
		    	finally {
		            try {
		            	dbUtil.closeConnection();
		            } catch (Exception ex) {
		            	logger.error("Exception in addFinalEODDetails(): " + ex);
		            }
		        }
		        return insertflag;
		 }
	 /**
	     * Method 		: addEODGenDetails
	     * Description	: returns boolean gives success of insert or not
	     * @param query :  array of string ,
	     * return Type  :boolean
	     */
		 public boolean addEODGenDetails(String[] param)throws Exception{
		    	
		    	boolean insertflag = false;	
		        
		    	try {
		    		dbUtil = new DBUtility();
		    		logger.debug("before inserting TID data ");
		    		//System.out.println("inside DAO before inserting data");
		    		
		            dbUtil.createPreparedStatement(CommonSQL.EOD_INSERT);
		            insertflag = dbUtil.executeUpdate(param);
		            //System.out.println("the querry to be executed is"+CommonSQL.EOD_INSERT);
		            //System.out.println("inside DAO after inserting data"); 
		           
		            logger.debug("after inserting ");

		        	}
		    	catch (Exception x) {
		    		logger.error("Exception in addTransidDetails(): " + x);
		    		
		    		
		        }
		    	finally {
		            try {
		            	
		            		dbUtil.closeConnection();
		            } catch (Exception ex) {
		            	
		            	logger.error("Exception in addTransidDetails(): " + ex);
		            	//System.out.println("exception is.............."+ex);
		            }
		        }
		        return insertflag;
		 }
		 public void cancelTrasanction()throws Exception{
		    	try {
		    		dbUtil = new DBUtility();
		    		logger.debug("before commiting whole transaction ");
		    		dbUtil.rollback(); 
		    	}	 
		    	catch (Exception x) {
		    		logger.error("Exception in rollback(): " + x);
		    		
		        }
		    	finally {
		            try {
		            	
		            		dbUtil.closeConnection();
		            } catch (Exception ex) {
		            	
		            	logger.error("Exception in addTransidDetails(): " + ex);
		            	//System.out.println("exception is.............."+ex);
		            }
		        }
		    }

		public String getsrName(String uid)throws Exception {
			
			String name=null;
			
			try {
				dbUtil = new DBUtility();
				String[] param={uid};
				//query = "SELECT FIRST_NAME||' '||MIDDLE_NAME||' ' ||LAST_NAME FROM IGRS_EMP_MASTER where EMP_ID ='" +uid+"'";
				query=CommonSQL.GET_FULL_NAME_FROM_EMP_MASTER;           //query moved to CommonSQL & createStatement changed to preparedStatement by Roopam: 7 Oct 2013.
				logger.debug("the query is  in DAO   " + query);
				//System.out.println("the querry is in dao...."+query);
				dbUtil.createPreparedStatement(query);
			    name = dbUtil.executeQry(param);
				logger.debug("the value in getsrname is " + name);
				
				
				/*String qry = "SELECT LAST_NAME FROM IGRS_EMP_MASTER where EMP_ID ='" +uid+"'";
				
				logger.debug("the query is  in DAO   " + qry);
				System.out.println("the querry is in dao...."+qry);
				dbUtil.createStatement();
				String 	lname = dbUtil.executeQry(qry);
				logger.debug("the value in getOfficeName is " + name);
				
				name= fname+""+""+lname;*/
				
			} catch (Exception x) {
				logger.error("Exception in getOfficeName(): " + x);
				
			} finally {
				try {
					dbUtil.closeConnection();
				} catch (Exception ex) {
					logger.error("Exception in closing connection" + ex);
					
				}
			}
			return name;
		}


		 /**
		 * @param regcomp no
		 *
		 * @return
		 * @throws Exception
		 */
		 public ArrayList getrequestDetails(String txnId,String language) throws Exception{
				String[] tid = new String[1];
				tid[0]=txnId;
				ArrayList list = new ArrayList();
				try{
					dbUtil = new DBUtility();
					if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
				dbUtil.createPreparedStatement(CommonSQL.REQ_DETL_QRY);
					}else{
						dbUtil.createPreparedStatement(CommonSQL.REQ_DETL_QRY_HINDI);
					}
				list=dbUtil.executeQuery(tid);
				list.trimToSize();
				} catch(Exception e){
					   e.printStackTrace();
					   dbUtil.rollback();
					logger.error(" Exception in getPayDtls " + e);
					throw e;
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


//Added By Anuj
		 public ArrayList getDetails(EODChallanForm form){
			 ArrayList alist = new ArrayList();
			 StringTokenizer st=new StringTokenizer(form.getChdate(),"/");
	 	     String day=st.nextToken();
	 	     String month=st.nextToken();
	 	     String year=st.nextToken();
			 String param[]=new String[4];
			 param[0]=form.getTransId();
			 param[1]=getOracleDate(day, month, year);
			 
			 
			  st=new StringTokenizer(form.getMinDate(),"/");
	 	     day=st.nextToken();
	 	     month=st.nextToken();
	 	     year=st.nextToken();
			 param[2]=getOracleDate(day, month, year);
			 param[3]=form.getOfficeid();
			 try{
				 dbUtil = new DBUtility();
				 String sql=CommonSQL.EOD_CHALLAN_DETAILS_VIEW;    //query moved to CommonSQL by Roopam: 7 Oct 2013.
					dbUtil.createPreparedStatement(sql);
					alist=dbUtil.executeQuery(param);
					alist.trimToSize();
					} catch(Exception e){
						   e.printStackTrace();
						   try {
							dbUtil.rollback();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						logger.error(" Exception in getDetails " + e);
						try {
							throw e;
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
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
					 
			 
			 return alist;
		 }


public ArrayList getAllDetails(String transId){
	ArrayList alist = new ArrayList();
	String param[] = new String[1];
	param[0]=transId;
	 try{
		 dbUtil = new DBUtility();
		 String sql=CommonSQL.EOD_CHALLAN_ALL_DETAILS_VIEW;    //query moved to CommonSQL by Roopam: 7 Oct 2013.
			dbUtil.createPreparedStatement(sql);
			alist=dbUtil.executeQuery(param);
			alist.trimToSize();
			} catch(Exception e){
				   e.printStackTrace();
				   try {
					dbUtil.rollback();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					logger.debug(e1.getMessage(),e1);
				}
				logger.error(" Exception in getAllDetails " + e);
				try {
					throw e;
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
	return alist;
}





}





