package com.wipro.igrs.payment.bd;

import org.apache.log4j.Logger;
import com.wipro.igrs.payment.dto.ChallanGenDTO;

import com.wipro.igrs.payment.dao.ChallanUpdateDAO;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

import com.wipro.igrs.payment.form.ChallanGenForm;
/**
 * ===========================================================================
 * File           :   ChallanUpdateBD.java
 * Description    :   Represents the Challan Updation BD Class
 * Author         :   vengamamba P
 * Created Date   :    Mar 17, 2008

 * ===========================================================================
 */
public class ChallanUpdateBD {
	private Logger logger = (Logger) Logger.getLogger(ChallanUpdateBD.class);
	 public ChallanUpdateBD() {
	    }
	    
	 /**
	     * ===========================================================================
	     * Method         :   getuseridList()
	     * Description    :   Returns list  of userids . 
	     * Arguments      :   
	     * return type    :   Arraylist
	     * Author         :   vengamamba P
	     * Created Date   :   Mar 17, 2008
	     * ===========================================================================
	     */  
	 
	 public ArrayList  getUseridList() throws Exception 
    {
		 ChallanUpdateDAO dao = new ChallanUpdateDAO();
	     ArrayList ulist = dao.getUseridList();
	     ArrayList list = new ArrayList();

	     if (ulist != null) {
	    		for (int i = 0; i < ulist.size(); i++) {
	    			ArrayList lst = (ArrayList)ulist.get(i);
	    			ChallanGenDTO dto=new ChallanGenDTO();
	    			dto.setValue((String)lst.get(0));
	    			dto.setName((String)lst.get(0));
	       	
	    			list.add(dto);
	    		}
	    	}

	    	return list;
    }
	 
	 /**
	     * ===========================================================================
	     * Method         :   getBankidList()
	     * Description    :   Returns list  of Bankids and its names . 
	     * Arguments      :   
	     * return type    :   Arraylist
	     * Author         :   vengamamba P
	     * ===========================================================================
	     */  
	 
	 public ArrayList  getBankidList() throws Exception 
	 {
		 ChallanUpdateDAO dao = new ChallanUpdateDAO();
	     ArrayList blist = dao.getBankidList();
	     ArrayList list = new ArrayList();

	     if (blist != null) {
	    		for (int i = 0; i < blist.size(); i++) {
	    			ArrayList lst = (ArrayList)blist.get(i);
	    			ChallanGenDTO dto=new ChallanGenDTO();
	    			dto.setValue((String)lst.get(0));
	    			dto.setName((String)lst.get(1));
	       	
	    			list.add(dto);
	    		}
	    	}

	    	return list;
	 }
	 
	 /**
	     * ===========================================================================
	     * Method         :   updateChallan()
	     * Description    :   Returns boolean of updating success or not . 
	     * Arguments      :   form as parameter
	     * return type    :   boolean
	     * Author         :   vengamamba P
	     * ===========================================================================
	     */  
	 public boolean updateChallan(ChallanGenForm form)throws Exception {
	        ChallanUpdateDAO dao = new ChallanUpdateDAO();
	        boolean chupdate= false;
	        String[] param = new String[6];
	        String day,month,year;
	        
	        String tid=form.getTxnid();
			String sno=form.getScrollno();
			String bid=form.getBankid();
			String depby=form.getDepositedby();
			String depdate=form.getDepositdate();
			String updatedby="admin";
			StringTokenizer st=new StringTokenizer(depdate,"/");
	    	 day=st.nextToken();
	    	 month=st.nextToken();
	    	 year=st.nextToken();
				        
	        param[0] =sno;
	        param[1] =bid;
	     	param[2]=getOracleDate(day,month,year);
	     	param[3]=depby;
	     	param[4]=updatedby;
	     	param[5]=tid;
	     	chupdate= dao.updateChallan(param);
	        return chupdate;
	    }
	 
	 /**
	     * Method 		: getChallanStatus
	     * Description	: RETURNING CHALANGEN delivery status BASED ON txnid
	     * @param query : form  
	     * @throws 		: Exception
	     * return Type  :String 
	     */
	 
	 public String getChallanStatus(ChallanGenForm form) throws Exception{
		 ChallanUpdateDAO dao = new ChallanUpdateDAO();
		 String status;
		 String tid=form.getTxnid();
		 
		 status=dao.getChallanStatus(tid);//getting  challn gendata based on Txnid 
		 logger.debug("status="+status);
		  return status;
	 }
	 
	 
	 /**
	     * Method 		: getChallanData
	     * Description	: RETURNING CHALANGEN DETAILS BASED ON DATE AND OFFICEID
	     * @param query : form 
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
		 logger.debug("getting arraylist of chalangen"+tlist);
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