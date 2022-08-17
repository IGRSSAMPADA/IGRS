package com.wipro.igrs.officedeptmapping.dao;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.officedepartmentmaster.sql.OfficeDeptartmentCommonSQL;
import com.wipro.igrs.officedeptmapping.dto.FullOfficeDeptDTO;
import com.wipro.igrs.officedeptmapping.dto.OfficeDeptDTO;
import com.wipro.igrs.officedeptmapping.sql.OfficeDeptMappingCommonSQL;




public class OfficeDeptMappingDAO {
	
	private ArrayList activityList = null;
	DBUtility dbUtility = null;
	String sql = null;
	OfficeDeptDTO dto = null;
	FullOfficeDeptDTO fullDTO = null;
	
	
	public String getSequenceNumber(String _seqName,String _moduleConstant) throws Exception {    

        String seqnumber="";

        try{
        
        dbUtility = new DBUtility();	
        	
        Date date=new Date();       

        Format yearformat= new SimpleDateFormat("yyyy");   

        Format monthformat= new SimpleDateFormat("MM");  

        Format dateformat= new SimpleDateFormat("dd");  

        String dfmt=  dateformat.format(date);

        String yfmt=  yearformat.format(date);

        String mfmt=  monthformat.format(date);      

        String SQL =  "select "+_seqName+".nextval from dual";

        dbUtility.createStatement();

        

        String number= dbUtility.executeQry(SQL);

        //seqnumber=_moduleConstant+dfmt+mfmt+yfmt+number;
        seqnumber=_moduleConstant+number;
        
        }catch(Exception e)

        {
        	e.printStackTrace();

            //logger.debug("Exception occur in PIN Generation: "+ e);

        }finally{

            if(dbUtility!=null){

            

            	dbUtility.closeConnection();

            }

            

        }

        return seqnumber;

    }

	
	public void addOfficeDeptMapping(OfficeDeptDTO officeDeptDTO) {
		sql = OfficeDeptMappingCommonSQL.INSERT_OFFICE_DEPT;
		String param[] = new String[3];
		
		try
		{
			param[0] = this.getSequenceNumber("IGRS_OFFICE_DEPT_MAPPING_SEQ", "ODM");
			param[1] = officeDeptDTO.getOfficeId();
			param[2] = officeDeptDTO.getDeptId();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		

		try {
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			boolean boo = dbUtility.executeUpdate(param);
			if (boo) {
				dbUtility.commit();
			} else {
				dbUtility.rollback();
			}
				 
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				dbUtility.closeConnection();
			}catch(Exception x) {
				x.printStackTrace();
			}
		}
	}
	
	
	public boolean isOfficeDeptMapExists(OfficeDeptDTO officeDeptDTO){
		try {
			sql = OfficeDeptMappingCommonSQL.SELECT_OFFICE_DEPT;
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			String sd[] = new String[2];
			sd[0] = officeDeptDTO.getOfficeId();
			sd[1] = officeDeptDTO.getDeptId();

			ArrayList list = dbUtility.executeQuery(sd);
			
			if(!list.isEmpty())
			{
				return true;
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public void deleteOfficeDeptMap(OfficeDeptDTO officeDeptDTO) {

		dto = new OfficeDeptDTO();
		String param[] = new String[2];
		param[0] = officeDeptDTO.getOfficeId();
		param[1] = officeDeptDTO.getDeptId();
		
		
		sql = OfficeDeptMappingCommonSQL.DELETE_OFFICE_DEPT;

		try {
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			boolean boo = dbUtility.executeUpdate(param);
			if (boo) {
				dbUtility.commit();
			}else {
				dbUtility.rollback();
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				dbUtility.closeConnection();
			}catch(Exception x){
				x.getStackTrace();
			}
		}
	}
	
	
	public ArrayList getOfficeDeptList()  {
		try {
			sql = OfficeDeptMappingCommonSQL.SELECT_OFFICE_DEPT_ALL;
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			activityList = new ArrayList();
			ArrayList mainList1 = dbUtility.executeQuery(sql);
			ArrayList subList = null;
			for (int i = 0; i < mainList1.size(); i++) {
				subList = (ArrayList) mainList1.get(i);
				fullDTO = new FullOfficeDeptDTO();
				fullDTO.setOfficeDeptId(subList.get(0).toString());
				fullDTO.setOfficeId(subList.get(1).toString());
				fullDTO.setDeptId(subList.get(2).toString());
				fullDTO.setOfficeName(subList.get(3).toString());
				fullDTO.setDeptName(subList.get(4).toString());
				
				activityList.add(fullDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return activityList;
	}
	
	public void deleteOfficeDeptMaps(String[] officeDeptIds)
	{
		for(int i =0;i<officeDeptIds.length;i++)
		{
				String param[] = new String[1];
				param[0] = officeDeptIds[i];
			
				sql = OfficeDeptMappingCommonSQL.DELETE_OFFICE_DEPT_BY_ID;
	
			try {
				dbUtility = new DBUtility();
				dbUtility.createPreparedStatement(sql);
				boolean boo = dbUtility.executeUpdate(param);
				if (boo) {
					dbUtility.commit();
				}else {
					dbUtility.rollback();
				}	
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					dbUtility.closeConnection();
				}catch(Exception x){
					x.getStackTrace();
				}
			}
		}
	}
	
	public void updateOfficeDeptMap(OfficeDeptDTO officeDeptDTO)
	{

			String param[] = new String[3];
			param[0] = officeDeptDTO.getOfficeId();
			param[1] = officeDeptDTO.getDeptId();
			param[2] = officeDeptDTO.getOfficeDeptId();
			
			
			sql = OfficeDeptMappingCommonSQL.UPDATE_OFFICE_DEPT_MAP;

			try {
				dbUtility = new DBUtility();
				dbUtility.createPreparedStatement(sql);
				boolean boo = dbUtility.executeUpdate(param);
				if (boo) {
					dbUtility.commit();
				}else {
					dbUtility.rollback();
				}	
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					dbUtility.closeConnection();
				}catch(Exception x){
					x.getStackTrace();
				}
			}
		
	}
	
	public OfficeDeptDTO getOfficeDeptByID(String officeDeptId)
	{
			try {
				sql = OfficeDeptMappingCommonSQL.SELECT_OFFICE_DEPT_ID;
				dbUtility = new DBUtility();
				dbUtility.createPreparedStatement(sql);
				String sd[] = new String[1];
				sd[0] = officeDeptId;
				ArrayList list = dbUtility.executeQuery(sd);
				ArrayList list1 = (ArrayList) list.get(0);
				dto = new OfficeDeptDTO();
				
				dto.setOfficeId(list1.get(0).toString());
				dto.setDeptId(list1.get(1).toString());
				dto.setOfficeDeptId(officeDeptId);

			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				try {
					dbUtility.closeConnection();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return dto;
		
	}
	
}
