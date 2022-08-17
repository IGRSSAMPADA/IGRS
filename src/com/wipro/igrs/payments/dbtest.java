package com.wipro.igrs.payments;

import java.sql.SQLException;
import java.util.ArrayList;

import com.wipro.igrs.payments.sql.CommonSQL;
import com.wipro.igrs.db.DBUtility;

public class dbtest {
	 
	/**
	 * @param args
	 */
	public static void main(String[] args) {
			 
			 
				 DBUtility dbutil ;
			  
		try {		
			dbutil = new DBUtility();
	    ArrayList distList = new ArrayList(); 
		 //distList = dbutil.executeQuery(CommonSQL.district_names);
		System.out.println("the size of List is   " + distList.size());
			}
		catch(NullPointerException ne){
			System.out.println("NullpointerException   " + ne);
		}
		catch(SQLException sqe){
			System.out.println("Sql Exception  " + sqe);
		}
		catch(Exception e){
			System.out.println("the General Exception  " + e);
		}
		
		
	
		// TODO Auto-generated method stub

	}

}
