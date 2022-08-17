/**
 * 
 */
package com.wipro.igrs.castemaster.sql;

/**
 * @author HMOHAM
 *
 */
public interface PersonCategorySQL {

	String GET_CATEGORY_NAMES = "SELECT CATEGORY_ID, CATEGORY_NAME," +
			" CATEGORY_STATUS, CREATED_BY, UPDATED_BY, CREATED_DATE, UPDATED_DATE" +
			" FROM IGRS_PERSON_CATEGORY_MASTER" +
			" WHERE CATEGORY_STATUS <> 'R'";
}
