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
 * 1.0             SHRUTI           xxxxx              
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.documentsearch.bd;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.wipro.igrs.DeliveryOfDocuments.bo.DeliveryOfDocumentsBO;
import com.wipro.igrs.caveats.dto.CaveatsDTO;
import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.documentsearch.bo.DocumentSearchBO;
import com.wipro.igrs.documentsearch.constant.CommonConstant;
import com.wipro.igrs.documentsearch.dao.DocumentSearchDAO;
import com.wipro.igrs.documentsearch.dto.DocumentSearchDTO;
import com.wipro.igrs.documentsearch.dto.PartyDetailsDTO;

public class DocumentSearchBD {
	DocumentSearchBO bo;
	Logger logger = (Logger) Logger.getLogger(DocumentSearchBD.class);

	public DocumentSearchBD() throws Exception {
		logger.debug("IN BD");
		bo = new DocumentSearchBO();
	}

	public String getDate(String _fdate) {
		logger.debug("in bd for get date" + _fdate);
		StringTokenizer stoken = new StringTokenizer(_fdate, "/");
		String dd = stoken.nextToken();
		String mm = stoken.nextToken();
		String yy = stoken.nextToken();
		if (dd.length() == 2) {
			_fdate = mm + "-" + getMonthName(dd) + "-" + yy;
		}
		logger.debug(_fdate);
		return _fdate;
	}

	public String getMonthName(String _month) {
		logger.debug("in bd for get mon");
		HashMap hm = new HashMap();
		hm.put("01", "JAN");
		hm.put("02", "FEB");
		hm.put("03", "MAR");
		hm.put("04", "APR");
		hm.put("05", "MAY");
		hm.put("06", "JUN");
		hm.put("07", "JUL");
		hm.put("08", "AUG");
		hm.put("09", "SEP");
		hm.put("10", "OCT");
		hm.put("11", "NOV");
		hm.put("12", "DEC");
		return (String) hm.get(_month);
	}

	public String verifyRegistrationId(String regNum) throws Exception {
		ArrayList ret = bo.verifyRegistrationId(regNum);
		System.out.println("I m here " + ret.size());
		String regNumber = null;
		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				try {
					regNumber = ((String) ret.get(0));
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		}
		return regNumber;
	}

	@SuppressWarnings("unchecked")
	public DocumentSearchDTO checkRegistrationId(String _refRegId, String language) throws Exception {
		ArrayList resultList = new ArrayList();
		ArrayList typeaList = new ArrayList();
		DocumentSearchDTO dto = new DocumentSearchDTO();
		DocumentSearchDTO resultdto = new DocumentSearchDTO();
		try {
			//resultList = bo.checkRegistrationId(_refRegId, language);
			logger.debug("Using new method for searching...");
			resultList = bo.checkRegistrationId(_refRegId, language);
			logger.debug("in bd resultList size -->" + resultList.size());
			if (resultList != null && resultList.size() > 1) {
				dto.setRegistNumber(_refRegId);
				dto.setApplicationId((String) resultList.get(0));
				dto.setEstampCode((String) resultList.get(1));
				ArrayList tmp = (ArrayList) resultList.get(2);
				ArrayList paryList = new ArrayList();
				ArrayList tmpList = (ArrayList) resultList.get(2);
				PartyDetailsDTO tmppdto = new PartyDetailsDTO();
				if (tmpList != null && tmpList.size() > 0) {
					logger.debug("--size--<>" + tmpList.size());
					for (int j = 0; j < tmpList.size(); j++) {
						logger.debug("tmp list  for--<>" + j);
						ArrayList arl = (ArrayList) tmpList.get(j);
						if (arl != null && arl.size() > 0) {
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
							pdto.setPropShare((String) arl.get(20));
							pdto.setProofNumber((String) arl.get(21));
							// pdto.setOcupation((String) arl.get(22));
							pdto.setGovtOffName((String) arl.get(22));
							pdto.setGovtOffDesg((String) arl.get(23));
							pdto.setGovtOffAddress((String) arl.get(24));
							paryList.add(pdto);
						}
					}
				}
				ArrayList tmppropertyList = new ArrayList();
				tmppropertyList = (ArrayList) resultList.get(3);
				ArrayList propertyList = new ArrayList();
				ArrayList khasraDetails = new ArrayList();
				ArrayList protestDetails = new ArrayList();
				DocumentSearchDTO propertydto = new DocumentSearchDTO();
				DocumentSearchDTO khasradto = new DocumentSearchDTO();
				DocumentSearchDTO protestdto = new DocumentSearchDTO();
				System.out.println("size of tmppropertyList>>>>>>>>>>>>" + tmppropertyList.size());
				if (tmppropertyList != null && tmppropertyList.size() > 0) { // changes
					// by
					// Shreeraj
					ArrayList temppropList = new ArrayList();

					for (int k = 0; k < tmppropertyList.size(); k++) {
						temppropList = (ArrayList) tmppropertyList.get(k);
						// ArrayList temppropList = new ArrayList();
						// temppropList=(ArrayList) tmppropertyList.get(k);
						logger.debug("property list size -->" + temppropList.size());
						logger.debug("property ref number-->" + (String) temppropList.get(0) + (String) temppropList.get(1));
						propertydto.setPropertyId((String) temppropList.get(1));
						propertydto.setPropType((String) temppropList.get(2));
						propertydto.setUseType((String) temppropList.get(2));
						propertydto.setDistName((String) temppropList.get(3));
						propertydto.setTehisilName((String) temppropList.get(4));
						propertydto.setWardName((String) temppropList.get(5));
						propertydto.setMohallaName((String) temppropList.get(6));
						propertydto.setAreaTypeName((String) temppropList.get(7));
						propertydto.setGorvMunicplBody((String) temppropList.get(8));
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
						propertydto.setPropMap((String) temppropList.get(19));
						propertydto.setConstuctedArea("");
						propertydto.setCeilingType("");
						propertyList.add(propertydto);
						propertydto = new DocumentSearchDTO();

						// modified by shruti---for iteration to be done for
						// each property id
						// by Shreeraj
						System.out.println("Property Id from tmppropertyList1>>>>>" + (String) temppropList.get(1));
						String propID = (String) temppropList.get(1);
						ArrayList khasraDet = new ArrayList();
						ArrayList protestDet = new ArrayList();
						khasraDet = bo.getKhasraDetail(propID);
						protestDet = bo.protestDetls(propID, language);
						logger.debug("khasra list size -->" + khasraDet.size());
						logger.debug("protestDet list size -->" + protestDet.size());
						if (khasraDet != null && khasraDet.size() > 0) {
							System.out.println(khasraDet.size());
							for (int j = 0; j < khasraDet.size(); j++) {
								ArrayList khasraList = (ArrayList) khasraDet.get(j);
								khasradto.setKhasraNumber((String) (khasraList.get(0)));
								khasradto.setKhasraName((String) khasraList.get(1));
								khasradto.setLagaan((String) khasraList.get(2));
								khasradto.setRinPushtikaNumber((String) khasraList.get(3));
								khasradto.setNorthBoundary((String) khasraList.get(4));
								khasradto.setSouthBoundary((String) khasraList.get(5));
								khasradto.setEastBoundary((String) khasraList.get(6));
								khasradto.setWestBoundary((String) khasraList.get(7));
								khasraDetails.add(khasradto);
								khasradto = new DocumentSearchDTO();
								System.out.println(khasraDetails.size());
							}
						}
						if (protestDet != null && protestDet.size() > 0) {
							System.out.println(protestDet.size());
							for (int j = 0; j < protestDet.size(); j++) {
								ArrayList protestList = (ArrayList) protestDet.get(j);
								protestdto.setProtestTxnId((String) (protestList.get(1)));
								protestdto.setCourtOrderDocPath((String) protestList.get(2));
								protestdto.setCourtOrderDoc((String) protestList.get(3));
								protestdto.setProtestStatus((String) protestList.get(4));

								protestDetails.add(protestdto);
								protestdto = new DocumentSearchDTO();
								System.out.println(protestDetails.size());
							}
						}
						// end
					}

				}

				ArrayList caveatlist = (ArrayList) resultList.get(5);
				ArrayList caveatslist = new ArrayList();
				CaveatsDTO caveatsDTO = new CaveatsDTO();
				if (caveatlist != null && caveatlist.size() > 0) {
					for (int k = 0; k < caveatlist.size(); k++) {
						caveatsDTO = new CaveatsDTO();
						logger.debug(caveatlist.get(k));
						ArrayList tmpll = (ArrayList) caveatlist.get(k);
						caveatsDTO.setBankChargeId((String) tmpll.get(0));
						caveatsDTO.setCaveatStatus((String) tmpll.get(1));
						if (caveatsDTO.getCaveatStatus().equals("LOGGED")) {
							caveatsDTO.setCaveatUploadedDoc((String) tmpll.get(2));
						}
						if (caveatsDTO.getCaveatStatus().equals("RELEASED")) {
							caveatsDTO.setCaveatUploadedDoc((String) tmpll.get(3));
						}
						if ("hi".equalsIgnoreCase(language)) {
							if ("LOGGED".equalsIgnoreCase(caveatsDTO.getCaveatStatus())) {
								caveatsDTO.setCaveatSorderStatus("लॉगड");
							} else if ("RELEASED".equalsIgnoreCase(caveatsDTO.getCaveatStatus())) {
								caveatsDTO.setCaveatSorderStatus("रिलीजड");
							}
						}
						caveatslist.add(caveatsDTO);
					}
				}

				ArrayList complianceList = (ArrayList) resultList.get(5);
				ArrayList tmpcomplList = new ArrayList();
				DocumentSearchDTO subclausedto = new DocumentSearchDTO();
				DocumentSearchDTO compliancedto = new DocumentSearchDTO();
				if (complianceList != null && complianceList.size() > 0) {
					for (int k = 0; k < complianceList.size(); k++) {
						subclausedto = new DocumentSearchDTO();
						logger.debug(complianceList.get(k));
						ArrayList tmpcompl = (ArrayList) complianceList.get(k);
						compliancedto = new DocumentSearchDTO();
						compliancedto.setComplianceName((String) tmpcompl.get(0));
						tmpcomplList.add(compliancedto);
					}
				}
				dto.setComplianceList(tmpcomplList);
				dto.setCaveatslist(caveatslist);
				logger.debug(" in bd party list? " + paryList);
				dto.setPropertyList(propertyList);
				dto.setKhasraList(khasraDetails);
				dto.setProtestList(protestDetails);
				logger.debug(" in bd khasra list? " + khasraDetails);
				logger.info("in bd bd propertyList size-->" + propertyList.size());
				logger.info("in bd bd party size-->" + paryList.size());
				tmppdto.setPartyList(paryList);
				dto.setPartyDTO(tmppdto);
				typeaList.add(dto);
				dto.setTypeBresult(typeaList);
			} else {
				dto = null;
			}
			logger.info("in bd checkRegistrationId-->" + resultList);
		} catch (Exception e) {
			logger.error(e);
		}
		return dto;
	}

	// added by shruti----14 feb 2014
	public DocumentSearchDTO checkOldRegistrationId(DocumentSearchDTO dsdto, String language) throws Exception {
		ArrayList resultList = null;
		ArrayList typeaList = new ArrayList();
		DocumentSearchDTO dto = new DocumentSearchDTO();
		DocumentSearchDTO resultdto = new DocumentSearchDTO();
		try {

			resultList = bo.checkOldRegistrationId(dsdto, language);
			logger.debug("in bd resultList size -->" + resultList.size());
			if (resultList != null && resultList.size() > 1) {
				// dto.setRegistNumber(_refRegId);
				dto.setApplicationId((String) resultList.get(0));
				dto.setEstampCode((String) resultList.get(1));
				ArrayList tmp = (ArrayList) resultList.get(2);
				ArrayList paryList = new ArrayList();
				ArrayList tmpList = (ArrayList) resultList.get(2);
				PartyDetailsDTO tmppdto = new PartyDetailsDTO();

				if (tmpList != null && tmpList.size() > 0) {
					logger.debug("--size--<>" + tmpList.size());
					for (int j = 0; j < tmpList.size(); j++) {
						logger.debug("tmp list  for--<>" + j);
						ArrayList arl = (ArrayList) tmpList.get(j);
						if (arl != null && arl.size() > 0) {
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
							pdto.setFatherName((String) arl.get(16));
							pdto.setMotherName((String) arl.get(17));
							pdto.setPropShare("");
							// added by shruti-5/8/2013
							pdto.setPartyChk((String) arl.get(15));
							pdto.setOrgName((String) arl.get(18));
							pdto.setAuthPrsnName((String) arl.get(19));
							// end
							paryList.add(pdto);
						}
					}
				}
				ArrayList tmppropertyList = (ArrayList) resultList.get(3);

				ArrayList propertyList = new ArrayList();
				ArrayList khasraDetails = new ArrayList();
				ArrayList protestDetails = new ArrayList();
				DocumentSearchDTO propertydto = new DocumentSearchDTO();
				DocumentSearchDTO khasradto = new DocumentSearchDTO();
				DocumentSearchDTO protestdto = new DocumentSearchDTO();
				System.out.println((String) tmppropertyList.get(1));
				if (tmppropertyList != null && tmppropertyList.size() > 0) {// changes
					// by
					// Shreeraj
					ArrayList tmppropertyList1 = (ArrayList) tmppropertyList.get(0);
					for (int k = 0; k < tmppropertyList.size(); k++) {
						logger.debug("inside property list methd");
						ArrayList temppropList = (ArrayList) tmppropertyList.get(k);
						logger.debug("property list size -->" + temppropList.size());
						logger.debug("property ref number-->" + (String) temppropList.get(0) + (String) temppropList.get(1));
						propertydto.setPropertyId((String) temppropList.get(1));
						propertydto.setPropType((String) temppropList.get(2));
						propertydto.setUseType((String) temppropList.get(2));
						propertydto.setDistName((String) temppropList.get(3));
						propertydto.setTehisilName((String) temppropList.get(4));
						propertydto.setWardName((String) temppropList.get(5));
						propertydto.setMohallaName((String) temppropList.get(6));
						// modified by shruti-6/8/2013
						propertydto.setAreaTypeName((String) temppropList.get(7));
						propertydto.setGorvMunicplBody((String) temppropList.get(8));
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

						/*
						 * propertydto.setKhasaraNo((String)(khasraDet.get(0))); propertydto.setKhasraName((String)khasraDet.get(1)); propertydto.setLagaan((String)khasraDet.get(2)); propertydto .setRinPushtikaNumber((String)khasraDet.get(3));
						 */
						propertydto.setConstuctedArea("");
						propertydto.setCeilingType("");
						propertyList.add(propertydto);
						propertydto = new DocumentSearchDTO();

						// end
					}
					// by Shreeraj

					System.out.println((String) tmppropertyList1.get(1));
					String propID = (String) tmppropertyList1.get(1);
					ArrayList khasraDet = bo.getKhasraDetail(propID);
					ArrayList protestDet = bo.protestDetls(propID, language);
					logger.debug("khasra list size -->" + khasraDet.size());
					if (khasraDet != null && khasraDet.size() > 0) {
						for (int j = 0; j < khasraDet.size(); j++) {

							ArrayList khasraList = (ArrayList) khasraDet.get(j);
							khasradto.setKhasraNumber((String) (khasraList.get(0)));
							khasradto.setKhasraName((String) khasraList.get(1));
							khasradto.setLagaan((String) khasraList.get(2));
							khasradto.setRinPushtikaNumber((String) khasraList.get(3));

							khasraDetails.add(khasradto);
							khasradto = new DocumentSearchDTO();

							System.out.println(khasraDetails.size());

						}
						System.out.println(khasraDet.size());
					}
					if (protestDet != null && protestDet.size() > 0) {
						System.out.println(protestDet.size());
						for (int j = 0; j < protestDet.size(); j++) {
							ArrayList protestList = (ArrayList) protestDet.get(j);
							protestdto.setProtestTxnId((String) (protestList.get(1)));
							protestdto.setCourtOrderDocPath((String) protestList.get(2));
							protestdto.setCourtOrderDoc((String) protestList.get(3));
							protestdto.setProtestStatus((String) protestList.get(4));
							protestDetails.add(protestdto);
							protestdto = new DocumentSearchDTO();
							System.out.println(protestDetails.size());
						}
					}
				}

				ArrayList caveatlist = (ArrayList) resultList.get(4);
				ArrayList caveatslist = new ArrayList();
				CaveatsDTO caveatsDTO = new CaveatsDTO();
				if (caveatlist != null && caveatlist.size() > 0) {
					for (int k = 0; k < caveatlist.size(); k++) {
						caveatsDTO = new CaveatsDTO();
						logger.debug(caveatlist.get(k));
						ArrayList tmpll = (ArrayList) caveatlist.get(k);
						// caveatsDTO.setCaveatType((String) tmpll.get(0));
						// caveatsDTO.setCaveatDetails((String) tmpll.get(1));
						// caveatsDTO.setCaveatSorderStatus((String)
						// tmpll.get(2));
						caveatsDTO.setBankChargeId((String) tmpll.get(0));
						caveatsDTO.setCaveatStatus((String) tmpll.get(1));
						if ((String) tmpll.get(2) != null)
							caveatsDTO.setCaveatUploadedDoc((String) tmpll.get(2));
						else
							caveatsDTO.setCaveatUploadedDoc("NA");
						caveatslist.add(caveatsDTO);
					}
				}

				ArrayList complianceList = (ArrayList) resultList.get(5);
				ArrayList tmpcomplList = new ArrayList();
				DocumentSearchDTO subclausedto = new DocumentSearchDTO();
				DocumentSearchDTO compliancedto = new DocumentSearchDTO();
				if (complianceList != null && complianceList.size() > 0) {
					for (int k = 0; k < complianceList.size(); k++) {
						subclausedto = new DocumentSearchDTO();
						logger.debug(complianceList.get(k));
						ArrayList tmpcompl = (ArrayList) complianceList.get(k);
						compliancedto = new DocumentSearchDTO();
						compliancedto.setComplianceName((String) tmpcompl.get(0));
						tmpcomplList.add(compliancedto);
					}
				}
				dto.setComplianceList(tmpcomplList);
				dto.setCaveatslist(caveatslist);
				logger.debug(" in bd party list? " + paryList);
				dto.setPropertyList(propertyList);
				dto.setKhasraList(khasraDetails);
				dto.setProtestList(protestDetails);
				logger.debug(" in bd khasra list? " + khasraDetails);

				logger.info("in bd bd propertyList size-->" + propertyList.size());
				logger.info("in bd bd party size-->" + paryList.size());
				tmppdto.setPartyList(paryList);
				dto.setPartyDTO(tmppdto);

				typeaList.add(dto);
				dto.setTypeBresult(typeaList);
			} else {
				dto = null;
			}
			logger.info("in bd checkRegistrationId-->" + resultList);
		} catch (Exception e) {
			logger.error(e);
		}
		return dto;
	}

	// end
	/**
	 * @param _refTranId
	 * @return
	 * @throws Exception
	 */
	public String checkEstampTxnId(String _refTranId) throws Exception {
		String estampId = null;

		try {
			estampId = bo.checkEstampTxnId(_refTranId);
			logger.info("checkEstampTxnId-->" + estampId);
		} catch (Exception e) {
			logger.error(e);

		}

		return estampId;
	}

	/**
	 * @param _refTranId
	 * @return
	 * @throws Exception
	 */
	public ArrayList getPartyDetails(String _refTranId, String language) throws Exception {
		ArrayList partyList = null;

		try {
			partyList = bo.getPartyDetails(_refTranId, language);
			logger.info("getPartyDetails-->" + partyList);
		} catch (Exception e) {
			logger.error(e);

		}

		return partyList;
	}

	/**
	 * @param _refTranId
	 * @return
	 * @throws Exception
	 */
	public ArrayList getPropertyDetails(String _refTranId, String language) throws Exception {
		ArrayList propertyList = null;

		try {
			propertyList = bo.getPropertyDetails(_refTranId, language);
			logger.info("getPartyDetails-->" + propertyList);
		} catch (Exception e) {
			logger.error(e);

		}
		return propertyList;
	}

	public ArrayList getSearchTypeBResult() throws Exception {

		ArrayList resultList = new ArrayList();
		try {
			// calling dao method
			DocumentSearchDTO dto = new DocumentSearchDTO();
			DocumentSearchDTO dto1 = new DocumentSearchDTO();
			// dateOfReg,registNumber,buyerName,sellerName
			dto.setSellerName("DJ");
			dto.setBuyerName("Deepak Jadan");
			dto.setRegistNumber("123456");
			dto.setDateOfReg("3/18/2008");
			dto.setAddress("IGRS1, Ward 1, Tehsil huzor, Bhopal");
			dto.setViewFee("200");
			dto.setDownloadFee("300");
			dto1.setSellerName("Hari");
			dto1.setBuyerName("DJ");
			dto1.setRegistNumber("456789");
			dto1.setDateOfReg("3/18/2008");
			dto1.setAddress("IGRS1, Ward 1, Tehsil huzor, Bhopal");
			dto1.setViewFee("200");
			dto1.setDownloadFee("300");
			resultList.add(dto);
			resultList.add(dto1);
		} catch (Exception e) {
			logger.error(e);
		}
		return resultList;
	}
	public String storePaymetTxnDetails(String paymentflag, DocumentSearchDTO dsdto, String userId, String functionId, String refID) throws Exception {

		String doctxnid = null;
		try {
			doctxnid = bo.storePaymetTxnDetails(paymentflag, dsdto, userId, functionId, refID);
			// System.out.println("I am in bd===========_docTxnId  "+regnum);
			System.out.println("I am in bd===========_docTxnId  " + dsdto.getRegistNumber());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return doctxnid;
	}

	public String updatePaymetTxnDetails(String paymentflag, String doctxnid, String regnum, String userId, String functionId) throws Exception {

		String txnid = null;
		try {
			// modified by shruti-5th aug
			txnid = bo.updatePaymetTxnDetails(paymentflag, doctxnid, regnum, userId, functionId);
			// end
			System.out.println("I am in bd===========_docTxnId  " + txnid);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return txnid;
	}

	public String updateOfficialTxnDetails(String doctxnid, String regnum) throws Exception {

		String txnid = null;
		try {
			// modified by shruti 5th august 2013
			txnid = bo.updateOfficialTxnDetails(doctxnid, regnum);
			// end
			System.out.println("I am in bd===========updateOfficialTxnDetails  " + txnid);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return txnid;
	}

	public String storeOfficeTxnDetails(String searchPurpose, String regnum, String userId, String functionId) throws Exception {

		String doctxnid = null;
		try {
			doctxnid = bo.storeOfficeTxnDetails(searchPurpose, regnum, userId, functionId);
			System.out.println("I am in bd===========_docTxnId  " + doctxnid);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return doctxnid;
	}

	// added by shruti--25 aug 2014
	public boolean updateOfficeTxnDetails(String docName, String docPath, String docTxnId) throws Exception {

		boolean flag = false;
		try {
			flag = bo.updateOfficeTxnDetails(docName, docPath, docTxnId);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	// end

	public String downloadPaymetTxnDetails(String paymentflag, String doctxnid, String userId, String functionId, String fee) throws Exception {

		String txnid = null;
		try {
			txnid = bo.downloadPaymetTxnDetails(paymentflag, doctxnid, userId, functionId, fee);
			System.out.println("I am in bd===========_docTxnId  " + doctxnid);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return txnid;
	}
	public String storeBTypeSearch(String paymentflag, DocumentSearchDTO dsto, DocumentSearchDTO dsdto, String userId, String functionId) throws Exception {

		String doctxnid = null;
		try {
			doctxnid = bo.storeBTypeSearch(paymentflag, dsto, dsdto, userId, functionId);
			System.out.println("I am in bd===========_docTxnId  " + doctxnid);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return doctxnid;
	}
	public String storeCTypeSearch(String paymentflag, DocumentSearchDTO dsto, String userId, String functionId) throws Exception {

		String doctxnid = null;
		try {
			doctxnid = bo.storeCTypeSearch(paymentflag, dsto, userId, functionId);
			System.out.println("I am in bd===========_docTxnId  " + doctxnid);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return doctxnid;
	}

	public ArrayList getDocumentViewResult() throws Exception {
		ArrayList resultList = new ArrayList();
		try {
			// calling dao method
			DocumentSearchDTO dto = new DocumentSearchDTO();

		} catch (Exception e) {
			logger.error(e);
		}
		return resultList;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public ArrayList getDistrict(String language) throws Exception {
		ArrayList distList = new ArrayList();
		IGRSCommon common = new IGRSCommon();
		try {
			ArrayList tmpList = common.getDistrict(CommonConstant.DOC_STATE_ID, language);
			for (int i = 0; i < tmpList.size(); i++) {
				ArrayList tmpsubList = (ArrayList) tmpList.get(i);
				if (tmpsubList != null) {
					DocumentSearchDTO dto = new DocumentSearchDTO();
					dto.setDistId((String) tmpsubList.get(0));
					dto.setDistId1((String) tmpsubList.get(0));
					dto.setDistName((String) tmpsubList.get(1));
					dto.setOldDistNameB((String) tmpsubList.get(1));
					distList.add(dto);
				}
			}
			logger.info("getDistictDetails-->" + distList);
		} catch (Exception e) {
			logger.error(e);

		}
		return distList;
	}

	public ArrayList getFinancialYear(String language) throws Exception {
		ArrayList financialYear = new ArrayList();
		IGRSCommon common = new IGRSCommon();
		DocumentSearchDAO dao = new DocumentSearchDAO();
		try {
			// ArrayList tmpList = common.getFinancialYear(language);
			ArrayList tmpList = dao.getFinancialYear(language);

			for (int i = 0; i < tmpList.size(); i++) {
				ArrayList tmpsubList = (ArrayList) tmpList.get(i);
				if (tmpsubList != null) {
					DocumentSearchDTO dto = new DocumentSearchDTO();
					dto.setFiscalYearId((String) tmpsubList.get(0));
					dto.setFiscalYearName((String) tmpsubList.get(1));
					financialYear.add(dto);
				}
			}
			logger.info("getDistictDetails-->" + financialYear);
		} catch (Exception e) {
			logger.error(e);

		}
		return financialYear;
	}
	public ArrayList getFiscalYearList(String language, String type) throws Exception {
		ArrayList financialYear = new ArrayList();
		IGRSCommon common = new IGRSCommon();
		DocumentSearchDAO dao = new DocumentSearchDAO();
		try {
			// ArrayList tmpList = common.getFinancialYear(language);
			ArrayList tmpList = dao.getFiscalYearList(language, type);

			for (int i = 0; i < tmpList.size(); i++) {
				ArrayList tmpsubList = (ArrayList) tmpList.get(i);
				if (tmpsubList != null) {
					DocumentSearchDTO dto = new DocumentSearchDTO();
					dto.setFiscalYearId((String) tmpsubList.get(0));
					dto.setFiscalYearName((String) tmpsubList.get(1));
					financialYear.add(dto);
				}
			}
			logger.info("getDistictDetails-->" + financialYear);
		} catch (Exception e) {
			logger.error(e);

		}
		return financialYear;
	}
	// added by shruti--25 nov 2014
	public ArrayList getRegsitrationDistrict(String language) throws Exception {
		ArrayList distList = new ArrayList();
		IGRSCommon common = new IGRSCommon();
		try {
			ArrayList tmpList = common.getRegsitrationDistrict(CommonConstant.DOC_STATE_ID, language);
			for (int i = 0; i < tmpList.size(); i++) {
				ArrayList tmpsubList = (ArrayList) tmpList.get(i);
				if (tmpsubList != null) {
					DocumentSearchDTO dto = new DocumentSearchDTO();
					dto.setRegDistId((String) tmpsubList.get(0));
					dto.setRegDistName((String) tmpsubList.get(1));
					dto.setHdnRegDistId((String) tmpsubList.get(0) + "~" + (String) tmpsubList.get(1));
					distList.add(dto);
				}
			}
			logger.info("getDistictDetails-->" + distList);
		} catch (Exception e) {
			logger.error(e);

		}
		return distList;
	}
	// end

	public ArrayList getSroList(String distId, String language) throws Exception {
		ArrayList sroList = new ArrayList();
		IGRSCommon common = new IGRSCommon();
		try {
			ArrayList tmpList = common.getSroList(distId, language);
			for (int i = 0; i < tmpList.size(); i++) {
				ArrayList tmpsubList = (ArrayList) tmpList.get(i);
				if (tmpsubList != null) {
					DocumentSearchDTO dto = new DocumentSearchDTO();
					// dto.setDistId((String) tmpsubList.get(0));
					// dto.setDistName((String) tmpsubList.get(1));
					dto.setSroName((String) tmpsubList.get(0));
					sroList.add(dto);
				}

			}
			logger.info("getDistictDetails-->" + sroList);
		} catch (Exception e) {
			logger.error(e);

		}
		return sroList;
	}
	/**
	 * @return
	 * @throws Exception
	 */
	public ArrayList getBookType() throws Exception {
		ArrayList bookResultList = new ArrayList();
		IGRSCommon common = new IGRSCommon();
		try {
			ArrayList tmpList = common.getBookType();
			for (int i = 0; i < tmpList.size(); i++) {
				ArrayList bookList = (ArrayList) tmpList.get(i);
				if (bookList != null) {
					DocumentSearchDTO dto = new DocumentSearchDTO();
					dto.setBookId((String) bookList.get(0));
					dto.setBookName((String) bookList.get(1));
					bookResultList.add(dto);
				}

			}
			logger.info("getDistictDetails-->" + bookResultList);
		} catch (Exception e) {
			logger.error(e);

		}
		return bookResultList;
	}
	/**
	 * @param _distId
	 * @return
	 * @throws Exception
	 */
	public ArrayList getTehisil(String _distId, String language) throws Exception {
		ArrayList tehsilList = new ArrayList();
		IGRSCommon common = new IGRSCommon();
		try {
			//ArrayList tmpList = common.getThesil(_distId, language);
			ArrayList tmpList = bo.getTehsil(_distId, language);
			for (int i = 0; i < tmpList.size(); i++) {
				ArrayList tmpsubList = (ArrayList) tmpList.get(i);
				if (tmpsubList != null) {
					DocumentSearchDTO dto = new DocumentSearchDTO();
					dto.setTehisilId((String) tmpsubList.get(0));
					if ("en".equalsIgnoreCase(language)) {
						dto.setTehisilName((String) tmpsubList.get(1));
					} else {

						dto.setTehisilName((String) tmpsubList.get(2));
					}
					tehsilList.add(dto);
				}

			}
			logger.info("getTehisil-->" + tehsilList);
		} catch (Exception e) {
			logger.error(e);

		}
		return tehsilList;
	}
	// added by shruti--25 nov 2014
	public ArrayList getRegistrationTehsil(String _distId, String language) throws Exception {
		ArrayList regTehsilList = new ArrayList();
		IGRSCommon common = new IGRSCommon();
		try {
			ArrayList tmpList = common.getRegistrationTehsil(_distId, language);
			for (int i = 0; i < tmpList.size(); i++) {
				ArrayList tmpsubList = (ArrayList) tmpList.get(i);
				if (tmpsubList != null) {
					DocumentSearchDTO dto = new DocumentSearchDTO();
					dto.setRegTehsilId((String) tmpsubList.get(0));
					dto.setRegTehsilName((String) tmpsubList.get(1));
					dto.setHdnRegTehsilId((String) tmpsubList.get(0) + "~" + (String) tmpsubList.get(1));
					regTehsilList.add(dto);
				}

			}
			logger.info("getPartyDetails-->" + regTehsilList);
		} catch (Exception e) {
			logger.error(e);

		}
		return regTehsilList;
	}
	// end
	// added by shruti---26 may 2014
	public ArrayList getSubAreaTypeId(String _areaTypeId, String language) throws Exception {
		ArrayList subAreaTypeIdList = new ArrayList();
		IGRSCommon common = new IGRSCommon();
		try {
			ArrayList tmpList = common.getSubAreaType(_areaTypeId, language);
			for (int i = 0; i < tmpList.size(); i++) {
				ArrayList tmpsubList = (ArrayList) tmpList.get(i);
				if (tmpsubList != null) {
					DocumentSearchDTO dto = new DocumentSearchDTO();
					dto.setSubAreaTypeId((String) tmpsubList.get(0));
					dto.setSubAreaTypeName((String) tmpsubList.get(1));
					subAreaTypeIdList.add(dto);
				}

			}
			logger.info("getSubAreaTypeId-->" + subAreaTypeIdList);
		} catch (Exception e) {
			logger.error(e);

		}
		return subAreaTypeIdList;
	}
	// end

	/**
	 * @return
	 * @throws Exception
	 */
	public ArrayList getAreaType(String language) throws Exception {
		ArrayList areaTypeList = new ArrayList();
		IGRSCommon common = new IGRSCommon();
		try {
			ArrayList tmpList = common.getAreaType(language);
			for (int i = 0; i < tmpList.size(); i++) {
				ArrayList tmpsubList = (ArrayList) tmpList.get(i);
				if (tmpsubList != null) {
					DocumentSearchDTO dto = new DocumentSearchDTO();
					dto.setAreaTypeId((String) tmpsubList.get(0));
					dto.setAreaTypeName((String) tmpsubList.get(1));
					areaTypeList.add(dto);
				}
			}
			logger.info("getPartyDetails-->" + areaTypeList);
		} catch (Exception e) {
			logger.error(e);

		}
		return areaTypeList;
	}

	/**
	 * @param _areaTypeId
	 * @return
	 * @throws Exception
	 * 
	 *             public ArrayList getWard(String _areaTypeId) throws Exception{ ArrayList wardList = new ArrayList(); IGRSCommon common=new IGRSCommon(); try { ArrayList tmpList=common.getWard(_areaTypeId); for(int i=0;i<tmpList.size();i++){ ArrayList tmpsubList=(ArrayList)tmpList.get(i); if(tmpsubList!=null){ DocumentSearchDTO dto=new DocumentSearchDTO(); dto.setWardId((String)tmpsubList.get(0));//for ward number dto.setWardName((String)tmpsubList.get(1));//for ward name wardList.add(dto); } } logger.info("getPartyDetails-->"+wardList); }catch (Exception e) { logger.error(e); } return wardList; } /**
	 * @param _areaTypeId
	 * @return
	 * @throws Exception
	 * 
	 *             public ArrayList getPatwarihalka(String _areaTypeId) throws Exception{ ArrayList patwariList = new ArrayList(); IGRSCommon common=new IGRSCommon(); try { ArrayList tmpList=common.getPatwarihalka(_areaTypeId); for(int i=0;i<tmpList.size();i++){ ArrayList tmpsubList=(ArrayList)tmpList.get(i); if(tmpsubList!=null){ DocumentSearchDTO dto=new DocumentSearchDTO(); dto.setPatwariId((String)tmpsubList.get(0));//for ward number dto.setPatwariName((String)tmpsubList.get(1));//for ward name patwariList.add(dto); } } logger.info("getPartyDetails-->"+patwariList); }catch (Exception e) { logger.error(e); } return patwariList; }
	 * 
	 *             /**
	 * @param _wardRpatwariId
	 * @return
	 * @throws Exception
	 * 
	 *             public ArrayList getMohalla(String _wardRpatwariId) throws Exception{ ArrayList mohallaList = new ArrayList(); IGRSCommon common=new IGRSCommon(); try { ArrayList tmpList=common.getMohalla(_wardRpatwariId); for(int i=0;i<tmpList.size();i++){ ArrayList tmpsubList=(ArrayList)tmpList.get(i); if(tmpsubList!=null){ DocumentSearchDTO dto=new DocumentSearchDTO(); dto.setMohallaId((String)tmpsubList.get(0));//for ward number dto.setMohallaName((String)tmpsubList.get(1));//for ward name mohallaList.add(dto); } } logger.info("getPartyDetails-->"+mohallaList); }catch (Exception e) { logger.error(e); } return mohallaList; }
	 * 
	 *             /**
	 * @param _wardRpatwariId
	 * @return
	 * @throws Exception
	 * 
	 *             public ArrayList getVillage(String _wardRpatwariId) throws Exception { ArrayList villageList = new ArrayList(); IGRSCommon common=new IGRSCommon(); try { ArrayList tmpList=common.getVillage(_wardRpatwariId); for(int i=0;i<tmpList.size();i++){ ArrayList tmpsubList=(ArrayList)tmpList.get(i); if(tmpsubList!=null){ DocumentSearchDTO dto=new DocumentSearchDTO(); dto.setVillageId((String)tmpsubList.get(0));//for ward number dto.setVillageName((String)tmpsubList.get(1));//for ward name villageList.add(dto); } } logger.info("getPartyDetails-->"+villageList); }catch (Exception e) { logger.error(e); } return villageList; }
	 * 
	 *             /**
	 * @param _areaTypeId
	 * @return
	 * @throws Exception
	 */
	// String[] _areaTypeId
	public ArrayList getWardOrPatwari(DocumentSearchDTO _refdto, String language) throws Exception {
		ArrayList wardList = new ArrayList();
		IGRSCommon common = new IGRSCommon();
		try {
			/* String[] _ward = new String[3]; */
			String[] _ward = new String[2];
			logger.debug("_refdto.getTehsilId()--->" + _refdto.getTehisilId());
			logger.debug("_refdto.getAreaTypeId()--->" + _refdto.getAreaTypeId());
			// logger.debug("_refdto.getWardId()----->"+
			// _refdto.getWardpatwarId());
			/*
			 * _ward[0] = _refdto.getTehisilId(); _ward[1] = _refdto.getAreaTypeId(); _ward[2] = _refdto.getWardpatwarId();
			 */
			_ward[1] = _refdto.getTehisilId();
			_ward[0] = _refdto.getAreaTypeId();
			// _ward[2] = _refdto.getWardpatwarId();
			ArrayList tmpList = common.getWardOrPatwariNew(_ward, language);// _areaTypeId
			for (int i = 0; i < tmpList.size(); i++) {
				ArrayList tmpsubList = (ArrayList) tmpList.get(i);
				if (tmpsubList != null) {
					DocumentSearchDTO dto = new DocumentSearchDTO();
					dto.setWardId((String) tmpsubList.get(0));// for ward number
					dto.setWardName((String) tmpsubList.get(1));// for ward name
					dto.setHdnSubAreaWardMappingId((String) tmpsubList.get(0) + "~" + (String) tmpsubList.get(2));
					wardList.add(dto);
				}
			}
			logger.info("getPartyDetails-->" + wardList);
		} catch (Exception e) {
			logger.error(e);

		}
		return wardList;
	}

	/**
	 * @param _wardRpatwariId
	 * @return
	 * @throws Exception
	 */
	public ArrayList getMohallaOrVillage(DocumentSearchDTO _refdto, String language) throws Exception {
		ArrayList mohallaList = new ArrayList();
		IGRSCommon common = new IGRSCommon();
		try {
			String[] _mohalla = new String[1];

			/*
			 * if (_refdto.getWardId() != null) { _mohalla[0] = _refdto.getWardId(); }
			 */
			if (_refdto.getSubAreaWardMappingId() != null) {
				_mohalla[0] = _refdto.getSubAreaWardMappingId();
			}
			ArrayList tmpList = common.getMohallaOrVillageNew(_mohalla, language);
			for (int i = 0; i < tmpList.size(); i++) {
				ArrayList tmpsubList = (ArrayList) tmpList.get(i);
				if (tmpsubList != null) {
					DocumentSearchDTO dto = new DocumentSearchDTO();
					dto.setMohallaId((String) tmpsubList.get(0));// for ward
					// number
					dto.setMohallaName((String) tmpsubList.get(1));// for ward
					// name
					mohallaList.add(dto);
				}
			}
			logger.info("getMohalla-->" + mohallaList);
		} catch (Exception e) {
			logger.error(e);
		}
		return mohallaList;
	}

	public ArrayList getTypeBSearchDetails(DocumentSearchDTO _searchDTO) throws Exception {
		ArrayList resultList = new ArrayList();
		ArrayList docsearchList = new ArrayList();
		ArrayList docAdvanceList = new ArrayList();
		DocumentSearchDAO dao = new DocumentSearchDAO();
		System.out.println("flag is" + _searchDTO.getRegFlag());
		// old condition not specifird as of now
		// by Shreeraj
		if (_searchDTO.getRegFlag() != null) {
			if (_searchDTO.getRegFlag().equalsIgnoreCase("new")) {
				String[] docArray = new String[7];
				try {

					String sqlQry = "SELECT distinct A.REGISTRATION_NUMBER FROM IGRS_REG_TXN_DETLS A,IGRS_REG_PROPRTY_DTLS B where B.DISTRICT_ID=?  AND  B.TEHSIL_ID=?  " + "AND B.WARD_ID=?  AND B.MOHALLA_ID=?  AND B.AREA_TYPE_ID=?  and  trunc(B.CREATED_DATE) between  to_date(?,'dd/mm/yyyy') and  to_date(?,'dd/mm/yyyy')" + " and  A.REG_TXN_ID=B.REG_TXN_ID ";

					docArray[0] = _searchDTO.getDistId();
					docArray[1] = _searchDTO.getTehisilId();
					docArray[2] = _searchDTO.getHdnSubAreaWardMappingId().split("~")[0];
					docArray[3] = _searchDTO.getMohallaId().split("~")[0];
					docArray[4] = _searchDTO.getAreaTypeId();
					docArray[5] = _searchDTO.getFromDate();// slotbd.getDate(_searchDTO.getFromDate());
					docArray[6] = _searchDTO.getToDate();// slotbd.getDate(_searchDTO.getToDate());
					// docArray[7]=_searchDTO.getPropertyAddr();
					docsearchList = (ArrayList) dao.getTypeBSearchDetails(docArray, sqlQry);// return
					// nothin
					logger.debug("--docsearchList--<>" + docsearchList);
					String regId = "";
					if (docsearchList != null && docsearchList.size() > 0) {
						for (int i = 0; i < docsearchList.size(); i++) {

							ArrayList tmpList = (ArrayList) docsearchList.get(i);
							logger.debug("--tmpList--" + tmpList + "--" + tmpList.size());
							regId = (String) tmpList.get(0);
							docAdvanceList = (ArrayList) dao.getDocAdvanceSearchDetails(regId);
							logger.debug("--docAdvanceList--" + docAdvanceList + "--" + docAdvanceList.size());
							if (docAdvanceList.size() > 0) {

								for (int j = 0; j < docAdvanceList.size(); j++) {
									ArrayList tmpPartyList = (ArrayList) docAdvanceList.get(j);

									logger.debug("--tmpPartyList--" + tmpPartyList + "--" + tmpPartyList.size());
									if (tmpPartyList.size() > 0) {
										logger.debug(tmpPartyList.get(0));
										logger.debug(tmpPartyList.get(1));
										logger.debug(tmpPartyList.get(2));// property_id
										logger.debug(tmpPartyList.get(3));

										ArrayList partyTypeList = new ArrayList();
										ArrayList partyList = dao.getDocPartyDetails((String) tmpPartyList.get(2), _searchDTO);
										logger.debug("partyList--<><>" + partyList + " partyList size===" + partyList.size());
										if (partyList.size() > 0) {
											for (int k = 0; k < partyList.size(); k++) {
												logger.debug(partyList.get(k));
												ArrayList partyDataList = (ArrayList) partyList.get(k);
												if (partyDataList.size() > 0) {
													DocumentSearchDTO partyTypeDto = new DocumentSearchDTO();
													partyTypeDto.setBuyerName((String) partyDataList.get(2));
													logger.debug("buyer name-----" + partyTypeDto.getBuyerName());
													partyTypeDto.setBuyerType((String) partyDataList.get(1));
													logger.debug("buyer type-------" + partyTypeDto.getBuyerType());
													partyTypeDto.setRegistNumber(regId);
													logger.debug("reg id------" + partyTypeDto.getRegistNumber());
													partyTypeDto.setDateOfReg((String) tmpPartyList.get(1));
													logger.debug("date of reg----" + partyTypeDto.getDateOfReg());
													partyTypeDto.setAddress((String) tmpPartyList.get(3));
													resultList.add(partyTypeDto);
													logger.debug("size of list " + resultList.size());
												}
											}
										}
										logger.debug("partyTypeList--<>" + partyTypeList);
									}
								}
							}

						}
					}

					logger.debug(resultList);
				} catch (Exception e) {
					logger.error(e);
				}
			}
		}

		return resultList;
	}
	public DocumentSearchDTO getOthersFeeDuty(String _retFunId, String _serId, String _userType) throws Exception {
		DocumentSearchDTO searchDto = new DocumentSearchDTO();
		try {
			System.out.println("values are.........." + _retFunId + "service id is" + _serId + "user type id is.." + _userType);
			IGRSCommon common = new IGRSCommon();
			logger.debug("in bd--<><>" + _retFunId + _serId + _userType);
			/*
			 * ArrayList otherList=common.getOthersFeeDuty(_retFunId, _serId, _userType); if(otherList!=null && otherList.size()>0){
			 * 
			 * searchDto.setOtherFee((String)otherList.get(1).toString()); searchDto.setTotalFee((String)otherList.get(2)); searchDto.setServiceFee((String)otherList.get(0)); }
			 */
			String totalfee = common.getOthersFeeDutyNew(_retFunId, _serId, _userType);
			if ("".equalsIgnoreCase(totalfee)) {
				totalfee = "0";
			}
			searchDto.setTotalFee(totalfee);
		} catch (Exception e) {
			e.printStackTrace();

		}

		return searchDto;
	}
	// added by shruti-13th aug 2013
	public ArrayList getSrchAResultList(String userId) throws Exception {
		ArrayList partyList = null;
		ArrayList list = new ArrayList();
		ArrayList list1 = new ArrayList();
		try {
			partyList = bo.getSrchAResultList(userId);

			if (partyList != null && partyList.size() > 0) {
				for (int i = 0; i < partyList.size(); i++) {

					DocumentSearchDTO searchDto = new DocumentSearchDTO();
					list = (ArrayList) partyList.get(i);
					searchDto.setRefId((String) list.get(0));
					searchDto.setRegistNumber((String) list.get(1));
					searchDto.setDateOfReg((String) list.get(2));
					searchDto.setRegnoFlag((String) list.get(3));
					searchDto.setHdnDocId((String) list.get(0) + "~" + (String) list.get(1) + "~" + (String) list.get(3));
					// modified by shruti
					searchDto.setOldRegNo("-" + "-" + "-" + "-" + "-" + "-" + "-");
					searchDto.setSearchDate("-");
					searchDto.setDistName("-");
					searchDto.setSroName("-");
					searchDto.setSrName("-");
					searchDto.setBookNo("-");
					searchDto.setVolNo("-");
					searchDto.setSerialNo("-");
					// end
					list1.add(searchDto);

					/*
					 * DocumentSearchDTO searchDto=new DocumentSearchDTO(); list = (ArrayList) partyList.get(i); searchDto.setRefId((String) list.get(0)); searchDto.setRegistNumber((String) list.get(1)); searchDto.setSearchDate((String) list.get(2)); searchDto.setRegnoFlag((String) list.get(3)); searchDto.setHdnDocId((String) list.get(0)+"~"+(String) list.get(1)+"~"+(String) list.get(3)); searchDto.setDistName((String) list.get(4)); searchDto.setSroName((String) list.get(5)); searchDto.setSrName((String) list.get(6)); searchDto.setBookNo((String) list.get(7)); searchDto.setVolNo((String) list.get(8)); searchDto.setSerialNo((String) list.get(9)); searchDto.setDateOfReg((String) list.get(10)); searchDto.setOldRegNo((String) list.get(4)+"/"+(String) list.get(5)+"/"+(String) list.get(6)+"/"+(String) list.get(7)+"/"+(String) list.get(8)+"/"+(String) list.get(9)+"/"+(String) list.get(10)); list1.add(searchDto);
					 */
				}

			}
			logger.info("getSrchAResultList-->" + list1);
		} catch (Exception e) {

			logger.error(e);

		}

		return list1;
	}
	public ArrayList getSrchBResultList(String userId) throws Exception {
		ArrayList partyList = null;
		ArrayList list = new ArrayList();
		ArrayList list1 = new ArrayList();
		try {
			partyList = bo.getSrchBResultList(userId);

			if (partyList != null && partyList.size() > 0) {
				for (int i = 0; i < partyList.size(); i++) {
					DocumentSearchDTO searchDto = new DocumentSearchDTO();
					list = (ArrayList) partyList.get(i);
					searchDto.setRefId((String) list.get(0));
					searchDto.setRegistNumber((String) list.get(1));
					searchDto.setDateOfReg((String) list.get(2));
					searchDto.setRegnoFlag((String) list.get(3));
					searchDto.setHdnDocId((String) list.get(0) + "~" + (String) list.get(1) + "~" + (String) list.get(3));
					// added by shruti
					searchDto.setOldRegNo("-" + "-" + "-" + "-" + "-" + "-" + "-");
					searchDto.setSearchDate("-");
					searchDto.setDistName("-");
					searchDto.setSroName("-");
					searchDto.setSrName("-");
					searchDto.setBookNo("-");
					searchDto.setVolNo("-");
					searchDto.setSerialNo("-");
					// end
					list1.add(searchDto);
				}

			}
			logger.info("getSrchBResultList-->" + list1);
		} catch (Exception e) {
			logger.error(e);

		}

		return list1;
	}
	// adedd by shruti-16th aig 2013
	public ArrayList getSrchLogList(String toDate) throws Exception {
		ArrayList partyList = null;
		ArrayList list = null;
		ArrayList list1 = new ArrayList();
		try {
			partyList = bo.getSrchLogList(toDate);

			if (partyList != null && partyList.size() > 0) {
				for (int i = 0; i < partyList.size(); i++) {
					DocumentSearchDTO searchDto = new DocumentSearchDTO();
					list = (ArrayList) partyList.get(i);
					searchDto.setUserId((String) list.get(0));
					searchDto.setUserType((String) list.get(1));
					if (searchDto.getUserType().equalsIgnoreCase("I")) {
						searchDto.setUserType("INTERNAL USER");
					} else {
						searchDto.setUserType("EXTERNAL USER");
					}
					searchDto.setRecordCount((String) list.get(2));
					list1.add(searchDto);
				}

			}
			logger.info("getSrchBResultList-->" + list1);
		} catch (Exception e) {
			logger.error(e);

		}

		return list1;
	}
	public ArrayList getDownloadLogList(String toDate) throws Exception {
		ArrayList partyList = null;
		ArrayList list = null;
		ArrayList list1 = new ArrayList();
		try {
			partyList = bo.getDownloadLogList(toDate);

			if (partyList != null && partyList.size() > 0) {
				for (int i = 0; i < partyList.size(); i++) {
					DocumentSearchDTO searchDto = new DocumentSearchDTO();
					list = (ArrayList) partyList.get(i);
					searchDto.setUserId((String) list.get(0));
					searchDto.setUserType((String) list.get(1));
					if (searchDto.getUserType().equalsIgnoreCase("I")) {
						searchDto.setUserType("INTERNAL USER");
					} else {
						searchDto.setUserType("EXTERNAL USER");
					}
					searchDto.setRecordCount((String) list.get(2));
					list1.add(searchDto);
				}

			}
			logger.info("getSrchBResultList-->" + list1);
		} catch (Exception e) {
			logger.error(e);

		}

		return list1;
	}
	public ArrayList getUserDetailsList(String userid) throws Exception {
		ArrayList detailsList = null;
		ArrayList list = null;
		ArrayList list1 = new ArrayList();
		try {
			detailsList = bo.getuserDetailsList(userid);

			if (detailsList != null && detailsList.size() > 0) {
				for (int i = 0; i < detailsList.size(); i++) {
					DocumentSearchDTO searchDto = new DocumentSearchDTO();
					list = (ArrayList) detailsList.get(i);
					logger.info("DETAILS LIST SIZE>>>>" + list);
					searchDto.setSrno(i + 1);
					searchDto.setSearchType((String) list.get(0));
					searchDto.setRegistNumber((String) list.get(1));
					searchDto.setDistName((String) list.get(2));
					searchDto.setSroName((String) list.get(3));
					searchDto.setSrName((String) list.get(4));
					searchDto.setBookNo((String) list.get(5));
					searchDto.setVolNo((String) list.get(6));
					searchDto.setSerialNo((String) list.get(7));
					searchDto.setRegDate((String) list.get(8));
					searchDto.setRefId((String) list.get(9));
					searchDto.setOldRegNo((String) list.get(2) + "/" + (String) list.get(3) + "/" + (String) list.get(4) + "/" + (String) list.get(5) + "/" + (String) list.get(6) + "/" + (String) list.get(7) + "/" + (String) list.get(8));
					list1.add(searchDto);
				}
			}
			logger.info("getuserDetailsList-->" + list1);
		} catch (Exception e) {
			logger.error(e);
		}
		return list1;
	}
	// adedd by shruti
	public DocumentSearchDTO checkRegistrationIdNew(String _refRegId, String flag, String language) throws Exception {
		ArrayList resultList = null;
		ArrayList typeaList = new ArrayList();
		DocumentSearchDTO dto = new DocumentSearchDTO();
		DocumentSearchDTO resultdto = new DocumentSearchDTO();
		try {

			resultList = bo.checkRegistrationIdNew(_refRegId, flag, language);
			logger.debug("in bd resultList size -->" + resultList.size());
			if (resultList != null && resultList.size() > 1) {
				dto.setRegistNumber(_refRegId);
				dto.setApplicationId((String) resultList.get(0));
				dto.setEstampCode((String) resultList.get(1));
				ArrayList tmp = (ArrayList) resultList.get(2);
				ArrayList paryList = new ArrayList();
				ArrayList tmpList = (ArrayList) resultList.get(2);
				PartyDetailsDTO tmppdto = new PartyDetailsDTO();

				if (tmpList != null && tmpList.size() > 0) {
					logger.debug("--size--<>" + tmpList.size());
					for (int j = 0; j < tmpList.size(); j++) {
						logger.debug("tmp list  for--<>" + j);
						ArrayList arl = (ArrayList) tmpList.get(j);
						if (arl != null && arl.size() > 0) {
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
							pdto.setFatherName((String) arl.get(16));
							pdto.setMotherName((String) arl.get(17));
							pdto.setPropShare("");
							// added by shruti-5/8/2013
							pdto.setPartyChk((String) arl.get(15));
							pdto.setOrgName((String) arl.get(18));
							pdto.setAuthPrsnName((String) arl.get(19));
							// end
							paryList.add(pdto);
						}
					}
				}
				ArrayList tmppropertyList = (ArrayList) resultList.get(3);
				ArrayList propertyList = new ArrayList();
				DocumentSearchDTO propertydto = new DocumentSearchDTO();
				if (tmppropertyList != null && tmppropertyList.size() > 0) {
					for (int k = 0; k < tmppropertyList.size(); k++) {
						ArrayList temppropList = (ArrayList) tmppropertyList.get(k);
						logger.debug("property list size -->" + temppropList.size());
						logger.debug("property ref number-->" + (String) temppropList.get(0));
						propertydto.setPropertyId((String) temppropList.get(1));
						propertydto.setPropType((String) temppropList.get(2));
						propertydto.setUseType((String) temppropList.get(2));
						propertydto.setDistName((String) temppropList.get(3));
						propertydto.setTehisilName((String) temppropList.get(4));
						propertydto.setWardName((String) temppropList.get(5));
						propertydto.setMohallaName((String) temppropList.get(6));
						// modified by shruti-6/8/2013
						propertydto.setAreaTypeName((String) temppropList.get(7));
						propertydto.setGorvMunicplBody((String) temppropList.get(8));
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
						propertydto.setConstuctedArea("");
						propertydto.setCeilingType("");
						propertyList.add(propertydto);
						// end
					}
				}

				ArrayList caveatlist = (ArrayList) resultList.get(4);
				ArrayList caveatslist = new ArrayList();
				CaveatsDTO caveatsDTO = new CaveatsDTO();
				if (caveatlist != null && caveatlist.size() > 0) {
					for (int k = 0; k < caveatlist.size(); k++) {
						caveatsDTO = new CaveatsDTO();
						logger.debug(caveatlist.get(k));
						ArrayList tmpll = (ArrayList) caveatlist.get(k);
						// caveatsDTO.setCaveatType((String) tmpll.get(0));
						// caveatsDTO.setCaveatDetails((String) tmpll.get(1));
						// caveatsDTO.setCaveatSorderStatus((String)
						// tmpll.get(2));
						caveatsDTO.setBankChargeId((String) tmpll.get(0));
						caveatsDTO.setCaveatStatus((String) tmpll.get(1));
						if (caveatsDTO.getCaveatStatus().equals("LOGGED")) {
							caveatsDTO.setCaveatUploadedDoc((String) tmpll.get(2));
						}
						if (caveatsDTO.getCaveatStatus().equals("RELEASED")) {
							caveatsDTO.setCaveatUploadedDoc((String) tmpll.get(3));
						}
						caveatslist.add(caveatsDTO);
					}
				}

				ArrayList complianceList = (ArrayList) resultList.get(5);
				ArrayList tmpcomplList = new ArrayList();
				DocumentSearchDTO subclausedto = new DocumentSearchDTO();
				DocumentSearchDTO compliancedto = new DocumentSearchDTO();
				if (complianceList != null && complianceList.size() > 0) {
					for (int k = 0; k < complianceList.size(); k++) {
						subclausedto = new DocumentSearchDTO();
						logger.debug(complianceList.get(k));
						ArrayList tmpcompl = (ArrayList) complianceList.get(k);
						compliancedto = new DocumentSearchDTO();
						compliancedto.setComplianceName((String) tmpcompl.get(0));
						tmpcomplList.add(compliancedto);
					}
				}
				dto.setComplianceList(tmpcomplList);
				dto.setCaveatslist(caveatslist);
				logger.debug(" in bd party list? " + paryList);
				dto.setPropertyList(propertyList);
				logger.info("in bd bd propertyList size-->" + propertyList.size());
				logger.info("in bd bd party size-->" + paryList.size());
				tmppdto.setPartyList(paryList);
				dto.setPartyDTO(tmppdto);
				typeaList.add(dto);
				dto.setTypeBresult(typeaList);
			} else {
				dto = null;
			}
			logger.info("in bd checkRegistrationId-->" + resultList);
		} catch (Exception e) {
			logger.error(e);
		}
		return dto;
	}

	public DocumentSearchDTO checkRegistrationIdNewWithProtest(String _refRegId, String flag, String language) throws Exception {
		ArrayList resultList = null;
		ArrayList typeaList = new ArrayList();
		DocumentSearchDTO dto = new DocumentSearchDTO();
		DocumentSearchDTO resultdto = new DocumentSearchDTO();
		try {

			resultList = bo.checkRegistrationIdNewWithProtest(_refRegId, flag, language);
			logger.debug("in bd resultList size -->" + resultList.size());
			if (resultList != null && resultList.size() > 1) {
				dto.setRegistNumber(_refRegId);
				dto.setApplicationId((String) resultList.get(0));
				dto.setEstampCode((String) resultList.get(1));
				ArrayList tmp = (ArrayList) resultList.get(2);
				ArrayList paryList = new ArrayList();
				ArrayList tmpList = (ArrayList) resultList.get(2);
				PartyDetailsDTO tmppdto = new PartyDetailsDTO();

				if (tmpList != null && tmpList.size() > 0) {
					logger.debug("--size--<>" + tmpList.size());
					for (int j = 0; j < tmpList.size(); j++) {
						logger.debug("tmp list  for--<>" + j);
						ArrayList arl = (ArrayList) tmpList.get(j);
						if (arl != null && arl.size() > 0) {
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
							pdto.setFatherName((String) arl.get(16));
							pdto.setMotherName((String) arl.get(17));
							pdto.setPropShare("");
							// added by shruti-5/8/2013
							pdto.setPartyChk((String) arl.get(15));
							pdto.setOrgName((String) arl.get(18));
							pdto.setAuthPrsnName((String) arl.get(19));
							// end
							paryList.add(pdto);
						}
					}
				}
				ArrayList tmppropertyList = (ArrayList) resultList.get(3);
				ArrayList propertyList = new ArrayList();
				ArrayList khasraDetails = new ArrayList();
				ArrayList protestDetails = new ArrayList();
				DocumentSearchDTO propertydto = new DocumentSearchDTO();
				DocumentSearchDTO khasradto = new DocumentSearchDTO();
				DocumentSearchDTO protestdto = new DocumentSearchDTO();
				if (tmppropertyList != null && tmppropertyList.size() > 0) {
					for (int k = 0; k < tmppropertyList.size(); k++) {
						ArrayList temppropList = (ArrayList) tmppropertyList.get(k);
						logger.debug("property list size -->" + temppropList.size());
						logger.debug("property ref number-->" + (String) temppropList.get(0));
						propertydto.setPropertyId((String) temppropList.get(1));
						propertydto.setPropType((String) temppropList.get(2));
						propertydto.setUseType((String) temppropList.get(2));
						propertydto.setDistName((String) temppropList.get(3));
						propertydto.setTehisilName((String) temppropList.get(4));
						propertydto.setWardName((String) temppropList.get(5));
						propertydto.setMohallaName((String) temppropList.get(6));
						// modified by shruti-6/8/2013
						propertydto.setAreaTypeName((String) temppropList.get(7));
						propertydto.setGorvMunicplBody((String) temppropList.get(8));
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
						propertydto.setConstuctedArea("");
						propertydto.setCeilingType("");
						propertyList.add(propertydto);
						propertydto = new DocumentSearchDTO();
						System.out.println("Property Id from tmppropertyList1>>>>>" + (String) temppropList.get(1));
						String propID = (String) temppropList.get(1);
						ArrayList khasraDet = new ArrayList();
						ArrayList protestDet = new ArrayList();
						protestDet = bo.protestDetls(propID, language);
						khasraDet = bo.getKhasraDetail(propID);
						logger.debug("khasra list size -->" + khasraDet.size());
						if (khasraDet != null && khasraDet.size() > 0) {
							System.out.println(khasraDet.size());
							for (int j = 0; j < khasraDet.size(); j++) {
								ArrayList khasraList = (ArrayList) khasraDet.get(j);
								khasradto.setKhasraNumber((String) (khasraList.get(0)));
								khasradto.setKhasraName((String) khasraList.get(1));
								khasradto.setLagaan((String) khasraList.get(2));
								khasradto.setRinPushtikaNumber((String) khasraList.get(3));
								khasradto.setNorthBoundary((String) khasraList.get(4));
								khasradto.setSouthBoundary((String) khasraList.get(5));
								khasradto.setEastBoundary((String) khasraList.get(6));
								khasradto.setWestBoundary((String) khasraList.get(7));
								khasraDetails.add(khasradto);
								khasradto = new DocumentSearchDTO();
								System.out.println(khasraDetails.size());
							}
						}
						if (protestDet != null && protestDet.size() > 0) {
							System.out.println(protestDet.size());
							for (int j = 0; j < protestDet.size(); j++) {
								ArrayList protestList = (ArrayList) protestDet.get(j);
								protestdto.setPropertyId((String) (protestList.get(0)));
								protestdto.setProtestTxnId((String) (protestList.get(1)));
								protestdto.setCourtOrderDocPath((String) protestList.get(2));
								protestdto.setCourtOrderDoc((String) protestList.get(3));
								protestdto.setProtestStatus((String) protestList.get(4));
								protestDetails.add(protestdto);
								protestdto = new DocumentSearchDTO();
								System.out.println(protestDetails.size());
								String docPath = (String) protestList.get(2);
								String docName = (String) protestList.get(3);
							}
						}
						// end
					}
				}

				ArrayList caveatlist = (ArrayList) resultList.get(4);
				ArrayList caveatslist = new ArrayList();
				CaveatsDTO caveatsDTO = new CaveatsDTO();
				if (caveatlist != null && caveatlist.size() > 0) {
					for (int k = 0; k < caveatlist.size(); k++) {
						caveatsDTO = new CaveatsDTO();
						logger.debug(caveatlist.get(k));
						ArrayList tmpll = (ArrayList) caveatlist.get(k);
						// caveatsDTO.setCaveatType((String) tmpll.get(0));
						// caveatsDTO.setCaveatDetails((String) tmpll.get(1));
						// caveatsDTO.setCaveatSorderStatus((String)
						// tmpll.get(2));
						caveatsDTO.setBankChargeId((String) tmpll.get(0));
						caveatsDTO.setCaveatStatus((String) tmpll.get(1));
						if (caveatsDTO.getCaveatStatus().equals("LOGGED")) {
							caveatsDTO.setCaveatUploadedDoc((String) tmpll.get(2));
						}
						if (caveatsDTO.getCaveatStatus().equals("RELEASED")) {
							caveatsDTO.setCaveatUploadedDoc((String) tmpll.get(3));
						}
						caveatslist.add(caveatsDTO);
					}
				}

				ArrayList complianceList = (ArrayList) resultList.get(5);
				ArrayList tmpcomplList = new ArrayList();
				DocumentSearchDTO subclausedto = new DocumentSearchDTO();
				DocumentSearchDTO compliancedto = new DocumentSearchDTO();
				if (complianceList != null && complianceList.size() > 0) {
					for (int k = 0; k < complianceList.size(); k++) {
						subclausedto = new DocumentSearchDTO();
						logger.debug(complianceList.get(k));
						ArrayList tmpcompl = (ArrayList) complianceList.get(k);
						compliancedto = new DocumentSearchDTO();
						compliancedto.setComplianceName((String) tmpcompl.get(0));
						tmpcomplList.add(compliancedto);
					}
				}
				dto.setComplianceList(tmpcomplList);
				dto.setCaveatslist(caveatslist);
				logger.debug(" in bd party list? " + paryList);
				dto.setPropertyList(propertyList);
				dto.setKhasraList(khasraDetails);
				dto.setProtestList(protestDetails);
				logger.info("in bd bd propertyList size-->" + propertyList.size());
				logger.info("in bd bd party size-->" + paryList.size());
				tmppdto.setPartyList(paryList);
				dto.setPartyDTO(tmppdto);
				typeaList.add(dto);
				dto.setTypeBresult(typeaList);
			} else {
				dto = null;
			}
			logger.info("in bd checkRegistrationId-->" + resultList);
		} catch (Exception e) {
			logger.error(e);
		}
		return dto;
	}

	// added by shruti
	public String checkResumeState(String docTxnId) throws Exception {

		ArrayList ret = bo.checkResumeState(docTxnId);
		System.out.println("I m here " + ret.size());

		String functionId = null;

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				try {
					functionId = ((String) ret.get(0));
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		}
		return functionId;
	}

	public ArrayList getTypeBSearchRecordDetails(String DocTxnId) throws Exception {
		ArrayList resultList = new ArrayList();
		ArrayList docsearchList = new ArrayList();
		ArrayList docAdvanceList = new ArrayList();
		DocumentSearchDAO dao = new DocumentSearchDAO();
		DocumentSearchDTO searchDto = new DocumentSearchDTO();
		try {
			docsearchList = (ArrayList) dao.getTypeBSearchRecordDetails(DocTxnId);
			logger.debug("--docsearchList--<>" + docsearchList);
			String regId = "";
			if (docsearchList != null && docsearchList.size() > 0) {
				for (int i = 0; i < docsearchList.size(); i++) {
					ArrayList tmpList = (ArrayList) docsearchList.get(i);
					searchDto.setRefId((String) tmpList.get(0));
					searchDto.setDistId((String) tmpList.get(1));
					searchDto.setTehisilId((String) tmpList.get(2));
					searchDto.setAreaTypeId((String) tmpList.get(3));
					searchDto.setWardId((String) tmpList.get(4));
					searchDto.setMohallaId((String) tmpList.get(5));
					searchDto.setFromDate((String) tmpList.get(6));
					searchDto.setToDate((String) tmpList.get(7));
					resultList.add(searchDto);
				}
			}

			logger.debug(resultList);
		} catch (Exception e) {
			logger.error(e);
		}

		return resultList;
	}

	// added by shruti
	public String chkUser(String userId) throws Exception {

		String typeid = "";
		ArrayList resultList = new ArrayList();
		DocumentSearchBO bo = new DocumentSearchBO();
		DocumentSearchDTO searchDto = new DocumentSearchDTO();
		try {
			typeid = bo.chkUser(userId);
			logger.debug(resultList);
		} catch (Exception e) {
			logger.error(e);
		}

		return typeid;
	}

	public ArrayList getExternalUserDtls(String userId) throws Exception {

		ArrayList resultList = new ArrayList();
		DocumentSearchBO bo = new DocumentSearchBO();
		DocumentSearchDTO searchDto = new DocumentSearchDTO();
		try {
			resultList = (ArrayList) bo.getExternalUserDtls(userId);
			if (resultList != null && resultList.size() > 0) {
				for (int i = 0; i < resultList.size(); i++) {
					ArrayList tmpList = (ArrayList) resultList.get(i);
					searchDto.setParentDistrictId((String) tmpList.get(0));
					searchDto.setParentDistrictName((String) tmpList.get(1));
					resultList.add(searchDto);
					logger.info("RESULTLIST>>>>>>>>>" + resultList);
				}
			}

			logger.debug(resultList);
		} catch (Exception e) {
			logger.error(e);
		}

		return resultList;
	}

	public ArrayList getInternalUserDtls(String officeId) throws Exception {

		ArrayList resultList = new ArrayList();
		DocumentSearchBO bo = new DocumentSearchBO();
		DocumentSearchDTO searchDto = new DocumentSearchDTO();
		try {
			resultList = (ArrayList) bo.getExternalUserDtls(officeId);
			if (resultList != null && resultList.size() > 0) {
				for (int i = 0; i < resultList.size(); i++) {
					ArrayList tmpList = (ArrayList) resultList.get(i);
					searchDto.setParentOfficeName((String) tmpList.get(0));
					searchDto.setParentDistrictId((String) tmpList.get(1));
					searchDto.setParentDistrictName((String) tmpList.get(2));
					resultList.add(searchDto);
					logger.info("RESULTLIST>>>>>>>>>" + resultList);
				}
			}

			logger.debug(resultList);
		} catch (Exception e) {
			logger.error(e);
		}

		return resultList;
	}
	// added by shruti 23rd sep 2013
	public String storeSearchATxnDetails(DocumentSearchDTO dsto, String userId, String functionId) throws Exception {

		String doctxnid = null;
		try {
			doctxnid = bo.storeSearchATxnDetails(dsto, userId, functionId);
			System.out.println("I am in bd===========_docTxnId  " + doctxnid);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return doctxnid;
	}
	public boolean chkPreviousDownloadedExist(DocumentSearchDTO dsto, String userId, String regNo) throws Exception {

		boolean flag = false;
		try {
			flag = bo.chkPreviousDownloadedExist(dsto, userId, regNo);
			System.out.println("I am in bd===========chkPreviousDownloadedExist  " + flag);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	// added by shruti---7 july 2014
	public String getNoOfDocYears(String fromDate, String toDate) throws Exception {

		String diff = "";
		try {
			IGRSCommon common = new IGRSCommon();
			diff = common.checkYearsOfDoc(fromDate, toDate);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return diff;
	}

	// added by shruti---26 nov 2014
	public String searchGoLiveDate(String distId) throws Exception {

		String date = "";
		try {
			date = bo.searchGoLiveDate(distId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	public ArrayList searchRegisteredDoc(DocumentSearchDTO dsdto, String language) throws ParseException {
		ArrayList resultList = new ArrayList();
		ArrayList docsearchList = new ArrayList();
		docsearchList = (ArrayList) bo.searchRegisteredDoc(dsdto, language);
		return docsearchList;
	}
	public ArrayList searchUnRegisteredDoc(DocumentSearchDTO dsdto) {
		ArrayList resultList = new ArrayList();
		ArrayList docsearchList = new ArrayList();
		docsearchList = (ArrayList) bo.searchUnRegisteredDoc(dsdto);
		return docsearchList;
	}

	public String storeBTypeSearchNew(String paymentflag, DocumentSearchDTO dsto, DocumentSearchDTO dsdto, String userId, String functionId) throws Exception {

		String doctxnid = null;
		try {
			doctxnid = bo.storeBTypeSearchNew(paymentflag, dsto, dsdto, userId, functionId);
			System.out.println("I am in bd===========_docTxnId  " + doctxnid);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return doctxnid;
	}
	public String getRegTxnID(String eRegNo) throws Exception {
		DeliveryOfDocumentsBO dodBO = new DeliveryOfDocumentsBO();
		return dodBO.getRegTxnID(eRegNo);
	}
	public String getDownloadStatus(String refID) throws Exception {

		return bo.getDownloadStatus(refID);
	}
	public ArrayList getPaymentList(String refID, String funID) throws Exception {
		ArrayList detailsList = null;
		ArrayList list = null;
		ArrayList list1 = new ArrayList();
		try {
			detailsList = bo.getPaymentList(refID, funID);

			if (detailsList != null && detailsList.size() > 0) {
				for (int i = 0; i < detailsList.size(); i++) {
					DocumentSearchDTO searchDto = new DocumentSearchDTO();
					list = (ArrayList) detailsList.get(i);
					logger.info("DETAILS LIST SIZE>>>>" + list);
					searchDto.setPaidAmount(Float.parseFloat(list.get(0).toString()));
					searchDto.setTotalFee((String) list.get(1));
					list1.add(searchDto);
				}
			}
			logger.info("getuserDetailsList-->" + list1);
		} catch (Exception e) {
			logger.error(e);
		}
		return list1;
	}
	public boolean updateSearchStatus(String refID, String funID, String status) throws Exception {

		return bo.updateSearchStatus(refID, funID, status);
	}
	public boolean updateDownloadStatus(String refID) throws Exception {

		return bo.updateDownloadStatus(refID);
	}
	public boolean updateDownloadStatus(String refID, String docId) throws Exception {

		return bo.updateDownloadStatus(refID, docId);
	}
	public boolean getSignatureCheck(String refID) throws Exception {

		return bo.getSignatureCheck(refID);
	}
	public int drawString(Graphics g, String s, int x, int y, int width) {
		FontMetrics fm = g.getFontMetrics();
		int lineHeight = fm.getHeight();
		boolean underlined = false;
		int curX = x;
		int curY = y;
		if (s != null) {
			// String words[] = s.split(" ");
			String words[] = s.split(Pattern.quote("||"));

			String as[];
			int j = (as = words).length;
			for (int i = 0; i < j; i++) {
				String word = as[i];
				System.out.println("words : " + word);
				int wordWidth = fm.stringWidth((new StringBuilder(String.valueOf(word))).append(" ").toString());
				if (word.equalsIgnoreCase("<ULine>"))
					underlined = true;
				else if (word.equalsIgnoreCase("</ULine>")) {
					underlined = false;
				} else {
					if (curX + wordWidth >= x + width) {
						curY += lineHeight;
						curX = x;
					} else if (word.equalsIgnoreCase("\n")) {
						curY += lineHeight;
						curX = x;
					}
					if (underlined) {
						g.drawString(word, curX, curY);
						g.drawLine(curX, curY + 2, curX + wordWidth, curY + 2);
					} else {
						g.drawString(word, curX, curY);
					}
					curX += wordWidth;
				}
			}

		}
		return curY;
	}
	public String burnRequestIdForPDF(String requestId, String inputFilePath, String folderPath) {
		String docName = "";
		String finalPath = "";
		int centerOfPage = 0;
		try {
			int pageWidth = (int) PageSize.A4.getWidth();
			int pageHeight = (int) PageSize.A4.getHeight();
			String inputfile = (new StringBuilder(String.valueOf(inputFilePath))).append(File.separatorChar).append(docName).toString();
			PdfReader reader1 = new PdfReader(inputfile);
			int noOfPages1 = reader1.getNumberOfPages();
			PdfStamper stamper = new PdfStamper(reader1, new FileOutputStream((new StringBuilder(String.valueOf(folderPath))).append(File.separator).append(requestId).append(".pdf").toString()));
			finalPath = new StringBuilder(String.valueOf(folderPath)).append(File.separator).append(requestId).append(".pdf").toString();
			centerOfPage = pageWidth / 2;
			for (int i = 1; i <= noOfPages1; i++) {
				PdfContentByte cbf = stamper.getOverContent(i);
				Graphics2D g2 = cbf.createGraphicsShapes(pageWidth, pageHeight);
				// Font font = new Font("Mangal", 0, 8);
				// g2.setFont(font);
				// g2.setColor(Color.BLACK);
				drawString(g2, requestId, 250, pageHeight - (int) (2D * (double) pageHeight) / 100 - 20, (40 * pageWidth) / 100);
				// font = new Font("Mangal", 0, 8);
				// g2.setFont(font);
				g2.dispose();
				g2 = null;
			}

			stamper.close();
			reader1.close();
		} catch (IOException ex) {
			logger.error("DocumentOperations burnRequestIdForPDF method, IOException caught.", ex);
		} catch (Throwable ex) {
			logger.error("DocumentOperations burnRequestIdForPDF method, Exception caught.", ex);
		}
		return finalPath;
	}
	public String burnReqIdAndReqDataForPDF(String requestId, String inputFilePath, String folderPath, String reqData) {
		String docName = "";
		String finalPath = "";
		int centerOfPage = 0;

		try {
			int pageWidth = (int) PageSize.A4.getWidth();
			int pageHeight = (int) PageSize.A4.getHeight();
			System.out.println("pageWidth : " + pageWidth);
			System.out.println("pageHeight : " + pageHeight);
			String inputfile = (new StringBuilder(String.valueOf(inputFilePath))).append(File.separatorChar).append(docName).toString();
			PdfReader reader1 = new PdfReader(inputfile);
			int noOfPages1 = reader1.getNumberOfPages();
			PdfStamper stamper = new PdfStamper(reader1, new FileOutputStream((new StringBuilder(String.valueOf(folderPath))).append(File.separator).append(requestId).append(".pdf").toString()));
			finalPath = new StringBuilder(String.valueOf(folderPath)).append(File.separator).append(requestId).append(".pdf").toString();
			centerOfPage = pageWidth / 2;

			AffineTransform affineTransform = new AffineTransform();

			affineTransform.rotate(Math.PI / 4.0);

			for (int i = 1; i <= noOfPages1; i++) {
				PdfContentByte cbf = stamper.getOverContent(i);
				Graphics2D g2 = cbf.createGraphicsShapes(pageWidth, pageHeight);
				AffineTransform orig = g2.getTransform();
				Font font1 = new Font("Mangal", 0, 7);
				Font font2 = new Font("Mangal", 0, 8);
				g2.setFont(font1);
				g2.setColor(Color.BLACK);
				g2.rotate(Math.PI / 4);

				drawString(g2, reqData, 200, -50, -50);
				g2.setTransform(orig);
				g2.setFont(font2);
				g2.setColor(Color.BLACK);

				// drawString(g2, requestId, 450, pageHeight - (int)(2D *
				// (double)pageHeight) / 100 - 15, (40 * pageWidth) / 100);
				drawString(g2, requestId, 250, pageHeight - (int) (2D * (double) pageHeight) / 100 - 20, (40 * pageWidth) / 100);

				g2.dispose();
				g2 = null;
			}

			stamper.close();
			reader1.close();
		} catch (IOException ex) {
			logger.error("DocumentOperations burnRequestIdForPDF method, IOException caught.", ex);
		} catch (Throwable ex) {
			logger.error("DocumentOperations burnRequestIdForPDF method, Exception caught.", ex);
		}
		return finalPath;
	}

	public String getNullvoidFlag(String refID) throws Exception {
		String nullVoidFlag = null;
		try {
			nullVoidFlag = bo.getNullvoidFlag(refID);
			logger.info("nullVoidFlag-->" + nullVoidFlag);
		} catch (Exception e) {
			logger.error(e);

		}
		return nullVoidFlag;
	}

	public String getProtestFlag(String refID) throws Exception {
		String protestFlag = null;
		try {
			protestFlag = bo.getProtestFlag(refID);
			logger.info("protestFlag-->" + protestFlag);
		} catch (Exception e) {
			logger.error(e);

		}
		return protestFlag;
	}
	public ArrayList getNullVoidDetls(String refID) throws Exception {
		ArrayList detailsList = null;
		ArrayList list = null;
		ArrayList list1 = new ArrayList();
		try {
			detailsList = bo.getNullVoidDetls(refID);

			if (detailsList != null && detailsList.size() > 0) {
				for (int i = 0; i < detailsList.size(); i++) {
					DocumentSearchDTO searchDto = new DocumentSearchDTO();
					list = (ArrayList) detailsList.get(i);
					logger.info("DETAILS NullVoidDetls>>>>" + list);
					searchDto.setCourtName((String) list.get(0));
					searchDto.setCourtOrderNo((String) list.get(1));
					searchDto.setCourtOrderDate((String) list.get(2));
					list1.add(searchDto);
				}
			}
			logger.info("getuserDetailsList-->" + list1);
		} catch (Exception e) {
			logger.error(e);
		}
		return list1;
	}
	public ArrayList getProtestId(String refID) throws Exception {
		ArrayList detailsList = null;
		ArrayList list = null;
		ArrayList list1 = new ArrayList();
		try {
			detailsList = bo.getProtestId(refID);

			if (detailsList != null && detailsList.size() > 0) {
				for (int i = 0; i < detailsList.size(); i++) {
					DocumentSearchDTO searchDto = new DocumentSearchDTO();
					list = (ArrayList) detailsList.get(i);
					logger.info("DETAILS Of Protest>>>>" + list);
					searchDto.setProtestId((String) list.get(0));
					searchDto.setProtestDocPath((String) list.get(1));
					searchDto.setProtestDocName((String) list.get(2));
					list1.add(searchDto);
				}
			}
			logger.info("getuserDetailsList-->" + list1);
		} catch (Exception e) {
			logger.error(e);
		}
		return list1;
	}

	public ArrayList getBankChargeId(String refID) throws Exception {
		ArrayList detailsList = null;
		ArrayList list = null;
		ArrayList list1 = new ArrayList();
		try {
			detailsList = bo.getBankChargeId(refID);

			if (detailsList != null && detailsList.size() > 0) {
				for (int i = 0; i < detailsList.size(); i++) {
					DocumentSearchDTO searchDto = new DocumentSearchDTO();
					list = (ArrayList) detailsList.get(i);
					logger.info("DETAILS Of Bank charges" + list);
					searchDto.setBankChargeId((String) list.get(0));
					searchDto.setCaveatStatus((String) list.get(1));
					if (searchDto.getCaveatStatus().equals("LOGGED")) {
						searchDto.setCaveatUploadedDoc((String) list.get(2));
					}
					if (searchDto.getCaveatStatus().equals("RELEASED")) {
						searchDto.setCaveatUploadedDoc((String) list.get(3));
					}
					list1.add(searchDto);
				}
			}
			logger.info("getuserDetailsList-->" + list1);
		} catch (Exception e) {
			logger.error(e);
		}
		return list1;
	}

	// Code changes for protest ---by Rupali

	public DocumentSearchDTO checkRegistrationIdWithProtest(String _refRegId, String language) throws Exception {
		ArrayList resultList = new ArrayList();
		ArrayList typeaList = new ArrayList();
		DocumentSearchDTO dto = new DocumentSearchDTO();
		DocumentSearchDTO resultdto = new DocumentSearchDTO();
		try {
			resultList = bo.checkRegistrationIdwithProtest(_refRegId, language);
			logger.debug("in bd resultList size -->" + resultList.size());
			if (resultList != null && resultList.size() > 1) {
				dto.setRegistNumber(_refRegId);
				dto.setApplicationId((String) resultList.get(0));
				dto.setEstampCode((String) resultList.get(1));
				ArrayList tmp = (ArrayList) resultList.get(2);
				ArrayList paryList = new ArrayList();
				ArrayList tmpList = (ArrayList) resultList.get(2);
				PartyDetailsDTO tmppdto = new PartyDetailsDTO();
				if (tmpList != null && tmpList.size() > 0) {
					logger.debug("--size--<>" + tmpList.size());
					for (int j = 0; j < tmpList.size(); j++) {
						logger.debug("tmp list  for--<>" + j);
						ArrayList arl = (ArrayList) tmpList.get(j);
						if (arl != null && arl.size() > 0) {
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
							pdto.setPropShare((String) arl.get(20));
							pdto.setProofNumber((String) arl.get(21));
							// pdto.setOcupation((String) arl.get(22));
							pdto.setGovtOffName((String) arl.get(22));
							pdto.setGovtOffDesg((String) arl.get(23));
							pdto.setGovtOffAddress((String) arl.get(24));
							paryList.add(pdto);
						}
					}
				}
				ArrayList tmppropertyList = new ArrayList();
				tmppropertyList = (ArrayList) resultList.get(3);
				ArrayList propertyList = new ArrayList();
				ArrayList khasraDetails = new ArrayList();
				ArrayList protestDetails = new ArrayList();
				DocumentSearchDTO propertydto = new DocumentSearchDTO();
				DocumentSearchDTO khasradto = new DocumentSearchDTO();
				DocumentSearchDTO protestdto = new DocumentSearchDTO();
				System.out.println("size of tmppropertyList>>>>>>>>>>>>" + tmppropertyList.size());
				if (tmppropertyList != null && tmppropertyList.size() > 0) { // changes
					// by
					// Shreeraj
					ArrayList temppropList = new ArrayList();

					for (int k = 0; k < tmppropertyList.size(); k++) {
						temppropList = (ArrayList) tmppropertyList.get(k);
						// ArrayList temppropList = new ArrayList();
						// temppropList=(ArrayList) tmppropertyList.get(k);
						logger.debug("property list size -->" + temppropList.size());
						logger.debug("property ref number-->" + (String) temppropList.get(0) + (String) temppropList.get(1));
						propertydto.setPropertyId((String) temppropList.get(1));
						propertydto.setPropType((String) temppropList.get(2));
						propertydto.setUseType((String) temppropList.get(2));
						propertydto.setDistName((String) temppropList.get(3));
						propertydto.setTehisilName((String) temppropList.get(4));
						propertydto.setWardName((String) temppropList.get(5));
						propertydto.setMohallaName((String) temppropList.get(6));
						propertydto.setAreaTypeName((String) temppropList.get(7));
						propertydto.setGorvMunicplBody((String) temppropList.get(8));
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
						propertydto.setPropMap((String) temppropList.get(19));
						propertydto.setConstuctedArea("");
						propertydto.setCeilingType("");
						propertyList.add(propertydto);
						propertydto = new DocumentSearchDTO();

						// modified by shruti---for iteration to be done for
						// each property id
						// by Shreeraj
						System.out.println("Property Id from tmppropertyList1>>>>>" + (String) temppropList.get(1));
						String propID = (String) temppropList.get(1);
						ArrayList khasraDet = new ArrayList();
						ArrayList protestDet = new ArrayList();
						protestDet = bo.protestDetls(propID, language);
						khasraDet = bo.getKhasraDetail(propID);
						logger.debug("khasra list size -->" + khasraDet.size());
						if (khasraDet != null && khasraDet.size() > 0) {
							System.out.println(khasraDet.size());
							for (int j = 0; j < khasraDet.size(); j++) {
								ArrayList khasraList = (ArrayList) khasraDet.get(j);
								khasradto.setKhasraNumber((String) (khasraList.get(0)));
								khasradto.setKhasraName((String) khasraList.get(1));
								khasradto.setLagaan((String) khasraList.get(2));
								khasradto.setRinPushtikaNumber((String) khasraList.get(3));
								khasradto.setNorthBoundary((String) khasraList.get(4));
								khasradto.setSouthBoundary((String) khasraList.get(5));
								khasradto.setEastBoundary((String) khasraList.get(6));
								khasradto.setWestBoundary((String) khasraList.get(7));
								khasraDetails.add(khasradto);
								khasradto = new DocumentSearchDTO();
								System.out.println(khasraDetails.size());
							}
						}
						if (protestDet != null && protestDet.size() > 0) {
							System.out.println(protestDet.size());
							for (int j = 0; j < protestDet.size(); j++) {
								ArrayList protestList = (ArrayList) protestDet.get(j);
								protestdto.setPropertyId((String) (protestList.get(0)));
								protestdto.setProtestTxnId((String) (protestList.get(1)));
								protestdto.setCourtOrderDocPath((String) protestList.get(2));
								protestdto.setCourtOrderDoc((String) protestList.get(3));
								protestdto.setProtestStatus((String) protestList.get(4));
								protestDetails.add(protestdto);
								protestdto = new DocumentSearchDTO();
								System.out.println(protestDetails.size());
								String docPath = (String) protestList.get(2);
								String docName = (String) protestList.get(3);
							}
						}
						// end
					}

				}

				ArrayList caveatlist = (ArrayList) resultList.get(4);
				ArrayList caveatslist = new ArrayList();
				CaveatsDTO caveatsDTO = new CaveatsDTO();
				if (caveatlist != null && caveatlist.size() > 0) {
					for (int k = 0; k < caveatlist.size(); k++) {
						caveatsDTO = new CaveatsDTO();
						logger.debug(caveatlist.get(k));
						ArrayList tmpll = (ArrayList) caveatlist.get(k);
						// caveatsDTO.setCaveatType((String) tmpll.get(0));
						// caveatsDTO.setCaveatDetails((String) tmpll.get(1));
						// caveatsDTO.setCaveatSorderStatus((String)
						// tmpll.get(2));
						caveatsDTO.setBankChargeId((String) tmpll.get(0));
						caveatsDTO.setCaveatStatus((String) tmpll.get(1));
						if (caveatsDTO.getCaveatStatus().equals("LOGGED")) {
							caveatsDTO.setCaveatUploadedDoc((String) tmpll.get(2));
						}
						if (caveatsDTO.getCaveatStatus().equals("RELEASED")) {
							caveatsDTO.setCaveatUploadedDoc((String) tmpll.get(3));
						}
						if ("hi".equalsIgnoreCase(language)) {
							if ("LOGGED".equalsIgnoreCase(caveatsDTO.getCaveatStatus())) {
								caveatsDTO.setCaveatSorderStatus("लॉगड");
							} else if ("RELEASED".equalsIgnoreCase(caveatsDTO.getCaveatStatus())) {
								caveatsDTO.setCaveatSorderStatus("रिलीजड");
							}
						}
						caveatslist.add(caveatsDTO);
					}
				}

				ArrayList complianceList = (ArrayList) resultList.get(5);
				ArrayList tmpcomplList = new ArrayList();
				DocumentSearchDTO subclausedto = new DocumentSearchDTO();
				DocumentSearchDTO compliancedto = new DocumentSearchDTO();
				if (complianceList != null && complianceList.size() > 0) {
					for (int k = 0; k < complianceList.size(); k++) {
						subclausedto = new DocumentSearchDTO();
						logger.debug(complianceList.get(k));
						ArrayList tmpcompl = (ArrayList) complianceList.get(k);
						compliancedto = new DocumentSearchDTO();
						compliancedto.setComplianceName((String) tmpcompl.get(0));
						tmpcomplList.add(compliancedto);
					}
				}
				dto.setComplianceList(tmpcomplList);
				dto.setCaveatslist(caveatslist);
				logger.debug(" in bd party list? " + paryList);
				dto.setPropertyList(propertyList);
				dto.setKhasraList(khasraDetails);
				dto.setProtestList(protestDetails);
				logger.debug(" in bd khasra list? " + khasraDetails);
				logger.info("in bd bd propertyList size-->" + propertyList.size());
				logger.info("in bd bd party size-->" + paryList.size());
				tmppdto.setPartyList(paryList);
				dto.setPartyDTO(tmppdto);
				typeaList.add(dto);
				dto.setTypeBresult(typeaList);
			} else {
				dto = null;
			}
			logger.info("in bd checkRegistrationId-->" + resultList);
		} catch (Exception e) {
			logger.error(e);
		}
		return dto;
	}
	public boolean getValidregNo(String regNumber) throws Exception {
		boolean response = false;
		try {
			response = bo.getValidregNo(regNumber);

		} catch (Exception e) {
			logger.error("valid document number check error---->" + e);
		}
		return response;
	}

	public DocumentSearchDTO checkOldDocData(String _refRegId, String language) throws Exception {
		DocumentSearchDTO dto = new DocumentSearchDTO();
		try {
			ArrayList list = new ArrayList<String>();
			list = bo.checkOldDocPropData(_refRegId, language);
			ArrayList list1 = new ArrayList<String>();
			list1 = bo.checkOldDocPartyData(_refRegId, language);
			ArrayList propData = new ArrayList();
			list = (ArrayList) list.get(0);
			for (int i = 0; i < list.size(); i++) {
				ArrayList temp = (ArrayList) list.get(i);
				DocumentSearchDTO propDto = new DocumentSearchDTO();
				propDto.setOldDocDistrict((String) temp.get(0));
				propDto.setOldDocTehsil((String) temp.get(1));
				propDto.setOldDocArea((String) temp.get(2));
				propDto.setOldDocWardName((String) temp.get(3));
				propDto.setOldDocColonyName((String) temp.get(4));
				propDto.setOldPropType((String) temp.get(5));
				propDto.setOldDocKhasraNum((String) temp.get(6));
				propDto.setOldDocPlotNum((String) temp.get(7));
				propDto.setOldDocPropAddress((String) temp.get(8));
				propDto.setOldDocMarketValue((String) temp.get(9));
				propDto.setOldDocConsiVal((String) temp.get(10));
				propDto.setOldDocSellerName((String) temp.get(11));
				propDto.setOldDocSellerAddress((String) temp.get(12));
				propDto.setOldDocBuyerName((String) temp.get(13));
				propDto.setOldDocBuyerAddress((String) temp.get(14));
				propDto.setOldDocType((String) temp.get(15));
				propData.add(propDto);
			}
			dto.setOldPropList(propData);
			list1 = (ArrayList) list1.get(0);
			dto.setOldDocFY((String) list1.get(0));
			dto.setOldDocRegDate((String) list1.get(1));
		} catch (Exception e) {
			logger.error("checkOldDocData error ---->" + e);
		}
		return dto;
	}

	public String checkSearchType(String refId) {
		String searchType = "";
		searchType = bo.checkSearchType(refId);
		return searchType;
	}

	/*
	 * public Map getPartyAllData(String regNumber, String partyType) throws Exception{ return bo.getPartyAllData(regNumber); }
	 */
	public ArrayList getTehisilGDL(String _distId, String language, String fisYear) throws Exception {
		ArrayList tehsilList = new ArrayList();
		//IGRSCommon common = new IGRSCommon();
		try {
			//ArrayList tmpList = common.getThesil(_distId, language);
			ArrayList tmpList = bo.getTehsilGDL(_distId, language,fisYear);
			for (int i = 0; i < tmpList.size(); i++) {
				ArrayList tmpsubList = (ArrayList) tmpList.get(i);
				if (tmpsubList != null) {
					DocumentSearchDTO dto = new DocumentSearchDTO();
					dto.setTehisilId((String) tmpsubList.get(0));
					if ("en".equalsIgnoreCase(language)) {
						dto.setTehisilName((String) tmpsubList.get(1));
					} else {

						dto.setTehisilName((String) tmpsubList.get(2));
					}
					tehsilList.add(dto);
				}

			}
			logger.info("getTehisil-->" + tehsilList);
		} catch (Exception e) {
			logger.error(e);

		}
		return tehsilList;
	}
	
	public String getFiscalYearName(String fiscalYearId) throws Exception {
		String retVal = "";
		retVal=bo.getFiscalYearName(fiscalYearId);
		return retVal;
	}
	
	@SuppressWarnings("unchecked")
	public DocumentSearchDTO checkRegistrationIdAll(String _refRegId, String language) throws Exception {
		ArrayList resultList = new ArrayList();
		ArrayList typeaList = new ArrayList();
		DocumentSearchDTO dto = new DocumentSearchDTO();
		DocumentSearchDTO resultdto = new DocumentSearchDTO();
		try {
			//resultList = bo.checkRegistrationId(_refRegId, language);
			logger.debug("Using new method for searching...");
			resultList = bo.checkRegistrationIdOffice(_refRegId, language);
			logger.debug("in bd resultList size -->" + resultList.size());
			if (resultList != null && resultList.size() > 1) {
				dto.setRegistNumber(_refRegId);
				dto.setApplicationId((String) resultList.get(0));
				dto.setEstampCode((String) resultList.get(1));
				ArrayList tmp = (ArrayList) resultList.get(2);
				ArrayList paryList = new ArrayList();
				ArrayList tmpList = (ArrayList) resultList.get(2);
				PartyDetailsDTO tmppdto = new PartyDetailsDTO();
				if (tmpList != null && tmpList.size() > 0) {
					logger.debug("--size--<>" + tmpList.size());
					for (int j = 0; j < tmpList.size(); j++) {
						logger.debug("tmp list  for--<>" + j);
						ArrayList arl = (ArrayList) tmpList.get(j);
						if (arl != null && arl.size() > 0) {
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
							pdto.setPropShare((String) arl.get(20));
							pdto.setProofNumber((String) arl.get(21));
							// pdto.setOcupation((String) arl.get(22));
							pdto.setGovtOffName((String) arl.get(22));
							pdto.setGovtOffDesg((String) arl.get(23));
							pdto.setGovtOffAddress((String) arl.get(24));
							paryList.add(pdto);
						}
					}
				}
				ArrayList tmppropertyList = new ArrayList();
				tmppropertyList = (ArrayList) resultList.get(3);
				ArrayList propertyList = new ArrayList();
				ArrayList khasraDetails = new ArrayList();
				ArrayList protestDetails = new ArrayList();
				DocumentSearchDTO propertydto = new DocumentSearchDTO();
				DocumentSearchDTO khasradto = new DocumentSearchDTO();
				DocumentSearchDTO protestdto = new DocumentSearchDTO();
				System.out.println("size of tmppropertyList>>>>>>>>>>>>" + tmppropertyList.size());
				if (tmppropertyList != null && tmppropertyList.size() > 0) { // changes
					// by
					// Shreeraj
					ArrayList temppropList = new ArrayList();

					for (int k = 0; k < tmppropertyList.size(); k++) {
						temppropList = (ArrayList) tmppropertyList.get(k);
						// ArrayList temppropList = new ArrayList();
						// temppropList=(ArrayList) tmppropertyList.get(k);
						logger.debug("property list size -->" + temppropList.size());
						logger.debug("property ref number-->" + (String) temppropList.get(0) + (String) temppropList.get(1));
						propertydto.setPropertyId((String) temppropList.get(1));
						propertydto.setPropType((String) temppropList.get(2));
						propertydto.setUseType((String) temppropList.get(2));
						propertydto.setDistName((String) temppropList.get(3));
						propertydto.setTehisilName((String) temppropList.get(4));
						propertydto.setWardName((String) temppropList.get(5));
						propertydto.setMohallaName((String) temppropList.get(6));
						propertydto.setAreaTypeName((String) temppropList.get(7));
						propertydto.setGorvMunicplBody((String) temppropList.get(8));
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
						propertydto.setPropMap((String) temppropList.get(19));
						propertydto.setConstuctedArea("");
						propertydto.setCeilingType("");
						propertyList.add(propertydto);
						propertydto = new DocumentSearchDTO();

						// modified by shruti---for iteration to be done for
						// each property id
						// by Shreeraj
						System.out.println("Property Id from tmppropertyList1>>>>>" + (String) temppropList.get(1));
						String propID = (String) temppropList.get(1);
						ArrayList khasraDet = new ArrayList();
						ArrayList protestDet = new ArrayList();
						khasraDet = bo.getKhasraDetail(propID);
						protestDet = bo.protestDetls(propID, language);
						logger.debug("khasra list size -->" + khasraDet.size());
						logger.debug("protestDet list size -->" + protestDet.size());
						if (khasraDet != null && khasraDet.size() > 0) {
							System.out.println(khasraDet.size());
							for (int j = 0; j < khasraDet.size(); j++) {
								ArrayList khasraList = (ArrayList) khasraDet.get(j);
								khasradto.setKhasraNumber((String) (khasraList.get(0)));
								khasradto.setKhasraName((String) khasraList.get(1));
								khasradto.setLagaan((String) khasraList.get(2));
								khasradto.setRinPushtikaNumber((String) khasraList.get(3));
								khasradto.setNorthBoundary((String) khasraList.get(4));
								khasradto.setSouthBoundary((String) khasraList.get(5));
								khasradto.setEastBoundary((String) khasraList.get(6));
								khasradto.setWestBoundary((String) khasraList.get(7));
								khasraDetails.add(khasradto);
								khasradto = new DocumentSearchDTO();
								System.out.println(khasraDetails.size());
							}
						}
						if (protestDet != null && protestDet.size() > 0) {
							System.out.println(protestDet.size());
							for (int j = 0; j < protestDet.size(); j++) {
								ArrayList protestList = (ArrayList) protestDet.get(j);
								protestdto.setProtestTxnId((String) (protestList.get(1)));
								protestdto.setCourtOrderDocPath((String) protestList.get(2));
								protestdto.setCourtOrderDoc((String) protestList.get(3));
								protestdto.setProtestStatus((String) protestList.get(4));

								protestDetails.add(protestdto);
								protestdto = new DocumentSearchDTO();
								System.out.println(protestDetails.size());
							}
						}
						// end
					}

				}

				ArrayList caveatlist = (ArrayList) resultList.get(5);
				ArrayList caveatslist = new ArrayList();
				CaveatsDTO caveatsDTO = new CaveatsDTO();
				if (caveatlist != null && caveatlist.size() > 0) {
					for (int k = 0; k < caveatlist.size(); k++) {
						caveatsDTO = new CaveatsDTO();
						logger.debug(caveatlist.get(k));
						ArrayList tmpll = (ArrayList) caveatlist.get(k);
						caveatsDTO.setBankChargeId((String) tmpll.get(0));
						caveatsDTO.setCaveatStatus((String) tmpll.get(1));
						if (caveatsDTO.getCaveatStatus().equals("LOGGED")) {
							caveatsDTO.setCaveatUploadedDoc((String) tmpll.get(2));
						}
						if (caveatsDTO.getCaveatStatus().equals("RELEASED")) {
							caveatsDTO.setCaveatUploadedDoc((String) tmpll.get(3));
						}
						if ("hi".equalsIgnoreCase(language)) {
							if ("LOGGED".equalsIgnoreCase(caveatsDTO.getCaveatStatus())) {
								caveatsDTO.setCaveatSorderStatus("लॉगड");
							} else if ("RELEASED".equalsIgnoreCase(caveatsDTO.getCaveatStatus())) {
								caveatsDTO.setCaveatSorderStatus("रिलीजड");
							}
						}
						caveatslist.add(caveatsDTO);
					}
				}

				ArrayList complianceList = (ArrayList) resultList.get(5);
				ArrayList tmpcomplList = new ArrayList();
				DocumentSearchDTO subclausedto = new DocumentSearchDTO();
				DocumentSearchDTO compliancedto = new DocumentSearchDTO();
				if (complianceList != null && complianceList.size() > 0) {
					for (int k = 0; k < complianceList.size(); k++) {
						subclausedto = new DocumentSearchDTO();
						logger.debug(complianceList.get(k));
						ArrayList tmpcompl = (ArrayList) complianceList.get(k);
						compliancedto = new DocumentSearchDTO();
						compliancedto.setComplianceName((String) tmpcompl.get(0));
						tmpcomplList.add(compliancedto);
					}
				}
				dto.setComplianceList(tmpcomplList);
				dto.setCaveatslist(caveatslist);
				logger.debug(" in bd party list? " + paryList);
				dto.setPropertyList(propertyList);
				dto.setKhasraList(khasraDetails);
				dto.setProtestList(protestDetails);
				logger.debug(" in bd khasra list? " + khasraDetails);
				logger.info("in bd bd propertyList size-->" + propertyList.size());
				logger.info("in bd bd party size-->" + paryList.size());
				tmppdto.setPartyList(paryList);
				dto.setPartyDTO(tmppdto);
				typeaList.add(dto);
				dto.setTypeBresult(typeaList);
			} else {
				dto = null;
			}
			logger.info("in bd checkRegistrationId-->" + resultList);
		} catch (Exception e) {
			logger.error(e);
		}
		return dto;
	}
	
}