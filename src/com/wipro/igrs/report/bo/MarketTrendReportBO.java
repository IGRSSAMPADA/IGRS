/* 
 * <Copyright information>
 *
 * <Customer specific copyright notice (if any) >
 *
 * ==============================================================================
 * This file contains proprietary information of Wipro Infotech Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007
 * ==============================================================================
 * 
 * File Name	   		: ReportBD.java
 *
 * Description	   		: This class interacts with DbService for data 
 * 							persistance and data fetch
 *
 * Version Number  		: 0.0 
 *
 * Created Date	   		: 30 04 2008 
 *
 * Modification History	: Created
 */

package com.wipro.igrs.report.bo;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.newPropvaluation.dto.PropertyValuationDTO;
import com.wipro.igrs.report.dao.ReportDAO;
import com.wipro.igrs.report.dto.MISReportDTO;
import com.wipro.igrs.report.dto.MarketTrendReportDTO;
import com.wipro.igrs.report.dto.ReportingDTO;
import com.wipro.igrs.report.sql.CommonSQL;

public class MarketTrendReportBO {
	/**
     * ===========================================================================
     * Method         :   getDistrictDetails()
     * Description    :   Retrieving values from database 
     * Arguments      :   ReportForm reportForm 
     * return type    :   List
     * Author         :   Madan Mohan
     * Created Date   :   Apr 30, 2008
     * @param reportForm description
     * ===========================================================================
     */
	private static final Logger logger = Logger
	.getLogger(MarketTrendReportBO.class);
	
	private IGRSCommon common ;
	
	public ArrayList getDistrictDetails(){
		ReportDAO dao = new ReportDAO();        
		ArrayList returnList=new ArrayList();
	    try{
	    	IGRSCommon common = new IGRSCommon();
	    	ArrayList resultList = common.getDistrict("1");
	    	if(resultList!=null){
                for(int i = 0;i<resultList.size();i++){
                	logger.debug("in bd for loop start:-" +resultList.get(i));
                	ArrayList list = (ArrayList)resultList.get(i);
                	MarketTrendReportDTO reportDTO = new MarketTrendReportDTO();	
                	reportDTO.setDistId((String)list.get(0));
                	reportDTO.setDistrictName((String)list.get(1));
                	returnList.add(reportDTO);
                	logger.debug( reportDTO.getDistrictId()+":"+reportDTO.getDistrictName());
                }            
			 }
	    }
	    catch(Exception err){
			
	        logger.debug("In getDistrictDetails Exception "+err);
	    }
	    return returnList;
	}
	/**
     * ===========================================================================
     * Method         :   getTehsilTypeDetails()
     * Description    :   Retrieving values from database 
     * Arguments      :   ReportForm reportForm 
     * return type    :   List
     * Author         :   Madan Mohan
     * Created Date   :   May 12, 2008
     * @param reportForm description
     * ===========================================================================
     */
	public ArrayList getTehsilTypeDetails(MarketTrendReportDTO dto){
		String districtId = 
			dto.getDistrictId();
		logger.debug("districtId:-"+districtId);
		ReportDAO dao = new ReportDAO();     
		
		ArrayList returnList=new ArrayList();
	    try{
	    	logger.debug("In getTehsilTypeDetails BD start");	
	    	IGRSCommon common = new IGRSCommon();
	    	
	    	ArrayList resultList = common.getThesil(districtId);
	    		 
	    	if(resultList!=null){
	    		logger.debug("in bd if block start");	
                for(int i = 0;i<resultList.size();i++){
                	logger.debug("in bd for loop start:-" 
                			+resultList.get(i));
                	ArrayList list = (ArrayList)resultList.get(i);
                	String offTypeId = (String) list.get(0);
                	MarketTrendReportDTO reportDTO = new MarketTrendReportDTO();	
                	reportDTO.setTehsilId((String)list.get(0));
                	reportDTO.setTehsilName((String)list.get(1));
                	returnList.add(reportDTO);
                	logger.debug(reportDTO.getTehsilId()+":"+reportDTO.getTehsilName());               	
                	 
                } 
			 }
	    	logger.debug("In getTehsilTypeDetails BD TryBlock end ");
	    }
	    catch(Exception err){
			
	        logger.debug("In getTehsilTypeDetails Exception "+err);
	    }
	    return returnList;
	}
	/**
     * ===========================================================================
     * Method         :   getAreaTypeDetails()
     * Description    :   Retrieving values from database 
     * Arguments      :   ReportForm reportForm 
     * return type    :   List
     * Author         :   Madan Mohan
     * Created Date   :   May 12, 2008
     * @param reportForm description
     * ===========================================================================
     */
	public ArrayList getAreaTypeDetails(){
		ReportDAO dao = new ReportDAO();        
		ArrayList returnList=new ArrayList();
	    try{
	    	IGRSCommon common = new IGRSCommon();
	    	ArrayList resultList = common.getAreaType();
	    	if(resultList!=null){
                for(int i = 0;i<resultList.size();i++){
                	logger.debug("in bd for loop start:-" +resultList.get(i));
                	ArrayList list = (ArrayList)resultList.get(i);
                	MarketTrendReportDTO reportDTO = new MarketTrendReportDTO();	
                	reportDTO.setAreaTypeId((String)list.get(0));
                	reportDTO.setAreaTypeName((String)list.get(1));
                	returnList.add(reportDTO);
                	logger.debug( reportDTO.getAreaTypeId()
                			+":"+reportDTO.getAreaTypeName());
                }            
			 }
	    }
	    catch(Exception err){
			
	        logger.debug("In getAreaTypeDetails Exception "+err);
	    }
	    return returnList;
	}
	
	/**
     * ===========================================================================
     * Method         :   getWardDetails()
     * Description    :   Retrieving values from database 
     * Arguments      :   ReportForm reportForm 
     * return type    :   List
     * Author         :   Madan Mohan
     * Created Date   :   May 12, 2008
     * @param reportForm description
     * ===========================================================================
     */
	public ArrayList getWardDetails(MarketTrendReportDTO dto){
		ReportDAO dao = new ReportDAO();        
		ArrayList returnList=new ArrayList();
	    try{
	    	IGRSCommon common = new IGRSCommon();
	    	ArrayList resultList = common.getWard(dto.getTehsilId(),
	    										  dto.getAreaTypeId(),
	    										   dto.getAreaType());
	    	if(resultList!=null){
                for(int i = 0;i<resultList.size();i++){
                	ArrayList list = (ArrayList)resultList.get(i);
                	MarketTrendReportDTO reportDTO = new MarketTrendReportDTO();	
                	reportDTO.setWardPatwariId((String)list.get(0));
                	reportDTO.setWardPatwariName((String)list.get(1));
                	returnList.add(reportDTO);
                	logger.debug(reportDTO.getWardPatwariId()+":"+reportDTO.getWardPatwariName());
                }            
			 }
	    	logger.debug("In getWardDetails BD TryBlock end ");
	    }
	    catch(Exception err){
			
	        logger.debug("In getWardDetails Exception "+err);
	    }
	    return returnList;
	}
	
	 
	/**
     * ===========================================================================
     * Method         :   getPatwariDetails()
     * Description    :   Retrieving values from database 
     * Arguments      :   ReportForm reportForm 
     * return type    :   List
     * Author         :   Madan Mohan
     * Created Date   :   May 12, 2008
     * @param reportForm description
     * ===========================================================================
     */
	public ArrayList getMohallaDetails(MarketTrendReportDTO dto){
		ReportDAO dao = new ReportDAO();        
		ArrayList returnList=new ArrayList();
	    try{
	    	logger.debug("In getPatwariDetails BD start");	  
	    	IGRSCommon common = new IGRSCommon();
	    	ArrayList resultList = common.getMahalla(dto.getWardPatwariId());
	    	if(resultList!=null){
                for(int i = 0;i<resultList.size();i++){
                	ArrayList list = (ArrayList)resultList.get(i);
                	MarketTrendReportDTO reportDTO = new MarketTrendReportDTO();	
                	reportDTO.setMohallaId((String)list.get(0));
                	reportDTO.setMohallaName((String)list.get(1));
                	returnList.add(reportDTO);
                	logger.debug(reportDTO.getWardPatwariId()
                			+":"+reportDTO.getWardPatwariName());
                }            
			 }
	    }
	    catch(Exception err){
			
	        logger.debug("In getPatwariDetails Exception "+err);
	    }
	    return returnList;
	}
	
	 
	/**
     * ===========================================================================
     * Method         :   getOfficeTypeDetails()
     * Description    :   Retrieving values from database 
     * Arguments      :   ReportForm reportForm 
     * return type    :   List
     * Author         :   Madan Mohan
     * Created Date   :   Apr 30, 2008
     * @param reportForm description
     * ===========================================================================
     */
	/*public ArrayList getOfficeTypeDetails(ReportForm reportForm){
		String districtId = 
			reportForm.getReportDTO().getDistrictId();
		logger.debug("districtId:-"+districtId);
		ReportDAO dao = new ReportDAO();        
		ArrayList returnList=new ArrayList();
	    try{
	    	logger.debug("In getOfficeTypeDetails BD start");	    		  
	    	ArrayList resultList = 
	    		dao.getOfficeTypeDetails(reportForm);
	    	if(resultList!=null){
	    		logger.debug("in bd if block start");	
                for(int i = 0;i<resultList.size();i++){
                	logger.debug("in bd for loop start:-" 
                			+resultList.get(i));
                	ArrayList list = (ArrayList)resultList.get(i);
                	String offTypeId = (String) list.get(0);
                	ReportDTO reportDTO = new ReportDTO();	
                	if(districtId.equalsIgnoreCase("DIST-001") 
                			&& offTypeId.equalsIgnoreCase("HO")){
                		reportDTO.setOfficeTypeId((String)list.get(0));             		
                	}
                	else{
                		if(!(districtId.equalsIgnoreCase("DIST-001")) 
                				&& offTypeId.equalsIgnoreCase("HO")){
                			continue;
                		}
                		else{
                			reportDTO.setOfficeTypeId((String)list.get(0));
                		}
                	}                	
                	returnList.add(reportDTO);
                	logger.debug("in bd for loop end,OffType.Id:-"
                			+reportDTO.getOfficeTypeId());
                } 
                logger.debug("in bd if block end ");               
			 }
	    	logger.debug("In getOfficeTypeDetails BD TryBlock end ");
	    }
	    catch(Exception err){
			
	        logger.debug("In getOfficeTypeDetails Exception "+err);
	    }
	    return returnList;
	}*/
	/**
     * ===========================================================================
     * Method         :   getOfficeNameDetails()
     * Description    :   Retrieving values from database 
     * Arguments      :   ReportForm reportForm 
     * return type    :   List
     * Author         :   Madan Mohan
     * Created Date   :   Apr 30, 2008
     * @param reportForm description
     * ===========================================================================
     */
	public ArrayList getOfficeNameDetails(MarketTrendReportDTO dto){
		ReportDAO dao = new ReportDAO();        
		ArrayList returnList = new ArrayList();
	    try{
	    	logger.debug("In getOfficeNameDetails BD start");	
	    	String[] param = new String[2];
	    	param[0] = dto.getUserType();
	    	param[1] = "A";
	    	String SQL = CommonSQL.OFFICE_NAME_DETAILS_I;
	    	
	    	ArrayList resultList = dao.getOfficeNameDetails(param,SQL);
	    	if(resultList!=null){
                for(int i = 0;i<resultList.size();i++){
                	ArrayList list = (ArrayList)resultList.get(i);
                	MarketTrendReportDTO reportDTO = new MarketTrendReportDTO();	
                	reportDTO.setOfficeId((String)list.get(0));
                	reportDTO.setOfficeName((String)list.get(1));
                	returnList.add(reportDTO);
                	logger.debug(reportDTO.getOfficeName()+":"+reportDTO.getOfficeId());
                } 
			 }
	    }
	    catch(Exception err){
			
	        logger.debug("In getOfficeNameDetails Exception "+err);
	    }
	    return returnList;
	}
	/**
     * ===========================================================================
   
	/**
	 * @param dto
	 * @return
	 */
	public ArrayList getMarketTrendReport(MarketTrendReportDTO dto) {
		ArrayList list = new ArrayList();
		String[] param = null;
		String SQL ="";
		
		if(dto != null) {
			
			
			String officeID = dto.getOfficeId();
			String periodMonth = dto.getSearchType();
			String userType = dto.getUserType();
			logger.debug("periodMonth:-"+periodMonth);
			if("M".equals(periodMonth)) {
				SQL = CommonSQL.MARKET_TREND_QUERY_MONTH_I;
				
				if("SRO".equals(userType) || "DRO".equals(userType)) {
					SQL = SQL + CommonSQL.MARKET_TREND_QUERY_MONTH_OFFICE_I
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_II
					    + CommonSQL.MARKET_TREND_QUERY_MONTH_III
					    + CommonSQL.MARKET_TREND_QUERY_MONTH_OFFICE_III
					    + CommonSQL.MARKET_TREND_QUERY_MONTH_IV
					    + CommonSQL.MARKET_TREND_QUERY_MONTH_V
					    + CommonSQL.MARKET_TREND_QUERY_MONTH_OFFICE_V
					    + CommonSQL.MARKET_TREND_QUERY_MONTH_VI
					    + CommonSQL.MARKET_TREND_QUERY_MONTH_VII
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_OFFICE_VII
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_VIII
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_IX
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_OFFICE_IX
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_X
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_OFFICE_X;
					
					if(!dto.getBookTypeId().trim().equals("")) {
						SQL = SQL +  CommonSQL.BOOK_TYPE_ID +"'"+ dto.getBookTypeId()+"'";
					}
					   SQL = SQL + CommonSQL.MARKET_TREND_QUERY_MONTH_XI;
					 
					param = new String[18];
					for(int i = 0; i < param.length ;) {
						param[i] = officeID;
						param[i+1] = userType;
						param[i+2] = dto.getMonth();
						 
						i+=3;
					}
					
					logger.debug("Office SQL:-"+SQL);
				}
				if("A".equals(userType)) {
					String districtId = dto.getDistrictId()==null 
										? "" : dto.getDistrictId();
					String tehsilId = dto.getTehsilId()==null 
										? "" :dto.getTehsilId();
					String wardId = dto.getWardPatwariId()==null 
										? "" :dto.getWardPatwariId();
					String mohallaId = dto.getMohallaId()==null 
										? "" :dto.getMohallaId();
					
					logger.debug("Month:-"+dto.getMonth());
					
					if( "".equals(districtId.trim()) && 
							"".equals(tehsilId.trim()) &&
							"".equals(wardId.trim()) &&
							"".equals(mohallaId.trim())) {
						
						SQL = SQL + CommonSQL.MARKET_TREND_QUERY_MONTH_II
							+ CommonSQL.MARKET_TREND_QUERY_MONTH_III
							+ CommonSQL.MARKET_TREND_QUERY_MONTH_IV
							+ CommonSQL.MARKET_TREND_QUERY_MONTH_V
							+ CommonSQL.MARKET_TREND_QUERY_MONTH_VI
							+ CommonSQL.MARKET_TREND_QUERY_MONTH_VII
							+ CommonSQL.MARKET_TREND_QUERY_MONTH_VIII
							+ CommonSQL.MARKET_TREND_QUERY_MONTH_IX
							+ CommonSQL.MARKET_TREND_QUERY_MONTH_X ;
						if(!dto.getBookTypeId().trim().equals("")) {
							SQL = SQL +  CommonSQL.BOOK_TYPE_ID +"'"+ dto.getBookTypeId()+"'";
						}
						SQL = SQL + CommonSQL.MARKET_TREND_QUERY_MONTH_XI;
						
						param = new String[6];
						for(int i = 0; i<param.length; i++) {
							param[i] = dto.getMonth();
							
						}
						logger.debug("ALL:-"+SQL);
					}else if(!"".equals(districtId.trim()) &&
							"".equals(tehsilId.trim()) &&
							"".equals(wardId.trim()) &&
							"".equals(mohallaId.trim())) {
						
						 
						SQL = SQL + CommonSQL.MARKET_TREND_QUERY_MONTH_DISTRICT_I 
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_II
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_III
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_DISTRICT_III
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_IV
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_V
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_DISTRICT_V
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_VI
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_VII
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_DISTRICT_VII
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_VIII
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_IX
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_DISTRICT_IX
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_X
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_DISTRICT_X;
						
						if(!dto.getBookTypeId().trim().equals("")) {
							SQL = SQL +  CommonSQL.BOOK_TYPE_ID +"'"+ dto.getBookTypeId()+"'";
						}
						SQL = SQL + CommonSQL.MARKET_TREND_QUERY_MONTH_XI;
						
						param = new String[12];
						for(int i = 0; i<param.length; ) {
							param[i] = districtId;
							param[i+1] = dto.getMonth();
							 
							i+=2;
						}
						logger.debug("District :-"+SQL);
					}
					else if(!"".equals(districtId.trim()) &&
							!"".equals(tehsilId.trim()) &&
							"".equals(wardId.trim()) &&
							"".equals(mohallaId.trim())) {
						 
						SQL = SQL + CommonSQL.MARKET_TREND_QUERY_MONTH_DISTRICT_I 
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_THESIL_I
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_II
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_III
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_DISTRICT_III
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_THESIL_III
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_IV
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_V
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_DISTRICT_V
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_THESIL_V
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_VI
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_VII
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_DISTRICT_VII
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_THESIL_VII
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_VIII
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_IX
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_DISTRICT_IX
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_THESIL_IX
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_X
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_DISTRICT_X
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_THESIL_X;
						if(!dto.getBookTypeId().trim().equals("")) {
							SQL = SQL +  CommonSQL.BOOK_TYPE_ID +"'"+ dto.getBookTypeId()+"'";
						}
						SQL = SQL + CommonSQL.MARKET_TREND_QUERY_MONTH_XI;
						
						param = new String[18];
						for(int i = 0; i<param.length; ) {
							param[i] = districtId;
							param[i+1] = tehsilId;
							param[i+2] = dto.getMonth();
							 
							i+=3;
						}
						
						logger.debug("Tehsil:-"+SQL);
					}
					else if(!"".equals(districtId.trim()) &&
							!"".equals(tehsilId.trim()) &&
							!"".equals(wardId.trim()) &&
							"".equals(mohallaId.trim())) {
						 
						SQL = SQL + CommonSQL.MARKET_TREND_QUERY_MONTH_DISTRICT_I 
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_THESIL_I
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_WARD_I
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_II
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_III
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_DISTRICT_III
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_THESIL_III
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_WARD_III
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_IV
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_V
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_DISTRICT_V
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_THESIL_V
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_WARD_V
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_VI
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_VII
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_DISTRICT_VII
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_THESIL_VII
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_WARD_VII
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_VIII
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_IX
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_DISTRICT_IX
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_THESIL_IX
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_WARD_IX
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_X
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_DISTRICT_X
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_THESIL_X
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_WARD_X;
						if(!dto.getBookTypeId().trim().equals("")) {
							SQL = SQL +  CommonSQL.BOOK_TYPE_ID +"'"+ dto.getBookTypeId()+"'";
						}
						SQL = SQL + CommonSQL.MARKET_TREND_QUERY_MONTH_XI;
						
						logger.debug("Ward :-"+SQL);
						
						param = new String[24];
						
						for(int i = 0; i<param.length; ) {
							param[i] = districtId;
							param[i+1] = tehsilId;
							param[i+2] = wardId;
							param[i+3] = dto.getMonth();
							 
							i+=4;
						}
					}
					else if(!"".equals(districtId.trim()) &&
							!"".equals(tehsilId.trim()) &&
							!"".equals(wardId.trim()) &&
							!"".equals(mohallaId.trim())) {
						 
						SQL = SQL + CommonSQL.MARKET_TREND_QUERY_MONTH_DISTRICT_I 
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_THESIL_I
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_WARD_I
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_MOHALLA_I
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_II
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_III
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_DISTRICT_III
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_THESIL_III
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_WARD_III
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_MOHALLA_III
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_IV
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_V
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_DISTRICT_V
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_THESIL_V
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_WARD_V
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_MOHALLA_V
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_VI
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_VII
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_DISTRICT_VII
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_THESIL_VII
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_WARD_VII
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_MOHALLA_VII
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_VIII
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_IX
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_DISTRICT_IX
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_THESIL_IX
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_WARD_IX
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_MOHALLA_IX
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_X
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_DISTRICT_X
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_THESIL_X
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_WARD_X
						+ CommonSQL.MARKET_TREND_QUERY_MONTH_MOHALLA_X;
						if(!dto.getBookTypeId().trim().equals("")) {
							SQL = SQL +  CommonSQL.BOOK_TYPE_ID +"'"+ dto.getBookTypeId()+"'";
						}
						SQL = SQL+ CommonSQL.MARKET_TREND_QUERY_MONTH_XI;
						
						logger.debug("Mohalla :-"+SQL);
						
						param = new String[30];
						
						/*for(int i = 0; i<param.length; ) {
							param[i] = districtId;
							param[i+1] = tehsilId;
							param[i+2] = wardId;
							param[i+3] = mohallaId;
							param[i+4] = dto.getMonth();
							 
							i+=5;
						} */
					}
					 
				}
			}
			
			
			if("P".equals(periodMonth)) {
				SQL = CommonSQL.MARKET_TREND_QUERY_PERIOD_I;
				
				if("SRO".equals(userType) || "DRO".equals(userType)) {
					 
					SQL = SQL + CommonSQL.MARKET_TREND_QUERY_PERIOD_OFFICE_I
					+ CommonSQL.MARKET_TREND_QUERY_PERIOD_II
					+ CommonSQL.MARKET_TREND_QUERY_PERIOD_OFFICE_II
					+ CommonSQL.MARKET_TREND_QUERY_PERIOD_III
					+ CommonSQL.MARKET_TREND_QUERY_PERIOD_OFFICE_III
					+ CommonSQL.MARKET_TREND_QUERY_PERIOD_IV
					+ CommonSQL.MARKET_TREND_QUERY_PERIOD_OFFICE_IV
					+ CommonSQL.MARKET_TREND_QUERY_PERIOD_V
					+ CommonSQL.MARKET_TREND_QUERY_PERIOD_OFFICE_V
					+ CommonSQL.MARKET_TREND_QUERY_PERIOD_VI
					+ CommonSQL.MARKET_TREND_QUERY_PERIOD_OFFICE_VI;
					if(!dto.getBookTypeId().trim().equals("")) {
						SQL = SQL +  CommonSQL.BOOK_TYPE_ID +"'"+ dto.getBookTypeId()+"' ";
					}
					SQL = SQL + CommonSQL.MARKET_TREND_QUERY_PERIOD_VII;
					 
					
					
					param = new String[24];
					for(int i = 0; i < param.length ;) {
						param[i] = officeID;
						param[i+1] = userType;
						param[i+2] = dto.getFromDate();
						param[i+3] = dto.getToDate();
						i+=4;
					}
					
					logger.debug("Office SQL:-"+SQL);
				}
				if("A".equals(userType)) {
					String districtId = dto.getDistrictId()==null 
										? "" : dto.getDistrictId();
					String tehsilId = dto.getTehsilId()==null 
										? "" :dto.getTehsilId();
					String wardId = dto.getWardPatwariId()==null 
										? "" :dto.getWardPatwariId();
					String mohallaId = dto.getMohallaId()==null 
										? "" :dto.getMohallaId();
					
					if( 	"".equals(districtId.trim()) && 
							"".equals(tehsilId.trim()) &&
							"".equals(wardId.trim()) &&
							"".equals(mohallaId.trim())) {
						
					 
						SQL = SQL + CommonSQL.MARKET_TREND_QUERY_PERIOD_II
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_III
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_IV
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_V
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_VI;
						if(!dto.getBookTypeId().trim().equals("")) {
							SQL = SQL +  CommonSQL.BOOK_TYPE_ID +"'"+ dto.getBookTypeId()+"'";
						}
						SQL = SQL+ CommonSQL.MARKET_TREND_QUERY_PERIOD_VIII;
						 
						
						param = new String[12];
						for(int i = 0; i<param.length; ) {
							param[i] = dto.getFromDate();
							param[i+1] = dto.getToDate();
							i+=2;
						}
						logger.debug("ALL:-"+SQL);
					}else if(!"".equals(districtId.trim()) &&
							"".equals(tehsilId.trim()) &&
							"".equals(wardId.trim()) &&
							"".equals(mohallaId.trim())) {
						
						SQL = SQL 
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_DISTRICT_I
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_II
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_DISTRICT_II
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_III
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_DISTRICT_III
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_IV
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_DISTRICT_IV
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_V
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_DISTRICT_V
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_VI
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_DISTRICT_VI;
						if(!dto.getBookTypeId().trim().equals("")) {
							SQL = SQL +  CommonSQL.BOOK_TYPE_ID +"'"+ dto.getBookTypeId()+"'";
						}
						SQL = SQL + CommonSQL.MARKET_TREND_QUERY_PERIOD_VII ;
						
						param = new String[18];
						for(int i = 0; i<param.length; ) {
							param[i] = districtId;
							param[i+1] = dto.getFromDate();
							param[i+2] = dto.getToDate(); 
							i+=3;
						}
						logger.debug("District :-"+SQL);
					}
					else if(!"".equals(districtId.trim()) &&
							!"".equals(tehsilId.trim()) &&
							"".equals(wardId.trim()) &&
							"".equals(mohallaId.trim())) {
						 
						 
						SQL = SQL 
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_DISTRICT_I
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_TEHSIL_I
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_II
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_DISTRICT_II
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_TEHSIL_II
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_III
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_DISTRICT_III
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_TEHSIL_III
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_IV
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_DISTRICT_IV
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_TEHSIL_IV
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_V
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_DISTRICT_V
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_TEHSIL_V
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_VI
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_DISTRICT_VI
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_TEHSIL_VI;
						if(!dto.getBookTypeId().trim().equals("")) {
							SQL = SQL +  CommonSQL.BOOK_TYPE_ID +"'"+ dto.getBookTypeId()+"'";
						}
						SQL = SQL + CommonSQL.MARKET_TREND_QUERY_PERIOD_VII ;
						
						
						param = new String[24];
						for(int i = 0; i<param.length; ) {
							param[i] = districtId;
							param[i+1] = tehsilId;
							param[i+2] = dto.getFromDate();
							param[i+3] = dto.getToDate(); 
							i+=4;
						}
						
						logger.debug("Tehsil:-"+SQL);
					}
					else if(!"".equals(districtId.trim()) &&
							!"".equals(tehsilId.trim()) &&
							!"".equals(wardId.trim()) &&
							"".equals(mohallaId.trim())) {
						 
						SQL = SQL 
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_DISTRICT_I
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_TEHSIL_I
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_WARD_I
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_II
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_DISTRICT_II
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_TEHSIL_II
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_WARD_II
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_III
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_DISTRICT_III
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_TEHSIL_III
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_WARD_III
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_IV
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_DISTRICT_IV
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_TEHSIL_IV
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_WARD_IV
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_V
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_DISTRICT_V
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_TEHSIL_V
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_WARD_V
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_VI
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_DISTRICT_VI
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_TEHSIL_VI
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_WARD_VI;
						if(!dto.getBookTypeId().trim().equals("")) {
							SQL = SQL +  CommonSQL.BOOK_TYPE_ID +"'"+ dto.getBookTypeId()+"'";
						}
						SQL = SQL+ CommonSQL.MARKET_TREND_QUERY_PERIOD_VII ;
						
						logger.debug("Ward :-"+SQL);
						
						param = new String[30];
						
						for(int i = 0; i<param.length; ) {
							param[i] = districtId;
							param[i+1] = tehsilId;
							param[i+2] = wardId;
							param[i+3] = dto.getFromDate();
							param[i+4] = dto.getToDate(); 
							i+=5;
						}
					}
					else if(!"".equals(districtId.trim()) &&
							!"".equals(tehsilId.trim()) &&
							!"".equals(wardId.trim()) &&
							!"".equals(mohallaId.trim())) {
						 
						 
						SQL = SQL 
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_DISTRICT_I
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_TEHSIL_I
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_WARD_I
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_MOHALLA_I
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_II
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_DISTRICT_II
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_TEHSIL_II
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_WARD_II
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_MOHALLA_II
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_III
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_DISTRICT_III
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_TEHSIL_III
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_WARD_III
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_MOHALLA_III
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_IV
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_DISTRICT_IV
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_TEHSIL_IV
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_WARD_IV
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_MOHALLA_IV
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_V
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_DISTRICT_V
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_TEHSIL_V
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_WARD_V
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_MOHALLA_V
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_VI
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_DISTRICT_VI
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_TEHSIL_VI
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_WARD_VI
						+ CommonSQL.MARKET_TREND_QUERY_PERIOD_MOHALLA_VI;
						if(!dto.getBookTypeId().trim().equals("")) {
							SQL = SQL +  CommonSQL.BOOK_TYPE_ID +"'"+ dto.getBookTypeId()+"'";
						}
						SQL = SQL + CommonSQL.MARKET_TREND_QUERY_PERIOD_VII ;
						
						logger.debug("Mohalla :-"+SQL);
						
						param = new String[36];
						
						for(int i = 0; i<param.length; ) {
							param[i] = districtId;
							param[i+1] = tehsilId;
							param[i+2] = wardId;
							param[i+3] = mohallaId;
							param[i+4] = dto.getFromDate();
							param[i+5] = dto.getToDate(); 
							 
							i+=6;
						} 
					}
					 
				}
			}
		} 
		
		 
		
		if(param !=null) {
			for(int i = 0; i < param.length ;i++) {
				logger.debug("param["+i+"]"+param[i]);
			}
		}
		ReportDAO dao = new ReportDAO();
		ArrayList retlist = dao.getMarketTrendReport(param, SQL);
		try {
			common = new IGRSCommon();
		}catch(Exception x) {
			logger.debug(x);
		}
		
		
		
		if(retlist != null) {
			for(int i = 0; i < retlist.size(); i++ ) {
				ArrayList listRet = (ArrayList) retlist.get(i);
				MarketTrendReportDTO reportDTO = new MarketTrendReportDTO();
				
				reportDTO.setDeedNameMT((String)listRet.get(1));
				int noOfDocCurr = (String)listRet.get(2) == null ?
							0  : Integer.parseInt((String)listRet.get(2));
				
				reportDTO.setNoOfDocMT(""+noOfDocCurr);
				
				double revenueCurr =(String)listRet.get(3) == null ?
						0.0  : Integer.parseInt((String)listRet.get(3));
				
				reportDTO.setRevenueMT(common.getCurrencyFormat(revenueCurr));
				
				double valuationCurr = (String)listRet.get(4) == null ?
						0.0  : Integer.parseInt((String)listRet.get(4));
				
				logger.debug("valuationCurr:-"+valuationCurr
									+":"+listRet.get(4));
				reportDTO.setValuationMT(
						   common.getCurrencyFormat(valuationCurr));
				
				int noOfDocPrev = (String)listRet.get(6) == null ?
						0  : Integer.parseInt((String)listRet.get(6));
				
				double revenuePrev = (String)listRet.get(7) == null ?
						0.0  : Integer.parseInt((String)listRet.get(7));
				
				
				double valuationPrev =(String)listRet.get(8) == null ?
						0.0  : Integer.parseInt((String)listRet.get(8));
				
				int noOfDoc =  noOfDocCurr - noOfDocPrev ;
				
				double revenue = revenuePrev == 0.0 ? 0.00 :
								((revenueCurr - revenuePrev)/revenuePrev) * 100;
				double valuation = valuationPrev == 0.0 ? 0.00 :
								((valuationCurr - valuationPrev)/valuationPrev) * 100;
					
				reportDTO.setNoOfDocPrevMT(""+noOfDoc);
				reportDTO.setRevenuePrevMT(common.getCurrencyFormat(revenue)+ " %");
				reportDTO.setValuationPrevMT(common.getCurrencyFormat(valuation)+" %");
				list.add(reportDTO);
				
			}
		}
		
		return list;
	}
	/**
	 * @param dto
	 * @return
	 */
	public String[] returnYear(MarketTrendReportDTO dto) {
		String search = dto.getSearchType();
		String[] str = new String[2];
		
		if("M".equals(search)) {
			ReportDAO dao = new ReportDAO();
			ArrayList list = dao.getCurrentYear();
			if(list != null) {
				if(list.size() == 1) {
					ArrayList ret = (ArrayList)list.get(0);
					str[0] = (String) ret.get(0);
					str[1] = (String) ret.get(1);
				}
			}
		}if("P".equals(search)) {
			String fromDate = dto.getFromDate();
			String toDate = dto.getToDate();
			
			str[0] = fromDate.substring(fromDate.length() - 4, 
										fromDate.length());
			str[1] = toDate.substring(toDate.length() - 4, 
										toDate.length());
			
		}
		return str;
	}
	
	public ArrayList getUsageReport(MISReportDTO dto) {
		ArrayList list = new ArrayList();
		
		ReportDAO dao = new ReportDAO();
		
		String searchType = dto.getSearchType();
		String SQL = "";
		String[] param = null;
		if("M".equals(searchType)) {
			SQL = CommonSQL.USAGE_REPORT_MONTH;
			param = new String[1];
			param[0] = dto.getMonth();
		}
		if("P".equals(searchType)) {
			SQL = CommonSQL.USAGE_REPORT_PERIOD;
			param = new String[2];
			param[0] = dto.getFromDate();
			param[1] = dto.getToDate();
		}
		
		ArrayList retList = dao.getUsageReport(param, SQL);
		
		if(retList != null) {
			for(int i = 0; i<retList.size(); i++) {
				ArrayList listRet = (ArrayList) retList.get(i);
				MISReportDTO misDTO = new MISReportDTO();
				misDTO.setFunctionality((String)listRet.get(0));
				misDTO.setNoTimeAccessed((String)listRet.get(1));
				misDTO.setAccessTime((String)listRet.get(2));
				list.add(misDTO);
			}
		}
		
		return list;
	}
	
	
	/*
	 * Method         :   getOfficeNameDetails()
     * Description    :   Retrieving values from database 
     * Arguments      :   ReportForm reportForm 
     * return type    :   List
     * Author         :   Madan
     * Created Date   :   June 03 2008
     * @param reportForm description
     * ===========================================================================
     */
	public ArrayList getOfficeNameDetailsMIS(MISReportDTO dto){
		ReportDAO dao = new ReportDAO();        
		ArrayList returnList = new ArrayList();
	    try{
	    	logger.debug("In getOfficeNameDetails BD start");	    		  
	    	String[] param = new String[2];
	    	param[0] = dto.getUserType();
	    	param[1] = "A";
	    	String SQL = CommonSQL.OFFICE_NAME_DETAILS_I;
	    	
	    	ArrayList resultList = dao.getOfficeNameDetails(param,SQL);
	    	if(resultList!=null){
                for(int i = 0;i<resultList.size();i++){
                	ArrayList list = (ArrayList)resultList.get(i);
                	MISReportDTO reportDTO = new MISReportDTO();	
                	reportDTO.setOfficeId((String)list.get(0));
                	reportDTO.setOfficeName((String)list.get(1));
                	returnList.add(reportDTO);
                	logger.debug(reportDTO.getOfficeName()+":"+reportDTO.getOfficeId());
                } 
			 }
	    }
	    catch(Exception err){
			
	        logger.debug("In getOfficeNameDetails Exception "+err);
	    }
	    return returnList;
	}
	public ArrayList getDistrictDetailsMIS(){
		ReportDAO dao = new ReportDAO();        
		ArrayList returnList=new ArrayList();
	    try{
	    	IGRSCommon common = new IGRSCommon();
	    	ArrayList resultList = common.getDistrict("MP");
	    	if(resultList!=null){
                for(int i = 0;i<resultList.size();i++){
                	logger.debug("in bd for loop start:-" +resultList.get(i));
                	ArrayList list = (ArrayList)resultList.get(i);
                	MISReportDTO reportDTO = new MISReportDTO();	
                	reportDTO.setDistrictID((String)list.get(0));
                	reportDTO.setDistrictName((String)list.get(1));
                	returnList.add(reportDTO);
                	logger.debug( reportDTO.getDistrictID()+":"+reportDTO.getDistrictName());
                }            
			 }
	    }
	    catch(Exception err){
			
	        logger.debug("In getDistrictDetails Exception "+err);
	    }
	    return returnList;
	}
	/*public static void main(String [] arg) {
		ReportBD bd = new ReportBD();
		MarketTrendReportDTO dto = new MarketTrendReportDTO();
		dto.setSearchType("P");
		dto.setMonth("June");
		dto.setUserType("A");
		dto.setOfficeId("SRO-01");
		dto.setFromDate("03/04/2008");
		dto.setToDate("05/04/2008");
		dto.setDistrictId("DD");
		dto.setTehsilId("TT");
		dto.setWardPatwariId("WW");
		dto.setMohallaId("MM");
		ArrayList list = bd.getMarketTrendReport(dto);
	}*/
	/**
	 * getting book type id
	 */
	public ArrayList getBookTypeList() 
	{
		ReportDAO dao = new ReportDAO();        
		ArrayList returnList=new ArrayList();
	    try{
	    	ArrayList resultList = dao.getBookTypeList();
	    	if(resultList!=null){
                for(int i = 0;i<resultList.size();i++){
                	logger.debug("in bd for loop start:-" +resultList.get(i));
                	ArrayList list = (ArrayList)resultList.get(i);
                	MarketTrendReportDTO reportDTO = new MarketTrendReportDTO();	
                	reportDTO.setBookId((String)list.get(0));
                	reportDTO.setBookName((String)list.get(1));
                	returnList.add(reportDTO);
                	logger.debug( reportDTO.getDistrictId()+":"+reportDTO.getDistrictName());
                }            
			 }
	    }
	    catch(Exception err){
			
	        logger.debug("In getDistrictDetails Exception "+err);
	    }
	    return returnList;
	}
	
	/**
	 * @return
	 * @throws Exception
	 */
	public ArrayList getAreaType() throws Exception {
		ArrayList areaTypeList = new ArrayList();
		IGRSCommon common = new IGRSCommon();
		try {
			ArrayList tmpList = common.getAreaType();
			for (int i = 0; i < tmpList.size(); i++) {
				ArrayList tmpsubList = (ArrayList) tmpList.get(i);
				if (tmpsubList != null) {
					MarketTrendReportDTO dto = new MarketTrendReportDTO();
					dto.setAreaTypeId((String) tmpsubList.get(0));
					dto.setAreaTypeName((String) tmpsubList.get(1));
					areaTypeList.add(dto);
				}
			}
			logger.info("getPartyDetails-->" + areaTypeList);
		} catch (Exception e) {
			logger.error(e);

		}
		return areaTypeList;
	}
	public String getConfigyear() {
		ReportDAO dao = new ReportDAO();
	String year="";
	year=dao.getConfigYear();
	return year;
	}
	
	public ArrayList getYearForJudicalStamp(int iYear) {

		String str = "";
		ArrayList listRet = new ArrayList();

		ReportDAO dao = new ReportDAO();
		ArrayList list = dao.getCurrentYear();
		if (list != null) {
			if (list.size() == 1) {
				ArrayList ret = (ArrayList) list.get(0);
				str = (String) ret.get(0);

			}
		}
		for (int i = 0; i < iYear; i++) {
			int Year = Integer.parseInt(str) - i;
			MarketTrendReportDTO dto = new MarketTrendReportDTO();
			int YearPlus = Year + 1;
			dto.setYearValue(""+Year);
			dto.setYear("" + Year+"-"+YearPlus);
			listRet.add(dto);
		}
		return listRet;
	}
	/**
	 * @param _distId
	 * @return
	 * @throws Exception
	 */
	public ArrayList getTehisil(String _distId) throws Exception {
		ArrayList tehsilList = new ArrayList();
		IGRSCommon common = new IGRSCommon();
		try {
			ArrayList tmpList = common.getThesil(_distId);
			for (int i = 0; i < tmpList.size(); i++) {
				ArrayList tmpsubList = (ArrayList) tmpList.get(i);
				if (tmpsubList != null) {
					MarketTrendReportDTO dto = new MarketTrendReportDTO();
					dto.setTehisilId((String) tmpsubList.get(0));
					dto.setTehsilName((String) tmpsubList.get(1));
					tehsilList.add(dto);
				}

			}
			logger.info("getPartyDetails-->" + tehsilList);
		} catch (Exception e) {
			logger.error(e);

		}
		return tehsilList;
	}
	
	  /**
		 * @param _areaTypeId
		 * @return
		 * @throws Exception
		 */
		// String[] _areaTypeId
		public ArrayList getWardOrPatwari(MarketTrendReportDTO _refdto)
				throws Exception {
			ArrayList wardList = new ArrayList();
			IGRSCommon common = new IGRSCommon();
			try {
				String[] _ward = new String[3];
				logger.debug("_refdto.getTehsilId()--->"+ _refdto.getTehisilId());
				logger.debug("_refdto.getAreaTypeId()--->"+ _refdto.getAreaTypeId());
				logger.debug("_refdto.getWardId()----->"+ _refdto.getWardpatwarId());
				_ward[0] = _refdto.getTehisilId();
				_ward[1] = _refdto.getAreaTypeId();
				_ward[2] = _refdto.getWardpatwarId();
				ArrayList tmpList = common.getWardOrPatwari(_ward,"");// _areaTypeId
				for (int i = 0; i < tmpList.size(); i++) {
					ArrayList tmpsubList = (ArrayList) tmpList.get(i);
					if (tmpsubList != null) {
						MarketTrendReportDTO dto = new MarketTrendReportDTO();
						dto.setWardId((String) tmpsubList.get(0));// for ward
																	// number
						dto.setWardPatwariName((String) tmpsubList.get(1));// for ward name
						wardList.add(dto);
					}
				}
				logger.info("getPartyDetails-->" + wardList);
			} catch (Exception e) {
				logger.error(e);

			}
			return wardList;
		}
		
		/**
		 * @param _wardRpatwariId
		 * @return
		 * @throws Exception
		 */
		public ArrayList getMohallaOrVillage(MarketTrendReportDTO _refdto)
				throws Exception {
			ArrayList mohallaList = new ArrayList();
			IGRSCommon common = new IGRSCommon();
			try {
				String[] _mohalla = new String[1];
			
				if (_refdto.getWardId() != null) {
					_mohalla[0] = _refdto.getWardId();
				}
			
				ArrayList tmpList = common.getMohallaOrVillage(_mohalla,"");
				for (int i = 0; i < tmpList.size(); i++) {
					ArrayList tmpsubList = (ArrayList) tmpList.get(i);
					if (tmpsubList != null) {
						MarketTrendReportDTO dto = new MarketTrendReportDTO();
						dto.setMohallaId((String) tmpsubList.get(0));// for ward
																		// number
						dto.setMohallaName((String) tmpsubList.get(1));// for ward
																		// name
						mohallaList.add(dto);
					}
				}
				logger.info("getMohalla-->" + mohallaList);
			} catch (Exception e) {
				logger.error(e);
			}
			return mohallaList;
		}
		
		/**
		 *
		 * @return
		 * @throws Exception
		 */
		public ArrayList getDeedTypeList() throws Exception {
			ArrayList tehsilList = new ArrayList();
			ReportDAO dao = new ReportDAO();    
			try {
				ArrayList tmpList = dao.getDeedTypeList();
				for (int i = 0; i < tmpList.size(); i++) {
					ArrayList tmpsubList = (ArrayList) tmpList.get(i);
					if (tmpsubList != null) {
						MarketTrendReportDTO dto = new MarketTrendReportDTO();
						dto.setDeedId((String) tmpsubList.get(0));
						dto.setDeedTypeName((String) tmpsubList.get(1));
						tehsilList.add(dto);
					}

				}
				logger.info("getPartyDetails-->" + tehsilList);
			} catch (Exception e) {
				logger.error(e);

			}
			return tehsilList;
		}
		
		/**
		 * @param deedID
		 * @return
		 * @throws Exception
		 */
		public ArrayList getdeedReport1(String deedID) throws Exception {
			ArrayList tehsilList = new ArrayList();
			MarketTrendReportDTO dto = new MarketTrendReportDTO();
			ReportDAO dao = new ReportDAO();    
			int b=0;
			int a=0;
			try {
				ArrayList tmpList = dao.getdeedReport1(deedID);
				for (int i = 0; i < tmpList.size(); i++) {
					ArrayList tmpsubList = (ArrayList) tmpList.get(i);
					if (tmpsubList != null) {
				
					dto.setDeedTypeName((String) tmpsubList.get(0));
					dto.setNoOfDoc((String) tmpsubList.get(1));
					String s1=(String) tmpsubList.get(1);
					if(s1==null){
						 a=0;
					}else{
					a=Integer.parseInt(s1);
					}
					int currYear=Math.abs(a);
					String s2=(String) tmpsubList.get(2);
					if(s2==null){
						 b=0;
					}
					else{
					  b=Integer.parseInt(s2);
					 }
					int prvYear=Math.abs(b);
					int compYear=Math.abs(currYear-prvYear);
					
					dto.setNoOfDocComp(String.valueOf(compYear));
					tehsilList.add(dto);
					}
				}
				ArrayList tmpList1 = dao.getdeedReport2(deedID);
				for (int i = 0; i < tmpList1.size(); i++) {
					ArrayList tmpsubList1 = (ArrayList) tmpList1.get(i);
					if (tmpsubList1 != null) {
					
					dto.setRevenue((String) tmpsubList1.get(0));
					dto.setValuation((String) tmpsubList1.get(1));
					tehsilList.add(dto);
					}
				}
				ArrayList tmpList2 = dao.getdeedReport3(deedID);
				for (int i = 0; i < tmpList1.size(); i++) {
					ArrayList tmpsubList1 = (ArrayList) tmpList1.get(i);
					if (tmpsubList1 != null) {
					
					dto.setRevenueComp((String) tmpsubList1.get(0));
					dto.setValuationComp((String) tmpsubList1.get(1));
					tehsilList.add(dto);
					}
				}
				logger.info("getPartyDetails-->" + tehsilList);
			} catch (Exception e) {
				logger.error(e);

			}
			return tehsilList;
		}
		/**
		 * @param _distId
		 * @return
		 * @throws Exception
		 *//*
		public ArrayList getdurReport(String fromYear,String toYear) throws Exception {
			ArrayList tehsilList = new ArrayList();int j=0,i=0;
			
			ReportDAO dao = new ReportDAO();    
			try {
				ArrayList tmpList = dao.getdurReport(fromYear,toYear);
				
					ArrayList tmpsubList = (ArrayList) tmpList.get(0);
					ArrayList tmpsubList1 = (ArrayList) tmpList.get(1);
					if (tmpsubList != null) {
						
							while(j==i && (i <= tmpsubList.size() || j<= tmpsubList1.size())){
								
								MarketTrendReportDTO dto = new MarketTrendReportDTO();
							ArrayList tmpsupersubList = (ArrayList) tmpsubList.get(i);
							if(tmpsupersubList.get(1)==null){
								dto.setNoOfDoc("-");
								dto.setNoOfDocComp("-");
							}
						dto.setDeedTypeName((String) tmpsupersubList.get(0));
						
						dto.setNoOfDoc((String) tmpsupersubList.get(1));
						dto.setNoOfDocComp((String) tmpsupersubList.get(1));
						if(tmpsubList1 != null){
					
							ArrayList tmpsupersubList1 = (ArrayList) tmpsubList1.get(j);
							if(tmpsupersubList1.get(0)==null){
								dto.setRevenue("-");
								dto.setRevenueComp("-");
							}
							if(tmpsupersubList1.get(1)==null){
								dto.setValuation("-");
								dto.setValuationComp("-");
							}
						dto.setRevenue((String) tmpsupersubList1.get(0));
						dto.setValuation((String) tmpsupersubList1.get(1));
						dto.setRevenueComp((String) tmpsupersubList1.get(0));
						dto.setValuationComp((String) tmpsupersubList1.get(1));
						i++;j++;
						tehsilList.add(dto);
						}
					}
					
						
							
					
				}
				
				logger.info("getPartyDetails-->" + tehsilList);
			} catch (Exception e) {
				logger.error(e);

			}
			System.out.println(tehsilList);
			
			return tehsilList;
		}*/
		
		public ArrayList getdurReport(String fromYear,String toYear,String lang) throws Exception {
			ReportDAO dao = new ReportDAO();
			ArrayList returnList = new ArrayList();
			try {
				ArrayList resultList = dao.getdurReport(fromYear,toYear);
				if (resultList != null) {
					for (int i = 0; i < resultList.size(); i++) {
						logger.debug("in bd for loop start:-" + resultList.get(i));
						ArrayList list = (ArrayList) resultList.get(i);
						MarketTrendReportDTO reportDTO = new MarketTrendReportDTO();
						if("en".equalsIgnoreCase(lang))
						reportDTO.setDeedTypeName((String) list.get(0));
						else
						reportDTO.setDeedTypeName((String) list.get(1));
						
						reportDTO.setNoOfDoc((String) list.get(3));
						reportDTO.setRevenue((String)list.get(4));
						reportDTO.setValuation((String)list.get(5));
						reportDTO.setNoOfDocComp((String)list.get(6));
						reportDTO.setRevenueComp((String)list.get(7));
						reportDTO.setValuationComp((String)list.get(8));
						returnList.add(reportDTO);
						
					}
				}
			} catch (Exception err) {

				logger.debug("In getRevenueReceiptReport Exception " + err);
			}
			return returnList;
		}
		public ArrayList getDurDuty(String fromYear,String toYear) throws Exception {
			ArrayList tehsilList = new ArrayList();
			ReportDAO dao = new ReportDAO();    
			try {
				ArrayList tmpList = dao.getdurReport(fromYear,toYear);
				for (int i = 0; i < tmpList.size(); i++) {
					ArrayList tmpsubList = (ArrayList) tmpList.get(i);
					if (tmpsubList != null) {
						MarketTrendReportDTO dto = new MarketTrendReportDTO();
						dto.setDeedTypeName((String) tmpsubList.get(0));
						if( tmpsubList.get(1)==null){
							dto.setNoOfDoc("-");
						}else{
						dto.setNoOfDoc((String) tmpsubList.get(1));		
						}
						if( tmpsubList.get(2)==null){
							dto.setRevenue("-");
						}else{
						dto.setRevenue((String) tmpsubList.get(2));}
						if( tmpsubList.get(3)==null){
							dto.setValuation("-");
						}else{
						dto.setValuation((String) tmpsubList.get(3));}
						if( tmpsubList.get(4)==null){
							dto.setNoOfDocComp("-");
						}else{
						dto.setNoOfDocComp((String) tmpsubList.get(4));}
						if( tmpsubList.get(5)==null){
							dto.setRevenueComp("-");
						}else{
						dto.setRevenueComp((String) tmpsubList.get(5));}
						if( tmpsubList.get(5)==null){
							dto.setValuationComp("-");
						}else{
						dto.setValuationComp((String) tmpsubList.get(6));}
						tehsilList.add(dto);
					}
					
				}
				logger.info("getPartyDetails-->" + tehsilList);
			} catch (Exception e) {
				logger.error(e);

			}
			return tehsilList;
		}
		
		
		/**
		 * @param _distId
		 * @return
		 * @throws Exception
		 */
		public ArrayList getAreaReport(MarketTrendReportDTO mtrDto) throws Exception {
			ArrayList tehsilList = new ArrayList();
			ReportDAO dao = new ReportDAO();    
			try {
				ArrayList tmpList = dao.getAreaReport(mtrDto);
				ArrayList tmpsubList = (ArrayList) tmpList.get(0);
				ArrayList tmpsubList1 = (ArrayList) tmpList.get(1);
				ArrayList tmpsupsubList = (ArrayList) tmpsubList.get(0);
			//	ArrayList tmpsupsubList1 = (ArrayList) tmpsubList1.get(1);
				for (int i = 0; (i < tmpsupsubList.size()||i < tmpsubList1.size()); i++) {
					
					if (tmpsubList != null) {
						ArrayList tmpsupersubList = (ArrayList) tmpsupsubList.get(i);
						ArrayList tmpsupersubList1 = (ArrayList) tmpsubList1.get(i);
						MarketTrendReportDTO dto = new MarketTrendReportDTO();
						dto.setDeedTypeName((String) tmpsupersubList.get(0));
						dto.setNoOfDoc((String) tmpsupersubList.get(1));
						dto.setRevenue((String) tmpsupersubList.get(2));
						dto.setValuation((String) tmpsupersubList.get(3));
						dto.setNoOfDocComp((String) tmpsupersubList1.get(0));
						dto.setRevenueComp((String) tmpsupersubList1.get(1));
						dto.setValuationComp((String) tmpsupersubList1.get(2));
						tehsilList.add(dto);
					}

				}
				
				logger.info("getPartyDetails-->" + tehsilList);
			} catch (Exception e) {
				logger.error(e);

			}
			return tehsilList;
		}
		
		/**
		 * @param _distId
		 * @return
		 * @throws Exception
		 */
		public ArrayList getTotalReport(MarketTrendReportDTO mtrDto) throws Exception {
			ArrayList tmpList = new ArrayList();			
			ReportDAO dao = new ReportDAO();    
			try {
				tmpList = dao.getTotalReport(mtrDto);		
				logger.info("getTotalReport-->" + tmpList);
			} catch (Exception e) {
				logger.error(e);

			}
			return tmpList;
		}
		/**
		 * @param _distId
		 * @return
		 * @throws Exception
		 */
		public ArrayList getTotalReportDur(String fromYear,String toYear) throws Exception {
			ArrayList tmpList = new ArrayList();			
			ReportDAO dao = new ReportDAO();    
			try {
				tmpList = dao.getTotalReportDur(fromYear,toYear);		
				logger.info("getTotalReport-->" + tmpList);
			} catch (Exception e) {
				logger.error(e);

			}
			return tmpList;
		}
		
		
		public ArrayList getSubArea(String language,
				String areaId,String tehsilId,String urbanFlag) {
			ReportDAO dao = new ReportDAO();    
				ArrayList  subAreaList=null;
				ArrayList returnList=new ArrayList();
				try{
					subAreaList=dao.getSubArea(language,areaId,tehsilId,urbanFlag);
					if(subAreaList!=null&& subAreaList.size()>0)
					{
						for(int i=0;i<subAreaList.size();i++)
						{
							ArrayList subList=(ArrayList) subAreaList.get(i);
							MarketTrendReportDTO propDTO= new MarketTrendReportDTO();
							propDTO.setSubAreaId((String) subList.get(0));
							propDTO.setSubAreaName((String) subList.get(1));
							returnList.add(propDTO);
							
						}
						
					}
					
					return returnList;
				}catch (Exception e) {
					logger.error(e);
					return null;
				}
			
		}
		
		
		public ArrayList getWardPatwari(String language,
				String subAreaId,String tehsilId) {
			ReportDAO dao = new ReportDAO();    
				ArrayList  wardPatwariList=null;
				ArrayList returnList=new ArrayList();
			try{
				wardPatwariList=dao.getWardPatwari(language,subAreaId,tehsilId);
				if(wardPatwariList!=null&& wardPatwariList.size()>0)
				{
					//returnList=new ArrayList();
					for(int i=0;i<wardPatwariList.size();i++)
					{
						ArrayList subList=(ArrayList) wardPatwariList.get(i);
						MarketTrendReportDTO propDTO= new MarketTrendReportDTO();
						propDTO.setWardId((String) subList.get(0)+"~"+(String) subList.get(2));
						propDTO.setWardPatwariName((String) subList.get(1));
						returnList.add(propDTO);
						
					}
					
				}
				
				return returnList;
			}catch (Exception e) {
				logger.error(e);
				return null;
			}
		}
		
		
		
		public ArrayList getColonyName(String language,
				String wardPatwariId) {
			ArrayList  l1NameList=new ArrayList();
			ReportDAO dao = new ReportDAO();    
			ArrayList returnList=new ArrayList();
			try{
				l1NameList=dao.getColonyName(language,wardPatwariId);
				if(l1NameList!=null&& l1NameList.size()>0)
				{
					
					for(int i=0;i<l1NameList.size();i++)
					{
						ArrayList subList=(ArrayList) l1NameList.get(i);
						MarketTrendReportDTO propDTO= new MarketTrendReportDTO();
						propDTO.setMohallaId((String) subList.get(0)+"~"+(String) subList.get(2)+"~"+(String) subList.get(3));
						propDTO.setMohallaName((String) subList.get(1));
						returnList.add(propDTO);
						
					}
					
				}
				
				return returnList;
			}catch (Exception e) {
				logger.error(e);
				return null;
			}

		
		}
}
