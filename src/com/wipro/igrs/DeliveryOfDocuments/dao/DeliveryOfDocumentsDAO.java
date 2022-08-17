package com.wipro.igrs.DeliveryOfDocuments.dao;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import oracle.jdbc.driver.OracleTypes;

import org.apache.log4j.Logger;

import com.wipro.igrs.DeliveryOfDocuments.dto.DeliveryOfDocumentsDTO;
import com.wipro.igrs.DeliveryOfDocuments.sql.DeliveryOfDocumentsSQL;
import com.wipro.igrs.RegCompMaker.util.PropertiesFileReader;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.eToken.dto.ETokenDTO;
import com.wipro.igrs.newreginit.constant.RegInitConstant;
import com.wipro.igrs.device.applet.EtokenChange;



public class DeliveryOfDocumentsDAO {
	
	
	String sql = null;
	ArrayList mainList = null;
	//DeliveryOfDocumentsDTO  dto = null;
	
	private Logger logger = Logger.getLogger(DeliveryOfDocumentsDAO.class);
	private PropertiesFileReader pr;
	private CallableStatement clstmt;
	
	
	
	
	
	
	/**
	 * @param -
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getStatus()throws Exception
	{
		ArrayList mainList = new ArrayList();
		sql = DeliveryOfDocumentsSQL.DOD_STATUS_DRPDOWN_QRY;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			mainList = dbUtility.executeQuery(sql);
			} catch (Exception x) {
			logger.debug(x);
		} finally {
			if (dbUtility != null) {
				dbUtility.closeConnection();
			}
		}return mainList;
		
	}
	/**
	 * @param -Rupali
	 * @return ArrayList
	 * @throws Exception
	 */
	
	public ArrayList getChangestatusList()throws Exception
	{
		ArrayList mainList = new ArrayList();
		sql = DeliveryOfDocumentsSQL.DOD_STATUS_CHANGE_QRY;
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			mainList = dbUtility.executeQuery(sql);
			} catch (Exception x) {
			logger.debug(x);
		} finally {
			if (dbUtility != null) {
				dbUtility.closeConnection();
			}
		}return mainList;
		
	}
	
		/**
		 * @param -Rupali
		 * @return ArrayList
		 * @throws Exception
		 */
		public ArrayList getPrintStatus() throws Exception{
		{
			ArrayList mainList = new ArrayList();
			sql = DeliveryOfDocumentsSQL.GET_PRINT_STATUS;
			DBUtility dbUtility = null;
			try {
				dbUtility = new DBUtility();
				dbUtility.createStatement();
				mainList = dbUtility.executeQuery(sql);
				} catch (Exception x) {
				logger.debug(x);
			} finally {
				if (dbUtility != null) {
					dbUtility.closeConnection();
				}
			}return mainList;
			
		}
	}
	/**
	 * @param registration number, document status, from date and to date
	 * @return ArrayList
	 * @throws Exception
	 */
	 public ArrayList getregDetl(String regno, String status, String frmDt, String toDt, String offId, ArrayList offcList,boolean flag) throws Exception{
		 
			
			logger.debug("getRegDetlsSR------>");
			ArrayList list = new ArrayList();
			DBUtility dbUtility = null;
			try{
			dbUtility = new DBUtility();
			String SQL = "";
			if(flag)
			{
				 SQL = DeliveryOfDocumentsSQL.REG_DETL_QRY_FIRST_DR;
				 logger.debug("first part of query--------->>"+SQL);
				 StringBuilder squery = new StringBuilder(SQL);
				 if(!("").equals(regno))
					 squery.append(DeliveryOfDocumentsSQL.REG_DETL_QRY_REG_NUM_DR);
				 if("1".equalsIgnoreCase(status)){
					 squery.append(DeliveryOfDocumentsSQL.REG_DETL_QRY_DEL_STATUS_DR);
				 }else{
				 if(!("").equals(status))
					 squery.append(DeliveryOfDocumentsSQL.REG_DETL_QRY_DEL_STATUS_DR);
				 }
				 if(!("").equals(frmDt))
					 squery.append(DeliveryOfDocumentsSQL.REG_DETL_QRY_DEL_DATE_DR);
				 squery.append(DeliveryOfDocumentsSQL.REG_DETL_QRY_SEC_DR);
				 
				 for(int i=0;i<offcList.size();i++)
					{
						if(i!=offcList.size()-1)
						squery.append("?,");
						else
							squery.append("?");
						
					} // End of for loopp
				
				 int index = 0;
				 if(!("").equals(regno))
					 index++;
				 if("1".equalsIgnoreCase(status)){
					 index=index+2;
				 }else{
				 if(!("").equals(status))
					 index++;
				 }
				 if(!("").equals(frmDt))
					 index = index+2;
				 int len = index+offcList.size();	 
				String[] regDtl = new String[len];
				index = 0;
				 if(!("").equals(regno))
				 {
					 regDtl[index] = regno;
					 index++;
				 }
				 if("1".equalsIgnoreCase(status)){
					 regDtl[index] = "1";
					 index++;
					 regDtl[index] = "1";
					 index++;
				 }else{
				 if(!("").equals(status))
				 {
					 regDtl[index] = status;
					 index++;
				 }
				 }
				 if(!("").equals(frmDt))
				 {
					 regDtl[index] = frmDt;
					 index++;
					 regDtl[index] = toDt;
					 index++;
				 }
				 
					for(int j = 0 ; j < offcList.size();j++)
					{
						ArrayList mainList = (ArrayList)offcList.get(j);
						int k = j+index;
						regDtl[k] = (String)mainList.get(0);
					}
				 
				 squery.append(DeliveryOfDocumentsSQL.REG_DETL_QRY_THIRD_DR);
				 SQL = squery.toString();
				 logger.debug("quey--------------->"+SQL);
				 dbUtility.createPreparedStatement(SQL);
					list=dbUtility.executeQuery(regDtl);
			}
			else
			{
				SQL =DeliveryOfDocumentsSQL.REG_DETL_QRY_FIRST;
				
				 StringBuilder squery = new StringBuilder(SQL);
				 if(!("").equals(regno))
					 squery.append(DeliveryOfDocumentsSQL.REG_DETL_QRY_REG_NUM);
				 if(!("doddto.statusSelected").equalsIgnoreCase(status)){
				 if("1".equalsIgnoreCase(status)){
					 squery.append(DeliveryOfDocumentsSQL.REG_DETL_QRY_DEL_STATUS_DR);
				 }else{
				 if(!("").equals(status))
					 squery.append(DeliveryOfDocumentsSQL.REG_DETL_QRY_DEL_STATUS);
			}
		}
				 if(!("").equals(frmDt))
					 squery.append(DeliveryOfDocumentsSQL.REG_DETL_QRY_DEL_DATE);
				 squery.append(DeliveryOfDocumentsSQL.REG_DETL_QRY_SEC);
				 SQL = squery.toString();
				 logger.debug("quey--------------->"+SQL);
				
				 int index = 0;
				 if(!("").equals(regno))
					 index++;
				 if(!("doddto.statusSelected").equalsIgnoreCase(status)){
				 if("1".equalsIgnoreCase(status)){
					 index=index+1;
				 }else{
				 if(!("").equals(status))
					 index++;
				 }
			}
				 if(!("").equals(frmDt))
					 index = index+2;
				 String[] regDtl = new String[index+1];
				 index = 0;
				if(!("").equals(regno))
				 {
					 regDtl[index] = regno;
					 index++;
				 }
				 if(!("doddto.statusSelected").equalsIgnoreCase(status)){
				 if("1".equalsIgnoreCase(status)){
					 /*regDtl[index] = "1";
					 index++;*/
					 regDtl[index] = "1";
					 index++;
				 }else{
				 if(!("").equals(status))
				 {
					 regDtl[index] = status;
					 index++;
				 }
				 }
			}
				 if(!("").equals(frmDt))
				 {
					 regDtl[index] = frmDt;
					 index++;
					 regDtl[index] = toDt;
					 index++;
				 }
				 regDtl[index]=offId;
				 dbUtility.createPreparedStatement(SQL);
					list=dbUtility.executeQuery(regDtl);
			}
			logger.debug("quey--------------->"+SQL);
			
			}catch (Exception x) {
				logger.debug(x);
			}finally {
				if (dbUtility != null) {
					dbUtility.closeConnection();
				}
			}
			list.trimToSize();
			return list;
		}
	 
	 
	 /**
		 * @param registration number, document status, from date and to date
		 * @return ArrayList
		 * @throws Exception
		 */
		 public String getregDetforChangeStatus(String regno, String status, String offId, ArrayList offcList,boolean flag) throws Exception{
			 boolean transactionflag = false;
		     String detl[]    = new String[3];
		     String[] tid = new String[1];	
		     tid[0]=regno;
		     String updatedStatus="6";
		     if(status.equals("1")){
		     detl[0]="9";
		     detl[1]=regno;
		     detl[2]="1";
		     }
		     else{
		    	 detl[0]="1";
			     detl[1]=regno;
			     detl[2]="9";
		     }
		    
		    
		     DBUtility dbUtility = null;
		   try{
			   dbUtility = new DBUtility();
			   dbUtility.setAutoCommit(true);
		  
			   dbUtility.createPreparedStatement(DeliveryOfDocumentsSQL.CHANGE_STATUS_UPDATE);
			   transactionflag= dbUtility.executeUpdate(detl);
			   if(transactionflag){
				   dbUtility.createPreparedStatement(DeliveryOfDocumentsSQL.SELECT_UPDATED_STATUS);
				   updatedStatus=dbUtility.executeQry(tid);
				   if(updatedStatus.equals("1"))
				   {
					   dbUtility.createPreparedStatement(DeliveryOfDocumentsSQL.UPDATED_READY_TOPRINT_FLAG);
					   transactionflag= dbUtility.executeUpdate(tid);
				   }
				   else if(updatedStatus.equals("9"))
				   {
					   dbUtility.createPreparedStatement(DeliveryOfDocumentsSQL.UPDATED_PRINTED_FLAG);
					   transactionflag= dbUtility.executeUpdate(tid);
				   }
		       dbUtility.commit();				     
			   }
		 }catch (NullPointerException ne) {
			    ne.printStackTrace();
			    dbUtility.rollback();
			   throw ne;
				
		   }
			    catch (SQLException se) {
			    	 se.printStackTrace();
			    	 dbUtility.rollback();
			    	
				logger.error("SQL Exception at estamp  in DAO " + se); 
			 throw se;
			}
		   catch(Exception e){
			   e.printStackTrace();
			   dbUtility.rollback();
			logger.error(" Exception at estamp in DAO " + e);
			throw e;
			}
		   
		   finally {
				 try{	    
					 if (dbUtility != null) {
							dbUtility.closeConnection();
				 }
					 }
				 catch (Exception e) {
				 logger.error("Exception at estamp in DAO  " + e);
				 }		
		       }
		 
		   return updatedStatus;   
		 }
		
	 /**
		 * @param registration number, document status, from date and to date
		 * @return ArrayList
		 * @throws Exception
		 */
		 public ArrayList getregDetlDr(String regno, String status, String frmDt, String toDt, ArrayList offcList) throws Exception{
			 ArrayList list = new ArrayList();
			 DBUtility dbUtility = null;
			 try{	
				 dbUtility = new DBUtility();
			 String SQL = DeliveryOfDocumentsSQL.REG_DETL_QRY_FIRST_DR;
			
			 StringBuilder squery = new StringBuilder(SQL);
			 
			logger.debug("getRegDetlsDR------>");
			 if(!("").equals(regno))
				 squery.append(DeliveryOfDocumentsSQL.REG_DETL_QRY_REG_NUM_DR);
			 if(!("").equals(status))
				 squery.append(DeliveryOfDocumentsSQL.REG_DETL_QRY_DEL_STATUS_DR);
			 if(!("").equals(frmDt) )
				 squery.append(DeliveryOfDocumentsSQL.REG_DETL_QRY_DEL_DATE_DR);
			 squery.append(DeliveryOfDocumentsSQL.REG_DETL_QRY_SEC_DR);
			 
			 for(int i=0;i<offcList.size();i++)
				{
					if(i!=offcList.size()-1)
					squery.append("?,");
					else
						squery.append("?");
					
				} // End of for loopp

			 int index = 0;
			 if(!("").equals(regno) )
				 index++;
			 if(!("").equals(status))
				 index++;
			 if(!("").equals(frmDt))
				 index = index+2;
			 int len = index+offcList.size();	 
			String[] regDtl = new String[len];
			index = 0;
			 if(!("").equals(regno))
			 {
				 regDtl[index] = regno;
				 index++;
			 }
			 if(!("").equals(status))
			 {
				 regDtl[index] = status;
				 index++;
			 }
			 if(!("").equals(frmDt))
			 {
				 regDtl[index] = frmDt;
				 index++;
				 regDtl[index] = toDt;
				 index++;
			 }
			 
	            
				for(int j = 0 ; j < offcList.size();j++)
				{
					ArrayList mainList = (ArrayList)offcList.get(j);
					int k = index+j;
					regDtl[k] = (String)mainList.get(0);
				}
			 
			 squery.append(DeliveryOfDocumentsSQL.REG_DETL_QRY_THIRD_DR);
			 SQL = squery.toString();
			 logger.debug("query----->"+SQL);
			 dbUtility.createPreparedStatement(SQL);
			list=dbUtility.executeQuery(regDtl);
			 //dbUtility.createPreparedStatement(DeliveryOfDocumentsSQL.REG_DETL_DR_QRY);
				//list=dbUtility.executeQuery(regDtl);
				}catch (Exception x) {
					logger.debug(x);
				}finally {
					if (dbUtility != null) {
						dbUtility.closeConnection();
					}
				}
				list.trimToSize();
				return list;
			}
		 
		 /**
			 * @param registration number, document status, from date and to date
			 * @return ArrayList
			 * @throws Exception
			 */
			 public ArrayList getregDetlDr(String regno,   ArrayList offcList) throws Exception{
				 ArrayList list = new ArrayList();
				 DBUtility dbUtility = null;
				 String frmDt="";
				 String toDt="";
				 String status="";
				 try{	
					 dbUtility = new DBUtility();
				 String SQL = DeliveryOfDocumentsSQL.REG_DETL_QRY_FIRST_DR;
				
				 StringBuilder squery = new StringBuilder(SQL);
				 
				logger.debug("getRegDetlsDR------>");
				 if(!("").equals(regno))
					 squery.append(DeliveryOfDocumentsSQL.REG_DETL_QRY_REG_NUM_DR);
				// if(!("").equals(status))
					// squery.append(DeliveryOfDocumentsSQL.REG_DETL_QRY_DEL_STATUS_DR);
				 if(!("").equals(frmDt) )
					 squery.append(DeliveryOfDocumentsSQL.REG_DETL_QRY_DEL_DATE_DR);
				// squery.append(DeliveryOfDocumentsSQL.REG_DETL_QRY_SEC_DR);
				 
				 for(int i=0;i<offcList.size();i++)
					{
						if(i!=offcList.size()-1)
						squery.append("?,");
						else
							squery.append("?");
						
					} // End of for loopp

				 int index = 0;
				 if(!("").equals(regno) )
					 index++;
				 if(!("").equals(status))
					 index++;
				 if(!("").equals(frmDt))
					 index = index+2;
				 int len = index+offcList.size();	 
				String[] regDtl = new String[len];
				index = 0;
				 if(!("").equals(regno))
				 {
					 regDtl[index] = regno;
					 index++;
				 }
				 if(!("").equals(status))
				 {
					 regDtl[index] = status;
					 index++;
				 }
				 if(!("").equals(frmDt))
				 {
					 regDtl[index] = frmDt;
					 index++;
					 regDtl[index] = toDt;
					 index++;
				 }
				 
		            
					for(int j = 0 ; j < offcList.size();j++)
					{
						ArrayList mainList = (ArrayList)offcList.get(j);
						int k = index+j;
						regDtl[k] = (String)mainList.get(0);
					}
				 
				// squery.append(DeliveryOfDocumentsSQL.REG_DETL_QRY_THIRD_DR);
				 SQL = squery.toString();
				 logger.debug("query----->"+SQL);
				 dbUtility.createPreparedStatement(SQL);
				list=dbUtility.executeQuery(regDtl);
				 //dbUtility.createPreparedStatement(DeliveryOfDocumentsSQL.REG_DETL_DR_QRY);
					//list=dbUtility.executeQuery(regDtl);
					}catch (Exception x) {
						logger.debug(x);
					}finally {
						if (dbUtility != null) {
							dbUtility.closeConnection();
						}
					}
					list.trimToSize();
					return list;
				}
			 

		 
	 /**
		 * @param registration number, document status, from date and to date
		 * @return ArrayList
		 * @throws Exception
		 */
		 public ArrayList getTransDetl(String regno) throws Exception{
			 
				String[] regDtl = new String[1];
				String[]reg_compno =new String[1];
	            regDtl[0]=regno;
				ArrayList list = new ArrayList();
				DBUtility dbUtility = null;
				try{
				dbUtility = new DBUtility();
				dbUtility.createPreparedStatement(DeliveryOfDocumentsSQL.TRANS_ID);
				String reg_txn_no = dbUtility.executeQry(regDtl);
				
				reg_compno[0]=reg_txn_no;
				dbUtility.createPreparedStatement(DeliveryOfDocumentsSQL.TRANS_DETL);
				list = dbUtility.executeQuery(reg_compno);
				
				//list=dbUtility.executeQuery(regDtl);
				}catch (Exception x) {
					logger.debug(x);
				}finally {
					if (dbUtility != null) {
						dbUtility.closeConnection();
					}
				}
				list.trimToSize();
				return list;
			}
		 
		 /**
			 * @param registration number
			 * @return ArrayList
			 * @throws Exception
			 */
			 public ArrayList getrecpDetl(String regno) throws Exception{
				 
					String[] regDtl = new String[1];
					regDtl[0]=regno;
					ArrayList list = new ArrayList();
					DBUtility dbUtility = null;
					try{
					dbUtility = new DBUtility();
					dbUtility.createPreparedStatement(DeliveryOfDocumentsSQL.RECP_DETL);
					list = dbUtility.executeQuery(regDtl);
					
					//list=dbUtility.executeQuery(regDtl);
					}catch (Exception x) {
						logger.debug(x);
					}finally {
						if (dbUtility != null) {
							dbUtility.closeConnection();
						}
					}
					list.trimToSize();
					return list;
				}
			 
			 
			 /**
				 * @param registration number
				 * @return ArrayList
				 * @throws Exception
				 */
				 public ArrayList getpartyDetl(String regno) throws Exception{
					 
					 String[] regDtl = new String[1];
						String[]reg_compno =new String[1];
			            regDtl[0]=regno;
						ArrayList list = new ArrayList();
						DBUtility dbUtility = null;
						try{
						dbUtility = new DBUtility();
						dbUtility.createPreparedStatement(DeliveryOfDocumentsSQL.TRANS_ID);
						String reg_txn_no = dbUtility.executeQry(regDtl);
						
						reg_compno[0]=reg_txn_no;
						dbUtility.createPreparedStatement(DeliveryOfDocumentsSQL.NOTICE_TRANS_DETL);
						list = dbUtility.executeQuery(reg_compno);
						
						//list=dbUtility.executeQuery(regDtl);
						}catch (Exception x) {
							logger.debug(x);
						}finally {
							if (dbUtility != null) {
								dbUtility.closeConnection();
							}
						}
						list.trimToSize();
						return list;
					}
	 
	 /**
		 * @param registration number
		 * @return ArrayList
		 * @throws Exception
		 */ 
	 
	 public ArrayList getrprsntatvDetls(String txnId) throws Exception{
			String[] tid = new String[1];
			tid[0]=txnId;
			ArrayList list = new ArrayList();
			DBUtility dbUtility = null;
			try{
				dbUtility = new DBUtility();
				dbUtility.createPreparedStatement(DeliveryOfDocumentsSQL.RPRSNTATV_DETL_QRY);
			    list=dbUtility.executeQuery(tid);
	          }  catch (Exception x) {
					logger.debug(x);
				} finally {
					if (dbUtility != null) {
						dbUtility.closeConnection();
					}
				}list.trimToSize();
			return list;
		}
	 
	 
	 
	 
	 /**
		 * @param registration number
		 * @return ArrayList
		 * @throws Exception
		 */ 
	 
	 public ArrayList getdestroyDetls(String txnId) throws Exception{
			String[] tid = new String[1];
			tid[0]=txnId;
			ArrayList list = new ArrayList();
			DBUtility dbUtility = null;
			try{
				dbUtility = new DBUtility();
				dbUtility.createPreparedStatement(DeliveryOfDocumentsSQL.DESTROY_DETL);
			    list=dbUtility.executeQuery(tid);
	          }  catch (Exception x) {
					logger.debug(x);
				} finally {
					if (dbUtility != null) {
						dbUtility.closeConnection();
					}
				}list.trimToSize();
			return list;
		}
	 /**
		 * @param registration number
		 * @return ArrayList
		 * @throws Exception
		 */ 
	 
	 public ArrayList getissueDetls(String txnId) throws Exception{
			String[] tid = new String[1];
			tid[0]=txnId;
			ArrayList list = new ArrayList();
			DBUtility dbUtility = null;
			try{
				dbUtility = new DBUtility();
				dbUtility.createPreparedStatement(DeliveryOfDocumentsSQL.ISSUE_DETL);
			    list=dbUtility.executeQuery(tid);
	          }  catch (Exception x) {
					logger.debug(x);
				} finally {
					if (dbUtility != null) {
						dbUtility.closeConnection();
					}
				}list.trimToSize();
			return list;
		}
	 /**
		 * @param registration number
		 * @return ArrayList
		 * @throws Exception
		 */ 
	 
	 public ArrayList getApproveDetls(String txnId) throws Exception{
			String[] tid = new String[1];
			tid[0]=txnId;
			ArrayList list = new ArrayList();
			DBUtility dbUtility = null;
			try{
				dbUtility = new DBUtility();
				dbUtility.createPreparedStatement(DeliveryOfDocumentsSQL.APPROVE_DETL);
			    list=dbUtility.executeQuery(tid);
	          }  catch (Exception x) {
					logger.debug(x);
				} finally {
					if (dbUtility != null) {
						dbUtility.closeConnection();
					}
				}list.trimToSize();
			return list;
		}
	 
	 /**
		 * @param registration number
		 * @return ArrayList
		 * @throws Exception
		 */ 
	 
	 public ArrayList getdeliveredDetls(String txnId) throws Exception{
			String[] tid = new String[1];
			tid[0]=txnId;
			ArrayList list = new ArrayList();
			DBUtility dbUtility = null;
			try{
				dbUtility = new DBUtility();
				dbUtility.createPreparedStatement(DeliveryOfDocumentsSQL.DELIVERED_DETL);
			    list=dbUtility.executeQuery(tid);
	          }  catch (Exception x) {
					logger.debug(x);
				} finally {
					if (dbUtility != null) {
						dbUtility.closeConnection();
					}
				}list.trimToSize();
			return list;
		}
	 /**
		 * @param registration number
		 * @return ArrayList
		 * @throws Exception
		 */ 
	 
	 public ArrayList getdeliveredDetlsPost(String txnId) throws Exception{
			String[] tid = new String[1];
			tid[0]=txnId;
			ArrayList list = new ArrayList();
			DBUtility dbUtility = null;
			try{
				dbUtility = new DBUtility();
				dbUtility.createPreparedStatement(DeliveryOfDocumentsSQL.DELIVERED_DETL_POST);
			    list=dbUtility.executeQuery(tid);
	          }  catch (Exception x) {
					logger.debug(x);
				} finally {
					if (dbUtility != null) {
						dbUtility.closeConnection();
					}
				}list.trimToSize();
			return list;
		}
	 
	 /**
		 * @param registration number
		 * @return ArrayList
		 * @throws Exception
		 */ 
	 
	 public ArrayList getDrDetl(String txnId) throws Exception{
			String[] tid = new String[1];
			tid[0]=txnId;
			ArrayList list = new ArrayList();
			DBUtility dbUtility = null;
			try{
				dbUtility = new DBUtility();
				dbUtility.createPreparedStatement(DeliveryOfDocumentsSQL.DR_DETL);
			    list=dbUtility.executeQuery(tid);
	          }  catch (Exception x) {
					logger.debug(x);
				} finally {
					if (dbUtility != null) {
						dbUtility.closeConnection();
					}
				}list.trimToSize();
			return list;
		}
	 
	 
	
	 /**
		 * @param registration number, user id, office id
		 * @return boolean
		 * @throws Exception
		 */ 
	 public  boolean statusDestroyUpdate (String regNo, String uid, String offid)throws Exception{
	     boolean transactionflag = false;
	     String detl[]    = new String[3];
	     detl[0]=uid;
	     detl[1]=offid;
	     detl[2]=regNo;
	     DBUtility dbUtility = null;
	   try{
		   dbUtility = new DBUtility();
		   dbUtility.setAutoCommit(true);
	  
		   dbUtility.createPreparedStatement(DeliveryOfDocumentsSQL.DESTROY_STATUS_UPDATE);
		   transactionflag= dbUtility.executeUpdate(detl);
		   if(transactionflag){
	       dbUtility.commit();				     
		   }
	 }catch (NullPointerException ne) {
		    ne.printStackTrace();
		    dbUtility.rollback();
		   throw ne;
			
	   }
		    catch (SQLException se) {
		    	 se.printStackTrace();
		    	 dbUtility.rollback();
		    	
			logger.error("SQL Exception at estamp  in DAO " + se); 
		 throw se;
		}
	   catch(Exception e){
		   e.printStackTrace();
		   dbUtility.rollback();
		logger.error(" Exception at estamp in DAO " + e);
		throw e;
		}
	   
	   finally {
			 try{	    
				 if (dbUtility != null) {
						dbUtility.closeConnection();
			 }
				 }
			 catch (Exception e) {
			 logger.error("Exception at estamp in DAO  " + e);
			 }		
	       }
	 
	   return transactionflag;   
	 }
	
	 
	 /**
		 * @param registration number, user id, office id
		 * @return boolean
		 * @throws Exception
		 */ 
	 public  boolean statusApproveUpdate (String regNo, String uid, String offid)throws Exception{
	     boolean transactionflag = false;
	     String detl[]    = new String[4];
	     detl[0]=uid;
	     detl[1]=offid;
	     detl[2]=uid;
	     detl[3]=regNo;
	     DBUtility dbUtility = null;
	   try{
		   
		   dbUtility = new DBUtility();
		   dbUtility.setAutoCommit(true);
	  
		   dbUtility.createPreparedStatement(DeliveryOfDocumentsSQL.APPROVE_STATUS_UPDATE);
		   transactionflag=dbUtility.executeUpdate(detl);
		   if(transactionflag){
			   dbUtility.commit();		 
		   }
	     		     
		
	 }catch (NullPointerException ne) {
		    ne.printStackTrace();
		    dbUtility.rollback();
		   throw ne;
			
	   }
		    catch (SQLException se) {
		    	 se.printStackTrace();
		    	 dbUtility.rollback();
		    	
			logger.error("SQL Exception at estamp  in DAO " + se); 
		 throw se;
		}
	   catch(Exception e){
		   e.printStackTrace();
		   dbUtility.rollback();
		logger.error(" Exception at estamp in DAO " + e);
		throw e;
		}
	   
	   finally {
			 try{	    
				 if (dbUtility != null) {
						dbUtility.closeConnection();
			 }
				 }
			 catch (Exception e) {
			 logger.error("Exception at estamp in DAO  " + e);
			 }		
	       }
	 
	   return transactionflag;   
	 }
	
	 
	
	 /**
		 * @param registration number, user id, office id
		 * @return boolean
		 * @throws Exception
		 */ 
	 public  boolean statusDeliveredUpdate (String regNo, String uid, String offid, String rcptName,DeliveryOfDocumentsDTO dto)throws Exception{
	     boolean transactionflag = false;
	     ArrayList<String> list=new ArrayList<String>();
	     ArrayList statusList = new ArrayList();
	     String detl[]    = new String[3];
	     detl[0]=uid;
	     detl[1]=offid;
	     detl[2]=regNo;
	     String rec[]=new String[5];
	     rec[1]=regNo;
	     rec[3]=uid;
	     rec[4]=dto.getHandPath();
	     String temp[] = new String[1];
			temp[0]=regNo;
	     
	     DBUtility dbUtility = null;
	   try{
		   dbUtility = new DBUtility();
		   dbUtility.setAutoCommit(true);
	  
		   dbUtility.createPreparedStatement(DeliveryOfDocumentsSQL.DELIVERED_STATUS_UPDATE);
		   transactionflag=dbUtility.executeUpdate(detl);
		   if(transactionflag){
		 //  for(int i=0; i<chckArry.length;i++){
			   String sql="select IGRS_DEL_DOC_RECPNT_ID_SEQ.nextval from dual";
			   dbUtility.createStatement();
			   String number = dbUtility.executeQry(sql);
			   String nm = rcptName;
			   rec[2]=nm;
			   rec[0]=number;
			   dbUtility.createPreparedStatement(DeliveryOfDocumentsSQL.RECPNT_INSERT);
			   dbUtility.executeUpdate(rec); 
		  // }
		   transactionflag = true;
	       dbUtility.commit();	
	     //Start : Changes added by Rupali for QMS
	        String query1=DeliveryOfDocumentsSQL.GET_STATUS_DELIVERED;
		       dbUtility.createPreparedStatement(query1);
		       statusList = dbUtility.executeQuery(temp);
		       if(statusList.size()>0){
		    	
		    		   for(int i=0;i<statusList.size();i++){
		    			   ArrayList ret=(ArrayList)statusList.get(i);
		    			
		    			   String status = (String)ret.get(0);
				    	   String regInitId=(String)ret.get(1);
				    	   if("7".equalsIgnoreCase(status))
				   			{
				   			EtokenChange eChange = new EtokenChange(regInitId,status,null);
				   			Thread t = new Thread(eChange);
				   			t.start();
				   			}
		    		   }
		    	   
		    	
		    		   }}
				//End : Changes added by Rupali for QMS
		
	 }catch (NullPointerException ne) {
		    ne.printStackTrace();
		    dbUtility.rollback();
		   throw ne;
			
	   }
		    catch (SQLException se) {
		    	 se.printStackTrace();
		    	 dbUtility.rollback();
		    	
			logger.error("SQL Exception at estamp  in DAO " + se); 
		 throw se;
		}
	   catch(Exception e){
		   e.printStackTrace();
		   dbUtility.rollback();
		logger.error(" Exception at estamp in DAO " + e);
		throw e;
		}
	   
	   finally {
			 try{	    
				 if (dbUtility != null) {
						dbUtility.closeConnection();
			 }
				 }
			 catch (Exception e) {
			 logger.error("Exception at estamp in DAO  " + e);
			 }		
	       }
	 
	   return transactionflag;   
	 }
	 
	 

	 /**
		 * @param registration number, user id, office id, docketno
		 * @return boolean
		 * @throws Exception
		 */ 
	 public  boolean statusDeliveredUpdatePost (String regNo, String uid, String offid, String docketno)throws Exception{
	     boolean transactionflag = false;
	     String detl[]    = new String[4];
	     detl[0]=uid;
	     detl[1]=offid;
	     detl[2]=docketno;
	     detl[3]=regNo;
	     ArrayList<String> list=new ArrayList<String>();
	     ArrayList statusList=new ArrayList();
	     String temp[] = new String[1];
				temp[0]=regNo;
	     DBUtility dbUtility = null;
	   try{
		   dbUtility = new DBUtility();
		   dbUtility.setAutoCommit(true);
	  
		   dbUtility.createPreparedStatement(DeliveryOfDocumentsSQL.DELIVERED_STATUS_UPDATE_POST);
		   transactionflag= dbUtility.executeUpdate(detl);
		   
		   if(transactionflag)
	       {dbUtility.commit();		
	     //Start : Changes added by Rupali for QMS
	        String query1=DeliveryOfDocumentsSQL.GET_STATUS_DELIVERED;
		       dbUtility.createPreparedStatement(query1);
		       statusList = dbUtility.executeQuery(temp);
		       if(statusList.size()>0){
		    	
		    		   for(int i=0;i<statusList.size();i++){
		    			   ArrayList ret=(ArrayList)statusList.get(i);
		    			
		    			   String status = (String)ret.get(0);
				    	   String regInitId=(String)ret.get(1);
				    	   if("7".equalsIgnoreCase(status))
				   			{
				   			EtokenChange eChange = new EtokenChange(regInitId,status,null);
				   			Thread t = new Thread(eChange);
				   			t.start();
				   			}
		    		   }
		    	   
		    	
		    		   }
				//End : Changes added by Rupali for QMS
	       }
		
	 }catch (NullPointerException ne) {
		    ne.printStackTrace();
		    dbUtility.rollback();
		   throw ne;
			
	   }
		    catch (SQLException se) {
		    	 se.printStackTrace();
		    	 dbUtility.rollback();
		    	
			logger.error("SQL Exception at estamp  in DAO " + se); 
		 throw se;
		}
	   catch(Exception e){
		   e.printStackTrace();
		   dbUtility.rollback();
		logger.error(" Exception at estamp in DAO " + e);
		throw e;
		}
	   
	   finally {
			 try{	    
				 if (dbUtility != null) {
						dbUtility.closeConnection();
			 }
				 }
			 catch (Exception e) {
			 logger.error("Exception at estamp in DAO  " + e);
			 }		
	       }
	 
	   return transactionflag;   
	 }
	 /**
		 * @param registration number, user id, office id
		 * @return boolean
		 * @throws Exception
		 */ 
	 public  boolean statusDeliveredUpdateP (String regNo, String uid, String offid, String rcptName)throws Exception{
	     boolean transactionflag = false;
	     ArrayList<String> list=new ArrayList<String>();
	     ArrayList subList = new ArrayList();
	     ArrayList statusList = new ArrayList();
	     String detl[]    = new String[3];
	     detl[0]=uid;
	     detl[1]=offid;
	     detl[2]=regNo;
	     String rec[]=new String[4];
	     rec[1]=regNo;
	     rec[3]=uid;
	     String[] paydetl=new String[2];
	     paydetl[0]=uid;
	     paydetl[1]=regNo;
	     
	     String temp[] = new String[1];
			temp[0]=regNo;
	     
	     DBUtility dbUtility = null;
	   try{
		   dbUtility = new DBUtility();
		   dbUtility.setAutoCommit(true);
	  
		   dbUtility.createPreparedStatement(DeliveryOfDocumentsSQL.DELIVERED_STATUS_UPDATE);
		   transactionflag=dbUtility.executeUpdate(detl);
		   if(transactionflag){
		  // for(int i=0; i<chckArry.length;i++){
			   String sql="select IGRS_DEL_DOC_RECPNT_ID_SEQ.nextval from dual";
			   dbUtility.createStatement();
			   String number = dbUtility.executeQry(sql);
			   String nm = rcptName;
			   rec[2]=nm;
			   rec[0]=number;
			   dbUtility.createPreparedStatement(DeliveryOfDocumentsSQL.RECPNT_INSERT);
			   dbUtility.executeUpdate(rec); 
			   dbUtility.createPreparedStatement(DeliveryOfDocumentsSQL.PAY_FLAG_UPDTAE);
			   dbUtility.executeUpdate(paydetl); 
		  // }
		   transactionflag = true;
	       dbUtility.commit();				     
	     //Start : Changes added by Rupali for QMS
	        String query1=DeliveryOfDocumentsSQL.GET_STATUS_DELIVERED;
		       dbUtility.createPreparedStatement(query1);
		       statusList = dbUtility.executeQuery(temp);
		       if(statusList.size()>0){
		    	
		    		   for(int i=0;i<statusList.size();i++){
		    			   ArrayList ret=(ArrayList)statusList.get(i);
		    			
		    			   String status = (String)ret.get(0);
				    	   String regInitId=(String)ret.get(1);
				    	   if("7".equalsIgnoreCase(status))
				   			{
				   			EtokenChange eChange = new EtokenChange(regInitId,status,null);
				   			Thread t = new Thread(eChange);
				   			t.start();
				   			}
		    		   }
		    	   
		    	
		    		   }
				//End : Changes added by Rupali for QMS
		
	 }}catch (NullPointerException ne) {
		    ne.printStackTrace();
		    dbUtility.rollback();
		   throw ne;
			
	   }
		    catch (SQLException se) {
		    	 se.printStackTrace();
		    	 dbUtility.rollback();
		    	
			logger.error("SQL Exception at estamp  in DAO " + se); 
		 throw se;
		}
	   catch(Exception e){
		   e.printStackTrace();
		   dbUtility.rollback();
		logger.error(" Exception at estamp in DAO " + e);
		throw e;
		}
	   
	   finally {
			 try{	    
				 if (dbUtility != null) {
						dbUtility.closeConnection();
			 }
				 }
			 catch (Exception e) {
			 logger.error("Exception at estamp in DAO  " + e);
			 }		
	       }
	 
	   return transactionflag;   
	 }
	 
	 /**
		 * @param lastdt 
	 * @param remarks 
	 * @param registration number, user id, office id
		 * @return boolean
		 * @throws Exception
		 */ 
	 public  boolean statusNoticeUpdate (String regNo, String uid, String offid, String remarks, String lastdt, ArrayList partydtls)throws Exception{
	     boolean transactionflag = false;
	     String detl[]    = new String[3];
	     detl[0]=uid;
	     detl[1]=offid;
	     detl[2]=regNo;
	     String noticedtl[]=new String[5];
	    
	     
	     DBUtility dbUtility = null;
	   try{
		   dbUtility = new DBUtility();
		   dbUtility.setAutoCommit(true);
	  
		   dbUtility.createPreparedStatement(DeliveryOfDocumentsSQL.NOTICE_STATUS_UPDATE);
		   transactionflag=dbUtility.executeUpdate(detl);
		     /*  for (int i =0; i<partydtls.size(); i++ ){
			   DeliveryOfDocumentsDTO dod= (DeliveryOfDocumentsDTO)partydtls.get(i);
			  String role = dod.getNoticePartyRole;
			   */
		   if(transactionflag){
		       String sql="select IGRS_DEL_DOC_NOTICE_ID_SEQ.nextval from dual";
			   dbUtility.createStatement();
			   String number = dbUtility.executeQry(sql);
			   noticedtl[0]=number;
			   noticedtl[1]=regNo;
			   noticedtl[2]=remarks;
			   noticedtl[3]=uid;
			   noticedtl[4]=offid;
			   dbUtility.createPreparedStatement(DeliveryOfDocumentsSQL.NOTICE_INSERT);
			   transactionflag=dbUtility.executeUpdate(noticedtl); 
	  // }
			   if(transactionflag){
	            dbUtility.commit();				     
		   }
		   }
	 }catch (NullPointerException ne) {
		    ne.printStackTrace();
		    dbUtility.rollback();
		   throw ne;
			
	   }
		    catch (SQLException se) {
		    	 se.printStackTrace();
		    	 dbUtility.rollback();
		    	
			logger.error("SQL Exception at estamp  in DAO " + se); 
		 throw se;
		}
	   catch(Exception e){
		   e.printStackTrace();
		   dbUtility.rollback();
		logger.error(" Exception at estamp in DAO " + e);
		throw e;
		}
	   
	   finally {
			 try{	    
				 if (dbUtility != null) {
						dbUtility.closeConnection();
			 }
				 }
			 catch (Exception e) {
			 logger.error("Exception at estamp in DAO  " + e);
			 }		
	       }
	 
	   return transactionflag;   
	 }
	 
	 
	
	 /**
		 * @param registration number, user id, office id
		 * @return boolean
		 * @throws Exception
		 */ 
	 public  boolean statusDeliveredUpdatePymt (String regNo, String uid, String offid, String rcptName, String fee)throws Exception{
	     boolean transactionflag = false;
	     String detl[]    = new String[3];
	     detl[0]=uid;
	     detl[1]=offid;
	     detl[2]=regNo;
	     String rec[]=new String[4];
	     rec[1]=regNo;
	     rec[3]=uid;
	     ArrayList<String> list=new ArrayList<String>();
	     ArrayList statusList = new ArrayList();
	     String temp[] = new String[1];
				temp[0]=regNo;
	     
	     
	     DBUtility dbUtility = null;
	   try{
		   dbUtility = new DBUtility();
		   dbUtility.setAutoCommit(true);
	  
		   dbUtility.createPreparedStatement(DeliveryOfDocumentsSQL.DELIVERED_STATUS_UPDATE);
		   transactionflag=dbUtility.executeUpdate(detl);
		   if(transactionflag){
		  // for(int i=0; i<chckArry.length;i++){
			   String sql="select IGRS_DEL_DOC_RECPNT_ID_SEQ.nextval from dual";
			   dbUtility.createStatement();
			   String number = dbUtility.executeQry(sql);
			   String nm = rcptName;
			   rec[2]=nm;
			   rec[0]=number;
			   dbUtility.createPreparedStatement(DeliveryOfDocumentsSQL.RECPNT_INSERT);
			   transactionflag=dbUtility.executeUpdate(rec); 
		  // }
		   if(transactionflag){
	       dbUtility.commit();		
	     //Start : Changes added by Rupali for QMS
	        String query1=DeliveryOfDocumentsSQL.GET_STATUS_DELIVERED;
		       dbUtility.createPreparedStatement(query1);
		       statusList = dbUtility.executeQuery(temp);
		       if(statusList.size()>0){
		    	
		    		   for(int i=0;i<statusList.size();i++){
		    			   ArrayList ret=(ArrayList)statusList.get(i);
		    			
		    			   String status = (String)ret.get(0);
				    	   String regInitId=(String)ret.get(1);
				    	   if("7".equalsIgnoreCase(status))
				   			{
				   			EtokenChange eChange = new EtokenChange(regInitId,status,null);
				   			Thread t = new Thread(eChange);
				   			t.start();
				   			}
		    		   }
		    	   
		    	
		    		   }
				//End : Changes added by Rupali for QMS
		   }
		   }
	 }catch (NullPointerException ne) {
		    ne.printStackTrace();
		    dbUtility.rollback();
		   throw ne;
			
	   }
		    catch (SQLException se) {
		    	 se.printStackTrace();
		    	 dbUtility.rollback();
		    	
			logger.error("SQL Exception at estamp  in DAO " + se); 
		 throw se;
		}
	   catch(Exception e){
		   e.printStackTrace();
		   dbUtility.rollback();
		logger.error(" Exception at estamp in DAO " + e);
		throw e;
		}
	   
	   finally {
			 try{	    
				 if (dbUtility != null) {
						dbUtility.closeConnection();
			 }
				 }
			 catch (Exception e) {
			 logger.error("Exception at estamp in DAO  " + e);
			 }		
	       }
	 
	   return transactionflag;   
	 }
	
	 /**
		 * @param dto2 
	 * @param registration number, user id, office id
		 * @return boolean
		 * @throws Exception
		 */ 
	 public  boolean statusDeliveredUpdate1 (String regNo, String uid, String offid, String fname, String mname, String lname, String docName, String docPath )throws Exception{
	     boolean transactionflag = false;
	     String detl[]    = new String[3];
	     detl[0]=uid;
	     detl[1]=offid;
	     detl[2]=regNo;
	     String rec[]=new String[8];
	     ArrayList<String> list=new ArrayList<String>();
	     ArrayList statusList = new ArrayList();
	     String temp[] = new String[1];
				temp[0]=regNo;
	     DBUtility dbUtility = null;
	   try{
		   dbUtility = new DBUtility();
		   dbUtility.setAutoCommit(true);
	  
		   dbUtility.createPreparedStatement(DeliveryOfDocumentsSQL.DELIVERED_STATUS_UPDATE1);
		   transactionflag= dbUtility.executeUpdate(detl);
		  if(transactionflag){
			   String sql=DeliveryOfDocumentsSQL.GET_DEL_DOC_RECPNT_ID_SEQ;
			   dbUtility.createStatement();
			   String number = dbUtility.executeQry(sql);
			   rec[0]=number;
			   rec[1]=regNo;
			   rec[2]=fname;
			   rec[3]=mname;
			   rec[4]=lname;
			   rec[5]=uid;
			   rec[6]=docName;
			   rec[7]=docPath;
			   dbUtility.createPreparedStatement(DeliveryOfDocumentsSQL.RECPNT_INSERT1);
			   transactionflag= dbUtility.executeUpdate(rec); 
		 
			   if(transactionflag){
	       dbUtility.commit();		
	     //Start : Changes added by Rupali for QMS
	        String query1=DeliveryOfDocumentsSQL.GET_STATUS_DELIVERED;
		       dbUtility.createPreparedStatement(query1);
		       statusList = dbUtility.executeQuery(temp);
		       if(statusList.size()>0){
		    	
		    		   for(int i=0;i<statusList.size();i++){
		    			   ArrayList ret=(ArrayList)statusList.get(i);
		    			
		    			   String status = (String)ret.get(0);
				    	   String regInitId=(String)ret.get(1);
				    	   if("7".equalsIgnoreCase(status))
				   			{
				   			EtokenChange eChange = new EtokenChange(regInitId,status,null);
				   			Thread t = new Thread(eChange);
				   			t.start();
				   			}
		    		   }
		    	   
		    	
		    		   }
				//End : Changes added by Rupali for QMS
		  }}
	 }catch (NullPointerException ne) {
		    ne.printStackTrace();
		    dbUtility.rollback();
		   throw ne;
			
	   }
		    catch (SQLException se) {
		    	 se.printStackTrace();
		    	 dbUtility.rollback();
		    	
			logger.error("SQL Exception at estamp  in DAO " + se); 
		 throw se;
		}
	   catch(Exception e){
		   e.printStackTrace();
		   dbUtility.rollback();
		 logger.error(" Exception at estamp in DAO " + e);
		throw e;
		}
	   
	   finally {
			 try{	    
				 if (dbUtility != null) {
						dbUtility.closeConnection();
			 }
				 }
			 catch (Exception e) {
			 logger.error("Exception at estamp in DAO  " + e);
			 }		
	       }
	 
	   return transactionflag;   
	 }

	 /**
		 * @param dto2 
	 * @param registration number, user id, office id
		 * @return boolean
		 * @throws Exception
		 */ 
	 public  boolean statusDeliveredUpdateP1 (String regNo, String uid, String offid, String fname, String mname, String lname, String docName, String docPath )throws Exception{
	     boolean transactionflag = false;
	     String detl[]    = new String[3];
	     detl[0]=uid;
	     detl[1]=offid;
	     detl[2]=regNo;
	     String rec[]=new String[8];
	     String[] paydetl=new String[2];
	     paydetl[0]=uid;
	     paydetl[1]=regNo;
	     ArrayList<String> list=new ArrayList<String>();
	     ArrayList statusList = new ArrayList();
	     String temp[] = new String[1];
				temp[0]=regNo;
	     DBUtility dbUtility = null;
	   try{
		   dbUtility = new DBUtility();
		   dbUtility.setAutoCommit(true);
	  
		   dbUtility.createPreparedStatement(DeliveryOfDocumentsSQL.DELIVERED_STATUS_UPDATE1);
		   transactionflag=dbUtility.executeUpdate(detl);
		  if(transactionflag){
			   String sql=DeliveryOfDocumentsSQL.GET_DEL_DOC_RECPNT_ID_SEQ;
			   dbUtility.createStatement();
			   String number = dbUtility.executeQry(sql);
			   rec[0]=number;
			   rec[1]=regNo;
			   rec[2]=fname;
			   rec[3]=mname;
			   rec[4]=lname;
			   rec[5]=uid;
			   rec[6]=docName;
			   rec[7]=docPath;
			   dbUtility.createPreparedStatement(DeliveryOfDocumentsSQL.RECPNT_INSERT1);
			   transactionflag=dbUtility.executeUpdate(rec); 
			   if(transactionflag){
			   dbUtility.createPreparedStatement(DeliveryOfDocumentsSQL.PAY_FLAG_UPDTAE);
			   transactionflag=dbUtility.executeUpdate(paydetl); 
		 
		   if(transactionflag){
	        dbUtility.commit();	
			//Start : Changes added by Rupali for QMS
	        String query1=DeliveryOfDocumentsSQL.GET_STATUS_DELIVERED;
		       dbUtility.createPreparedStatement(query1);
		       statusList = dbUtility.executeQuery(temp);
		       if(statusList.size()>0){
		    	
		    		   for(int i=0;i<statusList.size();i++){
		    			   ArrayList ret=(ArrayList)statusList.get(i);
		    			
		    			   String status = (String)ret.get(0);
				    	   String regInitId=(String)ret.get(1);
				    	   if("7".equalsIgnoreCase(status))
				   			{
				   			EtokenChange eChange = new EtokenChange(regInitId,status,null);
				   			Thread t = new Thread(eChange);
				   			t.start();
				   			}
		    		   }
		    	   
		    	
		    		   }
				//End : Changes added by Rupali for QMS
		   }
	 }}}catch (NullPointerException ne) {
		    ne.printStackTrace();
		    dbUtility.rollback();
		   throw ne;
			
	   }
		    catch (SQLException se) {
		    	 se.printStackTrace();
		    	 dbUtility.rollback();
		    	
			logger.error("SQL Exception at estamp  in DAO " + se); 
		 throw se;
		}
	   catch(Exception e){
		   e.printStackTrace();
		   dbUtility.rollback();
		 logger.error(" Exception at estamp in DAO " + e);
		throw e;
		}
	   
	   finally {
			 try{	    
				 if (dbUtility != null) {
						dbUtility.closeConnection();
			 }
				 }
			 catch (Exception e) {
			 logger.error("Exception at estamp in DAO  " + e);
			 }		
	       }
	 
	   return transactionflag;   
	 }
	 
	 /**
		 * @param _retFunId
		 * @param _serId
		 * @param _userType
		 * @return
		 * @throws Exception
		 */
		public ArrayList getfee(String _retFunId, String _serId,
				String _userType) throws Exception {
			ArrayList othersFeeDuty = new ArrayList();
			DBUtility dbUtility = null;
			try {
				dbUtility = new DBUtility();
				clstmt = dbUtility
						.returnCallableStatement(DeliveryOfDocumentsSQL.SERVICE_FEE_PROCEDURE);
				clstmt.setString(1, _retFunId);
				clstmt.setString(2, _serId);
				clstmt.setString(3, _userType);
				clstmt.registerOutParameter(4, OracleTypes.NUMBER);
				clstmt.registerOutParameter(5, OracleTypes.CLOB);
				clstmt.registerOutParameter(6, OracleTypes.VARCHAR);
				if (!clstmt.execute()) {
					int temp = clstmt.getInt(4);
					othersFeeDuty = new ArrayList();
					String serviceFeeStr = String.valueOf(temp);
					double totalFee = Double.parseDouble(serviceFeeStr);
					double fee = 0.0;
					double otherFee = 0.0;
					String SerVal = clstmt.getString(5);
					String[] serTokenOne = SerVal.split(";");
					if (serTokenOne.length > 0) {
						for (int i = 1; i < serTokenOne.length; i++) {
							String[] serTokenTwo = serTokenOne[i].split(",");
							if (serTokenTwo.length > 0) {
								if (i == 1) {
									fee = Double.parseDouble(serTokenTwo[4]);
								}
							}
						}
					}
					othersFeeDuty.add("" + fee);
					otherFee = totalFee - fee;
					othersFeeDuty.add("" + otherFee);
					othersFeeDuty.add("" + totalFee);

				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e);
			}  finally {
				 try{	    
					 if (dbUtility != null) {
							dbUtility.closeConnection();
				 }
					 }
				 catch (Exception e) {
				 logger.error("Exception at estamp in DAO  " + e);
				 }		
		       }
			System.out.println("othersFeeDuty:-" + othersFeeDuty);
			return othersFeeDuty;
		}
		
		
		 /**
		 * @param regcomp no
		 *
		 * @return
		 * @throws Exception
		 */
		 public ArrayList getPayDtls(String txnId) throws Exception{
				String[] tid = new String[2];
				tid[0]=txnId;
				tid[1]=txnId;
				ArrayList list = new ArrayList();
				DBUtility dbUtility = null;
				try{
					dbUtility = new DBUtility();
				
				dbUtility.createPreparedStatement(DeliveryOfDocumentsSQL.GET_PAY_DTLS);
				list=dbUtility.executeQuery(tid);
				list.trimToSize();
				} catch(Exception e){
					   e.printStackTrace();
					   dbUtility.rollback();
					logger.error(" Exception at estamp in DAO " + e);
					throw e;
					} finally {
						 try{	    
							 if (dbUtility != null) {
									dbUtility.closeConnection();
						 }
							 }
						 catch (Exception e) {
						 logger.error("Exception at estamp in DAO  " + e);
						 }		
				       }
				return list;
			}
		 
		 
		 /**
			 * @param regcomp no
			 *
			 * @return
			 * @throws Exception
			 */
			 public ArrayList getmodDetls(String txnId) throws Exception{
					String[] tid = new String[1];
					tid[0]=txnId;
					ArrayList list = new ArrayList();
					DBUtility dbUtility = null;
					try{
						dbUtility = new DBUtility();
					
					dbUtility.createPreparedStatement(DeliveryOfDocumentsSQL.MODE_DETL);
					list=dbUtility.executeQuery(tid);
					list.trimToSize();
					} catch(Exception e){
						   e.printStackTrace();
						   dbUtility.rollback();
						logger.error(" Exception at estamp in DAO " + e);
						throw e;
						} finally {
							 try{	    
								 if (dbUtility != null) {
										dbUtility.closeConnection();
							 }
								 }
							 catch (Exception e) {
							 logger.error("Exception at estamp in DAO  " + e);
							 }		
					       }
					return list;
				}
		 
		 
		 /******************************************************************  
		  *   Method Name  :   insertPay()
		  *   Arguments    :  reg no, fee, uid 
		  *   Return       :   Boolean
		  *   Throws 	  :   NullPointer  or SQLException or Exception
		*******************************************************************/  

		public  boolean insertPay(String regno, String fee, String uid,String id)throws NullPointerException,
		SQLException,Exception{
		 
		boolean transactionflag = false;
		//String estmTxnId = null;
		String paytable[] = new String[5];
		DBUtility dbUtility = null;
		try{
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(true);
		
		paytable[0] =id;
		paytable[1] =regno;
		paytable[2] =fee;
		paytable[3] ="I";
		paytable[4] =uid;

		dbUtility.createPreparedStatement(DeliveryOfDocumentsSQL.PAY_TABLE_INSERT);
		transactionflag=dbUtility.executeUpdate(paytable);
		 
		 if(transactionflag){
		  dbUtility.commit();				     
		 }
		}catch (NullPointerException ne) {
		    ne.printStackTrace();
		    dbUtility.rollback();
		   throw ne;
			
		}
		    catch (SQLException se) {
		    	 se.printStackTrace();
		    	 dbUtility.rollback();
		    	
			logger.error("SQL Exception at PaymentTransactionFinal  in DAO " + se); 
		 throw se;
		}
		catch(Exception e){
		   e.printStackTrace();
		   dbUtility.rollback();
		logger.error(" Exception at PaymentTransactionFinal in DAO " + e);
		throw e;
		}

		 finally {
			 try{	    
				 if (dbUtility != null) {
					dbUtility.closeConnection();
			 }
				 }
			 catch (Exception e) {
			 logger.error("Exception at estamp in DAO  " + e);
			 }		
	       }
		
		return transactionflag;   
		}
		
		
		
		 /******************************************************************  
		  *   Method Name  :   getrequestDetails()
		  *   Arguments    :   office id
		  *   Return       :   ArrayList
		  *   Throws 	  :   NullPointer  or SQLException or Exception
		*******************************************************************/  
		public ArrayList getrequestDetails(String txnId) throws Exception{
			String[] tid = new String[1];
			tid[0]=txnId;
			ArrayList list = new ArrayList();
			DBUtility dbUtility = null;
			try{
				dbUtility = new DBUtility();
				dbUtility.createPreparedStatement(DeliveryOfDocumentsSQL.REQ_DETL_QRY);
			list=dbUtility.executeQuery(tid);
			list.trimToSize();
			} catch(Exception e){
				   e.printStackTrace();
				   dbUtility.rollback();
				logger.error(" Exception in getPayDtls " + e);
				throw e;
				} finally {
					 try{	    
						 if (dbUtility != null) {
							 dbUtility.closeConnection();
					 }
						 }
					 catch (Exception e) {
					 logger.error("Exception in closing connection  " + e);
					 }		
			       }
			return list;
		}
		
		
	
		 /******************************************************************  
		  *   Method Name  :   getId()
		  *   Arguments    :  reg no, fee, uid 
		  *   Return       :   Boolean
		  *   Throws 	  :   NullPointer  or SQLException or Exception
		*******************************************************************/  
		public  String getId()throws NullPointerException,
		SQLException,Exception{
			String number1="";
			DBUtility dbUtility = null;
			try{
				dbUtility = new DBUtility();
				String SQL1 = DeliveryOfDocumentsSQL.GET_DEL_PAYMENT_ID_SEQ;
			    dbUtility.createStatement();
			    number1 = dbUtility.executeQry(SQL1);
			
			}catch (NullPointerException ne) {
			    ne.printStackTrace();
			    dbUtility.rollback();
			   throw ne;
				
			}
			    catch (SQLException se) {
			    	 se.printStackTrace();
			    	 dbUtility.rollback();
			    	
				logger.error("SQL Exception at PaymentTransactionFinal  in DAO " + se); 
			 throw se;
			}
			catch(Exception e){
			   e.printStackTrace();
			   dbUtility.rollback();
			logger.error(" Exception at PaymentTransactionFinal in DAO " + e);
			throw e;
			}

			 finally {
				 try{	    
					 if (dbUtility != null) {
						dbUtility.closeConnection();
				 }
					 }
				 catch (Exception e) {
				 logger.error("Exception at estamp in DAO  " + e);
				 }		
		       }
			
			
			return number1;
			
		}
		
		
		public String getMainId(String txnId) throws Exception{
			String[] tid = new String[1];
			tid[0]=txnId;
			String dty;
			DBUtility dbUtility = null;
			try{dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(DeliveryOfDocumentsSQL.GET_MAIN_ID);
			dty=dbUtility.executeQry(tid);
			}catch(Exception e){
				   e.printStackTrace();
				   dbUtility.rollback();
				logger.error(" Exception at PaymentTransactionFinal in DAO " + e);
				throw e;
				}

				 finally {
					 try{	    
						 if (dbUtility != null) {
							dbUtility.closeConnection();
					 }
						 }
					 catch (Exception e) {
					 logger.error("Exception at estamp in DAO  " + e);
					 }		
			       }
			return dty;
		}
	
		// added by SIMRAN
		
		public String getOffcTypeDetl(String offId) throws Exception{
			 
			String[] regDtl = {offId};
			String offcType= "";
			DBUtility dbUtility = null;
			try{
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(DeliveryOfDocumentsSQL.GET_OFFC_TYPE_ID);
			offcType=dbUtility.executeQry(regDtl);
			}catch (Exception x) {
				logger.debug(x);
			}finally {
				if (dbUtility != null) {
					dbUtility.closeConnection();
				}
			}
			
			return offcType;
		}
		
		public ArrayList getChildOffcDetl(String offId) throws Exception{
			 
			String[] regDtl = {offId};
			ArrayList list = new ArrayList();
			DBUtility dbUtility = null;
			try{
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(DeliveryOfDocumentsSQL.GET_CHILD_OFFICE);
			list=dbUtility.executeQuery(regDtl);
			}catch (Exception x) {
				logger.debug(x);
			}finally {
				if (dbUtility != null) {
					dbUtility.closeConnection();
				}
			}
			
			return list;
		}
		
		
		public String getCountryDetls(String partyTxnId) throws Exception{
			String[] tid = new String[1];
			tid[0]=partyTxnId;
			String countryName = "";
			DBUtility dbUtility = null;
			try{
				dbUtility = new DBUtility();
				dbUtility.createPreparedStatement(DeliveryOfDocumentsSQL.GET_COUNTRY_DETLS);
				countryName=dbUtility.executeQry(tid);
	          }  catch (Exception x) {
					logger.debug(x);
				} finally {
					if (dbUtility != null) {
						dbUtility.closeConnection();
					}
				}
			return countryName;
		}
		
		public ArrayList getPrintStatusDetail(String regno) throws Exception{
			
			
			String[] regDtl = {regno};
			ArrayList list = new ArrayList();
			DBUtility dbUtility = null;
			try{
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(DeliveryOfDocumentsSQL.GET_PRINT_STATUS);
			list=dbUtility.executeQuery(regDtl);
			}catch (Exception x) {
				logger.debug(x);
			}finally {
				if (dbUtility != null) {
					dbUtility.closeConnection();
				}
			}
			
			return list;
		}
		
		
		public String getStateDetls(String partyTxnId) throws Exception{
			String[] tid = new String[1];
			tid[0]=partyTxnId;
			String stateName = "";
			DBUtility dbUtility = null;
			try{
				dbUtility = new DBUtility();
				dbUtility.createPreparedStatement(DeliveryOfDocumentsSQL.GET_STATE_DETLS);
				stateName=dbUtility.executeQry(tid);
	          }  catch (Exception x) {
					logger.debug(x);
				} finally {
					if (dbUtility != null) {
						dbUtility.closeConnection();
					}
				}
			return stateName;
		}
		
		public String getDistrictDetls(String partyTxnId) throws Exception{
			String[] tid = new String[1];
			tid[0]=partyTxnId;
			String disttName = "";
			DBUtility dbUtility = null;
			try{
				dbUtility = new DBUtility();
				dbUtility.createPreparedStatement(DeliveryOfDocumentsSQL.GET_DISTRICT_DETLS);
				disttName=dbUtility.executeQry(tid);
	          }  catch (Exception x) {
					logger.debug(x);
				} finally {
					if (dbUtility != null) {
						dbUtility.closeConnection();
					}
				}
			return disttName;
		}
		
		 /**
		 * @param dto2 
	 * @param registration number, user id, office id
		 * @return boolean
		 * @throws Exception
		 */ 
	 public  boolean statusPrintedUpdate (String regNo, String uid, String offid)throws Exception{
	     boolean transactionflag = false;
	     String detl[]    = new String[3];
	     detl[0]=uid;
	     detl[1]=offid;
	     detl[2]=regNo;
	     DBUtility dbUtility = null;	     
	   try{
		   dbUtility = new DBUtility();
		   dbUtility.setAutoCommit(true);
	  
		   dbUtility.createPreparedStatement(DeliveryOfDocumentsSQL.PRINTED_STATUS_UPDATE1);
		   transactionflag=dbUtility.executeUpdate(detl);
		  }catch(Exception e){
		   e.printStackTrace();
		   dbUtility.rollback();
		 logger.error(" Exception at statusPrintedUpdate in DAO " + e);
		throw e;
		}
	   
	   finally {
			 try{	    
				 if (dbUtility != null) {
						dbUtility.closeConnection();
			 }
				 }
			 catch (Exception e) {
			 logger.error("Exception at estamp in DAO  " + e);
			 }		
	       }
	 
	   return transactionflag;   
	 }
	 
	 
	// added by SHREERAJ
		
		public String getRegTxnID(String eRegNo) throws Exception{
			 
			String[] regDtl = {eRegNo};
			String offcType= "";
			DBUtility dbUtility = null;
			try{
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(DeliveryOfDocumentsSQL.GET_REG_TXN_ID);
			offcType=dbUtility.executeQry(regDtl);
			}catch (Exception x) {
				logger.debug(x);
			}finally {
				if (dbUtility != null) {
					dbUtility.closeConnection();
				}
			}
			
			return offcType;
		}
		public String getDeedID(String propID) throws Exception{
			 
			String[] regDtl = {propID};
			String offcType= "";
			DBUtility dbUtility = null;
			try{
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(DeliveryOfDocumentsSQL.GET_DEED_ID);
			offcType=dbUtility.executeQry(regDtl);
			}catch (Exception x) {
				logger.debug(x);
			}finally {
				if (dbUtility != null) {
					dbUtility.closeConnection();
				}
			}
			
			return offcType;
		}
		public String getRegTxnIDLink(String propID) throws Exception{
			 
			String[] regDtl = {propID};
			String offcType= "";
			DBUtility dbUtility = null;
			try{
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(DeliveryOfDocumentsSQL.GET_REG_TXN_PROP_ID);
			offcType=dbUtility.executeQry(regDtl);
			}catch (Exception x) {
				logger.debug(x);
			}finally {
				if (dbUtility != null) {
					dbUtility.closeConnection();
				}
			}
			
			return offcType;
		}
		
		 /**
		 * @param dto2 
	 * @param registration number, user id, office id
		 * @return boolean
		 * @throws Exception
		 */ 
	 public  boolean statusUndeliveredUpdate (String regNo, String uid, String offid)throws Exception{
	     boolean transactionflag = false;
	     String detl[]    = new String[3];
	     detl[0]=uid;
	     detl[1]=offid;
	     detl[2]=regNo;
	     DBUtility dbUtility = null;	     
	   try{
		   dbUtility = new DBUtility();
		   dbUtility.setAutoCommit(true);
	  
		   dbUtility.createPreparedStatement(DeliveryOfDocumentsSQL.UNDELIVERED_STATUS_UPDATE1);
		   transactionflag=dbUtility.executeUpdate(detl);
		  }catch(Exception e){
		   e.printStackTrace();
		   dbUtility.rollback();
		 logger.error(" Exception at statusUndeliveredUpdate in DAO " + e);
		throw e;
		}
	   
	   finally {
			 try{	    
				 if (dbUtility != null) {
						dbUtility.closeConnection();
			 }
				 }
			 catch (Exception e) {
			 logger.error("Exception at estamp in DAO  " + e);
			 }		
	       }
	 
	   return transactionflag;   
	 }
	 
	 public  boolean signatureTimeUpdate (String regNo)throws Exception{
	     boolean transactionflag = false;
	     String detl[]    = new String[1];
	     detl[0]=regNo.trim();
	     DBUtility dbUtility = null;	     
	   try{
		   dbUtility = new DBUtility();
		   dbUtility.setAutoCommit(true);
	  
		   dbUtility.createPreparedStatement(DeliveryOfDocumentsSQL.SIGNATURE_TIME_UPDATE);
		   transactionflag=dbUtility.executeUpdate(detl);
		  }catch(Exception e){
		   e.printStackTrace();
		   dbUtility.rollback();
		 logger.error(" Exception at statusUndeliveredUpdate in DAO " + e);
		throw e;
		}
	   
	   finally {
			 try{	    
				 if (dbUtility != null) {
						dbUtility.closeConnection();
			 }
				 }
			 catch (Exception e) {
			 logger.error("Exception at estamp in DAO  " + e);
			 }		
	       }
	 
	   return transactionflag;   
	 }
	 
	 
	 /**
		 * @param dto2 
	 * @param registration number,deed, user id, office id
		 * @return boolean
		 * @throws Exception
		 */ 
	 public  boolean insertPinToSms (String regTxnID, String deed, DeliveryOfDocumentsDTO dto ,String userId)throws Exception{
	     boolean transactionflag = false;
	     String detl[]    = null;
	    
	    
	     int deedID=Integer.parseInt(dto.getDeedID());
	     String sql="";
	     
	     
	 	if(deedID==RegInitConstant.DEED_PARTITION_PV||deedID==RegInitConstant.DEED_TRUST_PV
				||deedID==RegInitConstant.DEED_RECONV_MORTGAGE_NPV||deedID==RegInitConstant.DEED_PARTNERSHIP_NPV
				||deedID==RegInitConstant.DEED_DEPOSIT_OF_TITLE){
	 		detl =new String[2];
	 				sql=DeliveryOfDocumentsSQL.INSERT_SMS_CLAIMANT_TRUST;
			
			
		}else if(deedID==RegInitConstant.DEED_EXCHANGE){
			detl =new String[3];
					sql=DeliveryOfDocumentsSQL.INSERT_SMS_CLAIMANT_EXCHANGE;
					 detl[2]=regTxnID;
					
		}else{
			detl =new String[2];
					sql=DeliveryOfDocumentsSQL.INSERT_SMS_CLAIMANT_CONVEYANCE;
		}
	 	DBUtility dbUtility = null;	     
	   try{
		   detl[0]=userId;
		   dbUtility = new DBUtility();
		   dbUtility.setAutoCommit(true);
		   String[] regDtl = {regTxnID};
		   String sqlprop=DeliveryOfDocumentsSQL.GET_PIN_PROPERTY_ID;
		   dbUtility.createPreparedStatement(sqlprop);
		  ArrayList list=dbUtility.executeQuery(regDtl);
		  if(list.size()>0){
		   for(int i=0;i<list.size();i++){
			   ArrayList ret=(ArrayList)list.get(i);
			   String propertyID=(String)ret.get(0);
			   detl[1]=propertyID;
			   dbUtility.createPreparedStatement(sql);
			   transactionflag=dbUtility.executeUpdate(detl);
			   logger.debug("PIN SENT TO THE PARTY FOR PROPERTY ID-------->"+propertyID);
		   }
		 
		  }else{
			  logger.debug("NO PROPERTY FOR THIS REG INIT");
		  }
		 
		
		  }catch(Exception e){
		   e.printStackTrace();
		   dbUtility.rollback();
		 logger.error(" Exception at insertPinToSms in DAO " + e);
		throw e;
		}
	   
	   finally {
			 try{	    
				 if (dbUtility != null) {
						dbUtility.closeConnection();
			 }
				 }
			 catch (Exception e) {
			 logger.error("Exception at estamp in DAO  " + e);
			 }		
	       }
	 
	   return transactionflag;   
	 }
	 
	 public boolean updatePinLinking(String regTxnID, String deed, DeliveryOfDocumentsDTO dto ,String userId)
	 {
		  boolean transactionflag = false;
		String detls[]=new String[4];
		DBUtility dbUtility = null;
		try{
				ArrayList list=viewClaimantDelivery(regTxnID,deed,dto,userId);
				if(list.size()>0){
					
					for(int i=0;i<list.size();i++){
						ArrayList sList=(ArrayList)list.get(i);
						ArrayList detlList=(ArrayList)sList.get(0);
						String prop=detlList.get(0).toString();
						String mob=(String)detlList.get(2);
						if(mob==null ||"".equalsIgnoreCase(mob)){
							continue;
						}
						 
						   dbUtility = new DBUtility();
						detls[0]=mob;
						detls[1]=userId;
						detls[2]=prop;
						detls[3]=prop;
						 String sqlprop=DeliveryOfDocumentsSQL.INSERT_SMS_DOD_HAND;
						  dbUtility.createPreparedStatement(sqlprop);
						   transactionflag=dbUtility.executeUpdate(detls);
						   logger.debug("PIN SENT TO THE PARTY FOR PROPERTY ID-------->"+prop);
					}
				}
		 }catch(Exception e){
		   e.printStackTrace();
		 
		 logger.error(" Exception at insertPinToSms in DAO " + e);
		 
		}
	   
	   finally {
			 try{	    
				 if (dbUtility != null) {
						dbUtility.closeConnection();
			 }
				 }
			 catch (Exception e) {
			 logger.error("Exception at estamp in DAO  " + e);
			 }		
	       }
	 
	   return transactionflag;   
	 }
	 
	 public String getSqlDOD(int deedID) throws Exception{
		 String sql="";
		 	if(deedID==RegInitConstant.DEED_PARTITION_PV||deedID==RegInitConstant.DEED_TRUST_PV
					||deedID==RegInitConstant.DEED_RECONV_MORTGAGE_NPV||deedID==RegInitConstant.DEED_PARTNERSHIP_NPV
					||deedID==RegInitConstant.DEED_DEPOSIT_OF_TITLE){
		 		//detl =new String[1];
		 				sql=DeliveryOfDocumentsSQL.VIEW_CLAIMANI_DOD_TRUST;
				
				
			}else if(deedID==RegInitConstant.DEED_EXCHANGE){
				//detl =new String[2];
						sql=DeliveryOfDocumentsSQL.VIEW_CLAIMANI_DOD_EXCHANGE;
					//	 detl[1]=regTxnID;
						
			}else{
			//	detl =new String[1];
						sql=DeliveryOfDocumentsSQL.VIEW_CLAIMANI_DOD_CONVEYANCE;
			}
		 	return sql;
		 	
	 }
	 
	 

	 public ArrayList viewClaimantDelivery(String regTxnID, String deed, DeliveryOfDocumentsDTO dto ,String userId) throws Exception{
		 
		  int deedID=Integer.parseInt(dto.getDeedID());
		  		String detl[]    = null;
		     //String sql="";
		     
		   /*  
		 	if(deedID==RegInitConstant.DEED_PARTITION_PV||deedID==RegInitConstant.DEED_TRUST_PV
					||deedID==RegInitConstant.DEED_RECONV_MORTGAGE_NPV||deedID==RegInitConstant.DEED_PARTNERSHIP_NPV
					||deedID==RegInitConstant.DEED_DEPOSIT_OF_TITLE){
		 		detl =new String[1];
		 				sql=DeliveryOfDocumentsSQL.VIEW_CLAIMANI_DOD_TRUST;
				
				
			}else if(deedID==RegInitConstant.DEED_EXCHANGE){
				detl =new String[2];
						sql=DeliveryOfDocumentsSQL.VIEW_CLAIMANI_DOD_EXCHANGE;
						 detl[1]=regTxnID;
						
			}else{
				detl =new String[1];
						sql=DeliveryOfDocumentsSQL.VIEW_CLAIMANI_DOD_CONVEYANCE;
			}*/
		    
		   // String sql= getSqlDOD(deedID);
		 	
			ArrayList finalList = new ArrayList();
			ArrayList propList=new ArrayList();
			DBUtility dbUtility = null;
			try{
				   dbUtility = new DBUtility();
				  
				   dbUtility.setAutoCommit(true);
				   String[] regDtl = {regTxnID};
				   String sqlprop=DeliveryOfDocumentsSQL.GET_PIN_PROPERTY_ID;
				   String sqlpropLink=DeliveryOfDocumentsSQL.GET_PIN_PROPERTY_ID_LINKING;
				   dbUtility.createPreparedStatement(sqlprop);
				 
				  ArrayList list=dbUtility.executeQuery(regDtl);
				  dbUtility.closeConnection();
				   dbUtility = new DBUtility();
				  dbUtility.createPreparedStatement(sqlpropLink);
				  ArrayList listLink=dbUtility.executeQuery(regDtl);
				  dbUtility.closeConnection();
				  if(list.size()>0){
					   for(int i=0;i<list.size();i++){
						   ArrayList ret=(ArrayList)list.get(i);
						   String propertyID=(String)ret.get(0);
						   propList.add(propertyID);
					   }
				  }
				  if(listLink.size()>0){
					   for(int i=0;i<listLink.size();i++){
						   ArrayList ret=(ArrayList)listLink.get(i);
						   String propertyID=(String)ret.get(0);
						   propList.add(propertyID);
					   }
				  }
				  
				  if(propList.size()>0){
				   for(int i=0;i<propList.size();i++){
					   String propertyID=(String)propList.get(i);
					  
					 
					   String deedId=getDeedID(propertyID);
					  String regInit=getRegTxnIDLink(propertyID);
					   if(deedId.equalsIgnoreCase(RegInitConstant.DEED_EXCHANGE_STRING))
					   {
						   detl =new String[2];
						   detl[1]=regInit;
						   detl[0]=propertyID;
					   }
					   else
					   {
						   detl =new String[1];
						   detl[0]=propertyID;
					   }
					   
					   String sql= getSqlDOD(Integer.parseInt(deedId));
					   dbUtility = new DBUtility();
					   dbUtility.createPreparedStatement(sql);
					   ArrayList lst=dbUtility.executeQuery(detl);
					   dbUtility.closeConnection();
					   finalList.add(lst);
					   logger.debug("CLAIMANT FOR THE PROPERTY-------->"+propertyID);
				   }
				 
				  }else{
					  logger.debug("NO PROPERTY FOR THIS REG INIT");
				  }
				  
				}catch (Exception x) {
				logger.debug(x);
			}finally {
				if (dbUtility != null) {
					dbUtility.closeConnection();
				}
			}
			
			return finalList;
		}
	 
	 /**
		 * @param dto2 
	 * @param registration number,deed, user id, office id
		 * @return boolean
		 * @throws Exception
		 */ 
	 public  boolean insertPinToSmsHand (String regno, String deedId, DeliveryOfDocumentsDTO dto,String userId ,String [] arr)throws Exception{
	     boolean transactionflag = false;
	     String detl[]    = new String[4];
	    ArrayList propertyList=dto.getClaimantList();
	    logger.debug("mobile NUMBERS OF CLAIMANTS ARE------>"+arr);
	    DBUtility dbUtility = null;
	    try{
	    	dbUtility = new DBUtility();
			   dbUtility.setAutoCommit(true);
	    String 	sql=DeliveryOfDocumentsSQL.INSERT_SMS_DOD_HAND;
	    for(int i=0;i<propertyList.size();i++){
	    	//ArrayList lst=(ArrayList)propertyList.get(i);
	    	String mobNo="";
	    	DeliveryOfDocumentsDTO dodto=(DeliveryOfDocumentsDTO)propertyList.get(i);
			  String propertyID=dodto.getPropertyID();
			  if(arr!=null){
				  mobNo=arr[i]; 
			  }
			  
			  if(mobNo==null)
				  mobNo="";
			  if("".equalsIgnoreCase(mobNo))
				  continue;
			  detl[0]=mobNo;
			 
			  detl[1]=userId;
			  detl[2]=propertyID;
			  detl[3]=propertyID;
			   dbUtility.createPreparedStatement(sql);
			   transactionflag=dbUtility.executeUpdate(detl);
			   logger.debug("PIN INSERTED TO THE SMS TABLE FOR THE PARTY No. "+mobNo+" FOR PROPERTY ID-------->"+propertyID);
		   }
		 if(transactionflag){
			 
		  }else{
			  logger.debug("NO PROPERTY FOR THIS REG INIT");
		  }
	     int deedID=Integer.parseInt(dto.getDeedID());
	   
	    
	 }  catch(Exception e){
		   e.printStackTrace();
		   dbUtility.rollback();
		 logger.error(" Exception at insertPinToSms in DAO " + e);
		throw e;
		}
	   
	   finally {
			 try{	    
				 if (dbUtility != null) {
						dbUtility.closeConnection();
			 }
				 }
			 catch (Exception e) {
			 logger.error("Exception at estamp in DAO  " + e);
			 }		
	       }
	    return transactionflag;
	 }
	 
		// added by SHREERAJ
		
		public ArrayList getSignStatus() throws Exception{
			 
			ArrayList list=new ArrayList();
			DBUtility dbUtility = null;
			try{
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			String sqlQuery = DeliveryOfDocumentsSQL.GET_SIGNED_STATUS_NAME;
			list = dbUtility.executeQuery(sqlQuery);
			
			
			}catch (Exception x) {
				logger.debug(x);
			}finally {
				if (dbUtility != null) {
					dbUtility.closeConnection();
				}
			}
			
			return list;
		}
		
		public String getSignFilePath(String regComp) throws Exception{
			String[] tid = new String[1];
			tid[0]=regComp;
			String dty;
			DBUtility dbUtility = null;
			try{dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(DeliveryOfDocumentsSQL.GET_SIGN_FILE_PATH);
			dty=dbUtility.executeQry(tid);
			}catch(Exception e){
				   e.printStackTrace();
				   dbUtility.rollback();
				logger.error(" Exception at PaymentTransactionFinal in DAO " + e);
				throw e;
				}

				 finally {
					 try{	    
						 if (dbUtility != null) {
							dbUtility.closeConnection();
					 }
						 }
					 catch (Exception e) {
					 logger.error("Exception at estamp in DAO  " + e);
					 }		
			       }
			return dty;
		}
		public String getFee(String funId) throws Exception{
			String[] tid = new String[1];
			tid[0]=funId;
			String dty;
			DBUtility dbUtility = null;
			try{dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(DeliveryOfDocumentsSQL.GET_FEE);
			dty=dbUtility.executeQry(tid);
			}catch(Exception e){
				   e.printStackTrace();
				   dbUtility.rollback();
				logger.error(" Exception at getFee in DAO " + e);
				throw e;
				}

				 finally {
					 try{	    
						 if (dbUtility != null) {
							dbUtility.closeConnection();
					 }
						 }
					 catch (Exception e) {
					 logger.error("Exception at getFee in DAO  " + e);
					 }		
			       }
			return dty;
		}
		
		
		
		public String getcheckFlag(String regNumber) throws Exception{
			String[] tid = new String[1];
			tid[0]=regNumber;
			String dty;
			DBUtility dbUtility = null;
			try{dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(DeliveryOfDocumentsSQL.CHECK_PRINT_FLAG);
			dty=dbUtility.executeQry(tid);
			}catch(Exception e){
				   e.printStackTrace();
				   dbUtility.rollback();
				logger.error(" Exception at getFee in DAO " + e);
				throw e;
				}

				 finally {
					 try{	    
						 if (dbUtility != null) {
							dbUtility.closeConnection();
					 }
						 }
					 catch (Exception e) {
					 logger.error("Exception at getFee in DAO  " + e);
					 }		
			       }
			return dty;
		}
		
		//Start : Changes added by Neeti for RCMS
		
		public String checkDeedTypeForRCMS(String regTxnID) throws Exception {
			 
			String[] tid = new String[1];
			tid[0]=regTxnID;
			String deed;
			DBUtility dbUtility = null;
			try{
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(DeliveryOfDocumentsSQL.CHECK_DEED_TYPE);
			deed=dbUtility.executeQry(tid);
			}catch(Exception e){
				   e.printStackTrace();
				   dbUtility.rollback();
				logger.error(" Exception at checkDeedTypeForRCMS in DAO " + e);
				throw e;
				}
				 finally {
					 try{	    
						 if (dbUtility != null) {
							dbUtility.closeConnection();
					 }
						 }
					 catch (Exception e) {
					 logger.error("Exception at deliveryofdoc in DAO  " + e);
					 }		
			       }
			return deed;
			
		}
		
		public ArrayList checkPropertyForAgriLand(String regTxnID) throws Exception {
			String[] tid = new String[1];
			tid[0]=regTxnID;
			ArrayList list = new ArrayList();
			DBUtility dbUtility = null;
			try{
				dbUtility = new DBUtility();
				dbUtility.createPreparedStatement(DeliveryOfDocumentsSQL.CHECK_AGRI_LAND);
			    list=dbUtility.executeQuery(tid);
	          }  catch (Exception x) {
					logger.debug(x);
				} finally {
					if (dbUtility != null) {
						dbUtility.closeConnection();
					}
				}list.trimToSize();
			return list;
		
		}
		
		public String getRCMSFlag(String regTxnID) throws Exception {
			String[] tid = new String[1];
			tid[0]=regTxnID;
			String rcmsFlag;
			DBUtility dbUtility = null;
			try{
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(DeliveryOfDocumentsSQL.GET_RCMS_FLAG);
			rcmsFlag=dbUtility.executeQry(tid);
			}catch(Exception e){
				   e.printStackTrace();
				   dbUtility.rollback();
				logger.error(" Exception at getRCMSFlag in DAO " + e);
				throw e;
				}
				 finally {
					 try{	    
						 if (dbUtility != null) {
							dbUtility.closeConnection();
					 }
						 }
					 catch (Exception e) {
					 logger.error("Exception at getRCMSFlag in DAO  " + e);
					 }		
			       }
			return rcmsFlag;
		}
		
		public String getTehsilID(String regNum) throws Exception {
			String[] tid = new String[1];
			tid[0]=regNum;
			String tehsilID;
			DBUtility dbUtility = null;
			try{
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(DeliveryOfDocumentsSQL.GET_TEHSIL_CHECK);
			tehsilID=dbUtility.executeQry(tid);
			}catch(Exception e){
				   e.printStackTrace();
				   dbUtility.rollback();
				logger.error(" Exception at getTehsilID in DAO " + e);
				throw e;
				}
				 finally {
					 try{	    
						 if (dbUtility != null) {
							dbUtility.closeConnection();
					 }
						 }
					 catch (Exception e) {
					 logger.error("Exception at getTehsilID in DAO  " + e);
					 }		
			       }
			return tehsilID;
		}
		
		//End : Changes added by Neeti for RCMS
}
