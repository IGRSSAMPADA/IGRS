package com.wipro.igrs.payment.bd;

import org.apache.log4j.Logger;

import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.documentsearch.dto.DocumentSearchDTO;
import com.wipro.igrs.payment.bo.CashCounterBO;
import com.wipro.igrs.payment.dto.CashCounterDTO;
import com.wipro.igrs.payment.dto.PhysicalChallanDTO;
import com.wipro.igrs.payment.bd.EODChallanBD;
import com.wipro.igrs.payment.dao.CashCounterDAO;
import com.wipro.igrs.payment.dao.CashLinkedDAO;
import com.wipro.igrs.payment.dao.EODChallanDAO;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.jms.Session;

import com.wipro.igrs.payment.form.CashCounterForm;
import com.wipro.igrs.payment.form.EODChallanForm;
import com.wipro.igrs.reginit.constant.RegInitConstant;
/**
 * ===========================================================================
 * File           :   CashCounterBD.java
 * Description    :   Represents the Cash Payment BD Class
 * Author         :   vengamamba P
 * Created Date   :   Feb 20, 2008

 * ===========================================================================
 */
public class CashCounterBD {
	private Logger logger = (Logger) Logger.getLogger(CashCounterBD.class);
	 public CashCounterBD() {
	    }
	    
	 /**
	     * ===========================================================================
	     * Method         :   getPhotoIDList()
	     * Description    :   Returns list  of photoids and its values. 
	     * Arguments      :   
	     * return type    :   Arraylist
	     * Author         :   vengamamba P
	     * Created Date   :   feb 20, 2008
	     * ===========================================================================
	     */  
	 
	 public ArrayList getPhotoIDList(String languageLocale) throws Exception 
    {
		 CashCounterDAO dao1 = new CashCounterDAO();
	     ArrayList plist = dao1.getPhotoIdList();
	     ArrayList list = new ArrayList();

	     if (plist != null) {
	    		for (int i = 0; i < plist.size(); i++) {
	    			ArrayList lst = (ArrayList)plist.get(i);
	    			CashCounterDTO dto=new CashCounterDTO();
	    			dto.setValue((String)lst.get(0));
	    			if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
	    			dto.setName((String)lst.get(1));
	    			}else{
	    				dto.setName((String)lst.get(2));	
	    			}
	       	
	    			list.add(dto);
	    		}
	    	}

	    	return list;
    }
	 
	 /**
	     * Method 		: getserviceList
	     * Description	: returns list of function ids and values 
	     * @param query : 
	     * @throws 		: Exception
	     * return Type  :ArrayList
	     */
	 
	 public ArrayList getServiceList(String languageLocale) throws Exception{
		 CashCounterDAO dao2 = new CashCounterDAO();
	     ArrayList flist = dao2.getServiceList();
	     ArrayList list = new ArrayList();

	     if (flist != null) {
	    		for (int i = 0; i < flist.size(); i++) {
	    			ArrayList lst = (ArrayList)flist.get(i);
	    			CashCounterDTO dto=new CashCounterDTO();
	    			dto.setValue((String)lst.get(0));
	    			if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
	    			dto.setName((String)lst.get(1));
	    			}else{
	    				dto.setName((String)lst.get(2));	
	    			}
	       	
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
		 CashCounterDAO dao = new CashCounterDAO();
	     ArrayList blist = dao.getBankidList();
	     ArrayList list = new ArrayList();

	     if (blist != null) {
	    		for (int i = 0; i < blist.size(); i++) {
	    			ArrayList lst = (ArrayList)blist.get(i);
	    			CashCounterDTO dto=new CashCounterDTO();
	    			dto.setValue((String)lst.get(0));
	    			dto.setName((String)lst.get(1));
	       	
	    			list.add(dto);
	    		}
	    	}

	    	return list;
	 }
	 
	 /* Method Name : getChallanNoDetails
      * Arguments   : PaymentForm Bean and Selected Button NO 
      * Return      : if it success return Success
      *               other wise return fail 
      */
	 public String  getChallanNoDetails(CashCounterForm _paymentForm,int buttonNo) throws Exception {
		 CashCounterBO paymentBO = new CashCounterBO();	
		 String returnFlag = null;//paymentBO.getChallanNoDetail(_paymentForm,buttonNo);			 
			  return returnFlag;
		 }
	 
	 
	 

	 /**
	     * Method 		: Transidgenerator
	     * Description	: creating trasactionid for transaction
	     * return Type  :string of Transid 
	     */
	 
	 public  String transIDgenerator() 
	 {
         String id = "Cash" + new Date().getTime();
         logger.debug("this is transidgenerator () & value of ID " + id);
         return id;
	 }  
	 	 
	 /**
	     * Method 		: dateConvertion
	     * Description	: creating sysdate for transaction
	     * return Type  :string of date
	     */
	 
	 public  String dateConvertion()throws Exception 
	 {   String strOutDt = "";
		try{
			Date d=new Date();
		
		 logger.debug("date"+d);
		 strOutDt = new SimpleDateFormat("dd/MMM/yyyy").format(d);
		 logger.debug("date converted="+strOutDt);
		}catch(Exception e){
			throw e;
		}
		return strOutDt;
	 }  
	 /**
	    * Method 		: total amt calculation
	    * Description	: storing total amt and idlist into dto obj from BO
	    * return Type  :formbean
	    */	 
	 
	public CashCounterForm amtCalc(CashCounterForm form)throws Exception{

		CashCounterBO paymentBO = new CashCounterBO();	
		PhysicalChallanDTO chdtoobj=new PhysicalChallanDTO();
		CashCounterForm form1=new CashCounterForm();
		String cashamt=form.getCashAmt();
		 double totamt =0;
		 ArrayList tid=new ArrayList();
		 ArrayList tid1=new ArrayList();
		 
		 form1.getChallanDTO().setTotalAmt(totamt+"");
		String checkchallan=form.getCheckboxAssociateChallan();
		logger.debug("checked state of"+checkchallan);
		 if(checkchallan!=null){
			 if (checkchallan.equals("on")){
				 //form1 = paymentBO.getChallanDetail(form);
			 }	 
		 }	 
		 
		 totamt =totamt+Double.parseDouble(form1.getChallanDTO().getTotalAmt());
		 tid = form1.getChallanDTO().getTransactionId();
		 logger.debug("tid"+tid);
		 if (tid==null)
			 tid=tid1;
		 logger.debug("tid="+tid);
		 logger.debug("toal before cash"+totamt);
		 totamt=totamt+Double.valueOf(cashamt.trim()).doubleValue();
		 logger.debug("toal After cash"+totamt);
		 String amount=totamt+"";
		
		 chdtoobj.setTransactionId(tid);
		 chdtoobj.setTotalAmt(amount);
		 form1.setChallanDTO(chdtoobj);
		 
		 return form1;
	}
	 /**
	    * Method 		: getPaymode
	    * Description	: getting func name base don funcid
	    * return Type  :String
	    * 
	    */	 
	 public String getPaymode(String pid)throws Exception{	
		 String modeName=null;
		 CashCounterDAO dao4 = new CashCounterDAO();
		 modeName=dao4.getPaymode(pid);
		 return modeName;
	 }
	 /**
	    * Method 		: getfuncName ()
	    * Description	: getting func name base don funcid
	    * return Type  :String
	    * 
	    */	 
	 public String getFuncName(String fid)throws Exception{	
		 String funcName=null;
		 CashCounterDAO dao4 = new CashCounterDAO();
		 funcName=dao4.getFuncName(fid);
		 return funcName;
	 }
	 /**
	    * Method 		: getOffice ()
	    * Description	: getting ofice id based on the user id
	    * return Type  :String
	    * 
	    */	 
	 public String getOffice(String uid)throws Exception{	
		 String officeid=null;
		 CashCounterDAO dao4 = new CashCounterDAO();
		 officeid=dao4.getOfficeid(uid);
		 return officeid;
	 }
	 /**
	    * Method 		: getSum ()
	    * Description	: getting sum of the amount collected
	    * return Type  :String
	    * 
	    */	 
	 public String getSum(String oid)throws Exception{	
		 String sum=null;
		 CashCounterDAO dao4 = new CashCounterDAO();
		 sum=dao4.getSum(oid);
		 logger.debug("inside BD.....sum is"+sum);
		 return sum;
	 }
	 /**
	    * Method 		: getLimit ()
	    * Description	: getting limit for the amount to be collected at sro
	    * return Type  :String
	    * 
	    */	 
	 public String getLimit(String oid)throws Exception{	
		 String limit=null;
		 CashCounterDAO dao4 = new CashCounterDAO();
		 limit=dao4.getLimit(oid);
		 logger.debug("inside BD.....limit is"+limit);
		 return limit;
	 }
	 
	 /**
	    * Method 		: getOfficeid ()
	    * Description	: getting office id of the logged in user
	    * return Type  :String
	    * 
	    */
	 public String getOfficeid(String uid)throws Exception{
			String oid=null;
			CashCounterDAO dao9 = new CashCounterDAO();
			oid = dao9.getOfficeid(uid);
			return oid;
	 }
	 
	 /**
	    * Method 		: getDistrictId ()
	    * Description	: getting district id of the logged in user
	    * return Type  :String
	    * 
	    */
	 public String getDistrictId(String oid)throws Exception{
			String did=null;
			CashCounterDAO dao9 = new CashCounterDAO();
			did = dao9.getDistrictId(oid);
			return did;
	 }
	 /**
	    * Method 		: getDistrictName ()
	    * Description	: getting district name of the logged in user
	    * return Type  :String
	    * 
	    */
	 public String getDistrictName(String did,String languageLocale)throws Exception{
			String dname=null;
			CashCounterDAO dao9 = new CashCounterDAO();
			dname = dao9.getDistrictName(did,languageLocale);
			return dname;
	 }
	 
	 /**
	    * Method 		: getempName ()
	    * Description	: getting emp name of the logged in user
	    * return Type  :String
	    * 
	    */
	 public String getempName(String uid)throws Exception{
			String did=null;
			CashCounterDAO dao10 = new CashCounterDAO();
			did = dao10.getempName(uid);
			return did;
	 }
	 /**
	    * Method 		: getOthersFeeDuty ()
	    * Description	: getting fees from fee matrix
	    * return Type  :DTO
	    * 
	    */
	 public CashCounterDTO getOthersFeeDuty(String _retFunId,String _serId,String _userType)throws Exception{
		    CashCounterDTO searchDto=new CashCounterDTO();
	    	try{
	    		System.out.println("values are.........."+_retFunId+"service id is"+_serId+"user type id is.."+_userType);
	    		CashCounterDAO dao1 = new CashCounterDAO();
	    		logger.debug("in bd--<><>"+_retFunId+_serId+_userType);
	    		ArrayList otherList=dao1.getOthersFeeDuty(_retFunId, _serId, _userType);
	    		if(otherList!=null && otherList.size()>0){
	    			
	    			searchDto.setOtherFees1((String)otherList.get(1).toString());
	    			searchDto.setTotalFees1((String)otherList.get(2));
	    			searchDto.setServiceFees1((String)otherList.get(0));
	    		}
	    		
	    	}catch(Exception e){
	    		e.printStackTrace();
	    		
	    	}
	    	
	    	
	    	return searchDto;
	    }
	 
	 /**
	     * Method 		: addCombineDetails
	     * Description	: inserting combine details into table
	     * @param query : formbean as param
	     * @throws 		: Exception
	     * return Type  :Boolean
	     */
	
	 public boolean addCombineDetails(CashCounterForm form,String uid) throws Exception{
		 CashCounterDAO dao1 		= new CashCounterDAO();
		 boolean 	insertflag	= false;
		 String[] 	param 		= new String[7];
		 
		// Format the current time.
		 SimpleDateFormat formatter
		     = new SimpleDateFormat ("dd/MM/yyyy");
		 Date currentTime_1 = new Date();
		 String dateString = formatter.format(currentTime_1);

			 
		 String txnid	  =	form.getCombId();
		 String fname	  =	form.getFirstName();
		 String mname	  =	form.getMiddleName();
		 String lname	  =	form.getLastName();
		 String gender	  =	form.getGender();
		 String amount    = form.getCashAmt();
		 
		 param[0]	=	txnid; 
		 param[1]	=	fname;
		 param[2]	=	mname;
		 param[3]	=	lname;
		 param[4]	=	gender;
		 param[5]	=	amount;
		 param[6]	=	uid;
		
		 insertflag=dao1.addCombineDetails(param);
		 
		 return insertflag;
	 }
	 
	 
	 
	 /**
	     * Method 		: addFinalCashDetails
	     * Description	: returns boolean gives success of insert or not
	     * @param query : array of string ,
	     * return Type  :boolean
	     */
		 public boolean addFinalCashDetails(CashCounterForm form, HashMap map, String uid)throws Exception{
			 Boolean returnflg = false;
			 CashCounterDAO dao1 = new CashCounterDAO();
			 returnflg = dao1.addFinalCashDetails(form, map, uid);
			 return returnflg;
		 }
		 
		 
		 /**
		     * ===========================================================================
		     * Method         :   getTransPrintList()
		     * Description    :   Returns list  of transaction ids
		     * Arguments      :   
		     * return type    :   Arraylist
		     
		     * ===========================================================================
		     */  
			public ArrayList getTransPrintList(String comid,String languageLocale) throws Exception{
				 ArrayList mainlist = new ArrayList();
				 ArrayList list1 = new ArrayList();
				 ArrayList list2 = new ArrayList();
				 PhysicalChallanDTO chdto=new PhysicalChallanDTO();
				 CashCounterDAO dao1 = new CashCounterDAO();
				 mainlist =  dao1.getTransPrintList(comid);
				 for(int i = 0; i< mainlist.size();i++){
					 list1.add(mainlist.get(i));
					 list1.trimToSize();
					 logger.debug("lllllllllllllllll"+list1.get(i));
					 
					 ArrayList listaa = new ArrayList();
					 listaa=(ArrayList) list1.get(i);
					 
					 //String arry1 = (String) listaa.get(i);
					 //logger.debug("hhhhhhhhhhhhhhhhhhhhhh"+arry1);
					 //String[]arry2 = arry1.split(",");
					 chdto.setDepositorName((String) listaa.get(0));
					 logger.debug(chdto.getDepositorName());
					 chdto.setTxnid((String) listaa.get(1));
					 logger.debug(chdto.getTxnid());
					 chdto.setTotalAmt((String) listaa.get(2));
					 logger.debug(chdto.getTotalAmt());
					 String d1 = (String) listaa.get(3);
					 /*logger.debug("the date from backend in bd class is..................."+d1);
					 SimpleDateFormat sdf1 = new SimpleDateFormat ("MM/dd/yyyy KK:mm:ss");
					 SimpleDateFormat sdf2 = new SimpleDateFormat ("dd/MM/yyyy hh:mm:ss a");
					 Date d2 = sdf1.parse(d1);
					 logger.debug("the date from backend after format in bd class is..................."+d2);
					 String d3 = sdf2.format(d2);
					 logger.debug("the date After final formatting in bd class is..................."+d3);
					 */chdto.setCurrentdate(d1);
					 //chdto.setCurrentdate((String) listaa.get(3));
					 logger.debug(chdto.getCurrentdate());
					 if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
					 chdto.setFuncName((String) listaa.get(4));
					 }else{
						 chdto.setFuncName((String) listaa.get(8));
					 }
					 chdto.setPosRefNo((listaa.get(9)!=null && !("").equalsIgnoreCase(listaa.get(9).toString()))?listaa.get(9).toString():"-");
					 chdto.setRevMjrHd((String) listaa.get(5));
					 chdto.setRevSbMjrHd((String) listaa.get(6));
					 chdto.setRevMnrHd((String) listaa.get(7));
					 
					 
					 if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
						 chdto.setOldNewReceipt((listaa.get(10)!=null && ("Y").equalsIgnoreCase(listaa.get(10).toString()))?RegInitConstant.YES_ENGLISH:RegInitConstant.NO_ENGLISH);
						 }else{
							 chdto.setOldNewReceipt((listaa.get(10)!=null && ("Y").equalsIgnoreCase(listaa.get(10).toString()))?RegInitConstant.YES_HINDI:RegInitConstant.NO_HINDI);
						 }
					 
					 logger.debug(chdto.getFuncName());
					 list2.add(i, chdto);
					 chdto = new PhysicalChallanDTO();
				 }
				 
				 return list2;
			}
			
			public ArrayList getTransPrintListView(String transId,String languageLocale) throws Exception{
				 ArrayList mainlist = new ArrayList();
				 ArrayList list1 = new ArrayList();
				 ArrayList list2 = new ArrayList();
				 PhysicalChallanDTO chdto=new PhysicalChallanDTO();
				 CashCounterDAO dao1 = new CashCounterDAO();
				 mainlist =  dao1.getTransPrintListView(transId);
				 for(int i = 0; i< mainlist.size();i++){
					 list1.add(mainlist.get(i));
					 list1.trimToSize();
					 logger.debug("lllllllllllllllll"+list1.get(i));
					 
					 ArrayList listaa = new ArrayList();
					 listaa=(ArrayList) list1.get(i);
					 
					 
					 chdto.setDepositorName((String) listaa.get(0));
					 logger.debug(chdto.getDepositorName());
					 chdto.setTxnid((String) listaa.get(1));
					 logger.debug(chdto.getTxnid());
					 chdto.setTotalAmt((String) listaa.get(2));
					 logger.debug(chdto.getTotalAmt());
					 String d1 = (String) listaa.get(3);
					 chdto.setCurrentdate(d1);
					 
					 logger.debug(chdto.getCurrentdate());
					 if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
					 chdto.setFuncName((String) listaa.get(4));
					 }else{
						 chdto.setFuncName((String) listaa.get(8));
					 }
					 chdto.setPosRefNo((listaa.get(9)!=null && !("").equalsIgnoreCase(listaa.get(9).toString()))?listaa.get(9).toString():"-");
					 chdto.setRevMjrHd((String) listaa.get(5));
					 chdto.setRevSbMjrHd((String) listaa.get(6));
					 chdto.setRevMnrHd((String) listaa.get(7));
					 
					 
					 if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
						 chdto.setOldNewReceipt((listaa.get(10)!=null && ("Y").equalsIgnoreCase(listaa.get(10).toString()))?RegInitConstant.YES_ENGLISH:RegInitConstant.NO_ENGLISH);
						 }else{
							 chdto.setOldNewReceipt((listaa.get(10)!=null && ("Y").equalsIgnoreCase(listaa.get(10).toString()))?RegInitConstant.YES_HINDI:RegInitConstant.NO_HINDI);
						 }
					 
					 logger.debug(chdto.getFuncName());
					 list2.add(i, chdto);
					 chdto = new PhysicalChallanDTO();
				 }
				 
				 return list2;
			}
	 /**
	     * Method 		: addCashDetails
	     * Description	: inserting cash details into table
	     * @param query : formbean as param
	     * @throws 		: Exception
	     * return Type  :Boolean
	     */
	
	 public boolean addCashDetails(CashCounterForm form,String uid) throws Exception{
		 CashCounterDAO dao1 = new CashCounterDAO();
		 EODChallanBD bd  = new EODChallanBD();
		 boolean 	insertflag	= false;
		 String[] 	param 		= new String[23];
		 
		// Format the current time.
		 SimpleDateFormat formatter
		     = new SimpleDateFormat ("dd/MM/yyyy");
		 Date currentTime_1 = new Date();
		 String dateString = formatter.format(currentTime_1);

			 
		 String txnid	  =	form.getChallanDTO().getTxnid();
		 String ddate	  =	form.getChallanDTO().getCurrentdate();
		 String fname	  =	form.getFirstName();
		 String mname	  =	form.getMiddleName();
		 String lname	  =	form.getLastName();
		 String gender	  =	form.getGender();
		 String age	      =	form.getAge();
		 String gname	  =	form.getFatherName();
		 String pid	      =	form.getListID();
		 String pidno	  =	form.getIdNo();
		 String bankname  =	form.getBankName();
		 String bankaddr  =	form.getBankAddr();
		 String serviceid =	form.getListService();
		 String amount	  =	form.getChallanDTO().getTotalAmt();
		 String remarks	  =	form.getRemarks();
		 String oid	      = form.getOffId();
		 String ofName    = form.getOffName();
		 String did	      = form.getDistId();
		 String comid     = form.getCombId();
		 param[0]	=	txnid; 
		 param[1]	=	amount;
		 param[2]	=	amount;
		 param[3]	=	uid;
		 param[4]	=	uid;
		 param[5]	=	fname;
		 param[6]	=	mname;
		 param[7]	=	lname;
		 param[8]	=	gender;		
		 param[9]	=	age;
		 param[10]	=	pid;
		 param[11]	=	pidno;
		 param[12]	=	gname;		 
		 if (form.getCheckboxAssociateChallan()== null)
			 param[13]	=	"IGRS_PT_001";
		 else
			 param[13]	=	"IGRS_PT_002";
				 
		 param[14]	=	bankname;
		 param[15]	=	bankaddr;
		 param[16]	=	oid;
		 param[17]	=	uid;
		 param[18]	=	did;
		 param[19]	=	serviceid;
		 param[20]	=	remarks;
		 param[21]	=	"A";
		 param[22]  =    comid;
		 //form.setOffName(ofName);		 
		 insertflag=dao1.addCashDetails(param);
		 
		 return insertflag;
	 }
	 /**
	     * Method 		: addTransidDetails
	     * Description	: inserting tid details into mapping table
	     * @param query : Transcationid
	     * @throws 		: Exception
	     * return Type  :Boolean
	     */
	 public boolean addTransidDetails(String tid,String ptid) throws Exception{
		 CashCounterDAO dao5 = new CashCounterDAO();
		 boolean insertflag=false;
		 String[] param = new String[2];
	 
		 param[0]=ptid;
		 logger.debug("in bd tid="+tid);
		 param[1]=tid;
		 insertflag=dao5.addTransidDetails(param);
		 return insertflag;
	 }
	 /**
	     * Method 		: updateStatus
	     * Description	: updating status flag of challan id
	     * @param query : Transcationid
	     * @throws 		: Exception
	     * return Type  :Boolean
	     */
	 public boolean updateStatus(String tid) throws Exception{
		 CashCounterDAO dao6 = new CashCounterDAO();
		 boolean insertflag=false;
		 String[] param = new String[1];
	 
		 param[0]=tid;
		 logger.debug("in bd tid="+tid);
		 insertflag=dao6.updateStatus(param);
		 return insertflag;
	 }
	 /**
	     * Method 		: commitingTransaction
	     * Description	: commiting whole trans success
	     * @param query : 
	     * @throws 		: Exception
	     * return Type  :void
	     */ 
	 
    public void commitingTrasanction()throws Exception{
    	CashCounterDAO dao8 = new CashCounterDAO();
    	dao8.commitingTrasanction();
    }
    /**
     * Method 		: cancelTransaction
     * Description	: rollback if whole trans fails
     * @param query : 
     * @throws 		: Exception
     * return Type  :void
     */ 
 
public void cancelTrasanction()throws Exception{
	CashCounterDAO dao7 = new CashCounterDAO();
	dao7.cancelTrasanction();
}
/**
 * ===========================================================================
 * Method         :   getRevHeadsFee()
 * Description    :   for retrieving revenue heads and fee applicable for DRS officials 
 * Arguments      :   String
 * return type    :   Arraylist
 * Author         :   ROOPAM MEHTA
 * Date           :   24 feb 2014
 
 * ===========================================================================
 */  
 public String[] getRevHeadsFee(String serviceId)throws Exception{
	 
	 CashCounterDAO dao1 = new CashCounterDAO();
	 ArrayList list = dao1.getRevHeadsFee(serviceId);
	 
	 String[] values;
	 
	 if(list!=null && list.size()==1){
		 
		 ArrayList rowList=(ArrayList)list.get(0);
		 values=new String[rowList.size()];
		 
		 for(int i=0;i<rowList.size();i++){
			 values[i]=rowList.get(i)!=null?rowList.get(i).toString():""; 
			 
		 }
		 
		 /*values[0]=rowList.get(0)!=null?rowList.get(0).toString():"";
		 values[1]=rowList.get(1)!=null?rowList.get(1).toString():"";
		 values[2]=rowList.get(2)!=null?rowList.get(2).toString():"";
		 values[3]=rowList.get(3)!=null?rowList.get(3).toString():"";
		 values[4]=rowList.get(4)!=null?rowList.get(4).toString():"";
		 values[5]=rowList.get(5)!=null?rowList.get(5).toString():"";
		 values[6]=rowList.get(6)!=null?rowList.get(6).toString():"";
		 values[7]=rowList.get(7)!=null?rowList.get(7).toString():"";
		 values[8]=rowList.get(8)!=null?rowList.get(8).toString():"";*/
		 
		 
		 
		 //String str=rowList.toString();
		 //str=str.substring(1, str.length()-1);
		 //values=str.split(",");
		 
	 }else{
		 values=null; 
	 }
	 
	 
	 return values;
 }
 
 public ArrayList getData(CashCounterForm form){
		String status="noData";
		ArrayList finalList = new ArrayList();
		CashCounterDAO dao;
		
		try {
			dao = new CashCounterDAO();
			ArrayList list = dao.getDetails(form);
			
			CashCounterForm form1;
			
			for(int i = 0; i<list.size(); i++){
				ArrayList rowList = (ArrayList)list.get(i);
				form1=new CashCounterForm();
				form1.setRowIndex(Integer.toString(i+1));
				form1.setTransId((String)rowList.get(0));
				form1.setChdate((String)rowList.get(1));
				form1.setCashAmt((String)rowList.get(2));
				//form.setCreatedby((String)rowList.get(2));
				//form.setError("data");
				finalList.add(form1);
				//form = new CashCounterForm();
				status="success";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return finalList;
	}
//akansha
	public String getTransactiionId(String comid,String languageLocale) throws Exception{
		 String mainlist ="";
		 ArrayList list1 = new ArrayList();
		 ArrayList list2 = new ArrayList();
		 PhysicalChallanDTO chdto=new PhysicalChallanDTO();
		 CashLinkedDAO dao1 = new CashLinkedDAO();
		 mainlist =  dao1.getTransactionid(comid);
		/* for(int i = 0; i< mainlist.size();i++){
			 list1.add(mainlist.get(i));
			 list1.trimToSize();
			 logger.debug("lllllllllllllllll"+list1.get(i));
			 
			 ArrayList listaa = new ArrayList();
			 listaa=(ArrayList) list1.get(i);
			 
			
			
			 chdto.setTxnid((String) listaa.get(0));
			 logger.debug(chdto.getTxnid());
			
			 chdto.setTotalAmt((String) listaa.get(1));
			 logger.debug(chdto.getTotalAmt());
			
			 
			 list2.add(chdto);
			 
			 chdto = new PhysicalChallanDTO();
		 }*/
		 
		 return mainlist;
	}
	
	public String getService(String languageLocale,String functionId) throws Exception{
		 CashLinkedDAO dao2 = new CashLinkedDAO();
		 String function ="";
		 if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
			 function = dao2.getServiceEng(functionId);
		 }
		 else{
			 function = dao2.getServiceHnd(functionId);
		 }
		
	    
	     return function;
	 }
}
