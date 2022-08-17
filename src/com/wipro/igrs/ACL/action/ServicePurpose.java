package com.wipro.igrs.ACL.action;

import java.util.ArrayList;
import java.util.Collections;

import com.wipro.igrs.categorymaster.util.PropertiesFileReader;
import com.wipro.igrs.db.DBUtility;

public class ServicePurpose {

	public static void main(String[] args) throws Exception {

		String sql = "SELECT b.reg_txn_id tt, " + "  ((TO_DATE (TO_CHAR (b.UPDATE_DATE, 'dd/mm/yyyy  hh24:mi:ss'),'dd/mm/yyyy  hh24:mi:ss' )-TO_DATE (TO_CHAR (a.START_TIME, 'dd/mm/yyyy  hh24:mi:ss'), 'dd/mm/yyyy  hh24:mi:ss'))*(24*60)) AS time_taken " + "FROM IGRS_ETOKEN_MAKER_TIME a, " + "  IGRS_REG_TXN_DETLS b, " + "  igrs_del_doc_txn_detl c " + "WHERE a.REGISTRATION_TXN_ID  =b.reg_txn_id " + "AND b.DELETE_FLAG            ='A' " + "AND b.registration_number    =c.registration_number " + "AND b.REGISTRATION_TXN_STATUS=17 " + "AND a.CREATED_DATE BETWEEN to_date('20-07-18 00:00:00','dd-mm-yy hh24:mi:ss')-15 AND to_Date('20-07-18 00:00:00','dd-mm-yy hh24:mi:ss') " + "AND b.UPDATE_DATE BETWEEN to_date('20-07-18 00:00:00','dd-mm-yy hh24:mi:ss') -15 AND to_Date('20-07-18 00:00:00','dd-mm-yy hh24:mi:ss') " + "AND c.UPDATE_DATE BETWEEN to_date('20-07-18 00:00:00','dd-mm-yy hh24:mi:ss') -15 AND to_Date('20-07-18 00:00:00','dd-mm-yy hh24:mi:ss')";
		DBUtility db = null;
		ArrayList list = new ArrayList();
		try{
			db = new DBUtility();
			db.createStatement();
			long startTime = System.currentTimeMillis();
			list = db.executeQuery(sql);
			long endTime = System.currentTimeMillis();
			System.out.println("Total time taken for processing the record --> "+(endTime-startTime)/1000 );
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			db.closeConnection();
		}
		double d = 0.0;
		ArrayList<Double> sortData = new ArrayList<Double>();
		for(int i = 0; i< list.size(); i++){
			ArrayList lst = (ArrayList) list.get(i);
			double dtemp = Double.parseDouble(((String)lst.get(1)));
			d = d+dtemp;
			sortData.add(dtemp);
		}
		System.out.println("Total document completed is "+list.size());
		System.out.println("mean is "+d/list.size());
		Collections.sort(sortData);
		System.out.println("minimum time is "+sortData.get(0));
		System.out.println("maximum time is "+sortData.get(sortData.size()-1));
		double median=0.0;
		if(sortData.size()%2==0){
			int n = sortData.size()/2;
			int n1 = n+1;
			double d1 = (sortData.get(n) + sortData.get(n1))/2;
			median = d1;
		}else{
			int n = sortData.size();
			n = (n+1)/2;
			median = sortData.get(n);
		}
		System.out.println("median is "+median);
		
	}

}
