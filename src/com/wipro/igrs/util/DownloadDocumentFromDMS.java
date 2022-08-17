package com.wipro.igrs.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.newgen.burning.Dataclass;
import com.newgen.burning.DocumentOperations;
import com.newgen.burning.ODServerDetails;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.payments.util.PropertiesFileReader;

public class DownloadDocumentFromDMS extends Thread {
	public static ArrayList<String> docList = new ArrayList<String>();
	public static ArrayList tempdocList = new ArrayList();

	static Set<String> set = new HashSet<String>();
	public void run() {

		for (int i = 0; i < tempdocList.size(); i++) {
			docList = (ArrayList<String>) tempdocList.get(i);
			String threadName = Thread.currentThread().getName();
			if (checkPrime(i) & ("Thread 1".equalsIgnoreCase(threadName))) {
				String docNum = docList.get(0);
				try {
					downloadCopyFromDMS(docNum);
					System.out.println("Downloaded document number " + docNum + " using thread " + threadName);
					docList.remove(docNum);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (i % 2 == 0 & ("Thread 2".equalsIgnoreCase(threadName))) {
				String docNum = docList.get(0);
				try {
					downloadCopyFromDMS(docNum);
					System.out.println("Downloaded document number " + docNum + " using thread " + threadName);
					docList.remove(docNum);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				if (("Thread 3".equalsIgnoreCase(threadName)) & ((i % 2 == 1)) & (!checkPrime(i))) {
					String docNum = docList.get(0);
					try {
						downloadCopyFromDMS(docNum);
						System.out.println("Downloaded document number " + docNum + " using thread " + threadName);
						docList.remove(docNum);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

		}
	}
	public static boolean checkPrime(int num) {

		boolean flag = true;
		if (num == 0 || num == 1 || num == 3 || num == 2) {
			flag = false;
		} else {
			for (int i = 2; i <= num / 2; i++) {
				if (num % i == 0) {
					flag = false;
					break;
				}
			}
		}
		return flag;
	}
	public static void main(String args[]) throws Exception {
		long startTime = System.currentTimeMillis();
		String sql = "SELECT Reg_Txn_Id FROM Igrs_Reg_Txn_Detls Where Update_Date Between To_Date('03-05-19 00:00:00','dd-mm-yy hh24:mi:ss') And To_Date('03-05-22 23:59:59','dd-mm-yy hh24:mi:ss') and Registration_Txn_Status=17";
		DBUtility db = null;
		try {
			db = new DBUtility();
			db.createStatement();
			tempdocList = db.executeQuery(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.closeConnection();
		}


		DownloadDocumentFromDMS d1 = new DownloadDocumentFromDMS();
		d1.setName("Thread 1");
		d1.start();

		Thread th1 = new Thread();

		DownloadDocumentFromDMS d2 = new DownloadDocumentFromDMS();
		d2.setName("Thread 2");
		d2.start();

		DownloadDocumentFromDMS d3 = new DownloadDocumentFromDMS();
		d3.setName("Thread 3");
		d3.start();
	}

	public static void downloadCopyFromDMS(String docNumber) throws Exception {
		PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");

		String[] docList = new String[1];
		docList[0] = docNumber;
		long startTime = System.currentTimeMillis();
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
		metaDataInfo.setDataclassName(pr.getValue("IGRSDataclass"));
		metaDataInfo.setType("F");
		metaDataInfo.setLatestFlag("L");
		String tempPath = "";
		String downloadPath = pr.getValue("igrs_upload_path");

		metaDataInfo.setUniqueNo(docNumber);
		String guid = UUID.randomUUID().toString();
		String EstampPath = downloadPath + File.separator + guid + File.separator + docNumber;
		File folder = new File(EstampPath.toString());
		if (!folder.exists()) {
			System.out.println(folder.toString());

			folder.mkdirs();

		}

		int result = docOperations.downloadDocument(connDetails, metaDataInfo, EstampPath.toString(), "Estamp");
		File[] listOfFiles = folder.listFiles();
		for (int z = 0; z < listOfFiles.length; z++) {
			tempPath = listOfFiles[z].getPath();
		}
		System.out.println("GET DOCUMENT BYTES");

		long endTime = System.currentTimeMillis();
		System.out.println("Total Time taken --" + (endTime - startTime));
		// filePath = burnRequestIdForPDF("", tempPath, EstampPath);
	}
}
