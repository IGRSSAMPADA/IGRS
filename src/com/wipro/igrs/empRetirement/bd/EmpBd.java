package com.wipro.igrs.empRetirement.bd;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.empRetirement.action.SearchEmployeeIdAction;
import com.wipro.igrs.empRetirement.bo.EmpBO;
import com.wipro.igrs.empRetirement.form.EmployeeForm;



public class EmpBd
{
	private  Logger logger = 
		(Logger) Logger.getLogger(EmpBd.class);
	
	 public EmployeeForm empDetails(String empId,String typeRetir,String date) throws Exception
		{
			ArrayList getFullList = new ArrayList();
			EmployeeForm emp = null;
			boolean flag = false;
			
			try
			{
				emp = new EmployeeForm();
				EmpBO bo = new EmpBO();
				String EmpId = emp.getEmpId();
			
				flag = bo.checkEmpId(empId);
				if(flag)
				{
					throw new Exception("EmployeeId Not Found Please Try Again ");
				}
				String pensionVal = bo.getPension(empId,typeRetir,date);
				String gratuityVal = bo.getGratuity(empId,date,typeRetir);
				String earnLeave = bo.getEarnLeave(empId,date);
				String earnAmt = bo.getEarnAmt(empId,date);
				
				ArrayList  gisList = bo.getGis(empId);
				ArrayList pfList = bo.getPf(empId);
				ArrayList list1 = bo.empDetails(empId);
				ArrayList list2 = bo.empDesg(empId);
				
				
				emp.setPension(pensionVal);
				emp.setGratuity(gratuityVal);
				emp.setLeave(earnLeave);
				emp.setAmount(earnAmt);
				
				if(list1!=null)
				{	
				for(int i=0;i<list1.size();i++)
				
							{
								ArrayList list =(ArrayList)list1.get(i);
								emp.setFirstName((String)list.get(0));
								emp.setMiddleName((String)list.get(1));
								emp.setLastName((String)list.get(2));
								emp.setDateOfJoing((String)list.get(3));
								emp.setDateOfBirth((String)list.get(4));
								emp.setGender((String)list.get(5));
							}
					}
				if(list2!=null)
				{	
				for(int i=0;i<list2.size();i++)
				
							{
								ArrayList list =(ArrayList)list2.get(i);
								emp.setDesignation((String)list.get(0));
								
							}
					}
				if(gisList!=null)
				{	
				for(int i=0;i<gisList.size();i++)
				
							{
								ArrayList list =(ArrayList)gisList.get(i);
								emp.setGis((String)list.get(0));
								
							}
				}
				
				if(pfList!=null)
				{	
				for(int i=0;i<pfList.size();i++)
				
							{
								ArrayList list =(ArrayList)pfList.get(i);
								emp.setPf((String)list.get(0));
								
							}
				}
				
			}
			catch(SQLException e)
			{
				logger.error("this is Exception in  BD" + e);
		    }
			
			return emp;
		}
}
