package com.wipro.igrs.partytypemaster.dao;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.partytypemaster.dto.DependenceDTO;
import com.wipro.igrs.partytypemaster.dto.FunctionDTO;
import com.wipro.igrs.partytypemaster.dto.PartyTypeMasterDTO;
import com.wipro.igrs.partytypemaster.sql.PartyTypeSQL;


public class PartyTypeMasterDAO {

	private static Logger log = org.apache.log4j.Logger.getLogger(PartyTypeMasterDAO.class);
    DBUtility dbUtil;
	public PartyTypeMasterDAO(){
	
		try {
			dbUtil = new DBUtility();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public boolean addPartyTypeMaster(PartyTypeMasterDTO partyTypeMasterDTO,String roleId, String funId, String userId) {
        boolean successAdding = false;
        
        try
        {    
        	IGRSCommon igrsCommon = new IGRSCommon();
            dbUtil.createPreparedStatement(PartyTypeSQL.INSERT_PARTY_TYPE);
            String[] param = new String[]{
            	partyTypeMasterDTO.getName(),
            	partyTypeMasterDTO.getDesc(),
            	partyTypeMasterDTO.getStatus(),
            	partyTypeMasterDTO.getCreatedBy(),
            	partyTypeMasterDTO.getUpdateBy(),
            	partyTypeMasterDTO.getFunctionId(),
            	partyTypeMasterDTO.getDependenceId()};
               successAdding = dbUtil.executeUpdate(param);
            if(successAdding)
            {
            dbUtil.commit();  
            igrsCommon.saveLogDet("IGRS_PARTY_TYPE_MASTER","INSERT","T",funId,userId,roleId);
            }
            else {
            	dbUtil.rollback();
            	igrsCommon.saveLogDet("IGRS_PARTY_TYPE_MASTER","INSERT","F",funId,userId,roleId);
            }
        } catch (Exception e){
        	e.printStackTrace();
        	
        } finally{
            try{
               // dbUtil.closeConnection();
            } catch (Exception ex){
            	ex.printStackTrace();
            }
        }
        return successAdding;
    }
	
	public boolean editPartyTypeMaster(PartyTypeMasterDTO partyTypeMasterDTO,String roleId, String funId, String userId) {
        boolean successEditing = false;
        
        try
        {
        	IGRSCommon igrsCommon = new IGRSCommon();
            dbUtil.createPreparedStatement(PartyTypeSQL.UPDATE_PARTY_TYPE);
            String[] param = new String[]{
            	partyTypeMasterDTO.getName(),
            	partyTypeMasterDTO.getDesc(),
            	partyTypeMasterDTO.getStatus(),
            	partyTypeMasterDTO.getUpdateBy(),
            	partyTypeMasterDTO.getFunctionId(),
            	partyTypeMasterDTO.getDependenceId(),
            	partyTypeMasterDTO.getId()};
            
            successEditing = dbUtil.executeUpdate(param);
            if(successEditing)
            {
            dbUtil.commit();
            igrsCommon.saveLogDet("IGRS_PARTY_TYPE_MASTER","UPDATE","T",funId,userId,roleId);
            }
            else {
            	dbUtil.rollback();
            	igrsCommon.saveLogDet("IGRS_PARTY_TYPE_MASTER","UPDATE","F",funId,userId,roleId);
            }
        } catch (Exception e){
        	e.printStackTrace();
        	
        } finally{
            try{
                //dbUtil.closeConnection();
            } catch (Exception ex){
            	ex.printStackTrace();
            }
        }
        return successEditing;
    }
    
	public boolean deletePartyTypeMaster(PartyTypeMasterDTO partyTypeMasterDTO,String roleId, String funId, String userId) {
        boolean successDeleting = false;
        
        try
        {
        	IGRSCommon igrsCommon = new IGRSCommon();
            dbUtil.createPreparedStatement(PartyTypeSQL.CHANGE_STATUS_PARTY_TYPE);
            String[] param = new String[]{
            	partyTypeMasterDTO.getStatus(),
            	partyTypeMasterDTO.getUpdateBy(),
            	partyTypeMasterDTO.getId()};
            
            successDeleting = dbUtil.executeUpdate(param);
            if(successDeleting)
            {
            dbUtil.commit();
            igrsCommon.saveLogDet("IGRS_PARTY_TYPE_MASTER","UPDATE","T",funId,userId,roleId);
            }
            else {
            	dbUtil.rollback();
            	igrsCommon.saveLogDet("IGRS_PARTY_TYPE_MASTER","UPDATE","F",funId,userId,roleId);
            }
        } catch (Exception e){
        	e.printStackTrace();
        	
        } finally{
            try{
                //dbUtil.closeConnection();
            } catch (Exception ex){
            	ex.printStackTrace();
            }
        }
        return successDeleting;
    }
	
	public List getAllPartyTypeMaster(){
		List allParty = new ArrayList();
		PartyTypeMasterDTO partyTypeMasterDTO;
		try {
			dbUtil.createStatement();
			//System.out.println(PartyTypeSQL.SELECT_ALL_PARTY_TYPE);
			ArrayList list = dbUtil.executeQuery(PartyTypeSQL.SELECT_ALL_PARTY_TYPE);
			for (int i = 0; i < list.size(); i++) {
					partyTypeMasterDTO = new PartyTypeMasterDTO();
					partyTypeMasterDTO.setId((String)((ArrayList)list.get(i)).get(0));
					partyTypeMasterDTO.setName((String)((ArrayList)list.get(i)).get(1));
					partyTypeMasterDTO.setDesc((String)((ArrayList)list.get(i)).get(2));
					partyTypeMasterDTO.setStatus((String)((ArrayList)list.get(i)).get(3));
					partyTypeMasterDTO.setCreatedBy((String)((ArrayList)list.get(i)).get(4));
					partyTypeMasterDTO.setCreatedDate((String)((ArrayList)list.get(i)).get(5));
					partyTypeMasterDTO.setUpdateBy((String)((ArrayList)list.get(i)).get(6));
					partyTypeMasterDTO.setUpdateDate((String)((ArrayList)list.get(i)).get(7));
					partyTypeMasterDTO.setFunctionId((String)((ArrayList)list.get(i)).get(8));
					partyTypeMasterDTO.setFunctionName((String)((ArrayList)list.get(i)).get(9));
		 			partyTypeMasterDTO.setDependenceId((String)((ArrayList)list.get(i)).get(10));
					partyTypeMasterDTO.setDependenceName((String)((ArrayList)list.get(i)).get(11));
					allParty.add(partyTypeMasterDTO);
			}
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
            try{
               // dbUtil.closeConnection();
            } catch (Exception ex){
            	ex.printStackTrace();
            }
        }
		
		
		return allParty;
	}
	
	
	public PartyTypeMasterDTO getPartyTypeMasterById(String id){
	
		PartyTypeMasterDTO partyTypeMasterDTO = new PartyTypeMasterDTO();
		try {
			dbUtil.createPreparedStatement(PartyTypeSQL.SELECT_PARTY_TYPE_BY_ID);
			String[] param = new String[]{id};
			ArrayList list = dbUtil.executeQuery(param);
			for (int i = 0; i < list.size(); i++) {	
					partyTypeMasterDTO.setId((String)((ArrayList)list.get(i)).get(0));
					partyTypeMasterDTO.setName((String)((ArrayList)list.get(i)).get(1));
					partyTypeMasterDTO.setDesc((String)((ArrayList)list.get(i)).get(2));
					partyTypeMasterDTO.setStatus((String)((ArrayList)list.get(i)).get(3));
					partyTypeMasterDTO.setCreatedBy((String)((ArrayList)list.get(i)).get(4));
					partyTypeMasterDTO.setCreatedDate((String)((ArrayList)list.get(i)).get(5));
					partyTypeMasterDTO.setUpdateBy((String)((ArrayList)list.get(i)).get(6));
					partyTypeMasterDTO.setUpdateDate((String)((ArrayList)list.get(i)).get(7));
					partyTypeMasterDTO.setFunctionId((String)((ArrayList)list.get(i)).get(8));
					partyTypeMasterDTO.setFunctionName((String)((ArrayList)list.get(i)).get(9));
					partyTypeMasterDTO.setDependenceId((String)((ArrayList)list.get(i)).get(10));
					partyTypeMasterDTO.setDependenceName((String)((ArrayList)list.get(i)).get(11));
			}
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
            try{
               // dbUtil.closeConnection();
            } catch (Exception ex){
            	ex.printStackTrace();
            }
        }
		
		
		return partyTypeMasterDTO;
	}
	
	
	public PartyTypeMasterDTO getPartyTypeMasterByName(PartyTypeMasterDTO masterDTO){
		String name = masterDTO.getName();
		PartyTypeMasterDTO partyTypeMasterDTO = new PartyTypeMasterDTO();
		try {
			dbUtil.createPreparedStatement(PartyTypeSQL.SELECT_PARTY_TYPE_BY_NAME);
			String[] param = new String[]{name};
			ArrayList list = dbUtil.executeQuery(param);
			for (int i = 0; i < list.size(); i++) {	
					partyTypeMasterDTO.setId((String)((ArrayList)list.get(i)).get(0));
					partyTypeMasterDTO.setName((String)((ArrayList)list.get(i)).get(1));
					partyTypeMasterDTO.setDesc((String)((ArrayList)list.get(i)).get(2));
					partyTypeMasterDTO.setStatus((String)((ArrayList)list.get(i)).get(3));
					partyTypeMasterDTO.setCreatedBy((String)((ArrayList)list.get(i)).get(4));
					partyTypeMasterDTO.setCreatedDate((String)((ArrayList)list.get(i)).get(5));
					partyTypeMasterDTO.setUpdateBy((String)((ArrayList)list.get(i)).get(6));
					partyTypeMasterDTO.setUpdateDate((String)((ArrayList)list.get(i)).get(7));
					partyTypeMasterDTO.setFunctionId((String)((ArrayList)list.get(i)).get(8));
					partyTypeMasterDTO.setDependenceId((String)((ArrayList)list.get(i)).get(9));
			}
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
            try{
             //   dbUtil.closeConnection();
            } catch (Exception ex){
            	ex.printStackTrace();
            }
        }
		
		
		return partyTypeMasterDTO;
	}
	
	public boolean isExist(PartyTypeMasterDTO partyTypeMasterDTO){
		boolean exist = false;
		
		if(getPartyTypeMasterByName(partyTypeMasterDTO).getName() != null){
			exist = true;
		}
		
		return exist;
	}
	
	
	
	public List getAllFunctions(){
		List allFunctions = new ArrayList();
		FunctionDTO functionDTO;
		try {
			dbUtil.createStatement();
			//System.out.println(PartyTypeSQL.SELECT_ALL_FUNCTION);
			ArrayList list = dbUtil.executeQuery(PartyTypeSQL.SELECT_ALL_FUNCTION);
			for (int i = 0; i < list.size(); i++) {
				functionDTO = new FunctionDTO();
				functionDTO.setId((String)((ArrayList)list.get(i)).get(0));
				functionDTO.setName((String)((ArrayList)list.get(i)).get(1));
				allFunctions.add(functionDTO);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
            try{
               // dbUtil.closeConnection();
            } catch (Exception ex){
            	ex.printStackTrace();
            }
        }

		return allFunctions;
	}
	
	public List getAllDependences(){
		List allDependences = new ArrayList();
		DependenceDTO dependenceDTO;
		try {
			dbUtil.createStatement();
		//	System.out.println(PartyTypeSQL.SELECT_ALL_DEPENDENCE);
			ArrayList list = dbUtil.executeQuery(PartyTypeSQL.SELECT_ALL_DEPENDENCE);
			for (int i = 0; i < list.size(); i++) {
				dependenceDTO = new DependenceDTO();
				dependenceDTO.setId((String)((ArrayList)list.get(i)).get(0));
				dependenceDTO.setName((String)((ArrayList)list.get(i)).get(1));
				allDependences.add(dependenceDTO);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
            try{
               // dbUtil.closeConnection();
            } catch (Exception ex){
            	ex.printStackTrace();
            }
        }

		return allDependences;
	}
	
	
	public static void main(String[] args) {
		PartyTypeMasterDTO partyTypeMasterDTO = new PartyTypeMasterDTO();
//		partyTypeMasterDTO.setName("beso");
//    	partyTypeMasterDTO.setDesc("beso");
//    	partyTypeMasterDTO.setStatus("A");
//    	partyTypeMasterDTO.setCreatedBy("beso");
//    	partyTypeMasterDTO.setUpdateBy("beso");
//    	partyTypeMasterDTO.setFunctionId("3");
//    	partyTypeMasterDTO.setDependenceId("3");
    	try {
			PartyTypeMasterDAO partyTypeMasterDAO = new PartyTypeMasterDAO();
			//partyTypeMasterDTO.setName("besoName11");
			//partyTypeMasterDAO.addPartyTypeMaster(partyTypeMasterDTO);
			
			List masterDTO = partyTypeMasterDAO.getAllDependences();
			System.out.println(((DependenceDTO)masterDTO.get(0)).getId());
			System.out.println(((DependenceDTO)masterDTO.get(0)).getName());
			
			
//			System.out.println(((PartyTypeMasterDTO)masterDTO.get(0)).getDesc());
//			System.out.println(((PartyTypeMasterDTO)masterDTO.get(0)).getCreatedBy());
//			System.out.println(((PartyTypeMasterDTO)masterDTO.get(0)).getCreatedDate());
//			System.out.println(((PartyTypeMasterDTO)masterDTO.get(0)).getDependenceId());
//			System.out.println(((PartyTypeMasterDTO)masterDTO.get(0)).getDependenceName());
//			System.out.println(((PartyTypeMasterDTO)masterDTO.get(0)).getFunctionName());
		//	System.out.println(partyTypeMasterDAO.isExist(partyTypeMasterDTO));
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
