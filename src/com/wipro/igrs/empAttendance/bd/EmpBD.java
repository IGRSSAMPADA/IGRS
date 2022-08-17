package com.wipro.igrs.empAttendance.bd;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.empAttendance.action.EmployeeAttendanceAction;
import com.wipro.igrs.empAttendance.bo.EmpBO;
import com.wipro.igrs.empAttendance.dto.EmpDTO;


public class EmpBD
{

	private  Logger logger = 
		(Logger) Logger.getLogger(EmpBD.class);	
	
	public boolean empAttendanceCreate(EmpDTO empDto,String date,String empRecords,String noOfRec) throws Exception
	{
		boolean flag = false;
		EmpBO bo = null;
		int j=0;
		try
		{
			ArrayList setList = new ArrayList();
				bo = new EmpBO();
				
				String employeeId = empDto.getEmpId();
				
				flag = bo.checkEmpId(employeeId);
				if(flag)
				{
					throw new Exception("EmployeeId Not Found Please Try Again ");
				}
				
				String empValues[] = empRecords.split(",");
				for(int i=0;i<empValues.length;i++)
				{
					System.out.println("Values are "+empValues[i]);
				}
				
			
				
			for(int i=0;i<noOfRec.length();i++)
			{
				//System.out.println("Count is "+noOfRec);
				for(;j<empValues.length;)
						{
									String[] param1 = new String[8];
										String empId = empDto.getEmpId();
									
										String empDate = empValues[j];
										j++;
										String inTime = empValues[j];
										j++;
										String inAmPm = empValues[j];
										j++;
										String outTime = empValues[j];
										j++;
										String outAmPm = empValues[j];
										j++;
										String status = empValues[j];
										j++;
										String remark = empValues[j];
										j++;
										param1[0] = empId;
										param1[1] = empDate;
										param1[2] = inTime;
										param1[3] = inAmPm;
										param1[4] = outTime;
										param1[5] = outAmPm;
										param1[6] = status;
										param1[7] = remark;
										
											flag = bo.empAttendanceCreate(param1); 
										
				}
			}
			
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
	
		}
		return flag;
	}

	
			
}
