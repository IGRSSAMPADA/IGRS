/**
 * Copyright (c) 2009-10 WIPRO INFOTECH. All Rights Reserved.
 *
 *  This software is the confidential and proprietary information of WIPRO
 *  ("Confidential Information"). You shall not
 *  disclose such Confidential Information and shall use it only in
 *  accordance with the terms of the license agreement you entered into
 *  with WIPRO.
 *
 * WIPRO MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE
 * SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, OR NON-INFRINGEMENT. WIPRO SHALL NOT BE
 * LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF
 * USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES.
 *
 * Customer specific copyright notice - NA
 */
package com.wipro.igrs.caveats.bo;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.wipro.igrs.caveats.bd.CaveatsDelegate;
import com.wipro.igrs.caveats.dao.CaveatsDAO;
import com.wipro.igrs.caveats.dto.CaveatsDTO;
import com.wipro.igrs.caveats.dto.OTTDetailDTO;
import com.wipro.igrs.caveats.form.CaveatsForm;
import com.wipro.igrs.copyissuance.dao.CertifiedCopyDetailsDAO;
import com.wipro.igrs.copyissuance.dto.CertifiedCopyDetailsDTO;
import com.wipro.igrs.propertylock.dao.PropertyLockDAO;
import com.wipro.igrs.report.dao.ReportDAO;
import com.wipro.igrs.report.dto.MISReportDTO;
import com.wipro.igrs.util.CommonUtil;

/**
* 
* CaveatsBO.java <br>
* CaveatsBO <br>
* 
* @version 1.0
* 
* @author <b>WIPRO JAVA TEAM</b> <br>
*         Created on 12-Apr-2008 <br>
*         Last Modified on 28-Mar-2013
*/
@SuppressWarnings({ "rawtypes" })
public class CaveatsBO {
	
	/**
	 * 
	 */
	public CaveatsBO() {
	}

	private Logger logger = (Logger) Logger.getLogger(CaveatsBO.class);

	/**
	 * @param param1
	 * @param param2
	 * @param fileName
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public boolean logCaveatsBO(String param1[], String param2[], String fileName, File file)
			throws Exception {
		logger.debug("WE ARE IN BD Debug");
		CaveatsDAO refDAO = new CaveatsDAO();
		try {
			logger.info("Inside (logCaveatsBO) Method");
			return refDAO.logCaveatsDAO(param1, param2, fileName, file);
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}
	
	public  String getReginitId (String regComNo)throws Exception,
	 SQLException,Exception{
		 String reginitId="";
		 CaveatsDAO dao = new CaveatsDAO(); 
		 	 	
	 	 try{
	 		reginitId = dao.getReginitId(regComNo);
	 	  }catch(Exception e){
			}
	 	 return reginitId;
	 }
	
	
	////
	
	
	/*public boolean uploadCaveatsBO(CaveatsForm fm)
			throws Exception {
		logger.debug("WE ARE IN BD Debug");
		CaveatsDAO refDAO = new CaveatsDAO();
		try {
			logger.info("Inside (logCaveatsBO) Method");
		//	return refDAO.uploadCaveatsDAO(param1, param2, fileName, file);
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}
*/
	/**
	 * @param _refId
	 * @return
	 * @throws Exception
	 */
	public ArrayList findCaveatBO(String _refId) throws Exception {
		logger.debug("WE ARE IN BD Debug");
		CaveatsDAO refDAO = new CaveatsDAO();
		try {
			logger.info("Inside (findCaveatBO) Method");
			return refDAO.findCaveatDAO(_refId);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	/**
	 * @param _refId
	 * @return
	 * @throws Exception
	 */
	public ArrayList releaseCvtBankByRefIdBO(String _refId) throws Exception {
		logger.debug("WE ARE IN BD Debug");
		CaveatsDAO refDAO = new CaveatsDAO();
		try {
			logger.info("Inside (releaseCvtBankByRefIdBO) Method");
			return refDAO.releaseCvtBankByRefIdDAO(_refId);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public static boolean insertSpDocDetls1(CaveatsForm fm) throws Exception
	{
		CaveatsDelegate objBD = new CaveatsDelegate();
		
		boolean inserTxn=objBD.insertSpDocDetls1(fm);

		return inserTxn;
	}
	
	
	
	
	
	/**
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public ArrayList releaseSearchByAllBO(String param[])
			throws Exception {
		logger.debug("WE ARE IN BD Debug");
		CaveatsDAO refDAO = new CaveatsDAO();
		try {
			logger.info("Inside (releaseSearchByAllBO) Method");
			return refDAO.releaseSearchByAllDAO(param);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	/**
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public ArrayList relsCavtBankByAllBO(String param[])
			throws Exception {
		logger.debug("WE ARE IN BD Debug");
		CaveatsDAO refDAO = new CaveatsDAO();
		try {
			logger.info("Inside (relsCavtBankByAllBO) Method");
			return refDAO.relsCavtBankByAllDAO(param);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	/**
	 * @param _regNo
	 * @return
	 * @throws Exception
	 */
	public ArrayList releaseByRegnoBO(String _regNo) throws Exception {
		logger.debug("WE ARE IN BD Debug");
		CaveatsDAO refDAO = new CaveatsDAO();
		try {
			logger.info("Inside (releaseByRegnoBO) Method");
			return refDAO.releaseByRegnoDAO(_regNo);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	/**
	 * @param _regNo
	 * @return
	 * @throws Exception
	 */
	public ArrayList searchRegIdBO(String _regNo) throws Exception {
		logger.debug("WE ARE IN BD Debug");
		CaveatsDAO refDAO = new CaveatsDAO();
		try {
			logger.info("Inside (searchRegIdBO) Method");
			return refDAO.searchRegIdDao(_regNo);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	/**
	 * @param caveatDTO
	 * @return
	 * @throws Exception
	 */
	public boolean modifyFlagBO(CaveatsDTO caveatDTO) throws Exception {
		logger.debug("WE ARE IN BD Debug");
		CaveatsDAO refDAO = new CaveatsDAO();
		try {
			logger.info("Inside (modifyFlagBO) Method");
			return refDAO.modifyFlagDAO(caveatDTO);
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}
	
	
	////
/*	public boolean updateFlagBO(CaveatsDTO caveatDTO) throws Exception {
		logger.debug("WE ARE IN BD Debug");
		CaveatsDAO refDAO = new CaveatsDAO();
		try {
			logger.info("Inside (modifyFlagBO) Method");
			//return refDAO.updateFlagDAO(caveatDTO);
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}*/

	/**
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public boolean relBankUpdationBO(String param[]) throws Exception {
		logger.debug("WE ARE IN BD Debug");
		CaveatsDAO refDAO = new CaveatsDAO();
		try {
			logger.info("Inside (relBankUpdationBO) Method");
			return refDAO.relBankUpdationDAO(param);
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}

	/**
	 * @param _refid
	 * @return
	 * @throws Exception
	 */
	public ArrayList searchByRefidBO(String _refid,String language) throws Exception {
		logger.debug("WE ARE IN BD Debug");
		CaveatsDAO refDAO = new CaveatsDAO();
		try {
			logger.info("Inside (searchByRefidBO) Method");
			return refDAO.searchByRefidDAO(_refid,language);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	/**
	 * @param param
	 * @return
	 * @throws Exception
	 */
	
	
	
	
	public ArrayList searchByAllBO(String param[],String language) throws Exception {
		logger.debug("WE ARE IN BD Debug");
		CaveatsDAO refDAO = new CaveatsDAO();
		try {
			logger.info("Inside (searchByAllBO) Method");
			return refDAO.searchByAllDAO(param,language);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	
	/**
	 * @param param
	 * @param userID
	 * @return
	 * @throws Exception
	 */
	public ArrayList searchBankAllBO(String param[], String userID,String language) throws Exception {
		logger.debug("WE ARE IN BD Debug");
		CaveatsDAO refDAO = new CaveatsDAO();
		try {
			logger.info("Inside (searchBankAllBO) Method");
			return refDAO.searchBankAllDAO(param, userID,language);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	/**
	 * @param _regNo
	 * @param _pin
	 * @return
	 * @throws Exception
	 */
	public ArrayList searchForPinBO(String _regNo, String _pin)
			throws Exception {
		logger.debug("WE ARE IN BD Debug");
		CaveatsDAO refDAO = new CaveatsDAO();
		try {
			logger.info("Inside (searchForPinBO) Method");
			return refDAO.searchForPinDAO(_regNo, _pin);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	/**
	 * @param _regNo
	 * @param _pinOtt
	 * @param _ottauto
	 * @return
	 * @throws Exception
	 */
	public CaveatsDTO insertOttBO(String _regNo, String _pinOtt, String _ottauto)
			throws Exception {
		logger.debug("WE ARE IN BD Debug");
		CaveatsDAO refDAO = new CaveatsDAO();
		try {
			logger.info("Inside (insertOttBO) Method");
			return refDAO.insertOttDAO(_regNo, _pinOtt, _ottauto);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	/**
	 * @param dto
	 * @return
	 */
	public boolean searchForOttBO(CaveatsDTO dto) {
		boolean retVal = false;
		try {
			CaveatsDAO refDAO = new CaveatsDAO();
			retVal = refDAO.searchForOttDAO(dto);
		} catch (Exception e) {
			logger.error(e);
		}
		return retVal;
	}
	
	/**
	 * @param dto
	 * @return
	 */
	public boolean searchBankPropertyByRefID(CaveatsDTO dto) {
		boolean retVal = false;
		try {
			CaveatsDAO refDAO = new CaveatsDAO();
			retVal = refDAO.searchBankProperty(dto);
		} catch (Exception e) {
			logger.error(e);
		}
		return retVal;
	}

	/**
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public boolean insertCaveatBankBO(String param[]) throws Exception {
		logger.debug("WE ARE IN BD Debug");
		CaveatsDAO refDAO = new CaveatsDAO();
		try {
			logger.info("Inside (insertCaveatBankBO) Method");
			return refDAO.insertCaveatBankDAO(param);
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}

	/**
	 * @param _regNo
	 * @param _ott
	 * @return
	 * @throws Exception
	 */
	public boolean updateOttStatusBO(String _regNo, String _ott)
			throws Exception {
		logger.debug("WE ARE IN BD Debug");
		CaveatsDAO refDAO = new CaveatsDAO();
		try {
			logger.info("Inside (updateOttStatusBO) Method");
			return refDAO.updateOttStatusDAO(_regNo, _ott);
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}

	/**
	 * @param _caveatsDto
	 * @param payAmount 
	 * @return
	 */
	public boolean logCaveats(CaveatsDTO _caveatsDto,String userId,String functionId, String payAmount) {
		boolean retVal = false;
		try {
			CaveatsDAO refDAO = new CaveatsDAO();
			retVal =refDAO.logCaveats( _caveatsDto,userId,functionId,payAmount);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			retVal = false;
		}
		return retVal;
	}

	/**
	 * @param registrationNumber
	 * @return
	 */
	public ArrayList getPropertyTxnIDList(String registrationNumber) {
		ArrayList retVal = new ArrayList();
		try {
			CaveatsDAO refDAO = new CaveatsDAO();
			retVal = refDAO.getPropertyTxnID(registrationNumber);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return retVal;
	}
	
	/**
	 * @param registrationNumber
	 * @return
	 */
	public ArrayList searchForAvailablePIN(String registrationNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @param attribName
	 * @return
	 */
	public String getOTTValidity(String attribName) {
		String retVal="NA";
		try {
			CaveatsDAO refDAO = new CaveatsDAO();
			retVal = refDAO.getOTTValidity(attribName);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			
		}
		return retVal;
	}

	/**
	 * @param caveatfrm
	 */
	public void generateOTTNumbers(CaveatsForm caveatfrm) {
		try {
			CaveatsDAO refDAO = new CaveatsDAO();
			refDAO.generateOTTNumbers(caveatfrm);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			
		}
	}

	/**
	 * @param selectedList
	 * @return
	 */
	public ArrayList<String> getActiveOTTPropertyMapping(ArrayList selectedList) {
		ArrayList<String> retVal = new ArrayList<String>();
		try {
			CaveatsDAO refDAO = new CaveatsDAO();
			retVal = refDAO.getActiveOTTPropertyMapping(selectedList);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return retVal;
	}

	/**
	 * @return
	 */
	public ArrayList getCaveatTypeList(String language) {
		ArrayList retVal = new ArrayList<String>();
		try {
			CaveatsDAO refDAO = new CaveatsDAO();
			retVal = refDAO.createCaveatsList(language);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return retVal;
	}

	/**
	 * @param dto
	 * @return
	 */
	public boolean logBankCaveat(CaveatsDTO dto) {
		CaveatsDAO refDAO = new CaveatsDAO();
		try {
			return refDAO.logBankCaveat(dto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * @param dto
	 * @return
	 */
	public boolean searchBankCaveat(CaveatsDTO dto) {
		CaveatsDAO refDAO = new CaveatsDAO();
		return refDAO.searchBankCaveat(dto);
	}

	/**
	 * @param userID
	 * @return
	 */
	public String checkBankRoleForUser(String userID) {
		CaveatsDAO refDAO = new CaveatsDAO();
		return refDAO.checkBankRoleForUser(userID);
	}

	/**
	 * @param cDTO
	 * @param userID
	 * @return
	 */
	public  ArrayList<OTTDetailDTO> getOTTListing(CaveatsDTO cDTO, String userID,String language) {
		CaveatsDAO refDAO = new CaveatsDAO();
		return refDAO.searchBankOTT(cDTO, userID,language);
	}

	/**
	 * @param registrationNumber
	 * @return
	 */
	public int getStayOrderLoggedCount(String registrationNumber) {
		CaveatsDAO refDAO = new CaveatsDAO();
		return refDAO.getStayOrderLoggedCount(registrationNumber);
	}

	public ArrayList getFee(String functionId, String serId, String loggedIn) {
		CaveatsDAO refDAO = new CaveatsDAO();
		return refDAO.getFee(functionId, serId, loggedIn);
	}

	
	//ashima
	public ArrayList getPendingApps(String userId,String language) throws Exception
	{
		
		
		CaveatsDelegate objCaveatBD = new CaveatsDelegate();
		ArrayList pendingAppListFinal = new ArrayList();
		ArrayList list=objCaveatBD.getPendingApps(userId,language);
		
		if(list!=null && list.size()>0){
			
			ArrayList rowList;
			
	        try{
			for (int i = 0; i < list.size(); i++) {
				
				CaveatsDTO objCaveatDTO = new CaveatsDTO();
				
				rowList = (ArrayList)list.get(i);
				//objDashBoardDTO = new DashBoardDTO();
			
				//if(rowList.get(0).toString().length()==11){
				
				objCaveatDTO.setTransactionID(rowList.get(0).toString());
				objCaveatDTO.setDocName(rowList.get(1).toString());
				
				objCaveatDTO.setCreateDate(rowList.get(6).toString());
				objCaveatDTO.setPayableamount(rowList.get(3).toString());
					
					
							
					//objDashBoardDTO.setPaidAmount(rowList.get(2));
					//System.out.println("*******"+(String)rowList.get(2));
				if ((rowList.get(2))==null)
				{
					
					objCaveatDTO.setPaidAmount("-");
					objCaveatDTO.setBalanceAmount(rowList.get(3).toString());
				}
					
				/*else
					objCaveatDTO.setPaidAmount(rowList.get(2).toString());
				
				if (rowList.get(2)== null)
				{
					objCaveatDTO.setBalanceAmount(rowList.get(6).toString());
				}*/
				else
				{	//objCaveatDTO.setBalanceAmount(rowList.get(3).toString());
				
				objCaveatDTO.setPaidAmount(rowList.get(2).toString());
				//objCaveatDTO.setBalanceAmount("-");
				//objEstampDetailsDTO.setLastTransactionDate(rowList.get(4).toString());
				}
				
				
				if(rowList.get(4)==null ||rowList.get(4).toString().equalsIgnoreCase("") )
				{
					objCaveatDTO.setBalanceAmount(rowList.get(3).toString());
					
				}
				else
				{
					objCaveatDTO.setBalanceAmount(rowList.get(4).toString());
				}
				if ((rowList.get(5))==null||rowList.get(5).toString().equalsIgnoreCase(""))
				{	
					objCaveatDTO.setLastTransactionDate(rowList.get(6).toString());
				}
				else
					{
					objCaveatDTO.setLastTransactionDate(rowList.get(5).toString());
					}
				
				pendingAppListFinal.add(objCaveatDTO);
		
			}
				
			
				
			}
	        catch(Exception e){
	        	e.printStackTrace();
	        	
	        }
		}
		return pendingAppListFinal;
	}

	
	/*
	public CaveatsDTO getTransactionidDetails(String transid,String language)  throws Exception

	   
    {
		//objCaveatDTO.setLastTransactionDate(rowList.get(5).toString());
            

    	

    	CaveatsDAO dao= new CaveatsDAO();
    	//ArrayList pendingAppList = new ArrayList();
    	 ArrayList rowlist=dao.getTransidDetails(transid,language);
    	 CaveatsDTO dto= new CaveatsDTO();
		if(rowlist!=null && rowlist.size()>0){
			
			ArrayList list;
			
	        	
	        
			for (int i = 0; i < rowlist.size(); i++) {
				
				
       list = (ArrayList)rowlist.get(i);
       dto.setTransactionID(list.get(0).toString());
       dto.setPropertyTxnId(list.get(1).toString());
       //System.out.println(dto.getTransactionID());
       dto.setBankCourtName(list.get(3).toString());
		

       dto.setBankCourtAddress(list.get(4).toString());
       if((list.get(5))!=null)
       {
    	   dto.setBankCourtRepName(list.get(5).toString());
      // dto.setNameOfCourtPerson(list.get(5).toString());
       }
       else
       {
    	   dto.setBankCourtRepName("-");
       }
      // System.out.println(dto.getNameOfCourtPerson());
       if((list.get(21))!=null)
       {
           dto.setBankCourtCountry(list.get(21).toString());
       }
       else
       {
    	   dto.setBankCourtCountry("-");
       }

       dto.setBankCourtStateName(list.get(22).toString());
       dto.setCityDistrict(list.get(23).toString());
       if((list.get(24))!=null)
       {
       dto.setPaidAmount(list.get(24).toString());
       }
       else
       {
    	   dto.setPaidAmount("-");
       }
       if((list.get(6))!=null)
       {
    	   
       
       dto.setBankCourtPostalCode(list.get(6).toString());
       }
       else
       {
    	   dto.setBankCourtPostalCode("-");
       }
       if((list.get(7))!=null)
       {
    	   
       
       dto.setBankCourtPhoneNumber(list.get(7).toString());
       }
       else
       {dto.setBankCourtPhoneNumber("-");
    	   
       }
       ///
       
       if((list.get(8))!=null)
       {
    	   
       
       dto.setCaseNum(list.get(8).toString());
       }
       else
       {dto.setCaseNum("-");
    	   
       }
       if((list.get(9))!=null)
       {
    	   
       
       dto.setCourtOrderDate(list.get(9).toString());
       }
       else
       {dto.setCourtOrderDate("-");
    	   
       }
       
       
       
       ///
       
       
       if((list.get(8))!=null)
       {
       dto.setCaveatLabel(list.get(8).toString());
       }
       else
       {
    	   dto.setCaveatLabel("-");
       }
      if((list.get(9))!=null)
      {
       dto.setStayOrderNo(list.get(9).toString());
      }
     else
      {
    	  dto.setStayOrderNo("-");
      }
      if((list.get(10))!=null)
      {
    	  
      
       dto.setStayOrderDetails(list.get(10).toString());
      }
      else
      {
    	  dto.setStayOrderDetails("-");
      }
      if((list.get(11))!=null)
      {
       dto.setCaveatDetails(list.get(11).toString());
      }
      else
      {
          dto.setCaveatDetails("-");
  
      }
       dto.setDocName(list.get(12).toString());
       if((list.get(13))!=null)
       {
      dto.setUploaded_doc_path(list.get(13).toString());
       }
       else
       {
    	   dto.setUploaded_doc_path("-");
       }
       //System.out.println(dto.getUploaded_doc_path());
       if((list.get(14))!=null)
       {
       dto.setRemarks(list.get(14).toString());
       }
       else
       {
    	   dto.setRemarks("-");
       }
       if((list.get(15))!=null)
       {
       dto.setRegistrationNumber(list.get(15).toString());
       }
       else
       {
    	   dto.setRegistrationNumber("-");
       }
      //dto.setUploaded_doc_path(list.get(16).toString());
       dto.setCaveatSorderStatus(list.get(16).toString());
       dto.setStayOrderStartDate(list.get(17).toString());
       if((list.get(18))!=null)
       {
      dto.setStayOrderUptoDate(list.get(18).toString());
       }
       else
       {
    	   dto.setStayOrderUptoDate("-");
       }

    //  System.out.println(dto.getReleaseDate());
      if((list.get(19))!=null)
      {
    	  
      
       dto.setReasonForRelease(list.get(19).toString());
      }
      else
      {
    	  dto.setReasonForRelease("-");
      }
      
      if((list.get(20))!=null)
      {
    	  
    	  dto.setRemarksForRelease(list.get(20).toString());
      }
      else
      {
    	  dto.setRemarksForRelease("-");
      }
      
      
      
      if((list.get(2))!=null)
      {
          dto.setBalanceAmount(list.get(2).toString());

      }
      else
      {
          dto.setBalanceAmount("-");
      }

			}
			
     
     // pendingAppList.add(dto);
      
      //return pendingAppList;
			//return dto;
}
	
		return dto;
}
	
*/
	
	
	
	
	public CaveatsDTO getTransactionidDetails(String transid,String language)  throws Exception

	   
    {
		//objCaveatDTO.setLastTransactionDate(rowList.get(5).toString());
            

    	

    	CaveatsDAO dao= new CaveatsDAO();
    	//ArrayList pendingAppList = new ArrayList();
    	 ArrayList rowlist=dao.getTransidDetails(transid,language);
    	 CaveatsDTO dto= new CaveatsDTO();
		if(rowlist!=null && rowlist.size()>0){
			
			ArrayList list;
			
	        	
	        
			for (int i = 0; i < rowlist.size(); i++) {
				
				
       list = (ArrayList)rowlist.get(i);
       dto.setTransactionID(list.get(0).toString());
       dto.setPropertyTxnId(list.get(1).toString());
       if((list.get(2))!=null)
       {
           dto.setBalanceAmount(list.get(2).toString());

       }
       else
       {
           dto.setBalanceAmount("-");
       }
       //System.out.println(dto.getTransactionID());
       dto.setBankCourtName(list.get(3).toString());
		

       dto.setBankCourtAddress(list.get(4).toString());
       if((list.get(5))!=null)
       {
    	   dto.setBankCourtRepName(list.get(5).toString());
      // dto.setNameOfCourtPerson(list.get(5).toString());
       }
       else
       {
    	   dto.setBankCourtRepName("-");
       }
      // System.out.println(dto.getNameOfCourtPerson());
       
       if((list.get(6))!=null)
       {
    	   
       
       dto.setBankCourtPostalCode(list.get(6).toString());
       }
       else
       {
    	   dto.setBankCourtPostalCode("-");
       }
       if((list.get(7))!=null)
       {
    	   
       
       dto.setBankCourtPhoneNumber(list.get(7).toString());
       }
       else
       {dto.setBankCourtPhoneNumber("-");
    	   
       }
       ///
       
       if((list.get(8))!=null)
       {
    	   
       
       dto.setCaseNum(list.get(8).toString());
       }
       else
       {dto.setCaseNum("-");
    	   
       }
       if((list.get(9))!=null)
       {
    	   
       
       dto.setCourtOrderDate(list.get(9).toString());
       }
       else
       {dto.setCourtOrderDate("-");
    	   
       }
       
       
       
       ///
       
       
       if((list.get(10))!=null)
       {
       dto.setCaveatLabel(list.get(10).toString());
       }
       else
       {
    	   dto.setCaveatLabel("-");
       }
      if((list.get(11))!=null)
      {
       dto.setStayOrderNo(list.get(11).toString());
      }
     else
      {
    	  dto.setStayOrderNo("-");
      }
      if((list.get(12))!=null)
      {
    	  
      
       dto.setStayOrderDetails(list.get(12).toString());
      }
      else
      {
    	  dto.setStayOrderDetails("-");
      }
      if((list.get(13))!=null)
      {
       dto.setCaveatDetails(list.get(13).toString());
      }
      else
      {
          dto.setCaveatDetails("-");
  
      }
      if((list.get(14))!=null)
      {
       dto.setDocName(list.get(14).toString());
      }
      else
      {
    	  dto.setDocName("-");
      }
       if((list.get(15))!=null)
       {
      dto.setUploaded_doc_path(list.get(15).toString());
       }
       else
       {
    	   dto.setUploaded_doc_path("-");
       }
       //System.out.println(dto.getUploaded_doc_path());
       if((list.get(16))!=null)
       {
       dto.setRemarks(list.get(16).toString());
       }
       else
       {
    	   dto.setRemarks("-");
       }
       if((list.get(17))!=null)
       {
       dto.setRegistrationNumber(list.get(17).toString());
       }
       else
       {
    	   dto.setRegistrationNumber("-");
       }
      //dto.setUploaded_doc_path(list.get(16).toString());
       if((list.get(18))!=null)
       dto.setCaveatSorderStatus(list.get(18).toString());
       else
    	   dto.setCaveatSorderStatus("-");
       
       if((list.get(19))!=null)
    	   dto.setStayOrderStartDate(list.get(19).toString());
       else
    	   dto.setStayOrderStartDate("-");
       if((list.get(20))!=null)
       {
    	   dto.setStayOrderUptoDate(list.get(20).toString());
       }
       else
       {
    	   dto.setStayOrderUptoDate("-");
       }

    //  System.out.println(dto.getReleaseDate());
      if((list.get(21))!=null)
      {
    	  
      
       dto.setReasonForRelease(list.get(21).toString());
      }
      else
      {
    	  dto.setReasonForRelease("-");
      }
      
      if((list.get(22))!=null)
      {
    	  
    	  dto.setRemarksForRelease(list.get(22).toString());
      }
      else
      {
    	  dto.setRemarksForRelease("-");
      }
      
      if((list.get(23))!=null)
      {
          dto.setBankCourtCountry(list.get(23).toString());
      }
      else
      {
   	   dto.setBankCourtCountry("-");
      }

      dto.setBankCourtStateName(list.get(24).toString());
      dto.setCityDistrict(list.get(25).toString());
      if((list.get(26))!=null)
      {
      dto.setPaidAmount(list.get(26).toString());
      }
      else
      {
   	   dto.setPaidAmount("-");
      }
      
     

			}
			
     
     // pendingAppList.add(dto);
      
      //return pendingAppList;
			//return dto;
}
	
		return dto;
}
	

	
	
	
	
	
	
	
	
	/////////////
	
	public ArrayList getPartial(String userId) throws Exception
	{
		
		
		CaveatsDelegate objCaveatBD = new CaveatsDelegate();
		ArrayList partialListFinal = new ArrayList();
		ArrayList list=objCaveatBD.getPartial(userId);
		
		if(list!=null && list.size()>0){
			
			ArrayList rowList;
			
	        try{
			for (int i = 0; i < list.size(); i++) {
				
				CaveatsDTO objCaveatDTO = new CaveatsDTO();
				
				rowList = (ArrayList)list.get(i);
				
				
				objCaveatDTO.setTransactionID(rowList.get(0).toString());
				
				objCaveatDTO.setBalanceAmount(rowList.get(1).toString());
				
			/*		
				if ((rowList.get(1))==null)
				{
					
					objCaveatDTO.setBalanceAmount("-");
				}
					
				
				else
				{	//objCaveatDTO.setBalanceAmount(rowList.get(3).toString());
				System.out.println("*******");
				objCaveatDTO.setBalanceAmount(rowList.get(1).toString());
				
				
				*/
				
				partialListFinal.add(objCaveatDTO);
		
			}
				
			
				
			}
	        
	        catch(Exception e){
	        	e.printStackTrace();
	        	
	        }
		}
		return partialListFinal;
	}
	
	
	
	
	
	public ArrayList getPartial1(String userId) throws Exception
	{
		
		
		CaveatsDelegate objCaveatBD = new CaveatsDelegate();
		ArrayList partialListFinal = new ArrayList();
		ArrayList list=objCaveatBD.getPartial(userId);
		
		if(list!=null && list.size()>0){
			
			ArrayList rowList;
			
	        try{
			for (int i = 0; i < list.size(); i++) {
				
				CaveatsDTO objCaveatDTO = new CaveatsDTO();
				
				rowList = (ArrayList)list.get(i);
				
				
				objCaveatDTO.setReferenceID(rowList.get(0).toString());
				
				objCaveatDTO.setBalanceAmount(rowList.get(1).toString());
				
			/*		
				if ((rowList.get(1))==null)
				{
					
					objCaveatDTO.setBalanceAmount("-");
				}
					
				
				else
				{	//objCaveatDTO.setBalanceAmount(rowList.get(3).toString());
				System.out.println("*******");
				objCaveatDTO.setBalanceAmount(rowList.get(1).toString());
				
				
				*/
				
				partialListFinal.add(objCaveatDTO);
		
			}
				
			
				
			}
	        
	        catch(Exception e){
	        	e.printStackTrace();
	        	
	        }
		}
		return partialListFinal;
	}

///
	
	
	public String setPaymentDetails(CaveatsDTO dto,String funId,String userId)
	{
		String paymentId="";
		try {
			CaveatsDAO  dao = new CaveatsDAO();
			ArrayList list=dao.isPaymentEntry(dto.getTransactionID().toString());
			if(list.size()>0)
			{	
			ArrayList rowlist=(ArrayList) list.get(0);
			if(rowlist.get(0).toString().equalsIgnoreCase("I"))
				{
					return rowlist.get(1).toString();
				}
				else
				{
					return dao.setPaymentDtls(dto, funId, userId);
				}
			}
			else
			{
			return dao.setPaymentDtls(dto, funId, userId);
			}
			} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return paymentId;
	}
	
	public String getDistrictName(String officeId,String language){
		
		CaveatsDAO  dao = new CaveatsDAO();
		String districtName=null;
		
		try {
			
			districtName=dao.getDistrictName(officeId, language);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return districtName;
		
	}
	
public ArrayList<CaveatsDTO> getTehsilList(CaveatsDTO caveatsDTO){
		
		CaveatsDAO  dao = new CaveatsDAO();
		ArrayList<CaveatsDTO> tehsilList  = null;
		
		try {
			
			tehsilList = dao.getTehsil(caveatsDTO);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return tehsilList;
		
	}

public ArrayList<CaveatsDTO> getAreaList(CaveatsDTO caveatsDTO){
	
	CaveatsDAO  dao = new CaveatsDAO();
	ArrayList<CaveatsDTO> areaList  = null;
	
	try {
		
		areaList = dao.getArea(caveatsDTO);
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return areaList;
	
}

public ArrayList<CaveatsDTO> getSubAreaList(CaveatsDTO caveatsDTO){
	
	CaveatsDAO  dao = new CaveatsDAO();
	ArrayList<CaveatsDTO> subAreaList  = null;
	
	try {
		
		subAreaList = dao.getSubAreaList(caveatsDTO);
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return subAreaList;
	
}

public ArrayList<CaveatsDTO> getWardList(CaveatsDTO caveatsDTO){
	
	CaveatsDAO  dao = new CaveatsDAO();
	ArrayList<CaveatsDTO> wardPatwariList  = new ArrayList<CaveatsDTO>();
	
	try {
		
		wardPatwariList = dao.getWardPatwari(caveatsDTO);
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return wardPatwariList;
	
}
public ArrayList<CaveatsDTO> getColonyList(CaveatsDTO caveatsDTO){
	
	CaveatsDAO  dao = new CaveatsDAO();
	ArrayList<CaveatsDTO> colonyList  = new ArrayList<CaveatsDTO>();
	
	try {
		
		colonyList = dao.getColonyList(caveatsDTO);
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return colonyList;
	
}

/*public ArrayList<CaveatsDTO> getPropertyTypeList(CaveatsDTO caveatsDTO){
	
	CaveatsDAO  dao = new CaveatsDAO();
	ArrayList<CaveatsDTO> propertyTypeList  = new ArrayList<CaveatsDTO>();
	
	try {
		
		propertyTypeList = dao.getPropertyTypeList(caveatsDTO);
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return propertyTypeList;
	
}*/
/**
 * @param registrationNumber
 * @return
 */
public ArrayList<CaveatsDTO> fetchPropertyTxnIDList(String registrationNumber,String lang) {
	ArrayList<CaveatsDTO> retVal = new ArrayList<CaveatsDTO>();
	try {
		CaveatsDAO refDAO = new CaveatsDAO();
		retVal = refDAO.fetchPropertyTxnID(registrationNumber,lang);
	} catch (Exception e) {
		logger.error(e.getMessage(),e);
	}
	return retVal;
}

public String fetchServiceFeeDetails(String funId) {
	String retVal = null;
	try {
		CaveatsDAO refDAO = new CaveatsDAO();
		retVal = refDAO.fetchServiceFeeDetls(funId);
	} catch (Exception e) {
		logger.error(e.getMessage(),e);
	}
	return retVal;
}

public String fetchRegTxnId(String regNo) {
	String retVal = null;
	try {
		CaveatsDAO refDAO = new CaveatsDAO();
		retVal = refDAO.getReginitId(regNo);
	} catch (Exception e) {
		logger.error(e.getMessage(),e);
	}
	return retVal;
}

public boolean updateRegProtestDetls(CaveatsDTO caveatsDTO){
	
	boolean retVal = false;
	try {
		CaveatsDAO refDAO = new CaveatsDAO();
		retVal = refDAO.updateRegProtestDetls(caveatsDTO);
	} catch (Exception e) {
		logger.error(e.getMessage(),e);
	}
	return retVal;
}
public boolean updatePropertyProtestDetls(CaveatsDTO dtoRelease,String protestRelId){
	
	boolean retVal = false;
	try {
		CaveatsDAO refDAO = new CaveatsDAO();
		retVal = refDAO.updatePropertyProtestDetls(dtoRelease,protestRelId);
	} catch (Exception e) {
		logger.error(e.getMessage(),e);
	}
	return retVal;
}
public boolean insertProtestDetls(CaveatsForm caveatsFrm){
	
	boolean retVal = false;
	try {
		CaveatsDAO refDAO = new CaveatsDAO();
		retVal = refDAO.insertProtestDetls(caveatsFrm);
	} catch (Exception e) {
		logger.error(e.getMessage(),e);
	}
	return retVal;
}

public String validateRegNo(CaveatsDTO caveatsDTO){
	
	String list = null;
	try {
		CaveatsDAO refDAO = new CaveatsDAO();
		list = refDAO.validateRegNo(caveatsDTO);
	} catch (Exception e) {
		logger.error(e.getMessage(),e);
	}
	return list;
}
public ArrayList<CaveatsDTO> getProtestId(CaveatsDTO caveatsDTO, String distName, String distNameHin){
	
	String list = null;
	ArrayList<CaveatsDTO> protestList = new ArrayList<CaveatsDTO>();
	try {
		CaveatsDAO refDAO = new CaveatsDAO();
		protestList = refDAO.getProtestId(caveatsDTO,distName,distNameHin);
	} catch (Exception e) {
		logger.error(e.getMessage(),e);
	}
	return protestList;
}

public ArrayList getProtestDetls(CaveatsDTO caveatsDTO){
	
	ArrayList list = null;
	try {
		CaveatsDAO refDAO = new CaveatsDAO();
		list = refDAO.getProtestDetails(caveatsDTO);
	} catch (Exception e) {
		logger.error(e.getMessage(),e);
	}
	return list;
}

public ArrayList getPaymentDetails(CaveatsDTO caveatsDTO) throws Exception{
	
	ArrayList list = null;
	
		logger.debug("from getPaymentDetails function of protest BO file");
		CaveatsDAO refDAO = new CaveatsDAO();
		list = refDAO.getPaymentDetails(caveatsDTO);
	
	return list;
}
public boolean InsertProtestRelDetls(CaveatsForm caveatsForm){
	
	boolean flag = false;
	try {
		CaveatsDAO refDAO = new CaveatsDAO();
		flag = refDAO.insertProtestReleaseDetls(caveatsForm);
	} catch (Exception e) {
		logger.error(e.getMessage(),e);
	}
	return flag;
}
public ArrayList<CaveatsDTO> getProtestSearchData(CaveatsDTO caveatsDTO,String distName,String distNameHi) throws Exception{
	
	String list = null;
	ArrayList<CaveatsDTO> protestList = new ArrayList<CaveatsDTO>();
	
		CaveatsDAO refDAO = new CaveatsDAO();
		protestList = refDAO.getProtestSearchData(caveatsDTO,distName,distNameHi);
	
	return protestList;
}

public ArrayList<CaveatsDTO> getProtestReportData(CaveatsDTO caveatsDTO, String districtName,String distHinName){
	
	String list = null;
	ArrayList<CaveatsDTO> protestList = new ArrayList<CaveatsDTO>();
	try {
		CaveatsDAO refDAO = new CaveatsDAO();
		protestList = refDAO.getProtestReportData(caveatsDTO,districtName,distHinName);
	} catch (Exception e) {
		logger.error(e.getMessage(),e);
	}
	return protestList;
}

public String validateProtestRelStatus(CaveatsDTO caveatsDTO){
	
	String list = null;
	
	try {
		CaveatsDAO refDAO = new CaveatsDAO();
		list = refDAO.validateProtestRelStatus(caveatsDTO);
	} catch (Exception e) {
		logger.error(e.getMessage(),e);
	}
	return list;
}
public boolean updateProtestStatus(CaveatsDTO caveatsDTO){
	
	boolean flag = false;
	
	try {
		CaveatsDAO refDAO = new CaveatsDAO();
		flag = refDAO.updateProtestStatus(caveatsDTO);
	} catch (Exception e) {
		logger.error(e.getMessage(),e);
	}
	return flag;
}

public boolean updateProtestLogStatus(CaveatsDTO caveatsDTO){
	
	boolean flag = false;
	
	try {
		CaveatsDAO refDAO = new CaveatsDAO();
		flag = refDAO.updateProtestLogStatus(caveatsDTO);
	} catch (Exception e) {
		logger.error(e.getMessage(),e);
	}
	return flag;
}

public ArrayList getProtestDashboardDetls(CaveatsDTO caveatsDTO){
	
	ArrayList list = null;
	try {
		CaveatsDAO refDAO = new CaveatsDAO();
		list = refDAO.getProtestDashboardDetails(caveatsDTO);
	} catch (Exception e) {
		logger.error(e.getMessage(),e);
	}
	return list;
}

public String getUserTypeId(String userId){
	String userTypeId = null;
	try {
		CaveatsDAO refDAO = new CaveatsDAO();
		userTypeId = refDAO.getUserTypeId(userId);
	} catch (Exception e) {
		logger.error(e.getMessage(),e);
	}
	return userTypeId;
}

public boolean insertPaymentDetls(CaveatsForm cfm, String paymentType){
	boolean status = false;
	try {
		CaveatsDAO refDAO = new CaveatsDAO();
		status = refDAO.insertPaymentDetails(cfm, paymentType);
	} catch (Exception e) {
		logger.error(e.getMessage(),e);
	}
	return status;
}

public String getPaymentTxnIdSeq(){
	String seqId = null;
	try {
		CaveatsDAO refDAO = new CaveatsDAO();
		seqId = refDAO.getPaymentTxnIdSeq();
	} catch (Exception e) {
		logger.error(e.getMessage(),e);
	}
	return seqId;
}
public ArrayList<CaveatsDTO> fetchPaymentDetails(CaveatsDTO caveatsDTO) throws Exception {
	
	String list = null;
	ArrayList<CaveatsDTO> protestList = new ArrayList<CaveatsDTO>();
	
		logger.debug("from fetchPaymentDetails function of protest BO file");
		CaveatsDAO refDAO = new CaveatsDAO();
		protestList = refDAO.fetchPaymentDetails(caveatsDTO);
	
	return protestList;
}

public String fetchChallanPaySeqId(String paySeqId){
	String paymentTxnId = null;
	try {
		CaveatsDAO refDAO = new CaveatsDAO();
		paymentTxnId = refDAO.fetchChallanPaySeqId(paySeqId);
	} catch (Exception e) {
		logger.error(e.getMessage(),e);
	}
	return paymentTxnId;
}

public String getOfficeType(String officeId) {

	String officeType = null;
	CaveatsDAO refDAO = new CaveatsDAO();
	officeType = refDAO.getOfficeType(officeId);

	return officeType;

}

public String getDIGZoneBo(String officeId) {
	CaveatsDAO refDAO = new CaveatsDAO();
	return refDAO.getDIGZoneDAO(officeId);
}

public ArrayList getDistDIGListBO(String zoneId,String language) {
	CaveatsDAO refDAO = new CaveatsDAO();
	
	ArrayList list = new ArrayList();
	ArrayList<CaveatsDTO> list2 = new ArrayList<CaveatsDTO>();
	list = refDAO.getDistDIGListDAO(zoneId,language);

	if (list != null) {
		for (int i = 0; i < list.size(); i++) {
			ArrayList list3 = (ArrayList) list.get(i);
			CaveatsDTO caveatsDTO = new CaveatsDTO();
			if(null!=(String) list3.get(0) && !"".equalsIgnoreCase((String) list3.get(0)))
			 caveatsDTO.setDistrictName((String) list3.get(0));
			if(null!=(String) list3.get(1) && !"".equalsIgnoreCase((String) list3.get(1)))
			caveatsDTO.setDistrictId((String) list3.get(1));
			list2.add(caveatsDTO);
		}
		logger.info("SpotInspBD----getState  " + list);
	}
	
	return list2;
}

public ArrayList getDistrictDetailsMIS(String language, String offcID) {
	CaveatsDAO refDAO = new CaveatsDAO();
	ArrayList returnList = new ArrayList();
	try {
		// IGRSCommon common = new IGRSCommon();
		ArrayList resultList = refDAO.getDistrict("1", offcID);
		if (resultList != null) {
			for (int i = 0; i < resultList.size(); i++) {
				logger.debug("in bd for loop start:-" + resultList.get(i));
				ArrayList list = (ArrayList) resultList.get(i);
				CaveatsDTO caveatsDTO = new CaveatsDTO();
				caveatsDTO.setDistrictId((String) list.get(0));
				if ("en".equalsIgnoreCase(language))
					caveatsDTO.setDistrictName((String) list.get(1));
				else
					caveatsDTO.setDistrictName((String) list.get(2));
				returnList.add(caveatsDTO);
				logger.debug(caveatsDTO.getDistrictId() + ":"
						+ caveatsDTO.getDistrictName());
			}
		}
	} catch (Exception err) {

		logger.debug("In getDistrictDetails Exception " + err);
	}
	return returnList;
}

public String burnRequestIdForPDF(String requestId,String inputFilePath, String folderPath)
{    String docName = "";
	String finalPath = "";
    int centerOfPage = 0;
    try
    {
        int pageWidth = (int)PageSize.A4.getWidth();
        int pageHeight = (int)PageSize.A4.getHeight();
        String inputfile = (new StringBuilder(String.valueOf(inputFilePath))).append(File.separatorChar).append(docName).toString();
        PdfReader reader1 = new PdfReader(inputfile);
        int noOfPages1 = reader1.getNumberOfPages();
        PdfStamper stamper = new PdfStamper(reader1, new FileOutputStream((new StringBuilder(String.valueOf(folderPath))).append(File.separator).append(requestId).append(".pdf").toString()));
        finalPath=new StringBuilder(String.valueOf(folderPath)).append(File.separator).append(requestId).append(".pdf").toString();
        centerOfPage = pageWidth / 2;
        for(int i = 1; i <= noOfPages1; i++)
        {
            PdfContentByte cbf = stamper.getOverContent(i);
            Graphics2D g2 = cbf.createGraphicsShapes(pageWidth, pageHeight);
           // Font font = new Font("Mangal", 0, 8);
          //  g2.setFont(font);
          //  g2.setColor(Color.BLACK);
            drawString(g2, requestId, 250, pageHeight - (int)(2D * (double)pageHeight) / 100 - 20, (40 * pageWidth) / 100);
          //  font = new Font("Mangal", 0, 8);
          //  g2.setFont(font);
            g2.dispose();
            g2 = null;
        }
        

        stamper.close();
        reader1.close();
    }
    catch(IOException ex)
    {
        logger.error("DocumentOperations burnRequestIdForPDF method, IOException caught.", ex);
    }
    catch(Throwable ex)
    {
        logger.error("DocumentOperations burnRequestIdForPDF method, Exception caught.", ex);
    }
    return finalPath;
}

public int drawString(Graphics g, String s, int x, int y, int width)
{
    FontMetrics fm = g.getFontMetrics();
    int lineHeight = fm.getHeight();
    boolean underlined = false;
    int curX = x;
    int curY = y;
    if(s != null)
    {
        String words[] = s.split(" ");
        String as[];
        int j = (as = words).length;
        for(int i = 0; i < j; i++)
        {
            String word = as[i];
            int wordWidth = fm.stringWidth((new StringBuilder(String.valueOf(word))).append(" ").toString());
            if(word.equalsIgnoreCase("<ULine>"))
                underlined = true;
            else
            if(word.equalsIgnoreCase("</ULine>"))
            {
                underlined = false;
            } else
            {
                if(curX + wordWidth >= x + width)
                {
                    curY += lineHeight;
                    curX = x;
                } else
                if(word.equalsIgnoreCase("\n"))
                {
                    curY += lineHeight;
                    curX = x;
                }
                if(underlined)
                {
                    g.drawString(word, curX, curY);
                    g.drawLine(curX, curY + 2, curX + wordWidth, curY + 2);
                } else
                {
                    g.drawString(word, curX, curY);
                }
                curX += wordWidth;
            }
        }

    }
    return curY;
}

public String getDistrictDetls(String distId,String language){
	
	CaveatsDAO  dao = new CaveatsDAO();
	String districtName=null;
	
	try {
		
		districtName=dao.getDistrictDetls(distId, language);
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return districtName;
	
}

public String getDistrictValues(String distId,String language){
	
	CaveatsDAO  dao = new CaveatsDAO();
	String districtName=null;
	
	try {
		
		districtName=dao.getDistrictValues(distId, language);
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return districtName;
	
}

public String getDistrictIdVal(String distId,String language){
	
	CaveatsDAO  dao = new CaveatsDAO();
	String districtid=null;
	
	try {
		
		districtid=dao.getDistrictIdVal(distId, language);
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return districtid;
	
}

public String validateRelDistrict(String protestId){

String list = null;

try {
	CaveatsDAO refDAO = new CaveatsDAO();
	list = refDAO.validateRelDistrict(protestId);
} catch (Exception e) {
	logger.error(e.getMessage(),e);
}
return list;
}

/*public ArrayList<CaveatsDTO> fetchRegIdSearchResult(CaveatsDTO caveatsDTO){
	
	CaveatsDAO  dao = new CaveatsDAO();
	ArrayList<CaveatsDTO> propertyTypeList  = new ArrayList<CaveatsDTO>();
	
	try {
		
		propertyTypeList = dao.searchRegIdDao(caveatsDTO.getRegistrationNumber());
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return propertyTypeList;
	
}*/
	
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
    
