package com.wipro.igrs.ACL.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import com.tcs.sgv.encdec.MPCTPAESEncDec;
import com.wipro.igrs.categorymaster.util.PropertiesFileReader;
import com.wipro.igrs.db.DBUtility;

public class CopyFile {
	static String algo = "DES/ECB/PKCS5Padding";

	static PropertiesFileReader read = null;
	public static void main(String args[]) throws Exception {
		read = PropertiesFileReader.getInstance("resources.igrs");
		String AESKeyFilePath = read.getValue("ENCRYPTION_KEY_FILE_PATH").trim();
		ArrayList<String> list = new ArrayList<String>();

		list.add("MPTIGR20220402.txt");
		list.add("MPTIGR20220403.txt");
		list.add("MPTIGR20220404.txt");
		list.add("MPTIGR20220405.txt");
		list.add("MPTIGR20220406.txt");
		list.add("MPTIGR20220407.txt");
		list.add("MPTIGR20220407.txt");
		list.add("MPTIGR20220409.txt");
		list.add("MPTIGR20220410.txt");
		list.add("MPTIGR20220411.txt");
		list.add("MPTIGR20220412.txt");
		list.add("MPTIGR20220413.txt");
		
		String encPath = "C://Users//SA334510//Documents//Application//IFMIS//enc//treasuryifmis//";
		String decPath = "C://Users//SA334510//Documents//Application//IFMIS//enc//decrypted//";

		int lineCount = 0;
		
		for (String fileName : list) {
			
			String filePath=encPath+fileName;
			File file = new File(encPath);
			System.out.println("Encrypted Path -- "+filePath);
			String s1 = "";
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			try {
				StringBuilder sb = new StringBuilder();
				String line = br.readLine();
				while (line != null) {

					sb.append(line);
					line = br.readLine();
					lineCount++;
				}
				s1 = sb.toString();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				br.close();
			}
			//System.out.println("encrpted string is ----> ");
			//System.out.println(s1);
			String decryptValue = MPCTPAESEncDec.DecryptData(s1, AESKeyFilePath);
			System.out.println("Decrpted string is ----> ");
			//System.out.println(decryptValue);
			String filePathDecrypt=decPath+fileName;
			System.out.println("Decrypted file Path -- "+filePathDecrypt);
			FileWriter fw = new FileWriter(filePathDecrypt);
			fw.write(decryptValue);
			fw.close();

		//	FTPLogin.insertDateIntoTMP(filePathDecrypt);

			/*String sql = "SELECT OUTPUT_TO_TRS_URL, crn_number FROM IGRS_OFFLINE_CYBER WHERE CREATED_DATE between to_date('02-12-21 00:00:00','dd-mm-yy hh24:mi:ss') and to_date('04-12-21 23:59:59','dd-mm-yy hh24:mi:ss')";
			DBUtility dbUtility = new DBUtility();
			dbUtility.createStatement();
			ArrayList urlData = dbUtility.executeQuery(sql);
			System.out.println("Length of url sent is "+urlData.size());
			for(int i = 0; i<urlData.size(); i++){
				ArrayList lst = (ArrayList) urlData.get(i);
				String str = (String) lst.get(0);
				String[] strarr = str.split("\\|");
				System.out.println("Size is "+strarr.length+" ------------!!!!!!!"+(String) lst.get(1)+"!!!!!!---------------- "+str);
			}*/
		}
		
		System.out.println("Total Line processed are --> "+lineCount);
	}
}
