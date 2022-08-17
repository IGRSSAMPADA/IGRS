/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   DeliveryOfDocumentsBD.java
 * Author      :   Aakriti Kaplish
 * Description :   Represents the Master BD Class for DOD
 * ----------------------------------------------------------------------------
 * Version         Last Modified By        Last Modified On           Modification
 * ----------------------------------------------------------------------------
 * 	0.0             Aakriti Kaplish        21th June, 2013 		 
 *  
 * --------------------------------------------------------------------------------
 */

package com.wipro.igrs.DeliveryOfDocuments.bd;


import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.newgen.burning.BurningImageAndText;
import com.newgen.burning.Dataclass;
import com.newgen.burning.DocumentOperations;
import com.newgen.burning.ODServerDetails;
import com.wipro.igrs.DeliveryOfDocuments.bo.DeliveryOfDocumentsBO;
import com.wipro.igrs.DeliveryOfDocuments.dao.DeliveryOfDocumentsDAO;
import com.wipro.igrs.DeliveryOfDocuments.dto.DeliveryOfDocumentsDTO;
import com.wipro.igrs.Seals.bd.SealsBD;
import com.wipro.igrs.Seals.constant.SealsConstant;
import com.wipro.igrs.common.PropertiesFileReader;
import com.wipro.igrs.propertylock.dao.PropertyLockDAO;
import com.wipro.igrs.regCompChecker.bd.RegCompCheckerBD;
import com.wipro.igrs.regCompChecker.constant.RegCompCheckerConstant;




public class DeliveryOfDocumentsBD{
	
	DeliveryOfDocumentsBO dodBO = null;
	
	
	private Logger logger = (Logger) Logger.getLogger(DeliveryOfDocumentsBO.class);
	
	 /******************************************************************  
	  *   Method Name  :   getstatusList()
	  *   Arguments    :    -
	  *   Return       :   ArrayList
	  *   Throws 	  :    Exception
	 *******************************************************************/  
	public ArrayList getstatusList(String lang) throws Exception{
		dodBO = new DeliveryOfDocumentsBO();
		ArrayList status =new ArrayList();
		try{
		status =dodBO.getStatus(lang);
		
		}catch(Exception e){
		}
		return status;
	}
	/******************************************************************  
	  *   Method Name  :   getChangestatusList() Rupali
	  *   Arguments    :    -
	  *   Return       :   ArrayList
	  *   Throws 	  :    Exception
	 *******************************************************************/  
	
	public ArrayList getChangestatusList(String lang) throws Exception{
		dodBO = new DeliveryOfDocumentsBO();
		ArrayList status =new ArrayList();
		try{
		status =dodBO.getChangestatusList(lang);
		
		}catch(Exception e){
		}
		return status;
	}
	 /******************************************************************  
	  *   Method Name  :   getPrintStatus() Rupali
	  *   Arguments    :    -
	  *   Return       :   ArrayList
	  *   Throws 	  :    Exception
	 *******************************************************************/  
	public ArrayList getPrintStatus(String lang) throws Exception{
		dodBO = new DeliveryOfDocumentsBO();
		ArrayList status =new ArrayList();
		try{
		status =dodBO.getPrintStatus(lang);
		
		}catch(Exception e){
		}
		return status;
	}
	
	 /******************************************************************  
	  *   Method Name  :   getregDetl()
	  *   Arguments    :   registration_completion_Number, status of doc, from date, to date, office id
	  *   Return       :   ArrayList
	  *   Throws 	  :    Exception
	 *******************************************************************/  
	public ArrayList getregDetl(String regno, String status, String frmDt,String toDt, String offId, String lang,ArrayList offcList, boolean flag) throws Exception{
		dodBO = new DeliveryOfDocumentsBO();
		ArrayList regDtl =new ArrayList();
		
		regno = regno==null?"":regno;
		status = status==null?"":status;
		frmDt = frmDt==null?"":frmDt;
		toDt = toDt==null?"":toDt;
		try{
			regDtl =dodBO.getregDetl(regno,status,frmDt,toDt,offId,lang, offcList,flag);
		
		}catch(Exception e){
		}
		return regDtl;
	}
	
	/******************************************************************  
	  *   Method Name  :   getregDetforChangeStatus()
	  *   Arguments    :   registration_completion_Number, status of doc, office id
	  *   Return       :   ArrayList
	  *   Throws 	  :    Exception
	 *******************************************************************/  
	public String getregDetforChangeStatus(String regno, String status, String offId, String lang,ArrayList offcList, boolean flag) throws Exception{
		dodBO = new DeliveryOfDocumentsBO();
		String flg = "";
		
		regno = regno==null?"":regno;
		status = status==null?"":status;
		
		try{
			flg =dodBO.getregDetforChangeStatus(regno,status,offId,lang, offcList,flag);
		
		}catch(Exception e){
		}
		return flg;
	}
	 /******************************************************************  
	  *   Method Name  :   getregDetlDr()
	  *   Arguments    :   registration_completion_Number, status of doc, from date, to date, district id
	  *   Return       :   ArrayList
	  *   Throws 	  :    Exception
	 *******************************************************************/  
	public ArrayList getregDetlDr(String regno, String status, String frmDt,String toDt, ArrayList offcList) throws Exception{
		dodBO = new DeliveryOfDocumentsBO();
		ArrayList regDtl =new ArrayList();
		regno = regno==null?"":regno;
		status = status==null?"":status;
		frmDt = frmDt==null?"":frmDt;
		toDt = toDt==null?"":toDt;
		try{
			regDtl =dodBO.getregDetlDr(regno,status,frmDt,toDt,offcList);
		
		}catch(Exception e){
		}
		return regDtl;
	}
	/******************************************************************  
	  *   Method Name  :   getregDetlDr()
	  *   Arguments    :   registration_completion_Number, status of doc, from date, to date, district id
	  *   Return       :   ArrayList
	  *   Throws 	  :    Exception
	 *******************************************************************/  
	public ArrayList getregDetlDr(String regno,   ArrayList offcList) throws Exception{
		dodBO = new DeliveryOfDocumentsBO();
		ArrayList regDtl =new ArrayList();
		regno = regno==null?"":regno;
	//	status = status==null?"":status;
		try{
			regDtl =dodBO.getregDetlDr(regno,offcList);
		
		}catch(Exception e){
		}
		return regDtl;
	}
	
	 /******************************************************************  
	  *   Method Name  :   getTransDetl()
	  *   Arguments    :   registration_completion_Number
	  *   Return       :   ArrayList
	  *   Throws 	  :    Exception
	 *******************************************************************/  
	public ArrayList getTransDetl(String regno) throws Exception{
		dodBO = new DeliveryOfDocumentsBO();
		ArrayList transDtl =new ArrayList();
		try{
			transDtl =dodBO.getTransDetl(regno);
		
		}catch(Exception e){
		}
		return transDtl;
	}
	 /******************************************************************  
	  *   Method Name  :   getrecpDetl()
	  *   Arguments    :   registration_completion_Number
	  *   Return       :   ArrayList
	  *   Throws 	  :    Exception
	 *******************************************************************/  
	public ArrayList getrecpDetl(String regno) throws Exception{
		dodBO = new DeliveryOfDocumentsBO();
		ArrayList transDtl =new ArrayList();
		try{
			transDtl =dodBO.getrecpDetl(regno);
		
		}catch(Exception e){
		}
		return transDtl;
	}
	
	/******************************************************************  
	  *   Method Name  :   getpartyDetl()
	  *   Arguments    :   registration_completion_Number
	  *   Return       :   ArrayList
	  *   Throws 	  :    Exception
	 *******************************************************************/  
	public ArrayList getpartyDetl(String regno,String lang) throws Exception{
		dodBO = new DeliveryOfDocumentsBO();
		ArrayList transDtl =new ArrayList();
		try{
			transDtl =dodBO.getpartyDetl(regno,lang);
		
		}catch(Exception e){
		}
		return transDtl;
	}
	
	 /******************************************************************  
	  *   Method Name  :   getMainId()
	  *   Arguments    :   primary key/unique key
	  *   Return       :   ArrayList
	  *   Throws 	  :    Exception
	 *******************************************************************/  
	public String getMainId(String txnId) {
		dodBO = new DeliveryOfDocumentsBO();
		String paydtls=null;
		try{
			paydtls = dodBO.getMainId(txnId);
		
		}catch(Exception e){
		}
		return paydtls;
	}
	
	
	 /******************************************************************  
	  *   Method Name  :   getrprsntatvDetls()
	  *   Arguments    :   registration completion number
	  *   Return       :   ArrayList
	  *   Throws 	  :   Exception
	 *******************************************************************/  
	public ArrayList getrprsntatvDetls(String regno) throws Exception{
		dodBO = new DeliveryOfDocumentsBO();
		ArrayList regDtl =new ArrayList();
		try{
			regDtl =dodBO.getrprsntatvDetls(regno);
		
		}catch(Exception e){
		}
		return regDtl;
	}
	
	
	
	 /******************************************************************  
	  *   Method Name  :   getdestroyDetls()
	  *   Arguments    :   registration completion number
	  *   Return       :   ArrayList
	  *   Throws 	  :   Exception
	 *******************************************************************/  
	public ArrayList getdestroyDetls(String regno) throws Exception{
		dodBO = new DeliveryOfDocumentsBO();
		ArrayList desDtl =new ArrayList();
		try{
			desDtl =dodBO.getdestroyDetls(regno);
		
		}catch(Exception e){
		}
		return desDtl;
	}
	 /******************************************************************  
	  *   Method Name  :   getissueDetls()
	  *   Arguments    :   registration completion number
	  *   Return       :   ArrayList
	  *   Throws 	  :   Exception
	 *******************************************************************/  
	public ArrayList getissueDetls(String regno) throws Exception{
		dodBO = new DeliveryOfDocumentsBO();
		ArrayList desDtl =new ArrayList();
		try{
			desDtl =dodBO.getissueDetls(regno);
		
		}catch(Exception e){
		}
		return desDtl;
	}
	
	/******************************************************************  
	  *   Method Name  :   getApproveDetls()
	  *   Arguments    :   registration completion number
	  *   Return       :   ArrayList
	  *   Throws 	  :   Exception
	 *******************************************************************/  
	public ArrayList getApproveDetls(String regno) throws Exception{
		dodBO = new DeliveryOfDocumentsBO();
		ArrayList desDtl =new ArrayList();
		try{
			desDtl =dodBO.getApproveDetls(regno);
		
		}catch(Exception e){
		}
		return desDtl;
	}
	/******************************************************************  
	  *   Method Name  :   getdeliveredDetls()
	  *   Arguments    :   registration completion number
	  *   Return       :   ArrayList
	  *   Throws 	  :   Exception
	 *******************************************************************/  
	public ArrayList getdeliveredDetls(String regno) throws Exception{
		dodBO = new DeliveryOfDocumentsBO();
		ArrayList desDtl =new ArrayList();
		try{
			desDtl =dodBO.getdeliveredDetls(regno);
		
		}catch(Exception e){
		}
		return desDtl;
	}
	/******************************************************************  
	  *   Method Name  :   getdeliveredDetlsPost()
	  *   Arguments    :   registration completion number
	  *   Return       :   ArrayList
	  *   Throws 	  :   Exception
	 *******************************************************************/  
	public ArrayList getdeliveredDetlsPost(String regno) throws Exception{
		dodBO = new DeliveryOfDocumentsBO();
		ArrayList desDtl =new ArrayList();
		try{
			desDtl =dodBO.getdeliveredDetlsPost(regno);
		
		}catch(Exception e){
		}
		return desDtl;
	}
	
	
	 /******************************************************************  
	  *   Method Name  :   getDrDetl()
	  *   Arguments    :   registration completion number
	  *   Return       :   ArrayList
	  *   Throws 	  :   Exception
	 *******************************************************************/  
	public ArrayList getDrDetl(String regno) throws Exception{
		dodBO = new DeliveryOfDocumentsBO();
		ArrayList desDtl =new ArrayList();
		try{
			desDtl =dodBO.getDrDetl(regno);
		
		}catch(Exception e){
		}
		return desDtl;
	}
	
	 /******************************************************************  
	  *   Method Name  :   statusDestroyUpdate()
	  *   Arguments    :   registration completion number, user id, office id
	  *   Return       :   Boolean
	  *   Throws 	  :    Exception
	 *******************************************************************/  
	public  boolean statusDestroyUpdate (String regno, String uid, String offid)throws Exception,
	 SQLException,Exception{
		 dodBO = new DeliveryOfDocumentsBO();
	 	 boolean flg = false;
	 	 try{flg = dodBO.statusDestroyUpdate(regno, uid, offid);
	 	  }catch(Exception e){
			}
	 	 return flg;
	 }
	 /******************************************************************  
	  *   Method Name  :   statusApproveUpdate()
	  *   Arguments    :   registration completion number, user id, office id
	  *   Return       :   Boolean
	  *   Throws 	  :    Exception
	 *******************************************************************/  
	public  boolean statusApproveUpdate (String regno, String uid, String offid)throws Exception,
	 SQLException,Exception{
		 dodBO = new DeliveryOfDocumentsBO();
	 	 boolean flg = false;
	 	 try{flg = dodBO.statusApproveUpdate(regno, uid, offid);
	 	  }catch(Exception e){
			}
	 	 return flg;
	 }
	 /******************************************************************  
	  *   Method Name  :   statusDeliveredUpdate()
	  *   Arguments    :   registration completion number, user id, office id
	  *   Return       :   Boolean
	  *   Throws 	  :    Exception
	 *******************************************************************/  
	public  boolean statusDeliveredUpdate (String regno, String uid, String offid, String chckArry,DeliveryOfDocumentsDTO dto)throws Exception,
	 SQLException,Exception{
		 dodBO = new DeliveryOfDocumentsBO();
	 	 boolean flg = false;
	 	 try{flg = dodBO.statusDeliveredUpdate(regno, uid, offid, chckArry,dto);
	 	  }catch(Exception e){
			}
	 	 return flg;
	 }
	 /******************************************************************  
	  *   Method Name  :   statusDeliveredUpdatePost()
	  *   Arguments    :   registration completion number, user id, office id
	  *   Return       :   Boolean
	  *   Throws 	  :    Exception
	 *******************************************************************/  
	public  boolean statusDeliveredUpdatePost (String regno, String uid, String offid, String docketno)throws Exception,
	 SQLException,Exception{
		 dodBO = new DeliveryOfDocumentsBO();
	 	 boolean flg = false;
	 	 try{flg = dodBO.statusDeliveredUpdatePost(regno, uid, offid, docketno);
	 	  }catch(Exception e){
			}
	 	 return flg;
	 }
	
	
	 /******************************************************************  
	  *   Method Name  :   statusDeliveredUpdateP()
	  *   Arguments    :   registration completion number, user id, office id
	  *   Return       :   Boolean
	  *   Throws 	  :    Exception
	 *******************************************************************/  
	public  boolean statusDeliveredUpdateP (String regno, String uid, String offid, String chckArry)throws Exception,
	 SQLException,Exception{
		 dodBO = new DeliveryOfDocumentsBO();
	 	 boolean flg = false;
	 	 try{flg = dodBO.statusDeliveredUpdateP(regno, uid, offid, chckArry);
	 	  }catch(Exception e){
			}
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
	public  boolean statusNoticeUpdate (String regno, String uid, String offid, String remarks, String lastdt, ArrayList partyList)throws Exception,
	 SQLException,Exception{
		 dodBO = new DeliveryOfDocumentsBO();
	 	 boolean flg = false;
	 	 try{flg = dodBO.statusNoticeUpdate(regno, uid, offid,remarks,lastdt,partyList);
	 	  }catch(Exception e){
			}
	 	 return flg;
	 }
	/******************************************************************  
	  *   Method Name  :   statusDeliveredUpdatePymt()
	  *   Arguments    :   registration completion number, user id, office id
	  *   Return       :   Boolean
	  *   Throws 	  :    Exception
	 *******************************************************************/  
	public  boolean statusDeliveredUpdatePymt (String regno, String uid, String offid, String chckArry, String fee)throws Exception,
	 SQLException,Exception{
		 dodBO = new DeliveryOfDocumentsBO();
	 	 boolean flg = false;
	 	 try{flg = dodBO.statusDeliveredUpdatePymt(regno, uid, offid, chckArry,fee);
	 	  }catch(Exception e){
			}
	 	 return flg;
	 }
	 /******************************************************************  
	  *   Method Name  :   statusDeliveredUpdate1()
	  *   Arguments    :   registration completion number, user id, office id
	  *   Return       :   Boolean
	  *   Throws 	  :    Exception
	 * @param docPath 
	 * @param docName 
	 * @param dto 
	 *******************************************************************/  
	public  boolean statusDeliveredUpdate1 (String regno, String uid, String offid, String fname, String mname, String lname, String docName, String docPath )throws Exception,
	 SQLException,Exception{
		 dodBO = new DeliveryOfDocumentsBO();
	 	 boolean flg = false;
	 	 try{flg = dodBO.statusDeliveredUpdate1(regno, uid, offid, fname,mname,lname,docName,docPath );
	 	  }catch(Exception e){
			}
	 	 return flg;
	 }
	 /******************************************************************  
	  *   Method Name  :   statusDeliveredUpdateP1()
	  *   Arguments    :   registration completion number, user id, office id
	  *   Return       :   Boolean
	  *   Throws 	  :    Exception
	 * @param docPath 
	 * @param docName 
	 * @param dto 
	 *******************************************************************/  
	public  boolean statusDeliveredUpdateP1 (String regno, String uid, String offid, String fname, String mname, String lname, String docName, String docPath )throws Exception,
	 SQLException,Exception{
		 dodBO = new DeliveryOfDocumentsBO();
	 	 boolean flg = false;
	 	 try{flg = dodBO.statusDeliveredUpdateP1(regno, uid, offid, fname,mname,lname,docName,docPath );
	 	  }catch(Exception e){
			}
	 	 return flg;
	 }
	 /******************************************************************  
	  *   Method Name  :   getfee()
	  *   Arguments    :   registration completion number
	  *   Return       :   ArrayList
	  *   Throws 	  :   Exception
	 *******************************************************************/  
	public String getfee(String funid,String serId,String userType) throws Exception{
		dodBO = new DeliveryOfDocumentsBO();
		String desDtl="";
		ArrayList li = new ArrayList();
		try{
			li =dodBO.getfee(funid,serId,userType);
			
			if(li!=null && li.size()>0){
    			desDtl= ((String)li.get(2));
    		}
		
		}catch(Exception e){
		}
		return desDtl;
	}
	
	 /******************************************************************  
	  *   Method Name  :   getId()
	  *   Arguments    :   -
	  *   Return       :   ArrayList
	  *   Throws 	  :   Exception
	 *******************************************************************/  
	public String getId() throws Exception{
		dodBO = new DeliveryOfDocumentsBO();
		String desDtl="";
		
		try{
			desDtl= dodBO.getId();
   		}
		catch(Exception e){
		}
		return desDtl;
	}
	
	/******************************************************************  
	  *   Method Name  :   getPayDtls()
	  *   Arguments    :    reg no
	  *   Return       :   list
	  *   Throws 	  :   NullPointer  or SQLException or Exception
	 *******************************************************************/  
	public ArrayList getPayDtls(String txnId)throws NullPointerException,
	 SQLException,Exception{
		dodBO = new DeliveryOfDocumentsBO();
		ArrayList paydtls =new ArrayList();
		try{
			paydtls = dodBO.getPayDtls(txnId);
		
		}catch(Exception e){
		}
		return paydtls;
	}
	/******************************************************************  
	  *   Method Name  :   getmodDetls()
	  *   Arguments    :    reg no
	  *   Return       :   list
	  *   Throws 	  :   NullPointer  or SQLException or Exception
	 *******************************************************************/  
	public ArrayList getmodDetls(String txnId)throws NullPointerException,
	 SQLException,Exception{
		dodBO = new DeliveryOfDocumentsBO();
		ArrayList modDetl =new ArrayList();
		try{
			modDetl = dodBO.getmodDetls(txnId);
		
		}catch(Exception e){
		}
		return modDetl;
	}
	
	/******************************************************************  
	  *   Method Name  :   insertPay()
	  *   Arguments    :    reg no, fee, user id 
	  *   Return       :   Boolean
	  *   Throws 	  :   NullPointer  or SQLException or Exception
	 *******************************************************************/  
	 public  boolean insertPay(String regNo, String fee, String uid, String id)throws NullPointerException,
	 SQLException,Exception{
		 dodBO = new DeliveryOfDocumentsBO();
	 	 boolean flg = false;
	 	try{ flg = dodBO.insertPay(regNo,fee,uid,id);
	 	
	 	}catch(Exception e){
		}
	 	 return flg;
	 }
	
	 /******************************************************************  
	  *   Method Name  :   getrequestDetails()
	  *   Arguments    :   office id
	  *   Return       :   ArrayList
	  *   Throws 	  :   Exception
	 *******************************************************************/  
	
	public ArrayList getrequestDetails(String txnId) throws Exception{
		 dodBO = new DeliveryOfDocumentsBO();
		ArrayList list = dodBO.getrequestDetails(txnId);
       
		return list;
	}
	
	public String getOffcTypeDetl(String offId) throws Exception{
		 dodBO = new DeliveryOfDocumentsBO();
		 return dodBO.getOffcTypeDetl(offId);
	}
	
	public ArrayList getChildOffcDetl(String offId) throws Exception{
		 dodBO = new DeliveryOfDocumentsBO();
		 return dodBO.getChildOffcDetl(offId);
	}
	
	
	public ArrayList getPrintStatusDetail(String regno) throws Exception{
		 dodBO = new DeliveryOfDocumentsBO();
		 return dodBO.getPrintStatusDetail(regno);
	}
	 /******************************************************************  
	  *   Method Name  :   statusDeliveredUpdate1()
	  *   Arguments    :   registration completion number, user id, office id
	  *   Return       :   Boolean
	  *   Throws 	  :    Exception
	 * @param docPath 
	 * @param docName 
	 * @param dto 
	 *******************************************************************/  
	public  boolean statusPrintedUpdate (String regno, String uid, String offid )throws Exception,
	 SQLException,Exception{
		 dodBO = new DeliveryOfDocumentsBO();
	 	 boolean flg = false;
	 	 try{flg = dodBO.statusPrintedUpdate(regno, uid, offid);
	 	  }catch(Exception e){
			}
	 	 return flg;
	 }
	public String getRegTxnID(String eRegNo) throws Exception{
		 dodBO = new DeliveryOfDocumentsBO();
		 return dodBO.getRegTxnID(eRegNo);
	}
	 
	public boolean putSrSgnOnFinalDoc(DeliveryOfDocumentsDTO dto, String userId,String offcId)
	{
		boolean flag = false;
		 int result = -1;
		try
		{
			
		
			PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
			DocumentOperations docOperations = new DocumentOperations();
		    ODServerDetails connDetails = new ODServerDetails();
		    Dataclass metaDataInfo = new Dataclass();
		    connDetails.setAppServerIp(pr.getValue("AppServerIp"));
		    connDetails.setCabinetName(pr.getValue("CabinetName"));
		    connDetails.setAppServerUserId(pr.getValue("AppServerUserId"));
		    connDetails.setAppServerUserPass(pr.getValue("AppServerUserPass"));
		    connDetails.setImageServerIp(pr.getValue("ImageServerIP"));
		    connDetails.setImageServerPort(pr.getValue("ImageServerPort"));
		    connDetails.setIniPath("D:\\workspace_25Feb\\IGRS\\WebContent\\WEB-INF\\lib\\userDbId.ini");
		    metaDataInfo.setUniqueNo(dto.getRegInitNumber());
		    metaDataInfo.setType(RegCompCheckerConstant.UNIQUE_CONSTANT_FOR_FINAL_DOC);
		    metaDataInfo.setDataclassName(pr.getValue("IGRSDataclass"));
			//BurningImageAndText burnObj = new BurningImageAndText();  
		    RegCompCheckerBD bd = new RegCompCheckerBD();
		    String compNumber=bd.getCompletionNumber(dto.getRegInitNumber());
		    String hindiFont = pr.getValue("dms_hindi_font_path");
			String englishFont = pr.getValue("dms_english_font_path");
			SealsBD sealBD=new SealsBD();
			boolean val=sealBD.validateFont(hindiFont,englishFont);
			if(!val){
				throw new Exception();
			}
			
			BurningImageAndText burnObj = new BurningImageAndText(hindiFont,englishFont);  
			 
		      String downloadPath = SealsConstant.DOWNLOAD_DEED_PATH_NEW+File.separator+dto.getRegInitNumber();
				File output2;
				output2 = new File(downloadPath.toString());
				
				if (output2.exists() == false) {
					logger.debug("Parent not found creating directory....");
					output2.mkdirs();
				}
		    String outputPath = RegCompCheckerConstant.DOWNLOAD_DEED_PATH+File.separator+dto.getRegInitNumber();
		    File output;
			output = new File(outputPath.toString());
			
			if (output.exists() == false) {
				logger.debug("Parent not found creating directory....");
				output.mkdirs();
			}
			logger.debug("sr sign path----->"+dto.getParentPathSrSign()+File.separator+dto.getFileNameSrSign());
			logger.debug("sr sign path----->"+dto.getParentPathSrSign());
			logger.debug("sr sign path----->"+dto.getFileNameSrSign());
			String srSignPath = dto.getParentPathSrSign()+dto.getFileNameSrSign();
			logger.debug("final path........................."+srSignPath);
			if (null==metaDataInfo.getUniqueNo()) {
				return false;
			}
			if (metaDataInfo.getUniqueNo().equals("")) {
				return false;
			}
			try{
			result = burnObj.addSignatureToRegCert(connDetails, metaDataInfo, downloadPath, compNumber.trim()+RegCompCheckerConstant.NAME_OF_FINAL_DOC_EXETENSION,RegCompCheckerConstant.UNIQUE_CONSTANT_FOR_REG_CERT,RegCompCheckerConstant.NAME_OF_REG_CERT,outputPath, RegCompCheckerConstant.UNIQUE_CONSTANT_FOR_FINAL_DOC, compNumber.trim()+RegCompCheckerConstant.NAME_OF_FINAL_DOC_EXETENSION, RegCompCheckerConstant.DMS_FOLDER, srSignPath);
		//	result = burnObj.addSignatureToRegCert(connDetails, metaDataInfo, downloadPath, compNumber.trim()+RegCompCheckerConstant.NAME_OF_FINAL_DOC_EXETENSION,outputPath, RegCompCheckerConstant.UNIQUE_CONSTANT_FOR_FINAL_DOC, compNumber.trim()+RegCompCheckerConstant.NAME_OF_FINAL_DOC_EXETENSION, RegCompCheckerConstant.DMS_FOLDER, srSignPath);
				
			}catch(Throwable t){
				logger.debug(t.getMessage(),t);
			}
		}
		catch(Exception e)
		{
			logger.debug("Exception in generateRegCertificate");
			logger.debug(e.getStackTrace());
		}finally{
			logger.debug("RESULT SET IS"+result);
		}
		if(result == 0)
			flag = true;
		return flag;
	}
	
	public  boolean statusUndeliveredUpdate (String regno, String uid, String offid )throws Exception,
	 SQLException,Exception{
		 dodBO = new DeliveryOfDocumentsBO();
	 	 boolean flg = false;
	 	 try{flg = dodBO.statusUndeliveredUpdate(regno, uid, offid);
	 	  }catch(Exception e){
			}
	 	 return flg;
	 }
	public  boolean signatureTimeUpdate (String regno)throws Exception,
	 SQLException,Exception{
		 dodBO = new DeliveryOfDocumentsBO();
	 	 boolean flg = false;
	 	 try{flg = dodBO.signatureTimeUpdate(regno);
	 	  }catch(Exception e){
			}
	 	 return flg;
	 }
	public  boolean updatePinLinking (String regno, String deedID, DeliveryOfDocumentsDTO dto ,String userId)throws Exception,
	 SQLException,Exception{
		 dodBO = new DeliveryOfDocumentsBO();
	 	 boolean flg = false;
	 	 try{flg = dodBO.updatePinLinking(regno, deedID, dto,userId);
	 	  }catch(Exception e){
			}
	 	 return flg;
	 }
	 /******************************************************************  
	  *   Method Name  :   viewClaimantDelivery()
	  *   Arguments    :   office id
	  *   Return       :   ArrayList
	  *   Throws 	  :   Exception
	 *******************************************************************/  
	
	public ArrayList viewClaimantDelivery(String regno, String deedID, DeliveryOfDocumentsDTO dto ,String userId) throws Exception{
		 dodBO = new DeliveryOfDocumentsBO();
		ArrayList list = dodBO.viewClaimantDelivery(regno, deedID, dto,userId);
      
		return list;
	}
	public  boolean insertPinToSmsHand (String regno, String deedID, DeliveryOfDocumentsDTO dto ,String userId,String [] arr)throws Exception,
	 SQLException,Exception{
		 dodBO = new DeliveryOfDocumentsBO();
	 	 boolean flg = false;
	 	 try{flg = dodBO.insertPinToSmsHand(regno, deedID, dto,userId,arr);
	 	  }catch(Exception e){
			}
	 	 return flg;
	 }
	public String getSignStatus(String language) {
		dodBO = new DeliveryOfDocumentsBO();
		String paydtls=null;
		ArrayList list=new ArrayList();
		try{
			list = dodBO.getSignStatus();
			ArrayList subList=(ArrayList)list.get(0);
			if("en".equalsIgnoreCase(language)){
				paydtls=subList.get(0).toString();
			}else{
				paydtls=subList.get(1).toString();
			}
		
		}catch(Exception e){
		}
		return paydtls;
	}
	public String getSignFilePath(String regComp) {
		dodBO = new DeliveryOfDocumentsBO();
		String paydtls=null;
		try{
			paydtls = dodBO.getSignFilePath(regComp);
		
		}catch(Exception e){
		}
		return paydtls;
	}
	public String getFee(String funId) {
		dodBO = new DeliveryOfDocumentsBO();
		String paydtls=null;
		try{
			paydtls = dodBO.getFee(funId);
		
		}catch(Exception e){
		}
		return paydtls;
	}
	
	//Start : Changes added by Neeti for RCMS
	
	public boolean checkDeedTypeForRCMS(String regTxnID) {
		dodBO = new DeliveryOfDocumentsBO();
		boolean deedType=false;
		try{
			String deedAvail= dodBO.checkDeedTypeForRCMS(regTxnID);
			if(deedAvail!=null && !deedAvail.isEmpty())
				deedType= true;
			else
				deedType=false;
		}catch(Exception e){
		}
		return deedType;
	}
	
	public ArrayList checkPropertyForAgriLand(String regTxnID) {
		dodBO = new DeliveryOfDocumentsBO();
		ArrayList propDtl =new ArrayList();
		try{
			propDtl =dodBO.checkPropertyForAgriLand(regTxnID);
		
		}catch(Exception e){
		}
		return propDtl;
	}
	
	public String getRCMSFlag(String regTxnID) {
		dodBO = new DeliveryOfDocumentsBO();
		String rcmsFlag=null;
		try{
			rcmsFlag = dodBO.getRCMSFlag(regTxnID);
		
		}catch(Exception e){
		}
		return rcmsFlag;
	}
	
	public String getTehsilID(String regNum) {
		dodBO = new DeliveryOfDocumentsBO();
		String tehsilID=null;
		try{
			tehsilID = dodBO.getTehsilID(regNum);
		
		}catch(Exception e){
		}
		return tehsilID;
	}
	
	//End : Changes added by Neeti for RCMS
}