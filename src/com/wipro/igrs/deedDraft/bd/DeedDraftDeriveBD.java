package com.wipro.igrs.deedDraft.bd;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.deedDraft.dao.DeedDraftDeriveDAO;
import com.wipro.igrs.deedDraft.dto.DeedDraftDTO;

public class DeedDraftDeriveBD {
	
	private Logger logger = Logger.getLogger(DeedDraftDeriveBD.class);
	DeedDraftDeriveDAO deriveDAO = new DeedDraftDeriveDAO();
	
	public ArrayList getDraftDeedDetailsForDashboard(String userId,String lang) throws Exception
	{
		ArrayList finalList = new ArrayList();
		
		ArrayList list = deriveDAO.getDraftDeedDetailsForDashboard(userId);
		
		for(int i = 0 ;i < list.size() ;i++)
		{
			ArrayList subList = (ArrayList)list.get(i);
			DeedDraftDTO dto = new DeedDraftDTO();
			dto.setDeedId((String)subList.get(0));
			dto.setDeedName((String)subList.get(1));
			
			String createdBy = (String)subList.get(2);
			String createdDate = (String)subList.get(3);
			
			if(createdBy ==null)
			{
				if(lang.equalsIgnoreCase("hi"))
					dto.setDeedType("Yes");
				else
					dto.setDeedType("Yes");
			}
			else
			{
				if(lang.equalsIgnoreCase("hi"))
					dto.setDeedType("No");
				else
					dto.setDeedType("No");
			}
			dto.setDeedDate(createdDate);
			finalList.add(dto);	
		}
		logger.debug("size of list in bd-->"+finalList.size());
		return finalList;
	}
	
	public boolean copyDeedDraftData(String deedIdOld, String userID,DeedDraftDTO ddto) throws Exception
	{
		return deriveDAO.copyDeedDraftData(deedIdOld, userID, ddto);
	}
	public boolean saveDeedDraftPath(DeedDraftDTO ddto, String userID) throws Exception{
		return deriveDAO.saveDeedDraftPath(ddto);
	}
	public String getPageId(String deedId){
		return deriveDAO.getPageId(deedId);
	}
}
