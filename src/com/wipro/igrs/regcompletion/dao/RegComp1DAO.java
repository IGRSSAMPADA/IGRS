package com.wipro.igrs.regcompletion.dao;


import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;
import org.apache.struts.upload.FormFile;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.regcompletion.dto.Common1DTO;
import com.wipro.igrs.regcompletion.dto.CommonDTO;
import com.wipro.igrs.regcompletion.sql.RegCommonSQL;

public class RegComp1DAO {
    public RegComp1DAO() {}
    
    private  Logger logger = 
		(Logger) Logger.getLogger(RegComp1DAO.class);
    //$01 Method for Retrieving Country from COUNTRY_MASTER
    public ArrayList countryStackDAO() throws ServletException,IOException, Exception {
    
    	ArrayList ar1=new ArrayList();
        DBUtility dbUtil=null;
        logger.debug("WE ARE IN DAO-countryStack");
        try {
            ArrayList typeList=new ArrayList();
            ArrayList typeTemp=new ArrayList();
           
            dbUtil=new DBUtility();
            String str=RegCommonSQL.IGRS_COUNTRY_MASTER; // Query for Country list from IGRS_COUNTRY_MASTER
            dbUtil.createStatement();
            typeList=dbUtil.executeQuery(str);
            logger.info("COUNTRY_typeList:="+typeList);
            if(typeList!=null){
                for(int i=0;i<typeList.size();i++) {
                    typeTemp=new ArrayList();
                    typeTemp=(ArrayList)typeList.get(i);
                    if(typeTemp.size()>0){
                        Common1DTO type = new Common1DTO();
                        type.setCountryId((String)typeTemp.get(0));
                        type.setCountryName((String)typeTemp.get(1));
                        ar1.add(type);
                    }
                }
            }
        }catch(Exception e){
            logger.error(e);
        }
        finally {
    		logger.error("using FINALLY Connection is closed");
            dbUtil.closeConnection(); 
        }
        return ar1;
    }
    //$01 End
    //$02 Method for Retrieving State from STATE_MASTER
    public ArrayList stateStackDAO(String _countryIdVar)throws Exception{
        ArrayList ar1=new ArrayList();
        DBUtility dbUtil=null;
        logger.debug("WE ARE IN DAO-stateStack");
        try {
            ArrayList typeList=new ArrayList();
            ArrayList typeTemp=new ArrayList();
           
            dbUtil=new DBUtility();
            String str=RegCommonSQL.IGRS_STATE_MASTER; // Query for state list from IGRS_STATEMASTER
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
                        Common1DTO type = new Common1DTO();
                        type.setStateId((String)typeTemp.get(0));
                        type.setStateName((String)typeTemp.get(1));
                        ar1.add(type);
                    }
                }
            }
        }catch(Exception e){
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
    public ArrayList districtStackDAO(String _stateIdVar)throws Exception{
        
        ArrayList ar1=new ArrayList();
        DBUtility dbUtil=null;
        logger.debug("WE ARE IN DAO-districtStack");
        try {
            ArrayList typeList=new ArrayList();
            ArrayList typeTemp=new ArrayList();
           
            dbUtil=new DBUtility();
            String str=RegCommonSQL.IGRS_DISTRICT_MASTER; // Query for district list from IGRS_DISTRICT_MASTER
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
                        Common1DTO type = new Common1DTO();
                        type.setDistrictId((String)typeTemp.get(0));
                        type.setDistrictName((String)typeTemp.get(1));
                        ar1.add(type);
                    }
                }
            }
        }catch(Exception e){
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
    public ArrayList photoIdStackDAO()throws Exception{
        
        ArrayList ar1=new ArrayList();
        DBUtility dbUtil=null;
        logger.debug("WE ARE IN DAO-photoIdStack");
        try {
            ArrayList typeList=new ArrayList();
            ArrayList typeTemp=new ArrayList();
            dbUtil=new DBUtility();
            String str=RegCommonSQL.IGRS_PHOTOID_PROOF_MASTER; // Query for Photo Id Proof Type list from IGRS_PHOTOID_PROOF_TYPE_MASTER
            dbUtil.createStatement();
            typeList=dbUtil.executeQuery(str);
            if(typeList!=null){
                for(int i=0;i<typeList.size();i++) {
                    typeTemp=new ArrayList();
                    typeTemp=(ArrayList)typeList.get(i);
                    if(typeTemp.size()>0){
                        Common1DTO type = new Common1DTO();
                        type.setPhotoProofTypeId((String)typeTemp.get(0));
                        type.setPhotoProofTypeName((String)typeTemp.get(1));
                        ar1.add(type);
                    }
                }
            }
        }catch(Exception e){
        	logger.error(e);
        }
        finally {
        	logger.error("Connection is closed using FINALLY");
            dbUtil.closeConnection(); 
        }
        return ar1;
	}
	// $04 End
    //$05 Method for Inserting Party Details in PARTY_TXN_DETAILS
    public boolean addPartyDAO(String []param, FormFile filePhoto,
	     FormFile fileSign, FormFile fileThumb) throws Exception
   {
    	logger.debug("WE ARE IN DAO-addPartyDao");
    	DBUtility dbUtil=null;
    	boolean bol=false;
    	try {
    		ArrayList typeList=new ArrayList();
    		ArrayList typeTemp=new ArrayList();
    		dbUtil=new DBUtility();
    		String str=RegCommonSQL.IGRS_REGCOMP_INSERT_PARTY; 
    		dbUtil.createPreparedStatement(str);
    		 bol = dbUtil.executeUpdate(param);
    		String SQL = 
    		    "UPDATE IGRS_REG_TXN_PARTY_DETAILS "
    		    +"SET PARTY_PHOTO = ?,PARTY_SIGNATURE = ?, "
    		    +"PARTY_THUMB_IMPRESSION =? WHERE REG_TXN_ID =?";
    		
    		PreparedStatement pst 
    			=  dbUtil.returnPreparedStatement(SQL);
    		
    		if(bol) {
    		logger.debug("after inserting DAO-addPartyDao");
    		    //Blob bPhoto = new BLOB();
    		    pst.setBinaryStream(1,filePhoto.getInputStream(),
    			    filePhoto.getInputStream().available());
    		    pst.setBinaryStream(2,fileSign.getInputStream(),
    			fileSign.getInputStream().available());
		    
    		    pst.setBinaryStream(3,fileThumb.getInputStream(),
    			fileThumb.getInputStream().available());
    		    pst.setString(4, param[27]);
    		    int i = pst.executeUpdate();
    		    //pst.setBlob(i, x)
    		    logger.debug("updated record:-"+i);
    		    /*if(i>0) {
    			bol = true;
    		    }*/
    		}
    		
    		if(bol) {
    		    dbUtil.commit();
    		}else {
    		    dbUtil.rollback();
    		}   		 
    		
    		return bol;
    	}catch(Exception e)
    	{
    		logger.error("Exception in RegComp1DAO --- addPartyDAO() "+ e);
    		dbUtil.rollback();
    	}
    	finally 
    	{
    		logger.error("Connection is closed using FINALLY");
    		dbUtil.closeConnection(); 
    	}
    	return bol;
	}
    //$05 End
    //$06 Method for Inserting Organization Details in PARTY_TXN_DETAILS
    public boolean addOrgaDAO(String []param) throws Exception
    {
    	logger.debug("WE ARE IN DAO-addOrgaDao");
    	DBUtility dbUtil=null;
    	boolean flag=false;
    	try {
    		ArrayList typeList=new ArrayList();
    		ArrayList typeTemp=new ArrayList();
    		dbUtil=new DBUtility();
    		String str=RegCommonSQL.IGRS_REGCOMP_INSERT_ORGA_DETAILS; 
    		dbUtil.createPreparedStatement(str);
    		flag=dbUtil.executeUpdate(param);
    		return flag;
    	}catch(Exception e)
    	{
    		logger.error(e);
    		return false;
    	}
    	finally 
    	{
    		logger.error("Connection is closed using FINALLY");
    		dbUtil.closeConnection(); 
    	}
	}
    //$06 End
    //$07 Method for Retrieving Caste from CASTE_MASTER
    public ArrayList casteStackDAO()throws Exception{
    	logger.debug("WE ARE IN DAO-casteStack");
        ArrayList ar1=new ArrayList();
        DBUtility dbUtil=null;
        try {
            ArrayList typeList=new ArrayList();
            ArrayList typeTemp=new ArrayList();
            dbUtil=new DBUtility();
            String str=RegCommonSQL.IGRS_CASTE_MASTER;
            dbUtil.createStatement();
            typeList=dbUtil.executeQuery(str);
            if(typeList!=null){
                for(int i=0;i<typeList.size();i++) {
                    typeTemp=new ArrayList();
                    typeTemp=(ArrayList)typeList.get(i);
                    if(typeTemp.size()>0){
                        Common1DTO type = new Common1DTO();
                        type.setCasteId((String)typeTemp.get(0));
                        type.setCasteName((String)typeTemp.get(1));
                        ar1.add(type);
                    }
                }
            }
        }catch(Exception e){
        	logger.error(e);
        }
        finally {
        	logger.error("Connection is closed using FINALLY");
            dbUtil.closeConnection(); 
        }
        return ar1;
	}
	// $07 End
    //$08 Method for Retrieving RELIGION from RELIGION_MASTER
    public ArrayList religionStackDAO()throws Exception{
    	logger.debug("WE ARE IN DAO-religionStack");
        ArrayList ar1=new ArrayList();
        DBUtility dbUtil=null;
        try {
            ArrayList typeList=new ArrayList();
            ArrayList typeTemp=new ArrayList();
            dbUtil=new DBUtility();
            String str=RegCommonSQL.IGRS_RELIGION_MASTER;
            dbUtil.createStatement();
            typeList=dbUtil.executeQuery(str);
            if(typeList!=null){
                for(int i=0;i<typeList.size();i++) {
                    typeTemp=new ArrayList();
                    typeTemp=(ArrayList)typeList.get(i);
                    if(typeTemp.size()>0){
                        Common1DTO type = new Common1DTO();
                        type.setReligionId((String)typeTemp.get(0));
                        type.setReligionName((String)typeTemp.get(1));
                        ar1.add(type);
                    }
                }
            }
	    }catch(Exception e){
	    	logger.error(e);
	    }
	    finally {
	    	logger.error("Connection is closed using FINALLY");
	        dbUtil.closeConnection(); 
	    }
	    return ar1;
	}
	// $08 End
    //$09 Method for retrieving recordS from PARTY_TXN
    public ArrayList selectPartiesDAO(String[] _appNo)
    {
    	logger.debug("WE ARE IN DAO-selectPartiesDAO");
        ArrayList list=new ArrayList();
        CommonDTO cDTO=new CommonDTO();
        list=null;
        ArrayList list2=new ArrayList();
        
        String errMessages=null;
        try{
	        DBUtility dbUtil = new DBUtility();
        	//String param[]=new String[1];
        	//param[0]=""+_appNo;
            String query=RegCommonSQL.IGRS_REGCOMP_SELECT_PARTY_LIST;
            dbUtil.createPreparedStatement(query);
            list = dbUtil.executeQuery(_appNo);
            errMessages="Not Found";
	        if(list.isEmpty())
	        {
	        	cDTO.setErroMsg(errMessages);
	        	list2.add(cDTO);
	        }
	        else
	        {
	        	try{
	        		 
	        		if(list!=null){
	        			for(int i=0;i<list.size();i++) {
		                   
		                    ArrayList typeTemp=(ArrayList)list.get(i);
		                    if(typeTemp!=null && typeTemp.size()>0){
		                        CommonDTO type = new CommonDTO();
		                        type.setPartyId((String)typeTemp.get(0));
		                        type.setFname((String)typeTemp.get(1));
		                        type.setOrganizationName((String)typeTemp.get(2));
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
		    		logger.error("Connection is closed using FINALLY");
		            dbUtil.closeConnection(); 
		        }
	        }
	        return (list2);
    	}catch(Exception e) {
    		logger.error(e);
    		return(list2);
    	}
    }
  //$09 Method for retrieving recordS from PARTY_TXN
    public ArrayList selectPartiesDAO(String  _appNo)
    {
    	logger.debug("WE ARE IN DAO-selectPartiesDAO");
        ArrayList list=new ArrayList();
        CommonDTO cDTO=new CommonDTO();
        list=null;
        ArrayList list2=new ArrayList();
        
        String errMessages=null;
        try{
	        DBUtility dbUtil = new DBUtility();
        	String param[]=new String[1];
        	param[0]=_appNo;
            String query=RegCommonSQL.IGRS_REGCOMP_SELECT_PARTY_DETAILS;
            dbUtil.createPreparedStatement(query);
            list = dbUtil.executeQuery(param);
            errMessages="Not Found";
	        if(list.isEmpty())
	        {
	        	cDTO.setErroMsg(errMessages);
	        	// Edited By aruna
	            // list2.add(cDTO);
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
	                        CommonDTO type = new CommonDTO();
	                        type.setPartyId((String)typeTemp.get(0));
	                        type.setFname((String)typeTemp.get(1));
	                        type.setOrganizationName((String)typeTemp.get(2));
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
		    		logger.error("Connection is closed using FINALLY");
		            dbUtil.closeConnection(); 
		        }
	        }
	        return (list2);
    	}catch(Exception e) {
    		logger.error(e);
    		return(list2);
    	}
    }
    //$09 End
    //$10 Method for Viewing party.
    public ArrayList viewPartyDAO(String _partyId) throws Exception
    {
    	logger.debug("WE ARE IN DAO-viewPartyDAO");
    	DBUtility dbUtil=new DBUtility();
    	ArrayList partyData=new ArrayList();
    	try{
    		String qry=RegCommonSQL.IGRS_REGCOMP_SELECT_VIEW_PARTY_DETAILS;
    		dbUtil.createPreparedStatement(qry);
    		String []param=new String[1];
    		param[0]=""+_partyId;
    		partyData=dbUtil.executeQuery(param);
    		return partyData;
    	}catch (Exception e) {
    		logger.error(e);
			return null;
		}
    	finally{
    		logger.error("Connection is closed using FINALLY");
            dbUtil.closeConnection(); 
    	}
    }
    //$10 End
    //$11 Method for deleting Party from TXN_PARTY
    public boolean deletePartyDAO(String _partyId) throws Exception
    {
    	logger.debug("WE ARE IN DAO-deletePartyDAO");
    	DBUtility dbUtil=new DBUtility();
    	try{
    		String qry=RegCommonSQL.IGRS_REGCOMP_SELECT_DELETE_PARTY_DETAILS;
    		dbUtil.createPreparedStatement(qry);
    		String []param=new String[1];
    		param[0]=""+_partyId;
    		boolean result=dbUtil.executeUpdate(param);
    		return result;
    	}catch (Exception e) {
    		logger.error(e);
			return false;
		}
    	finally{
    		logger.error("Connection is closed using FINALLY");
                 dbUtil.closeConnection(); 
    	}
    }
    //$11 End
    //$12 Method for Retrieving  party for modification.
    public ArrayList retrievePartyDAO(String _partyId) throws Exception
    {
    	logger.debug("WE ARE IN DAO-retrievePartyDAO");
    	DBUtility dbUtil=new DBUtility();
    	ArrayList partyData=new ArrayList();
    	try{
    		String qry=RegCommonSQL.IGRS_REGCOMP_RETRIEVE_PARTY_FOR_MODIFY_DETAILS;
    		dbUtil.createPreparedStatement(qry);
    		String []param=new String[1];
    		param[0]=""+_partyId;
    		partyData=dbUtil.executeQuery(param);
    		return partyData;
    	}catch (Exception e) {
			logger.error(e);
			return null;
		}
    	finally{
    		logger.error("Connection is closed using FINALLY");
            dbUtil.closeConnection(); 
    	}
    }
    //$12 End
    //$13 Method for Retrieving  party for modification.
    public boolean updatePartyDAO(String _partyType, String param[]) throws Exception
    {
    	logger.debug("WE ARE IN DAO-updatePartyDAO");
    	DBUtility dbUtil=new DBUtility();
    	String qry="";
    	try{
    		// Edited By Aruna
    		if(_partyType.equalsIgnoreCase("02"))
    		//if(_partyType.equalsIgnoreCase("Individuals"))
    		{
    			 qry=RegCommonSQL.IGRS_REGCOMP_UPDATE_PARTY;
    		}
    		else
    		{
    			qry=RegCommonSQL.IGRS_REGCOMP_UPDATE_ORGA;
    		}
    		dbUtil.createPreparedStatement(qry);
    		boolean result=dbUtil.executeUpdate(param);
    		return result;
    	}catch (Exception e) {
    		logger.error(e);
			return false;
		}
    	finally{
    		logger.error("Connection is closed using FINALLY");
            dbUtil.closeConnection(); 
    	}
    } //$13 End
    
}
