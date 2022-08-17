package com.wipro.igrs.copyissuance.util;

/**
 * ===========================================================================
 * File           :   CommonUtil.java
 * Description    :   Represents the util Class

 * Author         :   Dev Pradhan
 * Created Date   :   Jan 08, 2008

 * ===========================================================================
 */



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.wipro.igrs.db.DBUtility;

public class CommonUtil
{

    private String url = null;
    private String initial = null;
    private String user = null;
    private String pwd = null;
    //private DBUtility dbUtil;

    public CommonUtil() {
        try
        {
           // dbUtil=new DBUtility();
        } catch (Exception x)
        {
         x.printStackTrace();
        }
    }




    /**
     * This method is used to get the primary key id automatically.
     * @param tableName
     * @param colName
     * @param prefix
     * @return String value
     */
    public synchronized String getAutoId(String tableName,String colName,String prefix,String district_id){
        String refId=null;
        DBUtility dbUtil=null;
        try
        {
            String sqlCurrentdate="SELECT TO_CHAR(SYSDATE,'DDMMYYYY') FROM DUAL";
             dbUtil=new DBUtility();
            dbUtil.createStatement();
            String currentDate=dbUtil.executeQry(sqlCurrentdate);           
            if(district_id!=null && district_id.length()==1)
            {
            	district_id="0"+district_id;
            }
            String certifiedIdPrefix=prefix+district_id+currentDate;
            String sqlCertifiedId="SELECT MAX(TO_NUMBER(SUBSTR("+colName+","+(certifiedIdPrefix.length()+1)+"))) FROM "+tableName+" WHERE "+colName+"  LIKE '%"+certifiedIdPrefix+"%'";
            String sqlId=dbUtil.executeQry(sqlCertifiedId);
            if ("".equals(sqlId))
            {
                sqlId="0";
            }
            int id=Integer.parseInt(sqlId);
            id++;

            if (id>=0 && id<10)
                sqlId = "0000"+id;
            else if (id>=10 && id<100)
                sqlId = "000"+id;
            else if (id>=100 && id<1000)
                sqlId = "00"+id;
            else if (id>=1000 && id<10000)
                sqlId = "0"+id;
            else if (id>=10000 && id<100000)
                sqlId = ""+id;

            refId=certifiedIdPrefix+sqlId;

        } catch (Exception x)
        {
           x.printStackTrace();
        } finally
        {
            try
            {
                dbUtil.closeConnection();
            } catch (Exception x)
            {
            	x.printStackTrace();
            }
        }

        return refId;
    }

    
    public synchronized String getAutoNumber(){
        String refId=null;
        DBUtility dbUtil=null;
        try
        {
            //String sqlCurrentdate="SELECT TO_CHAR(SYSDATE,'DDMMYYYY') FROM DUAL";
           dbUtil=new DBUtility();
        	dbUtil.createStatement();
            //String currentDate=dbUtil.executeQry(sqlCurrentdate);           
            //String certifiedIdPrefix=currentDate;
            String sqlCertifiedId="select max(APPL_LOG_ID)+1 from IGRS_APPLICATION_LOG ";
            String sqlId=dbUtil.executeQry(sqlCertifiedId);
            if ("".equals(sqlId))
            {
                sqlId="0";
            }
           
            refId=sqlId;

        } catch (Exception x)
        {
           x.printStackTrace();
        } finally
        {
            try
            {
                dbUtil.closeConnection();
            } catch (Exception x)
            {
            	x.printStackTrace();
            }
        }

        return refId;
    }

    public static String getConvertedDate(String dFromDate) {
		String inputDate = dFromDate;
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");		
		String finalDate = "";
		Date newDate = new Date();
		try {
			newDate = formatter.parse(inputDate);
			SimpleDateFormat format = new SimpleDateFormat("dd/MMM/yyyy");
			finalDate = format.format(newDate);			
		} catch (ParseException e) {
			System.out.print(e);
		}
		return  finalDate;
	}
}









