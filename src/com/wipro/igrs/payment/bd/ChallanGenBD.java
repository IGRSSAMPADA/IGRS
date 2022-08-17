package com.wipro.igrs.payment.bd;

import org.apache.log4j.Logger;
import com.wipro.igrs.payment.dto.ChallanGenDTO;


import com.wipro.igrs.payment.dao.ChallanGenDAO;
import com.wipro.igrs.payment.dao.ChallanUpdateDAO;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

import com.wipro.igrs.payment.form.ChallanGenForm;
import com.wipro.igrs.payment.form.EODChallanForm;
/**
 * ===========================================================================
 * File           :   ChallanGenBD.java
 * Description    :   Represents the Challan generation BD Class
 * Author         :   vengamamba P
 * Created Date   :    Mar 13, 2008

 * ===========================================================================
 */
public class ChallanGenBD {
	private Logger logger = (Logger) Logger.getLogger(ChallanGenBD.class);
	 public ChallanGenBD() {
	    }
	    
	 /**
	     * ===========================================================================
	     * Method         :   getOfficeNameList()
	     * Description    :   Returns list  of officeids and its values. 
	     * Arguments      :   
	     * return type    :   Arraylist
	     * Author         :   vengamamba P
	     * Created Date   :   Mar 13, 2008
	     * ===========================================================================
	     */  
	 
	 public ArrayList  getOfficeNameList() throws Exception 
    {
		 ChallanGenDAO dao = new ChallanGenDAO();
	     ArrayList olist = dao.getOfficeNameList();
	     ArrayList list = new ArrayList();

	     if (olist != null) {
	    		for (int i = 0; i < olist.size(); i++) {
	    			ArrayList lst = (ArrayList)olist.get(i);
	    			ChallanGenDTO dto=new ChallanGenDTO();
	    			dto.setValue((String)lst.get(0));
	    			dto.setName((String)lst.get(1));
	       	
	    			list.add(dto);
	    		}
	    	}

	    	return list;
    }
	 /**
	     * Method 		: getChallanGenData
	     * Description	: RETURNING CHALANGEN DETAILS BASED ON DATE AND OFFICEID
	     * @param query : string ,
	     * @throws 		: Exception
	     * return Type  :ArrayList 
	     */
	 
	 public ArrayList getChallanGenData(String officeid,String date) throws Exception{
		 ChallanGenDAO dao = new ChallanGenDAO();
		 
		 
	     String param2[]=new String[2];
	     String day,month,year;
		 ArrayList tlist=null;
		 StringTokenizer st=new StringTokenizer(date,"/");
    	 day=st.nextToken();
    	 month=st.nextToken();
    	 year=st.nextToken();
	     param2[0]=officeid;
		 param2[1]=getOracleDate(day,month,year);
		 tlist=new ArrayList();
		 tlist=dao.getChallanGendata(param2);//getting  challn gendata based on officeid and date
		 logger.debug("arraylist of gendata"+tlist);
		  return tlist;
	 }
	 /**
	     * Method 		: getstatus
	     * Description	: returns status and transactionid 
	     * @param query :  string 
	     * return Type  :ArrayList
	     */
	 
	 public ArrayList getStatus(String officeid,String date) throws Exception{
		 ChallanGenDAO dao = new ChallanGenDAO();
		 
		 String param2[]=new String[2];
	     String day,month,year;
		 ArrayList tlist=null;
		 StringTokenizer st=new StringTokenizer(date,"/");
		 day=st.nextToken();
		 month=st.nextToken();
		 year=st.nextToken();
	     param2[0]=officeid;
		 param2[1]=getOracleDate(day,month,year);
		 tlist=new ArrayList();
		 tlist=dao.getStatus(param2);//getting  challn gendata based on officeid and date
		 logger.debug("arraylist of gendata"+tlist);
		  return tlist;
	 }
	 /**
	     * Method 		: getChallanData
	     * Description	: RETURNING CHALANGEN DETAILS BASED ON DATE AND OFFICEID
	     * @param query : string ,
	     * @throws 		: Exception
	     * return Type  :ArrayList 
	     */
	 
	 public ArrayList getChallanData(ChallanGenForm form) throws Exception{
		 ChallanUpdateDAO dao = new ChallanUpdateDAO();
		 
		 
	     String param2[]=new String[1];
	     
		 ArrayList tlist=null;
		 
 	 param2[0]=form.getTxnid();
		 
		 tlist=new ArrayList();
		 tlist=dao.getChallandata(param2);//getting  challn gendata based on Txnid 
		 logger.debug("arraylist of gendata"+tlist);
		  return tlist;
	 }
	 /**
	     * Method 		: getOracleDate
	     * Description	: getting date in oracle date format
	     * @param query : string ,
	     * @throws 		: 
	     * return Type  :string
	     */
	 public  String getOracleDate(String day,String month,String year) {

    	 
    	 String inputDate = day + "-" + month + "-" + year;
    	 SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    	 logger.debug(" " + inputDate + " parses as ");
    	 String finalDate = "";
    	 Date newDate ;
    	 try {
    		 newDate = formatter.parse(inputDate);
    		 SimpleDateFormat format = new SimpleDateFormat("dd/MMM/yyyy");
    		 finalDate = format.format(newDate);
    		 logger.debug(finalDate);

    	 	}
    	 catch (Exception e) {
    		 logger.error(e);
    	 }

    	 return finalDate;

     }


	 
}