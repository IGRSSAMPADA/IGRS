package com.wipro.igrs.pendingCase.dao;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.caseMonitoring.dao.CaseMonDAO;
import com.wipro.igrs.caseMonitoring.sql.CaseMonHistorySQL;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.newreginit.constant.RegInitConstant;
import com.wipro.igrs.newreginit.dto.CommonDTO;
import com.wipro.igrs.newreginit.sql.RegCommonSQL;
import com.wipro.igrs.pendingCase.form.PendingManualCaseForm;
import com.wipro.igrs.pendingCase.sql.PendingManualCaseSQL;
import com.wipro.igrs.report.sql.CommonSQL;

public class PendingManualCaseDAO {
	
	private Logger logger = (Logger) Logger.getLogger(PendingManualCaseDAO.class);
	PendingManualCaseSQL caseSQL=new PendingManualCaseSQL();
	public PendingManualCaseDAO() {

	}
	
	
	
	public ArrayList getRevenueHeadList(String language)
	{
		DBUtility dbUtil=null;
		ArrayList list=new ArrayList();
		try
		{
			dbUtil	=new DBUtility();
			dbUtil.createStatement();
			if("en".equalsIgnoreCase(language))
			{
			list=dbUtil.executeQuery(caseSQL.GETREVENUEHEADLIST);
			}
			else
			{
				list=dbUtil.executeQuery(caseSQL.GETREVENUEHEADLIST_H);	
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return  list;
	}
	
	public ArrayList getCaseSectionList(String language,String revHeadId)
	{
		DBUtility dbUtil=null;
		String[] param = {revHeadId};
		ArrayList list=new ArrayList();
		try
		{
			dbUtil	=new DBUtility();
			String query = null;
			dbUtil.createStatement();
			if("en".equalsIgnoreCase(language))
			{
				query=caseSQL.GETPENDINGCASESECTONLIST;
			//list=dbUtil.executeQuery(caseSQL.GETPENDINGCASESECTONLIST);
			}
			else
			{
				query=caseSQL.GETPENDINGCASESECTONLIST_H;
				//list=dbUtil.executeQuery(caseSQL.GETPENDINGCASESECTONLIST_H);	
			}
			dbUtil.createPreparedStatement(query);
			list=dbUtil.executeQuery(param);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return  list;
	}
	
	public boolean saveCaseData(String[] parameters,PendingManualCaseForm form)
	throws Exception {
		
	 boolean insertFlag = false;
	 DBUtility dbUtil=null;
	 dbUtil	=new DBUtility();
	 dbUtil.setAutoCommit(false);
	 try {
	
		String query = null;
		dbUtil.createStatement();
		
	    
			 query=caseSQL.Save_Case_Data_Section;
			  dbUtil.createPreparedStatement(query);
			  insertFlag=dbUtil.executeUpdate(parameters);
			  
			  if (insertFlag) {
				  dbUtil.commit();
				  
				 form.setCaseSequence(parameters[0].toString());
				} else {
					dbUtil.rollback();
					throw new Exception();
				}
	 
	} catch (Exception e) {
		logger.error("pending manual cases in dao start" + e.getStackTrace());
	} finally {
		try {

			dbUtil.closeConnection();
		} catch (Exception ex) {
			logger.debug(ex);
			logger.debug(ex.getMessage(),ex);
			// ex.printStackTrace();
		}
	}
    return insertFlag;
     
     
    }
	
	public boolean saveEditCaseData(String[] parameters,PendingManualCaseForm form)
	throws Exception {
		
	 boolean updateFlag = false;
	 DBUtility dbUtil=null;
	 dbUtil	=new DBUtility();
	 dbUtil.setAutoCommit(false);
	 try {
	
		String query = null;
		dbUtil.createStatement();
		
	    if(form.getCaseTypeEdit().equalsIgnoreCase("s")){
	    	query=caseSQL.update_stamp_cases;
	    	
	    }
	    
         if(form.getCaseTypeEdit().equalsIgnoreCase("r")){
        	 query=caseSQL.update_rrc_cases;
	    	
	    }
			 
			  dbUtil.createPreparedStatement(query);
			  updateFlag=dbUtil.executeUpdate(parameters);
			  
			  if (updateFlag) {
				  dbUtil.commit();
				} else {
					dbUtil.rollback();
					throw new Exception();
				}
	 
	} catch (Exception e) {
		logger.error("pending manual cases in dao start" + e.getStackTrace());
	} finally {
		try {

			dbUtil.closeConnection();
		} catch (Exception ex) {
			logger.debug(ex);
			logger.debug(ex.getMessage(),ex);
			// ex.printStackTrace();
		}
	}
    return updateFlag;
     
     
    }

	
	/*
	public String getCaseSequence( String caseType)	throws Exception {
		String caseSequence = "";
		
		 DBUtility dbUtil=null;
		 dbUtil	=new DBUtility();
		 try {
			String query = null;
			dbUtil.createStatement();
			 query=caseSQL.Save_Case_Sequence;
			 dbUtil.createStatement();
			 caseSequence=dbUtil.executeQry(query);
		 } catch (Exception e) {
				logger.error("pending manual cases in dao start" + e.getStackTrace());
			} finally {
				try {

					dbUtil.closeConnection();
				} catch (Exception ex) {
					logger.debug(ex);
					logger.debug(ex.getMessage(),ex);
					// ex.printStackTrace();
				}
			}
		return caseSequence;
	}*/
	
	public String getCaseSequence() throws Exception {
		
		String caseSequence = "0";

		 DBUtility dbUtility=null;
		 dbUtility	=new DBUtility();
		try {
			dbUtility = new DBUtility();
			String SQL1 = caseSQL.GET_TODAY_APP_COUNT;
			logger.debug(SQL1);
			dbUtility.createStatement();
			String number1 = dbUtility.executeQry(SQL1);
			if (number1.equalsIgnoreCase("0")) {
				String drpqry = caseSQL.DROP_PENDING_SEQ_1;
				dbUtility.executeUpdate(drpqry);
				String crtqry = caseSQL.CREATE_PENDING_ID_SEQ_1;
     			dbUtility.executeUpdate(crtqry);
			}
			caseSequence = dbUtility.executeQry(caseSQL.Save_Case_Sequence);
		} catch (Exception exception) {
			logger.debug("pending manual cases in dao start"
					+ exception);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("pending manual cases in dao start" + e.getStackTrace());
			}
		}

		return caseSequence;

	}
	
	
	public String getRevenueHeadName(String revHeadId)	throws Exception {
		String revenueHeadName = "";
		 DBUtility dbUtil=null;
		 dbUtil	=new DBUtility();
		 try {
			String query = null;
			String[] param = {revHeadId};
			dbUtil.createStatement();
			 query=caseSQL.getRevenueHeadName;
			 dbUtil.createPreparedStatement(query);
			 revenueHeadName=dbUtil.executeQry(param);
	     } catch (Exception e) {
	    	logger.error("pending manual cases in dao start" + e.getStackTrace());
     	} finally {
	    	try {

			dbUtil.closeConnection();
		} catch (Exception ex) {
			logger.debug(ex);
			logger.debug(ex.getMessage(),ex);
			// ex.printStackTrace();
		}
	}
		return revenueHeadName;
	}
	
	
	public String getSectionHeadName(String sectionId)	throws Exception {
		String sectionHeadName = "";
		
		 DBUtility dbUtil=null;
		 dbUtil	=new DBUtility();
		 try {
			String query = null;
			String[] param = {sectionId};
			dbUtil.createStatement();
			 query=caseSQL.getSectionHeadName;
			 dbUtil.createPreparedStatement(query);
			 sectionHeadName=dbUtil.executeQry(param);
	} catch (Exception e) {
		logger.error("pending manual cases in dao start" + e.getStackTrace());
	} finally {
		try {

			dbUtil.closeConnection();
		} catch (Exception ex) {
			logger.debug(ex);
			logger.debug(ex.getMessage(),ex);
			// ex.printStackTrace();
		}
	}
		return sectionHeadName;
	}
	
	
	public ArrayList getPendingApps(String userID,PendingManualCaseForm form, String userOffice)
	{
		DBUtility dbUtil=null;
		String[] param = null;
		ArrayList list=new ArrayList();
		try
		{
			dbUtil	=new DBUtility();
			String query = null;
			dbUtil.createStatement();
			
			if(form.getRevHeadId().isEmpty() && form.getSectionId().isEmpty()&& form.getPaymentType().isEmpty() && form.getCaseStatusId().isEmpty()){
				param = new String[4];
				param[0] = form.getCaseType();
				param[1] =form.getCaseDate();
				param[2] =userID;
				param[3] =userOffice;	
				query=caseSQL.get_case_details_1;
				
			}
			
            if(!form.getRevHeadId().isEmpty() && form.getSectionId().isEmpty()&& form.getPaymentType().isEmpty() && form.getCaseStatusId().isEmpty()){
				param = new String[5];
				param[0] = form.getCaseType();
				param[1] =form.getCaseDate();	
				param[2] =form.getRevHeadId();	
				param[3] =userID;
				param[4] =userOffice;	
				query=caseSQL.get_case_details_2;
				
			}
			
			if(!form.getRevHeadId().isEmpty() && !form.getSectionId().isEmpty() && form.getPaymentType().isEmpty() && form.getCaseStatusId().isEmpty() ){
				
				param = new String[6];
				param[0] = form.getCaseType();
				param[1] =form.getCaseDate();	
				param[2] =form.getRevHeadId();
				param[3] =form.getSectionId();
				param[4] =userID;	
				param[5] =userOffice;	
				query=caseSQL.get_case_details_3;
				
			}
			
            if(!form.getRevHeadId().isEmpty() && !form.getSectionId().isEmpty() && !form.getPaymentType().isEmpty() && !form.getCaseStatusId().isEmpty()){
				
            	param = new String[8];
				param[0] = form.getCaseType();
				param[1] =form.getCaseDate();	
				param[2] =form.getRevHeadId();
				param[3] =form.getSectionId();
				param[4] =form.getPaymentType();
				param[5] =form.getCaseStatusId();
				param[6] =userID;
				param[7] =userOffice;	
				
				query=caseSQL.get_case_details_4;
				
			}
            
            
            if(!form.getRevHeadId().isEmpty() && form.getSectionId().isEmpty()  && !form.getPaymentType().isEmpty() && !form.getCaseStatusId().isEmpty()){
				
            	param = new String[7];
				param[0] = form.getCaseType();
				param[1] =form.getCaseDate();	
				param[2] =form.getRevHeadId();
			//	param[3] =form.getSectionId();
				param[3] =form.getPaymentType();
				param[4] =form.getCaseStatusId();
				param[5] =userID;	
				param[6] =userOffice;	
				
				query=caseSQL.get_case_details_5;
			}
            
           if(!form.getRevHeadId().isEmpty()  && !form.getPaymentType().isEmpty() && form.getSectionId().isEmpty() && form.getCaseStatusId().isEmpty()){
				
				
        	  	param = new String[6];
				param[0] = form.getCaseType();
				param[1] =form.getCaseDate();	
				param[2] =form.getRevHeadId();
			//	param[3] =form.getSectionId();
				param[3] =form.getPaymentType();
				param[4] =userID;	
				param[5] =userOffice;	
				
				
				query=caseSQL.get_case_details_6;	
			}
           
              if(!form.getRevHeadId().isEmpty()  && !form.getCaseStatusId().isEmpty() && form.getPaymentType().isEmpty() && form.getSectionId().isEmpty()){
				
            	  param = new String[6];
  				param[0] = form.getCaseType();
  				param[1] =form.getCaseDate();	
  				param[2] =form.getRevHeadId();
  			//	param[3] =form.getSectionId();
  				param[3] =form.getCaseStatusId();
  				param[4] =userID;
  				param[5] =userOffice;	
  				
  				query=caseSQL.get_case_details_7;
			  }
           
           
           if(!form.getPaymentType().isEmpty()  && !form.getCaseStatusId().isEmpty() && form.getRevHeadId().isEmpty() && form.getSectionId().isEmpty()){
				
        	   param = new String[6];
 				param[0] = form.getCaseType();
 				param[1] =form.getCaseDate();	
 				//param[2] =form.getRevHeadId();
 			//	param[3] =form.getSectionId();
 				param[2] =form.getPaymentType();
 				param[3] =form.getCaseStatusId();
 				param[4] =userID;	
 				param[5] =userOffice;	
 				
 				query=caseSQL.get_case_details_8;
				
			}
			
           
           if(!form.getPaymentType().isEmpty()  && form.getCaseStatusId().isEmpty() && form.getRevHeadId().isEmpty() && form.getSectionId().isEmpty()){
				
        	   param = new String[5];
				param[0] = form.getCaseType();
				param[1] =form.getCaseDate();	
				//param[2] =form.getRevHeadId();
			//	param[3] =form.getSectionId();
				param[2] =form.getPaymentType();
				//param[3] =form.getCaseStatusId();
				param[3] =userID;	
				param[4] =userOffice;	
				
				query=caseSQL.get_case_details_9;
				
			}
           
           if(!form.getCaseStatusId().isEmpty()  && form.getPaymentType().isEmpty() && form.getRevHeadId().isEmpty() && form.getSectionId().isEmpty()){
				
        	   param = new String[5];
				param[0] = form.getCaseType();
				param[1] =form.getCaseDate();	
				//param[2] =form.getRevHeadId();
			//	param[3] =form.getSectionId();
			//	param[2] =form.getPaymentType();
				param[2] =form.getCaseStatusId();
				param[3] =userID;	
				param[4] =userOffice;	
				
				query=caseSQL.get_case_details_10;
			}
           
           if(!form.getCaseStatusId().isEmpty()  && form.getPaymentType().isEmpty() && !form.getRevHeadId().isEmpty() && !form.getSectionId().isEmpty()){
				
        	   param = new String[7];
				param[0] = form.getCaseType();
				param[1] =form.getCaseDate();	
				param[2] =form.getRevHeadId();
				param[3] =form.getSectionId();
			//	param[2] =form.getPaymentType();
				param[4] =form.getCaseStatusId();
				param[5] =userID;	
				param[6] =userOffice;	
				
				query=caseSQL.get_case_details_11;
			}
			
           
           if(form.getCaseStatusId().isEmpty()  && !form.getPaymentType().isEmpty() && !form.getRevHeadId().isEmpty() && !form.getSectionId().isEmpty()){
				
        	   param = new String[7];
				param[0] = form.getCaseType();
				param[1] =form.getCaseDate();	
				param[2] =form.getRevHeadId();
				param[3] =form.getSectionId();
				param[4] =form.getPaymentType();
				param[5] =userID;	
				param[6] =userOffice;	
				
				query=caseSQL.get_case_details_12;
			}
		
           
           
			
			dbUtil.createPreparedStatement(query);
			list=dbUtil.executeQuery(param);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return  list;
	}



	public ArrayList getCaseDetlsForDisplay(String caseId) {

		DBUtility dbUtil=null;
		String[] param = {caseId};
		ArrayList list=new ArrayList();
		try
		{
			dbUtil	=new DBUtility();
			String query = null;
			dbUtil.createStatement();
			
				query=caseSQL.caseDetails;
				//list=dbUtil.executeQuery(caseSQL.GETPENDINGCASESECTONLIST_H);	
			
			dbUtil.createPreparedStatement(query);
			list=dbUtil.executeQuery(param);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return  list;
	
	}


	public synchronized ArrayList getDistrict(String stateId, String userOffice, String officeType)
	throws Exception {
String arry[] = new String[1];
logger.debug("State ID:-" + stateId);
logger.debug("Office ID:-" + userOffice);
ArrayList list = new ArrayList();

DBUtility dbUtil = null;

try {
	logger.debug("Before initialize DBUtility");
	dbUtil = new DBUtility();

	//String sql = caseSQL.GET_OFFICE_TYPE_ID;
	//dbUtil.createPreparedStatement(sql);
	String officeTypeID = officeType;
	String distQuery = "";
	// 4 is DIG and 2 is DR
	if (officeTypeID == null)
		officeTypeID = "";
	/*if ("4".equalsIgnoreCase(officeTypeID)) {
		distQuery = caseSQL.GET_DIST_NAME_DIG;
		arry[0] = userOffice;
	} */
	 if ("2".equalsIgnoreCase(officeTypeID)) {
		distQuery = caseSQL.GET_DIST_NAME_DR;
		arry[0] = userOffice;
	}
	 else if ("3".equalsIgnoreCase(officeTypeID)) {// FOR SR
		distQuery = caseSQL.GET_DIST_NAME_SR;
		arry[0] = userOffice;
	} else {
		distQuery = caseSQL.DISTRICT_QUERY_HINDI;
		arry[0] = stateId;
	}
	dbUtil.createPreparedStatement(distQuery);

	// }
	list = dbUtil.executeQuery(arry);
	logger.debug("After initialize DBUtility");
	if (logger.isDebugEnabled()) {
		logger.debug("getDistrict(String) - end");
	}
} catch (Exception x) {
	logger.debug(x);
} finally {
	if (dbUtil != null) {
		dbUtil.closeConnection();
	}
}
return list;
}



		public String getDistrictName(String distID,String language) throws Exception {

		String[] regDtl = { distID };
		String DistID = "";
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			if ("en".equalsIgnoreCase(language))
			dbUtility.createPreparedStatement(caseSQL.GET_DIST_NAME);
			else
				dbUtility.createPreparedStatement(caseSQL.GET_DIST_NAME_HI);
			
			DistID = dbUtility.executeQry(regDtl);
		} catch (Exception x) {
			logger.debug(x);
		} finally {
			if (dbUtility != null) {
				dbUtility.closeConnection();
			}
		}

		return DistID;
	}




		public String getOfficeType(String officeId) {

			String getOfficeType = null;
			String SQL = null;
			DBUtility dbutility = null;
			try {
				String param[] = new String[1];
				param[0] = officeId;

				dbutility = new DBUtility();
				dbutility.createPreparedStatement(caseSQL.OFFICE_TYPE);

				getOfficeType = dbutility.executeQry(param);

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					dbutility.closeConnection();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			return getOfficeType;
		}

		

		public String getDistrictId(String officeId) {
			// TODO Auto-generated method stub

			String getDistId = null;
			String SQL = null;
			DBUtility dbutility = null;
			try {
				String param[] = new String[1];
				param[0] = officeId;

				dbutility = new DBUtility();
				dbutility.createPreparedStatement(caseSQL.DIST_ID);

				getDistId = dbutility.executeQry(param);

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					dbutility.closeConnection();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			return getDistId;
		}

		public String getType(String officeType) {
			// TODO Auto-generated method stub

			String officer = null;
			String SQL = null;
			DBUtility dbutility = null;
			try {
				String param[] = new String[1];
				param[0] = officeType;

				dbutility = new DBUtility();
				dbutility.createPreparedStatement(caseSQL.OFFICER);

				officer = dbutility.executeQry(param);

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					dbutility.closeConnection();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			return officer;
		}



		public String getDIGZoneDAO(String officeId) {
			String a = null;
			DBUtility dbUtil = null;
			try {
				String param[] = new String[1];
				param[0] = officeId;
				dbUtil = new DBUtility();
				String SQL = caseSQL.INSP_GET_ZONE_DIG;
				// logger.debug("before getting Zone details ");
				dbUtil.createPreparedStatement(SQL);
				// logger.debug("SQL:"+SQL);
				ArrayList data = dbUtil.executeQuery(param);
				ArrayList data1 = (ArrayList) data.get(0);
				a = (String) data1.get(0);
				// logger.debug("value of  Zone_Id) :- " + a);
			} catch (Exception e) {
				e.printStackTrace();
				// logger.debug("Exception in getZoneDetails() :- " + e);
			} finally {
				try {
					dbUtil.closeConnection();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			return a;

		}



		public ArrayList getDistDIGListDAO(String zoneId,String language) {

		ArrayList list = new ArrayList();
		String param[] = new String[1];
		param[0] = zoneId;
		String SQL = null;
		if (language.equalsIgnoreCase("en"))
		SQL = caseSQL.INSP_GET_DISTRICT_ZONE_LIST;
		else 
			SQL=caseSQL.INSP_GET_DISTRICT_ZONE_LIST_HINDI;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(SQL);
			list = dbUtil.executeQuery(param);
			logger
					.info("Wipro in manualCases - getDIGList() AFTER EXCUTIN QUERY list values"
							+ list);
		} catch (Exception e) {
			logger
					.error("exception in calling at manualCases Class at getDIGList()  "
							+ e);
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					dbUtil.closeConnection();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return list;

	}



		public ArrayList getDistListDAO(String officeId) {

			ArrayList list = new ArrayList();
			String param[] = new String[1];
			param[0] = officeId;
			String SQL = caseSQL.INSP_GET_DISTRICT_ZONES;
			DBUtility dbUtil = null;
			try {
				dbUtil = new DBUtility();
				dbUtil.createPreparedStatement(SQL);
				list = dbUtil.executeQuery(param);
				// logger.info("Wipro in SpotInspDAO - getDROList() AFTER EXCUTIN QUERY list values"
				// +list);
			} catch (Exception e) {
				// logger.error("exception in calling at SpotInspDAO Class at getDROList()  "
				// +e);
				e.printStackTrace();
			} finally {
				try {
					dbUtil.closeConnection();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			return list;

		}

		public ArrayList getDrOfficeName(String language, String districtId) {

			ArrayList list = new ArrayList();
			String param[] = new String[3];
			param[0] = "2";
			param[1] = "A";
			param[2] = districtId;
			String SQL = caseSQL.getDRofficeName;
			DBUtility dbUtil = null;
			try {
				dbUtil = new DBUtility();
				dbUtil.createPreparedStatement(SQL);
				list = dbUtil.executeQuery(param);
				
			} catch (Exception e) {
				
				e.printStackTrace();
			} finally {
				try {
					dbUtil.closeConnection();
				} catch (Exception e) {
					
					e.printStackTrace();
				}
			}

			return list;

		}


		public boolean getSearchReportResult(String selectedDistrict,
				String selectedRevId, String selectedSectionId,
				String selectedCaseType, String durationFrom,String durationTo,
				String selectedCaseStatus, String selectedPaymentType, String selectedDRofficeId) {
			
			ArrayList list = new ArrayList();
			String param[] = null;
			DBUtility dbUtil = null;
			String SQL ="";
			boolean flag = false;
			
		
			try {
				dbUtil = new DBUtility();
			if (selectedCaseStatus.equals("")&& selectedPaymentType.equals("")){
				 param = new String[7];
				 param[0] =  selectedDistrict;
				 param[1] =  selectedRevId;
				 param[2] =  selectedSectionId;
				 param[3] =  durationFrom;
				 param[4] =  durationTo;
				 param[5] =  selectedCaseType;
				 param[6] = selectedDRofficeId;
				 SQL = caseSQL.getCaseTxnForReport1;
				
			}
			
             if (selectedCaseStatus.equals("")&& !selectedPaymentType.equals("")){
            	 param = new String[8];
            	 param[0] =  selectedDistrict;
 				 param[1] =  selectedRevId;
 				 param[2] =  selectedSectionId;
 				 param[3] =  durationFrom;
				 param[4] =  durationTo;
 				 param[5] =  selectedPaymentType;
 				 param[6] =  selectedCaseType;
 				param[7] = selectedDRofficeId;
 				 SQL = caseSQL.getCaseTxnForReport3;
				
			}
             
             if (!selectedCaseStatus.equals("")&& selectedPaymentType.equals("")){
            	 param = new String[8];
            	 param[0] =  selectedDistrict;
  				 param[1] =  selectedRevId;
  				 param[2] =  selectedSectionId;
  				 param[3] =  durationFrom;
				 param[4] =  durationTo;
  				 param[5] =  selectedCaseStatus;
  				 param[6] =  selectedCaseType;
  				param[7] = selectedDRofficeId;
  				 SQL = caseSQL.getCaseTxnForReport2;
 				
 			  }
             if (!selectedCaseStatus.equals("")&& !selectedPaymentType.equals("")){
            	 param = new String[9];
            	 param[0] =  selectedDistrict;
   				 param[1] =  selectedRevId;
   				 param[2] =  selectedSectionId;
   				 param[3] =  durationFrom;
				 param[4] =  durationTo;
   				 param[5] =  selectedCaseStatus;
   				 param[6] =  selectedPaymentType;
   				 param[7] =  selectedCaseType;
   				param[8] = selectedDRofficeId;
   				 SQL = caseSQL.getCaseTxnForReport4;
  				
			  }
             
             dbUtil.createPreparedStatement(SQL);
				list = dbUtil.executeQuery(param);
				
				
				if(list != null && list.size() >0){
					
					flag = true;
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			return flag;
			}
		
}
