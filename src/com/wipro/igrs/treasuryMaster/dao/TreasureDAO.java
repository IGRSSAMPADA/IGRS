package com.wipro.igrs.treasuryMaster.dao;



import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.treasuryMaster.dto.TreasureDTO;
import com.wipro.igrs.treasuryMaster.sql.TreasuryCommonSql;

public class TreasureDAO {

	private ArrayList treasuryList;
	private Logger logger = (Logger) Logger.getLogger(TreasureDAO.class);
    DBUtility dbUtility = null;
    String sql = null;
    ArrayList columnList = null;
    TreasureDTO dto = null;
    /*DAO constructor*/
    public TreasureDAO() 
    {
    
    }
    /* ADD TREASURY MASTER */
    
    public void addTreasurymaster(TreasureDTO treasureDTO) throws SQLException,Exception
    {
    	//System.out.println("starting ..........");
    	logger.debug("starting------------------>");
    	 sql = TreasuryCommonSql.INSERT_TREASURY_MASTER;
    	 String param[] = new String[5];
 		 param[0] = treasureDTO.getTreasuryName();
 		 param[1] = treasureDTO.getTreasuryAddress();
 		 param[2] = treasureDTO.getTreasuryPhone();
 		 param[3] = treasureDTO.getCreatedBy();
 		 param[4] = treasureDTO.getUpdatedBy();
 		
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			boolean boo = dbUtility.executeUpdate(param);
			
			if (boo) {
				dbUtility.commit();
			} 
			else {
				dbUtility.rollback();
			}
			
			//dbUtility.closeConnection();
    }
    
    
    public ArrayList getTreasuryList() throws SQLException,Exception 
    {
    	logger.debug("Starting........");
    	treasuryList = new ArrayList();
    	
			sql = TreasuryCommonSql.SELECT_TREASURY_MASTER;
			dbUtility = new DBUtility();
			dbUtility.createStatement();

			ArrayList rowList = dbUtility.executeQuery(sql);
			for (int i = 0; i < rowList.size(); i++) {
				columnList = (ArrayList) rowList.get(i);
				dto = new TreasureDTO();
				dto.setTreasuryId(columnList.get(0).toString());
				dto.setTreasuryName(columnList.get(1).toString());
				dto.setTreasuryAddress(columnList.get(2).toString());
				dto.setTreasuryPhone(columnList.get(3).toString());
				dto.setStatus(columnList.get(4).toString());
				
				dto.setCreatedBy(columnList.get(5).toString());
				dto.setCreationDate(columnList.get(6).toString());
				dto.setUpdatedBy(columnList.get(7).toString());
				dto.setUpdateDate(columnList.get(8).toString());
				treasuryList.add(dto);
			}
		return treasuryList;
    }
    
    
    public void updateTreasurymaster(TreasureDTO treasureDTO) throws SQLException,Exception
    {

		dto = new TreasureDTO();
		String param[] = new String[6];
		param[0] = treasureDTO.getTreasuryName();
		param[1] = treasureDTO.getTreasuryAddress();
		param[2] = treasureDTO.getTreasuryPhone();
		param[3] = treasureDTO.getStatus();
		param[4] = treasureDTO.getUpdatedBy();
		param[5] = treasureDTO.getTreasuryId();
		
		sql = TreasuryCommonSql.UPDATE_TREASURY_MASTER;

		dbUtility = new DBUtility();
		dbUtility.createPreparedStatement(sql);
			boolean boo = dbUtility.executeUpdate(param);
			
			if (boo) {
				dbUtility.commit();
			}else {
				dbUtility.rollback();
			}	
		
	}
    
    
    public TreasureDTO getTreasuryId(TreasureDTO treasureDTO) throws SQLException,Exception 
    {
		
			sql = TreasuryCommonSql.SELECT_TREASURY_MASTER_ID;
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			String sd[] = new String[1];
			sd[0] = treasureDTO.getTreasuryId();
			ArrayList list = dbUtility.executeQuery(sd);
			ArrayList list1 = (ArrayList) list.get(0);
			dto = new TreasureDTO();
			dto.setTreasuryId((String)list1.get(0));
			dto.setTreasuryName((String)list1.get(1));
			dto.setTreasuryAddress((String)list1.get(2));
			dto.setTreasuryPhone((String)list1.get(3));
			dto.setStatus((String)list1.get(4));

		return dto;
	}

    
    public boolean isTreasuryExist(TreasureDTO treasureDTO) throws SQLException,Exception
    {
    	boolean result = true; 
    	sql = TreasuryCommonSql.SELECT_TREASURY_MASTER_EXIST;
    	
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			String sd[] = new String[1];
			sd[0] = treasureDTO.getTreasuryName();
			ArrayList list = dbUtility.executeQuery(sd);
			if(list.isEmpty())
			{
				result=false;
			}
		
		return result;
    }
    
    public boolean isTreasuryExist(String userId , String name) throws SQLException,Exception
    {
    	boolean result = true; 
    	sql = TreasuryCommonSql.SELECT_TREASURY_MASTER_EXIST_ID;
    	
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			String sd[] = new String[2];
			sd[0] = userId;
			sd[1] = name;
			ArrayList list = dbUtility.executeQuery(sd);
			if(list.isEmpty())
			{
				result=false;
			}
		
		return result;
    }
    
    public void deleteTreasurymaster(String id,String updatedBy) throws SQLException,Exception
    {
    	
			sql = TreasuryCommonSql.DELETE_TREASURY_MASTER;
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			String sd[] = new String[2];
			sd[0] = updatedBy;
			sd[1] = id;
			dbUtility.executeUpdate(sd);
	}
 
}
