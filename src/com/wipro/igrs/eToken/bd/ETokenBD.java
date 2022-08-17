package com.wipro.igrs.eToken.bd;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.log4j.Logger;

import com.wipro.igrs.eToken.dao.ETokenDAO;
import com.wipro.igrs.eToken.dto.ETokenDTO;
import com.wipro.igrs.eToken.form.ETokenForm;

public class ETokenBD {

	ETokenDAO eDAO = new ETokenDAO();

	private final Logger logger = Logger.getLogger(ETokenBD.class);

	/**
	 * 
	 * @param language
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getDistrictList(String language) throws Exception{

		ArrayList list = eDAO.getDistrictList(language);
		ArrayList subList = new ArrayList();
		ArrayList mainList = new ArrayList();

		for(int i = 0 ; i < list.size(); i++)
		{
			subList = (ArrayList)list.get(i);
			ETokenDTO eDTO = new ETokenDTO();
			eDTO.setDistrictId((String)subList.get(0));
			eDTO.setDistrictName((String)subList.get(1));

			mainList.add(eDTO);
		}

		return mainList;

	}

	public String getLock(String officeId) throws Exception{

		ArrayList list = eDAO.getLock(officeId);
		ArrayList subList = new ArrayList();
		ArrayList mainList = new ArrayList();
		String districtId="";

		for(int i = 0 ; i < list.size(); i++)
		{
			subList = (ArrayList)list.get(i);


			districtId  = 	(String)subList.get(2);


		}

		return districtId;

	}

	/**
	 * 
	 * @param disttId
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getSROList(String disttId,String language) throws Exception
	{
		ArrayList list = eDAO.getSROList(disttId,language);
		ArrayList subList = new ArrayList();
		ArrayList mainList = new ArrayList();

		for(int i = 0 ; i < list.size(); i++)
		{
			subList = (ArrayList)list.get(i);
			ETokenDTO eDTO = new ETokenDTO();
			eDTO.setSroId((String)subList.get(0));
			eDTO.setSroName((String)subList.get(1));

			mainList.add(eDTO);
		}

		return mainList;
	}

	/**
	 * 
	 * @param eDTO
	 * @param userID
	 * @param tDate
	 * @return boolean
	 * @throws Exception
	 */
	public String insertCounterDetls(ETokenDTO eDTO, String userID, String tDate) throws Exception
	{
		String value = "NI";  // data not inserted

		String counterArr[] = {
				eDTO.getDistrictId(),
				eDTO.getSroId(),
				eDTO.getCounterUserId(),
				eDTO.getUserType(),
				eDTO.getCounterOfficeMappingId(),
				userID,
				tDate
		};
		boolean flag = eDAO.insertCounterDetls(counterArr, eDTO.getUserType());
		if(flag)
		{
			value = "I";  //data inserted
		}



		return value;
	}

	/**
	 * 
	 * @param counterName
	 * @return String
	 * @throws Exception
	 */
	private String checkUniqueName(String counterName) throws Exception{
		return eDAO.checkUniqueName(counterName);
	}

	/**
	 * 
	 * @param eDTO
	 * @param language
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getCounterDetails(ETokenDTO eDTO, String language) throws Exception{
		ArrayList subList = new ArrayList();
		ArrayList mainList = new ArrayList();

		// changed by akansha
		String locArr[] = {eDTO.getDistrictId(),
				eDTO.getSroId(),

		};
		ArrayList list = eDAO.getCounterDetails(locArr, eDTO.getUserType(),language);

		for(int i = 0; i < list.size(); i++)
		{
			subList = (ArrayList)list.get(i);

			ETokenDTO eDTO1 = new ETokenDTO();
			eDTO1.setCounterId((String)subList.get(0));
			eDTO1.setCounterName((String)subList.get(1));
			eDTO1.setCounterType((String) subList.get(2));
			eDTO1.setCounterUserId((String) subList.get(3));
			String status = (String) subList.get(4);
			if(status.equalsIgnoreCase("A") )
			{
				if(language.equalsIgnoreCase("en"))
					eDTO1.setStatus("Active");
				else
					eDTO1.setStatus("सक्रिय");
			}
			else
			{
				if(language.equalsIgnoreCase("en"))
					eDTO1.setStatus("Deactivated");
				else
					eDTO1.setStatus("निष्क्रिय");
			}
			mainList.add(eDTO1);
		}

		return mainList;
	}

	/**
	 * 
	 * @param counterId
	 * @param eDTO
	 * @return ETokenDTO
	 * @throws Exception
	 */
	public ETokenDTO getCounterDetailsComplete(String counterId,ETokenDTO eDTO,String language ) throws Exception{
		ArrayList subList = new ArrayList();
		ArrayList mainList = new ArrayList();
		ArrayList userIdMapList=new ArrayList();

		ArrayList list = eDAO.getCounterDetailsComplete(counterId, eDTO.getUserType(),language, eDTO);

		for(int i = 0; i < list.size(); i++)
		{
			subList = (ArrayList)list.get(i);
			eDTO.setCounterName((String)subList.get(0));
			eDTO.setCounterUserId((String)subList.get(1));
			eDTO.setCounterType((String)subList.get(2));
			eDTO.setCounterOfficeMappingId((String)subList.get(3));
			//	userIdMapList.add()
			//eDTO.setc

		}
		return eDTO;
	}

	/**
	 * 
	 * @param eDTO
	 * @param userID
	 * @param tDate
	 * @return String
	 * @throws Exception
	 */
	public String updateCounterDetls(ETokenDTO eDTO, String userID, String tDate) throws Exception
	{
		String value = "NI";  // data not inserted
		if(checkUniqueNameEdit(eDTO.getCounterName(),eDTO.getCounterId()).equalsIgnoreCase("N"))
		{
			/*String counterArr[] = {
					eDTO.getDistrictId(),
					eDTO.getSroId(),
					eDTO.getCounterName(),
				//eDTO.getCounterDesc(),
					eDTO.getStatusChk(),
					userID,
					tDate,
					eDTO.getCounterId()
			};*/

			String counterArr[] = {
					eDTO.getStatusChk(),
					userID,
					eDTO.getCounterId(),
					//eDTO.getCounterDesc(),
					eDTO.getDistrictId(),
					eDTO.getSroId()

			};
			boolean flag = eDAO.updateCounterDetls(counterArr);
			if(flag)
			{
				value = "I";  //data inserted
			}
		}
		else
		{
			value = "U";  //unique constraint violated
		}

		return value;
	}

	/**
	 * 
	 * @param counterName
	 * @param counterId
	 * @return String
	 * @throws Exception
	 */
	public String checkUniqueNameEdit(String counterName, String counterId) throws Exception{
		return eDAO.checkUniqueNameEdit(counterName,counterId);
	}

	/**
	 * 
	 * @param edto
	 * @param officeId
	 * @param language
	 * @return
	 * @throws Exception
	 */
	public ETokenDTO getLoggedInDistrictDetls(ETokenDTO edto, String officeId, String language) throws Exception{
		ArrayList subList = new ArrayList();
		ArrayList mainList = new ArrayList();

		ArrayList list = eDAO.getLoggedInDistrictDetls(officeId,language);

		for(int i = 0; i < list.size(); i++)
		{
			subList = (ArrayList)list.get(i);
			edto.setSroName((String)subList.get(0));
			edto.setDistrictName((String)subList.get(1));
			edto.setSroId(officeId);
		}
		return edto;
	}

	/**
	 * 
	 * @param edto
	 * @param officeId
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getCounterMakerDetls(ETokenForm eForm, String officeId,String language)throws Exception{
		ArrayList subList = new ArrayList();
		ArrayList mainList = new ArrayList();
		ETokenDTO edto = eForm.getEtokenDTO();
		getLoggedInDistrictDetls(edto,officeId,language);
		ArrayList list = eDAO.getMakerCounterDetls(officeId);

		for(int i= 0; i <list.size(); i++)
		{
			subList = (ArrayList)list.get(i);
			ETokenDTO eDTO = new ETokenDTO();
			eDTO.setCounterId((String)subList.get(0));
			eDTO.setCounterName((String)subList.get(1));
			mainList.add(eDTO);
		}
		logger.debug("***** distt****"+edto.getDistrictName());
		logger.debug("***** office****"+edto.getSroName());
		eForm.setEtokenDTO(edto);
		return mainList;
	}

	/**
	 * 
	 * @param officeId
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getMappedMakerDetls(String officeId) throws Exception{
		ArrayList subList = new ArrayList();
		ArrayList mainList = new ArrayList();

		ArrayList list = eDAO.getMappedMakerDetls(officeId);

		for(int i= 0; i <list.size(); i++)
		{
			subList = (ArrayList)list.get(i);
			ETokenDTO eDTO = new ETokenDTO();
			eDTO.setMappingId((String)subList.get(0));
			eDTO.setMakerId((String)subList.get(1));
			eDTO.setMakerName((String)subList.get(2));
			eDTO.setCounterName((String)subList.get(3));
			eDTO.setDelete("Delete");
			mainList.add(eDTO);
		}
		return mainList;
	}

	/**
	 * 
	 * @param counterArr
	 * @return boolean
	 * @throws Exception
	 */
	public boolean insertMakerCounterDetls(ETokenDTO eDTO, String userId, String tDate) throws Exception
	{
		String makerArr[] = {
				eDTO.getMakerId(),
				eDTO.getCounterId(),
				eDTO.getSroId(),
				userId,
				tDate
		};

		return eDAO.insertMakerCounterDetls(makerArr);
	}

	/**
	 * 
	 * @param mappingId
	 * @param userId
	 * @param tDate
	 * @return boolean
	 * @throws Exception
	 */
	public boolean updateMakerCounterDetls(String mappingId,String userId, String tDate) throws Exception
	{
		return eDAO.updateMakerCounterDetls(mappingId, userId, tDate);
	}

	/**
	 * 
	 * @param officeId
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getMakerSRODetls(String officeId) throws Exception{
		ArrayList subList = new ArrayList();
		ArrayList mainList = new ArrayList();

		ArrayList list = eDAO.getMakerSRODetls(officeId);

		for(int i= 0; i <list.size(); i++)
		{
			subList = (ArrayList)list.get(i);
			ETokenDTO eDTO = new ETokenDTO();
			eDTO.setMakerId((String)subList.get(0));
			eDTO.setMakerName((String)subList.get(1));
			mainList.add(eDTO);
		}
		return mainList;
	}

	/**
	 * 
	 * @param edto
	 * @param officeId
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getCounterCheckerDetls(ETokenForm eForm, String officeId,String language)throws Exception{
		ArrayList subList = new ArrayList();
		ArrayList mainList = new ArrayList();
		ETokenDTO edto = eForm.getEtokenDTO();
		getLoggedInDistrictDetls(edto,officeId,language);
		ArrayList list = eDAO.getCheckerCounterDetls(officeId);

		for(int i= 0; i <list.size(); i++)
		{
			subList = (ArrayList)list.get(i);
			ETokenDTO eDTO = new ETokenDTO();
			eDTO.setCounterId((String)subList.get(0));
			eDTO.setCounterName((String)subList.get(1));
			mainList.add(eDTO);
		}
		logger.debug("***** distt****"+edto.getDistrictName());
		logger.debug("***** office****"+edto.getSroName());
		eForm.setEtokenDTO(edto);
		return mainList;
	}

	/**
	 * 
	 * @param officeId
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getMappedCheckerDetls(String officeId) throws Exception{
		ArrayList subList = new ArrayList();
		ArrayList mainList = new ArrayList();

		ArrayList list = eDAO.getMappedCheckerDetls(officeId);

		for(int i= 0; i <list.size(); i++)
		{
			subList = (ArrayList)list.get(i);
			ETokenDTO eDTO = new ETokenDTO();
			eDTO.setMappingId((String)subList.get(0));
			eDTO.setCheckerId((String)subList.get(1));
			eDTO.setCheckerName((String)subList.get(2));
			eDTO.setCounterName((String)subList.get(3));
			eDTO.setDelete("Delete");
			mainList.add(eDTO);
		}
		return mainList;
	}

	/**
	 * 
	 * @param counterArr
	 * @return boolean
	 * @throws Exception
	 */
	public boolean insertCheckerCounterDetls(ETokenDTO eDTO, String userId, String tDate) throws Exception
	{
		String makerArr[] = {
				eDTO.getCheckerId(),
				eDTO.getCounterId(),
				eDTO.getSroId(),
				userId,
				tDate
		};

		return eDAO.insertCheckerCounterDetls(makerArr);
	}

	/**
	 * 
	 * @param mappingId
	 * @param userId
	 * @param tDate
	 * @return boolean
	 * @throws Exception
	 */
	public boolean updateCheckerCounterDetls(String mappingId,String userId, String tDate) throws Exception
	{
		return eDAO.updateCheckerCounterDetls(mappingId, userId, tDate);
	}

	/**
	 * 
	 * @param officeId
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getCheckerSRODetls(String officeId) throws Exception{
		ArrayList subList = new ArrayList();
		ArrayList mainList = new ArrayList();

		ArrayList list = eDAO.getCheckerSRODetls(officeId);

		for(int i= 0; i <list.size(); i++)
		{
			subList = (ArrayList)list.get(i);
			ETokenDTO eDTO = new ETokenDTO();
			eDTO.setCheckerId((String)subList.get(0));
			eDTO.setCheckerName((String)subList.get(1));
			mainList.add(eDTO);
		}
		return mainList;
	}

	/**
	 * 
	 * @param eForm
	 * @param officeId
	 * @param language
	 * @return
	 * @throws Exception
	 */
	public ETokenForm getLoggedinDetls(ETokenForm eForm, String officeId, String language) throws Exception
	{
		ETokenDTO edto = eForm.getEtokenDTO();
		getLoggedInDistrictDetls(edto,officeId,language);
		eForm.setEtokenDTO(edto);
		return eForm;
	}

	/**
	 * 
	 * @param officeId
	 * @param userType
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getAvailableUsersList(String officeId,String userType) throws Exception
	{
		ArrayList subList = new ArrayList();
		ArrayList mainList = new ArrayList();

		ArrayList list = eDAO.getAvailableUsersList(officeId, userType);

		for(int i= 0; i <list.size(); i++)
		{
			subList = (ArrayList)list.get(i);
			ETokenDTO eDTO = new ETokenDTO();
			eDTO.setMappingId((String)subList.get(0));
			eDTO.setUserId((String)subList.get(1));
			eDTO.setCounterId((String)subList.get(2));
			eDTO.setUserName((String)subList.get(3));
			eDTO.setCounterName((String)subList.get(4));
			eDTO.setAvailableStatus((String)subList.get(5));
			mainList.add(eDTO);
		}
		return mainList;
	}

	/**
	 * 
	 * @param mappingIds
	 * @param userType
	 * @param eForm
	 * @return boolean
	 * @throws Exception
	 */
	public boolean updateAvailableUsersList(String mappingIds[],String userType,ETokenForm eForm) throws Exception
	{
		boolean flag = false;
		ArrayList list = eForm.getAvailableUsersList();
		String status = "";
		Iterator itr = list.iterator();
		while(itr.hasNext())
		{
			status = "N";
			ETokenDTO eedto = (ETokenDTO)itr.next();
			String mappingId = eedto.getMappingId();
			for(int i = 0 ; i < mappingIds.length ; i++)
			{
				if(mappingId.equals(mappingIds[i]))
				{

					status = "Y";
					break;
				}
			}
			flag = eDAO.updateAvailableUsersList(mappingId,status, userType);
		}

		return flag;
	}
	/**
	 * 
	 * @param regInitId
	 * @return ArrayList
	 * @throws Exception
	 * @author ROOPAM
	 */
	public ArrayList checkRegInitId(String officeId,ETokenForm eForm) throws Exception
	{

		return eDAO.checkRegInitId(officeId, eForm.getEtokenDTO().getRegInitId());
	}
	public ArrayList checkRegInitId(String officeId,String regId) throws Exception
	{

		return eDAO.checkRegInitId(officeId, regId);
	}

	/**
	 * 
	 * @param regInitId
	 * @return ArrayList
	 * @throws Exception
	 * @author Tanushree
	 */
	public ArrayList searchEToken(String regID,String officeId) throws Exception
	{

		return eDAO.searchEtoken(regID,officeId);
	}


	/**
	 * 
	 * @param regInitId
	 * @return ArrayList
	 * @throws Exception
	 * @author Tanushree
	 * @param language
	 * @param officeId
	 */
	public boolean cancelEToken(String regID,String remarks,String catagory, String language, String officeId) throws Exception
	{

		return	eDAO.cancelEToken(regID,remarks,catagory,language,officeId);
	}

	/**
	 * 
	 * @param regInitId
	 * @return ArrayList
	 * @throws Exception
	 * @author Tanushree
	 */

	public ArrayList todayReportEtoken(String toDate,String fromDate,String off,String mo[]) throws Exception
	{

		ArrayList reportDetails = new ArrayList();
		reportDetails = eDAO.todayReportEtoken(toDate,fromDate,off,mo);

		return  reportDetails;
	}

	public ArrayList TimeTakenReportEtoken(String toDate,String fromDate,String off,String id) throws Exception
	{

		ArrayList reportDetails = new ArrayList();
		if(id.equalsIgnoreCase("1"))
			reportDetails = eDAO.timeTakenReprtEtoken(toDate,fromDate,off,id);
		else
			reportDetails = eDAO.timeTakenReprtEtokenC(toDate,fromDate,off,id);
		return  reportDetails;
	}

	public String ReportEtoken(String toDate,String fromDate,String off,String id, String language) throws Exception
	{

		String reportDetails = "";
		reportDetails = eDAO.ReportEtoken(toDate,fromDate,off,id,language);

		return  reportDetails;
	}

	public ArrayList getCounterId(int i, String officeID) {

		return eDAO.getCounterID(i,officeID);

	}
	
	public ArrayList getEtokenActiveCounterList(String language,String officeId) throws Exception{

		ArrayList list = eDAO.getEtokenActiveCounterList(language,officeId);
		ArrayList subList = new ArrayList();
		ArrayList mainList = new ArrayList();

		for(int i = 0 ; i < list.size(); i++)
		{
			subList = (ArrayList)list.get(i);
			ETokenDTO eDTO = new ETokenDTO();
			eDTO.setDistrictId((String)subList.get(0));
			eDTO.setDistrictName((String)subList.get(1));

			mainList.add(eDTO);
		}

		return mainList;

	}
	public ArrayList getEtokenDistrictList(String language,String officeId) throws Exception{

		ArrayList list = eDAO.getEtokenDistrictList(language,officeId);
		ArrayList subList = new ArrayList();
		ArrayList mainList = new ArrayList();

		for(int i = 0 ; i < list.size(); i++)
		{
			subList = (ArrayList)list.get(i);
			ETokenDTO eDTO = new ETokenDTO();
			eDTO.setDistrictId((String)subList.get(0));
			eDTO.setDistrictName((String)subList.get(1));

			mainList.add(eDTO);
		}

		return mainList;

	}

	//districtList
	public  ArrayList<ETokenDTO> getAttendenceData(String language,String districtId,String SROId) throws Exception{
		ArrayList list=new ArrayList();
		ArrayList counterData = eDAO.getCounterDataList(language,districtId,SROId);
		ArrayList attendenceData=eDAO.getAttendenceDataList(language,districtId,SROId);
		for (int i = 0; i < counterData.size(); i++) {

			ArrayList lst = (ArrayList) counterData.get(i);

			ETokenDTO eDTO = new ETokenDTO();
			int j=i+1;
			String serailNo=String.valueOf(j);
			System.out.println("serailNo"+serailNo);
			eDTO.setSerialNo(serailNo);

			eDTO.setCounterUserId((String) lst.get(0));
			eDTO.setCounterType((String) lst.get(1));
			eDTO.setCounterId((String) lst.get(2));
			eDTO.setCounterName((String) lst.get(3));

			if(attendenceData.size()>0){
				for(int k=0;k<attendenceData.size();k++){
					ArrayList attendence=(ArrayList)attendenceData.get(k);
					if(attendence.get(0).equals(lst.get(2))){
						eDTO.setCheck("true");
						break;
					}
				}
			}
			list.add(eDTO);
		}

		return list;

	}

	public ArrayList getEtokenSROList(String disttId,String language) throws Exception
	{
		ArrayList list = eDAO.getEtokenSROList(disttId,language);
		ArrayList subList = new ArrayList();
		ArrayList mainList = new ArrayList();

		for(int i = 0 ; i < list.size(); i++)
		{
			subList = (ArrayList)list.get(i);
			ETokenDTO eDTO = new ETokenDTO();
			eDTO.setSroId((String)subList.get(0));
			eDTO.setSroName((String)subList.get(1));

			mainList.add(eDTO);
		}

		return mainList;
	}

	public ArrayList getUserCounterMappingList(ETokenDTO eDTO,String UserType,String lang) throws Exception {
		String locArr[] = {eDTO.getDistrictId(),
				eDTO.getSroId()
		};
		ArrayList list = eDAO.getUserCounterMappingList(locArr,UserType,lang);
		ArrayList subList = new ArrayList();
		ArrayList mainList = new ArrayList();

		for(int i = 0 ;i < list.size() ;i++)
		{
			ETokenDTO eDTO1 = new ETokenDTO();
			subList = (ArrayList)list.get(i);

			eDTO1.setCounterType((String)subList.get(1));
			eDTO1.setCounterUserId((String)subList.get(1));
			System.out.println("1 : "+eDTO1.getCounterUserId());
			System.out.println("2 : "+eDTO1.getCounterType());
			mainList.add(eDTO1);
		}
		logger.debug("size of list in bd-->"+mainList.size());
		return mainList;
	}

	public ArrayList getCounterType(String counterUserId,String DistrictId,String SroId, String language)throws Exception {
		ArrayList list = eDAO.getcounterType(counterUserId,DistrictId,SroId,language);
		ArrayList subList = new ArrayList();
		ArrayList mainList = new ArrayList();

		for(int i = 0 ;i < list.size() ;i++)
		{
			subList = (ArrayList)list.get(i);
			ETokenDTO eDTO = new ETokenDTO();
			eDTO.setCounterUserId((String)subList.get(0));
			//eDTO.setCounterUserId((String)subList.get(0));

			mainList.add(eDTO);
		}
		logger.debug("size of list in bd-->"+mainList.size());
		return mainList;
	}

	public ArrayList getcounterOfficeMappingList(String office,String userId) throws Exception {
		ArrayList list = eDAO.getcounterOfficeMappingList(office,userId);
		ArrayList subList = new ArrayList();
		ArrayList mainList = new ArrayList();

		for(int i = 0 ;i < list.size() ;i++)
		{
			subList = (ArrayList)list.get(i);
			ETokenDTO eDTO = new ETokenDTO();
			eDTO.setCounterOfficeMappingId((String)subList.get(0));
			eDTO.setCounterName((String)subList.get(1));
			mainList.add(eDTO);
		}
		logger.debug("size of list in bd-->"+mainList.size());
		return mainList;
	}
	public ArrayList getcounterOfficeMappingListInsert(ETokenForm eForm) throws Exception {
		//ArrayList list = eDAO.getcounterOfficeMappingListInsert(eForm);
		ArrayList subList = new ArrayList();
		ArrayList mainList = new ArrayList();
		return mainList;
	}
	public boolean insertAttendenceData(ETokenForm form, String[] checkBoxSize,String districtId,String SROId,String userId, String lang) throws Exception {
		return eDAO.insertAttendenceData(form,checkBoxSize,districtId,SROId,userId,lang);
	}
	public boolean updateAttendenceData(ETokenForm form, String[] checkBoxSize,String districtId,String SROId,String userId, String lang) throws Exception {
		return eDAO.updateAttendenceData(form,checkBoxSize,districtId,SROId,userId,lang);
	}

	public String getEtokenNum() {

		ETokenDAO dao = new ETokenDAO();


		return dao.getEtokenNum();
	}

	public String getDeedIDforRegtxn(String regId) {
		// TODO Auto-generated method stub
		ETokenDAO dao = new ETokenDAO();


		return dao.getDeedIDforRegtxn(regId);
	}

}
