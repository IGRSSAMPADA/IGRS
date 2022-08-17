package com.wipro.igrs.salarygrademaster.dao;

import java.util.ArrayList;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.salarygrademaster.bd.ISalaryGradeBD;
import com.wipro.igrs.salarygrademaster.bd.SalaryGradeBD;
import com.wipro.igrs.salarygrademaster.dto.ComponentDTO;
import com.wipro.igrs.salarygrademaster.dto.GradeDTO;
import com.wipro.igrs.salarygrademaster.dto.SalaryGradeDTO;
import com.wipro.igrs.salarygrademaster.sql.SalaryGradeCommonSQL;

public class SalaryGradeDAO implements ISalaryGradeDAO{

	DBUtility dbUtility = null;
    String sql = null;
    ArrayList subList = null;
    SalaryGradeDTO salaryGradeDTO=null;
    ComponentDTO componentDTO=null;
    GradeDTO gradeDTO=null;
    
    
////adding salary grade..........working 100%
	public void addSalaryGrade(SalaryGradeDTO salaryGrade) throws Exception {
		
		String[] param = new String[4];
    	param[0]=salaryGrade.getGradeId();
    	param[1]=salaryGrade.getComponentId();
    	param[2]=salaryGrade.getComponentValue();
    	param[3]=salaryGrade.getEmpId();

    	
    	sql=SalaryGradeCommonSQL.INSERT_SALARY_GRADE_MASTER;
    	try {
    		dbUtility = new DBUtility();
            dbUtility.createPreparedStatement(sql);
            boolean boo = dbUtility.executeUpdate(param);
            if (boo){
            	dbUtility.commit();
            	}
                
            else
                dbUtility.rollback();
           } catch (Exception e) {
        	   e.printStackTrace();
           }
           finally {
        	   try {
        		   dbUtility.closeConnection();
        	   }catch(Exception x) {
        		   x.printStackTrace();
        	   }
      	 }
		
	}
	////deleting salary grade..........working 100%
	public void deleteSalaryGrade(SalaryGradeDTO salaryGrade) throws Exception {

		String[] param = new String[1];
    	param[0]=salaryGrade.getSalCompGradeId();
	
    	sql=SalaryGradeCommonSQL.DELETE_SALARY_GRADE_MASTER;
    	      
    try {
    	 dbUtility = new DBUtility();
        dbUtility.createPreparedStatement(sql);
        boolean boo = dbUtility.executeUpdate(param);
        if (boo){
        	dbUtility.commit();
         }    
        else
            dbUtility.rollback();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	finally {
    		try {
    			dbUtility.closeConnection();
    		} catch (Exception e) {
    			e.printStackTrace();
        	}
        
   	 	}
		
	}

	public void updateSalaryGrade(SalaryGradeDTO salaryGrade) throws Exception {
		
		String[] param = new String[5];
    	param[0]=salaryGrade.getGradeId();
    	param[1]=salaryGrade.getComponentId();
    	param[2]=salaryGrade.getComponentValue();
    	param[3]=salaryGrade.getEmpId();
    	param[4]=salaryGrade.getSalCompGradeId();
    
    	
    	sql=SalaryGradeCommonSQL.UPDATE_SALARY_GRADE_MASTER;
    	      
    try {
    	 dbUtility = new DBUtility();
        dbUtility.createPreparedStatement(sql);
        boolean boo = dbUtility.executeUpdate(param);
        if (boo){
        	dbUtility.commit();
         }    
        else
            dbUtility.rollback();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	finally {
    		try {
    			dbUtility.closeConnection();
    		} catch (Exception e) {
    			e.printStackTrace();
        	}
        
   	 	}
		
	}
////getting components ..........working 100%
	public ArrayList getComponentsIDs() throws Exception {
		
		ArrayList salaryComponentsList = new ArrayList();
    	dbUtility = new DBUtility();
    	try {
        	 dbUtility = new DBUtility();
        	sql = SalaryGradeCommonSQL.SELECT_ALL_SALARY_COMPONENTS;
            dbUtility.createStatement();
           
            ArrayList mainList1= dbUtility.executeQuery(sql);
            for (int i=0;i<mainList1.size();i++) 
             {
            	subList = (ArrayList)mainList1.get(i);
            	componentDTO = new ComponentDTO();
            	componentDTO.setComponentId(subList.get(0).toString());
 
            	salaryComponentsList.add(componentDTO);
              }
            } catch (Exception e) {
            	e.printStackTrace();
            }
            finally {
       		 	dbUtility.closeConnection();
       	    }
            
            System.out.println(salaryComponentsList.size());
        return salaryComponentsList;
		
	}
	
////getting grades ..........working 100%
	public ArrayList getGradeDTOs() throws Exception {
		
		ArrayList gradeList = new ArrayList();
    	dbUtility = new DBUtility();
    	try {
        	 dbUtility = new DBUtility();
        	sql = SalaryGradeCommonSQL.SELECT_ALL_GRADE_MASTER;
            dbUtility.createStatement();
           
            ArrayList mainList1= dbUtility.executeQuery(sql);
            for (int i=0;i<mainList1.size();i++) 
             {
            	subList = (ArrayList)mainList1.get(i);
            	gradeDTO = new GradeDTO();
            	gradeDTO.setGradeId(subList.get(0).toString());
            	gradeDTO.setGradeName(subList.get(1).toString());
            	
            	gradeList.add(gradeDTO);
              }
            } catch (Exception e) {
            	e.printStackTrace();
            }
            finally {
       		 	dbUtility.closeConnection();
       	    }
            
            System.out.println(gradeList.size());
        return gradeList;
	}

////viewing all salary grades..........working 100%
	public ArrayList viewAllSalaryGrades() throws Exception {
		
		ArrayList salaryGradeList = new ArrayList();
    	dbUtility = new DBUtility();
    	try {
        	 dbUtility = new DBUtility();
        	sql = SalaryGradeCommonSQL.SELECT_ALL_SALARY_GRADE_MASTER;
            dbUtility.createStatement();
           
            ArrayList mainList1= dbUtility.executeQuery(sql);
            for (int i=0;i<mainList1.size();i++) 
             {
            	subList = (ArrayList)mainList1.get(i);
            	salaryGradeDTO = new SalaryGradeDTO();
            	salaryGradeDTO.setSalCompGradeId(subList.get(0).toString());
            	salaryGradeDTO.setGradeId(subList.get(1).toString());
            	salaryGradeDTO.setComponentId(subList.get(2).toString());
            	salaryGradeDTO.setComponentValue(subList.get(3).toString());
            	salaryGradeDTO.setEmpId(subList.get(4).toString());
            	salaryGradeDTO.setGradeName(subList.get(5).toString());
   
            	salaryGradeList.add(salaryGradeDTO);
              }
            } catch (Exception e) {
            	e.printStackTrace();
            }
            finally {
       		 	dbUtility.closeConnection();
       	    }
            
            System.out.println(salaryGradeList.size());
        return salaryGradeList;
		
	}
	

	public boolean isExist(SalaryGradeDTO salaryGradeDto) throws Exception {
		
		ArrayList list=new ArrayList();
				
				try{
		   		 	dbUtility = new DBUtility();	
		   		 	sql = SalaryGradeCommonSQL.SELECT_SALARY_GRADE_MASTER_BY_IDS;
		   		 	dbUtility.createPreparedStatement(sql);
		   		 	String[] param=new String[2];
		   		 	param[0]=salaryGradeDto.getGradeId();
		   		 	param[1]=salaryGradeDto.getComponentId();
		   		 	list=dbUtility.executeQuery(param);
		   		 
		   	}catch(Exception e){
		   	        e.printStackTrace();
		   	 }finally {
		   		 dbUtility.closeConnection();
		   	 }
		   	 
		   	 if(list.isEmpty())
					return false;
				else 
					return true;
		
	}
	
	public SalaryGradeDTO getSalaryGradeByID(String salaryGradeId) throws Exception {
		

    	try{
    		 dbUtility = new DBUtility();	
    	 sql = SalaryGradeCommonSQL.SELECT_SALARY_GRADE_MASTER_BY_ID;
    	 dbUtility.createPreparedStatement(sql);
    	 String[] param=new String[1];
    	 param[0]=salaryGradeId;
    	 ArrayList list=dbUtility.executeQuery(param);
    	 ArrayList list1 = (ArrayList)list.get(0);
    	 salaryGradeDTO = new SalaryGradeDTO();
    	 salaryGradeDTO.setSalCompGradeId(list1.get(0).toString());
    	 salaryGradeDTO.setComponentId(list1.get(1).toString());
    	 salaryGradeDTO.setGradeId(list1.get(2).toString());
    
    	}catch(Exception e){
    		e.printStackTrace();
    	 }finally {
    		 dbUtility.closeConnection();
    	 }
    	    return salaryGradeDTO;
	}
	
	public static void main(String[] args)
	{
		SalaryGradeDAO dao=new SalaryGradeDAO();
		SalaryGradeDTO salaryGradeDTO=new SalaryGradeDTO();
		ComponentDTO componentDTO=new ComponentDTO();
		GradeDTO gradeDTO=new GradeDTO();
		
		salaryGradeDTO.setSalCompGradeId("SAl_GRADE#1");
		salaryGradeDTO.setGradeId("GR2");
		salaryGradeDTO.setComponentId("DH04");
		salaryGradeDTO.setEmpId(null);
		salaryGradeDTO.setComponentValue(null);
		
		try {
				//dao.addSalaryGrade(salaryGradeDTO);
				//System.out.println("adding done !!!!");
			
			//
			//dao.deleteSalaryGrade(salaryGradeDTO);
			//System.out.println("deletion done !!!!");
			
			/*
			ArrayList salaryGrades=dao.viewAllSalaryGrades();
			for (int i = 0; i < salaryGrades.size(); i++) {
				System.out.println("Grade ID=>>"+((SalaryGradeDTO)salaryGrades.get(i)).getGradeId()+"   ComponentID=>>"+((SalaryGradeDTO)salaryGrades.get(i)).getSalComponentId());
				
			}*/
			
			/*
			ArrayList components=dao.getComponentsIDs();
			for (int i = 0; i < components.size(); i++) {
				System.out.println("Component ID=>>"+((ComponentDTO)components.get(i)).getComponentId());
				
			}
			*/
			/*
			ArrayList grades=dao.getGradeDTOs();
			for (int i = 0; i < grades.size(); i++) {
				System.out.println("Grade ID=>>"+((GradeDTO)grades.get(i)).getGradeId()+"    Grade Name=>>"+((GradeDTO)grades.get(i)).getGradeName());
				
			}*/
			
			/*
			salaryGradeDTO.setGradeId("GR2");
			salaryGradeDTO.setSalComponentId("DH019");
			if(dao.isExist(salaryGradeDTO))
				System.out.println("is exist");
			else
				System.out.println("not exist");
			*/
			
			SalaryGradeDTO old=new SalaryGradeDTO();
			ISalaryGradeBD salaryGradeBD=new SalaryGradeBD();

			salaryGradeDTO.setGradeId("GR2");
			salaryGradeDTO.setComponentId("DH04");
			salaryGradeDTO.setSalCompGradeId("SAl_GRADE#2");
			gradeDTO=new GradeDTO();
	    	old.setGradeId("GR2");
	    	old.setComponentId("DH04");
			salaryGradeBD.updateSalaryGrade(salaryGradeDTO, old);
			System.out.println("Updating done!!!");
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	

}
