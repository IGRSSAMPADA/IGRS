/**
 * Copyright (c) 2009-10 WIPRO INFOTECH. All Rights Reserved.
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
 * Customer specific copyright notice - NA
 */
package com.wipro.igrs.caveats.dao;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.TreeMap;

import javax.servlet.http.HttpSession;

import oracle.jdbc.driver.OracleTypes;

import org.apache.log4j.Logger;
import org.apache.struts.upload.FormFile;

import com.wipro.igrs.caveats.constant.CaveatsConstant;
import com.wipro.igrs.caveats.dto.CaveatsDTO;
import com.wipro.igrs.caveats.dto.OTTDetailDTO;
import com.wipro.igrs.caveats.form.CaveatsForm;
import com.wipro.igrs.caveats.sql.CommonSQL;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.newPropvaluation.sql.PropertyValuationSQL;
import com.wipro.igrs.newreginit.constant.RegInitConstant;
import com.wipro.igrs.newreginit.sql.RegCommonSQL;
import com.wipro.igrs.propertylock.sql.PlockSQL;

/**
* 
* CaveatsDAO.java <br>
* CaveatsDAO <br>
* 
* @version 1.0
* 
* @author <b>WIPRO JAVA TEAM</b> <br>
*         Created on 12-Apr-2008 <br>
*         Last Modified on 28-Mar-2013
*/
@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
public class CaveatsDAO {
	/**
	 * 
	 */
	
	String SQL;
	String SQL1;
	String transactionID;
	String bal;

    public CaveatsDAO() {}
    private static Logger logger = 
		(Logger) Logger.getLogger(CaveatsDAO.class);
    PreparedStatement pst = null;
    /**
     * 
     * @param _caveatsDto
     * @param payAmount 
     * @return
     * @throws Exception
     */
    public boolean logCaveats(CaveatsDTO _caveatsDto,String userId,String functionId, String payAmount) throws Exception {
		logger.debug("Inside logCaveats");
		boolean retVal = false;
		DBUtility dbUtil = null;
		try {
			 dbUtil =new DBUtility();
			retVal = dbUtil.insertCaveatData(_caveatsDto, userId, functionId,payAmount);
			dbUtil.closeConnection();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		finally
		{
			dbUtil.closeConnection();
		}
		return retVal;
	}
    /**
     * 
     * @param param1
     * @param param2
     * @param fileName
     * @param file
     * @return
     * @throws SQLException
     * @throws IOException
     * @throws Exception
     */
    public boolean logCaveatsDAO(String[] param1, String param2[], String fileName, File file) throws SQLException,IOException, Exception {
    	logger.debug("IN DAO logCaveats");
        DBUtility dbUtil=null;
        boolean log=false;
        try{
            dbUtil =new DBUtility();
            String qry1=CommonSQL.CAVEATS_CHARGES_INSERT;
            dbUtil.createPreparedStatement(qry1);
            if(dbUtil.executeUpdate(param1))
            {
            	 String qry2=CommonSQL.CAVEATS_CHARGES_INSERT_PROPERTYID;
                 dbUtil.createPreparedStatement(qry2);
                 if(dbUtil.executeUpdate(param2))
                 {
                	 String qry3=CommonSQL.CAVEATS_CHARGES_INSERT_FILE;
                	 FileInputStream fis = new FileInputStream(file);
            		 pst =  dbUtil.returnPreparedStatement(qry3);
            		 pst.setString(1, fileName);
            		 pst.setBinaryStream(2,fis,(int)fis.available());
            		 pst.setString(3,param1[0]);
                     if(pst.executeUpdate()>0)
                     {
                    	 log=true;
                     }
                 }
            }
            if(log)
            	dbUtil.commit();
            else
            	dbUtil.rollback();
            return log;
        }catch(IOException ie){
        	logger.error(ie);
        }
        catch(SQLException se){
        	logger.error(se);
        }
        catch(Exception e){
            logger.error(e);
        }
        finally {
    		logger.debug("using FINALLY Connection is closed");
            dbUtil.closeConnection(); 
        }
        return log;
    }
    /**
     * 
     * @return
     * @throws Exception
     */
    public ArrayList createCaveatsList(String language)throws Exception{
    	logger.debug("IN DAO createCaveatsList");
        ArrayList ar1=new ArrayList();
        DBUtility dbUtil=null;
      
        try {
            ArrayList typeList=new ArrayList();
            ArrayList typeTemp=new ArrayList();
           
            dbUtil=new DBUtility();
            
            if(language.equalsIgnoreCase("en"))
            {
            String str=CommonSQL.IGRS_CAVEATS_CAVEAT_MASTER;
                   	
            
            dbUtil.createStatement();
            typeList=dbUtil.executeQuery(str);
            if(typeList!=null){
                for(int i=0;i<typeList.size();i++) {
                    typeTemp=new ArrayList();
                    typeTemp=(ArrayList)typeList.get(i);
                    if(typeTemp.size()>0){
                        CaveatsDTO type = new CaveatsDTO();
                        type.setCaveatType((String)typeTemp.get(0));
                        type.setCaveatId((String)typeTemp.get(1));
                        type.setCaveatStayOrderFlag((String)typeTemp.get(2));
                        type.setCaveatSorderStatus((String)typeTemp.get(0)+"~"+(String)typeTemp.get(2)+"^"+(String)typeTemp.get(1));
                        ar1.add(type);
                    }
                }
            }
        }
            else
            {
            	String str=CommonSQL.IGRS_CAVEATS_CAVEAT_MASTER_hi;
               	
                
                dbUtil.createStatement();
                typeList=dbUtil.executeQuery(str);
                if(typeList!=null){
                    for(int i=0;i<typeList.size();i++) {
                        typeTemp=new ArrayList();
                        typeTemp=(ArrayList)typeList.get(i);
                        if(typeTemp.size()>0){
                            CaveatsDTO type = new CaveatsDTO();
                            type.setCaveatType((String)typeTemp.get(0));
                            type.setCaveatId((String)typeTemp.get(1));
                            type.setCaveatStayOrderFlag((String)typeTemp.get(2));
                            type.setCaveatSorderStatus((String)typeTemp.get(0)+"~"+(String)typeTemp.get(2)+"^"+(String)typeTemp.get(1));
                            ar1.add(type);
                        }
                    }
                }
            
            }
        }catch(IOException ie){
        	logger.error(ie);
        }
        catch(SQLException se){
        	logger.error(se);
        }
        catch(Exception e){
            logger.error(e);
        }
        finally {
    		logger.debug("using FINALLY Connection is closed");
            dbUtil.closeConnection(); 
        }
        return ar1;
    }
    
    
  
    /**
     * 
     * @return
     * @throws Exception
     */
    public ArrayList countryList(String language)throws Exception{
    	logger.debug("IN DAO countryList");
        ArrayList ar1=new ArrayList();
        DBUtility dbUtil=null;
        try {
            ArrayList typeList=new ArrayList();
            ArrayList typeTemp=new ArrayList();
           
            dbUtil=new DBUtility();
            if(language.equalsIgnoreCase("en"))
            {
            String str=CommonSQL.IGRS_COUNTRY_MASTER; // Query for Country list from IGRS_COUNTRY_MASTER
            dbUtil.createStatement();
            typeList=dbUtil.executeQuery(str);
            if(typeList!=null){
                for(int i=0;i<typeList.size();i++) {
                    typeTemp=new ArrayList();
                    typeTemp=(ArrayList)typeList.get(i);
                    if(typeTemp.size()>0){
                        CaveatsDTO type = new CaveatsDTO();
                        type.setCountryId((String)typeTemp.get(0));
                        type.setCountryName((String)typeTemp.get(1));
                        ar1.add(type);
                    }
                }
            }
        }
            else
            {
            	String str=CommonSQL.IGRS_COUNTRY_MASTER_hi; // Query for Country list from IGRS_COUNTRY_MASTER
                dbUtil.createStatement();
                typeList=dbUtil.executeQuery(str);
                if(typeList!=null){
                    for(int i=0;i<typeList.size();i++) {
                        typeTemp=new ArrayList();
                        typeTemp=(ArrayList)typeList.get(i);
                        if(typeTemp.size()>0){
                            CaveatsDTO type = new CaveatsDTO();
                            type.setCountryId((String)typeTemp.get(0));
                            type.setCountryName((String)typeTemp.get(1));
                            ar1.add(type);
                        }
                    }
                }
            }
        }
            catch(IOException ie){
        	logger.error(ie);
        }
        catch(SQLException se){
        	logger.error(se);
        }
        catch(Exception e){
            logger.error(e);
        }
        finally {
    		logger.debug("using FINALLY Connection is closed");
            dbUtil.closeConnection(); 
        }
        return ar1;
    }
    /**
     * 
     * @param countryIdVar
     * @return
     * @throws Exception
     */
    public ArrayList stateList(String countryIdVar,String language)throws Exception{
    	logger.debug("IN DAO stateList");
        ArrayList ar1=new ArrayList();
        DBUtility dbUtil=null;
      
        try {
            ArrayList typeList=new ArrayList();
            ArrayList typeTemp=new ArrayList();
            dbUtil=new DBUtility();
            if(language.equalsIgnoreCase("en"))
            {
            String str=CommonSQL.IGRS_STATE_MASTER; // Query for Country list from IGRS_COUNTRY_MASTER
            String param[]=new String[1];
            param[0]=""+countryIdVar;
            dbUtil.createPreparedStatement(str);
            typeList=dbUtil.executeQuery(param);
            if(typeList!=null){
                for(int i=0;i<typeList.size();i++) {
                    typeTemp=new ArrayList();
                    typeTemp=(ArrayList)typeList.get(i);
                    if(typeTemp.size()>0){
                        CaveatsDTO type = new CaveatsDTO();
                        type.setStateId((String)typeTemp.get(0));
                        type.setStateName((String)typeTemp.get(1));
                        ar1.add(type);
                    }
                }
            }
        }
            else
            {
            	String str=CommonSQL.IGRS_STATE_MASTER_hi; // Query for Country list from IGRS_COUNTRY_MASTER
                String param[]=new String[1];
                param[0]=""+countryIdVar;
                dbUtil.createPreparedStatement(str);
                typeList=dbUtil.executeQuery(param);
                if(typeList!=null){
                    for(int i=0;i<typeList.size();i++) {
                        typeTemp=new ArrayList();
                        typeTemp=(ArrayList)typeList.get(i);
                        if(typeTemp.size()>0){
                            CaveatsDTO type = new CaveatsDTO();
                            type.setStateId((String)typeTemp.get(0));
                            type.setStateName((String)typeTemp.get(1));
                            ar1.add(type);
                        }
                    }
                }	
            }
        }catch(IOException ie){
        	logger.error(ie);
        }
        catch(SQLException se){
        	logger.error(se);
        }
        catch(Exception e){
            logger.error(e);
        }
        finally {
    		logger.debug("using FINALLY Connection is closed");
            dbUtil.closeConnection(); 
        }
        return ar1;
    }
    /**
     * 
     * @param refId
     * @return
     * @throws Exception
     */
    public ArrayList findCaveatDAO(String refId) throws Exception {
    	logger.debug("IN DAO findCaveat");
    	ArrayList list =null;
    	DBUtility dbUtil = new DBUtility();
        try {
            String qury = new String(CommonSQL.IGRS_CAVEATS_SEARCH_REFID);
            String param[]=new String[1];
            param[0]=""+refId;
            
            dbUtil.createPreparedStatement(qury);
            list = dbUtil.executeQuery(param);
	    }catch(IOException ie){
	    	logger.error(ie);
	    }
	    catch(SQLException se){
	    	logger.error(se);
	    }
	    catch(Exception e){
	        logger.error(e);
	    }
	    finally {
			logger.debug("using FINALLY Connection is closed");
	        dbUtil.closeConnection(); 
	    }
        return list;
    }
    /**
     * 
     * @param searchFields
     * @return
     * @throws IOException
     * @throws SQLException
     * @throws Exception
     */
    public ArrayList releaseSearchByAllDAO(String searchFields[])throws IOException,SQLException,Exception
    {
    	logger.debug("IN DAO releaseSearchByAllDAO");
        ArrayList list=new ArrayList();
        CaveatsDTO cDTO=new CaveatsDTO();
        list=null;
        ArrayList list2=new ArrayList();
        String regNo=searchFields[1];
        	if(regNo == null || regNo.equalsIgnoreCase("null")) regNo="";
        String frDate=searchFields[2];
        	if(frDate == null || frDate.equalsIgnoreCase("null")) frDate="";
        String toDate=searchFields[3];
        	if(toDate == null || toDate.equalsIgnoreCase("null")) toDate="";
        String errMessages=null;
        DBUtility dbUtil = null;
        try{
	         dbUtil = new DBUtility();
	        if(regNo.length() > 0)
	        {
	            String param[]=new String[1];
	            String query=CommonSQL.IGRS_CAVEATS_RELEASE_BY_REGNO;
	            param[0]=""+regNo;
	            dbUtil.createPreparedStatement(query);
	            list = dbUtil.executeQuery(param);
	            errMessages="Caveat has not found for given REGISTRATION NUMBER";
	        }
	        else if(frDate.length() > 0 && toDate.length() > 0)
	        {
	            String param[]=new String[2];
	            String query=CommonSQL.IGRS_CAVEATS_RELEASE_BY_DURATION;
	            param[0]=""+frDate;
	            param[1]=""+toDate;
	            dbUtil.createPreparedStatement(query);
	            list = dbUtil.executeQuery(param);
	            errMessages="Caveat has not found for given DURATION";
	        }
	        if(list.isEmpty())
	        {
	        	cDTO.setErrorMsg(errMessages);
	        	list2.add(cDTO);
	        }
	        else
	        {
	        	try{
	        		ArrayList typeTemp=new ArrayList();
	        		if(list!=null){
		                for(int i=0;i<list.size();i++) {
		                    typeTemp=new ArrayList();
		                    typeTemp=(ArrayList)list.get(i);
		                    if(typeTemp.size()>0){
		                        CaveatsDTO type = new CaveatsDTO();
		                        type.setRegistrationNumber((String)typeTemp.get(0));
		                        type.setFlag((String)typeTemp.get(1));
		                        type.setReferenceID((String)typeTemp.get(2));
		                        type.setLoggedDate((String)typeTemp.get(3));
		                        type.setSerialNo(i+1);
		                        list2.add(type);
		                    }
		                }
	        		}
	        	}
			    catch(Exception e){
			        logger.error(e);
			    }
			    finally {
					logger.debug("using FINALLY Connection is closed");
			        dbUtil.closeConnection(); 
			    }
	        }
	        return list2;
        }catch(IOException ie){
	    	logger.error(ie);
	    }
	    catch(SQLException se){
	    	logger.error(se);
	    }
	    catch(Exception e){
	        logger.error(e);
	    }
	    finally {
			logger.debug("Finally Called");
	    }
	    return null;
    }
    /**
     * 
     * @param searchFields
     * @return
     */
    public ArrayList relsCavtBankByAllDAO	(String searchFields[])
    {
    	logger.debug("IN DAO relsCavtBankByAllDAO");
    	ArrayList list=new ArrayList();
        CaveatsDTO cDTO=new CaveatsDTO();
        list=null;
        ArrayList list2=new ArrayList();
        String regNo=searchFields[1];
        	if(regNo == null || regNo.equalsIgnoreCase("null")) regNo="";
        String frDate=searchFields[2];
        	if(frDate == null || frDate.equalsIgnoreCase("null")) frDate="";
        String toDate=searchFields[3];
        	if(toDate == null || toDate.equalsIgnoreCase("null")) toDate="";
        String errMessages=null;
        try{
	        DBUtility dbUtil = new DBUtility();
	        if(regNo.length() > 0)
	        {
	            String param[]=new String[1];
	            String query=CommonSQL.IGRS_CAVEATS_BANK_RELEASE_REGNO;
	            param[0]=""+regNo;
	            dbUtil.createPreparedStatement(query);
	            list = dbUtil.executeQuery(param);
	            errMessages="Caveat has not found for given REGISTRATION NUMBER";
	        }
	        else if(frDate.length() > 0 && toDate.length() > 0)
	        {
	            String param[]=new String[2];
	            String query=CommonSQL.IGRS_CAVEATS_BANK_RELEASE_DURATION;
	            param[0]=""+frDate;
	            param[1]=""+toDate;
	            dbUtil.createPreparedStatement(query);
	            list = dbUtil.executeQuery(param);
	            errMessages="Caveat has not found for given DURATION";
	        }
	        if(list.isEmpty())
	        {
	        	cDTO.setErrorMsg(errMessages);
	        	list2.add(cDTO);
	        }
	        else
	        {
	        	try{
	        		ArrayList typeTemp=new ArrayList();
	        		if(list!=null){
		                for(int i=0;i<list.size();i++) {
		                    typeTemp=new ArrayList();
		                    typeTemp=(ArrayList)list.get(i);
		                    if(typeTemp.size()>0){
		                        CaveatsDTO type = new CaveatsDTO();
		                        type.setRegistrationNumber((String)typeTemp.get(0));
		                        type.setFlag((String)typeTemp.get(1));
		                        type.setReferenceID((String)typeTemp.get(2));
		                        type.setLoggedDate((String)typeTemp.get(3));
		                        type.setSerialNo(i+1);
		                        list2.add(type);
		                    }
		                }
	        		}
	        	}
	        	catch (NullPointerException e) {
	        		logger.error(e);
				}
	        	catch(Exception e){
	        		logger.error(e);
	        	}
	        	finally{
	        		dbUtil.closeConnection();
	        		logger.debug("using Finally Connection is closed");
	        	}
	        }
	        return (list2);
        }catch(Exception e) {
        	logger.error(e);
            return(list2);
        }
    }
    /**
     * 
     * @param RegNo
     * @return
     * @throws IOException
     * @throws SQLException
     * @throws Exception
     */
    public ArrayList releaseByRegnoDAO(String RegNo)throws IOException,SQLException,Exception
    {
    	logger.debug("IN DAO releaseByRegnoDAO");
        ArrayList list=null;
        DBUtility dbUtil = new DBUtility();
        try{
	        String param[]=new String[1];
	        String query=CommonSQL.IGRS_CAVEATS_RELEASE_BY_REGNO;
	        param[0]=""+RegNo;
	        dbUtil.createPreparedStatement(query);
	        list = dbUtil.executeQuery(param);
	        return (list);
        }catch(IOException ie){
	    	logger.error(ie);
	    }
	    catch(SQLException se){
	    	logger.error(se);
	    }
	    catch(Exception e){
	        logger.error(e);
	    }
	    finally {
			logger.debug("using FINALLY Connection is closed");
	        dbUtil.closeConnection(); 
	    }
	    return list;
    }
    /**
     * 
     * @param RegNo
     * @return
     * @throws IOException
     * @throws SQLException
     * @throws Exception
     */
    public ArrayList searchRegIdDao(String RegNo)throws IOException,SQLException,Exception
    {
    	logger.debug("IN DAO searchRegIdDao");
        ArrayList list=null;
        DBUtility dbUtil = new DBUtility();
        try{
//	        String query=CommonSQL.IGRS_CAVEATS_REGID_SEARCH_IN_REG_TABLE;
	        String query = CommonSQL.GET_CAVEATS_REGID_PROPERTYID;
			dbUtil.createPreparedStatement(query );
	        list = dbUtil.executeQuery(new String[]{RegNo});
	        return (list);
        }catch(IOException ie){
	    	logger.error(ie);
	    }
	    catch(SQLException se){
	    	logger.error(se);
	    }
	    catch(Exception e){
	        logger.error(e);
	    }
	    finally {
			logger.debug("using FINALLY Connection is closed");
	        dbUtil.closeConnection(); 
	    }
	    return list;
    }
    /**
     * 
     * @param caveatDTO
     * @return
     * @throws Exception
     */
    public boolean modifyFlagDAO(CaveatsDTO caveatDTO)throws Exception{
    	logger.debug("IN DAO modifyFlagDAO");
    	try {
			DBUtility dbUtil = new DBUtility();
			try {
				String query = CommonSQL.IGRS_CAVEATS_UPDATE_RELEASE;
				return dbUtil.releaseCaveat(query, caveatDTO);
			} catch (Exception e) {
				logger.error(e);
			} finally {
				logger.debug("using FINALLY Connection is closed");
				dbUtil.closeConnection();
			}
		} catch (Exception e) {
		}
		return false;
    }
    
    
    
    
    
    public boolean updateFlagDAO(CaveatsDTO caveatDTO)throws Exception{
    	logger.debug("IN DAO modifyFlagDAO");
    	try {
			DBUtility dbUtil = new DBUtility();
		transactionID=caveatDTO.getTransactionID().toString();
		//transactionID=caveatDTO.getReferenceID().toString();
			try {
				String query = CommonSQL.CAVEATS_PAYMENT_STATUS;
				
				//return dbUtil.updateCaveat(query, caveatDTO);
				dbUtil.createPreparedStatement(query);
				
				
				dbUtil.executeUpdateTrans(transactionID);
				//return dbUtil.flagUpdate(query, caveatDTO);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e);
			} finally {
				logger.debug("using FINALLY Connection is closed");
				dbUtil.closeConnection();
			}
		} catch (Exception e) {
		}
		return false;
    }
    public boolean updateStatusDAO(CaveatsDTO caveatDTO)throws Exception{
    	logger.debug("IN DAO modifyFlagDAO");
    	try {
			DBUtility dbUtil = new DBUtility();
		transactionID=caveatDTO.getTransactionID().toString();
		//transactionID=caveatDTO.getReferenceID().toString();
			
			try {
				String query = CommonSQL.CAVEATS_LOG_STATUS;
				
				//return dbUtil.updateCaveat(query, caveatDTO);
				
				dbUtil.createPreparedStatement(query);
				
				dbUtil.executeUpdateTrans(transactionID);
				//return dbUtil.flagUpdate(query, caveatDTO);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e);
			} finally {
				logger.debug("using FINALLY Connection is closed");
				dbUtil.closeConnection();
			}
		} catch (Exception e) {
		}
		return false;
    }
    ///
    
    public boolean updateFlagDAO1(CaveatsDTO caveatDTO)throws Exception{
    	logger.debug("IN DAO modifyFlagDAO");
    	try {
			DBUtility dbUtil = new DBUtility();
		//transactionID=caveatDTO.getTransactionID().toString();
		transactionID=caveatDTO.getReferenceID().toString();
			try {
				String query = CommonSQL.CAVEATS_PAYMENT_STATUS;
				
				//return dbUtil.updateCaveat(query, caveatDTO);
				dbUtil.createPreparedStatement(query);
				
				
				dbUtil.executeUpdateTrans(transactionID);
				//return dbUtil.flagUpdate(query, caveatDTO);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e);
			} finally {
				logger.debug("using FINALLY Connection is closed");
				dbUtil.closeConnection();
			}
		} catch (Exception e) {
		}
		return false;
    }
    public boolean updateStatusDAO1(CaveatsDTO caveatDTO)throws Exception{
    	logger.debug("IN DAO modifyFlagDAO");
    	try {
			DBUtility dbUtil = new DBUtility();
		//transactionID=caveatDTO.getTransactionID().toString();
		transactionID=caveatDTO.getReferenceID().toString();
			
			try {
				String query = CommonSQL.CAVEATS_LOG_STATUS;
				
				//return dbUtil.updateCaveat(query, caveatDTO);
				
				dbUtil.createPreparedStatement(query);
				
				dbUtil.executeUpdateTrans(transactionID);
				//return dbUtil.flagUpdate(query, caveatDTO);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e);
			} finally {
				logger.debug("using FINALLY Connection is closed");
				dbUtil.closeConnection();
			}
		} catch (Exception e) {
		}
		return false;
    }
    
    ///
    
    public boolean checkStatusDAO(CaveatsDTO caveatDTO)throws Exception{
    	logger.debug("IN DAO modifyFlagDAO");
    	try {
			DBUtility dbUtil = new DBUtility();
			bal=caveatDTO.getTransactionID().toString();
			
			try {
				String query = CommonSQL.CAVEATS_PAYMENT_STATUS;
				//return dbUtil.updateCaveat(query, caveatDTO);
				dbUtil.createPreparedStatement(query);
				
				dbUtil.checkStatus(bal);
				//return dbUtil.flagUpdate(query, caveatDTO);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e);
			} finally {
				logger.debug("using FINALLY Connection is closed");
				dbUtil.closeConnection();
			}
		} catch (Exception e) {
		}
		return false;
    }
    
    
    
    
    
    /**
     * 
     * @param param
     * @return
     * @throws Exception
     */
    public boolean relBankUpdationDAO(String param[])throws Exception{
	    logger.debug("IN DAO relBankUpdationDAO");
	    try {
			DBUtility dbUtil = new DBUtility();
			try {
				String query = CommonSQL.IGRS_CAVEATS_BANK_RELEASE_UPDATE;
				dbUtil.createPreparedStatement(query);
				return dbUtil.executeUpdate(param);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			} finally {
				dbUtil.closeConnection();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return false;
    }
    /**
     * 
     * @param refid
     * @return
     * @throws Exception
     */
    public ArrayList searchByRefidDAO(String refid,String language) throws Exception
    {
    	logger.debug("IN DAO searchByRefidDAO");
    	DBUtility dbUtil = new DBUtility();
        try{
	        String param[]=new String[1];
	        if(language.equalsIgnoreCase("en"))
	        {
	        	        
	        String query=CommonSQL.GET_CAVEATS_SELECTED_PROPS;
	        
	        
	        param[0]=""+refid;
	        dbUtil.createPreparedStatement(query);
	        ArrayList list= dbUtil.executeQuery(param);
	        return list;
        }
        else
        {
        	String query=CommonSQL.GET_CAVEATS_SELECTED_PROPS_hi;
        	 param[0]=""+refid;
 	        dbUtil.createPreparedStatement(query);
 	        ArrayList list= dbUtil.executeQuery(param);
 	        return list;
        }
        }catch(IOException ie){
	    	logger.error(ie);
	    }
	    catch(SQLException se){
	    	logger.error(se);
	    }
	    catch(Exception e){
	        logger.error(e);
	    }
	    finally {
			logger.debug("using FINALLY Connection is closed");
	        dbUtil.closeConnection(); 
	    }
	    return null;
    }
    /**
     * 
     * @param searchFields
     * @return
     */
	public ArrayList searchByAllDAO(String searchFields[],String language) {
		logger.debug("IN DAO searchByAllDAO");
		ArrayList retVal = new ArrayList();
		ArrayList queryResult;
		if(language.equalsIgnoreCase("en"))
		{
		StringBuilder stbr = new StringBuilder(CommonSQL.BASE_CAVEATS_QUERY);
		DBUtility dbUtil;
//		searchFields[0] = _refid;
//		searchFields[1] = _RegNo;
//		searchFields[2] = _status;
//		searchFields[3] = _frDate;
//		searchFields[4] = _toDate;
		
		TreeMap<String, String> paramsMap = new TreeMap<String, String>();
		try {
			if ("".equals(searchFields[0]) == false) {
				stbr.append(CommonSQL.CAVEATS_QUERY_TXN_CLAUSE);
				paramsMap.put("1txnID", searchFields[0]);
			}
			if ("".equals(searchFields[1]) == false) {
				stbr.append(CommonSQL.CAVEATS_QUERY_REGN_CLAUSE);
				paramsMap.put("2regID", searchFields[1]);
			}
			if ("".equals(searchFields[2]) == false) {
				stbr.append(CommonSQL.CAVEATS_QUERY_STATUS_CLAUSE);
				paramsMap.put("3status", searchFields[2]);
			}
			if (("".equals(searchFields[3]) == false)
					&& ("".equals(searchFields[4]) == false)) {
				stbr.append(CommonSQL.CAVEATS_QUERY_DATE_BOTH_CLAUSE);
				
				
				paramsMap.put("4fromDate", searchFields[3]);
				paramsMap.put("5toDate", searchFields[4]);
			} else {
				if ("".equals(searchFields[3]) == false) {
					stbr.append(CommonSQL.CAVEATS_QUERY_DATE_FROM_CLAUSE);
					paramsMap.put("4fromDate", searchFields[3]);
				} else if ("".equals(searchFields[4]) == false) {
					stbr.append(CommonSQL.CAVEATS_QUERY_DATE_TO_CLAUSE);
				//	stbr.append(CommonSQL.CAVEATS_QUERY_ORDER);
                       
					paramsMap.put("5toDate", searchFields[4]);
				}
			}
			//logger.info("Final build query : " + stbr.toString());
			//logger.info("Params mapping : " + paramsMap);
			stbr.append(CommonSQL.CAVEATS_QUERY_ORDER);
			System.out.println(stbr);
			try {
				dbUtil = new DBUtility();
				
				Collection<String> values = paramsMap.values();
				ArrayList<String> tmp = new ArrayList(values);
//				Collections.reverse(tmp);
				String[] params = tmp.toArray(new String[]{}); 
				//logger.info("query params : " + params);
				queryResult = dbUtil.getCaveatDetails(stbr.toString(), params,language);
				retVal.addAll(queryResult);
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return retVal;
		}
		else
		{
			StringBuilder stbr = new StringBuilder(CommonSQL.BASE_CAVEATS_QUERY_HI);
			DBUtility dbUtil;
//			searchFields[0] = _refid;
//			searchFields[1] = _RegNo;
//			searchFields[2] = _status;
//			searchFields[3] = _frDate;
//			searchFields[4] = _toDate;
			
			TreeMap<String, String> paramsMap = new TreeMap<String, String>();
			try {
				if ("".equals(searchFields[0]) == false) {
					stbr.append(CommonSQL.CAVEATS_QUERY_TXN_CLAUSE);
					paramsMap.put("1txnID", searchFields[0]);
				}
				if ("".equals(searchFields[1]) == false) {
					stbr.append(CommonSQL.CAVEATS_QUERY_REGN_CLAUSE);
					paramsMap.put("2regID", searchFields[1]);
				}
				if ("".equals(searchFields[2]) == false) {
					stbr.append(CommonSQL.CAVEATS_QUERY_STATUS_CLAUSE);
					paramsMap.put("3status", searchFields[2]);
				}
				if (("".equals(searchFields[3]) == false)
						&& ("".equals(searchFields[4]) == false)) {
					stbr.append(CommonSQL.CAVEATS_QUERY_DATE_BOTH_CLAUSE);
					
					
					paramsMap.put("4fromDate", searchFields[3]);
					paramsMap.put("5toDate", searchFields[4]);
				} else {
					if ("".equals(searchFields[3]) == false) {
						stbr.append(CommonSQL.CAVEATS_QUERY_DATE_FROM_CLAUSE);
						paramsMap.put("4fromDate", searchFields[3]);
					} else if ("".equals(searchFields[4]) == false) {
						stbr.append(CommonSQL.CAVEATS_QUERY_DATE_TO_CLAUSE);
					//	stbr.append(CommonSQL.CAVEATS_QUERY_ORDER);
	                       
						paramsMap.put("5toDate", searchFields[4]);
					}
				}
				//logger.info("Final build query : " + stbr.toString());
				//logger.info("Params mapping : " + paramsMap);
				stbr.append(CommonSQL.CAVEATS_QUERY_ORDER);
				System.out.println(stbr);
				try {
					dbUtil = new DBUtility();
					
					Collection<String> values = paramsMap.values();
					ArrayList<String> tmp = new ArrayList(values);
//					Collections.reverse(tmp);
					String[] params = tmp.toArray(new String[]{}); 
					//logger.info("query params : " + params);
					queryResult = dbUtil.getCaveatDetails(stbr.toString(), params,language);
					retVal.addAll(queryResult);
					dbUtil.closeConnection();
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
			return retVal;
		}
	}
		
		/**
		}
	 * 
	 * @param searchFields
	 * @param userID
	 * @return
	 */
	public ArrayList searchBankAllDAO(String searchFields[], String userID,String language) {
		logger.debug("IN DAO searchBankAllDAO");
		ArrayList retVal = new ArrayList();
		ArrayList queryResult;
		//if(language.equalsIgnoreCase("en"))
		//{
				StringBuilder stbr = new StringBuilder(CommonSQL.SEARCH_BANK_CAVEAT_DTLS_VIEW_BASE_QUERY);
		DBUtility dbUtil;
//		searchFields[0] = _refid;
//		searchFields[1] = _RegNo;
//		searchFields[2] = _status;
//		searchFields[3] = _frDate;
//		searchFields[4] = _toDate;
		
		TreeMap<String, String> paramsMap = new TreeMap<String, String>();
		try {
			dbUtil = new DBUtility();
			String officalDRSRole = checkDRSOfficalRole(dbUtil,userID);
			if("".equals(officalDRSRole)) {
				stbr.append(CommonSQL.BANK_CAVEAT_QUERY_USER_CLAUSE);
				paramsMap.put("0userID", userID);
			}
			
			if ("".equals(searchFields[0]) == false) {
				stbr.append(CommonSQL.BANK_CAVEAT_QUERY_TXN_CLAUSE);
				paramsMap.put("1txnID", searchFields[0]);
			}
			if ("".equals(searchFields[1]) == false) {
				stbr.append(CommonSQL.BANK_CAVEAT_QUERY_REGN_CLAUSE);
				paramsMap.put("2regID", searchFields[1]);
			}
			if ("".equals(searchFields[2]) == false) {
				stbr.append(CommonSQL.BANK_CAVEAT_QUERY_STATUS_CLAUSE);
				paramsMap.put("3status", searchFields[2]);
			}
			if (("".equals(searchFields[3]) == false)
					&& ("".equals(searchFields[4]) == false)) {
				stbr.append(CommonSQL.BANK_CAVEAT_QUERY_DATE_BOTH_CLAUSE);
				paramsMap.put("4fromDate", searchFields[3]);
				paramsMap.put("5toDate", searchFields[4]);
			} else {
				if ("".equals(searchFields[3]) == false) {
					stbr.append(CommonSQL.BANK_CAVEAT_QUERY_DATE_FROM_CLAUSE);
					paramsMap.put("4fromDate", searchFields[3]);
				} else if ("".equals(searchFields[4]) == false) {
					stbr.append(CommonSQL.BANK_CAVEAT_QUERY_DATE_TO_CLAUSE);
					paramsMap.put("5toDate", searchFields[4]);
				}
			}
			//logger.info("Final build query : " + stbr.toString());
			//logger.info("Params mapping : " + paramsMap);
			try {
				
				
				Collection<String> values = paramsMap.values();
				ArrayList<String> tmp = new ArrayList(values);
//				Collections.reverse(tmp);
				String[] params = tmp.toArray(new String[]{}); 
				//logger.info("query params : " + params);
				queryResult = dbUtil.getBankCaveatDetails(stbr.toString(), params,language);
				retVal.addAll(queryResult);
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return retVal;
	}
	/*	else
		{
			StringBuilder stbr = new StringBuilder(CommonSQL.SEARCH_BANK_CAVEAT_DTLS_VIEW_BASE_QUERY_hi);
			DBUtility dbUtil;
//			searchFields[0] = _refid;
//			searchFields[1] = _RegNo;
//			searchFields[2] = _status;
//			searchFields[3] = _frDate;
//			searchFields[4] = _toDate;
			
			TreeMap<String, String> paramsMap = new TreeMap<String, String>();
			try {
				dbUtil = new DBUtility();
				String officalDRSRole = checkDRSOfficalRole(dbUtil,userID);
				if("".equals(officalDRSRole)) {
					stbr.append(CommonSQL.BANK_CAVEAT_QUERY_USER_CLAUSE);
					paramsMap.put("0userID", userID);
				}
				
				if ("".equals(searchFields[0]) == false) {
					stbr.append(CommonSQL.BANK_CAVEAT_QUERY_TXN_CLAUSE);
					paramsMap.put("1txnID", searchFields[0]);
				}
				if ("".equals(searchFields[1]) == false) {
					stbr.append(CommonSQL.BANK_CAVEAT_QUERY_REGN_CLAUSE);
					paramsMap.put("2regID", searchFields[1]);
				}
				if ("".equals(searchFields[2]) == false) {
					stbr.append(CommonSQL.BANK_CAVEAT_QUERY_STATUS_CLAUSE_hi);
					paramsMap.put("3status", searchFields[2]);
				}
				if (("".equals(searchFields[3]) == false)
						&& ("".equals(searchFields[4]) == false)) {
					stbr.append(CommonSQL.BANK_CAVEAT_QUERY_DATE_BOTH_CLAUSE);
					paramsMap.put("4fromDate", searchFields[3]);
					paramsMap.put("5toDate", searchFields[4]);
				} else {
					if ("".equals(searchFields[3]) == false) {
						stbr.append(CommonSQL.BANK_CAVEAT_QUERY_DATE_FROM_CLAUSE);
						paramsMap.put("4fromDate", searchFields[3]);
					} else if ("".equals(searchFields[4]) == false) {
						stbr.append(CommonSQL.BANK_CAVEAT_QUERY_DATE_TO_CLAUSE);
						paramsMap.put("5toDate", searchFields[4]);
					}
				}
				//logger.info("Final build query : " + stbr.toString());
				//logger.info("Params mapping : " + paramsMap);
				try {
					
					
					Collection<String> values = paramsMap.values();
					ArrayList<String> tmp = new ArrayList(values);
//					Collections.reverse(tmp);
					String[] params = tmp.toArray(new String[]{}); 
					//logger.info("query params : " + params);
					queryResult = dbUtil.getBankCaveatDetails(stbr.toString(), params);
					retVal.addAll(queryResult);
					dbUtil.closeConnection();
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
			return retVal;
		}
		}*/
	
	/**
	 * 
	 * @param util
	 * @param userID
	 * @return
	 * @throws Exception 
	 */
    private String checkDRSOfficalRole(DBUtility util, String userID) throws Exception {
		String retVal = "";
		try {
			util.createPreparedStatement(CommonSQL.GET_DRS_OFFICIAL_USER_ID_CHECK);
			ArrayList data = util.executeQuery(new String[]{userID, CaveatsConstant.DRS_OFFICIALS_ROLE_ATTRIB_NAME});
			ArrayList row = (ArrayList) data.get(0);
			retVal = (String) row.get(0);
		} catch (Exception e) {
		} finally {
			retVal = retVal == null ? "" : retVal.trim();
			util.closeConnection();
		}
		return retVal;
	}
    /**
     * 
     * @param regno
     * @param pin
     * @return
     * @throws IOException
     * @throws SQLException
     * @throws Exception
     */
	public ArrayList searchForPinDAO(String regno, String pin)throws IOException,SQLException,Exception
    {
    	logger.debug("IN DAO searchForPinDAO");
        ArrayList list=null;
        DBUtility dbUtil = new DBUtility();
        CaveatsDTO cDTO =new CaveatsDTO();
        try{
	        String param[]=new String[2];
	        String query=CommonSQL.IGRS_CAVEATS_CHECK_PIN;
	        param[0]=""+regno;
	        param[1]=""+pin;
	        
	        dbUtil.createPreparedStatement(query);
	        list = dbUtil.executeQuery(param);
	        ArrayList resultList=new ArrayList();
	        resultList=null;
	        if(!list.isEmpty())
	        {
	            String param2[]=new String[1];
	            String query2=CommonSQL.IGRS_CAVEATS_OTT_ACTIVE;
	            param2[0]=""+regno;
	            dbUtil.createPreparedStatement(query2);
	            ArrayList list2 = dbUtil.executeQuery(param2);
	            if(list2.isEmpty()){
	                resultList= list;
	            }
	        }
	        else
	        {
	            cDTO.setErrorMsg("not Found");
	        }
	        return resultList;
        }catch(IOException ie){
	    	logger.error(ie);
	    }
	    catch(SQLException se){
	    	logger.error(se);
	    }
	    catch(Exception e){
	        logger.error(e);
	    }
	    finally {
			logger.debug("using FINALLY Connection is closed");
	        dbUtil.closeConnection(); 
	    }
	    return null;
    }
	/**
	 * 
	 * @param regno
	 * @param pin
	 * @param ott
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 * @throws Exception
	 */
    public CaveatsDTO insertOttDAO(String regno, String pin, String ott)throws IOException,SQLException, Exception
    {
    	logger.debug("IN DAO insertOttDAO");
        CaveatsDTO cDTO =new CaveatsDTO();
        DBUtility dbUtil=null;
        try{
	        dbUtil = new DBUtility();
	        String query=CommonSQL.IGRS_CAVEATS_OTT_EXDAYS;
	        dbUtil.createStatement();
	        String days=dbUtil.executeQry(query);
	            int exdays=Integer.parseInt(days);
	            Calendar c1=Calendar.getInstance();
	            c1.add(Calendar.DATE,exdays);
	            Date today = new Date();
	            String DATE_FORMAT = "dd/MM/yyyy";      
	            java.text.SimpleDateFormat sdf =  new java.text.SimpleDateFormat(DATE_FORMAT);
	
	        String param[]=new String[6];
	        param[0]=regno;
	        param[1]=pin;
	        param[2]=ott;
	        param[3]=sdf.format(c1.getTime());
	        param[4]="Active";
	        param[5]=""+sdf.format(today);
	        query=CommonSQL.IGRS_CAVEATS_OTT_INSERT;
	        dbUtil.createPreparedStatement(query);
	        boolean insertion=dbUtil.executeUpdate(param);
	        if(insertion)
	        {
	            cDTO.setRegistrationNumber(param[0]);
	            cDTO.setPinNumber(param[1]);
	            cDTO.setOttNumber(param[2]);
	            cDTO.setOttExpiryDate(param[3]);
	            cDTO.setOttDate(sdf.format(today));
	            return(cDTO);
	        }
	        else
	        {
	            return null;
	        }
        }catch(IOException ie){
	    	logger.error(ie);
	    }
	    catch(SQLException se){
	    	logger.error(se);
	    }
	    catch(Exception e){
	        logger.error(e);
	    }
	    finally {
			logger.debug("using FINALLY Connection is closed");
	        dbUtil.closeConnection(); 
	    }
	    return null;
    }
    /**
     * 
     * @param mainDTO
     * @return
     */
	public boolean searchForOttDAO(CaveatsDTO mainDTO) {
		boolean retVal = false;
		try {
			DBUtility dbUtil = new DBUtility();
			ArrayList<CaveatsDTO> selectedList = new ArrayList<CaveatsDTO>();
			try {
				String param[] = new String[] { mainDTO.getRegistrationNumber(),
						mainDTO.getOttNumber() };
				String query = CommonSQL.GET_ACTIVE_REG_OTT_SEARCH_RESULT;
				//PM.REGISTRATION_ID, PM.OTT_ID, PM.PROPERTY_TXN_ID
				dbUtil.createPreparedStatement(query);
				ArrayList data = dbUtil.executeQuery(param);
				ArrayList row;
				for (Object item : data) {
					row = (ArrayList) item;
					CaveatsDTO selectDTO = new CaveatsDTO();
					selectDTO.setRegistrationNumber((String) row.get(0));
					selectDTO.setOttNumber((String) row.get(1));
					selectDTO.setPropertyTxnId((String) row.get(2));
					selectedList.add(selectDTO);
				}
				mainDTO.setSelectedItems(selectedList);
				retVal = selectedList.size() > 0;
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			} finally {
				dbUtil.closeConnection();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return retVal;
	}
	/**
	 * 
	 * @param mainDTO
	 * @return
	 */
    public boolean searchBankProperty(CaveatsDTO mainDTO) {
		boolean retVal = false;
		try {
			DBUtility dbUtil = new DBUtility();
			ArrayList<CaveatsDTO> selectedList = new ArrayList<CaveatsDTO>();
			try {
				String param[] = new String[] { mainDTO.getRegistrationNumber(),
						mainDTO.getOttNumber() };
				String query = CommonSQL.GET_ALL_REG_OTT_SEARCH_RESULT;
				//PM.REGISTRATION_ID, PM.OTT_ID, PM.PROPERTY_TXN_ID
				dbUtil.createPreparedStatement(query);
				ArrayList data = dbUtil.executeQuery(param);
				ArrayList row;
				for (Object item : data) {
					row = (ArrayList) item;
					CaveatsDTO selectDTO = new CaveatsDTO();
					selectDTO.setRegistrationNumber((String) row.get(0));
					selectDTO.setOttNumber((String) row.get(1));
					selectDTO.setPropertyTxnId((String) row.get(2));
					selectedList.add(selectDTO);
				}
				mainDTO.setSelectedItems(selectedList);
				retVal = selectedList.size() > 0;
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			} finally {
				dbUtil.closeConnection();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return retVal;
	}
    /**
     * 
     * @param param
     * @return
     * @throws Exception
     */
	public boolean insertCaveatBankDAO(String[] param) throws Exception {
		logger.debug("IN DAO searchByAll");
		DBUtility dbUtil = null;
		boolean result = false;
		try {
			String query = CommonSQL.IGRS_CAVEATS_INSERT_BYBANK;
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(query);
			result = dbUtil.executeUpdate(param);
			return result;
		} catch (IOException ie) {
			logger.error(ie);
		} catch (SQLException se) {
			logger.error(se);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			logger.debug("using FINALLY Connection is closed");
			dbUtil.closeConnection();
		}
		return result;
	}
	/**
	 * 
	 * @param regno
	 * @param ottno
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 * @throws Exception
	 */
    public boolean updateOttStatusDAO(String regno, String ottno)throws IOException,SQLException,Exception
    {
    	logger.debug("IN DAO updateOttStatusDAO");
    	DBUtility dbUtil=null;
        try{
            String param[]=new String[2];
            param[0]=regno;
            param[1]=ottno;
            String query=CommonSQL.IGRS_CAVEATS_OTT_UPDATE;
            dbUtil = new DBUtility();
            dbUtil.createPreparedStatement(query);
            return (dbUtil.executeUpdate(param));
        }catch(IOException ie){
	    	logger.error(ie);
	    }
	    catch(SQLException se){
	    	logger.error(se);
	    }
	    catch(Exception e){
	        logger.error(e);
	    }
	    finally {
			logger.debug("using FINALLY Connection is closed");
	        dbUtil.closeConnection(); 
	    }
	    return false;
    }
    /**
     * 
     * @param refId
     * @return
     * @throws Exception
     */
    public ArrayList releaseCvtBankByRefIdDAO(String refId) throws Exception {
    	logger.debug("IN DAO releaseCvtBankByRefIdDAO");
    	ArrayList list =null;
    	DBUtility dbUtil=null;
        try {
            String qury = new String(CommonSQL.IGRS_CAVEATS_BANK_RELEASE_REFID);
            String param[]=new String[1];
            param[0]=""+refId;
            dbUtil = new DBUtility();
            dbUtil.createPreparedStatement(qury);
            list = dbUtil.executeQuery(param);
        }catch(IOException ie){
	    	logger.error(ie);
	    }
	    catch(SQLException se){
	    	logger.error(se);
	    }
	    catch(Exception e){
	        logger.error(e);
	    }
	    finally {
			logger.debug("using FINALLY Connection is closed");
	        dbUtil.closeConnection(); 
	    }
        return list;
    }
    /**
     * 
     * @param _stateIdVar
     * @return
     * @throws Exception
     */
    public ArrayList districtStack(String _stateIdVar,String language)throws Exception{
    	logger.debug("IN DAO districtStack");
        ArrayList ar1=new ArrayList();
        DBUtility dbUtil=null;
      
        try {
            ArrayList typeList=new ArrayList();
            ArrayList typeTemp=new ArrayList();
            
            int arr [] ={1};
           
            dbUtil=new DBUtility();
            if(language.equalsIgnoreCase("en"))
            {
            String str=CommonSQL.IGRS_DISTRICT_MASTER; // Query for district list from IGRS_DISTRICT_MASTER
            
            dbUtil.createPreparedStatement(str);
            typeList=dbUtil.executeQuery(arr);
            if(typeList!=null){
                for(int i=0;i<typeList.size();i++) {
                    typeTemp=new ArrayList();
                    typeTemp=(ArrayList)typeList.get(i);
                    if(typeTemp.size()>0){
                        CaveatsDTO type = new CaveatsDTO();
                        type.setDistrictId((String)typeTemp.get(0));
                        type.setDistrictName((String)typeTemp.get(1));
                        ar1.add(type);
                    }
                }
            }
        }
            else
            {
            	 String str=CommonSQL.IGRS_DISTRICT_MASTER_hi; // Query for district list from IGRS_DISTRICT_MASTER
            	 //int arr1 [] ={1};
                 
                 dbUtil.createPreparedStatement(str);
                 typeList=dbUtil.executeQuery(arr);
                 if(typeList!=null){
                     for(int i=0;i<typeList.size();i++) {
                         typeTemp=new ArrayList();
                         typeTemp=(ArrayList)typeList.get(i);
                         if(typeTemp.size()>0){
                             CaveatsDTO type = new CaveatsDTO();
                             type.setDistrictId((String)typeTemp.get(0));
                             type.setDistrictName((String)typeTemp.get(1));
                             ar1.add(type);
                         }
                     }
                 }	
            }
        }catch(IOException ie){
	    	logger.error(ie);
	    }
	    catch(SQLException se){
	    	logger.error(se);
	    }
	    catch(Exception e){
	        logger.error(e);
	    }
	    finally {
			logger.debug("using FINALLY Connection is closed");
	        dbUtil.closeConnection(); 
	    }
        return ar1;
	}
    /**
     * 
     * @param registrationNumber
     * @return
     */
	public ArrayList getPropertyTxnID(String registrationNumber) {
		ArrayList data = new ArrayList();
		try {
			DBUtility util = new DBUtility();
			try {
				String query = CommonSQL.GET_BANK_CAVEATS_PROPERTYID;
				util.createPreparedStatement(query);
				data = util.executeQuery(new String[]{registrationNumber});
			} catch (Exception e) {
				 logger.error(e.getMessage(), e);
			}
			finally {
				util.closeConnection();
			}
		} catch (Exception e) {
			 logger.error(e.getMessage(), e);
		}
		return data;
	}
	/**
	 * 
	 * @param attribName
	 * @return
	 */
	public String getOTTValidity(String attribName) {
		String retVal="NA";
		try {
			DBUtility util = new DBUtility();
			try {
				String query = CommonSQL.GET_OTT_VALID_ATTRIB_VAL;
				util.createPreparedStatement(query);
				ArrayList data = util.executeQuery(new String[]{attribName});
				if(data == null || data.isEmpty()) {
					logger.error("ALERT - OTT Validity Parameter is either deactivated or does not exist");
				}else {
					ArrayList row = (ArrayList) data.get(0);
					retVal = row.get(0).toString();
				}
			} catch (Exception e) {
				 logger.error(e.getMessage(), e);
			}
			finally {
				util.closeConnection();
			}
		} catch (Exception e) {
			 logger.error(e.getMessage(), e);
		}
		return retVal;
		
	}
	/**
	 * 
	 * @param caveatfrm
	 */
	public void generateOTTNumbers(CaveatsForm caveatfrm) {
		try {
			DBUtility util = new DBUtility();
			util.setAutoCommit(false);
			String newOTT;
			String[] param;
			ArrayList data;
			try {
				CaveatsDTO dto = caveatfrm.getCaveatsDTO();
				util.createStatement();
				newOTT = util.executeQry(CommonSQL.GET_NEXT_OTT_ID);
				//logger.info("New OTT Generated : " + newOTT);
				dto.setOttNumber(newOTT);
				String query = CommonSQL.INSERT_OTT_DETAILS;
				/*
				 "( REGISTRATION_ID, OTT_ID, DATE_OF_CREATION, DATE_OF_EXPIRY, OTT_STATUS, CREATED_BY, CREATED_DATE)" +
    			 " VALUES ( ?, ?, SYSDATE, (SYSDATE + ?), 'A', ?, SYSDATE) "
				 */
				util.createPreparedStatement(query);
				param = new String[] {
					dto.getRegistrationNumber(), dto.getOttNumber(), caveatfrm.getValidityOTT(), dto.getLoggedIn()
				};
				util.executeUpdate(param);
			//	logger.info("OTT Details inserted for " + dto.getRegistrationNumber());
				query = CommonSQL.INSERT_OTT_PROP_MAPPING;
				/*
				 "( OTT_MAPPING_ID, REGISTRATION_ID, OTT_ID, PROPERTY_TXN_ID ) " +
    			 "VALUES ( IGRS_OTT_MAPPING_SEQ.NEXTVAL, ?, ?, ? )";
				 */
				ArrayList selectedList = caveatfrm.getSelectedList();
				if(selectedList != null && selectedList.isEmpty()==false) {
					for (Object item : selectedList) {
						dto = (CaveatsDTO) item;
						dto.setOttNumber(newOTT);
						util.createPreparedStatement(query);
						param = new String[] {
							dto.getRegistrationNumber(), dto.getOttNumber(), dto.getPropertyTxnId()
						};
						util.executeUpdate(param);
					//	logger.info("OTT Property Mapping inserted for " + dto.getPropertyTxnId());
					}
				}
				util.commit();
				logger.info("OTT Information Saved to database");
				query = CommonSQL.GET_OTT_DATES;
				//DATE_OF_CREATION, DATE_OF_EXPIRY
				util.createPreparedStatement(query);
				param = new String[] {newOTT};
				data = util.executeQuery(param);
				data = (ArrayList) data.get(0);
				dto = caveatfrm.getCaveatsDTO();
				dto.setOttDate((String) data.get(0));
				dto.setOttExpiryDate((String) data.get(1));
			} catch (Exception e) {
				 logger.error(e.getMessage(), e);
				 util.rollback();
			}
			finally {
				util.closeConnection();
			}
		} catch (Exception e) {
			 logger.error(e.getMessage(), e);
		}
		
	}
	/**
	 * 
	 * @param selectedList
	 * @return
	 */
	//modified by ashima
	public ArrayList<String> getActiveOTTPropertyMapping(ArrayList selectedList) {
		ArrayList<String> retVal = new ArrayList<String>();
		ArrayList data, row;
		try {
			DBUtility util = new DBUtility();
			StringBuilder strBldr = new StringBuilder();
			
			try {
				strBldr.append(CommonSQL.Caveat_Get_Ott);
				/*strBldr.append("SELECT DISTINCT(PM.PROPERTY_TXN_ID) FROM IGRS_LOAN_OTT_MASTER OM, IGRS_OTT_PROP_MAPPING PM ");
				strBldr.append("WHERE PM.OTT_ID    = OM.OTT_ID AND OM.OTT_STATUS = 'A' ");
				strBldr.append("AND PM.PROPERTY_TXN_ID IN (");*/
				String[] params = new String[selectedList.size()];
				for (int iLoop = 0; iLoop < selectedList.size(); iLoop++) {
					CaveatsDTO dto = (CaveatsDTO) selectedList.get(iLoop);
					params[iLoop] = dto.getPropertyTxnId(); 
				}
				strBldr.append(buildInClause(params ));
				strBldr.append(" )");
				util.createStatement();
				data = util.executeQuery(strBldr.toString());
				for (Object item : data) {
					row = (ArrayList) item;
					retVal.add((String) row.get(0));
				}
			} catch (Exception e) {
				 logger.error(e.getMessage(), e);
			}
			finally {
				util.closeConnection();
			}
		} catch (Exception e) {
			 logger.error(e.getMessage(), e);
		}
		return retVal;
	}
	/**
	 * 
	 * @param params
	 * @return
	 */
	private String buildInClause(String[] params) {
		StringBuilder stBldr = new StringBuilder();
		for (int iLoop = 0; iLoop < params.length; iLoop++) {
			stBldr.append("\'");
			stBldr.append(params[iLoop]);
			stBldr.append("\'");
			if (iLoop < (params.length - 1)) {
				stBldr.append(", ");
			}
		}
		return stBldr.toString();
	}
	/**
	 * 
	 * @param dto
	 * @return
	 * @throws Exception 
	 */
	public boolean logBankCaveat(CaveatsDTO dto) throws Exception {
		boolean retVal = false;
		DBUtility util = null;
		try {
			 util = new DBUtility();
			retVal = util.insertBankCaveatData(dto);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		finally
		{
			util.closeConnection();
		}
		return retVal;
	}
	/**
	 * 
	 * @param dto
	 * @return
	 */
	public boolean searchBankCaveat(CaveatsDTO dto) {
		boolean retVal = false;
		try {
			DBUtility util = new DBUtility();
			ArrayList data, row;
			try {
				String[] params;
				String query = CommonSQL.SEARCH_BANK_CAVEAT_DTLS_RELEASE;
				util.createPreparedStatement(query);
				params = new String[] { dto.getReferenceID(), dto.getLoggedIn() };
				data = util.executeQuery(params);
				if (data != null && data.isEmpty() == false) {
					row = (ArrayList) data.get(0);
					dto.setReferenceID((String) row.get(0));
					dto.setOttNumber((String) row.get(1));
					dto.setLoanAccountNumber((String) row.get(2));
					dto.setLoanDueDate((String) row.get(3));
					dto.setLoanAmount((String) row.get(4));
					dto.setSecuredAmount((String) row.get(5));
					dto.setNoOfInstallments((String) row.get(6));
					dto.setNameOfInsti((String) row.get(7));
					dto.setAddressOfInsti((String) row.get(8));
					dto.setNameOfBankPerson((String) row.get(9));
					dto.setRegistrationNumber((String) row.get(10));
					dto.setLoggedDate((String) row.get(11));
					dto.setMobOfBankPerson((String) row.get(12));
					dto.setEmailOfBankPerson((String) row.get(13));
					dto.setAmountOfInstall((String) row.get(14));
					dto.setStampPaid((String) row.get(15));
					dto.setLoanPurpose((String) row.get(16));
					dto.setMortgageType((String) row.get(17));
					dto.setRegDate((String) row.get(18));
					dto.setSdocuNumber((String) row.get(19));
					query = CommonSQL.SEARCH_OTT_PROPERTY_MAPPING;
					util.createPreparedStatement(query);
					params = new String[] { dto.getOttNumber() };
					data = util.executeQuery(params);
					if (data != null && data.isEmpty() == false) {
						ArrayList selectedItems = new ArrayList();
						dto.setSelectedItems(selectedItems);
						for (Object item : data) {
							row = (ArrayList) item;
							CaveatsDTO tmp = new CaveatsDTO();
							tmp.setPropertyTxnId((String) row.get(0));
							selectedItems.add(tmp);
						}
					}
					retVal = true;
				}

				
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			} finally {
				util.closeConnection();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return retVal;
	}
	/**
	 * 
	 * @param userID
	 * @return
	 */
	public String checkBankRoleForUser(String userID) {
		String bankRoleName = "";
		try {
			DBUtility util = new DBUtility();
			try {
				ArrayList data, row;
				
				String query = CommonSQL.GET_BANK_VALID_USER_ROLE_NAME;
				util.createPreparedStatement(query);
				String[] params = new String[] { userID, CaveatsConstant.BANK_ROLE_ATTRIB_NAME };
				data = util.executeQuery(params);
				if (data != null && data.isEmpty() == false) {
					row = (ArrayList) data.get(0);
					bankRoleName = (String) row.get(0);
					//logger.info("Got bankRoleName : " + bankRoleName);
					bankRoleName = bankRoleName == null ? "" : bankRoleName.trim();
				}
				if("".equals(bankRoleName)) {
					logger.error("ALERT - Bank Role not associated with user : " + userID);
				}else {
					logger.info("Bank Role associated with user : " + userID);
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
			finally {
				util.closeConnection();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return bankRoleName;
	}
	/**
	 * 
	 * @param cDTO
	 * @param userID
	 * @return
	 */
	public  ArrayList<OTTDetailDTO> searchBankOTT(CaveatsDTO cDTO, String userID,String language) {
		logger.debug("IN DAO searchBankAllDAO");
		ArrayList retVal = new  ArrayList<OTTDetailDTO>();
		ArrayList queryResult;
		if(language.equalsIgnoreCase("en"))
		{
		StringBuilder stbr = new StringBuilder(CommonSQL.SEARCH_BANK_OTT_VIEW_BASE_QUERY);
		DBUtility dbUtil;
		String[] searchFields = new String[5];
		searchFields[0] = cDTO.getOttNumber() == null ? "" : cDTO.getOttNumber().trim();
		searchFields[1] = cDTO.getRegistrationNoSearch() == null ? "" : cDTO.getRegistrationNoSearch().trim();
		searchFields[2] = cDTO.getFlag() == null ? "" : cDTO.getFlag().trim();
		searchFields[3] = cDTO.getFromDate() == null ? "" : cDTO.getFromDate().trim();
		searchFields[4] = cDTO.getToDate() == null ? "" : cDTO.getToDate().trim();
		
		cDTO.setToDate("");
		cDTO.setFromDate("");
		cDTO.setRegistrationNoSearch("");
		cDTO.setOttNumber("");
		cDTO.setFlag("");
		TreeMap<String, String> paramsMap = new TreeMap<String, String>();
		try {
			dbUtil = new DBUtility();
			
			String officalDRSRole = checkDRSOfficalRole(dbUtil,userID);
			if("".equals(officalDRSRole)) {
				stbr.append(CommonSQL.OTT_CAVEAT_QUERY_USER_CLAUSE);
				paramsMap.put("0userID", userID);
			}
			
			
			if ("".equals(searchFields[0]) == false) {
				stbr.append(CommonSQL.OTT_CAVEAT_QUERY_OTT_CLAUSE);
				paramsMap.put("1ottID", searchFields[0]);
			}
			if ("".equals(searchFields[1]) == false) {
				stbr.append(CommonSQL.OTT_CAVEAT_QUERY_REGN_CLAUSE);
				paramsMap.put("2regID", searchFields[1]);
			}
			if ("".equals(searchFields[2]) == false) {
				stbr.append(CommonSQL.OTT_CAVEAT_QUERY_STATUS_CLAUSE);
				paramsMap.put("3status", searchFields[2]);
			}
			if (("".equals(searchFields[3]) == false)
					&& ("".equals(searchFields[4]) == false)) {
				stbr.append(CommonSQL.OTT_CAVEAT_QUERY_DATE_BOTH_CLAUSE);
				paramsMap.put("4fromDate", searchFields[3]);
				paramsMap.put("5toDate", searchFields[4]);
			} else {
				if ("".equals(searchFields[3]) == false) {
					stbr.append(CommonSQL.OTT_CAVEAT_QUERY_DATE_FROM_CLAUSE);
					paramsMap.put("4fromDate", searchFields[3]);
				} else if ("".equals(searchFields[4]) == false) {
					stbr.append(CommonSQL.OTT_CAVEAT_QUERY_DATE_TO_CLAUSE);
					paramsMap.put("5toDate", searchFields[4]);
				}
			}
			//logger.info("Final build query : " + stbr.toString());
			//logger.info("Params mapping : " + paramsMap);
			try {
				
				
				Collection<String> values = paramsMap.values();
				ArrayList<String> tmp = new ArrayList(values);
//				Collections.reverse(tmp);
				String[] params = tmp.toArray(new String[]{}); 
				//logger.info("query params : " + params);
				queryResult = dbUtil.getBankOTTDetails(stbr.toString(), params);
				retVal.addAll(queryResult);
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return retVal;
	}
		else
		{
			StringBuilder stbr = new StringBuilder(CommonSQL.SEARCH_BANK_OTT_VIEW_BASE_QUERY_hi);
			DBUtility dbUtil;
			String[] searchFields = new String[5];
			searchFields[0] = cDTO.getOttNumber() == null ? "" : cDTO.getOttNumber().trim();
			searchFields[1] = cDTO.getRegistrationNoSearch() == null ? "" : cDTO.getRegistrationNoSearch().trim();
			searchFields[2] = cDTO.getFlag() == null ? "" : cDTO.getFlag().trim();
			searchFields[3] = cDTO.getFromDate() == null ? "" : cDTO.getFromDate().trim();
			searchFields[4] = cDTO.getToDate() == null ? "" : cDTO.getToDate().trim();
			

			TreeMap<String, String> paramsMap = new TreeMap<String, String>();
			try {
				dbUtil = new DBUtility();
				
				String officalDRSRole = checkDRSOfficalRole(dbUtil,userID);
				if("".equals(officalDRSRole)) {
					stbr.append(CommonSQL.OTT_CAVEAT_QUERY_USER_CLAUSE);
					paramsMap.put("0userID", userID);
				}
				
				
				if ("".equals(searchFields[0]) == false) {
					stbr.append(CommonSQL.OTT_CAVEAT_QUERY_OTT_CLAUSE);
					paramsMap.put("1ottID", searchFields[0]);
				}
				if ("".equals(searchFields[1]) == false) {
					stbr.append(CommonSQL.OTT_CAVEAT_QUERY_REGN_CLAUSE);
					paramsMap.put("2regID", searchFields[1]);
				}
				if ("".equals(searchFields[2]) == false) {
					stbr.append(CommonSQL.OTT_CAVEAT_QUERY_STATUS_CLAUSE);
					paramsMap.put("3status", searchFields[2]);
				}
				if (("".equals(searchFields[3]) == false)
						&& ("".equals(searchFields[4]) == false)) {
					stbr.append(CommonSQL.OTT_CAVEAT_QUERY_DATE_BOTH_CLAUSE);
					paramsMap.put("4fromDate", searchFields[3]);
					paramsMap.put("5toDate", searchFields[4]);
				} else {
					if ("".equals(searchFields[3]) == false) {
						stbr.append(CommonSQL.OTT_CAVEAT_QUERY_DATE_FROM_CLAUSE);
						paramsMap.put("4fromDate", searchFields[3]);
					} else if ("".equals(searchFields[4]) == false) {
						stbr.append(CommonSQL.OTT_CAVEAT_QUERY_DATE_TO_CLAUSE);
						paramsMap.put("5toDate", searchFields[4]);
					}
				}
				//logger.info("Final build query : " + stbr.toString());
				//logger.info("Params mapping : " + paramsMap);
				try {
					
					
					Collection<String> values = paramsMap.values();
					ArrayList<String> tmp = new ArrayList(values);
//					Collections.reverse(tmp);
					String[] params = tmp.toArray(new String[]{}); 
					//logger.info("query params : " + params);
					queryResult = dbUtil.getBankOTTDetails(stbr.toString(), params);
					retVal.addAll(queryResult);
					
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
				finally{
					dbUtil.closeConnection();
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
			return retVal;
		}
		}
	/**
	 * 
	 * @param registrationNumber
	 * @return
	 */
	public int getStayOrderLoggedCount(String registrationNumber) {
		int retVal = 0;
		try {
			DBUtility util = new DBUtility();
			try {
				String query = CommonSQL.GET_STAY_ORDER_LOGGED_COUNT;
				util.createPreparedStatement(query);
				String result = util.executeQry(new String[] {registrationNumber});
				//logger.error("result is"+result);
				//logger.error("Registration Number is"+registrationNumber);
				retVal = Integer.parseInt(result);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			} finally {
				util.closeConnection();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return retVal;
	}
	public ArrayList getFee(String functionId, String serId, String loggedIn) {
		ArrayList retVal = new ArrayList();
		try {
			DBUtility dbUtility = new DBUtility();
			try {

				CallableStatement clstmt = dbUtility
						.returnCallableStatement(CommonSQL.SERVICE_FEE_PROCEDURE);
				clstmt.setString(1, functionId);
				clstmt.setString(2, serId);
				clstmt.setString(3, loggedIn);
				clstmt.registerOutParameter(4, OracleTypes.NUMBER);
				clstmt.registerOutParameter(5, OracleTypes.CLOB);
				clstmt.registerOutParameter(6, OracleTypes.VARCHAR);
				if (!clstmt.execute()) {
					int temp = clstmt.getInt(4);
					retVal = new ArrayList();
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
					retVal.add("" + fee);
					otherFee = totalFee - fee;
					retVal.add("" + otherFee);
					retVal.add("" + totalFee);

				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			} finally {
				try {
					if (dbUtility != null) {
						dbUtility.closeConnection();
					}
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return retVal;
	}
	
	public String getOthersFeeDutyNew(String _retFunId, String _serId,
			String _userType) throws Exception {
		String othersFeeDuty="";
		DBUtility dbUtil = null;
			try {
				System.out.println("Inside getOthersFeeDuty()");
				
				String[] param={_retFunId};
				System.out.println("Inside getOthersFeeDuty()");
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
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}finally {
			dbUtil.closeConnection();
		}
		System.out.println("othersFeeDuty:-" + othersFeeDuty);
		return othersFeeDuty;
	}
	
	
	public String getOthersFeeDuty(String _userType) throws Exception {
		String othersFeeDuty="";
		DBUtility dbUtil = null;
			try {
				
				String arr[] = new String[1];
						arr[0] = _userType;
				
						dbUtil = new DBUtility();
						dbUtil.createPreparedStatement("select office_type_id from igrs_office_master where office_id=?");
					othersFeeDuty = 	dbUtil.executeQry(arr);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}finally {
			dbUtil.closeConnection();
		}
		System.out.println("othersFeeDuty:-" + othersFeeDuty);
		return othersFeeDuty;
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
        	dbUtil.createPreparedStatement(CommonSQL.GET_REGINIT_ID);
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
	
	public String getPropVal(String regComId) {
        String reginitId="";
        String[]ary=new String[1];
        ary[0]=regComId;
    	DBUtility dbUtil = null;
        try
        {
        	dbUtil = new DBUtility();	
        	dbUtil.createPreparedStatement(CommonSQL.GET_VAL_DETAILS);
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
	
	
	//ashima
	public ArrayList getPendingApps(String userId,String language)
	{
		ArrayList pendingAppList = new ArrayList();
		ArrayList pendingAppListFinal = new ArrayList();
		
		
		
		ArrayList list1 = new ArrayList();
		
		String[] param={userId};
		
		try {
			DBUtility dbUtil = new DBUtility();
			if(language.equalsIgnoreCase("en"))
			{
				SQL = CommonSQL.CAVEATS_DASHBOARD;
				SQL1 = CommonSQL.CAVEATS_DASHBOARD_ORDER;
				

			}
			else
			{
			SQL = CommonSQL.CAVEATS_DASHBOARD_HI;
			SQL1 = CommonSQL.CAVEATS_DASHBOARD_ORDER;
			}
					//dbUtil.createPreparedStatement(SQL);
					dbUtil.createStatement();
					
					try
					{	
						
					//	pendingAppList=dbUtil.executeQuery(param);
						pendingAppList=dbUtil.executeQuery(SQL + userId + "'" + SQL1 );
				            logger.debug("Wipro in CaveatDAO - getPendingApps() after dbUtil.executeQuery(SQL)");
				           
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

	
	
	/////
	
	public ArrayList getPartial(String userId)
	{
		ArrayList partialList = new ArrayList();
		ArrayList partialListFinal = new ArrayList();
		
		
		
		ArrayList list1 = new ArrayList();
		
		String[] param={userId};
		
		try {
			DBUtility dbUtil = new DBUtility();
			
					SQL = CommonSQL.CAVEATS_PARTIAL;
					//dbUtil.createPreparedStatement(SQL);
					dbUtil.createStatement();
					
					try
					{	
						
					//	pendingAppList=dbUtil.executeQuery(param);
						partialList=dbUtil.executeQuery(SQL+"'"+userId+"'");
				            logger.debug("Wipro in CaveatDAO - getPendingApps() after dbUtil.executeQuery(SQL)");
				           
				            partialList.trimToSize();
			
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
		return partialList;
		}

	
	
	
	
	
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
public ArrayList getTransidDetails(String transid,String language) {
	
		
		
		ArrayList transidDetailsList = new ArrayList();
		
		
		
		
		ArrayList list1 = new ArrayList();
		
		String[] param={transid};
		
		
		try {
			DBUtility dbUtil = new DBUtility();
			if(language.equalsIgnoreCase("en"))
			{
					SQL = CommonSQL.IGRS_CAVEATS_SEARCH_REFID_SEARCH;
			}
			else
			{
				SQL = CommonSQL.IGRS_CAVEATS_SEARCH_REFID_SEARCH_hi;
			}
					dbUtil.createPreparedStatement(SQL);
					//dbUtil.createStatement();
					

					
					try
					{	
						
						transidDetailsList=dbUtil.executeQuery(param);
						//transidDetailsList=dbUtil.executeQuery(SQL);
						dbUtil.executeUpdate(param);
				            logger.debug("Wipro in CaveatDAO - getTransidDetails() after dbUtil.executeQuery(SQL)");
				           
				            transidDetailsList.trimToSize();
			
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
		return transidDetailsList;
		}
 
public static  boolean insertSpDocDetls1 (CaveatsForm fm) throws Exception{
	 
	  boolean transactionflag = false;
	  String docTbl[]= new String[4];

	  DBUtility dbUtil = null;
	  try{
		  dbUtil = new DBUtility();
		  dbUtil.setAutoCommit(false);
		  
		  String SQL1 = "select IGRS_SP_DOC_ID_SEQ.nextval from dual";
		  dbUtil.createStatement();
		  String number2 = dbUtil.executeQry(SQL1);
		  docTbl[0]=number2;
		  docTbl[1]=(String) fm.getCaveatsDTO().getReferenceID();
		  docTbl[2]=(String) fm.getCaveatsDTO().getDocName();
		  docTbl[3]=(String)fm.getCaveatsDTO().getUploaded_doc_path();
		  
		//  docTbl[5]=(String)objServiceProviderForm.getObjServiceProviderDTO().getUid();
		  
		  dbUtil.createPreparedStatement(CommonSQL.IGRS_DOCUMENT_DTLS);
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


////ashima
public String setPaymentDtls(CaveatsDTO dto,String funId,String userId) throws Exception
{
	
	String paymentId="";
	DBUtility dbUtil = null;
	try {
		dbUtil = new DBUtility();
		PreparedStatement prepStmt;
		dbUtil.createStatement();
		paymentId = dbUtil.executeQry("SELECT IGRS_CAVEAT_PAYMENT_SEQ.NEXTVAL FROM DUAL");
		String sqlQuery=CommonSQL.COPY_PAYMENT_DTLS;
		
		dbUtil.createPreparedStatement(sqlQuery);
		String arr[] = new String[7];
		float bal=(dto.getBalanceAmount()==null)?0.0f:(Float)dto.getBalanceAmount();
		
		arr[0] = paymentId;
		arr[1] = dto.getTransactionID().toString();
		arr[2] = funId;
		arr[3] = "";
		arr[4] = "I";
		arr[5] = userId;
		arr[6] = String.valueOf(bal);
		dbUtil.executeUpdate(arr);
		
		
		//System.out.println("Success result>>>>"+record);
//		prepStmt.execute();
//		System.out.println("Query after execution>>>>"+sqlQuery);
		
	
	} catch (Exception e) {
	logger.error(e.getMessage(), e);
	}
	finally
	{
		dbUtil.closeConnection();
	}
return paymentId;

}

//ashima
public ArrayList isPaymentEntry(String transactionID)
{
	ArrayList pendingAppList = new ArrayList();
	
	DBUtility dbUtil = null;
	try {
		dbUtil = new DBUtility();
		String SQL=CommonSQL.PRESENT_PAYMENT_ENTRY;
			//	SQL = CommonSQL.GET_PENDING_APPLICATIONS_DETLS_JUD;
				//dbUtil.createPreparedStatement(SQL);
			//dbUtil.createPreparedStatement(SQL);
			dbUtil.createStatement();
			//String param[]={certifiedId};
				
				try
				{	
				
					pendingAppList=dbUtil.executeQuery(SQL+"'"+transactionID+"')");
					//pendingAppList=dbUtil.executeQuery(SQL);
			         //   logger.debug("-----> in EstampDAO - getPendingApps() for Judicial after dbUtil.executeQuery(SQL)");
			           
			          // pendingAppList.trimToSize();
		
	} catch (Exception x) {
		//logger.debug(x);
		x.printStackTrace();
	} finally {
		try {
			
			dbUtil.closeConnection();
		} catch (Exception ex) {
		//	logger.debug(ex);
			ex.printStackTrace();
		}
	}
	
}
	catch(Exception e){
		//logger.debug(e);
	}
	
	return pendingAppList;
}

//ashima
public ArrayList getDetails(String officeId){
	  ArrayList alist= new ArrayList();
		DBUtility dbUtil = null;
	  String sql= CommonSQL.Caveat_Get_Details;
	  //String sql="select a.office_id , a.office_name, a.district_id, b.district_name from igrs_office_master a, igrs_district_master b where a.district_id=b.district_id and a.office_id='"+officeId+"'";
	  
	  try {
		dbUtil= new DBUtility();
		dbUtil.createStatement();
		
		
		alist=dbUtil.executeQuery(sql+"'"+officeId+"'");
		
		
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
	  
	  return alist;
	  
	  }
public ArrayList getivrsCaveatsCharges(String regNumber, String propID) {
	ArrayList li = null;
	DBUtility dbUtil = null;
	try
	{
		String arr[] ={regNumber,propID};
		String arr1[] = {regNumber};
		dbUtil = new DBUtility();
		String sql ="";
		if(propID.equalsIgnoreCase("0"))
		{
			sql=CommonSQL.GET_IVRS_DETAILS_1;
			dbUtil.createPreparedStatement(sql);
			 li = dbUtil.executeQuery(arr1);
		}
			else
		{
		sql=CommonSQL.GET_IVRS_DETAILS ;
		dbUtil.createPreparedStatement(sql);
		 li = dbUtil.executeQuery(arr);
		}
		} catch (Exception e) {
		logger.debug(e.getMessage(),e);
	}
	finally
	{
		try {
			dbUtil.closeConnection();
		} catch (Exception e) {
			
			logger.debug(e.getMessage(),e);
		}
	}
	
	
	
	
	return li;
}
public void ivrsOTTGeneration(String regNumber, String propID, String pinNumber) {
	DBUtility dbUtil = null;
	try {
		
		dbUtil = new DBUtility();
		//dbUtil = new DBUtility();
		
		
	} catch (Exception e) {
	
	}
	finally
	{
	try {
		dbUtil.closeConnection();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
	}
	
}
public void getOtherDetails(CaveatsDTO tmpDTO) throws Exception {
	DBUtility dbUtil = null;
	try
	{
		String param[] = new String[1];
		param[0] = tmpDTO.getReferenceID();
		String sql = "select CASE_NUMBER,TO_CHAR(COURT_ORDER_DATE,'DD/MM/YYYY') from IGRS_CAVEAT_TXN where TRANSACTION_ID=?";
		dbUtil = new DBUtility();
		dbUtil.createPreparedStatement(sql);
		ArrayList li = dbUtil.executeQuery(param);
		if(li.size()>0)
		{
		ArrayList l = (ArrayList) li.get(0);
			tmpDTO.setCaseNum((String)l.get(0));
			tmpDTO.setCourtOrderDate((String)l.get(1));
		
		
		}
	}
	catch (Exception e) {
	logger.debug(e.getMessage(),e);
	}
	finally
	{
		dbUtil.closeConnection();
	}
}

// Log protest changes by santosh
public String getDistrictName(String officeId, String language) throws Exception {
	DBUtility dbUtil = null;
	String districtName=null;
	ArrayList districtDetails = null;
	ArrayList districtDetails1 = null;
	try
	{
		String sql =null;
		
		String param[] = new String[1];
		param[0] = officeId;
		if("en".equalsIgnoreCase(language)){
			sql = CommonSQL.GET_DISTRICT_DETAILS;
		}else if("hi".equalsIgnoreCase(language)){
			sql = CommonSQL.GET_DISTRICT_DETAILS_H;
		}
		 
		dbUtil = new DBUtility();
		
		dbUtil.createPreparedStatement(sql);
		 //districtName = dbUtil.executeQry(param);
		 districtDetails = dbUtil.executeQuery(param);
		
		if(null!=districtDetails && districtDetails.size()!=0){
			districtDetails1 = (ArrayList) districtDetails.get(0);
			districtName = districtDetails1.get(0)+";"+districtDetails1.get(1);
			 return districtName;
		}
		
		
	}
	catch (Exception e) {
	  logger.debug(e.getMessage(),e);
	}
	finally
	{
		dbUtil.closeConnection();
	}
	return districtName;
	
}

public ArrayList<CaveatsDTO> getTehsil(CaveatsDTO caveatsDTO) {
	
	DBUtility dbUtil = null;
	ArrayList tehsilList1 = null;
	ArrayList tehsilList = null;
	ArrayList<CaveatsDTO> caveatsList = new ArrayList<CaveatsDTO>();
	
	String sql =null;
	int param [] = {Integer.parseInt(caveatsDTO.getDistrictId())};
	
	try {
		 
		if("en".equalsIgnoreCase(caveatsDTO.getLanguage())){
			sql = CommonSQL.TEHSIL_NAME_EN;
		}else if("hi".equalsIgnoreCase(caveatsDTO.getLanguage())){
			sql = CommonSQL.TEHSIL_NAME_HI;
		}
		dbUtil = new DBUtility();
		dbUtil.createPreparedStatement(sql);
		//tehsilList1 = dbUtil.executeQuery(new String[] { caveatsDTO.getDistrictId() });
		tehsilList1 = dbUtil.executeQuery(param);
		if(null!=tehsilList1 && tehsilList1.size()>0){
			for(int i=0; i<tehsilList1.size(); i++){
				CaveatsDTO caveatsDTO1 = new CaveatsDTO();
				tehsilList = (ArrayList) tehsilList1.get(i);
				
				String tehsilId = (String) tehsilList.get(0);
				String tehsilName = (String) tehsilList.get(1);
				
				caveatsDTO1.setTehsilId(tehsilId);
				caveatsDTO1.setTehsilName(tehsilName);
				
				caveatsList.add(caveatsDTO1);
				
			}
		}
		
		
	} catch (Exception e) {
		logger.error(e);
	} finally {
		
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	return caveatsList;

}

public ArrayList<CaveatsDTO> getArea(CaveatsDTO caveatsDTO) {
	
	DBUtility dbUtil = null;
	ArrayList areaList1 = null;
	ArrayList areaList = null;
	ArrayList<CaveatsDTO> caveatsList = new ArrayList<CaveatsDTO>();
	
	String sql =null;
	//int param [] = {Integer.parseInt(caveatsDTO.getDistrictId())};
	
	try {
		 
		if("en".equalsIgnoreCase(caveatsDTO.getLanguage())){
			sql = CommonSQL.AREA_NAME_EN;
		}else if("hi".equalsIgnoreCase(caveatsDTO.getLanguage())){
			sql = CommonSQL.AREA_NAME_HI;
		}
		dbUtil = new DBUtility();
		dbUtil.createStatement();
		//tehsilList1 = dbUtil.executeQuery(new String[] { caveatsDTO.getDistrictId() });
		areaList1 = dbUtil.executeQuery(sql);
		if(null!=areaList1 && areaList1.size()>0){
			for(int i=0; i<areaList1.size(); i++){
				CaveatsDTO caveatsDTO1 = new CaveatsDTO();
				areaList = (ArrayList) areaList1.get(i);
				
				String areaId = (String) areaList.get(0);
				String areaName = (String) areaList.get(1);
				
				caveatsDTO1.setAreaId(areaId);
				caveatsDTO1.setAreaName(areaName);
				
				caveatsList.add(caveatsDTO1);
				
			}
		}
		
		
	} catch (Exception e) {
		logger.error(e);
	} finally {
		
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	return caveatsList;

}

public ArrayList<CaveatsDTO> getSubAreaList(CaveatsDTO caveatsDTO) {
	
	DBUtility dbUtil = null;
	ArrayList subAreaList1 = null;
	ArrayList subAreaList = null;
	ArrayList<CaveatsDTO> caveatsList = new ArrayList<CaveatsDTO>();
	
	String sql =null;
	String param [] = {caveatsDTO.getAreaName()};
	String param1 [] = {caveatsDTO.getAreaName(),caveatsDTO.getTehsilName()};
	
	try {
		if("2".equalsIgnoreCase(caveatsDTO.getAreaId())){
			if("en".equalsIgnoreCase(caveatsDTO.getLanguage())){
				sql = CommonSQL.SUB_AREA_NAME_EN_UR;
			}else if("hi".equalsIgnoreCase(caveatsDTO.getLanguage())){
				sql = CommonSQL.SUB_AREA_NAME_HI_UR;
			}
		}else{
			if("en".equalsIgnoreCase(caveatsDTO.getLanguage())){
				sql = CommonSQL.SUB_AREA_NAME_EN;
			}else if("hi".equalsIgnoreCase(caveatsDTO.getLanguage())){
				sql = CommonSQL.SUB_AREA_NAME_HI;
			}
		}
		
		dbUtil = new DBUtility();
		
		dbUtil.createPreparedStatement(sql);
		if("2".equalsIgnoreCase(caveatsDTO.getAreaId())){
			subAreaList1 = dbUtil.executeQuery(param1);
		}else{
			subAreaList1 = dbUtil.executeQuery(param);
		}
		
		if(null!=subAreaList1 && subAreaList1.size()>0){
			
			for(int i=0; i<subAreaList1.size(); i++){
				CaveatsDTO caveatsDTO1 = new CaveatsDTO();
				subAreaList = (ArrayList) subAreaList1.get(i);
				
				String subAreaId = (String) subAreaList.get(0);
				String subAreaName = (String) subAreaList.get(1);
				
				caveatsDTO1.setSubAreaId(subAreaId);
				caveatsDTO1.setSubAreaName(subAreaName);
				
				caveatsList.add(caveatsDTO1);
				
			}
		}
		
		
	} catch (Exception e) {
		logger.error(e);
	} finally {
		
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	return caveatsList;

}

public ArrayList<CaveatsDTO> getWardPatwari(CaveatsDTO caveatsDTO) {
	ArrayList wardPatwariList = null;
	ArrayList wardPatwariList1 = null;
	ArrayList<CaveatsDTO> caveatsList = new ArrayList<CaveatsDTO>();
	DBUtility dbUtil = null;
	try {
		dbUtil = new DBUtility();
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	try {
		if (caveatsDTO.getLanguage().equalsIgnoreCase("en")) {
			dbUtil
					.createPreparedStatement(CommonSQL.WARD_PATWARI_NAME_EN);
		} else {
			dbUtil
					.createPreparedStatement(CommonSQL.WARD_PATWARI_NAME_HI);
		}
		wardPatwariList = dbUtil.executeQuery(new String[] { caveatsDTO.getSubAreaName(),
				caveatsDTO.getTehsilName() });
		if(null!=wardPatwariList && wardPatwariList.size()>0){
			
			for(int i=0; i<wardPatwariList.size(); i++){
				CaveatsDTO caveatsDTO1 = new CaveatsDTO();
				wardPatwariList1 = (ArrayList) wardPatwariList.get(i);
				
				String wardPatwariId = (String) wardPatwariList1.get(0);
				String wardPatwariName = (String) wardPatwariList1.get(1);
				String subAreawardMappingId = (String)wardPatwariList1.get(2);
				
				
				caveatsDTO1.setWardPatwariId(wardPatwariId);
				caveatsDTO1.setWardPatwariName(wardPatwariName);
				caveatsDTO1.setSubAreaWardMappingId(subAreawardMappingId);
				
				caveatsList.add(caveatsDTO1);
				
			}
			
		}
		
		return caveatsList;

	} catch (Exception e) {
		logger.error(e);
		return null;
	} finally {
		try {
			dbUtil.closeConnection();
		} catch (Exception e) {
			logger.error(e);

		}

	}
}

public ArrayList<CaveatsDTO> getColonyList(CaveatsDTO caveatsDTO) {
	ArrayList colonyList = null;
	ArrayList colonyList1 = null;
	ArrayList<CaveatsDTO> caveatsList = new ArrayList<CaveatsDTO>();
	DBUtility dbUtil = null;
	try {
		dbUtil = new DBUtility();
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	try {
		
		if (caveatsDTO.getLanguage().equalsIgnoreCase("en")) {
			dbUtil.createPreparedStatement(CommonSQL.COLONY_NAME_EN);
		} else {
			dbUtil.createPreparedStatement(CommonSQL.COLONY_NAME_HI);
		}
		colonyList = dbUtil.executeQuery(new String[] {caveatsDTO.getSubAreaWardMappingId()});
		if(null!=colonyList && colonyList.size()>0){
			
			for(int i=0; i<colonyList.size(); i++){
				CaveatsDTO caveatsDTO1 = new CaveatsDTO();
				colonyList1 = (ArrayList) colonyList.get(i);
				
				String colonyId = (String) colonyList1.get(0);
				String colonyName = (String) colonyList1.get(1);
				
				caveatsDTO1.setColonyId(colonyId);
				caveatsDTO1.setColonyName(colonyName);
				
				caveatsList.add(caveatsDTO1);
				
			}
			
		}
		
		return caveatsList;

	} catch (Exception e) {
		logger.error(e);
		return null;
	} finally {
		try {
			dbUtil.closeConnection();
		} catch (Exception e) {
			logger.error(e);

		}

	}
}

/*public ArrayList<CaveatsDTO> getPropertyTypeList(CaveatsDTO caveatsDTO) {
	ArrayList propertyList = null;
	ArrayList propertyList1 = null;
	ArrayList<CaveatsDTO> caveatsList = new ArrayList<CaveatsDTO>();
	DBUtility dbUtil = null;
	String sql = null;
	try {
		dbUtil = new DBUtility();
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	try {
		
		if (caveatsDTO.getLanguage().equalsIgnoreCase("en")) {
			sql=CommonSQL.PROPERTY_TYPE_NAME_EN;
		} else {
			sql=CommonSQL.PROPERTY_TYPE_NAME_HI;
		}
		dbUtil.createStatement();
		propertyList = dbUtil.executeQuery(sql);
		if(null!=propertyList && propertyList.size()>0){
			
			for(int i=0; i<propertyList.size(); i++){
				CaveatsDTO caveatsDTO1 = new CaveatsDTO();
				propertyList1 = (ArrayList) propertyList.get(i);
				
				String propertyId = (String) propertyList1.get(0);
				String propertyName = (String) propertyList1.get(1);
				
				caveatsDTO1.setPropertyId(propertyId);
				caveatsDTO1.setPropertyTypeName(propertyName);
				
				caveatsList.add(caveatsDTO1);
				
			}
			
		}
		
		return caveatsList;

	} catch (Exception e) {
		logger.error(e);
		return null;
	} finally {
		try {
			dbUtil.closeConnection();
		} catch (Exception e) {
			logger.error(e);

		}

	}
}*/

public ArrayList<CaveatsDTO> fetchPropertyTxnID(String registrationNumber,String lang) {
	ArrayList data = null;
	ArrayList regData = null;
	ArrayList<CaveatsDTO> caveatsList = new ArrayList<CaveatsDTO>();
	boolean flag = false;
	String query = null;

	
	try {
		DBUtility util = new DBUtility();
		try {		
			
			query = CommonSQL.GET_CAVEATS_PROPERTY_DETLS;
			util.createPreparedStatement(query);
			data = util.executeQuery(new String[]{registrationNumber});
			
			if(null!=data && data.size()>0){
				for(int i=0; i<data.size(); i++){
					CaveatsDTO caveatsDTO1 = new CaveatsDTO();
					regData =  (ArrayList<String>) data.get(i);
					
					
					if(null!=(String)regData.get(0) && !"".equalsIgnoreCase((String)regData.get(0))){
						caveatsDTO1.setPropertyTxnId((String)regData.get(0));
					}else{
						caveatsDTO1.setPropertyTxnId("NA");
					}
					
					if(null!=(String)regData.get(1) && !"".equalsIgnoreCase((String)regData.get(1)) && "en".equalsIgnoreCase(lang)){
						caveatsDTO1.setPropertyTypeId((String)regData.get(1));
					}else if(null!=(String)regData.get(5) && !"".equalsIgnoreCase((String)regData.get(5)) && "hi".equalsIgnoreCase(lang)){
						caveatsDTO1.setPropertyTypeId((String)regData.get(5));
					}else{
						caveatsDTO1.setPropertyTypeId("NA");
					}
					if(null!=(String)regData.get(2) && !"".equalsIgnoreCase((String)regData.get(2))){
						caveatsDTO1.setProtestID((String)regData.get(2));
					}else{
						caveatsDTO1.setProtestID("NA");
					}
					
					if(null!=(String)regData.get(3) && !"".equalsIgnoreCase((String)regData.get(3)) && "en".equalsIgnoreCase(lang)){
						caveatsDTO1.setProtestStatus((String)regData.get(3));
					}else if(null!=(String)regData.get(3) && !"".equalsIgnoreCase((String)regData.get(3)) && "hi".equalsIgnoreCase(lang)){
						if("LOGGED".equalsIgnoreCase((String)regData.get(3))){
							caveatsDTO1.setProtestStatus("लॉग");
						}else if("RELEASED".equalsIgnoreCase((String)regData.get(3))){
							caveatsDTO1.setProtestStatus("जारी");
						}else{
							caveatsDTO1.setProtestStatus("NA");
						}
					}else{
						caveatsDTO1.setProtestStatus("NA");
					}
					if(null!=(String)regData.get(4) && !"".equalsIgnoreCase((String)regData.get(4))){
						caveatsDTO1.setPropDistrictId((String)regData.get(4));
					}else{
						caveatsDTO1.setPropDistrictId("NA");
					}
					
					caveatsList.add(caveatsDTO1);
					
				}
			}

		} catch (Exception e) {
			 logger.error(e.getMessage(), e);
		}
		finally {
			util.closeConnection();
		}
	} catch (Exception e) {
		 logger.error(e.getMessage(), e);
	}
	return caveatsList;
}

public String fetchServiceFeeDetls(String funid) {
	String serviceFee = null;
	try {
		DBUtility util = new DBUtility();
		try {
			String query = CommonSQL.GET_CAVEATS_SERVICE_FEE;
			util.createPreparedStatement(query);
			serviceFee = util.executeQry(new String [] {funid});
			
		} catch (Exception e) {
			 logger.error(e.getMessage(), e);
		}
		finally {
			util.closeConnection();
		}
	} catch (Exception e) {
		 logger.error(e.getMessage(), e);
	}
	return serviceFee;
}

public String getRegTxnID(String eRegNo) throws Exception {

	String[] regDtl = { eRegNo };
	String regTxnID = "";
	DBUtility dbUtility = null;
	try {
		dbUtility = new DBUtility();
		dbUtility.createPreparedStatement(CommonSQL.GET_REG_TXN_ID);
		regTxnID = dbUtility.executeQry(regDtl);
	} catch (Exception x) {
		logger.debug(x);
	} finally {
		if (dbUtility != null) {
			dbUtility.closeConnection();
		}
	}

	return regTxnID;
}

public boolean updateRegProtestDetls(CaveatsDTO caveatsDTO) throws Exception {
   
	String[] regDtl = new String [3];
	
	if("pageSubmit".equalsIgnoreCase(caveatsDTO.getActionName())){
		regDtl[0] = caveatsDTO.getPropertyTxnLock();
		regDtl[1] = "RELEASED";
		regDtl[2] = caveatsDTO.getRegistrationNumber();
	}else if("protestLoggedDownldRecpt".equalsIgnoreCase(caveatsDTO.getActionName())){
		regDtl[0] = caveatsDTO.getPropertyTxnLock();
		regDtl[1] = "LOGGED";
		regDtl[2] = caveatsDTO.getRegistrationNumber();
	}

	boolean flag = false;
	DBUtility dbUtility = null;
	try {
		dbUtility = new DBUtility();
		dbUtility.createPreparedStatement(CommonSQL.UPDATE_REG_PROTEST_DETLS);
		flag = dbUtility.executeUpdate(regDtl);
	} catch (Exception x) {
		logger.debug(x);
	} finally {
		if (dbUtility != null) {
			dbUtility.closeConnection();
		}
	}

	return flag;
}
public boolean updatePropertyProtestDetls(CaveatsDTO caveatsDTO,String protestRelId) throws Exception {

	boolean flag = false;
	DBUtility dbUtility = null;
	try {
		     String regDtl [] = new String [3];
		     String regDtl1 [] = new String [3];
				if("pageSubmit".equalsIgnoreCase(caveatsDTO.getActionName()) && "Yes".equalsIgnoreCase(caveatsDTO.getSampadaDocFlag())){
					regDtl [0]= protestRelId;
					regDtl [1]= "RELEASED";
					regDtl [2]= caveatsDTO.getPropertyId();
					
					dbUtility = new DBUtility();
					dbUtility.createPreparedStatement(CommonSQL.UPDATE_PROPERTY_PROTEST_DETLS);
					flag = dbUtility.executeUpdate(regDtl);
					
				}else if("protestLoggedDownldRecpt".equalsIgnoreCase(caveatsDTO.getActionName()) && "Yes".equalsIgnoreCase(caveatsDTO.getSampadaDocFlag())){
					regDtl [0]= caveatsDTO.getProtestID();
					regDtl [1]= "LOGGED";
					regDtl [2]= caveatsDTO.getPropertyId();
					
					dbUtility = new DBUtility();
					dbUtility.createPreparedStatement(CommonSQL.UPDATE_PROPERTY_PROTEST_DETLS);
					flag = dbUtility.executeUpdate(regDtl);
				}
				
				
				if("protestLoggedDownldRecpt".equalsIgnoreCase(caveatsDTO.getActionName())){
					
					regDtl1 [0]= "LOGGED";
					regDtl1 [1]= caveatsDTO.getUserId();
					regDtl1 [2]= caveatsDTO.getProtestID();
					
				 dbUtility = new DBUtility();
				 dbUtility.createPreparedStatement(CommonSQL.UPDATE_PROTEST_DETLS);
				 flag = dbUtility.executeUpdate(regDtl1);
				 
				}
		
	} catch (Exception x) {
		logger.debug(x);
	} finally {
		if (dbUtility != null) {
			dbUtility.closeConnection();
		}
	}

	return flag;
}


public boolean insertProtestDetls(CaveatsForm caveatsFrm) throws Exception {
	DBUtility dbUtility = null;
	String protestIdVal = null;
	CaveatsDTO caveatDTO = caveatsFrm.getCaveatsDTO();
	boolean flag = false;
	ArrayList<String> list = new ArrayList<String>();
  try {

	if("Yes".equalsIgnoreCase(caveatDTO.getSampadaDocFlag())){
		

		int len = caveatsFrm.getSelectedList().size();
		String protestIdValues = "";
		for(int i=0; i<len; i++){
			
			String key = "7801";
			Date date = new Date();
			SimpleDateFormat dateformatter = new SimpleDateFormat("dd/MM/yyyy");
			String arr [] = dateformatter.format(date).split("/");
			String protestId = key+arr[0]+arr[1]+arr[2]; 
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			String protestIdSeq = dbUtility.executeQry("SELECT IGRS_PROTEST_TXN_DETLS_SEQ.NEXTVAL FROM DUAL");
			
			if(protestIdSeq.length()==1){
				protestIdSeq="00000"+protestIdSeq;
			}else if(protestIdSeq.length()==2){
				protestIdSeq="0000"+protestIdSeq;
			}else if(protestIdSeq.length()==3){
				protestIdSeq="000"+protestIdSeq;
			}else if(protestIdSeq.length()==4){
				protestIdSeq="00"+protestIdSeq;
			}else if(protestIdSeq.length()==5){
				protestIdSeq="0"+protestIdSeq;
			}
			protestIdVal = protestId+protestIdSeq;
			list.add(protestIdVal);
			if(len>1){
				caveatDTO.setTransactionID(caveatDTO.getRegistrationNumber());
				
			}else{
				caveatDTO.setTransactionID(protestIdVal);
			}
			
			if(len>1){
				if(i==len-1){
					protestIdValues = protestIdValues + protestIdVal;
				}else{
					protestIdValues = protestIdValues + protestIdVal + ",";
				}
			}else{
				protestIdValues = protestIdVal;
			}
			caveatDTO.setProtestIdValues(protestIdValues);
			
			CaveatsDTO caveatDTO1 = (CaveatsDTO) caveatsFrm.getSelectedList().get(i);
			String[] regDtl = new String [23];
			
			regDtl[0] = protestIdVal;
			regDtl[1]=caveatDTO.getRegistrationNumber();
			regDtl[2]=caveatDTO1.getPropertyTxnId();
			regDtl[3]=caveatDTO.getSampadaDocFlag();
			regDtl[4]=caveatDTO.getDocumentName();
			regDtl[5]=caveatDTO.getUploaded_doc_path();
			regDtl[6]=caveatDTO.getCaseNum();
			regDtl[7]=caveatDTO.getCourtOrderDate();
			regDtl[8]=caveatDTO.getBankCourtName();
			regDtl[9]=caveatDTO.getBankCourtAddress();
			regDtl[10]=caveatDTO.getProtestCourtDistrict();
			regDtl[11]=caveatDTO.getBankCourtPostalCode();
			regDtl[12]=caveatDTO.getBankCourtPhoneNumber();
			regDtl[13]=caveatDTO.getCaveatApplicantName();
			regDtl[14]=caveatDTO.getPropertyTxnLock();
			regDtl[15]=caveatDTO.getStayOrderStartDate();
			regDtl[16]=caveatDTO.getStayOrderUptoDate();
			regDtl[17]=caveatDTO.getUserId();
			regDtl[18]=caveatDTO.getPblAmount();
			regDtl[19]=caveatDTO.getCaveatDetails();
			regDtl[20]="LOGGED PAYMENT PENDING";
			regDtl[21]=caveatDTO.getProtestLogDistrict();
			regDtl[22]=caveatDTO.getMultiRegNumFlag();

				dbUtility = new DBUtility();
				dbUtility.createPreparedStatement(CommonSQL.INSERT_PROTEST_DETLS_SMPD_DOC_Y);
				flag = dbUtility.executeUpdate(regDtl);
		}
		caveatDTO.setProtestIdList(list);
		
	}else if("No".equalsIgnoreCase(caveatDTO.getSampadaDocFlag())){
		
		String key = "7801";
		Date date = new Date();
		SimpleDateFormat dateformatter = new SimpleDateFormat("dd/MM/yyyy");
		String arr [] = dateformatter.format(date).split("/");
		String protestId = key+arr[0]+arr[1]+arr[2]; 
		dbUtility = new DBUtility();
		dbUtility.createStatement();
		String protestIdSeq = dbUtility.executeQry("SELECT IGRS_PROTEST_TXN_DETLS_SEQ.NEXTVAL FROM DUAL");
		
		if(protestIdSeq.length()==1){
			protestIdSeq="00000"+protestIdSeq;
		}else if(protestIdSeq.length()==2){
			protestIdSeq="0000"+protestIdSeq;
		}else if(protestIdSeq.length()==3){
			protestIdSeq="000"+protestIdSeq;
		}else if(protestIdSeq.length()==4){
			protestIdSeq="00"+protestIdSeq;
		}else if(protestIdSeq.length()==5){
			protestIdSeq="0"+protestIdSeq;
		}
		protestIdVal = protestId+protestIdSeq;
		
		caveatDTO.setProtestID(protestIdVal);
		caveatDTO.setTransactionID(protestIdVal);
		
		caveatDTO.setProtestIdValues(protestIdVal);
        String[] regDtl = new String [32];
		
		regDtl[0] = protestIdVal;
		regDtl[1]=caveatDTO.getSampadaDocFlag();
		regDtl[2]=caveatDTO.getDistrictName();
		regDtl[3]=caveatDTO.getAreaName();
		regDtl[4]=caveatDTO.getSubAreaName();
		regDtl[5]=caveatDTO.getTehsilName();
		regDtl[6]=caveatDTO.getColonyName();
		regDtl[7]=caveatDTO.getWardPatwariName();
		regDtl[8]=caveatDTO.getPropertyDetails();
		regDtl[9]=caveatDTO.getDocumentName();
		regDtl[10]=caveatDTO.getUploaded_doc_path();
		regDtl[11]=caveatDTO.getProtestCourtDistrict();
		regDtl[12]=caveatDTO.getCaseNum();
		regDtl[13]=caveatDTO.getCourtOrderDate();
		regDtl[14]=caveatDTO.getBankCourtName();
		regDtl[15]=caveatDTO.getBankCourtAddress();
		regDtl[16]=caveatDTO.getBankCourtPostalCode();
		regDtl[17]=caveatDTO.getBankCourtPhoneNumber();
		regDtl[18]=caveatDTO.getCaveatApplicantName();
		regDtl[19]=caveatDTO.getPropertyTxnLock();
		regDtl[20]=caveatDTO.getStayOrderStartDate();
		regDtl[21]=caveatDTO.getStayOrderUptoDate();
		regDtl[22]=caveatDTO.getUserId();
		regDtl[23]=caveatDTO.getPblAmount();
		regDtl[24]=caveatDTO.getCaveatDetails();
		regDtl[25]="LOGGED PAYMENT PENDING";
		
		regDtl[26]=caveatDTO.getAreaNameVal();
		regDtl[27]=caveatDTO.getSubAreaNameVal();
		regDtl[28]=caveatDTO.getTehsilNameVal();
		regDtl[29]=caveatDTO.getWardPatwariNameVal();
		regDtl[30]=caveatDTO.getColonyNameVal();
		regDtl[31]=caveatDTO.getProtestLogDistrict();
	
		

			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(CommonSQL.INSERT_PROTEST_DETLS_SMPD_DOC_N);
			flag = dbUtility.executeUpdate(regDtl);
	}
	
	} catch (Exception x) {
		logger.debug(x);
	} finally {
		if (dbUtility != null) {
			dbUtility.closeConnection();
		}
	}

	return flag;
}

public String validateRegNo(CaveatsDTO caveatsDTO) throws Exception {

	String[] regDtl = {caveatsDTO.getRegistrationNumber() };
	DBUtility dbUtility = null;
	ArrayList list = null;
	String data = null;
	ArrayList list1 = null;
	try {
				dbUtility = new DBUtility();
				dbUtility.createPreparedStatement(CommonSQL.VALIDATE_REG_NO);
				list  = dbUtility.executeQuery(regDtl);
				
				if(null!=list && list.size()>0){
					for(int i=0; i<list.size(); i++){
						list1 = (ArrayList) list.get(i);
						data = list1.get(0)+";"+list1.get(1);
					}
				}
		
	} catch (Exception x) {
		logger.debug(x);
	} finally {
		if (dbUtility != null) {
			dbUtility.closeConnection();
		}
	}

	return data;
}

public ArrayList getProtestDetails(CaveatsDTO caveatsDTO) throws Exception {

	CaveatsDTO caveatsDTO1 = new CaveatsDTO();
	DBUtility dbUtility = null;
	ArrayList list = null;
	ArrayList list1 = null;
	String data = null;
	//ArrayList list1 = null;
	try {
				dbUtility = new DBUtility();
				dbUtility.createPreparedStatement(CommonSQL.GET_PROTESTREL_DATA);
				list  = dbUtility.executeQuery(new String[]{caveatsDTO.getProtestID()});

	} catch (Exception x) {
		logger.debug(x);
	} finally {
		if (dbUtility != null) {
			dbUtility.closeConnection();
		}
	}

	return list;
}
public ArrayList<CaveatsDTO> getProtestId(CaveatsDTO caveatsDTO,String disName, String distNameHin) throws Exception {

	String[] regDtl = {disName,distNameHin,caveatsDTO.getFromDate(),caveatsDTO.getToDate()};
	DBUtility dbUtility = null;
	ArrayList list = null;
	ArrayList list1 = null;
	ArrayList<CaveatsDTO> protestList = new ArrayList<CaveatsDTO>();
	
	try {
				dbUtility = new DBUtility();
				dbUtility.createPreparedStatement(CommonSQL.GET_PROTEST_ID);
				
				list  = dbUtility.executeQuery(regDtl);
				
				if(null!=list && list.size()>0){
					for(int i=0; i<list.size(); i++){
						CaveatsDTO caveatsDto = new CaveatsDTO();
						list1 = (ArrayList) list.get(i);
						String protestId = (String) list1.get(0);
						String protestLoggedDate = (String) list1.get(1);
						
						//String date1 = protestLoggedDate.substring(0, 8);
						
						//String date2 [] = date1.split("-");
						//String protestLoggedDate1 = date2[2]+"-"+date2[1]+"-"+date2[0];
						caveatsDto.setProtestID(protestId);
						caveatsDto.setCreatedDate(protestLoggedDate);
						
						protestList.add(caveatsDto);
						
					}
				}
		
	} catch (Exception x) {
		logger.debug(x);
	} finally {
		if (dbUtility != null) {
			dbUtility.closeConnection();
		}
	}

	return protestList;
}

public ArrayList getPaymentDetails(CaveatsDTO caveatsDTO) throws Exception {

	String regNo = caveatsDTO.getRegistrationNumber();
	String protestId = caveatsDTO.getProtestID();
	String multiRegFlag = caveatsDTO.getMultiRegNumFlag();
	CaveatsDTO caveatsDTO1 = new CaveatsDTO();
	DBUtility dbUtility = null;
	ArrayList list = null;
	String data = null;
	//ArrayList list1 = null;
	try {
				dbUtility = new DBUtility();
				dbUtility.createPreparedStatement(CommonSQL.GET_PROTEST_PAYMENT_DETLS);
			logger.debug(" payment sql query  "+CommonSQL.GET_PROTEST_PAYMENT_DETLS);
			 
			    if(null!= regNo && !"".equalsIgnoreCase(regNo) && "Yes".equalsIgnoreCase(multiRegFlag)){
						list  = dbUtility.executeQuery(new String []{regNo,regNo});
						logger.debug(" payment result set inside getPaymentDetails of Log protest  "+list.size() + " reg id :: " +regNo);
				}else{
					list  = dbUtility.executeQuery(new String []{protestId,protestId});
					logger.debug(" From inside getPaymentDetails fun of Log protest id and reg id "+protestId + " reg id ::  " +regNo);
				}
				
				
	} catch (Exception x) {
		logger.debug(x);
		x.printStackTrace();
	} finally {
		if (dbUtility != null) {
			dbUtility.closeConnection();
		}
	}

	return list;
}

public boolean insertProtestReleaseDetls(CaveatsForm caveatsFrm) throws Exception {
	DBUtility dbUtility = null;
	String protestRelIdVal = null;
	CaveatsDTO caveatDTO = caveatsFrm.getCaveatsDTO();
	boolean flag = false;
	ArrayList<String> list = new ArrayList<String>();
  try {

		String key = "8701";
		Date date = new Date();
		SimpleDateFormat dateformatter = new SimpleDateFormat("dd/MM/yyyy");
		String arr [] = dateformatter.format(date).split("/");
		String protestId = key+arr[0]+arr[1]+arr[2]; 
		dbUtility = new DBUtility();
		dbUtility.createStatement();
		String protestIdSeq = dbUtility.executeQry("SELECT IGRS_PROTEST_TXN_DETLS_SEQ.NEXTVAL FROM DUAL");
		
		if(protestIdSeq.length()==1){
			protestIdSeq="00000"+protestIdSeq;
		}else if(protestIdSeq.length()==2){
			protestIdSeq="0000"+protestIdSeq;
		}else if(protestIdSeq.length()==3){
			protestIdSeq="000"+protestIdSeq;
		}else if(protestIdSeq.length()==4){
			protestIdSeq="00"+protestIdSeq;
		}else if(protestIdSeq.length()==5){
			protestIdSeq="0"+protestIdSeq;
		}
		protestRelIdVal = protestId+protestIdSeq;
		caveatsFrm.setProtestRelId(protestRelIdVal);
        String[] regDtl = new String [35];
		
		regDtl[0] = protestRelIdVal;
		regDtl[1]=caveatDTO.getSampadaDocFlag();
		regDtl[2]=caveatDTO.getPropertyId();
		regDtl[3]=caveatDTO.getRegistrationNumber();
		regDtl[4]=caveatDTO.getDistrictName();
		regDtl[5]=caveatDTO.getAreaNameVal();
		regDtl[6]=caveatDTO.getSubAreaNameVal();
		regDtl[7]=caveatDTO.getTehsilNameVal();
		regDtl[8]=caveatDTO.getWardPatwariNameVal();
		regDtl[9]=caveatDTO.getColonyNameVal();
		regDtl[10]=caveatDTO.getPropertyDetails();
		regDtl[11]=caveatDTO.getDocumentName();
		regDtl[12]=caveatDTO.getUploaded_doc_path();
		regDtl[13]=caveatDTO.getProtestCourtDistrict();
		regDtl[14]=caveatDTO.getCaseNum();
		regDtl[15]=caveatDTO.getCourtOrderDate();
		regDtl[16]=caveatDTO.getBankCourtName();
		regDtl[17]=caveatDTO.getBankCourtAddress();
		if("NA".equalsIgnoreCase(caveatDTO.getBankCourtPostalCode())){
			regDtl[18]=null;
		}else{
			regDtl[18]=caveatDTO.getBankCourtPostalCode();
		}
		if("NA".equalsIgnoreCase(caveatDTO.getBankCourtPhoneNumber())){
			regDtl[19]=null;
		}else{
			regDtl[19]=caveatDTO.getBankCourtPhoneNumber();
		}
		
		regDtl[20]=caveatDTO.getCaveatApplicantName();
		regDtl[21]=caveatDTO.getPropertyTxnLock();
		regDtl[22]=caveatDTO.getStayOrderStartDate();
		regDtl[23]=caveatDTO.getUserId();
		regDtl[24]=caveatDTO.getPblAmount();
		regDtl[25]=caveatDTO.getPdAmount();
		regDtl[26]=caveatDTO.getPaidByUser();
		regDtl[27]=caveatDTO.getPaymentDate();
		regDtl[28]=caveatDTO.getCaveatDetails();
		regDtl[29]="RELEASED";

		regDtl[30]=caveatDTO.getProtestID();
		regDtl[31]=caveatDTO.getReleaseDocName();
		regDtl[32]=caveatDTO.getRemarksForRelease();
		regDtl[33]=caveatDTO.getProtestRelDistrict();
		regDtl[34]=caveatDTO.getReleaseDocPath();
		
		
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(CommonSQL.INSERT_PROTEST_REL_DETLS);
			flag = dbUtility.executeUpdate(regDtl);

	} catch (Exception x) {
		logger.debug(x);
	} finally {
		if (dbUtility != null) {
			dbUtility.closeConnection();
		}
	}

	return flag;
}

public ArrayList<CaveatsDTO> getProtestSearchData(CaveatsDTO caveatsDTO,String distName, String distNameHi) throws Exception {
	
	//String[] regDtl = {caveatsDTO.getFromDate(),caveatsDTO.getToDate()};
	DBUtility dbUtility = null;
	ArrayList list = null;
	ArrayList list1 = null;
	ArrayList<CaveatsDTO> protestList = new ArrayList<CaveatsDTO>();
	String language = caveatsDTO.getLanguage();
	String status = caveatsDTO.getProtestStatus();
	boolean protestflg = false;
	try {
				dbUtility = new DBUtility();
				
				if(null!=caveatsDTO.getProtestID() && !"".equalsIgnoreCase(caveatsDTO.getProtestID())
						&& null!=caveatsDTO.getProtestStatus() && !"".equalsIgnoreCase(caveatsDTO.getProtestStatus())
						&& null!=caveatsDTO.getFromDate() && !"".equalsIgnoreCase(caveatsDTO.getFromDate()) && null!=caveatsDTO.getToDate() && !"".equalsIgnoreCase(caveatsDTO.getToDate())){
					
					String[] regDtl = {caveatsDTO.getProtestID(),caveatsDTO.getProtestStatus(),distName,distNameHi,caveatsDTO.getFromDate(),caveatsDTO.getToDate()};
					dbUtility.createPreparedStatement(CommonSQL.GET_PROTEST_SEARCH_DATA8);
					list  = dbUtility.executeQuery(regDtl);
					
				}else if(null!=caveatsDTO.getProtestID() && !"".equalsIgnoreCase(caveatsDTO.getProtestID())
						&& null!=caveatsDTO.getProtestStatus() && !"".equalsIgnoreCase(caveatsDTO.getProtestStatus())){
					
					String[] regDtl = {caveatsDTO.getProtestID(),caveatsDTO.getProtestStatus(),distName,distNameHi};
					dbUtility.createPreparedStatement(CommonSQL.GET_PROTEST_SEARCH_DATA9);
					list  = dbUtility.executeQuery(regDtl);
					
				}else if(null!=caveatsDTO.getProtestStatus() && !"".equalsIgnoreCase(caveatsDTO.getProtestStatus())
						&& null!=caveatsDTO.getFromDate() && !"".equalsIgnoreCase(caveatsDTO.getFromDate()) 
						&& null!=caveatsDTO.getToDate() && !"".equalsIgnoreCase(caveatsDTO.getToDate())){
					
					String[] regDtl = {caveatsDTO.getProtestStatus(),distName,distNameHi,caveatsDTO.getFromDate(),caveatsDTO.getToDate()};
					dbUtility.createPreparedStatement(CommonSQL.GET_PROTEST_SEARCH_DATA7);
					list  = dbUtility.executeQuery(regDtl);
					
				}else if(null!=caveatsDTO.getProtestID() && !"".equalsIgnoreCase(caveatsDTO.getProtestID())
						&& null!=caveatsDTO.getFromDate() && !"".equalsIgnoreCase(caveatsDTO.getFromDate()) && null!=caveatsDTO.getToDate() && !"".equalsIgnoreCase(caveatsDTO.getToDate())){
					
					String[] regDtl = {caveatsDTO.getProtestID(),distName,distNameHi,caveatsDTO.getFromDate(),caveatsDTO.getToDate()};
					dbUtility.createPreparedStatement(CommonSQL.GET_PROTEST_SEARCH_DATA6);
					list  = dbUtility.executeQuery(regDtl);
					
				}else if(null!=caveatsDTO.getProtestID() && !"".equalsIgnoreCase(caveatsDTO.getProtestID())){
					String[] regDtl = {caveatsDTO.getProtestID(),distName,distNameHi};
					dbUtility.createPreparedStatement(CommonSQL.GET_PROTEST_SEARCH_DATA2);
					list  = dbUtility.executeQuery(regDtl);
				}
				else if(null!=caveatsDTO.getProtestStatus() && !"".equalsIgnoreCase(caveatsDTO.getProtestStatus())){

					String[] regDtl = {caveatsDTO.getProtestStatus(),distName,distNameHi};
					dbUtility.createPreparedStatement(CommonSQL.GET_PROTEST_SEARCH_DATA13);
					
						list  = dbUtility.executeQuery(regDtl);
	
				}
				else if(null!=caveatsDTO.getFromDate() && !"".equalsIgnoreCase(caveatsDTO.getFromDate()) && null!=caveatsDTO.getToDate() && !"".equalsIgnoreCase(caveatsDTO.getToDate())){
					String[] regDtl = {distName,distNameHi,caveatsDTO.getFromDate(),caveatsDTO.getToDate()};
					dbUtility.createPreparedStatement(CommonSQL.GET_PROTEST_SEARCH_DATA);
					list  = dbUtility.executeQuery(regDtl);
				}
				
				
				
				if(null!=list && list.size()>0){
					for(int i=0; i<list.size(); i++){
						CaveatsDTO caveatsDto = new CaveatsDTO();
						list1 = (ArrayList) list.get(i);
						String protestId = (String) list1.get(0);
						String protestLoggedDate = (String) list1.get(1);
						String registrationNo = (String) list1.get(2);
						String protestStatus = (String) list1.get(3);
						
						//String date1 = protestLoggedDate.substring(0, 8);
						
						//String date2 [] = date1.split("-");
						//String protestLoggedDate1 = date2[2]+"-"+date2[1]+"-"+date2[0];
						caveatsDto.setProtestID(protestId);
						if(null!=protestLoggedDate && !"".equalsIgnoreCase(protestLoggedDate)){
							caveatsDto.setCreatedDate(protestLoggedDate);
						}else{
							caveatsDto.setCreatedDate("NA");
						}
						if(null!=registrationNo && !"".equalsIgnoreCase(registrationNo)){
							caveatsDto.setRegistrationNumber(registrationNo);
						}else{
							caveatsDto.setRegistrationNumber("NA");
						}
						caveatsDto.setProtestStatus(protestStatus);
						protestList.add(caveatsDto);
						
					}
				}
		
	} catch (Exception x) {
		logger.debug(x);
	} finally {
		if (dbUtility != null) {
			dbUtility.closeConnection();
		}
	}

	return protestList;
}

public ArrayList<CaveatsDTO> getProtestReportData(CaveatsDTO caveatsDTO, String disName,String distHinName) throws Exception {
	
	//String[] regDtl = {caveatsDTO.getFromDate(),caveatsDTO.getToDate()};
	DBUtility dbUtility = null;
	ArrayList list = null;
	ArrayList list1 = null;
	ArrayList<CaveatsDTO> protestList = new ArrayList<CaveatsDTO>();
	String language = caveatsDTO.getLanguage();
	String status = caveatsDTO.getProtestStatus();
	boolean protestflg = false;
	try {
				dbUtility = new DBUtility();
				if(null!=caveatsDTO.getRegistrationNumber() && !"".equalsIgnoreCase(caveatsDTO.getRegistrationNumber())
					&& null!=caveatsDTO.getFromDate() && !"".equalsIgnoreCase(caveatsDTO.getFromDate()) 
					&& null!=caveatsDTO.getToDate() && !"".equalsIgnoreCase(caveatsDTO.getToDate())
					&& null!=disName && !"".equalsIgnoreCase(disName)
					&& null!=distHinName && !"".equalsIgnoreCase(distHinName)){
					
					String[] regDtl = {disName,distHinName,caveatsDTO.getRegistrationNumber(),caveatsDTO.getFromDate(),caveatsDTO.getToDate()};
					dbUtility.createPreparedStatement(CommonSQL.GET_PROTEST_SEARCH_DATA11);
					list  = dbUtility.executeQuery(regDtl);
					
				}else if(null!=caveatsDTO.getFromDate() && !"".equalsIgnoreCase(caveatsDTO.getFromDate()) 
						&& null!=caveatsDTO.getToDate() && !"".equalsIgnoreCase(caveatsDTO.getToDate())
						&& null!=disName && !"".equalsIgnoreCase(disName)
						&& null!=distHinName && !"".equalsIgnoreCase(distHinName)){
					
					String[] regDtl = {disName,distHinName,caveatsDTO.getFromDate(),caveatsDTO.getToDate()};
					dbUtility.createPreparedStatement(CommonSQL.GET_PROTEST_SEARCH_DATA10);
					list  = dbUtility.executeQuery(regDtl);
				}
					/*else if(null!=caveatsDTO.getFromDate() && !"".equalsIgnoreCase(caveatsDTO.getFromDate()) && null!=caveatsDTO.getToDate() && !"".equalsIgnoreCase(caveatsDTO.getToDate())){
				}
					
					String[] regDtl = {caveatsDTO.getFromDate(),caveatsDTO.getToDate()};
					dbUtility.createPreparedStatement(CommonSQL.GET_PROTEST_SEARCH_DATA5);
					list  = dbUtility.executeQuery(regDtl);
					
				}*/

				if(null!=list && list.size()>0){
					for(int i=0; i<list.size(); i++){
						CaveatsDTO caveatsDto = new CaveatsDTO();
						list1 = (ArrayList) list.get(i);
						String protestId = (String) list1.get(0);
						String protestLoggedDate = (String) list1.get(1);
						String registrationNo = (String) list1.get(2);
						String protestStatus = (String) list1.get(3);
						String districtName = (String) list1.get(4);
						
						//String date1 = protestLoggedDate.substring(0, 8);
						
						//String date2 [] = date1.split("-");
						//String protestLoggedDate1 = date2[2]+"-"+date2[1]+"-"+date2[0];
						if(null!=protestId && !"".equalsIgnoreCase(protestId)){
							caveatsDto.setProtestID(protestId);
						}else{
							caveatsDto.setProtestID("NA");
						}
						if(null!=protestLoggedDate && !"".equalsIgnoreCase(protestLoggedDate)){
							caveatsDto.setCreatedDate(protestLoggedDate);
						}else{
							caveatsDto.setCreatedDate("NA");
						}
						if(null!=registrationNo && !"".equalsIgnoreCase(registrationNo)){
							caveatsDto.setRegistrationNumber(registrationNo);
						}else{
							caveatsDto.setRegistrationNumber("NA");
						}
						if(null!=protestStatus && !"".equalsIgnoreCase(protestStatus)){
							caveatsDto.setProtestStatus(protestStatus);
						}else{
							caveatsDto.setProtestStatus("NA");
						}
						if(null!=districtName && !"".equalsIgnoreCase(districtName)){
							caveatsDto.setDistrictName(districtName);
						}else{
							caveatsDto.setDistrictName("NA");
						}

						protestList.add(caveatsDto);
						
					}
				}
		
	} catch (Exception x) {
		logger.debug(x);
	} finally {
		if (dbUtility != null) {
			dbUtility.closeConnection();
		}
	}

	return protestList;
}

public String validateProtestRelStatus(CaveatsDTO caveatsDTO) throws Exception {

	String[] regDtl = {caveatsDTO.getProtestID()};
	CaveatsDTO caveatsDTO1 = new CaveatsDTO();
	DBUtility dbUtility = null;
	
	/*ArrayList list = new ArrayList();
	ArrayList list1 = new ArrayList();*/
	String data = null;
	//ArrayList list1 = null;
	try {
				dbUtility = new DBUtility();
					dbUtility.createPreparedStatement(CommonSQL.VALIDATE_PROTEST_REL_STATUS);
					data  = dbUtility.executeQry(regDtl);
				
					/*if(null!=list && list.size()>0){
						for(int i=0; i<list.size(); i++){
							
							list1 =  (ArrayList)list.get(i);
							String protestStatus = (String) list1.get(0);
							String district = (String) list1.get(1);
							
							data = protestStatus+";"+district;
						}
					}*/
			
	
	} catch (Exception x) {
		logger.debug(x);
	} finally {
		if (dbUtility != null) {
			dbUtility.closeConnection();
		}
	}

	return data;
}

public boolean updateProtestStatus(CaveatsDTO caveatsDTO) throws Exception {

	String lan = caveatsDTO.getLanguage();
	String protestId = caveatsDTO.getProtestID();
	String paidAmount = caveatsDTO.getPdAmount();
	String paidby = caveatsDTO.getPaidByUser();
	String paymentDate = caveatsDTO.getPaymentDate();
	CaveatsDTO caveatsDTO1 = new CaveatsDTO();
	DBUtility dbUtility = null;
	
	boolean flag = false;
	try {
				dbUtility = new DBUtility();
				
					dbUtility.createPreparedStatement(CommonSQL.UPDATE_PROTEST_STATUS);
					flag  = dbUtility.executeUpdate(new String [] {"LOGGED PAYMENT COMPLETED",paidAmount,paidby,paymentDate, protestId});

	} catch (Exception x) {
		logger.debug(x);
	} finally {
		if (dbUtility != null) {
			dbUtility.closeConnection();
		}
	}

	return flag;
}

public boolean updateProtestLogStatus(CaveatsDTO caveatsDTO) throws Exception {

	//String[] regDtl = {caveatsDTO.getPdAmount(),caveatsDTO.getPaidByUser(),caveatsDTO.getProtestID()};
	CaveatsDTO caveatsDTO1 = new CaveatsDTO();
	DBUtility dbUtility = null;
	
	boolean flag = false;
	//ArrayList list1 = null;
	try {
				dbUtility = new DBUtility();
				   
					   dbUtility.createPreparedStatement(CommonSQL.UPDATE_PROTEST_STATUS);

						flag  = dbUtility.executeUpdate(new String []{"LOGGED",caveatsDTO.getProtestID()});

	} catch (Exception x) {
		logger.debug(x);
	} finally {
		if (dbUtility != null) {
			dbUtility.closeConnection();
		}
	}

	return flag;
}

public ArrayList getProtestDashboardDetails(CaveatsDTO caveatsDTO) throws Exception {

	CaveatsDTO caveatsDTO1 = new CaveatsDTO();
	DBUtility dbUtility = null;
	ArrayList list = null;
	String data = null;
	//ArrayList list1 = null;
	try {
				dbUtility = new DBUtility();

				dbUtility.createPreparedStatement(CommonSQL.GET_PROTEST_DATA);
					
				list  = dbUtility.executeQuery(new String[]{caveatsDTO.getProtestID()});
			
				
	
	} catch (Exception x) {
		logger.debug(x);
	} finally {
		if (dbUtility != null) {
			dbUtility.closeConnection();
		}
	}

	return list;
}

public String getUserTypeId(String userId) throws Exception {

	String typeId = "";
	DBUtility dbUtility = null;
	String[] param = { userId };
	try {
		dbUtility = new DBUtility();
		dbUtility.createPreparedStatement(CommonSQL.GET_USER_TYPE_ID);
		typeId = dbUtility.executeQry(param);

	} catch (Exception exception) {

		System.out.println("Exception in getUserTypeId-RegCommonDAO"
				+ exception);

	} finally {
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			logger.error("RegCommonDAO in dao start" + e.getStackTrace());
		}
	}

	return typeId;

}

public String getDistrictName1(String distId, String languageLocale)
		throws Exception {
	DBUtility dbUtility = null;
		// int regTxnIdSeq = 0;
		String distname = "";
		String sql = null;
		
		try {
		dbUtility = new DBUtility();
		
		
		String[] param = { distId };
		if (languageLocale
				.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
			sql = CommonSQL.GET_DISTRICT_NAME;
		} else {
			sql = CommonSQL.GET_DISTRICT_NAME_HINDI;
		}
		dbUtility.createPreparedStatement(sql);
		distname = dbUtility.executeQry(param);
		
		} catch (Exception exception) {
		
		System.out.println("CaveatsDAO Exception in getDistrictName"
				+ exception);
		
		} finally {
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			logger.error("CaveatsDAO in dao start" + e.getStackTrace());
		}
		}
		
		return distname;
		
}

public ArrayList<CaveatsDTO> fetchPaymentDetails(CaveatsDTO caveatsDTO) throws Exception {
	
	//String[] regDtl = {caveatsDTO.getFromDate(),caveatsDTO.getToDate()};
	DBUtility dbUtility = null;
	ArrayList list = null;
	ArrayList list1 = null;
	ArrayList<CaveatsDTO> protestList = new ArrayList<CaveatsDTO>();
	String language = caveatsDTO.getLanguage();
	String status = caveatsDTO.getProtestStatus();
	boolean protestflg = false;
	try {
				dbUtility = new DBUtility();
				 if(null!=caveatsDTO.getProtestStatus() && !"".equalsIgnoreCase(caveatsDTO.getProtestStatus())
						&& null!=caveatsDTO.getFromDate() && !"".equalsIgnoreCase(caveatsDTO.getFromDate()) 
						&& null!=caveatsDTO.getToDate() && !"".equalsIgnoreCase(caveatsDTO.getToDate())){
					
					String[] regDtl = {"LOGGED PAYMENT PENDING",caveatsDTO.getFromDate(),caveatsDTO.getToDate()};
					dbUtility.createPreparedStatement(CommonSQL.GET_PROTEST_SEARCH_DATA12);
					list  = dbUtility.executeQuery(regDtl);
					logger.debug(" 1st block payment sql query  "+CommonSQL.GET_PROTEST_SEARCH_DATA12);
					logger.debug(" From inside fetchPaymentDetails fun of Log protest  "+regDtl );
					
				}else if(null!=caveatsDTO.getProtestStatus() && !"".equalsIgnoreCase(caveatsDTO.getProtestStatus())){

					String[] regDtl = {"LOGGED PAYMENT PENDING"};
					dbUtility.createPreparedStatement(CommonSQL.GET_PROTEST_SEARCH_DATA3);
					
						list  = dbUtility.executeQuery(regDtl);
						
						logger.debug(" payment sql query  "+CommonSQL.GET_PROTEST_SEARCH_DATA3);
						 logger.debug(" From inside fetchPaymentDetails fun of Log protest  "+regDtl );
				}

				if(null!=list && list.size()>0){
					for(int i=0; i<list.size(); i++){
						CaveatsDTO caveatsDto = new CaveatsDTO();
						list1 = (ArrayList) list.get(i);
						String protestId = (String) list1.get(0);
						String protestLoggedDate = (String) list1.get(1);
						String registrationNo = (String) list1.get(2);
						String protestStatus = (String) list1.get(3);
						
						//String date1 = protestLoggedDate.substring(0, 8);
						
						//String date2 [] = date1.split("-");
						//String protestLoggedDate1 = date2[2]+"-"+date2[1]+"-"+date2[0];
						caveatsDto.setProtestID(protestId);
						if(null!=protestLoggedDate && !"".equalsIgnoreCase(protestLoggedDate)){
							caveatsDto.setCreatedDate(protestLoggedDate);
						}else{
							caveatsDto.setCreatedDate("NA");
						}
						if(null!=registrationNo && !"".equalsIgnoreCase(registrationNo)){
							caveatsDto.setRegistrationNumber(registrationNo);
						}else{
							caveatsDto.setRegistrationNumber("NA");
						}
						caveatsDto.setProtestStatus(protestStatus);
						caveatsDto.setMultiRegNumFlag((String) list1.get(4));
						protestList.add(caveatsDto);
						
					}
				}
				
				logger.debug(" payment resultset size  " + protestList.size());
		
	} /*catch (Exception x) {
		logger.debug(x);
		x.printStackTrace();
		throw x;
	}*/ finally {
		if (dbUtility != null) {
			dbUtility.closeConnection();
		}
	}

	return protestList;
}


public String fetchChallanPaySeqId(String paySeqId) throws Exception {

	DBUtility dbUtility = null;
	String payTxnId = null;

	try {
				dbUtility = new DBUtility();
				
					String[] regDtl = {paySeqId,paySeqId};
					dbUtility.createPreparedStatement(CommonSQL.GET_CHALLAN_PAYMENT_DETLS);
					
					payTxnId  = dbUtility.executeQry(regDtl);

	} catch (Exception x) {
		logger.debug(x);
	} finally {
		if (dbUtility != null) {
			dbUtility.closeConnection();
		}
	}

	return payTxnId;
}
/**
 * for inserting registration initiation payment txn details in db.
 * 
 * @param String
 *            []
 * @return boolean
 * @author ROOPAM
 */
public boolean insertPaymentDetails(CaveatsForm cfm , String paymentType) {
	boolean boo = false;
	DBUtility dbUtility = null;
	String sql = null;
	
	boolean insertsuccess = false;
	// RegCommonDAO dao = new RegCommonDAO();

	String[] param = new String[8];
	param[0] = cfm.getPaymentSeqId();
	param[1] = (String)cfm.getCaveatsDTO().getTransactionID();
	param[2] = cfm.getCaveatsDTO().getUserId();
	param[3] = "I";
	param[4] = paymentType;
	param[5] = "FUN_016";
	param[6] = cfm.getCaveatsDTO().getPblAmount();
	param[7] = cfm.getCaveatsDTO().getPblAmount();
	
	
	try {
		dbUtility = new DBUtility();
		dbUtility.setAutoCommit(false);
		sql = CommonSQL.INSERT_CAVEATS_PAYMENT_DETLS;
		dbUtility.createPreparedStatement(sql);
		boo = dbUtility.executeUpdate(param);
		if (boo) {

			dbUtility.commit();
		} else
		{
			dbUtility.rollback();
			throw new Exception();
		}
	} catch (Exception e) {
		logger.debug(e.getMessage(),e);
		return false;
	} finally {
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			logger.error("CaveatsDAO in dao start" + e.getStackTrace());
		}
	}
	return boo;
}

/**
 * for getting payment table primary key sequence id from db.
 * 
 * @param
 * @return String
 * @author ROOPAM
 */
public String getPaymentTxnIdSeq() throws Exception {
	DBUtility dbUtility = null;
	String seqId = "0";
	try {
		dbUtility = new DBUtility();
		dbUtility.createStatement();
		seqId = dbUtility
				.executeQry(CommonSQL.GET_CAVEATS_PAYMENT_TXN_ID_SEQ);
	} catch (Exception exception) {
		System.out.println("Exception in getPaymentTxnIdSeq-CaveatsDAO"
				+ exception);
	} finally {
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			logger.error("RegCommonDAO in dao start" + e.getStackTrace());
		}
	}
	return seqId;
}

public String getOfficeType(String officeId) {

	DBUtility dbUtility = null;
	String getOfficeType = null;
	String SQL = null;

	try {
		String param[] = new String[1];
		param[0] = officeId;

		dbUtility = new DBUtility();
		dbUtility.createPreparedStatement(CommonSQL.OFFICE_TYPE);

		getOfficeType = dbUtility.executeQry(param);

	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	return getOfficeType;
}

public String getDIGZoneDAO(String officeId) {
	String a = null;
	DBUtility dbUtil = null;
	try {
		String param[] = new String[1];
		param[0] = officeId;
		dbUtil = new DBUtility();
		String SQL = CommonSQL.INSP_GET_ZONE_DIG;
		// logger.debug("before getting Zone details ");
		dbUtil.createPreparedStatement(SQL);
		// logger.debug("SQL:"+SQL);
		ArrayList data = dbUtil.executeQuery(param);
		ArrayList data1 = (ArrayList) data.get(0);
		a = (String) data1.get(0);
		// logger.debug("value of  Zone_Id) :- " + a);
	} catch (Exception e) {
		e.printStackTrace();
		// logger.debug("Exception in getZoneDetails() :- " + e);
	} finally {
		try {
			dbUtil.closeConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	return a;

}

public ArrayList getDistDIGListDAO(String zoneId,String language) {

	ArrayList list = new ArrayList();
	String param[] = new String[1];
	param[0] = zoneId;
	String SQL = null;
	if (language.equalsIgnoreCase("en"))
	SQL = CommonSQL.INSP_GET_DISTRICT_ZONE_LIST;
	else 
		SQL=CommonSQL.INSP_GET_DISTRICT_ZONE_LIST_HINDI;
	DBUtility dbUtil = null;
	try {
		dbUtil = new DBUtility();
		dbUtil.createPreparedStatement(SQL);
		list = dbUtil.executeQuery(param);
		logger
				.info("Wipro in SpotInspDAO - getDIGList() AFTER EXCUTIN QUERY list values"
						+ list);
	} catch (Exception e) {
		logger
				.error("exception in calling at SpotInspDAO Class at getDIGList()  "
						+ e);
	} finally {
		try {
			dbUtil.closeConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	return list;

}

public synchronized ArrayList getDistrict(String stateId, String officeID)
	throws Exception {
	String arry[] = new String[1];
	logger.debug("State ID:-" + stateId);
	logger.debug("Office ID:-" + officeID);
	ArrayList list = new ArrayList();
	
	DBUtility dbUtil = null;
	
	try {
	logger.debug("Before initialize DBUtility");
	dbUtil = new DBUtility();
	
	String sql = CommonSQL.GET_OFFICE_TYPE_ID;
	dbUtil.createPreparedStatement(sql);
	String officeTypeID = dbUtil.executeQry(new String[] { officeID });
	String distQuery = "";
	// 4 is DIG and 2 is DR
	if (officeTypeID == null)
		officeTypeID = "";
	if ("4".equalsIgnoreCase(officeTypeID)) {
		distQuery = CommonSQL.GET_DIST_NAME_DIG;
		arry[0] = officeID;
	} 
	else if ("2".equalsIgnoreCase(officeTypeID)) {
		distQuery = CommonSQL.GET_DIST_NAME_DR;
		arry[0] = officeID;
	}
	 else if ("3".equalsIgnoreCase(officeTypeID)) {// FOR SR
		distQuery = CommonSQL.GET_DIST_NAME_SR;
		arry[0] = officeID;
	} else {
		distQuery = CommonSQL.DISTRICT_QUERY_HINDI;
		arry[0] = stateId;
	}
	dbUtil.createPreparedStatement(distQuery);
	
	// }
	list = dbUtil.executeQuery(arry);
	logger.debug("After initialize DBUtility");
	if (logger.isDebugEnabled()) {
		logger.debug("getDistrict(String) - end");
	}
	} catch (Exception x) {
	logger.debug(x);
	} finally {
	if (dbUtil != null) {
		dbUtil.closeConnection();
	}
	}
	return list;
}


//Log protest changes by santosh
public String getDistrictDetls(String distId, String language) throws Exception {
	DBUtility dbUtil = null;
	
	String districtDetails = null;
	
	try
	{
		String sql =null;
		
		String param[] = new String[1];
		param[0] = distId;
		if("en".equalsIgnoreCase(language)){
			sql = CommonSQL.GET_DISTRICT_DETLS;
		}else if("hi".equalsIgnoreCase(language)){
			sql = CommonSQL.GET_DISTRICT_DETLS_H;
		}
		 
		dbUtil = new DBUtility();
		
		dbUtil.createPreparedStatement(sql);
		 //districtName = dbUtil.executeQry(param);
		 districtDetails = dbUtil.executeQry(param);
		
		
		//return districtDetails;
		
	}
	catch (Exception e) {
	  logger.debug(e.getMessage(),e);
	}
	finally
	{
		dbUtil.closeConnection();
	}
	return districtDetails;
	
}

//Log protest changes by santosh
public String getDistrictValues(String distId, String language) throws Exception {
	DBUtility dbUtil = null;
	
	ArrayList districtDetails = null;
	ArrayList districtDetails1 = null;
	String districtVal = null;
	try
	{
		String sql =null;
		
		String param[] = new String[1];
		param[0] = distId;
		
			sql = CommonSQL.GET_DISTRICT_VALUES;
		
		 
		dbUtil = new DBUtility();
		
		dbUtil.createPreparedStatement(sql);
		 //districtName = dbUtil.executeQry(param);
		 districtDetails = dbUtil.executeQuery(param);
		 
		 if(null!=districtDetails && districtDetails.size()>0){
			 
			 for(int i=0; i<districtDetails.size(); i++){
				 districtDetails1 = (ArrayList) districtDetails.get(0);
				 districtVal = districtDetails1.get(0)+";"+districtDetails1.get(1);
			 }
			 
		 }
		
		
		//return districtDetails;
		
	}
	catch (Exception e) {
	  logger.debug(e.getMessage(),e);
	}
	finally
	{
		dbUtil.closeConnection();
	}
	return districtVal;
	
}

//Log protest changes by santosh
public String getDistrictIdVal(String distName, String language) throws Exception {
	DBUtility dbUtil = null;
	
	String districtDetails = null;
	
	try
	{
		String sql =null;
		
		String param[] = new String[1];
		param[0] = distName;
		/*if("en".equalsIgnoreCase(language)){
			sql = CommonSQL.GET_DISTRICT_ID_VAL;
		}else if("hi".equalsIgnoreCase(language)){
			sql = CommonSQL.GET_DISTRICT_ID_VAL_H;
		}*/
		 
		dbUtil = new DBUtility();
		
		sql = CommonSQL.GET_DISTRICT_ID_VAL;
		dbUtil.createPreparedStatement(sql);
		 //districtName = dbUtil.executeQry(param);
		 districtDetails = dbUtil.executeQry(param);
		 
		 if(null!=districtDetails && !"".equalsIgnoreCase(districtDetails)){
			 
		 }else{
			 sql = CommonSQL.GET_DISTRICT_ID_VAL_H;
				dbUtil.createPreparedStatement(sql);
				 //districtName = dbUtil.executeQry(param);
				 districtDetails = dbUtil.executeQry(param);
		 }
		
		
		//return districtDetails;
		
	}
	catch (Exception e) {
	  logger.debug(e.getMessage(),e);
	}
	finally
	{
		dbUtil.closeConnection();
	}
	return districtDetails;
	
}

public String validateRelDistrict(String protestId) throws Exception {

String[] regDtl = {protestId};

DBUtility dbUtility = null;

/*ArrayList data = null;
ArrayList data1 = null;*/
String dataVal = null;
try {
			dbUtility = new DBUtility();
				dbUtility.createPreparedStatement(CommonSQL.VALIDATE_PROTEST_DISTRICT);
				dataVal  = dbUtility.executeQry(regDtl);
			
				/* if(null!=data && data.size()>0){
					 
					 for(int i=0; i<data.size(); i++){
						 data1 = (ArrayList) data.get(0);
						 dataVal = data1.get(0)+";"+data1.get(1);
					 }
					 
				 }*/
				
} catch (Exception x) {
	logger.debug(x);
} finally {
	if (dbUtility != null) {
		dbUtility.closeConnection();
	}
}

return dataVal;
}



	}

