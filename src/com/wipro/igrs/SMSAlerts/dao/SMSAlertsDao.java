package com.wipro.igrs.SMSAlerts.dao;

import com.wipro.igrs.SMSAlerts.dto.SMSAlertsDTO;
import com.wipro.igrs.SMSAlerts.form.SMSAlertsForm;
import com.wipro.igrs.SMSAlerts.sql.SMSAlertsSql;
import com.wipro.igrs.db.DBUtility;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class SMSAlertsDao
{
  
  public ArrayList getSMSZone(String lang) throws Exception
  {
    ArrayList details = new ArrayList();
    DBUtility dbUtility = null;
    try
    {
      dbUtility = new DBUtility();
      dbUtility.createStatement();
      String sql="";
      if("en".equalsIgnoreCase(lang))
		{
			sql = " SELECT ZONE_ID,ZONE_NAME FROM IGRS_ZONE_MASTER WHERE STATE_ID=? AND ZONE_STATUS='A' order by ZONE_NAME ";
		}
		else if("hi".equalsIgnoreCase(lang))
		{
			sql = " SELECT ZONE_ID,H_ZONE_NAME FROM IGRS_ZONE_MASTER WHERE STATE_ID=? AND ZONE_STATUS='A' order by H_ZONE_NAME ";
		}
      String[] temp = new String[1];
      temp[0] = "1";
      dbUtility.createPreparedStatement(sql);
      details = dbUtility.executeQuery(temp);

    }
    catch (Exception e)
    {

      e.printStackTrace();
    }
    finally {
      dbUtility.closeConnection();
    }
    return details;
  }
  
  public ArrayList getSMSDistrict(String zoneId) throws Exception {
    ArrayList details = new ArrayList();
    DBUtility dbUtility = null;
    try
    {
      dbUtility = new DBUtility();
      dbUtility.createStatement();
      String sql = "SELECT DISTRICT_ID,DISTRICT_NAME,H_DISTRICT_NAME FROM IGRS_DISTRICT_MASTER WHERE ZONE_ID=? AND DISTRICT_STATUS='A' AND STATE_ID='1'";
      String[] temp = new String[1];
      temp[0] = zoneId;
      dbUtility.createPreparedStatement(sql);
      details = dbUtility.executeQuery(temp);
    }
    catch (Exception e)
    {

      e.printStackTrace();
    }
    finally {
      dbUtility.closeConnection();
    }
    return details;
  }
  
  public ArrayList getStateData(String lang)
    throws Exception
  {
    ArrayList details = new ArrayList();
    DBUtility dbUtility = null;
    try
    {
      dbUtility = new DBUtility();
      dbUtility.createStatement();
      String sql = "SELECT DISTINCT  URD.USER_ID AS USER_ID,URD.FIRST_NAME ||' '||URD.MIDDLE_NAME ||' '|| URD.LAST_NAME AS NAME,(SELECT CM.CADRE_NAME FROM IGRS_EMP_CADRE_MASTER CM,IGRS_EMP_OFFICIAL_DETAILS EOD WHERE  EOD.EMP_DESIGNATION_ID=CM.CADRE_ID AND EOD.EMP_ID=URD.USER_ID AND EOD.EMP_OFFICIAL_STATUS='A' AND CM.CADRE_STATUS='A') AS DESIGNATION,URD.MOBILE_NUMBER FROM  IGRS_USER_REG_DETAILS URD,IGRS_USER_ROLE_GROUP_MAPPING URG ,IGRS_ROLE_GROUP_MASTER URM,IGRS_RGROUP_ROLE_MAPPING RRM,IGRS_ROLE_OFFICE_MAPPING ROM,IGRS_OFFICE_MASTER IOM,IGRS_OFFICE_TYPE_MASTER OTM  WHERE    URD.USER_ID=URG.USER_ID and URD.USER_STATUS='A' AND  URG.ROLE_GROUP_ID=URM.ROLE_GROUP_ID AND URG.ROLE_ACTIVE='A' AND URM.ROLE_GROUP_ID=RRM.ROLE_GROUP_ID AND URM.ROLE_GROUP_STATUS='A' AND RRM.STATUS='A' and RRM.ROLE_OFFICE_MAP_ID=ROM.ROLE_OFFICE_MAP_ID AND ROM.OFFICE_ID=IOM.OFFICE_ID AND ROM.STATUS='A' AND IOM.OFFICE_TYPE_ID=OTM.OFFICE_TYPE_ID AND IOM.OFFICE_STATUS='A' AND OTM.OFFICE_TYPE_STATUS='A' AND OTM.OFFICE_TYPE_NAME=? ORDER BY URD.USER_ID";
      String[] temp = new String[1];
      temp[0] = "IGO";
      dbUtility.createPreparedStatement(sql);
      details = dbUtility.executeQuery(temp);

    }
    catch (Exception e)
    {

      e.printStackTrace();
    }
    finally {
      dbUtility.closeConnection();
    }
    return details;
  }
  
  public ArrayList getZoneData(String lang) throws Exception {
    ArrayList details = new ArrayList();
    DBUtility dbUtility = null;
    try
    {
      dbUtility = new DBUtility();
      dbUtility.createStatement();
      String sql = "SELECT DISTINCT (SELECT ZM.ZONE_ID FROM IGRS_ZONE_MASTER ZM,IGRS_DISTRICT_MASTER DM  WHERE ZM.ZONE_ID=DM.ZONE_ID AND DM.DISTRICT_ID=IOM.DISTRICT_ID) AS ZONE_ID,(SELECT ZONE_NAME FROM IGRS_ZONE_MASTER ZM,IGRS_DISTRICT_MASTER DM  WHERE ZM.ZONE_ID=DM.ZONE_ID AND DM.DISTRICT_ID=IOM.DISTRICT_ID) AS ZONE,(SELECT H_ZONE_NAME FROM IGRS_ZONE_MASTER ZM,IGRS_DISTRICT_MASTER DM  WHERE ZM.ZONE_ID=DM.ZONE_ID AND DM.DISTRICT_ID=IOM.DISTRICT_ID) AS HINDI_ZONE, URD.USER_ID AS USER_ID,URD.FIRST_NAME ||' '||URD.MIDDLE_NAME ||' '|| URD.LAST_NAME AS NAME,(SELECT CM.CADRE_NAME FROM IGRS_EMP_CADRE_MASTER CM,IGRS_EMP_OFFICIAL_DETAILS EOD WHERE  EOD.EMP_DESIGNATION_ID=CM.CADRE_ID AND EOD.EMP_ID=URD.USER_ID AND EOD.EMP_OFFICIAL_STATUS='A' AND CM.CADRE_STATUS='A') AS DESIGNATION,URD.MOBILE_NUMBER FROM  IGRS_USER_REG_DETAILS URD,IGRS_USER_ROLE_GROUP_MAPPING URG ,IGRS_ROLE_GROUP_MASTER URM,IGRS_RGROUP_ROLE_MAPPING RRM,IGRS_ROLE_OFFICE_MAPPING ROM,IGRS_OFFICE_MASTER IOM,IGRS_OFFICE_TYPE_MASTER OTM  WHERE    URD.USER_ID=URG.USER_ID and URD.USER_STATUS='A' AND  URG.ROLE_GROUP_ID=URM.ROLE_GROUP_ID AND URG.ROLE_ACTIVE='A' AND URM.ROLE_GROUP_ID=RRM.ROLE_GROUP_ID AND URM.ROLE_GROUP_STATUS='A' AND RRM.STATUS='A' and RRM.ROLE_OFFICE_MAP_ID=ROM.ROLE_OFFICE_MAP_ID AND ROM.OFFICE_ID=IOM.OFFICE_ID AND ROM.STATUS='A' AND IOM.OFFICE_TYPE_ID=OTM.OFFICE_TYPE_ID AND IOM.OFFICE_STATUS='A' AND OTM.OFFICE_TYPE_STATUS='A' AND OTM.OFFICE_TYPE_NAME in (?,?) ORDER BY URD.USER_ID";
      String[] temp = new String[2];
      temp[0] = "DIGO";
      temp[1] = "JIG";
      dbUtility.createPreparedStatement(sql);
      details = dbUtility.executeQuery(temp);

    }
    catch (Exception e)
    {

      e.printStackTrace();
    }
    finally {
      dbUtility.closeConnection();
    }
    return details;
  }
  
  public boolean insertSMSData(SMSAlertsForm form, String userId) throws Exception {
    DBUtility dbUtility = null;
    boolean flag = false;
    boolean flag2 = false;
    int seqVal = 0;
    String seq = "";
    try
    {
      try
      {
        dbUtility = new DBUtility();
        dbUtility.setAutoCommit(false);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
      



      dbUtility.createStatement();
      



      String sql1 = "INSERT INTO IGRS_CUSTOM_SMS_MASTER D (D.SMS_TYPE,D.STATE,D.USER_ZONE,D.USER_DISTRICT,D.USER_ID,D.USER_NAME,D.USER_DESIGNATION,D.USER_MOBILE_NUMBER,D.USER_STATUS,D.USER_TYPE,D.FLAG_VALUE,D.RESPONSE_CODE,D.CREATED_BY,D.CREATED_DATE,D.UPDATE_BY,D.UPDATE_DATE)VALUES (?,?,?,?,?,?,?,?,'A',?,?,?,?,SYSDATE,?,SYSDATE)";
      System.out.println("INSERT_SMS_INSERT_DATA");
      try {
        dbUtility.createPreparedStatement(sql1);
        String[] temp = new String[13];
        
        temp[0] = form.getUserType();
        temp[1] = "Madhya Pradesh";
        if ((form.getZoneId() != null) && (form.getZoneId() != "")) {
          temp[2] = form.getZoneId();
        }
        else
        {
          temp[2] = "";
        }
        if ((form.getSmsdto().getDistrictId() != null) && (form.getSmsdto().getDistrictId() != ""))
        {
          temp[3] = form.getSmsdto().getDistrictId();
        }
        else
        {
          temp[3] = "";
        }
        
        temp[4] = "";
        temp[5] = form.getSmsdto().getUserName();
        temp[6] = form.getSmsdto().getUserDesignation();
        temp[7] = form.getSmsdto().getUserMobileNumber();
        temp[8] = form.getUserTypes();
        temp[9] = "";
        
        temp[10] = "";
        temp[11] = userId;
        temp[12] = userId;
        
        flag = dbUtility.executeUpdate(temp);
        System.out.println("flag value in Insert method" + flag);
      } catch (Exception e) {
        e.printStackTrace();
      }
      
      if (flag)
      {
        dbUtility.commit();

      }
      else
      {
        dbUtility.rollback();
      }
      

    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    finally {
      dbUtility.closeConnection();
    }
    return flag;
  }
  
  public ArrayList getStateDataNew(String lang) throws Exception { ArrayList details = new ArrayList();
    DBUtility dbUtility = null;
    try
    {
      dbUtility = new DBUtility();
      dbUtility.createStatement();
      String sql = "SELECT S_NO,USER_NAME,USER_DESIGNATION,USER_MOBILE_NUMBER FROM IGRS_CUSTOM_SMS_MASTER WHERE USER_STATUS='A' AND SMS_TYPE=?";
      String[] temp = new String[1];
      temp[0] = "S";
      dbUtility.createPreparedStatement(sql);
      details = dbUtility.executeQuery(temp);

    }
    catch (Exception e)
    {

      e.printStackTrace();
    }
    finally {
      dbUtility.closeConnection();
    }
    return details;
  }
  
  public boolean deleteSMSData(SMSAlertsForm form, String userId, String[] checkBoxSize) throws Exception {
    DBUtility dbUtility = null;
    boolean flag = false;
    boolean flag2 = false;
    int seqVal = 0;
    String seq = "";
    try
    {
      try
      {
        dbUtility = new DBUtility();
        dbUtility.setAutoCommit(false);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
      
      String[] awr = form.getCheckBoxFlag().split(",");
      for (int i = 0; i < checkBoxSize.length; i++)
      {
        String sql1 = "UPDATE IGRS_CUSTOM_SMS_MASTER SET USER_STATUS='D' WHERE S_NO=? AND SMS_TYPE='S'  ";
        System.out.println("DELETE_SMS_STATE_DATA");
        try {
          dbUtility.createPreparedStatement(sql1);
          String[] temp = new String[1];
          
          String[] checkData = awr[i].split("#");
          
          temp[0] = checkData[0];
          flag = dbUtility.executeUpdate(temp);
        }
        catch (Exception e) {
          e.printStackTrace();
        }
      }
      
      if (flag)
      {
        dbUtility.commit();

      }
      else
      {
        dbUtility.rollback();
      }
      

    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    finally {
      dbUtility.closeConnection();
    }
    return flag;
  }
  
  public boolean updateStateSMSData(SMSAlertsForm form, String userId, String[] checkBoxSize) throws Exception
  {
    DBUtility dbUtility = null;
    boolean flag = false;
    boolean flag2 = false;
    int seqVal = 0;
    String seq = "";
    try
    {
      try
      {
        dbUtility = new DBUtility();
        dbUtility.setAutoCommit(false);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
      

      String[] awr = form.getCheckBoxFlag().split(",");
      for (int i = 0; i < checkBoxSize.length; i++) {
        String sql1 = "INSERT INTO IGRS_CUSTOM_SMS_MASTER D (D.SMS_TYPE,D.STATE,D.USER_ZONE,D.USER_DISTRICT,D.USER_ID,D.USER_NAME,D.USER_DESIGNATION,D.USER_MOBILE_NUMBER,D.USER_STATUS,D.USER_TYPE,D.FLAG_VALUE,D.RESPONSE_CODE,D.CREATED_BY,D.CREATED_DATE,D.UPDATE_BY,D.UPDATE_DATE)VALUES (?,?,?,?,?,?,?,?,'A',?,?,?,?,SYSDATE,?,SYSDATE)";
        
        try
        {
          dbUtility.createPreparedStatement(sql1);
          String[] temp = new String[13];
          
          String[] checkData = awr[i].split("#");
          temp[0] = form.getUserType();
          temp[1] = "Madhya Pradesh";
          temp[2] = form.getZoneName();
          temp[3] = form.getDistrictName();
          temp[4] = form.getUserId();
          

          temp[2] = "";
          temp[3] = "";
          temp[4] = checkData[0];
          temp[5] = checkData[1];
          

          temp[6] = checkData[2];
          temp[7] = checkData[3];
          temp[8] = form.getUserTypes();
          temp[9] = "on";
          temp[10] = "";
          temp[11] = userId;
          temp[12] = userId;
          flag = dbUtility.executeUpdate(temp);
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
      }
      if (flag)
      {
        dbUtility.commit();

      }
      else
      {
        dbUtility.rollback();
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    finally {
      dbUtility.closeConnection();
    }
    return flag;
  }
  
  public ArrayList getsmsDistrictData(String lang) throws Exception
  {
    ArrayList details = new ArrayList();
    DBUtility dbUtility = null;
    
    try
    {
      dbUtility = new DBUtility();
      dbUtility.createStatement();
      String sql = "SELECT USER_ZONE,USER_DISTRICT,USER_ID,FLAG_VALUE,USER_MOBILE_NUMBER FROM IGRS_CUSTOM_SMS_MASTER WHERE USER_STATUS='A' AND SMS_TYPE='D'  AND USER_TYPE=?";
      String[] temp = new String[1];
      temp[0] = "system";
      
      dbUtility.createPreparedStatement(sql);
      details = dbUtility.executeQuery(temp);


    }
    catch (Exception e)
    {

      e.printStackTrace();
    }
    finally {
      dbUtility.closeConnection();
    }
    return details;
  }
  
  public ArrayList getDistrictData(String lang) throws Exception { ArrayList details = new ArrayList();
    
    DBUtility dbUtility = null;
    
    try
    {
      dbUtility = new DBUtility();
      dbUtility.createStatement();
      String sql = "SELECT (SELECT ZM.ZONE_ID FROM IGRS_ZONE_MASTER ZM,IGRS_DISTRICT_MASTER DM  WHERE ZM.ZONE_ID=DM.ZONE_ID AND DM.DISTRICT_ID=IOM.DISTRICT_ID) AS ZONE_ID, (SELECT ZONE_NAME FROM IGRS_ZONE_MASTER ZM,IGRS_DISTRICT_MASTER DM  WHERE ZM.ZONE_ID=DM.ZONE_ID AND DM.DISTRICT_ID=IOM.DISTRICT_ID) AS ZONE,(SELECT H_ZONE_NAME FROM IGRS_ZONE_MASTER ZM,IGRS_DISTRICT_MASTER DM  WHERE ZM.ZONE_ID=DM.ZONE_ID AND DM.DISTRICT_ID=IOM.DISTRICT_ID) AS HINDI_ZONE, (SELECT DISTRICT_ID FROM IGRS_DISTRICT_MASTER DM  WHERE  DM.DISTRICT_ID=IOM.DISTRICT_ID) AS DISTRICT,(SELECT DISTRICT_NAME FROM IGRS_DISTRICT_MASTER DM  WHERE  DM.DISTRICT_ID=IOM.DISTRICT_ID) AS DISTRICT,(SELECT H_DISTRICT_NAME FROM IGRS_DISTRICT_MASTER DM  WHERE  DM.DISTRICT_ID=IOM.DISTRICT_ID) AS HINDI_DISTRICT,URD.USER_ID AS USER_ID,URD.FIRST_NAME ||' '||URD.MIDDLE_NAME ||' '|| URD.LAST_NAME AS NAME,(SELECT CM.CADRE_NAME FROM IGRS_EMP_CADRE_MASTER CM,IGRS_EMP_OFFICIAL_DETAILS EOD WHERE  EOD.EMP_DESIGNATION_ID=CM.CADRE_ID AND EOD.EMP_ID=URD.USER_ID AND EOD.EMP_OFFICIAL_STATUS='A' AND CM.CADRE_STATUS='A') AS DESIGNATION,URD.MOBILE_NUMBER FROM  IGRS_USER_REG_DETAILS URD,IGRS_USER_ROLE_GROUP_MAPPING URG ,IGRS_ROLE_GROUP_MASTER URM,IGRS_RGROUP_ROLE_MAPPING RRM,IGRS_ROLE_OFFICE_MAPPING ROM,IGRS_OFFICE_MASTER IOM,IGRS_OFFICE_TYPE_MASTER OTM  WHERE    URD.USER_ID=URG.USER_ID and URD.USER_STATUS='A' AND  URG.ROLE_GROUP_ID=URM.ROLE_GROUP_ID AND URG.ROLE_ACTIVE='A' AND URM.ROLE_GROUP_ID=RRM.ROLE_GROUP_ID AND URM.ROLE_GROUP_STATUS='A' AND RRM.STATUS='A' and RRM.ROLE_OFFICE_MAP_ID=ROM.ROLE_OFFICE_MAP_ID AND ROM.OFFICE_ID=IOM.OFFICE_ID AND ROM.STATUS='A' AND IOM.OFFICE_TYPE_ID=OTM.OFFICE_TYPE_ID AND IOM.OFFICE_STATUS='A' AND OTM.OFFICE_TYPE_STATUS='A' AND OTM.OFFICE_TYPE_NAME IN (?,?,?) ORDER BY URD.USER_ID";
      
      String[] temp = new String[3];
      temp[0] = "DRO";
      temp[1] = "SRO";
      temp[2] = "RM";
      dbUtility.createPreparedStatement(sql);
      
      details = dbUtility.executeQuery(temp);

    }
    catch (Exception e)
    {

      e.printStackTrace();
    }
    finally {
      dbUtility.closeConnection();
    }
    return details;
  }
  
  public boolean insertCustomSMSData(SMSAlertsForm form, String userId) throws Exception {
    DBUtility dbUtility = null;
    boolean flag = false;
    boolean flag2 = false;
    int seqVal = 0;
    String seq = "";
    try
    {
      try
      {
        dbUtility = new DBUtility();
        dbUtility.setAutoCommit(false);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
      








      String sql1 = "INSERT INTO IGRS_TEMPLATE_SMS_MASTER M (M.SMS_TYPE,M.SMS_TEMPLATE,M.H_SMS_TEMPLATE,M.SMS_STATUS,M.CREATED_BY,M.CREATED_DATE,M.UPDATE_BY,M.UPDATE_DATE) VALUES(?,?,?,'A',?,SYSDATE,?,SYSDATE)";
      System.out.println("INSERT_CUSTOMSMS_INSERT_DATA");
      try {
        dbUtility.createPreparedStatement(sql1);
        String[] temp = new String[5];
        temp[0] = form.getUserTypes();
        temp[1] = form.getSmsEnglishContent();
        temp[2] = form.getSmsHindiContent();
        temp[3] = userId;
        temp[4] = userId;
        
        flag = dbUtility.executeUpdate(temp);
      }
      catch (Exception e) {
        e.printStackTrace();
      }
      
      if (flag)
      {
        dbUtility.commit();

      }
      else
      {
        dbUtility.rollback();
      }
      

    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    finally {
      dbUtility.closeConnection();
    }
    return flag;
  }
  
  public boolean updateSMSData(SMSAlertsForm form, String userId, String[] checkBoxSize) throws Exception {
    DBUtility dbUtility = null;
    boolean flag = false;
    boolean flag2 = false;
    int seqVal = 0;
    String seq = "";
    try
    {
      try
      {
        dbUtility = new DBUtility();
        dbUtility.setAutoCommit(false);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
      
      System.out.println("updated Lenght" + checkBoxSize.length);
      String[] awr = form.getCheckBoxFlag().split(",");
      

      for (int i = 0; i < checkBoxSize.length; i++) {
        System.out.println("Serial No" + form.getSerialNo());
        String sql1 = "INSERT INTO IGRS_CUSTOM_SMS_MASTER D (D.SMS_TYPE,D.STATE,D.USER_ZONE,D.USER_DISTRICT,D.USER_ID,D.USER_NAME,D.USER_DESIGNATION,D.USER_MOBILE_NUMBER,D.USER_STATUS,D.USER_TYPE,D.FLAG_VALUE,D.RESPONSE_CODE,D.CREATED_BY,D.CREATED_DATE,D.UPDATE_BY,D.UPDATE_DATE)VALUES (?,?,?,?,?,?,?,?,'A',?,?,?,?,SYSDATE,?,SYSDATE)";
        
        try
        {
          dbUtility.createPreparedStatement(sql1);
          String[] temp = new String[12];
          
          String[] checkData = awr[i].split("#");
          temp[0] = form.getUserType();
          temp[1] = "Madhya Pradesh";
          

          System.out.println("checkData" + checkData[0]);
          System.out.println("checkData" + checkData[1]);
          System.out.println("checkData" + checkData[2]);
          System.out.println("checkData" + checkData[3]);
          

          temp[2] = form.getZoneName();
          temp[3] = form.getDistrictName();
          temp[4] = form.getUserId();
          

          temp[2] = checkData[0];
          temp[3] = checkData[1];
          temp[4] = checkData[2];
          temp[5] = checkData[3];
          

          temp[2] = "";
          temp[3] = "";
          temp[4] = form.getUserId();
          temp[5] = form.getUserName();
          temp[6] = form.getUserDesignation();
          temp[7] = form.getUserMobileNumber();
          temp[8] = form.getUserTypes();
          temp[9] = checkBoxSize[i];
          temp[10] = userId;
          temp[11] = userId;
          flag = dbUtility.executeUpdate(temp);
        }
        catch (Exception e) {
          e.printStackTrace();
        }
      }
      if (flag)
      {
        dbUtility.commit();

      }
      else
      {
        dbUtility.rollback();
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    finally {
      dbUtility.closeConnection();
    }
    return flag;
  }
  
  public boolean updateStateMobileData(SMSAlertsForm form, String userId, String[] checkBoxSize) throws Exception {
    DBUtility dbUtility = null;
    boolean flag = false;
    boolean flag2 = false;
    int seqVal = 0;
    String seq = "";
    try
    {
      try
      {
        dbUtility = new DBUtility();
        dbUtility.setAutoCommit(false);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
      

      String[] awr = form.getCheckBoxFlag().split(",");
      for (int i = 0; i < checkBoxSize.length; i++) {
        String sql1 = "UPDATE IGRS_CUSTOM_SMS_MASTER SET  USER_MOBILE_NUMBER=?  WHERE S_NO=? AND USER_STATUS='A' AND SMS_TYPE='S'";
        
        try
        {
          dbUtility.createPreparedStatement(sql1);
          String[] temp = new String[2];
          
          String[] checkData = awr[i].split("#");
          




          temp[0] = checkData[4];
          temp[1] = checkData[0];
          


          flag = dbUtility.executeUpdate(temp);
        }
        catch (Exception e) {
          e.printStackTrace();
        }
      }
      if (flag)
      {
        dbUtility.commit();

      }
      else
      {
        dbUtility.rollback();
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    finally {
      dbUtility.closeConnection();
    }
    return flag;
  }
  
  public boolean updatezoneSMSData(SMSAlertsForm form, String userId, String[] checkBoxSize, String lang) throws Exception {
    DBUtility dbUtility = null;
    boolean flag = false;
    boolean flag2 = false;
    int seqVal = 0;
    String seq = "";
    ArrayList zone = null;
    try
    {
      try
      {
        dbUtility = new DBUtility();
        dbUtility.setAutoCommit(false);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
      

      String[] awr = form.getCheckBoxFlag().split(",");
      for (int i = 0; i < checkBoxSize.length; i++) {
        String sql1 = "INSERT INTO IGRS_CUSTOM_SMS_MASTER D (D.SMS_TYPE,D.STATE,D.USER_ZONE,D.USER_DISTRICT,D.USER_ID,D.USER_NAME,D.USER_DESIGNATION,D.USER_MOBILE_NUMBER,D.USER_STATUS,D.USER_TYPE,D.FLAG_VALUE,D.RESPONSE_CODE,D.CREATED_BY,D.CREATED_DATE,D.UPDATE_BY,D.UPDATE_DATE)VALUES (?,?,?,?,?,?,?,?,'A',?,?,?,?,SYSDATE,?,SYSDATE)";
        
        try
        {
          dbUtility.createPreparedStatement(sql1);
          String[] temp = new String[13];
          
          String[] checkData = awr[i].split("#");
          temp[0] = form.getUserType();
          temp[1] = "Madhya Pradesh";
          if (checkData[0].length() > 0) {
            String zoneName = checkData[0];
            System.out.println("zoneName" + zoneName);
            if ("en".equalsIgnoreCase(lang)) {
              zone = getzoneId(zoneName, lang);
            }
            else if ("hi".equalsIgnoreCase(lang)) {
              System.out.println("zoneName in Hindi Block");
              zone = getzoneHiId(zoneName, lang);
            }
            for (int k = 0; k < zone.size(); k++)
            {
              ArrayList lst = (ArrayList)zone.get(k);
              temp[2] = ((String)lst.get(0));
            }
          }
          


          temp[3] = "";
          temp[4] = checkData[1];
          temp[5] = checkData[2];
          

          temp[6] = checkData[3];
          temp[7] = checkData[4];
          temp[8] = form.getUserTypes();
          temp[9] = "on";
          temp[10] = "";
          temp[11] = userId;
          temp[12] = userId;
          flag = dbUtility.executeUpdate(temp);
        }
        catch (Exception e) {
          e.printStackTrace();
        }
      }
      if (flag)
      {
        dbUtility.commit();

      }
      else
      {
        dbUtility.rollback();
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    finally {
      dbUtility.closeConnection();
    }
    return flag;
  }
  
  private static ArrayList getzoneHiId(String zoneName, String lang) throws Exception { ArrayList details = new ArrayList();
    DBUtility dbUtility = null;
    try
    {
      dbUtility = new DBUtility();
      dbUtility.createStatement();
      String sql = "SELECT ZONE_ID FROM  IGRS_ZONE_MASTER WHERE H_ZONE_NAME=? AND ZONE_STATUS='A'";
      String[] temp = new String[1];
      temp[0] = zoneName;
      dbUtility.createPreparedStatement(sql);
      details = dbUtility.executeQuery(temp);

    }
    catch (Exception e)
    {

      e.printStackTrace();
    }
    finally {
      dbUtility.closeConnection();
    }
    return details;
  }
  
  private static ArrayList getzoneId(String zoneName, String lang) throws Exception {
    ArrayList details = new ArrayList();
    DBUtility dbUtility = null;
    try
    {
      dbUtility = new DBUtility();
      dbUtility.createStatement();
      String sql = "SELECT ZONE_ID FROM  IGRS_ZONE_MASTER WHERE ZONE_NAME=? AND ZONE_STATUS='A'";
      String[] temp = new String[1];
      temp[0] = zoneName;
      dbUtility.createPreparedStatement(sql);
      details = dbUtility.executeQuery(temp);

    }
    catch (Exception e)
    {

      e.printStackTrace();
    }
    finally {
      dbUtility.closeConnection();
    }
    return details;
  }
  
  public boolean deletezoneSMSData(SMSAlertsForm form, String userId, String[] checkBoxSize, String lang) throws Exception
  {
    DBUtility dbUtility = null;
    boolean flag = false;
    boolean flag2 = false;
    int seqVal = 0;
    String seq = "";
    ArrayList zone = new ArrayList();
    try
    {
      try
      {
        dbUtility = new DBUtility();
        dbUtility.setAutoCommit(false);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
      
      String[] awr = form.getCheckBoxFlag().split(",");
      for (int i = 0; i < checkBoxSize.length; i++)
      {
        String sql1 = "UPDATE IGRS_CUSTOM_SMS_MASTER SET USER_STATUS='D' WHERE S_NO=? AND SMS_TYPE='Z'";
        System.out.println("DELETE_SMS_ZONE_DATA");
        try {
          dbUtility.createPreparedStatement(sql1);
          String[] temp = new String[1];
          
          String[] checkData = awr[i].split("#");
          

          temp[0] = checkData[0];
          flag = dbUtility.executeUpdate(temp);
        }
        catch (Exception e) {
          e.printStackTrace();
        }
      }
      
      if (flag)
      {
        dbUtility.commit();

      }
      else
      {
        dbUtility.rollback();
      }
      

    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    finally {
      dbUtility.closeConnection();
    }
    return flag;
  }
  
  public boolean updatezoneSMSMobileData(SMSAlertsForm form, String userId, String[] checkBoxSize, String lang) throws Exception {
    DBUtility dbUtility = null;
    boolean flag = false;
    boolean flag2 = false;
    int seqVal = 0;
    String seq = "";
    ArrayList zone = new ArrayList();
    try
    {
      try
      {
        dbUtility = new DBUtility();
        dbUtility.setAutoCommit(false);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
      

      String[] awr = form.getCheckBoxFlag().split(",");
      for (int i = 0; i < checkBoxSize.length; i++) {
        String sql1 = "UPDATE IGRS_CUSTOM_SMS_MASTER SET USER_MOBILE_NUMBER=? WHERE S_NO=?  AND USER_STATUS='A' AND SMS_TYPE='Z'";
        
        try
        {
          dbUtility.createPreparedStatement(sql1);
          String[] temp = new String[2];
          
          String[] checkData = awr[i].split("#");
          
          temp[0] = checkData[5];
          


          temp[1] = checkData[0];
          



          flag = dbUtility.executeUpdate(temp);
        }
        catch (Exception e) {
          e.printStackTrace();
        }
      }
      if (flag)
      {
        dbUtility.commit();

      }
      else
      {
        dbUtility.rollback();
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    finally {
      dbUtility.closeConnection();
    }
    return flag;
  }
  
  public boolean updateDistrictSMSData(SMSAlertsForm form, String userId, String[] checkBoxSize, String lang) throws Exception {
    DBUtility dbUtility = null;
    boolean flag = false;
    boolean flag2 = false;
    int seqVal = 0;
    String seq = "";
    ArrayList zoneDistrict = null;
    try
    {
      try
      {
        dbUtility = new DBUtility();
        dbUtility.setAutoCommit(false);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
      

      String[] awr = form.getCheckBoxFlag().split(",");
      System.out.println("awr[]" + awr);
      for (int i = 0; i < checkBoxSize.length; i++) {
        String sql1 = "INSERT INTO IGRS_CUSTOM_SMS_MASTER D (D.SMS_TYPE,D.STATE,D.USER_ZONE,D.USER_DISTRICT,D.USER_ID,D.USER_NAME,D.USER_DESIGNATION,D.USER_MOBILE_NUMBER,D.USER_STATUS,D.USER_TYPE,D.FLAG_VALUE,D.RESPONSE_CODE,D.CREATED_BY,D.CREATED_DATE,D.UPDATE_BY,D.UPDATE_DATE)VALUES (?,?,?,?,?,?,?,?,'A',?,?,?,?,SYSDATE,?,SYSDATE)";
        
        try
        {
          dbUtility.createPreparedStatement(sql1);
          String[] temp = new String[13];
          
          String[] checkData = awr[i].split("#");
          temp[0] = form.getUserType();
          temp[1] = "Madhya Pradesh";
          

          if ((checkData[0].length() > 0) && (checkData[1].length() > 0)) {
            String zoneName = checkData[0];
            String districtName = checkData[1];
            
            if ("en".equalsIgnoreCase(lang)) {
              zoneDistrict = getzoneDistrictId(zoneName, districtName, lang);
            }
            else if ("hi".equalsIgnoreCase(lang))
            {
              zoneDistrict = getzoneDistrictHiId(zoneName, districtName, lang);
            }
            for (int k = 0; k < zoneDistrict.size(); k++)
            {
              ArrayList lst = (ArrayList)zoneDistrict.get(k);
              temp[2] = ((String)lst.get(0));
              temp[3] = ((String)lst.get(1));
            }
          }
          




          temp[4] = checkData[2];
          temp[5] = checkData[3];
          temp[6] = checkData[4];
          temp[7] = checkData[5];
          temp[8] = form.getUserTypes();
          temp[9] = "on";
          temp[10] = "";
          temp[11] = userId;
          temp[12] = userId;
          flag = dbUtility.executeUpdate(temp);
        }
        catch (Exception e) {
          e.printStackTrace();
        }
      }
      if (flag)
      {
        dbUtility.commit();

      }
      else
      {
        dbUtility.rollback();
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    finally {
      dbUtility.closeConnection();
    }
    return flag;
  }
  
  private static ArrayList getzoneDistrictHiId(String zoneName, String districtName, String lang) throws Exception {
    ArrayList details = new ArrayList();
    DBUtility dbUtility = null;
    try
    {
      dbUtility = new DBUtility();
      dbUtility.createStatement();
      String sql = "SELECT ZM.ZONE_ID ,DM.DISTRICT_ID FROM IGRS_ZONE_MASTER ZM,IGRS_DISTRICT_MASTER DM WHERE ZM.ZONE_ID=DM.ZONE_ID AND ZM.ZONE_STATUS='A' AND DM.DISTRICT_STATUS='A' AND ZM.H_ZONE_NAME=? AND DM.H_DISTRICT_NAME=?";
      String[] temp = new String[2];
      temp[0] = zoneName;
      temp[1] = districtName;
      dbUtility.createPreparedStatement(sql);
      details = dbUtility.executeQuery(temp);

    }
    catch (Exception e)
    {

      e.printStackTrace();
    }
    finally {
      dbUtility.closeConnection();
    }
    return details;
  }
  
  private static ArrayList getzoneDistrictId(String zoneName, String districtName, String lang) throws Exception
  {
    ArrayList details = new ArrayList();
    DBUtility dbUtility = null;
    try
    {
      dbUtility = new DBUtility();
      dbUtility.createStatement();
      String sql = "SELECT ZM.ZONE_ID ,DM.DISTRICT_ID FROM IGRS_ZONE_MASTER ZM,IGRS_DISTRICT_MASTER DM WHERE ZM.ZONE_ID=DM.ZONE_ID AND ZM.ZONE_STATUS='A' AND DM.DISTRICT_STATUS='A' AND ZM.ZONE_NAME=? AND DM.DISTRICT_NAME=?";
      String[] temp = new String[2];
      temp[0] = zoneName;
      temp[1] = districtName;
      dbUtility.createPreparedStatement(sql);
      details = dbUtility.executeQuery(temp);

    }
    catch (Exception e)
    {

      e.printStackTrace();
    }
    finally {
      dbUtility.closeConnection();
    }
    return details;
  }
  
  public boolean deleteDistrictSMSData(SMSAlertsForm form, String userId, String[] checkBoxSize) throws Exception
  {
    DBUtility dbUtility = null;
    boolean flag = false;
    boolean flag2 = false;
    int seqVal = 0;
    String seq = "";
    try
    {
      try
      {
        dbUtility = new DBUtility();
        dbUtility.setAutoCommit(false);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
      
      String[] awr = form.getCheckBoxFlag().split(",");
      for (int i = 0; i < checkBoxSize.length; i++)
      {
        String sql1 = "UPDATE IGRS_CUSTOM_SMS_MASTER SET USER_STATUS='D' WHERE S_NO=? AND SMS_TYPE='D'";
        System.out.println("DELETE_SMS_DISTRICT_DATA");
        try {
          dbUtility.createPreparedStatement(sql1);
          String[] temp = new String[1];
          
          String[] checkData = awr[i].split("#");
          
          temp[0] = checkData[0];
          flag = dbUtility.executeUpdate(temp);
        }
        catch (Exception e) {
          e.printStackTrace();
        }
      }
      
      if (flag)
      {
        dbUtility.commit();

      }
      else
      {
        dbUtility.rollback();
      }
      

    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    finally {
      dbUtility.closeConnection();
    }
    return flag;
  }
  
  public boolean updateDistrictSMSMobileData(SMSAlertsForm form, String userId, String[] checkBoxSize) throws Exception {
    DBUtility dbUtility = null;
    boolean flag = false;
    boolean flag2 = false;
    int seqVal = 0;
    String seq = "";
    try
    {
      try
      {
        dbUtility = new DBUtility();
        dbUtility.setAutoCommit(false);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
      

      String[] awr = form.getCheckBoxFlag().split(",");
      for (int i = 0; i < checkBoxSize.length; i++) {
        String sql1 = "UPDATE IGRS_CUSTOM_SMS_MASTER SET  USER_MOBILE_NUMBER=? WHERE S_NO=?  AND USER_STATUS='A' AND SMS_TYPE='D'";
        
        try
        {
          dbUtility.createPreparedStatement(sql1);
          String[] temp = new String[2];
          
          String[] checkData = awr[i].split("#");
          

          temp[0] = checkData[6];
          temp[1] = checkData[0];
          

          flag = dbUtility.executeUpdate(temp);
        }
        catch (Exception e) {
          e.printStackTrace();
        }
      }
      if (flag)
      {
        dbUtility.commit();

      }
      else
      {
        dbUtility.rollback();
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    finally {
      dbUtility.closeConnection();
    }
    return flag;
  }
  
  public ArrayList getsmsStateData(String lang) throws Exception { ArrayList details = new ArrayList();
    DBUtility dbUtility = null;
    
    try
    {
      dbUtility = new DBUtility();
      dbUtility.createStatement();
      String sql = "SELECT USER_ID,FLAG_VALUE,USER_MOBILE_NUMBER FROM IGRS_CUSTOM_SMS_MASTER WHERE USER_STATUS='A'  AND SMS_TYPE='S' AND USER_TYPE=?";
      String[] temp = new String[1];
      temp[0] = "system";
      
      dbUtility.createPreparedStatement(sql);
      details = dbUtility.executeQuery(temp);


    }
    catch (Exception e)
    {

      e.printStackTrace();
    }
    finally {
      dbUtility.closeConnection();
    }
    return details;
  }
  
  public ArrayList getsmsZoneData(String lang) throws Exception {
    ArrayList details = new ArrayList();
    DBUtility dbUtility = null;
    
    try
    {
      dbUtility = new DBUtility();
      dbUtility.createStatement();
      String sql = "SELECT USER_ZONE, USER_ID,FLAG_VALUE,USER_MOBILE_NUMBER FROM IGRS_CUSTOM_SMS_MASTER WHERE USER_STATUS='A'  AND SMS_TYPE='Z' AND USER_TYPE=?";
      String[] temp = new String[1];
      temp[0] = "system";
      
      dbUtility.createPreparedStatement(sql);
      details = dbUtility.executeQuery(temp);


    }
    catch (Exception e)
    {

      e.printStackTrace();
    }
    finally {
      dbUtility.closeConnection();
    }
    return details;
  }
  
  public ArrayList getCustomSMSData(SMSAlertsForm form, String userId) throws Exception {
    ArrayList details = new ArrayList();
    DBUtility dbUtility = null;
    
    try
    {
      dbUtility = new DBUtility();
      dbUtility.createStatement();
      String sql = "SELECT SMS_TYPE,SMS_TEMPLATE,H_SMS_TEMPLATE FROM IGRS_TEMPLATE_SMS_MASTER WHERE SMS_TYPE=? AND SMS_STATUS='A' ";
      String[] temp = new String[1];
      temp[0] = form.getUserTypes();
      
      dbUtility.createPreparedStatement(sql);
      details = dbUtility.executeQuery(temp);


    }
    catch (Exception e)
    {

      e.printStackTrace();
    }
    finally {
      dbUtility.closeConnection();
    }
    return details;
  }
  
  public boolean updateCustomSMSData(SMSAlertsForm form, String userId) throws Exception {
    DBUtility dbUtility = null;
    boolean flag = false;
    boolean flag2 = false;
    int seqVal = 0;
    String seq = "";
    try
    {
      try
      {
        dbUtility = new DBUtility();
        dbUtility.setAutoCommit(false);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
      


      String sql1 = "UPDATE IGRS_TEMPLATE_SMS_MASTER SET SMS_TEMPLATE=? ,H_SMS_TEMPLATE=? WHERE SMS_TYPE=? AND SMS_STATUS='A' ";
      
      try
      {
        dbUtility.createPreparedStatement(sql1);
        String[] temp = new String[3];
        
        temp[0] = form.getSmsEnglishContent();
        temp[1] = form.getSmsHindiContent();
        temp[2] = form.getUserTypes();
        

        flag = dbUtility.executeUpdate(temp);
        System.out.println("temp flag" + flag);
      }
      catch (Exception e) {
        e.printStackTrace();
      }
      
      if (flag)
      {
        dbUtility.commit();

      }
      else
      {
        dbUtility.rollback();
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    finally {
      dbUtility.closeConnection();
    }
    return flag;
  }
  
  public boolean deleteCustomSMSData(SMSAlertsForm form, String userId) throws Exception { DBUtility dbUtility = null;
    boolean flag = false;
    boolean flag2 = false;
    int seqVal = 0;
    String seq = "";
    try
    {
      try
      {
        dbUtility = new DBUtility();
        dbUtility.setAutoCommit(false);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
      


      String sql1 = "UPDATE IGRS_TEMPLATE_SMS_MASTER SET SMS_STATUS='D' WHERE SMS_TYPE=? AND SMS_STATUS='A'";
      
      try
      {
        dbUtility.createPreparedStatement(sql1);
        String[] temp = new String[1];
        
        temp[0] = form.getUserTypes();
        

        flag = dbUtility.executeUpdate(temp);
        System.out.println("temp flag" + flag);
      }
      catch (Exception e) {
        e.printStackTrace();
      }
      
      if (flag)
      {
        dbUtility.commit();

      }
      else
      {
        dbUtility.rollback();
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    finally {
      dbUtility.closeConnection();
    }
    return flag;
  }
  
  public ArrayList getSPUsersList() throws Exception { ArrayList details = new ArrayList();
    DBUtility dbUtility = null;
    try
    {
      dbUtility = new DBUtility();
      dbUtility.createStatement();
      String sql = "SELECT URD.FIRST_NAME, URD.MOBILE_NUMBER  FROM IGRS_SP_DETLS SP,IGRS_USER_REG_DETAILS URD WHERE SP.APPLCTN_STATUS=8 AND SP.STATUS='A' AND SP.CREATED_BY=URD.USER_ID AND URD.USER_STATUS='A'";
      String[] temp = new String[2];
      
      dbUtility.createPreparedStatement(sql);
      details = dbUtility.executeQuery(temp);

    }
    catch (Exception e)
    {

      e.printStackTrace();
    }
    finally {
      dbUtility.closeConnection();
    }
    return details;
  }
  
  public ArrayList getStateUsersData(SMSAlertsForm form, String lang) throws Exception {
    ArrayList details = new ArrayList();
    DBUtility dbUtility = null;
    try
    {
      dbUtility = new DBUtility();
      dbUtility.createStatement();
      String sql = "SELECT S_NO, USER_ID,USER_NAME,USER_DESIGNATION,USER_MOBILE_NUMBER FROM IGRS_CUSTOM_SMS_MASTER WHERE USER_STATUS='A' AND SMS_TYPE=?  order by S_NO,USER_ID";
      String[] temp = new String[1];
      temp[0] = form.getUserType();
      dbUtility.createPreparedStatement(sql);
      details = dbUtility.executeQuery(temp);

    }
    catch (Exception e)
    {

      e.printStackTrace();
    }
    finally {
      dbUtility.closeConnection();
    }
    return details;
  }
  
  public ArrayList getZoneUsersData(SMSAlertsForm form, String lang) throws Exception {
    ArrayList details = new ArrayList();
    DBUtility dbUtility = null;
    try
    {
      dbUtility = new DBUtility();
      dbUtility.createStatement();
      String sql = "SELECT SMS.S_NO, (SELECT ZM.ZONE_NAME FROM IGRS_ZONE_MASTER ZM WHERE ZM.ZONE_ID=SMS.USER_ZONE AND ZM.ZONE_STATUS='A') AS ZONE,(SELECT ZM.H_ZONE_NAME FROM IGRS_ZONE_MASTER ZM WHERE ZM.ZONE_ID=SMS.USER_ZONE AND ZM.ZONE_STATUS='A') AS HIZONE,SMS.USER_ID,SMS.USER_NAME,SMS.USER_DESIGNATION,SMS.USER_MOBILE_NUMBER FROM IGRS_CUSTOM_SMS_MASTER SMS WHERE SMS.USER_STATUS='A' AND SMS.SMS_TYPE=?   order by SMS.S_NO,SMS.USER_ID";
      String[] temp = new String[1];
      temp[0] = form.getUserType();
      dbUtility.createPreparedStatement(sql);
      details = dbUtility.executeQuery(temp);

    }
    catch (Exception e)
    {

      e.printStackTrace();
    }
    finally {
      dbUtility.closeConnection();
    }
    return details;
  }
  
  public ArrayList getDistrictUsersData(SMSAlertsForm form, String lang) throws Exception {
    ArrayList details = new ArrayList();
    DBUtility dbUtility = null;
    try
    {
      dbUtility = new DBUtility();
      dbUtility.createStatement();
      String sql = "SELECT SMS.S_NO,(SELECT ZM.ZONE_NAME FROM IGRS_ZONE_MASTER ZM WHERE SMS.USER_ZONE=ZM.ZONE_ID AND ZM.ZONE_STATUS='A' ) AS ZONE,(SELECT ZM.H_ZONE_NAME FROM IGRS_ZONE_MASTER ZM WHERE SMS.USER_ZONE=ZM.ZONE_ID AND ZM.ZONE_STATUS='A' ) AS HIZONE,(SELECT DM.DISTRICT_NAME  FROM IGRS_DISTRICT_MASTER DM WHERE SMS.USER_DISTRICT=DM.DISTRICT_ID AND DM.DISTRICT_STATUS='A') AS DISTRICT,(SELECT DM.H_DISTRICT_NAME  FROM IGRS_DISTRICT_MASTER DM WHERE SMS.USER_DISTRICT=DM.DISTRICT_ID AND DM.DISTRICT_STATUS='A') AS HDISTRICT,SMS.USER_ID,SMS.USER_NAME,SMS.USER_DESIGNATION,SMS.USER_MOBILE_NUMBER FROM IGRS_CUSTOM_SMS_MASTER SMS WHERE SMS.USER_STATUS='A' AND SMS.SMS_TYPE=?  order by SMS.S_NO,SMS.USER_ID";
      String[] temp = new String[1];
      temp[0] = form.getUserType();
      dbUtility.createPreparedStatement(sql);
      details = dbUtility.executeQuery(temp);

    }
    catch (Exception e)
    {

      e.printStackTrace();
    }
    finally {
      dbUtility.closeConnection();
    }
    return details;
  }
  
  public boolean createGroup(SMSAlertsForm form, String userId) throws Exception {
		DBUtility dbUtility = null;
		boolean flag=false;
		boolean flag2=false;
		int seqVal = 0;
		String seq="";
		HashMap map =null;
		map=form.getMapGroupMembers();
		Set mapSet = map.entrySet();
		Iterator mapIt = mapSet.iterator();
		try{

		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
			String sql1=SMSAlertsSql.INSERT_GROUP_MEMBERS_DATA;
			System.out.println("INSERT_GROUP_MEMBERS_DATA");
			
			//Get sequence for group Id
			String GRP_SEQ_SQL = "select IGRS_SMS_GRP_ID_SEQ.nextval from dual";
			dbUtility.createStatement();
	        String grpId = dbUtility.executeQry(GRP_SEQ_SQL);
	        
	        String GRP_MEMBER_SEQ_SQL = "select IGRS_SMS_GRP_MEMBERID_SEQ.nextval from dual";
			dbUtility.createStatement();
	        //String grpMemberId = dbUtility.executeQry(GRP_MEMBER_SEQ_SQL);
			
		try {
			while(mapIt.hasNext()){
				Map.Entry mapEntry = (Map.Entry) mapIt.next();
				SMSAlertsDTO keyValue = (SMSAlertsDTO) mapEntry.getValue();
				System.out.println(keyValue.getUserName() + keyValue.getUserMobileNumber());
				System.out.println(keyValue.toString());
				if(keyValue!=null && !keyValue.getUserName().equalsIgnoreCase("")){
					String grpMemberId = dbUtility.executeQry(GRP_MEMBER_SEQ_SQL);
					dbUtility.createPreparedStatement(sql1);
					String temp[] = new String[7];
					temp[0]=form.getGroupName();	
					temp[1]=keyValue.getUserName();
					temp[2]=keyValue.getUserMobileNumber();
					temp[3]=userId;
					temp[4]=userId;
					temp[5]=grpId;
					temp[6]=grpMemberId;
					flag = dbUtility.executeUpdate(temp);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			}

		if(flag)
		{
			dbUtility.commit();
		}

		else
		{
			dbUtility.rollback();
		}
//			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			dbUtility.closeConnection();
		}
		return flag;
	}

	public ArrayList searchEditDetails(String fromDt, String toDt, String userId) throws Exception {
		ArrayList details = new ArrayList();
		DBUtility dbUtility = null;
		try
		{
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			String sql =SMSAlertsSql.SELECT_SEARCH_DTLS;
			String temp[] = new String[3];
			temp[0]=userId;
			temp[1]=fromDt;
			temp[2]=toDt;
			dbUtility.createPreparedStatement(sql);
			details=dbUtility.executeQuery(temp);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			dbUtility.closeConnection();
		}
		return details;
	}

	public ArrayList searchMemberDtls(String grpName, String userId) throws Exception {
		ArrayList details = new ArrayList();
		DBUtility dbUtility = null;
		try
		{
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			String sql =SMSAlertsSql.SEARCH_MEMBER_DTLS;
			String temp[] = new String[1];
			temp[0]=grpName;
			dbUtility.createPreparedStatement(sql);
			details=dbUtility.executeQuery(temp);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			dbUtility.closeConnection();
		}
		return details;
	}

	public boolean verifyGroupExists(String grpName) throws Exception {
		ArrayList details = new ArrayList();
		DBUtility dbUtility = null;
		boolean flag=false;
		try
		{
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			String sql =SMSAlertsSql.VERIFY_GROUP_DTLS;
			String temp[] = new String[1];
			temp[0]=grpName;
			dbUtility.createPreparedStatement(sql);
			details=dbUtility.executeQuery(temp);
			if(details!=null && !details.isEmpty())
				flag=false;
			else
				flag=true;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			dbUtility.closeConnection();
		}
		return flag;

	}

	public boolean deleteGroup(HashMap map, String[] trnsPrtyID) throws Exception {
		DBUtility dbUtility = null;
		boolean flag=false;
		boolean flag2=false;
		int seqVal = 0;
		String seq="";

		try{

		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HashMap map1=map;
		for(int i = 0; i<trnsPrtyID.length; i++){
		
			String sql1=SMSAlertsSql.DELETE_SMS_GROUP;
			System.out.println("DELETE_SMS_GROUP");
		try {
			dbUtility.createPreparedStatement(sql1);
			String temp[] = new String[1];
			
			temp[0]=trnsPrtyID[i];
			flag = dbUtility.executeUpdate(temp);
			
		}catch(Exception e){
			e.printStackTrace();
			}

		}
		if(flag)
		{
			dbUtility.commit();
		}

		else
		{
			dbUtility.rollback();
		}
//			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			dbUtility.closeConnection();
		}
		return flag;
	}

	public boolean deleteGroupMembers(SMSAlertsForm form, String userId,
			String[] memberStr) throws Exception {

		DBUtility dbUtility = null;
		boolean flag=false;
		try{
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		String awr[]=form.getCheckBoxFlag().split(",");
		for(int i = 0; i<memberStr.length; i++){
			String sql1=SMSAlertsSql.DELETE_SMS_GROUP_MEMBERS;
		try {
			
			dbUtility.createPreparedStatement(sql1);
			String temp[] = new String[1];
			
			String checkData[]=awr[i].split("#");
			temp[0]=checkData[0]; //Group Member ID
			flag = dbUtility.executeUpdate(temp);
			
		}catch(Exception e){
			e.printStackTrace();
			}
		}
		if(flag)
		{
			dbUtility.commit();
		}

		else
		{
			dbUtility.rollback();
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			dbUtility.closeConnection();
		}
		return flag;
		
	}

	public boolean updateMembersData(SMSAlertsForm form, String userId,
			String[] memberStr) throws Exception {

		DBUtility dbUtility = null;
		boolean flag=false;
		try{
		try {
			dbUtility = new DBUtility();
			dbUtility.setAutoCommit(false);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		String awr[]=form.getCheckBoxFlag().split(",");
		for(int i = 0; i<memberStr.length; i++){
			String sql1=SMSAlertsSql.UPDATE_MEMBER_DATA;
		try {
			
			dbUtility.createPreparedStatement(sql1);
			String temp[] = new String[3];
			
			String checkData[]=awr[i].split("#");
			//String name = new String(checkData[1].getBytes("ISO-8859-1"), "UTF-8");
			temp[1]=checkData[2]; //Mobile
			temp[2]=checkData[0]; //Sno
			temp[0]=checkData[1]; //Username
				
			flag = dbUtility.executeUpdate(temp);
			
		}catch(Exception e){
			e.printStackTrace();
			}
		}
		if(flag)
		{
			dbUtility.commit();
		}

		else
		{
			dbUtility.rollback();
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			dbUtility.closeConnection();
		}
		return flag;
		
	}

	public ArrayList getDistrictSendGrp(SMSAlertsForm form, String zoneValues, String lang) throws Exception {
		ArrayList details = new ArrayList();
		DBUtility dbUtility = null;
		try
		{
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			String sql="";
			if("en".equalsIgnoreCase(lang))
			{
				sql = " SELECT DISTRICT_ID,DISTRICT_NAME FROM IGRS_DISTRICT_MASTER WHERE DISTRICT_STATUS='A' AND STATE_ID='1' ";
			}
			else if("hi".equalsIgnoreCase(lang))
			{
				sql = " SELECT DISTRICT_ID,H_DISTRICT_NAME FROM IGRS_DISTRICT_MASTER WHERE DISTRICT_STATUS='A' AND STATE_ID='1' ";
			}
			//Add values both for input as district select and zone select
			if(form.getUserTypes().equalsIgnoreCase("state"))
			{
				if(!zoneValues.isEmpty())
					sql = sql.concat("AND ZONE_ID IN " + "("+ zoneValues +")");
			}
			if(form.getUserTypes().equalsIgnoreCase("zone"))
			{
				if(!zoneValues.isEmpty())
					sql = sql.concat("AND ZONE_ID IN " + "("+ zoneValues +")");
			}
			if("en".equalsIgnoreCase(lang))
			{
				sql=sql.concat(" order by DISTRICT_NAME ");
			}
			else if("hi".equalsIgnoreCase(lang))
			{
				sql=sql.concat(" order by H_DISTRICT_NAME ");
			}
			
			//String temp[] = new String[2];
			//dbUtility.createPreparedStatement(sql);
			details=dbUtility.executeQuery(sql);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			dbUtility.closeConnection();
		}
		return details;
	}

	public ArrayList getNonSystemUserList() throws Exception {
		ArrayList details = new ArrayList();
		DBUtility dbUtility = null;
		try
		{
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			String sql =SMSAlertsSql.SELECT_NON_SYSTEM_USER_GRP;
			details=dbUtility.executeQuery(sql);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			dbUtility.closeConnection();
		}
		return details;
	}

	public ArrayList getNSMemberList(SMSAlertsForm form, String userType, String groupList) throws Exception {
		ArrayList details = new ArrayList();
		DBUtility dbUtility = null;
		try
		{
			dbUtility = new DBUtility();
			
			if(userType.equalsIgnoreCase("S")){
				String list1 = form.getHdnDeleteGrpMember();
				String list2 = form.getHdnRoleList();
				String list3 = form.getHdnZoneList();
				String list4 = form.getHdnDistrictList();
				String type = form.getUserTypes();
				String officeId="";
				String officeName="";
				String roleDesc="";
				String sql = "";
				
				sql = " select distinct  URD.USER_ID, URD.MOBILE_NUMBER from IGRS_ROLE_OFFICE_MAPPING T, IGRS_OFFICE_MASTER O, IGRS_RGROUP_ROLE_MAPPING UR, "+
					  " IGRS_USER_ROLE_GROUP_MAPPING RGM, IGRS_DISTRICT_MASTER DM, IGRS_ZONE_MASTER ZM, IGRS_USER_REG_DETAILS URD, IGRS_ROLE_MASTER RR where "+
					  " T.ROLE_ID = RR.ROLE_ID and T.OFFICE_ID = O.OFFICE_ID and UR.ROLE_OFFICE_MAP_ID = T.ROLE_OFFICE_MAP_ID "+
					  " and RGM.ROLE_GROUP_ID = UR.ROLE_GROUP_ID and RGM.USER_ID = URD.USER_ID and URD.USER_STATUS='A' and UR.STATUS='A' "+ 
					  " and O.DISTRICT_ID = DM.DISTRICT_ID and DM.ZONE_ID= ZM.ZONE_ID ";
				
				if(!list2.equalsIgnoreCase("")){
					String listTemp[]=list2.split(",");
					String val[]=new String[3];
					for(int i=0;i<listTemp.length;i++){
						val=listTemp[i].split("#");
						if(listTemp.length==1){
							officeId=officeId.concat("'"+val[0]+"'");
							officeName=officeName.concat("'"+val[1]+"'");
							roleDesc=roleDesc.concat("'"+val[2]+"'");
						}
						else{
							officeId=officeId.concat("'"+val[0]+"'"+",");
							officeName=officeName.concat("'"+val[1]+"'"+",");
							roleDesc=roleDesc.concat("'"+val[2]+"'"+",");
						}
					}
					if(officeId.endsWith(","))
						officeId=officeId.substring(0, officeId.length()-1);
					if(officeName.endsWith(","))
						officeName=officeName.substring(0, officeName.length()-1);
					if(roleDesc.endsWith(","))
						roleDesc=roleDesc.substring(0, roleDesc.length()-1);
					
						sql=sql.concat(" AND o.OFFICE_ID IN "+"("+officeId+")" + " AND RR.ROLE_NAME IN "+"(" + roleDesc +")" +" ");
				}
				
				if(type.equalsIgnoreCase("state")){
					
					if(!list3.equalsIgnoreCase(""))
						sql=sql.concat(" AND ZM.ZONE_ID IN "+"("+list3+")" + " ");
					
					if(!list4.equalsIgnoreCase(""))
						sql=sql.concat(" AND URD.DISTRICT_ID IN "+"("+list4+")" + " ");
					
				}
				
				if(type.equalsIgnoreCase("zone")){
					
					if(!list3.equalsIgnoreCase(""))
						sql=sql.concat(" AND ZM.ZONE_ID IN "+"("+list3+")" + " ");
					
					if(!list4.equalsIgnoreCase(""))
						sql=sql.concat(" AND URD.DISTRICT_ID IN "+"("+list4+")" + " ");
				}
				
				if(type.equalsIgnoreCase("district")){
					
					if(!list4.equalsIgnoreCase(""))
						sql=sql.concat(" AND URD.DISTRICT_ID IN "+"("+list4+")" + " ");
				}
				
				dbUtility.createStatement();
				details = dbUtility.executeQuery(sql);
			}
			
			if(userType.equalsIgnoreCase("NS")){
				String sql ="SELECT GROUP_NAME,MEMBER_NAME, MOBILE_NUM  FROM IGRS_SMS_ALERTS_GROUP_MASTER WHERE GROUP_STATUS='A' AND GROUP_ID IN "+"("+groupList+")";
				dbUtility.createStatement();
				details = dbUtility.executeQuery(sql);
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			dbUtility.closeConnection();
		}
		return details;
	}

	public ArrayList getRoleGroup(SMSAlertsForm form, String lang, String stateId,
			String zoneId, String districtId) throws Exception {
		ArrayList details = new ArrayList();
		DBUtility dbUtility = null;
		try
		{
			dbUtility = new DBUtility();
			String sql="";
			if("en".equalsIgnoreCase(lang)){
				sql =" SELECT distinct   ZM.ZONE_NAME ,  DM.DISTRICT_NAME ,   office_name, O.OFFICE_ID, RR.ROLE_NAME "+
						" FROM IGRS_ROLE_OFFICE_MAPPING t, igrs_office_master o, IGRS_RGROUP_ROLE_MAPPING UR, IGRS_USER_ROLE_GROUP_MAPPING RGM, "+
						" igrs_role_master rr ,igrs_district_master dm, igrs_zone_master zm WHERE t.role_id = rr.role_id AND t.office_id = O.OFFICE_ID "+
						" AND UR.ROLE_OFFICE_MAP_ID = t.ROLE_OFFICE_MAP_ID AND RGM.ROLE_GROUP_ID = UR.ROLE_GROUP_ID AND UR.STATUS='A' AND "+
						" O.DISTRICT_ID = DM.DISTRICT_ID AND DM.ZONE_ID= ZM.ZONE_ID ";
			//dbUtility.createPreparedStatement(sql);
			}
			else if("hi".equalsIgnoreCase(lang))
			{
				sql =" SELECT distinct   ZM.ZONE_NAME ,  DM.DISTRICT_NAME ,  h_office_name, O.OFFICE_ID, RR.ROLE_NAME "+
				" FROM IGRS_ROLE_OFFICE_MAPPING t, igrs_office_master o, IGRS_RGROUP_ROLE_MAPPING UR, IGRS_USER_ROLE_GROUP_MAPPING RGM, "+
				" igrs_role_master rr ,igrs_district_master dm, igrs_zone_master zm WHERE t.role_id = rr.role_id AND t.office_id = O.OFFICE_ID "+
				" AND UR.ROLE_OFFICE_MAP_ID = t.ROLE_OFFICE_MAP_ID AND RGM.ROLE_GROUP_ID = UR.ROLE_GROUP_ID AND UR.STATUS='A' AND "+
				" O.DISTRICT_ID = DM.DISTRICT_ID AND DM.ZONE_ID= ZM.ZONE_ID ";
			}
			
			if(stateId!=null && !stateId.isEmpty() || zoneId!=null && !zoneId.isEmpty()){
				if((form.getHdnZoneList()!=null && !form.getHdnZoneList().isEmpty()) || (form.getDeleteTrnsPrtyZone()!=null && !form.getDeleteTrnsPrtyZone().isEmpty())){
					if(form.getHdnZoneList()!=null && !form.getHdnZoneList().isEmpty())
						sql=sql.concat(" AND ZM.ZONE_ID in "+"("+form.getHdnZoneList()+") ");
					else{
						if(form.getDeleteTrnsPrtyZone()!=null && !form.getDeleteTrnsPrtyZone().isEmpty())
							sql=sql.concat(" AND ZM.ZONE_ID in "+"("+form.getDeleteTrnsPrtyZone()+") ");
					}
				}
				if((form.getHdnDistrictList()!=null && !form.getHdnDistrictList().isEmpty()) || (form.getDeleteTrnsPrtyDist()!=null && !form.getDeleteTrnsPrtyDist().isEmpty())){
					if(form.getHdnDistrictList()!=null && !form.getHdnDistrictList().isEmpty())
						sql=sql.concat(" AND DM.DISTRICT_ID IN "+"("+form.getHdnDistrictList()+") ");
					else{
						if(form.getDeleteTrnsPrtyDist()!=null && !form.getDeleteTrnsPrtyDist().isEmpty())
							sql=sql.concat(" AND DM.DISTRICT_ID IN "+"("+form.getDeleteTrnsPrtyDist()+") ");
					}
				}
				
				//String zoneSql = " AND ZM.ZONE_ID in "+"("+zoneId+") " + "ORDER BY ZM.ZONE_NAME ,  DM.DISTRICT_NAME ,   OFFICE_NAME ";
				//sql=sql.concat(zoneSql);
			}
			if(districtId!=null && !districtId.isEmpty()){
				if((form.getHdnDistrictList()!=null && !form.getHdnDistrictList().isEmpty()) || (form.getDeleteTrnsPrtyDist()!=null && !form.getDeleteTrnsPrtyDist().isEmpty())){
					if(form.getHdnDistrictList()!=null && !form.getHdnDistrictList().isEmpty())
						sql=sql.concat(" AND DM.DISTRICT_ID IN "+"("+form.getHdnDistrictList()+") ");
					else{
						if(form.getDeleteTrnsPrtyDist()!=null && !form.getDeleteTrnsPrtyDist().isEmpty())
							sql=sql.concat(" AND DM.DISTRICT_ID IN "+"("+form.getDeleteTrnsPrtyDist()+") ");
					}
				}
			}
			if("en".equalsIgnoreCase(lang))
				sql=sql.concat(" union select distinct   ZM.ZONE_NAME ,  DM.DISTRICT_NAME ,   OFFICE_NAME, O.OFFICE_ID, RR.ROLE_NAME  from IGRS_ROLE_OFFICE_MAPPING T, " +
							   " IGRS_OFFICE_MASTER O, IGRS_RGROUP_ROLE_MAPPING UR, IGRS_USER_ROLE_GROUP_MAPPING RGM,  IGRS_ROLE_MASTER RR ,IGRS_DISTRICT_MASTER DM, "+
							   " IGRS_ZONE_MASTER ZM where T.ROLE_ID = RR.ROLE_ID and T.OFFICE_ID = O.OFFICE_ID  and UR.ROLE_OFFICE_MAP_ID = T.ROLE_OFFICE_MAP_ID "+ 
							   " and RGM.ROLE_GROUP_ID = UR.ROLE_GROUP_ID and UR.STATUS='A' and  O.DISTRICT_ID = DM.DISTRICT_ID and DM.ZONE_ID= ZM.ZONE_ID "+  
							   " and O.OFFICE_ID='OFC214' ");
			else if("hi".equalsIgnoreCase(lang))
				sql=sql.concat(" union select distinct   ZM.ZONE_NAME ,  DM.DISTRICT_NAME ,   h_office_name, O.OFFICE_ID, RR.ROLE_NAME  from IGRS_ROLE_OFFICE_MAPPING T, " +
								" IGRS_OFFICE_MASTER O, IGRS_RGROUP_ROLE_MAPPING UR, IGRS_USER_ROLE_GROUP_MAPPING RGM,  IGRS_ROLE_MASTER RR ,IGRS_DISTRICT_MASTER DM, "+
								" IGRS_ZONE_MASTER ZM where T.ROLE_ID = RR.ROLE_ID and T.OFFICE_ID = O.OFFICE_ID  and UR.ROLE_OFFICE_MAP_ID = T.ROLE_OFFICE_MAP_ID "+ 
								" and RGM.ROLE_GROUP_ID = UR.ROLE_GROUP_ID and UR.STATUS='A' and  O.DISTRICT_ID = DM.DISTRICT_ID and DM.ZONE_ID= ZM.ZONE_ID "+  
								" and O.OFFICE_ID='OFC214' ");
			
			System.out.println("Query is :-   " + sql);
			dbUtility.createStatement();
			details = dbUtility.executeQuery(sql);
		}
		catch(Exception e)		{
			e.printStackTrace();
		}
		finally {
			dbUtility.closeConnection();
		}
		return details;
	}
  
}
