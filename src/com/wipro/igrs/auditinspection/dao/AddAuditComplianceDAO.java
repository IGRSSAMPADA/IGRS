package com.wipro.igrs.auditinspection.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeMap;

import com.sun.star.i18n.AmPmValue;
import com.wipro.igrs.auditinspection.dto.AGMPReportDetailsDTO;
import com.wipro.igrs.auditinspection.dto.PublicDTO;
import com.wipro.igrs.auditinspection.sql.AddComplianceSQL;
import com.wipro.igrs.db.DBUtility;

public class AddAuditComplianceDAO {

	public String getRole(String officeid)
	{
		String list=null;
		DBUtility dbUtil = null;
		try{
			 dbUtil=new DBUtility();
			 String[] param={officeid.toUpperCase()};
			 String str=AddComplianceSQL.GET_ROLE;
			 dbUtil.createPreparedStatement(str);
			 list = dbUtil.executeQry(param);
		}catch(Exception ex){
			ex.printStackTrace();
			
		}
		finally{
			try{
				if(dbUtil!=null){
					dbUtil.closeConnection();
				}
			}catch(Exception exp){
				exp.printStackTrace();
			}
		}
		return list;
	}

	public ArrayList searchReport(AGMPReportDetailsDTO reportDetailsDTO,String ofcId) {
		
		String sql ="";
		ArrayList retVal = new ArrayList();
		TreeMap<String, String> paramsMap = new TreeMap<String, String>();
		if(reportDetailsDTO.getRole().equalsIgnoreCase("SRO"))
		{
			paramsMap.put("1", ofcId);
			sql=AddComplianceSQL.SEARCH_AUDIT_REPORT_SR;
		}
		else if(reportDetailsDTO.getRole().equalsIgnoreCase("DRO"))
		{
			paramsMap.put("1", ofcId);
			sql=AddComplianceSQL.SEARCH_AUDIT_REPORT_DR;
		}
		else if(reportDetailsDTO.getRole().equalsIgnoreCase("DIGO"))
		{
			sql=AddComplianceSQL.SEARCH_AUDIT_REPORT_DIG;
			paramsMap.put("1", ofcId);
			paramsMap.put("2", ofcId);
		}
		else if(reportDetailsDTO.getRole().equalsIgnoreCase("IGO"))
		{
			paramsMap.put("1","4");
			sql=AddComplianceSQL.SEARCH_AUDIT_REPORT_IG;
		}
		StringBuilder stbr = new StringBuilder(sql);
		
		if("A".equalsIgnoreCase(reportDetailsDTO.getReportType()))
		{
			if("1".equalsIgnoreCase(reportDetailsDTO.getReportSubType()))
			{	
			stbr.append(" AND R.AUDIT_BODY_TYPE='AGMP' and R.TRANSACTION_ID LIKE '%AGR%'");
			}
			else
			{
			stbr.append(" AND R.AUDIT_BODY_TYPE='AGMP' and R.TRANSACTION_ID LIKE '%AGE%'");
			}
		}
		else if("I".equalsIgnoreCase(reportDetailsDTO.getReportType()))
		{
			if("1".equalsIgnoreCase(reportDetailsDTO.getReportSubType1()))
			{	
					stbr.append(" AND R.AUDIT_BODY_TYPE='INTERNAL' and R.TRANSACTION_ID LIKE '%IA%'");
			}
			else
			{
				stbr.append(" AND R.AUDIT_BODY_TYPE='INTERNAL' and R.TRANSACTION_ID LIKE '%IE%'");
				
			}
		}
		if(reportDetailsDTO.getReportId()!=null&&!reportDetailsDTO.getReportId().equalsIgnoreCase(""))
		{
			stbr.append(" AND R.TRANSACTION_ID=?");
			paramsMap.put("3", reportDetailsDTO.getReportId());
		}
		if((reportDetailsDTO.getFromDate()!=null && !reportDetailsDTO.getFromDate().equalsIgnoreCase(""))&&(reportDetailsDTO.getToDate()!=null && !reportDetailsDTO.getToDate().equalsIgnoreCase("")))
		{
			stbr.append(" AND R.CREATED_DATE+1 BETWEEN to_DATE(?,'DD-MM-YY') AND to_DATE(?,'DD-MM-YY')");
			paramsMap.put("4", reportDetailsDTO.getFromDate());
			paramsMap.put("5", reportDetailsDTO.getToDate());
		}DBUtility	dbUtil=null;
		try {
			dbUtil = new DBUtility();
			ArrayList queryResult;
			Collection<String> values = paramsMap.values();
			ArrayList<String> tmp = new ArrayList(values);
			String[] params = tmp.toArray(new String[]{}); 
			queryResult = dbUtil.getSearchAuditDetails(stbr.toString(), params,reportDetailsDTO);
			retVal.addAll(queryResult);
			dbUtil.closeConnection();
		} catch (Exception k) {
			k.printStackTrace();
		}finally{
			try{
				if(dbUtil!=null){
					dbUtil.closeConnection();
				}
			}catch(Exception exp){
				exp.printStackTrace();
			}
		}
		return retVal;
	}

	public boolean updateStatus(AGMPReportDetailsDTO agmpDTO) {
		
		String sql="";
		if(agmpDTO.getRole().equalsIgnoreCase("SRO"))
		{
			sql=AddComplianceSQL.ADD_COMPLIANCE_SR;
		}
		else if(agmpDTO.getRole().equalsIgnoreCase("DRO"))
		{
			sql=AddComplianceSQL.ADD_COMPLIANCE_DR;
		}
		else if(agmpDTO.getRole().equalsIgnoreCase("DIGO"))
		{
			sql=AddComplianceSQL.ADD_COMPLIANCE_DIG;
		}
		else if(agmpDTO.getRole().equalsIgnoreCase("IGO"))
		{
			sql=AddComplianceSQL.ADD_COMPLIANCE_IG;

		}
		boolean list=false;
		DBUtility dbUtil = null;
		try{
			 dbUtil=new DBUtility();
			 String[] param={agmpDTO.getCompliance(),agmpDTO.getTxnId()};
			 dbUtil.createPreparedStatement(sql);
			 { 
			 list = dbUtil.executeUpdate(param);
			 }
		}catch(Exception ex){
			ex.printStackTrace();
			
		}
		finally{
			try{
				if(dbUtil!=null){
					dbUtil.closeConnection();
				}
			}catch(Exception exp){
				exp.printStackTrace();
			}
		}
		return list;
		
	}

}
