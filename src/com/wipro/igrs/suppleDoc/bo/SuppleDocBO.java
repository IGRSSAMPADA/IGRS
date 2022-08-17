package com.wipro.igrs.suppleDoc.bo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import com.wipro.igrs.DMSConnection.DMSUtility;
import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.reginit.constant.RegInitConstant;
import com.wipro.igrs.reginit.dto.CommonDTO;
import com.wipro.igrs.reginit.dto.PropertyValuationDTO;
import com.wipro.igrs.reginit.dto.RegCompletDTO;
import com.wipro.igrs.spotInsp.dao.SpotInspDAO;
import com.wipro.igrs.spotInsp.dto.SpotInspDTO;
import com.wipro.igrs.suppleDoc.bd.SuppleDocBD;
import com.wipro.igrs.suppleDoc.constant.SuppleDocConstant;
import com.wipro.igrs.suppleDoc.dao.SuppleDocDAO;
import com.wipro.igrs.suppleDoc.dto.SuppleDocDTO;
import com.wipro.igrs.suppleDoc.sql.SuppleDocSQL;

public class SuppleDocBO {

	private Logger logger = (Logger) Logger.getLogger(SuppleDocBO.class);

	public SuppleDocBO() {
	}

	public ArrayList countryStackBO(String _axn, String language) throws Exception {
		logger.debug("WE ARE IN BO Debug");
		SuppleDocDAO refDAO = new SuppleDocDAO();
		ArrayList listBO = new ArrayList();
		try {
			logger.info("Inside (countryStackBO) Method");
			listBO = refDAO.countryStackDAO(_axn,language);
			return listBO;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	
	
	public ArrayList<SuppleDocDTO> getSubArea(String language,
			String areaId,String tehsilId,String urbanFlag) {
			ArrayList  subAreaList=null;
			SuppleDocDAO refDAO = new SuppleDocDAO();
			ArrayList <SuppleDocDTO>returnList=null;
			try{
				subAreaList=refDAO.getSubArea(language,areaId,tehsilId,urbanFlag);
				if(subAreaList!=null&& subAreaList.size()>0)
				{
					returnList=new ArrayList<SuppleDocDTO>();
					for(int i=0;i<subAreaList.size();i++)
					{
						ArrayList subList=(ArrayList) subAreaList.get(i);
						SuppleDocDTO propDTO= new SuppleDocDTO();
						propDTO.setSubAreaId((String) subList.get(0));
						propDTO.setSubAreaName((String) subList.get(1));
						returnList.add(propDTO);
						
					}
					
				}
				
				return returnList;
			}catch (Exception e) {
				logger.error(e);
				return null;
			}
		
	}
	public ArrayList<SuppleDocDTO> getColonyName(String language,
			String wardPatwariId) {
		ArrayList  l1NameList=new ArrayList();
		ArrayList <SuppleDocDTO>returnList=null;
		SuppleDocDAO da = new SuppleDocDAO();
		try{
			l1NameList=da.getColonyName(language,wardPatwariId);
			if(l1NameList!=null&& l1NameList.size()>0)
			{
				returnList=new ArrayList<SuppleDocDTO>();
				for(int i=0;i<l1NameList.size();i++)
				{
					ArrayList subList=(ArrayList) l1NameList.get(i);
					SuppleDocDTO propDTO= new SuppleDocDTO();
					propDTO.setColonyId((String) subList.get(0)+"~"+(String) subList.get(2)+"~"+(String) subList.get(3));
					propDTO.setColonyName((String) subList.get(1));
					returnList.add(propDTO);
					
				}
				
			}
			
			return returnList;
		}catch (Exception e) {
			logger.error(e);
			return null;
		}

	
	}

	public ArrayList documentListBO(String langauge) throws Exception {
		logger.debug("WE ARE IN BO Debug");
		SuppleDocDAO refDAO = new SuppleDocDAO();
		ArrayList listBO = new ArrayList();
		try {
			logger.info("Inside (documentListBO) Method");
			listBO = refDAO.documentListDAO(langauge);
			return listBO;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	
	public ArrayList purposeListBO(String id, String language) throws Exception {
		logger.debug("WE ARE IN BO Debug");
		SuppleDocDAO refDAO = new SuppleDocDAO();
		ArrayList listBO = new ArrayList();
		try {
			logger.info("Inside (documentListBO) Method");
			listBO = refDAO.purposeListDAO(id,language);
			return listBO;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public ArrayList stateStackBO(String _countryId, String _axn, String language)
			throws Exception {
		logger.debug("WE ARE IN BO Debug");
		SuppleDocDAO refDAO = new SuppleDocDAO();
		ArrayList listBO = new ArrayList();
		try {
			logger.info("Inside (stateStackBO) Method");
			listBO = refDAO.stateStackDAO(_countryId, _axn,language);
			return listBO;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public ArrayList photoIdStackBO() throws Exception {
		logger.debug("WE ARE IN BO Debug");
		SuppleDocDAO refDAO = new SuppleDocDAO();
		ArrayList listBO = new ArrayList();
		try {
			logger.info("Inside (photoIdStackBO) Method");
			listBO = refDAO.photoIdStackDAO();
			return listBO;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public ArrayList casteStackBO() throws Exception {
		logger.debug("WE ARE IN BO Debug");
		SuppleDocDAO refDAO = new SuppleDocDAO();
		ArrayList listBO = new ArrayList();
		try {
			logger.info("Inside (casteStackBO) Method");
			listBO = refDAO.casteStackDAO();
			return listBO;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public ArrayList religionStackBO() throws Exception {
		logger.debug("WE ARE IN BO Debug");
		SuppleDocDAO refDAO = new SuppleDocDAO();
		ArrayList listBO = new ArrayList();
		try {
			logger.info("Inside (religionStackBO) Method");
			listBO = refDAO.religionStackDAO();
			return listBO;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public ArrayList districtStackBO(String _stateId, String _axn, String language)
			throws Exception {
		logger.debug("WE ARE IN BO Debug");
		SuppleDocDAO refDAO = new SuppleDocDAO();
		ArrayList listBO = new ArrayList();
		try {
			logger.info("Inside (districtStackBO) Method");
			listBO = refDAO.districtStackDAO(_stateId, _axn,language);
			return listBO;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public ArrayList loanPurposeStackBO() throws Exception {
		logger.debug("WE ARE IN BO Debug");
		SuppleDocDAO refDAO = new SuppleDocDAO();
		ArrayList listBO = new ArrayList();
		try {
			logger.info("Inside (loanPurposeStackBO) Method");
			listBO = refDAO.loanPurposeStackDAO();
			return listBO;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public ArrayList deedStackBO() throws Exception {
		logger.debug("WE ARE IN BO Debug");
		SuppleDocDAO refDAO = new SuppleDocDAO();
		ArrayList listBO = new ArrayList();
		try {
			logger.info("Inside (deedStackBO) Method");
			listBO = refDAO.deedStackDAO();
			return listBO;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public ArrayList estampCheckBO(String _estampCode) throws Exception {
		logger.debug("WE ARE IN BO Debug");
		SuppleDocDAO refDAO = new SuppleDocDAO();
		ArrayList listBO = new ArrayList();
		try {
			logger.info("Inside (estampCheckBO) Method");
			listBO = refDAO.estampCheckDAO(_estampCode);
			return listBO;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public boolean insertSuppliDetailsBO(String param[]) throws Exception {
		logger.debug("WE ARE IN BO Debug");
		SuppleDocDAO refDAO = new SuppleDocDAO();
		try {
			logger.info("Inside (insertSuppliDetailsBO) Method");
			String param1[] = new String[4]; // Array for DOC_TXN_DETAILS
			param1[0] = param[0];// SUPPLI_TXN_ID
			param1[1] = param[1];
			param1[2] = param[2];
			param1[3] = param[3];
			String param2[] = new String[25];// aRRAY FOR SUPPLI_PARTY_DETAILS
			param2[0] = param[0];// SUPPLI_TXN_ID
			param2[1] = param[4];
			param2[2] = param[5];
			param2[3] = param[6];
			param2[4] = param[7];
			param2[5] = param[8];
			param2[6] = param[9];
			param2[7] = param[10];
			param2[8] = param[11];
			param2[9] = param[12];
			param2[10] = param[13];
			param2[11] = param[14];
			param2[12] = param[15];
			param2[13] = param[16];
			param2[14] = param[17];
			param2[15] = param[18];
			param2[16] = param[19];
			param2[17] = param[20];
			param2[18] = param[21];
			param2[19] = param[22];
			param2[20] = param[23];
			param2[21] = param[24];
			param2[22] = param[25];
			param2[23] = param[43];
			param2[24] = param[44];
			String param3[] = new String[9];// Array for DEED_DETAILS
			param3[0] = param[0];// SUPPLI_DOC_TXN_ID
			param3[1] = param[26];
			param3[2] = param[27];
			param3[3] = param[28];
			param3[4] = param[29];
			param3[5] = param[30];
			param3[6] = param[31];
			param3[7] = param[32];
			param3[8] = param[33];
			String param4[] = new String[10];// Array for BANK_DETAILS
			param4[0] = param[0];// SUPPLI_DOC_TXN_ID
			param4[1] = param[34];
			param4[2] = param[35];
			param4[3] = param[36];
			param4[4] = param[37];
			param4[5] = param[38];
			param4[6] = param[39];
			param4[7] = param[40];
			param4[8] = param[41];
			param4[9] = param[42];
			return refDAO
					.insertSuppliDetailsDAO(param1, param2, param3, param4);
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}

	public boolean insertSuppliPartyDetailsBO(String param[]) throws Exception {
		logger.debug("WE ARE IN BO Debug");
		SuppleDocDAO refDAO = new SuppleDocDAO();
		try {
			logger.info("Inside (insertSuppliPartyDetailsBO) Method");

			return refDAO.insertSuppliPartyDetailsDAO(param);
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}

	public ArrayList searchListViewBO(String _supplitxnId, String _frDate,
			String _toDate, String officeId) throws Exception {
		logger.debug("WE ARE IN BO Debug");
		SuppleDocDAO refDAO = new SuppleDocDAO();
		ArrayList listBO = new ArrayList();
		try {
			logger.info("Inside (searchListViewBO) Method");
			if(!_supplitxnId.equals("") && _frDate.equals(""))
			{
				String param[] = new String[2];
			String sql = SuppleDocSQL.IGRS_SUPPLI_VIEW_DOCUMENT_DETAILS_1;
				param[0] = _supplitxnId;
				param[1] = officeId;
				listBO = refDAO.searchListViewDAO(param,sql);
			}
			else if(_supplitxnId.equals("") && !_frDate.equals(""))
			{
				String param[] = new String[3];
			
			param[0] = _frDate;
			param[1] = _toDate;
			param[2] = officeId;
			String sql = SuppleDocSQL.IGRS_SUPPLI_VIEW_DOCUMENT_DETAILS_2;
			listBO = refDAO.searchListViewDAO(param,sql);
			}
			else
			{
				String param[] = new String[4];
				param[0] = _supplitxnId;
				param[1] = _frDate;
				param[2] = _toDate;
				param[3] = officeId;
				String sql = SuppleDocSQL.IGRS_SUPPLI_VIEW_DOCUMENT_DETAILS_3;
				listBO = refDAO.searchListViewDAO(param,sql);
				
				
			}
			return listBO;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public SuppleDocDTO getTotalDetailsBO(String _docNo, String language) throws Exception {
		logger.debug("WE ARE IN BO Debug");
		SuppleDocDAO refDAO = new SuppleDocDAO();
		SuppleDocDTO listBO = new SuppleDocDTO();
		try {
			logger.info("Inside (getTotalDetailsBO) Method");
			String param[] = new String[1];
			param[0] = _docNo;
			listBO = refDAO.getTotalDetailsDAO(param,language);
			return listBO;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	
	public SuppleDocDTO getTotalDetailsReferenceBO(String _docNo, String language) throws Exception {
		logger.debug("WE ARE IN BO Debug");
		SuppleDocDAO refDAO = new SuppleDocDAO();
		SuppleDocDTO listBO = new SuppleDocDTO();
		try {
			logger.info("Inside (getTotalDetailsReferenceBO) Method");
			String param[] = new String[1];
			param[0] = _docNo;
			listBO = refDAO.getTotalDetailsReferenceDAO(param,language);
			return listBO;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}


	public ArrayList getTotalPartyDetailsBO(String[] data) {
		
		SuppleDocDAO refDAO = new SuppleDocDAO();
		try {
			return refDAO.getTotalPartyDetailsDAO(data);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			logger.debug(e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.debug(e.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.debug(e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.debug(e.getMessage());
		}
		return null;
	}

	
	public void insertCompleteSupDetails(SuppleDocDTO baseFormDTO,
			SuppleDocDTO[] values) {
		try {
			SuppleDocDAO refDAO=new SuppleDocDAO();
			refDAO.insertCompleteSupDetails(baseFormDTO, values);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void insertCompletePartial2Details(SuppleDocDTO baseFormDTO,
			SuppleDocDTO[] values, SuppleDocDTO[] valuesEStamp) {
		try {
			SuppleDocDAO refDAO=new SuppleDocDAO();
			refDAO.insertCompletePartial2DetailsDAO(baseFormDTO, values,valuesEStamp);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	
	public ArrayList getTehsil(String districtId, String language) throws Exception {
		IGRSCommon common = new IGRSCommon();
		SuppleDocDAO commons = new SuppleDocDAO();
		ArrayList ret = commons.getThesil(districtId,language);
		ArrayList list = new ArrayList();

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				 SuppleDocDTO type = new SuppleDocDTO();
				logger.debug("Tehsil:-" + lst.get(0) + ":" + lst.get(1));
				type.setTehsilID((String) lst.get(0));
				type.setTehsil((String) lst.get(1));
				
				list.add(type);
			}

		}
		return list;
	}
	
	public ArrayList getAreaType(String language) {
		ArrayList list = new ArrayList();
		try {
			SuppleDocDAO commons = new SuppleDocDAO();
			ArrayList retList = commons.getAreaType(language);
			if (retList != null) {
				for (int i = 0; i < retList.size(); i++) {
					ArrayList lst = (ArrayList) retList.get(i);
					SuppleDocDTO type = new SuppleDocDTO();
					type.setAreaId((String) lst.get(0));
					type.setAreaType((String) lst.get(1));
					
					logger.debug(lst.get(0) + ":" + lst.get(1));
					list.add(type);
				}

			}

		} catch (Exception x) {
			logger.debug(x);
		}
		return list;
	}
	
	public ArrayList getWard(String tehsilId, String areaType, String wardType, String language)
			throws Exception {
	
		SuppleDocDAO commons = new SuppleDocDAO();
		ArrayList ret = commons.getWard(tehsilId, areaType, wardType,language);
		ArrayList list = new ArrayList();

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				SuppleDocDTO dto = new SuppleDocDTO();
				logger.debug("Tehsil:-" + lst.get(0) + ":" + lst.get(1));
				dto.setWardId((String) lst.get(0));
				
				dto.setWard((String) lst.get(1));
				
				list.add(dto);
			}

		}
		return list;
	}
	
	public ArrayList getMahalla(String patwariId, String language) throws Exception {
		SuppleDocDAO commons = new SuppleDocDAO();
		ArrayList ret = commons.getMahalla(patwariId,language);
		ArrayList list = new ArrayList();

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
			
				SuppleDocDTO dto = new SuppleDocDTO();
				logger.debug("Mahalla:-" + lst.get(0) + ":" + lst.get(1));
				dto.setMahallaId((String) lst.get(0));
				dto.setMahalla((String) lst.get(1));
				
				list.add(dto);
			}

		}
		return list;
	}
	
	public ArrayList getMunicipalBody(String language) {
		ArrayList list = new ArrayList();
		try {
			SuppleDocDAO dao = new SuppleDocDAO();
			ArrayList retList = dao.getMunicipalBody(language);
			if (retList != null) {
				for (int i = 0; i < retList.size(); i++) {
					ArrayList lst = (ArrayList) retList.get(i);
					SuppleDocDTO dto = new SuppleDocDTO();
					dto.setMunicipalBodyID((String) lst.get(0));
					dto.setMunicipalBody((String) lst.get(1));
					
					list.add(dto);
				}
			}

		} catch (Exception x) {
			logger.debug(x);
		}
		return list;
	}
	
	public ArrayList getPropertyType(String language) {
		ArrayList list = new ArrayList();

		

		try {
			SuppleDocDAO dao = new SuppleDocDAO();
			ArrayList retList = dao.getPropertyType(language);
			if (retList != null) {
				for (int i = 0; i < retList.size(); i++) {
					ArrayList lst = (ArrayList) retList.get(i);
					SuppleDocDTO dto = new SuppleDocDTO();
					dto.setPropertyTypeId((String) lst.get(0));
					dto.setPropertyType((String) lst.get(1));
					
					list.add(dto);
				}
			}
			
		} catch (Exception x) {
			logger.debug(x);
		}
		return list;
	}

	
	public ArrayList getPropTypeL1List(String propId, String language) throws Exception {
		SuppleDocDAO refDAO = new SuppleDocDAO();
        return refDAO.getPropTypeL1List(propId,language);

}
	
	public ArrayList getPropTypeL2List(String propL1Id, String language) throws Exception {
		ArrayList list = new ArrayList();
		SuppleDocDAO refDAO = new SuppleDocDAO();
		try{
			
			ArrayList ret=refDAO.getPropTypeL2List(propL1Id,language);
			

			if (ret != null) {
				for (int i = 0; i < ret.size(); i++) {
					ArrayList lst = (ArrayList) ret.get(i);
					RegCompletDTO dto = new RegCompletDTO();
					logger.debug("property type l2:-" + lst.get(0) + ":" + lst.get(1));
					dto.setPropTypeL2Id((String) lst.get(0));
					dto.setPropTypeL2((String) lst.get(1));
					//dto.setHdnPropTypeL2((String) lst.get(0) + "~"
					//		+ (String) lst.get(1));
					list.add(dto);
				}

			}
			
		}catch(Exception e){
			e.printStackTrace();
		}

        return list;

}
	public ArrayList getFloorType(String l1ID, String language) {
		ArrayList list = new ArrayList();
		SuppleDocDAO data = new SuppleDocDAO();
		try {
			ArrayList retList = data.getFloor(l1ID,language);
			if (retList != null) {
				for (int i = 0; i < retList.size(); i++) {
					ArrayList lst = (ArrayList) retList.get(i);
					SuppleDocDTO dto = new SuppleDocDTO();
					dto.setFloorId((String) lst.get(0));
					dto.setTypeFloorName((String) lst.get(1));
					dto.setTypeFloorDesc((String) lst.get(2));
					
					list.add(dto);
				}
			}
		} catch (Exception x) {
			x.printStackTrace();
		}
		return list;
	}
	
	public ArrayList getUnitList(String propL1Id, String language) throws Exception {
		SuppleDocBD refBd = new SuppleDocBD();
	    return refBd.getUnitList(propL1Id,language);

	}

	public boolean insertLastPageDetailsBO(String[] param) {
		logger.debug("WE ARE IN BO Debug");
		SuppleDocDAO refDAO = new SuppleDocDAO();
		try {
			logger.info("Inside (insertLastPageDetailsBO) Method");

			return refDAO.insertLastPageDetailsDAO(param);
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}

	public ArrayList getBookTypeList() {
		SuppleDocBD refBd = new SuppleDocBD();
	    return refBd.getBookTypeListBD();
	}

	public boolean insertCompletePropertyDetails(SuppleDocDTO sdDTO,
			HashMap mapFloor) {
		SuppleDocBD refBd = new SuppleDocBD();
		return refBd.insertCompletePropertyDetailsBD(sdDTO,mapFloor);
		
	}

	public ArrayList getPendingDetls(String uid, String officeId, String language) throws Exception{
			 ArrayList mainlist = new ArrayList();
			 ArrayList list2 = new ArrayList();
			 SuppleDocDTO dto = new SuppleDocDTO();
			 SuppleDocDAO dao = new SuppleDocDAO();
			 mainlist =  dao.getPendingDetls(uid,officeId);
			
			 for(int i = 0; i< mainlist.size();i++){
				 
				 ArrayList listaa = new ArrayList();
				 listaa=(ArrayList) mainlist.get(i);
				 dto.setReferenceId((String)listaa.get(0));
				 logger.debug(dto.getReferenceId());
				 String propID=(String) listaa.get(1);
				
				 dto.setCreatedDate((String) listaa.get(1));
				 
				 logger.debug(dto.getCreatedDate());
				
				 
				String status =(String) listaa.get(2);
				if(status.equalsIgnoreCase("R"))
				{
					if(language.equalsIgnoreCase("en"))
					dto.setCasecheck("Refered To DR");
					else
					dto.setCasecheck("मामला जिला रजिस्ट्रार को भेजा गया ");	
				}
				else if(status.equalsIgnoreCase("P"))
				{
					if(language.equalsIgnoreCase("en"))
					dto.setCasecheck("Pending");
					else
					dto.setCasecheck("लंबित है");	
				}
				// logger.debug(dto.getStatus());
				 dto.setPageNo((String) listaa.get(3));
				 
				 logger.debug(dto.getPageNo());
				 
				 dto.setCombineData((String)listaa.get(0)+"~"+(String)listaa.get(1)+"~"+(String)listaa.get(3)+"~"+(String)listaa.get(2));
				 logger.debug(dto.getCombineData());
				 list2.add(i, dto);
				 dto = new SuppleDocDTO();
			 }
			 return list2;
		}
	
public HashMap getPropertyDetsForViewConfirm(String appId, String propId,String referenceId) throws Exception {
		
		HashMap propMap=new HashMap();
		
		
		//valuation id
	//	String valuationId=commonBd.getPropValuationId(appId,propId);
		
		
		//details corresponding to valuation id
		ArrayList propList = new ArrayList();
		
		//propList=commonBd.getPropDetlsForDashboard(valuationId);
		//propList=commonBd.getPropDetlsForDashboard(propId);
		//propList=getPropDetlsForDisplay(propId);
		String propDetails=propList.toString().substring(2, (propList.toString().length()-2));
		String propDetsArray[]=propDetails.split(",");
	    
		
		
		
		RegCompletDTO dto=new RegCompletDTO();
		
		dto.setPropertyTypeName(propDetsArray[14].trim());
		dto.setDistName(propDetsArray[1].trim());
		dto.setTehsilName(propDetsArray[3].trim());
		dto.setAreaTypeName(propDetsArray[5].trim());
		dto.setTotalSqMeter(Integer.parseInt(propDetsArray[15].trim()));
		if(dto.getPropertyTypeName().equalsIgnoreCase("plot") ||
				dto.getPropertyTypeName().equalsIgnoreCase("building"))
		{
			
			dto.setUnit("sq mtr");
		}
		else
		{
			
			String unit=propDetsArray[16].trim();
			if(unit.equalsIgnoreCase("2"))
			{
				dto.setUnit("SQM");
			}
			else if(unit.equalsIgnoreCase("3"))
			{
				dto.setUnit("HECTARE");
			}
			
		}
		//dto.setUnit("");
		//dto.setMunicipalDutyName("");
		dto.setMunicipalBodyName(propDetsArray[12].trim());
		String wardstatus=propDetsArray[8].trim();//////
		
		if (wardstatus.equalsIgnoreCase("1"))
		{
			wardstatus="Ward";
		}
		else
		{
			wardstatus="Patwari/Halka";
		}
		dto.setPatwariStatus(wardstatus);
		dto.setWardpatwarName(propDetsArray[7].trim());
		dto.setMohallaName(propDetsArray[10].trim());
		//no. of floors.
		
		if(propDetsArray[17].trim().equals("") || propDetsArray[17].trim().equals("null")){
		dto.setVikasId("-");}
		else{
		dto.setVikasId(propDetsArray[17].trim().replace( RegInitConstant.SPLIT_CONSTANT,","));}
		dto.setRicircle(propDetsArray[18].trim().replace( RegInitConstant.SPLIT_CONSTANT,","));
		if(propDetsArray[19].trim().equals("") || propDetsArray[19].trim().equals("null"))
		dto.setLayoutdet("-");
		else
		dto.setLayoutdet(propDetsArray[19].trim().replace( RegInitConstant.SPLIT_CONSTANT,","));
		
		if(propDetsArray[20].trim().equals("") || propDetsArray[20].trim().equals("null"))
		dto.setSheetnum("-");	
		else
		dto.setSheetnum(propDetsArray[20].trim().replace( RegInitConstant.SPLIT_CONSTANT,","));
		if(propDetsArray[21].trim().equals("") || propDetsArray[21].trim().equals("null"))
		dto.setPlotnum("-");
		else
		dto.setPlotnum(propDetsArray[21].trim().replace( RegInitConstant.SPLIT_CONSTANT,","));
		dto.setPropAddress(propDetsArray[22].trim().replace( RegInitConstant.SPLIT_CONSTANT,","));
		dto.setNorth(propDetsArray[23].trim().replace( RegInitConstant.SPLIT_CONSTANT,","));
		dto.setSouth(propDetsArray[24].trim().replace( RegInitConstant.SPLIT_CONSTANT,","));
		dto.setEast(propDetsArray[25].trim().replace( RegInitConstant.SPLIT_CONSTANT,","));
		dto.setWest(propDetsArray[26].trim().replace( RegInitConstant.SPLIT_CONSTANT,","));
		if(propDetsArray[27].trim().equals("") || propDetsArray[27].trim().equals("null"))
			dto.setPropLandmark("-");
			else
			dto.setPropLandmark(propDetsArray[27].trim().replace( RegInitConstant.SPLIT_CONSTANT,","));
		
		
		if(	!dto.getPropertyTypeName().equalsIgnoreCase("building"))
		{
			
		//	dto.setSelectedSubClauseList(getSubClauseListNonBuilding(valuationId));
			
		}
		//BELOW CODE FOR UPLOADS
		//28
		
		dto.setPropImage1DocumentName("ANGLE 1.JPG");
		dto.setPropImage1FilePath(propDetsArray[28].trim());
		if(propDetsArray[28].trim()!=null && !propDetsArray[28].trim().equalsIgnoreCase("")){
			dto.setPropImage1DocContents(DMSUtility.getDocumentBytes(propDetsArray[28].trim()));
       		}else{
       			dto.setPropImage1DocContents(new byte[0]);
	
       		}
		dto.setPropImage1UploadType(propDetsArray[28].trim().substring(propDetsArray[28].trim().lastIndexOf(".")-2));
		
		dto.setPropImage2DocumentName("ANGLE 2.JPG");
		dto.setPropImage2FilePath(propDetsArray[29].trim());
		if(propDetsArray[29].trim()!=null && !propDetsArray[29].trim().equalsIgnoreCase("")){
			dto.setPropImage2DocContents(DMSUtility.getDocumentBytes(propDetsArray[29].trim()));
       		}else{
       			dto.setPropImage2DocContents(new byte[0]);
	
       		}
		dto.setPropImage2UploadType(propDetsArray[29].trim().substring(propDetsArray[29].trim().lastIndexOf(".")-2));
		
		dto.setPropImage3DocumentName("ANGLE 3.JPG");
		dto.setPropImage3FilePath(propDetsArray[30].trim());
		if(propDetsArray[30].trim()!=null && !propDetsArray[30].trim().equalsIgnoreCase("")){
			dto.setPropImage3DocContents(DMSUtility.getDocumentBytes(propDetsArray[30].trim()));
       		}else{
       			dto.setPropImage3DocContents(new byte[0]);
	
       		}
		dto.setPropImage3UploadType(propDetsArray[30].trim().substring(propDetsArray[30].trim().lastIndexOf(".")-2));
		
		dto.setPropMapDocumentName("MAP.JPG");
		dto.setPropMapFilePath(propDetsArray[31].trim());
		if(propDetsArray[31].trim()!=null && !propDetsArray[31].trim().equalsIgnoreCase("")){
			dto.setPropMapDocContents(DMSUtility.getDocumentBytes(propDetsArray[31].trim()));
       		}else{
       			dto.setPropMapDocContents(new byte[0]);
	
       		}
		dto.setPropMapUploadType(propDetsArray[31].trim().substring(propDetsArray[31].trim().lastIndexOf(".")-2));
		
		if(	dto.getPropertyTypeName().equalsIgnoreCase("AGRI LAND"))
		{
		dto.setPropRinDocumentName("RIN.JPG");
		dto.setPropRinFilePath(propDetsArray[32].trim());
		if(propDetsArray[32].trim()!=null && !propDetsArray[32].trim().equalsIgnoreCase("")){
			dto.setPropRinDocContents(DMSUtility.getDocumentBytes(propDetsArray[32].trim()));
       		}else{
       			dto.setPropRinDocContents(new byte[0]);
	
       		}
		dto.setPropRinUploadType(propDetsArray[32].trim().substring(propDetsArray[32].trim().lastIndexOf(".")-2));
		
		dto.setPropKhasraDocumentName("KHASRA.JPG");
		dto.setPropKhasraFilePath(propDetsArray[33].trim());
		if(propDetsArray[33].trim()!=null && !propDetsArray[33].trim().equalsIgnoreCase("")){
			dto.setPropKhasraDocContents(DMSUtility.getDocumentBytes(propDetsArray[33].trim()));
       		}else{
       			dto.setPropKhasraDocContents(new byte[0]);
	
       		}
		dto.setPropKhasraUploadType(propDetsArray[33].trim().substring(propDetsArray[33].trim().lastIndexOf(".")-2));
		}
		
		
		
		//other details corresponding to app id and prop id.
		//ArrayList otherPropList = new ArrayList();
		//otherPropList=commonBd.getOtherPropDetsForViewConfirm(appId,propId);
		
		//otherPropList=getPropKhasraDetlsForDisplay(propId);
		//String otherPropDetails=otherPropList.toString().substring(2, (otherPropList.toString().length()-2));
		
		//String otherPropDetsArray[]=otherPropDetails.split(",");
		
		//FOR GETTING PROPERTY KHASRA DETAILS
		String otherPropDetsArray[]=getPropKhasraDetlsForDisplay(propId);
		
		
		//following code for inserting khasra detls into map
		if(otherPropDetsArray!=null){
		String[] khasraNum=otherPropDetsArray[0].trim().split(SuppleDocSQL.SPLIT_CONSTANT_KHASRA);
		String[] khasraArea=otherPropDetsArray[1].trim().split(SuppleDocSQL.SPLIT_CONSTANT_KHASRA);
		String[] lagaan=otherPropDetsArray[2].trim().split(SuppleDocSQL.SPLIT_CONSTANT_KHASRA);
		String[] rinPustika=otherPropDetsArray[3].trim().split(SuppleDocSQL.SPLIT_CONSTANT_KHASRA);
		
		
		
		int length=khasraNum.length;
		CommonDTO objDto;
		
		for(int i=0;i<length;i++){
			objDto=new CommonDTO();
			if(khasraNum[i].equalsIgnoreCase("null"))
			{
				objDto.setKhasraNum("-");
				objDto.setKhasraArea("-");
				objDto.setLagaan("-");
				objDto.setRinPustika("-");
			}else{
			objDto.setKhasraNum(khasraNum[i].trim());
			objDto.setKhasraArea(khasraArea[i].trim());
			objDto.setLagaan(lagaan[i].trim());
			objDto.setRinPustika(rinPustika[i].trim());
			}
			
			dto.getKhasraDetlsDisplay().add(objDto);
		}
		}else{
			CommonDTO objDto=new CommonDTO();
			objDto.setKhasraNum("-");
			objDto.setKhasraArea("-");
			objDto.setLagaan("-");
			objDto.setRinPustika("-");
			dto.getKhasraDetlsDisplay().add(objDto);
		}
		HashMap buildingMap=new HashMap();
		if(propDetsArray[14].trim().equalsIgnoreCase("Building")){
		
			
		/*	//following code for getting building floor details
			ArrayList buildingList=getGuildingFloorDetails(1);
			if(buildingList.size()>0){
				
				dto.setFloorCount(buildingList.size());
				for(int j=0;j<buildingList.size();j++){
					RegCompletDTO dto1=new RegCompletDTO();
					ArrayList row_list=(ArrayList)buildingList.get(j);
					String str=row_list.toString();
					str=str.substring(1,str.length()-1);
					String[] strArray=str.split(",");
					dto1.setUsageBuilding(strArray[7]);
					dto1.setCeilingType(strArray[8]);
					dto1.setTypeFloorDesc(strArray[9]);
					dto1.setConsiderAmt(Double.parseDouble(strArray[4]));
					dto1.setTotalSqMeter(Double.parseDouble(strArray[5]));
					dto1.setConstructedArea(strArray[6]);
					
					dto1.setSelectedSubClauseList(getSubClauseListBuilding(valuationId,strArray[0]));
					
					dto.getMapBuilding().put(strArray[0], dto1);*/
					
				/*	
				}
				
				
				
			}
			
			
			
			
		}else*/
			dto.setFloorCount(0);
				
		propMap.put(propId, dto);  
		
		return propMap;}
		return buildingMap;
	}
	
	public SuppleDocDTO getPropertyDetails(String referenceId,SuppleDocDTO dto,String language) throws Exception
	{
		SuppleDocDAO refDAO = new SuppleDocDAO();
		
		ArrayList propList = new ArrayList();
		ArrayList floorLists = new ArrayList();
		ArrayList floorsData = new ArrayList();
		propList=refDAO.getPropDetlsForDisplay(referenceId);
		if(propList!=null && propList.size()>0)
		{
		ArrayList propertyList = (ArrayList)propList.get(0);
		
		
		String proptype = (String)propertyList.get(13);
		
		
		
		
		
		
		if(proptype.equalsIgnoreCase("2"))
		{
			
			floorsData= refDAO.getPropFloorDetlsForDisplay(referenceId,language);
			if(floorsData!=null && floorsData.size()>0)
			{
				for(int i=0;i<floorsData.size();i++)
				{
					ArrayList s = (ArrayList) floorsData.get(i);
				SuppleDocDTO a = new SuppleDocDTO();
				a.setFname((String)s.get(0));
				a.setPropTypeL1Name((String)s.get(1));
				a.setPropTypeL2((String)s.get(2));
				a.setUnitNameTemp((String)s.get(3));
				a.setAreaId((String)s.get(4));
				a.setAreaType((String)s.get(5));
				floorLists.add(a);
				}
			}
		dto.setFloorTypeList(floorLists);	
		}
		else
		{
			dto.setFloorTypeList(new ArrayList());
		}
		
		
		
		
		String Vikasid = (String)propertyList.get(0);
		String riCircle = (String)propertyList.get(1);
		String layoutDetails = (String)propertyList.get(2);
		String sheetnum = (String)propertyList.get(3);
		String plotNum = (String)propertyList.get(4);
		String east = (String)propertyList.get(5);
		String west = (String)propertyList.get(6);
		String north = (String)propertyList.get(7);
		String south = (String)propertyList.get(8);
		String propertyAdress  = (String)propertyList.get(9);
		
		
				String propertyLandmark = (String)propertyList.get(10);
				String area_id = (String)propertyList.get(11);
				String govbody = (String)propertyList.get(12);
			
				String l1id = (String)propertyList.get(14);
				String l2id = (String)propertyList.get(15);
				String area_unit_id = (String)propertyList.get(16);
				String area = (String)propertyList.get(17);
				String districtId = (String)propertyList.get(18);
				String tehsilId =(String)propertyList.get(19);
				String wardId =(String)propertyList.get(20);
				String mahallaId = (String)propertyList.get(21);
				String patwari_ward = (String)propertyList.get(22);
				 
		if(Vikasid==null){
			dto.setVikasKhand("-");}
			else{
			dto.setVikasKhand(Vikasid.trim().replace( RegInitConstant.SPLIT_CONSTANT,","));}
		
		if(riCircle==null)
		{
			dto.setRicircle("-");	
		}
		else
		{
		dto.setRicircle(riCircle.trim().replace( RegInitConstant.SPLIT_CONSTANT,","));
		}
		if(layoutDetails==null)
			dto.setLayoutdet("-");
			else
			dto.setLayoutdet(layoutDetails.trim().replace( RegInitConstant.SPLIT_CONSTANT,","));
			//added by shruti
			if(sheetnum==null)
			{
				dto.setSheetnum("-");
			}
			//if(sheetnum.trim().equals("") || sheetnum.trim().equals("null"))
			//dto.setSheetnum("-");
			//}
			//end
			else
			dto.setSheetnum(sheetnum.trim().replace( RegInitConstant.SPLIT_CONSTANT,","));
			
			logger.debug("PROPERT LIST SIZE:" + propList.size());
			if(plotNum==null)
			dto.setPlotnum("-");
			else
			dto.setPlotnum(plotNum.trim().replace( RegInitConstant.SPLIT_CONSTANT,","));
			
			if(propertyAdress==null)
			{
			dto.setPropAddress("-");
			}
			else
			dto.setPropAddress(propertyAdress.trim().replace( RegInitConstant.SPLIT_CONSTANT,","));
			
			if(propertyLandmark==null)
			{
			dto.setPropLandmark("-");
			}
			else
			dto.setPropLandmark(propertyLandmark.trim().replace( RegInitConstant.SPLIT_CONSTANT,","));
			
			
			dto.setNorth(north.trim().replace( RegInitConstant.SPLIT_CONSTANT,","));
			dto.setSouth(south.trim().replace( RegInitConstant.SPLIT_CONSTANT,","));
			dto.setEast(east.trim().replace( RegInitConstant.SPLIT_CONSTANT,","));
			dto.setWest(west.trim().replace( RegInitConstant.SPLIT_CONSTANT,","));
		
			dto.setMahalla(refDAO.getMohallaName(mahallaId,language));
			dto.setSubAreaName(refDAO.getMunicipalBodyName(govbody,language));
			dto.setAreaType(refDAO.getAreaTypeName(area_id,language));
			dto.setDistrictPropertyName(refDAO.getDistrictName(districtId,language));
			dto.setDistrictIDProperty(districtId);
			dto.setTehsil(refDAO.getTehsilName(tehsilId,language));
			dto.setPropertyType(refDAO.getPropertyTypeName(proptype,language));
			dto.setWardIds(refDAO.getPatwariWardName(patwari_ward,language));
			dto.setPropertyTypeId(proptype);
			dto.setAreaId(area_id);
			//GEtting tehsil name
			dto.setArea(Integer.parseInt(area));
			
			if(dto.getPropertyType().equalsIgnoreCase("plot") ||
					dto.getPropertyType().equalsIgnoreCase("building")|| dto.getPropertyType().equalsIgnoreCase("भूखंड") || dto.getPropertyType().equalsIgnoreCase("बिल्डिंग"))
			{
				if(language.equalsIgnoreCase("en"))
				dto.setUnit("sq mtr");
				else
					dto.setUnit("वर्ग मीटर");
			}
			else
			{
				
				String unit=area_unit_id;
				if(unit.equalsIgnoreCase("1"))
				{
					if(language.equalsIgnoreCase("en"))
					dto.setUnit("SQM");
					else
						dto.setUnit("वर्ग मीटर");	
					
				}
				else if(unit.equalsIgnoreCase("2"))
				{
					if(language.equalsIgnoreCase("en"))
					dto.setUnit("HECTARE");
					else
						dto.setUnit("हेक्टेयर");
				}
				
			}
			if (wardId.equalsIgnoreCase("1"))
			{
			if(language.equalsIgnoreCase("en"))
				wardId="Patwari/Halka";
			else
				wardId="पटवारी/हल्का ";
			}
			else
			{
				if(language.equalsIgnoreCase("en"))
				wardId="Ward";
				else
					wardId="वार्ड";	
			}
			
			dto.setWardId(wardId);
			
			
			
		
			
			String otherPropDetsArray[]=getPropKhasraDetlsForDisplay(referenceId);
			
			
			//following code for inserting khasra detls into map
			if(otherPropDetsArray!=null){
			String[] khasraNum=otherPropDetsArray[0].trim().split(SuppleDocSQL.SPLIT_CONSTANT_KHASRA);
			String[] khasraArea=otherPropDetsArray[1].trim().split(SuppleDocSQL.SPLIT_CONSTANT_KHASRA);
			String[] lagaan=otherPropDetsArray[2].trim().split(SuppleDocSQL.SPLIT_CONSTANT_KHASRA);
			String[] rinPustika=otherPropDetsArray[3].trim().split(SuppleDocSQL.SPLIT_CONSTANT_KHASRA);
			
			
			
			int length=khasraNum.length;
			SuppleDocDTO objDto;
			
			for(int i=0;i<length;i++){
				objDto=new SuppleDocDTO();
				if(khasraNum[i].equalsIgnoreCase("null"))
				{
					objDto.setKhasaraNum("-");
					objDto.setKhasraArea1("-");
					objDto.setLagaan("-");
					objDto.setRinpustikaNum("-");
				}else{
				objDto.setKhasaraNum(khasraNum[i].trim());
				objDto.setKhasraArea1(khasraArea[i].trim());
				objDto.setLagaan(lagaan[i].trim());
				objDto.setRinpustikaNum(rinPustika[i].trim());
				}
				
				dto.getKhasraDetlsDisplay().add(objDto);
			}
			}else{
				SuppleDocDTO objDto=new SuppleDocDTO();
				objDto.setKhasaraNum("-");
				objDto.setKhasraArea1("-");
				objDto.setLagaan("-");
				objDto.setRinpustikaNum("-");
				dto.getKhasraDetlsDisplay().add(objDto);
			}
		}
			
			return dto;
	}
	
	
	public String getReferenceId(String filingnuber) throws Exception
	{
		SuppleDocBD commonBd = new SuppleDocBD();
		
		
		
		return commonBd.getreferenceID(filingnuber);
		
		
		
		
	}
	
public String[] getPropKhasraDetlsForDisplay(String propId) throws Exception {
		
	SuppleDocBD commonBd = new SuppleDocBD();
		ArrayList list=commonBd.getPropKhasraDetlsForDisplay(propId);
		ArrayList rowList;
		//ArrayList mainList=new ArrayList();
		String khasraNum="";
		String khasraArea="";
		String lagaan="";
		String rinPustika="";
		String[] finalArr=new String[4];
		if(list!=null && list.size()>0){
			
			for(int i=0;i<list.size();i++){
			
				rowList=(ArrayList)list.get(i);
				String str=rowList.toString();
				str=str.substring(1, str.length()-1);
				String[] strArr=str.split(",");
				if(i==0){
					khasraNum =strArr[0]+SuppleDocSQL.SPLIT_CONSTANT_KHASRA;
					khasraArea=strArr[1]+SuppleDocSQL.SPLIT_CONSTANT_KHASRA;
					lagaan    =strArr[2]+SuppleDocSQL.SPLIT_CONSTANT_KHASRA;
					rinPustika=strArr[3]+SuppleDocSQL.SPLIT_CONSTANT_KHASRA;
				}else{
				khasraNum =khasraNum+strArr[0]+SuppleDocSQL.SPLIT_CONSTANT_KHASRA;
				khasraArea=khasraArea+strArr[1]+SuppleDocSQL.SPLIT_CONSTANT_KHASRA;
				lagaan    =lagaan+strArr[2]+SuppleDocSQL.SPLIT_CONSTANT_KHASRA;
				rinPustika=rinPustika+strArr[3]+SuppleDocSQL.SPLIT_CONSTANT_KHASRA;
				
				}
			}
			finalArr[0]=khasraNum;
			finalArr[1]=khasraArea;
			finalArr[2]=lagaan;
			finalArr[3]=rinPustika;
			
			return finalArr;	
		}else{
		return null;
		}
	}
public ArrayList<SuppleDocDTO> getWardPatwari(String language,
		String subAreaId,String tehsilId) {
	SuppleDocDAO dao = new SuppleDocDAO();
		ArrayList  wardPatwariList=null;
		ArrayList <SuppleDocDTO>returnList=null;
	try{
		wardPatwariList=dao.getWardPatwari(language,subAreaId,tehsilId);
		if(wardPatwariList!=null&& wardPatwariList.size()>0)
		{
			returnList=new ArrayList<SuppleDocDTO>();
			for(int i=0;i<wardPatwariList.size();i++)
			{
				ArrayList subList=(ArrayList) wardPatwariList.get(i);
				SuppleDocDTO propDTO= new SuppleDocDTO();
				propDTO.setWardIds(((String) subList.get(0)+"~"+(String) subList.get(2)));
				propDTO.setWardPatwariName((String) subList.get(1));
				returnList.add(propDTO);
				
			}
			
		}
		
		return returnList;
	}catch (Exception e) {
		logger.error(e);
		return null;
	}
}

public String getMuncipalFlag(String subAreaId)
{
	SuppleDocDAO dao = new SuppleDocDAO();
	if(subAreaId.equalsIgnoreCase("5"))
	{
		return "RF";
	}
	else
	{	
	String municipalId=dao.getMunicipalId(subAreaId);
	if("1".equalsIgnoreCase(municipalId)||"2".equalsIgnoreCase(municipalId))
	{
		return "NF";
	}
	else if("4".equalsIgnoreCase(municipalId))
	{
		return "RF";
	}
	else
	{
		return "N";
	}
	}
}

}