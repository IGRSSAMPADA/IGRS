package com.wipro.igrs.batchJob.bd;

import java.sql.SQLException;
import java.util.ArrayList;

import com.wipro.igrs.batchJob.bo.BatchJobBO;
import com.wipro.igrs.batchJob.dto.BatchJobDTO;
import com.wipro.igrs.empRetirement.bo.EmpBO;
import com.wipro.igrs.empRetirement.form.EmployeeForm;




public class BatchJobBD
{
	
	 public  ArrayList getJobNames(BatchJobDTO jobDto) throws SQLException
		{
			ArrayList mainList = new ArrayList();
			BatchJobBO bo = null;
			
			try
			{
				
				bo = new BatchJobBO();
				ArrayList list1 = bo.getJobNames();
				if(list1!=null)
				{	
				for(int i=0;i<list1.size();i++)
				{
					BatchJobDTO job = new BatchJobDTO();
					ArrayList list =(ArrayList)list1.get(i);
					job.setRunDate((String)list.get(0));
					job.setBatchJobName((String)list.get(1));
					job.setApplicationErrorMessage((String)list.get(2));
					
//					jobDto.setBatchJobName((String)list.get(0));
//					System.out.println("Job name is 1111111111111111111111111111111111111111111"+(String)list.get(0));
//					jobDto.setApplicationErrorMessage((String)list.get(1));
//					System.out.println("Appli Error is 22222222222222222222222222222222222222222"+(String)list.get(1));
//					jobDto.setRunDate((String)list.get(2));
//					System.out.println("RunDate is 3333333333333333333333333333333333333333333"+(String)list.get(2));
					
						//	reg.setStartTime((String)list.get(3) + " : " +(String)list.get(4) + " "+(String)list.get(5));
							
							
					mainList.add(job);
				}
			  }
			}
			catch(Exception e)
			{
				//logger.error("this is Exception in getAllHeads in BD" + e);
		    }
			return mainList;
		}
	 
	 
	 		
	 public void batchJobCallProc() throws Exception
		{
			ArrayList getFullList = new ArrayList();
		//	BatchJobForm emp = null;
			boolean flag = false;
			BatchJobBO bo = null;
			try
			{
				
				bo = new BatchJobBO();
				
				bo.batchJobCallProc();
				
		//	emp.setAmount(earnAmt);
				
				
				
			}
			catch(SQLException e)
			{
				//logger.error("this is Exception in headDetails in BD" + e);
		    }
			
		//	return emp;
		}
	 
	 

}
