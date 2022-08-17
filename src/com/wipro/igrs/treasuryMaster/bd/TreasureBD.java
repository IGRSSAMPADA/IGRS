package com.wipro.igrs.treasuryMaster.bd;



import java.sql.SQLException;
import java.util.ArrayList;

import com.wipro.igrs.treasuryMaster.bo.TreasureBO;
import com.wipro.igrs.treasuryMaster.dto.TreasureDTO;
import com.wipro.igrs.treasuryMaster.exception.IllegalTreasuryException;
import com.wipro.igrs.treasuryMaster.form.TreasuryForm;

public class TreasureBD 
{
	private TreasureBO treasureBO;
	public TreasureBD() {
		treasureBO = new TreasureBO();
	}
	
	public void deleteTreasurymaster(String[] id , String updatedBy) throws SQLException,Exception
	{
		treasureBO.deleteTreasurymaster(id,updatedBy);
	}
	
	public void addTreasuryMaster(TreasureDTO treasureDTO) throws SQLException,Exception,IllegalTreasuryException
	{
		treasureBO.addTreasuryMaster(treasureDTO);
	}
	
	public ArrayList getTreasuryList() throws SQLException,Exception
	{
		return treasureBO.getTreasuryList();
	}
	
	public void updateTreasurymaster(TreasureDTO treasureDTO) throws SQLException,Exception,IllegalTreasuryException
	{
		treasureBO.updateTreasurymaster(treasureDTO);
	}
	
	public TreasureDTO getTreasuryId(TreasureDTO treasureDTO) throws SQLException,Exception
	{
		return treasureBO.getTreasuryId(treasureDTO);
	}
	
	public boolean isTreasuryExist(TreasureDTO treasureDTO) throws SQLException,Exception
	{
		return treasureBO.isTreasuryExist(treasureDTO);
	}
}
