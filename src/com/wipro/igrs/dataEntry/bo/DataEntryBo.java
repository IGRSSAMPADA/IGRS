package com.wipro.igrs.dataEntry.bo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;


import com.wipro.igrs.dataEntry.dao.DataEntryDao;
import com.wipro.igrs.dataEntry.dto.DataEntryDto;
import com.wipro.igrs.pot.bo.PotBo;


public class DataEntryBo
{
    
    public static String RID = null;
    
    private  Logger logger = 
	(Logger) Logger.getLogger(DataEntryBo.class);
    public  ArrayList getDeedList() throws IOException
	{
		ArrayList getOfficeNames = new ArrayList();
		HttpSession session = null;
		DataEntryDao dao = null; 
		try
		{
			dao = new DataEntryDao();
			ArrayList list1 = dao.getDeedList();
			if(list1!=null)
			{	
					for(int i=0;i<list1.size();i++)
					{
					    DataEntryDto dto = new DataEntryDto();
						ArrayList list =(ArrayList)list1.get(i);
						dto.setDeedId((String)list.get(0));
						dto.setDeedName((String)list.get(1));
						getOfficeNames.add(dto);
					}
		    }
//			OfficeMasterDTO dto= new OfficeMasterDTO();
//			dto.setList(getOfficeNames);
//			session.setAttribute("officeNames",dto);
		}
		catch(Exception e)
		{
			logger.error("this is Exception in getOfficeNames in BD" + e);
	    }
		return getOfficeNames;
	}
    
    
    public  ArrayList getDistrictNames() throws IOException
	{
		ArrayList district = new ArrayList();
		
		DataEntryDao dao = null; 
		try
		{
			
		    dao = new DataEntryDao();
			ArrayList list1 = dao.getDistrictNames();
			if(list1!=null)
			{	
					for(int i=0;i<list1.size();i++)
					{
					    DataEntryDto dto = new DataEntryDto();
						ArrayList list =(ArrayList)list1.get(i);
						dto.setDistrictId((String)list.get(0));
						dto.setDistrictName((String)list.get(1));
						district.add(dto);
					}
		    }
//			PotDistrictDTO dto= new PotDistrictDTO();
//			dto.setList(getPotDistrict);
//			session.setAttribute("districtNames",dto);
		}
		catch(Exception e)
		{
	        logger.error("this is Exception in getDistrictNames in BD" + e);
	    }
		return district;
	}
    
    public String getRID()
    {
        return RID;
    }
    
    private String potDgenerator() 
	 {
        String id = "RegInit" + new Date().getTime();
        return id;
	 }   
    
    
  public boolean getAdoptionDetails(DataEntryDto dto,String date,String dist) throws Exception
    {
	boolean flag = false;
	
	DataEntryDao dao = null; 
	try
	{
		RID = potDgenerator();
		 dao = new DataEntryDao();
	
		String param2[] = new String[18];
		
				String regTxnId = RID;
				dto.setDataEntryTxnId(regTxnId);
				String volNo = dto.getVolumeNumber();
				String bookNo = dto.getBookNo();
				String regNo = dto.getRegNumber();
				String distist = dist;
				String regDate = date;
				String sroName = dto.getSroName();
				String srName = dto.getSrName();
				String adjud = dto.getAdjudicationDetails();
				String remarks = dto.getRemarks();
				String caveats = dto.getCaveats();
				String exemption = dto.getExemption();
				String totStampDuty = dto.getTotalStampDuty();
				String regFee = dto.getRegFee();
				String otherFee = dto.getOtherFee();
				String pendingFee = dto.getPendingFee();
				String witness1 = dto.getWitnessOne();
				String witness2 = dto.getWitnessTwo();
				
				
				
				param2[0] = regTxnId;
				param2[1] = volNo;
				param2[2] = bookNo;
				param2[3] = regNo;
				param2[4] = dist;
				param2[5] = getDate(regDate);
				param2[6] = sroName;
				param2[7] = srName;
				param2[8] = adjud;
				param2[9] = remarks;
				param2[10] = caveats;
				param2[11] = exemption;
				param2[12] = totStampDuty;
				param2[13] = regFee;
				param2[14] = otherFee;
				param2[15] = pendingFee;
				param2[16] = witness1;
				param2[17] = witness2;
				
				
						
			
			flag = dao.getAdoptionDetails(param2);					
				
				
			/*	String temp = formData.getTemp();
					String temp_[] = temp.split(",");
					for(int i = 0; i <temp_.length; i++)
					{
						//System.out.println("mytemp="+temp_[i]);							
					}
					for(int i = 0; i <temp_.length;)
					{
						String param11[] = new String[3];
						param11[0] = temp_[i];
						i++;
						param11[1] = temp_[i];
						param11[2] = RID;
						i++;
					    flag = bo.potCreateOld(param11);    
					}	*/		 
	
	
	}
	catch(Exception ex)
	{
		ex.printStackTrace();
		
	}
	return flag;
	
    }
  
  
  public String getDate(String _fdate) {

	StringTokenizer stoken = new StringTokenizer(_fdate, "/");
	
	String dd = stoken.nextToken();
	String mm = stoken.nextToken();
	String yy = stoken.nextToken();
	
	if (dd.length() == 2) {
		_fdate = dd + "-" + getMonthName(mm) + "-" + yy;
	}

	return _fdate;

}  
  
  public String getMonthName(String _month) {
	HashMap hm = new HashMap();
	hm.put("01", "JAN");
	hm.put("02", "FEB");
	hm.put("03", "MAR");
	hm.put("04", "APR");
	hm.put("05", "MAY");
	hm.put("06", "JUN");
	hm.put("07", "JUL");
	hm.put("08", "AUG");
	hm.put("09", "SEP");
	hm.put("10", "OCT");
	hm.put("11", "NOV");
	hm.put("12", "DEC");
	return (String) hm.get(_month);
}
   
    
    
    		public ArrayList getPhysicalStamp(String temp)
    		{
    		
    		    ArrayList list = new ArrayList();
    		    DataEntryDto dto = null;
    		    String stamp[] = temp.split(",");
    		    
    		    for(int i=0;i<stamp.length;i++)
    		    {
    		   // System.out.println("stamp are "+stamp[i]);
    		    }
    		   
    			for(int i=0;i<stamp.length;)
    			{
    			    dto = new DataEntryDto();
    			    dto.setStampCode(stamp[i]);
    			    dto.setStamp(stamp[i=i+1]);
    			    dto.setDenomination(stamp[i=i+1]);
    			    i=i+1;
    			list.add(dto);
    			}
    		
    		    return list;
    		    
    		}
    

}
