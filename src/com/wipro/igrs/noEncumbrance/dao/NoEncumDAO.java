package com.wipro.igrs.noEncumbrance.dao;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import org.apache.log4j.Logger;
import com.wipro.igrs.noEncumbrance.dto.FloorDetailsDTO;
import com.wipro.igrs.noEncumbrance.dto.KhasraDTO;
import com.wipro.igrs.noEncumbrance.dto.NoEncumDTO;
import com.wipro.igrs.noEncumbrance.sql.NoEncumSQL;
import com.wipro.igrs.newreginit.bo.RegCommonBO;
import com.wipro.igrs.newreginit.constant.RegInitConstant;
import com.wipro.igrs.newreginit.sql.RegCommonSQL;
import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.copyissuance.constant.CommonConstant;
import com.wipro.igrs.copyissuance.dto.CertifiedCopyDetailsDTO;
import com.wipro.igrs.copyissuance.sql.CommonSQL;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.empmgmt.dto.FamilyMemberDTO;

public class NoEncumDAO {
	
	//DBUtility dbUtil;
	String SQL;
	private Logger logger = 
		(Logger) Logger.getLogger(NoEncumDAO.class);
    public NoEncumDAO() {}
    
    //$01 Method for Retrieving Country from COUNTRY_MASTER
    /**
     * 
     * @return
     * @throws ServletException
     * @throws IOException
     * @throws SQLException
     * @throws Exception
     */
    public ArrayList  countryStackDAO(String languageLocale) throws ServletException,IOException,SQLException,Exception {
    
    	ArrayList  ar1=new ArrayList ();
        DBUtility dbUtil=null;
        logger.debug("WE ARE IN DAO-countryStack");
        try {
            ArrayList typeList=new ArrayList();
            ArrayList typeTemp=new ArrayList();
           
            dbUtil=new DBUtility();
            String str=NoEncumSQL.IGRS_COUNTRY_MASTER; // Query for Country list from IGRS_COUNTRY_MASTER
            if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_HINDI)){
            	str=NoEncumSQL.IGRS_COUNTRY_MASTER_HINDI;
            }
            dbUtil.createStatement();
            typeList=dbUtil.executeQuery(str);
            logger.info("COUNTRY_typeList:="+typeList);
            if(typeList!=null){
                for(int i=0;i<typeList.size();i++) {
                    typeTemp=new ArrayList();
                    typeTemp=(ArrayList)typeList.get(i);
                    if(typeTemp.size()>0){
                        NoEncumDTO type = new NoEncumDTO();
                       	type.setCountryId((String)typeTemp.get(0));
                       	type.setCountryName((String)typeTemp.get(1));
                        ar1.add(type);
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
    		logger.error("using FINALLY Connection is closed");
            dbUtil.closeConnection(); 
        }
        return ar1;
    }
    
    //$01 Method for Retrieving Country from COUNTRY_MASTER
    /**
     * 
     * @return
     * @throws ServletException
     * @throws IOException
     * @throws SQLException
     * @throws Exception
     */
    public ArrayList  countryDAO(String langauge) throws ServletException,IOException,SQLException,Exception {
    
    	ArrayList  ar1=new ArrayList ();
        DBUtility dbUtil=null;
        logger.debug("WE ARE IN DAO-countryStack");
        try {
            ArrayList typeList=new ArrayList();
            ArrayList typeTemp=new ArrayList();
           
            dbUtil=new DBUtility();
            String str="";
            if("en".equalsIgnoreCase(langauge))
            {	
            str=NoEncumSQL.IGRS_COUNTRY_MASTER; // Query for Country list from IGRS_COUNTRY_MASTER
            }
            else
            {
                str=NoEncumSQL.IGRS_COUNTRY_MASTER_HI; // Query for Country list from IGRS_COUNTRY_MASTER

            }
            dbUtil.createStatement();
            typeList=dbUtil.executeQuery(str);
            logger.info("COUNTRY_typeList:="+typeList);
            if(typeList!=null){
                for(int i=0;i<typeList.size();i++) {
                    typeTemp=new ArrayList();
                    typeTemp=(ArrayList)typeList.get(i);
                    if(typeTemp.size()>0){
                    	CertifiedCopyDetailsDTO dto = new CertifiedCopyDetailsDTO();
                    	dto.setCountryId((String)typeTemp.get(0));
                    	dto.setCountryName((String)typeTemp.get(1));
                        ar1.add(dto);
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
    		logger.error("using FINALLY Connection is closed");
            dbUtil.closeConnection(); 
        }
        return ar1;
    }
   
    /**
     * 
     * @return
     * @throws ServletException
     * @throws IOException
     * @throws SQLException
     * @throws Exception
     */
    public ArrayList  countrypropDAO(String languageLocale) throws ServletException,IOException,SQLException,Exception {
        
    	ArrayList  ar1=new ArrayList ();
        DBUtility dbUtil=null;
        logger.debug("WE ARE IN DAO-countryStack");
        try {
            ArrayList typeList=new ArrayList();
            ArrayList typeTemp=new ArrayList();
           
            dbUtil=new DBUtility();
            String str=NoEncumSQL.IGRS_COUNTRY_MASTER; // Query for Country list from IGRS_COUNTRY_MASTER
            if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_HINDI)){
            	str=NoEncumSQL.IGRS_COUNTRY_MASTER;
            }
            dbUtil.createStatement();
            typeList=dbUtil.executeQuery(str);
            logger.info("COUNTRY_typeList:="+typeList);
            if(typeList!=null){
                for(int i=0;i<typeList.size();i++) {
                    typeTemp=new ArrayList();
                    typeTemp=(ArrayList)typeList.get(i);
                    if(typeTemp.size()>0){
                    	NoEncumDTO dto = new NoEncumDTO();
                    	dto.setPropCountryId((String)typeTemp.get(0));
                    	dto.setPropCountryName((String)typeTemp.get(1));
                        ar1.add(dto);
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
    		logger.error("using FINALLY Connection is closed");
            dbUtil.closeConnection(); 
        }
        return ar1;
    }
    
    /**
     * 
     * @param _countryIdVar
     * @return
     * @throws ServletException
     * @throws IOException
     * @throws SQLException
     * @throws Exception
     */
    public ArrayList  stateDAO(String _countryIdVar,String language)throws ServletException,IOException,SQLException,Exception{
        ArrayList  ar1=new ArrayList ();
        DBUtility dbUtil=null;
        logger.debug("WE ARE IN DAO-stateStack");
        try {
            ArrayList typeList=new ArrayList();
            ArrayList typeTemp=new ArrayList();
           
            dbUtil=new DBUtility();
            String str="";
            if("en".equalsIgnoreCase(language))
            {	
            str=NoEncumSQL.IGRS_STATE_MASTER; // Query for state list from IGRS_STATEMASTER
            }
            else
            {
             str=NoEncumSQL.IGRS_STATE_MASTER_HI; // Query for state list from IGRS_STATEMASTER
            }
            String param[]=new String[1];
            param[0]=""+_countryIdVar;
            dbUtil.createPreparedStatement(str);
            typeList=dbUtil.executeQuery(param);
            logger.info("STATE_typeList:="+typeList);
            if(typeList!=null){
                for(int i=0;i<typeList.size();i++) {
                    typeTemp=new ArrayList();
                    typeTemp=(ArrayList)typeList.get(i);
                    if(typeTemp.size()>0){
                    	CertifiedCopyDetailsDTO dto = new CertifiedCopyDetailsDTO();
                    	dto.setStateId((String)typeTemp.get(0));
                    	dto.setState((String)typeTemp.get(1));
                        ar1.add(dto);
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
        	logger.error("Connection is closed using FINALLY");
            dbUtil.closeConnection(); 
        }
        return ar1;
	}
    
    
    
    //$01 End
    //$02 Method for Retrieving State from STATE_MASTER
    /**
     * 
     * @param _countryIdVar
     * @return
     * @throws ServletException
     * @throws IOException
     * @throws SQLException
     * @throws Exception
     */
    public ArrayList  stateStackDAO(String _countryIdVar,String languageLocale)throws ServletException,IOException,SQLException,Exception{
        ArrayList  ar1=new ArrayList ();
        DBUtility dbUtil=null;
        logger.debug("WE ARE IN DAO-stateStack");
        try {
            ArrayList typeList=new ArrayList();
            ArrayList typeTemp=new ArrayList();
           
            dbUtil=new DBUtility();
            String str=NoEncumSQL.IGRS_STATE_MASTER; // Query for state list from IGRS_STATEMASTER
            if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_HINDI)){
            	str=NoEncumSQL.IGRS_STATE_MASTER_HINDI;
            }
            String param[]=new String[1];
            param[0]=""+_countryIdVar;
            dbUtil.createPreparedStatement(str);
            typeList=dbUtil.executeQuery(param);
            logger.info("STATE_typeList:="+typeList);
            if(typeList!=null){
                for(int i=0;i<typeList.size();i++) {
                    typeTemp=new ArrayList();
                    typeTemp=(ArrayList)typeList.get(i);
                    if(typeTemp.size()>0){
                        NoEncumDTO type = new NoEncumDTO();
                       	type.setStateId((String)typeTemp.get(0));
                       	type.setStateName((String)typeTemp.get(1));
                        ar1.add(type);
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
        	logger.error("Connection is closed using FINALLY");
            dbUtil.closeConnection(); 
        }
        return ar1;
	}
	// $02 End
    //$03 Method for Retrieving Districts from DISTRICT_MASTER
    /**
     * 
     * @param _stateIdVar
     * @return
     * @throws ServletException
     * @throws IOException
     * @throws SQLException
     * @throws Exception
     */
    public ArrayList  districtStackDAO(String _stateIdVar,String languageLocale)throws ServletException,IOException,SQLException,Exception{
        
        ArrayList  ar1=new ArrayList ();
        DBUtility dbUtil=null;
        logger.debug("WE ARE IN DAO-districtStack");
        try {
            ArrayList typeList=new ArrayList();
            ArrayList typeTemp=new ArrayList();
           
            dbUtil=new DBUtility();
            String str=NoEncumSQL.IGRS_DISTRICT_MASTER; // Query for district list from IGRS_DISTRICT_MASTER
            if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_HINDI)){
            	str=NoEncumSQL.IGRS_DISTRICT_MASTER_HINDI;
            }
            String param[]=new String[1];
            param[0]=""+_stateIdVar;
            dbUtil.createPreparedStatement(str);
            typeList=dbUtil.executeQuery(param);
            logger.info("DISTRICT_typeList:="+typeList);
            if(typeList!=null){
                for(int i=0;i<typeList.size();i++) {
                    typeTemp=new ArrayList();
                    typeTemp=(ArrayList)typeList.get(i);
                    if(typeTemp.size()>0){
                        NoEncumDTO type = new NoEncumDTO();
                       	type.setDistrictId((String)typeTemp.get(0));
                       	type.setDistrictName((String)typeTemp.get(1));
                        ar1.add(type);
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
            logger.error("DISTRICT Connection is closed using FINALLY");
            dbUtil.closeConnection(); 
        }
        return ar1;
	}
    
    /**
     * 
     * @param _stateIdVar
     * @return
     * @throws ServletException
     * @throws IOException
     * @throws SQLException
     * @throws Exception
     */
    public ArrayList  districtDAO(String _stateIdVar,String language)throws ServletException,IOException,SQLException,Exception{
        
        ArrayList  ar1=new ArrayList ();
        DBUtility dbUtil=null;
        logger.debug("WE ARE IN DAO-districtStack");
        try {
            ArrayList typeList=new ArrayList();
            ArrayList typeTemp=new ArrayList();
           
            dbUtil=new DBUtility();
            String str="";
            if("en".equalsIgnoreCase(language))
            {	
            str=NoEncumSQL.IGRS_DISTRICT_MASTER; // Query for district list from IGRS_DISTRICT_MASTER
            }
            else
            {
             str=NoEncumSQL.IGRS_DISTRICT_MASTER_HI; // Query for district list from IGRS_DISTRICT_MASTER
 	
            }
            String param[]=new String[1];
            param[0]=""+_stateIdVar;
            dbUtil.createPreparedStatement(str);
            typeList=dbUtil.executeQuery(param);
            logger.info("DISTRICT_typeList:="+typeList);
            if(typeList!=null){
                for(int i=0;i<typeList.size();i++) {
                    typeTemp=new ArrayList();
                    typeTemp=(ArrayList)typeList.get(i);
                    if(typeTemp.size()>0){
                    	CertifiedCopyDetailsDTO dto = new CertifiedCopyDetailsDTO();
                    	dto.setDistrictId((String)typeTemp.get(0));
                    	dto.setDistrictName((String)typeTemp.get(1));
                        ar1.add(dto);
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
            logger.error("DISTRICT Connection is closed using FINALLY");
            dbUtil.closeConnection(); 
        }
        return ar1;
	}
    
    
	// $03 End
    //$04 Method for Retrieving Photo Id Proof from PHOTO_ID_PROOF_TYPE_MASTER
    /**
     * 
     * @return
     * @throws ServletException
     * @throws IOException
     * @throws SQLException
     * @throws Exception
     */
    public ArrayList  photoIdStackDAO(String languageLocale)throws ServletException,IOException,SQLException,Exception{
        
        ArrayList  ar1=new ArrayList ();
        DBUtility dbUtil=null;
        logger.debug("WE ARE IN DAO-photoIdStack");
        try {
            ArrayList typeList=new ArrayList();
            ArrayList typeTemp=new ArrayList();
            dbUtil=new DBUtility();
            String str=NoEncumSQL.IGRS_PHOTOID_PROOF_MASTER; // Query for Photo Id Proof Type list from IGRS_PHOTOID_PROOF_TYPE_MASTER
            if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_HINDI)){
            	str=NoEncumSQL.IGRS_PHOTOID_PROOF_MASTER_HINDI;
            }
            dbUtil.createStatement();
            typeList=dbUtil.executeQuery(str);
            if(typeList!=null){
                for(int i=0;i<typeList.size();i++) {
                    typeTemp=new ArrayList();
                    typeTemp=(ArrayList)typeList.get(i);
                    if(typeTemp.size()>0){
                        NoEncumDTO type = new NoEncumDTO();
                        type.setPhotoProofTypeId((String)typeTemp.get(0));
                        type.setPhotoProofTypeName((String)typeTemp.get(1));
                        ar1.add(type);
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
        	logger.error("Connection is closed using FINALLY");
            dbUtil.closeConnection(); 
        }
        return ar1;
	}
	// $04 End
    //$05 Method for Retrieving Caste from CASTE_MASTER
    /**
     * 
     * @return
     * @throws ServletException
     * @throws IOException
     * @throws SQLException
     * @throws Exception
     */
    public ArrayList  casteStackDAO(String languageLocale)throws ServletException,IOException,SQLException,Exception{
    	logger.debug("WE ARE IN DAO-casteStack");
        ArrayList  ar1=new ArrayList ();
        DBUtility dbUtil=null;
        try {
            ArrayList typeList=new ArrayList();
            ArrayList typeTemp=new ArrayList();
            dbUtil=new DBUtility();
            String str=NoEncumSQL.IGRS_CASTE_MASTER;
            if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_HINDI)){
            	str=NoEncumSQL.IGRS_CASTE_MASTER_HINDI;
            }
            dbUtil.createStatement();
            typeList=dbUtil.executeQuery(str);
            if(typeList!=null){
                for(int i=0;i<typeList.size();i++) {
                    typeTemp=new ArrayList();
                    typeTemp=(ArrayList)typeList.get(i);
                    if(typeTemp.size()>0){
                        NoEncumDTO type = new NoEncumDTO();
                        type.setCasteId((String)typeTemp.get(0));
                        type.setCasteName((String)typeTemp.get(1));
                        ar1.add(type);
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
        	logger.error("Connection is closed using FINALLY");
            dbUtil.closeConnection(); 
        }
        return ar1;
	}
	// $05 End
    //$06 Method for Retrieving RELIGION from RELIGION_MASTER
    /**
     * 
     * @return
     * @throws ServletException
     * @throws IOException
     * @throws SQLException
     * @throws Exception
     */
    public ArrayList  religionStackDAO(String languageLocale)throws ServletException,IOException,SQLException,Exception{
    	logger.debug("WE ARE IN DAO-religionStack");
        ArrayList  ar1=new ArrayList ();
        DBUtility dbUtil=null;
        try {
            ArrayList typeList=new ArrayList();
            ArrayList typeTemp=new ArrayList();
            dbUtil=new DBUtility();
            String str=NoEncumSQL.IGRS_RELIGION_MASTER;
            if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_HINDI)){
            	str=NoEncumSQL.IGRS_RELIGION_MASTER_HINDI;
            }
            dbUtil.createStatement();
            typeList=dbUtil.executeQuery(str);
            if(typeList!=null){
                for(int i=0;i<typeList.size();i++) {
                    typeTemp=new ArrayList();
                    typeTemp=(ArrayList)typeList.get(i);
                    if(typeTemp.size()>0){
                        NoEncumDTO type = new NoEncumDTO();
                        type.setReligionId((String)typeTemp.get(0));
                        type.setReligionName((String)typeTemp.get(1));
                        ar1.add(type);
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
	    	logger.error("Connection is closed using FINALLY");
	        dbUtil.closeConnection(); 
	    }
	    return ar1;
	}
	// $06 End
   
    //$09 Method for Searching Estamp Code
    /**
     * 
     * @param _regNo
     * @return
     * @throws ServletException
     * @throws IOException
     * @throws SQLException
     * @throws Exception
     */
    
    
    
    public ArrayList regIdCheckInfo(String _regNo)throws ServletException,IOException,SQLException,Exception{
    	logger.debug("WE ARE IN DAO-regIdCheckDAO");
        DBUtility dbUtil=null;
        ArrayList typeList=null;
        try {
            dbUtil=new DBUtility();
            String str=NoEncumSQL.IGRS_NOENCUM_REGID_SEARCH;
            dbUtil.createPreparedStatement(str);
            String param[]=new String[1];
            param[0]=_regNo;
            typeList=dbUtil.executeQuery(param);
            return typeList;
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
	    	logger.error("Connection is closed using FINALLY");
	        dbUtil.closeConnection(); 
	    }
	    return typeList;
	}
    
    /**
     * 
     * @param _regNo
     * @return
     * @throws ServletException
     * @throws IOException
     * @throws SQLException
     * @throws Exception
     */
    public ArrayList regIdCheckDAO(String _regNo)throws ServletException,IOException,SQLException,Exception{
    	logger.debug("WE ARE IN DAO-regIdCheckDAO");
        DBUtility dbUtil=null;
        ArrayList typeList=null;
        try {
            dbUtil=new DBUtility();
            String str=NoEncumSQL.IGRS_NOENCUM_REGID_SEARCH;
            dbUtil.createPreparedStatement(str);
            String param[]=new String[1];
            param[0]=_regNo;
            typeList=dbUtil.executeQuery(param);
            return typeList;
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
	    	logger.error("Connection is closed using FINALLY");
	        dbUtil.closeConnection(); 
	    }
	    return typeList;
	}
	// $09 End
   
    // $11 Method for Retrieving Document Details
    /**
     * 
     * @param param
     * @return
     * @throws ServletException
     * @throws IOException
     * @throws SQLException
     * @throws Exception
     */
    public ArrayList  searchListViewDAO(String param[])throws ServletException,IOException,SQLException,Exception{
    	logger.debug("WE ARE IN DAO-searchListViewDAO");
        ArrayList  ar1=new ArrayList ();
        DBUtility dbUtil=null;
        try {
            ArrayList typeList=new ArrayList();
            ArrayList typeTemp=new ArrayList();
            dbUtil=new DBUtility();
            String str=NoEncumSQL.IGRS_NOENCUM_VIEW_PDETAILS;
            dbUtil.createPreparedStatement(str);
            typeList=dbUtil.executeQuery(param);
            if(typeList!=null){
                for(int i=0;i<typeList.size();i++){
                    typeTemp=new ArrayList();
                    typeTemp=(ArrayList)typeList.get(i);
                    if(typeTemp.size()>0){
                        NoEncumDTO type = new NoEncumDTO();
                        type.setCerCopyTxnId((String)typeTemp.get(0));
                        if(typeTemp.get(1)!=null && !typeTemp.get(1).toString().equalsIgnoreCase(""))
                        {
                        	type.setRegNo((String)typeTemp.get(1));
                        }
                        else
                        {
                        	type.setRegNo("NA");
                        }
                       if(typeTemp.get(2).toString().equals("P"))
                        {
                    	   type.setStatus("PENDING");
                        }
                       if(typeTemp.get(2).toString().equals("C"))
                       {
                    	   type.setStatus("PENDING");
                    	   
                       }
                       if(typeTemp.get(2).toString().equals("D"))
                       {
                   	   type.setStatus("DISPATCHED");
                       } 
                       type.setCreatedDate((String)typeTemp.get(3));
                        type.setSerialNo(i+1);
                        ar1.add(type);
                    }
                }
            }
	    }catch(IOException ie){
        	logger.error(ie);
        }
        catch(SQLException se){
        	se.printStackTrace();
        	logger.error(se);
        }
        catch(Exception e){
        	e.printStackTrace();
	    	logger.error(e);
	    }
	    finally {
	    	logger.error("Connection is closed using FINALLY");
	        dbUtil.closeConnection(); 
	    }
	    return ar1;
	}
    //$011 End
    //$09 Method for Searching User Id and details
    /**
     * 
     * @param param
     * @return
     * @throws ServletException
     * @throws IOException
     * @throws SQLException
     * @throws Exception
     */
    public ArrayList userIdCheckDAO(String param[])throws ServletException,IOException,SQLException,Exception{
    	logger.debug("WE ARE IN DAO-estampCheckDAO");
        DBUtility dbUtil=null;
        ArrayList typeList=null;
        try {
            dbUtil=new DBUtility();
            String str=NoEncumSQL.IGRS_NOENCUM_CHECK_USERID;
            dbUtil.createPreparedStatement(str);
            typeList=dbUtil.executeQuery(param);
            return typeList;
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
	    	logger.error("Connection is closed using FINALLY");
	        dbUtil.closeConnection(); 
	    }
	    return typeList;
	}
	// $09 End
    //$09 Method for Inserting User Details
    //public boolean insertSuppliDetailsDAO(String param1[],String param2[],String param3[],String propType)throws ServletException,IOException,SQLException,Exception{
  /*  public boolean insertSuppliDetailsDAO(String param1[],String param2[],String param3[],String propType)throws ServletException,IOException,SQLException,Exception{
    	logger.debug("WE ARE IN DAO-insertSuppliDetailsDAO");
        DBUtility dbUtil=null;
        boolean flag=false;
        try {
            dbUtil=new DBUtility();
            String str1=NoEncumSQL.IGRS_NOENCUM_INSERT_CERTIDETAILS;
            dbUtil.createPreparedStatement(str1);
            flag=dbUtil.executeUpdate(param2);
            if(flag)
            {
            	String str2=NoEncumSQL.IGRS_NOENCUM_INSERT_PARTY;
                dbUtil.createPreparedStatement(str2);
                flag=dbUtil.executeUpdate(param1);
              
                if(propType!=null){
	                if(flag)
	                {
	                	String str3=NoEncumSQL.IGRS_NOENCUM_PROPERTY_DETAILS;
	                    dbUtil.createPreparedStatement(str3);
	                    flag=dbUtil.executeUpdate(param3);
	                    if(flag)
	                    {
	                    	dbUtil.commit();
	                    }
	                    else{
	                    	dbUtil.rollback();
	                    }
	                }
	                else{
	                	dbUtil.rollback();
	                }
                }
               
                
                
            }
            else{
            	dbUtil.rollback();
            }
            return flag;	
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
	    	logger.error("Connection is closed using FINALLY");
	        dbUtil.closeConnection(); 
	    }
	    return false;
	}*/
	// $09 End
    //$012 Method for retrieving entire details
    /**
     * 
     * @param encDTO
     * @return
     * @throws ServletException
     * @throws IOException
     * @throws SQLException
     * @throws Exception
     */
    public boolean insertSuppliDetailsDAO(NoEncumDTO encDTO)throws ServletException,IOException,SQLException,Exception{
    	logger.debug("WE ARE IN DAO-insertSuppliDetailsDAO");
    	 String sqlQuery = "";
         int rowCount;
         Connection con=null;
         DBUtility dbUtil=null;
        boolean flag=false;
        IGRSCommon igrsCommon = null;
        try {
			igrsCommon = new IGRSCommon();
		} catch (Exception e) {
			e.printStackTrace();
		}
       try 
         {
    	   RegCommonBO bo = new RegCommonBO();
    	   
    	   
    	    dbUtil=new DBUtility("");
        	PreparedStatement prepStmt;        	
        	if (con == null || con.isClosed()) {
				con = dbUtil.getDBConnection();
			}
        	con.setAutoCommit(false); 
        	//Start insert into IGRS_CERTIFIED_COPY_TXN
        	sqlQuery = NoEncumSQL.IGRS_NOENCUM_INSERT_CERTIDETAILS;
			prepStmt = con.prepareStatement(sqlQuery);
			prepStmt.setString(1, encDTO.getCerCopyTxnId());
			prepStmt.setString(2, encDTO.getCurrentUserId());
			prepStmt.setString(3, "No-Encumbrance");
			prepStmt.setString(4,"U");
			prepStmt.setString(5,encDTO.getPurposeReq());
			prepStmt.setString(6,encDTO.getFunId());
			prepStmt.setString(7,encDTO.getRegNo());
			//encDTO.setFromDate(bo.convertCalenderDate(encDTO.getFromDate()));
			//encDTO.setToDate(bo.convertCalenderDate(encDTO.getToDate()));
			prepStmt.setString(8, "");
			prepStmt.setString(9, "");
			rowCount = prepStmt.executeUpdate();
			logger.debug("RowCount " + rowCount);
			prepStmt.close();
			//End insert into IGRS_CERTIFIED_COPY_TXN
			
			
			//Start insert into IGRS_CERCOPY_TXN_PARTY_DETAILS
			sqlQuery =NoEncumSQL.IGRS_NOENCUM_INSERT_PARTY;		
			prepStmt = con.prepareStatement(sqlQuery);				
			prepStmt.setString(1, encDTO.getCerCopyTxnId());
			prepStmt.setString(2, encDTO.getFname());
			prepStmt.setString(3, encDTO.getMname());
			prepStmt.setString(4, encDTO.getLname());
			prepStmt.setString(5, encDTO.getGender());
			prepStmt.setString(6, encDTO.getAge());// Age Int
			prepStmt.setString(7, encDTO.getNationality());
			prepStmt.setString(8, encDTO.getCountryId());
			prepStmt.setString(9, encDTO.getStateId());
			prepStmt.setString(10, encDTO.getDistrictName());
			prepStmt.setString(11, encDTO.getAddress());
			prepStmt.setString(12, encDTO.getPostalCode());//Postal Code NUmber
			prepStmt.setString(13, encDTO.getPhoneNo());
			prepStmt.setString(14, encDTO.getMobNo());
			prepStmt.setString(15, encDTO.getMailId());
			prepStmt.setString(16, encDTO.getPhotoProofTypeName());
			prepStmt.setString(17, encDTO.getPhotoId());
			prepStmt.setString(18, encDTO.getPhotoBankName());
			prepStmt.setString(19, encDTO.getPhotoBankAddress());
			prepStmt.setString(20, encDTO.getFatherName());
			prepStmt.setString(21, encDTO.getMotherName());
			prepStmt.setString(22, encDTO.getCasteName());
			prepStmt.setString(23, encDTO.getReligionName());
			prepStmt.setString(24, encDTO.getCurrentUserId());
			prepStmt.setString(25, encDTO.getTotalFee());
			prepStmt.setString(26, encDTO.getOtherFee());
			prepStmt.setString(27, encDTO.getFee());
			prepStmt.setString(28, encDTO.getSpouseName());
			if("Ex".equalsIgnoreCase(encDTO.getUserMode()))
    		{
			prepStmt.setString(10, encDTO.getDistrictId());
			prepStmt.setString(16, encDTO.getPhotoProofTypeId());
			prepStmt.setString(22, encDTO.getCasteId());
			prepStmt.setString(23, encDTO.getReligionId());    			
    		}
			
			rowCount = prepStmt.executeUpdate();		
			prepStmt.close();
			
			if(encDTO.getSelectedItems()!= null && encDTO.getSelectedItems().size()>0)
				{	
				sqlQuery=NoEncumSQL.INSERT_PROPERTY_ID_MAP;
					for(int i=0;i<encDTO.getSelectedItems().size();i++)
					{
						dbUtil.createStatement();
						String id = dbUtil.executeQry(NoEncumSQL.POPERTY_MAP_SEQUENCE);
						prepStmt = con.prepareStatement(sqlQuery);
						NoEncumDTO dt=(NoEncumDTO) encDTO.getSelectedItems().get(i);
						prepStmt.setString(1,id);
						prepStmt.setString(2, encDTO.getCerCopyTxnId());
						prepStmt.setString(3, dt.getPropertyTxnId());
						prepStmt.setString(4, encDTO.getUserId());
						prepStmt.executeUpdate();
						prepStmt.close();
					}
				}
			else 
			{
			//Start insert into IGRS_REG_INIT_PROPERTY_DETAILS
			//sqlQuery =NoEncumSQL.IGRS_NOENCUM_PROPERTY_DETAILS;		
			if("3".equals(encDTO.getPropertyName()))//Agriculture details
    		{
				sqlQuery=NoEncumSQL.INSERT_AGRI_PROPERTY_DETAILS;
				prepStmt = con.prepareStatement(sqlQuery);	
				prepStmt.setString(1, encDTO.getCerCopyTxnId());
				prepStmt.setString(2, encDTO.getPropCountryId());
				prepStmt.setString(3, encDTO.getStatePropId());
				prepStmt.setString(4, encDTO.getPropDistrictId());
				prepStmt.setString(5, encDTO.getPropTehesilName());
				prepStmt.setString(6, encDTO.getPropPatwariId().split("~")[0]);
				prepStmt.setString(7, encDTO.getPropGramName().split("~")[0]);
				prepStmt.setString(8, encDTO.getPropertyName());
				prepStmt.setString(9, encDTO.getMunicipaltyId().split("~")[0]);
				if("1".equals(encDTO.getAreaType()))
				{
					prepStmt.setString(10,"R");
				}
				else
				{
					prepStmt.setString(10,"U");
				}
				prepStmt.setString(11, encDTO.getAgViKhand());
				prepStmt.setString(12, encDTO.getAgRicircle());
				prepStmt.setString(13, encDTO.getAgLayoutDtls());
				prepStmt.setString(14, encDTO.getAgEastboundryDtls());
				prepStmt.setString(15, encDTO.getAgWestboundryDtls());
				prepStmt.setString(16, encDTO.getAgNorthboundryDtls());
				prepStmt.setString(17, encDTO.getAgSouthboundryDtls());
				prepStmt.setString(18, encDTO.getAgArea());
				prepStmt.setString(19, encDTO.getAgSizeLength());
				if(encDTO.getIsSplit()!=null && encDTO.getIsSplit().equalsIgnoreCase("Yes"))
				{
				prepStmt.setString(20,"Y");
				}
				else
				{
				prepStmt.setString(20,"N");
				}
				prepStmt.setString(21, encDTO.getAgIsIrigated());
				prepStmt.setString(22, encDTO.getUserId());
				prepStmt.setString(23, encDTO.getAgSizeBreadth());
				rowCount = prepStmt.executeUpdate();		
				prepStmt.close();
				if(encDTO.getKhasraList()!= null && encDTO.getKhasraList().size()>0)
				{	
				sqlQuery=NoEncumSQL.INSERT_KHASRA_DEATILS;
					for(int i=0;i<encDTO.getKhasraList().size();i++)
					{
						dbUtil.createStatement();
						String id = dbUtil.executeQry(NoEncumSQL.KHASRA_SEQ_NEXT);
						prepStmt = con.prepareStatement(sqlQuery);
						KhasraDTO dt=(KhasraDTO) encDTO.getKhasraList().get(i);
						prepStmt.setString(1,id);
						prepStmt.setString(2, encDTO.getCerCopyTxnId());
						prepStmt.setString(3, dt.getKhasraNumber());
						prepStmt.setString(4, dt.getRinPustika());
						prepStmt.setString(5, dt.getKhasraArea());
						prepStmt.setString(6, dt.getLagaan());
						prepStmt.setString(7, encDTO.getUserId());
						prepStmt.executeUpdate();
						prepStmt.close();
					}
				}
    		}
			if("2".equals(encDTO.getPropertyName()))//Building details
    		{
				
				sqlQuery=NoEncumSQL.INSERT_BUILD_PROPERTY_DETAILS;
				prepStmt = con.prepareStatement(sqlQuery);	
				prepStmt.setString(1, encDTO.getCerCopyTxnId());
				prepStmt.setString(2, encDTO.getPropCountryId());
				prepStmt.setString(3, encDTO.getStatePropId());
				prepStmt.setString(4, encDTO.getPropDistrictId());
				prepStmt.setString(5, encDTO.getPropTehesilName());
				prepStmt.setString(6, encDTO.getPropPatwariId().split("~")[0]);
				prepStmt.setString(7, encDTO.getPropGramName().split("~")[0]);
				prepStmt.setString(8, encDTO.getPropertyName());
				prepStmt.setString(9, encDTO.getMunicipaltyId().split("~")[0]);
				if("1".equals(encDTO.getAreaTypeId()))
				{
					prepStmt.setString(10,"R");
				}
				else
				{
					prepStmt.setString(10,"U");
				}
				prepStmt.setString(11, encDTO.getBuildViKhand());
				prepStmt.setString(12, encDTO.getBuildRicircle());
				prepStmt.setString(13, encDTO.getBuildLayout());
				prepStmt.setString(14, encDTO.getBuildPlotNo());
				prepStmt.setString(15, encDTO.getBuildEastboundryDtls());
				prepStmt.setString(16, encDTO.getBuildWestboundryDtls());
				prepStmt.setString(17, encDTO.getBuildNorthboundryDtls());
				prepStmt.setString(18, encDTO.getBuildSouthboundryDtls());
				prepStmt.setString(19, encDTO.getPlotArea());
				prepStmt.setString(20, encDTO.getBuildMohalla());
				prepStmt.setString(21, encDTO.getBuildCeilingType());
				prepStmt.setString(22, encDTO.getNoOfShop());
				prepStmt.setString(23, encDTO.getBuildingType());
				prepStmt.setString(24, encDTO.getBuildNazSheetNo());
				prepStmt.setString(25, encDTO.getUserId());
				rowCount = prepStmt.executeUpdate();		
				prepStmt.close();
				if(encDTO.getKhasraList()!= null && encDTO.getKhasraList().size()>0)
				{	
				sqlQuery=NoEncumSQL.INSERT_KHASRA_DEATILS;
					for(int i=0;i<encDTO.getKhasraList().size();i++)
					{
						dbUtil.createStatement();
						String id = dbUtil.executeQry(NoEncumSQL.KHASRA_SEQ_NEXT);
						prepStmt = con.prepareStatement(sqlQuery);
						KhasraDTO dt=(KhasraDTO) encDTO.getKhasraList().get(i);
						prepStmt.setString(1,id);
						prepStmt.setString(2, encDTO.getCerCopyTxnId());
						prepStmt.setString(3, dt.getKhasraNumber());
						prepStmt.setString(4, dt.getRinPustika());
						prepStmt.setString(5, dt.getKhasraArea());
						prepStmt.setString(6, dt.getLagaan());
						prepStmt.setString(7, encDTO.getUserId());
						prepStmt.executeUpdate();
						prepStmt.close();
					}
				}
				if(encDTO.getFloordetails()!= null && encDTO.getFloordetails().size()>0)
				{	
				sqlQuery=NoEncumSQL.INSERT_FLOOR_DETAILS;
					for(int i=0;i<encDTO.getFloordetails().size();i++)
					{
						dbUtil.createStatement();
						String id = dbUtil.executeQry(NoEncumSQL.FLOOR_SEQ_NEXT);
						prepStmt = con.prepareStatement(sqlQuery);
						FloorDetailsDTO dt=(FloorDetailsDTO) encDTO.getFloordetails().get(i);
						prepStmt.setString(1,id);
						prepStmt.setString(2, encDTO.getCerCopyTxnId());
						prepStmt.setString(3, dt.getFloorLabel());
						prepStmt.setString(4, dt.getBuildingSize());
						prepStmt.setString(5, dt.getBuildingSizeBreadth());
						prepStmt.setString(6, dt.getBuildingArea());
						prepStmt.setString(7, encDTO.getUserId());
						prepStmt.executeUpdate();
						prepStmt.close();
					}
				}
    		}
			if("1".equals(encDTO.getPropertyName()))//Plot details
    		{
				
				sqlQuery=NoEncumSQL.INSERT_PLOT_PROPERTY_DETAILS;
				
				prepStmt = con.prepareStatement(sqlQuery);	
				prepStmt.setString(1, encDTO.getCerCopyTxnId());
				prepStmt.setString(2, encDTO.getPropCountryId());
				prepStmt.setString(3, encDTO.getStatePropId());
				prepStmt.setString(4, encDTO.getPropDistrictId());
				prepStmt.setString(5, encDTO.getPropTehesilName());
				prepStmt.setString(6, encDTO.getPropPatwariId().split("~")[0]);
				prepStmt.setString(7, encDTO.getPropGramName().split("~")[0]);
				prepStmt.setString(8, encDTO.getPropertyName());
				prepStmt.setString(9, encDTO.getMunicipaltyId().split("~")[0]);
				if("1".equals(encDTO.getAreaType()))
				{
					prepStmt.setString(10,"R");
				}
				else
				{
					prepStmt.setString(10,"U");
				}
				prepStmt.setString(11, encDTO.getPltViKhand());
				prepStmt.setString(12, encDTO.getPltRicircle());
				prepStmt.setString(13, encDTO.getPltLayout());
				prepStmt.setString(14, encDTO.getPltPlotNo());
				prepStmt.setString(15, encDTO.getPltEastboundryDtls());
				prepStmt.setString(16, encDTO.getPltWestboundryDtls());
				prepStmt.setString(17, encDTO.getPltNorthboundryDtls());
				prepStmt.setString(18, encDTO.getPltSouthboundryDtls());
				prepStmt.setString(19, encDTO.getPltArea());
				prepStmt.setString(20, encDTO.getPltMohala());
				if(encDTO.getIsPlotSplit() != null && encDTO.getIsPlotSplit().equalsIgnoreCase("Yes"))
				{
					prepStmt.setString(21, "Y");
				}
				else
				{
					prepStmt.setString(21, "N");
				}
				prepStmt.setString(22, encDTO.getPltSizeLength());
				prepStmt.setString(23, encDTO.getPltCorner());
				prepStmt.setString(24, encDTO.getPltResiCom());
				prepStmt.setString(25, encDTO.getPltNazSheetNo());
				prepStmt.setString(26, encDTO.getUserId());
				prepStmt.setString(27, encDTO.getPltSizeBreadth());
				rowCount = prepStmt.executeUpdate();		
				prepStmt.close();
				if(encDTO.getKhasraList()!= null && encDTO.getKhasraList().size()>0)
				{	
				sqlQuery=NoEncumSQL.INSERT_KHASRA_DEATILS;
					for(int i=0;i<encDTO.getKhasraList().size();i++)
					{
						dbUtil.createStatement();
						String id = dbUtil.executeQry(NoEncumSQL.KHASRA_SEQ_NEXT);
						prepStmt = con.prepareStatement(sqlQuery);
						KhasraDTO dt=(KhasraDTO) encDTO.getKhasraList().get(i);
						prepStmt.setString(1,id);
						prepStmt.setString(2, encDTO.getCerCopyTxnId());
						prepStmt.setString(3, dt.getKhasraNumber());
						prepStmt.setString(4, dt.getRinPustika());
						prepStmt.setString(5, dt.getKhasraArea());
						prepStmt.setString(6, dt.getLagaan());
						prepStmt.setString(7, encDTO.getUserId());
						prepStmt.executeUpdate();
						prepStmt.close();
					}
				}
    		}
		}
			/*if("3".equals(encDTO.getPropertyName()))//Agriculture details
    		{
				prepStmt.setString(1, encDTO.getCerCopyTxnId());
				prepStmt.setString(2, encDTO.getPropDistrictId());
				prepStmt.setString(3, encDTO.getPropTehesilId());
				prepStmt.setString(4, encDTO.getPropPatwariName());
				prepStmt.setString(5, encDTO.getPropGramName());
				prepStmt.setString(6, encDTO.getPropertyName());
				prepStmt.setString(7, encDTO.getAgViKhand());
				prepStmt.setString(8, encDTO.getAgRicircle());
				prepStmt.setString(9, encDTO.getAgKhasraNo());
				prepStmt.setString(10, encDTO.getAgLayoutDtls());
				prepStmt.setString(11, encDTO.getAgPustikaNo());
				prepStmt.setString(12, encDTO.getAgEastboundryDtls());
				prepStmt.setString(13, encDTO.getAgWestboundryDtls());
				prepStmt.setString(14, encDTO.getAgNorthboundryDtls());
				prepStmt.setString(15, encDTO.getAgSouthboundryDtls());
				prepStmt.setString(16, encDTO.getAgArea());
				prepStmt.setString(17, encDTO.getRegNo());
				prepStmt.setString(18, encDTO.getIsSplit());
				prepStmt.setString(19, "");
				prepStmt.setString(20, "");
				prepStmt.setString(21, "");
				prepStmt.setString(22, "");
				prepStmt.setString(23, "");
				prepStmt.setString(24, encDTO.getAreaType());
				prepStmt.setString(25, encDTO.getMunicipalty());
				
    		}
			if("2".equals(encDTO.getPropertyName()))//Building details
    		{
				prepStmt.setString(1, encDTO.getCerCopyTxnId());
				prepStmt.setString(2, encDTO.getPropDistrictId());
				prepStmt.setString(3, encDTO.getPropTehesilId());
				prepStmt.setString(4, encDTO.getPropPatwariName());
				prepStmt.setString(5, encDTO.getPropGramName());
				prepStmt.setString(6, encDTO.getPropertyName());
				prepStmt.setString(7, encDTO.getBuildViKhand());
				prepStmt.setString(8, encDTO.getBuildRicircle());
				prepStmt.setString(9, encDTO.getBuildKhasraNo());
				prepStmt.setString(10, encDTO.getBuildLayout());
				prepStmt.setString(11, encDTO.getBuildPlotNo());
				prepStmt.setString(12, encDTO.getBuildEastboundryDtls());
				prepStmt.setString(13, encDTO.getBuildWestboundryDtls());
				prepStmt.setString(14, encDTO.getBuildNorthboundryDtls());
				prepStmt.setString(15, encDTO.getBuildSouthboundryDtls());
				prepStmt.setString(16, encDTO.getBuildArea());
				prepStmt.setString(17, encDTO.getRegNo());
				prepStmt.setString(18, encDTO.getIsSplit());
				prepStmt.setString(19, encDTO.getPlotArea());
				prepStmt.setString(20, encDTO.getNoOfShop());
				prepStmt.setString(21, encDTO.getBuildingType());
				prepStmt.setString(22, encDTO.getNoOfConstFloor());
				prepStmt.setString(23, encDTO.getBuildNazSheetNo());
				prepStmt.setString(24, encDTO.getAreaType());
				prepStmt.setString(25, encDTO.getMunicipalty());
    		}
			
			if("1".equals(encDTO.getPropertyName()))//Plot details
    		{
				prepStmt.setString(1, encDTO.getCerCopyTxnId());
				prepStmt.setString(2, encDTO.getPropDistrictId());
				prepStmt.setString(3, encDTO.getPropTehesilId());
				prepStmt.setString(4, encDTO.getPropPatwariName());
				prepStmt.setString(5, encDTO.getPropGramName());
				prepStmt.setString(6, encDTO.getPropertyName());
				prepStmt.setString(7, encDTO.getPltViKhand());
				prepStmt.setString(8, encDTO.getPltRicircle());
				prepStmt.setString(9, encDTO.getPltKhasraNo());
				prepStmt.setString(10, encDTO.getPltLayout());
				prepStmt.setString(11, encDTO.getPltPlotNo());
				prepStmt.setString(12, encDTO.getPltEastboundryDtls());
				prepStmt.setString(13, encDTO.getPltWestboundryDtls());
				prepStmt.setString(14, encDTO.getPltNorthboundryDtls());
				prepStmt.setString(15, encDTO.getPltSouthboundryDtls());
				prepStmt.setString(16, encDTO.getPltArea());
				prepStmt.setString(17, encDTO.getRegNo());
				prepStmt.setString(18, encDTO.getIsSplit());
				prepStmt.setString(19, "");
				prepStmt.setString(20, "");
				prepStmt.setString(21, "");
				prepStmt.setString(22, "");
				prepStmt.setString(23, encDTO.getPltNazSheetNo());
				prepStmt.setString(24, encDTO.getAreaType());
				prepStmt.setString(25, encDTO.getMunicipalty());
				
    		}
			rowCount = prepStmt.executeUpdate();		
			prepStmt.close();
			//End insert into IGRS_REG_INIT_PROPERTY_DETAILS
			
			//Start Add row insertion
			
			if("2".equals(encDTO.getPropertyName()))//Building details
    		{
				ArrayList<FloorDetailsDTO> familyMembers = encDTO.getFloordetails();
				String[] params;
				if (familyMembers != null) 
				{
					dbUtil.createPreparedStatement(NoEncumSQL.IGRS_NOENCUM_BUILD_FLOOR_DETAILS);
					IGRSCommon common=new IGRSCommon();
					for (FloorDetailsDTO member : familyMembers) {					
						String floorId=common.getSequenceNumber("DUMMY", "FLOOR");
						params = new String[] {
							floorId,
							encDTO.getCerCopyTxnId(),
							member.getFloorTypeId(),
							member.getBuildingSize(),
							member.getBuildingArea(),
						  };
						logger.debug("Inserting Employee Family Record");
						flag = dbUtil.executeUpdate(params);
						logger.debug("Employee Family Record flag = " + flag);
						if(flag == false) {
							break;
						}
					}
				}
    		}*/
			//End Add row insertion
			con.commit();
			//con.close();
			igrsCommon.saveLogDet("IGRS_CERTIFIED_COPY_TXN","INSERT","T",encDTO.getFunId(),encDTO.getUserId(),encDTO.getRoleId());
			flag=true;
			
            return flag;	
	     } 
        catch (Exception e)
        {
        	logger.info("Exception in insertlockProperty() :- " + e);
        	e.printStackTrace();
        	flag=false;
        	con.rollback();  
        	igrsCommon.saveLogDet("IGRS_CERTIFIED_COPY_TXN","INSERT","F",encDTO.getFunId(),encDTO.getUserId(),encDTO.getRoleId());
        } 
        finally
        {
            try
            {        	
            	dbUtil.releaseConnection(con);
            } catch (Exception ex)
            {
            	logger.info("Exception in insertlockProperty() :-" + ex);
            }
        }
	    return false;
	}
    
    
    /**
     * 
     * @param param
     * @return
     * @throws ServletException
     * @throws IOException
     * @throws SQLException
     * @throws Exception
     */
    public ArrayList getTotalDetailsDAO(String param[])throws ServletException,IOException,SQLException,Exception{
    	logger.debug("WE ARE IN DAO-estampCheckDAO");
        DBUtility dbUtil=null;
        ArrayList typeList=null;
        try {
            dbUtil=new DBUtility();
            String str=NoEncumSQL.IGRS_NOENCUM_VIEW_ENTIRE_DETAILS;
            dbUtil.createPreparedStatement(str);
            typeList=dbUtil.executeQuery(param);
            return typeList;
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
	    	logger.error("Connection is closed using FINALLY");
	        dbUtil.closeConnection(); 
	    }
	    return typeList;
	}
	// $09 End
    
    //property Master
    /**
     * 
     * @return
     * @throws ServletException
     * @throws IOException
     * @throws SQLException
     * @throws Exception
     */
    public ArrayList  propertyDAO(String languageLocale)throws ServletException,IOException,SQLException,Exception{
    	logger.debug("WE ARE IN DAO-casteStack");
        ArrayList  ar1=new ArrayList ();
        DBUtility dbUtil=null;
        try {
            ArrayList typeList=new ArrayList();
            ArrayList typeTemp=new ArrayList();
            dbUtil=new DBUtility();
            String str=NoEncumSQL.IGRS_PROPERTY_MASTER;
            if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_HINDI)){
            	str=NoEncumSQL.IGRS_PROPERTY_MASTER_HINDI;
            }
            dbUtil.createStatement();
            typeList=dbUtil.executeQuery(str);
            if(typeList!=null){
                for(int i=0;i<typeList.size();i++) {
                    typeTemp=new ArrayList();
                    typeTemp=(ArrayList)typeList.get(i);
                    if(typeTemp.size()>0){
                        NoEncumDTO type = new NoEncumDTO();
                        type.setPropertyId((String)typeTemp.get(0));
                        type.setPropertyName((String)typeTemp.get(1));
                        ar1.add(type);
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
        	logger.error("Connection is closed using FINALLY");
            dbUtil.closeConnection(); 
        }
        return ar1;
	}
    
    /**
     * 
     * @return
     * @throws ServletException
     * @throws IOException
     * @throws SQLException
     * @throws Exception
     */
    //Municipalty Master
    public ArrayList  getMunicipalList()throws ServletException,IOException,SQLException,Exception{
    	logger.debug("WE ARE IN DAO-casteStack");
        ArrayList  ar1=new ArrayList ();
        DBUtility dbUtil=null;
        try {
            ArrayList typeList=new ArrayList();
            ArrayList typeTemp=new ArrayList();
            dbUtil=new DBUtility();
            String str=NoEncumSQL.IGRS_MUNICIPALITY_MASTER;
            dbUtil.createStatement();
            typeList=dbUtil.executeQuery(str);
            if(typeList!=null){
                for(int i=0;i<typeList.size();i++) {
                    typeTemp=new ArrayList();
                    typeTemp=(ArrayList)typeList.get(i);
                    if(typeTemp.size()>0){
                        NoEncumDTO type = new NoEncumDTO();
                        type.setMunicipaltyId((String)typeTemp.get(0));
                        type.setMunicipaltyName((String)typeTemp.get(1));
                        ar1.add(type);
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
        	logger.error("Connection is closed using FINALLY");
            dbUtil.closeConnection(); 
        }
        return ar1;
	}
    
    /**
     * 
     * @return
     * @throws ServletException
     * @throws IOException
     * @throws SQLException
     * @throws Exception
     */
    public ArrayList  getAreaTypeList(String languageLocale)throws ServletException,IOException,SQLException,Exception{
    	logger.debug("WE ARE IN DAO-casteStack");
        ArrayList  ar1=new ArrayList ();
        DBUtility dbUtil=null;
        try {
            ArrayList typeList=new ArrayList();
            ArrayList typeTemp=new ArrayList();
            dbUtil=new DBUtility();
            String str=NoEncumSQL.IGRS_AREA_TYPE_MASTER;
            if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_HINDI)){
            	str=NoEncumSQL.IGRS_AREA_TYPE_MASTER_HINDI;
            }
            dbUtil.createStatement();
            typeList=dbUtil.executeQuery(str);
            if(typeList!=null){
                for(int i=0;i<typeList.size();i++) {
                    typeTemp=new ArrayList();
                    typeTemp=(ArrayList)typeList.get(i);
                    if(typeTemp.size()>0){
                        NoEncumDTO type = new NoEncumDTO();
                        type.setAreaTypeId((String)typeTemp.get(0));
                        type.setAreaTypeName((String)typeTemp.get(1));
                        ar1.add(type);
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
        	logger.error("Connection is closed using FINALLY");
            dbUtil.closeConnection(); 
        }
        return ar1;
	}
    
    /**
     * 
     * @param _countryIdVar
     * @return
     * @throws ServletException
     * @throws IOException
     * @throws SQLException
     * @throws Exception
     */
    public ArrayList  statePropDAO(String _countryIdVar)throws ServletException,IOException,SQLException,Exception{
        ArrayList  ar1=new ArrayList ();
        DBUtility dbUtil=null;
        logger.debug("WE ARE IN DAO-stateStack");
        try {
            ArrayList typeList=new ArrayList();
            ArrayList typeTemp=new ArrayList();
           
            dbUtil=new DBUtility();
            String str=NoEncumSQL.IGRS_STATE_MASTER; // Query for state list from IGRS_STATEMASTER
            String param[]=new String[1];
            param[0]=""+_countryIdVar;
            dbUtil.createPreparedStatement(str);
            typeList=dbUtil.executeQuery(param);
            logger.info("STATE_typeList:="+typeList);
            if(typeList!=null){
                for(int i=0;i<typeList.size();i++) {
                    typeTemp=new ArrayList();
                    typeTemp=(ArrayList)typeList.get(i);
                    if(typeTemp.size()>0){
                        NoEncumDTO type = new NoEncumDTO();
                       	type.setStatePropId((String)typeTemp.get(0));
                       	type.setStatePropName((String)typeTemp.get(1));
                        ar1.add(type);
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
        	logger.error("Connection is closed using FINALLY");
            dbUtil.closeConnection(); 
        }
        return ar1;
	}
  
    /**
     * 
     * @param _distId
     * @return
     * @throws ServletException
     * @throws IOException
     * @throws SQLException
     * @throws Exception
     */
 public ArrayList  tehesilPropDAO(String _distId,String languageLocale)throws ServletException,IOException,SQLException,Exception{
        
        ArrayList  ar1=new ArrayList ();
        DBUtility dbUtil=null;
        logger.debug("WE ARE IN DAO-districtStack");
        try {
            ArrayList typeList=new ArrayList();
            ArrayList typeTemp=new ArrayList();
           
            dbUtil=new DBUtility();
            String str=NoEncumSQL.IGRS_TEHESIL_MASTER; // Query for district list from IGRS_DISTRICT_MASTER
            if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_HINDI)){
            	str=NoEncumSQL.IGRS_TEHESIL_MASTER_HINDI;
            }
            String param[]=new String[1];
            param[0]=""+_distId;
            dbUtil.createPreparedStatement(str);
            typeList=dbUtil.executeQuery(param);
            logger.info("DISTRICT_typeList:="+typeList);
            if(typeList!=null){
                for(int i=0;i<typeList.size();i++) {
                    typeTemp=new ArrayList();
                    typeTemp=(ArrayList)typeList.get(i);
                    if(typeTemp.size()>0){
                        NoEncumDTO type = new NoEncumDTO();
                        type.setPropTehesilId((String)typeTemp.get(0));
                    	type.setPropTehesilName((String)typeTemp.get(1));
                        ar1.add(type);
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
            logger.error("DISTRICT Connection is closed using FINALLY");
            dbUtil.closeConnection(); 
        }
        return ar1;
	}
  
 /**
  * 
  * @param _stateIdVar
  * @return
  * @throws ServletException
  * @throws IOException
  * @throws SQLException
  * @throws Exception
  */
 public ArrayList  districtPropDAO(String _stateIdVar,String languageLocale)throws ServletException,IOException,SQLException,Exception{
     
     ArrayList  ar1=new ArrayList ();
     DBUtility dbUtil=null;
     logger.debug("WE ARE IN DAO-districtStack");
     try {
         ArrayList typeList=new ArrayList();
         ArrayList typeTemp=new ArrayList();
        
         dbUtil=new DBUtility();
         String str=NoEncumSQL.IGRS_DISTRICT_MASTER; // Query for district list from IGRS_DISTRICT_MASTER
         if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_HINDI)){
        	 str=NoEncumSQL.IGRS_DISTRICT_MASTER_HINDI;
         }
         String param[]=new String[1];
         param[0]=""+_stateIdVar;
         dbUtil.createPreparedStatement(str);
         typeList=dbUtil.executeQuery(param);
         logger.info("DISTRICT_typeList:="+typeList);
         if(typeList!=null){
             for(int i=0;i<typeList.size();i++) {
                 typeTemp=new ArrayList();
                 typeTemp=(ArrayList)typeList.get(i);
                 if(typeTemp.size()>0){
                     NoEncumDTO type = new NoEncumDTO();
                        type.setPropDistrictId((String)typeTemp.get(0));
                    	type.setPropDistrictName((String)typeTemp.get(1));                    	
                     ar1.add(type);
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
         logger.error("DISTRICT Connection is closed using FINALLY");
         dbUtil.closeConnection(); 
     }
     return ar1;
	}
 
 /**
  * 
  * @param _tehsilId
  * @return
  * @throws ServletException
  * @throws IOException
  * @throws SQLException
  * @throws Exception
  */
public ArrayList  patwariPropDAO(String _tehsilId)throws ServletException,IOException,SQLException,Exception{
     
     ArrayList  ar1=new ArrayList ();
     DBUtility dbUtil=null;
     logger.debug("WE ARE IN DAO-districtStack");
     try {
         ArrayList typeList=new ArrayList();
         ArrayList typeTemp=new ArrayList();
        
         dbUtil=new DBUtility();
         String str=NoEncumSQL.IGRS_PATWARI_MASTER; // Query for district list from IGRS_DISTRICT_MASTER
         String param[]=new String[1];
         param[0]=""+_tehsilId;
         dbUtil.createPreparedStatement(str);
         typeList=dbUtil.executeQuery(param);
         logger.info("DISTRICT_typeList:="+typeList);
         if(typeList!=null){
             for(int i=0;i<typeList.size();i++) {
                 typeTemp=new ArrayList();
                 typeTemp=(ArrayList)typeList.get(i);
                 if(typeTemp.size()>0){
                     NoEncumDTO type = new NoEncumDTO();
                     type.setPropPatwariId((String)typeTemp.get(0));
                 	type.setPropPatwariName((String)typeTemp.get(1));
                     ar1.add(type);
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
         logger.error("DISTRICT Connection is closed using FINALLY");
         dbUtil.closeConnection(); 
     }
     return ar1;
	}

/**
 * 
 * @param _patwariId
 * @return
 * @throws ServletException
 * @throws IOException
 * @throws SQLException
 * @throws Exception
 */
 
public ArrayList  gramPropDAO(String _patwariId)throws ServletException,IOException,SQLException,Exception{
    
    ArrayList  ar1=new ArrayList ();
    DBUtility dbUtil=null;
    logger.debug("WE ARE IN DAO-districtStack");
    try {
        ArrayList typeList=new ArrayList();
        ArrayList typeTemp=new ArrayList();
       
        dbUtil=new DBUtility();
        String str=NoEncumSQL.IGRS_NOENCUM_GRAM_DETAILS; // Query for district list from IGRS_DISTRICT_MASTER
        String param[]=new String[1];
        param[0]=""+_patwariId;
        dbUtil.createPreparedStatement(str);
        typeList=dbUtil.executeQuery(param);
        logger.info("DISTRICT_typeList:="+typeList);
        if(typeList!=null){
            for(int i=0;i<typeList.size();i++) {
                typeTemp=new ArrayList();
                typeTemp=(ArrayList)typeList.get(i);
                if(typeTemp.size()>0){
                    NoEncumDTO type = new NoEncumDTO();
                    type.setPropGramId((String)typeTemp.get(0));
                	type.setPropGramName((String)typeTemp.get(1));
                    ar1.add(type);
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
        logger.error("DISTRICT Connection is closed using FINALLY");
        dbUtil.closeConnection(); 
    }
    return ar1;
	}


/**
 * This method uses to get the function name,Module names fron DB
 * Method Name		:getFunctionName
 * @param funId 	:String[] 
 * @return			:ArrayList
 */
public ArrayList getFunctionName(String[] funId,String languageLocale) {
    ArrayList list = null;
    DBUtility dbUtil=null;
    try
    {
    	 dbUtil=new DBUtility();
    	 if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
         dbUtil.createPreparedStatement(CommonSQL.GET_FUNID_QUERY);
    	 }else{
    		 dbUtil.createPreparedStatement(CommonSQL.GET_FUNID_QUERY_HINDI);
    	 }
         logger.debug("SQL:"+CommonSQL.GET_FUNID_QUERY +"Fun id:- "+funId);
         list = dbUtil.executeQuery(funId);
    } catch (Exception x)
    {
        logger.debug("Exception in getlockProperty() :- " + x);
    }
    finally {
		try {
			dbUtil.closeConnection();
		} catch (Exception ex) {
			logger.debug("Exception in closing connection :-" + ex);
		}
	}
    return list;
} 


/**
* This method uses to update the certified copy details
* Method Name			:updateICopyInfo
* @param dto			:CertifiedCopyDetailsDTO
* @param pymntStatus	:String
* @return				:boolean
* @throws Exception
*/
public boolean updateICopyInfo(NoEncumDTO dto,String pymntStatus) throws Exception {
  boolean icopy = false;
  String sqlQuery = "";
  int rowCount;
  Connection con=null;
  DBUtility dbUtil=null;
  try
  {
	  dbUtil=new DBUtility("");
    	PreparedStatement prepStmt;        	
  	if (con == null || con.isClosed()) {
			con = dbUtil.getDBConnection();
		}
  	con.setAutoCommit(false);      	
  	sqlQuery = CommonSQL.ICOPY_PYMNT_UPDATE;
		prepStmt = con.prepareStatement(sqlQuery);
		prepStmt.setString(1, CommonConstant.ICOPY_PYMNT_SUCC_FLAG);
		prepStmt.setString(2, dto.getCerCopyTxnId());
		prepStmt.setString(3, pymntStatus);	
		prepStmt.executeUpdate();			
		prepStmt.close();			
		con.commit();
		prepStmt = con.prepareStatement(CommonSQL.TXN_PAYMENT_UPDATE);
		prepStmt.setString(1, CommonConstant.ICOPY_PYMNT_SUCC_FLAG);
		prepStmt.setString(2, dto.getCerCopyTxnId());
		prepStmt.executeUpdate();			
		prepStmt.close();			
		con.commit();
		icopy=true;
		
  } catch (Exception e)
  {
  	logger.info("Exception in insertlockProperty() :- " + e);
  	icopy=false;
  	e.printStackTrace();
  	con.rollback();          
      
  } 
  finally
  {
      try
      {            	
          dbUtil.releaseConnection(con);
      } catch (Exception ex)
      {
          logger.info("Exception in insertlockProperty() :-" + ex);
      }
  }
  return icopy;
}

/**
 * 
 * @param dto
 * @return
 * @throws Exception
 */
public boolean updatePaymentInfo(NoEncumDTO dto) throws Exception {
	  boolean icopy = false;
	  String sqlQuery = "";
	  int rowCount;
	  Connection con=null;
	  DBUtility dbUtil=null;
	  try
	  {
		  dbUtil=new DBUtility("");
	    	PreparedStatement prepStmt;        	
	  	if (con == null || con.isClosed()) {
				con = dbUtil.getDBConnection();
			}
	  	con.setAutoCommit(false);      	
	  	sqlQuery = CommonSQL.PYMNT_UPDATE;
			prepStmt = con.prepareStatement(sqlQuery);
			prepStmt.setString(1, CommonConstant.ICOPY_PYMNT_SUCC_FLAG);
			prepStmt.setString(2, dto.getCerCopyTxnId());			
			prepStmt.executeUpdate();			
			prepStmt.close();			
			con.commit();
			dbUtil.releaseConnection(con);
			icopy=true;
	  } catch (Exception e)
	  {
	  	logger.info("Exception in insertlockProperty() :- " + e);
	  	icopy=false;
	  	e.printStackTrace();
	  	con.rollback();          
	      
	  } 
	  finally
	  {
	      try
	      {            	
	          dbUtil.closeConnection();
	      } catch (Exception ex)
	      {
	          logger.info("Exception in insertlockProperty() :-" + ex);
	      }
	  }
	  return icopy;
	}

public String getTehsilName(String ofcId,String languageLocale) throws Exception {

    // int regTxnIdSeq = 0;
     String name="";
     String sql = "";
     DBUtility dbUtil=null;
     try {
    	 dbUtil = new DBUtility();
     	/*dbUtility.createStatement();
     	sql=RegCommonSQL.GET_DISTRICT_NAME+"'"+distId+"'";
     	distname = dbUtility.executeQry(sql);*/
     	
     	String[] param={ofcId};
     	if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
     	sql=CommonSQL.GET_TEHSIL_NAME;
     	}else{
     		sql=CommonSQL.GET_TEHSIL_NAME_HINDI;	
     	}
     	dbUtil.createPreparedStatement(sql);
     	name = dbUtil.executeQry(param);

     } catch (Exception exception) {

             System.out.println("Exception in getTehsilName-RegCommonDAO" + exception);

     }finally{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error("NoEncumDAO in dao start" + e.getStackTrace());
			}
		}

     return name;

}
public String getRegDate(String regNo) throws Exception {

    // int regTxnIdSeq = 0;
     String name="";
     String sql = "";
     DBUtility dbUtil=null;
     try {
    	 dbUtil = new DBUtility();
     	/*dbUtility.createStatement();
     	sql=RegCommonSQL.GET_DISTRICT_NAME+"'"+distId+"'";
     	distname = dbUtility.executeQry(sql);*/
     	
     	String[] param={regNo};
     	
     	sql=CommonSQL.GET_REG_DATE;
     	
     	dbUtil.createPreparedStatement(sql);
     	name = dbUtil.executeQry(param);

     } catch (Exception exception) {

             System.out.println("Exception in getRegDate-RegCommonDAO" + exception);

     }finally{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error("NoEncumDAO in dao start" + e.getStackTrace());
			}
		}

     return name;

}
public String getNEDate(String txnId) throws Exception {

    // int regTxnIdSeq = 0;
     String name="";
     String sql = "";
     DBUtility dbUtil=null;
     try {
    	 dbUtil = new DBUtility();
     	/*dbUtility.createStatement();
     	sql=RegCommonSQL.GET_DISTRICT_NAME+"'"+distId+"'";
     	distname = dbUtility.executeQry(sql);*/
     	
     	String[] param={txnId};
     	
     	sql=CommonSQL.GET_NE_DATE;
     	
     	dbUtil.createPreparedStatement(sql);
     	name = dbUtil.executeQry(param);

     } catch (Exception exception) {

             System.out.println("Exception in getNEDate-RegCommonDAO" + exception);

     }finally{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error("NoEncumDAO in dao start" + e.getStackTrace());
			}
		}

     return name;

}
/**
 * 
 * @return
 */
public ArrayList getFloorMasterList(String languageLocale) {
	ArrayList dataSet = null;
	try {
		DBUtility dbUtil = new DBUtility();
		try {
			dbUtil.createStatement();
			if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
			dataSet = dbUtil.executeQuery(CommonSQL.FLOOR_MASTER_QUERY);
			}else{
				dataSet = dbUtil.executeQuery(CommonSQL.FLOOR_MASTER_QUERY_HINDI);
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			dbUtil.closeConnection();
		}
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
	}
	return dataSet;
}

/**
 * 
 * @param referenceId
 * @return
 */
public ArrayList<FloorDetailsDTO> getFloorDetails(String referenceId) {
	ArrayList<FloorDetailsDTO> retVal = new ArrayList<FloorDetailsDTO>();
	ArrayList data, row;
	FloorDetailsDTO member;
	try {
		DBUtility dbUtil = new DBUtility();
		try {
			dbUtil.createPreparedStatement(CommonSQL.FLOORDTLS_VIEW_QUERY);
			data = dbUtil.executeQuery(new String[]{referenceId});
			if(data != null) {
				for (Object item : data) {
					row = (ArrayList) item;
					member = new FloorDetailsDTO();
					member.setFloorTypeId((String) row.get(0));
					member.setBuildingSize((String) row.get(1));
					member.setBuildingArea((String) row.get(2));					
					retVal.add(member);
					row.clear();
				}
				data.clear();
			}
			retVal.trimToSize();
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

public ArrayList getPendingNoEncumApps(String userId)
{
	ArrayList pendingAppList = new ArrayList();
	ArrayList pendingAppListFinal = new ArrayList();
	
	
	
	ArrayList list1 = new ArrayList();
	
	String[] param={userId};
	DBUtility dbUtil=null;
	try {
		dbUtil = new DBUtility();
		
				SQL = NoEncumSQL.GET_PENDING_APPLICATIONS_DETLS;
				//dbUtil.createPreparedStatement(SQL);
			
				dbUtil.createStatement();
				try
				{	
					
						pendingAppList=dbUtil.executeQuery(SQL+userId+"'");
			            logger.debug("Wipro in EstampDAO - getPendingEstampApps() after dbUtil.executeQuery(SQL)");
			           
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
public ArrayList isPaymentEntry(String certifiedId)
{
	ArrayList pendingAppList = new ArrayList();
	DBUtility dbUtil=null;
	try {
		dbUtil = new DBUtility();
		String SQL=CommonSQL.PRESENT_PAYMENT_ENTRY;
			//	SQL = CommonSQL.GET_PENDING_APPLICATIONS_DETLS_JUD;
			dbUtil.createPreparedStatement(SQL);
			//dbUtil.createPreparedStatement(SQL);
			dbUtil.createStatement();
			String param[]={certifiedId};
				
				try
				{	
				
					pendingAppList=dbUtil.executeQuery(param);
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
public String setPaymentDetails(NoEncumDTO dto,String funId,String userId)
{
	Connection con=null;
	String paymentId="";
	DBUtility dbUtil=null;
	try {
		dbUtil=new DBUtility();
		con = dbUtil.getDBConnection();
		PreparedStatement prepStmt;
		dbUtil.createStatement();
		paymentId = dbUtil.executeQry(CommonSQL.PAYMENT_SEQ_NEXT);
		String sqlQuery=CommonSQL.COPY_PAYMENT_DTLS;
		prepStmt = con.prepareStatement(sqlQuery);
		prepStmt.setString(1, paymentId);
		prepStmt.setString(2, dto.getTxnId());
		prepStmt.setString(3, funId);
		prepStmt.setString(4, dto.getBalanceAmount());
		prepStmt.setString(5,"I");
		prepStmt.setString(6, userId);
		prepStmt.executeUpdate();
		prepStmt.close();
		dbUtil.releaseConnection(con);
		
	
	} catch (Exception e) {
	}finally{
		try {
			dbUtil.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
return paymentId;
}
public ArrayList getPendingAppsinNoEncum(String userId)
{
	ArrayList pendingAppList = new ArrayList();
	DBUtility dbUtil=null;
	try {
		dbUtil = new DBUtility();
				SQL = NoEncumSQL.PENDING_NO_ENCUM_APP;
				dbUtil.createPreparedStatement(SQL);
			//dbUtil.createStatement();
				String param[]={userId};
				
				try
				{	
					
					//pendingAppList=dbUtil.executeQuery(param);
					pendingAppList=dbUtil.executeQuery(param);
			         //   logger.debug("-----> in EstampDAO - getPendingApps() for Judicial after dbUtil.executeQuery(SQL)");
			           
			            pendingAppList.trimToSize();
		
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
public ArrayList getPropertMappingList(String txnId)
{
	
	ArrayList pendingAppList = new ArrayList();
	DBUtility dbUtil=null;

	try {
		dbUtil = new DBUtility();
				SQL = NoEncumSQL.SELECT_PROPERTY;
				dbUtil.createPreparedStatement(SQL);
			//dbUtil.createStatement();
			String param[]={txnId};
				
				try
				{	
					//String query=NoEncumSQL.SELECT_PROPERTY;
							
					
					pendingAppList=dbUtil.executeQuery(param);
			         //   logger.debug("-----> in EstampDAO - getPendingApps() for Judicial after dbUtil.executeQuery(SQL)");
			           
			            pendingAppList.trimToSize();
		
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
public ArrayList getDetailsOnId(String txnId,String languageLocale)
{
	
	ArrayList pendingAppList = new ArrayList();
	DBUtility dbUtil=null;

	try {
		dbUtil = new DBUtility();
		if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
				SQL = NoEncumSQL.GET_DETAILS_ON_ID;
		}else{
			SQL = NoEncumSQL.GET_DETAILS_ON_ID_HINDI;
		}
				dbUtil.createPreparedStatement(SQL);
			//dbUtil.createStatement();
				String param[]={txnId};
				
				try
				{	
					//String query=
							pendingAppList=dbUtil.executeQuery(param);
			         //   logger.debug("-----> in EstampDAO - getPendingApps() for Judicial after dbUtil.executeQuery(SQL)");
			           
			            pendingAppList.trimToSize();
		
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

public ArrayList getPropertyDetails(String txnId)
{
	
	ArrayList pendingAppList = new ArrayList();
	DBUtility dbUtil=null;

	try {
		dbUtil = new DBUtility();
				SQL = NoEncumSQL.GET_ALL_PROPERTY_DETAILS;
				dbUtil.createPreparedStatement(SQL);
		//	dbUtil.createStatement();
				String param[]={txnId};
				
				try
				{	
					//String query=
							pendingAppList=dbUtil.executeQuery(param);
			         //   logger.debug("-----> in EstampDAO - getPendingApps() for Judicial after dbUtil.executeQuery(SQL)");
			           
			            pendingAppList.trimToSize();
		
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

public ArrayList getKhasraDetails(String txnId) {
	
	ArrayList pendingAppList = new ArrayList();
	DBUtility dbUtil=null;

	try {
		dbUtil = new DBUtility();
				SQL = NoEncumSQL.GET_KHASRA_DETAILS;
				dbUtil.createPreparedStatement(SQL);
			//dbUtil.createStatement();
				String param[]={txnId};
				
				try
				{	
				
							pendingAppList=dbUtil.executeQuery(param);
			         //   logger.debug("-----> in EstampDAO - getPendingApps() for Judicial after dbUtil.executeQuery(SQL)");
			           
			            pendingAppList.trimToSize();
		
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

public ArrayList getFloorDetail(String txnId) {
	ArrayList pendingAppList = new ArrayList();
	DBUtility dbUtil=null;

	try {
		dbUtil = new DBUtility();
		SQL = NoEncumSQL.GET_FLOOR_DETAILS;
				dbUtil.createPreparedStatement(SQL);
			//dbUtil.createStatement();
				String param[]={txnId};
				
				try
				{	
					
							pendingAppList=dbUtil.executeQuery(param);
			         //   logger.debug("-----> in EstampDAO - getPendingApps() for Judicial after dbUtil.executeQuery(SQL)");
			           
			            pendingAppList.trimToSize();
		
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
public String isCompletePayment(String certifiedId)
	{
	String balance="";
	DBUtility dbUtil=null;

	try {
		dbUtil = new DBUtility();
		String SQL=CommonSQL.PENDING_BALANCE_DETAILS;
			
			dbUtil.createPreparedStatement(SQL);
			
				
				try
				{	
				
					 balance=dbUtil.executeQry(new String[]{certifiedId});
					
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
	return balance;
	}
public String TotalPayment(String certifiedId)
{
String balance="";
DBUtility dbUtil=null;

try {
	dbUtil = new DBUtility();
	String SQL=NoEncumSQL.TOTAL_PAYMENT;
	dbUtil.createPreparedStatement(SQL);
	//dbUtil.createStatement();
		String param[]={certifiedId};
		dbUtil.createStatement();
		
			
			try
			{	
			
				 balance=dbUtil.executeQry(param);
				
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
return balance;
}

public boolean deleteDetails(String txnid) {
	
	
	DBUtility dbUtil=null;

	try {
		dbUtil = new DBUtility();
		//String SQL=CommonSQL.PENDING_BALANCE_DETAILS;
			
			dbUtil.createStatement();
			
				try
				{	
				
					dbUtil.executeUpdt(NoEncumSQL.DELTE_CERCOPY_PARTY_DETAILS+txnid+"'");
					dbUtil.executeUpdt(NoEncumSQL.DELTE_CERCOPY_TXN_DETAILS+txnid+"'");
					dbUtil.executeUpdt(NoEncumSQL.DELTE_CERCOPY_KHASRA_DETAILS+txnid+"'");
					dbUtil.executeUpdt(NoEncumSQL.DELTE_CERCOPY_FLOOR_DETAILS+txnid+"'");
					dbUtil.executeUpdt(NoEncumSQL.DELTE_CERCOPY_PROPERTY_DETAILS+txnid+"'");
					dbUtil.executeUpdt(NoEncumSQL.DELTE_CERCOPY_PROPERTY_MAP_DETAILS+txnid+"'");
	} catch (Exception x) {
		//logger.debug(x);
		x.printStackTrace();
	} finally {
		try {
			
			dbUtil.closeConnection();
			return true;
		} catch (Exception ex) {
		//	logger.debug(ex);
			ex.printStackTrace();
		}
	}

	}
	catch(Exception e){
		//logger.debug(e);
	}
	return true;
}
public String getPropCountryName(String id,String languageLocale)
{
	String list = null;
    DBUtility dbUtil=null;
    try
    {
    	 dbUtil=new DBUtility();
        String SQL=CommonSQL.COUNTRY_NAME;
        if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_HINDI)){
        	SQL=CommonSQL.COUNTRY_NAME_HINDI;
        }
    	  dbUtil.createPreparedStatement(SQL);
         //logger.debug("SQL:"+CommonSQL.GET_FUNID_QUERY +"Fun id:- "+funId);
         list = dbUtil.executeQry(new String[]{id});
    } catch (Exception x)
    {
        logger.debug("Exception in getlockProperty() :- " + x);
    }
    finally {
		try {
			dbUtil.closeConnection();
		} catch (Exception ex) {
			logger.debug("Exception in closing connection :-" + ex);
		}
	}
    return list;
}

public String getPropStateName(String id,String languageLocale)
{
	String list = null;
    DBUtility dbUtil=null;
    try
    {
    	dbUtil=new DBUtility();
        String SQL=CommonSQL.STATE_NAME;
        if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_HINDI)){
        	SQL=CommonSQL.STATE_NAME_HINDI;
        }
    	  dbUtil.createPreparedStatement(SQL);
         //logger.debug("SQL:"+CommonSQL.GET_FUNID_QUERY +"Fun id:- "+funId);
         list = dbUtil.executeQry(new String[]{id});
    } catch (Exception x)
    {
        logger.debug("Exception in getlockProperty() :- " + x);
    }
    finally {
		try {
			dbUtil.closeConnection();
		} catch (Exception ex) {
			logger.debug("Exception in closing connection :-" + ex);
		}
	}
    return list;	}
public String getPropDistrictName(String id,String languageLocale)
{
	String list = null;
    DBUtility dbUtil=null;
    try
    {
    	dbUtil=new DBUtility();
        String SQL=CommonSQL.DISTRICT_NAME;
        if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_HINDI)){
        	SQL=CommonSQL.DISTRICT_NAME_HINDI;
        }
    	  dbUtil.createPreparedStatement(SQL);
         //logger.debug("SQL:"+CommonSQL.GET_FUNID_QUERY +"Fun id:- "+funId);
         list = dbUtil.executeQry(new String[]{id});
    } catch (Exception x)
    {
        logger.debug("Exception in getlockProperty() :- " + x);
    }
    finally {
		try {
			dbUtil.closeConnection();
		} catch (Exception ex) {
			logger.debug("Exception in closing connection :-" + ex);
		}
	}
    return list;}
public String getPropTehsilName(String id,String languageLocale)
{
	String list = null;
    DBUtility dbUtil=null;
    try
    {
    	dbUtil=new DBUtility();
        String SQL=CommonSQL.TEHSIL_NAME;
        if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_HINDI)){
        	SQL=CommonSQL.TEHSIL_NAME_HINDI;
        }
    	  dbUtil.createPreparedStatement(SQL);
         //logger.debug("SQL:"+CommonSQL.GET_FUNID_QUERY +"Fun id:- "+funId);
         list = dbUtil.executeQry(new String[]{id});
    } catch (Exception x)
    {
        logger.debug("Exception in getlockProperty() :- " + x);
    }
    finally {
		try {
			dbUtil.closeConnection();
		} catch (Exception ex) {
			logger.debug("Exception in closing connection :-" + ex);
		}
	}
    return list;}
public String getPropPatwariName(String id,String languageLocale)
{
	String list = null;
    DBUtility dbUtil=null;
    try
    {
    	dbUtil=new DBUtility();
        String SQL=CommonSQL.WARD_PATWARI_NAME;
        if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_HINDI)){
        	SQL=CommonSQL.WARD_PATWARI_NAME_HINDI;
        }
    	  dbUtil.createPreparedStatement(SQL);
         //logger.debug("SQL:"+CommonSQL.GET_FUNID_QUERY +"Fun id:- "+funId);
         list = dbUtil.executeQry(new String[]{id});
    } catch (Exception x)
    {
        logger.debug("Exception in getlockProperty() :- " + x);
    }
    finally {
		try {
			dbUtil.closeConnection();
		} catch (Exception ex) {
			logger.debug("Exception in closing connection :-" + ex);
		}
	}
    return list;}
public String getPropGramName(String id,String languageLocale)
{
	String list = null;
    DBUtility dbUtil=null;
    try
    {
    	dbUtil=new DBUtility();
        //String SQL=CommonSQL.MOHALLA_VILLAGE_NAME;
    	String SQL=CommonSQL.GRAM_NAME;
    	if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_HINDI)){
    		SQL=CommonSQL.GRAM_NAME_HINDI;
    	}
    	  dbUtil.createPreparedStatement(SQL);
         //logger.debug("SQL:"+CommonSQL.GET_FUNID_QUERY +"Fun id:- "+funId);
         list = dbUtil.executeQry(new String[]{id});
    } catch (Exception x)
    {
        logger.debug("Exception in getlockProperty() :- " + x);
    }
    finally {
		try {
			dbUtil.closeConnection();
		} catch (Exception ex) {
			logger.debug("Exception in closing connection :-" + ex);
		}
	}
    return list;}
public String getPropMCDName(String id,String languageLocale)
{
	String list = null;
    DBUtility dbUtil=null;
    try
    {
    	dbUtil=new DBUtility();
       // String SQL=CommonSQL.MUNICIPAL_BODY_NAME;
    	 String SQL=CommonSQL.SUB_AREA_NAME;
    	 if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_HINDI)){
    		 SQL=CommonSQL.SUB_AREA_NAME_HINDI;
    	 }
    	  dbUtil.createPreparedStatement(SQL);
         //logger.debug("SQL:"+CommonSQL.GET_FUNID_QUERY +"Fun id:- "+funId);
         list = dbUtil.executeQry(new String[]{id});
    } catch (Exception x)
    {
        logger.debug("Exception in getlockProperty() :- " + x);
    }
    finally {
		try {
			dbUtil.closeConnection();
		} catch (Exception ex) {
			logger.debug("Exception in closing connection :-" + ex);
		}
	}
    return list;
    }
public ArrayList getPaymentParameter(String regno,String languageLocale) 
{
	 ArrayList list = null;
		DBUtility dbUtil=null;
   
	 try
	    {
	    	dbUtil=new DBUtility();
	        String SQL=NoEncumSQL.GET_PAYMENT_PARAMETER;
	        if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_HINDI)){
	        	SQL=NoEncumSQL.GET_PAYMENT_PARAMETER;
	        }
	    	  dbUtil.createPreparedStatement(SQL);
	         //logger.debug("SQL:"+CommonSQL.GET_FUNID_QUERY +"Fun id:- "+funId);
	         list = dbUtil.executeQuery(new String[]{regno});
	  
	  
	   return list;
	        //  log.debug("LIST="+list.size());
	    } catch (Exception x)
	    {
	     //   log.debug("Exception in getlockProperty() :- " + x);
	    }
	    finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
			//	log.debug("Exception in closing connection :-" + ex);
			}
		}
	   
	    return list;
}

public ArrayList isDRO(String officeId) {
	 ArrayList list = null;
		DBUtility dbUtil=null;
 
	 try
	    {
	    	dbUtil=new DBUtility();
	        String SQL=NoEncumSQL.CHECK_DRO;
	    	  dbUtil.createPreparedStatement(SQL);
	         //logger.debug("SQL:"+CommonSQL.GET_FUNID_QUERY +"Fun id:- "+funId);
	         list = dbUtil.executeQuery(new String[]{officeId});
	  
	  
	   return list;
	        //  log.debug("LIST="+list.size());
	    } catch (Exception x)
	    {
	     x.printStackTrace();
	    }
	    finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
			ex.printStackTrace();
				//	log.debug("Exception in closing connection :-" + ex);
			}
		}
	   
	    return list;
}

public String getofficeName(String officeId,String languageLocale) {
	 String list = null;
		DBUtility dbUtil=null;
  
	 try
	    {
	    	dbUtil=new DBUtility();
	        String SQL=CommonSQL.OFFICE_NAME;
	        if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_HINDI)){
	        	SQL=CommonSQL.OFFICE_NAME;
	        }
	    	  dbUtil.createPreparedStatement(SQL);
	         //logger.debug("SQL:"+CommonSQL.GET_FUNID_QUERY +"Fun id:- "+funId);
	         list = dbUtil.executeQry(new String[]{officeId});
	  
	   return list;
	        //  log.debug("LIST="+list.size());
	    } catch (Exception x)
	    {
	     x.printStackTrace();
	    }
	    finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
			ex.printStackTrace();
				//	log.debug("Exception in closing connection :-" + ex);
			}
		}
	   
	    return list;
}

public boolean UpdateDownloadStatus(NoEncumDTO dto)
{
	DBUtility dbUtil=null;

	try
	    {
	    	 dbUtil = new DBUtility();
	    //log.debug("before getting lock details ");
	    String SQL=NoEncumSQL.UPDATE_TRAN_STATUS;
	    dbUtil.createPreparedStatement(SQL);
	   // String sql="UPDATE IGRS_CERTIFIED_COPY_TXN SET TRANSACTION_STATUS='C',PURPOSE_DOWNLOAD='"+dto.getPurposeDownload()+"' where TRANSACTION_ID ='"+dto.getTxnId()+"'";
	    //log.debug("before getting lock details "+sql);
	  RegCommonBO bo = new RegCommonBO();
	    dto.setFromDate(bo.convertCalenderDate(dto.getFromDate()));
	  dto.setToDate(bo.convertCalenderDate(dto.getToDate()));
	    return dbUtil.executeUpdate(new String[]{dto.getPurposeDownload(),dto.getFromDate(),dto.getToDate(),dto.getTxnId()});
	        //  log.debug("LIST="+list.size());
	    } catch (Exception x)
	    {
	      //  log.debug("Exception in getlockProperty() :- " + x);
	    }
	    finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
			//	log.debug("Exception in closing connection :-" + ex);
			}
		}
	   
	    return false;
	
}

public String getRegTxn(String regno) {
	 String list = null;
		DBUtility dbUtil=null;
   
	 try
	    {
	    	 dbUtil = new DBUtility();
	   String query=NoEncumSQL.GET_REG_TXN_ID;
	    // log.debug("before getting lock details ");
	   // dbUtil.createPreparedStatement(CommonSQL.GET_COUNTRY);
	   // log.debug("SQL:"+CommonSQL.STATUS_QRY);
	    dbUtil.createPreparedStatement(query);
	    list = dbUtil.executeQry(new String[]{regno});
	  
	  
	   return list;
	        //  log.debug("LIST="+list.size());
	    } catch (Exception x)
	    {
	     x.printStackTrace();
	    }
	    finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
			ex.printStackTrace();
				//	log.debug("Exception in closing connection :-" + ex);
			}
		}
	   
	    return list;
}

public boolean updateIssuance(String[] param, String[] statusparam,
		String roleId, String funId, String userId, NoEncumDTO dto) {
	
	String disDate= statusparam[2];
	if("".equals(disDate))
    {
		disDate=null;
       
    }else{
    	disDate =getOracleDate(disDate);
    }
	statusparam[2]=disDate;
    boolean updateIssuance = false;
    IGRSCommon igrsCommon = null;
    try {
		igrsCommon = new IGRSCommon();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	DBUtility dbUtil=null;

	try
    {
    	dbUtil=new DBUtility();
    	//log.debug("before updating ");
        dbUtil.createPreparedStatement(CommonSQL.ISSUANCE_UPDATE_QUERY);
        updateIssuance = dbUtil.executeUpdate(param);
        if(updateIssuance)
        {
        	dbUtil.commit();
        	igrsCommon.saveLogDet("IGRS_CERCOPY_TXN_PARTY_DETAILS","UPDATE","T",funId,userId,roleId);
        }
        else{
        	dbUtil.rollback();
        	igrsCommon.saveLogDet("IGRS_CERCOPY_TXN_PARTY_DETAILS","UPDATE","F",funId,userId,roleId);
        }

        dbUtil.createPreparedStatement(CommonSQL.ISSUANCE_DELIVERY_QUERY);
        updateIssuance = dbUtil.executeUpdate(statusparam);
        if(updateIssuance)
        {
        	dbUtil.commit();
        	igrsCommon.saveLogDet("IGRS_CERTIFIED_COPY_TXN","UPDATE","T",funId,userId,roleId);
        }
        else{
        	dbUtil.rollback();
        	igrsCommon.saveLogDet("IGRS_CERTIFIED_COPY_TXN","UPDATE","F",funId,userId,roleId);
        }

      //  log.debug("after inserting ");
    } catch (Exception x)
    {
       x.printStackTrace();
    }
    finally {
		try {
			dbUtil.closeConnection();
		} catch (Exception ex) {
		ex.printStackTrace();
		}
	}
    return updateIssuance;
}
public static String getOracleDate(String DateFormat) {
	String finalDate  =  "";
		if(DateFormat!= null || !DateFormat.equalsIgnoreCase("") )
		{
    	StringTokenizer st = new StringTokenizer(DateFormat,"/");
        String day = st.nextToken();
        String month = st.nextToken();
        String year = st.nextToken();
		String inputDate  =  day + "-" + month + "-" + year;
		SimpleDateFormat formatter  =  new SimpleDateFormat("dd-MM-yyyy");			
		
		Date newDate ;
		try {
			newDate  =  formatter.parse(inputDate);
			SimpleDateFormat format  =  new SimpleDateFormat("dd/MMM/yyyy");
			finalDate  =  format.format(newDate);
			
		}
		catch (Exception e) {
			//log.debug(e);
		}
		
		
		}
		
		return  finalDate;
	}

public String getCollectionOffice(String regNo)
{
	 String list = null;
		DBUtility dbUtil=null;
  
	 try
	    {
	    	 dbUtil = new DBUtility();
	 	   String query=NoEncumSQL.GET_COLLECTION_OFFICE;
	 	    // log.debug("before getting lock details ");
	 	   // dbUtil.createPreparedStatement(CommonSQL.GET_COUNTRY);
	 	   // log.debug("SQL:"+CommonSQL.STATUS_QRY);
	 	    dbUtil.createPreparedStatement(query);
	 	    list = dbUtil.executeQry(new String[]{regNo});
	  
	   return list;
	        //  log.debug("LIST="+list.size());
	    } catch (Exception x)
	    {
	     x.printStackTrace();
	    }
	    finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
			ex.printStackTrace();
				//	log.debug("Exception in closing connection :-" + ex);
			}
		}
	   
	    return list;
}

public ArrayList getPdfDetails(String propId,String language) {

	 ArrayList list = null;
		DBUtility dbUtil=null;
  
	 try
	    {
	    	dbUtil=new DBUtility();
	        String SQL="";
	        if("en".equalsIgnoreCase(language))
	        {	
	        SQL=NoEncumSQL.GET_PROP_DETAILS;
	        }
	        else
	        {
	        	SQL=NoEncumSQL.GET_PROP_DETAILS_HI;
	        }
	        dbUtil.createPreparedStatement(SQL);
	         //logger.debug("SQL:"+CommonSQL.GET_FUNID_QUERY +"Fun id:- "+funId);
	         list = dbUtil.executeQuery(new String[]{propId,propId});
	  
	  
	   return list;
	        //  log.debug("LIST="+list.size());
	    } catch (Exception x)
	    {
	     x.printStackTrace();
	    }
	    finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
			ex.printStackTrace();
				//	log.debug("Exception in closing connection :-" + ex);
			}
		}
	   
	    return list;

}
public ArrayList valArea(String valId,String propId)
{
	ArrayList list=null;
	String SQL="";
	if(propId.equalsIgnoreCase("1"))
	{
		SQL=NoEncumSQL.GET_PLOT_AREA;
	}
	else if("2".equalsIgnoreCase(propId))
	{
		SQL=NoEncumSQL.GET_BUILD_AREA;
		
	}
	else
	{
		SQL=NoEncumSQL.GET_AGRI_AREA;
	}
	DBUtility dbUtil=null;

	try
    {
    	dbUtil=new DBUtility();
        dbUtil.createPreparedStatement(SQL);
         //logger.debug("SQL:"+CommonSQL.GET_FUNID_QUERY +"Fun id:- "+funId);
         list = dbUtil.executeQuery(new String[]{valId});
  
  
   return list;
        //  log.debug("LIST="+list.size());
    } catch (Exception x)
    {
     x.printStackTrace();
    }
    finally {
		try {
			dbUtil.closeConnection();
		} catch (Exception ex) {
		ex.printStackTrace();
			//	log.debug("Exception in closing connection :-" + ex);
		}
	}
   
    return list;
	
	
}

public String getPath(String userId) {
	String path="";
	
	DBUtility dbUtil=null;

	try
    {
		SQL=NoEncumSQL.GET_SIGN_PATH;
		
		String param []= new String[1];
		
		param[0]=userId;
		
    	dbUtil=new DBUtility();
        dbUtil.createPreparedStatement(SQL);
         //logger.debug("SQL:"+CommonSQL.GET_FUNID_QUERY +"Fun id:- "+funId);
        path = dbUtil.executeQry(param);
  
        
        path="D:/satbir/signature.gif";
  

        //  log.debug("LIST="+list.size());
    } catch (Exception x)
    {
     x.printStackTrace();
    }finally{
    	try {
			dbUtil.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    return path;
}

public ArrayList getAreaDetails(String propId, String language) {
	 ArrayList list = null;
		DBUtility dbUtil=null;
   
	 try
	    {
	    	dbUtil=new DBUtility();
	        String SQL="";
	     	
	        SQL=NoEncumSQL.GET_AREA_DETAILS;
	    
	        dbUtil.createPreparedStatement(SQL);
	         //logger.debug("SQL:"+CommonSQL.GET_FUNID_QUERY +"Fun id:- "+funId);
	         list = dbUtil.executeQuery(new String[]{propId});
	  
	  
	   return list;
	        //  log.debug("LIST="+list.size());
	    } catch (Exception x)
	    {
	     x.printStackTrace();
	    }
	    finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
			ex.printStackTrace();
				//	log.debug("Exception in closing connection :-" + ex);
			}
		}
	   
	    return list;
}

}

