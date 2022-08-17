/**
 * 
 */
package com.wipro.igrs.castemaster.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.wipro.igrs.castemaster.dto.PersonCategoryDTO;
import com.wipro.igrs.castemaster.sql.PersonCategorySQL;
import com.wipro.igrs.db.DBUtility;

/**
 * @author HMOHAM
 *
 */
public class PersonCategoryDAO {

	DBUtility dbUtility;
	
	public PersonCategoryDAO() throws Exception {
		dbUtility = new DBUtility();
	}
	
	
	public List getAllCategories() {
		try {
			dbUtility.createStatement();
			
			ArrayList result = dbUtility.executeQuery(PersonCategorySQL.GET_CATEGORY_NAMES);
			
			ArrayList categories = new ArrayList(result.size());
			
			
			
			for (int i = 0; i < result.size(); i++) {
				ArrayList subList = (ArrayList)result.get(i);
				PersonCategoryDTO personCategory = new PersonCategoryDTO();
				
				int indx = 0;
				
				personCategory.setId( (String)subList.get(indx++));
				personCategory.setName((String)subList.get(indx++));
				personCategory.setStatus((String)subList.get(indx++));
				
				personCategory.setCreatedBy((String)subList.get(indx++));
				personCategory.setUpdatedBy((String)subList.get(indx++));
				
				personCategory.setCreatedDate((String)subList.get(indx++));
				personCategory.setUpdatedDate((String)subList.get(indx++));
				
				categories.add(personCategory);
			}
			
			return categories;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
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
