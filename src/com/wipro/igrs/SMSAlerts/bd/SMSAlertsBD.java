package com.wipro.igrs.SMSAlerts.bd;

import com.wipro.igrs.SMSAlerts.dao.SMSAlertsDao;
import com.wipro.igrs.SMSAlerts.dto.SMSAlertsDTO;
import com.wipro.igrs.SMSAlerts.form.SMSAlertsForm;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

public class SMSAlertsBD
{
  private static Logger logger = Logger.getLogger(SMSAlertsBD.class);
  
  SMSAlertsDao sroDAO = new SMSAlertsDao();
  
  public static ArrayList getSMSZone(String lang) throws Exception
  {
    SMSAlertsDao sroDAO = new SMSAlertsDao();
    ArrayList zone = sroDAO.getSMSZone(lang);
    ArrayList list = new ArrayList();
    
    if (zone != null) {
      for (int i = 0; i < zone.size(); i++) {
        ArrayList lst = (ArrayList)zone.get(i);
        SMSAlertsDTO dto = new SMSAlertsDTO();
        dto.setZoneId((String)lst.get(0));
        dto.setZoneName((String)lst.get(1));
        list.add(dto);
      }
    }
    return list;
  }
  
  public static ArrayList getSMSDistrict(String lang, String zoneId) throws Exception {
    SMSAlertsDao sroDAO = new SMSAlertsDao();
    ArrayList zone = sroDAO.getSMSDistrict(zoneId);
    ArrayList list = new ArrayList();
    
    if (zone != null) {
      for (int i = 0; i < zone.size(); i++) {
        ArrayList lst = (ArrayList)zone.get(i);
        SMSAlertsDTO dto = new SMSAlertsDTO();
        dto.setDistrictId((String)lst.get(0));
        if ("en".equalsIgnoreCase(lang))
        {
          dto.setDistrictName((String)lst.get(1));
        }
        else if ("hi".equalsIgnoreCase(lang))
        {
          dto.setDistrictName((String)lst.get(2));
        }
        list.add(dto);
      }
    }
    return list;
  }
  
  public static ArrayList<SMSAlertsDTO> getDistrictData(String lang) throws Exception {
    SMSAlertsDao sroDAO = new SMSAlertsDao();
    
    ArrayList list = new ArrayList();
    ArrayList districtData = sroDAO.getDistrictData(lang);
    ArrayList smsDistrictData = sroDAO.getsmsDistrictData(lang);
    



    for (int i = 0; i < districtData.size(); i++)
    {
      ArrayList lst = (ArrayList)districtData.get(i);
      
      SMSAlertsDTO dto = new SMSAlertsDTO();
      int j = i + 1;
      String serailNo = String.valueOf(j);
      System.out.println("serailNo" + serailNo);
      dto.setSerialNo(serailNo);
      
      dto.setZoneId((String)lst.get(0));
      if ("en".equalsIgnoreCase(lang))
      {
        dto.setZoneName((String)lst.get(1));
      }
      else if ("hi".equalsIgnoreCase(lang)) {
        dto.setZoneName((String)lst.get(2));
      }
      
      dto.setDistrictId((String)lst.get(3));
      

      if ("en".equalsIgnoreCase(lang))
      {
        dto.setDistrictName((String)lst.get(4));
      }
      else if ("hi".equalsIgnoreCase(lang)) {
        dto.setDistrictName((String)lst.get(5));
      }
      
      dto.setUserId((String)lst.get(6));
      
      dto.setUserName((String)lst.get(7));
      dto.setUserDesignation((String)lst.get(8));
    
      if (smsDistrictData.size() > 0) {
        for (int k = 0; k < smsDistrictData.size(); k++)
        {
          ArrayList sms = (ArrayList)smsDistrictData.get(k);
          if ((sms.get(2).equals(lst.get(6))) && (sms.get(0).equals((String)lst.get(0))) && (sms.get(1).equals((String)lst.get(3)))) {
            dto.setCheck("true");
            dto.setUserMobileNumber((String)sms.get(4));
            break;
          }
          
          dto.setUserMobileNumber((String)lst.get(9));
        }
        
      }
      else
      {
        dto.setUserMobileNumber((String)lst.get(9));
      }
      
      list.add(dto);
    }
    return list;
  }
  
  public static ArrayList getStateData(String lang) throws Exception {
    SMSAlertsDao sroDAO = new SMSAlertsDao();
    ArrayList stateData = sroDAO.getStateData(lang);
    ArrayList list = new ArrayList();
    ArrayList smsStateData = sroDAO.getsmsStateData(lang);
    
    if (stateData != null) {
      for (int i = 0; i < stateData.size(); i++) {
        ArrayList lst = (ArrayList)stateData.get(i);
        SMSAlertsDTO dto = new SMSAlertsDTO();
        
        int a = i + 1;
        String serailsNo = String.valueOf(a);
        dto.setSerialNo(serailsNo);
        dto.setUserId((String)lst.get(0));
        dto.setUserName((String)lst.get(1));
        dto.setUserDesignation((String)lst.get(2));
        

        if (smsStateData.size() > 0) {
          for (int k = 0; k < smsStateData.size(); k++)
          {
            ArrayList sms = (ArrayList)smsStateData.get(k);
            if ((sms.get(0).equals(lst.get(0))) && (sms.get(2) != lst.get(3))) {
              dto.setCheck("true");
              dto.setUserMobileNumber((String)sms.get(2));
              break;
            }
            
            dto.setUserMobileNumber((String)lst.get(3));
          }
          
        }
        else
        {
          dto.setUserMobileNumber((String)lst.get(3));
        }
        
        list.add(dto);
      }
    }
    return list;
  }
  
  public static ArrayList getZoneData(String lang) throws Exception {
    SMSAlertsDao sroDAO = new SMSAlertsDao();
    ArrayList stateData = sroDAO.getZoneData(lang);
    ArrayList smsZoneData = sroDAO.getsmsZoneData(lang);
    ArrayList list = new ArrayList();
    
    if (stateData != null) {
      for (int i = 0; i < stateData.size(); i++) {
        ArrayList lst = (ArrayList)stateData.get(i);
        SMSAlertsDTO dto = new SMSAlertsDTO();
        
        int a = i + 1;
        String serailsNo = String.valueOf(a);
        dto.setSerialNo(serailsNo);
        
        dto.setZoneId((String)lst.get(0));
        
        if ("en".equalsIgnoreCase(lang))
        {
          dto.setZoneName((String)lst.get(1));
        }
        else if ("hi".equalsIgnoreCase(lang)) {
          dto.setZoneName((String)lst.get(2));
        }
        
        dto.setUserId((String)lst.get(3));
        
        dto.setUserName((String)lst.get(4));
        dto.setUserDesignation((String)lst.get(5));
        
        if (smsZoneData.size() > 0) {
          for (int k = 0; k < smsZoneData.size(); k++)
          {
            ArrayList sms = (ArrayList)smsZoneData.get(k);
            
            if ((sms.get(1).equals(lst.get(3))) && (sms.get(0).equals((String)lst.get(0)))) {
              dto.setCheck("true");
              dto.setUserMobileNumber((String)sms.get(3));
              break;
            }
            dto.setUserMobileNumber((String)lst.get(6));
          }
          
        }
        else
        {
          dto.setUserMobileNumber((String)lst.get(6));
        }
        
        list.add(dto);
      }
    }
    return list;
  }
  
  public boolean insertSMSData(SMSAlertsForm form, String userId) throws Exception {
    return sroDAO.insertSMSData(form, userId);
  }
  
  public static ArrayList getStateDataNew(String lang) throws Exception {
    SMSAlertsDao sroDAO = new SMSAlertsDao();
    ArrayList stateData = sroDAO.getStateDataNew(lang);
    ArrayList list = new ArrayList();
    
    if (stateData != null) {
      for (int i = 0; i < stateData.size(); i++) {
        ArrayList lst = (ArrayList)stateData.get(i);
        SMSAlertsDTO dto = new SMSAlertsDTO();
        dto.setStateName("Madhya Pradesh");
        dto.setSerialNo((String)lst.get(0));
        if ("en".equalsIgnoreCase(lang))
        {
          dto.setUserName((String)lst.get(1));
        }
        else if ("hi".equalsIgnoreCase(lang))
        {
          dto.setUserName((String)lst.get(1));
        }
        
        if ("en".equalsIgnoreCase(lang))
        {
          dto.setUserDesignation((String)lst.get(2));
        }
        else if ("hi".equalsIgnoreCase(lang))
        {
          dto.setUserDesignation((String)lst.get(2));
        }
        

        dto.setUserMobileNumber((String)lst.get(3));
        


        list.add(dto);
      }
    }
    return list;
  }
  
  public boolean deleteSMSData(SMSAlertsForm form, String userId, String[] checkBoxSize) throws Exception {
    return sroDAO.deleteSMSData(form, userId, checkBoxSize);
  }
  
  public boolean updateStateSMSData(SMSAlertsForm form, String userId, String[] checkBoxSize) throws Exception {
    return sroDAO.updateStateSMSData(form, userId, checkBoxSize);
  }
  
  public static ArrayList getsmsDistrictData(String lang) throws Exception {
    SMSAlertsDao sroDAO = new SMSAlertsDao();
    ArrayList smsData = sroDAO.getsmsDistrictData(lang);
    ArrayList list = new ArrayList();
    
    if (smsData != null) {
      for (int i = 0; i < smsData.size(); i++)
      {
        ArrayList lst = (ArrayList)smsData.get(i);
        SMSAlertsDTO dto = new SMSAlertsDTO();
        

        if ("en".equalsIgnoreCase(lang))
        {
          dto.setZoneName((String)lst.get(0));
        }
        else if ("hi".equalsIgnoreCase(lang))
        {
          dto.setZoneName((String)lst.get(0));
        }
        
        if ("en".equalsIgnoreCase(lang))
        {
          dto.setDistrictName((String)lst.get(1));
        }
        else if ("hi".equalsIgnoreCase(lang))
        {
          dto.setDistrictName((String)lst.get(1));
        }
        
        dto.setUserId((String)lst.get(2));
        
        if ("en".equalsIgnoreCase(lang))
        {
          dto.setCheckFlag((String)lst.get(3));
        }
        else if ("hi".equalsIgnoreCase(lang))
        {
          dto.setCheckFlag((String)lst.get(3));
        }
        
        list.add(dto);
      }
    }
    
    return list;
  }
  
  public boolean insertCustomSMSData(SMSAlertsForm form, String userId) throws Exception {
    return sroDAO.insertCustomSMSData(form, userId);
  }
  
  public boolean updateSMSData(SMSAlertsForm form, String userId, String[] checkBoxSize)
    throws Exception
  {
    return sroDAO.updateSMSData(form, userId, checkBoxSize);
  }
  
  public boolean updateStateMobileData(SMSAlertsForm form, String userId, String[] checkBoxSize)
    throws Exception
  {
    return sroDAO.updateStateMobileData(form, userId, checkBoxSize);
  }
  
  public boolean updatezoneSMSData(SMSAlertsForm form, String userId, String[] checkBoxSize, String lang) throws Exception
  {
    return sroDAO.updatezoneSMSData(form, userId, checkBoxSize, lang);
  }
  
  public boolean deletezoneSMSData(SMSAlertsForm form, String userId, String[] checkBoxSize, String lang) throws Exception
  {
    return sroDAO.deletezoneSMSData(form, userId, checkBoxSize, lang);
  }
  
  public boolean updatezoneSMSMobileData(SMSAlertsForm form, String userId, String[] checkBoxSize, String lang) throws Exception
  {
    return sroDAO.updatezoneSMSMobileData(form, userId, checkBoxSize, lang);
  }
  
  public boolean updateDistrictSMSData(SMSAlertsForm form, String userId, String[] checkBoxSize, String lang) throws Exception
  {
    return sroDAO.updateDistrictSMSData(form, userId, checkBoxSize, lang);
  }
  
  public boolean deleteDistrictSMSData(SMSAlertsForm form, String userId, String[] checkBoxSize) throws Exception
  {
    return sroDAO.deleteDistrictSMSData(form, userId, checkBoxSize);
  }
  
  public boolean updateDistrictSMSMobileData(SMSAlertsForm form, String userId, String[] checkBoxSize) throws Exception
  {
    return sroDAO.updateDistrictSMSMobileData(form, userId, checkBoxSize);
  }
  
  public ArrayList getCustomSMSData(SMSAlertsForm form, String userId) throws Exception {
    SMSAlertsDao sroDAO = new SMSAlertsDao();
    
    ArrayList list = new ArrayList();
    ArrayList customSMSData = sroDAO.getCustomSMSData(form, userId);
    
    for (int i = 0; i < customSMSData.size(); i++)
    {
      ArrayList lst = (ArrayList)customSMSData.get(i);
      
      SMSAlertsDTO dto = new SMSAlertsDTO();
      int j = i + 1;
      String serailNo = String.valueOf(j);
      
      dto.setSerialNo(serailNo);
      dto.setUserTypes((String)lst.get(0));
      
      dto.setSmsEnglishContent((String)lst.get(1));
      
      dto.setSmsHindiContent((String)lst.get(2));
      list.add(dto);
    }
    return list;
  }
  
  public boolean updateCustomSMSData(SMSAlertsForm form, String userId) throws Exception
  {
    return sroDAO.updateCustomSMSData(form, userId);
  }
  
  public boolean deleteCustomSMSData(SMSAlertsForm form, String userId) throws Exception {
    return sroDAO.deleteCustomSMSData(form, userId);
  }
  
  public ArrayList getSPUsersList() throws Exception {
    SMSAlertsDao sroDAO = new SMSAlertsDao();
    ArrayList zone = sroDAO.getSPUsersList();
    ArrayList list = new ArrayList();
    
    return list;
  }
  
  public static ArrayList getStateUsersData(SMSAlertsForm form, String lang) throws Exception {
    SMSAlertsDao sroDAO = new SMSAlertsDao();
    ArrayList stateUsersData = sroDAO.getStateUsersData(form, lang);
    ArrayList list = new ArrayList();
    ArrayList smsStateData = sroDAO.getsmsStateData(lang);
    
    if (stateUsersData != null) {
      for (int i = 0; i < stateUsersData.size(); i++) {
        ArrayList lst = (ArrayList)stateUsersData.get(i);
        SMSAlertsDTO dto = new SMSAlertsDTO();
        
        int a = i + 1;
        String serailsNo = String.valueOf(a);
        dto.setSerialNo(serailsNo);
        
        dto.setSerialNo((String)lst.get(0));
        

        dto.setUserId((String)lst.get(1));
        
        if ("en".equalsIgnoreCase(lang))
        {
          dto.setUserName((String)lst.get(2));
        }
        else if ("hi".equalsIgnoreCase(lang))
        {
          dto.setUserName((String)lst.get(2));
        }
        

        if (lst.get(3).equals("null")) {
          dto.setUserDesignation("");
        }
        else {
          dto.setUserDesignation((String)lst.get(3));
        }
        
        if (lst.get(4).equals("0")) {
          dto.setUserMobileNumber("");
        }
        else {
          dto.setUserMobileNumber((String)lst.get(4));
        }
    
        list.add(dto);
      }
    }
    return list;
  }
  
  public static ArrayList getZoneUsersData(SMSAlertsForm form, String lang) throws Exception {
    SMSAlertsDao sroDAO = new SMSAlertsDao();
    ArrayList zoneUsersData = sroDAO.getZoneUsersData(form, lang);
    ArrayList list = new ArrayList();
    

    if (zoneUsersData != null) {
      for (int i = 0; i < zoneUsersData.size(); i++) {
        ArrayList lst = (ArrayList)zoneUsersData.get(i);
        SMSAlertsDTO dto = new SMSAlertsDTO();
        
        int a = i + 1;
        String serailsNo = String.valueOf(a);
        dto.setSerialNo(serailsNo);
        
        dto.setSerialNo((String)lst.get(0));
        
        if ("en".equalsIgnoreCase(lang))
        {
          dto.setZoneName((String)lst.get(1));
        }
        else if ("hi".equalsIgnoreCase(lang)) {
          dto.setZoneName((String)lst.get(2));
        }
        

        dto.setUserId((String)lst.get(3));
        
        dto.setUserName((String)lst.get(4));
        

        if (lst.get(5).equals("null")) {
          dto.setUserDesignation("");
        }
        else
        {
          dto.setUserDesignation((String)lst.get(5));
        }
       
        if (lst.get(6).equals("0")) {
          dto.setUserMobileNumber("");
        }
        else {
          dto.setUserMobileNumber((String)lst.get(6));
        }
        
        list.add(dto);
      }
    }
    return list;
  }
  
  public static ArrayList getDistrictUsersData(SMSAlertsForm form, String lang) throws Exception {
    SMSAlertsDao sroDAO = new SMSAlertsDao();
    ArrayList zoneUsersData = sroDAO.getDistrictUsersData(form, lang);
    ArrayList list = new ArrayList();
    
    if (zoneUsersData != null) {
      for (int i = 0; i < zoneUsersData.size(); i++) {
        ArrayList lst = (ArrayList)zoneUsersData.get(i);
        SMSAlertsDTO dto = new SMSAlertsDTO();
        
        int a = i + 1;
        String serailsNo = String.valueOf(a);
        dto.setSerialNo(serailsNo);
        
        dto.setSerialNo((String)lst.get(0));
        
        if ("en".equalsIgnoreCase(lang))
        {
          dto.setZoneName((String)lst.get(1));
        }
        else if ("hi".equalsIgnoreCase(lang)) {
          dto.setZoneName((String)lst.get(2));
        }
        

        if ("en".equalsIgnoreCase(lang))
        {
          dto.setDistrictName((String)lst.get(3));
        }
        else if ("hi".equalsIgnoreCase(lang)) {
          dto.setDistrictName((String)lst.get(4));
        }
        
        dto.setUserId((String)lst.get(5));
        
        dto.setUserName((String)lst.get(6));
        

        if (lst.get(7).equals("null")) {
          dto.setUserDesignation("");
        }
        else {
          dto.setUserDesignation((String)lst.get(7));
        }
        
        if (lst.get(8).equals("0")) {
          dto.setUserMobileNumber("");
        }
        else {
          dto.setUserMobileNumber((String)lst.get(8));
        }
        
        list.add(dto);
      }
    }
    return list;
  }
 
  //Added by Neeti
  
  public boolean createGroup(SMSAlertsForm form, String userId) throws Exception {
		return sroDAO.createGroup(form,userId);
	}

	public ArrayList searchEditDetails(String fromDt, String toDt, String userId) throws Exception {
		SMSAlertsDao sroDAO = new SMSAlertsDao();
		ArrayList grpMembersData = sroDAO.searchEditDetails(fromDt,toDt,userId);
		ArrayList list = new ArrayList();
		if (grpMembersData != null) {
			for (int i = 0; i < grpMembersData.size(); i++) {
				ArrayList lst = (ArrayList) grpMembersData.get(i);
				SMSAlertsDTO dto = new SMSAlertsDTO();
				
				dto.setGroupName((String) lst.get(0));
				dto.setCreatedDate((String) lst.get(1));
				
				list.add(dto);
			}
			}
		return list;
		
	}

	public ArrayList searchMemberDtls(String grpName, String userId) throws Exception {
		SMSAlertsDao sroDAO = new SMSAlertsDao();
		ArrayList grpMembersData = sroDAO.searchMemberDtls(grpName,userId);
		ArrayList list = new ArrayList();
		if (grpMembersData != null) {
			for (int i = 0; i < grpMembersData.size(); i++) {
				ArrayList lst = (ArrayList) grpMembersData.get(i);
				SMSAlertsDTO dto = new SMSAlertsDTO();
				dto.setGroupName((String) lst.get(0));
				dto.setGroupId((String) lst.get(1));
				dto.setGroupMemberId((String) lst.get(2));
				dto.setUserName((String) lst.get(3));
				dto.setUserMobileNumber((String) lst.get(4));
				list.add(dto);
			}
			}
		return list;
	}

	public boolean verifyGroupExists(String grpName) throws Exception {
		return sroDAO.verifyGroupExists(grpName);
	}

	public boolean deleteGroup(HashMap map, String[] trnsPrtyID) throws Exception {
		return sroDAO.deleteGroup(map,trnsPrtyID);
	}

	public boolean deleteGroupMembers(SMSAlertsForm form, String userId,
			String[] memberStr) throws Exception {
		return sroDAO.deleteGroupMembers(form, userId, memberStr);
	}

	public boolean updateMembersData(SMSAlertsForm form, String userId,
			String[] memberStr) throws Exception {
			return sroDAO.updateMembersData(form,userId,memberStr);
	}

	public static ArrayList getDistrictSendGrp(String lang, SMSAlertsForm form, String zoneValues) throws Exception {
		SMSAlertsDao sroDAO = new SMSAlertsDao();
		ArrayList zone = sroDAO.getDistrictSendGrp(form, zoneValues, lang);
		ArrayList list = new ArrayList();

		if (zone != null) {
			for (int i = 0; i < zone.size(); i++) {
				ArrayList lst = (ArrayList) zone.get(i);
				SMSAlertsDTO dto = new SMSAlertsDTO();
				dto.setDistrictId((String) lst.get(0));	
				if("en".equalsIgnoreCase(lang))
				{
				dto.setDistrictName((String) lst.get(1));
				}
				else if("hi".equalsIgnoreCase(lang))
				{
					dto.setDistrictName((String) lst.get(1));
				}
				list.add(dto);
			}
		}
		return list;
	}

	public static ArrayList getNonSystemUserList(String lang) throws Exception {
		SMSAlertsDao sroDAO = new SMSAlertsDao();
		ArrayList nonSysUserList = sroDAO.getNonSystemUserList();
		ArrayList list = new ArrayList();
		if (nonSysUserList != null) {
			for (int i = 0; i < nonSysUserList.size(); i++) {
				ArrayList lst = (ArrayList) nonSysUserList.get(i);
				SMSAlertsDTO dto = new SMSAlertsDTO();
				dto.setGroupId((String) lst.get(0));	
				dto.setGroupName((String) lst.get(1));
				list.add(dto);
			}
		}
		return list;
	}
	 

	public static ArrayList getNSMemberList(String lang, SMSAlertsForm form, String userType , String groupList) throws Exception {
		SMSAlertsDao sroDAO = new SMSAlertsDao();
		ArrayList nsMemberList = sroDAO.getNSMemberList(form, userType, groupList);
		ArrayList list = new ArrayList();
		
		if(userType.equalsIgnoreCase("S")){
			if (nsMemberList != null) {
				for (int i = 0; i < nsMemberList.size(); i++) {
					ArrayList lst = (ArrayList) nsMemberList.get(i);
					SMSAlertsDTO dto = new SMSAlertsDTO();
					dto.setUserId((String) lst.get(0));
					dto.setUserMobileNumber((String) lst.get(1));
					list.add(dto);
				}
			}
		}
		if(userType.equalsIgnoreCase("NS")){
			if (nsMemberList != null) {
				for (int i = 0; i < nsMemberList.size(); i++) {
					ArrayList lst = (ArrayList) nsMemberList.get(i);
					SMSAlertsDTO dto = new SMSAlertsDTO();
					dto.setGroupName((String) lst.get(0));	
					dto.setGroupMemberName((String) lst.get(1));
					dto.setUserMobileNumber((String) lst.get(2));
					list.add(dto);
				}
			}
		}
		
		return list;
	}

	public static ArrayList getRoleGroup(SMSAlertsForm form, String lang,
			String stateId, String zoneId, String districtId) throws Exception {
		
		SMSAlertsDao sroDAO = new SMSAlertsDao();
		ArrayList roleList = sroDAO.getRoleGroup(form,lang,stateId,zoneId,districtId);
		ArrayList list = new ArrayList();
		if (roleList != null) {
			for (int i = 0; i < roleList.size(); i++) {
				ArrayList lst = (ArrayList) roleList.get(i);
				SMSAlertsDTO dto = new SMSAlertsDTO();
				dto.setZoneName((String) lst.get(0));
				dto.setDistrictName((String) lst.get(1));
				dto.setOfficeId((String)lst.get(3));
				dto.setOfficeName((String) lst.get(2));
				dto.setRoleName((String)lst.get(4));
				list.add(dto);
			}
		}
		return list;
	}

  
}
