package com.wipro.igrs.payment.bd;
import org.apache.log4j.Logger;

import com.wipro.igrs.payment.dto.EODChallanDTO;
import com.wipro.igrs.payment.dao.CashCounterDAO;
import com.wipro.igrs.payment.dao.EODChallanDAO;
import com.wipro.igrs.payment.dao.ChallanUpdateDAO;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;
import com.wipro.igrs.payment.form.EODChallanForm;
import com.wipro.igrs.propertylock.dao.PropertyLockDAO;
import com.wipro.igrs.revenueManagement.bd.RevenueMgtBD;
import com.wipro.igrs.revenueManagement.dao.RevenueMgtDAO;
import com.wipro.igrs.revenueManagement.dto.RevenueMgtDTO;

/**
 * ===========================================================================
 * File           :   EODChallanBD.java
 * Description    :   Represents the EOD Challan generation BD Class
 * Author         :   Aakriti
 * Created Date   :   Dec 21, 2012

 * ===========================================================================
 */
public class EODChallanBD {
	private Logger logger = (Logger) Logger.getLogger(ChallanGenBD.class);
	 public EODChallanBD() {
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
	 
	 public ArrayList getMajorHead() throws Exception{
			ArrayList mainList = new ArrayList();
			ArrayList list = new ArrayList();
	       ArrayList subList = null;
	       EODChallanDAO dao = new EODChallanDAO();
			try{
			list = dao.getMajorHead();
	       
			RevenueMgtDTO dto = new RevenueMgtDTO();
	       for (int i = 0; i < list.size(); i++) {
	           subList = (ArrayList)list.get(i);
	           dto = new RevenueMgtDTO();
	           dto.setValue(subList.get(0).toString()+","+subList.get(1).toString());
	           dto.setName(subList.get(0).toString()+","+subList.get(1).toString());
	           mainList.add(dto);
	       	}
			}catch(Exception e){
				
			}
			return mainList;
		}
		
		/******************************************************************  
		  *   Method Name  :   getSubMajorHead()
		  *   Arguments    :  major head id
		  *   Return       :   ArrayList
		  *   Throws 	  :   Exception
		 *******************************************************************/  
		/*public ArrayList getSubMajorHead(String mjrHdId) throws Exception{
			RevenueMgtBD revBD = new RevenueMgtBD();
			ArrayList desDtl =new ArrayList();
			try{
				desDtl =revBD.getSubMajorHead(mjrHdId);
			
			}catch(Exception e){
			}
			return desDtl;
		}*/
	 public ArrayList getSubMajorHead() throws Exception{
			ArrayList mainList = new ArrayList();
			ArrayList list = new ArrayList();
	      ArrayList subList = null;
	      EODChallanDAO dao = new EODChallanDAO();
			try{
			list = dao.getSubMajorHead();
	      
			RevenueMgtDTO dto = new RevenueMgtDTO();
	      for (int i = 0; i < list.size(); i++) {
	          subList = (ArrayList)list.get(i);
	          dto = new RevenueMgtDTO();
	          dto.setValue(subList.get(0).toString()+","+subList.get(1).toString());
	          dto.setName(subList.get(0).toString()+","+subList.get(1).toString());
	          mainList.add(dto);
	      	}
			}catch(Exception e){
				
			}
			return mainList;
		}
		
		/******************************************************************  
		  *   Method Name  :   getMinorHead()
		  *   Arguments    : sub major head id
		  *   Return       :   ArrayList
		  *   Throws 	  :   Exception
		 *******************************************************************/  
		/*public ArrayList getMinorHead(String mjrHdId) throws Exception{
			RevenueMgtBD revBD = new RevenueMgtBD();
			ArrayList desDtl =new ArrayList();
			try{
				desDtl =revBD.getMinorHead(mjrHdId);
			
			}catch(Exception e){
			}
			return desDtl;
		}*/
	 public ArrayList getMinorHead() throws Exception{
			ArrayList mainList = new ArrayList();
			ArrayList list = new ArrayList();
	     ArrayList subList = null;
	     EODChallanDAO dao = new EODChallanDAO();
			try{
			list = dao.getMinorHead();
	     
			RevenueMgtDTO dto = new RevenueMgtDTO();
	     for (int i = 0; i < list.size(); i++) {
	         subList = (ArrayList)list.get(i);
	         dto = new RevenueMgtDTO();
	         dto.setValue(subList.get(0).toString()+","+subList.get(1).toString());
	         dto.setName(subList.get(0).toString()+","+subList.get(1).toString());
	         mainList.add(dto);
	     	}
			}catch(Exception e){
				
			}
			return mainList;
		}
	 
	 public ArrayList  getOfficeNameList() throws Exception 
 {
		 EODChallanDAO dao = new EODChallanDAO();
	     ArrayList olist = dao.getOfficeNameList();
	     ArrayList list = new ArrayList();

	     if (olist != null) {
	    		for (int i = 0; i < olist.size(); i++) {
	    			ArrayList lst = (ArrayList)olist.get(i);
	    			EODChallanDTO dto=new EODChallanDTO();
	    			dto.setValue((String)lst.get(0));
	    			dto.setName((String)lst.get(1));
	       	
	    			list.add(dto);
	    		}
	    	}

	    	return list;
 }
	 
	 /**
	    * Method 		: getOfficeName
	    * Description	: getting office name based on office id
	    * return Type  :String
	    * 
	    */	 
	 public String getOfficeName(String oid,String languageLocale)throws Exception{	
		 String officeName=null;
		 EODChallanDAO dao4 = new EODChallanDAO();
		 officeName=dao4.getOfficeName(oid,languageLocale);
		 return officeName;
	 }
	 
	 /**
	    * Method 		: getDistrictId
	    * Description	: getting office name based on office id
	    * return Type  :String
	    * 
	    */	 
	 public String getDistrictId(String oid)throws Exception{	
		 String officeName=null;
		 EODChallanDAO dao4 = new EODChallanDAO();
		 officeName=dao4.getDistrictId(oid);
		 return officeName;
	 }
	 /**
	     * Method 		: getChallanGenData
	     * Description	: RETURNING CHALANGEN DETAILS BASED ON DATE AND OFFICEID
	     * @param query : string ,
	     * @throws 		: Exception
	     * return Type  :ArrayList 
	     */
	 
	 public ArrayList getChallanGenData(String officeid,String date) throws Exception{
		 EODChallanDAO dao = new EODChallanDAO();
		 
		 
	     String param2[]=new String[2];
	     String day,month,year;
		 ArrayList tlist=null;
		 StringTokenizer st=new StringTokenizer(date,"/");
 	     day=st.nextToken();
 	     month=st.nextToken();
 	     year=st.nextToken();
	     param2[0]=officeid;
		 param2[1]=getOracleDate2(day,month,year);
		 tlist=new ArrayList();
		 tlist=dao.getChallanGendata(param2);//getting  challn gendata based on officeid and date
		 logger.debug("arraylist of gendata"+tlist);
		  return tlist;
	 }
	 
	 
	 /**
	     * Method 		: getlatestChlnDtls
	     * Description	: RETURNING latest CHALAN DETAILS BASED ON OFFICEID
	     * @param query : string ,
	     * @throws 		: Exception
	     * return Type  :ArrayList 
	     */
	 
	 public ArrayList getlatestChlnDtls(String officeid) throws Exception{
		 EODChallanDAO dao = new EODChallanDAO();
		 //String param2[]=new String[2];
	     String day,month,year;
		 ArrayList tlist=null;
		 tlist=new ArrayList();
		 tlist=dao.getlatestChlnDtls(officeid);//getting  challn gendata based on officeid and date
		 logger.debug("arraylist of gendata"+tlist);
		  return tlist;
	 }
	 //commented by Anuj
	 
	/* *//**
	     * Method 		: getTotalAmount
	     * Description	: returns status and transactionid 
	     * @param query :  string 
	     * return Type  :  String
	     *//*
	 
	 public String getTotalAmount(String officeid,String date) throws Exception{
		 EODChallanDAO dao = new EODChallanDAO();
		 
		 String param2[]=new String[2];
	     ArrayList tlist=null;
		 String str = null;
		 param2[0]=officeid;
		 param2[1]=date;
		 tlist=new ArrayList();
		 str=dao.getTotalAmount(param2);//getting  challn gendata based on officeid and date
		 logger.debug("arraylist of gendata"+tlist);
		  return str;
	 }*/
	 
	 
	 //Added by Anuj
	 /**
	     * Method 		: getTotalAmount
	     * Description	: returns status and transactionid 
	     * @param query :  string 
	     * return Type  :  String
	     */
	 
	 public ArrayList getTotalAmount(String officeid,String date,String subMjrHd,String mnrHd,String oldNewReceipt) throws Exception{
		 EODChallanDAO dao = new EODChallanDAO();
		 
		 String param2[]=new String[4];
	     ArrayList tlist=null;
		 String str = null;
		 param2[0]=officeid;
		 param2[1]=subMjrHd.split(",")[0].trim();
		 param2[2]=mnrHd.split(",")[0].trim();
		 param2[3]=oldNewReceipt;
		 //param2[1]=date;
		 tlist=new ArrayList();
		 tlist=dao.getTotalAmount(param2);//getting  challn gendata based on officeid and date
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
	 
	 public ArrayList getChallanData(EODChallanForm form) throws Exception{
		 ChallanUpdateDAO dao = new ChallanUpdateDAO();
		 
		 
	     String param2[]=new String[1];
	     
		 ArrayList tlist=null;
		 
	    param2[0]=form.getTxnid();
		 
		 tlist=new ArrayList();
		 tlist=dao.getChallandata(param2);//getting  challn gendata based on Txnid 
		 logger.debug("arraylist of gendata"+tlist);
		  return tlist;
	 }
	 
	 public ArrayList getEmpNameDesignation(String userId) throws Exception{
		 EODChallanDAO dao = new EODChallanDAO();
		 
		 
	     String param2[]=new String[1];
	     
		 ArrayList tlist=null;
		 
	    param2[0]=userId;
		 
		 tlist=new ArrayList();
		 tlist=dao.getEmpNameDesignation(param2);
		 logger.debug("arraylist of gendata"+tlist);
		  return tlist;
	 }
	 
	 /**
	     * Method 		: addEODDetails
	     * Description	: INSERTION OF EOD_CHALLAN_ID INTO IGRS_PAYMENT_MODE_DETAILS
	     * @param query : string ,
	     * @throws 		: Exception
	     * return Type  : boolean 
	     */
	  public boolean addEODDetails(EODChallanForm form,String uid) throws Exception{
		 EODChallanDAO dao1 		= new EODChallanDAO();
		 boolean 	insertflag	= false;
		 String[] 	param 		= new String[3];
		 String day,month,year;
		 String ddate	  =	form.getChdate();
		 StringTokenizer st=new StringTokenizer(ddate,"/");
		 day=st.nextToken();
		 month=st.nextToken();
		 year=st.nextToken();
		 String txnid	  =	form.getTxnid();
		 String officeId = form.getOfficeid();
		 System.out.println("INSIDE EODCHALLAN BD");
		 System.out.println("EOD Challan id generated is:.."+txnid);
		 System.out.println("EOD Cash collection date is:.."+ddate);
		 System.out.println("Office name is:.."+officeId);
		 
		 
		 param[0]	=	txnid; 
		 param[1]	=	getOracleDate(day,month,year);
		 param[2]	=	officeId;
		  
		 insertflag=dao1.addEODDetails(param);
		 
		 return insertflag;
	 }
	  
	  
	  
	  /**
	     * Method 		: addFinalEODDetails
	     * Description	: INSERTION OF EOD_CHALLAN_ID INTO IGRS_PAYMENT_MODE_DETAILS and eod generated challan in challan_gen_details
	     * @param query : Form ,
	     * @throws 		: Exception
	     * return Type  : boolean 
	     */
	  public boolean addFinalEODDetails(EODChallanForm form,String uid) throws NullPointerException,
                                                                              SQLException,Exception{
		  EODChallanDAO dao1 = new EODChallanDAO();
		  boolean insFlag = false;
		  insFlag = dao1.addFinalEODDetails(form, uid);
		  return insFlag;
		  
	  }
	  
	  /**
	     * Method 		: addEODGenDetails
	     * Description	: inserting eod generated challan in challan_gen_details table
	     * @param query : String
	     * @throws 		: Exception
	     * return Type  :Boolean
	     */
	 public boolean addEODGenDetails(EODChallanForm form,String uid) throws Exception{
		 EODChallanDAO dao5 = new EODChallanDAO();
		 boolean insertflag=false;
		 String[] param = new String[4];
		 String txnid	  =	form.getTxnid();
		 String officeId = form.getOfficeid();
		 String cashCollected = form.getAmt();
		 System.out.println("INSIDE EODCHALLAN BD");
		 System.out.println("EOD Challan id to be inserted is:.."+txnid);
		 System.out.println("Office id is:.."+officeId);
		 System.out.println("the amount to be inserted is..."+cashCollected);
		 param[0]=txnid;
		 param[1]=officeId;
		 param[2]= uid;
		 param[3]=cashCollected;
		 insertflag=dao5.addEODGenDetails(param);
		 return insertflag;
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
	 
	 public  String getOracleDate2(String day,String month,String year) {

	 	 
	 	 String inputDate = day + "-" + month + "-" + year;
	 	 SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	 	 logger.debug(" " + inputDate + " parses as ");
	 	 String finalDate = "";
	 	 Date newDate ;
	 	 try {
	 		 newDate = formatter.parse(inputDate);
	 		 SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	 		 finalDate = format.format(newDate);
	 		 logger.debug(finalDate);

	 	 	}
	 	 catch (Exception e) {
	 		 logger.error(e);
	 	 }

	 	 return finalDate;

	  }
	 public void cancelTrasanction()throws Exception{
		 EODChallanDAO dao7 = new EODChallanDAO();
			dao7.cancelTrasanction();
		}
	public String getsrName(String uid)throws Exception {
		 EODChallanDAO dao8 = new EODChallanDAO();
		 String name = dao8.getsrName(uid);
		return name;
	}
	
	
	/******************************************************************  
	  *   Method Name  :   getrequestDetails()
	  *   Arguments    :   office id
	  *   Return       :   ArrayList
	  *   Throws 	  :   Exception
	 *******************************************************************/  
	
	public ArrayList getrequestDetails(String txnId,String language) throws Exception{
		EODChallanDAO dao = new EODChallanDAO();
		ArrayList list = dao.getrequestDetails(txnId,language);
      
		return list;
	}
	public ArrayList getData(EODChallanForm form){
		String status="noData";
		ArrayList finalList = new ArrayList();
		EODChallanDAO dao;
		
		try {
			dao = new EODChallanDAO();
			ArrayList list = dao.getDetails(form);
			for(int i = 0; i<list.size(); i++){
				ArrayList rowList = (ArrayList)list.get(i);
				form.setsNo(i+1);
				form.setTransId((String)rowList.get(0));
				form.setChdate((String)rowList.get(1));
				form.setCreatedby((String)rowList.get(2));
				form.setError("data");
				finalList.add(form);
				form = new EODChallanForm();
				status="success";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return finalList;
	}
	public String getAllDetails(EODChallanForm form){
		String stat="";
		
		ArrayList finalList = new ArrayList();
		EODChallanDAO dao;
		
		try {
			dao = new EODChallanDAO();
			ArrayList list = dao.getAllDetails(form.getTransId());
			for(int i = 0; i<list.size(); i++){
				ArrayList rowList = (ArrayList)list.get(i);
				
				form.setTxnid((String)rowList.get(0));
				
				form.setCurrDate((String)rowList.get(1));
				
				form.setAmt((String)rowList.get(2));
				form.setChdate((String)rowList.get(1));
				form.setMinDate((String)rowList.get(3));
				form.setOfficeName((String)rowList.get(4));
				form.setCreatedby((String)rowList.get(5));
				form.setSrName((String)rowList.get(6));
				form.setRevMjrHeadId((String)rowList.get(7));
				form.setRevSubMjrHeadId((String)rowList.get(8));
				form.setRevMnrHeadId((String)rowList.get(9));
				form.setEprn((String)rowList.get(10));
				form.setOldNewReceipt(rowList.get(11)!=null?(String)rowList.get(11):"Y");
				form.setError("data");
				finalList.add(form);
				form = new EODChallanForm();
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return stat;
	}
	


	 

}
