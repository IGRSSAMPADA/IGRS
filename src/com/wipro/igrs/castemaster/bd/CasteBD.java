/**
 * 
 */
package com.wipro.igrs.castemaster.bd;

import java.util.List;

import com.wipro.igrs.castemaster.bo.CasteBO;
import com.wipro.igrs.castemaster.dto.CasteDTO;
import com.wipro.igrs.castemaster.exceptions.CasteAlreadyExistException;
import com.wipro.igrs.exception.IGRSException;

/**
 * @author HMOHAM
 *
 */
public class CasteBD {

	CasteBO casteBO;
	
	public CasteBD() throws Exception {
		casteBO  = new CasteBO();
	}
	
	public boolean addCaste(CasteDTO casteDTO, String roleId, String funId, String userId) throws CasteAlreadyExistException {
		return casteBO.addCaste(casteDTO,roleId,funId,userId);
	}
	
	public boolean deleteCaste(String casteId, String userId1,String roleId, String funId, String userId) {
		return casteBO.deleteCaste(casteId, userId1,roleId,funId,userId);
	}
	
	public void deleteCaste(String[] casteIdList, String userId1,String roleId, String funId, String userId) {
		casteBO.deleteCaste(casteIdList,userId1,roleId,funId,userId);
	}
	
	public List getAllCastes() {
		return casteBO.getAllCastes();
	}
	
	public boolean updateCaste(CasteDTO casteDTO,String roleId, String funId,String userId) throws IGRSException {
		return casteBO.updateCaste(casteDTO,roleId,funId,userId);
	}

	public boolean isCasteNameExist(String casteName) {
		return casteBO.isCasteNameExist(casteName);
	}
	
	public CasteDTO getCasteById(String castedId) {
		return casteBO.getCasteById(castedId);
	}
	
	public List getAllCategories() {
		return casteBO.getAllCategories();
	}
	
}
