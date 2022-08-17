package com.wipro.igrs.hrpayroll.hrp.dao;

import java.util.ArrayList;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.hrpayroll.hrp.dto.BasicSlabDTO;
import com.wipro.igrs.hrpayroll.hrp.sql.CommonSQL;

public class BasicSlabDAO {
	DBUtility dbUtil = null;
	private Logger logger = (Logger) Logger.getLogger(BasicSlabDAO.class);
	
	
	public boolean createBasicSlabDetails(String slabArray[]){
		boolean flag=false;
		
		try {
			dbUtil = new DBUtility();
			dbUtil.setAutoCommit(false);
			String sql = CommonSQL.INSERT_BASIC_SLAB_DETAILS;
			// logger.info("Inside leaveDAO");
			dbUtil.createPreparedStatement(sql);
			// logger.info("Inside Sql"+sql);
			flag = dbUtil.executeUpdate(slabArray);

			if (flag == true) {
				dbUtil.commit();
			} else {
				dbUtil.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		logger.debug("In the DAO---> "+flag);
		
		return flag;
	}
	public boolean updateBasicSlabDetails(String slabArray[]){
		boolean flag=false;
		
		try {
			dbUtil = new DBUtility();
			dbUtil.setAutoCommit(false);
			String sql = CommonSQL.UPDATE_BASIC_SLAB_DETAILS;
			 logger.info("Inside DAO  "+sql);
			dbUtil.createPreparedStatement(sql);
			 logger.info("Inside Sql"+sql);
			flag = dbUtil.executeUpdate(slabArray);

			if (flag == true) {
				dbUtil.commit();
			} else {
				dbUtil.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		logger.debug("In the DAO---> "+flag);
		
		return flag;
	}
	public ArrayList retrieveBasicSlabList() throws Exception{
		ArrayList list=new ArrayList();
		ArrayList list1=null;
		String sql="";
		BasicSlabDTO basicSlabDTO = null;
    	try {
    		dbUtil = new DBUtility();
        	sql = CommonSQL.GET_BASIC_SLAB_DETAILS;
        	dbUtil.createStatement();
           
            ArrayList mainList= dbUtil.executeQuery(sql);
            for (int i=0;i<mainList.size();i++) 
             {
            	list1 = (ArrayList)mainList.get(i);
            	basicSlabDTO = new BasicSlabDTO();
            	
            	basicSlabDTO.setBasicSlabId((String)list1.get(0));
            	basicSlabDTO.setBasicSlabMin((String)list1.get(1));
            	basicSlabDTO.setBasicSlabMax((String)list1.get(2));
            	basicSlabDTO.setBasicSlabIncrement((String)list1.get(3));
            	//basicSlabDTO.setBasicSlabValidFrom((String)list1.get(4));
            	//basicSlabDTO.setBasicSlabValidTo((String)list1.get(5));
            	//basicSlabDTO.setBasicSlabRemarks((String)list1.get(6));
            	basicSlabDTO.setStatus((String)list1.get(5));
            	
            	list.add(basicSlabDTO);
             }
        } catch (Exception e) {
        	e.printStackTrace();
        }
        finally {
   		 	dbUtil.closeConnection();
   	    }

		return list;
	}
	
	public BasicSlabDTO retrieveBasicSlabDetails(String slabId) throws Exception{
		ArrayList list1=null;
		String sql="";
		BasicSlabDTO basicSlabDTO = null;
    	try {
    		dbUtil = new DBUtility();
        	sql = "SELECT SLAB_ID,SLAB_MIN,SLAB_MAX,SLAB_INCREMENT, TO_CHAR(SLAB_VALID_FROM, 'DD/MM/YYYY'), " +
        			"TO_CHAR(SLAB_VALID_TO, 'DD/MM/YYYY'),SLAB_REMARKS,SLAB_STATUS	FROM IGRS_EMP_BASIC_SLAB_MASTER " +
        	"WHERE SLAB_ID ='"+slabId+"'";
        	dbUtil.createStatement();
           
            ArrayList mainList= dbUtil.executeQuery(sql);
            for (int i=0;i<mainList.size();i++) 
             {
            	list1 = (ArrayList)mainList.get(i);
            	basicSlabDTO = new BasicSlabDTO();
            	
            	basicSlabDTO.setBasicSlabId((String)list1.get(0));
            	basicSlabDTO.setBasicSlabMin((String)list1.get(1));
            	basicSlabDTO.setBasicSlabMax((String)list1.get(2));
            	basicSlabDTO.setBasicSlabIncrement((String)list1.get(3));
            	basicSlabDTO.setBasicSlabValidFrom((String)list1.get(4));
            	basicSlabDTO.setBasicSlabValidTo((String)list1.get(5));
            	basicSlabDTO.setBasicSlabRemarks((String)list1.get(6));
            	basicSlabDTO.setStatus((String)list1.get(7));
            	
             }
        } catch (Exception e) {
        	e.printStackTrace();
        }
        finally {
   		 	dbUtil.closeConnection();
   	    }

		return basicSlabDTO;
	}
	public String basicSlabId(){
		
		String id="";
		int i=0;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			String sql = "SELECT MAX(SLAB_ID) FROM IGRS_EMP_BASIC_SLAB_MASTER";

			id = dbUtil.executeQry(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(id == null)
			id="0";
		i=Integer.parseInt(id)+1;

		return String.valueOf(i);
	}
	
    public boolean deleteSlabMaster(String slabId) throws Exception
    {
    	String param[] = new String[1];
    	param[0] = slabId;
    	 boolean boo = true;
    	String sql=CommonSQL.DELETE_SLAB_MASTER; 
    try {
    	dbUtil = new DBUtility();
    	dbUtil.createPreparedStatement(sql);
         boo = dbUtil.executeUpdate(param);
        if (boo){
        	dbUtil.commit();
         }    
        else
        {	
        	dbUtil.rollback();
        }
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	finally {
    		try {
    			dbUtil.closeConnection();
    		} catch (Exception e) {
    			e.printStackTrace();
        	}
        
   	 	}
    	return boo;
    }

    public ArrayList getAllottedSlabList(String gradeId) throws Exception{
    	ArrayList List=new ArrayList();
    	ArrayList list1=new ArrayList();
		String sql="";
    	try {
    		dbUtil = new DBUtility();
    		BasicSlabDTO basicSlabDTO = null;
        	sql = "SELECT BASIC_SLAB_ID	FROM IGRS_EMP_GRADE_BASIC_MAP " +
        	"WHERE GRADE_ID ='"+gradeId+"' AND SLAB_MAP_STATUS = 'A' ";
        	dbUtil.createStatement();
            ArrayList mainList= dbUtil.executeQuery(sql);
            for (int i=0;i<mainList.size();i++) 
             {
            	basicSlabDTO = new BasicSlabDTO();
            	list1 = (ArrayList)mainList.get(i);
            	//basicSlabDTO.setBasicSlabId((String)mainList.get(0));
            	List.add((String)list1.get(0));
             }
        } catch (Exception e) {
        	e.printStackTrace();
        }
        finally {
   		 	dbUtil.closeConnection();
   	    }
        	logger.debug("List in dao====> "+List.size());
		return List;

    }
	public boolean updateOldSlabs(String oldSlabIds, String gradeId){
		boolean update = true;
		String [] slabArray = new String [2];
		
		StringTokenizer st = new StringTokenizer(oldSlabIds, "*");

		try {
			dbUtil = new DBUtility();
			dbUtil.setAutoCommit(false);
			String sql = CommonSQL.UPDATE_BASIC_SLAB_MAP;
			 logger.info("Inside DAO  "+sql);
			dbUtil.createPreparedStatement(sql);
			 logger.info("Inside Sql"+sql);
			 while (st.hasMoreTokens()) {
				 slabArray[0] = st.nextToken();
				 slabArray[1] = gradeId;
			 update = dbUtil.executeUpdate(slabArray);
			 }
			if (update == true) {
				dbUtil.commit();
			} else {
				dbUtil.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		logger.debug("In the DAO---> "+update);

		
		return update;
	}
	public boolean updateNewSlabs(String newSlabIds, String gradeId){
		boolean update = true;
		StringTokenizer st = new StringTokenizer(newSlabIds, "*");
		String [] slabArray = new String [2];
		
		try {
			dbUtil = new DBUtility();
			dbUtil.setAutoCommit(false);
			 while (st.hasMoreTokens()) {
				 String token=st.nextToken();
        	String sql="SELECT SLAB_MAP_STATUS	FROM IGRS_EMP_GRADE_BASIC_MAP WHERE GRADE_ID='"+gradeId+"' AND BASIC_SLAB_ID='"+token+"'";
        	dbUtil.createStatement();
            ArrayList mainList= dbUtil.executeQuery(sql);

			if(mainList != null && mainList.size() > 0)
			{
				 slabArray[0] = token;
				 slabArray[1] = gradeId;

			String insertSql = CommonSQL.UPDATE_BASIC_SLAB_MAP_STATUS;
			 logger.info("Inside DAO  "+insertSql);
			dbUtil.createPreparedStatement(insertSql);
			update = dbUtil.executeUpdate(slabArray);
			}
			else{
				String updateSql = CommonSQL.INSERT_BASIC_SLAB_MAP_STATUS;
				 slabArray[0] = gradeId;
				 slabArray[1] = token;
				 logger.info("Inside Sql"+updateSql);
					dbUtil.createPreparedStatement(updateSql);
					update = dbUtil.executeUpdate(slabArray);

			}
			 }
			if (update == true) {
				dbUtil.commit();
			} else {
				dbUtil.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		return update;
	}
    
	public static void main(String ar[]) throws Exception{
		BasicSlabDAO basicSlabDAO = new BasicSlabDAO();
		//String id = basicSlabDAO.basicSlabId();
		ArrayList l1=basicSlabDAO.retrieveBasicSlabList();
		System.out.println("list--->  "+l1);
	}
}
