package com.wipro.igrs.propertylock.bd;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.propertylock.dao.PropertyLockDAO;
import com.wipro.igrs.propertylock.dto.PropertyLockDTO;

public class PropertyLockBD {
	private Logger logger =(Logger)Logger.getLogger(PropertyLockBD.class);
	
	/**
	 *  @param  -
	 *  @exception Exception
	 *  @return ArrayList
	 */
	public ArrayList getCountry(String language) throws Exception{
		ArrayList mainList = new ArrayList();
		ArrayList list = new ArrayList();
        ArrayList subList = null;
		PropertyLockDAO dao = new PropertyLockDAO();
		try{
		list = dao.getCountryJud(language);
        
        PropertyLockDTO dto = new PropertyLockDTO();
        for (int i = 0; i < list.size(); i++) {
            subList = (ArrayList)list.get(i);
            dto = new PropertyLockDTO();
            dto.setValue(subList.get(0).toString());
            dto.setName(subList.get(1).toString());
            mainList.add(dto);
        	}
		}catch(Exception e){
			
		}
		return mainList;
	}
	
	
	
	/**
	 *  @param  -
	 *  @exception Exception
	 *  @return ArrayList
	 */
	public ArrayList getPhoto(String language) throws Exception{
		ArrayList list =  new IGRSCommon().getPhotoIdProof(language);
        ArrayList mainList = new ArrayList();
        ArrayList subList = null;
        PropertyLockDTO dto = new PropertyLockDTO();
        for (int i = 0; i < list.size(); i++) {
            subList = (ArrayList)list.get(i);
            dto = new PropertyLockDTO();
            dto.setValue(subList.get(0).toString());
            dto.setName(subList.get(1).toString());
            mainList.add(dto);
        	}
		return mainList;
	}
	
	/**
	 *  @param  -
	 *  @exception Exception
	 *  @return ArrayList
	 */
	public ArrayList getAppellate(String language) throws Exception{
		 ArrayList mainList = new ArrayList();
		 try{
			 ArrayList list =  new IGRSCommon().getAppellate(language);
		     ArrayList subList = null;
             PropertyLockDTO dto = new PropertyLockDTO();
            for (int i = 0; i < list.size(); i++) {
            subList = (ArrayList)list.get(i);
            dto = new PropertyLockDTO();
            dto.setValue(subList.get(0).toString());
            dto.setName(subList.get(1).toString());
            mainList.add(dto);
        	}
		 }catch(Exception e){}
		return mainList;
	}
	
	
	/**
	 *  @param  -
	 *  @exception Exception
	 *  @return ArrayList
	 */
	public ArrayList getState(String cntryID,String language) throws Exception{
		 ArrayList mainList = new ArrayList();
		try{
			PropertyLockDAO dao = new PropertyLockDAO();
		ArrayList list = dao.getStateJud(cntryID,language);
      
        ArrayList subList = null;
        PropertyLockDTO dto = new PropertyLockDTO();
        for (int i = 0; i < list.size(); i++) {
            subList = (ArrayList)list.get(i);
            dto = new PropertyLockDTO();
            dto.setValue(subList.get(0).toString());
            dto.setName(subList.get(1).toString());
            mainList.add(dto);
        	}
		}catch(Exception e){}
		return mainList;
	}
	
	/**
	 *  @param  -
	 *  @exception Exception
	 *  @return ArrayList
	 */
	public ArrayList getDistrict(String stateID,String language) throws Exception{
		  ArrayList mainList = new ArrayList();
		 try{
			 ArrayList list =  new IGRSCommon().getDistrict(stateID,language);
		
          ArrayList subList = null;
          PropertyLockDTO dto = new PropertyLockDTO();
           for (int i = 0; i < list.size(); i++) {
            subList = (ArrayList)list.get(i);
            dto = new PropertyLockDTO();
            dto.setValue(subList.get(0).toString());
            dto.setName(subList.get(1).toString());
            mainList.add(dto);
        	}
		 }catch(Exception e){}
		return mainList;
	}
	
	/******************************************************************  
	  *   Method Name  :   getfee()
	  *   Arguments    :   function id, service id, userid
	  *   Return       :   String
	  *   Throws 	  :   Exception
	 *******************************************************************/  
	public String getfee(String funid,String serId,String userType) throws Exception{
		String desDtl="";
		//ArrayList li = new ArrayList();
		try{
			PropertyLockDAO dao = new PropertyLockDAO();
			desDtl =dao.getfee(funid,serId,userType);
			/*if(li!=null && li.size()>0){
				desDtl= ((String)li.get(2));
				//modified by shruti----15 may 2014--new service fee mapping table changes
   		}*/
		
		}catch(Exception e){
		}
		return desDtl;
	}
	//added by shruti---15 may 2014
	public String chkUser(String userId) throws Exception{
		ArrayList resultList =new ArrayList();
		
		
		String typeid="";
		try 
		{
		PropertyLockDAO dao = new PropertyLockDAO();
		typeid=dao.chkUser(userId);
		
		}catch (Exception e) {
			logger.error(e);	

		}
		 
		 return typeid;
	}
	public ArrayList getExternalUserDtls(String userId) throws Exception{
		ArrayList resultList =new ArrayList();
		
		try {
			PropertyLockDAO dao = new PropertyLockDAO();
		resultList=dao.getExternalUserDtls(userId);
		
		}catch (Exception e) {
			logger.error(e);	

		}
		 
		 return resultList;
	}
	public ArrayList getRUUserDtls(String userId) throws Exception{
		ArrayList resultList =new ArrayList();
		
		try {
			PropertyLockDAO dao = new PropertyLockDAO();
		resultList=dao.getRUUserDtls(userId);
	
		
		}catch (Exception e) {
			logger.error(e);	

		}
		 
		 return resultList;
	}
	
	public ArrayList getInternalUserDtls(String officeId) throws Exception{
		ArrayList resultList =new ArrayList();
		
		try {
			PropertyLockDAO dao = new PropertyLockDAO();
		resultList=dao.getInternalUserDtls(officeId);
		
		}catch (Exception e) {
			logger.error(e);	

		}
		 
		 return resultList;
	}
	
	 /******************************************************************  
	  *   Method Name  :   getTransDetl()
	  *   Arguments    :   registration_completion_Number
	  *   Return       :   ArrayList
	  *   Throws 	  :   Exception
	 *******************************************************************/  
	public ArrayList getTransDetl(String regno,String language) throws Exception{
		 ArrayList mainlist = new ArrayList();
		 ArrayList list1 = new ArrayList();
		 ArrayList list2 = new ArrayList();
		 PropertyLockDTO dto = new PropertyLockDTO();
		 PropertyLockDAO dao = new PropertyLockDAO();
		 
		 mainlist =  dao.getTransDetl(regno,language);
		
		 for(int i = 0; i< mainlist.size();i++){
			 list1.add(mainlist.get(i));
			 list1.trimToSize();
			 ArrayList listaa = new ArrayList();
			 listaa=(ArrayList) list1.get(i);
			 String propId=((String) listaa.get(0));
			 if(propId.length()==15){
				 propId="0"+propId; 
			 }
			 logger.debug(propId);
			 String propst=((String) listaa.get(1));
			 logger.debug(propst);
			 String propDeed=((String) listaa.get(2));
			 logger.debug(propDeed);
			 String regcomno=((String) listaa.get(3));
			 logger.debug(propDeed);
			 String regcomdt=((String) listaa.get(4));
			 logger.debug(propDeed);
			 dto.setPropId(propId);
			 dto.setPropStatus(propst);
			 dto.setPropDeed(propDeed);
			 dto.setRegCompNumber(regcomno);
			 dto.setRegCompDate(regcomdt);
			 dto.setPropComb((String) listaa.get(0)+"~"+(String) listaa.get(3)+"~"+(String) listaa.get(4));
			 list2.add(i, dto);
			 dto = new PropertyLockDTO();
		 }
		 return list2;
	
	}
	 /******************************************************************  
	  *   Method Name  :   getTransDetlR()
	  *   Arguments    :   property lock id, duration from, duration to
	  *   Return       :   ArrayList
	  *   Throws 	  :   Exception
	 *******************************************************************/  
	public ArrayList getTransDetlR (String regno, String df, String dt,String language) throws Exception{
		 ArrayList mainlist = new ArrayList();
		 ArrayList list1 = new ArrayList();
		 ArrayList list2 = new ArrayList();
		 PropertyLockDTO dto = new PropertyLockDTO();
		 PropertyLockDAO dao = new PropertyLockDAO();
		 
		 mainlist =  dao.getTransDetl(regno,language);
		
		 for(int i = 0; i< mainlist.size();i++){
			 list1.add(mainlist.get(i));
			 list1.trimToSize();
			 ArrayList listaa = new ArrayList();
			 listaa=(ArrayList) list1.get(i);
			 String propId=((String) listaa.get(0));
			 if(propId.length()==15){
				 propId="0"+propId; 
			 }
			 logger.debug(propId);
			 String propst=((String) listaa.get(1));
			 logger.debug(propst);
			 String propDeed=((String) listaa.get(2));
			 logger.debug(propDeed);
			 String regcomno=((String) listaa.get(3));
			 logger.debug(propDeed);
			 String regcomdt=((String) listaa.get(4));
			 logger.debug(propDeed);
			 dto.setPropId(propId);
			 dto.setPropStatus(propst);
			 dto.setPropDeed(propDeed);
			 dto.setRegCompNumber(regcomno);
			 dto.setRegCompDate(regcomdt);
			 dto.setPropComb((String) listaa.get(0)+"~"+(String) listaa.get(3)+"~"+(String) listaa.get(4));
			 list2.add(i, dto);
			 dto = new PropertyLockDTO();
		 }
		 return list2;
	
	}
	 /******************************************************************  
	  *   Method Name  :   getTransDetlR1()
	  *   Arguments    :   property lock id, duration from, duration to
	  *   Return       :   ArrayList
	  *   Throws 	  :    Exception
	 *******************************************************************/  
	public ArrayList getTransDetlR1 (String regno, String df, String dt,String language) throws Exception{
		 ArrayList mainlist = new ArrayList();
		 ArrayList list1 = new ArrayList();
		 ArrayList list2 = new ArrayList();
		 PropertyLockDTO dto = new PropertyLockDTO();
		 PropertyLockDAO dao = new PropertyLockDAO();
		 
		 mainlist =  dao.getTransDetlR1(regno, df, dt,language);
		
		 for(int i = 0; i< mainlist.size();i++){
			 list1.add(mainlist.get(i));
			 list1.trimToSize();
			 ArrayList listaa = new ArrayList();
			 listaa=(ArrayList) list1.get(i);
			 String propId=((String) listaa.get(0));
			 if(propId.length()==15){
				 propId="0"+propId; 
			 }
			 logger.debug(propId);
			 String propst=((String) listaa.get(1));
			 logger.debug(propst);
			 String lockdt=((String) listaa.get(2));
			 logger.debug(lockdt);
			 String regcomno=((String) listaa.get(3));
			 logger.debug(regcomno);
			 String regcomdt=((String) listaa.get(4));
			 logger.debug(regcomdt);
			 dto.setPropId(propId);
			 dto.setPropStatus(propst);
			 dto.setDateLocking(lockdt);
			 dto.setRegistrationId(regcomno);
			 dto.setRegistrationDate(regcomdt);
			 dto.setPropertyLockid((String) listaa.get(5));
			 dto.setLockComb((String) listaa.get(0)+"~"+(String) listaa.get(3)+"~"+(String) listaa.get(4)+"~"+(String) listaa.get(5));
			 list2.add(i, dto);
			 dto = new PropertyLockDTO();
		 }
		 return list2;
	
	}
	
	/******************************************************************  
	  *   Method Name  :   getViewDetls()
	  *   Arguments    :   property lock id, duration from, duration to
	  *   Return       :   ArrayList
	  *   Throws 	  :    Exception
	 *******************************************************************/  
	public ArrayList getViewDetls (String refId,String regno, String df, String dt,String appSt,String language) throws Exception{
		 ArrayList mainlist = new ArrayList();
		 PropertyLockDAO dao = new PropertyLockDAO();
		 mainlist =  dao.getViewDetls(refId,regno,df,dt,appSt,language);
		 return mainlist;
	}
	
	 /******************************************************************  
	  *   Method Name  :   getLockStatus()
	  *   Arguments    :   registration_completion_Number, prop id
	  *   Return       :   ArrayList
	  *   Throws 	  :   Exception
	 *******************************************************************/  
	    
	    public ArrayList getLockStatus(String regId, String propId)  throws Exception 
		{
			{
				PropertyLockDAO dao = new PropertyLockDAO(); 
				ArrayList list = new ArrayList();
				try {
					String[] par1 = new String[2];
			        par1[0] = regId;
			        par1[1] = propId;
			        //ArrayList ret2 = dao.getLockStatus(par1);
			        list = dao.getLockStatus(par1);
					
				} catch (Exception e)
				{
					logger.debug("exception in BD in getLockStatus ()");
				}
				return list;
			}
		}
	
	
	
	//-- insertLockDetls
	    /******************************************************************  
		  *   Method Name  :   insertLockDetls()
		  *   Arguments    :   dto, user id, office id
		  *   Return       :   String
		  *   Throws 	  :    Exception
		 *******************************************************************/  
		public  String insertLockDetls (PropertyLockDTO dto, String uid, String offid)throws Exception,
		 SQLException,Exception{
			 String lckId="";
			 PropertyLockDAO dao = new PropertyLockDAO(); 
			 	 	
		 	 try{
		 		lckId = dao.insertLockDetls(dto, uid, offid);
		 	  }catch(Exception e){
				}
		 	 return lckId;
		 }
		/******************************************************************  
		  *   Method Name  :   insertReleaseDetls()
		  *   Arguments    :   dto, user id, office id
		  *   Return       :   String
		  *   Throws 	  :    Exception
		 *******************************************************************/  
		public  String insertReleaseDetls (PropertyLockDTO dto, String uid, String offid)throws Exception,
		 SQLException,Exception{
			 String lckId="";
			 PropertyLockDAO dao = new PropertyLockDAO(); 
			 	 	
		 	 try{
		 		lckId = dao.insertReleaseDetls(dto, uid, offid);
		 	  }catch(Exception e){
				}
		 	 return lckId;
		 }
		/******************************************************************  
		  *   Method Name  :   insertReleaseDetlsP()
		  *   Arguments    :   dto, user id, office id
		  *   Return       :   String
		  *   Throws 	  :    Exception
		 *******************************************************************/  
		public  String insertReleaseDetlsP (PropertyLockDTO dto, String uid, String offid)throws Exception,
		 SQLException,Exception{
			 String lckId="";
			 PropertyLockDAO dao = new PropertyLockDAO(); 
			 	 	
		 	 try{
		 		lckId = dao.insertReleaseDetlsP(dto, uid, offid);
		 	  }
		 	 catch(Exception e){
		 		 e.printStackTrace();
				}
		 	 return lckId;
		 }
		/******************************************************************  
		  *   Method Name  :   insertReleaseDetlsR()
		  *   Arguments    :   dto, user id, office id
		  *   Return       :   String
		  *   Throws 	  :    Exception
		 *******************************************************************/  
		public  String insertReleaseDetlsR (PropertyLockDTO dto, String uid, String offid)throws Exception,
		 SQLException,Exception{
			 String lckId="";
			 PropertyLockDAO dao = new PropertyLockDAO(); 
			 	 	
		 	 try{
		 		lckId = dao.insertReleaseDetlsR(dto, uid, offid);
		 	  }catch(Exception e){
				}
		 	 return lckId;
		 }
		/******************************************************************  
		  *   Method Name  :   insertReleaseDetlsRP()
		  *   Arguments    :   dto, user id, office id
		  *   Return       :   String
		  *   Throws 	  :    Exception
		 *******************************************************************/  
		public  String insertReleaseDetlsRP (PropertyLockDTO dto, String uid, String offid)throws Exception,
		 SQLException,Exception{
			 String lckId="";
			 PropertyLockDAO dao = new PropertyLockDAO(); 
			 	 	
		 	 try{
		 		lckId = dao.insertReleaseDetlsRP(dto, uid, offid);
		 	  }catch(Exception e){
				}
		 	 return lckId;
		 }
		
		/******************************************************************  
		  *   Method Name  :   insertLockDetlsP()
		  *   Arguments    :   dto, user id, office id
		  *   Return       :   String
		  *   Throws 	  :    Exception
		 *******************************************************************/  
		public  String insertLockDetlsP (PropertyLockDTO dto, String uid, String offid)throws Exception,
		 SQLException,Exception{
			 String lckId="";
			 PropertyLockDAO dao = new PropertyLockDAO(); 
			 	 	
		 	 try{
		 		lckId = dao.insertLockDetlsP(dto, uid, offid);
		 	  }catch(Exception e){
				}
		 	 return lckId;
		 }
		 /******************************************************************  
		  *   Method Name  :   getReginitId()
		  *   Arguments    :   registration completion number
		  *   Return       :   String
		  *   Throws 	  :    Exception
		 *******************************************************************/  
		public  String getReginitId (String regComNo)throws Exception,
		 SQLException,Exception{
			 String reginitId="";
			 PropertyLockDAO dao = new PropertyLockDAO(); 
			 	 	
		 	 try{
		 		reginitId = dao.getReginitId(regComNo);
		 	  }catch(Exception e){
				}
		 	 return reginitId;
		 }
		
		//ADDED BY SHRUTI----14 MAY 2014
		public  String getInstrumentFlag (String regComNo)throws Exception,
		 SQLException,Exception{
			 String insFlag="";
			 PropertyLockDAO dao = new PropertyLockDAO(); 
			 	 	
		 	 try{
		 		insFlag = dao.getInstrumentFlag(regComNo);
		 	  }catch(Exception e){
				}
		 	 return insFlag;
		 }
		//END
		
		
		 /******************************************************************  
		  *   Method Name  :   getTxnDetls()
		  *   Arguments    :   registration completion number, property id
		  *   Return       :   ArrayList
		  *   Throws 	  :   Exception
		 *******************************************************************/  
		public ArrayList getTxnDetls(String regNo, String propid, String refId,String language) throws Exception{
			PropertyLockDAO dao = new PropertyLockDAO();
			ArrayList list = dao.getTxnDetls(regNo,propid, refId,language);
	        return list;
		}
		 /******************************************************************  
		  *   Method Name  :   getTxnDetls()
		  *   Arguments    :   registration completion number, property id
		  *   Return       :   ArrayList
		  *   Throws 	  :   Exception
		 *******************************************************************/  
		public ArrayList getTxnDetlsV(String regNo, String propid, String refId,String language) throws Exception{
			PropertyLockDAO dao = new PropertyLockDAO();
			ArrayList list = dao.getTxnDetlsV(regNo,propid, refId,language);
	        return list;
		}
		 /******************************************************************  
		  *   Method Name  :   getTxnDetlsR()
		  *   Arguments    :   registration completion number, property id
		  *   Return       :   ArrayList
		  *   Throws 	  :   Exception
		 *******************************************************************/  
		public ArrayList getTxnDetlsR(String regNo, String propid, String refId,String language) throws Exception{
			PropertyLockDAO dao = new PropertyLockDAO();
			ArrayList list = dao.getTxnDetlsR(regNo,propid, refId,language);
	        return list;
		}
		/******************************************************************  
		  *   Method Name  :   getTxnDetlsRV()
		  *   Arguments    :   registration completion number, property id
		  *   Return       :   ArrayList
		  *   Throws 	  :   Exception
		 *******************************************************************/  
		public ArrayList getTxnDetlsRV(String regNo, String propid, String refId) throws Exception{
			PropertyLockDAO dao = new PropertyLockDAO();
			ArrayList list = dao.getTxnDetlsRV(regNo,propid, refId);
	        return list;
		}
		 /******************************************************************  
		  *   Method Name  :   getTxnDetlsRP()
		  *   Arguments    :   registration completion number, property id
		  *   Return       :   ArrayList
		  *   Throws 	  :   Exception
		 *******************************************************************/  
		public ArrayList getTxnDetlsRP(String regNo, String propid, String refId,String language) throws Exception{
			PropertyLockDAO dao = new PropertyLockDAO();
			ArrayList list = dao.getTxnDetlsRP(regNo,propid, refId,language);
	        return list;
		}
		 /******************************************************************  
		  *   Method Name  :   getTxnDetlsAP()
		  *   Arguments    :   registration completion number, property id
		  *   Return       :   ArrayList
		  *   Throws 	  :   Exception
		 *******************************************************************/  
		public ArrayList getTxnDetlsAP(String regNo, String propid, String refId,String language) throws Exception{
			PropertyLockDAO dao = new PropertyLockDAO();
			ArrayList list = dao.getTxnDetlsAP(regNo,propid, refId,language);
	        return list;
		}
		 
		 /******************************************************************  
		  *   Method Name  :   getTxnDetlsP()
		  *   Arguments    :   registration completion number, property id
		  *   Return       :   ArrayList
		  *   Throws 	  :   Exception
		 *******************************************************************/  
		public ArrayList getTxnDetlsP(String regNo, String propid, String refId,String language) throws Exception{
			PropertyLockDAO dao = new PropertyLockDAO();
			ArrayList list = dao.getTxnDetlsP(regNo,propid, refId,language);
	        return list;
		}
		
		/******************************************************************  
		  *   Method Name  :   getPartyDetls()
		  *   Arguments    :   registration completion number, property id
		  *   Return       :   ArrayList
		  *   Throws 	  :   Exception
		 *******************************************************************/  
		public ArrayList getPartyDetls(String regNo, String refId,String language) throws Exception{
			PropertyLockDAO dao = new PropertyLockDAO();
			ArrayList list = dao.getPartyDetls(regNo,refId,language);
	        return list;
		}
		/******************************************************************  
		  *   Method Name  :   getRelativeDetls()
		  *   Arguments    :   registration completion number, property id
		  *   Return       :   ArrayList
		  *   Throws 	  :   Exception
		 *******************************************************************/  
		public ArrayList getRelativeDetls(String regNo, String refId,String language) throws Exception{
			PropertyLockDAO dao = new PropertyLockDAO();
			ArrayList list = dao.getRelativeDetls(regNo,refId,language);
	        return list;
		}
		/******************************************************************  
		  *   Method Name  :   getPhotoDetl()
		  *   Arguments    :   property id
		  *   Return       :   ArrayList
		  *   Throws 	  :   Exception
		 *******************************************************************/  
		public ArrayList getPhotoDetl(String refId,String language) throws Exception{
			PropertyLockDAO dao = new PropertyLockDAO();
			ArrayList list = dao.getPhotoDetl(refId,language);
	        return list;
		}
		/******************************************************************  
		  *   Method Name  :   getPayDtls()
		  *   Arguments    :   registration completion number
		  *   Return       :   ArrayList
		  *   Throws 	  :   Exception
		 *******************************************************************/  
		
		public ArrayList getPayDtls(String txnId) throws Exception{
			PropertyLockDAO dao = new PropertyLockDAO();
			ArrayList list = dao.getPayDtls(txnId);
	       
			return list;
		}
		/******************************************************************  
		  *   Method Name  :   getrequestDetails()
		  *   Arguments    :   office id
		  *   Return       :   ArrayList
		  *   Throws 	  :   Exception
		 *******************************************************************/  
		
		public ArrayList getrequestDetails(String txnId) throws Exception{
			PropertyLockDAO dao = new PropertyLockDAO();
			ArrayList list = dao.getrequestDetails(txnId);
	       
			return list;
		}
		/******************************************************************  
		  *   Method Name  :   getPayDtlsV()
		  *   Arguments    :   registration completion number
		  *   Return       :   ArrayList
		  *   Throws 	  :   Exception
		 *******************************************************************/  
		
		public ArrayList getPayDtlsV(String txnId) throws Exception{
			PropertyLockDAO dao = new PropertyLockDAO();
			ArrayList list = dao.getPayDtlsV(txnId);
	       
			return list;
		}
		/******************************************************************  
		  *   Method Name  :   getId()
		  *   Arguments    :   -
		  *   Return       :   String
		  *   Throws 	  :   Exception
		 *******************************************************************/  
		public String getId() throws Exception{
			PropertyLockDAO dao = new PropertyLockDAO();
			String id = dao.getId();
	     return id;
		}
		/******************************************************************  
		 *   Method Name  :   insertPay()
		 *   Arguments    :   reg no, fee, uid 
		 *   Return       :   Boolean
		 *   Throws 	  :   NullPointer  or SQLException or Exception
		*******************************************************************/  
		public  boolean insertPay (String lockId, String fee, String uid,String id, String funId)throws NullPointerException,
		SQLException,Exception{
			boolean flg = false;
			PropertyLockDAO dao = new PropertyLockDAO();
			
				flg = dao.insertPay(lockId, fee,uid,id,funId );
			
			   return flg;
		}
		
		/******************************************************************  
		  *   Method Name  :   getMainId()
		  *   Arguments    :   primary key
		  *   Return       :   ArrayList
		  *   Throws 	  :   Exception
		 *******************************************************************/  
		public String getMainId(String txnId) throws Exception{
			PropertyLockDAO dao = new PropertyLockDAO();
			String list = dao.getMainId(txnId);
	       
			return list;
		}
		/******************************************************************  
		 *   Method Name  :   statusUpdateAfterP()
		 *   Arguments    :   lock id,uid,office id
		 *   Return       :   Boolean
		 *   Throws 	  :   NullPointer  or SQLException or Exception
		*******************************************************************/  
		public  boolean statusUpdateAfterP (String lockId,String uid,String ofid)throws NullPointerException,
		SQLException,Exception{
			boolean flg = false;
			PropertyLockDAO dao = new PropertyLockDAO();
			
				flg = dao.statusUpdateAfterP(lockId,uid,ofid);
			
			   return flg;
		}
		/******************************************************************  
		 *   Method Name  :   statusUpdateAfterPR()
		 *   Arguments    :   lock id,uid,office id
		 *   Return       :   Boolean
		 *   Throws 	  :   NullPointer  or SQLException or Exception
		*******************************************************************/  
		public  boolean statusUpdateAfterPR (String lockId,String uid,String ofid)throws NullPointerException,
		SQLException,Exception{
			boolean flg = false;
			PropertyLockDAO dao = new PropertyLockDAO();
			
				flg = dao.statusUpdateAfterPR(lockId,uid,ofid);
			
			   return flg;
		}
		
		/******************************************************************  
		  *   Method Name  :   getPendingDetls()
		  *   Arguments    :   registration_completion_Number, status of doc, from date, to date, district id
		  *   Return       :   ArrayList
		  *   Throws 	  :   Exception
		 *******************************************************************/  
		public ArrayList getPendingDetls(String uid,String language) throws Exception{
			 ArrayList mainlist = new ArrayList();
			 ArrayList list1 = new ArrayList();
			 ArrayList list2 = new ArrayList();
			 PropertyLockDTO dto = new PropertyLockDTO();
			 PropertyLockDAO dao = new PropertyLockDAO();
			 mainlist =  dao.getPendingDetls(uid,language);
			
			 for(int i = 0; i< mainlist.size();i++){
				 list1.add(mainlist.get(i));
				 list1.trimToSize();
				 ArrayList listaa = new ArrayList();
				 listaa=(ArrayList) list1.get(i);
				 dto.setPropertyLockid((String) listaa.get(0));
				 logger.debug(dto.getPropertyLockid());
				 String propID=(String) listaa.get(1);
				 if (propID.length()==15){
					 propID="0"+propID;
				 }
				 dto.setPropId(propID);
				 logger.debug(dto.getPropId());
				 dto.setRegistrationId((String) listaa.get(2));
				 logger.debug(dto.getRegistrationId());
				 dto.setRegistrationDate((String) listaa.get(3));
				 logger.debug(dto.getRegistrationDate());
				 dto.setLockStatus((String) listaa.get(4));
				 dto.setPendingCumDetl((String) listaa.get(0)+"~"+(String) listaa.get(1)+"~"+(String) listaa.get(2)+"~"+
						             (String) listaa.get(3)+"~"+(String) listaa.get(4));
				 list2.add(i, dto);
				 dto = new PropertyLockDTO();
			 }
			 return list2;
		}
		
		
		/******************************************************************  
		  *   Method Name  :   getPendingDetlsR()
		  *   Arguments    :   user id
		  *   Return       :   ArrayList
		  *   Throws 	  :   Exception
		 *******************************************************************/  
		public ArrayList getPendingDetlsR(String uid,String language) throws Exception{
			 ArrayList mainlist = new ArrayList();
			 ArrayList list1 = new ArrayList();
			 ArrayList list2 = new ArrayList();
			 PropertyLockDTO dto = new PropertyLockDTO();
			 PropertyLockDAO dao = new PropertyLockDAO();
			 mainlist =  dao.getPendingDetlsR(uid,language);
			
			 for(int i = 0; i< mainlist.size();i++){
				 list1.add(mainlist.get(i));
				 list1.trimToSize();
				 ArrayList listaa = new ArrayList();
				 listaa=(ArrayList) list1.get(i);
				 dto.setPropertyReleaseId((String) listaa.get(0));
				 logger.debug(dto.getPropertyLockid());
				 String propID=(String) listaa.get(1);
				 if (propID.length()==15){
					 propID="0"+propID;
				 }
				 dto.setPropId(propID);
				 logger.debug(dto.getPropId());
				 dto.setRegistrationId((String) listaa.get(2));
				 logger.debug(dto.getRegistrationId());
				 dto.setRegistrationDate((String) listaa.get(3));
				 logger.debug(dto.getRegistrationDate());
				 dto.setLockStatus((String) listaa.get(4));
				 dto.setPendingCumDetlR((String) listaa.get(0)+"~"+(String) listaa.get(1)+"~"+(String) listaa.get(2)+"~"+
						             (String) listaa.get(3)+"~"+(String) listaa.get(4));
				 list2.add(i, dto);
				 dto = new PropertyLockDTO();
			 }
			 return list2;
		}
		
		
		    /**
			 *  @param  -
			 *  @exception Exception
			 *  @return ArrayList
			 */
			public ArrayList getRelationType() throws Exception{
				ArrayList mainList = new ArrayList();
				ArrayList list = new ArrayList();
		        ArrayList subList = null;
				PropertyLockDAO dao = new PropertyLockDAO();
				try{
				list = dao.getRelationType();
		        
		        PropertyLockDTO dto = new PropertyLockDTO();
		        for (int i = 0; i < list.size(); i++) {
		            subList = (ArrayList)list.get(i);
		            dto = new PropertyLockDTO();
		            dto.setValue(subList.get(0).toString());
		            dto.setName(subList.get(1).toString());
		            mainList.add(dto);
		        	}
				}catch(Exception e){
					
				}
				return mainList;
			}
		

}
