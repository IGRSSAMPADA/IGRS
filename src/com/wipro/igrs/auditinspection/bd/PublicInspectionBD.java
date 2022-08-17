/*
* Copyright Notice
*==============================================================================
* This file contains proprietary information of IGRS Bhopal Madhya Pradesh .
* Copying or reproduction without prior written approval is prohibited.
* Copyright (c) 2007-08
*==============================================================================
*
* File Name   :   
 * Author      :   
 * Description :   
 * ----------------------------------------------------------------------------
* Version         Modified By     Modified On           Modification
* ----------------------------------------------------------------------------
* 1.0             SHRUTI          xxxxx              
 * --------------------------------------------------------------------------------
*/

package com.wipro.igrs.auditinspection.bd;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.auditinspection.dao.AGMPReportDetailsDAO;
import com.wipro.igrs.auditinspection.dao.PublicInspectionDAO;
import com.wipro.igrs.auditinspection.dto.AGMPReportDetailsDTO;
import com.wipro.igrs.auditinspection.dto.DROReportDetailsDTO;
import com.wipro.igrs.auditinspection.dto.POIAddObjectionDTO;
import com.wipro.igrs.auditinspection.dto.PartyDetailsDTO;
import com.wipro.igrs.auditinspection.dto.PropertyDetailsDTO;
import com.wipro.igrs.auditinspection.dto.PublicDTO;
import com.wipro.igrs.auditinspection.dto.PublicInspectionDTO;
import com.wipro.igrs.auditinspection.dto.RegIdOtherSearch;
import com.wipro.igrs.auditinspection.dto.UploadFileDTO;
import com.wipro.igrs.login.action.LoginAction;
import com.wipro.igrs.util.CommonUtil;

public class PublicInspectionBD {

	private PublicInspectionDAO publicInspectionDAO = new PublicInspectionDAO();
	private AGMPReportDetailsDAO agmpdao = new AGMPReportDetailsDAO();
	private Logger logger = (Logger) Logger.getLogger(PublicInspectionBD.class);

	public ArrayList getOffice(String userId) {

		ArrayList countlist = publicInspectionDAO.getOffice(userId);

		ArrayList cntList = new ArrayList();
		if (countlist != null) {
			for (int i = 0; i < countlist.size(); i++) {
				ArrayList cnttlist = (ArrayList) countlist.get(i);
				if (cnttlist != null) {
					PublicDTO cntdto = new PublicDTO();
					cntdto.setOfficeId((String) cnttlist.get(0));
					cntdto.setOfficeName((String) cnttlist.get(1));
					cntList.add(cntdto);
				}
			}
		}
		return cntList;
	}

	public String getOfficeName(String office) {

		String officeName = publicInspectionDAO.getOfficeName(office);

		return officeName;
	}

	public ArrayList getDistrict(String office,String language) {

		ArrayList countlist = publicInspectionDAO.getDistrict(office,language);

		ArrayList cntList = new ArrayList();
		if (countlist != null) {
			for (int i = 0; i < countlist.size(); i++) {
				ArrayList cnttlist = (ArrayList) countlist.get(i);
				if (cnttlist != null) {
					PublicDTO cntdto = new PublicDTO();
					cntdto.setDistrictId((String) cnttlist.get(0));
					cntdto.setDistrictName((String) cnttlist.get(1));
					cntList.add(cntdto);
				}
			}
		}
		return cntList;
	}

	public ArrayList addToSession(ArrayList pubList) {
		ArrayList list = new ArrayList();

		for (int i = 0; i < pubList.size(); i++) {
			PublicDTO poireportDTO = (PublicDTO) pubList.get(i);

			String sqlValues[] = { poireportDTO.getAddress(),
					poireportDTO.getDistrict(), poireportDTO.getInsDate(),
					poireportDTO.getInsEntryDate(), poireportDTO.getToDate(),
					poireportDTO.getFromDate(), poireportDTO.getComments(),
			// ""+poireportDTO.getAuditReport(),
			// ""+poireportDTO.getOthers()

			};
			list.add(sqlValues);
		}
		return list;

	}

	/*
	 * public boolean insertPublic(PublicDTO publicInspectionDTO,POIreportDTO
	 * poireportDTO,POIreportDTO1 poireportDTO1)throws Exception { return
	 * publicInspectionDAO.insertPublic(publicInspectionDTO,poireportDTO,poireportDTO1); }
	 */
	/**
	 * @param publicDTO
	 * @return
	 */
	public ArrayList getExistingReports(PublicDTO publicDTO,String language) {
		String[] arr = new String[2];
		ArrayList listExisting = new ArrayList();
		arr[0] = CommonUtil.getConvertedDate(publicDTO.getFromDate());
		arr[1] = CommonUtil.getConvertedDate(publicDTO.getToDate());
		ArrayList reportList = publicInspectionDAO.getExistingReports(arr);
		for (int i = 0; i < reportList.size(); i++) {
			ArrayList listTemp = (ArrayList) reportList.get(i);

			PublicInspectionDTO newDTO = new PublicInspectionDTO();
			newDTO.setPubInspectionId((String) listTemp.get(0));
			newDTO.setPubInspDate((String) listTemp.get(1));
			newDTO.setPubInspDispatchDate((String) listTemp.get(2));

			if (((String) listTemp.get(3)).equalsIgnoreCase("O")) {
				if("en".equalsIgnoreCase(language))
				{
				newDTO.setPubInspReportStatus("Open");
				}
				else
				{
					newDTO.setPubInspReportStatus("ओपन");
				}
			} else
				if("en".equalsIgnoreCase(language)){
				newDTO.setPubInspReportStatus("Pending");}
				else
				{
					newDTO.setPubInspReportStatus("लंबित");
				}

			listExisting.add(newDTO);
		}

		return listExisting;
	}

	public ArrayList getExistingPOIDetails(String poiId) {
		ArrayList list = null;
		String[] arr = new String[1];
		arr[0] = poiId;
		list = publicInspectionDAO.getExistingPOIDetails(arr);
		return list;
	}

	public ArrayList getRegDetails(String searchId) {
		ArrayList list = new ArrayList();

		try {

			String search[] = new String[1];
			search[0] = searchId;
			ArrayList searchlist = agmpdao.getRegidInfo(search);
			for (int i = 0; i < searchlist.size(); i++) {
				ArrayList tempList = (ArrayList) searchlist.get(i);
				PublicDTO myDTO = new PublicDTO();
				myDTO.setRegId((String) tempList.get(0));
				myDTO.setCreatedDate((String) tempList.get(1));
				list.add(myDTO);
			}
		} catch (Exception e) {
			logger.error("error in RegId");
		}
		return list;
	}

	public RegIdOtherSearch getRegIdDetails(String searchId,String userId) {
		RegIdOtherSearch regidother = new RegIdOtherSearch();
		//MODIFIED BY SHRUTI---18TH OCT 2013
		/*String regid[] = new String[2];
		regid[0] = searchId;
		regid[1]=userId;
*/
		
		String regid[] = new String[1];
		regid[0] = searchId;

		ArrayList searchOther = new ArrayList();
		try {

			searchOther = agmpdao.getRegDetails(regid);
			for (int i = 0; i < searchOther.size(); i++) {
				ArrayList row_list = (ArrayList) searchOther.get(i);
				regidother.setFilling_id((String) row_list.get(0));
				regidother.setFilling_date((String) row_list.get(1));
				regidother.setSrName((String) row_list.get(2));
				regidother.setStamp_details((String) row_list.get(3));
				regidother.setOther_fees((String) row_list.get(4));
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return regidother;
	}

	public RegIdOtherSearch getRegIdOtherDetails(String searchId) {
		RegIdOtherSearch regidother = new RegIdOtherSearch();
		String regid[] = new String[1];
		regid[0] = searchId;

		ArrayList searchOther = new ArrayList();
		try {

			searchOther = agmpdao.getRegOtherDetails(regid);
			for (int i = 0; i < searchOther.size(); i++) {
				ArrayList row_list = (ArrayList) searchOther.get(i);
				regidother.setFilling_id((String) row_list.get(0));
				regidother.setFilling_date((String) row_list.get(1));
				regidother.setStamp_details((String) row_list.get(2));
				regidother.setOther_fees((String) row_list.get(3));
				regidother.setFirst_name((String) row_list.get(4));
				regidother.setMiddle_name((String) row_list.get(5));
				regidother.setLast_name((String) row_list.get(6));
				regidother.setGender((String) row_list.get(7));
				regidother.setAge((String) row_list.get(8));
				regidother.setPostal_code((String) row_list.get(9));
				regidother.setPhone_number((String) row_list.get(10));
				regidother.setAddress((String) row_list.get(11));
				regidother.setBank_name((String) row_list.get(12));
				regidother.setBank_address((String) row_list.get(13));
				regidother.setDistrict((String) row_list.get(14));
				regidother.setState((String) row_list.get(15));
				regidother.setCountry((String) row_list.get(16));

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return regidother;
	}

	public ArrayList getDocIdDetails(String searchId) {
		ArrayList list = new ArrayList();
		try {

			String docid[] = new String[1];
			docid[0] = searchId;
			ArrayList searchOther = agmpdao.getDocIdDetails(docid);
			for (int i = 0; i < searchOther.size(); i++) {
				ArrayList tempList = (ArrayList) searchOther.get(i);
				RegIdOtherSearch dto = new RegIdOtherSearch();
				dto.setFilling_id((String) tempList.get(0));
				dto.setFilling_date((String) tempList.get(1));
				list.add(dto);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

	public String submitInspectionReport(PublicDTO myPublicDTO,
			ArrayList listFileNames, String strFilePath,
			ArrayList objectionList, String strUserId) {
		String result = null;
		try {

			/*
			 * ArrayList list = agmpdao.getDistrictId(agmpdto.getSroId());
			 * ArrayList listTemp = new ArrayList(); listTemp = (ArrayList)
			 * list.get(0); String distId = (String) listTemp.get(0);
			 */

			// Inserting into IGRS_POI_TXN_DTLS
			String poiTxnDtls[] = new String[15];
			poiTxnDtls[0] = myPublicDTO.getOfficeName().trim();
			poiTxnDtls[1] = myPublicDTO.getAddress().trim();
			poiTxnDtls[2] = CommonUtil.getConvertedDate(myPublicDTO.getFromDate().trim());
			poiTxnDtls[3] = CommonUtil.getConvertedDate(myPublicDTO.getToDate());
			poiTxnDtls[4] = CommonUtil.getConvertedDate(myPublicDTO.getInsDate());
			poiTxnDtls[5] = "O";
			poiTxnDtls[6] = myPublicDTO.getComments().trim();
			poiTxnDtls[7] = myPublicDTO.getDistrictId().trim();
			poiTxnDtls[8] = myPublicDTO.getOfficeId().trim();
			poiTxnDtls[9] = strUserId.trim();
			poiTxnDtls[10] = strUserId.trim();
			poiTxnDtls[11] = strUserId.trim();
			poiTxnDtls[13] =  CommonUtil.getConvertedDate(myPublicDTO.getDispatchDate());
			poiTxnDtls[14] = myPublicDTO.getDispatchNumber();
			// INSERTION IGRS_POI_DOC_DTLS

			String auditFilePath = strFilePath;// listFilePaths.size()
			String[] auditFileName = new String[listFileNames.size()];
			for (int i = 0; i < listFileNames.size(); i++) {
				UploadFileDTO uploadDTO = (UploadFileDTO) listFileNames.get(i);
				auditFileName[i] = uploadDTO.getFileName();
			}
			String fileDetail[] = new String[3];
			fileDetail[0] = "O";
			fileDetail[1] = strUserId;// "SR/DR";
			fileDetail[2] = strUserId;// "SR/DR";

			// INSERTION IGRS_POI_PARA_DTLS

			/*
			 * ArrayList para = agmpdao.getMaxParaId(); ArrayList paraRow =
			 * (ArrayList) para.get(0); String paraTxn = (String)
			 * paraRow.get(0);
			 */

			String paraDetails[] = new String[5];
			paraDetails[0] = myPublicDTO.getParaType().trim();
			paraDetails[1] = myPublicDTO.getParaName().trim();
			paraDetails[2] = myPublicDTO.getParaComments().trim();
			paraDetails[3] = "Y";

			// INSERTION IGRS_POI_PARA_OBJ_DTLS, IGRS_POI_COMMENT_DTLS

			// String objection[] = new String[7];

			// INSERTION IGRS_POI_COMMENT_DTLS
			ArrayList objList = new ArrayList();
			ArrayList commList = new ArrayList();

			for (int i = 0; i < objectionList.size(); i++) {
				POIAddObjectionDTO docDetails = (POIAddObjectionDTO) objectionList.get(i);
				String objection[] = new String[8];
				objection[0] = docDetails.getTxtObjDetails().trim();
				objection[1] = docDetails.getObjectionStatus().trim();
				objection[2] = docDetails.getTxtDocID().trim();
				//modified by shruti---24 feb 2014
				//objection[3] = docDetails.getTxtRegFee1().trim();
				objection[3] = docDetails.getTxtRegFee().trim();
				//end
				objection[4] = docDetails.getTempValAgmp().trim();
				objection[5] = docDetails.getTxtStampDuty().trim();
				objection[6] = docDetails.getTxtRegFee().trim();
				objection[7] = "";

				objList.add(objection);

				String agmpComm[] = new String[3];
				agmpComm[0] = strUserId;
				agmpComm[1] = docDetails.getTxtAgmpComm();
				agmpComm[2] = "";

				commList.add(agmpComm);

			}

			ArrayList listAddDetails = new ArrayList();
			listAddDetails.add(poiTxnDtls);
			listAddDetails.add(auditFilePath);
			listAddDetails.add(auditFileName);
			listAddDetails.add(fileDetail);
			listAddDetails.add(paraDetails);
			listAddDetails.add(objList);
			listAddDetails.add(commList);

			result = publicInspectionDAO.submitInspectionReport(listAddDetails);

		} catch (Exception e) {
			logger.error("error in BD");
			e.printStackTrace();
		}
		return result;
	}

	public PublicDTO getExistingDetails(String poiTxnId) {
		PublicDTO publicDTO = new PublicDTO();
		String[] arr = new String[1];
		arr[0] = poiTxnId;
		ArrayList list = publicInspectionDAO.getExistingDetails(arr);
		ArrayList list1 = (ArrayList) list.get(0);
		publicDTO.setTxnId((String) list1.get(0));
		publicDTO.setOfficeName((String) list1.get(1));
		publicDTO.setAddress((String) list1.get(2));
		publicDTO.setDistrictName((String) list1.get(3));
		publicDTO.setInsDate((String) list1.get(4));
		publicDTO.setCreatedDate((String) list1.get(5));
		publicDTO.setFromDate((String) list1.get(6));
		publicDTO.setToDate((String) list1.get(7));
		publicDTO.setComments((String) list1.get(8));
		return publicDTO;
	}

	public boolean updateInspectionReport(PublicDTO myPublicDTO,
			ArrayList objectionList, String strUserId) {
		boolean result = false;

		String poiTxnId = myPublicDTO.getTxnId();
		String paraDetails[] = new String[5];
		paraDetails[0] = myPublicDTO.getParaType().trim();
		paraDetails[1] = myPublicDTO.getParaName().trim();
		paraDetails[2] = myPublicDTO.getParaComments().trim();
		paraDetails[3] = "Y";
		paraDetails[4] = "";

		ArrayList objList = new ArrayList();
		ArrayList commList = new ArrayList();

		for (int i = 0; i < objectionList.size(); i++) {
			POIAddObjectionDTO docDetails = (POIAddObjectionDTO) objectionList
					.get(i);
			String objection[] = new String[8];
			objection[0] = docDetails.getTxtObjDetails().trim();
			objection[1] = docDetails.getObjectionStatus().trim();
			objection[2] = docDetails.getTxtDocID().trim();
			objection[3] = docDetails.getTxtRegFee1().trim();
			objection[4] = docDetails.getTempValAgmp().trim();
			objection[5] = docDetails.getTxtStampDuty().trim();
			objection[6] = docDetails.getTxtRegFee().trim();
			objection[7] = "";

			objList.add(objection);

			String agmpComm[] = new String[3];
			agmpComm[0] = strUserId;
			agmpComm[1] = docDetails.getTxtAgmpComm();
			agmpComm[2] = "";

			commList.add(agmpComm);
		}
		ArrayList listAddDetails = new ArrayList();
		listAddDetails.add(paraDetails);
		listAddDetails.add(objList);
		listAddDetails.add(commList);
		listAddDetails.add(poiTxnId);

		result = publicInspectionDAO.updateInspectionReport(listAddDetails);
		return result;
	}

	public ArrayList getPrintDetails(String poiTxnId) {
		ArrayList list = new ArrayList();
		String[] arr = new String[1];
		arr[0] = poiTxnId;
		ArrayList listAll = publicInspectionDAO.getPrintDetails(arr);
		ArrayList listDetails = (ArrayList) listAll.get(0);
		ArrayList listDoc = (ArrayList) listAll.get(1);
		ArrayList listPara = (ArrayList) listAll.get(2);

		PublicDTO myPublicDTO = new PublicDTO();
		ArrayList listDtails1 = (ArrayList) listDetails.get(0);
		myPublicDTO.setOfficeName((String) listDtails1.get(0));
		myPublicDTO.setAddress((String) listDtails1.get(1));
		myPublicDTO.setDistrictName((String) listDtails1.get(2));
		myPublicDTO.setInsDate((String) listDtails1.get(3));
		myPublicDTO.setCreatedDate((String) listDtails1.get(4));
		myPublicDTO.setFromDate((String) listDtails1.get(5));
		myPublicDTO.setToDate((String) listDtails1.get(6));
		myPublicDTO.setComments((String) listDtails1.get(7));

		list.add(myPublicDTO);

		ArrayList listFinalDoc = new ArrayList();
		for (int i = 0; i < listDoc.size(); i++) {
			ArrayList listDoc1 = (ArrayList) listDoc.get(i);
			PublicDTO publicDTO = new PublicDTO();
			publicDTO.setDocTxnId((String) listDoc1.get(0));
			publicDTO.setDocName((String) listDoc1.get(1));
			listFinalDoc.add(publicDTO);
		}

		list.add(listFinalDoc);

		ArrayList listFinalPara = new ArrayList();
		for (int i = 0; i < listPara.size(); i++) {
			ArrayList listPara1 = (ArrayList) listPara.get(i);
			PublicDTO publicDTO = new PublicDTO();
			publicDTO.setParaType((String) listPara1.get(0));
			publicDTO.setParaName((String) listPara1.get(1));
			publicDTO.setParaComments((String) listPara1.get(2));
			listFinalPara.add(publicDTO);
		}

		list.add(listFinalPara);

		return list;
	}

	 public ArrayList getPublicParaDetails(String language) throws Exception{
				ArrayList list = new ArrayList();
				AGMPReportDetailsDTO agmpDTO =null;
				ArrayList receiptparalist=new ArrayList();
				try {

					list = publicInspectionDAO.getExpenParaList(language);
					if(list!=null){
					for(int i=0;i<list.size();i++){			
					ArrayList rowList = (ArrayList) list.get(i);
					agmpDTO=new AGMPReportDetailsDTO();
					agmpDTO.setParaType((String) rowList.get(1));
					agmpDTO.setParaName((String) rowList.get(1));
					receiptparalist.add(agmpDTO);
					}
					}
					

				} catch (Exception e) {
					e.printStackTrace();
				}
				return receiptparalist;
			}
	
public ArrayList getInspectionPoiId(PublicDTO publicDTO,String language){
	 String[] droDetails    =   new String[5];
	    droDetails[0]   =  null;//CommonUtil.getConvertedDate(droReport.getInspStartDate());
	    droDetails[1]   =  null;//CommonUtil.getConvertedDate(droReport.getInspToDate());
	    droDetails[2]   =  null;//droReport.getDroId();
	    droDetails[3]   =  null;//droReport.getDroInspId();
	    droDetails[4]   =  null;
	    
	    
	    if(publicDTO.getFromDate()!=null && publicDTO.getToDate()!=null)
	    {
	    	if(!publicDTO.getFromDate().equalsIgnoreCase("") && !publicDTO.getToDate().equalsIgnoreCase(""))
	    	droDetails[0]   =  CommonUtil.getConvertedDate(publicDTO.getFromDate());
	    	droDetails[1]   =  CommonUtil.getConvertedDate(publicDTO.getToDate());
	    	
	    }
	    if(publicDTO.getReportId()!=null && !publicDTO.getReportId().equalsIgnoreCase(""))
	    {
	    	droDetails[3]   =  publicDTO.getReportId();
	    }
	   	if(publicDTO.getDistrictId()!=null && !publicDTO.getDistrictId().equalsIgnoreCase(""))
	   	{
	   		droDetails[2]   =  publicDTO.getDistrictId();
	   	}
	   	if(publicDTO.getInspectionStatus()!=null && !publicDTO.getInspectionStatus().equalsIgnoreCase(""))
	    {
	   		droDetails[4]   = publicDTO.getInspectionStatus();
	    }	
	   	ArrayList listIds     = new ArrayList();	
	   	ArrayList listFromDB  = publicInspectionDAO.getInspectionPoiId(droDetails);
	   		if (listFromDB != null) {
	   			for (int i = 0; i < listFromDB.size(); i++) {
	   	              ArrayList rowList = (ArrayList)listFromDB.get(i);
	   	              PublicDTO  droDto = new PublicDTO();
	   	              droDto.setTxnId((String)rowList.get(0));
	   	              droDto.setFromDate((String)rowList.get(1));
	   	              if("en".equalsIgnoreCase(language))
	   	              {
	   	              droDto.setInspectionStatus(rowList.get(2).toString().equalsIgnoreCase("O")?"OPEN":"CLOSED");
	   	              }
	   	              else
	   	              {
	   	            	droDto.setInspectionStatus(rowList.get(2).toString().equalsIgnoreCase("O")?"ओपन":"बंद");
	   	              }
	   	              // droDto.setInspStatus((String)rowList.get(3));
	   	              listIds.add(droDto);
	   			}
	   		}
	   	    return listIds;
	   		
}

public PublicDTO getPOIDetails(String id,String language)
{
	ArrayList listFromDB  = publicInspectionDAO.getPOIDetails(id);
	PublicDTO poiDTO=new PublicDTO();
	if (listFromDB != null) {
			for (int i = 0; i < listFromDB.size(); i++) {
	              ArrayList rowList = (ArrayList)listFromDB.get(i);
	              poiDTO.setPubOfficeName((String)rowList.get(0));
	              poiDTO.setAddress((String)rowList.get(1));
	              poiDTO.setDistrictName((String)rowList.get(2));
	              poiDTO.setInsDate((String)rowList.get(3));
	              poiDTO.setDispatchDate((String)rowList.get(4));
	              poiDTO.setDispatchNumber((String)rowList.get(5));
	              poiDTO.setFromDate((String)rowList.get(6));
	              poiDTO.setToDate((String)rowList.get(7));
	              poiDTO.setComments((String)rowList.get(8));
	              poiDTO.setInsEntryDate((String)rowList.get(9));
	              if("en".equalsIgnoreCase(language))
	              {
	              poiDTO.setInspectionStatus(rowList.get(10).toString().equalsIgnoreCase("O")?"OPEN":"CLOSED");
	              }
	              else
	              {
	            	  poiDTO.setInspectionStatus(rowList.get(10).toString().equalsIgnoreCase("O")?"ओपन":"बंद");
	              }
			}
	
	
}
	return poiDTO;
}

public ArrayList<PublicDTO> getDocName(String id)
{
	ArrayList listFromDB  = publicInspectionDAO.getDocName(id);
	ArrayList<PublicDTO> returnList=new ArrayList<PublicDTO>();
	if (listFromDB != null) {
		for (int i = 0; i < listFromDB.size(); i++) {
              ArrayList rowList = (ArrayList)listFromDB.get(i);
              PublicDTO poiDTO=new PublicDTO();
              poiDTO.setDocName((String)rowList.get(0));
              returnList.add(poiDTO);
		}
}

	return returnList;
}

public ArrayList<PublicDTO> getDocPath(String id)
{
	ArrayList listFromDB  = publicInspectionDAO.getDocPath(id);
	ArrayList<PublicDTO> returnList=new ArrayList<PublicDTO>();
	if (listFromDB != null) {
		for (int i = 0; i < listFromDB.size(); i++) {
              ArrayList rowList = (ArrayList)listFromDB.get(i);
              PublicDTO poiDTO=new PublicDTO();
              poiDTO.setDocPath((String)rowList.get(0));
              poiDTO.setDocName((String)rowList.get(1));
              returnList.add(poiDTO);
		}
}

	return returnList;
}
public ArrayList getParaDetails(String id,String language)
{
	ArrayList listFromDB  = publicInspectionDAO.getParaDetails(id,language);
	ArrayList returnList=new ArrayList();
	if (listFromDB != null) {
		for (int i = 0; i < listFromDB.size(); i++) {
              ArrayList rowList = (ArrayList)listFromDB.get(i);
              PublicDTO poiDTO=new PublicDTO();
              poiDTO.setParaType((String)rowList.get(0));
              poiDTO.setParaName((String)rowList.get(1));
              poiDTO.setParaComments((String)rowList.get(2));
              poiDTO.setParaId((String)rowList.get(3));
              if("en".equalsIgnoreCase(language))
              {
              poiDTO.setParaStatus(((String)rowList.get(4)).equalsIgnoreCase("Y")?"OPEN":"CLOSED");
              }
              else
              {
            	  poiDTO.setParaStatus(((String)rowList.get(4)).equalsIgnoreCase("Y")?"ओपन":"बंद");  
              }
              returnList.add(poiDTO);
		}
}

	return returnList;
}
public ArrayList<PublicDTO> getObjDetails(String id,String language)
{
	ArrayList listFromDB  = publicInspectionDAO.getObjDetails(id);
	ArrayList<PublicDTO> returnList=new ArrayList<PublicDTO>();
	if (listFromDB != null) {
		for (int i = 0; i < listFromDB.size(); i++) {
              ArrayList rowList = (ArrayList)listFromDB.get(i);
              PublicDTO poiDTO=new PublicDTO();
              poiDTO.setDocId((String)rowList.get(0));
              poiDTO.setObjDetails((String)rowList.get(1));
              poiDTO.setAgmpComments((String)rowList.get(2));
              //modified by shruti//15th oct 2013
              //poiDTO.setObjectionStatus(rowList.get(3).toString().equalsIgnoreCase("O")?"OPEN":"CLOSED");
              if("en".equalsIgnoreCase(language))
              {
              poiDTO.setObjectionStatus(rowList.get(3).toString().equalsIgnoreCase("OPEN")?"OPEN":"CLOSED");
              }
              else
              {
            	  poiDTO.setObjectionStatus(rowList.get(3).toString().equalsIgnoreCase("OPEN")? "ओपन":"बंद");  
              }
             
              returnList.add(poiDTO);
		}
}

	return returnList;
}
public PublicDTO getParaDtls(String transid)
		 throws Exception {
		 ArrayList tmpList = new ArrayList();
		// PublicDTO dto = new PublicDTO();
		 ArrayList docDetailsList=new ArrayList();
		 PublicDTO pdto = new PublicDTO();
		 try {
			 tmpList=publicInspectionDAO.getParaDetailsComplete(transid);
			
			 if (tmpList != null && tmpList.size() > 0) 
			 {
			 logger.debug("--size--<>" + tmpList.size());
				 for (int j = 0; j < tmpList.size(); j++)
				 {
				 ArrayList arl = (ArrayList) tmpList.get(j);
								 if (arl != null && arl.size() > 0) 
								 {
								 logger.debug("--arl size--<>" + arl.size());
								 pdto.setParaId((String) arl.get(0));
								 pdto.setParaTypeName((String) arl.get(1));
								 pdto.setParaName((String) arl.get(2));
								 pdto.setParaComments((String) arl.get(3));
								 pdto.setParaStatus(((String) arl.get(4)).equalsIgnoreCase("Y")?"OPEN":"CLOSED");
								 /*pdto.setParaNum((String) arl.get(5));
								 pdto.setReport((String) arl.get(6));
								 pdto.setParaId((String) arl.get(7));
								 pdto.setObjId((String) arl.get(8));
								 pdto.setDocId((String) arl.get(9));
								 pdto.setObjDate((String) arl.get(10));
								 pdto.setObjDtls((String) arl.get(11));
								 pdto.setDefRegFee((String) arl.get(12));
								 pdto.setDefStampDuty((String) arl.get(13));
								 pdto.setValReg((String) arl.get(14));
								 pdto.setValAgmp((String) arl.get(15));
								 pdto.setCaseId((String) arl.get(16));*/
								/* pdto.setParaType((String) arl.get(0));
								 pdto.setParaId((String) arl.get(1));
								 pdto.setParaStatus((String) arl.get(2));
								 pdto.setTransId((String) arl.get(3));*/
								 //docDetailsList.add(pdto);
								 logger.debug("SIZE>>>>>>>>"+docDetailsList.size());
								 }
				 }
			 }
			 else 
			 {
			 pdto = null;
			 }
		 } catch (Exception e) {
		 logger.error(e);
		 }
		 return pdto;
		 }	 

public ArrayList getObjectionDtls(String transid)
		 throws Exception {
		 ArrayList tmpList = new ArrayList();
	//	 PublicDTO dto = new PublicDTO();
		 ArrayList docDetailsList=new ArrayList();
		 try {
			 tmpList=publicInspectionDAO.getObjectionDetails(transid);
			 if (tmpList != null && tmpList.size() > 0) 
			 {
			 logger.debug("--size--<>" + tmpList.size());
				 for (int j = 0; j < tmpList.size(); j++)
				 {
				 ArrayList arl = (ArrayList) tmpList.get(j);
								 if (arl != null && arl.size() > 0) 
								 {
								 logger.debug("--arl size--<>" + arl.size());
								 PublicDTO pdto = new PublicDTO();
								 pdto.setObjId((String) arl.get(0));
								 pdto.setDocId((String) arl.get(1));
								 pdto.setObjStatus("O".equalsIgnoreCase(((String) arl.get(2)))?"OPEN":"CLOSED");
								 pdto.setCaseId((String) arl.get(3));
								 /*pdto.setParaType((String) arl.get(0));
								 pdto.setParaId((String) arl.get(1));
								 pdto.setParaStatus((String) arl.get(2));
								 pdto.setTransId((String) arl.get(3));*/
								 docDetailsList.add(pdto);
								 logger.debug("SIZE>>>>>>>>"+docDetailsList.size());
								 }
				 }
			 }
			 
		 } catch (Exception e) {
		 logger.error(e);
		 }
		 return docDetailsList;
		 }	 
public PublicDTO getObjectionParaDtls(String transid)
throws Exception {
	 ArrayList tmpList = new ArrayList();
	// PublicDTO dto = new PublicDTO();
	 ArrayList docDetailsList=new ArrayList();
	 PublicDTO pdto = new PublicDTO();
	 try {
		 tmpList=publicInspectionDAO.getObjectionParaDetails(transid);
		
		 if (tmpList != null && tmpList.size() > 0) 
		 {
		 logger.debug("--size--<>" + tmpList.size());
			 for (int j = 0; j < tmpList.size(); j++)
			 {
			 ArrayList arl = (ArrayList) tmpList.get(j);
							 if (arl != null && arl.size() > 0) 
							 {
							 logger.debug("--arl size--<>" + arl.size());
							 pdto.setObjDtls((String) arl.get(0));
							 pdto.setObjStatus((String) arl.get(1));
							 pdto.setDocId((String) arl.get(2));
							 pdto.setValReg((String) arl.get(3));
							 pdto.setValAgmp((String) arl.get(4));
							 pdto.setDefStampDuty((String) arl.get(5));
							 pdto.setDefRegFee((String) arl.get(6));
							 pdto.setCaseId((String) arl.get(7));
							 pdto.setObjDate((String) arl.get(8)); 
							 /*pdto.setParaId((String) arl.get(0));
							 pdto.setParaTypeName((String) arl.get(1));
							 pdto.setParaName((String) arl.get(2));
							 pdto.setParaComments((String) arl.get(3));
							 pdto.setParaStatus((String) arl.get(4));
							 pdto.setParaNum((String) arl.get(5));
							 pdto.setReport((String) arl.get(6));
							 pdto.setParaId((String) arl.get(7));
							 pdto.setObjId((String) arl.get(8));
							 pdto.setDocId((String) arl.get(9));
							 pdto.setObjDate((String) arl.get(10));
							 pdto.setObjDtls((String) arl.get(11));
							 pdto.setDefRegFee((String) arl.get(12));
							 pdto.setDefStampDuty((String) arl.get(13));
							 pdto.setValReg((String) arl.get(14));
							 pdto.setValAgmp((String) arl.get(15));
							 pdto.setCaseId((String) arl.get(16));*/
							 logger.debug("SIZE>>>>>>>>"+docDetailsList.size());
							 }
			 }
		 }
		 
	 } catch (Exception e) {
	 logger.error(e);
	 }
	 return pdto;
	 }	 

public boolean updateObjStatus(PublicDTO pdto)
{
	return publicInspectionDAO.updateObjStatus(pdto);
}
public boolean updateParaStatus(PublicDTO pdto)
{
	return publicInspectionDAO.updateParaStatus(pdto);
}
//added by shruti
//added by shruti-5th sep 2013
public PartyDetailsDTO getPartyDtls(String _refRegId,String language)
	throws Exception {
	 
ArrayList tmpList = new ArrayList();
PartyDetailsDTO dto = new PartyDetailsDTO();
ArrayList partyList=new ArrayList();
try {
		tmpList=agmpdao.getPartyDetails(_refRegId,language);
		PartyDetailsDTO tmppdto = new PartyDetailsDTO();
		
		if (tmpList != null && tmpList.size() > 0) 
		{
			logger.debug("--size--<>" + tmpList.size());
			for (int j = 0; j < tmpList.size(); j++)
			{
				logger.debug("tmp list  for--<>" + j);
				ArrayList arl = (ArrayList) tmpList.get(j);
				if (arl != null && arl.size() > 0) 
				{
					logger.debug("--arl size--<>" + arl.size());
					PartyDetailsDTO pdto = new PartyDetailsDTO();
					pdto.setFirstName((String) arl.get(0));
					pdto.setMiddleName((String) arl.get(1));
					pdto.setLastName((String) arl.get(2));
					pdto.setPartyTypeName((String) arl.get(3));
					pdto.setGender((String) arl.get(4));
					pdto.setAge((String) arl.get(5));
					pdto.setNationality((String) arl.get(6));
					pdto.setCountry((String) arl.get(7));
					pdto.setState((String) arl.get(8));
					pdto.setCity((String) arl.get(9));
					pdto.setAddress((String) arl.get(10));
					pdto.setPostalCode((String) arl.get(11));
					pdto.setLandNo((String) arl.get(12));
					pdto.setMobileNo((String) arl.get(13));
					pdto.setEmailId((String) arl.get(14));
					pdto.setPartyChk((String) arl.get(15));
					pdto.setFatherName((String) arl.get(16));
					pdto.setMotherName((String) arl.get(17));
					pdto.setOrgName((String) arl.get(18));
					pdto.setAuthPrsnName((String) arl.get(19));
					partyList.add(pdto);
					dto.setPartyList(partyList);
				}
			}
		}
		
	else 
	{
		dto = null;
	}
} catch (Exception e) {
	logger.error(e);
}
return dto;
}

public PropertyDetailsDTO getPropertyDtls(String _refRegId,String language)
	throws Exception {
	 
	 ArrayList tmppropertyList=new ArrayList();
	 PropertyDetailsDTO dto = new PropertyDetailsDTO();
	 try
	 {
		tmppropertyList = agmpdao.getPropertyDetails(_refRegId,language);
		ArrayList propertyList = new ArrayList();
		PropertyDetailsDTO propertydto = new PropertyDetailsDTO();
		if (tmppropertyList != null && tmppropertyList.size() > 0) 
		{
			for (int k = 0; k < tmppropertyList.size(); k++) 
			{
			ArrayList temppropList = (ArrayList) tmppropertyList.get(k);
			logger.debug("property list size -->"+  temppropList.size());
			propertydto.setPropType((String) temppropList.get(2));
			propertydto.setUseType((String) temppropList.get(2));
			propertydto.setDistName((String) temppropList.get(3));
			propertydto.setTehsilName((String) temppropList.get(4));
			propertydto.setWardName((String) temppropList.get(5));
			propertydto.setMohallaName((String) temppropList.get(6));
			propertydto.setAreaTypeName((String) temppropList.get(7));
			propertydto.setGovMunicplBody((String) temppropList.get(8));
			propertydto.setVikasKhandName((String) temppropList.get(9));
			propertydto.setRiCircle((String) temppropList.get(10));
			propertydto.setLayoutDet((String) temppropList.get(11));
			propertydto.setNazoolStreetNo((String) temppropList.get(12));
			propertydto.setAddress((String) temppropList.get(13));
			propertydto.setEastBoundary((String) temppropList.get(14));
			propertydto.setWestBoundary((String) temppropList.get(15));
			propertydto.setNorthBoundary((String) temppropList.get(16));
			propertydto.setSouthBoundary((String) temppropList.get(17));
			propertydto.setTotal((String) temppropList.get(18));
			propertyList.add(propertydto);
			dto.setPropertyList(propertyList);
		}
	}
	else 
	{
		dto = null;
	}
} catch (Exception e) {
	logger.error(e);
}
return dto;
}

}