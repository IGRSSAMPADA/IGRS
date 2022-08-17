package com.wipro.igrs.propertylock.util;
/**
 * ===========================================================================
 * File           :   CommonUtil.java
 * Description    :   Represents the Action Class

 * Author         :  
 * Created Date   :   Nov 28, 2007

 * ===========================================================================
 */


//import java.util.Properties;

//import javax.naming.InitialContext;
//import javax.naming.NamingException;
import com.wipro.igrs.db.DBUtility;

public class

CommonUtil
{
    private String url = null;
    private String initial = null;
    private String user = null;
    private String pwd = null;
    private DBUtility dbUtil;

    public CommonUtil() {
        try
        {
            dbUtil=new DBUtility();
        } catch (Exception x)
        {
           x.printStackTrace();
        }
    }

    /**
     * 
     * @param tableName
     * @param colName
     * @param prefix
     * @return
     */
    public synchronized String getAutoId(String tableName,String colName,String prefix){
        String refId=null;
        try
        {
            String sqlCurrentdate="SELECT TO_CHAR(SYSDATE,'DDMMYYYY') FROM DUAL";
            dbUtil.createStatement();
            String currentDate=dbUtil.executeQry(sqlCurrentdate);
            String lockIdPrefix=prefix+currentDate;
            String sqlLockId="SELECT MAX(TO_NUMBER(SUBSTR("+colName+","+(lockIdPrefix.length()+1)+"))) FROM "+tableName+" WHERE "+colName+"  LIKE '%"+lockIdPrefix+"%'";
            String sqlId=dbUtil.executeQry(sqlLockId);
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

            refId=lockIdPrefix+sqlId;

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

}
