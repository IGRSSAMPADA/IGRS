package com.wipro.igrs.empDeductions.dao;


import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.Hashtable;

import oracle.jdbc.driver.OracleTypes;

import org.apache.log4j.Logger;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.empDeductions.dto.EmpDTO;
import com.wipro.igrs.empDeductions.dto.EmpDeductionsDTO;


public class EmpDeductionsDAO
{
	private Logger logger = (Logger) Logger
	.getLogger(EmpDeductionsDAO.class);
	DBUtility dbUtil;
	EmpDeductionsDTO dto;
	
	
	/**
	 * getting the details of user
	 * @param empId
	 * @return ArrayList(Users Detail List) 
	 */	
	public ArrayList getUserDetails(String empId)
	{                     
		          ArrayList addList = new ArrayList();  
		          ArrayList readList = new ArrayList();
		          try 
		          {
		        	  dbUtil = new DBUtility();
		        	  dbUtil.createStatement(); 
		        	  String sql = "SELECT EMP_ID,FIRST_NAME,MIDDLE_NAME,LAST_NAME,TO_CHAR(TO_DATE(TRUNC(DATE_OF_JOINING),'DD-MM-RRRR'),'DD-MM-RRRR'),TO_CHAR(TO_DATE(TRUNC(DATE_OF_BIRTH),'DD-MM-RRRR'),'DD-MM-RRRR'),GENDER FROM IGRS_EMP_MASTER WHERE EMP_ID='"+empId+"'";
		        	  readList = dbUtil.executeQuery(sql);
		        	  if(readList!=null)
		            	if(readList.size()>0){
		            		for(int i=0;i<readList.size();i++)  
			                {
		            			dto = new EmpDeductionsDTO();
		            			ArrayList subList = (ArrayList)readList.get(i);
		            			dto.setEmpId((String)subList.get(0));
		            			dto.setFirstName((String)subList.get(1));
		            			dto.setMiddleName((String)subList.get(2));
		            			dto.setLastName((String)subList.get(3));
		            			dto.setDateOfJoing((String)subList.get(4));
		            			dto.setDateOfBirth((String)subList.get(5));
		            			String temp = (String)subList.get(6);
		            			if(temp!=null)
		            					if(temp.equalsIgnoreCase("M"))
		            						temp = "Male";
		            					else
		            						temp = "Female";
		            			dto.setGender(temp);
		            			addList.add(dto);
			                }
		            	}
		          }
		          catch (Exception e) {
		         	  logger.error("exception in calling at DAO Class at getUserDetails  " +e);
		          }
		          finally 
		          {
		             try
		             {
		            	 dbUtil.closeConnection();                                  
		             }
		             catch(Exception e){
		             	 logger.error("Exception in getUserDetails Finally Block  "+ e);   
		             } 
		          }
	       return addList;
	 }

	
	/**
	 * for getting the users list based on the values they given.
	 * the values are optional he can any thing in first name,middle name and last name OR
	 * he can give any combination 
	 * @param vals
	 * @return ArrayList
	 */
	public ArrayList getUsersList(Hashtable vals) {
		ArrayList addList = new ArrayList();  
        ArrayList readList = new ArrayList();
        try 
        {
      	  dbUtil = new DBUtility();
      	  dbUtil.createStatement(); 
      	  String sql = getSql(vals).toString();
      	  readList = dbUtil.executeQuery(sql);
      	  if(readList!=null)
          	if(readList.size()>0){
          		for(int i=0;i<readList.size();i++)  
	                {
          			dto = new EmpDeductionsDTO();
          			ArrayList subList = (ArrayList)readList.get(i);
          			dto.setEmpId((String)subList.get(0));
          			dto.setFirstName((String)subList.get(1));
          			dto.setMiddleName((String)subList.get(2));
          			dto.setLastName((String)subList.get(3));
          			dto.setDateOfJoing((String)subList.get(4));
          			dto.setDateOfBirth((String)subList.get(5));
          			String temp = (String)subList.get(6);
          			if(temp!=null)
          					if(temp.equalsIgnoreCase("M"))
          						temp = "Male";
          					else
          						temp = "Female";
          			dto.setGender(temp);
          			addList.add(dto);
	                }
          	}
        }
        catch (Exception e) {
       	  logger.error("exception in calling at DAO Class at getUsersList()  " +e);
        }
        finally 
        {
           try
           {
          	 dbUtil.closeConnection();                                  
           }
           catch(Exception e){
           	 logger.error("Exception in getUsersList Finally Block  "+ e);   
           } 
        }
 return addList;
	}  
	
	/**
	 * for create dynamic sql
	 * @param vals
	 * @return StringBuffer(SQL)
	 */
	public StringBuffer getSql(Hashtable vals){
		StringBuffer sql1= new StringBuffer("SELECT EMP_ID,FIRST_NAME,MIDDLE_NAME,LAST_NAME,TO_CHAR(TO_DATE(TRUNC(DATE_OF_JOINING),'DD-MM-RRRR'),'DD-MM-RRRR'),TO_CHAR(TO_DATE(TRUNC(DATE_OF_BIRTH),'DD-MM-RRRR'),'DD-MM-RRRR'),GENDER");
		StringBuffer sql2= new StringBuffer(" FROM IGRS_EMP_MASTER WHERE ");
		if(vals.get("FIRST_NAME")!=""){
				sql2.append("FIRST_NAME like '"+vals.get("FIRST_NAME")+"'");
			}
			if(vals.get("MIDDLE_NAME")!=""){
				if(vals.get("FIRST_NAME")!=""){
					sql2.append(",MIDDLE_NAME like '"+vals.get("MIDDLE_NAME")+"'");
				}else{
				sql2.append("MIDDLE_NAME like '"+vals.get("MIDDLE_NAME")+"'");
				}
			}
			if(vals.get("LAST_NAME")!=""){
				if(vals.get("FIRST_NAME")!="" || vals.get("MIDDLE_NAME")!=""){
					sql2.append(",LAST_NAME like '"+vals.get("LAST_NAME")+"'");
				}else{
				sql2.append("LAST_NAME like '"+vals.get("LAST_NAME")+"'");
				}
			}
			sql1.append(sql2);
		return sql1;
	}


	/**
	 * for getting the deductions list for an Employee
	 * @return ArrayList
	 */
	public ArrayList getDeductions() {
		ArrayList addList = new ArrayList();  
        ArrayList readList = new ArrayList();
        try 
        {
      	  dbUtil = new DBUtility();
      	  dbUtil.createStatement(); 
      	  String sql = "SELECT DH.DETAILED_HEAD_ID,DH.DETAILED_HEAD_NAME_E FROM IGRS_EMP_SALARY_COMPONENTS SC,IGRS_EXP_DETAILED_HEAD_MASTER DH WHERE SC.COMPONENT_TYPE='D' AND SC.COMPONENT_ID=DH.DETAILED_HEAD_ID";
      	  readList = dbUtil.executeQuery(sql);
      	  if(readList!=null)
          	if(readList.size()>0){
          		for(int i=0;i<readList.size();i++)  
	                {
          			dto = new EmpDeductionsDTO();
          			ArrayList subList = (ArrayList)readList.get(i);
          			dto.setId((String)subList.get(0));
          			dto.setLabel((String)subList.get(1));
          			addList.add(dto);
	                }
          	}
        }
        catch (Exception e) {
       	  logger.error("exception in calling at DAO Class at getDeductions()  " +e);
        }
        finally 
        {
           try
           {
          	 dbUtil.closeConnection();                                  
           }
           catch(Exception e){
           	 logger.error("Exception in getDeductions Finally Block  "+ e);   
           } 
        }
 return addList;
	}
	
	
	/**
	 * for getting the sub type of the deductions list
	 * @param deductionId
	 * @return ArrayList(deductions sub types)
	 */
	
	public ArrayList getDeductionSubType(String deductionId) {
		ArrayList addList = new ArrayList();  
        ArrayList readList = new ArrayList();
        try 
        {
      	  dbUtil = new DBUtility();
      	  dbUtil.createStatement(); 
      	  String sql = "SELECT LM.LOAN_TYPE_ID,LM.LOAN_NAME FROM IGRS_EMP_LOAN_TYPE_MASTER LM WHERE LM.COMPONENT_ID='"+deductionId+"'";
      	  readList = dbUtil.executeQuery(sql);
      	  if(readList!=null)
          	if(readList.size()>0){
          		for(int i=0;i<readList.size();i++)  
	                {
          			dto = new EmpDeductionsDTO();
          			ArrayList subList = (ArrayList)readList.get(i);
          			dto.setId((String)subList.get(0));
          			dto.setLabel((String)subList.get(1));
          			addList.add(dto);
	                }
          	}
        }
        catch (Exception e) {
       	  logger.error("exception in calling at DAO Class at getDeductionSubType()  " +e);
        }
        finally 
        {
           try
           {
          	 dbUtil.closeConnection();                                  
           }
           catch(Exception e){
           	 logger.error("Exception in getDeductionSubType Finally Block  "+ e);   
           } 
        }
 return addList;
	}
	
	/**
	 * for getting the deduction details 
	 * @param empDet
	 * @return EmpDTO
	 */
	public EmpDTO getDeductionDetails(String[] empDet) {
		EmpDTO dto = null;
		try 
        {
      	  dbUtil = new DBUtility();
      	  CallableStatement clstmt=dbUtil.returnCallableStatement("CALL IGRSUSER.IGRS_EMP_CURRENT_DED_STAT_PROC(?,?,?,?,?)");
      	  clstmt.setString(1, empDet[0]);
      	  clstmt.setString(2, empDet[1]);
      	  clstmt.setString(3,empDet[2]);
      	  clstmt.registerOutParameter(4, OracleTypes.VARCHAR);
      	  clstmt.registerOutParameter(5, OracleTypes.VARCHAR);

      	  if (!clstmt.execute())
      	  {
			String result = (String)clstmt.getString(4);
			if(result != null){
			String data[]=result.split(",");
			dto = arrangeData(data);
			}else
				dto = new EmpDTO();
      	  }
        }catch (Exception e) {
       	  logger.error("exception in calling at DAO Class at getDeductionDetails()  " +e);
        }
        finally 
        {
           try
           {
          	 dbUtil.closeConnection();                                  
           }
           catch(Exception e){
           	 logger.error("Exception in getDeductionDetails Finally Block  "+ e);   
           } 
        }
		return dto;
	}
	
	/**
	 * for setting the data in EmpDTO based on result
	 * GIS => gis_amount	
	 * PF contains=> pf_amount,pf_loan
	 * Loan=> loan_amount,paid_emi,paid_amount,unpaid_emi,unpaid_amount
	 * Advances => advance_amount,paid_emi,paid_amount,unpaid_emi,unpaid_amount
	 * @param data
	 * @return EmpDTO
	 */
	public EmpDTO arrangeData(String[] data){
		EmpDTO dto= new EmpDTO();
		if(data.length>0){
			dto.setAmount(data[0]);
		}
		if(data.length==2){
			dto.setPf_loan(data[1]);
		}
		if(data.length==5){
			dto.setPaid_emi(data[2]);
			dto.setPaid_amount(data[3]);
			dto.setUnpaid_emi(data[4]);
			dto.setUnpaid_amount(data[5]);
		}
		return dto;
	}
	
}