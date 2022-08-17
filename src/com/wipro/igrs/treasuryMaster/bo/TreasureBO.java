package com.wipro.igrs.treasuryMaster.bo;



import java.sql.SQLException;
import java.util.ArrayList;

import com.wipro.igrs.treasuryMaster.dao.TreasureDAO;
import com.wipro.igrs.treasuryMaster.dto.TreasureDTO;
import com.wipro.igrs.treasuryMaster.exception.IllegalTreasuryException;
import com.wipro.igrs.treasuryMaster.form.TreasuryForm;


public class TreasureBO {

	private TreasureDAO treasureDAO;
	
	public TreasureBO()
	{
		treasureDAO = new TreasureDAO();
	}
	
	public void deleteTreasurymaster(String[] id , String updatedBy) throws SQLException,Exception
	{
		for (int i = 0; i < id.length; i++) {
			treasureDAO.deleteTreasurymaster(id[i],updatedBy);
		}
		
	}
	
	public void addTreasuryMaster(TreasureDTO treasureDTO) throws SQLException,Exception,IllegalTreasuryException
	{
		if(treasureDAO.isTreasuryExist(treasureDTO))
			throw new IllegalTreasuryException();
		else
			treasureDAO.addTreasurymaster(treasureDTO);
		
	}
	
	public ArrayList getTreasuryList() throws SQLException,Exception
	{
		ArrayList treasuryList=null;
		
			treasuryList = treasureDAO.getTreasuryList();
		return treasuryList;
	}
	
	public void updateTreasurymaster(TreasureDTO treasureDTO) throws SQLException,Exception,IllegalTreasuryException
	{
		
		if(treasureDAO.isTreasuryExist(treasureDTO) && !(treasureDAO.isTreasuryExist(treasureDTO.getTreasuryId(),treasureDTO.getTreasuryName())))
		{
			throw new IllegalTreasuryException();
		}	
		else
			treasureDAO.updateTreasurymaster(treasureDTO);
			
	}
	
	public TreasureDTO getTreasuryId(TreasureDTO treasureDTO) throws SQLException,Exception
	{
		TreasureDTO treasury = null;
		treasury = treasureDAO.getTreasuryId(treasureDTO);
		
		return treasury;
	}
	
	 public boolean isTreasuryExist(TreasureDTO treasureDTO) throws SQLException,Exception
	 {
		 return treasureDAO.isTreasuryExist(treasureDTO);
	 }
	
}
