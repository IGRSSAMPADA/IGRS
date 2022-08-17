/**
 * 
 */
package com.wipro.igrs.officearea.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.mohallavillagemaster.dto.MohallavillageDTO;
import com.wipro.igrs.officearea.dto.DistrictDTO;
import com.wipro.igrs.officearea.dto.MohallaVillageDTO;
import com.wipro.igrs.officearea.dto.OfficeAreaMappingDTO;
import com.wipro.igrs.officearea.dto.OfficeDTO;
import com.wipro.igrs.officearea.dto.TehsilDTO;
import com.wipro.igrs.officearea.dto.WardPatwariDTO;
import com.wipro.igrs.officearea.sql.OfficeAreaMappingSQL;

/**
 * @author HMOHAM
 *
 */
public class OfficeAreaMappingDAO implements IOfficeAreaMappingDAO{

	DBUtility dbUtility;
	
	public OfficeAreaMappingDAO() {
		try {
			dbUtility = new DBUtility();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public OfficeAreaMappingDTO getByOfficeId(String OfficeId) {
		try {
			dbUtility.createPreparedStatement(OfficeAreaMappingSQL.SELECT_OFFICE_MAPPING_BY_ID);
			
			
			
			String params[] = new String[] {
				OfficeId
			};
			
			List resultList = dbUtility.executeQuery(params);
			
			if(!resultList.isEmpty()) {
				List subList = (List)resultList.get(0);
				
				OfficeAreaMappingDTO areaMappingDTO = new OfficeAreaMappingDTO();
				
				int index = 0;
				areaMappingDTO.setOfficeId( (String)subList.get(index++) );
				areaMappingDTO.setDistrictId( (String)subList.get(index++) );
				areaMappingDTO.setTehsilId( (String)subList.get(index++) );
				areaMappingDTO.setWardPatwariId( (String)subList.get(index++) );
				areaMappingDTO.setMohallaVillageId( (String)subList.get(index++));
				
				areaMappingDTO.setOfficeName( (String)subList.get(index++) );
				areaMappingDTO.setDistrictName( (String)subList.get(index++) );
				areaMappingDTO.setTehsilName( (String)subList.get(index++) );
				areaMappingDTO.setWardPatwariName( (String)subList.get(index++) );
				areaMappingDTO.setMohallaVillageName( (String)subList.get(index++) );
				
				return areaMappingDTO;
			}
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	public List getDistricts() {
		try {
			dbUtility.createStatement();
			
			List resultList = dbUtility.executeQuery(OfficeAreaMappingSQL.SELECT_DISTRICTS);
			
			
			ArrayList districtList = new ArrayList(resultList.size());
			List subList = null;
			
			for(int i=0; i<resultList.size(); i++) {
				 DistrictDTO districtDTO = new DistrictDTO();
				 subList = (List)resultList.get(i);
				 
				 int indx = 0;
				 districtDTO.setId( (String)subList.get(indx++) );
				 districtDTO.setName( (String)subList.get(indx++) );
				 
				 districtList.add(districtDTO);
				 
			}
			
			return districtList;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Collections.EMPTY_LIST;
	}

	public List getMohalaVilliges(String wardPatwariId) {
		try {
			dbUtility.createPreparedStatement(OfficeAreaMappingSQL.SELECT_MOHALLA_VILLIGES);
			
			String params[] = new String[] {
				wardPatwariId
			};
			
			List resultList = dbUtility.executeQuery(params);
			
			
			ArrayList thesilList = new ArrayList(resultList.size());
			List subList = null;
			
			for(int i=0; i<resultList.size(); i++) {
				 MohallaVillageDTO mohallaVilligeDTO = new MohallaVillageDTO();
				 subList = (List)resultList.get(i);
				 
				 int indx = 0;
				 mohallaVilligeDTO.setId( (String)subList.get(indx++) );
				 mohallaVilligeDTO.setName( (String)subList.get(indx++) );
				 
				 thesilList.add(mohallaVilligeDTO);
				 
			}
			
			return thesilList;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Collections.EMPTY_LIST;
	}

	public List getOffices() {
		try {
			dbUtility.createStatement();
			
			List resultList = dbUtility.executeQuery(OfficeAreaMappingSQL.SELECT_OFFICES);
			
			
			ArrayList officeList = new ArrayList(resultList.size());
			List subList = null;
			
			for(int i=0; i<resultList.size(); i++) {
				OfficeDTO officeDTO = new OfficeDTO();
				 subList = (List)resultList.get(i);
				 
				 int indx = 0;
				 officeDTO.setId( (String)subList.get(indx++) );
				 officeDTO.setName( (String)subList.get(indx++) );
				 
				 officeList.add(officeDTO);
				 
			}
			
			return officeList;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Collections.EMPTY_LIST;
	}

	public List getTehsils(String districtId) {
		try {
			dbUtility.createPreparedStatement(OfficeAreaMappingSQL.SELECT_TEHSILS);
			
			String params[] = new String[] {
				districtId
			};
			
			List resultList = dbUtility.executeQuery(params);
			
			
			ArrayList thesilList = new ArrayList(resultList.size());
			List subList = null;
			
			for(int i=0; i<resultList.size(); i++) {
				 TehsilDTO thesilDTO = new TehsilDTO();
				 subList = (List)resultList.get(i);
				 
				 int indx = 0;
				 thesilDTO.setId( (String)subList.get(indx++) );
				 thesilDTO.setName( (String)subList.get(indx++) );
				 
				 thesilList.add(thesilDTO);
				 
			}
			
			return thesilList;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Collections.EMPTY_LIST;
	}

	public List getWardPatwaris(String tehsilId) {
		try {
			dbUtility.createPreparedStatement(OfficeAreaMappingSQL.SELECT_WARD_PATWARIS);
			
			String params[] = new String[] {
				tehsilId
			};
			
			List resultList = dbUtility.executeQuery(params);
			
			
			ArrayList thesilList = new ArrayList(resultList.size());
			List subList = null;
			
			for(int i=0; i<resultList.size(); i++) {
				 WardPatwariDTO wardPatwariDTO = new WardPatwariDTO();
				 subList = (List)resultList.get(i);
				 
				 int indx = 0;
				 wardPatwariDTO.setId( (String)subList.get(indx++) );
				 wardPatwariDTO.setName( (String)subList.get(indx++) );
				 
				 thesilList.add(wardPatwariDTO);
				 
			}
			
			return thesilList;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Collections.EMPTY_LIST;
	}

	public void insertOfficeAreaMapping(OfficeAreaMappingDTO areaDTO) {
		try {
			dbUtility.createPreparedStatement(OfficeAreaMappingSQL.INSERT_OFFICE_MAPPING);
			
			
			
			String params[] = new String[] {
				areaDTO.getOfficeId(),
				areaDTO.getDistrictId(),
				areaDTO.getTehsilId(),
				areaDTO.getWardPatwariId(),
				areaDTO.getMohallaVillageId(),
			};
			
			boolean updateExecuted = dbUtility.executeUpdate(params);
			
			if(updateExecuted)
				dbUtility.commit();
			else {
				// TODO Throw an exception (could not insert the officeArea Mapping)
				dbUtility.rollback();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void updateOfficeAreaMapping(OfficeAreaMappingDTO areaDTO) {
		try {
			dbUtility.createPreparedStatement(OfficeAreaMappingSQL.UPDATE_OFFICE_MAPPING);
			
			
			String params[] = new String[] {
				areaDTO.getDistrictId(),
				areaDTO.getTehsilId(),
				areaDTO.getWardPatwariId(),
				areaDTO.getMohallaVillageId(),
				areaDTO.getOfficeId(),
			};
			
			boolean updateExecuted = dbUtility.executeUpdate(params);
			
			if(updateExecuted)
				dbUtility.commit();
			else {
				// TODO Throw an exception (could not insert the officeArea Mapping)
				dbUtility.rollback();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List getOfficeMappings() {
		try {
			dbUtility.createStatement();
			
			
			
			
			List resultList = dbUtility.executeQuery(OfficeAreaMappingSQL.SELECT_ALL_OFFICE_MAPPING);
			
			List officeMappingList = new ArrayList(resultList.size());
			
			for(int i=0; i<resultList.size(); i++) {
				List subList = (List)resultList.get(i);
				
				OfficeAreaMappingDTO areaMappingDTO = new OfficeAreaMappingDTO();
				
				int index = 0;
				areaMappingDTO.setOfficeId( (String)subList.get(index++) );
				areaMappingDTO.setDistrictId( (String)subList.get(index++) );
				areaMappingDTO.setTehsilId( (String)subList.get(index++) );
				areaMappingDTO.setWardPatwariId( (String)subList.get(index++) );
				areaMappingDTO.setMohallaVillageId( (String)subList.get(index++));
				
				areaMappingDTO.setOfficeName( (String)subList.get(index++) );
				areaMappingDTO.setDistrictName( (String)subList.get(index++) );
				areaMappingDTO.setTehsilName( (String)subList.get(index++) );
				areaMappingDTO.setWardPatwariName( (String)subList.get(index++) );
				areaMappingDTO.setMohallaVillageName( (String)subList.get(index++) );
				
				officeMappingList.add(areaMappingDTO);
			}
	
			return officeMappingList;
			
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Collections.EMPTY_LIST;
	}

	public void deleteOfficeAreaMapping(List officeMappingList) {
		for(int i=0; i<officeMappingList.size(); i++)
			deleteOfficeAreaMapping((OfficeAreaMappingDTO)officeMappingList.get(i));
		
	}

	public void deleteOfficeAreaMapping(OfficeAreaMappingDTO areaMapping) {
		try {
			dbUtility.createPreparedStatement(OfficeAreaMappingSQL.DELETE_OFFICE_MAPPINGS);
			
			
			
			
			String params[] = new String[] {
				areaMapping.getOfficeId(),
			};
			
			boolean updateExecuted = dbUtility.executeUpdate(params);
			
			if(updateExecuted)
				dbUtility.commit();
			else {
				// TODO Throw an exception (could not insert the officeArea Mapping)
				dbUtility.rollback();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public OfficeAreaMappingDTO getByAreaMapping(OfficeAreaMappingDTO areaMapping) {
		try {
			dbUtility.createPreparedStatement(OfficeAreaMappingSQL.SELECT_BY_AREA_MAPPING);
			
			String[] params = new String[] {
					areaMapping.getDistrictId(),
					areaMapping.getTehsilId(),
					areaMapping.getWardPatwariId(),
					areaMapping.getMohallaVillageId(),
			};
			
			
			ArrayList result = dbUtility.executeQuery(params);
			
				// there are results or not?
			if(!result.isEmpty()) {
				List subList = (List)result.get(0);
				
				OfficeAreaMappingDTO areaMappingDTO = new OfficeAreaMappingDTO();
				
				int index = 0;
				areaMappingDTO.setOfficeId( (String)subList.get(index++) );
				areaMappingDTO.setDistrictId( (String)subList.get(index++) );
				areaMappingDTO.setTehsilId( (String)subList.get(index++) );
				areaMappingDTO.setWardPatwariId( (String)subList.get(index++) );
				areaMappingDTO.setMohallaVillageId( (String)subList.get(index++));
				
				areaMappingDTO.setOfficeName( (String)subList.get(index++) );
				areaMappingDTO.setDistrictName( (String)subList.get(index++) );
				areaMappingDTO.setTehsilName( (String)subList.get(index++) );
				areaMappingDTO.setWardPatwariName( (String)subList.get(index++) );
				areaMappingDTO.setMohallaVillageName( (String)subList.get(index++) );
				
				return areaMappingDTO;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	

}
