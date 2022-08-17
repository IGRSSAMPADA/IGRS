package com.wipro.igrs.newPropvaluation.dto;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class CheckCLRService {
	public static void main(String args[]) throws IOException, JAXBException {

		HttpURLConnection conn;
		String proxyIp = "10.125.243.67";
		String proxyPort = "7003";
		try {
			URL url = new URL("https://webgisqc.co.in/WSMAPIT/MAPIT/getKhasraDetails2?khasraId=118060300096122000104");

			// Proxy proxy = new Proxy(java.net.Proxy.Type.HTTP, new
			// InetSocketAddress(proxyIp, Integer.parseInt(proxyPort)));
			conn = (HttpURLConnection) url.openConnection();

			System.out.println("conn:-" + conn);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "text/xml");
			conn.setConnectTimeout(10 * 1000);
			System.out.println("Connection Response Code..." + conn.getResponseCode());
			JAXBContext jc = JAXBContext.newInstance(KhasraDetailService.class);
			Unmarshaller ums = jc.createUnmarshaller();
			KhasraDetailService kds = (KhasraDetailService) ums.unmarshal(url);
			conn.disconnect();
			System.out.println(kds.getSuccess());
			System.out.println("success-- " + kds.getSuccess());
			System.out.println("message -- " + kds.getMessage());
			System.out.println("khashra ID -- " + kds.getKhasraId());
			System.out.println("landPArcelID--");
			ArrayList<LandParcelDetailDTO> ldpoList = kds.getLandParcelDetails();
			for (int i = 0; i < ldpoList.size(); i++) {
				LandParcelDetailDTO ldpo = ldpoList.get(i);
				System.out.println("Land Parcel ID -- " + ldpo.getLandParcelId());
				System.out.println("Survey number -- " + ldpo.getSurveyNo());
				System.out.println("survey number area -- " + ldpo.getSurveyNoArea());
				System.out.println("Land use -- " + ldpo.getLandUseType());
				System.out.println("land revenue -- " + ldpo.getLandRevenue());
				System.out.println("Mortgage detail...");
				ArrayList<MortgageRemarkList> mgrList = ldpo.getMortgageRemarks();
				for (int k = 0; k < mgrList.size(); k++) {
					MortgageRemarkList mgrl = mgrList.get(k);
					ArrayList<MortgageRemark> mgrr = mgrl.getMortgageRemark();
					for (int k1 = 0; k1 < mgrr.size(); k1++) {
						MortgageRemark mgr = mgrr.get(k1);
						System.out.println("mrtg remark -- " + mgr.getMRemark());
						System.out.println("mrtg type -- " + (null == mgr.getMtype() || "".equals(mgr.getMtype()) ? "NA" : mgr.getMtype()));
					}
				}
				System.out.println("Crop Details....");
				ArrayList<CropDetailList> cropList = ldpo.getCropDetails();
				for (int c = 0; c < cropList.size(); c++) {
					System.out.println(c + 1 + "---");
					CropDetailList cdtList = cropList.get(c);
					ArrayList<CropDetailDTO> cdtoList = cdtList.getCropDetail();
					for (int cd = 0; cd < cdtoList.size(); cd++) {
						CropDetailDTO cdt = cdtoList.get(cd);
						System.out.println("Season Type --" + cdt.getSeasonType());
						System.out.println("Crop Type -- " + cdt.getCropType());
						System.out.println("Crop Area -- " + cdt.getCropArea());
					}
				}
				System.out.println("Khasra remark detail----");
				ArrayList<KhasraRemarkDTO> kRemarkList = ldpo.getKhasraRemarks();
				for (int kh = 0; kh < kRemarkList.size(); kh++) {
					KhasraRemarkDTO khdto = kRemarkList.get(kh);
					ArrayList<SourceOfIrrigationList> soi = khdto.getSourceofIrrigations();
					for (int khl = 0; khl < soi.size(); khl++) {
						SourceOfIrrigationList sdto = soi.get(khl);
						ArrayList<SourceOfIrrigateDTO> sdtoList = sdto.getSourceofIrrigation();
						for (int k1 = 0; k1 < sdtoList.size(); k1++) {
							SourceOfIrrigateDTO srcdto = sdtoList.get(k1);
							System.out.println("Source of irrigation" + srcdto.getSource());
						}
					}

					System.out.println("Tree Details ---");
					ArrayList<NewTreeList> tList = khdto.getTrees();
					for (int t = 0; t < tList.size(); t++) {
						NewTreeList tree = tList.get(t);
						ArrayList<TreeNewDTO> treeList = tree.getTree();
						for (int k2 = 0; k2 < treeList.size(); k2++) {
							TreeNewDTO tdto = treeList.get(k2);
							System.out.println("Tree name -- " + tdto.getTreeName());
							System.out.println("Tree Count -- " + tdto.getTreeCount());
						}
					}
					System.out.println("Remark details---");
					ArrayList<RemarkList> rmkList = khdto.getRemarks();
					for (int r = 0; r < rmkList.size(); r++) {
						RemarkList rmkDtoList = rmkList.get(r);
						ArrayList<RmkList> rmk = rmkDtoList.getRemarks();
						for (int rm = 0; rm < rmk.size(); rm++) {
							RmkList rmk1 = rmk.get(rm);
							System.out.println("Remarks -- "+rmk1.getRemark());
							/*ArrayList<Remark> rm1 = rmk1.getRemark();
							for (int h = 0; h < rm1.size(); h++) {
								Remark rmk11 = rm1.get(r);
								System.out.println("Remarks -- " + rmk11.getRemark());
							}*/
						}
					}
				}
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block

			// conn.disconnect();
			e.printStackTrace();
		}
	}
}
