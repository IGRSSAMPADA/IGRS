package com.wipro.igrs.areaManagement.bd;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.areaManagement.dao.areaManagementDAO;
import com.wipro.igrs.areaManagement.dto.areaManagementDTO;
import com.wipro.igrs.areaManagement.form.areaManagementForm;


public class areaManagementBD {
	private static Logger logger = org.apache.log4j.Logger.getLogger(areaManagementBD.class);

	/*public ArrayList getCountry(String language) throws Exception {
		areaManagementDAO areamanagedao = new areaManagementDAO();
		ArrayList ret = areamanagedao.getCountry(language);
		ArrayList list = new ArrayList();

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				areaManagementDTO dto = new areaManagementDTO();
				dto.setCountryId((String) lst.get(0));
				if("en".equalsIgnoreCase(language))
				{
				dto.setCountryName((String) lst.get(1));
				}
				else if("hi".equalsIgnoreCase(language))
				{
					dto.setCountryName((String) lst.get(2));
				}
				list.add(dto);
			}
		}
		return list;
	}*/

	public ArrayList getState(String lang, areaManagementForm form) throws Exception {
		areaManagementDAO areamanagedao = new areaManagementDAO();
		ArrayList ret = areamanagedao.getState();
		ArrayList list = new ArrayList();

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				areaManagementDTO dto = new areaManagementDTO();
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
	
public areaManagementDTO getdetails(String lang, String id, String type, areaManagementDTO dto) throws Exception {
		areaManagementDAO areamanagedao = new areaManagementDAO();
		return areamanagedao.getdetails(lang,id,type,dto);
		
		
	}



	public ArrayList getDistrict(String lang, String stateId,String officeId) throws Exception {
		areaManagementDAO areamanagedao = new areaManagementDAO();
		ArrayList ret = areamanagedao.getDistrict(stateId,officeId);
		ArrayList list = new ArrayList();

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				areaManagementDTO dto = new areaManagementDTO();
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

	public ArrayList getTehsil(String lang, String distId) throws Exception {
		areaManagementDAO areamanagedao = new areaManagementDAO();
		ArrayList ret = areamanagedao.getTehsil(distId);
		ArrayList list = new ArrayList();

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				areaManagementDTO dto = new areaManagementDTO();
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

	public ArrayList getArea(String lang, String tehId, String logicId) throws Exception {
		areaManagementDAO areamanagedao = new areaManagementDAO();
		ArrayList ret = areamanagedao.getArea(lang,logicId);
		ArrayList list = new ArrayList();

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				areaManagementDTO dto = new areaManagementDTO();
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

	public ArrayList getSubArea(String lang, String areaId, String tehId) throws Exception {
		areaManagementDAO areamanagedao = new areaManagementDAO();
		ArrayList ret = areamanagedao.getSubArea(tehId,areaId);
		ArrayList list = new ArrayList();

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				areaManagementDTO dto = new areaManagementDTO();
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
		areaManagementDAO areamanagedao = new areaManagementDAO();
		ArrayList ret = areamanagedao.getWard(subareaId,areaId,tehId);
		ArrayList list = new ArrayList();

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				areaManagementDTO dto = new areaManagementDTO();
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

	public ArrayList getMohalla(String lang, String tehId, String areaId,
			String subareaId, String wardId) throws Exception {
		ArrayList list = new ArrayList();
		areaManagementDAO areamanagedao = new areaManagementDAO();
		ArrayList ret = areamanagedao.getMohalla(tehId,areaId,subareaId,wardId);
		

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				areaManagementDTO dto = new areaManagementDTO();
				dto.setMohallaListId((String) lst.get(0));
				
				if("en".equalsIgnoreCase(lang))
				{
					dto.setMohallaSubClauseName((String) lst.get(4));
				}
				else if("hi".equalsIgnoreCase(lang))
				{
					dto.setMohallaSubClauseName((String) lst.get(5));
				}
				
				if("en".equalsIgnoreCase(lang))
				{
				dto.setMohallaListName((String) lst.get(1));
				}
				else if("hi".equalsIgnoreCase(lang))
				{
					dto.setMohallaListName((String) lst.get(2));
				}
				list.add(dto);
			}
		}
		return list;
	}

	public ArrayList getsubClause(String lang) throws Exception {
		ArrayList list = new ArrayList();
		areaManagementDAO areamanagedao = new areaManagementDAO();
		ArrayList ret = areamanagedao.getsubClause();
		

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				areaManagementDTO dto = new areaManagementDTO();
				dto.setSubClauseListId((String) lst.get(0));	
				if("en".equalsIgnoreCase(lang))
				{
				dto.setSubClauseListName((String) lst.get(1));
				}
				else if("hi".equalsIgnoreCase(lang))
				{
					dto.setSubClauseListName((String) lst.get(2));
				}
				list.add(dto);
			}
		}
		return list;
	}

	public boolean saveTehsilData(areaManagementForm form, String userId) throws Exception {
		areaManagementDAO areamanagedao = new areaManagementDAO();
		return areamanagedao.saveTehsilData(form,userId);
	}

	public boolean saveSubareaData(areaManagementForm form, String userId) throws Exception {
		areaManagementDAO areamanagedao = new areaManagementDAO();
		return areamanagedao.saveSubareaData(form,userId);
	}

	public boolean saveWardData(areaManagementForm form, String userId) throws Exception {
		areaManagementDAO areamanagedao = new areaManagementDAO();
		return areamanagedao.saveWardData(form,userId);
	}

	public boolean saveMohallaData(areaManagementForm form, String userId) throws Exception {
		areaManagementDAO areamanagedao = new areaManagementDAO();
		return areamanagedao.saveMohallaData(form,userId);
	}

	public ArrayList getmunicipalBody(String lang) throws Exception {
		ArrayList list = new ArrayList();
		areaManagementDAO areamanagedao = new areaManagementDAO();
		ArrayList ret = areamanagedao.getmunicipalBody();
		

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				areaManagementDTO dto = new areaManagementDTO();
				dto.setMunciBodyListId((String) lst.get(0));	
				if("en".equalsIgnoreCase(lang))
				{
				dto.setMunciBodyListName((String) lst.get(1));
				}
				else if("hi".equalsIgnoreCase(lang))
				{
					dto.setMunciBodyListName((String) lst.get(2));
				}
				list.add(dto);
			}
		}
		return list;
	}

	public ArrayList getwardorPatwari(String lang, String stateId) throws Exception {
		ArrayList list = new ArrayList();
		areaManagementDAO areamanagedao = new areaManagementDAO();
		ArrayList ret = areamanagedao.getwardorPatwari();
		

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				areaManagementDTO dto = new areaManagementDTO();
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
	
	public boolean deleteTehsil(String[] abc) {
		areaManagementDAO areamanagedao = new areaManagementDAO();
		return areamanagedao.deleteTehsil(abc);
	}

	public boolean deleteSubArea(String[] abc) {
		areaManagementDAO areamanagedao = new areaManagementDAO();
		return areamanagedao.deleteSubArea(abc);
	}

	public boolean deleteWard(String[] abc) {
		areaManagementDAO areamanagedao = new areaManagementDAO();
		return areamanagedao.deleteWard(abc);
	}

	public boolean deleteMohalla(String[] abc) {
		areaManagementDAO areamanagedao = new areaManagementDAO();
		return areamanagedao.deleteMohalla(abc);
	}

	public boolean updatedetails(String lang, String id, String type,
			areaManagementDTO dto) throws Exception {
		areaManagementDAO areamanagedao = new areaManagementDAO();
		return areamanagedao.updatedetails(lang,id,type,dto);
	}

	public areaManagementDTO getConstRate(areaManagementDTO dto) {
		areaManagementDAO areamanagedao = new areaManagementDAO();
		return areamanagedao.getConstRate(dto);
	}

	public boolean updateConstData(areaManagementForm form, String userId) {
		areaManagementDAO areamanagedao = new areaManagementDAO();
		return areamanagedao.updateConstData(form,userId);
	}

	
}
