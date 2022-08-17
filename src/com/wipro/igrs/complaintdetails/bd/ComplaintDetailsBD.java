package com.wipro.igrs.complaintdetails.bd;

import java.util.List;

import com.wipro.igrs.complaintdetails.bao.ComplaintDetailsBAO;
import com.wipro.igrs.complaintdetails.dao.ComplaintDetailsDAO;
import com.wipro.igrs.complaintdetails.dto.ComplaintDetailsCriteriaDTO;

public class ComplaintDetailsBD {
	
	//ComplaintDetailsDAO compDAO = new ComplaintDetailsDAO();
	ComplaintDetailsBAO compBAO = new ComplaintDetailsBAO();
	
	public  List search(ComplaintDetailsCriteriaDTO dto)
	{
		return compBAO.search(dto);
	}
	
	public  List listComplaintStatus()
	{
		return compBAO.listComplaintStatus();
	}

}
