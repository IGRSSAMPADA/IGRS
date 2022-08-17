

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
 * FILE NAME: ViewHolidayBO.java
 * @author NIHAR R MISHRA
 * @version 1.0 
 * INITIAL CODING: 8th AUGUST 2008
 * MODIFIED ON:	   None
 * @DESCRIPTION:   THIS CLASS REPRESENTS THE BO FOR HOLIDAY VIEW.
 */

package com.wipro.igrs.holidaylist.bo;

import java.util.ArrayList;
import java.util.HashMap;

import com.wipro.igrs.holidaylist.dao.ViewHolidayDAO;
import com.wipro.igrs.holidaylist.dto.HolidayListDTO;

/**
 * @author NIHAR M.
 *
 */
public class ViewHolidayBO {

	ViewHolidayDAO holidayDAO; 

	/**
	 * @throws Exception
	 */
	public ViewHolidayBO() throws Exception{
		holidayDAO = new ViewHolidayDAO();
	}


	/**
	 * @return ArrayList
	 */
	public HashMap getAllHolidayList() throws Exception{
		return holidayDAO.getAllHolidayList();
	}


	/**
	 * @param removeHoliday
	 * @return boolean
	 */
	public boolean removeHoliday(String[] holiday) throws Exception {
		return holidayDAO.removeHoliday(holiday);
	}


	/**
	 * @param hid
	 * @return HolidayListDTO
	 * @throws Exception
	 */
	public HolidayListDTO getIndHolidayDetails(String hid) throws Exception {

		HolidayListDTO dto = new HolidayListDTO();
		String[] param = new String[1];
		param[0] = hid;

		ArrayList ret = holidayDAO.getIndHolidayDetails(param);

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList list = (ArrayList) ret.get(i);

				dto.setHolidayId((String) list.get(0));
				dto.setFinancialYear((String) list.get(1));
				dto.setDate((String) list.get(2));
				System.out.println("DATE:-   "+(String) list.get(2));
				dto.setHolidayDescription((String) list.get(3));
				dto.setHolidayStatus((String) list.get(4));
			}
		}
		return dto;
	}


	/**
	 * @param dto
	 * @return boolean
	 * @throws Exception
	 */
	public boolean updateHolidayDetails(HolidayListDTO dto,String userId) throws Exception{

		String[] param = new String[5];
		param[0] = dto.getHolidayDescription();
		param[1] = dto.getHolidayStatus();
		param[2] = dto.getDate();
		param[3] = userId;
		param[4] = dto.getHolidayId();

		return holidayDAO.updateHolidayDetails(param);
	}

}
