package com.wipro.igrs.bankmaster.dao;


import java.util.ArrayList;
import java.util.List;

import com.wipro.igrs.bankmaster.dto.BankDTO;
import com.wipro.igrs.bankmaster.sql.BankCommonSQL;
import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.db.DBUtility;


public class BankDAO implements IBankDAO{

	DBUtility dbUtility = null;
    String sql = null;
    ArrayList subList = null;
    BankDTO dto=null;
    IGRSCommon igrsCommon = null;
   
    
    
    //Singleton
    private static BankDAO bankDAO;
	private BankDAO() {
		
	}
	public static void main(String[] args){
		BankDAO dao=getInstance();
		BankDTO dto=new BankDTO();
	
		try {
			dto=dao.getBankById("BANK03");
			
		//	System.out.println(dto.getBankName());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//dto.setBankId("BANK02");
		//dto.setBankName("BANK MASR");
	//	dto.setBankAddress("Egypt");
	//	dto.setBankPhoneNumber("23000566");
		/*
		try {
			//System.out.println(dao.isExist(dto));
			dao.addBankMaster(dto);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		*/
	/*	
		 try {
			ArrayList allBanks=dao.getBankList();
			for (int i = 0; i < allBanks.size(); i++) {
				System.out.println("##"+((BankDTO)allBanks.get(i)).getBankId());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 */
		
		
	}
	public static BankDAO getInstance()
	{
		if(bankDAO==null)
			bankDAO=new BankDAO();
		
		return bankDAO;
		
	}
  
	 //Method for inserting Bank details into Database
    public void addBankMaster(BankDTO bankDto, String roleId, String funId, String userId) throws Exception
    {
    	 
    	String[] param = new String[5];
    	param[0]=bankDto.getBankName();
    	param[1]=bankDto.getBankAddress();
    	param[2]=bankDto.getBankPhoneNumber();
    	param[3]=bankDto.getCreatedBy();
    	param[4]=bankDto.getUpdateBy();
    	
    	sql=BankCommonSQL.INSERT_BANK_MASTER;
    	try {
    		igrsCommon = new IGRSCommon();
    		dbUtility = new DBUtility();
            dbUtility.createPreparedStatement(sql);
            boolean boo = dbUtility.executeUpdate(param);
            if (boo){
            	dbUtility.commit();
            	igrsCommon.saveLogDet("IGRS_BANK_MASTER","INSERT","T",funId,userId,roleId);
            	}
            else
            {
                dbUtility.rollback();
            igrsCommon.saveLogDet("IGRS_BANK_MASTER","INSERT","F",funId,userId,roleId);
            }
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
    
    //Method for updating Bank details into Database
    public void updateBankMaster(BankDTO bankDto, String roleId, String funId, String userId) throws Exception
    {
    	String param[] = new String[6];
    	param[0] = bankDto.getBankName();
    	param[1] = bankDto.getBankAddress();
    	param[2] = bankDto.getBankPhoneNumber();
    	param[3] = bankDto.getStatus();
    	param[4] = bankDto.getUpdateBy();
    	param[5] = bankDto.getBankId();
    	sql=BankCommonSQL.UPDATE_BANK_MASTER; 
    	      
    try {
    	dbUtility = new DBUtility();
        dbUtility.createPreparedStatement(sql);
        boolean boo = dbUtility.executeUpdate(param);
        if (boo)
        {
        	 dbUtility.commit();
        	 igrsCommon.saveLogDet("IGRS_BANK_MASTER","UPDATE","T",funId,userId,roleId);
         }    
        else{
        dbUtility.rollback();
        igrsCommon.saveLogDet("IGRS_BANK_MASTER","UPDATE","F",funId,userId,roleId);
        }
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
    
    
    //Method for getting Bank List from Database
    public ArrayList getBankList() throws Exception {
    	ArrayList bankList = new ArrayList();
    	dbUtility = new DBUtility();
    	try {
        	 dbUtility = new DBUtility();
        	sql = BankCommonSQL.SELECT_BANK_MASTER;
            dbUtility.createStatement();
           
            ArrayList mainList1= dbUtility.executeQuery(sql);
            for (int i=0;i<mainList1.size();i++) 
             {
            	subList = (ArrayList)mainList1.get(i);
            	dto = new BankDTO();
            	dto.setBankId(subList.get(0).toString());
                dto.setBankName(subList.get(1).toString());
                dto.setBankAddress(subList.get(2).toString());
                dto.setBankPhoneNumber(subList.get(3).toString());
                dto.setCreatedBy(subList.get(4).toString());
                dto.setCreatedDate(subList.get(5).toString());
                dto.setUpdateBy(subList.get(6).toString());
                dto.setUpdateDate(subList.get(7).toString());
                dto.setStatus(subList.get(8).toString());
                bankList.add(dto);
              }
            } catch (Exception e) {
            	e.printStackTrace();
            }
            finally {
       		 	dbUtility.closeConnection();
       	    }
            
         //   System.out.println(bankList.size());
        return bankList;
    }
	
    
    
    //Method for getting bank data by bank id from Database
    public BankDTO getBankById(String bankId) throws Exception
    {
    	try{
    		 dbUtility = new DBUtility();	
    	 sql = BankCommonSQL.SELECT_BANK_MASTER_ID;
    	 dbUtility.createPreparedStatement(sql);
    	 String[] param=new String[1];
    	 param[0]=bankId;
    	 ArrayList list=dbUtility.executeQuery(param);
    	 ArrayList list1 = (ArrayList)list.get(0);
    	 dto = new BankDTO();
    	 dto.setBankId(list1.get(0).toString());
    	 dto.setBankName(list1.get(1).toString());
    	 if(list1.get(2)!=null)
    		 dto.setBankAddress(list1.get(2).toString());
    	 else
    		 dto.setBankAddress("");
    	 if(list1.get(3)!=null)
    		 dto.setBankPhoneNumber(list1.get(3).toString());
    	 else
    		 dto.setBankPhoneNumber("");
    	 dto.setStatus(list1.get(4).toString());
    	}catch(Exception e){
    		e.printStackTrace();
    	 }finally {
    		 dbUtility.closeConnection();
    	 }
    	    return dto;
    }
    
    //Method for softly deleting Bank from Database
    public void deleteBankMaster(String bankId, String roleId, String funId, String userId) throws Exception
    {
    	String param[] = new String[1];
    	param[0] = bankId;
    	
    	sql=BankCommonSQL.DELETE_BANK_MASTER; 
    try {
    	igrsCommon = new IGRSCommon();
    	 dbUtility = new DBUtility();
        dbUtility.createPreparedStatement(sql);
        boolean boo = dbUtility.executeUpdate(param);
        if (boo){
        	dbUtility.commit();
        	igrsCommon.saveLogDet("IGRS_BANK_MASTER","DELETE","T",funId,userId,roleId);
         }    
        else
        {	
         dbUtility.rollback();
        igrsCommon.saveLogDet("IGRS_BANK_MASTER","DELETE","F",funId,userId,roleId);
        }
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
    
	public boolean isExist(BankDTO bankDto) throws Exception {
		
		ArrayList list=new ArrayList();
		
		try{
   		 	dbUtility = new DBUtility();	
   		 	sql = BankCommonSQL.SELECT_BANK_MASTER_BY_NAME;
   		 	dbUtility.createPreparedStatement(sql);
   		 	String[] param=new String[1];
   		 	param[0]=bankDto.getBankName();
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
