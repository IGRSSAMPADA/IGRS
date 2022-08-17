package com.wipro.igrs.guideline.bd;

import java.util.ArrayList;

import com.wipro.igrs.guideline.dao.GuidelineDraftToFinalDAO;

public class GuidelineDraftToFinalBD {
	
	GuidelineDraftToFinalDAO guideDAO = new GuidelineDraftToFinalDAO(); 
	
	public ArrayList getDistrictList() {
		return guideDAO.getDistrictList();
	}
	
	public ArrayList getFinancialYearList()
	{
		return guideDAO.getFinancialYearList();
	}
	
	public ArrayList showDraftStatusChecker( String finan,String  district)
	{
		return guideDAO.showDraftStatusChecker(finan, district);
	}
	

}
