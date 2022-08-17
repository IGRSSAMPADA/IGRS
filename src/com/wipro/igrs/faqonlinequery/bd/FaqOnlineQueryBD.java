package com.wipro.igrs.faqonlinequery.bd;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.faqonlinequery.dao.FaqOnlineQueryDAO;
import com.wipro.igrs.faqonlinequery.dto.FaqOnlineQueryDTO;
import com.wipro.igrs.noEncumbrance.bo.NoEncumBO;



public class FaqOnlineQueryBD {
private static Logger log = org.apache.log4j.Logger.getLogger(FaqOnlineQueryBD.class);
    
private FaqOnlineQueryDAO faqDAO;
    public FaqOnlineQueryBD() {
    	try {
			faqDAO=new FaqOnlineQueryDAO();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
    }

public ArrayList<FaqOnlineQueryDTO> getFAQDetails(String language)
{
	 ArrayList<FaqOnlineQueryDTO> faqList=new ArrayList<FaqOnlineQueryDTO>();
	 ArrayList list =faqDAO.getFAQDetails(language);
	 if(list!=null && list.size()>0)
	 {
		 for(int i=0;i<list.size();i++)
		 {
			 ArrayList rowList=(ArrayList) list.get(i);
			 FaqOnlineQueryDTO faqDTO=new FaqOnlineQueryDTO();
			 faqDTO.setQuestion((String)rowList.get(0));
			 faqDTO.setAnswer((String)rowList.get(1));
			 faqList.add(faqDTO);
		 }
	 }
	 
	 return faqList;
}
public ArrayList  countryBD(String language)throws Exception
{

	ArrayList  listBD=new ArrayList ();
	try{
		log.info("Inside (countryStackBD) Method");
		listBD=faqDAO.countryDAO(language);
		return listBD;
	}catch (Exception e) {
		log.error(e);
		return null;
	}
}
public ArrayList  stateBD(String _countryIdVar,String language )throws Exception
{

	ArrayList  listBD=new ArrayList ();
	try{
		log.info("Inside (countryStackBD) Method");
		listBD=faqDAO.stateDAO(_countryIdVar,language);
		return listBD;
	}catch (Exception e) {
		log.error(e);
		return null;
	}
}
public ArrayList  districtBD(String _stateIdVar,String language )throws Exception
{

	ArrayList  listBD=new ArrayList ();
	try{
		log.info("Inside (countryStackBD) Method");
		listBD=faqDAO.districtDAO(_stateIdVar,language);
		return listBD;
	}catch (Exception e) {
		log.error(e);
		return null;
	}
}

public String submitQueryDetails(FaqOnlineQueryDTO faqDTO) throws Exception
{
	
	return faqDAO.submitQueryDetails(faqDTO);
}

public ArrayList<FaqOnlineQueryDTO> replyDashBoard(String language)
{
	ArrayList<FaqOnlineQueryDTO> faqList=new ArrayList<FaqOnlineQueryDTO>();
	 ArrayList list =faqDAO.replyDashBoard();
	 if(list!=null && list.size()>0)
	 {
		 for(int i=0;i<list.size();i++)
		 {
			 ArrayList rowList=(ArrayList) list.get(i);
			 FaqOnlineQueryDTO faqDTO=new FaqOnlineQueryDTO();
			 faqDTO.setQueryTxnid((String)rowList.get(0));
			 if("en".equalsIgnoreCase(language))
			 {
				 faqDTO.setStatus("UN-ANSWERED");
			 
			 }
			 else
			 {
			faqDTO.setStatus("अनुत्तरित");
			 } 
			faqDTO.setCreatedDate((String)rowList.get(1));
			 faqList.add(faqDTO);
		 }
	 }
	 
	 return faqList;
}

public ArrayList<FaqOnlineQueryDTO> searchDashBoard(FaqOnlineQueryDTO faqDTO,String language)
{
	ArrayList<FaqOnlineQueryDTO> faqList=new ArrayList<FaqOnlineQueryDTO>();
	 ArrayList list =faqDAO.searchDashBoard(faqDTO);
	 if(list!=null && list.size()>0)
	 {
		 for(int i=0;i<list.size();i++)
		 {
			 ArrayList rowList=(ArrayList) list.get(i);
			 FaqOnlineQueryDTO faqDTO1=new FaqOnlineQueryDTO();
			 faqDTO1.setQueryTxnid((String)rowList.get(0));
			 if("en".equalsIgnoreCase(language))
			 {
			 faqDTO1.setStatus(((String)rowList.get(2)).equalsIgnoreCase("A")?"UN-ANSWERED":"ANSWERED");
			 }
			 else{
				 
		
			 faqDTO1.setStatus(((String)rowList.get(2)).equalsIgnoreCase("A")?"अनुत्तरित":"उत्तरित");
			 }
			 faqDTO1.setCreatedDate((String)rowList.get(1));
			 faqList.add(faqDTO1);
		 }
	 }
	 
	 return faqList;
}
public FaqOnlineQueryDTO replyDetails(String queryId,String language)
{
	FaqOnlineQueryDTO faqDTO=new FaqOnlineQueryDTO();
	ArrayList list=faqDAO.replyDetails(queryId);
	if(list!=null && list.size()>0)
	 {
		 for(int i=0;i<list.size();i++)
		 {
			 ArrayList rowList=(ArrayList) list.get(i);
			 faqDTO.setName((String)rowList.get(0));
			 faqDTO.setAddress((String)rowList.get(1));
			 faqDTO.setEmail((String)rowList.get(2));
			if("en".equalsIgnoreCase(language))
			{
			 faqDTO.setCountryName((String)rowList.get(3));
			 faqDTO.setDistrictName((String)rowList.get(4));
			 faqDTO.setStateName((String)rowList.get(5));
			}
			else if("hi".equalsIgnoreCase(language))
			{
				 faqDTO.setCountryName((String)rowList.get(10));
				 faqDTO.setDistrictName((String)rowList.get(12));
				 faqDTO.setStateName((String)rowList.get(11));
			}
			 faqDTO.setContactNumber((String)rowList.get(6));
			 faqDTO.setMobileNumber((String)rowList.get(7));
			 faqDTO.setQuery((String)rowList.get(8));
			 if(rowList.get(9)==null ||rowList.get(9).toString().equalsIgnoreCase(""))
			 {
				 faqDTO.setReply(""); 
			 }
			 else
			 {
				 faqDTO.setReply((String)rowList.get(9));
			 }
		 }
	 }
	return faqDTO;
}
public boolean updateQueryDetails(FaqOnlineQueryDTO faqDTO) throws Exception
{
	return faqDAO.updateQueryDetails(faqDTO);
}
}
