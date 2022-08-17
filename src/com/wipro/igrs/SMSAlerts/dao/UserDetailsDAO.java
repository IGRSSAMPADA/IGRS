package com.wipro.igrs.SMSAlerts.dao;

import com.wipro.igrs.SMSAlerts.dto.SmsDetails;
import com.wipro.igrs.SMSAlerts.form.SMSAlertsForm;
import com.wipro.igrs.db.DBUtility;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;

public class UserDetailsDAO
{
  private static final Logger log = Logger.getLogger(UserDetailsDAO.class);
  
  public static List<SmsDetails> getStateSMSDetail() throws Exception { DBUtility dbUtility = null;
    ArrayList details = new ArrayList();
    List<SmsDetails> smsData = new LinkedList();
    try {
      dbUtility = new DBUtility();
      List<SmsDetails> count = getEstampDetails();
     
      String sql = "SELECT sms.s_no,sms.sms_type , sms.user_mobile_number,con.h_sms_template FROM igrs_custom_sms_master sms,igrs_template_sms_master con  WHERE sms.sms_type=con.sms_type AND con.sms_status='A' AND sms.user_status='A' AND sms.sms_type='S' and 1=? ORDER BY sms.created_date desc  ";
      
      String[] temp = new String[1];
      temp[0] = "1";
      dbUtility.createPreparedStatement(sql);
      details = dbUtility.executeQuery(temp);
      System.out.println("sql query" + sql);
      

      for (int l = 0; l < details.size(); l++) {
        ArrayList lst = (ArrayList)details.get(l);
        SmsDetails dto = new SmsDetails();
        dto.setSmsTxnId((String)lst.get(0));
        dto.setSms_type((String)lst.get(1));
        dto.setSmsMobileNumber((String)lst.get(2));
        if (count.size() > 0)
        {
          for (int i = 0; i < count.size(); i++) {
            SmsDetails sms = (SmsDetails)count.get(i);
            String smsContent = (String)lst.get(3);
            String currentDate = sms.getCurrent_date();
            String estampCount = sms.getEstamps_count();
            String estampFee = sms.getEstamps_amount();
            String message = smsContent.replace("$sdate", currentDate);
            String message1 = message.replace("$count", estampCount);
            String message2 = message1.replace("$fee", estampFee);
            dto.setSmsMessasgeContent(message2);
          }
        }
        

        smsData.add(dto);
      }
      

    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    finally {
      dbUtility.closeConnection();
    }
    
    return smsData;
  }
  
  public static List<SmsDetails> getZOneSMSDetail()
    throws Exception
  {
    DBUtility dbUtility = null;
    ArrayList details = new ArrayList();
 
    List<SmsDetails> smsData = new LinkedList();
    
    try
    {
      dbUtility = new DBUtility();
      List<SmsDetails> count = getEstampDetails();
      String sql = "SELECT sms.s_no,sms.sms_type , sms.user_mobile_number,con.h_sms_template,con.created_date,sms.user_zone FROM igrs_custom_sms_master sms,igrs_template_sms_master con  WHERE sms.sms_type=con.sms_type AND con.sms_status='A' AND sms.user_status='A' AND sms.sms_type='Z'   and 1=? ORDER BY sms.created_date desc";
      String[] temp = new String[1];
      temp[0] = "1";
      dbUtility.createPreparedStatement(sql);
      details = dbUtility.executeQuery(temp);
      System.out.println("sql query" + sql);
      


      for (int l = 0; l < details.size(); l++) {
        ArrayList lst = (ArrayList)details.get(l);
        SmsDetails dto = new SmsDetails();
        dto.setSmsTxnId((String)lst.get(0));
        dto.setSms_type((String)lst.get(1));
        dto.setSmsMobileNumber((String)lst.get(2));
        String zone = (String)lst.get(5);
        if (zone.length() > 0) {
          ArrayList<SmsDetails> smsZoneData = getZoneEstampDetails(zone);
          for (int j = 0; j < smsZoneData.size(); j++) {
            SmsDetails sms = (SmsDetails)smsZoneData.get(j);
            String smsContent = (String)lst.get(3);
            String currentDate = sms.getCurrent_date();
            String estampCount = sms.getEstamps_count();
            String estampFee = sms.getEstamps_amount();
            String message = smsContent.replace("$date", currentDate);
            String message1 = message.replace("$zcount", estampCount);
            String message2 = message1.replace("$zfee", estampFee);
            dto.setSmsMessasgeContent(message2);
          }
          smsData.add(dto);
        }
        
      }
      
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    finally {
      dbUtility.closeConnection();
    }
    


    return smsData;
  }
  
  public static List<SmsDetails> getDistrictSMSDetail()
    throws Exception
  {
    DBUtility dbUtility = null;
    ArrayList details = new ArrayList();
    
    List<SmsDetails> smsData = new LinkedList();
    
    try
    {
      dbUtility = new DBUtility();
      List<SmsDetails> count = new LinkedList();
      
      String sql = "SELECT sms.s_no,sms.sms_type , sms.user_mobile_number,con.h_sms_template,con.created_date,sms.user_zone,sms.user_district FROM igrs_custom_sms_master sms,igrs_template_sms_master con  WHERE sms.sms_type=con.sms_type AND con.sms_status='A' AND sms.user_status='A' AND sms.sms_type='D'  and 1=? ORDER BY sms.created_date desc";
      
      String[] temp = new String[1];
      temp[0] = "1";
      dbUtility.createPreparedStatement(sql);
      
      details = dbUtility.executeQuery(temp);
      

      for (int l = 0; l < details.size(); l++) {
        ArrayList lst = (ArrayList)details.get(l);
        SmsDetails dto = new SmsDetails();
        dto.setSmsTxnId((String)lst.get(0));
        dto.setSms_type((String)lst.get(1));
        dto.setSmsMobileNumber((String)lst.get(2));
        String zone = (String)lst.get(5);
        
        String district = (String)lst.get(6);
        
        if ((zone.length() > 0) && (district.length() > 0)) {
          ArrayList<SmsDetails> smsZoneData = getDistEstampDetails(zone, district);
          for (int j = 0; j < smsZoneData.size(); j++)
          {
            SmsDetails sms = (SmsDetails)smsZoneData.get(j);
            String smsContent = (String)lst.get(3);
            
            String currentDate = sms.getCurrent_date();
            
            String estampCount = sms.getEstamps_count();
            
            String estampFee = sms.getEstamps_amount();
            
            String message = smsContent.replace("$ddate", currentDate);
            
            String message1 = message.replace("$dcount", estampCount);
            
            String message2 = message1.replace("$dfee", estampFee);
            
            dto.setSmsMessasgeContent(message2);
          }
          smsData.add(dto);

        }
        
      }
      

    }
    catch (Exception e)
    {

      e.printStackTrace();
    }
    finally {
      dbUtility.closeConnection();
    }
    
    return smsData;
  }
  


  private static ArrayList<SmsDetails> getDistEstampDetails(String zone, String district)
    throws Exception
  {
    DBUtility dbUtility = null;
    ArrayList details = new ArrayList();
    
    ArrayList<SmsDetails> estampZoneCount = new ArrayList();
    try {
      dbUtility = new DBUtility();
      DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
      Date date = new Date();
      

      String sql = "SELECT (SELECT COUNT(*) AS COUNT FROM IGRS_REG_TXN_DETLS WHERE REGISTRATION_TXN_STATUS =17 and registration_zone_id=? and registration_dist_id=? AND TO_CHAR(UPDATE_DATE, 'dd/mm/yyyy') = TO_CHAR(SYSDATE, 'dd/mm/yyyy')) AS COUNT,(SELECT SUM(e.estamp_amount) AS a FROM igrs_estamp_detls e, igrs_reg_estamp_map m WHERE e.estamp_txn_id = m.estamp_txn_id AND m.reg_txn_id IN(SELECT reg_txn_id FROM igrs_reg_txn_detls WHERE REGISTRATION_TXN_STATUS = 17 and registration_zone_id=? and registration_dist_id=? AND TO_CHAR(update_date, 'dd/mm/yyyy')=TO_CHAR(sysdate, 'dd/mm/yyyy') ) ) as AMOUNT  FROM dual";
      
      String[] temp = new String[4];
      temp[0] = zone;
      temp[1] = district;
      temp[2] = zone;
      temp[3] = district;
      System.out.println("sql" + sql);
      dbUtility.createPreparedStatement(sql);
      details = dbUtility.executeQuery(temp);
      System.out.println("count size" + details.size());
      for (int l = 0; l < details.size(); l++) {
        ArrayList lst = (ArrayList)details.get(l);
        
        SmsDetails dto = new SmsDetails();
        dto.setCurrent_date(dateFormat.format(date));
        dto.setEstamps_count((String)lst.get(0));
        if ((String)lst.get(1) != null) {
          dto.setEstamps_amount((String)lst.get(1));
        }
        else
        {
          dto.setEstamps_amount("0");
        }
        
        estampZoneCount.add(dto);
      }
      
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    finally {
      dbUtility.closeConnection();
    }
    
    return estampZoneCount;
  }
  
  private static ArrayList<SmsDetails> getZoneEstampDetails(String zone) throws Exception
  {
    DBUtility dbUtility = null;
    ArrayList details = new ArrayList();
    
    ArrayList<SmsDetails> estampZoneCount = new ArrayList();
    try {
      dbUtility = new DBUtility();
      DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
      Date date = new Date();
      

      String sql = "SELECT (SELECT COUNT(*) AS COUNT FROM IGRS_REG_TXN_DETLS WHERE REGISTRATION_TXN_STATUS =17 and registration_zone_id=? AND TO_CHAR(UPDATE_DATE, 'dd/mm/yyyy') = TO_CHAR(SYSDATE, 'dd/mm/yyyy')) AS COUNT,(SELECT SUM(e.estamp_amount) AS a FROM igrs_estamp_detls e, igrs_reg_estamp_map m WHERE e.estamp_txn_id = m.estamp_txn_id AND m.reg_txn_id IN(SELECT reg_txn_id FROM igrs_reg_txn_detls WHERE REGISTRATION_TXN_STATUS = 17 and registration_zone_id=? AND TO_CHAR(update_date, 'dd/mm/yyyy')=TO_CHAR(sysdate, 'dd/mm/yyyy') ) ) as AMOUNT FROM dual";
      

      String[] temp = new String[2];
      temp[0] = zone;
      temp[1] = zone;
      

      dbUtility.createPreparedStatement(sql);
      details = dbUtility.executeQuery(temp);
      
      for (int l = 0; l < details.size(); l++) {
        ArrayList lst = (ArrayList)details.get(l);
        
        SmsDetails dto = new SmsDetails();
        dto.setCurrent_date(dateFormat.format(date));
        dto.setEstamps_count((String)lst.get(0));
        if ((String)lst.get(1) != null) {
          dto.setEstamps_amount((String)lst.get(1));
        }
        else
        {
          dto.setEstamps_amount("0");
        }
        
        estampZoneCount.add(dto);
      }
      
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    finally {
      dbUtility.closeConnection();
    }
    

    return estampZoneCount;
  }
  
  private static List<SmsDetails> getEstampDetails()
    throws Exception
  {
    DBUtility dbUtility = null;
    ArrayList details = new ArrayList();
    List<SmsDetails> estampCount = new LinkedList();
    try {
      dbUtility = new DBUtility();
      DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
      Date date = new Date();
      

      String sql = "SELECT (SELECT  COUNT(*) FROM IGRS_REG_TXN_DETLS WHERE REGISTRATION_TXN_STATUS =17AND TO_CHAR(UPDATE_DATE, 'dd/mm/yyyy') = TO_CHAR(SYSDATE, 'dd/mm/yyyy')) AS count,(SELECT SUM(e.estamp_amount) AS a FROM igrs_estamp_detls e,igrs_reg_estamp_map m WHERE e.estamp_txn_id = m.estamp_txn_id AND m.reg_txn_id  IN(SELECT reg_txn_id FROM igrs_reg_txn_detls WHERE REGISTRATION_TXN_STATUS  = 17 AND 1=?  AND TO_CHAR(update_date, 'dd/mm/yyyy')=TO_CHAR(sysdate, 'dd/mm/yyyy') )) as AMOUNT FROM dual";
      
      String[] temp = new String[1];
      temp[0] = "1";
      


      dbUtility.createPreparedStatement(sql);
      details = dbUtility.executeQuery(temp);
      

      for (int l = 0; l < details.size(); l++) {
        ArrayList lst = (ArrayList)details.get(l);
        
        SmsDetails dto = new SmsDetails();
        dto.setCurrent_date(dateFormat.format(date));
        dto.setEstamps_count((String)lst.get(0));
        if ((String)lst.get(1) != null) {
          dto.setEstamps_amount((String)lst.get(1));
        }
        else
        {
          dto.setEstamps_amount("0");
        }
        
        estampCount.add(dto);
      }
      
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    finally {
      dbUtility.closeConnection();
    }
    
    return estampCount;
  }
  
  public static boolean updateUserPinDetail(String Sno) throws Exception
  {
    System.out.println("serial No" + Sno);
    boolean isRecUpdated = false;
    ArrayList details = new ArrayList();
    DBUtility dbUtility = null;
    System.out.println("sno" + Sno);
    
    String sql = "Update igrs_custom_sms_master set response_code =1, Update_date = systimestamp, update_by='igrs_sms_system' where s_no =?";
    try {
      dbUtility = new DBUtility();
      
      String[] temp = new String[1];
      temp[0] = Sno;
      


      dbUtility.createPreparedStatement(sql);
      isRecUpdated = dbUtility.executeUpdate(temp);
      System.out.println("isRecUpdated" + isRecUpdated);

    }
    catch (Exception e)
    {

      e.printStackTrace();
    }
    finally {
      dbUtility.closeConnection();
    }
    

    return isRecUpdated;
  }
  
  public static List<SmsDetails> getSPUsersList(SMSAlertsForm form, String userId)
    throws Exception
  {
    DBUtility dbUtility = null;
    ArrayList details = new ArrayList();
    
    List<SmsDetails> SPUsers = new LinkedList();
    try
    {
      dbUtility = new DBUtility();
      DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
      Date date = new Date();
      

      String sql = "SELECT URD.FIRST_NAME, URD.MOBILE_NUMBER  FROM IGRS_SP_DETLS SP,IGRS_USER_REG_DETAILS URD WHERE SP.APPLCTN_STATUS=8 AND SP.STATUS='A' AND SP.CREATED_BY=URD.USER_ID AND URD.USER_STATUS='A' and 1=?";
      

      String[] temp = new String[1];
      temp[0] = "1";
      dbUtility.createPreparedStatement(sql);
      details = dbUtility.executeQuery(temp);
      
      for (int i = 0; i < details.size(); i++) {
        ArrayList lst = (ArrayList)details.get(i);
        
        SmsDetails dto = new SmsDetails();
        dto.setFirstName((String)lst.get(0));
        dto.setSmsMobileNumber((String)lst.get(1));
        dto.setSmsMessasgeContent(form.getSmsHindiContent());
        SPUsers.add(dto);
      }
      
    }
    catch (Exception e)
    {

      e.printStackTrace();
    }
    finally {
      dbUtility.closeConnection();
    }
    
    return SPUsers;
  }
  
  public static List<SmsDetails> getStateUsersList(SMSAlertsForm form, String userId)
    throws Exception
  {
    DBUtility dbUtility = null;
    ArrayList details = new ArrayList();
    
    List<SmsDetails> StateUsers = new LinkedList();
    try {
      dbUtility = new DBUtility();
      DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
      Date date = new Date();
      String sql = "select s_no,user_mobile_number from igrs_custom_sms_master where sms_type='S' and user_status='A' and 1=?";
       String[] temp = new String[1];
      temp[0] = "1";
      dbUtility.createPreparedStatement(sql);
      details = dbUtility.executeQuery(temp);
      
      for (int i = 0; i < details.size(); i++) {
        ArrayList lst = (ArrayList)details.get(i);
        
        SmsDetails dto = new SmsDetails();
        dto.setSmsTxnId((String)lst.get(0));
        dto.setSmsMobileNumber((String)lst.get(1));
        System.out.println("form.getSmsHindiContent()" + form.getSmsHindiContent());
        dto.setSmsMessasgeContent(form.getSmsHindiContent());
        StateUsers.add(dto);

      }
      
    }
    catch (Exception e)
    {

      e.printStackTrace();
    }
    finally {
      dbUtility.closeConnection();
    }
    
    return StateUsers;
  }
  

  public static List<SmsDetails> getZoneUsersList(SMSAlertsForm form, String userId)
    throws Exception
  {
    DBUtility dbUtility = null;
    ArrayList details = new ArrayList();
    
    List<SmsDetails> zoneUsers = new LinkedList();
    try {
      dbUtility = new DBUtility();
      DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
      Date date = new Date();
      

      String sql = "select s_no,user_mobile_number from igrs_custom_sms_master where sms_type='Z' and user_status='A' and 1=? ";
      String[] temp = new String[1];
      temp[0] = "1";
      dbUtility.createPreparedStatement(sql);
      details = dbUtility.executeQuery(temp);
      
      for (int i = 0; i < details.size(); i++) {
        ArrayList lst = (ArrayList)details.get(i);
        
        SmsDetails dto = new SmsDetails();
        dto.setSmsTxnId((String)lst.get(0));
        dto.setSmsMobileNumber((String)lst.get(1));
        dto.setSmsMessasgeContent(form.getSmsHindiContent());
        zoneUsers.add(dto);

      }
     
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    finally {
      dbUtility.closeConnection();
    }
    
    return zoneUsers;
  }
  

  public static List<SmsDetails> getDistUsersList(SMSAlertsForm form, String userId)
    throws Exception
  {
    DBUtility dbUtility = null;
    ArrayList details = new ArrayList();
    
    List<SmsDetails> distUsers = new LinkedList();
    try {
      dbUtility = new DBUtility();
      DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
      Date date = new Date();
      

      String sql = "select s_no,user_mobile_number from igrs_custom_sms_master where sms_type='D' and user_status='A' and 1=? ";
      String[] temp = new String[1];
      temp[0] = "1";
      dbUtility.createPreparedStatement(sql);
      details = dbUtility.executeQuery(temp);
      
      for (int i = 0; i < details.size(); i++) {
        ArrayList lst = (ArrayList)details.get(i);
        
        SmsDetails dto = new SmsDetails();
        dto.setSmsTxnId((String)lst.get(0));
        dto.setSmsMobileNumber((String)lst.get(1));
        dto.setSmsMessasgeContent(form.getSmsHindiContent());
        distUsers.add(dto);

      }
     
    }
    catch (Exception e)
    {

      e.printStackTrace();
    }
    finally {
      dbUtility.closeConnection();
    }
   
    return distUsers;
  }
  
  public static void updateSmsDetail(String mobileNos, String smsContent)
    throws Exception
  {
    DBUtility dbUtility = null;
    ArrayList details = new ArrayList();
    
    boolean flag = false;
    try
    {
      dbUtility = new DBUtility();
      DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
      Date date = new Date();
      
      String sql = "insert  into igrs_custom_sms_response (mobile_number,sms_content,created_by,created_date) values(?,?,'system',sysdate) ";
      String[] temp = new String[2];
      
      String[] mob = mobileNos.split(",");
      System.out.println("Mobile Nos" + mob);
      for (int i = 0; i < mob.length; i++) {
        temp[0] = mob[i];
        temp[1] = smsContent;
        dbUtility.createPreparedStatement(sql);
        flag = dbUtility.executeUpdate(temp);
        dbUtility.commit();
      }
     
    }
    catch (Exception e)
    {

      e.printStackTrace();
    }
    finally {
      dbUtility.closeConnection();
    }
  }
}
