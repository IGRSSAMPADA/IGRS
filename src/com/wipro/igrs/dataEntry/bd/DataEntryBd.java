package com.wipro.igrs.dataEntry.bd;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.wipro.igrs.budgetRequest.dao.BudgetDAO;
import com.wipro.igrs.dataEntry.bo.DataEntryBo;
import com.wipro.igrs.dataEntry.dto.DataEntryDto;
import com.wipro.igrs.dataEntry.form.DataEntryForm;
import com.wipro.igrs.pot.dao.PotDAO;

public class DataEntryBd 
{
    
    public ArrayList getDeedList() throws Exception
	{
	DataEntryBo bo = new DataEntryBo();
	return bo.getDeedList();
	}
    
    public ArrayList getDistrictNames() throws Exception
	{
		DataEntryBo bo = new DataEntryBo();
		return bo.getDistrictNames();
	}
    
   public boolean getAdoptionDetails(DataEntryDto dto,String date,String dist) throws Exception
   {
       DataEntryBo bo = new DataEntryBo();
       return bo.getAdoptionDetails(dto,date,dist);
       
   }
   
   public ArrayList getPhysicalStamp(String temp) throws Exception
   {
       DataEntryBo bo = new DataEntryBo();
       return bo.getPhysicalStamp(temp);
   }

}
