/**
 * 
 */
package com.wipro.igrs.castemaster.bo;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.wipro.igrs.castemaster.dao.CasteDAO;
import com.wipro.igrs.castemaster.dao.PersonCategoryDAO;
import com.wipro.igrs.castemaster.dto.CasteDTO;
import com.wipro.igrs.castemaster.dto.PersonCategoryDTO;
import com.wipro.igrs.castemaster.exceptions.CasteAlreadyExistException;
import com.wipro.igrs.exception.IGRSException;

/**
 * @author HMOHAM
 *
 */
public class CasteBO {

	private Logger logger = Logger.getLogger(CasteBO.class);
	private CasteDAO casteDAO = new CasteDAO();
	private PersonCategoryDAO personCategoryDAO;
	
	public CasteBO() throws Exception {
		personCategoryDAO = new PersonCategoryDAO();
	}
	
	public boolean addCaste(CasteDTO casteDTO, String roleId, String funId, String userId) throws CasteAlreadyExistException{
		
		if( !casteDAO.isCasteExist(casteDTO.getName()) )
			return casteDAO.insertCaste(casteDTO,roleId,funId,userId);
		else
			throw new CasteAlreadyExistException("attemping to add a Caste with the same name as an already existing caste");
	}
	
	public boolean deleteCaste(String casteId, String userId1,String roleId, String funId, String userId) {
		
		if(casteDAO.getCasteById(casteId) != null)
			return casteDAO.deleteCaste(casteId, userId1,roleId,funId,userId);
		
		return false;
		
	}
	
	public boolean updateCaste(CasteDTO newCasteDTO,String roleId, String funId,String userId) throws IGRSException{
		
		CasteDTO oldCaste = casteDAO.getCasteById(newCasteDTO.getId());
		if( oldCaste == null)
			// no cast with the specified id
		throw new IGRSException("No Cast found with the specified Id : " + newCasteDTO.getId() + ", when attempting to update Caste");
	
			// if caste name has not changed
		if( !newCasteDTO.getName().equals(oldCaste.getName()))
			if( casteDAO.isCasteExist(newCasteDTO.getName())) {
				// new Cast name already exist
				throw new CasteAlreadyExistException("Cast name already exist");
			}
		
		
		return casteDAO.updateCaste(newCasteDTO,roleId,funId,userId);
	}

	public void deleteCaste(String[] castIdList, String userId1,String roleId, String funId, String userId) {
		
		for (int i = 0; i < castIdList.length; i++) {
			deleteCaste(castIdList[i], userId1,roleId,funId,userId);
		}
	}

	public List getAllCastes() {
		return casteDAO.getAllCastes();
	}
	
	public List getAllCategories() {
		return personCategoryDAO.getAllCategories();
	}
	
	public boolean isCasteNameExist(String castName) {
		return casteDAO.isCasteExist(castName);
	}
	
	public CasteDTO getCasteById(String castedId) {
		return casteDAO.getCasteById(castedId);
	}
	
	public static void main(String[] args) {
		PersonCategoryDAO categoryDAO = null;
		try {
			categoryDAO = new PersonCategoryDAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List allCategories = categoryDAO.getAllCategories();
		
		for (int i = 0; i < allCategories.size(); i++) {
			PersonCategoryDTO categoryDTO = (PersonCategoryDTO)allCategories.get(i);
			System.out.println(categoryDTO.getName());
			
		}
	}
}
