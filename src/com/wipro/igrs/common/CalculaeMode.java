package com.wipro.igrs.common;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.wipro.igrs.db.DBUtility;

public class CalculaeMode {
	public static void main(String args[]) throws Exception{
		String sql1 = "SELECT office_id, " +
		"  office_name " +
		"FROM igrspilot.igrs_office_master " +
		"WHERE office_type_id=3 " ;
		DBUtility db = null;
		long starttime = System.currentTimeMillis();
		try {
			db = new DBUtility();
			db.createStatement();
			ArrayList officeIdList = db.executeQuery(sql1);
			//officeIdList = (ArrayList) officeIdList.get(0);
		//	System.out.println("Total office Count -- "+officeIdList.size());
			int i = 0;
			for (Object string : officeIdList) {
				ArrayList tmp = (ArrayList) string;
				//System.out.println(tmp.get(0)+"~~~~~"+tmp.get(1));
				getModeCount((String)tmp.get(0), (String)tmp.get(1));
				i++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			db.closeConnection();
			e.printStackTrace();
		}
		long endtime = System.currentTimeMillis();
		System.out.println((endtime-starttime)/1000);
	}
	public static void getModeCount(String officeID, String officeName) throws Exception{
		ArrayList<String> resp=new ArrayList<String>();
		String sql2 = "SELECT a.slot_Date, " +
		"  c.district_name, " +
		"  b.office_name, " +
		"  COUNT(a.reg_txn_id) " +
		"FROM igrspilot.igrs_reg_slot_book_txn_dtls a, " +
		"  igrspilot.igrs_office_master b, " +
		"  igrspilot.igrs_district_master c " +
		"WHERE a.office_id=b.office_id " +
		"AND a.district_id=c.district_id " +
		"AND a.office_id  =? " +
		"AND a.slot_Date BETWEEN to_date('01-04-20 00:00:00','dd-mm-yy hh24:mi:ss') AND to_Date('31-03-21 23:59:59','dd-mm-yy hh24:mi:ss') " +
		"GROUP BY a.slot_Date, " +
		"  c.district_name, " +
		"  b.office_name " +
		"ORDER BY a.slot_Date, " +
		"  c.district_name, " +
		"  b.office_name" ;
		DBUtility db = null;
		ArrayList<Integer> cnt  = new ArrayList<Integer>();
		HashMap<String, String> hashCount = new HashMap<String, String>();
		db = new DBUtility();
		String sql3 = "INSERT INTO IGRS_SLOT_MODE_DATA (OFFICE_ID, OFFICE_NAME,COUNT,MAXCOUNT,ISFINAL) VALUES (?,?,?,?,?)";
		String sql4 = "INSERT INTO IGRS_SLOT_MODE_DATA_FINAL (OFFICE_ID, OFFICE_NAME,COUNT,MAXCOUNT,ISFINAL) VALUES (?,?,?,?,?)";
		PreparedStatement pst = db.returnPreparedStatement(sql3);
		try{
			db = new DBUtility();
			db.createPreparedStatement(sql2);
			String []arr = {officeID};
			ArrayList test = db.executeQuery(arr);
			//System.out.println("size of o/p is " +test.size());
			for (Object object : test) {
				ArrayList temp = (ArrayList) object;
				cnt.add( Integer.valueOf((String) temp.get(3)));
			}
			Collections.sort(cnt);
			 Map<String, Integer> hm = new HashMap<String, Integer>();
			 for (Integer i : cnt) {
		            Integer j = hm.get(i);
		            if(hm.containsKey(String.valueOf(i))){
		            	int t = hm.get(String.valueOf(i));
		            	hm.put(String.valueOf(i), t+1);
		            }else{
		            	hm.put(String.valueOf(i), 1);
		            }
		         //   hm.put(String.valueOf(i), (j == null) ? 1 : j + 1);
		        }
			
			 List<Map.Entry<String, Integer> > list =  new LinkedList<Map.Entry<String, Integer> >(hm.entrySet());
			 Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
		            public int compare(Map.Entry<String, Integer> o1,
		                               Map.Entry<String, Integer> o2)
		            {
		                return (o2.getValue()).compareTo(o1.getValue());
		            }
		        });
			 HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
		        for (Map.Entry<String, Integer> aa : list) {
		            temp.put(aa.getKey(), aa.getValue());
		        }
		        int mode = 0;
		        int modeCount = 0;
			 for (Map.Entry<String, Integer> val : temp.entrySet()) {
		            if(modeCount<val.getValue()){
		            	modeCount = val.getValue();
		            	mode = Integer.parseInt(val.getKey());
		            }
		        }
			 
			
			
			//pst.executeBatch();
			System.out.println("office_id -- "+officeID+" mode value -- "+mode);
			db.createPreparedStatement(sql4);
			String arr1[] = {officeID,officeName,"",String.valueOf(modeCount),String.valueOf(mode)};
			boolean bool =db.executeUpdate(arr1);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			db.closeConnection();
			e.printStackTrace();
		}
	}
}
