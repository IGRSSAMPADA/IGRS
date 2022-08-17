/**
 * 
 */
package com.wipro.igrs.castemaster.dao;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import com.wipro.igrs.castemaster.dto.CasteDTO;
import com.wipro.igrs.castemaster.sql.CasteSQL;
import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.db.DBUtility;

/**
 * @author HMOHAM
 *
 */
public class CasteDAO {

	private Logger logger = (Logger) Logger.getLogger(CasteDAO.class);
	private DBUtility dbUtil;
	
	
	
	public CasteDAO() {
		try {
			
			dbUtil = new DBUtility();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public boolean insertCaste(CasteDTO casteDTO, String roleId, String funId, String userId) {
		logger.info("before inserting Caste");
		
		
		
		try {
			IGRSCommon igrsCommon = new IGRSCommon();
			dbUtil.createPreparedStatement(CasteSQL.INSERT_CASTE);
			
			String[] params = new String[] {
				casteDTO.getName(),
				casteDTO.getCreatedBy(),
				casteDTO.getUpdatedBy(),
				casteDTO.getCategory_id() };
			
			boolean updateExecuted = dbUtil.executeUpdate(params);
			if(updateExecuted)
			{
			dbUtil.commit();
			igrsCommon.saveLogDet("IGRS_CASTE_MASTER","INSERT","T",funId,userId,roleId);
			}
			else
			{
			dbUtil.rollback();
			igrsCommon.saveLogDet("IGRS_CASTE_MASTER","INSERT","F",funId,userId,roleId);
			}
			
			return updateExecuted;
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
			
		}
	}

	public List getAllCastes() {
		
		try {
			
			dbUtil.createStatement();
			
			ArrayList result = dbUtil.executeQuery(CasteSQL.GET_ALL_CASTES);
			
			ArrayList casteList = new ArrayList(result.size());
			
			List subList = null;
			for(int i=0; i<result.size(); i++) {
				subList = (List)result.get(i);
				
				CasteDTO caste = new CasteDTO();
				int indx = 0;
				
				caste.setId((String)subList.get(indx++));
				caste.setName((String)subList.get(indx++));
				caste.setCreatedBy((String)subList.get(indx++));
				caste.setUpdatedBy((String)subList.get(indx++));
				caste.setCategory_id((String)subList.get(indx++));
				
				caste.setStatus( (String)subList.get(indx++) );
				caste.setCategoryName( (String)subList.get(indx++) );
				caste.setCreatedDate( (String)subList.get(indx++) );
				caste.setUpdatedDate( (String)subList.get(indx++) );
					
				
				
				casteList.add(caste);
					
				
			}
			
			return casteList;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Collections.EMPTY_LIST;
		}
		
		
	}
	
	public CasteDTO getCasteById(String id) {
		
		try {
			dbUtil.createPreparedStatement(CasteSQL.GET_BY_ID);
			
			ArrayList results = dbUtil.executeQuery( new String[] {id} );
			
			if(results.isEmpty())
				return null;
			
			ArrayList subList = (ArrayList)results.get(0);
			
			CasteDTO casteDTO = new CasteDTO();
			int indx = 0;
			
			
			casteDTO.setId((String)subList.get(indx++));
			casteDTO.setName((String)subList.get(indx++));
			casteDTO.setCreatedBy((String)subList.get(indx++));
			casteDTO.setUpdatedBy((String)subList.get(indx++));
			casteDTO.setCategory_id((String)subList.get(indx++));
			
				
			casteDTO.setStatus( (String)subList.get(indx++) );
			casteDTO.setCategoryName( (String)subList.get(indx++) );
			casteDTO.setCreatedDate( (String)subList.get(indx++) );
			casteDTO.setUpdatedDate( (String)subList.get(indx++) );
				
			
			
			return casteDTO;
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * @param casteId the id of the cast to be deleted (soft deletion)
	 * @param userId the id of the user who deleted this Caste 
	 * @param userId2 
	 * @param funId 
	 * @param roleId 
	 * @return
	 */
	public boolean deleteCaste(String casteId, String userId, String roleId, String funId, String userId2) {
		
		
		try {
			IGRSCommon  igrsCommon = new IGRSCommon();
			dbUtil.createPreparedStatement(CasteSQL.DELETE_CASTE);
			
			String params[] = new String[] {
					userId,
					casteId,
			};
			
			
			boolean updateExecuted = dbUtil.executeUpdate(params);
			
			if(updateExecuted)
			{
				dbUtil.commit();
			igrsCommon.saveLogDet("IGRS_CASTE_MASTER","UPDATE","T",funId,userId,roleId);
			}
			else
			{
				dbUtil.rollback();
			igrsCommon.saveLogDet("IGRS_CASTE_MASTER","UPDATE","T",funId,userId,roleId);
			}
			
			return updateExecuted;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean updateCaste(CasteDTO newCasteDTO,  String roleId, String funId,String userId) {
		
		try {
			
			IGRSCommon igrsCommon = new IGRSCommon();
			dbUtil.createPreparedStatement(CasteSQL.UPDATE_CASTE);
			
			String params[] = new String[] {
					userId,
					newCasteDTO.getName(),
					newCasteDTO.getStatus(),
					newCasteDTO.getCategory_id(),
					newCasteDTO.getId(),
			};
			
			boolean updateExecuted = dbUtil.executeUpdate(params);
			
			if(updateExecuted) {
				dbUtil.commit();
				igrsCommon.saveLogDet("IGRS_CASTE_MASTER","UPDATE","T",funId,userId,roleId);
			}
			else {
				
				dbUtil.rollback();
				igrsCommon.saveLogDet("IGRS_CASTE_MASTER","UPDATE","F",funId,userId,roleId);
			}
			
			return updateExecuted;
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public CasteDTO getCasteByName(String name) {
		try {
			dbUtil.createPreparedStatement(CasteSQL.GET_BY_NAME);
			
			ArrayList results = dbUtil.executeQuery( new String[] {name} );
			
			if(results.isEmpty())
				return null;
			
			ArrayList subList = (ArrayList)results.get(0);
			
			CasteDTO casteDTO = new CasteDTO();
			int indx = 0;
			
			
			casteDTO.setId((String)subList.get(indx++));
			casteDTO.setName((String)subList.get(indx++));
			casteDTO.setCreatedBy((String)subList.get(indx++));
			casteDTO.setUpdatedBy((String)subList.get(indx++));
			casteDTO.setCategory_id((String)subList.get(indx++));
				
				
			casteDTO.setStatus((String)subList.get(indx++));
			casteDTO.setCreatedDate( (String)subList.get(indx++) );
			casteDTO.setUpdatedDate( (String)subList.get(indx++) );
				
			
			
			return casteDTO;
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean isCasteExist(String name) {
		return (getCasteByName(name) != null);
	}
	
	
	
	
}
