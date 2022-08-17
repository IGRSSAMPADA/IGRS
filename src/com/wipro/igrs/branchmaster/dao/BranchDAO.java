package com.wipro.igrs.branchmaster.dao;


import java.util.ArrayList;
import java.util.List;

import com.wipro.igrs.branchmaster.dto.BranchDTO;
import com.wipro.igrs.branchmaster.sql.BranchCommonSQL;
import com.wipro.igrs.db.DBUtility;


public class BranchDAO implements IBranchDAO{

	DBUtility dbUtility = null;
    String sql = null;
    ArrayList subList = null;
    BranchDTO dto=null;
   
    
    
    //Singleton
    private static BranchDAO branchDAO;
	private BranchDAO() {
		
	}
	public static void main(String[] args){
		BranchDAO dao=getInstance();
		BranchDTO dto=new BranchDTO();
	
		try {
			dto=dao.getBranchById("BANK03");
			
			System.out.println(dto.getBranchName());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//dto.setBranchId("BANK02");
		//dto.setBranchName("BANK MASR");
	//	dto.setBranchAddress("Egypt");
	//	dto.setBranchPhoneNumber("23000566");
		/*
		try {
			//System.out.println(dao.isExist(dto));
			dao.addBranchMaster(dto);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		*/
	/*	
		 try {
			ArrayList allBranchs=dao.getBranchList();
			for (int i = 0; i < allBranchs.size(); i++) {
				System.out.println("##"+((BranchDTO)allBranchs.get(i)).getBranchId());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 */
		
		
	}
	public static BranchDAO getInstance()
	{
		if(branchDAO==null)
			branchDAO=new BranchDAO();
		
		return branchDAO;
		
	}
  
	 //Method for inserting Branch details into Database
    public void addBranchMaster(BranchDTO branchDto) throws Exception
    {
    	 
    	String[] param = new String[7];
    	param[0]=branchDto.getBranchName();
    	param[1]=branchDto.getBranchAddress();
    	param[2]=branchDto.getBranchPhoneNumber();
    	param[3]=branchDto.getBranchEmail();
    	//param[4]=branchDto.getBranchCode();
    	param[4]=branchDto.getBankId();
    	param[5]=branchDto.getCreatedBy();
    	param[6]=branchDto.getUpdateBy();
    	
    	sql=BranchCommonSQL.INSERT_BRANCH_MASTER;
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
    
    //Method for updating Branch details into Database
    public void updateBranchMaster(BranchDTO branchDto) throws Exception
    {
    	String param[] = new String[8];
    	param[0] = branchDto.getBranchName();
    	param[1] = branchDto.getBranchAddress();
    	param[2] = branchDto.getBranchPhoneNumber();
    	param[3]=branchDto.getBranchEmail();
    	//param[4]=branchDto.getBranchCode();
    	param[4] = branchDto.getStatus();
    	param[5] = branchDto.getBankId();
    	param[6] = branchDto.getUpdateBy();
    	param[7] = branchDto.getBranchId();
    	
    	sql=BranchCommonSQL.UPDATE_BRANCH_MASTER; 
    	      
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
    
    
    //Method for getting Branch List from Database
    public ArrayList getBranchList(String bankId) throws Exception {
    	System.out.println("Getting branches og bank : "+bankId);
    	ArrayList branchList = new ArrayList();
    	dbUtility = new DBUtility();
    	try {
        	 dbUtility = new DBUtility();
        	 sql = BranchCommonSQL.SELECT_BRANCH_MASTER;
        	 dbUtility.createPreparedStatement(sql);
        	 String[] param=new String[1];
        	 param[0]=bankId;
        	 ArrayList mainList1=dbUtility.executeQuery(param);
            for (int i=0;i<mainList1.size();i++) 
             {
            	subList = (ArrayList)mainList1.get(i);
            	dto = new BranchDTO();
            	if(subList.get(0)!=null)
            		dto.setBranchId(subList.get(0).toString());
            	else
            		dto.setBankId("");
            	if(subList.get(1)!=null)
            		dto.setBranchName(subList.get(1).toString());
            	else
            		dto.setBranchName("");
            	if(subList.get(2)!=null)
            		dto.setBranchAddress(subList.get(2).toString());
            	else
            		dto.setBranchAddress("");
            	if(subList.get(3)!=null)
            		dto.setBranchPhoneNumber(subList.get(3).toString());
            	else
            		dto.getBranchPhoneNumber();
            	if(subList.get(4)!=null)
            		dto.setBranchEmail(subList.get(4).toString());
            	else 
            		dto.setBranchEmail("");
//            	if(subList.get(5)!=null)
//            		dto.setBranchCode(subList.get(5).toString());
//            	else
//            		dto.setBranchCode("");
            	if(subList.get(5)!=null)
            		dto.setCreatedBy(subList.get(5).toString());
            	else
            		dto.setCreatedBy("");
            	 if(subList.get(6)!=null)
            		 dto.setCreatedDate(subList.get(6).toString());
            	 else
            		 dto.setCreatedDate("");
            	 if(subList.get(7)!=null)
            		 dto.setUpdateBy(subList.get(7).toString());
            	 else
            		 dto.setUpdateBy("");
            	 if(subList.get(8)!=null)
            		 dto.setUpdateDate(subList.get(8).toString());
            	 else
            		 dto.setUpdateDate("");
                if(subList.get(9)!=null)
                	dto.setStatus(subList.get(9).toString());
                else
                	dto.setStatus("");
                branchList.add(dto);
              }
            } catch (Exception e) {
            	e.printStackTrace();
            }
            finally {
       		 	dbUtility.closeConnection();
       	    }
            
            System.out.println(branchList.size());
        return branchList;
    }
	
    
    
    //Method for getting branch data by branch id from Database
    public BranchDTO getBranchById(String branchId) throws Exception
    {
    	try{
    		System.out.println("%%%%"+branchId);
    		 dbUtility = new DBUtility();	
    	 sql = BranchCommonSQL.SELECT_BRANCH_MASTER_ID;
    	 dbUtility.createPreparedStatement(sql);
    	 String[] param=new String[1];
    	 param[0]=branchId;
    	 ArrayList list=dbUtility.executeQuery(param);
    	 ArrayList list1 = (ArrayList)list.get(0);
    	 dto = new BranchDTO();
    	 dto.setBranchId(list1.get(0).toString());
    	 dto.setBranchName(list1.get(1).toString());
    	 if(list1.get(2)!=null)
    		 dto.setBranchAddress(list1.get(2).toString());
    	 else
    		 dto.setBranchAddress("");
    	 if(list1.get(3)!=null)
    		 dto.setBranchPhoneNumber(list1.get(3).toString());
    	 else
    		 dto.setBranchPhoneNumber("");
    	 if(list1.get(4)!=null)
    		 dto.setBranchEmail(list1.get(4).toString());
    	 else
    		 dto.setBranchEmail("");
//    	 if(list1.get(5)!=null)
//    		 dto.setBranchCode(list1.get(5).toString());
//    	 else
//    		 dto.setBranchCode("");
    	 System.out.println("name "+list1.get(5).toString()+"                id  "+list1.get(6).toString());
    	 if(list1.get(5)!=null)
    		 dto.setBankName(list1.get(5).toString());
    	 else
    		 dto.setBankName("");
    	 if(list1.get(6)!=null)
    		 dto.setBankId(list1.get(6).toString());
    	 else
    		 dto.setBankId("");
    	 
    	 //dto.setStatus(list1.get(4).toString());
    	 
    	 
    	}catch(Exception e){
    		e.printStackTrace();
    	 }finally {
    		 dbUtility.closeConnection();
    	 }
    	    return dto;
    }
    
    //Method for softly deleting Branch from Database
    public void deleteBranchMaster(BranchDTO branchDto) throws Exception
    {
    	String param[] = new String[1];
    	param[0] = branchDto.getBranchId();
    	
    	sql=BranchCommonSQL.DELETE_BRANCH_MASTER; 
    	      
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
    
	public boolean isExist(BranchDTO branchDto) throws Exception {
		
		ArrayList list=new ArrayList();
		
		try{
   		 	dbUtility = new DBUtility();	
   		 	sql = BranchCommonSQL.SELECT_BRANCH_MASTER_BY_NAME;
   		 	dbUtility.createPreparedStatement(sql);
   		 	String[] param=new String[1];
   		 	param[0]=branchDto.getBranchName();
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
}
