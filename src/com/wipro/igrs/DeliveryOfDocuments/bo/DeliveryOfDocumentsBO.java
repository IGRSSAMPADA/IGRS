package com.wipro.igrs.DeliveryOfDocuments.bo;

import java.io.File;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;




import com.newgen.burning.BurningImageAndText;
import com.newgen.burning.Dataclass;
import com.newgen.burning.DocumentOperations;
import com.newgen.burning.ODServerDetails;
import com.wipro.igrs.DeliveryOfDocuments.dto.DeliveryOfDocumentsDTO;
import com.wipro.igrs.DeliveryOfDocuments.dao.DeliveryOfDocumentsDAO;
import com.wipro.igrs.Seals.constant.SealsConstant;
import com.wipro.igrs.common.PropertiesFileReader;
import com.wipro.igrs.estamping.dao.DutyCalculationDAO;
import com.wipro.igrs.estamping.form.DutyCalculationForm;
import com.wipro.igrs.regCompChecker.constant.RegCompCheckerConstant;
import com.wipro.igrs.regCompChecker.form.RegCompCheckerForm;

public class DeliveryOfDocumentsBO {
	
	
	private Logger logger = (Logger) Logger.getLogger(DeliveryOfDocumentsBO.class);
	
	

	 /******************************************************************  
	  *   Method Name  :   getStatus()
	  *   Arguments    :   -
	  *   Return       :   ArrayList
	  *   Throws 	  :   Exception
	 *******************************************************************/  
	public ArrayList getStatus(String lang) throws Exception{
	  
		ArrayList mainList = new ArrayList();
        ArrayList list = new ArrayList();
        DeliveryOfDocumentsDAO dao = new DeliveryOfDocumentsDAO();
        list = dao.getStatus();
        ArrayList subList = null;
        DeliveryOfDocumentsDTO dto = new DeliveryOfDocumentsDTO();
        for (int i = 0; i < list.size(); i++) {
            subList = (ArrayList)list.get(i);
            dto = new DeliveryOfDocumentsDTO();
            dto.setValue(subList.get(1).toString());
            if(lang.equalsIgnoreCase("en"))
            	dto.setName(subList.get(0).toString());
            else
            	dto.setName(subList.get(2).toString());
            mainList.add(dto);
        	}
		return mainList;
	}
	/******************************************************************  
	  *   Method Name  :   getChangestatusList() Rupali
	  *   Arguments    :   -
	  *   Return       :   ArrayList
	  *   Throws 	  :   Exception
	 *******************************************************************/
	
	public ArrayList getChangestatusList(String lang) throws Exception{
		  
		ArrayList mainList = new ArrayList();
        ArrayList list = new ArrayList();
        DeliveryOfDocumentsDAO dao = new DeliveryOfDocumentsDAO();
        list = dao.getChangestatusList();
        ArrayList subList = null;
        DeliveryOfDocumentsDTO dto = new DeliveryOfDocumentsDTO();
        for (int i = 0; i < list.size(); i++) {
            subList = (ArrayList)list.get(i);
            dto = new DeliveryOfDocumentsDTO();
            dto.setValue(subList.get(1).toString());
            if(lang.equalsIgnoreCase("en"))
            	dto.setName(subList.get(0).toString());
            else
            	dto.setName(subList.get(2).toString());
            mainList.add(dto);
        	}
		return mainList;
	}
	
	 /******************************************************************  
	  *   Method Name  :   getPrintStatus() Rupali
	  *   Arguments    :   -
	  *   Return       :   ArrayList
	  *   Throws 	  :   Exception
	 *******************************************************************/  
	public ArrayList getPrintStatus(String lang) throws Exception{
	  
		ArrayList mainList = new ArrayList();
       ArrayList list = new ArrayList();
       DeliveryOfDocumentsDAO dao = new DeliveryOfDocumentsDAO();
       list = dao.getPrintStatus();
       ArrayList subList = null;
       DeliveryOfDocumentsDTO dto = new DeliveryOfDocumentsDTO();
       for (int i = 0; i < list.size(); i++) {
           subList = (ArrayList)list.get(i);
           dto = new DeliveryOfDocumentsDTO();
           dto.setValue(subList.get(1).toString());
           if(lang.equalsIgnoreCase("en"))
           	dto.setName(subList.get(0).toString());
           else
           	dto.setName(subList.get(2).toString());
           mainList.add(dto);
       	}
		return mainList;
	}
	

	

	 /******************************************************************  
	  *   Method Name  :   getregDetl()
	  *   Arguments    :   registration_completion_Number, status of doc, from date, to date, office id
	  *   Return       :   ArrayList
	  *   Throws 	  :   Exception
	 *******************************************************************/  
	public ArrayList getregDetl(String regno, String status, String frmDt, String toDt, String offId, String lang, ArrayList offcList, boolean flag) throws Exception{
		 ArrayList mainlist = new ArrayList();
		 ArrayList list1 = new ArrayList();
		 ArrayList list2 = new ArrayList();
		 DeliveryOfDocumentsDTO dto = new DeliveryOfDocumentsDTO();
		 DeliveryOfDocumentsDAO dao = new DeliveryOfDocumentsDAO();
		 logger.debug("Delivery of Doc BO--------------------->");
		 mainlist =  dao.getregDetl(regno, status, frmDt, toDt, offId, offcList, flag);
		logger.debug("Delivery of Doc BO--------------------->");
		 for(int i = 0; i< mainlist.size();i++){
			 list1.add(mainlist.get(i));
			 list1.trimToSize();
			 ArrayList listaa = new ArrayList();
			 listaa=(ArrayList) list1.get(i);
			 dto.setSelecRegNo((String) listaa.get(0));
			 logger.debug(dto.getSelecRegNo());
			 dto.setRegCompDt((String) listaa.get(1));
			 logger.debug(dto.getRegCompDt());
			 dto.setDocStatusID((String) listaa.get(2));
			 logger.debug(dto.getDocStatusID());
			 dto.setSrID((String) listaa.get(14));
			 if(lang.equalsIgnoreCase("en"))
				 dto.setDocStatusName((String) listaa.get(3));
			 else
				 dto.setDocStatusName((String) listaa.get(12));
			 logger.debug(dto.getDocStatusName());
			 dto.setRegCumStatus((String) listaa.get(0)+"~"+(String) listaa.get(1)+"~"+(String) listaa.get(2)+"~"+
					             (String) listaa.get(3)+"~"+(String) listaa.get(4)+"~"+(String) listaa.get(5)+"~"+
					             (String) listaa.get(6)+"~"+(String) listaa.get(7)+"~"+(String) listaa.get(8)+"~"+
					             (String) listaa.get(9)+"~"+(String) listaa.get(10)+"~"+(String)listaa.get(11)+"~"+(String)listaa.get(12)+"~"+(String)listaa.get(13)+"~"+(String)listaa.get(14));
			 list2.add(i, dto);
			 dto = new DeliveryOfDocumentsDTO();
		 }
		 return list2;
	}
	
	 /******************************************************************  
	  *   Method Name  :   getregDetforChangeStatus()
	  *   Arguments    :   registration_completion_Number, status of doc,  office id
	  *   Return       :   ArrayList
	  *   Throws 	  :   Exception
	 *******************************************************************/  
	public String getregDetforChangeStatus(String regno, String status,  String offId, String lang, ArrayList offcList, boolean flag) throws Exception{
		
		String flg = "";
		DeliveryOfDocumentsDAO dao = new DeliveryOfDocumentsDAO();
		 flg = dao.getregDetforChangeStatus(regno, status, offId, offcList, flag);
		return flg;
		
	}

	/******************************************************************  
	  *   Method Name  :   getregDetlDr()
	  *   Arguments    :   registration_completion_Number, status of doc, from date, to date, district id
	  *   Return       :   ArrayList
	  *   Throws 	  :   Exception
	 *******************************************************************/  
	public ArrayList getregDetlDr(String regno, String status, String frmDt, String toDt, ArrayList offcList) throws Exception{
		 ArrayList mainlist = new ArrayList();
		 ArrayList list1 = new ArrayList();
		 ArrayList list2 = new ArrayList();
		 DeliveryOfDocumentsDTO dto = new DeliveryOfDocumentsDTO();
		 DeliveryOfDocumentsDAO dao = new DeliveryOfDocumentsDAO();
		 mainlist =  dao.getregDetlDr(regno, status, frmDt, toDt, offcList);
		
		 for(int i = 0; i< mainlist.size();i++){
			 list1.add(mainlist.get(i));
			 list1.trimToSize();
			 ArrayList listaa = new ArrayList();
			 listaa=(ArrayList) list1.get(i);
			 dto.setSelecRegNo((String) listaa.get(0));
			 logger.debug(dto.getSelecRegNo());
			 dto.setRegCompDt((String) listaa.get(1));
			 logger.debug(dto.getRegCompDt());
			 dto.setDocStatusID((String) listaa.get(2));
			 logger.debug(dto.getDocStatusID());
			 dto.setDocStatusName((String) listaa.get(3));
			 logger.debug(dto.getDocStatusName());
			 dto.setRegCumStatus((String) listaa.get(0)+"~"+(String) listaa.get(1)+"~"+(String) listaa.get(2)+"~"+
					             (String) listaa.get(3)+"~"+(String) listaa.get(4)+"~"+(String) listaa.get(5)+"~"+
					             (String) listaa.get(6)+"~"+(String) listaa.get(7)+"~"+(String) listaa.get(8)+"~"+
					             (String) listaa.get(9)+"~"+(String) listaa.get(10)+"~"+(String) listaa.get(11));
			 list2.add(i, dto);
			 dto = new DeliveryOfDocumentsDTO();
		 }
		 return list2;
	}
	
	/******************************************************************  
	  *   Method Name  :   getregDetlDr()
	  *   Arguments    :   registration_completion_Number, status of doc, from date, to date, district id
	  *   Return       :   ArrayList
	  *   Throws 	  :   Exception
	 *******************************************************************/  
	public ArrayList getregDetlDr(String regno,  ArrayList offcList) throws Exception{
		 ArrayList mainlist = new ArrayList();
		 ArrayList list1 = new ArrayList();
		 ArrayList list2 = new ArrayList();
		 DeliveryOfDocumentsDTO dto = new DeliveryOfDocumentsDTO();
		 DeliveryOfDocumentsDAO dao = new DeliveryOfDocumentsDAO();
		 mainlist =  dao.getregDetlDr(regno,   offcList);
		
		 for(int i = 0; i< mainlist.size();i++){
			 list1.add(mainlist.get(i));
			 list1.trimToSize();
			 ArrayList listaa = new ArrayList();
			 listaa=(ArrayList) list1.get(i);
			 dto.setSelecRegNo((String) listaa.get(0));
			 logger.debug(dto.getSelecRegNo());
			 dto.setRegCompDt((String) listaa.get(1));
			 logger.debug(dto.getRegCompDt());
			 dto.setDocStatusID((String) listaa.get(2));
			 logger.debug(dto.getDocStatusID());
			 dto.setDocStatusName((String) listaa.get(3));
			 logger.debug(dto.getDocStatusName());
			 dto.setRegCumStatus((String) listaa.get(0)+"~"+(String) listaa.get(1)+"~"+(String) listaa.get(2)+"~"+
					             (String) listaa.get(3)+"~"+(String) listaa.get(4)+"~"+(String) listaa.get(5)+"~"+
					             (String) listaa.get(6)+"~"+(String) listaa.get(7)+"~"+(String) listaa.get(8)+"~"+
					             (String) listaa.get(9)+"~"+(String) listaa.get(10)+"~"+(String) listaa.get(11));
			 list2.add(i, dto);
			 dto = new DeliveryOfDocumentsDTO();
		 }
		 return list2;
	}
	
	 /******************************************************************  
	  *   Method Name  :   getTransDetl()
	  *   Arguments    :   registration_completion_Number
	  *   Return       :   ArrayList
	  *   Throws 	  :   Exception
	 *******************************************************************/  
	public ArrayList getTransDetl(String regno) throws Exception{
		 ArrayList mainlist = new ArrayList();
		 ArrayList list1 = new ArrayList();
		 ArrayList list2 = new ArrayList();
		 DeliveryOfDocumentsDTO dto = new DeliveryOfDocumentsDTO();
		 DeliveryOfDocumentsDAO dao = new DeliveryOfDocumentsDAO();
		 mainlist =  dao.getTransDetl(regno);
		
		 for(int i = 0; i< mainlist.size();i++){
			 list1.add(mainlist.get(i));
			 list1.trimToSize();
			 ArrayList listaa = new ArrayList();
			 listaa=(ArrayList) list1.get(i);
			 String fname=((String) listaa.get(0));
			 logger.debug(fname);
			 String authNm=((String) listaa.get(1));
			 logger.debug(authNm);
			 String comb;
			 if(authNm!=null){
				 dto.setPartyNm(authNm);
			 }else dto.setPartyNm(fname);
			 list2.add(i, dto);
			 dto = new DeliveryOfDocumentsDTO();
		 }
		 return list2;
	}
	
	
	/******************************************************************  
	  *   Method Name  :   getrecpDetl()
	  *   Arguments    :   registration_completion_Number
	  *   Return       :   ArrayList
	  *   Throws 	  :   Exception
	 *******************************************************************/  
	public ArrayList getrecpDetl(String regno) throws Exception{
		 ArrayList mainlist = new ArrayList();
		 ArrayList list1 = new ArrayList();
		 ArrayList list2 = new ArrayList();
		 DeliveryOfDocumentsDTO dto1 = new DeliveryOfDocumentsDTO();
		 DeliveryOfDocumentsDAO dao = new DeliveryOfDocumentsDAO();
		 mainlist =  dao.getrecpDetl(regno);
		
		 for(int i = 0; i< mainlist.size();i++){
			
			 list1.add(mainlist.get(i));
			 list1.trimToSize();
			 ArrayList listaa = new ArrayList();
			 listaa=(ArrayList) list1.get(i);
			 String fname=((String) listaa.get(0));
			 dto1.setPartyNmDisplay(fname);
			 list2.add(i, dto1);
			 dto1 = new DeliveryOfDocumentsDTO();
		 }
		 return list2;
	}
	

	/******************************************************************  
	  *   Method Name  :   getpartyDetl()
	  *   Arguments    :   registration_completion_Number
	  *   Return       :   ArrayList
	  *   Throws 	  :   Exception
	 *******************************************************************/  
	public ArrayList getpartyDetl(String regno,String lang) throws Exception{
		 ArrayList mainlist = new ArrayList();
		 ArrayList list1 = new ArrayList();
		 ArrayList list2 = new ArrayList();
		 DeliveryOfDocumentsDTO dto2 = new DeliveryOfDocumentsDTO();
		 DeliveryOfDocumentsDAO dao = new DeliveryOfDocumentsDAO();
		 mainlist =  dao.getpartyDetl(regno);
		
		 for(int i = 0; i< mainlist.size();i++){
			
			 list1.add(mainlist.get(i));
			 list1.trimToSize();
			 ArrayList listaa = new ArrayList();
			 listaa=(ArrayList) list1.get(i);
			 ///added by simran//////
			 String rolename = "";
			 if(((String)listaa.get(4)).equals("0"))
				 rolename=((String) listaa.get(5));
			 else
			 {
				 if(lang.equals("hi"))
					 rolename=((String) listaa.get(6));
				 else
					 rolename=((String) listaa.get(0));
			 }
			
			 String fname=((String) listaa.get(1));
			 String authname=((String) listaa.get(2));
			 String addrs=((String) listaa.get(3));
			 String distrct=dao.getDistrictDetls((String) listaa.get(7));
			 String state=dao.getStateDetls((String) listaa.get(7));
			 String country=dao.getCountryDetls((String) listaa.get(7));
			 
			 dto2.setNoticePartyRole(rolename);
			 if(authname!=null){
			 dto2.setNoticePartyNm(authname);
			 }else{ 
			 dto2.setNoticePartyNm(fname);
	         }
			 state = state == null?"":state;
			 distrct = distrct == null?"":distrct;
			 country = country == null?"":country;
			 if("".equals(distrct))
				 dto2.setNoticePartyAdrs(addrs);
			 else
				 dto2.setNoticePartyAdrs(addrs+", "+distrct+", "+state+", "+country);
			 list2.add(i, dto2);
			 dto2 = new DeliveryOfDocumentsDTO();
		 }
		 return list2;
	}
	
	
	/******************************************************************  
	  *   Method Name  :   getMainId()
	  *   Arguments    :   primary key
	  *   Return       :   ArrayList
	  *   Throws 	  :   Exception
	 *******************************************************************/  
	public String getMainId(String txnId) throws Exception{
		DeliveryOfDocumentsDAO dao = new DeliveryOfDocumentsDAO();
		String list = dao.getMainId(txnId);
       
		return list;
	}
	

	 /******************************************************************  
	  *   Method Name  :   getrprsntatvDetls()
	  *   Arguments    :   registration completion number
	  *   Return       :   ArrayList
	  *   Throws 	  :   Exception
	 *******************************************************************/  
	public ArrayList getrprsntatvDetls(String regNo) throws Exception{
		DeliveryOfDocumentsDAO dao = new DeliveryOfDocumentsDAO();
		ArrayList list = dao.getrprsntatvDetls(regNo);
        return list;
	}
	
	
	 /******************************************************************  
	  *   Method Name  :   getdestroyDetls()
	  *   Arguments    :   registration completion number
	  *   Return       :   ArrayList
	  *   Throws 	  :   Exception
	 *******************************************************************/  
	public ArrayList getdestroyDetls(String regNo) throws Exception{
		DeliveryOfDocumentsDAO dao = new DeliveryOfDocumentsDAO();
		ArrayList list = dao.getdestroyDetls(regNo);
       return list;
	}
	 /******************************************************************  
	  *   Method Name  :   getissueDetls()
	  *   Arguments    :   registration completion number
	  *   Return       :   ArrayList
	  *   Throws 	  :   Exception
	 *******************************************************************/  
	public ArrayList getissueDetls(String regNo) throws Exception{
		DeliveryOfDocumentsDAO dao = new DeliveryOfDocumentsDAO();
		ArrayList list = dao.getissueDetls(regNo);
      return list;
	}
	 /******************************************************************  
	  *   Method Name  :   getApproveDetls()
	  *   Arguments    :   registration completion number
	  *   Return       :   ArrayList
	  *   Throws 	  :   Exception
	 *******************************************************************/  
	public ArrayList getApproveDetls(String regNo) throws Exception{
		DeliveryOfDocumentsDAO dao = new DeliveryOfDocumentsDAO();
		ArrayList list = dao.getApproveDetls(regNo);
      return list;
	}
	/******************************************************************  
	  *   Method Name  :   getdeliveredDetls()
	  *   Arguments    :   registration completion number
	  *   Return       :   ArrayList
	  *   Throws 	  :   Exception
	 *******************************************************************/  
	public ArrayList getdeliveredDetls(String regNo) throws Exception{
		DeliveryOfDocumentsDAO dao = new DeliveryOfDocumentsDAO();
		ArrayList list = dao.getdeliveredDetls(regNo);
      return list;
	}
	
	/******************************************************************  
	  *   Method Name  :   getdeliveredDetlsPost()
	  *   Arguments    :   registration completion number
	  *   Return       :   ArrayList
	  *   Throws 	  :   Exception
	 *******************************************************************/  
	public ArrayList getdeliveredDetlsPost(String regNo) throws Exception{
		DeliveryOfDocumentsDAO dao = new DeliveryOfDocumentsDAO();
		ArrayList list = dao.getdeliveredDetlsPost(regNo);
     return list;
	}
	
	
	 /******************************************************************  
	  *   Method Name  :   getDrDetl()
	  *   Arguments    :   registration completion number
	  *   Return       :   ArrayList
	  *   Throws 	  :   Exception
	 *******************************************************************/  
	public ArrayList getDrDetl(String regNo) throws Exception{
		DeliveryOfDocumentsDAO dao = new DeliveryOfDocumentsDAO();
		ArrayList list = dao.getDrDetl(regNo);
      return list;
	}
	 /******************************************************************  
	  *   Method Name  :   statusDestroyUpdate()
	  *   Arguments    :   registration completion number, user id, office id
	  *   Return       :   Boolean
	  *   Throws 	  :    Exception
	 *******************************************************************/  
	public  boolean statusDestroyUpdate (String regno, String uid, String offid)throws Exception{
		boolean flg = false;
		DeliveryOfDocumentsDAO dao = new DeliveryOfDocumentsDAO();
		flg = dao.statusDestroyUpdate(regno, uid, offid);
		return flg;
	}
	/******************************************************************  
	  *   Method Name  :   statusApproveUpdate()
	  *   Arguments    :   registration completion number, user id, office id
	  *   Return       :   Boolean
	  *   Throws 	  :    Exception
	 *******************************************************************/  
	public  boolean statusApproveUpdate (String regno, String uid, String offid)throws Exception{
		boolean flg = false;
		DeliveryOfDocumentsDAO dao = new DeliveryOfDocumentsDAO();
		flg = dao.statusApproveUpdate(regno, uid, offid);
		return flg;
	}
	
	/******************************************************************  
	  *   Method Name  :   statusDeliveredUpdate()
	  *   Arguments    :   registration completion number, user id, office id
	  *   Return       :   Boolean
	  *   Throws 	  :    Exception
	 *******************************************************************/  
	public  boolean statusDeliveredUpdate (String regno, String uid, String offid, String chckArry,DeliveryOfDocumentsDTO dto)throws Exception{
		boolean flg = false;
		DeliveryOfDocumentsDAO dao = new DeliveryOfDocumentsDAO();
		flg = dao.statusDeliveredUpdate(regno, uid, offid, chckArry,dto);
		return flg;
	}
	/******************************************************************  
	  *   Method Name  :   statusDeliveredUpdatePost()
	  *   Arguments    :   registration completion number, user id, office id
	  *   Return       :   Boolean
	  *   Throws 	  :    Exception
	 *******************************************************************/  
	public  boolean statusDeliveredUpdatePost (String regno, String uid, String offid, String docketno)throws Exception{
		boolean flg = false;
		DeliveryOfDocumentsDAO dao = new DeliveryOfDocumentsDAO();
		flg = dao.statusDeliveredUpdatePost(regno, uid, offid, docketno);
		return flg;
	}
	/******************************************************************  
	  *   Method Name  :   statusDeliveredUpdateP()
	  *   Arguments    :   registration completion number, user id, office id
	  *   Return       :   Boolean
	  *   Throws 	  :    Exception
	 *******************************************************************/  
	public  boolean statusDeliveredUpdateP (String regno, String uid, String offid, String chckArry)throws Exception{
		boolean flg = false;
		DeliveryOfDocumentsDAO dao = new DeliveryOfDocumentsDAO();
		flg = dao.statusDeliveredUpdateP(regno, uid, offid, chckArry);
		return flg;
	}
	/******************************************************************  
	  *   Method Name  :   statusNoticeUpdate()
	  *   Arguments    :   registration completion number, user id, office id, party details
	  *   Return       :   Boolean
	  *   Throws 	  :    Exception
	 * @param lastdt 
	 * @param remarks 
	 *******************************************************************/  
	public  boolean statusNoticeUpdate (String regno, String uid, String offid, String remarks, String lastdt, ArrayList partydetls)throws Exception{
		boolean flg = false;
		DeliveryOfDocumentsDAO dao = new DeliveryOfDocumentsDAO();
		flg = dao.statusNoticeUpdate(regno, uid, offid,remarks,lastdt, partydetls);
		return flg;
	}
	
	
	/******************************************************************  
	  *   Method Name  :   statusDeliveredUpdatePymt()
	  *   Arguments    :   registration completion number, user id, office id
	  *   Return       :   Boolean
	  *   Throws 	  :    Exception
	 *******************************************************************/  
	public  boolean statusDeliveredUpdatePymt (String regno, String uid, String offid, String chckArry, String fee)throws Exception{
		boolean flg = false;
		DeliveryOfDocumentsDAO dao = new DeliveryOfDocumentsDAO();
		flg = dao.statusDeliveredUpdatePymt(regno, uid, offid, chckArry,fee);
		return flg;
	}
	/******************************************************************  
	  *   Method Name  :   statusDeliveredUpdate1()
	  *   Arguments    :   registration completion number, user id, office id
	  *   Return       :   Boolean
	  *   Throws 	  :    Exception
	 * @param dto 
	 *******************************************************************/  
	public  boolean statusDeliveredUpdate1 (String regno, String uid, String offid, String fname, String mname, String lname, String docName, String docPath)throws Exception{
		boolean flg = false;
		DeliveryOfDocumentsDAO dao = new DeliveryOfDocumentsDAO();
		flg = dao.statusDeliveredUpdate1(regno, uid, offid,fname, mname,lname,docName,docPath);
		return flg;
	}
	/******************************************************************  
	  *   Method Name  :   statusDeliveredUpdateP1()
	  *   Arguments    :   registration completion number, user id, office id
	  *   Return       :   Boolean
	  *   Throws 	  :    Exception
	 * @param dto 
	 *******************************************************************/  
	public  boolean statusDeliveredUpdateP1 (String regno, String uid, String offid, String fname, String mname, String lname, String docName, String docPath)throws Exception{
		boolean flg = false;
		DeliveryOfDocumentsDAO dao = new DeliveryOfDocumentsDAO();
		flg = dao.statusDeliveredUpdateP1(regno, uid, offid,fname, mname,lname,docName,docPath);
		return flg;
	}
	/******************************************************************  
	  *   Method Name  :   getfee()
	  *   Arguments    :   registration completion number
	  *   Return       :   ArrayList
	  *   Throws 	  :   Exception
	 *******************************************************************/  
	public ArrayList getfee(String funid,String serId,String userType) throws Exception{
		DeliveryOfDocumentsDAO dao = new DeliveryOfDocumentsDAO();
		ArrayList list = dao.getfee(funid,serId,userType);
      return list;
	}
	/******************************************************************  
	  *   Method Name  :   getId()
	  *   Arguments    :   -
	  *   Return       :   String
	  *   Throws 	  :   Exception
	 *******************************************************************/  
	public String getId() throws Exception{
		DeliveryOfDocumentsDAO dao = new DeliveryOfDocumentsDAO();
		String id = dao.getId();
     return id;
	}
	
	
	/******************************************************************  
	  *   Method Name  :   getPayDtls()
	  *   Arguments    :   registration completion number
	  *   Return       :   ArrayList
	  *   Throws 	  :   Exception
	 *******************************************************************/  
	
	public ArrayList getPayDtls(String txnId) throws Exception{
		DeliveryOfDocumentsDAO dao = new DeliveryOfDocumentsDAO();
		ArrayList list = dao.getPayDtls(txnId);
       
		return list;
	}
	/******************************************************************  
	  *   Method Name  :   getmodDetls()
	  *   Arguments    :   registration completion number
	  *   Return       :   ArrayList
	  *   Throws 	  :   Exception
	 *******************************************************************/  
	
	public ArrayList getmodDetls(String txnId) throws Exception{
		DeliveryOfDocumentsDAO dao = new DeliveryOfDocumentsDAO();
		ArrayList list = dao.getmodDetls(txnId);
      
		return list;
	}
	
	/******************************************************************  
	 *   Method Name  :   insertPay()
	 *   Arguments    :   reg no, fee, uid 
	 *   Return       :   Boolean
	 *   Throws 	  :   NullPointer  or SQLException or Exception
	*******************************************************************/  
	public  boolean insertPay (String regno, String fee, String uid,String id)throws NullPointerException,
	SQLException,Exception{
		boolean flg = false;
		DeliveryOfDocumentsDAO dao = new DeliveryOfDocumentsDAO();
		
			flg = dao.insertPay(regno, fee,uid,id);
		
		   return flg;
	}
	/******************************************************************  
	  *   Method Name  :   getrequestDetails()
	  *   Arguments    :   office id
	  *   Return       :   ArrayList
	  *   Throws 	  :   Exception
	 *******************************************************************/  
	
	public ArrayList getrequestDetails(String txnId) throws Exception{
		DeliveryOfDocumentsDAO dao = new DeliveryOfDocumentsDAO();
		ArrayList list = dao.getrequestDetails(txnId);
      
		return list;
	}
	
	public String getOffcTypeDetl(String offId) throws Exception{
		DeliveryOfDocumentsDAO dao = new DeliveryOfDocumentsDAO();
		return dao.getOffcTypeDetl(offId);
	}

	public ArrayList getChildOffcDetl(String offId) throws Exception{
		DeliveryOfDocumentsDAO dao = new DeliveryOfDocumentsDAO();
		return dao.getChildOffcDetl(offId);
	}
	public ArrayList getPrintStatusDetail(String regno) throws Exception{
		DeliveryOfDocumentsDAO dao = new DeliveryOfDocumentsDAO();
		return dao.getPrintStatusDetail(regno);
	}
	public  boolean statusPrintedUpdate (String regno, String uid, String offid)throws Exception{
		boolean flg = false;
		DeliveryOfDocumentsDAO dao = new DeliveryOfDocumentsDAO();
		flg = dao.statusPrintedUpdate(regno, uid, offid);
		return flg;
	}
	public String getRegTxnID(String eRegNo) throws Exception{
		DeliveryOfDocumentsDAO dao = new DeliveryOfDocumentsDAO();
		return dao.getRegTxnID(eRegNo);
	}
	
	public  boolean statusUndeliveredUpdate (String regno, String uid, String offid)throws Exception{
		boolean flg = false;
		DeliveryOfDocumentsDAO dao = new DeliveryOfDocumentsDAO();
		flg = dao.statusUndeliveredUpdate(regno, uid, offid);
		return flg;
	}
	public  boolean signatureTimeUpdate (String regno)throws Exception{
		boolean flg = false;
		DeliveryOfDocumentsDAO dao = new DeliveryOfDocumentsDAO();
		flg = dao.signatureTimeUpdate(regno);
		return flg;
	}
	public  boolean updatePinLinking (String regno, String deedID, DeliveryOfDocumentsDTO dto,String userId )throws Exception{
		boolean flg = false;
		DeliveryOfDocumentsDAO dao = new DeliveryOfDocumentsDAO();
		flg = dao.updatePinLinking(regno, deedID, dto,userId);
		return flg;
	}
	public ArrayList viewClaimantDelivery(String regno, String deedID, DeliveryOfDocumentsDTO dto,String userId ) throws Exception{
		DeliveryOfDocumentsDAO dao = new DeliveryOfDocumentsDAO();
		ArrayList finalList=new ArrayList();
		ArrayList list= dao.viewClaimantDelivery(regno, deedID, dto,userId);
		if(list.size()>0){
			for(int j=0;j<list.size();j++){
				ArrayList sList=(ArrayList)list.get(j);
				//ArrayList sList=(ArrayList)sublist.get(0);
		
		for(int i=0;i<sList.size();i++){
			DeliveryOfDocumentsDTO dto1=new DeliveryOfDocumentsDTO();
			ArrayList subList=(ArrayList)sList.get(i);
			dto1.setPropertyID(subList.get(0).toString());
			if(subList.get(1)!=null)
				dto1.setClaimantName(subList.get(1).toString());
			else
				dto1.setClaimantName("");	
			if(subList.get(2)!=null)
				dto1.setClaimantNumber(subList.get(2).toString());
			else
				dto1.setClaimantNumber("");	
			finalList.add(dto1);
		}
		}
		}
		return finalList;
	}
	
	public  boolean insertPinToSmsHand(String regno, String deedID, DeliveryOfDocumentsDTO dto,String userId ,String [] arr)throws Exception{
		boolean flg = false;
		DeliveryOfDocumentsDAO dao = new DeliveryOfDocumentsDAO();
		flg = dao.insertPinToSmsHand(regno, deedID, dto,userId,arr);
		return flg;
	}
	
	
	public ArrayList getSignStatus() {
		DeliveryOfDocumentsDAO dao = new DeliveryOfDocumentsDAO();
		ArrayList list=new ArrayList();
		try{
			list = dao.getSignStatus();
		
		}catch(Exception e){
		}
		return list;
	}
	public String getSignFilePath(String regComp) throws Exception{
		DeliveryOfDocumentsDAO dao = new DeliveryOfDocumentsDAO();
		String list = dao.getSignFilePath(regComp);
       
		return list;
	}
	public String getFee(String funId) throws Exception{
		DeliveryOfDocumentsDAO dao = new DeliveryOfDocumentsDAO();
		String list = dao.getFee(funId);
       
		return list;
	}
	
	//Start : Changes added by Neeti for RCMS
	
	public String checkDeedTypeForRCMS(String regTxnID) throws Exception {
		DeliveryOfDocumentsDAO dao = new DeliveryOfDocumentsDAO();
		String list = dao.checkDeedTypeForRCMS(regTxnID);
		return list;
	}
	
	public ArrayList checkPropertyForAgriLand(String regTxnID) throws Exception {
		DeliveryOfDocumentsDAO dao = new DeliveryOfDocumentsDAO();
		ArrayList list = dao.checkPropertyForAgriLand(regTxnID);
		return list;
	}
	
	public String getRCMSFlag(String regTxnID) throws Exception {
		DeliveryOfDocumentsDAO dao = new DeliveryOfDocumentsDAO();
		String rcmsFlag = dao.getRCMSFlag(regTxnID);
		return rcmsFlag;
	}
	public String getTehsilID(String regNum) throws Exception {
		DeliveryOfDocumentsDAO dao = new DeliveryOfDocumentsDAO();
		String tehsilID = dao.getTehsilID(regNum);
		return tehsilID;
	}
	
	//End : Changes added by Neeti for RCMS
}
