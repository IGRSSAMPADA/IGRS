package com.wipro.igrs.propertylock.dao;


import java.sql.CallableStatement;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.documentsearch.sql.CommonSQL;
import com.wipro.igrs.noEncumbrance.sql.NoEncumSQL;
import com.wipro.igrs.propertylock.dto.PropertyLockDTO;
import com.wipro.igrs.propertylock.sql.PlockSQL;



public class PropertyLockDAO {
	private Logger logger =(Logger)Logger.getLogger(PropertyLockDAO.class);
	
	String sql = null;
	ArrayList mainList = null;
	//DBUtility dbUtil = null;
	private CallableStatement clstmt;
	
	
	
	
	/**
	 * 
	 * @return ArrayList
	 * @throws Exception
	 */
	public synchronized ArrayList getCountryJud(String language) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("getCountry() - start");
		}

		ArrayList list = new ArrayList();DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			logger.debug("Wipro in IGRSCommon - "
					+ "getCountry() after create statement");
			if("en".equalsIgnoreCase(language))
			{
			list = dbUtil.executeQuery(PlockSQL.COUNTRY_QUERY);
			}
			else
			{
				list = dbUtil.executeQuery(PlockSQL.COUNTRY_QUERY_H);
			}
			logger.debug("Wipro in IGRSCommon - "
					+ "getCountry() after execute query");

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
	
	/**
	 * 
	 * @return ArrayList
	 * @throws Exception
	 */
	public synchronized ArrayList getStateJud(String countryId,String language) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("getState(String) - start");
		}

		ArrayList list = new ArrayList();
		String SQL ="";
		if("en".equalsIgnoreCase(language))
		{
			SQL=PlockSQL.STATE_QUERY;
		}
		else
		{
			SQL=PlockSQL.STATE_QUERY_H;
		}
		String arry[] = new String[1];
		if (countryId != null) {
			arry[0] = countryId;
		}DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(SQL);
			logger.debug("Wipro in IGRSCommon - getState() "
					+ "after creating preparedstatement");
			list = dbUtil.executeQuery(arry);
			logger.debug("Wipro in IGRSCommon - "
					+ "getState() after excuteQuery");
		} catch (Exception x) {
			logger.debug(x);
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
	
	//added by shruti---15 may 2014
public ArrayList getExternalUserDtls(String userId) throws Exception{
		
		logger.debug(" getExternalUserDtls--");
		ArrayList resultList=new ArrayList();
		String typeid="";
		String[] param={userId};DBUtility dbUtil = null;
		try {	
			dbUtil = new DBUtility();
			String str = CommonSQL.CHECKUSERTYPE;
			logger.debug(" in dao try for getExternalUserDtls --" + str);
			dbUtil.createPreparedStatement(str);
			resultList=dbUtil.executeQuery(param);
			typeid=dbUtil.executeQry(param);
			
				String str2=CommonSQL.GETSPDISTRICT;
				dbUtil.createPreparedStatement(str2);
				resultList=dbUtil.executeQuery(param);	
			
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		if(dbUtil!=null){
			dbUtil.closeConnection();
			}
			dbUtil=null;
	}			
	return resultList;		
}
public ArrayList getRUUserDtls(String userId) throws Exception{
	
	logger.debug(" getExternalUserDtls--");
	ArrayList resultList=new ArrayList();
	String typeid="";
	String[] param={userId};DBUtility dbUtil = null;
	try {	
		dbUtil = new DBUtility();
			String str1=CommonSQL.GETRUDISTRICT;
			dbUtil.createPreparedStatement(str1);
			resultList=dbUtil.executeQuery(param);
		
		
} catch (Exception e) {
	e.printStackTrace();
} finally {
	if(dbUtil!=null){
		dbUtil.closeConnection();
		}
		dbUtil=null;
}			
return resultList;		
}
	public ArrayList getInternalUserDtls(String officeId) {
		ArrayList list = null;
		DBUtility dbUtil = null;
		String officeName="";
		ArrayList resultList=new ArrayList();
		String arry[] = new String[1];
		if (officeId != null) {
			arry[0] = officeId;
		}
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.GETOFFICENAME);
			//officeName=dbUtil.executeQry(arry);
			resultList=dbUtil.executeQuery(arry);
			} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return resultList;
	}

	
	//added by shruti----15 may 2014
	
	public String chkUser(String userId) throws Exception{
		
		logger.debug(" getPaymentParameters--");
		ArrayList resultList=new ArrayList();
		String typeid="";
		String[] param={userId};DBUtility dbUtil = null;
		try {	
			dbUtil = new DBUtility();
			String str = PlockSQL.CHECKUSERTYPE;
			logger.debug(" in dao try for getPaymentParameters --" + str);
			dbUtil.createPreparedStatement(str);
			typeid=dbUtil.executeQry(param);
			
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		if(dbUtil!=null){
			dbUtil.closeConnection();
			}
			dbUtil=null;
	}			
	return typeid;		
}
	
	/**
	 * @param _retFunId
	 * @param _serId
	 * @param _userType
	 * @return
	 * @throws Exception
	 */
	public String getfee(String _retFunId, String _serId,
			String _userType) throws Exception {
		//ArrayList othersFeeDuty = new ArrayList();
		String othersFeeDuty="";
		String[] param={_retFunId};
		System.out.println("Inside getOthersFeeDuty()");DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			if("2".equalsIgnoreCase(_userType)){
				dbUtil.createPreparedStatement(PlockSQL.GET_SERVICE_FEE_RU);
				othersFeeDuty=dbUtil.executeQry(param);
				
			}
			else if("I".equalsIgnoreCase(_userType)){
				dbUtil.createPreparedStatement(PlockSQL.GET_SERVICE_FEE_DR);
				othersFeeDuty=dbUtil.executeQry(param);
			}
			else
			{
				dbUtil.createPreparedStatement(PlockSQL.GET_SERVICE_FEE_SP);
				othersFeeDuty=dbUtil.executeQry(param);
			}
			if("".equalsIgnoreCase(othersFeeDuty))
			{othersFeeDuty="0";}
			/*clstmt = dbUtil.returnCallableStatement(PlockSQL.SERVICE_FEE_PROCEDURE);
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

			}*/
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}  finally {
			 try{	    
				 if (dbUtil != null) {
					 dbUtil.closeConnection();
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
	 * @param registration number, document status, from date and to date
	 * @return ArrayList
	 * @throws Exception
	 */
	 public ArrayList getTransDetl(String regno,String language) throws Exception{
		 
			String[]reg_compno =new String[1];
			reg_compno[0]=regno;
			ArrayList list = new ArrayList();DBUtility dbUtil = null;
			try{
			dbUtil = new DBUtility();	
			if("en".equalsIgnoreCase(language))
			{
			dbUtil.createPreparedStatement(PlockSQL.REG_LOCK_DETLS_QRY);
			}
			else
			{
				dbUtil.createPreparedStatement(PlockSQL.REG_LOCK_DETLS_QRY_H);
			}
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
			list.trimToSize();
			return list;
		}
	 
	 
	 /**
		 * @param property lock id
		 * @return ArrayList
		 * @throws Exception
		 */
		 public ArrayList getTransDetlR(String regno) throws Exception{
			 
				String[]reg_compno =new String[1];
				reg_compno[0]=regno;
				ArrayList list = new ArrayList();DBUtility dbUtil = null;
				try{
				dbUtil = new DBUtility();	
				dbUtil.createPreparedStatement(PlockSQL.REG_LOCK_DETLS_QRY);
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
				list.trimToSize();
				return list;
			}
		 /**
			 * @param property lock id
			 * @return ArrayList
			 * @throws Exception
			 */
			 public ArrayList getTransDetlR1(String regno, String df, String dt,String language) throws Exception{
				 
					String[]reg_compno =new String[3];
					reg_compno[0]=regno;
					reg_compno[1]=df;
					reg_compno[2]=dt;
					ArrayList list = new ArrayList();DBUtility dbUtil = null;
					try{
					dbUtil = new DBUtility();	
					if("en".equalsIgnoreCase(language))
					{
					dbUtil.createPreparedStatement(PlockSQL.REL_LOCK_DETLS_QRY1);
					}
					else
					{
						dbUtil.createPreparedStatement(PlockSQL.REL_LOCK_DETLS_QRY1_H);
					}
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
					list.trimToSize();
					return list;
				}
		 
			 /**
				 * @param property lock id
				 * @return ArrayList
				 * @throws Exception
				 */
				 public ArrayList getViewDetls(String refId,String regno, String df, String dt,String appSt,String language) throws Exception{
					 
						String[]reg_compno =new String[7];
						reg_compno[0]=refId;
						reg_compno[1]=regno;
						reg_compno[2]=appSt;
						reg_compno[3]=df;
						reg_compno[4]=dt;
						reg_compno[5]=df;
						reg_compno[6]=dt;
						
						ArrayList list = new ArrayList();
						ArrayList subList = new ArrayList();
						ArrayList mainList = new ArrayList();
						String lstUpdateDate= "";DBUtility dbUtil = null;
						try{
						dbUtil = new DBUtility();
						PropertyLockDTO pdto = new PropertyLockDTO();
						
						if("en".equalsIgnoreCase(language))
						{
							//dbUtil.createPreparedStatement(PlockSQL.VIEW_DETLS_QRY);
							dbUtil.createPreparedStatement(PlockSQL.VIEW_DETLS_QRY_NEW);
						}
						else
						{
							//dbUtil.createPreparedStatement(PlockSQL.VIEW_DETLS_QRY_H);	
							dbUtil.createPreparedStatement(PlockSQL.VIEW_DETLS_QRY_H_NEW);	
						}
						list = dbUtil.executeQuery(reg_compno);
						if(list.size()>0 && list !=null)
						{
							for(int i=0;i<list.size();i++)
							{
								subList= (ArrayList) list.get(i);
								String temp[] = new String[2];
								temp[0] = (String)subList.get(0);//ref id
								temp[1] =(String)subList.get(4);//status
								
								if(temp[1].equalsIgnoreCase("1"))
								{
									dbUtil.createPreparedStatement(PlockSQL.VIEW_RELEASE_DATE);
									lstUpdateDate=dbUtil.executeQry(temp);
									
								}
								else if(temp[1].equalsIgnoreCase("2"))
								{
									dbUtil.createPreparedStatement(PlockSQL.VIEW_LOCK_DATE);
									lstUpdateDate=dbUtil.executeQry(temp);
								}
								
								pdto = new PropertyLockDTO();
								pdto.setPropertyTxnId((String)subList.get(0));
								pdto.setRegistrationId((String)subList.get(1));
								pdto.setPropId((String)subList.get(2));
								pdto.setLockStatus((String)subList.get(3));
								pdto.setCreatedDt(lstUpdateDate);
								pdto.setViewComb((String)subList.get(0)+"~"+(String)subList.get(1)+"~"+(String)subList.get(2)+"~"+(String)subList.get(4));
								mainList.add(pdto);
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
					}
			 
	 
	 /**
	     * @param regid
	     * @return
	     */
	    
	    public ArrayList getLockStatus(String[] regid) {
	        ArrayList list = null;DBUtility dbUtil = null;
	        try
	        {
	        	dbUtil = new DBUtility();	
	        	dbUtil.createPreparedStatement(PlockSQL.GETLOCKSTATUS);
	            list = dbUtil.executeQuery(regid);
	            logger.debug("LIST="+list.size());
	        } catch (Exception e)
	        {
	            logger.debug("Exception in getCopyIssuance() :- " + e);
	        }
	        finally {
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
	    }
	
	
	    
	    /**
	     * @param regComId
	     * @return
	     */
	    
	    public String getReginitId(String regComId) {
	        String reginitId="";
	        String[]ary=new String[1];
	        ary[0]=regComId;DBUtility dbUtil = null;
	        try
	        {
	        	dbUtil = new DBUtility();	
	        	dbUtil.createPreparedStatement(PlockSQL.GET_REGINIT_ID);
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
	    
	    //added by shruti---14 may 2014
	    public String getInstrumentFlag(String regComId) {
	        String getInstrumentFlag="";
	        String[]ary=new String[1];
	        ary[0]=regComId;DBUtility dbUtil = null;
	        try
	        {
	        	dbUtil = new DBUtility();	
	        	dbUtil.createPreparedStatement(PlockSQL.GET_INSTRUMENT_FLAG);
	        	getInstrumentFlag =dbUtil.executeQry(ary);
	          
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
	       
	        return getInstrumentFlag;
	    }
	    
	    //end
	    /**
		 * @param registration number, user id, office id
		 * @return boolean
		 * @throws Exception
		 */ 
	 public  String insertLockDetls (PropertyLockDTO dto, String uid, String offid)throws Exception{
	     
		 String lockid="";
		 
	     String[]flagUpdt=new String[3];
	     flagUpdt[0]= uid;
	     flagUpdt[1]= dto.getRegistrationId();
		 flagUpdt[2]= dto.getPropId();
		 
		 
		 String[] txnTblInsrt = new String[9];
		 txnTblInsrt[1]=dto.getRegistrationId();
		 txnTblInsrt[2]=dto.getRegistrationDate();
		 txnTblInsrt[3]=dto.getPropId();
	     txnTblInsrt[4]=dto.getPoaRegNo();
	     txnTblInsrt[5]=dto.getPoaRegDate();
	     txnTblInsrt[6]=dto.getPurpose();
	     txnTblInsrt[7]=uid;
	     txnTblInsrt[8]=offid;
	     
	     String[]partyTblInsert = new String[29];           
	     partyTblInsert[2]=dto.getAppType();
	     partyTblInsert[3]=dto.getFirstName();
	     partyTblInsert[4]=dto.getMidName();
	     partyTblInsert[5]=dto.getLastName();
	     partyTblInsert[6]=dto.getGender();
	     partyTblInsert[7]=dto.getAge();
	     partyTblInsert[8]=dto.getOrgCountry();
	     partyTblInsert[9]=dto.getOrgState();
	     partyTblInsert[10]=dto.getOrgDistrict();
	     partyTblInsert[11]=dto.getAddress();
	     partyTblInsert[12]=dto.getPin();
	     partyTblInsert[13]=dto.getPhone();
	     partyTblInsert[14]=dto.getMphone();
	     partyTblInsert[15]=dto.getEmail();
	     partyTblInsert[16]=dto.getIdProof();
	     partyTblInsert[17]=dto.getIdProofNo();
	     partyTblInsert[18]=dto.getBankName();
	     partyTblInsert[19]=dto.getBankAddress();
	     partyTblInsert[20]=dto.getGuardianName();
	     partyTblInsert[21]=dto.getMotherName();
	     partyTblInsert[22]=dto.getDocumentName();
	     partyTblInsert[23]=dto.getThunmbName();
	     partyTblInsert[24]=dto.getSignatureName();
	     partyTblInsert[25]=dto.getPhotoPath();
	     partyTblInsert[26]=dto.getThumbPath();
	     partyTblInsert[27]=dto.getSignPath();
	     partyTblInsert[28]=uid;
	     
	     String[]partyTblInsert1 = new String[18];           
	     partyTblInsert1[2]=dto.getAppType();
	     partyTblInsert1[3]=dto.getOrgCountry();
	     partyTblInsert1[4]=dto.getOrgState();
	     partyTblInsert1[5]=dto.getOrgDistrict();
	     partyTblInsert1[6]=dto.getOrgAddress();
	     partyTblInsert1[7]=dto.getOrgPhno();
	     partyTblInsert1[8]=dto.getOrgMobno();
	     partyTblInsert1[9]=dto.getOrgName();
	     partyTblInsert1[10]=dto.getAuthName();
	     partyTblInsert1[11]=dto.getDocumentName();
	     partyTblInsert1[12]=dto.getThunmbName();
	     partyTblInsert1[13]=dto.getSignatureName();
	     partyTblInsert1[14]=dto.getPhotoPath();
	     partyTblInsert1[15]=dto.getThumbPath();
	     partyTblInsert1[16]=dto.getSignPath();
	     partyTblInsert1[17]=uid;
	     DBUtility dbUtil = null;
	   try{
		   dbUtil = new DBUtility();
		   dbUtil.setAutoCommit(false);
		   
		  
		     String SQL1 = "SELECT COUNT(PROPERTY_LOCK_ID) FROM IGRS_PROP_LOCK_TXN_DETLS WHERE TRUNC(CREATED_DATE)= TRUNC(SYSDATE)";
		     logger.debug(SQL1);
		     dbUtil.createStatement();
		     String number1 = dbUtil.executeQry(SQL1);
		     if (number1.equalsIgnoreCase("0")){
		                   logger.debug("in if clause");
		                   String drpqry = "DROP SEQUENCE IGRS_PROP_LCK_TXN_ID_SEQ";                         
		                   dbUtil.createStatement();
		                   dbUtil.executeUpdate(drpqry);
		                   String crtqry = "CREATE SEQUENCE IGRS_PROP_LCK_TXN_ID_SEQ START WITH 1 INCREMENT BY 1 MAXVALUE 999999 MINVALUE 1 NOCYCLE  NOCACHE ORDER";
		                   dbUtil.createStatement();
		                   dbUtil.executeUpdate(crtqry);
		           }
		             String SQL2 = "select IGRS_PROP_LCK_TXN_ID_SEQ.nextval from dual";
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

		     Date date = new Date();
		     Format yearformat  = new SimpleDateFormat("yy");
		     Format monthformat = new SimpleDateFormat("MM");
		     Format dateformat  = new SimpleDateFormat("dd");
		     String dfmt = dateformat.format(date);
		     String yfmt = yearformat.format(date);
		     String mfmt = monthformat.format(date);
		     String locktxnid = "73"+dfmt+mfmt+yfmt+number2;
		     txnTblInsrt[0]=locktxnid;
		     partyTblInsert[1]=locktxnid;
		     partyTblInsert1[1]=locktxnid;

		     String SQL3 = "select IGRS_PROP_LCK_PARTY_ID_SEQ.nextval from dual";
             dbUtil.createStatement();
             String number3 = dbUtil.executeQry(SQL3);
             partyTblInsert[0] = number3;
             partyTblInsert1[0]= number3;
             
             
            dbUtil.createPreparedStatement(PlockSQL.STATUS_FLAG_UPDATE);
		    dbUtil.executeUpdate(flagUpdt);
		    
		    dbUtil.createPreparedStatement(PlockSQL.LOCK_TXN_TBL_INSERT);
			dbUtil.executeUpdate(txnTblInsrt); 
		  
			String apptype = dto.getAppType();
			if(apptype.equalsIgnoreCase("1")){
			dbUtil.createPreparedStatement(PlockSQL.LOCK_PARTY_TBL_INSERT1);
			dbUtil.executeUpdate(partyTblInsert1); 
			}
			else if (apptype.equalsIgnoreCase("2")){
			dbUtil.createPreparedStatement(PlockSQL.LOCK_PARTY_TBL_INSERT);
			dbUtil.executeUpdate(partyTblInsert); 
			}
			
		   dbUtil.commit();				     
		   lockid=locktxnid;
	 }catch (NullPointerException ne) {
		    ne.printStackTrace();
		    dbUtil.rollback();
		   throw ne;
			
	   }
		    catch (SQLException se) {
		    	 se.printStackTrace();
		    	 dbUtil.rollback();
		    	
			logger.error("SQL Exception at estamp  in DAO " + se); 
		 throw se;
		}
	   catch(Exception e){
		   e.printStackTrace();
		   dbUtil.rollback();
		logger.error(" Exception at estamp in DAO " + e);
		throw e;
		}
	   
	   finally {
			 try{	    
				 if (dbUtil != null) {
					 dbUtil.closeConnection();
			 }
				 }
			 catch (Exception e) {
			 logger.error("Exception at estamp in DAO  " + e);
			 }		
	       }
	 
	   return lockid;   
	 }
	 
	 
	 /**
		 * @param registration number, user id, office id
		 * @return boolean
		 * @throws Exception
		 */ 
	 public  String insertReleaseDetls (PropertyLockDTO dto, String uid, String offid)throws Exception{
	     
		 String releaseId="";
		 
	     String[]flagUpdt=new String[4];
	     flagUpdt[0]= uid;
	     flagUpdt[1]= dto.getRegistrationId();
		 flagUpdt[2]= dto.getPropId();
		 flagUpdt[3]= dto.getPropertyLockid();
		 
		 
		 String[] txnTblInsrt = new String[11];
		 txnTblInsrt[1]=dto.getRegistrationId();
		 txnTblInsrt[2]=dto.getRegistrationDate();
		 txnTblInsrt[3]=dto.getPropId();
	     txnTblInsrt[4]=dto.getPoaRegNo();
	     txnTblInsrt[5]=dto.getPoaRegDate();
	     txnTblInsrt[6]=dto.getReasonForRelease();
	     txnTblInsrt[7]=uid;
	     txnTblInsrt[8]=offid;
	     txnTblInsrt[9]=dto.getRemarksForRelease();
	     txnTblInsrt[10]="1";
	     
	     String[]partyTblInsert = new String[29];           
	     partyTblInsert[2]=dto.getAppType();
	     partyTblInsert[3]=dto.getFirstName();
	     partyTblInsert[4]=dto.getMidName();
	     partyTblInsert[5]=dto.getLastName();
	     partyTblInsert[6]=dto.getGender();
	     partyTblInsert[7]=dto.getAge();
	     partyTblInsert[8]=dto.getOrgCountry();
	     partyTblInsert[9]=dto.getOrgState();
	     partyTblInsert[10]=dto.getOrgDistrict();
	     partyTblInsert[11]=dto.getAddress();
	     partyTblInsert[12]=dto.getPin();
	     if(dto.getPhone()!=null){
	     partyTblInsert[13]=dto.getPhone();
	     }else {
	    	 partyTblInsert[13]="";
	     }
	     partyTblInsert[14]=dto.getMphone();
	     partyTblInsert[15]=dto.getEmail();
	     partyTblInsert[16]=dto.getIdProof();
	     partyTblInsert[17]=dto.getIdProofNo();
	     partyTblInsert[18]=dto.getBankName();
	     partyTblInsert[19]=dto.getBankAddress();
	     partyTblInsert[20]=dto.getGuardianName();
	     partyTblInsert[21]=dto.getMotherName();
	     partyTblInsert[22]=dto.getDocumentName();
	     partyTblInsert[23]=dto.getThunmbName();
	     partyTblInsert[24]=dto.getSignatureName();
	     partyTblInsert[25]=dto.getPhotoPath();
	     partyTblInsert[26]=dto.getThumbPath();
	     partyTblInsert[27]=dto.getSignPath();
	     partyTblInsert[28]=uid;
	     
	     String[]partyTblInsert1 = new String[18];           
	     partyTblInsert1[2]=dto.getAppType();
	     partyTblInsert1[3]=dto.getOrgCountry();
	     partyTblInsert1[4]=dto.getOrgState();
	     partyTblInsert1[5]=dto.getOrgDistrict();
	     partyTblInsert1[6]=dto.getOrgAddress();
	     if(dto.getOrgPhno()!=null){
	     partyTblInsert1[7]=dto.getOrgPhno();
	     }else{
	    	 partyTblInsert1[7]="";
	     }
	     partyTblInsert1[8]=dto.getOrgMobno();
	     partyTblInsert1[9]=dto.getOrgName();
	     partyTblInsert1[10]=dto.getAuthName();
	     partyTblInsert1[11]=dto.getDocumentName();
	     partyTblInsert1[12]=dto.getThunmbName();
	     partyTblInsert1[13]=dto.getSignatureName();
	     partyTblInsert1[14]=dto.getPhotoPath();
	     partyTblInsert1[15]=dto.getThumbPath();
	     partyTblInsert1[16]=dto.getSignPath();
	     partyTblInsert1[17]=uid;
	     DBUtility dbUtil = null;
	   try{
		   dbUtil = new DBUtility();
		   dbUtil.setAutoCommit(false);
		   
		  
		     String SQL1 = "SELECT COUNT(PROPERTY_LOCK_ID) FROM IGRS_PROP_LOCK_TXN_DETLS WHERE TRUNC(CREATED_DATE)= TRUNC(SYSDATE)";
		     logger.debug(SQL1);
		     dbUtil.createStatement();
		     String number1 = dbUtil.executeQry(SQL1);
		     if (number1.equalsIgnoreCase("0")){
		                   logger.debug("in if clause");
		                   String drpqry = "DROP SEQUENCE IGRS_PROP_LCK_TXN_ID_SEQ";                         
		                   dbUtil.createStatement();
		                   dbUtil.executeUpdate(drpqry);
		                   String crtqry = "CREATE SEQUENCE IGRS_PROP_LCK_TXN_ID_SEQ START WITH 1 INCREMENT BY 1 MAXVALUE 999999 MINVALUE 1 NOCYCLE  NOCACHE ORDER";
		                   dbUtil.createStatement();
		                   dbUtil.executeUpdate(crtqry);
		           }
		             String SQL2 = "select IGRS_PROP_LCK_TXN_ID_SEQ.nextval from dual";
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

		     Date date = new Date();
		     Format yearformat  = new SimpleDateFormat("yy");
		     Format monthformat = new SimpleDateFormat("MM");
		     Format dateformat  = new SimpleDateFormat("dd");
		     String dfmt = dateformat.format(date);
		     String yfmt = yearformat.format(date);
		     String mfmt = monthformat.format(date);
		     String locktxnid = "73"+dfmt+mfmt+yfmt+number2;
		     txnTblInsrt[0]=locktxnid;
		     partyTblInsert[1]=locktxnid;
		     partyTblInsert1[1]=locktxnid;

		     String SQL3 = "select IGRS_PROP_LCK_PARTY_ID_SEQ.nextval from dual";
          dbUtil.createStatement();
          String number3 = dbUtil.executeQry(SQL3);
          partyTblInsert[0] = number3;
          partyTblInsert1[0]= number3;
          boolean up=false;
          
          dbUtil.createPreparedStatement(PlockSQL.STATUS_FLAG_UPDATE_REL);
	       up=dbUtil.executeUpdate(flagUpdt);
		    if(up){
		    dbUtil.createPreparedStatement(PlockSQL.LOCK_TXN_TBL_INSERT_REL);
			up=dbUtil.executeUpdate(txnTblInsrt); 
		     if(up){
			String apptype = dto.getAppType();
			if(apptype.equalsIgnoreCase("1")){
			dbUtil.createPreparedStatement(PlockSQL.LOCK_PARTY_TBL_INSERT1);
			up=dbUtil.executeUpdate(partyTblInsert1); 
			}
			else if (apptype.equalsIgnoreCase("2")){
			dbUtil.createPreparedStatement(PlockSQL.LOCK_PARTY_TBL_INSERT);
			up=dbUtil.executeUpdate(partyTblInsert); 
			}
			if(up){
				dbUtil.commit();				     
				releaseId=locktxnid;
		  }
		   
		   }
	}
	 }catch (NullPointerException ne) {
		    ne.printStackTrace();
		    dbUtil.rollback();
		   throw ne;
			
	   }
		    catch (SQLException se) {
		    	 se.printStackTrace();
		    	 dbUtil.rollback();
		    	
			logger.error("SQL Exception at estamp  in DAO " + se); 
		 throw se;
		}
	   catch(Exception e){
		   e.printStackTrace();
		   dbUtil.rollback();
		logger.error(" Exception at estamp in DAO " + e);
		throw e;
		}
	   
	   finally {
			 try{	    
				 if (dbUtil != null) {
					 dbUtil.closeConnection();
			 }
				 }
			 catch (Exception e) {
			 logger.error("Exception at estamp in DAO  " + e);
			 }		
	       }
	 
	   return releaseId;   
	 }
	 
	 /**
		 * @param registration number, user id, office id
		 * @return boolean
		 * @throws Exception
		 */ 
	 public  String insertReleaseDetlsP (PropertyLockDTO dto, String uid, String offid)throws Exception{
	     
		 String releaseId="";
		 
	     String[]flagUpdt=new String[4];
	     flagUpdt[0]= uid;
	     flagUpdt[1]= dto.getRegistrationId();
		 flagUpdt[2]= dto.getPropId();
		 flagUpdt[3]= dto.getPropertyLockid();
		 
		 
		 String[] txnTblInsrt = new String[11];
		 txnTblInsrt[1]=dto.getRegistrationId();
		 txnTblInsrt[2]=convertedDate(dto.getRegistrationDate());
		 txnTblInsrt[3]=dto.getPropId();
	     txnTblInsrt[4]=dto.getPoaRegNo();
	     txnTblInsrt[5]=convertedDate(dto.getPoaRegDate());
	     txnTblInsrt[6]=dto.getReasonForRelease();
	     txnTblInsrt[7]=uid;
	     txnTblInsrt[8]=offid;
	     txnTblInsrt[9]=dto.getRemarksForRelease();
	     txnTblInsrt[10]="1";
	     
	     String[]partyTblInsert = new String[29];           
	     partyTblInsert[2]=dto.getAppType();
	     partyTblInsert[3]=dto.getFirstName();
	     partyTblInsert[4]=dto.getMidName();
	     partyTblInsert[5]=dto.getLastName();
	     partyTblInsert[6]=dto.getGender();
	     partyTblInsert[7]=dto.getAge();
	     partyTblInsert[8]=dto.getOrgCountry();
	     partyTblInsert[9]=dto.getOrgState();
	     partyTblInsert[10]=dto.getOrgDistrict();
	     partyTblInsert[11]=dto.getAddress();
	     partyTblInsert[12]=dto.getPin();
	     if(dto.getPhone()!=null){
	     partyTblInsert[13]=dto.getPhone();
	     }else {
	    	 partyTblInsert[13]="";
	     }
	     partyTblInsert[14]=dto.getMphone();
	     partyTblInsert[15]=dto.getEmail();
	     partyTblInsert[16]=dto.getIdProof();
	     partyTblInsert[17]=dto.getIdProofNo();
	     partyTblInsert[18]=dto.getBankName();
	     partyTblInsert[19]=dto.getBankAddress();
	     partyTblInsert[20]=dto.getGuardianName();
	     partyTblInsert[21]=dto.getMotherName();
	     partyTblInsert[22]=dto.getDocumentName();
	     partyTblInsert[23]=dto.getThunmbName();
	     partyTblInsert[24]=dto.getSignatureName();
	     partyTblInsert[25]=dto.getPhotoPath();
	     partyTblInsert[26]=dto.getThumbPath();
	     partyTblInsert[27]=dto.getSignPath();
	     partyTblInsert[28]=uid;
	     
	     String[]partyTblInsert1 = new String[18];           
	     partyTblInsert1[2]=dto.getAppType();
	     partyTblInsert1[3]=dto.getOrgCountry();
	     partyTblInsert1[4]=dto.getOrgState();
	     partyTblInsert1[5]=dto.getOrgDistrict();
	     partyTblInsert1[6]=dto.getOrgAddress();
	     if(dto.getOrgPhno()!=null){
	     partyTblInsert1[7]=dto.getOrgPhno();
	     }else{
	    	 partyTblInsert1[7]="";
	     }
	     partyTblInsert1[8]=dto.getOrgMobno();
	     partyTblInsert1[9]=dto.getOrgName();
	     partyTblInsert1[10]=dto.getAuthName();
	     partyTblInsert1[11]=dto.getDocumentName();
	     partyTblInsert1[12]=dto.getThunmbName();
	     partyTblInsert1[13]=dto.getSignatureName();
	     partyTblInsert1[14]=dto.getPhotoPath();
	     partyTblInsert1[15]=dto.getThumbPath();
	     partyTblInsert1[16]=dto.getSignPath();
	     partyTblInsert1[17]=uid;
	     DBUtility dbUtil = null;
	   try{
		   dbUtil = new DBUtility();
		   dbUtil.setAutoCommit(false);
		   
		  
		     String SQL1 = "SELECT COUNT(PROPERTY_LOCK_ID) FROM IGRS_PROP_LOCK_TXN_DETLS WHERE TRUNC(CREATED_DATE)= TRUNC(SYSDATE)";
		     logger.debug(SQL1);
		     dbUtil.createStatement();
		     String number1 = dbUtil.executeQry(SQL1);
		     if (number1.equalsIgnoreCase("0")){
		                   logger.debug("in if clause");
		                   String drpqry = "DROP SEQUENCE IGRS_PROP_LCK_TXN_ID_SEQ";                         
		                   dbUtil.createStatement();
		                   dbUtil.executeUpdate(drpqry);
		                   String crtqry = "CREATE SEQUENCE IGRS_PROP_LCK_TXN_ID_SEQ START WITH 1 INCREMENT BY 1 MAXVALUE 999999 MINVALUE 1 NOCYCLE  NOCACHE ORDER";
		                   dbUtil.createStatement();
		                   dbUtil.executeUpdate(crtqry);
		           }
		             String SQL2 = "select IGRS_PROP_LCK_TXN_ID_SEQ.nextval from dual";
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

		     Date date = new Date();
		     Format yearformat  = new SimpleDateFormat("yy");
		     Format monthformat = new SimpleDateFormat("MM");
		     Format dateformat  = new SimpleDateFormat("dd");
		     String dfmt = dateformat.format(date);
		     String yfmt = yearformat.format(date);
		     String mfmt = monthformat.format(date);
		     String locktxnid = "73"+dfmt+mfmt+yfmt+number2;
		     txnTblInsrt[0]=locktxnid;
		     partyTblInsert[1]=locktxnid;
		     partyTblInsert1[1]=locktxnid;

		     String SQL3 = "select IGRS_PROP_LCK_PARTY_ID_SEQ.nextval from dual";
       dbUtil.createStatement();
       String number3 = dbUtil.executeQry(SQL3);
       partyTblInsert[0] = number3;
       partyTblInsert1[0]= number3;
       boolean up=false;
       
       dbUtil.createPreparedStatement(PlockSQL.STATUS_FLAG_UPDATE_REL);
	       up=dbUtil.executeUpdate(flagUpdt);
		    if(up){
		    dbUtil.createPreparedStatement(PlockSQL.LOCK_TXN_TBL_INSERT_REL_P);
			up=dbUtil.executeUpdate(txnTblInsrt); 
		     if(up){
			String apptype = dto.getAppType();
			if(apptype.equalsIgnoreCase("1")){
			dbUtil.createPreparedStatement(PlockSQL.LOCK_PARTY_TBL_INSERT1);
			up=dbUtil.executeUpdate(partyTblInsert1); 
			}
			else if (apptype.equalsIgnoreCase("2")){
			dbUtil.createPreparedStatement(PlockSQL.LOCK_PARTY_TBL_INSERT);
			up=dbUtil.executeUpdate(partyTblInsert); 
			}
			if(up){
				dbUtil.commit();				     
				releaseId=locktxnid;
		  }
		   
		   }
	}
	 }catch (NullPointerException ne) {
		    ne.printStackTrace();
		    dbUtil.rollback();
		   throw ne;
			
	   }
		    catch (SQLException se) {
		    	 se.printStackTrace();
		    	 dbUtil.rollback();
		    	
			logger.error("SQL Exception at estamp  in DAO " + se); 
		 throw se;
		}
	   catch(Exception e){
		   e.printStackTrace();
		   dbUtil.rollback();
		logger.error(" Exception at estamp in DAO " + e);
		throw e;
		}
	   
	   finally {
			 try{	    
				 if (dbUtil != null) {
					 dbUtil.closeConnection();
			 }
				 }
			 catch (Exception e) {
			 logger.error("Exception at estamp in DAO  " + e);
			 }		
	       }
	 
	   return releaseId;   
	 }
	 /**
		 * @param registration number, user id, office id
		 * @return boolean
		 * @throws Exception
		 */ 
	 public  String insertReleaseDetlsR (PropertyLockDTO dto, String uid, String offid)throws Exception{
	     
		 String releaseId="";
		 
	     String[]flagUpdt=new String[4];
	     flagUpdt[0]= uid;
	     flagUpdt[1]= dto.getRegistrationId();
		 flagUpdt[2]= dto.getPropId();
		 flagUpdt[3]= dto.getPropertyLockid();
		 
		 
		 String[] txnTblInsrt = new String[11];
		 txnTblInsrt[1]=dto.getRegistrationId();
		 txnTblInsrt[2]=dto.getRegistrationDate();
		 txnTblInsrt[3]=dto.getPropId();
	     txnTblInsrt[4]=dto.getPoaRegNo();
	     txnTblInsrt[5]=dto.getPoaRegDate();
	     txnTblInsrt[6]=dto.getReasonForRelease();
	     txnTblInsrt[7]=uid;
	     txnTblInsrt[8]=offid;
	     txnTblInsrt[9]=dto.getRemarksForRelease();
	     txnTblInsrt[10]="2";
	     
	     String[]partyTblInsert = new String[22];           
	     partyTblInsert[2]=dto.getReleaserName();
	     partyTblInsert[3]=dto.getRelationId();
	     partyTblInsert[4]=dto.getRelFatherName();
	     partyTblInsert[5]=dto.getRelMotherName();
	     partyTblInsert[6]=dto.getRcountryId();
	     partyTblInsert[7]=dto.getRstateId();
	     partyTblInsert[8]=dto.getRdistrictId();
	     partyTblInsert[9]=dto.getRelAddress();
	     partyTblInsert[10]=dto.getRelMobNo();
	     partyTblInsert[11]=dto.getRelphone();
	     partyTblInsert[12]=dto.getRelEmail();
	     partyTblInsert[13]=dto.getRelDeathCerName();
	     partyTblInsert[14]=dto.getRelPhotoName();
	     partyTblInsert[15]=dto.getRelThumbName();
	     partyTblInsert[16]=dto.getRelSignName();
	     partyTblInsert[17]=dto.getRelDeathCrtPath();
	     partyTblInsert[18]=dto.getRelPhotoPath();
	     partyTblInsert[19]=dto.getRelThumbPath();
	     partyTblInsert[20]=dto.getRelSignPath();
	     partyTblInsert[21]=uid;
	     
	     DBUtility dbUtil = null;
	    
	   try{
		   dbUtil = new DBUtility();
		   dbUtil.setAutoCommit(false);
		   
		  
		     String SQL1 = "SELECT COUNT(PROPERTY_LOCK_ID) FROM IGRS_PROP_LOCK_TXN_DETLS WHERE TRUNC(CREATED_DATE)= TRUNC(SYSDATE)";
		     logger.debug(SQL1);
		     dbUtil.createStatement();
		     String number1 = dbUtil.executeQry(SQL1);
		     if (number1.equalsIgnoreCase("0")){
		                   logger.debug("in if clause");
		                   String drpqry = "DROP SEQUENCE IGRS_PROP_LCK_TXN_ID_SEQ";                         
		                   dbUtil.createStatement();
		                   dbUtil.executeUpdate(drpqry);
		                   String crtqry = "CREATE SEQUENCE IGRS_PROP_LCK_TXN_ID_SEQ START WITH 1 INCREMENT BY 1 MAXVALUE 999999 MINVALUE 1 NOCYCLE  NOCACHE ORDER";
		                   dbUtil.createStatement();
		                   dbUtil.executeUpdate(crtqry);
		           }
		             String SQL2 = "select IGRS_PROP_LCK_TXN_ID_SEQ.nextval from dual";
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

		     Date date = new Date();
		     Format yearformat  = new SimpleDateFormat("yy");
		     Format monthformat = new SimpleDateFormat("MM");
		     Format dateformat  = new SimpleDateFormat("dd");
		     String dfmt = dateformat.format(date);
		     String yfmt = yearformat.format(date);
		     String mfmt = monthformat.format(date);
		     String locktxnid = "73"+dfmt+mfmt+yfmt+number2;
		     txnTblInsrt[0]=locktxnid;
		     partyTblInsert[1]=locktxnid;
		    

	  String SQL3 = "select IGRS_PROP_LCK_RELATIVE_ID_SEQ.nextval from dual";
       dbUtil.createStatement();
       String number3 = dbUtil.executeQry(SQL3);
       partyTblInsert[0] = number3;
       boolean up=false;
       
           dbUtil.createPreparedStatement(PlockSQL.STATUS_FLAG_UPDATE_REL);
	       up=dbUtil.executeUpdate(flagUpdt);
		    if(up){
		    dbUtil.createPreparedStatement(PlockSQL.LOCK_TXN_TBL_INSERT_REL);
			up=dbUtil.executeUpdate(txnTblInsrt); 
		     if(up){
			
			dbUtil.createPreparedStatement(PlockSQL.LOCK_REL_TBL_INSERT);
			up=dbUtil.executeUpdate(partyTblInsert); 
			
			if(up){
				dbUtil.commit();				     
				releaseId=locktxnid;
		  }
		   
		   }
	}
	 }catch (NullPointerException ne) {
		    ne.printStackTrace();
		    dbUtil.rollback();
		   throw ne;
			
	   }
		    catch (SQLException se) {
		    	 se.printStackTrace();
		    	 dbUtil.rollback();
		    	
			logger.error("SQL Exception at estamp  in DAO " + se); 
		 throw se;
		}
	   catch(Exception e){
		   e.printStackTrace();
		   dbUtil.rollback();
		logger.error(" Exception at estamp in DAO " + e);
		throw e;
		}
	   
	   finally {
			 try{	    
				 if (dbUtil != null) {
					 dbUtil.closeConnection();
			 }
				 }
			 catch (Exception e) {
			 logger.error("Exception at estamp in DAO  " + e);
			 }		
	       }
	 
	   return releaseId;   
	 }
	 
	 /**
		 * @param registration number, user id, office id
		 * @return boolean
		 * @throws Exception
		 */ 
	 public  String insertReleaseDetlsRP (PropertyLockDTO dto, String uid, String offid)throws Exception{
	     
		 String releaseId="";
		 
	     String[]flagUpdt=new String[4];
	     flagUpdt[0]= uid;
	     flagUpdt[1]= dto.getRegistrationId();
		 flagUpdt[2]= dto.getPropId();
		 flagUpdt[3]= dto.getPropertyLockid();
		 
		 
		 String[] txnTblInsrt = new String[11];
		 txnTblInsrt[1]=dto.getRegistrationId();
		 txnTblInsrt[2]=dto.getRegistrationDate();
		 txnTblInsrt[3]=dto.getPropId();
	     txnTblInsrt[4]=dto.getPoaRegNo();
	     //modified by shruti--22 jan 2014
	     txnTblInsrt[5]=convertedDate(dto.getPoaRegDate());
	     txnTblInsrt[6]=dto.getReasonForRelease();
	     txnTblInsrt[7]=uid;
	     txnTblInsrt[8]=offid;
	     txnTblInsrt[9]=dto.getRemarksForRelease();
	     txnTblInsrt[10]="2";
	     
	     String[]partyTblInsert = new String[22];           
	     partyTblInsert[2]=dto.getReleaserName();
	     partyTblInsert[3]=dto.getRelationId();
	     partyTblInsert[4]=dto.getRelFatherName();
	     partyTblInsert[5]=dto.getRelMotherName();
	     partyTblInsert[6]=dto.getRcountryId();
	     partyTblInsert[7]=dto.getRstateId();
	     partyTblInsert[8]=dto.getRdistrictId();
	     partyTblInsert[9]=dto.getRelAddress();
	     partyTblInsert[10]=dto.getRelMobNo();
	     partyTblInsert[11]=dto.getRelphone();
	     partyTblInsert[12]=dto.getRelEmail();
	     partyTblInsert[13]=dto.getRelDeathCerName();
	     partyTblInsert[14]=dto.getRelPhotoName();
	     partyTblInsert[15]=dto.getRelThumbName();
	     partyTblInsert[16]=dto.getRelSignName();
	     partyTblInsert[17]=dto.getRelDeathCrtPath();
	     partyTblInsert[18]=dto.getRelPhotoPath();
	     partyTblInsert[19]=dto.getRelThumbPath();
	     partyTblInsert[20]=dto.getRelSignPath();
	     partyTblInsert[21]=uid;
	     
	     
	     DBUtility dbUtil = null;
	   try{
		   dbUtil = new DBUtility();
		   dbUtil.setAutoCommit(false);
		   
		  
		     String SQL1 = "SELECT COUNT(PROPERTY_LOCK_ID) FROM IGRS_PROP_LOCK_TXN_DETLS WHERE TRUNC(CREATED_DATE)= TRUNC(SYSDATE)";
		     logger.debug(SQL1);
		     dbUtil.createStatement();
		     String number1 = dbUtil.executeQry(SQL1);
		     if (number1.equalsIgnoreCase("0")){
		                   logger.debug("in if clause");
		                   String drpqry = "DROP SEQUENCE IGRS_PROP_LCK_TXN_ID_SEQ";                         
		                   dbUtil.createStatement();
		                   dbUtil.executeUpdate(drpqry);
		                   String crtqry = "CREATE SEQUENCE IGRS_PROP_LCK_TXN_ID_SEQ START WITH 1 INCREMENT BY 1 MAXVALUE 999999 MINVALUE 1 NOCYCLE  NOCACHE ORDER";
		                   dbUtil.createStatement();
		                   dbUtil.executeUpdate(crtqry);
		           }
		             String SQL2 = "select IGRS_PROP_LCK_TXN_ID_SEQ.nextval from dual";
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

		     Date date = new Date();
		     Format yearformat  = new SimpleDateFormat("yy");
		     Format monthformat = new SimpleDateFormat("MM");
		     Format dateformat  = new SimpleDateFormat("dd");
		     String dfmt = dateformat.format(date);
		     String yfmt = yearformat.format(date);
		     String mfmt = monthformat.format(date);
		     String locktxnid = "73"+dfmt+mfmt+yfmt+number2;
		     txnTblInsrt[0]=locktxnid;
		     partyTblInsert[1]=locktxnid;
		    

	  String SQL3 = "select IGRS_PROP_LCK_RELATIVE_ID_SEQ.nextval from dual";
    dbUtil.createStatement();
    String number3 = dbUtil.executeQry(SQL3);
    partyTblInsert[0] = number3;
    boolean up=false;
    
        dbUtil.createPreparedStatement(PlockSQL.STATUS_FLAG_UPDATE_REL);
	       up=dbUtil.executeUpdate(flagUpdt);
		    if(up){
		    dbUtil.createPreparedStatement(PlockSQL.LOCK_TXN_TBL_INSERT_REL_P);
			up=dbUtil.executeUpdate(txnTblInsrt); 
		     if(up){
			
			dbUtil.createPreparedStatement(PlockSQL.LOCK_REL_TBL_INSERT);
			up=dbUtil.executeUpdate(partyTblInsert); 
			
			if(up){
				dbUtil.commit();				     
				releaseId=locktxnid;
		  }
		   
		   }
	}
	 }catch (NullPointerException ne) {
		    ne.printStackTrace();
		    dbUtil.rollback();
		   throw ne;
			
	   }
		    catch (SQLException se) {
		    	 se.printStackTrace();
		    	 dbUtil.rollback();
		    	
			logger.error("SQL Exception at estamp  in DAO " + se); 
		 throw se;
		}
	   catch(Exception e){
		   e.printStackTrace();
		   dbUtil.rollback();
		logger.error(" Exception at estamp in DAO " + e);
		throw e;
		}
	   
	   finally {
			 try{	    
				 if (dbUtil != null) {
					 dbUtil.closeConnection();
			 }
				 }
			 catch (Exception e) {
			 logger.error("Exception at estamp in DAO  " + e);
			 }		
	       }
	 
	   return releaseId;   
	 }
	  /**
		 * @param registration number, user id, office id
		 * @return boolean
		 * @throws Exception
		 */ 
	 public  String insertLockDetlsP (PropertyLockDTO dto, String uid, String offid)throws Exception{
	     
		 String lockid="";
		 boolean ins=false;
	     String[]flagUpdt=new String[3];
	     flagUpdt[0]= uid;
	     flagUpdt[1]= dto.getRegistrationId();
		 flagUpdt[2]= dto.getPropId();
		 
		 
		 String[] txnTblInsrt = new String[9];
		 txnTblInsrt[1]=dto.getRegistrationId();
		 txnTblInsrt[2]=dto.getRegistrationDate();
		 txnTblInsrt[3]=dto.getPropId();
	     txnTblInsrt[4]=dto.getPoaRegNo();
	     txnTblInsrt[5]=dto.getPoaRegDate();
	     txnTblInsrt[6]=dto.getPurpose();
	     txnTblInsrt[7]=uid;
	     txnTblInsrt[8]=offid;
	     
	     String[]partyTblInsert = new String[29];           
	     partyTblInsert[2]=dto.getAppType();
	     partyTblInsert[3]=dto.getFirstName();
	     partyTblInsert[4]=dto.getMidName();
	     partyTblInsert[5]=dto.getLastName();
	     partyTblInsert[6]=dto.getGender();
	     partyTblInsert[7]=dto.getAge();
	     partyTblInsert[8]=dto.getOrgCountry();
	     partyTblInsert[9]=dto.getOrgState();
	     partyTblInsert[10]=dto.getOrgDistrict();
	     partyTblInsert[11]=dto.getAddress();
	     partyTblInsert[12]=dto.getPin();
	     partyTblInsert[13]=dto.getPhone();
	     partyTblInsert[14]=dto.getMphone();
	     partyTblInsert[15]=dto.getEmail();
	     partyTblInsert[16]=dto.getIdProof();
	     partyTblInsert[17]=dto.getIdProofNo();
	     partyTblInsert[18]=dto.getBankName();
	     partyTblInsert[19]=dto.getBankAddress();
	     partyTblInsert[20]=dto.getGuardianName();
	     partyTblInsert[21]=dto.getMotherName();
	     partyTblInsert[22]=dto.getDocumentName();
	     partyTblInsert[23]=dto.getThunmbName();
	     partyTblInsert[24]=dto.getSignatureName();
	     partyTblInsert[25]=dto.getPhotoPath();
	     partyTblInsert[26]=dto.getThumbPath();
	     partyTblInsert[27]=dto.getSignPath();
	     partyTblInsert[28]=uid;
	     
	     String[]partyTblInsert1 = new String[18];           
	     partyTblInsert1[2]=dto.getAppType();
	     partyTblInsert1[3]=dto.getOrgCountry();
	     partyTblInsert1[4]=dto.getOrgState();
	     partyTblInsert1[5]=dto.getOrgDistrict();
	     partyTblInsert1[6]=dto.getOrgAddress();
	     partyTblInsert1[7]=dto.getOrgPhno();
	     partyTblInsert1[8]=dto.getOrgMobno();
	     partyTblInsert1[9]=dto.getOrgName();
	     partyTblInsert1[10]=dto.getAuthName();
	     partyTblInsert1[11]=dto.getDocumentName();
	     partyTblInsert1[12]=dto.getThunmbName();
	     partyTblInsert1[13]=dto.getSignatureName();
	     partyTblInsert1[14]=dto.getPhotoPath();
	     partyTblInsert1[15]=dto.getThumbPath();
	     partyTblInsert1[16]=dto.getSignPath();
	     partyTblInsert1[17]=uid;
	     DBUtility dbUtil = null;
	   try{
		   dbUtil = new DBUtility();
		   dbUtil.setAutoCommit(false);
		   
		  
		     String SQL1 = "SELECT COUNT(PROPERTY_LOCK_ID) FROM IGRS_PROP_LOCK_TXN_DETLS WHERE TRUNC(CREATED_DATE)= TRUNC(SYSDATE)";
		     logger.debug(SQL1);
		     dbUtil.createStatement();
		     String number1 = dbUtil.executeQry(SQL1);
		     if (number1.equalsIgnoreCase("0")){
		                   logger.debug("in if clause");
		                   String drpqry = "DROP SEQUENCE IGRS_PROP_LCK_TXN_ID_SEQ";                         
		                   dbUtil.createStatement();
		                   dbUtil.executeUpdate(drpqry);
		                   String crtqry = "CREATE SEQUENCE IGRS_PROP_LCK_TXN_ID_SEQ START WITH 1 INCREMENT BY 1 MAXVALUE 999999 MINVALUE 1 NOCYCLE  NOCACHE ORDER";
		                   dbUtil.createStatement();
		                   dbUtil.executeUpdate(crtqry);
		           }
		             String SQL2 = "select IGRS_PROP_LCK_TXN_ID_SEQ.nextval from dual";
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

		     Date date = new Date();
		     Format yearformat  = new SimpleDateFormat("yy");
		     Format monthformat = new SimpleDateFormat("MM");
		     Format dateformat  = new SimpleDateFormat("dd");
		     String dfmt = dateformat.format(date);
		     String yfmt = yearformat.format(date);
		     String mfmt = monthformat.format(date);
		     String locktxnid = "73"+dfmt+mfmt+yfmt+number2;
		     txnTblInsrt[0]=locktxnid;
		     partyTblInsert[1]=locktxnid;
		     partyTblInsert1[1]=locktxnid;

		     String SQL3 = "select IGRS_PROP_LCK_PARTY_ID_SEQ.nextval from dual";
          dbUtil.createStatement();
          String number3 = dbUtil.executeQry(SQL3);
          partyTblInsert[0] = number3;
          partyTblInsert1[0]= number3;
          
          
            dbUtil.createPreparedStatement(PlockSQL.STATUS_FLAG_UPDATE);
		    ins=dbUtil.executeUpdate(flagUpdt);
		    if(ins){
		    dbUtil.createPreparedStatement(PlockSQL.LOCK_TXN_TBL_INSERTP);
			ins=dbUtil.executeUpdate(txnTblInsrt); 
		  if(ins){
			String apptype = dto.getAppType();
			if(apptype.equalsIgnoreCase("1")){
			dbUtil.createPreparedStatement(PlockSQL.LOCK_PARTY_TBL_INSERT1);
			ins=dbUtil.executeUpdate(partyTblInsert1); 
			}
			else if (apptype.equalsIgnoreCase("2")){
			dbUtil.createPreparedStatement(PlockSQL.LOCK_PARTY_TBL_INSERT);
			ins=dbUtil.executeUpdate(partyTblInsert); 
			}
			if(ins){
				 dbUtil.commit();				     
				 lockid=locktxnid;
			}
		  }
		  
		  }
	 }catch (NullPointerException ne) {
		    ne.printStackTrace();
		    dbUtil.rollback();
		   throw ne;
			
	   }
		    catch (SQLException se) {
		    	 se.printStackTrace();
		    	 dbUtil.rollback();
		    	
			logger.error("SQL Exception at estamp  in DAO " + se); 
		 throw se;
		}
	   catch(Exception e){
		   e.printStackTrace();
		   dbUtil.rollback();
		logger.error(" Exception at estamp in DAO " + e);
		throw e;
		}
	   
	   finally {
			 try{	    
				 if (dbUtil != null) {
					 dbUtil.closeConnection();
			 }
				 }
			 catch (Exception e) {
			 logger.error("Exception at estamp in DAO  " + e);
			 }		
	       }
	 
	   return lockid;   
	 }
	 
	 /**
		 * @param registration number, property id, reference id
		 * @return ArrayList
		 * @throws Exception
		 */ 
	 
	 public ArrayList getTxnDetls(String regNo, String propid, String refId,String language) throws Exception{
			String[] tid = new String[3];
			tid[0]=regNo;
			tid[1]=propid;
			tid[2]=refId;
			ArrayList list = new ArrayList();DBUtility dbUtil = null;
			try{
				dbUtil = new DBUtility();
				if("en".equalsIgnoreCase(language))
				{
				dbUtil.createPreparedStatement(PlockSQL.TXN_DETL);
				}
				else
				{
				dbUtil.createPreparedStatement(PlockSQL.TXN_DETL_H);
				}
			    list=dbUtil.executeQuery(tid);
	          }  catch (Exception x) {
					logger.debug(x);
				} finally {
					if (dbUtil != null) {
						dbUtil.closeConnection();
					}
				}list.trimToSize();
			return list;
		}
	 
	 /**
		 * @param registration number, property id, reference id
		 * @return ArrayList
		 * @throws Exception
		 */ 
	 
	 public ArrayList getTxnDetlsV(String regNo, String propid, String refId,String language) throws Exception{
			String[] tid = new String[3];
			tid[0]=regNo;
			tid[1]=propid;
			tid[2]=refId;
			ArrayList list = new ArrayList();DBUtility dbUtil = null;
			try{
				dbUtil = new DBUtility();
				if("en".equalsIgnoreCase(language))
				{
				dbUtil.createPreparedStatement(PlockSQL.TXN_DETL_VIEW);
				}
				else
				{
					dbUtil.createPreparedStatement(PlockSQL.TXN_DETL_VIEW_H);
				}
			    list=dbUtil.executeQuery(tid);
	          }  catch (Exception x) {
					logger.debug(x);
				} finally {
					if (dbUtil != null) {
						dbUtil.closeConnection();
					}
				}list.trimToSize();
			return list;
		}
	 /**
		 * @param registration number, property id, reference id
		 * @return ArrayList
		 * @throws Exception
		 */ 
	 
	 public ArrayList getTxnDetlsR(String regNo, String propid, String refId,String language) throws Exception{
			String[] tid = new String[3];
			tid[0]=regNo;
			tid[1]=propid;
			tid[2]=refId;
			ArrayList list = new ArrayList();DBUtility dbUtil = null;
			try{
				dbUtil = new DBUtility();
				if("en".equalsIgnoreCase(language))
				{
				dbUtil.createPreparedStatement(PlockSQL.TXN_DETL_REL);
				}
				else
				{
					dbUtil.createPreparedStatement(PlockSQL.TXN_DETL_REL_H);
				}
			    list=dbUtil.executeQuery(tid);
	          }  catch (Exception x) {
	        	  x.printStackTrace();
					logger.debug(x);
				} finally {
					if (dbUtil != null) {
						dbUtil.closeConnection();
					}
				}list.trimToSize();
			return list;
		}
	 /**
		 * @param registration number, property id, reference id
		 * @return ArrayList
		 * @throws Exception
		 */ 
	 
	 public ArrayList getTxnDetlsRV(String regNo, String propid, String refId) throws Exception{
			String[] tid = new String[3];
			tid[0]=regNo;
			tid[1]=propid;
			tid[2]=refId;
			ArrayList list = new ArrayList();DBUtility dbUtil = null;
			try{
				dbUtil = new DBUtility();
				dbUtil.createPreparedStatement(PlockSQL.TXN_DETL_REL_VIEW);
			    list=dbUtil.executeQuery(tid);
	          }  catch (Exception x) {
	        	  x.printStackTrace();
					logger.debug(x);
				} finally {
					if (dbUtil != null) {
						dbUtil.closeConnection();
					}
				}list.trimToSize();
			return list;
		}
	 /**
		 * @param registration number, property id, reference id
		 * @return ArrayList
		 * @throws Exception
		 */ 
	 
	 public ArrayList getTxnDetlsRP(String regNo, String propid, String refId,String language) throws Exception{
			String[] tid = new String[3];
			tid[0]=regNo;
			tid[1]=propid;
			tid[2]=refId;
			ArrayList list = new ArrayList();DBUtility dbUtil = null;
			try{
				dbUtil = new DBUtility();
				if("en".equalsIgnoreCase(language))
				{
				dbUtil.createPreparedStatement(PlockSQL.TXN_DETL_REL_P);
				}
				else
				{
					dbUtil.createPreparedStatement(PlockSQL.TXN_DETL_REL_P_H);	
				}
			    list=dbUtil.executeQuery(tid);
	          }  catch (Exception x) {
					logger.debug(x);
				} finally {
					if (dbUtil != null) {
						dbUtil.closeConnection();
					}
				}list.trimToSize();
			return list;
		}
	 /**
		 * @param registration number, property id, reference id
		 * @return ArrayList
		 * @throws Exception
		 */ 
	 
	 public ArrayList getTxnDetlsAP(String regNo, String propid, String refId,String language) throws Exception{
			String[] tid = new String[3];
			tid[0]=regNo;
			tid[1]=propid;
			tid[2]=refId;
			ArrayList list = new ArrayList();DBUtility dbUtil = null;
			try{
				dbUtil = new DBUtility();
				if("en".equalsIgnoreCase(language))
				{
				dbUtil.createPreparedStatement(PlockSQL.TXN_DETL_AP);
				}
				else
				{
					dbUtil.createPreparedStatement(PlockSQL.TXN_DETL_AP_H);	
				}
			    list=dbUtil.executeQuery(tid);
	          }  catch (Exception x) {
					logger.debug(x);
				} finally {
					if (dbUtil != null) {
						dbUtil.closeConnection();
					}
				}list.trimToSize();
			return list;
		}
	 /**
		 * @param registration number, property id, reference id
		 * @return ArrayList
		 * @throws Exception
		 */ 
	 
	 public ArrayList getTxnDetlsP(String regNo, String propid, String refId,String language) throws Exception{
			String[] tid = new String[3];
			tid[0]=regNo;
			tid[1]=propid;
			tid[2]=refId;
			ArrayList list = new ArrayList();DBUtility dbUtil = null;
			try{
				dbUtil = new DBUtility();
				if("en".equalsIgnoreCase(language))
				{
				dbUtil.createPreparedStatement(PlockSQL.TXN_DETLP);
				}
				else
				{
					dbUtil.createPreparedStatement(PlockSQL.TXN_DETLP_H);
				}
			    list=dbUtil.executeQuery(tid);
	          }  catch (Exception x) {
					logger.debug(x);
				} finally {
					if (dbUtil != null) {
						dbUtil.closeConnection();
					}
				}list.trimToSize();
			return list;
		}
	 /**
		 * @param registration number,reference id
		 * @return ArrayList
		 * @throws Exception
		 */ 
	 
	 public ArrayList getPartyDetls(String regNo,String refId,String language) throws Exception{
			String[] tid = new String[1];
			//tid[0]=regNo;
			tid[0]=refId;
			
			ArrayList list = new ArrayList();DBUtility dbUtil = null;
			try{
				dbUtil = new DBUtility();
				if("en".equalsIgnoreCase(language))
				{
				dbUtil.createPreparedStatement(PlockSQL.PARTY_DETL);
				}
				else
				{
					dbUtil.createPreparedStatement(PlockSQL.PARTY_DETL_H);
				}
			    list=dbUtil.executeQuery(tid);
	          }  catch (Exception x) {
					logger.debug(x);
				} finally {
					if (dbUtil != null) {
						dbUtil.closeConnection();
					}
				}list.trimToSize();
			return list;
		}
	 /**
		 * @param registration number,reference id
		 * @return ArrayList
		 * @throws Exception
		 */ 
	 
	 public ArrayList getRelativeDetls(String regNo,String refId,String language) throws Exception{
			String[] tid = new String[1];
			//tid[0]=regNo;
			tid[0]=refId;
			
			ArrayList list = new ArrayList();DBUtility dbUtil = null;
			try{
				dbUtil = new DBUtility();
				if("en".equalsIgnoreCase(language))
				{
				dbUtil.createPreparedStatement(PlockSQL.RELATIVE_DETL);
				}
				else
				{
				dbUtil.createPreparedStatement(PlockSQL.RELATIVE_DETL_H);	
				}
			    list=dbUtil.executeQuery(tid);
	          }  catch (Exception x) {
					logger.debug(x);
				} finally {
					if (dbUtil != null) {
						dbUtil.closeConnection();
					}
				}list.trimToSize();
			return list;
		}
	 /**
		 * @param reference id
		 * @return ArrayList
		 * @throws Exception
		 */ 
	 
	 public ArrayList getPhotoDetl(String refId,String language) throws Exception{
			String[] tid = new String[1];
			//tid[0]=regNo;
			tid[0]=refId;
			
			ArrayList list = new ArrayList();DBUtility dbUtil = null;
			try{
				dbUtil = new DBUtility();
				if("en".equalsIgnoreCase(language))
				{
				dbUtil.createPreparedStatement(PlockSQL.PHOTO_DETL);
				}
				else
				{
				dbUtil.createPreparedStatement(PlockSQL.PHOTO_DETL_H);
				}
			    list=dbUtil.executeQuery(tid);
	          }  catch (Exception x) {
					logger.debug(x);
				} finally {
					if (dbUtil != null) {
						dbUtil.closeConnection();
					}
				}list.trimToSize();
			return list;
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
				ArrayList list = new ArrayList();DBUtility dbUtil = null;
				try{
					dbUtil = new DBUtility();
				
					dbUtil.createPreparedStatement(PlockSQL.GET_PAY_DTLS);
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
		 /**
			 * @param regcomp no
			 *
			 * @return
			 * @throws Exception
			 */
			 public ArrayList getrequestDetails(String txnId) throws Exception{
					String[] tid = new String[1];
					tid[0]=txnId;
					ArrayList list = new ArrayList();DBUtility dbUtil = null;
					try{
					dbUtil = new DBUtility();
					dbUtil.createPreparedStatement(PlockSQL.REQ_DETL_QRY);
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
		 
		 /**
			 * @param regcomp no
			 *
			 * @return
			 * @throws Exception
			 */
			 public ArrayList getPayDtlsV(String txnId) throws Exception{
					String[] tid = new String[1];
					tid[0]=txnId;
					
					ArrayList list = new ArrayList();DBUtility dbUtil = null;
					try{
						dbUtil = new DBUtility();
					
						dbUtil.createPreparedStatement(PlockSQL.GET_PAY_DTLS_V);
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
		 /******************************************************************  
		  *   Method Name  :   getPendingDetls()
		  *   Arguments    :  
		  *   Return       :   ArrayList
		  *   Throws 	  :   NullPointer  or SQLException or Exception
		*******************************************************************/  
		 public ArrayList getPendingDetls(String uid,String language) throws Exception{
				String[] tid = new String[1];
				tid[0]=uid;
				ArrayList list = new ArrayList();DBUtility dbUtil = null;
				try{
					dbUtil = new DBUtility();
					if("en".equalsIgnoreCase(language))
					{
					dbUtil.createPreparedStatement(PlockSQL.PENDING_DETL);
					}
					else
					{
					dbUtil.createPreparedStatement(PlockSQL.PENDING_DETL_H);
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
		 /******************************************************************  
		  *   Method Name  :   getPendingDetlsR()
		  *   Arguments    :  
		  *   Return       :   ArrayList
		  *   Throws 	  :   NullPointer  or SQLException or Exception
		*******************************************************************/  
		 public ArrayList getPendingDetlsR(String uid,String language) throws Exception{
				String[] tid = new String[1];
				tid[0]=uid;
				ArrayList list = new ArrayList();DBUtility dbUtil = null;
				try{
					dbUtil = new DBUtility();
					if("en".equalsIgnoreCase(language))
					{
					dbUtil.createPreparedStatement(PlockSQL.PENDING_DETL_R);
					}
					else
					{
						dbUtil.createPreparedStatement(PlockSQL.PENDING_DETL_R_H);
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
		 /******************************************************************  
		  *   Method Name  :   getId()
		  *   Arguments    :  
		  *   Return       :   String
		  *   Throws 	  :   NullPointer  or SQLException or Exception
		*******************************************************************/  
		public  String getId()throws NullPointerException,
		SQLException,Exception{
			String number1="";
			DBUtility dbUtil = null;
			try{
				dbUtil = new DBUtility();
				String SQL1 = "select IGRS_PROP_LCK_PAYMENT_ID_SEQ.nextval from dual";
				dbUtil.createStatement();
			    number1 = dbUtil.executeQry(SQL1);
			
			}catch (NullPointerException ne) {
			    ne.printStackTrace();
			    dbUtil.rollback();
			   throw ne;
				
			}
			    catch (SQLException se) {
			    	 se.printStackTrace();
			    	 dbUtil.rollback();
			    	
				logger.error("SQL Exception  in DAO " + se); 
			 throw se;
			}
			catch(Exception e){
			   e.printStackTrace();
			   dbUtil.rollback();
			logger.error(" Exception  in DAO " + e);
			throw e;
			}

			 finally {
				 try{	    
					 if (dbUtil != null) {
						 dbUtil.closeConnection();
				 }
					 }
				 catch (Exception e) {
				 logger.error("Exception  DAO  " + e);
				 }		
		       }
			
			
			return number1;
			
		}
		
		/******************************************************************  
		  *   Method Name  :   insertPay()
		  *   Arguments    :  reg no, fee, uid, funId 
		  *   Return       :   Boolean
		  *   Throws 	  :   NullPointer  or SQLException or Exception
		*******************************************************************/  

		public  boolean insertPay(String lockId, String fee, String uid,String id, String funId)throws NullPointerException,
		SQLException,Exception{
		 
		boolean transactionflag = false;
		//String estmTxnId = null;
		String paytable[] = new String[6];
		DBUtility dbUtil = null;
		try{
			dbUtil = new DBUtility();
			dbUtil.setAutoCommit(false);
		
		paytable[0] =id;
		paytable[1] =lockId;
		paytable[2] =fee;
		paytable[3] ="I";
		paytable[4] =uid;
		paytable[5] =funId;

		dbUtil.createPreparedStatement(PlockSQL.PAY_TABLE_INSERT);
		boolean up=dbUtil.executeUpdate(paytable);
		if(up){ 
		transactionflag = true;
		dbUtil.commit();				     
		}
		}catch (NullPointerException ne) {
		    ne.printStackTrace();
		    dbUtil.rollback();
		   throw ne;
			
		}
		    catch (SQLException se) {
		    	 se.printStackTrace();
		    	 dbUtil.rollback();
		    	
			logger.error("SQL Exception in DAO " + se); 
		 throw se;
		}
		catch(Exception e){
		   e.printStackTrace();
		   dbUtil.rollback();
		logger.error(" Exception in DAO " + e);
		throw e;
		}

		 finally {
			 try{	    
				 if (dbUtil != null) {
					 dbUtil.closeConnection();
			 }
				 }
			 catch (Exception e) {
			 logger.error("Exception in DAO  " + e);
			 }		
	       }
		
		return transactionflag;   
		}
	
		/******************************************************************  
		  *   Method Name  :   statusUpdateAfterP()
		  *   Arguments    :  lock id, user id, office id
		  *   Return       :   Boolean
		  *   Throws 	  :   NullPointer  or SQLException or Exception
		*******************************************************************/  

		public  boolean statusUpdateAfterP(String lockId, String uid, String offid)throws NullPointerException,
		SQLException,Exception{
		 
		boolean transactionflag = false;
		String paytable[] = new String[2];
		paytable[0]=uid;
		paytable[1]=lockId;
		
		String txnTbl[] = new String[3];
		txnTbl[0]=uid;
		txnTbl[1]=offid;
		txnTbl[2]=lockId;
		
		DBUtility dbUtil = null;
		try{
			dbUtil = new DBUtility();
			dbUtil.setAutoCommit(false);
		    dbUtil.createPreparedStatement(PlockSQL.TXN_STATUS_UPDATE);
		    transactionflag=dbUtil.executeUpdate(txnTbl);
		    if(transactionflag){ 
			dbUtil.createPreparedStatement(PlockSQL.PAY_FLAG_UPDTAE);
			transactionflag=dbUtil.executeUpdate(paytable);
		    if(transactionflag){
		    dbUtil.commit();				     
		}
		    }
		}catch (NullPointerException ne) {
		    ne.printStackTrace();
		    dbUtil.rollback();
		   throw ne;
			
		}
		    catch (SQLException se) {
		    	 se.printStackTrace();
		    	 dbUtil.rollback();
		    	
			logger.error("SQL Exception in DAO " + se); 
		 throw se;
		}
		catch(Exception e){
		   e.printStackTrace();
		   dbUtil.rollback();
		logger.error(" Exception in DAO " + e);
		throw e;
		}

		 finally {
			 try{	    
				 if (dbUtil != null) {
					 dbUtil.closeConnection();
			 }
				 }
			 catch (Exception e) {
			 logger.error("Exception in DAO  " + e);
			 }		
	       }
		
		return transactionflag;   
		}
		
		/******************************************************************  
		  *   Method Name  :   statusUpdateAfterPR()
		  *   Arguments    :  lock id, user id, office id
		  *   Return       :   Boolean
		  *   Throws 	  :   NullPointer  or SQLException or Exception
		*******************************************************************/  

		public  boolean statusUpdateAfterPR(String lockId, String uid, String offid)throws NullPointerException,
		SQLException,Exception{
		 
		boolean transactionflag = false;
		String paytable[] = new String[2];
		paytable[0]=uid;
		paytable[1]=lockId;
		
		String txnTbl[] = new String[3];
		txnTbl[0]=uid;
		txnTbl[1]=offid;
		txnTbl[2]=lockId;
		
		DBUtility dbUtil = null;
		try{
			dbUtil = new DBUtility();
			dbUtil.setAutoCommit(false);
		    dbUtil.createPreparedStatement(PlockSQL.TXN_STATUS_UPDATE_REL);
		    transactionflag=dbUtil.executeUpdate(txnTbl);
		    if(transactionflag){ 
			dbUtil.createPreparedStatement(PlockSQL.PAY_FLAG_UPDTAE);
			transactionflag=dbUtil.executeUpdate(paytable);
		    if(transactionflag){
		    dbUtil.commit();				     
		}
		    }
		}catch (NullPointerException ne) {
		    ne.printStackTrace();
		    dbUtil.rollback();
		   throw ne;
			
		}
		    catch (SQLException se) {
		    	 se.printStackTrace();
		    	 dbUtil.rollback();
		    	
			logger.error("SQL Exception in DAO " + se); 
		 throw se;
		}
		catch(Exception e){
		   e.printStackTrace();
		   dbUtil.rollback();
		logger.error(" Exception in DAO " + e);
		throw e;
		}

		 finally {
			 try{	    
				 if (dbUtil != null) {
					 dbUtil.closeConnection();
			 }
				 }
			 catch (Exception e) {
			 logger.error("Exception in DAO  " + e);
			 }		
	       }
		
		return transactionflag;   
		}
		/******************************************************************  
		  *   Method Name  :   getMainId()
		  *   Arguments    :  txn id
		  *   Return       :   Boolean
		  *   Throws 	  :   NullPointer  or SQLException or Exception
		*******************************************************************/  

		public String getMainId(String txnId) throws Exception{
			String[] tid = new String[1];
			tid[0]=txnId;
			String dty;DBUtility dbUtil = null;
			try{
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(PlockSQL.GET_MAIN_ID);
			dty=dbUtil.executeQry(tid);
			}catch(Exception e){
				   e.printStackTrace();
				   dbUtil.rollback();
				logger.error(" Exception in DAO " + e);
				throw e;
				}

				 finally {
					 try{	    
						 if (dbUtil != null) {
							 dbUtil.closeConnection();
					 }
						 }
					 catch (Exception e) {
					 logger.error("Exception in  DAO  " + e);
					 }		
			       }
			return dty;
		}
		
	    
	    
	    /**
		 * 
		 * @return ArrayList
		 * @throws Exception
		 */
		public synchronized ArrayList getRelationType() throws Exception {
			if (logger.isDebugEnabled()) {
				logger.debug("getCountry() - start");
			}
			DBUtility dbUtil = null;
			ArrayList list = new ArrayList();
			try {
				
				dbUtil=new DBUtility();
	            String str=NoEncumSQL.IGRS_RELATION_MASTER; // Query for Country list from IGRS_COUNTRY_MASTER
	            dbUtil.createStatement();
	            list=dbUtil.executeQuery(str);
				
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
	    
	//added by shruti
	public static String convertedDate(Object date) throws Exception {
		DBUtility dbUtil=null;
		dbUtil=new DBUtility();
		try
		{
	        SimpleDateFormat date1 = new SimpleDateFormat ("yyyy-MM-dd");
	        SimpleDateFormat date2 = new SimpleDateFormat ("dd/MM/yyyy");
	        String reqDate="";
	        if(date!=null && !date.equals(""))
	        {
	        reqDate=date.toString();
	        reqDate=reqDate.substring(0, 10);
	        }
	       
	        //Date d1 = date1.parse(reqDate.toString());
	        //String formatDate = date2.format(d1);
			//System.out.println("formatted date=----->"+formatDate);
			//return formatDate;
	        return reqDate;
		}
	        finally {
				 try{	    
					 if (dbUtil != null) {
						 dbUtil.closeConnection();
				 }
					 }
				 catch (Exception e) {
				 System.out.print("Exception in closing connection  " + e);
				 }	
			}
		}
	//end

}
