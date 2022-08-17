package com.wipro.igrs.serviceProvider.bo;

import java.util.ArrayList;

import com.wipro.igrs.estamping.bd.EstampingBD;
import com.wipro.igrs.newPropvaluation.dto.PropertyValuationDTO;
import com.wipro.igrs.serviceProvider.bd.ServiceProviderBD;
import com.wipro.igrs.serviceProvider.dto.ServiceProviderDTO;
import com.wipro.igrs.serviceProvider.form.ServiceProviderForm;



/**
 * 
 * @author Lavi Tripathi
 * 
 */
public class ServiceProviderBO

{
	public boolean insertCriteria(ServiceProviderForm objServiceProviderForm) throws Exception {
		
		ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
			
		boolean addCriteria=objServiceProviderBD.insertCriteria(objServiceProviderForm);

		return addCriteria;
		
	}
	
	public ArrayList getCriterias(String value) throws Exception
	{
		
		
		ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
		ArrayList criteriaList = new ArrayList();
		ArrayList list=objServiceProviderBD.getCriterias(value);
		
		if(list!=null && list.size()>0){
			
			ArrayList rowList;
			
	        try{
			for (int i = 0; i < list.size(); i++) {
				
				ServiceProviderDTO objServiceProviderDTO = new ServiceProviderDTO() ;
				
				rowList = (ArrayList)list.get(i);
				
				objServiceProviderDTO.setCriteriaId(rowList.get(0));
				objServiceProviderDTO.setCriteriaName(rowList.get(1));
				objServiceProviderDTO.setCriteriaIdName(rowList.get(0)+"~"+rowList.get(01));
				
				
				criteriaList.add(objServiceProviderDTO);
		
			}
				
			
				
			}
	        catch(Exception e){
	        	e.printStackTrace();
	        	
	        }
		}
		return criteriaList;
	}
	
	public boolean deleteCriteria(String id) throws Exception {
		
		ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
			
		boolean delCriteria=objServiceProviderBD.deleteCriteria(id);

		return delCriteria;
		
	}
	public boolean editCriteria(ServiceProviderForm objServiceProviderForm) throws Exception {
		
		ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
			
		boolean delCriteria=objServiceProviderBD.editCriteria(objServiceProviderForm);

		return delCriteria;
		
	}
	
	public boolean insertTandC(ServiceProviderForm objServiceProviderForm) throws Exception {
		
		ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
			
		boolean addCriteria=objServiceProviderBD.insertTandC(objServiceProviderForm);

		return addCriteria;
		
	}
	
	public ArrayList getTC(String value) throws Exception
	{
		
		
		ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
		ArrayList tCList = new ArrayList();
		ArrayList list=objServiceProviderBD.getTC(value);
		
		if(list!=null && list.size()>0){
			
			ArrayList rowList;
			
	        try{
			for (int i = 0; i < list.size(); i++) {
				
				ServiceProviderDTO objServiceProviderDTO = new ServiceProviderDTO() ;
				
				rowList = (ArrayList)list.get(i);
				
				objServiceProviderDTO.setTcId(rowList.get(0));
				objServiceProviderDTO.setTcName(rowList.get(1));
				objServiceProviderDTO.setTcIdName(rowList.get(0)+"~"+rowList.get(01));
				
				
				tCList.add(objServiceProviderDTO);
		
			}
				
			
				
			}
	        catch(Exception e){
	        	e.printStackTrace();
	        	
	        }
		}
		return tCList;
	}
	
	public boolean deleteTC(String id) throws Exception {
		
		ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
			
		boolean delCriteria=objServiceProviderBD.deleteTC(id);

		return delCriteria;
		
	}
	
	public boolean editTC(ServiceProviderForm objServiceProviderForm) throws Exception {
		
		ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
			
		boolean delCriteria=objServiceProviderBD.editTC(objServiceProviderForm);

		return delCriteria;
		
	}
	
	public ArrayList getSPDetailsInd(String userID,String lang) throws Exception
	{
		ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
		ArrayList spDetailsInd = new ArrayList();
		ArrayList list = objServiceProviderBD.getSPDetails(userID,lang);
		if (list != null && list.size()>0)
		{
			ArrayList rowList;
			try {
				for (int i=0; i<list.size();i++)
				{
					ServiceProviderDTO objServiceProviderDTO = new ServiceProviderDTO();
					rowList = (ArrayList)list.get(i);
					objServiceProviderDTO.setFirstName(rowList.get(0));
					objServiceProviderDTO.setMiddleName(rowList.get(1));
					objServiceProviderDTO.setLastName(rowList.get(2));
					objServiceProviderDTO.setDob(rowList.get(3));
					objServiceProviderDTO.setAddress(rowList.get(4));
					if(rowList.get(5).equals("M"))
					{
						//modified by shruti
						if ("en".equalsIgnoreCase(lang)) 
						{
							objServiceProviderDTO.setGender("Male");
						}
						else if("hi".equalsIgnoreCase(lang))
						{
							objServiceProviderDTO.setGender("पुरुष");
						}
						//end
					}
					else if (rowList.get(5).equals("F"))
					{
						if ("en".equalsIgnoreCase(lang))
						{
							objServiceProviderDTO.setGender("Female");
						}
						else if("hi".equalsIgnoreCase(lang))
						{
							objServiceProviderDTO.setGender("महिला");
						}
					}
					objServiceProviderDTO.setCategory(rowList.get(6));
					objServiceProviderDTO.setPhNumber(rowList.get(7));
					objServiceProviderDTO.setMobNumber(rowList.get(8));
					objServiceProviderDTO.setEmailId(rowList.get(9));
					objServiceProviderDTO.setIdProofName(rowList.get(10));
					objServiceProviderDTO.setIdProofDetl(rowList.get(11));
					//MODFIIED BY SHRUTI---26 MARCH 2014
					objServiceProviderDTO.setFatherName(rowList.get(12));
					objServiceProviderDTO.setOccupation(rowList.get(13));
					spDetailsInd.add(objServiceProviderDTO);
				}
			}catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return spDetailsInd;
		
	}
	
	public ArrayList getSPDetails(String userID,String lang) throws Exception
	{
		ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
		ArrayList spDetails = new ArrayList();
		ArrayList list = objServiceProviderBD.getSPDetails(userID,lang);
		if (list != null && list.size()>0)
		{
			ArrayList rowList;
			try {
				for (int i=0; i<list.size();i++)
				{
					ServiceProviderDTO objServiceProviderDTO = new ServiceProviderDTO();
					rowList = (ArrayList)list.get(i);
					//objServiceProviderDTO.setFirstName(rowList.get(0));
					//objServiceProviderDTO.setMiddleName(rowList.get(1));
					//objServiceProviderDTO.setLastName(rowList.get(2));
					//objServiceProviderDTO.setDob(rowList.get(3));
					objServiceProviderDTO.setAddress(rowList.get(4));
					//if(rowList.get(5).equals("M"))
					//{
					//objServiceProviderDTO.setGender("Male");
					//}
					//else if (rowList.get(5).equals("F"))
					//{
					//objServiceProviderDTO.setGender("Female");
					//}
					//objServiceProviderDTO.setCategory(rowList.get(6));
					objServiceProviderDTO.setPhNumber(rowList.get(7));
					objServiceProviderDTO.setMobNumber(rowList.get(8));
					objServiceProviderDTO.setEmailId(rowList.get(9));
					objServiceProviderDTO.setIdProofName(rowList.get(10));
					objServiceProviderDTO.setIdProofDetl(rowList.get(11));
					//added by shruti---26 march 2014
					objServiceProviderDTO.setFatherName(rowList.get(12));
					objServiceProviderDTO.setOccupation(rowList.get(13));
					//end
					spDetails.add(objServiceProviderDTO);
				}
			}catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return spDetails;
		
	}
	
	public ArrayList getSpType(String lang) throws Exception
	{
		ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
		ArrayList spTypeLst = new ArrayList();
		ArrayList list = objServiceProviderBD.getSpType(lang);
		
		if (list!=null && list.size()>0)
		{
			try{
				
				ArrayList rowList;
			
				for (int i=0; i<list.size();i++)
				{
					ServiceProviderDTO objServiceProviderDTO = new ServiceProviderDTO();
					rowList = (ArrayList)list.get(i);
					objServiceProviderDTO.setValue(rowList.get(0).toString());
					objServiceProviderDTO.setName(rowList.get(1).toString());
				
					spTypeLst.add(objServiceProviderDTO);
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return spTypeLst;
	}
	
	public ArrayList getDistrict(String lang) throws Exception
	{
		ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
		ArrayList districtList = new ArrayList();
		ArrayList list = objServiceProviderBD.getDistrict(lang);
		
		if (list!=null && list.size()>0)
		{
			try{
				
				ArrayList rowList;
			
				for (int i=0; i<list.size();i++)
				{
					ServiceProviderDTO objServiceProviderDTO = new ServiceProviderDTO();
					rowList = (ArrayList)list.get(i);
					objServiceProviderDTO.setDisttValue(rowList.get(0).toString());
					objServiceProviderDTO.setDisttName(rowList.get(1).toString());
				
					districtList.add(objServiceProviderDTO);
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return districtList;
	}
	
	public ArrayList getTehsil(String disttId,String lang) throws Exception
	{
		ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
		ArrayList tehsilList = new ArrayList();
		ArrayList list = objServiceProviderBD.getTehsil(disttId,lang);
		
		if (list!=null && list.size()>0)
		{
			try{
				
				ArrayList rowList;
			
				for (int i=0; i<list.size();i++)
				{
					ServiceProviderDTO objServiceProviderDTO = new ServiceProviderDTO();
					rowList = (ArrayList)list.get(i);
					objServiceProviderDTO.setTehsilValue(rowList.get(0).toString());
					objServiceProviderDTO.setTehsilName(rowList.get(1).toString());
				
					tehsilList.add(objServiceProviderDTO);
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return tehsilList;
	}
	
	public ArrayList getWardPatwari(String tehsilId,String lang) throws Exception
	{
		ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
		ArrayList wardPatwariList = new ArrayList();
		ArrayList list = objServiceProviderBD.getWardPatwari(tehsilId,lang);
		
		if (list!=null && list.size()>0)
		{
			try{
				
				ArrayList rowList;
			
				for (int i=0; i<list.size();i++)
				{
					ServiceProviderDTO objServiceProviderDTO = new ServiceProviderDTO();
					rowList = (ArrayList)list.get(i);
					objServiceProviderDTO.setWardValue(rowList.get(0).toString());
					objServiceProviderDTO.setWardName(rowList.get(1).toString());
				
					wardPatwariList.add(objServiceProviderDTO);
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return wardPatwariList;
	}
	
	public ArrayList getMohallaVillage(String wardId,String lang) throws Exception
	{
		ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
		ArrayList mohallaList = new ArrayList();
		ArrayList list = objServiceProviderBD.getMohallaVillage(wardId,lang);
		
		if (list!=null && list.size()>0)
		{
			try{
				
				ArrayList rowList;
			
				for (int i=0; i<list.size();i++)
				{
					ServiceProviderDTO objServiceProviderDTO = new ServiceProviderDTO();
					rowList = (ArrayList)list.get(i);
					objServiceProviderDTO.setMohallaValue(rowList.get(0).toString());
					objServiceProviderDTO.setMohallaName(rowList.get(1).toString());
				
					mohallaList.add(objServiceProviderDTO);
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return mohallaList;
	}
	
	public boolean insertSpDetls(ServiceProviderForm objServiceProviderForm) throws Exception
	{
		ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
		
		boolean inserTxn=objServiceProviderBD.insertSpDetls(objServiceProviderForm);

		return inserTxn;
	}
	
	public boolean insertSpDocDetls1(ServiceProviderForm objServiceProviderForm) throws Exception
	{
		ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
		
		boolean inserTxn=objServiceProviderBD.insertSpDocDetls1(objServiceProviderForm);

		return inserTxn;
	}
	
	public ArrayList getDroDetails(String distt,String lang) throws Exception
	{
		ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
		ArrayList droDetailsList = new ArrayList();
		ArrayList list = objServiceProviderBD.getDroDetails(distt,lang);
		if (list != null && list.size()>0)
		{
			ArrayList rowList;
			try {
				for (int i=0; i<list.size();i++)
				{
					ServiceProviderDTO objServiceProviderDTO = new ServiceProviderDTO();
					rowList = (ArrayList)list.get(i);
					if (rowList.get(0)!=null)
					{
						objServiceProviderDTO.setDrAddress(rowList.get(0));
					}
					else
					{
					objServiceProviderDTO.setDrAddress("-");
					}
					if (rowList.get(1)!=null)
					{
					objServiceProviderDTO.setDrPhNo(rowList.get(1));
					}
					else
					{
					objServiceProviderDTO.setDrPhNo("-");
					}
					
					droDetailsList.add(objServiceProviderDTO);
				}
			}catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return droDetailsList;
		
	}
	
	public String getdisttId(String ofcId) throws Exception {
		
		ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
			
		String disttId=objServiceProviderBD.getdisttId(ofcId);

		return disttId;
		
	}
	
	public ArrayList getPendingApps(String distt,String lang) throws Exception
	{
		ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
		ArrayList pendingAppListFinal = new ArrayList();
		ArrayList list=objServiceProviderBD.getPendingApps(distt,lang);
		
		if(list!=null && list.size()>0){
			
			ArrayList rowList;
			
            try{
			for (int i = 0; i < list.size(); i++) {	
				ServiceProviderDTO objServiceProviderDTO = new ServiceProviderDTO();	
				rowList = (ArrayList)list.get(i);	
				objServiceProviderDTO.setRequestNo("0"+rowList.get(0).toString());
				objServiceProviderDTO.setDate(rowList.get(1).toString());
				objServiceProviderDTO.setApplctnStatus(rowList.get(2).toString());
				pendingAppListFinal.add(objServiceProviderDTO);
			}
			}
            catch(Exception e){
            	e.printStackTrace();	
            }
		}
		return pendingAppListFinal;
	}
	public ArrayList getPendingAppsSearch1(String distt, String fromDate, String toDate,String lang) throws Exception
	{
		
		
		ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
		ArrayList pendingAppListFinal = new ArrayList();
		ArrayList list=objServiceProviderBD.getPendingAppsSearch1(distt,fromDate,toDate,lang);
		
		if(list!=null && list.size()>0){
			
			ArrayList rowList;
			
            try{
			for (int i = 0; i < list.size(); i++) {
				
				ServiceProviderDTO objServiceProviderDTO = new ServiceProviderDTO();
				
				rowList = (ArrayList)list.get(i);
				
				objServiceProviderDTO.setRequestNo("0"+rowList.get(0).toString());
				objServiceProviderDTO.setDate(rowList.get(1).toString());
				objServiceProviderDTO.setApplctnStatus(rowList.get(2).toString());
				
					
				
				pendingAppListFinal.add(objServiceProviderDTO);
		
			}
				
			
				
			}
            catch(Exception e){
            	e.printStackTrace();
            	
            }
		}
		return pendingAppListFinal;
	}
	
	public ArrayList getPendingAppsSearch2(String distt, String applctnNumber,String language) throws Exception
	{
		
		
		ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
		ArrayList pendingAppListFinal = new ArrayList();
		ArrayList list=objServiceProviderBD.getPendingAppsSearch2(distt, applctnNumber,language);
		
		if(list!=null && list.size()>0){
			
			ArrayList rowList;
			
            try{
			for (int i = 0; i < list.size(); i++) {
				
				ServiceProviderDTO objServiceProviderDTO = new ServiceProviderDTO();
				
				rowList = (ArrayList)list.get(i);
				
				objServiceProviderDTO.setRequestNo("0"+rowList.get(0).toString());
				objServiceProviderDTO.setDate(rowList.get(1).toString());
				objServiceProviderDTO.setApplctnStatus(rowList.get(2).toString());
				
					
				
				pendingAppListFinal.add(objServiceProviderDTO);
		
			}
				
			
				
			}
            catch(Exception e){
            	e.printStackTrace();
            	
            }
		}
		return pendingAppListFinal;
	}
	
	public ArrayList getPendingAppsSearch3(String distt, String status,String lang) throws Exception
	{
		
		
		ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
		ArrayList pendingAppListFinal = new ArrayList();
		ArrayList list=objServiceProviderBD.getPendingAppsSearch3(distt, status,lang);
		
		if(list!=null && list.size()>0){
			
			ArrayList rowList;
			
            try{
			for (int i = 0; i < list.size(); i++) {
				
				ServiceProviderDTO objServiceProviderDTO = new ServiceProviderDTO();
				
				rowList = (ArrayList)list.get(i);
				
				objServiceProviderDTO.setRequestNo("0"+rowList.get(0).toString());
				objServiceProviderDTO.setDate(rowList.get(1).toString());
				objServiceProviderDTO.setApplctnStatus(rowList.get(2).toString());
				
					
				
				pendingAppListFinal.add(objServiceProviderDTO);
		
			}
				
			
				
			}
            catch(Exception e){
            	e.printStackTrace();
            	
            }
		}
		return pendingAppListFinal;
	}
	
	public ArrayList getPendingAppsSearch4(String distt, String fromDate, String toDate, String applctnNumber,String lang) throws Exception
	{
		
		
		ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
		ArrayList pendingAppListFinal = new ArrayList();
		ArrayList list=objServiceProviderBD.getPendingAppsSearch4(distt, fromDate, toDate,applctnNumber,lang );
		
		if(list!=null && list.size()>0){
			
			ArrayList rowList;
			
            try{
			for (int i = 0; i < list.size(); i++) {
				
				ServiceProviderDTO objServiceProviderDTO = new ServiceProviderDTO();
				
				rowList = (ArrayList)list.get(i);
				
				objServiceProviderDTO.setRequestNo("0"+rowList.get(0).toString());
				objServiceProviderDTO.setDate(rowList.get(1).toString());
				objServiceProviderDTO.setApplctnStatus(rowList.get(2).toString());
				
					
				
				pendingAppListFinal.add(objServiceProviderDTO);
		
			}
				
			
				
			}
            catch(Exception e){
            	e.printStackTrace();
            	
            }
		}
		return pendingAppListFinal;
	}
	
	public ArrayList getPendingAppsSearch5(String distt, String fromDate,String toDate, String status,String lang) throws Exception
	{
		
		
		ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
		ArrayList pendingAppListFinal = new ArrayList();
		ArrayList list=objServiceProviderBD.getPendingAppsSearch5(distt, fromDate, toDate, status,lang);
		
		if(list!=null && list.size()>0){
			
			ArrayList rowList;
			
            try{
			for (int i = 0; i < list.size(); i++) {
				
				ServiceProviderDTO objServiceProviderDTO = new ServiceProviderDTO();
				
				rowList = (ArrayList)list.get(i);
				
				objServiceProviderDTO.setRequestNo("0"+rowList.get(0).toString());
				objServiceProviderDTO.setDate(rowList.get(1).toString());
				objServiceProviderDTO.setApplctnStatus(rowList.get(2).toString());
				
					
				
				pendingAppListFinal.add(objServiceProviderDTO);
		
			}
				
			
				
			}
            catch(Exception e){
            	e.printStackTrace();
            	
            }
		}
		return pendingAppListFinal;
	}
	
	public ArrayList getPendingAppsSearch6(String distt, String applctnNumber, String status,String lang) throws Exception
	{
		
		
		ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
		ArrayList pendingAppListFinal = new ArrayList();
		ArrayList list=objServiceProviderBD.getPendingAppsSearch6(distt, applctnNumber, status,lang);
		
		if(list!=null && list.size()>0){
			
			ArrayList rowList;
			
            try{
			for (int i = 0; i < list.size(); i++) {
				
				ServiceProviderDTO objServiceProviderDTO = new ServiceProviderDTO();
				
				rowList = (ArrayList)list.get(i);
				
				objServiceProviderDTO.setRequestNo("0"+rowList.get(0).toString());
				objServiceProviderDTO.setDate(rowList.get(1).toString());
				objServiceProviderDTO.setApplctnStatus(rowList.get(2).toString());
				
					
				
				pendingAppListFinal.add(objServiceProviderDTO);
		
			}
				
			
				
			}
            catch(Exception e){
            	e.printStackTrace();
            	
            }
		}
		return pendingAppListFinal;
	}
	
	public ArrayList getPendingAppsSearch7(String distt, String fromDate, String toDate,String applctnNumber, String status) throws Exception
	{
		
		
		ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
		ArrayList pendingAppListFinal = new ArrayList();
		ArrayList list=objServiceProviderBD.getPendingAppsSearch7(distt, fromDate, toDate,applctnNumber, status);
		
		if(list!=null && list.size()>0){
			
			ArrayList rowList;
			
            try{
			for (int i = 0; i < list.size(); i++) {
				
				ServiceProviderDTO objServiceProviderDTO = new ServiceProviderDTO();
				
				rowList = (ArrayList)list.get(i);
				
				objServiceProviderDTO.setRequestNo("0"+rowList.get(0).toString());
				objServiceProviderDTO.setDate(rowList.get(1).toString());
				objServiceProviderDTO.setApplctnStatus(rowList.get(2).toString());
				
					
				
				pendingAppListFinal.add(objServiceProviderDTO);
		
			}
				
			
				
			}
            catch(Exception e){
            	e.printStackTrace();
            	
            }
		}
		return pendingAppListFinal;
	}
	
	public ArrayList getPendingAppsSP(String userId,String lang) throws Exception
	{
		
		
		ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
		ArrayList pendingAppListFinal = new ArrayList();
		ArrayList list=objServiceProviderBD.getPendingAppsSP(userId,lang);
		
		if(list!=null && list.size()>0){
			
			ArrayList rowList;
			
            try{
			for (int i = 0; i < list.size(); i++) {
				
				ServiceProviderDTO objServiceProviderDTO = new ServiceProviderDTO();
				
				rowList = (ArrayList)list.get(i);
				
				objServiceProviderDTO.setRequestNo("0"+rowList.get(0).toString());
				objServiceProviderDTO.setDate(rowList.get(1).toString());
				objServiceProviderDTO.setApplctnStatus(rowList.get(2).toString());
				objServiceProviderDTO.setPaidAmount(rowList.get(3).toString());
				objServiceProviderDTO.setBalAmount(rowList.get(4).toString());
				if (rowList.get(5)!=null)
				{
					objServiceProviderDTO.setLastTxnDate(rowList.get(5).toString());
					
				}
				else
				{
					objServiceProviderDTO.setLastTxnDate(rowList.get(1).toString());
				}
				
					
				
				pendingAppListFinal.add(objServiceProviderDTO);
		
			}
				
			
				
			}
            catch(Exception e){
            	e.printStackTrace();
            	
            }
		}
		return pendingAppListFinal;
	}
	
	public ArrayList getApplictnStatus(String lang) throws Exception
	{
		ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
		ArrayList aplctnStatusList = new ArrayList();
		ArrayList list = objServiceProviderBD.getApplictnStatus(lang);
		
		if (list!=null && list.size()>0)
		{
			try{
				
				ArrayList rowList;
			
				for (int i=0; i<list.size();i++)
				{
					ServiceProviderDTO objServiceProviderDTO = new ServiceProviderDTO();
					rowList = (ArrayList)list.get(i);
					objServiceProviderDTO.setStatusValue(rowList.get(0).toString());
					objServiceProviderDTO.setStatusName(rowList.get(1).toString());
				
					aplctnStatusList.add(objServiceProviderDTO);
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return aplctnStatusList;
	}
	
	/**
	 * 
	 * @param objServiceProviderForm
	 * @return
	 * @throws Exception
	 * @description This method is used to get the login details of SP who filed the SP application form for approval by DR
	 */
	
	public ArrayList getSPDetailsInd(ServiceProviderForm objServiceProviderForm,String lang) throws Exception
	{
		ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
		ArrayList spDetailsInd = new ArrayList();
		ArrayList list = objServiceProviderBD.getSPDetails(objServiceProviderForm,lang);
		if (list != null && list.size()>0)
		{
			ArrayList rowList;
			try {
				for (int i=0; i<list.size();i++)
				{
					ServiceProviderDTO objServiceProviderDTO = new ServiceProviderDTO();
					rowList = (ArrayList)list.get(i);
					objServiceProviderDTO.setFirstName(rowList.get(0));
					objServiceProviderDTO.setMiddleName(rowList.get(1));
					objServiceProviderDTO.setLastName(rowList.get(2));
					objServiceProviderDTO.setDob(rowList.get(3));
					//objServiceProviderDTO.setAddress(rowList.get(4));
							if(rowList.get(5).equals("M"))
							{
								if("en".equalsIgnoreCase(lang))
								{
									objServiceProviderDTO.setGender("Male");
								}
								else 
								{
									objServiceProviderDTO.setGender("पुरुष");
								}
							}
							else if (rowList.get(5).equals("F"))
							{
								if("en".equalsIgnoreCase(lang))
								{
									objServiceProviderDTO.setGender("Female");
								}
								else 
								{
									objServiceProviderDTO.setGender("महिला");
								}
							
							}
					
					objServiceProviderDTO.setCategory(rowList.get(6));
					//objServiceProviderDTO.setPhNumber(rowList.get(7));
					//objServiceProviderDTO.setMobNumber(rowList.get(8));
					//objServiceProviderDTO.setEmailId(rowList.get(9));
					//objServiceProviderDTO.setIdProofName(rowList.get(10));
					//objServiceProviderDTO.setIdProofDetl(rowList.get(11));
					objServiceProviderForm.getObjServiceProviderDTO().setSpType((String) rowList.get(12));
					//ADDED BY SHRUTI----15 APRIL 2014
					objServiceProviderDTO.setFatherName((String) rowList.get(13));
					objServiceProviderDTO.setOccupation((String) rowList.get(14));
					//END
					spDetailsInd.add(objServiceProviderDTO);
				}
			}catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return spDetailsInd;
		
	}
	
	public ArrayList getSPDetails(ServiceProviderForm objServiceProviderForm,String lang) throws Exception
	{
		ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
		ArrayList spDetails = new ArrayList();
		ArrayList list = objServiceProviderBD.getSPDetails(objServiceProviderForm,lang);
		if (list != null && list.size()>0)
		{
			ArrayList rowList;
			try {
				for (int i=0; i<list.size();i++)
				{
					ServiceProviderDTO objServiceProviderDTO = new ServiceProviderDTO();
					rowList = (ArrayList)list.get(i);
					//objServiceProviderDTO.setFirstName(rowList.get(0));
					//objServiceProviderDTO.setMiddleName(rowList.get(1));
					//objServiceProviderDTO.setLastName(rowList.get(2));
					//objServiceProviderDTO.setDob(rowList.get(3));
					objServiceProviderDTO.setAddress(rowList.get(4));
					//if(rowList.get(5).equals("M"))
					//{
					//objServiceProviderDTO.setGender("Male");
					//}
					//else if (rowList.get(5).equals("F"))
					//{
					//objServiceProviderDTO.setGender("Female");
					//}
					//objServiceProviderDTO.setCategory(rowList.get(6));
					objServiceProviderDTO.setPhNumber(rowList.get(7));
					objServiceProviderDTO.setMobNumber(rowList.get(8));
					objServiceProviderDTO.setEmailId(rowList.get(9));
					objServiceProviderDTO.setIdProofName(rowList.get(10));
					objServiceProviderDTO.setIdProofDetl(rowList.get(11));
					//modified by shruti---22 april 2014
					objServiceProviderDTO.setFatherName(rowList.get(13));
					objServiceProviderDTO.setOccupation(rowList.get(14));
					spDetails.add(objServiceProviderDTO);
				}
			}catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return spDetails;
		
	}
	
	public String getspTypeId(String requestNumber) throws Exception {
		
		ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
			
		String spTypeId=objServiceProviderBD.getspTypeId(requestNumber);

		return spTypeId;
		
	}
	
	/**
	 * 
	 * @param objServiceProviderForm
	 * @return
	 * @throws Exception
	 * @description This method is used to get the details of SP who filed the SP application form for approval by DR
	 */
	
	public ArrayList getspDetls(ServiceProviderForm objServiceProviderForm,String lang) throws Exception
	{
		ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
		ArrayList details = new ArrayList();
		ArrayList list = objServiceProviderBD.getspDetls(objServiceProviderForm,lang);
		if (list != null && list.size()>0)
		{
			ArrayList rowList;
			try {
				for (int i=0; i<list.size();i++)
				{
					ServiceProviderDTO objServiceProviderDTO = new ServiceProviderDTO();
					rowList = (ArrayList)list.get(i);
					//objServiceProviderDTO.setBankName(rowList.get(0));
					//objServiceProviderDTO.setAuthPersName(rowList.get(1));
					//objServiceProviderDTO.setDesignation(rowList.get(2));
					//objServiceProviderDTO.setAddressSp(rowList.get(3));
					//objServiceProviderDTO.setDistrict((String) rowList.get(4));
					
					//code uncommented by shruti---- commented by lavi
					objServiceProviderDTO.setTehsil((String)rowList.get(5));
					objServiceProviderDTO.setWard((String)rowList.get(6));
					objServiceProviderDTO.setMohalla((String)rowList.get(7));
					objServiceProviderDTO.setEducation(rowList.get(8));
					if("hi".equalsIgnoreCase(lang))
					{
							if("English".equalsIgnoreCase(rowList.get(9).toString()))
							{
								objServiceProviderDTO.setLangKnown("अंग्रेज़ी");	
							}
							else if("Hindi".equalsIgnoreCase(rowList.get(9).toString()))
							{
								objServiceProviderDTO.setLangKnown("हिंदी");	
							}
							else
							{
								objServiceProviderDTO.setLangKnown("अंग्रेजी तथा हिंदी");	
							}
					}
					else
					{
					objServiceProviderDTO.setLangKnown(rowList.get(9));	
					}
					//objServiceProviderDTO.setRadioSelectExp(rowList.get(10));
					//objServiceProviderDTO.setRadioSelectCh(rowList.get(11));
					//objServiceProviderDTO.setAmount(rowList.get(12));
					objServiceProviderDTO.setApplicantStatus(rowList.get(13));
					//added by shruti---4 th april 2014
					objServiceProviderDTO.setPanCard(rowList.get(14));
					objServiceProviderDTO.setForm60(rowList.get(15));
					//added by shruti---4 april 2014
					  if(objServiceProviderDTO.getPanCard()!=null)
					  {
						  objServiceProviderForm.setPanCardFlag("Y");
						  objServiceProviderForm.getObjServiceProviderDTO().setPancardFlag("Y");
						  objServiceProviderForm.setForm60Flag("N");
						  objServiceProviderForm.getObjServiceProviderDTO().setPanCard(objServiceProviderDTO.getPanCard());
					  }
					  else if(objServiceProviderDTO.getForm60()!=null)
					  {
						  objServiceProviderForm.setPanCardFlag("N");
						  objServiceProviderForm.getObjServiceProviderDTO().setPancardFlag("N");
						  objServiceProviderForm.getObjServiceProviderDTO().setForm60Flag("Y");
						  objServiceProviderForm.setForm60Flag("Y");
						  objServiceProviderForm.getObjServiceProviderDTO().setForm60(objServiceProviderDTO.getForm60());
					  }
					  //end
					//end
					details.add(objServiceProviderDTO);
				}
			}catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return details;
		
	}
	
	/**
	 * 
	 * @param objServiceProviderForm
	 * @return
	 * @throws Exception
	 * @description This method is used to get the details of SP who filed the SP application form for approval by DR
	 */
	
	public ArrayList getspDetlsBank(ServiceProviderForm objServiceProviderForm,String lang) throws Exception
	{
		ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
		ArrayList details = new ArrayList();
		ArrayList list = objServiceProviderBD.getspDetls(objServiceProviderForm,lang);
		if (list != null && list.size()>0)
		{
			ArrayList rowList;
			try {
				for (int i=0; i<list.size();i++)
				{
					ServiceProviderDTO objServiceProviderDTO = new ServiceProviderDTO();
					rowList = (ArrayList)list.get(i);
					objServiceProviderDTO.setBankName(rowList.get(0));
					objServiceProviderDTO.setAuthPersName(rowList.get(1));
					objServiceProviderDTO.setDesignation(rowList.get(2));
					//objServiceProviderDTO.setAddressSp(rowList.get(3));
					//objServiceProviderDTO.setDistrict((String) rowList.get(4));
					//objServiceProviderDTO.setTehsil((String)rowList.get(5));
					//objServiceProviderDTO.setWard((String)rowList.get(6));
					//objServiceProviderDTO.setMohalla((String)rowList.get(7));
					//objServiceProviderDTO.setEducation(rowList.get(8));
					//objServiceProviderDTO.setLangKnown(rowList.get(9));
					//objServiceProviderDTO.setRadioSelectExp(rowList.get(10));
					//objServiceProviderDTO.setRadioSelectCh(rowList.get(11));
					//objServiceProviderDTO.setAmount(rowList.get(12));
					objServiceProviderForm.getObjServiceProviderDTO().setApplicantStatus(rowList.get(13));
					
					details.add(objServiceProviderDTO);
				}
			}catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return details;
		
	}
	
	/**
	 * 
	 * @param objServiceProviderForm
	 * @return
	 * @throws Exception
	 * @description This method is used to get the details of SP who filed the SP application form for approval by DR
	 */
	
	public ArrayList getspDetlsAll(ServiceProviderForm objServiceProviderForm,String lang) throws Exception
	{
		ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
		ArrayList details = new ArrayList();
		ArrayList list = objServiceProviderBD.getspDetls(objServiceProviderForm,lang);
		if (list != null && list.size()>0)
		{
			ArrayList rowList;
			try {
				for (int i=0; i<list.size();i++)
				{
					ServiceProviderDTO objServiceProviderDTO = new ServiceProviderDTO();
					rowList = (ArrayList)list.get(i);
					//objServiceProviderDTO.setBankName(rowList.get(0));
					//objServiceProviderDTO.setAuthPersName(rowList.get(1));
					//objServiceProviderDTO.setDesignation(rowList.get(2));
					objServiceProviderDTO.setAddressSp(rowList.get(3));
					objServiceProviderDTO.setDistrict((String) rowList.get(4));
					objServiceProviderDTO.setTehsil((String)rowList.get(5));
					objServiceProviderDTO.setWard((String)rowList.get(6));
					objServiceProviderDTO.setMohalla((String)rowList.get(7));
					//objServiceProviderDTO.setEducation(rowList.get(8));
					//objServiceProviderDTO.setLangKnown(rowList.get(9));
					if (rowList.get(10).toString().equalsIgnoreCase("Y"))
					{
						if (rowList.get(13).toString().equalsIgnoreCase("4"))
						{
							objServiceProviderForm.getObjServiceProviderDTO().setRadioSelectExp("Y");
						}
						else
						{
							if("en".equalsIgnoreCase(lang))
							{
						    objServiceProviderDTO.setRadioSelectExp("Yes");
							}
							else
							{
							objServiceProviderDTO.setRadioSelectExp("हाँ");
							}
						}
					}
					else if (rowList.get(10).toString().equalsIgnoreCase("N"))
					{
						if (rowList.get(13).toString().equalsIgnoreCase("4"))
						{
							objServiceProviderForm.getObjServiceProviderDTO().setRadioSelectExp("N");
						}
						else
						{
							if("en".equalsIgnoreCase(lang))
							{
								objServiceProviderDTO.setRadioSelectExp("No");
							}
							else
							{
							objServiceProviderDTO.setRadioSelectExp("नहीं");
							}
								
						
						}
						
					}
					if (rowList.get(11).toString().equalsIgnoreCase("Y"))
					{
						if (rowList.get(13).toString().equalsIgnoreCase("4"))
						{
							objServiceProviderForm.getObjServiceProviderDTO().setRadioSelectCh("Y");
						}
						else
						{
							if("en".equalsIgnoreCase(lang))
							{
								objServiceProviderDTO.setRadioSelectCh("Yes");
							}
							else
							{
								objServiceProviderDTO.setRadioSelectCh("हाँ");
							}
						}
					
					}
					else if (rowList.get(11).toString().equalsIgnoreCase("N"))
					{
						if (rowList.get(13).toString().equalsIgnoreCase("4"))
						{
							objServiceProviderForm.getObjServiceProviderDTO().setRadioSelectCh("N");
						}
						else
						{
							if("en".equalsIgnoreCase(lang))
							{
								objServiceProviderDTO.setRadioSelectCh("No");
							}
							else
							{
								objServiceProviderDTO.setRadioSelectCh("नहीं");
							}
						}
					}
					objServiceProviderDTO.setAmount(rowList.get(12));
					objServiceProviderForm.getObjServiceProviderDTO().setApplicantStatus(rowList.get(13));
					//added by shruti--4 th april 2014
					objServiceProviderDTO.setPanCard(rowList.get(14));
					objServiceProviderDTO.setForm60(rowList.get(15));
					//added by shruti---4 april 2014
					  if(objServiceProviderDTO.getPanCard()!=null)
					  {
						  objServiceProviderForm.setPanCardFlag("Y");
						  objServiceProviderForm.getObjServiceProviderDTO().setPancardFlag("Y");
						  objServiceProviderForm.setForm60Flag("N");
						  objServiceProviderForm.getObjServiceProviderDTO().setPanCard(objServiceProviderDTO.getPanCard());
					  }
					  else if(objServiceProviderDTO.getForm60()!=null)
					  {
						  objServiceProviderForm.setPanCardFlag("N");
						  objServiceProviderForm.getObjServiceProviderDTO().setPancardFlag("N");
						  objServiceProviderForm.getObjServiceProviderDTO().setForm60Flag("Y");
						  objServiceProviderForm.setForm60Flag("Y");
						  objServiceProviderForm.getObjServiceProviderDTO().setForm60(objServiceProviderDTO.getForm60());
					  }
					  //end
					  //added by shruti---29 july 2014
					  objServiceProviderDTO.setAreaId((String)rowList.get(16));
					  objServiceProviderDTO.setSubAreaId((String)rowList.get(17));
					  objServiceProviderDTO.setDurationFrom((String)rowList.get(18));
					  objServiceProviderDTO.setDurationTo((String)rowList.get(19));
					  details.add(objServiceProviderDTO);
				}
			}catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return details;
		
	}
	
	public ArrayList getspDocDetls(String requestNumber) throws Exception
	{
		ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
		ArrayList details = new ArrayList();
		ArrayList list = objServiceProviderBD.getspDocDetls(requestNumber);
		if (list != null && list.size()>0)
		{
			ArrayList rowList;
			try {
				for (int i=0; i<list.size();i++)
				{
					ServiceProviderDTO objServiceProviderDTO = new ServiceProviderDTO();
					rowList = (ArrayList)list.get(i);
					
					objServiceProviderDTO.setDocName((String)rowList.get(0));
					objServiceProviderDTO.setDocPath((String)rowList.get(1));
					
					
					details.add(objServiceProviderDTO);
				}
			}catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return details;
		
	}
	
	public ServiceProviderForm getspDocDetlsDR(ServiceProviderForm objServiceProviderForm) throws Exception
	{
		ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
		ArrayList list = objServiceProviderBD.getspDocDetlsDR(objServiceProviderForm);
		if (list != null && list.size()>0)
		{
			ArrayList rowList;
			try {
				for (int i=0; i<list.size();i++)
				{
					ServiceProviderDTO objServiceProviderDTO = new ServiceProviderDTO();
					rowList = (ArrayList)list.get(i);
					
					if (rowList.get(0).toString().startsWith("Affidavit_Police_Case"))
					{					
						objServiceProviderForm.getObjServiceProviderDTO().setPoliceCaseCertiDoc((String)rowList.get(0));
						objServiceProviderForm.getObjServiceProviderDTO().setPoliceCaseCertiPath((String)rowList.get(1)+"/"+(String)rowList.get(0));
					}
					else if (rowList.get(0).toString().startsWith("Age_Proof"))
					{
						objServiceProviderForm.getObjServiceProviderDTO().setAgeProofDoc((String)rowList.get(0));
						objServiceProviderForm.getObjServiceProviderDTO().setAgeProofPath((String)rowList.get(1)+"/"+(String)rowList.get(0));
					}
					else if (rowList.get(0).toString().startsWith("Business_Premises"))
					{
						objServiceProviderForm.getObjServiceProviderDTO().setPossessionBusinessDocDoc((String)rowList.get(0));
						objServiceProviderForm.getObjServiceProviderDTO().setPossessionBusinessDocPath((String)rowList.get(1)+"/"+(String)rowList.get(0));
					}
					else if (rowList.get(0).toString().startsWith("Character_Ceertificate_Gazette"))
					{
						objServiceProviderForm.getObjServiceProviderDTO().setCharacterCertiGazDoc((String)rowList.get(0));
						objServiceProviderForm.getObjServiceProviderDTO().setCharacterCertiGazPath((String)rowList.get(1)+"/"+(String)rowList.get(0));
					}
					//MODIFIED BY SHRUTI---11 APRIL 2014 FOR UPLOAD ERROR
					//else if (rowList.get(0).toString().startsWith("Character_Certificate"))
					else if (rowList.get(0).toString().startsWith("Character_Certificate"))
					{
						objServiceProviderForm.getObjServiceProviderDTO().setCharacterCertiDoc((String)rowList.get(0));
						objServiceProviderForm.getObjServiceProviderDTO().setCharacterCertiPath((String)rowList.get(1)+"/"+(String)rowList.get(0));
					}
					else if (rowList.get(0).toString().startsWith("Criminal_Activities_Affidavit"))
					{
						objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalDoc((String)rowList.get(0));
						objServiceProviderForm.getObjServiceProviderDTO().setAffidavitCriminalPath((String)rowList.get(1)+"/"+(String)rowList.get(0));
					}
					else if (rowList.get(0).toString().startsWith("DOB_Certificate"))
					{
						objServiceProviderForm.getObjServiceProviderDTO().setDobCertiDoc((String)rowList.get(0));
						objServiceProviderForm.getObjServiceProviderDTO().setDobCertiPath((String)rowList.get(1)+"/"+(String)rowList.get(0));
					}
					else if (rowList.get(0).toString().startsWith("Hardware_Available"))
					{
						objServiceProviderForm.getObjServiceProviderDTO().setAffidavitHardwareDoc((String)rowList.get(0));
						objServiceProviderForm.getObjServiceProviderDTO().setAffidavitHardwarePath((String)rowList.get(1)+"/"+(String)rowList.get(0));
					}
					else if (rowList.get(0).toString().startsWith("Higher_Secondary_Certificate"))
					{
						objServiceProviderForm.getObjServiceProviderDTO().setHigherSecCertiDoc((String)rowList.get(0));
						objServiceProviderForm.getObjServiceProviderDTO().setHigherSecCertiPath((String)rowList.get(1)+"/"+(String)rowList.get(0));
					}
					else if (rowList.get(0).toString().startsWith("Photo"))
					{
						objServiceProviderForm.getObjServiceProviderDTO().setPhotoImgDoc((String)rowList.get(0));
						objServiceProviderForm.getObjServiceProviderDTO().setPhotoImgPath((String)rowList.get(1)+"/"+(String)rowList.get(0));
					}
					else if (rowList.get(0).toString().startsWith("Rent_Details"))
					{
						objServiceProviderForm.getObjServiceProviderDTO().setRentDetailDoc((String)rowList.get(0));
						objServiceProviderForm.getObjServiceProviderDTO().setRentDetailPath((String)rowList.get(1)+"/"+(String)rowList.get(0));
					}
					else if (rowList.get(0).toString().startsWith("Solvency_Certificate"))
					{
						objServiceProviderForm.getObjServiceProviderDTO().setSolvencyCertiDoc((String)rowList.get(0));
						objServiceProviderForm.getObjServiceProviderDTO().setSolvencyCertiPath((String)rowList.get(1)+"/"+(String)rowList.get(0));
					}
					else if (rowList.get(0).toString().startsWith("Education_Qualification"))
					{
						objServiceProviderForm.getObjServiceProviderDTO().setHigherEduCertiDoc((String)rowList.get(0));
						objServiceProviderForm.getObjServiceProviderDTO().setHigherEduCertiPath((String)rowList.get(1)+"/"+(String)rowList.get(0));
					}
					//added by shruti---2 april 2014
					else if (rowList.get(0).toString().startsWith("PanCard"))
					{
						objServiceProviderForm.getObjServiceProviderDTO().setPancardDoc((String)rowList.get(0));
						objServiceProviderForm.getObjServiceProviderDTO().setPancardPath((String)rowList.get(1)+"/"+(String)rowList.get(0));
						objServiceProviderForm.getObjServiceProviderDTO().setPancardupldchk("Y");
					}
					else if (rowList.get(0).toString().startsWith("Form60_61"))
					{
						objServiceProviderForm.getObjServiceProviderDTO().setForm60Doc((String)rowList.get(0));
						objServiceProviderForm.getObjServiceProviderDTO().setForm60Path((String)rowList.get(1)+"/"+(String)rowList.get(0));
						objServiceProviderForm.getObjServiceProviderDTO().setForm60upldchk("Y");
					}
					//end
					
				}
			}catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return objServiceProviderForm;
		
	}
	
     public String getFees() throws Exception {
		
		ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
			
		String disttId=objServiceProviderBD.getFees();

		return disttId;
		
	}
     
     public boolean insertDRApprovalComments(ServiceProviderForm objServiceProviderForm) throws Exception
 	{
 		ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
 		
 		boolean inserTxn=objServiceProviderBD.insertDRApprovalComments(objServiceProviderForm);

 		return inserTxn;
 	}
     
     public boolean updateSpDocDetls(ServiceProviderForm objServiceProviderForm) throws Exception
 	{
 		ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
 		
 		boolean inserTxn=objServiceProviderBD.updateSpDocDetls(objServiceProviderForm);

 		return inserTxn;
 	}
     
     public boolean insertDRCallForPVComments(ServiceProviderForm objServiceProviderForm) throws Exception
  	{
  		ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
  		
  		boolean inserTxn=objServiceProviderBD.insertDRCallForPVComments(objServiceProviderForm);

  		return inserTxn;
  	}
     public boolean insertCommentsCancel(ServiceProviderForm objServiceProviderForm) throws Exception
   	{
   		ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
   		
   		boolean inserTxn=objServiceProviderBD.insertDRCommentsCancel(objServiceProviderForm);

   		return inserTxn;
   	}
     
     public boolean insertDRCallForAddInfoComments(ServiceProviderForm objServiceProviderForm) throws Exception
   	{
   		ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
   		
   		boolean inserTxn=objServiceProviderBD.insertDRCallForAddInfoComments(objServiceProviderForm);

   		return inserTxn;
   	}
     
     public boolean insertDrRejectionComments(ServiceProviderForm objServiceProviderForm) throws Exception
   	{
   		ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
   		
   		boolean inserTxn=objServiceProviderBD.insertDrRejectionComments(objServiceProviderForm);

   		return inserTxn;
   	}
     
     public ArrayList getPendingAppsDIG(String ofcId,String lang) throws Exception
 	{
 		
 		
 		ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
 		ArrayList pendingAppListDIG = new ArrayList();
 		ArrayList list=objServiceProviderBD.getPendingAppsDIG(ofcId,lang);
 		
 		if(list!=null && list.size()>0){
 			
 			ArrayList rowList;
 			
             try{
 			for (int i = 0; i < list.size(); i++) {
 				
 				ServiceProviderDTO objServiceProviderDTO = new ServiceProviderDTO();
 				
 				rowList = (ArrayList)list.get(i);
 				
 				objServiceProviderDTO.setRequestNo("0"+rowList.get(0).toString());
 				objServiceProviderDTO.setDate(rowList.get(1).toString());
 				objServiceProviderDTO.setApplctnStatus(rowList.get(2).toString());
 				
 					
 				
 				pendingAppListDIG.add(objServiceProviderDTO);
 		
 			}
 				
 			
 				
 			}
             catch(Exception e){
             	e.printStackTrace();
             	
             }
 		}
 		return pendingAppListDIG;
 	}
     
     public ArrayList getPendingAppsSearchDIG1(String ofcId, String fromDate, String toDate) throws Exception
 	{
 		
 		
 		ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
 		ArrayList pendingAppListFinal = new ArrayList();
 		ArrayList list=objServiceProviderBD.getPendingAppsSearchDIG1(ofcId,fromDate,toDate);
 		
 		if(list!=null && list.size()>0){
 			
 			ArrayList rowList;
 			
             try{
 			for (int i = 0; i < list.size(); i++) {
 				
 				ServiceProviderDTO objServiceProviderDTO = new ServiceProviderDTO();
 				
 				rowList = (ArrayList)list.get(i);
 				
 				objServiceProviderDTO.setRequestNo("0"+rowList.get(0).toString());
 				objServiceProviderDTO.setDate(rowList.get(1).toString());
 				objServiceProviderDTO.setApplctnStatus(rowList.get(2).toString());
 				
 					
 				
 				pendingAppListFinal.add(objServiceProviderDTO);
 		
 			}
 				
 			
 				
 			}
             catch(Exception e){
             	e.printStackTrace();
             	
             }
 		}
 		return pendingAppListFinal;
 	}
 	
 	public ArrayList getPendingAppsSearchDIG2(String ofcId, String applctnNumber) throws Exception
 	{
 		
 		
 		ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
 		ArrayList pendingAppListFinal = new ArrayList();
 		ArrayList list=objServiceProviderBD.getPendingAppsSearchDIG2(ofcId, applctnNumber);
 		
 		if(list!=null && list.size()>0){
 			
 			ArrayList rowList;
 			
             try{
 			for (int i = 0; i < list.size(); i++) {
 				
 				ServiceProviderDTO objServiceProviderDTO = new ServiceProviderDTO();
 				
 				rowList = (ArrayList)list.get(i);
 				
 				objServiceProviderDTO.setRequestNo("0"+rowList.get(0).toString());
 				objServiceProviderDTO.setDate(rowList.get(1).toString());
 				objServiceProviderDTO.setApplctnStatus(rowList.get(2).toString());
 				
 					
 				
 				pendingAppListFinal.add(objServiceProviderDTO);
 		
 			}
 				
 			
 				
 			}
             catch(Exception e){
             	e.printStackTrace();
             	
             }
 		}
 		return pendingAppListFinal;
 	}
 	
 	public ArrayList getPendingAppsSearchDIG3(String ofcId, String fromDate, String toDate, String applctnNumber) throws Exception
 	{
 		
 		
 		ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
 		ArrayList pendingAppListFinal = new ArrayList();
 		ArrayList list=objServiceProviderBD.getPendingAppsSearchDIG3(ofcId, fromDate, toDate,applctnNumber );
 		
 		if(list!=null && list.size()>0){
 			
 			ArrayList rowList;
 			
             try{
 			for (int i = 0; i < list.size(); i++) {
 				
 				ServiceProviderDTO objServiceProviderDTO = new ServiceProviderDTO();
 				
 				rowList = (ArrayList)list.get(i);
 				
 				objServiceProviderDTO.setRequestNo("0"+rowList.get(0).toString());
 				objServiceProviderDTO.setDate(rowList.get(1).toString());
 				objServiceProviderDTO.setApplctnStatus(rowList.get(2).toString());
 				
 					
 				
 				pendingAppListFinal.add(objServiceProviderDTO);
 		
 			}
 				
 			
 				
 			}
             catch(Exception e){
             	e.printStackTrace();
             	
             }
 		}
 		return pendingAppListFinal;
 	}
 	
 	public boolean updateStatusOfApplicatnDIG(ServiceProviderForm objServiceProviderForm) throws Exception
   	{
   		ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
   		
   		boolean inserTxn=objServiceProviderBD.updateStatusOfApplicatnDIG(objServiceProviderForm);

   		return inserTxn;
   	}
 	
 	public boolean updateStatusOfApplicatnDR(ServiceProviderForm objServiceProviderForm) throws Exception
   	{
   		ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
   		
   		boolean inserTxn=objServiceProviderBD.updateStatusOfApplicatnDR(objServiceProviderForm);

   		return inserTxn;
   	}
 	
 	public boolean updateSpDetls(ServiceProviderForm objServiceProviderForm) throws Exception
	{
		ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
		
		boolean inserTxn=objServiceProviderBD.updateSpDetls(objServiceProviderForm);

		return inserTxn;
	}
 	
 	public ServiceProviderForm getspDetlsForUpdation(ServiceProviderForm objServiceProviderForm,String lang) throws Exception
	{
		ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
		ArrayList list = objServiceProviderBD.getspDetls(objServiceProviderForm,lang);
		if (list != null && list.size()>0)
		{
			ArrayList rowList;
			try {
				for (int i=0; i<list.size();i++)
				{
					rowList = (ArrayList)list.get(i);
					//objServiceProviderDTO.setBankName(rowList.get(0));
					//objServiceProviderDTO.setAuthPersName(rowList.get(1));
					//objServiceProviderDTO.setDesignation(rowList.get(2));
					//objServiceProviderDTO.setAddressSp(rowList.get(3));
					//objServiceProviderDTO.setDistrict((String) rowList.get(4));
					//objServiceProviderDTO.setTehsil((String)rowList.get(5));
					//objServiceProviderDTO.setWard((String)rowList.get(6));
					//objServiceProviderDTO.setMohalla((String)rowList.get(7));
					objServiceProviderForm.getObjServiceProviderDTO().setEducation(rowList.get(8));
					
					objServiceProviderForm.getObjServiceProviderDTO().setLangKnown(rowList.get(9));	
					//objServiceProviderDTO.setRadioSelectExp(rowList.get(10));
					//objServiceProviderDTO.setRadioSelectCh(rowList.get(11));
					//objServiceProviderDTO.setAmount(rowList.get(12));
					objServiceProviderForm.getObjServiceProviderDTO().setApplicantStatus(rowList.get(13));
				}
			}catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return objServiceProviderForm;
		
	}
 	
 	public ServiceProviderForm getspDetlsBankForUpdation(ServiceProviderForm objServiceProviderForm,String lang) throws Exception
	{
		ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
		ArrayList list = objServiceProviderBD.getspDetls(objServiceProviderForm,lang);
		if (list != null && list.size()>0)
		{
			ArrayList rowList;
			try {
				for (int i=0; i<list.size();i++)
				{
					rowList = (ArrayList)list.get(i);
					objServiceProviderForm.getObjServiceProviderDTO().setBankName(rowList.get(0));
					objServiceProviderForm.getObjServiceProviderDTO().setAuthPersName(rowList.get(1));
					objServiceProviderForm.getObjServiceProviderDTO().setDesignation(rowList.get(2));
					//objServiceProviderDTO.setAddressSp(rowList.get(3));
					//objServiceProviderDTO.setDistrict((String) rowList.get(4));
					//objServiceProviderDTO.setTehsil((String)rowList.get(5));
					//objServiceProviderDTO.setWard((String)rowList.get(6));
					//objServiceProviderDTO.setMohalla((String)rowList.get(7));
					//objServiceProviderDTO.setEducation(rowList.get(8));
					//objServiceProviderDTO.setLangKnown(rowList.get(9));
					//objServiceProviderDTO.setRadioSelectExp(rowList.get(10));
					//objServiceProviderDTO.setRadioSelectCh(rowList.get(11));
					//objServiceProviderDTO.setAmount(rowList.get(12));
					objServiceProviderForm.getObjServiceProviderDTO().setApplicantStatus(rowList.get(13));
				}
			}catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return objServiceProviderForm;
		
	}
 	
 	public ServiceProviderForm getspDetlsAllForUpdation(ServiceProviderForm objServiceProviderForm,String lang) throws Exception
	{
		ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
		ArrayList list = objServiceProviderBD.getspDetls(objServiceProviderForm,lang);
		if (list != null && list.size()>0)
		{
			ArrayList rowList;
			try {
				for (int i=0; i<list.size();i++)
				{
					
					rowList = (ArrayList)list.get(i);
					//objServiceProviderDTO.setBankName(rowList.get(0));
					//objServiceProviderDTO.setAuthPersName(rowList.get(1));
					//objServiceProviderDTO.setDesignation(rowList.get(2));
					objServiceProviderForm.getObjServiceProviderDTO().setAddressSp(rowList.get(3));
					objServiceProviderForm.getObjServiceProviderDTO().setDistrict((String) rowList.get(4));
					objServiceProviderForm.getObjServiceProviderDTO().setTehsil((String)rowList.get(5));
					/*objServiceProviderForm.getObjServiceProviderDTO().setWard((String)rowList.get(6));
					objServiceProviderForm.getObjServiceProviderDTO().setMohalla((String)rowList.get(7));*/
					objServiceProviderForm.getObjServiceProviderDTO().setWardPatwariId((String)rowList.get(6));
					objServiceProviderForm.getObjServiceProviderDTO().setColonyId((String)rowList.get(7));
					//objServiceProviderDTO.setEducation(rowList.get(8));
					//objServiceProviderDTO.setLangKnown(rowList.get(9));
					if (rowList.get(10).toString().equalsIgnoreCase("Y"))
					{
							objServiceProviderForm.getObjServiceProviderDTO().setRadioSelectExp("Y");
					}
					else if (rowList.get(10).toString().equalsIgnoreCase("N"))
					{
						
							objServiceProviderForm.getObjServiceProviderDTO().setRadioSelectExp("N");
						
					}
					if (rowList.get(11).toString().equalsIgnoreCase("Y"))
					{
							objServiceProviderForm.getObjServiceProviderDTO().setRadioSelectCh("Y");
					
					}
					else if (rowList.get(11).toString().equalsIgnoreCase("N"))
					{
						
							objServiceProviderForm.getObjServiceProviderDTO().setRadioSelectCh("N");
						
					}
					objServiceProviderForm.getObjServiceProviderDTO().setAmount(rowList.get(12));
					objServiceProviderForm.getObjServiceProviderDTO().setApplicantStatus(rowList.get(13));
					objServiceProviderForm.getObjServiceProviderDTO().setPanCard(rowList.get(14));
					objServiceProviderForm.getObjServiceProviderDTO().setForm60(rowList.get(15));
					objServiceProviderForm.getObjServiceProviderDTO().setAreaId((String)rowList.get(16));
					objServiceProviderForm.getObjServiceProviderDTO().setSubAreaId((String)rowList.get(17));
					objServiceProviderForm.getObjServiceProviderDTO().setDurationFrom((String)rowList.get(18));
					objServiceProviderForm.getObjServiceProviderDTO().setDurationTo((String)rowList.get(19));
				}
			}catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return objServiceProviderForm;
		
	}
 	
 		public String getdisttId(ServiceProviderForm objServiceProviderForm) throws Exception {
		
		ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
			
		String disttId=objServiceProviderBD.getdisttId(objServiceProviderForm);

		return disttId;
		
	}
 		//added by shruti
 		public String getTehsilId(ServiceProviderForm objServiceProviderForm) throws Exception {
 			ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
 			String tehsilId=objServiceProviderBD.getTehsilId(objServiceProviderForm);
 			return tehsilId;
 		}
 		public String getWardId(ServiceProviderForm objServiceProviderForm,String subareaId,String tehsilId) throws Exception {
 			ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
 			String wardId=objServiceProviderBD.getWardId(objServiceProviderForm,subareaId,tehsilId);
 			return wardId;
 		}
 		public String getMohallaId(ServiceProviderForm objServiceProviderForm,String mappingId ) throws Exception {
 			ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
 			String mohallaId=objServiceProviderBD.getMohallaId(objServiceProviderForm,mappingId);
 			return mohallaId;
 		}
 		public String getAreaId(ServiceProviderForm objServiceProviderForm) throws Exception {
 			ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
 			String mohallaId=objServiceProviderBD.getAreaId(objServiceProviderForm);
 			return mohallaId;
 		}
 		public String getSubAreaId(ServiceProviderForm objServiceProviderForm) throws Exception {
 			ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
 			String mohallaId=objServiceProviderBD.getSubAreaId(objServiceProviderForm);
 			return mohallaId;
 		}
 		//end
 		public ArrayList getDroDetailsIfAlreadyFiled(ServiceProviderForm objServiceProviderForm,String language) throws Exception
 		{
 			ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
 			ArrayList droDetailsList = new ArrayList();
 			ArrayList list = objServiceProviderBD.getDroDetailsIfAlreadyFiled(objServiceProviderForm,language);
 			if (list != null && list.size()>0)
 			{
 				ArrayList rowList;
 				try {
 					for (int i=0; i<list.size();i++)
 					{
 						ServiceProviderDTO objServiceProviderDTO = new ServiceProviderDTO();
 						rowList = (ArrayList)list.get(i);
 						objServiceProviderDTO.setReqNo("0"+(String) rowList.get(0));
 						
 						if (rowList.get(1)!=null)
 						{
 							objServiceProviderDTO.setDrAddress(rowList.get(1));
 						}
 						else
 						{
 						objServiceProviderDTO.setDrAddress("-");
 						}
 						if (rowList.get(2)!=null)
 						{
 						objServiceProviderDTO.setDrPhNo(rowList.get(2));
 						}
 						else
 						{
 						objServiceProviderDTO.setDrPhNo("-");
 						}
 						objServiceProviderForm.getObjServiceProviderDTO().setApplctnStatus((String) rowList.get(3));
 						
 						droDetailsList.add(objServiceProviderDTO);
 					}
 				}catch (Exception e)
 				{
 					e.printStackTrace();
 				}
 			}
 			return droDetailsList;
 			
 		}
 		
 		public ServiceProviderForm getPaymentDetls(ServiceProviderForm objServiceProviderForm,String language) throws Exception
 		{
 			ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
 			ArrayList list = objServiceProviderBD.getPaymentDetls(objServiceProviderForm,language);
 			if (list != null && list.size()>0)
 			{
 				ArrayList rowList;
 				try {
 					for (int i=0; i<list.size();i++)
 					{
 						
 						rowList = (ArrayList)list.get(i);
 						
 						objServiceProviderForm.getObjServiceProviderDTO().setPayableAmount(rowList.get(0));
 						objServiceProviderForm.getObjServiceProviderDTO().setPaidAmount(rowList.get(1));
 						objServiceProviderForm.getObjServiceProviderDTO().setSpPaymentId(rowList.get(2));
 						objServiceProviderForm.getObjServiceProviderDTO().setPaymentFlag(rowList.get(3));
 						objServiceProviderForm.getObjServiceProviderDTO().setDrOfcId(rowList.get(4));
 						objServiceProviderForm.getObjServiceProviderDTO().setDrOfficeName(rowList.get(5));
 						objServiceProviderForm.getObjServiceProviderDTO().setDrDistrict(rowList.get(6));
 						objServiceProviderForm.getObjServiceProviderDTO().setDrDistrictName(rowList.get(7));
 						objServiceProviderForm.getObjServiceProviderDTO().setAmountRemaining(Double.parseDouble((String)rowList.get(0))-Double.parseDouble((String)rowList.get(1)));
 					}
 				}catch (Exception e)
 				{
 					e.printStackTrace();
 				}
 			}
 			return objServiceProviderForm;
 			
 		}
 		
 		public boolean insertPaymentDetls(ServiceProviderForm objServiceProviderForm) throws Exception {
 			
 			ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
 				
 			boolean insert=objServiceProviderBD.insertPaymentDetls(objServiceProviderForm);

 			return insert;
 			
 		}
 		
 		public boolean updateStatus(ServiceProviderForm objServiceProviderForm) throws Exception {
 			
 			ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
 				
 			boolean update=objServiceProviderBD.updateStatus(objServiceProviderForm);

 			return update;
 			
 		}
 		//TODO -----
 		public ArrayList getPreviousLicenseDetailsEstamp(ServiceProviderForm objServiceProviderForm) throws Exception
 		{
 			ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
 			ArrayList previousLicenseDetails = new ArrayList();
 			ArrayList list = objServiceProviderBD.getPreviousLicenseDetailsEstamp(objServiceProviderForm);
 			if (list != null && list.size()>0)
 			{
 				ArrayList rowList;
 				try {
 					for (int i=0; i<list.size();i++)
 					{
 						ServiceProviderDTO objServiceProviderDTO = new ServiceProviderDTO();
 						rowList = (ArrayList)list.get(i);
 						
 						
 						objServiceProviderDTO.setBalAmountCreditEstamp(rowList.get(0));
 						
 						objServiceProviderForm.getObjServiceProviderDTO().setBalAmountCreditEstamp(rowList.get(0));
 						objServiceProviderForm.getObjServiceProviderDTO().setHdnBalAmountCreditEstamp(rowList.get(0));
 						objServiceProviderForm.getObjServiceProviderDTO().setPreviousLicenseNoEstamp(rowList.get(1));
 						
 						
 						
 						previousLicenseDetails.add(objServiceProviderDTO);
 					}
 				}catch (Exception e)
 				{
 					e.printStackTrace();
 				}
 			}
 			return previousLicenseDetails;
 			
 		}
 		
 		public ArrayList getPreviousLicenseDetailsOthers(ServiceProviderForm objServiceProviderForm) throws Exception
 		{
 			ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
 			ArrayList previousLicenseDetails = new ArrayList();
 			ArrayList list = objServiceProviderBD.getPreviousLicenseDetailsOthers(objServiceProviderForm);
 			if (list != null && list.size()>0)
 			{
 				ArrayList rowList;
 				try {
 					for (int i=0; i<list.size();i++)
 					{
 						ServiceProviderDTO objServiceProviderDTO = new ServiceProviderDTO();
 						rowList = (ArrayList)list.get(i);
 						
 						objServiceProviderDTO.setBalAmountCreditOthers(rowList.get(0));
 						
 						objServiceProviderForm.getObjServiceProviderDTO().setBalAmountCreditOthers(rowList.get(0));
 						objServiceProviderForm.getObjServiceProviderDTO().setHdnBalAmountCreditOthers(rowList.get(0));
 						objServiceProviderForm.getObjServiceProviderDTO().setPreviousLicenseNoOthers(rowList.get(1));
 						
 						previousLicenseDetails.add(objServiceProviderDTO);
 					}
 				}catch (Exception e)
 				{
 					e.printStackTrace();
 				}
 			}
 			return previousLicenseDetails;
 			
 		}
 		
 		public ServiceProviderForm getFirstTimeLicenseDetails(ServiceProviderForm objServiceProviderForm) throws Exception
 		{
 			ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
 			ArrayList list = objServiceProviderBD.getFirstTimeLicenseDetails(objServiceProviderForm);
 			if (list != null && list.size()>0)
 			{
 				ArrayList rowList;
 				try {
 					for (int i=0; i<list.size();i++)
 					{
 						
 						rowList = (ArrayList)list.get(i);
 						
 						
 						
 						objServiceProviderForm.getObjServiceProviderDTO().setDurationFrom((String) rowList.get(0));
 						objServiceProviderForm.getObjServiceProviderDTO().setDurationTo((String)rowList.get(1));
 						objServiceProviderForm.getObjServiceProviderDTO().setLicenseNumber((String)rowList.get(2));
 					}
 				}catch (Exception e)
 				{
 					e.printStackTrace();
 				}
 			}
 			return objServiceProviderForm;
 			
 		}
 		
 		public boolean insertLicenseNumber(ServiceProviderForm objServiceProviderForm) throws Exception {
 			
 			ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
 				
 			boolean insert=objServiceProviderBD.insertLicenseNumber(objServiceProviderForm);

 			return insert;
 			
 		}
 		
 		public ServiceProviderForm getDRComments(ServiceProviderForm objServiceProviderForm) throws Exception
 		{
 			ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
 			ArrayList list = objServiceProviderBD.getDRComments(objServiceProviderForm);
 			if (list != null && list.size()>0)
 			{
 				ArrayList rowList;
 				try {
 					for (int i=0; i<list.size();i++)
 					{
 						
 						rowList = (ArrayList)list.get(i);
 						
 						if (objServiceProviderForm.getObjServiceProviderDTO().getApplicantStatus().equals("3"))
 						{
 						
 						objServiceProviderForm.getObjServiceProviderDTO().setRmrksCallForPV((String) rowList.get(0));
 						}
 						
 						else if (objServiceProviderForm.getObjServiceProviderDTO().getApplicantStatus().equals("4"))
 						{
 						
 						objServiceProviderForm.getObjServiceProviderDTO().setRmrksCallForAddInfo((String) rowList.get(0));
 						}
 						
 						else if (objServiceProviderForm.getObjServiceProviderDTO().getApplicantStatus().equals("5"))
 						{
 						
 						objServiceProviderForm.getObjServiceProviderDTO().setComments((String) rowList.get(0));
 						}
 						
 						else if (objServiceProviderForm.getObjServiceProviderDTO().getApplicantStatus().equals("6"))
 						{
 						
 						objServiceProviderForm.getObjServiceProviderDTO().setRmrksForRejection((String) rowList.get(0));
 						}
 						else
 						{
 							
 						}
 					}
 				}catch (Exception e)
 				{
 					e.printStackTrace();
 				}
 			}
 			return objServiceProviderForm;
 			
 		}
 		
 		public ArrayList getLicenseNumber(ServiceProviderForm objServiceProviderForm) throws Exception
 		{
 			ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
 			ArrayList previousLicenseDetails = new ArrayList();
 			ArrayList list = objServiceProviderBD.getLicenseNumber(objServiceProviderForm);
 			if (list!= null && list.size()>0)
 			{
 				ArrayList rowList;
 				try {
 					for (int i=0; i<list.size();i++)
 					{
 						ServiceProviderDTO objServiceProviderDTO = new ServiceProviderDTO();
 						rowList = (ArrayList)list.get(i);
 						objServiceProviderDTO.setLicenseNumber(rowList.get(0));
 						//objServiceProviderDTO.setDurationFrom((String) rowList.get(1));
 						//objServiceProviderDTO.setDurationTo((String) rowList.get(2));
 						objServiceProviderForm.getObjServiceProviderDTO().setLicenseNumber(rowList.get(0));
 						//objServiceProviderForm.getObjServiceProviderDTO().setDurationFrom((String) rowList.get(1));
 						//objServiceProviderForm.getObjServiceProviderDTO().setDurationTo((String) rowList.get(2));
 						previousLicenseDetails.add(objServiceProviderDTO);
 					}
 				}catch (Exception e)
 				{
 					e.printStackTrace();
 				}
 			}
 			return previousLicenseDetails;
 			
 		}
 		
 		public boolean updateStatusToLicenseIssued(ServiceProviderForm objServiceProviderForm) throws Exception
 	   	{
 	   		ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
 	   		
 	   		boolean inserTxn=objServiceProviderBD.updateStatusToLicenseIssued(objServiceProviderForm);

 	   		return inserTxn;
 	   	}
 		
 		public boolean updateLicenseDate(ServiceProviderForm objServiceProviderForm) throws Exception
 	   	{
 	   		ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
 	   		
 	   		boolean inserTxn=objServiceProviderBD.updateLicenseDate(objServiceProviderForm);

 	   		return inserTxn;
 	   	}
 		
 		public ArrayList getPdfDetlsForLicense(ServiceProviderForm objServiceProviderForm,String language) throws Exception
 		{
 			ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
 			ArrayList licenseDetails = new ArrayList();
 			ArrayList list = objServiceProviderBD.getPdfDetlsForLicense(objServiceProviderForm,language);
 			if (list != null && list.size()>0)
 			{
 				ArrayList rowList;
 				try {
 					for (int i=0; i<list.size();i++)
 					{
 						ServiceProviderDTO objServiceProviderDTO = new ServiceProviderDTO();
 						
 						rowList = (ArrayList)list.get(i);
 						objServiceProviderDTO.setLicenseNumber((String) rowList.get(0));
 						
 						objServiceProviderForm.getObjServiceProviderDTO().setLicenseNumber((String) rowList.get(0));
 						objServiceProviderForm.getObjServiceProviderDTO().setAddressSp((String) rowList.get(1));
 						objServiceProviderForm.getObjServiceProviderDTO().setDistrict((String) rowList.get(2));
 						objServiceProviderForm.getObjServiceProviderDTO().setTehsil((String) rowList.get(3));
 						objServiceProviderForm.getObjServiceProviderDTO().setWard((String) rowList.get(4));
 						objServiceProviderForm.getObjServiceProviderDTO().setMohalla((String) rowList.get(5));
 						objServiceProviderForm.getObjServiceProviderDTO().setYear((String) rowList.get(6));
 						objServiceProviderForm.getObjServiceProviderDTO().setMonth((String) rowList.get(7));
 						objServiceProviderForm.getObjServiceProviderDTO().setDays((String) rowList.get(8));
 						objServiceProviderForm.getObjServiceProviderDTO().setDurationFrom((String) rowList.get(9));
 						objServiceProviderForm.getObjServiceProviderDTO().setDurationTo((String)rowList.get(10));
 						//ADDED BY SHRUTI---18 MARCH 2014---dms integration
 						objServiceProviderForm.getObjServiceProviderDTO().setPhotoLoc((String)rowList.get(11));
 						objServiceProviderForm.getObjServiceProviderDTO().setSignLoc((String)rowList.get(12));
 						//modified by shruti---20 march 2014
 						objServiceProviderForm.getObjServiceProviderDTO().setNewOrRenewalFlag((String)rowList.get(13));
 						objServiceProviderForm.getObjServiceProviderDTO().setSpName((String)rowList.get(14));
 						objServiceProviderForm.getObjServiceProviderDTO().setFatherName((String)rowList.get(15));
 						//added by shruti---29 april 2014
 						objServiceProviderForm.getObjServiceProviderDTO().setDrName((String)rowList.get(16));
 						objServiceProviderForm.getObjServiceProviderDTO().setDrDesignation((String)rowList.get(17));
 						//modified by shruti----28 august 2014
 						objServiceProviderForm.getObjServiceProviderDTO().setAddress((String)rowList.get(18));
 						//end
 						//licenseDetails.add(objServiceProviderDTO);
 						licenseDetails.add(objServiceProviderForm.getObjServiceProviderDTO());
 					}
 				}catch (Exception e)
 				{
 					e.printStackTrace();
 				}
 			}
 			return licenseDetails;
 			
 		}
 		
 		public String getTodaysDate() throws Exception
 		{
 			ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
 			String todaysDate = objServiceProviderBD.getTodaysDate();
 			return todaysDate;
 			
 		}
 	
 		public ArrayList<ServiceProviderDTO> cancelLicenseDashboard(ServiceProviderDTO spDTO,String lang)
 		{
 			ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
 			ArrayList list= objServiceProviderBD.cancelLicenseDashboard(spDTO,lang);
 			ArrayList<ServiceProviderDTO> spList= new ArrayList<ServiceProviderDTO>();
 			if(list!=null && list.size()>0)
 			{
 				for(int i=0;i<list.size();i++)
 				{
 					ServiceProviderDTO spDTO1=new ServiceProviderDTO();
 					ArrayList rowList=(ArrayList) list.get(i);
 					spDTO1.setLicenseId(rowList.get(0)!=null?rowList.get(0).toString():"");	
 					spDTO1.setSpTypeName(rowList.get(1)!=null?rowList.get(1).toString():"");
 					if(rowList.get(2)!=null && !rowList.get(2).toString().equalsIgnoreCase(""))
 					{
 						spDTO1.setName(rowList.get(2)!=null?rowList.get(2).toString():"");
 					}
 					else
 					{
 						spDTO1.setName(rowList.get(3)!=null?rowList.get(3).toString():"");
 					}
 					spDTO1.setToCreatedDate(rowList.get(4)!=null?rowList.get(4).toString():"");
 					spDTO1.setStatusName(rowList.get(5)!=null?rowList.get(5).toString():"");
 					spList.add(spDTO1);
 				}
 			}
 			return spList;
 		}
     
     
 		public String getRequestNumber(String licenseId)
 		{
 			ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
 			return objServiceProviderBD.getRequestNumber(licenseId);
 		}
 		
 		public ArrayList getDroDetailsIfAlreadyFiledRenewal(ServiceProviderForm objServiceProviderForm,String lang) throws Exception
 		{
 			ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
 			ArrayList droDetailsList = new ArrayList();
 			ArrayList list = objServiceProviderBD.getDroDetailsIfAlreadyFiledRenewal(objServiceProviderForm,lang);
 			if (list != null && list.size()>0)
 			{
 				ArrayList rowList;
 				try {
 					for (int i=0; i<list.size();i++)
 					{
 						ServiceProviderDTO objServiceProviderDTO = new ServiceProviderDTO();
 						rowList = (ArrayList)list.get(i);
 						objServiceProviderDTO.setReqNo("0"+(String) rowList.get(0));
 						
 						if (rowList.get(1)!=null)
 						{
 							objServiceProviderDTO.setDrAddress(rowList.get(1));
 						}
 						else
 						{
 						objServiceProviderDTO.setDrAddress("-");
 						}
 						if (rowList.get(2)!=null)
 						{
 						objServiceProviderDTO.setDrPhNo(rowList.get(2));
 						}
 						else
 						{
 						objServiceProviderDTO.setDrPhNo("-");
 						}
 						objServiceProviderForm.getObjServiceProviderDTO().setApplctnStatus((String) rowList.get(3));
 						
 						droDetailsList.add(objServiceProviderDTO);
 					}
 				}catch (Exception e)
 				{
 					e.printStackTrace();
 				}
 			}
 			return droDetailsList;
 			
 		}
 		
 		public ArrayList getSpTypeRenewal(ServiceProviderForm objServiceProviderForm,String lang) throws Exception
 		{
 			ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
 			ArrayList details = new ArrayList();
 			ArrayList list = objServiceProviderBD.getSpTypeRenewal(objServiceProviderForm,lang);
 			if (list != null && list.size()>0)
 			{
 				ArrayList rowList;
 				try {
 					for (int i=0; i<list.size();i++)
 					{
 						ServiceProviderDTO objServiceProviderDTO = new ServiceProviderDTO();
 						rowList = (ArrayList)list.get(i);
 						
 						objServiceProviderDTO.setSpType((String) rowList.get(0));
 						objServiceProviderDTO.setLicenseNumber((String) rowList.get(1));
 						objServiceProviderDTO.setSpTypeName((String) rowList.get(2));
 						
 						objServiceProviderForm.getObjServiceProviderDTO().setSpType((String) rowList.get(0));
 						objServiceProviderForm.getObjServiceProviderDTO().setLicenseNumber((String) rowList.get(1));
 						objServiceProviderForm.getObjServiceProviderDTO().setSpTypeName((String) rowList.get(2));
 						
 						details.add(objServiceProviderDTO);
 					}
 				}catch (Exception e)
 				{
 					e.printStackTrace();
 				}
 			}
 			return details;
 			
 		}
 		
 		public ServiceProviderForm getspDetlsForRenewal(ServiceProviderForm objServiceProviderForm) throws Exception
 		{
 			ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
 			ArrayList list = objServiceProviderBD.getspDetlsForRenewal(objServiceProviderForm);
 			if (list != null && list.size()>0)
 			{
 				ArrayList rowList;
 				try {
 					for (int i=0; i<list.size();i++)
 					{
 						rowList = (ArrayList)list.get(i);
 						//objServiceProviderDTO.setBankName(rowList.get(0));
 						//objServiceProviderDTO.setAuthPersName(rowList.get(1));
 						//objServiceProviderDTO.setDesignation(rowList.get(2));
 						//objServiceProviderDTO.setAddressSp(rowList.get(3));
 						//objServiceProviderDTO.setDistrict((String) rowList.get(4));
 						//objServiceProviderDTO.setTehsil((String)rowList.get(5));
 						//objServiceProviderDTO.setWard((String)rowList.get(6));
 						//objServiceProviderDTO.setMohalla((String)rowList.get(7));
 						objServiceProviderForm.getObjServiceProviderDTO().setEducation(rowList.get(8));
 						
 						objServiceProviderForm.getObjServiceProviderDTO().setLangKnown(rowList.get(9));	
 						//objServiceProviderDTO.setRadioSelectExp(rowList.get(10));
 						//objServiceProviderDTO.setRadioSelectCh(rowList.get(11));
 						//objServiceProviderDTO.setAmount(rowList.get(12));
 						objServiceProviderForm.getObjServiceProviderDTO().setApplicantStatus(rowList.get(13));
 						objServiceProviderForm.getObjServiceProviderDTO().setRequestNo("0"+rowList.get(14));
 					}
 				}catch (Exception e)
 				{
 					e.printStackTrace();
 				}
 			}
 			return objServiceProviderForm;
 			
 		}
 		
 		public ServiceProviderForm getspDetlsBankForRenewal(ServiceProviderForm objServiceProviderForm) throws Exception
 		{
 			ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
 			ArrayList list = objServiceProviderBD.getspDetlsForRenewal(objServiceProviderForm);
 			if (list != null && list.size()>0)
 			{
 				ArrayList rowList;
 				try {
 					for (int i=0; i<list.size();i++)
 					{
 						rowList = (ArrayList)list.get(i);
 						objServiceProviderForm.getObjServiceProviderDTO().setBankName(rowList.get(0));
 						objServiceProviderForm.getObjServiceProviderDTO().setAuthPersName(rowList.get(1));
 						objServiceProviderForm.getObjServiceProviderDTO().setDesignation(rowList.get(2));
 						//objServiceProviderDTO.setAddressSp(rowList.get(3));
 						//objServiceProviderDTO.setDistrict((String) rowList.get(4));
 						//objServiceProviderDTO.setTehsil((String)rowList.get(5));
 						//objServiceProviderDTO.setWard((String)rowList.get(6));
 						//objServiceProviderDTO.setMohalla((String)rowList.get(7));
 						//objServiceProviderDTO.setEducation(rowList.get(8));
 						//objServiceProviderDTO.setLangKnown(rowList.get(9));
 						//objServiceProviderDTO.setRadioSelectExp(rowList.get(10));
 						//objServiceProviderDTO.setRadioSelectCh(rowList.get(11));
 						//objServiceProviderDTO.setAmount(rowList.get(12));
 						objServiceProviderForm.getObjServiceProviderDTO().setApplicantStatus(rowList.get(13));
 						objServiceProviderForm.getObjServiceProviderDTO().setRequestNo("0"+rowList.get(14));
 					}
 				}catch (Exception e)
 				{
 					e.printStackTrace();
 				}
 			}
 			return objServiceProviderForm;
 			
 		}
 		
 		public ServiceProviderForm getspDetlsAllForRenewal(ServiceProviderForm objServiceProviderForm) throws Exception
 		{
 			ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
 			ArrayList list = objServiceProviderBD.getspDetlsForRenewal(objServiceProviderForm);
 			if (list != null && list.size()>0)
 			{
 				ArrayList rowList;
 				try {
 					for (int i=0; i<list.size();i++)
 					{
 						
 						rowList = (ArrayList)list.get(i);
 						//objServiceProviderDTO.setBankName(rowList.get(0));
 						//objServiceProviderDTO.setAuthPersName(rowList.get(1));
 						//objServiceProviderDTO.setDesignation(rowList.get(2));
 						objServiceProviderForm.getObjServiceProviderDTO().setAddressSp(rowList.get(3));
 						objServiceProviderForm.getObjServiceProviderDTO().setDistrict((String) rowList.get(4));
 						objServiceProviderForm.getObjServiceProviderDTO().setTehsil((String)rowList.get(5));
 						objServiceProviderForm.getObjServiceProviderDTO().setWard((String)rowList.get(6));
 						objServiceProviderForm.getObjServiceProviderDTO().setMohalla((String)rowList.get(7));
 						//objServiceProviderDTO.setEducation(rowList.get(8));
 						//objServiceProviderDTO.setLangKnown(rowList.get(9));
 						if (rowList.get(10).toString().equalsIgnoreCase("Y"))
 						{
 								objServiceProviderForm.getObjServiceProviderDTO().setRadioSelectExp("Y");
 						}
 						else if (rowList.get(10).toString().equalsIgnoreCase("N"))
 						{
 							
 								objServiceProviderForm.getObjServiceProviderDTO().setRadioSelectExp("N");
 							
 						}
 						if (rowList.get(11).toString().equalsIgnoreCase("Y"))
 						{
 								objServiceProviderForm.getObjServiceProviderDTO().setRadioSelectCh("Y");
 						
 						}
 						else if (rowList.get(11).toString().equalsIgnoreCase("N"))
 						{
 							
 								objServiceProviderForm.getObjServiceProviderDTO().setRadioSelectCh("N");
 							
 						}
 						objServiceProviderForm.getObjServiceProviderDTO().setAmount(rowList.get(12));
 						objServiceProviderForm.getObjServiceProviderDTO().setApplicantStatus(rowList.get(13));
 						objServiceProviderForm.getObjServiceProviderDTO().setRequestNo("0"+rowList.get(14));
 						//added by shruti---23 april 2014
 						objServiceProviderForm.getObjServiceProviderDTO().setPanCard(rowList.get(15));
 						
 						objServiceProviderForm.getObjServiceProviderDTO().setForm60(rowList.get(16));
 						//added by shruti----23 april 2014
 						if(objServiceProviderForm.getObjServiceProviderDTO().getPanCard()!=null)
 						  {
 							  objServiceProviderForm.setPanCardFlag("Y");
 							  objServiceProviderForm.getObjServiceProviderDTO().setPancardFlag("Y");
 							  objServiceProviderForm.setForm60Flag("N");
 							  objServiceProviderForm.getObjServiceProviderDTO().setPanCard(objServiceProviderForm.getObjServiceProviderDTO().getPanCard());
 						  }
 						  else if(objServiceProviderForm.getObjServiceProviderDTO().getForm60()!=null)
 						  {
 							  objServiceProviderForm.setPanCardFlag("N");
 							  objServiceProviderForm.getObjServiceProviderDTO().setPancardFlag("N");
 							  objServiceProviderForm.getObjServiceProviderDTO().setForm60Flag("Y");
 							  objServiceProviderForm.setForm60Flag("Y");
 							  objServiceProviderForm.getObjServiceProviderDTO().setForm60(objServiceProviderForm.getObjServiceProviderDTO().getForm60());
 						  }
 						//end
 					}
 				}catch (Exception e)
 				{
 					e.printStackTrace();
 				}
 			}
 			return objServiceProviderForm;
 			
 		}
 		
 		public ArrayList getLicenseDetails(ServiceProviderForm objServiceProviderForm) throws Exception
 		{
 			ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
 			ArrayList details = new ArrayList();
 			ArrayList list=new ArrayList();
 			list = objServiceProviderBD.getLicenseDetails(objServiceProviderForm);
 			if (list != null && list.size()>0)
 			{
 				ArrayList rowList;
 				try {
 					for (int i=0; i<list.size();i++)
 					{
 						ServiceProviderDTO objServiceProviderDTO = new ServiceProviderDTO();
 						rowList = (ArrayList)list.get(i);
 						objServiceProviderDTO.setLicenseNumber((String) rowList.get(0));
 						objServiceProviderForm.getObjServiceProviderDTO().setLicenseNumber((String) rowList.get(0));
 						objServiceProviderForm.getObjServiceProviderDTO().setDurationFrom((String) rowList.get(1));
 						objServiceProviderForm.getObjServiceProviderDTO().setDurationTo((String) rowList.get(2));
 						
 						
 						details.add(objServiceProviderDTO);
 					}
 				}catch (Exception e)
 				{
 					e.printStackTrace();
 				}
 			}
 			return details;
 			
 		}
 		
 		public boolean renewLicenseNumber(ServiceProviderForm objServiceProviderForm) throws Exception {
 			
 			ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
 				
 			boolean insert=objServiceProviderBD.renewLicenseNumber(objServiceProviderForm);

 			return insert;
 			
 		}
 		
 		public boolean updatePreviousStatusOfLicenseIssued(ServiceProviderForm objServiceProviderForm) throws Exception {
 			
 			ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
 				
 			boolean insert=objServiceProviderBD.updatePreviousStatusOfLicenseIssued(objServiceProviderForm);

 			return insert;
 			
 		}
 		
 		public ArrayList getPdfDetlsForLicenseReprint(ServiceProviderForm objServiceProviderForm,String lang) throws Exception
 		{
 			ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
 			ArrayList licenseDetails = new ArrayList();
 			ArrayList list = objServiceProviderBD.getPdfDetlsForLicenseReprint(objServiceProviderForm,lang);
 			if (list != null && list.size()>0)
 			{
 				ArrayList rowList;
 				try {
 					for (int i=0; i<list.size();i++)
 					{
 						ServiceProviderDTO objServiceProviderDTO = new ServiceProviderDTO();
 						
 						rowList = (ArrayList)list.get(i);
 						objServiceProviderDTO.setLicenseNumber((String) rowList.get(0));
 						
 						objServiceProviderForm.getObjServiceProviderDTO().setLicenseNumber((String) rowList.get(0));
 						objServiceProviderForm.getObjServiceProviderDTO().setAddressSp((String) rowList.get(1));
 						objServiceProviderForm.getObjServiceProviderDTO().setDistrict((String) rowList.get(2));
 						objServiceProviderForm.getObjServiceProviderDTO().setTehsil((String) rowList.get(3));
 						objServiceProviderForm.getObjServiceProviderDTO().setWard((String) rowList.get(4));
 						objServiceProviderForm.getObjServiceProviderDTO().setMohalla((String) rowList.get(5));
 						objServiceProviderForm.getObjServiceProviderDTO().setYear((String) rowList.get(6));
 						objServiceProviderForm.getObjServiceProviderDTO().setMonth((String) rowList.get(7));
 						objServiceProviderForm.getObjServiceProviderDTO().setDays((String) rowList.get(8));
 						objServiceProviderForm.getObjServiceProviderDTO().setDurationFrom((String) rowList.get(9));
 						objServiceProviderForm.getObjServiceProviderDTO().setDurationTo((String)rowList.get(10));
 						//ADDED BY SHRUTI---19 MARCH 2014
 						objServiceProviderForm.getObjServiceProviderDTO().setPhotoLoc((String)rowList.get(11));
 						objServiceProviderForm.getObjServiceProviderDTO().setSignLoc((String)rowList.get(12));
 						objServiceProviderForm.getObjServiceProviderDTO().setNewOrRenewalFlag((String)rowList.get(13));
 						objServiceProviderForm.getObjServiceProviderDTO().setSpName((String)rowList.get(14));
 						objServiceProviderForm.getObjServiceProviderDTO().setFatherName((String)rowList.get(15));
 						//added by shruti----29 april 2014
 						objServiceProviderForm.getObjServiceProviderDTO().setDrName((String)rowList.get(16));
 						objServiceProviderForm.getObjServiceProviderDTO().setDrDesignation((String)rowList.get(17));
 						
 						//END
 						
 						//modified by shruti---28 august 2014
 						objServiceProviderForm.getObjServiceProviderDTO().setAddress((String)rowList.get(18));
 						
 						
 						licenseDetails.add(objServiceProviderDTO);
 					}
 				}catch (Exception e)
 				{
 					e.printStackTrace();
 				}
 			}
 			return licenseDetails;
 			
 		}
 		
 		public boolean insertAmount(ServiceProviderForm objServiceProviderForm) throws Exception {
 			
 			ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
 				
 			boolean insert=objServiceProviderBD.insertAmount(objServiceProviderForm);

 			return insert;
 			
 		}
 		
 		public ArrayList getDetlsWhetherPreviousLicenseExprd(ServiceProviderForm objServiceProviderForm) throws Exception
 		{
 			ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
 			ArrayList previousLicenseDetails = new ArrayList();
 			ArrayList list = objServiceProviderBD.getDetlsWhetherPreviousLicenseExprd(objServiceProviderForm);
 			if (list != null && list.size()>0)
 			{
 				ArrayList rowList;
 				try {
 					for (int i=0; i<list.size();i++)
 					{
 						ServiceProviderDTO objServiceProviderDTO = new ServiceProviderDTO();
 						rowList = (ArrayList)list.get(i);
 						
 						
 						objServiceProviderDTO.setLicenseNumber(rowList.get(0));
 						
 						previousLicenseDetails.add(objServiceProviderDTO);
 					}
 				}catch (Exception e)
 				{
 					e.printStackTrace();
 				}
 			}
 			return previousLicenseDetails;
 			
 		}
 		
 		public boolean updateExprdStatus(ServiceProviderForm objServiceProviderForm) throws Exception
 	   	{
 	   		ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
 	   		
 	   		boolean updateTxn=objServiceProviderBD.updateExprdStatus(objServiceProviderForm);

 	   		return updateTxn;
 	   	}
 		
 		public ArrayList getPlaceDetls(ServiceProviderForm objServiceProviderForm) throws Exception {
 			
 			ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
 			ArrayList placeDetls = new ArrayList();
 			ArrayList list = objServiceProviderBD.getPlaceDetls(objServiceProviderForm);
 			if (list != null && list.size()>0)
 			{
 				ArrayList rowList;
 				try {
 					for (int i=0; i<list.size();i++)
 					{
 						ServiceProviderDTO objServiceProviderDTO = new ServiceProviderDTO();
 						rowList = (ArrayList)list.get(i);
 						
 						objServiceProviderForm.getObjServiceProviderDTO().setTehsil((String)rowList.get(0));
 						objServiceProviderForm.getObjServiceProviderDTO().setWard((String)rowList.get(1));
 						objServiceProviderForm.getObjServiceProviderDTO().setMohalla((String)rowList.get(2));
 						
 						
 						objServiceProviderDTO.setTehsil((String)rowList.get(0));
 						objServiceProviderDTO.setWard((String)rowList.get(1));
 						objServiceProviderDTO.setMohalla((String)rowList.get(2));
 						
 						placeDetls.add(objServiceProviderDTO);
 					}
 				}catch (Exception e)
 				{
 					e.printStackTrace();
 				}
 			}
 			return placeDetls;
 			
 		}
 		
 		public ArrayList getPreviousLicenseDetailsEstamp1(ServiceProviderForm objServiceProviderForm) throws Exception
 		{
 			ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
 			ArrayList previousLicenseDetails = new ArrayList();
 			ArrayList list = objServiceProviderBD.getPreviousLicenseDetailsEstamp1(objServiceProviderForm);
 			if (list != null && list.size()>0)
 			{
 				ArrayList rowList;
 				try {
 					for (int i=0; i<list.size();i++)
 					{
 						ServiceProviderDTO objServiceProviderDTO = new ServiceProviderDTO();
 						rowList = (ArrayList)list.get(i);
 						
 						
 						objServiceProviderDTO.setBalAmountCreditEstamp(rowList.get(0));
 						
 						objServiceProviderForm.getObjServiceProviderDTO().setBalAmountCreditEstamp(rowList.get(0));
 						objServiceProviderForm.getObjServiceProviderDTO().setHdnBalAmountCreditEstamp(rowList.get(0));
 						objServiceProviderForm.getObjServiceProviderDTO().setPreviousLicenseNoEstamp(rowList.get(1));
 						
 						
 						
 						previousLicenseDetails.add(objServiceProviderDTO);
 					}
 				}catch (Exception e)
 				{
 					e.printStackTrace();
 				}
 			}
 			return previousLicenseDetails;
 			
 		}
 		
 		public ArrayList getPreviousLicenseDetailsOthers1(ServiceProviderForm objServiceProviderForm) throws Exception
 		{
 			ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
 			ArrayList previousLicenseDetails = new ArrayList();
 			ArrayList list = objServiceProviderBD.getPreviousLicenseDetailsOthers1(objServiceProviderForm);
 			if (list != null && list.size()>0)
 			{
 				ArrayList rowList;
 				try {
 					for (int i=0; i<list.size();i++)
 					{
 						ServiceProviderDTO objServiceProviderDTO = new ServiceProviderDTO();
 						rowList = (ArrayList)list.get(i);
 						
 						objServiceProviderDTO.setBalAmountCreditOthers(rowList.get(0));
 						
 						objServiceProviderForm.getObjServiceProviderDTO().setBalAmountCreditOthers(rowList.get(0));
 						objServiceProviderForm.getObjServiceProviderDTO().setHdnBalAmountCreditOthers(rowList.get(0));
 						objServiceProviderForm.getObjServiceProviderDTO().setPreviousLicenseNoOthers(rowList.get(1));
 						
 						previousLicenseDetails.add(objServiceProviderDTO);
 					}
 				}catch (Exception e)
 				{
 					e.printStackTrace();
 				}
 			}
 			return previousLicenseDetails;
 			
 		}
 		
 	//added by shruti---21 july 2014
 		public ArrayList<ServiceProviderDTO>  getArea(String language)throws Exception
 		{
 			ArrayList  areaList=null;
 			ArrayList <ServiceProviderDTO>returnList=null;
 			ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
 			try{
 				areaList=objServiceProviderBD.getArea(language);
 				if(areaList!=null&& areaList.size()>0)
 				{
 					returnList=new ArrayList<ServiceProviderDTO>();
 					for(int i=0;i<areaList.size();i++)
 					{
 						ArrayList subList=(ArrayList) areaList.get(i);
 						ServiceProviderDTO objServiceProviderDTO = new ServiceProviderDTO();
 						objServiceProviderDTO.setAreaId((String) subList.get(0));
 						objServiceProviderDTO.setAreaName((String) subList.get(1));
 						returnList.add(objServiceProviderDTO);	
 					}
 					
 				}
 				
 				return returnList;
 			}catch (Exception e) {
 				return null;
 			}
 		}
 		//end
		
 		public ArrayList<ServiceProviderDTO> getSubArea(String language,
 				String areaId,String tehsilId,String urbanFlag) {
 				ArrayList  subAreaList=null;
 				ArrayList <ServiceProviderDTO>returnList=null;
 				ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
 				try{
 					subAreaList=objServiceProviderBD.getSubArea(language,areaId,tehsilId,urbanFlag);
 					if(subAreaList!=null&& subAreaList.size()>0)
 					{
 						returnList=new ArrayList<ServiceProviderDTO>();
 						for(int i=0;i<subAreaList.size();i++)
 						{
 							ArrayList subList=(ArrayList) subAreaList.get(i);
 							ServiceProviderDTO objServiceProviderDTO = new ServiceProviderDTO();
 							objServiceProviderDTO.setSubAreaId((String) subList.get(0));
 							objServiceProviderDTO.setSubAreaName((String) subList.get(1));
 							returnList.add(objServiceProviderDTO);
 						}
 						
 					}
 					
 					return returnList;
 				}catch (Exception e) {
 					return null;
 				}
 			
 		}
 		
 		public ArrayList<ServiceProviderDTO> getWardPatwari(String language,
 				String subAreaId,String tehsilId) {
 				ArrayList  wardPatwariList=null;
 				ArrayList <ServiceProviderDTO>returnList=null;
 				ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
 			try{
 				wardPatwariList=objServiceProviderBD.getWardPatwari(language,subAreaId,tehsilId);
 				if(wardPatwariList!=null&& wardPatwariList.size()>0)
 				{
 					returnList=new ArrayList<ServiceProviderDTO>();
 					for(int i=0;i<wardPatwariList.size();i++)
 					{
 						ArrayList subList=(ArrayList) wardPatwariList.get(i);
 						ServiceProviderDTO objServiceProviderDTO = new ServiceProviderDTO();
 						objServiceProviderDTO.setWardPatwariId((String) subList.get(0)+"~"+(String) subList.get(2));
 						objServiceProviderDTO.setWardPatwariName((String) subList.get(1));
 						returnList.add(objServiceProviderDTO);
 						
 					}
 					
 				}
 				
 				return returnList;
 			}catch (Exception e) {
 				return null;
 			}
 		}
 		
 		public ArrayList<ServiceProviderDTO> getColonyName(String language,
 				String wardPatwariId) {
 			ArrayList  l1NameList=new ArrayList();
 			ArrayList <ServiceProviderDTO>returnList=null;
 			ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
 			try{
 				l1NameList=objServiceProviderBD.getColonyName(language,wardPatwariId);
 				if(l1NameList!=null&& l1NameList.size()>0)
 				{
 					returnList=new ArrayList<ServiceProviderDTO>();
 					for(int i=0;i<l1NameList.size();i++)
 					{
 						ArrayList subList=(ArrayList) l1NameList.get(i);
 						ServiceProviderDTO objServiceProviderDTO = new ServiceProviderDTO();
 						objServiceProviderDTO.setColonyId((String) subList.get(0)+"~"+(String) subList.get(2)+"~"+(String) subList.get(3));
 						objServiceProviderDTO.setColonyName((String) subList.get(1));
 						returnList.add(objServiceProviderDTO);
 						
 					}
 					
 				}
 				
 				return returnList;
 			}catch (Exception e) {
 				return null;
 			}

 		
 		}
 		
 		//added by shruti--------10 sep 2014
 		public String getSubjectName(String userId) {
 			EstampingBD objEstampingBD = null;
 			try {
 				objEstampingBD = new EstampingBD();
 			} catch (Exception e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			}
 			return objEstampingBD.getSubjectNameBD(userId);
 		}

		public ArrayList getPreviousLicenseDetailsJudicialEstamp1 (
				ServiceProviderForm objServiceProviderForm) throws Exception {
 			ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
 			ArrayList previousLicenseDetails = new ArrayList();
 			ArrayList list = objServiceProviderBD.getPreviousLicenseDetailsEstampJudicial1(objServiceProviderForm);
 			if (list != null && list.size()>0)
 			{
 				ArrayList rowList;
 				try {
 					for (int i=0; i<list.size();i++)
 					{
 						ServiceProviderDTO objServiceProviderDTO = new ServiceProviderDTO();
 						rowList = (ArrayList)list.get(i);
 						
 						objServiceProviderDTO.setBalAmountCreditEstampJudicial((rowList.get(0)));
 						
 						objServiceProviderForm.getObjServiceProviderDTO().setBalAmountCreditEstampJudicial(rowList.get(0));
 						objServiceProviderForm.getObjServiceProviderDTO().setHdnBalAmountCreditEstampJudicial(rowList.get(0));
 						objServiceProviderForm.getObjServiceProviderDTO().setPreviousLicenseNoEstampJudicial(rowList.get(1));
 						
 						previousLicenseDetails.add(objServiceProviderDTO);
 					}
 				}catch (Exception e)
 				{
 					e.printStackTrace();
 				}
 			}
 			return previousLicenseDetails;
 			
 		
		}

		public boolean getRequestNumber(
				ServiceProviderForm objServiceProviderForm) {
			// TODO Auto-generated method stub
			
			ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
			
			boolean getRequestNumber=objServiceProviderBD.getRequestNumber(objServiceProviderForm);
			
			return getRequestNumber;
		}

		public String getNewLicenseNumber(
				ServiceProviderForm objServiceProviderForm) {
			// TODO Auto-generated method stub
			ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
			String newLicenseNumber=objServiceProviderBD.getNewLicenseNumber(objServiceProviderForm);
			return newLicenseNumber;
		}
		
		//santosh
		public boolean insertSpOldDocDetls(ServiceProviderForm objServiceProviderForm) throws Exception
		{
			ServiceProviderBD objServiceProviderBD = new ServiceProviderBD();
			
			boolean inserTxn=objServiceProviderBD.insertSpOldDocDetls(objServiceProviderForm);

			return inserTxn;
		}
	
}
