
/* Copyright (c) 2007-08 WIPRO INFOTECH. All Rights Reserved.
 * 
 *  This software is the confidential and proprietary information of WIPRO
 *  ("Confidential Information"). You shall not
 *  disclose such Confidential Information and shall use it only in
 *  accordance with the terms of the license agreement you entered into
 *  with WIPRO.
 *  
 * WIPRO MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE 
 * SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF 
 * MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, OR NON-INFRINGEMENT. WIPRO SHALL NOT BE 
 * LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF 
 * USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS 
 * DERIVATIVES.
 */ 

/* 
 * FILE NAME: CreateHolidayBO.java
 * @author NIHAR R MISHRA
 * @version 1.0 
 * INITIAL CODING: 8th AUGUST 2008
 * MODIFIED ON:	   None
 * @DESCRIPTION:   THIS CLASS REPRESENTS THE BO FOR HOLIDAY CREATION.
 */


package com.wipro.igrs.holidaylist.bo;

import org.apache.log4j.Logger;

import com.wipro.igrs.holidaylist.dao.CreateHolidayDAO;
import com.wipro.igrs.holidaylist.dto.HolidayListDTO;

/**
 * @author NIHAR M.
 *
 */
public class CreateHolidayBO {
	
	private Logger logger = (Logger) Logger.getLogger(CreateHolidayBO.class);
	
	
	
	/**
	 * @return boolean
	 */
	public boolean createNewHoliday(HolidayListDTO formDTO, String userId) throws Exception {
		
		CreateHolidayDAO holidayDAO = new CreateHolidayDAO();
		String[] param = new String[6];
		param[0] = formDTO.getFinancialYear();
		param[1] = formDTO.getDate();
		param[2] = formDTO.getHolidayDescription();
		param[3] = "P";
		param[4] = userId;
		param[5] = "P";
		
		logger.debug("************************************");
		logger.debug(param[0]);
		logger.debug(param[1]);
		logger.debug(param[2]);
		logger.debug(param[3]);
		logger.debug(param[4]);
		
		return holidayDAO.createNewHoliday(param);
	}

}
