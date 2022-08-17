package com.wipro.igrs.serviceProviderTop.bd;

import java.util.ArrayList;

import com.wipro.igrs.serviceProviderTop.dao.ServProDAO;
import com.wipro.igrs.serviceProviderTop.dto.DTO;
import com.wipro.igrs.transactionHistory.DAO.TranHistoryDAO;

public class ServProBD {

	public ArrayList getDistrict(String language) {
		TranHistoryDAO dao = new TranHistoryDAO();
		ArrayList alist = dao.getDistrict(language);
		ArrayList list = new ArrayList();
		DTO dto;
		if (alist != null) {
			for (int i = 0; i < alist.size(); i++) {
				ArrayList lst = (ArrayList) alist.get(i);
				dto = new DTO();
				dto.setDistrictId((String) lst.get(0));
				dto.setDistrict((String) lst.get(1));
				list.add(dto);
			}
		}

		return list;
	}

	public ArrayList getData(DTO dto) {
		ArrayList finalList = new ArrayList();

		ServProDAO dao = new ServProDAO();
		ArrayList alist = dao.getData(dto);
		if (alist != null) {
			for (int i = 0; i < alist.size(); i++) {
				ArrayList rowList = (ArrayList) alist.get(i);
				dto.setSrNo(i+1);
				dto.setName((String)rowList.get(0));
				dto.setAddress((String)rowList.get(1));
				finalList.add(dto);
				dto = new DTO();
			}
		}
		return finalList;
	}

}
