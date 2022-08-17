package com.wipro.igrs.Seals.dao;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.RegCompMaker.sql.RegCommonMkrSQL;
import com.wipro.igrs.Seals.dto.SealsDTO;
import com.wipro.igrs.Seals.form.SealsForm;
import com.wipro.igrs.Seals.sql.SealsSQL;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.login.dao.LoginDAO;
import com.wipro.igrs.login.dto.MasterListDTO;
import com.wipro.igrs.login.sql.CommonSQL;
import com.wipro.igrs.regCompChecker.sql.RegCompCheckerSQL;

public class SealsDAO {
	
	//DBUtility dbUtility = null;
	private Logger logger = Logger.getLogger(SealsDAO.class);
	
	
	/**
	 * 
	 * @param sealId
	 * @return
	 * @throws Exception
	 */
	public ArrayList getSubSealIds(String sealId,String language) throws Exception{
		ArrayList list=new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String SQL = "";
			if(language.equalsIgnoreCase("en"))
			{
				SQL=SealsSQL.GET_SUB_SEALS;
			}
			else
			{
				SQL=SealsSQL.GET_SUB_SEALS_HI;
			}
			
			dbUtility.createPreparedStatement(SQL);
			list = dbUtility.executeQuery(new String[]{sealId});
		} catch (Exception exception) {
			logger.debug("Exception in getSubSealIds" + exception);
		}
		finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("SealsDAO in dao start" + e.getStackTrace());
			}
		}
		return list;
		
	}
	
	/**
	 * 
	 * @param regId
	 * @return
	 * @throws Exception
	 */
	public ArrayList getSealDetailsForReg(String regId) throws Exception{
		ArrayList list=new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String SQL=SealsSQL.GET_SEAL_DETAILS_FOR_REGISTRATION;
			dbUtility.createPreparedStatement(SQL);
			list = dbUtility.executeQuery(new String[]{regId});
		} catch (Exception exception) {
			logger.debug("Exception in getSealDetailsForReg" + exception);
		}
		finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("SealsDAO in dao start" + e.getStackTrace());
			}
		}
		return list;
		
	}

	/**
	 * 
	 * @param regNumber
	 * @return
	 * @throws Exception
	 */
	/*public ArrayList getPresentationPartyDetails(String regNumber) throws Exception{
		ArrayList list=new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String SQL=SealsSQL.GET_PRESENTATION_PARTY_DETAILS;
			dbUtility.createPreparedStatement(SQL);
			list = dbUtility.executeQuery(new String[]{regNumber});
		} catch (Exception exception) {
			logger.debug("Exception in getPresentationPartyDetails" + exception);
		}
		finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("SealsDAO in dao start" + e.getStackTrace());
			}
		}
		return list;
		
	}
	*/
	// above commented and modified for gov official Rahul
	public ArrayList getPresentationPartyDetails(String regNumber, String newregNumber) throws Exception{
		ArrayList list=new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String SQL=SealsSQL.GET_PRESENTATION_PARTY_DETAILS;
			dbUtility.createPreparedStatement(SQL);
			list = dbUtility.executeQuery(new String[]{regNumber,newregNumber});
		} catch (Exception exception) {
			logger.debug("Exception in getPresentationPartyDetails" + exception);
		}
		finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("SealsDAO in dao start" + e.getStackTrace());
			}
		}
		return list;
		
	}
	
	
	public ArrayList getWitnessDetails(String regNumber) throws Exception{
		ArrayList list=new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String SQL=SealsSQL.GET_WITNESS_DETAILS;
			dbUtility.createPreparedStatement(SQL);
			list = dbUtility.executeQuery(new String[]{regNumber});
		} catch (Exception exception) {
			logger.debug("Exception in getWitnessDetails" + exception);
		}
		finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("SealsDAO in dao start" + e.getStackTrace());
			}
		}
		return list;
		
	}
	
	/**
	 * 
	 * @param regNumber
	 * @return
	 * @throws Exception
	 */
	public ArrayList getConsenterDetails(String regNumber) throws Exception{
		ArrayList list=new ArrayList();
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String SQL=SealsSQL.GET_CONSENTER_DETAILS;
			dbUtility.createPreparedStatement(SQL);
			list = dbUtility.executeQuery(new String[]{regNumber});
		} catch (Exception exception) {
			logger.debug("Exception in getConsenterDetails" + exception);
		}
		finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("SealsDAO in dao start" + e.getStackTrace());
			}
		}
		return list;
		
	}
	/**
	 * 
	 * @param partyTypeId
	 * @return
	 * @throws Exception
	 */
	public String getPartyRoleDetails(String partyTypeId,String lang) throws Exception{
		String partyRole = "";
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String SQL ="";
			if(lang.equalsIgnoreCase("en"))
				SQL=SealsSQL.GET_PARTY_ROLE_FROM_ID;
			else
				SQL=SealsSQL.GET_PARTY_ROLE_FROM_ID_HI;
			dbUtility.createPreparedStatement(SQL);
			String arr[] = {partyTypeId};
			partyRole = dbUtility.executeQry(arr);
		} catch (Exception exception) {
			logger.debug("Exception in getPartyRoleDetails" + exception);
		}
		finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("SealsDAO in dao start" + e.getStackTrace());
			}
		}
		return partyRole;
		
	}
	// Added for POA - Only English - Rahul
	
	public String getPartyRoleDetailsLangDesc(String partyTypeId) throws Exception{
		String partyRole = "";
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String SQL ="";
			
			SQL=SealsSQL.GET_PARTY_ROLE_FROM_ID;
			
			dbUtility.createPreparedStatement(SQL);
			String arr[] = {partyTypeId};
			partyRole = dbUtility.executeQry(arr);
		} catch (Exception exception) {
			logger.debug("Exception in getPartyRoleDetails" + exception);
		}
		finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("SealsDAO in dao start" + e.getStackTrace());
			}
		}
		return partyRole;
		
	}
	
	
	/**
	 * 
	 * @param partyId
	 * @return
	 * @throws Exception
	 */
	public String getPartyRole(String partyId) throws Exception{
		String partyRole = "";
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String SQL=SealsSQL.GET_PARTY_ROLE;
			dbUtility.createPreparedStatement(SQL);
			String arr[] = {partyId};
			partyRole = dbUtility.executeQry(arr);
		} catch (Exception exception) {
			logger.debug("Exception in getPartyRole" + exception);
		}
		finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("SealsDAO in dao start" + e.getStackTrace());
			}
		}
		return partyRole;
		
	}
	
	/**
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public String getSrDetails(String userId) throws Exception{
		String srName = "";
		DBUtility dbUtility = null;
		try {
			dbUtility = new DBUtility();
			String SQL=SealsSQL.GET_SR_DETAILS;
			dbUtility.createPreparedStatement(SQL);
			String arr[] = {userId};
			srName = dbUtility.executeQry(arr);
		} catch (Exception exception) {
			logger.debug("Exception in getSrDetails" + exception);
		}
		finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				logger.error("SealsDAO in dao start" + e.getStackTrace());
			}
		}
		return srName;
		
	}
	
	
public boolean insertPresentationSealDetails(ArrayList partyDetails,String[] presentationDetails, String[] presentParties,SealsForm sForm) throws Exception{
	boolean flag = false;
	DBUtility dbUtility = null;
	try {
		dbUtility = new DBUtility();
		String seq = SealsSQL.GET_SEALS_SEQ;
		dbUtility.createStatement();
		String seqNum = dbUtility.executeQry(seq);
		
		String dateQuery = SealsSQL.GET_PRESENTATION_DATE;
		String timeQuery = SealsSQL.GET_PRESENTATION_TIME;
		String partyChecker = SealsSQL.UPADTE_CHECKER_PARTY_PRESENT;
		int m=0;
		for(int k=0;k<presentParties.length;k++)
		{
			String id=presentParties[k];
			if(true)//(id!=null&&!id.equalsIgnoreCase(""))
			{
				if(m==0)
				{
					dateQuery=dateQuery+"?";
					timeQuery=timeQuery+"?";
					partyChecker=partyChecker+"?";
					m=m+1;
				}
				else
				{
					dateQuery=dateQuery+",?";
					timeQuery=timeQuery+",?";
					partyChecker=partyChecker+",?";
				}
			}
		}
		dateQuery=dateQuery+SealsSQL.GET_PRESENTATION_DATE_REMAING;
		timeQuery=timeQuery+SealsSQL.GET_PRESENTATION_DATE_REMAING;
		partyChecker=partyChecker+" )";
		dbUtility.createPreparedStatement(dateQuery);
		String sysDate = dbUtility.executeQry(presentParties);
		String dayMonth = sysDate.substring(0,5);
		String year  = sysDate.substring(6);
		logger.debug("day--->"+dayMonth);
		logger.debug("year--->"+year);
		dbUtility.createPreparedStatement(partyChecker);
		dbUtility.executeUpdate(presentParties);
		dbUtility.createPreparedStatement(timeQuery);
		String sysTime = dbUtility.executeQry(presentParties);
		logger.debug("time--->"+sysTime);
		String getOffice = SealsSQL.GET_OFFICE_NAME;
		String officeId[] = {presentationDetails[6]};
		dbUtility.createPreparedStatement(getOffice);
		ArrayList list = dbUtility.executeQuery(officeId);
		ArrayList subList = new ArrayList();
		String officeName = "";
		String districtName = "";
		String tehsilName = "";
		
		for(int i = 0 ; i < list.size() ; i++)
		{
			subList = (ArrayList)list.get(i);
			officeName = (String)subList.get(0);
			districtName = (String)subList.get(1);
			tehsilName = (String)subList.get(2);
		}
		
		String sealContent = "PRESENTED AT "+sysTime+" A.M/ P.M. ON THE "+dayMonth+" DAY OF "+year+", " +
		"IN THE OFFICE OF THE "+officeName+" ,TEHSIL - "+tehsilName+" DISTRICT- "+districtName+"";
		logger.debug("sealsContent"+sealContent);
		
		String sealContentHindi = "के द्वारा उप जिला "+tehsilName+" जिला "+districtName +" के उप पंजीयक कार्यालय में तारीख "+sysDate+" को मध्यान्ह पूर्व/मध्यान्ह पश्चात "+sysTime+" बजे प्रस्तुत किया गया।";

		sForm.setSealsContent(sealContentHindi);
		presentationDetails[0] = seqNum;
		presentationDetails[5] = sealContent;
		String SQL = SealsSQL.INSERT_PRSENTATION_SEAL_DATA;
		dbUtility.createPreparedStatement(SQL);
		flag = dbUtility.executeUpdate(presentationDetails);
		if(flag)
		{
			
			for(int j = 0; j < presentParties.length; j ++ )
			{
				for(int i = 0 ; i < partyDetails.size() ; i++)
				{
					SealsDTO sealDTO = (SealsDTO)partyDetails.get(i);
					String partyId = sealDTO.getPartyId();
					logger.debug("party"+partyId);
					logger.debug("Present Paty"+presentParties[j]);
					if(partyId.equals(presentParties[j]))
					{
						logger.debug("Match");
						String arr[] = {seqNum,
								partyId};
						String sql = SealsSQL.INSERT_PARTY_DETAILS_PRESENTATION;
						dbUtility.createPreparedStatement(sql);
						flag = dbUtility.executeUpdate(arr);
					}
				}
			}
		}
		
	} catch (Exception exception) {
		logger.debug("Exception in insertPresentationSealDetails" + exception);
	}
	finally{
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			logger.error("SealsDAO in dao start" + e.getStackTrace());
		}
	}
	return flag;
}

public boolean insertExecutionSeal1Details(ArrayList partyDetails,String[] presentationDetails, String[] presentParties,SealsForm sForm) throws Exception{
	boolean flag = false;
	DBUtility dbUtility = null;
	try {
		dbUtility = new DBUtility();
		String seq = SealsSQL.GET_SEALS_SEQ;
		dbUtility.createStatement();
		String seqNum = dbUtility.executeQry(seq);
		
		//String dateQuery = SealsSQL.GET_SYS_DATE;
		//dbUtility.createStatement();
	//	String sysDate = dbUtility.executeQry(dateQuery);
		String dateQuery = SealsSQL.GET_EXECUTION_DATE;
		//String timeQuery = SealsSQL.GET_PRESENTATION_TIME;
		int m=0;
		for(int k=0;k<presentParties.length;k++)
		{
			String id=presentParties[k];
			if(id!=null&&!id.equalsIgnoreCase(""))
			{
				if(m==0)
				{
					dateQuery=dateQuery+"?";
				//	timeQuery=timeQuery+"?";
					m=m+1;
				}
				else
				{
					dateQuery=dateQuery+",?";
					//timeQuery=timeQuery+",?";
				}
			}
		}
		dateQuery=dateQuery+SealsSQL.GET_PRESENTATION_DATE_REMAING;
		//timeQuery=timeQuery+SealsSQL.GET_PRESENTATION_DATE_REMAING;

		dbUtility.createPreparedStatement(dateQuery);
		String sysDate = dbUtility.executeQry(presentParties);
		String dayMonth = sysDate.substring(0,5);
		String year  = sysDate.substring(6);
		logger.debug("day--->"+dayMonth);
		logger.debug("year--->"+year);
		String timeQuery = SealsSQL.GET_SYS_TIME;
		dbUtility.createStatement();
		String sysTime = dbUtility.executeQry(timeQuery);
		logger.debug("time--->"+sysTime);
		String getOffice = SealsSQL.GET_OFFICE_NAME;
		String officeId[] = {presentationDetails[5]};
		dbUtility.createPreparedStatement(getOffice);
		ArrayList list = dbUtility.executeQuery(officeId);
		ArrayList subList = new ArrayList();
		String officeName = "";
		String districtName = "";
		String tehsilName = "";
		for(int i = 0 ; i < list.size() ; i++)
		{
			subList = (ArrayList)list.get(i);
			officeName = (String)subList.get(0);
			districtName = (String)subList.get(1);
			tehsilName = (String)subList.get(2);
		}
		//TODO
		SealsDTO sDTO = sForm.getSealDTO();
		String transType = "आंशिक";
		if(sDTO.getTransType().equalsIgnoreCase("F"))
			transType = "पूर्ण";
		String deed = sForm.getSealDTO().getDeedName();
		String sealContent = "स्वीकार करते हैं कि कथित "+deed+" विलेख का निष्पादन किया गया था और प्रतिफल " +
				"के "+transType+" रूपए "+sDTO.getConsAmt()+" प्राप्त हो गये हैं तथा रूपए "+sDTO.getAmntSr()+" " +
						"उन्हें मेरी उपस्थिति में चुकाये गये थे और प्रतिफल की बकाया रकम रूपए "+sDTO.getAmntToBePaid()+" बच गयी है, जो पंजीयन के बाद प्राप्त होगी । तारीख  "+sysDate+"";
		logger.debug("sealsContent"+sealContent);
		sForm.setSealsContent(sealContent);
		presentationDetails[0] = seqNum;
		presentationDetails[5] = sealContent;
		String SQL = SealsSQL.INSERT_EXECUTION_SEAL_1;
		dbUtility.createPreparedStatement(SQL);
		flag = dbUtility.executeUpdate(presentationDetails);
		if(flag)
		{
			for(int j = 0; j < presentParties.length; j ++ )
			{
				for(int i = 0 ; i < partyDetails.size() ; i++)
				{
					SealsDTO sealDTO = (SealsDTO)partyDetails.get(i);
					String partyId = sealDTO.getPartyId();
					logger.debug("party"+partyId);
					logger.debug("Present Paty"+presentParties[j]);
					if(partyId.equals(presentParties[j]))
					{
						logger.debug("Match");
						String arr[] = {seqNum,
								partyId};
						String sql = SealsSQL.INSERT_PARTY_DETAILS_PRESENTATION;
						dbUtility.createPreparedStatement(sql);
						flag = dbUtility.executeUpdate(arr);
					}
				}
			}
		}
		
	} catch (Exception exception) {
		logger.debug("Exception in insertPresentationSealDetails" + exception);
	}
	finally{
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			logger.error("SealsDAO in dao start" + e.getStackTrace());
		}
	}
	return flag;
}

public boolean insertExecutionSeal7Details(ArrayList partyDetails,String[] presentationDetails, String[] presentParties, SealsForm sForm) throws Exception{
	boolean flag = false;
	DBUtility dbUtility = null;
	try {
		dbUtility = new DBUtility();
		String seq = SealsSQL.GET_SEALS_SEQ;
		dbUtility.createStatement();
		String seqNum = dbUtility.executeQry(seq);
		
		String dateQuery = SealsSQL.GET_SYS_DATE;
		dbUtility.createStatement();
		String sysDate = dbUtility.executeQry(dateQuery);
		String dayMonth = sysDate.substring(0,5);
		String year  = sysDate.substring(6);
		logger.debug("day--->"+dayMonth);
		logger.debug("year--->"+year);
		String timeQuery = SealsSQL.GET_SYS_TIME;
		dbUtility.createStatement();
		String sysTime = dbUtility.executeQry(timeQuery);
		logger.debug("time--->"+sysTime);
		String getOffice = SealsSQL.GET_OFFICE_NAME;
		String officeId[] = {presentationDetails[5]};
		dbUtility.createPreparedStatement(getOffice);
		ArrayList list = dbUtility.executeQuery(officeId);
		ArrayList subList = new ArrayList();
		String officeName = "";
		String districtName = "";
		String tehsilName = "";
		for(int i = 0 ; i < list.size() ; i++)
		{
			subList = (ArrayList)list.get(i);
			officeName = (String)subList.get(0);
			districtName = (String)subList.get(1);
			tehsilName = (String)subList.get(2);
		}
		//TODO
		/*String sealContent = "PRESENTED AT "+sysTime+" A.M/ P.M. ON THE "+dayMonth+" DAY OF "+year+", " +
		"IN THE OFFICE OF THE "+officeName+" ,TEHSIL - "+tehsilName+" DISTRICT- "+districtName+"";*/
		
		String sealContent  = "इस दस्तावेज का स्वेच्छा से निष्पादन जांच करने के लिए कार्यालय के आदेश क्रमांक "+sForm.getSealDTO().getOrderNumber()+" तारीख "+sForm.getSealDTO().getOrderDate()+" द्वारा अधिनियम" +
				" की धारा 38 के अन्तर्गत नियुक्त किए गए आयुक्त "+sForm.getSealDTO().getName()+" द्वारा दिए गए प्रतिवेदन से मुझे संतोष है कि वह प्राप्त हुए प्रतिफल के लिए (यथास्थिति)" +
				" ऊपर उल्लेख की गई पार्टियों के द्वारा स्वेच्छा से निष्पादित किया गया ।";
		sForm.setSealsContent(sealContent);
		logger.debug("sealsContent"+sealContent);
		presentationDetails[0] = seqNum;
		presentationDetails[5] = sealContent;
		String SQL = SealsSQL.INSERT_EXECUTION_SEAL_7;
		dbUtility.createPreparedStatement(SQL);
		flag = dbUtility.executeUpdate(presentationDetails);
		if(flag)
		{
			for(int j = 0; j < presentParties.length; j ++ )
			{
				for(int i = 0 ; i < partyDetails.size() ; i++)
				{
					SealsDTO sealDTO = (SealsDTO)partyDetails.get(i);
					String partyId = sealDTO.getPartyId();
					logger.debug("party"+partyId);
					logger.debug("Present Paty"+presentParties[j]);
					if(partyId.equals(presentParties[j]))
					{
						logger.debug("Match");
						String arr[] = {seqNum,
								partyId};
						String sql = SealsSQL.INSERT_PARTY_DETAILS_PRESENTATION;
						dbUtility.createPreparedStatement(sql);
						flag = dbUtility.executeUpdate(arr);
					}
				}
			}
		}
		
	} catch (Exception exception) {
		logger.debug("Exception in insertPresentationSealDetails" + exception);
	}
	finally{
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			logger.error("SealsDAO in dao start" + e.getStackTrace());
		}
	}
	return flag;
}


public boolean insertWitnessSealDetails(ArrayList witnessDetailsList,String[] witnessDetails, String[] presentWitness) throws Exception{
	boolean flag = false;
	DBUtility dbUtility = null;
	try {
		dbUtility = new DBUtility();
		String seq = SealsSQL.GET_SEALS_SEQ;
		dbUtility.createStatement();
		String seqNum = dbUtility.executeQry(seq);
		
		String dateQuery = SealsSQL.GET_SYS_DATE;
		dbUtility.createStatement();
		String sysDate = dbUtility.executeQry(dateQuery);
		String dayMonth = sysDate.substring(0,5);
		String year  = sysDate.substring(6);
		logger.debug("day--->"+dayMonth);
		logger.debug("year--->"+year);
		String timeQuery = SealsSQL.GET_SYS_TIME;
		dbUtility.createStatement();
		String sysTime = dbUtility.executeQry(timeQuery);
		logger.debug("time--->"+sysTime);
		String getOffice = SealsSQL.GET_OFFICE_NAME;
		String officeId[] = {witnessDetails[5]};
		dbUtility.createPreparedStatement(getOffice);
		ArrayList list = dbUtility.executeQuery(officeId);
		ArrayList subList = new ArrayList();
		String officeName = "";
		String districtName = "";
		String tehsilName = "";
		for(int i = 0 ; i < list.size() ; i++)
		{
			subList = (ArrayList)list.get(i);
			officeName = (String)subList.get(0);
			districtName = (String)subList.get(1);
			tehsilName = (String)subList.get(2);
		}
		//TODO
		String sealContent = "PRESENTED AT "+sysTime+" A.M/ P.M. ON THE "+dayMonth+" DAY OF "+year+", " +
		"IN THE OFFICE OF THE "+officeName+" ,TEHSIL - "+tehsilName+" DISTRICT- "+districtName+"";
		sealContent = "";
		logger.debug("sealsContent"+sealContent);
		witnessDetails[0] = seqNum;
		witnessDetails[5] = sealContent;
		String SQL = SealsSQL.INSERT_PRSENTATION_SEAL_DATA;
		dbUtility.createPreparedStatement(SQL);
		flag = dbUtility.executeUpdate(witnessDetails);
		if(flag)
		{
			for(int j = 0; j < presentWitness.length; j ++ )
			{
				for(int i = 0 ; i < witnessDetailsList.size() ; i++)
				{
					SealsDTO sealDTO = (SealsDTO)witnessDetailsList.get(i);
					String witId = sealDTO.getWitTxnId();
					logger.debug("wit: "+witId);
					logger.debug("Present Paty"+presentWitness[j]);
					if(witId.equals(presentWitness[j]))
					{
						logger.debug("Match");
						String arr[] = {seqNum,
								witId};
						String sql = SealsSQL.INSERT_WITNESS_DETAILS_PRESENTATION;
						dbUtility.createPreparedStatement(sql);
						flag = dbUtility.executeUpdate(arr);
					}
				}
			}
		}
		
	} catch (Exception exception) {
		logger.debug("Exception in insertWitnessSealDetails" + exception);
	}
	finally{
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			logger.error("SealsDAO in dao start" + e.getStackTrace());
		}
	}
	return flag;
}

/**
 * 
 * @param consenterDetailsList
 * @param consenterDetails
 * @param presentConsenter
 * @return
 * @throws Exception
 */
public boolean insertConsenterSealDetails(ArrayList consenterDetailsList,String[] consenterDetails, String[] presentConsenter) throws Exception{
	boolean flag = false;
	DBUtility dbUtility = null;
	try {
		dbUtility = new DBUtility();
		String seq = SealsSQL.GET_SEALS_SEQ;
		dbUtility.createStatement();
		String seqNum = dbUtility.executeQry(seq);
		
		String dateQuery = SealsSQL.GET_SYS_DATE;
		dbUtility.createStatement();
		String sysDate = dbUtility.executeQry(dateQuery);
		String dayMonth = sysDate.substring(0,5);
		String year  = sysDate.substring(6);
		logger.debug("day--->"+dayMonth);
		logger.debug("year--->"+year);
		String timeQuery = SealsSQL.GET_SYS_TIME;
		dbUtility.createStatement();
		String sysTime = dbUtility.executeQry(timeQuery);
		logger.debug("time--->"+sysTime);
		String getOffice = SealsSQL.GET_OFFICE_NAME;
		String officeId[] = {consenterDetails[5]};
		dbUtility.createPreparedStatement(getOffice);
		ArrayList list = dbUtility.executeQuery(officeId);
		ArrayList subList = new ArrayList();
		String officeName = "";
		String districtName = "";
		String tehsilName = "";
		for(int i = 0 ; i < list.size() ; i++)
		{
			subList = (ArrayList)list.get(i);
			officeName = (String)subList.get(0);
			districtName = (String)subList.get(1);
			tehsilName = (String)subList.get(2);
		}
		//TODO
		String sealContent = "PRESENTED AT "+sysTime+" A.M/ P.M. ON THE "+dayMonth+" DAY OF "+year+", " +
		"IN THE OFFICE OF THE "+officeName+" ,TEHSIL - "+tehsilName+" DISTRICT- "+districtName+"";
		logger.debug("sealsContent"+sealContent);
		consenterDetails[0] = seqNum;
		consenterDetails[5] = sealContent;
		
		String SQL = SealsSQL.INSERT_PRSENTATION_SEAL_DATA;
		dbUtility.createPreparedStatement(SQL);
		flag = dbUtility.executeUpdate(consenterDetails);
		if(flag)
		{
			for(int j = 0; j < presentConsenter.length; j ++ )
			{
				for(int i = 0 ; i < consenterDetailsList.size() ; i++)
				{
					SealsDTO sealDTO = (SealsDTO)consenterDetailsList.get(i);
					String consId = sealDTO.getConsenterTxnId();
					logger.debug("wit: "+consId);
					logger.debug("Present Paty"+presentConsenter[j]);
					if(consId.equals(presentConsenter[j]))
					{
						logger.debug("Match");
						String arr[] = {seqNum,
								consId};
						String sql = SealsSQL.INSERT_CONSENTER_DETAILS_PRESENTATION;
						dbUtility.createPreparedStatement(sql);
						flag = dbUtility.executeUpdate(arr);
					}
				}
			}
		}
		
	} catch (Exception exception) {
		logger.debug("Exception in insertConsenterSealDetails" + exception);
	}
	finally{
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			logger.error("SealsDAO in dao start" + e.getStackTrace());
		}
	}
	return flag;
}

public boolean insertThumbSealDetails(SealsForm sealForm,String[] partyDetails, String[] presentParties) throws Exception{
	boolean flag = false;
	ArrayList partyDetailsList = sealForm.getPresentationPartyDetails();
	ArrayList witnessList = sealForm.getWitnessList();
	ArrayList consenterList = sealForm.getConsenterList();
	DBUtility dbUtility = null;
	try {
		dbUtility = new DBUtility();
		String seq = SealsSQL.GET_SEALS_SEQ;
		dbUtility.createStatement();
		String seqNum = dbUtility.executeQry(seq);
		
		String dateQuery = SealsSQL.GET_SYS_DATE;
		dbUtility.createStatement();
		String sysDate = dbUtility.executeQry(dateQuery);
		String dayMonth = sysDate.substring(0,5);
		String year  = sysDate.substring(6);
		logger.debug("day--->"+dayMonth);
		logger.debug("year--->"+year);
		String timeQuery = SealsSQL.GET_SYS_TIME;
		dbUtility.createStatement();
		String sysTime = dbUtility.executeQry(timeQuery);
		logger.debug("time--->"+sysTime);
		String getOffice = SealsSQL.GET_OFFICE_NAME;
		String officeId[] = {partyDetails[5]};
		dbUtility.createPreparedStatement(getOffice);
		ArrayList list = dbUtility.executeQuery(officeId);
		ArrayList subList = new ArrayList();
		String officeName = "";
		String districtName = "";
		String tehsilName = "";
		for(int i = 0 ; i < list.size() ; i++)
		{
			subList = (ArrayList)list.get(i);
			officeName = (String)subList.get(0);
			districtName = (String)subList.get(1);
			tehsilName = (String)subList.get(2);
		}
		//TODO
		String sealContent = "PRESENTED AT "+sysTime+" A.M/ P.M. ON THE "+dayMonth+" DAY OF "+year+", " +
		"IN THE OFFICE OF THE "+officeName+" ,TEHSIL - "+tehsilName+" DISTRICT- "+districtName+"";
		logger.debug("sealsContent"+sealContent);
		partyDetails[0] = seqNum;
		partyDetails[5] = sealContent;
		sealForm.getSealDTO().setOfficeName(officeName);
		String SQL = SealsSQL.INSERT_PRSENTATION_SEAL_DATA;
		dbUtility.createPreparedStatement(SQL);
		flag = dbUtility.executeUpdate(partyDetails);
		if(flag)
		{
			for(int j = 0; j < presentParties.length; j ++ )
			{
				for(int i = 0 ; i < partyDetailsList.size() ; i++)
				{
					SealsDTO sealDTO = (SealsDTO)partyDetailsList.get(i);
					String partyId = sealDTO.getPartyId();
					logger.debug("Party: "+partyId);
					logger.debug("Present Paty"+presentParties[j]);
					if(partyId.equals(presentParties[j]))
					{
						logger.debug("Match");
						String arr[] = {seqNum,
								partyId};
						String sql = SealsSQL.INSERT_PARTY_DETAILS_PRESENTATION;
						dbUtility.createPreparedStatement(sql);
						flag = dbUtility.executeUpdate(arr);
					}
				}
			}
			
			for(int j = 0; j < presentParties.length; j ++ )
			{
				for(int i = 0 ; i < witnessList.size() ; i++)
				{
					SealsDTO sealDTO = (SealsDTO)witnessList.get(i);
					String witId = sealDTO.getWitTxnId();
					logger.debug("wit: "+witId);
					logger.debug("Present Paty"+presentParties[j]);
					if(witId.equals(presentParties[j]))
					{
						logger.debug("Match");
						String arr[] = {seqNum,
								witId};
						String sql = SealsSQL.INSERT_WITNESS_DETAILS_PRESENTATION;
						dbUtility.createPreparedStatement(sql);
						flag = dbUtility.executeUpdate(arr);
					}
				}
			}
			
			for(int j = 0; j < presentParties.length; j ++ )
			{
				for(int i = 0 ; i < consenterList.size() ; i++)
				{
					SealsDTO sealDTO = (SealsDTO)consenterList.get(i);
					String consId = sealDTO.getConsenterTxnId();
					logger.debug("wit: "+consId);
					logger.debug("Present Paty"+presentParties[j]);
					if(consId.equals(presentParties[j]))
					{
						logger.debug("Match");
						String arr[] = {seqNum,
								consId};
						String sql = SealsSQL.INSERT_CONSENTER_DETAILS_PRESENTATION;
						dbUtility.createPreparedStatement(sql);
						flag = dbUtility.executeUpdate(arr);
					}
				}
			}
		}
		
	} catch (Exception exception) {
		logger.debug("Exception in insertThumbImpSealDetails" + exception);
	}
	finally{
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			logger.error("SealsDAO in dao start" + e.getStackTrace());
		}
	}
	return flag;
}

public ArrayList getDutyDeedDetails(String regNum) throws Exception{
	ArrayList getDutyDeedDetails = new ArrayList();
	DBUtility dbUtility = null;
	try {
		dbUtility = new DBUtility();
		String SQL=SealsSQL.GET_DEED_DUTY_DETAILS;
		dbUtility.createPreparedStatement(SQL);
		String arr[] = {regNum};
		getDutyDeedDetails = dbUtility.executeQuery(arr);
	} catch (Exception exception) {
		logger.debug("Exception in getSrDetails" + exception);
	}
	finally{
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			logger.error("SealsDAO in dao start" + e.getStackTrace());
		}
	}
	return getDutyDeedDetails;
}

public boolean insertAdjudicationSealDetails(ArrayList partyDetails,String[] presentationDetails, String[] presentParties, String regNumber,SealsForm sForm) throws Exception{
	boolean flag = false;
	DBUtility dbUtility = null;
	try {
		dbUtility = new DBUtility();
		String seq = SealsSQL.GET_SEALS_SEQ;
		dbUtility.createStatement();
		String seqNum = dbUtility.executeQry(seq);
		
		String dateQuery = SealsSQL.GET_SYS_DATE;
		dbUtility.createStatement();
		String sysDate = dbUtility.executeQry(dateQuery);
		String dayMonth = sysDate.substring(0,5);
		String year  = sysDate.substring(6);
		logger.debug("day--->"+dayMonth);
		logger.debug("year--->"+year);
		String timeQuery = SealsSQL.GET_SYS_TIME;
		dbUtility.createStatement();
		String sysTime = dbUtility.executeQry(timeQuery);
		logger.debug("time--->"+sysTime);
		String getOffice = SealsSQL.GET_OFFICE_NAME;
		String officeId[] = {presentationDetails[5]};
		dbUtility.createPreparedStatement(getOffice);
		ArrayList list = dbUtility.executeQuery(officeId);
		ArrayList subList = new ArrayList();
		String officeName = "";
		String districtName = "";
		String tehsilName = "";
		for(int i = 0 ; i < list.size() ; i++)
		{
			subList = (ArrayList)list.get(i);
			officeName = (String)subList.get(0);
			districtName = (String)subList.get(1);
			tehsilName = (String)subList.get(2);
		}
		String stampDuty = getStampDuty(regNumber); 
		/*String sealContent = "PRESENTED AT "+sysTime+" A.M/ P.M. ON THE "+dayMonth+" DAY OF "+year+", " +
		"IN THE OFFICE OF THE "+officeName+" ,TEHSIL - "+tehsilName+" DISTRICT- "+districtName+"";*/
		String sealContent = "के द्वारा लिखित रूप में प्रस्तुत किये आवेदन पत्र पर भारतीय स्टाम्प अधिनियम, 1899 की धारा 16 के अधीन मैं प्रमाणित करता हूं कि मैंने स्वयं का इस बात का समाधान कर " +
				"लिया है कि "+stampDuty+" रूपए का स्टाम्प शुल्क उस लिखत पर जिसकी विशिष्टियां नीचे दी गई हैं,";
		logger.debug("sealsContent"+sealContent);
		sForm.setSealsContent(sealContent);
		presentationDetails[0] = seqNum;
		presentationDetails[5] = sealContent;
		String SQL = SealsSQL.INSERT_ADJUDICATION_SEAL_DETAILS;
		dbUtility.createPreparedStatement(SQL);
		flag = dbUtility.executeUpdate(presentationDetails);
		if(flag)
		{
			for(int j = 0; j < presentParties.length; j ++ )
			{
				for(int i = 0 ; i < partyDetails.size() ; i++)
				{
					SealsDTO sealDTO = (SealsDTO)partyDetails.get(i);
					String partyId = sealDTO.getPartyId();
					logger.debug("party"+partyId);
					logger.debug("Present Paty"+presentParties[j]);
					if(partyId.equals(presentParties[j]))
					{
						logger.debug("Match");
						String arr[] = {seqNum,
								partyId};
						String sql = SealsSQL.INSERT_PARTY_DETAILS_PRESENTATION;
						dbUtility.createPreparedStatement(sql);
						flag = dbUtility.executeUpdate(arr);
					}
				}
			}
		}
		
	} catch (Exception exception) {
		logger.debug("Exception in insertAdjudicationSealDetails" + exception);
	}
	finally{
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			logger.error("SealsDAO in dao start" + e.getStackTrace());
		}
	}
	return flag;
}

public boolean insertPOASealDetails(ArrayList witnessDetailsList,String[] witnessDetails, String[] presentWitness,SealsForm sForm) throws Exception{
	boolean flag = false;
	DBUtility dbUtility = null;
	try {
		dbUtility = new DBUtility();
		String seq = SealsSQL.GET_SEALS_SEQ;
		dbUtility.createStatement();
		String seqNum = dbUtility.executeQry(seq);
		
		String dateQuery = SealsSQL.GET_SYS_DATE;
		dbUtility.createStatement();
		String sysDate = dbUtility.executeQry(dateQuery);
		String dayMonth = sysDate.substring(0,5);
		String year  = sysDate.substring(6);
		logger.debug("day--->"+dayMonth);
		logger.debug("year--->"+year);
		String timeQuery = SealsSQL.GET_SYS_TIME;
		dbUtility.createStatement();
		String sysTime = dbUtility.executeQry(timeQuery);
		logger.debug("time--->"+sysTime);
		String getOffice = SealsSQL.GET_OFFICE_NAME;
		String officeId[] = {witnessDetails[5]};
		dbUtility.createPreparedStatement(getOffice);
		ArrayList list = dbUtility.executeQuery(officeId);
		ArrayList subList = new ArrayList();
		String officeName = "";
		String districtName = "";
		String tehsilName = "";
		for(int i = 0 ; i < list.size() ; i++)
		{
			subList = (ArrayList)list.get(i);
			officeName = (String)subList.get(0);
			districtName = (String)subList.get(1);
			tehsilName = (String)subList.get(2);
		}
		//TODO
		/*String sealContent = "PRESENTED AT "+sysTime+" A.M/ P.M. ON THE "+dayMonth+" DAY OF "+year+", " +
		"IN THE OFFICE OF THE "+officeName+" ,TEHSIL - "+tehsilName+" DISTRICT- "+districtName+"";
		logger.debug("sealsContent"+sealContent);*/
		String sealContent = "के साक्ष्य द्वारा जिनकी हुलिया तय की गई थी, आज तारीख "+sysDate+"को मेरी उपस्थिति में निष्पादित किया गया है।";
		sForm.setSealsContent(sealContent);
		witnessDetails[0] = seqNum;
		witnessDetails[5] = sealContent;
		String SQL = SealsSQL.INSERT_POA_DETAILS;
		dbUtility.createPreparedStatement(SQL);
		flag = dbUtility.executeUpdate(witnessDetails);
		if(flag)
		{
			for(int j = 0; j < presentWitness.length; j ++ )
			{
				for(int i = 0 ; i < witnessDetailsList.size() ; i++)
				{
					SealsDTO sealDTO = (SealsDTO)witnessDetailsList.get(i);
					String witId = sealDTO.getWitTxnId();
					logger.debug("wit: "+witId);
					logger.debug("Present Paty"+presentWitness[j]);
					if(witId.equals(presentWitness[j]))
					{
						logger.debug("Match");
						String arr[] = {seqNum,
								witId};
						String sql = SealsSQL.INSERT_WITNESS_DETAILS_PRESENTATION;
						dbUtility.createPreparedStatement(sql);
						flag = dbUtility.executeUpdate(arr);
					}
				}
			}
		}
		
	} catch (Exception exception) {
		logger.debug("Exception in insertPOASealDetails" + exception);
	}
	finally{
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			logger.error("SealsDAO in dao start" + e.getStackTrace());
		}
	}
	return flag;
}

public boolean insertPOASealVisitDetails(String[] poaVisitDetails, SealsForm sForm) throws Exception{
	boolean flag = false;
	DBUtility dbUtility = null;
	try {
		dbUtility = new DBUtility();
		String seq = SealsSQL.GET_SEALS_SEQ;
		dbUtility.createStatement();
		String seqNum = dbUtility.executeQry(seq);
		
		String dateQuery = SealsSQL.GET_SYS_DATE;
		dbUtility.createStatement();
		String sysDate = dbUtility.executeQry(dateQuery);
		String dayMonth = sysDate.substring(0,5);
		String year  = sysDate.substring(6);
		logger.debug("day--->"+dayMonth);
		logger.debug("year--->"+year);
		String timeQuery = SealsSQL.GET_SYS_TIME;
		dbUtility.createStatement();
		String sysTime = dbUtility.executeQry(timeQuery);
		logger.debug("time--->"+sysTime);
		String getOffice = SealsSQL.GET_OFFICE_NAME;
		String officeId[] = {poaVisitDetails[5]};
		dbUtility.createPreparedStatement(getOffice);
		ArrayList list = dbUtility.executeQuery(officeId);
		ArrayList subList = new ArrayList();
		String officeName = "";
		String districtName = "";
		String tehsilName = "";
		for(int i = 0 ; i < list.size() ; i++)
		{
			subList = (ArrayList)list.get(i);
			officeName = (String)subList.get(0);
			districtName = (String)subList.get(1);
			tehsilName = (String)subList.get(2);
		}
		//TODO
		/*String sealContent = "PRESENTED AT "+sysTime+" A.M/ P.M. ON THE "+dayMonth+" DAY OF "+year+", " +
		"IN THE OFFICE OF THE "+officeName+" ,TEHSIg ing L - "+tehsilName+" DISTRICT- "+districtName+"";*/
		String sealContent = "प्रधान "+sForm.getSealDTO().getName()+" , "+sForm.getSealDTO().getAddress()+" से उसके निवास स्थान पर भेंट करने और उसे जांचने के पश्चात् मुझे यह संतोष है कि यह मुख्त्यारनामा उसके" +
				" द्वारा स्वेच्छा से निष्पादित किया गया है तथा तदनुसार पंजीयन अधिनियम, 1908 के अधीन क्रमांक "+sForm.getSealDTO().getRegCompNumber()+" देकर लेखबद्ध करता हूं।";
		sForm.setSealsContent(sealContent);
		logger.debug("sealsContent"+sealContent);
		poaVisitDetails[0] = seqNum;
		poaVisitDetails[5] = sealContent;
		String SQL = SealsSQL.INSERT_POA_VISIT_DETAILS;
		dbUtility.createPreparedStatement(SQL);
		flag = dbUtility.executeUpdate(poaVisitDetails);
		
	} catch (Exception exception) {
		logger.debug("Exception in insertPOASealDetails" + exception);
	}
	finally{
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			logger.error("SealsDAO in dao start" + e.getStackTrace());
		}
	}
	return flag;
}

public boolean insertPOASealCommDetails(String[] poaCommDetails, SealsForm sForm) throws Exception{
	boolean flag = false;
	DBUtility dbUtility = null;
	try {
		dbUtility = new DBUtility();
		String seq = SealsSQL.GET_SEALS_SEQ;
		dbUtility.createStatement();
		String seqNum = dbUtility.executeQry(seq);
		
		String dateQuery = SealsSQL.GET_SYS_DATE;
		dbUtility.createStatement();
		String sysDate = dbUtility.executeQry(dateQuery);
		String dayMonth = sysDate.substring(0,5);
		String year  = sysDate.substring(6);
		logger.debug("day--->"+dayMonth);
		logger.debug("year--->"+year);
		String timeQuery = SealsSQL.GET_SYS_TIME;
		dbUtility.createStatement();
		String sysTime = dbUtility.executeQry(timeQuery);
		logger.debug("time--->"+sysTime);
		String getOffice = SealsSQL.GET_OFFICE_NAME;
		String officeId[] = {poaCommDetails[5]};
		dbUtility.createPreparedStatement(getOffice);
		ArrayList list = dbUtility.executeQuery(officeId);
		ArrayList subList = new ArrayList();
		String officeName = "";
		String districtName = "";
		String tehsilName = "";
		for(int i = 0 ; i < list.size() ; i++)
		{
			subList = (ArrayList)list.get(i);
			officeName = (String)subList.get(0);
			districtName = (String)subList.get(1);
			tehsilName = (String)subList.get(2);
		}
		//TODO
		/*String sealContent = "PRESENTED AT "+sysTime+" A.M/ P.M. ON THE "+dayMonth+" DAY OF "+year+", " +
		"IN THE OFFICE OF THE "+officeName+" ,TEHSIL - "+tehsilName+" DISTRICT- "+districtName+"";*/
		
		String sealContent = "मुख्तयारनामे के स्वेच्छा से निष्पादन की जांच करने के लिए नियुक्त किए गए आयुक्त  "+sForm.getSealDTO().getName()+" , "+sForm.getSealDTO().getAddress()+" द्वारा दिए गए" +
				" प्रतिवेदन से मुझे संतोष है कि उक्त "+sForm.getSealDTO().getPoaName()+" के द्वारा यह " +
				"स्वेच्छा से निष्पादित किया गया है तथा तदनुसार पंजीयन अधिनियम, 1908 की धारा 33 के अन्तर्गत मैं उसे प्रमाणीकृत करता हूं और आज तारीख "+sysDate+" में क्रमांक "+sForm.getSealDTO().getRegCompNumber()+" देकर लेखबद्ध करता हूं।";
		sForm.setSealsContent(sealContent);
		logger.debug("sealsContent"+sealContent);
		poaCommDetails[0] = seqNum;
		poaCommDetails[5] = sealContent;
		String SQL = SealsSQL.INSERT_POA_COMM_DETAILS;
		dbUtility.createPreparedStatement(SQL);
		flag = dbUtility.executeUpdate(poaCommDetails);
		
	} catch (Exception exception) {
		logger.debug("Exception in insertPOASealCommDetails" + exception);
	}
	finally{
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			logger.error("SealsDAO in dao start" + e.getStackTrace());
		}
	}
	return flag;
}

public boolean insertPOASealDetails(String[] poaCommDetails,SealsForm sForm) throws Exception{
	boolean flag = false;
	DBUtility dbUtility = null;
	try {
		dbUtility = new DBUtility();
		String seq = SealsSQL.GET_SEALS_SEQ;
		dbUtility.createStatement();
		String seqNum = dbUtility.executeQry(seq);
		
		String dateQuery = SealsSQL.GET_SYS_DATE;
		dbUtility.createStatement();
		String sysDate = dbUtility.executeQry(dateQuery);
		String dayMonth = sysDate.substring(0,5);
		String year  = sysDate.substring(6);
		logger.debug("day--->"+dayMonth);
		logger.debug("year--->"+year);
		String timeQuery = SealsSQL.GET_SYS_TIME;
		dbUtility.createStatement();
		String sysTime = dbUtility.executeQry(timeQuery);
		logger.debug("time--->"+sysTime);
		String getOffice = SealsSQL.GET_OFFICE_NAME;
		String officeId[] = {poaCommDetails[5]};
		dbUtility.createPreparedStatement(getOffice);
		ArrayList list = dbUtility.executeQuery(officeId);
		ArrayList subList = new ArrayList();
		String officeName = "";
		String districtName = "";
		String tehsilName = "";
		for(int i = 0 ; i < list.size() ; i++)
		{
			subList = (ArrayList)list.get(i);
			officeName = (String)subList.get(0);
			districtName = (String)subList.get(1);
			tehsilName = (String)subList.get(2);
		}
		/*//TODO
		String sealContent = "PRESENTED AT "+sysTime+" A.M/ P.M. ON THE "+dayMonth+" DAY OF "+year+", " +
		"IN THE OFFICE OF THE "+officeName+" ,TEHSIL - "+tehsilName+" DISTRICT- "+districtName+"";
		logger.debug("sealsContent"+sealContent);*/
		String sealContent = "पंजीयन अधिनियम, 1908 की धारा 33 के अंतर्गत मैं इसे प्रमाणीकृत करता हूं और आज तारीख "+sysDate+" को क्रमांक "+sForm.getSealDTO().getRegCompNumber()+" देकर लेखबद्ध करता हूं ।";
		logger.debug("sealsContent"+sealContent);
		sForm.setSealsContent(sealContent);
		poaCommDetails[0] = seqNum;
		poaCommDetails[5] = sealContent;
		String SQL = SealsSQL.INSERT_POA_DETAILS_FINAL;
		dbUtility.createPreparedStatement(SQL);
		flag = dbUtility.executeUpdate(poaCommDetails);
		
	} catch (Exception exception) {
		logger.debug("Exception in insertPOASealDetails" + exception);
	}
	finally{
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			logger.error("SealsDAO in dao start" + e.getStackTrace());
		}
	}
	return flag;
}

public boolean insertReportCommissionerDetails(ArrayList partyDetails,String[] presentationDetails, String[] presentParties, String sealType) throws Exception{
	boolean flag = false;
	DBUtility dbUtility = null;
	try {
		dbUtility = new DBUtility();
		String seq = SealsSQL.GET_SEALS_SEQ;
		dbUtility.createStatement();
		String seqNum = dbUtility.executeQry(seq);
		
		String dateQuery = SealsSQL.GET_SYS_DATE;
		dbUtility.createStatement();
		String sysDate = dbUtility.executeQry(dateQuery);
		String dayMonth = sysDate.substring(0,5);
		String year  = sysDate.substring(6);
		logger.debug("day--->"+dayMonth);
		logger.debug("year--->"+year);
		String timeQuery = SealsSQL.GET_SYS_TIME;
		dbUtility.createStatement();
		String sysTime = dbUtility.executeQry(timeQuery);
		logger.debug("time--->"+sysTime);
		String getOffice = SealsSQL.GET_OFFICE_NAME;
		String officeId[] = {presentationDetails[5]};
		dbUtility.createPreparedStatement(getOffice);
		ArrayList list = dbUtility.executeQuery(officeId);
		ArrayList subList = new ArrayList();
		String officeName = "";
		String districtName = "";
		String tehsilName = "";
		for(int i = 0 ; i < list.size() ; i++)
		{
			subList = (ArrayList)list.get(i);
			officeName = (String)subList.get(0);
			districtName = (String)subList.get(1);
			tehsilName = (String)subList.get(2);
		}
		//TODO
		String sealContent  = "";
		if(sealType.equals("1"))
		{
			sealContent = "PRESENTED AT "+sysTime+" A.M/ P.M. ON THE "+dayMonth+" DAY OF "+year+", " +
			"IN THE OFFICE OF THE "+officeName+" ,TEHSIL - "+tehsilName+" DISTRICT- "+districtName+"";
		}
		else
		{
			sealContent = "PRESENTED AT "+sysTime+" A.M/ P.M. ON THE "+dayMonth+" DAY OF "+year+", " +
			"IN THE OFFICE OF THE "+officeName+" ,TEHSIL - "+tehsilName+" DISTRICT- "+districtName+"";
		}
		
		logger.debug("sealsContent"+sealContent);
		presentationDetails[0] = seqNum;
		presentationDetails[5] = sealContent;
		String SQL = SealsSQL.INSERT_REPORT_COMMISSIONER;
		dbUtility.createPreparedStatement(SQL);
		flag = dbUtility.executeUpdate(presentationDetails);
		if(flag)
		{
			for(int j = 0; j < presentParties.length; j ++ )
			{
				for(int i = 0 ; i < partyDetails.size() ; i++)
				{
					SealsDTO sealDTO = (SealsDTO)partyDetails.get(i);
					String partyId = sealDTO.getPartyId();
					logger.debug("party"+partyId);
					logger.debug("Present Paty"+presentParties[j]);
					if(partyId.equals(presentParties[j]))
					{
						logger.debug("Match");
						String arr[] = {seqNum,
								partyId};
						String sql = SealsSQL.INSERT_PARTY_DETAILS_PRESENTATION;
						dbUtility.createPreparedStatement(sql);
						flag = dbUtility.executeUpdate(arr);
					}
				}
			}
		}
		
	} catch (Exception exception) {
		logger.debug("Exception in insertReportCommissionerDetails" + exception);
	}
	finally{
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			logger.error("SealsDAO in dao start" + e.getStackTrace());
		}
	}
	return flag;
}

/**
 * 
 * @param regNumber
 * @return ArrayList
 * @throws Exception
 */
public ArrayList getStampDutyDetails(String regNumber) throws Exception{
	ArrayList list=new ArrayList();
	DBUtility dbUtility = null;
	try {
		dbUtility = new DBUtility();
		String SQL=SealsSQL.GET_STAMP_DUTY_DETLS;
		dbUtility.createPreparedStatement(SQL);
		list = dbUtility.executeQuery(new String[]{regNumber});
	} catch (Exception exception) {
		logger.debug("Exception in getStampDutyDetails" + exception);
	}
	finally{
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			logger.error("SealsDAO in dao start" + e.getStackTrace());
		}
	}
	return list;
}


public boolean insertExecutionSealWillDetails(ArrayList partyDetails,String[] presentationDetails, ArrayList witnessList, String presentWitness[],String presenterName, SealsForm sForm) throws Exception{
	boolean flag = false;
	DBUtility dbUtility = null;
	try {
		dbUtility = new DBUtility();
		String seq = SealsSQL.GET_SEALS_SEQ;
		dbUtility.createStatement();
		String seqNum = dbUtility.executeQry(seq);
		
		String dateQuery = SealsSQL.GET_SYS_DATE;
		dbUtility.createStatement();
		String sysDate = dbUtility.executeQry(dateQuery);
	    sysDate=getSealDatePartyMax(sForm.getSealDTO().getRegNumber());
		String dayMonth = sysDate.substring(0,5);
		String year  = sysDate.substring(6);
		logger.debug("day--->"+dayMonth);
		logger.debug("year--->"+year);
		String timeQuery = SealsSQL.GET_SYS_TIME;
		dbUtility.createStatement();
		String sysTime = dbUtility.executeQry(timeQuery);
		logger.debug("time--->"+sysTime);
		String getOffice = SealsSQL.GET_OFFICE_NAME;
		String officeId[] = {presentationDetails[5]};
		dbUtility.createPreparedStatement(getOffice);
		ArrayList list = dbUtility.executeQuery(officeId);
		ArrayList subList = new ArrayList();
		String officeName = "";
		String districtName = "";
		String tehsilName = "";
		for(int i = 0 ; i < list.size() ; i++)
		{
			subList = (ArrayList)list.get(i);
			officeName = (String)subList.get(0);
			districtName = (String)subList.get(1);
			tehsilName = (String)subList.get(2);
		}
		//TODO
		/*String sealContent = "PRESENTED AT "+sysTime+" A.M/ P.M. ON THE "+dayMonth+" DAY OF "+year+", " +
		"IN THE OFFICE OF THE "+officeName+" ,TEHSIL - "+tehsilName+" DISTRICT- "+districtName+"";
		logger.debug("sealsContent"+sealContent);*/
		String sealContent = "द्वारा निष्पादित किया गया था तथा यह कि वसीयतकर्ता/दाता मर गया है" +
				" और "+presenterName+" "+presentationDetails[6]+" पंजीयन अधिनियम के अन्तर्गत प्रस्तुत करने का हकदार है, तदनुसार मैं अधिनियम की धारा 41 के अन्तर्गत उसे पंजीयन के लिए ग्रहण करता हूं। तारीख "+sysDate+" ";
		logger.debug("sealsContent"+sealContent);
		sForm.setSealsContent(sealContent);
		presentationDetails[0] = seqNum;
		presentationDetails[5] = sealContent;
		String SQL = SealsSQL.INSERT_EXECUTION_SEAL_WILL;
		dbUtility.createPreparedStatement(SQL);
		flag = dbUtility.executeUpdate(presentationDetails);
		/*if(flag)
		{
			for(int j = 0; j < presentParties.length; j ++ )
			{
				for(int i = 0 ; i < partyDetails.size() ; i++)
				{
					SealsDTO sealDTO = (SealsDTO)partyDetails.get(i);
					String partyId = sealDTO.getPartyId();
					logger.debug("party"+partyId);
					logger.debug("Present Paty"+presentParties[j]);
					if(partyId.equals(presentParties[j]))
					{
						logger.debug("Match");
						String arr[] = {seqNum,
								partyId};
						String sql = SealsSQL.INSERT_PARTY_DETAILS_PRESENTATION;
						dbUtility.createPreparedStatement(sql);
						flag = dbUtility.executeUpdate(arr);
					}
				}
			}
		}*/
		
		if(flag)
		{
			for(int j = 0; j < presentWitness.length; j ++ )
			{
				for(int i = 0 ; i < witnessList.size() ; i++)
				{
					SealsDTO sealDTO = (SealsDTO)witnessList.get(i);
					String partyId = sealDTO.getWitTxnId();
					logger.debug("party"+partyId);
					logger.debug("Present Paty"+presentWitness[j]);
					if(partyId.equals(presentWitness[j]))
					{
						logger.debug("Match");
						String arr[] = {seqNum,
								partyId};
						String sql = SealsSQL.INSERT_PARTY_DETAILS_PRESENTATION;
						dbUtility.createPreparedStatement(sql);
						flag = dbUtility.executeUpdate(arr);
					}
				}
			}
		}
		
	} catch (Exception exception) {
		logger.debug("Exception in insertExecutionSealWillDetails" + exception);
	}
	finally{
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			logger.error("SealsDAO in dao start" + e.getStackTrace());
		}
	}
	return flag;
}

public ArrayList getRegFeeDetails(String regNumber) throws Exception{
	ArrayList list=new ArrayList();
	DBUtility dbUtility = null;
	try {
		dbUtility = new DBUtility();
		String SQL=SealsSQL.GET_REG_SEAL_DETAILS;
		dbUtility.createPreparedStatement(SQL);
		list = dbUtility.executeQuery(new String[]{regNumber});
	} catch (Exception exception) {
		logger.debug("Exception in getRegFeeDetails" + exception);
	}
	finally{
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			logger.error("SealsDAO in dao start" + e.getStackTrace());
		}
	}
	return list;
}

public ArrayList getPartyDetailsForPrsentationSeal(String[] partiesPresent, String lang) throws Exception{
	ArrayList list=new ArrayList();
	DBUtility dbUtility = null;
	try {
		dbUtility = new DBUtility(); 
		String sql = "";
		StringBuilder SQL = new StringBuilder();
		if("en".equalsIgnoreCase(lang))
			SQL= new StringBuilder(SealsSQL.GET_PARTY_DETAILS_FOR_PRESENTATION_SEAL);
		else
			SQL= new StringBuilder(SealsSQL.GET_PARTY_DETAILS_FOR_PRESENTATION_SEAL_HI);
		
		for(int i = 0 ; i < partiesPresent.length; i++)
		{
			if(i != partiesPresent.length-1)
			{
				SQL = SQL.append("?,");
			}
			else
			{
				SQL = SQL.append("?");
			}
				
		}
		SQL = SQL.append(SealsSQL.GET_PARTY_DETAILS_FOR_PRESENTATION_SEAL_SECOND);
		sql = SQL.toString();
		dbUtility.createPreparedStatement(sql);
		
		list = dbUtility.executeQuery(partiesPresent);
	} catch (Exception exception) {
		logger.debug("Exception in getPartyDetailsForPrsentationSeal" + exception);
	}
	finally{
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			logger.error("SealsDAO in dao start" + e.getStackTrace());
		}
	}
	logger.debug("size of partyDetailsList"+list.size());
	return list;
}

public String getCurrTimeStamp() throws Exception {
	// TODO Auto-generated method stub
	String timeCurrTimeStamp ="";
	String sql;
	DBUtility dbUtility = null;
	try
	{dbUtility = new DBUtility(); 
		dbUtility.createStatement();
		sql =CommonSQL.GET_SYS_DATE;
		
		timeCurrTimeStamp=dbUtility.executeQry(sql);
	}
	catch(Exception e)
	{
		e.printStackTrace();
		logger.error("error in getCurrTimeStamp() in LogonDAO");
	}
	finally
	{
		try
		{
			dbUtility.closeConnection();
		}
		catch(Exception e)
		{
			logger.error("error in getCurrTimeStamp() in closing connection in LogonDAO");
		}
	}
	return timeCurrTimeStamp;
}

public ArrayList getPartyDetailsForAdmissionSeal(String[] partiesPresent,String lang) throws Exception{
	ArrayList list=new ArrayList();
	DBUtility dbUtility = null;
	try {
		dbUtility = new DBUtility(); 
		String sql = "";
		StringBuilder SQL= new StringBuilder();
		
		if(lang.equalsIgnoreCase("en"))
			SQL = new StringBuilder(SealsSQL.GET_PARTY_DETAILS_FOR_PRESENTATION_SEAL);
		else
			SQL = new StringBuilder(SealsSQL.GET_PARTY_DETAILS_FOR_PRESENTATION_SEAL_HI);
		
		for(int i = 0 ; i < partiesPresent.length; i++)
		{
			if(i != partiesPresent.length-1)
			{
				SQL = SQL.append("?,");
			}
			else
			{
				SQL = SQL.append("?");
			}
				
		}
		SQL = SQL.append(SealsSQL.GET_PARTY_DETAILS_FOR_PRESENTATION_SEAL_SECOND);
		sql = SQL.toString();
		dbUtility.createPreparedStatement(sql);
		
		list = dbUtility.executeQuery(partiesPresent);
	} catch (Exception exception) {
		logger.debug("Exception in getPartyDetailsForPrsentationSeal" + exception);
	}
	finally{
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			logger.error("SealsDAO in dao start" + e.getStackTrace());
		}
	}
	logger.debug("size of partyDetailsList"+list.size());
	return list;
}

public ArrayList getPartyDetailsForThumbSeal(ArrayList partiesPresent,String lang) {
	ArrayList list=new ArrayList();
	DBUtility dbUtility = null;
	try {
		dbUtility = new DBUtility(); 
		String sql = "";
		StringBuilder SQL= new StringBuilder();
		if(lang.equalsIgnoreCase("en"))
			SQL = new StringBuilder(SealsSQL.GET_PARTY_DETAILS_FOR_PRESENTATION_SEAL);
		else
			SQL = new StringBuilder(SealsSQL.GET_PARTY_DETAILS_FOR_PRESENTATION_SEAL_HI);
		
		for(int i = 0 ; i < partiesPresent.size(); i++)
		{
			if(i != partiesPresent.size()-1)
			{
				SQL = SQL.append("?,");
			}
			else
			{
				SQL = SQL.append("?");
			}
				
		}
		SQL = SQL.append(SealsSQL.GET_PARTY_DETAILS_FOR_PRESENTATION_SEAL_SECOND);
		sql = SQL.toString();
		dbUtility.createPreparedStatement(sql);
		int len = partiesPresent.size();
		String partyArr[] = new String[len];
		for(int j= 0 ; j < len ; j++)
		{
			partyArr[j] = (String)partiesPresent.get(j);
		}
		
		list = dbUtility.executeQuery(partyArr);
	} catch (Exception exception) {
		logger.debug("Exception in getPartyDetailsForPrsentationSeal" + exception);
	}
	finally{
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			logger.error("SealsDAO in dao start" + e.getStackTrace());
		}
	}
	logger.debug("size of partyDetailsList"+list.size());
	return list;
}

public ArrayList getConsenterDetailsForThumbSeal(ArrayList partiesPresent, String lang) {
	ArrayList list=new ArrayList();
	DBUtility dbUtility = null;
	try {
		dbUtility = new DBUtility(); 
		String sql = "";
		StringBuilder SQL= new StringBuilder();
		if(lang.equalsIgnoreCase("en"))
			SQL = new StringBuilder(SealsSQL.GET_CONSENTER_DETL_FOR_THUMB_SEAL);
		else
			SQL = new StringBuilder(SealsSQL.GET_CONSENTER_DETL_FOR_THUMB_SEAL_HI);
		
		
		for(int i = 0 ; i < partiesPresent.size(); i++)
		{
			if(i != partiesPresent.size()-1)
			{
				SQL = SQL.append("?,");
			}
			else
			{
				SQL = SQL.append("?");
			}
				
		}
		SQL = SQL.append(SealsSQL.GET_CONSENTER_DETL_FOR_THUMB_SEAL_SEC);
		sql = SQL.toString();
		dbUtility.createPreparedStatement(sql);
		int len = partiesPresent.size();
		String partyArr[] = new String[len];
		for(int j= 0 ; j < len ; j++)
		{
			partyArr[j] = (String)partiesPresent.get(j);
		}
		
		list = dbUtility.executeQuery(partyArr);
	} catch (Exception exception) {
		logger.debug("Exception in getPartyDetailsForPrsentationSeal" + exception);
	}
	finally{
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			logger.error("SealsDAO in dao start" + e.getStackTrace());
		}
	}
	logger.debug("size of partyDetailsList"+list.size());
	return list;
}

public ArrayList getWitnessDetailsForThumbSeal(ArrayList witnessPresent,String lang) {
	ArrayList list=new ArrayList();
	DBUtility dbUtility = null;
	try {
		dbUtility = new DBUtility(); 
		String sql = "";
		StringBuilder SQL= new StringBuilder();
		
		if(lang.equalsIgnoreCase("en"))
			SQL = new StringBuilder(SealsSQL.GET_WITNESS_DETL_FOR_THUMB_SEAL);
		else
			SQL = new StringBuilder(SealsSQL.GET_WITNESS_DETL_FOR_THUMB_SEAL_HI);
		
		
		for(int i = 0 ; i < witnessPresent.size(); i++)
		{
			if(i != witnessPresent.size()-1)
			{
				SQL = SQL.append("?,");
			}
			else
			{
				SQL = SQL.append("?");
			}
				
		}
		SQL = SQL.append(SealsSQL.GET_WITNESS_DETL_FOR_THUMB_SEAL_SEC);
		sql = SQL.toString();
		dbUtility.createPreparedStatement(sql);
		int len = witnessPresent.size();
		String partyArr[] = new String[len];
		for(int j= 0 ; j < len ; j++)
		{
			partyArr[j] = (String)witnessPresent.get(j);
		}
		
		list = dbUtility.executeQuery(partyArr);
	} catch (Exception exception) {
		logger.debug("Exception in getPartyDetailsForPrsentationSeal" + exception);
	}
	finally{
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			logger.error("SealsDAO in dao start" + e.getStackTrace());
		}
	}
	logger.debug("size of partyDetailsList"+list.size());
	return list;
}

/**
 * 
 * @return
 */
public String getSystemDate()
{
	String sysDate = "";
	DBUtility dbUtility = null;
	try {
		dbUtility = new DBUtility(); 
		String sql = SealsSQL.GET_SYS_DATE;
		dbUtility.createStatement();
		sysDate = dbUtility.executeQry(sql);
	}catch (Exception exception) {
		logger.debug("Exception in getSystemDate" + exception);
	}
	finally{
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			logger.error("SealsDAO in dao start" + e.getStackTrace());
		}
	}
	return sysDate;
}
public String getSealDatePartyMax(String regInitId)
{
	String sysDate = "";
	DBUtility dbUtility = null;
	try {
		dbUtility = new DBUtility(); 
		String sql = SealsSQL.GET_SEAL_DATE_MAX;
		dbUtility.createPreparedStatement(sql);
		sysDate = dbUtility.executeQry(new String[]{regInitId});
	}catch (Exception exception) {
		logger.debug("Exception in getSystemDate" + exception);
	}
	finally{
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			logger.error("SealsDAO in dao start" + e.getStackTrace());
		}
	}
	return sysDate;
}
public String getSealDateWitnessMax(String regInitId)
{
	String sysDate = "";
	DBUtility dbUtility = null;
	try {
		dbUtility = new DBUtility(); 
		String sql = SealsSQL.GET_SEAL_WITNESS_MAX;
		dbUtility.createPreparedStatement(sql);
		sysDate = dbUtility.executeQry(new String[]{regInitId});
	}catch (Exception exception) {
		logger.debug("Exception in getSystemDate" + exception);
	}
	finally{
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			logger.error("SealsDAO in dao start" + e.getStackTrace());
		}
	}
	return sysDate;
}
public String getThumbSealDate(String partyId,String partyFlag)
{
	String sysDate = "";
	DBUtility dbUtility = null;
	try {
		dbUtility = new DBUtility(); 
		String sql = "";
		
		if("P".equalsIgnoreCase(partyFlag))
		{
			sql=SealsSQL.GET_SEAL_PARTY_THUMB_DATE;
		}
		else if("W".equalsIgnoreCase(partyFlag))
		{
			sql=SealsSQL.GET_SEAL_WITNESS_THUMB_DATE;
		}
		else if("C".equalsIgnoreCase(partyFlag))
		{
			sql=SealsSQL.GET_SEAL_CONSENTER_THUMB_DATE;
		}
	
		dbUtility.createPreparedStatement(sql);
		sysDate = dbUtility.executeQry(new String[]{partyId});
	}catch (Exception exception) {
		logger.debug("Exception in getSystemDate" + exception);
	}
	finally{
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			logger.error("SealsDAO in dao start" + e.getStackTrace());
		}
	}
	return sysDate;
}
public ArrayList getWitnessDetailsWitnessSeal(String[] witnessPresent,String lang) {
	ArrayList list=new ArrayList();
	DBUtility dbUtility = null;
	try {
		dbUtility = new DBUtility(); 
		String sql = "";
		StringBuilder SQL = new StringBuilder();
		if(lang.equalsIgnoreCase("en"))
			SQL= new StringBuilder(SealsSQL.GET_WITNESS_DETL_FOR_THUMB_SEAL);
		else
			SQL= new StringBuilder(SealsSQL.GET_WITNESS_DETL_FOR_THUMB_SEAL_HI);
		
		for(int i = 0 ; i < witnessPresent.length; i++)
		{
			if(i != witnessPresent.length-1)
			{
				SQL = SQL.append("?,");
			}
			else
			{
				SQL = SQL.append("?");
			}
				
		}
		SQL = SQL.append(SealsSQL.GET_WITNESS_DETL_FOR_THUMB_SEAL_SEC);
		sql = SQL.toString();
		dbUtility.createPreparedStatement(sql);
		
		
		list = dbUtility.executeQuery(witnessPresent);
	} catch (Exception exception) {
		logger.debug("Exception in getPartyDetailsForPrsentationSeal" + exception);
	}
	finally{
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			logger.error("SealsDAO in dao start" + e.getStackTrace());
		}
	}
	logger.debug("size of partyDetailsList"+list.size());
	return list;
}

public String getLocationDetails(String officeId, String lang)
{
	String offcDetails = "";
	DBUtility dbUtility = null;
	try {
		dbUtility = new DBUtility(); 
		String sql = "";
		if(lang.equalsIgnoreCase("en"))
			sql = SealsSQL.GET_OFFC_NAME;
		else
			sql = SealsSQL.GET_OFFC_NAME_HI;
		dbUtility.createPreparedStatement(sql);
		String offcArr[] = {officeId};
		offcDetails = dbUtility.executeQry(offcArr);
	}catch (Exception exception) {
		logger.debug("Exception in getSystemDate" + exception);
	}
	finally{
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			logger.error("SealsDAO in dao start" + e.getStackTrace());
		}
	}
	return offcDetails;
}

public String getStampDuty(String regNo)
{
	String stampDuty = "";
	DBUtility dbUtility = null;
	try {
		dbUtility = new DBUtility(); 
		String sql = SealsSQL.GET_STAMP_DUTY;
		dbUtility.createPreparedStatement(sql);
		String regArr[] = {regNo};
		stampDuty = dbUtility.executeQry(regArr);
	}catch (Exception exception) {
		logger.debug("Exception in getSystemDate" + exception);
	}
	finally{
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			logger.error("SealsDAO in dao start" + e.getStackTrace());
		}
	}
	return stampDuty;
}
public String getSealTypeFlag(String regNo)
{
	String sealflag = "";
	DBUtility dbUtility = null;
	try {
		dbUtility = new DBUtility(); 
		String sql = SealsSQL.GET_SEALS_FLAG;
		dbUtility.createPreparedStatement(sql);
		String regArr[] = {regNo};
		sealflag = dbUtility.executeQry(regArr);
		if(sealflag==null)
		{
			sealflag="";
		}
	}catch (Exception exception) {
		logger.debug("Exception in getSystemDate" + exception);
	}
	finally{
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			logger.error("SealsDAO in dao start" + e.getStackTrace());
		}
	}
	return sealflag;
}
public boolean updateSerialNumber(String regNo, String sealId,String serialNumber)
{
	boolean flag = false;
	DBUtility dbUtility = null;
	try {
	String arr[] = {serialNumber,regNo,sealId};
	String sql = SealsSQL.UPDATE_COMMON_SEAL_DATA;
	dbUtility = new DBUtility(); 
	dbUtility.createPreparedStatement(sql);
	flag = dbUtility.executeUpdate(arr);
	}
	catch (Exception exception) {
		logger.debug("Exception in getSystemDate" + exception);
	}
	finally{
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			logger.error("SealsDAO in dao start" + e.getStackTrace());
		}
	}
	return flag;
	
	
}

public boolean updateSealApplied(String regNo, String sealId,String status )
{
	boolean flag = false;
	DBUtility dbUtility = null;
	try {
	String arr[] = {status,regNo,sealId};
	String sql = SealsSQL.UPDATE_APPLY_SEAL;
	dbUtility = new DBUtility(); 
	dbUtility.createPreparedStatement(sql);
	flag = dbUtility.executeUpdate(arr);
	}
	catch (Exception exception) {
		logger.debug("Exception in getSystemDate" + exception);
	}
	finally{
		try {
			dbUtility.commit();
			dbUtility.closeConnection();
		} catch (Exception e) {
			logger.error("SealsDAO in dao start" + e.getStackTrace());
		}
	}
	return flag;
	
	
}
public boolean updateCompTime(String regNo )
{
	boolean flag = false;
	DBUtility dbUtility = null;
	try {
	String arr[] = {regNo};
	String sql = SealsSQL.UPDATE_COMP_DATA;
	dbUtility = new DBUtility(); 
	dbUtility.createPreparedStatement(sql);
	flag = dbUtility.executeUpdate(arr);
	}
	catch (Exception exception) {
		logger.debug("Exception in getSystemDate" + exception);
	}
	finally{
		try {
			dbUtility.commit();
			dbUtility.closeConnection();
		} catch (Exception e) {
			logger.error("SealsDAO in dao start" + e.getStackTrace());
		}
	}
	return flag;
	
	
}
public boolean updateSealFlag(String regNo, String sealFlag)
{
	boolean flag = false;
	DBUtility dbUtility = null;
	try {
	String arr[] = {sealFlag,regNo};
	String sql = SealsSQL.UPDATE_APPLY_SEAL_DATA;
	dbUtility = new DBUtility(); 
	dbUtility.createPreparedStatement(sql);
	flag = dbUtility.executeUpdate(arr);
	}
	catch (Exception exception) {
		logger.debug("Exception in getSystemDate" + exception);
	}
	finally{
		try {
			dbUtility.commit();
			dbUtility.closeConnection();
		} catch (Exception e) {
			logger.error("SealsDAO in dao start" + e.getStackTrace());
		}
	}
	return flag;
	
	
}
public boolean updateBookId(String regNo)
{
	boolean flag = false;
	DBUtility dbUtility = null;
	try {
	String arr[] = {regNo};
	String sql = SealsSQL.UPDATE_BOOK_TYPE_DATA;
	dbUtility = new DBUtility(); 
	dbUtility.createPreparedStatement(sql);
	flag = dbUtility.executeUpdate(arr);
	}
	catch (Exception exception) {
		logger.debug("Exception in getSystemDate" + exception);
	}
	finally{
		try {
			dbUtility.commit();
			dbUtility.closeConnection();
		} catch (Exception e) {
			logger.error("SealsDAO in dao start" + e.getStackTrace());
		}
	}
	return flag;
	
	
}
public boolean updateBookIdRule(String regNo,String bookId)
{
	boolean flag = false;
	DBUtility dbUtility = null;
	try {
	String arr[] = {bookId,regNo};
	String sql = SealsSQL.UPDATE_BOOK_TYPE_DATA_RULE;
	dbUtility = new DBUtility(); 
	dbUtility.createPreparedStatement(sql);
	flag = dbUtility.executeUpdate(arr);
	}
	catch (Exception exception) {
		logger.debug("Exception in getSystemDate" + exception);
	}
	finally{
		try {
			dbUtility.commit();
			dbUtility.closeConnection();
		} catch (Exception e) {
			logger.error("SealsDAO in dao start" + e.getStackTrace());
		}
	}
	return flag;
	
	
}
public boolean insertCommonSealDetails(String regNo, String sealId)
{
	boolean flag = false;
	DBUtility dbUtility = null;
	try {
	String arr[] = {regNo,sealId};
	String sql = SealsSQL.INSERT_COMMON_SEAL_DATA;
	dbUtility = new DBUtility(); 
	dbUtility.createPreparedStatement(sql);
	flag = dbUtility.executeUpdate(arr);
	}
	catch (Exception exception) {
		logger.debug("Exception in getSystemDate" + exception);
	}
	finally{
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			logger.error("SealsDAO in dao start" + e.getStackTrace());
		}
	}
	return flag;
	
	
}

public String getDesignationDetails(String userId)
{
	String desg = "";
	DBUtility dbUtility = null;
	try {
	String arr[] = {userId};
	String sql = SealsSQL.GET_DESIGNATION_DETLS;
	dbUtility = new DBUtility(); 
	dbUtility.createPreparedStatement(sql);
	desg = dbUtility.executeQry(arr);
	}
	catch (Exception exception) {
		logger.debug("Exception in getSystemDate" + exception);
	}
	finally{
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			logger.error("SealsDAO in dao start" + e.getStackTrace());
		}
	}
	return desg;
}

public String getDeedType(String regInitId) throws Exception
{
	String deedName = "";
	DBUtility dbUtility = null;
	try{
		
		dbUtility = new DBUtility();
		
		String query=RegCompCheckerSQL.GET_DEED_NAME;
		dbUtility.createPreparedStatement(query);
		deedName = dbUtility.executeQry(new String[]{regInitId});
	} catch (Exception exception) {
		logger.debug("Exception in getEstampForRegistration" + exception);
	}
	finally
	{
		try
		{
			dbUtility.closeConnection();
		}
		catch(Exception e)
		{
			logger.error("error in close connection getEstampForRegistration"+e.getStackTrace());
		}
		
	}
	return deedName;
}

public String getNameOfPresentParty(String regInitId) throws Exception
{
	String presenterName = "";
	ArrayList presenterList = new ArrayList();
	DBUtility dbUtility = null;
	try{
		
		dbUtility = new DBUtility();
		
		String query=RegCompCheckerSQL.GET_PRESENTER_DETLS;
		dbUtility.createPreparedStatement(query);
		String regArr[] = {regInitId};
		presenterList = dbUtility.executeQuery(regArr);
		
		for(int i = 0 ; i < presenterList.size();i++)
		{
			ArrayList subList = (ArrayList)presenterList.get(i);
			String partyName = (String)subList.get(0);
			String orgName = (String)subList.get(1);
			partyName = partyName == null?"":partyName;
			if("".equals(partyName))
				presenterName = orgName;
			else
				presenterName = partyName;
		}
	} catch (Exception exception) {
		logger.debug("Exception in getNameOfPresentParty" + exception);
	}
	finally
	{
		try
		{
			dbUtility.closeConnection();
		}
		catch(Exception e)
		{
			logger.error("error in close connection getNameOfPresentParty"+e.getStackTrace());
		}
		
	}
	return presenterName;
}
public String getSerailNumber(String regInitId,String sealId) throws Exception
{
	String presenterName = "";
	ArrayList presenterList = new ArrayList();
	DBUtility dbUtility = null;
	try{
		
		dbUtility = new DBUtility();
		
		String query=RegCompCheckerSQL.GET_SERIAL_NUMBER;
		dbUtility.createPreparedStatement(query);
		String regArr[] = {regInitId,sealId};
		presenterList = dbUtility.executeQuery(regArr);
		if(presenterList!=null&&presenterList.size()>0)
		{
		
			ArrayList subList = (ArrayList)presenterList.get(0);
			presenterName=(String) subList.get(0);
		
	}
	} catch (Exception exception) {
		logger.debug("Exception in getNameOfPresentParty" + exception);
	}
	finally
	{
		try
		{
			dbUtility.closeConnection();
		}
		catch(Exception e)
		{
			logger.error("error in close connection getNameOfPresentParty"+e.getStackTrace());
		}
		
	}
	return presenterName;
}
public String getSealDAte(String regInitId,String sealId) throws Exception
{
	String presenterName = "";
	ArrayList presenterList = new ArrayList();
	DBUtility dbUtility = null;
	try{
		
		dbUtility = new DBUtility();
		
		String query=RegCompCheckerSQL.GET_SEAL_DATE;
		dbUtility.createPreparedStatement(query);
		String regArr[] = {regInitId,sealId};
		presenterList = dbUtility.executeQuery(regArr);
		if(presenterList!=null&&presenterList.size()>0)
		{
		
			ArrayList subList = (ArrayList)presenterList.get(0);
			presenterName=(String) subList.get(0);
		
	}
	} catch (Exception exception) {
		logger.debug("Exception in getNameOfPresentParty" + exception);
	}
	finally
	{
		try
		{
			dbUtility.closeConnection();
		}
		catch(Exception e)
		{
			logger.error("error in close connection getNameOfPresentParty"+e.getStackTrace());
		}
		
	}
	return presenterName;
}
public String getSealAppliedFlag(String regInitId,String sealId) throws Exception
{
	String presenterName = "";
	ArrayList presenterList = new ArrayList();
	DBUtility dbUtility = null;
	try{
		
		dbUtility = new DBUtility();
		
		String query=RegCompCheckerSQL.GET_SEAL_APPLIED;
		dbUtility.createPreparedStatement(query);
		String regArr[] = {regInitId,sealId};
		presenterList = dbUtility.executeQuery(regArr);
		if(presenterList!=null&&presenterList.size()>0)
		{
		
			ArrayList subList = (ArrayList)presenterList.get(0);
			presenterName=(String) subList.get(0);
		
	}
	} catch (Exception exception) {
		logger.debug("Exception in getNameOfPresentParty" + exception);
	}
	finally
	{
		try
		{
			dbUtility.closeConnection();
		}
		catch(Exception e)
		{
			logger.error("error in close connection getNameOfPresentParty"+e.getStackTrace());
		}
		
	}
	return presenterName;
}
public String getLangauge(String txnId) throws Exception{
	String[] tid = new String[1];
	tid[0]=txnId;
	String dty;
	DBUtility transmgtUtil = new DBUtility();
	try{
	transmgtUtil.createPreparedStatement(RegCompCheckerSQL.GET_LANGUAGE);
	dty=transmgtUtil.executeQry(tid);
	}
	catch(Exception e)
	{
		throw e;
	}
	finally
	{	
	transmgtUtil.closeConnection();
	}
	return dty;
}
public void signatureDetails(SealsForm sForm,String regNo,String language)
{
	DBUtility dbUtility = null;
	try {
		 dbUtility = new DBUtility();
		 dbUtility.createPreparedStatement(RegCompCheckerSQL.GET_USER_COMP);
		String userId=dbUtility.executeQry(new String[]{regNo});
		dbUtility.createPreparedStatement(RegCompCheckerSQL.GET_OFFICE_COMP);
		String officeId=dbUtility.executeQry(new String[]{regNo});
		LoginDAO dao = new LoginDAO();
		ArrayList list = dao.getLoggedInUserDetls(userId);
		for(int i= 0 ; i < list.size();i++)
		{
			ArrayList subList = (ArrayList)list.get(i);
			
			String fName = (String)subList.get(0);
			String mName = (String)subList.get(1);
			String lName = (String)subList.get(2);
			String name = "";
			if(mName != null && !mName.equals(""))
				name = fName+" "+mName+" "+lName;
			else
				name = fName+" "+lName;
			sForm.getSealDTO().setUserName(name);
			String sign = (String)subList.get(3);
			if(sign!= null && !sign.equals(""))
				sForm.getSealDTO().setUserSignature(sign);
			else
				sForm.getSealDTO().setUserSignature(null);
			String desg = dao.getLoggedInDesignation(userId, language);
			if(desg != null && !desg.equals(""))
				sForm.getSealDTO().setUserDesignation(desg);
			else
				sForm.getSealDTO().setDesignation(null);
			String offc = dao.getLoggedInOffice(officeId, language);
			if(offc != null && !offc.equals(""))
				sForm.getSealDTO().setUserOfficeName(offc);
			else
				sForm.getSealDTO().setUserOfficeName(null);
		
			
		}
	} catch (Exception e) {
		
		e.printStackTrace();
	}
	finally
	{
		try {
			dbUtility.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
} 
// Added for Wil Deed - Common Flag - Rahul
public String getCommonFlowFlag(String txnId) throws Exception{
	String[] tid = new String[1];
	tid[0]=txnId;
	String sqlOne="";
	DBUtility dbUtility = null;

		 dbUtility = new DBUtility();
	try{
		dbUtility.createPreparedStatement( RegCommonMkrSQL.GET_COMMON_FLOW_FLAG);
	sqlOne=dbUtility.executeQry(tid);
	}
	catch(Exception e)
	{
		logger.debug("error in check common flow flag " +e);
		throw e;
		
	}
	finally
	{	
		dbUtility.closeConnection();
	}
	return sqlOne;
}

}
