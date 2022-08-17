/**
 * PayrollBD.java
 */
package com.wipro.igrs.hrpayroll.payroll.bd;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.log4j.Logger;

import com.wipro.igrs.estamping.dto.EstampDetailsDTO;
import com.wipro.igrs.hrpayroll.payroll.dao.PayrollDAO;
import com.wipro.igrs.hrpayroll.payroll.dto.PayrollDTO;
import com.wipro.igrs.hrpayroll.payroll.form.PayrollForm;

/**
 * @author pranalk
 *
 */

public class PayrollBD {
	PayrollDAO payrollDAO = null;
	private Logger logger = (Logger) Logger.getLogger(PayrollBD.class);

	/**
	 * @throws Exception
	 */
	
	public PayrollBD() throws Exception {
		payrollDAO = new PayrollDAO();
		//System.out.println("In PayrollBd");
	}

	public PayrollDTO calculatePayroll(PayrollDTO payrollDTO)throws Exception {
		//System.out.println("calculatePayroll");
		HashMap components=null;
		PayrollDTO payrolldto=new PayrollDTO();
		try{
			ArrayList componentlist=new ArrayList();
			
			components=payrollDAO.calculatePayroll(payrollDTO);
			if(components!=null){
			logger.debug("Comopents"+components);
			Integer totalsal=new Integer(Integer.parseInt((String)components.get("TOTALSAL")));
			logger.debug("totalsal"+totalsal);
			componentlist = (ArrayList)components.get("COMPONENTS");
			
			//Integer taxamount=new Integer(Integer.parseInt((String)components.get("TAXAMOUNT")));
			//logger.debug("taxamount"+taxamount);
			//Integer professiontax=new Integer(Integer.parseInt((String)components.get("PROFESSIONTAX")));
			//logger.debug("professiontax"+professiontax);
			
			//ArrayList componentslist=(ArrayList)components.get("COMPONENTS");
			//logger.debug("componentslist"+componentslist.size());
			
			//String errorcode=(String)components.get("ERRORCODE");
			//logger.debug("errorcode"+errorcode);
			//String errormsg=(String)components.get("ERRORMSG");
			//logger.debug("componentslist"+errorcode);
						
			/*
			if(componentslist!=null){
			Iterator it=componentslist.listIterator();
			PayrollDTO payrollcomponents;
			while(it.hasNext()){
			ArrayList list=(ArrayList)it.next();
			Iterator newiter=list.iterator();
			      while(newiter.hasNext()){
			    	  logger.debug("IN WHILE LOOP");
			    	  payrollcomponents=new PayrollDTO();
			    	  payrollcomponents.setComponentid((String)newiter.next());
			    	  payrollcomponents.setCoponentname((String)newiter.next());
			    	  payrollcomponents.setComponenttype((String)newiter.next());
			    	  payrollcomponents.setSalaryamount((String)newiter.next());
			    	  componentlist.add(payrollcomponents);
			    	  
			      }
			
			}
			}
			*/
			
			payrolldto.setTotalsal(totalsal);
			//payrolldto.setTaxamount(taxamount);
			//payrolldto.setProfessiontax(professiontax);
			payrolldto.setComponentlist(componentlist);
			//payrolldto.setErrorcode(errorcode);
			//payrolldto.setErrormsg(errormsg);
			}
					
		}catch (Exception e) {
			logger.debug(e);
			throw e;
		}
		return payrolldto;
		
	}
	//------------------ code is added for adding payroll Formula
	/**
	 * getting component id list
	 * @return
	 */

	public ArrayList getcompIdList()
	{
	    logger.info("in Payaroll bd getcompIdList() ret ");
	 ArrayList list = null;   
	 ArrayList ret  =  payrollDAO.getcompIdList();
        logger.info("in Payaroll bd getcompIdList() ret "+ret);
        list  =  new ArrayList();
        try {
	
        if (ret !=  null) {
            for (int i  =  0; i < ret.size(); i++) {
                ArrayList lst  =  (ArrayList)ret.get(i);
                PayrollDTO dto = new PayrollDTO();
                dto.setValue((String)lst.get(0));
                dto.setName((String)lst.get(1));
                list.add(dto);
            }

        }
       }
        catch (Exception e) {
            logger.info("Exception in Payroll BD -- getcompIdList( )  "+e);
	}
        return list;
    }
	/**
	 * getting Operator  id list
	 * @return
	 */
	public ArrayList getOprIdList() {
	    logger.info("in Payaroll bd getOprIdList() ret ");
		 ArrayList list = null;   
		 ArrayList ret  =  payrollDAO.getOprIdList();
	        logger.info("in Payaroll bd getOprIdList() ret "+ret);
	        list  =  new ArrayList();
	        try{
	        if (ret !=  null) {
	            for (int i  =  0; i < ret.size(); i++) {
	                ArrayList lst  =  (ArrayList)ret.get(i);
	                PayrollDTO dto = new PayrollDTO();
	                dto.setValue((String)lst.get(0));
	                dto.setName((String)lst.get(1));
	                list.add(dto);
	            }

	        }
	        }
	        catch (Exception e) {
	            logger.info("Exception in Payroll BD -- getOprIdList( )  "+e);
		}
	        return list;
	}
	/**
	 * adding  Employee salary component values 
	 * @param payrollForm
	 * @return
	 */
	public boolean addEmpSalComp(PayrollForm payrollForm)
	{
	    boolean boo = false;
	    
	    String param[] = new String[5];
	    
	    param[0] = payrollForm.getCompId();
	    param[1] = payrollForm.getCompType();
	    param[2] = payrollForm.getOperId();
	    param[3] = payrollForm.getCompParentId();
	    param[4] = payrollForm.getFunOprId();
	    try{
	    boo = payrollDAO.addEmpSalComp(param);
	    logger.info("in Payaroll bd addEmpSalComp() boo-------> "+boo);
	    }
	    catch (Exception e) {
		logger.info("Exception in Payroll BD -- addEmpSalComp( )  "+e);
	    }
	    return boo;
	}
	/**
	 * getting Employee Salary  Component values 
	 * @return
	 */
	
	public ArrayList getEmpSalComp() {
	    logger.info("in Payaroll bd getEmpSalComp() ret ");
		 ArrayList list = null;  
		 try{
		 ArrayList ret  =  payrollDAO.getEmpSalComp();
	        logger.info("in Payaroll bd getEmpSalComp() ret "+ret);
	        list  =  new ArrayList();

	        if (ret !=  null) {
	            for (int i  =  0; i < ret.size(); i++) {
	                ArrayList lst  =  (ArrayList)ret.get(i);
	                PayrollForm form = new PayrollForm();
	                form.setCompName((String)lst.get(0));
	                if(((String)lst.get(1)).equalsIgnoreCase("A"))
	                form.setCompType("Addition");
	                if(((String)lst.get(1)).equalsIgnoreCase("D"))
		                form.setCompType("Deduction");
	                if(((String)lst.get(1)).equalsIgnoreCase("T"))
		                form.setCompType("Treasury");
	                form.setOperId((String)lst.get(2));
	                //form.setCompParentId((String)lst.get(3));
	                form.setFunOprId((String)lst.get(3));
	                form.setCompId((String)lst.get(4));
	                list.add(form);
	            }

	        }
	 }
		 catch (Exception e) {
		     logger.info("Exception in Payroll BD -- getEmpSalComp( )  "+e);
		}
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
	    
	    try{
	    boo = payrollDAO.delCompDet(compId);
	    logger.info("in Payaroll bd delCompDet() boo-------> "+boo);
	    }
	    catch (Exception e) {
		logger.info("Exception in Payroll BD -- delCompDet( )  "+e);
	    }
	    return boo;
	}

	
}
