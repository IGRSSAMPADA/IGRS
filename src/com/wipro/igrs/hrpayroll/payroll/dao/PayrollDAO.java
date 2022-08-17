/**
 * PayrollDAO.java
 */
package com.wipro.igrs.hrpayroll.payroll.dao;
import  com.wipro.igrs.hrpayroll.payroll.sql.CommonSQL;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.hrpayroll.payroll.dto.PayrollDTO;

/**
 * @author pranalk
 *
 */


public class PayrollDAO{

	private DBUtility dbUtil = null;
	private Logger logger = (Logger) Logger
			.getLogger(PayrollDAO.class);
	public PayrollDAO(){
		
	}
	
	public HashMap calculatePayroll(PayrollDTO payrollDTO) throws Exception{
		dbUtil=new DBUtility();
		
		HashMap components=dbUtil.calculatePayroll(payrollDTO.getEmployeeid(),payrollDTO.getMonth());
		
		return components;
	}
	
	//------------------ code is added for adding payroll Formula
	/**
	 * getting component id list
	 * @return
	 */
	public ArrayList getcompIdList() {
	    logger.info("PayrollDAO in getcompIdList(): list.size()");
	    String SQL  =  CommonSQL.PAYROLL_COMP_ID_LIST;
		  ArrayList list = new ArrayList();
	       try{
		   dbUtil=new DBUtility();
	           dbUtil.createStatement();
	        list  =  dbUtil.executeQuery(SQL);
	        logger.info("PayrollDAO in getcompIdList(): list.size()"+list.size());
	       
	        } catch (Exception e) {
	        	logger.error("Exception in Payroll DAO getcompIdList():" + e);
	        }
	        logger.info("PayrollDAO in getcompIdList():"+list);
	        return list;
	}
	/**
	 * getting Operator  id list
	 * @return
	 */
	public ArrayList getOprIdList() {
	    String SQL  =  CommonSQL.PAYROLL_OPR_ID_LIST;
		  ArrayList list = new ArrayList();
	       try{
		   dbUtil=new DBUtility();
	           dbUtil.createStatement();
	        list  =  dbUtil.executeQuery(SQL);
	        logger.info("PayrollDAO in getOprIdList(): list.size()"+list.size());
	       
	        } catch (Exception e) {
	        	logger.error("Exception in getOprIdList():" + e);
	        }
	        logger.info("PayrollDAO in  Payroll DAO getOprIdList():"+list);
	        return list;
	}
	
	/**
	 * adding  Employee salary component values 
	 * @param payrollForm
	 * @return
	 */

	public boolean addEmpSalComp(String[] param) 
	{
	    
	    boolean boo = false;
	    String SQL  =  CommonSQL.PAYROLL_COMP_DET_INSERT;
	       try{
		   dbUtil=new DBUtility();
	           dbUtil.createPreparedStatement(SQL);
	            boo  =  dbUtil.executeUpdate(param);
	        logger.info("PayrollDAO in addEmpSalComp():boo============="+boo);
	        if(boo)
	            dbUtil.commit();
	        else 
	            dbUtil.rollback();
	        } catch (Exception e) {
	        	logger.error("Exception in Payroll DAO  addEmpSalComp():" + e);
	        	try {
			    dbUtil.rollback();
			} catch (Exception e1) {
			    e1.printStackTrace();
			}
	        }
	        return boo;
	}
	/**
	 * getting Employee Salary  Component values 
	 * @return
	 */
	public ArrayList getEmpSalComp() {
	    String SQL  = CommonSQL.PAYROLL_COMP_DET_SELECT;
		  ArrayList list = new ArrayList();
	       try{
		   dbUtil=new DBUtility();
	           dbUtil.createStatement();
	        list  =  dbUtil.executeQuery(SQL);
	        logger.info("PayrollDAO in getEmpSalComp(): list.size()--------->"+list.size());
	       
	        } catch (Exception e) {
	        	logger.error("Exception in  Payroll DAO getEmpSalComp():" + e);
	        }
	        logger.info("PayrollDAO in getEmpSalComp():"+list);
	        return list;
	}
	/**
	 * delete the component Details
	 * @param compId
	 * @return
	 */
	public boolean delCompDet(String compId) 
	{
	    boolean boo = false;
	    String SQL  = CommonSQL.PAYROLL_COMP_ID_DELETE+"'"+compId+"'";
	   
	       try{
		   dbUtil=new DBUtility();
		   dbUtil.createStatement();
		   logger.info("PayrollDAO in delCompDet():SQL============="+SQL);
	            boo  =  dbUtil.executeUpdate(SQL);
	           
	        logger.info("PayrollDAO in delCompDet():boo============="+boo);
	        if(boo)
	            dbUtil.commit();
	        else 
	            dbUtil.rollback();
	        } catch (Exception e) {
	        	logger.error("Exception in  Payroll DAO delCompDet():" + e);
	        	try {
			    dbUtil.rollback();
			} catch (Exception e1) {
			    e1.printStackTrace();
			}
	        }
	        return boo;
	}
	
	
}
