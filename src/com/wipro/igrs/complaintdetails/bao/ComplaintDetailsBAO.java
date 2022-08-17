package com.wipro.igrs.complaintdetails.bao;

import java.util.List;

import com.wipro.igrs.complaintdetails.dao.ComplaintDetailsDAO;
import com.wipro.igrs.complaintdetails.dto.ComplaintDetailsCriteriaDTO;




public class ComplaintDetailsBAO {
	
	ComplaintDetailsDAO compDAO = new ComplaintDetailsDAO();
	
	public  List search(ComplaintDetailsCriteriaDTO dto)
	{
		return compDAO.search(dto);
	}

	public List listComplaintStatus() {
		// TODO Auto-generated method stub
		return compDAO.listComplaintStatus();
	}

}
