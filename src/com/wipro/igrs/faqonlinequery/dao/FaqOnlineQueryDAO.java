package com.wipro.igrs.faqonlinequery.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeMap;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import com.wipro.igrs.copyissuance.util.CommonUtil;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.faqonlinequery.dto.FaqOnlineQueryDTO;
import com.wipro.igrs.faqonlinequery.sql.FaqOnlineQuerySQL;
import com.wipro.igrs.guideline.dao.GuideLineViewDAO;

public class FaqOnlineQueryDAO {
	 private static Logger log = org.apache.log4j.Logger.getLogger(FaqOnlineQueryDAO.class);

	   

	    /**
	     * <p>Constructor for the class</p>
	     */
	    private Logger logger = (Logger) Logger
		.getLogger(GuideLineViewDAO.class);
	    public FaqOnlineQueryDAO() throws Exception {
	        //dbUtil = new DBUtility();

	    }
public ArrayList getFAQDetails(String language)
{
	ArrayList list =new ArrayList();
	DBUtility dbUtil=null;
	try {
		String query="";
		dbUtil = new DBUtility();
		dbUtil.createStatement();
		if("en".equalsIgnoreCase(language))
		{	
		query=FaqOnlineQuerySQL.GET_FAQ_DETAILS_ENGLISH;
		}
		else if("hi".equalsIgnoreCase(language))
		{
			query=FaqOnlineQuerySQL.GET_FAQ_DETAILS_HINDI;
		}
		list=dbUtil.executeQuery(query);
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			dbUtil.closeConnection();
		} catch (Exception e) {
			//e.printStackTrace();
			logger.debug(e);
		}
	}
	return list;
}
public ArrayList  countryDAO(String language) throws ServletException,IOException,SQLException,Exception {
    
	ArrayList  ar1=new ArrayList ();
    DBUtility dbUtil=null;
    log.debug("WE ARE IN DAO-countryStack");
    try {
        ArrayList typeList=new ArrayList();
        ArrayList typeTemp=new ArrayList();
       
        dbUtil=new DBUtility();
        String str=FaqOnlineQuerySQL.IGRS_COUNTRY_MASTER_HINDI; // Query for Country list from IGRS_COUNTRY_MASTER
        dbUtil.createStatement();
        typeList=dbUtil.executeQuery(str);
        log.info("COUNTRY_typeList:="+typeList);
        if(typeList!=null){
            for(int i=0;i<typeList.size();i++) {
                typeTemp=new ArrayList();
                typeTemp=(ArrayList)typeList.get(i);
                if(typeTemp.size()>0){
                	FaqOnlineQueryDTO dto = new FaqOnlineQueryDTO();
                	dto.setCountryId((String)typeTemp.get(0));
                	if("en".equalsIgnoreCase(language))
                	{	
                	dto.setCountryName((String)typeTemp.get(1));
                	}
                	else if("hi".equalsIgnoreCase(language))
                	{
                		dto.setCountryName((String)typeTemp.get(2));
                	}
                	ar1.add(dto);
                }
            }
        }
    }catch(IOException ie){
    	ie.printStackTrace();
    }
    catch(SQLException se){
    	se.printStackTrace();
    }
    catch(Exception e){
    	e.printStackTrace();
    }
    finally {
		try {
			dbUtil.closeConnection();
		} catch (Exception e) {
		//	e.printStackTrace();
			logger.debug(e);
		}
	}
    return ar1;
}

public ArrayList  stateDAO(String _countryIdVar,String language)throws ServletException,IOException,SQLException,Exception{
    ArrayList  ar1=new ArrayList ();
    DBUtility dbUtil=null;
    log.debug("WE ARE IN DAO-stateStack");
    try {
        ArrayList typeList=new ArrayList();
        ArrayList typeTemp=new ArrayList();
       
        dbUtil=new DBUtility();
        String str=FaqOnlineQuerySQL.IGRS_STATE_MASTER_HINDI; // Query for state list from IGRS_STATEMASTER
        String param[]=new String[1];
        param[0]=""+_countryIdVar;
        dbUtil.createPreparedStatement(str);
        typeList=dbUtil.executeQuery(param);
        log.info("STATE_typeList:="+typeList);
        if(typeList!=null){
            for(int i=0;i<typeList.size();i++) {
                typeTemp=new ArrayList();
                typeTemp=(ArrayList)typeList.get(i);
                if(typeTemp.size()>0){
                	FaqOnlineQueryDTO dto = new FaqOnlineQueryDTO();
                	dto.setStateId((String)typeTemp.get(0));
                	if("en".equalsIgnoreCase(language))
                	{
                	dto.setStateName((String)typeTemp.get(1));
                	}
                	else if("hi".equalsIgnoreCase(language))
                	{
                		dto.setStateName((String)typeTemp.get(2));
                	}
                	ar1.add(dto);
                }
            }
        }
    }catch(IOException ie){
    	log.error(ie);
    }
    catch(SQLException se){
    	log.error(se);
    }
    catch(Exception e){
    	log.error(e);
    }
    finally {
		try {
			dbUtil.closeConnection();
		} catch (Exception e) {
			//e.printStackTrace();
			logger.debug(e);
		}
	}
    return ar1;
}
public ArrayList  districtDAO(String _stateIdVar,String language)throws ServletException,IOException,SQLException,Exception{
    
    ArrayList  ar1=new ArrayList ();
    DBUtility dbUtil=null;
    log.debug("WE ARE IN DAO-districtStack");
    try {
        ArrayList typeList=new ArrayList();
        ArrayList typeTemp=new ArrayList();
       
        dbUtil=new DBUtility();
        String str=FaqOnlineQuerySQL.IGRS_DISTRICT_MASTER_HINDI; // Query for district list from IGRS_DISTRICT_MASTER
        String param[]=new String[1];
        param[0]=""+_stateIdVar;
        dbUtil.createPreparedStatement(str);
        typeList=dbUtil.executeQuery(param);
        log.info("DISTRICT_typeList:="+typeList);
        if(typeList!=null){
            for(int i=0;i<typeList.size();i++) {
                typeTemp=new ArrayList();
                typeTemp=(ArrayList)typeList.get(i);
                if(typeTemp.size()>0){
                	FaqOnlineQueryDTO dto = new FaqOnlineQueryDTO();
                	dto.setDistrictId((String)typeTemp.get(0));
                	if("en".equalsIgnoreCase(language))
                	{
                	dto.setDistrictName((String)typeTemp.get(1));
                	}
                	else if("hi".equalsIgnoreCase(language))
                	{
                		dto.setDistrictName((String)typeTemp.get(2));
                	}
                	ar1.add(dto);
                }
            }
        }
    }catch(IOException ie){
    	log.error(ie);
    }
    catch(SQLException se){
    	log.error(se);
    }
    catch(Exception e){
    	log.error(e);
    }
    finally {
		try {
			dbUtil.closeConnection();
		} catch (Exception e) {
			//e.printStackTrace();
			logger.debug(e);
		}
	}
    return ar1;
}
public String submitQueryDetails(FaqOnlineQueryDTO faqDTO) throws Exception
{
	String id=certifiedIDgenerator();
	boolean flag=false;
	DBUtility dbUtil=null;
	try
	{
		dbUtil=new DBUtility();
		String param[]={id,
				faqDTO.getName(),faqDTO.getAddress(),faqDTO.getEmail(),
				faqDTO.getCountryId(),faqDTO.getStateId(),faqDTO.getDistrictId(),
				faqDTO.getContactNumber(),faqDTO.getMobileNumber(),faqDTO.getQuery(),
				"A",faqDTO.getName()};
		String query=FaqOnlineQuerySQL.SUBMIT_QUERY;
		dbUtil.createPreparedStatement(query);
		
		flag= dbUtil.executeUpdate(param);
		if(flag)
		{
			query=FaqOnlineQuerySQL.EMAIL_DATA;
			dbUtil.createPreparedStatement(query);
			String param1[]={faqDTO.getEmail(),"QUERY:"+id,"Your refernce number is "+id+" . Your Query is under observation."};
			flag= dbUtil.executeUpdate(param1);
		}
	
	}catch(SQLException e)
	{
		log.error(e);
	}
	finally {
		try {
			dbUtil.closeConnection();
		} catch (Exception e) {
		//	e.printStackTrace();
			logger.debug(e);
		}
	}
	if(flag==true)
	{
	return id;
	}
	return "";
}
private String certifiedIDgenerator() {
    String id = new CommonUtil().getAutoId("IGRS_ONLINE_QUERY_DETAILS","QUERY_TXN_ID","Q","");
   return id;
}
public ArrayList replyDashBoard()
{
	ArrayList list =new ArrayList();
	DBUtility dbUtil=null;
	try {
		dbUtil=new DBUtility();
		dbUtil.createStatement();
		String query=FaqOnlineQuerySQL.GET_REPLY_DAHBOARD;
		list=dbUtil.executeQuery(query);
	} catch (Exception e) {
		e.printStackTrace();
	}
	finally {
		try {
			dbUtil.closeConnection();
		} catch (Exception e) {
		//	e.printStackTrace();
			logger.debug(e);
		}
	}
	
	return list;
}
public ArrayList searchDashBoard(FaqOnlineQueryDTO faqDTO)
{
	DBUtility dbUtil=null;
	ArrayList listFromDb = null;
	try {
		
		String param[]=null;
		dbUtil  =  new DBUtility();
		String sql=FaqOnlineQuerySQL.GET_SEARCH_DAHBOARD;
		TreeMap<String, String> paramsMap = new TreeMap<String, String>();
		if(faqDTO.getQueryTxnid()!=null&&!faqDTO.getQueryTxnid().equalsIgnoreCase(""))
		{
			
			paramsMap.put("1txnID", faqDTO.getQueryTxnid());
			sql=sql+" AND QUERY_TXN_ID=?";
		}
		if(faqDTO.getStatus()!=null&&!faqDTO.getStatus().equalsIgnoreCase(""))
		{
			paramsMap.put("2Status", faqDTO.getStatus());
			sql=sql+" AND UPPER(STATUS)=?";
		}
		if( faqDTO.getFromDate()!=null&&!faqDTO.getFromDate().equalsIgnoreCase("")&&faqDTO.getToDate()!=null&&!faqDTO.getToDate().equalsIgnoreCase(""))
		{
			sql=sql+" AND CREATED_DATE  BETWEEN TO_DATE(?,'DD/MM/YYYY') AND TO_DATE(?,'DD/MM/YYYY')+1";
			paramsMap.put("3fromDate", faqDTO.getFromDate());
			paramsMap.put("4toDate", faqDTO.getToDate());
		}
		sql=sql+" order by CREATED_DATE desc";
		dbUtil.createPreparedStatement(sql);
		Collection<String> values = paramsMap.values();
		ArrayList<String> tmp = new ArrayList(values);
//		Collections.reverse(tmp);
		String[] params = tmp.toArray(new String[]{}); 
		listFromDb  =  dbUtil.executeQuery(params);
		log.debug("size is"+listFromDb.size());
	}catch(Exception e)
	{
		log.debug(e);
	}
	finally {
		try {
			dbUtil.closeConnection();
		} catch (Exception e) {
		//	e.printStackTrace();
			logger.debug(e);
		}
	}
	return listFromDb;
	
}
public ArrayList replyDetails(String queryId)
{
	ArrayList list =new ArrayList();
	DBUtility dbUtil=null;
	try {
		dbUtil=new DBUtility();
		String query=FaqOnlineQuerySQL.GET_REPLY_DETAILS_HINDI;
		dbUtil.createPreparedStatement(query);
		list=dbUtil.executeQuery(new String[]{queryId});
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	finally {
		try {
			dbUtil.closeConnection();
		} catch (Exception e) {
		//	e.printStackTrace();
			logger.debug(e);
		}
	}
	
	return list;
}
public boolean updateQueryDetails(FaqOnlineQueryDTO faqDTO) throws Exception
{
	boolean flag=false;
	DBUtility dbUtil=null;
	try
	{
		dbUtil=new DBUtility();
		String param[]={faqDTO.getReply(),
				faqDTO.getUserID(),faqDTO.getQueryTxnid()};
		String query=FaqOnlineQuerySQL.UPDATE_REPLY;
		dbUtil.createPreparedStatement(query);
		
		flag= dbUtil.executeUpdate(param);
		if(flag)
		{
			query=FaqOnlineQuerySQL.EMAIL_DATA;
			dbUtil.createPreparedStatement(query);
			String param1[]={faqDTO.getEmail(),"QUERY:"+faqDTO.getQueryTxnid(),"Your Query with Refernce number is "+faqDTO.getQueryTxnid()+" is resolved.\n Resolution:"+ faqDTO.getReply()};
			flag= dbUtil.executeUpdate(param1);
		}
	
	}catch(SQLException e)
	{
		log.error(e);
	}
	finally {
		try {
			dbUtil.closeConnection();
		} catch (Exception e) {
			//e.printStackTrace();
			logger.debug(e);
		}
	}
	return flag;
}
}
