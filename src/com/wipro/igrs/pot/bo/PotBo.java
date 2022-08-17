package com.wipro.igrs.pot.bo;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.wipro.igrs.caveats.bd.CaveatsDelegate;
import com.wipro.igrs.caveats.dao.CaveatsDAO;
import com.wipro.igrs.caveats.dto.CaveatsDTO;
import com.wipro.igrs.pot.bd.PotBD;
import com.wipro.igrs.pot.dao.PotDAO;
import com.wipro.igrs.pot.dto.potDTO;
import com.wipro.igrs.pot.form.PotFORM;

public class PotBo
{
	
	public ArrayList getDistrictNames(String language) throws Exception
	{
		PotDAO dao = new PotDAO();
		return dao.getDistrictNames(language);
	}
	public ArrayList getDistrictId(String language) throws Exception
	{
		PotDAO dao = new PotDAO();
		return dao.getDistrictId(language);
	}
	public boolean potCreateOld22(String param2[]) throws Exception
	{
		PotDAO dao = new PotDAO();
		return dao.potCreateOld22(param2);
	}
	public boolean potCreateOld(String param11[]) throws Exception
	{
		PotDAO dao = new PotDAO();
		return dao.potCreateOld(param11);
	}
	public boolean potCreate(String param1[]) throws Exception
	{
		PotDAO dao = new PotDAO();
		return dao.potCreate(param1);
	}
	public ArrayList getPotDetails(String potId) throws Exception
	{
		PotDAO dao = new PotDAO();
		return dao.getPotDetails(potId);
	}
	public ArrayList getPotPhysicalList(String txnId) throws Exception
	{
		PotDAO dao = new PotDAO();
		return dao.getPotPhysicalList(txnId);
	}
	public boolean potUpdate1(String param[]) throws Exception
	{
		PotDAO dao = new PotDAO();
		return dao.potUpdate1(param);
	}
	public boolean potUpdate2(String param2[]) throws Exception
	{
		PotDAO dao = new PotDAO();
		return dao.potUpdate2(param2);
	}
	public ArrayList getPotComments(String txnId) throws Exception
	{
		PotDAO dao = new PotDAO();
		return dao.getPotComments(txnId);
	}
	public ArrayList getPotList(String param[],String language) throws Exception
	{
		PotDAO dao = new PotDAO();
		return dao.getPotList(param,language);
	}
	public ArrayList getPotListUp(String param[],String language) throws Exception
	{
		PotDAO dao = new PotDAO();
		return dao.getPotListUp(param,language);
	}
	public ArrayList getPotList2(String durationFrom,String durationTo,String dis,String language) throws Exception
	{
		PotDAO dao = new PotDAO();
		return dao.getPotList2(durationFrom,durationTo,dis,language);
	}
	//
	public ArrayList getPotList3(String durationFrom,String durationTo, String userId, String language) throws Exception
	{
		PotDAO dao = new PotDAO();
		return dao.getPotList3(durationFrom,durationTo,userId,language);
	}
	public ArrayList searchRegIdBO(String _regNo) throws Exception {
				PotDAO refDAO = new PotDAO();
		try {
			return refDAO.searchRegIdDao(_regNo);
		} catch (Exception e) {
			
			return null;
		}
	}

	public ArrayList searchRegIdBO1(String _regNo) throws Exception {
		PotDAO refDAO = new PotDAO();
try {
	return refDAO.searchRegIdDao1(_regNo);
} catch (Exception e) {
	
	return null;
}
}
//
	
	public ArrayList getPendingApps(String userId) throws Exception
	{
		
		
		 PotBD objBD = new PotBD();
		ArrayList pendingAppListFinal = new ArrayList();
		ArrayList list=objBD.getPendingApps(userId);
		
		if(list!=null && list.size()>0){
			
			ArrayList rowList;
			
	        try{
			for (int i = 0; i < list.size(); i++) {
				
				potDTO objDTO = new potDTO();
				
				rowList = (ArrayList)list.get(i);
				//objDashBoardDTO = new DashBoardDTO();
			
				//if(rowList.get(0).toString().length()==11){
				
				objDTO.setRegNumber(rowList.get(0).toString());
				
				objDTO.setReportId(rowList.get(1).toString());
				objDTO.setCaseNum(rowList.get(2).toString());
				objDTO.setCaseStat(rowList.get(3).toString());
				
				pendingAppListFinal.add(objDTO);
		
			}
				
			
				
			}
	        catch(Exception e){
	        	e.printStackTrace();
	        	
	        }
		}
		return pendingAppListFinal;
	}

	///
	
	public ArrayList getPotNameBO(String userId) throws Exception {
		PotDAO refDAO = new PotDAO();
try {
	return refDAO.getPotNameDAO(userId);
} catch (Exception e) {
	
	return null;
}
}
	///

}
