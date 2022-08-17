package com.wipro.igrs.suppleDoc.dao;
import java.io.IOException;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;
import org.eclipse.jdt.internal.compiler.flow.FinallyFlowContext;

import com.wipro.igrs.common.CommonSQL;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.newPropvaluation.sql.PropertyValuationSQL;
import com.wipro.igrs.reginit.constant.RegInitConstant;
import com.wipro.igrs.reginit.dto.RegCompletDTO;
import com.wipro.igrs.reginit.sql.RegCommonSQL;
import com.wipro.igrs.suppleDoc.dto.SuppleDocDTO;
import com.wipro.igrs.suppleDoc.sql.SuppleDocSQL;

public class SuppleDocDAO {
	
	private Logger logger = 
		(Logger) Logger.getLogger(SuppleDocDAO.class);
    public SuppleDocDAO() {}
    
    //$01 Method for Retrieving Country from COUNTRY_MASTER
    public ArrayList  countryStackDAO(String _axn, String language) throws ServletException,IOException,SQLException,Exception {
    
    	
    	
    	ArrayList  ar1=new ArrayList ();
        DBUtility dbUtil=null;
        logger.debug("WE ARE IN DAO-countryStack");
        try {
            ArrayList typeList=new ArrayList();
            ArrayList typeTemp=new ArrayList();
           String str ="";
            dbUtil=new DBUtility();
            if(_axn.equalsIgnoreCase("BankCountry"))
            {
            	if(language.equalsIgnoreCase("en"))
            	{
            		str=SuppleDocSQL.IGRS_COUNTRY_MASTER;// Query for Country list from IGRS_COUNTRY_MASTER
            	}
            	else
            	{
            		str=SuppleDocSQL.IGRS_COUNTRY_MASTER_HINDI;
            	}
            }
            else
            {
            	if(language.equalsIgnoreCase("en"))
            	{
            	 str=SuppleDocSQL.IGRS_COUNTRY_MASTER_INDIA;
            	}
            	else
            	{
            		str = SuppleDocSQL.IGRS_COUNTRY_MASTER_HINDI_INDIA;
            	}
            }
            dbUtil.createStatement();
            typeList=dbUtil.executeQuery(str);
            logger.info("COUNTRY_typeList:="+typeList);
            if(typeList!=null){
                for(int i=0;i<typeList.size();i++) {
                    typeTemp=new ArrayList();
                    typeTemp=(ArrayList)typeList.get(i);
                    if(typeTemp.size()>0){
                        SuppleDocDTO type = new SuppleDocDTO();
                        if(_axn.equalsIgnoreCase("BankCountryType"))
                        {
                        	type.setBankCountryId((String)typeTemp.get(0));
                            type.setBankCountryName((String)typeTemp.get(1));
                        }
                        else
                        {
                        	type.setCountryId((String)typeTemp.get(0));
                        	type.setCountryName((String)typeTemp.get(1));
                        }
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
    
    public String getSpotMaxDay()  {
    	DBUtility dbUtil=null;
		String getSpotMaxDay = "";
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			getSpotMaxDay=	dbUtil.executeQry( "SELECT  ATTRIBUTE_VALUE  FROM IGRS_CONFIG_PARAM_LIST WHERE ATTRIBUTE_ID = 'ATT_166'");
		}catch (Exception exception) {
			logger.info("Exception in getMaxdats in Supplementary Documents" + exception);
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
		return getSpotMaxDay;
	}
    
    public ArrayList getColonyName(String language, String wardPatwariId) {
		ArrayList l2NameList=null;
		DBUtility dbUtil = null;
		System.out.println("Ward Id "+wardPatwariId);
		try {
			dbUtil = new DBUtility();
			if(language.equalsIgnoreCase("en"))
			{	
				dbUtil.createPreparedStatement(SuppleDocSQL.COLONY_NAME_EN);
			}
			else
			{
				dbUtil.createPreparedStatement(SuppleDocSQL.COLONY_NAME_HI);	
			}
			l2NameList=dbUtil.executeQuery(new String[]{wardPatwariId.split("~")[1]});
			return l2NameList;
			
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
			return null;
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
    
    
    
    public ArrayList getSubArea(String language, String areaId, String tehsilId, String urbanFlag) {
		ArrayList subAreaList=null;
		 DBUtility dbUtil=null;
	try {
		dbUtil = new DBUtility();
		if(language.equalsIgnoreCase("en"))
		{	
			if("Y".equalsIgnoreCase(urbanFlag))
			{	
			dbUtil.createPreparedStatement(SuppleDocSQL.SUB_AREA_NAME_EN_UR);
			subAreaList=dbUtil.executeQuery(new String[]{areaId,tehsilId});

			}
			else
			{
				dbUtil.createPreparedStatement(SuppleDocSQL.SUB_AREA_NAME_EN);	
				subAreaList=dbUtil.executeQuery(new String[]{areaId});
			}
		}
		else
		{
			if("Y".equalsIgnoreCase(urbanFlag))
			{	
			dbUtil.createPreparedStatement(SuppleDocSQL.SUB_AREA_NAME_HI_UR);
			subAreaList=dbUtil.executeQuery(new String[]{areaId,tehsilId});
			}
			else
			{
				dbUtil.createPreparedStatement(SuppleDocSQL.SUB_AREA_NAME_HI);	
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
		try {
			dbUtil.closeConnection();
		} catch (Exception e) {
			
			logger.debug(e.getMessage(),e);
		}
	}
	
}
    
    
    //$02 Method for Retrieving Document List from DOCUMENT_MASTER
    public ArrayList documentListDAO(String langauge) throws ServletException,IOException,SQLException,Exception {
        
    	ArrayList  ar1=new ArrayList ();
        DBUtility dbUtil=null;
        logger.debug("WE ARE IN DAO-documentListDAO()");
        try {
            ArrayList typeList=new ArrayList();
            ArrayList typeTemp=new ArrayList();
            String str = "";
            if(langauge.equalsIgnoreCase("en"))
            {
            	str =SuppleDocSQL.IGRS_SUPPLI_VIEW_DOCUMENT_LIST;
            } 
            else
            {
            	str =SuppleDocSQL.IGRS_SUPPLI_VIEW_DOCUMENT_LIST_HINDI;
            }
           dbUtil=new DBUtility();
           
           
            dbUtil.createStatement();
            typeList=dbUtil.executeQuery(str);
            logger.info("Document_typeList:="+typeList);
            if(typeList!=null){
                for(int i=0;i<typeList.size();i++) {
                    typeTemp=new ArrayList();
                    typeTemp=(ArrayList)typeList.get(i);
                    if(typeTemp.size()>0){
                        SuppleDocDTO type = new SuppleDocDTO();
                       
                        
                        	type.setDocumentId((String)typeTemp.get(0));
                            type.setDocumentName((String)typeTemp.get(1));
                        
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
    
    
    //$03 Method for Purpose List from Purpose_MASTER
 public ArrayList purposeListDAO(String id, String language) throws ServletException,IOException,SQLException,Exception {
        
    	ArrayList  ar1=new ArrayList ();
        DBUtility dbUtil=null;
        logger.debug("WE ARE IN DAO-purposeListDAO()");
        try {
            ArrayList typeList=new ArrayList();
            ArrayList typeTemp=new ArrayList();
            String str = "";
            if(language.equalsIgnoreCase("en"))
            {
            	str =SuppleDocSQL.IGRS_SUPPLI_VIEW_PURPOSE_LIST;
            }
            else
            {
            	str =SuppleDocSQL.IGRS_SUPPLI_VIEW_PURPOSE_LIST_HINDI;
            }
          
            dbUtil=new DBUtility();
           
           
            dbUtil.createStatement();
            typeList=dbUtil.executeQuery(str);
            logger.info("Document_typeList:="+typeList);
            if(typeList!=null){
                for(int i=0;i<typeList.size();i++) {
                    typeTemp=new ArrayList();
                    typeTemp=(ArrayList)typeList.get(i);
                    if(typeTemp.size()>0){
                        SuppleDocDTO type = new SuppleDocDTO();
                       
                        
                        	type.setPurposeId((String)typeTemp.get(0));
                            type.setPurposeName((String)typeTemp.get(1));
                        
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
        if(id.equalsIgnoreCase("1"))
        {
        	ArrayList temp1 = new ArrayList();
        	List temp11 = ar1.subList(0, ar1.size()/2);
        	temp1.addAll(temp11);
			
        return temp1;
        }
        else
        {
        	ArrayList temp1 = new ArrayList();
        	List temp11 = ar1.subList(ar1.size()/2, ar1.size());
        	temp1.addAll(temp11);
			
        
        	 return temp1;
        }
    }
    
   
    //$04 Method for Retrieving State from STATE_MASTER
    public ArrayList  stateStackDAO(String _countryIdVar,String _axn, String language)throws ServletException,IOException,SQLException,Exception{
        ArrayList  ar1=new ArrayList ();
        DBUtility dbUtil=null;
        logger.debug("WE ARE IN DAO-stateStack");
        try {
            ArrayList typeList=new ArrayList();
            ArrayList typeTemp=new ArrayList();
           
            dbUtil=new DBUtility();
            String str=""; // Query for state list from IGRS_STATEMASTER
            String param[]=new String[1];
            if(_axn.equalsIgnoreCase("BankState"))
            {
            	
            	param[0]=""+_countryIdVar;
            	if(language.equalsIgnoreCase("en"))
            	{
            	str = SuppleDocSQL.IGRS_STATE_MASTER;
            	}
            	else
            	{
            		
            		str = SuppleDocSQL.IGRS_STATE_MASTER_HINDI;
            	}
            }
            else
            {
            	param[0]=""+_countryIdVar;
            	if(language.equalsIgnoreCase("en"))
            	{
            	str = SuppleDocSQL.IGRS_STATE_MASTER_MP;
            	}
            	else
            	{
            		str = SuppleDocSQL.IGRS_STATE_MASTER_MP_HINDI;
            	}
            }
            param[0]=""+_countryIdVar;
            dbUtil.createPreparedStatement(str);
            typeList=dbUtil.executeQuery(param);
            logger.info("STATE_typeList:="+typeList);
            if(typeList!=null){
                for(int i=0;i<typeList.size();i++) {
                    typeTemp=new ArrayList();
                    typeTemp=(ArrayList)typeList.get(i);
                    if(typeTemp.size()>0){
                        SuppleDocDTO type = new SuppleDocDTO();
                        if(_axn.equalsIgnoreCase("BankStateType"))
                        {
                        	type.setBankStateId((String)typeTemp.get(0));
                            type.setBankStateName((String)typeTemp.get(1));
                        }
                        else
                        {
                        	type.setStateId((String)typeTemp.get(0));
                        	type.setStateName((String)typeTemp.get(1));
                        }
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
    
    public synchronized ArrayList getMunicipalBody(String language) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("getMunicipalBody() - start");
		}
		DBUtility dbUtil = null;
		ArrayList list = new ArrayList();
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			
			if(language.equalsIgnoreCase("en"))
			{
				list = dbUtil.executeQuery(SuppleDocSQL.MUNICIPAL_BODY_SELECT_QUERY);
			}
			else
			{
				list = dbUtil.executeQuery(SuppleDocSQL.MUNICIPAL_BODY_SELECT_QUERY_HINDI);
			}
			
		} catch (Exception x) {
			logger.debug(x);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getMunicipalBody() - end");
		}
		return list;
	}

    
    public synchronized ArrayList getPropertyType(String language) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("getPropertyType() - start");
		}
		
		DBUtility dbUtil = new DBUtility();
		ArrayList list = new ArrayList();
		try {
			dbUtil.createStatement();
		
			if(language.equalsIgnoreCase("en"))
			{
			list = dbUtil.executeQuery(SuppleDocSQL.PROPERTY_TYPE_SELECT_QUERY);
			}
			else
			{
				list = dbUtil.executeQuery(SuppleDocSQL.PROPERTY_TYPE_SELECT_QUERY_HINDI);
			}
		
		
		} catch (Exception x) {
			logger.debug(x);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getPropertyType() - end");
		}
		return list;
	}
    
    
    public synchronized ArrayList getMahalla(String patwariId, String language) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("getMahalla(String) - start");
		}
		DBUtility dbUtil = null;
		
		
		String SQL ="";
		if(language.equalsIgnoreCase("en"))
		{
			SQL =  SuppleDocSQL.MOHALLA_QUERY;	
		}
		else
		{
			SQL = SuppleDocSQL.MOHALLA_QUERY_HINDI;
		}
		
		ArrayList list = new ArrayList();
		String arry[] = new String[1];
		if (patwariId != null) {
			arry[0] = patwariId;
		}
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(SQL);
			logger.debug("Wipro in IGRSCommon - "
					+ "getMahalla after PreparedStatement");
			list = dbUtil.executeQuery(arry);
			logger.debug("Wipro in IGRSCommon - "
					+ "getMahalla() after executeQuery");
		} catch (Exception x) {
			logger.debug(x);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}

		return list;
	}
    
    
    public synchronized ArrayList getWard(String tehsilId, String areaType,
			String wardType, String language) throws Exception {
		if (logger.isDebugEnabled()) {
			System.out.println("getWard(String, String, String) - start");
		}
		DBUtility dbUtil = null;
		
		String SQL ="";
			if(language.equalsIgnoreCase("en"))
			{
				SQL = SuppleDocSQL.WARD_QUERY;
			}
			else
			{
				SQL = SuppleDocSQL.WARD_QUERY_HINDI;	
			}
		
		String arry[] = new String[3];
		ArrayList list = new ArrayList();
		if (tehsilId != null) {
			arry[0] = tehsilId;
		}

		arry[1] = areaType;
		arry[2] = wardType;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(SQL);
			System.out.println("Wipro in IGRSCommon - "
					+ "getWard() after preparedStatement");
			list = dbUtil.executeQuery(arry);
			System.out.println("Wipro in IGRSCommon - "
					+ "getWard() after executeQuery");
		} catch (Exception x) {
			System.out.println(x);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}

		return list;
	}
    
    
    public synchronized ArrayList getAreaType(String langauge) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("getAreaType() - start");
		}
		DBUtility dbUtil = null;
		ArrayList list = new ArrayList();
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			//list = dbUtil.executeQuery(CommonSQL.AREA_TYPE_QUERY);
			if(langauge.equalsIgnoreCase("en"))
			list = dbUtil.executeQuery(SuppleDocSQL.AREA_TYPE_QUERY);
			else
			list = dbUtil.executeQuery(SuppleDocSQL.AREA_TYPE_QUERY_HINDI);
		} catch (Exception x) {
			logger.debug(x);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getAreaType() - end");
		}
		return list;
	}
    
    
    
    public synchronized ArrayList getThesil(String distId, String language) throws Exception {
		if (logger.isDebugEnabled()) {
			System.out.println("getThesil(String) - start");
		}

		DBUtility dbUtil = null;
		String SQL="";
		if(language.equalsIgnoreCase("en"))
		{
		 SQL = SuppleDocSQL.TEHSIL_QUERY;
		}
		else
		{
			 SQL = SuppleDocSQL.TEHSIL_QUERY_HINDI;
		}
		ArrayList list = new ArrayList();
		String arry[] = new String[1];
		if (distId != null) {
			arry[0] = distId;
		}
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(SQL);
			System.out.println("Wipro in IGRSCommon - "
					+ "getTehsil() after PreparedStatement");
			list = dbUtil.executeQuery(arry);
			System.out.println("Wipro in IGRSCommon - "
					+ "getTehsil() after executeQuery");
		} catch (Exception x) {
			System.out.println(x);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}

		return list;
	}
    
    
	
    //$05 Method for Retrieving Districts from DISTRICT_MASTER
    public ArrayList  districtStackDAO(String _stateIdVar, String _axn, String language)throws ServletException,IOException,SQLException,Exception{
        
        ArrayList  ar1=new ArrayList ();
        DBUtility dbUtil=null;
        logger.debug("WE ARE IN DAO-districtStack");
        try {
            ArrayList typeList=new ArrayList();
            ArrayList typeTemp=new ArrayList();
           
            dbUtil=new DBUtility();
            String str=""; // Query for district list from IGRS_DISTRICT_MASTER
            String param[]=new String[1];
            param[0]=""+_stateIdVar;
            
            if(_axn.equalsIgnoreCase("BankDistrict"))
            {
            	str=SuppleDocSQL.IGRS_DISTRICT_MASTER;
            	
            }
            
            else
            {
            	if(language.equalsIgnoreCase("en"))
            	{
            	str = SuppleDocSQL.IGRS_DISTRICT_MASTER_MP;
            	}
            	else 
            	{
            		str = SuppleDocSQL.IGRS_DISTRICT_MASTER_HINDI_MP;
            	}
            
            }
            dbUtil.createPreparedStatement(str);
            typeList=dbUtil.executeQuery(param);
            logger.info("DISTRICT_typeList:="+typeList);
            if(typeList!=null){
                for(int i=0;i<typeList.size();i++) {
                    typeTemp=new ArrayList();
                    typeTemp=(ArrayList)typeList.get(i);
                    if(typeTemp.size()>0){
                        SuppleDocDTO type = new SuppleDocDTO();
                        if(_axn.equalsIgnoreCase("BankDistrictType"))
                        {
                        	type.setBankDistrictId((String)typeTemp.get(0));
                            type.setBankDistrictName((String)typeTemp.get(1));
                        }
                        else if(_axn.equalsIgnoreCase("Property"))
                        {
                        	type.setDistrictIDProperty((String)typeTemp.get(0));
                        	type.setDistrictProperty((String)typeTemp.get(1));
                        }
                        else
                        {
                        	type.setDistrictId((String)typeTemp.get(0));
                        	type.setDistrictName((String)typeTemp.get(1));
                        }
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

    //$00 Method for Retrieving Photo Id Proof from PHOTO_ID_PROOF_TYPE_MASTER --Not required Anymore
    public ArrayList  photoIdStackDAO()throws ServletException,IOException,SQLException,Exception{
        
        ArrayList  ar1=new ArrayList ();
        DBUtility dbUtil=null;
        logger.debug("WE ARE IN DAO-photoIdStack");
        try {
            ArrayList typeList=new ArrayList();
            ArrayList typeTemp=new ArrayList();
            dbUtil=new DBUtility();
            String str=SuppleDocSQL.IGRS_PHOTOID_PROOF_MASTER; // Query for Photo Id Proof Type list from IGRS_PHOTOID_PROOF_TYPE_MASTER
            dbUtil.createStatement();
            typeList=dbUtil.executeQuery(str);
            if(typeList!=null){
                for(int i=0;i<typeList.size();i++) {
                    typeTemp=new ArrayList();
                    typeTemp=(ArrayList)typeList.get(i);
                    if(typeTemp.size()>0){
                        SuppleDocDTO type = new SuppleDocDTO();
                        //type.setPhotoProofTypeId((String)typeTemp.get(0));
                      //  type.setPhotoProofTypeName((String)typeTemp.get(1));
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
	
    //$00 Method for Retrieving Caste from CASTE_MASTER --Not Required Anymore
    public ArrayList  casteStackDAO()throws ServletException,IOException,SQLException,Exception{
    	logger.debug("WE ARE IN DAO-casteStack");
        ArrayList  ar1=new ArrayList ();
        DBUtility dbUtil=null;
        try {
            ArrayList typeList=new ArrayList();
            ArrayList typeTemp=new ArrayList();
            dbUtil=new DBUtility();
            String str=SuppleDocSQL.IGRS_CASTE_MASTER;
            dbUtil.createStatement();
            typeList=dbUtil.executeQuery(str);
            if(typeList!=null){
                for(int i=0;i<typeList.size();i++) {
                    typeTemp=new ArrayList();
                    typeTemp=(ArrayList)typeList.get(i);
                    if(typeTemp.size()>0){
                        SuppleDocDTO type = new SuppleDocDTO();
                        //type.setCasteId((String)typeTemp.get(0));
                      //  type.setCasteName((String)typeTemp.get(1));
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
	
    //$00 Method for Retrieving RELIGION from RELIGION_MASTER -- Not required Anymore
    public ArrayList  religionStackDAO()throws ServletException,IOException,SQLException,Exception{
    	logger.debug("WE ARE IN DAO-religionStack");
        ArrayList  ar1=new ArrayList ();
        DBUtility dbUtil=null;
        try {
            ArrayList typeList=new ArrayList();
            ArrayList typeTemp=new ArrayList();
            dbUtil=new DBUtility();
            String str=SuppleDocSQL.IGRS_RELIGION_MASTER;
            dbUtil.createStatement();
            typeList=dbUtil.executeQuery(str);
            if(typeList!=null){
                for(int i=0;i<typeList.size();i++) {
                    typeTemp=new ArrayList();
                    typeTemp=(ArrayList)typeList.get(i);
                    if(typeTemp.size()>0){
                        SuppleDocDTO type = new SuppleDocDTO();
                     //   type.setReligionId((String)typeTemp.get(0));
                     //   type.setReligionName((String)typeTemp.get(1));
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
	
    //$07 Method for Retrieving LOAN PURPOSE from SUPPLI_LOANPURPOSE_MASTER
    public ArrayList  loanPurposeStackDAO()throws ServletException,IOException,SQLException,Exception{
    	logger.debug("WE ARE IN DAO-loanPurposeStackDAO");
        ArrayList  ar1=new ArrayList ();
        DBUtility dbUtil=null;
        try {
            ArrayList typeList=new ArrayList();
            ArrayList typeTemp=new ArrayList();
            dbUtil=new DBUtility();
            String str=SuppleDocSQL.IGRS_SUPPLI_PURPOSE_MASTER;
            dbUtil.createStatement();
            typeList=dbUtil.executeQuery(str);
            if(typeList!=null){
                for(int i=0;i<typeList.size();i++) {
                    typeTemp=new ArrayList();
                    typeTemp=(ArrayList)typeList.get(i);
                    if(typeTemp.size()>0){
                        SuppleDocDTO type = new SuppleDocDTO();
                      //  type.setLoanPurposeId((String)typeTemp.get(0));
                      //  type.setLoanPurposeName((String)typeTemp.get(1));
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
	
    //$00 Method for Retrieving LOAN DEED from DEED_MASTER -- Not required Anymore
    public ArrayList  deedStackDAO()throws ServletException,IOException,SQLException,Exception{
    	logger.debug("WE ARE IN DAO-deedStackDAO");
        ArrayList  ar1=new ArrayList ();
        DBUtility dbUtil=null;
        try {
            ArrayList typeList=new ArrayList();
            ArrayList typeTemp=new ArrayList();
            dbUtil=new DBUtility();
            String str=SuppleDocSQL.IGRS_DEED_TYPE_MASTER;
            dbUtil.createStatement();
            typeList=dbUtil.executeQuery(str);
            if(typeList!=null){
                for(int i=0;i<typeList.size();i++) {
                    typeTemp=new ArrayList();
                    typeTemp=(ArrayList)typeList.get(i);
                    if(typeTemp.size()>0){
                        SuppleDocDTO type = new SuppleDocDTO();
                        //type.setDeedTypeId((String)typeTemp.get(0));
                       // type.setDeedTypeName((String)typeTemp.get(1));
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
	
    //$09 Method for Searching Estamp Code
    public ArrayList estampCheckDAO(String _estampCode)throws ServletException,IOException,SQLException,Exception{
    	logger.debug("WE ARE IN DAO-estampCheckDAO");
        DBUtility dbUtil=null;
        ArrayList typeList=null;
        try {
            dbUtil=new DBUtility();
            String str=SuppleDocSQL.IGRS_ESTAMP_CODE_SEARCH;
            dbUtil.createPreparedStatement(str);
            String param[]=new String[1];
            param[0]=_estampCode;
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
	// 
    //$10 Method for Inserting details
    public boolean insertSuppliDetailsDAO(String param1[], String param2[], String param3[],String param4[])throws ServletException,IOException,SQLException,Exception{
    	logger.debug("WE ARE IN DAO-insertSuppliDetailsDAO");
        DBUtility dbUtil=null;
        try {
            dbUtil=new DBUtility();
            boolean flag=false;
            String str1=SuppleDocSQL.IGRS_SUPPLI_INSERT_DOCTXN;
            dbUtil.createPreparedStatement(str1);
            if(dbUtil.executeUpdate(param1))
            {
            	String str2=SuppleDocSQL.IGRS_SUPPLI_INSERT_PARTY;
            	dbUtil.createPreparedStatement(str2);
                if(dbUtil.executeUpdate(param2))
                {
                	String str3=SuppleDocSQL.IGRS_SUPPLI_INSERT_DEED_DETAILS;
                	dbUtil.createPreparedStatement(str3);
                    if(dbUtil.executeUpdate(param3))
                    {
                    	String str4=SuppleDocSQL.IGRS_SUPPLI_INSERT_BANK_DETAILS;
                    	dbUtil.createPreparedStatement(str4);
                        if(dbUtil.executeUpdate(param4))
                        {
                        	flag= true;
                        }
                    }
                }
            }
            if(!flag)
            {
            	dbUtil.rollback();
            }else{
            	dbUtil.commit();
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
	}
    //$011 Method for inserting Party Details..
    public boolean insertSuppliPartyDetailsDAO(String param1[])throws ServletException,IOException,SQLException,Exception{
    	logger.debug("WE ARE IN DAO-insertSuppliDetailsDAO");
        DBUtility dbUtil=null;
        try {
            dbUtil=new DBUtility();
            boolean flag=false;
            String str1=SuppleDocSQL.IGRS_SUPPLI_INSERT_PARTY;
            dbUtil.createPreparedStatement(str1);
            if(dbUtil.executeUpdate(param1))
            {
            	
                        	flag= true;
                    
             }
                
            
            if(!flag)
            {
            	dbUtil.rollback();
            }else{
            	dbUtil.commit();
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
	}
    
	//
    // $12 Method for Retrieving Document Details
    public ArrayList  searchListViewDAO(String param[],String sql)throws ServletException,IOException,SQLException,Exception{
    	logger.debug("WE ARE IN DAO-searchListViewDAO");
        ArrayList  ar1=new ArrayList ();
        DBUtility dbUtil=null;
        try {
            ArrayList typeList=new ArrayList();
            ArrayList typeTemp=new ArrayList();
            dbUtil=new DBUtility();
            String str=sql;
            
            
         /*   if((!param[0].equalsIgnoreCase("")&&param[0]!=null) &&
            		((param[1].trim().equalsIgnoreCase("") && param[2].trim().equalsIgnoreCase(""))))
            {
            	str += " FILING_NUMBER='" + param[0]+ "'";
            	logger.debug("Only Id");
            	
            }
            
       if(param[0].equalsIgnoreCase("") &&
    		(!param[1].trim().equalsIgnoreCase("") && !param[2].trim().equalsIgnoreCase("")))
            {
    	   
    	   str += " TRUNC(CREATED_DATE) BETWEEN TO_CHAR(";
			str += " TO_DATE('" + param[1] + "','DD/MM/YYYY')) AND ";
			str += " TO_CHAR(TO_DATE('" + param[2] + "','DD/MM/YYYY'))";
			logger.debug("Only Dates");
            }
          if((!param[0].equalsIgnoreCase("")) &&
    		((!param[1].trim().equalsIgnoreCase("") && !param[2].trim().equalsIgnoreCase(""))))
            {
        	  str += " FILING_NUMBER='" + param[0]+ "'";
        	  str += " AND";
        	  str += " TRUNC(CREATED_DATE) BETWEEN TO_CHAR(";
  			str += " TO_DATE('" + param[1] + "','DD/MM/YYYY')) AND ";
  			str += " TO_CHAR(TO_DATE('" + param[2] + "','DD/MM/YYYY'))";
        	  logger.debug("Both");
            	
            }
            */
            str += SuppleDocSQL.REST_OF_QUERY;
            
            dbUtil.createPreparedStatement(str);
            typeList=dbUtil.executeQuery(param);
            if(typeList!=null){
                for(int i=0;i<typeList.size();i++) {
                    typeTemp=new ArrayList();
                    typeTemp=(ArrayList)typeList.get(i);
                    if(typeTemp.size()>0){
                        SuppleDocDTO type = new SuppleDocDTO();
                        type.setSearchFiling(((String)typeTemp.get(0)));
                        type.setCreatedDate((String)typeTemp.get(1));
                        type.setSerialNo(i+1);
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
    //$011 End
    //$012 Method for retrieving entire details
    public SuppleDocDTO getTotalDetailsDAO(String param[], String language)throws ServletException,IOException,SQLException,Exception{
    	logger.debug("WE ARE IN DAO-estampCheckDAO");
        DBUtility dbUtil=null;
        ArrayList typeList=null;
        try {
           
        	SuppleDocDTO fullDetails = new SuppleDocDTO();
        	
        	dbUtil=new DBUtility();
           
            String str=SuppleDocSQL.IGRS_SUPPLI_VIEW_SINGLE_DETAILS;
           
            dbUtil.createPreparedStatement(str);
           
            
            
            typeList=dbUtil.executeQuery(param);
            
            ArrayList singleDetails = (ArrayList)typeList.get(0);
            fullDetails.setReferenceId((String)singleDetails.get(0));
            fullDetails.setDocumentId((String)singleDetails.get(1));
            fullDetails.setOtherDocument((String)singleDetails.get(2));
            fullDetails.setBankName((String)singleDetails.get(3));
            fullDetails.setBankCountryId((String)singleDetails.get(4));
            fullDetails.setBankDistrictId((String)singleDetails.get(5));
            fullDetails.setBankStateId((String)singleDetails.get(6));
            fullDetails.setBankPersonName((String)singleDetails.get(7));
            fullDetails.setBankPersonDesi((String)singleDetails.get(8));
            fullDetails.setBankPersonMob((String)singleDetails.get(9));
            fullDetails.setBankPersonEmail((String)singleDetails.get(10));
            fullDetails.setBankPostalCode(Integer.parseInt((String)singleDetails.get(11)));
            fullDetails.setBankPhone((String)singleDetails.get(12));
            fullDetails.setExecuteDate((String)singleDetails.get(13));
            fullDetails.setRecieptDate(((String)singleDetails.get(14)));
            fullDetails.setTransactionAmount(Double.parseDouble((String)singleDetails.get(15)));
            fullDetails.setKeysStringPurpose((String)singleDetails.get(16));
            fullDetails.setAcqElements((String)singleDetails.get(17));
            fullDetails.setPresentDate((String)singleDetails.get(18));
            fullDetails.setSrName((String)singleDetails.get(19));
            fullDetails.setSroName((String)singleDetails.get(20));
            fullDetails.setScannedCopyName((String)singleDetails.get(21));
            fullDetails.setStatus("Comp");
            String data = ((String)singleDetails.get(22));
            if(data==null)
            {
            	data="0.0";
            }
            fullDetails.setStampDuty(data);
            if(language.equalsIgnoreCase("en"))
            {
            fullDetails.setCountryName("India");
            fullDetails.setBankCountryName("India");
            fullDetails.setBankStateName("Bhopal");
            fullDetails.setStateName("Bhopal");
            }
            else
            {
            	fullDetails.setCountryName("भारत");
                fullDetails.setBankCountryName("भारत");
                fullDetails.setBankStateName("भोपाल");
                fullDetails.setStateName("भोपाल");
            	
            	
            }
            fullDetails.setBankAddress((String)singleDetails.get(23));
            fullDetails.setFilingNumber(param[0]);
            fullDetails.setBookTypeName((String)singleDetails.get(24));
            fullDetails.setScannedCopyPath((String)singleDetails.get(25));
            
            fullDetails.setDocumentNumber((String)singleDetails.get(26));
            String data1 = ((String)singleDetails.get(27));
            if(data1==null)
            {
            	data1="0.0";
            }
             fullDetails.setStampDutyNow(Double.parseDouble(data1));
             String rem= (String)singleDetails.get(28);
             if("".equalsIgnoreCase(rem))
             {
            fullDetails.setRemarks("-");
             }
             else
             {
            	 fullDetails.setRemarks(rem);
             }
             
             String Exemptions = (String)singleDetails.get(29);
             if(Exemptions==null || "".equalsIgnoreCase(Exemptions))
             {
            	 fullDetails.setExemptions("-");
            	 
             }
             else
             {
            	 fullDetails.setExemptions(Exemptions);
             }
            String refernceId[] = new String[1];
        	refernceId[0]= fullDetails.getReferenceId();
            
            
        	String districtId[] = new String[1];
        	districtId[0]= fullDetails.getBankDistrictId();
        	String sql="";
   if(language.equalsIgnoreCase("en"))
   {
	    sql = SuppleDocSQL.IGRS_SUPPLI_VIEW_DISTRICT_DETAILS;
   }
   else
   {
	   sql = SuppleDocSQL.IGRS_SUPPLI_VIEW_DISTRICT_DETAILS_HINDI;
   }
        	
        	
        	dbUtil.createPreparedStatement(sql);
        	
        	 typeList = dbUtil.executeQuery(districtId);
        	 ArrayList bankStateName = (ArrayList)typeList.get(0);
        	fullDetails.setBankDistrictName((String)bankStateName.get(0));
        	
        	
        	
        	//Changing query to get Document no.
            if(!fullDetails.getDocumentId().equalsIgnoreCase("4"))
            {
            	String documentId[] = new String[1];
            	documentId[0] = fullDetails.getDocumentId();
            	if(language.equalsIgnoreCase("en"))
            	{
            		str =SuppleDocSQL.IGRS_SUPPLI_VIEW_DOCUMENT_TYPE_DETAILS;
            	}
            	else
            	{
            		 str =SuppleDocSQL.IGRS_SUPPLI_VIEW_DOCUMENT_TYPE_DETAILS_HINDI;
            	}
            dbUtil.createPreparedStatement(str);
          typeList =   dbUtil.executeQuery(documentId);
          ArrayList documentName = (ArrayList)typeList.get(0);
          
          fullDetails.setDocumentName((String)documentName.get(0));
          
            }
            else
            {
            	
            	fullDetails.setDocumentName(fullDetails.getOtherDocument());
            }
            
            //--Got the Document Number
            
            
            //Getting all of the Party Details 
            str = SuppleDocSQL.IGRS_SUPPLI_VIEW_PARTY_TYPE_DETAILS_LIST;
            dbUtil.createPreparedStatement(str);
            typeList = dbUtil.executeQuery(refernceId);
            
            ArrayList listPartyDetails = new ArrayList();

    		if (typeList != null) {
    			for (int i = 0; i < typeList.size(); i++) {
    				ArrayList lst = (ArrayList) typeList.get(i);
    				SuppleDocDTO dto = new SuppleDocDTO();
    				logger.debug("Data:-" + lst.get(0) + ":" + lst.get(1)+ ":" + lst.get(2));
    				dto.setFname((String) lst.get(0));
    				dto.setLname((String) lst.get(1));
    				dto.setFatherName((String) lst.get(2));
    				dto.setCombineData((String) lst.get(0)+"~"+(String) lst.get(1)+"~"+(String) lst.get(2)+"~"+refernceId[0]);
    				listPartyDetails.add(dto);
    			}

    		}
            
    		fullDetails.setPartyDetails(listPartyDetails);
            
    		
    		//--Getting PStamp Details
    		 str = SuppleDocSQL.IGRS_SUPPLI_VIEW_PSTAMP_DETAILS;
    		 dbUtil.createPreparedStatement(str);
    		 typeList = dbUtil.executeQuery(refernceId);
    		
    		 
    		 ArrayList listPstampDetails = new ArrayList();

     		if (typeList != null) {
     			for (int i = 0; i < typeList.size(); i++) {
     				ArrayList lst = (ArrayList) typeList.get(i);
     				SuppleDocDTO dto = new SuppleDocDTO();
     				logger.debug("Data:-" + lst.get(0) + ":" + lst.get(1)+ ":" + lst.get(2));
     				dto.setCodeStamps((String) lst.get(0));
     				dto.setDenominationStamps(Double.parseDouble((String) lst.get(1)));
     				dto.setSeriesNo((String) lst.get(2));
     				dto.setNameVendorPStamps((String) lst.get(3));
     				dto.setDatePStamps((String) lst.get(4));
     				listPstampDetails.add(dto);
     			}

     		}
            
     		fullDetails.setPstampDetails(listPstampDetails);
     		
     		
     		//--Getting EStamp Details
   		 str = SuppleDocSQL.IGRS_SUPPLI_VIEW_ESTAMP_DETAILS;
   		 dbUtil.createPreparedStatement(str);
   		 typeList = dbUtil.executeQuery(refernceId);
     		ArrayList listEstampDetails = new ArrayList();

     		if (typeList != null) {
     			for (int i = 0; i < typeList.size(); i++) {
     				ArrayList lst = (ArrayList) typeList.get(i);
     				SuppleDocDTO dto = new SuppleDocDTO();
     				logger.debug("Data:-" + lst.get(0) + ":" + lst.get(1)+ ":" + lst.get(2));
     				dto.setEstampCode(((String) lst.get(0)));
     				dto.setEstampDuty(((String) lst.get(1)));
     				dto.setEstampPurpose((String) lst.get(2));
     				listEstampDetails.add(dto);
     			}

     		}
     		
            fullDetails.setEstampDetails(listEstampDetails);
     		
            
            
            String purpose = fullDetails.getKeysStringPurpose();
            String purposeId[] = null;
            if(purpose!=null)
            {
            purposeId = purpose.split(",");
            }
            else
            {
            	purposeId = null;
            }
            if(language.equalsIgnoreCase("en"))
            {
            str = SuppleDocSQL.IGRS_SUPPLI_GET_PURPOSES_NAME;
            }
            else
            {
            str = SuppleDocSQL.IGRS_SUPPLI_GET_PURPOSES_NAME_HINDI;
            }
            dbUtil.createPreparedStatement(str);
            ArrayList listPurpose= null;
            ArrayList<String> purposeNames = new ArrayList<String>();
            if(purposeId!=null)
            {
            for(int i=0;i<purposeId.length;i++)
            {
            	String temp[] = new String[1];
            	temp[0]= purposeId[i];
            	typeList = dbUtil.executeQuery(temp);
            	listPurpose = (ArrayList)typeList.get(0);
            	purposeNames.add((i+1)+".) "+(String)listPurpose.get(0));
            }
            }
            fullDetails.setPurposeNames(purposeNames);
            
     		
            return fullDetails;
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
	    return null;
	}
    
    
    
    
    public SuppleDocDTO getTotalDetailsReferenceDAO(String param[], String language)throws ServletException,IOException,SQLException,Exception{
    	logger.debug("WE ARE IN DAO-estampCheckDAO");
        DBUtility dbUtil=null;
        ArrayList typeList=null;
        try {
           
        	SuppleDocDTO fullDetails = new SuppleDocDTO();
        	
        	dbUtil=new DBUtility();
           
            String str=SuppleDocSQL.IGRS_SUPPLI_VIEW_SINGLE_DETAILS_REF;
           
            dbUtil.createPreparedStatement(str);
           
            
            
            typeList=dbUtil.executeQuery(param);
            
            ArrayList singleDetails = (ArrayList)typeList.get(0);
            fullDetails.setReferenceId((String)singleDetails.get(0));
            fullDetails.setDocumentId((String)singleDetails.get(1));
            fullDetails.setOtherDocument((String)singleDetails.get(2));
            fullDetails.setBankName((String)singleDetails.get(3));
            fullDetails.setBankCountryId((String)singleDetails.get(4));
            fullDetails.setBankDistrictId((String)singleDetails.get(5));
            fullDetails.setBankStateId((String)singleDetails.get(6));
            fullDetails.setBankPersonName((String)singleDetails.get(7));
            fullDetails.setBankPersonDesi((String)singleDetails.get(8));
            fullDetails.setBankPersonMob((String)singleDetails.get(9));
            fullDetails.setBankPersonEmail((String)singleDetails.get(10));
            fullDetails.setBankPostalCode(Integer.parseInt((String)singleDetails.get(11)));
            fullDetails.setBankPhone((String)singleDetails.get(12));
            fullDetails.setExecuteDate((String)singleDetails.get(13));
            fullDetails.setRecieptDate((String)singleDetails.get(14));
            fullDetails.setTransactionAmount(Double.parseDouble((String)singleDetails.get(15)));
            fullDetails.setKeysStringPurpose((String)singleDetails.get(16));
            fullDetails.setAcqElements((String)singleDetails.get(17));
            fullDetails.setPresentDate((String)singleDetails.get(18));
            fullDetails.setSrName((String)singleDetails.get(19));
            fullDetails.setSroName((String)singleDetails.get(20));
            fullDetails.setScannedCopyName((String)singleDetails.get(21));
            fullDetails.setStatus("Refer");
            
            String data = ((String)singleDetails.get(22));
            if(data==null)
            {
            	data="0.0";
            }
            fullDetails.setStampDuty(data);
            if(language.equalsIgnoreCase("en"))
            {
            fullDetails.setCountryName("India");
            fullDetails.setBankCountryName("India");
            fullDetails.setBankStateName("Bhopal");
            fullDetails.setStateName("Bhopal");
            }
            else
            {
            	fullDetails.setCountryName("भारत");
                fullDetails.setBankCountryName("भारत");
                fullDetails.setBankStateName("भोपाल");
                fullDetails.setStateName("भोपाल");
            	
            	
            }
            fullDetails.setBankAddress((String)singleDetails.get(23));
            fullDetails.setFilingNumber(param[0]);
            fullDetails.setBookTypeName((String)singleDetails.get(24));
            fullDetails.setScannedCopyPath((String)singleDetails.get(25));
            
            fullDetails.setDocumentNumber((String)singleDetails.get(26));
            
            
             String data1 = (String)singleDetails.get(27);
             if(data1==null)
             {
            	 data1="0.0";
             }
             fullDetails.setStampDutyNow(Double.parseDouble(data1));
             
             String exemption = (String)singleDetails.get(28);
             if(exemption==null)
             {
            	 fullDetails.setExemptions("-");
             }
             else
             {
            	 fullDetails.setExemptions(exemption);
             }
             
             String refernceId[] = new String[1];
        	refernceId[0]= fullDetails.getReferenceId();
            
            
        	String districtId[] = new String[1];
        	districtId[0]= fullDetails.getBankDistrictId();
       
         	String sql="";
            if(language.equalsIgnoreCase("en"))
            {
         	    sql = SuppleDocSQL.IGRS_SUPPLI_VIEW_DISTRICT_DETAILS;
            }
            else
            {
         	   sql = SuppleDocSQL.IGRS_SUPPLI_VIEW_DISTRICT_DETAILS_HINDI;
            } 	
        	
        	 dbUtil.createPreparedStatement(sql);
        	
        	 typeList = dbUtil.executeQuery(districtId);
        	 ArrayList bankStateName = (ArrayList)typeList.get(0);
        	fullDetails.setBankDistrictName((String)bankStateName.get(0));
        	
        	
        	
        	//Changing query to get Document no.
            if(!fullDetails.getDocumentId().equalsIgnoreCase("4"))
            {
            	String documentId[] = new String[1];
            	documentId[0] = fullDetails.getDocumentId();
            	if(language.equalsIgnoreCase("en"))
            	{
            		str =SuppleDocSQL.IGRS_SUPPLI_VIEW_DOCUMENT_TYPE_DETAILS;
            	}
            	else
            	{
            		 str =SuppleDocSQL.IGRS_SUPPLI_VIEW_DOCUMENT_TYPE_DETAILS_HINDI;
            	}
            
            dbUtil.createPreparedStatement(str);
          typeList =   dbUtil.executeQuery(documentId);
          ArrayList documentName = (ArrayList)typeList.get(0);
          
          fullDetails.setDocumentName((String)documentName.get(0));
          
            }
            else
            {
            	
            	fullDetails.setDocumentName(fullDetails.getOtherDocument());
            }
            
            //--Got the Document Number
            
            
            //Getting all of the Party Details 
            str = SuppleDocSQL.IGRS_SUPPLI_VIEW_PARTY_TYPE_DETAILS_LIST;
            dbUtil.createPreparedStatement(str);
            typeList = dbUtil.executeQuery(refernceId);
            
            ArrayList listPartyDetails = new ArrayList();

    		if (typeList != null) {
    			for (int i = 0; i < typeList.size(); i++) {
    				ArrayList lst = (ArrayList) typeList.get(i);
    				SuppleDocDTO dto = new SuppleDocDTO();
    				logger.debug("Data:-" + lst.get(0) + ":" + lst.get(1)+ ":" + lst.get(2));
    				dto.setFname((String) lst.get(0));
    				dto.setLname((String) lst.get(1));
    				dto.setFatherName((String) lst.get(2));
    				dto.setCombineData((String) lst.get(0)+"~"+(String) lst.get(1)+"~"+(String) lst.get(2)+"~"+refernceId[0]);
    				listPartyDetails.add(dto);
    			}

    		}
            
    		fullDetails.setPartyDetails(listPartyDetails);
            
    		
    		//--Getting PStamp Details
    		 str = SuppleDocSQL.IGRS_SUPPLI_VIEW_PSTAMP_DETAILS;
    		 dbUtil.createPreparedStatement(str);
    		 typeList = dbUtil.executeQuery(refernceId);
    		
    		 
    		 ArrayList listPstampDetails = new ArrayList();

     		if (typeList != null) {
     			for (int i = 0; i < typeList.size(); i++) {
     				ArrayList lst = (ArrayList) typeList.get(i);
     				SuppleDocDTO dto = new SuppleDocDTO();
     				logger.debug("Data:-" + lst.get(0) + ":" + lst.get(1)+ ":" + lst.get(2));
     				dto.setCodeStamps((String) lst.get(0));
     				dto.setDenominationStamps(Double.parseDouble((String) lst.get(1)));
     				dto.setSeriesNo((String) lst.get(2));
     				dto.setNameVendorPStamps((String) lst.get(3));
     				dto.setDatePStamps((String) lst.get(4));
     				listPstampDetails.add(dto);
     			}

     		}
            
     		fullDetails.setPstampDetails(listPstampDetails);
     		
     		
     		//--Getting EStamp Details
   		 str = SuppleDocSQL.IGRS_SUPPLI_VIEW_ESTAMP_DETAILS;
   		 dbUtil.createPreparedStatement(str);
   		 typeList = dbUtil.executeQuery(refernceId);
     		ArrayList listEstampDetails = new ArrayList();

     		if (typeList != null) {
     			for (int i = 0; i < typeList.size(); i++) {
     				ArrayList lst = (ArrayList) typeList.get(i);
     				SuppleDocDTO dto = new SuppleDocDTO();
     				logger.debug("Data:-" + lst.get(0) + ":" + lst.get(1)+ ":" + lst.get(2));
     				dto.setEstampCode(((String) lst.get(0)));
     				dto.setEstampDuty(((String) lst.get(1)));
     				dto.setEstampPurpose((String) lst.get(2));
     				listEstampDetails.add(dto);
     			}

     		}
     		
            fullDetails.setEstampDetails(listEstampDetails);
     		
            
            
            String purpose = fullDetails.getKeysStringPurpose();
            ArrayList<String> purposeNames = new ArrayList<String>();
            if(purpose!=null)
            {
            String purposeId[] = purpose.split(",");
            
           
            if(language.equalsIgnoreCase("en"))
            {
            str = SuppleDocSQL.IGRS_SUPPLI_GET_PURPOSES_NAME;
            }
            else
            {
            str = SuppleDocSQL.IGRS_SUPPLI_GET_PURPOSES_NAME_HINDI;
            }
            dbUtil.createPreparedStatement(str);
            ArrayList listPurpose= null;
           
            for(int i=0;i<purposeId.length;i++)
            {
            	String temp[] = new String[1];
            	temp[0]= purposeId[i];
            	typeList = dbUtil.executeQuery(temp);
            	listPurpose = (ArrayList)typeList.get(0);
            	purposeNames.add((i+1)+".) "+(String)listPurpose.get(0));
            }
            }
            fullDetails.setPurposeNames(purposeNames);
            
     		
            return fullDetails;
	    }catch(IOException ie){
        	logger.error(ie);
        }
        catch(SQLException se){
        	logger.error(se);
        }
        catch(Exception e){
	    	logger.error(e);
	    	e.printStackTrace();
	    }
	    finally {
	    	logger.error("Connection is closed using FINALLY");
	        dbUtil.closeConnection(); 
	    }
	    return null;
	}
    
	// Method for getting referece id from sequence
    public  String getReferenceNumber(String _seqName) throws Exception {
    	DBUtility dbUtil = new DBUtility();
		String seqnumber = "";

		try {
			Date date = new Date();
			Format yearformat = new SimpleDateFormat("yyyy");
			Format monthformat = new SimpleDateFormat("MM");
			Format dateformat = new SimpleDateFormat("dd");
			String dfmt = dateformat.format(date);
			String yfmt = yearformat.format(date);
			String mfmt = monthformat.format(date);
			String SQL = SuppleDocSQL.GET_REFERENCE_SEQ_ID;
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			String number = dbUtil.executeQry(SQL);
			if(number.length()==1){
                number="00000"+number;  
                }else
                if(number.length()==2){
                        number="0000"+number;  
                }else
                if(number.length()==3){
                        number="000"+number;  
                }else
                if(number.length()==4){
                        number="00"+number;  
                }else
                if(number.length()==5){
                        number="0"+number;  
                }

			seqnumber =  "1"+dfmt + mfmt + yfmt + number;
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}
		return seqnumber;
	}

    //Method for getting Sequence Number for Last Page for generation of Filing Number
    public  String getSequenceNumber(String year,String seqname) throws Exception {
    	
    	
    	
    	String snakey = SuppleDocSQL.GET_MAX_DATE;
    	String sequencenumber="";
  //  	String SQL = "select " + _seqName + ".nextval from dual";
		DBUtility dbUtil = new DBUtility();
		try
		{
		
		dbUtil.createStatement();
		dbUtil.setAutoCommit(false);
		String number = dbUtil.executeQry(snakey);
	String date[] = number.split("/");
	
	int datenow = Integer.parseInt(date[2]);
	int yeargot = Integer.parseInt(year);
	if(yeargot>datenow)
	{
	
	dbUtil.createStatement();
	String dropSQL = "DROP SEQUENCE "+seqname+"";
	
	int i = dbUtil.executeUpdt(dropSQL);
	
		if(i==0)
		{
			dbUtil.createStatement();
			String createSequence = SuppleDocSQL.CREATE_SEQUENCE_FILING;
			
			int j = dbUtil.executeUpdt(createSequence);
			
			dbUtil.commit();
		String SQL = "select " + seqname + ".nextval from dual";
		sequencenumber = dbUtil.executeQry(SQL);
		
		}
		
	}
	
	else
	{
		dbUtil.createStatement();
		String SQL = "select " + seqname + ".nextval from dual";
		sequencenumber = dbUtil.executeQry(SQL);
	}
		if(sequencenumber.length()==1){
			
            sequencenumber="0000"+sequencenumber;  
            }else
            if(sequencenumber.length()==2){
                    sequencenumber="000"+sequencenumber;  
            }else
            if(sequencenumber.length()==3){
                    sequencenumber="00"+sequencenumber;  
            }else
            if(sequencenumber.length()==4){
                    sequencenumber="0"+sequencenumber;  
            }
		
		}
		catch (Exception e) {
		logger.debug(e.getMessage(),e);
		}
		finally
		{
			dbUtil.closeConnection();
		}
		
		
		return sequencenumber;
    
    }
    
    
    
    
	public void insertCompleteSupDetails(SuppleDocDTO baseFormDTO,
			SuppleDocDTO[] values) {
		boolean check = false;
		try {
			DBUtility dbUtil = new DBUtility();
			try {
				dbUtil.setAutoCommit(false);
				
				//TODO insert baseFormDTO
				String str=SuppleDocSQL.IGRS_SUPPLI_INSERT_PARTY;
				String str1=SuppleDocSQL.IGRS_SUPPLI_INSERT_PARTY_FULL;
				dbUtil.createPreparedStatement(str);
				String param1[] = new String[18];
				for(int i =0;i<values.length;i++)
				{
					param1[0] = baseFormDTO.getReferenceId();
					param1[1] = values[i].getFname();
					param1[2] = values[i].getMname();
					param1[3] = values[i].getLname();
					param1[4] = values[i].getGender();
					param1[5] = String.valueOf(values[i].getAge());
					param1[6] = values[i].getCountryId();
					param1[7] = values[i].getStateId();
					param1[8] = values[i].getDistrictName();
					param1[9] = values[i].getAddress();
					param1[10] = String.valueOf(values[i].getPostalCode());
					param1[11] = String.valueOf(values[i].getPhoneNo());
					param1[12] = String.valueOf(values[i].getMobNo());
					param1[13] = values[i].getMailId();
					param1[14] = values[i].getFatherName();
					param1[15] = values[i].getMotherName();
					param1[16] = values[i].getRole();
					param1[17] = baseFormDTO.getUserId();
					
					check = dbUtil.executeUpdate(param1);
					if(!check)
					{
						break;
					}
			
				}
				
				if(check)
				{
				dbUtil.createPreparedStatement(str1);
				String param2[] = new String[21];
				param2[0] = baseFormDTO.getReferenceId();
				param2[1] = baseFormDTO.getDocumentName();
				param2[2] = baseFormDTO.getOtherDocument();
				param2[3] = baseFormDTO.getBankName();
				param2[4] = baseFormDTO.getBankCountryName();
				param2[5] = baseFormDTO.getBankStateName();
				param2[6] = baseFormDTO.getBankDistrictName();
				param2[7] = baseFormDTO.getBankPersonName();
				param2[8] = baseFormDTO.getBankPersonDesi();
				param2[9] = baseFormDTO.getBankPersonMob();
				param2[10] = baseFormDTO.getBankPersonEmail();
				param2[11] = String.valueOf(baseFormDTO.getBankPostalCode());
				param2[12] = String.valueOf(baseFormDTO.getBankPhone());
				param2[13] = baseFormDTO.getExecuteDate();
				param2[14] = baseFormDTO.getPresentDate();
				param2[15] = String.valueOf(baseFormDTO.getTransactionAmount());
				param2[16] = baseFormDTO.getUserId();
				param2[17] = "P";
				param2[18] = "1";
				param2[19] = baseFormDTO.getBankAddress();
				param2[20] = baseFormDTO.getOfficeId();
				 check = dbUtil.executeUpdate(param2);
				}
				
				
				
				if(check)
				{
				dbUtil.commit();
				}
				else
				{
					dbUtil.rollback();
				}
				} catch (Exception e) {
				// TODO: handle exception
				dbUtil.rollback();
			} finally {
				dbUtil.closeConnection();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	public void insertCompletePartial2DetailsDAO(SuppleDocDTO baseFormDTO,
			SuppleDocDTO[] valuesPStamp, SuppleDocDTO[] valuesEStamp) {
		
		boolean boo=true;
	
		try {
			DBUtility dbUtil = new DBUtility();
			try {
				dbUtil.setAutoCommit(false);
				
				String strEstamp=SuppleDocSQL.IGRS_SUPPLI_INSERT_ESTAMP;
				String strPstamp=SuppleDocSQL.IGRS_SUPPLI_INSERT_PSTAMP;
				String strPartial2 = SuppleDocSQL.IGRS_SUPPLI_UPDATE_PARTIAL2;
				dbUtil.createPreparedStatement(strPstamp);
				
				String param1[] = new String[8];
				
				for(int i =0;i<valuesPStamp.length;i++)
				{
					param1[0] = valuesPStamp[i].getCodeStamps();
					param1[1] = ""+valuesPStamp[i].getDenominationStamps();
					param1[2] = valuesPStamp[i].getSeriesNo();
					param1[3] = baseFormDTO.getUserId();
					param1[4] = "A";
					param1[5] = baseFormDTO.getReferenceId();
					param1[6] =""+valuesPStamp[i].getNameVendorPStamps();
					param1[7] =""+valuesPStamp[i].getDatePStamps();
					boo=dbUtil.executeUpdate(param1);
					if(!boo)
					{
						break;
					}
				}
				if(boo)
				{
					dbUtil.createPreparedStatement(strEstamp);
					String paramEstamp[] = new String[7];
					for(int i =0;i<valuesEStamp.length;i++)
					{
					paramEstamp[0] = valuesEStamp[i].getEstampCode();
					paramEstamp[1] = ""+valuesEStamp[i].getEstampDuty();
					paramEstamp[2] = valuesEStamp[i].getEstampPurpose();
					paramEstamp[3] = baseFormDTO.getUserId();
					paramEstamp[4] = valuesEStamp[i].getEstampDate();
					paramEstamp[5] = "A";
					paramEstamp[6] = baseFormDTO.getReferenceId();
					boo=dbUtil.executeUpdate(paramEstamp);
					if(!boo)
					{
						break;
					}
					}
				}
				if(boo)
				{
				dbUtil.createPreparedStatement(strPartial2);
				String param2[] = new String[10];
				param2[0] = ""+baseFormDTO.getStampDuty();
				param2[1] = baseFormDTO.getKeysStringPurpose();
				param2[2] = baseFormDTO.getAcqElements();
				param2[3] = baseFormDTO.getUserId();
				param2[4] ="P";
				param2[5] ="2";
				param2[6] = ""+baseFormDTO.getStampDutyNow();
				param2[7] = baseFormDTO.getDocumentNumber();
				param2[9] = baseFormDTO.getReferenceId();
				param2[8] = baseFormDTO.getExemptions();
				boo = dbUtil.executeUpdate(param2);
				//TODO insert baseFormDTO
				//TODO insert each item in values
				
				}
			if(boo)
			{
				dbUtil.commit();
			}
			else
			{
					dbUtil.rollback();
			}
			} catch (Exception e) {
				// TODO: handle exception
				dbUtil.rollback();
			} finally {
				dbUtil.closeConnection();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	public ArrayList getPropTypeL1List(String propId, String language) throws Exception {
		String[] param={propId};
		DBUtility dbUtility = null;
		ArrayList list = null;
		try{
			
		
		 dbUtility = new DBUtility();
		
		if(language.equalsIgnoreCase("en"))
		{
		dbUtility.createPreparedStatement(SuppleDocSQL.SELECT_PROPERTY_TYPEL1_DETAILS);
		}
		else
		{
			dbUtility.createPreparedStatement(SuppleDocSQL.SELECT_PROPERTY_TYPEL1_DETAILS_HINDI);
		}
		ArrayList ret=dbUtility.executeQuery(param);
		
		 list = new ArrayList();

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				SuppleDocDTO dto = new SuppleDocDTO();
				logger.debug("Dist:-" + lst.get(0) + ":" + lst.get(1));
				dto.setPropTypeL1Id((String) lst.get(0));
				dto.setPropTypeL1((String) lst.get(1));
				
				list.add(dto);
			}

		}
		}
		catch (Exception e) {
			logger.debug(e.getMessage(),e);
		}
		finally
		{
		dbUtility.closeConnection();
		}
		return list;
	}
	
	public ArrayList getBookTypeList() throws Exception {
		DBUtility dbUtility =null;
		ArrayList list = null;
		try{
			dbUtility = new DBUtility();
		dbUtility.createStatement();
		ArrayList ret=dbUtility.executeQuery(SuppleDocSQL.SELECT_BOOK_TYPE_DETAILS);
		
		 list = new ArrayList();

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				SuppleDocDTO dto = new SuppleDocDTO();
				logger.debug("Dist:-" + lst.get(0) + ":" + lst.get(1));
				dto.setBookTypeId((String) lst.get(0));
				dto.setBookTypeName((String) lst.get(1));
				
				list.add(dto);
			}

		}
		}
		catch (Exception e) {
			logger.debug(e.getMessage(),e);
		}
		finally
		{
		dbUtility.closeConnection();
		}
		return list;
	}
	
	
	
	public ArrayList getPropTypeL2List(String propL1Id, String language) throws Exception {
		String[] param={propL1Id};
		DBUtility dbUtility = null;
		ArrayList ret = null;
		try
		{
		dbUtility = new DBUtility();
		if(language.equalsIgnoreCase("en"))
		{
		dbUtility.createPreparedStatement(SuppleDocSQL.SELECT_PROPERTY_TYPE_L2_DETAILS);
		}
		else
		{
			dbUtility.createPreparedStatement(SuppleDocSQL.SELECT_PROPERTY_TYPE_L2_DETAILS_HINDI);
		}
		 ret=dbUtility.executeQuery(param);
		}
		catch (Exception e) {
			logger.debug(e.getMessage(),e);
		}
		finally
		{
		dbUtility.closeConnection();
		}
		if(ret!=null){
		return ret;
		}
		else{
			return new ArrayList();
		}
		
	}
	
	public ArrayList getUnitList(String propL1Id, String language) throws Exception {
		String[] param={propL1Id};
		DBUtility dbUtility = null;
		ArrayList ret = null;
		try{
		dbUtility = new DBUtility();
		
		if(language.equalsIgnoreCase("en"))
		{
		dbUtility.createPreparedStatement(SuppleDocSQL.SELECT_UNIT_LIST_DETAILS);
		}
		else
		{
			dbUtility.createPreparedStatement(SuppleDocSQL.SELECT_UNIT_LIST_DETAILS_HINDI);
		}	
		 ret=dbUtility.executeQuery(param);
		}
		catch (Exception e) {
			logger.debug(e.getMessage(),e);
		}
		finally
		{
			dbUtility.closeConnection();
		}
	
		if(ret!=null){
		return ret;
		}
		else{
			return new ArrayList();
		}
	}

	public String getUserName(String userId) {
		String username="";
		DBUtility dbUtility = null;
		try {
			ArrayList list=new ArrayList();
			 dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(SuppleDocSQL.SELECT_USERNAME_DETAILS);
			String arr[] = new String[1];
			arr[0] = userId;
			list=dbUtility.executeQuery(arr);
			if(list.size()>0)
			{
			ArrayList list1 = (ArrayList) list.get(0);
			
			if(list1.get(1)==null||String.valueOf(list1.get(1)).equalsIgnoreCase(""))
			{
				username = String.valueOf(list1.get(0))+" "+String.valueOf(list1.get(2))+","+list1.get(3);
				
				
			}
			else
			{
				username = String.valueOf(list1.get(0))+" " +String.valueOf(list1.get(1))+ " "+String.valueOf(list1.get(2))+","+list1.get(3);;
			} 
			}
			else
			{
				username="";
			}
		} catch (Exception e) {
		
			logger.error(e.getMessage());
		}
		finally
		{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return username;
	}

	public String getSROName(String officeId, String language) {
		String officesId="";
		ArrayList list1= null;
		DBUtility dbutil = null;
				try {
					ArrayList list=new ArrayList();
					dbutil	 = new DBUtility();
					if(language.equalsIgnoreCase("en"))
					dbutil.createPreparedStatement(SuppleDocSQL.SELECT_OFFICE_DETAILS);
					else
						dbutil.createPreparedStatement(SuppleDocSQL.SELECT_OFFICE_DETAILS_HINDI);
					String arr[] = new String[1];
					arr[0] = officeId;
					list=dbutil.executeQuery(arr);
					if(list.size()>0)
					{
					list1 = (ArrayList)list.get(0);
					officesId = String.valueOf(list1.get(0));
				
					}
					else
					{
						officesId="";
					}
					
					
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
				finally
				{
					try {
						dbutil.closeConnection();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		return officesId;
	}

	public boolean insertLastPageDetailsDAO(String[] param) {
		logger.debug("WE ARE IN DAO-insertLastPageDetailsDAO");
        DBUtility dbUtil=null;
        try {
            dbUtil=new DBUtility();
            boolean flag=false;
            String str1=SuppleDocSQL.IGRS_SUPPLI_UPDATE_LAST_PAGE;
            dbUtil.createPreparedStatement(str1);
            if(dbUtil.executeUpdate(param))
            {
            	
                        	flag= true;
                     
             }
                
            
            if(!flag)
            {
            	dbUtil.rollback();
            }else{
            	dbUtil.commit();
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
	        try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	    }
	    return false;
	}
	

	public boolean insertCompletePropertyDetailsDAO(SuppleDocDTO baseFormDto,
			SuppleDocDTO[] valuesFloor) {
		
		String khasraNo;
		String rinPustika;
		String khasraArea;
		String lagaan;
		String[] khasraNoArr;
		String[] rinPustikaArr;
		String[] khasraAreaArr;
		String[] lagaanArr;
		String[] floorArr=new String[8];
		boolean check=true;
		try {
			DBUtility dbUtil = new DBUtility();
			try {
				dbUtil.setAutoCommit(false);
				String strKhasra = SuppleDocSQL.IGRS_SUPPLI_INSERT_KHASRA_DETAILS;
				String strPropertyDetails = SuppleDocSQL.IGRS_SUPPLI_INSERT_PROPERTY_DETAILS;
				if(valuesFloor.length>0)
				{
					check=true;
					
					String strFloor=SuppleDocSQL.IGRS_SUPPLI_INSERT_FLOOR_DETAILS;
					dbUtil.createPreparedStatement(strFloor);
					
					String param1[] = new String[8];
					
					for(int i =0;i<valuesFloor.length;i++)
					{
						param1[0] = valuesFloor[i].getPropTypeL1Id();
						param1[1] = ""+valuesFloor[i].getPropTypeL2Id();
						param1[2] = ""+valuesFloor[i].getFloorId();
						param1[3] = ""+valuesFloor[i].getAreaConstructed();
						param1[4] = ""+valuesFloor[i].getArea();
						param1[5] = "01";
						param1[6] = ""+baseFormDto.getUserId();
						param1[7] = ""+baseFormDto.getReferenceId();
						
						check = dbUtil.executeUpdate(param1);
						if(!check)
						{
							break;
						}
				
					}

					
				}
				
				
		
				
				String[] khasra = new String[4];
				if(baseFormDto.getKhasraNoArray() != null && baseFormDto.getKhasraAreaArray()!=null
						&& baseFormDto.getLagaanArray()!=null && baseFormDto.getRinPustikaArray()!=null 
						&& !baseFormDto.getKhasraNoArray().equalsIgnoreCase("null") && !baseFormDto.getKhasraAreaArray().equalsIgnoreCase("null")
						&& !baseFormDto.getLagaanArray().equalsIgnoreCase("null") && !baseFormDto.getRinPustikaArray().equalsIgnoreCase("null")
						&& !baseFormDto.getKhasraNoArray().equalsIgnoreCase("") && !baseFormDto.getKhasraAreaArray().equalsIgnoreCase("")
						&& !baseFormDto.getLagaanArray().equalsIgnoreCase("") && !baseFormDto.getRinPustikaArray().equalsIgnoreCase("")) {
			
				
				khasra[0] = baseFormDto.getKhasraNoArray();
				khasra[1] = baseFormDto.getRinPustikaArray();
				khasra[2] = baseFormDto.getKhasraAreaArray();
				khasra[3] = baseFormDto.getLagaanArray();
				}else{
					khasra=null;
				}
				
				logger.debug("i love khasra");
				if(khasra!=null)
				{	
				/*if(!khasra[0].equalsIgnoreCase(""))
				{*/				  
				khasraNoArr  =khasra[0].split(RegInitConstant.SPLIT_CONSTANT_KHASRA);
				rinPustikaArr=khasra[1].split(RegInitConstant.SPLIT_CONSTANT_KHASRA);
				khasraAreaArr=khasra[2].split(RegInitConstant.SPLIT_CONSTANT_KHASRA);
				lagaanArr    =khasra[3].split(RegInitConstant.SPLIT_CONSTANT_KHASRA);
				/*}else{
					khasraNoArr=new String[0];
					rinPustikaArr=new String[0];
					khasraAreaArr=new String[0];
					lagaanArr=new String[0];
				}*/
				String[] khasraParam=new String[6];
				
				for(int i=0;i<khasraNoArr.length;i++)
				{
					
				    
					khasraParam[0]=baseFormDto.getReferenceId();
					
					khasraParam[1]=khasraNoArr[i].trim();
					khasraParam[2]=rinPustikaArr[i].trim();
					khasraParam[3]=khasraAreaArr[i].trim();
					khasraParam[4]=lagaanArr[i].trim();
					khasraParam[5]=baseFormDto.getUserId();               //CREATED BY
					
					if(check)
					{
					String sql = SuppleDocSQL.INSERT_SUPPLI_PROP_KHASRA_DETLS;
					dbUtil.createPreparedStatement(sql);
				check=dbUtil.executeUpdate(khasraParam);
				
					}
				}
				
				}
				if(check)
				{
				
				
				
				dbUtil.createPreparedStatement(strPropertyDetails);
				String property[] = new String[25];
				
				property[0] = baseFormDto.getReferenceId();
				property[1] = baseFormDto.getVikasKhand();
				property[2] = baseFormDto.getRicircle();
				property[3] = baseFormDto.getLayoutdet();
				property[4] = baseFormDto.getSheetnum();
				property[5] = baseFormDto.getPlotnum();
				property[6] = baseFormDto.getEast();
				property[7] = baseFormDto.getWest();
				property[8] = baseFormDto.getNorth();
				property[9] = baseFormDto.getSouth();
				property[10] = baseFormDto.getAreaId();
				String[] subarea = baseFormDto.getSubAreaId().split("~");
				property[11] = subarea[0];//Change for government/Subarea
				property[12] = baseFormDto.getPropertyTypeId();
				if(valuesFloor.length>0)
				{	property[13] = "0";
				property[14] = "";
				property[15] = "0";
				property[16] = ""+"0";
				}
				
				else
				{
				property[13] = baseFormDto.getPropTypeL1Id();
				property[14] = baseFormDto.getPropTypeL2Id();
				property[15] = baseFormDto.getUnitTypeId();
				property[16] = ""+baseFormDto.getArea();
				}
				
				property[17] = baseFormDto.getDistrictIDProperty();
				property[18] = baseFormDto.getTehsilID();
				if(baseFormDto.getWardPatwariId().equalsIgnoreCase("P"))
				{
				property[19] = "1";
			}
			else
			{
				property[19] = "2";
			}
				String colony[] = baseFormDto.getColonyId().split("~");
				property[20] = colony[0]; //Mahalla / Colony
				property[21] = baseFormDto.getUserId();
				property[22] = baseFormDto.getPropAddress();
				property[23] = baseFormDto.getPropLandmark();
				String partwari[] = baseFormDto.getWardIds().split("~");
				property[24] = partwari[0];//Patwari Halwa Ya Ward
				
			check=	dbUtil.executeUpdate(property);
				
				}
				
				if(check)
				{
				
				String updatePage = SuppleDocSQL.IGRS_SUPPLI_UPDATE_PARTIAL3;
				dbUtil.createPreparedStatement(updatePage);
				
				String param1[] = new String[4];
				param1[0] ="P";
				param1[1] ="3";
				param1[2] = baseFormDto.getUserId();
				param1[3] = baseFormDto.getReferenceId();
				
				check = dbUtil.executeUpdate(param1);
				}
				if(check)
				{
				dbUtil.commit();
				return check;
				}
				else
				{
					dbUtil.rollback();
					return check;
				}
		 
				}catch (Exception e) {
				e.printStackTrace();
				dbUtil.rollback();
				return check;
			} finally {
				dbUtil.closeConnection();
			}
		} catch (Exception e) {
			return check;
		}
		
		
		
		
		
			}

	 public ArrayList getPendingDetls(String uid, String officeId) throws Exception{
			String[] tid = new String[4];
			tid[0]=uid;
			tid[1]="P";
			tid[2]="R";
			tid[3]=officeId;
		DBUtility dbUtil = null;
			ArrayList list = new ArrayList();
			try{
				dbUtil = new DBUtility();
				dbUtil.createPreparedStatement(SuppleDocSQL.PENDING_DETL);
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

	public ArrayList getDtoDataDAO(String refernceid) throws Exception{
		DBUtility dbUtil=null;
		ArrayList list = new ArrayList();
		try{
			
			dbUtil = new DBUtility();
			String param[] = new String[1];
			param[0] = refernceid;
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(SuppleDocSQL.IGRS_SUPPLI_SELECT_STAMPDUTY);
		    list=dbUtil.executeQuery(param);
		    list.trimToSize();
		} catch(Exception e){
			   e.printStackTrace();
			   dbUtil.rollback();
			logger.error(" Exception in getDtoDaoDtls " + e);
			
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

	public ArrayList getDistrictIdDAO(String refernceid) throws Exception {
		DBUtility dbUtil=null;
		ArrayList list = new ArrayList();
		try{
			
			dbUtil = new DBUtility();
			String param[] = new String[1];
			param[0] = refernceid;
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(SuppleDocSQL.IGRS_SUPPLI_SELECT_DISTRICT_ID);
		    list=dbUtil.executeQuery(param);
		    list.trimToSize();
		} catch(Exception e){
			   e.printStackTrace();
			   dbUtil.rollback();
			logger.error(" Exception in getDistrictDtls " + e);
			
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
		
	
	


public String getDistrictName(String id, String langauge) throws Exception
{
	String param[]= new String[1];
	param[0]=id;
	DBUtility util = new DBUtility();
	String sql = "";
	if(langauge.equalsIgnoreCase("en"))
	sql=SuppleDocSQL.IGRS_SUPPLI_GET_DISTRICT_NAME;
	else
		sql=SuppleDocSQL.IGRS_SUPPLI_GET_DISTRICT_NAME_HINDI;	
	ArrayList districtName=null;
	util.createPreparedStatement(sql);
	try {
		
	ArrayList list = 	util.executeQuery(param);
	util.closeConnection();
	if(list!=null)
	{
		districtName = (ArrayList) list.get(0);
		String name = (String)districtName.get(0);
		return name;
	}
	} catch (Exception e) {
		
	}
	
	return null;
}


public String getTehsilName(String id, String language) throws Exception
{
	String param[]= new String[1];
	param[0]=id;
	DBUtility util = new DBUtility();
	String sql="";
	if(language.equalsIgnoreCase("en"))
	{
		sql=SuppleDocSQL.IGRS_SUPPLI_GET_TEHSIL_NAME;
	}
		else
	{
			sql=SuppleDocSQL.IGRS_SUPPLI_GET_TEHSIL_NAME_HINDI;
	}
	ArrayList tehsilName=null;
	util.createPreparedStatement(sql);
	try {
		
	ArrayList list = 	util.executeQuery(param);
	util.closeConnection();
	if(list!=null)
	{
		tehsilName = (ArrayList) list.get(0);
		String name = (String)tehsilName.get(0);
		return name;
	}
	} catch (Exception e) {
		logger.debug(e.getMessage(),e);
	}
	finally
	{
		util.closeConnection();
	}
	return null;
}

public String getPropertyTypeName(String id, String language) throws Exception
{
	String param[]= new String[1];
	param[0]=id;
	DBUtility util = new DBUtility();
	String sql="";
	
	if(language.equalsIgnoreCase("en"))
	sql=SuppleDocSQL.IGRS_SUPPLI_GET_PROPERTY_TYPE_NAME;
	else
		sql=SuppleDocSQL.IGRS_SUPPLI_GET_PROPERTY_TYPE_NAME_HINDI;
	
	ArrayList propertyTypeName=null;
	util.createPreparedStatement(sql);
	try {
		
	ArrayList list = 	util.executeQuery(param);
	util.closeConnection(); 
	if(list!=null)
	{
		propertyTypeName = (ArrayList) list.get(0);
		String name = (String)propertyTypeName.get(0);
		return name;
	}
	} catch (Exception e) {
		logger.debug(e.getMessage(),e);
	}
	finally
	{
		util.closeConnection();
	}
	
	return null;
}

public String getAreaTypeName(String id, String language) throws Exception
{
	String param[]= new String[1];
	param[0]=id;
	DBUtility util = new DBUtility();
	String sql="";
	if(language.equalsIgnoreCase("en"))
	{
		sql=SuppleDocSQL.IGRS_SUPPLI_GET_AREA_TYPE_NAME;
	}
	else
	{
		sql=SuppleDocSQL.IGRS_SUPPLI_GET_AREA_TYPE_NAME_HINDI;
	}
	ArrayList AreaTypeName=null;
	util.createPreparedStatement(sql);
	try {
		
	ArrayList list = 	util.executeQuery(param);
	util.closeConnection();
	if(list!=null)
	{
		AreaTypeName = (ArrayList) list.get(0);
		String name = (String)AreaTypeName.get(0);
		return name;
	}
	} catch (Exception e) {
		logger.debug(e.getMessage(),e);
	}
	finally
	{
		util.closeConnection();
	}
	
	return null;
}

public String getMunicipalBodyName(String id, String language) throws Exception
{
	String param[]= new String[1];
	param[0]=id;
	DBUtility util = new DBUtility();
	String sql = "";
	
	if(language.equalsIgnoreCase("en"))
	{
	sql=SuppleDocSQL.IGRS_SUPPLI_GET_MUNICIPAL_BODY_NAME;
	}
	else
	{
		sql=SuppleDocSQL.IGRS_SUPPLI_GET_MUNICIPAL_BODY_NAME_HINDI;	
	}
	ArrayList MunicipalBodyName=null;
	util.createPreparedStatement(sql);
	try {
		
	ArrayList list = 	util.executeQuery(param);
	util.closeConnection();
	if(list!=null)
	{
		MunicipalBodyName = (ArrayList) list.get(0);
		String name = (String)MunicipalBodyName.get(0);
		return name;
	}
	} catch (Exception e) {
		logger.debug(e.getMessage(),e);
	}
	finally
	{
		util.closeConnection();
	}
	
	return null;
}

public String getMohallaName(String id, String language) throws Exception
{
	String param[]= new String[1];
	param[0]=id;
	DBUtility util = new DBUtility();
	String sql="";
	
	if(language.equalsIgnoreCase("en"))
	{
	sql=SuppleDocSQL.IGRS_SUPPLI_GET_MOHALLA_NAME;
	}
	else
	{
		sql=SuppleDocSQL.IGRS_SUPPLI_GET_MOHALLA_NAME_HINDI;
	}
	ArrayList mohallaName=null;
	util.createPreparedStatement(sql);
	try {
		
	ArrayList list = 	util.executeQuery(param);
	util.closeConnection();
	if(list!=null)
	{
		mohallaName = (ArrayList) list.get(0);
		String name = (String)mohallaName.get(0);
		return name;
	}
	} catch (Exception e) {
		logger.debug(e.getMessage(),e);
	}
	finally
	{
		util.closeConnection();
	}
	return null;
}

public String getPatwariWardName(String id, String language) throws Exception
{
	String param[]= new String[1];
	param[0]=id;
	DBUtility util = new DBUtility();
	String sql="";
	
	if(language.equalsIgnoreCase("en"))
	{
	sql=SuppleDocSQL.IGRS_SUPPLI_GET_PW_NAME;
	}
	else
	{
		sql=SuppleDocSQL.IGRS_SUPPLI_GET_PW_NAME_HINDI;
	}
	ArrayList pwName=null;
	util.createPreparedStatement(sql);
	try {
		
	ArrayList list = 	util.executeQuery(param);
	util.closeConnection();
	if(list!=null)
	{
		pwName = (ArrayList) list.get(0);
		String name = (String)pwName.get(0);
		return name;
	}
	} catch (Exception e) {
		logger.debug(e.getMessage(),e);
	}
	finally
	{
		util.closeConnection();
	}
	
	return null;
}

 public ArrayList getTotalPartyDetailsDAO(String param[])throws ServletException,IOException,SQLException,Exception{
    	logger.debug("WE ARE IN DAO-getTotalPartyDetailsDAO");
        DBUtility dbUtil=null;
        ArrayList typeList=null;
        try {
            dbUtil=new DBUtility();
            String str=SuppleDocSQL.IGRS_SUPPLI_VIEW_ENTIRE_PARTY_DETAILS;
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
 

 public ArrayList getPropDetlsForDisplay(String referenceId) throws Exception {
		ArrayList propDetls=new ArrayList();
		DBUtility dbUtility =null;
		try 
		{
			String param[] = new String[1];
			param[0]= referenceId;
			String SQL =  SuppleDocSQL.GET_PROP_DETLS_DISPLAY;
			 dbUtility = new DBUtility();
			
			dbUtility.createPreparedStatement(SQL);
			
			propDetls = dbUtility.executeQuery(param);
			
			
		} catch (Exception exception) {
			logger.debug("Exception in getPropDetlsForDisplay" + exception);
		}
		finally
		{
			dbUtility.closeConnection();
		}
		return propDetls;

	}
 
 
 public ArrayList getPropFloorDetlsForDisplay(String referenceId,String language) throws Exception {
		ArrayList propDetls=new ArrayList();
		DBUtility dbUtility = null;
		try 
		{
			String param[] = new String[1];
			param[0]= referenceId;
			
			String SQL =  "";
			if(language.equalsIgnoreCase("en"))
				SQL = SuppleDocSQL.GET_FLOOR_DETLS;
			else
				SQL = SuppleDocSQL.GET_FLOOR_DETLS_HINDI;
			dbUtility = new DBUtility();
			
			dbUtility.createPreparedStatement(SQL);
			
			propDetls = dbUtility.executeQuery(param);
			
		} catch (Exception exception) {
			logger.debug("Exception in getPropDetlsForDisplay" + exception);
		}
		finally
		{
			dbUtility.closeConnection();
		}
		return propDetls;

	}
 
 public ArrayList getPropKhasraDetlsForDisplay(String referenceId) throws Exception {
		ArrayList propDetls=new ArrayList();
		DBUtility dbUtility =null;
		try {
			
			String param[] = new String[1];
			String SQL = SuppleDocSQL.GET_PROP_KHASRA_DETLS_DISPLAY;
			param[0]= referenceId;
		
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(SQL);
			
			propDetls = dbUtility.executeQuery(param);
			
		} catch (Exception exception) {
			logger.debug("Exception in getPropKhasraDetlsForDisplay" + exception);
		}
		finally
		{
			dbUtility.closeConnection();
		}
		return propDetls;

	}

public String getReferenceId(String filingnuber) throws Exception {
	String param[]= new String[1];
	param[0]=filingnuber;
	DBUtility util = new DBUtility();
	String sql=SuppleDocSQL.IGRS_SUPPLI_GET_REFERENCE_ID;
	ArrayList pwName=null;
	util.createPreparedStatement(sql);
	try {
		
	ArrayList list = 	util.executeQuery(param);
	util.closeConnection();
	if(list!=null)
	{
		pwName = (ArrayList) list.get(0);
		String name = (String)pwName.get(0);
		return name;
	}
	} catch (Exception e) {
		logger.debug(e.getMessage(),e);
	}
	finally
	{
		util.closeConnection();
	}
	return null;
}

public boolean updateFilingNumberDAOs(String filingNo) {
	
	
	logger.debug("WE ARE IN DAO-UpdateFilingNumberPageDetailsDAO");
    DBUtility dbUtil=null;
    String ar[] = new String[2];
    ar[1]=filingNo;
    ar[0]= "Consumed";
   
    try {
        dbUtil=new DBUtility();
        boolean flag=false;
        String str1=SuppleDocSQL.IGRS_ESTAMP_UPDATE_CONSUME;
        dbUtil.createPreparedStatement(str1);
        if(dbUtil.executeUpdate(ar))
        {
        	
                    	flag= true;
                 
         }
            
        
        if(!flag)
        {
        	dbUtil.rollback();
        }else{
        	dbUtil.commit();
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
        try {
			dbUtil.closeConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }
    return false;
	
}




public boolean updateFilingNumberDAO(String referId, String remarks, String filingNo) {
	
	
	logger.debug("WE ARE IN DAO-UpdateFilingNumberPageDetailsDAO");
    DBUtility dbUtil=null;
    String ar[] = new String[3];
    ar[0]=filingNo;
    ar[1]= remarks;
    ar[2] = referId;
    try {
        dbUtil=new DBUtility();
        boolean flag=false;
        String str1=SuppleDocSQL.IGRS_SUPPLI_UPDATE_REFERENCE_PAGE;
        dbUtil.createPreparedStatement(str1);
        if(dbUtil.executeUpdate(ar))
        {
        	
                    	flag= true;
                 
         }
            
        
        if(!flag)
        {
        	dbUtil.rollback();
        }else{
        	dbUtil.commit();
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
        try {
			dbUtil.closeConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }
    return false;
	
}

public boolean setStatusDao(String estampDuty) {
	DBUtility dbUtil = null;
	String param[] = new String[1];
	param[0] = estampDuty;
	try {
		dbUtil = new DBUtility();
		dbUtil.createPreparedStatement(SuppleDocSQL.IGRS_ESTAMP_UPDATE_CONSUME);
		dbUtil.executeUpdate(param);
		
		
	} catch (Exception e) {
	
		logger.debug(e.getMessage());
	}
	finally
	{
		try {
			dbUtil.closeConnection();
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
		}
	}
	return true;
	
}
 
public void setStatusDaoN(String estampDuty) {
	DBUtility dbUtil = null;
	String param[] = new String[1];
	param[0] = estampDuty;
	try {
		dbUtil = new DBUtility();
		dbUtil.createPreparedStatement(SuppleDocSQL.IGRS_ESTAMP_UPDATE_NOT_CONSUME);
		dbUtil.executeUpdate(param);
		
		
	} catch (Exception e) {
	
		logger.debug(e.getMessage());
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


public synchronized ArrayList getFloor(String l1ID, String language) throws Exception {
	if (logger.isDebugEnabled()) {
		logger.debug("getUsedPlot(String) - start");
	}
DBUtility dbUtil = null;
	ArrayList list = new ArrayList();
	try {
		dbUtil = new DBUtility();
		//dbUtil.createPreparedStatement(CommonSQL.FLOOR_MASTER_QUERY);
		if(language.equalsIgnoreCase("en"))
		dbUtil.createPreparedStatement(SuppleDocSQL.FLOOR_MASTER_QUERY);
		else
			dbUtil.createPreparedStatement(SuppleDocSQL.FLOOR_MASTER_QUERY_HINDI);
		String[] param = new String[1];
		param[0] = l1ID;
		list = dbUtil.executeQuery(param);
	} catch (Exception x) {
		logger.debug(x);
	} finally {
		if (dbUtil != null) {
			dbUtil.closeConnection();
		}
	}
	if (logger.isDebugEnabled()) {
		logger.debug("getUsedPlot(String) - end");
	}
	return list;

}
public String getMunicipalId(String subAreaId) {
	String name="";
	DBUtility dbUtil=null;
	try {
		dbUtil = new DBUtility();
		
			dbUtil.createPreparedStatement(SuppleDocSQL.GET_MUNICIPAL_ID);	
		
		name=dbUtil.executeQry(new String[]{subAreaId});
		return name;
	
} catch (Exception e) {
	logger.error(e);
	return null;
	
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

public ArrayList getWardPatwari(String language, String subAreaId,
		String tehsilId) {
		ArrayList wardPatwariList=null;
		DBUtility dbUtil= null;
	try {
		dbUtil = new DBUtility();
		if(language.equalsIgnoreCase("en"))
		{	
			dbUtil.createPreparedStatement(SuppleDocSQL.WARD_PATWARI_NAME_EN);
		}
		else
		{
			dbUtil.createPreparedStatement(SuppleDocSQL.WARD_PATWARI_NAME_HI);	
		}
		wardPatwariList=dbUtil.executeQuery(new String[]{subAreaId,tehsilId});
		return wardPatwariList;
		
	} catch (Exception e) {
		logger.error(e);
		return null;
	}
	finally
	{
		try {
			dbUtil.closeConnection();
		} catch (Exception e) {
			
			logger.debug(e.getMessage(),e);
		}
	}
}


}