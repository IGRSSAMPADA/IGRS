package com.wipro.igrs.clr.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;

import com.wipro.igrs.DeliveryOfDocuments.action.DeliveryOfDocumentsAction;
import com.wipro.igrs.common.PropertiesFileReader;
import com.wipro.igrs.rcms.bd.RevenueCaseBD;
import com.wipro.igrs.rcms.constant.RevenueCaseConstant;
import com.wipro.igrs.rcms.entity.BuyerDetails;
import com.wipro.igrs.rcms.entity.BuyerDetailsSampadaList;
import com.wipro.igrs.rcms.entity.KhasraRecord;
import com.wipro.igrs.rcms.entity.KhasraRecordList;
import com.wipro.igrs.rcms.entity.PropertyDetails;
import com.wipro.igrs.rcms.entity.RCMSDetailsSampada;
import com.wipro.igrs.rcms.entity.RCMSResponse;
import com.wipro.igrs.rcms.entity.SellerDetails;
import com.wipro.igrs.rcms.entity.SellerDetailsSampadaList;

public class TestRCMSNew {
	private static Logger logger = (Logger) Logger.getLogger(DeliveryOfDocumentsAction.class);
	public static void main(String args[]){

		String regTxnID="140222000002";
		ArrayList propIdList = new ArrayList();
		RevenueCaseBD revenueBD = new RevenueCaseBD();
		boolean propFlag = false;
		try {

			// Check for property with beresia tehsil level in SQL query ****Removed

			propIdList = new ArrayList();
			ArrayList propIDs = revenueBD.getPropIdsForRCMS(regTxnID);

			if (propIDs != null && !propIDs.isEmpty()) {

				// Property Null check to be added
				for (int j = 0; j < propIDs.size(); j++) {
					ArrayList list2 = new ArrayList();
					list2 = (ArrayList) propIDs.get(j);
					propIdList.add(list2.get(0));
				}

				// Tehsil Check for Pilot

				// Update reg txn table with New Request status

				boolean transFlag = revenueBD.updateRegTrnStatus(regTxnID, "1");

				logger.debug("Property ID List :- " + propIdList);

				logger.debug("Get XML data to be sent to RCMS");

				int counter = 1;
				RCMSDetailsSampada rcmsds;
				ArrayList<PropertyDetails> propds;
				PropertyDetails prds;
				KhasraRecordList krList;
				ArrayList<KhasraRecord> kr;
				BuyerDetailsSampadaList bdsl;
				SellerDetailsSampadaList sdsl;
				ArrayList<SellerDetails> sdList;
				ArrayList<BuyerDetails> bdList;
				KhasraRecord krr;

				// Get common reg details
				rcmsds = new RCMSDetailsSampada();
				propds = new ArrayList<PropertyDetails>();

				ArrayList commonfields = revenueBD.getRegistrationDetails(regTxnID);
				logger.info("commonfields: " + commonfields);
				ArrayList commonfieldsNew = new ArrayList();
				for (int j = 0; j < commonfields.size(); j++) {
					ArrayList list1 = new ArrayList();
					list1 = (ArrayList) commonfields.get(j);
					commonfieldsNew.add(list1.get(0));
					commonfieldsNew.add(list1.get(1));
					commonfieldsNew.add(list1.get(2));
					commonfieldsNew.add(list1.get(3));
					commonfieldsNew.add(list1.get(4));
					commonfieldsNew.add(list1.get(5));
					commonfieldsNew.add(list1.get(6));
				}

				// Fetch RegNo,doctypeID
				String registrationNo = (String) commonfieldsNew.get(0);
				String MutationTypeId = (String) commonfieldsNew.get(2);
				String docTypeId = (String) commonfieldsNew.get(2);

				String officeId = (String) commonfieldsNew.get(3);
				logger.info("Registration No:" + commonfieldsNew.get(0));

				// Set RCMS password
				PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
				String password = pr.getValue("RCMS_PASSWORD");
				rcmsds.setRcmsPassword(password);

				rcmsds.setDocumentNo((registrationNo != null && !registrationNo.isEmpty()) ? registrationNo : "");
				rcmsds.setMutationTypeId((MutationTypeId != null && !MutationTypeId.isEmpty()) ? MutationTypeId : "");
				rcmsds.setDocTypeId((docTypeId != null && !docTypeId.isEmpty()) ? docTypeId : "");
				rcmsds.setOfficeId((officeId != null && !officeId.isEmpty()) ? officeId : "");
				String url1=(String) ((commonfieldsNew.get(5)==null)?"NA":commonfieldsNew.get(5));
				System.out.println(url1);
				String tempUrl="";
				if(!("NA".equalsIgnoreCase(url1))){
					int i=url1.lastIndexOf(".");
					tempUrl = url1.substring(0, i);
				}
				//url1=DownloadService.encryptFilePath(tempUrl);
				System.out.println("Encrypted file path is --- "+url1);
				String finalurl1="http://localhost:8080/IGRS/login.do?downloadFilePath="+tempUrl;
				System.out.println("Final URL --> "+finalurl1);
				rcmsds.setURL_Form_6(finalurl1);
				rcmsds.setURL_Form_6A(finalurl1);
				rcmsds.setURL_Form_6B(finalurl1);
				
				// Fetch Document Date
				String docDate = revenueBD.getDocDate(registrationNo);
				if (docDate != null && !docDate.isEmpty()) {
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					Date parsedDate = dateFormat.parse(docDate);
					String newDate = dateFormat.format(parsedDate);
					rcmsds.setDocumentDate(newDate);
				} else {
					rcmsds.setDocumentDate("");
				}
				logger.info("Document Date: " + docDate);

				for (int i = 0; i < propIdList.size(); i++) {

					prds = new PropertyDetails();
					krList = new KhasraRecordList();

					String propId = (String) propIdList.get(i);
					prds.setPropertyId(propId);

					// Get total sellable area
					String totalArea = revenueBD.getTotalSellableArea(propId);
					prds.setTotalSellableArea(totalArea);

					kr = new ArrayList<KhasraRecord>();

					// Get khasra IDs wrt Property ID
					ArrayList khasraIdLists = revenueBD.getAllKhasraIds(propId,"");
					ArrayList khasraIdList = new ArrayList();

					// Fetch Buyer and Seller Details
					// Party Details will be same for all khasras

					ArrayList buyerSellerPartyList = revenueBD.getBuyerSellerPartyList(regTxnID, propId,"'");
					ArrayList sellerPartyList = (ArrayList) buyerSellerPartyList.get(1);
					logger.info("sellerPartyList:" + sellerPartyList);

					ArrayList buyerPartyList = (ArrayList) buyerSellerPartyList.get(0);
					logger.info("buyerList:" + buyerPartyList);

					// Get district, tehsil and other area related details

					ArrayList areaList = revenueBD.getAllAreaDetails(propId);
					logger.info("areaDetails: " + areaList);

					String landArea = revenueBD.getLandAreaForProp(propId);

					for (int m = 0; m < khasraIdLists.size(); m++) {
						khasraIdList = (ArrayList) khasraIdLists.get(m);

						krr = new KhasraRecord();
						bdsl = new BuyerDetailsSampadaList();
						sdsl = new SellerDetailsSampadaList();
						sdList = new ArrayList<SellerDetails>();
						bdList = new ArrayList<BuyerDetails>();

						String khasraNo = (String) khasraIdList.get(1);
						String khasraArea = (String) khasraIdList.get(2);

						// Store area details to xml object
						for (int j = 0; j < areaList.size(); j++) {
							ArrayList list1 = new ArrayList();
							list1 = (ArrayList) areaList.get(j);
							if (((String) list1.get(1)) != null && !((String) list1.get(1)).isEmpty()) {
								krr.setDistrict((String) list1.get(1));
							} else {
								krr.setDistrict("");
							}

							if (((String) list1.get(2)) != null && !((String) list1.get(2)).isEmpty()) {
								krr.setTehsil((String) list1.get(2));
							} else {
								krr.setTehsil("");
							}

							if (((String) list1.get(3)) != null && !((String) list1.get(3)).isEmpty()) {
								krr.setRICircle((String) list1.get(3));
							} else {
								krr.setRICircle("");
							}

							if (((String) list1.get(4)) != null && !((String) list1.get(4)).isEmpty()) {
								krr.setPatwariHalka((String) list1.get(4));
							} else {
								krr.setPatwariHalka("");
							}

							if (((String) list1.get(5)) != null && !((String) list1.get(5)).isEmpty()) {
								krr.setVillage((String) list1.get(5));
							} else {
								krr.setVillage("");
							}

							if (((String) list1.get(6)) != null && !((String) list1.get(6)).isEmpty()) {
								krr.setAreaType((String) list1.get(6));
							} else {
								krr.setAreaType("");
							}

							// Khasra Sellable Area
							if (khasraArea != null && !khasraArea.isEmpty()) {
								krr.setKhasraSellableArea(khasraArea);
							} else {
								krr.setKhasraSellableArea("");
							}

							krr.setKhasraNumber(khasraNo);

						}

						// Seller Details iterating loop
						if (sellerPartyList.size() != 0) {
							for (int j = 0; j < sellerPartyList.size(); j++) {
								ArrayList sellerList = revenueBD.getSellerPartyList((String) sellerPartyList.get(j));
								for (int k = 0; k < sellerList.size(); k++) {
									SellerDetails sd = new SellerDetails();
									ArrayList list1 = new ArrayList();
									list1 = (ArrayList) sellerList.get(k);

									logger.info("***************Seller Details************************");
									if (list1.get(0).equals("1")) {
										if (((String) list1.get(5)) != null && !((String) list1.get(5)).isEmpty()) {
											sd.setFirstName((String) list1.get(5));
										} else {
											sd.setFirstName("");
										}

										sd.setMiddleName("");
										sd.setLastName("");
										if (((String) list1.get(0)) != null && !((String) list1.get(0)).isEmpty()) {
											sd.setOwnershipTypeIdSeller((String) list1.get(0));
										} else {
											sd.setOwnershipTypeIdSeller("");
										}

										if (((String) list1.get(4)) != null && !((String) list1.get(4)).isEmpty()) {
											sd.setDeptCode((String) list1.get(4));
										} else {
											sd.setDeptCode("");
										}
										sdList.add(sd);

									}
									if (list1.get(0).equals("2")) {
										if (((String) list1.get(1)) != null && !((String) list1.get(1)).isEmpty()) {
											sd.setFirstName((String) list1.get(1));
										} else {
											sd.setFirstName("");
										}

										if (((String) list1.get(2)) != null && !((String) list1.get(2)).isEmpty()) {
											sd.setMiddleName((String) list1.get(2));
										} else {
											sd.setMiddleName("");
										}
										if (((String) list1.get(3)) != null && !((String) list1.get(3)).isEmpty()) {
											sd.setLastName((String) list1.get(3));
										} else {
											sd.setLastName("");
										}

										if (((String) list1.get(0)) != null && !((String) list1.get(0)).isEmpty()) {
											sd.setOwnershipTypeIdSeller((String) list1.get(0));
										} else {
											sd.setOwnershipTypeIdSeller("");
										}

										if (((String) list1.get(4)) != null && !((String) list1.get(4)).isEmpty()) {
											sd.setDeptCode((String) list1.get(4));
										} else {
											sd.setDeptCode("");
										}
										sdList.add(sd);
									}
									if (list1.get(0).equals("3")) {

										if (((String) list1.get(6)) != null && !((String) list1.get(6)).isEmpty()) {
											sd.setFirstName((String) list1.get(6));
										} else {
											sd.setFirstName("");
										}
										sd.setMiddleName("");
										sd.setLastName("");

										if (((String) list1.get(0)) != null && !((String) list1.get(0)).isEmpty()) {
											sd.setOwnershipTypeIdSeller((String) list1.get(0));
										} else {
											sd.setOwnershipTypeIdSeller("");
										}

										if (((String) list1.get(4)) != null && !((String) list1.get(4)).isEmpty()) {
											sd.setDeptCode((String) list1.get(4));
										} else {
											sd.setDeptCode("");
										}

										sdList.add(sd);
									}

								}

							}
						} else {
							SellerDetails sd = new SellerDetails();
							sd.setFirstName("");
							sd.setMiddleName("");
							sd.setLastName("");
							sd.setOwnershipTypeIdSeller("");
							sd.setDeptCode("");

							sdList.add(sd);
						}

						// Buyer Details iterating loop
						if (buyerPartyList.size() != 0) {
							for (int j = 0; j < buyerPartyList.size(); j++) {
								ArrayList buyerList = revenueBD.getBuyerPartyList((String) buyerPartyList.get(j));
								logger.info("***************Buyer Details************************");
								for (int k = 0; k < buyerList.size(); k++) {

									String partyTxnId = (String) buyerPartyList.get(j);
									logger.info("partyTxnId" + partyTxnId);
									String share = revenueBD.getPartyShare(partyTxnId, propId,"","");

									ArrayList list1 = new ArrayList();
									list1 = (ArrayList) buyerList.get(k);

									// Buyer Details
									BuyerDetails bd = new BuyerDetails();
									if (share != null && !share.isEmpty()) {
										bd.setOwnerShare(share.trim());
									} else {
										bd.setOwnerShare("");
									}
									bd.setOwnerId("");
									bd.setCity("");
									if (((String) list1.get(7)) != null && !((String) list1.get(7)).isEmpty()) {
										bd.setCountry((String) list1.get(7));
									} else {
										bd.setCountry("");
									}
									if (((String) list1.get(8)) != null && !((String) list1.get(8)).isEmpty()) {
										bd.setStateId((String) list1.get(8));
									} else {
										bd.setStateId("");
									}
									if (((String) list1.get(9)) != null && !((String) list1.get(9)).isEmpty()) {
										bd.setDistrictId((String) list1.get(9));
									} else {
										bd.setDistrictId("");
									}
									if (((String) list1.get(11)) != null && !((String) list1.get(11)).isEmpty()) {
										bd.setPhoneNumber((String) list1.get(11));
									} else {
										bd.setPhoneNumber("");
									}
									if (((String) list1.get(12)) != null && !((String) list1.get(12)).isEmpty()) {
										bd.setMobileNumber((String) list1.get(12));
									} else {
										bd.setMobileNumber("");
									}
									if (((String) list1.get(13)) != null && !((String) list1.get(13)).isEmpty()) {
										bd.setEmailId((String) list1.get(13));
									} else {
										bd.setEmailId("");
									}

									if (((String) list1.get(17)) != null && !((String) list1.get(17)).isEmpty()) {
										bd.setIdNumber((String) list1.get(17));
										bd.setAadharNumber((String) list1.get(17));
									} else {
										bd.setIdNumber("");
										bd.setAadharNumber("");
									}
									if (((String) list1.get(18)) != null && !((String) list1.get(18)).isEmpty()) {
										bd.setNameAadharCard((String) list1.get(18));
									} else {
										bd.setNameAadharCard("");
									}

									if (((String) list1.get(20)) != null && !((String) list1.get(20)).isEmpty()) {
										String CasteName = revenueBD.getBuyerCasteName((String) list1.get(20));
										bd.setCaste(CasteName);
									} else {
										bd.setCaste("");
									}

									String IdTypeName = "";
									if (((String) list1.get(22)) != null && !((String) list1.get(22)).isEmpty()) {
										IdTypeName = revenueBD.getBuyerIdTypeName((String) list1.get(22));
										bd.setIdType(IdTypeName);
									}

									else {
										bd.setIdType("");
									}

									// Buyer Details
									if (list1.get(0).equals("1")) {

										// For Organization
										if (((String) list1.get(5)) != null && !((String) list1.get(5)).isEmpty()) {
											bd.setFirstName((String) list1.get(5));
										} else {
											bd.setFirstName("");
										}

										bd.setFatherName("");
										bd.setMiddleName("");
										bd.setLastName("");
										bd.setGender("");
										bd.setRelationType("");

										if (((String) list1.get(0)) != null && !((String) list1.get(0)).isEmpty()) {
											bd.setOwnershipTypeIdBuyer((String) list1.get(0));
										} else {
											bd.setOwnershipTypeIdBuyer("");
										}

										if (((String) list1.get(4)) != null && !((String) list1.get(4)).isEmpty()) {
											bd.setDeptCode((String) list1.get(4));
										} else {
											bd.setDeptCode("");
										}

										if (((String) list1.get(10)) != null && !((String) list1.get(10)).isEmpty()) {
											bd.setAddress((String) list1.get(10));
										} else {
											bd.setAddress("");
										}

									}
									if (list1.get(0).equals("2")) {

										if (((String) list1.get(1)) != null && !((String) list1.get(1)).isEmpty()) {
											bd.setFirstName((String) list1.get(1));
										} else {
											bd.setFirstName("");
										}

										if (((String) list1.get(2)) != null && !((String) list1.get(2)).isEmpty()) {
											bd.setMiddleName((String) list1.get(2));
										} else {
											bd.setMiddleName("");
										}
										if (((String) list1.get(3)) != null && !((String) list1.get(3)).isEmpty()) {
											bd.setLastName((String) list1.get(3));
										} else {
											bd.setLastName("");
										}
										if (((String) list1.get(21)) != null && !((String) list1.get(21)).isEmpty()) {
											bd.setFatherName((String) list1.get(21));
										} else {
											bd.setFatherName("");
										}
										if (((String) list1.get(14)) != null && !((String) list1.get(14)).isEmpty()) {
											bd.setGender((String) list1.get(14));
										} else {
											bd.setGender("");
										}
										if (((String) list1.get(15)) != null && !((String) list1.get(15)).isEmpty()) {
											String reletionName = revenueBD.getRelationName((String) list1.get(15));
											bd.setRelationType(reletionName);
										} else {
											bd.setRelationType("");
										}

										if (((String) list1.get(0)) != null && !((String) list1.get(0)).isEmpty()) {
											bd.setOwnershipTypeIdBuyer((String) list1.get(0));
										} else {
											bd.setOwnershipTypeIdBuyer("");
										}

										if (((String) list1.get(4)) != null && !((String) list1.get(4)).isEmpty()) {
											bd.setDeptCode((String) list1.get(4));
										} else {
											bd.setDeptCode("");
										}

										if (((String) list1.get(10)) != null && !((String) list1.get(10)).isEmpty()) {
											bd.setAddress((String) list1.get(10));
										} else {
											bd.setAddress("");
										}

									}
									if (list1.get(0).equals("3")) {
										if (((String) list1.get(6)) != null && !((String) list1.get(6)).isEmpty()) {
											bd.setFirstName((String) list1.get(6));
										} else {
											bd.setFirstName("");
										}

										bd.setMiddleName("");
										bd.setLastName("");
										bd.setFatherName("");
										bd.setRelationType("");
										if (((String) list1.get(14)) != null && !((String) list1.get(14)).isEmpty()) {
											bd.setGender((String) list1.get(14));
										} else {
											bd.setGender("");
										}

										if (((String) list1.get(0)) != null && !((String) list1.get(0)).isEmpty()) {
											bd.setOwnershipTypeIdBuyer((String) list1.get(0));
										} else {
											bd.setOwnershipTypeIdBuyer("");
										}

										if (((String) list1.get(4)) != null && !((String) list1.get(4)).isEmpty()) {
											bd.setDeptCode((String) list1.get(4));
										} else {
											bd.setDeptCode("");
										}
										if (((String) list1.get(23)) != null && !((String) list1.get(23)).isEmpty()) {
											bd.setAddress((String) list1.get(23));
										} else {
											bd.setAddress("");
										}

									}

									bdList.add(bd);
									logger.info("bdListSize:" + bdList.size());

								}

							}
							bdsl.setBuyerDetails(bdList);
							sdsl.setSellerDetails(sdList);
							krr.setBuyerDetailsSampadaList(bdsl);
							krr.setSellerDetailsSampadaList(sdsl);
							kr.add(krr);
						} else {
							// Empty buyer details

							BuyerDetails buyerDetails = new BuyerDetails();
							buyerDetails.setOwnerId("");
							buyerDetails.setOwnershipTypeIdBuyer("");
							buyerDetails.setFirstName("");
							buyerDetails.setMiddleName("");
							buyerDetails.setLastName("");
							buyerDetails.setGender("");
							buyerDetails.setFatherName("");
							buyerDetails.setRelationType("");
							buyerDetails.setCaste("");
							buyerDetails.setDistrictId("");
							buyerDetails.setOwnerShare("");
							buyerDetails.setAadharNumber("");
							buyerDetails.setNameAadharCard("");
							buyerDetails.setIdType("");
							buyerDetails.setIdNumber("");
							buyerDetails.setAddress("");
							buyerDetails.setCity("");
							buyerDetails.setStateId("");
							buyerDetails.setCountry("");
							buyerDetails.setPhoneNumber("");
							buyerDetails.setEmailId("");
							buyerDetails.setMobileNumber("");
							buyerDetails.setDeptCode("");

							bdList.add(buyerDetails);

							bdsl.setBuyerDetails(bdList);
							sdsl.setSellerDetails(sdList);
							krr.setBuyerDetailsSampadaList(bdsl);
							krr.setSellerDetailsSampadaList(sdsl);
							kr.add(krr);
						}

					}

					krList.setKhasraRecord(kr);
					prds.setKhasraRecordList(krList);
					propds.add(prds);

				}

				rcmsds.setProperty(propds);

				// Marshalling of object to XML
				JAXBContext jc = JAXBContext.newInstance(RCMSDetailsSampada.class);
				Marshaller ms = jc.createMarshaller();

				ms.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

				String filePath = pr.getValue("rcms_upload_path") + RevenueCaseConstant.IGRS_RCMS_XML + "/";;
				logger.debug("Path :- " + filePath);

				File folder = new File(filePath);
				if (!folder.exists()) {
					folder.mkdirs();
				}

				filePath = filePath + RevenueCaseConstant.XML_REG_NO + registrationNo + "_" + counter + ".xml";
				// String s = "D:/RCMSRequest/SampleReQ_" + regTxnID + "_" + counter + ".xml";
				filePath="C:/Users/SA334510/Documents/SampleReQ_" + regTxnID + "_" + counter + ".xml";
				ms.marshal(rcmsds, new FileOutputStream(filePath));

				StringWriter xmlStringWriter = new StringWriter();
				ms.marshal(rcmsds, xmlStringWriter);

				String xmlString = xmlStringWriter.toString().trim();
				logger.info("xmlString:" + xmlString);
				logger.debug("Before WebService Call");

				RCMSResponse rcmsResponse = null;

				try {
					// Send request to RCMS
					//http://sarabhai-coe.mapit.gov.in/WCRCMS/Service1.svc/PUSH
			//	rcmsResponse = sendRCMSRequest(xmlString, registrationNo);
				} catch (Exception e) {
					logger.debug("Exception in sendRCMSRequest Method Call" + e);
				}

				if (rcmsResponse != null) {
					logger.debug("Webservice response from RCMS...");
					if (rcmsResponse.getStatus() != null && !(rcmsResponse.getStatus().isEmpty())) {
						logger.debug("Response Status : " + rcmsResponse.getStatus());
						String status = rcmsResponse.getStatus();
						String errorDesc = rcmsResponse.getErrorType();

						// Update reg txn table with status 2 for successful completion
						if (rcmsResponse.getStatus().equalsIgnoreCase("Success")) {
							boolean transFlag1 = revenueBD.updateRegTrnStatus(regTxnID, "2");
						}

						// Update reg txn table with status 2 for failed transaction
						if (rcmsResponse.getStatus().equalsIgnoreCase("Failed")) {
							boolean transFlag1 = revenueBD.updateRegTrnStatus(regTxnID, "3");
						}

						boolean flag = revenueBD.updateRCMSStatus(regTxnID, propIdList, rcmsResponse, errorDesc);
					}

				}
				//return true;
			} else {
				//return false;
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		//return propFlag;
	
	}
	private static RCMSResponse sendRCMSRequest(String xmlString, String registrationNo) {

		RCMSResponse response = null;

		try {
			long startTime = System.currentTimeMillis();
			PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
			URL url = new URL(pr.getValue("IGRS_RCMS_URL"));
			int timeout = Integer.valueOf(pr.getValue("RCMS_REQ_TIMEOUT"));
			String proxyIp = (String) pr.getValue("IGRS_RCMS_PROXY_IP");
			String proxyPort = (String) pr.getValue("IGRS_RCMS_PROXY_PORT");
			HttpURLConnection rc;
			Proxy proxy;
			Properties systemSettings = System.getProperties();
			timeout=20;
			systemSettings.put("proxySet", "true");
			systemSettings.put("http.proxyHost", proxyIp);
			//systemSettings.put("http.proxyPort", proxyPort);
			System.out.println("Proxy IP----" + systemSettings.getProperty("http.proxyHost") + "  Proxy Port -----" + systemSettings.getProperty("http.proxyHost"));

			// System.out.println("Connection opened " + rc );
			//proxy = new Proxy(java.net.Proxy.Type.HTTP, new InetSocketAddress(proxyIp, Integer.parseInt(proxyPort)));
			rc = (HttpURLConnection) url.openConnection();
			System.out.println("If going proxy--" + rc.usingProxy());
			// if(rc.get)
		//	rc.usingProxy();
			rc.setRequestMethod("POST");
			rc.setDoOutput(true);
			rc.setDoInput(true);
			rc.setConnectTimeout(timeout * 1000);
			rc.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
			String reqStr = xmlString; // the entire payload in a single
			// String
			int len = reqStr.length();
			// rc.setRequestProperty("Content-Length", Integer.toString(len));
			rc.connect();
			OutputStreamWriter out = new OutputStreamWriter(rc.getOutputStream());
			out.write(reqStr, 0, len);
			out.flush();
			System.out.println("Request sent, reading response ");
			InputStreamReader read = new InputStreamReader(rc.getInputStream());
			StringBuilder sb = new StringBuilder();
			int ch = read.read();
			while (ch != -1) {
				sb.append((char) ch);
				ch = read.read();
			}
			String response1 = sb.toString(); // entire response ends up in String
			read.close();
			rc.disconnect();
			long stopTime = System.currentTimeMillis();
			long elapsedTime = stopTime - startTime;
			long elapsedTimeInSec = 0;
			if (elapsedTime < 1000) {
				elapsedTimeInSec = 0;
			} else {
				elapsedTimeInSec = elapsedTime / 1000;
			}
			logger.info("**********Total Time taken in process of " + registrationNo + " is " + elapsedTime + " MilliSeconds ************ " + elapsedTimeInSec + " Seconds");
			System.out.println(response1);

			JAXBContext jaxbContext = JAXBContext.newInstance(RCMSResponse.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

			StringReader reader = new StringReader(response1);
			response = (RCMSResponse) jaxbUnmarshaller.unmarshal(reader);

			logger.info(response.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
		}
		return response;

	}
}
