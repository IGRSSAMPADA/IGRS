/**
 * 
 */
package com.wipro.igrs.revenueManagement.dao;

import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;
import java.util.TreeMap;


import org.apache.log4j.Logger;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.reginit.constant.RegInitConstant;
import com.wipro.igrs.revenueManagement.dto.RevMgtDTO;
import com.wipro.igrs.revenueManagement.sql.RevMgtSQL;
import com.wipro.igrs.serviceProvider.sql.ServiceProviderSQL;


/**
 * @author RA836784
 *
 */
public class RevMgtDAO {
	
	private Logger logger =(Logger)Logger.getLogger(RevMgtDAO.class);
	DBUtility dbUtil = null;
	String sql = null;
	ArrayList mainList = null;
	private CallableStatement clstmt;
	ArrayList subList2=new ArrayList();
	ArrayList list2=new ArrayList();
	Double sum=0.0;
	
	
	
	
	
	 /**
	 * @param dto 
	 * @param property lock id
	 * @return ArrayList
	 * @throws Exception
	 */
	
	//For Cash Receipt Search
	 
	 public ArrayList getFilteredresults2(String receiptTextBox, RevMgtDTO dto) throws Exception{
		 
		 String[]reg_compno =new String[1];
		
			
		//	reg_compno[0]=radioSelectReceipt;
			
		 	ArrayList list = new ArrayList();
			
		 	reg_compno[0]=receiptTextBox.toLowerCase();
						
			try{
				
				dbUtil = new DBUtility();
				//dbUtil.createPreparedStatement(RevMgtSQL.GET_CASH_DETAILS_RECEIPT_SEARCH);
				dbUtil.createPreparedStatement(RevMgtSQL.GET_CASH_DETAILS_RECEIPT_SEARCH_HINDI);
				list = dbUtil.executeQuery(reg_compno);
			
	//list=dbUtility.executeQuery(regDtl);
			}catch (Exception x) {
				logger.debug(x);
				throw x;
				
			}finally {
				 try{	    
					 if (dbUtil != null) {
						 dbUtil.closeConnection();
				 }
					 }
				 catch (Exception e) {
				 logger.error("Exception at closing connection  " + e);
				 }		
			}
			
			return list;
			//return list;
		}
	 
	 
	 // For Challan Receipt Search
	 
	
	 
	 public ArrayList getFilteredresults3(String receiptTextBox,String radioSelectReceipt,RevMgtDTO dto) throws Exception{
		 
		 String[]reg_compno =new String[2];
		
			
		//	reg_compno[0]=radioSelectReceipt;
			
		 	ArrayList list = new ArrayList();
			
		 	reg_compno[0]=receiptTextBox.toLowerCase();
		 	reg_compno[1]=radioSelectReceipt;
		 	
		 
		 	
						
			try{
				
				dbUtil = new DBUtility();
				//dbUtil.createPreparedStatement(RevMgtSQL.GET_CHALLAN_DETAILS_RECEIPT_SEARCH);
				dbUtil.createPreparedStatement(RevMgtSQL.GET_CHALLAN_DETAILS_RECEIPT_SEARCH_HINDI);
				list = dbUtil.executeQuery(reg_compno);
			
	//list=dbUtility.executeQuery(regDtl);
			}catch (Exception x) {
				logger.debug(x);
				throw x;
				
			}finally {
				 try{	    
					 if (dbUtil != null) {
						 dbUtil.closeConnection();
				 }
					 }
				 catch (Exception e) {
				 logger.error("Exception at closing connection  " + e);
				 }		
			}
			
			return list;
			//return list;
		}
	 
	 
	 
	 
	 
	 
 // For Challan Receipt Search
	 
	
	 
	 public ArrayList getFilteredresults4(String receiptTextBox,String radioSelectReceipt,RevMgtDTO dto) throws Exception{
		 
		 String[]reg_compno =new String[2];
		
			
		//	reg_compno[0]=radioSelectReceipt;
			
		 	ArrayList list = new ArrayList();
			
		 	reg_compno[0]=receiptTextBox;
		 	reg_compno[1]=radioSelectReceipt;
		 	
		 
		 	
						
			try{
				
				dbUtil = new DBUtility();
				//dbUtil.createPreparedStatement(RevMgtSQL.GET_ONLINE_DETAILS_RECEIPT_SEARCH);
				dbUtil.createPreparedStatement(RevMgtSQL.GET_ONLINE_DETAILS_RECEIPT_SEARCH_HINDI);
				list = dbUtil.executeQuery(reg_compno);
			
	//list=dbUtility.executeQuery(regDtl);
			}catch (Exception x) {
				logger.debug(x);
				throw x;
				
			}finally {
				 try{	    
					 if (dbUtil != null) {
						 dbUtil.closeConnection();
				 }
					 }
				 catch (Exception e) {
				 logger.error("Exception at closing connection  " + e);
				 }		
			}
			
			return list;
			//return list;
		}
	
	//for credit limit
	
	
	/**
	 * @param 
	 * @return ArrayList
	 * @throws Exception
	 */
	 /*public ArrayList getViewDetls(String paymentType,String fromDate, String toDate,String txnId ) throws Exception{
		 
		 
		 String[]reg_compno =new String[4];
			
			
			reg_compno[0]=paymentType;
			
			
			reg_compno[1]=fromDate;
			reg_compno[2]=toDate;
			reg_compno[3]=txnId;
			
			
			ArrayList list = new ArrayList();
			ArrayList subList = new ArrayList();
			ArrayList mainList = new ArrayList();
			String paymentTypeName= "Credit Limit";
			try{
			dbUtil = new DBUtility();
			RevMgtDTO rmdto = new RevMgtDTO();
			dbUtil.createPreparedStatement(RevMgtSQL.VIEW_DETLS_QRY);
			list = dbUtil.executeQuery(reg_compno);
			if(list.size()>0 && list !=null)
			{
				for(int i=0;i<list.size();i++)
				{
					subList= (ArrayList) list.get(i);
					

					rmdto = new RevMgtDTO();
					
					String a=(String)subList.get(0);
					System.out.println("a=============================================================="+a);
					rmdto.setTxnId((String)(subList.get(0)));
					rmdto.setTxnAmt((Double.parseDouble((String)subList.get(1))));
				    rmdto.setTxnDate((String)(subList.get(2)));
					rmdto.setReceiptId("-");
					String receiptId="-";

					Double txnAmt=(Double.parseDouble((String)subList.get(1)));
					
					
					rmdto.setViewComb((String)subList.get(0)+"~"+txnAmt+"~"+(String)subList.get(2)+"~"+paymentTypeName+"~"+(String)subList.get(4)+"~"+(String)subList.get(7)+"~"+(String)subList.get(8)+"~"+(String)subList.get(9)+"~"+receiptId);
					
			
					mainList.add(rmdto);
				}
				
			}
			
			//list=dbUtility.executeQuery(regDtl);
			}catch (Exception x) {
				logger.debug(x);
				throw x;
				
			}finally {
				 try{	    
					 if (dbUtil != null) {
						 dbUtil.closeConnection();
				 }
					 }
				 catch (Exception e) {
				 logger.error("Exception at closing connection  " + e);
				 }		
			}
			
			return mainList;
		}*/
 
	 
	 // For cash / challan / online


	public ArrayList getFilteredresults(String param[],String languageLocale) {
		
		
	    String sql = "";//RevMgtSQL.GET_SEARCH_DETAILS_QUERY;
	    
	    

		ArrayList retVal = new ArrayList();
		ArrayList queryResult;
		//StringBuilder stbr = new StringBuilder(RevMgtSQL.GET_SEARCH_DETAILS_QUERY);
		StringBuilder stbr = new StringBuilder(RevMgtSQL.GET_SEARCH_DETAILS_QUERY_HINDI);
		//DBUtility dbUtil;
	    //param[0] = _paymentType;
		//param[1] = _fromDate;
		//param[2] = _toDate;
		//param[3] = _txnId;
	
		
		TreeMap<String, String> paramsMap = new TreeMap<String, String>();
		try {
		
			stbr.append(RevMgtSQL.TRANSACTION_QUERY_PAYMENTID_CLAUSE);
			paramsMap.put("1paymentType", param[0]);
			
			if ("".equals(param[3]) == false) {
				stbr.append(RevMgtSQL.TRANSACTION_QUERY_TXNID_CLAUSE);
				paramsMap.put("2txnID", param[3]);
			}
			
			if (("".equals(param[1]) == false)
					&& ("".equals(param[2]) == false)) {
				stbr.append(RevMgtSQL.TRANSACTION_QUERY_DATE_BOTH_CLAUSE);
				
				
				paramsMap.put("3fromDate", param[1]);
				paramsMap.put("4toDate", param[2]);
			} 
			if ("".equals(param[4]) == false) {
				stbr.append(RevMgtSQL.TRANSACTION_QUERY_PAYMENT_PURPOSE_CLAUSE);
				paramsMap.put("5purposeID", param[4]);
			}
			
			
			
			
			stbr.append(RevMgtSQL.TRANSACTION_ORDER_BY_DATE_CLAUSE);
			
			logger.info("Final build query : " + stbr.toString());
			logger.info("Params mapping : " + paramsMap);
			try {
				dbUtil = new DBUtility();
				
				Collection<String> values = paramsMap.values();
				ArrayList<String> tmp = new ArrayList(values);
//				Collections.reverse(tmp);
				String[] params = tmp.toArray(new String[]{}); 
				logger.info("query params : " + params);
				queryResult = dbUtil.getSearchDetails1(stbr.toString(), params,languageLocale);
				retVal.addAll(queryResult);
				//dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}finally {
				 try{	    
					 if (dbUtil != null) {
						 dbUtil.closeConnection();
				 }
					 }
				 catch (Exception e) {
				 logger.error("Exception at closing connection  " + e);
				 }		
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
		return retVal;
	}
 
	
	
	 // For credit limit


		public ArrayList getFilteredresults1(String param[],String languageLocale) {
			
			
		    String sql = "";//RevMgtSQL.GET_SEARCH_DETAILS_QUERY;
		    
		    

			ArrayList queryResult = new ArrayList();
			//ArrayList queryResult;
			//StringBuilder stbr = new StringBuilder(RevMgtSQL.GET_SEARCH_DETAILS_QUERY1);
			StringBuilder stbr = new StringBuilder(RevMgtSQL.GET_SEARCH_DETAILS_QUERY1_HINDI);
			//DBUtility dbUtil;
		    //param[0] = _paymentType;
			//param[1] = _fromDate;
			//param[2] = _toDate;
			//param[3] = _txnId;
		
			
			//TreeMap<String, String> paramsMap = new TreeMap<String, String>();
			String searchFields[] = new String[6];
			try {
			
				stbr.append(RevMgtSQL.TRANSACTION_QUERY_PAYMENTID_CLAUSE);
				
				searchFields[0] = param[0];
				
				if ("".equals(param[3]) == false) {
					stbr.append(RevMgtSQL.TRANSACTION_QUERY_TXNID_CLAUSE);
					//paramsMap.put("2txnID", param[3]);
					searchFields[1] = param[3];
				}
				
				if (("".equals(param[1]) == false)
						&& ("".equals(param[2]) == false)) {
					stbr.append(RevMgtSQL.TRANSACTION_QUERY_DATE_BOTH_CLAUSE);
					
					
					/*paramsMap.put("3fromDate", param[1]);
					paramsMap.put("4toDate", param[2]);*/
					searchFields[2] = param[1];
					searchFields[3] = param[2];
				} 
				if ("".equals(param[4]) == false) {
					stbr.append(RevMgtSQL.TRANSACTION_QUERY_PAYMENT_PURPOSE_CLAUSE);
					//paramsMap.put("5purposeID", param[4]);
					searchFields[4] = param[4];
				}
				stbr.append(RevMgtSQL.TRANSACTION_QUERY_OFFICEID_CLAUSE);
				searchFields[5] = param[5];
				
				ArrayList<String> newVal = new ArrayList<String>();
				for(int i=0;i<searchFields.length;i++) {
					
					if(null!=searchFields[i])
						newVal.add(searchFields[i]);
					
				}
				
				stbr.append(RevMgtSQL.TRANSACTION_ORDER_BY_DATE_CLAUSE);
				
				logger.info("Final build query : " + stbr.toString());
				//logger.info("Params mapping : " + paramsMap);
				try {
					dbUtil = new DBUtility();
					
					/*Collection<String> values = paramsMap.values();
					ArrayList<String> tmp = new ArrayList(values);
//					Collections.reverse(tmp);
					String[] params = tmp.toArray(new String[]{}); 
					logger.info("query params : " + params);*/
					queryResult = dbUtil.getSearchDetails2(stbr.toString(), newVal.toArray(new String[0]),languageLocale);
					//retVal.addAll(queryResult);
					//dbUtil.closeConnection();
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}finally {
					 try{	    
						 if (dbUtil != null) {
							 dbUtil.closeConnection();
					 }
						 }
					 catch (Exception e) {
					 logger.error("Exception at closing connection  " + e);
					 }		
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
			return queryResult;
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
				dbUtil.createStatement();
				
				//dbUtil.createPreparedStatement(RevMgtSQL.OFFICE_NAME_DETAILS);
				
				dbUtil.createPreparedStatement(RevMgtSQL.OFFICE_NAME_DETAILS_HINDI);
				
				
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
				//list = dbUtil.executeQuery(RevMgtSQL.OFFICE_TYPE_DETAILS);
				list = dbUtil.executeQuery(RevMgtSQL.OFFICE_TYPE_DETAILS_HINDI);
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
				//list = dbUtil.executeQuery(RevMgtSQL.DISTRICT_QRY);
				list = dbUtil.executeQuery(RevMgtSQL.DISTRICT_QRY_HINDI);
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
		
	 
	 
		
		
		
		
		// For Cash Revenue Collection Report
		 
		
		 
		 public ArrayList getRevenueCollectionCash(String revenueDistrictId,String revenueOfficeTypeName,String revenueFromDate,String revenueToDate , RevMgtDTO dto) throws Exception{
			 
			 String[]reg_compno =new String[4];
			
				ArrayList list2=new ArrayList();
			//	reg_compno[0]=radioSelectReceipt;
				
			 	ArrayList list = new ArrayList();
				
			 	reg_compno[0]=revenueDistrictId;
			 	reg_compno[1]=revenueOfficeTypeName;
			 	reg_compno[2]=revenueFromDate;
			 	reg_compno[3]=revenueToDate;
			 	
			 
			 	
							
				try{
					
					dbUtil = new DBUtility();
					//RevMgtDTO dto1=new RevMgtDTO();
					//dbUtil.createPreparedStatement(RevMgtSQL.CASH_REVENUE_COLLECTION_REPORT_QRY);
					dbUtil.createPreparedStatement(RevMgtSQL.CASH_REVENUE_COLLECTION_REPORT_QRY_HINDI);
					list = dbUtil.executeQuery(reg_compno);
					
					 
						   dbUtil.createPreparedStatement(RevMgtSQL.SUM_QRY_CASH_REVENUE_COLLECTION);
						   list2=dbUtil.executeQuery(reg_compno);
					    
						   if(list2.size()>0 && list2 !=null)
							{
						    	for(int i=0; i<list2.size(); i++){
									subList2.add((ArrayList)list2.get(i));
									if(subList2 != null && subList2.size()>0){ 
											  for (int k=0; k< subList2.size(); k++){
												 ArrayList compSub = (ArrayList)subList2.get(k);  
												 dto.setSum((String)compSub.get(0));
												
														
												
												 }
									}
						    	}
							}
												 
												 //list=dbUtility.executeQuery(regDtl);
				}catch (Exception x) {
					logger.debug(x);
					throw x;
					
				}finally {
					 try{	    
						 if (dbUtil != null) {
							 dbUtil.closeConnection();
					 }
						 }
					 catch (Exception e) {
					 logger.error("Exception at closing connection  " + e);
					 }		
				}
				
				return list;
				//return list;
			}
		
		
		
		 
		// For Challan Revenue Collection Report
		 
			
		 
			 public ArrayList getRevenueCollectionChallan(String radioMode,String revenueDistrictId,String revenueOfficeTypeName,String revenueFromDate,String revenueToDate , RevMgtDTO dto,Locale locale,String sroName) throws Exception{
				 
				 String[]reg_compno =new String[5];
				
					
				//	reg_compno[0]=radioSelectReceipt;
					
				 	ArrayList list = new ArrayList();
					
				 	reg_compno[0]=revenueDistrictId;
				 	reg_compno[1]=revenueOfficeTypeName;
				 	reg_compno[2]=revenueFromDate;
				 	reg_compno[3]=revenueToDate;
				 	reg_compno[4]=radioMode;
				 
				 	
				 	
					try{
						
						dbUtil = new DBUtility();

					 	
					

							
						//dbUtil.createPreparedStatement(RevMgtSQL.CHALLAN_REVENUE_COLLECTION_REPORT_QRY);
						dbUtil.createPreparedStatement(RevMgtSQL.CHALLAN_REVENUE_COLLECTION_REPORT_QRY_HINDI);
						list = dbUtil.executeQuery(reg_compno);
						
						
						  // dbUtil.createPreparedStatement(RevMgtSQL.SUM_QRY_CHALLAN_REVENUE_COLLECTION);
						dbUtil.createPreparedStatement(RevMgtSQL.SUM_QRY_CHALLAN_REVENUE_COLLECTION);
						   list2=dbUtil.executeQuery(reg_compno);
					    
						  /* if(list2.size()>0 && list2 !=null)
							{
						    	for(int i=0; i<list2.size(); i++){
									subList2.add((ArrayList)list2.get(i));
									if(subList2 != null && subList2.size()>0){ 
											  for (int k=0; k< subList2.size(); k++){
												 ArrayList compSub = (ArrayList)subList2.get(k);  
												 dto.setSum((String)compSub.get(0));
												 
														
												
												 }
									}
						    	}
							}*/
						   System.out.println(list2.size());
						   if(list2.size()>0 && list2 !=null)
							{
							  ArrayList listNew=(ArrayList)list2.get(0);
							   dto.setSum((String)listNew.get(0));
							   dto.setSampadaSum((String)listNew.get(1));
							}
			//list=dbUtility.executeQuery(regDtl);
					}catch (Exception x) {
						logger.debug(x);
						throw x;
						
					}finally {
						 try{	    
							 if (dbUtil != null) {
								 dbUtil.closeConnection();
						 }
							 }
						 catch (Exception e) {
						 logger.error("Exception at closing connection  " + e);
						 }		
					}
					
					return list;
					//return list;
				}
			
			
			

			 public ArrayList getRevenueCollectionOnline(String radioMode,String revenueDistrictId,String revenueOfficeTypeName,String revenueFromDate,String revenueToDate , RevMgtDTO dto) throws Exception{
				 
				 String[]reg_compno =new String[5];
				
					
				//	reg_compno[0]=radioSelectReceipt;
					
				 	ArrayList list = new ArrayList();
					
				 	reg_compno[0]=revenueDistrictId;
				 	reg_compno[1]=revenueOfficeTypeName;
				 	reg_compno[2]=revenueFromDate;
				 	reg_compno[3]=revenueToDate;
				 	reg_compno[4]=radioMode;
				 
				 	
								
					try{
						
						dbUtil = new DBUtility();
						
						//dbUtil.createPreparedStatement(RevMgtSQL.ONLINE_REVENUE_COLLECTION_REPORT_QRY);
						dbUtil.createPreparedStatement(RevMgtSQL.ONLINE_REVENUE_COLLECTION_REPORT_QRY_HINDI);
						list = dbUtil.executeQuery(reg_compno);
						
						
						dbUtil.createPreparedStatement(RevMgtSQL.SUM_QRY_ONLINE_REVENUE_COLLECTION);
						   list2=dbUtil.executeQuery(reg_compno);
					    
						   if(list2.size()>0 && list2 !=null)
							{
						    	for(int i=0; i<list2.size(); i++){
									subList2.add((ArrayList)list2.get(i));
									if(subList2 != null && subList2.size()>0){ 
											  for (int k=0; k< subList2.size(); k++){
												 ArrayList compSub = (ArrayList)subList2.get(k);  
												 dto.setSum((String)compSub.get(0));
												
														
												
												 }
									}
						    	}
							}
							
					
			//list=dbUtility.executeQuery(regDtl);
					}catch (Exception x) {
						logger.debug(x);
						throw x;
						
					}finally {
						 try{	    
							 if (dbUtil != null) {
								 dbUtil.closeConnection();
						 }
							 }
						 catch (Exception e) {
						 logger.error("Exception at closing connection  " + e);
						 }		
					}
					
					return list;
					//return list;
				}
			
			
				/**
				 * for getting district name from db.
				 * @param 
				 * @return String
				 * @author ROOPAM
				 */
				public String getOfficeName(String ofcId,String languageLocale) throws Exception {

			       // int regTxnIdSeq = 0;
			        String name="";
			        
			        try {
			        	dbUtil = new DBUtility();
			        	//dbUtility = new DBUtility();
			        	/*dbUtility.createStatement();
			        	sql=RegCommonSQL.GET_DISTRICT_NAME+"'"+distId+"'";
			        	distname = dbUtility.executeQry(sql);*/
			        	
			        	String[] param={ofcId};
			        	if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
			        	sql=RevMgtSQL.OFFICE_NAME;
			        	}else{
			        		sql=RevMgtSQL.OFFICE_NAME_HINDI;	
			        	}
			        	dbUtil.createPreparedStatement(sql);
			        	name = dbUtil.executeQry(param);

			        } catch (Exception exception) {

			                System.out.println("Exception in getOfficeName" + exception);

			        }finally {
						 try{	    
							 if (dbUtil != null) {
								 dbUtil.closeConnection();
						 }
							 }
						 catch (Exception e) {
						 logger.error("Exception at closing connection  " + e);
						 }		
					}

			        return name;

			}		

				/******************************************************************  
				  *   Method Name  :   getPurposeList()
				  *   Arguments    :   -
				  *   Return       :   ArrayList
				  *   Throws 	  :   Exception
				  *   Author	   :  Shreeraj
				 *******************************************************************/ 
				public synchronized ArrayList getPurposeList() throws Exception {
					ArrayList list = new ArrayList();
					try {
						dbUtil = new DBUtility();
						dbUtil.createStatement();
						//list = dbUtil.executeQuery(RevMgtSQL.DISTRICT_QRY);
						list = dbUtil.executeQuery(RevMgtSQL.GET_PURPOSE_LIST);
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
				  *   Method Name  :   getRevenueCollectionCombined()
				  *   Arguments    :   -
				  *   Return       :   String[]
				  *   Throws 	  :   Exception
				  *   Author	   :  int
				 *******************************************************************/  
				 public double[] getRevenueCollectionCombined(String radioMode,String revenueDistrictId,String revenueOfficeTypeName,
						 String revenueFromDate,String revenueToDate ) throws Exception{
						 double arr[]=new double[4];
						 String paramCash[]={revenueDistrictId,revenueOfficeTypeName,revenueFromDate,revenueToDate};
						 String paramChallan[]={revenueDistrictId,revenueOfficeTypeName,revenueFromDate,revenueToDate,"2"};
						 String paramOnline[]={revenueDistrictId,revenueOfficeTypeName,revenueFromDate,revenueToDate,"3"};
					String sqlCash=RevMgtSQL.SUM_QRY_CASH_REVENUE_COLLECTION_COMBINE;
					String sqlChallan=RevMgtSQL.SUM_QRY_CHALLAN_REVENUE_COLLECTION_COMBINE;
					String sqlOnline=RevMgtSQL.SUM_QRY_ONLINE_REVENUE_COLLECTION_COMBINE;
					dbUtil = new DBUtility();
					dbUtil.createPreparedStatement(sqlCash);
					String totCash=dbUtil.executeQry(paramCash);
					
					dbUtil.createPreparedStatement(sqlChallan);
					String totChallan=dbUtil.executeQry(paramChallan);
					
					dbUtil.createPreparedStatement(sqlOnline);
					String totOnline=dbUtil.executeQry(paramOnline);
					
					
					
					
					
					
					arr[0]=(totCash==null || totCash=="")?0.0:Double.parseDouble(totCash);
					arr[1]=(totChallan==null || totChallan=="")?0.0:Double.parseDouble(totChallan);
					arr[2]=(totOnline==null || totOnline=="")?0.0:Double.parseDouble(totOnline);
					arr[3]=arr[0]+arr[1]+arr[2];	
					 return arr;
				 }
					
				// START | changes to fetch credit limit param value by santosh
					
					
					public String getCreditLimitParam() throws Exception {
						String judLedgerParam = "";
						DBUtility dbUtility = new DBUtility();
						try
						{
						dbUtility.createStatement();
						judLedgerParam = dbUtility.executeQry(RevMgtSQL.CREDITLIMITPARAM);
						}
						catch(Exception exception)
						{
							logger.info("Exception in getCreditLimitParam");
						}
						finally{
							try {
								dbUtility.closeConnection();
							} catch (Exception e) {
								logger.error("Service Provider Account DAO in dao start" + e.getStackTrace());
							}
						}
						return judLedgerParam;
					}
					
					
					// END | changes to fetch credit limit param value by santosh
					
					// START | changes to fetch challan param value by santosh
					
					
					public String getChallanParam() throws Exception {
						String judLedgerParam = "";
						DBUtility dbUtility = new DBUtility();
						try
						{
						dbUtility.createStatement();
						judLedgerParam = dbUtility.executeQry(RevMgtSQL.CHALLANPARAM);
						}
						catch(Exception exception)
						{
							logger.info("Exception in getChallanParam");
						}
						finally{
							try {
								dbUtility.closeConnection();
							} catch (Exception e) {
								logger.error("Service Provider Account DAO in dao start" + e.getStackTrace());
							}
						}
						return judLedgerParam;
					}
					
					
					// END | changes to fetch challan param value by santosh
		
		
	 
}














