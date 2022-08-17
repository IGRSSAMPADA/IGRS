package com.wipro.igrs.formmptc27.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.formmptc27.dto.FormMPTC27DTO;
import com.wipro.igrs.formmptc27.sql.FormMPTC27SQL;



public class FormMPTC27DAO {

	private static Logger log = org.apache.log4j.Logger.getLogger(FormMPTC27DAO.class);
	private DBUtility utility;
	
	public FormMPTC27DAO(){
		
		try {
			utility = new DBUtility();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public FormMPTC27DTO getEmpDetailsById(String id){
		
		FormMPTC27DTO formMPTC27DTO = new FormMPTC27DTO();
		
		try {
			utility.createPreparedStatement(FormMPTC27SQL.SELECT_EMP_BY_ID);
			String[] param = new String[]{id};
			ArrayList list = utility.executeQuery(param);
			
			for (int i = 0; i < list.size(); i++) {
				int index = 0;
				formMPTC27DTO.setEmpId((String)((ArrayList)list.get(i)).get(index++));
				formMPTC27DTO.setFName((String)((ArrayList)list.get(i)).get(index++));
				formMPTC27DTO.setMName((String)((ArrayList)list.get(i)).get(index++));
				formMPTC27DTO.setLName((String)((ArrayList)list.get(i)).get(index++));
				formMPTC27DTO.setOfficiating((String)((ArrayList)list.get(i)).get(index++));
				formMPTC27DTO.setMiniSal((String)((ArrayList)list.get(i)).get(index++));
				formMPTC27DTO.setMaxSal((String)((ArrayList)list.get(i)).get(index++));
				formMPTC27DTO.setIncrementAmount((String)((ArrayList)list.get(i)).get(index++));
				formMPTC27DTO.setCompVal((String)((ArrayList)list.get(i)).get(index++));
				formMPTC27DTO.setSalDate((String)((ArrayList)list.get(i)).get(index++));
				formMPTC27DTO.setEffectiveDate((String)((ArrayList)list.get(i)).get(index++));
				formMPTC27DTO.setLeaveFrom((String)((ArrayList)list.get(i)).get(index++));
				formMPTC27DTO.setLeaveTo((String)((ArrayList)list.get(i)).get(index++));
				
				
				formMPTC27DTO.setEmpName(formMPTC27DTO.getFName() + " " + formMPTC27DTO.getMName() + " " + formMPTC27DTO.getLName());
				formMPTC27DTO.setGradePayScale(formMPTC27DTO.getMiniSal() + "-" + formMPTC27DTO.getIncrementAmount() + "-" + formMPTC27DTO.getMaxSal());
				double compVal = Double.parseDouble(formMPTC27DTO.getCompVal());
				double incrementVal = Double.parseDouble(formMPTC27DTO.getIncrementAmount());
				double num = compVal + incrementVal;
				formMPTC27DTO.setFuturePay(num + "");
				if(formMPTC27DTO.getOfficiating().equals("o")){
					formMPTC27DTO.setOfficiatingStr("Officiating");
				}
				else if(formMPTC27DTO.getOfficiating().equals("s")){
					formMPTC27DTO.setOfficiatingStr("Substantive");
				}
			
					
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return formMPTC27DTO;
	}
	
	
	public ArrayList getAllIds(){
		ArrayList ids = new ArrayList();
		
		try {
			utility.createStatement();
			ArrayList list = utility.executeQuery(FormMPTC27SQL.SELECT_ALL_EMP_ID);
			String id;
			for (int i = 0; i < list.size(); i++) {
				id = new String();
				id = (String)(((ArrayList)list.get(i)).get(0));
				ids.add(id);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ids;
	}
	
	
	
	
	public static void main(String[] args) {
		FormMPTC27DAO formMPTC27DAO = new FormMPTC27DAO();
		FormMPTC27DTO empDetailsById = formMPTC27DAO.getEmpDetailsById("prakash");
		System.out.println("Start:");
		System.out.println("EmpId:"+empDetailsById.getEmpId());
		System.out.println("FName:"+empDetailsById.getFName());
		System.out.println("MName:"+empDetailsById.getMName());
		System.out.println("LName:"+empDetailsById.getLName());
		System.out.println("Officiating:"+empDetailsById.getOfficiating());
		System.out.println("MiniSal:"+empDetailsById.getMiniSal());
		System.out.println("MaxSal:"+empDetailsById.getMaxSal());
		System.out.println("IncrementAmount:"+empDetailsById.getIncrementAmount());
		System.out.println("CompVal:"+empDetailsById.getCompVal());
		System.out.println("SalDate:"+empDetailsById.getSalDate());
		System.out.println("EffectiveDate:"+empDetailsById.getEffectiveDate());
		System.out.println("LeaveFrom:"+empDetailsById.getLeaveFrom());
		System.out.println("LeaveTo:"+empDetailsById.getLeaveTo());
		System.out.println("EmpName:"+empDetailsById.getEmpName());
	}
	
}
