package com.wipro.igrs.deedparammapping.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.deedparammapping.dto.DeedExemptionMasterDTO;
import com.wipro.igrs.deedparammapping.dto.DeedInstrumentMasterDTO;
import com.wipro.igrs.deedparammapping.dto.DeedParamDTO;
import com.wipro.igrs.deedparammapping.dto.DeedParamMasterDTO;
import com.wipro.igrs.deedparammapping.dto.DeedTypeMasterDTO;
import com.wipro.igrs.deedparammapping.sql.DeedParamSQL;


public class DeedParamDAO {

	private static Logger log = org.apache.log4j.Logger.getLogger(DeedParamDAO.class);
	private DBUtility utility;
	
	public DeedParamDAO(){
	
		try {
			utility = new DBUtility();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public List getAllDeedTypeMaster(){
		List allDeedTypeMaster = new ArrayList();
		DeedTypeMasterDTO deedTypeMasterDTO;
		
		try {
			utility.createStatement();
			ArrayList list = utility.executeQuery(DeedParamSQL.SELECT_ALL_DEED_TYPE_MASTER);
			for (int i = 0; i < list.size(); i++) {
				deedTypeMasterDTO = new DeedTypeMasterDTO();
				deedTypeMasterDTO.setId((String)((ArrayList)list.get(i)).get(0));
				deedTypeMasterDTO.setName((String)((ArrayList)list.get(i)).get(1));
				allDeedTypeMaster.add(deedTypeMasterDTO);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		
		return allDeedTypeMaster;
	}
	
	public List getAllDeedParamMaster(){
		List allDeedParamMaster = new ArrayList();
		DeedParamMasterDTO deedParamMasterDTO;
		
		try {
			utility.createStatement();
			ArrayList list = utility.executeQuery(DeedParamSQL.SELECT_ALL_DEED_PARAM_MASTER);
			for (int i = 0; i < list.size(); i++) {
				deedParamMasterDTO = new DeedParamMasterDTO();
				deedParamMasterDTO.setId((String)((ArrayList)list.get(i)).get(0));
				deedParamMasterDTO.setName((String)((ArrayList)list.get(i)).get(1));
				allDeedParamMaster.add(deedParamMasterDTO);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		
		return allDeedParamMaster;
	}
	
	public List getAllDeedInstrument(){
		List allDeedDeedInstrument = new ArrayList();
		DeedInstrumentMasterDTO  deedInstrumentMasterDTO;
		
		try {
			utility.createStatement();
			ArrayList list = utility.executeQuery(DeedParamSQL.SELECT_ALL_DEED_INSTRUMENT);
			for (int i = 0; i < list.size(); i++) {
				deedInstrumentMasterDTO = new DeedInstrumentMasterDTO();
				deedInstrumentMasterDTO.setId((String)((ArrayList)list.get(i)).get(0));
				deedInstrumentMasterDTO.setName((String)((ArrayList)list.get(i)).get(1));
				allDeedDeedInstrument.add(deedInstrumentMasterDTO);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return allDeedDeedInstrument;
	}
	
	public List getAllDeedExemption(){
		List allDeedExemption = new ArrayList();
		DeedExemptionMasterDTO deedExemptionMasterDTO;
		
		try {
			utility.createStatement();
			ArrayList list = utility.executeQuery(DeedParamSQL.SELECT_ALL_DEED_EXEMPTION);
			for (int i = 0; i < list.size(); i++) {
				deedExemptionMasterDTO = new DeedExemptionMasterDTO();
				deedExemptionMasterDTO.setId((String)((ArrayList)list.get(i)).get(0));
				deedExemptionMasterDTO.setName((String)((ArrayList)list.get(i)).get(1));
				allDeedExemption.add(deedExemptionMasterDTO);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return allDeedExemption;
	}
	
	public List getDeedInstrumentByDeedTypeId(String id){
		List allDeedDeedInstrument = new ArrayList();
		DeedInstrumentMasterDTO  deedInstrumentMasterDTO;
		
		try {
			
			utility.createStatement();
			//String[] param = new String[]{id};
			ArrayList list = utility.executeQuery(DeedParamSQL.SELECT_DEED_INSTRUMENT_BY_DEED_TYPR_ID);
			
			for (int i = 0; i < list.size(); i++) {
				deedInstrumentMasterDTO = new DeedInstrumentMasterDTO();
				deedInstrumentMasterDTO.setId((String)((ArrayList)list.get(i)).get(0));
				deedInstrumentMasterDTO.setName((String)((ArrayList)list.get(i)).get(1));
				allDeedDeedInstrument.add(deedInstrumentMasterDTO);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return allDeedDeedInstrument;
	}
	
	public List getExemptionsByInstrumentId(String id){
		List allDeedExemption = new ArrayList();
		DeedExemptionMasterDTO deedExemptionMasterDTO;
		
		try {
			
			utility.createPreparedStatement(DeedParamSQL.SELECT_DEED_EXEMPTION_BY_INSTRUMENT_ID);
			String[] param = new String[]{id};
			ArrayList list = utility.executeQuery(param);
			
			for (int i = 0; i < list.size(); i++) {
				deedExemptionMasterDTO = new DeedExemptionMasterDTO();
				deedExemptionMasterDTO.setId((String)((ArrayList)list.get(i)).get(0));
				deedExemptionMasterDTO.setName((String)((ArrayList)list.get(i)).get(1));
				allDeedExemption.add(deedExemptionMasterDTO);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return allDeedExemption;
	}
	
	public List getDeedExemptionByDeedTypeId(String id){
		List allDeedExemption = new ArrayList();
		DeedExemptionMasterDTO deedExemptionMasterDTO;
		
		try {
			
			utility.createStatement();
			//String[] param = new String[]{id};
			ArrayList list = utility.executeQuery(DeedParamSQL.SELECT_DEED_EXEMPTION_BY_DEED_TYPR_ID);
			
			for (int i = 0; i < list.size(); i++) {
				deedExemptionMasterDTO = new DeedExemptionMasterDTO();
				deedExemptionMasterDTO.setId((String)((ArrayList)list.get(i)).get(0));
				deedExemptionMasterDTO.setName((String)((ArrayList)list.get(i)).get(1));
				allDeedExemption.add(deedExemptionMasterDTO);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return allDeedExemption;
	}
	
	public List getAllDeedParamMapping(){
		List allDeedParamMapping = new ArrayList();
		DeedParamDTO deedParamDTO;
		
		try {
			utility.createStatement();
			ArrayList list = utility.executeQuery(DeedParamSQL.SELECT_DEED_PARAM_MAPPING);
			for (int i = 0; i < list.size(); i++) {
				deedParamDTO = new DeedParamDTO();
				deedParamDTO.setDeedTypeId((String)((ArrayList)list.get(i)).get(0));
				deedParamDTO.setInstrumentId((String)((ArrayList)list.get(i)).get(1));
				deedParamDTO.setExeptionId((String)((ArrayList)list.get(i)).get(2));
				deedParamDTO.setStatus((String)((ArrayList)list.get(i)).get(3));
				deedParamDTO.setCreatedBy((String)((ArrayList)list.get(i)).get(4));
				deedParamDTO.setCreateDate((String)((ArrayList)list.get(i)).get(5));
				deedParamDTO.setUpdateBy((String)((ArrayList)list.get(i)).get(6));
				deedParamDTO.setUpdateDate((String)((ArrayList)list.get(i)).get(7));
				deedParamDTO.setId((String)((ArrayList)list.get(i)).get(8));
				deedParamDTO.setDeedTypeName((String)((ArrayList)list.get(i)).get(9));
				deedParamDTO.setInstrumentName((String)((ArrayList)list.get(i)).get(10));
				deedParamDTO.setExeptionName((String)((ArrayList)list.get(i)).get(11));
				deedParamDTO.setDeedParamMasterId((String)((ArrayList)list.get(i)).get(12));
				deedParamDTO.setDeedParamMasterName((String)((ArrayList)list.get(i)).get(13));
				allDeedParamMapping.add(deedParamDTO);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		
		return allDeedParamMapping;
	}
	
	public static void main(String[] args) {
		
		DeedParamDAO deedParamDAO = new DeedParamDAO();
		DeedParamDTO deedParamMapping = deedParamDAO.getDeedParamMappingById("21DPMAP");
		//System.out.println(deedParamMapping.getDeedParamMasterName());
		deedParamMapping.setStatus("A");
		deedParamMapping.setExeptionId("3");
		deedParamMapping.setDeedTypeId("1");
		deedParamMapping.setInstrumentId("1");
		deedParamMapping.setDeedParamMasterId("2");
		boolean b = deedParamDAO.updateMapTablesToDeepParam(deedParamMapping);
		
		//System.out.println(b);
		//System.out.println("hiiiiiiiiiiiiiiiiiiiiiiiii");
		
	}
	
	public DeedParamDTO getDeedParamMappingById(String id){
		
		DeedParamDTO deedParamDTO = null;
		
		try {
			
			utility.createPreparedStatement(DeedParamSQL.SELECT_DEED_PARAM_MAPPING_BY_ID);
			String[] param = new String[]{id};
			ArrayList list = utility.executeQuery(param);
			
			for (int i = 0; i < list.size(); i++) {
				deedParamDTO = new DeedParamDTO();
				deedParamDTO.setDeedTypeId((String)((ArrayList)list.get(i)).get(0));
				deedParamDTO.setInstrumentId((String)((ArrayList)list.get(i)).get(1));
				deedParamDTO.setExeptionId((String)((ArrayList)list.get(i)).get(2));
				deedParamDTO.setStatus((String)((ArrayList)list.get(i)).get(3));
				deedParamDTO.setCreatedBy((String)((ArrayList)list.get(i)).get(4));
				deedParamDTO.setCreateDate((String)((ArrayList)list.get(i)).get(5));
				deedParamDTO.setUpdateBy((String)((ArrayList)list.get(i)).get(6));
				deedParamDTO.setUpdateDate((String)((ArrayList)list.get(i)).get(7));
				deedParamDTO.setDeedTypeName((String)((ArrayList)list.get(i)).get(8));
				deedParamDTO.setInstrumentName((String)((ArrayList)list.get(i)).get(9));
				deedParamDTO.setExeptionName((String)((ArrayList)list.get(i)).get(10));
				deedParamDTO.setDeedParamMasterId((String)((ArrayList)list.get(i)).get(11));
				deedParamDTO.setDeedParamMasterName((String)((ArrayList)list.get(i)).get(12));
				deedParamDTO.setId(id);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		
		return deedParamDTO;
	}
	
	public boolean isExist(DeedParamDTO deedParamDTO){
		boolean b = false;
		
		try {
			utility.createPreparedStatement(DeedParamSQL.SELECT_DEED_PARAM_MAPPING_BY_COMPOSITE_KEY);
			String[] param = new String[]{
				deedParamDTO.getDeedTypeId(),
				deedParamDTO.getInstrumentId(),
				deedParamDTO.getExeptionId(),
				deedParamDTO.getDeedParamMasterId()};
			
			ArrayList list = utility.executeQuery(param);
			
			if(list.isEmpty()){
				b = false;
			}
			else{
				b = true;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return b;
	}
	
	public boolean mapTablesToDeepParam(DeedParamDTO deedParamDTO){
		boolean b = false;
		
		try {
			utility.createPreparedStatement(DeedParamSQL.INSERT_DEED_PARAM_MAPPING);
			
			  String[] param = new String[]{
	            		deedParamDTO.getDeedTypeId(),
	            		deedParamDTO.getInstrumentId(),
	            		deedParamDTO.getExeptionId(),
	            		deedParamDTO.getDeedParamMasterId(),
	            		deedParamDTO.getStatus()};
			 // System.out.println(DeedParamSQL.INSERT_DEED_PARAM_MAPPING);
	            b = utility.executeUpdate(param);
	            utility.commit();  
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return b;
	}
	
	
	
	public boolean updateMapTablesToDeepParam(DeedParamDTO deedParamDTO){
		boolean b = false;
		
		try {
			utility.createPreparedStatement(DeedParamSQL.UPDATE_DEED_PARAM_MAPPING);
			
			  String[] param = new String[]{
	            		deedParamDTO.getDeedTypeId(),
	            		deedParamDTO.getInstrumentId(),
	            		deedParamDTO.getExeptionId(),
	            		deedParamDTO.getDeedParamMasterId(),
	            		deedParamDTO.getStatus(),
	            		deedParamDTO.getId()};
			  
	            b = utility.executeUpdate(param);
	            utility.commit();  
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return b;
	}
	
	
	public boolean deleteMapTablesToDeepParam(DeedParamDTO deedParamDTO){
		boolean b = false;
		try {
			utility.createPreparedStatement(DeedParamSQL.DELETE_DEED_PARAM_MAPPING);
			
			  String[] param = new String[]{
					  deedParamDTO.getStatus(),
					  deedParamDTO.getId()};
			  
	            b = utility.executeUpdate(param);
	            utility.commit();  
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return b;
	}
	
}
