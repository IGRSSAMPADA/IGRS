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
package com.wipro.igrs.empmgmt.bd;

import java.util.ArrayList;

import com.wipro.igrs.empmgmt.dao.OfficalInfoDAO;
import com.wipro.igrs.empmgmt.dto.DropDownDTO;
import com.wipro.igrs.empmgmt.dto.EmpmgmtUploadDTO;
import com.wipro.igrs.empmgmt.dto.OfficalInfoDTO;
import com.wipro.igrs.empmgmt.dto.ServiceVerificationDTO;

/**
* 
* OfficalInfoBD.java <br>
* OfficalInfoBD <br>
* 
* @version 1.0
* 
* @author <b>WIPRO JAVA TEAM</b> <br>
*         Created on 12-Apr-2008 <br>
*         Last Modified on 28-Mar-2013
*/
@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
public class OfficalInfoBD {

	OfficalInfoDAO oDAO = null;

	/**
	 * @throws Exception
	 */
	public OfficalInfoBD() throws Exception {
		oDAO = new OfficalInfoDAO();
	}

	/**
	 * @param servicelist
	 * @param strUserId
	 * @param strempId
	 * @return
	 * @throws Exception
	 */
	public boolean submitService(ArrayList servicelist, String strUserId,String strempId)
			throws Exception {

		return oDAO.submitService(servicelist, strUserId,strempId);
	}

	/**
	 * @param officalInfoDTO
	 * @param servicelist
	 * @param strUserId
	 * @param strempId
	 * @param listFileNames
	 * @param strFilePath
	 * @param docType
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	public boolean submitOfficalInfo(OfficalInfoDTO officalInfoDTO,ArrayList servicelist,
			String strUserId,String strempId,ArrayList listFileNames,String strFilePath,String docType,String userid,String locale) throws Exception {
		return oDAO.insertOfficalInfo(officalInfoDTO, servicelist,strUserId,strempId,listFileNames,strFilePath,docType,userid,locale);

	}

	/**
	 * @param officalInfoDTO
	 * @param strUserId
	 * @param strempId
	 * @return
	 * @throws Exception
	 */
	public boolean insertMapping(OfficalInfoDTO officalInfoDTO, String strUserId,String strempId)
			throws Exception {
		return oDAO.insertMapping(officalInfoDTO, strUserId,strempId);
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public ArrayList<DropDownDTO> getAllgradeList(String locale) throws Exception {
		ArrayList data, row;
		DropDownDTO entry;
		ArrayList<DropDownDTO> gradeMasterList = new ArrayList<DropDownDTO>();
		data = oDAO.getAllgradeList();
		for (Object item : data) {
			row = (ArrayList) item;
			entry = new DropDownDTO();
			entry.setOptionValue(row.get(0).toString());
			if(locale.equalsIgnoreCase("hi_IN")){
				entry.setOptionLabel(row.get(2).toString());
	
			}else{
				entry.setOptionLabel(row.get(1).toString());

			}

			gradeMasterList.add(entry);
		}
//		for (int i = 0; i < data.size(); i++) {
//			row = (ArrayList) data.get(i);
//			if (row != null) {
//				OfficalInfoDTO officalInfoDTO = new OfficalInfoDTO();
//				officalInfoDTO.setGradeid(row.get(0).toString());
//				officalInfoDTO.setClass1(row.get(1).toString());
//				_gradelist.add(officalInfoDTO);
//			}
//		}
		return gradeMasterList;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public ArrayList getAllDocumentList(String locale) throws Exception {
		ArrayList documentlist = oDAO.getAllDocumentList();
		ArrayList _documentlist = new ArrayList();
		for (int i = 0; i < documentlist.size(); i++) {
			ArrayList _document = (ArrayList) documentlist.get(i);

			

			if (documentlist != null) {

				ServiceVerificationDTO verificationDTO = new ServiceVerificationDTO();
				verificationDTO.setDocumentid((String) _document.get(0));
				if(locale.equalsIgnoreCase("hi_IN")){
					verificationDTO.setDocumentType((String) _document.get(2));
			
				}else{
					verificationDTO.setDocumentType((String) _document.get(1));
				
				}

				_documentlist.add(verificationDTO);

			}
		}
		return _documentlist;
	}

	/**
	 * @param gradeId
	 * @return
	 * @throws Exception
	 */
	public ArrayList getCadresForGrade(String gradeId,String locale) throws Exception {
		ArrayList data, row;
		DropDownDTO entry;
		ArrayList<DropDownDTO> cadreMapList = new ArrayList<DropDownDTO>();
		data = oDAO.getCadresForGrade(gradeId);
		for (Object item : data) {
			row = (ArrayList) item;
			entry = new DropDownDTO();
			entry.setOptionValue(row.get(0).toString());
			if(locale.equalsIgnoreCase("hi_IN")){
				entry.setOptionLabel(row.get(2).toString());
				
			}else{
				entry.setOptionLabel(row.get(1).toString());

			}

			cadreMapList.add(entry);
		}
		return cadreMapList;
//		ArrayList _cadrelist = new ArrayList();
//		for (int i = 0; i < data.size(); i++) {
//			ArrayList _cadres = (ArrayList) data.get(i);
//
//
//			if (data != null) {
//
//				OfficalInfoDTO officalInfoDTO = new OfficalInfoDTO();
//				officalInfoDTO.setCadreid((String) _cadres.get(0));
//				officalInfoDTO.setDesignation((String) _cadres.get(1));
//
//				_cadrelist.add(officalInfoDTO);
//
//			}
//		}
//		return _cadrelist;

	}

	/**
	 * @param officating
	 * @return
	 * @throws Exception
	 */
	public ArrayList getAllOfficating(String officating) throws Exception {
		ArrayList gradetypes = oDAO.getAllOfficating(officating);
	
		ArrayList _cadrelist = new ArrayList();
		for (int i = 0; i < gradetypes.size(); i++) {
			ArrayList _cadres = (ArrayList) gradetypes.get(i);

			if (gradetypes != null) {

				OfficalInfoDTO officalInfoDTO = new OfficalInfoDTO();

				officalInfoDTO.setDesignation((String) _cadres.get(0));
				officalInfoDTO.setOfficiatingid((String) _cadres.get(1));
				officalInfoDTO.setDesignationid((String) _cadres.get(1));
				_cadrelist.add(officalInfoDTO);

			}
		}
		return _cadrelist;

	}

	/**
	 * @param empid
	 * @return
	 * @throws Exception
	 */
	public OfficalInfoDTO getRepoteeList(String empid) throws Exception {

		ArrayList reportingHirachy = oDAO.getRepoteeList(empid);
		ArrayList hirachylist = new ArrayList();
		OfficalInfoDTO officalInfoDTO =null;
			try {
				ArrayList hirachy = (ArrayList) reportingHirachy.get(0);
				// ArrayList supervisorID=new ArrayList();
				if (reportingHirachy != null) {
					officalInfoDTO = new OfficalInfoDTO();

					officalInfoDTO.setDesignation((String) hirachy.get(0));
					//officalInfoDTO.setSupervisorID(empid);
					officalInfoDTO.setSupervisorName(empid);

				}
			} catch (Exception e) {
			}
			return officalInfoDTO ;

	}

	/**
	 * @param empid
	 * @return
	 * @throws Exception
	 */
	public ArrayList getReportingHirachy(String empid) throws Exception {
		ArrayList reportingHirachy = oDAO.getReportingHirachy(empid);
		ArrayList hirachylist = new ArrayList();
		for (int i = 0; i < reportingHirachy.size(); i++) {
			ArrayList hirachy = (ArrayList) reportingHirachy.get(i);
			// ArrayList supervisorID=new ArrayList();
			if (reportingHirachy != null) {
				OfficalInfoDTO officalInfoDTO = new OfficalInfoDTO();

				officalInfoDTO.setSupervisorID((String) hirachy.get(0));
				officalInfoDTO.setSupervisorName((String) hirachy.get(1));
				hirachylist.add(officalInfoDTO);
			}

		}
		return hirachylist;
	}

	
	/**
	 * @param empId
	 * @return
	 */
	public ArrayList getDocumentDetails(String empId,String locale) {

		ArrayList documentList = oDAO.getDocumentDetails(empId);
		ArrayList documentlist = new ArrayList();

		for (int i = 0; i < documentList.size(); i++) {
			ArrayList document = (ArrayList) documentList.get(i);
			if (documentList != null) {
				EmpmgmtUploadDTO officeDTO = new EmpmgmtUploadDTO();
				officeDTO.setDocID((String) document.get(0));
				
				if(locale.equalsIgnoreCase("hi_IN")){
					officeDTO.setDocumenttype((String) document.get(3));
					officeDTO.setDocTypeLabel((String) document.get(3));
				}else{
					officeDTO.setDocumenttype((String) document.get(1));
					officeDTO.setDocTypeLabel((String) document.get(1));
				}
				officeDTO.setDocumentname((String) document.get(2));
				officeDTO.setFileName((String) document.get(2));
				officeDTO.setFileContents((byte[]) document.get(4));
				officeDTO.setFileId(documentlist.size()+1);
				documentlist.add(officeDTO);
			}

		}
		return documentlist;
	}

	/**
	 * @return
	 */
	public ArrayList<DropDownDTO> getOfficatingSubstantingList(String locale) {
		ArrayList<DropDownDTO> retVal = new ArrayList<DropDownDTO>();
		try {
			ArrayList data, row;
			DropDownDTO entry;
			data = oDAO.getOfficatingSubstantingList();
			for (Object item : data) {
				row = (ArrayList) item;
				entry = new DropDownDTO();
				entry.setOptionValue(row.get(0).toString());
				if(locale.equalsIgnoreCase("hi_IN")){
					entry.setOptionLabel(row.get(2).toString());
					
				}else{
					entry.setOptionLabel(row.get(1).toString());

				}

				retVal.add(entry);
			}
		} catch (Exception e) {
		}
		return retVal ;
	}

	/**
	 * @return
	 */
	public ArrayList<DropDownDTO> getEmpStatusMasterList(String locale) {
		ArrayList<DropDownDTO> retVal = new ArrayList<DropDownDTO>();
		DropDownDTO entry;
	if(locale.equalsIgnoreCase("hi_IN")){
		entry = new DropDownDTO();
		entry.setOptionLabel("परिवीक्षा");
		entry.setOptionValue("परिवीक्षा");
		retVal.add(entry);
		entry = new DropDownDTO();
		entry.setOptionLabel("अस्थायी");
		entry.setOptionValue("अस्थायी");
		retVal.add(entry);
		entry = new DropDownDTO();
		entry.setOptionLabel("पुष्टीकृत");
		entry.setOptionValue("पुष्टीकृत");
		retVal.add(entry);
		
		}else{
		entry = new DropDownDTO();
		entry.setOptionLabel("Probation");
		entry.setOptionValue("Probation");
		retVal.add(entry);
		entry = new DropDownDTO();
		entry.setOptionLabel("Temporary");
		entry.setOptionValue("Temporary");
		retVal.add(entry);
		entry = new DropDownDTO();
		entry.setOptionLabel("Confirmed");
		entry.setOptionValue("Confirmed");
		retVal.add(entry);
		}

		return retVal ;
	}

	/**
	 * @return
	 */
	public ArrayList<DropDownDTO> getAllCadres(String locale) {
		ArrayList<DropDownDTO> retVal = new ArrayList<DropDownDTO>();
		try {
			ArrayList data, row;
			DropDownDTO entry;
			data = oDAO.getAllCadres();
			for (Object item : data) {
				row = (ArrayList) item;
				entry = new DropDownDTO();
				entry.setOptionValue(row.get(0).toString());
				if(locale.equalsIgnoreCase("hi_IN")){
					entry.setOptionLabel(row.get(2).toString());

				}else{
					entry.setOptionLabel(row.get(1).toString());

				}

					
				retVal.add(entry);
			}
		} catch (Exception e) {
		}
		return retVal ;
	}

	/**
	 * @param attachedDocLabels
	 * @return
	 */
	public ArrayList<String> getMissingDocLabels(
			ArrayList<String> attachedDocLabels) {
		ArrayList<String> retVal = new ArrayList<String>();
		try {
			ArrayList data, row;
			data = oDAO.getMissingDocLabels(attachedDocLabels);
			for (Object item : data) {
				row = (ArrayList) item;
				retVal.add(row.get(0).toString());
			}
		} catch (Exception e) {
		}
		return retVal ;
	}
}