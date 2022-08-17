package com.wipro.igrs.ACL.action;



import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import com.sun.star.sdbc.SQLException;
import com.tcs.sgv.encdec.MPCTPAESEncDec;
import com.wipro.igrs.common.PropertiesFileReader;
import com.wipro.igrs.db.DBUtility;

public class FTPLogin {

	static String algo = "DES/ECB/PKCS5Padding";

	static PropertiesFileReader read = null;
	public static void UploadFile(FTPClient ftpClient, String path) {
		File firstLocalFile = new File(path);
		try {
			String firstRemoteFile = firstLocalFile.getName();
			InputStream inputStream = new FileInputStream(firstLocalFile);

			System.out.println("Start uploading first file");
			boolean done = ftpClient.storeFile(firstRemoteFile, inputStream);

			inputStream.close();
			if (done) {
				System.out.println("The first file is uploaded successfully.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void downloadDirectory(FTPClient ftpClient, String parentDir, String currentDir, String saveDir, String type, String delete) throws IOException {
		String dirToList = parentDir;
		if (!currentDir.equals("")) {
			dirToList += "/" + currentDir;
		}

		FTPFile[] subFiles = ftpClient.listFiles(dirToList);

		if (subFiles != null && subFiles.length > 0) {
			for (FTPFile aFile : subFiles) {
				String currentFileName = aFile.getName();
				if (currentFileName.equals(".") || currentFileName.equals("..")) {
					// skip parent directory and the directory itself
					continue;
				}
				String filePath = parentDir + "/" + currentDir + "/" + currentFileName;
				if (currentDir.equals("")) {
					filePath = parentDir + "/" + currentFileName;
				}

				String newDirPath = saveDir + parentDir + File.separator + currentDir + File.separator + currentFileName;
				if (currentDir.equals("")) {
					newDirPath = saveDir + parentDir + File.separator + currentFileName;
				}
				boolean ch = currentFileName.contains(type);
				if (!ch) {
					// System.out.println("Skipped File : " + filePath);
					continue;
				}
				if (!(currentFileName.toUpperCase()).startsWith("MPT")) {
					// System.out.println("Skipped file .. "+filePath);
					continue;
				}
				if (aFile.isDirectory()) {
					// create the directory in saveDir
					File newDir = new File(newDirPath);
					boolean created = newDir.mkdirs();
					if (created) {
						System.out.println("CREATED the directory: " + newDirPath);
					} else {
						System.out.println("COULD NOT create the directory: " + newDirPath);
					}

					// download the sub directory
					downloadDirectory(ftpClient, dirToList, currentFileName, saveDir, type, delete);
				} else {
					// download the file

					boolean success = downloadSingleFile(ftpClient, filePath, newDirPath);
					if (success) {
						try {
							if (delete.equalsIgnoreCase("yes")) {

								boolean us = ftpClient.deleteFile(filePath);
								System.out.println("Deleted File :: " + filePath);
								System.out.println(" Deletion status :: " + us);
								if (us) {
									System.out.println("File of path " + filePath + " has been deleted..");
								}
							}
							PropertiesFileReader read = PropertiesFileReader.getInstance("resources.igrs");
							String pa = read.getValue("DecryptPath").trim();
							String up = read.getValue("DecryptSavePath").trim();
							decrypt(pa + filePath, up);

						} catch (Exception e) {
							e.printStackTrace();
						}
						System.out.println("DOWNLOADED the file: " + filePath);
					} else {
						System.out.println("COULD NOT download the file: " + filePath);
					}
				}
			}
		}
	}

	public static boolean downloadSingleFile(FTPClient ftpClient, String remoteFilePath, String savePath) throws IOException {
		File downloadFile = new File(savePath);

		File parentDir = downloadFile.getParentFile();
		if (!parentDir.exists()) {
			boolean check = parentDir.mkdirs();
			System.out.println(check);
		}

		OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(downloadFile));
		try {

			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			return ftpClient.retrieveFile(remoteFilePath, outputStream);
		} catch (IOException ex) {
			throw ex;
		} finally {
			if (outputStream != null) {
				outputStream.close();
			}
		}
	}

	public static boolean getTime(String time) {
		boolean check = false;
		SimpleDateFormat dates = new SimpleDateFormat("yyyyMMddHHmmss");
		try {
			String timePart = time.split(" ")[1];
			Date modificationTime = dates.parse(timePart);
			Date da = new Date();
			Calendar ca1 = Calendar.getInstance();
			Calendar ca2 = Calendar.getInstance();
			ca1.setTime(da);
			ca2.setTime(modificationTime);
			check = ca1.get(Calendar.YEAR) == ca2.get(Calendar.YEAR) && ca1.get(Calendar.DAY_OF_YEAR) == ca2.get(Calendar.DAY_OF_YEAR);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return check;
	}

	public static void decrypt(String filePath, String outputPath) throws Exception {

		File file = new File(filePath);
		String s1 = "";
		BufferedReader br = new BufferedReader(new FileReader(file));
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			while (line != null) {

				sb.append(line);
				line = br.readLine();
			}
			s1 = sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			br.close();
		}
		// System.out.println("String to be decrypted.. "+s1);
		FTPLogin ftplogin = new FTPLogin();
		read = PropertiesFileReader.getInstance("resources.igrs");
		String AESKeyFilePath = read.getValue("ENCRYPTION_KEY_FILE_PATH").trim();
		//ADDED ON 01/12/21
		System.out.println(AESKeyFilePath);
		String decryptValue = MPCTPAESEncDec.DecryptData(s1, AESKeyFilePath);
		// System.out.println("decrypt value -- "+decryptValue);
		// System.out.println(String.valueOf(outputPath));
		String tempPath = read.getValue("DecryptTempPath").trim();
		ftplogin.writeFile(decryptValue, tempPath + "temp_" + file.getName());
		File ftemp = new File(tempPath + "temp_" + file.getName());
		BufferedReader sbTemp = new BufferedReader(new FileReader(ftemp));
		StringBuilder sbFinal = new StringBuilder();
		String finalDecryptVal = "";
		int lineCount = 0;
		try {
			String lineTemp = sbTemp.readLine();
			while (lineTemp != null) {
				// System.out.println(lineTemp);
				if (lineCount > 0) {
					sbFinal.append(lineTemp + "\n");
				}
				lineTemp = sbTemp.readLine();
				lineCount++;
			}
			finalDecryptVal = sbFinal.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			br.close();
		}
		ftplogin.writeFile(finalDecryptVal, String.valueOf(outputPath) + file.getName());
		insertDateIntoTMP(String.valueOf(outputPath) + file.getName());
	}
	String writeFile(String data, String fileName) throws IOException {
		// System.out.println("fileName----" + fileName);
		FileWriter fw = new FileWriter(fileName);
		fw.write(data);
		fw.close();
		return fileName;

	}
	public static void main(String[] args) {
		FTPClient ftpClient = new FTPClient();
		// connect and login...
		String fileNameType = "";
		try {
			read = PropertiesFileReader.getInstance("resources.igrs");
			String ftpServer = read.getValue("FTPClientIP").trim();
			fileNameType = read.getValue("type").trim();
			ftpClient.connect(ftpServer);
		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// pass username and password, returned true if authentication is
		// successful
		try {
			read = PropertiesFileReader.getInstance("resources.igrs");
			String username = read.getValue("FTPUserName").trim();
			String password = read.getValue("FTPPassWord").trim();
			boolean login = ftpClient.login(username, password);
			if (login) {
				System.out.println("ftp logged in...");
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// call the utility method
		try {
			// directory on the server to be downloaded
			read = PropertiesFileReader.getInstance("resources.igrs");
			String remoteDirPath = read.getValue("FTPRemoteDir").trim();
			String deleteFile = read.getValue("delete").trim();
			// deleteFile="no";
			// directory where the files will be saved
			String saveDirPath = read.getValue("FTPSaveDir").trim();
			FTPLogin.downloadDirectory(ftpClient, remoteDirPath, "", saveDirPath, fileNameType, deleteFile);
			// FTPLogin.UploadFile(ftpClient, "D:/testUpload.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void insertDateIntoTMP(String tmpFileName) {

		System.out.println("Inside insertDateIntoIGRSTMP func ");
		PropertiesFileReader read = null;
		String fileNameType = "";
		String DataBaseIp = null;
		String DatabasePort = null;
		String DatabaseSid = null;
		String USER = null;
		String PASS = null;
		Connection conn = null;
		Statement stmt = null;

		BufferedReader br = null;
		String ifscCode = null;
		String branchCode = null;
		String challanDate = null;
		String cin = null;
		String ChallanNo = null;
		String eprnNo = null;
		String namePlayer = null;
		String hoa = null;
		String challanAmount = null;
		String treasuryId = null;
		String purpose = null;
		String challanType = null;
		String totalChallan = null;
		String totalAmount = null;
		String CRN = null;
		String BRN = null;
		String txnDateTime = null;
		String tin = null;;
		Date date = null;
		// String deal
		String logDateTime = null;
		String fileType = null;
		String insertDataSize = "";
		// String decryptFilePath = null;
		DBUtility db = null;
		try {
			read = PropertiesFileReader.getInstance("resources.igrs");
			DataBaseIp = read.getValue("DatabaseIP").trim();
			DatabasePort = read.getValue("DatabasePort").trim();
			DatabaseSid = read.getValue("DatabaseSid").trim();
			USER = read.getValue("DatabseUsername").trim();
			PASS = read.getValue("DatabasePassword").trim();
			fileType = read.getValue("type").trim();
			// decryptFilePath = read.getValue("DecryptSavePath").trim();
			// decryptFilePath = read.getValue("DecryptPath").trim();
			String DB_URL = "jdbc:oracle:thin:@" + DataBaseIp + ":" + DatabasePort + ":" + DatabaseSid;
			// System.out.println("Value of DB_URL  :: "+DB_URL);
/*
			Class.forName("oracle.jdbc.driver.OracleDriver");

			conn = DriverManager.getConnection(DB_URL, USER, PASS);*/
			// conn.setAutoCommit(false);
			ArrayList lst = new ArrayList();
			System.out.println("Value of connection  :: " + conn);
			date = new Date();
			DateFormat dt = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
			System.out.println("Current date and time value " + dt.format(date));
			logDateTime = (dt.format(date)).toString();
			// File file = new File(decryptFilePath);
			// File[] listOfFiles = file.listFiles();
			String fileName1 = tmpFileName;
			// System.out.println("Value of File Size :: " + listOfFiles.length);
			// fileName1 = listOfFiles[i].getName();
			if (fileName1.contains(fileType)) {
				// fileName1 = decryptFilePath + fileName1;
				System.out.println("File is valid :: " + fileName1);
				br = new BufferedReader(new FileReader(fileName1));
				String bbb = fileName1.split("/")[fileName1.split("/").length-1];
				boolean flag = false;
				String line = null;
				// ArrayList<String> list = new ArrayList<String>();

				db = new DBUtility();
				while ((line = br.readLine()) != null) {
					flag = true;
					ArrayList tmp = new ArrayList();
					String fileData[] = line.split("#");
					ifscCode = fileData[0];
					challanDate = fileData[1];
					cin = fileData[2];
					ChallanNo = fileData[3];
					tin = fileData[4];

					

					//System.out.println("File length -- " + fileData.length);
					String crn=fileData[15];
					String brn=fileData[16];
					String urn = fileData[18];
					//System.out.println(crn);
					tmp.add(0,crn);
					tmp.add(1,brn);
					tmp.add(2,bbb);
					tmp.add(3,urn);
					//insertDataSize = stmt.executeUpdate(sql);
					//System.out.println(insertDataSize);
					/*
					 * System.out.println("bankCode " + bankCode + " branchCode " + branchCode + " challanDate " + challanDate + " cin " + cin + " ChallanNo " + ChallanNo + " eprnNo " + eprnNo + " namePlayer " + namePlayer + " hoa " + hoa + " challanAmount " + challanAmount + " treasuryId " + treasuryId + " purpose " + purpose + " challanType " + challanType + " totalChallan " + totalChallan + " totalAmount " + totalAmount + " CRN " + CRN + " BRN " + BRN + " txnDateTime " + txnDateTime);
					 * 
					 * String sql = "INSERT INTO IGRS_PAYMENT_ECHLN_CYBER_TMP (BANK_CODE,BRANCH_CODE,CHALLAN_ONLINE_DATE,CIN,CHALLAN_SERIAL_NUMBER,EPRN_NO,NAME_PAYER,HOA,CHALLAN_AMOUNT,TREASURY_ID,PURPOSE,CHALLAN_TYPE,TOTAL_NO_CHALLAN,TOTAL_CHALLAN_AMOUNT,CRN,BRN,TRANSACTION_DATE_TIME) " + "values ('" + bankCode + "','" + branchCode + "','" + challanDate + "','" + cin + "','" + ChallanNo + "','" + eprnNo + "','" + namePlayer + "','" + hoa + "','" + challanAmount + "','" + treasuryId + "','" + purpose + "','" + challanType + "','" + totalChallan + "','" + totalAmount + "','" + CRN + "','" + BRN + "','" + txnDateTime + "')"; try {
					 * 
					 * stmt = conn.createStatement();
					 * 
					 * insertDataSize = stmt.executeUpdate(sql);
					 * 
					 * } catch (Exception e) { System.out.println("Exception occurred while inserting data into TMP table :: " + e.getMessage()); String sql1 = "INSERT INTO IGRS_PAYMENT_ECHLN_CYBR_LOG (BANK_CODE,BRANCH_CODE,CHALLAN_ONLINE_DATE,CIN,CHALLAN_SERIAL_NUMBER,EPRN_NO,NAME_PAYER,HOA,CHALLAN_AMOUNT,TREASURY_ID,PURPOSE,CHALLAN_TYPE,TOTAL_NO_CHALLAN,TOTAL_CHALLAN_AMOUNT,CRN,BRN,TRANSACTION_DATE_TIME,ERROR_LOG,ERROR_LOG_TIME) " + "values ('" + bankCode + "','" + branchCode + "','" + challanDate + "','" + cin + "','" + ChallanNo + "','" + eprnNo + "','" + namePlayer + "','" + hoa + "','" + challanAmount + "','" + treasuryId + "','" + purpose + "','" + challanType + "','" + totalChallan + "','" + totalAmount + "','" + CRN + "','" + BRN + "','" + txnDateTime + "','" + e.getMessage() + "','" + logDateTime + "')"; stmt = conn.createStatement(); stmt.executeQuery(sql1); System.out.println("Treasury File data inserted into LOG Table successfully"); }
					 */
					lst.add(tmp);
				}
				try{

					String sql = "insert into IGRS_PMT_23_NOV_MANUAL (crn,brn,filename,urn_number) values (?,?,?,?)";
					db.createPreparedStatement(sql);
					//String arr[] = {crn};
					int count=0;
					PreparedStatement pst = db.returnPreparedStatement(sql);
					//pst.setMaxRows(1000);
					for(int i=0; i<lst.size(); i++){
						count++;
						ArrayList res = (ArrayList) lst.get(i);
						pst.setString(1, (String)res.get(0));
						pst.setString(2, (String)res.get(1));
						pst.setString(3, (String)res.get(2));
						pst.setString(4, (String)res.get(3));
						pst.addBatch();
						if(count>=1500){
							int[] aa = pst.executeBatch();
							pst = db.returnPreparedStatement(sql);
							System.out.println("rows completed => "+count);
							count=0;
						}
					}
					System.out.println("value add now executing batch");
					int[] aa = pst.executeBatch();
					
					}catch(SQLException e) {
						e.printStackTrace();
					}

					db.closeConnection();
				if (!("").equals(insertDataSize)) {
					System.out.println("Treasury File data inserted into TMP Table successfully and count of total row inserted is :: " + insertDataSize + "  " + CRN);
				} else {
					System.out.println("No data found in file to be inserted into TMP table :: " + insertDataSize + "  " + CRN);
				}

			} else {
				System.out.println("File is not valid " + fileName1);
			}

			try {
				if (br != null) {
					br.close();
				}
				if (conn != null) {
					conn.close();
				}
				if (stmt != null) {
					stmt.close();
				}
			} catch (Exception e) {
				System.out.println("Exception occurred while closing connection");
			}
		} catch (Exception e1) {

			e1.printStackTrace();
		}

	}

}
