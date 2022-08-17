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
import com.wipro.igrs.auditinspection.dto.AGMPReportDetailsDTO;
import com.wipro.igrs.auditinspection.dto.CaseDetails;
import com.wipro.igrs.auditinspection.dto.CaseIdOtherSearch;
import com.wipro.igrs.auditinspection.dto.DocDetails;
import com.wipro.igrs.auditinspection.dto.ExistingAuditBean;
import com.wipro.igrs.auditinspection.dto.PartyDetailsDTO;
import com.wipro.igrs.auditinspection.dto.PropertyDetailsDTO;
import com.wipro.igrs.auditinspection.dto.RegIdOtherSearch;
import com.wipro.igrs.auditinspection.dto.UploadFileDTO;
import com.wipro.igrs.auditinspection.sql.CommonSQL;
import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.util.CommonUtil;

public class AGMPReportDetailsBD {

	private AGMPReportDetailsDAO agmpdao = new AGMPReportDetailsDAO();
	 Logger logger = 
			(Logger) Logger.getLogger(AGMPReportDetailsBD.class);
	/**
	 * @param agmpreportdetailsdto
	 * @param userid 
	 * @return
	 */
	public RegIdOtherSearch getRegIdDetails(
			AGMPReportDetailsDTO agmpreportdetailsdto, String userid) {
		RegIdOtherSearch regidother = new RegIdOtherSearch();
		//MODIFIED BY SHRUTI---18/OCT/2013
		/*String regid[] = new String[2];
		regid[0] = agmpreportdetailsdto.getRegId();
		regid[1]=userid;*/
		
		String regid[] = new String[1];
		regid[0] = agmpreportdetailsdto.getRegId();
		//regid[1]=userid;

		ArrayList searchOther = new ArrayList();
		ArrayList resultList=new ArrayList();
		try {
			searchOther = agmpdao.getRegDetails(regid);
			
			for (int i = 0; i < searchOther.size(); i++) {
				ArrayList row_list = (ArrayList) searchOther.get(i);
				regidother.setFilling_id((String) row_list.get(0));
				regidother.setFilling_date((String) row_list.get(1));
				regidother.setSrName((String) row_list.get(2));
				regidother.setStamp_details((String) row_list.get(3));
				regidother.setOther_fees((String) row_list.get(4));
				if((String) row_list.get(5)==""||(String) row_list.get(5)==null)
				{
					regidother.setMarketVal("-");
				}
				else
				{
				regidother.setMarketVal((String) row_list.get(5));
				}
			}
			agmpreportdetailsdto.setRegIdOther(regidother);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return regidother;
	}

	/**
	 * @param agmpreportdetailsdto
	 * @return
	 */
	public RegIdOtherSearch getRegIdOtherDetails(
			AGMPReportDetailsDTO agmpreportdetailsdto) {
		RegIdOtherSearch regidother = new RegIdOtherSearch();
		String regid[] = new String[1];
		regid[0] = agmpreportdetailsdto.getRegId();
		
		ArrayList searchOther = new ArrayList();
		try {

			searchOther = agmpdao.getRegOtherDetails(regid);
			//modified by shruti-----19th oct 2013
			for (int i = 0; i < searchOther.size(); i++) {
				ArrayList row_list = (ArrayList) searchOther.get(i);
				regidother.setFilling_id((String) row_list.get(0));
				regidother.setFilling_date((String) row_list.get(1));
				regidother.setSrName((String) row_list.get(2));
				regidother.setStamp_details((String) row_list.get(3));
				regidother.setOther_fees((String) row_list.get(4));
				regidother.setMarketVal((String) row_list.get(5));
				/*regidother.setFirst_name((String) row_list.get(4));
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
				regidother.setCountry((String) row_list.get(16));*/

			}
			agmpreportdetailsdto.setRegIdOther(regidother);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return regidother;
	}

	/**
	 * @param existId
	 * @return
	 */
	public AGMPReportDetailsDTO getExistingDetails(String existId,String language) {
		ArrayList list = new ArrayList();
		AGMPReportDetailsDTO agmpDTO = new AGMPReportDetailsDTO();
		String[] reportId = new String[1];
		reportId[0] = existId;
		try {

			list = agmpdao.getExistDetails(reportId,language);
			ArrayList rowList = (ArrayList) list.get(0);
			agmpDTO.setTxtAuditorName((String) rowList.get(0));
			agmpDTO.setTxtAuditLocation((String) rowList.get(1));
			agmpDTO.setFromDate((String) rowList.get(2));
			agmpDTO.setToDate((String) rowList.get(3));
			agmpDTO.setTxnId((String) rowList.get(4));
			agmpDTO.setTxtPhysicalId((String) rowList.get(5));
			agmpDTO.setTxtAuditDate((String) rowList.get(6));
			agmpDTO.setTxtDispatchDate((String) rowList.get(7));
			agmpDTO.setTxtEntryDate((String) rowList.get(8));
			agmpDTO.setTxtComments((String) rowList.get(9));
			agmpDTO.setOfficeName((String) rowList.get(10));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return agmpDTO;
	}

	/**
	 * @param agmpreport
	 * @return
	 */
	public ArrayList getRegDetails(AGMPReportDetailsDTO agmpreport) {
		ArrayList searchlist = new ArrayList();
		try {

			String search[] = new String[1];
			search[0] = agmpreport.getRegId();
			searchlist = agmpdao.getRegidInfo(search);
		} catch (Exception e) {
		e.printStackTrace();	
		}
		return searchlist;
	}

	/***************************************************************************
	 * This method For Displaying All DoumentIDs
	 **************************************************************************/
	/**
	 * @param agmpreportdetailsdto
	 * @return
	 */
	public ArrayList getDocIdDetails(AGMPReportDetailsDTO agmpreportdetailsdto) {
		ArrayList searchOther = new ArrayList();
		try {

			String docid[] = new String[1];
			docid[0] = agmpreportdetailsdto.getRegId();
			searchOther = agmpdao.getDocIdDetails(docid);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return searchOther;
	}

	/**
	 * @param offTypeId
	 * @return
	 * @throws Exception
	 */
	public ArrayList getSroName(String userId) throws Exception {

		ArrayList sroList = agmpdao.getSRO(userId);
		ArrayList sroList1 = new ArrayList();
		if (sroList != null) {
			for (int i = 0; i < sroList.size(); i++) {
				ArrayList sroTemp = (ArrayList) sroList.get(i);
				if (sroTemp != null) {
					AGMPReportDetailsDTO agmpreportdetailsdto = new AGMPReportDetailsDTO();
					agmpreportdetailsdto.setSroId((String) sroTemp.get(0));
					agmpreportdetailsdto.setSroName((String) sroTemp.get(1));
					sroList1.add(agmpreportdetailsdto);
				}
			}
		}
		return sroList1;
	}

	/**
	 * @param agmpdto
	 * @return
	 */
	public boolean insertAddDocDetails(AGMPReportDetailsDTO agmpdto) {
		String docdetails[] = new String[5];
		docdetails[0] = "OBJ12222";
		docdetails[1] = agmpdto.getTxtObjDetails();
		docdetails[2] = agmpdto.getTxtValAgmp();
		docdetails[3] = agmpdto.getTxtStampDuty();
		docdetails[4] = agmpdto.getTxtRegFee();
		try {

			agmpdao.saveDocDetails(docdetails);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return true;
	}

	/**
	 * @param agmpdto
	 * @return
	 */
	public boolean insertParaDetails(AGMPReportDetailsDTO agmpdto) {
		String paraDetails[] = new String[5];
		paraDetails[0] = "PARATXN001";
		paraDetails[1] = agmpdto.getListParaType();
		paraDetails[2] = agmpdto.getTextParaName();
		paraDetails[3] = agmpdto.getTextParaComments();
		paraDetails[4] = "O";
		try {

			agmpdao.saveParaDetails(paraDetails);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * @param docList
	 * @param agmpReport
	 * @return
	 */
	public boolean saveDocDetails(ArrayList docList,
			AGMPReportDetailsDTO agmpReport) {

		for (int i = 0; i < docList.size(); i++) {
			// DocDetails docDetails3=new DocDetails();
			DocDetails docDetails = (DocDetails) docList.get(i);
			String docdetails[] = new String[5];
			docdetails[0] = "OBJ000" + i;
			docdetails[1] = docDetails.getObjDetails();
			docdetails[2] = docDetails.getValAgmp();
			docdetails[3] = docDetails.getStampDuty();
			docdetails[4] = docDetails.getRegFee();
			String[] agmpComments = new String[4];
			agmpComments[0] = "COMMENTTXN000" + i;
			agmpComments[1] = agmpReport.getTxtAuditorName();
			agmpComments[2] = agmpReport.getTxtAuditLocation();
			agmpComments[3] = docDetails.getTxtAgmpComm();
			try {

				agmpdao.saveDocDetails(docdetails);
				agmpdao.insertAuditComments(agmpComments);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	/**
	 * @param agmpReport
	 * @return
	 */
	public AGMPReportDetailsDTO[] getDocIdStatus(AGMPReportDetailsDTO agmpReport) {
		ArrayList docIdStatus = new ArrayList();
		AGMPReportDetailsDTO[] agmpReportDTO1 = null;
		String[] docId = new String[1];
		docId[0] = agmpReport.getTxtDocID();
		try {

			docIdStatus = agmpdao.getDocStatus(docId);
			
			agmpReportDTO1 = new AGMPReportDetailsDTO[docIdStatus.size()];
			for (int i = 0; i < agmpReportDTO1.length; i++) {
				agmpReportDTO1[i] = new AGMPReportDetailsDTO();
			}
			for (int i = 0; i < docIdStatus.size(); i++) {
				ArrayList rowList = new ArrayList();
				rowList = (ArrayList) docIdStatus.get(i);
				agmpReportDTO1[i].setTxtDocID((String) rowList.get(0));
				agmpReportDTO1[i].setDocStatusId((String) rowList.get(1));
				agmpReportDTO1[i].setTxtAgmpComm(agmpReport.getTxtAgmpComm());
			}
			// return agmpReportDTO1;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		// return agmpReportDTO;
		return agmpReportDTO1;
	}

	/**
	 * @param agmpReport
	 * @return
	 */
	public DocDetails getRegDocStatus(AGMPReportDetailsDTO agmpReport) {
		ArrayList docIdStatus = new ArrayList();
		DocDetails docDetail = new DocDetails();
		String[] docId = new String[1];
		docId[0] = agmpReport.getTxtDocID();
		try {

			docIdStatus = agmpdao.getRegDocStatus(docId);
			for (int i = 0; i < docIdStatus.size(); i++) {
				ArrayList rowList = new ArrayList();
				rowList = (ArrayList) docIdStatus.get(i);
				docDetail.setTxtDocID((String) rowList.get(0));
				docDetail.setDocStatusId((String) rowList.get(1));
				docDetail.setTxtAgmpComm(agmpReport.getTxtAgmpComm());
				docDetail.setDocId(agmpReport.getTxtDocID());
				docDetail.setValAgmp(agmpReport.getTempValAgmp());
				docDetail.setTxtStampDuty1(agmpReport.getTxtStampDuty1());
				docDetail.setTxtSRname(agmpReport.getTxtSRname());
				docDetail.setObjDetails(agmpReport.getTxtObjDetails());
				docDetail.setTxtRegFee1(agmpReport.getTxtRegFee1());
				docDetail.setRegFee(agmpReport.getTxtRegFee());
				docDetail.setStampDuty(agmpReport.getTxtStampDuty());
			}
			return docDetail;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return docDetail;

	}

	/**
	 * @param agmpReport
	 * @return
	 */
	public DocDetails getDocStatus(AGMPReportDetailsDTO agmpReport) {
		ArrayList docIdStatus = new ArrayList();
		DocDetails docDetail = new DocDetails();
		String[] docId = new String[1];
		docId[0] = agmpReport.getTxtDocID();
		
	System.out.println("================="+docId[0]);
		try {
//COMMENTED FOR UNREGISTERED DOC INTEGRATION ON 8 OCT 2014
			//docIdStatus = agmpdao.getDocStatus(docId);
			//for (int i = 0; i < docIdStatus.size(); i++) {
				ArrayList rowList = new ArrayList();
				//rowList = (ArrayList) docIdStatus.get(i);
				docDetail.setTxtDocID(agmpReport.getTxtDocID());
				//docDetail.setTxtDocID((String) rowList.get(0));
				//docDetail.setDocStatusId((String) rowList.get(1));
				//docDetail.setTxtDocID(agmpreportdetailsdto.getOthersRegId());
				docDetail.setDocStatusId("OPEN");
				docDetail.setTxtAgmpComm(agmpReport.getTxtAgmpComm());
				docDetail.setDocId(agmpReport.getTxtDocID());
				docDetail.setValAgmp(agmpReport.getTempValAgmp());
				docDetail.setTxtStampDuty1(agmpReport.getTxtStampDuty1());
				docDetail.setTxtSRname(agmpReport.getTxtSRname());
				docDetail.setObjDetails(agmpReport.getTxtObjDetails());
				docDetail.setTxtRegFee1(agmpReport.getTxtRegFee1());
				docDetail.setRegFee(agmpReport.getTxtRegFee());
				docDetail.setStampDuty(agmpReport.getTxtStampDuty());
			//}
			// return docDetail;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return docDetail;

	}

	/**
	 * @param agmpReport
	 * @return
	 */
	public DocDetails getRegStatus(AGMPReportDetailsDTO agmpReport) {
		ArrayList regIdStatus = new ArrayList();
		DocDetails docDetail = new DocDetails();
		String[] docId = new String[1];
		docId[0] = agmpReport.getTxtDocID();
		
		try {

			regIdStatus = agmpdao.getRegIdStatus(docId);
			for (int i = 0; i < regIdStatus.size(); i++) {
				ArrayList rowList = new ArrayList();
				rowList = (ArrayList) regIdStatus.get(i);
				docDetail.setTxtDocID((String) rowList.get(0));
				docDetail.setDocStatusId((String) rowList.get(1));
				docDetail.setTxtAgmpComm(agmpReport.getTxtAgmpComm());
				docDetail.setDocId(agmpReport.getTxtDocID());
				docDetail.setValAgmp(agmpReport.getTempValAgmp());
				docDetail.setTxtStampDuty1(agmpReport.getTxtStampDuty1());
				docDetail.setTxtSRname(agmpReport.getTxtSRname());
				docDetail.setObjDetails(agmpReport.getTxtObjDetails());
				docDetail.setTxtRegFee1(agmpReport.getTxtRegFee1());
				docDetail.setRegFee(agmpReport.getTxtRegFee());
				docDetail.setStampDuty(agmpReport.getTxtStampDuty());
			}
			// return docDetail;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return docDetail;

	}

	
	//added by shruti-10th sep 2013
	public ArrayList getRegStatusNew(AGMPReportDetailsDTO agmpReport) {
		ArrayList regIdStatus = new ArrayList();
		DocDetails docDetail = new DocDetails();
		String[] docId = new String[1];
		docId[0] = agmpReport.getTxtDocID();
		ArrayList list=new ArrayList();
		
		try {

			regIdStatus = agmpdao.getRegIdStatus(docId);
			for (int i = 0; i < regIdStatus.size(); i++) {
				ArrayList rowList = new ArrayList();
				rowList = (ArrayList) regIdStatus.get(i);
				docDetail = new DocDetails();
				docDetail.setTxtDocID((String) rowList.get(0));
				docDetail.setDocStatusId((String) rowList.get(1));
				docDetail.setTxtAgmpComm(agmpReport.getTxtAgmpComm());
				docDetail.setDocId(agmpReport.getTxtDocID());
				docDetail.setValAgmp(agmpReport.getTempValAgmp());
				docDetail.setTxtStampDuty1(agmpReport.getTxtStampDuty1());
				docDetail.setTxtSRname(agmpReport.getTxtSRname());
				docDetail.setObjDetails(agmpReport.getTxtObjDetails());
				docDetail.setTxtRegFee1(agmpReport.getTxtRegFee1());
				docDetail.setRegFee(agmpReport.getTxtRegFee());
				docDetail.setStampDuty(agmpReport.getTxtStampDuty());
				list.add(docDetail);
			}
			// return docDetail;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}
	//end
	/**
	 * @param agmpReport
	 * @return
	 */
	public ArrayList getExistingTxnIds(AGMPReportDetailsDTO agmpReport) {
		ArrayList exist = new ArrayList();
		ArrayList existing = new ArrayList();
		String[] dates = new String[4];
		dates[0] = CommonUtil.getConvertedDate(agmpReport.getFromDate());
		dates[1] = CommonUtil.getConvertedDate(agmpReport.getToDate());
		dates[2] = agmpReport.getAuditBody();
		dates[3] = agmpReport.getSroId();
		try {

			exist = agmpdao.getExistingTxn(dates);
			for (int i = 0; i < exist.size(); i++) {
				ArrayList rowList = (ArrayList) exist.get(i);
				ExistingAuditBean bean = new ExistingAuditBean();
				bean.setAuditTxnId((String) rowList.get(0));
				bean.setAudit_date((String) rowList.get(1));
				bean.setDispatch_date((String) rowList.get(2));
				String strStatus = (String) rowList.get(3);
				if (strStatus.equalsIgnoreCase("O")) {
					strStatus = "Open";
				}
				bean.setComments(strStatus);
				existing.add(bean);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return existing;
	}
	//added by shruti---20 june 2014
	public ArrayList getExistingAgmpRcptTxnIds(AGMPReportDetailsDTO agmpReport) {
		ArrayList exist = new ArrayList();
		ArrayList existing = new ArrayList();
		String[] dates = new String[4];
		dates[0] = CommonUtil.getConvertedDate(agmpReport.getFromDate());
		dates[1] = CommonUtil.getConvertedDate(agmpReport.getToDate());
		dates[2] = agmpReport.getAuditBody();
		dates[3] = agmpReport.getSroId();
		try {

			exist = agmpdao.getExistingAgmpRcptTxn(dates);
			for (int i = 0; i < exist.size(); i++) {
				ArrayList rowList = (ArrayList) exist.get(i);
				ExistingAuditBean bean = new ExistingAuditBean();
				bean.setAuditTxnId((String) rowList.get(0));
				bean.setAudit_date((String) rowList.get(1));
				bean.setDispatch_date((String) rowList.get(2));
				//added by simran
				String status = getParaStatus(bean.getAuditTxnId());
				
				/*String strStatus = (String) rowList.get(3);
				if (strStatus.equalsIgnoreCase("O")) {
					strStatus = "Open";
				}*/
				bean.setComments(status);
				existing.add(bean);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return existing;
	}
	public ArrayList getExistingAgmpExpTxnIds(AGMPReportDetailsDTO agmpReport) {
		ArrayList exist = new ArrayList();
		ArrayList existing = new ArrayList();
		String[] dates = new String[4];
		dates[0] = CommonUtil.getConvertedDate(agmpReport.getFromDate());
		dates[1] = CommonUtil.getConvertedDate(agmpReport.getToDate());
		dates[2] = agmpReport.getAuditBody();
		dates[3] = agmpReport.getSroId();
		try {

			exist = agmpdao.getExistingAgmpExpTxn(dates);
			for (int i = 0; i < exist.size(); i++) {
				ArrayList rowList = (ArrayList) exist.get(i);
				ExistingAuditBean bean = new ExistingAuditBean();
				bean.setAuditTxnId((String) rowList.get(0));
				bean.setAudit_date((String) rowList.get(1));
				bean.setDispatch_date((String) rowList.get(2));
				/*String strStatus = (String) rowList.get(3);
				if (strStatus.equalsIgnoreCase("O")) {
					strStatus = "Open";
				}*/
				String status = getParaStatus(bean.getAuditTxnId());
				bean.setComments(status);
				existing.add(bean);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return existing;
	}
	public ArrayList getExistingInternalRcptTxnIds(AGMPReportDetailsDTO agmpReport) {
		ArrayList exist = new ArrayList();
		ArrayList existing = new ArrayList();
		String[] dates = new String[4];
		dates[0] = CommonUtil.getConvertedDate(agmpReport.getFromDate());
		dates[1] = CommonUtil.getConvertedDate(agmpReport.getToDate());
		dates[2] = agmpReport.getAuditBody();
		dates[3] = agmpReport.getSroId();
		try {

			exist = agmpdao.getExistingInternalRcptTxn(dates);
			for (int i = 0; i < exist.size(); i++) {
				ArrayList rowList = (ArrayList) exist.get(i);
				ExistingAuditBean bean = new ExistingAuditBean();
				bean.setAuditTxnId((String) rowList.get(0));
				bean.setAudit_date((String) rowList.get(1));
				bean.setDispatch_date((String) rowList.get(2));
				/*String strStatus = (String) rowList.get(3);
				if (strStatus.equalsIgnoreCase("O")) {
					strStatus = "Open";
				}*/
				//added by simran
				String status = getParaStatus(bean.getAuditTxnId());
				bean.setComments(status);
				existing.add(bean);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return existing;
	}
	public ArrayList getExistingInternalExpTxnIds(AGMPReportDetailsDTO agmpReport) {
		ArrayList exist = new ArrayList();
		ArrayList existing = new ArrayList();
		String[] dates = new String[4];
		dates[0] = CommonUtil.getConvertedDate(agmpReport.getFromDate());
		dates[1] = CommonUtil.getConvertedDate(agmpReport.getToDate());
		dates[2] = agmpReport.getAuditBody();
		dates[3] = agmpReport.getSroId();
		try {

			exist = agmpdao.getExistingInternalExpTxn(dates);
			for (int i = 0; i < exist.size(); i++) {
				ArrayList rowList = (ArrayList) exist.get(i);
				ExistingAuditBean bean = new ExistingAuditBean();
				bean.setAuditTxnId((String) rowList.get(0));
				bean.setAudit_date((String) rowList.get(1));
				bean.setDispatch_date((String) rowList.get(2));
				/*String strStatus = (String) rowList.get(3);
				if (strStatus.equalsIgnoreCase("O")) {
					strStatus = "Open";
				}*/
				String status = getParaStatus(bean.getAuditTxnId());
				bean.setComments(status);
				existing.add(bean);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return existing;
	}
	//end

	/**
	 * @param agmpReport
	 * @return
	 */
	public boolean saveAuditComments(AGMPReportDetailsDTO agmpReport) {
		ArrayList seqId = new ArrayList();
		try {

			seqId = agmpdao.getSeqTransactionIds();

			ArrayList rowList = new ArrayList();
			rowList = (ArrayList) seqId.get(0);
			String[] seq = new String[4];
			seq[0] = (String) rowList.get(0);
			seq[1] = (String) rowList.get(1);
			seq[2] = (String) rowList.get(2);
			seq[3] = (String) rowList.get(3);

			agmpdao.insertRauditCommentsDetails(seq);

		} catch (Exception exe) {
			exe.printStackTrace();
		}
		return true;
	}

	/**
	 * @param agmpReport
	 * @return
	 */
	public boolean saveExistingAuditComments(AGMPReportDetailsDTO agmpReport) {
		ArrayList seqId = new ArrayList();
		try {

			seqId = agmpdao.getSeqTransactionIds();

			ArrayList rowList = new ArrayList();
			rowList = (ArrayList) seqId.get(0);
			String[] seq = new String[4];
			seq[0] = agmpReport.getTxnId();
			seq[1] = (String) rowList.get(1);
			seq[2] = (String) rowList.get(2);
			seq[3] = (String) rowList.get(3);

			agmpdao.insertRauditCommentsDetails(seq);

		} catch (Exception exe) {
			exe.printStackTrace();
		}
		return true;
	}

	/**
	 * @param agmpdto
	 * @param listFileNames
	 * @param strFilePath
	 * @param docAgmpList
	 * @param strUserId
	 * @return
	 */
	public String submitAGMPReport(AGMPReportDetailsDTO agmpdto,
			ArrayList listFileNames, String strFilePath, ArrayList docAgmpList,String strUserId,
			String officeId)

	{
		String result = null;
		try {
			String distId="";
			ArrayList list = agmpdao.getDistrictId(officeId);
			logger.info("List Size"+list.size());
			ArrayList listTemp = new ArrayList();
			if(list!=null && list.size()>0){
			listTemp = (ArrayList) list.get(0);
			logger.info("InFirst IF");
			}
			if(listTemp!=null && listTemp.size()>0){
			distId = (String) listTemp.get(0);
			logger.info("InSecond IF");
			}
			logger.info("District ID"+distId);
			
			String a[] = new String[15];
			a[0] = CommonUtil.getConvertedDate(agmpdto.getTxtFromDate());
			a[1] = CommonUtil.getConvertedDate(agmpdto.getTxtToDate());
			a[2] = CommonUtil.getConvertedDate(agmpdto.getTxtAuditDate());
			a[3] = "O";
			a[4] = strUserId;// "DR/SR";
			a[5] = strUserId;
			a[6] = agmpdto.getTxtPhysicalId();
			a[7] = CommonUtil.getConvertedDate(agmpdto.getTxtDispatchDate());
			a[8] = agmpdto.getAuditBody();
			a[9] = agmpdto.getTxtComments();
			a[10] = distId;
			a[11] = agmpdto.getSroId();
			a[12] = strUserId;// "sro user";
			a[13] = CommonUtil.getConvertedDate(agmpdto.getTxtEntryDate());

			String auditFilePath = strFilePath;// listFilePaths.size()
			
			// auditFilePath[0]= strFilePath;
			int size = 0;
			if(listFileNames != null)
			{
			size = listFileNames.size();
			}
			
			String[] auditFileName = new String[size];

			


			
			for (int i = 0; i < size; i++) {
				UploadFileDTO uploadDTO = (UploadFileDTO) listFileNames.get(i);

				auditFileName[i] = uploadDTO.getFileName();
			}

			String fileDetail[] = new String[3];
			fileDetail[0] = "O";
			fileDetail[1] = strUserId;// "SR/DR";
			fileDetail[2] = strUserId;// "SR/DR";
			ArrayList para = agmpdao.getMaxParaId();
			ArrayList paraRow = (ArrayList) para.get(0);
			String paraTxn = (String) paraRow.get(0);

			//MODIFIED BY SHRUTI-3RD SEP 2013
			String paraDetails[] = new String[6];
			paraDetails[0] = paraTxn;
			paraDetails[1] = agmpdto.getListParaType();
			paraDetails[2] = agmpdto.getTextParaName();
			paraDetails[3] = agmpdto.getTextParaComments();
			//paraDetails[4] = "O";
			//modified by shruti-10th sep 2013
			paraDetails[4] = "OPEN";
			//end
			paraDetails[5] = agmpdto.getTextParaNumber();

			String objection[] = new String[docAgmpList.size()];
			String valAgmp[] = new String[docAgmpList.size()];
			String stampDuty[] = new String[docAgmpList.size()];
			String regFee[] = new String[docAgmpList.size()];
			String marketVal[]=new String[docAgmpList.size()];
			//added by shruti
			String objStatus[]=new String[docAgmpList.size()];
			String docId[]=new String[docAgmpList.size()];
			String caseId[]=new String[docAgmpList.size()];
			//end
			String agmpComm[] = new String[docAgmpList.size()];
			String comDetails[] = new String[2];
			comDetails[0] = agmpdto.getTxtAuditorName();
			comDetails[1] = agmpdto.getTxtAuditLocation();

			for (int i = 0; i < docAgmpList.size(); i++) 
			{
				if(agmpdto.getListParaType()!=null)
				{
						if(agmpdto.getListParaType().equalsIgnoreCase("documents")||agmpdto.getListParaType().equalsIgnoreCase("दस्तावेज़")||agmpdto.getListParaType().equalsIgnoreCase("P01"))
						{
							DocDetails docDetails = (DocDetails) docAgmpList.get(i);
							objection[i] = docDetails.getObjDetails();
							valAgmp[i] = docDetails.getValAgmp();
							stampDuty[i] = docDetails.getStampDuty();
							agmpComm[i] = docDetails.getTxtAgmpComm();
							regFee[i] = docDetails.getRegFee();
							docId[i]=docDetails.getTxtDocID();
							objStatus[i]=docDetails.getDocStatusId();
							caseId[i]=docDetails.getCaseDocID();
						}
						else if(agmpdto.getListParaType().equalsIgnoreCase("Pending Cases")||agmpdto.getListParaType().equalsIgnoreCase("अनिर्णीत मामले")||agmpdto.getListParaType().equalsIgnoreCase("P02"))
						{
							CaseDetails caseDetails = (CaseDetails) docAgmpList.get(i);
							objection[i] = caseDetails.getObjDetails();
							valAgmp[i] = "";
							stampDuty[i] = "";
							agmpComm[i] = caseDetails.getTxtAgmpComm();
							regFee[i] = "";
							docId[i]="";
							objStatus[i]=caseDetails.getCaseStatus();
							caseId[i]=caseDetails.getTxtCaseID();
						}
						else if(agmpdto.getListParaType().equalsIgnoreCase("Miscellaneous")||agmpdto.getListParaType().equalsIgnoreCase("विविध")||agmpdto.getListParaType().equalsIgnoreCase("P03"))
						{
							DocDetails docDetails = (DocDetails) docAgmpList.get(i);
							objection[i] = docDetails.getObjDetails();
							valAgmp[i] = "";
							stampDuty[i] = "";
							agmpComm[i] = docDetails.getTxtAgmpComm();
							regFee[i] = "";
							docId[i]="";
							objStatus[i]="OPEN";
							caseId[i]="";
						}
				}
				else
				{
					DocDetails docDetails = (DocDetails) docAgmpList.get(i);
					objection[i] = docDetails.getObjDetails();
					valAgmp[i] = docDetails.getValAgmp();
					stampDuty[i] = docDetails.getStampDuty();
					agmpComm[i] = docDetails.getTxtAgmpComm();
					regFee[i] = docDetails.getRegFee();
					docId[i]=docDetails.getTxtDocID();
					objStatus[i]=docDetails.getDocStatusId();
					caseId[i]=docDetails.getCaseDocID();
					
				/*DocDetails docDetails = (DocDetails) docAgmpList.get(i);
				objection[i] = docDetails.getObjDetails();
				valAgmp[i] = docDetails.getValAgmp();
				stampDuty[i] = docDetails.getStampDuty();
				agmpComm[i] = docDetails.getTxtAgmpComm();
				regFee[i] = docDetails.getRegFee();*/
				}
				
			}

			ArrayList listAddDetails = new ArrayList();
			listAddDetails.add(a);
			listAddDetails.add(auditFilePath);
			listAddDetails.add(auditFileName);
			listAddDetails.add(fileDetail);
			listAddDetails.add(paraDetails);
			listAddDetails.add(objection);
			listAddDetails.add(valAgmp);
			listAddDetails.add(stampDuty);
			listAddDetails.add(regFee);
			listAddDetails.add(marketVal);
			//added by shruti
			listAddDetails.add(docId);
			listAddDetails.add(objStatus);
			listAddDetails.add(caseId);
			//end
			
			listAddDetails.add(agmpComm);
			listAddDetails.add(comDetails);
			//added by shruti
			//listAddDetails.add();

			result = agmpdao.submitAGMPReport(listAddDetails);

		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return result;
	}

	
	public String submitInternalReport(AGMPReportDetailsDTO agmpdto,
			ArrayList listFileNames, String strFilePath, ArrayList docAgmpList,String strUserId,
			String officeId)

	{
		String result = null;
		try {
			String distId="";
			ArrayList list = agmpdao.getDistrictId(officeId);
			logger.info("List Size"+list.size());
			ArrayList listTemp = new ArrayList();
			if(list!=null && list.size()>0){
			listTemp = (ArrayList) list.get(0);
			logger.info("InFirst IF");
			}
			if(listTemp!=null && listTemp.size()>0){
			distId = (String) listTemp.get(0);
			logger.info("InSecond IF");
			}
			logger.info("District ID"+distId);
			
			String a[] = new String[15];
			a[0] = CommonUtil.getConvertedDate(agmpdto.getTxtFromDate());
			a[1] = CommonUtil.getConvertedDate(agmpdto.getTxtToDate());
			a[2] = CommonUtil.getConvertedDate(agmpdto.getTxtAuditDate());
			a[3] = "O";
			a[4] = strUserId;// "DR/SR";
			a[5] = strUserId;
			a[6] = agmpdto.getTxtPhysicalId();
			a[7] = CommonUtil.getConvertedDate(agmpdto.getTxtDispatchDate());
			a[8] = agmpdto.getAuditBody();
			a[9] = agmpdto.getTxtComments();
			a[10] = distId;
			a[11] = agmpdto.getSroId();
			a[12] = strUserId;// "sro user";
			a[13] = CommonUtil.getConvertedDate(agmpdto.getTxtEntryDate());

			String auditFilePath = strFilePath;// listFilePaths.size()
			
			// auditFilePath[0]= strFilePath;
			int size = 0;
			if(listFileNames != null)
			{
			size = listFileNames.size();
			}
			
			String[] auditFileName = new String[size];

			
			
			for (int i = 0; i < size; i++) {
				UploadFileDTO uploadDTO = (UploadFileDTO) listFileNames.get(i);

				auditFileName[i] = uploadDTO.getFileName();
			}

			String fileDetail[] = new String[3];
			fileDetail[0] = "O";
			fileDetail[1] = strUserId;// "SR/DR";
			fileDetail[2] = strUserId;// "SR/DR";
			ArrayList para = agmpdao.getMaxParaId();
			ArrayList paraRow = (ArrayList) para.get(0);
			String paraTxn = (String) paraRow.get(0);

			//MODIFIED BY SHRUTI-3RD SEP 2013
			String paraDetails[] = new String[6];
			paraDetails[0] = paraTxn;
			paraDetails[1] = agmpdto.getListParaType();
			paraDetails[2] = agmpdto.getTextParaName();
			paraDetails[3] = agmpdto.getTextParaComments();
			//paraDetails[4] = "O";
			//modified by shruti-10th sep 2013
			paraDetails[4] = "OPEN";
			//end
			paraDetails[5] = agmpdto.getTextParaNumber();

			String objection[] = new String[docAgmpList.size()];
			String valAgmp[] = new String[docAgmpList.size()];
			String stampDuty[] = new String[docAgmpList.size()];
			String regFee[] = new String[docAgmpList.size()];
			String marketVal[]=new String[docAgmpList.size()];
			//added by shruti
			String objStatus[]=new String[docAgmpList.size()];
			String docId[]=new String[docAgmpList.size()];
			String caseId[]=new String[docAgmpList.size()];
			//end
			String agmpComm[] = new String[docAgmpList.size()];
			String comDetails[] = new String[2];
			comDetails[0] = agmpdto.getTxtAuditorName();
			comDetails[1] = agmpdto.getTxtAuditLocation();

			for (int i = 0; i < docAgmpList.size(); i++) 
			{
				if(agmpdto.getListParaType()!=null)
				{
						if(agmpdto.getListParaType().equalsIgnoreCase("documents")||agmpdto.getListParaType().equalsIgnoreCase("दस्तावेज़")||agmpdto.getListParaType().equalsIgnoreCase("P01"))
						{
							DocDetails docDetails = (DocDetails) docAgmpList.get(i);
							objection[i] = docDetails.getObjDetails();
							valAgmp[i] = docDetails.getValAgmp();
							stampDuty[i] = docDetails.getStampDuty();
							agmpComm[i] = docDetails.getTxtAgmpComm();
							regFee[i] = docDetails.getRegFee();
							docId[i]=docDetails.getTxtDocID();
							objStatus[i]=docDetails.getDocStatusId();
							caseId[i]=docDetails.getCaseDocID();
						}
						else if(agmpdto.getListParaType().equalsIgnoreCase("Pending Cases")||agmpdto.getListParaType().equalsIgnoreCase("अनिर्णीत मामले")||agmpdto.getListParaType().equalsIgnoreCase("P02"))
						{
							CaseDetails caseDetails = (CaseDetails) docAgmpList.get(i);
							objection[i] = caseDetails.getObjDetails();
							valAgmp[i] = "";
							stampDuty[i] = "";
							agmpComm[i] = caseDetails.getTxtAgmpComm();
							regFee[i] = "";
							docId[i]="";
							objStatus[i]=caseDetails.getCaseStatus();
							caseId[i]=caseDetails.getTxtCaseID();
						}
						else if(agmpdto.getListParaType().equalsIgnoreCase("Miscellaneous")||agmpdto.getListParaType().equalsIgnoreCase("विविध")||agmpdto.getListParaType().equalsIgnoreCase("P03"))
						{
							DocDetails docDetails = (DocDetails) docAgmpList.get(i);
							objection[i] = docDetails.getObjDetails();
							valAgmp[i] = "";
							stampDuty[i] = "";
							agmpComm[i] = docDetails.getTxtAgmpComm();
							regFee[i] = "";
							docId[i]="";
							objStatus[i]="OPEN";
							caseId[i]="";
						}
				}
				else
				{
					DocDetails docDetails = (DocDetails) docAgmpList.get(i);
					objection[i] = docDetails.getObjDetails();
					valAgmp[i] = docDetails.getValAgmp();
					stampDuty[i] = docDetails.getStampDuty();
					agmpComm[i] = docDetails.getTxtAgmpComm();
					regFee[i] = docDetails.getRegFee();
					docId[i]=docDetails.getTxtDocID();
					objStatus[i]=docDetails.getDocStatusId();
					caseId[i]=docDetails.getCaseDocID();
					
				/*DocDetails docDetails = (DocDetails) docAgmpList.get(i);
				objection[i] = docDetails.getObjDetails();
				valAgmp[i] = docDetails.getValAgmp();
				stampDuty[i] = docDetails.getStampDuty();
				agmpComm[i] = docDetails.getTxtAgmpComm();
				regFee[i] = docDetails.getRegFee();*/
				}
				
			}

			ArrayList listAddDetails = new ArrayList();
			listAddDetails.add(a);
			listAddDetails.add(auditFilePath);
			listAddDetails.add(auditFileName);
			listAddDetails.add(fileDetail);
			listAddDetails.add(paraDetails);
			listAddDetails.add(objection);
			listAddDetails.add(valAgmp);
			listAddDetails.add(stampDuty);
			listAddDetails.add(regFee);
			listAddDetails.add(marketVal);
			//added by shruti
			listAddDetails.add(docId);
			listAddDetails.add(objStatus);
			listAddDetails.add(caseId);
			//end
			
			listAddDetails.add(agmpComm);
			listAddDetails.add(comDetails);
			//added by shruti
			//listAddDetails.add();

			result = agmpdao.submitInternalReport(listAddDetails);

		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return result;
	}

	public String submitInternalExpReport(AGMPReportDetailsDTO agmpdto,
			ArrayList listFileNames, String strFilePath, ArrayList docAgmpList,String strUserId,
			String officeId)

	{
		String result = null;
		try {
			
			
			String distId="";
			/*ArrayList list = agmpdao.getDistrictId(officeId);
			logger.info("List Size"+list.size());
			ArrayList listTemp = new ArrayList();
			if(list!=null && list.size()>0){
			listTemp = (ArrayList) list.get(0);
			logger.info("InFirst IF");
			}
			if(listTemp!=null && listTemp.size()>0){
			distId = (String) listTemp.get(0);
			logger.info("InSecond IF");
			}
			logger.info("District ID"+distId);*/
			distId=agmpdao.getDistId(officeId);
			
			
			
			String a[] = new String[15];
			a[0] = CommonUtil.getConvertedDate(agmpdto.getTxtFromDate());
			a[1] = CommonUtil.getConvertedDate(agmpdto.getTxtToDate());
			a[2] = CommonUtil.getConvertedDate(agmpdto.getTxtAuditDate());
			a[3] = "O";
			a[4] = strUserId;// "DR/SR";
			a[5] = strUserId;
			a[6] = agmpdto.getTxtPhysicalId();
			a[7] = CommonUtil.getConvertedDate(agmpdto.getTxtDispatchDate());
			a[8] = agmpdto.getAuditBody();
			a[9] = agmpdto.getTxtComments();
			a[10] = distId;
			a[11] = agmpdto.getSroId();
			a[12] = strUserId;// "sro user";
			a[13] = CommonUtil.getConvertedDate(agmpdto.getTxtEntryDate());

			String auditFilePath = strFilePath;// listFilePaths.size()
			
			// auditFilePath[0]= strFilePath;
			int size = 0;
			if(listFileNames != null)
			{
			size = listFileNames.size();
			}
			
			String[] auditFileName = new String[size];

			
			
			for (int i = 0; i < size; i++) {
				UploadFileDTO uploadDTO = (UploadFileDTO) listFileNames.get(i);

				auditFileName[i] = uploadDTO.getFileName();
			}

			String fileDetail[] = new String[3];
			fileDetail[0] = "O";
			fileDetail[1] = strUserId;// "SR/DR";
			fileDetail[2] = strUserId;// "SR/DR";
			ArrayList para = agmpdao.getMaxParaId();
			ArrayList paraRow = (ArrayList) para.get(0);
			String paraTxn = (String) paraRow.get(0);

			//MODIFIED BY SHRUTI-3RD SEP 2013
			String paraDetails[] = new String[6];
			paraDetails[0] = paraTxn;
			paraDetails[1] = agmpdto.getListParaType();
			paraDetails[2] = agmpdto.getTextParaName();
			paraDetails[3] = agmpdto.getTextParaComments();
			//paraDetails[4] = "O";
			//modified by shruti-10th sep 2013
			paraDetails[4] = "OPEN";
			//end
			paraDetails[5] = agmpdto.getTextParaNumber();

			String objection[] = new String[docAgmpList.size()];
			String valAgmp[] = new String[docAgmpList.size()];
			String stampDuty[] = new String[docAgmpList.size()];
			String regFee[] = new String[docAgmpList.size()];
			String marketVal[]=new String[docAgmpList.size()];
			//added by shruti
			String objStatus[]=new String[docAgmpList.size()];
			String docId[]=new String[docAgmpList.size()];
			String caseId[]=new String[docAgmpList.size()];
			//end
			String agmpComm[] = new String[docAgmpList.size()];
			String comDetails[] = new String[2];
			comDetails[0] = agmpdto.getTxtAuditorName();
			comDetails[1] = agmpdto.getTxtAuditLocation();

			for (int i = 0; i < docAgmpList.size(); i++) 
			{
				if(agmpdto.getListParaType()!=null)
				{
						if(agmpdto.getListParaType().equalsIgnoreCase("documents")||agmpdto.getListParaType().equalsIgnoreCase("दस्तावेज़")||agmpdto.getListParaType().equalsIgnoreCase("P01"))
						{
							DocDetails docDetails = (DocDetails) docAgmpList.get(i);
							objection[i] = docDetails.getObjDetails();
							valAgmp[i] = docDetails.getValAgmp();
							stampDuty[i] = docDetails.getStampDuty();
							agmpComm[i] = docDetails.getTxtAgmpComm();
							regFee[i] = docDetails.getRegFee();
							docId[i]=docDetails.getTxtDocID();
							objStatus[i]=docDetails.getDocStatusId();
							caseId[i]=docDetails.getCaseDocID();
						}
						else if(agmpdto.getListParaType().equalsIgnoreCase("Pending Cases")||agmpdto.getListParaType().equalsIgnoreCase("अनिर्णीत मामले")||agmpdto.getListParaType().equalsIgnoreCase("P02"))
						{
							CaseDetails caseDetails = (CaseDetails) docAgmpList.get(i);
							objection[i] = caseDetails.getObjDetails();
							valAgmp[i] = "";
							stampDuty[i] = "";
							agmpComm[i] = caseDetails.getTxtAgmpComm();
							regFee[i] = "";
							docId[i]="";
							objStatus[i]=caseDetails.getCaseStatus();
							caseId[i]=caseDetails.getTxtCaseID();
						}
						else if(agmpdto.getListParaType().equalsIgnoreCase("Miscellaneous")||agmpdto.getListParaType().equalsIgnoreCase("विविध")||agmpdto.getListParaType().equalsIgnoreCase("P03"))
						{
							DocDetails docDetails = (DocDetails) docAgmpList.get(i);
							objection[i] = docDetails.getObjDetails();
							valAgmp[i] = "";
							stampDuty[i] = "";
							agmpComm[i] = docDetails.getTxtAgmpComm();
							regFee[i] = "";
							docId[i]="";
							objStatus[i]="OPEN";
							caseId[i]="";
						}
				}
				else
				{
					DocDetails docDetails = (DocDetails) docAgmpList.get(i);
					objection[i] = docDetails.getObjDetails();
					valAgmp[i] = docDetails.getValAgmp();
					stampDuty[i] = docDetails.getStampDuty();
					agmpComm[i] = docDetails.getTxtAgmpComm();
					regFee[i] = docDetails.getRegFee();
					docId[i]=docDetails.getTxtDocID();
					objStatus[i]=docDetails.getDocStatusId();
					caseId[i]=docDetails.getCaseDocID();
					
				/*DocDetails docDetails = (DocDetails) docAgmpList.get(i);
				objection[i] = docDetails.getObjDetails();
				valAgmp[i] = docDetails.getValAgmp();
				stampDuty[i] = docDetails.getStampDuty();
				agmpComm[i] = docDetails.getTxtAgmpComm();
				regFee[i] = docDetails.getRegFee();*/
				}
				
			}

			ArrayList listAddDetails = new ArrayList();
			listAddDetails.add(a);
			listAddDetails.add(auditFilePath);
			listAddDetails.add(auditFileName);
			listAddDetails.add(fileDetail);
			listAddDetails.add(paraDetails);
			listAddDetails.add(objection);
			listAddDetails.add(valAgmp);
			listAddDetails.add(stampDuty);
			listAddDetails.add(regFee);
			listAddDetails.add(marketVal);
			//added by shruti
			listAddDetails.add(docId);
			listAddDetails.add(objStatus);
			listAddDetails.add(caseId);
			//end
			
			listAddDetails.add(agmpComm);
			listAddDetails.add(comDetails);
			//added by shruti
			//listAddDetails.add();

			result = agmpdao.submitInternalExpReport(listAddDetails);

		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return result;
	}

	public String submitAGMPRcptReport(AGMPReportDetailsDTO agmpdto,
			ArrayList listFileNames, String strFilePath, ArrayList docAgmpList,String strUserId,
			String officeId)

	{
		String result = null;
		try {
			String distId="";
			distId=agmpdao.getDistId(officeId);
			
			String a[] = new String[15];
			a[0] = CommonUtil.getConvertedDate(agmpdto.getTxtFromDate());
			a[1] = CommonUtil.getConvertedDate(agmpdto.getTxtToDate());
			a[2] = CommonUtil.getConvertedDate(agmpdto.getTxtAuditDate());
			a[3] = "O";
			a[4] = strUserId;// "DR/SR";
			a[5] = strUserId;
			a[6] = agmpdto.getTxtPhysicalId();
			a[7] = CommonUtil.getConvertedDate(agmpdto.getTxtDispatchDate());
			a[8] = agmpdto.getAuditBody();
			a[9] = agmpdto.getTxtComments();
			a[10] = distId;
			a[11] = agmpdto.getSroId();
			a[12] = strUserId;// "sro user";
			a[13] = CommonUtil.getConvertedDate(agmpdto.getTxtEntryDate());

			String auditFilePath = strFilePath;// listFilePaths.size()
			
			// auditFilePath[0]= strFilePath;
			int size = 0;
			if(listFileNames != null)
			{
			size = listFileNames.size();
			}
			
			String[] auditFileName = new String[size];

			
			
			for (int i = 0; i < size; i++) {
				UploadFileDTO uploadDTO = (UploadFileDTO) listFileNames.get(i);

				auditFileName[i] = uploadDTO.getFileName();
			}

			String fileDetail[] = new String[3];
			fileDetail[0] = "O";
			fileDetail[1] = strUserId;// "SR/DR";
			fileDetail[2] = strUserId;// "SR/DR";
			ArrayList para = agmpdao.getMaxParaId();
			ArrayList paraRow = (ArrayList) para.get(0);
			String paraTxn = (String) paraRow.get(0);

			//MODIFIED BY SHRUTI-3RD SEP 2013
			String paraDetails[] = new String[6];
			paraDetails[0] = paraTxn;
			paraDetails[1] = agmpdto.getListParaType();
			paraDetails[2] = agmpdto.getTextParaName();
			paraDetails[3] = agmpdto.getTextParaComments();
			//paraDetails[4] = "O";
			//modified by shruti-10th sep 2013
			paraDetails[4] = "OPEN";
			//end
			paraDetails[5] = agmpdto.getTextParaNumber();

			String objection[] = new String[docAgmpList.size()];
			String valAgmp[] = new String[docAgmpList.size()];
			String stampDuty[] = new String[docAgmpList.size()];
			String regFee[] = new String[docAgmpList.size()];
			String marketVal[]=new String[docAgmpList.size()];
			//added by shruti
			String objStatus[]=new String[docAgmpList.size()];
			String docId[]=new String[docAgmpList.size()];
			String caseId[]=new String[docAgmpList.size()];
			//end
			String agmpComm[] = new String[docAgmpList.size()];
			String comDetails[] = new String[2];
			comDetails[0] = agmpdto.getTxtAuditorName();
			comDetails[1] = agmpdto.getTxtAuditLocation();

			for (int i = 0; i < docAgmpList.size(); i++) 
			{
				if(agmpdto.getListParaType()!=null)
				{
						if(agmpdto.getListParaType().equalsIgnoreCase("documents")||agmpdto.getListParaType().equalsIgnoreCase("दस्तावेज़")||agmpdto.getListParaType().equalsIgnoreCase("P01"))
						{
							DocDetails docDetails = (DocDetails) docAgmpList.get(i);
							objection[i] = docDetails.getObjDetails();
							valAgmp[i] = docDetails.getValAgmp();
							stampDuty[i] = docDetails.getStampDuty();
							agmpComm[i] = docDetails.getTxtAgmpComm();
							regFee[i] = docDetails.getRegFee();
							docId[i]=docDetails.getTxtDocID();
							objStatus[i]=docDetails.getDocStatusId();
							caseId[i]=docDetails.getCaseDocID();
						}
						else if(agmpdto.getListParaType().equalsIgnoreCase("Pending Cases")||agmpdto.getListParaType().equalsIgnoreCase("अनिर्णीत मामले")||agmpdto.getListParaType().equalsIgnoreCase("P02"))
						{
							CaseDetails caseDetails = (CaseDetails) docAgmpList.get(i);
							objection[i] = caseDetails.getObjDetails();
							valAgmp[i] = "";
							stampDuty[i] = "";
							agmpComm[i] = caseDetails.getTxtAgmpComm();
							regFee[i] = "";
							docId[i]="";
							objStatus[i]=caseDetails.getCaseStatus();
							caseId[i]=caseDetails.getTxtCaseID();
						}
						else if(agmpdto.getListParaType().equalsIgnoreCase("Miscellaneous")||agmpdto.getListParaType().equalsIgnoreCase("विविध")||agmpdto.getListParaType().equalsIgnoreCase("P03"))
						{
							DocDetails docDetails = (DocDetails) docAgmpList.get(i);
							objection[i] = docDetails.getObjDetails();
							valAgmp[i] = "";
							stampDuty[i] = "";
							agmpComm[i] = docDetails.getTxtAgmpComm();
							regFee[i] = "";
							docId[i]="";
							objStatus[i]="OPEN";
							caseId[i]="";
						}
				}
				else
				{
					DocDetails docDetails = (DocDetails) docAgmpList.get(i);
					objection[i] = docDetails.getObjDetails();
					valAgmp[i] = docDetails.getValAgmp();
					stampDuty[i] = docDetails.getStampDuty();
					agmpComm[i] = docDetails.getTxtAgmpComm();
					regFee[i] = docDetails.getRegFee();
					docId[i]=docDetails.getTxtDocID();
					objStatus[i]=docDetails.getDocStatusId();
					caseId[i]=docDetails.getCaseDocID();
					
				/*DocDetails docDetails = (DocDetails) docAgmpList.get(i);
				objection[i] = docDetails.getObjDetails();
				valAgmp[i] = docDetails.getValAgmp();
				stampDuty[i] = docDetails.getStampDuty();
				agmpComm[i] = docDetails.getTxtAgmpComm();
				regFee[i] = docDetails.getRegFee();*/
				}
				
			}

			ArrayList listAddDetails = new ArrayList();
			listAddDetails.add(a);
			listAddDetails.add(auditFilePath);
			listAddDetails.add(auditFileName);
			listAddDetails.add(fileDetail);
			listAddDetails.add(paraDetails);
			listAddDetails.add(objection);
			listAddDetails.add(valAgmp);
			listAddDetails.add(stampDuty);
			listAddDetails.add(regFee);
			listAddDetails.add(marketVal);
			//added by shruti
			listAddDetails.add(docId);
			listAddDetails.add(objStatus);
			listAddDetails.add(caseId);
			//end
			
			listAddDetails.add(agmpComm);
			listAddDetails.add(comDetails);
			//added by shruti
			//listAddDetails.add();

			result = agmpdao.submitAGMPRcptReport(listAddDetails);

		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return result;
	}

	public String submitAGMPExpReport(AGMPReportDetailsDTO agmpdto,
			ArrayList listFileNames, String strFilePath, ArrayList docAgmpList,String strUserId,
			String officeId)

	{
		String result = null;
		try {
			String distId="";
			distId=agmpdao.getDistId(officeId);
			
			String a[] = new String[15];
			a[0] = CommonUtil.getConvertedDate(agmpdto.getTxtFromDate());
			a[1] = CommonUtil.getConvertedDate(agmpdto.getTxtToDate());
			a[2] = CommonUtil.getConvertedDate(agmpdto.getTxtAuditDate());
			a[3] = "O";
			a[4] = strUserId;// "DR/SR";
			a[5] = strUserId;
			a[6] = agmpdto.getTxtPhysicalId();
			a[7] = CommonUtil.getConvertedDate(agmpdto.getTxtDispatchDate());
			a[8] = agmpdto.getAuditBody();
			a[9] = agmpdto.getTxtComments();
			a[10] = distId;
			a[11] = agmpdto.getSroId();
			a[12] = strUserId;// "sro user";
			a[13] = CommonUtil.getConvertedDate(agmpdto.getTxtEntryDate());

			String auditFilePath = strFilePath;// listFilePaths.size()
			
			// auditFilePath[0]= strFilePath;
			int size = 0;
			if(listFileNames != null)
			{
			size = listFileNames.size();
			}
			
			String[] auditFileName = new String[size];

			
			
			for (int i = 0; i < size; i++) {
				UploadFileDTO uploadDTO = (UploadFileDTO) listFileNames.get(i);

				auditFileName[i] = uploadDTO.getFileName();
			}

			String fileDetail[] = new String[3];
			fileDetail[0] = "O";
			fileDetail[1] = strUserId;// "SR/DR";
			fileDetail[2] = strUserId;// "SR/DR";
			ArrayList para = agmpdao.getMaxParaId();
			ArrayList paraRow = (ArrayList) para.get(0);
			String paraTxn = (String) paraRow.get(0);

			//MODIFIED BY SHRUTI-3RD SEP 2013
			String paraDetails[] = new String[6];
			paraDetails[0] = paraTxn;
			paraDetails[1] = agmpdto.getListParaType();
			paraDetails[2] = agmpdto.getTextParaName();
			paraDetails[3] = agmpdto.getTextParaComments();
			//paraDetails[4] = "O";
			//modified by shruti-10th sep 2013
			paraDetails[4] = "OPEN";
			//end
			paraDetails[5] = agmpdto.getTextParaNumber();

			String objection[] = new String[docAgmpList.size()];
			String valAgmp[] = new String[docAgmpList.size()];
			String stampDuty[] = new String[docAgmpList.size()];
			String regFee[] = new String[docAgmpList.size()];
			String marketVal[]=new String[docAgmpList.size()];
			//added by shruti
			String objStatus[]=new String[docAgmpList.size()];
			String docId[]=new String[docAgmpList.size()];
			String caseId[]=new String[docAgmpList.size()];
			//end
			String agmpComm[] = new String[docAgmpList.size()];
			String comDetails[] = new String[2];
			comDetails[0] = agmpdto.getTxtAuditorName();
			comDetails[1] = agmpdto.getTxtAuditLocation();

			for (int i = 0; i < docAgmpList.size(); i++) 
			{
				if(agmpdto.getListParaType()!=null)
				{
						if(agmpdto.getListParaType().equalsIgnoreCase("documents")||agmpdto.getListParaType().equalsIgnoreCase("दस्तावेज़")||agmpdto.getListParaType().equalsIgnoreCase("P01"))
						{
							DocDetails docDetails = (DocDetails) docAgmpList.get(i);
							objection[i] = docDetails.getObjDetails();
							valAgmp[i] = docDetails.getValAgmp();
							stampDuty[i] = docDetails.getStampDuty();
							agmpComm[i] = docDetails.getTxtAgmpComm();
							regFee[i] = docDetails.getRegFee();
							docId[i]=docDetails.getTxtDocID();
							objStatus[i]=docDetails.getDocStatusId();
							caseId[i]=docDetails.getCaseDocID();
						}
						else if(agmpdto.getListParaType().equalsIgnoreCase("Pending Cases")||agmpdto.getListParaType().equalsIgnoreCase("अनिर्णीत मामले")||agmpdto.getListParaType().equalsIgnoreCase("P02"))
						{
							CaseDetails caseDetails = (CaseDetails) docAgmpList.get(i);
							objection[i] = caseDetails.getObjDetails();
							valAgmp[i] = "";
							stampDuty[i] = "";
							agmpComm[i] = caseDetails.getTxtAgmpComm();
							regFee[i] = "";
							docId[i]="";
							objStatus[i]=caseDetails.getCaseStatus();
							caseId[i]=caseDetails.getTxtCaseID();
						}
						else if(agmpdto.getListParaType().equalsIgnoreCase("Miscellaneous")||agmpdto.getListParaType().equalsIgnoreCase("विविध")||agmpdto.getListParaType().equalsIgnoreCase("P03"))
						{
							DocDetails docDetails = (DocDetails) docAgmpList.get(i);
							objection[i] = docDetails.getObjDetails();
							valAgmp[i] = "";
							stampDuty[i] = "";
							agmpComm[i] = docDetails.getTxtAgmpComm();
							regFee[i] = "";
							docId[i]="";
							objStatus[i]="OPEN";
							caseId[i]="";
						}
				}
				else
				{
					DocDetails docDetails = (DocDetails) docAgmpList.get(i);
					objection[i] = docDetails.getObjDetails();
					valAgmp[i] = docDetails.getValAgmp();
					stampDuty[i] = docDetails.getStampDuty();
					agmpComm[i] = docDetails.getTxtAgmpComm();
					regFee[i] = docDetails.getRegFee();
					docId[i]=docDetails.getTxtDocID();
					objStatus[i]=docDetails.getDocStatusId();
					caseId[i]=docDetails.getCaseDocID();
					
				/*DocDetails docDetails = (DocDetails) docAgmpList.get(i);
				objection[i] = docDetails.getObjDetails();
				valAgmp[i] = docDetails.getValAgmp();
				stampDuty[i] = docDetails.getStampDuty();
				agmpComm[i] = docDetails.getTxtAgmpComm();
				regFee[i] = docDetails.getRegFee();*/
				}
				
			}

			ArrayList listAddDetails = new ArrayList();
			listAddDetails.add(a);
			listAddDetails.add(auditFilePath);
			listAddDetails.add(auditFileName);
			listAddDetails.add(fileDetail);
			listAddDetails.add(paraDetails);
			listAddDetails.add(objection);
			listAddDetails.add(valAgmp);
			listAddDetails.add(stampDuty);
			listAddDetails.add(regFee);
			listAddDetails.add(marketVal);
			//added by shruti
			listAddDetails.add(docId);
			listAddDetails.add(objStatus);
			listAddDetails.add(caseId);
			//end
			
			listAddDetails.add(agmpComm);
			listAddDetails.add(comDetails);
			//added by shruti
			//listAddDetails.add();

			result = agmpdao.submitAGMPExpReport(listAddDetails);

		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return result;
	}

	public String submitInternalRcptReport(AGMPReportDetailsDTO agmpdto,
			ArrayList listFileNames, String strFilePath, ArrayList docAgmpList,String strUserId,
			String officeId)

	{
		String result = null;
		try {
			
			
			String distId="";
			/*ArrayList list = agmpdao.getDistrictId(officeId);
			logger.info("List Size"+list.size());
			ArrayList listTemp = new ArrayList();
			if(list!=null && list.size()>0){
			listTemp = (ArrayList) list.get(0);
			logger.info("InFirst IF");
			}
			if(listTemp!=null && listTemp.size()>0){
			distId = (String) listTemp.get(0);
			logger.info("InSecond IF");
			}
			logger.info("District ID"+distId);*/
			distId=agmpdao.getDistId(officeId);
			
			
			
			String a[] = new String[15];
			a[0] = CommonUtil.getConvertedDate(agmpdto.getTxtFromDate());
			a[1] = CommonUtil.getConvertedDate(agmpdto.getTxtToDate());
			a[2] = CommonUtil.getConvertedDate(agmpdto.getTxtAuditDate());
			a[3] = "O";
			a[4] = strUserId;// "DR/SR";
			a[5] = strUserId;
			a[6] = agmpdto.getTxtPhysicalId();
			a[7] = CommonUtil.getConvertedDate(agmpdto.getTxtDispatchDate());
			a[8] = agmpdto.getAuditBody();
			a[9] = agmpdto.getTxtComments();
			a[10] = distId;
			a[11] = agmpdto.getSroId();
			a[12] = strUserId;// "sro user";
			a[13] = CommonUtil.getConvertedDate(agmpdto.getTxtEntryDate());

			String auditFilePath = strFilePath;// listFilePaths.size()
			int size = 0;
			if(listFileNames != null)
			{
			size = listFileNames.size();
			}
			
			String[] auditFileName = new String[size];
			// auditFilePath[0]= strFilePath;
			
			
			for (int i = 0; i < size; i++) {
				UploadFileDTO uploadDTO = (UploadFileDTO) listFileNames.get(i);

				auditFileName[i] = uploadDTO.getFileName();
			}

			String fileDetail[] = new String[3];
			fileDetail[0] = "O";
			fileDetail[1] = strUserId;// "SR/DR";
			fileDetail[2] = strUserId;// "SR/DR";
			ArrayList para = agmpdao.getMaxParaId();
			ArrayList paraRow = (ArrayList) para.get(0);
			String paraTxn = (String) paraRow.get(0);

			//MODIFIED BY SHRUTI-3RD SEP 2013
			String paraDetails[] = new String[6];
			paraDetails[0] = paraTxn;
			paraDetails[1] = agmpdto.getListParaType();
			paraDetails[2] = agmpdto.getTextParaName();
			paraDetails[3] = agmpdto.getTextParaComments();
			//paraDetails[4] = "O";
			//modified by shruti-10th sep 2013
			paraDetails[4] = "OPEN";
			//end
			paraDetails[5] = agmpdto.getTextParaNumber();

			String objection[] = new String[docAgmpList.size()];
			String valAgmp[] = new String[docAgmpList.size()];
			String stampDuty[] = new String[docAgmpList.size()];
			String regFee[] = new String[docAgmpList.size()];
			String marketVal[]=new String[docAgmpList.size()];
			//added by shruti
			String objStatus[]=new String[docAgmpList.size()];
			String docId[]=new String[docAgmpList.size()];
			String caseId[]=new String[docAgmpList.size()];
			//end
			String agmpComm[] = new String[docAgmpList.size()];
			String comDetails[] = new String[2];
			comDetails[0] = agmpdto.getTxtAuditorName();
			comDetails[1] = agmpdto.getTxtAuditLocation();

			for (int i = 0; i < docAgmpList.size(); i++) 
			{
				if(agmpdto.getListParaType()!=null)
				{
						if(agmpdto.getListParaType().equalsIgnoreCase("documents")||agmpdto.getListParaType().equalsIgnoreCase("दस्तावेज़")||agmpdto.getListParaType().equalsIgnoreCase("P01"))
						{
							DocDetails docDetails = (DocDetails) docAgmpList.get(i);
							objection[i] = docDetails.getObjDetails();
							valAgmp[i] = docDetails.getValAgmp();
							stampDuty[i] = docDetails.getStampDuty();
							agmpComm[i] = docDetails.getTxtAgmpComm();
							regFee[i] = docDetails.getRegFee();
							docId[i]=docDetails.getTxtDocID();
							objStatus[i]=docDetails.getDocStatusId();
							caseId[i]=docDetails.getCaseDocID();
						}
						else if(agmpdto.getListParaType().equalsIgnoreCase("Pending Cases")||agmpdto.getListParaType().equalsIgnoreCase("अनिर्णीत मामले")||agmpdto.getListParaType().equalsIgnoreCase("P02"))
						{
							CaseDetails caseDetails = (CaseDetails) docAgmpList.get(i);
							objection[i] = caseDetails.getObjDetails();
							valAgmp[i] = "";
							stampDuty[i] = "";
							agmpComm[i] = caseDetails.getTxtAgmpComm();
							regFee[i] = "";
							docId[i]="";
							objStatus[i]=caseDetails.getCaseStatus();
							caseId[i]=caseDetails.getTxtCaseID();
						}
						else if(agmpdto.getListParaType().equalsIgnoreCase("Miscellaneous")||agmpdto.getListParaType().equalsIgnoreCase("विविध")||agmpdto.getListParaType().equalsIgnoreCase("P03"))
						{
							DocDetails docDetails = (DocDetails) docAgmpList.get(i);
							objection[i] = docDetails.getObjDetails();
							valAgmp[i] = "";
							stampDuty[i] = "";
							agmpComm[i] = docDetails.getTxtAgmpComm();
							regFee[i] = "";
							docId[i]="";
							objStatus[i]="OPEN";
							caseId[i]="";
						}
				}
				else
				{
					DocDetails docDetails = (DocDetails) docAgmpList.get(i);
					objection[i] = docDetails.getObjDetails();
					valAgmp[i] = docDetails.getValAgmp();
					stampDuty[i] = docDetails.getStampDuty();
					agmpComm[i] = docDetails.getTxtAgmpComm();
					regFee[i] = docDetails.getRegFee();
					docId[i]=docDetails.getTxtDocID();
					objStatus[i]=docDetails.getDocStatusId();
					caseId[i]=docDetails.getCaseDocID();
					
				/*DocDetails docDetails = (DocDetails) docAgmpList.get(i);
				objection[i] = docDetails.getObjDetails();
				valAgmp[i] = docDetails.getValAgmp();
				stampDuty[i] = docDetails.getStampDuty();
				agmpComm[i] = docDetails.getTxtAgmpComm();
				regFee[i] = docDetails.getRegFee();*/
				}
				
			}

			ArrayList listAddDetails = new ArrayList();
			listAddDetails.add(a);
			listAddDetails.add(auditFilePath);
			listAddDetails.add(auditFileName);
			listAddDetails.add(fileDetail);
			listAddDetails.add(paraDetails);
			listAddDetails.add(objection);
			listAddDetails.add(valAgmp);
			listAddDetails.add(stampDuty);
			listAddDetails.add(regFee);
			listAddDetails.add(marketVal);
			//added by shruti
			listAddDetails.add(docId);
			listAddDetails.add(objStatus);
			listAddDetails.add(caseId);
			//end
			
			listAddDetails.add(agmpComm);
			listAddDetails.add(comDetails);
			//added by shruti
			//listAddDetails.add();

			result = agmpdao.submitInternalRcptReport(listAddDetails);

		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return result;
	}

	
	/**
	 * @param agmpdto
	 * @param docAgmpList
	 * @return
	 */
	public boolean submitExistingAGMPReport(AGMPReportDetailsDTO agmpdto,
			ArrayList docAgmpList) {

		boolean res = false;
		String result = null;
		try {
			IGRSCommon myCommon = new IGRSCommon();

			ArrayList para = agmpdao.getMaxParaId();
			ArrayList paraRow = (ArrayList) para.get(0);
			String paraTxn = (String) paraRow.get(0);

			String paraDetails[] = new String[6];
			paraDetails[0] = paraTxn;
			paraDetails[1] = agmpdto.getListParaType();
			paraDetails[2] = agmpdto.getTextParaName();
			paraDetails[3] = agmpdto.getTextParaComments();
			paraDetails[4] = "O";
			paraDetails[5]=agmpdto.getTextParaNumber();

			String objection[] = new String[docAgmpList.size()];
			String valAgmp[] = new String[docAgmpList.size()];
			String stampDuty[] = new String[docAgmpList.size()];
			String regFee[] = new String[docAgmpList.size()];
			String marketVal[]=new String[docAgmpList.size()];
			//added by shruti
			String objStatus[]=new String[docAgmpList.size()];
			String docId[]=new String[docAgmpList.size()];
			String caseId[]=new String[docAgmpList.size()];
			//end
			String agmpComm[] = new String[docAgmpList.size()];
			String comDetails[] = new String[2];
			comDetails[0] = agmpdto.getTxtAuditorName();
			comDetails[1] = agmpdto.getTxtAuditLocation();

			for (int i = 0; i < docAgmpList.size(); i++) {
				
				if(agmpdto.getListParaType()!=null)
				{
						if(agmpdto.getListParaType().equalsIgnoreCase("documents")||agmpdto.getListParaType().equalsIgnoreCase("दस्तावेज़")||agmpdto.getListParaType().equalsIgnoreCase("P01"))
						{
							DocDetails docDetails = (DocDetails) docAgmpList.get(i);
							objection[i] = docDetails.getObjDetails();
							valAgmp[i] = docDetails.getValAgmp();
							stampDuty[i] = docDetails.getStampDuty();
							agmpComm[i] = docDetails.getTxtAgmpComm();
							regFee[i] = docDetails.getRegFee();
							marketVal[i]=docDetails.getMarketVal();
							docId[i]=docDetails.getTxtDocID();
							objStatus[i]=docDetails.getDocStatusId();
							caseId[i]=docDetails.getCaseDocID();
						}
						else if(agmpdto.getListParaType().equalsIgnoreCase("Pending Cases")||agmpdto.getListParaType().equalsIgnoreCase("अनिर्णीत मामले")||agmpdto.getListParaType().equalsIgnoreCase("P02"))
						{
							CaseDetails caseDetails = (CaseDetails) docAgmpList.get(i);
							
							objection[i] = caseDetails.getObjDetails();
							valAgmp[i] = "";
							stampDuty[i] = "";
							agmpComm[i] = caseDetails.getTxtAgmpComm();
							regFee[i] = "";
							docId[i]="";
							objStatus[i]=caseDetails.getCaseStatus();
							caseId[i]=caseDetails.getTxtCaseID();
						}
						else if(agmpdto.getListParaType().equalsIgnoreCase("Miscellaneous")||agmpdto.getListParaType().equalsIgnoreCase("विविध")||agmpdto.getListParaType().equalsIgnoreCase("P03"))
						{
							DocDetails docDetails = (DocDetails) docAgmpList.get(i);
							objection[i] = docDetails.getObjDetails();
							valAgmp[i] = "";
							stampDuty[i] = "";
							agmpComm[i] = docDetails.getTxtAgmpComm();
							regFee[i] ="";
							docId[i]="";
							objStatus[i]="OPEN";
							caseId[i]="";
						}
				}
				else
				{
				DocDetails docDetails = (DocDetails) docAgmpList.get(i);
				objection[i] = docDetails.getObjDetails();
				valAgmp[i] = docDetails.getValAgmp();
				stampDuty[i] = docDetails.getStampDuty();
				agmpComm[i] = docDetails.getTxtAgmpComm();
				regFee[i] = docDetails.getRegFee();
				docId[i]=docDetails.getTxtDocID();
				objStatus[i]=docDetails.getDocStatusId();
				caseId[i]=docDetails.getCaseDocID();
				}
				
			}

			ArrayList listAddDetails = new ArrayList();
			listAddDetails.add(paraDetails);
			listAddDetails.add(objection);
			listAddDetails.add(valAgmp);
			listAddDetails.add(stampDuty);
			listAddDetails.add(regFee);
			listAddDetails.add(marketVal);
			//added by shruti
			listAddDetails.add(docId);
			listAddDetails.add(objStatus);
			listAddDetails.add(caseId);
			//end
			
			listAddDetails.add(agmpComm);
			listAddDetails.add(comDetails);

			String reportId = agmpdto.getTxnId();
			result = agmpdao.submitExistingAuditReport(listAddDetails, reportId);
			if (!result.equalsIgnoreCase("false")) {
				res = true;
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return res;

	}

	/**
	 * @param strDate
	 * @return
	 */
	public String getStrDate(String strDate) {
		String sdf = null;
		try {

			java.util.Date date = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(strDate);
			sdf = new java.text.SimpleDateFormat("dd/MM/yyyy").format(date);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return sdf;
	}

	public ArrayList getReceiptParaList(String language) throws Exception{
		ArrayList list = new ArrayList();
		AGMPReportDetailsDTO agmpDTO =null;
		ArrayList receiptparalist=new ArrayList();
		try {

			list = agmpdao.getReceiptParaList(language);
			if(list!=null){
			for(int i=0;i<list.size();i++){			
			ArrayList rowList = (ArrayList) list.get(i);
			agmpDTO=new AGMPReportDetailsDTO();
			//modified by shruti-11th september 2013
			agmpDTO.setParaType((String) rowList.get(0));
			agmpDTO.setParaName((String) rowList.get(1));
			receiptparalist.add(agmpDTO);
			}
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return receiptparalist;
	}
	//added by shruti
	public ArrayList getReceiptParaDtlsList(String reportId ) throws Exception{
		ArrayList list = new ArrayList();
		AGMPReportDetailsDTO agmpDTO =null;
		ArrayList auditparadtlslist=new ArrayList();
		try {

			list = agmpdao.getReceiptParaDtlsList(reportId);
			if(list!=null){
			for(int i=0;i<list.size();i++){			
			ArrayList rowList = (ArrayList) list.get(i);
			agmpDTO=new AGMPReportDetailsDTO();
			
			agmpDTO.setDbParaType((String) rowList.get(0));
			agmpDTO.setDbParaId((String) rowList.get(1));
			agmpDTO.setDbParaStatus((String) rowList.get(2));
			
			/*agmpDTO.setDbParaType((String) rowList.get(0));
			agmpDTO.setDbParaName((String) rowList.get(1));
			agmpDTO.setDbParaNumber((String) rowList.get(2));
			agmpDTO.setDbParaStatus((String) rowList.get(3));*/
			
			auditparadtlslist.add(agmpDTO);
			}
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return auditparadtlslist;
	}
	//end

	public ArrayList getExpenParaList()throws Exception {
		ArrayList list = new ArrayList();
		AGMPReportDetailsDTO agmpDTO =null;
		ArrayList receiptparalist=new ArrayList();
		try {

			list = agmpdao.getExpenParaList();
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
				//logger.debug("property ref number-->"+ (String) temppropList.get(0));
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
	    
	 //added by shruti
	 public String getOfficeName(String officeId,String language) throws Exception {

		 String officeName="";
		officeName = agmpdao.getOfficeName(officeId,language);
			
			return officeName;
		}
	 
	 //added by shruti
	 public ArrayList getCaseIdInfo(AGMPReportDetailsDTO agmpreport) {
			ArrayList searchlist = new ArrayList();
			try {

				String search[] = new String[1];
				search[0] = agmpreport.getCaseId();
				searchlist = agmpdao.getCaseidInfo(search);
			} catch (Exception e) {
			e.printStackTrace();	
			}
			return searchlist;
		}

	 public CaseIdOtherSearch getCaseIdDetails(AGMPReportDetailsDTO agmpreportdetailsdto, String userid)
	 {
		   CaseIdOtherSearch caseidother = new CaseIdOtherSearch();
			String caseid[] = new String[2];
			caseid[0] = agmpreportdetailsdto.getCaseId();
			caseid[1]=userid;

			ArrayList searchOther = new ArrayList();
			ArrayList resultList=new ArrayList();
			try {
				searchOther = agmpdao.getCaseDetails(caseid);
				
				for (int i = 0; i < searchOther.size(); i++) {
					
					ArrayList row_list = (ArrayList) searchOther.get(i);
					caseidother.setCase_Id((String) row_list.get(0));
					caseidother.setCase_Date((String) row_list.get(1));
					caseidother.setCase_Status((String) row_list.get(2));
		
				}
				agmpreportdetailsdto.setCaseIdOther(caseidother);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return caseidother;
		}
	 //added by shruti-12th spe 2013
	 public CaseDetails getCaseStatus(AGMPReportDetailsDTO agmpReport) {
			ArrayList caseIdStatus = new ArrayList();
			CaseDetails caseDetail = new CaseDetails();
			String[] caseId = new String[1];
			caseId[0] = agmpReport.getTxtCaseID();
		
			try {

				caseIdStatus = agmpdao.getCaseStatus(caseId);
				for (int i = 0; i < caseIdStatus.size(); i++) {
					ArrayList rowList = new ArrayList();
					rowList = (ArrayList) caseIdStatus.get(i);
					caseDetail.setTxtCaseID((String) rowList.get(0));
					caseDetail.setCaseStatus((String) rowList.get(1));
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			return caseDetail;

		}
	 public CaseDetails getRRCCaseStatus(AGMPReportDetailsDTO agmpReport) {
			ArrayList caseIdStatus = new ArrayList();
			CaseDetails caseDetail = new CaseDetails();
			String[] caseId = new String[1];
			caseId[0] = agmpReport.getTxtCaseID();
		
			try {

				caseIdStatus = agmpdao.getRRCCaseStatus(caseId);
				for (int i = 0; i < caseIdStatus.size(); i++) {
					ArrayList rowList = new ArrayList();
					rowList = (ArrayList) caseIdStatus.get(i);
					caseDetail.setTxtCaseID((String) rowList.get(0));
					caseDetail.setCaseStatus((String) rowList.get(1));
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			return caseDetail;

		}

	 public AGMPReportDetailsDTO[] getCaseIdStatus(AGMPReportDetailsDTO agmpReport) {
			ArrayList docIdStatus = new ArrayList();
			AGMPReportDetailsDTO[] agmpReportDTO1 = null;
			String[] caseId = new String[1];
			caseId[0] = agmpReport.getTxtCaseID();
			try {

				docIdStatus = agmpdao.getCaseStatus(caseId);
				
				agmpReportDTO1 = new AGMPReportDetailsDTO[docIdStatus.size()];
				for (int i = 0; i < agmpReportDTO1.length; i++) {
					agmpReportDTO1[i] = new AGMPReportDetailsDTO();
				}
				for (int i = 0; i < docIdStatus.size(); i++) {
					ArrayList rowList = new ArrayList();
					rowList = (ArrayList) docIdStatus.get(i);
					agmpReportDTO1[i].setTxtCaseID((String) rowList.get(0));
					agmpReportDTO1[i].setCaseStatus((String) rowList.get(1));
					//agmpReportDTO1[i].setTxtDocID((String) rowList.get(0));
					//agmpReportDTO1[i].setDocStatusId((String) rowList.get(1));
					//agmpReportDTO1[i].setTxtAgmpComm(agmpReport.getTxtAgmpComm());
				}
				// return agmpReportDTO1;
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			// return agmpReportDTO;
			return agmpReportDTO1;
		}

	 
	 //added by ashima
	 public ArrayList getDocDtls(String transid)
	 throws Exception {
	 ArrayList tmpList = new ArrayList();
	 AGMPReportDetailsDTO dto = new AGMPReportDetailsDTO();
	 ArrayList docDetailsList=new ArrayList();
	 ArrayList objStatusList=new ArrayList();
	 try {
		 tmpList=agmpdao.getDocDetails(transid);
		 if (tmpList != null && tmpList.size() > 0) 
		 {
		 logger.debug("--size--<>" + tmpList.size());
			 for (int j = 0; j < tmpList.size(); j++)
			 {
			 ArrayList arl = (ArrayList) tmpList.get(j);
							 if (arl != null && arl.size() > 0) 
							 {
							 logger.debug("--arl size--<>" + arl.size());
							 AGMPReportDetailsDTO pdto = new AGMPReportDetailsDTO();
							 pdto.setParaType((String) arl.get(0));
							 pdto.setParaId((String) arl.get(1));
							 pdto.setParaStatus((String) arl.get(2));
							 if(pdto.getParaStatus()!=null&&pdto.getParaStatus().equalsIgnoreCase("O"))
							 {
								 pdto.setParaStatus("OPEN");
							 }
							 //String objStatus = getObjStatus(pdto.getParaId());
							 ArrayList objections=agmpdao.getObjectionDetails(pdto.getParaId());
							 {
								 if (objections != null && objections.size() > 0) 
								 {
									 for (int k = 0; k < objections.size(); k++)
									 {
										 ArrayList ar1 = (ArrayList) objections.get(k);
										 if (ar1 != null && ar1.size() > 0) 
										 {
											 AGMPReportDetailsDTO pdto1 = new AGMPReportDetailsDTO();
											 pdto1.setObjStatus((String) ar1.get(2));
											 objStatusList.add(pdto1);
										 }
									 }
								 }
							 }
							 for(Object i:objStatusList)
							 {
								 AGMPReportDetailsDTO pdto2 =(AGMPReportDetailsDTO)i;
								 
								 if(pdto2.getObjStatus()!=null && !pdto2.getObjStatus().equalsIgnoreCase("CLOSE"))
								 {
									 pdto.setParaStatus("OPEN");
									 break;
								 }
								 else if(pdto2.getObjStatus()!=null && pdto2.getObjStatus().equalsIgnoreCase("CLOSE"))
								 {
									 
									 pdto.setParaStatus("CLOSE");
									 pdto2.setParaStatus("CLOSE");
									 pdto2.setParaComments("PARA ATOMATICALLY CLOSED BECAUSE OBJECTIONS ARE CLOSED");
									 pdto2.setParaId(pdto.getParaId());
									 boolean flag=agmpdao.updateParaDetails(pdto2);
									 logger.info("PARA STATUS UPDATED BY SYSTEM:::::::"+flag);
								 }
							 }
							 
							 pdto.setTransId((String) arl.get(3));
							 if(pdto.getParaType()==null)
							 {
								 pdto.setParaType("-");
							 }
							 else if(pdto.getParaId()==null)
							 {
								 pdto.setParaId("-");
							 }
							 else if(pdto.getParaStatus()==null)
							 {
								 pdto.setParaStatus("-");
							 }
							 else if(pdto.getTransId()==null)
							 {
								 pdto.setTransId("-");
							 }
							 docDetailsList.add(pdto);
							 logger.debug("SIZE>>>>>>>>"+docDetailsList.size());
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
	 return docDetailsList;
	 }	 
	 
	 //added by shruti
	 public AGMPReportDetailsDTO getParaDtls(String transid)
	 throws Exception {
	 ArrayList tmpList = new ArrayList();
	 AGMPReportDetailsDTO dto = new AGMPReportDetailsDTO();
	 ArrayList docDetailsList=new ArrayList();
	 AGMPReportDetailsDTO pdto = new AGMPReportDetailsDTO();
	 try {
		 tmpList=agmpdao.getParaDetails(transid);
		
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
							 pdto.setParaStatus((String) arl.get(4));
							 pdto.setDbParaStatus((String) arl.get(4));
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
							 pdto.setCaseId((String) arl.get(16));
							 if(pdto.getParaId()==null)
							 {
								 pdto.setParaId("-");
							 }
							 if(pdto.getParaTypeName()==null)
							 {
								 pdto.setParaTypeName("-");
							 }
							 if(pdto.getParaName()==null)
							 {
								 pdto.setParaName("-");
							 }
							 if(pdto.getParaComments()==null)
							 {
								 pdto.setParaComments("-");
							 }
							 if(pdto.getParaStatus()==null)
							 {
								 pdto.setParaStatus("-");
							 }
							 if(pdto.getParaNum()==null)
							 {
								 pdto.setParaNum("-");
							 }
							 if(pdto.getReport()==null)
							 {
								 pdto.setReport("-");
							 }
							 if(pdto.getParaId()==null)
							 {
								 pdto.setParaId("-");
							 }
							 if(pdto.getObjId()==null)
							 {
								 pdto.setObjId("-");
							 }
							 if(pdto.getDocId()==null)
							 {
								 pdto.setDocId("-");
							 }
							 if(pdto.getObjDate()==null)
							 {
								 pdto.setObjDate("-");
							 }
							 if(pdto.getObjDtls()==null)
							 {
								 pdto.setObjDtls("-");
							 }
							 if(pdto.getDefRegFee()==null)
							 {
								 pdto.setDefRegFee("-");
							 }
							 if(pdto.getDefStampDuty()==null)
							 {
								 pdto.setDefStampDuty("-");
							 }
							 if(pdto.getValReg()==null)
							 {
								 pdto.setValReg("-");
							 }
							 if(pdto.getValAgmp()==null)
							 {
								 pdto.setValAgmp("-");
							 }
							 if(pdto.getCaseId()==null)
							 {
								 pdto.setCaseId("-");
							 }
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
		 dto = null;
		 }
	 } catch (Exception e) {
	 logger.error(e);
	 }
	 return pdto;
	 }	 
	 //end
	 //added by Shreeaj
	 
	 public ArrayList getOfficeType(String language) throws Exception {
			ArrayList offcList = new ArrayList();
			
			
			try {
				ArrayList tmpList = agmpdao.getOfficeType();
				for (int i = 0; i < tmpList.size(); i++) {
					ArrayList tmpsubList = (ArrayList) tmpList.get(i);
					if (tmpsubList != null) {
						AGMPReportDetailsDTO dto = new AGMPReportDetailsDTO();
						dto.setOfficeTypeId((String) tmpsubList.get(0));
						//dto.setOffcId((String) tmpsubList.get(0));
						if("en".equalsIgnoreCase(language))
						{	
						dto.setOfficeName((String) tmpsubList.get(1));
						}
						else
						{
							dto.setOfficeName((String) tmpsubList.get(2));
		
						}
						offcList.add(dto);
					}

				}
			//	logger.info("getDistictDetails-->" + offcList);
			} catch (Exception e) {
				logger.error(e);

			}
			return offcList;
		}
	 
	 
	 public ArrayList getParaType() throws Exception {
			ArrayList paraTypeList = new ArrayList();
			
			
			try {
				ArrayList tmpList = agmpdao.getParaType();
				for (int i = 0; i < tmpList.size(); i++) {
					ArrayList tmpsubList = (ArrayList) tmpList.get(i);
					if (tmpsubList != null) {
						AGMPReportDetailsDTO dto = new AGMPReportDetailsDTO();
						dto.setParaId((String) tmpsubList.get(0));
						dto.setParaTypeName((String) tmpsubList.get(1));
						paraTypeList.add(dto);
					}

				}
				logger.info("getDistictDetails-->" + paraTypeList);
			} catch (Exception e) {
				logger.error(e);

			}
			return paraTypeList;
		}

	 
	 public ArrayList getParaStatus() throws Exception {
			ArrayList paraStatusList = new ArrayList();
			
			
			try {
				ArrayList tmpList = agmpdao.getParaStatus();
				for (int i = 0; i < tmpList.size(); i++) {
					ArrayList tmpsubList = (ArrayList) tmpList.get(i);
					if (tmpsubList != null) {
						AGMPReportDetailsDTO dto = new AGMPReportDetailsDTO();
						dto.setParaStatusId((String) tmpsubList.get(0));
						dto.setParaStatus((String) tmpsubList.get(1));
						paraStatusList.add(dto);
					}

				}
				logger.info("getDistictDetails-->" + paraStatusList);
			} catch (Exception e) {
				logger.error(e);

			}
			return paraStatusList;
		}
	 
	 public ArrayList getOfficeTypeList(String offId,String ofcId,String lang) throws Exception {
			ArrayList offcList = new ArrayList();
			
			
			try {
				ArrayList tmpList = agmpdao.getOfficeTypeList(offId,ofcId);
				for (int i = 0; i < tmpList.size(); i++) {
					ArrayList tmpsubList = (ArrayList) tmpList.get(i);
					if (tmpsubList != null) {
						AGMPReportDetailsDTO dto = new AGMPReportDetailsDTO();
						if(offId.equalsIgnoreCase("1")){
							
							dto.setOffcId((String) tmpsubList.get(0));
							if("en".equalsIgnoreCase(lang))
							{	
							dto.setHoName((String) tmpsubList.get(1));
							}
							else
							{
							dto.setHoName((String) tmpsubList.get(2));
								
							}
						}
						if(offId.equalsIgnoreCase("2")){
							dto.setOffcId((String) tmpsubList.get(0));
							if("en".equalsIgnoreCase(lang))
							{
							dto.setDroName((String) tmpsubList.get(1));
							}
							else
							{
							dto.setDroName((String) tmpsubList.get(2));
								
							}
							}
						if(offId.equalsIgnoreCase("3")){
							dto.setOffcId((String) tmpsubList.get(0));
							if("en".equalsIgnoreCase(lang))
							{
							dto.setSroName((String) tmpsubList.get(1));
							}
							else
							{
								dto.setSroName((String) tmpsubList.get(2));
								
							}
							}
						//dto.setOffcId((String) tmpsubList.get(2));
						offcList.add(dto);
					}

				}
				//logger.info("Office ID-->" + offId);
				//logger.info("getDistictDetails-->" + offcList);
			} catch (Exception e) {
				logger.error(e);

			}
			return offcList;
		}
	 
	 public ArrayList getSearchResult( AGMPReportDetailsDTO adto) throws Exception {
			ArrayList searchList = new ArrayList();
			String durFrom="01-"+adto.getAuditFromMonth()+"-"+adto.getAuditFromYear();
			String durTo="01-"+adto.getAuditToMonth()+"-"+adto.getAuditToYear();
			
			//logger.info("Duration from is: "+durFrom+" Duration To is: "+durTo);
			try {
				
				ArrayList tmpList = agmpdao.getSearchResult(durFrom,durTo,adto);
				for (int i = 0; i < tmpList.size(); i++) {
					ArrayList tmpsubList = (ArrayList) tmpList.get(i);
					if (tmpsubList != null) {
						AGMPReportDetailsDTO dto = new AGMPReportDetailsDTO();
						dto.setTxnId((String) tmpsubList.get(0));
						String audDate=(String) tmpsubList.get(1);
						String audEntDt=(String) tmpsubList.get(2);
						
						dto.setAuditDate(audDate.substring(0, 10));
						dto.setAudEntryDate(audEntDt.substring(0, 10));
						String status=(String) tmpsubList.get(3);
						if(status.equalsIgnoreCase("O")||status.equalsIgnoreCase("Open")){
							dto.setReportStatus("OPEN");
						}
						else{
							dto.setReportStatus("CLOSE");
						}
						
						searchList.add(dto);
					}

				}
				logger.info("getDistictDetails-->" + searchList);
			} catch (Exception e) {
				logger.error(e);

			}
			return searchList;
		}
	 public ArrayList getSearchInternalResult( AGMPReportDetailsDTO adto) throws Exception {
			ArrayList searchList = new ArrayList();
			String durFrom="01-"+adto.getAuditFromMonth()+"-"+adto.getAuditFromYear();
			String durTo="01-"+adto.getAuditToMonth()+"-"+adto.getAuditToYear();
			
			logger.info("Duration from is: "+durFrom+" Duration To is: "+durTo);
			try {
				
				ArrayList tmpList = agmpdao.getSearchInternalResult(durFrom,durTo,adto);
				for (int i = 0; i < tmpList.size(); i++) {
					ArrayList tmpsubList = (ArrayList) tmpList.get(i);
					if (tmpsubList != null) {
						AGMPReportDetailsDTO dto = new AGMPReportDetailsDTO();
						dto.setTxnId((String) tmpsubList.get(0));
						String audDate=(String) tmpsubList.get(1);
						String audEntDt=(String) tmpsubList.get(2);
						
						dto.setAuditDate(audDate.substring(0, 10));
						dto.setAudEntryDate(audEntDt.substring(0, 10));
						String status=(String) tmpsubList.get(3);
						if(status.equalsIgnoreCase("O")||status.equalsIgnoreCase("Open")){
							dto.setReportStatus("OPEN");
						}
						else{
							dto.setReportStatus("CLOSE");
						}
						
						searchList.add(dto);
					}

				}
				logger.info("getDistictDetails-->" + searchList);
			} catch (Exception e) {
				logger.error(e);

			}
			return searchList;
		}
	 public ArrayList fetchSearchResult(String reportID) throws Exception {
			ArrayList paraStatusList = new ArrayList();
			
			
			try {
				ArrayList tmpList = agmpdao.fetchSearchResult(reportID);
				
				for (int i = 0; i < tmpList.size(); i++) {
					ArrayList tmpsubList = (ArrayList) tmpList.get(i);
					if (tmpsubList != null) {
						AGMPReportDetailsDTO dto = new AGMPReportDetailsDTO();
						dto.setAuditDate((String) tmpsubList.get(0));
						dto.setAudEntryDate((String) tmpsubList.get(1));
						dto.setReportStatus((String) tmpsubList.get(2));
						dto.setAuditBodyType((String) tmpsubList.get(3));
						dto.setComplianceFlag((String) tmpsubList.get(4));
						dto.setSrCompilance((String) tmpsubList.get(5));
						dto.setDrComplinace((String) tmpsubList.get(6));
						dto.setDigCompliance((String) tmpsubList.get(7));
						dto.setIgCompliance((String) tmpsubList.get(8));
						paraStatusList.add(dto);
					}

				}
				logger.info("getDistictDetails-->" + paraStatusList);
			} catch (Exception e) {
				logger.error(e);

			}
			return paraStatusList;
		}
	 
	
	 public ArrayList fetchParaResult(String reportID) throws Exception {
			ArrayList paraStatusList = new ArrayList();
			
			
			try {
				ArrayList tmpList = agmpdao.fetchParaResult(reportID);
				for (int i = 0; i < tmpList.size(); i++) {
					ArrayList tmpsubList = (ArrayList) tmpList.get(i);
					if (tmpsubList != null) {
						AGMPReportDetailsDTO dto = new AGMPReportDetailsDTO();
						dto.setParaNamee((String) tmpsubList.get(0));
						dto.setParaComments((String) tmpsubList.get(1));
						String paraStatus=(String) tmpsubList.get(2);
					
						if(paraStatus.equalsIgnoreCase("O")||paraStatus.equalsIgnoreCase("Open")){
							dto.setParaStatus("OPEN");
						}
						else{
							dto.setParaStatus("CLOSE");
						}
						dto.setParaNum((String) tmpsubList.get(3));
						dto.setParaTxnId((String)tmpsubList.get(4));
						paraStatusList.add(dto);
					}

				}
				logger.info("getDistictDetails-->" + paraStatusList);
			} catch (Exception e) {
				logger.error(e);

			}
			return paraStatusList;
		}
	 //added by shruti
	 public ArrayList getParaStatusList(String lastStatus) throws Exception {
			ArrayList getParaStatusList = new ArrayList();
			
			
			try {
				ArrayList tmpList = agmpdao.getParaStatusList(lastStatus);
				for (int i = 0; i < tmpList.size(); i++) {
					ArrayList tmpsubList = (ArrayList) tmpList.get(i);
					if (tmpsubList != null) {
						AGMPReportDetailsDTO dto = new AGMPReportDetailsDTO();
						dto.setParaStatus((String) tmpsubList.get(0));
						dto.setParaStatusId((String) tmpsubList.get(1));
						if(dto.getParaStatus()==null)
						{
							dto.setParaStatus("-");
						}
						if(dto.getParaStatusId()==null)
						{
							dto.setParaStatusId("-");
						}
						getParaStatusList.add(dto);
					}

				}
				logger.info("getDistictDetails-->" + getParaStatusList);
			} catch (Exception e) {
				logger.error(e);

			}
			return getParaStatusList;
		}
	 public boolean  updateParaDetails(AGMPReportDetailsDTO dto) throws Exception

	    {
			boolean flg=false;
	            ArrayList partyList =null;
	            DBUtility dbUtil = null;
	            try {   	
	            	flg=agmpdao.updateParaDetails(dto);
	            } catch (Exception e) {
	                    logger.info(e);
	            } finally {
	                    if(dbUtil!=null){
	                            dbUtil.closeConnection();
	                            }
	            }                               
	            return flg;               

	    }
	 
	 //added by shruti-15th oct 2013
	 public ArrayList getObjectionDtls(String transid)
	 throws Exception {
	 ArrayList tmpList = new ArrayList();
	 AGMPReportDetailsDTO dto = new AGMPReportDetailsDTO();
	 ArrayList docDetailsList=new ArrayList();
	 try {
		 tmpList=agmpdao.getObjectionDetails(transid);
		 if (tmpList != null && tmpList.size() > 0) 
		 {
		 logger.debug("--size--<>" + tmpList.size());
			 for (int j = 0; j < tmpList.size(); j++)
			 {
			 ArrayList arl = (ArrayList) tmpList.get(j);
							 if (arl != null && arl.size() > 0) 
							 {
							 logger.debug("--arl size--<>" + arl.size());
							 AGMPReportDetailsDTO pdto = new AGMPReportDetailsDTO();
							 pdto.setObjId((String) arl.get(0));
							 pdto.setDocId((String) arl.get(1));
							 pdto.setObjStatus((String) arl.get(2));
							 pdto.setCaseId((String) arl.get(3));
							 if(pdto.getObjId()==null)
							 {
								 pdto.setObjId("-");
							 }
							 if(pdto.getDocId()==null)
							 {
								 pdto.setDocId("-");
							 }
							 if(pdto.getObjStatus()==null)
							 {
								 pdto.setObjStatus("-");
							 }
							 if(pdto.getCaseId()==null)
							 {
								 pdto.setCaseId("-");
							 }
							 docDetailsList.add(pdto);
							 logger.debug("SIZE>>>>>>>>"+docDetailsList.size());
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
	 return docDetailsList;
	 }
	 
	 // added by shruti-16th oct 2013 
	 public AGMPReportDetailsDTO getObjectionParaDtls(String transid)
	 throws Exception {
		 ArrayList tmpList = new ArrayList();
		 AGMPReportDetailsDTO dto = new AGMPReportDetailsDTO();
		 ArrayList docDetailsList=new ArrayList();
		 AGMPReportDetailsDTO pdto = new AGMPReportDetailsDTO();
		 try {
			 tmpList=agmpdao.getObjectionParaDetails(transid);
			
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
								 if(pdto.getObjDtls()==null)
								 {
									 pdto.setObjDtls("-");
								 }
								 if(pdto.getObjStatus()==null)
								 {
									 pdto.setObjStatus("-");
								 }
								 if(pdto.getDocId()==null)
								 {
									 pdto.setDocId("-");
									 pdto.setValReg("-");
								 }else{
									 String[] regid={pdto.getDocId()}; 
								ArrayList list=	 agmpdao.getRegDetails(regid);
								if(list!=null && list.size()>=0){
								ArrayList row_list = (ArrayList) list.get(0);
								pdto.setValReg((String)row_list.get(5));
								}else{
									pdto.setValReg("-");
								}
								 }
								 if(pdto.getObjDate()==null)
								 {
									 pdto.setObjDate("-");
								 }
								 if(pdto.getDefRegFee()==null)
								 {
									 pdto.setDefRegFee("-");
								 }
								 if(pdto.getDefStampDuty()==null)
								 {
									 pdto.setDefStampDuty("-");
								 }
								 //if(pdto.getValReg()==null)
								 //{
								//	 pdto.setValReg("-");
								// }
								 if(pdto.getValAgmp()==null)
								 {
									 pdto.setValAgmp("-");
								 }
								 if(pdto.getCaseId()==null)
								 {
									 pdto.setCaseId("-");
								 }
								 logger.debug("SIZE>>>>>>>>"+docDetailsList.size());
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
		 return pdto;
		 }	 
	 
	 //ADDED BY SHRUTI-16TH OCT 2013
	 public boolean  updateObjectionParaDetails(AGMPReportDetailsDTO dto) throws Exception

	    {
			boolean flg=false;
	            ArrayList partyList =null;
	            DBUtility dbUtil = null;
	            try {   	
	            	flg=agmpdao.updateObjectionParaDetails(dto);
	            } catch (Exception e) {
	                    logger.info(e);
	            } finally {
	                    if(dbUtil!=null){
	                            dbUtil.closeConnection();
	                            }
	            }                               
	            return flg;               

	    }
	 
	 public ArrayList getJurisdictionSroName(String officeId,String language) throws Exception {

			ArrayList sroList = agmpdao.getJurisdictionSRO(officeId,language);
			ArrayList sroList1 = new ArrayList();
			if (sroList != null) {
				for (int i = 0; i < sroList.size(); i++) {
					ArrayList sroTemp = (ArrayList) sroList.get(i);
					if (sroTemp != null) {
						AGMPReportDetailsDTO agmpreportdetailsdto = new AGMPReportDetailsDTO();
						agmpreportdetailsdto.setSroId((String) sroTemp.get(0));
						agmpreportdetailsdto.setSroName((String) sroTemp.get(1));
						sroList1.add(agmpreportdetailsdto);
					}
				}
			}
			return sroList1;
		}
	 public ArrayList getObjectionDetails(String paraID)
	 {
		 ArrayList tmpList = new ArrayList();
		 AGMPReportDetailsDTO dto = new AGMPReportDetailsDTO();
		 ArrayList docDetailsList=new ArrayList();
		 ArrayList retList=new ArrayList();
		 try {
			 tmpList=agmpdao.getObjectionDetls(paraID);
			
			 if (tmpList != null && tmpList.size() > 0) 
			 {
			 logger.debug("--size--<>" + tmpList.size());
				 for (int j = 0; j < tmpList.size(); j++)
				 {
				 ArrayList arl = (ArrayList) tmpList.get(j);
								 if (arl != null && arl.size() > 0) 
								 {
								 AGMPReportDetailsDTO pdto = new AGMPReportDetailsDTO();
								 logger.debug("--arl size--<>" + arl.size());
								 pdto.setObjId((String) arl.get(0));
								 pdto.setObjDtls((String) arl.get(1));
								 pdto.setObjDate((String) arl.get(2));
								 pdto.setObjStatus((String) arl.get(3));
								if(arl.get(4)==null||arl.get(4).toString().equalsIgnoreCase(""))
								{
									pdto.setDocId("-");
								}
								else
								 {
									pdto.setDocId((String) arl.get(4));
								 }
								if(arl.get(5)==null||arl.get(5).toString().equalsIgnoreCase(""))
								{
									 pdto.setValReg("-");

								}
								else
								{	
								 pdto.setValReg((String) arl.get(5));
								}
								if(arl.get(6)==null||arl.get(6).toString().equalsIgnoreCase(""))
								{
									 pdto.setValAgmp("-");

								}
								else
								{	
								 pdto.setValAgmp((String) arl.get(6));
								}
								if(arl.get(7)==null||arl.get(7).toString().equalsIgnoreCase(""))
								{
									pdto.setDefStampDuty("-");
								}
								else
								{
									pdto.setDefStampDuty((String) arl.get(7));
								}
								if(arl.get(8)==null||arl.get(8).toString().equalsIgnoreCase(""))
								{
									pdto.setDefRegFee("-");
								}
								else
								{	
									pdto.setDefRegFee((String) arl.get(8));
								}
								if(arl.get(9)==null||arl.get(9).toString().equalsIgnoreCase(""))
								{
									pdto.setCaseId("-");

								}
								else
								{
								pdto.setCaseId((String) arl.get(9));
								}
									retList.add(pdto);
								 logger.debug("SIZE>>>>>>>>"+docDetailsList.size());
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
		 return retList;
		 }
	 
	 public String getDistrict(String officeId,String language)
	 {
		 String distName="";
		 try {
			
			distName=agmpdao.getDist(officeId,language);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return distName;
	 }
	 
	 public String getParaStatus(String auditTxnId)
		{
		 return agmpdao.getParaStatus(auditTxnId);
		}
	 
	 public String getObjStatus(String paraId)
		{
		 return agmpdao.getObjStatus(paraId);
		}
}