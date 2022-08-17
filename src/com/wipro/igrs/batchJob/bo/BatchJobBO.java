package com.wipro.igrs.batchJob.bo;

import java.util.ArrayList;

import com.wipro.igrs.batchJob.dao.BatchJobDAO;
import com.wipro.igrs.empRetirement.dao.EmpDAO;



public class BatchJobBO
{
	
	public ArrayList getJobNames() throws Exception
	 {
		BatchJobDAO dao = new BatchJobDAO();
		 	return dao.getJobNames();
	 }
	public void batchJobCallProc()throws Exception
	{
		BatchJobDAO dao = new BatchJobDAO();
	 	 dao.batchJobCallProc();
	}

}
