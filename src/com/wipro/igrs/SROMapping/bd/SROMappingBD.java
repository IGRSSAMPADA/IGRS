package com.wipro.igrs.SROMapping.bd;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.SROMapping.dao.SROMappingDAO;
import com.wipro.igrs.SROMapping.dto.SROMappingDTO;
import com.wipro.igrs.SROMapping.form.SROMappingForm;
import com.wipro.igrs.areaManagement.dao.areaManagementDAO;
import com.wipro.igrs.areaManagement.form.areaManagementForm;


public class SROMappingBD {
	private static Logger logger = org.apache.log4j.Logger.getLogger(SROMappingBD.class);

	SROMappingDAO sroDAO = new SROMappingDAO();
		

	public static ArrayList getState(String lang, SROMappingForm form) throws Exception {
		SROMappingDAO sroDAO = new SROMappingDAO();
		ArrayList ret = sroDAO.getState();
		ArrayList list = new ArrayList();

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				SROMappingDTO dto = new SROMappingDTO();
				form.getAppdto().setStateId((String) lst.get(0));
				dto.setStateId((String) lst.get(0));	
				if("en".equalsIgnoreCase(lang))
				{
				dto.setStateName((String) lst.get(1));
				}
				else if("hi".equalsIgnoreCase(lang))
				{
					dto.setStateName((String) lst.get(2));
				}
				list.add(dto);
			}
		}
		return list;
	}

	public static ArrayList getDistrict(String lang, String StateId,String officeId ) throws Exception {
		SROMappingDAO sroDAO = new SROMappingDAO();
		ArrayList ret = sroDAO.getDistrict(StateId,officeId);
		ArrayList list = new ArrayList();

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				SROMappingDTO dto = new SROMappingDTO();
				dto.setDistrictId((String) lst.get(0));	
				if("en".equalsIgnoreCase(lang))
				{
				dto.setDistrictName((String) lst.get(1));
				}
				else if("hi".equalsIgnoreCase(lang))
				{
					dto.setDistrictName((String) lst.get(2));
				}
				list.add(dto);
			}
		}
		return list;
	}
	
	public static ArrayList getDistrictEdit(String lang, String stateId,String officeId) throws Exception {
		SROMappingDAO sroDAO = new SROMappingDAO();
		ArrayList ret = sroDAO.getDistrictEdit(stateId,officeId);
		ArrayList list = new ArrayList();

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				SROMappingDTO dto = new SROMappingDTO();
				dto.setDistrictId((String) lst.get(0));	
				if("en".equalsIgnoreCase(lang))
				{
				dto.setDistrictName((String) lst.get(1));
				}
				else if("hi".equalsIgnoreCase(lang))
				{
					dto.setDistrictName((String) lst.get(2));
				}
				list.add(dto);
			}
		}
		return list;
	}
	
	public static ArrayList getsroOfc(String lang,String distId) throws Exception {
		SROMappingDAO sroDAO = new SROMappingDAO();
		ArrayList ret = sroDAO.getsroOfc(distId);
		ArrayList list = new ArrayList();

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				SROMappingDTO dto = new SROMappingDTO();
				dto.setSroListId((String) lst.get(0));	
				if("en".equalsIgnoreCase(lang))
				{
				dto.setSroName((String) lst.get(1));
				}
				else if("hi".equalsIgnoreCase(lang))
				{
					dto.setSroName((String) lst.get(2));
				}
				list.add(dto);
			}
		}
		return list;
	}
	
	public static ArrayList getsroOfcEdit(String lang,String distId ,String tehId,String areaId,String subAreaId) throws Exception {
		SROMappingDAO sroDAO = new SROMappingDAO();
		ArrayList ret = sroDAO.getsroOfcEdit(distId,tehId,areaId,subAreaId);
		ArrayList list = new ArrayList();

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				SROMappingDTO dto = new SROMappingDTO();
				dto.setSroListId((String) lst.get(0));	
				if("en".equalsIgnoreCase(lang))
				{
				dto.setSroName((String) lst.get(1));
				}
				else if("hi".equalsIgnoreCase(lang))
				{
					dto.setSroName((String) lst.get(2));
				}
				list.add(dto);
			}
		}
		return list;
	}
	
	public static ArrayList getTehsil(String lang, String distId) throws Exception {
		SROMappingDAO sroDAO = new SROMappingDAO();
		ArrayList ret = sroDAO.getTehsil(distId);
		ArrayList list = new ArrayList();

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				SROMappingDTO dto = new SROMappingDTO();
				dto.setTehsilListId((String) lst.get(0));	
				if("en".equalsIgnoreCase(lang))
				{
				dto.setTehsilListName((String) lst.get(1));
				}
				else if("hi".equalsIgnoreCase(lang))
				{
					dto.setTehsilListName((String) lst.get(2));
				}
				list.add(dto);
			}
		}
		return list;
	}

	public static ArrayList getTehsilEdit(String lang, String distId) throws Exception {
		SROMappingDAO sroDAO = new SROMappingDAO();
		ArrayList ret = sroDAO.getTehsilEdit(distId);
		ArrayList list = new ArrayList();

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				SROMappingDTO dto = new SROMappingDTO();
				dto.setTehsilListId((String) lst.get(0));	
				if("en".equalsIgnoreCase(lang))
				{
				dto.setTehsilListName((String) lst.get(1));
				}
				else if("hi".equalsIgnoreCase(lang))
				{
					dto.setTehsilListName((String) lst.get(2));
				}
				list.add(dto);
			}
		}
		return list;
	}

	public ArrayList getArea(String lang, String tehId, String distId) throws Exception {
		SROMappingDAO sroDAO = new SROMappingDAO();
		ArrayList ret = sroDAO.getArea(distId,tehId);
		ArrayList list = new ArrayList();

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				SROMappingDTO dto = new SROMappingDTO();
				dto.setAreaId((String) lst.get(0));	
				if("en".equalsIgnoreCase(lang))
				{
				dto.setAreaName((String) lst.get(1));
				}
				else if("hi".equalsIgnoreCase(lang))
				{
					dto.setAreaName((String) lst.get(2));
				}
				list.add(dto);
			}
		}
		return list;
	}

	public ArrayList getAreaEdit(String lang, String distId, String tehId) throws Exception {
		SROMappingDAO sroDAO = new SROMappingDAO();
		ArrayList ret = sroDAO.getAreaEdit(distId,tehId);
		ArrayList list = new ArrayList();

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				SROMappingDTO dto = new SROMappingDTO();
				dto.setAreaId((String) lst.get(0));	
				if("en".equalsIgnoreCase(lang))
				{
				dto.setAreaName((String) lst.get(1));
				}
				else if("hi".equalsIgnoreCase(lang))
				{
					dto.setAreaName((String) lst.get(2));
				}
				list.add(dto);
			}
		}
		return list;
	}

	
	public ArrayList getSubArea(String lang, String areaId, String tehId,String distId) throws Exception {
		SROMappingDAO sroDAO = new SROMappingDAO();
		ArrayList ret = sroDAO.getSubArea(tehId,areaId, distId);
		ArrayList list = new ArrayList();

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				SROMappingDTO dto = new SROMappingDTO();
				dto.setSubAreaListId((String) lst.get(0));	
				if("en".equalsIgnoreCase(lang))
				{
				dto.setSubAreaListName((String) lst.get(1));
				}
				else if("hi".equalsIgnoreCase(lang))
				{
					dto.setSubAreaListName((String) lst.get(2));
				}
				list.add(dto);
			}
		}
		return list;
	}
	
	public ArrayList getSubAreaEdit(String lang, String areaId, String tehId) throws Exception {
		SROMappingDAO sroDAO = new SROMappingDAO();
		ArrayList ret = sroDAO.getSubAreaEdit(tehId,areaId);
		ArrayList list = new ArrayList();

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				SROMappingDTO dto = new SROMappingDTO();
				dto.setSubAreaListId((String) lst.get(0));	
				if("en".equalsIgnoreCase(lang))
				{
				dto.setSubAreaListName((String) lst.get(1));
				}
				else if("hi".equalsIgnoreCase(lang))
				{
					dto.setSubAreaListName((String) lst.get(2));
				}
				list.add(dto);
			}
		}
		return list;
	}

	public ArrayList getWard(String lang, String subareaId, String areaId,
			String tehId) throws Exception {
		SROMappingDAO sroDAO = new SROMappingDAO();
		ArrayList ret = sroDAO.getWard(subareaId,areaId,tehId);
		ArrayList list = new ArrayList();

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				SROMappingDTO dto = new SROMappingDTO();
				dto.setWardListId((String) lst.get(0));	
				if("en".equalsIgnoreCase(lang))
				{
				dto.setWardListName((String) lst.get(1));
				}
				else if("hi".equalsIgnoreCase(lang))
				{
					dto.setWardListName((String) lst.get(2));
				}
				list.add(dto);
			}
		}
		return list;
	}

	public static ArrayList getwardEdit(String lang, String tehId, String areaId, String subareaId, String sroId)
	throws Exception {
		SROMappingDAO sroDAO = new SROMappingDAO();
		ArrayList ret = sroDAO.getwardEdit(tehId,areaId,subareaId,sroId);
		ArrayList list = new ArrayList();

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				SROMappingDTO dto = new SROMappingDTO();
				dto.setWardListId((String) lst.get(0));	
				if("en".equalsIgnoreCase(lang))
				{
				dto.setWardListName((String) lst.get(1));
				}
				else if("hi".equalsIgnoreCase(lang))
				{
					dto.setWardListName((String) lst.get(2));
				}
				list.add(dto);
			}
		}
		return list;
	}
	
	public boolean saveWardData(SROMappingForm form, String userId,String[] ward_data) throws Exception {
		return sroDAO.saveWardData(form,userId,ward_data);
	}	
	public boolean saveAllWard(String[] ward_data, String[] ofc_data) throws Exception {
		return sroDAO.saveAllWard(ward_data,ofc_data);
	}

	public boolean updateWard(String[] abc) {
		return sroDAO.updateWard(abc);

	}
	/*
	public static ArrayList getwardorPatwari(String lang, String stateId) throws Exception {
		ArrayList list = new ArrayList();
		SROMappingDAO sroDAO = new SROMappingDAO();
		ArrayList ret = sroDAO.getwardorPatwari();
		

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				SROMappingDTO dto = new SROMappingDTO();
				dto.setWardorPatwariListId((String) lst.get(0));	
				if("en".equalsIgnoreCase(lang))
				{
				dto.setWardorPatwariListName((String) lst.get(1));
				}
				else if("hi".equalsIgnoreCase(lang))
				{
					dto.setWardorPatwariListName((String) lst.get(2));
				}
				list.add(dto);
			}
		}
		return list;
	}
	*/
	/*public boolean deleteTehsil(String[] abc) {
		SROMappingDAO sroDAO = new SROMappingDAO();
		return sroDAO.deleteTehsil(abc);
	}

	public boolean deleteSubArea(String[] abc) {
		SROMappingDAO sroDAO = new SROMappingDAO();
		return sroDAO.deleteSubArea(abc);
	}*/

	/*public boolean deleteWard(String[] abc) {
		SROMappingDAO sroDAO = new SROMappingDAO();
		return sroDAO.deleteWard(abc);
	}

	public boolean deleteMohalla(String[] abc) {
		SROMappingDAO sroDAO = new SROMappingDAO();
		return sroDAO.deleteMohalla(abc);
	}

	public boolean updatedetails(String lang, String id, String type,
			SROMappingDTO dto) throws Exception {
		SROMappingDAO sroDAO = new SROMappingDAO();
		return sroDAO.updatedetails(lang,id,type,dto);
	}

	public SROMappingDTO getConstRate(SROMappingDTO dto) {
		SROMappingDAO sroDAO = new SROMappingDAO();
		return sroDAO.getConstRate(dto);
	}

	public boolean updateConstData(SROMappingForm form, String userId) {
		SROMappingDAO sroDAO = new SROMappingDAO();
		return sroDAO.updateConstData(form,userId);
	}
*/
	
}
